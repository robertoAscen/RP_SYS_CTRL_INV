/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Transferencias;

import com.eneri.scstock.Apariencia.FondoFormularios;
import com.eneri.scstock.Herramientas.Validaciones;
import com.eneri.scstock.Modelos.ModeloProductos;
import com.eneri.scstock.Modelos.ModeloSucursal;
import com.eneri.scstock.Objetos.DaoProductos;
import com.eneri.scstock.Objetos.DaoSucursal;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class Transferencias extends JDialog
{
    
  private JLabel lblTitulo;
  private static JTextField tfCodProducto;
  private JLabel lblCodProd;
  public static List<ModeloProductos> lista;
  public static DefaultTableModel modelo = new DefaultTableModel(null, new String[] { "C�d. del producto", "Descripci�n", "U.M.", "Transferencia", "Stock act.", "Precio", "Sub-Total" })
  {
    boolean[] columnEditables = new boolean[6];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private static JTable table;
  private JTabbedPane tabbedPane;
  private JLabel lblGaleriaIcon;
  private JTextField tfAumentar;
  private JTextField tfDisminuir;
  private JButton btnAceptar;
  private JButton btnNewButton;
  private JButton btnConsultar;
  private JButton btnGuardar;
  private JComboBox comboBoxSucursales;
  public static List<ModeloSucursal> listaSucursales;
  private JTabbedPane tabbedPane_3;
  private JTabbedPane tabbedPane_1;
  private JTabbedPane tabbedPane_4;
  
  public static void main(String[] args)
  {
    try
    {
      Transferencias dialog = new Transferencias();
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
  
  public Transferencias()
  {
    setTitle("Transferencia de productos");
    setResizable(false);
    setBounds(100, 100, 1114, 643);
    setIconImage(Toolkit.getDefaultToolkit().getImage(Transferencias.class.getResource("/Iconos/TransferenciasIcon.png")));
    FondoFormularios contentPane = new FondoFormularios();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    this.lblTitulo = new JLabel("Transferencia de productos");
    this.lblTitulo.setForeground(Color.WHITE);
    this.lblTitulo.setFont(new Font("Tahoma", 0, 35));
    this.lblTitulo.setBounds(231, 5, 586, 43);
    contentPane.add(this.lblTitulo);
    
    this.lblCodProd = new JLabel("C�d. del prod.:");
    this.lblCodProd.setForeground(Color.WHITE);
    this.lblCodProd.setFont(new Font("Tahoma", 0, 14));
    this.lblCodProd.setBounds(231, 65, 111, 30);
    contentPane.add(this.lblCodProd);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setAlignmentX(1.0F);
    scrollPane.setBounds(205, 117, 893, 411);
    contentPane.add(scrollPane);
    
    table = new JTable();
    table.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent Evento)
      {
        if (Evento.getKeyCode() == 127) {
          Transferencias.this.EliminarDeLaLista();
        }
      }
    });
    table.setForeground(SystemColor.text);
    table.setBackground(SystemColor.inactiveCaptionText);
    table.setSelectionBackground(Color.WHITE);
    table.setSelectionForeground(Color.BLACK);
    table.getTableHeader().setReorderingAllowed(false);
    table.setModel(modelo);
    table.getColumnModel().getColumn(0).setPreferredWidth(108);
    table.getColumnModel().getColumn(1).setPreferredWidth(400);
    table.getColumnModel().getColumn(2).setPreferredWidth(40);
    table.getColumnModel().getColumn(3).setPreferredWidth(40);
    table.getColumnModel().getColumn(4).setPreferredWidth(40);
    table.getColumnModel().getColumn(5).setPreferredWidth(40);
    scrollPane.setViewportView(table);
    
    this.tabbedPane = new JTabbedPane(1);
    this.tabbedPane.setBounds(10, 5, 1088, 49);
    contentPane.add(this.tabbedPane);
    
    tfCodProducto = new JTextField();
    Validaciones validar = new Validaciones();
    validar.BloqueoAlfabetico(tfCodProducto);
    tfCodProducto.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          if (Transferencias.tfCodProducto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "�Ingrese el c�digo del producto!", "Aviso", 2);
          } else {
            Transferencias.this.AgregaraLaLista();
          }
        }
      }
    });
    tfCodProducto.setBounds(352, 72, 117, 20);
    contentPane.add(tfCodProducto);
    tfCodProducto.setColumns(10);
    
    this.lblGaleriaIcon = new JLabel("");
    this.lblGaleriaIcon.setIcon(new ImageIcon(Transferencias.class.getResource("/Galeria/Transferencia.png")));
    this.lblGaleriaIcon.setForeground(Color.WHITE);
    this.lblGaleriaIcon.setFont(new Font("Tahoma", 0, 13));
    this.lblGaleriaIcon.setBounds(28, 310, 155, 218);
    contentPane.add(this.lblGaleriaIcon);
    
    JLabel lblAumentar = new JLabel("Aumentar:");
    lblAumentar.setForeground(Color.WHITE);
    lblAumentar.setFont(new Font("Tahoma", 0, 14));
    lblAumentar.setBounds(638, 65, 80, 30);
    contentPane.add(lblAumentar);
    
    this.tfAumentar = new JTextField();
    validar.BloqueoAlfabetico(this.tfAumentar);
    this.tfAumentar.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (Transferencias.this.tfAumentar.getText().trim().length() > 0) {
          Transferencias.this.tfDisminuir.setEnabled(false);
        } else {
          Transferencias.this.tfDisminuir.setEnabled(true);
        }
      }
    });
    this.tfAumentar.setColumns(10);
    this.tfAumentar.setBounds(712, 72, 56, 20);
    contentPane.add(this.tfAumentar);
    
    this.tfDisminuir = new JTextField();
    validar.BloqueoAlfabetico(this.tfDisminuir);
    this.tfDisminuir.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (Transferencias.this.tfDisminuir.getText().trim().length() > 0) {
          Transferencias.this.tfAumentar.setEnabled(false);
        } else {
          Transferencias.this.tfAumentar.setEnabled(true);
        }
      }
    });
    this.tfDisminuir.setColumns(10);
    this.tfDisminuir.setBounds(572, 72, 56, 20);
    contentPane.add(this.tfDisminuir);
    
    this.btnAceptar = new JButton("Ok");
    this.btnAceptar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (Transferencias.this.ValidarAjuste()) {
          Transferencias.this.AjustarTransferencia();
        }
      }
    });
    this.btnAceptar.setBounds(778, 72, 50, 20);
    contentPane.add(this.btnAceptar);
    
    this.btnNewButton = new JButton("IMPRIMIR");
    this.btnNewButton.setIcon(new ImageIcon(Transferencias.class.getResource("/Iconos/imprimirIcon.png")));
    this.btnNewButton.setBounds(953, 541, 145, 63);
    contentPane.add(this.btnNewButton);
    
    this.btnConsultar = new JButton("CONSULTAR");
    this.btnConsultar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0) {}
    });
    this.btnConsultar.setIcon(new ImageIcon(Transferencias.class.getResource("/Iconos/Consultar.png")));
    this.btnConsultar.setBounds(604, 541, 167, 63);
    contentPane.add(this.btnConsultar);
    
    this.btnGuardar = new JButton("GUARDAR");
    this.btnGuardar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0) {}
    });
    this.btnGuardar.setCursor(Cursor.getPredefinedCursor(12));
    this.btnGuardar.setIcon(new ImageIcon(Transferencias.class.getResource("/Iconos/Guardar.png")));
    this.btnGuardar.setBounds(781, 541, 161, 63);
    contentPane.add(this.btnGuardar);
    
    JLabel lblSucursal = new JLabel("Sucursal:");
    lblSucursal.setForeground(Color.WHITE);
    lblSucursal.setFont(new Font("Tahoma", 0, 14));
    lblSucursal.setBounds(845, 72, 73, 20);
    contentPane.add(lblSucursal);
    
    this.comboBoxSucursales = new JComboBox();
    this.comboBoxSucursales.setBounds(903, 72, 189, 20);
    contentPane.add(this.comboBoxSucursales);
    
    JTabbedPane tabbedPane_2 = new JTabbedPane(1);
    tabbedPane_2.setBounds(838, 59, 260, 49);
    contentPane.add(tabbedPane_2);
    
    this.tabbedPane_3 = new JTabbedPane(1);
    this.tabbedPane_3.setBounds(10, 59, 191, 469);
    contentPane.add(this.tabbedPane_3);
    
    this.tabbedPane_4 = new JTabbedPane(1);
    this.tabbedPane_4.setBounds(206, 59, 282, 49);
    contentPane.add(this.tabbedPane_4);
    
    this.tabbedPane_1 = new JTabbedPane(1);
    this.tabbedPane_1.setBounds(494, 59, 340, 49);
    contentPane.add(this.tabbedPane_1);
    
    JLabel lblLbldisminuir = new JLabel("Disminuir:");
    lblLbldisminuir.setForeground(Color.WHITE);
    lblLbldisminuir.setFont(new Font("Tahoma", 0, 14));
    lblLbldisminuir.setBounds(498, 65, 80, 30);
    contentPane.add(lblLbldisminuir);
    
    cargarComboBoxSucursales();
  }
  
  private void cargarComboBoxSucursales()
  {
    listaSucursales = new ArrayList();
    listaSucursales = DaoSucursal.ConsultarTodos();
    
    this.comboBoxSucursales.addItem("");
    for (int i = 0; i < listaSucursales.size(); i++)
    {
      ModeloSucursal m = (ModeloSucursal)listaSucursales.get(i);
      m.getCodigo();
      m.getNombreSucursal();
      this.comboBoxSucursales.addItem(m.getCodigo() + "-" + m.getNombreSucursal());
    }
  }
  
  private void AjustarTransferencia()
  {
    int filaseleccionada = table.getSelectedRow();
    
    int aumentar = 0;
    int total = 0;
    int inventariotabla = 0;
    int disminuir = 0;
    
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
      inventariotabla = Integer.parseInt(String.valueOf(table.getValueAt(filaseleccionada, 3)));
      if (this.tfAumentar.isEnabled())
      {
        aumentar = Integer.parseInt(this.tfAumentar.getText());
        total = aumentar + inventariotabla;
        
        totalSetTable = String.valueOf(total);
        table.setValueAt(totalSetTable, filaseleccionada, 3);
        
        int cantInventario = Integer.parseInt(String.valueOf(table.getValueAt(filaseleccionada, 3)));
        int resultFaSo = 0;
        
        table.setValueAt(Integer.valueOf(cantInventario), filaseleccionada, 3);
        if (cantInventario == producto.getStock())
        {
          estado = "OK";
          this.lblGaleriaIcon.setText("Infomaci�n adicional>|::::::Descripci�n=" + producto.getDescripcion() + 
            "|-|" + "::::::Costo=" + producto.getPrecioVenta() + "|-|" + "::::::Fac. sin stock=" + producto.getFacturar() + 
            "|-|" + "::::::Proveedor=" + producto.getCodProveedor() + "<" + "Estado inventario: " + estado);
          this.lblGaleriaIcon.setForeground(Color.GREEN);
          faSo = String.valueOf(0);
          table.setValueAt(estado, filaseleccionada, 5);
        }
        if (cantInventario > producto.getStock())
        {
          estado = "SOBRA";
          table.setValueAt(estado, filaseleccionada, 5);
          this.lblGaleriaIcon.setText("Infomaci�n adicional>|::::::Descripci�n=" + producto.getDescripcion() + 
            "|-|" + "::::::Costo=" + producto.getPrecioVenta() + "|-|" + "::::::Fac. sin stock=" + producto.getFacturar() + 
            "|-|" + "::::::Proveedor=" + producto.getCodProveedor() + "<" + "Estado inventario: " + estado);
          this.lblGaleriaIcon.setForeground(Color.WHITE);
          resultFaSo = (int)(cantInventario - producto.getStock());
          faSo = String.valueOf(resultFaSo);
        }
        if (cantInventario < producto.getStock())
        {
          estado = "FALTA";
          table.setValueAt(estado, filaseleccionada, 5);
          this.lblGaleriaIcon.setText("Infomaci�n adicional>|::::::Descripci�n=" + producto.getDescripcion() + 
            "|-|" + "::::::Costo=" + producto.getPrecioVenta() + "|-|" + "::::::Fac. sin stock=" + producto.getFacturar() + 
            "|-|" + "::::::Proveedor=" + producto.getCodProveedor() + "<" + "Estado inventario: " + estado);
          this.lblGaleriaIcon.setForeground(Color.RED);
          resultFaSo = (int)(producto.getStock() - cantInventario);
          faSo = String.valueOf(resultFaSo);
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
        
        int cantInventario = Integer.parseInt(String.valueOf(table.getValueAt(filaseleccionada, 3)));
        int resultFaSo = 0;
        
        table.setValueAt(Integer.valueOf(cantInventario), filaseleccionada, 3);
        if (cantInventario == producto.getStock())
        {
          estado = "OK";
          this.lblGaleriaIcon.setText("Infomaci�n adicional>|::::::Descripci�n=" + producto.getDescripcion() + 
            "|-|" + "::::::Costo=" + producto.getPrecioVenta() + "|-|" + "::::::Fac. sin stock=" + producto.getFacturar() + 
            "|-|" + "::::::Proveedor=" + producto.getCodProveedor() + "<" + "Estado inventario: " + estado);
          this.lblGaleriaIcon.setForeground(Color.GREEN);
          faSo = String.valueOf(0);
          table.setValueAt(estado, filaseleccionada, 5);
        }
        if (cantInventario > producto.getStock())
        {
          estado = "SOBRA";
          table.setValueAt(estado, filaseleccionada, 5);
          this.lblGaleriaIcon.setText("Infomaci�n adicional>|::::::Descripci�n=" + producto.getDescripcion() + 
            "|-|" + "::::::Costo=" + producto.getPrecioVenta() + "|-|" + "::::::Fac. sin stock=" + producto.getFacturar() + 
            "|-|" + "::::::Proveedor=" + producto.getCodProveedor() + "<" + "Estado inventario: " + estado);
          this.lblGaleriaIcon.setForeground(Color.WHITE);
          resultFaSo = (int)(cantInventario - producto.getStock());
          faSo = String.valueOf(resultFaSo);
        }
        if (cantInventario < producto.getStock())
        {
          estado = "FALTA";
          table.setValueAt(estado, filaseleccionada, 5);
          this.lblGaleriaIcon.setText("Infomaci�n adicional>|::::::Descripci�n=" + producto.getDescripcion() + 
            "|-|" + "::::::Costo=" + producto.getPrecioVenta() + "|-|" + "::::::Fac. sin stock=" + producto.getFacturar() + 
            "|-|" + "::::::Proveedor=" + producto.getCodProveedor() + "<" + "Estado inventario: " + estado);
          this.lblGaleriaIcon.setForeground(Color.RED);
          resultFaSo = (int)(producto.getStock() - cantInventario);
          faSo = String.valueOf(resultFaSo);
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
    double precio = 0.0D;
    String transferencia = "";
    double transferenciaConvert = 0.0D;
    
    double subtotal = 0.0D;
    
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
      transferencia = JOptionPane.showInputDialog("Ingrese cantidad a transferir");
      String stockActual = String.valueOf(producto.getStock());
      precio = producto.getPrecioVenta();
      if ((transferencia == null) || (transferencia.equals("")))
      {
        JOptionPane.showMessageDialog(null, "No se ha ingresado ning�n valor", "Error", 0);
        return;
      }
      transferenciaConvert = Double.parseDouble(transferencia);
      
      subtotal = precio * transferenciaConvert;
      
      String x = String.valueOf(subtotal);
      
      String precioVenta = String.valueOf(precio);
      String[] fila = { codigo, descripcion, unidadMedida, transferencia, stockActual, precioVenta, x };
      modelo.addRow(fila);
    }
    tfCodProducto.setText("");
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
    String transferencia = "";
    String codigoParaBuscProd = String.valueOf(table.getValueAt(filaseleccionada, 0));
    
    double precioVenta = 0.0D;
    
    ModeloProductos producto = DaoProductos.ConsutaParaModificar(codigoParaBuscProd);
    
    String descripcion = producto.getDescripcion();
    String unidadMedida = producto.getUnidadDeMedida();
    transferencia = JOptionPane.showInputDialog("Ingrese cantidad a transferir");
    if (transferencia == null)
    {
      JOptionPane.showMessageDialog(null, "No se ha ingresado ninguna cantidad");
      return;
    }
    String stockActual = String.valueOf(producto.getStock());
    precioVenta = producto.getPrecioVenta();
    
    int cantidadInventario = Integer.parseInt(String.valueOf(table.getValueAt(filaseleccionada, 3)));
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
  
  private void EliminarDeLaLista()
  {
    int seleccion = table.getSelectedRow();
    String nombre = table.getValueAt(seleccion, 1).toString();
    int yes = JOptionPane.showConfirmDialog(this, "�Est� seguro que desea eliminar de la lista el producto " + 
      nombre + "?", "Aviso", 0);
    if (yes == 0) {
      modelo.removeRow(seleccion);
    }
  }    
}
