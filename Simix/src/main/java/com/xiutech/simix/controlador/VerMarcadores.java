/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiutech.simix.controlador;

import com.xiutech.simix.modelo.Marcador;
import com.xiutech.simix.modelo.MarcadorDAO;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 * Clase para la visualizacion de marcadores.
 * @author Jose Fernando Reyes Garcia
 * @version 11/05/19 
 */
@ManagedBean
@ViewScoped
public class VerMarcadores {
    private MapModel simpleModel;
    private Marker markerSelected;
    private Marcador marcadorSeleccionado;
    
    @PostConstruct
    public void verMarcadores(){
        setSimpleModel(new DefaultMapModel());
        MarcadorDAO marcadorDAO = new MarcadorDAO();
        List<Marcador> marcadores = marcadorDAO.findAll();
        
        for(Marcador m :marcadores){
            LatLng cord = new LatLng(m.getLatitud(),m.getLongitud());
            Marker marcador = new Marker(cord,m.getDescripcion());
            marcador.setIcon("resources/images/" + m.getTema().getNombre() + ".svg");
            getSimpleModel().addOverlay(marcador);
        }
        
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

    /**
     * @return the markerSelected
     */
    public Marker getMarkerSelected() {
        return markerSelected;
    }

    /**
     * @param markerSelected the markerSelected to set
     */
    public void setMarkerSelected(Marker markerSelected) {
        this.markerSelected = markerSelected;
    }

    /**
     * @return the marcadorSeleccionado
     */
    public Marcador getMarcadorSeleccionado() {
        return marcadorSeleccionado;
    }

    /**
     * @param marcadorSeleccionado the marcadorSeleccionado to set
     */
    public void setMarcadorSeleccionado(Marcador marcadorSeleccionado) {
        this.marcadorSeleccionado = marcadorSeleccionado;
    }
    
    /**
     * Metodo para mostrar detalles al seleccionar un marcador.
     * @param event El evento en que se realiza la accion.
     */
    public void onMarkerSelect(OverlaySelectEvent event) {
       this.markerSelected =(Marker) event.getOverlay();
       MarcadorDAO mDAO = new MarcadorDAO();
       this.marcadorSeleccionado = mDAO.buscaPorLatLng(markerSelected.getLatlng().getLat(), markerSelected.getLatlng().getLng());
       PrimeFaces current = PrimeFaces.current();
       current.executeScript("PF('dlg').show();");
    }
}
