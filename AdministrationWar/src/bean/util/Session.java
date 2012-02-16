/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.util;

/**
 *
 * @author davd
 */
public enum Session {
    MPMS("MULTI_PAGE_MESSAGES_SUPPORT"),
    UserCo("CONNECTED_MANAGED_USER"),
    FileApp("fileSystemController"),
    Users("utilisateurController");

    private String name;

    public String getName() {
        return name;
    }

    Session(String name) {
        this.name = name;
    }
}
