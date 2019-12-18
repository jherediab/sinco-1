/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.CiclosAuditoriaDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CiclosAuditoriaDAO;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CiclosAuditoriaDAO
/*     */ {
/*     */   public CiclosAuditoriaDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  35 */       CiclosAuditoriaDTO reg = new CiclosAuditoriaDTO();
/*     */       
/*  37 */       reg.setCiclo(rs.getString("ciclo"));
/*  38 */       reg.setEstado(rs.getString("estado"));
/*  39 */       reg.setDescripcion(rs.getString("descripcion"));
/*  40 */       reg.setFechaCierre(rs.getString("fecha_cierre"));
/*  41 */       reg.setServicioAuditoria(rs.getInt("servicio_auditoria"));
/*  42 */       reg.setServicioCalificarGrupo(rs.getInt("servicio_calificar_grupo"));
/*  43 */       reg.setServicioCalificarAuditor(rs.getInt("servicio_calificar_auditor"));
/*  44 */       reg.setMensajeRealizarAuditoria(rs.getString("mensaje_realizar_auditoria"));
/*  45 */       reg.setMensajeCalificarAuditor(rs.getString("mensaje_calificar_auditor"));
/*  46 */       reg.setMensajeCalificarGrupo(rs.getString("mensaje_calificar_grupo"));
/*  47 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  48 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  49 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  50 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  51 */       reg.setNombreEstado(rs.getString("nombre_estado"));
/*  52 */       return reg;
/*     */     }
/*  54 */     catch (Exception e) {
/*  55 */       e.printStackTrace();
/*  56 */       Utilidades.writeError("CiclosAuditoriaDAO:leerRegistro ", e);
/*     */       
/*  58 */       return null;
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
/*     */   public Collection<CiclosAuditoriaDTO> cargarTodos(String ciclo, String estado, String descripcion) {
/*  71 */     Collection<CiclosAuditoriaDTO> resultados = new ArrayList<CiclosAuditoriaDTO>();
/*     */     
/*  73 */    DBManager dat = new DBManager();
/*     */     try {
/*  75 */       String s = "select t.ciclo,t.estado,m1.DESCRIPCION as nombre_estado,t.descripcion,t.fecha_cierre,t.servicio_auditoria,t.servicio_calificar_grupo,t.servicio_calificar_auditor,t.mensaje_realizar_auditoria,t.mensaje_calificar_auditor,t.mensaje_calificar_grupo,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from CICLOS_AUDITORIA t  left join sis_multivalores m1 on (m1.tabla='ESTADO_CICLOS_AUDITORIA' and m1.VALOR=t.estado) where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  94 */       if (ciclo.length() > 0) {
/*  95 */         s = s + " and upper(t.ciclo) like upper('%" + ciclo + "%')";
/*     */       }
/*  97 */       if (estado.length() > 0) {
/*  98 */         s = s + " and upper(t.estado) like upper('%" + estado + "%')";
/*     */       }
/* 100 */       if (descripcion.length() > 0) {
/* 101 */         s = s + " and upper(t.descripcion) like upper('%" + descripcion + "%')";
/*     */       }
/* 103 */       s = s + " order by 1";
/* 104 */       boolean rtaDB = dat.parseSql(s);
/* 105 */       if (!rtaDB) {
/* 106 */         return resultados;
/*     */       }
/* 108 */       ResultSet rs = dat.getResultSet();
/* 109 */       while (rs.next()) {
/* 110 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 113 */     catch (Exception e) {
/* 114 */       e.printStackTrace();
/* 115 */       Utilidades.writeError("CiclosAuditoriaDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 118 */       dat.close();
/*     */     } 
/* 120 */     return resultados;
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
/*     */   public Collection<CiclosAuditoriaDTO> cargarTodos(int anno) {
/* 132 */     Collection<CiclosAuditoriaDTO> resultados = new ArrayList<CiclosAuditoriaDTO>();
/*     */     
/* 134 */   DBManager  dat = new DBManager();
/*     */     try {
/* 136 */       String s = "select t.ciclo,t.estado,m1.DESCRIPCION as nombre_estado,t.descripcion,t.fecha_cierre,t.servicio_auditoria,t.servicio_calificar_grupo,t.servicio_calificar_auditor,t.mensaje_realizar_auditoria,t.mensaje_calificar_auditor,t.mensaje_calificar_grupo,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from CICLOS_AUDITORIA t  left join sis_multivalores m1 on (m1.tabla='ESTADO_CICLOS_AUDITORIA' and m1.VALOR=t.estado) where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 155 */       if (anno > 0) {
/* 156 */         s = s + " and t.ciclo like '" + anno + "%'";
/*     */       }
/*     */       
/* 159 */       s = s + " order by t.ciclo";
/* 160 */       boolean rtaDB = dat.parseSql(s);
/* 161 */       if (!rtaDB) {
/* 162 */         return resultados;
/*     */       }
/* 164 */       ResultSet rs = dat.getResultSet();
/* 165 */       while (rs.next()) {
/* 166 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 169 */     catch (Exception e) {
/* 170 */       e.printStackTrace();
/* 171 */       Utilidades.writeError("CiclosAuditoriaDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 174 */       dat.close();
/*     */     } 
/* 176 */     return resultados;
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
/*     */   public CiclosAuditoriaDTO cargarRegistro(String ciclo) {
/* 188 */   DBManager  dat = new DBManager();
/*     */     try {
/* 190 */       String s = "select t.ciclo,t.estado,m1.DESCRIPCION as nombre_estado,t.descripcion,t.fecha_cierre,t.servicio_auditoria,t.servicio_calificar_grupo,t.servicio_calificar_auditor,t.mensaje_realizar_auditoria,t.mensaje_calificar_auditor,t.mensaje_calificar_grupo,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from CICLOS_AUDITORIA t  left join sis_multivalores m1 on (m1.tabla='ESTADO_CICLOS_AUDITORIA' and m1.VALOR=t.estado) where  t.ciclo='" + ciclo + "'" + "";
/*     */ 

/*     */ 
/*     */       
/* 211 */       boolean rtaDB = dat.parseSql(s);
/* 212 */       if (!rtaDB) {
/* 213 */         return null;
/*     */       }
/* 215 */       ResultSet rs = dat.getResultSet();
/* 216 */       if (rs.next()) {
/* 217 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 220 */     catch (Exception e) {
/* 221 */       e.printStackTrace();
/* 222 */       Utilidades.writeError("CiclosAuditoriaDAO:cargarCiclosAuditoria", e);
/*     */     } finally {
/*     */       
/* 225 */       dat.close();
/*     */     } 
/* 227 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(String ciclo) {
/* 237 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 239 */    DBManager dat = new DBManager();
/*     */     try {
/* 241 */       String s = "delete from CICLOS_AUDITORIA where  ciclo='" + ciclo + "'" + "";
/*     */ 
/*     */ 
/*     */       
/* 245 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 247 */     catch (Exception e) {
/* 248 */       e.printStackTrace();
/* 249 */       Utilidades.writeError("CiclosAuditoriaDAO:eliminarRegistro ", e);
/* 250 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 253 */       dat.close();
/*     */     } 
/* 255 */     return rta;
/*     */   }
/*     */ 

/*     */   
/*     */   public RespuestaBD crearRegistro(String ciclo, String estado, String descripcion, String fechaCierre, int servicioAuditoria, int servicioCalificarGrupo, int servicioCalificarAuditor, String mensajeRealizarAuditoria, String mensajeCalificarAuditor, String mensajeCalificarGrupo, String usuarioInsercion) {
/* 276 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 278 */  DBManager   dat = new DBManager();
/*     */     try {
/* 280 */       String s = "insert into CICLOS_AUDITORIA(ciclo,estado,descripcion,fecha_cierre,servicio_auditoria,servicio_calificar_grupo,servicio_calificar_auditor,mensaje_realizar_auditoria,mensaje_calificar_auditor,mensaje_calificar_grupo,fecha_insercion,usuario_insercion) values ('" + ciclo + "'," + "'" + estado + "'," + "'" + descripcion + "'," + "" + ((fechaCierre.length() > 0) ? Utilidades.formatoFecha2(fechaCierre) : "null") + "," + "" + servicioAuditoria + "," + "" + servicioCalificarGrupo + "," + "" + servicioCalificarAuditor + "," + "'" + mensajeRealizarAuditoria + "'," + "'" + mensajeCalificarAuditor + "'," + "'" + mensajeCalificarGrupo + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 

/*     */       
/* 307 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 309 */     catch (Exception e) {
/* 310 */       e.printStackTrace();
/* 311 */       Utilidades.writeError("%CiclosAuditoriaDAO:crearRegistro ", e);
/* 312 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 315 */       dat.close();
/*     */     } 
/* 317 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(String ciclo, String estado, String descripcion, String fechaCierre, int servicioAuditoria, int servicioCalificarGrupo, int servicioCalificarAuditor, String mensajeRealizarAuditoria, String mensajeCalificarAuditor, String mensajeCalificarGrupo, String usuarioModificacion) {
/* 338 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 340 */    DBManager dat = new DBManager();
/*     */     try {
/* 342 */       String s = "update CICLOS_AUDITORIA set  estado='" + estado + "'," + " descripcion='" + descripcion + "'," + " fecha_cierre=" + ((fechaCierre.length() > 0) ? Utilidades.formatoFecha2(fechaCierre) : "null") + "," + " servicio_auditoria=" + servicioAuditoria + "," + " servicio_calificar_grupo=" + servicioCalificarGrupo + "," + " servicio_calificar_auditor=" + servicioCalificarAuditor + "," + " mensaje_realizar_auditoria='" + mensajeRealizarAuditoria + "'," + " mensaje_calificar_auditor='" + mensajeCalificarAuditor + "'," + " mensaje_calificar_grupo='" + mensajeCalificarGrupo + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " ciclo='" + ciclo + "'" + "";
/*     */ 
/*     */ 

/*     */       
/* 357 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 359 */     catch (Exception e) {
/* 360 */       e.printStackTrace();
/* 361 */       Utilidades.writeError("CiclosAuditoriaDAO:modificarRegistro ", e);
/* 362 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 365 */       dat.close();
/*     */     } 
/* 367 */     return rta;
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
/*     */   public RespuestaBD enviarSolicitudes(String ciclo, int idUsuario, String usuarioModificacion) {
/* 382 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 384 */    DBManager dat = new DBManager();
/*     */     try {
/* 386 */       rta = dat.enviarSolicitudes(ciclo, idUsuario, usuarioModificacion);
/*     */     }
/* 388 */     catch (Exception e) {
/* 389 */       e.printStackTrace();
/* 390 */       Utilidades.writeError("CiclosAuditoriaDAO:modificarRegistro ", e);
/* 391 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 394 */       dat.close();
/*     */     } 
/* 396 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\CiclosAuditoriaDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */