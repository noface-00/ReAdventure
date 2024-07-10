package swing.controls;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class MyPasswordField extends JPasswordField {

    // Atributos para el ícono prefijo, ícono sufijo y texto de sugerencia
    private Icon prefixIcon;
    private Icon suffixIcon;
    private String hint = "";

    // Constructor de la clase
    public MyPasswordField() {
        // Configuración inicial del campo de contraseña
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Borde vacío con márgenes de 10 en cada lado
        setBackground(new Color(0, 0, 0, 0)); // Fondo transparente
        setForeground(Color.BLACK); // Color de texto
        setFont(new java.awt.Font("Roboto Black", 0, 13)); // Fuente y tamaño de la fuente
        setSelectionColor(new Color(75, 175, 152)); // Color de selección
    }

    // Getter y Setter para el texto de sugerencia
    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    // Getter y Setter para el ícono prefijo
    public Icon getPrefixIcon() {
        return prefixIcon;
    }

    public void setPrefixIcon(Icon prefixIcon) {
        this.prefixIcon = prefixIcon;
        initBorder(); // Llama a un método para ajustar el borde del campo de contraseña
    }

    // Getter y Setter para el ícono sufijo
    public Icon getSuffixIcon() {
        return suffixIcon;
    }

    public void setSuffixIcon(Icon suffixIcon) {
        this.suffixIcon = suffixIcon;
        initBorder(); // Llama a un método para ajustar el borde del campo de contraseña
    }

    // Método para pintar el componente
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Llama al método de la clase base para pintar el componente

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Renderización suave
        g2.setColor(new Color(230, 245, 241)); // Color de fondo del campo de contraseña
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5); // Dibuja un rectángulo redondeado como fondo
        paintIcon(g); // Llama a un método para pintar los íconos (prefijo y sufijo)
    }

    // Método para pintar el texto de sugerencia si el campo de contraseña está vacío
    @Override
    public void paint(Graphics g) {
        super.paint(g); // Llama al método de la clase base para pintar el componente

        if (getPassword().length == 0) { // Verifica si el campo de contraseña está vacío
            int height = getHeight();
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); // Renderización suave del texto
            Insets insets = getInsets();
            FontMetrics fontMetrics = g.getFontMetrics();
            g.setColor(new Color(200, 200, 200)); // Color del texto de sugerencia
            g.drawString(hint, insets.left, height / 2 + fontMetrics.getAscent() / 2 - 2); // Dibuja el texto de sugerencia centrado verticalmente
        }
    }

    // Método privado para pintar los íconos (prefijo y sufijo)
    private void paintIcon(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Si hay un ícono prefijo definido, dibújalo
        if (prefixIcon != null) {
            Image prefixImage = ((ImageIcon) prefixIcon).getImage();
            int y = (getHeight() - prefixIcon.getIconHeight()) / 2; // Calcula la posición vertical centrada del ícono
            g2.drawImage(prefixImage, 10, y, this); // Dibuja el ícono prefijo
        }

        // Si hay un ícono sufijo definido, dibújalo
        if (suffixIcon != null) {
            Image suffixImage = ((ImageIcon) suffixIcon).getImage();
            int y = (getHeight() - suffixIcon.getIconHeight()) / 2; // Calcula la posición vertical centrada del ícono
            g2.drawImage(suffixImage, getWidth() - suffixIcon.getIconWidth() - 10, y, this); // Dibuja el ícono sufijo
        }
    }

    // Método privado para inicializar el borde del campo de contraseña
    private void initBorder() {
        int left = 15;
        int right = 15;

        // Ajusta los márgenes izquierdo y derecho en función de los íconos prefijo y sufijo
        if (prefixIcon != null) {
            left = prefixIcon.getIconWidth() + 15; // Ajusta el margen izquierdo
        }
        if (suffixIcon != null) {
            right = suffixIcon.getIconWidth() + 15; // Ajusta el margen derecho
        }

        // Establece un borde vacío con los márgenes calculados
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, left, 10, right));
    }
}
