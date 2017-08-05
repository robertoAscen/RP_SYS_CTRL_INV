/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Mainframe;

import com.eneri.scstock.Apariencia.FApariencia;
import com.eneri.scstock.Apariencia.FondoPrincipal;
import com.eneri.scstock.Configuraciones.Configuraciones;
import com.eneri.scstock.Devoluciones.DevolucionFacContado;
import com.eneri.scstock.Devoluciones.DevolucionFacCredito;
import com.eneri.scstock.FacturaContado.FacturaContado;
import com.eneri.scstock.FacturaContado.FormularioFacturasEmitidas;
import com.eneri.scstock.FacturaCredito.FacturaCredito;
import com.eneri.scstock.FacturaCredito.FormularioCobranzas;
import com.eneri.scstock.Formularios.Clientes;
import com.eneri.scstock.Formularios.Productos;
import com.eneri.scstock.Formularios.Proveedores;
import com.eneri.scstock.Formularios.Sucursales;
import com.eneri.scstock.Formularios.Vendedores;
import com.eneri.scstock.Herramientas.Control;
import com.eneri.scstock.Herramientas.JFlip;
import com.eneri.scstock.Informaciones.GraficoDeBarras;
import com.eneri.scstock.Informaciones.GraficoDeLineas;
import com.eneri.scstock.Informaciones.GraficoDePastel;
import com.eneri.scstock.Informaciones.MejoresProductos;
import com.eneri.scstock.Informaciones.MenuInformaciones;
import com.eneri.scstock.Informaciones.VentaAnualCliente;
import com.eneri.scstock.Informaciones.VentaDetallada;
import com.eneri.scstock.Informaciones.VentasInfoPorProducto;
import com.eneri.scstock.Informaciones.VentaMensualCliente;
import com.eneri.scstock.Inicializacion.ConfiguracionDB;
import com.eneri.scstock.Internet.Solicitud;
import com.eneri.scstock.Inventario.Inventario;
import com.eneri.scstock.Inventario.ProductosFaltantes;
import com.eneri.scstock.Inventario.ProductosSobrantes;
import com.eneri.scstock.Listados.ListadoClientes;
import com.eneri.scstock.Listados.ListadoProductos;
import com.eneri.scstock.Listados.ListadoProveedores;
import com.eneri.scstock.Modelos.ModeloCaja;
import com.eneri.scstock.Presupuesto.Presupuesto;
import com.eneri.scstock.Seguridad.AdminUsuarios;
import com.eneri.scstock.Seguridad.Login;
import com.eneri.scstock.Splash.Cambios;
import com.eneri.scstock.Tesoreria.Caja;
import com.eneri.scstock.Tesoreria.Pagos;
import com.eneri.scstock.Tesoreria.Retiros;
import com.eneri.scstock.Transferencias.Transferencias;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author RAscencio
 */
public class Principal extends JFrame
{
    
  private static final long serialVersionUID = 1L;
  public static JButton btnReiniciar;
  private FondoPrincipal contentPane;
  public static JButton btnConfiguraciones;
  public static JButton btnClientes;
  public static JButton btnProveedores;
  public static JButton btnProductos;
  public static JButton btnFacturacion;
  public static JButton btnCobranzas;
  public static JButton btnInformaciones;
  private JMenuItem mntmAdministradorDeUsuarios;
  private JMenu mnInformes;
  public static JButton btnPrivilegios;
  private JMenu mnListados;
  private JMenuItem mntmListadoDeProveedores;
  private JMenuItem mntmBaseDeDatos;
  private JMenuItem mntmSalir;
  private JMenuItem mntmVentaACrdito;
  private JMenu mnAcerca;
  private JMenuItem mntmX;
  public static JTextField tfUsuario;
  private JMenuItem mntmVentaDelDia;
  private JMenuItem mntmVentaPorProducto;
  private JMenuItem mntmProductosFaltantes;
  private JMenuItem mntmProductosSobrantes;
  private JMenuItem mntmInventar;
  private JMenuItem mntmConfiguraciones;
  private JMenuItem mntmRegistroDeCobranzas;
  private JMenuItem mntmSucursales;
  private JMenu mnGrficas;
  private JSeparator separator_2;
  private JSeparator separator_3;
  private JSeparator separator_5;
  private JMenu mnOperaciones;
  private JMenu mntmInventarios;
  private JMenuItem mntmTransferenciaProductos;
  private JMenu mnNewMenu;
  private JMenuItem mntmVentaContado;
  private JMenu mnTesoreria;
  private JMenu mnRegistros;
  private JMenuItem mntmVendedores;
  private JMenuItem mntmClientes;
  private JMenuItem mntmProveedores;
  private JMenuItem mntmProductos_1;
  private JMenuItem mntmInformeDeDeudas;
  private JMenuItem mntmInformeDeCobranzas;
  private JMenuItem mntmMejoresProductos;
  private JMenuItem mntmListadoDeClientes;
  private JMenuItem mntmListadoDeProductos;
  private JMenuItem mntmGrficoDeLineas;
  private JMenuItem mntmGrficoDeBarras;
  private JMenuItem mntmGrficoDePastel;
  private JMenuItem mntmArqueoYCierre;
  private JButton btnVCrdito;
  private JButton btnFacturas;
  private JButton btnPresupuesto;
  private JMenuItem mntmRealizarPresupuesto;
  private ModeloCaja cierreCaja;
  private JMenuItem mntmApariencia;
  private JFlip panel1;
  private boolean encendido = true;
  private JMenuItem mntmCerrarSesion;
  private JMenuItem mntmPorClientesAnual;
  private JMenuItem mntmPorProductoAnual;
  private JMenu mnInformesMensuales;
  private JMenuItem mntmPorProductoMensual;
  private JMenu mnInformesAnuales;
  private JMenuItem mntmPorClienteMensual;
  private JMenu mnDevoluciones;
  private JMenuItem mntmDevolucinventaContado;
  private JMenuItem mntmDevolucinventaA;
  private JMenuItem mntmPedidos;
  private JMenuItem mntmFacturasEmitidas;
  private JMenuItem mntmPagos;
  private JMenuItem mntmRetiros;
  private JMenu mnRecursosHumanos;
  private JMenu mnSistema;
  
  public static void main(String[] args)
  {
    if (new Control().comprobar())
    {
      Principal menu = new Principal();
      menu.setVisible(true);
    }
    else
    {
      System.exit(0);
    }
  }
  
  public Principal()
  {
    //Demo();
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent evento)
      {
        Principal.this.Salir(evento);
      }
    });
    //setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\Iconos\\IconoPrincipal.png")));
    setTitle("Menú principal");
    setMinimumSize(new Dimension(1024, 768));
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setBounds(100, 100, 1382, 783);
    
    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    setMaximizedBounds(env.getMaximumWindowBounds());
    setExtendedState(getExtendedState() | 0x6);
    
    this.contentPane = new FondoPrincipal();
    this.contentPane.setCursor(Cursor.getPredefinedCursor(0));
    this.contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
    this.contentPane.setMinimumSize(new Dimension(500, 400));
    this.contentPane.setPreferredSize(new Dimension(1024, 768));
    setContentPane(this.contentPane);
    this.contentPane.setLayout(null);
    
    btnClientes = new JButton("Clientes");
    btnClientes.setCursor(Cursor.getPredefinedCursor(12));
    btnClientes.setToolTipText("Registro de clientes");
    btnClientes.setMnemonic('C');
    btnClientes.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Clientes.main(null);
      }
    });
    btnClientes.setBounds(10, 39, 175, 55);
    this.contentPane.add(btnClientes);
    //btnClientes.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\Iconos\\Clientes.png")));
    
    btnProveedores = new JButton("Proveedores");
    btnProveedores.setCursor(Cursor.getPredefinedCursor(12));
    btnProveedores.setToolTipText("Registro de proveedores");
    btnProveedores.setMnemonic('v');
    btnProveedores.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evento)
      {
        Proveedores.main(null);
      }
    });
    //btnProveedores.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\proveedores_icono.png")));
    btnProveedores.setBounds(10, 105, 175, 55);
    this.contentPane.add(btnProveedores);
    
    btnProductos = new JButton("Productos");
    btnProductos.setCursor(Cursor.getPredefinedCursor(12));
    btnProductos.setToolTipText("Registro de productos");
    btnProductos.setMnemonic('P');
    btnProductos.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Productos.main(null);
      }
    });
    //btnProductos.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\Productos.png")));
    btnProductos.setBounds(10, 171, 175, 55);
    this.contentPane.add(btnProductos);
    
    btnFacturacion = new JButton("Facturaci�n");
    btnFacturacion.setCursor(Cursor.getPredefinedCursor(12));
    btnFacturacion.setToolTipText("Venta contado");
    btnFacturacion.setMnemonic('F');
    btnFacturacion.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FacturaContado.main(null);
      }
    });
    //btnFacturacion.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\Facturacion.png")));
    btnFacturacion.setBounds(10, 303, 175, 55);
    this.contentPane.add(btnFacturacion);
    
    btnInformaciones = new JButton("Informaciones");
    btnInformaciones.setCursor(Cursor.getPredefinedCursor(12));
    btnInformaciones.setToolTipText("Men� de informaciones");
    btnInformaciones.setMnemonic('E');
    btnInformaciones.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        MenuInformaciones.main(null);
      }
    });
    //btnInformaciones.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\InformacionIcono.png")));
    btnInformaciones.setBounds(10, 632, 175, 55);
    this.contentPane.add(btnInformaciones);
    
    btnReiniciar = new JButton("Reiniciar");
    btnReiniciar.setVisible(false);
    btnReiniciar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Principal.this.dispose();
        Cambios.main(null);
      }
    });
    btnReiniciar.setBounds(212, 106, 89, 55);
    this.contentPane.add(btnReiniciar);
    
    btnCobranzas = new JButton("Cobranzas");
    
    btnCobranzas.setCursor(Cursor.getPredefinedCursor(12));
    btnCobranzas.setToolTipText("Registro de cobranza de deudas");
    btnCobranzas.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FormularioCobranzas.main(null);
      }
    });
    //btnCobranzas.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\Cobranza.png")));
    btnCobranzas.setMnemonic('E');
    btnCobranzas.setBounds(10, 566, 175, 55);
    this.contentPane.add(btnCobranzas);
    
    btnConfiguraciones = new JButton("Vendedores");
    btnConfiguraciones.setCursor(Cursor.getPredefinedCursor(12));
    btnConfiguraciones.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Vendedores.main(null);
      }
    });
    //btnConfiguraciones.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\Vendedor.png")));
    btnConfiguraciones.setToolTipText("Registro de vendedores");
    btnConfiguraciones.setMnemonic('V');
    btnConfiguraciones.setBounds(10, 237, 175, 55);
    this.contentPane.add(btnConfiguraciones);
    
    btnPrivilegios = new JButton("Privilegios");
    btnPrivilegios.setVisible(false);
    btnPrivilegios.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Principal.this.Privilegios();
      }
    });
    btnPrivilegios.setBounds(212, 172, 89, 54);
    this.contentPane.add(btnPrivilegios);
    
    tfUsuario = new JTextField();
    tfUsuario.setVisible(false);
    tfUsuario.setBounds(215, 75, 86, 20);
    this.contentPane.add(tfUsuario);
    tfUsuario.setColumns(10);
    
    JMenuBar menuBar = new JMenuBar();
    menuBar.setBounds(0, 0, 1366, 28);
    this.contentPane.add(menuBar);
    
    this.mnSistema = new JMenu("Sistema");
    this.mnSistema.setCursor(Cursor.getPredefinedCursor(12));
    //this.mnSistema.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\Pc.png")));
    menuBar.add(this.mnSistema);
    
    this.mntmAdministradorDeUsuarios = new JMenuItem("Administrador de usuarios");
    this.mntmAdministradorDeUsuarios.setAccelerator(KeyStroke.getKeyStroke(85, 2));
    //this.mntmAdministradorDeUsuarios.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\CopyUserSettings.png")));
    this.mntmAdministradorDeUsuarios.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        AdminUsuarios.main(null);
      }
    });
    this.mntmBaseDeDatos = new JMenuItem("Base de datos");
    this.mntmBaseDeDatos.setAccelerator(KeyStroke.getKeyStroke(66, 2));
    this.mnSistema.add(this.mntmBaseDeDatos);
    //this.mntmBaseDeDatos.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\Inicializar.png")));
    this.mntmBaseDeDatos.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        ConfiguracionDB.main(null);
      }
    });
    this.separator_5 = new JSeparator();
    this.mnSistema.add(this.separator_5);
    this.mnSistema.add(this.mntmAdministradorDeUsuarios);
    
    this.mntmSalir = new JMenuItem("Salir");
    //this.mntmSalir.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\del.png")));
    this.mntmSalir.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        Principal.this.Salir(null);
      }
    });
    this.separator_2 = new JSeparator();
    this.mnSistema.add(this.separator_2);
    
    this.mntmConfiguraciones = new JMenuItem("Configuraciones");
    this.mntmConfiguraciones.setAccelerator(KeyStroke.getKeyStroke(83, 2));
    this.mnSistema.add(this.mntmConfiguraciones);
    this.mntmConfiguraciones.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Configuraciones.main(null);
      }
    });
    //this.mntmConfiguraciones.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\config.png")));
    
    this.mntmApariencia = new JMenuItem("Apariencia");
    this.mntmApariencia.setAccelerator(KeyStroke.getKeyStroke(65, 2));
    this.mnSistema.add(this.mntmApariencia);
    //this.mntmApariencia.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\Apariencia.png")));
    this.mntmApariencia.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FApariencia.main(null);
      }
    });
    this.mntmSucursales = new JMenuItem("Sucursales");
    this.mntmSucursales.setAccelerator(KeyStroke.getKeyStroke(76, 2));
    //this.mntmSucursales.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\Sucursal.png")));
    this.mnSistema.add(this.mntmSucursales);
    this.mntmSucursales.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Sucursales.main(null);
      }
    });
    JSeparator separator = new JSeparator();
    this.mnSistema.add(separator);
    
    this.mntmCerrarSesion = new JMenuItem("Cerrar sesi�n");
    this.mntmCerrarSesion.setAccelerator(KeyStroke.getKeyStroke(69, 2));
    //this.mntmCerrarSesion.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\Bloqu.png")));
    this.mntmCerrarSesion.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        int res = JOptionPane.showConfirmDialog(null, "�Est� seguro que desea cerrar sesi�n e iniciar con otro usuario?", "Cerrar sesi�n", 0);
        if (res == 0)
        {
          Principal.this.dispose();
          Login.main(null);
        }
        else {}
      }
    });
    this.mnSistema.add(this.mntmCerrarSesion);
    this.mnSistema.add(this.mntmSalir);
    
    this.mnRegistros = new JMenu("Registros");
    //this.mnRegistros.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\Registros.png")));
    menuBar.add(this.mnRegistros);
    
    this.mntmClientes = new JMenuItem("Registro de clientes");
    this.mntmClientes.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Clientes.main(null);
      }
    });
    //this.mntmClientes.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\Clientes.png")));
    this.mnRegistros.add(this.mntmClientes);
    
    this.mntmProveedores = new JMenuItem("Registro de proveedores");
    this.mntmProveedores.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Proveedores.main(null);
      }
    });
    //this.mntmProveedores.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\proveedores_icono.png")));
    this.mnRegistros.add(this.mntmProveedores);
    
    this.mntmProductos_1 = new JMenuItem("Registro de productos");
    this.mntmProductos_1.setAccelerator(KeyStroke.getKeyStroke(80, 2));
    this.mnRegistros.add(this.mntmProductos_1);
    this.mntmProductos_1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Productos.main(null);
      }
    });
    //this.mntmProductos_1.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\Productos.png")));
    
    this.mnInformes = new JMenu("Informaciones");
    //this.mnInformes.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\Consultar.png")));
    this.mnInformes.setCursor(Cursor.getPredefinedCursor(12));
    menuBar.add(this.mnInformes);
    
    this.mntmInformeDeDeudas = new JMenuItem("Informe de deudas");
    //this.mntmInformeDeDeudas.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\Informes.png")));
    this.mnInformes.add(this.mntmInformeDeDeudas);
    
    this.mntmInformeDeCobranzas = new JMenuItem("Informe de cobranzas");
    //this.mntmInformeDeCobranzas.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\sysde-cobranza1.png")));
    this.mnInformes.add(this.mntmInformeDeCobranzas);
    
    this.mntmVentaDelDia = new JMenuItem("Venta del dia");
    //this.mntmVentaDelDia.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\VentaDelDia.png")));
    this.mntmVentaDelDia.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        VentaDetallada.main(null);
      }
    });
    JSeparator separator_7 = new JSeparator();
    this.mnInformes.add(separator_7);
    this.mnInformes.add(this.mntmVentaDelDia);
    
    this.mntmVentaPorProducto = new JMenuItem("Ventas por producto");
    //this.mntmVentaPorProducto.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\VentaInfoProducto.png")));
    this.mntmVentaPorProducto.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        VentasInfoPorProducto.main(null);
      }
    });
    this.mnInformes.add(this.mntmVentaPorProducto);
    
    this.mntmMejoresProductos = new JMenuItem("Mejores productos");
    //this.mntmMejoresProductos.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\MejorProducto.png")));
    this.mntmMejoresProductos.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        MejoresProductos.main(null);
      }
    });
    this.mnInformes.add(this.mntmMejoresProductos);
    
    this.mnInformesMensuales = new JMenu("Informes mensuales");
    this.mnInformes.add(this.mnInformesMensuales);
    
    this.mntmPorClienteMensual = new JMenuItem("Por cliente");
    this.mntmPorClienteMensual.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        VentaMensualCliente.main(null);
      }
    });
    this.mnInformesMensuales.add(this.mntmPorClienteMensual);
    
    this.mntmPorProductoMensual = new JMenuItem("Por producto");
    this.mnInformesMensuales.add(this.mntmPorProductoMensual);
    
    this.mnInformesAnuales = new JMenu("Informes anuales");
    this.mnInformes.add(this.mnInformesAnuales);
    
    this.mntmPorClientesAnual = new JMenuItem("Por cliente");
    this.mntmPorClientesAnual.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        VentaAnualCliente.main(null);
      }
    });
    this.mnInformesAnuales.add(this.mntmPorClientesAnual);
    
    this.mntmPorProductoAnual = new JMenuItem("Por producto");
    this.mnInformesAnuales.add(this.mntmPorProductoAnual);
    
    this.separator_3 = new JSeparator();
    this.mnInformes.add(this.separator_3);
    
    this.mnListados = new JMenu("Listados");
    this.mnInformes.add(this.mnListados);
    //this.mnListados.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\BoxList.png")));
    this.mnListados.setCursor(Cursor.getPredefinedCursor(12));
    
    this.mntmListadoDeClientes = new JMenuItem("Listado de clientes");
    //this.mntmListadoDeClientes.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\IconList.png")));
    this.mntmListadoDeClientes.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        ListadoClientes.main(null);
      }
    });
    this.mnListados.add(this.mntmListadoDeClientes);
    
    this.mntmListadoDeProveedores = new JMenuItem("Listado de proveedores");
    //this.mntmListadoDeProveedores.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\IconList.png")));
    this.mntmListadoDeProveedores.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        ListadoProveedores.main(null);
      }
    });
    this.mnListados.add(this.mntmListadoDeProveedores);
    
    this.mntmListadoDeProductos = new JMenuItem("Listado de productos");
    //this.mntmListadoDeProductos.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\IconList.png")));
    this.mntmListadoDeProductos.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        ListadoProductos.main(null);
      }
    });
    this.mnListados.add(this.mntmListadoDeProductos);
    
    JSeparator separator_4 = new JSeparator();
    this.mnInformes.add(separator_4);
    
    this.mnGrficas = new JMenu("Informe en gr�ficas");
    //this.mnGrficas.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\48px-Crystal_Clear_mimetype_log.png")));
    this.mnInformes.add(this.mnGrficas);
    
    this.mntmGrficoDeLineas = new JMenuItem("Gr�fico de lineas");
    this.mntmGrficoDeLineas.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        GraficoDeLineas.main(null);
      }
    });
    //this.mntmGrficoDeLineas.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\GraficoLineal.png")));
    this.mnGrficas.add(this.mntmGrficoDeLineas);
    
    this.mntmGrficoDeBarras = new JMenuItem("Gr�fico de barras");
    this.mntmGrficoDeBarras.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        GraficoDeBarras.main(null);
      }
    });
    //this.mntmGrficoDeBarras.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\graficoBarras.png")));
    this.mnGrficas.add(this.mntmGrficoDeBarras);
    
    this.mntmGrficoDePastel = new JMenuItem("Gr�fico de pastel");
    this.mntmGrficoDePastel.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        GraficoDePastel.main(null);
      }
    });
    //this.mntmGrficoDePastel.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\pie-chart-149727_640.png")));
    this.mnGrficas.add(this.mntmGrficoDePastel);
    
    this.mnOperaciones = new JMenu("Operaciones");
    this.mnOperaciones.setCursor(Cursor.getPredefinedCursor(12));
    //this.mnOperaciones.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\Procesos.png")));
    menuBar.add(this.mnOperaciones);
    
    this.mntmInventarios = new JMenu("Inventarios");
    //this.mntmInventarios.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\Notepad.png")));
    this.mntmInventarios.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0) {}
    });
    this.mnOperaciones.add(this.mntmInventarios);
    
    this.mntmInventar = new JMenuItem("Realizar inventario");
    this.mntmInventar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Inventario.main(null);
      }
    });
    //this.mntmInventar.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\servicios_auditoria_icono - copia.png")));
    this.mntmInventarios.add(this.mntmInventar);
    
    this.mntmProductosFaltantes = new JMenuItem("Productos faltantes");
    this.mntmProductosFaltantes.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        ProductosFaltantes.main(null);
      }
    });
    //this.mntmProductosFaltantes.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\48px-Crystal_Clear_action_edit_remove.png")));
    this.mntmInventarios.add(this.mntmProductosFaltantes);
    
    this.mntmProductosSobrantes = new JMenuItem("Productos sobrantes");
    this.mntmProductosSobrantes.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        ProductosSobrantes.main(null);
      }
    });
    //this.mntmProductosSobrantes.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\48px-Crystal_Clear_action_edit_add.png")));
    this.mntmInventarios.add(this.mntmProductosSobrantes);
    
    JSeparator separator_6 = new JSeparator();
    this.mnOperaciones.add(separator_6);
    
    this.mnDevoluciones = new JMenu("Devoluciones");
    this.mnOperaciones.add(this.mnDevoluciones);
    this.mnDevoluciones.setCursor(Cursor.getPredefinedCursor(12));
    //this.mnDevoluciones.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\Devolucion.png")));
    
    this.mntmDevolucinventaContado = new JMenuItem("Contado");
    //this.mntmDevolucinventaContado.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\Devolver.png")));
    this.mntmDevolucinventaContado.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        DevolucionFacContado.main(null);
      }
    });
    this.mnDevoluciones.add(this.mntmDevolucinventaContado);
    
    this.mntmDevolucinventaA = new JMenuItem("Cr�dito");
    //this.mntmDevolucinventaA.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\retourenProduct.png")));
    this.mntmDevolucinventaA.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        DevolucionFacCredito.main(null);
      }
    });
    this.mnDevoluciones.add(this.mntmDevolucinventaA);
    
    JSeparator separator_8 = new JSeparator();
    this.mnOperaciones.add(separator_8);
    
    this.mntmPedidos = new JMenuItem("Pedidos");
    this.mntmPedidos.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Solicitud.main(null);
      }
    });
    //this.mntmPedidos.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\pedidosMin.png")));
    this.mnOperaciones.add(this.mntmPedidos);
    
    JSeparator separator_9 = new JSeparator();
    this.mnOperaciones.add(separator_9);
    
    this.mntmTransferenciaProductos = new JMenuItem("Transferencia de productos");
    //this.mntmTransferenciaProductos.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\TransferenciasIcon.png")));
    this.mnOperaciones.add(this.mntmTransferenciaProductos);
    this.mntmTransferenciaProductos.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Transferencias.main(null);
      }
    });
    this.mnNewMenu = new JMenu("Ventas");
    this.mnNewMenu.setCursor(Cursor.getPredefinedCursor(12));
    //this.mnNewMenu.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\CarritoCompra.png")));
    menuBar.add(this.mnNewMenu);
    
    this.mntmVentaContado = new JMenuItem("Facturaci�n contado");
    this.mntmVentaContado.setAccelerator(KeyStroke.getKeyStroke(70, 2));
    this.mnNewMenu.add(this.mntmVentaContado);
    //this.mntmVentaContado.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\Facturacion.png")));
    this.mntmVentaContado.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FacturaContado.main(null);
      }
    });
    this.mntmVentaACrdito = new JMenuItem("Facturaci�n a cr�dito");
    this.mnNewMenu.add(this.mntmVentaACrdito);
    //this.mntmVentaACrdito.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\IconDeuda.png")));
    
    this.mntmRealizarPresupuesto = new JMenuItem("Realizar presupuesto");
    this.mntmRealizarPresupuesto.setAccelerator(KeyStroke.getKeyStroke(121, 8));
    this.mntmRealizarPresupuesto.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Presupuesto.main(null);
      }
    });
    this.mnNewMenu.add(this.mntmRealizarPresupuesto);
    
    JSeparator separator_1 = new JSeparator();
    this.mnNewMenu.add(separator_1);
    
    this.mntmRegistroDeCobranzas = new JMenuItem("Registro de cobranzas");
    this.mnNewMenu.add(this.mntmRegistroDeCobranzas);
    //this.mntmRegistroDeCobranzas.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\Cobranza.png")));
    
    this.mntmFacturasEmitidas = new JMenuItem("Facturas emitidas");
    this.mnNewMenu.add(this.mntmFacturasEmitidas);
    //his.mntmFacturasEmitidas.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\Facturas.png")));
    this.mntmFacturasEmitidas.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FormularioFacturasEmitidas.main(null);
      }
    });
    this.mntmRegistroDeCobranzas.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        FormularioCobranzas.main(null);
      }
    });
    this.mntmVentaACrdito.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FacturaCredito.main(null);
      }
    });
    this.mnTesoreria = new JMenu("Tesorer�a");
    //this.mnTesoreria.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\48px-Crystal_Clear_app_mylinspire.png")));
    menuBar.add(this.mnTesoreria);
    
    this.mntmPagos = new JMenuItem("Pago de salarios");
    this.mntmPagos.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Pagos.main(null);
      }
    });
    //this.mntmPagos.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\money.png")));
    this.mnTesoreria.add(this.mntmPagos);
    
    this.mntmArqueoYCierre = new JMenuItem("Arqueo y cierre de caja");
    this.mntmArqueoYCierre.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Caja.main(null);
      }
    });
    this.mntmRetiros = new JMenuItem("Retiros de dinero");
    this.mntmRetiros.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Retiros.main(null);
      }
    });
    //this.mntmRetiros.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\Retiro.png")));
    this.mnTesoreria.add(this.mntmRetiros);
    //this.mntmArqueoYCierre.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\CierreCaja.png")));
    this.mnTesoreria.add(this.mntmArqueoYCierre);
    
    this.mnRecursosHumanos = new JMenu("RRHH");
    //this.mnRecursosHumanos.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\RecursosHumanos.png")));
    menuBar.add(this.mnRecursosHumanos);
    
    this.mntmVendedores = new JMenuItem("Registro de vendedores");
    this.mnRecursosHumanos.add(this.mntmVendedores);
    this.mntmVendedores.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Vendedores.main(null);
      }
    });
    //this.mntmVendedores.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\Vendedor.png")));
    
    this.mnAcerca = new JMenu("Acerca");
    //this.mnAcerca.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\Informacion.png")));
    this.mnAcerca.setCursor(Cursor.getPredefinedCursor(12));
    menuBar.add(this.mnAcerca);
    
    this.mntmX = new JMenuItem("M�s informaci�n");
    //this.mntmX.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\Informacion.png")));
    /*this.mntmX.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        JOptionPane.showMessageDialog(null, "---------------------------------------------------------------------------------\nSoftware desarrollado para control de stock de negocios\n---------------------------------------------------------------------------------\nDesarrollado por: >::Gabriel Gonz�lez Rojas::< \nTel�fono m�vil: [+595986 709 035] \nE-mail: gabrielg13rojas@gmail.com ", 
        
          "Informaci�n del sistema", 
          Principal.this.getDefaultCloseOperation(), new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\Galeria\\MyPc.png")));
      }
    });*/
    JMenuItem mntmFacebook = new JMenuItem("Facebook");
    mntmFacebook.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        Principal.this.AbrirURL(evt);
      }
    });
    //mntmFacebook.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\IconosMin\\Facebook.png")));
    this.mnAcerca.add(mntmFacebook);
    this.mnAcerca.add(this.mntmX);
    
    this.btnVCrdito = new JButton("V. Cr�dito");
    this.btnVCrdito.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FacturaCredito.main(null);
      }
    });
    this.btnVCrdito.setToolTipText("Venta a cr�dito");
    //this.btnVCrdito.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\Iconos\\IconDeuda.png")));
    this.btnVCrdito.setBounds(10, 369, 175, 55);
    this.contentPane.add(this.btnVCrdito);
    
    this.btnFacturas = new JButton("Facturas");
    this.btnFacturas.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FormularioFacturasEmitidas.main(null);
      }
    });
    //this.btnFacturas.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\Iconos\\Facturas.png")));
    this.btnFacturas.setToolTipText("Devoluci�n de mercader�as");
    this.btnFacturas.setMnemonic('T');
    this.btnFacturas.setBounds(10, 500, 175, 55);
    this.contentPane.add(this.btnFacturas);
    
    this.btnPresupuesto = new JButton("Presupuesto");
    //this.btnPresupuesto.setIcon(new ImageIcon(Principal.class.getResource("d:\\GIT REPOS\\SCStock\\src\\main\\resources\\image\\Iconos\\presupuesto-icono.png")));
    this.btnPresupuesto.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Presupuesto.main(null);
      }
    });
    this.btnPresupuesto.setToolTipText("Devoluci�n de mercader�as");
    this.btnPresupuesto.setMnemonic('T');
    this.btnPresupuesto.setBounds(10, 435, 175, 55);
    this.contentPane.add(this.btnPresupuesto);
    
    this.panel1 = new JFlip();
    this.panel1.setVisible(false);
    this.panel1.setBounds(0, 27, 1382, 727);
    this.panel1.setMaximumSize(getMaximumSize());
    this.contentPane.add(this.panel1);
  }
  
  private void Privilegios()
  {
    btnConfiguraciones.setEnabled(false);
    btnClientes.setEnabled(false);
    btnProveedores.setEnabled(false);
    btnProductos.setEnabled(false);
    btnFacturacion.setEnabled(false);
    btnCobranzas.setEnabled(false);
    btnInformaciones.setEnabled(false);
    this.mntmAdministradorDeUsuarios.setEnabled(false);
    this.mnInformes.setEnabled(false);
    btnPrivilegios.setEnabled(false);
    this.mnListados.setEnabled(false);
    this.mntmListadoDeProveedores.setEnabled(false);
    this.mntmBaseDeDatos.setEnabled(false);
    this.mntmSalir.setEnabled(false);
    this.mntmVentaACrdito.setEnabled(false);
    this.mnAcerca.setEnabled(false);
    this.mntmX.setEnabled(false);
    tfUsuario.setEnabled(false);
    this.mntmVentaDelDia.setEnabled(false);
    this.mntmVentaPorProducto.setEnabled(false);
    this.mntmProductosFaltantes.setEnabled(false);
    this.mntmProductosSobrantes.setEnabled(false);
    this.mntmInventar.setEnabled(false);
    this.mntmConfiguraciones.setEnabled(false);
    this.mntmRegistroDeCobranzas.setEnabled(false);
    this.mntmSucursales.setEnabled(false);
    this.mnGrficas.setEnabled(false);
    this.mnOperaciones.setEnabled(false);
    this.mntmInventarios.setEnabled(false);
    this.mntmTransferenciaProductos.setEnabled(false);
    this.mnNewMenu.setEnabled(false);
    this.mntmVentaContado.setEnabled(false);
    this.mnTesoreria.setEnabled(false);
    this.mnRegistros.setEnabled(false);
    this.mntmVendedores.setEnabled(false);
    this.mntmClientes.setEnabled(false);
    this.mntmProveedores.setEnabled(false);
    this.mntmProductos_1.setEnabled(false);
    this.mntmInformeDeDeudas.setEnabled(false);
    this.mntmInformeDeCobranzas.setEnabled(false);
    this.mntmMejoresProductos.setEnabled(false);
    this.mntmListadoDeClientes.setEnabled(false);
    this.mntmListadoDeProductos.setEnabled(false);
    this.mntmGrficoDeLineas.setEnabled(false);
    this.mntmGrficoDeBarras.setEnabled(false);
    this.mntmGrficoDePastel.setEnabled(false);
    this.mntmArqueoYCierre.setEnabled(false);
    this.btnVCrdito.setEnabled(false);
    this.btnFacturas.setEnabled(false);
    this.btnPresupuesto.setEnabled(false);
    this.mntmRealizarPresupuesto.setEnabled(false);
    this.mntmApariencia.setEnabled(false);
    this.mntmCerrarSesion.setEnabled(false);
    this.mntmPorClientesAnual.setEnabled(false);
    this.mntmPorProductoAnual.setEnabled(false);
    this.mnInformesMensuales.setEnabled(false);
    this.mntmPorProductoMensual.setEnabled(false);
    this.mnInformesAnuales.setEnabled(false);
    this.mntmPorClienteMensual.setEnabled(false);
    this.mnDevoluciones.setEnabled(false);
    this.mntmDevolucinventaContado.setEnabled(false);
    this.mntmDevolucinventaA.setEnabled(false);
    this.mntmPedidos.setEnabled(false);
    this.mntmFacturasEmitidas.setEnabled(false);
    this.mntmPagos.setEnabled(false);
    this.mntmRetiros.setEnabled(false);
    this.mnRecursosHumanos.setEnabled(false);
    this.mnSistema.setEnabled(false);
  }
  
  private void AbrirURL(ActionEvent evt)
  {
    try
    {
      String URL = "";
      URL = "http://www.facebook.com/gabrigonzaro";
      
      Desktop.getDesktop().browse(new URI(URL));
      System.out.println("Url: " + URL);
    }
    catch (URISyntaxException ex)
    {
      System.out.println(ex);
    }
    catch (IOException e)
    {
      System.out.println(e);
    }
  }
  
  private void Salir(WindowEvent evt)
  {
    int res = JOptionPane.showConfirmDialog(null, "�Desea cerrar sesi�n y salir por completo del sistema?", "Salir", 0);
    if (res == 0) {
      System.exit(0);
    } else {}
  }
  
  public void TituloUsuario()
  {
    String usuario = tfUsuario.getText();
    setTitle("Men� principal/Acceso:" + usuario);
  }
  
  /*private void Demo()
  {
    JOptionPane.showMessageDialog(null, "Esta es una versi�n de prueba, con pocas validaciones, la seguridad del acceso al sistema por parte de cualquier\nusuario puede ser vulnerable, la versi�n con licencia del sistema ofrece m�s seguridad y m�s funcionalidades\nNota: Queda totalmente prohibido el uso ilegal de este sistema, est� prohibido desmontar, descompilar, crear\nobras derivadas de la misma, traducir, vender, etc.\nLa versi�n lanzada de este sistema es solamente con fines de prueba. Autor: Gabriel Gonz�lez Rojas\nContacto: www.facebook.com/gabrigonzaro");
    
    JOptionPane.showMessageDialog(null, "Por favor, antes de abrir cualquier ventana de registros o transacci�n, configure el servidor.\nde no ser as�, puede que algunas ventanas no se abran.");
    
    JOptionPane.showMessageDialog(null, "Vea el video tutorial en YouTube con el nombre: Sistema de control de stock, facturaci�n e inventarios hecho en java (Parte 1)\npara conocer los primeros pasos antes del uso del sistema");
  }   */ 
}
