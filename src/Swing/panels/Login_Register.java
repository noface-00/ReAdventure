package Swing.panels;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;
import readadventure.Conexion;
import swing.controls.Button;
import swing.controls.MyCombo_Box;
import swing.controls.MyPasswordField;
import swing.controls.MyTextField;
import readadventure.Conexion;

/**
 *
 * @author PC
 */
public class Login_Register extends javax.swing.JPanel {
    private Conexion con;
    private Button B_register;
    private MyTextField Nombre;
    private MyTextField Apellido;
    private MyTextField Cedula;
    private MyTextField Edad;
    private MyCombo_Box Curso;
    private MyCombo_Box Sexo;
    
    
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
        Titulo.setFont(new Font("Roboto Black", 1, 30));
        Titulo.setForeground(new Color(7, 164, 121));
        Register.add(Titulo);

        Nombre = new MyTextField();
        Nombre.setHint("Nombre");
        Register.add(Nombre, "w 60%");

        Apellido = new MyTextField();
        Apellido.setHint("Apellido");
        Register.add(Apellido, "w 60%");

        Cedula = new MyTextField();
        Cedula.setHint("Cedula");
        Register.add(Cedula, "w 60%");

        Edad = new MyTextField();
        Edad.setHint("Edad");
        Register.add(Edad, "w 60%");

        Curso = new MyCombo_Box();
        Curso.addItem("Curso");
        Curso.addItem("3ro");
        Curso.addItem("4to");
        Curso.setHint("Cursos");
        Curso.setBackground(new Color(7, 164, 121));
        Curso.setSelectionBackground(new Color(250, 250, 250));
        Register.add(Curso, "width 30%, height 30lp, align right");

        Sexo = new MyCombo_Box();
        Sexo.addItem("Género");
        Sexo.addItem("Masculino");
        Sexo.addItem("Femenino");
        Sexo.setHint("Género");
        Sexo.setBackground(new Color(7, 164, 121));
        Sexo.setSelectionBackground(new Color(250, 250, 250));
        Register.add(Sexo, "width 30%, height 30lp, align left");

        Button B_register = new Button();
        B_register.setBackground(new Color(7, 164, 121));
        B_register.setForeground(new Color(250, 250, 250));
        B_register.setText("Registrarse");
        Register.add(B_register, "w 60%, h 40");
        B_register.addActionListener(this::B_registerActionPerformed);


    }
    private void B_registerActionPerformed(java.awt.event.ActionEvent evt){
       try {
            Connection conn = con.getConexion();

            String nombre = Nombre.getText();
            String apellido = Apellido.getText();
            String cedula = Cedula.getText();
            String edad = Edad.getText();
            String curso = Curso.getSelectedItem().toString();
            String genero = Sexo.getSelectedItem().toString();

            // Preparar la consulta SQL para insertar el usuario
            String sql = "INSERT INTO users(nombre, apellido, edad, sexo, curso, cedula) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pps = conn.prepareStatement(sql);
            pps.setString(1, nombre);
            pps.setString(2, apellido);
            pps.setString(3, edad);
            pps.setString(4, genero);
            pps.setString(5, curso);
            pps.setString(6, cedula);

            // Ejecutar la consulta
            pps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Registro exitoso");
            limpiarCampos();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al registrar: " + e.getMessage());
        }
 
        
    }
    
  private void limpiarCampos() {
        Nombre.setText("");
        Apellido.setText("");
        Edad.setText("");
        Cedula.setText("");
        Curso.setSelectedIndex(0);
        Sexo.setSelectedIndex(0);
    }
  
    private void initLogin(){
       Login.setLayout(new MigLayout("wrap", "push[center]push", "push[]push"));
        JLabel Titulo = new JLabel("Bienvenido");
        Titulo.setFont(new Font("Roboto Black", 1, 30));
        Titulo.setForeground(new Color(7, 164, 121));
        Login.add(Titulo);

        

        JButton B_forget = new JButton("¿Olvidó su contraseña?");
        B_forget.setBackground(new Color(100, 100, 100));
        B_forget.setFont(new Font("Roboto Black", 1, 12));
        B_forget.setContentAreaFilled(false);
        B_forget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Login.add(B_forget);

        Button B_login = new Button();
        B_login.setBackground(new Color(7, 164, 121));
        B_login.setForeground(new Color(250, 250, 250));
        B_login.setText("Iniciar sesión");
        Login.add(B_login, "w 60%, h 40");
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
