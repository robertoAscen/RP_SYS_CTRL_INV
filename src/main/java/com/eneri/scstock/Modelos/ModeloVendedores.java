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
public class ModeloVendedores
{
    
  private int codigo;
  private String nombre;
  private String cedula;
  private String direccion;
  private String contacto;
  private String observaciones;
  private String estado;
  private double salario;
  private double comision;
  
  public ModeloVendedores()
  {
    this.codigo = 0;
    this.nombre = "";
    this.cedula = "";
    this.direccion = "";
    this.contacto = "";
    this.salario = 0.0D;
    this.comision = 0.0D;
    this.observaciones = "";
    this.estado = "";
  }
  
  public ModeloVendedores(int codigo, String nombre, String cedula, String direccion, String contacto, double salario, double comision, String observaciones, String estado)
  {
    this.codigo = codigo;
    this.nombre = nombre;
    this.cedula = cedula;
    this.direccion = direccion;
    this.contacto = contacto;
    this.salario = salario;
    this.comision = comision;
    this.observaciones = observaciones;
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
  
  public void setContacto(String contacto)
  {
    this.contacto = contacto;
  }
  
  public double getSalario()
  {
    return this.salario;
  }
  
  public void setSalario(double salario)
  {
    this.salario = salario;
  }
  
  public double getComision()
  {
    return this.comision;
  }
  
  public void setComision(double comision)
  {
    this.comision = comision;
  }
  
  public String getObservaciones()
  {
    return this.observaciones;
  }
  
  public void setObservaciones(String observaciones)
  {
    this.observaciones = observaciones;
  }
  
  public String getEstado()
  {
    return this.estado;
  }
  
  public void setEstado(String estado)
  {
    this.estado = estado;
  }    
}
