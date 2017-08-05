/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Tesoreria;

import com.eneri.scstock.Modelos.ModeloFacturaContado;
import com.eneri.scstock.Modelos.ModeloFacturaCredito;
import com.eneri.scstock.Modelos.ModeloVendedores;
import com.eneri.scstock.Objetos.DaoPagos;
import com.eneri.scstock.Objetos.DaoVendedores;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Container;
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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author RAscencio
 */
public class Pagos extends JDialog
{    
  private JButton btnCalcular;
  private JButton btnImprimir;
  private JTable tablaVendedores;
  private JTextField tfCedula;
  private JTextField tfNombre;
  private JTable tablaFacContado;
  private JTextField tfTotalContado;
  private JTextField tfCedulaDetalle;
  private JTextField tfSalario;
  private JTextField tfComision;
  private JTextField tfTelefono;
  private JTextArea tfObservaciones;
  private JTextField tfDireccion;
  private JLabel label_6;
  public static List<ModeloVendedores> listaVendedores;
  public static List<ModeloFacturaCredito> listafacturacredito;
  public static List<ModeloFacturaContado> listafacturacontado;
  public static DefaultTableModel modeloVendedores = new DefaultTableModel(null, new String[] { "C�digo", "Nombre y apellido del vendedor" })
  {
    boolean[] columnEditables = new boolean[2];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  public static DefaultTableModel modelofacturacontado = new DefaultTableModel(null, new String[] { "N� Fact.", "Fecha", "Monto", "Condici�n", "Nombre del vendedor" })
  {
    boolean[] columnEditables = new boolean[5];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  public static DefaultTableModel modelofacturacredito = new DefaultTableModel(null, new String[] { "N� Fact.", "Fecha", "Monto", "Condici�n", "Nombre del vendedor" })
  {
    boolean[] columnEditables = new boolean[5];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private JScrollPane scrollPaneVendedores;
  private JScrollPane scrollPaneVentasRealizadas;
  private JLabel lblCedula;
  private JButton btnConsultar;
  private JLabel label_7;
  private JTextField tfComisionEfectivo;
  private JTextField tfTotalCredito;
  private JTextField tfTotalAPagar;
  private JTable tablaFactCredito;
  private JScrollPane scrollPane_1;
  private JTextField tfTotalGeneral;
  private JTabbedPane tabbedPane;
  private JTabbedPane tabbedPane_1;
  private JLabel lblFecha;
  private JTabbedPane tabbedPane_2;
  private JTabbedPane tabbedPane_3;
  private JTabbedPane tabbedPane_4;
  private JTabbedPane tabbedPane_5;
  private JLabel lblPagoDeSalarios;
  private JDateChooser tfDesde;
  private JDateChooser tfHasta;
  private JTabbedPane tabbedPane_6;
  private JTabbedPane tabbedPane_7;
  private JTabbedPane tabbedPane_8;
  
  public static void main(String[] args)
  {
    try
    {
      Pagos dialog = new Pagos();
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
  
  public Pagos()
  {
    setIconImage(Toolkit.getDefaultToolkit().getImage(Pagos.class.getResource("/IconosMin/money.png")));
    setTitle("Pago de salarios");
    setResizable(false);
    setBounds(100, 100, 1024, 696);
    getContentPane().setLayout(null);
    
    this.btnCalcular = new JButton("Calcular");
    this.btnCalcular.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Pagos.this.CalcularMontoFacturas();
        Pagos.this.CalcularComision();
      }
    });
    this.lblPagoDeSalarios = new JLabel("Pago de salarios");
    this.lblPagoDeSalarios.setForeground(Color.WHITE);
    this.lblPagoDeSalarios.setFont(new Font("Times New Roman", 0, 35));
    this.lblPagoDeSalarios.setBounds(132, 11, 290, 43);
    getContentPane().add(this.lblPagoDeSalarios);
    this.btnCalcular.setIcon(new ImageIcon(Pagos.class.getResource("/Iconos/calculadora.PNG")));
    this.btnCalcular.setBounds(566, 590, 164, 57);
    getContentPane().add(this.btnCalcular);
    
    this.btnImprimir = new JButton("Imprimir");
    this.btnImprimir.setIcon(new ImageIcon(Pagos.class.getResource("/Iconos/impresora.png")));
    this.btnImprimir.setBounds(793, 590, 164, 57);
    getContentPane().add(this.btnImprimir);
    
    this.tfCedula = new JTextField();
    this.tfCedula.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          if (Pagos.this.tfCedula.getText().trim().length() > 0) {
            Pagos.this.consultarPorCedula();
          } else {
            JOptionPane.showMessageDialog(null, "Ingrese el n�mero de c�dula del vendedor para filtrar la lista", "Aviso", 2);
          }
        }
      }
    });
    this.tfCedula.setBounds(68, 108, 99, 20);
    getContentPane().add(this.tfCedula);
    this.tfCedula.setColumns(10);
    
    this.tfNombre = new JTextField();
    this.tfNombre.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (Pagos.this.tfNombre.getText().trim().length() > 0) {
          Pagos.this.consultarPorNombre();
        } else {
          Pagos.this.cargarTodosLosRegistros();
        }
      }
    });
    this.tfNombre.setBounds(233, 108, 232, 20);
    getContentPane().add(this.tfNombre);
    this.tfNombre.setColumns(10);
    
    this.lblCedula = new JLabel("C�dula:");
    this.lblCedula.setForeground(Color.WHITE);
    this.lblCedula.setBounds(19, 111, 52, 14);
    getContentPane().add(this.lblCedula);
    
    JLabel lblNombre = new JLabel("Nombre:");
    lblNombre.setForeground(Color.WHITE);
    lblNombre.setBounds(177, 110, 60, 14);
    getContentPane().add(lblNombre);
    
    this.tfTotalContado = new JTextField();
    this.tfTotalContado.setEditable(false);
    this.tfTotalContado.setColumns(10);
    this.tfTotalContado.setBounds(661, 431, 135, 20);
    getContentPane().add(this.tfTotalContado);
    
    JLabel lblTotal = new JLabel("Total ventas:");
    lblTotal.setForeground(Color.WHITE);
    lblTotal.setBounds(500, 434, 115, 14);
    getContentPane().add(lblTotal);
    
    JLabel lblCin = new JLabel("     C.I.N�:");
    lblCin.setForeground(Color.WHITE);
    lblCin.setBounds(62, 431, 57, 20);
    getContentPane().add(lblCin);
    
    this.tfCedulaDetalle = new JTextField();
    this.tfCedulaDetalle.setEditable(false);
    this.tfCedulaDetalle.setColumns(10);
    this.tfCedulaDetalle.setBounds(114, 431, 130, 20);
    getContentPane().add(this.tfCedulaDetalle);
    
    JLabel lblSalario = new JLabel(" Salario:");
    lblSalario.setForeground(Color.WHITE);
    lblSalario.setBounds(282, 431, 52, 20);
    getContentPane().add(lblSalario);
    
    this.tfSalario = new JTextField();
    this.tfSalario.setEditable(false);
    this.tfSalario.setColumns(10);
    this.tfSalario.setBounds(335, 431, 122, 20);
    getContentPane().add(this.tfSalario);
    
    JLabel label_2 = new JLabel("Comisi�n:");
    label_2.setForeground(Color.WHITE);
    label_2.setBounds(274, 459, 81, 20);
    getContentPane().add(label_2);
    
    this.tfComision = new JTextField();
    this.tfComision.setEditable(false);
    this.tfComision.setColumns(10);
    this.tfComision.setBounds(335, 459, 45, 20);
    getContentPane().add(this.tfComision);
    
    this.tfTelefono = new JTextField();
    this.tfTelefono.setEditable(false);
    this.tfTelefono.setColumns(10);
    this.tfTelefono.setBounds(114, 457, 130, 20);
    getContentPane().add(this.tfTelefono);
    
    JLabel label_3 = new JLabel("Tel�fono:");
    label_3.setForeground(Color.WHITE);
    label_3.setBounds(58, 457, 67, 20);
    getContentPane().add(label_3);
    
    JLabel label_4 = new JLabel("Observaciones:");
    label_4.setForeground(Color.WHITE);
    label_4.setBounds(20, 507, 99, 14);
    getContentPane().add(label_4);
    
    JLabel label_5 = new JLabel("%");
    label_5.setForeground(Color.WHITE);
    label_5.setFont(new Font("Tahoma", 0, 15));
    label_5.setBounds(383, 462, 63, 14);
    getContentPane().add(label_5);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(114, 507, 343, 52);
    getContentPane().add(scrollPane);
    
    this.tfObservaciones = new JTextArea();
    this.tfObservaciones.setEditable(false);
    scrollPane.setViewportView(this.tfObservaciones);
    
    this.label_6 = new JLabel("  Direcci�n:");
    this.label_6.setForeground(Color.WHITE);
    this.label_6.setBounds(46, 483, 81, 20);
    getContentPane().add(this.label_6);
    
    this.tfDireccion = new JTextField();
    this.tfDireccion.setEditable(false);
    this.tfDireccion.setColumns(10);
    this.tfDireccion.setBounds(114, 483, 343, 20);
    getContentPane().add(this.tfDireccion);
    
    this.btnConsultar = new JButton("Consultar");
    this.btnConsultar.setIcon(new ImageIcon(Pagos.class.getResource("/Iconos/procesar.png")));
    this.btnConsultar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (Pagos.this.ValidarFecha())
        {
          Pagos.this.ConsultarDetallesYMovimientosDelVendedor();
          Pagos.this.cargarTablaContado();
          Pagos.this.cargarTablaCredito();
        }
      }
    });
    this.btnConsultar.setBounds(150, 590, 181, 57);
    getContentPane().add(this.btnConsultar);
    
    JLabel lblTotalAPagar = new JLabel("Comisi�n en efectivo:");
    lblTotalAPagar.setForeground(Color.WHITE);
    lblTotalAPagar.setBounds(500, 505, 165, 20);
    getContentPane().add(lblTotalAPagar);
    
    this.tfComisionEfectivo = new JTextField();
    this.tfComisionEfectivo.setEditable(false);
    this.tfComisionEfectivo.setColumns(10);
    this.tfComisionEfectivo.setBounds(661, 505, 135, 20);
    getContentPane().add(this.tfComisionEfectivo);
    
    this.tfTotalCredito = new JTextField();
    this.tfTotalCredito.setEditable(false);
    this.tfTotalCredito.setColumns(10);
    this.tfTotalCredito.setBounds(848, 431, 135, 20);
    getContentPane().add(this.tfTotalCredito);
    
    JLabel label_8 = new JLabel("Total a pagar:");
    label_8.setFont(new Font("Tahoma", 0, 15));
    label_8.setForeground(Color.WHITE);
    label_8.setBounds(500, 540, 165, 30);
    getContentPane().add(label_8);
    
    this.tfTotalAPagar = new JTextField();
    this.tfTotalAPagar.setFont(new Font("Tahoma", 1, 15));
    this.tfTotalAPagar.setEditable(false);
    this.tfTotalAPagar.setColumns(10);
    this.tfTotalAPagar.setBounds(661, 539, 224, 30);
    getContentPane().add(this.tfTotalAPagar);
    
    JLabel lblVentaACrdito = new JLabel("  Cr�dito:");
    lblVentaACrdito.setForeground(Color.WHITE);
    lblVentaACrdito.setBounds(793, 431, 71, 20);
    getContentPane().add(lblVentaACrdito);
    
    JLabel lblTotalDeVentas = new JLabel("Total de ventas realizadas:");
    lblTotalDeVentas.setForeground(Color.WHITE);
    lblTotalDeVentas.setBounds(500, 469, 165, 20);
    getContentPane().add(lblTotalDeVentas);
    
    this.tfTotalGeneral = new JTextField();
    this.tfTotalGeneral.setEditable(false);
    this.tfTotalGeneral.setColumns(10);
    this.tfTotalGeneral.setBounds(661, 469, 135, 20);
    getContentPane().add(this.tfTotalGeneral);
    
    this.tabbedPane = new JTabbedPane(1);
    this.tabbedPane.setBounds(10, 420, 470, 159);
    getContentPane().add(this.tabbedPane);
    
    this.tabbedPane_1 = new JTabbedPane(1);
    this.tabbedPane_1.setBounds(485, 420, 520, 159);
    getContentPane().add(this.tabbedPane_1);
    
    this.tfHasta = new JDateChooser();
    this.tfHasta.setDateFormatString("dd/MM/yyyy");
    this.tfHasta.setBounds(233, 74, 107, 20);
    getContentPane().add(this.tfHasta);
    
    this.lblFecha = new JLabel("Hasta:");
    this.lblFecha.setForeground(Color.WHITE);
    this.lblFecha.setBounds(192, 74, 60, 20);
    getContentPane().add(this.lblFecha);
    
    JLabel lblDesde = new JLabel("Desde:");
    lblDesde.setForeground(Color.WHITE);
    lblDesde.setBounds(25, 77, 60, 14);
    getContentPane().add(lblDesde);
    
    this.tfDesde = new JDateChooser();
    this.tfDesde.setDateFormatString("dd/MM/yyyy");
    this.tfDesde.setBounds(68, 74, 99, 20);
    getContentPane().add(this.tfDesde);
    
    JLabel lblContado = new JLabel("Contado:");
    lblContado.setForeground(Color.WHITE);
    lblContado.setBounds(593, 434, 130, 14);
    getContentPane().add(lblContado);
    
    this.tabbedPane_3 = new JTabbedPane(1);
    this.tabbedPane_3.setBounds(10, 65, 470, 71);
    getContentPane().add(this.tabbedPane_3);
    
    this.tabbedPane_2 = new JTabbedPane(1);
    this.tabbedPane_2.setBounds(10, 11, 470, 52);
    getContentPane().add(this.tabbedPane_2);
    
    this.tabbedPane_4 = new JTabbedPane(1);
    this.tabbedPane_4.setBounds(10, 583, 470, 74);
    getContentPane().add(this.tabbedPane_4);
    
    this.tabbedPane_5 = new JTabbedPane(1);
    this.tabbedPane_5.setBounds(485, 583, 520, 74);
    getContentPane().add(this.tabbedPane_5);
    
    this.tabbedPane_6 = new JTabbedPane(1);
    this.tabbedPane_6.setBounds(10, 139, 470, 273);
    getContentPane().add(this.tabbedPane_6);
    
    this.scrollPaneVendedores = new JScrollPane();
    this.tabbedPane_6.addTab("Lista de vendedores", null, this.scrollPaneVendedores, null);
    
    this.tablaVendedores = new JTable();
    this.scrollPaneVendedores.setViewportView(this.tablaVendedores);
    this.tablaVendedores.getTableHeader().setReorderingAllowed(false);
    this.tablaVendedores.setModel(modeloVendedores);
    this.tablaVendedores.getColumnModel().getColumn(0).setPreferredWidth(15);
    this.tablaVendedores.getColumnModel().getColumn(1).setPreferredWidth(176);
    
    this.tabbedPane_7 = new JTabbedPane(1);
    this.tabbedPane_7.setBounds(485, 11, 523, 191);
    getContentPane().add(this.tabbedPane_7);
    
    this.scrollPaneVentasRealizadas = new JScrollPane();
    this.tabbedPane_7.addTab("Lista de ventas realizadas - contado", null, this.scrollPaneVentasRealizadas, null);
    
    this.tablaFacContado = new JTable();
    this.tablaFacContado.setModel(modelofacturacontado);
    this.tablaFacContado.getColumnModel().getColumn(0).setPreferredWidth(22);
    this.tablaFacContado.getColumnModel().getColumn(1).setPreferredWidth(43);
    this.tablaFacContado.getColumnModel().getColumn(2).setPreferredWidth(43);
    this.tablaFacContado.getColumnModel().getColumn(3).setPreferredWidth(42);
    this.tablaFacContado.getColumnModel().getColumn(4).setPreferredWidth(125);
    this.tablaFacContado.getTableHeader().setReorderingAllowed(false);
    this.scrollPaneVentasRealizadas.setViewportView(this.tablaFacContado);
    
    this.tabbedPane_8 = new JTabbedPane(1);
    this.tabbedPane_8.setBounds(485, 208, 523, 204);
    getContentPane().add(this.tabbedPane_8);
    
    this.scrollPane_1 = new JScrollPane();
    this.tabbedPane_8.addTab("Lista de ventas realizadas - cr�dito", null, this.scrollPane_1, null);
    
    this.tablaFactCredito = new JTable();
    this.tablaFactCredito.setModel(modelofacturacredito);
    this.tablaFactCredito.getColumnModel().getColumn(0).setPreferredWidth(22);
    this.tablaFactCredito.getColumnModel().getColumn(1).setPreferredWidth(43);
    this.tablaFactCredito.getColumnModel().getColumn(2).setPreferredWidth(43);
    this.tablaFactCredito.getColumnModel().getColumn(3).setPreferredWidth(42);
    this.tablaFactCredito.getColumnModel().getColumn(4).setPreferredWidth(125);
    this.tablaFactCredito.getTableHeader().setReorderingAllowed(false);
    this.scrollPane_1.setViewportView(this.tablaFactCredito);
    
    this.label_7 = new JLabel("");
    this.label_7.setIcon(new ImageIcon(new File("data/graficos/Transacciones.jpg").getAbsolutePath()));
    this.label_7.setBounds(0, 0, 1018, 668);
    getContentPane().add(this.label_7);
    
    cargarTodosLosRegistros();
  }
  
  private void ConsultarDetallesYMovimientosDelVendedor()
  {
    int filaSeleccionada = this.tablaVendedores.getSelectedRow();
    if (filaSeleccionada < 0)
    {
      JOptionPane.showMessageDialog(null, "Seleccione un vendedor de la lista para visualizar sus detalles y movimientos");
    }
    else
    {
      int codigo = Integer.parseInt(String.valueOf(this.tablaVendedores.getValueAt(filaSeleccionada, 0)));
      ModeloVendedores v = DaoVendedores.ConsutarPorCodigo(codigo);
      this.tfCedulaDetalle.setText(v.getCedula());
      this.tfComision.setText(String.valueOf(v.getComision()));
      this.tfDireccion.setText(v.getDireccion());
      this.tfObservaciones.setText(v.getObservaciones());
      int salario = (int)v.getSalario();
      this.tfSalario.setText(String.valueOf(salario));
      this.tfTelefono.setText(v.getContacto());
    }
  }
  
  public void consultarPorCedula()
  {
    listaVendedores = new ArrayList();
    listaVendedores = DaoVendedores.consultarPorCedula(this.tfCedula.getText());
    ActualizarTablaVendedor();
  }
  
  public void consultarPorNombre()
  {
    listaVendedores = new ArrayList();
    listaVendedores = DaoVendedores.ConsultarPorNombre(this.tfNombre.getText());
    ActualizarTablaVendedor();
  }
  
  public void cargarTodosLosRegistros()
  {
    listaVendedores = new ArrayList();
    listaVendedores = DaoVendedores.consultarTodoElRegistro();
    ActualizarTablaVendedor();
  }
  
  private static void ActualizarTablaVendedor()
  {
    while (modeloVendedores.getRowCount() > 0) {
      modeloVendedores.removeRow(0);
    }
    String[] campos = new String[2];
    for (int i = 0; i < listaVendedores.size(); i++)
    {
      modeloVendedores.addRow(campos);
      ModeloVendedores vendedor = (ModeloVendedores)listaVendedores.get(i);
      
      modeloVendedores.setValueAt(Integer.valueOf(vendedor.getCodigo()), i, 0);
      modeloVendedores.setValueAt(vendedor.getNombre(), i, 1);
    }
  }
  
  public void cargarTablaContado()
  {
    String formato = this.tfDesde.getDateFormatString();
    String formato2 = this.tfHasta.getDateFormatString();
    
    Date date = this.tfDesde.getDate();
    Date date2 = this.tfHasta.getDate();
    
    SimpleDateFormat sdf = new SimpleDateFormat(formato);
    SimpleDateFormat sdf2 = new SimpleDateFormat(formato2);
    String Fecha1 = String.valueOf(sdf.format(date));
    String Fecha2 = String.valueOf(sdf2.format(date2));
    
    int fila = this.tablaVendedores.getSelectedRow();
    int codigoVendedor = Integer.parseInt(String.valueOf(this.tablaVendedores.getValueAt(fila, 0)));
    listafacturacontado = new ArrayList();
    listafacturacontado = DaoPagos.consultarFacturaCabeceraContado(Fecha1, Fecha2, codigoVendedor);
    ActualizarTablaContado();
  }
  
  public void cargarTablaCredito()
  {
    String formato = this.tfDesde.getDateFormatString();
    String formato2 = this.tfHasta.getDateFormatString();
    
    Date date = this.tfDesde.getDate();
    Date date2 = this.tfHasta.getDate();
    
    SimpleDateFormat sdf = new SimpleDateFormat(formato);
    SimpleDateFormat sdf2 = new SimpleDateFormat(formato2);
    String Fecha1 = String.valueOf(sdf.format(date));
    String Fecha2 = String.valueOf(sdf2.format(date2));
    
    int fila = this.tablaVendedores.getSelectedRow();
    int codigoVendedor = Integer.parseInt(String.valueOf(this.tablaVendedores.getValueAt(fila, 0)));
    listafacturacredito = new ArrayList();
    listafacturacredito = DaoPagos.consultarFacturaCabeceraCredito(Fecha1, Fecha2, codigoVendedor);
    ActualizarTablaCredito();
  }
  
  private static void ActualizarTablaContado()
  {
    while (modelofacturacontado.getRowCount() > 0) {
      modelofacturacontado.removeRow(0);
    }
    String[] campos = new String[2];
    for (int i = 0; i < listafacturacontado.size(); i++)
    {
      modelofacturacontado.addRow(campos);
      ModeloFacturaContado factura = (ModeloFacturaContado)listafacturacontado.get(i);
      
      modelofacturacontado.setValueAt(Integer.valueOf(factura.getCodigoCabecera()), i, 0);
      modelofacturacontado.setValueAt(factura.getFecha(), i, 1);
      modelofacturacontado.setValueAt(Double.valueOf(factura.getTotal()), i, 2);
      modelofacturacontado.setValueAt("Contado", i, 3);
      modelofacturacontado.setValueAt(factura.getVendedor().getNombre(), i, 4);
    }
  }
  
  private static void ActualizarTablaCredito()
  {
    while (modelofacturacredito.getRowCount() > 0) {
      modelofacturacredito.removeRow(0);
    }
    String[] campos = new String[2];
    for (int i = 0; i < listafacturacredito.size(); i++)
    {
      modelofacturacredito.addRow(campos);
      ModeloFacturaCredito factura = (ModeloFacturaCredito)listafacturacredito.get(i);
      
      modelofacturacredito.setValueAt(Integer.valueOf(factura.getCodigoCabecera()), i, 0);
      modelofacturacredito.setValueAt(factura.getFecha(), i, 1);
      modelofacturacredito.setValueAt(Double.valueOf(factura.getTotal()), i, 2);
      modelofacturacredito.setValueAt("Cr�dito", i, 3);
      modelofacturacredito.setValueAt(factura.getVendedor().getNombre(), i, 4);
    }
  }
  
  private void CalcularComision()
  {
    double comision = Double.parseDouble(this.tfComision.getText());
    double totalVentas = Double.parseDouble(this.tfTotalGeneral.getText());
    double salario = Double.parseDouble(this.tfSalario.getText());
    double subtotal = 0.0D;
    int totalGeneral = 0;
    
    subtotal = totalVentas * comision / 100.0D;
    
    totalGeneral = (int)(subtotal + salario);
    
    this.tfComisionEfectivo.setText(String.valueOf(subtotal));
    this.tfTotalAPagar.setText(String.valueOf(totalGeneral));
  }
  
  private void CalcularMontoFacturas()
  {
    double sumatoria1 = 0.0D;
    double sumatoria2 = 0.0D;
    double total1 = 0.0D;
    double total2 = 0.0D;
    int totalRow1 = this.tablaFacContado.getRowCount();
    totalRow1--;
    int totalRow2 = this.tablaFactCredito.getRowCount();
    totalRow2--;
    int totalInt1 = 0;
    int totalInt2 = 0;
    double totalGral = 0.0D;
    for (int i = 0; i <= totalRow1; i++)
    {
      if (this.tablaFacContado.getValueAt(i, 2).equals("")) {
        sumatoria1 = 0.0D;
      } else {
        sumatoria1 = Double.parseDouble(String.valueOf(this.tablaFacContado.getValueAt(i, 2)));
      }
      total1 += sumatoria1;
    }
    for (int i = 0; i <= totalRow2; i++)
    {
      if (this.tablaFactCredito.getValueAt(i, 2).equals("")) {
        sumatoria2 = 0.0D;
      } else {
        sumatoria2 = Double.parseDouble(String.valueOf(this.tablaFactCredito.getValueAt(i, 2)));
      }
      total2 += sumatoria2;
    }
    totalGral = total1 + total2;
    if (totalGral > 9999999.0D)
    {
      int total3xD = (int)totalGral;
      this.tfTotalGeneral.setText(String.valueOf(total3xD));
    }
    else
    {
      this.tfTotalGeneral.setText(String.valueOf(totalGral));
    }
    if (total1 > 9999999.0D)
    {
      totalInt1 = (int)total1;
      String totalObtenido = String.valueOf(totalInt1);
      
      this.tfTotalContado.setText(totalObtenido);
    }
    else
    {
      String totalObtenido = String.valueOf(total1);
      
      this.tfTotalContado.setText(totalObtenido);
    }
    if (total2 > 9999999.0D)
    {
      totalInt2 = (int)total1;
      String totalObtenido = String.valueOf(totalInt2);
      
      this.tfTotalCredito.setText(totalObtenido);
    }
    else
    {
      String totalObtenido = String.valueOf(total2);
      
      this.tfTotalCredito.setText(totalObtenido);
    }
  }
  
  private boolean ValidarFecha()
  {
    if ((this.tfDesde.getDate() == null) || (this.tfHasta.getDate() == null))
    {
      JOptionPane.showMessageDialog(null, "Complete el rango de fecha", "Aviso", 2);
      return false;
    }
    return true;
  }   
}
