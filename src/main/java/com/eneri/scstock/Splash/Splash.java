/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Splash;

import com.eneri.scstock.Mainframe.Principal;
import com.eneri.scstock.netbeans.lib.AbsoluteConstraints;
import com.eneri.scstock.netbeans.lib.AbsoluteLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author RAscencio
 */
public class Splash extends JFrame
{
    
  private JLabel jLabel1;
  private JProgressBar Progreso;
  Cargar hilo;
  double j = 1.0D;
  double i = 50.0D;
  
  public Splash()
  {
    initComponents();
    iniciar();
  }
  
  public void iniciar()
  {
    setLocationRelativeTo(null);
    this.Progreso.setVisible(false);
    this.hilo = new Cargar(getProgreso());
    this.hilo.start();
    this.hilo = null;
  }
  
  private void initComponents()
  {
    this.Progreso = new JProgressBar();
    this.jLabel1 = new JLabel();
    
    setDefaultCloseOperation(3);
    setUndecorated(true);
    getContentPane().setLayout(new AbsoluteLayout());
    
    this.Progreso.addChangeListener(new ChangeListener()
    {
      public void stateChanged(ChangeEvent evt)
      {
        Splash.this.ProgresoStateChanged(evt);
      }
    });
    getContentPane().add(this.Progreso, new AbsoluteConstraints(120, 140, 300, 30));
    
    this.jLabel1.setIcon(new ImageIcon(new File("data/graficos/Principal.jpg").getAbsolutePath()));
    getContentPane().add(this.jLabel1, new AbsoluteConstraints(0, 0, -1, -1));
    
    pack();
  }
  
  private void ProgresoStateChanged(ChangeEvent evt)
  {
    if ((this.Progreso.getValue() == this.i) && 
      (this.j != 101.0D))
    {
      this.i += 1.0D;
      this.j += 2.0D;
    }
    if (this.Progreso.getValue() == 100) {
      Principal.main(null);
    }
  }
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        new Splash().setVisible(true);
      }
    });
  }
  
  public JProgressBar getProgreso()
  {
    return this.Progreso;
  }
  
  public void setProgreso(JProgressBar Progreso)
  {
    this.Progreso = Progreso;
  }
}
