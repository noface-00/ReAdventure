package Swing.panels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import swing.controls.Button;
import swing.controls.MyCombo_Box;
import swing.controls.MyPasswordField;
import swing.controls.MyTextField;

/**
 *
 * @author PC
 */
public class Login_Register extends javax.swing.JPanel {
    private ActionListener event;
    /**
     * Creates new form cover
     */
    public Login_Register() {
        initComponents();
        initRegister();
        initLogin();
        Login.setVisible(false);
        Register.setVisible(true);
    }


    
    private void initRegister() {
        Register.setLayout(new MigLayout("wrap", "push[center]push", "push[]push"));
        JLabel Titulo = new JLabel("Crear Cuenta");
        Titulo.setFont(new Font("Roboto Black", 1, 30));
        Titulo.setForeground(new Color(7,164,121));
        Register.add(Titulo);
        MyTextField Nombre = new MyTextField();
        //Nombre.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/bloque2.png")));
        Nombre.setHint("Nombre");
        Register.add(Nombre, "w 60%");
        MyTextField Apellido = new MyTextField();
        //Nombre.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/bloque2.png")));
        Apellido.setHint("Apellido");
        Register.add(Apellido, "w 60%");
        MyTextField Cedula = new MyTextField();
        //Nombre.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/bloque2.png")));
        Cedula.setHint("Cedula");
        Register.add(Cedula, "w 60%");
        MyPasswordField Contraseña = new MyPasswordField();
        //Nombre.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/bloque2.png")));
        Contraseña.setHint("Contraseña");
        Register.add(Contraseña, "w 60%");
        MyCombo_Box Curso = new MyCombo_Box();
        Curso.addItem("Curso");
        Curso.addItem("3ro");
        Curso.addItem("4to");
        Curso.setHint("Cursos");
        Curso.setBackground(new Color(7,164,121));
        Curso.setSelectionBackground(new Color(250,250,250));
        Register.add(Curso,"width 30%, height 30lp, align right");
        MyCombo_Box sexo = new MyCombo_Box();
        sexo.addItem("Género");
        sexo.addItem("Masculino");
        sexo.addItem("Femenino");
        sexo.setHint("Genero");
        sexo.setBackground(new Color(7,164,121));
        sexo.setSelectionBackground(new Color(250,250,250));
        Register.add(sexo,"width 30%, height 30lp, align left");

        Button B_register = new Button();
        B_register.setBackground(new Color(7,164,121));
        B_register.setForeground(new Color(250,250,250));
        B_register.setText("Registrarse");
        Register.add(B_register,"w 60%, h 40");


    }
    private void initLogin(){
        Login.setLayout(new MigLayout("wrap", "push[center]push", "push[]push"));
        JLabel Titulo = new JLabel("Bienvenido");
        Titulo.setFont(new Font("Roboto Black", 1, 30));
        Titulo.setForeground(new Color(7,164,121));
        Login.add(Titulo);
        MyTextField Nombre = new MyTextField();
        //Nombre.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/bloque2.png")));
        Nombre.setHint("Nombre");
        Login.add(Nombre, "w 60%");
        MyTextField Cedula = new MyTextField();
        //Nombre.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/bloque2.png")));
        Cedula.setHint("Cedula");
        Login.add(Cedula, "w 60%");
        JButton B_forget = new JButton("¿Olvido su contraseña?");
        B_forget.setBackground(new Color(100,100,100));
        B_forget.setFont(new Font("Roboto Black", 1, 12));
        B_forget.setContentAreaFilled(false);
        B_forget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Login.add(B_forget);
        Button B_login = new Button();
        B_login.setBackground(new Color(7,164,121));
        B_login.setForeground(new Color(250,250,250));
        B_login.setText("Iniciar sesion");
        Login.add(B_login,"w 60%, h 40");
    }
    
    public void showLogin(boolean show){
        if(show){
            Register.setVisible(true);
            Login.setVisible(false);
        }else{
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
