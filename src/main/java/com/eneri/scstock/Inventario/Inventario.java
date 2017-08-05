/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Inventario;

import com.eneri.scstock.Apariencia.FondoFormularios;
import com.eneri.scstock.Herramientas.Validaciones;
import com.eneri.scstock.Modelos.ModeloInventarios;
import com.eneri.scstock.Modelos.ModeloProductos;
import com.eneri.scstock.Objetos.DaoInventarios;
import com.eneri.scstock.Objetos.DaoProductos;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author RAscencio
 */
public class Inventario extends JDialog
{
    
  private JLabel lblCondicion;
  private static JTextField tfCodProducto;
  private JLabel lblCodProd;
  public static List<ModeloProductos> lista;
  public static DefaultTableModel modelo = new DefaultTableModel(null, new String[] { "C�d. del producto", "Descripci�n", "U.M.", "Inventario", "Stock act.", "Estado", "Fa./So.", "Costo" })
  {
    boolean[] columnEditables = new boolean[8];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private static JTable table;
  private JTabbedPane tabbedPane;
  private JTabbedPane tabbedPane_1;
  private JLabel label;
  private JLabel lblDescripcionProd;
  private JLabel lblEmoji;
  private JTextField tfAumentar;
  private JTextField tfDisminuir;
  private JButton btnAjustar;
  private JButton btnImprimir;
  private JTabbedPane tabbedPane_3;
  private JButton btnConsultar;
  public static List<ModeloInventarios> listaInventarios;
  private JButton btnGuardar;
  private ModeloInventarios inventario;
  private JButton btnFinalizar;
  
  public static void main(String[] args)
  {
    try
    {
      Inventario dialog = new Inventario();
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
  
  public Inventario()
  {
    setTitle("Inventarios");
    setResizable(false);
    setBounds(100, 100, 1189, 643);
    setIconImage(Toolkit.getDefaultToolkit().getImage(Inventario.class.getResource("/Iconos/servicios_auditoria_icono - copia.png")));
    FondoFormularios contentPane = new FondoFormularios();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    this.label = new JLabel("");
    this.label.setIcon(new ImageIcon(Inventario.class.getResource("/Galeria/InventarioIcon.png")));
    this.label.setBounds(35, 380, 192, 188);
    contentPane.add(this.label);
    
    this.lblCondicion = new JLabel("Inventario");
    this.lblCondicion.setForeground(Color.WHITE);
    this.lblCondicion.setFont(new Font("Tahoma", 0, 35));
    this.lblCondicion.setBounds(514, 5, 471, 43);
    contentPane.add(this.lblCondicion);
    
    this.lblCodProd = new JLabel("C�d. del producto:");
    this.lblCodProd.setForeground(Color.WHITE);
    this.lblCodProd.setFont(new Font("Tahoma", 0, 14));
    this.lblCodProd.setBounds(20, 59, 131, 30);
    contentPane.add(this.lblCodProd);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setAlignmentX(1.0F);
    scrollPane.setBounds(259, 62, 914, 442);
    contentPane.add(scrollPane);
    
    table = new JTable();
    table.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        Inventario.this.ConsultarDetallesTabla();
      }
    });
    table.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        Inventario.this.ConsultarDetallesTabla();
      }
    });
    table.setForeground(SystemColor.text);
    table.setBackground(SystemColor.inactiveCaptionText);
    table.setSelectionBackground(Color.WHITE);
    table.setSelectionForeground(Color.BLACK);
    table.getTableHeader().setReorderingAllowed(false);
    table.setModel(modelo);
    table.getColumnModel().getColumn(0).setPreferredWidth(108);
    table.getColumnModel().getColumn(1).setPreferredWidth(350);
    table.getColumnModel().getColumn(2).setPreferredWidth(40);
    table.getColumnModel().getColumn(3).setPreferredWidth(40);
    table.getColumnModel().getColumn(4).setPreferredWidth(40);
    table.getColumnModel().getColumn(5).setPreferredWidth(40);
    table.getColumnModel().getColumn(6).setPreferredWidth(40);
    scrollPane.setViewportView(table);
    
    this.tabbedPane = new JTabbedPane(1);
    this.tabbedPane.setBounds(259, 5, 914, 49);
    contentPane.add(this.tabbedPane);
    
    this.tabbedPane_1 = new JTabbedPane(1);
    this.tabbedPane_1.setBounds(10, 370, 245, 219);
    contentPane.add(this.tabbedPane_1);
    
    tfCodProducto = new JTextField();
    Validaciones validar = new Validaciones();
    validar.BloqueoAlfabetico(tfCodProducto);
    tfCodProducto.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          if (Inventario.tfCodProducto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "�Ingrese el c�digo del producto!", "Aviso", 2);
          } else {
            Inventario.this.AgregaraLaLista();
          }
        }
      }
    });
    tfCodProducto.setBounds(137, 66, 118, 20);
    contentPane.add(tfCodProducto);
    tfCodProducto.setColumns(10);
    
    this.lblDescripcionProd = new JLabel("");
    this.lblDescripcionProd.setForeground(Color.WHITE);
    this.lblDescripcionProd.setFont(new Font("Tahoma", 0, 13));
    this.lblDescripcionProd.setBounds(10, 585, 1173, 30);
    contentPane.add(this.lblDescripcionProd);
    
    this.lblEmoji = new JLabel("");
    this.lblEmoji.setBounds(35, 162, 207, 197);
    contentPane.add(this.lblEmoji);
    
    JLabel lblAumentar = new JLabel("Aumentar:");
    lblAumentar.setForeground(Color.WHITE);
    lblAumentar.setFont(new Font("Tahoma", 0, 14));
    lblAumentar.setBounds(16, 92, 80, 30);
    contentPane.add(lblAumentar);
    
    this.tfAumentar = new JTextField();
    validar.BloqueoAlfabetico(this.tfAumentar);
    this.tfAumentar.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (Inventario.this.tfAumentar.getText().trim().length() > 0) {
          Inventario.this.tfDisminuir.setEnabled(false);
        } else {
          Inventario.this.tfDisminuir.setEnabled(true);
        }
      }
    });
    this.tfAumentar.setColumns(10);
    this.tfAumentar.setBounds(90, 99, 65, 20);
    contentPane.add(this.tfAumentar);
    
    this.tfDisminuir = new JTextField();
    validar.BloqueoAlfabetico(this.tfDisminuir);
    this.tfDisminuir.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (Inventario.this.tfDisminuir.getText().trim().length() > 0) {
          Inventario.this.tfAumentar.setEnabled(false);
        } else {
          Inventario.this.tfAumentar.setEnabled(true);
        }
      }
    });
    this.tfDisminuir.setColumns(10);
    this.tfDisminuir.setBounds(90, 122, 65, 20);
    contentPane.add(this.tfDisminuir);
    
    JLabel lblDisminuir = new JLabel("Disminuir:");
    lblDisminuir.setForeground(Color.WHITE);
    lblDisminuir.setFont(new Font("Tahoma", 0, 14));
    lblDisminuir.setBounds(16, 115, 80, 30);
    contentPane.add(lblDisminuir);
    
    this.btnAjustar = new JButton("Ajustar");
    this.btnAjustar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (Inventario.this.ValidarAjuste()) {
          Inventario.this.AjustarInventario();
        }
      }
    });
    this.btnAjustar.setBounds(162, 99, 93, 43);
    contentPane.add(this.btnAjustar);
    
    JTabbedPane tabbedPane_2 = new JTabbedPane(1);
    tabbedPane_2.setBounds(10, 150, 245, 211);
    contentPane.add(tabbedPane_2);
    
    this.btnImprimir = new JButton("IMPRIMIR");
    this.btnImprimir.setIcon(new ImageIcon(Inventario.class.getResource("/Iconos/imprimirIcon.png")));
    this.btnImprimir.setBounds(834, 519, 158, 63);
    contentPane.add(this.btnImprimir);
    
    this.btnConsultar = new JButton("CONSULTAR");
    this.btnConsultar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Inventario.this.CargarInventarioPendienteEnLaTabla();
      }
    });
    this.btnConsultar.setIcon(new ImageIcon(Inventario.class.getResource("/Iconos/procesar.png")));
    this.btnConsultar.setBounds(998, 518, 158, 63);
    contentPane.add(this.btnConsultar);
    
    this.btnGuardar = new JButton("GUARDAR");
    this.btnGuardar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Inventario.this.GuardarInventario();
      }
    });
    this.btnGuardar.setCursor(Cursor.getPredefinedCursor(12));
    this.btnGuardar.setIcon(new ImageIcon(Inventario.class.getResource("/Iconos/Guardar.png")));
    this.btnGuardar.setBounds(269, 515, 158, 66);
    contentPane.add(this.btnGuardar);
    
    this.btnFinalizar = new JButton("FINALIZAR");
    this.btnFinalizar.setBounds(432, 515, 158, 66);
    contentPane.add(this.btnFinalizar);
    
    this.tabbedPane_3 = new JTabbedPane(1);
    this.tabbedPane_3.setBounds(259, 510, 914, 79);
    contentPane.add(this.tabbedPane_3);
  }
  
  private void AjustarInventario()
  {
    int filaseleccionada = table.getSelectedRow();
    
    double aumentar = 0.0D;
    double total = 0.0D;
    double inventariotabla = 0.0D;
    double disminuir = 0.0D;
    
    String totalSetTable = "";
    String estado = "";
    String faSo = "";
    if (filaseleccionada < 0)
    {
      JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila, seleccione una para realizar la operaci�n de ajuste", "Error", 0);
      this.tfAumentar.setText("");
      this.tfDisminuir.setText("");
    }
    else
    {
      String codigoParaBuscProd = String.valueOf(table.getValueAt(filaseleccionada, 0));
      ModeloProductos producto = DaoProductos.ConsutaParaModificar(codigoParaBuscProd);
      inventariotabla = Double.parseDouble(String.valueOf(table.getValueAt(filaseleccionada, 3)));
      if (this.tfAumentar.isEnabled())
      {
        aumentar = Integer.parseInt(this.tfAumentar.getText());
        total = aumentar + inventariotabla;
        
        totalSetTable = String.valueOf(total);
        table.setValueAt(totalSetTable, filaseleccionada, 3);
        
        double cantInventario = Double.parseDouble(String.valueOf(table.getValueAt(filaseleccionada, 3)));
        double resultFaSo = 0.0D;
        
        table.setValueAt(Double.valueOf(cantInventario), filaseleccionada, 3);
        if (cantInventario == producto.getStock())
        {
          estado = "OK";
          this.lblDescripcionProd.setText("Infomaci�n adicional>|::::::Descripci�n=" + producto.getDescripcion() + 
            "|-|" + "::::::Costo=" + producto.getPrecioVenta() + "|-|" + "::::::Fac. sin stock=" + producto.getFacturar() + 
            "|-|" + "::::::Proveedor=" + producto.getCodProveedor() + "<" + "Estado inventario: " + estado);
          this.lblDescripcionProd.setForeground(Color.GREEN);
          faSo = String.valueOf(0.0D);
          table.setValueAt(estado, filaseleccionada, 5);
          this.lblEmoji.setIcon(new ImageIcon(Inventario.class.getResource("/Galeria/IconOk.png")));
        }
        if (cantInventario > producto.getStock())
        {
          estado = "SOBRA";
          table.setValueAt(estado, filaseleccionada, 5);
          this.lblDescripcionProd.setText("Infomaci�n adicional>|::::::Descripci�n=" + producto.getDescripcion() + 
            "|-|" + "::::::Costo=" + producto.getPrecioVenta() + "|-|" + "::::::Fac. sin stock=" + producto.getFacturar() + 
            "|-|" + "::::::Proveedor=" + producto.getCodProveedor() + "<" + "Estado inventario: " + estado);
          this.lblDescripcionProd.setForeground(Color.WHITE);
          resultFaSo = cantInventario - producto.getStock();
          faSo = String.valueOf(resultFaSo);
          this.lblEmoji.setIcon(new ImageIcon(Inventario.class.getResource("/Galeria/IconSobra.png")));
        }
        if (cantInventario < producto.getStock())
        {
          estado = "FALTA";
          table.setValueAt(estado, filaseleccionada, 5);
          this.lblDescripcionProd.setText("Infomaci�n adicional>|::::::Descripci�n=" + producto.getDescripcion() + 
            "|-|" + "::::::Costo=" + producto.getPrecioVenta() + "|-|" + "::::::Fac. sin stock=" + producto.getFacturar() + 
            "|-|" + "::::::Proveedor=" + producto.getCodProveedor() + "<" + "Estado inventario: " + estado);
          this.lblDescripcionProd.setForeground(Color.RED);
          resultFaSo = producto.getStock() - cantInventario;
          faSo = String.valueOf(resultFaSo);
          this.lblEmoji.setIcon(new ImageIcon(Inventario.class.getResource("/Galeria/IconFalta.png")));
        }
        table.setValueAt(faSo, filaseleccionada, 6);
        
        this.tfAumentar.setText("");
        this.tfAumentar.requestFocus();
      }
      if (this.tfDisminuir.isEnabled())
      {
        disminuir = Integer.parseInt(this.tfDisminuir.getText());
        total = inventariotabla - disminuir;
        
        totalSetTable = String.valueOf(total);
        table.setValueAt(totalSetTable, filaseleccionada, 3);
        
        double cantInventario = Double.parseDouble(String.valueOf(table.getValueAt(filaseleccionada, 3)));
        double resultFaSo = 0.0D;
        
        table.setValueAt(Double.valueOf(cantInventario), filaseleccionada, 3);
        if (cantInventario == producto.getStock())
        {
          estado = "OK";
          this.lblDescripcionProd.setText("Infomaci�n adicional>|::::::Descripci�n=" + producto.getDescripcion() + 
            "|-|" + "::::::Costo=" + producto.getPrecioVenta() + "|-|" + "::::::Fac. sin stock=" + producto.getFacturar() + 
            "|-|" + "::::::Proveedor=" + producto.getCodProveedor() + "<" + "Estado inventario: " + estado);
          this.lblDescripcionProd.setForeground(Color.GREEN);
          faSo = String.valueOf(0.0D);
          table.setValueAt(estado, filaseleccionada, 5);
          this.lblEmoji.setIcon(new ImageIcon(Inventario.class.getResource("/Galeria/IconOk.png")));
        }
        if (cantInventario > producto.getStock())
        {
          estado = "SOBRA";
          table.setValueAt(estado, filaseleccionada, 5);
          this.lblDescripcionProd.setText("Infomaci�n adicional>|::::::Descripci�n=" + producto.getDescripcion() + 
            "|-|" + "::::::Costo=" + producto.getPrecioVenta() + "|-|" + "::::::Fac. sin stock=" + producto.getFacturar() + 
            "|-|" + "::::::Proveedor=" + producto.getCodProveedor() + "<" + "Estado inventario: " + estado);
          this.lblDescripcionProd.setForeground(Color.WHITE);
          resultFaSo = cantInventario - producto.getStock();
          faSo = String.valueOf(resultFaSo);
          this.lblEmoji.setIcon(new ImageIcon(Inventario.class.getResource("/Galeria/IconSobra.png")));
        }
        if (cantInventario < producto.getStock())
        {
          estado = "FALTA";
          table.setValueAt(estado, filaseleccionada, 5);
          this.lblDescripcionProd.setText("Infomaci�n adicional>|::::::Descripci�n=" + producto.getDescripcion() + 
            "|-|" + "::::::Costo=" + producto.getPrecioVenta() + "|-|" + "::::::Fac. sin stock=" + producto.getFacturar() + 
            "|-|" + "::::::Proveedor=" + producto.getCodProveedor() + "<" + "Estado inventario: " + estado);
          this.lblDescripcionProd.setForeground(Color.RED);
          resultFaSo = producto.getStock() - cantInventario;
          faSo = String.valueOf(resultFaSo);
          this.lblEmoji.setIcon(new ImageIcon(Inventario.class.getResource("/Galeria/IconFalta.png")));
        }
        table.setValueAt(faSo, filaseleccionada, 6);
        
        this.tfDisminuir.setText("");
        this.tfDisminuir.requestFocus();
      }
    }
    this.tfDisminuir.setEnabled(true);
    this.tfAumentar.setEnabled(true);
  }
  
  private void AgregaraLaLista()
  {
    String codigoParaBuscProd = tfCodProducto.getText();
    
    ModeloProductos producto = DaoProductos.ConsutaParaModificar(codigoParaBuscProd);
    if (producto == null)
    {
      JOptionPane.showMessageDialog(null, "�ste producto no se encuentra registrado en la base de datos", "Error", 0);
      JOptionPane.showMessageDialog(null, "Por favor, verifique si el c�digo que ha ingresado es correcto y vuelva a intentar", "Aviso", 2);
      tfCodProducto.requestFocus();
      tfCodProducto.selectAll();
    }
    else
    {
      String codigo = codigoParaBuscProd;
      String descripcion = producto.getDescripcion();
      String unidadMedida = producto.getUnidadDeMedida();
      String inventario = "1";
      String stockActual = String.valueOf(producto.getStock());
      String estado = "";
      String faSo = "";
      
      int cantidadInventario = 1;
      int resultFaSo = 0;
      double stockProdBD = Double.parseDouble(String.valueOf(producto.getStock()));
      if (cantidadInventario == producto.getStock())
      {
        estado = "OK";
        this.lblDescripcionProd.setText("Infomaci�n adicional>|::::::Descripci�n=" + descripcion + 
          "|-|" + "::::::Costo=" + producto.getPrecioVenta() + "|-|" + "::::::Fac. sin stock=" + producto.getFacturar() + 
          "|-|" + "::::::Proveedor=" + producto.getCodProveedor() + "<" + "Estado inventario: " + estado);
        this.lblDescripcionProd.setForeground(Color.GREEN);
        
        faSo = String.valueOf(0);
        
        this.lblEmoji.setIcon(new ImageIcon(Inventario.class.getResource("/Galeria/IconOk.png")));
      }
      if (cantidadInventario > producto.getStock())
      {
        estado = "SOBRA";
        
        this.lblDescripcionProd.setText("Infomaci�n adicional>|::::::Descripci�n=" + descripcion + 
          "|-|" + "::::::Costo=" + producto.getPrecioVenta() + "|-|" + "::::::Fac. sin stock=" + producto.getFacturar() + 
          "|-|" + "::::::Proveedor=" + producto.getCodProveedor() + "<" + "Estado inventario: " + estado);
        this.lblDescripcionProd.setForeground(Color.WHITE);
        resultFaSo = (int)(cantidadInventario - stockProdBD);
        faSo = String.valueOf(resultFaSo);
        this.lblEmoji.setIcon(new ImageIcon(Inventario.class.getResource("/Galeria/IconSobra.png")));
      }
      if (cantidadInventario < producto.getStock())
      {
        estado = "FALTA";
        this.lblDescripcionProd.setText("Infomaci�n adicional>|::::::Descripci�n=" + descripcion + 
          "|-|" + "::::::Costo=" + producto.getPrecioVenta() + "|-|" + "::::::Fac. sin stock=" + producto.getFacturar() + 
          "|-|" + "::::::Proveedor=" + producto.getCodProveedor() + "<" + "Estado inventario: " + estado);
        this.lblDescripcionProd.setForeground(Color.RED);
        resultFaSo = (int)(stockProdBD - cantidadInventario);
        faSo = String.valueOf(resultFaSo);
        this.lblEmoji.setIcon(new ImageIcon(Inventario.class.getResource("/Galeria/IconFalta.png")));
      }
      if (codigoParaBuscProd.equals(VerificarCodigo()))
      {
        table.changeSelection(PosicionFila().intValue(), 0, false, false);
        
        double cantInventario = Double.parseDouble(String.valueOf(table.getValueAt(PosicionFila().intValue(), 3)));
        cantInventario += 1.0D;
        
        table.setValueAt(Double.valueOf(cantInventario), PosicionFila().intValue(), 3);
        if (cantInventario == producto.getStock())
        {
          estado = "OK";
          this.lblDescripcionProd.setText("Infomaci�n adicional>|::::::Descripci�n=" + descripcion + 
            "|-|" + "::::::Costo=" + producto.getPrecioVenta() + "|-|" + "::::::Fac. sin stock=" + producto.getFacturar() + 
            "|-|" + "::::::Proveedor=" + producto.getCodProveedor() + "<" + "Estado inventario: " + estado);
          this.lblDescripcionProd.setForeground(Color.GREEN);
          faSo = String.valueOf(0);
          table.setValueAt(estado, PosicionFila().intValue(), 5);
          this.lblEmoji.setIcon(new ImageIcon(Inventario.class.getResource("/Galeria/IconOk.png")));
        }
        if (cantInventario > producto.getStock())
        {
          estado = "SOBRA";
          table.setValueAt(estado, PosicionFila().intValue(), 5);
          this.lblDescripcionProd.setText("Infomaci�n adicional>|::::::Descripci�n=" + descripcion + 
            "|-|" + "::::::Costo=" + producto.getPrecioVenta() + "|-|" + "::::::Fac. sin stock=" + producto.getFacturar() + 
            "|-|" + "::::::Proveedor=" + producto.getCodProveedor() + "<" + "Estado inventario: " + estado);
          this.lblDescripcionProd.setForeground(Color.WHITE);
          resultFaSo = (int)(cantInventario - stockProdBD);
          faSo = String.valueOf(resultFaSo);
          this.lblEmoji.setIcon(new ImageIcon(Inventario.class.getResource("/Galeria/IconSobra.png")));
        }
        if (cantInventario < producto.getStock())
        {
          estado = "FALTA";
          table.setValueAt(estado, PosicionFila().intValue(), 5);
          this.lblDescripcionProd.setText("Infomaci�n adicional>|::::::Descripci�n=" + descripcion + 
            "|-|" + "::::::Costo=" + producto.getPrecioVenta() + "|-|" + "::::::Fac. sin stock=" + producto.getFacturar() + 
            "|-|" + "::::::Proveedor=" + producto.getCodProveedor() + "<" + "Estado inventario: " + estado);
          this.lblDescripcionProd.setForeground(Color.RED);
          resultFaSo = (int)(stockProdBD - cantInventario);
          faSo = String.valueOf(resultFaSo);
          this.lblEmoji.setIcon(new ImageIcon(Inventario.class.getResource("/Galeria/IconFalta.png")));
        }
        table.setValueAt(faSo, PosicionFila().intValue(), 6);
      }
      else
      {
        this.lblDescripcionProd.setText("Infomaci�n adicional>|::::::Descripci�n=" + descripcion + 
          "|-|" + "::::::Costo=" + producto.getPrecioVenta() + "|-|" + "::::::Fac. sin stock=" + producto.getFacturar() + 
          "|-|" + "::::::Proveedor=" + producto.getCodProveedor() + "<" + "Estado inventario: " + estado);
        String costo = String.valueOf(producto.getPrecioVenta());
        String[] fila = { codigo, descripcion, unidadMedida, inventario, stockActual, estado, faSo, costo };
        modelo.addRow(fila);
      }
      tfCodProducto.setText("");
    }
  }
  
  public static Integer StockInventario()
  {
    int filaTotal = table.getRowCount();
    int retorno = 0;
    int cantidad = 0;
    String codProd = tfCodProducto.getText();
    while (filaTotal > 0)
    {
      filaTotal--;
      String codProdTabla = String.valueOf(table.getValueAt(filaTotal, 0));
      if (codProd.equals(codProdTabla)) {
        cantidad = Integer.parseInt(String.valueOf(table.getValueAt(filaTotal, 3)));
      }
      retorno = cantidad;
    }
    return Integer.valueOf(retorno);
  }
  
  private String VerificarCodigo()
  {
    int filaTotal = table.getRowCount();
    String codProd = tfCodProducto.getText();
    String retorno = "";
    String codigo = "";
    while (filaTotal > 0)
    {
      filaTotal--;
      String codProdTabla = String.valueOf(table.getValueAt(filaTotal, 0));
      if (codProd.equals(codProdTabla)) {
        codigo = String.valueOf(table.getValueAt(filaTotal, 0));
      }
      retorno = codigo;
    }
    return retorno;
  }
  
  private Integer PosicionFila()
  {
    int filaTotal = table.getRowCount();
    String codProd = tfCodProducto.getText();
    int retorno = 0;
    while (filaTotal > 0)
    {
      filaTotal--;
      String codProdTabla = String.valueOf(table.getValueAt(filaTotal, 0));
      if (codProd.equals(codProdTabla)) {
        retorno = filaTotal;
      }
    }
    return Integer.valueOf(retorno);
  }
  
  private void ConsultarDetallesTabla()
  {
    int filaseleccionada = table.getSelectedRow();
    String codigoParaBuscProd = String.valueOf(table.getValueAt(filaseleccionada, 0));
    
    ModeloProductos producto = DaoProductos.ConsutaParaModificar(codigoParaBuscProd);
    
    String descripcion = producto.getDescripcion();
    String unidadMedida = producto.getUnidadDeMedida();
    String inventario = "1";
    String stockActual = String.valueOf(producto.getStock());
    String estado = "";
    String faSo = "";
    
    double cantidadInventario = Double.parseDouble(String.valueOf(table.getValueAt(filaseleccionada, 3)));
    if (cantidadInventario == producto.getStock())
    {
      estado = "OK";
      this.lblDescripcionProd.setText("Infomaci�n adicional>|::::::Descripci�n=" + descripcion + 
        "|-|" + "::::::Costo=" + producto.getPrecioVenta() + "|-|" + "::::::Fac. sin stock=" + producto.getFacturar() + 
        "|-|" + "::::::Proveedor=" + producto.getCodProveedor() + "<" + "Estado inventario: " + estado);
      this.lblDescripcionProd.setForeground(Color.GREEN);
      this.lblEmoji.setIcon(new ImageIcon(Inventario.class.getResource("/Galeria/IconOk.png")));
    }
    if (cantidadInventario > producto.getStock())
    {
      estado = "SOBRA";
      this.lblDescripcionProd.setText("Infomaci�n adicional>|::::::Descripci�n=" + descripcion + 
        "|-|" + "::::::Costo=" + producto.getPrecioVenta() + "|-|" + "::::::Fac. sin stock=" + producto.getFacturar() + 
        "|-|" + "::::::Proveedor=" + producto.getCodProveedor() + "<" + "Estado inventario: " + estado);
      this.lblDescripcionProd.setForeground(Color.WHITE);
      this.lblEmoji.setIcon(new ImageIcon(Inventario.class.getResource("/Galeria/IconSobra.png")));
    }
    if (cantidadInventario < producto.getStock())
    {
      estado = "FALTA";
      this.lblDescripcionProd.setText("Infomaci�n adicional>|::::::Descripci�n=" + descripcion + 
        "|-|" + "::::::Costo=" + producto.getPrecioVenta() + "|-|" + "::::::Fac. sin stock=" + producto.getFacturar() + 
        "|-|" + "::::::Proveedor=" + producto.getCodProveedor() + "<" + "Estado inventario: " + estado);
      this.lblDescripcionProd.setForeground(Color.RED);
      this.lblEmoji.setIcon(new ImageIcon(Inventario.class.getResource("/Galeria/IconFalta.png")));
    }
  }
  
  private boolean ValidarAjuste()
  {
    if ((this.tfAumentar.isEnabled()) && (this.tfDisminuir.isEnabled()))
    {
      JOptionPane.showMessageDialog(null, "Ingrese un n�mero para aumentar o disminuir la cantidad", "Aviso", 2);
      return false;
    }
    return true;
  }
  
  public static String fecha()
  {
    Date fecha = new Date();
    SimpleDateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");
    return fechaFormato.format(fecha);
  }
  
  private void GuardarInventario()
  {
    while (modelo.getRowCount() > 0)
    {
      this.inventario = new ModeloInventarios();
      int codigo = DaoInventarios.IncrementarCodigo() + 1;
      
      this.inventario = DaoInventarios.Existencia(table.getValueAt(0, 0).toString(), fecha());
      
      String codProd = String.valueOf(table.getValueAt(0, 0));
      String estado = String.valueOf(table.getValueAt(0, 5));
      double stockActual = Double.parseDouble(String.valueOf(table.getValueAt(0, 4)));
      double inventariocant = Double.parseDouble(String.valueOf(table.getValueAt(0, 3)));
      double faSo = Double.parseDouble(String.valueOf(table.getValueAt(0, 6)));
      double costo = Double.parseDouble(String.valueOf(table.getValueAt(0, 7)));
      if (this.inventario != null)
      {
        ModeloInventarios guardar = new ModeloInventarios();
        
        guardar.setCodigo(codigo);
        guardar.getProducto().setCodigo(codProd);
        guardar.setStockActual(stockActual);
        guardar.setEstado(estado);
        guardar.setStockActual(stockActual);
        guardar.setCantInventario(inventariocant);
        guardar.setFaSo(faSo);
        guardar.setFecha(fecha());
        
        DaoInventarios.ActualizarDetalleInventario(guardar);
      }
      else
      {
        ModeloInventarios guardar = new ModeloInventarios();
        
        guardar.setCodigo(codigo);
        guardar.getProducto().setCodigo(codProd);
        guardar.setStockActual(stockActual);
        guardar.setEstado(estado);
        guardar.setStockActual(stockActual);
        guardar.setCantInventario(inventariocant);
        guardar.setFaSo(faSo);
        guardar.setFecha(fecha());
        guardar.setCosto(costo);
        
        DaoInventarios.GuardarInventario(guardar);
      }
      modelo.removeRow(0);
    }
  }
  
  public void CargarInventarioPendienteEnLaTabla()
  {
    listaInventarios = new ArrayList();
    listaInventarios = DaoInventarios.ConsultarInventarioPendiente();
    ActualizarTablaPrincipal();
  }
  
  private static void ActualizarTablaPrincipal()
  {
    while (modelo.getRowCount() > 0) {
      modelo.removeRow(0);
    }
    String[] campos = new String[7];
    for (int i = 0; i < listaInventarios.size(); i++)
    {
      modelo.addRow(campos);
      ModeloInventarios inventario = (ModeloInventarios)listaInventarios.get(i);
      
      modelo.setValueAt(inventario.getProducto().getCodigo(), i, 0);
      modelo.setValueAt(inventario.getProducto().getDescripcion(), i, 1);
      modelo.setValueAt(inventario.getProducto().getUnidadDeMedida(), i, 2);
      modelo.setValueAt(Double.valueOf(inventario.getCantInventario()), i, 3);
      modelo.setValueAt(Double.valueOf(inventario.getStockActual()), i, 4);
      modelo.setValueAt(inventario.getEstado(), i, 5);
      modelo.setValueAt(Double.valueOf(inventario.getFaSo()), i, 6);
      modelo.setValueAt(Double.valueOf(inventario.getCosto()), i, 7);
    }
  }    
}
