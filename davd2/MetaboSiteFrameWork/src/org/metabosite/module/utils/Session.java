package org.metabosite.module.utils;

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
