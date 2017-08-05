/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Herramientas;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.GeneralPath;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.Timer;

/**
 *
 * @author RAscencio
 */
public class JFlip extends JComponent implements MouseListener
{
    
  private Timer timer = null;
  private boolean inTransition = false;
  private int speed = 4;
  private int displacement = 2;
  private boolean isFront = true;
  private boolean inward = true;
  private Icon iconBack = new ImageIcon(new File("data/graficos/Principal1.jpg").getAbsolutePath());
  private Icon iconFront = new ImageIcon(new File("data/graficos/Principal2.jpg").getAbsolutePath());
  private Image image = ((ImageIcon)this.iconFront).getImage();
  private int displacementHorizontal = 0;
  private float displacementLeft = 0.0F;
  private float displacementRight = 0.0F;
  private Dimension dimension = new Dimension(160, 160);
  
  public JFlip()
  {
    setSize(this.dimension);
    setPreferredSize(this.dimension);
    setVisible(true);
    addMouseListener(this);
  }
  
  public void paintComponent(Graphics g)
  {
    Graphics2D g2 = (Graphics2D)g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setStroke(new BasicStroke(0.0F));
    
    GeneralPath p = new GeneralPath();
    p.moveTo(this.displacementHorizontal, this.displacementLeft);
    p.lineTo(getWidth() - this.displacementHorizontal - 1, this.displacementRight);
    p.lineTo(getWidth() - this.displacementHorizontal - 1, getHeight() - this.displacementRight - 1.0F);
    p.lineTo(this.displacementHorizontal, getHeight() - this.displacementLeft - 1.0F);
    p.closePath();
    Shape shp = p;
    g2.setClip(shp);
    g2.drawImage(this.image, 0, 0, getWidth() - this.displacementHorizontal, getHeight(), null);
    g2.setClip(null);
    g2.draw(shp);
    g.dispose();
  }
  
  private void flipAnimate()
  {
    this.inTransition = (!this.inTransition);
    
    ActionListener animation = new ActionListener()
    {
      public void actionPerformed(ActionEvent ae)
      {
        if (JFlip.this.inward)
        {
          JFlip.this.displacementHorizontal += JFlip.this.displacement; JFlip 
            tmp33_30 = JFlip.this;tmp33_30.displacementRight = ((float)(tmp33_30.displacementRight + (JFlip.this.displacementRight >= JFlip.this.getHeight() / 2 ? 0.0D : JFlip.this.displacement / (JFlip.this.getSize().getWidth() / JFlip.this.getSize().getHeight() + 1.0D))));
        }
        else
        {
          JFlip.this.displacementHorizontal -= JFlip.this.displacement; JFlip 
            tmp126_123 = JFlip.this;tmp126_123.displacementLeft = ((float)(tmp126_123.displacementLeft - JFlip.this.displacement / (JFlip.this.getSize().getWidth() / JFlip.this.getSize().getHeight() + 1.0D)));
        }
        JFlip.this.repaint();
        if (JFlip.this.displacementHorizontal >= JFlip.this.getWidth() / 2)
        {
          JFlip.this.inward = false;
          JFlip.this.displacementLeft = JFlip.this.displacementRight;
          JFlip.this.displacementRight = 0.0F;
          if (JFlip.this.isFront)
          {
            JFlip.this.isFront = false;
            JFlip.this.image = ((ImageIcon)JFlip.this.iconBack).getImage();
          }
          else
          {
            JFlip.this.isFront = true;
            JFlip.this.image = ((ImageIcon)JFlip.this.iconFront).getImage();
          }
        }
        if ((!JFlip.this.inward) && (JFlip.this.displacementHorizontal == 0))
        {
          JFlip.this.inTransition = false;
          JFlip.this.timer.stop();
          JFlip.this.displacementLeft = 0.0F;
          JFlip.this.displacementRight = 0.0F;
          JFlip.this.displacementHorizontal = 0;
          JFlip.this.inward = true;
        }
      }
    };
    if (this.inTransition)
    {
      if (this.timer != null) {
        this.timer.stop();
      }
      this.timer = new Timer(this.speed, animation);
      this.timer.start();
    }
  }
  
  public void mouseClicked(MouseEvent e)
  {
    flipAnimate();
  }
  
  public void mousePressed(MouseEvent e) {}
  
  public void mouseReleased(MouseEvent e) {}
  
  public void mouseEntered(MouseEvent e) {}
  
  public void mouseExited(MouseEvent e) {}
  
  public Icon getIconBack()
  {
    return this.iconBack;
  }
  
  public void setIconBack(Icon iconBack)
  {
    this.iconBack = iconBack;
  }
  
  public Icon getIconFront()
  {
    return this.iconFront;
  }
  
  public void setIconFront(Icon iconFront)
  {
    this.iconFront = iconFront;
  }
  
  public int getDisplacement()
  {
    return this.displacement;
  }
  
  public void setDisplacement(int displacement)
  {
    this.displacement = displacement;
  }
  
  public int getSpeed()
  {
    return this.speed;
  }
  
  public void setSpeed(int speed)
  {
    this.speed = speed;
  }    
}
