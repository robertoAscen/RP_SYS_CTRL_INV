/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Ediciones;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAscencio
 */
public class EClientes extends JDialog
{
    private JPanel contentPane;
  public static JTextField tfCodigo;
  public static JTextField tfNombre;
  private JButton btnGuardar;
  private JTextField tfCedula;
  private JTextField tfTelefono;
  private JTextField tfCelular;
  private JTextField tfEmail;
  private boolean existencia;
  private JLabel lblCodigo;
  private JLabel lblNombreYApellido;
  private JLabel lblNcedRuc;
  private JLabel lblTelfono;
  private JLabel lblTelfonoMvil;
  private JLabel lblEmail;
  private JLabel lblCampoRequerido1;
  private JLabel lblCampoRequerido2;
  private JLabel lblDireccin;
  private JButton btnModificar;
  private Clientes cliente;
  private JLabel lblCompletelosdatos;
  private JTextField tfDVRUC;
  private JCheckBox chckbxInsertarDV;
  private JTextArea tfDireccion;
  private JScrollPane scrollPane;
  private JLabel lblImagen2;
  private JLabel lblImagen;
  private JTextField tfColor;
  private JTextField tfDescuento;
  private JCheckBox checkBoxDescuento;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          EClientes frame = new EClientes();
          frame.setLocationRelativeTo(null);
          frame.setVisible(true);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }
  
  public EClientes()
  {
    setIconImage(Toolkit.getDefaultToolkit().getImage(EClientes.class.getResource("/Iconos/DatosDelCliente2.png")));
    setTitle("Datos del cliente");
    setResizable(false);
    
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent salir)
      {
        EClientes.this.Salir(salir);
      }
    });
    setDefaultCloseOperation(2);
    setBounds(100, 100, 695, 415);
    this.contentPane = new JPanel();
    
    FondoSubFormularios contentPane = new FondoSubFormularios();
    contentPane.setForeground(Color.BLACK);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    this.btnGuardar = new JButton("Guardar");
    this.btnGuardar.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent arg0)
      {
        EClientes.this.modificarCliente();
      }
    });
    this.btnGuardar.setIcon(new ImageIcon(EClientes.class.getResource("/Iconos/Guardar.png")));
    this.btnGuardar.setEnabled(false);
    this.btnGuardar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        EClientes.this.modificarCliente();
      }
    });
    this.btnGuardar.setToolTipText("Guardar cambios");
    this.btnGuardar.setFont(new Font("Tahoma", 0, 15));
    this.btnGuardar.setBounds(527, 298, 152, 62);
    contentPane.add(this.btnGuardar);
    
    tfCodigo = new JTextField();
    tfCodigo.setToolTipText("C�digo del cliente");
    tfCodigo.setEnabled(false);
    tfCodigo.setFont(new Font("Tahoma", 0, 15));
    tfCodigo.setBounds(139, 19, 136, 21);
    contentPane.add(tfCodigo);
    tfCodigo.setColumns(10);
    
    tfNombre = new JTextField();
    Validaciones nombre = new Validaciones();
    nombre.BloqueoNumerico(tfNombre);
    tfNombre.setEnabled(false);
    tfNombre.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10)
        {
          EClientes.this.tfCedula.requestFocus();
          EClientes.this.tfCedula.selectAll();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        EClientes.this.reconocerCambios();
        if ((EClientes.tfNombre.getText().trim().length() > 0) && 
          (EClientes.this.lblCompletelosdatos.isVisible()))
        {
          EClientes.this.lblCampoRequerido1.setVisible(false);
          EClientes.this.lblCompletelosdatos.setForeground(Color.GREEN);
          EClientes.this.lblCompletelosdatos.setText("Pulse Guardar para modificar el registro. Ya se ha ingresado el dato requerido.");
        }
      }
      
      public void keyTyped(KeyEvent consumir)
      {
        String caracter = EClientes.tfNombre.getText();
        if (caracter.trim().length() > 60) {
          consumir.consume();
        }
      }
    });
    tfNombre.setFont(new Font("Tahoma", 0, 15));
    tfNombre.setColumns(10);
    tfNombre.setBounds(139, 61, 363, 21);
    contentPane.add(tfNombre);
    
    this.tfCedula = new JTextField();
    Validaciones validar = new Validaciones();
    validar.BloqueoAlfabetico(this.tfCedula);
    this.tfCedula.setEnabled(false);
    this.tfCedula.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (EClientes.this.tfDVRUC.isVisible())
        {
          if (evento.getKeyCode() == 10)
          {
            EClientes.this.tfDVRUC.requestFocus();
            EClientes.this.tfDVRUC.selectAll();
          }
        }
        else if (evento.getKeyCode() == 10) {
          EClientes.this.chckbxInsertarDV.requestFocus();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        EClientes.this.reconocerCambios();
        if ((EClientes.this.tfCedula.getText().trim().length() > 0) && 
          (EClientes.this.lblCompletelosdatos.isVisible()))
        {
          EClientes.this.lblCampoRequerido2.setVisible(false);
          EClientes.this.lblCompletelosdatos.setForeground(Color.GREEN);
          EClientes.this.lblCompletelosdatos.setText("Pulse Guardar para modificar el registro. Ya se ha ingresado el dato requerido.");
        }
      }
      
      public void keyTyped(KeyEvent ke)
      {
        String caracter = EClientes.this.tfCedula.getText();
        if (caracter.trim().length() >= 8) {
          ke.consume();
        }
      }
    });
    this.tfCedula.setFont(new Font("Tahoma", 0, 15));
    this.tfCedula.setColumns(10);
    this.tfCedula.setBounds(139, 104, 155, 21);
    contentPane.add(this.tfCedula);
    
    this.tfTelefono = new JTextField();
    validar.BloqueoAlfabetico(this.tfTelefono);
    this.tfTelefono.setEnabled(false);
    this.tfTelefono.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          if (EClientes.this.checkBoxDescuento.isSelected())
          {
            EClientes.this.tfDescuento.requestFocus();
            EClientes.this.tfDescuento.selectAll();
          }
          else
          {
            EClientes.this.checkBoxDescuento.requestFocus();
          }
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (EClientes.this.tfTelefono.getText().trim().length() > 0) {
          EClientes.this.reconocerCambios();
        }
      }
      
      public void keyTyped(KeyEvent consumir)
      {
        String caracter = EClientes.this.tfTelefono.getText();
        if (caracter.trim().length() >= 20) {
          consumir.consume();
        }
      }
    });
    this.tfTelefono.setFont(new Font("Tahoma", 0, 15));
    this.tfTelefono.setColumns(10);
    this.tfTelefono.setBounds(139, 194, 201, 21);
    contentPane.add(this.tfTelefono);
    
    this.tfCelular = new JTextField();
    validar.BloqueoAlfabetico(this.tfCelular);
    this.tfCelular.setEnabled(false);
    this.tfCelular.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10)
        {
          EClientes.this.tfTelefono.requestFocus();
          EClientes.this.tfTelefono.selectAll();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (EClientes.this.tfDVRUC.getText().trim().length() > 0) {
          EClientes.this.reconocerCambios();
        }
      }
      
      public void keyTyped(KeyEvent consumir)
      {
        String caracter = EClientes.this.tfCelular.getText();
        if (caracter.trim().length() >= 20) {
          consumir.consume();
        }
      }
    });
    this.tfCelular.setFont(new Font("Tahoma", 0, 15));
    this.tfCelular.setColumns(10);
    this.tfCelular.setBounds(139, 150, 201, 21);
    contentPane.add(this.tfCelular);
    
    this.tfEmail = new JTextField();
    this.tfEmail.setEnabled(false);
    this.tfEmail.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10)
        {
          EClientes.this.tfDireccion.requestFocus();
          EClientes.this.tfDireccion.selectAll();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (EClientes.this.tfEmail.getText().trim().length() > 0) {
          EClientes.this.reconocerCambios();
        }
      }
      
      public void keyTyped(KeyEvent consumir)
      {
        String caracter = EClientes.this.tfEmail.getText();
        if (caracter.trim().length() >= 50) {
          consumir.consume();
        }
      }
    });
    this.tfEmail.setFont(new Font("Tahoma", 0, 15));
    this.tfEmail.setColumns(10);
    this.tfEmail.setBounds(139, 259, 363, 21);
    contentPane.add(this.tfEmail);
    
    this.lblCodigo = new JLabel(" C�digo:");
    this.lblCodigo.setForeground(Color.WHITE);
    this.lblCodigo.setFont(new Font("Tahoma", 0, 15));
    this.lblCodigo.setBounds(75, 19, 74, 21);
    contentPane.add(this.lblCodigo);
    
    this.lblNombreYApellido = new JLabel(" Nombre y apellido:");
    this.lblNombreYApellido.setForeground(Color.WHITE);
    this.lblNombreYApellido.setFont(new Font("Tahoma", 0, 15));
    this.lblNombreYApellido.setBounds(0, 61, 150, 21);
    contentPane.add(this.lblNombreYApellido);
    
    this.lblNcedRuc = new JLabel(" N�mero de c�dula:");
    this.lblNcedRuc.setForeground(Color.WHITE);
    this.lblNcedRuc.setFont(new Font("Tahoma", 0, 15));
    this.lblNcedRuc.setText(" N�mero de c�dula:");
    this.lblNcedRuc.setBounds(0, 107, 136, 14);
    contentPane.add(this.lblNcedRuc);
    
    this.lblTelfono = new JLabel("  Tel�fono:");
    this.lblTelfono.setForeground(Color.WHITE);
    this.lblTelfono.setFont(new Font("Tahoma", 0, 15));
    this.lblTelfono.setBounds(61, 198, 88, 14);
    contentPane.add(this.lblTelfono);
    
    this.lblTelfonoMvil = new JLabel(" Celular:");
    this.lblTelfonoMvil.setForeground(Color.WHITE);
    this.lblTelfonoMvil.setFont(new Font("Tahoma", 0, 15));
    this.lblTelfonoMvil.setBounds(75, 154, 103, 14);
    contentPane.add(this.lblTelfonoMvil);
    
    this.lblEmail = new JLabel("  E-mail:");
    this.lblEmail.setForeground(Color.WHITE);
    this.lblEmail.setFont(new Font("Tahoma", 0, 15));
    this.lblEmail.setBounds(75, 262, 103, 14);
    contentPane.add(this.lblEmail);
    
    this.lblDireccin = new JLabel(" Direcci�n:");
    this.lblDireccin.setForeground(Color.WHITE);
    this.lblDireccin.setFont(new Font("Tahoma", 0, 15));
    this.lblDireccin.setBounds(61, 280, 88, 45);
    contentPane.add(this.lblDireccin);
    
    this.btnModificar = new JButton("Modificar");
    this.btnModificar.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent arg0)
      {
        EClientes.this.HabilitarTFparaModificar();
        EClientes.this.btnModificar.setEnabled(false);
      }
    });
    this.btnModificar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        EClientes.this.HabilitarTFparaModificar();
        EClientes.this.btnModificar.setEnabled(false);
        EClientes.this.chckbxInsertarDV.setEnabled(true);
      }
    });
    this.btnModificar.setIcon(new ImageIcon(EClientes.class.getResource("/Iconos/Modificar.png")));
    this.btnModificar.setToolTipText("Editar dato(s) del cliente.");
    this.btnModificar.setFont(new Font("Tahoma", 0, 15));
    this.btnModificar.setBounds(527, 231, 152, 56);
    contentPane.add(this.btnModificar);
    
    this.lblImagen = new JLabel("");
    this.lblImagen.setIcon(new ImageIcon(EClientes.class.getResource("/Galeria/ModificarCliente.png")));
    this.lblImagen.setForeground(Color.DARK_GRAY);
    this.lblImagen.setFont(new Font("Tahoma", 0, 15));
    this.lblImagen.setBounds(527, 62, 146, 132);
    contentPane.add(this.lblImagen);
    
    this.lblCampoRequerido1 = new JLabel("*");
    this.lblCampoRequerido1.setForeground(new Color(255, 0, 0));
    this.lblCampoRequerido1.setFont(new Font("Tahoma", 1, 20));
    this.lblCampoRequerido1.setBounds(506, 63, 52, 21);
    this.lblCampoRequerido1.setVisible(false);
    contentPane.add(this.lblCampoRequerido1);
    
    this.lblCompletelosdatos = new JLabel("");
    this.lblCompletelosdatos.setVisible(false);
    this.lblCompletelosdatos.setForeground(Color.YELLOW);
    this.lblCompletelosdatos.setBounds(138, 361, 475, 26);
    contentPane.add(this.lblCompletelosdatos);
    
    this.tfDVRUC = new JTextField();
    this.tfDVRUC.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        if (EClientes.this.tfDVRUC.isRequestFocusEnabled()) {
          EClientes.this.btnGuardar.setEnabled(true);
        }
      }
    });
    validar.BloqueoAlfabetico(this.tfDVRUC);
    this.tfDVRUC.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent evento)
      {
        if (EClientes.this.tfDVRUC.getText().trim().length() == 1) {
          EClientes.this.reconocerCambios();
        }
      }
      
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10)
        {
          EClientes.this.tfCelular.requestFocus();
          EClientes.this.tfCelular.selectAll();
        }
      }
      
      public void keyTyped(KeyEvent ke)
      {
        String caracter = EClientes.this.tfDVRUC.getText();
        if (caracter.trim().length() >= 1) {
          ke.consume();
        }
      }
    });
    this.tfDVRUC.setText("<dynamic>");
    this.tfDVRUC.setFont(new Font("Tahoma", 0, 15));
    this.tfDVRUC.setEnabled(false);
    this.tfDVRUC.setColumns(10);
    this.tfDVRUC.setBounds(304, 104, 34, 21);
    contentPane.add(this.tfDVRUC);
    
    this.lblCampoRequerido2 = new JLabel("*");
    this.lblCampoRequerido2.setForeground(new Color(255, 0, 0));
    this.lblCampoRequerido2.setFont(new Font("Tahoma", 1, 20));
    this.lblCampoRequerido2.setBounds(340, 104, 25, 31);
    this.lblCampoRequerido2.setVisible(false);
    contentPane.add(this.lblCampoRequerido2);
    
    this.chckbxInsertarDV = new JCheckBox("Insertar DV");
    this.chckbxInsertarDV.setOpaque(false);
    this.chckbxInsertarDV.setForeground(Color.WHITE);
    this.chckbxInsertarDV.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          EClientes.this.tfCelular.requestFocus();
          EClientes.this.tfCedula.selectAll();
        }
      }
    });
    this.chckbxInsertarDV.setEnabled(false);
    this.chckbxInsertarDV.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (EClientes.this.chckbxInsertarDV.isSelected())
        {
          EClientes.this.tfDVRUC.setVisible(true);
          EClientes.this.tfDVRUC.setText("");
          EClientes.this.tfDVRUC.requestFocus();
          EClientes.this.lblNcedRuc.setText("  RUC:");
          EClientes.this.lblNcedRuc.setBounds(84, 107, 44, 14);
        }
        else
        {
          EClientes.this.tfDVRUC.setVisible(false);
          EClientes.this.lblNcedRuc.setText(" N�mero de c�dula:");
          EClientes.this.lblNcedRuc.setBounds(0, 107, 136, 14);
        }
      }
    });
    this.chckbxInsertarDV.setBounds(358, 104, 97, 21);
    contentPane.add(this.chckbxInsertarDV);
    
    this.scrollPane = new JScrollPane();
    this.scrollPane.setBounds(139, 291, 359, 69);
    contentPane.add(this.scrollPane);
    
    this.tfDireccion = new JTextArea();
    this.scrollPane.setViewportView(this.tfDireccion);
    this.tfDireccion.setEnabled(false);
    this.tfDireccion.setFont(new Font("Tahoma", 0, 15));
    
    this.lblImagen2 = new JLabel("");
    this.lblImagen2.setVisible(false);
    this.lblImagen2.setIcon(new ImageIcon(EClientes.class.getResource("/Galeria/IconModificar.png")));
    this.lblImagen2.setForeground(Color.DARK_GRAY);
    this.lblImagen2.setFont(new Font("Tahoma", 0, 15));
    this.lblImagen2.setBounds(523, 61, 156, 132);
    contentPane.add(this.lblImagen2);
    
    this.tfColor = new JTextField();
    this.tfColor.setVisible(false);
    this.tfColor.setEditable(false);
    this.tfColor.setBounds(587, 11, 86, 20);
    contentPane.add(this.tfColor);
    this.tfColor.setColumns(10);
    
    JLabel label = new JLabel("Descuento:");
    label.setToolTipText("");
    label.setForeground(Color.WHITE);
    label.setFont(new Font("Tahoma", 0, 15));
    label.setBounds(61, 229, 88, 14);
    contentPane.add(label);
    
    this.tfDescuento = new JTextField();
    this.tfDescuento.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent arg0)
      {
        if (arg0.getKeyCode() == 10)
        {
          EClientes.this.tfEmail.requestFocus();
          EClientes.this.tfEmail.selectAll();
        }
      }
    });
    this.tfDescuento.setToolTipText("Ingrese el N� de celular del cliente");
    this.tfDescuento.setFont(new Font("Tahoma", 0, 15));
    this.tfDescuento.setEnabled(false);
    this.tfDescuento.setColumns(10);
    this.tfDescuento.setBounds(139, 226, 71, 21);
    contentPane.add(this.tfDescuento);
    
    JLabel label_1 = new JLabel("%");
    label_1.setToolTipText("");
    label_1.setForeground(Color.WHITE);
    label_1.setFont(new Font("Tahoma", 0, 15));
    label_1.setBounds(220, 227, 40, 21);
    contentPane.add(label_1);
    
    this.checkBoxDescuento = new JCheckBox("Descuento");
    this.checkBoxDescuento.setOpaque(false);
    this.checkBoxDescuento.setForeground(Color.WHITE);
    this.checkBoxDescuento.setEnabled(false);
    this.checkBoxDescuento.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          EClientes.this.tfEmail.requestFocus();
        }
      }
    });
    this.checkBoxDescuento.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (EClientes.this.checkBoxDescuento.isSelected())
        {
          EClientes.this.tfDescuento.setEnabled(true);
          EClientes.this.tfDescuento.requestFocus();
          EClientes.this.tfDescuento.selectAll();
        }
        else
        {
          EClientes.this.tfDescuento.setText("0");
          EClientes.this.tfDescuento.setEnabled(false);
          EClientes.this.tfEmail.requestFocus();
        }
        EClientes.this.reconocerCambios();
      }
    });
    this.checkBoxDescuento.setBounds(258, 226, 97, 21);
    contentPane.add(this.checkBoxDescuento);
    this.tfDireccion.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          EClientes.this.btnGuardar.requestFocus();
        }
      }
      
      public void keyTyped(KeyEvent ke)
      {
        String caracter = EClientes.this.tfDireccion.getText();
        if (caracter.trim().length() >= 80) {
          ke.consume();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (EClientes.this.tfDireccion.getText().trim().length() > 0) {
          EClientes.this.reconocerCambios();
        }
      }
    });
    LlamadaSeleccion();
    CargarTextFieldsParaModificar();
    HabilitarCheckBox();
    ReconocerRUCoCIN();
    ColorBlanco();
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
  
  private void LlamadaSeleccion()
  {
    int seleccion = Clientes.tablaDeClientes.getSelectedRow();
    try
    {
      DefaultTableModel m = (DefaultTableModel)Clientes.tablaDeClientes.getModel();
      String codigo = Clientes.tablaDeClientes.getValueAt(seleccion, 0).toString();
      tfCodigo.setText(codigo);
    }
    catch (Exception localException) 
    {
        
    }
  }
  
  private void CargarTextFieldsParaModificar()
  {
    String codigo = tfCodigo.getText();
    ModeloClientes clientes = DaoClientes.ConsutaParaModificar(Integer.parseInt(codigo));
    tfNombre.setText(clientes.getNombre());
    this.tfCedula.setText(clientes.getCedula().split("-")[0]);
    String ruc = clientes.getCedula().concat("-nulo").split("-")[1];
    this.tfDVRUC.setText(ruc);
    this.tfCelular.setText(clientes.getContacto());
    this.tfTelefono.setText(clientes.getContacto2());
    this.tfEmail.setText(clientes.getEmail());
    this.tfDireccion.setText(clientes.getDireccion());
    double descuento = clientes.getDescuento();
    if (descuento == 0.0D)
    {
      this.tfDescuento.setText(String.valueOf(descuento));
      this.checkBoxDescuento.setSelected(false);
    }
    else
    {
      this.tfDescuento.setText(String.valueOf(descuento));
      this.checkBoxDescuento.setSelected(true);
    }
    HabilitarTextFieldRUC();
  }
  
  private void modificarCliente()
  {
    if (ValidarDatosDelCliente())
    {
      ModeloClientes clientes = new ModeloClientes();
      
      clientes.setCodigo(Integer.parseInt(tfCodigo.getText()));
      clientes.setNombre(tfNombre.getText().toUpperCase());
      if (this.tfDVRUC.getText().trim().length() == 1) {
        clientes.setCedula(this.tfCedula.getText().concat("-" + this.tfDVRUC.getText()));
      } else {
        clientes.setCedula(this.tfCedula.getText());
      }
      clientes.setDireccion(this.tfDireccion.getText().toUpperCase());
      clientes.setContacto(this.tfCelular.getText());
      clientes.setContacto2(this.tfTelefono.getText());
      clientes.setEmail(this.tfEmail.getText().toLowerCase());
      
      clientes.setDescuento(Double.parseDouble(this.tfDescuento.getText()));
      if (!this.existencia) {
        if (!DaoClientes.CedulaDuplex(clientes))
        {
          this.btnGuardar.setText("Guardar");
          DaoClientes.EditarCliente(clientes);
          dispose();
          Clientes.btnActualizar.doClick();
        }
        else
        {
          JOptionPane.showMessageDialog(null, "�Ya existe un cliente con este n�mero de c�dula!", "Advertencia", 1);
          this.tfCedula.requestFocus();
        }
      }
    }
  }
  
  private void reconocerCambios()
  {
    if (tfNombre.getText().trim().length() >= 0) {
      this.btnGuardar.setEnabled(true);
    }
  }
  
  private void Salir(WindowEvent evt)
  {
    if (this.btnGuardar.isEnabled())
    {
      int res = JOptionPane.showConfirmDialog(null, "�Desea guardar los cambios hechos y cerrar la ventana?", "Salir", 0);
      if (res == 0)
      {
        modificarCliente();
        dispose();
      }
      else {}
    }
  }
  
  private void HabilitarTFparaModificar()
  {
    tfNombre.setEnabled(true);
    this.tfCedula.setEnabled(true);
    this.tfTelefono.setEnabled(true);
    this.tfCelular.setEnabled(true);
    this.tfEmail.setEnabled(true);
    this.tfDireccion.setEnabled(true);
    this.tfDVRUC.setEnabled(true);
    tfNombre.requestFocus();
    tfNombre.selectAll();
    this.lblImagen.setVisible(false);
    this.lblImagen2.setVisible(true);
    this.chckbxInsertarDV.setEnabled(true);
    this.checkBoxDescuento.setEnabled(true);
    if (this.checkBoxDescuento.isSelected()) {
      this.tfDescuento.setEnabled(true);
    }
  }
  
  private boolean ValidarDatosDelCliente()
  {
    if (tfNombre.getText().trim().isEmpty())
    {
      JOptionPane.showMessageDialog(null, "�No se puede registrar un cliente sin nombre!");
      tfNombre.requestFocus();
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
      JOptionPane.showMessageDialog(null, "�No se puede registrar un cliente sin el n�mero de c�dula!");
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
    if ((this.tfDescuento.getText().trim().isEmpty()) && (this.checkBoxDescuento.isSelected()))
    {
      this.lblCompletelosdatos.setForeground(Color.YELLOW);
      this.lblCompletelosdatos.setText("Ingrese el porcentaje de descuento que puede tener el cliente.");
      this.lblCompletelosdatos.setVisible(true);
      this.tfDescuento.requestFocus();
      return false;
    }
    return true;
  }
  
  private void HabilitarTextFieldRUC()
  {
    if (this.tfDVRUC.getText().equals("nulo")) {
      this.tfDVRUC.setVisible(false);
    }
  }
  
  private void HabilitarCheckBox()
  {
    if (this.tfDVRUC.getText().trim().length() == 1) {
      this.chckbxInsertarDV.setVisible(false);
    }
  }
  
  private void ReconocerRUCoCIN()
  {
    if (this.rootPaneCheckingEnabled) {
      if (this.tfDVRUC.getText().equals("nulo"))
      {
        this.tfDVRUC.setVisible(false);
      }
      else
      {
        this.lblNcedRuc.setText("  RUC:");
        this.lblNcedRuc.setBounds(84, 107, 44, 14);
      }
    }
  }
}
