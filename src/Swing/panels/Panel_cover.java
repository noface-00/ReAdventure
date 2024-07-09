package swing.panels;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 *
 * @author PC
 */
public class Panel_cover extends javax.swing.JPanel {
    private ActionListener event;
    private JLabel Logo;
    
    public Panel_cover() {
        initComponents();
        init();
        setOpaque(false);
    }
    private void init() {
        Logo = new JLabel();
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
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(jButton1)
                .addContainerGap(564, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(264, 264, 264)
                .addComponent(jButton1)
                .addContainerGap(653, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        event.actionPerformed(evt);
        System.out.print("hola");
    }//GEN-LAST:event_jButton1ActionPerformed

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gra = new GradientPaint(0,0, new Color(140, 212, 212),0, getHeight(), new Color(116, 100, 156)); // Cambiar color
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public void addEvent(ActionListener event) {
        this.event = event;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
