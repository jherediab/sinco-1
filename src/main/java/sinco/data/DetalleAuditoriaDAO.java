/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import sinco.business.DetalleAuditoriaDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.DetalleAuditoriaDAO;
/*     */ 

/*     */ 
/*     */ public class DetalleAuditoriaDAO
/*     */ {
/*     */   public DetalleAuditoriaDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  36 */       DetalleAuditoriaDTO reg = new DetalleAuditoriaDTO();
/*     */       
/*  38 */       reg.setCiclo(rs.getString("ciclo"));
/*  39 */       reg.setCodigoEmpleado(rs.getInt("codigo_empleado"));
/*  40 */       reg.setConsecutivo(rs.getInt("consecutivo"));
/*  41 */       reg.setRol(rs.getString("rol"));
/*  42 */       reg.setAreaAuditada(rs.getInt("area_auditada"));
/*  43 */       reg.setPersonaAuditada(rs.getInt("persona_auditada"));
/*  44 */       reg.setAsistio(rs.getString("asistio"));
/*  45 */       reg.setGenerado(rs.getString("generado"));
/*  46 */       reg.setSolicitud(rs.getInt("solicitud"));
/*  47 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  48 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  49 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  50 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  51 */       reg.setNombreCodigoEmpleado(rs.getString("nombre_codigo_empleado"));
/*  52 */       reg.setNombreRol(rs.getString("nombre_rol"));
/*  53 */       return reg;
/*     */     }
/*  55 */     catch (Exception e) {
/*  56 */       e.printStackTrace();
/*  57 */       Utilidades.writeError("DetalleAuditoriaDAO:leerRegistro ", e);
/*     */       
/*  59 */       return null;
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
/*     */   public Collection<DetalleAuditoriaDTO> cargarTodos(String ciclo, int areaAuditada, int personaAuditada) {
/*  72 */     Collection<DetalleAuditoriaDTO> resultados = new ArrayList<DetalleAuditoriaDTO>();
/*     */     
/*  74 */    DBManager dat = new DBManager();
/*     */     try {
/*  76 */       String s = "select t.ciclo,t.codigo_empleado,r1.APELLIDOS || ' ' || r1.nombres as nombre_codigo_empleado,t.consecutivo,t.rol,m2.DESCRIPCION as nombre_rol,t.area_auditada,t.persona_auditada,t.asistio,t.generado,t.solicitud,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from DETALLE_AUDITORIA t  left join SIS_USUARIOS r1 on (r1.CODIGO_EMPLEADO=t.codigo_empleado) left join sis_multivalores m2 on (m2.tabla='ROL_AUDITOR' and m2.VALOR=t.rol) where 1=1";
/*     */ 
/*     */ 

/*     */ 
/*     */       
/*  96 */       if (ciclo.length() > 0) {
/*  97 */         s = s + " and upper(t.ciclo) like upper('%" + ciclo + "%')";
/*     */       }
/*  99 */       if (areaAuditada > 0) {
/* 100 */         s = s + " and t.area_auditada=" + areaAuditada;
/*     */       }
/* 102 */       if (personaAuditada > 0) {
/* 103 */         s = s + " and t.persona_auditada=" + personaAuditada;
/*     */       }
/* 105 */       s = s + " order by 1,2";
/* 106 */       boolean rtaDB = dat.parseSql(s);
/* 107 */       if (!rtaDB) {
/* 108 */         return resultados;
/*     */       }
/* 110 */       ResultSet rs = dat.getResultSet();
/* 111 */       while (rs.next()) {
/* 112 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 115 */     catch (Exception e) {
/* 116 */       e.printStackTrace();
/* 117 */       Utilidades.writeError("DetalleAuditoriaDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 120 */       dat.close();
/*     */     } 
/* 122 */     return resultados;
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
/*     */   public Collection<DetalleAuditoriaDTO> cargarTodos(int solicitud) {
/* 134 */     Collection<DetalleAuditoriaDTO> resultados = new ArrayList<DetalleAuditoriaDTO>();
/*     */     
/* 136 */    DBManager dat = new DBManager();
/*     */     try {
/* 138 */       String s = "select t.ciclo,t.codigo_empleado,r1.APELLIDOS || ' ' || r1.nombres as nombre_codigo_empleado,t.consecutivo,t.rol,m2.DESCRIPCION as nombre_rol,t.area_auditada,t.persona_auditada,t.asistio,t.generado,t.solicitud,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from DETALLE_AUDITORIA t  left join SIS_USUARIOS r1 on (r1.CODIGO_EMPLEADO=t.codigo_empleado) left join sis_multivalores m2 on (m2.tabla='ROL_AUDITOR' and m2.VALOR=t.rol) where solicitud=" + solicitud;
/*     */ 

/*     */       
/* 158 */       s = s + " order by 1,2";
/* 159 */       boolean rtaDB = dat.parseSql(s);
/* 160 */       if (!rtaDB) {
/* 161 */         return resultados;
/*     */       }
/* 163 */       ResultSet rs = dat.getResultSet();
/* 164 */       while (rs.next()) {
/* 165 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 168 */     catch (Exception e) {
/* 169 */       e.printStackTrace();
/* 170 */       Utilidades.writeError("DetalleAuditoriaDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 173 */       dat.close();
/*     */     } 
/* 175 */     return resultados;
/*     */   }
/*     */ 

/*     */ 
/*     */   
/*     */   public DetalleAuditoriaDTO cargarRegistro(String ciclo, int codigoEmpleado, int consecutivo) {
/* 189 */  DBManager   dat = new DBManager();
/*     */     try {
/* 191 */       String s = "select t.ciclo,t.codigo_empleado,r1.APELLIDOS || ' ' || r1.nombres as nombre_codigo_empleado,t.consecutivo,t.rol,m2.DESCRIPCION as nombre_rol,t.area_auditada,t.persona_auditada,t.asistio,t.generado,t.solicitud,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from DETALLE_AUDITORIA t  left join SIS_USUARIOS r1 on (r1.CODIGO_EMPLEADO=t.codigo_empleado) left join sis_multivalores m2 on (m2.tabla='ROL_AUDITOR' and m2.VALOR=t.rol) where  t.ciclo='" + ciclo + "'" + " and t.codigo_empleado=" + codigoEmpleado + " and t.consecutivo=" + consecutivo + "";
/*     */ 

/*     */       
/* 215 */       boolean rtaDB = dat.parseSql(s);
/* 216 */       if (!rtaDB) {
/* 217 */         return null;
/*     */       }
/* 219 */       ResultSet rs = dat.getResultSet();
/* 220 */       if (rs.next()) {
/* 221 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 224 */     catch (Exception e) {
/* 225 */       e.printStackTrace();
/* 226 */       Utilidades.writeError("DetalleAuditoriaDAO:cargarDetalleAuditoria", e);
/*     */     } finally {
/*     */       
/* 229 */       dat.close();
/*     */     } 
/* 231 */     return null;
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
/*     */   public DetalleAuditoriaDTO cargarRegistro(String ciclo, int auditor, int areaAuditada, int personaAuditada) {
/* 248 */   DBManager  dat = new DBManager();
/*     */     try {
/* 250 */       String s = "select t.ciclo,t.codigo_empleado,r1.APELLIDOS || ' ' || r1.nombres as nombre_codigo_empleado,t.consecutivo,t.rol,m2.DESCRIPCION as nombre_rol,t.area_auditada,t.persona_auditada,t.asistio,t.generado,t.solicitud,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from DETALLE_AUDITORIA t  left join SIS_USUARIOS r1 on (r1.CODIGO_EMPLEADO=t.codigo_empleado) left join sis_multivalores m2 on (m2.tabla='ROL_AUDITOR' and m2.VALOR=t.rol) where  t.ciclo='" + ciclo + "'" + " and t.codigo_empleado=" + auditor + " and t.area_auditada=" + areaAuditada + "  and t.persona_auditada=" + personaAuditada;
/*     */ 

/*     */       
/* 274 */       boolean rtaDB = dat.parseSql(s);
/* 275 */       if (!rtaDB) {
/* 276 */         return null;
/*     */       }
/* 278 */       ResultSet rs = dat.getResultSet();
/* 279 */       if (rs.next()) {
/* 280 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 283 */     catch (Exception e) {
/* 284 */       e.printStackTrace();
/* 285 */       Utilidades.writeError("DetalleAuditoriaDAO:cargarDetalleAuditoria", e);
/*     */     } finally {
/*     */       
/* 288 */       dat.close();
/*     */     } 
/* 290 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(Collection<DetalleAuditoriaDTO> arr) {
/* 301 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 303 */   DBManager  dat = new DBManager();
/*     */     try {
/* 305 */       Iterator<DetalleAuditoriaDTO> iterator = arr.iterator();
/* 306 */       while (iterator.hasNext()) {
/* 307 */         DetalleAuditoriaDTO reg = (DetalleAuditoriaDTO)iterator.next();
/*     */         
/* 309 */         String s = "delete from DETALLE_AUDITORIA where  ciclo='" + reg.getCiclo() + "'" + "  and codigo_empleado=" + reg.getCodigoEmpleado() + "  and consecutivo=" + reg.getConsecutivo() + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 315 */         rta = dat.executeUpdate2(s);
/*     */       }
/*     */     
/* 318 */     } catch (Exception e) {
/* 319 */       e.printStackTrace();
/* 320 */       Utilidades.writeError("DetalleAuditoriaDAO:eliminarRegistro ", e);
/* 321 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 324 */       dat.close();
/*     */     } 
/* 326 */     return rta;
/*     */   }
/*     */ 
/*     */ 

/*     */ 
/*     */   
/*     */   public RespuestaBD crearRegistro(String ciclo, int codigoEmpleado, int consecutivo, String rol, int areaAuditada, int personaAuditada, String usuarioInsercion) {
/* 343 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 345 */    DBManager dat = new DBManager();
/*     */     try {
/* 347 */       String s = "insert into DETALLE_AUDITORIA(ciclo,codigo_empleado,consecutivo,rol,area_auditada,persona_auditada,usuario_insercion,fecha_insercion) values ('" + ciclo + "'," + "" + codigoEmpleado + "," + "(select  coalesce(max(consecutivo),0)+1 from DETALLE_AUDITORIA where ciclo='" + ciclo + "' and codigo_empleado=" + codigoEmpleado + ")," + "'" + rol + "'," + "" + areaAuditada + "," + "" + personaAuditada + "," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
/*     */ 

/*     */       
/* 366 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 368 */     catch (Exception e) {
/* 369 */       e.printStackTrace();
/* 370 */       Utilidades.writeError("%DetalleAuditoriaDAO:crearRegistro ", e);
/* 371 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 374 */       dat.close();
/*     */     } 
/* 376 */     return rta;
/*     */   }
/*     */ 

/*     */   
/*     */   public RespuestaBD modificarRegistro(String ciclo, int codigoEmpleado, int consecutivo, String rol, int areaAuditada, int personaAuditada, String usuarioModificacion) {
/* 393 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 395 */    DBManager dat = new DBManager();
/*     */     try {
/* 397 */       String s = "update DETALLE_AUDITORIA set  rol='" + rol + "'," + " area_auditada=" + areaAuditada + "," + " persona_auditada=" + personaAuditada + "," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " ciclo='" + ciclo + "'" + " and codigo_empleado=" + codigoEmpleado + " and consecutivo=" + consecutivo + "";
/*     */ 

/*     */       
/* 408 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 410 */     catch (Exception e) {
/* 411 */       e.printStackTrace();
/* 412 */       Utilidades.writeError("DetalleAuditoriaDAO:modificarRegistro ", e);
/* 413 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 416 */       dat.close();
/*     */     } 
/* 418 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\DetalleAuditoriaDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */