/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.CalLogrosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CalLogrosDAO;
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
/*     */ public class CalLogrosDAO
/*     */ {
/*     */   ResultSet rs;
/*  25 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  34 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  41 */       this.dat.close();
/*     */     }
/*  43 */     catch (Exception e) {
/*  44 */       Utilidades.writeError("CalLogrosFactory:close", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalLogrosDTO next() {
/*     */     try {
/*  55 */       if (this.rs.next()) {
/*  56 */         return leerRegistro();
/*     */       }
/*     */     }
/*  59 */     catch (Exception e) {
/*  60 */       e.printStackTrace();
/*  61 */       Utilidades.writeError("CalLogrosFactory:next ", e);
/*     */     } 
/*  63 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalLogrosDTO leerRegistro() {
/*     */     try {
/*  73 */       CalLogrosDTO reg = new CalLogrosDTO();
/*  74 */       reg.setCodigoCiclo(this.rs.getInt("codigo_ciclo"));
/*  75 */       reg.setCodigoPlan(this.rs.getInt("codigo_plan"));
/*  76 */       reg.setCodigoObjetivo(this.rs.getInt("codigo_objetivo"));
/*  77 */       reg.setCodigoMeta(this.rs.getInt("codigo_meta"));
/*  78 */       reg.setPeriodo(this.rs.getInt("periodo"));
/*  79 */       reg.setValorLogro(this.rs.getDouble("valor_logro"));
/*  80 */       reg.setValorMeta(this.rs.getDouble("valor_meta"));
/*  81 */       reg.setValorMetaPlan(this.rs.getInt("valor_meta_plan"));
/*  82 */       reg.setJustificacion(this.rs.getString("justificacion"));
/*  83 */       reg.setEstado(this.rs.getString("estado"));
/*  84 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  85 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  86 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  87 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  88 */       reg.setCumple(this.rs.getString("cumple"));
/*  89 */       reg.setNombreMeta(this.rs.getString("nombre_meta"));
/*  90 */       reg.setTipoMedicion(this.rs.getString("tipo_medicion"));
/*  91 */       reg.setFrecuenciaMedicion(this.rs.getString("frecuencia_medicion"));
/*  92 */       reg.setAccionNumero(this.rs.getInt("accion_numero"));
/*  93 */       return reg;
/*     */     }
/*  95 */     catch (Exception e) {
/*  96 */       e.printStackTrace();
/*  97 */       Utilidades.writeError("CalLogrosFactory:leerRegistro ", e);
/*     */       
/*  99 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalLogrosDTO cargarRegistro(int codigoCiclo, int codigoPlan, int codigoObjetivo, int codigoMeta, int periodo) {
/*     */     try {
/* 111 */       String s = "select cal_logros.*,' '  as nombre_meta,' ' as tipo_medicion,' ' as frecuencia_medicion,";
/* 112 */       s = s + "' ' as tipo_medicion ,' ' as cumple,";
/* 113 */       s = s + "0 as valor_meta_plan";
/* 114 */       s = s + " from cal_logros ";
/* 115 */       s = s + " where codigo_ciclo=" + codigoCiclo;
/* 116 */       s = s + " and codigo_plan=" + codigoPlan;
/* 117 */       s = s + " and codigo_objetivo=" + codigoObjetivo;
/* 118 */       s = s + " and codigo_meta=" + codigoMeta;
/* 119 */       s = s + " and periodo=" + periodo;
/*     */       
/* 121 */       boolean rtaDB = this.dat.parseSql(s);
/* 122 */       if (!rtaDB) {
/* 123 */         return null;
/*     */       }
/*     */       
/* 126 */       this.rs = this.dat.getResultSet();
/* 127 */       if (this.rs.next()) {
/* 128 */         return leerRegistro();
/*     */       }
/*     */     }
/* 131 */     catch (Exception e) {
/* 132 */       e.printStackTrace();
/* 133 */       Utilidades.writeError("CalLogrosFactory:cargarCalLogros ", e);
/*     */     } 
/* 135 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection cargarLogros(int codigoCiclo, int codigoPlan, int codigoObjetivo, int codigoMeta) {
/* 145 */     Collection resultados = new ArrayList();
/*     */     
/*     */     try {
/* 148 */       String s = "select l.*,pm.descripcion as nombre_meta,";
/* 149 */       s = s + "pm.frecuencia_medicion,pm.tipo_medicion,";
/* 150 */       s = s + "pm.valor_meta as valor_meta_plan,";
/* 151 */       s = s + "case when (pm.tipo_medicion='A' AND l.valor_logro>l.valor_meta)";
/* 152 */       s = s + " \t    OR  (pm.tipo_medicion='B' AND l.valor_logro>=l.valor_meta)";
/* 153 */       s = s + " \t    OR  (pm.tipo_medicion='C' AND l.valor_logro=l.valor_meta)";
/* 154 */       s = s + " \t    OR  (pm.tipo_medicion='D' AND l.valor_logro<=l.valor_meta)";
/* 155 */       s = s + " \t    OR  (pm.tipo_medicion='E' AND l.valor_logro<l.valor_meta)";
/* 156 */       s = s + "  then 'S' else 'N' end as cumple";
/* 157 */       s = s + " from cal_logros l ,cal_plan_metas pm";
/* 158 */       s = s + " where";
/* 159 */       s = s + " l.codigo_ciclo=pm.codigo_ciclo";
/* 160 */       s = s + " and l.codigo_plan=pm.codigo_plan";
/* 161 */       s = s + " and l.codigo_meta=pm.codigo_meta";
/* 162 */       s = s + " and  l.codigo_ciclo=" + codigoCiclo;
/* 163 */       s = s + " and  l.codigo_plan=" + codigoPlan;
/* 164 */       s = s + " and  l.codigo_objetivo=" + codigoObjetivo;
/* 165 */       s = s + " and  l.codigo_meta=" + codigoMeta;
/* 166 */       s = s + " and  l.estado in('A','T')";
/* 167 */       s = s + " order by l.periodo";
/* 168 */       boolean rtaDB = this.dat.parseSql(s);
/* 169 */       this.rs = this.dat.getResultSet();
/* 170 */       while (this.rs.next()) {
/* 171 */         resultados.add(leerRegistro());
/*     */       
/*     */       }
/*     */     }
/* 175 */     catch (Exception e) {
/* 176 */       e.printStackTrace();
/* 177 */       Utilidades.writeError("CalLogrosFactory:cargarLogros ", e);
/*     */     } 
/* 179 */     return resultados;
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
/*     */   public boolean crearRegistro(int codigoCiclo, int codigoPlan, int codigoObjetivo, int codigoMeta, int periodo, double valorLogro, double valorMeta, String justificacion, String estado, String usuarioInsercion) {
/*     */     try {
/* 201 */       String s = "insert into cal_logros (codigo_ciclo,codigo_plan,codigo_objetivo,codigo_meta,periodo,valor_logro,valor_meta,justificacion,estado,fecha_insercion,usuario_insercion)";
/* 202 */       s = s + " values (";
/* 203 */       s = s + "" + codigoCiclo + ",";
/* 204 */       s = s + "" + codigoPlan + ",";
/* 205 */       s = s + "" + codigoObjetivo + ",";
/* 206 */       s = s + "" + codigoMeta + ",";
/* 207 */       s = s + "" + periodo + ",";
/* 208 */       s = s + "" + valorLogro + ",";
/* 209 */       s = s + "" + valorMeta + ",";
/* 210 */       s = s + "'" + justificacion + "',";
/* 211 */       s = s + "'" + estado + "',";
/* 212 */       s = s + "" + Utilidades.getFechaBD() + ",";
/* 213 */       s = s + "'" + usuarioInsercion + "'";
/* 214 */       s = s + ")";
/* 215 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 218 */     catch (Exception e) {
/* 219 */       e.printStackTrace();
/* 220 */       Utilidades.writeError("CalLogrosFactory:crearRegistro", e);
/*     */       
/* 222 */       return false;
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
/*     */   
/*     */   public boolean modificarRegistro(int codigoCiclo, int codigoPlan, int codigoObjetivo, int codigoMeta, int periodo, double valorLogro, double valorMeta, String justificacion, String estado, String usuarioModificacion) {
/*     */     try {
/* 244 */       String s = "update cal_logros set ";
/* 245 */       s = s + " valor_logro=" + valorLogro + ",";
/* 246 */       s = s + " valor_meta=" + valorMeta + ",";
/* 247 */       s = s + " justificacion='" + justificacion + "',";
/* 248 */       s = s + " estado='" + estado + "',";
/* 249 */       s = s + " fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 250 */       s = s + " usuario_modificacion='" + usuarioModificacion + "'";
/* 251 */       s = s + " where ";
/* 252 */       s = s + " codigo_ciclo=" + codigoCiclo;
/* 253 */       s = s + " and codigo_plan=" + codigoPlan;
/* 254 */       s = s + " and codigo_objetivo=" + codigoObjetivo;
/* 255 */       s = s + " and codigo_meta=" + codigoMeta;
/* 256 */       s = s + " and periodo=" + periodo;
/* 257 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 260 */     catch (Exception e) {
/* 261 */       e.printStackTrace();
/* 262 */       Utilidades.writeError("CalLogrosFactory:modificarRegistro", e);
/*     */       
/* 264 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean modificarAccionMejora(int codigoCiclo, int codigoPlan, int codigoObjetivo, int codigoMeta, int periodo, int accionMejora, String usuarioModificacion) {
/*     */     try {
/* 276 */       String s = "update cal_logros set ";
/* 277 */       s = s + " accion_numero=" + accionMejora + ",";
/* 278 */       s = s + " fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 279 */       s = s + " usuario_modificacion='" + usuarioModificacion + "'";
/* 280 */       s = s + " where ";
/* 281 */       s = s + " codigo_ciclo=" + codigoCiclo;
/* 282 */       s = s + " and codigo_plan=" + codigoPlan;
/* 283 */       s = s + " and codigo_objetivo=" + codigoObjetivo;
/* 284 */       s = s + " and codigo_meta=" + codigoMeta;
/* 285 */       s = s + " and periodo=" + periodo;
/* 286 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 289 */     catch (Exception e) {
/* 290 */       e.printStackTrace();
/* 291 */       Utilidades.writeError("CalLogrosFactory:modificarAccionMejora", e);
/*     */       
/* 293 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean modificarJustificacion(int codigoCiclo, int codigoPlan, int codigoObjetivo, int codigoMeta, int periodo, String justificacion, String usuarioModificacion) {
/*     */     try {
/* 304 */       String s = "update cal_logros set ";
/* 305 */       s = s + " justificacion='" + justificacion + "',";
/* 306 */       s = s + " fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 307 */       s = s + " usuario_modificacion='" + usuarioModificacion + "'";
/* 308 */       s = s + " where ";
/* 309 */       s = s + " codigo_ciclo=" + codigoCiclo;
/* 310 */       s = s + " and codigo_plan=" + codigoPlan;
/* 311 */       s = s + " and codigo_objetivo=" + codigoObjetivo;
/* 312 */       s = s + " and codigo_meta=" + codigoMeta;
/* 313 */       s = s + " and periodo=" + periodo;
/* 314 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 317 */     catch (Exception e) {
/* 318 */       e.printStackTrace();
/* 319 */       Utilidades.writeError("CalLogrosFactory:modificarJustificacion", e);
/*     */       
/* 321 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean borrarRegistro(int codigoCiclo, int codigoPlan, int codigoObjetivo, int codigoMeta, int periodo) {
/*     */     try {
/* 331 */       String s = "delete from  cal_logros  ";
/* 332 */       s = s + " where ";
/* 333 */       s = s + " codigo_ciclo=" + codigoCiclo;
/* 334 */       s = s + " and codigo_plan=" + codigoPlan;
/* 335 */       s = s + " and codigo_objetivo=" + codigoObjetivo;
/* 336 */       s = s + " and codigo_meta=" + codigoMeta;
/* 337 */       s = s + " and periodo=" + periodo;
/* 338 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 341 */     catch (Exception e) {
/* 342 */       e.printStackTrace();
/* 343 */       Utilidades.writeError("CalLogrosFactory:modificarRegistro", e);
/*     */       
/* 345 */       return false;
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
/*     */   public boolean generarEstadisticaAreas(int ciclo, int plan, int periodo1, int periodo2, int incluirHijas, String secuencia, String proceso, String tipoProceso) {
/*     */     try {
/* 363 */       String s = "select ";
/* 364 */       s = s + " p.codigo as proceso,";
/* 365 */       s = s + " p.descripcion as nombre_proceso,";
/* 366 */       s = s + " sp.codigo as subproceso,";
/* 367 */       s = s + " sp.descripcion as nombre_subproceso,";
/* 368 */       s = s + " l.codigo_meta,";
/* 369 */       s = s + " pm.descripcion as nombre_meta,";
/* 370 */       s = s + " u.codigo,";
/* 371 */       s = s + " u.descripcion as nombre_area,";
/* 372 */       s = s + " l.periodo,";
/* 373 */       s = s + " l.valor_logro,";
/* 374 */       s = s + " sp.factor,";
/* 375 */       s = s + " pl.codigo_plan,";
/* 376 */       s = s + " pm.tipo_medicion,";
/* 377 */       s = s + " case when (pm.tipo_medicion='A' AND l.valor_logro>l.valor_meta)";
/* 378 */       s = s + " OR  (pm.tipo_medicion='B' AND l.valor_logro>=l.valor_meta)";
/* 379 */       s = s + " OR  (pm.tipo_medicion='C' AND l.valor_logro=l.valor_meta)";
/* 380 */       s = s + " OR  (pm.tipo_medicion='D' AND l.valor_logro<=l.valor_meta)";
/* 381 */       s = s + " OR  (pm.tipo_medicion='E' AND l.valor_logro<l.valor_meta) then 'S' else 'N' end as cumple,";
/* 382 */       s = s + " l.justificacion,";
/* 383 */       s = s + " l.valor_meta as valor_meta,";
/* 384 */       s = s + " pm.valor_meta as valor_meta_plan";
/* 385 */       s = s + " from ";
/* 386 */       s = s + " cal_logros l,";
/* 387 */       s = s + " cal_planes pl,";
/* 388 */       s = s + " cal_plan_objetivos po,";
/* 389 */       s = s + " caL_plan_metas pm,";
/* 390 */       s = s + " procesos p,";
/* 391 */       s = s + " subprocesos sp,";
/* 392 */       s = s + " unidades_dependencia u";
/* 393 */       s = s + " where l.codigo_ciclo=pl.ciclo ";
/* 394 */       s = s + " and l.codigo_plan=pl.codigo_plan";
/* 395 */       s = s + " and u.codigo=pl.codigo_area ";
/* 396 */       s = s + " and l.codigo_ciclo=po.codigo_ciclo ";
/* 397 */       s = s + " and l.codigo_plan=po.codigo_plan ";
/* 398 */       s = s + " and l.codigo_objetivo=po.codigo_objetivo ";
/* 399 */       s = s + " and l.codigo_ciclo=pm.codigo_ciclo ";
/* 400 */       s = s + " and l.codigo_plan=pm.codigo_plan";
/* 401 */       s = s + " and l.codigo_meta=pm.codigo_meta";
/* 402 */       s = s + " and po.proceso=p.codigo";
/* 403 */       s = s + " and po.proceso=sp.proceso";
/* 404 */       s = s + " and po.subproceso=sp.codigo";
/* 405 */       s = s + " and po.estado='A'";
/* 406 */       s = s + " and l.estado='A'";
/* 407 */       s = s + " and l.codigo_ciclo=" + ciclo;
/* 408 */       s = s + " and l.periodo>=" + periodo1;
/* 409 */       s = s + " and l.periodo<=" + periodo2;
/*     */       
/* 411 */       if (incluirHijas == 1) {
/* 412 */         s = s + " and u.secuencia like '" + secuencia + "%'";
/*     */       }
/* 414 */       if (incluirHijas == 0) {
/* 415 */         s = s + " and l.codigo_plan=" + plan;
/*     */       }
/*     */       
/* 418 */       if (proceso.length() > 0) {
/* 419 */         s = s + " and p.codigo='" + proceso + "'";
/*     */       }
/* 421 */       if (tipoProceso.length() > 0) {
/* 422 */         s = s + " and p.tipo_proceso='" + tipoProceso + "'";
/*     */       }
/*     */       
/* 425 */       s = s + " order by ";
/* 426 */       s = s + " p.codigo,";
/* 427 */       s = s + " p.descripcion,";
/* 428 */       s = s + " sp.codigo,";
/* 429 */       s = s + " sp.descripcion,";
/* 430 */       s = s + " pm.descripcion ,";
/* 431 */       s = s + " u.descripcion,";
/* 432 */       s = s + " l.periodo";
/* 433 */       boolean rtaDB = this.dat.parseSql(s);
/* 434 */       if (!rtaDB) {
/* 435 */         return false;
/*     */       }
/*     */       
/* 438 */       this.rs = this.dat.getResultSet();
/*     */     }
/* 440 */     catch (Exception e) {
/* 441 */       e.printStackTrace();
/* 442 */       Utilidades.writeError("CalLogrosFactory:generarEstadisticaAreas ", e);
/*     */     } 
/* 444 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalLogrosDTO nextEstAreas() {
/*     */     try {
/* 455 */       if (this.rs.next()) {
/* 456 */         return leerRegEstArea();
/*     */       }
/*     */     }
/* 459 */     catch (Exception e) {
/* 460 */       e.printStackTrace();
/* 461 */       Utilidades.writeError("CalLogrosFactory:next ", e);
/*     */     } 
/* 463 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalLogrosDTO leerRegEstArea() {
/*     */     try {
/* 473 */       CalLogrosDTO reg = new CalLogrosDTO();
/* 474 */       reg.setProceso(this.rs.getString("proceso"));
/* 475 */       reg.setSubproceso(this.rs.getString("subproceso"));
/* 476 */       reg.setNombreProceso(this.rs.getString("nombre_proceso"));
/* 477 */       reg.setNombreSubProceso(this.rs.getString("nombre_subproceso"));
/* 478 */       reg.setNombreMeta(this.rs.getString("nombre_meta"));
/* 479 */       reg.setCodigoPlan(this.rs.getInt("codigo_plan"));
/* 480 */       reg.setNombreArea(this.rs.getString("nombre_area"));
/* 481 */       reg.setFactor(this.rs.getInt("factor"));
/* 482 */       reg.setCumple(this.rs.getString("cumple"));
/* 483 */       reg.setPeriodo(this.rs.getInt("periodo"));
/* 484 */       reg.setValorLogro(this.rs.getDouble("valor_logro"));
/* 485 */       reg.setJustificacion(this.rs.getString("justificacion"));
/* 486 */       reg.setValorLogro(this.rs.getDouble("valor_logro"));
/* 487 */       reg.setValorMeta(this.rs.getDouble("valor_meta"));
/* 488 */       reg.setValorMetaPlan(this.rs.getDouble("valor_meta_plan"));
/* 489 */       reg.setTipoMedicion(this.rs.getString("tipo_medicion"));
/* 490 */       return reg;
/*     */     }
/* 492 */     catch (Exception e) {
/* 493 */       e.printStackTrace();
/* 494 */       Utilidades.writeError("CalLogrosFactory:leerRegistro ", e);
/*     */       
/* 496 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalLogrosDTO getCumplimiento(int codigoCiclo, int periodo, int codigoArea) {
/*     */     try {
/* 506 */       String s = "select count(0) as numero_logros,";
/* 507 */       s = s + " sum(case when (cal_plan_metas.tipo_medicion='A' AND cal_logros.valor_logro>cal_logros.valor_meta)";
/* 508 */       s = s + "  OR  (cal_plan_metas.tipo_medicion='B' AND cal_logros.valor_logro>=cal_logros.valor_meta)";
/* 509 */       s = s + "  OR  (cal_plan_metas.tipo_medicion='C' AND cal_logros.valor_logro=cal_logros.valor_meta)";
/* 510 */       s = s + "  OR  (cal_plan_metas.tipo_medicion='D' AND cal_logros.valor_logro<=cal_logros.valor_meta)";
/* 511 */       s = s + "  OR  (cal_plan_metas.tipo_medicion='E' AND cal_logros.valor_logro<cal_logros.valor_meta)";
/* 512 */       s = s + "  then 1 else 0 end) as cumplen";
/* 513 */       s = s + " from cal_planes,cal_logros,cal_plan_metas";
/* 514 */       s = s + " where";
/* 515 */       s = s + " cal_planes.codigo_plan=cal_logros.codigo_plan";
/* 516 */       s = s + " and cal_planes.ciclo=cal_logros.codigo_ciclo";
/* 517 */       s = s + " and cal_logros.codigo_ciclo=cal_plan_metas.codigo_ciclo";
/* 518 */       s = s + " and cal_logros.codigo_plan=cal_plan_metas.codigo_plan";
/* 519 */       s = s + " and cal_logros.codigo_meta=cal_plan_metas.codigo_meta";
/* 520 */       s = s + " and cal_logros.estado='A'";
/* 521 */       s = s + " and cal_logros.codigo_ciclo=" + codigoCiclo;
/* 522 */       s = s + " and cal_logros.periodo=" + periodo;
/* 523 */       s = s + " and cal_planes.codigo_area=" + codigoArea;
/* 524 */       boolean rtaDB = this.dat.parseSql(s);
/* 525 */       if (!rtaDB) {
/* 526 */         return null;
/*     */       }
/*     */       
/* 529 */       this.rs = this.dat.getResultSet();
/* 530 */       if (this.rs.next()) {
/* 531 */         CalLogrosDTO reg = new CalLogrosDTO();
/* 532 */         reg.setNumeroLogros(this.rs.getInt("numero_logros"));
/* 533 */         reg.setCumplen(this.rs.getInt("cumplen"));
/* 534 */         return reg;
/*     */       } 
/* 536 */       return null;
/*     */     }
/* 538 */     catch (Exception e) {
/* 539 */       e.printStackTrace();
/* 540 */       Utilidades.writeError("CalLogrosFactory:cargarLogros ", e);
/*     */       
/* 542 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalLogrosDTO nextEstadistica() {
/*     */     try {
/* 554 */       if (this.rs.next()) {
/* 555 */         return leerRegistroEstadistica();
/*     */       }
/*     */     }
/* 558 */     catch (Exception e) {
/* 559 */       e.printStackTrace();
/* 560 */       Utilidades.writeError("CalLogrosFactory:next ", e);
/*     */     } 
/* 562 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalLogrosDTO leerRegistroEstadistica() {
/*     */     try {
/* 572 */       CalLogrosDTO reg = new CalLogrosDTO();
/* 573 */       reg.setNombreSubProceso(this.rs.getString("nombre_subproceso"));
/* 574 */       reg.setCumplen(this.rs.getInt("cumplen"));
/* 575 */       reg.setNoCumplen(this.rs.getInt("no_cumplen"));
/* 576 */       return reg;
/*     */     }
/* 578 */     catch (Exception e) {
/* 579 */       e.printStackTrace();
/* 580 */       Utilidades.writeError("CalLogrosFactory:leerRegistro ", e);
/*     */       
/* 582 */       return null;
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
/*     */   public boolean generarEstadistica2(int ciclo, int plan, int periodo1, int periodo2, int incluirHijas, String secuencia, String proceso, String tipoProceso) {
/*     */     try {
/* 599 */       String s = "select unidades_dependencia.codigo,";
/* 600 */       s = s + " unidades_dependencia.descripcion as nombre_area,";
/* 601 */       s = s + " sum(case when (cal_plan_metas.tipo_medicion='A' AND cal_logros.valor_logro>cal_logros.valor_meta)";
/* 602 */       s = s + "  OR  (cal_plan_metas.tipo_medicion='B' AND cal_logros.valor_logro>=cal_logros.valor_meta)";
/* 603 */       s = s + "  OR  (cal_plan_metas.tipo_medicion='C' AND cal_logros.valor_logro=cal_logros.valor_meta)";
/* 604 */       s = s + "  OR  (cal_plan_metas.tipo_medicion='D' AND cal_logros.valor_logro<=cal_logros.valor_meta)";
/* 605 */       s = s + "  OR  (cal_plan_metas.tipo_medicion='E' AND cal_logros.valor_logro<cal_logros.valor_meta)";
/* 606 */       s = s + "  then 1 else 0 end) as cumplen,";
/* 607 */       s = s + " count(0) as totales";
/* 608 */       s = s + " from ";
/* 609 */       s = s + " cal_logros,";
/* 610 */       s = s + " cal_planes,";
/* 611 */       s = s + " cal_plan_objetivos,";
/* 612 */       s = s + " caL_plan_metas,";
/* 613 */       s = s + " procesos,";
/* 614 */       s = s + " subprocesos,";
/* 615 */       s = s + " unidades_dependencia where  cal_logros.codigo_ciclo=cal_planes.ciclo ";
/* 616 */       s = s + " and cal_logros.codigo_plan=cal_planes.codigo_plan";
/* 617 */       s = s + " and unidades_dependencia.codigo=cal_planes.codigo_area ";
/* 618 */       s = s + " and caL_logros.codigo_ciclo=cal_plan_objetivos.codigo_ciclo ";
/* 619 */       s = s + " and caL_logros.codigo_plan=cal_plan_objetivos.codigo_plan ";
/* 620 */       s = s + " and caL_logros.codigo_objetivo=cal_plan_objetivos.codigo_objetivo ";
/* 621 */       s = s + " and caL_logros.codigo_ciclo=cal_plan_metas.codigo_ciclo ";
/* 622 */       s = s + " and caL_logros.codigo_plan=cal_plan_metas.codigo_plan";
/* 623 */       s = s + " and caL_logros.codigo_meta=cal_plan_metas.codigo_meta";
/* 624 */       s = s + " and cal_plan_objetivos.proceso=procesos.codigo";
/* 625 */       s = s + " and cal_plan_objetivos.proceso=subprocesos.proceso";
/* 626 */       s = s + " and cal_plan_objetivos.subproceso=subprocesos.codigo";
/* 627 */       s = s + " and cal_logros.estado='A'";
/* 628 */       s = s + " and cal_plan_objetivos.estado='A'";
/* 629 */       s = s + " and caL_logros.codigo_ciclo=" + ciclo;
/* 630 */       s = s + " and caL_logros.periodo>=" + periodo1;
/* 631 */       s = s + " and caL_logros.periodo<=" + periodo2;
/*     */       
/* 633 */       if (incluirHijas == 1) {
/* 634 */         s = s + " and unidades_dependencia.secuencia like '" + secuencia + "%'";
/*     */       }
/* 636 */       if (incluirHijas == 0) {
/* 637 */         s = s + " and caL_logros.codigo_plan=" + plan;
/*     */       }
/*     */       
/* 640 */       if (proceso.length() > 0) {
/* 641 */         s = s + " and procesos.codigo='" + proceso + "'";
/*     */       }
/* 643 */       if (tipoProceso.length() > 0) {
/* 644 */         s = s + " and procesos.tipo_proceso='" + tipoProceso + "'";
/*     */       }
/* 646 */       s = s + " group by unidades_dependencia.descripcion,unidades_dependencia.codigo";
/* 647 */       s = s + " order by ";
/* 648 */       s = s + " unidades_dependencia.descripcion";
/* 649 */       boolean rtaDB = this.dat.parseSql(s);
/* 650 */       if (!rtaDB) {
/* 651 */         return false;
/*     */       }
/*     */       
/* 654 */       this.rs = this.dat.getResultSet();
/*     */     }
/* 656 */     catch (Exception e) {
/* 657 */       e.printStackTrace();
/* 658 */       Utilidades.writeError("CalLogrosFactory:generarEstadistica2 ", e);
/*     */     } 
/* 660 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalLogrosDTO next2() {
/*     */     try {
/* 671 */       if (this.rs.next()) {
/* 672 */         return leerReg2();
/*     */       }
/*     */     }
/* 675 */     catch (Exception e) {
/* 676 */       e.printStackTrace();
/* 677 */       Utilidades.writeError("CalLogrosFactory:next3 ", e);
/*     */     } 
/* 679 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalLogrosDTO leerReg2() {
/*     */     try {
/* 689 */       CalLogrosDTO reg = new CalLogrosDTO();
/* 690 */       reg.setCodigoArea(this.rs.getInt("codigo"));
/* 691 */       reg.setCumplen(this.rs.getInt("cumplen"));
/* 692 */       reg.setNumeroLogros(this.rs.getInt("totales"));
/* 693 */       reg.setNombreArea(this.rs.getString("nombre_area"));
/* 694 */       return reg;
/*     */     }
/* 696 */     catch (Exception e) {
/* 697 */       e.printStackTrace();
/* 698 */       Utilidades.writeError("CalLogrosFactory:leerReg3 ", e);
/*     */       
/* 700 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean generarEstadistica5(int ciclo, int plan) {
/*     */     try {
/* 712 */       String s = "select ";
/* 713 */       s = s + " p.codigo as proceso,";
/* 714 */       s = s + " p.descripcion as nombre_proceso,";
/* 715 */       s = s + " subp.codigo as subproceso,";
/* 716 */       s = s + " subp.descripcion as nombre_subproceso,";
/* 717 */       s = s + " l.periodo,pm.descripcion as nombre_meta,";
/* 718 */       s = s + " subp.factor,";
/* 719 */       s = s + " l.valor_logro";
/* 720 */       s = s + " from cal_logros l,cal_planes pl,cal_plan_objetivos po,caL_plan_metas pm,procesos p,subprocesos subp";
/* 721 */       s = s + " where ";
/* 722 */       s = s + " l.codigo_ciclo=pl.ciclo";
/* 723 */       s = s + " and l.codigo_plan=pl.codigo_plan";
/* 724 */       s = s + " and l.codigo_ciclo=po.codigo_ciclo";
/* 725 */       s = s + " and l.codigo_plan=po.codigo_plan";
/* 726 */       s = s + " and l.codigo_objetivo=po.codigo_objetivo";
/* 727 */       s = s + " and l.codigo_ciclo=pm.codigo_ciclo";
/* 728 */       s = s + " and l.codigo_plan=pm.codigo_plan";
/* 729 */       s = s + " and l.codigo_meta=pm.codigo_meta";
/* 730 */       s = s + " and po.proceso=p.codigo";
/* 731 */       s = s + " and po.proceso=subp.proceso";
/* 732 */       s = s + " and po.subproceso=subp.codigo";
/* 733 */       s = s + " and l.estado='A'";
/* 734 */       s = s + " and l.codigo_ciclo=" + ciclo;
/* 735 */       s = s + " and l.codigo_plan=" + plan;
/*     */       
/* 737 */       boolean rtaDB = this.dat.parseSql(s);
/* 738 */       if (!rtaDB) {
/* 739 */         return false;
/*     */       }
/*     */       
/* 742 */       this.rs = this.dat.getResultSet();
/*     */     }
/* 744 */     catch (Exception e) {
/* 745 */       e.printStackTrace();
/* 746 */       Utilidades.writeError("CalLogrosFactory:cargarCalLogros ", e);
/*     */     } 
/* 748 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalLogrosDTO next5() {
/*     */     try {
/* 759 */       if (this.rs.next()) {
/* 760 */         return leerReg5();
/*     */       }
/*     */     }
/* 763 */     catch (Exception e) {
/* 764 */       e.printStackTrace();
/* 765 */       Utilidades.writeError("CalLogrosFactory:next3 ", e);
/*     */     } 
/* 767 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalLogrosDTO leerReg5() {
/*     */     try {
/* 777 */       CalLogrosDTO reg = new CalLogrosDTO();
/*     */       
/* 779 */       reg.setPeriodo(this.rs.getInt("periodo"));
/* 780 */       reg.setValorLogro(this.rs.getDouble("valor_logro"));
/*     */       
/* 782 */       reg.setProceso(this.rs.getString("proceso"));
/* 783 */       reg.setSubproceso(this.rs.getString("subproceso"));
/* 784 */       reg.setNombreProceso(this.rs.getString("nombre_proceso"));
/* 785 */       reg.setNombreSubProceso(this.rs.getString("nombre_subproceso"));
/* 786 */       reg.setFactor(this.rs.getInt("factor"));
/* 787 */       reg.setNombreMeta(this.rs.getString("nombre_meta"));
/*     */       
/* 789 */       return reg;
/*     */     }
/* 791 */     catch (Exception e) {
/* 792 */       e.printStackTrace();
/* 793 */       Utilidades.writeError("CalLogrosFactory:leerReg3 ", e);
/*     */       
/* 795 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection cumplimentoPlan(int codigoCiclo, int codigoPlan) {
/* 806 */     Collection resultados = new ArrayList();
/*     */     
/*     */     try {
/* 809 */       String s = "select Proceso, \t\t Nombre_Proceso, \t\t (100 * Cumple / Registros) Cumplimiento from   (select Food.Proceso, \t\t\t\t\tFood.Nombre_Proceso, \t\t\t\t\t \t\t\t\t\tsum(case \t\t\t\t\t\t\t when Food.Cumple = 'S' then \t\t\t\t\t\t\t  1 \t\t\t\t\t\t\t else \t\t\t\t\t\t\t  0 \t\t\t\t\t\t end) Cumple, \t\t\t\t\tsum(case \t\t\t\t\t\t\t when Food.Cumple = 'N' then \t\t\t\t\t\t\t  1 \t\t\t\t\t\t\t else \t\t\t\t\t\t\t  0 \t\t\t\t\t\t end) No_Cumple, \t\t\t\t\tcount(0) Registros \t\t  from   (select p.Codigo as Proceso, \t\t\t\t\t\t\t  p.Descripcion as Nombre_Proceso, \t\t\t\t\t\t\t  case \t\t\t\t\t\t\t\t  when (Pm.Tipo_Medicion = 'A' and l.Valor_Logro > l.Valor_Meta) \t\t\t\t\t\t\t\t\t\t or (Pm.Tipo_Medicion = 'B' and l.Valor_Logro >= l.Valor_Meta) \t\t\t\t\t\t\t\t\t\t or (Pm.Tipo_Medicion = 'C' and l.Valor_Logro = l.Valor_Meta) \t\t\t\t\t\t\t\t\t\t or (Pm.Tipo_Medicion = 'D' and l.Valor_Logro <= l.Valor_Meta) \t\t\t\t\t\t\t\t\t\t or (Pm.Tipo_Medicion = 'E' and l.Valor_Logro < l.Valor_Meta) then \t\t\t\t\t\t\t\t\t'S' \t\t\t\t\t\t\t\t  else \t\t\t\t\t\t\t\t\t'N' \t\t\t\t\t\t\t  end as Cumple \t\t\t\t\t from   Cal_Logros         l, \t\t\t\t\t\t\t  Cal_Planes         Pl, \t\t\t\t\t\t\t  Cal_Plan_Objetivos Po, \t\t\t\t\t\t\t  Cal_Plan_Metas     Pm, \t\t\t\t\t\t\t  Procesos           p \t\t\t\t\t where  l.Codigo_Ciclo = Pl.Ciclo \t\t\t\t\t\t\t  and l.Codigo_Plan = Pl.Codigo_Plan \t\t\t\t\t\t\t  and l.Codigo_Ciclo = Po.Codigo_Ciclo \t\t\t\t\t\t\t  and l.Codigo_Plan = Po.Codigo_Plan \t\t\t\t\t\t\t  and l.Codigo_Objetivo = Po.Codigo_Objetivo \t\t\t\t\t\t\t  and l.Codigo_Ciclo = Pm.Codigo_Ciclo \t\t\t\t\t\t\t  and l.Codigo_Plan = Pm.Codigo_Plan \t\t\t\t\t\t\t  and l.Codigo_Meta = Pm.Codigo_Meta \t\t\t\t\t\t\t  and Po.Proceso = p.Codigo \t\t\t\t\t\t\t  and Po.Estado = 'A' \t\t\t\t\t\t\t  and Pm.Estado = 'A' \t\t\t\t\t\t\t  and l.Estado = 'A' \t\t\t\t\t\t\t  and p.Estado = 'A' \t\t\t\t\t\t\t  and l.Codigo_Ciclo = " + codigoCiclo + " \t\t\t\t\t\t\t  and l.Codigo_Plan = " + codigoPlan + ") AS Food" + " \t\t  group  by Food.Proceso," + " \t\t\t\t\t\tFood.Nombre_Proceso) AS DATOS" + " order  by 1,2,3";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 864 */       boolean rtaDB = this.dat.parseSql(s);
/* 865 */       this.rs = this.dat.getResultSet();
/* 866 */       while (this.rs.next()) {
/* 867 */         CalLogrosDTO reg = new CalLogrosDTO();
/* 868 */         reg.setProceso(this.rs.getString("Proceso"));
/* 869 */         reg.setNombreProceso(this.rs.getString("Nombre_Proceso"));
/* 870 */         reg.setValorLogro(this.rs.getDouble("Cumplimiento"));
/* 871 */         resultados.add(reg);
/*     */       }
/*     */     
/*     */     }
/* 875 */     catch (Exception e) {
/* 876 */       e.printStackTrace();
/* 877 */       Utilidades.writeError("CalLogrosFactory:cargarLogros ", e);
/*     */     } 
/* 879 */     return resultados;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\CalLogrosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */