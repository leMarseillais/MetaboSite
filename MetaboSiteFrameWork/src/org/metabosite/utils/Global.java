/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.metabosite.utils;

import java.util.Locale;

import javax.faces.context.FacesContext;

public class Global {
    
    private static Locale locale = null;
    public static final String BUNDLES_BASE_NAME = "org.metabosite.bundles.";

    public static Locale getLocale() {
        if (FacesContext.getCurrentInstance() == null && locale == null) {
            locale = Locale.getDefault();
        } else if (locale == null) {
            locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        }
        return locale;
    }
}
