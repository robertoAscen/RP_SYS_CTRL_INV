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
import com.eneri.scstock.Modelos.ModeloClientes;
import com.eneri.scstock.Objetos.DaoClientes;
import com.eneri.scstock.Objetos.DaoConfiguraciones;
import java.awt.Color;
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
import javax.swing.JPanel;
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
public class ListadoClientes extends JDialog
{
    
  private JPanel contentPane;
  public static JTable tablaDeClientes;
  public static JTextField tfBuscarPorCedula;
  public static JTextField tfBuscarPorNombre;
  private JScrollPane scrollPaneTablaClientes;
  public static List<ModeloClientes> listaCliente;
  public static DefaultTableModel modelo = new DefaultTableModel(null, new String[] { "C�digo", "Nombre y apellido del cliente", "RUC / C.I.N�", "Contacto", "Direcci�n" })
  {
    boolean[] columnEditables = new boolean[5];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private JLabel lblBuscCedula;
  private JLabel lblBuscarNombre;
  private JTextField tfColor;
  private JButton btnImprimir;
  private JButton btnProcesar;
  private JComboBox comboBox;
  private JLabel lblOrden;
  private JTextField tfCodigo1;
  private JTextField tfCodigo2;
  private JLabel lblRango;
  private JTabbedPane tabbedPane_2;
  private JCheckBox chbxNumerico;
  private JCheckBox chbxAlfabetico;
  private JCheckBox chbxTodos;
  private JLabel lblHasta;
  private JLabel lblDesde;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          ListadoClientes frame = new ListadoClientes();
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
  
  public ListadoClientes()
  {
    setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoClientes.class.getResource("/Iconos/Clientes.png")));
    setResizable(false);
    setTitle("Listado de clientes");
    setDefaultCloseOperation(2);
    setBounds(100, 100, 900, 600);
    
    FondoFormularios contentPane = new FondoFormularios();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    this.scrollPaneTablaClientes = new JScrollPane();
    this.scrollPaneTablaClientes.setBounds(10, 166, 874, 328);
    contentPane.add(this.scrollPaneTablaClientes);
    
    tablaDeClientes = new JTable();
    tablaDeClientes.setForeground(Color.WHITE);
    tablaDeClientes.setBackground(new Color(51, 51, 51));
    tablaDeClientes.getTableHeader().setReorderingAllowed(false);
    tablaDeClientes.setModel(modelo);
    tablaDeClientes.getColumnModel().getColumn(0).setPreferredWidth(40);
    tablaDeClientes.getColumnModel().getColumn(1).setPreferredWidth(293);
    tablaDeClientes.getColumnModel().getColumn(2).setPreferredWidth(49);
    tablaDeClientes.getColumnModel().getColumn(3).setPreferredWidth(42);
    tablaDeClientes.getColumnModel().getColumn(4).setPreferredWidth(104);
    this.scrollPaneTablaClientes.setViewportView(tablaDeClientes);
    
    tfBuscarPorCedula = new JTextField();
    Validaciones validar = new Validaciones();
    validar.BloqueoAlfabetico(tfBuscarPorCedula);
    tfBuscarPorCedula.setToolTipText("Buscar cliente por C.I.N�/RUC");
    tfBuscarPorCedula.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent evento)
      {
        if (ListadoClientes.tfBuscarPorCedula.getText().trim().length() > 0)
        {
          ListadoClientes.this.ConsultarClientePorNCedula();
          ListadoClientes.tfBuscarPorNombre.setEnabled(false);
        }
        else
        {
          ListadoClientes.tfBuscarPorNombre.setEnabled(true);
        }
      }
    });
    tfBuscarPorCedula.setFont(new Font("Tahoma", 1, 13));
    tfBuscarPorCedula.setBounds(377, 70, 176, 20);
    contentPane.add(tfBuscarPorCedula);
    tfBuscarPorCedula.setColumns(10);
    
    tfBuscarPorNombre = new JTextField();
    validar.BloqueoNumerico(tfBuscarPorNombre);
    tfBuscarPorNombre.setToolTipText("Buscar cliente por nombre");
    tfBuscarPorNombre.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent evento)
      {
        if (ListadoClientes.tfBuscarPorNombre.getText().trim().length() > 0)
        {
          ListadoClientes.this.ConsultarClientePorNombre();
          ListadoClientes.tfBuscarPorCedula.setEnabled(false);
          ListadoClientes.tablaDeClientes.changeSelection(0, 0, false, false);
        }
        else
        {
          ListadoClientes.tfBuscarPorCedula.setEnabled(true);
        }
      }
    });
    tfBuscarPorNombre.setFont(new Font("Tahoma", 1, 13));
    tfBuscarPorNombre.setColumns(10);
    tfBuscarPorNombre.setBounds(377, 116, 490, 20);
    contentPane.add(tfBuscarPorNombre);
    
    this.lblBuscCedula = new JLabel("Buscar por c�dula / RUC:");
    this.lblBuscCedula.setForeground(Color.WHITE);
    this.lblBuscCedula.setFont(new Font("Tahoma", 0, 15));
    this.lblBuscCedula.setBounds(377, 45, 185, 25);
    contentPane.add(this.lblBuscCedula);
    
    this.lblBuscarNombre = new JLabel("Buscar por nombre:");
    this.lblBuscarNombre.setForeground(Color.WHITE);
    this.lblBuscarNombre.setFont(new Font("Tahoma", 0, 15));
    this.lblBuscarNombre.setBounds(377, 90, 140, 25);
    contentPane.add(this.lblBuscarNombre);
    
    this.tfColor = new JTextField();
    this.tfColor.setVisible(false);
    this.tfColor.setBounds(798, 9, 86, 20);
    contentPane.add(this.tfColor);
    this.tfColor.setColumns(10);
    
    this.btnImprimir = new JButton("Imprimir");
    this.btnImprimir.setEnabled(false);
    this.btnImprimir.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        ListadoClientes.this.dispose();
        ListadoClientes.this.Imprimir();
      }
    });
    this.btnImprimir.setIcon(new ImageIcon(ListadoClientes.class.getResource("/Iconos/imprimirIcon.png")));
    this.btnImprimir.setToolTipText("Imprimir listado");
    this.btnImprimir.setFont(new Font("Tahoma", 0, 13));
    this.btnImprimir.setBounds(719, 505, 165, 56);
    contentPane.add(this.btnImprimir);
    
    this.btnProcesar = new JButton("Procesar");
    this.btnProcesar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if ((ListadoClientes.this.chbxTodos.isSelected()) && (ListadoClientes.this.comboBox.getSelectedItem().equals("C�digo")))
        {
          ListadoClientes.this.cargarTodosLosRegistrosOrdenCodigo();
        }
        else if ((ListadoClientes.this.chbxTodos.isSelected()) && (ListadoClientes.this.comboBox.getSelectedItem().equals("Nombre")))
        {
          ListadoClientes.this.cargarTodosLosRegistrosOrdenNombre();
        }
        else if (ListadoClientes.this.ValidarCondiciones())
        {
          if ((ListadoClientes.this.chbxAlfabetico.isSelected()) && (ListadoClientes.this.comboBox.getSelectedItem().equals("Nombre"))) {
            ListadoClientes.this.ConsultarPorRangoAlfabeticoOrdenAlfabetico();
          }
          if ((ListadoClientes.this.chbxAlfabetico.isSelected()) && (ListadoClientes.this.comboBox.getSelectedItem().equals("C�digo"))) {
            ListadoClientes.this.ConsultarPorRangoAlfabeticoOrdenNumerico();
          }
          if ((ListadoClientes.this.chbxNumerico.isSelected()) && (ListadoClientes.this.comboBox.getSelectedItem().equals("C�digo"))) {
            ListadoClientes.this.ConsultarPorRangoNumericoOrdenNumerico();
          }
          if ((ListadoClientes.this.chbxNumerico.isSelected()) && (ListadoClientes.this.comboBox.getSelectedItem().equals("Nombre"))) {
            ListadoClientes.this.ConsultarPorRangoNumericoOrdenAlfabetico();
          }
        }
        int filatotal = ListadoClientes.tablaDeClientes.getRowCount();
        if (filatotal > 0) {
          ListadoClientes.this.btnImprimir.setEnabled(true);
        }
      }
    });
    this.btnProcesar.setIcon(new ImageIcon(ListadoClientes.class.getResource("/Iconos/Filtro.png")));
    this.btnProcesar.setToolTipText("Procesar datos");
    this.btnProcesar.setFont(new Font("Tahoma", 0, 13));
    this.btnProcesar.setBounds(519, 505, 176, 56);
    contentPane.add(this.btnProcesar);
    
    this.comboBox = new JComboBox();
    this.comboBox.setModel(new DefaultComboBoxModel(new String[] { "C�digo", "Nombre" }));
    this.comboBox.setBounds(169, 8, 131, 21);
    contentPane.add(this.comboBox);
    
    this.lblOrden = new JLabel("Ordenar lista por:");
    this.lblOrden.setForeground(Color.WHITE);
    this.lblOrden.setFont(new Font("Tahoma", 0, 15));
    this.lblOrden.setBounds(41, 4, 140, 25);
    contentPane.add(this.lblOrden);
    
    this.tfCodigo1 = new JTextField();
    this.tfCodigo1.addKeyListener(new KeyAdapter()
    {
      public void keyTyped(KeyEvent evento)
      {
        char caracter = evento.getKeyChar();
        if (Character.isDigit(caracter))
        {
          getClass();
          ListadoClientes.this.chbxNumerico.setSelected(true);
          ListadoClientes.this.chbxAlfabetico.setSelected(false);
        }
        if (!Character.isDigit(caracter))
        {
          getClass();
          ListadoClientes.this.chbxAlfabetico.setSelected(true);
          ListadoClientes.this.chbxNumerico.setSelected(false);
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (ListadoClientes.this.tfCodigo1.getText().trim().length() > 0)
        {
          ListadoClientes.this.chbxTodos.setSelected(false);
          ListadoClientes.this.chbxTodos.setEnabled(false);
        }
        else
        {
          ListadoClientes.this.chbxTodos.setSelected(true);
          ListadoClientes.this.chbxTodos.setEnabled(true);
        }
      }
    });
    this.tfCodigo1.setBounds(75, 95, 92, 20);
    contentPane.add(this.tfCodigo1);
    this.tfCodigo1.setColumns(10);
    
    this.tfCodigo2 = new JTextField();
    this.tfCodigo2.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (ListadoClientes.this.tfCodigo2.getText().trim().length() > 0)
        {
          ListadoClientes.this.chbxTodos.setSelected(false);
          ListadoClientes.this.chbxTodos.setEnabled(false);
        }
        else
        {
          ListadoClientes.this.chbxTodos.setSelected(true);
          ListadoClientes.this.chbxTodos.setEnabled(true);
        }
      }
    });
    this.tfCodigo2.setColumns(10);
    this.tfCodigo2.setBounds(75, 126, 92, 20);
    contentPane.add(this.tfCodigo2);
    
    this.lblDesde = new JLabel("Desde:");
    this.lblDesde.setForeground(Color.WHITE);
    this.lblDesde.setFont(new Font("Tahoma", 0, 15));
    this.lblDesde.setBounds(20, 94, 60, 20);
    contentPane.add(this.lblDesde);
    
    this.lblHasta = new JLabel("Hasta:");
    this.lblHasta.setForeground(Color.WHITE);
    this.lblHasta.setFont(new Font("Tahoma", 0, 15));
    this.lblHasta.setBounds(20, 126, 60, 20);
    contentPane.add(this.lblHasta);
    
    JTabbedPane tabbedPane = new JTabbedPane(1);
    tabbedPane.setBounds(357, 38, 527, 117);
    contentPane.add(tabbedPane);
    
    this.lblRango = new JLabel("Rango alfab�tico");
    this.lblRango.setForeground(Color.WHITE);
    this.lblRango.setFont(new Font("Tahoma", 0, 15));
    this.lblRango.setBounds(111, 40, 140, 25);
    contentPane.add(this.lblRango);
    
    this.tabbedPane_2 = new JTabbedPane(1);
    this.tabbedPane_2.setBounds(10, 38, 337, 27);
    contentPane.add(this.tabbedPane_2);
    
    this.chbxNumerico = new JCheckBox("Num�rico");
    this.chbxNumerico.setForeground(Color.WHITE);
    this.chbxNumerico.setOpaque(false);
    this.chbxNumerico.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (ListadoClientes.this.chbxNumerico.isSelected())
        {
          ListadoClientes.this.chbxAlfabetico.setSelected(false);
          ListadoClientes.this.chbxTodos.setSelected(false);
        }
      }
    });
    this.chbxNumerico.setBounds(221, 125, 92, 23);
    contentPane.add(this.chbxNumerico);
    
    this.chbxAlfabetico = new JCheckBox("Alfab�tico");
    this.chbxAlfabetico.setForeground(Color.WHITE);
    this.chbxAlfabetico.setOpaque(false);
    this.chbxAlfabetico.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        if (ListadoClientes.this.chbxAlfabetico.isSelected())
        {
          ListadoClientes.this.chbxNumerico.setSelected(false);
          ListadoClientes.this.chbxTodos.setSelected(false);
        }
      }
    });
    this.chbxAlfabetico.setBounds(221, 90, 92, 23);
    contentPane.add(this.chbxAlfabetico);
    
    JTabbedPane tabbedPane_1 = new JTabbedPane(1);
    tabbedPane_1.setBounds(10, 62, 337, 93);
    contentPane.add(tabbedPane_1);
    
    this.chbxTodos = new JCheckBox("Consultar todos");
    this.chbxTodos.setForeground(Color.WHITE);
    this.chbxTodos.setOpaque(false);
    this.chbxTodos.setBounds(359, 8, 131, 23);
    contentPane.add(this.chbxTodos);
    
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
    this.lblRango.setForeground(Color.RED);
    this.lblOrden.setForeground(Color.RED);
    this.lblDesde.setForeground(Color.RED);
    this.lblHasta.setForeground(Color.RED);
    this.lblBuscCedula.setForeground(Color.RED);
    this.lblBuscarNombre.setForeground(Color.RED);
  }
  
  private void ColorBlanco()
  {
    this.lblRango.setForeground(Color.WHITE);
    this.lblOrden.setForeground(Color.WHITE);
    this.lblDesde.setForeground(Color.WHITE);
    this.lblHasta.setForeground(Color.WHITE);
    this.lblBuscCedula.setForeground(Color.WHITE);
    this.lblBuscarNombre.setForeground(Color.WHITE);
  }
  
  private void ColorGris()
  {
    this.lblRango.setForeground(Color.GRAY);
    this.lblOrden.setForeground(Color.GRAY);
    this.lblDesde.setForeground(Color.GRAY);
    this.lblHasta.setForeground(Color.GRAY);
    this.lblBuscCedula.setForeground(Color.GRAY);
    this.lblBuscarNombre.setForeground(Color.GRAY);
  }
  
  private void ColorAmarillo()
  {
    this.lblRango.setForeground(Color.YELLOW);
    this.lblOrden.setForeground(Color.YELLOW);
    this.lblDesde.setForeground(Color.YELLOW);
    this.lblHasta.setForeground(Color.YELLOW);
    this.lblBuscCedula.setForeground(Color.YELLOW);
    this.lblBuscarNombre.setForeground(Color.YELLOW);
  }
  
  private void ColorVerde()
  {
    this.lblRango.setForeground(new Color(0, 128, 0));
    this.lblOrden.setForeground(new Color(0, 128, 0));
    this.lblDesde.setForeground(new Color(0, 128, 0));
    this.lblHasta.setForeground(new Color(0, 128, 0));
    this.lblBuscCedula.setForeground(new Color(0, 128, 0));
    this.lblBuscarNombre.setForeground(new Color(0, 128, 0));
  }
  
  private void ColorAzul()
  {
    this.lblRango.setForeground(new Color(0, 0, 205));
    this.lblOrden.setForeground(new Color(0, 0, 205));
    this.lblDesde.setForeground(new Color(0, 0, 205));
    this.lblHasta.setForeground(new Color(0, 0, 205));
    this.lblBuscCedula.setForeground(new Color(0, 0, 205));
    this.lblBuscarNombre.setForeground(new Color(0, 0, 205));
  }
  
  private void ColorMarron()
  {
    this.lblRango.setForeground(new Color(139, 69, 19));
    this.lblOrden.setForeground(new Color(139, 69, 19));
    this.lblDesde.setForeground(new Color(139, 69, 19));
    this.lblHasta.setForeground(new Color(139, 69, 19));
    this.lblBuscCedula.setForeground(new Color(139, 69, 19));
    this.lblBuscarNombre.setForeground(new Color(139, 69, 19));
  }
  
  private void ColorPurpura()
  {
    this.lblRango.setForeground(new Color(148, 0, 211));
    this.lblOrden.setForeground(new Color(148, 0, 211));
    this.lblDesde.setForeground(new Color(148, 0, 211));
    this.lblHasta.setForeground(new Color(148, 0, 211));
    this.lblBuscCedula.setForeground(new Color(148, 0, 211));
    this.lblBuscarNombre.setForeground(new Color(148, 0, 211));
  }
  
  private void ColorCyan()
  {
    this.lblRango.setForeground(Color.CYAN);
    this.lblOrden.setForeground(Color.CYAN);
    this.lblDesde.setForeground(Color.CYAN);
    this.lblHasta.setForeground(Color.CYAN);
    this.lblBuscCedula.setForeground(Color.CYAN);
    this.lblBuscarNombre.setForeground(Color.CYAN);
  }
  
  private void ColorNaranja()
  {
    this.lblRango.setForeground(Color.ORANGE);
    this.lblOrden.setForeground(Color.ORANGE);
    this.lblDesde.setForeground(Color.ORANGE);
    this.lblHasta.setForeground(Color.ORANGE);
    this.lblBuscCedula.setForeground(Color.ORANGE);
    this.lblBuscarNombre.setForeground(Color.ORANGE);
  }
  
  private void ColorVerdeFluor()
  {
    this.lblRango.setForeground(Color.GREEN);
    this.lblOrden.setForeground(Color.GREEN);
    this.lblDesde.setForeground(Color.GREEN);
    this.lblHasta.setForeground(Color.GREEN);
    this.lblBuscCedula.setForeground(Color.GREEN);
    this.lblBuscarNombre.setForeground(Color.GREEN);
  }
  
  private void ColorNegro()
  {
    this.lblRango.setForeground(Color.BLACK);
    this.lblOrden.setForeground(Color.BLACK);
    this.lblDesde.setForeground(Color.BLACK);
    this.lblHasta.setForeground(Color.BLACK);
    this.lblBuscCedula.setForeground(Color.BLACK);
    this.lblBuscarNombre.setForeground(Color.BLACK);
  }
  
  public void cargarTodosLosRegistrosOrdenNombre()
  {
    listaCliente = new ArrayList();
    listaCliente = DaoClientes.consultarTodoElRegistro("cliente_nombre");
    ActualizarTablaPrincipal();
  }
  
  public void cargarTodosLosRegistrosOrdenCodigo()
  {
    listaCliente = new ArrayList();
    listaCliente = DaoClientes.consultarTodoElRegistro("cliente_codigo");
    ActualizarTablaPrincipal();
  }
  
  private static void ActualizarTablaPrincipal()
  {
    while (modelo.getRowCount() > 0) {
      modelo.removeRow(0);
    }
    String[] campos = new String[6];
    for (int i = 0; i < listaCliente.size(); i++)
    {
      modelo.addRow(campos);
      ModeloClientes cliente = (ModeloClientes)listaCliente.get(i);
      
      modelo.setValueAt(Integer.valueOf(cliente.getCodigo()), i, 0);
      modelo.setValueAt(cliente.getNombre(), i, 1);
      modelo.setValueAt(cliente.getCedula(), i, 2);
      modelo.setValueAt(cliente.getContacto(), i, 3);
      modelo.setValueAt(cliente.getDireccion(), i, 4);
    }
  }
  
  private void ConsultarClientePorNombre()
  {
    listaCliente = new ArrayList();
    listaCliente = DaoClientes.ConsultarPorNombre(tfBuscarPorNombre.getText());
    ActualizarTablaPrincipal();
  }
  
  private void ConsultarPorRangoAlfabeticoOrdenAlfabetico()
  {
    listaCliente = new ArrayList();
    listaCliente = DaoClientes.ConsultarPorRangoAlfabetico(this.tfCodigo1.getText(), this.tfCodigo2.getText().concat("ZZZZZ"), "cliente_nombre");
    ActualizarTablaPrincipal();
  }
  
  private void ConsultarPorRangoAlfabeticoOrdenNumerico()
  {
    listaCliente = new ArrayList();
    listaCliente = DaoClientes.ConsultarPorRangoAlfabetico(this.tfCodigo1.getText(), this.tfCodigo2.getText().concat("ZZZZZZ"), "cliente_codigo");
    ActualizarTablaPrincipal();
  }
  
  private void ConsultarPorRangoNumericoOrdenAlfabetico()
  {
    listaCliente = new ArrayList();
    listaCliente = DaoClientes.ConsultarPorRangoCodigo(Integer.parseInt(this.tfCodigo1.getText()), Integer.parseInt(this.tfCodigo2.getText()), "cliente_nombre");
    ActualizarTablaPrincipal();
  }
  
  private void ConsultarPorRangoNumericoOrdenNumerico()
  {
    listaCliente = new ArrayList();
    listaCliente = DaoClientes.ConsultarPorRangoCodigo(Integer.parseInt(this.tfCodigo1.getText()), Integer.parseInt(this.tfCodigo2.getText()), "cliente_codigo");
    ActualizarTablaPrincipal();
  }
  
  private void ConsultarClientePorNCedula()
  {
    listaCliente = new ArrayList();
    listaCliente = DaoClientes.consultarPorCedula(tfBuscarPorCedula.getText());
    ActualizarTablaPrincipal();
  }
  
  private void Imprimir()
  {
    Map parametros = new HashMap();
    parametros.put("rango1", this.tfCodigo1.getText());
    parametros.put("rango2", this.tfCodigo2.getText());
    parametros.put("nombre", tfBuscarPorNombre.getText().toUpperCase());
    parametros.put("cedula", tfBuscarPorCedula.getText());
    parametros.put("orden", this.comboBox.getSelectedItem());
    parametros.put("empresa", DaoConfiguraciones.NombreEmpresa());
    if (this.chbxTodos.isSelected())
    {
      parametros.put("condicion", "SI");
      parametros.put("rango1", "*P. REG.*");
      parametros.put("rango2", "*UH. REG.*");
    }
    else
    {
      parametros.put("condicion", "NO");
    }
    ArrayList<?> list = (ArrayList)listaCliente;
    ParametrosImpresion.impresionReporte(list, parametros, "ListadoClientes");
  }
  
  private boolean ValidarCondiciones()
  {
    if (this.tfCodigo1.getText().length() == 0)
    {
      JOptionPane.showMessageDialog(null, "Ingrese el rango 'desde'", "Aviso", 2);
      return false;
    }
    if (this.tfCodigo2.getText().length() == 0)
    {
      JOptionPane.showMessageDialog(null, "Ingrese el rango 'hasta'", "Aviso", 2);
      return false;
    }
    if (!this.chbxAlfabetico.isSelected()) {
      if (!this.chbxNumerico.isSelected()) {
        JOptionPane.showMessageDialog(null, "Error checkBox", "Aviso", 0);
      }
    }
    return true;
  }    
}
