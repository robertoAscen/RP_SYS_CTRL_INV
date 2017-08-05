/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Inicializacion;

import com.eneri.scstock.Apariencia.FondoPrincipal;
import com.eneri.scstock.Gestor.Gestionar;
import com.eneri.scstock.Seguridad.Encriptador;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author RAscencio
 */
public class ConfiguracionDB extends JDialog
{
    
  private JTextField tfHost;
  private JTextField tfPuerto;
  private JTextField tfUsuario;
  private JTextField tfBasedeDatos;
  private JPasswordField psfContrasenha;
  private JLabel lblPostgres;
  private JLabel lblBd;
  private JButton btnGuardarConfiguraciones;
  private Connection conexion;
  private ResultSet resultset;
  private PreparedStatement pstm;
  private String sql;
  static Gestionar gestion = new Gestionar();
  private JButton btnCrearBD;
  Encriptador cripto = new Encriptador();
  private JTabbedPane tabbedPane_1;
  
  public static void main(String[] args)
  {
    try
    {
      ConfiguracionDB dialog = new ConfiguracionDB();
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
  
  public ConfiguracionDB()
  {
    setTitle("Servidor");
    setIconImage(Toolkit.getDefaultToolkit().getImage(ConfiguracionDB.class.getResource("/Galeria/DBSQL.png")));
    setBounds(100, 100, 585, 389);
    
    FondoPrincipal contentPane = new FondoPrincipal();
    contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
    contentPane.setMinimumSize(new Dimension(500, 400));
    contentPane.setPreferredSize(new Dimension(1024, 768));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel lblConfiguracinDelServidor = new JLabel("Configuraci�n del servidor");
    lblConfiguracinDelServidor.setForeground(new Color(255, 255, 255));
    lblConfiguracinDelServidor.setFont(new Font("Tahoma", 0, 20));
    lblConfiguracinDelServidor.setBounds(154, 11, 260, 27);
    getContentPane().add(lblConfiguracinDelServidor);
    
    this.tfHost = new JTextField();
    this.tfHost.setFont(new Font("Segoe UI Semibold", 1, 15));
    this.tfHost.setBackground(Color.BLACK);
    this.tfHost.setForeground(Color.GREEN);
    this.tfHost.setBounds(246, 55, 168, 27);
    getContentPane().add(this.tfHost);
    this.tfHost.setColumns(10);
    
    this.tfPuerto = new JTextField();
    this.tfPuerto.setFont(new Font("Segoe UI Semibold", 1, 15));
    this.tfPuerto.setBackground(Color.BLACK);
    this.tfPuerto.setForeground(Color.GREEN);
    this.tfPuerto.setBounds(246, 103, 168, 27);
    getContentPane().add(this.tfPuerto);
    this.tfPuerto.setColumns(10);
    
    this.tfUsuario = new JTextField();
    this.tfUsuario.setFont(new Font("Segoe UI Semibold", 1, 15));
    this.tfUsuario.setBackground(Color.BLACK);
    this.tfUsuario.setForeground(Color.GREEN);
    this.tfUsuario.setBounds(246, 152, 168, 27);
    getContentPane().add(this.tfUsuario);
    this.tfUsuario.setColumns(10);
    
    this.tfBasedeDatos = new JTextField();
    this.tfBasedeDatos.setFont(new Font("Segoe UI Semibold", 1, 15));
    this.tfBasedeDatos.setBackground(Color.BLACK);
    this.tfBasedeDatos.setForeground(Color.GREEN);
    this.tfBasedeDatos.setBounds(246, 190, 313, 27);
    getContentPane().add(this.tfBasedeDatos);
    this.tfBasedeDatos.setColumns(10);
    
    JLabel lblNewLabel = new JLabel("Host:");
    lblNewLabel.setFont(new Font("Dialog", 0, 13));
    lblNewLabel.setForeground(Color.WHITE);
    lblNewLabel.setBounds(212, 58, 66, 24);
    getContentPane().add(lblNewLabel);
    
    JLabel lblPuerto = new JLabel("  Puerto:");
    lblPuerto.setFont(new Font("Dialog", 0, 13));
    lblPuerto.setForeground(Color.WHITE);
    lblPuerto.setBounds(194, 106, 84, 24);
    getContentPane().add(lblPuerto);
    
    JLabel lblUsuario = new JLabel("Usuario:");
    lblUsuario.setFont(new Font("Dialog", 0, 13));
    lblUsuario.setForeground(Color.WHITE);
    lblUsuario.setBounds(194, 155, 84, 24);
    getContentPane().add(lblUsuario);
    
    JLabel lblNombreDeLa = new JLabel(" Base de datos:");
    lblNombreDeLa.setFont(new Font("Dialog", 0, 13));
    lblNombreDeLa.setForeground(Color.WHITE);
    lblNombreDeLa.setBounds(154, 193, 117, 25);
    getContentPane().add(lblNombreDeLa);
    
    JLabel lblContrasea = new JLabel(" Contrase�a:");
    lblContrasea.setFont(new Font("Dialog", 0, 13));
    lblContrasea.setForeground(Color.WHITE);
    lblContrasea.setBounds(167, 228, 84, 25);
    getContentPane().add(lblContrasea);
    
    this.psfContrasenha = new JPasswordField();
    this.psfContrasenha.setFont(new Font("Segoe UI Semibold", 1, 15));
    this.psfContrasenha.setBackground(Color.BLACK);
    this.psfContrasenha.setForeground(Color.GREEN);
    this.psfContrasenha.setBounds(246, 228, 313, 25);
    getContentPane().add(this.psfContrasenha);
    
    this.lblPostgres = new JLabel("");
    this.lblPostgres.setIcon(new ImageIcon(ConfiguracionDB.class.getResource("/Galeria/postgresql.png")));
    this.lblPostgres.setBounds(413, 30, 146, 162);
    getContentPane().add(this.lblPostgres);
    
    this.btnGuardarConfiguraciones = new JButton("Guardar");
    this.btnGuardarConfiguraciones.setToolTipText("Guardar configuraciones");
    this.btnGuardarConfiguraciones.setIcon(new ImageIcon(ConfiguracionDB.class.getResource("/Iconos/Guardar.png")));
    this.btnGuardarConfiguraciones.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        ConfiguracionDB.this.SaveSettings();
        ConfiguracionDB.this.btnCrearBD.setEnabled(true);
      }
    });
    this.btnGuardarConfiguraciones.setBounds(260, 280, 146, 60);
    getContentPane().add(this.btnGuardarConfiguraciones);
    
    this.btnCrearBD = new JButton("Crear BD");
    this.btnCrearBD.setToolTipText("Crear la base de datos");
    this.btnCrearBD.setIcon(new ImageIcon(ConfiguracionDB.class.getResource("/Iconos/database.png")));
    this.btnCrearBD.setEnabled(false);
    this.btnCrearBD.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        ConfiguracionDB.this.CreateDataBase();
      }
    });
    this.btnCrearBD.setBounds(413, 280, 146, 60);
    getContentPane().add(this.btnCrearBD);
    
    this.lblBd = new JLabel("");
    this.lblBd.setIcon(new ImageIcon(ConfiguracionDB.class.getResource("/Galeria/DBSQL.png")));
    this.lblBd.setBounds(0, 153, 156, 187);
    getContentPane().add(this.lblBd);
    
    JTabbedPane tabbedPane = new JTabbedPane(1);
    tabbedPane.setBounds(10, 11, 549, 27);
    contentPane.add(tabbedPane);
    
    this.tabbedPane_1 = new JTabbedPane(1);
    this.tabbedPane_1.setBounds(10, 49, 146, 291);
    contentPane.add(this.tabbedPane_1);
    
    OpenSetting();
  }
  
  private void SaveSettings()
  {
    try
    {
      File hostDB = new File(new File("data/servidor/data_base_host.dll").getAbsolutePath());
      File puertoDB = new File(new File("data/servidor/data_base_puerto.dll").getAbsolutePath());
      File nombreDB = new File(new File("data/servidor/data_base_name.dll").getAbsolutePath());
      File passwordDB = new File(new File("data/servidor/data_base_password.dll").getAbsolutePath());
      File usuarioDB = new File(new File("data/servidor/data_base_usuario.dll").getAbsolutePath());
      
      String hostEncrypt = this.cripto.encriptar(this.tfHost.getText(), this.cripto.ClaveEncryption());
      String puertoEncrypt = this.cripto.encriptar(this.tfPuerto.getText(), this.cripto.ClaveEncryption());
      String nombreBDEncrypt = this.cripto.encriptar(this.tfBasedeDatos.getText().toLowerCase(), this.cripto.ClaveEncryption());
      String passEncrypt = this.cripto.encriptar(this.psfContrasenha.getText(), this.cripto.ClaveEncryption());
      String usuarioEncrypt = this.cripto.encriptar(this.tfUsuario.getText(), this.cripto.ClaveEncryption());
      
      gestion.GuardarTexto(hostDB, hostEncrypt);
      gestion.GuardarTexto(puertoDB, puertoEncrypt);
      gestion.GuardarTexto(nombreDB, nombreBDEncrypt);
      gestion.GuardarTexto(passwordDB, passEncrypt);
      gestion.GuardarTexto(usuarioDB, usuarioEncrypt);
      
      JOptionPane.showMessageDialog(null, "�La configuraci�n del servidor se ha guardado con �xito!");
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", 0);
    }
  }
  
  private void OpenSetting()
  {
    File hostDB = new File(new File("data/servidor/data_base_host.dll").getAbsolutePath());
    File puertoDB = new File(new File("data/servidor/data_base_puerto.dll").getAbsolutePath());
    File nombreDB = new File(new File("data/servidor/data_base_name.dll").getAbsolutePath());
    File passwordDB = new File(new File("data/servidor/data_base_password.dll").getAbsolutePath());
    File usuarioDB = new File(new File("data/servidor/data_base_usuario.dll").getAbsolutePath());
    
    String host = gestion.AbrirTexto(hostDB);
    String port = gestion.AbrirTexto(puertoDB);
    String dbname = gestion.AbrirTexto(nombreDB);
    String user = gestion.AbrirTexto(usuarioDB);
    String password = gestion.AbrirTexto(passwordDB);
    
    String hostDesencrypt = this.cripto.desencriptar(host, this.cripto.ClaveEncryption());
    String prtDesencrypt = this.cripto.desencriptar(port, this.cripto.ClaveEncryption());
    String BDDesencrypt = this.cripto.desencriptar(dbname, this.cripto.ClaveEncryption());
    String passDesencrypt = this.cripto.desencriptar(password, this.cripto.ClaveEncryption());
    String usuDesencrypt = this.cripto.desencriptar(user, this.cripto.ClaveEncryption());
    
    this.tfHost.setText(hostDesencrypt);
    this.tfPuerto.setText(prtDesencrypt);
    this.tfBasedeDatos.setText(BDDesencrypt);
    this.psfContrasenha.setText(passDesencrypt);
    this.tfUsuario.setText(usuDesencrypt);
  }
  
  private void CreateDataBase()
  {
    JOptionPane.showMessageDialog(null, "Lea atentamente: Esta funci�n utiliza la configuraci�n del servidor para crear la base de datos, primeramente \nverifica si existe una base de datos con el nombre que se ha configurado previamente, de ser asi, elimina la base \nde datos existente y crea una nueva, descartando la posiblidad de que la base de datos eliminada sea recuperada. \nNota: Contin�e solo si est� seguro de que no se perder�n datos importantes por accidente.");
    
    int resultado = JOptionPane.showConfirmDialog(null, "�Desea continuar con la creaci�n de una nueva base de datos?", "Crear base de datos", 0);
    if (resultado == 0)
    {
      File file = new File(new File("data/servidor/data_base_name.dll").getAbsolutePath());
      
      String nombreBD = gestion.AbrirTexto(file);
      
      String desencriptado = this.cripto.desencriptar(nombreBD, this.cripto.ClaveEncryption());
      
      this.conexion = DriverDB.obtenerConnection();
      try
      {
        this.sql = ("DROP DATABASE IF EXISTS " + desencriptado);
        this.pstm = null;
        this.pstm = this.conexion.prepareStatement(this.sql);
        this.pstm.executeUpdate();
        
        this.sql = ("CREATE DATABASE " + desencriptado + ";");
        
        this.pstm = null;
        this.pstm = this.conexion.prepareStatement(this.sql);
        this.pstm.executeUpdate();
        JOptionPane.showMessageDialog(null, "�Se ha creado la base de datos!");
        
        InicializarTablas();
      }
      catch (Exception e)
      {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
      }
      desconectar();
      dispose();
    }
    else {}
  }
  
  private void InicializarTablas()
  {
    if (JOptionPane.showConfirmDialog(new JFrame(), "�Desea inicializar las tablas y columnas de la base de datos creada?", "Inicializar", 
      0) == 0)
    {
      InicializacionTablas inicializacionDB = new InicializacionTablas();
      inicializacionDB.EliminarTuplas();
      inicializacionDB.CrearTuplas();
      inicializacionDB = null;
      System.runFinalization();
    }
    else
    {
      JOptionPane.showMessageDialog(null, "Se ha cancelado la inicializaci�n de las tablas.");
    }
  }
  
  public void desconectar()
  {
    try
    {
      if (this.resultset != null) {
        this.resultset.close();
      }
      if (this.pstm != null) {
        this.pstm.close();
      }
      if (this.conexion != null) {
        this.conexion.close();
      }
    }
    catch (Exception excepcion)
    {
      JOptionPane.showMessageDialog(null, "Exception: " + excepcion.getMessage());
      excepcion.printStackTrace();
    }
  }    
}
