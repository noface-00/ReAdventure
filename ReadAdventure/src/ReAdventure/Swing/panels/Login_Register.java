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
import javax.swing.JOptionPane;

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
    private MyTextField ContrasenaR;
    private MyTextField User;
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
        
        ContrasenaR = new MyTextField();
        ContrasenaR.setHint("Repite la contraseña");
        ContrasenaR.setPrefixIcon(new ImageIcon(getClass().getResource("/ReAdventure/icons/Contrasena.png")));
        Register.add(ContrasenaR, "w 60%");
        
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
        String cedula = Cedula.getText().trim();
        String edad = Edad.getText().trim();
        String contrasenna = new String(ContrasenaR.getText()); // Use getPassword() for JPasswordField
        String curso = Curso.getSelectedItem().toString();
        String sexo = Sexo.getSelectedItem().toString();
        
       
        int edadC = 0;
        if (!edad.isEmpty()) {
            try {
                edadC = Integer.parseInt(edad);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "La edad debe ser un número válido");
                return;
            }
        }
        
    
        if (!isValidCedula(cedula)) {
            JOptionPane.showMessageDialog(null, "La cédula no es válida");
        } else if (edadC < 8 || edadC > 10) {
            JOptionPane.showMessageDialog(null, "La edad debe estar entre 8 y 10 años");
        } else if (!Contrasena.getText().equals(ContrasenaR.getText())) {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
        } else {
            // Registration successful
            usuario = new Usuarios(edad, cedula, nombre, apellido, contrasenna, curso, sexo);

        }
    }
        });
    }
    
    public String getNombre() {
        return Nombre.getText();
    }
    public String getApellido() {
        return Apellido.getText();
    }
    public String getEdad() {
        return Edad.getText().trim();
    }
    public String getContrasena() {
        return Contrasena.getText();
    }
    public String getContrasenaR() {
        return ContrasenaR.getText();
    }
     public String getCurso() {
        return Curso.getSelectedItem().toString();
    }
    public String getSexo() {
        return Sexo.getSelectedItem().toString();
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
            ContrasenaR.setText("");
            Curso.setSelectedIndex(0);
            Sexo.setSelectedIndex(0);
    }
   public void limpiarCamposLogin() {
        User.setText("");
        Clave.setText("");
    }
  
    @SuppressWarnings("Convert2Lambda")
    private void initLogin(ActionListener eventForgetPass, ActionListener eventLogin){
         Login.setLayout(new MigLayout("wrap", "push[center]push", "push[]push"));
    JLabel Titulo = new JLabel("Bienvenido");
    Titulo.setFont(new Font("Roboto Black", 1, 60));
    Titulo.setForeground(new Color(7, 164, 121));
    Login.add(Titulo);

    User = new MyTextField();  // Initialize class field
    User.setHint("Cedula");
    User.setPrefixIcon(new ImageIcon(getClass().getResource("/ReAdventure/icons/Cedula.png")));
    Login.add(User, "w 60%");
    User.addKeyListener(new KeyListener() {
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

    B_login = new Button();  
    B_login.setBackground(new Color(7, 164, 121));
    B_login.setForeground(new Color(250, 250, 250));
    B_login.setText("Iniciar sesión");
    B_login.addActionListener(eventLogin);
    Login.add(B_login, "w 60%, h 40");
    B_login.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
                String cedula = User.getText();
                String contrasena = new String(Clave.getPassword());
                data_login = new Usuarios_L(cedula,contrasena);
        }  
    });
    
    
    }
    public String getUser() {
        return User.getText();
    }
    public String getClave() {
        return new String(Clave.getPassword());
    }
    
    private void UsuarioKeyTyped(java.awt.event.KeyEvent evt){
        char key = evt.getKeyChar();
        if (!(Character.isDigit(key) || key == KeyEvent.VK_BACK_SPACE)) {
            evt.consume(); // Consume el evento para evitar que el carácter sea ingresado
        } else if (User.getText().length() >= 10) {
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
      private boolean isValidCedula(String cedula) {
      if (cedula.length() != 10) {
        return false;
    }

    int alternador = 0;
    int suma = 0;
    int[] L_temp = new int[10];

    for (int i = 0; i < 9; i++) {
        int valor = Character.getNumericValue(cedula.charAt(i));

        valor *= (alternador == 0) ? 2 : 1;
        alternador = 1 - alternador;

        if (valor >= 10) {
            valor -= 9;
        }

        L_temp[i] = valor;
    }

    suma = 0;
    for (int i = 0; i < 9; i++) {
        suma += L_temp[i];
    }

    int comp = suma % 10;
    int com_1 = (comp == 0) ? 0 : 10 - comp;

    return Character.getNumericValue(cedula.charAt(9)) == com_1;
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
