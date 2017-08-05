/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.util;

import java.io.Serializable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author RAscencio
 */
public class ServiceLocator implements Serializable
{
    
  static final long serialVersionUID = 1L;
  private static ServiceLocator instance;
  private static Context context;
  
  private ServiceLocator()
    throws NamingException
  {
    context = new InitialContext();
  }
  
  public static ServiceLocator getInstance()
    throws NamingException
  {
    if (instance == null) {
      instance = new ServiceLocator();
    }
    return instance;
  }
  
  public DataSource getDataSource(String jndiDataSource)
    throws Exception
  {
    DataSource ds = (DataSource)context.lookup(jndiDataSource);
    return ds;
  }
}
