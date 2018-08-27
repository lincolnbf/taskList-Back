package com.lincolnbf.supero.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lincolnbf.supero.domain.Task;
import com.lincolnbf.supero.dto.TaskDTO;
import com.lincolnbf.supero.services.TaskService;

@RestController
@RequestMapping(value="/tasks")
public class TaskResource {

	@Autowired
	private TaskService taskService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Task>> findAll() {
		List<Task> list = taskService.findAll();		
		return ResponseEntity.ok().body(list);
	}	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Task> find(@PathVariable Integer id) {
		Task obj = taskService.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert( @Valid @RequestBody TaskDTO objDto) {
		Task obj = taskService.fromDTO(objDto);
		obj = taskService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody TaskDTO objDto, @PathVariable Integer id){
		Task obj = taskService.fromDTO(objDto);
		obj.setId(id);		
		obj = taskService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Task> delete(@PathVariable Integer id) {
		taskService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
