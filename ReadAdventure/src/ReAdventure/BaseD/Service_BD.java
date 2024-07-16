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
            try (PreparedStatement pps = conn.prepareStatement(sql)) {
                pps.setString(1, usuario.getNombre());
                pps.setString(2, usuario.getApellido());
                pps.setString(3, usuario.getEdad());
                pps.setString(4, usuario.getGenero());
                pps.setString(5, usuario.getCurso());
                pps.setString(6, usuario.getCedula());
                pps.setString(7, usuario.getContrasena());
                pps.executeUpdate();
                System.out.println("Registro exitoso");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al registrar: " + e.getMessage());
        }
    }

    public boolean insertloginUser(Usuarios_L usuario) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM users WHERE cedula=? AND contrasenna=?";
            try (PreparedStatement pps = conn.prepareStatement(sql)) {
                pps.setString(1, usuario.getUser());
                pps.setString(2, usuario.getClave());
                try (ResultSet rs = pps.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("Login exitoso");
                        return true;
                    } else {
                        System.out.println("Credenciales incorrectas");
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al intentar iniciar sesión: " + e.getMessage());
            return false;
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

    public void selectUserByField(String fieldValue) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pps = conn.prepareStatement("SELECT * FROM users WHERE cedula = ? OR nombre = ? OR apellido = ?")) {
            pps.setString(1, fieldValue);
            pps.setString(2, fieldValue);
            pps.setString(3, fieldValue);
            try (ResultSet rs = pps.executeQuery()) {
                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    String edad = rs.getString("edad");
                    String sexo = rs.getString("sexo");
                    String curso = rs.getString("curso");
                    String cedula = rs.getString("cedula");
                    String contrasenna = rs.getString("contrasenna");
                    System.out.println("Nombre: " + nombre + ", Apellido: " + apellido + ", Edad: " + edad +
                                       ", Sexo: " + sexo + ", Curso: " + curso + ", Cedula: " + cedula +
                                       ", Contrasena: " + contrasenna);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener los usuarios: " + e.getMessage());
        }
    }

    public void updatePasswordByCedula(String cedula, String nuevaContrasenna) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pps = conn.prepareStatement("UPDATE users SET contrasenna = ? WHERE cedula = ?")) {
            pps.setString(1, nuevaContrasenna);
            pps.setString(2, cedula);
            int rowsUpdated = pps.executeUpdate();
            if (rowsUpdated > 0) {
            } else {
                System.out.println("No se encontró el usuario con la cédula proporcionada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al actualizar la contraseña: " + e.getMessage());
        }
    }
}
