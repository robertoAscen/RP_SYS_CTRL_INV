/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Internet;

import com.eneri.scstock.Apariencia.FondoTransacciones;
import com.eneri.scstock.Excel.export_excel;
import com.eneri.scstock.Gestor.Gestionar;
import com.eneri.scstock.Modelos.ModeloMarcas;
import com.eneri.scstock.Modelos.ModeloProductos;
import com.eneri.scstock.Modelos.ModeloProveedores;
import com.eneri.scstock.Objetos.DaoConfiguraciones;
import com.eneri.scstock.Objetos.DaoProductos;
import com.eneri.scstock.Objetos.DaoProveedores;
import com.eneri.scstock.Seguridad.CifrarPassword;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author RAscencio
 */
public class Solicitud extends JDialog
{
    
  Gestionar gestion = new Gestionar();
  File archivo;
  private JTable tbProductos;
  public static DefaultTableModel modelo = new DefaultTableModel(null, new String[] { "C�digo", "Descripci�n del producto", 
    "Stock", "Marca", "Cantidad" })
  {
    boolean[] columnEditables = {
      false,false,false,false,true };
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  public static DefaultTableModel modeloProveedores = new DefaultTableModel(null, new String[] { "C�digo", "Proveedor", "Email" })
  {
    boolean[] columnEditables = new boolean[3];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private JTextField tfBuscadorCodigo;
  private JTextField tfCondStock;
  private JCheckBox chckbxSinStock;
  public static List<ModeloProductos> listaProductos;
  public static List<ModeloProveedores> listaProveedores;
  private JTextArea tfCorreo;
  private JComboBox comboBox;
  private JScrollPane scrollPane;
  private JScrollPane scrollPaneCorreo;
  private JButton btnProcesar;
  private JButton btnSolicitarProductos;
  private JButton btnEnviar;
  private JLabel label;
  private JTabbedPane tabbedPane_1;
  private JLabel lblSolocitarProductoPor;
  private JRadioButton rdbtnxlsx;
  private JRadioButton rdbtnxls;
  private JTextField tfEstadoConexion;
  private JLabel lblSenhal;
  private JPanel panel;
  private JLabel label_2;
  private JLabel lblPorFavorEspere;
  private String correo = DaoConfiguraciones.CorreoEmpresa();
  private String password = DaoConfiguraciones.PasswordCorreoEmpresa();
  CifrarPassword cifra = new CifrarPassword();
  private String psswddes = this.cifra.Desencriptar(this.password);
  private JTextField tfAsunto;
  
  public static void main(String[] args)
  {
    try
    {
      Solicitud dialog = new Solicitud();
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
  
  public Solicitud()
  {
    setTitle("Pedido");
    setIconImage(Toolkit.getDefaultToolkit().getImage(Solicitud.class.getResource("/IconosMin/pedidosMin.png")));
    setBounds(100, 100, 1000, 700);
    
    FondoTransacciones contentPane = new FondoTransacciones();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    this.panel = new JPanel();
    this.panel.setVisible(false);
    
    this.tfAsunto = new JTextField();
    this.tfAsunto.setCursor(Cursor.getPredefinedCursor(2));
    this.tfAsunto.setForeground(Color.WHITE);
    this.tfAsunto.setOpaque(false);
    
    this.tfAsunto.setBounds(355, 350, 619, 22);
    contentPane.add(this.tfAsunto);
    this.panel.setBounds(82, 108, 623, 181);
    contentPane.add(this.panel);
    this.panel.setLayout(null);
    
    this.lblPorFavorEspere = new JLabel("Por favor, espere...");
    this.lblPorFavorEspere.setHorizontalTextPosition(0);
    this.lblPorFavorEspere.setHorizontalAlignment(0);
    this.lblPorFavorEspere.setForeground(Color.WHITE);
    this.lblPorFavorEspere.setFont(new Font("Book Antiqua", 1, 44));
    this.lblPorFavorEspere.setBackground(Color.BLACK);
    this.lblPorFavorEspere.setBounds(96, 352, 430, 38);
    this.panel.add(this.lblPorFavorEspere);
    
    this.label_2 = new JLabel("");
    this.label_2.setIcon(new ImageIcon("C:\\Users\\Gabriel\\Desktop\\tumblr_nltc6y6pau1rx5rfmo1_1280.gif"));
    this.label_2.setBounds(0, 0, 623, 253);
    this.panel.add(this.label_2);
    
    JLabel lblFiltrar = new JLabel("Con stock menor a:");
    lblFiltrar.setForeground(Color.WHITE);
    lblFiltrar.setBounds(614, 77, 121, 20);
    getContentPane().add(lblFiltrar);
    
    this.chckbxSinStock = new JCheckBox("S�lo sin stock");
    this.chckbxSinStock.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        if (Solicitud.this.chckbxSinStock.isSelected()) {
          Solicitud.this.tfCondStock.setEnabled(false);
        } else {
          Solicitud.this.tfCondStock.setEnabled(true);
        }
      }
    });
    this.chckbxSinStock.setForeground(Color.WHITE);
    this.chckbxSinStock.setBounds(610, 41, 133, 23);
    this.chckbxSinStock.setOpaque(false);
    getContentPane().add(this.chckbxSinStock);
    
    this.scrollPane = new JScrollPane();
    this.scrollPane.setOpaque(false);
    this.scrollPane.getViewport().setOpaque(false);
    this.scrollPane.setBounds(6, 108, 968, 204);
    getContentPane().add(this.scrollPane);
    
    this.tbProductos = new JTable();
    this.tbProductos.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent ev)
      {
        if ((ev.getKeyCode() == 127) && 
          (Solicitud.this.tbProductos.getSelectedRow() != -1)) {
          Solicitud.modelo.removeRow(Solicitud.this.tbProductos.getSelectedRow());
        }
      }
    });
    this.tbProductos.setModel(modelo);
    this.tbProductos.getColumnModel().getColumn(0).setPreferredWidth(70);
    this.tbProductos.getColumnModel().getColumn(1).setPreferredWidth(325);
    this.tbProductos.getColumnModel().getColumn(2).setPreferredWidth(15);
    this.tbProductos.getColumnModel().getColumn(3).setPreferredWidth(123);
    this.tbProductos.setOpaque(false);
    this.scrollPane.setViewportView(this.tbProductos);
    
    this.tfBuscadorCodigo = new JTextField();
    this.tfBuscadorCodigo.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        Solicitud.this.BuscarProductoCodigo();
      }
    });
    this.tfBuscadorCodigo.setToolTipText("Buscar producto por c�digo");
    this.tfBuscadorCodigo.setText("");
    this.tfBuscadorCodigo.setFont(new Font("Tahoma", 1, 13));
    this.tfBuscadorCodigo.setColumns(10);
    this.tfBuscadorCodigo.setBounds(6, 81, 154, 22);
    getContentPane().add(this.tfBuscadorCodigo);
    
    JLabel label_1 = new JLabel("Buscar por c�digo:");
    label_1.setForeground(Color.WHITE);
    label_1.setFont(new Font("Tahoma", 0, 15));
    label_1.setBounds(6, 62, 121, 19);
    getContentPane().add(label_1);
    
    this.tfCondStock = new JTextField();
    this.tfCondStock.setBounds(729, 76, 73, 23);
    contentPane.add(this.tfCondStock);
    this.tfCondStock.setColumns(10);
    
    this.btnProcesar = new JButton("Procesar");
    this.btnProcesar.setIcon(new ImageIcon(Solicitud.class.getResource("/Iconos/process.png")));
    this.btnProcesar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (Solicitud.this.chckbxSinStock.isSelected()) {
          Solicitud.this.ConsultarSinStock();
        } else if (Solicitud.this.tfCondStock.getText().trim().length() > 0) {
          Solicitud.this.ConsultarStockMenor();
        } else {
          Solicitud.this.cargarTodosLosRegistros();
        }
      }
    });
    this.btnProcesar.setBounds(812, 44, 162, 55);
    
    contentPane.add(this.btnProcesar);
    
    this.scrollPaneCorreo = new JScrollPane();
    this.scrollPaneCorreo.setOpaque(false);
    this.scrollPaneCorreo.getViewport().setOpaque(false);
    this.scrollPaneCorreo.setBounds(294, 411, 680, 174);
    contentPane.add(this.scrollPaneCorreo);
    
    this.tfCorreo = new JTextArea();
    this.tfCorreo.setLineWrap(true);
    this.scrollPaneCorreo.setViewportView(this.tfCorreo);
    this.tfCorreo.setFont(new Font("Tahoma", 0, 15));
    this.tfCorreo.setForeground(Color.WHITE);
    this.tfCorreo.setOpaque(false);
    
    this.btnSolicitarProductos = new JButton("Preparar");
    this.btnSolicitarProductos.setIcon(new ImageIcon(Solicitud.class.getResource("/Iconos/prepararcorreo.png")));
    this.btnSolicitarProductos.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if ((Solicitud.this.FormatoValido()) && (Solicitud.this.Conexion()))
        {
          Solicitud.this.panel.setVisible(true);
          Solicitud.this.Exporta();
          Solicitud.this.btnEnviar.setEnabled(true);
          Solicitud.this.AbrirDocumento();
        }
      }
    });
    this.btnSolicitarProductos.setBounds(614, 596, 175, 55);
    contentPane.add(this.btnSolicitarProductos);
    
    this.btnEnviar = new JButton("Enviar");
    this.btnEnviar.setEnabled(false);
    this.btnEnviar.setIcon(new ImageIcon(Solicitud.class.getResource("/Iconos/enviarcorreo.png")));
    this.btnEnviar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Solicitud.this.mandarCorreo();
      }
    });
    this.btnEnviar.setBounds(799, 596, 175, 55);
    contentPane.add(this.btnEnviar);
    
    this.comboBox = new JComboBox();
    //AutoCompleteDecorator.decorate(this.comboBox);
    this.comboBox.setEditable(true);
    this.comboBox.setBounds(614, 11, 360, 23);
    contentPane.add(this.comboBox);
    
    JButton btnRemover = new JButton("Quitar");
    btnRemover.setMnemonic('Q');
    btnRemover.setIcon(new ImageIcon(Solicitud.class.getResource("/IconosMin/del.png")));
    btnRemover.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Solicitud.this.RemoverFilas();
      }
    });
    btnRemover.setBounds(853, 320, 121, 24);
    contentPane.add(btnRemover);
    
    JLabel lblMensaje = new JLabel("Mensaje:");
    lblMensaje.setForeground(Color.WHITE);
    lblMensaje.setFont(new Font("Tahoma", 0, 15));
    lblMensaje.setBounds(294, 388, 121, 23);
    contentPane.add(lblMensaje);
    
    this.label = new JLabel("");
    this.label.setIcon(new ImageIcon(Solicitud.class.getResource("/Galeria/email-gaealfyed.png")));
    this.label.setBounds(6, 357, 274, 228);
    contentPane.add(this.label);
    
    JTabbedPane tabbedPane = new JTabbedPane(1);
    tabbedPane.setBounds(6, 350, 274, 235);
    contentPane.add(tabbedPane);
    
    this.lblSolocitarProductoPor = new JLabel("Pedidos");
    this.lblSolocitarProductoPor.setHorizontalAlignment(0);
    this.lblSolocitarProductoPor.setHorizontalTextPosition(0);
    this.lblSolocitarProductoPor.setForeground(Color.WHITE);
    this.lblSolocitarProductoPor.setFont(new Font("Book Antiqua", 1, 44));
    this.lblSolocitarProductoPor.setBackground(Color.BLACK);
    this.lblSolocitarProductoPor.setBounds(25, 13, 430, 38);
    contentPane.add(this.lblSolocitarProductoPor);
    
    this.tabbedPane_1 = new JTabbedPane(1);
    this.tabbedPane_1.setBounds(6, 6, 468, 58);
    contentPane.add(this.tabbedPane_1);
    
    this.rdbtnxls = new JRadioButton(".xls");
    this.rdbtnxls.setSelected(true);
    this.rdbtnxls.setForeground(Color.WHITE);
    this.rdbtnxls.setOpaque(false);
    this.rdbtnxls.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        if (Solicitud.this.rdbtnxls.isSelected()) {
          Solicitud.this.rdbtnxlsx.setSelected(false);
        }
      }
    });
    this.rdbtnxls.setBounds(514, 596, 80, 23);
    contentPane.add(this.rdbtnxls);
    
    this.rdbtnxlsx = new JRadioButton(".xlsx");
    this.rdbtnxlsx.setForeground(Color.WHITE);
    this.rdbtnxlsx.setOpaque(false);
    this.rdbtnxlsx.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        if (Solicitud.this.rdbtnxlsx.isSelected()) {
          Solicitud.this.rdbtnxls.setSelected(false);
        }
      }
    });
    this.rdbtnxlsx.setBounds(514, 622, 80, 23);
    contentPane.add(this.rdbtnxlsx);
    
    this.tfEstadoConexion = new JTextField();
    this.tfEstadoConexion.setEnabled(false);
    this.tfEstadoConexion.setEditable(false);
    this.tfEstadoConexion.setForeground(Color.WHITE);
    this.tfEstadoConexion.setBackground(Color.RED);
    this.tfEstadoConexion.setText("Desconectado");
    this.tfEstadoConexion.setBounds(68, 634, 106, 20);
    contentPane.add(this.tfEstadoConexion);
    this.tfEstadoConexion.setColumns(10);
    
    JLabel lblConexin = new JLabel("Conexi�n:");
    lblConexin.setForeground(Color.WHITE);
    lblConexin.setBounds(6, 637, 73, 14);
    contentPane.add(lblConexin);
    
    this.lblSenhal = new JLabel("");
    this.lblSenhal.setIcon(new ImageIcon(Solicitud.class.getResource("/Iconos/NotConected.png")));
    this.lblSenhal.setBounds(184, 622, 46, 40);
    contentPane.add(this.lblSenhal);
    
    JLabel lblProveedor = new JLabel("Proveedor:");
    lblProveedor.setForeground(Color.WHITE);
    lblProveedor.setBounds(499, 11, 121, 20);
    contentPane.add(lblProveedor);
    
    JButton btnGmailSettings = new JButton("Ayuda");
    btnGmailSettings.setIcon(new ImageIcon(Solicitud.class.getResource("/com/sun/java/swing/plaf/windows/icons/Question.gif")));
    btnGmailSettings.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Solicitud.this.Ayuda();
      }
    });
    btnGmailSettings.setToolTipText("Abre el navegador para configurar el gmail y asi permitir iniciar sesi�n desde este software.");
    btnGmailSettings.setBounds(341, 612, 133, 39);
    contentPane.add(btnGmailSettings);
    
    JLabel lblAsunto = new JLabel("Asunto:");
    lblAsunto.setForeground(Color.WHITE);
    lblAsunto.setFont(new Font("Tahoma", 0, 15));
    lblAsunto.setBounds(294, 350, 121, 20);
    contentPane.add(lblAsunto);
    
    cargarCBXProveedores();
    cargarTodosLosRegistros();
  }
  
  private void RemoverFilas()
  {
    int fila = this.tbProductos.getSelectedRow();
    if (fila >= 0)
    {
      int[] filasselec = this.tbProductos.getSelectedRows();
      for (int i = 0; i < filasselec.length; i++) {
        modelo.removeRow(filasselec[i]);
      }
    }
    else
    {
      JOptionPane.showMessageDialog(null, "Por favor seleccione por lo menos una fila", "Aviso", 0);
    }
  }
  
  private void cargarCBXProveedores()
  {
    listaProveedores = new ArrayList();
    listaProveedores = DaoProveedores.consultarTodosLosProveedores("proveedor_nombre");
    for (int i = 0; i < listaProveedores.size(); i++)
    {
      ModeloProveedores m = (ModeloProveedores)listaProveedores.get(i);
      
      this.comboBox.addItem(m.getNombre() + "-" + m.getCodigo());
    }
  }
  
  public void cargarTodosLosRegistros()
  {
    listaProductos = new ArrayList();
    listaProductos = DaoProductos.ConsProdXProv(Integer.parseInt(String.valueOf(this.comboBox.getSelectedItem()).split("-")[1]));
    ActualizarTabla();
    this.tfBuscadorCodigo.setText("");
  }
  
  private void ConsultarStockMenor()
  {
    listaProductos = new ArrayList();
    listaProductos = DaoProductos.ConsultarPorCondStock(Double.parseDouble(this.tfCondStock.getText()), Integer.parseInt(String.valueOf(this.comboBox.getSelectedItem()).split("-")[1]));
    ActualizarTabla();
  }
  
  private void ConsultarSinStock()
  {
    listaProductos = new ArrayList();
    listaProductos = DaoProductos.ConsProdSinStockXProv(Integer.parseInt(String.valueOf(this.comboBox.getSelectedItem()).split("-")[1]));
    ActualizarTabla();
  }
  
  private void BuscarProductoCodigo()
  {
    listaProductos = new ArrayList();
    listaProductos = DaoProductos.BuscarProductoCodigo(this.tfBuscadorCodigo.getText());
    ActualizarTabla();
  }
  
  private static void ActualizarTabla()
  {
    while (modelo.getRowCount() > 0) {
      modelo.removeRow(0);
    }
    String[] campos = new String[4];
    for (int i = 0; i < listaProductos.size(); i++)
    {
      modelo.addRow(campos);
      ModeloProductos producto = (ModeloProductos)listaProductos.get(i);
      
      modelo.setValueAt(producto.getCodigo(), i, 0);
      modelo.setValueAt(producto.getDescripcion(), i, 1);
      modelo.setValueAt(Double.valueOf(producto.getStock()), i, 2);
      modelo.setValueAt(producto.getMarcas().getDescripcion(), i, 3);
      modelo.setValueAt("1", i, 4);
    }
  }
  
  public void mandarCorreo()
  {
    String correoEnvia = this.correo;
    String claveCorreo = this.psswddes;
    
    Properties properties = new Properties();
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.port", "587");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.user", correoEnvia);
    properties.put("mail.password", claveCorreo);
    
    Session session = Session.getInstance(properties, null);
    int aviso = 0;
    try
    {
      MimeMessage mimeMessage = new MimeMessage(session);
      
      mimeMessage.setFrom(new InternetAddress(correoEnvia, DaoConfiguraciones.NombreEmpresa()));
      
      InternetAddress[] internetAddresses = {
        new InternetAddress(DaoProveedores.CorreoProveedor(Integer.parseInt(this.comboBox.getSelectedItem().toString().split("-")[1]))) };
      
      mimeMessage.setRecipients(Message.RecipientType.TO, 
        internetAddresses);
      
      mimeMessage.setSubject(this.tfAsunto.getText());
      
      MimeBodyPart mimeBodyPart = new MimeBodyPart();
      mimeBodyPart.setText(this.tfCorreo.getText());
      
      MimeBodyPart mimeBodyPartAdjunto = new MimeBodyPart();
      mimeBodyPartAdjunto.attachFile(Adjunto());
      
      Multipart multipart = new MimeMultipart();
      multipart.addBodyPart(mimeBodyPart);
      multipart.addBodyPart(mimeBodyPartAdjunto);
      
      mimeMessage.setContent(multipart);
      
      Transport transport = session.getTransport("smtp");
      transport.connect(correoEnvia, claveCorreo);
      transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
      
      transport.close();
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
      JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
      JOptionPane.showMessageDialog(null, "Por favor, verifique si tiene acceso a internet");
      aviso = 1;
    }
    if (aviso == 0) {
      JOptionPane.showMessageDialog(null, "�Correo electr�nico enviado exitosamente!");
    }
  }
  
  private String fecha()
  {
    Date fecha = new Date();
    SimpleDateFormat fechaFormato = new SimpleDateFormat("dd-MM-yyyy");
    return fechaFormato.format(fecha);
  }
  
  public static String hora()
  {
    Calendar Cal = Calendar.getInstance();
    String hora = String.valueOf(Cal.get(11));
    String minuto = String.valueOf(Cal.get(12));
    String segundo = String.valueOf(Cal.get(13));
    String milisegundo = String.valueOf(Cal.get(14));
    
    String hora1 = "";
    String minuto1 = "";
    String segundo1 = "";
    String milisegundo1 = "";
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
    if (milisegundo.length() == 1) {
      milisegundo1 = "0" + Cal.get(14);
    } else {
      milisegundo1 = milisegundo;
    }
    horacompleta = hora1 + "-" + minuto1 + "-" + segundo1 + "-" + milisegundo1;
    
    return horacompleta;
  }
  
  private String Adjunto()
  {
    String ruta = String.valueOf(archivo);
    System.out.println(ruta);
    return ruta;
  }
  
  private void Exporta()
  {
    File f = new File(new File("data/doc/correo/" + DaoConfiguraciones.NombreEmpresa() + "_" + fecha() + "_" + hora() + FormatoArchivo()).getAbsolutePath());
    this.archivo = f;
    try
    {
      List<JTable> tb = new ArrayList();
      tb.add(this.tbProductos);
      
      export_excel excelExporter = new export_excel(tb, f);
      if (excelExporter.export()) {
        this.panel.setVisible(false);
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }
  
  private void AbrirDocumento()
  {
    int resp = JOptionPane.showConfirmDialog(null, "�Desea abrir el documento antes del env�o?", "Abrir documento", 0);
    System.out.println(resp);
    if (resp == 0)
    {
      Desktop desktop = Desktop.getDesktop();
      try
      {
        desktop.open(this.archivo);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
  
  private String FormatoArchivo()
  {
    String formato = "";
    if (this.rdbtnxlsx.isSelected()) {
      formato = this.rdbtnxlsx.getText();
    } else {
      formato = this.rdbtnxls.getText();
    }
    return formato;
  }
  
  private boolean FormatoValido()
  {
    if ((!this.rdbtnxls.isSelected()) && 
      (!this.rdbtnxlsx.isSelected()))
    {
      JOptionPane.showMessageDialog(null, "Elija un formato v�lido para el archivo", "Error de formato", 0);
      return false;
    }
    return true;
  }
  
  private boolean Conexion()
  {
    boolean respuesta = false;
    String dirWeb = "www.google.com";
    int puerto = 80;
    try
    {
      Socket s = new Socket(dirWeb, puerto);
      if (s.isConnected())
      {
        respuesta = true;
        this.lblSenhal.setIcon(new ImageIcon(Solicitud.class.getResource("/Iconos/Conected.png")));
        this.tfEstadoConexion.setText("Conectado");
        
        this.tfEstadoConexion.setForeground(Color.BLACK);
        this.tfEstadoConexion.setBackground(Color.GREEN);
      }
      else
      {
        respuesta = false;
      }
    }
    catch (Exception localException) {}
    return respuesta;
  }
  
  private void Ayuda()
  {
    Object[] botones = { " No logro iniciar sesi�n\n", " El sistema dice que no tengo conexi�n a internet", "Amigo", "Nada" };
    int variable = JOptionPane.showOptionDialog(null, " �Cu�l es su problema joven?", "Ayuda", 
      1, 1, null, botones, botones[0]);
    if (variable == 0) {
      AbrirURL();
    }
  }
  
  private void AbrirURL()
  {
    try
    {
      String URL = "https://www.google.com/settings/security/lesssecureapps";
      
      Desktop.getDesktop().browse(new URI(URL));
      System.out.println("Url: " + URL);
    }
    catch (URISyntaxException ex)
    {
      System.out.println(ex);
    }
    catch (IOException e)
    {
      System.out.println(e);
    }
  }    
}
