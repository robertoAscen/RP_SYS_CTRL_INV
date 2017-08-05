/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Formularios;

import com.eneri.scstock.Apariencia.Colores;
import com.eneri.scstock.Apariencia.FondoFormularios;
import com.eneri.scstock.Ediciones.EProveedores;
import com.eneri.scstock.Inserciones.IProveedores;
import com.eneri.scstock.Modelos.ModeloProveedores;
import com.eneri.scstock.Objetos.DaoProveedores;
import java.awt.Color;
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
import java.util.ArrayList;
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
public class Proveedores extends JDialog
{
    
  private JTextField tfBuscador;
  public static List<ModeloProveedores> listaProveedores;
  public static DefaultTableModel modelo = new DefaultTableModel(null, new String[] { "C�digo", "Proveedor", "Contacto" })
  {
    boolean[] columnEditables = new boolean[3];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  public static JTable tablaProveedores;
  private JButton btnNuevo;
  private JButton btnVisualizar;
  private JButton btnEliminar;
  public static JButton btnActualizar;
  private JLabel lblCodigo;
  private JTextField tfColor;
  private JTabbedPane tabbedPane;
  private JTabbedPane tabbedPane_1;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          Proveedores dialog = new Proveedores();
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
  
  public Proveedores()
  {
    setTitle("Registro de proveedores");
    //setIconImage(Toolkit.getDefaultToolkit().getImage(Proveedores.class.getResource("/Galeria/Proveedores.png")));
    setResizable(false);
    setBounds(100, 100, 900, 600);
    
    FondoFormularios contentPane = new FondoFormularios();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    this.btnNuevo = new JButton("Nuevo");
    this.btnNuevo.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        IProveedores.main(null);
        Proveedores.this.btnVisualizar.setEnabled(false);
        Proveedores.this.btnEliminar.setEnabled(false);
      }
    });
    //this.btnNuevo.setIcon(new ImageIcon(Proveedores.class.getResource("/Iconos/NuevoProveedor.png")));
    this.btnNuevo.setToolTipText("Registrar un nuevo proveedor");
    this.btnNuevo.setFont(new Font("Arial Narrow", 0, 17));
    this.btnNuevo.setBounds(25, 38, 155, 56);
    getContentPane().add(this.btnNuevo);
    
    this.btnVisualizar = new JButton("Visualizar");
    this.btnVisualizar.setMnemonic('V');
    this.btnVisualizar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Proveedores.this.btnEliminar.setEnabled(false);
        Proveedores.this.btnVisualizar.setEnabled(false);
        
        EProveedores proveedores = new EProveedores();
        
        proveedores.setLocationRelativeTo(null);
        proveedores.setModal(true);
        proveedores.setVisible(true);
      }
    });
    //this.btnVisualizar.setIcon(new ImageIcon(Proveedores.class.getResource("/Iconos/Visualizar.png")));
    this.btnVisualizar.setToolTipText("Ver m�s datos del proveedor");
    this.btnVisualizar.setFont(new Font("Arial Narrow", 0, 17));
    this.btnVisualizar.setEnabled(false);
    this.btnVisualizar.setBounds(25, 117, 155, 56);
    getContentPane().add(this.btnVisualizar);
    
    this.btnEliminar = new JButton("Eliminar");
    this.btnEliminar.setMnemonic('E');
    this.btnEliminar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Proveedores.this.EliminarProveedor();
      }
    });
    //this.btnEliminar.setIcon(new ImageIcon(Proveedores.class.getResource("/Iconos/Eliminar.png")));
    this.btnEliminar.setToolTipText("Eliminar registro del proveedor");
    this.btnEliminar.setFont(new Font("Arial Narrow", 0, 17));
    this.btnEliminar.setEnabled(false);
    this.btnEliminar.setBounds(25, 195, 155, 56);
    getContentPane().add(this.btnEliminar);
    
    this.tfBuscador = new JTextField();
    this.tfBuscador.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (Proveedores.this.tfBuscador.getText().trim().length() > 0)
        {
          Proveedores.this.BuscarProveedor();
          Proveedores.tablaProveedores.changeSelection(0, 0, false, false);
          Proveedores.this.btnEliminar.setEnabled(false);
          Proveedores.this.btnVisualizar.setEnabled(false);
        }
        else
        {
          Proveedores.this.cargarTodosLosRegistros();
        }
      }
    });
    this.tfBuscador.setToolTipText("Buscar proveedor por nombre");
    this.tfBuscador.setFont(new Font("Tahoma", 1, 13));
    this.tfBuscador.setColumns(10);
    this.tfBuscador.setBounds(220, 38, 459, 20);
    getContentPane().add(this.tfBuscador);
    
    this.lblCodigo = new JLabel("Buscar proveedor:");
    this.lblCodigo.setForeground(Color.WHITE);
    this.lblCodigo.setFont(new Font("Tahoma", 0, 15));
    this.lblCodigo.setBounds(220, 13, 459, 25);
    getContentPane().add(this.lblCodigo);
    
    JLabel lblNewLabel_1 = new JLabel("");
    //lblNewLabel_1.setIcon(new ImageIcon(Proveedores.class.getResource("/Galeria/ProveedoresP.png")));
    lblNewLabel_1.setBounds(10, 354, 188, 197);
    getContentPane().add(lblNewLabel_1);
    
    JScrollPane scrollPanetablaProveedores = new JScrollPane();
    scrollPanetablaProveedores.setBounds(208, 69, 676, 497);
    getContentPane().add(scrollPanetablaProveedores);
    
    tablaProveedores = new JTable();
    tablaProveedores.setToolTipText("");
    tablaProveedores.getTableHeader().setReorderingAllowed(false);
    tablaProveedores.setForeground(Color.WHITE);
    tablaProveedores.setBackground(new Color(51, 51, 51));
    tablaProveedores.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        Proveedores.this.btnVisualizar.setEnabled(true);
        Proveedores.this.btnEliminar.setEnabled(true);
      }
    });
    tablaProveedores.setModel(modelo);
    tablaProveedores.getColumnModel().getColumn(0).setPreferredWidth(26);
    tablaProveedores.getColumnModel().getColumn(1).setPreferredWidth(277);
    tablaProveedores.getColumnModel().getColumn(2).setPreferredWidth(41);
    scrollPanetablaProveedores.setViewportView(tablaProveedores);
    
    btnActualizar = new JButton("Actualizando...");
    btnActualizar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Proveedores.this.cargarTodosLosRegistros();
      }
    });
    btnActualizar.setVisible(false);
    btnActualizar.setToolTipText("");
    btnActualizar.setFont(new Font("Arial Narrow", 0, 17));
    btnActualizar.setBounds(25, 271, 155, 56);
    getContentPane().add(btnActualizar);
    
    this.tfColor = new JTextField();
    this.tfColor.setVisible(false);
    this.tfColor.setBounds(364, 7, 86, 20);
    contentPane.add(this.tfColor);
    this.tfColor.setColumns(10);
    
    this.tabbedPane = new JTabbedPane(1);
    this.tabbedPane.setBounds(10, 11, 188, 555);
    contentPane.add(this.tabbedPane);
    
    this.tabbedPane_1 = new JTabbedPane(1);
    this.tabbedPane_1.setBounds(208, 13, 676, 49);
    contentPane.add(this.tabbedPane_1);
    
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
  }
  
  private void ColorBlanco()
  {
    this.lblCodigo.setForeground(Color.WHITE);
  }
  
  private void ColorGris()
  {
    this.lblCodigo.setForeground(Color.GRAY);
  }
  
  private void ColorAmarillo()
  {
    this.lblCodigo.setForeground(Color.YELLOW);
  }
  
  private void ColorVerde()
  {
    this.lblCodigo.setForeground(new Color(0, 128, 0));
  }
  
  private void ColorAzul()
  {
    this.lblCodigo.setForeground(new Color(0, 0, 205));
  }
  
  private void ColorMarron()
  {
    this.lblCodigo.setForeground(new Color(139, 69, 19));
  }
  
  private void ColorPurpura()
  {
    this.lblCodigo.setForeground(new Color(148, 0, 211));
  }
  
  private void ColorCyan()
  {
    this.lblCodigo.setForeground(Color.CYAN);
  }
  
  private void ColorNaranja()
  {
    this.lblCodigo.setForeground(Color.ORANGE);
  }
  
  private void ColorVerdeFluor()
  {
    this.lblCodigo.setForeground(Color.GREEN);
  }
  
  private void ColorNegro()
  {
    this.lblCodigo.setForeground(Color.BLACK);
  }
  
  public void cargarTodosLosRegistros()
  {
    listaProveedores = new ArrayList();
    listaProveedores = DaoProveedores.consultarTodosLosProveedores("proveedor_nombre");
    ActualizarTablaPrincipal();
  }
  
  private static void ActualizarTablaPrincipal()
  {
    while (modelo.getRowCount() > 0) {
      modelo.removeRow(0);
    }
    String[] campos = new String[3];
    for (int i = 0; i < listaProveedores.size(); i++)
    {
      modelo.addRow(campos);
      ModeloProveedores proveedor = (ModeloProveedores)listaProveedores.get(i);
      
      modelo.setValueAt(Integer.valueOf(proveedor.getCodigo()), i, 0);
      modelo.setValueAt(proveedor.getNombre(), i, 1);
      modelo.setValueAt(proveedor.getTelefono(), i, 2);
    }
  }
  
  private void EliminarProveedor()
  {
    int seleccion = tablaProveedores.getSelectedRow();
    try
    {
      DefaultTableModel m = (DefaultTableModel)tablaProveedores.getModel();
      String codigo = tablaProveedores.getValueAt(seleccion, 0).toString();
      String nombre = tablaProveedores.getValueAt(seleccion, 1).toString();
      int yes = JOptionPane.showConfirmDialog(this, "�Est� seguro que desea eliminar a " + 
        nombre + " del registro de proveedores?", "Aviso", 0);
      if (yes == 0)
      {
        DaoProveedores.EliminarProveedor(Integer.parseInt(codigo));
        cargarTodosLosRegistros();
        this.btnVisualizar.setEnabled(false);
        this.btnEliminar.setEnabled(false);
      }
    }
    catch (Exception localException) {}
  }
  
  private void BuscarProveedor()
  {
    listaProveedores = new ArrayList();
    listaProveedores = DaoProveedores.BuscarProveedorOrdenNombre(this.tfBuscador.getText());
    ActualizarTablaPrincipal();
  }    
}
