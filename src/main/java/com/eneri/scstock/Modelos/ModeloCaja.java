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
public class ModeloCaja 
{
    private int codigo;
  private double totalVenta;
  private double totalRetiro;
  private double saldo;
  private String fechaCierre;
  private String horaCierre;
  
  public ModeloCaja()
  {
    this.codigo = 0;
    this.totalVenta = 0.0D;
    this.totalRetiro = 0.0D;
    this.saldo = 0.0D;
    this.fechaCierre = "";
    this.horaCierre = "";
  }
  
  public ModeloCaja(int codigo, double totalVenta, double totalRetiro, double saldo, String fechaCierre, String horaCierre)
  {
    this.codigo = codigo;
    this.totalVenta = totalVenta;
    this.totalRetiro = totalRetiro;
    this.saldo = saldo;
    this.fechaCierre = fechaCierre;
    this.horaCierre = horaCierre;
  }
  
  public int getCodigo()
  {
    return this.codigo;
  }
  
  public void setCodigo(int codigo)
  {
    this.codigo = codigo;
  }
  
  public double getTotalVenta()
  {
    return this.totalVenta;
  }
  
  public void setTotalVenta(double totalVenta)
  {
    this.totalVenta = totalVenta;
  }
  
  public double getTotalRetiro()
  {
    return this.totalRetiro;
  }
  
  public void setTotalRetiro(double totalRetiro)
  {
    this.totalRetiro = totalRetiro;
  }
  
  public double getSaldo()
  {
    return this.saldo;
  }
  
  public void setSaldo(double saldo)
  {
    this.saldo = saldo;
  }
  
  public String getFechaCierre()
  {
    return this.fechaCierre;
  }
  
  public void setFechaCierre(String fechaCierre)
  {
    this.fechaCierre = fechaCierre;
  }
  
  public String getHoraCierre()
  {
    return this.horaCierre;
  }
  
  public void setHoraCierre(String horaCierre)
  {
    this.horaCierre = horaCierre;
  }    
}
