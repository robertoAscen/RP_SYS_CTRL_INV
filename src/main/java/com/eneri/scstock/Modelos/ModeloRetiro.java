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
public class ModeloRetiro
{
    
  private int codigo;
  private String fecha;
  private String hora;
  private double monto;
  private String detalle;
  
  public ModeloRetiro()
  {
    this.codigo = 0;
    this.fecha = "";
    this.hora = "";
    this.monto = 0.0D;
    this.detalle = "";
  }
  
  public ModeloRetiro(int codigo, String fecha, String hora, double monto, String detalle)
  {
    this.codigo = codigo;
    this.fecha = fecha;
    this.hora = hora;
    this.monto = monto;
    this.detalle = detalle;
  }
  
  public int getCodigo()
  {
    return this.codigo;
  }
  
  public void setCodigo(int codigo)
  {
    this.codigo = codigo;
  }
  
  public String getFecha()
  {
    return this.fecha;
  }
  
  public void setFecha(String fecha)
  {
    this.fecha = fecha;
  }
  
  public String getHora()
  {
    return this.hora;
  }
  
  public void setHora(String hora)
  {
    this.hora = hora;
  }
  
  public double getMonto()
  {
    return this.monto;
  }
  
  public void setMonto(double monto)
  {
    this.monto = monto;
  }
  
  public String getDetalle()
  {
    return this.detalle;
  }
  
  public void setDetalle(String detalle)
  {
    this.detalle = detalle;
  }    
}
