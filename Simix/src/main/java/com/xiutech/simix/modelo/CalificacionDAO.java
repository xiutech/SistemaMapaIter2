/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiutech.simix.modelo;

import java.util.List;
import javax.faces.bean.ManagedBean;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Clase para manejar datos de tabla Calificacion en la base.
 * @author Jose Fernando Reyes Garcia
 * @version 04/04/19
 */
@ManagedBean
public class CalificacionDAO extends AbstractDAO<Calificacion>{
      
    /**
     * Constructor default. Usa el constructor de AbstractDAO.
     */
    public CalificacionDAO(){
        super();
    }
    
    /**
     * Guarda una Calificacion en la base.
     * @param calificacion la Calificacion a agregar.
     */
    @Override
    public void save(Calificacion calificacion){
        super.save(calificacion);
    }
    
    /**
     * Actualiza una Calificacion en la base.
     * @param calificacion la Calificacion a actualizar.
     */
    @Override
    public void update(Calificacion calificacion){
        super.update(calificacion);
    }
    
    /**
     * Elimina una Calificacion en la base.
     * @param calificacion la Calificacion eliminar.
     */
    @Override
    public void delete(Calificacion calificacion){
        super.delete(calificacion);
    }
    
    /**
     * Busca una Calificacion, dado su identificador.
     * @param id el identificador de la Calificacion.
     * @return la Calificacion, null si no está.
     */
    public Calificacion find(CalificacionId id){
        return super.find(Calificacion.class, id);
    }
    
    /**
     * Busca todas las calificaciones en la base.
     * @return Una lista de todas las calificaciones en la base.
     */
    public List<Calificacion> findAll(){
        return super.findAll(Calificacion.class);
    }
    
    public boolean estaCalificado(Comentario coment, String comentarista){
        CalificacionId id = new CalificacionId();
        id.setCorreoCalificador(comentarista);
        id.setCorreoComentarista(coment.getComentarista().getCorreo());
        id.setIdMarcador(coment.getMarcador().getIdMarcador());
        return (find(id) != null);
    }
    
    public List<Calificacion> buscaPorComentario(Comentario coment){
        List<Calificacion> obj = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from Calificacion where correo_comentarista = :mail and id_marcador = :id";
            Query query = session.createQuery(hql);
            query.setParameter("mail", coment.getComentarista().getCorreo());
            query.setParameter("id", coment.getMarcador().getIdMarcador());
            obj = (List<Calificacion>) query.list();
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)
                tx.rollback();
        }finally{
            session.close();
        }
        return obj;
    }
}

