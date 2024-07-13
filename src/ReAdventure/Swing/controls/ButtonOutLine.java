package ReAdventure.Swing.controls;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class ButtonOutLine extends JButton {

    // Constructor de la clase
    public ButtonOutLine() {
        setContentAreaFilled(false); // No rellena el área del botón
        setBorder(new EmptyBorder(5, 0, 5, 0)); // Borde vacío con márgenes
        setBackground(Color.WHITE); // Color de fondo del botón
        setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor de mano al pasar sobre el botón
    }

    // Método para pintar el componente
    @Override
    protected void paintComponent(Graphics grphcs) {
        int width = getWidth();
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Renderización suave
        g2.setColor(getBackground()); // Color del borde
        g2.drawRoundRect(0, 0, width - 1, height - 1, height, height); // Dibuja un rectángulo redondeado como borde
        super.paintComponent(grphcs); // Llama al método de la clase base para pintar el componente
    }
}
