/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PrcProcedimientoDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PrcProcedimientoDAO;
/*     */ 
 
/*     */ 
/*     */ 
/*     */ public class PrcProcedimientoDAO
/*     */ {
/*     */   public PrcProcedimientoDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  35 */       PrcProcedimientoDTO reg = new PrcProcedimientoDTO();
/*     */       
/*  37 */       reg.setIdProcedimiento(rs.getInt("id_procedimiento"));
/*  38 */       reg.setProcesoId(rs.getInt("proceso_id"));
/*  39 */       reg.setSubprocesoId(rs.getInt("subproceso_id"));
/*  40 */       reg.setServicioUnidadId(rs.getInt("servicioUnidad_id"));
/*  41 */       reg.setCodigoEmpleado(rs.getInt("codigo_empleado"));
/*  42 */       reg.setObjetivo(rs.getString("objetivo"));
/*  43 */       reg.setAlcance(rs.getString("alcance"));
/*  44 */       reg.setDefiniciones(rs.getString("definiciones"));
/*  45 */       reg.setConcepto(rs.getString("concepto"));
/*  46 */       reg.setEstado(rs.getString("estado"));
/*     */ 
 
/*  57 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  58 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  59 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  60 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  61 */       reg.setNombreCodigoEmpleado(rs.getString("nombre_codigo_empleado"));
/*  62 */       reg.setNombreEstado(rs.getString("nombre_estado"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  73 */       return reg;
/*     */     }
/*  75 */     catch (Exception e) {
/*  76 */       e.printStackTrace();
/*  77 */       Utilidades.writeError("PrcProcedimientoDAO:leerRegistro ", e);
/*     */       
/*  79 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PrcProcedimientoDTO leerRegistroPoliticas(ResultSet rs) {
/*     */     try {
/*  89 */       PrcProcedimientoDTO reg = new PrcProcedimientoDTO();
/*     */       
/*  91 */       reg.setNombrePoliticas(rs.getString("descripcion_politica"));
/*  92 */       return reg;
/*     */     }
/*  94 */     catch (Exception e) {
/*  95 */       e.printStackTrace();
/*  96 */       Utilidades.writeError("PrcProcedimientoDAO:leerRegistroPoliticas ", e);
/*     */       
/*  98 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PrcProcedimientoDTO leerRegistroEntrada(ResultSet rs) {
/*     */     try {
/* 108 */       PrcProcedimientoDTO reg = new PrcProcedimientoDTO();
/*     */       
/* 110 */       reg.setProveedores(rs.getInt("id_proveedor"));
/* 111 */       reg.setEntradas(rs.getInt("id_entrada"));
/*     */       
/* 113 */       return reg;
/*     */     }
/* 115 */     catch (Exception e) {
/* 116 */       e.printStackTrace();
/* 117 */       Utilidades.writeError("PrcProcedimientoDAO:leerRegistroEntrada ", e);
/*     */       
/* 119 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PrcProcedimientoDTO leerRegistroSalida(ResultSet rs) {
/*     */     try {
/* 129 */       PrcProcedimientoDTO reg = new PrcProcedimientoDTO();
/*     */       
/* 131 */       reg.setClientes(rs.getInt("id_salida"));
/* 132 */       reg.setSalidas(rs.getInt("id_cliente"));
/*     */       
/* 134 */       return reg;
/*     */     }
/* 136 */     catch (Exception e) {
/* 137 */       e.printStackTrace();
/* 138 */       Utilidades.writeError("PrcProcedimientoDAO:leerRegistroProveedor ", e);
/*     */       
/* 140 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PrcProcedimientoDTO leerRegistroRecurso(ResultSet rs) {
/*     */     try {
/* 150 */       PrcProcedimientoDTO rec = new PrcProcedimientoDTO();
/*     */       
/* 152 */       rec.setTipoRecurso(rs.getString("id_tipo_recurso"));
/* 153 */       rec.setDescRecurso(rs.getString("descripcion_recurso"));
/*     */       
/* 155 */       return rec;
/*     */     }
/* 157 */     catch (Exception e) {
/* 158 */       e.printStackTrace();
/* 159 */       Utilidades.writeError("PrcProcedimientoDAO:leerRegistroProveedor ", e);
/*     */       
/* 161 */       return null;
/*     */     } 
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
/*     */   public Collection<PrcProcedimientoDTO> cargarTodos(int idProcedimiento, int codigoEmpleado, String objetivo, String alcance) {
/* 175 */     Collection<PrcProcedimientoDTO> resultados = new ArrayList<PrcProcedimientoDTO>();
/*     */     
/* 177 */    DBManager dat = new DBManager();
/*     */     try {
/* 179 */       String s = "select t.id_procedimiento,t.proceso_id,t.subproceso_id,t.servicioUnidad_id,t.codigo_empleado,r1.APELLIDOS as nombre_codigo_empleado,t.objetivo,t.alcance,t.definiciones,t.concepto,t.estado,m2.descripcion as nombre_estado,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from prc_procedimientos t  left join sis_usuarios r1 on (r1.CODIGO_EMPLEADO=t.codigo_empleado) left join sis_multivalores m2 on (m2.tabla='estado_activo_inactivo' and m2.valor=t.estado) where 1=1";
 
/*     */ 
/*     */       
/* 230 */       if (idProcedimiento > 0) {
/* 231 */         s = s + " and t.id_procedimiento=" + idProcedimiento;
/*     */       }
/* 233 */       if (codigoEmpleado > 0) {
/* 234 */         s = s + " and t.codigo_empleado=" + codigoEmpleado;
/*     */       }
/* 236 */       if (objetivo.length() > 0) {
/* 237 */         s = s + " and upper(t.objetivo) like upper('%" + objetivo + "%')";
/*     */       }
/* 239 */       if (alcance.length() > 0) {
/* 240 */         s = s + " and upper(t.alcance) like upper('%" + alcance + "%')";
/*     */       }
/* 242 */       s = s + " order by 1";
/* 243 */       boolean rtaDB = dat.parseSql(s);
/* 244 */       if (!rtaDB) {
/* 245 */         return resultados;
/*     */       }
/* 247 */       ResultSet rs = dat.getResultSet();
/* 248 */       while (rs.next()) {
/* 249 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 252 */     catch (Exception e) {
/* 253 */       e.printStackTrace();
/* 254 */       Utilidades.writeError("PrcProcedimientoDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 257 */       dat.close();
/*     */     } 
/* 259 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection cargarPoliticas(int idProcedimiento) {
/* 270 */     Collection resultados = new ArrayList();
/*     */     
/* 272 */     DBManager dat = new DBManager();
/*     */     try {
/* 274 */       String s = "select  t.descripcion_politica from prc_procedimiento_politica t where 1=1   and t.id_procedimiento = " + idProcedimiento;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 279 */       s = s + " order by 1";
/* 280 */       boolean rtaDB = dat.parseSql(s);
/* 281 */       if (!rtaDB) {
/* 282 */         return resultados;
/*     */       }
/* 284 */       ResultSet rs = dat.getResultSet();
/* 285 */       while (rs.next()) {
/* 286 */         resultados.add(leerRegistroPoliticas(rs));
/*     */       }
/*     */     }
/* 289 */     catch (Exception e) {
/* 290 */       e.printStackTrace();
/* 291 */       Utilidades.writeError("PrcProcedimientoDAO:cargarPoliticas ", e);
/*     */     } 
/* 293 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection cargarProveedoresEntradas(int idProcedimiento) {
/* 304 */     Collection resultados = new ArrayList();
/*     */     
/* 306 */     DBManager dat = new DBManager();
/*     */     try {
/* 308 */       String s = "select  t.id_proveedor,t.id_entrada from prc_proveedor_entrada t where 1=1   and t.id_procedimiento = " + idProcedimiento;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 314 */       s = s + " order by 1";
/* 315 */       boolean rtaDB = dat.parseSql(s);
/* 316 */       if (!rtaDB) {
/* 317 */         return resultados;
/*     */       }
/* 319 */       ResultSet rs = dat.getResultSet();
/* 320 */       while (rs.next()) {
/* 321 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 324 */     catch (Exception e) {
/* 325 */       e.printStackTrace();
/* 326 */       Utilidades.writeError("PrcProcedimientoDAO:cargarProveedoresEntradas ", e);
/*     */     } 
/* 328 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection cargarClienteSalida(int idProcedimiento) {
/* 339 */     Collection resultados = new ArrayList();
/*     */     
/* 341 */     DBManager dat = new DBManager();
/*     */     try {
/* 343 */       String s = "select  t.id_salida,t.id_cliente from prc_cliente_salida t where 1=1   and t.id_procedimiento = " + idProcedimiento;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 349 */       s = s + " order by 1";
/* 350 */       boolean rtaDB = dat.parseSql(s);
/* 351 */       if (!rtaDB) {
/* 352 */         return resultados;
/*     */       }
/* 354 */       ResultSet rs = dat.getResultSet();
/* 355 */       while (rs.next()) {
/* 356 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 359 */     catch (Exception e) {
/* 360 */       e.printStackTrace();
/* 361 */       Utilidades.writeError("PrcProcedimientoDAO:cargarProveedoresEntradas ", e);
/*     */     } 
/* 363 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection cargarEncadenamiento(int idProcedimiento) {
/* 374 */     Collection resultados = new ArrayList();
/*     */     
/* 376 */     DBManager dat = new DBManager();
/*     */     try {
/* 378 */       String s = "select  ppr.id_procedimiento_antecesor from prc_procedimiento_proc ppr where 1=1   and ppr.id_procedimiento = " + idProcedimiento;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 383 */       s = s + " order by 1";
/* 384 */       boolean rtaDB = dat.parseSql(s);
/* 385 */       if (!rtaDB) {
/* 386 */         return resultados;
/*     */       }
/* 388 */       ResultSet rs = dat.getResultSet();
/* 389 */       while (rs.next()) {
/* 390 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 393 */     catch (Exception e) {
/* 394 */       e.printStackTrace();
/* 395 */       Utilidades.writeError("PrcProcedimientoDAO:cargarEncadenamiento ", e);
/*     */     } 
/* 397 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection cargarRecurso(int idProcedimiento) {
/* 408 */     Collection resultados = new ArrayList();
/*     */     
/* 410 */     DBManager dat = new DBManager();
/*     */     try {
/* 412 */       String s = "select  rec.id_tipo_recurso,rec.descripcion_recurso from prc_recurso rec where 1=1   and rec.id_procedimiento = " + idProcedimiento;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 418 */       s = s + " order by 1";
/* 419 */       boolean rtaDB = dat.parseSql(s);
/* 420 */       if (!rtaDB) {
/* 421 */         return resultados;
/*     */       }
/* 423 */       ResultSet rs = dat.getResultSet();
/* 424 */       while (rs.next()) {
/* 425 */         resultados.add(leerRegistroRecurso(rs));
/*     */       }
/*     */     }
/* 428 */     catch (Exception e) {
/* 429 */       e.printStackTrace();
/* 430 */       Utilidades.writeError("PrcProcedimientoDAO:cargarRecurso ", e);
/*     */     } 
/* 432 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PrcProcedimientoDTO cargarRegistro(int idProcedimiento) {
/* 443 */    DBManager dat = new DBManager();
/*     */     try {
/* 445 */       String s = "select t.id_procedimiento,t.proceso_id,t.subproceso_id,t.servicioUnidad_id,t.codigo_empleado,r1.APELLIDOS as nombre_codigo_empleado,t.objetivo,t.alcance,t.definiciones,t.concepto,t.estado,m2.descripcion as nombre_estado,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from prc_procedimientos t  left join sis_usuarios r1 on (r1.CODIGO_EMPLEADO=t.codigo_empleado) left join sis_multivalores m2 on (m2.tabla='estado_activo_inactivo' and m2.valor=t.estado) where  t.id_procedimiento=" + idProcedimiento + "";
/*     */ 
/*     */ 

/*     */ 
/*     */ 
/*     */       
/* 498 */       boolean rtaDB = dat.parseSql(s);
/* 499 */       if (!rtaDB) {
/* 500 */         return null;
/*     */       }
/* 502 */       ResultSet rs = dat.getResultSet();
/* 503 */       if (rs.next()) {
/* 504 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 507 */     catch (Exception e) {
/* 508 */       e.printStackTrace();
/* 509 */       Utilidades.writeError("PrcProcedimientoDAO:cargarPrcProcedimiento", e);
/*     */     } finally {
/*     */       
/* 512 */       dat.close();
/*     */     } 
/* 514 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 523 */     int inumero = 1;
/* 524 */     String s = "select max(id_procedimiento) from prc_procedimientos ";
/*     */ 
/*     */     
/* 527 */    DBManager dat = new DBManager();
/*     */     try {
/* 529 */       boolean rta = dat.parseSql(s);
/* 530 */       if (!rta) return 0; 
/* 531 */       ResultSet rs = dat.getResultSet();
/* 532 */       if (rs.next()) {
/* 533 */         s = rs.getString(1);
/* 534 */         if (!rs.wasNull()) {
/* 535 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 538 */       return inumero;
/*     */     }
/* 540 */     catch (Exception e) {
/* 541 */       e.printStackTrace();
/* 542 */       Utilidades.writeError("PrcProcedimientoDAO:siguienteRegistro ", e);
/*     */     } finally {
/*     */       
/* 545 */       dat.close();
/*     */     } 
/* 547 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int idProcedimiento) {
/* 557 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 559 */   DBManager  dat = new DBManager();
/*     */     try {
/* 561 */       String s = "delete from prc_procedimientos where  id_procedimiento=" + idProcedimiento + "";
/*     */ 
/*     */ 
/*     */       
/* 565 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 567 */     catch (Exception e) {
/* 568 */       e.printStackTrace();
/* 569 */       Utilidades.writeError("PrcProcedimientoDAO:eliminarRegistro ", e);
/* 570 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 573 */       dat.close();
/*     */     } 
/* 575 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int idProcedimiento, int procesoId, int subprocesoId, int servicioUnidadId, int codigoEmpleado, String objetivo, String alcance, String definiciones, String concepto, String estado, String usuarioInsercion) {
/* 596 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 598 */     int elSiguiente = siguienteRegistro();
/* 599 */     Utilidades.grabarLog(Integer.toString(elSiguiente));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 605 */    DBManager dat = new DBManager();
/*     */     try {
/* 607 */       String s = "insert into prc_procedimientos(id_procedimiento,proceso_id,subproceso_id,servicioUnidad_id,codigo_empleado,objetivo,alcance,definiciones,concepto,estado,usuario_insercion,fecha_insercion) values (" + elSiguiente + "," + "" + procesoId + "," + "" + subprocesoId + "," + "" + servicioUnidadId + "," + "" + codigoEmpleado + "," + "'" + objetivo + "'," + "'" + alcance + "'," + "'" + definiciones + "'," + "'" + concepto + "'," + "'" + estado + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
/*     */ 

/*     */ 
/*     */ 
/*     */       
/* 634 */       rta = dat.executeUpdate2(s);
/* 635 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 637 */     catch (Exception e) {
/* 638 */       e.printStackTrace();
/* 639 */       Utilidades.writeError("%PrcProcedimientoDAO:crearRegistro ", e);
/* 640 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 643 */       dat.close();
/*     */     } 
/* 645 */     return rta;
/*     */   }
/*     */ 

/*     */   public RespuestaBD crearRegistroRompimiento3(String tabla, int idProcedimiento, int idPrimer, int idSegundo) {
/* 659 */     RespuestaBD rta = new RespuestaBD();
/*     */ 
/*     */     
/* 662 */   DBManager  dat = new DBManager();
/*     */     try {
/* 664 */       String s = "";
/* 665 */       if (tabla.equals("prc_proveedor_entrada")) {
/* 666 */         s = "insert into prc_proveedor_entrada (id_procedimiento,id_proveedor,id_entrada) values (" + idProcedimiento + "," + "" + idPrimer + "," + "" + idSegundo + "" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 675 */       else if (tabla.equals("prc_cliente_salida")) {
/* 676 */         s = "insert into prc_cliente_salida (id_procedimiento,id_cliente,id_salida) values (" + idProcedimiento + "," + "" + idPrimer + "," + "" + idSegundo + "" + ")";
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 687 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 689 */     catch (Exception e) {
/* 690 */       e.printStackTrace();
/* 691 */       Utilidades.writeError("%PrcProcedimientoDAO:crearRegistroRompimiento3 ", e);
/* 692 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 695 */       dat.close();
/*     */     } 
/* 697 */     return rta;
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
/*     */   public RespuestaBD crearRegistroRompimiento2(String tabla, int idProcedimiento, String idPrimer) {
/* 710 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 712 */   DBManager  dat = new DBManager();
/*     */     try {
/* 714 */       String s = "";
/*     */       
/* 716 */       s = "insert into prc_procedimiento_politica (descripcion_politica,id_procedimiento) values ('" + idPrimer + "'," + "" + idProcedimiento + "" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 723 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 725 */     catch (Exception e) {
/* 726 */       e.printStackTrace();
/* 727 */       Utilidades.writeError("%PrcProcedimientoDAO:crearRegistroRompimiento2 ", e);
/* 728 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 731 */       dat.close();
/*     */     } 
/* 733 */     return rta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD crearRegistroRompimiento2Int(String tabla, int idProcedimiento, int idPrimer) {
/* 741 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 743 */    DBManager dat = new DBManager();
/*     */     try {
/* 745 */       String s = "insert into prc_procedimiento_proc (id_procedimiento,id_procedimiento_antecesor) values (" + idProcedimiento + "," + "" + idPrimer + "" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 753 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 755 */     catch (Exception e) {
/* 756 */       e.printStackTrace();
/* 757 */       Utilidades.writeError("%PrcProcedimientoDAO:crearRegistroRompimiento2 ", e);
/* 758 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 761 */       dat.close();
/*     */     } 
/* 763 */     return rta;
/*     */   }
/*     */ 
 
/*     */ 
/*     */   
/*     */   public RespuestaBD modificarRegistro(int idProcedimiento, int procesoId, int subprocesoId, int servicioUnidadId, int codigoEmpleado, String objetivo, String alcance, String definiciones, String concepto, String estado, int AnterioresText, int Proveedores, int Entradas, int Clientes, int Salidas, int codigoEmpleadoDescPlan, int codigoEmpleadoDescHac, int codigoEmpleadoDescVer, int codigoEmpleadoDescAct, String tipoRecurso, String usuarioModificacion) {
/* 796 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 798 */    DBManager dat = new DBManager();
/*     */     try {
/* 800 */       String s = "update prc_procedimientos set  proceso_id=" + procesoId + "," + " subproceso_id=" + subprocesoId + "," + " servicioUnidad_id=" + servicioUnidadId + "," + " codigo_empleado=" + codigoEmpleado + "," + " objetivo='" + objetivo + "'," + " alcance='" + alcance + "'," + " definiciones='" + definiciones + "'," + " concepto='" + concepto + "'," + " estado='" + estado + "'," + " AnterioresText=" + AnterioresText + "," + " Proveedores=" + Proveedores + "," + " Entradas=" + Entradas + "," + " Clientes=" + Clientes + "," + " Salidas=" + Salidas + "," + " codigoEmpleadoDescPlan=" + codigoEmpleadoDescPlan + "," + " codigoEmpleadoDescHac=" + codigoEmpleadoDescHac + "," + " codigoEmpleadoDescVer=" + codigoEmpleadoDescVer + "," + " codigoEmpleadoDescAct=" + codigoEmpleadoDescAct + "," + " tipoRecurso='" + tipoRecurso + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " id_procedimiento=" + idProcedimiento + "";
/*     */ 
/*     */ 
 
/*     */       
/* 825 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 827 */     catch (Exception e) {
/* 828 */       e.printStackTrace();
/* 829 */       Utilidades.writeError("PrcProcedimientoDAO:modificarRegistro ", e);
/* 830 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 833 */       dat.close();
/*     */     } 
/* 835 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PrcProcedimientoDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */