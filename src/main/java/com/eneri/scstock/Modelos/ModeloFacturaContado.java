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
public class ModeloFacturaContado 
{
    
  private int codigoCabecera;
  private int codigoCliente;
  private String fecha;
  private String hora;
  private int codigoDetalle;
  private String codigoProducto;
  private double cantidad;
  private double precioUnitario;
  private double subtotal;
  private double total;
  private double totalIva;
  private String iva;
  private String estado;
  private ModeloClientes cliente;
  private ModeloProductos producto;
  private ModeloVendedores vendedor;
  private String descripcionProd;
  private String unidadDeMedida;
  private String codigoProd;
  private double tiva1;
  private double tiva2;
  private double descuento;
  private double montoDescuento;
  
  public ModeloFacturaContado()
  {
    this.codigoCabecera = 0;
    this.codigoCliente = 0;
    this.fecha = "";
    this.hora = "";
    this.estado = "";
    this.codigoDetalle = 0;
    this.codigoProducto = "";
    this.cantidad = 0.0D;
    this.precioUnitario = 0.0D;
    this.subtotal = 0.0D;
    this.total = 0.0D;
    this.totalIva = 0.0D;
    this.iva = "";
    this.cliente = new ModeloClientes();
    this.vendedor = new ModeloVendedores();
    this.producto = new ModeloProductos();
    this.unidadDeMedida = "";
    this.descripcionProd = "";
    this.codigoProd = "";
    this.tiva1 = 0.0D;
    this.tiva2 = 0.0D;
    this.descuento = 0.0D;
    this.montoDescuento = 0.0D;
  }
  
  public ModeloFacturaContado(int codigoCabecera, int codigoCliente, String fecha, String hora, int codigoDetalle, String codigoProducto, double cantidad, double precioUnitario, double subtotal, double total, double totalIva, String iva, String estado, ModeloClientes cliente, ModeloProductos producto, ModeloVendedores vendedor, String descripcionProd, String unidadDeMedida, String codigoProd, double tiva1, double tiva2, double descuento, double montoDescuento)
  {
    this.codigoCabecera = codigoCabecera;
    this.codigoCliente = codigoCliente;
    this.fecha = fecha;
    this.hora = hora;
    this.codigoDetalle = codigoDetalle;
    this.codigoProducto = codigoProducto;
    this.cantidad = cantidad;
    this.precioUnitario = precioUnitario;
    this.subtotal = subtotal;
    this.total = total;
    this.totalIva = totalIva;
    this.iva = iva;
    this.estado = estado;
    this.cliente = cliente;
    this.producto = producto;
    this.vendedor = vendedor;
    this.descripcionProd = descripcionProd;
    this.unidadDeMedida = unidadDeMedida;
    this.codigoProd = codigoProd;
    this.tiva1 = tiva1;
    this.tiva2 = tiva2;
    this.descuento = descuento;
    this.montoDescuento = montoDescuento;
  }
  
  public int getCodigoCabecera()
  {
    return this.codigoCabecera;
  }
  
  public void setCodigoCabecera(int codigoCabecera)
  {
    this.codigoCabecera = codigoCabecera;
  }
  
  public int getCodigoCliente()
  {
    return this.codigoCliente;
  }
  
  public void setCodigoCliente(int codigoCliente)
  {
    this.codigoCliente = codigoCliente;
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
  
  public int getCodigoDetalle()
  {
    return this.codigoDetalle;
  }
  
  public void setCodigoDetalle(int codigoDetalle)
  {
    this.codigoDetalle = codigoDetalle;
  }
  
  public String getCodigoProducto()
  {
    return this.codigoProducto;
  }
  
  public void setCodigoProducto(String codigoProducto)
  {
    this.codigoProducto = codigoProducto;
  }
  
  public double getCantidad()
  {
    return this.cantidad;
  }
  
  public void setCantidad(double cantidad)
  {
    this.cantidad = cantidad;
  }
  
  public double getPrecioUnitario()
  {
    return this.precioUnitario;
  }
  
  public void setPrecioUnitario(double precioUnitario)
  {
    this.precioUnitario = precioUnitario;
  }
  
  public double getSubtotal()
  {
    return this.subtotal;
  }
  
  public void setSubtotal(double subtotal)
  {
    this.subtotal = subtotal;
  }
  
  public double getTotal()
  {
    return this.total;
  }
  
  public void setTotal(double total)
  {
    this.total = total;
  }
  
  public double getTotalIva()
  {
    return this.totalIva;
  }
  
  public void setTotalIva(double totalIva)
  {
    this.totalIva = totalIva;
  }
  
  public String getIva()
  {
    return this.iva;
  }
  
  public void setIva(String iva)
  {
    this.iva = iva;
  }
  
  public String getEstado()
  {
    return this.estado;
  }
  
  public void setEstado(String estado)
  {
    this.estado = estado;
  }
  
  public ModeloClientes getCliente()
  {
    return this.cliente;
  }
  
  public void setCliente(ModeloClientes cliente)
  {
    this.cliente = cliente;
  }
  
  public ModeloProductos getProducto()
  {
    return this.producto;
  }
  
  public void setProducto(ModeloProductos producto)
  {
    this.producto = producto;
  }
  
  public ModeloVendedores getVendedor()
  {
    return this.vendedor;
  }
  
  public void setVendedor(ModeloVendedores vendedor)
  {
    this.vendedor = vendedor;
  }
  
  public String getDescripcionProd()
  {
    return this.descripcionProd;
  }
  
  public void setDescripcionProd(String descripcionProd)
  {
    this.descripcionProd = descripcionProd;
  }
  
  public String getUnidadDeMedida()
  {
    return this.unidadDeMedida;
  }
  
  public void setUnidadDeMedida(String unidadDeMedida)
  {
    this.unidadDeMedida = unidadDeMedida;
  }
  
  public String getCodigoProd()
  {
    return this.codigoProd;
  }
  
  public void setCodigoProd(String codigoProd)
  {
    this.codigoProd = codigoProd;
  }
  
  public double getTiva1()
  {
    return this.tiva1;
  }
  
  public void setTiva1(double tiva1)
  {
    this.tiva1 = tiva1;
  }
  
  public double getTiva2()
  {
    return this.tiva2;
  }
  
  public void setTiva2(double tiva2)
  {
    this.tiva2 = tiva2;
  }
  
  public double getDescuento()
  {
    return this.descuento;
  }
  
  public void setDescuento(double descuento)
  {
    this.descuento = descuento;
  }
  
  public double getMontoDescuento()
  {
    return this.montoDescuento;
  }
  
  public void setMontoDescuento(double montoDescuento)
  {
    this.montoDescuento = montoDescuento;
  }    
}
