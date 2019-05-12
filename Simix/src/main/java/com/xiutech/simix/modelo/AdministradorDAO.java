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
 * Clase para manejar datos de tabla Administrador en la base.
 * @author Jose Fernando Reyes Garcia
 * @version 04/04/19
 */
public class AdministradorDAO extends AbstractDAO<Administrador>{
    
    /**
     * Constructor default. Usa el constructor de AbstractDAO.
     */
    public AdministradorDAO(){
        super();
    }
    
    /**
     * Guarda un Administrador en la base.
     * @param admin El Administrador a agregar.
     */
    @Override
    public void save(Administrador admin){
        super.save(admin);
    }
    
    /**
     * Actualiza un Administrador en la base.
     * @param admin El Administrador a actualizar.
     */
    @Override
    public void update(Administrador admin){
        super.update(admin);
    }
    
    /**
     * Elimina un Administrador en la base.
     * @param admin El Administrador a eliminar.
     */
    @Override
    public void delete(Administrador admin){
        super.delete(admin);
    }
    
    /**
     * Busca un Administrador, dado su correo.
     * @param correo el correo del Administrador.
     * @return el Administrador, null si no está.
     */
    public Administrador find(String correo){
        return super.find(Administrador.class, correo);
    }
    
    /**
     * Busca todos los administradores en la base.
     * @return Una lista de todos los administradores en la base. 
     */
    public List<Administrador> findAll(){
        return super.findAll(Administrador.class);
    }
    
    /**
     * Busca los administradores con este nombre de usuario.
     * @param nombre el nombre de usuario.
     * @return la lista de todos los administradores con este nombre de usuario.
     */
    public List<Administrador> buscaPorNombre(String nombre){
        List<Administrador> usuarios =null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "From Administrador  u where u.nombre like concat('%',:nombre,'%')";
            Query query = session.createQuery(hql);
            query.setParameter("nombre", nombre);
            usuarios = (List<Administrador>)query.list();
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
     * Busca un Administrador, dados su correo y su contraseña.
     * @param correo el correo del Administrador.
     * @param contrasenia la contraseña del Administrador.
     * @return el Administrador correspondiiente, null si no está.
     */
    public Administrador buscaPorCorreoContrasenia(String correo,String contrasenia){
        Administrador u =null;
        Session session = this.sessionFactory.openSession();
        Transaction tx =null;
        try{
            tx = session.beginTransaction();
            String hql = "from Administrador where correo = :correo and contrasenia = :contrasenia";
            Query query = session.createQuery(hql);
            query.setParameter("correo", correo);
            query.setParameter("contrasenia",contrasenia);
            u = (Administrador)query.uniqueResult();
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
     * Busca un Administrador, dado su correo.
     * @param correo el correo del Administrador.
     * @return el Administrador, null si no está.
     */
    public Administrador buscaPorCorreo(String correo){
        Administrador u =null;
        Session session = this.sessionFactory.openSession();
        Transaction tx =null;
        try{
            tx = session.beginTransaction();
            String hql = "from Administrador where correo = :correo";
            Query query = session.createQuery(hql);
            query.setParameter("correo", correo);
            u = (Administrador)query.uniqueResult();
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
