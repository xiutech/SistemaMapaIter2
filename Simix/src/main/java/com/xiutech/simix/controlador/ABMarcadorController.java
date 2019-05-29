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
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

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
    private Marker marcador;
    private MapModel simpleModel;
    private LatLng centro;
    
    
    @PostConstruct
    public void init(){
        this.centro = new LatLng(23.382390, -102.291477);
        setSimpleModel(new DefaultMapModel());
        setMarcador(new Marker(centro,"Arrastrame"));
        getMarcador().setDraggable(true);
        getSimpleModel().addOverlay(getMarcador());
        this.latitud = getMarcador().getLatlng().getLat();
        this.longitud = getMarcador().getLatlng().getLng();
    }
    
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
     * @return the centro
     */
    public LatLng getCentro() {
        return centro;
    }

    /**
     * @param centro the centro to set
     */
    public void setCentro(LatLng centro) {
        this.centro = centro;
    }
    
        /**
     * @return the marcador
     */
    public Marker getMarcador() {
        return marcador;
    }

    /**
     * @param marcador the marcador to set
     */
    public void setMarcador(Marker marcador) {
        this.marcador = marcador;
    }

    /**
     * @return the simpleModel
     */
    public MapModel getSimpleModel() {
        return simpleModel;
    }

    /**
     * @param simpleModel the simpleModel to set
     */
    public void setSimpleModel(MapModel simpleModel) {
        this.simpleModel = simpleModel;
    }
 
        public void onMarkerDrag(MarkerDragEvent event){
        marcador = event.getMarker();
        this.latitud = marcador.getLatlng().getLat();
        this.longitud = marcador.getLatlng().getLng();
    }

    public void onPointSelect(PointSelectEvent event) {
        LatLng latlng = event.getLatLng();
        marcador = simpleModel.getMarkers().get(0);
        marcador.setLatlng(latlng);
        this.latitud = latlng.getLat();
        this.longitud = latlng.getLng();
        this.setCentro(latlng);   
    }
    /**
     * Agrega un marcador en la base de datos.
     * @return el url de redireccionamiento, en este caso, para agregar otro marcador.
     */
    public String agregaMarcador(){
        MarcadorDAO mdb =new MarcadorDAO();
        Marcador marker = mdb.buscaPorLatLng(latitud, longitud);
        if(marker != null){
            this.descripcion ="";
            Mensajes.error("Ya existe un marcador con estas cordenadas \n" +"Lat: "+this.latitud +" Lng: "+this.longitud);
            return "";
        }
        marker = new Marcador();
        marker.setDescripcion(descripcion);
        marker.setDatosUtiles(datosUtiles);
        marker.setLatitud(latitud);
        marker.setLongitud(longitud);
        TemaDAO udbT = new TemaDAO();
        Tema temaO = udbT.find(this.tema);
        if(temaO == null){
            this.descripcion ="";
            Mensajes.error("El tema no existe");
            return "";
        }
        marker.setTema(temaO);
        mdb.save(marker);
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
