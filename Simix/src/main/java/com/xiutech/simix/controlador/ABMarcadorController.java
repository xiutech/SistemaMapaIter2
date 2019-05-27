/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiutech.simix.controlador;

import com.xiutech.simix.modelo.Marcador;
import com.xiutech.simix.modelo.MarcadorDAO;
import com.xiutech.simix.modelo.Tema;
import com.xiutech.simix.modelo.TemaDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Clase controlador dedicada al alta de marcadores.
 * @author Jose Fernando Reyes Garcia
 * @version 11/05/19 
 */
@ViewScoped
@ManagedBean
public class ABMarcadorController {
    private double longitud;
    private double latitud;
    private String descripcion;
    private String datosUtiles = "Sin datos útiles";
    private String tema;
    
    /**
     * @return the tema
     */
    public String getTema() {
        return tema;
    }

    /**
     * @param tema the tema to set
     */
    public void setTema(String tema) {
        this.tema = tema;
    }
    
    /**
     * @return the longitud
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    /**
     * @return the latitud
     */
    public double getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /**
     * @return the datosUtiles
     */
    public String getDatosUtiles() {
        return datosUtiles;
    }

    /**
     * @param datosUtiles the datosUtiles to set
     */
    public void setDatosUtiles(String datosUtiles) {
        this.datosUtiles = datosUtiles;
    }
    
    /**
     * Agrega un marcador en la base de datos.
     * @return el url de redireccionamiento, en este caso, para agregar otro marcador.
     */
    public String agregaMarcador(){
        MarcadorDAO mdb =new MarcadorDAO();
        Marcador marcador = mdb.buscaPorLatLng(latitud, longitud);
        if(marcador!= null){
            this.descripcion ="";
            Mensajes.error("Ya existe un marcador con estas cordenadas \n" +"Lat: "+this.latitud +" Lng: "+this.longitud);
            return "";
        }
        marcador = new Marcador();
        marcador.setDescripcion(descripcion);
        marcador.setDatosUtiles(datosUtiles);
        marcador.setLatitud(latitud);
        marcador.setLongitud(longitud);
        TemaDAO udbT = new TemaDAO();
        Tema temaO = udbT.find(this.tema);
        if(temaO == null){
            this.descripcion ="";
            Mensajes.error("El tema no existe");
            return "";
        }
        marcador.setTema(temaO);
        mdb.save(marcador);
        Mensajes.info("Marcador añadido");
        this.datosUtiles = "Sin datos útiles";
        return "/informador/AgregarMarcadorIH?faces-redirect=true";
    }
    

    /**
     * Agrega un marcador en la base de datos recibiendo un tema como parametro.
     * @return el url de redireccionamiento, en este caso, para agregar otro marcador.
     */
    public String agregaMarcadorTema(){
        MarcadorDAO mdb = new MarcadorDAO();
        Marcador marcador = mdb.buscaPorLatLng(latitud, longitud);
        if(marcador!= null){
            this.descripcion ="";
            Mensajes.error("Ya existe un marcador con estas cordenadas \n" +"Lat: "+this.latitud +" Lng: "+this.longitud);
            return "";
        }
        marcador = new Marcador();
        marcador.setDescripcion(descripcion);
        marcador.setDatosUtiles(datosUtiles);
        marcador.setLatitud(latitud);
        marcador.setLongitud(longitud);
        TemaDAO udbT = new TemaDAO();
        if(this.tema == null)
            Mensajes.error("Falta tema");
        Tema temaO = udbT.find(this.tema);
        if(temaO == null){
            this.descripcion ="";
            Mensajes.error("El tema no existe");
            return "";
        }
        marcador.setTema(temaO);
        mdb.save(marcador);
        Mensajes.info("Marcador añadido");
        this.datosUtiles = "Sin datos útiles";
        return ("/informador/AgregarMarcadorTemaIH?faces-redirect=true&tema=" + tema);
    }
    
}
