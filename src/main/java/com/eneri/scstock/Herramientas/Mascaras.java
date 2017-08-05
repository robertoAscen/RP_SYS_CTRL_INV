/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Herramientas;

import javax.swing.text.MaskFormatter;
/**
 *
 * @author RAscencio
 */
public class Mascaras
{
    public static MaskFormatter formatoFsecha()
    throws Exception
  {
    MaskFormatter formato = new MaskFormatter("##/##/####");
    formato.setPlaceholderCharacter('_');
    return formato;
  }
  
  public static MaskFormatter formatoHora()
    throws Exception
  {
    MaskFormatter formato = new MaskFormatter("000.000.000.000.000");
    formato.setPlaceholderCharacter('-');
    return formato;
  }    
}
