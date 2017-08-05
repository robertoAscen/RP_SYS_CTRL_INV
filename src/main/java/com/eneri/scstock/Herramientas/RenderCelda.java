/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Herramientas;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author RAscencio
 */
public class RenderCelda extends DefaultTableCellRenderer
{
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column)
  {
    setBackground(Color.white);
    table.setForeground(Color.black);
    
    setForeground(Color.WHITE);
    setBackground(new Color(51, 51, 51));
    double stock = Double.parseDouble(table.getValueAt(row, 3).toString());
    if ((stock > 5.0D) && (stock < 11.0D)) {
      setBackground(Color.ORANGE);
    }
    if (stock <= 1.0D) {
      setBackground(Color.RED);
    }
    if (stock <= 0.0D) {
      setBackground(Color.RED);
    }
    super.getTableCellRendererComponent(table, value, selected, focused, row, column);
    return this;
  }
}
