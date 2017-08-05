/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Formularios;

import com.eneri.scstock.Apariencia.Colores;
import com.eneri.scstock.Apariencia.FondoFormularios;
import com.eneri.scstock.Ediciones.EClientes;
import com.eneri.scstock.Herramientas.Validaciones;
import com.eneri.scstock.Inserciones.IClientes;
import com.eneri.scstock.Modelos.ModeloClientes;
import com.eneri.scstock.Objetos.DaoClientes;
import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAscencio
 */
public class Clientes extends JDialog
{    
  private JPanel contentPane;
  public static JTable tablaDeClientes;
  public static JTextField tfBuscarPorCedula;
  public static JTextField tfBuscarPorNombre;
  private JScrollPane scrollPaneTablaClientes;
  private JButton btnNuevo;
  private JButton btnVisualizar;
  private JButton btnEliminar;
  public static List<ModeloClientes> listaCliente;
  public static DefaultTableModel modelo = new DefaultTableModel(null, new String[] { "C�digo", "Nombre y apellido del cliente", "RUC / N� de c�dula" })
  {
    boolean[] columnEditables = new boolean[3];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private JLabel lblNewLabel;
  public static JButton btnActualizar;
  private JLabel lblBuscarPorCedula;
  private JLabel lblBuscarPorNombre;
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
          Clientes frame = new Clientes();
          frame.setLocationRelativeTo(null);
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
  
  public Clientes()
  {
    setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/Iconos/Clientes.png")));
    setResizable(false);
    setTitle("Registro de clientes");
    setDefaultCloseOperation(2);
    setBounds(100, 100, 900, 600);
    
    FondoFormularios contentPane = new FondoFormularios();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    this.btnNuevo = new JButton("Nuevo");
    this.btnNuevo.setIcon(new ImageIcon(Clientes.class.getResource("/Iconos/Nuevo.png")));
    this.btnNuevo.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Clientes.this.btnVisualizar.setEnabled(false);
        Clientes.this.btnEliminar.setEnabled(false);
        IClientes.main(null);
      }
    });
    this.btnNuevo.setToolTipText("Registrar un nuevo cliente");
    this.btnNuevo.setFont(new Font("Arial Narrow", 0, 17));
    this.btnNuevo.setBounds(25, 31, 155, 56);
    contentPane.add(this.btnNuevo);
    
    this.btnVisualizar = new JButton("Visualizar");
    this.btnVisualizar.setMnemonic('V');
    this.btnVisualizar.setEnabled(false);
    this.btnVisualizar.setIcon(new ImageIcon(Clientes.class.getResource("/Iconos/Visualizar.png")));
    this.btnVisualizar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Clientes.this.btnVisualizar.setEnabled(false);
        Clientes.this.btnEliminar.setEnabled(false);
        EClientes modificar = new EClientes();
        
        modificar.setLocationRelativeTo(null);
        modificar.setModal(true);
        modificar.setVisible(true);
      }
    });
    this.btnVisualizar.setToolTipText("Ver m�s datos del cliente");
    this.btnVisualizar.setFont(new Font("Arial Narrow", 0, 17));
    this.btnVisualizar.setBounds(25, 110, 155, 56);
    contentPane.add(this.btnVisualizar);
    
    this.btnEliminar = new JButton("Eliminar");
    this.btnEliminar.setMnemonic('E');
    this.btnEliminar.setEnabled(false);
    this.btnEliminar.setIcon(new ImageIcon(Clientes.class.getResource("/Iconos/Eliminar.png")));
    this.btnEliminar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Clientes.this.EliminarCliente();
      }
    });
    this.btnEliminar.setToolTipText("Eliminar registro del cliente");
    this.btnEliminar.setFont(new Font("Arial Narrow", 0, 17));
    this.btnEliminar.setBounds(25, 188, 155, 56);
    contentPane.add(this.btnEliminar);
    
    this.scrollPaneTablaClientes = new JScrollPane();
    this.scrollPaneTablaClientes.setBounds(199, 60, 685, 501);
    contentPane.add(this.scrollPaneTablaClientes);
    
    tablaDeClientes = new JTable();
    tablaDeClientes.setForeground(Color.WHITE);
    tablaDeClientes.setBackground(new Color(51, 51, 51));
    tablaDeClientes.getTableHeader().setReorderingAllowed(false);
    tablaDeClientes.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        Clientes.this.btnVisualizar.setEnabled(true);
        Clientes.this.btnEliminar.setEnabled(true);
      }
    });
    tablaDeClientes.setModel(modelo);
    tablaDeClientes.getColumnModel().getColumn(0).setPreferredWidth(64);
    tablaDeClientes.getColumnModel().getColumn(1).setPreferredWidth(380);
    tablaDeClientes.getColumnModel().getColumn(2).setPreferredWidth(96);
    this.scrollPaneTablaClientes.setViewportView(tablaDeClientes);
    
    tfBuscarPorCedula = new JTextField();
    Validaciones validar = new Validaciones();
    validar.BloqueoAlfabetico(tfBuscarPorCedula);
    tfBuscarPorCedula.setToolTipText("Buscar cliente por C.I.N�/RUC");
    tfBuscarPorCedula.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent evento)
      {
        if (Clientes.tfBuscarPorCedula.getText().trim().length() > 0)
        {
          Clientes.this.ConsultarClientePorNCedula();
          Clientes.tfBuscarPorNombre.setEnabled(false);
          Clientes.this.btnEliminar.setEnabled(false);
          Clientes.this.btnVisualizar.setEnabled(false);
        }
        else
        {
          Clientes.tfBuscarPorNombre.setEnabled(true);
          Clientes.this.cargarTodosLosRegistros();
        }
      }
    });
    tfBuscarPorCedula.setFont(new Font("Tahoma", 1, 13));
    tfBuscarPorCedula.setBounds(210, 31, 174, 20);
    contentPane.add(tfBuscarPorCedula);
    tfBuscarPorCedula.setColumns(10);
    
    tfBuscarPorNombre = new JTextField();
    validar.BloqueoNumerico(tfBuscarPorNombre);
    tfBuscarPorNombre.setToolTipText("Buscar cliente por nombre");
    tfBuscarPorNombre.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent evento)
      {
        if (Clientes.tfBuscarPorNombre.getText().trim().length() > 0)
        {
          Clientes.this.ConsultarClientePorNombre();
          Clientes.tfBuscarPorCedula.setEnabled(false);
          Clientes.tablaDeClientes.changeSelection(0, 0, false, false);
          Clientes.this.btnEliminar.setEnabled(false);
          Clientes.this.btnVisualizar.setEnabled(false);
        }
        else
        {
          Clientes.tfBuscarPorCedula.setEnabled(true);
          Clientes.this.cargarTodosLosRegistros();
        }
      }
    });
    tfBuscarPorNombre.setFont(new Font("Tahoma", 1, 13));
    tfBuscarPorNombre.setColumns(10);
    tfBuscarPorNombre.setBounds(394, 31, 469, 20);
    contentPane.add(tfBuscarPorNombre);
    
    this.lblNewLabel = new JLabel("");
    this.lblNewLabel.setIcon(new ImageIcon(Clientes.class.getResource("/Galeria/RClientes.png")));
    this.lblNewLabel.setBounds(10, 375, 206, 186);
    contentPane.add(this.lblNewLabel);
    
    btnActualizar = new JButton("Actualizando...");
    btnActualizar.setVisible(false);
    btnActualizar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Clientes.this.cargarTodosLosRegistros();
      }
    });
    btnActualizar.setToolTipText("Elimina el registro de la base de datos");
    btnActualizar.setFont(new Font("Arial Narrow", 0, 17));
    btnActualizar.setBounds(25, 262, 155, 56);
    contentPane.add(btnActualizar);
    
    this.lblBuscarPorCedula = new JLabel("Buscar por c�dula / RUC:");
    this.lblBuscarPorCedula.setForeground(Color.WHITE);
    this.lblBuscarPorCedula.setFont(new Font("Tahoma", 0, 15));
    this.lblBuscarPorCedula.setBounds(210, 6, 174, 25);
    contentPane.add(this.lblBuscarPorCedula);
    
    this.lblBuscarPorNombre = new JLabel("Buscar por nombre:");
    this.lblBuscarPorNombre.setForeground(Color.WHITE);
    this.lblBuscarPorNombre.setFont(new Font("Tahoma", 0, 15));
    this.lblBuscarPorNombre.setBounds(394, 6, 142, 25);
    contentPane.add(this.lblBuscarPorNombre);
    
    this.tfColor = new JTextField();
    this.tfColor.setVisible(false);
    this.tfColor.setBounds(25, 10, 86, 20);
    contentPane.add(this.tfColor);
    this.tfColor.setColumns(10);
    
    this.tabbedPane = new JTabbedPane(1);
    this.tabbedPane.setBounds(10, 6, 179, 555);
    contentPane.add(this.tabbedPane);
    
    this.tabbedPane_1 = new JTabbedPane(1);
    this.tabbedPane_1.setBounds(199, 6, 685, 49);
    contentPane.add(this.tabbedPane_1);
    
    cargarTodosLosRegistros();
    tfBuscarPorCedula.requestFocus();
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
    this.lblBuscarPorCedula.setForeground(Color.RED);
    this.lblBuscarPorNombre.setForeground(Color.RED);
  }
  
  private void ColorBlanco()
  {
    this.lblBuscarPorCedula.setForeground(Color.WHITE);
    this.lblBuscarPorNombre.setForeground(Color.WHITE);
  }
  
  private void ColorGris()
  {
    this.lblBuscarPorCedula.setForeground(Color.GRAY);
    this.lblBuscarPorNombre.setForeground(Color.GRAY);
  }
  
  private void ColorAmarillo()
  {
    this.lblBuscarPorCedula.setForeground(Color.YELLOW);
    this.lblBuscarPorNombre.setForeground(Color.YELLOW);
  }
  
  private void ColorVerde()
  {
    this.lblBuscarPorCedula.setForeground(new Color(0, 128, 0));
    this.lblBuscarPorNombre.setForeground(new Color(0, 128, 0));
  }
  
  private void ColorAzul()
  {
    this.lblBuscarPorCedula.setForeground(new Color(0, 0, 205));
    this.lblBuscarPorNombre.setForeground(new Color(0, 0, 205));
  }
  
  private void ColorMarron()
  {
    this.lblBuscarPorCedula.setForeground(new Color(139, 69, 19));
    this.lblBuscarPorNombre.setForeground(new Color(139, 69, 19));
  }
  
  private void ColorPurpura()
  {
    this.lblBuscarPorCedula.setForeground(new Color(148, 0, 211));
    this.lblBuscarPorNombre.setForeground(new Color(148, 0, 211));
  }
  
  private void ColorCyan()
  {
    this.lblBuscarPorCedula.setForeground(Color.CYAN);
    this.lblBuscarPorNombre.setForeground(Color.CYAN);
  }
  
  private void ColorNaranja()
  {
    this.lblBuscarPorCedula.setForeground(Color.ORANGE);
    this.lblBuscarPorNombre.setForeground(Color.ORANGE);
  }
  
  private void ColorVerdeFluor()
  {
    this.lblBuscarPorCedula.setForeground(Color.GREEN);
    this.lblBuscarPorNombre.setForeground(Color.GREEN);
  }
  
  private void ColorNegro()
  {
    this.lblBuscarPorCedula.setForeground(Color.BLACK);
    this.lblBuscarPorNombre.setForeground(Color.BLACK);
  }
  
  public void cargarTodosLosRegistros()
  {
    listaCliente = new ArrayList();
    listaCliente = DaoClientes.consultarTodoElRegistro("cliente_nombre");
    ActualizarTablaPrincipal();
  }
  
  private static void ActualizarTablaPrincipal()
  {
    while (modelo.getRowCount() > 0) {
      modelo.removeRow(0);
    }
    String[] campos = new String[3];
    for (int i = 0; i < listaCliente.size(); i++)
    {
      modelo.addRow(campos);
      ModeloClientes cliente = (ModeloClientes)listaCliente.get(i);
      
      modelo.setValueAt(Integer.valueOf(cliente.getCodigo()), i, 0);
      modelo.setValueAt(cliente.getNombre(), i, 1);
      modelo.setValueAt(cliente.getCedula(), i, 2);
    }
  }
  
  private void EliminarCliente()
  {
    int seleccion = tablaDeClientes.getSelectedRow();
    try
    {
      DefaultTableModel m = (DefaultTableModel)tablaDeClientes.getModel();
      String codigo = tablaDeClientes.getValueAt(seleccion, 0).toString();
      String nombre = tablaDeClientes.getValueAt(seleccion, 1).toString();
      int yes = JOptionPane.showConfirmDialog(this, "�Est� seguro que desea eliminar a " + 
        nombre + " del registro de clientes?", "Aviso", 0);
      if (yes == 0)
      {
        DaoClientes.EliminarCliente(Integer.parseInt(codigo));
        cargarTodosLosRegistros();
        this.btnVisualizar.setEnabled(false);
        this.btnEliminar.setEnabled(false);
      }
    }
    catch (Exception localException) {}
  }
  
  private void ConsultarClientePorNombre()
  {
    listaCliente = new ArrayList();
    listaCliente = DaoClientes.ConsultarPorNombre(tfBuscarPorNombre.getText());
    ActualizarTablaPrincipal();
  }
  
  private void ConsultarClientePorNCedula()
  {
    listaCliente = new ArrayList();
    listaCliente = DaoClientes.consultarPorCedula(tfBuscarPorCedula.getText());
    ActualizarTablaPrincipal();
  }    
}
