/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.FlujoDetalleDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.FlujoDetalleDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlujoDetalleDAO
/*     */ {
/*     */   ResultSet rs;
/*  32 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  50 */       this.dat.close();
/*     */     }
/*  52 */     catch (Exception e) {
/*  53 */       Utilidades.writeError("FlujoDetalleDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FlujoDetalleDTO next() {
/*     */     try {
/*  64 */       if (this.rs.next()) {
/*  65 */         return leerRegistro();
/*     */       }
/*     */     }
/*  68 */     catch (Exception e) {
/*  69 */       e.printStackTrace();
/*  70 */       Utilidades.writeError("FlujoDetalleDAO:next ", e);
/*     */     } 
/*  72 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FlujoDetalleDTO leerRegistro() {
/*     */     try {
/*  82 */       FlujoDetalleDTO reg = new FlujoDetalleDTO();
/*     */       
/*  84 */       reg.setCodigoFlujo(this.rs.getInt("codigo_flujo"));
/*  85 */       reg.setSecuencia(this.rs.getInt("secuencia"));
/*  86 */       reg.setServicioInicio(this.rs.getInt("servicio_inicio"));
/*  87 */       reg.setCodigoEstado(this.rs.getInt("codigo_estado"));
/*  88 */       reg.setServicioDestino(this.rs.getInt("servicio_destino"));
/*  89 */       reg.setNombreProcedimiento(this.rs.getString("nombre_procedimiento"));
/*  90 */       reg.setCorreoDestino(this.rs.getString("correo_destino"));
/*  91 */       reg.setEnviarSolicitud(this.rs.getString("enviar_solicitud"));
/*  92 */       reg.setMismoProveedor(this.rs.getString("ind_mismo_proveedor"));
/*  93 */       reg.setMismoCliente(this.rs.getString("ind_mismo_cliente"));
/*  94 */       reg.setEstado(this.rs.getString("estado"));
/*  95 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  96 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  97 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  98 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  99 */       reg.setNombreServicioInicio(this.rs.getString("nombre_servicio_inicio"));
/* 100 */       reg.setNombreCodigoEstado(this.rs.getString("nombre_codigo_estado"));
/* 101 */       reg.setNombreServicioDestino(this.rs.getString("nombre_servicio_destino"));
/* 102 */       reg.setNombreEstado(this.rs.getString("nombre_estado"));
/* 103 */       reg.setCaracteristica(this.rs.getInt("caracteristica"));
/* 104 */       reg.setCaracteristicaValor(this.rs.getInt("valor_caracteristica"));
/* 105 */       reg.setCaracteristicaCorreo(this.rs.getInt("caracteristica_correo"));
/* 106 */       reg.setNombreCaracteristica(this.rs.getString("nombre_caracteristica"));
/* 107 */       reg.setDescripcionValor(this.rs.getString("descripcion_valor"));
/* 108 */       reg.setMetodoSeleccionProveedor(this.rs.getString("metodo_seleccion_proveedor"));
/* 109 */       reg.setIndCorreoCliente(this.rs.getString("ind_correo_clientes"));
/*     */       
/*     */       try {
/* 112 */         reg.setEnviar_hermana(this.rs.getString("enviar_hermana"));
/* 113 */         reg.setEnviar_si_hermana_cerrada(this.rs.getString("enviar_si_hermana_cerrada"));
/* 114 */         reg.setInd_cliente_inicial(this.rs.getString("ind_cliente_inicial"));
/*     */ 
/*     */       
/*     */       }
/* 118 */       catch (Exception e) {}
/*     */ 
/*     */ 
/*     */       
/* 122 */       return reg;
/*     */     }
/* 124 */     catch (Exception e) {
/* 125 */       e.printStackTrace();
/* 126 */       Utilidades.writeError("FlujoDetalleDAO:leerRegistro ", e);
/*     */       
/* 128 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<FlujoDetalleDTO> cargarTodos(int codigoFlujo) {
/* 138 */     Collection<FlujoDetalleDTO> resultados = new ArrayList<FlujoDetalleDTO>();
/*     */     try {
/* 140 */       String s = "select t.codigo_flujo,t.secuencia,t.servicio_inicio,r1.descripcion as nombre_servicio_inicio,t.accion,t.codigo_estado,r3.descripcion as nombre_codigo_estado,t.servicio_destino,r4.descripcion as nombre_servicio_destino,t.nombre_procedimiento,t.correo_destino,t.enviar_solicitud,t.ind_mismo_proveedor,t.ind_mismo_cliente,t.estado,t.caracteristica,t.valor_caracteristica,t.caracteristica_correo,m5.descripcion as nombre_estado,c.DESCRIPCION nombre_caracteristica,cv.DESCRIPCION descripcion_valor,t.metodo_seleccion_proveedor,t.ind_correo_clientes,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from wkf_detalle t  left join SERVICIOS r1 on (r1.CODIGO=t.servicio_inicio) left join ESTADOS r3 on (r3.codigo=t.codigo_estado) left join SERVICIOS r4 on (r4.CODIGO=t.servicio_destino) left join sis_multivalores m5 on (m5.tabla='ESTADO_REGISTRO' and m5.valor=t.estado) LEFT   JOIN CARACTERISTICAS c ON (t.Caracteristica = c.CODIGO) LEFT   JOIN  CARACTERISTICAS_VALOR cv ON (t.CARACTERISTICA=cv.CARACTERISTICA AND t.VALOR_CARACTERISTICA = cv.VALOR) where t.codigo_flujo=" + codigoFlujo + " order by t.secuencia";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 178 */       boolean rtaDB = this.dat.parseSql(s);
/* 179 */       if (!rtaDB) {
/* 180 */         return resultados;
/*     */       }
/* 182 */       this.rs = this.dat.getResultSet();
/* 183 */       while (this.rs.next()) {
/* 184 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 187 */     catch (Exception e) {
/* 188 */       e.printStackTrace();
/* 189 */       Utilidades.writeError("FlujoDetalleDAO:cargarTodos ", e);
/*     */     } 
/* 191 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FlujoDetalleDTO cargarRegistro(int codigoFlujo, int secuencia) {
/*     */     try {
/* 201 */       String s = "select t.codigo_flujo,t.secuencia,t.servicio_inicio,r1.descripcion as nombre_servicio_inicio,t.accion,t.codigo_estado,r3.descripcion as nombre_codigo_estado,t.servicio_destino,r4.descripcion as nombre_servicio_destino,t.nombre_procedimiento,t.correo_destino,t.enviar_solicitud,t.ind_mismo_proveedor,t.ind_mismo_cliente,t.estado,t.caracteristica,t.valor_caracteristica,t.caracteristica_correo,m5.descripcion as nombre_estado,c.DESCRIPCION nombre_caracteristica,cv.DESCRIPCION descripcion_valor,t.metodo_seleccion_proveedor,t.ind_correo_clientes,t.enviar_hermana,t.enviar_si_hermana_cerrada,t.ind_cliente_inicial,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from wkf_detalle t  left join SERVICIOS r1 on (r1.CODIGO=t.servicio_inicio) left join ESTADOS r3 on (r3.codigo=t.codigo_estado) left join SERVICIOS r4 on (r4.CODIGO=t.servicio_destino) left join sis_multivalores m5 on (m5.tabla='ESTADO_REGISTRO' and m5.valor=t.estado) LEFT   JOIN CARACTERISTICAS c ON (t.Caracteristica = c.CODIGO) LEFT   JOIN  CARACTERISTICAS_VALOR cv ON (t.CARACTERISTICA=cv.CARACTERISTICA AND t.VALOR_CARACTERISTICA = cv.VALOR) where  t.codigo_flujo=" + codigoFlujo + " and t.secuencia=" + secuencia + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 243 */       boolean rtaDB = this.dat.parseSql(s);
/* 244 */       if (!rtaDB) {
/* 245 */         return null;
/*     */       }
/* 247 */       this.rs = this.dat.getResultSet();
/* 248 */       if (this.rs.next()) {
/* 249 */         return leerRegistro();
/*     */       }
/*     */     }
/* 252 */     catch (Exception e) {
/* 253 */       e.printStackTrace();
/* 254 */       Utilidades.writeError("FlujoDetalleDAO:cargarFlujoDetalle", e);
/*     */     } 
/* 256 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro(int codigoFlujo) {
/* 265 */     int inumero = 1;
/* 266 */     String s = "select max(secuencia) from wkf_detalle  where  codigo_flujo=" + codigoFlujo + "";
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 271 */       boolean rta = this.dat.parseSql(s);
/* 272 */       if (!rta) return 0; 
/* 273 */       this.rs = this.dat.getResultSet();
/* 274 */       if (this.rs.next()) {
/* 275 */         s = this.rs.getString(1);
/* 276 */         if (!this.rs.wasNull()) {
/* 277 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 280 */       return inumero;
/*     */     }
/* 282 */     catch (Exception e) {
/* 283 */       e.printStackTrace();
/* 284 */       Utilidades.writeError("FlujoDetalleDAO:siguienteRegistro ", e);
/*     */       
/* 286 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int codigoFlujo, int secuencia) {
/* 296 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 299 */       String s = "delete from wkf_detalle where  codigo_flujo=" + codigoFlujo + "  and secuencia=" + secuencia + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 304 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 306 */     catch (Exception e) {
/* 307 */       e.printStackTrace();
/* 308 */       Utilidades.writeError("FlujoDetalleDAO:eliminarRegistro ", e);
/* 309 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 311 */     return rta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD crearRegistro(int codigoFlujo, int secuencia, int servicioInicio, int accion, int codigoEstado, int servicioDestino, String nombreProcedimiento, String correoDestino, String enviar, String mismoProveedor, String mismoCliente, String estado, int caracteristica, int caracteristicaValor, int caracteristicaCorreo, String metodoSeleccionProveedor, String indCorreoCliente, String indHermana, String indHermanaCerrada, String indfuncionario, String usuarioInsercion) {
/* 342 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 344 */     int elSiguiente = siguienteRegistro(codigoFlujo);
/* 345 */     if (elSiguiente == 0) {
/* 346 */       rta.setMensaje("Generando secuencia");
/* 347 */       return rta;
/*     */     } 
/*     */     
/*     */     try {
/* 351 */       String s = "insert into wkf_detalle(codigo_flujo,secuencia,servicio_inicio,accion,codigo_estado,servicio_destino,nombre_procedimiento,correo_destino,enviar_solicitud,ind_mismo_proveedor,ind_mismo_cliente,estado,caracteristica,valor_caracteristica,caracteristica_correo,metodo_seleccion_proveedor,ind_correo_clientes,enviar_hermana,enviar_si_hermana_cerrada,ind_cliente_inicial,usuario_insercion,fecha_insercion) values (" + codigoFlujo + "," + "" + elSiguiente + "," + "" + servicioInicio + "," + "" + accion + "," + "" + codigoEstado + "," + "" + ((servicioDestino == 0) ? "null" : Integer.valueOf(servicioDestino)) + "," + "'" + nombreProcedimiento + "'," + "'" + correoDestino + "'," + "'" + enviar + "'," + "'" + mismoProveedor + "'," + "'" + mismoCliente + "'," + "'" + estado + "'," + ((caracteristica == 0) ? "null" : ("" + caracteristica)) + "," + ((caracteristicaValor == 0) ? "null" : ("" + caracteristicaValor)) + "," + ((caracteristicaCorreo == 0) ? "null" : ("" + caracteristicaCorreo)) + "," + ((metodoSeleccionProveedor.length() == 0) ? "null" : ("'" + metodoSeleccionProveedor + "'")) + "," + ((indCorreoCliente.length() == 0) ? "null" : ("'" + indCorreoCliente + "'")) + "," + ((indHermana.length() == 0) ? "null" : ("'" + indHermana + "'")) + "," + ((indHermanaCerrada.length() == 0) ? "null" : ("'" + indHermanaCerrada + "'")) + "," + ((indfuncionario.length() == 0) ? "null" : ("'" + indfuncionario + "'")) + "," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 398 */       rta = this.dat.executeUpdate2(s);
/* 399 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 401 */     catch (Exception e) {
/* 402 */       e.printStackTrace();
/* 403 */       Utilidades.writeError("%FlujoDetalleDAO:crearRegistro ", e);
/* 404 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 406 */     return rta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD modificarRegistro(int codigoFlujo, int secuencia, int servicioInicio, int accion, int codigoEstado, int servicioDestino, String nombreProcedimiento, String correoDestino, String enviar, String mismoProveedor, String mismomismoCliente, String estado, int caracteristica, int caracteristicaValor, int caracteristicaCorreo, String metodoSeleccionProveedor, String indCorreoCliente, String indHermana, String indHermanaCerrada, String indfuncionario, String usuarioModificacion) {
/* 437 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 440 */       String s = "update wkf_detalle set  servicio_inicio=" + servicioInicio + "," + " accion=" + accion + "," + " codigo_estado=" + codigoEstado + "," + " servicio_destino=" + ((servicioDestino == 0) ? "null" : Integer.valueOf(servicioDestino)) + "," + " nombre_procedimiento='" + nombreProcedimiento + "'," + " correo_destino='" + correoDestino + "'," + " enviar_solicitud='" + enviar + "'," + " ind_mismo_proveedor='" + mismoProveedor + "'," + " ind_mismo_cliente='" + mismomismoCliente + "'," + " estado='" + estado + "'," + " caracteristica=" + ((caracteristica == 0) ? "null" : ("" + caracteristica)) + "," + " valor_caracteristica=" + ((caracteristicaValor == 0) ? "null" : ("" + caracteristicaValor)) + "," + " caracteristica_correo=" + ((caracteristicaCorreo == 0) ? "null" : ("" + caracteristicaCorreo)) + "," + " metodo_seleccion_proveedor=" + ((metodoSeleccionProveedor.length() == 0) ? "null" : ("'" + metodoSeleccionProveedor + "'")) + "," + " ind_correo_clientes=" + ((indCorreoCliente.length() == 0) ? "null" : ("'" + indCorreoCliente + "'")) + "," + " enviar_hermana=" + ((indHermana.length() == 0) ? "null" : ("'" + indHermana + "'")) + "," + " enviar_si_hermana_cerrada=" + ((indHermanaCerrada.length() == 0) ? "null" : ("'" + indHermanaCerrada + "'")) + "," + " ind_cliente_inicial=" + ((indfuncionario.length() == 0) ? "null" : ("'" + indfuncionario + "'")) + "," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " codigo_flujo=" + codigoFlujo + " and secuencia=" + secuencia + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 465 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 467 */     catch (Exception e) {
/* 468 */       e.printStackTrace();
/* 469 */       Utilidades.writeError("FlujoDetalleDAO:modificarRegistro ", e);
/* 470 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 472 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\FlujoDetalleDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */