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
public class ModeloInventarios 
{
    
  private int codigo;
  private ModeloProductos producto;
  private double cantInventario;
  private double stockActual;
  private String estado;
  private double faSo;
  private String fecha;
  private double costo;
  private double subTotal;
  
  public ModeloInventarios()
  {
    this.codigo = 0;
    this.producto = new ModeloProductos();
    this.cantInventario = 0.0D;
    this.stockActual = 0.0D;
    this.estado = "";
    this.faSo = 0.0D;
    this.fecha = "";
    this.costo = 0.0D;
    this.subTotal = 0.0D;
  }
  
  public ModeloInventarios(int codigo, ModeloProductos producto, double cantInventario, double stockActual, String estado, double faSo, String fecha, double costo, double subTotal)
  {
    this.codigo = codigo;
    this.producto = producto;
    this.cantInventario = cantInventario;
    this.stockActual = stockActual;
    this.estado = estado;
    this.faSo = faSo;
    this.fecha = fecha;
    this.costo = costo;
    this.subTotal = subTotal;
  }
  
  public String getFecha()
  {
    return this.fecha;
  }
  
  public void setFecha(String fecha)
  {
    this.fecha = fecha;
  }
  
  public int getCodigo()
  {
    return this.codigo;
  }
  
  public void setCodigo(int codigo)
  {
    this.codigo = codigo;
  }
  
  public ModeloProductos getProducto()
  {
    return this.producto;
  }
  
  public void setProducto(ModeloProductos producto)
  {
    this.producto = producto;
  }
  
  public double getCantInventario()
  {
    return this.cantInventario;
  }
  
  public void setCantInventario(double cantInventario)
  {
    this.cantInventario = cantInventario;
  }
  
  public double getStockActual()
  {
    return this.stockActual;
  }
  
  public void setStockActual(double stockActual)
  {
    this.stockActual = stockActual;
  }
  
  public String getEstado()
  {
    return this.estado;
  }
  
  public void setEstado(String estado)
  {
    this.estado = estado;
  }
  
  public double getFaSo()
  {
    return this.faSo;
  }
  
  public void setFaSo(double faSo)
  {
    this.faSo = faSo;
  }
  
  public double getCosto()
  {
    return this.costo;
  }
  
  public void setCosto(double costo)
  {
    this.costo = costo;
  }
  
  public double getSubTotal()
  {
    return this.subTotal;
  }
  
  public void setSubTotal(double subTotal)
  {
    this.subTotal = subTotal;
  }    
}
