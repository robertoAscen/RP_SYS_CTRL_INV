/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Splash;

import com.eneri.scstock.Seguridad.Login;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.Window.Type;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 *
 * @author RAscencio
 */
public class Cambios extends JDialog
{    
  private JPanel panel;
  public static JLabel lblOperacion;
  public static JLabel lblEstado;
  CargarBarra hilo;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          Cambios frame = new Cambios();
          frame.setVisible(true);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }
  
  double j = 1.0D;
  double i = 50.0D;
  private JProgressBar Progreso;
  
  public Cambios()
  {
    setEnabled(false);
    setAutoRequestFocus(false);
    setType(Window.Type.UTILITY);
    setResizable(false);
    setBounds(100, 100, 468, 140);
    
    JPanel contentPane = new JPanel();
    contentPane.setForeground(Color.BLACK);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    lblEstado = new JLabel("");
    lblEstado.setForeground(Color.BLACK);
    lblEstado.setFont(new Font("Tahoma", 0, 15));
    lblEstado.setBounds(401, 27, 57, 24);
    contentPane.add(lblEstado);
    
    this.Progreso = new JProgressBar();
    this.Progreso.addChangeListener(new ChangeListener()
    {
      public void stateChanged(ChangeEvent evt)
      {
        Cambios.this.ProgresoStateChanged(evt);
      }
    });
    this.Progreso.setBounds(10, 27, 381, 24);
    contentPane.add(this.Progreso);
    
    this.panel = new JPanel();
    this.panel.setBorder(new TitledBorder(null, "Operaci�n:", 4, 2, null, null));
    this.panel.setBounds(10, 59, 448, 46);
    contentPane.add(this.panel);
    this.panel.setLayout(null);
    
    lblOperacion = new JLabel("");
    lblOperacion.setBounds(6, 16, 421, 20);
    this.panel.add(lblOperacion);
    lblOperacion.setFont(new Font("Tahoma", 0, 14));
    lblOperacion.setForeground(Color.BLACK);
    iniciar();
  }
  
  public void iniciar()
  {
    setLocationRelativeTo(null);
    
    this.hilo = new CargarBarra(getProgreso());
    this.hilo.start();
    this.hilo = null;
  }
  
  public JProgressBar getProgreso()
  {
    return this.Progreso;
  }
  
  public void setProgreso(JProgressBar Progreso)
  {
    this.Progreso = Progreso;
  }
  
  private void ProgresoStateChanged(ChangeEvent evt)
  {
    if ((this.Progreso.getValue() == this.i) && 
      (this.j != 101.0D))
    {
      this.i += 1.0D;
      this.j += 2.0D;
    }
    if (this.Progreso.getValue() == 100)
    {
      dispose();
      Login.main(null);
    }
  }    
}
