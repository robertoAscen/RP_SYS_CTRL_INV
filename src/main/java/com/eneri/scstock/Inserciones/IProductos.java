/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Inserciones;

import com.eneri.scstock.Apariencia.Colores;
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
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author RAscencio
 */
public class IProductos extends JDialog
{
    
  private JTextField tfCodigo;
  private JTextField tfDescripcion;
  private JTextField tfPrecioCosto;
  private JTextField tfPrecioMayorista;
  private JTextField tfPrecioVenta;
  private JTextField tfPrecioCredito;
  private JTextField tfStock;
  private JCheckBox chbxUnidad;
  private JCheckBox chbxKilogramo;
  private JCheckBox chbxMetro;
  private JLabel lblValCodigo;
  private Productos producto;
  private JLabel lblUnidadDeMedida;
  private JTextField tfCantPaquete;
  private JLabel lblCantEnPaq;
  public static List<ModeloProveedores> listaProveedores;
  public static List<ModeloMarcas> listaMarcas;
  public static List<ModeloFamilia> listaFamilias;
  private JComboBox comboBoxProveedores;
  private JCheckBox chckbxFacturar;
  private JTabbedPane tabbedPane;
  private JTabbedPane tabbedPane_1;
  private JButton btnGuardar;
  private JLabel lblCodigo;
  private JLabel lblDescripcin;
  private JLabel lblStock;
  private JLabel lblPrecioMayorista;
  private JLabel lblPrecioCosto;
  private JLabel lblPrecioVenta;
  private JLabel lblPrecioCredito;
  private JLabel lblProveedor;
  private JTextField tfColor;
  private JCheckBox chbxCaja;
  private JLabel lblValDescripcio;
  private JLabel lblValPrecioCosto;
  private JLabel lblValPrecioMayorista;
  private JLabel lblValPrecioVenta;
  private JLabel lblValPrecioCredito;
  private JCheckBox chbxMt2;
  private JTextField tfIva;
  private JLabel lblIva;
  private JLabel label;
  private JLabel lblEstante;
  private JTextField tfEstante;
  private JLabel lblIvaValidate;
  private JLabel lblObservaciones;
  private JTextField tfObservaciones;
  private JComboBox cbxFamilias;
  private JComboBox cbxMarcas;
  private double iva1 = DaoConfiguraciones.Iva1();
  private double iva2 = DaoConfiguraciones.Iva2();
  private JTextField tfDescuento;
  private JLabel label_4;
  
  public static void main(String[] args)
  {
    try
    {
      IProductos dialog = new IProductos();
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
  
  public IProductos()
  {
    setIconImage(Toolkit.getDefaultToolkit().getImage(IProductos.class.getResource("/Iconos/nuevoProducto.png")));
    setTitle("Insertar nuevo producto");
    setBounds(100, 100, 695, 458);
    setResizable(false);
    
    FondoSubFormularios contentPane = new FondoSubFormularios();
    contentPane.setForeground(Color.BLACK);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    this.lblCodigo = new JLabel(" C�digo:");
    this.lblCodigo.setBounds(88, 11, 74, 18);
    this.lblCodigo.setForeground(Color.WHITE);
    this.lblCodigo.setFont(new Font("Tahoma", 0, 15));
    getContentPane().add(this.lblCodigo);
    
    this.tfCodigo = new JTextField();
    this.tfCodigo.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent arg0)
      {
        if (arg0.getKeyCode() == 10) {
          IProductos.this.comboBoxProveedores.requestFocus();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if ((IProductos.this.lblValCodigo.isVisible()) && (IProductos.this.tfCodigo.getText().length() > 0)) {
          IProductos.this.lblValCodigo.setVisible(false);
        }
      }
    });
    Validaciones validar = new Validaciones();
    validar.BloqueoAlfabetico(this.tfCodigo);
    this.tfCodigo.setBounds(148, 11, 131, 21);
    this.tfCodigo.setToolTipText("C�digo del producto");
    this.tfCodigo.setFont(new Font("Tahoma", 0, 15));
    this.tfCodigo.setColumns(10);
    getContentPane().add(this.tfCodigo);
    
    this.lblDescripcin = new JLabel(" Descripci�n:");
    this.lblDescripcin.setBounds(58, 58, 103, 17);
    this.lblDescripcin.setForeground(Color.WHITE);
    this.lblDescripcin.setFont(new Font("Tahoma", 0, 15));
    getContentPane().add(this.lblDescripcin);
    
    this.tfDescripcion = new JTextField();
    this.tfDescripcion.setToolTipText("Ingrese la descripci�n del producto");
    this.tfDescripcion.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          IProductos.this.tfPrecioCosto.requestFocus();
        }
      }
      
      public void keyReleased(KeyEvent evento)
      {
        if (IProductos.this.tfDescripcion.getText().length() > 0) {
          IProductos.this.lblValDescripcio.setVisible(false);
        }
      }
    });
    this.tfDescripcion.setBounds(147, 56, 509, 21);
    this.tfDescripcion.setFont(new Font("Tahoma", 0, 15));
    this.tfDescripcion.setColumns(10);
    getContentPane().add(this.tfDescripcion);
    
    this.lblStock = new JLabel(" Stock:");
    this.lblStock.setBounds(20, 366, 74, 14);
    this.lblStock.setForeground(Color.WHITE);
    this.lblStock.setFont(new Font("Tahoma", 0, 15));
    getContentPane().add(this.lblStock);
    
    this.btnGuardar = new JButton("Guardar");
    this.btnGuardar.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          IProductos.this.RegistrarNuevoProducto();
        }
      }
    });
    this.btnGuardar.setMnemonic('G');
    this.btnGuardar.setBounds(514, 353, 165, 71);
    this.btnGuardar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        IProductos.this.RegistrarNuevoProducto();
      }
    });
    this.btnGuardar.setIcon(new ImageIcon(IProductos.class.getResource("/Iconos/Guardar.png")));
    this.btnGuardar.setToolTipText("Guardar datos");
    this.btnGuardar.setFont(new Font("Tahoma", 0, 15));
    getContentPane().add(this.btnGuardar);
    
    this.tfStock = new JTextField();
    this.tfStock.setToolTipText("Ingrese la cantidad existente del producto");
    this.tfStock.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10)
        {
          IProductos.this.tfCantPaquete.requestFocus();
          IProductos.this.tfCantPaquete.selectAll();
        }
      }
    });
    validar.BloqueoAlfabetico(this.tfStock);
    this.tfStock.setBounds(82, 363, 82, 21);
    this.tfStock.setFont(new Font("Tahoma", 0, 15));
    this.tfStock.setColumns(10);
    getContentPane().add(this.tfStock);
    
    this.lblProveedor = new JLabel("Proveedor:");
    this.lblProveedor.setBounds(353, 6, 91, 23);
    this.lblProveedor.setForeground(Color.WHITE);
    this.lblProveedor.setFont(new Font("Tahoma", 0, 15));
    getContentPane().add(this.lblProveedor);
    
    this.lblPrecioCosto = new JLabel("Precio de costo:");
    this.lblPrecioCosto.setBounds(41, 106, 122, 18);
    contentPane.add(this.lblPrecioCosto);
    this.lblPrecioCosto.setForeground(Color.WHITE);
    this.lblPrecioCosto.setFont(new Font("Tahoma", 0, 15));
    
    this.tfPrecioCosto = new JTextField();
    this.tfPrecioCosto.setToolTipText("Ingrese el precio de costo del producto");
    this.tfPrecioCosto.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          IProductos.this.CalcularPrecios();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (IProductos.this.tfPrecioCosto.getText().trim().length() != 0) {
          IProductos.this.lblValPrecioCosto.setVisible(false);
        }
      }
    });
    validar.ValidarFormatoPrecio(this.tfPrecioCosto);
    this.tfPrecioCosto.setBounds(149, 105, 136, 21);
    contentPane.add(this.tfPrecioCosto);
    this.tfPrecioCosto.setFont(new Font("Tahoma", 0, 15));
    this.tfPrecioCosto.setColumns(10);
    
    this.tfPrecioMayorista = new JTextField();
    this.tfPrecioMayorista.setEditable(false);
    this.tfPrecioMayorista.setToolTipText("Ingrese el precio para mayoristas del producto");
    this.tfPrecioMayorista.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10)
        {
          String preciosup = IProductos.this.tfPrecioMayorista.getText();
          IProductos.this.tfPrecioVenta.setText(preciosup);
          IProductos.this.tfPrecioVenta.requestFocus();
          IProductos.this.tfPrecioVenta.selectAll();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (IProductos.this.tfPrecioMayorista.getText().trim().length() != 0) {
          IProductos.this.lblValPrecioMayorista.setVisible(false);
        }
      }
    });
    validar.BloqueoAlfabetico(this.tfPrecioMayorista);
    this.tfPrecioMayorista.setBounds(148, 145, 136, 21);
    contentPane.add(this.tfPrecioMayorista);
    this.tfPrecioMayorista.setFont(new Font("Tahoma", 0, 15));
    this.tfPrecioMayorista.setColumns(10);
    
    this.lblPrecioMayorista = new JLabel("Precio mayorista:");
    this.lblPrecioMayorista.setBounds(30, 147, 128, 17);
    contentPane.add(this.lblPrecioMayorista);
    this.lblPrecioMayorista.setForeground(Color.WHITE);
    this.lblPrecioMayorista.setFont(new Font("Tahoma", 0, 15));
    
    this.lblPrecioVenta = new JLabel(" Precio de venta:");
    this.lblPrecioVenta.setBounds(30, 189, 122, 14);
    contentPane.add(this.lblPrecioVenta);
    this.lblPrecioVenta.setForeground(Color.WHITE);
    this.lblPrecioVenta.setFont(new Font("Tahoma", 0, 15));
    
    this.tfPrecioVenta = new JTextField();
    this.tfPrecioVenta.setEditable(false);
    this.tfPrecioVenta.setToolTipText("Ingrese el precio de venta del producto (con I.V.A.)");
    this.tfPrecioVenta.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10)
        {
          String preciosup = IProductos.this.tfPrecioVenta.getText();
          IProductos.this.tfPrecioCredito.setText(preciosup);
          IProductos.this.tfPrecioCredito.requestFocus();
          IProductos.this.tfPrecioCredito.selectAll();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (IProductos.this.tfPrecioVenta.getText().trim().length() != 0) {
          IProductos.this.lblValPrecioVenta.setVisible(false);
        }
      }
    });
    validar.BloqueoAlfabetico(this.tfPrecioVenta);
    this.tfPrecioVenta.setBounds(148, 186, 136, 21);
    contentPane.add(this.tfPrecioVenta);
    this.tfPrecioVenta.setFont(new Font("Tahoma", 0, 15));
    this.tfPrecioVenta.setColumns(10);
    
    this.tfPrecioCredito = new JTextField();
    this.tfPrecioCredito.setEditable(false);
    this.tfPrecioCredito.setToolTipText("Ingrese el precio a cr�dito del producto");
    this.tfPrecioCredito.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10)
        {
          IProductos.this.tfIva.selectAll();
          IProductos.this.tfIva.requestFocus();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (IProductos.this.tfPrecioCredito.getText().trim().length() != 0) {
          IProductos.this.lblValPrecioCredito.setVisible(false);
        }
      }
    });
    validar.BloqueoAlfabetico(this.tfPrecioCredito);
    this.tfPrecioCredito.setBounds(148, 225, 137, 21);
    contentPane.add(this.tfPrecioCredito);
    this.tfPrecioCredito.setFont(new Font("Tahoma", 0, 15));
    this.tfPrecioCredito.setColumns(10);
    
    JLabel label_1 = new JLabel("");
    label_1.setBounds(282, 11, 40, 21);
    label_1.setIcon(new ImageIcon(IProductos.class.getResource("/Galeria/NuevoReg.gif")));
    contentPane.add(label_1);
    
    this.lblPrecioCredito = new JLabel("    Precio cr�dito:");
    this.lblPrecioCredito.setBounds(31, 228, 122, 14);
    contentPane.add(this.lblPrecioCredito);
    this.lblPrecioCredito.setForeground(Color.WHITE);
    this.lblPrecioCredito.setFont(new Font("Tahoma", 0, 15));
    
    this.chbxUnidad = new JCheckBox("Unidad");
    this.chbxUnidad.setForeground(Color.WHITE);
    this.chbxUnidad.setOpaque(false);
    this.chbxUnidad.setToolTipText("Unidad de medida en unidades");
    this.chbxUnidad.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          IProductos.this.chbxMetro.requestFocus();
        }
      }
    });
    this.chbxUnidad.setMnemonic('U');
    this.chbxUnidad.setBounds(353, 137, 125, 23);
    contentPane.add(this.chbxUnidad);
    this.chbxUnidad.setSelected(true);
    
    this.chbxMetro = new JCheckBox("Metro");
    this.chbxMetro.setForeground(Color.WHITE);
    this.chbxMetro.setOpaque(false);
    this.chbxMetro.setToolTipText("Unidad de medida en metros");
    this.chbxMetro.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          IProductos.this.chbxKilogramo.requestFocus();
        }
      }
    });
    this.chbxMetro.setMnemonic('M');
    this.chbxMetro.setBounds(353, 158, 125, 23);
    contentPane.add(this.chbxMetro);
    
    this.chbxKilogramo = new JCheckBox("Kilogramo");
    this.chbxKilogramo.setForeground(Color.WHITE);
    this.chbxKilogramo.setOpaque(false);
    this.chbxKilogramo.setToolTipText("Unidad de medida en kilogramos");
    this.chbxKilogramo.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          IProductos.this.chbxCaja.requestFocus();
        }
      }
    });
    this.chbxKilogramo.setMnemonic('K');
    this.chbxKilogramo.setBounds(353, 181, 125, 21);
    contentPane.add(this.chbxKilogramo);
    
    this.lblValCodigo = new JLabel("Ingrese el c�digo del producto");
    this.lblValCodigo.setVisible(false);
    this.lblValCodigo.setBounds(148, 30, 305, 14);
    contentPane.add(this.lblValCodigo);
    
    this.lblUnidadDeMedida = new JLabel("Unidad de medida");
    this.lblUnidadDeMedida.setForeground(Color.WHITE);
    this.lblUnidadDeMedida.setFont(new Font("Tahoma", 0, 12));
    this.lblUnidadDeMedida.setBounds(353, 116, 125, 14);
    contentPane.add(this.lblUnidadDeMedida);
    
    this.tfCantPaquete = new JTextField();
    this.tfCantPaquete.setText("1");
    this.tfCantPaquete.setToolTipText("Ingrese la cantidad por paquete (o cajas) del producto");
    this.tfCantPaquete.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10)
        {
          IProductos.this.tfEstante.requestFocus();
          IProductos.this.tfEstante.selectAll();
        }
      }
    });
    validar.BloqueoAlfabetico(this.tfCantPaquete);
    this.tfCantPaquete.setFont(new Font("Tahoma", 0, 15));
    this.tfCantPaquete.setColumns(10);
    this.tfCantPaquete.setBounds(282, 363, 62, 21);
    contentPane.add(this.tfCantPaquete);
    
    this.lblCantEnPaq = new JLabel(" Cant. en paq.:");
    this.lblCantEnPaq.setForeground(Color.WHITE);
    this.lblCantEnPaq.setFont(new Font("Tahoma", 0, 15));
    this.lblCantEnPaq.setBounds(169, 365, 153, 17);
    contentPane.add(this.lblCantEnPaq);
    
    this.comboBoxProveedores = new JComboBox();
    this.comboBoxProveedores.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent ev)
      {
        if (ev.getKeyCode() == 10) {
          IProductos.this.tfDescripcion.requestFocus();
        }
      }
    });
    this.comboBoxProveedores.setToolTipText("Seleccione el proveedor del producto");
    
    this.comboBoxProveedores.setBounds(428, 8, 228, 21);
    contentPane.add(this.comboBoxProveedores);
    
    JLabel label_2 = new JLabel("");
    label_2.setIcon(new ImageIcon(IProductos.class.getResource("/Galeria/nuevoProducto.png")));
    label_2.setForeground(Color.BLACK);
    label_2.setFont(new Font("Tahoma", 0, 12));
    label_2.setBounds(500, 130, 153, 137);
    contentPane.add(label_2);
    
    this.chckbxFacturar = new JCheckBox("Facturar sin stock");
    this.chckbxFacturar.setForeground(Color.WHITE);
    this.chckbxFacturar.setOpaque(false);
    this.chckbxFacturar.setToolTipText("Si guarda \"Facturar sin stock\" el sistema omitir� el \"Stock agotado\" y facturar� de todas formas.");
    this.chckbxFacturar.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          IProductos.this.cbxFamilias.requestFocus();
        }
      }
    });
    this.chckbxFacturar.setMnemonic('F');
    this.chckbxFacturar.setBounds(353, 253, 141, 20);
    contentPane.add(this.chckbxFacturar);
    
    this.tfColor = new JTextField();
    this.tfColor.setVisible(false);
    this.tfColor.setBounds(12, 9, 62, 20);
    contentPane.add(this.tfColor);
    this.tfColor.setColumns(10);
    
    this.chbxCaja = new JCheckBox("Caja");
    this.chbxCaja.setForeground(Color.WHITE);
    this.chbxCaja.setOpaque(false);
    this.chbxCaja.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          IProductos.this.chbxMt2.requestFocus();
        }
      }
    });
    this.chbxCaja.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (IProductos.this.chbxCaja.isSelected())
        {
          IProductos.this.chbxMetro.setSelected(false);
          IProductos.this.chbxUnidad.setSelected(false);
          IProductos.this.chbxKilogramo.setSelected(false);
          IProductos.this.chbxMt2.setSelected(false);
        }
      }
    });
    this.chbxCaja.setToolTipText("Unidad de medida en cajas");
    this.chbxCaja.setMnemonic('C');
    this.chbxCaja.setBounds(353, 202, 125, 21);
    contentPane.add(this.chbxCaja);
    
    this.lblValDescripcio = new JLabel("Ingrese la descripci�n del producto");
    this.lblValDescripcio.setVisible(false);
    this.lblValDescripcio.setForeground(Color.RED);
    this.lblValDescripcio.setBounds(148, 74, 296, 21);
    contentPane.add(this.lblValDescripcio);
    
    this.lblValPrecioCosto = new JLabel("Ingrese el precio de costo");
    this.lblValPrecioCosto.setVisible(false);
    this.lblValPrecioCosto.setForeground(Color.RED);
    this.lblValPrecioCosto.setBounds(148, 128, 156, 14);
    contentPane.add(this.lblValPrecioCosto);
    
    this.lblValPrecioMayorista = new JLabel("Ingrese el precio mayorista");
    this.lblValPrecioMayorista.setVisible(false);
    this.lblValPrecioMayorista.setForeground(Color.RED);
    this.lblValPrecioMayorista.setBounds(148, 168, 156, 18);
    contentPane.add(this.lblValPrecioMayorista);
    
    this.lblValPrecioVenta = new JLabel("Ingrese el precio de venta");
    this.lblValPrecioVenta.setVisible(false);
    this.lblValPrecioVenta.setForeground(Color.RED);
    this.lblValPrecioVenta.setBounds(148, 205, 156, 18);
    contentPane.add(this.lblValPrecioVenta);
    
    this.lblValPrecioCredito = new JLabel("Ingrese el precio cr�dito");
    this.lblValPrecioCredito.setVisible(false);
    this.lblValPrecioCredito.setForeground(Color.RED);
    this.lblValPrecioCredito.setBounds(148, 246, 156, 21);
    contentPane.add(this.lblValPrecioCredito);
    
    this.chbxMt2 = new JCheckBox("Mt2");
    this.chbxMt2.setForeground(Color.WHITE);
    this.chbxMt2.setOpaque(false);
    this.chbxMt2.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          IProductos.this.chckbxFacturar.requestFocus();
        }
      }
    });
    this.chbxMt2.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (IProductos.this.chbxMt2.isSelected())
        {
          IProductos.this.chbxMetro.setSelected(false);
          IProductos.this.chbxUnidad.setSelected(false);
          IProductos.this.chbxCaja.setSelected(false);
          IProductos.this.chbxKilogramo.setSelected(false);
        }
      }
    });
    this.chbxMt2.setToolTipText("Unidad de medida en metro cuadrado");
    this.chbxMt2.setMnemonic('C');
    this.chbxMt2.setBounds(353, 220, 125, 21);
    contentPane.add(this.chbxMt2);
    
    this.tfIva = new JTextField();
    this.tfIva.setText("10");
    this.tfIva.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          IProductos.this.tfDescuento.requestFocus();
          IProductos.this.tfDescuento.selectAll();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (IProductos.this.tfIva.getText().trim().length() > 0) {
          IProductos.this.lblIvaValidate.setVisible(false);
        }
      }
    });
    validar.BloqueoAlfabetico(this.tfIva);
    this.tfIva.setToolTipText("Ingrese el precio a cr�dito del producto");
    this.tfIva.setFont(new Font("Tahoma", 0, 15));
    this.tfIva.setColumns(10);
    this.tfIva.setBounds(148, 267, 66, 21);
    contentPane.add(this.tfIva);
    
    this.lblIva = new JLabel("I.V.A:");
    this.lblIva.setForeground(Color.WHITE);
    this.lblIva.setFont(new Font("Tahoma", 0, 15));
    this.lblIva.setBounds(99, 270, 66, 14);
    contentPane.add(this.lblIva);
    
    this.label = new JLabel("%");
    this.label.setForeground(Color.WHITE);
    this.label.setFont(new Font("Tahoma", 0, 15));
    this.label.setBounds(224, 268, 40, 19);
    contentPane.add(this.label);
    
    this.lblEstante = new JLabel("Estante:");
    this.lblEstante.setForeground(Color.WHITE);
    this.lblEstante.setFont(new Font("Tahoma", 0, 15));
    this.lblEstante.setBounds(351, 366, 74, 14);
    contentPane.add(this.lblEstante);
    
    this.tfEstante = new JTextField();
    this.tfEstante.setText("0");
    this.tfEstante.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          IProductos.this.tfObservaciones.requestFocus();
        }
      }
      
      public void keyTyped(KeyEvent e)
      {
        if (IProductos.this.tfEstante.getText().trim().length() > 4) {
          e.consume();
        }
      }
    });
    validar.BloqueoAlfabetico(this.tfEstante);
    this.tfEstante.setToolTipText("Ingrese el precio a cr�dito del producto");
    this.tfEstante.setFont(new Font("Tahoma", 0, 15));
    this.tfEstante.setColumns(10);
    this.tfEstante.setBounds(412, 363, 82, 21);
    contentPane.add(this.tfEstante);
    
    this.lblIvaValidate = new JLabel("Ingrese el I.V.A.");
    this.lblIvaValidate.setVisible(false);
    this.lblIvaValidate.setForeground(Color.RED);
    this.lblIvaValidate.setBounds(148, 284, 193, 23);
    contentPane.add(this.lblIvaValidate);
    
    JLabel lblMarca = new JLabel("Marca:");
    lblMarca.setForeground(Color.WHITE);
    lblMarca.setFont(new Font("Tahoma", 0, 15));
    lblMarca.setBounds(363, 306, 91, 23);
    contentPane.add(lblMarca);
    
    this.cbxMarcas = new JComboBox();
    this.cbxMarcas.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          IProductos.this.btnGuardar.requestFocus();
        }
      }
    });
    this.cbxMarcas.setToolTipText("Seleccione la marca del producto");
    this.cbxMarcas.setBounds(414, 309, 223, 21);
    contentPane.add(this.cbxMarcas);
    
    JLabel lblFamilia = new JLabel("Familia:");
    lblFamilia.setForeground(Color.WHITE);
    lblFamilia.setFont(new Font("Tahoma", 0, 15));
    lblFamilia.setBounds(363, 278, 62, 23);
    contentPane.add(lblFamilia);
    
    this.cbxFamilias = new JComboBox();
    this.cbxFamilias.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          IProductos.this.cbxMarcas.requestFocus();
        }
      }
    });
    this.cbxFamilias.setToolTipText("Seleccione la familia del producto");
    this.cbxFamilias.setBounds(414, 280, 223, 21);
    contentPane.add(this.cbxFamilias);
    
    JTabbedPane tabbedPane_2 = new JTabbedPane(1);
    tabbedPane_2.setBounds(327, 96, 352, 246);
    contentPane.add(tabbedPane_2);
    
    this.tabbedPane = new JTabbedPane(1);
    this.tabbedPane.setBounds(12, 96, 316, 246);
    contentPane.add(this.tabbedPane);
    
    JTabbedPane tabbedPane_3 = new JTabbedPane(1);
    tabbedPane_3.setBounds(10, 2, 669, 93);
    contentPane.add(tabbedPane_3);
    
    this.lblObservaciones = new JLabel("Obs.:");
    this.lblObservaciones.setForeground(Color.WHITE);
    this.lblObservaciones.setFont(new Font("Tahoma", 0, 15));
    this.lblObservaciones.setBounds(30, 395, 62, 14);
    contentPane.add(this.lblObservaciones);
    
    this.tfObservaciones = new JTextField();
    this.tfObservaciones.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          IProductos.this.chbxUnidad.requestFocus();
        }
      }
    });
    this.tfObservaciones.setToolTipText("Observaciones del producto");
    this.tfObservaciones.setFont(new Font("Tahoma", 0, 15));
    this.tfObservaciones.setColumns(10);
    this.tfObservaciones.setBounds(82, 394, 412, 21);
    contentPane.add(this.tfObservaciones);
    
    this.tabbedPane_1 = new JTabbedPane(1);
    this.tabbedPane_1.setBounds(10, 353, 494, 71);
    contentPane.add(this.tabbedPane_1);
    
    JLabel lblDescuento = new JLabel("Descuento:");
    lblDescuento.setForeground(Color.WHITE);
    lblDescuento.setFont(new Font("Tahoma", 0, 15));
    lblDescuento.setBounds(62, 311, 103, 14);
    contentPane.add(lblDescuento);
    
    this.tfDescuento = new JTextField();
    this.tfDescuento.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          IProductos.this.tfStock.requestFocus();
          IProductos.this.tfStock.selectAll();
        }
      }
    });
    validar.ValidarFormatoPrecio(this.tfDescuento);
    this.tfDescuento.setToolTipText("Ingrese el precio a cr�dito del producto");
    this.tfDescuento.setText("0");
    this.tfDescuento.setFont(new Font("Tahoma", 0, 15));
    this.tfDescuento.setColumns(10);
    this.tfDescuento.setBounds(148, 308, 66, 21);
    contentPane.add(this.tfDescuento);
    
    this.label_4 = new JLabel("%");
    this.label_4.setForeground(Color.WHITE);
    this.label_4.setFont(new Font("Tahoma", 0, 15));
    this.label_4.setBounds(224, 310, 40, 18);
    contentPane.add(this.label_4);
    
    this.chbxKilogramo.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (IProductos.this.chbxKilogramo.isSelected())
        {
          IProductos.this.chbxMetro.setSelected(false);
          IProductos.this.chbxUnidad.setSelected(false);
          IProductos.this.chbxCaja.setSelected(false);
          IProductos.this.chbxMt2.setSelected(false);
        }
      }
    });
    this.chbxMetro.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (IProductos.this.chbxMetro.isSelected())
        {
          IProductos.this.chbxUnidad.setSelected(false);
          IProductos.this.chbxKilogramo.setSelected(false);
          IProductos.this.chbxCaja.setSelected(false);
          IProductos.this.chbxMt2.setSelected(false);
        }
      }
    });
    this.chbxUnidad.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (IProductos.this.chbxUnidad.isSelected())
        {
          IProductos.this.chbxMetro.setSelected(false);
          IProductos.this.chbxKilogramo.setSelected(false);
          IProductos.this.chbxCaja.setSelected(false);
          IProductos.this.chbxMt2.setSelected(false);
        }
      }
    });
    cargarCBXProveedores();
    cargarCBXMarcas();
    cargarCBXFamilias();
    ColorBlanco();
  }
  
  private void CapturarColor()
  {
    String codigocolor = "1";
    Colores color = Colores.ConsutarColorSubFormularios(codigocolor);
    this.tfColor.setText(color.getColor());
  }
  
  private void DetectarColor()
  {
    if (this.tfColor.getText().equals("Amarillo")) {
      ColorAmarillo();
    }
    if (this.tfColor.getText().equals("Azul")) {
      ColorAzul();
    }
    if (this.tfColor.getText().equals("Cyan")) {
      ColorCyan();
    }
    if (this.tfColor.getText().equals("Gris")) {
      ColorGris();
    }
    if (this.tfColor.getText().equals("Naranja")) {
      ColorNaranja();
    }
    if (this.tfColor.getText().equals("Negro")) {
      ColorNegro();
    }
    if (this.tfColor.getText().equals("P�rpura")) {
      ColorPurpura();
    }
    if (this.tfColor.getText().equals("Rojo")) {
      ColorRojo();
    }
    if (this.tfColor.getText().equals("Marr�n")) {
      ColorMarron();
    }
    if (this.tfColor.getText().equals("Verde fluor")) {
      ColorVerdeFluor();
    }
    if (this.tfColor.getText().equals("Verde")) {
      ColorVerde();
    }
    if (this.tfColor.getText().equals("Blanco")) {
      ColorBlanco();
    }
  }
  
  private void ColorRojo()
  {
    this.lblCodigo.setForeground(Color.RED);
    this.lblDescripcin.setForeground(Color.RED);
    this.lblCantEnPaq.setForeground(Color.RED);
    this.lblPrecioCosto.setForeground(Color.RED);
    this.lblPrecioCredito.setForeground(Color.RED);
    this.lblPrecioMayorista.setForeground(Color.RED);
    this.lblPrecioVenta.setForeground(Color.RED);
    this.lblProveedor.setForeground(Color.RED);
    this.lblStock.setForeground(Color.RED);
    this.lblUnidadDeMedida.setForeground(Color.RED);
  }
  
  private void ColorBlanco()
  {
    this.lblCodigo.setForeground(Color.WHITE);
    this.lblDescripcin.setForeground(Color.WHITE);
    this.lblCantEnPaq.setForeground(Color.WHITE);
    this.lblPrecioCosto.setForeground(Color.WHITE);
    this.lblPrecioCredito.setForeground(Color.WHITE);
    this.lblPrecioMayorista.setForeground(Color.WHITE);
    this.lblPrecioVenta.setForeground(Color.WHITE);
    this.lblProveedor.setForeground(Color.WHITE);
    this.lblStock.setForeground(Color.WHITE);
    this.lblUnidadDeMedida.setForeground(Color.WHITE);
  }
  
  private void ColorGris()
  {
    this.lblCodigo.setForeground(Color.GRAY);
    this.lblDescripcin.setForeground(Color.GRAY);
    this.lblCantEnPaq.setForeground(Color.GRAY);
    this.lblPrecioCosto.setForeground(Color.GRAY);
    this.lblPrecioCredito.setForeground(Color.GRAY);
    this.lblPrecioMayorista.setForeground(Color.GRAY);
    this.lblPrecioVenta.setForeground(Color.GRAY);
    this.lblProveedor.setForeground(Color.GRAY);
    this.lblStock.setForeground(Color.GRAY);
    this.lblUnidadDeMedida.setForeground(Color.GRAY);
  }
  
  private void ColorAmarillo()
  {
    this.lblCodigo.setForeground(Color.YELLOW);
    this.lblDescripcin.setForeground(Color.YELLOW);
    this.lblCantEnPaq.setForeground(Color.YELLOW);
    this.lblPrecioCosto.setForeground(Color.YELLOW);
    this.lblPrecioCredito.setForeground(Color.YELLOW);
    this.lblPrecioMayorista.setForeground(Color.YELLOW);
    this.lblPrecioVenta.setForeground(Color.YELLOW);
    this.lblProveedor.setForeground(Color.YELLOW);
    this.lblStock.setForeground(Color.YELLOW);
    this.lblUnidadDeMedida.setForeground(Color.YELLOW);
  }
  
  private void ColorVerde()
  {
    this.lblCodigo.setForeground(new Color(0, 128, 0));
    this.lblDescripcin.setForeground(new Color(0, 128, 0));
    this.lblCantEnPaq.setForeground(new Color(0, 128, 0));
    this.lblPrecioCosto.setForeground(new Color(0, 128, 0));
    this.lblPrecioCredito.setForeground(new Color(0, 128, 0));
    this.lblPrecioMayorista.setForeground(new Color(0, 128, 0));
    this.lblPrecioVenta.setForeground(new Color(0, 128, 0));
    this.lblProveedor.setForeground(new Color(0, 128, 0));
    this.lblStock.setForeground(new Color(0, 128, 0));
    this.lblUnidadDeMedida.setForeground(new Color(0, 128, 0));
  }
  
  private void ColorAzul()
  {
    this.lblCodigo.setForeground(new Color(0, 0, 205));
    this.lblDescripcin.setForeground(new Color(0, 0, 205));
    this.lblCantEnPaq.setForeground(new Color(0, 0, 205));
    this.lblPrecioCosto.setForeground(new Color(0, 0, 205));
    this.lblPrecioCredito.setForeground(new Color(0, 0, 205));
    this.lblPrecioMayorista.setForeground(new Color(0, 0, 205));
    this.lblPrecioVenta.setForeground(new Color(0, 0, 205));
    this.lblProveedor.setForeground(new Color(0, 0, 205));
    this.lblStock.setForeground(new Color(0, 0, 205));
    this.lblUnidadDeMedida.setForeground(new Color(0, 0, 205));
  }
  
  private void ColorMarron()
  {
    this.lblCodigo.setForeground(new Color(139, 69, 19));
    this.lblDescripcin.setForeground(new Color(139, 69, 19));
    this.lblCantEnPaq.setForeground(new Color(139, 69, 19));
    this.lblPrecioCosto.setForeground(new Color(139, 69, 19));
    this.lblPrecioCredito.setForeground(new Color(139, 69, 19));
    this.lblPrecioMayorista.setForeground(new Color(139, 69, 19));
    this.lblPrecioVenta.setForeground(new Color(139, 69, 19));
    this.lblProveedor.setForeground(new Color(139, 69, 19));
    this.lblStock.setForeground(new Color(139, 69, 19));
    this.lblUnidadDeMedida.setForeground(new Color(139, 69, 19));
  }
  
  private void ColorPurpura()
  {
    this.lblCodigo.setForeground(new Color(148, 0, 211));
    this.lblDescripcin.setForeground(new Color(148, 0, 211));
    this.lblCantEnPaq.setForeground(new Color(148, 0, 211));
    this.lblPrecioCosto.setForeground(new Color(148, 0, 211));
    this.lblPrecioCredito.setForeground(new Color(148, 0, 211));
    this.lblPrecioMayorista.setForeground(new Color(148, 0, 211));
    this.lblPrecioVenta.setForeground(new Color(148, 0, 211));
    this.lblProveedor.setForeground(new Color(148, 0, 211));
    this.lblStock.setForeground(new Color(148, 0, 211));
    this.lblUnidadDeMedida.setForeground(new Color(148, 0, 211));
  }
  
  private void ColorCyan()
  {
    this.lblCodigo.setForeground(Color.CYAN);
    this.lblDescripcin.setForeground(Color.CYAN);
    this.lblCantEnPaq.setForeground(Color.CYAN);
    this.lblPrecioCosto.setForeground(Color.CYAN);
    this.lblPrecioCredito.setForeground(Color.CYAN);
    this.lblPrecioMayorista.setForeground(Color.CYAN);
    this.lblPrecioVenta.setForeground(Color.CYAN);
    this.lblProveedor.setForeground(Color.CYAN);
    this.lblStock.setForeground(Color.CYAN);
    this.lblUnidadDeMedida.setForeground(Color.CYAN);
  }
  
  private void ColorNaranja()
  {
    this.lblCodigo.setForeground(Color.ORANGE);
    this.lblDescripcin.setForeground(Color.ORANGE);
    this.lblCantEnPaq.setForeground(Color.ORANGE);
    this.lblPrecioCosto.setForeground(Color.ORANGE);
    this.lblPrecioCredito.setForeground(Color.ORANGE);
    this.lblPrecioMayorista.setForeground(Color.ORANGE);
    this.lblPrecioVenta.setForeground(Color.ORANGE);
    this.lblProveedor.setForeground(Color.ORANGE);
    this.lblStock.setForeground(Color.ORANGE);
    this.lblUnidadDeMedida.setForeground(Color.ORANGE);
  }
  
  private void ColorVerdeFluor()
  {
    this.lblCodigo.setForeground(Color.GREEN);
    this.lblDescripcin.setForeground(Color.GREEN);
    this.lblCantEnPaq.setForeground(Color.GREEN);
    this.lblPrecioCosto.setForeground(Color.GREEN);
    this.lblPrecioCredito.setForeground(Color.GREEN);
    this.lblPrecioMayorista.setForeground(Color.GREEN);
    this.lblPrecioVenta.setForeground(Color.GREEN);
    this.lblProveedor.setForeground(Color.GREEN);
    this.lblStock.setForeground(Color.GREEN);
    this.lblUnidadDeMedida.setForeground(Color.GREEN);
  }
  
  private void ColorNegro()
  {
    this.lblCodigo.setForeground(Color.BLACK);
    this.lblDescripcin.setForeground(Color.BLACK);
    this.lblCantEnPaq.setForeground(Color.BLACK);
    this.lblPrecioCosto.setForeground(Color.BLACK);
    this.lblPrecioCredito.setForeground(Color.BLACK);
    this.lblPrecioMayorista.setForeground(Color.BLACK);
    this.lblPrecioVenta.setForeground(Color.BLACK);
    this.lblProveedor.setForeground(Color.BLACK);
    this.lblStock.setForeground(Color.BLACK);
    this.lblUnidadDeMedida.setForeground(Color.BLACK);
  }
  
  private void RegistrarNuevoProducto()
  {
    if (ValidarDatosDelProducto())
    {
      ModeloProductos productos = new ModeloProductos();
      productos.setCodigo(this.tfCodigo.getText());
      productos.setDescripcion(this.tfDescripcion.getText().toUpperCase());
      productos.setPrecioCosto(Double.parseDouble(this.tfPrecioCosto.getText()));
      productos.setPrecioCredito(Double.parseDouble(this.tfPrecioCredito.getText()));
      productos.setPrecioVenta(Double.parseDouble(this.tfPrecioVenta.getText()));
      productos.setPrecioMayorista(Double.parseDouble(this.tfPrecioMayorista.getText()));
      productos.setStock(Integer.parseInt(this.tfStock.getText()));
      productos.setCantPaquete(Integer.parseInt(this.tfCantPaquete.getText()));
      productos.setIva(Double.parseDouble(this.tfIva.getText()));
      productos.setEstante(this.tfEstante.getText());
      productos.setCodProveedor(Integer.parseInt(this.comboBoxProveedores.getSelectedItem().toString().split("-")[0]));
      productos.getFamilia().setCodigo(Integer.parseInt(this.cbxFamilias.getSelectedItem().toString().split("-")[0]));
      productos.getMarcas().setCodigo(Integer.parseInt(this.cbxMarcas.getSelectedItem().toString().split("-")[0]));
      productos.setDescripcion(this.tfDescripcion.getText().toUpperCase());
      productos.setDescuento(Double.parseDouble(this.tfDescuento.getText()));
      if (this.tfDescuento.getText().trim().length() < 1) {
        productos.setDescuento(0.0D);
      }
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
      if (this.chckbxFacturar.isSelected()) {
        productos.setFacturar("Si");
      } else {
        productos.setFacturar("No");
      }
      if (productos.getUnidadDeMedida().isEmpty())
      {
        JOptionPane.showMessageDialog(null, "Seleccione una unidad de medida");
        return;
      }
      if (productos.getUnidadDeMedida().trim().length() != 0)
      {
        DaoProductos.RegistrarProducto(productos);
        Productos.btnActualizar.doClick();
        dispose();
      }
    }
  }
  
  private boolean ValidarDatosDelProducto()
  {
    if (this.tfCodigo.getText().trim().isEmpty())
    {
      this.tfCodigo.requestFocus();
      this.lblValCodigo.setVisible(true);
      this.lblValCodigo.setForeground(Color.RED);
      return false;
    }
    if (this.tfDescripcion.getText().trim().isEmpty())
    {
      this.tfDescripcion.requestFocus();
      this.lblValDescripcio.setVisible(true);
      this.lblValDescripcio.setForeground(Color.RED);
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
    if (this.tfStock.getText().trim().isEmpty())
    {
      JOptionPane.showMessageDialog(null, "No se puede registrar un producto sin stock.");
      this.tfStock.setText("0");
      this.tfStock.requestFocus();
      this.tfStock.selectAll();
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
  
  private void cargarCBXProveedores()
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
}
