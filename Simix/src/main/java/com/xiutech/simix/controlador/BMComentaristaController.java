package com.xiutech.simix.controlador;

import com.xiutech.simix.modelo.Comentarista;
import com.xiutech.simix.modelo.ComentaristaDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


/**
 * Clase controlador dedicada a baja y modificacion de una cuenta 
 * de usuario con rol de comentarista.
 * @author Kevin Cervantes Gonzalez
 * @version 11/05/19 
 */

@ManagedBean
@ViewScoped
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
     * Actualiza la contraseña y el nombre del comentarista loggeado.
     * @return el url de redireccionamiento, en este caso, el perfil del comentarista.
     */
    public String actualizaCuentaComen(){
        ComentaristaDAO cdb = new ComentaristaDAO();
        SessionController.UserLogged ul = (SessionController.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        Comentarista comen = cdb.buscaPorCorreo(ul.getCorreo());
        if(contrasenia==""){
            contrasenia=comen.getContrasenia();
        }
        if(nombre==""){
            nombre=comen.getNombre();
        }       
        comen.setContrasenia(contrasenia);
        comen.setNombre(nombre);
        cdb.update(comen);
        return "/comentarista/PerfilComentaristaIH?faces-redirect=true";
    }
    
    /**
     * Da de baja del sistema la cuenta del usuario loggeado.
     * @return el url de redireccionamiento, en este caso, la página principal (el index).
     */
    public String borrarCuenta() {
        ComentaristaDAO cdb = new ComentaristaDAO();
        SessionController.UserLogged ul = (SessionController.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        Comentarista comen = cdb.find(ul.getCorreo());
        comen.setEstado(false);
        cdb.update(comen);
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
        return "/index?faces-redirect=true";
    }
    
}