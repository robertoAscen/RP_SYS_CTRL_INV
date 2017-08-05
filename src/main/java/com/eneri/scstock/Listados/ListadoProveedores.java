/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Listados;

import com.eneri.scstock.Apariencia.Colores;
import com.eneri.scstock.Apariencia.FondoFormularios;
import com.eneri.scstock.Herramientas.ParametrosImpresion;
import com.eneri.scstock.Modelos.ModeloProveedores;
import com.eneri.scstock.Objetos.DaoConfiguraciones;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
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
public class ListadoProveedores extends JDialog
{
    
  private JTextField tfBuscadorNombre;
  public static List<ModeloProveedores> lista;
  public static DefaultTableModel modelo = new DefaultTableModel(null, new String[] { "C�digo", "Proveedor", "Contacto", "Web", "E-mail" })
  {
    boolean[] columnEditables = new boolean[4];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  public static JTable tablaProveedores;
  private JLabel lblCodigo;
  private JTextField tfColor;
  private JTextField tfRango2;
  private JTextField tfRango1;
  private JComboBox comboBoxOrden;
  private JCheckBox chckbxTodos;
  private JButton btnImprimir;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          ListadoProveedores dialog = new ListadoProveedores();
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
  
  public ListadoProveedores()
  {
    setTitle("Listado de proveedores");
    setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoProveedores.class.getResource("/Galeria/Proveedores.png")));
    setResizable(false);
    setBounds(100, 100, 900, 600);
    
    FondoFormularios contentPane = new FondoFormularios();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    this.tfBuscadorNombre = new JTextField();
    this.tfBuscadorNombre.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (ListadoProveedores.this.tfBuscadorNombre.getText().trim().length() > 0) {
          ListadoProveedores.this.BuscarProveedor();
        }
      }
    });
    this.tfBuscadorNombre.setToolTipText("Buscar proveedor por nombre");
    this.tfBuscadorNombre.setFont(new Font("Tahoma", 1, 13));
    this.tfBuscadorNombre.setColumns(10);
    this.tfBuscadorNombre.setBounds(141, 48, 471, 20);
    getContentPane().add(this.tfBuscadorNombre);
    
    this.lblCodigo = new JLabel("Buscar proveedor:");
    this.lblCodigo.setForeground(Color.WHITE);
    this.lblCodigo.setFont(new Font("Tahoma", 0, 15));
    this.lblCodigo.setBounds(10, 45, 142, 25);
    getContentPane().add(this.lblCodigo);
    
    JScrollPane scrollPanetablaProveedores = new JScrollPane();
    scrollPanetablaProveedores.setBounds(10, 87, 874, 407);
    getContentPane().add(scrollPanetablaProveedores);
    
    tablaProveedores = new JTable();
    tablaProveedores.setToolTipText("");
    tablaProveedores.getTableHeader().setReorderingAllowed(false);
    tablaProveedores.setForeground(Color.WHITE);
    tablaProveedores.setBackground(new Color(51, 51, 51));
    tablaProveedores.setModel(modelo);
    tablaProveedores.getColumnModel().getColumn(0).setPreferredWidth(25);
    tablaProveedores.getColumnModel().getColumn(1).setPreferredWidth(270);
    tablaProveedores.getColumnModel().getColumn(2).setPreferredWidth(28);
    scrollPanetablaProveedores.setViewportView(tablaProveedores);
    
    this.tfColor = new JTextField();
    this.tfColor.setVisible(false);
    this.tfColor.setBounds(509, 7, 86, 20);
    contentPane.add(this.tfColor);
    this.tfColor.setColumns(10);
    
    this.btnImprimir = new JButton("Imprimir");
    this.btnImprimir.setEnabled(false);
    this.btnImprimir.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        ListadoProveedores.this.dispose();
        ListadoProveedores.this.Imprimir();
      }
    });
    this.btnImprimir.setIcon(new ImageIcon(ListadoProveedores.class.getResource("/Iconos/imprimirIcon.png")));
    this.btnImprimir.setToolTipText("Imprimir listado de proveedores");
    this.btnImprimir.setFont(new Font("Tahoma", 0, 13));
    this.btnImprimir.setBounds(719, 505, 165, 56);
    contentPane.add(this.btnImprimir);
    
    JButton btnProcesar = new JButton("Procesar");
    btnProcesar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        ListadoProveedores.this.FiltrarDatos();
        int filatotal = ListadoProveedores.tablaProveedores.getRowCount();
        if (filatotal > 0) {
          ListadoProveedores.this.btnImprimir.setEnabled(true);
        }
      }
    });
    btnProcesar.setIcon(new ImageIcon(ListadoProveedores.class.getResource("/Iconos/Filtro.png")));
    btnProcesar.setToolTipText("Procesar datos");
    btnProcesar.setFont(new Font("Tahoma", 0, 13));
    btnProcesar.setBounds(533, 505, 176, 56);
    contentPane.add(btnProcesar);
    
    this.comboBoxOrden = new JComboBox();
    this.comboBoxOrden.setModel(new DefaultComboBoxModel(new String[] { "C�digo", "Nombre" }));
    this.comboBoxOrden.setBounds(760, 15, 124, 21);
    contentPane.add(this.comboBoxOrden);
    
    this.tfRango2 = new JTextField();
    this.tfRango2.setToolTipText("Buscar producto por c�digo");
    this.tfRango2.setFont(new Font("Tahoma", 1, 13));
    this.tfRango2.setColumns(10);
    this.tfRango2.setBounds(299, 15, 97, 20);
    contentPane.add(this.tfRango2);
    
    JLabel label = new JLabel("hasta:");
    label.setForeground(Color.WHITE);
    label.setFont(new Font("Tahoma", 0, 15));
    label.setBounds(248, 15, 53, 20);
    contentPane.add(label);
    
    this.tfRango1 = new JTextField();
    this.tfRango1.setToolTipText("Buscar producto por c�digo");
    this.tfRango1.setFont(new Font("Tahoma", 1, 13));
    this.tfRango1.setColumns(10);
    this.tfRango1.setBounds(141, 15, 97, 20);
    contentPane.add(this.tfRango1);
    
    JLabel label_1 = new JLabel("Filtrar desde:");
    label_1.setForeground(Color.WHITE);
    label_1.setFont(new Font("Tahoma", 0, 15));
    label_1.setBounds(49, 12, 124, 25);
    contentPane.add(label_1);
    
    JLabel label_2 = new JLabel("Ordenar lista por:");
    label_2.setForeground(Color.WHITE);
    label_2.setFont(new Font("Tahoma", 0, 15));
    label_2.setBounds(640, 15, 124, 21);
    contentPane.add(label_2);
    
    this.chckbxTodos = new JCheckBox("Todos");
    this.chckbxTodos.setForeground(Color.WHITE);
    this.chckbxTodos.setOpaque(false);
    this.chckbxTodos.setBounds(759, 48, 97, 23);
    contentPane.add(this.chckbxTodos);
    cargarTodos();
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
  
  public void FiltrarDatos()
  {
    if (this.chckbxTodos.isSelected())
    {
      lista = new ArrayList();
      if (this.comboBoxOrden.getSelectedIndex() == 0)
      {
        lista = DaoProveedores.consultarTodosLosProveedores("proveedor_codigo");
        ActualizarTablaPrincipal();
      }
      if (this.comboBoxOrden.getSelectedIndex() == 1)
      {
        lista = DaoProveedores.consultarTodosLosProveedores("proveedor_nombre");
        ActualizarTablaPrincipal();
      }
    }
    else if (validar())
    {
      if (this.comboBoxOrden.getSelectedIndex() == 0)
      {
        lista = DaoProveedores.consultarProveedorXRango("proveedor_codigo", this.tfRango1.getText().toUpperCase(), this.tfRango2.getText().toUpperCase().concat("ZZZZZZZ"));
        ActualizarTablaPrincipal();
      }
      if (this.comboBoxOrden.getSelectedIndex() == 1)
      {
        lista = DaoProveedores.consultarProveedorXRango("proveedor_nombre", this.tfRango1.getText().toUpperCase(), this.tfRango2.getText().toUpperCase().concat("ZZZZZZZ"));
        ActualizarTablaPrincipal();
      }
    }
  }
  
  private boolean validar()
  {
    if (this.tfRango1.getText().trim().length() < 1)
    {
      JOptionPane.showMessageDialog(null, "Ingrese el rango 'desde' y 'hasta' para filtrar los datos");
      return false;
    }
    return true;
  }
  
  private static void ActualizarTablaPrincipal()
  {
    while (modelo.getRowCount() > 0) {
      modelo.removeRow(0);
    }
    String[] campos = new String[4];
    for (int i = 0; i < lista.size(); i++)
    {
      modelo.addRow(campos);
      ModeloProveedores proveedor = (ModeloProveedores)lista.get(i);
      
      modelo.setValueAt(Integer.valueOf(proveedor.getCodigo()), i, 0);
      modelo.setValueAt(proveedor.getNombre(), i, 1);
      modelo.setValueAt(proveedor.getTelefono(), i, 2);
      modelo.setValueAt(proveedor.getSitioweb(), i, 3);
      modelo.setValueAt(proveedor.getEmail(), i, 4);
    }
  }
  
  private void BuscarProveedor()
  {
    if (this.comboBoxOrden.getSelectedIndex() == 0)
    {
      lista = new ArrayList();
      lista = DaoProveedores.BuscarProvedorOrdenCodigo(this.tfBuscadorNombre.getText());
      ActualizarTablaPrincipal();
    }
    if (this.comboBoxOrden.getSelectedIndex() == 1)
    {
      lista = new ArrayList();
      lista = DaoProveedores.BuscarProveedorOrdenNombre(this.tfBuscadorNombre.getText());
      ActualizarTablaPrincipal();
    }
  }
  
  private void cargarTodos()
  {
    lista = new ArrayList();
    lista = DaoProveedores.consultarTodosLosProveedores("proveedor_codigo");
    ActualizarTablaPrincipal();
  }
  
  private void Imprimir()
  {
    Map parametros = new HashMap();
    parametros.put("rango1", this.tfRango1.getText());
    parametros.put("rango2", this.tfRango2.getText());
    parametros.put("empresa", DaoConfiguraciones.NombreEmpresa());
    parametros.put("orden", this.comboBoxOrden.getSelectedItem().toString());
    parametros.put("descripcion", this.tfBuscadorNombre.getText().toUpperCase());
    
    ArrayList<?> list = (ArrayList)lista;
    ParametrosImpresion.impresionReporte(list, parametros, "ListadoDeProveedores");
  }    
}
