/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Formularios;

import com.eneri.scstock.Apariencia.Colores;
import com.eneri.scstock.Apariencia.FondoFormularios;
import com.eneri.scstock.Ediciones.EProductos;
import com.eneri.scstock.Herramientas.RenderCelda;
import com.eneri.scstock.Herramientas.Validaciones;
import com.eneri.scstock.Inserciones.IProductos;
import com.eneri.scstock.Modelos.ModeloProductos;
import com.eneri.scstock.Objetos.DaoProductos;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
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
public class Productos extends JDialog
{
    
  public static JTable tablaProductos;
  public static DefaultTableModel modelo = new DefaultTableModel(null, new String[] { "C�digo", "Descripci�n del producto", 
    "Costo", "Stock", "Estante", "U.M.", "Marca", "Familia" })
  {
    boolean[] columnEditables = new boolean[8];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private JTextField tfBuscadorCodigo;
  private JTextField tfBuscadorDescripcion;
  public static List<ModeloProductos> listaProductos;
  private JButton btnNuevo;
  private JButton btnVisualizar;
  private JButton btnEliminar;
  public static JButton btnActualizar;
  private JLabel lblCodigo;
  private JLabel lblBuscarPorDescripcin;
  private JTextField tfColor;
  private JTabbedPane tabbedPane;
  private JTabbedPane tabbedPane_1;
  private JButton btnMarcas;
  private JButton btnFamilias;
  private JPopupMenu popupMenu;
  private JMenuItem mntmElimar;
  private JMenuItem mntmModificar;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          Productos dialog = new Productos();
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
  
  public Productos()
  {
    setTitle("Registro de productos");
    setIconImage(Toolkit.getDefaultToolkit().getImage(Productos.class.getResource("/Galeria/paqueteProducto.png")));
    setResizable(false);
    setBounds(100, 100, 1226, 652);
    
    FondoFormularios contentPane = new FondoFormularios();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    
    contentPane.setLayout(null);
    this.btnNuevo = new JButton("Nuevo");
    this.btnNuevo.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        IProductos.main(null);
        Productos.this.btnVisualizar.setEnabled(false);
        Productos.this.btnEliminar.setEnabled(false);
      }
    });
    this.btnNuevo.setIcon(new ImageIcon(Productos.class.getResource("/Iconos/nuevoProducto.png")));
    this.btnNuevo.setToolTipText("Registrar un nuevo producto");
    this.btnNuevo.setFont(new Font("Arial Narrow", 0, 17));
    this.btnNuevo.setBounds(24, 33, 155, 56);
    getContentPane().add(this.btnNuevo);
    
    this.btnVisualizar = new JButton("Visualizar");
    this.btnVisualizar.setMnemonic('V');
    this.btnVisualizar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        int seleccion = Productos.tablaProductos.getSelectedRow();
        if (seleccion < 0)
        {
          JOptionPane.showMessageDialog(null, "Seleccione un producto para visualizar sus datos", "", 2);
        }
        else
        {
          Productos.this.btnVisualizar.setEnabled(false);
          Productos.this.btnEliminar.setEnabled(false);
          
          EProductos productos = new EProductos();
          
          productos.setLocationRelativeTo(null);
          productos.setModal(true);
          productos.setVisible(true);
        }
      }
    });
    this.btnVisualizar.setIcon(new ImageIcon(Productos.class.getResource("/Iconos/Visualizar.png")));
    this.btnVisualizar.setToolTipText("Ver m�s datos del producto");
    this.btnVisualizar.setFont(new Font("Arial Narrow", 0, 17));
    this.btnVisualizar.setEnabled(false);
    this.btnVisualizar.setBounds(24, 100, 155, 56);
    getContentPane().add(this.btnVisualizar);
    
    this.btnEliminar = new JButton("Eliminar");
    this.btnEliminar.setMnemonic('E');
    this.btnEliminar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Productos.this.EliminarProducto();
      }
    });
    this.btnEliminar.setIcon(new ImageIcon(Productos.class.getResource("/Iconos/Eliminar.png")));
    this.btnEliminar.setToolTipText("Eliminar registro del producto");
    this.btnEliminar.setFont(new Font("Arial Narrow", 0, 17));
    this.btnEliminar.setEnabled(false);
    this.btnEliminar.setBounds(24, 167, 155, 56);
    getContentPane().add(this.btnEliminar);
    
    JLabel label = new JLabel("");
    label.setIcon(new ImageIcon(Productos.class.getResource("/Galeria/paqueteProducto.png")));
    label.setBounds(10, 439, 179, 175);
    getContentPane().add(label);
    
    JScrollPane scrollPaneTablaProductos = new JScrollPane();
    scrollPaneTablaProductos.setBounds(199, 64, 1011, 550);
    getContentPane().add(scrollPaneTablaProductos);
    
    tablaProductos = new JTable();
    tablaProductos.getTableHeader().setReorderingAllowed(false);
    tablaProductos.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e)
      {
        if (e.getButton() == 1)
        {
          if (e.getClickCount() == 2) {
            Productos.this.btnVisualizar.doClick();
          }
          Productos.this.btnVisualizar.setEnabled(true);
          Productos.this.btnEliminar.setEnabled(true);
        }
      }
    });
    tablaProductos.setModel(modelo);
    tablaProductos.getColumnModel().getColumn(0).setPreferredWidth(85);
    tablaProductos.getColumnModel().getColumn(1).setPreferredWidth(330);
    tablaProductos.getColumnModel().getColumn(2).setPreferredWidth(65);
    tablaProductos.getColumnModel().getColumn(3).setPreferredWidth(30);
    tablaProductos.getColumnModel().getColumn(4).setPreferredWidth(40);
    tablaProductos.getColumnModel().getColumn(5).setPreferredWidth(15);
    tablaProductos.setDefaultRenderer(Object.class, new RenderCelda());
    
    this.popupMenu = new JPopupMenu();
    addPopup(tablaProductos, this.popupMenu);
    
    this.mntmElimar = new JMenuItem("Eliminar");
    this.mntmElimar.setIcon(new ImageIcon(Productos.class.getResource("/Iconos/Eliminarr.png")));
    this.mntmElimar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Productos.this.btnEliminar.doClick();
      }
    });
    this.popupMenu.add(this.mntmElimar);
    
    this.mntmModificar = new JMenuItem("Modificar");
    this.mntmModificar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        Productos.this.btnVisualizar.doClick();
      }
    });
    this.mntmModificar.setIcon(new ImageIcon(Productos.class.getResource("/Iconos/Modificar.png")));
    this.popupMenu.add(this.mntmModificar);
    tablaProductos.setSelectionBackground(Color.CYAN);
    tablaProductos.setSelectionForeground(Color.BLACK);
    scrollPaneTablaProductos.setViewportView(tablaProductos);
    
    this.tfBuscadorCodigo = new JTextField();
    this.tfBuscadorCodigo.setFont(new Font("Tahoma", 1, 13));
    Validaciones v = new Validaciones();
    v.BloqueoAlfabetico(this.tfBuscadorCodigo);
    this.tfBuscadorCodigo.setToolTipText("Buscar producto por c�digo");
    this.tfBuscadorCodigo.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (Productos.this.tfBuscadorCodigo.getText().trim().length() > 0)
        {
          Productos.this.BuscarProductoCodigo();
          Productos.this.btnVisualizar.setEnabled(false);
          Productos.this.btnEliminar.setEnabled(false);
          Productos.this.tfBuscadorDescripcion.setText("");
          Productos.this.tfBuscadorDescripcion.setEnabled(false);
        }
        else
        {
          Productos.this.tfBuscadorDescripcion.setEnabled(true);
          Productos.this.cargarTodosLosRegistros();
        }
      }
    });
    this.tfBuscadorCodigo.setBounds(208, 33, 156, 20);
    getContentPane().add(this.tfBuscadorCodigo);
    this.tfBuscadorCodigo.setColumns(10);
    
    this.tfBuscadorDescripcion = new JTextField();
    
    this.tfBuscadorDescripcion.setFont(new Font("Tahoma", 1, 13));
    this.tfBuscadorDescripcion.setToolTipText("Buscar producto por descripci�n");
    this.tfBuscadorDescripcion.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (Productos.this.tfBuscadorDescripcion.getText().trim().length() > 0)
        {
          Productos.this.BuscarProductoDescripcion();
          Productos.this.btnVisualizar.setEnabled(false);
          Productos.this.btnEliminar.setEnabled(false);
          Productos.this.tfBuscadorCodigo.setText("");
          Productos.this.tfBuscadorCodigo.setEnabled(false);
        }
        else
        {
          Productos.this.tfBuscadorCodigo.setEnabled(true);
          Productos.this.cargarTodosLosRegistros();
        }
      }
    });
    this.tfBuscadorDescripcion.setColumns(10);
    this.tfBuscadorDescripcion.setBounds(371, 33, 498, 20);
    getContentPane().add(this.tfBuscadorDescripcion);
    
    btnActualizar = new JButton("Actualizar...");
    btnActualizar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Productos.this.cargarTodosLosRegistros();
      }
    });
    btnActualizar.setVisible(false);
    btnActualizar.setToolTipText("Actualizar tabla");
    btnActualizar.setFont(new Font("Arial Narrow", 0, 17));
    btnActualizar.setBounds(10, 505, 155, 56);
    getContentPane().add(btnActualizar);
    
    this.lblCodigo = new JLabel("Buscar por c�digo:");
    this.lblCodigo.setForeground(Color.WHITE);
    this.lblCodigo.setFont(new Font("Tahoma", 0, 15));
    this.lblCodigo.setBounds(209, 11, 155, 25);
    contentPane.add(this.lblCodigo);
    
    this.lblBuscarPorDescripcin = new JLabel("Buscar por descripci�n:");
    this.lblBuscarPorDescripcin.setForeground(Color.WHITE);
    this.lblBuscarPorDescripcin.setFont(new Font("Tahoma", 0, 15));
    this.lblBuscarPorDescripcin.setBounds(371, 11, 165, 25);
    contentPane.add(this.lblBuscarPorDescripcin);
    
    this.tfColor = new JTextField();
    this.tfColor.setVisible(false);
    this.tfColor.setBounds(24, 11, 86, 20);
    contentPane.add(this.tfColor);
    this.tfColor.setColumns(10);
    
    this.tabbedPane_1 = new JTabbedPane(1);
    this.tabbedPane_1.setBounds(199, 11, 1011, 49);
    contentPane.add(this.tabbedPane_1);
    
    this.btnMarcas = new JButton("Marcas");
    this.btnMarcas.setIcon(new ImageIcon(Productos.class.getResource("/Iconos/Marcas.png")));
    this.btnMarcas.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Marcas.main(null);
      }
    });
    this.btnMarcas.setToolTipText("Registro de marca de productos");
    this.btnMarcas.setMnemonic('E');
    this.btnMarcas.setFont(new Font("Arial Narrow", 0, 17));
    this.btnMarcas.setBounds(24, 234, 155, 56);
    contentPane.add(this.btnMarcas);
    
    this.btnFamilias = new JButton("Familias");
    this.btnFamilias.setIcon(new ImageIcon(Productos.class.getResource("/Iconos/Familia.png")));
    this.btnFamilias.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Familias.main(null);
      }
    });
    this.btnFamilias.setToolTipText("Registro de familias de productos");
    this.btnFamilias.setMnemonic('E');
    this.btnFamilias.setFont(new Font("Arial Narrow", 0, 17));
    this.btnFamilias.setBounds(24, 301, 155, 56);
    contentPane.add(this.btnFamilias);
    
    this.tabbedPane = new JTabbedPane(1);
    this.tabbedPane.setBounds(10, 11, 179, 603);
    contentPane.add(this.tabbedPane);
    
    cargarTodosLosRegistros();
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
    this.lblCodigo.setForeground(Color.RED);
    this.lblBuscarPorDescripcin.setForeground(Color.RED);
  }
  
  private void ColorBlanco()
  {
    this.lblCodigo.setForeground(Color.WHITE);
    this.lblBuscarPorDescripcin.setForeground(Color.WHITE);
  }
  
  private void ColorGris()
  {
    this.lblCodigo.setForeground(Color.GRAY);
    this.lblBuscarPorDescripcin.setForeground(Color.GRAY);
  }
  
  private void ColorAmarillo()
  {
    this.lblCodigo.setForeground(Color.YELLOW);
    this.lblBuscarPorDescripcin.setForeground(Color.YELLOW);
  }
  
  private void ColorVerde()
  {
    this.lblCodigo.setForeground(new Color(0, 128, 0));
    this.lblBuscarPorDescripcin.setForeground(new Color(0, 128, 0));
  }
  
  private void ColorAzul()
  {
    this.lblCodigo.setForeground(new Color(0, 0, 205));
    this.lblBuscarPorDescripcin.setForeground(new Color(0, 0, 205));
  }
  
  private void ColorMarron()
  {
    this.lblCodigo.setForeground(new Color(139, 69, 19));
    this.lblBuscarPorDescripcin.setForeground(new Color(139, 69, 19));
  }
  
  private void ColorPurpura()
  {
    this.lblCodigo.setForeground(new Color(148, 0, 211));
    this.lblBuscarPorDescripcin.setForeground(new Color(148, 0, 211));
  }
  
  private void ColorCyan()
  {
    this.lblCodigo.setForeground(Color.CYAN);
    this.lblBuscarPorDescripcin.setForeground(Color.CYAN);
  }
  
  private void ColorNaranja()
  {
    this.lblCodigo.setForeground(Color.ORANGE);
    this.lblBuscarPorDescripcin.setForeground(Color.ORANGE);
  }
  
  private void ColorVerdeFluor()
  {
    this.lblCodigo.setForeground(Color.GREEN);
    this.lblBuscarPorDescripcin.setForeground(Color.GREEN);
  }
  
  private void ColorNegro()
  {
    this.lblCodigo.setForeground(Color.BLACK);
    this.lblBuscarPorDescripcin.setForeground(Color.BLACK);
  }
  
  public void cargarTodosLosRegistros()
  {
    listaProductos = new ArrayList();
    listaProductos = DaoProductos.consultarTodosLosProductos();
    ActualizarTablaPrincipal();
    this.tfBuscadorCodigo.setText("");
    this.tfBuscadorDescripcion.setText("");
  }
  
  private static void ActualizarTablaPrincipal()
  {
    while (modelo.getRowCount() > 0) {
      modelo.removeRow(0);
    }
    String[] campos = new String[8];
    for (int i = 0; i < listaProductos.size(); i++)
    {
      modelo.addRow(campos);
      ModeloProductos producto = (ModeloProductos)listaProductos.get(i);
      
      modelo.setValueAt(producto.getCodigo(), i, 0);
      modelo.setValueAt(producto.getDescripcion(), i, 1);
      DecimalFormat formatea = new DecimalFormat("###,###.##");
      double precio = producto.getPrecioVenta();
      modelo.setValueAt(formatea.format(precio), i, 2);
      modelo.setValueAt(Double.valueOf(producto.getStock()), i, 3);
      modelo.setValueAt(producto.getEstante(), i, 4);
      modelo.setValueAt(producto.getUnidadDeMedida(), i, 5);
      modelo.setValueAt(producto.getMarcas().getDescripcion(), i, 6);
      modelo.setValueAt(producto.getFamilia().getDescripcion(), i, 7);
    }
  }
  
  private void EliminarProducto()
  {
    int seleccion = tablaProductos.getSelectedRow();
    try
    {
      if (seleccion < 0)
      {
        JOptionPane.showMessageDialog(null, "Seleccione un producto para eliminar", "", 2);
      }
      else
      {
        DefaultTableModel m = (DefaultTableModel)tablaProductos.getModel();
        String codigo = tablaProductos.getValueAt(seleccion, 0).toString();
        String nombre = tablaProductos.getValueAt(seleccion, 1).toString();
        int yes = JOptionPane.showConfirmDialog(this, "�Est� seguro que desea eliminar el producto\n" + 
          nombre + "?", "Aviso", 0);
        if (yes == 0)
        {
          DaoProductos.EliminarProducto(codigo);
          cargarTodosLosRegistros();
          this.btnVisualizar.setEnabled(false);
          this.btnEliminar.setEnabled(false);
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  private void BuscarProductoDescripcion()
  {
    listaProductos = new ArrayList();
    listaProductos = DaoProductos.BuscarProductoDescripcion(this.tfBuscadorDescripcion.getText());
    ActualizarTablaPrincipal();
  }
  
  private void BuscarProductoCodigo()
  {
    listaProductos = new ArrayList();
    listaProductos = DaoProductos.BuscarProductoCodigo(this.tfBuscadorCodigo.getText());
    ActualizarTablaPrincipal();
  }
  
  private static void addPopup(Component component, JPopupMenu popup)
  {
    component.addMouseListener(new MouseAdapter()
    {
      public void mousePressed(MouseEvent e)
      {
        if (e.isPopupTrigger()) {
          showMenu(e);
        }
      }
      
      public void mouseReleased(MouseEvent e)
      {
        if (e.isPopupTrigger()) {
          showMenu(e);
        }
      }
      
      private void showMenu(MouseEvent e)
      {
        //Productos.this.show(e.getComponent(), e.getX(), e.getY());
      }
    });
  }    
}
