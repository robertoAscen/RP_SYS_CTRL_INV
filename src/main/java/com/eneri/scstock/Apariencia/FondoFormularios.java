/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Apariencia;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author RAscencio
 */
public class FondoFormularios extends JPanel
{

  public void paintComponent(Graphics g)
  {
    Dimension tamanio = getSize();
    
    ImageIcon imagenFondo = new ImageIcon(new File("data/graficos/Formularios.jpg").getAbsolutePath());
    g.drawImage(imagenFondo.getImage(), 0, 0, tamanio.width, tamanio.height, null);
    setOpaque(false);
    super.paintComponents(g);
  }    
}
