/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiutech.simix.controlador;

import com.xiutech.simix.modelo.Calificacion;
import com.xiutech.simix.modelo.CalificacionDAO;
import com.xiutech.simix.modelo.CalificacionId;
import com.xiutech.simix.modelo.Comentario;
import com.xiutech.simix.modelo.Comentarista;
import com.xiutech.simix.modelo.ComentaristaDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * Clase controlador para calificar comentarios sobre marcadores.
 * @author Mauricio Suarez Barrera
 * @version 11/05/19 
 */
@ManagedBean
public class CalificarComentarioController{        
    
    public void calificaComentario(int calificacion, Comentario coment){
        //busca al comentarista loggeado
        ComentaristaDAO comentaristaUDB = new ComentaristaDAO();
        SessionController.UserLogged usuario = (SessionController.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        Comentarista comentarista = comentaristaUDB.find(usuario.getCorreo());
        
        //crea el id de la calificacion
        CalificacionId calificacionId = new CalificacionId();
        calificacionId.setIdMarcador(coment.getMarcador().getIdMarcador());
        calificacionId.setCorreoComentarista(coment.getComentarista().getCorreo());
        calificacionId.setCorreoCalificador(comentarista.getCorreo());
        
        //Se crea la calificacion para agregarlo a la base de datos
        Calificacion calif = new Calificacion();
        calif.setCalificacion(calificacion);
        calif.setComentarista(comentarista);
        calif.setComentario(coment);
        calif.setId(calificacionId);
        
        //Se agrega a la base de datos.
        CalificacionDAO calificacionUDB = new CalificacionDAO ();
        calificacionUDB.save(calif);
    }
 
    public int getLikes(Comentario coment){
        int likes = 0;
        CalificacionDAO calDAO = new CalificacionDAO();
        for(Calificacion cal: calDAO.buscaPorComentario(coment)){
            if(cal.getCalificacion() == 1)
                likes++;
        }
        return likes;
    }
    
    public int getDislikes(Comentario coment){
        int dislikes = 0;
        CalificacionDAO calDAO = new CalificacionDAO();
        for(Calificacion cal: calDAO.buscaPorComentario(coment)){
            if(cal.getCalificacion() == -1)
                dislikes++;
        }
        return dislikes;
    }
}

