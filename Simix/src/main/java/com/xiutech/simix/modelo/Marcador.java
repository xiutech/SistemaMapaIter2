package com.xiutech.simix.modelo;
// Generated 29-mar-2019 11:00:45 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Marcador generated by hbm2java
 */
public class Marcador  implements java.io.Serializable {

     private int idMarcador;
     private Tema tema;
     private double longitud;
     private double latitud;
     private String descripcion;
     private String datosUtiles;
     private Set comentarios = new HashSet(0);

    public Marcador() {
    }

	
    public Marcador(int idMarcador, double longitud, double latitud, String descripcion, String datosUtiles) {
        this.idMarcador = idMarcador;
        this.longitud = longitud;
        this.latitud = latitud;
        this.descripcion = descripcion;
        this.datosUtiles = datosUtiles;
    }
    public Marcador(int idMarcador, Tema tema, double longitud, double latitud, String descripcion, String datosUtiles, Set comentarios) {
       this.idMarcador = idMarcador;
       this.tema = tema;
       this.longitud = longitud;
       this.latitud = latitud;
       this.descripcion = descripcion;
       this.datosUtiles = datosUtiles;
       this.comentarios = comentarios;
    }
   
    public int getIdMarcador() {
        return this.idMarcador;
    }
    
    public void setIdMarcador(int idMarcador) {
        this.idMarcador = idMarcador;
    }
    public Tema getTema() {
        return this.tema;
    }
    
    public void setTema(Tema tema) {
        this.tema = tema;
    }
    public double getLongitud() {
        return this.longitud;
    }
    
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    public double getLatitud() {
        return this.latitud;
    }
    
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDatosUtiles() {
        return this.datosUtiles;
    }
    
    public void setDatosUtiles(String datosUtiles) {
        this.datosUtiles = datosUtiles;
    }
    public Set getComentarios() {
        return this.comentarios;
    }
    
    public void setComentarios(Set comentarios) {
        this.comentarios = comentarios;
    }




}


