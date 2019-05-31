package com.xiutech.simix.modelo;

import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Clase para manejar datos de tabla ATema en la base.
 * @author Jose Fernando Reyes Garcia
 * @version 04/04/19
 */
@ManagedBean
public class TemaDAO extends AbstractDAO<Tema>{
    
    /**
     * Constructor default. Usa el constructor de AbstractDAO.
     */
    public TemaDAO(){
        super();
    }
    
    /**
     * Agrega un Tema en la base.
     * @param tema El tema a añadir.
     */
    @Override
    public void save(Tema tema){
        super.save(tema);
    }
    
    /**
     * Actualiza un Tema en la base.
     * @param tema El tema a actualizar.
     */
    @Override
    public void update(Tema tema){
        super.update(tema);
    }
    
    /**
     * Elimina un Tema en la base.
     * @param tema El tema a eliminar.
     */
    @Override
    public void delete(Tema tema){
        super.delete(tema);
    }
    
    /**
     * Consulta un Tema en la base.
     * @param nombre El tema a consultar.
     * @return  El Tema construido con datos en la base. Null si no existe.
     */
    public Tema find(String nombre){
        return super.find(Tema.class, nombre);
    }
    
    /**
     * Consulta de todos los temas en la base.
     * @return Una lista de los temas en la base.
     */
    public List<Tema> findAll(){
        return super.findAll(Tema.class);
    }
    
    public List<String> parecidos(String tema_buscado){
        List<Tema> obj = null;
        List<String> nombres = new LinkedList<>();
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from Tema where nombre like ':tema%'";
            Query query = session.createQuery(hql);
            query.setParameter("tema", tema_buscado);
            obj = (List<Tema>) query.list();
            for(Tema t: obj){
            nombres.add(t.getNombre());
        }
            tx.commit();
        }catch(HibernateException e){
            if(tx != null)
                tx.rollback();
        }finally{
            session.close();
        }
        return nombres;
    }
    
    
    public List<String> todosLosTemas(){
        List<Tema> temas = this.findAll();
        List<String> nombres = new LinkedList<>();
        for(Tema t: temas){
            nombres.add(t.getNombre());
        }
        return nombres;
    }
}
