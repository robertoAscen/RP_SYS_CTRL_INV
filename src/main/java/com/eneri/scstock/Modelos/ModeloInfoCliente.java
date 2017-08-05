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
public class ModeloInfoCliente
{
    
  private int codigo;
  private ModeloClientes cliente;
  private int mes;
  private int anho;
  private double total;
  
  public ModeloInfoCliente()
  {
    this.codigo = 0;
    this.cliente = new ModeloClientes();
    this.mes = 0;
    this.anho = 0;
    this.total = 0.0D;
  }
  
  public ModeloInfoCliente(int codigo, ModeloClientes cliente, int mes, int anho, double total)
  {
    this.codigo = codigo;
    this.cliente = cliente;
    this.mes = mes;
    this.anho = anho;
    this.total = total;
  }
  
  public int getCodigo()
  {
    return this.codigo;
  }
  
  public void setCodigo(int codigo)
  {
    this.codigo = codigo;
  }
  
  public ModeloClientes getCliente()
  {
    return this.cliente;
  }
  
  public void setCliente(ModeloClientes cliente)
  {
    this.cliente = cliente;
  }
  
  public int getMes()
  {
    return this.mes;
  }
  
  public void setMes(int mes)
  {
    this.mes = mes;
  }
  
  public int getAnho()
  {
    return this.anho;
  }
  
  public void setAnho(int anho)
  {
    this.anho = anho;
  }
  
  public double getTotal()
  {
    return this.total;
  }
  
  public void setTotal(double total)
  {
    this.total = total;
  }    
}
