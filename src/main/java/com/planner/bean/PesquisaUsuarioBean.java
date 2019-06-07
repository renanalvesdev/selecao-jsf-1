package com.planner.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.planner.repository.Usuarios;
import com.planner.treina.entity.Usuario;

import util.CriptografiaUtil;
import util.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;

	private String senhaAtual, novaSenha;

	private List<Usuario> usuariosFiltrados = new ArrayList<>();

	public void limpar() {
		senhaAtual = "";
		novaSenha = "";
	}
	
	public void abreModalSenha() {
		limpar();
		RequestContext.getCurrentInstance().execute("PF('novaSenhaDlg').show()");
	}
	
	public void alterarSenha(Usuario usuarioLogado) {
		if(usuarioLogado.getSenha().equals(CriptografiaUtil.criptografaMd5(senhaAtual))) {
			Usuario usuarioAtualizado = usuarioLogado;
			usuarioAtualizado.setSenha(CriptografiaUtil.criptografaMd5(novaSenha));
			usuarios.guardar(usuarioAtualizado);
			
			RequestContext.getCurrentInstance().execute("PF('novaSenhaDlg').hide()");
		}
		
		else {
			FacesUtil.addInfoMessage("msg", "Senha atual não confere!");
			limpar();
		}
	}

	@PostConstruct
	public void pesquisar() {
		usuariosFiltrados = usuarios.todos();
	}

	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}

	public void setUsuariosFiltrados(List<Usuario> usuariosFiltrados) {
		this.usuariosFiltrados = usuariosFiltrados;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

}
