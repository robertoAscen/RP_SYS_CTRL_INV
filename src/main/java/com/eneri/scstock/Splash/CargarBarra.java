/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Splash;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
/**
 *
 * @author RAscencio
 */
public class CargarBarra extends Thread
{
     
  JProgressBar progreso;
  private Cambios s;
  
  public CargarBarra(JProgressBar progreso)
  {
    this.progreso = progreso;
  }
  
  public void run()
  {
    for (int i = 1; i <= 100; i++)
    {
      Cambios.lblEstado.setText(i + "%");
      this.progreso.setValue(i);
      
      pausa(30);
      if (i == 1) {
        Cambios.lblOperacion.setText("Actualizando la base de datos...");
      }
      if (i == 50) {
        Cambios.lblOperacion.setText("Aplicando nuevos cambios...");
      }
      if (i == 75) {
        Cambios.lblOperacion.setText("Iniciando sistema...");
      }
    }
    JOptionPane.showMessageDialog(null, "�Se han realizado los cambios con �xito!");
  }
  
  public void pausa(int mlSeg)
  {
    try
    {
      Thread.sleep(mlSeg);
    }
    catch (Exception localException) {}
  }   
}
