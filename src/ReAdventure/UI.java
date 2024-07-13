
package ReAdventure;

import ReAdventure.BaseD.Service_BD;
import ReAdventure.BaseD.Usuarios;
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
        ForgetPassword = new PanelForgetPassword(); // Inicializar panel de olvidar contraseña
        
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
   
    
    private void F_password(){
        ForgetPassword.setVisible(true);
        System.out.println("CLICK");
    }
    
    private void R_usuarios(){
        Usuarios usuario = loginANDRegister.getUsuario();
        try {
            // Verifica si los datos ingresados estan en blanco
            if (!usuario.equals("")) {
                // Verifica si la cédula ya existe
                if (service.checkDuplicateID(usuario.getCedula())) {
                    showMessage(Message.MessageType.ERROR, "Cédula existente");
                } else {
                    // Inserta el nuevo usuario
                    service.insertRegisterUser(usuario);
                    showMessage(Message.MessageType.SUCCESS, "Registro exitoso");
                    loginANDRegister.limpiarCamposRegister();
                    
                }
            } else {
                showMessage(Message.MessageType.ERROR, "Error: Sin Datos");
                usuario.setApellido("");
            }
        } catch (Exception e) {
            // Maneja cualquier otra excepción
            showMessage(Message.MessageType.ERROR, "Error al registrarse");
    }
   
    }
    

    private void L_usuarios(){
        Usuarios_L data = loginANDRegister.getData_login();
        try {
            // Verifica si los datos ingresados estan en blanco
            if(data.equals("")){
               showMessage(Message.MessageType.ERROR, "Ingrese sus crendenciales"); 
            }else{ 
                // Inserta los datos 
            service.insertloginUser(data);
            showMessage(Message.MessageType.SUCCESS, "Credenciales correctas");
            loginANDRegister.limpiarCamposLogin();
            }
        }   catch(Exception e){
           showMessage(Message.MessageType.ERROR, "Error al Loguearse"); 
        }
        
    }
    
    @SuppressWarnings("Convert2Lambda")
    private void showMessage(Message.MessageType messageType, String message){
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
