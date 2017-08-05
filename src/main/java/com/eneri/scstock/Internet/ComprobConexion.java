/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Internet;

import java.net.Socket;
import javax.swing.JOptionPane;
/**
 *
 * @author RAscencio
 */
public class ComprobConexion 
{
    
  public static boolean Conexion()
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
        JOptionPane.showMessageDialog(null, "�Conexi�n establecida con �xito!");
      }
      else
      {
        respuesta = false;
      }
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(null, "�No se pudo establecer conexi�n a internet!");
    }
    return respuesta;
  }    
}
