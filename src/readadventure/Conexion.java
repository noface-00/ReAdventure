
package readadventure;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


/**
 *
 * @author mkevi
 */
public class Conexion {
    Connection conectar;
    
    public Conexion(){
        String host = "localhost";
        String puerto = "3306";
        String nameDB = "readadventure";
        String usuario = "root";
        String pass = "";
        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseURL = "jdbc:mysql://"+host+":"+puerto+"/"+nameDB+"?useSSL=false";
        
        try{
            Class.forName(driver);
            conectar = DriverManager.getConnection(databaseURL, usuario, pass);
        }catch(Exception ex){
        
        }
        
    }
    
    public Connection getConexion(){
    
        return conectar;
    
    }
}
