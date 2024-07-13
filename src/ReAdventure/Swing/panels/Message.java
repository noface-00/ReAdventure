
package ReAdventure.Swing.panels;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;


public class Message extends javax.swing.JPanel {

    /**
     * @return the show
     */
    public boolean isShow() {
        return show;
    }

    /**
     * @param show the show to set
     */
    public void setShow(boolean show) {
        this.show = show;
    }
    private MessageType messageType =  MessageType.SUCCESS;
    private boolean show;
    
    public Message() {
        initComponents();
        setOpaque(false);
        setVisible(false);
    }
    public void showMessage(MessageType messageType, String message){
        this.messageType = messageType;
        lbMenssage.setText(message);
        
        if(messageType==MessageType.SUCCESS){
            lbMenssage.setIcon(new ImageIcon(getClass().getResource("/ReAdventure/icons/success.png")));
        }else{
            lbMenssage.setIcon(new ImageIcon(getClass().getResource("/ReAdventure/icons/error.png")));
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbMenssage = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(300, 30));

        lbMenssage.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lbMenssage.setForeground(new java.awt.Color(248, 248, 248));
        lbMenssage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMenssage.setText("Menssage");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbMenssage, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbMenssage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        if(messageType==MessageType.SUCCESS){
            g2.setColor(new Color(15,174,37));
        }else{
            g2.setColor(new Color(240, 52, 53));
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setComposite(AlphaComposite.SrcOver);
        g2.setColor(new Color(245,245,245));
        g2.drawRect(0, 0, getWidth()-1, getHeight()-1);
        super.paintComponent(g);
    }
    
    
    public static enum MessageType{
        SUCCESS, ERROR
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbMenssage;
    // End of variables declaration//GEN-END:variables
}
