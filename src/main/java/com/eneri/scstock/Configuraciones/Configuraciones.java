/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Configuraciones;

import com.eneri.scstock.Apariencia.FondoFormularios;
import com.eneri.scstock.Gestor.Gestionar;
import com.eneri.scstock.Herramientas.Validaciones;
import com.eneri.scstock.Modelos.ModeloConfiguraciones;
import com.eneri.scstock.Objetos.DaoConfiguraciones;
import com.eneri.scstock.Seguridad.CifrarPassword;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
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
public class Configuraciones extends JDialog
{
    private JTextField tfNombreEmpresa;
  private JTextField tfTelefono;
  private JTextField tfDesdeContado;
  private JTextField tfHastaContado;
  private JTextField tfDesdeCredito;
  private JButton btnNewButton;
  private JTextField tfFax;
  private JTextField tfLocalidad;
  private JLabel lblLocalidad;
  private JTextField tfHastaCredito;
  private JLabel lblImagen;
  File archivo;
  byte[] bytesImg;
  Gestionar gestion = new Gestionar();
  private FondoFormularios contentPane;
  private JTextField tfPorcMayorista;
  private JTextField tfPorcVenta;
  private JTextField tfPorCredito;
  private JLabel lblMayorista;
  private JTextField tfIva1;
  private JTextField tfDivIva1;
  private JTextField tfIva2;
  private JTextField tfDivIva2;
  private JTextField tfMoneda;
  private JTextField tfSimbolo;
  private JButton btnCambiarLogo;
  private JTextField tfEslogan;
  private JTextField tfEmail;
  private JPasswordField tfContrasenha;
  private JLabel label_1;
  private JButton btnSalir;
  CifrarPassword cifra = new CifrarPassword();
  
  public static void main(String[] args)
  {
    try
    {
      Configuraciones dialog = new Configuraciones();
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
  
  public Configuraciones()
  {
    Validaciones validar = new Validaciones();
    setTitle("Configuraciones");
    setResizable(false);
    setBounds(100, 100, 852, 606);
    setIconImage(Toolkit.getDefaultToolkit().getImage(Configuraciones.class.getResource("/IconosMin/config.png")));
    
    this.contentPane = new FondoFormularios();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    this.contentPane.setLayout(null);
    
    this.btnNewButton = new JButton("Guardar");
    this.btnNewButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (Configuraciones.this.ValidarEmail())
        {
          if (Configuraciones.this.Consultar())
          {
            Configuraciones.this.Modificar();
            Configuraciones.this.GuardarLogo();
          }
          else
          {
            Configuraciones.this.Guardar();
            Configuraciones.this.GuardarLogo();
          }
          Configuraciones.this.dispose();
        }
      }
    });
    this.btnNewButton.setToolTipText("Guardar configuraciones y salir");
    this.btnNewButton.setIcon(new ImageIcon(Configuraciones.class.getResource("/Iconos/Guardar.png")));
    this.btnNewButton.setBounds(550, 508, 156, 59);
    getContentPane().add(this.btnNewButton);
    
    this.tfDesdeCredito = new JTextField();
    this.tfDesdeCredito.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          Configuraciones.this.tfHastaCredito.requestFocus();
          Configuraciones.this.tfHastaCredito.selectAll();
        }
      }
    });
    this.tfDesdeCredito.setBounds(345, 228, 174, 20);
    getContentPane().add(this.tfDesdeCredito);
    this.tfDesdeCredito.setColumns(10);
    
    JLabel label = new JLabel("Desde:");
    label.setForeground(Color.WHITE);
    label.setBounds(294, 228, 59, 20);
    getContentPane().add(label);
    
    JLabel lblHasta_1 = new JLabel(" Hasta:");
    lblHasta_1.setForeground(Color.WHITE);
    lblHasta_1.setBounds(294, 259, 59, 20);
    getContentPane().add(lblHasta_1);
    
    this.tfHastaCredito = new JTextField();
    this.tfHastaCredito.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          Configuraciones.this.tfPorcMayorista.requestFocus();
          Configuraciones.this.tfPorcMayorista.selectAll();
        }
      }
    });
    this.tfHastaCredito.setBounds(345, 259, 174, 20);
    getContentPane().add(this.tfHastaCredito);
    this.tfHastaCredito.setColumns(10);
    
    JLabel lblDesde = new JLabel("Desde:");
    lblDesde.setForeground(Color.WHITE);
    lblDesde.setBounds(38, 228, 50, 20);
    getContentPane().add(lblDesde);
    
    this.tfDesdeContado = new JTextField();
    this.tfDesdeContado.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          Configuraciones.this.tfHastaContado.requestFocus();
          Configuraciones.this.tfHastaContado.selectAll();
        }
      }
    });
    this.tfDesdeContado.setBounds(87, 228, 158, 20);
    getContentPane().add(this.tfDesdeContado);
    this.tfDesdeContado.setColumns(10);
    
    JLabel lblHasta = new JLabel(" Hasta:");
    lblHasta.setForeground(Color.WHITE);
    lblHasta.setBounds(38, 259, 50, 20);
    getContentPane().add(lblHasta);
    
    this.tfHastaContado = new JTextField();
    this.tfHastaContado.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          Configuraciones.this.tfDesdeCredito.requestFocus();
          Configuraciones.this.tfDesdeCredito.selectAll();
        }
      }
    });
    this.tfHastaContado.setBounds(87, 259, 158, 20);
    getContentPane().add(this.tfHastaContado);
    this.tfHastaContado.setColumns(10);
    
    JLabel lblNombreDeLa = new JLabel("Nombre de la empresa:");
    lblNombreDeLa.setForeground(Color.WHITE);
    lblNombreDeLa.setBounds(32, 51, 134, 20);
    getContentPane().add(lblNombreDeLa);
    
    this.tfNombreEmpresa = new JTextField();
    this.tfNombreEmpresa.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          Configuraciones.this.tfTelefono.requestFocus();
          Configuraciones.this.tfTelefono.selectAll();
        }
      }
    });
    this.tfNombreEmpresa.setBounds(171, 51, 313, 20);
    getContentPane().add(this.tfNombreEmpresa);
    this.tfNombreEmpresa.setColumns(10);
    
    JLabel lblNmeroDeTelfono = new JLabel("N� Tel�fono:");
    lblNmeroDeTelfono.setForeground(Color.WHITE);
    lblNmeroDeTelfono.setBounds(32, 89, 67, 20);
    getContentPane().add(lblNmeroDeTelfono);
    
    this.tfTelefono = new JTextField();
    this.tfTelefono.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          Configuraciones.this.tfFax.requestFocus();
          Configuraciones.this.tfFax.selectAll();
        }
      }
    });
    this.tfTelefono.setBounds(110, 89, 155, 20);
    getContentPane().add(this.tfTelefono);
    this.tfTelefono.setColumns(10);
    
    JLabel lblCiudad = new JLabel("Fax:");
    lblCiudad.setForeground(Color.WHITE);
    lblCiudad.setBounds(294, 89, 38, 20);
    getContentPane().add(lblCiudad);
    
    this.tfFax = new JTextField();
    this.tfFax.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          Configuraciones.this.tfLocalidad.requestFocus();
          Configuraciones.this.tfLocalidad.selectAll();
        }
      }
    });
    this.tfFax.setBounds(329, 89, 155, 20);
    getContentPane().add(this.tfFax);
    this.tfFax.setColumns(10);
    
    this.lblLocalidad = new JLabel("Localidad:");
    this.lblLocalidad.setForeground(Color.WHITE);
    this.lblLocalidad.setBounds(38, 121, 61, 20);
    getContentPane().add(this.lblLocalidad);
    
    this.tfLocalidad = new JTextField();
    this.tfLocalidad.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          Configuraciones.this.tfDesdeContado.requestFocus();
          Configuraciones.this.tfDesdeContado.selectAll();
        }
      }
    });
    this.tfLocalidad.setBounds(110, 121, 374, 20);
    getContentPane().add(this.tfLocalidad);
    this.tfLocalidad.setColumns(10);
    
    JLabel lblDatosDeLa = new JLabel("Datos de la empresa");
    lblDatosDeLa.setHorizontalAlignment(0);
    lblDatosDeLa.setForeground(Color.WHITE);
    lblDatosDeLa.setBounds(10, 11, 532, 20);
    this.contentPane.add(lblDatosDeLa);
    
    JTabbedPane tabbedPane_1 = new JTabbedPane(1);
    tabbedPane_1.setBounds(10, 11, 532, 20);
    this.contentPane.add(tabbedPane_1);
    
    JLabel lblRangoDeFactura_1 = new JLabel("Rango de factura contado");
    lblRangoDeFactura_1.setForeground(Color.WHITE);
    lblRangoDeFactura_1.setBounds(48, 197, 162, 20);
    this.contentPane.add(lblRangoDeFactura_1);
    
    JTabbedPane tabbedPane_2 = new JTabbedPane(1);
    tabbedPane_2.setBounds(10, 197, 255, 20);
    this.contentPane.add(tabbedPane_2);
    
    JLabel lblRangoDeFactura = new JLabel("Rango de factura cr�dito");
    lblRangoDeFactura.setForeground(Color.WHITE);
    lblRangoDeFactura.setBounds(335, 199, 174, 20);
    this.contentPane.add(lblRangoDeFactura);
    
    JTabbedPane tabbedPane_3 = new JTabbedPane(1);
    tabbedPane_3.setBounds(275, 197, 267, 20);
    this.contentPane.add(tabbedPane_3);
    
    JTabbedPane tabbedPane_4 = new JTabbedPane(1);
    tabbedPane_4.setBounds(10, 197, 255, 96);
    this.contentPane.add(tabbedPane_4);
    
    this.lblImagen = new JLabel("");
    this.lblImagen.setBounds(571, 39, 262, 250);
    this.contentPane.add(this.lblImagen);
    
    this.btnCambiarLogo = new JButton("Cambiar logo");
    this.btnCambiarLogo.setIcon(new ImageIcon(Configuraciones.class.getResource("/Iconos/imagen.PNG")));
    this.btnCambiarLogo.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Configuraciones.this.AbrirImagen();
      }
    });
    this.btnCambiarLogo.setBounds(550, 296, 292, 29);
    this.contentPane.add(this.btnCambiarLogo);
    
    JLabel lblLogotipo = new JLabel("Logotipo");
    lblLogotipo.setHorizontalAlignment(0);
    lblLogotipo.setForeground(Color.WHITE);
    lblLogotipo.setBounds(552, 11, 290, 29);
    this.contentPane.add(lblLogotipo);
    
    JTabbedPane tabbedPane_7 = new JTabbedPane(1);
    tabbedPane_7.setBounds(550, 11, 292, 29);
    this.contentPane.add(tabbedPane_7);
    
    JTabbedPane tabbedPane_5 = new JTabbedPane(1);
    tabbedPane_5.setBounds(550, 11, 292, 281);
    this.contentPane.add(tabbedPane_5);
    
    this.tfPorcMayorista = new JTextField();
    this.tfPorcMayorista.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          Configuraciones.this.tfPorcVenta.requestFocus();
          Configuraciones.this.tfPorcVenta.selectAll();
        }
      }
    });
    validar.ValidarFormatoPrecio(this.tfPorcMayorista);
    this.tfPorcMayorista.setColumns(10);
    this.tfPorcMayorista.setBounds(704, 381, 50, 20);
    this.contentPane.add(this.tfPorcMayorista);
    
    this.tfPorcVenta = new JTextField();
    this.tfPorcVenta.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          Configuraciones.this.tfPorCredito.requestFocus();
          Configuraciones.this.tfPorCredito.selectAll();
        }
      }
    });
    validar.ValidarFormatoPrecio(this.tfPorcVenta);
    this.tfPorcVenta.setColumns(10);
    this.tfPorcVenta.setBounds(704, 424, 50, 20);
    this.contentPane.add(this.tfPorcVenta);
    
    this.tfPorCredito = new JTextField();
    this.tfPorCredito.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          Configuraciones.this.tfIva1.requestFocus();
          Configuraciones.this.tfIva1.selectAll();
        }
      }
    });
    validar.ValidarFormatoPrecio(this.tfPorCredito);
    this.tfPorCredito.setColumns(10);
    this.tfPorCredito.setBounds(704, 466, 50, 20);
    this.contentPane.add(this.tfPorCredito);
    
    this.lblMayorista = new JLabel("Utilidad p/ mayoristas:");
    this.lblMayorista.setForeground(Color.WHITE);
    this.lblMayorista.setBounds(571, 381, 156, 20);
    this.contentPane.add(this.lblMayorista);
    
    JLabel lblVenta = new JLabel("Utilidad p/ venta:");
    lblVenta.setForeground(Color.WHITE);
    lblVenta.setBounds(599, 424, 109, 20);
    this.contentPane.add(lblVenta);
    
    JLabel lblCrdito = new JLabel("Utilidad p/ cr�dito:");
    lblCrdito.setForeground(Color.WHITE);
    lblCrdito.setBounds(599, 465, 117, 20);
    this.contentPane.add(lblCrdito);
    
    JLabel label_2 = new JLabel("%");
    label_2.setFont(new Font("Tahoma", 0, 15));
    label_2.setForeground(Color.WHITE);
    label_2.setBounds(764, 381, 52, 20);
    this.contentPane.add(label_2);
    
    JLabel label_3 = new JLabel("%");
    label_3.setFont(new Font("Tahoma", 0, 15));
    label_3.setForeground(Color.WHITE);
    label_3.setBounds(764, 424, 52, 20);
    this.contentPane.add(label_3);
    
    JLabel label_4 = new JLabel("%");
    label_4.setFont(new Font("Tahoma", 0, 15));
    label_4.setForeground(Color.WHITE);
    label_4.setBounds(764, 466, 52, 20);
    this.contentPane.add(label_4);
    
    JTabbedPane tabbedPane_6 = new JTabbedPane(1);
    tabbedPane_6.setBounds(550, 355, 292, 142);
    this.contentPane.add(tabbedPane_6);
    
    this.tfIva1 = new JTextField();
    this.tfIva1.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          Configuraciones.this.tfDivIva1.requestFocus();
          Configuraciones.this.tfDivIva1.selectAll();
        }
      }
    });
    validar.ValidarFormatoPrecio(this.tfIva1);
    this.tfIva1.setText("0.0");
    this.tfIva1.setColumns(10);
    this.tfIva1.setBounds(79, 329, 50, 20);
    this.contentPane.add(this.tfIva1);
    
    JLabel lblIva = new JLabel("% IVA:");
    lblIva.setForeground(Color.WHITE);
    lblIva.setBounds(32, 329, 46, 20);
    this.contentPane.add(lblIva);
    
    this.tfDivIva1 = new JTextField();
    this.tfDivIva1.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          Configuraciones.this.tfIva2.requestFocus();
          Configuraciones.this.tfIva2.selectAll();
        }
      }
    });
    validar.ValidarFormatoPrecio(this.tfDivIva1);
    this.tfDivIva1.setText("0.0");
    this.tfDivIva1.setColumns(10);
    this.tfDivIva1.setBounds(190, 329, 54, 20);
    this.contentPane.add(this.tfDivIva1);
    
    this.tfIva2 = new JTextField();
    this.tfIva2.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          Configuraciones.this.tfDivIva2.requestFocus();
          Configuraciones.this.tfDivIva2.selectAll();
        }
      }
    });
    validar.ValidarFormatoPrecio(this.tfIva2);
    this.tfIva2.setText("0.0");
    this.tfIva2.setColumns(10);
    this.tfIva2.setBounds(79, 375, 50, 20);
    this.contentPane.add(this.tfIva2);
    
    JLabel label_5 = new JLabel("% IVA:");
    label_5.setForeground(Color.WHITE);
    label_5.setBounds(38, 375, 46, 20);
    this.contentPane.add(label_5);
    
    this.tfDivIva2 = new JTextField();
    this.tfDivIva2.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          Configuraciones.this.tfMoneda.requestFocus();
          Configuraciones.this.tfMoneda.selectAll();
        }
      }
    });
    validar.ValidarFormatoPrecio(this.tfDivIva2);
    this.tfDivIva2.setText("0.0");
    this.tfDivIva2.setColumns(10);
    this.tfDivIva2.setBounds(190, 375, 54, 20);
    this.contentPane.add(this.tfDivIva2);
    
    this.tfMoneda = new JTextField();
    this.tfMoneda.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10)
        {
          Configuraciones.this.tfSimbolo.requestFocus();
          Configuraciones.this.tfSimbolo.selectAll();
        }
      }
    });
    validar.BloqueoNumerico(this.tfMoneda);
    this.tfMoneda.setColumns(10);
    this.tfMoneda.setBounds(307, 375, 149, 20);
    this.contentPane.add(this.tfMoneda);
    
    JLabel lblMoneda = new JLabel("Moneda:");
    lblMoneda.setForeground(Color.WHITE);
    lblMoneda.setBounds(254, 375, 52, 20);
    this.contentPane.add(lblMoneda);
    
    this.tfSimbolo = new JTextField();
    this.tfSimbolo.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          Configuraciones.this.btnCambiarLogo.requestFocus();
        }
      }
    });
    this.tfSimbolo.setColumns(10);
    this.tfSimbolo.setBounds(307, 329, 52, 20);
    this.contentPane.add(this.tfSimbolo);
    
    JLabel lblSmbolo = new JLabel("S�mbolo:");
    lblSmbolo.setForeground(Color.WHITE);
    lblSmbolo.setBounds(250, 329, 58, 20);
    this.contentPane.add(lblSmbolo);
    
    JLabel lblDivisor = new JLabel("Divisor:");
    lblDivisor.setForeground(Color.WHITE);
    lblDivisor.setBounds(139, 329, 57, 20);
    this.contentPane.add(lblDivisor);
    
    JLabel lblDivisor_1 = new JLabel("Divisor:");
    lblDivisor_1.setForeground(Color.WHITE);
    lblDivisor_1.setBounds(139, 375, 57, 20);
    this.contentPane.add(lblDivisor_1);
    
    JTabbedPane tabbedPane_8 = new JTabbedPane(1);
    tabbedPane_8.setBounds(10, 317, 532, 87);
    this.contentPane.add(tabbedPane_8);
    
    JTabbedPane tabbedPane_9 = new JTabbedPane(1);
    tabbedPane_9.setBounds(275, 197, 267, 96);
    this.contentPane.add(tabbedPane_9);
    
    this.tfEslogan = new JTextField();
    this.tfEslogan.setColumns(10);
    this.tfEslogan.setBounds(110, 152, 374, 20);
    this.contentPane.add(this.tfEslogan);
    
    JLabel lblEslogan = new JLabel("Eslogan:");
    lblEslogan.setForeground(Color.WHITE);
    lblEslogan.setBounds(48, 152, 66, 20);
    this.contentPane.add(lblEslogan);
    
    JTabbedPane tabbedPane = new JTabbedPane(1);
    tabbedPane.setBounds(10, 31, 532, 155);
    this.contentPane.add(tabbedPane);
    
    JLabel lblTransacciones = new JLabel("Transacciones");
    lblTransacciones.setHorizontalAlignment(0);
    lblTransacciones.setForeground(Color.WHITE);
    lblTransacciones.setBounds(13, 299, 520, 20);
    this.contentPane.add(lblTransacciones);
    
    JTabbedPane tabbedPane_10 = new JTabbedPane(1);
    tabbedPane_10.setBounds(10, 299, 532, 20);
    this.contentPane.add(tabbedPane_10);
    
    JLabel lblUtilidad = new JLabel("% Utilidades");
    lblUtilidad.setHorizontalAlignment(0);
    lblUtilidad.setForeground(Color.WHITE);
    lblUtilidad.setBounds(545, 336, 297, 20);
    this.contentPane.add(lblUtilidad);
    
    JTabbedPane tabbedPane_11 = new JTabbedPane(1);
    tabbedPane_11.setBounds(550, 329, 292, 29);
    this.contentPane.add(tabbedPane_11);
    
    this.tfEmail = new JTextField();
    this.tfEmail.setBounds(251, 466, 267, 20);
    this.contentPane.add(this.tfEmail);
    this.tfEmail.setColumns(10);
    
    JLabel lblEmail = new JLabel("E-mail:");
    lblEmail.setForeground(Color.WHITE);
    lblEmail.setBounds(202, 466, 49, 20);
    this.contentPane.add(lblEmail);
    
    JLabel lblContrasea = new JLabel("Contrase�a:");
    lblContrasea.setForeground(Color.WHITE);
    lblContrasea.setBounds(177, 521, 77, 20);
    this.contentPane.add(lblContrasea);
    
    this.tfContrasenha = new JPasswordField();
    this.tfContrasenha.setColumns(10);
    this.tfContrasenha.setBounds(253, 521, 174, 20);
    this.contentPane.add(this.tfContrasenha);
    
    JLabel lblEmail_1 = new JLabel("E-mail");
    lblEmail_1.setHorizontalAlignment(0);
    lblEmail_1.setForeground(Color.WHITE);
    lblEmail_1.setBounds(10, 414, 530, 20);
    this.contentPane.add(lblEmail_1);
    
    JTabbedPane tabbedPane_12 = new JTabbedPane(1);
    tabbedPane_12.setBounds(10, 407, 532, 29);
    this.contentPane.add(tabbedPane_12);
    
    JTabbedPane tabbedPane_13 = new JTabbedPane(1);
    tabbedPane_13.setBounds(10, 435, 532, 132);
    this.contentPane.add(tabbedPane_13);
    
    this.btnSalir = new JButton("Salir");
    this.btnSalir.setIcon(new ImageIcon(Configuraciones.class.getResource("/Iconos/Cerrar.png")));
    this.btnSalir.setToolTipText("Salir sin guardar los cambios");
    this.btnSalir.setBounds(716, 508, 126, 59);
    this.contentPane.add(this.btnSalir);
    
    this.label_1 = new JLabel("");
    this.label_1.setIcon(new ImageIcon(Configuraciones.class.getResource("/Galeria/e-mail.png")));
    this.label_1.setBounds(63, 466, 117, 72);
    this.contentPane.add(this.label_1);
    
    CargarConfiguraciones();
    AbrirLogoActual();
  }
  
  private boolean Consultar()
  {
    ModeloConfiguraciones conf = DaoConfiguraciones.ConsultarConfiguraciones(1);
    if (conf == null)
    {
      System.out.println("no existe");
      return false;
    }
    return true;
  }
  
  private void CargarConfiguraciones()
  {
    try
    {
      ModeloConfiguraciones conf = DaoConfiguraciones.ConsultarConfiguraciones(1);
      
      this.tfNombreEmpresa.setText(String.valueOf(conf.getNombreEmpresa()));
      this.tfTelefono.setText(String.valueOf(conf.getTelefono()));
      this.tfFax.setText(String.valueOf(conf.getFax()));
      this.tfLocalidad.setText(String.valueOf(conf.getLocalidad()));
      
      this.tfDesdeContado.setText(String.valueOf(conf.getDesdeContado()));
      this.tfHastaContado.setText(String.valueOf(conf.getHastaContado()));
      
      this.tfDesdeCredito.setText(String.valueOf(conf.getDesdeCredito()));
      this.tfHastaCredito.setText(String.valueOf(conf.getHastaCredito()));
      
      this.tfPorcMayorista.setText(String.valueOf(conf.getUtilmayorista()));
      this.tfPorcVenta.setText(String.valueOf(conf.getUtilventa()));
      this.tfPorCredito.setText(String.valueOf(conf.getUtilcredito()));
      
      this.tfIva1.setText(String.valueOf(conf.getIva1()));
      this.tfIva2.setText(String.valueOf(conf.getIva2()));
      this.tfDivIva1.setText(String.valueOf(conf.getDivIva1()));
      this.tfDivIva2.setText(String.valueOf(conf.getDivIva2()));
      this.tfMoneda.setText(String.valueOf(conf.getMoneda()));
      this.tfSimbolo.setText(String.valueOf(conf.getSimbolo()));
      
      this.tfEmail.setText(String.valueOf(conf.getEmail()));
      this.tfContrasenha.setText(String.valueOf(conf.getContrasenha()));
      this.tfEslogan.setText(String.valueOf(conf.getEslogan()));
    }
    catch (Exception localException) {}
  }
  
  private boolean ValidarEmail()
  {
    if (((this.tfEmail.getText().trim().length() > 0) || (this.tfContrasenha.getText().trim().length() > 0)) && (
      (!this.tfEmail.getText().contains("@")) || (!this.tfEmail.getText().contains("."))))
    {
      JOptionPane.showMessageDialog(null, "Error del formato: usuario@dominio.com", "Error de formato", 0);
      this.tfEmail.requestFocus();
      return false;
    }
    return true;
  }
  
  private void Guardar()
  {
    ModeloConfiguraciones guardar = new ModeloConfiguraciones();
    
    guardar.setCodigo(1);
    guardar.setNombreEmpresa(this.tfNombreEmpresa.getText().toUpperCase());
    guardar.setTelefono(this.tfTelefono.getText());
    guardar.setFax(this.tfFax.getText());
    guardar.setLocalidad(this.tfLocalidad.getText().toUpperCase());
    guardar.setDesdeContado(Integer.parseInt(this.tfDesdeContado.getText()));
    guardar.setHastaContado(Integer.parseInt(this.tfHastaContado.getText()));
    guardar.setDesdeCredito(Integer.parseInt(this.tfDesdeCredito.getText()));
    guardar.setHastaCredito(Integer.parseInt(this.tfHastaCredito.getText()));
    guardar.setUtilmayorista(Double.parseDouble(this.tfPorcMayorista.getText()));
    guardar.setUtilventa(Double.parseDouble(this.tfPorcVenta.getText()));
    guardar.setUtilcredito(Double.parseDouble(this.tfPorCredito.getText()));
    guardar.setIva1(Double.parseDouble(this.tfIva1.getText()));
    guardar.setIva2(Double.parseDouble(this.tfIva2.getText()));
    guardar.setDivIva1(Double.parseDouble(this.tfDivIva1.getText()));
    guardar.setDivIva2(Double.parseDouble(this.tfDivIva2.getText()));
    guardar.setMoneda(this.tfMoneda.getText().toUpperCase());
    guardar.setSimbolo(this.tfSimbolo.getText().toUpperCase());
    guardar.setEmail(this.tfEmail.getText().toLowerCase());
    guardar.setContrasenha(this.cifra.Encriptar(this.tfContrasenha.getText()));
    guardar.setEslogan(this.tfEslogan.getText().toUpperCase());
    
    DaoConfiguraciones.GuardarConfiguraciones(guardar);
  }
  
  private void Modificar()
  {
    ModeloConfiguraciones guardar = new ModeloConfiguraciones();
    
    guardar.setCodigo(1);
    guardar.setNombreEmpresa(this.tfNombreEmpresa.getText().toUpperCase());
    guardar.setTelefono(this.tfTelefono.getText());
    guardar.setFax(this.tfFax.getText());
    guardar.setLocalidad(this.tfLocalidad.getText().toUpperCase());
    guardar.setDesdeContado(Integer.parseInt(this.tfDesdeContado.getText()));
    guardar.setHastaContado(Integer.parseInt(this.tfHastaContado.getText()));
    guardar.setDesdeCredito(Integer.parseInt(this.tfDesdeCredito.getText()));
    guardar.setHastaCredito(Integer.parseInt(this.tfHastaCredito.getText()));
    guardar.setUtilmayorista(Double.parseDouble(this.tfPorcMayorista.getText()));
    guardar.setUtilventa(Double.parseDouble(this.tfPorcVenta.getText()));
    guardar.setUtilcredito(Double.parseDouble(this.tfPorCredito.getText()));
    guardar.setIva1(Double.parseDouble(this.tfIva1.getText()));
    guardar.setIva2(Double.parseDouble(this.tfIva2.getText()));
    guardar.setDivIva1(Double.parseDouble(this.tfDivIva1.getText()));
    guardar.setDivIva2(Double.parseDouble(this.tfDivIva2.getText()));
    guardar.setMoneda(this.tfMoneda.getText().toUpperCase());
    guardar.setSimbolo(this.tfSimbolo.getText().toUpperCase());
    
    guardar.setEmail(this.tfEmail.getText().toLowerCase());
    guardar.setContrasenha(this.cifra.Encriptar(this.tfContrasenha.getText()));
    guardar.setEslogan(this.tfEslogan.getText().toUpperCase());
    
    DaoConfiguraciones.EditarConfiguraciones(guardar);
  }
  
  private void AbrirImagen()
  {
    JOptionPane.showMessageDialog(null, "Las im�genes con resoluci�n superior a 150x150 pueden \nperder nitidez ya que se reducir� al tama�o necesario.\nEs recomendable que la imagen sea de formato (.png)", "Recomendaci�n", 1);
    FileDialog seleccionado = new FileDialog(this, "Abrir imagen", 0);
    seleccionado.show();
    String curFile = seleccionado.getFile();
    String filename = seleccionado.getDirectory() + curFile;
    setCursor(Cursor.getPredefinedCursor(12));
    File f = new File(filename);
    this.archivo = f;
    if (this.archivo.canRead())
    {
      String nombreArchivo = this.archivo.getName();
      if (nombreArchivo.endsWith("png"))
      {
        this.bytesImg = this.gestion.AbrirImagen(this.archivo);
        this.lblImagen.setIcon(new ImageIcon(this.bytesImg));
      }
      else
      {
        JOptionPane.showMessageDialog(null, "El formato de imagen debe ser (.png)", "Error al abrir", 0);
      }
    }
  }
  
  private void AbrirLogoActual()
  {
    File f = new File(new File("data/graficos/logo.png").getAbsolutePath());
    this.bytesImg = this.gestion.AbrirImagen(f);
    this.lblImagen.setIcon(new ImageIcon(this.bytesImg));
  }
  
  private void GuardarLogo()
  {
    File f = new File(new File("data/graficos/logo.png").getAbsolutePath());
    this.gestion.GuardarImagen(f, this.bytesImg);
  }    
}
