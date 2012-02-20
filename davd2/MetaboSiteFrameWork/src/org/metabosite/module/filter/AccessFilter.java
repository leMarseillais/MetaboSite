/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.metabosite.module.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.metabosite.module.controllers.user.security.Access;
import org.metabosite.module.controllers.user.security.Page;
import org.metabosite.module.controllers.user.security.RightsManager;
import org.metabosite.module.utils.Session;

import src.entities.Siteuser;

public class AccessFilter implements Filter {
    
    private static final boolean debug = false;
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public AccessFilter() {
    }    
    
    private void doBeforeProcessing(RequestWrapper request, ResponseWrapper response)
            throws IOException, ServletException {
        if (debug) {
            log("AccessFilter:DoBeforeProcessing");
        }
        
        RightsManager rm = RightsManager.getInstance();

        if (debug) {
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println("Request URI :           " + request.getRequestURI());
            System.out.println("Request URL :           " + request.getRequestURL());
            System.out.println("Request Query String :  " + request.getQueryString());
            System.out.println("Request Path Info :     " + request.getPathInfo());
            System.out.println("Request Servlet Path :  " + request.getServletPath());
            System.out.println("------------------------");
        }
        
        String pathInfo = request.getPathInfo();
        String redirectPath = Page.Co.getPath();
        
        if (pathInfo != null && request.getServletPath().contains("mod")) {
            
            if (!pathInfo.contains("xhtml")) {
                return;
            }

            Page page = null;
            for (Page p : Page.values()) {
                if (pathInfo.contains(p.getPath())) {
                    page = p;
                    break;
                }
            }

            if (page != null) {
                Siteuser u = null;

                u = (Siteuser) request.getSession().getAttribute(Session.UserCo.getName());

                if (u == null) {
                    u = new Siteuser("Anonymous", true, "Anonymous Corp.", 
                    		RightsManager.getInstance().toLong(Access.GUEST), 
                    		"anonymous@metabosite.org", "anonymous");
                    u.setConnected(false);
                    
                    request.getSession().setAttribute(Session.UserCo.getName(), u);
                }

                if (debug) {
                    for (Access a : page.getAccess()) {
                        System.out.println(a);
                    }
                    System.out.println(rm.toLong(page.getAccess()));
                    System.out.println(u.getDroit());
                }

                if (u.has(rm.toLong(page.getAccess()))) {
                    return;
                }
            }
        }
        
        response.sendRedirect(
            request.getContextPath() + 
            request.getServletPath() +
            redirectPath
        );
    }    
    
    private void doAfterProcessing(RequestWrapper request, ResponseWrapper response)
            throws IOException, ServletException {
        if (debug) {
            log("AccessFilter:DoAfterProcessing");
        }
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        if (debug) {
            log("AccessFilter:doFilter()");
        }

        // Create wrappers for the request and response objects.
        // Using these, you can extend the capabilities of the
        // request and response, for example, allow setting parameters
        // on the request before sending the request to the rest of the filter chain,
        // or keep track of the cookies that are set on the response.
        //
        // Caveat: some servers do not handle wrappers very well for forward or
        // include requests.
        RequestWrapper wrappedRequest = new RequestWrapper((HttpServletRequest) request);
        ResponseWrapper wrappedResponse = new ResponseWrapper((HttpServletResponse) response);
        
        doBeforeProcessing(wrappedRequest, wrappedResponse);
        
        Throwable problem = null;
        
        try {
            chain.doFilter(wrappedRequest, wrappedResponse);
        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }
        
        doAfterProcessing(wrappedRequest, wrappedResponse);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter 
     */
    @Override
    public void destroy() {        
    }

    /**
     * Init method for this filter 
     */
    @Override
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("AccessFilter: Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AccessFilter()");
        }
        StringBuilder sb = new StringBuilder("AccessFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
        
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }

    /**
     * This request wrapper class extends the support class HttpServletRequestWrapper,
     * which implements all the methods in the HttpServletRequest interface, as
     * delegations to the wrapped request. 
     * You only need to override the methods that you need to change.
     * You can get access to the wrapped request using the method getRequest()
     */
    class RequestWrapper extends HttpServletRequestWrapper {
        
        public RequestWrapper(HttpServletRequest request) {
            super(request);
        }
        // You might, for example, wish to add a setParameter() method. To do this
        // you must also override the getParameter, getParameterValues, getParameterMap,
        // and getParameterNames methods.
        protected Hashtable<String, String[]> localParams = null;
        
        public void setParameter(String name, String[] values) {
            if (debug) {
                System.out.println("AccessFilter::setParameter(" + name + "=" + values + ")" + " localParams = " + localParams);
            }
            
            if (localParams == null) {
                localParams = new Hashtable<String, String[]>();
                // Copy the parameters from the underlying request.
                Map<String, String[]> wrappedParams = getRequest().getParameterMap();
                Set<String> keySet = wrappedParams.keySet();
                for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
                    String key = it.next();
                    String[] value = wrappedParams.get(key);
                    localParams.put(key, value);
                }
            }
            localParams.put(name, values);
        }
        
        @Override
        public String getParameter(String name) {
            if (debug) {
                System.out.println("AccessFilter::getParameter(" + name + ") localParams = " + localParams);
            }
            if (localParams == null) {
                return getRequest().getParameter(name);
            }
            Object val = localParams.get(name);
            if (val instanceof String) {
                return (String) val;
            }
            if (val instanceof String[]) {
                String[] values = (String[]) val;
                return values[0];
            }
            return (val == null ? null : val.toString());
        }
        
        @Override
        public String[] getParameterValues(String name) {
            if (debug) {
                System.out.println("AccessFilter::getParameterValues(" + name + ") localParams = " + localParams);
            }
            if (localParams == null) {
                return getRequest().getParameterValues(name);
            }
            return (String[]) localParams.get(name);
        }
        
        @Override
        public Enumeration<String> getParameterNames() {
            if (debug) {
                System.out.println("AccessFilter::getParameterNames() localParams = " + localParams);
            }
            if (localParams == null) {
                return getRequest().getParameterNames();
            }
            return (Enumeration<String>) localParams.keys();
        }        
        
        @Override
        public Map<String, String[]> getParameterMap() {
            if (debug) {
                System.out.println("AccessFilter::getParameterMap() localParams = " + localParams);
            }
            if (localParams == null) {
                return getRequest().getParameterMap();
            }
            return (Map<String, String[]>) localParams;
        }
    }

    /**
     * This response wrapper class extends the support class HttpServletResponseWrapper,
     * which implements all the methods in the HttpServletResponse interface, as
     * delegations to the wrapped response. 
     * You only need to override the methods that you need to change.
     * You can get access to the wrapped response using the method getResponse()
     */
    class ResponseWrapper extends HttpServletResponseWrapper {
        
        public ResponseWrapper(HttpServletResponse response) {
            super(response);            
        }
        // You might, for example, wish to know what cookies were set on the response
        // as it went throught the filter chain. Since HttpServletRequest doesn't
        // have a get cookies method, we will need to store them locally as they
        // are being set.
	/*
        protected Vector cookies = null;
        
        // Create a new method that doesn't exist in HttpServletResponse
        public Enumeration getCookies() {
        if (cookies == null)
        cookies = new Vector();
        return cookies.elements();
        }
        
        // Override this method from HttpServletResponse to keep track
        // of cookies locally as well as in the wrapped response.
        public void addCookie (Cookie cookie) {
        if (cookies == null)
        cookies = new Vector();
        cookies.add(cookie);
        ((HttpServletResponse)getResponse()).addCookie(cookie);
        }
         */
    }
}
