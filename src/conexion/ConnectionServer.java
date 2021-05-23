/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
/**
 *
 * @author Usuario
 */
public class ConnectionServer {
      Connection conexion = null;
    String url = "jdbc:sqlserver://DESKTOP-I75HOTA:1433;"
                + "database=Login;"
                + "user=sa;"
                + "password=password;";
    public void conectar(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conexion = DriverManager.getConnection(url);} 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar"+e.getMessage(),e.getMessage(),JOptionPane.ERROR_MESSAGE);
        }
    }   
    public String Select(){
        String texto="";
        try {
            Statement ejecutor= conexion.createStatement();
            ResultSet respuesta = ejecutor.executeQuery("Select * from Usuarios");
            while(respuesta.next()){
                texto = texto + respuesta.getString("Nombre")+" | "+respuesta.getString("Contraseña")+"\n";
            }
        } catch (Exception e) {
        }
        return texto;
    }   
    public String Selectfromquery(String Query){
        String texto="";
        try {
            Statement ejecutor= conexion.createStatement();
            ResultSet respuesta = ejecutor.executeQuery(Query);
            while(respuesta.next()){
                texto = texto + respuesta.getString("Nombre")+" | "+respuesta.getString("Contraseña")+"\n";
            }
        } catch (Exception e) {
        }
        return texto;
    }
    public int Login(String User, String Password){
        Integer resultado = 0;
        try {
            Statement ejecutor = conexion.createStatement();
            ResultSet rs = ejecutor.executeQuery("Select * from Usuarios Where Nombre = '"+User+"' and Contraseña = '"+Password+"'");
            
            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Bienvenido");
                resultado=1;
            } else{
                JOptionPane.showMessageDialog(null, "Problemas con usuario y/o clave");
                resultado=0;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar"+e.getMessage(),e.getMessage(),JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }
}
