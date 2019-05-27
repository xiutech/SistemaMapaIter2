/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiutech.simix.controlador;

import com.xiutech.simix.modelo.Informador;
import com.xiutech.simix.modelo.InformadorDAO;
import com.xiutech.simix.modelo.Tema;
import com.xiutech.simix.modelo.TemaDAO;
import java.awt.Color;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
/**
 * Calse controlador dedicada al alta de temas en el sistema. 
 * @author Jose Fernando Reyes Garcia
 * @version 11/05/19 
 */
@ViewScoped
@ManagedBean
public class ABTemaController {    
    private String nombre;
    
    /**
     * Obtener el nombre del tema.
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modificar el nombre del tema.
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Agrega un tema a la base.
     * @return el url de redireccionamiento, en este caso, para agregar un marcador.
     */
    public String agregaTema(){
        TemaDAO udbT = new TemaDAO();
        Tema tema = udbT.find(nombre);
        if(tema != null){
            Mensajes.error("El tema ya existe. \n Añade tus marcadores al tema existente");
            return "";
        }
        InformadorDAO infDAO = new InformadorDAO();        
        SessionController.UserLogged usuario = (SessionController.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        Informador informador = infDAO.find(usuario.getCorreo());
        String color = generaColor();
        tema = new Tema(nombre, color);
        tema.setInformador(informador);
        udbT.save(tema);
        return ("/informador/AgregarMarcadorTemaIH?faces-redirect=true&tema=" + nombre);
    }
    
    //Genera un color aleatorio.
    private String generaColor(){
        Random random = new Random();
        Color col = new Color(random.nextInt(255), random.nextInt(255),random.nextInt(255));
        return col.toString();
    }
    
    /**
    public boolean tieneMarcadores(String tema){
        TemaDAO temaO = new TemaDAO();
        Tema tem = temaO.find(tema);
        return !(tem.getMarcadors().isEmpty());          
    }
    */
}
