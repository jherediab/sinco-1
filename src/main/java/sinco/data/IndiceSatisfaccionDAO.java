/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import sinco.business.IndiceSatisfaccionDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.IndiceSatisfaccionDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IndiceSatisfaccionDAO
/*     */ {
/*     */   ResultSet rs;
/*  27 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  43 */       this.dat.close();
/*     */     }
/*  45 */     catch (Exception e) {
/*  46 */       Utilidades.writeError("IndiceSatisfaccionFactory:close", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndiceSatisfaccionDTO next() {
/*     */     try {
/*  57 */       if (this.rs.next()) {
/*  58 */         return leerRegistro();
/*     */       }
/*     */     }
/*  61 */     catch (Exception e) {
/*  62 */       e.printStackTrace();
/*  63 */       Utilidades.writeError("IndiceSatisfaccionFactory:next ", e);
/*     */     } 
/*  65 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndiceSatisfaccionDTO leerRegistro() {
/*     */     try {
/*  75 */       IndiceSatisfaccionDTO reg = new IndiceSatisfaccionDTO();
/*  76 */       reg.setNombre(this.rs.getString("nombre"));
/*  77 */       reg.setCodigo(this.rs.getInt("codigo"));
/*  78 */       reg.setOportunidadExcelente(this.rs.getInt("oportunidad_excelente"));
/*  79 */       reg.setOportunidadBuena(this.rs.getInt("oportunidad_buena"));
/*  80 */       reg.setOportunidadRegular(this.rs.getInt("oportunidad_regular"));
/*  81 */       reg.setConfiabilidadExcelente(this.rs.getInt("confiabilidad_excelente"));
/*  82 */       reg.setConfiabilidadBuena(this.rs.getInt("confiabilidad_buena"));
/*  83 */       reg.setConfiabilidadRegular(this.rs.getInt("confiabilidad_regular"));
/*  84 */       return reg;
/*     */     }
/*  86 */     catch (Exception e) {
/*  87 */       e.printStackTrace();
/*  88 */       Utilidades.writeError("IndiceSatisfaccionFactory:leerRegistro ", e);
/*     */       
/*  90 */       return null;
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
/*     */   public IndiceSatisfaccionDTO cargarIndiceArea(int area, int anno, int mesInicial, int mesFinal) {
/*     */     try {
/* 106 */       String s = "select a.codigo_area as codigo,u.descripcion as nombre,sum(oportunidad_excelente) as oportunidad_excelente,sum(oportunidad_buena) as oportunidad_buena,sum(oportunidad_regular) as oportunidad_regular,sum(confiabilidad_excelente) as confiabilidad_excelente,sum(confiabilidad_buena) as confiabilidad_buena,sum(confiabilidad_regular) as confiabilidad_regular from   acumulados a,        unidades_dependencia u where  u.codigo = a.codigo_area and u.codigo =" + area + " and a.anno=" + anno + " and a.mes>=" + mesInicial + " and a.mes<=" + mesFinal + " group by a.codigo_area , u.descripcion";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 158 */       boolean rtaDB = this.dat.parseSql(s);
/* 159 */       if (!rtaDB) {
/* 160 */         return null;
/*     */       }
/* 162 */       this.rs = this.dat.getResultSet();
/* 163 */       if (this.rs.next()) {
/* 164 */         return leerRegistro();
/*     */       }
/*     */     }
/* 167 */     catch (Exception e) {
/* 168 */       e.printStackTrace();
/* 169 */       Utilidades.writeError("IndiceSatisfaccionFactory:cargarIndiceSatisfaccion ", e);
/*     */     } 
/* 171 */     return null;
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
/*     */   public IndiceSatisfaccionDTO cargarIndicePersona(int persona, int anno, int mesInicial, int mesFinal) {
/*     */     try {
/* 187 */       String s = "select a.codigo_persona as codigo,u.apellidos||' '||u.nombres as nombre,sum(oportunidad_excelente) as oportunidad_excelente,sum(oportunidad_buena) as oportunidad_buena,sum(oportunidad_regular) as oportunidad_regular,sum(confiabilidad_excelente) as confiabilidad_excelente,sum(confiabilidad_buena) as confiabilidad_buena,sum(confiabilidad_regular) as confiabilidad_regular from   acumulados_persona  a, sis_usuarios u where  u.codigo_empleado= a.codigo_persona       and a.anno = " + anno + "       and a.mes >= " + mesInicial + "       and a.mes <= " + mesFinal + "       and u.codigo_empleado=" + persona + " group  by a.codigo_persona,u.apellidos||' '||u.nombres ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 235 */       boolean rtaDB = this.dat.parseSql(s);
/* 236 */       if (!rtaDB) {
/* 237 */         return null;
/*     */       }
/* 239 */       this.rs = this.dat.getResultSet();
/* 240 */       if (this.rs.next()) {
/* 241 */         return leerRegistro();
/*     */       }
/*     */     }
/* 244 */     catch (Exception e) {
/* 245 */       e.printStackTrace();
/* 246 */       Utilidades.writeError("IndiceSatisfaccionFactory:cargarIndiceSatisfaccion ", e);
/*     */     } 
/* 248 */     return null;
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
/*     */   public Collection cargarIndiceSecuencia(Collection arrAreas, int anno, int mesInicial, int mesFinal) {
/* 266 */     Collection resultados = new ArrayList();
/*     */ 
/*     */     
/*     */     try {
/* 270 */       String s = "select a.codigo_area as codigo,u.descripcion as nombre,sum(oportunidad_excelente) as oportunidad_excelente,sum(oportunidad_buena) as oportunidad_buena,sum(oportunidad_regular) as oportunidad_regular,sum(confiabilidad_excelente) as confiabilidad_excelente,sum(confiabilidad_buena) as confiabilidad_buena,sum(confiabilidad_regular) as confiabilidad_regular from   acumulados a,unidades_dependencia u where  u.codigo = a.codigo_area";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 282 */       Iterator iterator = arrAreas.iterator();
/* 283 */       String areas = "";
/* 284 */       while (iterator.hasNext()) {
/* 285 */         Integer codigo = (Integer)iterator.next();
/* 286 */         areas = areas + codigo + ",";
/*     */       } 
/* 288 */       areas = areas + "-2";
/*     */       
/* 290 */       s = s + " and u.codigo IN (" + areas + ") " + " and a.anno=" + anno + " and a.mes>=" + mesInicial + " and a.mes<=" + mesFinal + " group by a.codigo_area , u.descripcion";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 342 */       boolean rtaDB = this.dat.parseSql(s);
/*     */       
/* 344 */       if (!rtaDB) {
/* 345 */         return resultados;
/*     */       }
/* 347 */       this.rs = this.dat.getResultSet();
/* 348 */       while (this.rs.next()) {
/* 349 */         resultados.add(leerRegistro());
/*     */       }
/* 351 */       this.rs = this.dat.getResultSet();
/*     */     }
/* 353 */     catch (Exception e) {
/* 354 */       e.printStackTrace();
/* 355 */       Utilidades.writeError("IndiceSatisfaccionFactory:cargarTodos ", e);
/*     */     } 
/* 357 */     return resultados;
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
/*     */   public Collection reporteRevisionDireccion(String fechaInicio, String fechaFin, String tipoArea) {
/* 370 */     Collection resultados = new ArrayList();
/*     */     try {
/* 372 */       String s = "select areas.descripcion as nombre,food.* from ( select coalesce(U.AREA_AGRUPA,U.CODIGO) as codigo,sum(case when s.fecha_oportunidad>=" + Utilidades.formatoFecha2(fechaInicio) + " and s.fecha_oportunidad<" + Utilidades.formatoFecha2(fechaFin) + " and s.codigo_oportunidad='E' then 1 else 0 end) as oportunidad_excelente," + " sum(" + " case when " + " s.fecha_oportunidad>=" + Utilidades.formatoFecha2(fechaInicio) + " and s.fecha_oportunidad<" + Utilidades.formatoFecha2(fechaFin) + " and s.codigo_oportunidad='B' then 1 else 0 end) as oportunidad_buena," + " sum(" + " case when" + " s.fecha_oportunidad>=" + Utilidades.formatoFecha2(fechaInicio) + " and s.fecha_oportunidad<" + Utilidades.formatoFecha2(fechaFin) + " and s.codigo_oportunidad='R' then 1 else 0 end) as oportunidad_regular," + " sum(" + " case when s.fecha_confiabilidad>=" + Utilidades.formatoFecha2(fechaInicio) + " and s.fecha_confiabilidad<" + Utilidades.formatoFecha2(fechaFin) + " and s.codigo_confiabilidad='E' then 1 else 0 end) as confiabilidad_excelente," + " sum(" + " case when s.fecha_confiabilidad>=" + Utilidades.formatoFecha2(fechaInicio) + " and s.fecha_confiabilidad<" + Utilidades.formatoFecha2(fechaFin) + " and s.codigo_confiabilidad='B' then 1 else 0 end) as confiabilidad_buena," + " sum(" + " case when s.fecha_confiabilidad>=" + Utilidades.formatoFecha2(fechaInicio) + " and s.fecha_confiabilidad<" + Utilidades.formatoFecha2(fechaFin) + " and s.codigo_confiabilidad='R' then 1 else 0 end) as confiabilidad_regular," + " sum(" + " case when s.fecha_vigencia>=" + Utilidades.formatoFecha2(fechaInicio) + " and s.fecha_vigencia<" + Utilidades.formatoFecha2(fechaFin) + " then 1 else 0 end) as recibidas," + " sum(" + " case when s.fecha_real_terminacion>=" + Utilidades.formatoFecha2(fechaInicio) + " and s.fecha_real_terminacion<" + Utilidades.formatoFecha2(fechaFin) + " then 1 else 0 end) as atendidas," + " sum(" + " case when s.fecha_vigencia>=" + Utilidades.formatoFecha2(fechaInicio) + " and s.fecha_vigencia<" + Utilidades.formatoFecha2(fechaFin) + " and s.fecha_real_terminacion is null" + " then 1 else 0 end) as por_atender," + " sum((SELECT COUNT(0) FROM HISTORIA_SOLICITUD h" + "    WHERE  h.NUMERO_SOLICITUD=s.NUMERO" + "      AND h.FECHA_CAMBIO>=" + Utilidades.formatoFecha2(fechaInicio) + "      AND h.FECHA_CAMBIO<" + Utilidades.formatoFecha2(fechaFin) + "      AND h.OBSERVACIONES LIKE 'Correo%'" + "      AND h.ESTADO_INICIAL=" + ParametrosDTO.getInt("PRV") + "      AND h.ESTADO_FINAL=" + ParametrosDTO.getInt("ESC") + ")) as escaladas" + " from   solicitudes s," + " unidades_dependencia u" + " where  u.codigo = s.area_proveedor";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 436 */       if (tipoArea.length() > 0) {
/* 437 */         s = s + " and u.tipo_area='" + tipoArea + "'";
/*     */       }
/* 439 */       s = s + " and u.estado='A' and s.fecha_generada  >= " + Utilidades.formatoFecha2(fechaInicio) + " group  by coalesce(U.AREA_AGRUPA,U.CODIGO)" + " ) food,unidades_dependencia areas" + " where" + " food.codigo=areas.codigo" + " and food.oportunidad_excelente+" + " food.oportunidad_buena+" + " food.oportunidad_regular+" + " food.confiabilidad_excelente+" + " food.confiabilidad_buena+" + " food.confiabilidad_regular>0" + " order by areas.secuencia";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 453 */       boolean rtaDB = this.dat.parseSql(s);
/*     */       
/* 455 */       if (!rtaDB) {
/* 456 */         return resultados;
/*     */       }
/* 458 */       this.rs = this.dat.getResultSet();
/* 459 */       while (this.rs.next()) {
/* 460 */         IndiceSatisfaccionDTO reg = new IndiceSatisfaccionDTO();
/* 461 */         reg.setNombre(this.rs.getString("nombre"));
/* 462 */         reg.setCodigo(this.rs.getInt("codigo"));
/* 463 */         reg.setOportunidadExcelente(this.rs.getInt("oportunidad_excelente"));
/* 464 */         reg.setOportunidadBuena(this.rs.getInt("oportunidad_buena"));
/* 465 */         reg.setOportunidadRegular(this.rs.getInt("oportunidad_regular"));
/* 466 */         reg.setConfiabilidadExcelente(this.rs.getInt("confiabilidad_excelente"));
/* 467 */         reg.setConfiabilidadBuena(this.rs.getInt("confiabilidad_buena"));
/* 468 */         reg.setConfiabilidadRegular(this.rs.getInt("confiabilidad_regular"));
/* 469 */         reg.setRecibidas(this.rs.getInt("recibidas"));
/* 470 */         reg.setAtendidas(this.rs.getInt("atendidas"));
/* 471 */         reg.setPorAtender(this.rs.getInt("por_atender"));
/* 472 */         reg.setEscaladas(this.rs.getInt("escaladas"));
/* 473 */         resultados.add(reg);
/*     */       }
/*     */     
/* 476 */     } catch (Exception e) {
/* 477 */       e.printStackTrace();
/* 478 */       Utilidades.writeError("IndiceSatisfaccionFactory:reporteRevisionDireccion ", e);
/*     */     } 
/* 480 */     return resultados;
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
/*     */   public Collection indiceProntitud(int anno, int mes1, int area) {
/* 495 */     Collection resultados = new ArrayList();
/*     */     try {
/* 497 */       String s = "select u.Descripcion as Nombre_Area,        u.Codigo as Codigo_Area,        sum(Food.Tiempo_Base) as Tiempo_Base,        sum(Food.Numero_Base) as Numero_Base,        sum(Food.Tiempo_Movil) as Tiempo_Movil,        sum(Food.Numero_Movil) as Numero_Movil  from   Unidades_Dependencia u,        (         select Mo.Codigo_Area,                 sum(Mo.Tiempo_Atencion) as Tiempo_Movil,                 sum(Mo.Numero_Servicios) as Numero_Movil,                 0 as Tiempo_Base,                 0 as Numero_Base         from   Cal_Prontitud Mo         where  Mo.Anno = " + anno + "                and Mo.Mes = " + mes1 + "                and Mo.Codigo_Area in" + "                (select Di.Codigo" + "                     from   Unidades_Dependencia Di" + "                     where  Di.Secuencia like (select u.Secuencia" + "                                               from   Unidades_Dependencia u" + "                                               where  u.Codigo = " + area + ") || '%')" + "         group  by Mo.Codigo_Area" + "         union all" + "         select Lb.Codigo_Area," + "                0 as Tiempo_Movil," + "                0 as Numero_Movil," + "                sum(Lb.Tiempo_Atencion) as Tiempo_Base," + "                sum(Lb.Numero_Servicios) as Numero_Base" + "         from   Cal_Prontitud      Lb," + "                Cal_Base_Prontitud b" + "         where  b.Anno = " + anno + "                and b.Mes = " + mes1 + "                and b.Anno_Base = Lb.Anno" + "                and Lb.Mes >= b.Mes_Base_Inicio" + "                and Lb.Mes <= b.Mes_Base_Final" + "                and Lb.Codigo_Area in" + "                (select Di.Codigo" + "                     from   Unidades_Dependencia Di" + "                     where  Di.Secuencia like (select u.Secuencia" + "                                               from   Unidades_Dependencia u" + "                                               where  u.Codigo = " + area + ") || '%')" + "         group  by Lb.Codigo_Area" + "         ) Food" + " where  u.Codigo = Food.Codigo_Area" + " group  by u.Descripcion," + "           u.Codigo" + "  order  by u.descripcion";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 548 */       boolean rtaDB = this.dat.parseSql(s);
/*     */       
/* 550 */       if (!rtaDB) {
/* 551 */         return resultados;
/*     */       }
/* 553 */       this.rs = this.dat.getResultSet();
/* 554 */       while (this.rs.next()) {
/* 555 */         IndiceSatisfaccionDTO reg = new IndiceSatisfaccionDTO();
/*     */         
/* 557 */         reg.setNombreArea(this.rs.getString("nombre_area"));
/* 558 */         reg.setCodigoArea(this.rs.getInt("codigo_area"));
/* 559 */         reg.setTiempoBase(this.rs.getDouble("tiempo_base"));
/* 560 */         reg.setTiempoMovil(this.rs.getDouble("tiempo_movil"));
/* 561 */         reg.setNumeroBase(this.rs.getInt("numero_base"));
/* 562 */         reg.setNumeroMovil(this.rs.getInt("numero_movil"));
/* 563 */         resultados.add(reg);
/*     */       }
/*     */     
/* 566 */     } catch (Exception e) {
/* 567 */       e.printStackTrace();
/* 568 */       Utilidades.writeError("IndiceSatisfaccionFactory:reporteRevisionDireccion ", e);
/*     */     } 
/* 570 */     return resultados;
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
/*     */   public Collection detalleProntitud(int anno, int mes1, int area) {
/* 586 */     Collection resultados = new ArrayList();
/*     */     try {
/* 588 */       String s = "select s.Codigo as Codigo_Servicio,        s.Descripcion as Nombre_Servicio,        sum(food.Tiempo_Base) as Tiempo_Base,        sum(food.Numero_Base) as Numero_Base,        sum(food.Tiempo_Movil ) as Tiempo_Movil,        sum(food.Numero_Movil ) as Numero_Movil from   Servicios s,        (                  select Mo.Codigo_Area,                 Mo.Codigo_Servicio,                0 as Tiempo_Base,                0 as Numero_Base,                 sum(Mo.Tiempo_Atencion) as Tiempo_Movil,                 sum(Mo.Numero_Servicios) as Numero_Movil         from   Cal_Prontitud Mo         where  Mo.Anno = " + anno + "                and Mo.Mes = " + mes1 + "                and Mo.Codigo_Area = " + area + "         group  by Mo.Codigo_Area," + "                    Mo.Codigo_Servicio" + "                  " + "       UNION ALL" + "        " + "        select Lb.Codigo_Area," + "                Lb.Codigo_Servicio," + "                sum(Lb.Tiempo_Atencion) as Tiempo_Base," + "                sum(Lb.Numero_Servicios) as Numero_Base," + "                 0 as Tiempo_Movil," + "                 0 as Numero_Movil" + "         from   Cal_Prontitud      Lb," + "                Cal_Base_Prontitud b" + "         where  b.Anno = " + anno + "                and b.Mes = " + mes1 + "                and b.Anno_Base = Lb.Anno" + "                and Lb.Mes >= b.Mes_Base_Inicio" + "                and Lb.Mes <= b.Mes_Base_Final" + "                and Lb.Codigo_Area = " + area + "         group  by Lb.Codigo_Area," + "                   Lb.Codigo_Servicio" + "                 " + "                 " + "                 ) food" + " where  food.Codigo_Servicio=s.CODIGO" + " GROUP BY s.Codigo ," + "        s.Descripcion     " + " order by s.Descripcion";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 639 */       boolean rtaDB = this.dat.parseSql(s);
/*     */       
/* 641 */       if (!rtaDB) {
/* 642 */         return resultados;
/*     */       }
/* 644 */       this.rs = this.dat.getResultSet();
/* 645 */       while (this.rs.next()) {
/* 646 */         IndiceSatisfaccionDTO reg = new IndiceSatisfaccionDTO();
/*     */         
/* 648 */         reg.setNombreServicio(this.rs.getString("nombre_servicio"));
/* 649 */         reg.setCodigoServicio(this.rs.getInt("codigo_servicio"));
/* 650 */         reg.setTiempoBase(this.rs.getDouble("tiempo_base"));
/* 651 */         reg.setTiempoMovil(this.rs.getDouble("tiempo_movil"));
/* 652 */         reg.setNumeroBase(this.rs.getInt("numero_base"));
/* 653 */         reg.setNumeroMovil(this.rs.getInt("numero_movil"));
/* 654 */         resultados.add(reg);
/*     */       }
/*     */     
/* 657 */     } catch (Exception e) {
/* 658 */       e.printStackTrace();
/* 659 */       Utilidades.writeError("IndiceSatisfaccionFactory:reporteRevisionDireccion ", e);
/*     */     } 
/* 661 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndiceSatisfaccionDTO baseProntitud(int anno, int mes) {
/*     */     try {
/* 672 */       String s = "select  p.anno_base, p.mes_base_inicio, p.mes_base_final from cal_base_prontitud p where p.anno=" + anno + " and p.mes=" + mes;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 681 */       boolean rtaDB = this.dat.parseSql(s);
/* 682 */       if (!rtaDB) {
/* 683 */         return null;
/*     */       }
/* 685 */       this.rs = this.dat.getResultSet();
/* 686 */       if (this.rs.next()) {
/* 687 */         IndiceSatisfaccionDTO reg = new IndiceSatisfaccionDTO();
/* 688 */         reg.setAnnoBase(this.rs.getInt("anno_base"));
/* 689 */         reg.setMesBase1(this.rs.getInt("mes_base_inicio"));
/* 690 */         reg.setMesBase2(this.rs.getInt("mes_base_final"));
/* 691 */         return reg;
/*     */       }
/*     */     
/* 694 */     } catch (Exception e) {
/* 695 */       e.printStackTrace();
/* 696 */       Utilidades.writeError("IndiceSatisfaccionFactory:cargarIndiceSatisfaccion ", e);
/*     */     } 
/* 698 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\IndiceSatisfaccionDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */