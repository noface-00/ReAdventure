
package ReAdventure;

import ReAdventure.BaseD.Service_BD;
import ReAdventure.BaseD.Usuarios_L;
import ReAdventure.Swing.panels.Login_Register;
import ReAdventure.Swing.panels.Message;
import ReAdventure.Swing.panels.PanelForgetPassword;
import ReAdventure.Swing.panels.Panel_cover;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import ReAdventure.BaseD.Usuarios;
import ReAdventure.BaseD.Usuarios_Update;
import ReAdventure.funcions.Validate_cedula;



/**
 *
 * @author PC
 */
public class UI extends javax.swing.JFrame {
    private MigLayout layout; // Layout manager para la disposición de componentes
    private Panel_cover cover; // Panel de cubierta
    private Login_Register loginANDRegister; // Panel de login y registro
    private PanelForgetPassword ForgetPassword; // Panel para olvidar contraseña
    private boolean isLogin = false; // Estado de login
    private final double addSize =  30; // Tamaño adicional
    private final double coverSize = 45; // Tamaño del panel de cubierta
    private final double loginSize = 60; // Tamaño del panel de login
    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US)); // Formato decimal
    private Service_BD service; // Servicio de base de datos
    private Validate_cedula V_cedula;

    
    // Constructor de la clase UI
    public UI() {
        initComponents();
        init();
    }
    
    @SuppressWarnings("Convert2Lambda")
    private void init(){
        service = new Service_BD(); // Inicializar el servicio de base de datos
        layout = new MigLayout("fill, insets 0"); // Inicializa MigLayout
        cover = new Panel_cover(); // Inicializar panel de cubierta
        V_cedula = new Validate_cedula();
        ActionListener eventUpdatePass = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Updated_Password();
            }
            
        };
        // Listener para el evento de olvidar contraseña
        ActionListener eventForgetPass = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                F_password();
            }
        };
        // Listener para el evento de registro
        ActionListener eventRegister = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                R_usuarios();
            }
        };
        
        // Listener para el evento de login
        ActionListener eventLogin = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                L_usuarios();
            }
        };
        
        ForgetPassword = new PanelForgetPassword(eventUpdatePass); // Inicializar panel de olvidar contraseña
        // Inicializar el panel de login y registro con los listeners
        loginANDRegister = new Login_Register(eventForgetPass, eventRegister, eventLogin);
        
        // Animación para la transición entre el panel de login y registro
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            @SuppressWarnings("UnnecessaryTemporaryOnConversionFromString")
            public void timingEvent(float fraction){
                double fractionCover;
                double fractionLogin;
                double size = coverSize;
                if(fraction <= 0.5f){
                    size += fraction * addSize;
                } else {
                    size += addSize - fraction * addSize;
                }
                if(isLogin){
                    fractionCover = 1f - fraction;
                    fractionLogin = fraction;
                    if (fraction >= 0.5f) {
                        cover.registerRight(fractionCover * 100);
                    } else {
                        cover.loginRight(fractionLogin * 100);
                    }
                } else {
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                    if(fraction <= 0.5f){
                        cover.registerLeft(fraction*100);
                    } else {
                        cover.loginLeft((1f - fraction)*100);
                    }
                }
                if(fraction >= 0.5f){
                    loginANDRegister.showLogin(isLogin);
                }
                fractionCover = Double.valueOf(df.format(fractionCover));
                fractionLogin = Double.valueOf(df.format(fractionLogin));
                layout.setComponentConstraints(cover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
                layout.setComponentConstraints(loginANDRegister, "width " + loginSize + "%, pos " + fractionLogin + "al 0 n 100%");
                main.revalidate();
            }
            
            @Override
            public void end() {
                isLogin =! isLogin;
            }
        };
        
        
        Animator animator = new Animator(1000, target); // Inicializar el animador
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0); // Suavizado de animación
        
        main.setLayout(layout); // Establecer el layout del panel principal
        main.setLayer(ForgetPassword, JLayeredPane.POPUP_LAYER);
        main.add(ForgetPassword, "pos 0 0 100% 100%");
        main.add(cover, "width " + coverSize + "%, pos 0al 0 n 100%");
        main.add(loginANDRegister, "width " + loginSize + "%, pos 1al 0 n 100%");
        
        cover.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!animator.isRunning()){
                    animator.start();
                }
            }
        })   ;
        
        
    }
   
    private void Updated_Password(){
       Usuarios_Update usuario = ForgetPassword.getUsuario_UP();
       try {
            if (usuario.getCedula().isEmpty()) {
                showMessage(Message.MessageType.ERROR, "Ingrese su cedula");
                return;
            }
            if(!V_cedula.isValidCedula(usuario.getCedula())){
                showMessage(Message.MessageType.ERROR, "La cedula no es valida");
                return;
            }

            if (usuario.getContrasena().isEmpty()) {
                showMessage(Message.MessageType.ERROR, "Ingrese su nueva contraseña");
                return;
            }
            
            if (usuario.getContrasena().length() < 5) {
            showMessage(Message.MessageType.ERROR, "La contraseña debe tener al menos 5 caracteres");
            return;
            }
            
            if (!containsNumber(usuario.getContrasena())) {
            showMessage(Message.MessageType.ERROR, "Falta escribir un número en la contraseña");
            return;
             }
           
            if (!containsUpperCase(usuario.getContrasena())) {
            showMessage(Message.MessageType.ERROR, "Falta escribir una mayúscula en la contraseña");
            return;
            }
            
            if (!containsSpecialCharacter(usuario.getContrasena())) {
            showMessage(Message.MessageType.ERROR, "La contraseña debe contener al menos un carácter especial");
            return;
            }

            if (usuario.getVerf_Contrasena().isEmpty()) {
                showMessage(Message.MessageType.ERROR, "Repita su nueva contraseña");
                return;
            }          
            
            if(!usuario.getContrasena().equals(usuario.getVerf_Contrasena())){
                showMessage(Message.MessageType.ERROR, "Las contraseñas no coinciden");
                System.out.println(usuario.getContrasena());
                System.out.println(usuario.getContrasena());
                return;
            }
            
            if (service.checkDuplicateID(usuario.getCedula())) {
                service.updatePasswordByCedula(usuario.getCedula(), usuario.getVerf_Contrasena());
                showMessage(Message.MessageType.SUCCESS, "Cambio de contraseña exitoso");
                ForgetPassword.close_updatePass();
            } else {
              showMessage(Message.MessageType.ERROR, "Usted no esta registrado");
            }

        } catch (Exception e) {
            System.err.println(e);
            showMessage(Message.MessageType.ERROR, "Error al cambiar contraseña");
            
        }
    }
    private void F_password(){
        ForgetPassword.setVisible(true);
        System.out.println("CLICK");

    }
    
    private void R_usuarios(){
      Usuarios usuario = loginANDRegister.getUsuario();
      String nombre = usuario.getNombre();
      String apellido = usuario.getApellido();
      String cedula = usuario.getCedula();
      String edad = usuario.getEdad();
      String contrasena = usuario.getContrasena();
      String contrasenaR = usuario.getContrasenaR();
      String curso = usuario.getCurso();
      String sexo = usuario.getGenero();
        try {
            if (nombre.isEmpty()) {
                showMessage(Message.MessageType.ERROR, "Falta Nombre");
                return;
            }

            if (apellido.isEmpty()) {
                showMessage(Message.MessageType.ERROR, "Falta Apellido");
                return;
            }
            
            if (cedula.isEmpty()){
                showMessage(Message.MessageType.ERROR, "Falta Cedula");
                return;
            }
            
            if(!V_cedula.isValidCedula(cedula)){
                showMessage(Message.MessageType.ERROR, "La cedula no es valida");
                return;
            }
            
            if (edad.isEmpty()) {
                showMessage(Message.MessageType.ERROR, "Falta Edad");
                return;
            }
            
            if (Integer.parseInt(edad) <= 7 || Integer.parseInt(edad) >= 11){
                showMessage(Message.MessageType.ERROR, "La edad debe estar entre 8 a 10 años");
                return;
            }

            if (contrasena.isEmpty()) {
                showMessage(Message.MessageType.ERROR, "Falta Contraseña");
                return;
            }
 
            if (contrasena.length() < 5) {
            showMessage(Message.MessageType.ERROR, "La contraseña debe tener al menos 5 caracteres");
            return;
            }
            
            if (!containsNumber(contrasena)) {
            showMessage(Message.MessageType.ERROR, "Falta escribir un número en la contraseña");
            return;
             }
           
            if (!containsUpperCase(contrasena)) {
            showMessage(Message.MessageType.ERROR, "Falta escribir una mayúscula en la contraseña");
            return;
            }
            
            if (!containsSpecialCharacter(contrasena)) {
            showMessage(Message.MessageType.ERROR, "La contraseña debe contener al menos un carácter especial");
            return;
            }
            
            if (contrasenaR.isEmpty()) {
                showMessage(Message.MessageType.ERROR, "Falta repetir la Contraseña");
                return;
            }
            
            if(!contrasena.equals(contrasenaR)){
                showMessage(Message.MessageType.ERROR, "Las contraseñas no coiciden");
                return;
            }
            
            
            if (curso.isEmpty()) {
                showMessage(Message.MessageType.ERROR, "Falta Curso");
                return;
            }

            if (sexo.isEmpty()) {
                showMessage(Message.MessageType.ERROR, "Falta Género");
                return;
            }
            if (service.checkDuplicateID(usuario.getCedula())) {
                showMessage(Message.MessageType.ERROR, "Cédula existente");
                return;
            } else {
                // Insertar el nuevo usuario si pasa todas las validaciones
                service.insertRegisterUser(usuario);
                showMessage(Message.MessageType.SUCCESS, "Registro exitoso");
                loginANDRegister.limpiarCamposRegister();
            }

        } catch (Exception e) {
            System.err.print(e);
            showMessage(Message.MessageType.ERROR, "Error al registrarse");
        }
    }
    

    private void L_usuarios(){
        Usuarios_L data = loginANDRegister.getData_login();
        String user = data.getUser();
        String clave = data.getClave();
        try {
            // Verifica si los datos ingresados estan en blanco
            if(data.equals("")){
               showMessage(Message.MessageType.ERROR, "Ingrese sus crendenciales"); 
            }
            
            if (user.isEmpty()) {
                showMessage(Message.MessageType.ERROR, "Falta la Cédula");
                return;
            }
            if(!service.checkDuplicateID(user)){
                showMessage(Message.MessageType.ERROR, "Su cedula no esta registrada");
                return;
            }
            
            if (clave.isEmpty()) {
                showMessage(Message.MessageType.ERROR, "Falta Contraseña");
                return;
            }

            if(service.insertloginUser(data)){
                showMessage(Message.MessageType.SUCCESS, "Credenciales correctas");
                Main Inicio = new Main();
                setVisible(false);
                Inicio.setVisible(true);
                Inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
                
            } else{ 
               showMessage(Message.MessageType.ERROR, "Credenciales incorrectas");
            
            }
        }   catch(Exception e){
           showMessage(Message.MessageType.ERROR, "Error al loguearse"); 
        }
        
    }
    
    @SuppressWarnings("Convert2Lambda")
    public void showMessage(Message.MessageType messageType, String message){
            Message ms = new Message();
            ms.showMessage(messageType, message);
            TimingTarget target = new TimingTarget(){
                @Override
                public void begin() {
                   if(!ms.isShow()){
                       main.add(ms,"pos 0.5al -30",0);
                       ms.setVisible(true);
                       main.repaint();
                       
                   }
                }

                @Override
                public void timingEvent(float fraction) {
                    float f;
                    if(ms.isShow()){
                        f = 40 * (1f - fraction);
                    }else{
                        f = 40 * fraction;
                    }
                    layout.setComponentConstraints(ms, "pos 0.5al "+ (int) (f - 30));
                    main.repaint();
                    main.revalidate();
                }

                @Override
                public void end() {
                   if(ms.isShow()){
                       main.remove(ms);
                       main.repaint();
                       main.revalidate();
                   } else{
                       ms.setShow(true);
                   }
                }

                @Override
                public void repeat() {
                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }
                   
            };
            Animator animator = new Animator(300, target);
            animator.setResolution(0);
            animator.setAcceleration(0.5f);
            animator.setDeceleration(0.5f);
            animator.start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        animator.start();
                    } catch (InterruptedException e) {
                        System.err.println(e);
                    }
                }
            }).start();                    
    }
    
    private boolean containsNumber(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
            return true;
            }
        }
        return false;
    }

    private boolean containsUpperCase(String str) {
    for (char c : str.toCharArray()) {
        if (Character.isUpperCase(c)) {
            return true;
        }
    }
        return false;
    }
    private boolean containsSpecialCharacter(String password) {
        // Aquí puedes definir los caracteres especiales que deseas validar
        String specialCharacters = "!@#$%^&()-_=+[]{}<>?;:,./\\|~\"'°¨´¡¿?=/&%$#°*~¢£áéíóú®ÁÉÍÓÚ";
        for (char c : specialCharacters.toCharArray()) {
            if (password.contains(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        main.setBackground(new java.awt.Color(255, 255, 255));
        main.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        main.setOpaque(true);

        javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(main);
        main.setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("Convert2Lambda")
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @SuppressWarnings("override")
            public void run() {
                UI ui = new UI();
                ui.setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicia la ventana maximizada
                ui.setResizable(false);
                ui.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane main;
    // End of variables declaration//GEN-END:variables
}
