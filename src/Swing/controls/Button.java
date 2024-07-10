package swing.controls;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Button extends JButton {

    private Animator animator; // Animator para la animación de efecto
    private int targetSize; // Tamaño máximo para la animación
    private float animatSize; // Tamaño actual de la animación
    private Point pressedPoint; // Punto donde se presionó el botón
    private float alpha; // Valor de transparencia para el efecto
    private Color effectColor = new Color(255, 255, 255); // Color de efecto predeterminado

    // Constructor de la clase
    public Button() {
        setFont(new Font("Roboto Black", 1, 30)); // Fuente y tamaño de la fuente del botón
        setContentAreaFilled(false); // No rellena el área del botón
        setBorder(new EmptyBorder(5, 0, 5, 0)); // Borde vacío con márgenes
        setBackground(Color.WHITE); // Color de fondo del botón
        setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor de mano al pasar sobre el botón

        // Agrega un listener para el evento de presionar el botón
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                targetSize = Math.max(getWidth(), getHeight()) * 2; // Calcula el tamaño máximo para la animación
                animatSize = 0; // Inicializa el tamaño de la animación
                pressedPoint = me.getPoint(); // Obtiene el punto donde se presionó el botón
                alpha = 0.5f; // Inicializa el valor de transparencia
                if (animator.isRunning()) { // Detiene el animator si ya está corriendo
                    animator.stop();
                }
                animator.start(); // Inicia la animación
            }
        });

        // Define el target para la animación
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (fraction > 0.5f) {
                    alpha = 1 - fraction; // Ajusta la transparencia basado en la fracción de la animación
                }
                animatSize = fraction * targetSize; // Ajusta el tamaño de la animación basado en la fracción
                repaint(); // Vuelve a pintar el componente
            }
        };

        animator = new Animator(700, target); // Crea un Animator con duración de 700 ms
        animator.setAcceleration(0.5f); // Aceleración de la animación
        animator.setDeceleration(0.5f); // Desaceleración de la animación
        animator.setResolution(0); // Resolución de la animación
    }

    // Método para obtener el color de efecto
    public Color getEffectColor() {
        return effectColor;
    }

    // Método para establecer el color de efecto
    public void setEffectColor(Color effectColor) {
        this.effectColor = effectColor;
    }

    // Método para pintar el componente
    @Override
    protected void paintComponent(Graphics grphcs) {
        int width = getWidth();
        int height = getHeight();
        
        // Crea una imagen BufferedImage para dibujar
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Renderización suave
        g2.setColor(getBackground()); // Color de fondo del botón
        g2.fillRoundRect(0, 0, width, height, height, height); // Dibuja un rectángulo redondeado como fondo

        // Dibuja el efecto de presionado si hay un punto presionado
        if (pressedPoint != null) {
            g2.setColor(effectColor); // Color de efecto
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha)); // Establece la transparencia
            g2.fillOval((int) (pressedPoint.x - animatSize / 2), (int) (pressedPoint.y - animatSize / 2), (int) animatSize, (int) animatSize); // Dibuja un círculo como efecto
        }

        g2.dispose(); // Libera los recursos de Graphics2D
        grphcs.drawImage(img, 0, 0, null); // Dibuja la imagen en el contexto gráfico del componente
        super.paintComponent(grphcs); // Llama al método de la clase base para pintar el componente
    }
}
