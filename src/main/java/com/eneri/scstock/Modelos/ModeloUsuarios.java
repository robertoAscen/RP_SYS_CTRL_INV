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
public class ModeloUsuarios 
{
    
  private int codigo;
  private String nombre;
  private String password;
  private byte[] imagen;
  
  public ModeloUsuarios()
  {
    this.codigo = 0;
    this.nombre = "";
    this.password = "";
  }
  
  public ModeloUsuarios(int codigo, String nombre, String password)
  {
    this.codigo = codigo;
    this.nombre = nombre;
    this.password = password;
  }
  
  public int getCodigo()
  {
    return this.codigo;
  }
  
  public void setCodigo(int codigo)
  {
    this.codigo = codigo;
  }
  
  public String getNombre()
  {
    return this.nombre;
  }
  
  public void setNombre(String nombre)
  {
    this.nombre = nombre;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }    
}
