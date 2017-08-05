/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Configuraciones;

import java.beans.Beans;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *
 * @author RAscencio
 */
public class Messages 
{
     private static final String BUNDLE_NAME = "Configuraciones.messages";
     private static final ResourceBundle RESOURCE_BUNDLE = null;
  
  private static ResourceBundle loadBundle()
  {
    return ResourceBundle.getBundle("Configuraciones.messages");
  }
  
  public static String getString(String key)
  {
    try
    {
      ResourceBundle bundle = Beans.isDesignTime() ? loadBundle() : RESOURCE_BUNDLE;
      return bundle.getString(key);
    }
    catch (MissingResourceException e) {}
    return "!" + key + "!";
  }   
}
