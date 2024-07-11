package Swing.panels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;
import ReAdventure.Conexion;
import swing.controls.Button;
import swing.controls.MyCombo_Box;
import swing.controls.MyPasswordField;
import swing.controls.MyTextField;
import ReAdventure.Conexion;
import javax.swing.text.AbstractDocument;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author PC
 */
public class Login_Register extends javax.swing.JPanel {
    private Conexion con;
    private JButton B_register;
    private Button B_login;
    private MyTextField Nombre;
    private MyTextField Apellido;
    private MyTextField Cedula;
    private MyTextField Edad;
    private MyCombo_Box Curso;
    private MyCombo_Box Sexo;
    private MyTextField Contrasena;
    private MyTextField Usuario;
    private MyPasswordField Clave;
    
    
    public Login_Register() {
        initComponents();
        initRegister();
        initLogin();
        Login.setVisible(false);
        Register.setVisible(true);
        con = new Conexion(); // Inicialización de la conexión
    }
    
  

    
    private void initRegister() {
        Register.setLayout(new MigLayout("wrap", "push[center]push", "push[]push"));
        JLabel Titulo = new JLabel("Crear Cuenta");
        Titulo.setFont(new Font("Roboto Black", 1, 60));
        Titulo.setForeground(new Color(7, 164, 121));
        Register.add(Titulo);

        Nombre = new MyTextField();
        Nombre.setHint("Nombre");
        Nombre.setPrefixIcon(new ImageIcon(getClass().getResource("/icons/readventure/Person.png")));
        Register.add(Nombre, "w 60%");
         Nombre.addKeyListener(new KeyListener() {
        @Override
            public void keyTyped(KeyEvent e) {
             NombreKeyTyped(e);
            }
        @Override
            public void keyPressed(KeyEvent e) {
            // Método requerido por la interfaz KeyListener
            }
        @Override
           public void keyReleased(KeyEvent e) {
        // Método requerido por la interfaz KeyListener
    }
    });

        Apellido = new MyTextField();
        Apellido.setHint("Apellido");
        Apellido.setPrefixIcon(new ImageIcon(getClass().getResource("/icons/readventure/Person.png")));
        Register.add(Apellido, "w 60%");
        Apellido.addKeyListener(new KeyListener() {
        @Override
            public void keyTyped(KeyEvent e) {
             ApellidoKeyTyped(e);
            }
        @Override
            public void keyPressed(KeyEvent e) {
            // Método requerido por la interfaz KeyListener
            }
        @Override
           public void keyReleased(KeyEvent e) {
        // Método requerido por la interfaz KeyListener
    }
    });

        Cedula = new MyTextField();
        Cedula.setHint("Cedula");
        Cedula.setPrefixIcon(new ImageIcon(getClass().getResource("/icons/readventure/Cedula.png")));
        Register.add(Cedula, "w 60%");
        Cedula.addKeyListener(new KeyListener() {
        @Override
            public void keyTyped(KeyEvent e) {
             CedulaKeyTyped(e);
            }
        @Override
            public void keyPressed(KeyEvent e) {
            // Método requerido por la interfaz KeyListener
            }
        @Override
           public void keyReleased(KeyEvent e) {
        // Método requerido por la interfaz KeyListener
    }
    });
        
        Contrasena = new MyTextField(); 
        Contrasena.setHint("Contraseña");
        Contrasena.setPrefixIcon(new ImageIcon(getClass().getResource("/icons/readventure/Contrasena.png")));
        Register.add(Contrasena, "w 60%");

        Edad = new MyTextField();
        Edad.setHint("Edad");
        Edad.setPrefixIcon(new ImageIcon(getClass().getResource("/icons/readventure/Edad.png")));
        Register.add(Edad, "w 60%");
        
        Edad.addKeyListener(new KeyListener() {
        @Override
            public void keyTyped(KeyEvent e) {
             EdadKeyTyped(e);
            }
        @Override
            public void keyPressed(KeyEvent e) {
            // Método requerido por la interfaz KeyListener
            }
        @Override
           public void keyReleased(KeyEvent e) {
        // Método requerido por la interfaz KeyListener
    }
    });

        Curso = new MyCombo_Box();
        Curso.addItem("Curso");
        Curso.addItem("3ro");
        Curso.addItem("4to");
        Curso.setHint("Cursos");
        Curso.setBackground(new Color(236, 87, 0));
        Curso.setSelectionBackground(new Color(236, 87, 0));
        Register.add(Curso, "w 60%, h 40");

        Sexo = new MyCombo_Box();
        Sexo.addItem("Género");
        Sexo.addItem("Masculino");
        Sexo.addItem("Femenino");
        Sexo.setHint("Género");
        Sexo.setBackground(new Color(44, 131, 192));
        Sexo.setSelectionBackground(new Color(44, 131, 192));
        Register.add(Sexo, "w 60%, h 40");

        
        B_register = new JButton();  // Cambio aquí: utilizar JButton en lugar de Button
        B_register.setBackground(new Color(7, 164, 121));
        B_register.setForeground(new Color(250, 250, 250));
        B_register.setText("Registrarse");
        Register.add(B_register, "w 60%, h 40");
        B_register.addActionListener(this::B_registerActionPerformed);




    }
    
    private void CedulaKeyTyped(java.awt.event.KeyEvent evt){
       char key = evt.getKeyChar();
    if (!(Character.isDigit(key) || key == KeyEvent.VK_BACK_SPACE)) {
        evt.consume(); // Consume el evento para evitar que el carácter sea ingresado
    } else if (Cedula.getText().length() >= 10) {
        evt.consume(); // Limita la longitud del texto a 10 caracteres
    }
    }
    private void EdadKeyTyped(java.awt.event.KeyEvent evt){
        int key=evt.getKeyChar();
        boolean numero = key >=48 && key <=57;
    
        if (!numero){
        
            evt.consume();
        
        }
    }
    private void NombreKeyTyped(java.awt.event.KeyEvent evt){
        char key = evt.getKeyChar();
        if (!(Character.isLetter(key) || key == KeyEvent.VK_BACK_SPACE)) {
        evt.consume(); // Consume el evento para evitar que el carácter sea ingresado
    }
    }
    private void ApellidoKeyTyped(java.awt.event.KeyEvent evt){
        char key = evt.getKeyChar();
        if (!(Character.isLetter(key) || key == KeyEvent.VK_BACK_SPACE)) {
        evt.consume(); // Consume el evento para evitar que el carácter sea ingresado
    }
    }
    
    private void B_registerActionPerformed(java.awt.event.ActionEvent evt){
        System.out.println("ta");
        limpiarCamposRegister();
       try {
           String nombre = Nombre.getText();
        String apellido = Apellido.getText();
        String cedula = Cedula.getText();
        String edad = Edad.getText();
        String curso = Curso.getSelectedItem().toString();
        String genero = Sexo.getSelectedItem().toString();
        String contrasenna = Contrasena.getText(); 
            Connection conn = con.getConexion();
            // Preparar la consulta SQL para insertar el usuario
             String sql = "INSERT INTO users(nombre, apellido, edad, sexo, curso, cedula, contrasenna) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pps = conn.prepareStatement(sql);
            pps.setString(1, nombre);
            pps.setString(2, apellido);
            pps.setString(3, edad);
            pps.setString(4, genero);
            pps.setString(5, curso);
            pps.setString(6, cedula);
            pps.setString(7, contrasenna);

            // Ejecutar la consulta
            pps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Registro exitoso");
            limpiarCamposRegister();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al registrar: " + e.getMessage());
        }
       
        
    }
    
   
  private void limpiarCamposRegister() {
        Nombre.setText("");
        Apellido.setText("");
        Edad.setText("");
        Cedula.setText("");
        Contrasena.setText("");
        Curso.setSelectedIndex(0);
        Sexo.setSelectedIndex(0);
    }
  
   private void limpiarCamposLogin() {
        Usuario.setText("");
        Clave.setText("");
    }
  
    private void initLogin(){
         Login.setLayout(new MigLayout("wrap", "push[center]push", "push[]push"));
    JLabel Titulo = new JLabel("Bienvenido");
    Titulo.setFont(new Font("Roboto Black", 1, 60));
    Titulo.setForeground(new Color(7, 164, 121));
    Login.add(Titulo);

    Usuario = new MyTextField();  // Initialize class field
    Usuario.setHint("Cedula");
    Usuario.setPrefixIcon(new ImageIcon(getClass().getResource("/icons/readventure/Cedula.png")));
    Login.add(Usuario, "w 60%");
     Usuario.addKeyListener(new KeyListener() {
        @Override
            public void keyTyped(KeyEvent e) {
             UsuarioKeyTyped(e);
            }
        @Override
            public void keyPressed(KeyEvent e) {
            // Método requerido por la interfaz KeyListener
            }
        @Override
           public void keyReleased(KeyEvent e) {
        // Método requerido por la interfaz KeyListener
    }
    });

    Clave = new MyPasswordField();  // Initialize class field
    Clave.setHint("Contraseña");
    Clave.setPrefixIcon(new ImageIcon(getClass().getResource("/icons/readventure/Contrasena.png")));
    Login.add(Clave, "w 60%");        

    JButton B_forget = new JButton("¿Olvidó su contraseña?");
    B_forget.setBackground(new Color(100, 100, 100));
    B_forget.setFont(new Font("Roboto Black", 1, 12));
    B_forget.setContentAreaFilled(false);
    B_forget.setCursor(new Cursor(Cursor.HAND_CURSOR));
    Login.add(B_forget);

    B_login = new Button();  // Assuming B_login is a class field
    B_login.setBackground(new Color(7, 164, 121));
    B_login.setForeground(new Color(250, 250, 250));
    B_login.setText("Iniciar sesión");
    Login.add(B_login, "w 60%, h 40");
    B_login.addActionListener(this::B_loginActionPerformed);
    }
     private void UsuarioKeyTyped(java.awt.event.KeyEvent evt){
        char key = evt.getKeyChar();
    if (!(Character.isDigit(key) || key == KeyEvent.VK_BACK_SPACE)) {
        evt.consume(); // Consume el evento para evitar que el carácter sea ingresado
    } else if (Usuario.getText().length() >= 10) {
        evt.consume(); // Limita la longitud del texto a 10 caracteres
    }
    }
    
    
     private void B_loginActionPerformed(java.awt.event.ActionEvent evt){
         limpiarCamposLogin();
       try {
        Connection conn = con.getConexion();
        String usuario = Usuario.getText();
        String clave = new String(Clave.getPassword());

        // Preparar la consulta SQL con PreparedStatement para evitar SQL injection
        String sql = "SELECT * FROM users WHERE cedula=? AND contrasenna=?";
        PreparedStatement pps = conn.prepareStatement(sql);
        pps.setString(1, usuario);
        pps.setString(2, clave);

        // Ejecutar la consulta y obtener resultados
        ResultSet rs = pps.executeQuery();

        // Verificar si se encontró un resultado
        if (rs.next()) {
            JOptionPane.showMessageDialog(this, "Login exitoso");
            limpiarCamposLogin();
           
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
        }

        // Cerrar el ResultSet, PreparedStatement y Connection
        rs.close();
        pps.close();
        conn.close();

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al intentar iniciar sesión: " + e.getMessage());
    }
        
    }
    public void showLogin(boolean show){
         if (show) {
            Register.setVisible(true);
            Login.setVisible(false);
        } else {
            Register.setVisible(false);
            Login.setVisible(true);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Login = new javax.swing.JPanel();
        Register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        Login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout LoginLayout = new javax.swing.GroupLayout(Login);
        Login.setLayout(LoginLayout);
        LoginLayout.setHorizontalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        LoginLayout.setVerticalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(Login, "card3");

        Register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout RegisterLayout = new javax.swing.GroupLayout(Register);
        Register.setLayout(RegisterLayout);
        RegisterLayout.setHorizontalGroup(
            RegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        RegisterLayout.setVerticalGroup(
            RegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(Register, "card2");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Login;
    private javax.swing.JPanel Register;
    // End of variables declaration//GEN-END:variables
}
