/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Inserciones;

import com.eneri.scstock.Apariencia.Colores;
import com.eneri.scstock.Apariencia.FondoSubFormularios;
import com.eneri.scstock.Formularios.Clientes;
import com.eneri.scstock.Herramientas.Validaciones;
import com.eneri.scstock.Modelos.ModeloClientes;
import com.eneri.scstock.Objetos.DaoClientes;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author RAscencio
 */
public class IClientes extends JDialog
{
    
  private JPanel contentPane;
  private JTextField tfCodigo;
  private JTextField tfNombre;
  private JButton btnGuardar;
  private JTextField tfCedula;
  private JTextField tfTelefono;
  private JTextField tfCelular;
  private JTextField tfEmail;
  private boolean existencia;
  private Clientes clientes;
  private JLabel lblCampoRequerido1;
  private JLabel lblCampoRequerido2;
  private JLabel label_1;
  private JLabel lblCompletelosdatos;
  private JTextField tfDVRUC;
  private JCheckBox chckbxInsertarDV;
  private JTextArea tfDireccion;
  private JScrollPane scrollPane;
  private JLabel lblNcedRuc;
  private JLabel label_2;
  private JLabel lblCodigo;
  private JTextField tfColor;
  private JLabel lblNombreYApellido;
  private JLabel lblTelfono;
  private JLabel lblTelfonoMvil;
  private JLabel lblEmail;
  private JLabel lblDireccin;
  private JLabel lblDescuento;
  private JTextField tfDescuento;
  private JCheckBox chckbxDescuento;
  private JLabel label;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          IClientes insertar = new IClientes();
          insertar.setLocationRelativeTo(null);
          insertar.setModal(true);
          insertar.setVisible(true);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }
  
  public IClientes()
  {
    setIconImage(Toolkit.getDefaultToolkit().getImage(IClientes.class.getResource("/Galeria/ClienteNuevo.png")));
    setTitle("Insertar nuevo cliente");
    setResizable(false);
    setDefaultCloseOperation(2);
    setBounds(100, 100, 695, 415);
    
    FondoSubFormularios contentPane = new FondoSubFormularios();
    contentPane.setForeground(Color.BLACK);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    this.btnGuardar = new JButton("Guardar");
    this.btnGuardar.setMnemonic('G');
    this.btnGuardar.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent arg0)
      {
        IClientes.this.InsertarNuevoCliente();
      }
    });
    this.btnGuardar.setIcon(new ImageIcon(IClientes.class.getResource("/Iconos/Guardar.png")));
    this.btnGuardar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        IClientes.this.InsertarNuevoCliente();
      }
    });
    this.btnGuardar.setToolTipText("Guardar datos");
    this.btnGuardar.setFont(new Font("Tahoma", 0, 15));
    this.btnGuardar.setBounds(529, 298, 150, 67);
    contentPane.add(this.btnGuardar);
    
    this.tfCodigo = new JTextField();
    this.tfCodigo.setToolTipText("C�digo del cliente");
    this.tfCodigo.setEnabled(false);
    this.tfCodigo.setFont(new Font("Tahoma", 0, 15));
    this.tfCodigo.setBounds(159, 11, 157, 21);
    contentPane.add(this.tfCodigo);
    this.tfCodigo.setColumns(10);
    
    this.tfNombre = new JTextField();
    this.tfNombre.setToolTipText("Ingrese el nombre del cliente");
    Validaciones validar = new Validaciones();
    validar.BloqueoNumerico(this.tfNombre);
    this.tfNombre.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          IClientes.this.tfCedula.requestFocus();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (IClientes.this.tfNombre.getText().trim().length() > 0)
        {
          IClientes.this.lblCampoRequerido1.setVisible(false);
          IClientes.this.lblCompletelosdatos.setForeground(Color.GREEN);
          IClientes.this.lblCompletelosdatos.setText("Pulse Guardar para registrar el cliente. Ya se ha ingresado el nombre.");
        }
        else
        {
          IClientes.this.lblCompletelosdatos.setForeground(Color.YELLOW);
          IClientes.this.lblCompletelosdatos.setText("Complete el dato requerido del cliente, el campo Nombre y Apellido est� vac�o.");
        }
      }
      
      public void keyTyped(KeyEvent consumir)
      {
        String caracter = IClientes.this.tfNombre.getText();
        if (caracter.trim().length() > 60) {
          consumir.consume();
        }
      }
    });
    this.tfNombre.setFont(new Font("Tahoma", 0, 15));
    this.tfNombre.setColumns(10);
    this.tfNombre.setBounds(159, 55, 343, 21);
    contentPane.add(this.tfNombre);
    
    this.tfCedula = new JTextField();
    this.tfCedula.setToolTipText("Ingrese el RUC o N� de c�dula del cliente");
    validar.BloqueoAlfabetico(this.tfCedula);
    this.tfCedula.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10)
        {
          IClientes.this.chckbxInsertarDV.requestFocus();
          IClientes.this.tfDVRUC.requestFocus();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (IClientes.this.tfCedula.getText().trim().length() > 0)
        {
          IClientes.this.lblCampoRequerido2.setVisible(false);
          IClientes.this.lblCompletelosdatos.setForeground(Color.GREEN);
          IClientes.this.lblCompletelosdatos.setText("Pulse Guardar para registrar el cliente. Ya se ha ingresado el dato requerido.");
        }
      }
      
      public void keyTyped(KeyEvent ke)
      {
        String caracter = IClientes.this.tfCedula.getText();
        if (caracter.trim().length() >= 8) {
          ke.consume();
        }
      }
    });
    this.tfCedula.setFont(new Font("Tahoma", 0, 15));
    this.tfCedula.setColumns(10);
    this.tfCedula.setBounds(159, 102, 150, 21);
    contentPane.add(this.tfCedula);
    
    this.tfTelefono = new JTextField();
    this.tfTelefono.setToolTipText("Ingrese el N� de tel�fono del cliente");
    validar.BloqueoAlfabetico(this.tfTelefono);
    this.tfTelefono.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          IClientes.this.chckbxDescuento.requestFocus();
        }
      }
      
      public void keyTyped(KeyEvent consumir)
      {
        String caracter = IClientes.this.tfTelefono.getText();
        if (caracter.trim().length() >= 20) {
          consumir.consume();
        }
      }
    });
    this.tfTelefono.setFont(new Font("Tahoma", 0, 15));
    this.tfTelefono.setColumns(10);
    this.tfTelefono.setBounds(159, 186, 189, 21);
    contentPane.add(this.tfTelefono);
    
    this.lblCodigo = new JLabel("   C�digo:");
    this.lblCodigo.setForeground(Color.WHITE);
    this.lblCodigo.setFont(new Font("Tahoma", 0, 15));
    this.lblCodigo.setBounds(96, 11, 74, 18);
    contentPane.add(this.lblCodigo);
    
    this.lblNombreYApellido = new JLabel(" Nombre y apellido:");
    this.lblNombreYApellido.setForeground(Color.WHITE);
    this.lblNombreYApellido.setFont(new Font("Tahoma", 0, 15));
    this.lblNombreYApellido.setBounds(31, 57, 142, 17);
    contentPane.add(this.lblNombreYApellido);
    
    this.lblNcedRuc = new JLabel("N�mero de c�dula:");
    this.lblNcedRuc.setForeground(Color.WHITE);
    this.lblNcedRuc.setFont(new Font("Tahoma", 0, 15));
    this.lblNcedRuc.setBounds(39, 105, 131, 14);
    contentPane.add(this.lblNcedRuc);
    
    this.lblTelfono = new JLabel("  Tel�fono:");
    this.lblTelfono.setForeground(Color.WHITE);
    this.lblTelfono.setFont(new Font("Tahoma", 0, 15));
    this.lblTelfono.setBounds(82, 189, 88, 14);
    contentPane.add(this.lblTelfono);
    
    this.lblTelfonoMvil = new JLabel(" Celular:");
    this.lblTelfonoMvil.setToolTipText("");
    this.lblTelfonoMvil.setForeground(Color.WHITE);
    this.lblTelfonoMvil.setFont(new Font("Tahoma", 0, 15));
    this.lblTelfonoMvil.setBounds(101, 146, 103, 14);
    contentPane.add(this.lblTelfonoMvil);
    
    this.tfCelular = new JTextField();
    this.tfCelular.setToolTipText("Ingrese el N� de celular del cliente");
    validar.BloqueoAlfabetico(this.tfCelular);
    this.tfCelular.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          IClientes.this.tfTelefono.requestFocus();
        }
      }
      
      public void keyTyped(KeyEvent consumir)
      {
        String caracter = IClientes.this.tfCelular.getText();
        if (caracter.trim().length() >= 20) {
          consumir.consume();
        }
      }
    });
    this.tfCelular.setFont(new Font("Tahoma", 0, 15));
    this.tfCelular.setColumns(10);
    this.tfCelular.setBounds(159, 142, 189, 21);
    contentPane.add(this.tfCelular);
    
    this.lblEmail = new JLabel("   E-mail:");
    this.lblEmail.setForeground(Color.WHITE);
    this.lblEmail.setFont(new Font("Tahoma", 0, 15));
    this.lblEmail.setBounds(96, 266, 88, 14);
    contentPane.add(this.lblEmail);
    
    this.tfEmail = new JTextField();
    this.tfEmail.setToolTipText("Ingrese el email del cliente");
    this.tfEmail.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          IClientes.this.tfDireccion.requestFocus();
        }
      }
      
      public void keyTyped(KeyEvent consumir)
      {
        String caracter = IClientes.this.tfEmail.getText();
        if (caracter.trim().length() >= 50) {
          consumir.consume();
        }
      }
    });
    this.tfEmail.setFont(new Font("Tahoma", 0, 15));
    this.tfEmail.setColumns(10);
    this.tfEmail.setBounds(160, 262, 341, 21);
    contentPane.add(this.tfEmail);
    
    this.lblDireccin = new JLabel("  Direcci�n:");
    this.lblDireccin.setForeground(Color.WHITE);
    this.lblDireccin.setFont(new Font("Tahoma", 0, 15));
    this.lblDireccin.setBounds(82, 291, 117, 26);
    contentPane.add(this.lblDireccin);
    
    int codigo = DaoClientes.IncrementarCodigo() + 1;
    this.tfCodigo.setText(String.valueOf(codigo));
    
    this.lblCampoRequerido1 = new JLabel("*");
    this.lblCampoRequerido1.setForeground(new Color(255, 0, 0));
    this.lblCampoRequerido1.setFont(new Font("Tahoma", 1, 20));
    this.lblCampoRequerido1.setBounds(512, 62, 52, 14);
    this.lblCampoRequerido1.setVisible(false);
    contentPane.add(this.lblCampoRequerido1);
    
    this.lblCampoRequerido2 = new JLabel("*");
    this.lblCampoRequerido2.setForeground(new Color(255, 0, 0));
    this.lblCampoRequerido2.setFont(new Font("Tahoma", 1, 20));
    this.lblCampoRequerido2.setBounds(354, 107, 22, 21);
    this.lblCampoRequerido2.setVisible(false);
    contentPane.add(this.lblCampoRequerido2);
    
    this.label_1 = new JLabel("");
    this.label_1.setIcon(new ImageIcon(IClientes.class.getResource("/Galeria/ClienteNuevo.png")));
    this.label_1.setBounds(510, 99, 142, 127);
    contentPane.add(this.label_1);
    
    this.lblCompletelosdatos = new JLabel("");
    this.lblCompletelosdatos.setVisible(false);
    this.lblCompletelosdatos.setForeground(Color.YELLOW);
    this.lblCompletelosdatos.setBounds(159, 363, 523, 26);
    contentPane.add(this.lblCompletelosdatos);
    
    this.tfDVRUC = new JTextField();
    validar.BloqueoAlfabetico(this.tfDVRUC);
    this.tfDVRUC.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          IClientes.this.tfCelular.requestFocus();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (IClientes.this.tfDVRUC.getText().trim().length() != 0)
        {
          IClientes.this.lblCampoRequerido2.setVisible(false);
          IClientes.this.lblCompletelosdatos.setForeground(Color.GREEN);
          IClientes.this.lblCompletelosdatos.setText("Pulse Guardar para registrar el cliente. Ya se ha ingresado el dato requerido.");
          IClientes.this.lblCampoRequerido2.setVisible(false);
        }
      }
      
      public void keyTyped(KeyEvent ke)
      {
        String caracter = IClientes.this.tfDVRUC.getText();
        if (caracter.trim().length() >= 1) {
          ke.consume();
        }
      }
    });
    this.tfDVRUC.setVisible(false);
    this.tfDVRUC.setFont(new Font("Tahoma", 0, 15));
    this.tfDVRUC.setColumns(10);
    this.tfDVRUC.setBounds(315, 102, 33, 21);
    contentPane.add(this.tfDVRUC);
    
    this.chckbxInsertarDV = new JCheckBox("Insertar DV");
    this.chckbxInsertarDV.setForeground(Color.WHITE);
    this.chckbxInsertarDV.setOpaque(false);
    this.chckbxInsertarDV.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          IClientes.this.tfCelular.requestFocus();
        }
      }
    });
    this.chckbxInsertarDV.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (IClientes.this.chckbxInsertarDV.isSelected())
        {
          IClientes.this.tfDVRUC.setVisible(true);
          IClientes.this.tfDVRUC.requestFocus();
          IClientes.this.lblNcedRuc.setText("  RUC:");
          IClientes.this.lblNcedRuc.setBounds(87, 105, 62, 14);
        }
        else
        {
          IClientes.this.tfDVRUC.setVisible(false);
          IClientes.this.lblNcedRuc.setText("N�mero de c�dula:");
          IClientes.this.lblNcedRuc.setBounds(10, 105, 139, 14);
        }
      }
    });
    this.chckbxInsertarDV.setBounds(374, 102, 97, 21);
    contentPane.add(this.chckbxInsertarDV);
    
    this.scrollPane = new JScrollPane();
    this.scrollPane.setBounds(159, 298, 341, 67);
    contentPane.add(this.scrollPane);
    
    this.tfDireccion = new JTextArea();
    this.tfDireccion.setToolTipText("Ingrese la direcci�n del cliente");
    this.tfDireccion.setFont(new Font("Tahoma", 0, 15));
    this.scrollPane.setViewportView(this.tfDireccion);
    
    this.label_2 = new JLabel("");
    this.label_2.setIcon(new ImageIcon(IClientes.class.getResource("/Galeria/NuevoReg.gif")));
    this.label_2.setBounds(319, 11, 40, 21);
    contentPane.add(this.label_2);
    
    this.tfColor = new JTextField();
    this.tfColor.setVisible(false);
    this.tfColor.setEditable(false);
    this.tfColor.setBounds(574, 13, 86, 20);
    contentPane.add(this.tfColor);
    this.tfColor.setColumns(10);
    
    this.lblDescuento = new JLabel("Descuento:");
    this.lblDescuento.setToolTipText("");
    this.lblDescuento.setForeground(Color.WHITE);
    this.lblDescuento.setFont(new Font("Tahoma", 0, 15));
    this.lblDescuento.setBounds(82, 225, 88, 14);
    contentPane.add(this.lblDescuento);
    
    this.tfDescuento = new JTextField();
    this.tfDescuento.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (IClientes.this.tfDescuento.getText().trim().length() > 0) {
          IClientes.this.lblCompletelosdatos.setVisible(false);
        }
      }
    });
    this.tfDescuento.setEnabled(false);
    this.tfDescuento.setToolTipText("Ingrese el N� de celular del cliente");
    this.tfDescuento.setFont(new Font("Tahoma", 0, 15));
    this.tfDescuento.setColumns(10);
    this.tfDescuento.setBounds(159, 222, 72, 21);
    contentPane.add(this.tfDescuento);
    
    this.chckbxDescuento = new JCheckBox("Descuento");
    this.chckbxDescuento.setOpaque(false);
    this.chckbxDescuento.setForeground(Color.WHITE);
    this.chckbxDescuento.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          IClientes.this.tfEmail.requestFocus();
        }
      }
    });
    this.chckbxDescuento.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (IClientes.this.chckbxDescuento.isSelected())
        {
          IClientes.this.tfDescuento.setEnabled(true);
          IClientes.this.tfDescuento.requestFocus();
        }
        else
        {
          IClientes.this.tfDescuento.setEnabled(false);
          IClientes.this.tfEmail.requestFocus();
        }
      }
    });
    this.chckbxDescuento.setBounds(279, 222, 97, 21);
    contentPane.add(this.chckbxDescuento);
    
    this.label = new JLabel("%");
    this.label.setToolTipText("");
    this.label.setForeground(Color.WHITE);
    this.label.setFont(new Font("Tahoma", 0, 15));
    this.label.setBounds(241, 223, 40, 21);
    contentPane.add(this.label);
    this.tfDireccion.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          IClientes.this.btnGuardar.requestFocus();
        }
      }
      
      public void keyTyped(KeyEvent ke)
      {
        String caracter = IClientes.this.tfDireccion.getText();
        if (caracter.trim().length() >= 80) {
          ke.consume();
        }
      }
    });
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
    this.lblNombreYApellido.setForeground(Color.RED);
    this.lblNcedRuc.setForeground(Color.RED);
    this.lblTelfono.setForeground(Color.RED);
    this.lblDireccin.setForeground(Color.RED);
    this.lblEmail.setForeground(Color.RED);
    this.lblTelfonoMvil.setForeground(Color.RED);
  }
  
  private void ColorBlanco()
  {
    this.lblCodigo.setForeground(Color.WHITE);
    this.lblNombreYApellido.setForeground(Color.WHITE);
    this.lblNcedRuc.setForeground(Color.WHITE);
    this.lblTelfono.setForeground(Color.WHITE);
    this.lblDireccin.setForeground(Color.WHITE);
    this.lblEmail.setForeground(Color.WHITE);
    this.lblTelfonoMvil.setForeground(Color.WHITE);
  }
  
  private void ColorGris()
  {
    this.lblCodigo.setForeground(Color.GRAY);
    this.lblNombreYApellido.setForeground(Color.GRAY);
    this.lblNcedRuc.setForeground(Color.GRAY);
    this.lblTelfono.setForeground(Color.GRAY);
    this.lblDireccin.setForeground(Color.GRAY);
    this.lblEmail.setForeground(Color.GRAY);
    this.lblTelfonoMvil.setForeground(Color.GRAY);
  }
  
  private void ColorAmarillo()
  {
    this.lblCodigo.setForeground(Color.YELLOW);
    this.lblNombreYApellido.setForeground(Color.YELLOW);
    this.lblNcedRuc.setForeground(Color.YELLOW);
    this.lblTelfono.setForeground(Color.YELLOW);
    this.lblDireccin.setForeground(Color.YELLOW);
    this.lblEmail.setForeground(Color.YELLOW);
    this.lblTelfonoMvil.setForeground(Color.YELLOW);
  }
  
  private void ColorVerde()
  {
    this.lblCodigo.setForeground(new Color(0, 128, 0));
    this.lblNombreYApellido.setForeground(new Color(0, 128, 0));
    this.lblNcedRuc.setForeground(new Color(0, 128, 0));
    this.lblTelfono.setForeground(new Color(0, 128, 0));
    this.lblDireccin.setForeground(new Color(0, 128, 0));
    this.lblEmail.setForeground(new Color(0, 128, 0));
    this.lblTelfonoMvil.setForeground(new Color(0, 128, 0));
  }
  
  private void ColorAzul()
  {
    this.lblCodigo.setForeground(new Color(0, 0, 205));
    this.lblNombreYApellido.setForeground(new Color(0, 0, 205));
    this.lblNcedRuc.setForeground(new Color(0, 0, 205));
    this.lblTelfono.setForeground(new Color(0, 0, 205));
    this.lblDireccin.setForeground(new Color(0, 0, 205));
    this.lblEmail.setForeground(new Color(0, 0, 205));
    this.lblTelfonoMvil.setForeground(new Color(0, 0, 205));
  }
  
  private void ColorMarron()
  {
    this.lblCodigo.setForeground(new Color(139, 69, 19));
    this.lblNombreYApellido.setForeground(new Color(139, 69, 19));
    this.lblNcedRuc.setForeground(new Color(139, 69, 19));
    this.lblTelfono.setForeground(new Color(139, 69, 19));
    this.lblDireccin.setForeground(new Color(139, 69, 19));
    this.lblEmail.setForeground(new Color(139, 69, 19));
    this.lblTelfonoMvil.setForeground(new Color(139, 69, 19));
  }
  
  private void ColorPurpura()
  {
    this.lblCodigo.setForeground(new Color(148, 0, 211));
    this.lblNombreYApellido.setForeground(new Color(148, 0, 211));
    this.lblNcedRuc.setForeground(new Color(148, 0, 211));
    this.lblTelfono.setForeground(new Color(148, 0, 211));
    this.lblDireccin.setForeground(new Color(148, 0, 211));
    this.lblEmail.setForeground(new Color(148, 0, 211));
    this.lblTelfonoMvil.setForeground(new Color(148, 0, 211));
  }
  
  private void ColorCyan()
  {
    this.lblCodigo.setForeground(Color.CYAN);
    this.lblNombreYApellido.setForeground(Color.CYAN);
    this.lblNcedRuc.setForeground(Color.CYAN);
    this.lblTelfono.setForeground(Color.CYAN);
    this.lblDireccin.setForeground(Color.CYAN);
    this.lblEmail.setForeground(Color.CYAN);
    this.lblTelfonoMvil.setForeground(Color.CYAN);
  }
  
  private void ColorNaranja()
  {
    this.lblCodigo.setForeground(Color.ORANGE);
    this.lblNombreYApellido.setForeground(Color.ORANGE);
    this.lblNcedRuc.setForeground(Color.ORANGE);
    this.lblTelfono.setForeground(Color.ORANGE);
    this.lblDireccin.setForeground(Color.ORANGE);
    this.lblEmail.setForeground(Color.ORANGE);
    this.lblTelfonoMvil.setForeground(Color.ORANGE);
  }
  
  private void ColorVerdeFluor()
  {
    this.lblCodigo.setForeground(Color.GREEN);
    this.lblNombreYApellido.setForeground(Color.GREEN);
    this.lblNcedRuc.setForeground(Color.GREEN);
    this.lblTelfono.setForeground(Color.GREEN);
    this.lblDireccin.setForeground(Color.GREEN);
    this.lblEmail.setForeground(Color.GREEN);
    this.lblTelfonoMvil.setForeground(Color.GREEN);
  }
  
  private void ColorNegro()
  {
    this.lblCodigo.setForeground(Color.BLACK);
    this.lblNombreYApellido.setForeground(Color.BLACK);
    this.lblNcedRuc.setForeground(Color.BLACK);
    this.lblTelfono.setForeground(Color.BLACK);
    this.lblDireccin.setForeground(Color.BLACK);
    this.lblEmail.setForeground(Color.BLACK);
    this.lblTelfonoMvil.setForeground(Color.BLACK);
  }
  
  private void InsertarNuevoCliente()
  {
    if (ValidarDatosDelCliente())
    {
      ModeloClientes cliente = new ModeloClientes();
      cliente.setCodigo(Integer.parseInt(this.tfCodigo.getText()));
      cliente.setNombre(this.tfNombre.getText().toUpperCase());
      if ((this.tfDVRUC.isVisible()) && (this.tfDVRUC.getText().trim().length() > 0)) {
        cliente.setCedula(this.tfCedula.getText().concat("-" + this.tfDVRUC.getText()));
      } else {
        cliente.setCedula(this.tfCedula.getText());
      }
      cliente.setDireccion(this.tfDireccion.getText().toUpperCase());
      cliente.setContacto(this.tfCelular.getText());
      cliente.setContacto2(this.tfTelefono.getText());
      if (this.chckbxDescuento.isSelected())
      {
        if (this.tfDescuento.getText().trim().length() > 0) {
          cliente.setDescuento(Double.parseDouble(this.tfDescuento.getText()));
        }
      }
      else {
        cliente.setDescuento(0.0D);
      }
      cliente.setEmail(this.tfEmail.getText().toLowerCase());
      if (!this.existencia) {
        if (!DaoClientes.CedulaDuplex(cliente))
        {
          DaoClientes.RegistrarCliente(cliente);
          Clientes.btnActualizar.doClick();
          dispose();
        }
        else
        {
          JOptionPane.showMessageDialog(null, "�Ya existe un cliente con este n�mero de c�dula!", "Advertencia", 2);
          this.tfCedula.requestFocus();
          this.tfCedula.selectAll();
        }
      }
    }
  }
  
  private boolean ValidarDatosDelCliente()
  {
    if (this.tfNombre.getText().trim().isEmpty())
    {
      this.tfNombre.requestFocus();
      this.lblCampoRequerido1.setVisible(true);
      this.lblCompletelosdatos.setVisible(true);
      this.lblCompletelosdatos.setForeground(Color.YELLOW);
      this.lblCompletelosdatos.setText("Complete el dato requerido del cliente, el campo Nombre y apellido est� vac�o.");
      return false;
    }
    if ((this.tfCedula.getText().trim().isEmpty()) && (this.lblNcedRuc.getText().equals("  RUC:")))
    {
      this.tfCedula.requestFocus();
      this.lblCampoRequerido2.setVisible(true);
      this.lblCompletelosdatos.setForeground(Color.YELLOW);
      this.lblCompletelosdatos.setText("Complete el dato requerido del cliente, el campo RUC est� incompleto.");
      this.lblCompletelosdatos.setVisible(true);
      return false;
    }
    if ((this.chckbxInsertarDV.isSelected()) && (this.tfDVRUC.getText().isEmpty()))
    {
      this.lblCompletelosdatos.setVisible(true);
      this.lblCompletelosdatos.setForeground(Color.YELLOW);
      this.lblCompletelosdatos.setText("El campo RUC, est� incompleto.");
      this.tfDVRUC.requestFocus();
      this.lblCampoRequerido2.setVisible(true);
      return false;
    }
    if (this.tfCedula.getText().trim().isEmpty())
    {
      this.tfCedula.requestFocus();
      this.lblCampoRequerido2.setVisible(true);
      this.lblCompletelosdatos.setForeground(Color.YELLOW);
      this.lblCompletelosdatos.setText("Complete el dato requerido del cliente, el campo N�mero de c�dula est� incompleto.");
      this.lblCompletelosdatos.setVisible(true);
      return false;
    }
    if ((this.tfEmail.getText().trim().length() > 0) && (
      (!this.tfEmail.getText().contains("@")) || (!this.tfEmail.getText().contains("."))))
    {
      JOptionPane.showMessageDialog(null, "Error del formato: usuario@dominio.com", "Error de formato", 0);
      this.tfEmail.requestFocus();
      return false;
    }
    if ((this.tfDescuento.getText().trim().isEmpty()) && (this.chckbxDescuento.isSelected()))
    {
      this.lblCompletelosdatos.setForeground(Color.YELLOW);
      this.lblCompletelosdatos.setText("Ingrese el porcentaje de descuento que puede tener el cliente.");
      this.lblCompletelosdatos.setVisible(true);
      this.tfDescuento.requestFocus();
      return false;
    }
    return true;
  }    
}
