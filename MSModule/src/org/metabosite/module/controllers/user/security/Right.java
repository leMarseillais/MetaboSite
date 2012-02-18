package org.metabosite.module.controllers.user.security;

import org.metabosite.module.controllers.exception.MaxRightsKException;

public class Right {
    
    private static final boolean debug = false;
    
    private static final int max = Long.SIZE;
    private static Long cnt = 0L;
    private Long value = 0L;
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Long getValue() {
        return value;
    }
    
    public Right(String name) throws MaxRightsKException {
        if (cnt >= max) {
            throw new MaxRightsKException(
                    "Il ne peut pas y avoir plus de " + 
                    max +
                    "droits.");
        }
        
        value |= 1;
        value <<= cnt;
        cnt++;
        
        if (debug)
            System.out.println("new Right:"+name+":"+cnt+":"+value);
        
        this.name = name;
    }
}
