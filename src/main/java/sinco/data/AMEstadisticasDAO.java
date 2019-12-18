/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.AMAccionesEstadisticaDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AMEstadisticasDAO;
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
/*     */ public class AMEstadisticasDAO
/*     */ {
/*     */   ResultSet rs;
/*  24 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  40 */       this.dat.close();
/*     */     }
/*  42 */     catch (Exception e) {
/*  43 */       Utilidades.writeError("AMEstadisticasDAO:close ", e);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection estadisticaProcesos(String secuencia, int areaImplanta, String fechaDesde, String fechaHasta, int codigoEstado, String accion, String origen, String proceso, String norma) {
/*  63 */     Collection resultados = new ArrayList();
/*     */     try {
/*  65 */       String s = "select unidades_dependencia.descripcion as nombreArea,am_acciones.area_implanta,am_acciones.proceso,procesos.descripcion as nombreProceso, count(0) as numero from am_acciones,POA_PROCESOS procesos,unidades_dependencia where am_acciones.proceso=procesos.codigo_proceso and unidades_dependencia.codigo=am_acciones.area_implanta and 1= case when am_acciones.impacto='C' then 1  when NUMERO IN ( SELECT  NUMERO FROM AM_ACCIONES_AREAS WHERE AREA_IMPLANTA=" + areaImplanta + ") THEN 1" + " WHEN unidades_dependencia.secuencia like '" + secuencia + "%' THEN 1 ELSE 0 END" + " and am_acciones.codigo_estado not in (0,4)" + " and codigo_estado not in (0,4)";
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
/*  79 */       if (fechaDesde.length() > 0) {
/*  80 */         s = s + " and am_acciones.fecha_vigencia>=" + Utilidades.formatoFecha2(fechaDesde);
/*     */       }
/*  82 */       if (fechaHasta.length() > 0) {
/*  83 */         s = s + " and am_acciones.fecha_vigencia<" + Utilidades.formatoFecha2(fechaHasta);
/*     */       }
/*     */       
/*  86 */       if (codigoEstado != 0) {
/*  87 */         s = s + " and am_acciones.codigo_estado=" + codigoEstado;
/*     */       }
/*     */       
/*  90 */       if (accion.length() > 0) {
/*  91 */         s = s + " and am_acciones.accion='" + accion + "'";
/*     */       }
/*     */       
/*  94 */       if (norma.length() > 0) {
/*  95 */         s = s + " and am_acciones.norma='" + norma + "'";
/*     */       }
/*     */       
/*  98 */       if (origen.length() > 0) {
/*  99 */         s = s + " and am_acciones.origen='" + origen + "'";
/*     */       }
/*     */       
/* 102 */       if (proceso.length() > 0) {
/* 103 */         s = s + " and am_acciones.proceso='" + proceso + "'";
/*     */       }
/*     */       
/* 106 */       s = s + " group by unidades_dependencia.descripcion,am_acciones.area_implanta,am_acciones.proceso,procesos.descripcion";
/* 107 */       s = s + " order by unidades_dependencia.descripcion,am_acciones.area_implanta,am_acciones.proceso";
/*     */       
/* 109 */       boolean rtaDB = this.dat.parseSql(s);
/* 110 */       if (!rtaDB) {
/* 111 */         return resultados;
/*     */       }
/* 113 */       this.rs = this.dat.getResultSet();
/* 114 */       while (this.rs.next())
/*     */       {
/* 116 */         AMAccionesEstadisticaDTO reg = new AMAccionesEstadisticaDTO();
/* 117 */         reg.setNombreArea(this.rs.getString("nombreArea"));
/* 118 */         reg.setAreaImplanta(this.rs.getInt("area_implanta"));
/* 119 */         reg.setCodigoProceso(this.rs.getString("proceso"));
/* 120 */         reg.setNombreProceso(this.rs.getString("nombreProceso"));
/* 121 */         reg.setNumero(this.rs.getInt("numero"));
/*     */         
/* 123 */         resultados.add(reg);
/*     */       }
/*     */     
/* 126 */     } catch (Exception e) {
/* 127 */       e.printStackTrace();
/* 128 */       Utilidades.writeError("AMEstadisticasDAO:cargarTodos ", e);
/*     */     } 
/* 130 */     return resultados;
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
/*     */   public Collection estadisticaProcesosResumen(String secuencia, int areaImplanta, String fechaDesde, String fechaHasta, int codigo_estado, String accion, String origen, String proceso, String norma) {
/* 150 */     Collection resultados = new ArrayList();
/*     */     try {
/* 152 */       String s = "select am_acciones.proceso, procesos.descripcion as nombreProceso, count(0) as numero from am_acciones,POA_PROCESOS procesos,unidades_dependencia where am_acciones.proceso=procesos.codigo_proceso and unidades_dependencia.codigo=am_acciones.area_implanta and 1= case when am_acciones.impacto='C' then 1  when NUMERO IN ( SELECT  NUMERO FROM AM_ACCIONES_AREAS WHERE AREA_IMPLANTA=" + areaImplanta + ") THEN 1" + " WHEN unidades_dependencia.secuencia like '" + secuencia + "%' THEN 1 ELSE 0 END" + " and am_acciones.codigo_estado not in (0,4)";
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
/* 163 */       if (fechaDesde.length() > 0) {
/* 164 */         s = s + " and am_acciones.fecha_vigencia>=" + Utilidades.formatoFecha2(fechaDesde);
/*     */       }
/* 166 */       if (fechaHasta.length() > 0) {
/* 167 */         s = s + " and am_acciones.fecha_vigencia<" + Utilidades.formatoFecha2(fechaHasta);
/*     */       }
/*     */ 
/*     */       
/* 171 */       if (codigo_estado != 0) {
/* 172 */         s = s + " and am_acciones.codigo_estado=" + codigo_estado;
/*     */       }
/*     */       
/* 175 */       if (accion.length() > 0) {
/* 176 */         s = s + " and am_acciones.accion='" + accion + "'";
/*     */       }
/*     */       
/* 179 */       if (norma.length() > 0) {
/* 180 */         s = s + " and am_acciones.norma='" + norma + "'";
/*     */       }
/*     */       
/* 183 */       if (origen.length() > 0) {
/* 184 */         s = s + " and am_acciones.origen='" + origen + "'";
/*     */       }
/*     */       
/* 187 */       if (proceso.length() > 0) {
/* 188 */         s = s + " and am_acciones.proceso='" + proceso + "'";
/*     */       }
/*     */       
/* 191 */       s = s + " group by am_acciones.proceso,procesos.descripcion";
/* 192 */       s = s + " order by am_acciones.proceso";
/*     */       
/* 194 */       boolean rtaDB = this.dat.parseSql(s);
/* 195 */       if (!rtaDB) {
/* 196 */         return resultados;
/*     */       }
/* 198 */       this.rs = this.dat.getResultSet();
/* 199 */       while (this.rs.next())
/*     */       {
/* 201 */         AMAccionesEstadisticaDTO reg = new AMAccionesEstadisticaDTO();
/* 202 */         reg.setCodigoProceso(this.rs.getString("proceso"));
/* 203 */         reg.setNombreProceso(this.rs.getString("nombreProceso"));
/* 204 */         reg.setNumero(this.rs.getInt("numero"));
/*     */         
/* 206 */         resultados.add(reg);
/*     */       }
/*     */     
/* 209 */     } catch (Exception e) {
/* 210 */       e.printStackTrace();
/* 211 */       Utilidades.writeError("AMEstadisticasDAO:cargarTodos ", e);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection cargarEstadistica(String secuencia, String fechaDesde, String fechaHasta, int codigo_estado, String accion, String origen, String proceso, String norma) {
/* 233 */     String s = "SELECT food.nombreArea,food.area_implanta,acce.accion,food.nombre_tipo_accion as nombreTipo,food.Total,food.Proceso,food.por_calificar,food.Implantadas,food.NoImplantadas, food.Cumplieron,food.NoCumplieron,COUNT(0) AS numero_causas,SUM(CASE WHEN am_causas.prorrogas=0 THEN 1 ELSE 0 END) AS prorroga0,SUM(CASE WHEN am_causas.prorrogas=1 THEN 1 ELSE 0 END) AS prorroga1,SUM(CASE WHEN am_causas.prorrogas=2 THEN 1 ELSE 0 END) AS prorroga2,SUM(CASE WHEN am_causas.prorrogas=3 THEN 1 ELSE 0 END) AS prorroga3,SUM(CASE WHEN am_causas.prorrogas>=4 THEN 1 ELSE 0 END) AS prorroga4 FROM am_causas,unidades_dependencia,am_acciones acce,(SELECT unidades_dependencia.descripcion AS nombreArea,acci.area_implanta,acci.accion,am_tipo_i.descripcion as nombre_tipo_accion,SUM(CASE WHEN acci.codigo_estado IN(1,2,3,5) THEN 1 ELSE 0 END) AS Total,SUM(CASE WHEN acci.codigo_estado=1 THEN 1 ELSE 0 END) AS Proceso,SUM(CASE WHEN acci.codigo_estado=2 THEN 1 ELSE 0 END) AS Implantadas,SUM(CASE WHEN acci.codigo_estado=3 THEN 1 ELSE 0 END) AS NoImplantadas,SUM(CASE WHEN acci.codigo_estado=5 THEN 1 ELSE 0 END) AS por_calificar,SUM(CASE  WHEN acci.cumplio='S' THEN 1 ELSE 0 END) AS Cumplieron,SUM(CASE  WHEN acci.cumplio='N' THEN 1 ELSE 0 END) AS NoCumplieron  FROM  am_acciones acci,unidades_dependencia,sis_multivalores am_tipo_i WHERE unidades_dependencia.codigo=acci.area_implanta and acci.accion=am_tipo_i.valor  and am_tipo_i.tabla='AM_TIPO_ACCION' AND acci.codigo_estado NOT IN (0,4)  AND unidades_dependencia.secuencia LIKE  '" + secuencia + "%'";
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
/* 269 */     if (fechaDesde.length() > 0) {
/* 270 */       s = s + " AND acci.fecha_vigencia>=" + Utilidades.formatoFecha2(fechaDesde);
/*     */     }
/* 272 */     if (fechaHasta.length() > 0) {
/* 273 */       s = s + " AND acci.fecha_vigencia<" + Utilidades.formatoFecha2(fechaHasta);
/*     */     }
/*     */     
/* 276 */     if (codigo_estado > 0) {
/* 277 */       s = s + " and acci.codigo_estado=" + codigo_estado;
/*     */     }
/*     */     
/* 280 */     if (accion != null && !accion.equals("")) {
/* 281 */       s = s + " and acci.accion='" + accion + "'";
/*     */     }
/*     */     
/* 284 */     if (norma != null && !norma.equals("")) {
/* 285 */       s = s + " and acci.norma='" + norma + "'";
/*     */     }
/*     */     
/* 288 */     if (origen != null && !origen.equals("")) {
/* 289 */       s = s + " and acci.origen='" + origen + "'";
/*     */     }
/*     */     
/* 292 */     if (proceso != null && !proceso.equals("")) {
/* 293 */       s = s + " and acci.proceso='" + proceso + "'";
/*     */     }
/*     */     
/* 296 */     s = s + " GROUP BY unidades_dependencia.descripcion,acci.area_implanta," + "acci.accion,am_tipo_i.descripcion" + " ) food" + " WHERE food.area_implanta=acce.area_implanta" + " AND acce.accion=food.accion" + " AND unidades_dependencia.codigo=acce.area_implanta" + " AND acce.codigo_estado NOT IN (0,4) " + " AND unidades_dependencia.secuencia LIKE  '" + secuencia + "%'";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 304 */     if (fechaDesde.length() > 0) {
/* 305 */       s = s + " AND acce.fecha_vigencia>=" + Utilidades.formatoFecha2(fechaDesde);
/*     */     }
/* 307 */     if (fechaHasta.length() > 0) {
/* 308 */       s = s + " AND acce.fecha_vigencia<" + Utilidades.formatoFecha2(fechaHasta);
/*     */     }
/* 310 */     if (codigo_estado > 0) {
/* 311 */       s = s + " and acce.codigo_estado=" + codigo_estado;
/*     */     }
/*     */ 
/*     */     
/* 315 */     if (accion != null && !accion.equals("")) {
/* 316 */       s = s + " and acce.accion='" + accion + "'";
/*     */     }
/*     */     
/* 319 */     if (norma != null && !norma.equals("")) {
/* 320 */       s = s + " and acce.norma='" + norma + "'";
/*     */     }
/*     */     
/* 323 */     if (origen != null && !origen.equals("")) {
/* 324 */       s = s + " and acce.origen='" + origen + "'";
/*     */     }
/*     */     
/* 327 */     if (proceso != null && !proceso.equals("")) {
/* 328 */       s = s + " and acce.proceso='" + proceso + "'";
/*     */     }
/* 330 */     s = s + " AND acce.numero=am_causas.numero" + " GROUP BY food.nombreArea," + "food.area_implanta," + "acce.accion,food.nombre_tipo_accion," + "food.Total," + "food.Proceso," + "food.por_calificar," + "food.Implantadas," + "food.NoImplantadas," + "food.Cumplieron," + "food.NoCumplieron" + " order by 1,3";
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
/* 343 */     Collection resultados = new ArrayList();
/*     */     
/*     */     try {
/* 346 */       boolean rtaDB = this.dat.parseSql(s);
/* 347 */       if (!rtaDB) {
/* 348 */         return resultados;
/*     */       }
/*     */       
/* 351 */       this.rs = this.dat.getResultSet();
/*     */       
/* 353 */       while (this.rs.next()) {
/* 354 */         AMAccionesEstadisticaDTO reg = new AMAccionesEstadisticaDTO();
/* 355 */         reg.setNombreArea(this.rs.getString("nombreArea"));
/* 356 */         reg.setAccion(this.rs.getString("accion"));
/* 357 */         reg.setNombreTipo(this.rs.getString("nombreTipo"));
/* 358 */         reg.setAreaImplanta(this.rs.getInt("area_implanta"));
/* 359 */         reg.setPorCalificar(this.rs.getInt("por_calificar"));
/* 360 */         reg.setTotal(this.rs.getInt("total"));
/* 361 */         reg.setProceso(this.rs.getInt("proceso"));
/* 362 */         reg.setImplantadas(this.rs.getInt("implantadas"));
/* 363 */         reg.setNoImplantadas(this.rs.getInt("noImplantadas"));
/* 364 */         reg.setCumplieron(this.rs.getInt("cumplieron"));
/* 365 */         reg.setNoCumplieron(this.rs.getInt("noCumplieron"));
/*     */         
/* 367 */         reg.setNumeroCausas(this.rs.getInt("numero_causas"));
/* 368 */         reg.setProrroga0(this.rs.getInt("prorroga0"));
/* 369 */         reg.setProrroga1(this.rs.getInt("prorroga1"));
/* 370 */         reg.setProrroga2(this.rs.getInt("prorroga2"));
/* 371 */         reg.setProrroga3(this.rs.getInt("prorroga3"));
/* 372 */         reg.setProrroga4(this.rs.getInt("prorroga4"));
/*     */         
/* 374 */         resultados.add(reg);
/*     */       }
/*     */     
/* 377 */     } catch (Exception e) {
/* 378 */       e.printStackTrace();
/* 379 */       Utilidades.writeError("AMAccionesFactory:cargarAccionesNoEnviadas", e);
/*     */     } 
/* 381 */     return resultados;
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
/*     */   public Collection resumenEstadistica(String secuencia, String fechaDesde, String fechaHasta, int codigoEstado, String accion, String origen, String proceso, String norma) {
/* 400 */     String s = "SELECT acce.accion,food.nombre_tipo_accion as nombreTipo,food.Total,food.Proceso,food.por_calificar,food.Implantadas,food.NoImplantadas,food.Cumplieron,food.NoCumplieron,COUNT(0) AS numero_causas,SUM(CASE WHEN am_causas.prorrogas=0 THEN 1 ELSE 0 END) AS prorroga0,SUM(CASE WHEN am_causas.prorrogas=1 THEN 1 ELSE 0 END) AS prorroga1,SUM(CASE WHEN am_causas.prorrogas=2 THEN 1 ELSE 0 END) AS prorroga2,SUM(CASE WHEN am_causas.prorrogas=3 THEN 1 ELSE 0 END) AS prorroga3,SUM(CASE WHEN am_causas.prorrogas>=4 THEN 1 ELSE 0 END) AS prorroga4 FROM am_causas,unidades_dependencia,am_acciones acce,(SELECT acci.accion,am_tipo_i.descripcion as nombre_tipo_accion,SUM(CASE WHEN acci.codigo_estado IN(1,2,3,5) THEN 1 ELSE 0 END) AS Total,SUM(CASE WHEN acci.codigo_estado=1 THEN 1 ELSE 0 END) AS Proceso,SUM(CASE WHEN acci.codigo_estado=2 THEN 1 ELSE 0 END) AS Implantadas,SUM(CASE WHEN acci.codigo_estado=3 THEN 1 ELSE 0 END) AS NoImplantadas,sum(CASE when acci.codigo_estado = 5 THEN 1 ELSE 0 end) as por_calificar,SUM(CASE  WHEN acci.cumplio='S' THEN 1 ELSE 0 END) AS Cumplieron,SUM(CASE  WHEN acci.cumplio='N' THEN 1 ELSE 0 END) AS NoCumplieron  FROM  am_acciones acci,unidades_dependencia,sis_multivalores am_tipo_i WHERE unidades_dependencia.codigo=acci.area_implanta and acci.accion=am_tipo_i.valor  and am_tipo_i.tabla='AM_TIPO_ACCION' AND acci.codigo_estado NOT IN (0,4)  AND unidades_dependencia.secuencia LIKE  '" + secuencia + "%'";
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 426 */     if (fechaDesde.length() > 0) {
/* 427 */       s = s + " AND acci.fecha_vigencia>=" + Utilidades.formatoFecha2(fechaDesde);
/*     */     }
/* 429 */     if (fechaHasta.length() > 0) {
/* 430 */       s = s + " AND acci.fecha_vigencia<" + Utilidades.formatoFecha2(fechaHasta);
/*     */     }
/*     */     
/* 433 */     if (codigoEstado > 0) {
/* 434 */       s = s + " and acci.codigo_estado=" + codigoEstado;
/*     */     }
/*     */     
/* 437 */     if (accion != null && !accion.equals("")) {
/* 438 */       s = s + " and acci.accion='" + accion + "'";
/*     */     }
/*     */     
/* 441 */     if (norma != null && !norma.equals("")) {
/* 442 */       s = s + " and acci.norma='" + norma + "'";
/*     */     }
/*     */     
/* 445 */     if (origen != null && !origen.equals("")) {
/* 446 */       s = s + " and acci.origen='" + origen + "'";
/*     */     }
/*     */     
/* 449 */     if (proceso != null && !proceso.equals("")) {
/* 450 */       s = s + " and acci.proceso='" + proceso + "'";
/*     */     }
/*     */     
/* 453 */     s = s + " GROUP BY acci.accion,am_tipo_i.descripcion" + " ) food" + " WHERE acce.accion=food.accion" + " AND unidades_dependencia.codigo=acce.area_implanta" + " AND acce.codigo_estado NOT IN (0,4) " + " AND unidades_dependencia.secuencia LIKE  '" + secuencia + "%'";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 459 */     if (fechaDesde.length() > 0) {
/* 460 */       s = s + " AND acce.fecha_vigencia>=" + Utilidades.formatoFecha2(fechaDesde);
/*     */     }
/* 462 */     if (fechaHasta.length() > 0) {
/* 463 */       s = s + " AND acce.fecha_vigencia<" + Utilidades.formatoFecha2(fechaHasta);
/*     */     }
/* 465 */     if (codigoEstado > 0) {
/* 466 */       s = s + " and acce.codigo_estado=" + codigoEstado;
/*     */     }
/*     */ 
/*     */     
/* 470 */     if (accion != null && !accion.equals("")) {
/* 471 */       s = s + " and acce.accion='" + accion + "'";
/*     */     }
/*     */     
/* 474 */     if (norma != null && !norma.equals("")) {
/* 475 */       s = s + " and acce.norma='" + norma + "'";
/*     */     }
/*     */     
/* 478 */     if (origen != null && !origen.equals("")) {
/* 479 */       s = s + " and acce.origen='" + origen + "'";
/*     */     }
/*     */     
/* 482 */     if (proceso != null && !proceso.equals("")) {
/* 483 */       s = s + " and acce.proceso='" + proceso + "'";
/*     */     }
/* 485 */     s = s + " AND acce.numero=am_causas.numero" + " GROUP BY acce.accion,food.nombre_tipo_accion," + "food.Total," + "food.Proceso," + "food.por_calificar," + "food.Implantadas," + "food.NoImplantadas," + "food.Cumplieron," + "food.NoCumplieron" + " order by 1";
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
/* 496 */     Collection resultados = new ArrayList();
/*     */     
/*     */     try {
/* 499 */       boolean rtaDB = this.dat.parseSql(s);
/* 500 */       if (!rtaDB) {
/* 501 */         return resultados;
/*     */       }
/*     */       
/* 504 */       this.rs = this.dat.getResultSet();
/* 505 */       while (this.rs.next()) {
/* 506 */         AMAccionesEstadisticaDTO reg = new AMAccionesEstadisticaDTO();
/* 507 */         reg.setTotal(this.rs.getInt("total"));
/* 508 */         reg.setProceso(this.rs.getInt("proceso"));
/* 509 */         reg.setImplantadas(this.rs.getInt("implantadas"));
/* 510 */         reg.setPorCalificar(this.rs.getInt("por_calificar"));
/*     */         
/* 512 */         reg.setCumplieron(this.rs.getInt("cumplieron"));
/* 513 */         reg.setNoCumplieron(this.rs.getInt("noCumplieron"));
/* 514 */         reg.setNombreTipo(this.rs.getString("nombreTipo"));
/* 515 */         reg.setAccion(this.rs.getString("accion"));
/*     */         
/* 517 */         reg.setNumeroCausas(this.rs.getInt("numero_causas"));
/* 518 */         reg.setProrroga0(this.rs.getInt("prorroga0"));
/* 519 */         reg.setProrroga1(this.rs.getInt("prorroga1"));
/* 520 */         reg.setProrroga2(this.rs.getInt("prorroga2"));
/* 521 */         reg.setProrroga3(this.rs.getInt("prorroga3"));
/* 522 */         reg.setProrroga4(this.rs.getInt("prorroga4"));
/* 523 */         resultados.add(reg);
/*     */       }
/*     */     
/* 526 */     } catch (Exception e) {
/* 527 */       e.printStackTrace();
/* 528 */       Utilidades.writeError("AMAccionesFactory:resumenEstadistica", e);
/*     */     } 
/* 530 */     return resultados;
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
/*     */   public Collection cargarEstadisticaNoEfectivas(String fechaDesde, String fechaHasta, boolean bTodas, int codigoArea) {
/* 544 */     Collection resultados = new ArrayList();
/*     */     
/*     */     try {
/* 547 */       String s = "select FOOD.NOMBRE_AREA,       FOOD.AREA_IMPLANTA,       FOOD.TOTAL_ACCIONES,       FOOD.CORRECTIVAS,       FOOD.PREVENTIVAS,       FOOD.CORRECCIONES,       count(0) as NUMERO_CAUSAS,       sum(case when CA.ESTADO = 2 then 1 else 0 end) as SATISFACTORIAS,       sum(case when CA.ESTADO = 3 then 1 else 0 end) as INSATISFACTORIAS from   AM_CAUSAS CA,       UNIDADES_DEPENDENCIA ue,       AM_ACCIONES ACCE,       (select AI.AREA_IMPLANTA,               U1.DESCRIPCION as NOMBRE_AREA,               count(0) as TOTAL_ACCIONES,               sum(case when AI.ACCION = 'C' and AI.CUMPLIO = 'N' then 1 else 0 end) as CORRECTIVAS,               sum(case when AI.ACCION = 'P' and AI.CUMPLIO = 'N' then 1 else 0 end) as PREVENTIVAS,               sum(case when AI.ACCION = 'R' and AI.CUMPLIO = 'N' then 1 else 0 end) as CORRECCIONES        from   AM_ACCIONES          AI,               UNIDADES_DEPENDENCIA U1        where  AI.AREA_IMPLANTA = U1.CODIGO";
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
/*     */       
/* 569 */       if (fechaDesde.length() > 0) {
/* 570 */         s = s + " and ai.fecha_vigencia>=" + Utilidades.formatoFecha2(fechaDesde);
/*     */       }
/* 572 */       if (fechaHasta.length() > 0) {
/* 573 */         s = s + " and ai.fecha_vigencia<=" + Utilidades.formatoFecha2(fechaHasta);
/*     */       }
/* 575 */       s = s + "               and AI.CODIGO_ESTADO in (2, 3, 5)        group  by AI.AREA_IMPLANTA,                  U1.DESCRIPCION) FOOD where  FOOD.AREA_IMPLANTA = ACCE.AREA_IMPLANTA       and ue.CODIGO = ACCE.AREA_IMPLANTA";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 581 */       if (!bTodas) {
/* 582 */         s = s + " and exists(select 'X'" + " from unidades_dependencia u2,sis_usuarios_area personas_area where personas_area.codigo_area=" + codigoArea + " and  u2.codigo=personas_area.codigo_area" + " and  ue.secuencia like u2.secuencia||'%' )";
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 588 */       if (fechaDesde.length() > 0) {
/* 589 */         s = s + " and acce.fecha_vigencia>=" + Utilidades.formatoFecha2(fechaDesde);
/*     */       }
/* 591 */       if (fechaHasta.length() > 0) {
/* 592 */         s = s + " and acce.fecha_vigencia<=" + Utilidades.formatoFecha2(fechaHasta);
/*     */       }
/* 594 */       s = s + "       and ACCE.NUMERO = CA.NUMERO      and ACCE.CODIGO_ESTADO in (2, 3, 5) group  by FOOD.NOMBRE_AREA,          FOOD.AREA_IMPLANTA,          FOOD.TOTAL_ACCIONES,          FOOD.CORRECTIVAS,          FOOD.PREVENTIVAS,          FOOD.CORRECCIONES order  by 1,3";
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
/* 606 */       boolean rtaDB = this.dat.parseSql(s);
/* 607 */       if (!rtaDB) {
/* 608 */         return resultados;
/*     */       }
/*     */       
/* 611 */       this.rs = this.dat.getResultSet();
/*     */       
/* 613 */       while (this.rs.next()) {
/* 614 */         AMAccionesEstadisticaDTO reg = new AMAccionesEstadisticaDTO();
/* 615 */         reg.setNombreArea(this.rs.getString("nombre_area"));
/* 616 */         reg.setAreaImplanta(this.rs.getInt("area_implanta"));
/* 617 */         reg.setTotal(this.rs.getInt("total_acciones"));
/* 618 */         reg.setCorrectivas(this.rs.getInt("correctivas"));
/* 619 */         reg.setPreventivas(this.rs.getInt("preventivas"));
/* 620 */         reg.setCorrecciones(this.rs.getInt("correcciones"));
/* 621 */         reg.setNumeroCausas(this.rs.getInt("numero_causas"));
/* 622 */         reg.setSatisfactorias(this.rs.getInt("satisfactorias"));
/* 623 */         reg.setInsatisfactorias(this.rs.getInt("insatisfactorias"));
/*     */         
/* 625 */         resultados.add(reg);
/*     */       }
/*     */     
/* 628 */     } catch (Exception e) {
/* 629 */       e.printStackTrace();
/* 630 */       Utilidades.writeError("AMAccionesFactory:cargarAccionesNoEnviadas", e);
/*     */     } 
/* 632 */     return resultados;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\AMEstadisticasDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */