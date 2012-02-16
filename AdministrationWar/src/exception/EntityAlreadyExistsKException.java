/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author davd
 */
public class EntityAlreadyExistsKException extends KException {
    
    private static final long serialVersionUID = 1L;
    
    public EntityAlreadyExistsKException(String message) {
        super(message);
    }
}
