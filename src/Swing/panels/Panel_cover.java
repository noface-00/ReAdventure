package swing.panels;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import swing.controls.ButtonOutLine;


/**
 *
 * @author PC
 */
public class Panel_cover extends javax.swing.JPanel {
    private ActionListener event;
    private MigLayout layout;
    private JLabel Logo;
    private ButtonOutLine button;
    private Image backgroundImage;
    
    public Panel_cover() {
        initComponents();
        init();
        layout = new MigLayout("wrap, fill", "[center]","push[]25[]10[]25[]push");
        setLayout(layout);
        setOpaque(false);
        
    }
    private void init() {
        Logo = new JLabel();
                // Cargar la imagen de fondo
        try {
            URL bgUrl = getClass().getResource("/icons/readventure/Stickers.png");
            if (bgUrl != null) {
                backgroundImage = new ImageIcon(bgUrl).getImage();
            } else {
                System.out.println("Imagen de fondo no encontrada.");
            }
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen de fondo: " + e.getMessage());
        }
        // Verificación manual del recurso
        try {
            URL resourceUrl = getClass().getResource("/icons/readventure/logo.png");
            if (resourceUrl != null) {
                System.out.println("Recurso encontrado: " + resourceUrl.toString());
                ImageIcon icono = new ImageIcon(resourceUrl);
                if (icono.getImageLoadStatus() == java.awt.MediaTracker.COMPLETE) {
                    Logo.setIcon(icono);
                } else {
                    System.out.println("No se pudo cargar la imagen. Estado de carga: " + icono.getImageLoadStatus());
                }
            } else {
                System.out.println("Recurso no encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen: " + e.getMessage());
        }
        add(Logo);
        
        button = new ButtonOutLine();
        button.setBackground(new Color(255,255,255));
        button.setForeground(new Color(255,255,255));
        button.setText("Registrate");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               event.actionPerformed(e);
            }
        });
        add(button);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 802, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 940, Short.MAX_VALUE)
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
