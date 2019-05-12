package com.xiutech.simix.controlador;

import com.xiutech.simix.modelo.Comentarista;
import com.xiutech.simix.modelo.ComentaristaDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


/**
 * Clase controlador dedicada a baja y modificacion de una cuenta 
 * de usuario con rol de comentarista.
 * @author Kevin Cervantes Gonzalez
 * @version 11/05/19 
 */

@ManagedBean
@SessionScoped
public class BMComentaristaController {
    private String nombre;
    private String correo;
    private String contrasenia;
    
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
     * Actualiza el nombre del comentarista loggeado.
     * @return el url de redireccionamiento, en este caso, el perfil del comentarista.
     */
    public String actualizaNombre() {
        SessionController.UserLogged ul = (SessionController.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        ComentaristaDAO cdb = new ComentaristaDAO();
        Comentarista comen = cdb.find(ul.getCorreo());
        comen.setNombre(this.getNombre());
        cdb.update(comen);
        return "/comentarista/PerfilComentaristaIH?faces-redirect=true";
    }
    
    /**
     * Actualiza la contraseña del comentarista loggeado.
     * @return el url de redireccionamiento, en este caso, el perfil del comentarista.
     */
    public String actualizaContrasenia() {
        SessionController.UserLogged ul = (SessionController.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        ComentaristaDAO cdb = new ComentaristaDAO();
        Comentarista comen = cdb.find(ul.getCorreo());
        comen.setContrasenia(this.getContrasenia());
        cdb.update(comen);
        return "/comentarista/PerfilComentaristaIH?faces-redirect=true";
    }
    
    /**
     * Da de baja del sistema la cuenta del usuario loggeado.
     * @return el url de redireccionamiento, en este caso, la página principal (el index).
     */
    public String borrarCuenta() {
        SessionController.UserLogged ul = (SessionController.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        ComentaristaDAO cdb = new ComentaristaDAO();
        Comentarista comen = cdb.find(ul.getCorreo());
        cdb.delete(comen);
        return "/index?faces-redirect=true";
    }
    
}