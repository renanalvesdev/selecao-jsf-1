package com.planner.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class MessagesBean {

	public void adicionarMensagemErro() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Resumo da mensagem", "Mensagem completa" );
		context.addMessage("msgErro", msg);
	}
	
}
