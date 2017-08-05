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
import com.toedter.components.JSpinField;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author RAscencio
 */
public class MejoresProductos extends JDialog
{
    
  public static List<ModeloInformaciones> lista;
  public static DefaultTableModel modelo = new DefaultTableModel(null, new String[] { "C�digo", "Descripci�n", "Fecha", "Cant. vend.", "Precio", "Sub-Total" })
  {
    boolean[] columnEditables = new boolean[6];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private JTable table;
  private JDateChooser tfFechaDesde;
  private JDateChooser tfFechaHasta;
  private static JSpinField spinField;
  private JScrollPane scrollPane;
  private JLabel lblFondo;
  private JLabel lblNDeFactura;
  private JLabel lblRuccin;
  private JLabel lblTelfono;
  private JButton btnConsultar;
  private JButton btnImprimir;
  private JCheckBox chckbxContado;
  private JCheckBox chckbxCrdito;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          MejoresProductos dialog = new MejoresProductos();
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
  
  public MejoresProductos()
  {
    setTitle("Mejores productos");
    setIconImage(Toolkit.getDefaultToolkit().getImage(MejoresProductos.class.getResource("/Iconos/MejorProducto.png")));
    setResizable(false);
    setBounds(100, 100, 1024, 696);
    getContentPane().setLayout(null);
    
    this.chckbxCrdito = new JCheckBox("Cr�dito");
    this.chckbxCrdito.setForeground(Color.WHITE);
    this.chckbxCrdito.setOpaque(false);
    this.chckbxCrdito.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        MejoresProductos.this.chckbxContado.setSelected(false);
      }
    });
    this.chckbxCrdito.setBounds(365, 84, 91, 32);
    getContentPane().add(this.chckbxCrdito);
    
    this.chckbxContado = new JCheckBox("Contado");
    this.chckbxContado.setForeground(Color.WHITE);
    this.chckbxContado.setOpaque(false);
    this.chckbxContado.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        MejoresProductos.this.chckbxCrdito.setSelected(false);
      }
    });
    this.chckbxContado.setBounds(365, 65, 91, 23);
    getContentPane().add(this.chckbxContado);
    
    this.tfFechaDesde = new JDateChooser();
    this.tfFechaDesde.setDateFormatString("dd/MM/yyyy");
    this.tfFechaDesde.setBounds(144, 65, 123, 20);
    getContentPane().add(this.tfFechaDesde);
    
    this.tfFechaHasta = new JDateChooser();
    this.tfFechaHasta.setDateFormatString("dd/MM/yyyy");
    this.tfFechaHasta.setBounds(144, 96, 123, 20);
    getContentPane().add(this.tfFechaHasta);
    
    this.lblNDeFactura = new JLabel("Desde la fecha:");
    this.lblNDeFactura.setForeground(Color.WHITE);
    this.lblNDeFactura.setFont(new Font("Tahoma", 1, 14));
    this.lblNDeFactura.setBounds(33, 65, 117, 20);
    getContentPane().add(this.lblNDeFactura);
    
    this.lblRuccin = new JLabel("Hasta la fecha:");
    this.lblRuccin.setForeground(Color.WHITE);
    this.lblRuccin.setFont(new Font("Tahoma", 1, 14));
    this.lblRuccin.setBounds(33, 96, 117, 20);
    getContentPane().add(this.lblRuccin);
    
    this.scrollPane = new JScrollPane();
    this.scrollPane.setBounds(10, 127, 834, 452);
    getContentPane().add(this.scrollPane);
    
    this.table = new JTable();
    this.table.getTableHeader().setReorderingAllowed(false);
    this.table.setModel(modelo);
    this.table.getColumnModel().getColumn(0).setPreferredWidth(63);
    this.table.getColumnModel().getColumn(1).setPreferredWidth(355);
    this.table.getColumnModel().getColumn(2).setPreferredWidth(32);
    this.table.getColumnModel().getColumn(3).setPreferredWidth(48);
    this.scrollPane.setViewportView(this.table);
    
    spinField = new JSpinField();
    spinField.setBounds(744, 84, 44, 20);
    getContentPane().add(spinField);
    
    this.lblTelfono = new JLabel("Cant. m�x.:");
    this.lblTelfono.setForeground(Color.WHITE);
    this.lblTelfono.setFont(new Font("Tahoma", 1, 14));
    this.lblTelfono.setBounds(651, 84, 100, 20);
    getContentPane().add(this.lblTelfono);
    
    this.btnConsultar = new JButton("Consultar");
    this.btnConsultar.setIcon(new ImageIcon(MejoresProductos.class.getResource("/Iconos/procesar.png")));
    this.btnConsultar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        int valor = MejoresProductos.spinField.getValue();
        if (valor <= 0)
        {
          JOptionPane.showMessageDialog(null, "El valor no puede ser menor, ni igual a cero.");
        }
        else if (MejoresProductos.this.ValidarRangoFecha())
        {
          MejoresProductos.this.ConsultarInformacion();
          if (MejoresProductos.this.table.getRowCount() > 0) {
            MejoresProductos.this.btnImprimir.setEnabled(true);
          }
        }
      }
    });
    this.btnConsultar.setFont(new Font("Tahoma", 1, 13));
    this.btnConsultar.setBounds(505, 589, 161, 68);
    getContentPane().add(this.btnConsultar);
    
    this.btnImprimir = new JButton("Imprimir");
    this.btnImprimir.setEnabled(false);
    this.btnImprimir.setIcon(new ImageIcon(MejoresProductos.class.getResource("/Iconos/impresora.png")));
    this.btnImprimir.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        MejoresProductos.this.dispose();
        MejoresProductos.this.Imprimir();
      }
    });
    this.btnImprimir.setFont(new Font("Tahoma", 1, 13));
    this.btnImprimir.setBounds(676, 589, 161, 68);
    getContentPane().add(this.btnImprimir);
    
    JLabel label_2 = new JLabel("New label");
    label_2.setIcon(new ImageIcon(MejoresProductos.class.getResource("/Galeria/product-icon.png")));
    label_2.setBounds(855, 423, 153, 187);
    getContentPane().add(label_2);
    
    JTabbedPane tabbedPane = new JTabbedPane(1);
    tabbedPane.setBounds(847, 127, 161, 530);
    getContentPane().add(tabbedPane);
    
    JLabel lblInformeDeLos = new JLabel("Informe de los mejores productos");
    lblInformeDeLos.setForeground(Color.WHITE);
    lblInformeDeLos.setFont(new Font("Times New Roman", 0, 35));
    lblInformeDeLos.setBounds(283, 11, 594, 43);
    getContentPane().add(lblInformeDeLos);
    
    JTabbedPane tabbedPane_1 = new JTabbedPane(1);
    tabbedPane_1.setBounds(10, 11, 998, 43);
    getContentPane().add(tabbedPane_1);
    
    JTabbedPane tabbedPane_2 = new JTabbedPane(1);
    tabbedPane_2.setBounds(10, 57, 998, 68);
    getContentPane().add(tabbedPane_2);
    
    this.lblFondo = new JLabel("");
    this.lblFondo.setIcon(new ImageIcon(new File("data/graficos/Formularios.jpg").getAbsolutePath()));
    this.lblFondo.setBounds(0, 0, 1018, 668);
    getContentPane().add(this.lblFondo);
    
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
  
  private boolean ValidarRangoFecha()
  {
    if (this.tfFechaDesde.getDate() == null)
    {
      JOptionPane.showMessageDialog(null, "Complete desde qu� fecha desea obtener la informaci�n", "Advertencia", 2);
      this.tfFechaDesde.requestFocus();
      return false;
    }
    if (this.tfFechaHasta.getDate() == null)
    {
      JOptionPane.showMessageDialog(null, "Complete hasta qu� fecha desea obtener la informaci�n", "Advertencia", 2);
      this.tfFechaHasta.requestFocus();
      return false;
    }
    if ((!this.chckbxContado.isSelected()) && (!this.chckbxCrdito.isSelected()))
    {
      JOptionPane.showMessageDialog(null, "Elija una condici�n para filtrar las informaciones", "Advertencia", 2);
      return false;
    }
    return true;
  }
  
  private void ConsultarInformacion()
  {
    String limiteFila = String.valueOf(spinField.getValue());
    
    String formato = this.tfFechaDesde.getDateFormatString();
    String formato2 = this.tfFechaHasta.getDateFormatString();
    
    Date date = this.tfFechaDesde.getDate();
    Date date2 = this.tfFechaHasta.getDate();
    
    SimpleDateFormat sdf = new SimpleDateFormat(formato);
    SimpleDateFormat sdf2 = new SimpleDateFormat(formato2);
    String Fecha1 = String.valueOf(sdf.format(date));
    String Fecha2 = String.valueOf(sdf2.format(date2));
    
    lista = new ArrayList();
    lista = DaoInformaciones.ConsultarParaInfoMejoresProductos(Fecha1, Fecha2, limiteFila);
    ActualizarTabla();
  }
  
  private static void ActualizarTabla()
  {
    while (modelo.getRowCount() > 0) {
      modelo.removeRow(0);
    }
    String[] campos = new String[4];
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
    String condicion = "";
    if (this.chckbxContado.isSelected()) {
      condicion = this.chckbxContado.getText().toUpperCase();
    }
    if (this.chckbxCrdito.isSelected()) {
      condicion = "CREDITO";
    }
    int cantidadFila = spinField.getValue();
    String formato = this.tfFechaDesde.getDateFormatString();
    String formato2 = this.tfFechaHasta.getDateFormatString();
    
    Date date = this.tfFechaDesde.getDate();
    Date date2 = this.tfFechaHasta.getDate();
    
    SimpleDateFormat sdf = new SimpleDateFormat(formato);
    SimpleDateFormat sdf2 = new SimpleDateFormat(formato2);
    
    String FechaDesde = String.valueOf(sdf.format(date));
    String FechaHasta = String.valueOf(sdf2.format(date2));
    
    Map parametros = new HashMap();
    if (cantidadFila < 2) {
      parametros.put("cantidadMaxProd", "INFORME DEL MEJOR PRODUCTO");
    } else {
      parametros.put("cantidadMaxProd", "INFORME DE LOS " + this.table.getRowCount() + " MEJORES PRODUCTOS");
    }
    parametros.put("empresa", DaoConfiguraciones.NombreEmpresa());
    parametros.put("FechaDesde", FechaDesde);
    parametros.put("FechaHasta", FechaHasta);
    parametros.put("condicion", condicion);
    
    ArrayList<?> list = (ArrayList)lista;
    ParametrosImpresion.impresionReporte(list, parametros, "InformeMejoresProductos");
  }        
}
