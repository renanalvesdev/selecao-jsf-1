package com.planner.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.planner.treina.entity.Usuario;

import service.CadastroUsuarioService;

import util.CriptografiaUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;

	@Inject
	private CadastroUsuarioService cadastroUsuarioService;

	public void abrirModalCadastro() {
		usuario = new Usuario();
		RequestContext.getCurrentInstance().execute("PF('novoUsuarioDlg').show()");
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public CadastroUsuarioBean() {
		usuario = new Usuario();
	}

	public void salvar() {
		String senhaCriptografada = CriptografiaUtil.criptografaMd5(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		cadastroUsuarioService.salvar(usuario);
	}

	public void limpar() {
		usuario = new Usuario();
	}

}
