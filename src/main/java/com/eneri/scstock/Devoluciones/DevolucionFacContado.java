/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Devoluciones;

import com.eneri.scstock.Apariencia.FondoTransacciones;
import com.eneri.scstock.Herramientas.ConversionEnLetras;
import com.eneri.scstock.Herramientas.Validaciones;
import com.eneri.scstock.Modelos.ModeloFacturaContado;
import com.eneri.scstock.Modelos.ModeloInformaciones;
import com.eneri.scstock.Modelos.ModeloProductos;
import com.eneri.scstock.Objetos.DaoDevolucionesFacContado;
import com.eneri.scstock.Objetos.DaoFacturaContado;
import com.eneri.scstock.Objetos.DaoInformaciones;
import com.eneri.scstock.Objetos.DaoProductos;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

/**
 *
 * @author RAscencio
 */
public class DevolucionFacContado extends JDialog
{    
    private final JPanel contentPanel = new JPanel();
  private JLabel lblTotal;
  public static List<ModeloFacturaContado> listaFacturaCabecera;
  public static List<ModeloFacturaContado> listaFacturaDetalle;
  
  public static void main(String[] args)
  {
    try
    {
      DevolucionFacContado dialog = new DevolucionFacContado();
      dialog.setLocationRelativeTo(null);
      dialog.setModal(true);
      dialog.setVisible(true);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public static DefaultTableModel modelocabecera = new DefaultTableModel(null, new String[] { "N� Fact.", "N�Ced/RUC", "Nombre y apellido del cliente", "Fecha de compra", "Hora", "Total", "Estado" })
  {
    boolean[] columnEditables = new boolean[7];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  public static DefaultTableModel modelodetalle = new DefaultTableModel(null, new String[] { "N� Det.", "Cantidad", "C�d.", "Descripci�n", "P. Unit.", "Subtotal" })
  {
    boolean[] columnEditables = new boolean[6];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private JTable tablaCabecera;
  private JLabel lblFactura;
  private JTable tablaDetalle;
  private JButton btnDetalle;
  private JScrollPane scrollPaneDetalle;
  private JTextField tfTotal;
  private JButton btnDevolver;
  private JScrollPane scrollPaneCabecera;
  private JLabel lblNFactura;
  private JTextField tfBuscarPorCedula;
  private JLabel lblCliente;
  private JTextField tfBuscarPorNombre;
  private JTextField tfBuscarPorNFactura;
  private JLabel lblImg;
  public static JButton btnActualizar;
  private JTextField tfColor;
  private JTextField tfNFactura;
  private JButton btnGuardar;
  private JLabel label_1;
  private JTabbedPane tabbedPane_3;
  
  public DevolucionFacContado()
  {
    setResizable(false);
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent evt)
      {
        DevolucionFacContado.this.Salir(evt);
      }
    });
    setDefaultCloseOperation(0);
    setBounds(100, 100, 1189, 643);
    setTitle("Devoluci�n de mercader�a - Venta contado");
    
    setIconImage(Toolkit.getDefaultToolkit().getImage(DevolucionFacContado.class.getResource("/Galeria/Devoluciones.png")));
    FondoTransacciones contentPane = new FondoTransacciones();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    this.btnDevolver = new JButton("Devolver");
    this.btnDevolver.setToolTipText("Devolver la mercader�a y actualizar el stock del producto.");
    this.btnDevolver.setEnabled(false);
    this.btnDevolver.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        int seleccion = DevolucionFacContado.this.tablaDetalle.getSelectedRow();
        if (seleccion < 0)
        {
          JOptionPane.showMessageDialog(null, "Seleccione el producto que ser� devuelto.", "Sin selecci�n", 2);
        }
        else
        {
          DevolucionFacContado.this.DevolverMercaderia();
          DevolucionFacContado.this.btnDevolver.setEnabled(false);
        }
      }
    });
    this.btnDevolver.setIcon(new ImageIcon(DevolucionFacContado.class.getResource("/Iconos/Devolucion.png")));
    this.btnDevolver.setMnemonic('C');
    this.btnDevolver.setBounds(374, 547, 153, 57);
    contentPane.add(this.btnDevolver);
    
    JLabel label = new JLabel("");
    label.setIcon(new ImageIcon(DevolucionFacContado.class.getResource("/Iconos/dinero.png")));
    label.setFont(new Font("Tahoma", 1, 15));
    label.setBounds(1115, 555, 48, 49);
    contentPane.add(label);
    
    this.tfTotal = new JTextField();
    this.tfTotal.setBackground(Color.GRAY);
    this.tfTotal.setEditable(false);
    this.tfTotal.setBounds(880, 555, 225, 49);
    this.tfTotal.setFont(new Font("Microsoft Yi Baiti", 1, 45));
    contentPane.add(this.tfTotal);
    this.tfTotal.setColumns(10);
    
    this.lblTotal = new JLabel("TOTAL COMPRA:");
    this.lblTotal.setForeground(Color.WHITE);
    this.lblTotal.setFont(new Font("Tahoma", 1, 15));
    this.lblTotal.setBounds(747, 559, 143, 36);
    getContentPane().add(this.lblTotal);
    
    this.scrollPaneCabecera = new JScrollPane();
    this.scrollPaneCabecera.setToolTipText("Factura cabecera");
    this.scrollPaneCabecera.setBounds(210, 58, 953, 218);
    contentPane.add(this.scrollPaneCabecera);
    
    this.tablaCabecera = new JTable();
    this.tablaCabecera.setForeground(Color.WHITE);
    this.tablaCabecera.setBackground(new Color(51, 51, 51));
    this.tablaCabecera.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        DevolucionFacContado.this.LimpiarDetalle();
        DevolucionFacContado.this.btnDetalle.setEnabled(true);
        DevolucionFacContado.this.btnDevolver.setEnabled(false);
        DevolucionFacContado.this.tfTotal.setToolTipText("");
      }
    });
    this.scrollPaneCabecera.setViewportView(this.tablaCabecera);
    this.tablaCabecera.setModel(modelocabecera);
    this.tablaCabecera.getTableHeader().setReorderingAllowed(false);
    this.tablaCabecera.getColumnModel().getColumn(0).setPreferredWidth(54);
    this.tablaCabecera.getColumnModel().getColumn(1).setPreferredWidth(61);
    this.tablaCabecera.getColumnModel().getColumn(2).setPreferredWidth(246);
    this.tablaCabecera.getColumnModel().getColumn(3).setPreferredWidth(62);
    this.tablaCabecera.getColumnModel().getColumn(4).setPreferredWidth(51);
    this.tablaCabecera.getColumnModel().getColumn(5).setPreferredWidth(72);
    
    this.lblFactura = new JLabel("Devoluciones");
    this.lblFactura.setFont(new Font("Tahoma", 1, 20));
    this.lblFactura.setForeground(Color.WHITE);
    this.lblFactura.setBounds(38, 17, 166, 25);
    contentPane.add(this.lblFactura);
    
    this.scrollPaneDetalle = new JScrollPane();
    this.scrollPaneDetalle.setToolTipText("Factura detalle");
    this.scrollPaneDetalle.setBounds(210, 283, 953, 256);
    contentPane.add(this.scrollPaneDetalle);
    
    this.tablaDetalle = new JTable();
    this.tablaDetalle.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        DevolucionFacContado.this.btnDevolver.setEnabled(true);
      }
    });
    this.tablaDetalle.setForeground(Color.WHITE);
    this.tablaDetalle.setBackground(new Color(51, 51, 51));
    this.tablaDetalle.getTableHeader().setReorderingAllowed(false);
    this.scrollPaneDetalle.setViewportView(this.tablaDetalle);
    this.tablaDetalle.setModel(modelodetalle);
    this.tablaDetalle.getColumnModel().getColumn(0).setPreferredWidth(30);
    this.tablaDetalle.getColumnModel().getColumn(1).setPreferredWidth(22);
    this.tablaDetalle.getColumnModel().getColumn(2).setPreferredWidth(41);
    this.tablaDetalle.getColumnModel().getColumn(3).setPreferredWidth(394);
    this.tablaDetalle.getColumnModel().getColumn(4).setPreferredWidth(48);
    
    this.btnDetalle = new JButton("Ver detalle");
    this.btnDetalle.setEnabled(false);
    this.btnDetalle.setMnemonic('D');
    this.btnDetalle.setToolTipText("Ver el detalle de la factura");
    this.btnDetalle.setIcon(new ImageIcon(DevolucionFacContado.class.getResource("/Iconos/icono-N�mina.png")));
    this.btnDetalle.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        DevolucionFacContado.this.ConsultarDetalle();
        DevolucionFacContado.this.CalcularTotalDetalle();
        DevolucionFacContado.this.tfTotal.setToolTipText(DevolucionFacContado.this.ConversionTotalCompra() + " GUARANIES");
        DevolucionFacContado.this.btnDetalle.setEnabled(false);
      }
    });
    this.btnDetalle.setBounds(210, 547, 153, 57);
    contentPane.add(this.btnDetalle);
    
    this.lblNFactura = new JLabel("N�mero de factura:");
    this.lblNFactura.setForeground(Color.WHITE);
    this.lblNFactura.setFont(new Font("Tahoma", 1, 13));
    this.lblNFactura.setBounds(237, 25, 135, 14);
    contentPane.add(this.lblNFactura);
    
    this.tfBuscarPorCedula = new JTextField();
    this.tfBuscarPorCedula.setToolTipText("Buscar factura por RUC / C.I.N�.");
    this.tfBuscarPorCedula.setFont(new Font("Tahoma", 1, 13));
    Validaciones validar = new Validaciones();
    validar.BloqueoAlfabetico(this.tfBuscarPorCedula);
    this.tfBuscarPorCedula.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (DevolucionFacContado.this.tfBuscarPorNFactura.getText().trim().length() == 0) {
          DevolucionFacContado.this.ConsultarTodasLasFacturasCabecera();
        }
        if (DevolucionFacContado.this.tfBuscarPorCedula.getText().trim().length() != 0)
        {
          DevolucionFacContado.this.tfBuscarPorNFactura.setText("");
          DevolucionFacContado.this.tfBuscarPorNFactura.setEnabled(false);
          DevolucionFacContado.this.tfBuscarPorNombre.setText("");
          DevolucionFacContado.this.tfBuscarPorNombre.setEnabled(false);
        }
        else
        {
          DevolucionFacContado.this.tfBuscarPorNombre.setEnabled(true);
          DevolucionFacContado.this.tfBuscarPorNFactura.setEnabled(true);
        }
        DevolucionFacContado.this.ConsultarPorCedula();
      }
    });
    this.tfBuscarPorCedula.setColumns(10);
    this.tfBuscarPorCedula.setBounds(579, 20, 109, 25);
    contentPane.add(this.tfBuscarPorCedula);
    
    this.lblCliente = new JLabel("Cliente:");
    this.lblCliente.setForeground(Color.WHITE);
    this.lblCliente.setFont(new Font("Tahoma", 1, 13));
    this.lblCliente.setBounds(520, 20, 82, 25);
    contentPane.add(this.lblCliente);
    
    this.tfBuscarPorNombre = new JTextField();
    this.tfBuscarPorNombre.setToolTipText("Buscar factura por nombre.");
    this.tfBuscarPorNombre.setFont(new Font("Tahoma", 1, 13));
    validar.BloqueoNumerico(this.tfBuscarPorNombre);
    this.tfBuscarPorNombre.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (DevolucionFacContado.this.tfBuscarPorNFactura.getText().trim().length() == 0) {
          DevolucionFacContado.this.ConsultarTodasLasFacturasCabecera();
        }
        if (DevolucionFacContado.this.tfBuscarPorNombre.getText().trim().length() != 0)
        {
          DevolucionFacContado.this.tfBuscarPorNFactura.setText("");
          DevolucionFacContado.this.tfBuscarPorNFactura.setEnabled(false);
          DevolucionFacContado.this.tfBuscarPorCedula.setText("");
          DevolucionFacContado.this.tfBuscarPorCedula.setEnabled(false);
        }
        else
        {
          DevolucionFacContado.this.tfBuscarPorCedula.setEnabled(true);
          DevolucionFacContado.this.tfBuscarPorNFactura.setEnabled(true);
        }
        DevolucionFacContado.this.ConsultarPorNombre();
      }
    });
    this.tfBuscarPorNombre.setColumns(10);
    this.tfBuscarPorNombre.setBounds(698, 20, 449, 25);
    contentPane.add(this.tfBuscarPorNombre);
    
    this.tfBuscarPorNFactura = new JTextField();
    this.tfBuscarPorNFactura.setToolTipText("Buscar factura por N�");
    this.tfBuscarPorNFactura.setFont(new Font("Tahoma", 1, 13));
    validar.BloqueoAlfabetico(this.tfBuscarPorNFactura);
    this.tfBuscarPorNFactura.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          if (DevolucionFacContado.this.tfBuscarPorNFactura.getText().trim().length() == 0) {
            DevolucionFacContado.this.ConsultarTodasLasFacturasCabecera();
          } else {
            DevolucionFacContado.this.ConsultarPorNFactura();
          }
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (DevolucionFacContado.this.tfBuscarPorNFactura.getText().trim().length() != 0)
        {
          DevolucionFacContado.this.tfBuscarPorCedula.setText("");
          DevolucionFacContado.this.tfBuscarPorCedula.setEnabled(false);
          DevolucionFacContado.this.tfBuscarPorNombre.setText("");
          DevolucionFacContado.this.tfBuscarPorNombre.setEnabled(false);
        }
        else
        {
          DevolucionFacContado.this.tfBuscarPorCedula.setEnabled(true);
          DevolucionFacContado.this.tfBuscarPorNombre.setEnabled(true);
        }
      }
    });
    this.tfBuscarPorNFactura.setColumns(10);
    this.tfBuscarPorNFactura.setBounds(382, 20, 119, 25);
    contentPane.add(this.tfBuscarPorNFactura);
    
    this.lblImg = new JLabel("");
    this.lblImg.setIcon(new ImageIcon(DevolucionFacContado.class.getResource("/Galeria/Devoluciones.png")));
    this.lblImg.setBounds(20, 64, 180, 212);
    contentPane.add(this.lblImg);
    
    btnActualizar = new JButton("Actualizando...");
    btnActualizar.setVisible(false);
    btnActualizar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        DevolucionFacContado.this.ActualizacionGral();
      }
    });
    btnActualizar.setMnemonic('P');
    btnActualizar.setBounds(693, 558, 40, 43);
    contentPane.add(btnActualizar);
    
    JTabbedPane tabbedPane = new JTabbedPane(1);
    tabbedPane.setBounds(10, 58, 194, 218);
    contentPane.add(tabbedPane);
    
    JTabbedPane tabbedPane_1 = new JTabbedPane(1);
    tabbedPane_1.setBounds(10, 11, 194, 37);
    contentPane.add(tabbedPane_1);
    
    JTabbedPane tabbedPane_2 = new JTabbedPane(1);
    tabbedPane_2.setBounds(210, 11, 953, 43);
    contentPane.add(tabbedPane_2);
    
    this.tfColor = new JTextField();
    this.tfColor.setVisible(false);
    this.tfColor.setBounds(743, 553, 65, 20);
    contentPane.add(this.tfColor);
    this.tfColor.setColumns(10);
    
    this.btnGuardar = new JButton("Guardar");
    this.btnGuardar.setVerticalAlignment(1);
    this.btnGuardar.setToolTipText("Guardar cambios del detalle y la cabecera de la factura.");
    this.btnGuardar.setEnabled(false);
    this.btnGuardar.setIcon(new ImageIcon(DevolucionFacContado.class.getResource("/Iconos/Guardar.png")));
    this.btnGuardar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        int codigoCabecera = Integer.parseInt(DevolucionFacContado.this.tfNFactura.getText());
        if (DevolucionFacContado.this.CalcularTotalDetalle().equals("0"))
        {
          ModeloFacturaContado f = new ModeloFacturaContado();
          f.setCodigoCabecera(codigoCabecera);
          f.setTotal(Integer.parseInt(DevolucionFacContado.this.CalcularTotalDetalle()));
          f.setEstado("ANULADO");
          DaoDevolucionesFacContado.ActualizarTotalFacturaCabecera(f);
          JOptionPane.showMessageDialog(null, "La factura fue anulada, ya no contiene detalles.", "Factura anulada", 2);
        }
        else
        {
          ModeloFacturaContado f = new ModeloFacturaContado();
          f.setCodigoCabecera(codigoCabecera);
          f.setTotal(Integer.parseInt(DevolucionFacContado.this.CalcularTotalDetalle()));
          f.setEstado("EMITIDO");
          DaoDevolucionesFacContado.ActualizarTotalFacturaCabecera(f);
        }
        DevolucionFacContado.this.btnGuardar.setEnabled(false);
        DevolucionFacContado.this.LimpiarDetalle();
        DevolucionFacContado.btnActualizar.doClick();
      }
    });
    this.btnGuardar.setBounds(537, 547, 143, 57);
    contentPane.add(this.btnGuardar);
    
    this.tfNFactura = new JTextField();
    this.tfNFactura.setVisible(false);
    this.tfNFactura.setEditable(false);
    this.tfNFactura.setBounds(743, 584, 86, 20);
    contentPane.add(this.tfNFactura);
    this.tfNFactura.setColumns(10);
    
    this.label_1 = new JLabel("");
    this.label_1.setIcon(new ImageIcon(DevolucionFacContado.class.getResource("/Galeria/DevolucionProd.png")));
    this.label_1.setBounds(20, 315, 166, 249);
    contentPane.add(this.label_1);
    
    this.tabbedPane_3 = new JTabbedPane(1);
    this.tabbedPane_3.setBounds(10, 283, 194, 321);
    contentPane.add(this.tabbedPane_3);
    ConsultarTodasLasFacturasCabecera();
  }
  
  public void ConsultarTodasLasFacturasCabecera()
  {
    listaFacturaCabecera = new ArrayList();
    listaFacturaCabecera = DaoDevolucionesFacContado.consultarFacturaCabecera();
    ActualizarTablaCabecera();
  }
  
  private void ConsultarPorNFactura()
  {
    listaFacturaCabecera = new ArrayList();
    listaFacturaCabecera = DaoDevolucionesFacContado.ConsultarPorNFactura(Integer.parseInt(this.tfBuscarPorNFactura.getText()));
    ActualizarTablaCabecera();
  }
  
  private void ConsultarPorCedula()
  {
    listaFacturaCabecera = new ArrayList();
    listaFacturaCabecera = DaoDevolucionesFacContado.ConsultarPorCedula(this.tfBuscarPorCedula.getText());
    ActualizarTablaCabecera();
  }
  
  private void ConsultarPorNombre()
  {
    listaFacturaCabecera = new ArrayList();
    listaFacturaCabecera = DaoDevolucionesFacContado.ConsultarPorNombre(this.tfBuscarPorNombre.getText());
    ActualizarTablaCabecera();
  }
  
  private static void ActualizarTablaCabecera()
  {
    while (modelocabecera.getRowCount() > 0) {
      modelocabecera.removeRow(0);
    }
    String[] campos = new String[7];
    for (int i = 0; i < listaFacturaCabecera.size(); i++)
    {
      modelocabecera.addRow(campos);
      ModeloFacturaContado factura = (ModeloFacturaContado)listaFacturaCabecera.get(i);
      
      modelocabecera.setValueAt(Integer.valueOf(factura.getCodigoCabecera()), i, 0);
      modelocabecera.setValueAt(factura.getCliente().getCedula(), i, 1);
      modelocabecera.setValueAt(factura.getCliente().getNombre(), i, 2);
      modelocabecera.setValueAt(factura.getFecha(), i, 3);
      modelocabecera.setValueAt(factura.getHora(), i, 4);
      modelocabecera.setValueAt(Double.valueOf(factura.getTotal()), i, 5);
      modelocabecera.setValueAt(factura.getEstado(), i, 6);
    }
  }
  
  public void ConsultarDetalle()
  {
    listaFacturaDetalle = new ArrayList();
    int seleccion = this.tablaCabecera.getSelectedRow();
    this.tfNFactura.setText(String.valueOf(this.tablaCabecera.getValueAt(seleccion, 0)));
    if (seleccion == -1)
    {
      JOptionPane.showMessageDialog(null, "Seleccione una factura para visualizar sus detalles.");
    }
    else
    {
      String valor = String.valueOf(modelocabecera.getValueAt(seleccion, 0).toString());
      String total = String.valueOf(modelocabecera.getValueAt(seleccion, 5).toString());
      this.tfTotal.setText(total);
      int codigo = Integer.parseInt(valor);
      listaFacturaDetalle = DaoFacturaContado.consultarDetalleDeLaFactura(codigo);
      ActualizarTablaDetalle();
    }
  }
  
  private static void ActualizarTablaDetalle()
  {
    while (modelodetalle.getRowCount() > 0) {
      modelodetalle.removeRow(0);
    }
    String[] campos = new String[6];
    for (int i = 0; i < listaFacturaDetalle.size(); i++)
    {
      modelodetalle.addRow(campos);
      ModeloFacturaContado cobranza = (ModeloFacturaContado)listaFacturaDetalle.get(i);
      
      modelodetalle.setValueAt(Integer.valueOf(cobranza.getCodigoDetalle()), i, 0);
      modelodetalle.setValueAt(Double.valueOf(cobranza.getCantidad()), i, 1);
      modelodetalle.setValueAt(cobranza.getProducto().getCodigo(), i, 2);
      modelodetalle.setValueAt(cobranza.getDescripcionProd(), i, 3);
      modelodetalle.setValueAt(Double.valueOf(cobranza.getPrecioUnitario()), i, 4);
      modelodetalle.setValueAt(Double.valueOf(cobranza.getSubtotal()), i, 5);
    }
  }
  
  public String CalcularTotalDetalle()
  {
    double sumatoria = 0.0D;
    int total = 0;
    int totalRow = this.tablaDetalle.getRowCount();
    totalRow--;
    for (int i = 0; i <= totalRow; i++)
    {
      sumatoria = Double.parseDouble(String.valueOf(this.tablaDetalle.getValueAt(i, 5)));
      total = (int)(total + sumatoria);
    }
    String totalObtenido = String.valueOf(total);
    
    this.tfTotal.setText(totalObtenido);
    
    return totalObtenido;
  }
  
  private void ActualizacionGral()
  {
    try
    {
      ConsultarTodasLasFacturasCabecera();
      
      int filaTotal = this.tablaDetalle.getRowCount();
      while (filaTotal >= 1)
      {
        modelodetalle.removeRow(0);
        this.btnDetalle.setEnabled(false);
        filaTotal--;
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  private void DevolverMercaderia()
  {
    int seleccion = this.tablaDetalle.getSelectedRow();
    String descripcion = String.valueOf(this.tablaDetalle.getValueAt(seleccion, 3).toString());
    int yes = JOptionPane.showConfirmDialog(this, "�Est� seguro que desea devolver " + descripcion + "?", "Aviso", 0);
    if (yes == 0)
    {
      EliminarDetalleYDevolverMercaderia();
      modelodetalle.removeRow(seleccion);
      CalcularTotalDetalle();
      this.btnGuardar.setEnabled(true);
    }
  }
  
  private void EliminarDetalleYDevolverMercaderia()
  {
    int seleccion = this.tablaDetalle.getSelectedRow();
    double stockactual = 0.0D;
    String codigoproducto = String.valueOf(this.tablaDetalle.getValueAt(seleccion, 2));
    ModeloProductos productos = DaoProductos.ConsutaParaModificar(String.valueOf(codigoproducto));
    double cantidadLLevada = Double.parseDouble(String.valueOf(this.tablaDetalle.getValueAt(seleccion, 1)));
    stockactual = productos.getStock();
    double updatestock = stockactual + cantidadLLevada;
    
    ModeloProductos p = new ModeloProductos();
    
    p.setCodigo(String.valueOf(codigoproducto));
    p.setStock(updatestock);
    
    DaoProductos.ActualizarStock(p);
    DaoFacturaContado.EliminarDetalles(Integer.parseInt(String.valueOf(this.tablaDetalle.getValueAt(seleccion, 0))));
    
    double cantvendidabd = 0.0D;
    double update = 0.0D;
    double subtotaladescontar = Double.parseDouble(String.valueOf(this.tablaDetalle.getValueAt(0, 5)));
    double subtotalact = 0.0D;
    double subtotalbd = 0.0D;
    double utlddact = 0.0D;
    double precioCosto = 0.0D;
    double precioVenta = 0.0D;
    int selecfac = this.tablaCabecera.getSelectedRow();
    String fechaCompra = String.valueOf(this.tablaCabecera.getValueAt(selecfac, 3));
    ModeloInformaciones i = DaoInformaciones.ConsultaForUpdate(codigoproducto, fechaCompra, "CONTADO");
    precioCosto = i.getProducto().getPrecioCosto();
    precioVenta = i.getPrecioUnitario();
    cantvendidabd = i.getCantVendida();
    subtotalbd = i.getSubtotal();
    update = cantvendidabd - cantidadLLevada;
    utlddact = (precioVenta - precioCosto) * update;
    subtotalact = subtotalbd - subtotaladescontar;
    if (update == 0.0D)
    {
      DaoInformaciones.EliminarInformacion(codigoproducto, fechaCompra, "CONTADO");
    }
    else
    {
      ModeloInformaciones info = new ModeloInformaciones();
      info.setCantVendida(update);
      info.setCodigoProducto(codigoproducto);
      info.setSubtotal(subtotalact);
      info.setUtilidad(utlddact);
      
      DaoInformaciones.ActualizarCantidadVendida(info, fechaCompra, "CONTADO");
    }
  }
  
  private void LimpiarDetalle()
  {
    int filaTotal = this.tablaDetalle.getRowCount();
    while (filaTotal >= 1)
    {
      modelodetalle.removeRow(0);
      filaTotal--;
    }
    this.tfTotal.setText("");
  }
  
  private String ConversionTotalCompra()
  {
    int num = Integer.parseInt(this.tfTotal.getText());
    ConversionEnLetras numero = new ConversionEnLetras(num);
    String res = numero.convertirLetras(num);
    
    String texto = res;
    return texto;
  }
  
  private void Salir(WindowEvent evt)
  {
    if (this.btnGuardar.isEnabled())
    {
      JOptionPane.showMessageDialog(null, "Se ha hecho cambios en el detalle de la factura,\nguarde cualquier cambio que haya hecho.", "Advertencia", 2);
      int res = JOptionPane.showConfirmDialog(null, "Nota: Si no guarda los cambios, el total de la \ncompra en la cabecera de la factura \npuede no coincidir con el total del detalle. \n�Est� seguro que desea salir sin guardar?", "Salir", 0);
      if (res == 0)
      {
        this.btnGuardar.requestFocus();
        dispose();
      }
    }
    else
    {
      dispose();
    }
    LimpiarDetalle();
  }
}
