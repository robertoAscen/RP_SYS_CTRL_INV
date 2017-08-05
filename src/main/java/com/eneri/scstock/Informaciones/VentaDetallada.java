/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Informaciones;

import com.eneri.scstock.Herramientas.ParametrosImpresion;
import com.eneri.scstock.Modelos.ModeloInformaciones;
import com.eneri.scstock.Objetos.DaoConfiguraciones;
import com.eneri.scstock.Objetos.DaoInformaciones;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author RAscencio
 */
public class VentaDetallada extends JDialog
{
    public static List<ModeloInformaciones> lista;
  public static DefaultTableModel modelo = new DefaultTableModel(null, new String[] { "C�digo", "Descripci�n", "Fecha", "Cant. vend.", "Costo", "Sub-Total" })
  {
    boolean[] columnEditables = new boolean[6];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private JDateChooser tfFecha;
  private JButton button;
  private JButton btnImprimir;
  private JLabel lblFecha;
  private JLabel label_1;
  private JTable table;
  private JTextField tfCondicion;
  private JLabel lblCondicion;
  private JCheckBox chbxCredito;
  private JCheckBox chbxContado;
  private JLabel label;
  private JTabbedPane tabbedPane;
  private JLabel lblInformeDeVentas;
  private JTabbedPane tabbedPane_1;
  private JTabbedPane tabbedPane_2;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          VentaDetallada dialog = new VentaDetallada();
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
  
  public VentaDetallada()
  {
    setTitle("Venta del d�a");
    setIconImage(Toolkit.getDefaultToolkit().getImage(VentaDetallada.class.getResource("/Iconos/VentaDelDia.png")));
    setResizable(false);
    setBounds(100, 100, 1024, 696);
    getContentPane().setLayout(null);
    
    this.tfFecha = new JDateChooser();
    this.tfFecha.setDateFormatString("dd/MM/yyyy");
    this.tfFecha.setBounds(77, 65, 111, 20);
    getContentPane().add(this.tfFecha);
    
    this.button = new JButton("Consultar");
    this.button.setIcon(new ImageIcon(VentaDetallada.class.getResource("/Iconos/procesar.png")));
    this.button.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (VentaDetallada.this.ValidarCondiciones()) {
          VentaDetallada.this.ConsultarInformacionDetallada();
        }
        if (VentaDetallada.this.table.getRowCount() > 0) {
          VentaDetallada.this.btnImprimir.setEnabled(true);
        }
      }
    });
    this.button.setFont(new Font("Tahoma", 1, 13));
    this.button.setBounds(506, 595, 161, 68);
    getContentPane().add(this.button);
    
    this.btnImprimir = new JButton("Imprimir");
    this.btnImprimir.setEnabled(false);
    this.btnImprimir.setIcon(new ImageIcon(VentaDetallada.class.getResource("/Iconos/impresora.png")));
    this.btnImprimir.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        VentaDetallada.this.dispose();
        VentaDetallada.this.Imprimir();
      }
    });
    this.btnImprimir.setFont(new Font("Tahoma", 1, 13));
    this.btnImprimir.setBounds(677, 595, 161, 68);
    getContentPane().add(this.btnImprimir);
    
    this.lblFecha = new JLabel("Fecha:");
    this.lblFecha.setForeground(Color.WHITE);
    this.lblFecha.setFont(new Font("Tahoma", 1, 14));
    this.lblFecha.setBounds(20, 65, 73, 20);
    getContentPane().add(this.lblFecha);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(10, 98, 828, 494);
    getContentPane().add(scrollPane);
    
    this.table = new JTable();
    this.table.getTableHeader().setReorderingAllowed(false);
    this.table.setModel(modelo);
    this.table.getColumnModel().getColumn(0).setPreferredWidth(42);
    this.table.getColumnModel().getColumn(1).setPreferredWidth(320);
    this.table.getColumnModel().getColumn(2).setPreferredWidth(39);
    this.table.getColumnModel().getColumn(3).setPreferredWidth(49);
    this.table.getColumnModel().getColumn(4).setPreferredWidth(42);
    this.table.getColumnModel().getColumn(5).setPreferredWidth(58);
    scrollPane.setViewportView(this.table);
    
    this.lblCondicion = new JLabel("Condici�n:");
    this.lblCondicion.setForeground(Color.WHITE);
    this.lblCondicion.setFont(new Font("Tahoma", 1, 14));
    this.lblCondicion.setBounds(253, 65, 97, 23);
    getContentPane().add(this.lblCondicion);
    
    this.chbxContado = new JCheckBox("Contado");
    this.chbxContado.setForeground(Color.WHITE);
    this.chbxContado.setOpaque(false);
    this.chbxContado.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (VentaDetallada.this.chbxContado.isSelected())
        {
          VentaDetallada.this.tfCondicion.setText("CONTADO");
          VentaDetallada.this.chbxCredito.setSelected(false);
        }
      }
    });
    this.chbxContado.setBounds(339, 65, 97, 23);
    getContentPane().add(this.chbxContado);
    
    this.chbxCredito = new JCheckBox("Cr�dito");
    this.chbxCredito.setForeground(Color.WHITE);
    this.chbxCredito.setOpaque(false);
    this.chbxCredito.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (VentaDetallada.this.chbxCredito.isSelected())
        {
          VentaDetallada.this.tfCondicion.setText("CREDITO");
          VentaDetallada.this.chbxContado.setSelected(false);
        }
      }
    });
    this.chbxCredito.setBounds(432, 65, 103, 23);
    getContentPane().add(this.chbxCredito);
    
    this.tfCondicion = new JTextField();
    this.tfCondicion.setEditable(false);
    this.tfCondicion.setVisible(false);
    this.tfCondicion.setBounds(689, 0, 86, 20);
    getContentPane().add(this.tfCondicion);
    this.tfCondicion.setColumns(10);
    
    this.label = new JLabel("");
    this.label.setIcon(new ImageIcon(VentaDetallada.class.getResource("/Galeria/VentadelDia.png")));
    this.label.setBounds(841, 363, 169, 264);
    getContentPane().add(this.label);
    
    this.tabbedPane = new JTabbedPane(1);
    this.tabbedPane.setBounds(841, 11, 169, 652);
    getContentPane().add(this.tabbedPane);
    
    this.lblInformeDeVentas = new JLabel("Informe de ventas del d�a");
    this.lblInformeDeVentas.setForeground(Color.WHITE);
    this.lblInformeDeVentas.setFont(new Font("Times New Roman", 0, 35));
    this.lblInformeDeVentas.setBounds(243, 11, 565, 43);
    getContentPane().add(this.lblInformeDeVentas);
    
    this.tabbedPane_1 = new JTabbedPane(1);
    this.tabbedPane_1.setBounds(10, 11, 828, 43);
    getContentPane().add(this.tabbedPane_1);
    
    this.tabbedPane_2 = new JTabbedPane(1);
    this.tabbedPane_2.setBounds(10, 56, 828, 37);
    getContentPane().add(this.tabbedPane_2);
    
    this.label_1 = new JLabel("");
    this.label_1.setIcon(new ImageIcon(new File("data/graficos/Formularios.jpg").getAbsolutePath()));
    this.label_1.setBounds(0, 0, 1018, 668);
    getContentPane().add(this.label_1);
    
    LimpiarTabla();
  }
  
  private void LimpiarTabla()
  {
    int filaTotal = this.table.getRowCount();
    while (filaTotal >= 1)
    {
      modelo.removeRow(0);
      filaTotal--;
    }
  }
  
  private boolean ValidarCondiciones()
  {
    if (this.tfFecha.getDate() == null)
    {
      JOptionPane.showMessageDialog(null, "Es necesario completar el rango de fecha para consultar la informaci�n.", "Advertencia", 2);
      return false;
    }
    if ((!this.chbxContado.isSelected()) && (!this.chbxCredito.isSelected()))
    {
      JOptionPane.showMessageDialog(null, "Elija una condici�n para filtrar las informaciones", "Advertencia", 2);
      return false;
    }
    return true;
  }
  
  private void ConsultarInformacionDetallada()
  {
    String formato = this.tfFecha.getDateFormatString();
    
    Date date = this.tfFecha.getDate();
    
    SimpleDateFormat sdf = new SimpleDateFormat(formato);
    
    String Fecha = String.valueOf(sdf.format(date));
    
    lista = new ArrayList();
    lista = DaoInformaciones.ConsultaDetalladaInformacion(Fecha, this.tfCondicion.getText().toString());
    ActualizarTabla();
  }
  
  private static void ActualizarTabla()
  {
    while (modelo.getRowCount() > 0) {
      modelo.removeRow(0);
    }
    String[] campos = new String[6];
    for (int i = 0; i < lista.size(); i++)
    {
      modelo.addRow(campos);
      ModeloInformaciones informacion = (ModeloInformaciones)lista.get(i);
      
      modelo.setValueAt(informacion.getCodigoProducto(), i, 0);
      modelo.setValueAt(informacion.getDescripcionProducto(), i, 1);
      modelo.setValueAt(informacion.getFecha(), i, 2);
      modelo.setValueAt(Double.valueOf(informacion.getCantVendida()), i, 3);
      
      modelo.setValueAt(Double.valueOf(informacion.getPrecioUnitario()), i, 4);
      modelo.setValueAt(Double.valueOf(informacion.getSubtotal()), i, 5);
    }
  }
  
  private void Imprimir()
  {
    String formato = this.tfFecha.getDateFormatString();
    
    Date date = this.tfFecha.getDate();
    
    SimpleDateFormat sdf = new SimpleDateFormat(formato);
    
    String Fecha = String.valueOf(sdf.format(date));
    
    Map parametros = new HashMap();
    
    parametros.put("dia", Fecha);
    parametros.put("empresa", DaoConfiguraciones.NombreEmpresa());
    parametros.put("condicion", this.tfCondicion.getText().toString());
    
    ArrayList<?> list = (ArrayList)lista;
    ParametrosImpresion.impresionReporte(list, parametros, "InformeVentaDetallada");
  }    
}
