package com.xiutech.simix.controlador;

import com.xiutech.simix.modelo.Comentario;
import com.xiutech.simix.modelo.ComentarioDAO;
import com.xiutech.simix.modelo.ComentarioId;
import com.xiutech.simix.modelo.Comentarista;
import com.xiutech.simix.modelo.ComentaristaDAO;
import com.xiutech.simix.modelo.Marcador;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/** 
 *  Clase del controlador dedicada al alta de comentarios en marcadores.
 * @author Andrea Fernanda Muñiz Patiño
 * @version 11/05/19 
 */
@ManagedBean 
public class ABMComentarioController{
    private String texto; 
    private boolean editable=false;
   
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
 
    /**
     * Agrega un comentario a un marcador en la base de datos.
     * @param marcador marcador al que se agrega el comentario
     * @return el url de redireccionamiento, en este caso, para mostrar un mensaje.
     */
    public void agregaComentario(Marcador marcador){
        //busca al comentarista loggeado
        ComentaristaDAO comentaristaUDB = new ComentaristaDAO();
        SessionController.UserLogged usuario = (SessionController.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        Comentarista comentarista = comentaristaUDB.find(usuario.getCorreo());
       
        //crea el id del comentario
        ComentarioId comentId = new ComentarioId();
        comentId.setIdMarcador(marcador.getIdMarcador());
        comentId.setCorreoComentarista(comentarista.getCorreo());
        
        //Se crea el comentario para agregarlo a la base de datos
        Comentario coment = new Comentario();
        coment.setTexto(getTexto());
        coment.setComentarista(comentarista);
        coment.setMarcador(marcador);
        coment.setId(comentId);
        
        //Se agrega a la base de datos.
        ComentarioDAO comentarioUDB = new ComentarioDAO ();
        comentarioUDB.save(coment);
        //return "/general/MensajeExitoIH?faces-redirect=true&mensaje=" + mensaje;
    }
   /**
     * Elimina un comentario de un marcador en la base de datos.
     * @param comentario  el comentario a eliminar
     */
    public void eliminaComentario(Comentario comentario){
        
        ComentarioDAO comentarioUDB = new ComentarioDAO();
        comentarioUDB.delete(comentario);
        
    }
    
    public void editarComentario(Marcador marcador) {
        ComentaristaDAO comentaristaUDB = new ComentaristaDAO();
        SessionController.UserLogged usuario = (SessionController.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        Comentarista comentarista = comentaristaUDB.find(usuario.getCorreo());
       
        //crea el id del comentario
        ComentarioId comentId = new ComentarioId();
        comentId.setIdMarcador(marcador.getIdMarcador());
        comentId.setCorreoComentarista(comentarista.getCorreo());
        
        ComentarioDAO comentarioUDB = new ComentarioDAO ();
        Comentario coment = comentarioUDB.find(comentId);
        coment.setTexto(texto);
        comentarioUDB.update(coment);
        
    }    

    /**
     * @return the editable
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * @param editable the editable to set
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }
}