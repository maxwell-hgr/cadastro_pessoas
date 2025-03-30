package org.sinerji.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

public class FacesMessages implements Serializable {

    private static final long serialVersionUID = 1L;

    private void add(String msg) {
        FacesMessage facesMessage = new FacesMessage(msg);
        facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);

        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        // cria mensagem e joga no contexto do jsf com a mensagem passada + severity.info (notificação azul)
    }

    public void info(String msg) {
        add(msg);
    }
}
