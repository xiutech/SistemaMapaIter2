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
 * Clase para manejar datos de tabla Comentario en la base.
 * @author Jose Fernando Reyes Garcia
 * @version 04/04/19
 */
@ManagedBean
public class ComentarioDAO extends AbstractDAO<Comentario>{
    
    /**
     * Constructor default. Usa el constructor de AbstractDAO.
     */
    public ComentarioDAO(){
        super();
    }
    
    /**
     * Guarda un Comentario en la base.
     * @param comentario El Comentario a agregar.
     */
    @Override
    public void save(Comentario comentario){
        super.save(comentario);
    }
    
    /**
     * Actualiza un Comentario en la base.
     * @param comentario el Comentario a actualizar.
     */
    @Override
    public void update(Comentario comentario){
        super.update(comentario);
    }
    
    /**
     * Elimina un Comentario en la base.
     * @param comentario el Comentario a eliminar.
     */
    @Override
    public void delete(Comentario comentario){
        super.delete(comentario);
    }
    
    /**
     * Busca un Comentario, dado su identificador.
     * @param id el identificador del Comentario.
     * @return el Comentario, null si no está.
     */
    public Comentario find(ComentarioId id){
        return super.find(Comentario.class, id);
    }
    
    /**
     * Busca todos los comentarios en la base.
     * @return Una lista de todos los comentarios en la clase. 
     */
    public List<Comentario> findAll(){
        return super.findAll(Comentario.class);
    }
    
     /**
     * Busca los objetos de determinada clase.
     * @param id_Marcador la clase de objetos a buscar.
     * @return Una lista de todos los objetos en la clase.
     */
    public List<Comentario> findMarcador(int id_Marcador){
        
        List<Comentario> obj = null;
        
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from Comentario where id_marcador = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id_Marcador);
            obj = (List<Comentario>) query.list();
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

