package com.planner.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.primefaces.context.RequestContext;

import com.planner.treina.entity.Tarefa;
import com.planner.treina.entity.Usuario;

import util.FacesUtil;

public class Tarefas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;

	public Tarefa guardar(Tarefa tarefa) {
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();
		tarefa = entityManager.merge(tarefa);
		transaction.commit();

		FacesUtil.addInfoMessage("msgFora", "Cadastro realizado !");
		RequestContext.getCurrentInstance().execute("PF('novaTarefaDlg').hide()");
		
		return tarefa;
	}

	
	@SuppressWarnings("unchecked")
	public List<Tarefa> tarefasPorUsuario(Usuario usuario){
		
		List<Tarefa> listaTarefas = new ArrayList<>();
		
		Session session = this.entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Tarefa.class)
				.createAlias("usuario", "usuario");		
		criteria.add(Restrictions.eq("usuario.id", usuario.getId()));
		criteria.addOrder(Order.asc("dataCriacao"));
		
		listaTarefas = criteria.list();
		
		return listaTarefas;
	}
}
