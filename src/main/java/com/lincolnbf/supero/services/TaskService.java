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
	
	/**
	 * 
	 * Método para encontrar todas as tasks
	 * @return Retorna todas as tasks em formato de List<Task>
	 * @author lincoln
	 * 
	 */	
	public List<Task> findAll(){
		return repo.findAll();
	}
	
	/**
	 * 
	 * Método para encontrar uma task a partir de um id
	 * @return retorna o objeto Task ou gera uma exceção personalizada caso não o encontre.
	 * @author lincoln
	 * 
	 */	
	public Task find(Integer id) {
		Optional<Task> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Task.class.getName()));
	}
	
	/**
	 * 
	 * Método para inserir uma task a partir de um objeto Task
	 * @return 
	 * @author lincoln
	 * 
	 */	
	public Task insert (Task obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	/**
	 * 
	 * Método para atualizar uma task a partir de um objeto Task
	 * @return 
	 * @author lincoln
	 * 
	 */	
	public Task update (Task obj) {
		Task newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	/**
	 * 
	 * Método que chama o método que irá deletar uma Task, com seu retorno, coloca o status como Excluido no banco de dados.
	 * @return 
	 * @author lincoln
	 * 
	 */	
	public void delete (Integer id) {
		Task newObj = find(id);
		deleteData(newObj);
		repo.save(newObj);
	}	
	
	/**
	 * 
	 * Método que chama o método que irá concluir uma Task, com seu retorno, coloca o status como Concluido no banco de dados.
	 * @return 
	 * @author lincoln
	 * 
	 */	
	public void finalize(Integer id) {
		Task newObj = find(id);
		concluirTask(newObj);
		repo.save(newObj);		
	}	
	
	/**
	 * 
	 * Método que chama o método que irá remover o status
	 * de concluido de uma Task, com seu retorno, coloca o status como Pendente no banco de dados.
	 * @return 
	 * @author lincoln
	 * 
	 */	
	public void undoFinalize(Integer id) {
		Task newObj = find(id);
		desfazerTask(newObj);
		repo.save(newObj);
	}
	
	/**
	 * 
	 * Método que preenche o status e data da exclusão do objeto para ser excluído
	 * @return 
	 * @author lincoln
	 * 
	 */	
	private void deleteData(Task newObj) {
		newObj.setStatus("Excluido");
		newObj.setDataExclusao(new Date());		
	}
	
	/**
	 * 
	 * Método que preenche o status e data da conclusão do objeto para ser concluído
	 * @return 
	 * @author lincoln
	 * 
	 */	
	private void concluirTask(Task newObj) {
		newObj.setStatus("Concluido");
		newObj.setDataConclusao(new Date());
	}	
	
	/**
	 * 
	 * Método que preenche o status do objeto para ser Pendente (Não concluido) e coloca a data inicial do Java no local
	 * da data de conclusão
	 * @return 
	 * @author lincoln
	 * 
	 */	
	private void desfazerTask(Task newObj) {
		newObj.setStatus("Pendente");
		newObj.setDataConclusao(new Date(0));
	}	
	
	/**
	 * 
	 * Método que preenche prepara o objeto para ser atualizado, pegando os campos Descrição, Título e colocando
	 * a data de edição.
	 * @return 
	 * @author lincoln
	 * 
	 */	
	private void updateData(Task newObj, Task obj) {
		newObj.setDescricao(obj.getDescricao());
		newObj.setTitulo(obj.getTitulo());
		newObj.setDataEdicao(new Date());
	}	
	
	/**
	 * 
	 * Inicia uma task colocando o status como pendente
	 * @return um objeto Task para ser salvo.
	 * @author lincoln
	 * 
	 */	
	public Task fromDTO(TaskDTO objDTO) {
		Task task = new Task(objDTO.getId(), objDTO.getTitulo(), "PENDENTE", objDTO.getDescricao());
		return task;
	}
}
