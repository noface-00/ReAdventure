/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package readadventure;

import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author mkevi
 */
public class Conexion_sql {
        Connection conectar = null;
        String usuario = "root";
        String contraseña = "";
        String bd = "readventure";
        String ip = "localhost";
        String puerto = "3307";
        String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
        public Connection estableceConexion(){
            try{
                Class.forName("com.mysql.jdb.Driver");
                
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "No se conecto a la base de datos, error:"+ e.toString());
            }
        }
}
