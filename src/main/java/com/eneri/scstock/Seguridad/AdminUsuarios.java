/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Seguridad;

import com.eneri.scstock.Apariencia.Colores;
import com.eneri.scstock.Apariencia.FondoPrincipal;
import com.eneri.scstock.Conector.DataBase;
import com.eneri.scstock.Herramientas.Validaciones;
import com.eneri.scstock.Modelos.ModeloUsuarios;
import com.eneri.scstock.Objetos.DaoUsuarios;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author RAscencio
 */
public class AdminUsuarios extends JDialog
{
    
  private JTextField tfUsuario;
  private JPasswordField tfPassword;
  public static List<ModeloUsuarios> listaUsuarios;
  public static DefaultTableModel modelo = new DefaultTableModel(null, new String[] { "C�digo", "Usuario", "Contrase�a" })
  {
    boolean[] columnEditables = new boolean[3];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private JTable tabla;
  private JButton btnEliminar;
  private JButton btnModificar;
  private JButton btnGuardar;
  private boolean existencia;
  private JTextField tfColor;
  private JLabel lblUsuario;
  private JLabel lblContrasenha;
  private JScrollPane scrollPane;
  private ModeloUsuarios usuario;
  CifrarPassword cifra = new CifrarPassword();
  FileInputStream fis;
  int longitudBytes;
  private JLabel lblFoto;
  File archivo;
  private JTextField tfCodigo;
  private DataBase conexion = new DataBase();
  private JLabel label_1;
  private JButton btnNuevo;
  private JLabel lblGif;
  private JButton btnCancelar;
  private JButton btnCargarImagen;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          AdminUsuarios dialog = new AdminUsuarios();
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
    });
  }
  
  public AdminUsuarios()
  {
    setResizable(false);
    setIconImage(Toolkit.getDefaultToolkit().getImage(AdminUsuarios.class.getResource("/Galeria/CopyUserSettings.png")));
    setTitle("Administrador de usuarios");
    setBounds(100, 100, 870, 600);
    getContentPane().setLayout(null);
    
    FondoPrincipal contentPanel = new FondoPrincipal();
    contentPanel.setForeground(Color.BLACK);
    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPanel);
    contentPanel.setLayout(null);
    
    this.btnGuardar = new JButton("Guardar");
    this.btnGuardar.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if ((evento.getKeyCode() == 10) && 
          (AdminUsuarios.this.ValidarUsuarios()))
        {
          AdminUsuarios.this.btnGuardar.setEnabled(false);
          AdminUsuarios.this.btnModificar.setEnabled(false);
          AdminUsuarios.this.btnEliminar.setEnabled(false);
          AdminUsuarios.this.RegistrarUsuario();
        }
      }
    });
    this.btnGuardar.setEnabled(false);
    this.btnGuardar.setIcon(new ImageIcon(AdminUsuarios.class.getResource("/Iconos/Guardar.png")));
    this.btnGuardar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (AdminUsuarios.this.ValidarUsuarios())
        {
          if (AdminUsuarios.this.btnGuardar.getText().equals("Guardar"))
          {
            AdminUsuarios.this.btnGuardar.setEnabled(false);
            AdminUsuarios.this.btnModificar.setEnabled(false);
            AdminUsuarios.this.btnEliminar.setEnabled(false);
            AdminUsuarios.this.RegistrarUsuario();
            AdminUsuarios.this.tabla.setEnabled(true);
            AdminUsuarios.this.btnNuevo.setEnabled(true);
            AdminUsuarios.this.btnCancelar.setEnabled(false);
          }
          else
          {
            AdminUsuarios.this.btnGuardar.setEnabled(false);
            AdminUsuarios.this.btnModificar.setEnabled(false);
            AdminUsuarios.this.btnEliminar.setEnabled(false);
            AdminUsuarios.this.ModificarUsuario();
            AdminUsuarios.this.tabla.setEnabled(true);
            AdminUsuarios.this.btnNuevo.setEnabled(true);
            AdminUsuarios.this.btnCancelar.setEnabled(false);
          }
          AdminUsuarios.this.lblGif.setVisible(false);
        }
      }
    });
    this.btnGuardar.setBounds(402, 203, 159, 57);
    getContentPane().add(this.btnGuardar);
    
    this.btnEliminar = new JButton("Eliminar");
    this.btnEliminar.setEnabled(false);
    this.btnEliminar.setIcon(new ImageIcon(AdminUsuarios.class.getResource("/Iconos/Eliminarx.png")));
    this.btnEliminar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        AdminUsuarios.this.VaciarCampos();
        AdminUsuarios.this.ImgPorDefecto();
        AdminUsuarios.this.btnEliminar.setEnabled(false);
      }
    });
    this.btnEliminar.setBounds(711, 511, 143, 50);
    getContentPane().add(this.btnEliminar);
    
    this.tfUsuario = new JTextField();
    this.tfUsuario.setEnabled(false);
    Validaciones validaciones = new Validaciones();
    validaciones.BloqueoNumerico(this.tfUsuario);
    this.tfUsuario.addKeyListener(new KeyAdapter()
    {
      public void keyTyped(KeyEvent evento)
      {
        if (AdminUsuarios.this.tfUsuario.getText().trim().length() > 19) {
          evento.consume();
        }
      }
      
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          AdminUsuarios.this.tfPassword.requestFocus();
        }
      }
    });
    Validaciones validar = new Validaciones();
    
    this.tfUsuario.setColumns(10);
    this.tfUsuario.setBounds(300, 98, 244, 23);
    getContentPane().add(this.tfUsuario);
    
    this.lblUsuario = new JLabel("Usuario:");
    this.lblUsuario.setBounds(250, 98, 60, 23);
    getContentPane().add(this.lblUsuario);
    
    this.lblContrasenha = new JLabel("Contrase�a:");
    this.lblContrasenha.setBounds(228, 144, 82, 23);
    getContentPane().add(this.lblContrasenha);
    
    this.tfPassword = new JPasswordField();
    this.tfPassword.setEnabled(false);
    this.tfPassword.addKeyListener(new KeyAdapter()
    {
      public void keyTyped(KeyEvent evento)
      {
        if (AdminUsuarios.this.tfPassword.getText().trim().length() > 19) {
          evento.consume();
        }
      }
      
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          AdminUsuarios.this.btnGuardar.requestFocus();
        }
      }
    });
    this.tfPassword.setBounds(300, 144, 244, 23);
    getContentPane().add(this.tfPassword);
    
    this.scrollPane = new JScrollPane();
    this.scrollPane.setBounds(215, 307, 639, 193);
    getContentPane().add(this.scrollPane);
    
    this.tabla = new JTable();
    this.tabla.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        if (AdminUsuarios.this.tabla.isEnabled())
        {
          AdminUsuarios.this.VaciarCampos();
          AdminUsuarios.this.btnModificar.setEnabled(true);
          AdminUsuarios.this.Consultar();
          AdminUsuarios.this.btnCancelar.setEnabled(true);
          AdminUsuarios.this.tfUsuario.setEnabled(false);
          AdminUsuarios.this.tfPassword.setEnabled(false);
          AdminUsuarios.this.btnEliminar.setEnabled(true);
        }
      }
    });
    this.scrollPane.setViewportView(this.tabla);
    this.tabla.setModel(modelo);
    this.tabla.getColumnModel().getColumn(0).setPreferredWidth(35);
    this.tabla.getColumnModel().getColumn(1).setPreferredWidth(142);
    
    this.btnModificar = new JButton("Modificar");
    this.btnModificar.setEnabled(false);
    this.btnModificar.setIcon(new ImageIcon(AdminUsuarios.class.getResource("/Iconos/Modificar.png")));
    this.btnModificar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        AdminUsuarios.this.btnModifica();
      }
    });
    this.btnModificar.setBounds(550, 511, 151, 50);
    getContentPane().add(this.btnModificar);
    
    this.label_1 = new JLabel("");
    this.label_1.setIcon(new ImageIcon(AdminUsuarios.class.getResource("/Galeria/admuser.png")));
    this.label_1.setBounds(15, 27, 190, 474);
    contentPanel.add(this.label_1);
    
    JTabbedPane tabbedPane_1 = new JTabbedPane(1);
    tabbedPane_1.setBounds(5, 11, 200, 489);
    contentPanel.add(tabbedPane_1);
    
    this.tfColor = new JTextField();
    this.tfColor.setEditable(false);
    this.tfColor.setVisible(false);
    this.tfColor.setBounds(15, 277, 86, 20);
    contentPanel.add(this.tfColor);
    this.tfColor.setColumns(10);
    
    this.lblFoto = new JLabel("");
    this.lblFoto.setBorder(new LineBorder(null, 2));
    this.lblFoto.setBounds(571, 11, 283, 251);
    contentPanel.add(this.lblFoto);
    
    this.btnCargarImagen = new JButton("Cargar imagen");
    this.btnCargarImagen.setEnabled(false);
    this.btnCargarImagen.setIcon(new ImageIcon(AdminUsuarios.class.getResource("/Iconos/imagen.PNG")));
    this.btnCargarImagen.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        AdminUsuarios.this.AbrirImagen();
      }
    });
    this.btnCargarImagen.setBounds(571, 265, 283, 31);
    contentPanel.add(this.btnCargarImagen);
    
    JLabel label = new JLabel("Usuario:");
    label.setForeground(Color.WHITE);
    label.setBounds(250, 50, 60, 23);
    contentPanel.add(label);
    
    this.tfCodigo = new JTextField();
    this.tfCodigo.setEditable(false);
    this.tfCodigo.setColumns(10);
    this.tfCodigo.setBounds(300, 50, 82, 23);
    contentPanel.add(this.tfCodigo);
    
    this.btnNuevo = new JButton("Nuevo");
    this.btnNuevo.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        AdminUsuarios.this.VaciarCampos();
        int codigo = DaoUsuarios.IncrementarCodigo() + 1;
        AdminUsuarios.this.tfCodigo.setEditable(false);
        AdminUsuarios.this.lblGif.setVisible(true);
        AdminUsuarios.this.tfCodigo.setText(String.valueOf(codigo));
        AdminUsuarios.this.btnNuevo.setEnabled(false);
        AdminUsuarios.this.tabla.setEnabled(false);
        AdminUsuarios.this.btnGuardar.setText("Guardar");
        AdminUsuarios.this.btnCancelar.setEnabled(true);
        AdminUsuarios.this.tfUsuario.requestFocus();
        AdminUsuarios.this.btnGuardar.setEnabled(true);
        AdminUsuarios.this.tfUsuario.setEnabled(true);
        AdminUsuarios.this.tfPassword.setEnabled(true);
        AdminUsuarios.this.btnCargarImagen.setEnabled(true);
        AdminUsuarios.this.lblFoto.setIcon(null);
      }
    });
    this.btnNuevo.setIcon(new ImageIcon(AdminUsuarios.class.getResource("/Iconos/Nuevo.png")));
    this.btnNuevo.setBounds(215, 203, 159, 57);
    contentPanel.add(this.btnNuevo);
    
    this.lblGif = new JLabel("");
    this.lblGif.setVisible(false);
    this.lblGif.setIcon(new ImageIcon(AdminUsuarios.class.getResource("/Galeria/NuevoReg.gif")));
    this.lblGif.setBounds(392, 50, 92, 23);
    contentPanel.add(this.lblGif);
    
    this.btnCancelar = new JButton("Cancelar");
    this.btnCancelar.setEnabled(false);
    this.btnCancelar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        AdminUsuarios.this.VaciarCampos();
        AdminUsuarios.this.lblFoto.setIcon(null);
        AdminUsuarios.this.tabla.setEnabled(true);
        AdminUsuarios.this.btnNuevo.setEnabled(true);
        AdminUsuarios.this.btnModificar.setEnabled(false);
        AdminUsuarios.this.btnGuardar.setText("Guardar");
        AdminUsuarios.this.btnGuardar.setEnabled(false);
        AdminUsuarios.this.btnCancelar.setEnabled(false);
        AdminUsuarios.this.lblGif.setVisible(false);
        AdminUsuarios.this.tfUsuario.setEnabled(false);
        AdminUsuarios.this.tfPassword.setEnabled(false);
        AdminUsuarios.this.btnCargarImagen.setEnabled(false);
      }
    });
    this.btnCancelar.setIcon(new ImageIcon(AdminUsuarios.class.getResource("/IconosMin/del.png")));
    this.btnCancelar.setBounds(215, 265, 346, 31);
    contentPanel.add(this.btnCancelar);
    
    JTabbedPane tabbedPane = new JTabbedPane(1);
    tabbedPane.setBounds(215, 11, 346, 181);
    getContentPane().add(tabbedPane);
    cargarTodosLosRegistros();
    ColorBlanco();
    this.conexion.crearConexion();
    ImgPorDefecto();
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
    this.lblUsuario.setForeground(Color.WHITE);
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
  
  public void cargarTodosLosRegistrosConContrasenha()
  {
    listaUsuarios = new ArrayList();
    listaUsuarios = DaoUsuarios.ConsultarListaDeTodosLosUsuarios();
    ActualizarTablaPrincipalConContrasenha();
  }
  
  private static void ActualizarTablaPrincipalConContrasenha()
  {
    while (modelo.getRowCount() > 0) {
      modelo.removeRow(0);
    }
    String[] campos = new String[3];
    for (int i = 0; i < listaUsuarios.size(); i++)
    {
      modelo.addRow(campos);
      ModeloUsuarios usuario = (ModeloUsuarios)listaUsuarios.get(i);
      
      modelo.setValueAt(Integer.valueOf(usuario.getCodigo()), i, 0);
      modelo.setValueAt(usuario.getNombre(), i, 1);
      modelo.setValueAt(usuario.getPassword(), i, 2);
    }
  }
  
  public void cargarTodosLosRegistros()
  {
    listaUsuarios = new ArrayList();
    listaUsuarios = DaoUsuarios.ConsultarListaDeTodosLosUsuarios();
    ActualizarTablaPrincipal();
  }
  
  private static void ActualizarTablaPrincipal()
  {
    while (modelo.getRowCount() > 0) {
      modelo.removeRow(0);
    }
    String[] campos = new String[3];
    for (int i = 0; i < listaUsuarios.size(); i++)
    {
      modelo.addRow(campos);
      ModeloUsuarios usuario = (ModeloUsuarios)listaUsuarios.get(i);
      
      modelo.setValueAt(Integer.valueOf(usuario.getCodigo()), i, 0);
      modelo.setValueAt(usuario.getNombre(), i, 1);
      modelo.setValueAt(usuario.getPassword(), i, 2);
    }
  }
  
  private void Eliminar()
  {
    int seleccion = this.tabla.getSelectedRow();
    try
    {
      DefaultTableModel m = (DefaultTableModel)this.tabla.getModel();
      String codigo = this.tabla.getValueAt(seleccion, 0).toString();
      String nombre = this.tabla.getValueAt(seleccion, 1).toString();
      if (codigo.equals("1"))
      {
        JOptionPane.showMessageDialog(null, "No se puede eliminar al usuario administrador", "Error", 0);
        JOptionPane.showMessageDialog(null, "El primer usuario es el usuario administrador", "Mensaje", 1);
      }
      else
      {
        int yes = JOptionPane.showConfirmDialog(this, "�Est� seguro que desea eliminar al usuario " + 
          nombre + "?", "Aviso", 0);
        if (yes == 0)
        {
          DaoUsuarios.EliminarUsuario(Integer.parseInt(codigo));
          cargarTodosLosRegistros();
        }
      }
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(null, "Try catch exeption: " + e.getMessage());
    }
  }
  
  private void VaciarCampos()
  {
    this.tfUsuario.setText("");
    this.tfPassword.setText("");
    this.tfCodigo.setText("");
  }
  
  private boolean ValidarUsuarios()
  {
    if (this.tfUsuario.getText().isEmpty())
    {
      JOptionPane.showMessageDialog(null, "No se puede registrar un usuario sin nombre", "Error", 0);
      this.tfUsuario.requestFocus();
      return false;
    }
    if (this.tfPassword.getText().isEmpty())
    {
      JOptionPane.showMessageDialog(null, "No se puede registrar un usuario sin contrase�a", "Error", 0);
      this.tfPassword.requestFocus();
      return false;
    }
    return true;
  }
  
  private void ModificarUsuario()
  {
    if (ValidarUsuarios())
    {
      int seleccion = this.tabla.getSelectedRow();
      ModeloUsuarios usuario = new ModeloUsuarios();
      String password = this.cifra.Encriptar(this.tfPassword.getText());
      usuario.setCodigo(Integer.parseInt(String.valueOf(this.tabla.getValueAt(seleccion, 0))));
      usuario.setNombre(this.tfUsuario.getText().toUpperCase());
      usuario.setPassword(password);
      if (!this.existencia) {
        if (!DaoUsuarios.UsuarioExistente(usuario))
        {
          DaoUsuarios.EditarUsuario(usuario, this.fis, this.longitudBytes);
          VaciarCampos();
          cargarTodosLosRegistros();
          this.btnModificar.setEnabled(false);
        }
        else
        {
          JOptionPane.showMessageDialog(null, "�Ya existe un usuario con este nombre!", "Advertencia", 1);
        }
      }
    }
  }
  
  private void AbrirImagen()
  {
    this.lblFoto.setIcon(null);
    
    FileDialog seleccionado = new FileDialog(this, "Abrir archivo", 0);
    seleccionado.show();
    String curFile = seleccionado.getFile();
    String filename = seleccionado.getDirectory() + curFile;
    setCursor(Cursor.getPredefinedCursor(12));
    File f = new File(filename);
    this.archivo = f;
    JFileChooser jFileChooser = new JFileChooser();
    try
    {
      this.fis = new FileInputStream(this.archivo);
      if (this.fis == null) {
        return;
      }
      jFileChooser.setSelectedFile(this.archivo);
      
      this.longitudBytes = ((int)jFileChooser.getSelectedFile().length());
      try
      {
        Image icono = ImageIO.read(f).getScaledInstance(
          this.lblFoto.getWidth(), this.lblFoto.getHeight(), 1);
        this.lblFoto.setIcon(new ImageIcon(icono));
        this.lblFoto.updateUI();
      }
      catch (IOException ex)
      {
        JOptionPane.showMessageDialog(this.rootPane, "imagen: " + ex);
      }
      return;
    }
    catch (FileNotFoundException ex)
    {
      ex.printStackTrace();
    }
  }
  
  private void ImgPorDefecto()
  {
    try
    {
      ImageIcon icono = new ImageIcon(new File("data/graficos/xuser.jpg").getAbsolutePath());
      JFileChooser jFileChooser = new JFileChooser();
      File imagen = new File(new File("/data/graficos/xuser.jpg").getAbsolutePath());
      this.archivo = imagen;
      
      jFileChooser.setSelectedFile(imagen);
      this.longitudBytes = icono.getImage().toString().length();
      System.out.println(this.longitudBytes);
      this.lblFoto.setIcon(icono);
      this.lblFoto.updateUI();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  private void RegistrarUsuario()
  {
    int codigo = DaoUsuarios.IncrementarCodigo() + 1;
    if (codigo <= 10)
    {
      ModeloUsuarios usuarios = new ModeloUsuarios();
      String password = this.cifra.Encriptar(this.tfPassword.getText());
      usuarios.setCodigo(codigo);
      usuarios.setNombre(this.tfUsuario.getText().toUpperCase());
      usuarios.setPassword(password);
      if (!this.existencia) {
        if (!DaoUsuarios.UsuarioExistente(usuarios))
        {
          DaoUsuarios.RegistrarUsuario(usuarios, this.fis, this.longitudBytes);
          this.lblFoto.setIcon(null);
          this.tfCodigo.setText("");
          this.tfUsuario.setText("");
          cargarTodosLosRegistros();
          VaciarCampos();
        }
        else
        {
          JOptionPane.showMessageDialog(null, "�Ya existe un usuario con este nombre!", "Advertencia", 2);
          this.tfUsuario.requestFocus();
          this.tfUsuario.selectAll();
          this.btnGuardar.setEnabled(true);
          this.btnModificar.setEnabled(false);
        }
      }
    }
    else
    {
      JOptionPane.showMessageDialog(null, "La cantidad de usuarios ha llegado a su l�mite", "Error", 0);
      JOptionPane.showMessageDialog(null, "Elimine un usuario para registrar uno nuevo", "", 2);
    }
  }
  
  private void Consultar()
  {
    int selecc = this.tabla.getSelectedRow();
    String codigo = "";
    if (selecc != -1) {
      codigo = String.valueOf(this.tabla.getValueAt(selecc, 0));
    } else {
      return;
    }
    this.tfCodigo.setText(codigo);
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
        this.tfUsuario.setText(nombre);
      }
    }
    catch (Exception ex)
    {
      JOptionPane.showMessageDialog(this.rootPane, "exception: " + ex);
      ex.printStackTrace();
    }
  }
  
  private void btnModifica()
  {
    int seleccion = this.tabla.getSelectedRow();
    String codigo = String.valueOf(this.tabla.getValueAt(seleccion, 0));
    this.tfCodigo.setText(codigo);
    this.btnGuardar.setText("Guardar ");
    this.btnGuardar.setEnabled(true);
    this.btnModificar.setEnabled(false);
    this.btnNuevo.setEnabled(false);
    this.tabla.setEnabled(false);
    this.tfUsuario.setEnabled(true);
    this.tfPassword.setEnabled(true);
    this.btnCargarImagen.setEnabled(true);
  }    
}
