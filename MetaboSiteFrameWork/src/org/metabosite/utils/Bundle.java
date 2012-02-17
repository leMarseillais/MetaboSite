/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.metabosite.utils;

import org.metabosite.utils.Global;
import java.util.ResourceBundle;

public enum Bundle {
    UI("UI");

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
