/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

import bean.util.JsfUtil;



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
