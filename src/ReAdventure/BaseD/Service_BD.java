package ReAdventure.BaseD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Service_BD {
    private static final String URL = "jdbc:mysql://localhost:3306/readadventure?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public void insertRegisterUser(Usuarios usuario) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO users(nombre, apellido, edad, sexo, curso, cedula, contrasenna) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pps = conn.prepareStatement(sql);
            
            pps.setString(1, usuario.getNombre());
            pps.setString(2, usuario.getApellido());
            pps.setString(3, usuario.getEdad());
            pps.setString(4, usuario.getGenero());
            pps.setString(5, usuario.getCurso());
            pps.setString(6, usuario.getCedula());
            pps.setString(7, usuario.getContrasena());

            pps.executeUpdate();
            System.out.println("Registro exitoso");
            

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al registrar: " + e.getMessage());
        }
    }
         public void insertloginUser(Usuarios_L usuario){
       try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

        // Preparar la consulta SQL con PreparedStatement para evitar SQL injection
        String sql = "SELECT * FROM users WHERE cedula=? AND contrasenna=?";
        PreparedStatement pps = conn.prepareStatement(sql);
            pps.setString(1, usuario.getUser());
            pps.setString(2, usuario.getClave());

        // Ejecutar la consulta y obtener resultados
        ResultSet rs = pps.executeQuery();

        // Verificar si se encontró un resultado
        if (rs.next()) {
            System.out.println("Login exitoso");
           
        } else {
            System.out.println("Credenciales incorrectas");
        }

        // Cerrar el ResultSet, PreparedStatement y Connection
        rs.close();
        pps.close();
        conn.close();

    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error al intentar iniciar sesión: " + e.getMessage());
    }
        
    }
    
    public boolean checkDuplicateID(String cedula) {
        boolean duplicate = false;
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pps = conn.prepareStatement("SELECT COUNT(*) FROM users WHERE cedula = ?")) {
        
            pps.setString(1, cedula);
            
            try (ResultSet rs = pps.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    duplicate = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return duplicate;
    }
}
