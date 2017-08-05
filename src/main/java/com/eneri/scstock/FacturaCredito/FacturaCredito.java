/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.FacturaCredito;

import com.eneri.scstock.Apariencia.Colores;
import com.eneri.scstock.Apariencia.FondoTransacciones;
import com.eneri.scstock.FacturaContado.ClientesDatosSetFac;
import com.eneri.scstock.FacturaContado.FacturaContado;
import com.eneri.scstock.Modelos.ModeloFacturaCredito;
import com.eneri.scstock.Modelos.ModeloInformaciones;
import com.eneri.scstock.Modelos.ModeloPresupuesto;
import com.eneri.scstock.Modelos.ModeloProductos;
import com.eneri.scstock.Modelos.ModeloVendedores;
import com.eneri.scstock.Objetos.DaoCobranzas;
import com.eneri.scstock.Objetos.DaoFacturaCredito;
import com.eneri.scstock.Objetos.DaoInformaciones;
import com.eneri.scstock.Objetos.DaoPresupuesto;
import com.eneri.scstock.Objetos.DaoProductos;
import com.eneri.scstock.Objetos.DaoVendedores;
import java.awt.Color;
import java.awt.Cursor;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAscencio
 */
public class FacturaCredito extends JDialog
{
    
  private JTable tbFactura;
  private JTable tbProductos;
  private JTextField tfPrecioCredito;
  private JTextField tfPrecioCosto;
  private JTextField tfCantPaquete;
  private JTextField tfStock;
  private JTextField tfBuscadorProducto;
  private JButton btnBuscarProducto;
  private JTextField tfTotal;
  private JButton btnAgregar;
  private JButton btnFacturar;
  private JTextField tfNFactura;
  public static JTextField tfCedulaRUC;
  public static JTextField tfNombreCliente;
  public static List<ModeloProductos> listaProductos;
  public static List<ModeloPresupuesto> listapresupuesto;
  private ModeloInformaciones info;
  private ModeloProductos producto;
  public static DefaultTableModel modelofactura = new DefaultTableModel(null, new String[] { "Cantidad", "Unidad", "C�digo", 
    "Descripci�n del producto", "P. Unit.", "Subtotal" })
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
  private JButton btnLimpiarLista;
  private JScrollPane scrollPane;
  private JButton btnInsertarCliente;
  private JLabel lblNDeFactura;
  private JLabel lblRuccin;
  private JLabel lblNombre;
  private JLabel lblTelfono;
  private JLabel lblDireccin;
  private JLabel lblPrecioCredito;
  private JLabel lblStock;
  private JLabel lblCantPaquete;
  private JLabel lblPrecioDeCosto;
  private JTextField tfColor;
  private JTextField tfFactSinStock;
  private JLabel lblDatosDelCliente;
  public static JTextField tfCodCliente;
  private JLabel lblFacturacion;
  public static List<ModeloVendedores> listaVendedores;
  private JLabel lblVendedor;
  private JComboBox comboBoxVendedor;
  private JButton btnRecuperar;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          FacturaCredito dialog = new FacturaCredito();
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
  
  public FacturaCredito()
  {
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent evt)
      {
        FacturaCredito.this.Salir(evt);
      }
    });
    setResizable(false);
    setBounds(100, 100, 1189, 643);
    setTitle("Venta a cr�dito");
    setDefaultCloseOperation(0);
    setIconImage(Toolkit.getDefaultToolkit().getImage(FacturaCredito.class.getResource("/Galeria/Deudas.png")));
    
    FondoTransacciones contentPane = new FondoTransacciones();
    contentPane.setForeground(Color.BLACK);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    this.scrollPaneFact = new JScrollPane();
    this.scrollPaneFact.setVerticalScrollBarPolicy(22);
    this.scrollPaneFact.setBounds(10, 277, 1163, 264);
    getContentPane().add(this.scrollPaneFact);
    
    this.tbFactura = new JTable();
    this.tbFactura.setCursor(Cursor.getPredefinedCursor(2));
    this.tbFactura.getTableHeader().setReorderingAllowed(false);
    this.tbFactura.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          int seleccionfila = FacturaCredito.this.tbFactura.getSelectedRow();
          String cantidadEntrada = FacturaCredito.this.tbFactura.getValueAt(seleccionfila, 1).toString();
          if (cantidadEntrada.trim().length() != 0) {
            FacturaCredito.this.InsertarCantYCalSubTotal();
          }
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (FacturaCredito.this.tbFactura.getRowCount() > 0)
        {
          int seleccionfila = FacturaCredito.this.tbFactura.getSelectedRow();
          String cantidadEntrada = FacturaCredito.this.tbFactura.getValueAt(seleccionfila, 1).toString();
          if (cantidadEntrada.trim().length() != 0) {
            FacturaCredito.this.InsertarCantYCalSubTotal();
          }
        }
      }
    });
    this.tbFactura.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        FacturaCredito.this.btnQuitar.setEnabled(true);
      }
    });
    this.scrollPaneFact.setViewportView(this.tbFactura);
    this.tbFactura.setModel(modelofactura);
    this.tbFactura.getColumnModel().getColumn(0).setPreferredWidth(15);
    this.tbFactura.getColumnModel().getColumn(1).setPreferredWidth(21);
    this.tbFactura.getColumnModel().getColumn(2).setPreferredWidth(15);
    this.tbFactura.getColumnModel().getColumn(3).setPreferredWidth(512);
    this.tbFactura.getColumnModel().getColumn(4).setPreferredWidth(38);
    this.tbFactura.getColumnModel().getColumn(5).setPreferredWidth(43);
    
    this.scrollPaneProductos = new JScrollPane();
    this.scrollPaneProductos.setVerticalScrollBarPolicy(22);
    this.scrollPaneProductos.setBounds(464, 37, 709, 201);
    getContentPane().add(this.scrollPaneProductos);
    
    this.tbProductos = new JTable();
    this.tbProductos.setCursor(Cursor.getPredefinedCursor(12));
    this.tbProductos.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        FacturaCredito.this.btnAgregar.setEnabled(true);
        FacturaCredito.this.CargarDatosDelProducto();
      }
    });
    this.tbProductos.getTableHeader().setReorderingAllowed(false);
    this.tbProductos.setForeground(Color.WHITE);
    this.tbProductos.setBackground(new Color(51, 51, 51));
    this.tbProductos.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        FacturaCredito.this.btnAgregar.setEnabled(true);
        FacturaCredito.this.CargarDatosDelProducto();
      }
    });
    this.scrollPaneProductos.setViewportView(this.tbProductos);
    this.tbProductos.setModel(modeloproductos);
    this.tbProductos.getColumnModel().getColumn(0).setPreferredWidth(15);
    this.tbProductos.getColumnModel().getColumn(0).setMinWidth(5);
    this.tbProductos.getColumnModel().getColumn(1).setPreferredWidth(300);
    
    this.lblPrecioCredito = new JLabel("Precio a cr�dito:");
    this.lblPrecioCredito.setForeground(Color.BLACK);
    this.lblPrecioCredito.setFont(new Font("Tahoma", 1, 11));
    this.lblPrecioCredito.setBounds(596, 244, 105, 20);
    getContentPane().add(this.lblPrecioCredito);
    
    this.tfPrecioCredito = new JTextField();
    this.tfPrecioCredito.setEditable(false);
    this.tfPrecioCredito.setColumns(10);
    this.tfPrecioCredito.setBounds(691, 244, 86, 20);
    getContentPane().add(this.tfPrecioCredito);
    
    this.lblPrecioDeCosto = new JLabel("   Precio de costo:");
    this.lblPrecioDeCosto.setForeground(Color.BLACK);
    this.lblPrecioDeCosto.setFont(new Font("Tahoma", 1, 11));
    this.lblPrecioDeCosto.setBounds(1236, -112, 111, 14);
    getContentPane().add(this.lblPrecioDeCosto);
    
    this.tfPrecioCosto = new JTextField();
    this.tfPrecioCosto.setEditable(false);
    this.tfPrecioCosto.setColumns(10);
    this.tfPrecioCosto.setBounds(1346, -115, 81, 20);
    getContentPane().add(this.tfPrecioCosto);
    
    this.lblCantPaquete = new JLabel("Cant. por paquete:");
    this.lblCantPaquete.setForeground(Color.BLACK);
    this.lblCantPaquete.setFont(new Font("Tahoma", 1, 11));
    this.lblCantPaquete.setBounds(787, 244, 114, 20);
    getContentPane().add(this.lblCantPaquete);
    
    this.tfCantPaquete = new JTextField();
    this.tfCantPaquete.setEditable(false);
    this.tfCantPaquete.setColumns(10);
    this.tfCantPaquete.setBounds(895, 244, 86, 20);
    getContentPane().add(this.tfCantPaquete);
    
    this.lblStock = new JLabel("Stock:");
    this.lblStock.setForeground(Color.BLACK);
    this.lblStock.setFont(new Font("Tahoma", 1, 11));
    this.lblStock.setBounds(991, 244, 48, 17);
    getContentPane().add(this.lblStock);
    
    this.tfStock = new JTextField();
    this.tfStock.setEditable(false);
    this.tfStock.setColumns(10);
    this.tfStock.setBounds(1034, 244, 54, 20);
    getContentPane().add(this.tfStock);
    
    this.tfBuscadorProducto = new JTextField();
    this.tfBuscadorProducto.setToolTipText("Buscar producto");
    this.tfBuscadorProducto.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          FacturaCredito.this.btnBuscarProducto.doClick();
        }
      }
      
      public void keyTyped(KeyEvent evento)
      {
        char caracter = evento.getKeyChar();
        if (Character.isDigit(caracter))
        {
          getClass();
          FacturaCredito.this.chckbxCodigo.setSelected(true);
          FacturaCredito.this.chckbxDescripcion.setSelected(false);
        }
        if (!Character.isDigit(caracter))
        {
          getClass();
          FacturaCredito.this.chckbxDescripcion.setSelected(true);
          FacturaCredito.this.chckbxCodigo.setSelected(false);
        }
      }
    });
    this.tfBuscadorProducto.setColumns(10);
    this.tfBuscadorProducto.setBounds(464, 7, 226, 26);
    getContentPane().add(this.tfBuscadorProducto);
    
    this.btnBuscarProducto = new JButton("");
    this.btnBuscarProducto.setCursor(Cursor.getPredefinedCursor(12));
    this.btnBuscarProducto.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (FacturaCredito.this.chckbxCodigo.isSelected())
        {
          FacturaCredito.this.producto = DaoProductos.ConsutaParaModificar(FacturaCredito.this.tfBuscadorProducto.getText());
          if (FacturaCredito.this.producto != null)
          {
            FacturaCredito.this.BuscarProductoCodigo();
            FacturaCredito.this.AgregarProductoEnLaFacturaConLector();
          }
          else
          {
            JOptionPane.showMessageDialog(null, "No existe ning�n producto registrado con este c�digo en la base de datos", "Error", 0);
            FacturaCredito.this.tfBuscadorProducto.requestFocus();
            FacturaCredito.this.tfBuscadorProducto.selectAll();
          }
        }
        if (FacturaCredito.this.chckbxDescripcion.isSelected()) {
          FacturaCredito.this.BuscarProductoDescripcion();
        }
      }
    });
    this.btnBuscarProducto.setIcon(new ImageIcon(FacturaContado.class.getResource("/Iconos/Lupa.png")));
    this.btnBuscarProducto.setBounds(700, 7, 48, 25);
    getContentPane().add(this.btnBuscarProducto);
    
    this.tfTotal = new JTextField();
    this.tfTotal.setToolTipText("Total a pagar");
    this.tfTotal.setEditable(false);
    this.tfTotal.setFont(new Font("Microsoft Yi Baiti", 1, 45));
    this.tfTotal.setColumns(10);
    this.tfTotal.setBounds(922, 561, 238, 43);
    getContentPane().add(this.tfTotal);
    
    this.btnAgregar = new JButton("Agregar");
    this.btnAgregar.setCursor(Cursor.getPredefinedCursor(12));
    this.btnAgregar.setMnemonic('A');
    this.btnAgregar.setEnabled(false);
    this.btnAgregar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        int seleccionfila = FacturaCredito.this.tbProductos.getSelectedRow();
        try
        {
          if (seleccionfila < 0)
          {
            JOptionPane.showMessageDialog(null, "Seleccione un producto para agregar en la factura");
          }
          else
          {
            double dato = Double.parseDouble(FacturaCredito.this.tfStock.getText());
            if ((FacturaCredito.this.tfFactSinStock.getText().equals("No")) && (dato == 0.0D))
            {
              JOptionPane.showMessageDialog(null, "�ste producto no se puede facturar por falta de existencia del mismo.", "Existencia agotada", 2);
            }
            else if (FacturaCredito.this.tbProductos.getSelectedRow() < 0)
            {
              JOptionPane.showMessageDialog(null, "Selecciona un producto para agregar en la lista", "Aviso", 2);
            }
            else if (FacturaCredito.this.VerificarExistTabDet())
            {
              FacturaCredito.this.AgregarProductoEnLaFactura();
              FacturaCredito.this.CalcularTotalGral();
            }
          }
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
    this.btnAgregar.setToolTipText("Agregar producto en la lista");
    this.btnAgregar.setIcon(new ImageIcon(FacturaContado.class.getResource("/Iconos/agregar16.png")));
    this.btnAgregar.setBounds(464, 244, 122, 28);
    getContentPane().add(this.btnAgregar);
    
    this.btnFacturar = new JButton("GENERAR DEUDA");
    this.btnFacturar.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if ((e.getKeyCode() == 10) && 
          (FacturaCredito.this.ValidarDatosDelCliente())) {
          FacturaCredito.this.FacturacionGeneral();
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
        if (FacturaCredito.this.ValidarDatosDelCliente()) {
          FacturaCredito.this.FacturacionGeneral();
        }
      }
    });
    this.btnFacturar.setToolTipText("Registrar la venta y generar la deuda");
    this.btnFacturar.setFont(new Font("Tahoma", 1, 15));
    this.btnFacturar.setIcon(new ImageIcon(FacturaCredito.class.getResource("/Iconos/IconDeuda.png")));
    this.btnFacturar.setBounds(10, 546, 225, 58);
    getContentPane().add(this.btnFacturar);
    int codigo = DaoFacturaCredito.IncrementarCodigoCabecera() + 1;
    this.tfNFactura = new JTextField();
    this.tfNFactura.setEnabled(false);
    this.tfNFactura.setColumns(10);
    this.tfNFactura.setBounds(109, 77, 95, 20);
    getContentPane().add(this.tfNFactura);
    this.tfNFactura.setText(String.valueOf(codigo));
    
    tfCedulaRUC = new JTextField();
    tfCedulaRUC.setEditable(false);
    tfCedulaRUC.setColumns(10);
    tfCedulaRUC.setBounds(109, 159, 95, 20);
    getContentPane().add(tfCedulaRUC);
    
    tfNombreCliente = new JTextField();
    tfNombreCliente.setEditable(false);
    tfNombreCliente.setColumns(10);
    tfNombreCliente.setBounds(183, 190, 261, 20);
    getContentPane().add(tfNombreCliente);
    
    this.scrollPane = new JScrollPane();
    this.scrollPane.setBounds(109, 215, 335, 38);
    getContentPane().add(this.scrollPane);
    
    tfDireccion = new JTextArea();
    this.scrollPane.setViewportView(tfDireccion);
    tfDireccion.setEditable(false);
    
    this.lblNDeFactura = new JLabel("N� de factura:");
    this.lblNDeFactura.setForeground(Color.BLACK);
    this.lblNDeFactura.setFont(new Font("Tahoma", 1, 11));
    this.lblNDeFactura.setBounds(20, 80, 86, 14);
    getContentPane().add(this.lblNDeFactura);
    
    this.lblRuccin = new JLabel("RUC/C.I.N�:");
    this.lblRuccin.setForeground(Color.BLACK);
    this.lblRuccin.setFont(new Font("Tahoma", 1, 11));
    this.lblRuccin.setBounds(36, 159, 76, 18);
    getContentPane().add(this.lblRuccin);
    
    this.lblNombre = new JLabel("   Cliente:");
    this.lblNombre.setForeground(Color.BLACK);
    this.lblNombre.setFont(new Font("Tahoma", 1, 11));
    this.lblNombre.setBounds(49, 190, 73, 20);
    getContentPane().add(this.lblNombre);
    
    this.lblDireccin = new JLabel("  Direcci�n:");
    this.lblDireccin.setForeground(Color.BLACK);
    this.lblDireccin.setFont(new Font("Tahoma", 1, 11));
    this.lblDireccin.setBounds(36, 221, 76, 14);
    getContentPane().add(this.lblDireccin);
    
    this.lblTelfono = new JLabel("Tel�fono:");
    this.lblTelfono.setForeground(Color.BLACK);
    this.lblTelfono.setFont(new Font("Tahoma", 1, 11));
    this.lblTelfono.setBounds(214, 159, 73, 20);
    getContentPane().add(this.lblTelfono);
    
    tfTelefono = new JTextField();
    tfTelefono.setEditable(false);
    tfTelefono.setColumns(10);
    tfTelefono.setBounds(281, 159, 95, 20);
    getContentPane().add(tfTelefono);
    
    this.btnInsertarCliente = new JButton("");
    this.btnInsertarCliente.setCursor(Cursor.getPredefinedCursor(12));
    this.btnInsertarCliente.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        ClientesDatosSetFac cliente = new ClientesDatosSetFac();
        ClientesDatosSetFac.lblFormularioCliente.setText("Credito");
        cliente.setLocationRelativeTo(null);
        cliente.setModal(true);
        cliente.setVisible(true);
      }
    });
    this.btnInsertarCliente.setIcon(new ImageIcon(FacturaContado.class.getResource("/Iconos/agregar16.png")));
    this.btnInsertarCliente.setBounds(386, 159, 48, 20);
    getContentPane().add(this.btnInsertarCliente);
    
    this.btnQuitar = new JButton("Quitar");
    this.btnQuitar.setCursor(Cursor.getPredefinedCursor(12));
    this.btnQuitar.setMnemonic('Q');
    this.btnQuitar.setEnabled(false);
    this.btnQuitar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FacturaCredito.this.QuitarProducto();
      }
    });
    this.btnQuitar.setToolTipText("Quitar producto de la lista");
    this.btnQuitar.setIcon(new ImageIcon(FacturaContado.class.getResource("/Iconos/del.png")));
    this.btnQuitar.setBounds(243, 546, 191, 28);
    getContentPane().add(this.btnQuitar);
    
    this.lblTotal = new JLabel("TOTAL:");
    this.lblTotal.setForeground(Color.BLACK);
    this.lblTotal.setFont(new Font("Tahoma", 1, 15));
    this.lblTotal.setBounds(839, 561, 73, 43);
    getContentPane().add(this.lblTotal);
    
    this.tfUnidadMedida = new JTextField();
    this.tfUnidadMedida.setVisible(false);
    this.tfUnidadMedida.setEditable(false);
    this.tfUnidadMedida.setBounds(1132, 244, 41, 20);
    getContentPane().add(this.tfUnidadMedida);
    this.tfUnidadMedida.setColumns(10);
    
    this.chckbxCodigo = new JCheckBox("C�digo");
    this.chckbxCodigo.setForeground(Color.BLACK);
    this.chckbxCodigo.setFont(new Font("Tahoma", 1, 11));
    this.chckbxCodigo.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FacturaCredito.this.chckbxDescripcion.setSelected(false);
      }
    });
    this.chckbxCodigo.setBounds(754, 7, 73, 23);
    getContentPane().add(this.chckbxCodigo);
    
    this.chckbxDescripcion = new JCheckBox("Descripci�n");
    this.chckbxDescripcion.setForeground(Color.BLACK);
    this.chckbxDescripcion.setFont(new Font("Tahoma", 1, 11));
    this.chckbxDescripcion.setSelected(true);
    this.chckbxDescripcion.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FacturaCredito.this.chckbxCodigo.setSelected(false);
      }
    });
    this.chckbxDescripcion.setBounds(824, 7, 103, 23);
    getContentPane().add(this.chckbxDescripcion);
    
    this.btnLimpiarLista = new JButton("Limpiar lista");
    this.btnLimpiarLista.setCursor(Cursor.getPredefinedCursor(12));
    this.btnLimpiarLista.setMnemonic('L');
    this.btnLimpiarLista.setEnabled(false);
    this.btnLimpiarLista.setIcon(new ImageIcon(FacturaContado.class.getResource("/Iconos/Papelera1.png")));
    this.btnLimpiarLista.setToolTipText("Limpia la lista");
    this.btnLimpiarLista.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FacturaCredito.this.LimpiarFactura();
      }
    });
    this.btnLimpiarLista.setBounds(243, 577, 191, 28);
    getContentPane().add(this.btnLimpiarLista);
    
    this.tfColor = new JTextField();
    this.tfColor.setVisible(false);
    this.tfColor.setEditable(false);
    this.tfColor.setBounds(688, 567, 86, 20);
    contentPane.add(this.tfColor);
    this.tfColor.setColumns(10);
    
    this.tfFactSinStock = new JTextField();
    this.tfFactSinStock.setVisible(false);
    this.tfFactSinStock.setBounds(1088, 244, 41, 20);
    contentPane.add(this.tfFactSinStock);
    this.tfFactSinStock.setColumns(10);
    
    this.lblDatosDelCliente = new JLabel("Datos del cliente");
    this.lblDatosDelCliente.setForeground(Color.BLACK);
    this.lblDatosDelCliente.setFont(new Font("Tahoma", 1, 11));
    this.lblDatosDelCliente.setBounds(170, 116, 129, 26);
    contentPane.add(this.lblDatosDelCliente);
    
    JTabbedPane tabbedPane_4 = new JTabbedPane(1);
    tabbedPane_4.setBounds(10, 116, 448, 26);
    contentPane.add(tabbedPane_4);
    
    tfCodCliente = new JTextField();
    tfCodCliente.setEditable(false);
    tfCodCliente.setBounds(109, 190, 64, 20);
    contentPane.add(tfCodCliente);
    tfCodCliente.setColumns(10);
    
    this.lblFacturacion = new JLabel("Venta a cr�dito");
    this.lblFacturacion.setForeground(Color.BLACK);
    this.lblFacturacion.setFont(new Font("Book Antiqua", 1, 44));
    this.lblFacturacion.setBounds(65, 7, 348, 58);
    contentPane.add(this.lblFacturacion);
    
    JTabbedPane tabbedPane_2 = new JTabbedPane(1);
    tabbedPane_2.setBounds(10, 7, 448, 58);
    contentPane.add(tabbedPane_2);
    
    JTabbedPane tabbedPane = new JTabbedPane(1);
    tabbedPane.setBounds(10, 139, 448, 133);
    contentPane.add(tabbedPane);
    
    this.lblVendedor = new JLabel("Vendedor:");
    this.lblVendedor.setForeground(Color.WHITE);
    this.lblVendedor.setFont(new Font("Tahoma", 1, 11));
    this.lblVendedor.setBounds(214, 80, 86, 14);
    contentPane.add(this.lblVendedor);
    
    this.comboBoxVendedor = new JComboBox();
    this.comboBoxVendedor.setBounds(281, 77, 163, 20);
    contentPane.add(this.comboBoxVendedor);
    
    JTabbedPane tabbedPane_3 = new JTabbedPane(1);
    tabbedPane_3.setBounds(10, 69, 448, 38);
    contentPane.add(tabbedPane_3);
    
    this.btnRecuperar = new JButton("Recuperar");
    this.btnRecuperar.setIcon(new ImageIcon(FacturaCredito.class.getResource("/Iconos/icon_recover.png")));
    this.btnRecuperar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FacturaCredito.this.RecuperarPresupuesto();
      }
    });
    this.btnRecuperar.setToolTipText("Recuperar lista de presupuesto por N� de presupuesto");
    this.btnRecuperar.setMnemonic('Q');
    this.btnRecuperar.setBounds(438, 546, 169, 58);
    contentPane.add(this.btnRecuperar);
    
    ColorBlancoFacturaLabels();
    cargarComboBoxVendedores();
    LimpiarTabla();
  }
  
  private void LimpiarTabla()
  {
    int filaTotal = this.tbProductos.getRowCount();
    int filaTotal2 = this.tbFactura.getRowCount();
    while (filaTotal >= 1)
    {
      modeloproductos.removeRow(0);
      filaTotal--;
    }
    while (filaTotal2 >= 1)
    {
      modelofactura.removeRow(0);
      filaTotal--;
    }
  }
  
  private void CapturarColor()
  {
    String codigocolor = "1";
    Colores color = Colores.ConsutarColorFactura(codigocolor);
    this.tfColor.setText(color.getColor());
  }
  
  private void DetectarColor()
  {
    if (this.tfColor.getText().equals("Amarillo")) {
      ColorAmarilloFacturaLabels();
    }
    if (this.tfColor.getText().equals("Azul")) {
      ColorAzulFacturaLabels();
    }
    if (this.tfColor.getText().equals("Cyan")) {
      ColorCyanFacturaLabels();
    }
    if (this.tfColor.getText().equals("Gris")) {
      ColorGrisFacturaLabels();
    }
    if (this.tfColor.getText().equals("Naranja")) {
      ColorNaranjaFacturaLabels();
    }
    if (this.tfColor.getText().equals("Negro")) {
      ColorNegroFacturaLabels();
    }
    if (this.tfColor.getText().equals("P�rpura")) {
      ColorPurpuraFacturaLabels();
    }
    if (this.tfColor.getText().equals("Rojo")) {
      ColorRojoFacturaLabels();
    }
    if (this.tfColor.getText().equals("Marr�n")) {
      ColorMarronFacturaLabels();
    }
    if (this.tfColor.getText().equals("Verde fl�or")) {
      ColorVerdeFluorFacturaLabels();
    }
    if (this.tfColor.getText().equals("Verde")) {
      ColorVerdeFacturaLabels();
    }
    if (this.tfColor.getText().equals("Blanco")) {
      ColorBlancoFacturaLabels();
    }
  }
  
  private void ColorRojoFacturaLabels()
  {
    this.lblNDeFactura.setForeground(Color.RED);
    this.lblRuccin.setForeground(Color.RED);
    this.lblNombre.setForeground(Color.RED);
    this.lblTelfono.setForeground(Color.RED);
    this.lblDireccin.setForeground(Color.RED);
    this.lblPrecioCredito.setForeground(Color.RED);
    this.lblStock.setForeground(Color.RED);
    this.lblCantPaquete.setForeground(Color.RED);
    this.lblPrecioDeCosto.setForeground(Color.RED);
    this.lblFacturacion.setForeground(Color.RED);
    this.lblTotal.setForeground(Color.RED);
    this.lblDatosDelCliente.setForeground(Color.RED);
  }
  
  private void ColorBlancoFacturaLabels()
  {
    this.lblNDeFactura.setForeground(Color.WHITE);
    this.lblRuccin.setForeground(Color.WHITE);
    this.lblNombre.setForeground(Color.WHITE);
    this.lblTelfono.setForeground(Color.WHITE);
    this.lblDireccin.setForeground(Color.WHITE);
    this.lblPrecioCredito.setForeground(Color.WHITE);
    this.lblStock.setForeground(Color.WHITE);
    this.lblCantPaquete.setForeground(Color.WHITE);
    this.lblPrecioDeCosto.setForeground(Color.WHITE);
    this.lblFacturacion.setForeground(Color.WHITE);
    this.lblTotal.setForeground(Color.WHITE);
    this.lblDatosDelCliente.setForeground(Color.WHITE);
  }
  
  private void ColorGrisFacturaLabels()
  {
    this.lblNDeFactura.setForeground(Color.GRAY);
    this.lblRuccin.setForeground(Color.GRAY);
    this.lblNombre.setForeground(Color.GRAY);
    this.lblTelfono.setForeground(Color.GRAY);
    this.lblDireccin.setForeground(Color.GRAY);
    this.lblPrecioCredito.setForeground(Color.GRAY);
    this.lblStock.setForeground(Color.GRAY);
    this.lblCantPaquete.setForeground(Color.GRAY);
    this.lblPrecioDeCosto.setForeground(Color.GRAY);
    
    this.lblFacturacion.setForeground(Color.GRAY);
    this.lblTotal.setForeground(Color.GRAY);
    this.lblDatosDelCliente.setForeground(Color.GRAY);
  }
  
  private void ColorAmarilloFacturaLabels()
  {
    this.lblNDeFactura.setForeground(Color.YELLOW);
    this.lblRuccin.setForeground(Color.YELLOW);
    this.lblNombre.setForeground(Color.YELLOW);
    this.lblTelfono.setForeground(Color.YELLOW);
    this.lblDireccin.setForeground(Color.YELLOW);
    this.lblPrecioCredito.setForeground(Color.YELLOW);
    this.lblStock.setForeground(Color.YELLOW);
    this.lblCantPaquete.setForeground(Color.YELLOW);
    this.lblPrecioDeCosto.setForeground(Color.YELLOW);
    this.lblFacturacion.setForeground(Color.YELLOW);
    this.lblTotal.setForeground(Color.YELLOW);
    this.lblDatosDelCliente.setForeground(Color.YELLOW);
  }
  
  private void ColorVerdeFacturaLabels()
  {
    this.lblNDeFactura.setForeground(new Color(0, 128, 0));
    this.lblRuccin.setForeground(new Color(0, 128, 0));
    this.lblNombre.setForeground(new Color(0, 128, 0));
    this.lblTelfono.setForeground(new Color(0, 128, 0));
    this.lblDireccin.setForeground(new Color(0, 128, 0));
    this.lblPrecioCredito.setForeground(new Color(0, 128, 0));
    this.lblStock.setForeground(new Color(0, 128, 0));
    this.lblCantPaquete.setForeground(new Color(0, 128, 0));
    this.lblPrecioDeCosto.setForeground(new Color(0, 128, 0));
    this.lblFacturacion.setForeground(new Color(0, 128, 0));
    this.lblTotal.setForeground(new Color(0, 128, 0));
    this.lblDatosDelCliente.setForeground(new Color(0, 128, 0));
  }
  
  private void ColorAzulFacturaLabels()
  {
    this.lblNDeFactura.setForeground(new Color(0, 0, 205));
    this.lblRuccin.setForeground(new Color(0, 0, 205));
    this.lblNombre.setForeground(new Color(0, 0, 205));
    this.lblTelfono.setForeground(new Color(0, 0, 205));
    this.lblDireccin.setForeground(new Color(0, 0, 205));
    this.lblPrecioCredito.setForeground(new Color(0, 0, 205));
    this.lblStock.setForeground(new Color(0, 0, 205));
    this.lblCantPaquete.setForeground(new Color(0, 0, 205));
    this.lblPrecioDeCosto.setForeground(new Color(0, 0, 205));
    this.lblFacturacion.setForeground(new Color(0, 0, 205));
    this.lblTotal.setForeground(new Color(0, 0, 205));
    this.lblDatosDelCliente.setForeground(new Color(0, 0, 205));
  }
  
  private void ColorMarronFacturaLabels()
  {
    this.lblNDeFactura.setForeground(new Color(139, 69, 19));
    this.lblRuccin.setForeground(new Color(139, 69, 19));
    this.lblNombre.setForeground(new Color(139, 69, 19));
    this.lblTelfono.setForeground(new Color(139, 69, 19));
    this.lblDireccin.setForeground(new Color(139, 69, 19));
    this.lblPrecioCredito.setForeground(new Color(139, 69, 19));
    this.lblStock.setForeground(new Color(139, 69, 19));
    this.lblCantPaquete.setForeground(new Color(139, 69, 19));
    this.lblPrecioDeCosto.setForeground(new Color(139, 69, 19));
    this.lblFacturacion.setForeground(new Color(139, 69, 19));
    this.lblTotal.setForeground(new Color(139, 69, 19));
    this.lblDatosDelCliente.setForeground(new Color(139, 69, 19));
  }
  
  private void ColorPurpuraFacturaLabels()
  {
    this.lblNDeFactura.setForeground(new Color(148, 0, 211));
    this.lblRuccin.setForeground(new Color(148, 0, 211));
    this.lblNombre.setForeground(new Color(148, 0, 211));
    this.lblTelfono.setForeground(new Color(148, 0, 211));
    this.lblDireccin.setForeground(new Color(148, 0, 211));
    this.lblPrecioCredito.setForeground(new Color(148, 0, 211));
    this.lblStock.setForeground(new Color(148, 0, 211));
    this.lblCantPaquete.setForeground(new Color(148, 0, 211));
    this.lblPrecioDeCosto.setForeground(new Color(148, 0, 211));
    this.lblFacturacion.setForeground(new Color(148, 0, 211));
    this.lblTotal.setForeground(new Color(148, 0, 211));
    this.lblDatosDelCliente.setForeground(new Color(148, 0, 211));
  }
  
  private void ColorCyanFacturaLabels()
  {
    this.lblNDeFactura.setForeground(Color.CYAN);
    this.lblRuccin.setForeground(Color.CYAN);
    this.lblNombre.setForeground(Color.CYAN);
    this.lblTelfono.setForeground(Color.CYAN);
    this.lblDireccin.setForeground(Color.CYAN);
    this.lblPrecioCredito.setForeground(Color.CYAN);
    this.lblStock.setForeground(Color.CYAN);
    this.lblCantPaquete.setForeground(Color.CYAN);
    this.lblPrecioDeCosto.setForeground(Color.CYAN);
    this.lblFacturacion.setForeground(Color.CYAN);
    this.lblTotal.setForeground(Color.CYAN);
    this.lblDatosDelCliente.setForeground(Color.CYAN);
  }
  
  private void ColorNaranjaFacturaLabels()
  {
    this.lblNDeFactura.setForeground(Color.ORANGE);
    this.lblRuccin.setForeground(Color.ORANGE);
    this.lblNombre.setForeground(Color.ORANGE);
    this.lblTelfono.setForeground(Color.ORANGE);
    this.lblDireccin.setForeground(Color.ORANGE);
    this.lblPrecioCredito.setForeground(Color.ORANGE);
    this.lblStock.setForeground(Color.ORANGE);
    this.lblCantPaquete.setForeground(Color.ORANGE);
    this.lblPrecioDeCosto.setForeground(Color.ORANGE);
    this.lblFacturacion.setForeground(Color.ORANGE);
    this.lblTotal.setForeground(Color.ORANGE);
    this.lblDatosDelCliente.setForeground(Color.ORANGE);
  }
  
  private void ColorVerdeFluorFacturaLabels()
  {
    this.lblNDeFactura.setForeground(Color.GREEN);
    this.lblRuccin.setForeground(Color.GREEN);
    this.lblNombre.setForeground(Color.GREEN);
    this.lblTelfono.setForeground(Color.GREEN);
    this.lblDireccin.setForeground(Color.GREEN);
    this.lblPrecioCredito.setForeground(Color.GREEN);
    this.lblStock.setForeground(Color.GREEN);
    this.lblCantPaquete.setForeground(Color.GREEN);
    this.lblPrecioDeCosto.setForeground(Color.GREEN);
    this.lblFacturacion.setForeground(Color.GREEN);
    this.lblTotal.setForeground(Color.GREEN);
    this.lblDatosDelCliente.setForeground(Color.GREEN);
  }
  
  private void ColorNegroFacturaLabels()
  {
    this.lblNDeFactura.setForeground(Color.BLACK);
    this.lblRuccin.setForeground(Color.BLACK);
    this.lblNombre.setForeground(Color.BLACK);
    this.lblTelfono.setForeground(Color.BLACK);
    this.lblDireccin.setForeground(Color.BLACK);
    this.lblPrecioCredito.setForeground(Color.BLACK);
    this.lblStock.setForeground(Color.BLACK);
    this.lblCantPaquete.setForeground(Color.BLACK);
    this.lblPrecioDeCosto.setForeground(Color.BLACK);
    this.lblFacturacion.setForeground(Color.BLACK);
    this.lblTotal.setForeground(Color.BLACK);
    this.lblDatosDelCliente.setForeground(Color.BLACK);
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
    int seleccion = this.tbProductos.getSelectedRow();
    String codigo = this.tbProductos.getValueAt(seleccion, 0).toString();
    ModeloProductos productos = DaoProductos.ConsutaParaModificar(codigo);
    
    this.tfPrecioCosto.setText(String.valueOf(productos.getPrecioCosto()));
    this.tfPrecioCredito.setText(String.valueOf(productos.getPrecioCredito()));
    this.tfStock.setText(String.valueOf(productos.getStock()));
    this.tfCantPaquete.setText(String.valueOf(productos.getCantPaquete()));
    this.tfUnidadMedida.setText(String.valueOf(productos.getUnidadDeMedida()));
    this.tfFactSinStock.setText(String.valueOf(productos.getFacturar()));
  }
  
  private void CargarDatosDelProductoConLector()
  {
    String codigo = this.tfBuscadorProducto.getText();
    ModeloProductos productos = DaoProductos.ConsutaParaModificar(codigo);
    
    this.tfPrecioCosto.setText(String.valueOf(productos.getPrecioCosto()));
    this.tfPrecioCredito.setText(String.valueOf(productos.getPrecioCredito()));
    this.tfStock.setText(String.valueOf(productos.getStock()));
    this.tfCantPaquete.setText(String.valueOf(productos.getCantPaquete()));
    this.tfUnidadMedida.setText(String.valueOf(productos.getUnidadDeMedida()));
    this.tfFactSinStock.setText(String.valueOf(productos.getFacturar()));
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
      this.btnLimpiarLista.setEnabled(true);
      this.btnQuitar.setEnabled(true);
    }
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
  
  private void InsertarCantYCalSubTotal()
  {
    try
    {
      int seleccionfila = this.tbFactura.getSelectedRow();
      String cantidadEntrada = this.tbFactura.getValueAt(seleccionfila, 0).toString();
      String costoProducto = this.tbFactura.getValueAt(seleccionfila, 4).toString();
      String unidadMedida = this.tbFactura.getValueAt(seleccionfila, 1).toString();
      
      double subtotal = 0.0D;
      double cantidad = Double.parseDouble(cantidadEntrada);
      double costo = 0.0D;
      
      costo = Double.parseDouble(costoProducto);
      
      subtotal = cantidad * costo;
      int seleccion = this.tbFactura.getSelectedRow();
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
      }
    }
    catch (Exception localException) {}
  }
  
  private void AgregarProductoEnLaFactura()
  {
    int seleccionfila = this.tbProductos.getSelectedRow();
    try
    {
      if (seleccionfila < 0)
      {
        JOptionPane.showMessageDialog(null, "Seleccione un producto para agregar en la factura");
      }
      else
      {
        String codigo = this.tbProductos.getValueAt(seleccionfila, 0).toString();
        String descripcion = this.tbProductos.getValueAt(seleccionfila, 1).toString();
        String precio = this.tfPrecioCredito.getText();
        String unidadMedida = this.tfUnidadMedida.getText();
        String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio };
        
        modelofactura.addRow(filaseleccionada);
        this.btnAgregar.setEnabled(false);
        this.btnLimpiarLista.setEnabled(true);
        this.btnLimpiarLista.setIcon(new ImageIcon(FacturaContado.class.getResource("/Iconos/Papelera1.png")));
        this.btnFacturar.setEnabled(true);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Try catch:" + e.getMessage());
    }
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
        String precio = this.tfPrecioCredito.getText();
        String codigo = this.tbProductos.getValueAt(0, 0).toString();
        String descripcion = this.tbProductos.getValueAt(0, 1).toString();
        String unidadMedida = this.tfUnidadMedida.getText();
        String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio };
        
        modelofactura.addRow(filaseleccionada);
        
        this.btnLimpiarLista.setEnabled(true);
        this.btnLimpiarLista.setIcon(new ImageIcon(FacturaContado.class.getResource("/Iconos/Papelera1.png")));
        CalcularTotalGral();
        this.tfBuscadorProducto.setText("");
        this.tfBuscadorProducto.requestFocus();
        this.btnFacturar.setEnabled(true);
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
        String precio = this.tfPrecioCredito.getText();
        String codigo = this.tbProductos.getValueAt(0, 0).toString();
        String descripcion = this.tbProductos.getValueAt(0, 1).toString();
        String unidadMedida = this.tfUnidadMedida.getText();
        String[] filaseleccionada = { "1", unidadMedida, codigo, descripcion, precio, precio };
        
        modelofactura.addRow(filaseleccionada);
        
        this.btnLimpiarLista.setEnabled(true);
        this.btnLimpiarLista.setIcon(new ImageIcon(FacturaContado.class.getResource("/Iconos/Papelera1.png")));
        CalcularTotalGral();
        this.tfBuscadorProducto.setText("");
        this.tfBuscadorProducto.requestFocus();
        this.btnFacturar.setEnabled(true);
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
  
  private boolean VerificarExistTabDet()
  {
    int seleccion = this.tbProductos.getSelectedRow();
    int filaTotal = this.tbFactura.getRowCount();
    String codProd = String.valueOf(this.tbProductos.getValueAt(seleccion, 0));
    while (filaTotal > 0)
    {
      filaTotal--;
      String codProdTablaFactura = String.valueOf(this.tbFactura.getValueAt(filaTotal, 2));
      if (codProd.equals(codProdTablaFactura))
      {
        this.tbFactura.changeSelection(filaTotal, 0, false, false);
        JOptionPane.showMessageDialog(null, "�ste producto ya se encuentra en la lista de la factura.");
        return false;
      }
    }
    return true;
  }
  
  public void CalcularTotalGral()
  {
    double sumatoria = 0.0D;
    double total = 0.0D;
    int totalRow = this.tbFactura.getRowCount();
    totalRow--;
    for (int i = 0; i <= totalRow; i++)
    {
      if (this.tbFactura.getValueAt(i, 5).equals("")) {
        sumatoria = 0.0D;
      } else {
        sumatoria = Double.parseDouble(String.valueOf(this.tbFactura.getValueAt(i, 5)));
      }
      total += sumatoria;
    }
    if (total > 9999999.0D)
    {
      int totalInt = (int)total;
      String totalObtenido = String.valueOf(totalInt);
      
      this.tfTotal.setText(totalObtenido);
    }
    else
    {
      String totalObtenido = String.valueOf(total);
      
      this.tfTotal.setText(totalObtenido);
    }
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
    CalcularTotalGral();
  }
  
  public void LimpiarFacturaSinPreguntar()
  {
    try
    {
      int filaTotal = this.tbFactura.getRowCount();
      while (filaTotal >= 1)
      {
        modelofactura.removeRow(0);
        filaTotal--;
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public void LimpiarFactura()
  {
    try
    {
      int yes = JOptionPane.showConfirmDialog(this, "�Est� seguro que desea limpiar la lista?", "Aviso", 0);
      if (yes == 0)
      {
        int filaTotal = this.tbFactura.getRowCount();
        while (filaTotal >= 1)
        {
          modelofactura.removeRow(0);
          this.btnLimpiarLista.setEnabled(false);
          filaTotal--;
          this.btnQuitar.setEnabled(false);
          LimpiarTextFieldsLuegoDeFacturar();
        }
      }
      else
      {
        this.btnLimpiarLista.setEnabled(true);
      }
      this.btnFacturar.setEnabled(false);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  private void QuitarProducto()
  {
    int filaseleccionada = this.tbFactura.getSelectedRow();
    if (filaseleccionada < 0)
    {
      JOptionPane.showMessageDialog(null, "Seleccione un producto para quitar de la lista del detalle.");
    }
    else
    {
      String dato = this.tbFactura.getValueAt(filaseleccionada, 3).toString();
      int yes = JOptionPane.showConfirmDialog(this, "�Est� seguro que desea quitar el producto " + 
        dato + " de la lista de la factura?", "Aviso", 0);
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
          int fila = this.tbFactura.getRowCount();
          if (fila == 0)
          {
            this.btnFacturar.setEnabled(false);
            this.btnLimpiarLista.setEnabled(false);
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
    ModeloFacturaCredito cabecera = new ModeloFacturaCredito();
    cabecera.setCodigoCabecera(Integer.parseInt(this.tfNFactura.getText()));
    cabecera.setCodigoCliente(Integer.parseInt(tfCodCliente.getText()));
    cabecera.setFecha(fecha());
    cabecera.setHora(hora());
    cabecera.setTotal(Double.parseDouble(this.tfTotal.getText()));
    cabecera.setSaldo(Double.parseDouble(this.tfTotal.getText()));
    cabecera.setEstado("EN DEUDA");
    cabecera.setEntrega(0.0D);
    cabecera.setFechaUEntrega(fecha());
    cabecera.setHoraEntrega(hora());
    cabecera.getVendedor().setCodigo(Integer.parseInt(this.comboBoxVendedor.getSelectedItem().toString().split("-")[0]));
    DaoFacturaCredito.GuardarFacturaCabecera(cabecera);
  }
  
  private void GuardarDatosFacturaDetalleBD()
  {
    int filaTotal = this.tbFactura.getRowCount();
    int codigo = DaoFacturaCredito.IncrementarCodigoDetalle() + 1;
    double precioUnit = Double.parseDouble(String.valueOf(this.tbFactura.getValueAt(0, 4)));
    while (filaTotal >= 1)
    {
      ModeloFacturaCredito detalle = new ModeloFacturaCredito();
      detalle.setCodigoDetalle(codigo);
      detalle.setCodigoProducto(String.valueOf(this.tbFactura.getValueAt(0, 2)));
      detalle.setPrecioUnitario(precioUnit);
      String descripcionprod = this.tbFactura.getValueAt(0, 3).toString();
      String subtotal = String.valueOf(this.tbFactura.getValueAt(0, 5));
      if (subtotal.equals("null"))
      {
        JOptionPane.showMessageDialog(null, "El producto " + descripcionprod + " no se puede facturar\nha habido un error en la cantidad o el subtotal.", "Error", 2);
        JOptionPane.showMessageDialog(null, "Verifique los detalles en 'Cobranzas' si desea saber\nqu� productos se han facturado.");
        LimpiarFacturaSinPreguntar();
        dispose();
      }
      detalle.setSubtotal(Double.parseDouble(String.valueOf(this.tbFactura.getValueAt(0, 5))));
      detalle.setCantidad(Double.parseDouble(String.valueOf(this.tbFactura.getValueAt(0, 0))));
      detalle.setCodigoCabecera(Integer.parseInt(this.tfNFactura.getText()));
      
      double stockactual = 0.0D;
      String codigoproducto = this.tbFactura.getValueAt(0, 2).toString();
      ModeloProductos productos = DaoProductos.ConsutaParaModificar(String.valueOf(codigoproducto));
      
      stockactual = productos.getStock();
      String descripcion = productos.getDescripcion();
      double cantidadLLevada = Double.parseDouble(String.valueOf(this.tbFactura.getValueAt(0, 0)));
      double updatestock = stockactual - cantidadLLevada;
      if ((productos.getFacturar().equals("No")) && (updatestock < 0.0D))
      {
        JOptionPane.showMessageDialog(null, "El producto " + descripcion + " no se podr� facturar por stock insuficiente.");
        JOptionPane.showMessageDialog(null, "Verifique el stock del producto " + descripcion + ", solo quedan " + stockactual + " en stock");
        
        ModeloFacturaCredito cobranza = DaoCobranzas.ConsutarMasDetalles(Integer.parseInt(this.tfNFactura.getText()));
        double totalguardado = cobranza.getTotal();
        double subtotalarestar = Double.parseDouble(String.valueOf(this.tbFactura.getValueAt(0, 5)));
        double totalaguardar = totalguardado - subtotalarestar;
        
        ModeloFacturaCredito x = new ModeloFacturaCredito();
        x.setCodigoCabecera(Integer.parseInt(this.tfNFactura.getText()));
        x.setSaldo(totalaguardar);
        x.setTotal(totalaguardar);
        DaoCobranzas.ActTotCabEnCasoDeStockInsuf(x);
      }
      else
      {
        if ((productos.getFacturar().equals("No")) && (updatestock == 0.0D)) {
          JOptionPane.showMessageDialog(null, "El producto " + descripcion + " ha llegado en stock cero.");
        }
        DaoFacturaCredito.GuardarFacturaDetalle(detalle);
        ModeloProductos p = new ModeloProductos();
        
        p.setCodigo(String.valueOf(this.tbFactura.getValueAt(0, 2)));
        p.setStock(updatestock);
        
        DaoProductos.ActualizarStock(p);
      }
      if (updatestock >= 0.0D)
      {
        this.info = new ModeloInformaciones();
        try
        {
          int codigoInfo = DaoInformaciones.IncrementarCodigo() + 1;
          this.info = DaoInformaciones.Existencia(this.tbFactura.getValueAt(0, 2).toString(), fecha(), "CREDITO");
          if (this.info != null)
          {
            double precioUnitario = Double.parseDouble(String.valueOf(this.tbFactura.getValueAt(0, 4)));
            double cantvendidabd = 0.0D;
            double update = 0.0D;
            double subtotalbd = 0.0D;
            double calsubtotal = 0.0D;
            double subtotalact = 0.0D;
            
            double utilidad = 0.0D;
            double precioCosto = productos.getPrecioCosto();
            
            ModeloInformaciones i = DaoInformaciones.ConsultaForUpdate(codigoproducto, fecha(), "CREDITO");
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
            DaoInformaciones.ActualizarCantidadVendida(info, fecha(), "CREDITO");
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
            info.setPrecioUnitario(Double.parseDouble(String.valueOf(this.tbFactura.getValueAt(0, 4).toString())));
            info.setCondicion("CREDITO");
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
      }
      this.btnLimpiarLista.setEnabled(false);
      modelofactura.removeRow(0);
      filaTotal--;
      codigo++;
    }
  }
  
  private boolean ValidarFacturarSinStock()
  {
    int seleccion = this.tbFactura.getSelectedRow();
    String codigoproducto = this.tbFactura.getValueAt(0, 2).toString();
    String cantidad = this.tbFactura.getValueAt(0, 0).toString();
    ModeloProductos productos = DaoProductos.ConsutaParaModificar(String.valueOf(codigoproducto));
    double stockactual = productos.getStock();
    double cant = Double.parseDouble(cantidad);
    String descripcion = productos.getDescripcion();
    if ((productos.getFacturar().equals("No")) && (cant > stockactual))
    {
      JOptionPane.showMessageDialog(null, "Este producto no se podr� facturar por stock insuficiente.");
      JOptionPane.showMessageDialog(null, "El producto " + descripcion + " solo quedan " + stockactual + " en stock.");
      modelofactura.removeRow(seleccion);
      if (this.tbFactura.getRowCount() == 0)
      {
        this.btnFacturar.setEnabled(false);
        this.btnLimpiarLista.setEnabled(false);
        this.btnQuitar.setEnabled(false);
      }
      CalcularTotalGral();
      return false;
    }
    return true;
  }
  
  private boolean ValidarDatosDelCliente()
  {
    if (tfNombreCliente.getText().isEmpty())
    {
      JOptionPane.showMessageDialog(null, "Los datos del cliente son obligatorios para la facturaci�n.", null, 1);
      this.btnInsertarCliente.doClick();
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
      dispose();
    }
  }
  
  private void LimpiarTextFieldsLuegoDeFacturar()
  {
    tfCodCliente.setText("");
    this.tfTotal.setText("");
    tfNombreCliente.setText("");
    tfTelefono.setText("");
    tfDireccion.setText("");
    tfCodCliente.setText("");
  }
  
  private void LimpiarDetallesDeProducto()
  {
    this.tfPrecioCosto.setText("");
    this.tfPrecioCredito.setText("");
    this.tfStock.setText("");
    this.tfCantPaquete.setText("");
  }
  
  private void Salir(WindowEvent evt)
  {
    if (this.btnFacturar.isEnabled())
    {
      int res = JOptionPane.showConfirmDialog(null, "�Desea salir sin generar la deuda?", "Salir", 0);
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
    int filaTotal = this.tbFactura.getRowCount();
    int filaTotal2 = this.tbProductos.getRowCount();
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
    this.tfTotal.setText("");
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
}
