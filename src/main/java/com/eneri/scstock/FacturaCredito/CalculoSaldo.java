/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.FacturaCredito;

import com.eneri.scstock.Apariencia.FondoTransacciones;
import com.eneri.scstock.Modelos.ModeloFacturaCredito;
import com.eneri.scstock.Objetos.DaoCobranzas;
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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author RAscencio
 */
public class CalculoSaldo extends JDialog
{
    private JTextField tfSaldo;
  public static JTextField tfDeudaActual;
  private JButton btnCobrar;
  private JLabel lblCambio;
  private JLabel label_1;
  public static JTextField tfEntrega;
  private JLabel lblDinero;
  private JLabel lblMontoACobrar;
  private JTextField tfColor;
  public static JLabel lblCodigoFactura;
  private JLabel lblFacturaN;
  private JTabbedPane tabbedPane;
  private JTabbedPane tabbedPane_1;
  private FormularioCobranzas actualizar;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          CalculoSaldo dialog = new CalculoSaldo();
          dialog.setDefaultCloseOperation(2);
          dialog.setVisible(true);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }
  
  public CalculoSaldo()
  {
    setResizable(false);
    setIconImage(Toolkit.getDefaultToolkit().getImage(CalculoSaldo.class.getResource("/Iconos/Cobrar.png")));
    setTitle("Cobranza");
    setBounds(100, 100, 393, 411);
    getContentPane().setLayout(null);
    
    FondoTransacciones contentPane = new FondoTransacciones();
    contentPane.setForeground(Color.BLACK);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    lblCodigoFactura = new JLabel("New label");
    lblCodigoFactura.setFont(new Font("Tahoma", 1, 15));
    lblCodigoFactura.setForeground(Color.BLACK);
    lblCodigoFactura.setBounds(133, 7, 205, 31);
    contentPane.add(lblCodigoFactura);
    
    this.tfSaldo = new JTextField();
    this.tfSaldo.setForeground(new Color(0, 0, 0));
    this.tfSaldo.setEditable(false);
    this.tfSaldo.setFont(new Font("Tahoma", 1, 25));
    this.tfSaldo.setColumns(10);
    this.tfSaldo.setBounds(130, 112, 229, 47);
    getContentPane().add(this.tfSaldo);
    
    this.lblCambio = new JLabel("Saldo:");
    this.lblCambio.setFont(new Font("Tahoma", 1, 15));
    this.lblCambio.setBounds(71, 112, 61, 47);
    getContentPane().add(this.lblCambio);
    
    tfDeudaActual = new JTextField();
    tfDeudaActual.setForeground(Color.GRAY);
    tfDeudaActual.setEditable(false);
    tfDeudaActual.setFont(new Font("Tahoma", 1, 15));
    tfDeudaActual.setColumns(10);
    tfDeudaActual.setBounds(130, 191, 229, 29);
    getContentPane().add(tfDeudaActual);
    
    this.lblMontoACobrar = new JLabel("Deuda actual:");
    this.lblMontoACobrar.setForeground(new Color(0, 0, 0));
    this.lblMontoACobrar.setFont(new Font("Tahoma", 1, 15));
    this.lblMontoACobrar.setBounds(21, 191, 104, 29);
    getContentPane().add(this.lblMontoACobrar);
    
    this.lblDinero = new JLabel("Entrega:");
    this.lblDinero.setForeground(new Color(0, 0, 0));
    this.lblDinero.setFont(new Font("Tahoma", 1, 15));
    this.lblDinero.setBounds(61, 62, 76, 29);
    getContentPane().add(this.lblDinero);
    
    this.btnCobrar = new JButton("COBRAR");
    this.btnCobrar.setEnabled(false);
    this.btnCobrar.setToolTipText("Continuar con la cobranza");
    this.btnCobrar.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10)
        {
          CalculoSaldo.this.ActualizarCobranza();
          
          CalculoSaldo.this.dispose();
        }
      }
    });
    this.btnCobrar.setIcon(new ImageIcon(CalculoSaldo.class.getResource("/Iconos/Cobrar.png")));
    this.btnCobrar.setFont(new Font("Tahoma", 1, 15));
    this.btnCobrar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        CalculoSaldo.this.ActualizarCobranza();
        CalculoSaldo.this.dispose();
      }
    });
    this.btnCobrar.setBounds(206, 281, 159, 76);
    getContentPane().add(this.btnCobrar);
    
    this.label_1 = new JLabel("");
    this.label_1.setIcon(new ImageIcon(CalculoSaldo.class.getResource("/Galeria/Notepad.png")));
    this.label_1.setFont(new Font("Tahoma", 1, 15));
    this.label_1.setBounds(10, 246, 159, 126);
    getContentPane().add(this.label_1);
    
    tfEntrega = new JTextField();
    tfEntrega.setForeground(Color.DARK_GRAY);
    tfEntrega.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10)
        {
          double entrega = Double.parseDouble(CalculoSaldo.tfEntrega.getText());
          double deudactual = Double.parseDouble(CalculoSaldo.tfDeudaActual.getText());
          double saldo = 0.0D;
          
          saldo = deudactual - entrega;
          if (saldo > 0.0D)
          {
            CalculoSaldo.this.tfSaldo.setText(String.valueOf(saldo));
            CalculoSaldo.this.tfSaldo.setEditable(false);
            CalculoSaldo.tfEntrega.setEditable(false);
          }
          if (saldo < 0.0D)
          {
            JOptionPane.showMessageDialog(null, "El monto que desea cobrar supera el saldo.", "Advertencia", 2);
            return;
          }
          if (saldo == 0.0D)
          {
            CalculoSaldo.this.tfSaldo.setText("0");
            CalculoSaldo.tfEntrega.setEditable(false);
          }
          CalculoSaldo.this.btnCobrar.setEnabled(true);
          CalculoSaldo.this.btnCobrar.requestFocus();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (CalculoSaldo.tfEntrega.getText().trim().length() > 0) {
          CalculoSaldo.tfDeudaActual.setForeground(new Color(218, 165, 32));
        }
      }
    });
    tfEntrega.setFont(new Font("Tahoma", 1, 15));
    tfEntrega.setColumns(10);
    tfEntrega.setBounds(130, 62, 232, 29);
    getContentPane().add(tfEntrega);
    
    this.tfColor = new JTextField();
    this.tfColor.setVisible(false);
    this.tfColor.setBounds(279, 8, 76, 20);
    contentPane.add(this.tfColor);
    this.tfColor.setColumns(10);
    
    this.lblFacturaN = new JLabel("Factura N�:");
    this.lblFacturaN.setFont(new Font("Tahoma", 1, 15));
    this.lblFacturaN.setForeground(Color.BLACK);
    this.lblFacturaN.setBounds(39, 7, 130, 31);
    contentPane.add(this.lblFacturaN);
    
    this.tabbedPane = new JTabbedPane(1);
    this.tabbedPane.setBounds(10, 7, 367, 31);
    contentPane.add(this.tabbedPane);
    
    this.tabbedPane_1 = new JTabbedPane(1);
    this.tabbedPane_1.setBounds(10, 178, 364, 54);
    contentPane.add(this.tabbedPane_1);
    
    ColorBlancoFacturaLabels();
  }
  
  private void ColorBlancoFacturaLabels()
  {
    this.lblDinero.setForeground(Color.WHITE);
    this.lblMontoACobrar.setForeground(Color.WHITE);
    this.lblCambio.setForeground(Color.WHITE);
    this.lblFacturaN.setForeground(Color.WHITE);
    lblCodigoFactura.setForeground(Color.WHITE);
  }
  
  private void ColorGrisFacturaLabels()
  {
    this.lblDinero.setForeground(Color.GRAY);
    this.lblMontoACobrar.setForeground(Color.GRAY);
    this.lblCambio.setForeground(Color.GRAY);
    this.lblFacturaN.setForeground(Color.GRAY);
    lblCodigoFactura.setForeground(Color.GRAY);
  }
  
  private void ColorAmarilloFacturaLabels()
  {
    this.lblDinero.setForeground(Color.YELLOW);
    this.lblMontoACobrar.setForeground(Color.YELLOW);
    this.lblCambio.setForeground(Color.YELLOW);
    this.lblFacturaN.setForeground(Color.YELLOW);
    lblCodigoFactura.setForeground(Color.YELLOW);
  }
  
  private void ColorVerdeFacturaLabels()
  {
    this.lblDinero.setForeground(new Color(0, 128, 0));
    this.lblMontoACobrar.setForeground(new Color(0, 128, 0));
    this.lblCambio.setForeground(new Color(0, 128, 0));
    this.lblFacturaN.setForeground(new Color(0, 128, 0));
    lblCodigoFactura.setForeground(new Color(0, 128, 0));
  }
  
  private void ColorAzulFacturaLabels()
  {
    this.lblDinero.setForeground(new Color(0, 0, 205));
    this.lblMontoACobrar.setForeground(new Color(0, 0, 205));
    this.lblCambio.setForeground(new Color(0, 0, 205));
    this.lblFacturaN.setForeground(new Color(0, 0, 205));
    lblCodigoFactura.setForeground(new Color(0, 0, 205));
  }
  
  private void ColorMarronFacturaLabels()
  {
    this.lblDinero.setForeground(new Color(139, 69, 19));
    this.lblMontoACobrar.setForeground(new Color(139, 69, 19));
    this.lblCambio.setForeground(new Color(139, 69, 19));
    this.lblFacturaN.setForeground(new Color(139, 69, 19));
    lblCodigoFactura.setForeground(new Color(139, 69, 19));
  }
  
  private void ColorPurpuraFacturaLabels()
  {
    this.lblDinero.setForeground(new Color(148, 0, 211));
    this.lblMontoACobrar.setForeground(new Color(148, 0, 211));
    this.lblCambio.setForeground(new Color(148, 0, 211));
    this.lblFacturaN.setForeground(new Color(148, 0, 211));
    lblCodigoFactura.setForeground(new Color(148, 0, 211));
  }
  
  private void ColorCyanFacturaLabels()
  {
    this.lblDinero.setForeground(Color.CYAN);
    this.lblMontoACobrar.setForeground(Color.CYAN);
    this.lblCambio.setForeground(Color.CYAN);
    this.lblFacturaN.setForeground(Color.CYAN);
    lblCodigoFactura.setForeground(Color.CYAN);
  }
  
  private void ColorNaranjaFacturaLabels()
  {
    this.lblDinero.setForeground(Color.ORANGE);
    this.lblMontoACobrar.setForeground(Color.ORANGE);
    this.lblCambio.setForeground(Color.ORANGE);
    this.lblFacturaN.setForeground(Color.ORANGE);
    lblCodigoFactura.setForeground(Color.ORANGE);
  }
  
  private void ColorVerdeFluorFacturaLabels()
  {
    this.lblDinero.setForeground(Color.GREEN);
    this.lblMontoACobrar.setForeground(Color.GREEN);
    this.lblCambio.setForeground(Color.GREEN);
    this.lblFacturaN.setForeground(Color.GREEN);
    lblCodigoFactura.setForeground(Color.GREEN);
  }
  
  private void ColorNegroFacturaLabels()
  {
    this.lblDinero.setForeground(Color.BLACK);
    this.lblCambio.setForeground(Color.BLACK);
    this.lblMontoACobrar.setForeground(Color.BLACK);
    this.lblFacturaN.setForeground(Color.BLACK);
    lblCodigoFactura.setForeground(Color.BLACK);
  }
  
  private void ActualizarCobranza()
  {
    String codigo = lblCodigoFactura.getText();
    ModeloFacturaCredito cobranz = DaoCobranzas.ConsutarMasDetalles(Integer.parseInt(codigo));
    String fecha = cobranz.getFechaUEntrega();
    if (fecha().equals(fecha))
    {
      double ultimaentrega = cobranz.getEntrega();
      double entregaAct = Double.parseDouble(tfEntrega.getText());
      double totalEntrega = ultimaentrega + entregaAct;
      ModeloFacturaCredito cobranza = new ModeloFacturaCredito();
      cobranza.setSaldo(Double.parseDouble(this.tfSaldo.getText()));
      cobranza.setFechaUEntrega(fecha());
      cobranza.setHoraEntrega(hora());
      cobranza.setEntrega(totalEntrega);
      cobranza.setCodigoCabecera(Integer.parseInt(lblCodigoFactura.getText()));
      double saldo = Double.parseDouble(this.tfSaldo.getText());
      if (saldo == 0.0D) {
        cobranza.setEstado("PAGADO");
      } else {
        cobranza.setEstado("EN DEUDA");
      }
      DaoCobranzas.ActualizarCobranza(cobranza);
      
      FormularioCobranzas.btnActualizar.doClick();
    }
    else
    {
      ModeloFacturaCredito cobranza = new ModeloFacturaCredito();
      cobranza.setSaldo(Double.parseDouble(this.tfSaldo.getText()));
      cobranza.setFechaUEntrega(fecha());
      cobranza.setHoraEntrega(hora());
      cobranza.setEntrega(Double.parseDouble(tfEntrega.getText()));
      cobranza.setCodigoCabecera(Integer.parseInt(lblCodigoFactura.getText()));
      double saldo = Double.parseDouble(this.tfSaldo.getText());
      if (saldo == 0.0D) {
        cobranza.setEstado("PAGADO");
      } else {
        cobranza.setEstado("EN DEUDA");
      }
      DaoCobranzas.ActualizarCobranza(cobranza);
      
      FormularioCobranzas.btnActualizar.doClick();
    }
  }
  
  public static String fecha()
  {
    Date fecha = new Date();
    SimpleDateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");
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
