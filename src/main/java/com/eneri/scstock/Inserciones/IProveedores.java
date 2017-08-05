/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Inserciones;

import com.eneri.scstock.Apariencia.Colores;
import com.eneri.scstock.Apariencia.FondoSubFormularios;
import com.eneri.scstock.Formularios.Proveedores;
import com.eneri.scstock.Herramientas.Validaciones;
import com.eneri.scstock.Modelos.ModeloProveedores;
import com.eneri.scstock.Objetos.DaoProveedores;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
public class IProveedores extends JDialog
{
    
  private final JPanel contentPane = new JPanel();
  private JTextField tfCodigo;
  private JTextField tfNombre;
  private JTextField tfTelefono;
  private JTextField tfTelefono2;
  private JTextField tfSitioweb;
  private JTextField tfEmail;
  private JLabel lblCompletelosdatos;
  private JTextArea tfDireccion;
  private Proveedores proveedor;
  private JButton btnGuardar;
  private JLabel lblNewLabel;
  private JLabel lblImagenIcon;
  private JLabel lblCodigo;
  private JLabel lblNombreProveedor;
  private JLabel lblTelefono2;
  private JLabel lblTelefono;
  private JLabel lblSitioWeb;
  private JLabel lblEmail;
  private JLabel lblDireccion;
  private JTextField tfColor;
  
  public static void main(String[] args)
  {
    try
    {
      IProveedores dialog = new IProveedores();
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
  
  public IProveedores()
  {
    setIconImage(Toolkit.getDefaultToolkit().getImage(IProveedores.class.getResource("/Galeria/NuevoProveedor.png")));
    setTitle("Registrar un nuevo proveedor");
    setResizable(false);
    setBounds(100, 100, 695, 415);
    
    FondoSubFormularios contentPane = new FondoSubFormularios();
    contentPane.setForeground(Color.BLACK);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    this.tfCodigo = new JTextField();
    int codigo = DaoProveedores.IncrementarCodigo() + 1;
    this.tfCodigo.setText(String.valueOf(codigo));
    this.tfCodigo.setToolTipText("C�digo del proveedor");
    this.tfCodigo.setFont(new Font("Tahoma", 0, 15));
    this.tfCodigo.setEnabled(false);
    this.tfCodigo.setColumns(10);
    this.tfCodigo.setBounds(159, 11, 157, 21);
    contentPane.add(this.tfCodigo);
    
    this.tfNombre = new JTextField();
    this.tfNombre.setToolTipText("Ingrese el nombre del proveedor");
    this.tfNombre.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          IProveedores.this.tfTelefono.requestFocus();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (IProveedores.this.tfNombre.getText().trim().length() > 0)
        {
          IProveedores.this.lblCompletelosdatos.setForeground(Color.GREEN);
          IProveedores.this.lblCompletelosdatos.setText("Pulse Guardar para registrar el proveedor. Ya se ha ingresado el nombre.");
        }
        else
        {
          IProveedores.this.lblCompletelosdatos.setForeground(Color.YELLOW);
          IProveedores.this.lblCompletelosdatos.setText("Complete el dato requerido del proveedor, el campo Nombre del proveedor est� vac�o.");
        }
      }
      
      public void keyTyped(KeyEvent consumir)
      {
        String caracter = IProveedores.this.tfNombre.getText();
        if (caracter.trim().length() > 60) {
          consumir.consume();
        }
      }
    });
    this.tfNombre.setFont(new Font("Tahoma", 0, 15));
    this.tfNombre.setColumns(10);
    this.tfNombre.setBounds(159, 55, 343, 21);
    contentPane.add(this.tfNombre);
    
    this.tfTelefono = new JTextField();
    this.tfTelefono.setToolTipText("Ingrese un N� de contacto del proveedor");
    Validaciones validar = new Validaciones();
    validar.BloqueoAlfabetico(this.tfTelefono);
    this.tfTelefono.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          IProveedores.this.tfTelefono2.requestFocus();
        }
      }
      
      public void keyTyped(KeyEvent ke)
      {
        String caracter = IProveedores.this.tfTelefono.getText();
        if (caracter.trim().length() > 20) {
          ke.consume();
        }
      }
    });
    this.tfTelefono.setFont(new Font("Tahoma", 0, 15));
    this.tfTelefono.setColumns(10);
    this.tfTelefono.setBounds(159, 110, 200, 21);
    contentPane.add(this.tfTelefono);
    
    this.tfTelefono2 = new JTextField();
    this.tfTelefono2.setToolTipText("Ingrese un segundo N� de contacto del proveedor");
    validar.BloqueoAlfabetico(this.tfTelefono2);
    this.tfTelefono2.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          IProveedores.this.tfSitioweb.requestFocus();
        }
      }
      
      public void keyTyped(KeyEvent ke)
      {
        String caracter = IProveedores.this.tfTelefono2.getText();
        if (caracter.trim().length() > 20) {
          ke.consume();
        }
      }
    });
    this.tfTelefono2.setFont(new Font("Tahoma", 0, 15));
    this.tfTelefono2.setColumns(10);
    this.tfTelefono2.setBounds(160, 156, 200, 21);
    contentPane.add(this.tfTelefono2);
    
    this.tfSitioweb = new JTextField();
    this.tfSitioweb.setToolTipText("Ingrese el sitio web del proveedor");
    this.tfSitioweb.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          IProveedores.this.tfEmail.requestFocus();
        }
      }
      
      public void keyTyped(KeyEvent ke)
      {
        String caracter = IProveedores.this.tfSitioweb.getText();
        if (caracter.trim().length() > 50) {
          ke.consume();
        }
      }
    });
    this.tfSitioweb.setFont(new Font("Tahoma", 0, 15));
    this.tfSitioweb.setColumns(10);
    this.tfSitioweb.setBounds(159, 202, 200, 21);
    contentPane.add(this.tfSitioweb);
    
    this.tfEmail = new JTextField();
    this.tfEmail.setToolTipText("Ingrese el email del proveedor");
    this.tfEmail.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          IProveedores.this.tfDireccion.requestFocus();
        }
      }
      
      public void keyTyped(KeyEvent ke)
      {
        String caracter = IProveedores.this.tfEmail.getText();
        if (caracter.trim().length() > 50) {
          ke.consume();
        }
      }
    });
    this.tfEmail.setFont(new Font("Tahoma", 0, 15));
    this.tfEmail.setColumns(10);
    this.tfEmail.setBounds(161, 250, 341, 21);
    contentPane.add(this.tfEmail);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(160, 298, 339, 67);
    contentPane.add(scrollPane);
    
    this.tfDireccion = new JTextArea();
    this.tfDireccion.setToolTipText("Ingrese la direcci�n del proveedor");
    this.tfDireccion.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          IProveedores.this.btnGuardar.requestFocus();
        }
      }
      
      public void keyTyped(KeyEvent ke)
      {
        String caracter = IProveedores.this.tfDireccion.getText();
        if (caracter.trim().length() > 80) {
          ke.consume();
        }
      }
    });
    scrollPane.setViewportView(this.tfDireccion);
    this.tfDireccion.setFont(new Font("Tahoma", 0, 15));
    
    this.btnGuardar = new JButton("Guardar");
    this.btnGuardar.setMnemonic('G');
    this.btnGuardar.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent arg0)
      {
        IProveedores.this.RegistrarNuevoProveedor();
      }
    });
    this.btnGuardar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        IProveedores.this.RegistrarNuevoProveedor();
      }
    });
    this.btnGuardar.setIcon(new ImageIcon(IProveedores.class.getResource("/Iconos/Guardar.png")));
    this.btnGuardar.setToolTipText("Guardar datos");
    this.btnGuardar.setFont(new Font("Tahoma", 0, 15));
    this.btnGuardar.setBounds(509, 298, 150, 67);
    contentPane.add(this.btnGuardar);
    
    this.lblDireccion = new JLabel("  Direcci�n:");
    this.lblDireccion.setForeground(Color.WHITE);
    this.lblDireccion.setFont(new Font("Tahoma", 0, 15));
    this.lblDireccion.setBounds(61, 290, 117, 26);
    contentPane.add(this.lblDireccion);
    
    this.lblEmail = new JLabel("  E-mail:");
    this.lblEmail.setForeground(Color.WHITE);
    this.lblEmail.setFont(new Font("Tahoma", 0, 15));
    this.lblEmail.setBounds(82, 254, 103, 14);
    contentPane.add(this.lblEmail);
    
    this.lblSitioWeb = new JLabel(" Sitio web:");
    this.lblSitioWeb.setForeground(Color.WHITE);
    this.lblSitioWeb.setFont(new Font("Tahoma", 0, 15));
    this.lblSitioWeb.setBounds(61, 206, 122, 14);
    contentPane.add(this.lblSitioWeb);
    
    this.lblTelefono2 = new JLabel("  Contacto 2:");
    this.lblTelefono2.setForeground(Color.WHITE);
    this.lblTelefono2.setFont(new Font("Tahoma", 0, 15));
    this.lblTelefono2.setBounds(47, 160, 103, 14);
    contentPane.add(this.lblTelefono2);
    
    this.lblTelefono = new JLabel(" Contacto:");
    this.lblTelefono.setForeground(Color.WHITE);
    this.lblTelefono.setFont(new Font("Tahoma", 0, 15));
    this.lblTelefono.setBounds(61, 111, 88, 18);
    contentPane.add(this.lblTelefono);
    
    this.lblNombreProveedor = new JLabel("Nombre del prov.:");
    this.lblNombreProveedor.setForeground(Color.WHITE);
    this.lblNombreProveedor.setFont(new Font("Tahoma", 0, 15));
    this.lblNombreProveedor.setBounds(10, 57, 142, 17);
    contentPane.add(this.lblNombreProveedor);
    
    this.lblCodigo = new JLabel(" C�digo:");
    this.lblCodigo.setForeground(Color.WHITE);
    this.lblCodigo.setFont(new Font("Tahoma", 0, 15));
    this.lblCodigo.setBounds(75, 15, 74, 17);
    contentPane.add(this.lblCodigo);
    
    this.lblCompletelosdatos = new JLabel("");
    this.lblCompletelosdatos.setVisible(false);
    this.lblCompletelosdatos.setBounds(159, 77, 504, 14);
    contentPane.add(this.lblCompletelosdatos);
    
    this.lblNewLabel = new JLabel("");
    this.lblNewLabel.setIcon(new ImageIcon(IProveedores.class.getResource("/Galeria/NuevoReg.gif")));
    this.lblNewLabel.setBounds(326, 11, 43, 21);
    contentPane.add(this.lblNewLabel);
    
    this.lblImagenIcon = new JLabel("");
    this.lblImagenIcon.setIcon(new ImageIcon(IProveedores.class.getResource("/Galeria/NuevoProveedor.png")));
    this.lblImagenIcon.setBounds(509, 77, 128, 136);
    contentPane.add(this.lblImagenIcon);
    
    this.tfColor = new JTextField();
    this.tfColor.setVisible(false);
    this.tfColor.setBounds(10, 13, 55, 20);
    contentPane.add(this.tfColor);
    this.tfColor.setColumns(10);
    
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
    this.lblNombreProveedor.setForeground(Color.RED);
    this.lblTelefono.setForeground(Color.RED);
    this.lblTelefono2.setForeground(Color.RED);
    this.lblSitioWeb.setForeground(Color.RED);
    this.lblEmail.setForeground(Color.RED);
    this.lblDireccion.setForeground(Color.RED);
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
  
  private void ColorGris()
  {
    this.lblCodigo.setForeground(Color.GRAY);
    this.lblNombreProveedor.setForeground(Color.GRAY);
    this.lblTelefono.setForeground(Color.GRAY);
    this.lblTelefono2.setForeground(Color.GRAY);
    this.lblSitioWeb.setForeground(Color.GRAY);
    this.lblEmail.setForeground(Color.GRAY);
    this.lblDireccion.setForeground(Color.GRAY);
  }
  
  private void ColorAmarillo()
  {
    this.lblCodigo.setForeground(Color.YELLOW);
    this.lblNombreProveedor.setForeground(Color.YELLOW);
    this.lblTelefono.setForeground(Color.YELLOW);
    this.lblTelefono2.setForeground(Color.YELLOW);
    this.lblSitioWeb.setForeground(Color.YELLOW);
    this.lblEmail.setForeground(Color.YELLOW);
    this.lblDireccion.setForeground(Color.YELLOW);
  }
  
  private void ColorVerde()
  {
    this.lblCodigo.setForeground(new Color(0, 128, 0));
    this.lblNombreProveedor.setForeground(new Color(0, 128, 0));
    this.lblTelefono.setForeground(new Color(0, 128, 0));
    this.lblTelefono2.setForeground(new Color(0, 128, 0));
    this.lblSitioWeb.setForeground(new Color(0, 128, 0));
    this.lblEmail.setForeground(new Color(0, 128, 0));
    this.lblDireccion.setForeground(new Color(0, 128, 0));
  }
  
  private void ColorAzul()
  {
    this.lblCodigo.setForeground(new Color(0, 0, 205));
    this.lblNombreProveedor.setForeground(new Color(0, 0, 205));
    this.lblTelefono.setForeground(new Color(0, 0, 205));
    this.lblTelefono2.setForeground(new Color(0, 0, 205));
    this.lblSitioWeb.setForeground(new Color(0, 0, 205));
    this.lblEmail.setForeground(new Color(0, 0, 205));
    this.lblDireccion.setForeground(new Color(0, 0, 205));
  }
  
  private void ColorMarron()
  {
    this.lblCodigo.setForeground(new Color(139, 69, 19));
    this.lblNombreProveedor.setForeground(new Color(139, 69, 19));
    this.lblTelefono.setForeground(new Color(139, 69, 19));
    this.lblTelefono2.setForeground(new Color(139, 69, 19));
    this.lblSitioWeb.setForeground(new Color(139, 69, 19));
    this.lblEmail.setForeground(new Color(139, 69, 19));
    this.lblDireccion.setForeground(new Color(139, 69, 19));
  }
  
  private void ColorPurpura()
  {
    this.lblCodigo.setForeground(new Color(148, 0, 211));
    this.lblNombreProveedor.setForeground(new Color(148, 0, 211));
    this.lblTelefono.setForeground(new Color(148, 0, 211));
    this.lblTelefono2.setForeground(new Color(148, 0, 211));
    this.lblSitioWeb.setForeground(new Color(148, 0, 211));
    this.lblEmail.setForeground(new Color(148, 0, 211));
    this.lblDireccion.setForeground(new Color(148, 0, 211));
  }
  
  private void ColorCyan()
  {
    this.lblCodigo.setForeground(Color.CYAN);
    this.lblNombreProveedor.setForeground(Color.CYAN);
    this.lblTelefono.setForeground(Color.CYAN);
    this.lblTelefono2.setForeground(Color.CYAN);
    this.lblSitioWeb.setForeground(Color.CYAN);
    this.lblEmail.setForeground(Color.CYAN);
    this.lblDireccion.setForeground(Color.CYAN);
  }
  
  private void ColorNaranja()
  {
    this.lblCodigo.setForeground(Color.ORANGE);
    this.lblNombreProveedor.setForeground(Color.ORANGE);
    this.lblTelefono.setForeground(Color.ORANGE);
    this.lblTelefono2.setForeground(Color.ORANGE);
    this.lblSitioWeb.setForeground(Color.ORANGE);
    this.lblEmail.setForeground(Color.ORANGE);
    this.lblDireccion.setForeground(Color.ORANGE);
  }
  
  private void ColorVerdeFluor()
  {
    this.lblCodigo.setForeground(Color.GREEN);
    this.lblNombreProveedor.setForeground(Color.GREEN);
    this.lblTelefono.setForeground(Color.GREEN);
    this.lblTelefono2.setForeground(Color.GREEN);
    this.lblSitioWeb.setForeground(Color.GREEN);
    this.lblEmail.setForeground(Color.GREEN);
    this.lblDireccion.setForeground(Color.GREEN);
  }
  
  private void ColorNegro()
  {
    this.lblCodigo.setForeground(Color.BLACK);
    this.lblNombreProveedor.setForeground(Color.BLACK);
    this.lblTelefono.setForeground(Color.BLACK);
    this.lblTelefono2.setForeground(Color.BLACK);
    this.lblSitioWeb.setForeground(Color.BLACK);
    this.lblEmail.setForeground(Color.BLACK);
    this.lblDireccion.setForeground(Color.BLACK);
  }
  
  private void RegistrarNuevoProveedor()
  {
    if (ValidarDatosDelProveedor())
    {
      ModeloProveedores proveedores = new ModeloProveedores();
      proveedores.setCodigo(Integer.parseInt(this.tfCodigo.getText()));
      proveedores.setNombre(this.tfNombre.getText().toUpperCase());
      proveedores.setDireccion(this.tfDireccion.getText().toUpperCase());
      proveedores.setTelefono(this.tfTelefono.getText());
      proveedores.setTelefono2(this.tfTelefono2.getText());
      proveedores.setSitioweb(this.tfSitioweb.getText().toLowerCase());
      proveedores.setEmail(this.tfEmail.getText().toLowerCase());
      
      DaoProveedores.RegistrarProveedor(proveedores);
      Proveedores.btnActualizar.doClick();
      dispose();
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
      this.tfSitioweb.requestFocus();
      return false;
    }
    return true;
  }    
}
