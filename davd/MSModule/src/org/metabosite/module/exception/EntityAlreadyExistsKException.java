package org.metabosite.module.exception;

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
