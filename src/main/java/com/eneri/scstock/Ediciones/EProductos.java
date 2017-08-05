/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Ediciones;

import com.eneri.scstock.Apariencia.FondoSubFormularios;
import com.eneri.scstock.Formularios.Productos;
import com.eneri.scstock.Herramientas.FormatoPrecio;
import com.eneri.scstock.Herramientas.Validaciones;
import com.eneri.scstock.Modelos.ModeloFamilia;
import com.eneri.scstock.Modelos.ModeloMarcas;
import com.eneri.scstock.Modelos.ModeloProductos;
import com.eneri.scstock.Modelos.ModeloProveedores;
import com.eneri.scstock.Objetos.DaoConfiguraciones;
import com.eneri.scstock.Objetos.DaoFamilias;
import com.eneri.scstock.Objetos.DaoMarcas;
import com.eneri.scstock.Objetos.DaoProductos;
import com.eneri.scstock.Objetos.DaoProveedores;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
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
/**
 *
 * @author RAscencio
 */
public class EProductos extends JDialog
{
    public static JTextField tfCodigo;
  private JTextField tfDescripcion;
  private JTextField tfPrecioCosto;
  private JTextField tfPrecioMayorista;
  private JTextField tfPrecioVenta;
  private JTextField tfPrecioCredito;
  private JTextField tfStockActual;
  private JTextField tfCantPaquete;
  private JButton btnGuardar;
  private boolean existencia;
  private JComboBox comboBoxProveedores;
  private JButton btnModificar;
  public static List<ModeloProveedores> listaProveedores;
  public static List<ModeloFamilia> listaFamilias;
  public static List<ModeloMarcas> listaMarcas;
  private JCheckBox chbxUnidad;
  private JCheckBox chbxMetro;
  private JCheckBox chbxKilogramo;
  private JCheckBox chbxFacturar;
  private JTabbedPane tabbedPane;
  private JTabbedPane tabbedPane_3;
  private JLabel lblImagen;
  private JLabel lblValDescripcion;
  private Productos p;
  private JButton btnProveedRefresh;
  private JLabel lblCodigo;
  private JLabel lblDescripcion;
  private JTextField tfColor;
  private JLabel lblPrecioCosto;
  private JLabel lblPrecioMayorista;
  private JLabel lblPrecioVenta;
  private JLabel lblPrecioCredito;
  private JLabel lblStock;
  private JLabel lblCantEnPaq;
  private JLabel lblProveedor;
  private JLabel lblUnidadDeMedida;
  private JCheckBox chbxCaja;
  private JLabel lblValPrecioCosto;
  private JLabel lblValPrecioMayorista;
  private JLabel lblValPrecioVenta;
  private JLabel lblValPrecioCredito;
  private JTabbedPane tabbedPane_2;
  private JCheckBox chbxMt2;
  private JTextField tfEstante;
  private JLabel label;
  private JLabel lblIva;
  private JLabel label_2;
  private JTextField tfIva;
  private JLabel lblIvaValidate;
  private JComboBox cbxFamilias;
  private JButton btnFamiliaRefresh;
  private JComboBox cbxMarcas;
  private JButton btnMarcasRefresh;
  private JTextArea tfObservaciones;
  private JLabel lblObs;
  private JScrollPane scrollPane;
  private JTextField tfActualizar;
  private double iva1 = DaoConfiguraciones.Iva1();
  private double iva2 = DaoConfiguraciones.Iva2();
  private JTextField tfDescuento;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          EProductos dialog = new EProductos();
          dialog.setVisible(true);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }
  
  public EProductos()
  {
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent evt)
      {
        EProductos.this.Salir(evt);
      }
    });
    setTitle("Datos detallados del producto");
    setIconImage(Toolkit.getDefaultToolkit().getImage(EProductos.class.getResource("/Galeria/product-icon.png")));
    setResizable(false);
    setDefaultCloseOperation(0);
    setBounds(100, 100, 695, 458);
    
    FondoSubFormularios contentPane = new FondoSubFormularios();
    contentPane.setForeground(Color.BLACK);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    tfCodigo = new JTextField();
    tfCodigo.setEnabled(false);
    tfCodigo.setEditable(false);
    tfCodigo.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          EProductos.this.CargarDatosParaModificar();
        }
      }
    });
    tfCodigo.setToolTipText("C�digo del producto");
    tfCodigo.setFont(new Font("Tahoma", 0, 15));
    tfCodigo.setColumns(10);
    tfCodigo.setBounds(184, 23, 157, 21);
    contentPane.add(tfCodigo);
    
    this.tfDescripcion = new JTextField();
    this.tfDescripcion.setToolTipText("Descripci�n del producto");
    this.tfDescripcion.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent arg0)
      {
        if (arg0.getKeyCode() == 10)
        {
          EProductos.this.tfPrecioCosto.requestFocus();
          EProductos.this.tfPrecioCosto.selectAll();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (EProductos.this.tfDescripcion.getText().trim().length() > 0)
        {
          EProductos.this.ReconocerCambios();
          EProductos.this.lblValDescripcion.setVisible(false);
        }
      }
    });
    this.tfDescripcion.setEnabled(false);
    this.tfDescripcion.setFont(new Font("Tahoma", 0, 15));
    this.tfDescripcion.setColumns(10);
    this.tfDescripcion.setBounds(184, 55, 456, 21);
    contentPane.add(this.tfDescripcion);
    
    this.tfPrecioCosto = new JTextField();
    this.tfPrecioCosto.setToolTipText("Precio de costo del producto");
    this.tfPrecioCosto.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent arg0)
      {
        if (arg0.getKeyCode() == 10) {
          EProductos.this.CalcularPrecios();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (EProductos.this.tfPrecioCosto.getText().trim().length() > 0)
        {
          EProductos.this.ReconocerCambios();
          EProductos.this.lblValPrecioCosto.setVisible(false);
        }
      }
    });
    Validaciones validar = new Validaciones();
    validar.ValidarFormatoPrecio(this.tfPrecioCosto);
    this.tfPrecioCosto.setEnabled(false);
    this.tfPrecioCosto.setFont(new Font("Tahoma", 0, 15));
    this.tfPrecioCosto.setColumns(10);
    this.tfPrecioCosto.setBounds(143, 101, 144, 21);
    contentPane.add(this.tfPrecioCosto);
    
    this.tfPrecioMayorista = new JTextField();
    this.tfPrecioMayorista.setEditable(false);
    this.tfPrecioMayorista.setToolTipText("Precio para mayoristas del producto");
    this.tfPrecioMayorista.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent arg0)
      {
        if (arg0.getKeyCode() == 10)
        {
          EProductos.this.tfPrecioVenta.requestFocus();
          EProductos.this.tfPrecioVenta.selectAll();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (EProductos.this.tfPrecioMayorista.getText().trim().length() > 0)
        {
          EProductos.this.ReconocerCambios();
          EProductos.this.lblValPrecioMayorista.setVisible(false);
        }
      }
    });
    validar.BloqueoAlfabetico(this.tfPrecioMayorista);
    this.tfPrecioMayorista.setEnabled(false);
    this.tfPrecioMayorista.setFont(new Font("Tahoma", 0, 15));
    this.tfPrecioMayorista.setColumns(10);
    this.tfPrecioMayorista.setBounds(143, 136, 144, 21);
    contentPane.add(this.tfPrecioMayorista);
    
    this.tfPrecioVenta = new JTextField();
    this.tfPrecioVenta.setEditable(false);
    this.tfPrecioVenta.setToolTipText("Precio de venta del producto (con I.V.A.)");
    this.tfPrecioVenta.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent arg0)
      {
        if (arg0.getKeyCode() == 10)
        {
          EProductos.this.tfPrecioCredito.requestFocus();
          EProductos.this.tfPrecioCredito.selectAll();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (EProductos.this.tfPrecioVenta.getText().trim().length() > 0)
        {
          EProductos.this.ReconocerCambios();
          EProductos.this.lblValPrecioVenta.setVisible(false);
        }
      }
    });
    validar.BloqueoAlfabetico(this.tfPrecioVenta);
    this.tfPrecioVenta.setEnabled(false);
    this.tfPrecioVenta.setFont(new Font("Tahoma", 0, 15));
    this.tfPrecioVenta.setColumns(10);
    this.tfPrecioVenta.setBounds(143, 177, 144, 21);
    contentPane.add(this.tfPrecioVenta);
    
    this.tfPrecioCredito = new JTextField();
    this.tfPrecioCredito.setEditable(false);
    this.tfPrecioCredito.setToolTipText("Precio a cr�dito del producto");
    this.tfPrecioCredito.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent arg0)
      {
        if (arg0.getKeyCode() == 10)
        {
          EProductos.this.tfIva.requestFocus();
          EProductos.this.tfIva.selectAll();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (EProductos.this.tfPrecioCredito.getText().trim().length() > 0)
        {
          EProductos.this.ReconocerCambios();
          EProductos.this.lblValPrecioCredito.setVisible(false);
        }
      }
    });
    validar.BloqueoAlfabetico(this.tfPrecioCredito);
    this.tfPrecioCredito.setEnabled(false);
    this.tfPrecioCredito.setFont(new Font("Tahoma", 0, 15));
    this.tfPrecioCredito.setColumns(10);
    this.tfPrecioCredito.setBounds(143, 218, 144, 21);
    contentPane.add(this.tfPrecioCredito);
    
    this.tfStockActual = new JTextField();
    this.tfStockActual.setEditable(false);
    this.tfStockActual.setToolTipText("Stock actual del producto");
    this.tfStockActual.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent arg0)
      {
        if (arg0.getKeyCode() == 10)
        {
          EProductos.this.tfCantPaquete.requestFocus();
          EProductos.this.tfCantPaquete.selectAll();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (EProductos.this.tfStockActual.getText().trim().length() > 0) {
          EProductos.this.ReconocerCambios();
        }
      }
    });
    validar.BloqueoAlfabetico(this.tfStockActual);
    this.tfStockActual.setEnabled(false);
    this.tfStockActual.setFont(new Font("Tahoma", 0, 15));
    this.tfStockActual.setColumns(10);
    this.tfStockActual.setBounds(82, 340, 67, 21);
    contentPane.add(this.tfStockActual);
    
    this.tfCantPaquete = new JTextField();
    this.tfCantPaquete.setToolTipText("Cantidad en paquete del producto");
    this.tfCantPaquete.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10)
        {
          EProductos.this.tfEstante.requestFocus();
          EProductos.this.tfEstante.selectAll();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (EProductos.this.tfCantPaquete.getText().trim().length() > 0) {
          EProductos.this.ReconocerCambios();
        }
      }
    });
    validar.BloqueoAlfabetico(this.tfCantPaquete);
    this.tfCantPaquete.setEnabled(false);
    this.tfCantPaquete.setFont(new Font("Tahoma", 0, 15));
    this.tfCantPaquete.setColumns(10);
    this.tfCantPaquete.setBounds(270, 340, 67, 21);
    contentPane.add(this.tfCantPaquete);
    
    this.lblCantEnPaq = new JLabel(" Cant. en paq.:");
    this.lblCantEnPaq.setForeground(Color.WHITE);
    this.lblCantEnPaq.setFont(new Font("Tahoma", 0, 15));
    this.lblCantEnPaq.setBounds(159, 342, 128, 17);
    contentPane.add(this.lblCantEnPaq);
    
    this.lblStock = new JLabel(" Stock:");
    this.lblStock.setForeground(Color.WHITE);
    this.lblStock.setFont(new Font("Tahoma", 0, 15));
    this.lblStock.setBounds(26, 345, 74, 14);
    contentPane.add(this.lblStock);
    
    this.lblPrecioCredito = new JLabel("    Precio cr�dito:");
    this.lblPrecioCredito.setForeground(Color.WHITE);
    this.lblPrecioCredito.setFont(new Font("Tahoma", 0, 15));
    this.lblPrecioCredito.setBounds(26, 222, 130, 14);
    contentPane.add(this.lblPrecioCredito);
    
    this.lblPrecioVenta = new JLabel(" Precio de venta:");
    this.lblPrecioVenta.setForeground(Color.WHITE);
    this.lblPrecioVenta.setFont(new Font("Tahoma", 0, 15));
    this.lblPrecioVenta.setBounds(26, 180, 130, 14);
    contentPane.add(this.lblPrecioVenta);
    
    this.lblPrecioMayorista = new JLabel("Precio mayorista:");
    this.lblPrecioMayorista.setForeground(Color.WHITE);
    this.lblPrecioMayorista.setFont(new Font("Tahoma", 0, 15));
    this.lblPrecioMayorista.setBounds(26, 140, 130, 17);
    contentPane.add(this.lblPrecioMayorista);
    
    this.lblPrecioCosto = new JLabel("Precio de costo:");
    this.lblPrecioCosto.setForeground(Color.WHITE);
    this.lblPrecioCosto.setFont(new Font("Tahoma", 0, 15));
    this.lblPrecioCosto.setBounds(37, 102, 119, 18);
    contentPane.add(this.lblPrecioCosto);
    
    this.lblDescripcion = new JLabel(" Descripci�n:");
    this.lblDescripcion.setForeground(Color.WHITE);
    this.lblDescripcion.setFont(new Font("Tahoma", 0, 15));
    this.lblDescripcion.setBounds(71, 57, 103, 17);
    contentPane.add(this.lblDescripcion);
    
    this.lblCodigo = new JLabel(" C�digo:");
    this.lblCodigo.setForeground(Color.WHITE);
    this.lblCodigo.setFont(new Font("Tahoma", 0, 15));
    this.lblCodigo.setBounds(100, 23, 74, 18);
    contentPane.add(this.lblCodigo);
    
    this.lblProveedor = new JLabel("Proveedor:");
    this.lblProveedor.setForeground(Color.WHITE);
    this.lblProveedor.setFont(new Font("Tahoma", 0, 15));
    this.lblProveedor.setBounds(361, 24, 91, 14);
    contentPane.add(this.lblProveedor);
    
    this.comboBoxProveedores = new JComboBox();
    this.comboBoxProveedores.setToolTipText("Proveedor del producto");
    this.comboBoxProveedores.setEnabled(false);
    this.comboBoxProveedores.setBounds(435, 23, 189, 21);
    contentPane.add(this.comboBoxProveedores);
    
    this.lblUnidadDeMedida = new JLabel("Unidad de medida");
    this.lblUnidadDeMedida.setForeground(Color.WHITE);
    this.lblUnidadDeMedida.setFont(new Font("Tahoma", 0, 12));
    this.lblUnidadDeMedida.setBounds(349, 108, 136, 14);
    contentPane.add(this.lblUnidadDeMedida);
    
    this.btnGuardar = new JButton("Guardar");
    this.btnGuardar.setEnabled(false);
    this.btnGuardar.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent arg0)
      {
        if (arg0.getKeyCode() == 10) {
          EProductos.this.ModificarProducto();
        }
      }
    });
    this.btnGuardar.setMnemonic('G');
    this.btnGuardar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        EProductos.this.ModificarProducto();
      }
    });
    this.btnGuardar.setIcon(new ImageIcon(EProductos.class.getResource("/Iconos/Guardar.png")));
    this.btnGuardar.setToolTipText("Guardar datos");
    this.btnGuardar.setFont(new Font("Tahoma", 0, 15));
    this.btnGuardar.setBounds(519, 369, 158, 57);
    contentPane.add(this.btnGuardar);
    
    this.lblImagen = new JLabel("");
    this.lblImagen.setIcon(new ImageIcon(EProductos.class.getResource("/Galeria/product-icon.png")));
    this.lblImagen.setForeground(Color.ORANGE);
    this.lblImagen.setFont(new Font("Tahoma", 0, 12));
    this.lblImagen.setBounds(519, 97, 158, 160);
    contentPane.add(this.lblImagen);
    
    this.btnModificar = new JButton("Modificar");
    this.btnModificar.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          EProductos.this.HabilitarComponentesParaModificar();
          EProductos.this.lblImagen.setIcon(new ImageIcon(EProveedores.class.getResource("/Galeria/IconModificar.png")));
          EProductos.this.btnModificar.setEnabled(false);
        }
      }
    });
    this.btnModificar.setMnemonic('M');
    this.btnModificar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        EProductos.this.HabilitarComponentesParaModificar();
        EProductos.this.lblImagen.setIcon(new ImageIcon(EProveedores.class.getResource("/Galeria/IconModificar.png")));
        EProductos.this.btnModificar.setEnabled(false);
      }
    });
    this.btnModificar.setIcon(new ImageIcon(EProductos.class.getResource("/Iconos/Modificar.png")));
    this.btnModificar.setToolTipText("Modificar datos del producto");
    this.btnModificar.setFont(new Font("Tahoma", 0, 15));
    this.btnModificar.setBounds(519, 329, 158, 34);
    contentPane.add(this.btnModificar);
    
    this.chbxUnidad = new JCheckBox("Unidad");
    this.chbxUnidad.setForeground(Color.WHITE);
    this.chbxUnidad.setOpaque(false);
    this.chbxUnidad.setToolTipText("Unidad de medida en unidades");
    this.chbxUnidad.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent arg0)
      {
        if (arg0.getKeyCode() == 10) {
          EProductos.this.chbxMetro.requestFocus();
        }
      }
    });
    this.chbxUnidad.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        EProductos.this.chbxKilogramo.setSelected(false);
        EProductos.this.chbxMetro.setSelected(false);
        EProductos.this.chbxCaja.setSelected(false);
        EProductos.this.chbxMt2.setSelected(false);
        EProductos.this.ReconocerCambios();
      }
    });
    this.chbxUnidad.setEnabled(false);
    this.chbxUnidad.setBounds(341, 132, 143, 23);
    contentPane.add(this.chbxUnidad);
    
    this.chbxMetro = new JCheckBox("Metro");
    this.chbxMetro.setForeground(Color.WHITE);
    this.chbxMetro.setOpaque(false);
    this.chbxMetro.setToolTipText("Unidad de medida en metros");
    this.chbxMetro.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent arg0)
      {
        if (arg0.getKeyCode() == 10) {
          EProductos.this.chbxKilogramo.requestFocus();
        }
      }
    });
    this.chbxMetro.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        EProductos.this.chbxUnidad.setSelected(false);
        EProductos.this.chbxKilogramo.setSelected(false);
        EProductos.this.chbxCaja.setSelected(false);
        EProductos.this.chbxMt2.setSelected(false);
        EProductos.this.ReconocerCambios();
      }
    });
    this.chbxMetro.setEnabled(false);
    this.chbxMetro.setBounds(341, 154, 143, 23);
    contentPane.add(this.chbxMetro);
    
    this.chbxMt2 = new JCheckBox("Mt2");
    this.chbxMt2.setForeground(Color.WHITE);
    this.chbxMt2.setOpaque(false);
    this.chbxMt2.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          EProductos.this.chbxFacturar.requestFocus();
        }
      }
    });
    this.chbxMt2.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (EProductos.this.chbxMt2.isSelected())
        {
          EProductos.this.chbxUnidad.setSelected(false);
          EProductos.this.chbxKilogramo.setSelected(false);
          EProductos.this.chbxCaja.setSelected(false);
          EProductos.this.chbxMetro.setSelected(false);
        }
      }
    });
    this.chbxMt2.setEnabled(false);
    this.chbxMt2.setToolTipText("Unidad de medida en metro cuadrado");
    this.chbxMt2.setMnemonic('C');
    this.chbxMt2.setBounds(341, 216, 143, 21);
    contentPane.add(this.chbxMt2);
    
    this.chbxKilogramo = new JCheckBox("Kilogramo");
    this.chbxKilogramo.setForeground(Color.WHITE);
    this.chbxKilogramo.setOpaque(false);
    this.chbxKilogramo.setToolTipText("Unidad de medida en kilogramos");
    this.chbxKilogramo.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent arg0)
      {
        if (arg0.getKeyCode() == 10) {
          EProductos.this.chbxCaja.requestFocus();
        }
      }
    });
    this.chbxKilogramo.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        EProductos.this.chbxUnidad.setSelected(false);
        EProductos.this.chbxMetro.setSelected(false);
        EProductos.this.chbxCaja.setSelected(false);
        EProductos.this.chbxMt2.setSelected(false);
        EProductos.this.ReconocerCambios();
      }
    });
    this.chbxKilogramo.setEnabled(false);
    this.chbxKilogramo.setBounds(341, 175, 143, 23);
    contentPane.add(this.chbxKilogramo);
    
    this.chbxFacturar = new JCheckBox("Facturar sin stock");
    this.chbxFacturar.setForeground(Color.WHITE);
    this.chbxFacturar.setOpaque(false);
    this.chbxFacturar.setToolTipText("Si guarda \"Facturar sin stock\" el sistema omitir� el \"Stock agotado\" y facturar� de todas formas.");
    this.chbxFacturar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        EProductos.this.ReconocerCambios();
      }
    });
    this.chbxFacturar.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent arg0)
      {
        if (arg0.getKeyCode() == 10)
        {
          EProductos.this.cbxFamilias.requestFocus();
          EProductos.this.ReconocerCambios();
        }
      }
    });
    this.chbxFacturar.setEnabled(false);
    this.chbxFacturar.setBounds(341, 238, 143, 20);
    contentPane.add(this.chbxFacturar);
    
    this.lblValDescripcion = new JLabel("Ingrese la descripci�n del producto");
    this.lblValDescripcion.setVisible(false);
    this.lblValDescripcion.setForeground(Color.RED);
    this.lblValDescripcion.setBounds(184, 74, 426, 21);
    contentPane.add(this.lblValDescripcion);
    
    this.btnProveedRefresh = new JButton("");
    this.btnProveedRefresh.setToolTipText("Cambiar proveedor del producto (Actualizar lista)");
    this.btnProveedRefresh.setEnabled(false);
    this.btnProveedRefresh.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        EProductos.this.btnProveedRefresh.setEnabled(false);
        EProductos.this.comboBoxProveedores.removeAllItems();
        EProductos.this.cargarComboBox();
        EProductos.this.ReconocerCambios();
      }
    });
    this.btnProveedRefresh.setIcon(new ImageIcon(EProductos.class.getResource("/Iconos/actualizar.png")));
    this.btnProveedRefresh.setBounds(634, 23, 23, 20);
    contentPane.add(this.btnProveedRefresh);
    
    this.tfColor = new JTextField();
    this.tfColor.setVisible(false);
    this.tfColor.setBounds(10, 23, 80, 20);
    contentPane.add(this.tfColor);
    this.tfColor.setColumns(10);
    
    this.chbxCaja = new JCheckBox("Caja");
    this.chbxCaja.setForeground(Color.WHITE);
    this.chbxCaja.setOpaque(false);
    this.chbxCaja.setToolTipText("Unidad de medida en cajas");
    this.chbxCaja.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          EProductos.this.chbxMt2.requestFocus();
        }
      }
    });
    this.chbxCaja.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        EProductos.this.chbxKilogramo.setSelected(false);
        EProductos.this.chbxMetro.setSelected(false);
        EProductos.this.chbxUnidad.setSelected(false);
        EProductos.this.chbxMt2.setSelected(false);
        EProductos.this.ReconocerCambios();
      }
    });
    this.chbxCaja.setMnemonic('C');
    this.chbxCaja.setEnabled(false);
    this.chbxCaja.setBounds(341, 198, 143, 21);
    contentPane.add(this.chbxCaja);
    
    this.lblValPrecioCosto = new JLabel("Ingrese el precio de costo");
    this.lblValPrecioCosto.setVisible(false);
    this.lblValPrecioCosto.setForeground(Color.RED);
    this.lblValPrecioCosto.setBounds(144, 121, 173, 14);
    contentPane.add(this.lblValPrecioCosto);
    
    this.lblValPrecioMayorista = new JLabel("Ingrese el precio mayorista");
    this.lblValPrecioMayorista.setVisible(false);
    this.lblValPrecioMayorista.setForeground(Color.RED);
    this.lblValPrecioMayorista.setBounds(144, 160, 173, 14);
    contentPane.add(this.lblValPrecioMayorista);
    
    this.lblValPrecioVenta = new JLabel("Ingrese el precio de venta");
    this.lblValPrecioVenta.setVisible(false);
    this.lblValPrecioVenta.setForeground(Color.RED);
    this.lblValPrecioVenta.setBounds(144, 196, 173, 14);
    contentPane.add(this.lblValPrecioVenta);
    
    this.lblValPrecioCredito = new JLabel("Ingrese el precio cr�dito");
    this.lblValPrecioCredito.setVisible(false);
    this.lblValPrecioCredito.setForeground(Color.RED);
    this.lblValPrecioCredito.setBounds(143, 243, 174, 14);
    contentPane.add(this.lblValPrecioCredito);
    
    this.tabbedPane_2 = new JTabbedPane(1);
    this.tabbedPane_2.setBounds(10, 11, 667, 83);
    contentPane.add(this.tabbedPane_2);
    
    this.tfIva = new JTextField();
    validar.BloqueoAlfabetico(this.tfIva);
    this.tfIva.setEnabled(false);
    this.tfIva.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (EProductos.this.tfIva.getText().trim().length() > 0) {
          EProductos.this.lblIvaValidate.setVisible(false);
        }
        if (EProductos.this.tfIva.getText().trim().length() > 0) {
          EProductos.this.ReconocerCambios();
        }
      }
      
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          EProductos.this.tfDescuento.requestFocus();
          EProductos.this.tfDescuento.selectAll();
        }
      }
    });
    this.tfIva.setToolTipText("Ingrese el precio a cr�dito del producto");
    this.tfIva.setText("10");
    this.tfIva.setFont(new Font("Tahoma", 0, 15));
    this.tfIva.setColumns(10);
    this.tfIva.setBounds(143, 260, 57, 21);
    contentPane.add(this.tfIva);
    
    this.tfEstante = new JTextField();
    validar.BloqueoAlfabetico(this.tfEstante);
    this.tfEstante.setEnabled(false);
    this.tfEstante.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          EProductos.this.tfObservaciones.requestFocus();
          EProductos.this.tfObservaciones.selectAll();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (EProductos.this.tfEstante.getText().trim().length() > 0) {
          EProductos.this.btnGuardar.setEnabled(true);
        }
      }
      
      public void keyTyped(KeyEvent e)
      {
        if (EProductos.this.tfEstante.getText().trim().length() > 4) {
          e.consume();
        }
      }
    });
    this.tfEstante.setToolTipText("Ingrese el precio a cr�dito del producto");
    this.tfEstante.setText("0");
    this.tfEstante.setFont(new Font("Tahoma", 0, 15));
    this.tfEstante.setColumns(10);
    this.tfEstante.setBounds(406, 340, 103, 21);
    contentPane.add(this.tfEstante);
    
    this.label = new JLabel("  %");
    this.label.setForeground(Color.WHITE);
    this.label.setFont(new Font("Tahoma", 0, 15));
    this.label.setBounds(198, 261, 47, 19);
    contentPane.add(this.label);
    
    this.lblIva = new JLabel("I.V.A.:");
    this.lblIva.setForeground(Color.WHITE);
    this.lblIva.setFont(new Font("Tahoma", 0, 15));
    this.lblIva.setBounds(89, 260, 74, 21);
    contentPane.add(this.lblIva);
    
    this.label_2 = new JLabel("Estante:");
    this.label_2.setForeground(Color.WHITE);
    this.label_2.setFont(new Font("Tahoma", 0, 15));
    this.label_2.setBounds(347, 343, 62, 14);
    contentPane.add(this.label_2);
    
    this.lblIvaValidate = new JLabel("Ingrese el I.V.A");
    this.lblIvaValidate.setVisible(false);
    this.lblIvaValidate.setForeground(Color.RED);
    this.lblIvaValidate.setBounds(143, 274, 103, 26);
    contentPane.add(this.lblIvaValidate);
    
    JLabel lblFamilia = new JLabel("Familia:");
    lblFamilia.setForeground(Color.WHITE);
    lblFamilia.setFont(new Font("Tahoma", 0, 15));
    lblFamilia.setBounds(327, 266, 91, 14);
    contentPane.add(lblFamilia);
    
    this.cbxFamilias = new JComboBox();
    this.cbxFamilias.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          EProductos.this.cbxMarcas.requestFocus();
        }
      }
    });
    this.cbxFamilias.setToolTipText("Familia del producto");
    this.cbxFamilias.setEnabled(false);
    this.cbxFamilias.setBounds(392, 265, 198, 21);
    contentPane.add(this.cbxFamilias);
    
    JLabel lblMarcas = new JLabel("Marca:");
    lblMarcas.setForeground(Color.WHITE);
    lblMarcas.setFont(new Font("Tahoma", 0, 15));
    lblMarcas.setBounds(327, 298, 91, 14);
    contentPane.add(lblMarcas);
    
    this.cbxMarcas = new JComboBox();
    this.cbxMarcas.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          EProductos.this.btnGuardar.requestFocus();
        }
      }
    });
    this.cbxMarcas.setToolTipText("Marca del producto");
    this.cbxMarcas.setEnabled(false);
    this.cbxMarcas.setBounds(392, 297, 198, 21);
    contentPane.add(this.cbxMarcas);
    
    this.btnFamiliaRefresh = new JButton("");
    this.btnFamiliaRefresh.setIcon(new ImageIcon(EProductos.class.getResource("/Iconos/actualizar.png")));
    this.btnFamiliaRefresh.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        EProductos.this.btnFamiliaRefresh.setEnabled(false);
        EProductos.this.btnGuardar.setEnabled(true);
        EProductos.this.cbxFamilias.removeAllItems();
        EProductos.this.cargarCBXFamilias();
      }
    });
    this.btnFamiliaRefresh.setToolTipText("Cambiar proveedor del producto (Actualizar lista)");
    this.btnFamiliaRefresh.setEnabled(false);
    this.btnFamiliaRefresh.setBounds(601, 264, 23, 20);
    contentPane.add(this.btnFamiliaRefresh);
    
    this.btnMarcasRefresh = new JButton("");
    this.btnMarcasRefresh.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        EProductos.this.btnMarcasRefresh.setEnabled(false);
        EProductos.this.cbxMarcas.removeAllItems();
        EProductos.this.btnGuardar.setEnabled(true);
        EProductos.this.cargarCBXMarcas();
      }
    });
    this.btnMarcasRefresh.setIcon(new ImageIcon(EProductos.class.getResource("/Iconos/actualizar.png")));
    this.btnMarcasRefresh.setToolTipText("Cambiar proveedor del producto (Actualizar lista)");
    this.btnMarcasRefresh.setEnabled(false);
    this.btnMarcasRefresh.setBounds(600, 297, 23, 21);
    contentPane.add(this.btnMarcasRefresh);
    
    this.scrollPane = new JScrollPane();
    this.scrollPane.setEnabled(false);
    this.scrollPane.setBounds(82, 372, 426, 47);
    contentPane.add(this.scrollPane);
    
    this.tfObservaciones = new JTextArea();
    this.tfObservaciones.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (EProductos.this.tfObservaciones.getText().trim().length() > 0) {
          EProductos.this.ReconocerCambios();
        }
      }
      
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          EProductos.this.chbxUnidad.requestFocus();
        }
      }
    });
    this.scrollPane.setViewportView(this.tfObservaciones);
    this.tfObservaciones.setToolTipText("Observaciones del producto");
    this.tfObservaciones.setFont(new Font("Tahoma", 0, 15));
    this.tfObservaciones.setEnabled(false);
    this.tfObservaciones.setColumns(10);
    
    this.lblObs = new JLabel("Obs.:");
    this.lblObs.setForeground(Color.WHITE);
    this.lblObs.setFont(new Font("Tahoma", 0, 15));
    this.lblObs.setBounds(26, 372, 74, 14);
    contentPane.add(this.lblObs);
    
    this.tabbedPane_3 = new JTabbedPane(1);
    this.tabbedPane_3.setBounds(10, 330, 507, 96);
    contentPane.add(this.tabbedPane_3);
    
    this.tfActualizar = new JTextField();
    this.tfActualizar.setFont(new Font("Tahoma", 0, 15));
    this.tfActualizar.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10)
        {
          EProductos.this.ActualizarStock();
          EProductos.this.tfCantPaquete.requestFocus();
          EProductos.this.tfCantPaquete.selectAll();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (EProductos.this.tfActualizar.getText().trim().length() > 0) {
          EProductos.this.ReconocerCambios();
        }
      }
    });
    this.tfActualizar.setBounds(256, 295, 47, 20);
    contentPane.add(this.tfActualizar);
    this.tfActualizar.setColumns(10);
    
    JLabel lblXxxxx = new JLabel("New:");
    lblXxxxx.setForeground(Color.WHITE);
    lblXxxxx.setFont(new Font("Tahoma", 0, 15));
    lblXxxxx.setBounds(221, 295, 50, 21);
    contentPane.add(lblXxxxx);
    
    JLabel label_1 = new JLabel("%");
    label_1.setForeground(Color.WHITE);
    label_1.setFont(new Font("Tahoma", 0, 15));
    label_1.setBounds(194, 299, 30, 17);
    contentPane.add(label_1);
    
    this.tfDescuento = new JTextField();
    this.tfDescuento.setEnabled(false);
    validar.ValidarFormatoPrecio(this.tfDescuento);
    this.tfDescuento.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          EProductos.this.tfActualizar.requestFocus();
          EProductos.this.tfActualizar.selectAll();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (EProductos.this.tfDescuento.getText().trim().length() > 0) {
          EProductos.this.ReconocerCambios();
        }
      }
    });
    this.tfDescuento.setToolTipText("Ingrese el precio a cr�dito del producto");
    this.tfDescuento.setText("0");
    this.tfDescuento.setFont(new Font("Tahoma", 0, 15));
    this.tfDescuento.setColumns(10);
    this.tfDescuento.setBounds(143, 297, 47, 21);
    contentPane.add(this.tfDescuento);
    
    JLabel label_3 = new JLabel("Descuento:");
    label_3.setForeground(Color.WHITE);
    label_3.setFont(new Font("Tahoma", 0, 15));
    label_3.setBounds(60, 299, 91, 17);
    contentPane.add(label_3);
    
    this.tabbedPane = new JTabbedPane(1);
    this.tabbedPane.setBounds(10, 96, 667, 228);
    contentPane.add(this.tabbedPane);
    
    LlamadaSeleccion();
    CargarDatosParaModificar();
    ColorBlanco();
  }
  
  private void ActualizarStock()
  {
    double stockActual = 0.0D;
    double stockNuevo = 0.0D;
    double totalStock = 0.0D;
    stockActual = Double.parseDouble(this.tfStockActual.getText());
    stockNuevo = Double.parseDouble(this.tfActualizar.getText());
    
    totalStock = stockActual + stockNuevo;
    
    this.tfStockActual.setText(String.valueOf(totalStock));
  }
  
  private void CalcularPrecios()
  {
    if (this.tfPrecioCosto.getText().trim().isEmpty())
    {
      this.lblValPrecioCosto.setVisible(true);
      this.tfPrecioCosto.requestFocus();
    }
    else
    {
      double utilPorcMayorista = DaoConfiguraciones.PorcentajeUtilidadMayorista();
      double utilPorcVenta = DaoConfiguraciones.PorcentajeUtilidadVenta();
      double utilPorcCredito = DaoConfiguraciones.PorcentajeUtilidadCredito();
      double precioCosto = 0.0D;
      double precioMayorista = 0.0D;
      double precioVenta = 0.0D;
      double precioCredito = 0.0D;
      precioCosto = Double.parseDouble(this.tfPrecioCosto.getText());
      
      precioMayorista = precioCosto * utilPorcMayorista / 100.0D;
      precioMayorista = precioCosto + precioMayorista;
      precioMayorista = FormatoPrecio.NumeroFormato(precioMayorista);
      
      precioVenta = precioCosto * utilPorcVenta / 100.0D;
      precioVenta = precioCosto + precioVenta;
      precioVenta = FormatoPrecio.NumeroFormato(precioVenta);
      
      precioCredito = precioCosto * utilPorcCredito / 100.0D;
      precioCredito = precioCosto + precioCredito;
      precioCredito = FormatoPrecio.NumeroFormato(precioCredito);
      
      this.tfPrecioMayorista.setText(String.valueOf(precioMayorista));
      this.tfPrecioVenta.setText(String.valueOf(precioVenta));
      this.tfPrecioCredito.setText(String.valueOf(precioCredito));
      
      this.tfIva.requestFocus();
      this.tfIva.selectAll();
    }
  }
  
  private void ColorBlanco()
  {
    this.lblCodigo.setForeground(Color.WHITE);
    this.lblDescripcion.setForeground(Color.WHITE);
    this.lblCantEnPaq.setForeground(Color.WHITE);
    this.lblPrecioCosto.setForeground(Color.WHITE);
    this.lblPrecioCredito.setForeground(Color.WHITE);
    this.lblPrecioMayorista.setForeground(Color.WHITE);
    this.lblPrecioVenta.setForeground(Color.WHITE);
    this.lblProveedor.setForeground(Color.WHITE);
    this.lblStock.setForeground(Color.WHITE);
    this.lblUnidadDeMedida.setForeground(Color.WHITE);
  }
  
  private void LlamadaSeleccion()
  {
    int seleccion = Productos.tablaProductos.getSelectedRow();
    try
    {
      String codigo = Productos.tablaProductos.getValueAt(seleccion, 0).toString();
      tfCodigo.setText(codigo);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  private void CargarDatosParaModificar()
  {
    String codigoproducto = tfCodigo.getText();
    
    ModeloProductos productos = DaoProductos.ConsutaParaModificar(codigoproducto);
    
    this.tfDescripcion.setText(productos.getDescripcion());
    this.tfPrecioCosto.setText(String.valueOf(productos.getPrecioCosto()));
    this.tfPrecioMayorista.setText(String.valueOf(productos.getPrecioMayorista()));
    this.tfPrecioVenta.setText(String.valueOf(productos.getPrecioVenta()));
    this.tfPrecioCredito.setText(String.valueOf(productos.getPrecioCredito()));
    this.tfIva.setText(String.valueOf(productos.getIva()));
    this.tfEstante.setText(String.valueOf(productos.getEstante()));
    this.tfObservaciones.setText(String.valueOf(productos.getObservaciones()));
    this.tfDescuento.setText(String.valueOf(productos.getDescuento()));
    if ((productos.getUnidadDeMedida().equals("MTS")) || (productos.getUnidadDeMedida().equals("KGS")))
    {
      this.tfStockActual.setText(String.valueOf(productos.getStock()));
    }
    else
    {
      int stock = (int)productos.getStock();
      this.tfStockActual.setText(String.valueOf(stock));
    }
    this.tfCantPaquete.setText(String.valueOf(productos.getCantPaquete()));
    if (productos.getUnidadDeMedida().equals("MTS"))
    {
      this.chbxMetro.setSelected(true);
      this.chbxUnidad.setSelected(false);
      this.chbxKilogramo.setSelected(false);
      this.chbxCaja.setSelected(false);
      this.chbxMt2.setSelected(false);
    }
    if (productos.getUnidadDeMedida().equals("KGS"))
    {
      this.chbxKilogramo.setSelected(true);
      this.chbxMetro.setSelected(false);
      this.chbxUnidad.setSelected(false);
      this.chbxCaja.setSelected(false);
      this.chbxMt2.setSelected(false);
    }
    if (productos.getUnidadDeMedida().equals("UNI"))
    {
      this.chbxUnidad.setSelected(true);
      this.chbxKilogramo.setSelected(false);
      this.chbxMetro.setSelected(false);
      this.chbxCaja.setSelected(false);
      this.chbxMt2.setSelected(false);
    }
    if (productos.getUnidadDeMedida().equals("CAJ"))
    {
      this.chbxCaja.setSelected(true);
      this.chbxUnidad.setSelected(false);
      this.chbxKilogramo.setSelected(false);
      this.chbxMetro.setSelected(false);
      this.chbxMt2.setSelected(false);
    }
    if (productos.getUnidadDeMedida().equals("MT2"))
    {
      this.chbxMt2.setSelected(true);
      this.chbxCaja.setSelected(false);
      this.chbxUnidad.setSelected(false);
      this.chbxKilogramo.setSelected(false);
      this.chbxMetro.setSelected(false);
    }
    if (productos.getFacturar().equals("Si")) {
      this.chbxFacturar.setSelected(true);
    }
    this.comboBoxProveedores.addItem(productos.getCodProveedor() + "-" + productos.getProveedor().getNombre());
    this.cbxMarcas.addItem(productos.getMarcas().getCodigo() + "-" + productos.getMarcas().getDescripcion());
    this.cbxFamilias.addItem(productos.getFamilia().getCodigo() + "-" + productos.getFamilia().getDescripcion());
  }
  
  private void cargarComboBox()
  {
    listaProveedores = new ArrayList();
    listaProveedores = DaoProveedores.consultarTodosLosProveedores("proveedor_nombre");
    for (int i = 0; i < listaProveedores.size(); i++)
    {
      ModeloProveedores m = (ModeloProveedores)listaProveedores.get(i);
      this.comboBoxProveedores.addItem(m.getCodigo() + "-" + m.getNombre());
    }
  }
  
  private void cargarCBXMarcas()
  {
    listaMarcas = new ArrayList();
    listaMarcas = DaoMarcas.ConsultarMarcasActivas("marca_descripcion");
    for (int i = 0; i < listaMarcas.size(); i++)
    {
      ModeloMarcas m = (ModeloMarcas)listaMarcas.get(i);
      
      this.cbxMarcas.addItem(m.getCodigo() + "-" + m.getDescripcion());
    }
  }
  
  private void cargarCBXFamilias()
  {
    listaFamilias = new ArrayList();
    listaFamilias = DaoFamilias.ConsultarFamiliasActivas("familia_descripcion");
    for (int i = 0; i < listaFamilias.size(); i++)
    {
      ModeloFamilia m = (ModeloFamilia)listaFamilias.get(i);
      
      this.cbxFamilias.addItem(m.getCodigo() + "-" + m.getDescripcion());
    }
  }
  
  private void HabilitarComponentesParaModificar()
  {
    this.tfDescripcion.setEnabled(true);
    this.tfPrecioCosto.setEnabled(true);
    this.tfPrecioCredito.setEnabled(true);
    this.tfPrecioMayorista.setEnabled(true);
    this.tfPrecioVenta.setEnabled(true);
    this.tfStockActual.setEnabled(true);
    this.tfCantPaquete.setEnabled(true);
    this.chbxFacturar.setEnabled(true);
    this.chbxKilogramo.setEnabled(true);
    this.chbxMetro.setEnabled(true);
    this.chbxUnidad.setEnabled(true);
    this.chbxCaja.setEnabled(true);
    this.chbxMt2.setEnabled(true);
    this.comboBoxProveedores.setEnabled(true);
    this.btnProveedRefresh.setEnabled(true);
    this.tfIva.setEnabled(true);
    this.tfEstante.setEnabled(true);
    this.btnFamiliaRefresh.setEnabled(true);
    this.btnMarcasRefresh.setEnabled(true);
    this.cbxFamilias.setEnabled(true);
    this.cbxMarcas.setEnabled(true);
    this.scrollPane.setEnabled(true);
    this.tfObservaciones.setEnabled(true);
    this.tfDescuento.setEnabled(true);
    this.tfDescripcion.requestFocus();
    this.tfDescripcion.selectAll();
  }
  
  private boolean ValidarDatosDelProducto()
  {
    if (this.tfDescripcion.getText().trim().isEmpty())
    {
      this.tfDescripcion.requestFocus();
      this.lblValDescripcion.setVisible(true);
      this.lblValDescripcion.setForeground(Color.RED);
      return false;
    }
    if (this.tfPrecioCosto.getText().trim().isEmpty())
    {
      this.lblValPrecioCosto.setVisible(true);
      this.tfPrecioCosto.requestFocus();
      return false;
    }
    if (this.tfPrecioMayorista.getText().trim().isEmpty())
    {
      this.lblValPrecioMayorista.setVisible(true);
      this.tfPrecioMayorista.requestFocus();
      return false;
    }
    if (this.tfPrecioVenta.getText().trim().isEmpty())
    {
      this.lblValPrecioVenta.setVisible(true);
      this.tfPrecioVenta.requestFocus();
      return false;
    }
    if (this.tfPrecioCredito.getText().trim().isEmpty())
    {
      this.lblValPrecioCredito.setVisible(true);
      this.tfPrecioCredito.requestFocus();
      return false;
    }
    if (this.tfStockActual.getText().trim().isEmpty())
    {
      this.tfStockActual.setBackground(Color.GRAY);
      JOptionPane.showMessageDialog(null, "No se puede registrar un producto sin stock.");
      this.tfStockActual.requestFocus();
      return false;
    }
    if (this.tfIva.getText().trim().isEmpty())
    {
      this.lblIvaValidate.setVisible(true);
      this.tfIva.requestFocus();
      return false;
    }
    double iva = Double.parseDouble(this.tfIva.getText());
    if ((iva != this.iva1) && (iva != this.iva2) && (iva != 0.0D))
    {
      JOptionPane.showMessageDialog(null, "Verifique la configuraci�n del sistema, los ajustes de iva no coinciden");
      return false;
    }
    return true;
  }
  
  private void ModificarProducto()
  {
    if (ValidarDatosDelProducto())
    {
      ModeloProductos productos = new ModeloProductos();
      
      productos.setCodigo(tfCodigo.getText());
      productos.setDescripcion(this.tfDescripcion.getText().toUpperCase());
      
      productos.setPrecioCosto(Double.parseDouble(this.tfPrecioCosto.getText()));
      productos.setPrecioMayorista(Double.parseDouble(this.tfPrecioMayorista.getText()));
      productos.setPrecioVenta(Double.parseDouble(this.tfPrecioVenta.getText()));
      productos.setPrecioCredito(Double.parseDouble(this.tfPrecioCredito.getText()));
      productos.setIva(Double.parseDouble(this.tfIva.getText()));
      productos.setEstante(this.tfEstante.getText());
      productos.setCantPaquete(Integer.parseInt(this.tfCantPaquete.getText()));
      productos.setStock(Double.parseDouble(this.tfStockActual.getText()));
      productos.setObservaciones(this.tfObservaciones.getText().toUpperCase());
      if (this.tfDescuento.getText().trim().length() < 1) {
        productos.setDescuento(0.0D);
      }
      productos.setDescuento(Double.parseDouble(this.tfDescuento.getText()));
      productos.setCodProveedor(Integer.parseInt(this.comboBoxProveedores.getSelectedItem().toString().split("-")[0]));
      productos.getFamilia().setCodigo(Integer.parseInt(this.cbxFamilias.getSelectedItem().toString().split("-")[0]));
      productos.getMarcas().setCodigo(Integer.parseInt(this.cbxMarcas.getSelectedItem().toString().split("-")[0]));
      if (this.chbxUnidad.isSelected()) {
        productos.setUnidadDeMedida("UNI");
      }
      if (this.chbxMetro.isSelected()) {
        productos.setUnidadDeMedida("MTS");
      }
      if (this.chbxKilogramo.isSelected()) {
        productos.setUnidadDeMedida("KGS");
      }
      if (this.chbxCaja.isSelected()) {
        productos.setUnidadDeMedida("CAJ");
      }
      if (this.chbxMt2.isSelected()) {
        productos.setUnidadDeMedida("MT2");
      }
      if (this.chbxFacturar.isSelected()) {
        productos.setFacturar("Si");
      } else {
        productos.setFacturar("No");
      }
      if (productos.getUnidadDeMedida().isEmpty())
      {
        JOptionPane.showMessageDialog(null, "Seleccione una unidad de medida");
        return;
      }
      DaoProductos.EditarProducto(productos);
      dispose();
      Productos.btnActualizar.doClick();
    }
  }
  
  private void Salir(WindowEvent evt)
  {
    if (this.btnGuardar.isEnabled())
    {
      int res = JOptionPane.showConfirmDialog(null, "A�n no se guard� los cambios. �Est� seguro que desea salir?", "Se ha hecho cambios", 0);
      if (res == 0) {
        dispose();
      }
    }
    else
    {
      dispose();
    }
  }
  
  private void ReconocerCambios()
  {
    if (this.tfDescripcion.getText().trim().length() > 0) {
      this.btnGuardar.setEnabled(true);
    }
  }    
}
