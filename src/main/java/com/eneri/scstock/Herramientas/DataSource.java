/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Herramientas;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author RAscencio
 */
public class DataSource implements JRDataSource
{
    
  private int contador = 0;
  private ArrayList lista;
  private ArrayList lista2;
  
  public boolean next()
    throws JRException
  {
    this.contador += 1;
    if (this.contador == 1) {
      return true;
    }
    return false;
  }
  
  public Object getFieldValue(JRField field)
    throws JRException
  {
    if (field == null) {
      return null;
    }
    if (field.getName().equalsIgnoreCase("lista")) {
      return new JRBeanCollectionDataSource(this.lista);
    }
    if (field.getName().equalsIgnoreCase("lista2")) {
      return new JRBeanCollectionDataSource(this.lista2);
    }
    return null;
  }
  
  public Object getFieldVal(JRField campo)
    throws JRException
  {
    if (campo == null) {
      return null;
    }
    if (campo.getName().equalsIgnoreCase("lista2")) {
      return new JRBeanCollectionDataSource(this.lista2);
    }
    return null;
  }
  
  public int getContador()
  {
    return this.contador;
  }
  
  public void setContador(int contador)
  {
    this.contador = contador;
  }
  
  public ArrayList getLista()
  {
    return this.lista;
  }
  
  public void setLista(ArrayList lista)
  {
    this.lista = lista;
  }
  
  public List getLista2()
  {
    return this.lista2;
  }
  
  public void setLista2(ArrayList lista2)
  {
    this.lista2 = lista2;
  }    
}
