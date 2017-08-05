/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Formularios;


import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
/**
 *
 * @author RAscencio
 */
public class JTableBackground extends JFrame
{
    
  private static final long serialVersionUID = -6650115843758904110L;
  private static final String pathImage = "c:\\test.jpg";
  public static final short WINDOW_WIDTH = 930;
  public static final short WINDOW_HEIGTH = 420;
  protected JTable mainTable = null;
  protected TableModel tableModel = null;
  
  public JTableBackground()
  {
    super("Imagen de Fondo JTable");
    initComponents();
    setSize(930, 420);
    setDefaultCloseOperation(3);
  }
  
  private class TableModel
    extends DefaultTableModel
  {
    private static final long serialVersionUID = 1L;
    
    public TableModel()
    {
      addColumn("1");addColumn("2");
      addColumn("3");addColumn("4");
      for (int index = 0; index < 20; index++)
      {
        Object[] row = {
          Integer.valueOf(new Random().nextInt(100001)), 
          Integer.valueOf(new Random().nextInt(100001)), 
          Integer.valueOf(new Random().nextInt(100001)), 
          Integer.valueOf(new Random().nextInt(100001)) };
        addRow(row);
      }
    }
  }
  
  private void initComponents()
  {
    this.tableModel = new TableModel();
    this.mainTable = new JTable(this.tableModel)
    {
      private static final long serialVersionUID = 1L;
      ImageIcon imageBackground = new ImageIcon("c:\\test.jpg");
      
      public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
      {
        Component c = super.prepareRenderer(renderer, row, column);
        if ((c instanceof JComponent)) {
          ((JComponent)c).setOpaque(false);
        }
        return c;
      }
      
      public void paint(Graphics graphics)
      {
        graphics.drawImage(this.imageBackground.getImage(), 0, 0, getWidth(), getHeight(), null);
        super.paint(graphics);
      }
    };
    this.mainTable.setFillsViewportHeight(true);
    this.mainTable.setOpaque(false);
    this.mainTable.setForeground(Color.white);
    JScrollPane scrollPane = new JScrollPane(this.mainTable);
    getContentPane().add(scrollPane);
  }
  
  public static void main(String[] args)
  {
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        new JTableBackground().setVisible(true);
      }
    });
  }    
}
