package com.lincolnbf.supero.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lincolnbf.supero.domain.Task;
import com.lincolnbf.supero.dto.TaskDTO;
import com.lincolnbf.supero.repositories.TaskRepository;
import com.lincolnbf.supero.services.exceptions.ObjectNotFoundException;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository repo;
	
	public List<Task> findAll(){
		return repo.findAll();
	}
	
	public Task find(Integer id) {
		Optional<Task> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Task.class.getName()));
	}
	
	public Task insert (Task obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Task update (Task obj) {
		Task newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete (Integer id) {
		Task newObj = find(id);
		deleteData(newObj);
		repo.save(newObj);
	}	
	
	public void finalize(Integer id) {
		Task newObj = find(id);
		concluirTask(newObj);
		repo.save(newObj);		
	}	
	
	public void undoFinalize(Integer id) {
		Task newObj = find(id);
		desfazerTask(newObj);
		repo.save(newObj);
	}
	
	private void deleteData(Task newObj) {
		newObj.setStatus("Excluido");
		newObj.setDataExclusao(new Date());		
	}
	
	private void concluirTask(Task newObj) {
		newObj.setStatus("Concluido");
		newObj.setDataConclusao(new Date());
	}	
	
	private void desfazerTask(Task newObj) {
		newObj.setStatus("Pendente");
		newObj.setDataConclusao(new Date(0));
	}	
		
	private void updateData(Task newObj, Task obj) {
		newObj.setDescricao(obj.getDescricao());
		newObj.setTitulo(obj.getTitulo());
		newObj.setDataEdicao(new Date());
	}	
	
	public Task fromDTO(TaskDTO objDTO) {
		Task task = new Task(objDTO.getId(), objDTO.getTitulo(), "PENDENTE", objDTO.getDescricao());
		return task;
	}
}
