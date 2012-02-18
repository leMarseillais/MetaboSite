package org.metabosite.module.utils;


import java.util.ResourceBundle;

/**
 *
 * @author davd
 */
public enum Bundle {
    MBFE("ManagedBeansForEntities"), Err("Errors"), Rich("RichFaces"),
    UserI("UserInterface"), Functs("Functionalities"), Files("FilesApp");

    private ResourceBundle bundle;
    private String name;

    public ResourceBundle getBundle() {
        return bundle;
    }

    public String getName() {
        return name;
    }

    Bundle(String name) {
        this.name = name;
        this.bundle = ResourceBundle.getBundle(
                Global.BUNDLES_BASE_NAME + name, Global.getLocale());
    }
}
