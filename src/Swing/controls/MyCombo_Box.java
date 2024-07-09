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

    public MyCombo_Box() {
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.YELLOW);
        setForeground(Color.BLACK);
        setFont(new java.awt.Font("Roboto Black", 0, 13));
        setRenderer(new CustomComboBoxRenderer());
        setEditor(new CustomComboBoxEditor());
        setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
            @Override
            protected javax.swing.JButton createArrowButton() {
                javax.swing.JButton button = new javax.swing.plaf.basic.BasicArrowButton(
                        javax.swing.plaf.basic.BasicArrowButton.SOUTH,
                        new Color(75, 175, 152), Color.YELLOW, Color.BLACK, Color.YELLOW);
                button.setName("ComboBox.arrowButton");
                return button;
            }
        });
    }

    public Icon getPrefixIcon() {
        return prefixIcon;
    }

    public void setPrefixIcon(Icon prefixIcon) {
        this.prefixIcon = prefixIcon;
        initBorder();
    }

    public Icon getSuffixIcon() {
        return suffixIcon;
    }

    public void setSuffixIcon(Icon suffixIcon) {
        this.suffixIcon = suffixIcon;
        initBorder();
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public void setSelectionBackground(Color color) {
        this.selectionBackground = color;
    }

    private void initBorder() {
        int left = 15;
        int right = 15;
        if (prefixIcon != null) {
            left = prefixIcon.getIconWidth() + 15;
        }
        if (suffixIcon != null) {
            right = suffixIcon.getIconWidth() + 15;
        }
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, left, 10, right));
    }

    private class CustomComboBoxRenderer extends BasicComboBoxRenderer {

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (isSelected) {
                label.setBackground(selectionBackground);
                label.setForeground(new Color(255, 0, 0, 128));
            } else {
                label.setBackground(new Color(75, 175, 152));
                label.setForeground(Color.BLACK);
            }
            return label;
        }
    }

    private class CustomComboBoxEditor extends BasicComboBoxEditor {

        private MyTextField editor;

        public CustomComboBoxEditor() {
            editor = new MyTextField();
            editor.setBorder(null);
            editor.setBackground(Color.YELLOW);
            editor.setForeground(Color.BLACK);
        }

        @Override
        public void setItem(Object item) {
            if (item != null) {
                editor.setText(item.toString());
            } else {
                editor.setText("");
            }
        }

        @Override
        public Object getItem() {
            return editor.getText();
        }

        @Override
        public JTextField getEditorComponent() {
            return editor;
        }
    }
}
