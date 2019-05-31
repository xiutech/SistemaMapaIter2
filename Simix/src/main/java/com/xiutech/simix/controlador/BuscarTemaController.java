/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiutech.simix.controlador;

import com.xiutech.simix.modelo.Tema;
import com.xiutech.simix.modelo.TemaDAO;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author fercho117
 */
@ManagedBean
public class BuscarTemaController {
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
    
    /*
    public List<String> parecidos(String query){
        TemaDAO temaDAO = new TemaDAO();
        return temaDAO.parecidos(query);
    }
    *
    public String redirige(){
        return ("/general/VistaTemaIH?faces-redirect=true&tema=" + tema);
    }
*/
    public String buscaTema(){
        TemaDAO temaDAO = new TemaDAO();
        Tema temaBuscado = temaDAO.find(tema);
        if(temaBuscado == null){
            Mensajes.fatal("No existe el tema");
            return "";
        }
        return ("/general/VistaTemaIH?faces-redirect=true&tema=" + tema);
    }
    
}
