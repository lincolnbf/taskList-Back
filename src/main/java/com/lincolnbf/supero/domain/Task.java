package com.lincolnbf.supero.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * Gerando o id de cada task usando o AutoIncrement do MySQL, e a declaração do restante dos campos
	 * @author lincoln
	 * 
	 */	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String titulo;

	private String status;

	private String descricao;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataCriacao;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataEdicao;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataConclusao;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dataExclusao;
	
	public Task() {

	}
	
	/**
	 * 
	 * Contrutor para instanciação de um objeto da classe Task
	 * @author lincoln
	 * 
	 */	
	public Task(Integer id, String titulo, String status, String descricao) {
		super();		
		this.id = id;
		this.titulo = titulo;		
		this.descricao = descricao;
		this.status = "Pendente";
		this.dataCriacao = new Date();
	}
	
	/**
	 * 
	 * Getters e Setters de cada atributo
	 * @return 
	 * @author lincoln
	 * 
	 */	
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataEdicao() {
		return dataEdicao;
	}

	public void setDataEdicao(Date dataEdicao) {
		this.dataEdicao = dataEdicao;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

}
