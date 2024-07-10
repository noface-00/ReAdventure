package swing.controls;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class MyCombo_Box<E> extends JComboBox<E> {

    private Icon prefixIcon;
    private Icon suffixIcon;
    private String hint = "";
    private Color selectionBackground = Color.BLACK;

    // Constructor de la clase
    public MyCombo_Box() {
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Borde vacío con márgenes de 10 en cada lado
        setBackground(Color.YELLOW); // Color de fondo del combo box
        setForeground(Color.BLACK); // Color de texto del combo box
        setFont(new java.awt.Font("Roboto Black", 0, 13)); // Fuente y tamaño de la fuente
        setRenderer(new CustomComboBoxRenderer()); // Configura el renderer personalizado para las celdas de la lista desplegable
        setEditor(new CustomComboBoxEditor()); // Configura el editor personalizado para el combo box
        setUI(new javax.swing.plaf.basic.BasicComboBoxUI() { // Configura la interfaz de usuario básica del combo box
            @Override
            protected javax.swing.JButton createArrowButton() {
                // Crea un botón de flecha personalizado con colores específicos
                javax.swing.JButton button = new javax.swing.plaf.basic.BasicArrowButton(
                        javax.swing.plaf.basic.BasicArrowButton.SOUTH,
                        new Color(0, 0, 0), Color.WHITE, Color.WHITE, Color.WHITE);
                button.setName("ComboBox.arrowButton");
                return button;
            }
        });
    }

    // Getter y Setter para el ícono prefijo
    public Icon getPrefixIcon() {
        return prefixIcon;
    }

    public void setPrefixIcon(Icon prefixIcon) {
        this.prefixIcon = prefixIcon;
        initBorder(); // Llama a un método para ajustar el borde del combo box
    }

    // Getter y Setter para el ícono sufijo
    public Icon getSuffixIcon() {
        return suffixIcon;
    }

    public void setSuffixIcon(Icon suffixIcon) {
        this.suffixIcon = suffixIcon;
        initBorder(); // Llama a un método para ajustar el borde del combo box
    }

    // Getter y Setter para el texto de sugerencia
    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    // Setter para el color de fondo de la selección
    public void setSelectionBackground(Color color) {
        this.selectionBackground = color;
    }

    // Método privado para ajustar el borde del combo box según los íconos prefijo y sufijo
    private void initBorder() {
        int left = 15;
        int right = 15;
        if (prefixIcon != null) {
            left = prefixIcon.getIconWidth() + 15; // Ajusta el margen izquierdo
        }
        if (suffixIcon != null) {
            right = suffixIcon.getIconWidth() + 15; // Ajusta el margen derecho
        }
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, left, 10, right)); // Establece un borde vacío con los márgenes calculados
    }

    // Clase interna para el renderer personalizado del combo box
    private class CustomComboBoxRenderer extends BasicComboBoxRenderer {

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (isSelected) {
                label.setBackground(selectionBackground); // Establece el color de fondo de la celda seleccionada
                label.setForeground(new Color(0, 0, 0)); // Establece el color de texto de la celda seleccionada
            } else {
                label.setBackground(new Color(250, 250, 250)); // Establece el color de fondo predeterminado
                label.setForeground(Color.BLACK); // Establece el color de texto predeterminado
            }
            return label;
        }
    }

    // Clase interna para el editor personalizado del combo box
    private class CustomComboBoxEditor extends BasicComboBoxEditor {

        private MyTextField editor; // Campo de texto personalizado para el editor del combo box

        public CustomComboBoxEditor() {
            editor = new MyTextField(); // Inicializa el campo de texto personalizado
            editor.setBorder(null); // Elimina el borde del campo de texto
            editor.setBackground(Color.YELLOW); // Establece el color de fondo del campo de texto
            editor.setForeground(Color.BLACK); // Establece el color de texto del campo de texto
        }

        @Override
        public void setItem(Object item) {
            if (item != null) {
                editor.setText(item.toString()); // Establece el texto del campo de texto con el valor seleccionado
            } else {
                editor.setText(""); // Si el valor es nulo, establece el texto del campo de texto como vacío
            }
        }

        @Override
        public Object getItem() {
            return editor.getText(); // Obtiene el texto actual del campo de texto
        }

        @Override
        public JTextField getEditorComponent() {
            return editor; // Retorna el componente del editor del combo box
        }
    }
}
