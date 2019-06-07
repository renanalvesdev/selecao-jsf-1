package service;

import java.io.Serializable;

import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import com.planner.bean.MessagesBean;
import com.planner.repository.Tarefas;
import com.planner.repository.Usuarios;
import com.planner.treina.entity.Tarefa;
import com.planner.treina.entity.Usuario;

import util.FacesUtil;

public class CadastroTarefaService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Tarefas tarefas;

	public Tarefa salvar(Tarefa tarefa) {
		return tarefas.guardar(tarefa);
	}
}
