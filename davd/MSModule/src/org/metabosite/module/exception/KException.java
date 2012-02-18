package org.metabosite.module.exception;

import org.metabosite.module.utils.JsfUtil;

public abstract class KException extends Exception {
    
	private static final long serialVersionUID = 1L;
	
	private String message;

    @Override
    public String getMessage() {
        return message;
    }
    
    KException(String message) {
        this.message = message;
    }
    
    public void diplay() {
        JsfUtil.addErrorMessage(message);
    }
}
