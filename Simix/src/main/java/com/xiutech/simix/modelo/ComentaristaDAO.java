package com.xiutech.simix.modelo;

import java.util.List;
import javax.faces.bean.ManagedBean;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Clase para manejar datos de tabla Comentarista en la base.
 * @author Jose Fernando Reyes Garcia
 * @version 04/04/19
 */
@ManagedBean    
public class ComentaristaDAO extends AbstractDAO<Comentarista>{
    
    /**
     * Constructor default. Usa el constructor de AbstractDAO.
     */
    public ComentaristaDAO(){
        super();
    }
    
    /**
     * Guarda un Comentarista en la base.
     * @param comentarista El Comentarista a agregar.
     */
    @Override
    public void save(Comentarista comentarista){
        super.save(comentarista);
    }
    
    /**
     * Actualiza un Comentarista en la base.
     * @param comentarista el Comentarista a actualizar.
     */
    @Override
    public void update(Comentarista comentarista){
        super.update(comentarista);
    }
    
    /**
     * Elimina un Comentarista en la base.
     * @param comentarista el Comentarista a eliminar.
     */
    @Override
    public void delete(Comentarista comentarista){
        super.delete(comentarista);
    }
    
    /**
     * Busca un Comentarista, dado su correo.
     * @param correo el correo del Comentarista.
     * @return el Comentarista, null si no está.
     */
    public Comentarista find(String correo){
        return super.find(Comentarista.class, correo);
    }
    
    /**
     * Busca todos los comentaristas en la base.
     * @return Una lista de todos los comentaristas en la clase.
     */
    public List<Comentarista> findAll(){
        return super.findAll(Comentarista.class);
    }
    
    /**
     * Busca los comentaristas con este nombre de usuario.
     * @param nombre el nombre de usuario.
     * @return la lista de todos los comentaristas con este nombre de usuario.
     */
    public List<Comentarista> buscaPorNombre(String nombre){
        List<Comentarista> usuarios =null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "From Comentarista  u where u.nombre like concat('%',:nombre,'%')";
            Query query = session.createQuery(hql);
            query.setParameter("nombre", nombre);
            usuarios = (List<Comentarista>)query.list();
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
     * Busca un Comentarista, dados su correo y su contraseña.
     * @param correo el correo del Comentarista.
     * @param contrasenia la contraseña del Comentarista.
     * @return el Comentarista correspondiiente, null si no está.
     */
    public Comentarista buscaPorCorreoContrasenia(String correo,String contrasenia){
        Comentarista u =null;
        Session session = this.sessionFactory.openSession();
        Transaction tx =null;
        try{
            tx = session.beginTransaction();
            String hql = "from Comentarista where correo = :correo and contrasenia = :contrasenia";
            Query query = session.createQuery(hql);
            query.setParameter("correo", correo);
            query.setParameter("contrasenia",contrasenia);
            u = (Comentarista)query.uniqueResult();
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
     * Busca un Comentarista, dado su correo.
     * @param correo el correo del Comentarista.
     * @return el Comentarista, null si no está.
     */
    public Comentarista buscaPorCorreo(String correo){
        Comentarista u =null;
        Session session = this.sessionFactory.openSession();
        Transaction tx =null;
        try{
            tx = session.beginTransaction();
            String hql = "from Comentarista where correo = :correo";
            Query query = session.createQuery(hql);
            query.setParameter("correo", correo);
            u = (Comentarista)query.uniqueResult();
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
