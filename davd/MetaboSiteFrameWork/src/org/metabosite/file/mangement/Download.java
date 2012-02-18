/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.metabosite.file.mangement;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.metabosite.utils.KFile;
import org.metabosite.utils.Session;

import src.EJBKException;
import src.entities.Files;
import src.entities.Siteuser;
import src.services.FileFacadeLocal;



public class Download extends HttpServlet {
    
 
    @EJB
    private FileFacadeLocal fcFacade;
    
    private static final long serialVersionUID = 1L;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idParam = request.getParameter("id");
        if (idParam == null) {
            stop(request, response);
            return;
        }
        
        Long id = Long.parseLong(idParam);
        
        if (id == null) {
            stop(request, response);
        }
        
        String name = null;
        
        Siteuser u = null;
        u = (Siteuser) request.getSession().getAttribute(Session.UserCo.getName());
        
        Files fc = null;
        if (u == null || !u.getConnected()) {
            stop(request, response);
            return;
        } else {
            try {
                fc = fcFacade.findById(id);
                if (fc == null) {
                    throw new EJBKException("Le fichier demandé n'existe pas...");
                }
                
                name = fc.getFileName() + "." + fc.getExtention();
                
               
            } catch (EJBKException ex) {
                String mess = "File not found...";
                Logger.getLogger(Download.class.getName()).log(Level.SEVERE, mess, ex);
                stop(request, response);
                return;
            }
        }
        
        response.setContentType("application/force-download");
        
        KFile kf = null;
        try {
            kf = KFile.getKFile(fc.getFileLocation());
        } catch (FileNotFoundException ex) {
            kf = null;
            Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (kf != null) {
            response.addHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
            response.setContentType(kf.getMime());
            response.setContentLength(kf.getData().length);
            ServletOutputStream out = response.getOutputStream();
            try {
                out.write(kf.getData());
            } finally {
                out.close();
            }
        } else {
            response.sendError(404, "File not found.");
        }
        
    }
    
    private void stop(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect(
                request.getContextPath() + 
                "/faces/index.xhtml"
            );
        } catch (IOException ex) {
            Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
