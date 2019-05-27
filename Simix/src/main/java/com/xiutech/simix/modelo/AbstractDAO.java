/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiutech.simix.modelo;

import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Clase abstracta para manejar datos de la base.
 * @author Jose Fernando Reyes Garcia
 * @version 04/04/19
 */
public abstract class AbstractDAO<T> {    
    protected SessionFactory sessionFactory;
    
    /**
     * Constructor por default. Inicia una fabricadora de sesiones.
     */
    public AbstractDAO(){
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
    
    /**
     * Guarda un objeto de tipo T en la base.
     * @param obj el objeto a agregar. 
     */
    protected void save(T obj){
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
             session.save(obj);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)
                tx.rollback();
        }finally{
            session.close();
        }   
    }
    
    /**
     * Actualiza un objeto de tipo T en la base.
     * @param obj el objeto a actualizar.
     */
    protected void update(T obj){
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)
                tx.rollback();
        }finally{
            session.close();
        }
    }
    
    /**
     * Elimina un objeto de tipo T en la base.
     * @param obj el objeto a eliminar.
     */
    protected void delete(T obj){
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null)
                tx.rollback();
        }finally{
            session.close();
        }
    }
    
    /**
     * Busca un objeto de determinada clase, dado su identificador.
     * @param clazz la clase del objeto a buscar
     * @param id el identificador del objeto
     * @return el objeto, null si no está.
     */
    protected T find(Class clazz, Serializable id){
        T obj = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            obj = (T) session.get(clazz, id);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)
                tx.rollback();
        }finally{
            session.close();
        }
        return obj;
    }
    
    /**
     * Busca los objetos de determinada clase.
     * @param clazz la clase de objetos a buscar.
     * @return Una lista de todos los objetos en la clase.
     */
    protected List<T> findAll(Class clazz){
        List<T> obj = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from "+clazz.getName();
            Query query = session.createQuery(hql);
            obj = (List<T>) query.list();
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
