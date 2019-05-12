/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiutech.simix.controlador;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Clase para el filtro de páginas para sesion de Informador.
 * @author Jose Fernando Reyes Garcia
 * @version 11/05/19 
 */
@WebFilter("/informador/*")
public class FilterSessionInformador implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(true);
         if (session.getAttribute("user") == null) {
            res.sendRedirect(req.getContextPath() + "/index.xhtml"); // Si no hay usuario loggeado, redirige al index
        }
        else {
            SessionController.UserLogged usuario = (SessionController.UserLogged) session.getAttribute("user");
            if(usuario.getRol() == SessionController.Rol.INFORMADOR){
                chain.doFilter(req, res); // esta logueado un informador se continua con lo que se solicito.
            }else 
                res.sendRedirect(req.getContextPath() + "/index.xhtml"); // Si no es informador, redirige al index
        }
    }

    @Override
    public void destroy() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
