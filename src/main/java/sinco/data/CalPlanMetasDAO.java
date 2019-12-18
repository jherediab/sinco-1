/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import sinco.business.CalMetasDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CalPlanMetasDAO;
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
/*     */ public class CalPlanMetasDAO
/*     */ {
/*     */   ResultSet rs;
/*  26 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  42 */       this.dat.close();
/*     */     }
/*  44 */     catch (Exception e) {
/*  45 */       Utilidades.writeError("CalPlanMetasFactory:close", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalMetasDTO leerRegistro() {
/*     */     try {
/*  56 */       CalMetasDTO reg = new CalMetasDTO();
/*  57 */       reg.setCodigoCiclo(this.rs.getInt("codigo_ciclo"));
/*  58 */       reg.setCodigoPlan(this.rs.getInt("codigo_plan"));
/*  59 */       reg.setCodigoMeta(this.rs.getInt("codigo_meta"));
/*  60 */       reg.setCodigoObjetivo(this.rs.getInt("codigo_objetivo"));
/*  61 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  62 */       reg.setJustificacion(this.rs.getString("justificacion"));
/*  63 */       reg.setValorMeta(this.rs.getDouble("valor_meta"));
/*  64 */       reg.setTipoMedicion(this.rs.getString("tipo_medicion"));
/*  65 */       reg.setFuenteDato(this.rs.getString("fuente_dato"));
/*  66 */       reg.setAplicaEn(this.rs.getString("aplica_en"));
/*  67 */       reg.setUnidadMedida(this.rs.getString("unidad_medida"));
/*  68 */       reg.setValorMinimo(this.rs.getDouble("valor_minimo"));
/*  69 */       reg.setValorMaximo(this.rs.getDouble("valor_maximo"));
/*  70 */       reg.setMes01(this.rs.getString("mes01"));
/*  71 */       reg.setMes02(this.rs.getString("mes02"));
/*  72 */       reg.setMes03(this.rs.getString("mes03"));
/*  73 */       reg.setMes04(this.rs.getString("mes04"));
/*  74 */       reg.setMes05(this.rs.getString("mes05"));
/*  75 */       reg.setMes06(this.rs.getString("mes06"));
/*  76 */       reg.setMes07(this.rs.getString("mes07"));
/*  77 */       reg.setMes08(this.rs.getString("mes08"));
/*  78 */       reg.setMes09(this.rs.getString("mes09"));
/*  79 */       reg.setMes10(this.rs.getString("mes10"));
/*  80 */       reg.setMes11(this.rs.getString("mes11"));
/*  81 */       reg.setMes12(this.rs.getString("mes12"));
/*  82 */       reg.setEstado(this.rs.getString("estado"));
/*  83 */       reg.setTipoGrafica(this.rs.getString("tipo_grafica"));
/*  84 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  85 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  86 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  87 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  88 */       reg.setNombreTipoMedicion(this.rs.getString("nombreTipoMedicion"));
/*  89 */       reg.setNombreEstado(this.rs.getString("nombreEstado"));
/*  90 */       reg.setNombreUnidadMedida(this.rs.getString("nombre_unidad_medida"));
/*     */       
/*     */       try {
/*  93 */         reg.setNumeroAcciones(this.rs.getInt("acciones"));
/*     */       }
/*  95 */       catch (Exception e) {}
/*     */ 
/*     */ 
/*     */       
/*  99 */       return reg;
/*     */     }
/* 101 */     catch (Exception e) {
/* 102 */       e.printStackTrace();
/* 103 */       Utilidades.writeError("CalPlanMetasFactory:leerRegistro ", e);
/*     */       
/* 105 */       return null;
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
/*     */   public Collection cargarDeObjetivo(int codigoCiclo, int codigoPlan, int objetivo, int periodo, String estado) {
/* 121 */     Collection resultados = new ArrayList();
/*     */     try {
/* 123 */       String s = "select m.Codigo_Ciclo,  m.Codigo_Plan,  m.Codigo_Meta,  m.Codigo_Objetivo,  m.Descripcion,  m.Valor_Meta,  m.Tipo_Medicion,  m.Frecuencia_Medicion,  m.Justificacion,  m.Estado,  m.Fecha_Insercion,  m.Usuario_Insercion,  m.Fecha_Modificacion,  m.Usuario_Modificacion,  m.Mes01,  m.Mes02,  m.Mes03,  m.Mes04,  m.Mes05,  m.Mes06,  m.Mes07,  m.Mes08,  m.Mes09,  m.Mes10,  m.Mes11,  m.Mes12,  m.Fuente_Dato,  m.Aplica_En,  m.Unidad_Medida,  m.Valor_Minimo,  m.Valor_Maximo,  m.Tipo_Grafica,  Tm.Descripcion         as Nombretipomedicion,  Est.Descripcion        as Nombreestado,  Um.Descripcion         as Nombre_Unidad_Medida,  SUM(CASE WHEN ac.NUMERO IS NOT NULL THEN 1 ELSE 0 END) acciones from   Cal_Plan_Metas   m  left join Am_Acciones      Ac on( m.Codigo_Ciclo = Ac.Codigo_Ciclo and m.Codigo_Plan = Ac.Codigo_Plan and m.Codigo_Meta = Ac.Codigo_Meta and Ac.Asociado = 'P'), \t\t Sis_Multivalores Tm, \t\t Sis_Multivalores Est, \t\t Sis_Multivalores Um where  m.Tipo_Medicion = Tm.Valor  and Tm.Tabla = 'CAL_TIPO_MEDICION'  and m.Estado = Est.Valor  and Est.Tabla = 'CAL_ESTADO_META'  and m.Unidad_Medida = Um.Valor  and Um.Tabla = 'CAL_UNIDAD_MEDIDA_META'  and m.codigo_ciclo=" + codigoCiclo + "  and m.codigo_plan=" + codigoPlan + "  and m.codigo_objetivo=" + objetivo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 180 */       if (estado.length() > 0) {
/* 181 */         s = s + " and m.estado='A'";
/*     */       }
/*     */       
/* 184 */       if (periodo == 1) {
/* 185 */         s = s + " and m.mes01='S'";
/*     */       }
/* 187 */       else if (periodo == 2) {
/* 188 */         s = s + " and m.mes02='S'";
/*     */       }
/* 190 */       else if (periodo == 3) {
/* 191 */         s = s + " and m.mes03='S'";
/*     */       }
/* 193 */       else if (periodo == 4) {
/* 194 */         s = s + " and m.mes04='S'";
/*     */       }
/* 196 */       else if (periodo == 5) {
/* 197 */         s = s + " and m.mes05='S'";
/*     */       }
/* 199 */       else if (periodo == 6) {
/* 200 */         s = s + " and m.mes06='S'";
/*     */       }
/* 202 */       else if (periodo == 7) {
/* 203 */         s = s + " and m.mes07='S'";
/*     */       }
/* 205 */       else if (periodo == 8) {
/* 206 */         s = s + " and m.mes08='S'";
/*     */       }
/* 208 */       else if (periodo == 9) {
/* 209 */         s = s + " and m.mes09='S'";
/*     */       }
/* 211 */       else if (periodo == 10) {
/* 212 */         s = s + " and m.mes10='S'";
/*     */       }
/* 214 */       else if (periodo == 11) {
/* 215 */         s = s + " and m.mes11='S'";
/*     */       }
/* 217 */       else if (periodo == 12) {
/* 218 */         s = s + " and m.mes12='S'";
/*     */       } 
/*     */       
/* 221 */       s = s + " GROUP BY   m.Codigo_Ciclo,  m.Codigo_Plan,  m.Codigo_Meta,  m.Codigo_Objetivo,  m.Descripcion,  m.Valor_Meta,  m.Tipo_Medicion,  m.Frecuencia_Medicion,  m.Justificacion,  m.Estado,  m.Fecha_Insercion,  m.Usuario_Insercion,  m.Fecha_Modificacion,  m.Usuario_Modificacion,  m.Mes01,  m.Mes02,  m.Mes03,  m.Mes04,  m.Mes05,  m.Mes06,  m.Mes07,  m.Mes08,  m.Mes09,  m.Mes10,  m.Mes11,  m.Mes12,  m.Fuente_Dato,  m.Aplica_En,  m.Unidad_Medida,  m.Valor_Minimo,  m.Valor_Maximo,  m.Tipo_Grafica,  Tm.Descripcion,  Est.Descripcion,  Um.Descripcion  order by m.descripcion";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 259 */       boolean rtaDB = this.dat.parseSql(s);
/* 260 */       if (!rtaDB) {
/* 261 */         return resultados;
/*     */       }
/*     */       
/* 264 */       this.rs = this.dat.getResultSet();
/* 265 */       while (this.rs.next()) {
/* 266 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 269 */     catch (Exception e) {
/* 270 */       e.printStackTrace();
/* 271 */       Utilidades.writeError("CalPlanMetasFactory:cargarTodos ", e);
/*     */     } 
/* 273 */     return resultados;
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
/*     */   public Collection cargarMasivo(int codigoCiclo, String proceso, String subproceso, int periodo) {
/* 287 */     Collection resultados = new ArrayList();
/*     */     try {
/* 289 */       String s = "select m.Codigo_Ciclo,  m.Codigo_Plan,  m.Codigo_Meta,  m.Codigo_Objetivo,  u.Descripcion Nombre_Area,  Um.Descripcion as Nombre_Unidad_Medida,  to_char(m.Codigo_Objetivo,'9999999999') ||' - ' || o.Descripcion Nombre_Objetivo,  to_char(m.Codigo_Meta,'9999999999') ||' - ' || m.Descripcion Nombre_Meta,  m.Unidad_Medida,  m.Valor_Minimo,  m.Valor_Maximo,  l.Valor_Logro,  case    when l.Valor_Logro is not null then   'A'  else   'N' end Existe from   Cal_Planes           p,        Cal_Plan_Objetivos   o,        Cal_Plan_Metas       m left join         Cal_Logros           l on(        m.Codigo_Ciclo = l.Codigo_Ciclo        and m.Codigo_Plan = l.Codigo_Plan        and m.Codigo_Meta = l.Codigo_Meta        and m.Codigo_Objetivo = l.Codigo_Objetivo        and " + periodo + " = l.Periodo)," + "        Unidades_Dependencia u," + "        Sis_Multivalores     Um" + " where  p.Ciclo = o.Codigo_Ciclo" + "        and p.Codigo_Plan = o.Codigo_Plan" + "        and o.Codigo_Ciclo = m.Codigo_Ciclo" + "        and o.Codigo_Plan = m.Codigo_Plan" + "        and o.Codigo_Objetivo = m.Codigo_Objetivo" + "        and p.Codigo_Area = u.Codigo" + "        and m.Unidad_Medida = Um.Valor" + "        and Um.Tabla = 'CAL_UNIDAD_MEDIDA_META'" + "        and o.Proceso = '" + proceso + "'" + "        and o.Subproceso = '" + subproceso + "'" + "        and p.Ciclo = " + codigoCiclo + "        and m.Estado = 'A'" + "        and o.Estado = 'A'" + "        and o.Tipo_Objetivo in ('G', 'M')";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 333 */       if (periodo == 1) {
/* 334 */         s = s + " and m.mes01='S'";
/*     */       }
/* 336 */       else if (periodo == 2) {
/* 337 */         s = s + " and m.mes02='S'";
/*     */       }
/* 339 */       else if (periodo == 3) {
/* 340 */         s = s + " and m.mes03='S'";
/*     */       }
/* 342 */       else if (periodo == 4) {
/* 343 */         s = s + " and m.mes04='S'";
/*     */       }
/* 345 */       else if (periodo == 5) {
/* 346 */         s = s + " and m.mes05='S'";
/*     */       }
/* 348 */       else if (periodo == 6) {
/* 349 */         s = s + " and m.mes06='S'";
/*     */       }
/* 351 */       else if (periodo == 7) {
/* 352 */         s = s + " and m.mes07='S'";
/*     */       }
/* 354 */       else if (periodo == 8) {
/* 355 */         s = s + " and m.mes08='S'";
/*     */       }
/* 357 */       else if (periodo == 9) {
/* 358 */         s = s + " and m.mes09='S'";
/*     */       }
/* 360 */       else if (periodo == 10) {
/* 361 */         s = s + " and m.mes10='S'";
/*     */       }
/* 363 */       else if (periodo == 11) {
/* 364 */         s = s + " and m.mes11='S'";
/*     */       }
/* 366 */       else if (periodo == 12) {
/* 367 */         s = s + " and m.mes12='S'";
/*     */       } 
/*     */       
/* 370 */       s = s + " order  by m.Codigo_Ciclo,           m.Codigo_Objetivo,           m.Codigo_Meta,           u.Descripcion";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 377 */       boolean rtaDB = this.dat.parseSql(s);
/* 378 */       if (!rtaDB) {
/* 379 */         return resultados;
/*     */       }
/*     */       
/* 382 */       this.rs = this.dat.getResultSet();
/* 383 */       while (this.rs.next()) {
/* 384 */         CalMetasDTO reg = new CalMetasDTO();
/* 385 */         reg.setCodigoCiclo(codigoCiclo);
/* 386 */         reg.setCodigoPlan(this.rs.getInt("codigo_plan"));
/* 387 */         reg.setCodigoMeta(this.rs.getInt("codigo_meta"));
/* 388 */         reg.setCodigoObjetivo(this.rs.getInt("codigo_objetivo"));
/* 389 */         reg.setNombreArea(this.rs.getString("Nombre_Area"));
/* 390 */         reg.setNombreMeta(this.rs.getString("Nombre_meta"));
/* 391 */         reg.setNombreObjetivo(this.rs.getString("Nombre_Objetivo"));
/* 392 */         reg.setUnidadMedida(this.rs.getString("unidad_medida"));
/* 393 */         reg.setValorMinimo(this.rs.getDouble("valor_minimo"));
/* 394 */         reg.setValorMaximo(this.rs.getDouble("valor_maximo"));
/* 395 */         reg.setValorLogro(this.rs.getDouble("Valor_Logro"));
/* 396 */         reg.setNombreUnidadMedida(this.rs.getString("nombre_unidad_medida"));
/* 397 */         reg.setEstado(this.rs.getString("existe"));
/* 398 */         resultados.add(reg);
/*     */       }
/*     */     
/* 401 */     } catch (Exception e) {
/* 402 */       e.printStackTrace();
/* 403 */       Utilidades.writeError("CalPlanMetasFactory:cargarTodos ", e);
/*     */     } 
/* 405 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalMetasDTO cargarRegistro(int codigoCiclo, int codigoPlan, int codigoMeta) {
/*     */     try {
/* 416 */       String s = "select m.*,";
/* 417 */       s = s + "tm.descripcion as nombreTipoMedicion,";
/* 418 */       s = s + "est.descripcion as nombreEstado,";
/* 419 */       s = s + "Um.Descripcion as nombre_unidad_medida";
/* 420 */       s = s + " from cal_plan_metas m,sis_multivalores tm,sis_multivalores est,Sis_Multivalores Um";
/* 421 */       s = s + " where ";
/* 422 */       s = s + " m.tipo_medicion =tm.valor";
/* 423 */       s = s + " and tm.tabla='CAL_TIPO_MEDICION'";
/* 424 */       s = s + " and m.estado  =est.valor";
/* 425 */       s = s + " and est.tabla='CAL_ESTADO_META'";
/* 426 */       s = s + " and m.Unidad_Medida = Um.Valor";
/* 427 */       s = s + " and Um.Tabla = 'CAL_UNIDAD_MEDIDA_META'";
/* 428 */       s = s + " and m.codigo_ciclo=" + codigoCiclo;
/* 429 */       s = s + " and m.codigo_plan=" + codigoPlan;
/* 430 */       s = s + " and m.codigo_meta=" + codigoMeta;
/* 431 */       boolean rtaDB = this.dat.parseSql(s);
/* 432 */       if (!rtaDB) {
/* 433 */         return null;
/*     */       }
/*     */       
/* 436 */       this.rs = this.dat.getResultSet();
/* 437 */       if (this.rs.next()) {
/* 438 */         return leerRegistro();
/*     */       }
/*     */     }
/* 441 */     catch (Exception e) {
/* 442 */       e.printStackTrace();
/* 443 */       Utilidades.writeError("CalPlanMetasFactory:cargarCalMetas ", e);
/*     */     } 
/* 445 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro(int codigoCiclo, int codigoPlan) {
/* 454 */     int numero = 1;
/* 455 */     String s = "select max(codigo_meta) from cal_plan_metas";
/* 456 */     s = s + "  where codigo_ciclo=" + codigoCiclo;
/*     */     try {
/* 458 */       boolean rta = this.dat.parseSql(s);
/* 459 */       if (!rta) return 0; 
/* 460 */       this.rs = this.dat.getResultSet();
/* 461 */       if (this.rs.next()) {
/* 462 */         s = this.rs.getString(1);
/* 463 */         if (!this.rs.wasNull()) {
/* 464 */           numero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 467 */       if (numero < 10000) numero = 10000; 
/* 468 */       return numero;
/*     */     }
/* 470 */     catch (Exception e) {
/* 471 */       e.printStackTrace();
/* 472 */       Utilidades.writeError("CalPlanMetasFactory:siguienteRegistro", e);
/*     */       
/* 474 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(int codigoCiclo, int codigoPlan, int codigoMeta) {
/*     */     try {
/* 484 */       String s = "delete from  cal_plan_metas";
/* 485 */       s = s + " where codigo_ciclo=" + codigoCiclo;
/* 486 */       s = s + " and codigo_plan=" + codigoPlan;
/* 487 */       s = s + " codigo_meta=" + codigoMeta;
/* 488 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 491 */     catch (Exception e) {
/* 492 */       e.printStackTrace();
/* 493 */       Utilidades.writeError("CalPlanMetasFactory:eliminarRegistro", e);
/*     */       
/* 495 */       return false;
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
/*     */   private boolean crearRecurso(int codigoCiclo, int codigoPlan, int codigoMeta, int codigoRecurso, String estado, String usuarioInsercion) {
/*     */     try {
/* 513 */       String s = "select estado from  cal_plan_recursos_meta r where r.codigo_ciclo=" + codigoCiclo + " and r.codigo_plan=" + codigoPlan + " and r.codigo_meta=" + codigoMeta + " and r.codigo_recurso=" + codigoRecurso;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 520 */       this.dat.parseSql(s);
/* 521 */       this.rs = this.dat.getResultSet();
/* 522 */       if (this.rs.next()) {
/* 523 */         s = "update cal_plan_recursos_meta set ";
/* 524 */         s = s + " estado='" + estado + "',";
/* 525 */         s = s + " fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 526 */         s = s + " usuario_modificacion='" + usuarioInsercion + "'";
/* 527 */         s = s + " where ";
/* 528 */         s = s + " codigo_ciclo=" + codigoCiclo;
/* 529 */         s = s + " and codigo_plan=" + codigoPlan;
/* 530 */         s = s + " and codigo_meta=" + codigoMeta;
/* 531 */         s = s + " and codigo_recurso=" + codigoRecurso;
/*     */       } else {
/*     */         
/* 534 */         s = "insert into cal_plan_recursos_meta (codigo_ciclo,codigo_plan,codigo_meta,codigo_recurso,estado,fecha_insercion,usuario_insercion) values (" + codigoCiclo + "," + "" + codigoPlan + "," + "" + codigoMeta + "," + "" + codigoRecurso + "," + "'" + estado + "'," + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 552 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 555 */     catch (Exception e) {
/* 556 */       e.printStackTrace();
/* 557 */       Utilidades.writeError("CalMetasDAO:crearRegistro", e);
/*     */       
/* 559 */       return false;
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
/*     */   private boolean crearResponsable(int codigoCiclo, int codigoPlan, int codigoMeta, int codigoResponsable, String estado, String usuarioInsercion) {
/*     */     try {
/* 578 */       String s = "select estado from  cal_plan_responsables_meta r where r.codigo_ciclo=" + codigoCiclo + " and r.codigo_plan=" + codigoPlan + " and r.codigo_meta=" + codigoMeta + " and r.codigo_responsable=" + codigoResponsable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 585 */       this.dat.parseSql(s);
/* 586 */       this.rs = this.dat.getResultSet();
/* 587 */       if (this.rs.next()) {
/* 588 */         s = "update cal_plan_responsables_meta set estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioInsercion + "'" + " where codigo_ciclo=" + codigoCiclo + " and codigo_plan=" + codigoPlan + " and codigo_meta=" + codigoMeta + " and codigo_responsable=" + codigoResponsable;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 599 */         s = "insert into cal_plan_responsables_meta (codigo_ciclo,codigo_plan,codigo_meta,codigo_responsable,estado,fecha_insercion,usuario_insercion) values (" + codigoCiclo + "," + "" + codigoPlan + "," + "" + codigoMeta + "," + "" + codigoResponsable + "," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 617 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 620 */     catch (Exception e) {
/* 621 */       e.printStackTrace();
/* 622 */       Utilidades.writeError("CalResponsablesMetaFactory:crearRegistro", e);
/*     */       
/* 624 */       return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean crearRegistro(int codigoCiclo, int codigoPlan, int codigoObjetivo, String descripcion, String justificacion, double valorMeta, double valorMinimo, double valorMaximo, String tipoMedicion, String estado, String fuenteDato, String aplicaEn, String unidadMedida, String tipoGrafica, String mes01, String mes02, String mes03, String mes04, String mes05, String mes06, String mes07, String mes08, String mes09, String mes10, String mes11, String mes12, Collection arrResponsables, Collection arrRecursos, String usuarioInsercion) {
/* 664 */     int elSiguiente = siguienteRegistro(codigoCiclo, codigoPlan);
/* 665 */     if (elSiguiente == 0) {
/* 666 */       return false;
/*     */     }
/*     */     
/*     */     try {
/* 670 */       String s = "insert into cal_plan_metas (codigo_ciclo,codigo_plan,codigo_meta,codigo_objetivo,descripcion,justificacion,valor_meta,valor_minimo,valor_maximo,tipo_medicion,estado,fuente_dato,aplica_en,unidad_medida,tipo_grafica,mes01,mes02,mes03,mes04,mes05,mes06,mes07,mes08,mes09,mes10,mes11,mes12,fecha_insercion,usuario_insercion) values (" + codigoCiclo + "," + "" + codigoPlan + "," + "" + elSiguiente + "," + "" + codigoObjetivo + "," + "'" + descripcion + "'," + "'" + justificacion + "'," + "" + valorMeta + "," + "" + valorMinimo + "," + "" + valorMaximo + "," + "'" + tipoMedicion + "'," + "'" + estado + "'," + "'" + fuenteDato + "'," + "'" + aplicaEn + "'," + "'" + unidadMedida + "'," + "'" + tipoGrafica + "'," + "'" + mes01 + "'," + "'" + mes02 + "'," + "'" + mes03 + "'," + "'" + mes04 + "'," + "'" + mes05 + "'," + "'" + mes06 + "'," + "'" + mes07 + "'," + "'" + mes08 + "'," + "'" + mes09 + "'," + "'" + mes10 + "'," + "'" + mes11 + "'," + "'" + mes12 + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 731 */       boolean rta = this.dat.executeUpdate(s);
/*     */ 
/*     */       
/* 734 */       if (rta) {
/*     */         
/* 736 */         s = "delete from cal_plan_recursos_meta where codigo_ciclo=" + codigoCiclo + " and codigo_plan=" + codigoPlan + " and codigo_meta=" + elSiguiente;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 742 */         this.dat.executeUpdate(s);
/* 743 */         Iterator iterator = arrRecursos.iterator();
/* 744 */         while (iterator.hasNext()) {
/* 745 */           Integer codigo = (Integer)iterator.next();
/* 746 */           crearRecurso(codigoCiclo, codigoPlan, elSiguiente, codigo.intValue(), "A", usuarioInsercion);
/*     */         } 
/* 748 */         s = "delete from cal_plan_responsables_meta where codigo_ciclo=" + codigoCiclo + " and codigo_plan=" + codigoPlan + " and codigo_meta=" + elSiguiente + this.dat.executeUpdate(s);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 756 */         Iterator iterator2 = arrResponsables.iterator();
/* 757 */         while (iterator2.hasNext()) {
/* 758 */           Integer codigo = (Integer)iterator2.next();
/* 759 */           crearResponsable(codigoCiclo, codigoPlan, elSiguiente, codigo.intValue(), "A", usuarioInsercion);
/*     */         } 
/*     */       } 
/*     */       
/* 763 */       return rta;
/*     */     }
/* 765 */     catch (Exception e) {
/* 766 */       e.printStackTrace();
/* 767 */       Utilidades.writeError("%CalMetasDAO:crearRegistro ", e);
/*     */       
/* 769 */       return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean modificarRegistro(int codigoCiclo, int codigoPlan, int codigoMeta, int codigoObjetivo, String descripcion, String justificacion, double valorMeta, double valorMinimo, double valorMaximo, String tipoMedicion, String estado, String fuenteDato, String aplicaEn, String unidadMedida, String tipoGrafica, String mes01, String mes02, String mes03, String mes04, String mes05, String mes06, String mes07, String mes08, String mes09, String mes10, String mes11, String mes12, Collection arrResponsables, Collection arrRecursos, String usuarioModificacion) {
/*     */     try {
/* 810 */       String s = "update cal_plan_metas  set  codigo_objetivo=" + codigoObjetivo + "," + " descripcion='" + descripcion + "'," + " justificacion='" + justificacion + "'," + " valor_meta=" + valorMeta + "," + " valor_minimo=" + valorMinimo + "," + " valor_maximo=" + valorMaximo + "," + " tipo_medicion='" + tipoMedicion + "'," + " estado='" + estado + "'," + " fuente_dato='" + fuenteDato + "'," + " aplica_en='" + aplicaEn + "'," + " unidad_medida='" + unidadMedida + "'," + " tipo_grafica='" + tipoGrafica + "'," + " mes01='" + mes01 + "'," + " mes02='" + mes02 + "'," + " mes03='" + mes03 + "'," + " mes04='" + mes04 + "'," + " mes05='" + mes05 + "'," + " mes06='" + mes06 + "'," + " mes07='" + mes07 + "'," + " mes08='" + mes08 + "'," + " mes09='" + mes09 + "'," + " mes10='" + mes10 + "'," + " mes11='" + mes11 + "'," + " mes12='" + mes12 + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo_meta=" + codigoMeta + " and codigo_ciclo=" + codigoCiclo + " and codigo_plan=" + codigoPlan;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 842 */       boolean rta = this.dat.executeUpdate(s);
/*     */ 
/*     */ 
/*     */       
/* 846 */       if (rta) {
/*     */         
/* 848 */         s = "update cal_plan_recursos_meta  set estado='X', fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo_ciclo=" + codigoCiclo + " and codigo_plan=" + codigoPlan + " and codigo_meta=" + codigoMeta;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 857 */         this.dat.executeUpdate(s);
/* 858 */         Iterator iterator = arrRecursos.iterator();
/* 859 */         while (iterator.hasNext()) {
/* 860 */           Integer codigo = (Integer)iterator.next();
/* 861 */           crearRecurso(codigoCiclo, codigoPlan, codigoMeta, codigo.intValue(), "A", usuarioModificacion);
/*     */         } 
/*     */         
/* 864 */         s = "delete from  cal_plan_recursos_meta  where codigo_ciclo=" + codigoCiclo + " and codigo_plan=" + codigoPlan + " and codigo_meta=" + codigoMeta + " and estado='X'";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 870 */         this.dat.executeUpdate(s);
/*     */         
/* 872 */         s = "update cal_plan_responsables_meta set estado='X', fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where codigo_ciclo=" + codigoCiclo + " and codigo_plan=" + codigoPlan + " and codigo_meta=" + codigoMeta;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 880 */         this.dat.executeUpdate(s);
/*     */         
/* 882 */         Iterator iterator2 = arrResponsables.iterator();
/* 883 */         while (iterator2.hasNext()) {
/* 884 */           Integer codigo = (Integer)iterator2.next();
/* 885 */           crearResponsable(codigoCiclo, codigoPlan, codigoMeta, codigo.intValue(), "A", usuarioModificacion);
/*     */         } 
/*     */         
/* 888 */         s = "delete from cal_plan_responsables_meta where codigo_ciclo=" + codigoCiclo + " and codigo_plan=" + codigoPlan + " and codigo_meta=" + codigoMeta + " and estado='X'";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 895 */         this.dat.executeUpdate(s);
/*     */       } 
/*     */ 
/*     */       
/* 899 */       return rta;
/*     */     }
/* 901 */     catch (Exception e) {
/* 902 */       e.printStackTrace();
/* 903 */       Utilidades.writeError("CalMetasDAO:modificarRegistro ", e);
/*     */       
/* 905 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\CalPlanMetasDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */