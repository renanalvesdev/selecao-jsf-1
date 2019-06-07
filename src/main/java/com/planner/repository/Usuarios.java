package com.planner.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.planner.treina.entity.Usuario;

import util.FacesUtil;

public class Usuarios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;

	public Usuario guardar(Usuario usuario) {
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();
		usuario = entityManager.merge(usuario);
		transaction.commit();

		FacesUtil.addInfoMessage("msgFora", "Operação realiza com sucesso!");
		return usuario;
	}

	public Usuario porLogin(String login) {
		try {
			return entityManager.createQuery("from Usuario where login = :login", Usuario.class)
					.setParameter("login", login).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}
	
	public Usuario porAutenticacao(String login, String senha) {
		try {
			return entityManager.createQuery("from Usuario where login = :login and senha = :senha", Usuario.class)
					.setParameter("login", login).setParameter("senha", senha).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> todos(){
		return entityManager.createQuery("from Usuario ").getResultList();
	}
}
