/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Herramientas;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author RAscencio
 */
public class Validaciones
{
    
  public void BloqueoNumerico(JTextField letras)
  {
    letras.addKeyListener(new KeyAdapter()
    {
      public void keyTyped(KeyEvent evento)
      {
        char caracter = evento.getKeyChar();
        if (Character.isDigit(caracter))
        {
          getClass();
          evento.consume();
        }
      }
    });
  }
  
  public void BloqueoAlfabetico(JTextField numeros)
  {
    numeros.addKeyListener(new KeyAdapter()
    {
      public void keyTyped(KeyEvent evento)
      {
        char caracter = evento.getKeyChar();
        if (!Character.isDigit(caracter))
        {
          getClass();
          evento.consume();
        }
      }
    });
  }
  
  public void ValidarFormatoPrecio(final JTextField numeros)
  {
    numeros.addKeyListener(new KeyAdapter()
    {
      public void keyTyped(KeyEvent evento)
      {
        char caracter = evento.getKeyChar();
        if ((numeros.getText().contains(".")) && 
          ((caracter < '0') || (caracter > '9')) && (caracter != '\b') && (caracter == '.')) {
          evento.consume();
        }
        if (((caracter < '0') || (caracter > '9')) && (caracter != '\b') && (caracter != '.')) {
          evento.consume();
        }
      }
    });
  }    
}
