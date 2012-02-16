
package userAdmin.security;


public enum Page {
    Index(
            "/info/index.xhtml", 
            Access.MIN
    ),
    Home(
            "/info/index.xhtml",
            Access.MIN
    ),
    Account(
            "/user/", 
            Access.USER
    ),
    Co(
            "/userInterface/connect.xhtml", 
            Access.GUEST
    ),
    UserI(
            "/userInterface/", 
            Access.GUEST
    ),
    JsfEntities_Niveaux(
            "/jsfEntities/niveau/", 
            Access.ROOT
    ),
    JsfEntities_Users(
            "/jsfEntities/utilisateur/", 
            Access.MANAGER
    ),
    JsfEntities_Files(
            "/jsfEntities/fichier/", 
            Access.MANAGER
    ),
    JsfEntities(
            "/jsfEntities/", 
            Access.ADMIN
    ),
    FAppDls(
            "/filesApp/telechargement", 
            Access.MANAGER
    ),
    FAppManage(
            "/fileInterface/manage.xhtml", 
            Access.MANAGER
    ),
    FAppView(
            "/filesApp/view.xhtml", 
            Access.USER
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
