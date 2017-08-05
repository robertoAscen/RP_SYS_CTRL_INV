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
public class FondoSubFormularios extends JPanel
{
    public void paintComponent(Graphics g)
  {
    Dimension tamanho = getSize();
    ImageIcon imagenFondo = new ImageIcon(new File("data/graficos/SubFormularios.jpg").getAbsolutePath());
    g.drawImage(imagenFondo.getImage(), 0, 0, tamanho.width, tamanho.height, null);
    setOpaque(false);
    super.paintComponents(g);
  }        
}
