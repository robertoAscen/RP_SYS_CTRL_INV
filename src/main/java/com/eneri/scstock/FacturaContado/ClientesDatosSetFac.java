/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.FacturaContado;

import com.eneri.scstock.Apariencia.FondoTransacciones;
import com.eneri.scstock.FacturaCredito.FacturaCredito;
import com.eneri.scstock.Herramientas.Validaciones;
import com.eneri.scstock.Modelos.ModeloClientes;
import com.eneri.scstock.Objetos.DaoClientes;
import com.eneri.scstock.Presupuesto.Presupuesto;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAscencio
 */
public class ClientesDatosSetFac extends JDialog
{
    private List<ModeloClientes> listaCliente;
  private ModeloClientes clientes;
  private JTable tbClientes;
  private DefaultTableModel dtmCliente = new DefaultTableModel(null, 
    new String[] { "C�digo", "N� de c�dula/RUC", "Nombre y apellido" })
  {
    boolean[] columnEditables = new boolean[3];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private final JPanel contentPanel = new JPanel();
  private JTextField tfNumeroCedula;
  private JTextField tfNombreCliente;
  private JButton btnAgregar;
  private JScrollPane scrollPaneClientes;
  private JLabel lblCedula;
  private JLabel lblNombre;
  private JTextField tfColor;
  public static JLabel lblFormularioCliente;
  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        try
        {
          ClientesDatosSetFac dialog = new ClientesDatosSetFac();
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
  
  public ClientesDatosSetFac()
  {
    setIconImage(Toolkit.getDefaultToolkit().getImage(FacturaContado.class.getResource("/Iconos/Lupa.png")));
    setForeground(Color.BLACK);
    setBackground(Color.BLACK);
    setTitle("Buscar cliente");
    setBounds(100, 100, 500, 500);
    this.contentPanel.setLayout(null);
    
    FondoTransacciones contentPanel = new FondoTransacciones();
    contentPanel.setForeground(Color.BLACK);
    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPanel);
    contentPanel.setLayout(null);
    
    this.tfNumeroCedula = new JTextField();
    Validaciones val = new Validaciones();
    val.BloqueoAlfabetico(this.tfNumeroCedula);
    this.tfNumeroCedula.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        ClientesDatosSetFac.this.consultarPorCedula();
        ClientesDatosSetFac.this.tbClientes.changeSelection(0, 0, false, false);
      }
    });
    this.tfNumeroCedula.setBounds(123, 18, 161, 20);
    contentPanel.add(this.tfNumeroCedula);
    this.tfNumeroCedula.setColumns(10);
    
    this.tfNombreCliente = new JTextField();
    val.BloqueoNumerico(this.tfNombreCliente);
    this.tfNombreCliente.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        ClientesDatosSetFac.this.consultarPorNombre();
      }
    });
    this.tfNombreCliente.setColumns(10);
    this.tfNombreCliente.setBounds(122, 46, 349, 20);
    contentPanel.add(this.tfNombreCliente);
    
    this.scrollPaneClientes = new JScrollPane();
    this.scrollPaneClientes.setBounds(10, 77, 461, 310);
    contentPanel.add(this.scrollPaneClientes);
    
    this.tbClientes = new JTable();
    this.tbClientes.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        int filaseleccionada = ClientesDatosSetFac.this.tbClientes.getSelectedRow();
        String nombre = ClientesDatosSetFac.this.tbClientes.getValueAt(filaseleccionada, 2).toString();
      }
    });
    this.tbClientes.getTableHeader().setReorderingAllowed(false);
    this.tbClientes.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          ClientesDatosSetFac.this.btnAgregar.doClick();
        }
      }
    });
    this.tbClientes.setBackground(SystemColor.inactiveCaptionText);
    this.tbClientes.setForeground(SystemColor.text);
    this.tbClientes.setModel(this.dtmCliente);
    this.scrollPaneClientes.setViewportView(this.tbClientes);
    
    this.lblCedula = new JLabel("   N� C�dula:");
    this.lblCedula.setBackground(Color.BLACK);
    this.lblCedula.setForeground(Color.WHITE);
    this.lblCedula.setBounds(47, 21, 74, 14);
    contentPanel.add(this.lblCedula);
    
    this.lblNombre = new JLabel("Nombre y apellido:");
    this.lblNombre.setBackground(Color.BLACK);
    this.lblNombre.setForeground(Color.WHITE);
    this.lblNombre.setBounds(10, 49, 111, 14);
    contentPanel.add(this.lblNombre);
    
    this.btnAgregar = new JButton("Agregar");
    this.btnAgregar.setFont(new Font("Tahoma", 1, 15));
    this.btnAgregar.setIcon(new ImageIcon(ClientesDatosSetFac.class.getResource("/Iconos/Aceptar.png")));
    this.btnAgregar.setMnemonic(10);
    this.btnAgregar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        ClientesDatosSetFac.this.TransferirDatos();
      }
    });
    this.btnAgregar.setBounds(286, 398, 186, 53);
    contentPanel.add(this.btnAgregar);
    
    this.tfColor = new JTextField();
    this.tfColor.setVisible(false);
    this.tfColor.setBounds(10, 398, 86, 20);
    contentPanel.add(this.tfColor);
    this.tfColor.setColumns(10);
    
    lblFormularioCliente = new JLabel("Contado");
    lblFormularioCliente.setVisible(false);
    lblFormularioCliente.setBounds(317, 21, 46, 14);
    contentPanel.add(lblFormularioCliente);
    consultar();
    this.tbClientes.changeSelection(0, 0, false, false);
    
    ColorBlancoFacturaLabels();
  }
  
  private void ColorBlancoFacturaLabels()
  {
    this.lblCedula.setForeground(Color.WHITE);
    this.lblNombre.setForeground(Color.WHITE);
  }
  
  private void TransferirDatos()
  {
    int seleccionfila = this.tbClientes.getSelectedRow();
    try
    {
      if (seleccionfila < 0)
      {
        JOptionPane.showMessageDialog(null, "Seleccione un registro de cliente");
      }
      else if (lblFormularioCliente.getText().equals("Contado"))
      {
        String codigo = this.tbClientes.getValueAt(seleccionfila, 0).toString();
        
        this.clientes = DaoClientes.ConsultaClientePFactura(Integer.parseInt(codigo));
        
        FacturaContado.tfCedulaRUC.setText(this.clientes.getCedula());
        FacturaContado.tfNombreCliente.setText(this.clientes.getNombre());
        FacturaContado.tfDireccion.setText(this.clientes.getDireccion());
        FacturaContado.tfTelefono.setText(this.clientes.getContacto());
        FacturaContado.tfCodCliente.setText(String.valueOf(this.clientes.getCodigo()));
        
        double descuento = this.clientes.getDescuento();
        if ((FacturaContado.tfTotal.getText().trim().length() > 1) && 
          (descuento > 0.0D))
        {
          FacturaContado.chckbxDescuento.setEnabled(true);
          FacturaContado.tfDesMax.setText(String.valueOf(descuento));
          FacturaContado.descMax = descuento;
        }
        dispose();
      }
      else if (lblFormularioCliente.getText().equals("Credito"))
      {
        String codigo = this.tbClientes.getValueAt(seleccionfila, 0).toString();
        
        this.clientes = DaoClientes.ConsultaClientePFactura(Integer.parseInt(codigo));
        
        FacturaCredito.tfCedulaRUC.setText(this.clientes.getCedula());
        FacturaCredito.tfNombreCliente.setText(this.clientes.getNombre());
        FacturaCredito.tfDireccion.setText(this.clientes.getDireccion());
        FacturaCredito.tfTelefono.setText(this.clientes.getContacto());
        FacturaCredito.tfCodCliente.setText(String.valueOf(this.clientes.getCodigo()));
        
        dispose();
      }
      else if (lblFormularioCliente.getText().equals("Presupuesto"))
      {
        String codigo = this.tbClientes.getValueAt(seleccionfila, 0).toString();
        
        this.clientes = DaoClientes.ConsultaClientePFactura(Integer.parseInt(codigo));
        
        Presupuesto.tfCedulaRUC.setText(this.clientes.getCedula());
        Presupuesto.tfNombreCliente.setText(this.clientes.getNombre());
        Presupuesto.tfDireccion.setText(this.clientes.getDireccion());
        Presupuesto.tfTelefono.setText(this.clientes.getContacto());
        Presupuesto.tfCodCliente.setText(String.valueOf(this.clientes.getCodigo()));
        
        dispose();
      }
    }
    catch (Exception n)
    {
      n.printStackTrace();
    }
  }
  
  private void consultar()
  {
    this.listaCliente = new ArrayList();
    this.listaCliente = DaoClientes.consultarTodoElRegistro("cliente_nombre");
    cargarTabla();
  }
  
  private void cargarTabla()
  {
    while (this.dtmCliente.getRowCount() > 0) {
      this.dtmCliente.removeRow(0);
    }
    String[] campos = new String[3];
    for (int i = 0; i < this.listaCliente.size(); i++)
    {
      this.dtmCliente.addRow(campos);
      ModeloClientes cliente = (ModeloClientes)this.listaCliente.get(i);
      
      this.dtmCliente.setValueAt(Integer.valueOf(cliente.getCodigo()), i, 0);
      this.dtmCliente.setValueAt(cliente.getCedula(), i, 1);
      this.dtmCliente.setValueAt(cliente.getNombre(), i, 2);
    }
  }
  
  private void consultarPorNombre()
  {
    this.listaCliente = new ArrayList();
    this.listaCliente = DaoClientes.ConsultarPorNombre(this.tfNombreCliente.getText());
    cargarTabla();
    if (this.tfNombreCliente.getText().equals("")) {
      this.tfNumeroCedula.setEnabled(true);
    } else {
      this.tfNumeroCedula.setEnabled(false);
    }
  }
  
  private void consultarPorCedula()
  {
    this.listaCliente = new ArrayList();
    this.listaCliente = DaoClientes.consultarPorCedula(this.tfNumeroCedula.getText());
    cargarTabla();
    if (this.tfNumeroCedula.getText().equals("")) {
      this.tfNombreCliente.setEnabled(true);
    } else {
      this.tfNombreCliente.setEnabled(false);
    }
  }    
}
