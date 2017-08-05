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
public class ModeloProductos
{
    
  private String descripcion;
  private double stock;
  private double precioCosto;
  private double precioVenta;
  private double precioMayorista;
  private double precioCredito;
  private int codProveedor;
  private String unidadDeMedida;
  private int cantPaquete;
  private String facturar;
  private String codigo;
  private double descuento;
  private double subtotal;
  private ModeloProveedores proveedor;
  private double iva;
  private String estante;
  private String observaciones;
  private ModeloMarcas marcas;
  private ModeloFamilia familia;
  
  public ModeloProductos()
  {
    this.codigo = "";
    this.descripcion = "";
    this.stock = 0.0D;
    this.precioCosto = 0.0D;
    this.precioVenta = 0.0D;
    this.precioMayorista = 0.0D;
    this.precioCredito = 0.0D;
    this.codProveedor = 0;
    this.unidadDeMedida = "";
    this.cantPaquete = 0;
    this.proveedor = new ModeloProveedores();
    this.facturar = "";
    this.subtotal = 0.0D;
    this.iva = 0.0D;
    this.estante = "";
    this.observaciones = "";
    this.marcas = new ModeloMarcas();
    this.familia = new ModeloFamilia();
    this.descuento = 0.0D;
  }
  
  public ModeloProductos(String descripcion, double stock, double precioCosto, double precioVenta, double precioMayorista, double precioCredito, int codProveedor, String unidadDeMedida, int cantPaquete, String facturar, String codigo, double descuento, double subtotal, ModeloProveedores proveedor, double iva, String estante, String observaciones, ModeloMarcas marcas, ModeloFamilia familia)
  {
    this.descripcion = descripcion;
    this.stock = stock;
    this.precioCosto = precioCosto;
    this.precioVenta = precioVenta;
    this.precioMayorista = precioMayorista;
    this.precioCredito = precioCredito;
    this.codProveedor = codProveedor;
    this.unidadDeMedida = unidadDeMedida;
    this.cantPaquete = cantPaquete;
    this.facturar = facturar;
    this.codigo = codigo;
    this.descuento = descuento;
    this.subtotal = subtotal;
    this.proveedor = proveedor;
    this.iva = iva;
    this.estante = estante;
    this.observaciones = observaciones;
    this.marcas = marcas;
    this.familia = familia;
  }
  
  public String getDescripcion()
  {
    return this.descripcion;
  }
  
  public void setDescripcion(String descripcion)
  {
    this.descripcion = descripcion;
  }
  
  public double getStock()
  {
    return this.stock;
  }
  
  public void setStock(double stock)
  {
    this.stock = stock;
  }
  
  public double getPrecioCosto()
  {
    return this.precioCosto;
  }
  
  public void setPrecioCosto(double precioCosto)
  {
    this.precioCosto = precioCosto;
  }
  
  public double getPrecioVenta()
  {
    return this.precioVenta;
  }
  
  public void setPrecioVenta(double precioVenta)
  {
    this.precioVenta = precioVenta;
  }
  
  public double getPrecioMayorista()
  {
    return this.precioMayorista;
  }
  
  public void setPrecioMayorista(double precioMayorista)
  {
    this.precioMayorista = precioMayorista;
  }
  
  public double getPrecioCredito()
  {
    return this.precioCredito;
  }
  
  public void setPrecioCredito(double precioCredito)
  {
    this.precioCredito = precioCredito;
  }
  
  public int getCodProveedor()
  {
    return this.codProveedor;
  }
  
  public void setCodProveedor(int codProveedor)
  {
    this.codProveedor = codProveedor;
  }
  
  public String getUnidadDeMedida()
  {
    return this.unidadDeMedida;
  }
  
  public void setUnidadDeMedida(String unidadDeMedida)
  {
    this.unidadDeMedida = unidadDeMedida;
  }
  
  public int getCantPaquete()
  {
    return this.cantPaquete;
  }
  
  public void setCantPaquete(int cantPaquete)
  {
    this.cantPaquete = cantPaquete;
  }
  
  public String getFacturar()
  {
    return this.facturar;
  }
  
  public void setFacturar(String facturar)
  {
    this.facturar = facturar;
  }
  
  public String getCodigo()
  {
    return this.codigo;
  }
  
  public void setCodigo(String codigo)
  {
    this.codigo = codigo;
  }
  
  public double getDescuento()
  {
    return this.descuento;
  }
  
  public void setDescuento(double descuento)
  {
    this.descuento = descuento;
  }
  
  public double getSubtotal()
  {
    return this.subtotal;
  }
  
  public void setSubtotal(double subtotal)
  {
    this.subtotal = subtotal;
  }
  
  public ModeloProveedores getProveedor()
  {
    return this.proveedor;
  }
  
  public void setProveedor(ModeloProveedores proveedor)
  {
    this.proveedor = proveedor;
  }
  
  public double getIva()
  {
    return this.iva;
  }
  
  public void setIva(double iva)
  {
    this.iva = iva;
  }
  
  public String getEstante()
  {
    return this.estante;
  }
  
  public void setEstante(String estante)
  {
    this.estante = estante;
  }
  
  public String getObservaciones()
  {
    return this.observaciones;
  }
  
  public void setObservaciones(String observaciones)
  {
    this.observaciones = observaciones;
  }
  
  public ModeloMarcas getMarcas()
  {
    return this.marcas;
  }
  
  public void setMarcas(ModeloMarcas marcas)
  {
    this.marcas = marcas;
  }
  
  public ModeloFamilia getFamilia()
  {
    return this.familia;
  }
  
  public void setFamilia(ModeloFamilia familia)
  {
    this.familia = familia;
  }
  
  public String toString()
  {
    return this.codProveedor + "-" + this.proveedor;
  }    
}
