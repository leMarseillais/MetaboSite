/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ResourceBundle;


public enum Bundle {
	 Err("Errors"),Files("File");

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
                "bundles."+ name, Global.getLocale());
    }
}
