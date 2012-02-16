
package userAdmin.security;



public enum Access {
    ROOT(new RightID [] {
        RightID.ROOT,
        RightID.ADMIN,
        RightID.MANAGER,
        RightID.USER,
        RightID.GUEST,
        RightID.MAINTENANCE,
    }), 
    ADMIN(new RightID [] {
        RightID.ADMIN,
        RightID.MANAGER,
        RightID.USER,
        RightID.GUEST,
    }), 
    MANAGER(new RightID [] {
        RightID.MANAGER,
        RightID.USER,
        RightID.GUEST,
    }), 
    USER(new RightID [] {
        RightID.USER,
        RightID.GUEST,
    }), 
    GUEST(new RightID [] {
        RightID.GUEST,
        RightID.NOT_CONNECTED,
    }), 
    MIN(new RightID [] {
        RightID.GUEST,
    });
    
    private RightID[] rights;

    public RightID[] getRights() {
        return rights;
    }
    
    Access(RightID[] rights) {
        this.rights = rights;
    }
}
