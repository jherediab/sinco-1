/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.AudInformesPlanAnualDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AudInformesPlanAnualDAO;
/*     */ import sinco.data.DBManager;

/*     */ 
/*     */ public class AudInformesPlanAnualDAO
/*     */ {
/*     */   public AudInformesPlanAnualDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  35 */       AudInformesPlanAnualDTO reg = new AudInformesPlanAnualDTO();
/*     */       
/*  37 */       reg.setCiclo(rs.getInt("ciclo"));
/*  38 */       reg.setCodigoInforme(rs.getInt("codigo_informe"));
/*  39 */       reg.setAreaResponsable(rs.getInt("area_responsable"));
/*  40 */       reg.setCoordinadorAuditoría(rs.getString("coordinador_auditoria"));
/*  41 */       reg.setEquipoAuditor(rs.getString("equipo_auditor"));
/*  42 */       reg.setMes01(rs.getString("mes01"));
/*  43 */       reg.setMes02(rs.getString("mes02"));
/*  44 */       reg.setMes03(rs.getString("mes03"));
/*  45 */       reg.setMes04(rs.getString("mes04"));
/*  46 */       reg.setMes05(rs.getString("mes05"));
/*  47 */       reg.setMes06(rs.getString("mes06"));
/*  48 */       reg.setMes07(rs.getString("mes07"));
/*  49 */       reg.setMes08(rs.getString("mes08"));
/*  50 */       reg.setMes09(rs.getString("mes09"));
/*  51 */       reg.setMes10(rs.getString("mes10"));
/*  52 */       reg.setMes11(rs.getString("mes11"));
/*  53 */       reg.setMes12(rs.getString("mes12"));
/*  54 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  55 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  56 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  57 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  58 */       reg.setNombreCodigoInforme(rs.getString("nombre_codigo_informe"));
/*  59 */       reg.setNombreAreaResponsable(rs.getString("nombre_area_responsable"));
/*  60 */       reg.setNombreCoordinadorAuditoría(rs.getString("nombre_coordinador_auditoria"));
/*     */       
/*  62 */       reg.setTitulo(rs.getString("titulo"));
/*  63 */       reg.setObjetivos_especificos(rs.getString("objetivos_especificos"));
/*  64 */       reg.setAlcance(rs.getString("alcance"));
/*     */       
/*     */       try {
/*  67 */         reg.setNombreTipoInforme(rs.getString("Nombre_Tipo_Informe"));
/*     */       
/*     */       }
/*  70 */       catch (Exception e) {}
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/*  75 */         reg.setNombreTipoProceso(rs.getString("nombre_tipo_proceso"));
/*  76 */         reg.setNombreLiderProceso(rs.getString("nombre_lider_proceso"));
/*     */       
/*     */       }
/*  79 */       catch (Exception e) {}
/*     */ 
/*     */ 
/*     */       
/*  83 */       return reg;
/*     */     }
/*  85 */     catch (Exception e) {
/*  86 */       e.printStackTrace();
/*  87 */       Utilidades.writeError("AudInformesPlanAnualDAO:leerRegistro ", e);
/*     */       
/*  89 */       return null;
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
/*     */   public Collection<AudInformesPlanAnualDTO> cargarTodos(int ciclo, String tipoInforme) {
/* 102 */     Collection<AudInformesPlanAnualDTO> resultados = new ArrayList<AudInformesPlanAnualDTO>();
/*     */     
/* 104 */   DBManager  dat = new DBManager();
/*     */     try {
/* 106 */       String s = "select t.ciclo,t.codigo_informe,r1.nombre as nombre_codigo_informe,t.coordinador_auditoria,t.area_responsable,ud.Descripcion as nombre_area_responsable,m2.DESCRIPCION as nombre_coordinador_auditoria,t.equipo_auditor,t.mes01,t.mes02,t.mes03,t.mes04,t.mes05,t.mes06,t.mes07,t.mes08,t.mes09,t.mes10,t.mes11,t.mes12,t.titulo,t.objetivos_especificos,t.alcance,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion, Ti.Descripcion          Nombre_Tipo_Informe from   Aud_Informes_Plan_Anual t left join Unidades_Dependencia ud on (ud.Codigo=t.area_responsable), Aud_Informes            R1, Sis_Multivalores        M2, Sis_Multivalores        Ti where  R1.Codigo = t.Codigo_Informe and R1.Tipo_Informe = Ti.Valor and UPPER(M2.Tabla) LIKE UPPER('cal_coordinador_auditoria') and UPPER(M2.Valor) LIKE UPPER(t.coordinador_auditoria) and R1.Tipo_Informe = Ti.Valor and Ti.Tabla = 'CAL_TIPO_INFORME'";
/*     */ 
/*     */ 

/*     */ 
/*     */       
/* 146 */       if (ciclo > 0) {
/* 147 */         s = s + " and t.ciclo=" + ciclo;
/*     */       }
/* 149 */       if (tipoInforme.length() > 0) {
/* 150 */         s = s + " and r1.tipo_informe='" + tipoInforme + "'";
/*     */       }
/* 152 */       s = s + " order by 1,2";
/* 153 */       boolean rtaDB = dat.parseSql(s);
/* 154 */       if (!rtaDB) {
/* 155 */         return resultados;
/*     */       }
/* 157 */       ResultSet rs = dat.getResultSet();
/* 158 */       while (rs.next()) {
/* 159 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 162 */     catch (Exception e) {
/* 163 */       e.printStackTrace();
/* 164 */       Utilidades.writeError("AudInformesPlanAnualDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 167 */       dat.close();
/*     */     } 
/* 169 */     return resultados;
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
/*     */   public Collection<AudInformesPlanAnualDTO> cargarDeAuditor(int ciclo, int auditor) {
/* 182 */     Collection<AudInformesPlanAnualDTO> resultados = new ArrayList<AudInformesPlanAnualDTO>();
/*     */     
/* 184 */    DBManager dat = new DBManager();
/*     */     
/*     */     try {
/* 187 */       String s = "select t.ciclo,t.codigo_informe,r1.nombre as nombre_codigo_informe,t.area_responsable,ud.Descripcion as nombre_area_responsable,t.coordinador_auditoria,m2.DESCRIPCION as nombre_coordinador_auditoria,t.equipo_auditor,t.mes01,t.mes02,t.mes03,t.mes04,t.mes05,t.mes06,t.mes07,t.mes08,t.mes09,t.mes10,t.mes11,t.mes12,t.titulo,t.objetivos_especificos,t.alcance,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion, Ti.Descripcion          Nombre_Tipo_Informe from   Aud_Informes_Plan_Anual t  left join Unidades_Dependencia ud on (ud.Codigo=t.area_responsable) Aud_Informes            R1, Sis_Multivalores        M2, Sis_Multivalores        Ti where  R1.Codigo = t.Codigo_Informe and M2.Tabla = 'CAL_TIPO_AUDITORIA' and M2.Valor = t.coordinador_auditoria and R1.Tipo_Informe = Ti.Valor and Ti.Tabla = 'CAL_TIPO_INFORME' and t.ciclo=" + ciclo + " AND exists (select 'x'" + "  from   Aud_Grupo_Auditor e" + "  where  e.Ciclo = t.Ciclo" + "\t\t\tand e.Codigo_Empleado = " + auditor + "           and e.codigo_informe=t.codigo_informe" + "\t\t\tand e.Asociado_a = 'I')" + " order by t.codigo_informe";
/*     */ 
    
/* 235 */       boolean rtaDB = dat.parseSql(s);
/* 236 */       if (!rtaDB) {
/* 237 */         return resultados;
/*     */       }
/* 239 */       ResultSet rs = dat.getResultSet();
/* 240 */       while (rs.next()) {
/* 241 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 244 */     catch (Exception e) {
/* 245 */       e.printStackTrace();
/* 246 */       Utilidades.writeError("AudProcesosPlanAnualDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 249 */       dat.close();
/*     */     } 
/* 251 */     return resultados;
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
/*     */   public Collection<AudInformesPlanAnualDTO> cargarPlan(int ciclo, String tipoInforme) {
/* 266 */     Collection<AudInformesPlanAnualDTO> resultados = new ArrayList<AudInformesPlanAnualDTO>();
/*     */     
/* 268 */   DBManager  dat = new DBManager();
/*     */     try {
/* 270 */       String s = "select t.ciclo,t.codigo_informe,r1.nombre as nombre_codigo_informe,t.area_responsable,ud.Descripcion as nombre_area_responsable,t.coordinador_auditoria,m2.DESCRIPCION as nombre_coordinador_auditoria,t.equipo_auditor,t.mes01,t.mes02,t.mes03,t.mes04,t.mes05,t.mes06,t.mes07,t.mes08,t.mes09,t.mes10,t.mes11,t.mes12,t.titulo,t.objetivos_especificos,t.alcance,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion, \t\t case \t\t\t when R1.Estrategico = 'S' then \t\t\t  'Estrategico - ' \t\t\t else \t\t\t  null \t\t end || case \t\t\t when R1.Misional = 'S' then \t\t\t  'Misional - ' \t\t\t else \t\t\t  null \t\t end || case \t\t\t when R1.Apoyo = 'S' then \t\t\t  'Apoyo  - ' \t\t\t else \t\t\t  null \t\t end || case \t\t\t when R1.Evaluacion = 'S' then \t\t\t  'Evaluacion ' \t\t\t else \t\t\t  null \t\t end nombre_Tipo_Proceso, \t\t Ml.Descripcion Nombre_Lider_Proceso from   Aud_Informes_Plan_Anual t  left join Unidades_Dependencia ud on (ud.Codigo=t.area_responsable), \t\t Aud_Informes            R1, \t\t Sis_Multivalores        M2, \t\t Sis_Multivalores        Ml where  R1.Codigo = t.Codigo_Informe \t\t and M2.Tabla = 'CAL_COORDINADOR_AUDITORIA' \t\t and M2.Valor = t.coordinador_auditoria \t\t and R1.Responsable = Ml.Valor \t\t and Ml.Tabla = 'CAL_LIDER_PROCESO'";
/*     */ 
/*     */ 

/*     */       
/* 330 */       if (tipoInforme.length() > 0) {
/* 331 */         s = s + " \t\t and r1.tipo_informe='" + tipoInforme + "'";
/*     */       }
/* 333 */       s = s + " \t\t and t.Ciclo = " + ciclo + " order by t.codigo_informe";
/*     */       
/* 335 */       boolean rtaDB = dat.parseSql(s);
/* 336 */       if (!rtaDB) {
/* 337 */         return resultados;
/*     */       }
/* 339 */       ResultSet rs = dat.getResultSet();
/* 340 */       while (rs.next()) {
/* 341 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 344 */     catch (Exception e) {
/* 345 */       e.printStackTrace();
/* 346 */       Utilidades.writeError("AudInformesPlanAnualDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 349 */       dat.close();
/*     */     } 
/* 351 */     return resultados;
/*     */   }

/*     */   public AudInformesPlanAnualDTO cargarRegistro(int ciclo, int codigoInforme) {
/* 364 */   DBManager  dat = new DBManager();
/*     */     try {
/* 366 */       String s = "select t.ciclo,t.codigo_informe,r1.nombre as nombre_codigo_informe,t.area_responsable,ud.Descripcion as nombre_area_responsable,t.coordinador_auditoria,m2.DESCRIPCION as nombre_coordinador_auditoria,t.equipo_auditor,t.mes01,t.mes02,t.mes03,t.mes04,t.mes05,t.mes06,t.mes07,t.mes08,t.mes09,t.mes10,t.mes11,t.mes12,t.titulo,t.objetivos_especificos,t.alcance,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from aud_informes_plan_anual t  left join AUD_INFORMES r1 on (r1.Codigo=t.codigo_informe) left join Unidades_Dependencia ud on (ud.Codigo=t.area_responsable) left join sis_multivalores m2 on (m2.tabla='CAL_COORDINADOR_AUDITORIA' and m2.VALOR=t.coordinador_auditoria) where  t.ciclo=" + ciclo + " and t.codigo_informe=" + codigoInforme + "";
/*     */ 

/*     */ 
/*     */       
/* 402 */       boolean rtaDB = dat.parseSql(s);
/* 403 */       if (!rtaDB) {
/* 404 */         return null;
/*     */       }
/* 406 */       ResultSet rs = dat.getResultSet();
/* 407 */       if (rs.next()) {
/* 408 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 411 */     catch (Exception e) {
/* 412 */       e.printStackTrace();
/* 413 */       Utilidades.writeError("AudInformesPlanAnualDAO:cargarAudInformesPlanAnual", e);
/*     */     } finally {
/*     */       
/* 416 */       dat.close();
/*     */     } 
/* 418 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int ciclo, int codigoInforme) {
/* 429 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 431 */    DBManager dat = new DBManager();
/*     */     try {
/* 433 */       String s = "delete from aud_informes_plan_anual where  ciclo=" + ciclo + "  and codigo_informe=" + codigoInforme + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 438 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 440 */     catch (Exception e) {
/* 441 */       e.printStackTrace();
/* 442 */       Utilidades.writeError("AudInformesPlanAnualDAO:eliminarRegistro ", e);
/* 443 */       rta.setMensaje(e.getMessage());
/*     */     } 
/*     */     try {
/* 446 */       String s = "delete from aud_areas_auditadas where  ciclo=" + ciclo + "  and codigo_informe=" + codigoInforme + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 451 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 453 */     catch (Exception e) {
/* 454 */       e.printStackTrace();
/* 455 */       Utilidades.writeError("AudInformesPlanAnualDAO:eliminarRegistroAreas ", e);
/* 456 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 459 */       dat.close();
/*     */     } 
/* 461 */     return rta;
/*     */   }
/*     */ 

/*     */ 
/*     */   
/*     */   public RespuestaBD crearRegistro(int ciclo, int codigoInforme, int areaResponsable, String coordinadorAuditoría, String equipoAuditor, String mes01, String mes02, String mes03, String mes04, String mes05, String mes06, String mes07, String mes08, String mes09, String mes10, String mes11, String mes12, String titulo, String objetivos_especificos, String alcance, String usuarioInsercion) {
/* 492 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 494 */    DBManager dat = new DBManager();
/*     */     try {
/* 496 */       String s = "insert into aud_informes_plan_anual(ciclo,codigo_informe,area_responsable,coordinador_auditoria,equipo_auditor,mes01,mes02,mes03,mes04,mes05,mes06,mes07,mes08,mes09,mes10,mes11,mes12,titulo,objetivos_especificos,alcance,usuario_insercion,fecha_insercion) values (" + ciclo + "," + "" + codigoInforme + "," + "" + areaResponsable + "," + "'" + coordinadorAuditoría + "'," + "'" + equipoAuditor + "'," + "'" + mes01 + "'," + "'" + mes02 + "'," + "'" + mes03 + "'," + "'" + mes04 + "'," + "'" + mes05 + "'," + "'" + mes06 + "'," + "'" + mes07 + "'," + "'" + mes08 + "'," + "'" + mes09 + "'," + "'" + mes10 + "'," + "'" + mes11 + "'," + "'" + mes12 + "'," + "'" + titulo + "'," + "'" + objetivos_especificos + "'," + "'" + alcance + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
/*     */ 

/* 543 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 545 */     catch (Exception e) {
/* 546 */       e.printStackTrace();
/* 547 */       Utilidades.writeError("%AudInformesPlanAnualDAO:crearRegistro ", e);
/* 548 */       rta.setMensaje(e.getMessage());
/*     */     } 
/*     */     try {
/* 551 */       String s = "insert into aud_areas_auditadas(ciclo,area,codigo_informe,asociado_a,usuario_insercion,fecha_insercion) values (" + ciclo + "," + "" + areaResponsable + "," + "" + codigoInforme + "," + "'I'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
/*     */ 

/*     */       
/* 566 */       rta = dat.executeUpdate2(s);
/* 567 */     } catch (Exception e) {
/* 568 */       e.printStackTrace();
/* 569 */       Utilidades.writeError("%AudInformesPlanAnualDAO:crearRegistroAreas ", e);
/* 570 */       rta.setMensaje(e.getMessage());
/*     */     
/*     */     }
/*     */     finally {
/*     */       
/* 575 */       dat.close();
/*     */     } 
/* 577 */     return rta;
/*     */   }
/*     */ 

/*     */ 
/*     */   
/*     */   public RespuestaBD modificarRegistro(int ciclo, int codigoInforme, int areaResponsable, String coordinadorAuditoría, String equipoAuditor, String mes01, String mes02, String mes03, String mes04, String mes05, String mes06, String mes07, String mes08, String mes09, String mes10, String mes11, String mes12, String titulo, String objetivos_especificos, String alcance, String usuarioModificacion) {
/* 608 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 610 */    DBManager dat = new DBManager();
/*     */     try {
/* 612 */       String s = "update aud_informes_plan_anual set  coordinador_auditoria='" + coordinadorAuditoría + "'," + " area_responsable='" + areaResponsable + "'," + " equipo_auditor='" + equipoAuditor + "'," + " mes01='" + mes01 + "'," + " mes02='" + mes02 + "'," + " mes03='" + mes03 + "'," + " mes04='" + mes04 + "'," + " mes05='" + mes05 + "'," + " mes06='" + mes06 + "'," + " mes07='" + mes07 + "'," + " mes08='" + mes08 + "'," + " mes09='" + mes09 + "'," + " mes10='" + mes10 + "'," + " mes11='" + mes11 + "'," + " mes12='" + mes12 + "'," + " titulo='" + titulo + "'," + " objetivos_especificos='" + objetivos_especificos + "'," + " alcance='" + alcance + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " ciclo=" + ciclo + " and codigo_informe=" + codigoInforme + "";
/*     */ 
/*     */ 

/*     */       
/* 637 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 639 */     catch (Exception e) {
/* 640 */       e.printStackTrace();
/* 641 */       Utilidades.writeError("AudInformesPlanAnualDAO:modificarRegistro ", e);
/* 642 */       rta.setMensaje(e.getMessage());
 
/*     */     }
/*     */     finally {

/*     */       
/* 662 */       dat.close();
/*     */     } 
/* 664 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\AudInformesPlanAnualDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */