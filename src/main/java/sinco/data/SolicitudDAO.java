/*      */ package sinco.data;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import sinco.business.EstadoDTO;
/*      */ import sinco.business.ManejadorArchivosDTO;
/*      */ import sinco.business.ParametrosDTO;
/*      */ import sinco.business.RespuestaBD;
/*      */ import sinco.business.SolicitudDTO;
/*      */ import sinco.business.Utilidades;
/*      */ import sinco.data.ArchivosSolicitudDAO;
/*      */ import sinco.data.DBManager;
/*      */ import sinco.data.EstadoDAO;
/*      */ import sinco.data.HistoriaSolicitudDAO;
/*      */ import sinco.data.SolicitudDAO;
/*      */ 
/*      */ public class SolicitudDAO {
/*      */   public void close() {
/*      */     try {
/*   19 */       this.dat.close();
/*      */     }
/*   21 */     catch (Exception e) {}
/*      */   }
/*      */ 
/*      */   
/*      */   ResultSet rs;
/*   26 */   DBManager dat = new DBManager();
/*      */ 
/*      */   
/*   29 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SolicitudDTO leerRegistro() {
/*      */     try {
/*   38 */       SolicitudDTO reg = new SolicitudDTO();
/*   39 */       reg.setNumero(this.rs.getInt("numero"));
/*   40 */       String temporal = this.rs.getString("fecha_generada");
/*   41 */       reg.setFechaGenerada(temporal);
/*      */       
/*   43 */       reg.setAreaCliente(this.rs.getInt("area_cliente"));
/*   44 */       reg.setEmpleadoCliente(this.rs.getInt("empleado_cliente"));
/*   45 */       reg.setCodigoServicio(this.rs.getInt("codigo_servicio"));
/*   46 */       reg.setAreaProveedor(this.rs.getInt("area_proveedor"));
/*   47 */       reg.setEmpleadoProveedor(this.rs.getInt("empleado_proveedor"));
/*   48 */       reg.setCodigoEstado(this.rs.getInt("codigo_estado"));
/*      */       
/*   50 */       reg.setDuracion(this.rs.getInt("duracion"));
/*   51 */       reg.setUnidad(this.rs.getString("unidad_medida"));
/*      */       
/*   53 */       if (this.rs.getString("abierta").toLowerCase().equals("s")) {
/*   54 */         reg.setAbierta(true);
/*      */       } else {
/*   56 */         reg.setAbierta(false);
/*   57 */       }  reg.setNivelEscalamiento(this.rs.getInt("nivel_escalamiento"));
/*   58 */       String temp = this.rs.getString("fecha_vigencia");
/*      */       
/*   60 */       if (!this.rs.wasNull()) {
/*   61 */         reg.setFechaVigencia(temp);
/*      */       } else {
/*      */         
/*   64 */         reg.setFechaVigencia("");
/*      */       } 
/*      */ 
/*      */       
/*   68 */       temp = this.rs.getString("fecha_estimada_terminacion");
/*      */       
/*   70 */       if (!this.rs.wasNull()) {
/*   71 */         reg.setFechaEstimadaTerminacion(temp);
/*      */       } else {
/*      */         
/*   74 */         reg.setFechaEstimadaTerminacion("");
/*      */       } 
/*      */       
/*   77 */       temp = this.rs.getString("fecha_real_terminacion");
/*      */       
/*   79 */       if (!this.rs.wasNull()) {
/*   80 */         reg.setFechaRealTerminacion(temp);
/*      */       } else {
/*      */         
/*   83 */         reg.setFechaRealTerminacion("");
/*      */       } 
/*      */       
/*   86 */       temp = this.rs.getString("fecha_base_escalamientos");
/*      */       
/*   88 */       if (!this.rs.wasNull()) {
/*   89 */         reg.setFechaBaseEscalamientos(temp);
/*      */       } else {
/*      */         
/*   92 */         reg.setFechaBaseEscalamientos("");
/*      */       } 
/*      */ 
/*      */       
/*   96 */       temp = this.rs.getString("solicitud_padre");
/*      */       
/*   98 */       if (!this.rs.wasNull()) {
/*   99 */         reg.setSolicitudPadre(Integer.parseInt(temp));
/*      */       } else {
/*      */         
/*  102 */         reg.setSolicitudPadre(-1);
/*      */       } 
/*      */       
/*  105 */       temp = this.rs.getString("fecha_oportunidad");
/*      */       
/*  107 */       if (!this.rs.wasNull()) {
/*  108 */         reg.setFechaOportunidad(temp);
/*      */       } else {
/*      */         
/*  111 */         reg.setFechaOportunidad("");
/*      */       } 
/*      */       
/*  114 */       temp = this.rs.getString("codigo_oportunidad");
/*      */       
/*  116 */       if (!this.rs.wasNull()) {
/*  117 */         reg.setCodigoOportunidad(temp);
/*      */       } else {
/*      */         
/*  120 */         reg.setCodigoOportunidad("");
/*      */       } 
/*      */       
/*  123 */       temp = this.rs.getString("codigo_confiabilidad");
/*      */       
/*  125 */       if (!this.rs.wasNull()) {
/*  126 */         reg.setCodigoConfiabilidad(temp);
/*      */       } else {
/*      */         
/*  129 */         reg.setCodigoConfiabilidad("");
/*      */       } 
/*      */       
/*  132 */       temp = this.rs.getString("fecha_confiabilidad");
/*      */       
/*  134 */       if (!this.rs.wasNull()) {
/*  135 */         reg.setFechaConfiabilidad(temp);
/*      */       } else {
/*      */         
/*  138 */         reg.setFechaConfiabilidad("");
/*      */       } 
/*      */       
/*  141 */       temp = this.rs.getString("observaciones");
/*      */       
/*  143 */       if (!this.rs.wasNull()) {
/*  144 */         reg.setObservaciones(temp);
/*      */       } else {
/*      */         
/*  147 */         reg.setObservaciones("");
/*      */       } 
/*  149 */       reg.setProveedorAnterior(this.rs.getInt("proveedor_anterior"));
/*  150 */       reg.setNotificar(this.rs.getString("notificar"));
/*  151 */       reg.setCiclo(this.rs.getString("ciclo"));
/*      */       
/*  153 */       reg.setNumeroMacroservicio(this.rs.getInt("numero_macroservicio"));
/*      */       
/*  155 */       return reg;
/*      */     }
/*  157 */     catch (SQLException e) {
/*  158 */       Utilidades.writeError("SolicitudFactory", e);
/*  159 */       e.printStackTrace();
/*      */       
/*  161 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SolicitudDTO next() {
/*      */     try {
/*  170 */       if (this.rs.next()) {
/*  171 */         return leerRegistro();
/*      */       }
/*      */     }
/*  174 */     catch (SQLException e) {
/*  175 */       Utilidades.writeError("SolicitudFactory", e);
/*  176 */       e.printStackTrace();
/*      */     } 
/*  178 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SolicitudDTO getSolicitud(int numero) {
/*      */     try {
/*  189 */       boolean rta = this.dat.parseSql("select * from solicitudes where numero=" + numero);
/*  190 */       if (!rta) return null; 
/*  191 */       this.rs = this.dat.getResultSet();
/*  192 */       if (this.rs.next()) {
/*  193 */         return leerRegistro();
/*      */       }
/*      */     }
/*  196 */     catch (SQLException e) {
/*  197 */       Utilidades.writeError("SolicitudFactory", e);
/*  198 */       e.printStackTrace();
/*      */     } 
/*  200 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int cuantosClientesHay(int quien, int codigo, String fechaInicio, String fechaFin) {
/*  213 */     int numero = 0;
/*      */     try {
/*  215 */       String s = "select count(0) from sis_usuarios  where codigo_empleado in(select distinct(empleado_cliente)  from solicitudes  where";
/*      */ 
/*      */ 
/*      */       
/*  219 */       if (quien == 1) {
/*  220 */         s = s + " area_proveedor=" + codigo;
/*      */       } else {
/*      */         
/*  223 */         s = s + " empleado_proveedor=" + codigo;
/*      */       } 
/*  225 */       s = s + " and fecha_vigencia>=" + Utilidades.formatoFecha2(fechaInicio);
/*  226 */       s = s + " and fecha_vigencia<" + Utilidades.formatoFecha2(fechaFin);
/*  227 */       s = s + " )";
/*  228 */       boolean rta = this.dat.parseSql(s);
/*  229 */       if (!rta) return 0; 
/*  230 */       this.rs = this.dat.getResultSet();
/*  231 */       if (this.rs.next()) {
/*  232 */         String temp = this.rs.getString(1);
/*  233 */         if (!this.rs.wasNull()) {
/*  234 */           numero = Integer.parseInt(temp);
/*      */         }
/*      */       }
/*      */     
/*  238 */     } catch (Exception e) {
/*  239 */       e.printStackTrace();
/*      */     } 
/*  241 */     return numero;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int cuantosServiciosHay(int quien, int codigo, String fechaInicio, String fechaFin) {
/*  254 */     int numero = 0;
/*      */     
/*      */     try {
/*  257 */       String s = "select count(0) from servicios where codigo in (select distinct(codigo_servicio) from solicitudes where";
/*  258 */       if (quien == 1) {
/*  259 */         s = s + " area_proveedor=" + codigo;
/*      */       } else {
/*      */         
/*  262 */         s = s + " empleado_proveedor=" + codigo;
/*      */       } 
/*  264 */       s = s + " and fecha_vigencia>=" + Utilidades.formatoFecha2(fechaInicio);
/*  265 */       s = s + " and fecha_vigencia<" + Utilidades.formatoFecha2(fechaFin);
/*  266 */       s = s + ")";
/*      */       
/*  268 */       boolean rta = this.dat.parseSql(s);
/*  269 */       if (!rta) return 0; 
/*  270 */       this.rs = this.dat.getResultSet();
/*  271 */       if (this.rs.next()) {
/*  272 */         String temp = this.rs.getString(1);
/*  273 */         if (!this.rs.wasNull()) {
/*  274 */           numero = Integer.parseInt(temp);
/*      */         }
/*      */       }
/*      */     
/*  278 */     } catch (Exception e) {
/*  279 */       e.printStackTrace();
/*      */     } 
/*  281 */     return numero;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int cuantosOportunidadHay(int quien, int codigo, String fechaInicio, String fechaFin, String oport) {
/*  294 */     int numero = 0;
/*      */     try {
/*  296 */       String s = "select count(0) from solicitudes where";
/*  297 */       if (quien == 1) {
/*  298 */         s = s + " area_proveedor=" + codigo;
/*      */       } else {
/*      */         
/*  301 */         s = s + " empleado_proveedor=" + codigo;
/*      */       } 
/*  303 */       s = s + " and fecha_oportunidad>=" + Utilidades.formatoFecha2(fechaInicio);
/*  304 */       s = s + " and fecha_oportunidad<" + Utilidades.formatoFecha2(fechaFin);
/*  305 */       s = s + " and codigo_oportunidad='" + oport + "'";
/*  306 */       boolean rta = this.dat.parseSql(s);
/*  307 */       if (!rta) return 0; 
/*  308 */       this.rs = this.dat.getResultSet();
/*  309 */       if (this.rs.next()) {
/*  310 */         String temp = this.rs.getString(1);
/*  311 */         if (!this.rs.wasNull()) {
/*  312 */           numero = Integer.parseInt(temp);
/*      */         }
/*      */       }
/*      */     
/*  316 */     } catch (Exception e) {
/*  317 */       e.printStackTrace();
/*      */     } 
/*  319 */     return numero;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int cuantosConfiabilidadHay(int quien, int codigo, String fechaInicio, String fechaFin, String conf) {
/*  332 */     int numero = 0;
/*      */     try {
/*  334 */       String s = "select count(0) from solicitudes where";
/*  335 */       if (quien == 1) {
/*  336 */         s = s + " area_proveedor=" + codigo;
/*      */       } else {
/*      */         
/*  339 */         s = s + " empleado_proveedor=" + codigo;
/*      */       } 
/*  341 */       s = s + " and fecha_confiabilidad>=" + Utilidades.formatoFecha2(fechaInicio);
/*  342 */       s = s + " and fecha_confiabilidad<" + Utilidades.formatoFecha2(fechaFin);
/*  343 */       s = s + " and codigo_confiabilidad='" + conf + "'";
/*      */       
/*  345 */       boolean rta = this.dat.parseSql(s);
/*  346 */       if (!rta) return 0; 
/*  347 */       this.rs = this.dat.getResultSet();
/*  348 */       if (this.rs.next()) {
/*  349 */         String temp = this.rs.getString(1);
/*  350 */         if (!this.rs.wasNull()) {
/*  351 */           numero = Integer.parseInt(temp);
/*      */         }
/*      */       }
/*      */     
/*  355 */     } catch (Exception e) {
/*  356 */       e.printStackTrace();
/*      */     } 
/*  358 */     return numero;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int crearSolicitud(int areac, int empleadoc, int codigoservicio, int areap, int empleadop, int solicitudPadre, String ciclo, int duracion, String unidadmedida, String macroServicio, int numeroMacroservicio, String usuario, String archivoAnexo) {
/*      */     try {
/*  395 */       boolean rta = this.dat.parseSql("select nextval('sec_solicitudes') siguiente");
/*  396 */       if (!rta) {
/*  397 */         return 0;
/*      */       }
/*  399 */       ResultSet rs = this.dat.getResultSet();
/*  400 */       int numero = 1;
/*  401 */       if (rs.next()) {
/*  402 */         numero = rs.getInt("siguiente");
/*      */       }
/*      */       
/*  405 */       EstadoDAO efa = new EstadoDAO();
/*  406 */       efa.cargarTodosTipo("INI");
/*  407 */       EstadoDTO esta = efa.next();
/*  408 */       efa.close();
/*      */       
/*  410 */       if (esta == null) {
/*  411 */         return 0;
/*      */       }
/*      */       
/*  414 */       String campos = "numero,fecha_generada,area_cliente,empleado_cliente,codigo_servicio,area_proveedor,empleado_proveedor,codigo_estado,abierta,nivel_escalamiento,ciclo,duracion,unidad_medida,macro_servicio,usuario_insercion";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  430 */       String valores = "" + numero + ",";
/*  431 */       valores = valores + Utilidades.formatoFecha(Utilidades.fechaActual());
/*  432 */       valores = valores + "," + areac;
/*  433 */       valores = valores + "," + empleadoc;
/*  434 */       valores = valores + "," + codigoservicio;
/*  435 */       valores = valores + "," + ((areap == 0) ? "NULL" : ("" + areap));
/*  436 */       valores = valores + "," + ((areap == 0) ? "NULL" : ("" + empleadop));
/*  437 */       valores = valores + "," + esta.getCodigo();
/*  438 */       valores = valores + ",'S',0,'" + ciclo + "'";
/*  439 */       valores = valores + "," + duracion + ",'" + unidadmedida + "'";
/*      */       
/*  441 */       valores = valores + "," + ((macroServicio == null) ? "null" : ("'" + macroServicio + "'"));
/*  442 */       valores = valores + ",'" + usuario + "'";
/*      */       
/*  444 */       if (solicitudPadre != -1) {
/*  445 */         campos = campos + ",solicitud_padre";
/*  446 */         valores = valores + "," + solicitudPadre;
/*      */       } 
/*      */       
/*  449 */       if (numeroMacroservicio > 0) {
/*  450 */         campos = campos + ",numero_macroservicio";
/*  451 */         valores = valores + "," + numeroMacroservicio;
/*      */       } 
/*      */       
/*  454 */       rta = this.dat.executeUpdate("insert into solicitudes (" + campos + ") values (" + valores + ")");
/*  455 */       if (!rta) {
/*  456 */         return 0;
/*      */       }
/*      */       
/*  459 */       HistoriaSolicitudDAO ssf = new HistoriaSolicitudDAO();
/*  460 */       ssf.crearHistoria(numero, esta.getCodigo(), -1, "", usuario);
/*  461 */       ssf.close();
/*      */       
/*  463 */       if (archivoAnexo.length() > 0) {
/*  464 */         ManejadorArchivosDTO ma = new ManejadorArchivosDTO();
/*  465 */         String archivo = numero + "_1_" + archivoAnexo;
/*  466 */         String toPath = ParametrosDTO.getString("archivos") + "/" + archivo;
/*      */         
/*  468 */         ArchivosSolicitudDAO rsArc = new ArchivosSolicitudDAO();
/*  469 */         rsArc.crearArchivo(numero, archivo, 0, usuario);
/*  470 */         rsArc.close();
/*      */         
/*  472 */         ma.copy(ParametrosDTO.getString("archivos_servicios") + "/" + archivoAnexo, toPath);
/*      */       } 
/*      */       
/*  475 */       return numero;
/*      */     }
/*  477 */     catch (SQLException e) {
/*  478 */       Utilidades.writeError("SolicitudFactory", e);
/*  479 */       e.printStackTrace();
/*      */       
/*  481 */       return 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int replicar(int solPadre, int empleadop, String fechaVigencia, String fechaterminacion, int estadoInicial, int estadoProveedor, String usuario) {
/*      */     try {
/*  507 */       boolean rta = this.dat.parseSql("select nextval('sec_solicitudes') siguiente");
/*  508 */       if (!rta) {
/*  509 */         return 0;
/*      */       }
/*  511 */       ResultSet rs = this.dat.getResultSet();
/*  512 */       int numero = 1;
/*  513 */       if (rs.next()) {
/*  514 */         String temp = rs.getString(1);
/*  515 */         if (!rs.wasNull()) {
/*  516 */           numero = Integer.parseInt(temp);
/*      */         }
/*      */       } 
/*      */       
/*  520 */       String s = "insert into solicitudes (numero,fecha_generada,area_cliente,empleado_cliente,codigo_servicio,area_proveedor,empleado_proveedor,codigo_estado,abierta,nivel_escalamiento,fecha_vigencia,fecha_estimada_terminacion,fecha_base_escalamientos,solicitud_padre,observaciones,encuesta,duracion,unidad_medida,ciclo,usuario_insercion)";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  541 */       s = s + " select " + numero;
/*  542 */       s = s + "," + Utilidades.formatoFecha(Utilidades.ahora());
/*  543 */       s = s + ",area_proveedor";
/*  544 */       s = s + ",empleado_proveedor";
/*  545 */       s = s + ",codigo_servicio";
/*  546 */       s = s + ",area_proveedor";
/*  547 */       s = s + "," + empleadop;
/*  548 */       s = s + "," + estadoInicial;
/*  549 */       s = s + ",abierta";
/*  550 */       s = s + ",nivel_escalamiento";
/*  551 */       s = s + "," + Utilidades.formatoFecha(fechaVigencia);
/*  552 */       s = s + "," + Utilidades.formatoFecha(fechaterminacion);
/*  553 */       s = s + "," + Utilidades.formatoFecha(fechaterminacion);
/*  554 */       s = s + "," + solPadre;
/*  555 */       s = s + ",observaciones";
/*  556 */       s = s + ",encuesta";
/*  557 */       s = s + ",duracion";
/*  558 */       s = s + ",unidad_medida";
/*  559 */       s = s + ",ciclo";
/*  560 */       s = s + ",'" + usuario + "'";
/*  561 */       s = s + " from solicitudes where numero=" + solPadre;
/*      */       
/*  563 */       rta = this.dat.executeUpdate(s);
/*  564 */       if (!rta) {
/*  565 */         return 0;
/*      */       }
/*      */       
/*  568 */       s = "update solicitudes set codigo_estado=" + estadoProveedor + ",";
/*  569 */       s = s + "fecha_modificacion=" + Utilidades.formatoFecha(Utilidades.ahora()) + ",";
/*  570 */       s = s + "usuario_modificacion='" + usuario + "'";
/*  571 */       s = s + " where numero=" + numero;
/*  572 */       rta = this.dat.executeUpdate(s);
/*      */ 
/*      */ 
/*      */       
/*  576 */       if (rta) {
/*  577 */         this.dat.procesarNovedad(numero, estadoInicial, usuario);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  611 */       return numero;
/*      */     }
/*  613 */     catch (SQLException e) {
/*  614 */       Utilidades.writeError("SolicitudFactory", e);
/*  615 */       e.printStackTrace();
/*      */       
/*  617 */       return 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean desvincularEncuesta(int sol, String usuario) {
/*  628 */     String s = "update solicitudes set encuesta=null,";
/*  629 */     s = s + "fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/*  630 */     s = s + "usuario_modificacion='" + usuario + "'";
/*  631 */     s = s + " where numero=" + sol;
/*  632 */     return this.dat.executeUpdate(s);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean replicarSolicitud(int solicitud, int empleadoProveedor, int areaProveedor, String usuario) {
/*      */     try {
/*  655 */       boolean rta = this.dat.parseSql("select nextval('sec_solicitudes') siguiente");
/*  656 */       if (!rta) {
/*  657 */         return false;
/*      */       }
/*  659 */       ResultSet rs = this.dat.getResultSet();
/*  660 */       int numero = 1;
/*  661 */       if (rs.next()) {
/*  662 */         String temp = rs.getString(1);
/*  663 */         if (!rs.wasNull()) {
/*  664 */           numero = Integer.parseInt(temp);
/*      */         }
/*      */       } 
/*      */       
/*  668 */       String s = "insert into solicitudes (numero,fecha_generada,area_proveedor,empleado_proveedor,codigo_servicio,area_cliente,empleado_cliente,codigo_estado,abierta,nivel_escalamiento,solicitud_padre,observaciones,encuesta,duracion,unidad_medida,ciclo,numero_flujo,codigo_flujo,usuario_insercion)";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  688 */       s = s + " select " + numero;
/*  689 */       s = s + "," + Utilidades.formatoFecha(Utilidades.ahora());
/*  690 */       s = s + "," + areaProveedor + " as area_proveedor";
/*  691 */       s = s + "," + empleadoProveedor + " as empleado_proveedor";
/*  692 */       s = s + ",codigo_servicio";
/*  693 */       s = s + ",area_cliente";
/*  694 */       s = s + ",empleado_cliente";
/*  695 */       s = s + ",codigo_estado";
/*  696 */       s = s + ",abierta";
/*  697 */       s = s + ",nivel_escalamiento";
/*  698 */       s = s + ",solicitud_padre";
/*  699 */       s = s + ",observaciones";
/*  700 */       s = s + ",encuesta";
/*  701 */       s = s + ",duracion";
/*  702 */       s = s + ",unidad_medida";
/*  703 */       s = s + ",ciclo";
/*  704 */       s = s + ",numero_flujo";
/*  705 */       s = s + ",codigo_flujo";
/*  706 */       s = s + ",'" + usuario + "'";
/*  707 */       s = s + " from solicitudes " + " where numero=" + solicitud;
/*      */ 
/*      */       
/*  710 */       return this.dat.executeUpdate(s);
/*      */     }
/*  712 */     catch (SQLException e) {
/*  713 */       Utilidades.writeError("SolicitudFactory", e);
/*  714 */       e.printStackTrace();
/*      */       
/*  716 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean asignarResponsable(int solicitud, int empleadoProveedor, int areaProveedor, String usuario) {
/*      */     try {
/*  738 */       String s = "update solicitudes set empleado_proveedor=" + empleadoProveedor + "," + "area_proveedor=" + areaProveedor + "," + "fecha_modificacion=" + Utilidades.formatoFecha(Utilidades.ahora()) + "," + "usuario_modificacion='" + usuario + "'" + " where numero=" + solicitud;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  744 */       return this.dat.executeUpdate(s);
/*      */     
/*      */     }
/*  747 */     catch (Exception e) {
/*  748 */       Utilidades.writeError("SolicitudFactory", e);
/*  749 */       e.printStackTrace();
/*      */       
/*  751 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RespuestaBD cambiarPrioridad(int numeroSolicitud, String nuevaFecha, int duracion, String unidadMedida, String elUsuario) {
/*      */     try {
/*  773 */       String s = "update solicitudes set fecha_estimada_terminacion=" + Utilidades.formatoFecha2(nuevaFecha) + "," + "fecha_base_escalamientos=" + Utilidades.formatoFecha2(nuevaFecha) + "," + "duracion=" + duracion + "," + "unidad_medida='" + unidadMedida + "'," + "fecha_modificacion=" + Utilidades.formatoFecha(Utilidades.ahora()) + "," + "usuario_modificacion='" + elUsuario + "'" + " where numero=" + numeroSolicitud;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  781 */       return this.dat.executeUpdate2(s);
/*      */     
/*      */     }
/*  784 */     catch (Exception e) {
/*  785 */       Utilidades.writeError("SolicitudFactory", e);
/*  786 */       e.printStackTrace();
/*      */       
/*  788 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RespuestaBD actualizarFechaTerminacion(int numeroSolicitud, String nuevaFecha, String elUsuario) {
/*      */     try {
/*  806 */       String s = "update solicitudes set fecha_estimada_terminacion=" + Utilidades.formatoFecha2(nuevaFecha) + "," + "fecha_base_escalamientos=" + Utilidades.formatoFecha2(nuevaFecha) + "," + "fecha_modificacion=" + Utilidades.formatoFecha(Utilidades.ahora()) + "," + "usuario_modificacion='" + elUsuario + "'" + " where numero=" + numeroSolicitud;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  812 */       return this.dat.executeUpdate2(s);
/*      */     
/*      */     }
/*  815 */     catch (Exception e) {
/*  816 */       Utilidades.writeError("SolicitudFactory", e);
/*  817 */       e.printStackTrace();
/*      */       
/*  819 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RespuestaBD limpiarOportunidad(int numeroSolicitud, int codigoEstado, String elUsuario) {
/*      */     try {
/*  836 */       String s = "update solicitudes set  nivel_escalamiento=0, codigo_estado=" + codigoEstado + "," + " codigo_oportunidad=NULL," + " fecha_modificacion=" + Utilidades.formatoFecha(Utilidades.ahora()) + "," + " usuario_modificacion='" + elUsuario + "'" + " where numero=" + numeroSolicitud;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  843 */       return this.dat.executeUpdate2(s);
/*      */     }
/*  845 */     catch (Exception e) {
/*  846 */       Utilidades.writeError("SolicitudFactory", e);
/*  847 */       e.printStackTrace();
/*      */       
/*  849 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RespuestaBD actualizarEstado(int numeroSolicitud, int codigoEstado, String tipoEstado, boolean cierra, boolean oportunidad, String codigoOportunidad, int nivelEscalamiento, String observacion, String elUsuario) {
/*  874 */     RespuestaBD rta = new RespuestaBD();
/*      */     
/*      */     try {
/*  877 */       String s = "update solicitudes set  codigo_estado=" + codigoEstado + "";
/*      */ 
/*      */       
/*  880 */       if (tipoEstado.equals("CAL")) {
/*  881 */         s = s + ",fecha_real_terminacion=" + Utilidades.getFechaBD();
/*      */       }
/*      */       
/*  884 */       if (cierra) {
/*  885 */         s = s + ",abierta='N'";
/*      */       }
/*  887 */       if (oportunidad) {
/*  888 */         s = s + ",fecha_oportunidad=" + Utilidades.getFechaBD();
/*  889 */         s = s + ",codigo_oportunidad='" + codigoOportunidad + "'";
/*      */       } 
/*      */       
/*  892 */       if (observacion.length() > 0) {
/*  893 */         s = s + ",observaciones='" + observacion + "'";
/*      */       }
/*      */       
/*  896 */       s = s + ", fecha_modificacion=" + Utilidades.formatoFecha(Utilidades.ahora()) + " ,usuario_modificacion='" + elUsuario + "'" + " where numero=" + numeroSolicitud;
/*      */ 
/*      */ 
/*      */       
/*  900 */       rta = this.dat.executeUpdate2(s);
/*      */       
/*  902 */       if (rta.isRta()) {
/*  903 */         this.dat.procesarNovedad(numeroSolicitud, codigoEstado, elUsuario);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  909 */       if (tipoEstado.equals("CAL") || tipoEstado.equals("DV") || tipoEstado.equals("NAT")) {
/*  910 */         s = "update aplazamientos_solicitud set estado=3,fecha_Estado=" + Utilidades.getFechaBD() + "," + "usuario_modificacion='" + elUsuario + "'" + " where numero_solicitud=" + numeroSolicitud + " and estado=0";
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  915 */         this.dat.executeUpdate(s);
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  920 */     catch (Exception e) {
/*  921 */       Utilidades.writeError("SolicitudFactory", e);
/*  922 */       e.printStackTrace();
/*  923 */       rta.setMensaje(e.getMessage());
/*      */     } 
/*  925 */     return rta;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RespuestaBD actualizarEstado(int numeroSolicitud, int codigoEstado, String tipoEstado, boolean cierra, boolean oportunidad, String codigoOportunidad, int nivelEscalamiento, int devuelta, String codigoConfiabilidad, int diasSumar, int duracion, String unidadMedida, int nuevoResponsable, String fechaEscalamiento, String observacion, String elUsuario) {
/*  957 */     RespuestaBD rta = new RespuestaBD();
/*      */     
/*      */     try {
/*  960 */       rta = this.dat.procesarNovedad(numeroSolicitud, codigoEstado, elUsuario);
/*      */       
/*  962 */       if (rta.getCerrarSolicitud().equals("N")) {
/*  963 */         return rta;
/*      */       }
/*      */       
/*  966 */       String s = "update solicitudes set  codigo_estado=" + codigoEstado + "";
/*      */ 
/*      */       
/*  969 */       if (tipoEstado.equals("CAL")) {
/*  970 */         s = s + ",fecha_real_terminacion=" + Utilidades.getFechaBD();
/*      */       }
/*      */       
/*  973 */       if (cierra) {
/*  974 */         s = s + ",abierta='N'";
/*      */       }
/*      */       
/*  977 */       if (devuelta == 1) {
/*      */         
/*  979 */         if (nivelEscalamiento == 0) {
/*  980 */           s = s + ",fecha_oportunidad=null,codigo_oportunidad=null";
/*      */         }
/*      */         
/*  983 */         s = s + ",fecha_real_terminacion=null";
/*  984 */         s = s + ",numero_devoluciones=coalesce(numero_devoluciones,0)+1";
/*      */         
/*  986 */         if (nuevoResponsable > 0) {
/*  987 */           s = s + ",empleado_proveedor=" + nuevoResponsable;
/*      */         }
/*  989 */         if (fechaEscalamiento.length() > 0)
/*      */         {
/*  991 */           if (tipoEstado.equals("PRV")) {
/*  992 */             s = s + ",fecha_estimada_terminacion=" + Utilidades.formatoFecha(fechaEscalamiento);
/*      */           }
/*  994 */           s = s + ",fecha_base_escalamientos=" + Utilidades.formatoFecha(fechaEscalamiento);
/*      */         }
/*      */       
/*  997 */       } else if (oportunidad) {
/*  998 */         s = s + ",fecha_oportunidad=" + Utilidades.getFechaBD();
/*  999 */         s = s + ",codigo_oportunidad='" + codigoOportunidad + "'";
/*      */       } 
/*      */       
/* 1002 */       if (observacion.length() > 0) {
/* 1003 */         s = s + ",observaciones='" + observacion + "'";
/*      */       }
/*      */       
/* 1006 */       if (codigoConfiabilidad.length() > 0) {
/* 1007 */         s = s + ",codigo_confiabilidad='" + codigoConfiabilidad + "'" + ",fecha_confiabilidad=" + Utilidades.getFechaBD();
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1012 */       s = s + " ,fecha_modificacion=" + Utilidades.formatoFecha(Utilidades.ahora()) + " ,usuario_modificacion='" + elUsuario + "'" + " where numero=" + numeroSolicitud;
/*      */ 
/*      */ 
/*      */       
/* 1016 */       rta = this.dat.executeUpdate2(s);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1021 */       if (tipoEstado.equals("CAL") || tipoEstado.equals("DV") || tipoEstado.equals("NAT")) {
/* 1022 */         s = "update aplazamientos_solicitud set estado=3,fecha_Estado=" + Utilidades.getFechaBD() + "," + "usuario_modificacion='" + elUsuario + "'" + " where numero_solicitud=" + numeroSolicitud + " and estado=0";
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1027 */         this.dat.executeUpdate(s);
/*      */       
/*      */       }
/*      */     
/*      */     }
/* 1032 */     catch (Exception e) {
/* 1033 */       Utilidades.writeError("SolicitudFactory", e);
/* 1034 */       e.printStackTrace();
/* 1035 */       rta.setMensaje(e.getMessage());
/*      */     } 
/* 1037 */     return rta;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RespuestaBD enviarSolicitud(int numeroSolicitud, int codigoEstado, String fechaVigencia, String fechaterminacion, int duracion, String unidadMedida, String observacion, int horasContacto, String fechaContacto, String elUsuario) {
/* 1061 */     RespuestaBD rta = new RespuestaBD();
/*      */     
/*      */     try {
/* 1064 */       String s = "update solicitudes set codigo_estado=" + codigoEstado + "," + "fecha_vigencia=" + Utilidades.formatoFecha(fechaVigencia) + "," + "fecha_estimada_terminacion=" + Utilidades.formatoFecha(fechaterminacion) + "," + "fecha_base_escalamientos=" + Utilidades.formatoFecha(fechaterminacion) + "," + "duracion=" + duracion + "," + "unidad_medida='" + unidadMedida + "'," + "fecha_modificacion=" + Utilidades.formatoFecha(Utilidades.ahora()) + "," + "usuario_modificacion='" + elUsuario + "'," + "observaciones='" + observacion + "'";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1075 */       if (horasContacto > 0) {
/* 1076 */         s = s + ",fecha_contacto=" + Utilidades.formatoFecha(fechaContacto);
/*      */       }
/*      */       
/* 1079 */       s = s + " where numero=" + numeroSolicitud;
/*      */       
/* 1081 */       rta = this.dat.executeUpdate2(s);
/*      */       
/* 1083 */       if (rta.isRta()) {
/* 1084 */         this.dat.procesarNovedad(numeroSolicitud, codigoEstado, elUsuario);
/*      */       }
/*      */     }
/* 1087 */     catch (Exception e) {
/* 1088 */       Utilidades.writeError("SolicitudFactory", e);
/* 1089 */       e.printStackTrace();
/* 1090 */       rta.setMensaje(e.getMessage());
/*      */     } 
/* 1092 */     return rta;
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\SolicitudDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */