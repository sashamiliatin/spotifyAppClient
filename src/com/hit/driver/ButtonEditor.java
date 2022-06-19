package com.hit.driver;

import javax.swing.*;
import java.awt.*;

public class ButtonEditor extends DefaultCellEditor {
    private String label;
    private JButton button = new JButton("Delete");
    public ButtonEditor(JCheckBox checkBox,JButton button)
    {
        super(checkBox);
        this.button = button;
    }
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column)
    {
        label = (value == null) ? "Delete" : value.toString();

        button.setText("Delete");
        return button;
    }
    public Object getCellEditorValue()
    {
        return new String(label);
    }
}
