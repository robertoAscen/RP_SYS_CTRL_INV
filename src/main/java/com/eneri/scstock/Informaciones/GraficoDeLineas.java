/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Informaciones;

import com.eneri.scstock.Herramientas.Validaciones;
import com.eneri.scstock.Modelos.ModeloInformaciones;
import com.eneri.scstock.Modelos.ModeloProductos;
import com.eneri.scstock.Objetos.DaoInformaciones;
import com.eneri.scstock.Objetos.DaoProductos;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
/**
 *
 * @author RAscencio
 */
public class GraficoDeLineas extends JDialog
{
    
  ChartPanel panel = null;
  JFreeChart chart = null;
  private JPanel contentPane;
  private JPanel lineas;
  private JDateChooser tfFechaDesde;
  private JDateChooser tfFechaHasta;
  private JLayeredPane capa;
  public static List<ModeloInformaciones> lista;
  public static DefaultTableModel modelo = new DefaultTableModel(null, new String[] { "Dia", "Cantidad" })
  {
    boolean[] columnEditables = new boolean[2];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private JTextField tfCodProducto;
  private JTable datos;
  private JScrollPane scrollPane;
  private JLabel lblNewLabel;
  private JLabel lblCdigoDelProducto;
  private JTabbedPane tabbedPane;
  private JLabel lblNewLabel_1;
  private JTabbedPane tabbedPane_2;
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
          GraficoDeLineas dialog = new GraficoDeLineas();
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
  
  public GraficoDeLineas()
  {
    setIconImage(Toolkit.getDefaultToolkit().getImage(GraficoDeLineas.class.getResource("/Iconos/Lineas.png")));
    setTitle("Gr�fico lineal");
    setResizable(false);
    setBounds(100, 100, 1099, 700);
    
    this.contentPane = new JPanel();
    this.contentPane.setForeground(Color.BLACK);
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    this.contentPane.setLayout(null);
    
    this.capa = new JLayeredPane();
    this.capa.setBorder(UIManager.getBorder("FormattedTextField.border"));
    this.capa.setBounds(12, 133, 765, 528);
    this.contentPane.add(this.capa);
    
    this.lineas = new JPanel();
    this.lineas.setBounds(10, 11, 745, 506);
    this.capa.add(this.lineas);
    
    this.tfFechaDesde = new JDateChooser();
    this.tfFechaDesde.getCalendarButton().setIcon(new ImageIcon(GraficoDeLineas.class.getResource("/com/toedter/calendar/demo/images/DemoTableColor16.gif")));
    this.tfFechaDesde.setDateFormatString("dd/MM/yyyy");
    this.tfFechaDesde.setBounds(135, 68, 123, 20);
    this.contentPane.add(this.tfFechaDesde);
    
    this.tfFechaHasta = new JDateChooser();
    this.tfFechaHasta.getCalendarButton().setIcon(new ImageIcon(GraficoDeLineas.class.getResource("/com/toedter/calendar/demo/images/DemoTableColor16.gif")));
    this.tfFechaHasta.setDateFormatString("dd/MM/yyyy");
    this.tfFechaHasta.setBounds(135, 99, 123, 20);
    this.contentPane.add(this.tfFechaHasta);
    
    this.tfCodProducto = new JTextField();
    this.tfCodProducto.addKeyListener(new KeyAdapter()
    {
      public void keyTyped(KeyEvent evento)
      {
        if (GraficoDeLineas.this.tfCodProducto.getText().trim().length() > 14) {
          evento.consume();
        }
      }
    });
    Validaciones validar = new Validaciones();
    validar.BloqueoAlfabetico(this.tfCodProducto);
    this.tfCodProducto.setBounds(135, 22, 123, 20);
    this.contentPane.add(this.tfCodProducto);
    this.tfCodProducto.setColumns(10);
    
    this.btnGraficar = new JButton("Graficar");
    this.btnGraficar.setFont(new Font("Tahoma", 1, 13));
    this.btnGraficar.setIcon(new ImageIcon(GraficoDeLineas.class.getResource("/Iconos/GraficoLineal.png")));
    this.btnGraficar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (GraficoDeLineas.this.ValidarCodigoYCondicion()) {
          GraficoDeLineas.this.VerificarSiExisteInfoDelProducto();
        }
      }
    });
    this.btnGraficar.setBounds(323, 37, 188, 68);
    this.contentPane.add(this.btnGraficar);
    
    this.scrollPane = new JScrollPane();
    this.scrollPane.setVisible(false);
    this.scrollPane.setBounds(835, 11, 248, 117);
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
    
    this.lblNewLabel = new JLabel("");
    this.lblNewLabel.setIcon(new ImageIcon(GraficoDeLineas.class.getResource("/Galeria/GraficoLineal.png")));
    this.lblNewLabel.setBounds(805, 139, 278, 511);
    this.contentPane.add(this.lblNewLabel);
    
    this.lblCdigoDelProducto = new JLabel("C�d. del prod.:");
    this.lblCdigoDelProducto.setForeground(Color.WHITE);
    this.lblCdigoDelProducto.setFont(new Font("Tahoma", 1, 14));
    this.lblCdigoDelProducto.setBounds(25, 22, 144, 20);
    this.contentPane.add(this.lblCdigoDelProducto);
    
    this.tabbedPane = new JTabbedPane(1);
    this.tabbedPane.setBounds(787, 133, 296, 528);
    this.contentPane.add(this.tabbedPane);
    
    this.tabbedPane_2 = new JTabbedPane(1);
    this.tabbedPane_2.setBounds(12, 11, 278, 38);
    this.contentPane.add(this.tabbedPane_2);
    
    this.tabbedPane_3 = new JTabbedPane(1);
    this.tabbedPane_3.setBounds(12, 60, 278, 68);
    this.contentPane.add(this.tabbedPane_3);
    
    this.tabbedPane_4 = new JTabbedPane(1);
    this.tabbedPane_4.setBounds(300, 11, 232, 117);
    this.contentPane.add(this.tabbedPane_4);
    
    this.lblGrafico = new JLabel("Gr�fico lineal");
    this.lblGrafico.setForeground(Color.WHITE);
    this.lblGrafico.setFont(new Font("Tahoma", 1, 30));
    this.lblGrafico.setBounds(718, 22, 340, 97);
    this.contentPane.add(this.lblGrafico);
    
    this.tabbedPane_1 = new JTabbedPane(1);
    this.tabbedPane_1.setBounds(669, 11, 414, 117);
    this.contentPane.add(this.tabbedPane_1);
    
    this.tfColor = new JTextField();
    this.tfColor.setVisible(false);
    this.tfColor.setBounds(300, 6, 86, 20);
    this.contentPane.add(this.tfColor);
    this.tfColor.setColumns(10);
    
    this.chckbxContado = new JCheckBox("Contado");
    this.chckbxContado.setForeground(Color.WHITE);
    this.chckbxContado.setOpaque(false);
    this.chckbxContado.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        GraficoDeLineas.this.chckbxCrdito.setSelected(false);
      }
    });
    this.chckbxContado.setBounds(551, 23, 97, 23);
    this.contentPane.add(this.chckbxContado);
    
    this.chckbxCrdito = new JCheckBox("Cr�dito");
    this.chckbxCrdito.setForeground(Color.WHITE);
    this.chckbxCrdito.setOpaque(false);
    this.chckbxCrdito.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        GraficoDeLineas.this.chckbxContado.setSelected(false);
      }
    });
    this.chckbxCrdito.setBounds(551, 82, 97, 23);
    this.contentPane.add(this.chckbxCrdito);
    
    this.lblNewLabel_1 = new JLabel("New label");
    this.lblNewLabel_1.setIcon(new ImageIcon(new File("data/graficos/Formularios.jpg").getAbsolutePath()));
    this.lblNewLabel_1.setBounds(0, 0, 1093, 672);
    this.contentPane.add(this.lblNewLabel_1);
  }
  
  private void Graficar()
  {
    GraficoLineal();
    
    this.panel = new ChartPanel(this.chart);
    this.panel.setBounds(10, 11, 730, 490);
    
    this.lineas.add(this.panel);
    this.lineas.repaint();
  }
  
  private void GraficoLineal()
  {
    ConsultarInformacion();
    int valor = 1;
    XYSplineRenderer renderer = new XYSplineRenderer();
    XYSeriesCollection dataset = new XYSeriesCollection();
    
    ValueAxis x = new NumberAxis();
    ValueAxis y = new NumberAxis();
    
    ModeloProductos producto = DaoProductos.ConsutaParaModificar(this.tfCodProducto.getText());
    String descripcion = producto.getDescripcion();
    
    XYSeries serie = new XYSeries(descripcion);
    
    this.lineas.removeAll();
    
    int filatotal = this.datos.getRowCount() - 1;
    for (int i = 0; i <= filatotal; i++)
    {
      double cantidad = Double.parseDouble(String.valueOf(this.datos.getValueAt(i, 1)));
      serie.add(Float.parseFloat(String.valueOf(this.datos.getValueAt(i, 0)).split("-")[2]), Float.parseFloat(String.valueOf(cantidad)));
    }
    if (valor == 1)
    {
      dataset.addSeries(serie);
      
      x.setLabel("Dias");
      y.setLabel("Cantidad vendida");
      XYPlot plot = new XYPlot(dataset, x, y, renderer);
      this.chart = new JFreeChart(plot);
      this.chart.setTitle("Grafico estad�stico lineal para el producto " + descripcion);
    }
    else
    {
      JOptionPane.showMessageDialog(null, "Error al intentar graficar");
    }
    this.btnGraficar.setEnabled(false);
  }
  
  private boolean ValidarCodigoYCondicion()
  {
    if (this.tfCodProducto.getText().trim().isEmpty())
    {
      JOptionPane.showMessageDialog(null, "Ingrese el c�digo del producto del cual desea obtener el gr�fico.", "Advertencia", 2);
      this.tfCodProducto.requestFocus();
      return false;
    }
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
      JOptionPane.showMessageDialog(null, "Elija la condici�n de venta para graficar la informaci�n", "Aviso", 2);
      return false;
    }
    return true;
  }
  
  private void ConsultarInformacion()
  {
    String condicion = "";
    if (this.chckbxContado.isSelected()) {
      condicion = this.chckbxContado.getText().toUpperCase();
    }
    if (this.chckbxCrdito.isSelected()) {
      condicion = "CREDITO";
    }
    String formato = this.tfFechaDesde.getDateFormatString();
    String formato2 = this.tfFechaHasta.getDateFormatString();
    
    Date date = this.tfFechaDesde.getDate();
    Date date2 = this.tfFechaHasta.getDate();
    
    SimpleDateFormat sdf = new SimpleDateFormat(formato);
    SimpleDateFormat sdf2 = new SimpleDateFormat(formato2);
    String Fecha1 = String.valueOf(sdf.format(date));
    String Fecha2 = String.valueOf(sdf2.format(date2));
    String codigo = String.valueOf(this.tfCodProducto.getText());
    lista = new ArrayList();
    lista = DaoInformaciones.ConsultarParaGraficoDeBarras(codigo, Fecha1, Fecha2, condicion);
    ActualizarTabla();
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
      modelo.setValueAt(informacion.getFecha(), i, 0);
      modelo.setValueAt(Double.valueOf(informacion.getCantVendida()), i, 1);
    }
  }
  
  private void VerificarSiExisteInfoDelProducto()
  {
    ModeloInformaciones informacion = new ModeloInformaciones();
    informacion.setCodigoProducto(this.tfCodProducto.getText());
    if ((this.chckbxContado.isSelected()) && 
      (!this.existencia)) {
      if (!DaoInformaciones.ExistenciaDeLaInformacion(informacion, "CONTADO")) {
        JOptionPane.showMessageDialog(null, "No existe ninguna informaci�n para este producto", "Sin informaciones", 2);
      } else {
        Graficar();
      }
    }
    if ((this.chckbxCrdito.isSelected()) && 
      (!this.existencia)) {
      if (!DaoInformaciones.ExistenciaDeLaInformacion(informacion, "CREDITO")) {
        JOptionPane.showMessageDialog(null, "No existe ninguna informaci�n para este producto", "Sin informaciones", 2);
      } else {
        Graficar();
      }
    }
  }    
}
