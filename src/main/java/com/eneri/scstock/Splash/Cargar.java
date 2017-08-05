/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Splash;

import javax.swing.JProgressBar;
/**
 *
 * @author RAscencio
 */
public class Cargar extends Thread
{
    
  JProgressBar progreso;
  
  public Cargar(JProgressBar progreso)
  {
    this.progreso = progreso;
  }
  
  public void run()
  {
    for (int i = 1; i <= 100; i++)
    {
      this.progreso.setValue(i);
      pausa(30);
    }
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
