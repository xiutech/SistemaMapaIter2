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
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author fercho117
 */
@ViewScoped
@ManagedBean
public class VerMarcadoresTema {
    private MapModel simpleModel;
    private Marker markerSelected;
    private Marcador marcadorSeleccionado;    
    private Tema tema;
    
    public void verMarcadores(String nombre_tema){
        this.setSimpleModel(new DefaultMapModel());
        this.setTema(new TemaDAO().find(nombre_tema));
        MarcadorDAO marcadorDAO = new MarcadorDAO();
        
        List<Marcador> marcadores = marcadorDAO.buscaPorTema(nombre_tema);
        for(Marcador m:marcadores){
            LatLng cord = new LatLng(m.getLatitud(),m.getLongitud());
            Marker mark = new Marker(cord,m.getDescripcion());
            mark.setIcon("../resources/images/" + m.getTema().getNombre() + ".svg");
            this.getSimpleModel().addOverlay(mark);
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
    
    public void onMarkerSelect(OverlaySelectEvent event) {
       this.markerSelected =(Marker) event.getOverlay();
       MarcadorDAO mDAO = new MarcadorDAO();
       this.marcadorSeleccionado = mDAO.buscaPorLatLng(markerSelected.getLatlng().getLat(), markerSelected.getLatlng().getLng());
       PrimeFaces current = PrimeFaces.current();
       current.executeScript("PF('dlg').show();");  
    }

    /**
     * @return the tema
     */
    public Tema getTema() {
        return tema;
    }

    /**
     * @param tema the tema to set
     */
    public void setTema(Tema tema) {
        this.tema = tema;
    }
}
