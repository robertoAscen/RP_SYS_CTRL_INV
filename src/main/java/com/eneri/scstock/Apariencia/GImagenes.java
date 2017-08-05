/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Apariencia;

import com.eneri.scstock.Gestor.Gestionar;
import com.eneri.scstock.Mainframe.Principal;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author RAscencio
 */
public class GImagenes extends JDialog
{  
    private JPanel contentPane;
  File archivo;
  byte[] bytesImg;
  Gestionar gestion = new Gestionar();
  public static JTextField tfContentPane;
  private JButton btnGuardarImagen;
  private Principal m;
  private JLabel label;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          GImagenes frame = new GImagenes();
          frame.setLocationRelativeTo(null);
          frame.setDefaultCloseOperation(2);
          frame.setModal(true);
          frame.setVisible(true);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }
  
  public GImagenes()
  {
    setResizable(false);
    setIconImage(Toolkit.getDefaultToolkit().getImage(GImagenes.class.getResource("/Iconos/Apariencia.png")));
    setTitle("Elegir imagen");
    setBounds(100, 100, 897, 600);
    this.contentPane = new FondoFormularios();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    this.contentPane.setLayout(null);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(10, 11, 871, 443);
    this.contentPane.add(scrollPane);
    
    this.label = new JLabel("");
    scrollPane.setViewportView(this.label);
    
    this.btnGuardarImagen = new JButton("Guardar imagen");
    this.btnGuardarImagen.setEnabled(false);
    this.btnGuardarImagen.setIcon(new ImageIcon(GImagenes.class.getResource("/Iconos/GuardarImagen.png")));
    this.btnGuardarImagen.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        GImagenes.this.Guardar();
      }
    });
    this.btnGuardarImagen.setBounds(690, 496, 191, 65);
    this.contentPane.add(this.btnGuardarImagen);
    
    JButton btnNewButton_2 = new JButton("Abrir imagen");
    btnNewButton_2.setIcon(new ImageIcon(GImagenes.class.getResource("/Iconos/open.png")));
    btnNewButton_2.setToolTipText("Abre el explorador para elegir una imagen");
    btnNewButton_2.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        GImagenes.this.AbrirArchivo();
      }
    });
    btnNewButton_2.setBounds(504, 496, 176, 65);
    this.contentPane.add(btnNewButton_2);
    
    tfContentPane = new JTextField();
    tfContentPane.setEditable(false);
    tfContentPane.setBounds(785, 541, 96, 20);
    this.contentPane.add(tfContentPane);
    tfContentPane.setColumns(10);
    
    JLabel lblContentPane = new JLabel("Content pane:");
    lblContentPane.setForeground(Color.WHITE);
    lblContentPane.setBounds(704, 541, 83, 20);
    this.contentPane.add(lblContentPane);
    
    JLabel lblNewLabel = new JLabel("Nota: La vista previa puede variar pero el tama�o original se conserva al guardar la imagen.");
    lblNewLabel.setBounds(24, 465, 668, 20);
    this.contentPane.add(lblNewLabel);
    lblNewLabel.setForeground(Color.WHITE);
    lblNewLabel.setFont(new Font("Tahoma", 1, 12));
  }
  
  private void AbrirArchivo()
  {
    JOptionPane.showMessageDialog(null, "Es recomendable elegir una imagen con resoluci�n \nsuperior a 800x600 y que la imagen sea de formato (.jpg)", "Recomendaci�n", 1);
    FileDialog seleccionado = new FileDialog(this, "Abrir archivo", 0);
    seleccionado.show();
    String curFile = seleccionado.getFile();
    String filename = seleccionado.getDirectory() + curFile;
    setCursor(Cursor.getPredefinedCursor(12));
    File f = new File(filename);
    this.archivo = f;
    if (this.archivo.canRead())
    {
      String nombreArchivo = this.archivo.getName();
      if ((nombreArchivo.endsWith("jpg")) || (nombreArchivo.endsWith("gif")) || (nombreArchivo.endsWith("png")))
      {
        this.bytesImg = this.gestion.AbrirImagen(this.archivo);
        
        this.label.setIcon(new ImageIcon(this.bytesImg));
        this.btnGuardarImagen.setEnabled(true);
      }
      else
      {
        JOptionPane.showMessageDialog(null, "El formato debe ser de formato imagen", "Error de apertura", 0);
      }
    }
  }
  
  private void Guardar()
  {
    int result = JOptionPane.showConfirmDialog(null, "�Desea reiniciar el sistema para que los cambios surtan efecto?\n Nota: Si no reinicia el sistema, no se aplicar�n los cambios.", "Pregunta", 0);
    if (result == 0)
    {
      String nombreIMG = tfContentPane.getText();
      if (nombreIMG.equals("Principal")) {
        GuardarIMGPrincipal();
      }
      if (nombreIMG.equals("Formularios")) {
        GuardarIMGFormularios();
      }
      if (nombreIMG.equals("SubFormularios")) {
        GuardarIMGSubFormularios();
      }
      if (nombreIMG.equals("Transacciones")) {
        GuardarIMGTransacciones();
      }
      dispose();
      
      Principal.btnReiniciar.doClick();
    }
  }
  
  private void GuardarIMGPrincipal()
  {
    File f = new File(new File("data/graficos/Principal.jpg").getAbsolutePath());
    this.gestion.GuardarImagen(f, this.bytesImg);
  }
  
  private void GuardarIMGFormularios()
  {
    File f = new File(new File("data/graficos/Formularios.jpg").getAbsolutePath());
    this.gestion.GuardarImagen(f, this.bytesImg);
  }
  
  private void GuardarIMGSubFormularios()
  {
    File f = new File(new File("data/graficos/SubFormularios.jpg").getAbsolutePath());
    this.gestion.GuardarImagen(f, this.bytesImg);
  }
  
  private void GuardarIMGTransacciones()
  {
    File f = new File(new File("data/graficos/Transacciones.jpg").getAbsolutePath());
    this.gestion.GuardarImagen(f, this.bytesImg);
  }
}
