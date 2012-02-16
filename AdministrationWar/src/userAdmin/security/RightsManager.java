package userAdmin.security;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;

import exception.MaxRightsKException;



public class RightsManager {
    
    private EnumMap<RightID, Right> rights = new EnumMap<RightID, Right>(RightID.class);
    private volatile static RightsManager rm = null;
    
    private RightsManager() {
        for (RightID r : RightID.values()) {
            try {
                rights.put(r, new Right(r.name()));
            } catch (MaxRightsKException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
    
    public static RightsManager getInstance() {
        if (rm == null) {
            synchronized(RightsManager.class) {
                if (rm == null) {
                    rm = new RightsManager();
                }
            }
        }
        return rm;
    }
    
    public Right get(RightID rID) {
        return rights.get(rID);
    }
    
    public Long toLong(RightID rID) {
        return rights.get(rID).getValue();
    }
    
    public Long toLong(Access access) {
        Long result = 0L;
        
        for (RightID rID : access.getRights()) {
            result |= toLong(rID);
        }
        
        return result;
    }
    
    public Long toLong(Access[] access) {
        Long result = 0L;
        
        for (Access a : access) {
            result |= toLong(a);
        }
        
        return result;
    }
    
    public Access[] getAccess(Long rights) {
        Long droitsAccess = 0L;
        List<Access> access = new ArrayList<Access>();
        for (Access a : Access.values()) {
            droitsAccess = toLong(a);
            if ((rights & droitsAccess) == droitsAccess)
                access.add(a);
        }
        
        Access[] a = new Access[access.size()];
        int i = 0;
        for (Iterator<Access> aIt = access.iterator(); aIt.hasNext();) {
            a[i++] = aIt.next();
        }
        
        return a;
    }
    
}
