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
public class ModeloFacturaCredito
{
    
  private int codigoCabecera;
  private int codigoCliente;
  private String fecha;
  private String hora;
  private String horaEntrega;
  private int codigoDetalle;
  private String codigoProducto;
  private double cantidad;
  private double precioUnitario;
  private double subtotal;
  private ModeloClientes cliente;
  private ModeloProductos producto;
  private double total;
  private double saldo;
  private double entrega;
  private String fechaUEntrega;
  private String estado;
  private ModeloVendedores vendedor;
  
  public ModeloFacturaCredito()
  {
    this.codigoCabecera = 0;
    this.codigoCliente = 0;
    this.fecha = "";
    this.hora = "";
    
    this.codigoDetalle = 0;
    this.codigoProducto = "";
    this.cantidad = 0.0D;
    this.precioUnitario = 0.0D;
    this.subtotal = 0.0D;
    this.cliente = new ModeloClientes();
    this.producto = new ModeloProductos();
    this.total = 0.0D;
    this.saldo = 0.0D;
    this.estado = "";
    this.entrega = 0.0D;
    this.fechaUEntrega = "";
    this.horaEntrega = "";
    this.vendedor = new ModeloVendedores();
  }
  
  public ModeloFacturaCredito(int codigoCabecera, int codigoCliente, String fecha, String hora, int codigoDetalle, String codigoProducto, double cantidad, double precioUnitario, double subtotal, ModeloClientes cliente, ModeloProductos producto, double total, double saldo, String estado, double entrega, String fechaUEntrega, String horaEntrega, ModeloVendedores vendedor)
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
    this.cliente = cliente;
    this.producto = producto;
    this.total = total;
    this.saldo = saldo;
    this.estado = estado;
    this.fechaUEntrega = fechaUEntrega;
    this.horaEntrega = horaEntrega;
    this.vendedor = vendedor;
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
  
  public String getHoraEntrega()
  {
    return this.horaEntrega;
  }
  
  public void setHoraEntrega(String horaEntrega)
  {
    this.horaEntrega = horaEntrega;
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
  
  public double getTotal()
  {
    return this.total;
  }
  
  public void setTotal(double total)
  {
    this.total = total;
  }
  
  public double getSaldo()
  {
    return this.saldo;
  }
  
  public void setSaldo(double saldo)
  {
    this.saldo = saldo;
  }
  
  public double getEntrega()
  {
    return this.entrega;
  }
  
  public void setEntrega(double entrega)
  {
    this.entrega = entrega;
  }
  
  public String getFechaUEntrega()
  {
    return this.fechaUEntrega;
  }
  
  public void setFechaUEntrega(String fechaUEntrega)
  {
    this.fechaUEntrega = fechaUEntrega;
  }
  
  public String getEstado()
  {
    return this.estado;
  }
  
  public void setEstado(String estado)
  {
    this.estado = estado;
  }
  
  public ModeloVendedores getVendedor()
  {
    return this.vendedor;
  }
  
  public void setVendedor(ModeloVendedores vendedor)
  {
    this.vendedor = vendedor;
  }    
}
