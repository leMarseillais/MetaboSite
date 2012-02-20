/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

/**
 *
 * @author davd
 */
public class EJBKException extends Exception {
    
	private static final long serialVersionUID = 1L;
	
	private String message;

    @Override
    public String getMessage() {
        return message;
    }
    
    public EJBKException(String message) {
        this.message = message;
    }
}
