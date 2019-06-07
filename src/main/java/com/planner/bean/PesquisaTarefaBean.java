package com.planner.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.planner.repository.Tarefas;
import com.planner.repository.Usuarios;
import com.planner.treina.entity.Tarefa;
import com.planner.treina.entity.Usuario;

@Named
@ViewScoped
public class PesquisaTarefaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Tarefas tarefas;
	
	@Inject
	private LoginBean loginBean;
	
	private List<Tarefa> tarefasFiltrados = new ArrayList<>();

	public void limpar() {

	}
	
	@PostConstruct
	public void pesquisar(Usuario usuario) {
		tarefasFiltrados = tarefas.tarefasPorUsuario(usuario);
	}

	public List<Tarefa> getTarefasFiltrados() {
		return tarefasFiltrados;
	}

	public void setTarefasFiltrados(List<Tarefa> tarefasFiltrados) {
		this.tarefasFiltrados = tarefasFiltrados;
	}


	
}
