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
public class ModeloSucursal 
{
    
  private int codigo;
  private String nombreSucursal;
  private String responsable;
  private String contacto;
  private String direccion;
  
  public ModeloSucursal()
  {
    this.codigo = 0;
    this.nombreSucursal = "";
    this.responsable = "";
    this.contacto = "";
    this.direccion = "";
  }
  
  public ModeloSucursal(int codigo, String nombreSucursal, String responsable, String contacto, String direccion)
  {
    this.codigo = codigo;
    this.nombreSucursal = nombreSucursal;
    this.responsable = responsable;
    this.contacto = contacto;
    this.direccion = direccion;
  }
  
  public int getCodigo()
  {
    return this.codigo;
  }
  
  public void setCodigo(int codigo)
  {
    this.codigo = codigo;
  }
  
  public String getNombreSucursal()
  {
    return this.nombreSucursal;
  }
  
  public void setNombreSucursal(String nombreSucursal)
  {
    this.nombreSucursal = nombreSucursal;
  }
  
  public String getResponsable()
  {
    return this.responsable;
  }
  
  public void setResponsable(String responsable)
  {
    this.responsable = responsable;
  }
  
  public String getContacto()
  {
    return this.contacto;
  }
  
  public void setContacto(String contacto)
  {
    this.contacto = contacto;
  }
  
  public String getDireccion()
  {
    return this.direccion;
  }
  
  public void setDireccion(String direccion)
  {
    this.direccion = direccion;
  }
  
  public String toString()
  {
    return this.codigo + " - " + this.nombreSucursal;
  }    
}
