/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Seguridad;

import com.eneri.scstock.Apariencia.Colores;
import com.eneri.scstock.Apariencia.FondoPrincipal;
import com.eneri.scstock.Conector.DataBase;
import com.eneri.scstock.Gestor.Gestionar;
import com.eneri.scstock.Mainframe.Principal;
import com.eneri.scstock.Modelos.ModeloUsuarios;
import com.eneri.scstock.Objetos.DaoConfiguraciones;
import com.eneri.scstock.Objetos.DaoUsuarios;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
/**
 *
 * @author RAscencio
 */
public class Login extends JDialog
{
    
  private ModeloUsuarios usuario;
  private JButton btnIniciarSesion;
  private JPasswordField tfPassword;
  private JLabel lblContrasenha;
  private Principal menuprincipal;
  private JTextField tfColor;
  JTextArea texto = new JTextArea();
  Gestionar gestion = new Gestionar();
  Gestionar gestion2 = new Gestionar();
  private JTextField tfLicencia = new JTextField();
  Encriptador cripto = new Encriptador();
  CifrarPassword cifra = new CifrarPassword();
  private JLabel lblUsuario;
  private JComboBox tfUsuario;
  public static List<ModeloUsuarios> listaUsuarios;
  private JLabel lblFoto;
  private DataBase conexion = new DataBase();
  private JPanel panel;
  private JLabel lblBienvenida;
  private JLabel lblaccesoCorrecto;
  
  public static void main(String[] args)
  {
    try
    {
      Login dialog = new Login();
      dialog.setLocationRelativeTo(null);
      dialog.setModal(true);
      dialog.setVisible(true);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public Login()
  {
    this.conexion.crearConexion();
    setResizable(false);
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent evt)
      {
        Login.this.Salir(evt);
      }
    });
    setTitle("Iniciar sesi�n");
    setBounds(100, 100, 414, 501);
    setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/Iconos/Clave.png")));
    setDefaultCloseOperation(0);
    
    FondoPrincipal contentPane = new FondoPrincipal();
    contentPane.setForeground(Color.BLACK);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    this.btnIniciarSesion = new JButton("Iniciar sesi�n");
    this.btnIniciarSesion.setToolTipText("Iniciar sesi�n");
    this.btnIniciarSesion.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent arg0)
      {
        if (Login.this.ValidarUsuarios()) {
          Login.this.IniciarSesion();
        }
      }
    });
    this.btnIniciarSesion.setFont(new Font("Arial Narrow", 0, 20));
    this.btnIniciarSesion.setIcon(new ImageIcon(Login.class.getResource("/Iconos/IniciarSesion.png")));
    
    this.btnIniciarSesion.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (Login.this.ValidarUsuarios()) {
          Login.this.IniciarSesion();
        }
      }
    });
    this.panel = new JPanel();
    this.panel.setVisible(false);
    this.panel.setBounds(0, 0, 636, 395);
    contentPane.add(this.panel);
    this.panel.setLayout(null);
    
    this.lblaccesoCorrecto = new JLabel("�Acceso correcto!");
    this.lblaccesoCorrecto.setHorizontalTextPosition(0);
    this.lblaccesoCorrecto.setHorizontalAlignment(0);
    this.lblaccesoCorrecto.setForeground(Color.WHITE);
    this.lblaccesoCorrecto.setFont(new Font("Book Antiqua", 1, 44));
    this.lblaccesoCorrecto.setBackground(Color.BLACK);
    this.lblaccesoCorrecto.setBounds(112, 310, 430, 54);
    this.panel.add(this.lblaccesoCorrecto);
    
    this.lblBienvenida = new JLabel("");
    this.lblBienvenida.setBounds(0, 0, 636, 395);
    this.panel.add(this.lblBienvenida);
    this.btnIniciarSesion.setBounds(91, 406, 283, 57);
    getContentPane().add(this.btnIniciarSesion);
    
    this.tfPassword = new JPasswordField();
    this.tfPassword.setEchoChar('X');
    this.tfPassword.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          Login.this.btnIniciarSesion.requestFocus();
        }
      }
    });
    this.tfPassword.setFont(new Font("Tahoma", 1, 15));
    this.tfPassword.setBounds(91, 365, 283, 25);
    getContentPane().add(this.tfPassword);
    
    this.lblContrasenha = new JLabel("Contrase�a:");
    this.lblContrasenha.setFont(new Font("Tahoma", 0, 15));
    this.lblContrasenha.setBounds(10, 362, 85, 33);
    getContentPane().add(this.lblContrasenha);
    
    this.tfColor = new JTextField();
    this.tfColor.setVisible(false);
    this.tfColor.setEditable(false);
    this.tfColor.setFont(new Font("Tahoma", 1, 15));
    this.tfColor.setBounds(0, 0, 101, 33);
    contentPane.add(this.tfColor);
    
    this.tfUsuario = new JComboBox();
    this.tfUsuario.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Login.this.Consultar();
      }
    });
    this.tfUsuario.setBounds(91, 64, 235, 25);
    contentPane.add(this.tfUsuario);
    
    this.lblUsuario = new JLabel("Usuario:");
    this.lblUsuario.setForeground(Color.WHITE);
    this.lblUsuario.setFont(new Font("Tahoma", 0, 15));
    this.lblUsuario.setBounds(31, 59, 85, 33);
    contentPane.add(this.lblUsuario);
    
    this.lblFoto = new JLabel("");
    this.lblFoto.setCursor(Cursor.getPredefinedCursor(12));
    this.lblFoto.setBorder(new LineBorder(null, 2));
    this.lblFoto.setBounds(91, 100, 283, 251);
    contentPane.add(this.lblFoto);
    
    ColorBlanco();
    cargarCBXUsuarios();
    this.lblFoto.setIcon(null);
  }
  
  private void Consultar()
  {
    String codigo = "";
    codigo = this.tfUsuario.getSelectedItem().toString().split("-")[1];
    if (codigo.isEmpty()) {
      return;
    }
    String sql = "select usuario_imagen, usuario_nombre from \"usuarios\" where usuario_codigo = " + codigo + ";";
    try
    {
      ResultSet rs = this.conexion.ejecutarSQLSelect(sql);
      while (rs.next())
      {
        InputStream is = rs.getBinaryStream(1);
        String nombre = rs.getString(2);
        
        BufferedImage bi = ImageIO.read(is);
        ImageIcon foto = new ImageIcon(bi);
        
        Image img = foto.getImage();
        Image newimg = img.getScaledInstance(283, 251, 4);
        
        ImageIcon newicon = new ImageIcon(newimg);
        
        this.lblFoto.setIcon(newicon);
      }
    }
    catch (Exception ex)
    {
      JOptionPane.showMessageDialog(this.rootPane, "exception: " + ex);
      ex.printStackTrace();
    }
  }
  
  private void CapturarColor()
  {
    String codigocolor = "1";
    Colores color = Colores.ConsutarColorFormularios(codigocolor);
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
    this.lblUsuario.setForeground(Color.RED);
    this.lblContrasenha.setForeground(Color.RED);
  }
  
  private void ColorBlanco()
  {
    this.lblContrasenha.setForeground(Color.WHITE);
  }
  
  private void ColorGris()
  {
    this.lblUsuario.setForeground(Color.GRAY);
    this.lblContrasenha.setForeground(Color.GRAY);
  }
  
  private void ColorAmarillo()
  {
    this.lblUsuario.setForeground(Color.YELLOW);
    this.lblContrasenha.setForeground(Color.YELLOW);
  }
  
  private void ColorVerde()
  {
    this.lblUsuario.setForeground(new Color(0, 128, 0));
    this.lblContrasenha.setForeground(new Color(0, 128, 0));
  }
  
  private void ColorAzul()
  {
    this.lblUsuario.setForeground(new Color(0, 0, 205));
    this.lblContrasenha.setForeground(new Color(0, 0, 205));
  }
  
  private void ColorMarron()
  {
    this.lblUsuario.setForeground(new Color(139, 69, 19));
    this.lblContrasenha.setForeground(new Color(139, 69, 19));
  }
  
  private void ColorPurpura()
  {
    this.lblUsuario.setForeground(new Color(148, 0, 211));
    this.lblContrasenha.setForeground(new Color(148, 0, 211));
  }
  
  private void ColorCyan()
  {
    this.lblUsuario.setForeground(Color.CYAN);
    this.lblContrasenha.setForeground(Color.CYAN);
  }
  
  private void ColorNaranja()
  {
    this.lblUsuario.setForeground(Color.ORANGE);
    this.lblContrasenha.setForeground(Color.ORANGE);
  }
  
  private void ColorVerdeFluor()
  {
    this.lblUsuario.setForeground(Color.GREEN);
    this.lblContrasenha.setForeground(Color.GREEN);
  }
  
  private void ColorNegro()
  {
    this.lblUsuario.setForeground(Color.BLACK);
    this.lblContrasenha.setForeground(Color.BLACK);
  }
  
  private void cargarCBXUsuarios()
  {
    listaUsuarios = new ArrayList();
    listaUsuarios = DaoUsuarios.ConsultarListaDeTodosLosUsuarios();
    for (int i = 0; i < listaUsuarios.size(); i++)
    {
      ModeloUsuarios m = (ModeloUsuarios)listaUsuarios.get(i);
      
      this.tfUsuario.addItem(m.getNombre() + "-" + m.getCodigo());
    }
  }
  
  private void IniciarSesion()
  {
    this.usuario = new ModeloUsuarios();
    String passdesc = this.cifra.Encriptar(this.tfPassword.getText());
    try
    {
      this.usuario = DaoUsuarios.ComprobarAcceso(this.tfUsuario.getSelectedItem().toString().split("-")[0], passdesc);
      if (this.usuario != null)
      {
        this.panel.setVisible(true);
        this.lblBienvenida.setIcon(new ImageIcon(new File("data/graficos/Bienvenida.gif").getAbsolutePath()));
        setBounds(getX(), getY(), 636, 400);
        setTitle("Acceso correcto");
        setLocationRelativeTo(null);
        
        JOptionPane.showMessageDialog(null, "�Enhorabuena!");
        if (this.usuario.getCodigo() == 1)
        {
          dispose();
          Principal main = new Principal();
          Principal.tfUsuario.setText(this.tfUsuario.getSelectedItem().toString().split("-")[0].concat("/Privilegio: Administrador"));
          main.setVisible(true);
          main.TituloUsuario();
          JOptionPane.showMessageDialog(null, this.tfUsuario.getSelectedItem() + ", bienvenido al sistema de " + DaoConfiguraciones.NombreEmpresa(), "", 1);
        }
        if (this.usuario.getCodigo() >= 2)
        {
          dispose();
          Principal main = new Principal();
          Principal.tfUsuario.setText(this.tfUsuario.getSelectedItem().toString().split("-")[0].concat("/Privilegio: Usuario com�n"));
          Principal.btnPrivilegios.doClick();
          main.setVisible(true);
          main.TituloUsuario();
          JOptionPane.showMessageDialog(null, this.tfUsuario.getSelectedItem().toString().split("-")[0] + ", bienvenido al sistema de " + DaoConfiguraciones.NombreEmpresa(), "", 1);
        }
      }
      else
      {
        JOptionPane.showMessageDialog(null, "Error en el usuario o la contrase�a, verifique \nsi est�n correctos y vuelva a intentar.", "Error", 0);
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
  
  private void Salir(WindowEvent evt)
  {
    int res = JOptionPane.showConfirmDialog(null, "�Est� seguro que desea salir?", "Salir", 0);
    if (res == 0) {
      dispose();
    } else {}
  }
  
  private boolean ValidarUsuarios()
  {
    if (this.tfUsuario.getSelectedItem().toString().isEmpty())
    {
      JOptionPane.showMessageDialog(null, "Ingrese el nombre del usuario", "Advertencia", 2);
      this.tfUsuario.requestFocus();
      return false;
    }
    if (this.tfPassword.getText().isEmpty())
    {
      JOptionPane.showMessageDialog(null, "Ingrese la contrase�a para iniciar sesi�n", "Advertencia", 2);
      this.tfPassword.requestFocus();
      return false;
    }
    return true;
  }
  
  private void AbrirTXT()
  {
    File f = new File(new File("data/demo/system.dll").getAbsolutePath());
    File f2 = new File(new File("data/demo/mapsystem.dll").getAbsolutePath());
    if (f.canRead())
    {
      String contenido = this.cripto.desencriptar(this.gestion.AbrirTexto(f), this.cripto.ClaveEncryption());
      System.out.println(contenido);
      String contenido2 = this.cripto.desencriptar(this.gestion.AbrirTexto(f2), this.cripto.ClaveEncryption());
      System.out.println(contenido2);
      System.out.println(contenido);
      String desde = contenido.split(":::")[2];
      String hasta = contenido.split(":::")[3];
      int D = Integer.parseInt(desde);
      int H = Integer.parseInt(hasta);
      
      D--;
      if (D > 0) {
        JOptionPane.showMessageDialog(null, "Le quedan " + D + " sesiones writeMemory[binaryCode:0110100101011001110101]");
      }
      if (D < 1)
      {
        JOptionPane.showMessageDialog(null, "La versi�n de prueba ha expirado, lleg� a inicar la sesiones permitidas desde la instalaci�n [binaryCode:01101001010110011101011: >" + contenido.split(":::")[2] + "<");
        System.exit(0);
      }
      this.tfLicencia.setText(String.valueOf(D));
      this.texto.setText("ax51a5xc:::strictfp:::" + D + ":::" + H + ":::651148265418\n".concat(contenido2));
      if (D > -1) {
        GuardarTXT();
      }
    }
  }
  
  private void GuardarTXT()
  {
    File f = new File(new File("data/demo/system.dll").getAbsolutePath());
    
    String encriptado = this.cripto.encriptar(this.texto.getText(), this.cripto.ClaveEncryption());
    
    this.gestion.GuardarTexto(f, encriptado);
  }    
}
