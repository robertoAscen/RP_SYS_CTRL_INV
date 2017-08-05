/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Listados;

import com.eneri.scstock.Apariencia.Colores;
import com.eneri.scstock.Apariencia.FondoFormularios;
import com.eneri.scstock.Herramientas.ParametrosImpresion;
import com.eneri.scstock.Herramientas.Validaciones;
import com.eneri.scstock.Modelos.ModeloProductos;
import com.eneri.scstock.Objetos.DaoConfiguraciones;
import com.eneri.scstock.Objetos.DaoProductos;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
public class ListadoProductos extends JDialog
{
    
  public static JTable tablaProductos;
  public static DefaultTableModel modelo = new DefaultTableModel(null, new String[] { "C�digo", "Descripci�n del producto", "Proveedor", "Costo", "Stock", "Sub-Total" })
  {
    boolean[] columnEditables = new boolean[6];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private JTextField tfCodigo1;
  public static List<ModeloProductos> lista;
  private JLabel lblDesde;
  private JTextField tfColor;
  private JButton btnImprimir;
  private JTextField tfCodigo2;
  private JCheckBox chbxSoloSinStock;
  private JCheckBox chbxTodos;
  private JLabel lblHasta;
  private JTextField tfBuscadorCodigo;
  private JTextField tfBuscadorDescripcion;
  private JLabel lblBuscCedula;
  private JLabel lblBuscarNombre;
  private JTabbedPane tabbedPane_2;
  private JTabbedPane tabbedPane_3;
  private JLabel lblRango;
  private JCheckBox chbSoloConStock;
  private JButton btnProcesar;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          ListadoProductos dialog = new ListadoProductos();
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
    });
  }
  
  public ListadoProductos()
  {
    setTitle("Listado de productos");
    setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoProductos.class.getResource("/Galeria/paqueteProducto.png")));
    setResizable(false);
    setBounds(100, 100, 900, 600);
    
    FondoFormularios contentPane = new FondoFormularios();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    
    contentPane.setLayout(null);
    
    JScrollPane scrollPaneTablaProductos = new JScrollPane();
    scrollPaneTablaProductos.setBounds(10, 139, 874, 359);
    getContentPane().add(scrollPaneTablaProductos);
    
    tablaProductos = new JTable();
    tablaProductos.getTableHeader().setReorderingAllowed(false);
    tablaProductos.setForeground(Color.WHITE);
    tablaProductos.setBackground(new Color(51, 51, 51));
    tablaProductos.setModel(modelo);
    tablaProductos.getColumnModel().getColumn(1).setPreferredWidth(296);
    tablaProductos.getColumnModel().getColumn(2).setPreferredWidth(71);
    tablaProductos.getColumnModel().getColumn(3).setPreferredWidth(47);
    scrollPaneTablaProductos.setViewportView(tablaProductos);
    
    this.tfCodigo1 = new JTextField();
    this.tfCodigo1.setFont(new Font("Tahoma", 1, 13));
    Validaciones v = new Validaciones();
    
    this.tfCodigo1.setToolTipText("Buscar producto por c�digo");
    this.tfCodigo1.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          if (ListadoProductos.this.tfCodigo1.getText().trim().length() > 0) {
            ListadoProductos.this.BuscarProductoCodigo();
          } else {
            ListadoProductos.this.cargarTodosLosRegistros();
          }
        }
      }
    });
    this.tfCodigo1.setBounds(99, 46, 97, 20);
    getContentPane().add(this.tfCodigo1);
    this.tfCodigo1.setColumns(10);
    
    this.lblDesde = new JLabel("Desde:");
    this.lblDesde.setForeground(Color.WHITE);
    this.lblDesde.setFont(new Font("Tahoma", 0, 15));
    this.lblDesde.setBounds(48, 43, 69, 25);
    contentPane.add(this.lblDesde);
    
    this.tfColor = new JTextField();
    this.tfColor.setVisible(false);
    this.tfColor.setBounds(808, 0, 86, 20);
    contentPane.add(this.tfColor);
    this.tfColor.setColumns(10);
    
    this.btnProcesar = new JButton("Procesar");
    this.btnProcesar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (ListadoProductos.this.chbxTodos.isSelected()) {
          ListadoProductos.this.cargarTodosLosRegistros();
        } else if (ListadoProductos.this.chbxSoloSinStock.isSelected()) {
          ListadoProductos.this.SinStock();
        } else if (ListadoProductos.this.chbSoloConStock.isSelected()) {
          ListadoProductos.this.ConStock();
        } else if (ListadoProductos.this.ValidarRango()) {
          ListadoProductos.this.ConsultarPorRangoDescripcionOrdenDescripcion();
        }
        int filatotal = ListadoProductos.tablaProductos.getRowCount();
        if (filatotal > 0) {
          ListadoProductos.this.btnImprimir.setEnabled(true);
        }
      }
    });
    this.btnProcesar.setIcon(new ImageIcon(ListadoProductos.class.getResource("/Iconos/Filtro.png")));
    this.btnProcesar.setToolTipText("Procesar datos");
    this.btnProcesar.setFont(new Font("Tahoma", 0, 13));
    this.btnProcesar.setBounds(519, 505, 176, 56);
    contentPane.add(this.btnProcesar);
    
    this.btnImprimir = new JButton("Imprimir");
    this.btnImprimir.setEnabled(false);
    this.btnImprimir.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        ListadoProductos.this.dispose();
        ListadoProductos.this.Imprimir();
      }
    });
    this.btnImprimir.setIcon(new ImageIcon(ListadoProductos.class.getResource("/Iconos/imprimirIcon.png")));
    this.btnImprimir.setToolTipText("Imprimir listado");
    this.btnImprimir.setFont(new Font("Tahoma", 0, 13));
    this.btnImprimir.setBounds(719, 505, 165, 56);
    contentPane.add(this.btnImprimir);
    
    this.chbxTodos = new JCheckBox("Todos");
    this.chbxTodos.setForeground(Color.WHITE);
    this.chbxTodos.setOpaque(false);
    this.chbxTodos.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (ListadoProductos.this.chbxTodos.isSelected())
        {
          ListadoProductos.this.chbxSoloSinStock.setSelected(false);
          ListadoProductos.this.chbSoloConStock.setSelected(false);
          ListadoProductos.this.LimpiarRango();
        }
        else
        {
          ListadoProductos.this.tfCodigo1.setEnabled(true);
          ListadoProductos.this.tfCodigo2.setEnabled(true);
        }
      }
    });
    this.chbxTodos.setBounds(757, 46, 111, 23);
    contentPane.add(this.chbxTodos);
    
    this.chbxSoloSinStock = new JCheckBox("S�lo sin stock");
    this.chbxSoloSinStock.setForeground(Color.WHITE);
    this.chbxSoloSinStock.setOpaque(false);
    this.chbxSoloSinStock.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (ListadoProductos.this.chbxSoloSinStock.isSelected())
        {
          ListadoProductos.this.chbxTodos.setSelected(false);
          ListadoProductos.this.chbSoloConStock.setSelected(false);
          ListadoProductos.this.LimpiarRango();
        }
        else
        {
          ListadoProductos.this.tfCodigo1.setEnabled(true);
          ListadoProductos.this.tfCodigo2.setEnabled(true);
        }
      }
    });
    this.chbxSoloSinStock.setBounds(757, 67, 111, 23);
    contentPane.add(this.chbxSoloSinStock);
    
    this.tfCodigo2 = new JTextField();
    this.tfCodigo2.setToolTipText("Buscar producto por c�digo");
    this.tfCodigo2.setFont(new Font("Tahoma", 1, 13));
    this.tfCodigo2.setColumns(10);
    this.tfCodigo2.setBounds(99, 77, 97, 20);
    contentPane.add(this.tfCodigo2);
    
    this.lblHasta = new JLabel("Hasta:");
    this.lblHasta.setForeground(Color.WHITE);
    this.lblHasta.setFont(new Font("Tahoma", 0, 15));
    this.lblHasta.setBounds(48, 77, 53, 20);
    contentPane.add(this.lblHasta);
    
    this.tfBuscadorCodigo = new JTextField();
    this.tfBuscadorCodigo.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if ((ListadoProductos.this.tfBuscadorCodigo.getText().trim().length() > 0) && 
          (evento.getKeyCode() == 10)) {
          ListadoProductos.this.BuscarProductoCodigo();
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (ListadoProductos.this.tfBuscadorCodigo.getText().trim().length() > 0)
        {
          ListadoProductos.this.tfBuscadorDescripcion.setText("");
          ListadoProductos.this.tfBuscadorDescripcion.setEnabled(false);
        }
        else
        {
          ListadoProductos.this.tfBuscadorDescripcion.setEnabled(true);
        }
      }
    });
    this.tfBuscadorCodigo.setToolTipText("Buscar producto por c�digo");
    this.tfBuscadorCodigo.setFont(new Font("Tahoma", 1, 13));
    this.tfBuscadorCodigo.setColumns(10);
    this.tfBuscadorCodigo.setBounds(371, 49, 160, 20);
    contentPane.add(this.tfBuscadorCodigo);
    
    this.tfBuscadorDescripcion = new JTextField();
    this.tfBuscadorDescripcion.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (ListadoProductos.this.tfBuscadorDescripcion.getText().trim().length() > 0)
        {
          ListadoProductos.this.tfBuscadorCodigo.setText("");
          ListadoProductos.this.tfBuscadorCodigo.setEnabled(false);
        }
        else
        {
          ListadoProductos.this.tfBuscadorCodigo.setEnabled(true);
        }
      }
      
      public void keyPressed(KeyEvent evento)
      {
        if ((ListadoProductos.this.tfBuscadorDescripcion.getText().trim().length() > 0) && 
          (evento.getKeyCode() == 10)) {
          ListadoProductos.this.BuscarProductoDescripcion();
        }
      }
    });
    this.tfBuscadorDescripcion.setToolTipText("Buscar producto por c�digo");
    this.tfBuscadorDescripcion.setFont(new Font("Tahoma", 1, 13));
    this.tfBuscadorDescripcion.setColumns(10);
    this.tfBuscadorDescripcion.setBounds(371, 88, 350, 20);
    contentPane.add(this.tfBuscadorDescripcion);
    
    this.lblBuscCedula = new JLabel("C�digo:");
    this.lblBuscCedula.setForeground(Color.WHITE);
    this.lblBuscCedula.setFont(new Font("Tahoma", 0, 15));
    this.lblBuscCedula.setBounds(321, 46, 97, 23);
    contentPane.add(this.lblBuscCedula);
    
    this.lblBuscarNombre = new JLabel("Descripci�n:");
    this.lblBuscarNombre.setForeground(Color.WHITE);
    this.lblBuscarNombre.setFont(new Font("Tahoma", 0, 15));
    this.lblBuscarNombre.setBounds(289, 88, 129, 20);
    contentPane.add(this.lblBuscarNombre);
    
    JTabbedPane tabbedPane = new JTabbedPane(1);
    tabbedPane.setBounds(9, 35, 268, 93);
    contentPane.add(tabbedPane);
    
    this.lblRango = new JLabel("Rango");
    this.lblRango.setForeground(Color.WHITE);
    this.lblRango.setFont(new Font("Tahoma", 0, 15));
    this.lblRango.setBounds(117, 13, 133, 25);
    contentPane.add(this.lblRango);
    
    JTabbedPane tabbedPane_1 = new JTabbedPane(1);
    tabbedPane_1.setBounds(9, 11, 268, 27);
    contentPane.add(tabbedPane_1);
    
    this.tabbedPane_2 = new JTabbedPane(1);
    this.tabbedPane_2.setBounds(284, 11, 459, 117);
    contentPane.add(this.tabbedPane_2);
    
    this.chbSoloConStock = new JCheckBox("S�lo con stock");
    this.chbSoloConStock.setForeground(Color.WHITE);
    this.chbSoloConStock.setOpaque(false);
    this.chbSoloConStock.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (ListadoProductos.this.chbSoloConStock.isSelected())
        {
          ListadoProductos.this.chbxSoloSinStock.setSelected(false);
          ListadoProductos.this.chbxTodos.setSelected(false);
        }
      }
    });
    this.chbSoloConStock.setBounds(757, 88, 111, 23);
    contentPane.add(this.chbSoloConStock);
    
    this.tabbedPane_3 = new JTabbedPane(1);
    this.tabbedPane_3.setBounds(751, 13, 133, 115);
    contentPane.add(this.tabbedPane_3);
    
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
    this.lblDesde.setForeground(Color.RED);
    this.lblHasta.setForeground(Color.RED);
    this.lblBuscCedula.setForeground(Color.RED);
    this.lblBuscarNombre.setForeground(Color.RED);
    this.lblRango.setForeground(Color.RED);
  }
  
  private void ColorBlanco()
  {
    this.lblDesde.setForeground(Color.WHITE);
    this.lblHasta.setForeground(Color.WHITE);
    this.lblBuscCedula.setForeground(Color.WHITE);
    this.lblBuscarNombre.setForeground(Color.WHITE);
    this.lblRango.setForeground(Color.WHITE);
  }
  
  private void ColorGris()
  {
    this.lblDesde.setForeground(Color.GRAY);
    this.lblHasta.setForeground(Color.GRAY);
    this.lblBuscCedula.setForeground(Color.GRAY);
    this.lblBuscarNombre.setForeground(Color.GRAY);
    this.lblRango.setForeground(Color.GRAY);
  }
  
  private void ColorAmarillo()
  {
    this.lblDesde.setForeground(Color.YELLOW);
    this.lblHasta.setForeground(Color.YELLOW);
    this.lblBuscCedula.setForeground(Color.YELLOW);
    this.lblBuscarNombre.setForeground(Color.YELLOW);
    this.lblRango.setForeground(Color.YELLOW);
  }
  
  private void ColorVerde()
  {
    this.lblDesde.setForeground(new Color(0, 128, 0));
    this.lblHasta.setForeground(new Color(0, 128, 0));
    this.lblBuscCedula.setForeground(new Color(0, 128, 0));
    this.lblBuscarNombre.setForeground(new Color(0, 128, 0));
    this.lblRango.setForeground(new Color(0, 128, 0));
  }
  
  private void ColorAzul()
  {
    this.lblDesde.setForeground(new Color(0, 0, 205));
    this.lblHasta.setForeground(new Color(0, 0, 205));
    this.lblBuscCedula.setForeground(new Color(0, 0, 205));
    this.lblBuscarNombre.setForeground(new Color(0, 0, 205));
    this.lblRango.setForeground(new Color(0, 0, 205));
  }
  
  private void ColorMarron()
  {
    this.lblDesde.setForeground(new Color(139, 69, 19));
    this.lblHasta.setForeground(new Color(139, 69, 19));
    this.lblBuscCedula.setForeground(new Color(139, 69, 19));
    this.lblBuscarNombre.setForeground(new Color(139, 69, 19));
    this.lblRango.setForeground(new Color(139, 69, 19));
  }
  
  private void ColorPurpura()
  {
    this.lblDesde.setForeground(new Color(148, 0, 211));
    this.lblHasta.setForeground(new Color(148, 0, 211));
    this.lblBuscCedula.setForeground(new Color(148, 0, 211));
    this.lblBuscarNombre.setForeground(new Color(148, 0, 211));
    this.lblRango.setForeground(new Color(148, 0, 211));
  }
  
  private void ColorCyan()
  {
    this.lblDesde.setForeground(Color.CYAN);
    this.lblHasta.setForeground(Color.CYAN);
    this.lblBuscCedula.setForeground(Color.CYAN);
    this.lblBuscarNombre.setForeground(Color.CYAN);
    this.lblRango.setForeground(Color.CYAN);
  }
  
  private void ColorNaranja()
  {
    this.lblDesde.setForeground(Color.ORANGE);
    this.lblHasta.setForeground(Color.ORANGE);
    this.lblBuscCedula.setForeground(Color.ORANGE);
    this.lblBuscarNombre.setForeground(Color.ORANGE);
    this.lblRango.setForeground(Color.ORANGE);
  }
  
  private void ColorVerdeFluor()
  {
    this.lblDesde.setForeground(Color.GREEN);
    this.lblHasta.setForeground(Color.GREEN);
    this.lblBuscCedula.setForeground(Color.GREEN);
    this.lblBuscarNombre.setForeground(Color.GREEN);
    this.lblRango.setForeground(Color.GREEN);
  }
  
  private void ColorNegro()
  {
    this.lblDesde.setForeground(Color.BLACK);
    this.lblHasta.setForeground(Color.BLACK);
    this.lblBuscCedula.setForeground(Color.BLACK);
    this.lblBuscarNombre.setForeground(Color.BLACK);
    this.lblRango.setForeground(Color.BLACK);
  }
  
  public void cargarTodosLosRegistros()
  {
    lista = new ArrayList();
    lista = DaoProductos.consultarTodosLosProductos();
    ActualizarTablaPrincipal();
  }
  
  private static void ActualizarTablaPrincipal()
  {
    while (modelo.getRowCount() > 0) {
      modelo.removeRow(0);
    }
    String[] campos = new String[6];
    double subtotal = 0.0D;
    for (int i = 0; i < lista.size(); i++)
    {
      modelo.addRow(campos);
      ModeloProductos producto = (ModeloProductos)lista.get(i);
      
      modelo.setValueAt(producto.getCodigo(), i, 0);
      modelo.setValueAt(producto.getDescripcion(), i, 1);
      modelo.setValueAt(producto.getProveedor().getNombre(), i, 2);
      DecimalFormat formatea = new DecimalFormat("###,###.##");
      double precio = producto.getPrecioVenta();
      modelo.setValueAt(formatea.format(precio), i, 3);
      modelo.setValueAt(Double.valueOf(producto.getStock()), i, 4);
      subtotal = producto.getSubtotal();
      
      modelo.setValueAt(formatea.format(subtotal), i, 5);
    }
  }
  
  private void BuscarProductoCodigo()
  {
    lista = new ArrayList();
    lista = DaoProductos.BuscarProductoCodigo(this.tfBuscadorCodigo.getText());
    ActualizarTablaPrincipal();
  }
  
  private void SinStock()
  {
    lista = new ArrayList();
    lista = DaoProductos.ConsultarProductoSinStock();
    ActualizarTablaPrincipal();
  }
  
  private void ConStock()
  {
    lista = new ArrayList();
    lista = DaoProductos.ConsultarProductoConStock();
    ActualizarTablaPrincipal();
  }
  
  private void ConsultarPorRangoDescripcionOrdenDescripcion()
  {
    try
    {
      lista = new ArrayList();
      lista = DaoProductos.ConsultaRango(this.tfCodigo1.getText(), this.tfCodigo2.getText().concat("ZZZZZZZZZZZZZZZZZZZ"));
      if (lista != null)
      {
        while (modelo.getRowCount() > 0) {
          modelo.removeRow(0);
        }
        if (lista.size() > 0) {
          ActualizarTablaPrincipal();
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  private boolean ValidarRango()
  {
    if (this.tfCodigo1.getText().trim().isEmpty())
    {
      JOptionPane.showMessageDialog(null, "Complete el rango desde qu� letra desea filtrar la lista.", "Rango 'desde' vac�o", 2);
      this.tfCodigo1.requestFocus();
      return false;
    }
    if (this.tfCodigo2.getText().trim().isEmpty())
    {
      JOptionPane.showMessageDialog(null, "Complete el rango hasta qu� letra desea filtrar la lista.", "Rango 'hasta' vac�o", 2);
      this.tfCodigo2.requestFocus();
      return false;
    }
    return true;
  }
  
  private void LimpiarRango()
  {
    this.tfCodigo1.setText("");
    this.tfCodigo2.setText("");
    this.tfCodigo1.setEnabled(false);
    this.tfCodigo2.setEnabled(false);
  }
  
  private void BuscarProductoDescripcion()
  {
    lista = new ArrayList();
    lista = DaoProductos.BuscarProductoDescripcion(this.tfBuscadorDescripcion.getText());
    ActualizarTablaPrincipal();
  }
  
  private void Imprimir()
  {
    Map parametros = new HashMap();
    parametros.put("rango1", this.tfCodigo1.getText());
    parametros.put("rango2", this.tfCodigo2.getText());
    parametros.put("empresa", DaoConfiguraciones.NombreEmpresa());
    parametros.put("descripcion", this.tfBuscadorDescripcion.getText().toUpperCase());
    parametros.put("codigo", this.tfBuscadorCodigo.getText().toUpperCase());
    if (this.chbxSoloSinStock.isSelected()) {
      parametros.put("condicion", this.chbxSoloSinStock.getText().toUpperCase());
    }
    if (this.chbxTodos.isSelected()) {
      parametros.put("condicion", this.chbxTodos.getText().toUpperCase());
    }
    if (this.chbSoloConStock.isSelected()) {
      parametros.put("condicion", this.chbSoloConStock.getText().toUpperCase());
    }
    if ((this.tfCodigo1.getText().trim().length() > 0) || (this.tfCodigo2.getText().trim().length() > 0) || (this.tfBuscadorCodigo.getText().trim().length() > 0) || (this.tfBuscadorDescripcion.getText().trim().length() > 0)) {
      parametros.put("condicion", "FILTRADO");
    }
    ArrayList<?> list = (ArrayList)lista;
    ParametrosImpresion.impresionReporte(list, parametros, "ListadoDeProductos");
  }        
}
