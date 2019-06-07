package service;

import java.io.Serializable;

import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import com.planner.bean.MessagesBean;
import com.planner.repository.Usuarios;
import com.planner.treina.entity.Usuario;

import util.FacesUtil;

public class LoginService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;

	public Usuario logar(Usuario usuario) {

		Usuario usuarioExistente = usuarios.porAutenticacao(usuario.getLogin(), usuario.getSenha());
		if (usuarioExistente != null) {
			return usuarioExistente;
		}		
		return null;
	}
}
