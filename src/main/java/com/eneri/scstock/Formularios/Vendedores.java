/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Formularios;

import com.eneri.scstock.Apariencia.FondoFormularios;
import com.eneri.scstock.Herramientas.Validaciones;
import com.eneri.scstock.Modelos.ModeloVendedores;
import com.eneri.scstock.Objetos.DaoVendedores;
import java.awt.Color;
import java.awt.Container;
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
import javax.swing.DefaultComboBoxModel;
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
public class Vendedores extends JDialog
{
    
  private JTextField tfCodigo;
  private JTextField tfNombre;
  private JTextField tfCedula;
  private JTextField tfTelefono;
  private JTextField tfDireccion;
  private JButton btnGuardar;
  private JButton btnEliminar;
  private JButton btnModificar;
  private JLabel lblCdigo;
  private JLabel lblSucursal;
  private JLabel lblNombreResp;
  private JTable table;
  public static List<ModeloVendedores> listaVendedores;
  public static DefaultTableModel modelo = new DefaultTableModel(null, new String[] { "C�digo", "Nombre y apellido", "C.I.N�", "Salario", "% Comisi�n", "Estado" })
  {
    boolean[] columnEditables = new boolean[6];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private JScrollPane scrollPane;
  private JLabel lblNombre;
  private JLabel lblCedula;
  private JTextField tfObservaciones;
  private JTextField tfSalario;
  private JTextField tfComision;
  private JLabel lblSalariox;
  private JLabel lblComision;
  private JComboBox comboBoxEstado;
  private JLabel lblPorcentaje;
  private JTabbedPane tabbedPane;
  private JTabbedPane tabbedPane_1;
  private JLabel label;
  private JTabbedPane tabbedPane_2;
  
  public static void main(String[] args)
  {
    try
    {
      Vendedores dialog = new Vendedores();
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
  
  public Vendedores()
  {
    setIconImage(Toolkit.getDefaultToolkit().getImage(Vendedores.class.getResource("/IconosMin/Vendedor.png")));
    setResizable(false);
    setTitle("Registro de vendedores");
    setBounds(100, 100, 900, 600);
    
    FondoFormularios contentPane = new FondoFormularios();
    contentPane.setForeground(Color.BLACK);
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    int codigo = DaoVendedores.IncrementarCodigo() + 1;
    this.tfCodigo = new JTextField();
    this.tfCodigo.setEditable(false);
    this.tfCodigo.setText(String.valueOf(codigo));
    this.tfCodigo.setBounds(121, 25, 86, 20);
    getContentPane().add(this.tfCodigo);
    this.tfCodigo.setColumns(10);
    
    Validaciones v = new Validaciones();
    this.tfNombre = new JTextField();
    v.BloqueoNumerico(this.tfNombre);
    this.tfNombre.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          Vendedores.this.tfCedula.requestFocus();
        }
      }
    });
    this.tfNombre.setColumns(10);
    this.tfNombre.setBounds(121, 67, 366, 20);
    getContentPane().add(this.tfNombre);
    
    this.tfCedula = new JTextField();
    v.BloqueoAlfabetico(this.tfCedula);
    this.tfCedula.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          Vendedores.this.tfTelefono.requestFocus();
        }
      }
    });
    this.tfCedula.setColumns(10);
    this.tfCedula.setBounds(121, 106, 130, 20);
    getContentPane().add(this.tfCedula);
    
    this.tfTelefono = new JTextField();
    v.BloqueoAlfabetico(this.tfTelefono);
    this.tfTelefono.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          Vendedores.this.tfSalario.requestFocus();
        }
      }
    });
    this.tfTelefono.setColumns(10);
    this.tfTelefono.setBounds(121, 147, 130, 20);
    getContentPane().add(this.tfTelefono);
    
    this.tfDireccion = new JTextField();
    this.tfDireccion.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          Vendedores.this.tfObservaciones.requestFocus();
        }
      }
    });
    this.tfDireccion.setColumns(10);
    this.tfDireccion.setBounds(121, 189, 366, 20);
    getContentPane().add(this.tfDireccion);
    
    this.btnEliminar = new JButton("Eliminar");
    this.btnEliminar.setMnemonic('E');
    this.btnEliminar.setIcon(new ImageIcon(Vendedores.class.getResource("/Iconos/Eliminar.png")));
    this.btnEliminar.setEnabled(false);
    this.btnEliminar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Vendedores.this.EliminarSucursal();
      }
    });
    this.btnEliminar.setBounds(551, 188, 146, 62);
    getContentPane().add(this.btnEliminar);
    
    this.btnModificar = new JButton("Modificar");
    this.btnModificar.setMnemonic('M');
    this.btnModificar.setIcon(new ImageIcon(Vendedores.class.getResource("/Iconos/Modificar.png")));
    this.btnModificar.setEnabled(false);
    this.btnModificar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Vendedores.this.HabilitarCampos();
        Vendedores.this.btnGuardar.setText("Guardar ");
        Vendedores.this.btnGuardar.setEnabled(true);
        Vendedores.this.btnModificar.setEnabled(false);
        Vendedores.this.btnEliminar.setEnabled(false);
      }
    });
    this.btnModificar.setBounds(551, 107, 146, 62);
    getContentPane().add(this.btnModificar);
    
    this.btnGuardar = new JButton("Guardar");
    this.btnGuardar.setMnemonic('G');
    this.btnGuardar.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          if (Vendedores.this.btnGuardar.getText().equals("Guardar"))
          {
            if (Vendedores.this.Validarciones()) {
              Vendedores.this.Guardar();
            }
          }
          else {
            Vendedores.this.ModificarDatosDelVendedor();
          }
        }
      }
    });
    this.btnGuardar.setIcon(new ImageIcon(Vendedores.class.getResource("/Iconos/Guardar.png")));
    this.btnGuardar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (Vendedores.this.btnGuardar.getText().equals("Guardar"))
        {
          if (Vendedores.this.Validarciones()) {
            Vendedores.this.Guardar();
          }
        }
        else if (Vendedores.this.Validarciones()) {
          Vendedores.this.ModificarDatosDelVendedor();
        }
      }
    });
    this.btnGuardar.setBounds(551, 26, 146, 62);
    getContentPane().add(this.btnGuardar);
    
    this.lblCdigo = new JLabel("  C�digo:");
    this.lblCdigo.setForeground(Color.WHITE);
    this.lblCdigo.setBounds(65, 25, 67, 20);
    getContentPane().add(this.lblCdigo);
    
    this.lblSucursal = new JLabel(" Nombre:");
    this.lblSucursal.setForeground(Color.WHITE);
    this.lblSucursal.setBounds(65, 67, 67, 20);
    getContentPane().add(this.lblSucursal);
    
    this.lblNombreResp = new JLabel("   C.I.N�:");
    this.lblNombreResp.setForeground(Color.WHITE);
    this.lblNombreResp.setBounds(75, 106, 57, 20);
    getContentPane().add(this.lblNombreResp);
    
    JLabel lblTelfono = new JLabel("Tel�fono:");
    lblTelfono.setForeground(Color.WHITE);
    lblTelfono.setBounds(65, 147, 67, 20);
    getContentPane().add(lblTelfono);
    
    JLabel lblDireccin = new JLabel("  Direcci�n:");
    lblDireccin.setForeground(Color.WHITE);
    lblDireccin.setBounds(51, 189, 81, 20);
    getContentPane().add(lblDireccin);
    
    this.scrollPane = new JScrollPane();
    this.scrollPane.setBounds(10, 275, 698, 286);
    getContentPane().add(this.scrollPane);
    
    this.table = new JTable();
    this.table.getTableHeader().setReorderingAllowed(false);
    this.table.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        Vendedores.this.ConsultarParaEditar();
      }
    });
    this.scrollPane.setViewportView(this.table);
    this.table.setModel(modelo);
    this.table.getColumnModel().getColumn(0).setPreferredWidth(16);
    this.table.getColumnModel().getColumn(1).setPreferredWidth(350);
    this.table.getColumnModel().getColumn(2).setPreferredWidth(32);
    this.table.getColumnModel().getColumn(3).setPreferredWidth(27);
    this.table.getColumnModel().getColumn(4).setPreferredWidth(29);
    this.table.getColumnModel().getColumn(5).setPreferredWidth(31);
    
    this.lblNombre = new JLabel("*");
    this.lblNombre.setVisible(false);
    this.lblNombre.setFont(new Font("Tahoma", 0, 20));
    this.lblNombre.setForeground(Color.RED);
    this.lblNombre.setBounds(495, 70, 46, 17);
    contentPane.add(this.lblNombre);
    
    this.lblCedula = new JLabel("*");
    this.lblCedula.setVisible(false);
    this.lblCedula.setForeground(Color.RED);
    this.lblCedula.setFont(new Font("Tahoma", 0, 20));
    this.lblCedula.setBounds(261, 106, 46, 25);
    contentPane.add(this.lblCedula);
    
    JLabel lblObservaciones = new JLabel("Observaciones:");
    lblObservaciones.setForeground(Color.WHITE);
    lblObservaciones.setBounds(25, 231, 107, 19);
    contentPane.add(lblObservaciones);
    
    this.tfObservaciones = new JTextField();
    this.tfObservaciones.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          Vendedores.this.btnGuardar.requestFocus();
        }
      }
    });
    this.tfObservaciones.setColumns(10);
    this.tfObservaciones.setBounds(121, 231, 366, 20);
    contentPane.add(this.tfObservaciones);
    
    JLabel lblSalario = new JLabel("Salario:");
    lblSalario.setForeground(Color.WHITE);
    lblSalario.setBounds(305, 107, 46, 14);
    contentPane.add(lblSalario);
    
    this.tfSalario = new JTextField();
    v.BloqueoAlfabetico(this.tfSalario);
    this.tfSalario.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          Vendedores.this.tfComision.requestFocus();
        }
      }
    });
    this.tfSalario.setColumns(10);
    this.tfSalario.setBounds(355, 104, 132, 20);
    contentPane.add(this.tfSalario);
    
    JLabel lblComisi = new JLabel("Comisi�n:");
    lblComisi.setForeground(Color.WHITE);
    lblComisi.setBounds(291, 150, 75, 14);
    contentPane.add(lblComisi);
    
    this.tfComision = new JTextField();
    this.tfComision.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          Vendedores.this.tfDireccion.requestFocus();
        }
      }
    });
    this.tfComision.setColumns(10);
    this.tfComision.setBounds(355, 147, 99, 20);
    contentPane.add(this.tfComision);
    
    JLabel lblEstado = new JLabel("Estado:");
    lblEstado.setForeground(Color.WHITE);
    lblEstado.setBounds(309, 28, 57, 14);
    contentPane.add(lblEstado);
    
    this.comboBoxEstado = new JComboBox();
    this.comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] { "ACTIVO", "INACTIVO" }));
    this.comboBoxEstado.setBounds(355, 25, 132, 20);
    contentPane.add(this.comboBoxEstado);
    
    this.lblSalariox = new JLabel("*");
    this.lblSalariox.setVisible(false);
    this.lblSalariox.setForeground(Color.RED);
    this.lblSalariox.setFont(new Font("Tahoma", 0, 20));
    this.lblSalariox.setBounds(495, 98, 52, 33);
    contentPane.add(this.lblSalariox);
    
    this.lblComision = new JLabel("*");
    this.lblComision.setVisible(false);
    this.lblComision.setForeground(Color.RED);
    this.lblComision.setFont(new Font("Tahoma", 0, 20));
    this.lblComision.setBounds(495, 153, 46, 14);
    contentPane.add(this.lblComision);
    
    this.lblPorcentaje = new JLabel("%");
    this.lblPorcentaje.setFont(new Font("Tahoma", 0, 15));
    this.lblPorcentaje.setForeground(Color.WHITE);
    this.lblPorcentaje.setBounds(463, 150, 63, 14);
    contentPane.add(this.lblPorcentaje);
    
    this.tabbedPane = new JTabbedPane(1);
    this.tabbedPane.setBounds(10, 11, 519, 253);
    contentPane.add(this.tabbedPane);
    
    this.tabbedPane_1 = new JTabbedPane(1);
    this.tabbedPane_1.setBounds(536, 11, 172, 253);
    contentPane.add(this.tabbedPane_1);
    
    this.label = new JLabel("");
    this.label.setIcon(new ImageIcon(Vendedores.class.getResource("/Galeria/Vendedor.png")));
    this.label.setBounds(712, 341, 172, 203);
    contentPane.add(this.label);
    
    this.tabbedPane_2 = new JTabbedPane(1);
    this.tabbedPane_2.setBounds(712, 11, 172, 550);
    contentPane.add(this.tabbedPane_2);
    
    cargarTodosLosRegistros();
  }
  
  private void ConsultarParaEditar()
  {
    DeshabilitarCampos();
    int seleccion = this.table.getSelectedRow();
    
    String codigo = String.valueOf(this.table.getValueAt(seleccion, 0));
    ModeloVendedores consultar = DaoVendedores.ConsutarPorCodigo(Integer.parseInt(codigo));
    
    this.tfCodigo.setText(codigo);
    this.tfNombre.setText(consultar.getNombre());
    this.tfCedula.setText(consultar.getCedula());
    this.tfTelefono.setText(consultar.getContacto());
    this.tfDireccion.setText(consultar.getDireccion());
    int salario = (int)consultar.getSalario();
    this.tfSalario.setText(String.valueOf(salario));
    this.tfComision.setText(String.valueOf(consultar.getComision()));
    this.comboBoxEstado.removeAllItems();
    this.comboBoxEstado.addItem(consultar.getEstado());
    if (this.comboBoxEstado.getSelectedItem().equals("ACTIVO")) {
      this.comboBoxEstado.addItem("INACTIVO");
    } else {
      this.comboBoxEstado.addItem("ACTIVO");
    }
    this.tfObservaciones.setText(consultar.getObservaciones());
    
    this.btnModificar.setEnabled(true);
    this.btnEliminar.setEnabled(true);
    this.btnGuardar.setEnabled(false);
  }
  
  private void Guardar()
  {
    ModeloVendedores insertar = new ModeloVendedores();
    try
    {
      insertar.setCodigo(Integer.parseInt(this.tfCodigo.getText()));
      insertar.setNombre(this.tfNombre.getText().toUpperCase());
      insertar.setCedula(this.tfCedula.getText());
      insertar.setContacto(this.tfTelefono.getText());
      insertar.setDireccion(this.tfDireccion.getText().toUpperCase());
      insertar.setObservaciones(this.tfObservaciones.getText().toUpperCase());
      insertar.setSalario(Double.parseDouble(this.tfSalario.getText()));
      insertar.setComision(Double.parseDouble(this.tfComision.getText()));
      insertar.setEstado(this.comboBoxEstado.getSelectedItem().toString());
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(null, "Error, no se guardar� el dato: " + e.getMessage(), "Error", 0);
    }
    DaoVendedores.RegistrarVendedor(insertar);
    
    LimpiarCampos();
    cargarTodosLosRegistros();
    this.tfNombre.requestFocus();
  }
  
  private boolean Validarciones()
  {
    if (this.tfNombre.getText().isEmpty())
    {
      JOptionPane.showMessageDialog(null, "Ingrese el nombre del vendedor", "Advertencia", 2);
      this.lblNombre.setVisible(true);
      this.tfNombre.requestFocus();
      return false;
    }
    if (this.tfCedula.getText().isEmpty())
    {
      JOptionPane.showMessageDialog(null, "Ingrese el n�mero de c�dula del vendedor", "Advertencia", 2);
      this.lblCedula.setVisible(true);
      this.tfCedula.requestFocus();
      return false;
    }
    if (this.tfSalario.getText().isEmpty())
    {
      JOptionPane.showMessageDialog(null, "Ingrese el salario que percibe el vendedor", "Advertencia", 2);
      this.lblSalariox.setVisible(true);
      this.tfSalario.requestFocus();
      return false;
    }
    if (this.tfComision.getText().isEmpty())
    {
      JOptionPane.showMessageDialog(null, "Ingrese el porcentaje de comisi�n que percibe el vendedor", "Advertencia", 2);
      this.lblComision.setVisible(true);
      this.tfComision.requestFocus();
      return false;
    }
    double comision = 0.0D;
    comision = Double.parseDouble(this.tfComision.getText());
    if (comision > 50.0D)
    {
      int yes = JOptionPane.showConfirmDialog(this, "La comisi�n es superior al 50%.\n\t\t\t\t\t\t\t\t\t�Est� seguro que desea guardar?", 
        "Pregunta", 0);
      if (yes == 0) {
        return true;
      }
      this.tfComision.requestFocus();
      this.tfComision.selectAll();
      return false;
    }
    return true;
  }
  
  private void HabilitarCampos()
  {
    this.tfNombre.setEnabled(true);
    this.tfCedula.setEnabled(true);
    this.tfTelefono.setEnabled(true);
    this.tfDireccion.setEnabled(true);
    this.tfObservaciones.setEnabled(true);
    this.tfSalario.setEnabled(true);
    this.tfComision.setEnabled(true);
    this.comboBoxEstado.setEnabled(true);
  }
  
  private void DeshabilitarCampos()
  {
    this.tfNombre.setEnabled(false);
    this.tfCedula.setEnabled(false);
    this.tfTelefono.setEnabled(false);
    this.tfDireccion.setEnabled(false);
    this.tfObservaciones.setEnabled(false);
    this.tfSalario.setEnabled(false);
    this.tfComision.setEnabled(false);
    this.comboBoxEstado.setEnabled(false);
  }
  
  private void ModificarDatosDelVendedor()
  {
    ModeloVendedores vendedor = new ModeloVendedores();
    try
    {
      vendedor.setCodigo(Integer.parseInt(this.tfCodigo.getText()));
      vendedor.setNombre(this.tfNombre.getText().toUpperCase());
      vendedor.setCedula(this.tfCedula.getText());
      vendedor.setContacto(this.tfTelefono.getText());
      vendedor.setDireccion(this.tfDireccion.getText().toUpperCase());
      vendedor.setObservaciones(this.tfObservaciones.getText().toUpperCase());
      vendedor.setComision(Double.parseDouble(this.tfComision.getText()));
      vendedor.setSalario(Double.parseDouble(this.tfSalario.getText()));
      vendedor.setEstado(this.comboBoxEstado.getSelectedItem().toString());
      DaoVendedores.EditarVendedor(vendedor);
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(null, "Error, no se guardar� el dato: " + e.getMessage(), "Error", 0);
    }
    cargarTodosLosRegistros();
    LimpiarCampos();
    this.btnGuardar.setText("Guardar");
    
    cargarTodosLosRegistros();
    this.tfNombre.requestFocus();
  }
  
  private void LimpiarCampos()
  {
    this.tfNombre.setText("");
    this.tfCedula.setText("");
    this.tfTelefono.setText("");
    this.tfDireccion.setText("");
    this.tfObservaciones.setText("");
    this.tfSalario.setText("");
    this.tfComision.setText("");
    this.tfNombre.setText("");
    this.tfCodigo.setText(String.valueOf(DaoVendedores.IncrementarCodigo() + 1));
  }
  
  public void cargarTodosLosRegistros()
  {
    listaVendedores = new ArrayList();
    listaVendedores = DaoVendedores.consultarTodoElRegistro();
    ActualizarTablaPrincipal();
  }
  
  private static void ActualizarTablaPrincipal()
  {
    while (modelo.getRowCount() > 0) {
      modelo.removeRow(0);
    }
    String[] campos = new String[5];
    for (int i = 0; i < listaVendedores.size(); i++)
    {
      modelo.addRow(campos);
      
      ModeloVendedores vendedor = (ModeloVendedores)listaVendedores.get(i);
      
      modelo.setValueAt(Integer.valueOf(vendedor.getCodigo()), i, 0);
      modelo.setValueAt(vendedor.getNombre(), i, 1);
      modelo.setValueAt(vendedor.getCedula(), i, 2);
      int salario = (int)vendedor.getSalario();
      modelo.setValueAt(Integer.valueOf(salario), i, 3);
      modelo.setValueAt(Double.valueOf(vendedor.getComision()), i, 4);
      modelo.setValueAt(vendedor.getEstado(), i, 5);
    }
  }
  
  private void EliminarSucursal()
  {
    int seleccion = this.table.getSelectedRow();
    try
    {
      if (seleccion < 0)
      {
        JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar", "", 2);
      }
      else
      {
        DefaultTableModel m = (DefaultTableModel)this.table.getModel();
        String codigo = this.table.getValueAt(seleccion, 0).toString();
        String nombre = this.table.getValueAt(seleccion, 1).toString();
        int yes = JOptionPane.showConfirmDialog(this, "�Est� seguro que desea eliminar el vendedor\n" + 
          nombre + "?", "Aviso", 0);
        if (yes == 0)
        {
          DaoVendedores.EliminarVendedor(Integer.parseInt(codigo));
          cargarTodosLosRegistros();
          this.btnModificar.setEnabled(false);
          this.btnEliminar.setEnabled(false);
          this.btnGuardar.setEnabled(true);
          this.btnGuardar.setText("Guardar");
          HabilitarCampos();
          LimpiarCampos();
          this.comboBoxEstado.removeAllItems();
          this.comboBoxEstado.addItem("ACTIVO");
          this.comboBoxEstado.addItem("INACTIVO");
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Try catch: ", e.getMessage(), 0);
    }
  }    
}
