package service;

import java.io.Serializable;

import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import com.planner.bean.MessagesBean;
import com.planner.repository.Usuarios;
import com.planner.treina.entity.Usuario;

import util.FacesUtil;

public class CadastroUsuarioService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;

	public Usuario salvar(Usuario usuario) {

		Usuario usuarioExistente = usuarios.porLogin(usuario.getLogin());
		if (usuarioExistente != null) {
			FacesUtil.addErroMessage("msg", "Usuário já cadastrado !");
			return null;
		}
		
		RequestContext.getCurrentInstance().execute("PF('novoUsuarioDlg').hide()");
		return usuarios.guardar(usuario);
	}
	
	
}
