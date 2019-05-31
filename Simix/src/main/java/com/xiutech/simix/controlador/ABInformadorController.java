/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiutech.simix.controlador;

import com.xiutech.simix.modelo.Administrador;
import com.xiutech.simix.modelo.AdministradorDAO;
import com.xiutech.simix.modelo.Comentarista;
import com.xiutech.simix.modelo.ComentaristaDAO;
import com.xiutech.simix.modelo.Informador;
import com.xiutech.simix.modelo.InformadorDAO;
import javax.faces.bean.ManagedBean;


/**
 * Clase controlador dedica a alta y baja de usuario con rol de Informador.
 * @author Jose Jhovan Gallardo Valdez
 * @version 11/05/19 
 */

@ManagedBean
public class ABInformadorController {
    private String nombre;
    private String correo;
    private String contrasenia;

    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * @param contrasenia the contrasenia to set
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    /**
    * Genera una cadena alfanumerica de longitud n
    *
    * @param n la longitud de la cadena a generar
    * @return una cadena alfanumerica aleatoria
    */
    private static String getAlphaNumericString(int n) 
    { 
  
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"
                                    + "!?&#%"; 
  
        // crea un stringBuilder del tamaño de la contraseña que queremos 
        StringBuilder sb = new StringBuilder(n); 
  
        // agrega n caracteres al azar
        for (int i = 0; i < n; i++) { 
  
            int index = (int)(AlphaNumericString.length() * Math.random()); 
  
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
    
    /**
    * Actualiza la contraseña del objeto con una contraseña aleatoria
    */
    private void generarContrasenia(){
        this.setContrasenia(getAlphaNumericString(10));
    }
    
    
    /**
    * Envia correo electronico con las credenciales del objeto
    * Se debe actualizar el token de acceso cada hora
    */    
    private void enviarCorreo() {
        SendingMailThroughGmailSMTPServer em = new SendingMailThroughGmailSMTPServer();
        String SMTP_SERVER_HOST = "smtp.gmail.com";
        String SMTP_SERVER_PORT = "587";
        String SUBJECT = "Tu cuenta de SIMIX se ha registrado correctamente";
        String BODY = "Hola, esas son tus credenciales:<br><br>Nombre: " + this.getNombre() + "<br>Contraseña: " + this.getContrasenia();
        String FROM_USER_EMAIL = "jhovan.simix@gmail.com";
        String FROM_USER_FULLNAME = "SIMIX";
        String CLIENT_ID = "";
        String CLIENT_SECRET = "";
        String REFRESH_TOKEN = "";
        //String FROM_USER_ACCESSTOKEN = em.getAccessTokenFromRefreshToken(CLIENT_ID,CLIENT_SECRET, REFRESH_TOKEN);
        String FROM_USER_ACCESSTOKEN = "yya29.Gl0ZB8M3AxLHZHMl4zEZw1w1Kg6NXSphP_coZLl8D2uo60dJ75nMLvbeR9IYmIga6nxS2UtYQ-sCBlrbQyTFr5XBF2E3laRG52PTOcb-5KURH8bl8GueL216VHWoLLo";
        String TO_USER_EMAIL = this.getCorreo();
        em.sendMail(SMTP_SERVER_HOST, SMTP_SERVER_PORT, FROM_USER_EMAIL, FROM_USER_ACCESSTOKEN, FROM_USER_EMAIL, FROM_USER_FULLNAME, TO_USER_EMAIL, SUBJECT, BODY);
    }
    
    /**
    * Da de alta a un informador en la base de datos
    *
    * @return el path de la vista "confirmacion_administrador" con un mensaje de confirmacion
    */
    public String darAltaInformador() {
        String mensaje = "El registro se llevó a cabo con exito y se ha enviado la contraseña al informador";
        String mensaje_error = "Ha ocurrido un error";
        
        try { 
            this.generarContrasenia();
            Informador i = new Informador();
            i.setNombre(getNombre());
            i.setCorreo(getCorreo());
            i.setContrasenia(getContrasenia());
            InformadorDAO udb = new InformadorDAO();
            
            // Verifica que no haya usuarios con el mismo correo, en caso contrario
            // lanza una excepcion
            ComentaristaDAO cudb = new ComentaristaDAO();
            Comentarista c2 = cudb.find(i.getCorreo());                       
            Informador i2 = udb.find(i.getCorreo());            
            AdministradorDAO audb = new AdministradorDAO();            
            Administrador a2 = audb.find(i.getCorreo());            
            if(c2!=null || i2!=null || a2 != null){
                mensaje_error = "Ya existe el usuario";
                throw new RuntimeException("Ya existe el usuario");
            }
            
            udb.save(i);
            enviarCorreo();
            return "ConfirmacionAdministradorIH?faces-redirect=true&mensaje=" + mensaje;
        }
        catch(Exception e){
            return "ConfirmacionAdministradorIH?faces-redirect=true&mensaje=" + mensaje_error;
        }
    }

}
