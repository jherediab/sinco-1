/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.AreasAuditadasDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AreasAuditadasDAO;
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
/*     */ public class AreasAuditadasDAO
/*     */ {
/*     */   public AreasAuditadasDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  35 */       AreasAuditadasDTO reg = new AreasAuditadasDTO();
/*     */       
/*  37 */       reg.setCiclo(rs.getInt("ciclo"));
/*  38 */       reg.setArea(rs.getInt("area"));
/*  39 */       reg.setNombreArea(rs.getString("nombre_area"));
/*  40 */       reg.setCodigoProceso(rs.getString("codigo_proceso"));
/*  41 */       reg.setNombreProceso(rs.getString("nombre"));
/*  42 */       reg.setCoordinadorProceso(rs.getString("coordinador"));
/*  43 */       reg.setEquipoProceso(rs.getString("equipo"));
/*  44 */       reg.setCodigoInforme(rs.getInt("codigo_informe"));
/*  45 */       reg.setNombreInforme(rs.getString("nombre"));
/*  46 */       reg.setCoordinadorInforme(rs.getString("coordinador"));
/*  47 */       reg.setEquipoInforme(rs.getString("equipo"));
/*  48 */       reg.setAsociadoA(rs.getString("asociado_a"));
/*  49 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  50 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  51 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  52 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  53 */       reg.setNombreCoordinador(rs.getString("nombre_coordinador"));
/*     */ 
/*     */       
/*  56 */       return reg;
/*     */     }
/*  58 */     catch (Exception e) {
/*  59 */       e.printStackTrace();
/*  60 */       Utilidades.writeError("AreasAuditadasDAO:leerRegistro ", e);
/*     */       
/*  62 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AreasAuditadasDTO leerRegistroAuditor(ResultSet rs) {
/*     */     try {
/*  72 */       AreasAuditadasDTO reg = new AreasAuditadasDTO();
/*     */       
/*  74 */       reg.setCiclo(rs.getInt("ciclo"));
/*  75 */       reg.setNombreArea(rs.getString("nombre_area"));
/*  76 */       reg.setCodigoProceso(rs.getString("codigo_proceso"));
/*  77 */       reg.setCodigoInforme(rs.getInt("codigo_informe"));
/*  78 */       reg.setNombreInforme(rs.getString("nombre"));
/*  79 */       reg.setNombreProceso(rs.getString("nombre"));
/*  80 */       reg.setNombreCoordinador(rs.getString("nombre_coordinador"));
/*  81 */       reg.setAsociadoA(rs.getString("asociado_a"));
/*  82 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  83 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  84 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  85 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  86 */       reg.setNombreCoordinador(rs.getString("nombre_coordinador"));
/*     */ 
/*     */       
/*  89 */       return reg;
/*     */     }
/*  91 */     catch (Exception e) {
/*  92 */       e.printStackTrace();
/*  93 */       Utilidades.writeError("AreasAuditadasDAO:leerRegistroAuditor ", e);
/*     */       
/*  95 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<AreasAuditadasDTO> cargarTodosAuditor(int auditor) {
/* 106 */     Collection<AreasAuditadasDTO> resultados = new ArrayList<AreasAuditadasDTO>();
/*     */     
/* 108 */     DBManager dat = new DBManager();
/*     */     try {
/* 110 */       String s = "SELECT ap.ciclo, p.descripcion AS nombre, m1.DESCRIPCION as nombre_coordinador,u.descripcion nombre_area, 'Proceso' as asociado_a, aud.codigo_proceso as codigo_proceso,0 as codigo_informe,aud.fecha_insercion, aud.usuario_insercion, aud.fecha_modificacion, aud.usuario_modificacion from aud_procesos_plan_anual aud, procesos p, aud_grupo_auditor g,aud_plan_anual ap, sis_multivalores m1, unidades_dependencia u, aud_areas_auditadas a where u.codigo=a.area and a.codigo_proceso=aud.codigo_proceso and aud.codigo_proceso=p.codigo and a.ciclo=aud.ciclo and ap.ciclo=aud.ciclo and g.codigo_proceso=aud.codigo_proceso and g.ciclo=aud.ciclo and UPPER(m1.Tabla) LIKE UPPER('cal_coordinador_auditoria') and UPPER(m1.Valor) LIKE UPPER(aud.coordinador_auditoria) and g.codigo_empleado=" + auditor + " UNION" + " SELECT ap.ciclo, p.nombre, m1.DESCRIPCION as nombre_coordinador,u.descripcion nombre_area, 'Informe' as asociado_a," + " '' as codigo_proceso,aud.codigo_informe as codigo_informe,aud.fecha_insercion, aud.usuario_insercion, aud.fecha_modificacion, aud.usuario_modificacion" + " from aud_informes_plan_anual aud, aud_informes p, aud_grupo_auditor g,aud_plan_anual ap, sis_multivalores m1," + " unidades_dependencia u, aud_areas_auditadas a" + " where u.codigo=a.area" + " and a.codigo_informe=aud.codigo_informe" + " and aud.codigo_informe=p.codigo " + " and a.ciclo=aud.ciclo" + " and ap.ciclo=aud.ciclo " + " and g.codigo_informe=aud.codigo_informe" + " and g.ciclo=aud.ciclo" + " and UPPER(m1.Tabla) LIKE UPPER('cal_coordinador_auditoria')" + " and UPPER(m1.Valor) LIKE UPPER(aud.coordinador_auditoria)" + " and g.codigo_empleado=" + auditor + " order by 1";
/*     */ 

/*     */       
/* 140 */       boolean rtaDB = dat.parseSql(s);
/* 141 */       if (!rtaDB) {
/* 142 */         return resultados;
/*     */       }
/* 144 */       ResultSet rs = dat.getResultSet();
/* 145 */       while (rs.next()) {
/* 146 */         resultados.add(leerRegistroAuditor(rs));
/*     */       }
/*     */     }
/* 149 */     catch (Exception e) {
/* 150 */       e.printStackTrace();
/* 151 */       Utilidades.writeError("AreasAuditadasDAO:cargarTodosAuditor ", e);
/*     */     } finally {
/*     */       
/* 154 */       dat.close();
/*     */     } 
/* 156 */     return resultados;
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
/*     */   public Collection<AreasAuditadasDTO> cargarTodos(int area) {
/* 168 */     Collection<AreasAuditadasDTO> resultados = new ArrayList<AreasAuditadasDTO>();
/*     */     
/* 170 */    DBManager dat = new DBManager();
/*     */     try {
/* 172 */       String s = "select a.ciclo,a.area,u.descripcion as nombre_area,a.codigo_proceso as codigo_proceso,0 as codigo_informe,p1.descripcion as nombre, ap.coordinador_auditoria as coordinador,ap.equipo_auditor as equipo, m1.DESCRIPCION as nombre_coordinador,a.asociado_a,a.usuario_insercion, a.fecha_insercion,a.usuario_modificacion,a.fecha_modificacion from aud_areas_auditadas a full outer join procesos p1 on (p1.codigo=a.codigo_proceso) full outer join aud_procesos_plan_anual ap on (ap.codigo_proceso=a.codigo_proceso and ap.ciclo=a.ciclo) full outer join unidades_dependencia u on (u.codigo=a.area), sis_multivalores m1 where a.area=" + area + " and UPPER(m1.Tabla) LIKE UPPER('cal_coordinador_auditoria')" + " and UPPER(m1.Valor) LIKE UPPER(ap.coordinador_auditoria)" + " UNION" + " select a.ciclo,a.area,u.descripcion as nombre_area,'' as codigo_proceso,a.codigo_informe as codigo_informe,p2.nombre as nombre," + " ai.coordinador_auditoria as coordinador,ai.equipo_auditor as equipo," + " m1.DESCRIPCION as nombre_coordinador,a.asociado_a,a.usuario_insercion," + " a.fecha_insercion,a.usuario_modificacion,a.fecha_modificacion" + " from aud_areas_auditadas a" + " full outer join aud_informes p2 on (p2.codigo=a.codigo_informe) " + " full outer join aud_informes_plan_anual ai on (ai.codigo_informe=a.codigo_informe and ai.ciclo=a.ciclo )" + " full outer join unidades_dependencia u on (u.codigo=a.area),sis_multivalores m1 " + " where a.area=" + area + " and UPPER(m1.Tabla) LIKE UPPER('cal_coordinador_auditoria')" + " and UPPER(m1.Valor) LIKE UPPER(ai.coordinador_auditoria)" + "order by 1";
/*     */ 

/*     */       
/* 197 */       boolean rtaDB = dat.parseSql(s);
/* 198 */       if (!rtaDB) {
/* 199 */         return resultados;
/*     */       }
/* 201 */       ResultSet rs = dat.getResultSet();
/* 202 */       while (rs.next()) {
/* 203 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 206 */     catch (Exception e) {
/* 207 */       e.printStackTrace();
/* 208 */       Utilidades.writeError("AreasAuditadasDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 211 */       dat.close();
/*     */     } 
/* 213 */     return resultados;
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
/*     */   public AreasAuditadasDTO cargarRegistro(String ciclo, int area, int personaAuditada) {
/* 225 */    DBManager dat = new DBManager();
/*     */     try {
/* 227 */       String s = "select t.ciclo,t.area,r1.descripcion as nombre_area,t.persona_auditada,r2.APELLIDOS as nombre_persona_auditada,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from AREAS_AUDITADAS t  left join unidades_dependencia r1 on (r1.codigo=t.area) left join SIS_USUARIOS r2 on (r2.CODIGO_EMPLEADO=t.persona_auditada) where  t.ciclo='" + ciclo + "'" + " and t.area=" + area + " and t.persona_auditada=" + personaAuditada + "";
/*     */ 

/*     */       
/* 245 */       boolean rtaDB = dat.parseSql(s);
/* 246 */       if (!rtaDB) {
/* 247 */         return null;
/*     */       }
/* 249 */       ResultSet rs = dat.getResultSet();
/* 250 */       if (rs.next()) {
/* 251 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 254 */     catch (Exception e) {
/* 255 */       e.printStackTrace();
/* 256 */       Utilidades.writeError("AreasAuditadasDAO:cargarAreasAuditadas", e);
/*     */     } finally {
/*     */       
/* 259 */       dat.close();
/*     */     } 
/* 261 */     return null;
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
/*     */   public RespuestaBD eliminarRegistro(String ciclo, int area, int personaAuditada) {
/* 273 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 275 */    DBManager dat = new DBManager();
/*     */     try {
/* 277 */       String s = "delete from AREAS_AUDITADAS where  ciclo='" + ciclo + "'" + "  and area=" + area + "  and persona_auditada=" + personaAuditada + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 283 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 285 */     catch (Exception e) {
/* 286 */       e.printStackTrace();
/* 287 */       Utilidades.writeError("AreasAuditadasDAO:eliminarRegistro ", e);
/* 288 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 291 */       dat.close();
/*     */     } 
/* 293 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(String ciclo, int area, int personaAuditada, String usuarioInsercion) {
/* 307 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 309 */    DBManager dat = new DBManager();
/*     */     try {
/* 311 */       String s = "insert into AREAS_AUDITADAS(ciclo,area,persona_auditada,usuario_insercion,fecha_insercion) values ('" + ciclo + "'," + "" + area + "," + "" + personaAuditada + "," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
/*     */ 

/*     */       
/* 324 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 326 */     catch (Exception e) {
/* 327 */       e.printStackTrace();
/* 328 */       Utilidades.writeError("%AreasAuditadasDAO:crearRegistro ", e);
/* 329 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 332 */       dat.close();
/*     */     } 
/* 334 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(String ciclo, int area, int personaAuditada, String usuarioModificacion) {
/* 348 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 350 */    DBManager dat = new DBManager();
/*     */     try {
/* 352 */       String s = "update AREAS_AUDITADAS set  usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " ciclo='" + ciclo + "'" + " and area=" + area + " and persona_auditada=" + personaAuditada + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 360 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 362 */     catch (Exception e) {
/* 363 */       e.printStackTrace();
/* 364 */       Utilidades.writeError("AreasAuditadasDAO:modificarRegistro ", e);
/* 365 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 368 */       dat.close();
/*     */     } 
/* 370 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\AreasAuditadasDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */