/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiutech.simix.controlador;

import com.xiutech.simix.modelo.Informador;
import com.xiutech.simix.modelo.InformadorDAO;
import com.xiutech.simix.modelo.Tema;
import com.xiutech.simix.modelo.TemaDAO;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
/**
 * Calse controlador dedicada al alta de temas en el sistema. 
 * @author Jose Fernando Reyes Garcia
 * @version 11/05/19 
 */
@ViewScoped
@ManagedBean
public class ABTemaController {    
    private String nombre;
    private String color;
    
    /**
     * Obtener el nombre del tema.
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modificar el nombre del tema.
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Agrega un tema a la base.
     * @return el url de redireccionamiento, en este caso, para agregar un marcador.
     */
    public String agregaTema(){
        TemaDAO udbT = new TemaDAO();
        Tema tema = udbT.find(nombre);
        if(tema != null){
            Mensajes.error("El tema ya existe. \n Añade tus marcadores al tema existente");
            return "";
        }
        InformadorDAO infDAO = new InformadorDAO();        
        SessionController.UserLogged usuario = (SessionController.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        Informador informador = infDAO.find(usuario.getCorreo());
        tema = new Tema(nombre, this.color);
        tema.setInformador(informador);
        udbT.save(tema);
        //creaIcono(50,50);
        return ("/informador/AgregarMarcadorTemaIH?faces-redirect=true&tema=" + nombre);
    }    
    /**
     * Metodo para crear el icono para los marcadores de este tema.
     * @param largo el largo del icono
     * @param ancho el ancho del icono
     */
    private void creaIcono(int largo,int ancho){
        String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n";
        s += "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n";
			s += "<svg width=\""+largo+"\" height=\""+ancho+"\" version=\"1.1\" id=\"Capa_1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"0px\" y=\"0px\" style=\"enable-background:new 0 0 512 512;\" xml:space=\"preserve\">\n<g>\n";
        int x =largo/2;
        int y = (ancho/3);
        int radio = ((largo+ancho)/2)/4;

        int[] p ={x-radio,y,x+radio,y,x,(y*3)};
        s += creaPoligono(p,"#" + this.color);
        s += creaCirculo(x,y,radio,"#" + this.color,true);
        s += creaCirculo(x,y,radio/2,"black",true);
        s+="</g>\n"+"</svg>";
        
        try {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String destino = (servletContext.getRealPath("/")) + "resources/images/";
            FileOutputStream fileOut = new FileOutputStream(new File(destino + this.nombre + ".svg"));
            OutputStreamWriter osOut = new OutputStreamWriter(fileOut);
            BufferedWriter out = new BufferedWriter(osOut);
            out.write(s);
            out.close();
        } catch (IOException ioe) {
            System.out.println("No pude guardar en el archivo" );
            Mensajes.fatal("No se pudo crear el archivo");
        }
    }

    private String creaCirculo(int x ,int y , int r,String color,boolean stroke){
        String s = stroke ? "<circle cx=\""+x+"\" cy=\"" +y+"\"  r=\"" + r + "\" stroke=\"white\" stroke-width=\"1\"  fill=\"" + color + "\" />\n" : "<circle cx=\""+x+"\" cy=\"" +y+"\"  r=\"" + r + "\" stroke=\"black\" stroke-width=\"0\"  fill=\"" + color + "\" />\n";
        return  s;

    }

    private String creaPoligono(int[] puntos,String color){
        String p = "";
        if(puntos.length%2 != 0)
          return "Los puntos estan mal";
        for(int i=0;i<puntos.length;i+=2){
          p+=puntos[i]+","+puntos[i+1]+" ";
        }
        return "<polygon points=\""+p+"\" \n style=\" fill:" +color+";stroke:black;stroke-width:1;\" /> \n";
    }    
}
