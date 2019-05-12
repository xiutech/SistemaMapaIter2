/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiutech.simix.controlador;

import com.xiutech.simix.modelo.Administrador;
import com.xiutech.simix.modelo.Informador;
import com.xiutech.simix.modelo.Comentarista;
import com.xiutech.simix.modelo.AdministradorDAO;
import com.xiutech.simix.modelo.InformadorDAO;
import com.xiutech.simix.modelo.ComentaristaDAO;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * Clase controlador para el manejo de sesion.
 * @author Kevin Cervantes Gonzalez
 * @version 11/05/19 
 */
@ManagedBean
@SessionScoped
public class SessionController implements Serializable{
    private String correo;
    private String contrasenia;

        /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * @param contrasenia the contrasenia to set
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }   
    
    /**
     * Inicia una sesión con el correo y contraseña dados en la instancia.
     * @return el url de redireccionamiento, en este caso, el perfil de usuario.
     */
    public String login(){
        AdministradorDAO adb = new AdministradorDAO();
        ComentaristaDAO cdb = new ComentaristaDAO();
        InformadorDAO idb = new InformadorDAO();
        
        Administrador admin = adb.buscaPorCorreoContrasenia(getCorreo(), getContrasenia());
        Comentarista comen = cdb.buscaPorCorreoContrasenia(getCorreo(), getContrasenia());
        Informador info = idb.buscaPorCorreoContrasenia(getCorreo(), getContrasenia());
        
        FacesContext context = FacesContext.getCurrentInstance();
        if(admin != null){
            UserLogged u = new UserLogged(admin.getNombre(),admin.getCorreo(), Rol.ADMINISTRADOR);   
            context.getExternalContext().getSessionMap().put("user", u);
            return "/administrador/PerfilAdministradorIH?faces-redirect=true";
        } else if (comen != null){
            UserLogged u = new UserLogged(comen.getNombre(),comen.getCorreo(), Rol.COMENTARISTA);
            context.getExternalContext().getSessionMap().put("user", u);
            return "/comentarista/PerfilComentaristaIH?faces-redirect=true";
        } else if (info != null) {
            UserLogged u = new UserLogged(info.getNombre(),info.getCorreo(), Rol.INFORMADOR);
            context.getExternalContext().getSessionMap().put("user", u);
            return "/informador/PerfilInformadorIH?faces-redirect=true";
        }
        
        Mensajes.error("NO hay usuarios con este correo"+this.getCorreo());
        return "";
        
    }
    
    /**
     * Cierra la sesion iniciada.
     * @return el url de redireccionamiento, en este caso, la pagina prinicipal (el index).
     */
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }
    
    /**
     * Clase enum para el manejo de roles de usuario:
     * Administrador, Informador y Comentarista
     */
    public enum Rol{
        ADMINISTRADOR,
        INFORMADOR,
        COMENTARISTA,
    }
    
    /**
     * Clase privada para el usuario loggeado.
     */
    public class UserLogged implements Serializable{
        private String nombre;
        private String correo;
        private Rol rol;
        
        /**
         * Crea un usuario loggeado.
         * @param nombre el nombre de usuario
         * @param correo el correo del usuario
         * @param rol el rol de usuario
         */
        public UserLogged(String nombre, String correo, Rol rol) {
            this.nombre = nombre;
            this.correo = correo;
            this.rol = rol;
        }        

        /**
         * @return the nombre
         */
        public String getNombre() {
            return nombre;
        }

        /**
         * @param nombre the nombre to set
         */
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        /**
         * @return the correo
         */
        public String getCorreo() {
            return correo;
        }

        /**
         * @param correo the correo to set
         */
        public void setCorreo(String correo) {
            this.correo = correo;
        }

        /**
         * @return the rol
         */
        public Rol getRol() {
            return rol;
        }

        /**
         * @param rol the rol to set
         */
        public void setRol(Rol rol) {
            this.rol = rol;
        }
    }

}