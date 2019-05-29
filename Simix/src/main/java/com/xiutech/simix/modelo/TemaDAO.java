/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiutech.simix.modelo;

import java.util.List;

/**
 * Clase para manejar datos de tabla ATema en la base.
 * @author Jose Fernando Reyes Garcia
 * @version 04/04/19
 */
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
    
    /**
    public List<Tema> buscaPorInf(String informador) {
        List<Tema> temas = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from Tema where correo_informador = :inf";
            Query query = session.createQuery(hql);
            query.setParameter("inf", informador);
            temas = (List<Tema>) query.list();
            tx.commit();
            
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return temas;
    } */
}
