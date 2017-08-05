/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Informaciones;


import com.eneri.scstock.Herramientas.ParametrosImpresion;
import com.eneri.scstock.Herramientas.Validaciones;
import com.eneri.scstock.Modelos.ModeloInformaciones;
import com.eneri.scstock.Modelos.ModeloProductos;
import com.eneri.scstock.Objetos.DaoConfiguraciones;
import com.eneri.scstock.Objetos.DaoInformaciones;
import com.eneri.scstock.Objetos.DaoProductos;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author RAscencio
 */
public class VentasInfoPorProducto extends JDialog
{
      private JPanel contentPane;
  private JDateChooser tfFechaDesde;
  private JDateChooser tfFechaHasta;
  public static List<ModeloInformaciones> lista;
  public static DefaultTableModel modelo = new DefaultTableModel(null, new String[] { "Fecha", "Cantidad", "Costo", "Sub-Total" })
  {
    boolean[] columnEditables = new boolean[4];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private JTextField tfCodProducto;
  private JTable table;
  private JLabel lblCdigoDelProducto;
  private JTabbedPane tabbedPane_3;
  private JTextField tfColor;
  private JLabel lblDesdeLaFecha;
  private JLabel lblHastaLaFecha;
  private JButton btnConsultar;
  private boolean existencia;
  private JLabel lblDescripcion;
  private JScrollPane scrollPane;
  private JLabel lblFondo;
  private JButton btnImprimir;
  private JCheckBox chckbxContado;
  private JCheckBox chckbxCrdito;
  private JLabel lblInformeDeVentas;
  private JTabbedPane tabbedPane_2;
  
  public static void main(String[] args)
  {
    try
    {
      VentasInfoPorProducto dialog = new VentasInfoPorProducto();
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
  
  public VentasInfoPorProducto()
  {
    setIconImage(Toolkit.getDefaultToolkit().getImage(VentasInfoPorProducto.class.getResource("/Iconos/VentaInfoProducto.png")));
    setTitle("Informe de venta por producto");
    setResizable(false);
    setBounds(100, 100, 1024, 696);
    this.contentPane = new JPanel();
    this.contentPane.setForeground(Color.BLACK);
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    this.contentPane.setLayout(null);
    
    this.lblDescripcion = new JLabel("");
    this.lblDescripcion.setForeground(Color.WHITE);
    this.lblDescripcion.setFont(new Font("Tahoma", 1, 15));
    this.lblDescripcion.setBounds(22, 167, 829, 20);
    this.contentPane.add(this.lblDescripcion);
    
    this.tfFechaDesde = new JDateChooser();
    this.tfFechaDesde.getCalendarButton().setIcon(new ImageIcon(GraficoDeBarras.class.getResource("/com/toedter/calendar/demo/images/DemoTableColor16.gif")));
    this.tfFechaDesde.setDateFormatString("dd/MM/yyyy");
    this.tfFechaDesde.setBounds(136, 93, 123, 20);
    this.contentPane.add(this.tfFechaDesde);
    
    this.tfFechaHasta = new JDateChooser();
    this.tfFechaHasta.getCalendarButton().setIcon(new ImageIcon(GraficoDeBarras.class.getResource("/com/toedter/calendar/demo/images/DemoTableColor16.gif")));
    this.tfFechaHasta.setDateFormatString("dd/MM/yyyy");
    this.tfFechaHasta.setBounds(136, 124, 123, 20);
    this.contentPane.add(this.tfFechaHasta);
    
    this.tfCodProducto = new JTextField();
    this.tfCodProducto.addKeyListener(new KeyAdapter()
    {
      public void keyTyped(KeyEvent evento)
      {
        if (VentasInfoPorProducto.this.tfCodProducto.getText().trim().length() > 14) {
          evento.consume();
        }
      }
    });
    Validaciones validar = new Validaciones();
    validar.BloqueoAlfabetico(this.tfCodProducto);
    this.tfCodProducto.setBounds(136, 62, 123, 20);
    this.contentPane.add(this.tfCodProducto);
    this.tfCodProducto.setColumns(10);
    
    this.btnConsultar = new JButton("Consultar");
    this.btnConsultar.setFont(new Font("Tahoma", 1, 13));
    this.btnConsultar.setIcon(new ImageIcon(VentasInfoPorProducto.class.getResource("/Iconos/procesar.png")));
    this.btnConsultar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (VentasInfoPorProducto.this.ValidarCodigo())
        {
          VentasInfoPorProducto.this.VerificarSiExisteInfoDelProducto();
          if (VentasInfoPorProducto.this.table.getRowCount() > 0) {
            VentasInfoPorProducto.this.btnImprimir.setEnabled(true);
          }
        }
      }
    });
    this.btnConsultar.setBounds(477, 589, 161, 68);
    this.contentPane.add(this.btnConsultar);
    
    this.scrollPane = new JScrollPane();
    this.scrollPane.setVisible(true);
    this.scrollPane.setBounds(12, 202, 801, 376);
    this.contentPane.add(this.scrollPane);
    
    this.table = new JTable();
    this.table.getTableHeader().setReorderingAllowed(false);
    this.scrollPane.setViewportView(this.table);
    this.table.setModel(modelo);
    this.table.getColumnModel().getColumn(0).setPreferredWidth(35);
    this.table.getColumnModel().getColumn(1).setPreferredWidth(43);
    this.table.getColumnModel().getColumn(2).setPreferredWidth(53);
    
    this.lblDesdeLaFecha = new JLabel("Desde la fecha:");
    this.lblDesdeLaFecha.setForeground(Color.WHITE);
    this.lblDesdeLaFecha.setFont(new Font("Tahoma", 1, 14));
    this.lblDesdeLaFecha.setBounds(25, 93, 117, 20);
    this.contentPane.add(this.lblDesdeLaFecha);
    
    this.lblHastaLaFecha = new JLabel("Hasta la fecha:");
    this.lblHastaLaFecha.setForeground(Color.WHITE);
    this.lblHastaLaFecha.setFont(new Font("Tahoma", 1, 14));
    this.lblHastaLaFecha.setBounds(25, 124, 117, 20);
    this.contentPane.add(this.lblHastaLaFecha);
    
    this.lblCdigoDelProducto = new JLabel("C�d. del prod.:");
    this.lblCdigoDelProducto.setForeground(Color.WHITE);
    this.lblCdigoDelProducto.setFont(new Font("Tahoma", 1, 14));
    this.lblCdigoDelProducto.setBounds(22, 60, 144, 20);
    this.contentPane.add(this.lblCdigoDelProducto);
    
    this.tabbedPane_3 = new JTabbedPane(1);
    this.tabbedPane_3.setBounds(12, 52, 438, 103);
    this.contentPane.add(this.tabbedPane_3);
    
    this.tfColor = new JTextField();
    this.tfColor.setVisible(false);
    this.tfColor.setBounds(300, 6, 86, 20);
    this.contentPane.add(this.tfColor);
    this.tfColor.setColumns(10);
    
    this.btnImprimir = new JButton("Imprimir");
    this.btnImprimir.setEnabled(false);
    this.btnImprimir.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        VentasInfoPorProducto.this.dispose();
        VentasInfoPorProducto.this.Imprimir();
      }
    });
    this.btnImprimir.setIcon(new ImageIcon(VentasInfoPorProducto.class.getResource("/Iconos/impresora.png")));
    this.btnImprimir.setFont(new Font("Tahoma", 1, 13));
    this.btnImprimir.setBounds(648, 589, 161, 68);
    this.contentPane.add(this.btnImprimir);
    
    JLabel lblNewLabel = new JLabel("");
    lblNewLabel.setIcon(new ImageIcon(VentasInfoPorProducto.class.getResource("/Galeria/InformXProduct.png")));
    lblNewLabel.setBounds(814, 392, 200, 186);
    this.contentPane.add(lblNewLabel);
    
    JTabbedPane tabbedPane = new JTabbedPane(1);
    tabbedPane.setBounds(820, 6, 188, 651);
    this.contentPane.add(tabbedPane);
    
    this.chckbxContado = new JCheckBox("Contado");
    this.chckbxContado.setForeground(Color.WHITE);
    this.chckbxContado.setOpaque(false);
    this.chckbxContado.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        VentasInfoPorProducto.this.chckbxCrdito.setSelected(false);
      }
    });
    this.chckbxContado.setBounds(581, 65, 85, 23);
    this.contentPane.add(this.chckbxContado);
    
    this.chckbxCrdito = new JCheckBox("Cr�dito");
    this.chckbxCrdito.setForeground(Color.WHITE);
    this.chckbxCrdito.setOpaque(false);
    this.chckbxCrdito.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        VentasInfoPorProducto.this.chckbxContado.setSelected(false);
      }
    });
    this.chckbxCrdito.setBounds(581, 106, 85, 23);
    this.contentPane.add(this.chckbxCrdito);
    
    JTabbedPane tabbedPane_1 = new JTabbedPane(1);
    tabbedPane_1.setBounds(12, 160, 801, 38);
    this.contentPane.add(tabbedPane_1);
    
    JTabbedPane tabbedPane_4 = new JTabbedPane(1);
    tabbedPane_4.setBounds(455, 52, 358, 103);
    this.contentPane.add(tabbedPane_4);
    
    this.lblInformeDeVentas = new JLabel("Informe de ventas por producto");
    this.lblInformeDeVentas.setForeground(Color.WHITE);
    this.lblInformeDeVentas.setFont(new Font("Times New Roman", 0, 35));
    this.lblInformeDeVentas.setBounds(207, 6, 601, 43);
    this.contentPane.add(this.lblInformeDeVentas);
    
    this.tabbedPane_2 = new JTabbedPane(1);
    this.tabbedPane_2.setBounds(10, 6, 803, 43);
    this.contentPane.add(this.tabbedPane_2);
    
    this.lblFondo = new JLabel("New label");
    this.lblFondo.setIcon(new ImageIcon(new File("data/graficos/Formularios.jpg").getAbsolutePath()));
    this.lblFondo.setBounds(0, 0, 1018, 668);
    this.contentPane.add(this.lblFondo);
    
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
  
  private boolean ValidarCodigo()
  {
    if (this.tfCodProducto.getText().trim().isEmpty())
    {
      JOptionPane.showMessageDialog(null, "Ingrese el c�digo del producto del cual desea obtener la informaci�n.", "Advertencia", 2);
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
    ModeloProductos p = DaoProductos.ConsutaParaModificar(this.tfCodProducto.getText());
    this.lblDescripcion.setText(p.getDescripcion());
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
      modelo.setValueAt(Double.valueOf(informacion.getPrecioUnitario()), i, 2);
      modelo.setValueAt(Double.valueOf(informacion.getSubtotal()), i, 3);
    }
  }
  
  private void VerificarSiExisteInfoDelProducto()
  {
    String condicion = "";
    if (this.chckbxContado.isSelected()) {
      condicion = this.chckbxContado.getText().toUpperCase();
    }
    if (this.chckbxCrdito.isSelected()) {
      condicion = "CREDITO";
    }
    ModeloInformaciones informacion = new ModeloInformaciones();
    informacion.setCodigoProducto(this.tfCodProducto.getText());
    if (!this.existencia) {
      if (!DaoInformaciones.ExistenciaDeLaInformacion(informacion, condicion)) {
        JOptionPane.showMessageDialog(null, "No existe ninguna informaci�n para este producto", "Sin informaciones", 2);
      } else {
        ConsultarInformacion();
      }
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
    String formato = this.tfFechaDesde.getDateFormatString();
    String formato2 = this.tfFechaHasta.getDateFormatString();
    
    Date date = this.tfFechaDesde.getDate();
    Date date2 = this.tfFechaHasta.getDate();
    
    SimpleDateFormat sdf = new SimpleDateFormat(formato);
    SimpleDateFormat sdf2 = new SimpleDateFormat(formato2);
    
    String FechaDesde = String.valueOf(sdf.format(date));
    String FechaHasta = String.valueOf(sdf2.format(date2));
    
    Map parametros = new HashMap();
    
    parametros.put("fechaDesde", FechaDesde);
    parametros.put("fechaHasta", FechaHasta);
    parametros.put("empresa", DaoConfiguraciones.NombreEmpresa());
    parametros.put("descripcion", this.lblDescripcion.getText().toUpperCase());
    parametros.put("codigo", this.tfCodProducto.getText());
    parametros.put("condicion", condicion);
    
    ArrayList<?> list = (ArrayList)lista;
    ParametrosImpresion.impresionReporte(list, parametros, "InfVentXproducto");
  }
}
