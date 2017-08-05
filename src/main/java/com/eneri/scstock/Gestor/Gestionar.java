/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Gestor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 *
 * @author RAscencio
 */
public class Gestionar 
{     
    FileInputStream entrada;
  FileOutputStream salida;
  File archivo;
  
  public String AbrirTexto(File archivo)
  {
    String contenido = "";
    try
    {
      this.entrada = new FileInputStream(archivo);
      int ascci;
      while ((ascci = this.entrada.read()) != -1)
      {
        int asccci=0;
        char caracter = (char)asccci;
        contenido = contenido + caracter;
      }
    }
    catch (Exception localException) {}
    return contenido;
  }
  
  public String GuardarTexto(File archivo, String contenido)
  {
    String respuesta = null;
    try
    {
      this.salida = new FileOutputStream(archivo);
      byte[] bytestxt = contenido.getBytes();
      this.salida.write(bytestxt);
      respuesta = "El archivo se ha guardado con �xito.";
    }
    catch (Exception localException) {}
    return respuesta;
  }
  
  public byte[] AbrirImagen(File archivo)
  {
    byte[] bytesImg = new byte[409600];
    try
    {
      this.entrada = new FileInputStream(archivo);
      this.entrada.read(bytesImg);
    }
    catch (Exception localException) {}
    return bytesImg;
  }
  
  public String GuardarImagen(File archivo, byte[] bytesImg)
  {
    String respuesta = null;
    try
    {
      this.salida = new FileOutputStream(archivo);
      this.salida.write(bytesImg);
      respuesta = "La imagen se ha guardado con �xito.";
    }
    catch (Exception localException) {}
    return respuesta;
  }
  
  public byte[] getBytes()
  {
    return null;
  }
}
