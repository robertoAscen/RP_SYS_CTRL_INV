/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.FacturaContado;

//import br.com.adilson.util.PrinterMatrix;
import com.eneri.scstock.Apariencia.FondoTransacciones;
import com.eneri.scstock.Gestor.Gestionar;
import com.eneri.scstock.Herramientas.ConversionEnLetras;
import com.eneri.scstock.Herramientas.Validaciones;
import com.eneri.scstock.Modelos.ModeloFacturaContado;
import com.eneri.scstock.Modelos.ModeloInformaciones;
import com.eneri.scstock.Modelos.ModeloPresupuesto;
import com.eneri.scstock.Modelos.ModeloProductos;
import com.eneri.scstock.Modelos.ModeloVendedores;
import com.eneri.scstock.Objetos.DaoConfiguraciones;
import com.eneri.scstock.Objetos.DaoFacturaContado;
import com.eneri.scstock.Objetos.DaoInformaciones;
import com.eneri.scstock.Objetos.DaoPresupuesto;
import com.eneri.scstock.Objetos.DaoProductos;
import com.eneri.scstock.Objetos.DaoUsuarios;
import com.eneri.scstock.Objetos.DaoVendedores;
import com.eneri.scstock.Seguridad.CifrarPassword;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
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
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author RAscencio
 */
public class FacturaContado extends JDialog
{
    public static JTable tbFactura;
  public static JTable tbProductos;
  private JTextField tfPrecioVenta;
  private JLabel lblPrecioVenta;
  private JTextField tfPrecioMayorista;
  private JTextField tfPrecioCredito;
  private JTextField tfPrecioCosto;
  private JTextField tfCantPaquete;
  private JTextField tfStock;
  private JTextField tfBuscadorProducto;
  private JButton btnBuscarProducto;
  public static JTextField tfTotal;
  private JButton btnAgregar;
  private ModeloInformaciones info;
  private JButton btnFacturar;
  private JLabel lblFacturacion;
  private JTextField tfNFactura;
  public static JTextField tfCedulaRUC;
  public static JTextField tfNombreCliente;
  public static List<ModeloProductos> listaProductos;
  public static List<ModeloVendedores> listaVendedores;
  public static List<ModeloPresupuesto> listapresupuesto;
  public static List<ModeloFacturaContado> lista;
  public static CifrarPassword cifra = new CifrarPassword();
  private static double iva1 = DaoConfiguraciones.Iva1();
  private static double iva2 = DaoConfiguraciones.Iva2();
  private static double div1 = DaoConfiguraciones.Divisor1();
  private static double div2 = DaoConfiguraciones.Divisor2();
  private static String moneda = DaoConfiguraciones.Moneda();
  private static String simbolo = DaoConfiguraciones.Simbolo();
  private static String passwordAdmin = cifra.Desencriptar(DaoUsuarios.PassAdmin());
  private static String nombreEmpresa = DaoConfiguraciones.NombreEmpresa();
  private boolean descuento = false;
  public static DefaultTableModel modelofactura = new DefaultTableModel(null, new String[] { "Cantidad", "UM", "C�digo", 
    "Descripci�n del producto", "P. Unit.", "Subtotal", "IVA " + iva2 + "%", "IVA " + iva1 + "%", "IVA" })
  {
    boolean[] columnEditables = { true };
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  public static DefaultTableModel modeloproductos = new DefaultTableModel(null, new String[] { "C�digo", "Descripci�n del producto" })
  {
    boolean[] columnEditables = new boolean[2];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private JScrollPane scrollPaneFact;
  private JScrollPane scrollPaneProductos;
  public static JTextField tfTelefono;
  private JButton btnQuitar;
  private JLabel lblTotal;
  public static JTextArea tfDireccion;
  private JTextField tfUnidadMedida;
  private JCheckBox chckbxCodigo;
  private JCheckBox chckbxDescripcion;
  private JButton btnCancelarFactura;
  private JScrollPane scrollPane;
  private JButton btnInsertarCliente;
  private JTabbedPane tabbedPane;
  private JLabel lblNDeFactura;
  private JLabel lblRuccin;
  private JLabel lblNombre;
  private JLabel lblTelfono;
  private JLabel lblDireccin;
  private JLabel lblPrecioMayorista;
  private JLabel lblPrecioCredito;
  private JLabel lblStock;
  private JLabel lblCantPaquete;
  private JLabel lblPrecioDeCosto;
  private JTextField tfColor;
  private JTextField tfFactSinStock;
  private JLabel lblDatosDelCliente;
  public static JTextField tfCodCliente;
  private JLabel lblPrecio;
  private JComboBox comboBoxPrecio;
  private ModeloProductos producto;
  private JTextField tfDinero;
  private JTextField tfMontoACobrar;
  private JTextField tfCambio;
  private JLabel label_3;
  private JLabel lblCalcularCambio;
  private JComboBox comboBoxVendedor;
  private JLabel lblEstante;
  private JTextField tfEstante;
  private JLabel lblIva;
  private JTextField tfIva;
  private JLabel label_4;
  private JButton btnRecuperar;
  private JTextField tfIva2;
  private JTextField tfItem;
  private JTextField tfIva1;
  private JTextField tfIvaTotal;
  JTextArea parametroFactura;
  private JTextField tfDescPorc;
  private JLabel lblMontoDeDes;
  private JTextField tfDescMonto;
  public static JCheckBox chckbxDescuento;
  public static JTextField tfDesMax;
  public static double descMax = 0.0D;
  private JLabel lblMx;
  private JLabel lblTotalIva;
  private JLabel lblIva_1;
  private JTextField tfTotalVer;
  Gestionar gestion = new Gestionar();
  private JTextField tfDescuentooProducto;
  private JLabel label_5;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          FacturaContado dialog = new FacturaContado();
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
  
  public FacturaContado()
  {
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent evt)
      {
        FacturaContado.this.Salir(evt);
      }
    });
    setResizable(false);
    setDefaultCloseOperation(0);
    setIconImage(Toolkit.getDefaultToolkit().getImage(FacturaContado.class.getResource("/Iconos/Facturacion.png")));
    setTitle("Venta contado - Facturaci�n");
    setBounds(100, 100, 1295, 671);
    
    FondoTransacciones contentPane = new FondoTransacciones();
    contentPane.setForeground(Color.BLACK);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    this.label_5 = new JLabel("%");
    this.label_5.setForeground(Color.WHITE);
    this.label_5.setFont(new Font("Tahoma", 1, 11));
    this.label_5.setBounds(772, 491, 95, 20);
    contentPane.add(this.label_5);
    
    this.tfTotalVer = new JTextField();
    this.tfTotalVer.setToolTipText("Total a pagar");
    this.tfTotalVer.setText("");
    this.tfTotalVer.setFont(new Font("SansSerif", 1, 40));
    this.tfTotalVer.setEditable(false);
    this.tfTotalVer.setColumns(10);
    this.tfTotalVer.setBounds(955, 588, 324, 43);
    contentPane.add(this.tfTotalVer);
    
    this.tfItem = new JTextField();
    this.tfItem.setToolTipText("Cantidad de tipos de productos a facturar");
    this.tfItem.setEditable(false);
    this.tfItem.setFont(new Font("Arial Narrow", 0, 50));
    this.tfItem.setBounds(1206, 534, 73, 47);
    contentPane.add(this.tfItem);
    this.tfItem.setColumns(10);
    
    this.scrollPaneFact = new JScrollPane();
    this.scrollPaneFact.setCursor(Cursor.getPredefinedCursor(12));
    this.scrollPaneFact.setViewportBorder(new LineBorder(new Color(0, 255, 0), 2, true));
    this.scrollPaneFact.setBounds(468, 75, 811, 399);
    getContentPane().add(this.scrollPaneFact);
    
    tbFactura = new JTable();
    tbFactura.setCursor(Cursor.getPredefinedCursor(2));
    tbFactura.getTableHeader().setReorderingAllowed(false);
    tbFactura.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        int seleccionfila = FacturaContado.tbFactura.getSelectedRow();
        String cantidadEntrada = FacturaContado.tbFactura.getValueAt(seleccionfila, 1).toString();
        if (cantidadEntrada.trim().length() != 0) {
          FacturaContado.this.InsertarCantYCalSubTotal();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (FacturaContado.tbFactura.getRowCount() > 0)
        {
          int seleccionfila = FacturaContado.tbFactura.getSelectedRow();
          String cantidadEntrada = FacturaContado.tbFactura.getValueAt(seleccionfila, 1).toString();
          if (cantidadEntrada.trim().length() != 0) {
            FacturaContado.this.InsertarCantYCalSubTotal();
          }
        }
      }
    });
    tbFactura.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        FacturaContado.this.btnQuitar.setEnabled(true);
      }
    });
    this.scrollPaneFact.setViewportView(tbFactura);
    tbFactura.setModel(modelofactura);
    tbFactura.getColumnModel().getColumn(0).setPreferredWidth(39);
    tbFactura.getColumnModel().getColumn(1).setPreferredWidth(19);
    tbFactura.getColumnModel().getColumn(2).setPreferredWidth(73);
    tbFactura.getColumnModel().getColumn(3).setPreferredWidth(400);
    tbFactura.getColumnModel().getColumn(4).setPreferredWidth(59);
    tbFactura.getColumnModel().getColumn(5).setPreferredWidth(54);
    tbFactura.getColumnModel().getColumn(6).setPreferredWidth(47);
    tbFactura.getColumnModel().getColumn(7).setPreferredWidth(115);
    tbFactura.getColumnModel().getColumn(8).setPreferredWidth(20);
    
    setOcultarColumnasJTable(tbFactura, new int[] { 6, 7 });
    
    this.scrollPaneProductos = new JScrollPane();
    this.scrollPaneProductos.setViewportBorder(new BevelBorder(0, Color.CYAN, Color.CYAN, Color.CYAN, Color.CYAN));
    this.scrollPaneProductos.setVerticalScrollBarPolicy(22);
    this.scrollPaneProductos.setBounds(10, 242, 448, 224);
    getContentPane().add(this.scrollPaneProductos);
    
    tbProductos = new JTable();
    tbProductos.setCursor(Cursor.getPredefinedCursor(12));
    tbProductos.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        FacturaContado.this.btnAgregar.setEnabled(true);
        FacturaContado.this.CargarDatosDelProducto();
      }
    });
    tbProductos.getTableHeader().setReorderingAllowed(false);
    tbProductos.setForeground(Color.WHITE);
    tbProductos.setBackground(new Color(51, 51, 51));
    tbProductos.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        FacturaContado.this.btnAgregar.setEnabled(true);
        FacturaContado.this.CargarDatosDelProducto();
      }
    });
    this.scrollPaneProductos.setViewportView(tbProductos);
    tbProductos.setModel(modeloproductos);
    tbProductos.getColumnModel().getColumn(0).setPreferredWidth(15);
    tbProductos.getColumnModel().getColumn(0).setMinWidth(5);
    tbProductos.getColumnModel().getColumn(1).setPreferredWidth(200);
    this.tfPrecioVenta = new JTextField();
    this.tfPrecioVenta.setEditable(false);
    this.tfPrecioVenta.setBounds(158, 509, 86, 20);
    getContentPane().add(this.tfPrecioVenta);
    this.tfPrecioVenta.setColumns(10);
    
    this.lblPrecioVenta = new JLabel("Precio de venta:");
    this.lblPrecioVenta.setForeground(Color.WHITE);
    this.lblPrecioVenta.setFont(new Font("Tahoma", 1, 11));
    this.lblPrecioVenta.setBounds(63, 512, 95, 14);
    getContentPane().add(this.lblPrecioVenta);
    
    this.lblPrecioMayorista = new JLabel("   Precio para mayoristas:");
    this.lblPrecioMayorista.setForeground(Color.WHITE);
    this.lblPrecioMayorista.setFont(new Font("Tahoma", 1, 11));
    this.lblPrecioMayorista.setBounds(12, 543, 156, 14);
    getContentPane().add(this.lblPrecioMayorista);
    
    this.tfPrecioMayorista = new JTextField();
    this.tfPrecioMayorista.setEditable(false);
    this.tfPrecioMayorista.setColumns(10);
    this.tfPrecioMayorista.setBounds(158, 540, 86, 20);
    getContentPane().add(this.tfPrecioMayorista);
    
    this.lblPrecioCredito = new JLabel("Precio a cr�dito:");
    this.lblPrecioCredito.setForeground(Color.WHITE);
    this.lblPrecioCredito.setFont(new Font("Tahoma", 1, 11));
    this.lblPrecioCredito.setBounds(63, 571, 105, 14);
    getContentPane().add(this.lblPrecioCredito);
    
    this.tfPrecioCredito = new JTextField();
    this.tfPrecioCredito.setEditable(false);
    this.tfPrecioCredito.setColumns(10);
    this.tfPrecioCredito.setBounds(158, 568, 86, 20);
    getContentPane().add(this.tfPrecioCredito);
    
    this.lblPrecioDeCosto = new JLabel("   Precio de costo:");
    this.lblPrecioDeCosto.setForeground(Color.WHITE);
    this.lblPrecioDeCosto.setFont(new Font("Tahoma", 1, 11));
    this.lblPrecioDeCosto.setBounds(256, 512, 111, 14);
    getContentPane().add(this.lblPrecioDeCosto);
    
    this.tfPrecioCosto = new JTextField();
    this.tfPrecioCosto.setEditable(false);
    this.tfPrecioCosto.setColumns(10);
    this.tfPrecioCosto.setBounds(366, 509, 81, 20);
    getContentPane().add(this.tfPrecioCosto);
    
    this.lblCantPaquete = new JLabel("Cant. por paquete:");
    this.lblCantPaquete.setForeground(Color.WHITE);
    this.lblCantPaquete.setFont(new Font("Tahoma", 1, 11));
    this.lblCantPaquete.setBounds(253, 543, 114, 14);
    getContentPane().add(this.lblCantPaquete);
    
    this.tfCantPaquete = new JTextField();
    this.tfCantPaquete.setEditable(false);
    this.tfCantPaquete.setColumns(10);
    this.tfCantPaquete.setBounds(366, 540, 81, 20);
    getContentPane().add(this.tfCantPaquete);
    
    this.lblStock = new JLabel("Stock:");
    this.lblStock.setForeground(Color.WHITE);
    this.lblStock.setFont(new Font("Tahoma", 1, 11));
    this.lblStock.setBounds(319, 571, 48, 14);
    getContentPane().add(this.lblStock);
    
    this.tfStock = new JTextField();
    this.tfStock.setEditable(false);
    this.tfStock.setColumns(10);
    this.tfStock.setBounds(366, 568, 81, 20);
    getContentPane().add(this.tfStock);
    
    this.tfBuscadorProducto = new JTextField();
    this.tfBuscadorProducto.setFont(new Font("Tahoma", 1, 13));
    this.tfBuscadorProducto.setToolTipText("Buscar producto");
    this.tfBuscadorProducto.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          FacturaContado.this.btnBuscarProducto.doClick();
        }
      }
      
      public void keyTyped(KeyEvent evento)
      {
        char caracter = evento.getKeyChar();
        if (Character.isDigit(caracter))
        {
          getClass();
          FacturaContado.this.chckbxCodigo.setSelected(true);
          FacturaContado.this.chckbxDescripcion.setSelected(false);
        }
        if (!Character.isDigit(caracter))
        {
          getClass();
          FacturaContado.this.chckbxDescripcion.setSelected(true);
          FacturaContado.this.chckbxCodigo.setSelected(false);
        }
      }
    });
    this.tfBuscadorProducto.setColumns(10);
    this.tfBuscadorProducto.setBounds(10, 212, 206, 26);
    getContentPane().add(this.tfBuscadorProducto);
    
    this.btnBuscarProducto = new JButton("");
    this.btnBuscarProducto.setCursor(Cursor.getPredefinedCursor(12));
    this.btnBuscarProducto.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (FacturaContado.this.chckbxCodigo.isSelected())
        {
          FacturaContado.this.producto = DaoProductos.ConsutaParaModificar(FacturaContado.this.tfBuscadorProducto.getText());
          if (FacturaContado.this.producto != null)
          {
            FacturaContado.this.BuscarProductoCodigo();
            if (FacturaContado.tbProductos.getRowCount() == 1)
            {
              if (FacturaContado.this.LectorVerificarExistTabDet())
              {
                FacturaContado.this.AgregarProductoEnLaFacturaConLector();
                FacturaContado.this.ContarFilas();
              }
            }
            else {
              JOptionPane.showMessageDialog(null, "Elija el producto que desea agregar a la factura.");
            }
          }
          else
          {
            JOptionPane.showMessageDialog(null, "No existe ning�n producto registrado con este c�digo en la base de datos", "Error", 0);
            FacturaContado.this.tfBuscadorProducto.requestFocus();
            FacturaContado.this.tfBuscadorProducto.selectAll();
          }
        }
        if (FacturaContado.this.chckbxDescripcion.isSelected()) {
          FacturaContado.this.BuscarProductoDescripcion();
        }
      }
    });
    this.btnBuscarProducto.setIcon(new ImageIcon(FacturaContado.class.getResource("/Iconos/Lupa.png")));
    this.btnBuscarProducto.setBounds(226, 212, 48, 25);
    getContentPane().add(this.btnBuscarProducto);
    
    this.btnAgregar = new JButton("Agregar");
    this.btnAgregar.setCursor(Cursor.getPredefinedCursor(12));
    this.btnAgregar.setMnemonic('A');
    this.btnAgregar.setEnabled(false);
    this.btnAgregar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (FacturaContado.tbProductos.getSelectedRow() < 0)
        {
          JOptionPane.showMessageDialog(null, "Selecciona un producto para agregar en la lista", "Aviso", 2);
        }
        else if (FacturaContado.this.VerificarExistTabDet())
        {
          FacturaContado.this.AgregarProductoEnLaFacturaPorBTNAgregar();
          FacturaContado.this.ContarFilas();
        }
      }
    });
    this.btnAgregar.setToolTipText("Agregar producto a la factura");
    this.btnAgregar.setIcon(new ImageIcon(FacturaContado.class.getResource("/Iconos/agregar16.png")));
    this.btnAgregar.setBounds(468, 533, 135, 26);
    getContentPane().add(this.btnAgregar);
    
    this.btnFacturar = new JButton("FACTURAR");
    this.btnFacturar.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (FacturaContado.this.ValidarDatosDelCliente())
        {
          FacturaContado.this.FacturacionGeneral();
          FacturaContado.this.scrollPaneFact.setBounds(468, 68, 340, 434);
          FacturaContado.this.tfMontoACobrar.setText(FacturaContado.tfTotal.getText());
          FacturaContado.this.tfDinero.setText(FacturaContado.tfTotal.getText());
          FacturaContado.this.tfDinero.requestFocus();
          FacturaContado.this.tfDinero.selectAll();
          JOptionPane.showMessageDialog(null, "�Se ha realizado la transacci�n exitosamente!");
        }
      }
    });
    this.btnFacturar.setCursor(Cursor.getPredefinedCursor(12));
    this.btnFacturar.setMnemonic('F');
    this.btnFacturar.setEnabled(false);
    this.btnFacturar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (FacturaContado.this.ValidarDatosDelCliente()) {
          FacturaContado.this.FacturacionGeneral();
        }
      }
    });
    this.btnFacturar.setToolTipText("Guardar e imprimir la factura.");
    this.btnFacturar.setFont(new Font("Tahoma", 1, 15));
    this.btnFacturar.setIcon(new ImageIcon(FacturaContado.class.getResource("/Iconos/Facturacion.png")));
    this.btnFacturar.setBounds(613, 533, 225, 58);
    getContentPane().add(this.btnFacturar);
    
    this.lblFacturacion = new JLabel("Venta contado");
    this.lblFacturacion.setHorizontalAlignment(0);
    this.lblFacturacion.setHorizontalTextPosition(0);
    this.lblFacturacion.setBackground(Color.BLACK);
    this.lblFacturacion.setForeground(Color.BLACK);
    this.lblFacturacion.setFont(new Font("Book Antiqua", 1, 44));
    this.lblFacturacion.setBounds(811, 19, 468, 38);
    getContentPane().add(this.lblFacturacion);
    int codigo = DaoFacturaContado.IncrementarCodigoCabecera() + 1;
    this.tfNFactura = new JTextField();
    this.tfNFactura.setEnabled(false);
    this.tfNFactura.setColumns(10);
    this.tfNFactura.setBounds(111, 19, 141, 20);
    getContentPane().add(this.tfNFactura);
    this.tfNFactura.setText(String.valueOf(codigo));
    
    tfCedulaRUC = new JTextField();
    tfCedulaRUC.setEditable(false);
    tfCedulaRUC.setColumns(10);
    tfCedulaRUC.setBounds(111, 93, 95, 20);
    getContentPane().add(tfCedulaRUC);
    
    tfNombreCliente = new JTextField();
    tfNombreCliente.setEditable(false);
    tfNombreCliente.setColumns(10);
    tfNombreCliente.setBounds(185, 124, 261, 20);
    getContentPane().add(tfNombreCliente);
    
    this.scrollPane = new JScrollPane();
    this.scrollPane.setBounds(111, 155, 335, 38);
    getContentPane().add(this.scrollPane);
    
    tfDireccion = new JTextArea();
    this.scrollPane.setViewportView(tfDireccion);
    tfDireccion.setEditable(false);
    
    this.lblNDeFactura = new JLabel("N� de factura:");
    this.lblNDeFactura.setForeground(Color.WHITE);
    this.lblNDeFactura.setFont(new Font("Tahoma", 1, 11));
    this.lblNDeFactura.setBounds(22, 22, 86, 14);
    getContentPane().add(this.lblNDeFactura);
    
    this.lblRuccin = new JLabel("RUC/C.I.N�:");
    this.lblRuccin.setForeground(Color.WHITE);
    this.lblRuccin.setFont(new Font("Tahoma", 1, 11));
    this.lblRuccin.setBounds(38, 93, 76, 18);
    getContentPane().add(this.lblRuccin);
    
    this.lblNombre = new JLabel("   Cliente:");
    this.lblNombre.setForeground(Color.WHITE);
    this.lblNombre.setFont(new Font("Tahoma", 1, 11));
    this.lblNombre.setBounds(51, 124, 73, 20);
    getContentPane().add(this.lblNombre);
    
    this.lblDireccin = new JLabel("  Direcci�n:");
    this.lblDireccin.setForeground(Color.WHITE);
    this.lblDireccin.setFont(new Font("Tahoma", 1, 11));
    this.lblDireccin.setBounds(38, 155, 76, 14);
    getContentPane().add(this.lblDireccin);
    
    this.lblTelfono = new JLabel("Tel�fono:");
    this.lblTelfono.setForeground(Color.WHITE);
    this.lblTelfono.setFont(new Font("Tahoma", 1, 11));
    this.lblTelfono.setBounds(216, 93, 73, 20);
    getContentPane().add(this.lblTelfono);
    
    tfTelefono = new JTextField();
    tfTelefono.setEditable(false);
    tfTelefono.setColumns(10);
    tfTelefono.setBounds(283, 93, 95, 20);
    getContentPane().add(tfTelefono);
    
    this.btnInsertarCliente = new JButton("");
    this.btnInsertarCliente.setEnabled(false);
    this.btnInsertarCliente.setCursor(Cursor.getPredefinedCursor(12));
    this.btnInsertarCliente.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        ClientesDatosSetFac cliente = new ClientesDatosSetFac();
        
        cliente.setLocationRelativeTo(null);
        cliente.setModal(true);
        cliente.setVisible(true);
      }
    });
    this.btnInsertarCliente.setIcon(new ImageIcon(FacturaContado.class.getResource("/Iconos/agregar16.png")));
    this.btnInsertarCliente.setBounds(388, 93, 48, 20);
    getContentPane().add(this.btnInsertarCliente);
    
    this.btnQuitar = new JButton("Quitar");
    this.btnQuitar.setCursor(Cursor.getPredefinedCursor(12));
    this.btnQuitar.setMnemonic('Q');
    this.btnQuitar.setEnabled(false);
    this.btnQuitar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FacturaContado.this.QuitarProducto();
      }
    });
    this.btnQuitar.setToolTipText("Quitar producto de la factura");
    this.btnQuitar.setIcon(new ImageIcon(FacturaContado.class.getResource("/Iconos/del.png")));
    this.btnQuitar.setBounds(468, 567, 135, 26);
    getContentPane().add(this.btnQuitar);
    
    this.lblTotal = new JLabel("TOTAL:");
    this.lblTotal.setForeground(Color.WHITE);
    this.lblTotal.setFont(new Font("Tahoma", 1, 15));
    this.lblTotal.setBounds(885, 588, 73, 43);
    getContentPane().add(this.lblTotal);
    
    this.tfUnidadMedida = new JTextField();
    this.tfUnidadMedida.setVisible(false);
    this.tfUnidadMedida.setEditable(false);
    this.tfUnidadMedida.setBounds(1082, 587, 46, 20);
    getContentPane().add(this.tfUnidadMedida);
    this.tfUnidadMedida.setColumns(10);
    
    this.chckbxCodigo = new JCheckBox("C�digo");
    this.chckbxCodigo.setOpaque(false);
    this.chckbxCodigo.setForeground(Color.WHITE);
    this.chckbxCodigo.setFont(new Font("Tahoma", 1, 11));
    this.chckbxCodigo.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FacturaContado.this.chckbxDescripcion.setSelected(false);
      }
    });
    this.chckbxCodigo.setBounds(280, 212, 74, 26);
    getContentPane().add(this.chckbxCodigo);
    
    this.chckbxDescripcion = new JCheckBox("Descripci�n");
    this.chckbxDescripcion.setOpaque(false);
    this.chckbxDescripcion.setForeground(Color.WHITE);
    this.chckbxDescripcion.setFont(new Font("Tahoma", 1, 11));
    this.chckbxDescripcion.setSelected(true);
    this.chckbxDescripcion.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FacturaContado.this.chckbxCodigo.setSelected(false);
      }
    });
    this.chckbxDescripcion.setBounds(353, 212, 105, 26);
    getContentPane().add(this.chckbxDescripcion);
    
    this.btnCancelarFactura = new JButton("Limpiar lista");
    this.btnCancelarFactura.setCursor(Cursor.getPredefinedCursor(12));
    this.btnCancelarFactura.setMnemonic('E');
    this.btnCancelarFactura.setEnabled(false);
    this.btnCancelarFactura.setIcon(new ImageIcon(FacturaContado.class.getResource("/Iconos/Papelera1.png")));
    this.btnCancelarFactura.setToolTipText("Limpia la lista de la factura");
    this.btnCancelarFactura.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FacturaContado.this.LimpiarFactura();
        FacturaContado.this.LimpiarTextFieldsLuegoDeFacturar();
        FacturaContado.this.tfTotalVer.setText("");
        FacturaContado.tfTotal.setText("");
        FacturaContado.this.btnFacturar.setEnabled(false);
        FacturaContado.this.btnQuitar.setEnabled(false);
        FacturaContado.this.btnAgregar.setEnabled(false);
      }
    });
    this.btnCancelarFactura.setBounds(613, 601, 225, 31);
    getContentPane().add(this.btnCancelarFactura);
    
    this.tfColor = new JTextField();
    this.tfColor.setVisible(false);
    this.tfColor.setEditable(false);
    this.tfColor.setBounds(1127, 587, 86, 20);
    contentPane.add(this.tfColor);
    this.tfColor.setColumns(10);
    
    JTabbedPane tabbedPane_2 = new JTabbedPane(1);
    tabbedPane_2.setBounds(811, 12, 468, 58);
    contentPane.add(tabbedPane_2);
    
    this.tfFactSinStock = new JTextField();
    this.tfFactSinStock.setVisible(false);
    this.tfFactSinStock.setBounds(1043, 587, 41, 20);
    contentPane.add(this.tfFactSinStock);
    this.tfFactSinStock.setColumns(10);
    
    JTabbedPane tabbedPane_3 = new JTabbedPane(1);
    tabbedPane_3.setBounds(10, 12, 448, 31);
    contentPane.add(tabbedPane_3);
    
    this.lblDatosDelCliente = new JLabel("Datos del cliente");
    this.lblDatosDelCliente.setForeground(Color.WHITE);
    this.lblDatosDelCliente.setFont(new Font("Tahoma", 1, 11));
    this.lblDatosDelCliente.setBounds(172, 50, 129, 26);
    contentPane.add(this.lblDatosDelCliente);
    
    JTabbedPane tabbedPane_4 = new JTabbedPane(1);
    tabbedPane_4.setBounds(10, 50, 448, 26);
    contentPane.add(tabbedPane_4);
    
    tfCodCliente = new JTextField();
    tfCodCliente.setEditable(false);
    tfCodCliente.setBounds(111, 124, 64, 20);
    contentPane.add(tfCodCliente);
    tfCodCliente.setColumns(10);
    
    JTabbedPane tabbedPane_1 = new JTabbedPane(1);
    tabbedPane_1.setBounds(10, 74, 448, 127);
    contentPane.add(tabbedPane_1);
    
    this.comboBoxPrecio = new JComboBox();
    this.comboBoxPrecio.setCursor(Cursor.getPredefinedCursor(12));
    this.comboBoxPrecio.setModel(new DefaultComboBoxModel(new String[] { "Precio de venta", "Precio mayorista", "Precio a cr�dito" }));
    this.comboBoxPrecio.setBounds(556, 19, 231, 20);
    contentPane.add(this.comboBoxPrecio);
    
    this.lblPrecio = new JLabel("Precio:");
    this.lblPrecio.setForeground(Color.WHITE);
    this.lblPrecio.setFont(new Font("Tahoma", 1, 13));
    this.lblPrecio.setBounds(503, 19, 73, 20);
    contentPane.add(this.lblPrecio);
    
    JLabel label = new JLabel("Dinero:");
    label.setForeground(Color.WHITE);
    label.setFont(new Font("Tahoma", 1, 15));
    label.setBounds(878, 136, 76, 14);
    contentPane.add(label);
    Validaciones v = new Validaciones();
    
    JLabel label_1 = new JLabel("Monto a cobrar:");
    label_1.setForeground(Color.WHITE);
    label_1.setFont(new Font("Tahoma", 1, 15));
    label_1.setBounds(819, 196, 135, 29);
    contentPane.add(label_1);
    
    JLabel label_2 = new JLabel(" Cambio:");
    label_2.setForeground(Color.WHITE);
    label_2.setFont(new Font("Tahoma", 1, 15));
    label_2.setBounds(870, 256, 76, 47);
    contentPane.add(label_2);
    
    this.lblCalcularCambio = new JLabel("Calcular cambio");
    this.lblCalcularCambio.setForeground(Color.WHITE);
    this.lblCalcularCambio.setFont(new Font("Tahoma", 1, 15));
    this.lblCalcularCambio.setBounds(935, 94, 178, 14);
    contentPane.add(this.lblCalcularCambio);
    
    this.tfMontoACobrar = new JTextField();
    this.tfMontoACobrar.setForeground(new Color(218, 165, 32));
    this.tfMontoACobrar.setFont(new Font("Tahoma", 1, 15));
    this.tfMontoACobrar.setEditable(false);
    this.tfMontoACobrar.setColumns(10);
    this.tfMontoACobrar.setBounds(944, 196, 217, 29);
    contentPane.add(this.tfMontoACobrar);
    
    this.tfDinero = new JTextField();
    v.BloqueoAlfabetico(this.tfDinero);
    this.tfDinero.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          FacturaContado.this.CalcularCambio();
        }
      }
    });
    this.tfDinero.setForeground(new Color(255, 165, 0));
    this.tfDinero.setFont(new Font("Tahoma", 1, 15));
    this.tfDinero.setColumns(10);
    this.tfDinero.setBounds(944, 129, 217, 29);
    contentPane.add(this.tfDinero);
    
    this.tfCambio = new JTextField();
    this.tfCambio.setForeground(Color.BLACK);
    this.tfCambio.setFont(new Font("Tahoma", 1, 25));
    this.tfCambio.setEditable(false);
    this.tfCambio.setColumns(10);
    this.tfCambio.setBounds(944, 256, 217, 47);
    contentPane.add(this.tfCambio);
    
    this.label_3 = new JLabel("");
    this.label_3.setIcon(new ImageIcon(FacturaContado.class.getResource("/Galeria/cambio.png")));
    this.label_3.setFont(new Font("Tahoma", 1, 15));
    this.label_3.setBounds(818, 328, 112, 102);
    contentPane.add(this.label_3);
    
    JTabbedPane tabbedPane_6 = new JTabbedPane(1);
    tabbedPane_6.setBounds(819, 75, 363, 366);
    contentPane.add(tabbedPane_6);
    
    JLabel lblVendedor = new JLabel(" Vendedor:");
    lblVendedor.setForeground(Color.WHITE);
    lblVendedor.setFont(new Font("Tahoma", 1, 13));
    lblVendedor.setBounds(477, 43, 86, 20);
    contentPane.add(lblVendedor);
    
    this.comboBoxVendedor = new JComboBox();
    this.comboBoxVendedor.setBounds(556, 43, 231, 20);
    contentPane.add(this.comboBoxVendedor);
    
    JTabbedPane tabbedPane_5 = new JTabbedPane(1);
    tabbedPane_5.setBounds(468, 12, 333, 57);
    contentPane.add(tabbedPane_5);
    
    this.lblEstante = new JLabel("Estante:");
    this.lblEstante.setForeground(Color.WHITE);
    this.lblEstante.setFont(new Font("Tahoma", 1, 11));
    this.lblEstante.setBounds(106, 485, 86, 14);
    contentPane.add(this.lblEstante);
    
    this.tfEstante = new JTextField();
    this.tfEstante.setEditable(false);
    this.tfEstante.setColumns(10);
    this.tfEstante.setBounds(158, 482, 86, 20);
    contentPane.add(this.tfEstante);
    
    this.lblIva = new JLabel("I.V.A.:");
    this.lblIva.setForeground(Color.WHITE);
    this.lblIva.setFont(new Font("Tahoma", 1, 11));
    this.lblIva.setBounds(319, 485, 48, 14);
    contentPane.add(this.lblIva);
    
    this.tfIva = new JTextField();
    this.tfIva.setEditable(false);
    this.tfIva.setColumns(10);
    this.tfIva.setBounds(366, 482, 48, 20);
    contentPane.add(this.tfIva);
    
    this.label_4 = new JLabel("%");
    this.label_4.setForeground(Color.WHITE);
    this.label_4.setFont(new Font("Tahoma", 1, 11));
    this.label_4.setBounds(422, 485, 48, 14);
    contentPane.add(this.label_4);
    
    this.btnRecuperar = new JButton("Recuperar");
    this.btnRecuperar.setIcon(new ImageIcon(FacturaContado.class.getResource("/Iconos/icon_recover 18x18.png")));
    this.btnRecuperar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FacturaContado.this.RecuperarPresupuesto();
      }
    });
    this.btnRecuperar.setToolTipText("Recuperar lista de detalles por N� de presupuesto");
    this.btnRecuperar.setMnemonic('Q');
    this.btnRecuperar.setBounds(468, 601, 135, 31);
    contentPane.add(this.btnRecuperar);
    
    this.tfIva2 = new JTextField();
    this.tfIva2.setEditable(false);
    this.tfIva2.setColumns(10);
    this.tfIva2.setBounds(1110, 533, 92, 20);
    contentPane.add(this.tfIva2);
    
    this.lblTotalIva = new JLabel(iva1 + "%");
    this.lblTotalIva.setForeground(Color.WHITE);
    this.lblTotalIva.setFont(new Font("Tahoma", 1, 11));
    this.lblTotalIva.setBounds(909, 533, 49, 20);
    contentPane.add(this.lblTotalIva);
    
    this.lblIva_1 = new JLabel(iva2 + "%");
    this.lblIva_1.setForeground(Color.WHITE);
    this.lblIva_1.setFont(new Font("Tahoma", 1, 11));
    this.lblIva_1.setBounds(1069, 536, 55, 14);
    contentPane.add(this.lblIva_1);
    
    this.tfIva1 = new JTextField();
    this.tfIva1.setEditable(false);
    this.tfIva1.setColumns(10);
    this.tfIva1.setBounds(955, 533, 92, 20);
    contentPane.add(this.tfIva1);
    
    this.tfIvaTotal = new JTextField();
    this.tfIvaTotal.setEditable(false);
    this.tfIvaTotal.setColumns(10);
    this.tfIvaTotal.setBounds(955, 560, 247, 20);
    contentPane.add(this.tfIvaTotal);
    
    JLabel lblTotalIva_1 = new JLabel("Total I.V.A:");
    lblTotalIva_1.setForeground(Color.WHITE);
    lblTotalIva_1.setFont(new Font("Tahoma", 1, 11));
    lblTotalIva_1.setBounds(885, 560, 86, 20);
    contentPane.add(lblTotalIva_1);
    
    JLabel lblIva_2 = new JLabel("I.V.A:");
    lblIva_2.setForeground(Color.WHITE);
    lblIva_2.setFont(new Font("Tahoma", 1, 11));
    lblIva_2.setBounds(855, 533, 55, 20);
    contentPane.add(lblIva_2);
    
    JLabel lblDescuento = new JLabel("Descuento:");
    lblDescuento.setForeground(Color.WHITE);
    lblDescuento.setFont(new Font("Tahoma", 1, 11));
    lblDescuento.setBounds(607, 489, 104, 26);
    contentPane.add(lblDescuento);
    
    this.tfDescPorc = new JTextField();
    v.ValidarFormatoPrecio(this.tfDescPorc);
    this.tfDescPorc.setFont(new Font("Tahoma", 1, 14));
    this.tfDescPorc.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if ((e.getKeyCode() == 10) && 
          (FacturaContado.this.ComprobarAutorizacionAdmin()))
        {
          double totalFac = Double.parseDouble(FacturaContado.tfTotal.getText());
          FacturaContado.this.Descontar(FacturaContado.this.tfDescPorc.getText());
          FacturaContado.this.CalcularTotalGral();
          FacturaContado.this.CalcularTotalIvaC();
          FacturaContado.this.CalcularTotalIvaD();
          FacturaContado.this.CalcularTotalIvaGeneral();
          double totalActual = Double.parseDouble(FacturaContado.tfTotal.getText());
          totalFac -= totalActual;
          FacturaContado.this.tfDescMonto.setText(String.valueOf(totalFac));
        }
      }
      
      public void keyTyped(KeyEvent evento)
      {
        char caracter = evento.getKeyChar();
        if (!Character.isDigit(caracter))
        {
          getClass();
          evento.consume();
        }
      }
    });
    this.tfDescPorc.setText("");
    this.tfDescPorc.setEditable(false);
    this.tfDescPorc.setColumns(10);
    this.tfDescPorc.setBounds(709, 489, 55, 26);
    contentPane.add(this.tfDescPorc);
    
    chckbxDescuento = new JCheckBox("Descuento");
    chckbxDescuento.setForeground(Color.WHITE);
    chckbxDescuento.setOpaque(false);
    chckbxDescuento.setEnabled(false);
    chckbxDescuento.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (FacturaContado.chckbxDescuento.isSelected())
        {
          FacturaContado.this.tfDescPorc.setEditable(true);
          FacturaContado.this.tfDescPorc.requestFocus();
        }
        else
        {
          FacturaContado.this.tfDescPorc.setEditable(false);
        }
      }
    });
    chckbxDescuento.setBounds(483, 493, 114, 20);
    contentPane.add(chckbxDescuento);
    
    this.lblMontoDeDes = new JLabel("Monto de desc.:");
    this.lblMontoDeDes.setForeground(Color.WHITE);
    this.lblMontoDeDes.setFont(new Font("Tahoma", 1, 11));
    this.lblMontoDeDes.setBounds(979, 487, 105, 26);
    contentPane.add(this.lblMontoDeDes);
    
    this.tfDescMonto = new JTextField();
    this.tfDescMonto.setFont(new Font("Tahoma", 0, 14));
    this.tfDescMonto.setText("");
    this.tfDescMonto.setEditable(false);
    this.tfDescMonto.setColumns(10);
    this.tfDescMonto.setBounds(1080, 488, 179, 26);
    contentPane.add(this.tfDescMonto);
    
    JTabbedPane tabbedPane_7 = new JTabbedPane(1);
    tabbedPane_7.setBounds(468, 481, 811, 42);
    contentPane.add(tabbedPane_7);
    
    tfDesMax = new JTextField();
    tfDesMax.setText("");
    tfDesMax.setFont(new Font("Tahoma", 1, 14));
    tfDesMax.setEditable(false);
    tfDesMax.setColumns(10);
    tfDesMax.setBounds(890, 488, 48, 26);
    contentPane.add(tfDesMax);
    
    this.lblMx = new JLabel("M�x.:");
    this.lblMx.setForeground(Color.WHITE);
    this.lblMx.setFont(new Font("Tahoma", 1, 11));
    this.lblMx.setBounds(846, 489, 55, 26);
    contentPane.add(this.lblMx);
    
    tfTotal = new JTextField();
    tfTotal.setText("");
    tfTotal.setVisible(false);
    tfTotal.setToolTipText("Total a pagar");
    tfTotal.setEditable(false);
    tfTotal.setFont(new Font("SansSerif", 1, 40));
    tfTotal.setColumns(10);
    tfTotal.setBounds(955, 588, 324, 43);
    getContentPane().add(tfTotal);
    
    JLabel lblDescuento_1 = new JLabel("Descuento:");
    lblDescuento_1.setForeground(Color.WHITE);
    lblDescuento_1.setFont(new Font("Tahoma", 1, 11));
    lblDescuento_1.setBounds(82, 599, 86, 14);
    contentPane.add(lblDescuento_1);
    
    this.tfDescuentooProducto = new JTextField();
    this.tfDescuentooProducto.setEditable(false);
    this.tfDescuentooProducto.setColumns(10);
    this.tfDescuentooProducto.setBounds(158, 596, 86, 20);
    contentPane.add(this.tfDescuentooProducto);
    
    this.tabbedPane = new JTabbedPane(1);
    this.tabbedPane.setBounds(10, 469, 448, 162);
    contentPane.add(this.tabbedPane);
    
    ColorBlancoFacturaLabels();
    LimpiarDetalleAlSalir();
    cargarComboBoxVendedores();
  }
  
  private boolean ComprobarAutorizacionAdmin()
  {
    String resp = JOptionPane.showInputDialog(null, "Ingrese contrase�a del administrador", "Descuento", -1);
    if (passwordAdmin == resp)
    {
      JOptionPane.showMessageDialog(null, "Contrase�a incorrecta, descuento no autorizado", "Error", 0);
      return false;
    }
    return true;
  }
  
  private void setOcultarColumnasJTable(JTable tbl, int[] columna)
  {
    for (int i = 0; i < columna.length; i++)
    {
      tbl.getColumnModel().getColumn(columna[i]).setMaxWidth(0);
      tbl.getColumnModel().getColumn(columna[i]).setMinWidth(0);
      tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMaxWidth(0);
      tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMinWidth(0);
    }
  }
  
  private void RecuperarPresupuesto()
  {
    String numero = JOptionPane.showInputDialog(null, "Ingrese el n�mero de presupuesto", "");
    if ((numero == null) || (numero.equals("")))
    {
      JOptionPane.showMessageDialog(null, "No podr� recuperar la lista sin el n�mero de presupuesto");
    }
    else
    {
      listapresupuesto = new ArrayList();
      listapresupuesto = DaoPresupuesto.consultarDetallePresupuesto(Integer.parseInt(numero));
      ActualizarTablaDetallePresupuesto();
      CalcularTotalGral();
      this.btnFacturar.setEnabled(true);
      this.btnCancelarFactura.setEnabled(true);
    }
  }
  
  private void cargarComboBoxVendedores()
  {
    listaVendedores = new ArrayList();
    listaVendedores = DaoVendedores.consultarTodoElRegistroForComboBox();
    for (int i = 0; i < listaVendedores.size(); i++)
    {
      ModeloVendedores v = (ModeloVendedores)listaVendedores.get(i);
      this.comboBoxVendedor.addItem(v.getCodigo() + "-" + v.getNombre());
    }
  }
  
  private void ColorBlancoFacturaLabels()
  {
    this.lblNDeFactura.setForeground(Color.WHITE);
    this.lblRuccin.setForeground(Color.WHITE);
    this.lblNombre.setForeground(Color.WHITE);
    this.lblTelfono.setForeground(Color.WHITE);
    this.lblDireccin.setForeground(Color.WHITE);
    this.lblPrecioMayorista.setForeground(Color.WHITE);
    this.lblPrecioCredito.setForeground(Color.WHITE);
    this.lblStock.setForeground(Color.WHITE);
    this.lblCantPaquete.setForeground(Color.WHITE);
    this.lblPrecioDeCosto.setForeground(Color.WHITE);
    this.lblPrecioVenta.setForeground(Color.WHITE);
    this.lblFacturacion.setForeground(Color.WHITE);
    this.lblTotal.setForeground(Color.WHITE);
    this.lblDatosDelCliente.setForeground(Color.WHITE);
  }
  
  public void cargarTodosLosRegistros()
  {
    listaProductos = new ArrayList();
    listaProductos = DaoProductos.consultarTodosLosProductos();
    ActualizarTablaPrincipal();
  }
  
  private static void ActualizarTablaPrincipal()
  {
    while (modeloproductos.getRowCount() > 0) {
      modeloproductos.removeRow(0);
    }
    String[] campos = new String[2];
    for (int i = 0; i < listaProductos.size(); i++)
    {
      modeloproductos.addRow(campos);
      ModeloProductos producto = (ModeloProductos)listaProductos.get(i);
      
      modeloproductos.setValueAt(producto.getCodigo(), i, 0);
      modeloproductos.setValueAt(producto.getDescripcion(), i, 1);
    }
  }
  
  private void CargarDatosDelProducto()
  {
    int seleccion = tbProductos.getSelectedRow();
    String codigo = tbProductos.getValueAt(seleccion, 0).toString();
    ModeloProductos productos = DaoProductos.ConsutaParaModificar(codigo);
    
    this.tfPrecioCosto.setText(String.valueOf(productos.getPrecioCosto()));
    this.tfPrecioMayorista.setText(String.valueOf(productos.getPrecioMayorista()));
    this.tfPrecioVenta.setText(String.valueOf(productos.getPrecioVenta()));
    this.tfPrecioCredito.setText(String.valueOf(productos.getPrecioCredito()));
    this.tfStock.setText(String.valueOf(productos.getStock()));
    this.tfCantPaquete.setText(String.valueOf(productos.getCantPaquete()));
    this.tfUnidadMedida.setText(String.valueOf(productos.getUnidadDeMedida()));
    this.tfFactSinStock.setText(String.valueOf(productos.getFacturar()));
    this.tfEstante.setText(String.valueOf(productos.getEstante()));
    this.tfIva.setText(String.valueOf(productos.getIva()));
    this.tfDescuentooProducto.setText(String.valueOf(productos.getDescuento()));
  }
  
  private void CargarDatosDelProductoConLector()
  {
    String codigo = this.tfBuscadorProducto.getText();
    ModeloProductos productos = DaoProductos.ConsutaParaModificar(codigo);
    this.tfPrecioCosto.setText(String.valueOf(productos.getPrecioCosto()));
    this.tfPrecioMayorista.setText(String.valueOf(productos.getPrecioMayorista()));
    this.tfPrecioVenta.setText(String.valueOf(productos.getPrecioVenta()));
    this.tfPrecioCredito.setText(String.valueOf(productos.getPrecioCredito()));
    this.tfStock.setText(String.valueOf(productos.getStock()));
    this.tfCantPaquete.setText(String.valueOf(productos.getCantPaquete()));
    this.tfUnidadMedida.setText(String.valueOf(productos.getUnidadDeMedida()));
    this.tfFactSinStock.setText(String.valueOf(productos.getFacturar()));
    this.tfEstante.setText(String.valueOf(productos.getEstante()));
    this.tfIva.setText(String.valueOf(productos.getIva()));
  }
  
  private void InsertarCantYCalSubTotal()
  {
    try
    {
      int seleccionfila = tbFactura.getSelectedRow();
      String cantidadEntrada = tbFactura.getValueAt(seleccionfila, 0).toString();
      String costoProducto = tbFactura.getValueAt(seleccionfila, 4).toString();
      String unidadMedida = tbFactura.getValueAt(seleccionfila, 1).toString();
      
      double subtotal = 0.0D;
      double cantidad = Double.parseDouble(cantidadEntrada);
      double costo = 0.0D;
      
      costo = Double.parseDouble(costoProducto);
      
      subtotal = cantidad * costo;
      int seleccion = tbFactura.getSelectedRow();
      if (ValidarFacturarSinStock())
      {
        if ((subtotal < costo) && (unidadMedida.equals("UNI")))
        {
          JOptionPane.showMessageDialog(null, "�ste producto se vende por unidad, verifique la cantidad ingresada.");
          this.btnFacturar.setEnabled(false);
          modelofactura.setValueAt("0", seleccion, 5);
          CalcularTotalGral();
        }
        if ((subtotal < costo) && (unidadMedida.equals("CAJ")))
        {
          JOptionPane.showMessageDialog(null, "�ste producto se vende por caja, verifique la cantidad ingresada.");
          modelofactura.setValueAt("0", seleccion, 5);
          CalcularTotalGral();
        }
        if (subtotal >= costo)
        {
          if (subtotal > 9999999.0D)
          {
            int subtotalInt = (int)subtotal;
            modelofactura.setValueAt(Integer.valueOf(subtotalInt), seleccionfila, 5);
          }
          else
          {
            modelofactura.setValueAt(Double.valueOf(subtotal), seleccionfila, 5);
          }
          CalcularTotalGral();
          this.btnFacturar.setEnabled(true);
        }
        if (tbFactura.getValueAt(seleccionfila, 8).equals(iva2 + "%"))
        {
          double iva = div2;
          double totalIva = 0.0D;
          double subTotal = Double.parseDouble(String.valueOf(tbFactura.getValueAt(seleccionfila, 5)));
          
          totalIva = subTotal / iva;
          totalIva = NumeroFormato(totalIva);
          tbFactura.setValueAt(Double.valueOf(totalIva), seleccionfila, 6);
        }
        if (tbFactura.getValueAt(seleccionfila, 8).equals(iva1 + "%"))
        {
          double iva = div1;
          double totalIva = 0.0D;
          double subTotal = Double.parseDouble(String.valueOf(tbFactura.getValueAt(seleccionfila, 5)));
          
          totalIva = subTotal / iva;
          totalIva = NumeroFormato(totalIva);
          tbFactura.setValueAt(Double.valueOf(totalIva), seleccionfila, 7);
        }
        CalcularTotalIvaC();
        CalcularTotalIvaD();
        CalcularTotalIvaGeneral();
      }
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(null, e.getMessage());
    }
  }
  
  private boolean ValidarFacturarSinStock()
  {
    int seleccion = tbFactura.getSelectedRow();
    String codigoproducto = tbFactura.getValueAt(0, 2).toString();
    String cantidad = tbFactura.getValueAt(0, 0).toString();
    ModeloProductos productos = DaoProductos.ConsutaParaModificar(String.valueOf(codigoproducto));
    double stockactual = productos.getStock();
    double cant = Double.parseDouble(cantidad);
    String descripcion = productos.getDescripcion();
    String um = productos.getUnidadDeMedida();
    if ((productos.getFacturar().equals("No")) && (cant > stockactual))
    {
      JOptionPane.showMessageDialog(null, "El producto " + descripcion + " solo queda " + stockactual + " " + um + " en stock.", "Stock insuficiente", 2);
      modelofactura.removeRow(seleccion);
      CalcularTotalGral();
      return false;
    }
    return true;
  }
  
  private void AgregarProductoEnLaFacturaConLector()
  {
    CargarDatosDelProductoConLector();
    double stock = 0.0D;
    String facss = "";
    stock = Double.parseDouble(this.tfStock.getText());
    facss = this.tfFactSinStock.getText();
    if (stock > 0.0D) {
      try
      {
        if (this.comboBoxPrecio.getSelectedIndex() == 0)
        {
          String precio = this.tfPrecioVenta.getText();
          String codigo = tbProductos.getValueAt(0, 0).toString();
          String descripcion = tbProductos.getValueAt(0, 1).toString();
          String unidadMedida = this.tfUnidadMedida.getText();
          if (this.tfIva.getText().equals(iva1))
          {
            double iva = div1;
            double totalIva = 0.0D;
            double precioInt = Double.parseDouble(precio);
            
            totalIva = precioInt / iva;
            totalIva = NumeroFormato(totalIva);
            String IvaTotal = String.valueOf(totalIva);
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, "0", IvaTotal };
            modelofactura.addRow(filaseleccionada);
          }
          if (this.tfIva.getText().equals(iva2))
          {
            double iva = div2;
            double totalIva = 0.0D;
            double precioInt = Double.parseDouble(precio);
            
            totalIva = precioInt / iva;
            totalIva = NumeroFormato(totalIva);
            String IvaTotal = String.valueOf(totalIva);
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, IvaTotal, "0" };
            modelofactura.addRow(filaseleccionada);
          }
          if (this.tfIva.getText().equals("0.0"))
          {
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, "0", "0" };
            modelofactura.addRow(filaseleccionada);
          }
          this.btnFacturar.setEnabled(true);
        }
        if (this.comboBoxPrecio.getSelectedIndex() == 1)
        {
          String precio = this.tfPrecioMayorista.getText();
          String codigo = tbProductos.getValueAt(0, 0).toString();
          String descripcion = tbProductos.getValueAt(0, 1).toString();
          String unidadMedida = this.tfUnidadMedida.getText();
          if (this.tfIva.getText().equals(iva1))
          {
            double iva = div1;
            double totalIva = 0.0D;
            double precioInt = Double.parseDouble(precio);
            
            totalIva = precioInt / iva;
            totalIva = NumeroFormato(totalIva);
            String IvaTotal = String.valueOf(totalIva);
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, "0", IvaTotal };
            modelofactura.addRow(filaseleccionada);
          }
          if (this.tfIva.getText().equals(iva2))
          {
            double iva = div2;
            double totalIva = 0.0D;
            double precioInt = Double.parseDouble(precio);
            
            totalIva = precioInt / iva;
            totalIva = NumeroFormato(totalIva);
            String IvaTotal = String.valueOf(totalIva);
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, IvaTotal, "0" };
            modelofactura.addRow(filaseleccionada);
          }
          if (this.tfIva.getText().equals("0.0"))
          {
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, "0", "0" };
            modelofactura.addRow(filaseleccionada);
          }
          this.btnFacturar.setEnabled(true);
        }
        if (this.comboBoxPrecio.getSelectedIndex() == 2)
        {
          String precio = this.tfPrecioCredito.getText();
          
          String codigo = tbProductos.getValueAt(0, 0).toString();
          String descripcion = tbProductos.getValueAt(0, 1).toString();
          String unidadMedida = this.tfUnidadMedida.getText();
          if (this.tfIva.getText().equals(iva1))
          {
            double iva = div1;
            double totalIva = 0.0D;
            double precioInt = Double.parseDouble(precio);
            
            totalIva = precioInt / iva;
            totalIva = NumeroFormato(totalIva);
            String IvaTotal = String.valueOf(totalIva);
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, "0", IvaTotal };
            modelofactura.addRow(filaseleccionada);
          }
          if (this.tfIva.getText().equals(iva2))
          {
            double iva = div2;
            double totalIva = 0.0D;
            double precioInt = Double.parseDouble(precio);
            
            totalIva = precioInt / iva;
            totalIva = NumeroFormato(totalIva);
            String IvaTotal = String.valueOf(totalIva);
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, IvaTotal, "0" };
            modelofactura.addRow(filaseleccionada);
          }
          if (this.tfIva.getText().equals("0.0"))
          {
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, "0", "0" };
            modelofactura.addRow(filaseleccionada);
          }
          this.btnFacturar.setEnabled(true);
        }
        this.btnCancelarFactura.setEnabled(true);
        this.btnCancelarFactura.setIcon(new ImageIcon(FacturaContado.class.getResource("/Iconos/Papelera1.png")));
        CalcularTotalGral();
        this.tfBuscadorProducto.setText("");
        this.tfBuscadorProducto.requestFocus();
      }
      catch (Exception e)
      {
        JOptionPane.showMessageDialog(null, "Try catch exeption: " + e.getMessage());
      }
    }
    if ((stock < 1.0D) && (facss.equals("Si"))) {
      try
      {
        if (this.comboBoxPrecio.getSelectedIndex() == 0)
        {
          String precio = this.tfPrecioVenta.getText();
          String codigo = tbProductos.getValueAt(0, 0).toString();
          String descripcion = tbProductos.getValueAt(0, 1).toString();
          String unidadMedida = this.tfUnidadMedida.getText();
          if (this.tfIva.getText().equals(iva1))
          {
            double iva = div1;
            double totalIva = 0.0D;
            double precioInt = Double.parseDouble(precio);
            
            totalIva = precioInt / iva;
            totalIva = NumeroFormato(totalIva);
            String IvaTotal = String.valueOf(totalIva);
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, "0", IvaTotal };
            modelofactura.addRow(filaseleccionada);
          }
          if (this.tfIva.getText().equals(iva2))
          {
            double iva = div2;
            double totalIva = 0.0D;
            double precioInt = Double.parseDouble(precio);
            
            totalIva = precioInt / iva;
            totalIva = NumeroFormato(totalIva);
            String IvaTotal = String.valueOf(totalIva);
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, IvaTotal, "0" };
            modelofactura.addRow(filaseleccionada);
          }
          if (this.tfIva.getText().equals("0.0"))
          {
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, "0", "0" };
            modelofactura.addRow(filaseleccionada);
          }
          this.btnFacturar.setEnabled(true);
        }
        if (this.comboBoxPrecio.getSelectedIndex() == 1)
        {
          String precio = this.tfPrecioMayorista.getText();
          String codigo = tbProductos.getValueAt(0, 0).toString();
          String descripcion = tbProductos.getValueAt(0, 1).toString();
          String unidadMedida = this.tfUnidadMedida.getText();
          if (this.tfIva.getText().equals(iva1))
          {
            double iva = div1;
            double totalIva = 0.0D;
            double precioInt = Double.parseDouble(precio);
            
            totalIva = precioInt / iva;
            totalIva = NumeroFormato(totalIva);
            String IvaTotal = String.valueOf(totalIva);
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, "0", IvaTotal };
            modelofactura.addRow(filaseleccionada);
          }
          if (this.tfIva.getText().equals(iva2))
          {
            double iva = div2;
            double totalIva = 0.0D;
            double precioInt = Double.parseDouble(precio);
            
            totalIva = precioInt / iva;
            totalIva = NumeroFormato(totalIva);
            String IvaTotal = String.valueOf(totalIva);
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, IvaTotal, "0" };
            modelofactura.addRow(filaseleccionada);
          }
          if (this.tfIva.getText().equals("0.0"))
          {
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, "0", "0" };
            modelofactura.addRow(filaseleccionada);
          }
          this.btnFacturar.setEnabled(true);
        }
        if (this.comboBoxPrecio.getSelectedIndex() == 2)
        {
          String precio = this.tfPrecioCredito.getText();
          String codigo = tbProductos.getValueAt(0, 0).toString();
          String descripcion = tbProductos.getValueAt(0, 1).toString();
          String unidadMedida = this.tfUnidadMedida.getText();
          if (this.tfIva.getText().equals(iva1))
          {
            double iva = div1;
            double totalIva = 0.0D;
            double precioInt = Double.parseDouble(precio);
            
            totalIva = precioInt / iva;
            totalIva = NumeroFormato(totalIva);
            String IvaTotal = String.valueOf(totalIva);
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, "0", IvaTotal };
            modelofactura.addRow(filaseleccionada);
          }
          if (this.tfIva.getText().equals(iva2))
          {
            double iva = div2;
            double totalIva = 0.0D;
            double precioInt = Double.parseDouble(precio);
            
            totalIva = precioInt / iva;
            totalIva = NumeroFormato(totalIva);
            String IvaTotal = String.valueOf(totalIva);
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, IvaTotal, "0" };
            modelofactura.addRow(filaseleccionada);
          }
          if (this.tfIva.getText().equals("0.0"))
          {
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, "0", "0" };
            modelofactura.addRow(filaseleccionada);
          }
          this.btnFacturar.setEnabled(true);
        }
        this.btnCancelarFactura.setEnabled(true);
        this.btnCancelarFactura.setIcon(new ImageIcon(FacturaContado.class.getResource("/Iconos/Papelera1.png")));
        CalcularTotalGral();
        this.tfBuscadorProducto.setText("");
        this.tfBuscadorProducto.requestFocus();
      }
      catch (Exception e)
      {
        JOptionPane.showMessageDialog(null, "Try catch exeption: " + e.getMessage());
      }
    }
    if ((stock < 1.0D) && (facss.equals("No")))
    {
      JOptionPane.showMessageDialog(null, "�ste producto no se puede facturar por falta de existencia del mismo.", "Existencia agotada", 2);
      this.tfBuscadorProducto.requestFocus();
      this.tfBuscadorProducto.selectAll();
    }
  }
  
  private void AgregarProductoEnLaFacturaPorBTNAgregar()
  {
    int seleccionfila = tbProductos.getSelectedRow();
    
    double stock = 0.0D;
    String facss = "";
    stock = Double.parseDouble(this.tfStock.getText());
    facss = this.tfFactSinStock.getText();
    String ivaprod = this.tfIva.getText() + "%";
    if (stock > 0.0D) {
      try
      {
        if (this.comboBoxPrecio.getSelectedIndex() == 0)
        {
          String precio = this.tfPrecioVenta.getText();
          String codigo = tbProductos.getValueAt(seleccionfila, 0).toString();
          String descripcion = tbProductos.getValueAt(seleccionfila, 1).toString();
          String unidadMedida = this.tfUnidadMedida.getText();
          if (this.tfIva.getText().equals(iva1))
          {
            double iva = div1;
            double totalIva = 0.0D;
            double precioInt = Double.parseDouble(precio);
            
            totalIva = precioInt / iva;
            totalIva = NumeroFormato(totalIva);
            String IvaTotal = String.valueOf(totalIva);
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, "0", IvaTotal, ivaprod };
            modelofactura.addRow(filaseleccionada);
          }
          if (this.tfIva.getText().equals(iva2))
          {
            double iva = div2;
            double totalIva = 0.0D;
            double precioInt = Double.parseDouble(precio);
            
            totalIva = precioInt / iva;
            totalIva = NumeroFormato(totalIva);
            String IvaTotal = String.valueOf(totalIva);
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, IvaTotal, "0", ivaprod };
            modelofactura.addRow(filaseleccionada);
          }
          if (this.tfIva.getText().equals("0.0"))
          {
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, "0", "0", ivaprod };
            modelofactura.addRow(filaseleccionada);
          }
          this.btnFacturar.setEnabled(true);
        }
        if (this.comboBoxPrecio.getSelectedIndex() == 1)
        {
          String precio = this.tfPrecioMayorista.getText();
          String codigo = tbProductos.getValueAt(seleccionfila, 0).toString();
          String descripcion = tbProductos.getValueAt(seleccionfila, 1).toString();
          String unidadMedida = this.tfUnidadMedida.getText();
          if (this.tfIva.getText().equals(iva1))
          {
            double iva = div1;
            double totalIva = 0.0D;
            double precioInt = Double.parseDouble(precio);
            
            totalIva = precioInt / iva;
            totalIva = NumeroFormato(totalIva);
            String IvaTotal = String.valueOf(totalIva);
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, "0", IvaTotal, ivaprod };
            modelofactura.addRow(filaseleccionada);
          }
          if (this.tfIva.getText().equals(iva2))
          {
            double iva = div2;
            double totalIva = 0.0D;
            double precioInt = Double.parseDouble(precio);
            
            totalIva = precioInt / iva;
            totalIva = NumeroFormato(totalIva);
            String IvaTotal = String.valueOf(totalIva);
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, IvaTotal, "0", ivaprod };
            modelofactura.addRow(filaseleccionada);
          }
          if (this.tfIva.getText().equals("0.0"))
          {
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, "0", "0", ivaprod };
            modelofactura.addRow(filaseleccionada);
          }
          this.btnFacturar.setEnabled(true);
        }
        if (this.comboBoxPrecio.getSelectedIndex() == 2)
        {
          String precio = this.tfPrecioCredito.getText();
          String codigo = tbProductos.getValueAt(seleccionfila, 0).toString();
          String descripcion = tbProductos.getValueAt(seleccionfila, 1).toString();
          String unidadMedida = this.tfUnidadMedida.getText();
          if (this.tfIva.getText().equals(iva1))
          {
            double iva = div1;
            double totalIva = 0.0D;
            double precioInt = Double.parseDouble(precio);
            
            totalIva = precioInt / iva;
            totalIva = NumeroFormato(totalIva);
            String IvaTotal = String.valueOf(totalIva);
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, "0", IvaTotal, ivaprod };
            modelofactura.addRow(filaseleccionada);
          }
          if (this.tfIva.getText().equals(iva2))
          {
            double iva = div2;
            double totalIva = 0.0D;
            double precioInt = Double.parseDouble(precio);
            
            totalIva = precioInt / iva;
            totalIva = NumeroFormato(totalIva);
            String IvaTotal = String.valueOf(totalIva);
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, IvaTotal, "0", ivaprod };
            modelofactura.addRow(filaseleccionada);
          }
          if (this.tfIva.getText().equals("0.0"))
          {
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, "0", "0", ivaprod };
            modelofactura.addRow(filaseleccionada);
          }
          this.btnFacturar.setEnabled(true);
        }
        this.btnCancelarFactura.setEnabled(true);
        this.btnCancelarFactura.setIcon(new ImageIcon(FacturaContado.class.getResource("/Iconos/Papelera1.png")));
        CalcularTotalGral();
        this.tfBuscadorProducto.setText("");
        this.tfBuscadorProducto.requestFocus();
      }
      catch (Exception e)
      {
        JOptionPane.showMessageDialog(null, "Try catch exeption: " + e.getMessage());
        e.printStackTrace();
      }
    }
    if ((stock < 1.0D) && (facss.equals("Si"))) {
      try
      {
        if (this.comboBoxPrecio.getSelectedIndex() == 0)
        {
          String precio = this.tfPrecioVenta.getText();
          String codigo = tbProductos.getValueAt(seleccionfila, 0).toString();
          String descripcion = tbProductos.getValueAt(seleccionfila, 1).toString();
          String unidadMedida = this.tfUnidadMedida.getText();
          if (this.tfIva.getText().equals(iva1))
          {
            double iva = div1;
            double totalIva = 0.0D;
            double precioInt = Double.parseDouble(precio);
            
            totalIva = precioInt / iva;
            totalIva = NumeroFormato(totalIva);
            String IvaTotal = String.valueOf(totalIva);
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, "0", IvaTotal, ivaprod };
            modelofactura.addRow(filaseleccionada);
          }
          if (this.tfIva.getText().equals(iva2))
          {
            double iva = div2;
            double totalIva = 0.0D;
            double precioInt = Double.parseDouble(precio);
            
            totalIva = precioInt / iva;
            totalIva = NumeroFormato(totalIva);
            String IvaTotal = String.valueOf(totalIva);
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, IvaTotal, "0", ivaprod };
            modelofactura.addRow(filaseleccionada);
          }
          if (this.tfIva.getText().equals("0.0"))
          {
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, "0", "0", ivaprod };
            modelofactura.addRow(filaseleccionada);
          }
          this.btnFacturar.setEnabled(true);
        }
        if (this.comboBoxPrecio.getSelectedIndex() == 1)
        {
          String precio = this.tfPrecioMayorista.getText();
          String codigo = tbProductos.getValueAt(0, 0).toString();
          String descripcion = tbProductos.getValueAt(0, 1).toString();
          String unidadMedida = this.tfUnidadMedida.getText();
          if (this.tfIva.getText().equals(iva1))
          {
            double iva = div1;
            double totalIva = 0.0D;
            double precioInt = Double.parseDouble(precio);
            
            totalIva = precioInt / iva;
            totalIva = NumeroFormato(totalIva);
            String IvaTotal = String.valueOf(totalIva);
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, "0", IvaTotal, ivaprod };
            modelofactura.addRow(filaseleccionada);
          }
          if (this.tfIva.getText().equals(iva2))
          {
            double iva = div2;
            double totalIva = 0.0D;
            double precioInt = Double.parseDouble(precio);
            
            totalIva = precioInt / iva;
            totalIva = NumeroFormato(totalIva);
            String IvaTotal = String.valueOf(totalIva);
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, IvaTotal, "0", ivaprod };
            modelofactura.addRow(filaseleccionada);
          }
          if (this.tfIva.getText().equals("0.0"))
          {
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, "0", "0", ivaprod };
            modelofactura.addRow(filaseleccionada);
          }
          this.btnFacturar.setEnabled(true);
        }
        if (this.comboBoxPrecio.getSelectedIndex() == 2)
        {
          String precio = this.tfPrecioCredito.getText();
          String codigo = tbProductos.getValueAt(0, 0).toString();
          String descripcion = tbProductos.getValueAt(0, 1).toString();
          String unidadMedida = this.tfUnidadMedida.getText();
          if (this.tfIva.getText().equals(iva1))
          {
            double iva = div1;
            double totalIva = 0.0D;
            double precioInt = Double.parseDouble(precio);
            
            totalIva = precioInt / iva;
            totalIva = NumeroFormato(totalIva);
            String IvaTotal = String.valueOf(totalIva);
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, "0", IvaTotal, ivaprod };
            modelofactura.addRow(filaseleccionada);
          }
          if (this.tfIva.getText().equals(iva2))
          {
            double iva = div2;
            double totalIva = 0.0D;
            double precioInt = Double.parseDouble(precio);
            
            totalIva = precioInt / iva;
            totalIva = NumeroFormato(totalIva);
            String IvaTotal = String.valueOf(totalIva);
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, IvaTotal, "0", ivaprod };
            modelofactura.addRow(filaseleccionada);
          }
          if (this.tfIva.getText().equals("0.0"))
          {
            String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio, "0", "0", ivaprod };
            modelofactura.addRow(filaseleccionada);
          }
          this.btnFacturar.setEnabled(true);
        }
        this.btnCancelarFactura.setEnabled(true);
        this.btnCancelarFactura.setIcon(new ImageIcon(FacturaContado.class.getResource("/Iconos/Papelera1.png")));
        CalcularTotalGral();
        this.tfBuscadorProducto.setText("");
        this.tfBuscadorProducto.requestFocus();
      }
      catch (Exception e)
      {
        JOptionPane.showMessageDialog(null, "Try catch exeption: " + e.getMessage());
        e.printStackTrace();
      }
    }
    if ((stock < 1.0D) && (facss.equals("No")))
    {
      JOptionPane.showMessageDialog(null, "�ste producto no se puede facturar por falta de existencia del mismo.", "Existencia agotada", 2);
      this.tfBuscadorProducto.requestFocus();
      this.tfBuscadorProducto.selectAll();
    }
  }
  
  private void ContarFilas()
  {
    int filas = tbFactura.getRowCount();
    this.tfItem.setText(String.valueOf(filas));
  }
  
  public void CalcularTotalGral()
  {
    DecimalFormat formato = new DecimalFormat("###,###.##");
    double sumatoria = 0.0D;
    double total = 0.0D;
    int totalRow = tbFactura.getRowCount();
    totalRow--;
    for (int i = 0; i <= totalRow; i++)
    {
      if (tbFactura.getValueAt(i, 5).equals(null)) {
        sumatoria = 0.0D;
      } else {
        sumatoria = Double.parseDouble(String.valueOf(tbFactura.getValueAt(i, 5)));
      }
      total += sumatoria;
      String totalVer = formato.format(total);
      this.tfTotalVer.setText(totalVer);
      tfTotal.setText(String.valueOf(total));
      total = NumeroFormato(total);
    }
    if (total > 9999999.0D)
    {
      int a = (int)total;
      String totalObtenido = String.valueOf(a);
      tfTotal.setText(totalObtenido);
    }
    else
    {
      String totalObtenido = String.valueOf(total);
      tfTotal.setText(totalObtenido);
    }
    CalcularTotalIvaC();
    CalcularTotalIvaD();
    CalcularTotalIvaGeneral();
    if (tfTotal.getText().trim().length() > 4)
    {
      this.btnFacturar.setEnabled(true);
      this.btnInsertarCliente.setEnabled(true);
    }
  }
  
  private void CalcularTotalIvaC()
  {
    double sumatoria = 0.0D;
    double total = 0.0D;
    int totalRow = tbFactura.getRowCount();
    totalRow--;
    for (int i = 0; i <= totalRow; i++)
    {
      if (tbFactura.getValueAt(i, 5).equals(null)) {
        sumatoria = 0.0D;
      } else {
        sumatoria = Double.parseDouble(String.valueOf(tbFactura.getValueAt(i, 6)));
      }
      total += sumatoria;
      total = NumeroFormato(total);
    }
    String totalObtenidob = String.valueOf(total);
    this.tfIva2.setText(totalObtenidob);
  }
  
  private void CalcularTotalIvaD()
  {
    double sumatoria = 0.0D;
    double total = 0.0D;
    int totalRow = tbFactura.getRowCount();
    totalRow--;
    for (int i = 0; i <= totalRow; i++)
    {
      if (tbFactura.getValueAt(i, 5).equals(null)) {
        sumatoria = 0.0D;
      } else {
        sumatoria = Double.parseDouble(String.valueOf(tbFactura.getValueAt(i, 7)));
      }
      total += sumatoria;
      total = NumeroFormato(total);
    }
    String totalObtenidob = String.valueOf(total);
    this.tfIva1.setText(totalObtenidob);
  }
  
  private void CalcularTotalIvaGeneral()
  {
    double totalIvaC = Double.parseDouble(this.tfIva2.getText());
    double totalIvaD = Double.parseDouble(this.tfIva1.getText());
    double totalIva = 0.0D;
    totalIva = totalIvaC + totalIvaD;
    
    totalIva = NumeroFormato(totalIva);
    if (totalIva > 9999999.0D)
    {
      int d = (int)totalIva;
      String totalObtenidod = String.valueOf(d);
      this.tfIvaTotal.setText(totalObtenidod);
    }
    else
    {
      String totalObtenidod = String.valueOf(totalIva);
      this.tfIvaTotal.setText(totalObtenidod);
    }
  }
  
  private double CalcularTotal10Filas()
  {
    double sumatoria = 0.0D;
    double total = 0.0D;
    int totalRow = tbFactura.getRowCount();
    totalRow--;
    for (int i = 0; i <= totalRow; i++)
    {
      int contador = 10;
      while (contador > 1)
      {
        if (tbFactura.getValueAt(i, 5).equals(null)) {
          sumatoria = 0.0D;
        } else {
          sumatoria = Double.parseDouble(String.valueOf(tbFactura.getValueAt(i, 5)));
        }
        total += sumatoria;
        if (contador == 1)
        {
          contador = 0;
          totalRow = 10;
          i = 1500;
        }
        contador--;
      }
    }
    return total;
  }
  
  private void BuscarProductoDescripcion()
  {
    listaProductos = new ArrayList();
    listaProductos = DaoProductos.BuscarProductoDescripcion(this.tfBuscadorProducto.getText());
    ActualizarTablaPrincipal();
    LimpiarDetallesDeProducto();
  }
  
  private void BuscarProductoCodigo()
  {
    listaProductos = new ArrayList();
    listaProductos = DaoProductos.BuscarProductoCodigo(this.tfBuscadorProducto.getText());
    ActualizarTablaPrincipal();
    LimpiarDetallesDeProducto();
  }
  
  public void LimpiarFactura()
  {
    try
    {
      int yes = JOptionPane.showConfirmDialog(this, "�Est� seguro que desea limpiar la lista?", "Aviso", 0);
      if (yes == 0)
      {
        int filaTotal = tbFactura.getRowCount();
        while (filaTotal >= 1)
        {
          modelofactura.removeRow(0);
          this.btnCancelarFactura.setEnabled(false);
          filaTotal--;
        }
      }
      else
      {
        this.btnCancelarFactura.setEnabled(true);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public void LimpiarFacturaSinPreguntar()
  {
    try
    {
      int filaTotal = tbFactura.getRowCount();
      while (filaTotal >= 1)
      {
        modelofactura.removeRow(0);
        this.btnCancelarFactura.setEnabled(false);
        filaTotal--;
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  private void QuitarProducto()
  {
    int filaseleccionada = tbFactura.getSelectedRow();
    if (filaseleccionada < 0)
    {
      JOptionPane.showMessageDialog(null, "Seleccione un producto para quitar de la lista", "Aviso", 2);
    }
    else
    {
      String dato = tbFactura.getValueAt(filaseleccionada, 3).toString();
      int yes = JOptionPane.showConfirmDialog(this, "�Est� seguro que desea quitar el producto\n" + 
        dato + "\nde la lista de la factura?", "Aviso", 0);
      if (yes == 0)
      {
        if (filaseleccionada < 0)
        {
          JOptionPane.showMessageDialog(null, "Seleccione un producto");
        }
        else
        {
          modelofactura.removeRow(filaseleccionada);
          CalcularTotalGral();
          ContarFilas();
          int fila = tbFactura.getRowCount();
          if (fila == 0)
          {
            this.btnFacturar.setEnabled(false);
            this.btnCancelarFactura.setEnabled(false);
          }
        }
        this.btnQuitar.setEnabled(false);
      }
    }
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
  
  private void GuardarDatosFacturaCabeceraBD()
  {
    int codigo = DaoFacturaContado.IncrementarCodigoCabecera() + 1;
    this.tfNFactura.setText(String.valueOf(codigo));
    ModeloFacturaContado cabecera = new ModeloFacturaContado();
    cabecera.setCodigoCabecera(codigo);
    cabecera.setCodigoCliente(Integer.parseInt(tfCodCliente.getText()));
    cabecera.setFecha(fecha());
    cabecera.setHora(hora());
    cabecera.setTotal(Double.parseDouble(tfTotal.getText()));
    cabecera.setTiva1(Double.parseDouble(this.tfIva1.getText()));
    cabecera.setTiva2(Double.parseDouble(this.tfIva2.getText()));
    cabecera.setEstado("EMITIDO");
    cabecera.getVendedor().setCodigo(Integer.parseInt(this.comboBoxVendedor.getSelectedItem().toString().split("-")[0]));
    if (!this.descuento)
    {
      cabecera.setDescuento(0.0D);
      cabecera.setMontoDescuento(0.0D);
    }
    else
    {
      cabecera.setDescuento(Double.parseDouble(this.tfDescPorc.getText()));
      cabecera.setMontoDescuento(Double.parseDouble(this.tfDescMonto.getText()));
    }
    DaoFacturaContado.GuardarFacturaCabecera(cabecera);
  }
  
  private void GuardarDatosFacturaDetalleBD()
  {
    //imprimirFactura();
    int filaTotal = tbFactura.getRowCount();
    while (filaTotal >= 1)
    {
      double precioUnit = Double.parseDouble(String.valueOf(tbFactura.getValueAt(0, 4)));
      double stockactual = 0.0D;
      String codigoproducto = tbFactura.getValueAt(0, 2).toString();
      String descripcionprod = tbFactura.getValueAt(0, 3).toString();
      String subtotal = String.valueOf(tbFactura.getValueAt(0, 5));
      double subtotaln = Double.parseDouble(String.valueOf(tbFactura.getValueAt(0, 5)));
      ModeloProductos productos = DaoProductos.ConsutaParaModificar(String.valueOf(codigoproducto));
      String descripcion = productos.getDescripcion();
      stockactual = productos.getStock();
      double cantidadLLevada = Double.parseDouble(String.valueOf(tbFactura.getValueAt(0, 0)));
      double updatestock = 0.0D;
      
      ModeloFacturaContado detalle = new ModeloFacturaContado();
      detalle.setCodigoProducto(codigoproducto);
      detalle.setPrecioUnitario(precioUnit);
      if (subtotal.equals("null"))
      {
        JOptionPane.showMessageDialog(null, "El producto " + descripcionprod + " no se puede facturar\nha habido un error en la cantidad o el subtotal.", "Error", 2);
        JOptionPane.showMessageDialog(null, "Verifique los detalles en 'Facturas Emitidas' si desea ver\ncu�les fueron productos se han facturado.");
        LimpiarFacturaSinPreguntar();
        dispose();
      }
      detalle.setSubtotal(subtotaln);
      detalle.setUnidadDeMedida(String.valueOf(tbFactura.getValueAt(0, 1)));
      detalle.setCantidad(cantidadLLevada);
      detalle.setCodigoCabecera(Integer.parseInt(this.tfNFactura.getText()));
      
      updatestock = stockactual - cantidadLLevada;
      if ((productos.getFacturar().equals("No")) && (updatestock < 0.0D))
      {
        JOptionPane.showMessageDialog(null, "Los productos con stock insuficiente no se podr�n facturar.", "Existencia insuficiente", 2);
        JOptionPane.showMessageDialog(null, "Verifique el stock del producto " + descripcion + ", solo quedan " + stockactual + " en stock.");
        double totalguardado = Double.parseDouble(tfTotal.getText());
        double subtotalarestar = Double.parseDouble(String.valueOf(tbFactura.getValueAt(0, 5)));
        double totalaguardar = totalguardado - subtotalarestar;
        
        tfTotal.setText(String.valueOf(totalaguardar));
        ModeloFacturaContado x = new ModeloFacturaContado();
        x.setCodigoCabecera(Integer.parseInt(this.tfNFactura.getText()));
        x.setTotal(totalaguardar);
        DaoFacturaContado.ActTotCabEnCasoDeStockInsuf(x);
        
        double iva = Double.parseDouble(String.valueOf(tbFactura.getValueAt(0, 6)));
        if (iva == 0.0D)
        {
          double tIvaguardado = Double.parseDouble(this.tfIvaTotal.getText());
          double IvaArestar = Double.parseDouble(String.valueOf(tbFactura.getValueAt(0, 7)));
          double IvaAguardar = tIvaguardado - IvaArestar;
          this.tfIvaTotal.setText(String.valueOf(IvaAguardar));
          ModeloFacturaContado fc = new ModeloFacturaContado();
          fc.setTotalIva(IvaAguardar);
          fc.setCodigoCabecera(Integer.parseInt(this.tfNFactura.getText()));
          DaoFacturaContado.ActTotIvaEnCasoDeStockInsuf(fc);
        }
        else
        {
          double tIvaguardado = Double.parseDouble(this.tfIvaTotal.getText());
          double IvaArestar = Double.parseDouble(String.valueOf(tbFactura.getValueAt(0, 6)));
          double IvaAguardar = tIvaguardado - IvaArestar;
          this.tfIvaTotal.setText(String.valueOf(IvaAguardar));
          ModeloFacturaContado fc = new ModeloFacturaContado();
          fc.setTotalIva(IvaAguardar);
          fc.setCodigoCabecera(Integer.parseInt(this.tfNFactura.getText()));
          DaoFacturaContado.ActTotIvaEnCasoDeStockInsuf(fc);
        }
      }
      else
      {
        if ((productos.getFacturar().equals("No")) && (updatestock == 0.0D)) {
          JOptionPane.showMessageDialog(null, "El producto " + descripcion + " ha llegado en stock cero.");
        }
        String iva = String.valueOf(tbFactura.getValueAt(0, 8));
        detalle.setIva(iva);
        DaoFacturaContado.GuardarFacturaDetalle(detalle);
        ModeloProductos p = new ModeloProductos();
        
        p.setCodigo(String.valueOf(tbFactura.getValueAt(0, 2)));
        p.setStock(updatestock);
        
        DaoProductos.ActualizarStock(p);
      }
      this.info = new ModeloInformaciones();
      try
      {
        int codigoInfo = DaoInformaciones.IncrementarCodigo() + 1;
        this.info = DaoInformaciones.Existencia(tbFactura.getValueAt(0, 2).toString(), fecha(), "CONTADO");
        if (this.info != null)
        {
          double precioUnitario = Double.parseDouble(String.valueOf(tbFactura.getValueAt(0, 4)));
          double cantvendidabd = 0.0D;
          double update = 0.0D;
          double subtotalbd = 0.0D;
          double calsubtotal = 0.0D;
          double subtotalact = 0.0D;
          
          double utilidad = 0.0D;
          double precioCosto = productos.getPrecioCosto();
          
          ModeloInformaciones i = DaoInformaciones.ConsultaForUpdate(codigoproducto, fecha(), "CONTADO");
          subtotalbd = i.getSubtotal();
          cantvendidabd = i.getCantVendida();
          double utilidadBD = i.getUtilidad();
          update = cantvendidabd + cantidadLLevada;
          calsubtotal = cantidadLLevada * precioUnitario;
          
          utilidad = (precioUnit - precioCosto) * cantidadLLevada;
          
          utilidadBD += utilidad;
          
          subtotalact = calsubtotal + subtotalbd;
          ModeloInformaciones info = new ModeloInformaciones();
          info.setCodigo(codigoInfo);
          info.setCantVendida(update);
          info.setCodigoProducto(codigoproducto);
          info.setSubtotal(subtotalact);
          info.setUtilidad(utilidadBD);
          DaoInformaciones.ActualizarCantidadVendida(info, fecha(), "CONTADO");
        }
        else
        {
          double utilidad = 0.0D;
          double precioCosto = productos.getPrecioCosto();
          
          utilidad = (precioUnit - precioCosto) * cantidadLLevada;
          
          ModeloInformaciones info = new ModeloInformaciones();
          info.setCodigo(codigoInfo);
          info.setCodigoProducto(codigoproducto);
          info.setCantVendida(cantidadLLevada);
          info.setFecha(fecha());
          info.setUtilidad(utilidad);
          info.setSubtotal(Double.parseDouble(subtotal));
          info.setPrecioUnitario(Double.parseDouble(String.valueOf(tbFactura.getValueAt(0, 4).toString())));
          info.setCondicion("CONTADO");
          DaoInformaciones.InsertarInformacion(info);
        }
      }
      catch (NumberFormatException e)
      {
        e.printStackTrace();
      }
      catch (Exception el)
      {
        el.printStackTrace();
      }
      this.btnCancelarFactura.setEnabled(false);
      modelofactura.removeRow(0);
      filaTotal--;
    }
  }
  
  private boolean ValidarDatosDelCliente()
  {
    if (tfCodCliente.getText().isEmpty())
    {
      JOptionPane.showMessageDialog(null, "Los datos del cliente son obligatorios para la facturaci�n.", null, 1);
      ClientesDatosSetFac.main(null);
      return false;
    }
    return true;
  }
  
  private void FacturacionGeneral()
  {
    int yes = JOptionPane.showConfirmDialog(this, "�Desea continuar con la facturaci�n?", "Pregunta", 0);
    if (yes == 0)
    {
      GuardarDatosFacturaCabeceraBD();
      GuardarDatosFacturaDetalleBD();
      LimpiarTextFieldsLuegoDeFacturar();
      tbProductos.removeAll();
      this.btnFacturar.setEnabled(false);
      this.scrollPaneFact.setBounds(468, 68, 340, 434);
      this.tfMontoACobrar.setText(tfTotal.getText());
      this.tfDinero.setText(tfTotal.getText());
      this.tfDinero.requestFocus();
      this.tfDinero.selectAll();
    }
  }
  
  private void LimpiarTextFieldsLuegoDeFacturar()
  {
    tfCodCliente.setText("");
    tfNombreCliente.setText("");
    tfTelefono.setText("");
    tfDireccion.setText("");
    tfCodCliente.setText("");
    this.tfIva2.setText("");
    this.tfIva1.setText("");
    this.tfItem.setText("");
    this.tfIvaTotal.setText("");
    this.tfTotalVer.setText("");
  }
  
  private void LimpiarDetallesDeProducto()
  {
    this.tfPrecioCosto.setText("");
    this.tfPrecioCredito.setText("");
    this.tfPrecioMayorista.setText("");
    this.tfPrecioVenta.setText("");
    this.tfStock.setText("");
    this.tfCantPaquete.setText("");
  }
  
  private void Salir(WindowEvent evt)
  {
    if (this.btnFacturar.isEnabled())
    {
      int res = JOptionPane.showConfirmDialog(null, "�Desea salir sin facturar?", "Salir", 0);
      if (res == 0)
      {
        LimpiarDetalleAlSalir();
        
        dispose();
      }
    }
    else
    {
      dispose();
    }
  }
  
  private void LimpiarDetalleAlSalir()
  {
    int filaTotal = tbFactura.getRowCount();
    int filaTotal2 = tbProductos.getRowCount();
    while (filaTotal >= 1)
    {
      modelofactura.removeRow(0);
      filaTotal--;
    }
    while (filaTotal2 >= 1)
    {
      modeloproductos.removeRow(0);
      filaTotal2--;
    }
    tfTotal.setText("");
    this.tfIva2.setText("");
    this.tfIva1.setText("");
    this.tfItem.setText("");
    this.tfIvaTotal.setText("");
  }
  
  private void CalcularCambio()
  {
    if (this.tfDinero.getText().trim().length() < 1)
    {
      JOptionPane.showMessageDialog(null, "Ingrese un valor para el c�lculo del cambio");
    }
    else
    {
      double dinero = Double.parseDouble(this.tfDinero.getText());
      double cobrar = Double.parseDouble(tfTotal.getText());
      double cambio = 0.0D;
      
      cambio = dinero - cobrar;
      if (cambio > 0.0D)
      {
        this.tfCambio.setText(String.valueOf(cambio));
        this.tfCambio.setEditable(false);
        Conversion();
      }
      if (cambio < 0.0D)
      {
        JOptionPane.showMessageDialog(null, "El dinero que ha recibido es insuficiente para cobrar.");
        this.tfDinero.requestFocus();
        this.tfDinero.selectAll();
        return;
      }
    }
    dispose();
  }
  
  private void Conversion()
  {
    double total = Double.parseDouble(this.tfCambio.getText());
    int num = (int)total;
    if (total < 9999999.0D)
    {
      ConversionEnLetras numero = new ConversionEnLetras(num);
      String res = numero.convertirLetras(num);
      
      JOptionPane.showMessageDialog(null, "EL CAMBIO ES: " + res + " " + moneda);
    }
  }
  
  private String TotalEnLetras()
  {
    String res = "";
    double total = Double.parseDouble(tfTotal.getText());
    int num = (int)total;
    if (total < 9999999.0D)
    {
      ConversionEnLetras numero = new ConversionEnLetras(num);
      res = numero.convertirLetras(num);
    }
    return res + " " + moneda;
  }
  
  private static void ActualizarTablaDetallePresupuesto()
  {
    while (modelofactura.getRowCount() > 0) {
      modelofactura.removeRow(0);
    }
    String[] campos = new String[6];
    for (int i = 0; i < listapresupuesto.size(); i++)
    {
      modelofactura.addRow(campos);
      ModeloPresupuesto presupuesto = (ModeloPresupuesto)listapresupuesto.get(i);
      
      modelofactura.setValueAt(Integer.valueOf(presupuesto.getCodigoDetalle()), i, 0);
      modelofactura.setValueAt(Double.valueOf(presupuesto.getCantidad()), i, 1);
      modelofactura.setValueAt(presupuesto.getProducto().getCodigo(), i, 2);
      modelofactura.setValueAt(presupuesto.getProducto().getDescripcion(), i, 3);
      modelofactura.setValueAt(Double.valueOf(presupuesto.getPrecioUnitario()), i, 4);
      modelofactura.setValueAt(Double.valueOf(presupuesto.getSubtotal()), i, 5);
    }
  }
  
  private boolean VerificarExistTabDet()
  {
    int seleccion = tbProductos.getSelectedRow();
    int filaTotal = tbFactura.getRowCount();
    String codProd = String.valueOf(tbProductos.getValueAt(seleccion, 0));
    String retorno = "";
    double cantidad = 0.0D;
    while (filaTotal > 0)
    {
      filaTotal--;
      String codProdTablaFactura = String.valueOf(tbFactura.getValueAt(filaTotal, 2));
      if (codProd.equals(codProdTablaFactura))
      {
        retorno = codProd;
        tbFactura.changeSelection(filaTotal, 0, false, false);
        JOptionPane.showMessageDialog(null, "�ste producto ya se encuentra en la lista de la factura.");
        cantidad = Double.parseDouble(String.valueOf(tbFactura.getValueAt(filaTotal, 0)));
        cantidad += 1.0D;
        tbFactura.setValueAt(Double.valueOf(cantidad), filaTotal, 0);
        InsertarCantYCalSubTotal();
        if (tbFactura.getValueAt(filaTotal, 6).equals(iva1))
        {
          double iva = div1;
          double totalIva = 0.0D;
          double subTotal = Double.parseDouble(String.valueOf(tbFactura.getValueAt(filaTotal, 5)));
          
          totalIva = subTotal / iva;
          totalIva = NumeroFormato(totalIva);
          tbFactura.setValueAt(Double.valueOf(totalIva), filaTotal, 6);
        }
        if (tbFactura.getValueAt(filaTotal, 6).equals(iva2))
        {
          double iva = div2;
          double totalIva = 0.0D;
          double subTotal = Double.parseDouble(String.valueOf(tbFactura.getValueAt(filaTotal, 5)));
          
          totalIva = subTotal / iva;
          totalIva = NumeroFormato(totalIva);
          tbFactura.setValueAt(Double.valueOf(totalIva), filaTotal, 7);
        }
        return false;
      }
    }
    return true;
  }
  
  private boolean LectorVerificarExistTabDet()
  {
    int filaTotal = tbFactura.getRowCount();
    String codProd = String.valueOf(tbProductos.getValueAt(0, 0));
    String retorno = "";
    double cantidad = 0.0D;
    while (filaTotal > 0)
    {
      filaTotal--;
      String codProdTablaFactura = String.valueOf(tbFactura.getValueAt(filaTotal, 2));
      if (codProd.equals(codProdTablaFactura))
      {
        retorno = codProd;
        tbFactura.changeSelection(filaTotal, 0, false, false);
        JOptionPane.showMessageDialog(null, "�ste producto ya se encuentra en la lista de la factura.");
        cantidad = Double.parseDouble(String.valueOf(tbFactura.getValueAt(filaTotal, 0)));
        cantidad += 1.0D;
        tbFactura.setValueAt(Double.valueOf(cantidad), filaTotal, 0);
        InsertarCantYCalSubTotal();
        if (tbFactura.getValueAt(filaTotal, 7).equals(iva1))
        {
          double iva = div1;
          double totalIva = 0.0D;
          double subTotal = Double.parseDouble(String.valueOf(tbFactura.getValueAt(filaTotal, 5)));
          
          totalIva = subTotal / iva;
          totalIva = NumeroFormato(totalIva);
          tbFactura.setValueAt(Double.valueOf(totalIva), filaTotal, 6);
        }
        if (tbFactura.getValueAt(filaTotal, 6).equals(iva2))
        {
          double iva = div2;
          double totalIva = 0.0D;
          double subTotal = Double.parseDouble(String.valueOf(tbFactura.getValueAt(filaTotal, 5)));
          
          totalIva = subTotal / iva;
          totalIva = NumeroFormato(totalIva);
          
          tbFactura.setValueAt(Double.valueOf(totalIva), filaTotal, 7);
        }
        return false;
      }
    }
    return true;
  }
  
  private String FechaEnLetras()
  {
    String[] Meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
    
    String FechaCompleta = "";
    
    String dia = fecha().split("/")[0];
    int mes = Integer.parseInt(String.valueOf(fecha().split("/")[1]));
    String anho = fecha().split("/")[2];
    
    System.out.println(dia + " de " + Meses[(mes - 1)] + " del " + anho);
    FechaCompleta = dia + " de " + Meses[(mes - 1)] + " del " + anho;
    
    return FechaCompleta;
  }
  
  private void Descontar(String descontar)
  {
    int filaTotal = tbFactura.getRowCount();
    this.descuento = true;
    double cantidad = 0.0D;
    double precio = 0.0D;
    double subtotal = 0.0D;
    double iva1 = 0.0D;
    double iva2 = 0.0D;
    while (filaTotal > 0)
    {
      filaTotal--;
      double porcDesc = Double.parseDouble(descontar);
      
      String codProd = String.valueOf(tbFactura.getValueAt(filaTotal, 2));
      ModeloProductos p = DaoProductos.Descuento(codProd);
      double desc = p.getDescuento();
      if (desc != 0.0D)
      {
        if (porcDesc > desc) {
          porcDesc = desc;
        }
        String costo = String.valueOf(tbFactura.getValueAt(filaTotal, 4));
        String cantidadEntrada = String.valueOf(tbFactura.getValueAt(filaTotal, 0));
        String iva1s = String.valueOf(tbFactura.getValueAt(filaTotal, 6));
        String iva2s = String.valueOf(tbFactura.getValueAt(filaTotal, 7));
        
        iva1 = Double.parseDouble(iva1s);
        iva2 = Double.parseDouble(iva2s);
        
        iva1 -= iva1 * porcDesc / 100.0D;
        double ivar1 = NumeroFormato(iva1);
        iva2 -= iva2 * porcDesc / 100.0D;
        double ivar2 = NumeroFormato(iva2);
        
        cantidad = Double.parseDouble(cantidadEntrada);
        precio = Double.parseDouble(costo);
        precio -= precio * porcDesc / 100.0D;
        
        subtotal = cantidad * precio;
        
        subtotal = cantidad * precio;
        
        tbFactura.setValueAt(Double.valueOf(precio), filaTotal, 4);
        tbFactura.setValueAt(Double.valueOf(ivar1), filaTotal, 6);
        tbFactura.setValueAt(Double.valueOf(ivar2), filaTotal, 7);
        tbFactura.setValueAt(Double.valueOf(subtotal), filaTotal, 5);
      }
    }
  }
  
  private double NumeroFormato(double num)
  {
    double valor = 0.0D;
    
    valor = num;
    
    valor *= 100.0D;
    valor = Math.round(valor);
    valor /= 100.0D;
    
    return valor;
  }
  
  /*public void imprimirFactura()
  {
    PrinterMatrix printer = new PrinterMatrix();
    
    printer.setOutSize(80, 100);
    
    printer.printCharAtCol(1, 1, 100, "=");
    
    printer.printTextWrap(1, 2, 35, 100, "     " + nombreEmpresa);
    
    printer.printTextWrap(2, 3, 1, 22, "N� FACTURA:   001-001-" + this.tfNFactura.getText());
    printer.printTextWrap(2, 3, 30, 55, FechaEnLetras().toUpperCase());
    printer.printTextWrap(2, 3, 70, 100, "HORA: " + hora());
    printer.printTextWrap(3, 3, 1, 100, "VENDEDOR:     " + this.comboBoxVendedor.getSelectedItem().toString().split("-")[1]);
    printer.printTextWrap(4, 4, 1, 100, "CLIENTE:      " + tfNombreCliente.getText());
    printer.printTextWrap(5, 5, 1, 100, "RUC/C.I.N�:   " + tfCedulaRUC.getText());
    printer.printTextWrap(6, 6, 1, 100, "DIRECCION:    " + tfDireccion.getText().toString());
    printer.printCharAtCol(8, 2, 100, "=");
    printer.printTextWrap(8, 9, 1, 100, "CANT.     C�DIGO          DESCRIPCI�N DEL PRODUCTO                 P.UNIT.     SUB-TOTAL      IVA");
    printer.printCharAtCol(10, 2, 
      100, "-");
    
    int filas = tbFactura.getRowCount();
    for (int i = 0; i < filas; i++)
    {
      String cantidad = modelofactura.getValueAt(i, 0).toString() + " " + modelofactura.getValueAt(i, 1).toString();
      String codigo = modelofactura.getValueAt(i, 2).toString();
      String descripcion = modelofactura.getValueAt(i, 3).toString();
      String precUnit = modelofactura.getValueAt(i, 4).toString();
      String subtotal = modelofactura.getValueAt(i, 5).toString();
      String ivatxt = modelofactura.getValueAt(i, 8).toString();
      while (cantidad.length() < 9) {
        cantidad = cantidad + " ";
      }
      while (codigo.length() < 16) {
        codigo = codigo + " ";
      }
      if (descripcion.length() > 40) {
        descripcion = descripcion.substring(0, 40);
      } else {
        while (descripcion.length() < 40) {
          descripcion = descripcion + " ";
        }
      }
      if (subtotal.length() < 12) {
        while (subtotal.length() < 12) {
          subtotal = subtotal + " ";
        }
      }
      if (precUnit.length() < 10) {
        while (precUnit.length() < 10) {
          precUnit = precUnit + " ";
        }
      }
      String items = cantidad + " " + codigo + descripcion + " " + precUnit + "  " + subtotal + "   " + ivatxt;
      
      printer.printTextWrap(10 + i, 5, 1, 100, items);
    }
    printer.printCharAtCol(26, 1, 100, "=");
    printer.printTextWrap(26, 1, 1, 100, "TOTAL A PAGAR: " + tfTotal.getText() + "               " + "Iva " + iva1 + "%=" + this.tfIva1.getText() + "   " + "Iva " + iva2 + "%=" + this.tfIva2.getText());
    printer.printTextWrap(27, 1, 1, 100, "TOTAL EN LETRAS: " + TotalEnLetras());
    printer.printTextWrap(28, 1, 1, 100, "Descuento:" + this.tfDescPorc.getText() + "%" + " --- " + "Monto:" + this.tfDescMonto.getText());
    printer.printCharAtCol(30, 1, 100, "=");
    printer.printTextWrap(31, 1, 1, 100, "Esta boleta no tiene valor fiscal, solo para uso interno.");
    printer.printTextWrap(32, 1, 1, 100, DaoConfiguraciones.Eslogan());
    
    printer.toFile("data/doc/factura/" + this.tfNFactura.getText() + "factura.txt");
    
    Desktop desktop = Desktop.getDesktop();
    
    File archivo = new File(new File("data/doc/factura/" + this.tfNFactura.getText() + "factura.txt").getAbsolutePath());
    try
    {
      desktop.print(archivo);
    }
    catch (IOException e1)
    {
      e1.printStackTrace();
    }
  }   */ 
}
