/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.AudProcesosPlanAnualDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AudProcesosPlanAnualDAO;
/*     */ import sinco.data.DBManager;
/*     */ 

/*     */ 
/*     */ public class AudProcesosPlanAnualDAO
/*     */ {
/*     */   public AudProcesosPlanAnualDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  35 */       AudProcesosPlanAnualDTO reg = new AudProcesosPlanAnualDTO();
/*     */       
/*  37 */       reg.setCiclo(rs.getInt("ciclo"));
/*  38 */       reg.setCodigoProceso(rs.getString("codigo_proceso"));
/*  39 */       reg.setAreaResponsable(rs.getInt("area_responsable"));
/*  40 */       reg.setTipoAuditoria(rs.getString("tipo_auditoria"));
/*  41 */       reg.setcoordinadorAuditoria(rs.getString("coordinador_auditoria"));
/*  42 */       reg.setEquipoAuditor(rs.getString("equipo_auditor"));
/*  43 */       reg.setMes01(rs.getString("mes01"));
/*  44 */       reg.setMes02(rs.getString("mes02"));
/*  45 */       reg.setMes03(rs.getString("mes03"));
/*  46 */       reg.setMes04(rs.getString("mes04"));
/*  47 */       reg.setMes05(rs.getString("mes05"));
/*  48 */       reg.setMes06(rs.getString("mes06"));
/*  49 */       reg.setMes07(rs.getString("mes07"));
/*  50 */       reg.setMes08(rs.getString("mes08"));
/*  51 */       reg.setMes09(rs.getString("mes09"));
/*  52 */       reg.setMes10(rs.getString("mes10"));
/*  53 */       reg.setMes11(rs.getString("mes11"));
/*  54 */       reg.setMes12(rs.getString("mes12"));
/*  55 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  56 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  57 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  58 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  59 */       reg.setNombreCodigoProceso(rs.getString("nombre_codigo_proceso"));
/*  60 */       reg.setNombreAreaResponsable(rs.getString("nombre_area_responsable"));
/*  61 */       reg.setNombreTipoAuditoria(rs.getString("nombre_tipo_auditoria"));
/*  62 */       reg.setNombrecoordinadorAuditoria(rs.getString("nombre_coordinador_auditoria"));
/*     */       
/*  64 */       reg.setTitulo(rs.getString("titulo"));
/*  65 */       reg.setObjetivos_especificos(rs.getString("objetivos_especificos"));
/*  66 */       reg.setAlcance(rs.getString("alcance"));
/*     */       
/*     */       try {
/*  69 */         reg.setNombreTipoProceso(rs.getString("nombre_tipo_proceso"));
/*  70 */         reg.setNombreLiderProceso(rs.getString("nombre_lider_proceso"));
/*     */       
/*     */       }
/*  73 */       catch (Exception e) {}
/*     */ 
/*     */       
/*  76 */       return reg;
/*     */     }
/*  78 */     catch (Exception e) {
/*  79 */       e.printStackTrace();
/*  80 */       Utilidades.writeError("AudProcesosPlanAnualDAO:leerRegistro ", e);
/*     */       
/*  82 */       return null;
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
/*     */   public Collection<AudProcesosPlanAnualDTO> cargarTodos(int ciclo, String tipoAuditoria) {
/*  95 */     Collection<AudProcesosPlanAnualDTO> resultados = new ArrayList<AudProcesosPlanAnualDTO>();
/*     */     
/*  97 */    DBManager dat = new DBManager();
/*     */     try {
/*  99 */       String s = "select t.ciclo,t.codigo_proceso,r1.Descripcion as nombre_codigo_proceso,t.area_responsable,ud.Descripcion as nombre_area_responsable,t.tipo_auditoria,m2.DESCRIPCION as nombre_tipo_auditoria,t.coordinador_auditoria,m3.DESCRIPCION as nombre_coordinador_auditoria,t.equipo_auditor,t.mes01,t.mes02,t.mes03,t.mes04,t.mes05,t.mes06,t.mes07,t.mes08,t.mes09,t.mes10,t.mes11,t.mes12,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion,t.titulo,t.objetivos_especificos,t.alcance from aud_procesos_plan_anual t  left join procesos r1 on (r1.Codigo=t.codigo_proceso) left join Unidades_Dependencia ud on (ud.Codigo=t.area_responsable) left join sis_multivalores m2 on (m2.tabla='CAL_TIPO_AUDITORIA' and m2.VALOR=t.tipo_auditoria) left join sis_multivalores m3 on (m3.tabla='CAL_COORDINADOR_AUDITORIA' and m3.VALOR=t.coordinador_auditoria) where 1=1";
/*     */ 

/*     */ 
/*     */ 
/*     */       
/* 135 */       if (ciclo > 0) {
/* 136 */         s = s + " and t.ciclo=" + ciclo;
/*     */       }
/* 138 */       if (tipoAuditoria.length() > 0) {
/* 139 */         s = s + " and t.tipo_auditoria='" + tipoAuditoria + "'";
/*     */       }
/*     */       
/* 142 */       s = s + " order by t.codigo_proceso";
/* 143 */       boolean rtaDB = dat.parseSql(s);
/* 144 */       if (!rtaDB) {
/* 145 */         return resultados;
/*     */       }
/* 147 */       ResultSet rs = dat.getResultSet();
/* 148 */       while (rs.next()) {
/* 149 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 152 */     catch (Exception e) {
/* 153 */       e.printStackTrace();
/* 154 */       Utilidades.writeError("AudProcesosPlanAnualDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 157 */       dat.close();
/*     */     } 
/* 159 */     return resultados;
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
/*     */   public Collection<AudProcesosPlanAnualDTO> cargarDeAuditor(int ciclo, int auditor) {
/* 173 */     Collection<AudProcesosPlanAnualDTO> resultados = new ArrayList<AudProcesosPlanAnualDTO>();
/*     */     
/* 175 */   DBManager  dat = new DBManager();
/*     */     try {
/* 177 */       String s = "select t.ciclo,t.codigo_proceso,r1.Descripcion as nombre_codigo_proceso,t.area_responsable,ud.Descripcion as nombre_area_responsable,t.tipo_auditoria,m2.DESCRIPCION as nombre_tipo_auditoria,t.coordinador_auditoria,m3.DESCRIPCION as nombre_coordinador_auditoria,t.equipo_auditor,t.mes01,t.mes02,t.mes03,t.mes04,t.mes05,t.mes06,t.mes07,t.mes08,t.mes09,t.mes10,t.mes11,t.mes12,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion,t.titulo,t.objetivos_especificos,t.alcance from aud_procesos_plan_anual t  left join Procesos r1 on (r1.Codigo=t.codigo_proceso) left join Unidades_Dependencia ud on (ud.Codigo=t.area_responsable) left join sis_multivalores m2 on (m2.tabla='CAL_TIPO_AUDITORIA' and m2.VALOR=t.tipo_auditoria) left join sis_multivalores m3 on (m3.tabla='CAL_COORDINADOR_AUDITORIA' and m3.VALOR=t.coordinador_auditoria) where t.ciclo=" + ciclo + " AND exists (select 'x'" + "  from   Aud_Grupo_Auditor e" + "  where  e.Ciclo = t.Ciclo" + "\t\t\tand e.Codigo_Empleado = " + auditor + "           and e.codigo_proceso=t.codigo_proceso" + "\t\t\tand e.Asociado_a = 'P')" + " order by t.codigo_proceso";
/*     */ 

/*     */       
/* 220 */       boolean rtaDB = dat.parseSql(s);
/* 221 */       if (!rtaDB) {
/* 222 */         return resultados;
/*     */       }
/* 224 */       ResultSet rs = dat.getResultSet();
/* 225 */       while (rs.next()) {
/* 226 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 229 */     catch (Exception e) {
/* 230 */       e.printStackTrace();
/* 231 */       Utilidades.writeError("AudProcesosPlanAnualDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 234 */       dat.close();
/*     */     } 
/* 236 */     return resultados;
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
/*     */   public Collection<AudProcesosPlanAnualDTO> cargarPlan(int ciclo, String tipoAuditoria) {
/* 249 */     Collection<AudProcesosPlanAnualDTO> resultados = new ArrayList<AudProcesosPlanAnualDTO>();
/*     */     
/* 251 */    DBManager dat = new DBManager();
/*     */     try {
/* 253 */       String s = "select t.ciclo,t.codigo_proceso,r1.Descripcion as nombre_codigo_proceso,t.area_responsable,ud.Descripcion as nombre_area_responsable,t.tipo_auditoria,m2.DESCRIPCION as nombre_tipo_auditoria,t.coordinador_auditoria,m3.DESCRIPCION as nombre_coordinador_auditoria,t.equipo_auditor,t.mes01,t.mes02,t.mes03,t.mes04,t.mes05,t.mes06,t.mes07,t.mes08,t.mes09,t.mes10,t.mes11,t.mes12,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion,t.titulo,t.objetivos_especificos,t.alcance,mtp.descripcion nombre_tipo_proceso from aud_procesos_plan_anual t  left join Procesos r1 on (r1.Codigo=t.codigo_proceso) left join Unidades_Dependencia ud on (ud.Codigo=t.area_responsable) left join sis_multivalores m2 on (m2.tabla='CAL_TIPO_AUDITORIA' and m2.VALOR=t.tipo_auditoria) left join sis_multivalores m3 on (m3.tabla='CAL_COORDINADOR_AUDITORIA' and m3.VALOR=t.coordinador_auditoria) left   join Sis_Multivalores Mtp on     (R1.Tipo_Proceso = Mtp.Valor and Mtp.Tabla = 'CAL_TIPO_PROCESO') where t.ciclo=" + ciclo;
/*     */ 
/*     */ 

/*     */       
/* 292 */       if (tipoAuditoria.length() > 0) {
/* 293 */         s = s + " and t.tipo_auditoria='" + tipoAuditoria + "'";
/*     */       }
/*     */       
/* 296 */       s = s + " order by t.codigo_proceso";
/* 297 */       boolean rtaDB = dat.parseSql(s);
/* 298 */       if (!rtaDB) {
/* 299 */         return resultados;
/*     */       }
/* 301 */       ResultSet rs = dat.getResultSet();
/* 302 */       while (rs.next()) {
/* 303 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 306 */     catch (Exception e) {
/* 307 */       e.printStackTrace();
/* 308 */       Utilidades.writeError("AudProcesosPlanAnualDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 311 */       dat.close();
/*     */     } 
/* 313 */     return resultados;
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
/*     */   public AudProcesosPlanAnualDTO cargarRegistro(int ciclo, String codigoProceso) {
/* 327 */    DBManager dat = new DBManager();
/*     */     try {
/* 329 */       String s = "select t.ciclo,t.codigo_proceso,r1.Descripcion as nombre_codigo_proceso,t.area_responsable,ud.Descripcion as nombre_area_responsable,t.tipo_auditoria,m2.DESCRIPCION as nombre_tipo_auditoria,t.coordinador_auditoria,m3.DESCRIPCION as nombre_coordinador_auditoria,t.equipo_auditor,t.mes01,t.mes02,t.mes03,t.mes04,t.mes05,t.mes06,t.mes07,t.mes08,t.mes09,t.mes10,t.mes11,t.mes12,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion,t.titulo,t.objetivos_especificos,t.alcance from aud_procesos_plan_anual t  left join Procesos r1 on (r1.Codigo=t.codigo_proceso) left join Unidades_Dependencia ud on (ud.Codigo=t.area_responsable) left join sis_multivalores m2 on (m2.tabla='CAL_TIPO_AUDITORIA' and m2.VALOR=t.tipo_auditoria) left join sis_multivalores m3 on (m3.tabla='CAL_COORDINADOR_AUDITORIA' and m3.VALOR=t.coordinador_auditoria) where  t.ciclo=" + ciclo + " and t.codigo_proceso='" + codigoProceso + "'" + "";
/*     */ 

/*     */       
/* 368 */       boolean rtaDB = dat.parseSql(s);
/* 369 */       if (!rtaDB) {
/* 370 */         return null;
/*     */       }
/* 372 */       ResultSet rs = dat.getResultSet();
/* 373 */       if (rs.next()) {
/* 374 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 377 */     catch (Exception e) {
/* 378 */       e.printStackTrace();
/* 379 */       Utilidades.writeError("AudProcesosPlanAnualDAO:cargarAudProcesosPlanAnual", e);
/*     */     } finally {
/*     */       
/* 382 */       dat.close();
/*     */     } 
/* 384 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int ciclo, String codigoProceso) {
/* 395 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 397 */   DBManager  dat = new DBManager();
/*     */     try {
/* 399 */       String s = "delete from aud_procesos_plan_anual where  ciclo=" + ciclo + "  and codigo_proceso='" + codigoProceso + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 404 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 406 */     catch (Exception e) {
/* 407 */       e.printStackTrace();
/* 408 */       Utilidades.writeError("AudProcesosPlanAnualDAO:eliminarRegistro ", e);
/* 409 */       rta.setMensaje(e.getMessage());
/*     */     } 
/*     */     try {
/* 412 */       String s = "delete from aud_areas_auditadas where  ciclo=" + ciclo + "  and codigo_proceso='" + codigoProceso + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 417 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 419 */     catch (Exception e) {
/* 420 */       e.printStackTrace();
/* 421 */       Utilidades.writeError("AudProcesosPlanAnualDAO:eliminarRegistroAreas ", e);
/* 422 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 425 */       dat.close();
/*     */     } 
/* 427 */     return rta;
/*     */   }
/*     */ 

/*     */   
/*     */   public RespuestaBD crearRegistro(int ciclo, String codigoProceso, int areaResponsable, String tipoAuditoria, String coordinadorAuditoria, String equipoAuditor, String mes01, String mes02, String mes03, String mes04, String mes05, String mes06, String mes07, String mes08, String mes09, String mes10, String mes11, String mes12, String titulo, String objetivos_especificos, String alcance, String usuarioInsercion) {
/* 459 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 461 */    DBManager dat = new DBManager();
/*     */     try {
/* 463 */       String s = "insert into aud_procesos_plan_anual(ciclo,codigo_proceso,area_responsable,tipo_auditoria,coordinador_auditoria,equipo_auditor,mes01,mes02,mes03,mes04,mes05,mes06,mes07,mes08,mes09,mes10,mes11,mes12,titulo,objetivos_especificos,alcance,fecha_insercion,usuario_insercion) values (" + ciclo + "," + "'" + codigoProceso + "'," + "" + areaResponsable + "," + "'" + tipoAuditoria + "'," + "'" + coordinadorAuditoria + "'," + "'" + equipoAuditor + "'," + "'" + mes01 + "'," + "'" + mes02 + "'," + "'" + mes03 + "'," + "'" + mes04 + "'," + "'" + mes05 + "'," + "'" + mes06 + "'," + "'" + mes07 + "'," + "'" + mes08 + "'," + "'" + mes09 + "'," + "'" + mes10 + "'," + "'" + mes11 + "'," + "'" + mes12 + "'," + "'" + titulo + "'," + "'" + objetivos_especificos + "'," + "'" + alcance + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 

/*     */       
/* 512 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 514 */     catch (Exception e) {
/* 515 */       e.printStackTrace();
/* 516 */       Utilidades.writeError("%AudProcesosPlanAnualDAO:crearRegistro ", e);
/* 517 */       rta.setMensaje(e.getMessage());
/*     */     } 
/*     */     try {
/* 520 */       String s = "insert into aud_areas_auditadas(ciclo,area,codigo_proceso,asociado_a,usuario_insercion,fecha_insercion) values (" + ciclo + "," + "" + areaResponsable + "," + "'" + codigoProceso + "'," + "'P'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 535 */       rta = dat.executeUpdate2(s);
/* 536 */     } catch (Exception e) {
/* 537 */       e.printStackTrace();
/* 538 */       Utilidades.writeError("%AudProcesosPlanAnualDAO:crearRegistroAreas ", e);
/* 539 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 542 */       dat.close();
/*     */     } 
/* 544 */     return rta;
/*     */   }

/*     */ 
/*     */   
/*     */   public RespuestaBD modificarRegistro(int ciclo, String codigoProceso, int areaResponsable, String tipoAuditoria, String coordinadorAuditoria, String equipoAuditor, String mes01, String mes02, String mes03, String mes04, String mes05, String mes06, String mes07, String mes08, String mes09, String mes10, String mes11, String mes12, String titulo, String objetivos_especificos, String alcance, String usuarioModificacion) {
/* 576 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 578 */   DBManager  dat = new DBManager();
/*     */     try {
/* 580 */       String s = "update aud_procesos_plan_anual set  tipo_auditoria='" + tipoAuditoria + "'," + " coordinador_auditoria='" + coordinadorAuditoria + "'," + " area_responsable='" + areaResponsable + "'," + " equipo_auditor='" + equipoAuditor + "'," + " mes01='" + mes01 + "'," + " mes02='" + mes02 + "'," + " mes03='" + mes03 + "'," + " mes04='" + mes04 + "'," + " mes05='" + mes05 + "'," + " mes06='" + mes06 + "'," + " mes07='" + mes07 + "'," + " mes08='" + mes08 + "'," + " mes09='" + mes09 + "'," + " mes10='" + mes10 + "'," + " mes11='" + mes11 + "'," + " mes12='" + mes12 + "'," + " titulo='" + titulo + "'," + " objetivos_especificos='" + objetivos_especificos + "'," + " alcance='" + alcance + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " ciclo=" + ciclo + " and codigo_proceso='" + codigoProceso + "'" + "";
/*     */ 

/*     */       
/* 606 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 608 */     catch (Exception e) {
/* 609 */       e.printStackTrace();
/* 610 */       Utilidades.writeError("AudProcesosPlanAnualDAO:modificarRegistro ", e);
/* 611 */       rta.setMensaje(e.getMessage());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     finally {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 630 */       dat.close();
/*     */     } 
/* 632 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\AudProcesosPlanAnualDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */