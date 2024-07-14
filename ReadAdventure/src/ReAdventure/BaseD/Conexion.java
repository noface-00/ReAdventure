package ReAdventure.BaseD;

import com.mysql.cj.xdevapi.Statement; // Import innecesario, ya que no se está utilizando
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 * Clase para gestionar la conexión a la base de datos MySQL.
 */
public class Conexion {
    Connection conectar; // Objeto Connection para la conexión
    Statement st; // Objeto Statement, innecesario en este contexto

    /**
     * Constructor de la clase. Establece la conexión a la base de datos.
     */
    public Conexion() {
        String host = "localhost"; // Dirección del servidor MySQL
        String puerto = "3306"; // Puerto de conexión
        String nameDB = "readadventure"; // Nombre de la base de datos
        String usuario = "root"; // Usuario de la base de datos
        String pass = ""; // Contraseña del usuario
        String driver = "com.mysql.cj.jdbc.Driver"; // Driver JDBC para MySQL
        String databaseURL = "jdbc:mysql://" + host + ":" + puerto + "/" + nameDB + "?useSSL=false"; // URL de conexión
        
        try {
            Class.forName(driver); // Cargar el driver JDBC
            conectar = DriverManager.getConnection(databaseURL, usuario, pass); // Establecer la conexión
        } catch (Exception ex) {
            // Capturar cualquier excepción que ocurra durante la conexión
            ex.printStackTrace(); // Imprimir el stack trace de la excepción (esto es básico, podrías querer hacer un manejo de errores más sofisticado)
            JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos"); // Mostrar un mensaje de error en caso de fallo
        }
    }
    
    /**
     * Método para obtener la conexión establecida.
     * @return Objeto Connection que representa la conexión a la base de datos.
     */
    public Connection getConexion() {
        return conectar; // Devuelve el objeto Connection establecido
    }
}
