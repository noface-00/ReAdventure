package ReAdventure.Swing.panels;

import ReAdventure.BaseD.Usuarios;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import ReAdventure.Swing.controls.Button;
import ReAdventure.Swing.controls.MyCombo_Box;
import ReAdventure.Swing.controls.MyPasswordField;
import ReAdventure.Swing.controls.MyTextField;
import ReAdventure.BaseD.Conexion;
import ReAdventure.BaseD.Usuarios_L;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author PC
 */
public class Login_Register extends javax.swing.JLayeredPane {

    /**
     * @return the data_login
     */
    public Usuarios_L getData_login() {
        return data_login;
    }

    /**
     * @return the usuario
     */
    public Usuarios getUsuario() {
        return usuario;
    }
    private Usuarios_L data_login;
    private Usuarios usuario;
   
    private Button B_register;
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
    
    
    public Login_Register(ActionListener eventForgetPass, ActionListener eventRegister, ActionListener eventLogin) {
        initComponents();
        initRegister(eventRegister);
        initLogin(eventForgetPass, eventLogin);
        Login.setVisible(true);
        Register.setVisible(false);
        Login.revalidate();
        Register.revalidate();
        
    }
    
  

    
    private void initRegister(ActionListener eventRegister) {
        Register.setLayout(new MigLayout("wrap", "push[center]push", "push[]push"));
        
        JLabel Titulo = new JLabel("Crear Cuenta");
        Titulo.setFont(new Font("Roboto Black", 1, 60));
        Titulo.setForeground(new Color(7, 164, 121));
        Register.add(Titulo);

        Nombre = new MyTextField();
        Nombre.setHint("Nombre");
        Nombre.setPrefixIcon(new ImageIcon(getClass().getResource("/ReAdventure/icons/Person.png")));
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
        Apellido.setPrefixIcon(new ImageIcon(getClass().getResource("/ReAdventure/icons/Person.png")));
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
        Cedula.setPrefixIcon(new ImageIcon(getClass().getResource("/ReAdventure/icons/Cedula.png")));
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

        Edad = new MyTextField();
        Edad.setHint("Edad");
        Edad.setPrefixIcon(new ImageIcon(getClass().getResource("/ReAdventure/icons/Edad.png")));
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
        Contrasena = new MyTextField();
        Contrasena.setHint("Contraseña");
        Contrasena.setPrefixIcon(new ImageIcon(getClass().getResource("/ReAdventure/icons/Contrasena.png")));
        Register.add(Contrasena, "w 60%");
        
        Clave = new MyPasswordField();
        Clave.setHint("Repite la contraseña");
        Clave.setPrefixIcon(new ImageIcon(getClass().getResource("/ReAdventure/icons/Contrasena.png")));
        Register.add(Clave, "w 60%");
        
        Curso = new MyCombo_Box();
        Curso.addItem("");
        Curso.addItem("3ro");
        Curso.addItem("4to");
        Curso.setLabeText("Curso");

        Register.add(Curso, "w 60%, h 40");

        Sexo = new MyCombo_Box();
        Sexo.addItem("");
        Sexo.addItem("Masculino");
        Sexo.addItem("Femenino");
        Sexo.setLabeText("Género");
        Register.add(Sexo, "w 60%, h 40");

        B_register = new Button();
        B_register.setBackground(new Color(7, 164, 121));
        B_register.setForeground(new Color(250, 250, 250));
        B_register.setText("Registrarse");
        Register.add(B_register, "w 60%, h 40");
        B_register.addActionListener(eventRegister);
        B_register.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String nombre = Capitalize(Nombre.getText().trim());
                String apellido = Capitalize(Apellido.getText().trim());
                String cedula = Cedula.getText();
                String edad = Edad.getText();
                String contrasena = new String(Clave.getPassword());
                String curso = Curso.getSelectedItem().toString();
                String sexo = Sexo.getSelectedItem().toString();
                usuario = new Usuarios(edad,cedula,nombre,apellido,contrasena,curso,sexo);
                //limpiarCamposRegister();
            }
        });
        
        //B_register.addActionListener(this::B_registerActionPerformed);
        
        
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
    
    private void ApellidoKeyTyped(java.awt.event.KeyEvent evt){
        char key = evt.getKeyChar();
        if (!(Character.isLetter(key) || key == KeyEvent.VK_BACK_SPACE)) {
            evt.consume(); // Consume el evento para evitar que el carácter sea ingresado
        }
    }
     
    private void NombreKeyTyped(java.awt.event.KeyEvent evt){
        char key = evt.getKeyChar();
        if (!(Character.isLetter(key) || key == KeyEvent.VK_BACK_SPACE)) {
        evt.consume(); // Consume el evento para evitar que el carácter sea ingresado
    }
    }
    
  public void limpiarCamposRegister() {
        Nombre.setText("");
        Apellido.setText("");
        Edad.setText("");
        Cedula.setText("");
        Contrasena.setText("");
        Curso.setSelectedIndex(0);
        Sexo.setSelectedIndex(0);
    }
  
   public void limpiarCamposLogin() {
        Usuario.setText("");
        Clave.setText("");
    }
  
    @SuppressWarnings("Convert2Lambda")
    private void initLogin(ActionListener eventForgetPass, ActionListener eventLogin){
         Login.setLayout(new MigLayout("wrap", "push[center]push", "push[]push"));
    JLabel Titulo = new JLabel("Bienvenido");
    Titulo.setFont(new Font("Roboto Black", 1, 60));
    Titulo.setForeground(new Color(7, 164, 121));
    Login.add(Titulo);

    Usuario = new MyTextField();  // Initialize class field
    Usuario.setHint("Cedula");
    Usuario.setPrefixIcon(new ImageIcon(getClass().getResource("/ReAdventure/icons/Cedula.png")));
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
    Clave.setPrefixIcon(new ImageIcon(getClass().getResource("/ReAdventure/icons/Contrasena.png")));
    Login.add(Clave, "w 60%");        

    JButton B_forget = new JButton("¿Olvidó su contraseña?");
    B_forget.setBackground(new Color(100, 100, 100));
    B_forget.setFont(new Font("Roboto Black", 1, 12));
    B_forget.setContentAreaFilled(false);
    B_forget.setCursor(new Cursor(Cursor.HAND_CURSOR));
    B_forget.addActionListener(eventForgetPass);
    Login.add(B_forget);

    B_login = new Button();  // Assuming B_login is a class field
    B_login.setBackground(new Color(7, 164, 121));
    B_login.setForeground(new Color(250, 250, 250));
    B_login.setText("Iniciar sesión");
    B_login.addActionListener(eventLogin);
    Login.add(B_login, "w 60%, h 40");
    B_login.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
                String cedula = Usuario.getText();
                String contrasena = new String(Clave.getPassword());
                data_login = new Usuarios_L(cedula,contrasena);
        }  
    });
    
    }
    
    private void UsuarioKeyTyped(java.awt.event.KeyEvent evt){
        char key = evt.getKeyChar();
        if (!(Character.isDigit(key) || key == KeyEvent.VK_BACK_SPACE)) {
            evt.consume(); // Consume el evento para evitar que el carácter sea ingresado
        } else if (Usuario.getText().length() >= 10) {
            evt.consume(); // Limita la longitud del texto a 10 caracteres
        }
    }
    
    private String Capitalize(String str){
        if(str == null || str.isEmpty()){
            return str;
        }
          return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
    
    public void showLogin(boolean show){
         if (show) {
            Register.setVisible(false);
            Login.setVisible(true);
            Login.revalidate();
        } else {
            Register.setVisible(true);
            Login.setVisible(false);
            Register.revalidate();
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
