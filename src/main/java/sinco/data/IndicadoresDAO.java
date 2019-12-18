/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.IndicadoresDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.IndicadoresDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IndicadoresDAO
/*     */ {
/*     */   ResultSet rs;
/*  29 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  47 */       this.dat.close();
/*     */     }
/*  49 */     catch (Exception e) {
/*  50 */       Utilidades.writeError("IndicadoresDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadoresDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("IndicadoresDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadoresDTO leerRegistro() {
/*     */     try {
/*  79 */       IndicadoresDTO reg = new IndicadoresDTO();
/*     */       
/*  81 */       reg.setCodigoIndicador(this.rs.getString("codigo_indicador"));
/*  82 */       reg.setNombreIndicador(this.rs.getString("nombre_indicador"));
/*  83 */       reg.setProceso(this.rs.getInt("proceso"));
/*  84 */       reg.setCiclo(this.rs.getInt("ciclo"));
/*  85 */       reg.setArea(this.rs.getInt("area"));
/*  86 */       reg.setObjetivoEstrategico(this.rs.getInt("objetivo_estrategico"));
/*  87 */       reg.setIndicadorAcuerdo(this.rs.getString("indicador_acuerdo"));
/*  88 */       reg.setTipoIndicador(this.rs.getString("tipo_indicador"));
/*  89 */       reg.setProyectoInversion(this.rs.getString("proyecto_inversion"));
/*  90 */       reg.setMetaPlanDeDesarrollo(this.rs.getInt("meta_plan_de_desarrollo"));
/*  91 */       reg.setMetaProyecto(this.rs.getInt("meta_proyecto"));
/*  92 */       reg.setObjetivoIndicador(this.rs.getString("objetivo_indicador"));
/*  93 */       reg.setPrioridadEnProducto(this.rs.getString("prioridad_en_producto"));
/*  94 */       reg.setNombrePrioridadEnProducto(this.rs.getString("nombre_prioridad_en_producto"));
/*  95 */       reg.setUnidadMedida(this.rs.getString("unidad_medida"));
/*  96 */       reg.setFrecuenciaMedicion(this.rs.getString("frecuencia_medicion"));
/*  97 */       reg.setFormula(this.rs.getString("formula"));
/*     */       
/*  99 */       reg.setMes1(this.rs.getString("mes1"));
/* 100 */       reg.setMes2(this.rs.getString("mes2"));
/* 101 */       reg.setMes3(this.rs.getString("mes3"));
/* 102 */       reg.setMes4(this.rs.getString("mes4"));
/* 103 */       reg.setMes5(this.rs.getString("mes5"));
/* 104 */       reg.setMes6(this.rs.getString("mes6"));
/* 105 */       reg.setMes7(this.rs.getString("mes7"));
/* 106 */       reg.setMes8(this.rs.getString("mes8"));
/* 107 */       reg.setMes9(this.rs.getString("mes9"));
/* 108 */       reg.setMes10(this.rs.getString("mes10"));
/* 109 */       reg.setMes11(this.rs.getString("mes11"));
/* 110 */       reg.setMes12(this.rs.getString("mes12"));
/* 111 */       reg.setValormes1(this.rs.getInt("valormes1"));
/* 112 */       reg.setValormes2(this.rs.getInt("valormes2"));
/* 113 */       reg.setValormes3(this.rs.getInt("valormes3"));
/* 114 */       reg.setValormes4(this.rs.getInt("valormes4"));
/* 115 */       reg.setValormes5(this.rs.getInt("valormes5"));
/* 116 */       reg.setValormes6(this.rs.getInt("valormes6"));
/* 117 */       reg.setValormes7(this.rs.getInt("valormes7"));
/* 118 */       reg.setValormes8(this.rs.getInt("valormes8"));
/* 119 */       reg.setValormes9(this.rs.getInt("valormes9"));
/* 120 */       reg.setValormes10(this.rs.getInt("valormes10"));
/* 121 */       reg.setValormes11(this.rs.getInt("valormes11"));
/* 122 */       reg.setValormes12(this.rs.getInt("valormes12"));
/* 123 */       reg.setEstado(this.rs.getString("estado"));
/* 124 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/* 125 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/* 126 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/* 127 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/* 128 */       reg.setNombreProceso(this.rs.getString("nombre_proceso"));
/* 129 */       reg.setNombreCiclo(this.rs.getString("nombre_ciclo"));
/* 130 */       reg.setNombreArea(this.rs.getString("nombre_area"));
/* 131 */       reg.setNombreObjetivoEstrategico(this.rs.getString("nombre_objetivo_estrategico"));
/* 132 */       reg.setNombreTipoIndicador(this.rs.getString("nombre_tipo_indicador"));
/* 133 */       reg.setNombreProyectoInversion(this.rs.getString("nombre_proyecto_inversion"));
/* 134 */       reg.setNombreMetaPlanDeDesarrollo(this.rs.getString("nombre_meta_plan_de_desarrollo"));
/* 135 */       reg.setNombreMetaProyecto(this.rs.getString("nombre_meta_proyecto"));
/* 136 */       reg.setNombreUnidadMedida(this.rs.getString("nombre_unidad_medida"));
/* 137 */       reg.setNombreFrecuenciaMedicion(this.rs.getString("nombre_frecuencia_medicion"));
/* 138 */       reg.setNombreEstado(this.rs.getString("nombre_estado"));
/* 139 */       reg.setNombreProductoPMR(this.rs.getString("producto_PMR"));
/*     */       
/* 141 */       reg.setMetaTotalProgramada(this.rs.getInt("meta_total_programada"));
/* 142 */       reg.setMetaEstaVigencia(this.rs.getInt("meta_esta_vigencia"));
/* 143 */       reg.setAcumuladoVigencias(this.rs.getInt("acumulado_vigencias"));
/* 144 */       reg.setMetaTotalProgramada(this.rs.getInt("acumulado_esta_vigencia"));
/* 145 */       reg.setLogroTotal(this.rs.getInt("logro_total"));
/* 146 */       reg.setLogroAcumuladoTotal(this.rs.getInt("logro_acumulado_total"));
/* 147 */       return reg;
/*     */     }
/* 149 */     catch (Exception e) {
/* 150 */       e.printStackTrace();
/* 151 */       Utilidades.writeError("IndicadoresDAO:leerRegistro ", e);
/*     */       
/* 153 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<IndicadoresDTO> cargarTodos() {
/* 163 */     Collection<IndicadoresDTO> resultados = new ArrayList<IndicadoresDTO>();
/*     */     try {
/* 165 */       String s = "select t.codigo_indicador,t.nombre_indicador,t.proceso,r1.DESCRIPCION as nombre_proceso,t.ciclo,r2.DESCRIPCION as nombre_ciclo,t.area,r3.DESCRIPCION as nombre_area,t.objetivo_estrategico,r4.DESCRIPCION as nombre_objetivo_estrategico,t.indicador_acuerdo,m14.DESCRIPCION as nombre_indicador_acuerdo,t.tipo_indicador,m5.DESCRIPCION as nombre_tipo_indicador,t.proyecto_inversion,r6.DESCRIPCION as nombre_proyecto_inversion,t.meta_plan_de_desarrollo,r7.DESCRIPCION as nombre_meta_plan_de_desarrollo,t.meta_proyecto,r8.DESCRIPCION as nombre_meta_proyecto,t.objetivo_indicador,t.prioridad_en_producto,m12.descripcion as nombre_prioridad_en_producto,t.unidad_medida,m9.nombre_unidad as nombre_unidad_medida,t.frecuencia_medicion,m10.DESCRIPCION as nombre_frecuencia_medicion,t.formula,t.mes1,t.mes2,t.mes3,t.mes4,t.mes5,t.mes6,t.mes7,t.mes8,t.mes9,t.mes10,t.mes11,t.mes12,t.valormes1,t.valormes2,t.valormes3,t.valormes4,t.valormes5,t.valormes6,t.valormes7,t.valormes8,t.valormes9,t.valormes10,t.valormes11,t.valormes12, t.meta_total_programada, t.meta_esta_vigencia, t.acumulado_vigencias, t.acumulado_esta_vigencia, t.logro_total, t.logro_acumulado_total,t.estado,m11.descripcion as nombre_estado,t.producto_PMR,m13.descripcion as nombre_producto_PMR,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from INDICADORES t  left join POA_PROCESOS r1 on (r1.CODIGO_PROCESO=t.proceso) left join POA_CICLOS r2 on (r2.CODIGO_CICLO=t.ciclo) left join UNIDADES_DEPENDENCIA r3 on (r3.CODIGO=t.area) left join POA_OBJETIVOS_ESTRATEGICOS r4 on (r4.CODIGO_OBJETIVO=t.objetivo_estrategico) left join sis_multivalores m5 on (m5.tabla='TIPO_INDICADOR' and m5.VALOR=t.tipo_indicador) left join POA_PROYECTOS_INVERSION r6 on (r6.CODIGO_PROYECTO_INVERSION=t.proyecto_inversion) left join POA_METAS_PLAN_DESARROLLO r7 on (r7.CODIGO_META_PLAN=t.meta_plan_de_desarrollo) left join POA_METAS_PROYECTO r8 on (r8.CODIGO_META_PROYECTO=t.meta_proyecto) left join sis_unidades_medida m9 on (m9.codigo_unidad=t.unidad_medida) left join sis_multivalores m10 on (m10.tabla='FRECUENTA_MEDICION_INDICADORES' and m10.VALOR=t.frecuencia_medicion) left join sis_multivalores m11 on (m11.tabla='ESTADO_REGISTRO' and m11.valor=t.estado) left join sis_multivalores m12 on (m12.tabla='PROY_PRIORIDAD' and m12.valor=t.prioridad_en_producto) left join sis_multivalores m13 on (m13.tabla='PRODUCTO__PMR_INDICADORES' and m13.valor=t.producto_PMR) left join sis_multivalores m14 on (m14.tabla='INDICADOR_ACUERDO_INDICADORES' and m14.valor=t.indicador_acuerdo) where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 249 */       s = s + " order by 1";
/* 250 */       boolean rtaDB = this.dat.parseSql(s);
/* 251 */       if (!rtaDB) {
/* 252 */         return resultados;
/*     */       }
/* 254 */       this.rs = this.dat.getResultSet();
/* 255 */       while (this.rs.next()) {
/* 256 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 259 */     catch (Exception e) {
/* 260 */       e.printStackTrace();
/* 261 */       Utilidades.writeError("IndicadoresDAO:cargarTodos ", e);
/*     */     } 
/* 263 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadoresDTO cargarRegistro(int codigoIndicador, String mes) {
/*     */     try {
/* 274 */       String s = "select t.codigo_indicador,t.nombre_indicador,t.proceso,r1.DESCRIPCION as nombre_proceso,t.ciclo,r2.DESCRIPCION as nombre_ciclo,t.area,r3.DESCRIPCION as nombre_area,t.objetivo_estrategico,r4.DESCRIPCION as nombre_objetivo_estrategico,t.indicador_acuerdo,m14.DESCRIPCION as nombre_indicador_acuerdo,t.tipo_indicador,m5.DESCRIPCION as nombre_tipo_indicador,t.proyecto_inversion,r6.DESCRIPCION as nombre_proyecto_inversion,t.meta_plan_de_desarrollo,r7.DESCRIPCION as nombre_meta_plan_de_desarrollo,t.meta_proyecto,r8.DESCRIPCION as nombre_meta_proyecto,t.objetivo_indicador,t.prioridad_en_producto,m12.descripcion as nombre_prioridad_en_producto,t.unidad_medida,m9.nombre_unidad as nombre_unidad_medida,t.frecuencia_medicion,m10.DESCRIPCION as nombre_frecuencia_medicion,t.formula,t.mes1,t.mes2,t.mes3,t.mes4,t.mes5,t.mes6,t.mes7,t.mes8,t.mes9,t.mes10,t.mes11,t.mes12,t.valormes1,t.valormes2,t.valormes3,t.valormes4,t.valormes5,t.valormes6,t.valormes7,t.valormes8,t.valormes9,t.valormes10,t.valormes11,t.valormes12,t.estado,m11.descripcion as nombre_estado,t.producto_PMR,m13.descripcion as nombre_producto_PMR,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion, t.meta_total_programada, t.meta_esta_vigencia, t.acumulado_vigencias, t.acumulado_esta_vigencia, t.logro_total, t.logro_acumulado_total,t.usuario_modificacion from INDICADORES t  left join POA_PROCESOS r1 on (r1.CODIGO_PROCESO=t.proceso) left join POA_CICLOS r2 on (r2.CODIGO_CICLO=t.ciclo) left join UNIDADES_DEPENDENCIA r3 on (r3.CODIGO=t.area) left join POA_OBJETIVOS_ESTRATEGICOS r4 on (r4.CODIGO_OBJETIVO=t.objetivo_estrategico) left join sis_multivalores m5 on (m5.tabla='TIPO_INDICADOR' and m5.VALOR=t.tipo_indicador) left join POA_PROYECTOS_INVERSION r6 on (r6.CODIGO_PROYECTO_INVERSION=t.proyecto_inversion) left join POA_METAS_PLAN_DESARROLLO r7 on (r7.CODIGO_META_PLAN=t.meta_plan_de_desarrollo) left join POA_METAS_PROYECTO r8 on (r8.CODIGO_META_PROYECTO=t.meta_proyecto) left join sis_unidades_medida m9 on (m9.codigo_unidad=t.unidad_medida) left join sis_multivalores m10 on (m10.tabla='FRECUENTA_MEDICION_INDICADORES' and m10.VALOR=t.frecuencia_medicion) left join sis_multivalores m11 on (m11.tabla='ESTADO_REGISTRO' and m11.valor=t.estado) left join sis_multivalores m12 on (m12.tabla='PROY_PRIORIDAD' and m12.valor=t.prioridad_en_producto) left join sis_multivalores m13 on (m13.tabla='PRODUCTO__PMR_INDICADORES' and m13.valor=t.producto_PMR) left join sis_multivalores m14 on (m14.tabla='INDICADOR_ACUERDO_INDICADORES' and m14.valor=t.indicador_acuerdo) where  t.codigo_indicador='" + codigoIndicador + "'";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 359 */       if (mes == "Enero") {
/* 360 */         s = s + " and t.mes1='S'";
/*     */       }
/* 362 */       if (mes == "Febrero") {
/* 363 */         s = s + " and t.mes2='S'";
/*     */       }
/* 365 */       if (mes == "Marzo") {
/* 366 */         s = s + " and t.mes3='S'";
/*     */       }
/* 368 */       if (mes == "Abril") {
/* 369 */         s = s + " and t.mes4='S'";
/*     */       }
/* 371 */       if (mes == "Mayo") {
/* 372 */         s = s + " and t.mes5='S'";
/*     */       }
/* 374 */       if (mes == "Junio") {
/* 375 */         s = s + " and t.mes6='S'";
/*     */       }
/* 377 */       if (mes == "Julio") {
/* 378 */         s = s + " and t.mes7='S'";
/*     */       }
/* 380 */       if (mes == "Agosto") {
/* 381 */         s = s + " and t.mes8='S'";
/*     */       }
/* 383 */       if (mes == "Septiembre") {
/* 384 */         s = s + " and t.mes9='S'";
/*     */       }
/* 386 */       if (mes == "Octubre") {
/* 387 */         s = s + " and t.mes10='S'";
/*     */       }
/* 389 */       if (mes == "Noviembre") {
/* 390 */         s = s + " and t.mes11='S'";
/*     */       }
/* 392 */       if (mes == "Diciembre") {
/* 393 */         s = s + " and t.mes12='S'";
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 398 */       boolean rtaDB = this.dat.parseSql(s);
/* 399 */       if (!rtaDB) {
/* 400 */         return null;
/*     */       }
/* 402 */       this.rs = this.dat.getResultSet();
/* 403 */       if (this.rs.next()) {
/* 404 */         return leerRegistro();
/*     */       }
/*     */     }
/* 407 */     catch (Exception e) {
/* 408 */       e.printStackTrace();
/* 409 */       Utilidades.writeError("IndicadoresDAO:cargarIndicadores", e);
/*     */     } 
/* 411 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadoresDTO cargarRegistro(String codigoIndicador) {
/*     */     try {
/* 418 */       String s = "select t.codigo_indicador,t.nombre_indicador,t.proceso,r1.DESCRIPCION as nombre_proceso,t.ciclo,r2.DESCRIPCION as nombre_ciclo,t.area,r3.DESCRIPCION as nombre_area,t.objetivo_estrategico,r4.DESCRIPCION as nombre_objetivo_estrategico,t.indicador_acuerdo,m5.DESCRIPCION as nombre_indicador_acuerdo,t.tipo_indicador,m5.DESCRIPCION as nombre_tipo_indicador,t.proyecto_inversion,r6.DESCRIPCION as nombre_proyecto_inversion,t.meta_plan_de_desarrollo,r7.DESCRIPCION as nombre_meta_plan_de_desarrollo,t.meta_proyecto,r8.DESCRIPCION as nombre_meta_proyecto,t.objetivo_indicador,t.prioridad_en_producto,m12.descripcion as nombre_prioridad_en_producto,t.unidad_medida,m9.nombre_unidad as nombre_unidad_medida,t.frecuencia_medicion,m10.DESCRIPCION as nombre_frecuencia_medicion,t.formula,t.mes1,t.mes2,t.mes3,t.mes4,t.mes5,t.mes6,t.mes7,t.mes8,t.mes9,t.mes10,t.mes11,t.mes12,t.valormes1,t.valormes2,t.valormes3,t.valormes4,t.valormes5,t.valormes6,t.valormes7,t.valormes8,t.valormes9,t.valormes10,t.valormes11,t.valormes12,t.estado,m11.descripcion as nombre_estado,t.producto_PMR,m13.descripcion as nombre_producto_PMR,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion, t.meta_total_programada, t.meta_esta_vigencia, t.acumulado_vigencias, t.acumulado_esta_vigencia, t.logro_total, t.logro_acumulado_total,t.usuario_modificacion from INDICADORES t  left join POA_PROCESOS r1 on (r1.CODIGO_PROCESO=t.proceso) left join POA_CICLOS r2 on (r2.CODIGO_CICLO=t.ciclo) left join UNIDADES_DEPENDENCIA r3 on (r3.CODIGO=t.area) left join POA_OBJETIVOS_ESTRATEGICOS r4 on (r4.CODIGO_OBJETIVO=t.objetivo_estrategico) left join sis_multivalores m5 on (m5.tabla='TIPO_INDICADOR' and m5.VALOR=t.tipo_indicador) left join POA_PROYECTOS_INVERSION r6 on (r6.CODIGO_PROYECTO_INVERSION=t.proyecto_inversion) left join POA_METAS_PLAN_DESARROLLO r7 on (r7.CODIGO_META_PLAN=t.meta_plan_de_desarrollo) left join POA_METAS_PROYECTO r8 on (r8.CODIGO_META_PROYECTO=t.meta_proyecto) left join sis_unidades_medida m9 on (m9.codigo_unidad=t.unidad_medida) left join sis_multivalores m10 on (m10.tabla='FRECUENTA_MEDICION_INDICADORES' and m10.VALOR=t.frecuencia_medicion) left join sis_multivalores m11 on (m11.tabla='ESTADO_REGISTRO' and m11.valor=t.estado) left join sis_multivalores m12 on (m12.tabla='PROY_PRIORIDAD' and m12.valor=t.prioridad_en_producto) left join sis_multivalores m13 on (m13.tabla='PRODUCTO__PMR_INDICADORES' and m13.valor=t.producto_PMR) left join sis_multivalores m14 on (m14.tabla='INDICADOR_ACUERDO_INDICADORES' and m14.valor=t.indicador_acuerdo) where  t.codigo_indicador= '" + codigoIndicador + "'";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 508 */       boolean rtaDB = this.dat.parseSql(s);
/* 509 */       if (!rtaDB) {
/* 510 */         return null;
/*     */       }
/* 512 */       this.rs = this.dat.getResultSet();
/* 513 */       if (this.rs.next()) {
/* 514 */         return leerRegistro();
/*     */       }
/*     */     }
/* 517 */     catch (Exception e) {
/* 518 */       e.printStackTrace();
/* 519 */       Utilidades.writeError("IndicadoresDAO:cargarIndicadores", e);
/*     */     } 
/* 521 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(String codigoIndicador) {
/* 531 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 534 */       String s = "delete from INDICADORES where  codigo_indicador='" + codigoIndicador + "'" + "";
/*     */ 
/*     */ 
/*     */       
/* 538 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 540 */     catch (Exception e) {
/* 541 */       e.printStackTrace();
/* 542 */       Utilidades.writeError("IndicadoresDAO:eliminarRegistro ", e);
/* 543 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 545 */     return rta;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD crearRegistro(String codigoIndicador, String nombreIndicador, int proceso, int ciclo, int area, int objetivoEstrategico, String indicadorAcuerdo, String tipoIndicador, String proyectoInversion, int metaPlanDeDesarrollo, int metaProyecto, String objetivoIndicador, String prioridadEnProducto, String unidadMedida, String frecuenciaMedicion, String formula, String mes1, String mes2, String mes3, String mes4, String mes5, String mes6, String mes7, String mes8, String mes9, String mes10, String mes11, String mes12, int valormes1, int valormes2, int valormes3, int valormes4, int valormes5, int valormes6, int valormes7, int valormes8, int valormes9, int valormes10, int valormes11, int valormes12, String estado, String productoPMR, String usuarioInsercion, int metaTotalProgramada, int metaEstaVigencia, int acumuladoVigencias, int acumuladoEstaVigencia, int logroTotal, int logroTotalAcumulado) {
/* 604 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 607 */       String s = "insert into INDICADORES(codigo_indicador,nombre_indicador,proceso,ciclo,area,objetivo_estrategico,indicador_acuerdo,tipo_indicador,proyecto_inversion,meta_plan_de_desarrollo,meta_proyecto,objetivo_indicador,prioridad_en_producto,unidad_medida,frecuencia_medicion,formula,mes1,mes2,mes3,mes4,mes5,mes6,mes7,mes8,mes9,mes10,mes11,mes12,valormes1,valormes2,valormes3,valormes4,valormes5,valormes6,valormes7,valormes8,valormes9,valormes10,valormes11,valormes12,estado,meta_total_programada,meta_esta_vigencia,acumulado_vigencias,acumulado_esta_vigencia,logro_total,logro_acumulado_total,producto_PMR,fecha_insercion,usuario_insercion) values ('" + codigoIndicador + "'," + "'" + nombreIndicador + "'," + "" + proceso + "," + "" + ciclo + "," + "" + area + "," + "" + objetivoEstrategico + "," + "'" + indicadorAcuerdo + "'," + "'" + tipoIndicador + "'," + "'" + proyectoInversion + "'," + "" + metaPlanDeDesarrollo + "," + "" + metaProyecto + "," + "'" + objetivoIndicador + "'," + "'" + prioridadEnProducto + "'," + "'" + unidadMedida + "'," + "'" + frecuenciaMedicion + "'," + "'" + formula + "'," + "'" + mes1 + "'," + "'" + mes2 + "'," + "'" + mes3 + "'," + "'" + mes4 + "'," + "'" + mes5 + "'," + "'" + mes6 + "'," + "'" + mes7 + "'," + "'" + mes8 + "'," + "'" + mes9 + "'," + "'" + mes10 + "'," + "'" + mes11 + "'," + "'" + mes12 + "'," + "" + valormes1 + "," + "" + valormes2 + "," + "" + valormes3 + "," + "" + valormes4 + "," + "" + valormes5 + "," + "" + valormes6 + "," + "" + valormes7 + "," + "" + valormes8 + "," + "" + valormes9 + "," + "" + valormes10 + "," + "" + valormes11 + "," + "" + valormes12 + "," + "'" + estado + "'," + "" + metaTotalProgramada + "," + "" + metaEstaVigencia + "," + "" + acumuladoVigencias + "," + "" + acumuladoEstaVigencia + "," + "" + logroTotal + "," + "" + logroTotalAcumulado + "," + "'" + productoPMR + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 710 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 712 */     catch (Exception e) {
/* 713 */       e.printStackTrace();
/* 714 */       Utilidades.writeError("%IndicadoresDAO:crearRegistro ", e);
/* 715 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 717 */     return rta;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD modificarRegistro(String codigoIndicador, String nombreIndicador, int proceso, int ciclo, int area, int objetivoEstrategico, String indicadorAcuerdo, String tipoIndicador, String proyectoInversion, int metaPlanDeDesarrollo, int metaProyecto, String objetivoIndicador, String prioridadEnProducto, String unidadMedida, String frecuenciaMedicion, String formula, String mes1, String mes2, String mes3, String mes4, String mes5, String mes6, String mes7, String mes8, String mes9, String mes10, String mes11, String mes12, int valormes1, int valormes2, int valormes3, int valormes4, int valormes5, int valormes6, int valormes7, int valormes8, int valormes9, int valormes10, int valormes11, int valormes12, String estado, String productoPMR, String usuarioModificacion, int metaTotalProgramada, int metaEstaVigencia, int acumuladoVigencias, int acumuladoEstaVigencia, int logroTotal, int logroTotalAcumulado) {
/* 777 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 780 */       String s = "update INDICADORES set  nombre_indicador='" + nombreIndicador + "'," + " proceso=" + proceso + "," + " ciclo=" + ciclo + "," + " area=" + area + "," + " objetivo_estrategico=" + objetivoEstrategico + "," + " indicador_acuerdo='" + indicadorAcuerdo + "'," + " tipo_indicador='" + tipoIndicador + "'," + " proyecto_inversion='" + proyectoInversion + "'," + " meta_plan_de_desarrollo=" + metaPlanDeDesarrollo + "," + " meta_proyecto=" + metaProyecto + "," + " objetivo_indicador='" + objetivoIndicador + "'," + " prioridad_en_producto='" + prioridadEnProducto + "'," + " unidad_medida='" + unidadMedida + "'," + " frecuencia_medicion='" + frecuenciaMedicion + "'," + " formula='" + formula + "'," + " mes1='" + mes1 + "'," + " mes2='" + mes2 + "'," + " mes3='" + mes3 + "'," + " mes4='" + mes4 + "'," + " mes5='" + mes5 + "'," + " mes6='" + mes6 + "'," + " mes7='" + mes7 + "'," + " mes8='" + mes8 + "'," + " mes9='" + mes9 + "'," + " mes10='" + mes10 + "'," + " mes11='" + mes11 + "'," + " mes12='" + mes12 + "'," + " valormes1=" + valormes1 + "," + " valormes2=" + valormes2 + "," + " valormes3=" + valormes3 + "," + " valormes4=" + valormes4 + "," + " valormes5=" + valormes5 + "," + " valormes6=" + valormes6 + "," + " valormes7=" + valormes7 + "," + " valormes8=" + valormes8 + "," + " valormes9=" + valormes9 + "," + " valormes10=" + valormes10 + "," + " valormes11=" + valormes11 + "," + " valormes12=" + valormes12 + "," + " estado='" + estado + "'," + " meta_total_programada =" + metaTotalProgramada + "," + " meta_esta_vigencia=" + metaEstaVigencia + "," + " acumulado_vigencias=" + acumuladoVigencias + "," + " acumulado_esta_vigencia=" + acumuladoEstaVigencia + "," + " logro_total=" + logroTotal + "," + " logro_acumulado_total=" + logroTotalAcumulado + "," + " producto_PMR='" + productoPMR + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo_indicador='" + codigoIndicador + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 834 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 836 */     catch (Exception e) {
/* 837 */       e.printStackTrace();
/* 838 */       Utilidades.writeError("IndicadoresDAO:modificarRegistro ", e);
/* 839 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 841 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\IndicadoresDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */