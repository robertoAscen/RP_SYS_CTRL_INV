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
public class ModeloFamilia 
{
    
  private int codigo;
  private String descripcion;
  private String estado;
  
  public ModeloFamilia()
  {
    this.codigo = 0;
    this.descripcion = "";
    this.estado = "";
  }
  
  public ModeloFamilia(int codigo, String descripcion, String estado)
  {
    this.codigo = codigo;
    this.descripcion = descripcion;
    this.estado = estado;
  }
  
  public int getCodigo()
  {
    return this.codigo;
  }
  
  public void setCodigo(int codigo)
  {
    this.codigo = codigo;
  }
  
  public String getDescripcion()
  {
    return this.descripcion;
  }
  
  public void setDescripcion(String descripcion)
  {
    this.descripcion = descripcion;
  }
  
  public String getEstado()
  {
    return this.estado;
  }
  
  public void setEstado(String estado)
  {
    this.estado = estado;
  }
  
  public String toString()
  {
    return this.codigo + "-" + this.descripcion;
  }    
}
