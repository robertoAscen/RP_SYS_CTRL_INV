/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Apariencia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author RAscencio
 */
public class FApariencia extends JDialog
{
    private JLabel label_1;
  private JButton btnPrincipal;
  private JButton btnFormularios;
  private JButton btnSubFormularios;
  private JButton btnTransacciones;
  
  public static void main(String[] args)
  {
    try
    {
      FApariencia dialog = new FApariencia();
      dialog.setDefaultCloseOperation(2);
      dialog.setLocationRelativeTo(null);
      dialog.setModal(true);
      dialog.setVisible(true);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public FApariencia()
  {
    setBounds(100, 100, 450, 300);
    setIconImage(Toolkit.getDefaultToolkit().getImage(FApariencia.class.getResource("/Iconos/Apariencia.png")));
    setTitle("Apariencia");
    setResizable(false);
    setBounds(100, 100, 413, 577);
    
    FondoFormularios contentPane = new FondoFormularios();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel label = new JLabel("Personalizaci�n del sistema");
    label.setForeground(Color.WHITE);
    label.setFont(new Font("Tahoma", 0, 20));
    label.setBounds(80, 11, 282, 40);
    contentPane.add(label);
    
    this.btnPrincipal = new JButton("Principal");
    this.btnPrincipal.setIcon(new ImageIcon(FApariencia.class.getResource("/Iconos/btn_skin_s.PNG")));
    this.btnPrincipal.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FApariencia.this.dispose();
        FApariencia.this.Principal();
      }
    });
    this.btnPrincipal.setBounds(220, 114, 149, 138);
    contentPane.add(this.btnPrincipal);
    
    this.label_1 = new JLabel("");
    this.label_1.setIcon(new ImageIcon(FApariencia.class.getResource("/Galeria/Control Panel.png")));
    this.label_1.setBounds(92, 301, 270, 190);
    contentPane.add(this.label_1);
    
    JTabbedPane tabbedPane_1 = new JTabbedPane(1);
    tabbedPane_1.setBounds(10, 280, 384, 233);
    contentPane.add(tabbedPane_1);
    
    this.btnFormularios = new JButton("Formularios");
    this.btnFormularios.setIcon(new ImageIcon(FApariencia.class.getResource("/Iconos/imagen.PNG")));
    this.btnFormularios.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FApariencia.this.dispose();
        FApariencia.this.Formularios();
      }
    });
    this.btnFormularios.setBounds(36, 114, 157, 32);
    contentPane.add(this.btnFormularios);
    
    this.btnSubFormularios = new JButton("Sub-Formularios");
    this.btnSubFormularios.setIcon(new ImageIcon(FApariencia.class.getResource("/Iconos/imagen.PNG")));
    this.btnSubFormularios.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FApariencia.this.dispose();
        FApariencia.this.SubFormularios();
      }
    });
    this.btnSubFormularios.setBounds(36, 168, 157, 33);
    contentPane.add(this.btnSubFormularios);
    
    this.btnTransacciones = new JButton("Transacciones");
    this.btnTransacciones.setIcon(new ImageIcon(FApariencia.class.getResource("/Iconos/imagen.PNG")));
    this.btnTransacciones.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FApariencia.this.dispose();
        FApariencia.this.Transacciones();
      }
    });
    this.btnTransacciones.setBounds(36, 219, 157, 33);
    contentPane.add(this.btnTransacciones);
    
    JLabel label_2 = new JLabel("Fondos");
    label_2.setForeground(Color.WHITE);
    label_2.setBounds(171, 54, 132, 33);
    contentPane.add(label_2);
    
    JTabbedPane tabbedPane = new JTabbedPane(1);
    tabbedPane.setBounds(10, 54, 384, 215);
    contentPane.add(tabbedPane);
    
    JTabbedPane tabbedPane_2 = new JTabbedPane(1);
    tabbedPane_2.setBounds(10, 11, 384, 41);
    contentPane.add(tabbedPane_2);
    
    JTabbedPane tabbedPane_3 = new JTabbedPane(1);
    tabbedPane_3.setBounds(10, 54, 384, 32);
    contentPane.add(tabbedPane_3);
  }
  
  private void Principal()
  {
    GImagenes gestion = new GImagenes();
    GImagenes.tfContentPane.setText("Principal");
    gestion.setLocationRelativeTo(null);
    
    gestion.setVisible(true);
  }
  
  private void Transacciones()
  {
    GImagenes gestion = new GImagenes();
    GImagenes.tfContentPane.setText("Transacciones");
    gestion.setLocationRelativeTo(null);
    
    gestion.setVisible(true);
  }
  
  private void SubFormularios()
  {
    GImagenes gestion = new GImagenes();
    GImagenes.tfContentPane.setText("SubFormularios");
    gestion.setLocationRelativeTo(null);
    
    gestion.setVisible(true);
  }
  
  private void Formularios()
  {
    GImagenes gestion = new GImagenes();
    GImagenes.tfContentPane.setText("Formularios");
    gestion.setLocationRelativeTo(null);
    
    gestion.setVisible(true);
  }
}
