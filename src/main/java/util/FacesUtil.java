package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {

	public static void addInfoMessage(String chave, String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(chave, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public static void addErroMessage(String chave, String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(chave, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg , msg));
	}
}