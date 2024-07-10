package swing.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import swing.controls.ButtonOutLine;


/**
 *
 * @author PC
 */
public class Panel_cover extends javax.swing.JPanel {
    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private ActionListener event;
    private MigLayout layout;
    private JLabel Logo;
    private JLabel eslogan_1;
    private ButtonOutLine button;
    private Image backgroundImage;
    private boolean isLogin;
    
    public Panel_cover() {
        initComponents();
        setOpaque(false);
        layout = new MigLayout("wrap, fill", "[center]","push[]25[200]10[]25[]push");
        setLayout(layout);
        init(); 
        
    }
    private void init() {
        Logo = new JLabel();
        backgroundImage = new ImageIcon(getClass().getResource("/icons/readventure/Stickers.png")).getImage();
        ImageIcon icono = new ImageIcon(getClass().getResource("/icons/readventure/logo.png"));
        Logo.setIcon(icono);
        add(Logo);
        
        eslogan_1 = new JLabel("¡Regístrate y descubre un mundo de historias por leer!");
        eslogan_1.setFont(new Font("Roboto Black", 1, 23));
        eslogan_1.setForeground(new Color(247,198,57));
        add(eslogan_1);
        button = new ButtonOutLine();
        button.setBackground(new Color(255,255,255));
        button.setForeground(new Color(255,255,255));
        button.setText("Inicia Sesion");
        button.setFont(new Font("Roboto Black", 1, 20));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    event.actionPerformed(e);
            }
        });
        add(button, "width 200px!, height 40px!");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 322, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 246, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Aplicar el degradado primero
        GradientPaint gra = new GradientPaint(0, 0, new Color(156, 220, 212), 0, getHeight(), new Color(116, 100, 156));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Dibujar la imagen de fondo encima del degradado
        if (backgroundImage != null) {
            g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public void addEvent(ActionListener event) {
        this.event = event;
    }
    
    public void registerLeft(double v) {
        v = Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(Logo, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(eslogan_1, "pad 0 -" + v + "% 0 0");
        //layout.setComponentConstraints(description1, "pad 0 -" + v + "% 0 0");
    }

    public void registerRight(double v) {
        v = Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(Logo, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(eslogan_1, "pad 0 -" + v + "% 0 0");
        //layout.setComponentConstraints(description1, "pad 0 -" + v + "% 0 0");
    }

    public void loginLeft(double v) {
        v = Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(Logo, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(eslogan_1, "pad 0 " + v + "% 0 " + v + "%");
        //layout.setComponentConstraints(description1, "pad 0 " + v + "% 0 " + v + "%");
    }

    public void loginRight(double v) {
        v = Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(Logo, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(eslogan_1, "pad 0 " + v + "% 0 " + v + "%");
        //layout.setComponentConstraints(description1, "pad 0 " + v + "% 0 " + v + "%");
    }
    public void login(boolean login){
        if(this.isLogin != login){
            eslogan_1.setText("¡Inicia sesión y continúa tu aventura literaria!");
            button.setText("Registrate");
        }else{
            eslogan_1.setText("¡Regístrate y descubre un mundo de historias por leer!");
            button.setText("Inicia Sesion");
        }
        //this.isLogin = login;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
