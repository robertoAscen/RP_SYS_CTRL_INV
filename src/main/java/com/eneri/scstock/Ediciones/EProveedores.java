/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Ediciones;

import com.eneri.scstock.Apariencia.FondoSubFormularios;
import com.eneri.scstock.Formularios.Proveedores;
import com.eneri.scstock.Modelos.ModeloProveedores;
import com.eneri.scstock.Objetos.DaoProveedores;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAscencio
 */
public class EProveedores extends JDialog
{
    private final JPanel contentPane = new JPanel();
  public static JTextField tfCodigo;
  private JTextField tfNombre;
  private JTextField tfTelefono;
  private JTextField tfTelefono2;
  private JTextField tfSitioweb;
  private JTextField tfEmail;
  private JLabel lblCompletelosdatos;
  private JTextArea tfDireccion;
  private JButton btnGuardar;
  private Proveedores proveedor;
  private JButton btnModificar;
  private JScrollPane scrollPane;
  private JLabel lblImagen;
  private JLabel lblImagen2;
  private JTextField tfColor;
  private JLabel lblCodigo;
  private JLabel lblNombreProveedor;
  private JLabel lblTelefono;
  private JLabel lblTelefono2;
  private JLabel lblSitioWeb;
  private JLabel lblEmail;
  private JLabel lblDireccion;
  
  public static void main(String[] args)
  {
    try
    {
      EProveedores dialog = new EProveedores();
      dialog.setVisible(true);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public EProveedores()
  {
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent evento)
      {
        EProveedores.this.Salir(evento);
      }
    });
    setIconImage(Toolkit.getDefaultToolkit().getImage(EProveedores.class.getResource("/Iconos/Proveedores.png")));
    setTitle("Datos detallados del proveedor");
    setResizable(false);
    setDefaultCloseOperation(0);
    setBounds(100, 100, 695, 415);
    
    FondoSubFormularios contentPane = new FondoSubFormularios();
    contentPane.setForeground(Color.BLACK);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    tfCodigo = new JTextField();
    tfCodigo.setToolTipText("C�digo del proveedor");
    tfCodigo.setFont(new Font("Tahoma", 0, 15));
    tfCodigo.setEnabled(false);
    tfCodigo.setColumns(10);
    tfCodigo.setBounds(149, 11, 136, 21);
    contentPane.add(tfCodigo);
    
    this.tfNombre = new JTextField();
    this.tfNombre.setToolTipText("Nombre del proveedor");
    this.tfNombre.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (EProveedores.this.tfNombre.getText().trim().length() > 0) {
          EProveedores.this.reconocerCambios();
        }
      }
      
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10)
        {
          EProveedores.this.tfTelefono.requestFocus();
          EProveedores.this.tfTelefono.selectAll();
        }
      }
    });
    this.tfNombre.setText(null);
    this.tfNombre.setFont(new Font("Tahoma", 0, 15));
    this.tfNombre.setEnabled(false);
    this.tfNombre.setColumns(10);
    this.tfNombre.setBounds(149, 53, 363, 21);
    contentPane.add(this.tfNombre);
    
    this.tfTelefono = new JTextField();
    this.tfTelefono.setToolTipText("N� de tel�fono del proveedor");
    this.tfTelefono.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (EProveedores.this.tfTelefono.getText().trim().length() > 0) {
          EProveedores.this.reconocerCambios();
        }
      }
      
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10)
        {
          EProveedores.this.tfTelefono2.requestFocus();
          EProveedores.this.tfTelefono2.selectAll();
        }
      }
    });
    this.tfTelefono.setText("<dynamic>");
    this.tfTelefono.setFont(new Font("Tahoma", 0, 15));
    this.tfTelefono.setEnabled(false);
    this.tfTelefono.setColumns(10);
    this.tfTelefono.setBounds(148, 107, 199, 21);
    contentPane.add(this.tfTelefono);
    
    this.tfTelefono2 = new JTextField();
    this.tfTelefono2.setToolTipText("N� de tel�fono del proveedor");
    this.tfTelefono2.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (EProveedores.this.tfTelefono2.getText().trim().length() > 0) {
          EProveedores.this.reconocerCambios();
        }
      }
      
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10)
        {
          EProveedores.this.tfSitioweb.requestFocus();
          EProveedores.this.tfSitioweb.selectAll();
        }
      }
    });
    this.tfTelefono2.setText(null);
    this.tfTelefono2.setFont(new Font("Tahoma", 0, 15));
    this.tfTelefono2.setEnabled(false);
    this.tfTelefono2.setColumns(10);
    this.tfTelefono2.setBounds(147, 157, 200, 21);
    contentPane.add(this.tfTelefono2);
    
    this.tfSitioweb = new JTextField();
    this.tfSitioweb.setToolTipText("Sitio web del proveedor");
    this.tfSitioweb.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (EProveedores.this.tfSitioweb.getText().trim().length() > 0) {
          EProveedores.this.reconocerCambios();
        }
      }
      
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10)
        {
          EProveedores.this.tfEmail.requestFocus();
          EProveedores.this.tfEmail.selectAll();
        }
      }
    });
    this.tfSitioweb.setText(null);
    this.tfSitioweb.setFont(new Font("Tahoma", 0, 15));
    this.tfSitioweb.setEnabled(false);
    this.tfSitioweb.setColumns(10);
    this.tfSitioweb.setBounds(147, 205, 200, 21);
    contentPane.add(this.tfSitioweb);
    
    this.tfEmail = new JTextField();
    this.tfEmail.setToolTipText("Email del proveedor");
    this.tfEmail.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (EProveedores.this.tfEmail.getText().trim().length() > 0) {
          EProveedores.this.reconocerCambios();
        }
      }
      
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10)
        {
          EProveedores.this.tfDireccion.requestFocus();
          EProveedores.this.tfDireccion.selectAll();
        }
      }
    });
    this.tfEmail.setText(null);
    this.tfEmail.setFont(new Font("Tahoma", 0, 15));
    this.tfEmail.setEnabled(false);
    this.tfEmail.setColumns(10);
    this.tfEmail.setBounds(149, 260, 363, 21);
    contentPane.add(this.tfEmail);
    
    this.scrollPane = new JScrollPane();
    this.scrollPane.setBounds(150, 298, 362, 67);
    contentPane.add(this.scrollPane);
    
    this.tfDireccion = new JTextArea();
    this.tfDireccion.setToolTipText("Direcci�n del proveedor");
    this.tfDireccion.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (EProveedores.this.tfDireccion.getText().trim().length() > 0) {
          EProveedores.this.reconocerCambios();
        }
      }
      
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          EProveedores.this.btnGuardar.requestFocus();
        }
      }
    });
    this.scrollPane.setViewportView(this.tfDireccion);
    this.tfDireccion.setText(null);
    this.tfDireccion.setFont(new Font("Tahoma", 0, 15));
    this.tfDireccion.setEnabled(false);
    
    this.btnGuardar = new JButton("Guardar");
    this.btnGuardar.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          EProveedores.this.modificarProveedor();
        }
      }
    });
    this.btnGuardar.setMnemonic('G');
    this.btnGuardar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        EProveedores.this.modificarProveedor();
      }
    });
    this.btnGuardar.setIcon(new ImageIcon(EProveedores.class.getResource("/Iconos/Guardar.png")));
    this.btnGuardar.setToolTipText("Guardar cambios");
    this.btnGuardar.setFont(new Font("Tahoma", 0, 15));
    this.btnGuardar.setEnabled(false);
    this.btnGuardar.setBounds(527, 303, 152, 62);
    contentPane.add(this.btnGuardar);
    
    this.btnModificar = new JButton("Modificar");
    this.btnModificar.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10)
        {
          EProveedores.this.HabilitarTFparaModificar();
          
          EProveedores.this.btnModificar.setEnabled(false);
          EProveedores.this.lblImagen.setVisible(false);
          EProveedores.this.lblImagen2.setVisible(true);
        }
      }
    });
    this.btnModificar.setMnemonic('M');
    this.btnModificar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        EProveedores.this.HabilitarTFparaModificar();
        
        EProveedores.this.btnModificar.setEnabled(false);
        EProveedores.this.lblImagen.setVisible(false);
        EProveedores.this.lblImagen2.setVisible(true);
      }
    });
    this.btnModificar.setIcon(new ImageIcon(EProveedores.class.getResource("/Iconos/Modificar.png")));
    this.btnModificar.setToolTipText("Editar dato(s) del proveedor.");
    this.btnModificar.setFont(new Font("Tahoma", 0, 15));
    this.btnModificar.setBounds(527, 225, 152, 67);
    contentPane.add(this.btnModificar);
    
    this.lblImagen = new JLabel("");
    this.lblImagen.setIcon(new ImageIcon(EProveedores.class.getResource("/Galeria/Proveedores.png")));
    this.lblImagen.setForeground(Color.DARK_GRAY);
    this.lblImagen.setFont(new Font("Tahoma", 0, 15));
    this.lblImagen.setBounds(344, 77, 195, 182);
    contentPane.add(this.lblImagen);
    
    this.lblCodigo = new JLabel(" C�digo:");
    this.lblCodigo.setForeground(Color.WHITE);
    this.lblCodigo.setFont(new Font("Tahoma", 0, 15));
    this.lblCodigo.setBounds(75, 11, 74, 21);
    contentPane.add(this.lblCodigo);
    
    this.lblNombreProveedor = new JLabel("Nombre del prov.:");
    this.lblNombreProveedor.setForeground(Color.WHITE);
    this.lblNombreProveedor.setFont(new Font("Tahoma", 0, 15));
    this.lblNombreProveedor.setBounds(10, 53, 142, 17);
    contentPane.add(this.lblNombreProveedor);
    
    this.lblTelefono = new JLabel(" Contacto:");
    this.lblTelefono.setForeground(Color.WHITE);
    this.lblTelefono.setFont(new Font("Tahoma", 0, 15));
    this.lblTelefono.setBounds(61, 110, 88, 18);
    contentPane.add(this.lblTelefono);
    
    this.lblTelefono2 = new JLabel("  Contacto 2:");
    this.lblTelefono2.setForeground(Color.WHITE);
    this.lblTelefono2.setFont(new Font("Tahoma", 0, 15));
    this.lblTelefono2.setBounds(43, 163, 103, 14);
    contentPane.add(this.lblTelefono2);
    
    this.lblCompletelosdatos = new JLabel("");
    this.lblCompletelosdatos.setVisible(false);
    this.lblCompletelosdatos.setBounds(159, 77, 504, 14);
    contentPane.add(this.lblCompletelosdatos);
    
    this.lblSitioWeb = new JLabel("  Sitio web:");
    this.lblSitioWeb.setForeground(Color.WHITE);
    this.lblSitioWeb.setFont(new Font("Tahoma", 0, 15));
    this.lblSitioWeb.setBounds(59, 210, 122, 14);
    contentPane.add(this.lblSitioWeb);
    
    this.lblEmail = new JLabel("E-mail:");
    this.lblEmail.setForeground(Color.WHITE);
    this.lblEmail.setFont(new Font("Tahoma", 0, 15));
    this.lblEmail.setBounds(90, 263, 88, 14);
    contentPane.add(this.lblEmail);
    
    this.lblDireccion = new JLabel("  Direcci�n:");
    this.lblDireccion.setForeground(Color.WHITE);
    this.lblDireccion.setFont(new Font("Tahoma", 0, 15));
    this.lblDireccion.setBounds(61, 292, 117, 26);
    contentPane.add(this.lblDireccion);
    
    this.lblImagen2 = new JLabel("");
    this.lblImagen2.setVisible(false);
    this.lblImagen2.setIcon(new ImageIcon(EProveedores.class.getResource("/Galeria/IconModificar.png")));
    this.lblImagen2.setForeground(Color.DARK_GRAY);
    this.lblImagen2.setFont(new Font("Tahoma", 0, 15));
    this.lblImagen2.setBounds(371, 97, 142, 152);
    contentPane.add(this.lblImagen2);
    
    this.tfColor = new JTextField();
    this.tfColor.setVisible(false);
    this.tfColor.setBounds(10, 11, 54, 20);
    contentPane.add(this.tfColor);
    this.tfColor.setColumns(10);
    
    LlamadaSeleccion();
    CargarTextFieldsParaModificar();
    ColorBlanco();
  }
  
  private void ColorBlanco()
  {
    this.lblCodigo.setForeground(Color.WHITE);
    this.lblNombreProveedor.setForeground(Color.WHITE);
    this.lblTelefono.setForeground(Color.WHITE);
    this.lblTelefono2.setForeground(Color.WHITE);
    this.lblSitioWeb.setForeground(Color.WHITE);
    this.lblEmail.setForeground(Color.WHITE);
    this.lblDireccion.setForeground(Color.WHITE);
  }
  
  private void LlamadaSeleccion()
  {
    int seleccion = Proveedores.tablaProveedores.getSelectedRow();
    try
    {
      DefaultTableModel m = (DefaultTableModel)Proveedores.tablaProveedores.getModel();
      String codigo = Proveedores.tablaProveedores.getValueAt(seleccion, 0).toString();
      tfCodigo.setText(codigo);
    }
    catch (Exception localException) {}
  }
  
  private void CargarTextFieldsParaModificar()
  {
    String codigo = tfCodigo.getText();
    ModeloProveedores proveedores = DaoProveedores.ConsutaParaModificar(Integer.parseInt(codigo));
    this.tfNombre.setText(proveedores.getNombre());
    this.tfTelefono.setText(proveedores.getTelefono());
    this.tfTelefono2.setText(proveedores.getTelefono2());
    this.tfEmail.setText(proveedores.getEmail());
    this.tfSitioweb.setText(proveedores.getSitioweb());
    this.tfDireccion.setText(proveedores.getDireccion());
  }
  
  private void HabilitarTFparaModificar()
  {
    this.tfNombre.setEnabled(true);
    this.tfNombre.requestFocus();
    this.tfNombre.selectAll();
    this.tfTelefono2.setEnabled(true);
    this.tfTelefono.setEnabled(true);
    this.tfSitioweb.setEnabled(true);
    this.tfEmail.setEnabled(true);
    this.tfDireccion.setEnabled(true);
    
    this.tfNombre.requestFocus();
    this.tfNombre.selectAll();
  }
  
  private void modificarProveedor()
  {
    if (ValidarDatosDelProveedor())
    {
      ModeloProveedores proveedores = new ModeloProveedores();
      
      proveedores.setCodigo(Integer.parseInt(tfCodigo.getText()));
      proveedores.setNombre(this.tfNombre.getText().toUpperCase());
      proveedores.setDireccion(this.tfDireccion.getText().toUpperCase());
      proveedores.setTelefono(this.tfTelefono.getText());
      proveedores.setTelefono2(this.tfTelefono2.getText());
      proveedores.setEmail(this.tfEmail.getText().toLowerCase());
      proveedores.setSitioweb(this.tfSitioweb.getText().toLowerCase());
      
      DaoProveedores.EditarProveedor(proveedores);
      dispose();
      Proveedores.btnActualizar.doClick();
    }
  }
  
  private boolean ValidarDatosDelProveedor()
  {
    if (this.tfNombre.getText().trim().isEmpty())
    {
      this.tfNombre.requestFocus();
      this.lblCompletelosdatos.setVisible(true);
      this.lblCompletelosdatos.setForeground(Color.YELLOW);
      this.lblCompletelosdatos.setText("Complete el dato requerido del proveedor, el campo Nombre del proveedor est� vac�o.");
      return false;
    }
    if ((this.tfEmail.getText().trim().length() > 0) && (
      (!this.tfEmail.getText().contains("@")) || (!this.tfEmail.getText().contains("."))))
    {
      JOptionPane.showMessageDialog(null, "Error del formato: usuario@dominio.com", "Error de formato", 0);
      this.tfEmail.requestFocus();
      return false;
    }
    if ((this.tfSitioweb.getText().trim().length() > 0) && 
      (!this.tfSitioweb.getText().contains(".")))
    {
      JOptionPane.showMessageDialog(null, "Error del formato: www.nombre.com", "Error de formato", 0);
      return false;
    }
    return true;
  }
  
  private void reconocerCambios()
  {
    if (this.tfNombre.getText().trim().length() > 0) {
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
        modificarProveedor();
        dispose();
      }
    }
    else
    {
      dispose();
    }
  }    
}
