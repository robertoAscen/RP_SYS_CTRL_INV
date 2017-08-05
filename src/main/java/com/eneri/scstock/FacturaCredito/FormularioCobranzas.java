/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.FacturaCredito;

import com.eneri.scstock.Apariencia.Colores;
import com.eneri.scstock.Apariencia.FondoTransacciones;
import com.eneri.scstock.Herramientas.Validaciones;
import com.eneri.scstock.Modelos.ModeloFacturaCredito;
import com.eneri.scstock.Modelos.ModeloInformaciones;
import com.eneri.scstock.Modelos.ModeloProductos;
import com.eneri.scstock.Objetos.DaoCobranzas;
import com.eneri.scstock.Objetos.DaoInformaciones;
import com.eneri.scstock.Objetos.DaoProductos;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author RAscencio
 */
public class FormularioCobranzas extends JDialog
{
    private final JPanel contentPanel = new JPanel();
  private JLabel lblSaldo;
  public static List<ModeloFacturaCredito> listaFacturaCabecera;
  public static List<ModeloFacturaCredito> listaFacturaDetalle;
  
  public static void main(String[] args)
  {
    try
    {
      FormularioCobranzas dialog = new FormularioCobranzas();
      dialog.setLocationRelativeTo(null);
      dialog.setModal(true);
      dialog.setVisible(true);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public static DefaultTableModel modelocabecera = new DefaultTableModel(null, new String[] { "N� Fact.", "N�Ced/RUC", "Nombre y apellido del cliente", "Fecha de compra", "Hora", "Estado" })
  {
    boolean[] columnEditables = new boolean[6];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  public static DefaultTableModel modelodetalle = new DefaultTableModel(null, new String[] { "N� Det.", "Cantidad", "C�d.", "Descripci�n", "P. Unit.", "Subtotal" })
  {
    boolean[] columnEditables = new boolean[6];
    
    public boolean isCellEditable(int row, int column)
    {
      return this.columnEditables[column];
    }
  };
  private JTable tablaCabecera;
  private JLabel lblDeuda;
  private JTable tablaDetalle;
  private JButton btnDetalle;
  private JScrollPane scrollPaneDetalle;
  private JTextField tfSaldo;
  private JButton btnCobrar;
  private JScrollPane scrollPaneCabecera;
  private JTextField tfFechaUEntrega;
  private JTextField tfHoraUEntrega;
  private JTextField tfTotalCompra;
  private JTextField tfMontoUEntrega;
  private JLabel lblTotalCompra;
  private JButton btnImprimirFactura;
  private JLabel lblNFactura;
  private JTextField tfBuscarPorCedula;
  private JLabel lblCliente;
  private JTextField tfBuscarPorNombre;
  private JTextField tfBuscarPorNFactura;
  public static JButton btnActualizar;
  private JTabbedPane tabbedPane_2;
  private JLabel lblFechaUltEntrega;
  private JLabel lblHoraUltEntrega;
  private JLabel lblMontoDeUltEntrega;
  private JTextField tfColor;
  private JButton btnAnular;
  private JLabel lblNewLabel;
  private JTabbedPane tabbedPane_3;
  
  public FormularioCobranzas()
  {
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent evt)
      {
        FormularioCobranzas.this.Salir(evt);
      }
    });
    setResizable(false);
    setBounds(100, 100, 1189, 643);
    setTitle("Registro de cobranzas");
    setDefaultCloseOperation(0);
    setIconImage(Toolkit.getDefaultToolkit().getImage(FormularioCobranzas.class.getResource("/Galeria/IconoCobranza.png")));
    FondoTransacciones contentPane = new FondoTransacciones();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    this.btnCobrar = new JButton("Cobrar");
    this.btnCobrar.setToolTipText("Cobranza total o parcial de la deuda.");
    this.btnCobrar.setEnabled(false);
    this.btnCobrar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        int seleccion = FormularioCobranzas.this.tablaCabecera.getSelectedRow();
        String estado = String.valueOf(FormularioCobranzas.this.tablaCabecera.getValueAt(seleccion, 5));
        if (seleccion < 0)
        {
          JOptionPane.showMessageDialog(null, "Seleccione una factura para continuar con la operaci�n.");
        }
        else if (estado.equals("EN DEUDA"))
        {
          FormularioCobranzas.this.FormularioCalcularSaldoYUPDT();
          FormularioCobranzas.this.btnCobrar.setEnabled(false);
          FormularioCobranzas.this.btnAnular.setEnabled(false);
        }
      }
    });
    this.btnCobrar.setIcon(new ImageIcon(FormularioCobranzas.class.getResource("/Iconos/Cobrar.png")));
    this.btnCobrar.setMnemonic('C');
    this.btnCobrar.setBounds(697, 117, 151, 49);
    contentPane.add(this.btnCobrar);
    
    this.btnImprimirFactura = new JButton("Imprimir");
    this.btnImprimirFactura.setEnabled(false);
    this.btnImprimirFactura.setIcon(new ImageIcon(FormularioCobranzas.class.getResource("/Iconos/imprimirIcon.png")));
    
    this.btnImprimirFactura.setMnemonic('P');
    this.btnImprimirFactura.setBounds(697, 244, 151, 49);
    contentPane.add(this.btnImprimirFactura);
    
    this.tfSaldo = new JTextField();
    this.tfSaldo.setBackground(Color.GRAY);
    this.tfSaldo.setEditable(false);
    this.tfSaldo.setBounds(936, 240, 225, 42);
    this.tfSaldo.setFont(new Font("Microsoft Yi Baiti", 1, 45));
    contentPane.add(this.tfSaldo);
    this.tfSaldo.setColumns(10);
    
    this.lblSaldo = new JLabel("SALDO:");
    this.lblSaldo.setForeground(Color.BLACK);
    this.lblSaldo.setFont(new Font("Tahoma", 1, 15));
    this.lblSaldo.setBounds(872, 240, 66, 42);
    getContentPane().add(this.lblSaldo);
    
    this.scrollPaneCabecera = new JScrollPane();
    this.scrollPaneCabecera.setBounds(10, 53, 677, 240);
    contentPane.add(this.scrollPaneCabecera);
    
    this.tablaCabecera = new JTable();
    this.tablaCabecera.setForeground(Color.WHITE);
    this.tablaCabecera.setBackground(new Color(51, 51, 51));
    this.tablaCabecera.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        FormularioCobranzas.this.LimpiarDetalle();
        FormularioCobranzas.this.tfMontoUEntrega.setToolTipText("");
        FormularioCobranzas.this.tfSaldo.setToolTipText("");
        FormularioCobranzas.this.tfTotalCompra.setToolTipText("");
        FormularioCobranzas.this.btnCobrar.setEnabled(false);
        FormularioCobranzas.this.btnDetalle.setEnabled(true);
        FormularioCobranzas.this.tfHoraUEntrega.setToolTipText("");
      }
    });
    this.scrollPaneCabecera.setViewportView(this.tablaCabecera);
    this.tablaCabecera.setModel(modelocabecera);
    this.tablaCabecera.getColumnModel().getColumn(0).setPreferredWidth(54);
    this.tablaCabecera.getColumnModel().getColumn(1).setPreferredWidth(61);
    this.tablaCabecera.getColumnModel().getColumn(2).setPreferredWidth(246);
    this.tablaCabecera.getColumnModel().getColumn(3).setPreferredWidth(79);
    this.tablaCabecera.getColumnModel().getColumn(4).setPreferredWidth(51);
    this.tablaCabecera.getColumnModel().getColumn(5).setPreferredWidth(72);
    this.tablaCabecera.getTableHeader().setReorderingAllowed(false);
    
    this.lblDeuda = new JLabel("Cobranza");
    this.lblDeuda.setFont(new Font("Tahoma", 0, 20));
    this.lblDeuda.setForeground(Color.BLACK);
    this.lblDeuda.setBounds(974, 11, 95, 38);
    contentPane.add(this.lblDeuda);
    
    this.scrollPaneDetalle = new JScrollPane();
    this.scrollPaneDetalle.setBounds(10, 300, 838, 304);
    contentPane.add(this.scrollPaneDetalle);
    
    this.tablaDetalle = new JTable();
    this.tablaDetalle.setForeground(Color.WHITE);
    this.tablaDetalle.setBackground(new Color(51, 51, 51));
    this.tablaDetalle.getTableHeader().setReorderingAllowed(false);
    this.scrollPaneDetalle.setViewportView(this.tablaDetalle);
    this.tablaDetalle.setModel(modelodetalle);
    this.tablaDetalle.getColumnModel().getColumn(0).setPreferredWidth(30);
    this.tablaDetalle.getColumnModel().getColumn(1).setPreferredWidth(22);
    this.tablaDetalle.getColumnModel().getColumn(2).setPreferredWidth(41);
    this.tablaDetalle.getColumnModel().getColumn(3).setPreferredWidth(394);
    this.tablaDetalle.getColumnModel().getColumn(4).setPreferredWidth(48);
    
    this.btnDetalle = new JButton("Ver detalle");
    this.btnDetalle.setEnabled(false);
    this.btnDetalle.setMnemonic('D');
    this.btnDetalle.setToolTipText("Ver el detalle de la deuda");
    this.btnDetalle.setIcon(new ImageIcon(FormularioCobranzas.class.getResource("/Iconos/icono-N�mina.png")));
    this.btnDetalle.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        int seleccion = FormularioCobranzas.this.tablaCabecera.getSelectedRow();
        String estado = String.valueOf(FormularioCobranzas.this.tablaCabecera.getValueAt(seleccion, 5));
        if (estado.equals("EN DEUDA"))
        {
          FormularioCobranzas.this.btnCobrar.setEnabled(true);
          FormularioCobranzas.this.ConsultarDetalle();
        }
        if (estado.equals("PAGADO"))
        {
          FormularioCobranzas.this.btnAnular.setEnabled(false);
          FormularioCobranzas.this.btnCobrar.setEnabled(false);
          FormularioCobranzas.this.ConsultarDetalle();
        }
        if (estado.equals("ANULADO"))
        {
          FormularioCobranzas.this.btnAnular.setEnabled(false);
          FormularioCobranzas.this.btnCobrar.setEnabled(false);
          FormularioCobranzas.this.ConsultarDetalle();
        }
        else
        {
          FormularioCobranzas.this.btnAnular.setEnabled(true);
        }
        FormularioCobranzas.this.btnDetalle.setEnabled(false);
        if (FormularioCobranzas.this.tfHoraUEntrega.getText().equals("SIN ENTREGA")) {
          FormularioCobranzas.this.tfHoraUEntrega.setToolTipText("No se ha registrado ninguna entrega");
        }
      }
    });
    this.btnDetalle.setBounds(697, 53, 151, 53);
    contentPane.add(this.btnDetalle);
    
    this.tfFechaUEntrega = new JTextField();
    this.tfFechaUEntrega.setFont(new Font("Dialog", 1, 15));
    this.tfFechaUEntrega.setEditable(false);
    this.tfFechaUEntrega.setBounds(1035, 69, 126, 25);
    contentPane.add(this.tfFechaUEntrega);
    this.tfFechaUEntrega.setColumns(10);
    
    this.tfHoraUEntrega = new JTextField();
    this.tfHoraUEntrega.setFont(new Font("Dialog", 1, 15));
    this.tfHoraUEntrega.setEditable(false);
    this.tfHoraUEntrega.setColumns(10);
    this.tfHoraUEntrega.setBounds(1035, 115, 126, 25);
    contentPane.add(this.tfHoraUEntrega);
    
    this.tfTotalCompra = new JTextField();
    this.tfTotalCompra.setFont(new Font("Dialog", 1, 15));
    this.tfTotalCompra.setEditable(false);
    this.tfTotalCompra.setColumns(10);
    this.tfTotalCompra.setBounds(1035, 202, 126, 25);
    contentPane.add(this.tfTotalCompra);
    
    this.tfMontoUEntrega = new JTextField();
    this.tfMontoUEntrega.setFont(new Font("Dialog", 1, 15));
    this.tfMontoUEntrega.setEditable(false);
    this.tfMontoUEntrega.setColumns(10);
    this.tfMontoUEntrega.setBounds(1034, 160, 126, 25);
    contentPane.add(this.tfMontoUEntrega);
    
    this.lblFechaUltEntrega = new JLabel("Fecha de �lt. entrega:");
    this.lblFechaUltEntrega.setFont(new Font("Dialog", 1, 15));
    this.lblFechaUltEntrega.setBounds(869, 69, 167, 23);
    contentPane.add(this.lblFechaUltEntrega);
    
    this.lblHoraUltEntrega = new JLabel("  Hora de �lt. entrega:");
    this.lblHoraUltEntrega.setFont(new Font("Dialog", 1, 15));
    this.lblHoraUltEntrega.setBounds(869, 117, 167, 23);
    contentPane.add(this.lblHoraUltEntrega);
    
    this.lblMontoDeUltEntrega = new JLabel("Monto de �lt. entrega:");
    this.lblMontoDeUltEntrega.setFont(new Font("Dialog", 1, 15));
    this.lblMontoDeUltEntrega.setBounds(869, 161, 178, 25);
    contentPane.add(this.lblMontoDeUltEntrega);
    
    this.lblTotalCompra = new JLabel("Total compra:");
    this.lblTotalCompra.setFont(new Font("Dialog", 1, 15));
    this.lblTotalCompra.setBounds(926, 203, 120, 25);
    contentPane.add(this.lblTotalCompra);
    
    this.lblNFactura = new JLabel("N� de factura:");
    this.lblNFactura.setForeground(Color.GRAY);
    this.lblNFactura.setFont(new Font("Tahoma", 1, 15));
    this.lblNFactura.setBounds(20, 17, 135, 25);
    contentPane.add(this.lblNFactura);
    
    this.tfBuscarPorCedula = new JTextField();
    this.tfBuscarPorCedula.setFont(new Font("Tahoma", 1, 13));
    Validaciones validar = new Validaciones();
    validar.BloqueoAlfabetico(this.tfBuscarPorCedula);
    this.tfBuscarPorCedula.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (FormularioCobranzas.this.tfBuscarPorCedula.getText().trim().length() != 0)
        {
          FormularioCobranzas.this.tfBuscarPorNFactura.setText("");
          FormularioCobranzas.this.tfBuscarPorNFactura.setEnabled(false);
          FormularioCobranzas.this.tfBuscarPorNombre.setText("");
          FormularioCobranzas.this.tfBuscarPorNombre.setEnabled(false);
        }
        else
        {
          FormularioCobranzas.this.tfBuscarPorNombre.setEnabled(true);
          FormularioCobranzas.this.tfBuscarPorNFactura.setEnabled(true);
        }
        FormularioCobranzas.this.ConsultarPorCedula();
      }
    });
    this.tfBuscarPorCedula.setColumns(10);
    this.tfBuscarPorCedula.setBounds(331, 16, 128, 25);
    contentPane.add(this.tfBuscarPorCedula);
    
    this.lblCliente = new JLabel("Cliente:");
    this.lblCliente.setFont(new Font("Tahoma", 1, 15));
    this.lblCliente.setBounds(264, 17, 95, 20);
    contentPane.add(this.lblCliente);
    
    this.tfBuscarPorNombre = new JTextField();
    this.tfBuscarPorNombre.setFont(new Font("Tahoma", 1, 13));
    validar.BloqueoNumerico(this.tfBuscarPorNombre);
    this.tfBuscarPorNombre.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        if (FormularioCobranzas.this.tfBuscarPorNombre.getText().trim().length() != 0)
        {
          FormularioCobranzas.this.tfBuscarPorNFactura.setText("");
          FormularioCobranzas.this.tfBuscarPorNFactura.setEnabled(false);
          FormularioCobranzas.this.tfBuscarPorCedula.setText("");
          FormularioCobranzas.this.tfBuscarPorCedula.setEnabled(false);
        }
        else
        {
          FormularioCobranzas.this.tfBuscarPorCedula.setEnabled(true);
          FormularioCobranzas.this.tfBuscarPorNFactura.setEnabled(true);
        }
        FormularioCobranzas.this.ConsultarPorNombre();
      }
    });
    this.tfBuscarPorNombre.setColumns(10);
    this.tfBuscarPorNombre.setBounds(469, 17, 368, 25);
    contentPane.add(this.tfBuscarPorNombre);
    
    this.tfBuscarPorNFactura = new JTextField();
    this.tfBuscarPorNFactura.setFont(new Font("Tahoma", 1, 13));
    validar.BloqueoAlfabetico(this.tfBuscarPorNFactura);
    this.tfBuscarPorNFactura.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent evento)
      {
        if (evento.getKeyCode() == 10) {
          if (FormularioCobranzas.this.tfBuscarPorNFactura.getText().trim().length() == 0) {
            FormularioCobranzas.this.ConsultarTodasLasFacturasCabecera();
          } else {
            FormularioCobranzas.this.ConsultarPorNFactura();
          }
        }
      }
      
      public void keyReleased(KeyEvent arg0)
      {
        if (FormularioCobranzas.this.tfBuscarPorNFactura.getText().trim().length() != 0)
        {
          FormularioCobranzas.this.tfBuscarPorCedula.setText("");
          FormularioCobranzas.this.tfBuscarPorCedula.setEnabled(false);
          FormularioCobranzas.this.tfBuscarPorNombre.setText("");
          FormularioCobranzas.this.tfBuscarPorNombre.setEnabled(false);
        }
        else
        {
          FormularioCobranzas.this.tfBuscarPorCedula.setEnabled(true);
          FormularioCobranzas.this.tfBuscarPorNombre.setEnabled(true);
        }
      }
    });
    this.tfBuscarPorNFactura.setColumns(10);
    this.tfBuscarPorNFactura.setBounds(126, 17, 128, 24);
    contentPane.add(this.tfBuscarPorNFactura);
    
    btnActualizar = new JButton("Act.");
    btnActualizar.setVisible(false);
    btnActualizar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FormularioCobranzas.this.ActualizacionGral();
      }
    });
    btnActualizar.setMnemonic('P');
    btnActualizar.setBounds(789, 221, 59, 42);
    contentPane.add(btnActualizar);
    
    JTabbedPane tabbedPane = new JTabbedPane(1);
    tabbedPane.setBounds(10, 12, 838, 37);
    contentPane.add(tabbedPane);
    
    JTabbedPane tabbedPane_1 = new JTabbedPane(1);
    tabbedPane_1.setBounds(857, 53, 316, 241);
    contentPane.add(tabbedPane_1);
    
    this.btnAnular = new JButton("Anular");
    this.btnAnular.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FormularioCobranzas.this.AnularFactura();
        FormularioCobranzas.this.btnAnular.setEnabled(false);
        FormularioCobranzas.btnActualizar.doClick();
      }
    });
    this.btnAnular.setIcon(new ImageIcon(FormularioCobranzas.class.getResource("/Iconos/Anular.png")));
    this.btnAnular.setMnemonic('P');
    this.btnAnular.setEnabled(false);
    this.btnAnular.setBounds(697, 177, 151, 56);
    contentPane.add(this.btnAnular);
    
    this.tabbedPane_2 = new JTabbedPane(1);
    this.tabbedPane_2.setBounds(857, 11, 316, 37);
    contentPane.add(this.tabbedPane_2);
    
    this.tfColor = new JTextField();
    this.tfColor.setVisible(false);
    this.tfColor.setBounds(879, 11, 86, 20);
    contentPane.add(this.tfColor);
    this.tfColor.setColumns(10);
    
    this.lblNewLabel = new JLabel("");
    this.lblNewLabel.setIcon(new ImageIcon(FormularioCobranzas.class.getResource("/Galeria/Pago.png")));
    this.lblNewLabel.setBounds(867, 308, 306, 285);
    contentPane.add(this.lblNewLabel);
    
    this.tabbedPane_3 = new JTabbedPane(1);
    this.tabbedPane_3.setBounds(857, 300, 316, 304);
    contentPane.add(this.tabbedPane_3);
    ConsultarTodasLasFacturasCabecera();
    ColorBlancoFacturaLabels();
  }
  
  private void CapturarColor()
  {
    String codigocolor = "1";
    Colores color = Colores.ConsutarColorFactura(codigocolor);
    this.tfColor.setText(color.getColor());
  }
  
  private void DetectarColor()
  {
    if (this.tfColor.getText().equals("Amarillo")) {
      ColorAmarilloFacturaLabels();
    }
    if (this.tfColor.getText().equals("Azul")) {
      ColorAzulFacturaLabels();
    }
    if (this.tfColor.getText().equals("Cyan")) {
      ColorCyanFacturaLabels();
    }
    if (this.tfColor.getText().equals("Gris")) {
      ColorGrisFacturaLabels();
    }
    if (this.tfColor.getText().equals("Naranja")) {
      ColorNaranjaFacturaLabels();
    }
    if (this.tfColor.getText().equals("Negro")) {
      ColorNegroFacturaLabels();
    }
    if (this.tfColor.getText().equals("P�rpura")) {
      ColorPurpuraFacturaLabels();
    }
    if (this.tfColor.getText().equals("Rojo")) {
      ColorRojoFacturaLabels();
    }
    if (this.tfColor.getText().equals("Marr�n")) {
      ColorMarronFacturaLabels();
    }
    if (this.tfColor.getText().equals("Verde fl�or")) {
      ColorVerdeFluorFacturaLabels();
    }
    if (this.tfColor.getText().equals("Verde")) {
      ColorVerdeFacturaLabels();
    }
    if (this.tfColor.getText().equals("Blanco")) {
      ColorBlancoFacturaLabels();
    }
  }
  
  private void ColorRojoFacturaLabels()
  {
    this.lblCliente.setForeground(Color.RED);
    this.lblDeuda.setForeground(Color.RED);
    this.lblFechaUltEntrega.setForeground(Color.RED);
    this.lblHoraUltEntrega.setForeground(Color.RED);
    this.lblMontoDeUltEntrega.setForeground(Color.RED);
    this.lblNFactura.setForeground(Color.RED);
    this.lblSaldo.setForeground(Color.RED);
    this.lblTotalCompra.setForeground(Color.RED);
  }
  
  private void ColorBlancoFacturaLabels()
  {
    this.lblCliente.setForeground(Color.WHITE);
    this.lblDeuda.setForeground(Color.WHITE);
    this.lblFechaUltEntrega.setForeground(Color.WHITE);
    this.lblHoraUltEntrega.setForeground(Color.WHITE);
    this.lblMontoDeUltEntrega.setForeground(Color.WHITE);
    this.lblNFactura.setForeground(Color.WHITE);
    this.lblSaldo.setForeground(Color.WHITE);
    this.lblTotalCompra.setForeground(Color.WHITE);
  }
  
  private void ColorGrisFacturaLabels()
  {
    this.lblCliente.setForeground(Color.GRAY);
    this.lblDeuda.setForeground(Color.GRAY);
    this.lblFechaUltEntrega.setForeground(Color.GRAY);
    this.lblHoraUltEntrega.setForeground(Color.GRAY);
    this.lblMontoDeUltEntrega.setForeground(Color.GRAY);
    this.lblNFactura.setForeground(Color.GRAY);
    this.lblSaldo.setForeground(Color.GRAY);
    this.lblTotalCompra.setForeground(Color.GRAY);
  }
  
  private void ColorAmarilloFacturaLabels()
  {
    this.lblCliente.setForeground(Color.YELLOW);
    this.lblDeuda.setForeground(Color.YELLOW);
    this.lblFechaUltEntrega.setForeground(Color.YELLOW);
    this.lblHoraUltEntrega.setForeground(Color.YELLOW);
    this.lblMontoDeUltEntrega.setForeground(Color.YELLOW);
    this.lblNFactura.setForeground(Color.YELLOW);
    this.lblSaldo.setForeground(Color.YELLOW);
    this.lblTotalCompra.setForeground(Color.YELLOW);
  }
  
  private void ColorVerdeFacturaLabels()
  {
    this.lblCliente.setForeground(new Color(0, 128, 0));
    this.lblDeuda.setForeground(new Color(0, 128, 0));
    this.lblFechaUltEntrega.setForeground(new Color(0, 128, 0));
    this.lblHoraUltEntrega.setForeground(new Color(0, 128, 0));
    this.lblMontoDeUltEntrega.setForeground(new Color(0, 128, 0));
    this.lblNFactura.setForeground(new Color(0, 128, 0));
    this.lblSaldo.setForeground(new Color(0, 128, 0));
    this.lblTotalCompra.setForeground(new Color(0, 128, 0));
  }
  
  private void ColorAzulFacturaLabels()
  {
    this.lblCliente.setForeground(new Color(0, 0, 205));
    this.lblDeuda.setForeground(new Color(0, 0, 205));
    this.lblFechaUltEntrega.setForeground(new Color(0, 0, 205));
    this.lblHoraUltEntrega.setForeground(new Color(0, 0, 205));
    this.lblMontoDeUltEntrega.setForeground(new Color(0, 0, 205));
    this.lblNFactura.setForeground(new Color(0, 0, 205));
    this.lblSaldo.setForeground(new Color(0, 0, 205));
    this.lblTotalCompra.setForeground(new Color(0, 0, 205));
  }
  
  private void ColorMarronFacturaLabels()
  {
    this.lblCliente.setForeground(new Color(139, 69, 19));
    this.lblDeuda.setForeground(new Color(139, 69, 19));
    this.lblFechaUltEntrega.setForeground(new Color(139, 69, 19));
    this.lblHoraUltEntrega.setForeground(new Color(139, 69, 19));
    this.lblMontoDeUltEntrega.setForeground(new Color(139, 69, 19));
    this.lblNFactura.setForeground(new Color(139, 69, 19));
    this.lblSaldo.setForeground(new Color(139, 69, 19));
    this.lblTotalCompra.setForeground(new Color(139, 69, 19));
  }
  
  private void ColorPurpuraFacturaLabels()
  {
    this.lblCliente.setForeground(new Color(148, 0, 211));
    this.lblDeuda.setForeground(new Color(148, 0, 211));
    this.lblFechaUltEntrega.setForeground(new Color(148, 0, 211));
    this.lblHoraUltEntrega.setForeground(new Color(148, 0, 211));
    this.lblMontoDeUltEntrega.setForeground(new Color(148, 0, 211));
    this.lblNFactura.setForeground(new Color(148, 0, 211));
    this.lblSaldo.setForeground(new Color(148, 0, 211));
    this.lblTotalCompra.setForeground(new Color(148, 0, 211));
  }
  
  private void ColorCyanFacturaLabels()
  {
    this.lblCliente.setForeground(Color.CYAN);
    this.lblDeuda.setForeground(Color.CYAN);
    this.lblFechaUltEntrega.setForeground(Color.CYAN);
    this.lblHoraUltEntrega.setForeground(Color.CYAN);
    this.lblMontoDeUltEntrega.setForeground(Color.CYAN);
    this.lblNFactura.setForeground(Color.CYAN);
    this.lblSaldo.setForeground(Color.CYAN);
    this.lblTotalCompra.setForeground(Color.CYAN);
  }
  
  private void ColorNaranjaFacturaLabels()
  {
    this.lblCliente.setForeground(Color.ORANGE);
    this.lblDeuda.setForeground(Color.ORANGE);
    this.lblFechaUltEntrega.setForeground(Color.ORANGE);
    this.lblHoraUltEntrega.setForeground(Color.ORANGE);
    this.lblMontoDeUltEntrega.setForeground(Color.ORANGE);
    this.lblNFactura.setForeground(Color.ORANGE);
    this.lblSaldo.setForeground(Color.ORANGE);
    this.lblTotalCompra.setForeground(Color.ORANGE);
  }
  
  private void ColorVerdeFluorFacturaLabels()
  {
    this.lblCliente.setForeground(Color.GREEN);
    this.lblDeuda.setForeground(Color.GREEN);
    this.lblFechaUltEntrega.setForeground(Color.GREEN);
    this.lblHoraUltEntrega.setForeground(Color.GREEN);
    this.lblMontoDeUltEntrega.setForeground(Color.GREEN);
    this.lblNFactura.setForeground(Color.GREEN);
    this.lblSaldo.setForeground(Color.GREEN);
    this.lblTotalCompra.setForeground(Color.GREEN);
  }
  
  private void ColorNegroFacturaLabels()
  {
    this.lblCliente.setForeground(Color.BLACK);
    this.lblDeuda.setForeground(Color.BLACK);
    this.lblFechaUltEntrega.setForeground(Color.BLACK);
    this.lblHoraUltEntrega.setForeground(Color.BLACK);
    this.lblMontoDeUltEntrega.setForeground(Color.BLACK);
    this.lblNFactura.setForeground(Color.BLACK);
    this.lblSaldo.setForeground(Color.BLACK);
    this.lblTotalCompra.setForeground(Color.BLACK);
  }
  
  public void ConsultarTodasLasFacturasCabecera()
  {
    listaFacturaCabecera = new ArrayList();
    listaFacturaCabecera = DaoCobranzas.consultarFacturaCabecera();
    ActualizarTablaCabecera();
  }
  
  private void ConsultarPorNFactura()
  {
    listaFacturaCabecera = new ArrayList();
    listaFacturaCabecera = DaoCobranzas.ConsultarPorNFactura(Integer.parseInt(this.tfBuscarPorNFactura.getText()));
    ActualizarTablaCabecera();
  }
  
  private void ConsultarPorCedula()
  {
    listaFacturaCabecera = new ArrayList();
    listaFacturaCabecera = DaoCobranzas.ConsultarPorCedula(this.tfBuscarPorCedula.getText());
    ActualizarTablaCabecera();
  }
  
  private void ConsultarPorNombre()
  {
    listaFacturaCabecera = new ArrayList();
    listaFacturaCabecera = DaoCobranzas.ConsultarPorNombre(this.tfBuscarPorNombre.getText());
    ActualizarTablaCabecera();
  }
  
  private static void ActualizarTablaCabecera()
  {
    while (modelocabecera.getRowCount() > 0) {
      modelocabecera.removeRow(0);
    }
    String[] campos = new String[6];
    for (int i = 0; i < listaFacturaCabecera.size(); i++)
    {
      modelocabecera.addRow(campos);
      ModeloFacturaCredito cobranza = (ModeloFacturaCredito)listaFacturaCabecera.get(i);
      
      modelocabecera.setValueAt(Integer.valueOf(cobranza.getCodigoCabecera()), i, 0);
      modelocabecera.setValueAt(cobranza.getCliente().getCedula(), i, 1);
      modelocabecera.setValueAt(cobranza.getCliente().getNombre(), i, 2);
      modelocabecera.setValueAt(cobranza.getFecha(), i, 3);
      modelocabecera.setValueAt(cobranza.getHora(), i, 4);
      modelocabecera.setValueAt(cobranza.getEstado(), i, 5);
    }
  }
  
  private void ConsultarMasDetalles()
  {
    int seleccion = this.tablaCabecera.getSelectedRow();
    String codigo = this.tablaCabecera.getValueAt(seleccion, 0).toString();
    ModeloFacturaCredito cobranza = DaoCobranzas.ConsutarMasDetalles(Integer.parseInt(codigo));
    
    this.tfMontoUEntrega.setText(String.valueOf(cobranza.getEntrega()));
    if (this.tfMontoUEntrega.getText().equals("0"))
    {
      this.tfFechaUEntrega.setText("SIN ENTREGA");
      this.tfHoraUEntrega.setText("SIN ENTREGA");
    }
    else
    {
      this.tfFechaUEntrega.setText(cobranza.getFechaUEntrega());
      this.tfHoraUEntrega.setText(cobranza.getHoraEntrega());
    }
    this.tfTotalCompra.setText(String.valueOf(cobranza.getTotal()));
    this.tfSaldo.setText(String.valueOf(cobranza.getSaldo()));
  }
  
  public void ConsultarDetalle()
  {
    listaFacturaDetalle = new ArrayList();
    int seleccion = this.tablaCabecera.getSelectedRow();
    if (seleccion == -1)
    {
      JOptionPane.showMessageDialog(null, "Seleccione una factura para visualizar sus detalles.");
    }
    else
    {
      String valor = String.valueOf(modelocabecera.getValueAt(seleccion, 0).toString());
      int codigo = Integer.parseInt(valor);
      listaFacturaDetalle = DaoCobranzas.consultarDetalleDeLaFactura(codigo);
      ActualizarTablaDetalle();
      ConsultarMasDetalles();
    }
  }
  
  private static void ActualizarTablaDetalle()
  {
    while (modelodetalle.getRowCount() > 0) {
      modelodetalle.removeRow(0);
    }
    String[] campos = new String[6];
    for (int i = 0; i < listaFacturaDetalle.size(); i++)
    {
      modelodetalle.addRow(campos);
      ModeloFacturaCredito cobranza = (ModeloFacturaCredito)listaFacturaDetalle.get(i);
      
      modelodetalle.setValueAt(Integer.valueOf(cobranza.getCodigoDetalle()), i, 0);
      modelodetalle.setValueAt(Double.valueOf(cobranza.getCantidad()), i, 1);
      modelodetalle.setValueAt(cobranza.getProducto().getCodigo(), i, 2);
      modelodetalle.setValueAt(cobranza.getProducto().getDescripcion(), i, 3);
      modelodetalle.setValueAt(Double.valueOf(cobranza.getPrecioUnitario()), i, 4);
      modelodetalle.setValueAt(Double.valueOf(cobranza.getSubtotal()), i, 5);
    }
  }
  
  public static String hora()
  {
    Calendar Cal = Calendar.getInstance();
    String hora = String.valueOf(Cal.get(11));
    String minuto = String.valueOf(Cal.get(12));
    String segundo = String.valueOf(Cal.get(13));
    
    String hora1 = "";
    String minuto1 = "";
    String segundo1 = "";
    String horacompleta = "";
    if (hora.length() == 1) {
      hora1 = "0" + Cal.get(11);
    } else {
      hora1 = hora;
    }
    if (minuto.length() == 1) {
      minuto1 = "0" + Cal.get(12);
    } else {
      minuto1 = minuto;
    }
    if (segundo.length() == 1) {
      segundo1 = "0" + Cal.get(13);
    } else {
      segundo1 = segundo;
    }
    horacompleta = hora1 + ":" + minuto1 + ":" + segundo1;
    
    return horacompleta;
  }
  
  private void FormularioCalcularSaldoYUPDT()
  {
    if (this.tfHoraUEntrega.getText().equals("SIN ENTREGA"))
    {
      CalculoSaldo cambio = new CalculoSaldo();
      int seleccion = this.tablaCabecera.getSelectedRow();
      String codigoFactura = String.valueOf(modelocabecera.getValueAt(seleccion, 0).toString());
      if (seleccion == -1)
      {
        JOptionPane.showMessageDialog(null, "Seleccione una factura para cobrar.");
      }
      else
      {
        String valor = this.tfSaldo.getText();
        CalculoSaldo.tfDeudaActual.setText(valor);
        CalculoSaldo.tfEntrega.setText("0");
        CalculoSaldo.tfEntrega.requestFocus();
        CalculoSaldo.tfEntrega.selectAll();
        CalculoSaldo.lblCodigoFactura.setText(codigoFactura);
        cambio.setLocationRelativeTo(null);
        cambio.setModal(true);
        cambio.setVisible(true);
      }
    }
    else
    {
      CalculoSaldo cambio = new CalculoSaldo();
      int seleccion = this.tablaCabecera.getSelectedRow();
      String codigoFactura = String.valueOf(modelocabecera.getValueAt(seleccion, 0).toString());
      if (seleccion == -1)
      {
        JOptionPane.showMessageDialog(null, "Seleccione una factura para cobrar.");
      }
      else
      {
        String valor = this.tfSaldo.getText();
        CalculoSaldo.tfDeudaActual.setText(valor);
        CalculoSaldo.tfEntrega.setText("0");
        CalculoSaldo.tfEntrega.requestFocus();
        CalculoSaldo.tfEntrega.selectAll();
        CalculoSaldo.lblCodigoFactura.setText(codigoFactura);
        cambio.setLocationRelativeTo(null);
        cambio.setModal(true);
        cambio.setVisible(true);
      }
    }
  }
  
  private void ActualizacionGral()
  {
    try
    {
      this.tfFechaUEntrega.setText("");
      this.tfHoraUEntrega.setText("");
      this.tfSaldo.setText("");
      this.tfMontoUEntrega.setText("");
      this.tfTotalCompra.setText("");
      this.tfFechaUEntrega.setText("");
      this.tfHoraUEntrega.setText("");
      this.tfSaldo.setText("");
      this.tfMontoUEntrega.setText("");
      this.tfTotalCompra.setText("");
      
      ConsultarTodasLasFacturasCabecera();
      
      int filaTotal = this.tablaDetalle.getRowCount();
      while (filaTotal >= 1)
      {
        modelodetalle.removeRow(0);
        this.btnDetalle.setEnabled(false);
        filaTotal--;
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  private void LimpiarDetalle()
  {
    int filaTotal = this.tablaDetalle.getRowCount();
    while (filaTotal >= 1)
    {
      modelodetalle.removeRow(0);
      filaTotal--;
    }
    this.tfFechaUEntrega.setText("");
    this.tfHoraUEntrega.setText("");
    this.tfSaldo.setText("");
    this.tfMontoUEntrega.setText("");
    this.tfTotalCompra.setText("");
    this.tfFechaUEntrega.setText("");
    this.tfHoraUEntrega.setText("");
    this.tfSaldo.setText("");
    this.tfMontoUEntrega.setText("");
    this.tfTotalCompra.setText("");
  }
  
  private void AnularFactura()
  {
    int seleccion = this.tablaCabecera.getSelectedRow();
    int yes = JOptionPane.showConfirmDialog(this, "�Est� seguro que desea anular esta deuda?", "Aviso", 0);
    if (yes == 0)
    {
      int codigoCabecera = Integer.parseInt(String.valueOf(this.tablaCabecera.getValueAt(seleccion, 0)));
      ModeloFacturaCredito f = new ModeloFacturaCredito();
      f.setCodigoCabecera(codigoCabecera);
      f.setEstado("ANULADO");
      f.setSaldo(0.0D);
      DaoCobranzas.AnularDeuda(f);
      EliminarDatosFacturaDetalleBD();
      this.btnCobrar.setEnabled(false);
    }
  }
  
  private void EliminarDatosFacturaDetalleBD()
  {
    int filaTotal = this.tablaDetalle.getRowCount();
    while (filaTotal >= 1)
    {
      double stockactual = 0.0D;
      String codigoproducto = String.valueOf(this.tablaDetalle.getValueAt(0, 2));
      ModeloProductos productos = DaoProductos.ConsutaParaModificar(String.valueOf(codigoproducto));
      double cantidadLLevada = Double.parseDouble(String.valueOf(this.tablaDetalle.getValueAt(0, 1)));
      stockactual = productos.getStock();
      double updatestock = stockactual + cantidadLLevada;
      
      ModeloProductos p = new ModeloProductos();
      
      p.setCodigo(String.valueOf(codigoproducto));
      p.setStock(updatestock);
      
      DaoProductos.ActualizarStock(p);
      
      double cantvendidabd = 0.0D;
      double update = 0.0D;
      
      double subtotaladescontar = Double.parseDouble(String.valueOf(this.tablaDetalle.getValueAt(0, 5)));
      double subtotalact = 0.0D;
      double subtotalbd = 0.0D;
      int selecfac = this.tablaCabecera.getSelectedRow();
      String fechaCompra = String.valueOf(this.tablaCabecera.getValueAt(selecfac, 3));
      ModeloInformaciones i = DaoInformaciones.ConsultaForUpdate(codigoproducto, fechaCompra, "CREDITO");
      cantvendidabd = i.getCantVendida();
      update = cantvendidabd - cantidadLLevada;
      subtotalbd = i.getSubtotal();
      update = cantvendidabd - cantidadLLevada;
      
      subtotalact = subtotalbd - subtotaladescontar;
      if (update == 0.0D)
      {
        DaoInformaciones.EliminarInformacion(codigoproducto, fechaCompra, "CREDITO");
      }
      else
      {
        ModeloInformaciones info = new ModeloInformaciones();
        info.setCantVendida(update);
        info.setCodigoProducto(codigoproducto);
        info.setSubtotal(subtotalact);
        DaoInformaciones.ActualizarCantidadVendida(info, fechaCompra, "CREDITO");
      }
      modelodetalle.removeRow(0);
      filaTotal--;
    }
  }
  
  private void LimpiarDetalleAlSalir()
  {
    int filaTotal = this.tablaDetalle.getRowCount();
    while (filaTotal >= 1)
    {
      modelodetalle.removeRow(0);
      filaTotal--;
    }
    this.tfFechaUEntrega.setText("");
    this.tfHoraUEntrega.setText("");
    this.tfSaldo.setText("");
    this.tfMontoUEntrega.setText("");
    this.tfTotalCompra.setText("");
    this.tfFechaUEntrega.setText("");
    this.tfHoraUEntrega.setText("");
    this.tfSaldo.setText("");
    this.tfMontoUEntrega.setText("");
    this.tfTotalCompra.setText("");
  }
  
  private void Salir(WindowEvent evt)
  {
    dispose();
    LimpiarDetalleAlSalir();
  }    
}
