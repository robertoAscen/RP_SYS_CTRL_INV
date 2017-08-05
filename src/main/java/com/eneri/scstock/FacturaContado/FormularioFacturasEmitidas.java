/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.FacturaContado;

import com.eneri.scstock.Apariencia.FondoFormularios;
import com.eneri.scstock.Herramientas.Validaciones;
import com.eneri.scstock.Modelos.ModeloFacturaContado;
import com.eneri.scstock.Modelos.ModeloInformaciones;
import com.eneri.scstock.Modelos.ModeloProductos;
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
public class FormularioFacturasEmitidas extends JDialog
{
     private final JPanel contentPanel = new JPanel();
  private JLabel lblTotal;
  public static List<ModeloFacturaContado> listaFacturaCabecera;
  public static List<ModeloFacturaContado> listaFacturaDetalle;
  
  public static void main(String[] args)
  {
    try
    {
      FormularioFacturasEmitidas dialog = new FormularioFacturasEmitidas();
      dialog.setLocationRelativeTo(null);
      dialog.setModal(true);
      dialog.setVisible(true);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public static DefaultTableModel modelocabecera = new DefaultTableModel(null, new String[] { "N� Fact.", "N�Ced/RUC", "Nombre y apellido del cliente", "Fecha de compra", "Hora", "Total", "IVA", "Estado" })
  {
    boolean[] columnEditables = new boolean[8];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  public static DefaultTableModel modelodetalle = new DefaultTableModel(null, new String[] { "N� Det.", "Cantidad", "C�d.", "Descripci�n", "P. Unit.", "Subtotal", "IVA" })
  {
    boolean[] columnEditables = new boolean[7];
    
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
  private JButton btnAnular;
  private JScrollPane scrollPaneCabecera;
  private JButton btnImprimirFactura;
  private JLabel lblNFactura;
  private JTextField tfBuscarPorCedula;
  private JLabel lblCliente;
  private JTextField tfBuscarPorNombre;
  private JTextField tfBuscarPorNFactura;
  private JLabel lblImg;
  public static JButton btnActualizar;
  private JTextField tfColor;
  private JTabbedPane tabbedPane_3;
  private JLabel label_1;
  private JTabbedPane tabbedPane;
  
  public FormularioFacturasEmitidas()
  {
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent evt)
      {
        FormularioFacturasEmitidas.this.Salir(evt);
      }
    });
    setResizable(false);
    setBounds(100, 100, 1189, 643);
    setTitle("Facturas emitidas");
    setDefaultCloseOperation(0);
    setIconImage(Toolkit.getDefaultToolkit().getImage(FormularioFacturasEmitidas.class.getResource("/Iconos/Facturas.png")));
    FondoFormularios contentPane = new FondoFormularios();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    this.btnAnular = new JButton("ANULAR");
    this.btnAnular.setEnabled(false);
    this.btnAnular.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FormularioFacturasEmitidas.this.AnularFactura();
        FormularioFacturasEmitidas.this.btnAnular.setEnabled(false);
        FormularioFacturasEmitidas.btnActualizar.doClick();
      }
    });
    this.btnAnular.setIcon(new ImageIcon(FormularioFacturasEmitidas.class.getResource("/Iconos/Anular.png")));
    this.btnAnular.setMnemonic('C');
    this.btnAnular.setBounds(365, 549, 153, 55);
    contentPane.add(this.btnAnular);
    
    this.btnImprimirFactura = new JButton("IMPRIMIR");
    this.btnImprimirFactura.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0) {}
    });
    this.btnImprimirFactura.setEnabled(false);
    this.btnImprimirFactura.setIcon(new ImageIcon(FormularioFacturasEmitidas.class.getResource("/Iconos/imprimirIcon.png")));
    
    this.btnImprimirFactura.setMnemonic('P');
    this.btnImprimirFactura.setBounds(528, 549, 141, 55);
    contentPane.add(this.btnImprimirFactura);
    
    JLabel label = new JLabel("");
    label.setIcon(new ImageIcon(FormularioFacturasEmitidas.class.getResource("/Iconos/dinero.png")));
    label.setFont(new Font("Tahoma", 1, 15));
    label.setBounds(1115, 549, 48, 49);
    contentPane.add(label);
    
    this.tfTotal = new JTextField();
    this.tfTotal.setBackground(Color.GRAY);
    this.tfTotal.setEditable(false);
    this.tfTotal.setBounds(887, 549, 225, 49);
    this.tfTotal.setFont(new Font("Microsoft Yi Baiti", 1, 45));
    contentPane.add(this.tfTotal);
    this.tfTotal.setColumns(10);
    
    this.lblTotal = new JLabel("TOTAL COMPRA:");
    this.lblTotal.setForeground(Color.WHITE);
    this.lblTotal.setFont(new Font("Tahoma", 1, 15));
    this.lblTotal.setBounds(754, 553, 143, 36);
    getContentPane().add(this.lblTotal);
    
    this.scrollPaneCabecera = new JScrollPane();
    this.scrollPaneCabecera.setBounds(210, 51, 963, 229);
    contentPane.add(this.scrollPaneCabecera);
    
    this.tablaCabecera = new JTable();
    this.tablaCabecera.setForeground(Color.WHITE);
    this.tablaCabecera.setBackground(new Color(51, 51, 51));
    this.tablaCabecera.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        FormularioFacturasEmitidas.this.LimpiarDetalle();
        FormularioFacturasEmitidas.this.btnDetalle.setEnabled(true);
        FormularioFacturasEmitidas.this.btnAnular.setEnabled(false);
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
    
    this.lblFactura = new JLabel("Facturas emitidas");
    this.lblFactura.setFont(new Font("Tahoma", 1, 20));
    this.lblFactura.setForeground(Color.BLACK);
    this.lblFactura.setBounds(17, 17, 187, 25);
    contentPane.add(this.lblFactura);
    
    this.scrollPaneDetalle = new JScrollPane();
    this.scrollPaneDetalle.setBounds(210, 283, 963, 259);
    contentPane.add(this.scrollPaneDetalle);
    
    this.tablaDetalle = new JTable();
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
    
    this.btnDetalle = new JButton("DETALLE");
    this.btnDetalle.setEnabled(false);
    this.btnDetalle.setMnemonic('D');
    this.btnDetalle.setToolTipText("Ver el detalle de la factura");
    this.btnDetalle.setIcon(new ImageIcon(FormularioFacturasEmitidas.class.getResource("/Iconos/icono-N�mina.png")));
    this.btnDetalle.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FormularioFacturasEmitidas.this.ConsultarDetalle();
        int seleccion = FormularioFacturasEmitidas.this.tablaCabecera.getSelectedRow();
        String estado = String.valueOf(FormularioFacturasEmitidas.this.tablaCabecera.getValueAt(seleccion, 6));
        if (estado.equals("ANULADO")) {
          FormularioFacturasEmitidas.this.btnAnular.setEnabled(false);
        } else {
          FormularioFacturasEmitidas.this.btnAnular.setEnabled(true);
        }
        FormularioFacturasEmitidas.this.btnDetalle.setEnabled(false);
      }
    });
    this.btnDetalle.setBounds(210, 549, 141, 55);
    contentPane.add(this.btnDetalle);
    
    this.lblNFactura = new JLabel("N�mero de factura:");
    this.lblNFactura.setFont(new Font("Tahoma", 1, 13));
    this.lblNFactura.setBounds(235, 20, 135, 14);
    contentPane.add(this.lblNFactura);
    
    this.tfBuscarPorCedula = new JTextField();
    this.tfBuscarPorCedula.setFont(new Font("Tahoma", 1, 13));
    Validaciones validar = new Validaciones();
    validar.BloqueoAlfabetico(this.tfBuscarPorCedula);
    this.tfBuscarPorCedula.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (FormularioFacturasEmitidas.this.tfBuscarPorNFactura.getText().trim().length() == 0) {
          FormularioFacturasEmitidas.this.ConsultarTodasLasFacturasCabecera();
        }
        if (FormularioFacturasEmitidas.this.tfBuscarPorCedula.getText().trim().length() != 0)
        {
          FormularioFacturasEmitidas.this.tfBuscarPorNFactura.setText("");
          FormularioFacturasEmitidas.this.tfBuscarPorNFactura.setEnabled(false);
          FormularioFacturasEmitidas.this.tfBuscarPorNombre.setText("");
          FormularioFacturasEmitidas.this.tfBuscarPorNombre.setEnabled(false);
        }
        else
        {
          FormularioFacturasEmitidas.this.tfBuscarPorNombre.setEnabled(true);
          FormularioFacturasEmitidas.this.tfBuscarPorNFactura.setEnabled(true);
        }
        FormularioFacturasEmitidas.this.ConsultarPorCedula();
      }
    });
    this.tfBuscarPorCedula.setColumns(10);
    this.tfBuscarPorCedula.setBounds(555, 17, 119, 25);
    contentPane.add(this.tfBuscarPorCedula);
    
    this.lblCliente = new JLabel("Cliente:");
    this.lblCliente.setFont(new Font("Tahoma", 1, 13));
    this.lblCliente.setBounds(494, 17, 74, 25);
    contentPane.add(this.lblCliente);
    
    this.tfBuscarPorNombre = new JTextField();
    this.tfBuscarPorNombre.setFont(new Font("Tahoma", 1, 13));
    validar.BloqueoNumerico(this.tfBuscarPorNombre);
    this.tfBuscarPorNombre.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (FormularioFacturasEmitidas.this.tfBuscarPorNFactura.getText().trim().length() == 0) {
          FormularioFacturasEmitidas.this.ConsultarTodasLasFacturasCabecera();
        }
        if (FormularioFacturasEmitidas.this.tfBuscarPorNombre.getText().trim().length() != 0)
        {
          FormularioFacturasEmitidas.this.tfBuscarPorNFactura.setText("");
          FormularioFacturasEmitidas.this.tfBuscarPorNFactura.setEnabled(false);
          FormularioFacturasEmitidas.this.tfBuscarPorCedula.setText("");
          FormularioFacturasEmitidas.this.tfBuscarPorCedula.setEnabled(false);
        }
        else
        {
          FormularioFacturasEmitidas.this.tfBuscarPorCedula.setEnabled(true);
          FormularioFacturasEmitidas.this.tfBuscarPorNFactura.setEnabled(true);
        }
        FormularioFacturasEmitidas.this.ConsultarPorNombre();
      }
    });
    this.tfBuscarPorNombre.setColumns(10);
    this.tfBuscarPorNombre.setBounds(684, 17, 458, 25);
    contentPane.add(this.tfBuscarPorNombre);
    
    this.tfBuscarPorNFactura = new JTextField();
    this.tfBuscarPorNFactura.setFont(new Font("Tahoma", 1, 13));
    validar.BloqueoAlfabetico(this.tfBuscarPorNFactura);
    this.tfBuscarPorNFactura.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          if (FormularioFacturasEmitidas.this.tfBuscarPorNFactura.getText().trim().length() == 0) {
            FormularioFacturasEmitidas.this.ConsultarTodasLasFacturasCabecera();
          } else {
            FormularioFacturasEmitidas.this.ConsultarPorNFactura();
          }
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (FormularioFacturasEmitidas.this.tfBuscarPorNFactura.getText().trim().length() != 0)
        {
          FormularioFacturasEmitidas.this.tfBuscarPorCedula.setText("");
          FormularioFacturasEmitidas.this.tfBuscarPorCedula.setEnabled(false);
          FormularioFacturasEmitidas.this.tfBuscarPorNombre.setText("");
          FormularioFacturasEmitidas.this.tfBuscarPorNombre.setEnabled(false);
        }
        else
        {
          FormularioFacturasEmitidas.this.tfBuscarPorCedula.setEnabled(true);
          FormularioFacturasEmitidas.this.tfBuscarPorNombre.setEnabled(true);
        }
      }
    });
    this.tfBuscarPorNFactura.setColumns(10);
    this.tfBuscarPorNFactura.setBounds(365, 17, 119, 25);
    contentPane.add(this.tfBuscarPorNFactura);
    
    this.lblImg = new JLabel("");
    this.lblImg.setIcon(new ImageIcon(FormularioFacturasEmitidas.class.getResource("/Galeria/Invoices.png")));
    this.lblImg.setBounds(13, 375, 180, 195);
    contentPane.add(this.lblImg);
    
    btnActualizar = new JButton("Actualizando...");
    btnActualizar.setVisible(false);
    btnActualizar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FormularioFacturasEmitidas.this.ActualizacionGral();
      }
    });
    btnActualizar.setMnemonic('P');
    btnActualizar.setBounds(730, 542, 95, 43);
    contentPane.add(btnActualizar);
    
    JTabbedPane tabbedPane_1 = new JTabbedPane(1);
    tabbedPane_1.setBounds(10, 11, 194, 37);
    contentPane.add(tabbedPane_1);
    
    JTabbedPane tabbedPane_2 = new JTabbedPane(1);
    tabbedPane_2.setBounds(210, 11, 963, 36);
    contentPane.add(tabbedPane_2);
    
    this.tfColor = new JTextField();
    this.tfColor.setVisible(false);
    this.tfColor.setBounds(679, 553, 65, 20);
    contentPane.add(this.tfColor);
    this.tfColor.setColumns(10);
    
    this.tabbedPane_3 = new JTabbedPane(1);
    this.tabbedPane_3.setBounds(10, 283, 194, 321);
    contentPane.add(this.tabbedPane_3);
    
    this.label_1 = new JLabel("");
    this.label_1.setIcon(new ImageIcon(FormularioFacturasEmitidas.class.getResource("/Galeria/FolderE.png")));
    this.label_1.setBounds(17, 58, 180, 216);
    contentPane.add(this.label_1);
    
    this.tabbedPane = new JTabbedPane(1);
    this.tabbedPane.setBounds(10, 51, 194, 229);
    contentPane.add(this.tabbedPane);
    ConsultarTodasLasFacturasCabecera();
    ColorBlancoFacturaLabels();
  }
  
  private void ColorBlancoFacturaLabels()
  {
    this.lblCliente.setForeground(Color.WHITE);
    this.lblNFactura.setForeground(Color.WHITE);
    this.lblTotal.setForeground(Color.WHITE);
    this.lblFactura.setForeground(Color.WHITE);
  }
  
  public void ConsultarTodasLasFacturasCabecera()
  {
    listaFacturaCabecera = new ArrayList();
    listaFacturaCabecera = DaoFacturaContado.consultarFacturaCabecera();
    ActualizarTablaCabecera();
  }
  
  private void ConsultarPorNFactura()
  {
    listaFacturaCabecera = new ArrayList();
    listaFacturaCabecera = DaoFacturaContado.ConsultarPorNFactura(Integer.parseInt(this.tfBuscarPorNFactura.getText()));
    ActualizarTablaCabecera();
  }
  
  private void ConsultarPorCedula()
  {
    listaFacturaCabecera = new ArrayList();
    listaFacturaCabecera = DaoFacturaContado.ConsultarPorCedula(this.tfBuscarPorCedula.getText());
    ActualizarTablaCabecera();
  }
  
  private void ConsultarPorNombre()
  {
    listaFacturaCabecera = new ArrayList();
    listaFacturaCabecera = DaoFacturaContado.ConsultarPorNombre(this.tfBuscarPorNombre.getText());
    ActualizarTablaCabecera();
  }
  
  private static void ActualizarTablaCabecera()
  {
    while (modelocabecera.getRowCount() > 0) {
      modelocabecera.removeRow(0);
    }
    String[] campos = new String[8];
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
      modelocabecera.setValueAt(Double.valueOf(factura.getTiva1()), i, 6);
      modelocabecera.setValueAt(Double.valueOf(factura.getTiva2()), i, 7);
      modelocabecera.setValueAt(factura.getEstado(), i, 8);
    }
  }
  
  public void ConsultarDetalle()
  {
    listaFacturaDetalle = new ArrayList();
    int seleccion = this.tablaCabecera.getSelectedRow();
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
    String[] campos = new String[7];
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
      modelodetalle.setValueAt(cobranza.getIva(), i, 6);
    }
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
  
  private void AnularFactura()
  {
    int seleccion = this.tablaCabecera.getSelectedRow();
    int yes = JOptionPane.showConfirmDialog(this, "�Est� seguro que desea anular esta factura?", "Aviso", 0);
    if (yes == 0)
    {
      int codigoCabecera = Integer.parseInt(String.valueOf(this.tablaCabecera.getValueAt(seleccion, 0)));
      ModeloFacturaContado f = new ModeloFacturaContado();
      f.setCodigoCabecera(codigoCabecera);
      f.setEstado("ANULADO");
      DaoFacturaContado.AnularFactura(f);
      EliminarDatosFacturaDetalleBD();
    }
  }
  
  private void EliminarDatosFacturaDetalleBD()
  {
    int filaTotal = this.tablaDetalle.getRowCount();
    while (filaTotal >= 1)
    {
      double stockactual = 0.0D;
      String codigoproducto = String.valueOf(this.tablaDetalle.getValueAt(0, 2));
      ModeloProductos productos = DaoProductos.ConsutaParaModificar(String.valueOf(codigoproducto));
      double cantidadLLevada = Double.parseDouble(String.valueOf(this.tablaDetalle.getValueAt(0, 1)));
      stockactual = productos.getStock();
      double updatestock = stockactual + cantidadLLevada;
      
      ModeloProductos p = new ModeloProductos();
      
      p.setCodigo(String.valueOf(codigoproducto));
      p.setStock(updatestock);
      
      DaoProductos.ActualizarStock(p);
      
      double cantvendidabd = 0.0D;
      double update = 0.0D;
      double subtotaladescontar = Double.parseDouble(String.valueOf(this.tablaDetalle.getValueAt(0, 5)));
      double subtotalact = 0.0D;
      double subtotalbd = 0.0D;
      int selecfac = this.tablaCabecera.getSelectedRow();
      String fechaCompra = String.valueOf(this.tablaCabecera.getValueAt(selecfac, 3));
      ModeloInformaciones i = DaoInformaciones.ConsultaForUpdate(codigoproducto, fechaCompra, "CONTADO");
      cantvendidabd = i.getCantVendida();
      subtotalbd = i.getSubtotal();
      update = cantvendidabd - cantidadLLevada;
      
      subtotalact = subtotalbd - subtotaladescontar;
      if (update == 0.0D)
      {
        DaoInformaciones.EliminarInformacion(codigoproducto, fechaCompra, "CONTADO");
      }
      else
      {
        ModeloInformaciones info = new ModeloInformaciones();
        info.setCodigoProducto(codigoproducto);
        info.setCantVendida(update);
        info.setCodigoProducto(codigoproducto);
        info.setSubtotal(subtotalact);
        DaoInformaciones.ActualizarCantidadVendida(info, fechaCompra, "CONTADO");
      }
      modelodetalle.removeRow(0);
      filaTotal--;
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
  
  private void Salir(WindowEvent evt)
  {
    dispose();
    LimpiarDetalle();
  }    
}
