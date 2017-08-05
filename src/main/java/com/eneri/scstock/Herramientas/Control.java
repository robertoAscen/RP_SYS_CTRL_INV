/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Herramientas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;

/**
 *
 * @author RAscencio
 */
public class Control
{
    private String appPath = System.getProperties().getProperty("user.dir");
  private File fichero = new File(this.appPath + "\\miApp.tmp");
  private int segundos = 20;
  
  public boolean comprobar()
  {
    if (this.fichero.exists())
    {
      long tiempo = leer();
      long res = restarTiempo(tiempo);
      if (res < this.segundos)
      {
        JOptionPane.showMessageDialog(null, "El programa ya est� en ejecuci�n.", "Error", 0);
        return false;
      }
      programar_tarea();
      return true;
    }
    crearTMP();
    programar_tarea();
    return true;
  }
  
  public long leer()
  {
    String linea = "0";
    try
    {
      BufferedReader bufferedReader = new BufferedReader(new FileReader(this.fichero));
      while (bufferedReader.ready()) {
        linea = bufferedReader.readLine();
      }
    }
    catch (IOException e)
    {
      System.err.println(e.getMessage());
    }
    return Long.valueOf(linea).longValue();
  }
  
  public void programar_tarea()
  {
    ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    scheduler.scheduleAtFixedRate(
      new Runnable()
      {
        public void run()
        {
          Control.this.crearTMP();
        }
      }, 1000L, this.segundos * 1000, TimeUnit.MILLISECONDS);
  }
  
  public void crearTMP()
  {
    Date fecha = new Date();
    try
    {
      BufferedWriter writer = new BufferedWriter(new FileWriter(this.fichero));
      writer.write(String.valueOf(fecha.getTime()));
      writer.close();
    }
    catch (IOException e)
    {
      System.err.println(e.getMessage());
    }
  }
  
  public long restarTiempo(long tiempoActual)
  {
    Date date = new Date();
    long tiempoTMP = date.getTime();
    long tiempo = tiempoTMP - tiempoActual;
    tiempo /= 1000L;
    return tiempo;
  }
  
  public void cerrarApp()
  {
    if (this.fichero.exists()) {
      this.fichero.delete();
    }
    System.exit(0);
  }    
}
