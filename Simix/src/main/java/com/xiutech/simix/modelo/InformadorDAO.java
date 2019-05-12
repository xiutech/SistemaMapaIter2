/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiutech.simix.modelo;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Clase para manejar datos de tabla Informador en la base.
 * @author Jose Fernando Reyes Garcia
 * @version 04/04/19
 */
public class InformadorDAO extends AbstractDAO<Informador>{
    
    /**
     * Constructor default. Usa el constructor de AbstractDAO.
     */
    public InformadorDAO(){
        super();
    }
    
    /**
     * Guarda un Informador en la base.
     * @param informador El Informador a agregar.
     */
    @Override
    public void save(Informador informador){
        super.save(informador);
    }
    
    /**
     * Actualiza un Informador en la base.
     * @param informador el Informador a actualizar.
     */
    @Override
    public void update(Informador informador){
        super.save(informador);
    }
    
    /**
     * Elimina un Informador en la base.
     * @param informador el Informador a eliminar.
     */
    @Override
    public void delete(Informador informador){
        super.delete(informador);
    }
       
    /**
     * Busca un Informador, dado su correo.
     * @param id el correo del Informador
     * @return el Informador, null si no está.
     */
    public Informador find(String correo){
        return super.find(Informador.class, correo);
    }
    
    /**
     * Busca todos los informadores en la base
     * @return Una lista de todos los informadores en la clase.
     */
    public List<Informador> findAll(){
        return super.findAll(Informador.class);
    } 
    
    /**
     * Busca los informadores con este nombre de usuario.
     * @param nombre el nombre de usuario.
     * @return la lista de todos los informadores con este nombre de usuario.
     */
    public List<Informador> buscaPorNombre(String nombre){
        List<Informador> usuarios =null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "From Informador  u where u.nombre like concat('%',:nombre,'%')";
            Query query = session.createQuery(hql);
            query.setParameter("nombre", nombre);
            usuarios = (List<Informador>)query.list();
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return usuarios;
    }
    
    /**
     * Busca un Informador, dados su correo y su contraseña.
     * @param correo el correo del Informador.
     * @param contrasenia la contraseña del Informador.
     * @return el Informador correspondiiente, null si no está.
     */
    public Informador buscaPorCorreoContrasenia(String correo,String contrasenia){
        Informador u =null;
        Session session = this.sessionFactory.openSession();
        Transaction tx =null;
        try{
            tx = session.beginTransaction();
            String hql = "from Informador where correo = :correo and contrasenia = :contrasenia";
            Query query = session.createQuery(hql);
            query.setParameter("correo", correo);
            query.setParameter("contrasenia",contrasenia);
            u = (Informador)query.uniqueResult();
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return u;
    }
    
    /**
     * Busca un Informador, dado su correo.
     * @param correo el correo del Informador.
     * @return el Informador, null si no está.
     */
    public Informador buscaPorCorreo(String correo){
        Informador u =null;
        Session session = this.sessionFactory.openSession();
        Transaction tx =null;
        try{
            tx = session.beginTransaction();
            String hql = "from Informador where correo = :correo";
            Query query = session.createQuery(hql);
            query.setParameter("correo", correo);
            u = (Informador)query.uniqueResult();
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return u;
    }
}
