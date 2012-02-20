package org.metabosite.module.utils;


public enum Nav {
    Index("Index"), Co("Connection"), Compte("Account"), 
    UList("UtilisateurList"), FList("FichierList"),
    HList("HierarchieList"), NList("NiveauList"),
    FApp("FilesAppManage"), FAppManage("FilesAppManage"),
    ModIndex("ModIndex");
    
    private String destination;
    
    public String getDestination() {
        return destination;
    }
    
    Nav(String destination) {
        this.destination = destination;
    }
}
