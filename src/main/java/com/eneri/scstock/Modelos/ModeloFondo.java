/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Modelos;

/**
 *
 * @author RAscencio
 */
public class ModeloFondo 
{
    
  private String fondo;
  
  public ModeloFondo()
  {
    this.fondo = "";
  }
  
  public ModeloFondo(String fondo)
  {
    this.fondo = fondo;
  }
  
  public String getFondo()
  {
    return this.fondo;
  }
  
  public void setFondo(String fondo)
  {
    this.fondo = fondo;
  }    
}
