/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Tesoreria;

import com.eneri.scstock.Apariencia.FondoFormularios;
import com.eneri.scstock.Modelos.ModeloCaja;
import com.eneri.scstock.Modelos.ModeloFacturaContado;
import com.eneri.scstock.Modelos.ModeloFacturaCredito;
import com.eneri.scstock.Modelos.ModeloRetiro;
import com.eneri.scstock.Objetos.DaoCaja;
import com.eneri.scstock.Objetos.DaoRetiros;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
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
public class Caja extends JDialog
{
   private JTable tablaContado;
  private final JPanel contentPanel = new JPanel();
  private JScrollPane scrollPane1;
  public static List<ModeloFacturaContado> listaFacturaContado;
  public static List<ModeloFacturaCredito> listafacturaCredito;
  public static List<ModeloRetiro> listaRetiros;
  public static DefaultTableModel modelocontado = new DefaultTableModel(null, new String[] { "N� Fact.", "Fecha emisi�n", "Hora emisi�n", "C�d. cliente", "Total" })
  {
    boolean[] columnEditables = new boolean[5];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  public static DefaultTableModel modelocredito = new DefaultTableModel(null, new String[] { "N� Fact.", "Fecha emisi�n", "Hora emisi�n", "C�d. cliente", "Total" })
  {
    boolean[] columnEditables = new boolean[5];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  public static DefaultTableModel modeloRetiro = new DefaultTableModel(null, new String[] { "C�d. retiro", "Fecha", "Hora", "Monto", "Detalle" })
  {
    boolean[] columnEditables = new boolean[5];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private JTable tablaCredito;
  private JButton btnCerrarCaja;
  private JTextField tfTotalGeneral;
  public static JButton btnConsultar;
  private JLabel lblTotalGeneral;
  private JTextField tfColor;
  private JLabel lblCierreDeCaja;
  private ModeloCaja cajaInfo;
  private JTable tbRetiros;
  private JTabbedPane tabbedPane_1;
  private JTabbedPane tabbedPane_2;
  private JTextField tfRetiro;
  private JTextField tfSaldo;
  private JTabbedPane tabbedPane_3;
  private JTextField tfTotalCredito;
  private JLabel label;
  private JTextField tfTotalContado;
  private JLabel label_1;
  private JLabel label_2;
  private JTabbedPane tabbedPane_4;
  private JLabel lblDa;
  
  public static void main(String[] args)
  {
    try
    {
      Caja dialog = new Caja();
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
  
  public Caja()
  {
    setTitle("Arqueo y cierre de caja");
    setResizable(false);
    setBounds(100, 100, 1024, 686);
    setIconImage(Toolkit.getDefaultToolkit().getImage(Caja.class.getResource("/Iconos/cobranzaIcon.png")));
    
    FondoFormularios contentPanel = new FondoFormularios();
    contentPanel.setForeground(Color.BLACK);
    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPanel);
    contentPanel.setLayout(null);
    
    this.btnCerrarCaja = new JButton("Cerrar");
    this.btnCerrarCaja.setEnabled(false);
    this.btnCerrarCaja.setIcon(new ImageIcon(Caja.class.getResource("/Iconos/Cerrar.png")));
    this.btnCerrarCaja.setBounds(300, 564, 148, 57);
    this.btnCerrarCaja.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0) {}
    });
    contentPanel.add(this.btnCerrarCaja);
    
    this.tfTotalGeneral = new JTextField();
    this.tfTotalGeneral.setToolTipText("Total general en caja");
    this.tfTotalGeneral.setBounds(181, 412, 175, 20);
    this.tfTotalGeneral.setEditable(false);
    this.tfTotalGeneral.setColumns(10);
    contentPanel.add(this.tfTotalGeneral);
    
    this.lblTotalGeneral = new JLabel("Total facturas:");
    this.lblTotalGeneral.setToolTipText("");
    this.lblTotalGeneral.setForeground(Color.WHITE);
    this.lblTotalGeneral.setBounds(88, 412, 111, 20);
    contentPanel.add(this.lblTotalGeneral);
    
    btnConsultar = new JButton("Consultar");
    btnConsultar.setIcon(new ImageIcon(Caja.class.getResource("/Iconos/procesar.png")));
    btnConsultar.setBounds(135, 564, 148, 57);
    btnConsultar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Caja.this.ConsultarCajaContado();
        Caja.this.ConsultarCajaCredito();
        Caja.this.CalcularTotalGralContado();
        Caja.this.CalcularTotalGralCredito();
        Caja.this.CalcularTotalFacturas();
        Caja.this.ConsultarRetiros();
        Caja.this.CalcularTotalRetiro();
        
        Caja.this.CalcularMontoEnCaja();
        Caja.this.btnCerrarCaja.setEnabled(true);
      }
    });
    contentPanel.add(btnConsultar);
    
    this.tfColor = new JTextField();
    this.tfColor.setVisible(false);
    this.tfColor.setBounds(308, 11, 86, 20);
    contentPanel.add(this.tfColor);
    this.tfColor.setColumns(10);
    
    this.lblCierreDeCaja = new JLabel("Cierre de caja");
    this.lblCierreDeCaja.setToolTipText("");
    this.lblCierreDeCaja.setForeground(Color.WHITE);
    this.lblCierreDeCaja.setFont(new Font("Times New Roman", 0, 25));
    this.lblCierreDeCaja.setBounds(58, 302, 179, 40);
    contentPanel.add(this.lblCierreDeCaja);
    
    JTabbedPane tabbedPane = new JTabbedPane(1);
    tabbedPane.setBounds(10, 14, 505, 285);
    contentPanel.add(tabbedPane);
    
    JScrollPane scrollPane_1 = new JScrollPane();
    tabbedPane.addTab("Retiros", null, scrollPane_1, null);
    
    this.tbRetiros = new JTable();
    this.tbRetiros.getTableHeader().setReorderingAllowed(false);
    this.tbRetiros.setModel(modeloRetiro);
    scrollPane_1.setViewportView(this.tbRetiros);
    
    this.tabbedPane_1 = new JTabbedPane(1);
    this.tabbedPane_1.setBounds(519, 300, 489, 347);
    contentPanel.add(this.tabbedPane_1);
    
    JScrollPane scrollPane = new JScrollPane();
    this.tabbedPane_1.addTab("Facturas emitidas | Condici�n: CR�DITO", null, scrollPane, null);
    
    this.tablaCredito = new JTable();
    this.tablaCredito.setModel(modelocredito);
    scrollPane.setViewportView(this.tablaCredito);
    this.tablaCredito.setForeground(Color.WHITE);
    this.tablaCredito.setBackground(new Color(51, 51, 51));
    
    this.tabbedPane_2 = new JTabbedPane(1);
    this.tabbedPane_2.setBounds(519, 11, 489, 288);
    contentPanel.add(this.tabbedPane_2);
    
    this.scrollPane1 = new JScrollPane();
    this.tabbedPane_2.addTab("Facturas emitidas | Condici�n: CONTADO", null, this.scrollPane1, null);
    
    this.tablaContado = new JTable();
    this.tablaContado.setForeground(Color.WHITE);
    this.tablaContado.setBackground(new Color(51, 51, 51));
    this.scrollPane1.setViewportView(this.tablaContado);
    this.tablaContado.setModel(modelocontado);
    this.tablaContado.getColumnModel().getColumn(0).setPreferredWidth(55);
    this.tablaContado.getColumnModel().getColumn(1).setPreferredWidth(61);
    this.tablaContado.getColumnModel().getColumn(2).setPreferredWidth(58);
    this.tablaContado.getTableHeader().setReorderingAllowed(false);
    
    this.tfRetiro = new JTextField();
    this.tfRetiro.setEditable(false);
    this.tfRetiro.setColumns(10);
    this.tfRetiro.setBounds(181, 461, 175, 20);
    contentPanel.add(this.tfRetiro);
    
    this.tfSaldo = new JTextField();
    this.tfSaldo.setFont(new Font("Tahoma", 0, 20));
    this.tfSaldo.setEditable(false);
    this.tfSaldo.setColumns(10);
    this.tfSaldo.setBounds(135, 503, 313, 50);
    contentPanel.add(this.tfSaldo);
    
    JLabel lblSaldo = new JLabel("Monto en caja:");
    lblSaldo.setToolTipText("");
    lblSaldo.setForeground(Color.WHITE);
    lblSaldo.setBounds(41, 518, 102, 20);
    contentPanel.add(lblSaldo);
    
    JLabel lblTotalRetiros = new JLabel("Total retiros:");
    lblTotalRetiros.setForeground(Color.WHITE);
    lblTotalRetiros.setBounds(101, 462, 73, 20);
    contentPanel.add(lblTotalRetiros);
    
    this.tabbedPane_3 = new JTabbedPane(1);
    this.tabbedPane_3.setBounds(10, 302, 505, 40);
    contentPanel.add(this.tabbedPane_3);
    
    this.tfTotalCredito = new JTextField();
    this.tfTotalCredito.setEditable(false);
    this.tfTotalCredito.setColumns(10);
    this.tfTotalCredito.setBounds(368, 362, 135, 20);
    contentPanel.add(this.tfTotalCredito);
    
    this.label = new JLabel("  Cr�dito:");
    this.label.setForeground(Color.WHITE);
    this.label.setBounds(313, 362, 71, 20);
    contentPanel.add(this.label);
    
    this.tfTotalContado = new JTextField();
    this.tfTotalContado.setEditable(false);
    this.tfTotalContado.setColumns(10);
    this.tfTotalContado.setBounds(181, 362, 135, 20);
    contentPanel.add(this.tfTotalContado);
    
    this.label_1 = new JLabel("Total ventas:");
    this.label_1.setForeground(Color.WHITE);
    this.label_1.setBounds(20, 365, 86, 14);
    contentPanel.add(this.label_1);
    
    this.label_2 = new JLabel("Contado:");
    this.label_2.setForeground(Color.WHITE);
    this.label_2.setBounds(118, 365, 119, 14);
    contentPanel.add(this.label_2);
    
    this.tabbedPane_4 = new JTabbedPane(1);
    this.tabbedPane_4.setBounds(10, 343, 505, 304);
    contentPanel.add(this.tabbedPane_4);
    
    this.lblDa = new JLabel("del d�a de:");
    this.lblDa.setForeground(Color.WHITE);
    this.lblDa.setBounds(276, 302, 80, 40);
    contentPanel.add(this.lblDa);
    
    JDateChooser dateChooser = new JDateChooser();
    dateChooser.setBounds(368, 310, 130, 20);
    contentPanel.add(dateChooser);
  }
  
  private void ConsultarCajaContado()
  {
    listaFacturaContado = new ArrayList();
    listaFacturaContado = DaoCaja.consultarFacturaCabeceraContado(fecha());
    ActualizarTablaContado();
  }
  
  public void ConsultarCajaCredito()
  {
    if (this.rootPaneCheckingEnabled)
    {
      listafacturaCredito = new ArrayList();
      listafacturaCredito = DaoCaja.consultarFacturaCabeceraCredito(fecha());
      ActualizarTablaCredito();
    }
  }
  
  public void ConsultarRetiros()
  {
    listaRetiros = new ArrayList();
    listaRetiros = DaoRetiros.ConsultarRetiros(fecha());
    ActualizarTablaRetiro();
  }
  
  private static void ActualizarTablaContado()
  {
    while (modelocontado.getRowCount() > 0) {
      modelocontado.removeRow(0);
    }
    String[] campos = new String[5];
    for (int i = 0; i < listaFacturaContado.size(); i++)
    {
      modelocontado.addRow(campos);
      
      ModeloFacturaContado factura = (ModeloFacturaContado)listaFacturaContado.get(i);
      modelocontado.setValueAt(Integer.valueOf(factura.getCodigoCabecera()), i, 0);
      modelocontado.setValueAt(factura.getFecha(), i, 1);
      modelocontado.setValueAt(factura.getHora(), i, 2);
      modelocontado.setValueAt(Integer.valueOf(factura.getCodigoCliente()), i, 3);
      modelocontado.setValueAt(Double.valueOf(factura.getTotal()), i, 4);
    }
  }
  
  private static void ActualizarTablaRetiro()
  {
    while (modeloRetiro.getRowCount() > 0) {
      modeloRetiro.removeRow(0);
    }
    String[] campos = new String[5];
    for (int i = 0; i < listaRetiros.size(); i++)
    {
      modeloRetiro.addRow(campos);
      ModeloRetiro retiro = (ModeloRetiro)listaRetiros.get(i);
      
      modeloRetiro.setValueAt(Integer.valueOf(retiro.getCodigo()), i, 0);
      modeloRetiro.setValueAt(retiro.getFecha(), i, 1);
      modeloRetiro.setValueAt(retiro.getHora(), i, 2);
      modeloRetiro.setValueAt(Double.valueOf(retiro.getMonto()), i, 3);
      modeloRetiro.setValueAt(retiro.getDetalle(), i, 4);
    }
  }
  
  private static void ActualizarTablaCredito()
  {
    while (modelocredito.getRowCount() > 0) {
      modelocredito.removeRow(0);
    }
    String[] campos = new String[5];
    for (int i = 0; i < listafacturaCredito.size(); i++)
    {
      modelocredito.addRow(campos);
      ModeloFacturaCredito cobranza = (ModeloFacturaCredito)listafacturaCredito.get(i);
      
      modelocredito.setValueAt(Integer.valueOf(cobranza.getCodigoCabecera()), i, 0);
      modelocredito.setValueAt(cobranza.getFecha(), i, 1);
      modelocredito.setValueAt(cobranza.getHora(), i, 2);
      modelocredito.setValueAt(Integer.valueOf(cobranza.getCodigoCliente()), i, 3);
      modelocredito.setValueAt(Double.valueOf(cobranza.getEntrega()), i, 4);
    }
  }
  
  public void CalcularTotalGralContado()
  {
    double sumatoria = 0.0D;
    double total = 0.0D;
    int totalInt = 0;
    int totalRow = this.tablaContado.getRowCount();
    totalRow--;
    for (int i = 0; i <= totalRow; i++)
    {
      if (this.tablaContado.getValueAt(i, 4).equals("")) {
        sumatoria = 0.0D;
      } else {
        sumatoria = Double.parseDouble(String.valueOf(this.tablaContado.getValueAt(i, 4)));
      }
      total += sumatoria;
    }
    if (total > 9999999.0D)
    {
      totalInt = (int)total;
      String totalObtenido = String.valueOf(totalInt);
      
      this.tfTotalContado.setText(totalObtenido);
    }
    else
    {
      String totalObtenido = String.valueOf(total);
      
      this.tfTotalContado.setText(totalObtenido);
    }
  }
  
  public void CalcularTotalGralCredito()
  {
    double sumatoria = 0.0D;
    double total = 0.0D;
    int totalRow = this.tablaCredito.getRowCount();
    totalRow--;
    int totalInt = 0;
    for (int i = 0; i <= totalRow; i++)
    {
      if (this.tablaCredito.getValueAt(i, 4).equals("")) {
        sumatoria = 0.0D;
      } else {
        sumatoria = Double.parseDouble(String.valueOf(this.tablaCredito.getValueAt(i, 4)));
      }
      total += sumatoria;
    }
    if (total > 9999999.0D)
    {
      totalInt = (int)total;
      String totalObtenido = String.valueOf(totalInt);
      
      this.tfTotalCredito.setText(totalObtenido);
    }
    else
    {
      String totalObtenido = String.valueOf(total);
      
      this.tfTotalCredito.setText(totalObtenido);
    }
  }
  
  public void CalcularTotalRetiro()
  {
    double sumatoria = 0.0D;
    double total = 0.0D;
    int totalRow = this.tbRetiros.getRowCount();
    totalRow--;
    int totalInt = 0;
    for (int i = 0; i <= totalRow; i++)
    {
      if (this.tbRetiros.getValueAt(i, 3).equals("")) {
        sumatoria = 0.0D;
      } else {
        sumatoria = Double.parseDouble(String.valueOf(this.tbRetiros.getValueAt(i, 3)));
      }
      total += sumatoria;
    }
    if (total > 9999999.0D)
    {
      totalInt = (int)total;
      String totalObtenido = String.valueOf(totalInt);
      
      this.tfRetiro.setText(totalObtenido);
    }
    else
    {
      String totalObtenido = String.valueOf(total);
      
      this.tfRetiro.setText(totalObtenido);
    }
  }
  
  public String CalcularTotalFacturas()
  {
    double contado = 0.0D;
    double credito = 0.0D;
    double total = 0.0D;
    contado = Double.parseDouble(this.tfTotalContado.getText());
    credito = Double.parseDouble(this.tfTotalCredito.getText());
    total = contado + credito;
    if (total > 9999999.0D) {
      total = (int)(contado + credito);
    }
    String totalObtenido = String.valueOf(total);
    
    this.tfTotalGeneral.setText(totalObtenido);
    
    return totalObtenido;
  }
  
  public String CalcularMontoEnCaja()
  {
    double totalFacturas = 0.0D;
    double totalRetiro = 0.0D;
    double totalEnCaja = 0.0D;
    totalFacturas = Double.parseDouble(this.tfTotalGeneral.getText());
    totalRetiro = Double.parseDouble(this.tfRetiro.getText());
    totalEnCaja = totalFacturas - totalRetiro;
    if (totalEnCaja > 9999999.0D) {
      totalEnCaja = (int)(totalFacturas + totalRetiro);
    }
    String totalObtenido = String.valueOf(totalEnCaja);
    
    this.tfSaldo.setText(totalObtenido);
    
    return totalObtenido;
  }
  
  public static String fecha()
  {
    Date fecha = new Date();
    SimpleDateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");
    return fechaFormato.format(fecha);
  }
  
  public static String hora()
  {
    Calendar Cal = Calendar.getInstance();
    String hora = String.valueOf(Cal.get(11));
    String minuto = String.valueOf(Cal.get(12));
    String segundo = String.valueOf(Cal.get(13));
    
    String hora1 = "";
    String minuto1 = "";
    String segundo1 = "";
    String horacompleta = "";
    if (hora.length() == 1) {
      hora1 = "0" + Cal.get(11);
    } else {
      hora1 = hora;
    }
    if (minuto.length() == 1) {
      minuto1 = "0" + Cal.get(12);
    } else {
      minuto1 = minuto;
    }
    if (segundo.length() == 1) {
      segundo1 = "0" + Cal.get(13);
    } else {
      segundo1 = segundo;
    }
    horacompleta = hora1 + ":" + minuto1 + ":" + segundo1;
    
    return horacompleta;
  } 
}
