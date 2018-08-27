package com.lincolnbf.supero.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.lincolnbf.supero.domain.Task;

public class TaskDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preencimento Obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 50 caracteres")
	private String titulo;
	
	@NotEmpty(message="Preencimento Obrigatório")
	@Length(min=5, max=254, message="O tamanho deve ser entre 5 e 254 caracteres")
	private String descricao;	

	public TaskDTO () {
		
	}
	
	public TaskDTO(Task task) {
		id = task.getId();
		titulo = task.getTitulo();
		descricao = task.getDescricao();			
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	

}
