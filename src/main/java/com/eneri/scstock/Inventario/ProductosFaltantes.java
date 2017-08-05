/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Inventario;

import com.eneri.scstock.Apariencia.FondoFormularios;
import com.eneri.scstock.Modelos.ModeloInventarios;
import com.eneri.scstock.Modelos.ModeloProductos;
import com.eneri.scstock.Objetos.DaoInventarios;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author RAscencio
 */
public class ProductosFaltantes extends JDialog
{
    
  private JTabbedPane tabbedPane;
  public static DefaultTableModel modelo = new DefaultTableModel(null, new String[] { "C�d. del producto", "Descripci�n", "U.M.", "Stock act.", "Falta", "Costo", "Sub-Total" })
  {
    boolean[] columnEditables = new boolean[7];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private JTable table;
  private JScrollPane scrollPane;
  private JButton btnImprimir;
  private JButton btnConsultar;
  private JTabbedPane tabbedPane_2;
  private JDateChooser tfFecha;
  public static FondoFormularios contentPane;
  public static List<ModeloInventarios> lista;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          ProductosFaltantes dialog = new ProductosFaltantes();
          dialog.setDefaultCloseOperation(2);
          dialog.setLocationRelativeTo(null);
          dialog.setModal(true);
          dialog.setVisible(true);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }
  
  public ProductosFaltantes()
  {
    setTitle("Productos faltantes");
    setResizable(false);
    setBounds(100, 100, 1189, 643);
    
    contentPane = new FondoFormularios();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    this.tabbedPane = new JTabbedPane(1);
    this.tabbedPane.setBounds(249, 11, 924, 49);
    getContentPane().add(this.tabbedPane);
    
    this.scrollPane = new JScrollPane();
    this.scrollPane.setBounds(249, 71, 924, 439);
    contentPane.add(this.scrollPane);
    
    this.table = new JTable();
    this.scrollPane.setViewportView(this.table);
    this.table.getTableHeader().setReorderingAllowed(false);
    this.table.setModel(modelo);
    this.table.getColumnModel().getColumn(0).setPreferredWidth(62);
    this.table.getColumnModel().getColumn(1).setPreferredWidth(368);
    this.table.getColumnModel().getColumn(2).setPreferredWidth(41);
    this.table.getColumnModel().getColumn(3).setPreferredWidth(40);
    this.table.getColumnModel().getColumn(4).setPreferredWidth(47);
    this.table.getColumnModel().getColumn(5).setPreferredWidth(47);
    this.table.getColumnModel().getColumn(6).setPreferredWidth(39);
    
    this.btnConsultar = new JButton("Consultar");
    this.btnConsultar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        ProductosFaltantes.this.CargarProductosFaltantes();
      }
    });
    this.btnConsultar.setIcon(new ImageIcon(ProductosFaltantes.class.getResource("/Iconos/procesar.png")));
    this.btnConsultar.setBounds(825, 530, 158, 63);
    contentPane.add(this.btnConsultar);
    
    this.btnImprimir = new JButton("Imprimir");
    this.btnImprimir.setIcon(new ImageIcon(ProductosFaltantes.class.getResource("/Iconos/impresora.png")));
    this.btnImprimir.setBounds(994, 530, 158, 63);
    contentPane.add(this.btnImprimir);
    
    JLabel lblProductosFaltantes = new JLabel("Productos faltantes");
    lblProductosFaltantes.setForeground(Color.WHITE);
    lblProductosFaltantes.setFont(new Font("Tahoma", 0, 35));
    lblProductosFaltantes.setBounds(477, 11, 471, 43);
    contentPane.add(lblProductosFaltantes);
    
    JTabbedPane tabbedPane_1 = new JTabbedPane(1);
    tabbedPane_1.setBounds(10, 521, 1163, 84);
    contentPane.add(tabbedPane_1);
    
    this.tabbedPane_2 = new JTabbedPane(1);
    this.tabbedPane_2.setBounds(10, 175, 229, 335);
    contentPane.add(this.tabbedPane_2);
    
    this.tfFecha = new JDateChooser();
    this.tfFecha.setDateFormatString("dd/MM/yyyy");
    this.tfFecha.setBounds(0, 0, 138, 20);
    contentPane.add(this.tfFecha);
  }
  
  public void CargarProductosFaltantes()
  {
    lista = new ArrayList();
    lista = DaoInventarios.ConsultarProductosFaltantes();
    ActualizarTablaPrincipal();
  }
  
  private static void ActualizarTablaPrincipal()
  {
    while (modelo.getRowCount() > 0) {
      modelo.removeRow(0);
    }
    String[] campos = new String[7];
    for (int i = 0; i < lista.size(); i++)
    {
      modelo.addRow(campos);
      ModeloInventarios inventario = (ModeloInventarios)lista.get(i);
      modelo.setValueAt(inventario.getProducto().getCodigo(), i, 0);
      modelo.setValueAt(inventario.getProducto().getDescripcion(), i, 1);
      modelo.setValueAt(inventario.getProducto().getUnidadDeMedida(), i, 2);
      modelo.setValueAt(Double.valueOf(inventario.getStockActual()), i, 3);
      modelo.setValueAt(Double.valueOf(inventario.getFaSo()), i, 4);
      modelo.setValueAt(Double.valueOf(inventario.getCosto()), i, 5);
      modelo.setValueAt(Double.valueOf(inventario.getSubTotal()), i, 6);
    }
  }    
}
