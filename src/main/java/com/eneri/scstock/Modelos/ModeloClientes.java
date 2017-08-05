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
public class ModeloClientes
{
    
  private int codigo;
  private String nombre;
  private String cedula;
  private String direccion;
  private String contacto;
  private String email;
  private String contacto2;
  private double descuento;
  
  public ModeloClientes()
  {
    this.codigo = 0;
    this.nombre = "";
    this.cedula = "";
    this.direccion = "";
    this.contacto = "";
    this.email = "";
    this.contacto2 = "";
    this.descuento = 0.0D;
  }
  
  public ModeloClientes(int codigo, String nombre, String cedula, String direccion, String email, String contacto, String contacto2, double descuento)
  {
    this.codigo = codigo;
    this.nombre = nombre;
    this.cedula = cedula;
    this.direccion = direccion;
    this.contacto = contacto;
    this.email = email;
    this.contacto2 = contacto2;
    this.descuento = descuento;
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
  
  public String getCedula()
  {
    return this.cedula;
  }
  
  public void setCedula(String cedula)
  {
    this.cedula = cedula;
  }
  
  public String getDireccion()
  {
    return this.direccion;
  }
  
  public void setDireccion(String direccion)
  {
    this.direccion = direccion;
  }
  
  public String getContacto()
  {
    return this.contacto;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public String getContacto2()
  {
    return this.contacto2;
  }
  
  public void setContacto2(String contacto2)
  {
    this.contacto2 = contacto2;
  }
  
  public void setContacto(String contacto)
  {
    this.contacto = contacto;
  }
  
  public double getDescuento()
  {
    return this.descuento;
  }
  
  public void setDescuento(double descuento)
  {
    this.descuento = descuento;
  }    
}
