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
public class ModeloProveedores 
{
    
  private int codigo;
  private String nombre;
  private String direccion;
  private String telefono;
  private String telefono2;
  private String email;
  private String sitioweb;
  
  public ModeloProveedores()
  {
    this.codigo = 0;
    this.nombre = "";
    this.direccion = "";
    this.telefono = "";
    this.telefono2 = "";
    this.email = "";
    this.sitioweb = "";
  }
  
  public ModeloProveedores(int codigo, String nombre, String direccion, String telefono, String telefono2, String email, String sitioweb)
  {
    this.codigo = codigo;
    this.nombre = nombre;
    this.direccion = direccion;
    this.telefono = telefono;
    this.telefono2 = telefono2;
    this.email = email;
    this.sitioweb = sitioweb;
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
  
  public String getDireccion()
  {
    return this.direccion;
  }
  
  public void setDireccion(String direccion)
  {
    this.direccion = direccion;
  }
  
  public String getTelefono()
  {
    return this.telefono;
  }
  
  public void setTelefono(String telefono)
  {
    this.telefono = telefono;
  }
  
  public String getTelefono2()
  {
    return this.telefono2;
  }
  
  public void setTelefono2(String telefono2)
  {
    this.telefono2 = telefono2;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public String getSitioweb()
  {
    return this.sitioweb;
  }
  
  public void setSitioweb(String sitioweb)
  {
    this.sitioweb = sitioweb;
  }
  
  public String toString()
  {
    return this.codigo + "-" + this.nombre;
  }    
}
