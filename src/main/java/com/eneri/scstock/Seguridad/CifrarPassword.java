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
public class CifrarPassword 
{
    
  public String Encriptar(String texto)
  {
    char[] array = texto.toCharArray();
    for (int i = 0; i < array.length; i++) {
      array[i] = ((char)(array[i] + '\t'));
    }
    String encriptado = String.valueOf(array);
    return encriptado;
  }
  
  public String Desencriptar(String texto)
  {
    char[] arrayD = texto.toCharArray();
    for (int i = 0; i < arrayD.length; i++) {
      arrayD[i] = ((char)(arrayD[i] - '\t'));
    }
    String desencriptado = String.valueOf(arrayD);
    return desencriptado;
  }
  
  public static void main(String[] args)
  {
    CifrarPassword x = new CifrarPassword();
    String c = x.Desencriptar("pjkrvrjvx{");
    System.out.println(c);
  }    
}
