/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Tesoreria;

import com.eneri.scstock.Apariencia.FondoFormularios;
import com.eneri.scstock.Herramientas.Validaciones;
import com.eneri.scstock.Modelos.ModeloRetiro;
import com.eneri.scstock.Objetos.DaoRetiros;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author RAscencio
 */
public class Retiros extends JDialog
{
    
  private JTextField tfMonto;
  private JTextField tfCodigo;
  private JTextField tfFecha;
  private JLabel lblCdigo;
  private JLabel lblDetalle;
  private JTextArea tfDetalle;
  private JScrollPane scrollPane;
  private JLabel lblFecha;
  private JButton btnRetirar;
  private JTabbedPane tabbedPane;
  private JTabbedPane tabbedPane_1;
  private JTabbedPane tabbedPane_2;
  private JTabbedPane tabbedPane_3;
  private JLabel lblMontoVal;
  private JLabel lblDetalleVal;
  private JButton btnVerRetiros;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          Retiros frame = new Retiros();
          frame.setDefaultCloseOperation(2);
          frame.setLocationRelativeTo(null);
          frame.setModal(true);
          frame.setVisible(true);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }
  
  public Retiros()
  {
    setIconImage(Toolkit.getDefaultToolkit().getImage(Retiros.class.getResource("/Iconos/Retiros.png")));
    setTitle("Retiros");
    setResizable(false);
    setBounds(100, 100, 645, 393);
    
    FondoFormularios contentPane = new FondoFormularios();
    contentPane.setForeground(Color.BLACK);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    this.tfMonto = new JTextField();
    Validaciones v = new Validaciones();
    v.BloqueoAlfabetico(this.tfMonto);
    this.tfMonto.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (Retiros.this.tfMonto.getText().trim().length() > 0) {
          Retiros.this.lblMontoVal.setVisible(false);
        }
      }
      
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          Retiros.this.tfDetalle.requestFocus();
        }
      }
    });
    this.tfMonto.setFont(new Font("Tahoma", 0, 20));
    this.tfMonto.setColumns(10);
    this.tfMonto.setBounds(68, 113, 344, 33);
    getContentPane().add(this.tfMonto);
    
    JLabel lblMonto = new JLabel("Monto:");
    lblMonto.setForeground(Color.WHITE);
    lblMonto.setBounds(21, 122, 58, 14);
    getContentPane().add(lblMonto);
    
    this.lblDetalle = new JLabel("Detalle:");
    this.lblDetalle.setForeground(Color.WHITE);
    this.lblDetalle.setBounds(21, 168, 46, 14);
    getContentPane().add(this.lblDetalle);
    
    this.scrollPane = new JScrollPane();
    this.scrollPane.setBounds(69, 168, 344, 75);
    contentPane.add(this.scrollPane);
    
    this.tfDetalle = new JTextArea();
    this.tfDetalle.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (Retiros.this.tfDetalle.getText().trim().length() > 0) {
          Retiros.this.lblDetalleVal.setVisible(false);
        }
      }
      
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          Retiros.this.btnRetirar.requestFocus();
        }
      }
    });
    this.scrollPane.setViewportView(this.tfDetalle);
    
    int codigo = DaoRetiros.IncrementarCodigo() + 1;
    this.tfCodigo = new JTextField();
    this.tfCodigo.setText(String.valueOf(codigo));
    this.tfCodigo.setEditable(false);
    this.tfCodigo.setColumns(10);
    this.tfCodigo.setBounds(68, 82, 86, 20);
    getContentPane().add(this.tfCodigo);
    
    this.lblCdigo = new JLabel("C�digo:");
    this.lblCdigo.setForeground(Color.WHITE);
    this.lblCdigo.setBounds(21, 85, 54, 14);
    getContentPane().add(this.lblCdigo);
    
    this.lblFecha = new JLabel("Fecha:");
    this.lblFecha.setForeground(Color.WHITE);
    this.lblFecha.setBounds(184, 85, 46, 14);
    getContentPane().add(this.lblFecha);
    
    this.tfFecha = new JTextField();
    this.tfFecha.setText(fecha());
    this.tfFecha.setEditable(false);
    this.tfFecha.setColumns(10);
    this.tfFecha.setBounds(230, 82, 104, 20);
    getContentPane().add(this.tfFecha);
    
    this.btnRetirar = new JButton("Retirar");
    this.btnRetirar.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if ((e.getKeyCode() == 10) && 
          (Retiros.this.Validar())) {
          Retiros.this.RetirarDinero();
        }
      }
    });
    this.btnRetirar.setMnemonic('R');
    this.btnRetirar.setIcon(new ImageIcon(Retiros.class.getResource("/Iconos/retirar.png")));
    this.btnRetirar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (Retiros.this.Validar()) {
          Retiros.this.RetirarDinero();
        }
      }
    });
    this.btnRetirar.setBounds(83, 288, 129, 55);
    contentPane.add(this.btnRetirar);
    
    JLabel label = new JLabel("");
    label.setIcon(new ImageIcon(Retiros.class.getResource("/Galeria/Retiros.png")));
    label.setBounds(440, 40, 200, 294);
    contentPane.add(label);
    
    JLabel lblRetirosDeDinero = new JLabel("Retiros de dinero");
    lblRetirosDeDinero.setFont(new Font("Tahoma", 0, 25));
    lblRetirosDeDinero.setForeground(Color.WHITE);
    lblRetirosDeDinero.setBounds(91, 11, 255, 38);
    contentPane.add(lblRetirosDeDinero);
    
    this.tabbedPane = new JTabbedPane(1);
    this.tabbedPane.setBounds(10, 11, 420, 38);
    contentPane.add(this.tabbedPane);
    
    this.tabbedPane_1 = new JTabbedPane(1);
    this.tabbedPane_1.setBounds(10, 60, 420, 217);
    contentPane.add(this.tabbedPane_1);
    
    this.tabbedPane_2 = new JTabbedPane(1);
    this.tabbedPane_2.setBounds(435, 11, 200, 343);
    contentPane.add(this.tabbedPane_2);
    
    this.btnVerRetiros = new JButton("Ver lista");
    this.btnVerRetiros.setIcon(new ImageIcon(Retiros.class.getResource("/Iconos/icono-N�mina.png")));
    this.btnVerRetiros.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        ListaRetiros.main(null);
      }
    });
    this.btnVerRetiros.setToolTipText("Ver lista de retiros realizados en el dia");
    this.btnVerRetiros.setMnemonic('R');
    this.btnVerRetiros.setBounds(229, 288, 157, 55);
    contentPane.add(this.btnVerRetiros);
    
    this.tabbedPane_3 = new JTabbedPane(1);
    this.tabbedPane_3.setBounds(10, 280, 420, 74);
    contentPane.add(this.tabbedPane_3);
    
    this.lblMontoVal = new JLabel("Ingrese el monto que desea retirar");
    this.lblMontoVal.setVisible(false);
    this.lblMontoVal.setForeground(Color.RED);
    this.lblMontoVal.setBounds(68, 147, 344, 14);
    contentPane.add(this.lblMontoVal);
    
    this.lblDetalleVal = new JLabel("Ingrese el concepto de retiro del dinero");
    this.lblDetalleVal.setVisible(false);
    this.lblDetalleVal.setForeground(Color.RED);
    this.lblDetalleVal.setBounds(68, 243, 344, 14);
    contentPane.add(this.lblDetalleVal);
  }
  
  private void RetirarDinero()
  {
    ModeloRetiro retirar = new ModeloRetiro();
    
    retirar.setCodigo(Integer.parseInt(this.tfCodigo.getText()));
    retirar.setDetalle(this.tfDetalle.getText().toUpperCase());
    retirar.setFecha(fecha());
    retirar.setHora(hora());
    retirar.setMonto(Double.parseDouble(this.tfMonto.getText()));
    
    DaoRetiros.RetiroDinero(retirar);
    
    String dinero = this.tfMonto.getText();
    String concepto = this.tfDetalle.getText().toUpperCase();
    dispose();
    JOptionPane.showMessageDialog(null, "Ha hecho un retiro de dinero por un monto de: " + dinero + " en\n concepto de " + concepto);
  }
  
  private boolean Validar()
  {
    if (this.tfMonto.getText().trim().length() < 1)
    {
      this.lblMontoVal.setVisible(true);
      this.tfMonto.requestFocus();
      return false;
    }
    if (this.tfDetalle.getText().trim().length() < 1)
    {
      this.lblDetalleVal.setVisible(true);
      this.tfDetalle.requestFocus();
      return false;
    }
    return true;
  }
  
  public static String fecha()
  {
    Date fecha = new Date();
    SimpleDateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");
    return fechaFormato.format(fecha);
  }
  
  public static String hora()
  {
    Calendar Cal = Calendar.getInstance();
    String hora = String.valueOf(Cal.get(11));
    String minuto = String.valueOf(Cal.get(12));
    String segundo = String.valueOf(Cal.get(13));
    
    String hora1 = "";
    String minuto1 = "";
    String segundo1 = "";
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
    horacompleta = hora1 + ":" + minuto1 + ":" + segundo1;
    
    return horacompleta;
  }    
}
