/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Formularios;

import com.eneri.scstock.Apariencia.FondoFormularios;
import com.eneri.scstock.Herramientas.Validaciones;
import com.eneri.scstock.Modelos.ModeloSucursal;
import com.eneri.scstock.Objetos.DaoSucursal;
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
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author RAscencio
 */
public class Sucursales extends JDialog
{
    
  private JTextField tfCodigo;
  private JTextField tfNombreSucursal;
  private JTextField tfResponsable;
  private JTextField tfTelefono;
  private JTextField tfDireccion;
  private JButton btnGuardar;
  private JButton btnEliminar;
  private JButton btnModificar;
  private JLabel lblCdigo;
  private JLabel lblSucursal;
  private JLabel lblNombreResp;
  private JTable table;
  public static List<ModeloSucursal> listaSucursales;
  public static DefaultTableModel modelo = new DefaultTableModel(null, new String[] { "C�digo", "Sucursal", "Nombre del responsable", "Contacto", "Direcci�n" })
  {
    boolean[] columnEditables = new boolean[5];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private JScrollPane scrollPane;
  private JLabel lblNombreSucursal;
  private JLabel lblNombreRespo;
  private JLabel lblTelefono;
  private JLabel lblDireccion;
  private JTabbedPane tabbedPane;
  private JTabbedPane tabbedPane_1;
  private FondoFormularios contentPane;
  private JLabel label;
  
  public static void main(String[] args)
  {
    try
    {
      Sucursales dialog = new Sucursales();
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
  
  public Sucursales()
  {
    Validaciones validar = new Validaciones();
    setIconImage(Toolkit.getDefaultToolkit().getImage(Sucursales.class.getResource("/IconosMin/Sucursal.png")));
    setResizable(false);
    setTitle("Sucursales");
    setBounds(100, 100, 900, 600);
    
    this.contentPane = new FondoFormularios();
    this.contentPane.setForeground(Color.BLACK);
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(this.contentPane);
    this.contentPane.setLayout(null);
    
    int codigo = DaoSucursal.IncrementarCodigo() + 1;
    this.tfCodigo = new JTextField();
    this.tfCodigo.setEditable(false);
    this.tfCodigo.setText(String.valueOf(codigo));
    this.tfCodigo.setBounds(100, 16, 86, 20);
    getContentPane().add(this.tfCodigo);
    this.tfCodigo.setColumns(10);
    
    this.tfNombreSucursal = new JTextField();
    this.tfNombreSucursal.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          Sucursales.this.tfResponsable.requestFocus();
        }
      }
    });
    this.tfNombreSucursal.setColumns(10);
    
    this.tfNombreSucursal.setBounds(100, 47, 366, 20);
    getContentPane().add(this.tfNombreSucursal);
    
    this.tfResponsable = new JTextField();
    validar.BloqueoNumerico(this.tfResponsable);
    this.tfResponsable.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          Sucursales.this.tfTelefono.requestFocus();
        }
      }
    });
    this.tfResponsable.setColumns(10);
    this.tfResponsable.setBounds(100, 97, 366, 20);
    getContentPane().add(this.tfResponsable);
    
    this.tfTelefono = new JTextField();
    validar.BloqueoAlfabetico(this.tfTelefono);
    this.tfTelefono.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          Sucursales.this.tfDireccion.requestFocus();
        }
      }
    });
    this.tfTelefono.setColumns(10);
    this.tfTelefono.setBounds(100, 147, 366, 20);
    getContentPane().add(this.tfTelefono);
    
    this.tfDireccion = new JTextField();
    this.tfDireccion.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          Sucursales.this.btnGuardar.requestFocus();
        }
      }
    });
    this.tfDireccion.setColumns(10);
    this.tfDireccion.setBounds(100, 195, 366, 20);
    getContentPane().add(this.tfDireccion);
    
    this.btnEliminar = new JButton("Eliminar");
    this.btnEliminar.setMnemonic('E');
    this.btnEliminar.setIcon(new ImageIcon(Sucursales.class.getResource("/Iconos/Eliminar.png")));
    this.btnEliminar.setEnabled(false);
    this.btnEliminar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Sucursales.this.EliminarSucursal();
      }
    });
    this.btnEliminar.setBounds(510, 166, 148, 58);
    getContentPane().add(this.btnEliminar);
    
    this.btnModificar = new JButton("Modificar");
    this.btnModificar.setMnemonic('M');
    this.btnModificar.setIcon(new ImageIcon(Sucursales.class.getResource("/Iconos/Modificar.png")));
    this.btnModificar.setEnabled(false);
    this.btnModificar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Sucursales.this.HabilitarCampos();
        Sucursales.this.btnGuardar.setText("Guardar ");
        Sucursales.this.btnGuardar.setEnabled(true);
        Sucursales.this.btnModificar.setEnabled(false);
        Sucursales.this.btnEliminar.setEnabled(false);
      }
    });
    this.btnModificar.setBounds(510, 97, 148, 58);
    getContentPane().add(this.btnModificar);
    
    this.btnGuardar = new JButton("Guardar");
    this.btnGuardar.setMnemonic('G');
    this.btnGuardar.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent e)
      {
        if (e.getKeyCode() == 10) {
          if (Sucursales.this.btnGuardar.getText().equals("Guardar"))
          {
            if (Sucursales.this.ValidarCampos()) {
              Sucursales.this.RegistrarSucursal();
            }
          }
          else {
            Sucursales.this.ModificarDatosDeLaSucursal();
          }
        }
      }
    });
    this.btnGuardar.setIcon(new ImageIcon(Sucursales.class.getResource("/Iconos/Guardar.png")));
    this.btnGuardar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (Sucursales.this.btnGuardar.getText().equals("Guardar"))
        {
          if (Sucursales.this.ValidarCampos()) {
            Sucursales.this.RegistrarSucursal();
          }
        }
        else {
          Sucursales.this.ModificarDatosDeLaSucursal();
        }
      }
    });
    this.btnGuardar.setBounds(510, 28, 148, 58);
    getContentPane().add(this.btnGuardar);
    
    this.lblCdigo = new JLabel("  C�digo:");
    this.lblCdigo.setForeground(Color.WHITE);
    this.lblCdigo.setBounds(44, 19, 52, 14);
    getContentPane().add(this.lblCdigo);
    
    this.lblSucursal = new JLabel("Sucursal:");
    this.lblSucursal.setForeground(Color.WHITE);
    this.lblSucursal.setBounds(37, 50, 59, 14);
    getContentPane().add(this.lblSucursal);
    
    this.lblNombreResp = new JLabel("Nombre resp.:");
    this.lblNombreResp.setForeground(Color.WHITE);
    this.lblNombreResp.setBounds(10, 100, 86, 14);
    getContentPane().add(this.lblNombreResp);
    
    JLabel lblTelfono = new JLabel("Tel�fono:");
    lblTelfono.setForeground(Color.WHITE);
    lblTelfono.setBounds(37, 150, 52, 14);
    getContentPane().add(lblTelfono);
    
    JLabel lblDireccin = new JLabel("Direcci�n:");
    lblDireccin.setForeground(Color.WHITE);
    lblDireccin.setBounds(37, 198, 59, 14);
    getContentPane().add(lblDireccin);
    
    this.scrollPane = new JScrollPane();
    this.scrollPane.setBounds(10, 244, 664, 317);
    getContentPane().add(this.scrollPane);
    
    this.table = new JTable();
    this.table.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        Sucursales.this.DeshabilitarCampos();
        int seleccion = Sucursales.this.table.getSelectedRow();
        String codigo = String.valueOf(Sucursales.this.table.getValueAt(seleccion, 0));
        String nombre = String.valueOf(Sucursales.this.table.getValueAt(seleccion, 1));
        String respon = String.valueOf(Sucursales.this.table.getValueAt(seleccion, 2));
        String contac = String.valueOf(Sucursales.this.table.getValueAt(seleccion, 3));
        String direcc = String.valueOf(Sucursales.this.table.getValueAt(seleccion, 4));
        
        Sucursales.this.tfCodigo.setText(codigo);
        Sucursales.this.tfNombreSucursal.setText(nombre);
        Sucursales.this.tfResponsable.setText(respon);
        Sucursales.this.tfTelefono.setText(contac);
        Sucursales.this.tfDireccion.setText(direcc);
        
        Sucursales.this.btnModificar.setEnabled(true);
        Sucursales.this.btnEliminar.setEnabled(true);
        Sucursales.this.btnGuardar.setEnabled(false);
      }
    });
    this.scrollPane.setViewportView(this.table);
    this.table.setModel(modelo);
    
    this.lblNombreSucursal = new JLabel("*");
    this.lblNombreSucursal.setVisible(false);
    this.lblNombreSucursal.setFont(new Font("Tahoma", 0, 20));
    this.lblNombreSucursal.setForeground(Color.RED);
    this.lblNombreSucursal.setBounds(474, 50, 46, 17);
    this.contentPane.add(this.lblNombreSucursal);
    
    this.lblNombreRespo = new JLabel("*");
    this.lblNombreRespo.setVisible(false);
    this.lblNombreRespo.setForeground(Color.RED);
    this.lblNombreRespo.setFont(new Font("Tahoma", 0, 20));
    this.lblNombreRespo.setBounds(474, 100, 46, 17);
    this.contentPane.add(this.lblNombreRespo);
    
    this.lblTelefono = new JLabel("*");
    this.lblTelefono.setVisible(false);
    this.lblTelefono.setForeground(Color.RED);
    this.lblTelefono.setFont(new Font("Tahoma", 0, 20));
    this.lblTelefono.setBounds(474, 150, 46, 17);
    this.contentPane.add(this.lblTelefono);
    
    this.lblDireccion = new JLabel("*");
    this.lblDireccion.setVisible(false);
    this.lblDireccion.setForeground(Color.RED);
    this.lblDireccion.setFont(new Font("Tahoma", 0, 20));
    this.lblDireccion.setBounds(474, 198, 46, 17);
    this.contentPane.add(this.lblDireccion);
    
    this.tabbedPane = new JTabbedPane(1);
    this.tabbedPane.setBounds(10, 11, 479, 222);
    this.contentPane.add(this.tabbedPane);
    
    this.tabbedPane_1 = new JTabbedPane(1);
    this.tabbedPane_1.setBounds(498, 9, 176, 224);
    this.contentPane.add(this.tabbedPane_1);
    
    this.label = new JLabel("");
    this.label.setIcon(new ImageIcon(Sucursales.class.getResource("/Galeria/sucursal.png")));
    this.label.setBounds(684, 327, 192, 209);
    this.contentPane.add(this.label);
    
    JTabbedPane tabbedPane_2 = new JTabbedPane(1);
    tabbedPane_2.setBounds(684, 9, 200, 552);
    this.contentPane.add(tabbedPane_2);
    this.table.getColumnModel().getColumn(0).setPreferredWidth(15);
    this.table.getColumnModel().getColumn(1).setPreferredWidth(95);
    this.table.getColumnModel().getColumn(2).setPreferredWidth(129);
    this.table.getColumnModel().getColumn(3).setPreferredWidth(15);
    this.table.getColumnModel().getColumn(4).setPreferredWidth(103);
    
    cargarTodosLosRegistros();
  }
  
  private boolean ValidarCampos()
  {
    if (this.tfNombreSucursal.getText().isEmpty())
    {
      JOptionPane.showMessageDialog(null, "Ingrese los datos requeridos", "Advertencia", 2);
      this.lblNombreSucursal.setVisible(true);
      return false;
    }
    if (this.tfResponsable.getText().isEmpty())
    {
      JOptionPane.showMessageDialog(null, "Ingrese los datos requeridos", "Advertencia", 2);
      this.lblNombreRespo.setVisible(true);
      return false;
    }
    if (this.tfDireccion.getText().isEmpty())
    {
      JOptionPane.showMessageDialog(null, "Ingrese los datos requeridos", "Advertencia", 2);
      this.lblDireccion.setVisible(true);
      return false;
    }
    if (this.tfTelefono.getText().isEmpty())
    {
      JOptionPane.showMessageDialog(null, "Ingrese los datos requeridos", "Advertencia", 2);
      this.lblTelefono.setVisible(true);
      return false;
    }
    return true;
  }
  
  private void HabilitarCampos()
  {
    this.tfNombreSucursal.setEnabled(true);
    this.tfResponsable.setEnabled(true);
    this.tfTelefono.setEnabled(true);
    this.tfDireccion.setEnabled(true);
  }
  
  private void DeshabilitarCampos()
  {
    this.tfNombreSucursal.setEnabled(false);
    this.tfResponsable.setEnabled(false);
    this.tfTelefono.setEnabled(false);
    this.tfDireccion.setEnabled(false);
  }
  
  private void RegistrarSucursal()
  {
    ModeloSucursal sucursal = new ModeloSucursal();
    
    sucursal.setCodigo(Integer.parseInt(this.tfCodigo.getText()));
    sucursal.setNombreSucursal(this.tfNombreSucursal.getText().toUpperCase());
    sucursal.setResponsable(this.tfResponsable.getText().toUpperCase());
    sucursal.setContacto(this.tfTelefono.getText());
    sucursal.setDireccion(this.tfDireccion.getText().toUpperCase());
    
    DaoSucursal.RegistrarSucursal(sucursal);
    cargarTodosLosRegistros();
    LimpiarCampos();
    this.tfNombreSucursal.requestFocus();
  }
  
  private void ModificarDatosDeLaSucursal()
  {
    ModeloSucursal sucursal = new ModeloSucursal();
    
    sucursal.setCodigo(Integer.parseInt(this.tfCodigo.getText()));
    sucursal.setNombreSucursal(this.tfNombreSucursal.getText().toUpperCase());
    sucursal.setResponsable(this.tfResponsable.getText().toUpperCase());
    sucursal.setContacto(this.tfTelefono.getText());
    sucursal.setDireccion(this.tfDireccion.getText().toUpperCase());
    
    DaoSucursal.EditarSucursal(sucursal);
    cargarTodosLosRegistros();
    LimpiarCampos();
    this.btnGuardar.setText("Guardar");
    this.tfNombreSucursal.requestFocus();
  }
  
  private void LimpiarCampos()
  {
    this.tfNombreSucursal.setText("");
    this.tfResponsable.setText("");
    this.tfTelefono.setText("");
    this.tfDireccion.setText("");
    
    this.tfCodigo.setText(String.valueOf(DaoSucursal.IncrementarCodigo() + 1));
  }
  
  public void cargarTodosLosRegistros()
  {
    listaSucursales = new ArrayList();
    listaSucursales = DaoSucursal.ConsultarTodos();
    ActualizarTablaPrincipal();
  }
  
  private static void ActualizarTablaPrincipal()
  {
    while (modelo.getRowCount() > 0) {
      modelo.removeRow(0);
    }
    String[] campos = new String[5];
    for (int i = 0; i < listaSucursales.size(); i++)
    {
      modelo.addRow(campos);
      ModeloSucursal sucursal = (ModeloSucursal)listaSucursales.get(i);
      
      modelo.setValueAt(Integer.valueOf(sucursal.getCodigo()), i, 0);
      modelo.setValueAt(sucursal.getNombreSucursal(), i, 1);
      modelo.setValueAt(sucursal.getResponsable(), i, 2);
      modelo.setValueAt(sucursal.getContacto(), i, 3);
      modelo.setValueAt(sucursal.getDireccion(), i, 4);
    }
  }
  
  private void EliminarSucursal()
  {
    int seleccion = this.table.getSelectedRow();
    try
    {
      if (seleccion < 0)
      {
        JOptionPane.showMessageDialog(null, "Seleccione una sucursal para eliminar", "", 2);
      }
      else
      {
        DefaultTableModel m = (DefaultTableModel)this.table.getModel();
        String codigo = this.table.getValueAt(seleccion, 0).toString();
        String nombre = this.table.getValueAt(seleccion, 1).toString();
        int yes = JOptionPane.showConfirmDialog(null, "�Est� seguro que desea eliminar la sucursal\n" + 
          nombre + "?", "Aviso", 0);
        if (yes == 0)
        {
          DaoSucursal.EliminarSucursal(Integer.parseInt(codigo));
          cargarTodosLosRegistros();
          this.btnModificar.setEnabled(false);
          this.btnEliminar.setEnabled(false);
          this.btnGuardar.setEnabled(true);
          this.btnGuardar.setText("Guardar");
          HabilitarCampos();
          LimpiarCampos();
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
