/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.metabosite.utils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.context.FacesContext;

public class Global {
    
    private static Locale locale = null;
    public static final String BUNDLES_BASE_NAME = "org.metabosite.bundles.";
	private static String FILES_PATH;


	public static String getFILES_PATH() {
		return FILES_PATH;
	}

    public static Locale getLocale() {
        if (FacesContext.getCurrentInstance() == null && locale == null) {
            locale = Locale.getDefault();
        } else if (locale == null) {
            locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        }
        return locale;
    }
    
    public static void setLocale(String languageCode) {
		locale = new Locale(languageCode);

		FacesContext.getCurrentInstance().getApplication()
				.setDefaultLocale(locale);

		// Redirection vers la page actuelle
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect(contextRoot() + currentPage());
		} catch (IOException ex) {
			Logger.getLogger(Global.class.getName())
					.log(Level.SEVERE, null, ex);
		}
	}
    
    public static String contextRoot() {
        return FacesContext.getCurrentInstance().getExternalContext()
                .getRequestContextPath();
    }
    
    public static String servletPath() {
        return FacesContext.getCurrentInstance().getExternalContext()
                .getRequestServletPath();
    }
    
    public static String currentPage() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestPathInfo();
	}

	public static Long currentTime() {
		return System.currentTimeMillis();
	}
	
    public static String resourcePath(String file) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ResourceHandler rh = fc.getApplication().getResourceHandler();
        Resource r = rh.createResource("org.metabosite/" + file);
        
        return r.getRequestPath();
    }
    public static void displaySessions() {
		Map<String, Object> s = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		for (Iterator<String> it = s.keySet().iterator(); it.hasNext();) {
			System.out.println(it.next());
		}
	}

	public static void clearSessions() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.clear();
	}

	public static Object getSession(Session session) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap().get(session.getName());
	}
}
