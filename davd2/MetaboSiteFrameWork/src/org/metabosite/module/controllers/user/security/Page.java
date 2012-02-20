package org.metabosite.module.controllers.user.security;

public enum Page {
	Module(
			"/module/",
			Access.USER
	),
    Co(
            "/userInterface/connect.xhtml", 
            Access.GUEST
    );
    
    private String path;
    private Access[] access;

    public Access[] getAccess() {
        return access;
    }

    public String getPath() {
        return path;
    }
    
    Page(String path, Access access) {
        this.path = path;
        this.access = new Access[] {access};
    }
    
    Page(String path, Access[] access) {
        this.path = path;
        this.access = access;
    }
}
