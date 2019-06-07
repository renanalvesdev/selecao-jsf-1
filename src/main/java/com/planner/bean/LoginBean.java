package com.planner.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import com.planner.treina.entity.Usuario;

import service.CadastroUsuarioService;
import service.LoginService;
import util.CriptografiaUtil;
import util.FacesUtil;

@Named
//@ViewScoped
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -8699654255891466192L;

	@Inject
	private EntityManager em;

	@Inject
	private LoginService loginService;

	private Usuario usuario;

	public LoginBean() {
		usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	private String title;

	@PostConstruct
	public void init() {
		this.title = "Login - do bean";
		select();
	}

	public String login() {
		String senhaCriptografada = CriptografiaUtil.criptografaMd5(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		
		Usuario usuarioLogado = loginService.logar(usuario);

		if (usuarioLogado != null) {
			usuario = usuarioLogado;
			return "/usuarios/listaUsuario.xhtml?faces-redirect=true";
		}

		FacesUtil.addErroMessage("msgFora", "Login inválido !");
		return null;
	}

	private void select() {

		List<Usuario> usuarios = em.createQuery("from Usuario", Usuario.class).getResultList();

		for (Usuario usuario : usuarios) {
			System.out.println(usuario.getUsuario());
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
