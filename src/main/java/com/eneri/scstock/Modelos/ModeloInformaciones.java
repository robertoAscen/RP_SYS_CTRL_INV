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
public class ModeloInformaciones 
{
    
  private int codigo;
  private double cantVendida;
  private String codigoProducto;
  private String fecha;
  private ModeloProductos producto;
  private String descripcionProducto;
  private double precioUnitario;
  private String condicion;
  private double subtotal;
  private double stockActual;
  private double utilidad;
  
  public ModeloInformaciones()
  {
    this.codigo = 0;
    this.cantVendida = 0.0D;
    this.codigoProducto = "";
    this.fecha = "";
    this.producto = new ModeloProductos();
    this.descripcionProducto = "";
    this.precioUnitario = 0.0D;
    this.condicion = "";
    this.subtotal = 0.0D;
    this.stockActual = 0.0D;
    this.utilidad = 0.0D;
  }
  
  public ModeloInformaciones(int codigo, double cantVendida, String codigoProducto, String fecha, ModeloProductos producto, String descripcionProducto, double precioUnitario, String condicion, double subtotal, double stockActual, double utilidad)
  {
    this.codigo = codigo;
    this.cantVendida = cantVendida;
    this.codigoProducto = codigoProducto;
    this.fecha = fecha;
    this.producto = producto;
    this.descripcionProducto = descripcionProducto;
    this.precioUnitario = precioUnitario;
    this.condicion = condicion;
    this.subtotal = subtotal;
    this.stockActual = stockActual;
    this.utilidad = utilidad;
  }
  
  public int getCodigo()
  {
    return this.codigo;
  }
  
  public void setCodigo(int codigo)
  {
    this.codigo = codigo;
  }
  
  public double getCantVendida()
  {
    return this.cantVendida;
  }
  
  public void setCantVendida(double cantVendida)
  {
    this.cantVendida = cantVendida;
  }
  
  public String getCodigoProducto()
  {
    return this.codigoProducto;
  }
  
  public void setCodigoProducto(String codigoProducto)
  {
    this.codigoProducto = codigoProducto;
  }
  
  public String getFecha()
  {
    return this.fecha;
  }
  
  public void setFecha(String fecha)
  {
    this.fecha = fecha;
  }
  
  public ModeloProductos getProducto()
  {
    return this.producto;
  }
  
  public void setProducto(ModeloProductos producto)
  {
    this.producto = producto;
  }
  
  public String getDescripcionProducto()
  {
    return this.descripcionProducto;
  }
  
  public void setDescripcionProducto(String descripcionProducto)
  {
    this.descripcionProducto = descripcionProducto;
  }
  
  public double getPrecioUnitario()
  {
    return this.precioUnitario;
  }
  
  public void setPrecioUnitario(double precioUnitario)
  {
    this.precioUnitario = precioUnitario;
  }
  
  public String getCondicion()
  {
    return this.condicion;
  }
  
  public void setCondicion(String condicion)
  {
    this.condicion = condicion;
  }
  
  public double getSubtotal()
  {
    return this.subtotal;
  }
  
  public void setSubtotal(double subtotal)
  {
    this.subtotal = subtotal;
  }
  
  public double getStockActual()
  {
    return this.stockActual;
  }
  
  public void setStockActual(double stockActual)
  {
    this.stockActual = stockActual;
  }
  
  public double getUtilidad()
  {
    return this.utilidad;
  }
  
  public void setUtilidad(double utilidad)
  {
    this.utilidad = utilidad;
  }    
}
