/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Herramientas;

/**
 *
 * @author RAscencio
 */
public class FormatoPrecio 
{
       public static double NumeroFormato(double num)
  {
    double valor = 0.0D;
    
    valor = num;
    
    valor *= 100.0D;
    valor = Math.round(valor);
    valor /= 100.0D;
    
    return valor;
  }   
}
