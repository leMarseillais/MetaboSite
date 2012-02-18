/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.util;


public enum Nav {
    Index("Index"), Co("Connection"), Compte("Account"), 
    UList("UtilisateurList"), FList("FichierList"),
    HList("HierarchieList"), NList("NiveauList"),
    FApp("FilesAppManage"), FAppManage("FilesAppManage");
    
    private String destination;
    
    public String getDestination() {
        return destination;
    }
    
    Nav(String destination) {
        this.destination = destination;
    }
}
