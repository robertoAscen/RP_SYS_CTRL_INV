/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Informaciones;

import com.eneri.scstock.Apariencia.Colores;
import com.eneri.scstock.Apariencia.FondoTransacciones;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author RAscencio
 */
public class MenuInformaciones extends JDialog
{
    
  private final JPanel contentPane = new FondoTransacciones();
  private JButton btnGraficoDeLineas;
  private JLabel lblInformacinEnReportes;
  private JLabel lblInformacionGrficos;
  private JLabel lblMenDeInformaciones;
  private JTextField tfColor;
  
  public static void main(String[] args)
  {
    try
    {
      MenuInformaciones dialog = new MenuInformaciones();
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
  
  public MenuInformaciones()
  {
    setTitle("Men� de informaciones");
    setResizable(false);
    setBounds(100, 100, 796, 436);
    getContentPane().setLayout(new BorderLayout());
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    this.contentPane.setLayout(null);
    
    this.btnGraficoDeLineas = new JButton("Gr�fico de lineas");
    this.btnGraficoDeLineas.setBounds(312, 151, 192, 68);
    this.contentPane.add(this.btnGraficoDeLineas);
    this.btnGraficoDeLineas.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        MenuInformaciones.this.dispose();
        GraficoDeLineas lineas = new GraficoDeLineas();
        GraficoDeLineas.main(null);
      }
    });
    this.btnGraficoDeLineas.setIcon(new ImageIcon(MenuInformaciones.class.getResource("/Iconos/Lineas.png")));
    
    JButton btnBarras = new JButton("Gr�fico de barras");
    btnBarras.setBounds(312, 230, 192, 68);
    this.contentPane.add(btnBarras);
    btnBarras.setIcon(new ImageIcon(MenuInformaciones.class.getResource("/Iconos/Estadisticas.png")));
    
    JButton btnPastel = new JButton("Gr�fico de pastel");
    btnPastel.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        MenuInformaciones.this.dispose();
        GraficoDePastel pastel = new GraficoDePastel();
        GraficoDePastel.main(null);
      }
    });
    btnPastel.setBounds(312, 309, 192, 68);
    this.contentPane.add(btnPastel);
    btnPastel.setIcon(new ImageIcon(MenuInformaciones.class.getResource("/Iconos/Pastel.png")));
    
    JButton btnVentasPorProducto = new JButton("Ventas por producto");
    btnVentasPorProducto.setIcon(new ImageIcon(MenuInformaciones.class.getResource("/Iconos/VentaInfoProducto.png")));
    btnVentasPorProducto.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        MenuInformaciones.this.dispose();
        VentasInfoPorProducto.main(null);
      }
    });
    btnVentasPorProducto.setBounds(557, 230, 209, 68);
    this.contentPane.add(btnVentasPorProducto);
    
    JButton btnVentasPorProducto_1 = new JButton("Ventas del d�a");
    btnVentasPorProducto_1.setIcon(new ImageIcon(MenuInformaciones.class.getResource("/Iconos/VentaDelDia.png")));
    btnVentasPorProducto_1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        MenuInformaciones.this.dispose();
        VentaDetallada.main(null);
      }
    });
    btnVentasPorProducto_1.setBounds(557, 151, 209, 68);
    this.contentPane.add(btnVentasPorProducto_1);
    
    JButton btnMejoresProductos = new JButton("Mejores productos");
    btnMejoresProductos.setIcon(new ImageIcon(MenuInformaciones.class.getResource("/Iconos/MejorProducto.png")));
    btnMejoresProductos.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        MenuInformaciones.this.dispose();
        MejoresProductos.main(null);
      }
    });
    btnMejoresProductos.setBounds(557, 309, 209, 68);
    this.contentPane.add(btnMejoresProductos);
    
    JLabel lblNewLabel = new JLabel("New label");
    lblNewLabel.setIcon(new ImageIcon(MenuInformaciones.class.getResource("/Galeria/documentar.png")));
    lblNewLabel.setBounds(10, 113, 268, 264);
    this.contentPane.add(lblNewLabel);
    
    JTabbedPane tabbedPane = new JTabbedPane(1);
    tabbedPane.setBounds(10, 89, 268, 308);
    this.contentPane.add(tabbedPane);
    
    JTabbedPane tabbedPane_1 = new JTabbedPane(1);
    tabbedPane_1.setBounds(288, 138, 236, 259);
    this.contentPane.add(tabbedPane_1);
    
    JTabbedPane tabbedPane_2 = new JTabbedPane(1);
    tabbedPane_2.setBounds(534, 138, 246, 259);
    this.contentPane.add(tabbedPane_2);
    
    this.lblMenDeInformaciones = new JLabel("Men� de informaciones");
    this.lblMenDeInformaciones.setForeground(Color.WHITE);
    this.lblMenDeInformaciones.setFont(new Font("Tahoma", 0, 25));
    this.lblMenDeInformaciones.setBounds(290, 11, 277, 67);
    this.contentPane.add(this.lblMenDeInformaciones);
    
    JTabbedPane tabbedPane_3 = new JTabbedPane(1);
    tabbedPane_3.setBounds(10, 11, 770, 67);
    this.contentPane.add(tabbedPane_3);
    
    this.lblInformacionGrficos = new JLabel("Informaci�n en gr�ficos");
    this.lblInformacionGrficos.setForeground(Color.WHITE);
    this.lblInformacionGrficos.setFont(new Font("Tahoma", 0, 15));
    this.lblInformacionGrficos.setBounds(325, 89, 179, 45);
    this.contentPane.add(this.lblInformacionGrficos);
    
    this.lblInformacinEnReportes = new JLabel("Informaci�n en reportes");
    this.lblInformacinEnReportes.setForeground(Color.WHITE);
    this.lblInformacinEnReportes.setFont(new Font("Tahoma", 0, 15));
    this.lblInformacinEnReportes.setBounds(572, 89, 208, 45);
    this.contentPane.add(this.lblInformacinEnReportes);
    
    JTabbedPane tabbedPane_4 = new JTabbedPane(1);
    tabbedPane_4.setBounds(288, 89, 236, 51);
    this.contentPane.add(tabbedPane_4);
    
    JTabbedPane tabbedPane_5 = new JTabbedPane(1);
    tabbedPane_5.setBounds(534, 89, 246, 51);
    this.contentPane.add(tabbedPane_5);
    
    this.tfColor = new JTextField();
    this.tfColor.setEditable(false);
    this.tfColor.setVisible(false);
    this.tfColor.setBounds(10, 11, 86, 20);
    this.contentPane.add(this.tfColor);
    this.tfColor.setColumns(10);
    btnBarras.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        MenuInformaciones.this.dispose();
        GraficoDeBarras barras = new GraficoDeBarras();
        GraficoDeBarras.main(null);
      }
    });
    ColorBlanco();
  }
  
  private void CapturarColor()
  {
    String codigocolor = "1";
    Colores color = Colores.ConsutarColorFormularios(codigocolor);
    this.tfColor.setText(color.getColor());
  }
  
  private void DetectarColor()
  {
    if (this.tfColor.getText().equals("Amarillo")) {
      ColorAmarillo();
    }
    if (this.tfColor.getText().equals("Azul")) {
      ColorAzul();
    }
    if (this.tfColor.getText().equals("Cyan")) {
      ColorCyan();
    }
    if (this.tfColor.getText().equals("Gris")) {
      ColorGris();
    }
    if (this.tfColor.getText().equals("Naranja")) {
      ColorNaranja();
    }
    if (this.tfColor.getText().equals("Negro")) {
      ColorNegro();
    }
    if (this.tfColor.getText().equals("P�rpura")) {
      ColorPurpura();
    }
    if (this.tfColor.getText().equals("Rojo")) {
      ColorRojo();
    }
    if (this.tfColor.getText().equals("Marr�n")) {
      ColorMarron();
    }
    if (this.tfColor.getText().equals("Verde fluor")) {
      ColorVerdeFluor();
    }
    if (this.tfColor.getText().equals("Verde")) {
      ColorVerde();
    }
    if (this.tfColor.getText().equals("Blanco")) {
      ColorBlanco();
    }
  }
  
  private void ColorRojo()
  {
    this.lblInformacinEnReportes.setForeground(Color.RED);
    this.lblInformacionGrficos.setForeground(Color.RED);
    this.lblMenDeInformaciones.setForeground(Color.RED);
  }
  
  private void ColorBlanco()
  {
    this.lblInformacinEnReportes.setForeground(Color.WHITE);
    this.lblInformacionGrficos.setForeground(Color.WHITE);
    this.lblMenDeInformaciones.setForeground(Color.WHITE);
  }
  
  private void ColorGris()
  {
    this.lblInformacinEnReportes.setForeground(Color.GRAY);
    this.lblInformacionGrficos.setForeground(Color.GRAY);
    this.lblMenDeInformaciones.setForeground(Color.GRAY);
  }
  
  private void ColorAmarillo()
  {
    this.lblInformacinEnReportes.setForeground(Color.YELLOW);
    this.lblInformacionGrficos.setForeground(Color.YELLOW);
    this.lblMenDeInformaciones.setForeground(Color.YELLOW);
  }
  
  private void ColorVerde()
  {
    this.lblInformacinEnReportes.setForeground(new Color(0, 128, 0));
    this.lblInformacionGrficos.setForeground(new Color(0, 128, 0));
    this.lblMenDeInformaciones.setForeground(new Color(0, 128, 0));
  }
  
  private void ColorAzul()
  {
    this.lblInformacinEnReportes.setForeground(new Color(0, 0, 205));
    this.lblInformacionGrficos.setForeground(new Color(0, 0, 205));
    this.lblMenDeInformaciones.setForeground(new Color(0, 0, 205));
  }
  
  private void ColorMarron()
  {
    this.lblInformacinEnReportes.setForeground(new Color(139, 69, 19));
    this.lblInformacionGrficos.setForeground(new Color(139, 69, 19));
    this.lblMenDeInformaciones.setForeground(new Color(139, 69, 19));
  }
  
  private void ColorPurpura()
  {
    this.lblInformacinEnReportes.setForeground(new Color(148, 0, 211));
    this.lblInformacionGrficos.setForeground(new Color(148, 0, 211));
    this.lblMenDeInformaciones.setForeground(new Color(148, 0, 211));
  }
  
  private void ColorCyan()
  {
    this.lblInformacinEnReportes.setForeground(Color.CYAN);
    this.lblInformacionGrficos.setForeground(Color.CYAN);
    this.lblMenDeInformaciones.setForeground(Color.CYAN);
  }
  
  private void ColorNaranja()
  {
    this.lblInformacinEnReportes.setForeground(Color.ORANGE);
    this.lblInformacionGrficos.setForeground(Color.ORANGE);
    this.lblMenDeInformaciones.setForeground(Color.ORANGE);
  }
  
  private void ColorVerdeFluor()
  {
    this.lblInformacinEnReportes.setForeground(Color.GREEN);
    this.lblInformacionGrficos.setForeground(Color.GREEN);
    this.lblMenDeInformaciones.setForeground(Color.GREEN);
  }
  
  private void ColorNegro()
  {
    this.lblInformacinEnReportes.setForeground(Color.BLACK);
    this.lblInformacionGrficos.setForeground(Color.BLACK);
    this.lblMenDeInformaciones.setForeground(Color.BLACK);
  }
}
