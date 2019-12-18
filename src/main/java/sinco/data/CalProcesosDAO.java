/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.CalProcesosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CalProcesosDAO;
/*     */ import sinco.data.DBManager;
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
/*     */ public class CalProcesosDAO
/*     */ {
/*     */   ResultSet rs;
/*  29 */   DBManager dat = new DBManager();
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
/*  40 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  47 */       this.dat.close();
/*     */     }
/*  49 */     catch (Exception e) {
/*  50 */       Utilidades.writeError("CalProcesosDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalProcesosDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("CalProcesosDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalProcesosDTO leerRegistro() {
/*     */     try {
/*  79 */       CalProcesosDTO reg = new CalProcesosDTO();
/*     */       
/*  81 */       reg.setProceso(this.rs.getString("codigo"));
/*  82 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  83 */       reg.setEstado(this.rs.getString("estado"));
/*  84 */       reg.setTipoProceso(this.rs.getString("tipo_proceso"));
/*  85 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  86 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  87 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  88 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  89 */       reg.setNombreEstado(this.rs.getString("nombre_estado"));
/*  90 */       reg.setNombreTipoProceso(this.rs.getString("nombre_tipo_proceso"));
/*  91 */       return reg;
/*     */     }
/*  93 */     catch (Exception e) {
/*  94 */       e.printStackTrace();
/*  95 */       Utilidades.writeError("CalProcesosDAO:leerRegistro ", e);
/*     */       
/*  97 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<CalProcesosDTO> cargarTodos(String descripcion, String estado) {
/* 108 */     Collection<CalProcesosDTO> resultados = new ArrayList<CalProcesosDTO>();
/*     */     try {
/* 110 */       String s = "select t.codigo,t.descripcion,t.estado,m1.descripcion as nombre_estado,t.tipo_proceso,m2.descripcion as nombre_tipo_proceso,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from procesos t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) left join sis_multivalores m2 on (m2.tabla='CAL_NOMBRE_TIPO_PROCESO' and m2.VALOR=t.tipo_proceso) where t.codigo not in ('00','0')";
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
/* 126 */       if (descripcion.length() > 0) {
/* 127 */         s = s + " and upper(t.descripcion) like upper('%" + descripcion + "%')";
/*     */       }
/* 129 */       if (estado.length() > 0) {
/* 130 */         s = s + " and upper(t.estado) like upper('%" + estado + "%')";
/*     */       }
/*     */       
/* 133 */       s = s + " order by 1";
/* 134 */       boolean rtaDB = this.dat.parseSql(s);
/* 135 */       if (!rtaDB) {
/* 136 */         return resultados;
/*     */       }
/* 138 */       this.rs = this.dat.getResultSet();
/* 139 */       while (this.rs.next()) {
/* 140 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 143 */     catch (Exception e) {
/* 144 */       e.printStackTrace();
/* 145 */       Utilidades.writeError("CalProcesosDAO:cargarTodos ", e);
/*     */     } 
/* 147 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalProcesosDTO cargarRegistro(String codigo) {
/*     */     try {
/* 156 */       String s = "select t.codigo,t.descripcion,t.estado,m1.descripcion as nombre_estado,t.tipo_proceso,m2.descripcion as nombre_tipo_proceso,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from procesos t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) left join sis_multivalores m2 on (m2.tabla='CAL_NOMBRE_TIPO_PROCESO' and m2.VALOR=t.tipo_proceso) where  t.codigo='" + codigo + "'" + "";
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
/* 173 */       boolean rtaDB = this.dat.parseSql(s);
/* 174 */       if (!rtaDB) {
/* 175 */         return null;
/*     */       }
/* 177 */       this.rs = this.dat.getResultSet();
/* 178 */       if (this.rs.next()) {
/* 179 */         return leerRegistro();
/*     */       }
/*     */     }
/* 182 */     catch (Exception e) {
/* 183 */       e.printStackTrace();
/* 184 */       Utilidades.writeError("CalProcesosDAO:cargarCalProcesos", e);
/*     */     } 
/* 186 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(String codigo) {
/* 195 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 198 */       String s = "delete from procesos where  codigo='" + codigo + "'" + "";
/*     */ 
/*     */ 
/*     */       
/* 202 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 204 */     catch (Exception e) {
/* 205 */       e.printStackTrace();
/* 206 */       Utilidades.writeError("CalProcesosDAO:eliminarRegistro ", e);
/* 207 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 209 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(String codigo, String descripcion, String estado, String tipoProceso, String usuarioInsercion) {
/* 224 */     RespuestaBD rta = new RespuestaBD();
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 229 */       String s = "insert into procesos(codigo,descripcion,estado,tipo_proceso,usuario_insercion,fecha_insercion) values ('" + codigo + "'," + "'" + descripcion + "'," + "'" + estado + "'," + "'" + tipoProceso + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
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
/* 244 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 246 */     catch (Exception e) {
/* 247 */       e.printStackTrace();
/* 248 */       Utilidades.writeError("%CalProcesosDAO:crearRegistro ", e);
/* 249 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 251 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(String codigo, String descripcion, String estado, String tipoProceso, String usuarioModificacion) {
/* 266 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 269 */       String s = "update procesos set  descripcion='" + descripcion + "'," + " estado='" + estado + "'," + " tipo_proceso='" + tipoProceso + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " codigo='" + codigo + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 278 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 280 */     catch (Exception e) {
/* 281 */       e.printStackTrace();
/* 282 */       Utilidades.writeError("CalProcesosDAO:modificarRegistro ", e);
/* 283 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 285 */     return rta;
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
/*     */   public Collection<CalProcesosDTO> cargarTablaProcesos() {
/* 297 */     Collection<CalProcesosDTO> resultados = new ArrayList<CalProcesosDTO>();
/*     */     
/*     */     try {
/* 300 */       String s = "select t.codigo,t.descripcion,t.estado,m1.descripcion as nombre_estado,t.tipo_proceso,m2.descripcion as nombre_tipo_proceso,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from procesos t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) left join sis_multivalores m2 on (m2.tabla='CAL_NOMBRE_TIPO_PROCESO' and m2.VALOR=t.tipo_proceso) where  t.estado='A' order by t.codigo";
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
/* 317 */       boolean rtaDB = this.dat.parseSql(s);
/* 318 */       if (!rtaDB) {
/* 319 */         return resultados;
/*     */       }
/* 321 */       this.rs = this.dat.getResultSet();
/* 322 */       while (this.rs.next()) {
/* 323 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 326 */     catch (Exception e) {
/* 327 */       e.printStackTrace();
/* 328 */       Utilidades.writeError("CalProcesosFactory:cargarTablaProcesos ", e);
/*     */     } 
/* 330 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<CalProcesosDTO> cargarProcesosDocumentos() {
/* 340 */     Collection<CalProcesosDTO> resultados = new ArrayList<CalProcesosDTO>();
/*     */     try {
/* 342 */       String s = "select procesos.tipo_proceso,doc.proceso as codigo,procesos.descripcion,COUNT(0) AS numero from cal_documentos doc,procesos where doc.proceso=procesos.codigo and procesos.estado='A' and doc.estado<>'I' GROUP BY procesos.tipo_proceso,doc.proceso,procesos.descripcion ORDER BY doc.proceso,procesos.tipo_proceso,procesos.tipo_proceso,procesos.descripcion";
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
/* 356 */       boolean rtaDB = this.dat.parseSql(s);
/* 357 */       this.rs = this.dat.getResultSet();
/* 358 */       if (!rtaDB) {
/* 359 */         return resultados;
/*     */       }
/* 361 */       while (this.rs.next()) {
/* 362 */         CalProcesosDTO reg = new CalProcesosDTO();
/* 363 */         reg.setProceso(this.rs.getString("codigo"));
/* 364 */         reg.setDescripcion(this.rs.getString("descripcion"));
/* 365 */         resultados.add(reg);
/*     */       }
/*     */     
/* 368 */     } catch (Exception e) {
/* 369 */       e.printStackTrace();
/* 370 */       Utilidades.writeError("CalProcesosDAO:cargarProcesosDocumentos ", e);
/*     */     } 
/* 372 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarTodos(String estado) {
/*     */     try {
/* 384 */       String s = "select t.codigo,t.descripcion,t.estado,m1.descripcion as nombre_estado,t.tipo_proceso,m2.descripcion as nombre_tipo_proceso,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from procesos t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) left join sis_multivalores m2 on (m2.tabla='CAL_NOMBRE_TIPO_PROCESO' and m2.VALOR=t.tipo_proceso) where t.codigo not in ('00','0')";
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
/* 399 */       if (estado.length() > 0) {
/* 400 */         s = s + " and t.estado='" + estado + "'";
/*     */       }
/* 402 */       s = s + " order by t.descripcion";
/* 403 */       boolean rtaDB = this.dat.parseSql(s);
/* 404 */       if (!rtaDB) {
/* 405 */         return false;
/*     */       }
/* 407 */       this.rs = this.dat.getResultSet();
/* 408 */       return true;
/*     */     }
/* 410 */     catch (Exception e) {
/* 411 */       e.printStackTrace();
/* 412 */       Utilidades.writeError("CalProcesosFactory:cargarTodos ", e);
/*     */       
/* 414 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int buscarMaximo() {
/*     */     try {
/* 425 */       String s = "select max(codigo) as codigo from procesos";
/* 426 */       boolean rtaDB = this.dat.parseSql(s);
/* 427 */       if (!rtaDB) {
/* 428 */         return 0;
/*     */       }
/* 430 */       this.rs = this.dat.getResultSet();
/* 431 */       if (this.rs.next()) {
/* 432 */         return Integer.parseInt(this.rs.getString("codigo")) + 1;
/*     */       }
/*     */     }
/* 435 */     catch (Exception e) {
/* 436 */       e.printStackTrace();
/* 437 */       Utilidades.writeError("CalProcesosFactory:buscarMaximo ", e);
/*     */     } 
/* 439 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection cargarSubProcesosDocumentos(String proceso) {
/* 449 */     Collection resultados = new ArrayList();
/*     */     try {
/* 451 */       String s = "select doc.subproceso,subprocesos.descripcion as nombre_subproceso,COUNT(0) AS numero from procesos, cal_documentos doc LEFT JOIN subprocesos on (doc.proceso=subprocesos.proceso and doc.subproceso=subprocesos.codigo) where doc.proceso=procesos.codigo and procesos.estado='A' and doc.estado<>'I' and subprocesos.estado='A' AND doc.proceso='" + proceso + "'" + " GROUP BY doc.subproceso," + " subprocesos.descripcion" + " ORDER BY 1,subprocesos.descripcion";
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
/* 465 */       boolean rtaDB = this.dat.parseSql(s);
/* 466 */       if (!rtaDB) {
/* 467 */         return resultados;
/*     */       }
/* 469 */       this.rs = this.dat.getResultSet();
/* 470 */       while (this.rs.next()) {
/* 471 */         CalProcesosDTO reg = new CalProcesosDTO();
/* 472 */         reg.setSubProceso(this.rs.getString("subproceso"));
/* 473 */         reg.setDescripcion(this.rs.getString("nombre_subproceso"));
/* 474 */         resultados.add(reg);
/*     */       }
/*     */     
/* 477 */     } catch (Exception e) {
/* 478 */       e.printStackTrace();
/* 479 */       Utilidades.writeError("CalProcesosDAO:cargarProcesosDocumentos ", e);
/*     */     } 
/* 481 */     return resultados;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\CalProcesosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */