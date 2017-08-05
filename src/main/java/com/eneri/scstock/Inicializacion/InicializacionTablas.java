/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Inicializacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
/**
 *
 * @author RAscencio
 */
public class InicializacionTablas 
{
    
  private Connection conexion;
  private ResultSet resultset;
  private PreparedStatement pstm;
  private String sql;
  
  public void EliminarTuplas()
  {
    this.conexion = DriverTablas.obtenerConnection();
    try
    {
      this.sql = "DROP TABLE IF EXISTS  info_producto_utilidad_mensual, info_producto_utilidad_anual, info_cliente_anho, info_cliente_mes, factura_detalle, factura_cabecera, presupuesto_cabecera, presupuesto_detalle, factura_detcred, factura_cabcred, usuarios, clientes, proveedores, productos, colores, usuarios, fondo,informaciones, caja, marcas, familias, fondo, sucursales, inventarios, vendedores, retiros, configuraciones";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
    }
    catch (Exception excepcion)
    {
      JOptionPane.showMessageDialog(null, "Error :" + excepcion.getMessage());
      excepcion.printStackTrace();
    }
    desconectar();
  }
  
  public void CrearTuplas()
  {
    this.conexion = DriverTablas.obtenerConnection();
    try
    {
      this.sql = "CREATE TABLE IF NOT EXISTS clientes(cliente_codigo integer NOT NULL,cliente_nombre character varying(60),cliente_cedula character varying(10),cliente_direccion character varying(80),cliente_contacto character varying(20),cliente_contacto2 character varying(20),cliente_email character varying(50),cliente_descuento double precision,CONSTRAINT ClavePrimaria_ PRIMARY KEY (cliente_codigo))WITH (OIDS=FALSE);ALTER TABLE clientes OWNER TO postgres;COMMENT ON COLUMN clientes.cliente_codigo IS 'C�digo del cliente';COMMENT ON COLUMN clientes.cliente_nombre IS 'Nombre del cliente';COMMENT ON COLUMN clientes.cliente_cedula IS 'N�mero de C.I. del cliente';COMMENT ON COLUMN clientes.cliente_direccion IS 'Direcci�n del cliente';COMMENT ON COLUMN clientes.cliente_contacto IS 'N� de tel�fono del cliente';COMMENT ON COLUMN clientes.cliente_contacto2 IS 'N� de celular del cliente';COMMENT ON COLUMN clientes.cliente_email IS 'Email del cliente';";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "CREATE TABLE usuarios(usuario_codigo integer NOT NULL,usuario_nombre character varying(20),usuario_password character varying(20),CONSTRAINT ClavePrimariaUsu PRIMARY KEY (usuario_codigo))WITH (OIDS=FALSE);ALTER TABLE usuarios OWNER TO postgres;";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "CREATE TABLE marcas(marca_codigo integer NOT NULL,marca_descripcion character varying(50),marca_estado character varying(8),CONSTRAINT PKMarcas PRIMARY KEY (marca_codigo))WITH (OIDS=FALSE);ALTER TABLE marcas OWNER TO postgres;";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "CREATE TABLE familias(familia_codigo integer NOT NULL,familia_estado character varying(8),familia_descripcion character varying(50),CONSTRAINT PKFamilias PRIMARY KEY (familia_codigo))WITH (OIDS=FALSE);ALTER TABLE familias OWNER TO postgres;";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "CREATE TABLE IF NOT EXISTS proveedores(proveedor_codigo integer NOT NULL,proveedor_nombre character varying(60),proveedor_direccion character varying(80),proveedor_telefono character varying(20),proveedor_telefono2 character varying(20),proveedor_sitioweb character varying(50),proveedor_email character varying(50),CONSTRAINT ClavePrimaria PRIMARY KEY (proveedor_codigo))WITH (OIDS=FALSE);ALTER TABLE proveedores OWNER TO postgres;COMMENT ON COLUMN proveedores.proveedor_codigo IS 'C�digo del proveedor';COMMENT ON COLUMN proveedores.proveedor_nombre IS 'Nombre del proveedor';COMMENT ON COLUMN proveedores.proveedor_direccion IS 'Direcci�n del proveedor';COMMENT ON COLUMN proveedores.proveedor_telefono IS 'N� de tel�fono del proveedor';COMMENT ON COLUMN proveedores.proveedor_telefono2 IS 'N� de tel�fono2 del proveedor';COMMENT ON COLUMN proveedores.proveedor_sitioweb IS 'Sitio web del proveedor';COMMENT ON COLUMN proveedores.proveedor_email IS 'Email del proveedor';";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "CREATE TABLE IF NOT EXISTS productos(producto_codigo character varying(15),producto_descripcion character varying(80),producto_facturar character varying(2),producto_stock double precision,producto_precio_costo double precision,producto_precio_venta double precision,producto_precio_mayorista double precision,producto_precio_credito double precision,producto_cod_proveedor integer,producto_unidad_medida character varying(3),producto_cant_paquete integer,producto_iva double precision,producto_estante character varying(5),producto_cod_marca integer,producto_cod_familia integer,producto_descuento double precision,producto_observaciones character varying(200),CONSTRAINT clave_primaria PRIMARY KEY (producto_codigo),CONSTRAINT fk_familia FOREIGN KEY (producto_cod_familia)REFERENCES familias (familia_codigo) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,CONSTRAINT fk_marca FOREIGN KEY (producto_cod_marca)REFERENCES marcas (marca_codigo) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,CONSTRAINT FK_Proveedor FOREIGN KEY (producto_cod_proveedor)REFERENCES proveedores (proveedor_codigo) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION)WITH (OIDS=FALSE);ALTER TABLE productos OWNER TO postgres;COMMENT ON COLUMN productos.producto_codigo IS 'C�digo del producto';COMMENT ON COLUMN productos.producto_stock IS 'Stock actual del producto';COMMENT ON COLUMN productos.producto_descripcion IS 'Descripcion del producto';COMMENT ON COLUMN productos.producto_precio_costo IS 'Precio de costo del producto';COMMENT ON COLUMN productos.producto_precio_venta IS 'Precio de venta del producto';COMMENT ON COLUMN productos.producto_precio_mayorista IS 'Precio mayorista del producto';COMMENT ON COLUMN productos.producto_cod_proveedor IS 'Proveedor del producto';COMMENT ON COLUMN productos.producto_iva IS 'Impuesto al valor agregado';COMMENT ON COLUMN productos.producto_estante IS 'Ubicaci�n del producto';COMMENT ON COLUMN productos.producto_cant_paquete IS 'Cantidad por paquete del producto';COMMENT ON COLUMN productos.producto_precio_Credito IS 'Precio cr�dito del producto';";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "CREATE TABLE colores(color_menu_principal character varying(12),color_factura character varying(12),color_formularios character varying(12),color_insertupdate character varying(12),color_codigo integer NOT NULL DEFAULT 1,CONSTRAINT ClavePrim PRIMARY KEY (color_codigo))WITH (OIDS=FALSE);ALTER TABLE colores OWNER TO postgres;";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "CREATE TABLE fondo(fondo_menu_principal character varying(2),fondo_formularios character varying(2),fondo_factura character varying(2),fondo_insertupdate character varying(2),fondo_codigo integer NOT NULL DEFAULT 1,CONSTRAINT ClavePrimariafondo PRIMARY KEY (fondo_codigo))WITH (OIDS=FALSE);ALTER TABLE fondo OWNER TO postgres;";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "CREATE TABLE vendedores(vend_codigo integer NOT NULL,vend_nombre character varying(80),vend_cedula character varying(10),vend_contacto character varying(60),vend_salario double precision,vend_comision double precision,vend_direccion character varying(130),vend_observaciones character varying(130),vend_estado character varying(10),CONSTRAINT PKVendedor PRIMARY KEY (vend_codigo)) WITH (OIDS=FALSE);ALTER TABLE vendedores OWNER TO postgres;";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "CREATE TABLE factura_cabecera(fc_codigo integer NOT NULL,fc_cod_cliente integer,fc_total double precision,fc_fecha date,fc_estado character varying(10),fc_hora character varying(8),fc_cod_vendedor integer,fc_tiva1 double precision, fc_tiva2 double precision, fc_descuento double precision, fc_monto_descuento double precision, CONSTRAINT PKFacturaCabecera PRIMARY KEY (fc_codigo),CONSTRAINT FKCliente FOREIGN KEY (fc_cod_cliente)REFERENCES clientes (cliente_codigo) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,CONSTRAINT FKCodVendedor FOREIGN KEY (fc_cod_vendedor) REFERENCES vendedores (vend_codigo) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION) WITH (OIDS=FALSE);ALTER TABLE factura_cabecera OWNER TO postgres;COMMENT ON COLUMN factura_cabecera.fc_codigo IS 'C�digo de la factura cabecera';COMMENT ON COLUMN factura_cabecera.fc_cod_cliente IS 'C�digo del cliente en la factura cabecera';COMMENT ON COLUMN factura_cabecera.fc_fecha IS 'Fecha de emisi�n de la factura';COMMENT ON COLUMN factura_cabecera.fc_hora IS 'Hora de emisi�n de la factura';";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "CREATE TABLE factura_detalle(fd_codigo integer NOT NULL,fd_um character varying(3),fd_cod_producto character varying(15),fd_cantidad double precision,fd_punitario double precision,fd_subtotal double precision,fd_iva character varying(5),fd_cod_cabecera integer,CONSTRAINT pkfacturadetalle PRIMARY KEY (fd_codigo),CONSTRAINT FKCabecera FOREIGN KEY (fd_cod_cabecera)REFERENCES factura_cabecera (fc_codigo) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,CONSTRAINT fkproducto FOREIGN KEY (fd_cod_producto)REFERENCES productos (producto_codigo) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION) WITH (OIDS=FALSE);ALTER TABLE factura_detalle OWNER TO postgres;COMMENT ON COLUMN factura_detalle.fd_codigo IS 'C�digo del detalle de la factura';COMMENT ON COLUMN factura_detalle.fd_cod_producto IS 'C�digo del producto en el detalle de la factura';COMMENT ON COLUMN factura_detalle.fd_cantidad IS 'Cantidad vendida del producto';COMMENT ON COLUMN factura_detalle.fd_punitario IS 'Precio unitario del producto';COMMENT ON COLUMN factura_detalle.fd_subtotal IS 'Subtotal';COMMENT ON COLUMN factura_detalle.fd_cod_cabecera IS 'C�digo de la cabecera';";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "CREATE TABLE presupuesto_cabecera(fc_codigo integer NOT NULL,fc_cod_cliente integer,fc_total double precision,fc_fecha date,fc_estado character varying(10),fc_hora character varying(8),fc_cod_vendedor integer,CONSTRAINT PKPresupuestoCabecera PRIMARY KEY (fc_codigo),CONSTRAINT FKCliente FOREIGN KEY (fc_cod_cliente)REFERENCES clientes (cliente_codigo) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,CONSTRAINT FKCodVendedor FOREIGN KEY (fc_cod_vendedor) REFERENCES vendedores (vend_codigo) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION)WITH (OIDS=FALSE);ALTER TABLE factura_cabecera OWNER TO postgres;COMMENT ON COLUMN factura_cabecera.fc_codigo IS 'C�digo de la factura cabecera';COMMENT ON COLUMN factura_cabecera.fc_cod_cliente IS 'C�digo del cliente en la factura cabecera';COMMENT ON COLUMN factura_cabecera.fc_fecha IS 'Fecha de emisi�n de la factura';COMMENT ON COLUMN factura_cabecera.fc_hora IS 'Hora de emisi�n de la factura';";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "CREATE TABLE presupuesto_detalle(fd_codigo integer NOT NULL,fd_cod_producto character varying(15),fd_cantidad double precision,fd_punitario double precision,fd_subtotal double precision,fd_cod_cabecera integer,CONSTRAINT pkpresupuestodetalle PRIMARY KEY (fd_codigo),CONSTRAINT FKCabecera FOREIGN KEY (fd_cod_cabecera)REFERENCES factura_cabecera (fc_codigo) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,CONSTRAINT fkproducto FOREIGN KEY (fd_cod_producto)REFERENCES productos (producto_codigo) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION) WITH (OIDS=FALSE);ALTER TABLE factura_detalle OWNER TO postgres;COMMENT ON COLUMN factura_detalle.fd_codigo IS 'C�digo del detalle de la factura';COMMENT ON COLUMN factura_detalle.fd_cod_producto IS 'C�digo del producto en el detalle de la factura';COMMENT ON COLUMN factura_detalle.fd_cantidad IS 'Cantidad vendida del producto';COMMENT ON COLUMN factura_detalle.fd_punitario IS 'Precio unitario del producto';COMMENT ON COLUMN factura_detalle.fd_subtotal IS 'Subtotal';COMMENT ON COLUMN factura_detalle.fd_cod_cabecera IS 'C�digo de la cabecera';";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "CREATE TABLE factura_cabcred(fc_codigo integer NOT NULL,fc_cod_cliente integer,fc_total double precision,fc_saldo double precision,fc_estado character varying(10),fc_fecha date,fc_entrega double precision,fc_fechaentrega date,fc_horaentrega character varying(12),fc_hora character varying(8),fc_cod_vendedor integer, CONSTRAINT PKFacturaCreditoCabecera PRIMARY KEY (fc_codigo),CONSTRAINT FKCliente FOREIGN KEY (fc_cod_cliente)REFERENCES clientes (cliente_codigo) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION, CONSTRAINT FKCodVendedor FOREIGN KEY (fc_cod_vendedor) REFERENCES vendedores (vend_codigo) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION)WITH (OIDS=FALSE);ALTER TABLE factura_cabcred OWNER TO postgres;COMMENT ON COLUMN factura_cabcred.fc_codigo IS 'C�digo de la factura cabecera';COMMENT ON COLUMN factura_cabcred.fc_cod_cliente IS 'C�digo del cliente en la factura cabecera';COMMENT ON COLUMN factura_cabcred.fc_fecha IS 'Fecha de emisi�n de la factura';COMMENT ON COLUMN factura_cabcred.fc_total IS 'Monto total a pagar';COMMENT ON COLUMN factura_cabcred.fc_entrega IS 'Monto entregado';COMMENT ON COLUMN factura_cabcred.fc_fechaentrega IS 'Fecha de ultima entrega';COMMENT ON COLUMN factura_cabcred.fc_estado IS 'Estado de la factura';COMMENT ON COLUMN factura_cabcred.fc_horaentrega IS 'Hora de ultima entrega';COMMENT ON COLUMN factura_cabcred.fc_hora IS 'Hora de emisi�n de la factura';";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "CREATE TABLE factura_detcred(fd_codigo integer NOT NULL,fd_cod_producto character varying(15),fd_cantidad double precision,fd_punitario double precision,fd_subtotal double precision,fd_cod_cabecera integer,CONSTRAINT pkfacturadetallecabecera PRIMARY KEY (fd_codigo),CONSTRAINT FKCabecera FOREIGN KEY (fd_cod_cabecera)REFERENCES factura_cabcred (fc_codigo) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,CONSTRAINT fkproducto FOREIGN KEY (fd_cod_producto)REFERENCES productos (producto_codigo) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION) WITH (OIDS=FALSE);ALTER TABLE factura_detcred OWNER TO postgres;COMMENT ON COLUMN factura_detcred.fd_codigo IS 'C�digo del detalle de la factura';COMMENT ON COLUMN factura_detcred.fd_cod_producto IS 'C�digo del producto en el detalle de la factura';COMMENT ON COLUMN factura_detcred.fd_cantidad IS 'Cantidad vendida del producto';COMMENT ON COLUMN factura_detcred.fd_punitario IS 'Precio unitario del producto';COMMENT ON COLUMN factura_detcred.fd_subtotal IS 'Subtotal';COMMENT ON COLUMN factura_detcred.fd_cod_cabecera IS 'C�digo de la cabecera';";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "CREATE TABLE informaciones(info_codigo integer NOT NULL,info_cod_producto character varying(15),info_fecha date,info_cant_vendida double precision,info_precio_unitario double precision,info_subtotal double precision,info_utilidad double precision,info_condicion character varying(10),CONSTRAINT PKInformaciones PRIMARY KEY (info_codigo),CONSTRAINT FKInfoProductos FOREIGN KEY (info_cod_producto)REFERENCES productos (producto_codigo) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION) WITH (OIDS=FALSE);ALTER TABLE informaciones OWNER TO postgres;COMMENT ON COLUMN informaciones.info_codigo IS 'C�digo de la informaci�n';COMMENT ON COLUMN informaciones.info_cod_producto IS 'C�digo del producto en la informacion';COMMENT ON COLUMN informaciones.info_cant_vendida IS 'La cantidad vendida en el d�a';COMMENT ON COLUMN informaciones.info_fecha IS 'La fecha de la venta del producto';";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "CREATE TABLE sucursales(sucursal_codigo integer,sucursal_nombre character varying(20),sucursal_telefono character varying(20),sucursal_responsable character varying(20),sucursal_direccion character varying(30),CONSTRAINT sucursales_pkey PRIMARY KEY (sucursal_codigo)) WITH (OIDS=FALSE); ALTER TABLE sucursales OWNER TO postgres;COMMENT ON COLUMN sucursales.sucursal_codigo IS 'C�digo del sucursal';COMMENT ON COLUMN sucursales.sucursal_nombre IS 'Nombre o raz�n social de sucursal';COMMENT ON COLUMN sucursales.sucursal_telefono IS 'Contacto';COMMENT ON COLUMN sucursales.sucursal_responsable IS 'Nombre del responsable en la sucursal';COMMENT ON COLUMN sucursales.sucursal_direccion IS 'Direcci�n de la sucursal';";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "CREATE TABLE inventarios(invent_codigo integer NOT NULL, invent_codProducto character varying(15),invent_cantidadInventario double precision,invent_stockActual double precision,invent_estado character varying(10), invent_costo double precision,invent_fecha date, invent_faso double precision, CONSTRAINT inventarios_pkey PRIMARY KEY (invent_codigo),CONSTRAINT inventarios_codProducto_fkey FOREIGN KEY (invent_codProducto)REFERENCES productos (producto_codigo) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION)WITH (OIDS=FALSE);ALTER TABLE inventarios OWNER TO postgres;COMMENT ON COLUMN inventarios.invent_codigo IS 'C�digo detalle del inventario';COMMENT ON COLUMN inventarios.invent_codProducto IS 'C�digo del producto';COMMENT ON COLUMN inventarios.invent_fecha IS 'Fecha del inventario';COMMENT ON COLUMN inventarios.invent_cantidadInventario IS 'Cantidad existente del producto durante el inventario';COMMENT ON COLUMN inventarios.invent_stockActual IS 'Stock actual del producto';COMMENT ON COLUMN inventarios.invent_estado IS 'Estado actual del inventario y la existencia de los productos';COMMENT ON COLUMN inventarios.invent_faso IS 'Columna para almacenar lo que sobra o lo que falta. De ah� las siglas faSo (Faltante o sobrante). Los n�meros que aqu� se almacenan se podr�n comparar con la columna ''estado'' para saber si falta o sobra.Ejemplo: estado=FALTA - faSo=5; esto quiere decir que falta 5 productos, de la misma forma pasa con el estado ''SOBRA''. Ejemplo: estado=SOBRA - faSo=5; esto indica que est�n sobrando 5 productos.Cuando el estado pasa a OK, faSo debe estar en 0 (cero), l�gicamente si faSo est� en cero, no sobra ni falta. ';";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "CREATE TABLE configuraciones(conf_codigo integer NOT NULL,conf_nombre_empresa character varying(80),conf_telefono character varying(40),conf_fax character varying(40),conf_localidad character varying(50),conf_eslogan character varying(100),conf_contrasenha character varying(40),conf_email character varying(80),conf_desde_contado integer, conf_hasta_contado integer,conf_desde_credito integer, conf_hasta_credito integer,conf_porc_utilidad_mayorista double precision,conf_porc_utilidad_venta double precision,conf_porc_utilidad_credito double precision,conf_iva1 double precision,conf_iva2 double precision,conf_div1 double precision,conf_div2 double precision,conf_moneda character varying(20),conf_simbolo character varying(3),CONSTRAINT PKConfiguracion PRIMARY KEY (conf_codigo)) WITH (OIDS=FALSE);ALTER TABLE configuraciones OWNER TO postgres;COMMENT ON COLUMN configuraciones.conf_nombre_empresa IS 'Nombre de la empresa';COMMENT ON COLUMN configuraciones.conf_desde_contado IS 'Rango ''desde'' de la factura contado';COMMENT ON COLUMN configuraciones.conf_hasta_contado IS 'Rango ''hasta'' de la factura contado';COMMENT ON COLUMN configuraciones.conf_desde_credito IS 'Rango ''desde'' de la factura a cr�dito';COMMENT ON COLUMN configuraciones.conf_hasta_credito IS 'Rango ''hasta'' de la factura a cr�dito';";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "CREATE TABLE caja(caja_codigo integer NOT NULL,caja_total double precision,caja_retiro double precision,caja_saldo double precision,caja_fecha_cierre date,caja_hora_cierre character varying(8),CONSTRAINT clavePrimariaCaja PRIMARY KEY (caja_codigo))WITH (OIDS=FALSE);ALTER TABLE caja OWNER TO postgres;";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "CREATE TABLE retiros(ret_codigo integer NOT NULL,ret_fecha date,ret_hora character varying(8),ret_monto double precision,ret_detalle character varying(400),CONSTRAINT clavePrimariaRetiros PRIMARY KEY (ret_codigo))WITH (OIDS=FALSE);ALTER TABLE retiros OWNER TO postgres;";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "CREATE TABLE info_cliente_anho(info_codigo serial NOT NULL,info_cod_cliente integer,info_anho integer,info_total double precision,CONSTRAINT pk_info_anual PRIMARY KEY (info_codigo),CONSTRAINT fk_cliente FOREIGN KEY (info_cod_cliente)    REFERENCES clientes (cliente_codigo) MATCH SIMPLE    ON UPDATE NO ACTION ON DELETE NO ACTION) WITH (OIDS=FALSE); ALTER TABLE info_cliente_anho OWNER TO postgres;";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "CREATE TABLE info_cliente_mes(info_codigo serial NOT NULL,info_cod_cliente integer,info_mes integer,info_anho integer,info_total double precision,CONSTRAINT pk_info_mensual PRIMARY KEY (info_codigo),CONSTRAINT fk_cliente FOREIGN KEY (info_cod_cliente)    REFERENCES clientes (cliente_codigo) MATCH SIMPLE    ON UPDATE NO ACTION ON DELETE NO ACTION) WITH (OIDS=FALSE); ALTER TABLE info_cliente_anho OWNER TO postgres;";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "CREATE TABLE info_producto_utilidad_mensual(info_codigo serial NOT NULL,info_cod_producto character varying(13),info_mes integer,info_anho integer,info_producto_costo double precision,info_producto_venta double precision,info_producto_utilidad double precision,CONSTRAINT PKInfoProdMes PRIMARY KEY (info_codigo),CONSTRAINT FK_Producto FOREIGN KEY (info_cod_producto)REFERENCES productos (producto_codigo) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION)WITH (OIDS=FALSE);ALTER TABLE info_producto_utilidad_mensual OWNER TO postgres;";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "CREATE TABLE info_producto_utilidad_anual(info_codigo serial NOT NULL,info_cod_producto character varying(13),info_anho integer,info_producto_costo double precision,info_producto_venta double precision,info_producto_utilidad double precision,CONSTRAINT PKInfoProductoanual PRIMARY KEY (info_codigo),CONSTRAINT FK_Productos FOREIGN KEY (info_cod_producto)REFERENCES productos (producto_codigo) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION)WITH (OIDS=FALSE);ALTER TABLE info_producto_utilidad_anual OWNER TO postgres;";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "INSERT INTO fondo(fondo_menu_principal,fondo_factura,fondo_formularios,fondo_insertupdate)VALUES ('24', '24', '24', '24');";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "INSERT INTO colores(color_menu_principal,color_factura,color_insertupdate,color_formularios)VALUES ('Blanco', 'Blanco', 'Blanco', 'Blanco');";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      this.sql = "INSERT INTO usuarios(usuario_codigo,usuario_nombre,usuario_password) VALUES ('1', 'ADMINISTRADOR', 'jmvrw');";
      
      this.pstm = null;
      this.pstm = this.conexion.prepareStatement(this.sql);
      this.pstm.executeUpdate();
      
      JOptionPane.showMessageDialog(null, "�Inicializaci�n de base de datos concluida satisfactoriamente!");
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(null, "Error :" + e.getMessage());
      e.printStackTrace();
    }
    desconectar();
  }
  
  public void desconectar()
  {
    try
    {
      if (this.resultset != null) {
        this.resultset.close();
      }
      if (this.pstm != null) {
        this.pstm.close();
      }
      if (this.conexion != null) {
        this.conexion.close();
      }
    }
    catch (Exception excepcion)
    {
      JOptionPane.showMessageDialog(null, "Exception: " + excepcion.getMessage());
      excepcion.printStackTrace();
    }
  }
  
  public static void main(String[] args)
  {
    InicializacionTablas inicializacion = new InicializacionTablas();
    inicializacion.EliminarTuplas();
    inicializacion.CrearTuplas();
  }
}
