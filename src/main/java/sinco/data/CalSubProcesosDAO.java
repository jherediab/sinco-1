/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import sinco.business.CalSubProcesosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CalSubProcesosDAO;
/*     */ import sinco.data.DBManager;
/*     */ 

/*     */ 
/*     */ public class CalSubProcesosDAO
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
/*  53 */       Utilidades.writeError("CalSubProcesosDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalSubProcesosDTO next() {
/*     */     try {
/*  64 */       if (this.rs.next()) {
/*  65 */         return leerRegistro();
/*     */       }
/*     */     }
/*  68 */     catch (Exception e) {
/*  69 */       e.printStackTrace();
/*  70 */       Utilidades.writeError("CalSubProcesosDAO:next ", e);
/*     */     } 
/*  72 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalSubProcesosDTO leerRegistro() {
/*     */     try {
/*  82 */       CalSubProcesosDTO reg = new CalSubProcesosDTO();
/*     */       
/*  84 */       reg.setProceso(this.rs.getString("proceso"));
/*  85 */       reg.setSubproceso(this.rs.getString("codigo"));
/*  86 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  87 */       reg.setEstado(this.rs.getString("estado"));
/*  88 */       reg.setFactor(this.rs.getInt("factor"));
/*  89 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  90 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  91 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  92 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  93 */       reg.setNombreEstado(this.rs.getString("nombre_estado"));
/*  94 */       return reg;
/*     */     }
/*  96 */     catch (Exception e) {
/*  97 */       e.printStackTrace();
/*  98 */       Utilidades.writeError("CalSubProcesosDAO:leerRegistro ", e);
/*     */       
/* 100 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<CalSubProcesosDTO> cargarTodos(String proceso) {
/* 111 */     Collection<CalSubProcesosDTO> resultados = new ArrayList<CalSubProcesosDTO>();
/*     */     try {
/* 113 */       String s = "select t.proceso,t.codigo,t.descripcion,t.estado,m1.descripcion as nombre_estado,t.factor,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from subprocesos t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where t.proceso='" + proceso + "'" + " order by 2";
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
/* 129 */       boolean rtaDB = this.dat.parseSql(s);
/* 130 */       if (!rtaDB) {
/* 131 */         return resultados;
/*     */       }
/* 133 */       this.rs = this.dat.getResultSet();
/* 134 */       while (this.rs.next()) {
/* 135 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 138 */     catch (Exception e) {
/* 139 */       e.printStackTrace();
/* 140 */       Utilidades.writeError("CalSubProcesosDAO:cargarTodos ", e);
/*     */     } 
/* 142 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<CalSubProcesosDTO> cargarDeProceso(String proceso) {
/* 153 */     Collection<CalSubProcesosDTO> resultados = new ArrayList<CalSubProcesosDTO>();
/*     */     try {
/* 155 */       String s = "select t.proceso,t.codigo,t.descripcion,t.estado,m1.descripcion as nombre_estado,t.factor,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from subprocesos t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where t.proceso='" + proceso + "'" + " and t.estado='A'" + " order by t.codigo";
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
/* 172 */       boolean rtaDB = this.dat.parseSql(s);
/* 173 */       if (!rtaDB) {
/* 174 */         return resultados;
/*     */       }
/* 176 */       this.rs = this.dat.getResultSet();
/* 177 */       while (this.rs.next()) {
/* 178 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 181 */     catch (Exception e) {
/* 182 */       e.printStackTrace();
/* 183 */       Utilidades.writeError("CalSubProcesosDAO:cargarTodos ", e);
/*     */     } 
/* 185 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<CalSubProcesosDTO> cargarTodosActivos() {
/* 194 */     Collection<CalSubProcesosDTO> resultados = new ArrayList<CalSubProcesosDTO>();
/*     */     try {
/* 196 */       String s = "select t.proceso,t.codigo,t.descripcion,t.estado,m1.descripcion as nombre_estado,t.factor,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from subprocesos t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where  t.estado='A' and t.codigo not in ('00','0')";
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
/* 211 */       s = s + " order by t.proceso,t.codigo";
/* 212 */       boolean rtaDB = this.dat.parseSql(s);
/* 213 */       if (!rtaDB) {
/* 214 */         return resultados;
/*     */       }
/* 216 */       this.rs = this.dat.getResultSet();
/* 217 */       while (this.rs.next()) {
/* 218 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 221 */     catch (Exception e) {
/* 222 */       e.printStackTrace();
/* 223 */       Utilidades.writeError("CalSubProcesosDAO:cargarTodos ", e);
/*     */     } 
/* 225 */     return resultados;
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
/*     */   public CalSubProcesosDTO cargarRegistro(String proceso, String subproceso) {
/*     */     try {
/* 238 */       String s = "select t.proceso,t.codigo,t.descripcion,t.estado,m1.descripcion as nombre_estado,t.factor,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from subprocesos t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where  t.proceso='" + proceso + "'" + " and t.codigo='" + subproceso + "'" + "";
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
/* 255 */       boolean rtaDB = this.dat.parseSql(s);
/* 256 */       if (!rtaDB) {
/* 257 */         return null;
/*     */       }
/* 259 */       this.rs = this.dat.getResultSet();
/* 260 */       if (this.rs.next()) {
/* 261 */         return leerRegistro();
/*     */       }
/*     */     }
/* 264 */     catch (Exception e) {
/* 265 */       e.printStackTrace();
/* 266 */       Utilidades.writeError("CalSubProcesosDAO:cargarCalSubProcesos", e);
/*     */     } 
/* 268 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(String proceso, String subproceso) {
/* 278 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 281 */       String s = "delete from subprocesos where  proceso='" + proceso + "'" + "  and codigo='" + subproceso + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 286 */       rta = this.dat.executeUpdate2(s);
/*     */     
/*     */     }
/* 289 */     catch (Exception e) {
/* 290 */       e.printStackTrace();
/* 291 */       Utilidades.writeError("CalSubProcesosDAO:eliminarRegistro ", e);
/* 292 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 294 */     return rta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarServicioPorSubProceso(String subproceso) {
/* 303 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 306 */       String s = "delete from procesos_servicios where  subproceso='" + subproceso + "'" + "";
/*     */ 
/*     */ 
/*     */       
/* 310 */       rta = this.dat.executeUpdate2(s);
/*     */     
/*     */     }
/* 313 */     catch (Exception e) {
/* 314 */       e.printStackTrace();
/* 315 */       Utilidades.writeError("CalSubProcesosDAO:eliminarServicioPorSubProceso ", e);
/* 316 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 318 */     return rta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private RespuestaBD agregarServicios(String proceso, String subproceso, Collection<CalSubProcesosDTO> resultados, String usuario) {
/* 329 */   RespuestaBD  rta = new RespuestaBD();
/*     */     
/* 331 */     String s = "update procesos_servicios set estado='X', usuario_modificacion='" + usuario + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " proceso='" + proceso + "'" + " and subproceso='" + subproceso + "'";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 338 */     rta = this.dat.executeUpdate2(s);
/*     */     
/* 340 */     Iterator<CalSubProcesosDTO> iterator = resultados.iterator();
/* 341 */     while (iterator.hasNext()) {
/* 342 */       CalSubProcesosDTO regDet = (CalSubProcesosDTO)iterator.next();
/*     */ 
/*     */       
/*     */       try {
/* 346 */         s = "select estado from procesos_servicios  where proceso='" + proceso + "'" + " and subproceso='" + subproceso + "'" + " and servicio='" + regDet.getServicio() + "'";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 352 */         this.dat.parseSql(s);
/* 353 */         this.rs = this.dat.getResultSet();
/* 354 */         boolean existe = false;
/* 355 */         if (this.rs.next()) {
/* 356 */           existe = true;
/*     */         }
/*     */         
/* 359 */         if (!existe) {
/* 360 */           s = "insert into procesos_servicios(proceso,subproceso,servicio,descripcion,estado,fecha_insercion,usuario_insercion) values ('" + proceso + "'," + "'" + subproceso + "'," + "'" + regDet.getServicio() + "'," + "'" + regDet.getDescripcion() + "'," + "'" + regDet.getEstado() + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuario + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 378 */           s = "update procesos_servicios set  descripcion='" + regDet.getDescripcion() + "'," + " estado='" + regDet.getEstado() + "'," + " usuario_modificacion='" + usuario + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " proceso='" + proceso + "'" + " and subproceso='" + subproceso + "'" + " and servicio='" + regDet.getServicio() + "'";
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 388 */         rta = this.dat.executeUpdate2(s);
/*     */ 
/*     */       
/*     */       }
/* 392 */       catch (Exception e) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 398 */     s = "delete from procesos_servicios  where estado='X' and proceso='" + proceso + "'" + " and subproceso='" + subproceso + "'";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 404 */     return this.dat.executeUpdate2(s);
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
/*     */   public RespuestaBD crearRegistro(String proceso, String subproceso, String descripcion, String estado, int factor, Collection<CalSubProcesosDTO> resultados, String usuarioInsercion) {
/* 424 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 427 */       String s = "insert into subprocesos(proceso,codigo,descripcion,estado,factor,usuario_insercion,fecha_insercion) values ('" + proceso + "'," + "'" + subproceso + "'," + "'" + descripcion + "'," + "'" + estado + "'," + "" + factor + "," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
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
/* 444 */       rta = this.dat.executeUpdate2(s);
/*     */       
/* 446 */       if (rta.isRta()) {
/* 447 */         agregarServicios(proceso, subproceso, resultados, usuarioInsercion);
/*     */       }
/*     */     }
/* 450 */     catch (Exception e) {
/* 451 */       e.printStackTrace();
/* 452 */       Utilidades.writeError("%CalSubProcesosDAO:crearRegistro ", e);
/* 453 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 455 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(String proceso, String subproceso, String descripcion, String estado, int factor, Collection<CalSubProcesosDTO> resultados, String usuarioModificacion) {
/* 472 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 475 */       String s = "update subprocesos set  descripcion='" + descripcion + "'," + " estado='" + estado + "'," + " factor=" + factor + "," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " proceso='" + proceso + "'" + " and codigo='" + subproceso + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 485 */       rta = this.dat.executeUpdate2(s);
/*     */       
/* 487 */       if (rta.isRta()) {
/* 488 */         agregarServicios(proceso, subproceso, resultados, usuarioModificacion);
/*     */       
/*     */       }
/*     */     }
/* 492 */     catch (Exception e) {
/* 493 */       e.printStackTrace();
/* 494 */       Utilidades.writeError("CalSubProcesosDAO:modificarRegistro ", e);
/* 495 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 497 */     return rta;
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
/*     */   public int buscarMaximo(String proceso) {
/*     */     try {
/* 510 */       String s = "select max(codigo) as codigo from subprocesos where proceso='" + proceso + "'";
/* 511 */       boolean rtaDB = this.dat.parseSql(s);
/* 512 */       if (!rtaDB) {
/* 513 */         return 1;
/*     */       }
/*     */       
/* 516 */       this.rs = this.dat.getResultSet();
/* 517 */       if (this.rs.next()) {
/* 518 */         return Integer.parseInt(this.rs.getString("codigo")) + 1;
/*     */       }
/*     */     }
/* 521 */     catch (Exception e) {
/* 522 */       e.printStackTrace();
/* 523 */       Utilidades.writeError("CalProcesosFactory:buscarMaximo ", e);
/*     */     } 
/* 525 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSuma(String proceso, String subproceso) {
/*     */     try {
/* 537 */       String s = "select sum(factor) as suma from subprocesos where estado='A' AND proceso='" + proceso + "'" + " and codigo<>'" + subproceso + "'";
/*     */       
/* 539 */       boolean rtaDB = this.dat.parseSql(s);
/* 540 */       if (!rtaDB) {
/* 541 */         return 0;
/*     */       }
/*     */       
/* 544 */       this.rs = this.dat.getResultSet();
/* 545 */       if (this.rs.next()) {
/* 546 */         return this.rs.getInt("suma");
/*     */       }
/*     */     }
/* 549 */     catch (Exception e) {
/* 550 */       e.printStackTrace();
/* 551 */       Utilidades.writeError("CalSubProcesosFactory:cargarCalSubProcesos ", e);
/*     */     } 
/* 553 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<CalSubProcesosDTO> cargarServicios(String proceso, String subproceso, String estado) {
/* 563 */     Collection<CalSubProcesosDTO> resultados = new ArrayList<CalSubProcesosDTO>();
/*     */     try {
/* 565 */       String s = "select  servicio, descripcion, estado, fecha_insercion, usuario_insercion, fecha_modificacion, usuario_modificacion from procesos_servicios t where proceso='" + proceso + "'" + " and subproceso='" + subproceso + "'";
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
/* 576 */       if (!estado.equals("T")) {
/* 577 */         s = s + " and t.estado='" + estado + "'";
/*     */       }
/* 579 */       s = s + " order by servicio";
/* 580 */       boolean rtaDB = this.dat.parseSql(s);
/* 581 */       if (!rtaDB) {
/* 582 */         return resultados;
/*     */       }
/* 584 */       this.rs = this.dat.getResultSet();
/* 585 */       while (this.rs.next()) {
/* 586 */         CalSubProcesosDTO reg = new CalSubProcesosDTO();
/*     */         
/* 588 */         reg.setProceso(proceso);
/* 589 */         reg.setSubproceso(subproceso);
/* 590 */         reg.setServicio(this.rs.getString("servicio"));
/* 591 */         reg.setDescripcion(this.rs.getString("descripcion"));
/* 592 */         reg.setEstado(this.rs.getString("estado"));
/* 593 */         reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/* 594 */         reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/* 595 */         reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/* 596 */         reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/* 597 */         resultados.add(reg);
/*     */       }
/*     */     
/* 600 */     } catch (Exception e) {
/* 601 */       e.printStackTrace();
/* 602 */       Utilidades.writeError("CalSubProcesosDAO:cargarTodos ", e);
/*     */     } 
/* 604 */     return resultados;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\CalSubProcesosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */