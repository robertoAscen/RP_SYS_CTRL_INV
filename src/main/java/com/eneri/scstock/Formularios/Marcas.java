/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Formularios;

import com.eneri.scstock.Apariencia.FondoFormularios;
import com.eneri.scstock.Modelos.ModeloMarcas;
import com.eneri.scstock.Objetos.DaoMarcas;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author RAscencio
 */
public class Marcas extends JDialog
{
    
  private final JPanel contentPane = new JPanel();
  private JTable table;
  private JTextField tfCodigo;
  private JTextField tfDescripcion;
  private JTextField tfBuscar;
  public static List<ModeloMarcas> listaMarcas;
  public static DefaultTableModel modelo = new DefaultTableModel(null, new String[] { "C�digo", "Descripc�n", "Estado" })
  {
    boolean[] columnEditables = new boolean[3];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private JButton btnGuardar;
  private JButton btnNuevo;
  private JButton btnModificar;
  private JLabel label;
  private JScrollPane scrollPane;
  private JLabel lblEstado;
  private JComboBox cbxEstado;
  private JButton btnEliminar;
  
  public static void main(String[] args)
  {
    try
    {
      Marcas dialog = new Marcas();
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
  
  public Marcas()
  {
    setIconImage(Toolkit.getDefaultToolkit().getImage(Marcas.class.getResource("/Iconos/Marcas.png")));
    setTitle("Registro de marcas");
    setBounds(100, 100, 763, 495);
    FondoFormularios contentPane = new FondoFormularios();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    this.scrollPane = new JScrollPane();
    this.scrollPane.setBounds(323, 41, 414, 337);
    contentPane.add(this.scrollPane);
    
    this.table = new JTable();
    this.table.setForeground(Color.WHITE);
    this.table.setBackground(new Color(51, 51, 51));
    this.table.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        Marcas.this.CargarDatos();
      }
    });
    this.table.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        Marcas.this.CargarDatos();
      }
    });
    this.scrollPane.setViewportView(this.table);
    this.table.setModel(modelo);
    this.table.getColumnModel().getColumn(0).setPreferredWidth(15);
    this.table.getColumnModel().getColumn(1).setPreferredWidth(150);
    
    this.tfCodigo = new JTextField();
    this.tfCodigo.setEnabled(false);
    this.tfCodigo.setBounds(10, 43, 86, 20);
    contentPane.add(this.tfCodigo);
    this.tfCodigo.setColumns(10);
    
    this.tfDescripcion = new JTextField();
    this.tfDescripcion.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (Marcas.this.tfDescripcion.getText().trim().length() > 0) {
          Marcas.this.btnGuardar.setEnabled(true);
        }
      }
    });
    this.tfDescripcion.setEnabled(false);
    this.tfDescripcion.setColumns(10);
    this.tfDescripcion.setBounds(10, 89, 303, 20);
    contentPane.add(this.tfDescripcion);
    
    JLabel lblCdigo = new JLabel("C�digo");
    lblCdigo.setForeground(Color.WHITE);
    lblCdigo.setBounds(10, 24, 46, 14);
    contentPane.add(lblCdigo);
    
    JLabel lblDescripcin = new JLabel("Descripci�n");
    lblDescripcin.setForeground(Color.WHITE);
    lblDescripcin.setBounds(10, 74, 86, 14);
    contentPane.add(lblDescripcin);
    
    this.tfBuscar = new JTextField();
    this.tfBuscar.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        Marcas.this.BuscarPorDescripcion();
      }
    });
    this.tfBuscar.setColumns(10);
    this.tfBuscar.setBounds(379, 18, 208, 20);
    contentPane.add(this.tfBuscar);
    
    JLabel lblBuscar = new JLabel("Buscar:");
    lblBuscar.setForeground(Color.WHITE);
    lblBuscar.setBounds(323, 21, 46, 14);
    contentPane.add(lblBuscar);
    
    this.btnGuardar = new JButton("Guardar");
    this.btnGuardar.setEnabled(false);
    this.btnGuardar.setIcon(new ImageIcon(Familias.class.getResource("/Iconos/Guardar.png")));
    this.btnGuardar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (Marcas.this.btnGuardar.getText().equals("Guardar")) {
          Marcas.this.GuardarMarca();
        } else {
          Marcas.this.ModificarMarca();
        }
        Marcas.this.tfDescripcion.setEnabled(false);
        Marcas.this.btnGuardar.setEnabled(false);
        Marcas.this.btnGuardar.setText("Guardar");
        Marcas.this.cbxEstado.setEnabled(false);
      }
    });
    this.btnGuardar.setBounds(159, 123, 154, 56);
    contentPane.add(this.btnGuardar);
    
    this.btnNuevo = new JButton("Nuevo");
    this.btnNuevo.setIcon(new ImageIcon(Familias.class.getResource("/Iconos/Nuevo.png")));
    this.btnNuevo.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Marcas.this.BotonNuevo();
      }
    });
    this.btnNuevo.setBounds(10, 123, 139, 56);
    contentPane.add(this.btnNuevo);
    
    this.btnModificar = new JButton("Modificar");
    this.btnModificar.setEnabled(false);
    this.btnModificar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Marcas.this.tfDescripcion.setEnabled(true);
        Marcas.this.tfDescripcion.requestFocus();
        Marcas.this.tfDescripcion.selectAll();
        Marcas.this.cbxEstado.setEnabled(true);
        Marcas.this.btnGuardar.setText("Guardar ");
        Marcas.this.btnModificar.setEnabled(false);
        Marcas.this.btnEliminar.setEnabled(false);
        Marcas.this.table.setEnabled(false);
      }
    });
    this.btnModificar.setIcon(new ImageIcon(Familias.class.getResource("/Iconos/Modificar.png")));
    this.btnModificar.setBounds(323, 389, 149, 57);
    contentPane.add(this.btnModificar);
    
    this.label = new JLabel("");
    this.label.setIcon(new ImageIcon(Familias.class.getResource("/Galeria/marcas.png")));
    this.label.setBounds(46, 236, 217, 190);
    contentPane.add(this.label);
    
    this.cbxEstado = new JComboBox();
    this.cbxEstado.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        if (Marcas.this.cbxEstado.isEnabled()) {
          Marcas.this.btnGuardar.setEnabled(true);
        }
      }
    });
    this.cbxEstado.setEnabled(false);
    this.cbxEstado.setModel(new DefaultComboBoxModel(new String[] { "ACTIVO", "INACTIVO" }));
    this.cbxEstado.setBounds(170, 41, 131, 20);
    contentPane.add(this.cbxEstado);
    
    this.lblEstado = new JLabel("Estado:");
    this.lblEstado.setForeground(Color.WHITE);
    this.lblEstado.setBounds(125, 42, 46, 20);
    contentPane.add(this.lblEstado);
    
    JTabbedPane tabbedPane = new JTabbedPane(1);
    tabbedPane.setBounds(10, 223, 303, 223);
    contentPane.add(tabbedPane);
    
    this.btnEliminar = new JButton("Eliminar");
    this.btnEliminar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Marcas.this.Eliminar();
      }
    });
    this.btnEliminar.setIcon(new ImageIcon(Marcas.class.getResource("/Iconos/Eliminar.png")));
    this.btnEliminar.setEnabled(false);
    this.btnEliminar.setBounds(588, 389, 149, 57);
    contentPane.add(this.btnEliminar);
    
    JButton btnCancelar = new JButton("Cancelar");
    btnCancelar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Marcas.this.tfDescripcion.setText("");
        Marcas.this.tfDescripcion.setEnabled(false);
        Marcas.this.table.setEnabled(true);
        //Marcas.access$11();
        Marcas.this.btnGuardar.setEnabled(false);
      }
    });
    btnCancelar.setIcon(new ImageIcon(Marcas.class.getResource("/Iconos/del.png")));
    btnCancelar.setBounds(10, 189, 303, 23);
    contentPane.add(btnCancelar);
    cargarTodosLosRegistros();
  }
  
  private void BotonNuevo()
  {
    int codigo = DaoMarcas.IncrementarCodigo() + 1;
    this.tfCodigo.setText(String.valueOf(codigo));
    
    this.tfDescripcion.setText("");
    this.tfDescripcion.setEnabled(true);
    this.tfDescripcion.requestFocus();
    this.table.setEnabled(false);
    this.btnGuardar.setText("Guardar");
  }
  
  private void CargarDatos()
  {
    if (this.table.isEnabled())
    {
      int filaseleccionada = this.table.getSelectedRow();
      this.tfCodigo.setText(String.valueOf(this.table.getValueAt(filaseleccionada, 0)));
      this.tfDescripcion.setText(String.valueOf(this.table.getValueAt(filaseleccionada, 1)));
      String estado = String.valueOf(this.table.getValueAt(filaseleccionada, 2));
      if (estado.equals("ACTIVO")) {
        this.cbxEstado.setSelectedIndex(0);
      } else {
        this.cbxEstado.setSelectedIndex(1);
      }
      this.btnModificar.setEnabled(true);
      this.btnEliminar.setEnabled(true);
    }
  }
  
  private void GuardarMarca()
  {
    ModeloMarcas guardar = new ModeloMarcas();
    guardar.setCodigo(Integer.parseInt(this.tfCodigo.getText()));
    guardar.setDescripcion(this.tfDescripcion.getText().toUpperCase());
    guardar.setEstado(this.cbxEstado.getSelectedItem().toString());
    
    DaoMarcas.RegistrarMarca(guardar);
    cargarTodosLosRegistros();
    this.tfCodigo.setText("");
    this.tfDescripcion.setText("");
    this.table.setEnabled(true);
    this.tfDescripcion.setEnabled(false);
    this.btnModificar.setEnabled(false);
  }
  
  private void Eliminar()
  {
    int seleccion = this.table.getSelectedRow();
    try
    {
      if (seleccion < 0)
      {
        JOptionPane.showMessageDialog(null, "Seleccione una marca para eliminar", "", 2);
      }
      else
      {
        int codigo = Integer.parseInt(String.valueOf(this.table.getValueAt(seleccion, 0).toString()));
        String nombre = this.table.getValueAt(seleccion, 1).toString();
        int yes = JOptionPane.showConfirmDialog(this, "�Est� seguro que desea eliminar la marca " + 
          nombre + "?", "Aviso", 0);
        if (yes == 0)
        {
          DaoMarcas.EliminarMarca(codigo);
          cargarTodosLosRegistros();
          this.btnModificar.setEnabled(false);
          this.btnEliminar.setEnabled(false);
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  private void ModificarMarca()
  {
    ModeloMarcas guardar = new ModeloMarcas();
    guardar.setCodigo(Integer.parseInt(this.tfCodigo.getText()));
    guardar.setDescripcion(this.tfDescripcion.getText().toUpperCase());
    guardar.setEstado(this.cbxEstado.getSelectedItem().toString());
    DaoMarcas.EditarMarca(guardar);
    cargarTodosLosRegistros();
    this.tfDescripcion.setText("");
  }
  
  public void cargarTodosLosRegistros()
  {
    listaMarcas = new ArrayList();
    listaMarcas = DaoMarcas.ConsultarMarcas("marca_descripcion");
    ActualizarTabla();
  }
  
  public void BuscarPorDescripcion()
  {
    listaMarcas = new ArrayList();
    listaMarcas = DaoMarcas.BuscarPorDescripcion(this.tfBuscar.getText().toUpperCase());
    ActualizarTabla();
  }
  
  private static void ActualizarTabla()
  {
    while (modelo.getRowCount() > 0) {
      modelo.removeRow(0);
    }
    String[] campos = new String[3];
    for (int i = 0; i < listaMarcas.size(); i++)
    {
      modelo.addRow(campos);
      ModeloMarcas cliente = (ModeloMarcas)listaMarcas.get(i);
      
      modelo.setValueAt(Integer.valueOf(cliente.getCodigo()), i, 0);
      modelo.setValueAt(cliente.getDescripcion(), i, 1);
      modelo.setValueAt(cliente.getEstado(), i, 2);
    }
  }    
}
