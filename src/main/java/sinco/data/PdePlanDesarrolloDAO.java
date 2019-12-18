/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PdeNivelPlanDTO;
/*     */ import sinco.business.PdePlanDesarrolloDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PdePlanDesarrolloDAO;
/*     */ 

/*     */ 
/*     */ public class PdePlanDesarrolloDAO
/*     */ {
    
    
/*     */   public PdePlanDesarrolloDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  36 */       PdePlanDesarrolloDTO reg = new PdePlanDesarrolloDTO();
/*     */       
/*  38 */       reg.setIdPlanDesarrollo(rs.getInt("id_plan_desarrollo"));
/*  39 */       reg.setNitEntidad(rs.getLong("nit_entidad"));
/*  40 */       reg.setNombrePlanDesarrollo(rs.getString("nombre_plan_desarrollo"));
/*  41 */       reg.setAprobacion(rs.getString("aprobacion"));
/*  42 */       reg.setNroAprobacion(rs.getInt("nro_aprobacion"));
/*  43 */       reg.setFechaAprobacion(rs.getString("fecha_aprobacion"));
/*  44 */       reg.setFechaInicial(rs.getString("fecha_inicial"));
/*  45 */       reg.setFechaFinal(rs.getString("fecha_final"));
/*  46 */       reg.setPlanCargado(rs.getString("cargado"));
/*  47 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  48 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  49 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  50 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  51 */       return reg;
/*     */     }
/*  53 */     catch (Exception e) {
/*  54 */       e.printStackTrace();
/*  55 */       Utilidades.writeError("PdePlanDesarrolloDAO:leerRegistro ", e);
/*     */       
/*  57 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<PdePlanDesarrolloDTO> cargarTodos(long nitEntidad, String nombrePlanDesarrollo) {
/*  68 */     Collection<PdePlanDesarrolloDTO> resultados = new ArrayList<PdePlanDesarrolloDTO>();
/*     */     
/*  70 */     DBManager dat = new DBManager();
/*     */     try {
/*  72 */       String s = "select t.id_plan_desarrollo,t.nit_entidad,t.nombre_plan_desarrollo,t.aprobacion,t.nro_aprobacion,t.fecha_aprobacion,t.fecha_inicial,t.fecha_final,t.cargado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from pde_plan_desarrollo t  where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  88 */       if (nitEntidad > 0L) {
/*  89 */         s = s + " and t.nit_entidad=" + nitEntidad;
/*     */       }
/*  91 */       if (nombrePlanDesarrollo.length() > 0) {
/*  92 */         s = s + " and upper(t.nombre_plan_desarrollo) like upper('%" + nombrePlanDesarrollo + "%')";
/*     */       }
/*  94 */       s = s + " order by 1";
/*  95 */       boolean rtaDB = dat.parseSql(s);
/*  96 */       if (!rtaDB) {
/*  97 */         return resultados;
/*     */       }
/*  99 */       ResultSet rs = dat.getResultSet();
/* 100 */       while (rs.next()) {
/* 101 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 104 */     catch (Exception e) {
/* 105 */       e.printStackTrace();
/* 106 */       Utilidades.writeError("PdePlanDesarrolloDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 109 */       dat.close();
/*     */     } 
/* 111 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PdePlanDesarrolloDTO cargarRegistro(int idPlanDesarrollo, long nitEntidad) {
/* 122 */   DBManager  dat = new DBManager();
/*     */     try {
/* 124 */       String s = "select t.id_plan_desarrollo,t.nit_entidad,t.nombre_plan_desarrollo,t.aprobacion,t.nro_aprobacion,t.fecha_aprobacion,t.fecha_inicial,t.fecha_final,t.cargado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from pde_plan_desarrollo t  where  t.id_plan_desarrollo=" + idPlanDesarrollo;

/*     */       
/* 141 */       if (nitEntidad > 0L) {
/* 142 */         s = s + " and t.nit_entidad=" + nitEntidad;
/*     */       }
/* 144 */       boolean rtaDB = dat.parseSql(s);
/* 145 */       if (!rtaDB) {
/* 146 */         return null;
/*     */       }
/* 148 */       ResultSet rs = dat.getResultSet();
/* 149 */       if (rs.next()) {
/* 150 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 153 */     catch (Exception e) {
/* 154 */       e.printStackTrace();
/* 155 */       Utilidades.writeError("PdePlanDesarrolloDAO:cargarPdePlanDesarrollo", e);
/*     */     } finally {
/*     */       
/* 158 */       dat.close();
/*     */     } 
/* 160 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 169 */     int inumero = 1;
/* 170 */     String s = "select max(id_plan_desarrollo) from pde_plan_desarrollo ";
/*     */ 
/*     */     
/* 173 */    DBManager dat = new DBManager();
/*     */     try {
/* 175 */       boolean rta = dat.parseSql(s);
/* 176 */       if (!rta) return 0; 
/* 177 */       ResultSet rs = dat.getResultSet();
/* 178 */       if (rs.next()) {
/* 179 */         s = rs.getString(1);
/* 180 */         if (!rs.wasNull()) {
/* 181 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 184 */       return inumero;
/*     */     }
/* 186 */     catch (Exception e) {
/* 187 */       e.printStackTrace();
/* 188 */       Utilidades.writeError("PdePlanDesarrolloDAO:siguienteRegistro ", e);
/*     */     } finally {
/*     */       
/* 191 */       dat.close();
/*     */     } 
/* 193 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int contarNivelesPlan(int idPlanDesarrollo) {
/* 203 */     int inumero = 1;
/* 204 */     String s = "select count(id_plan_desarrollo) from pde_nivel_plan where id_plan_desarrollo=" + idPlanDesarrollo;
/*     */     
/* 206 */    DBManager dat = new DBManager();
/*     */     try {
/* 208 */       boolean rta = dat.parseSql(s);
/* 209 */       if (!rta) return 0; 
/* 210 */       ResultSet rs = dat.getResultSet();
/* 211 */       if (rs.next()) {
/* 212 */         s = rs.getString(1);
/* 213 */         if (!rs.wasNull()) {
/* 214 */           inumero = Integer.parseInt(s);
/*     */         }
/*     */       } 
/* 217 */       return inumero;
/*     */     }
/* 219 */     catch (Exception e) {
/* 220 */       e.printStackTrace();
/* 221 */       Utilidades.writeError("PdePlanDesarrolloDAO:contarNivelesPlan ", e);
/*     */     } finally {
/*     */       
/* 224 */       dat.close();
/*     */     } 
/* 226 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int idPlanDesarrollo, long nitEntidad) {
/* 237 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 239 */   DBManager  dat = new DBManager();
/*     */     try {
/* 241 */       String s = "delete from pde_plan_desarrollo where  id_plan_desarrollo=" + idPlanDesarrollo + "  and nit_entidad=" + nitEntidad + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 246 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 248 */     catch (Exception e) {
/* 249 */       e.printStackTrace();
/* 250 */       Utilidades.writeError("PdePlanDesarrolloDAO:eliminarRegistro ", e);
/* 251 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 254 */       dat.close();
/*     */     } 
/* 256 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int idPlanDesarrollo, long nitEntidad, String nombrePlanDesarrollo, String aprobacion, int nroAprobacion, String fechaAprobacion, String fechaInicial, String fechaFinal, Collection<PdeNivelPlanDTO> resultados, String usuarioInsercion) {
/* 276 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 278 */     int elSiguiente = siguienteRegistro();
/* 279 */     if (elSiguiente == 0) {
/* 280 */       rta.setMensaje("Generando secuencia");
/* 281 */       return rta;
/*     */     } 
/*     */     
/* 284 */   DBManager  dat = new DBManager();
/*     */     try {
/* 286 */       String s = "insert into pde_plan_desarrollo(id_plan_desarrollo,nit_entidad,nombre_plan_desarrollo,aprobacion,nro_aprobacion,fecha_aprobacion,fecha_inicial,fecha_final,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "" + nitEntidad + "," + "'" + nombrePlanDesarrollo + "'," + "'" + aprobacion + "'," + "" + nroAprobacion + "," + "" + ((fechaAprobacion.length() > 0) ? Utilidades.formatoFecha2(fechaAprobacion) : "null") + "," + "" + ((fechaInicial.length() > 0) ? Utilidades.formatoFecha2(fechaInicial) : "null") + "," + "" + ((fechaFinal.length() > 0) ? Utilidades.formatoFecha2(fechaFinal) : "null") + "," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 

/*     */       
/* 309 */       rta = dat.executeUpdate2(s);
/* 310 */       rta.setSecuencia(elSiguiente);
/*     */ 
/*     */     
/*     */     }
/* 314 */     catch (Exception e) {
/* 315 */       e.printStackTrace();
/* 316 */       Utilidades.writeError("%PdePlanDesarrolloDAO:crearRegistro ", e);
/* 317 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 320 */       dat.close();
/*     */     } 
/* 322 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int idPlanDesarrollo, long nitEntidad, String nombrePlanDesarrollo, String aprobacion, int nroAprobacion, String fechaAprobacion, String fechaInicial, String fechaFinal, String usuarioModificacion) {
/* 343 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 345 */   DBManager  dat = new DBManager();
/*     */     try {
/* 347 */       String s = "update pde_plan_desarrollo set  nombre_plan_desarrollo='" + nombrePlanDesarrollo + "'," + " aprobacion='" + aprobacion + "'," + " nro_aprobacion=" + nroAprobacion + "," + " fecha_aprobacion=" + ((fechaAprobacion.length() > 0) ? Utilidades.formatoFecha2(fechaAprobacion) : "null") + "," + " fecha_inicial=" + ((fechaInicial.length() > 0) ? Utilidades.formatoFecha2(fechaInicial) : "null") + "," + " fecha_final=" + ((fechaFinal.length() > 0) ? Utilidades.formatoFecha2(fechaFinal) : "null") + "," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " id_plan_desarrollo=" + idPlanDesarrollo + "";

/*     */       
/* 359 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 361 */     catch (Exception e) {
/* 362 */       e.printStackTrace();
/* 363 */       Utilidades.writeError("PdePlanDesarrolloDAO:modificarRegistro ", e);
/* 364 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 367 */       dat.close();
/*     */     } 
/* 369 */     return rta;
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
/*     */   public RespuestaBD guardarPlan(int idPlanDesarrollo, String usuarioModificacion) {
/* 383 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 385 */  DBManager   dat = new DBManager();
/*     */     try {
/* 387 */       String s = "update pde_plan_desarrollo set  cargado='S', fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " id_plan_desarrollo=" + idPlanDesarrollo + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 394 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 396 */     catch (Exception e) {
/* 397 */       e.printStackTrace();
/* 398 */       Utilidades.writeError("PdePlanDesarrolloDAO:guardarPlan ", e);
/* 399 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 402 */       dat.close();
/*     */     } 
/* 404 */     return rta;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean planCargado(int idPlanDesarrollo) {
/* 410 */   DBManager  dat = new DBManager();
/*     */     try {
/* 412 */       String s = "select *  from pde_plan_desarrollo  where id_plan_desarrollo=" + idPlanDesarrollo + "" + " and cargado='S'";
/*     */ 
/*     */ 
/*     */       
/* 416 */       dat.parseSql(s);
/* 417 */       ResultSet rs = dat.getResultSet();
/* 418 */       if (rs.next()) {
/* 419 */         return true;
/*     */       }
/* 421 */       return false;
/*     */     }
/* 423 */     catch (Exception e) {
/* 424 */       e.printStackTrace();
/* 425 */       Utilidades.writeError("PdePlanDesarrolloDAO:planCargado " + e.getMessage());
/*     */     } finally {
/*     */       
/* 428 */       dat.close();
/*     */     } 
/*     */     
/* 431 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PdePlanDesarrolloDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */