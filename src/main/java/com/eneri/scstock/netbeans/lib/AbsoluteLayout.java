/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.netbeans.lib;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager2;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author RAscencio
 */
public class AbsoluteLayout implements LayoutManager2, Serializable
{
    
  static final long serialVersionUID = -1919857869177070440L;
  protected Hashtable constraints = new Hashtable();
  
  public void addLayoutComponent(String paramString, Component paramComponent)
  {
    throw new IllegalArgumentException();
  }
  
  public void removeLayoutComponent(Component paramComponent)
  {
    this.constraints.remove(paramComponent);
  }
  
  public Dimension preferredLayoutSize(Container paramContainer)
  {
    int i = 0;
    int j = 0;
    Enumeration localEnumeration = this.constraints.keys();
    while (localEnumeration.hasMoreElements())
    {
      Component localComponent = (Component)localEnumeration.nextElement();
      AbsoluteConstraints localAbsoluteConstraints = (AbsoluteConstraints)this.constraints.get(localComponent);
      Dimension localDimension = localComponent.getPreferredSize();
      int k = localAbsoluteConstraints.getWidth();
      if (k == -1) {
        k = localDimension.width;
      }
      int m = localAbsoluteConstraints.getHeight();
      if (m == -1) {
        m = localDimension.height;
      }
      if (localAbsoluteConstraints.x + k > i) {
        i = localAbsoluteConstraints.x + k;
      }
      if (localAbsoluteConstraints.y + m > j) {
        j = localAbsoluteConstraints.y + m;
      }
    }
    return new Dimension(i, j);
  }
  
  public Dimension minimumLayoutSize(Container paramContainer)
  {
    int i = 0;
    int j = 0;
    Enumeration localEnumeration = this.constraints.keys();
    while (localEnumeration.hasMoreElements())
    {
      Component localComponent = (Component)localEnumeration.nextElement();
      AbsoluteConstraints localAbsoluteConstraints = (AbsoluteConstraints)this.constraints.get(localComponent);
      Dimension localDimension = localComponent.getMinimumSize();
      int k = localAbsoluteConstraints.getWidth();
      if (k == -1) {
        k = localDimension.width;
      }
      int m = localAbsoluteConstraints.getHeight();
      if (m == -1) {
        m = localDimension.height;
      }
      if (localAbsoluteConstraints.x + k > i) {
        i = localAbsoluteConstraints.x + k;
      }
      if (localAbsoluteConstraints.y + m > j) {
        j = localAbsoluteConstraints.y + m;
      }
    }
    return new Dimension(i, j);
  }
  
  public void layoutContainer(Container paramContainer)
  {
    Enumeration localEnumeration = this.constraints.keys();
    while (localEnumeration.hasMoreElements())
    {
      Component localComponent = (Component)localEnumeration.nextElement();
      AbsoluteConstraints localAbsoluteConstraints = (AbsoluteConstraints)this.constraints.get(localComponent);
      Dimension localDimension = localComponent.getPreferredSize();
      int i = localAbsoluteConstraints.getWidth();
      if (i == -1) {
        i = localDimension.width;
      }
      int j = localAbsoluteConstraints.getHeight();
      if (j == -1) {
        j = localDimension.height;
      }
      localComponent.setBounds(localAbsoluteConstraints.x, localAbsoluteConstraints.y, i, j);
    }
  }
  
  public void addLayoutComponent(Component paramComponent, Object paramObject)
  {
    if (!(paramObject instanceof AbsoluteConstraints)) {
      throw new IllegalArgumentException();
    }
    this.constraints.put(paramComponent, paramObject);
  }
  
  public Dimension maximumLayoutSize(Container paramContainer)
  {
    return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
  }
  
  public float getLayoutAlignmentX(Container paramContainer)
  {
    return 0.0F;
  }
  
  public float getLayoutAlignmentY(Container paramContainer)
  {
    return 0.0F;
  }
  
  public void invalidateLayout(Container paramContainer) {}
}
