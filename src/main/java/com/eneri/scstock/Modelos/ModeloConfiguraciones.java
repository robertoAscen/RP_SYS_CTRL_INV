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
public class ModeloConfiguraciones 
{
    
  private int codigo;
  private String NombreEmpresa;
  private String telefono;
  private String fax;
  private String localidad;
  private String eslogan;
  private int desdeContado;
  private int hastaContado;
  private int desdeCredito;
  private int hastaCredito;
  private double utilmayorista;
  private double utilventa;
  private double utilcredito;
  private double iva1;
  private double iva2;
  private double divIva1;
  private double divIva2;
  private String moneda;
  private String simbolo;
  private String email;
  private String contrasenha;
  
  public ModeloConfiguraciones()
  {
    this.codigo = 0;
    this.NombreEmpresa = "";
    this.telefono = "";
    this.fax = "";
    this.localidad = "";
    this.desdeContado = 0;
    this.hastaContado = 0;
    this.desdeCredito = 0;
    this.hastaCredito = 0;
    this.utilmayorista = 0.0D;
    this.utilventa = 0.0D;
    this.utilcredito = 0.0D;
    this.iva1 = 0.0D;
    this.iva2 = 0.0D;
    this.divIva1 = 0.0D;
    this.divIva2 = 0.0D;
    this.moneda = "";
    this.simbolo = "";
    this.eslogan = "";
    this.contrasenha = "";
    this.email = "";
  }
  
  public ModeloConfiguraciones(int codigo, String nombreEmpresa, String telefono, String fax, String localidad, String eslogan, int desdeContado, int hastaContado, int desdeCredito, int hastaCredito, double utilmayorista, double utilventa, double utilcredito, double iva1, double iva2, double divIva1, double divIva2, String moneda, String simbolo, String email, String contrasenha)
  {
    this.codigo = codigo;
    this.NombreEmpresa = nombreEmpresa;
    this.telefono = telefono;
    this.fax = fax;
    this.localidad = localidad;
    this.eslogan = eslogan;
    this.desdeContado = desdeContado;
    this.hastaContado = hastaContado;
    this.desdeCredito = desdeCredito;
    this.hastaCredito = hastaCredito;
    this.utilmayorista = utilmayorista;
    this.utilventa = utilventa;
    this.utilcredito = utilcredito;
    this.iva1 = iva1;
    this.iva2 = iva2;
    this.divIva1 = divIva1;
    this.divIva2 = divIva2;
    this.moneda = moneda;
    this.simbolo = simbolo;
    this.email = email;
    this.contrasenha = contrasenha;
  }
  
  public int getCodigo()
  {
    return this.codigo;
  }
  
  public void setCodigo(int codigo)
  {
    this.codigo = codigo;
  }
  
  public String getNombreEmpresa()
  {
    return this.NombreEmpresa;
  }
  
  public void setNombreEmpresa(String nombreEmpresa)
  {
    this.NombreEmpresa = nombreEmpresa;
  }
  
  public String getTelefono()
  {
    return this.telefono;
  }
  
  public void setTelefono(String telefono)
  {
    this.telefono = telefono;
  }
  
  public String getFax()
  {
    return this.fax;
  }
  
  public void setFax(String fax)
  {
    this.fax = fax;
  }
  
  public String getLocalidad()
  {
    return this.localidad;
  }
  
  public void setLocalidad(String localidad)
  {
    this.localidad = localidad;
  }
  
  public String getEslogan()
  {
    return this.eslogan;
  }
  
  public void setEslogan(String eslogan)
  {
    this.eslogan = eslogan;
  }
  
  public int getDesdeContado()
  {
    return this.desdeContado;
  }
  
  public void setDesdeContado(int desdeContado)
  {
    this.desdeContado = desdeContado;
  }
  
  public int getHastaContado()
  {
    return this.hastaContado;
  }
  
  public void setHastaContado(int hastaContado)
  {
    this.hastaContado = hastaContado;
  }
  
  public int getDesdeCredito()
  {
    return this.desdeCredito;
  }
  
  public void setDesdeCredito(int desdeCredito)
  {
    this.desdeCredito = desdeCredito;
  }
  
  public int getHastaCredito()
  {
    return this.hastaCredito;
  }
  
  public void setHastaCredito(int hastaCredito)
  {
    this.hastaCredito = hastaCredito;
  }
  
  public double getUtilmayorista()
  {
    return this.utilmayorista;
  }
  
  public void setUtilmayorista(double utilmayorista)
  {
    this.utilmayorista = utilmayorista;
  }
  
  public double getUtilventa()
  {
    return this.utilventa;
  }
  
  public void setUtilventa(double utilventa)
  {
    this.utilventa = utilventa;
  }
  
  public double getUtilcredito()
  {
    return this.utilcredito;
  }
  
  public void setUtilcredito(double utilcredito)
  {
    this.utilcredito = utilcredito;
  }
  
  public double getIva1()
  {
    return this.iva1;
  }
  
  public void setIva1(double iva1)
  {
    this.iva1 = iva1;
  }
  
  public double getIva2()
  {
    return this.iva2;
  }
  
  public void setIva2(double iva2)
  {
    this.iva2 = iva2;
  }
  
  public double getDivIva1()
  {
    return this.divIva1;
  }
  
  public void setDivIva1(double divIva1)
  {
    this.divIva1 = divIva1;
  }
  
  public double getDivIva2()
  {
    return this.divIva2;
  }
  
  public void setDivIva2(double divIva2)
  {
    this.divIva2 = divIva2;
  }
  
  public String getMoneda()
  {
    return this.moneda;
  }
  
  public void setMoneda(String moneda)
  {
    this.moneda = moneda;
  }
  
  public String getSimbolo()
  {
    return this.simbolo;
  }
  
  public void setSimbolo(String simbolo)
  {
    this.simbolo = simbolo;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public String getContrasenha()
  {
    return this.contrasenha;
  }
  
  public void setContrasenha(String contrasenha)
  {
    this.contrasenha = contrasenha;
  }    
}
