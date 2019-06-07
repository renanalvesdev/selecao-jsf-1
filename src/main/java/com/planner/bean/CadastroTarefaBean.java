package com.planner.bean;

import java.io.Serializable;
import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.planner.treina.entity.Tarefa;
import com.planner.treina.entity.Usuario;

import service.CadastroTarefaService;
import service.CadastroUsuarioService;

@Named
@ViewScoped
public class CadastroTarefaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Tarefa tarefa;
	
	@Inject 
	private LoginBean LoginBean;
	
	@Inject
	private CadastroTarefaService cadastroTarefaService;
	

	public CadastroTarefaBean() {
		tarefa = new Tarefa();
	}
	
	public void novaTarefa() {
		limpar();
		RequestContext.getCurrentInstance().execute("PF('novaTarefaDlg').show()");
	}

	public void salvar(Usuario usuario) {
		tarefa.setUsuario(usuario);
		tarefa.setDataCriacao(new Date());
		cadastroTarefaService.salvar(tarefa);
	}

	public void limpar() {
		tarefa = new Tarefa();
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
	

}
