/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Seguridad;

/**
 *
 * @author RAscencio
 */
public class Encriptador
{
    
  public String encriptar(String texto, String clave)
  {
    int tamtext = texto.length();
    int tamclav = clave.length();
    int p = 0;
    String encriptado = "";
    int[] textoAscii = new int[tamtext];
    int[] claveAscii = new int[tamclav];
    for (int i = 0; i < tamtext; i++) {
      textoAscii[i] = texto.charAt(i);
    }
    for (int i = 0; i < tamclav; i++) {
      claveAscii[i] = clave.charAt(i);
    }
    for (int i = 0; i < tamtext; i++)
    {
      p++;
      if (p >= tamclav) {
        p = 0;
      }
      int temp = textoAscii[i] + claveAscii[p];
      if (temp > 255) {
        temp -= 255;
      }
      encriptado = encriptado + (char)temp;
    }
    return encriptado;
  }
  
  public String desencriptar(String texto, String clave)
  {
    int tamtext = texto.length();
    int tamclav = clave.length();
    int p = 0;
    String desencriptado = "";
    int[] textoAscii = new int[tamtext];
    int[] claveAscii = new int[tamclav];
    for (int i = 0; i < tamtext; i++) {
      textoAscii[i] = texto.charAt(i);
    }
    for (int i = 0; i < tamclav; i++) {
      claveAscii[i] = clave.charAt(i);
    }
    for (int i = 0; i < tamtext; i++)
    {
      p++;
      if (p >= tamclav) {
        p = 0;
      }
      int temp = textoAscii[i] - claveAscii[p];
      if (temp < 0) {
        temp += 256;
      }
      desencriptado = desencriptado + (char)temp;
    }
    return desencriptado;
  }
  
  public String ClaveEncryption()
  {
    String clave = "x";
    return clave;
  }    
}
