/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Informaciones;

import com.eneri.scstock.Modelos.ModeloInformaciones;
import com.eneri.scstock.Modelos.ModeloProductos;
import com.eneri.scstock.Objetos.DaoInformaciones;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
/**
 *
 * @author RAscencio
 */
public class GraficoDePastel extends JDialog
{
    
  ChartPanel panel = null;
  JFreeChart chart = null;
  private JPanel contentPane;
  private JPanel pastel;
  private JDateChooser tfFechaDesde;
  private JDateChooser tfFechaHasta;
  private JLayeredPane capa;
  public static List<ModeloInformaciones> lista;
  public static DefaultTableModel modelo = new DefaultTableModel(null, new String[] { "Descripci�n", "Cantidad" })
  {
    boolean[] columnEditables = new boolean[2];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private JTable datos;
  private JScrollPane scrollPane;
  private JLabel lblNewLabel_1;
  private JTabbedPane tabbedPane_3;
  private JTabbedPane tabbedPane_4;
  private JLabel lblGrafico;
  private JTabbedPane tabbedPane_1;
  private JTextField tfColor;
  private JLabel lblDesdeLaFecha;
  private JLabel lblHastaLaFecha;
  private JButton btnGraficar;
  private boolean existencia;
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
          GraficoDePastel dialog = new GraficoDePastel();
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
  
  public GraficoDePastel()
  {
    setIconImage(Toolkit.getDefaultToolkit().getImage(GraficoDePastel.class.getResource("/Iconos/Pastel.png")));
    setTitle("Gr�fico de pastel");
    setResizable(false);
    setBounds(100, 100, 1099, 700);
    
    this.contentPane = new JPanel();
    this.contentPane.setForeground(Color.BLACK);
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    this.contentPane.setLayout(null);
    
    this.chckbxCrdito = new JCheckBox("Cr�dito");
    this.chckbxCrdito.setForeground(Color.WHITE);
    this.chckbxCrdito.setOpaque(false);
    this.chckbxCrdito.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (GraficoDePastel.this.chckbxCrdito.isSelected()) {
          GraficoDePastel.this.chckbxContado.setSelected(false);
        }
      }
    });
    this.chckbxCrdito.setBounds(135, 26, 123, 23);
    this.contentPane.add(this.chckbxCrdito);
    
    this.chckbxContado = new JCheckBox("Contado");
    this.chckbxContado.setForeground(Color.WHITE);
    this.chckbxContado.setOpaque(false);
    this.chckbxContado.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (GraficoDePastel.this.chckbxContado.isSelected()) {
          GraficoDePastel.this.chckbxCrdito.setSelected(false);
        }
      }
    });
    this.chckbxContado.setBounds(24, 26, 112, 23);
    this.contentPane.add(this.chckbxContado);
    
    this.capa = new JLayeredPane();
    this.capa.setBorder(UIManager.getBorder("FormattedTextField.border"));
    this.capa.setBounds(12, 133, 1071, 528);
    this.contentPane.add(this.capa);
    
    this.pastel = new JPanel();
    this.pastel.setBounds(10, 11, 1051, 506);
    this.capa.add(this.pastel);
    
    this.tfFechaDesde = new JDateChooser();
    this.tfFechaDesde.getCalendarButton().setIcon(new ImageIcon(GraficoDePastel.class.getResource("/com/toedter/calendar/demo/images/DemoTableColor16.gif")));
    this.tfFechaDesde.setDateFormatString("dd/MM/yyyy");
    this.tfFechaDesde.setBounds(135, 68, 123, 20);
    this.contentPane.add(this.tfFechaDesde);
    
    this.tfFechaHasta = new JDateChooser();
    this.tfFechaHasta.getCalendarButton().setIcon(new ImageIcon(GraficoDePastel.class.getResource("/com/toedter/calendar/demo/images/DemoTableColor16.gif")));
    this.tfFechaHasta.setDateFormatString("dd/MM/yyyy");
    this.tfFechaHasta.setBounds(135, 99, 123, 20);
    this.contentPane.add(this.tfFechaHasta);
    
    this.btnGraficar = new JButton("Graficar");
    this.btnGraficar.setFont(new Font("Tahoma", 1, 13));
    this.btnGraficar.setIcon(new ImageIcon(GraficoDePastel.class.getResource("/Iconos/pie-chart-149727_640.png")));
    this.btnGraficar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (GraficoDePastel.this.ValidarRangoFecha()) {
          GraficoDePastel.this.Graficar();
        }
      }
    });
    this.btnGraficar.setBounds(323, 37, 188, 68);
    this.contentPane.add(this.btnGraficar);
    
    this.scrollPane = new JScrollPane();
    this.scrollPane.setVisible(false);
    this.scrollPane.setBounds(670, 18, 413, 68);
    this.contentPane.add(this.scrollPane);
    
    this.datos = new JTable();
    this.scrollPane.setViewportView(this.datos);
    this.datos.setModel(modelo);
    
    this.lblDesdeLaFecha = new JLabel("Desde la fecha:");
    this.lblDesdeLaFecha.setForeground(Color.WHITE);
    this.lblDesdeLaFecha.setFont(new Font("Tahoma", 1, 14));
    this.lblDesdeLaFecha.setBounds(24, 68, 117, 20);
    this.contentPane.add(this.lblDesdeLaFecha);
    
    this.lblHastaLaFecha = new JLabel("Hasta la fecha:");
    this.lblHastaLaFecha.setForeground(Color.WHITE);
    this.lblHastaLaFecha.setFont(new Font("Tahoma", 1, 14));
    this.lblHastaLaFecha.setBounds(24, 99, 117, 20);
    this.contentPane.add(this.lblHastaLaFecha);
    
    this.tabbedPane_4 = new JTabbedPane(1);
    this.tabbedPane_4.setBounds(300, 11, 232, 117);
    this.contentPane.add(this.tabbedPane_4);
    
    this.lblGrafico = new JLabel("Gr�fico de pastel");
    this.lblGrafico.setForeground(Color.WHITE);
    this.lblGrafico.setFont(new Font("Tahoma", 1, 30));
    this.lblGrafico.setBounds(681, 22, 378, 97);
    this.contentPane.add(this.lblGrafico);
    
    this.tabbedPane_3 = new JTabbedPane(1);
    this.tabbedPane_3.setBounds(12, 11, 278, 117);
    this.contentPane.add(this.tabbedPane_3);
    
    this.tabbedPane_1 = new JTabbedPane(1);
    this.tabbedPane_1.setBounds(542, 11, 541, 117);
    this.contentPane.add(this.tabbedPane_1);
    
    this.tfColor = new JTextField();
    this.tfColor.setVisible(false);
    this.tfColor.setBounds(300, 6, 86, 20);
    this.contentPane.add(this.tfColor);
    this.tfColor.setColumns(10);
    
    this.lblNewLabel_1 = new JLabel("New label");
    this.lblNewLabel_1.setIcon(new ImageIcon(new File("data/graficos/Formularios.jpg").getAbsolutePath()));
    this.lblNewLabel_1.setBounds(0, 0, 1093, 672);
    this.contentPane.add(this.lblNewLabel_1);
  }
  
  private void Graficar()
  {
    GraficoPastel();
    
    this.panel = new ChartPanel(this.chart);
    this.panel.setBounds(10, 11, 1040, 500);
    
    this.pastel.add(this.panel);
    this.pastel.repaint();
  }
  
  private void GraficoPastel()
  {
    ConsultarInformacion();
    
    DefaultPieDataset data = new DefaultPieDataset();
    int filatotal = this.datos.getRowCount() - 1;
    for (int i = 0; i <= filatotal; i++)
    {
      String Descripcion = String.valueOf(this.datos.getValueAt(i, 0));
      double cantidad = Double.parseDouble(String.valueOf(this.datos.getValueAt(i, 1)));
      data.setValue(Descripcion, cantidad);
    }
    this.chart = ChartFactory.createPieChart3D("Grafico de pastel de los mejores productos", data, true, true, true);
    this.btnGraficar.setEnabled(false);
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
    if ((!this.chckbxCrdito.isSelected()) && (!this.chckbxContado.isSelected()))
    {
      JOptionPane.showMessageDialog(null, "Elija la condici�n para la consulta", "Aviso", 2);
      return false;
    }
    return true;
  }
  
  private void ConsultarInformacion()
  {
    String formato = this.tfFechaDesde.getDateFormatString();
    String formato2 = this.tfFechaHasta.getDateFormatString();
    
    Date date = this.tfFechaDesde.getDate();
    Date date2 = this.tfFechaHasta.getDate();
    
    SimpleDateFormat sdf = new SimpleDateFormat(formato);
    SimpleDateFormat sdf2 = new SimpleDateFormat(formato2);
    String Fecha1 = String.valueOf(sdf.format(date));
    String Fecha2 = String.valueOf(sdf2.format(date2));
    
    lista = new ArrayList();
    if (this.chckbxContado.isSelected())
    {
      lista = DaoInformaciones.ConsultarParaGraficoPastel(Fecha1, Fecha2, "CONTADO");
      ActualizarTabla();
    }
    if (this.chckbxCrdito.isSelected())
    {
      lista = DaoInformaciones.ConsultarParaGraficoPastel(Fecha1, Fecha2, "CREDITO");
      ActualizarTabla();
    }
  }
  
  private static void ActualizarTabla()
  {
    while (modelo.getRowCount() > 0) {
      modelo.removeRow(0);
    }
    String[] campos = new String[2];
    for (int i = 0; i < lista.size(); i++)
    {
      modelo.addRow(campos);
      ModeloInformaciones informacion = (ModeloInformaciones)lista.get(i);
      
      modelo.setValueAt(informacion.getProducto().getDescripcion().concat("-").concat(" [").concat(informacion.getFecha().concat("]")), i, 0);
      modelo.setValueAt(Double.valueOf(informacion.getCantVendida()), i, 1);
    }
  }    
}
