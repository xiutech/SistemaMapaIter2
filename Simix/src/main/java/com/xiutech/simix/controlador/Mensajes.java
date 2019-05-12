/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiutech.simix.controlador;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Clase para controlar mensajes de salida
 * @author Kevin Cervantes Gonzalez
 * @version 11/05/19 
 */
public class Mensajes {
    
    /**
     * Muestra un mensaje informativo.
     * @param info el mensaje a mostrar
     */
    public static void info(String info) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", info));
    }
    
    /**
     * Muestra un mensaje de advertencia.
     * @param warn el mensaje a mostrar
     */
    public static void warn(String warn) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", warn));
    }
     
    /**
     * Muestra un mensaje de error.
     * @param error el mensaje a mostrar
     */
    public static void error(String error) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", error));
    }
     
    /**
     * Muestra un mensaje de error fatal.
     * @param error el mensaje a mostrar
     */
    public static void fatal(String fatal) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", fatal));
    }
}
