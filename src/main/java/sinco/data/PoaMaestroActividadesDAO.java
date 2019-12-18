/*      */ package sinco.data;
/*      */ 
/*      */ import java.sql.ResultSet;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import sinco.business.PoaMaestroActividadesDTO;
/*      */ import sinco.business.RespuestaBD;
/*      */ import sinco.business.Utilidades;
/*      */ import sinco.data.DBManager;
/*      */ import sinco.data.PoaMaestroActividadesDAO;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PoaMaestroActividadesDAO
/*      */ {
/*      */   ResultSet rs;
/*   29 */   DBManager dat = new DBManager();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   40 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close() {
/*      */     try {
/*   47 */       this.dat.close();
/*      */     }
/*   49 */     catch (Exception e) {
/*   50 */       Utilidades.writeError("PoaMaestroActividadesDAO:close ", e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PoaMaestroActividadesDTO next() {
/*      */     try {
/*   61 */       if (this.rs.next()) {
/*   62 */         return leerRegistro();
/*      */       }
/*      */     }
/*   65 */     catch (Exception e) {
/*   66 */       e.printStackTrace();
/*   67 */       Utilidades.writeError("PoaMaestroActividadesDAO:next ", e);
/*      */     } 
/*   69 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PoaMaestroActividadesDTO leerRegistro() {
/*      */     try {
/*   79 */       PoaMaestroActividadesDTO reg = new PoaMaestroActividadesDTO();
/*      */       
/*   81 */       reg.setCodigoPoa(this.rs.getInt("codigo_poa"));
/*   82 */       reg.setCodigoPoaActividad(this.rs.getInt("codigo_poa_actividad"));
/*   83 */       reg.setObjetivoEstrategico(this.rs.getInt("objetivo_estrategico"));
/*   84 */       reg.setActividad(this.rs.getInt("actividad"));
/*   85 */       reg.setTipoActividad(this.rs.getInt("tipo_actividad"));
/*   86 */       reg.setProyectoInversion(this.rs.getString("proyecto_inversion"));
/*   87 */       reg.setProductoProceso(this.rs.getInt("producto_proceso"));
/*   88 */       reg.setMetaPlanDeDesarrollo(this.rs.getInt("meta_plan_de_desarrollo"));
/*   89 */       reg.setObjetivoSubsistema(this.rs.getInt("objetivo_subsistema"));
/*   90 */       reg.setMetaProyecto(this.rs.getInt("meta_proyecto"));
/*   91 */       reg.setTipoRecurso(this.rs.getString("tipo_recurso"));
/*   92 */       reg.setPrioridadEnProducto(this.rs.getString("prioridad_en_producto"));
/*   93 */       reg.setPrioridadObjetivo(this.rs.getString("prioridad_objetivo"));
/*   94 */       reg.setMes1(this.rs.getString("mes1"));
/*   95 */       reg.setMes2(this.rs.getString("mes2"));
/*   96 */       reg.setMes3(this.rs.getString("mes3"));
/*   97 */       reg.setMes4(this.rs.getString("mes4"));
/*   98 */       reg.setMes5(this.rs.getString("mes5"));
/*   99 */       reg.setMes6(this.rs.getString("mes6"));
/*  100 */       reg.setMes7(this.rs.getString("mes7"));
/*  101 */       reg.setMes8(this.rs.getString("mes8"));
/*  102 */       reg.setMes9(this.rs.getString("mes9"));
/*  103 */       reg.setMes10(this.rs.getString("mes10"));
/*  104 */       reg.setMes11(this.rs.getString("mes11"));
/*  105 */       reg.setMes12(this.rs.getString("mes12"));
/*  106 */       reg.setValorMes1(this.rs.getInt("valor_mes1"));
/*  107 */       reg.setValorMes2(this.rs.getInt("valor_mes2"));
/*  108 */       reg.setValorMes3(this.rs.getInt("valor_mes3"));
/*  109 */       reg.setValorMes4(this.rs.getInt("valor_mes4"));
/*  110 */       reg.setValorMes5(this.rs.getInt("valor_mes5"));
/*  111 */       reg.setValorMes6(this.rs.getInt("valor_mes6"));
/*  112 */       reg.setValorMes7(this.rs.getInt("valor_mes7"));
/*  113 */       reg.setValorMes8(this.rs.getInt("valor_mes8"));
/*  114 */       reg.setValorMes9(this.rs.getInt("valor_mes9"));
/*  115 */       reg.setValorMes10(this.rs.getInt("valor_mes10"));
/*  116 */       reg.setValorMes11(this.rs.getInt("valor_mes11"));
/*  117 */       reg.setValorMes12(this.rs.getInt("valor_mes12"));
/*  118 */       reg.setEstado(this.rs.getString("estado"));
/*  119 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  120 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  121 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  122 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  123 */       reg.setNombreObjetivoEstrategico(this.rs.getString("nombre_objetivo_estrategico"));
/*  124 */       reg.setNombreActividad(this.rs.getString("nombre_actividad"));
/*  125 */       reg.setNombreProyectoInversion(this.rs.getString("nombre_proyecto_inversion"));
/*  126 */       reg.setNombreProductoProceso(this.rs.getString("nombre_producto_proceso"));
/*  127 */       reg.setNombreMetaPlanDeDesarrollo(this.rs.getString("nombre_meta_plan_de_desarrollo"));
/*  128 */       reg.setNombreObjetivoSubsistema(this.rs.getString("nombre_objetivo_subsistema"));
/*  129 */       reg.setNombreMetaProyecto(this.rs.getString("nombre_meta_proyecto"));
/*  130 */       reg.setNombreTipoRecurso(this.rs.getString("nombre_tipo_recurso"));
/*  131 */       reg.setNombrePrioridadEnProducto(this.rs.getString("nombre_prioridad_en_producto"));
/*  132 */       reg.setNombrePrioridadObjetivo(this.rs.getString("nombre_prioridad_objetivo"));
/*  133 */       reg.setNombreEstado(this.rs.getString("nombre_estado"));
/*  134 */       return reg;
/*      */     }
/*  136 */     catch (Exception e) {
/*  137 */       e.printStackTrace();
/*  138 */       Utilidades.writeError("PoaMaestroActividadesDAO:leerRegistro ", e);
/*      */       
/*  140 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<PoaMaestroActividadesDTO> cargarTodos(int codigoPoa, int actividad) {
/*  151 */     Collection<PoaMaestroActividadesDTO> resultados = new ArrayList<PoaMaestroActividadesDTO>();
/*      */     try {
/*  153 */       String s = "select t.codigo_poa,t.codigo_poa_actividad,t.objetivo_estrategico,r5.DESCRIPCION as nombre_objetivo_estrategico,t.actividad,r6.DESCRIPCION as nombre_actividad,t.tipo_actividad,t.proyecto_inversion,r7.DESCRIPCION as nombre_proyecto_inversion,t.producto_proceso,r8.DESCRIPCION as nombre_producto_proceso,t.meta_plan_de_desarrollo,r9.DESCRIPCION as nombre_meta_plan_de_desarrollo,t.objetivo_subsistema,r10.DESCRIPCION as nombre_objetivo_subsistema,t.meta_proyecto,r11.DESCRIPCION as nombre_meta_proyecto,t.tipo_recurso,m12.DESCRIPCION as nombre_tipo_recurso,t.prioridad_en_producto,m13.DESCRIPCION as nombre_prioridad_en_producto,t.prioridad_objetivo,m14.DESCRIPCION as nombre_prioridad_objetivo,t.mes1,t.mes2,t.mes3,t.mes4,t.mes5,t.mes6,t.mes7,t.mes8,t.mes9,t.mes10,t.mes11,t.mes12,t.valor_mes1,t.valor_mes2,t.valor_mes3,t.valor_mes4,t.valor_mes5,t.valor_mes6,t.valor_mes7,t.valor_mes8,t.valor_mes9,t.valor_mes10,t.valor_mes11,t.valor_mes12,t.estado,m15.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_MAESTRO_ACTIVIDADES t  left join POA_OBJETIVOS_ESTRATEGICOS r5 on (r5.CODIGO_OBJETIVO=t.objetivo_estrategico) left join POA_ACTIVIDADES r6 on (r6.CODIGO_ACTIVIDAD=t.actividad) left join POA_PROYECTOS_INVERSION r7 on (r7.CODIGO_PROYECTO_INVERSION=t.proyecto_inversion) left join POA_PRODUCTOS_PROCESO r8 on (r8.CODIGO_PRODUCTO_PROCESO=t.producto_proceso) left join POA_METAS_PLAN_DESARROLLO r9 on (r9.CODIGO_META_PLAN=t.meta_plan_de_desarrollo) left join POA_OBJETIVOS_SUBSISTEMA_SIG r10 on (r10.CODIGO_OBJETIVO_SUBSISTEMA=t.objetivo_subsistema) left join POA_METAS_PROYECTO r11 on (r11.CODIGO_META_PROYECTO=t.meta_proyecto) left join sis_multivalores m12 on (m12.tabla='POA_TIPOS_RECURSO' and m12.VALOR=t.tipo_recurso) left join sis_multivalores m13 on (m13.tabla='POA_PRIORIDADES_PRODUCTO' and m13.VALOR=t.prioridad_en_producto) left join sis_multivalores m14 on (m14.tabla='POA_PRIORIDADES_OBJETIVO' and m14.VALOR=t.prioridad_objetivo) left join sis_multivalores m15 on (m15.tabla='ESTADO_REGISTRO' and m15.VALOR=t.estado) where 1=1";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  220 */       if (codigoPoa > 0) {
/*  221 */         s = s + " and t.codigo_poa=" + codigoPoa;
/*      */       }
/*  223 */       if (actividad > 0) {
/*  224 */         s = s + " and t.actividad=" + actividad;
/*      */       }
/*  226 */       s = s + " order by 1";
/*  227 */       boolean rtaDB = this.dat.parseSql(s);
/*  228 */       if (!rtaDB) {
/*  229 */         return resultados;
/*      */       }
/*  231 */       this.rs = this.dat.getResultSet();
/*  232 */       while (this.rs.next()) {
/*  233 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  236 */     catch (Exception e) {
/*  237 */       e.printStackTrace();
/*  238 */       Utilidades.writeError("PoaMaestroActividadesDAO:cargarTodos ", e);
/*      */     } 
/*  240 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<PoaMaestroActividadesDTO> cargarTodos(int codigoPoa, int actividad, int objetivo) {
/*  255 */     Collection<PoaMaestroActividadesDTO> resultados = new ArrayList<PoaMaestroActividadesDTO>();
/*      */     try {
/*  257 */       String s = "select t.codigo_poa,t.codigo_poa_actividad,t.objetivo_estrategico,r5.DESCRIPCION as nombre_objetivo_estrategico,t.actividad,r6.DESCRIPCION as nombre_actividad,t.tipo_actividad,t.proyecto_inversion,r7.DESCRIPCION as nombre_proyecto_inversion,t.producto_proceso,r8.DESCRIPCION as nombre_producto_proceso,t.meta_plan_de_desarrollo,r9.DESCRIPCION as nombre_meta_plan_de_desarrollo,t.objetivo_subsistema,r10.DESCRIPCION as nombre_objetivo_subsistema,t.meta_proyecto,r11.DESCRIPCION as nombre_meta_proyecto,t.tipo_recurso,m12.DESCRIPCION as nombre_tipo_recurso,t.prioridad_en_producto,m13.DESCRIPCION as nombre_prioridad_en_producto,t.prioridad_objetivo,m14.DESCRIPCION as nombre_prioridad_objetivo,t.mes1,t.mes2,t.mes3,t.mes4,t.mes5,t.mes6,t.mes7,t.mes8,t.mes9,t.mes10,t.mes11,t.mes12,t.valor_mes1,t.valor_mes2,t.valor_mes3,t.valor_mes4,t.valor_mes5,t.valor_mes6,t.valor_mes7,t.valor_mes8,t.valor_mes9,t.valor_mes10,t.valor_mes11,t.valor_mes12,t.estado,m15.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_MAESTRO_ACTIVIDADES t  left join POA_OBJETIVOS_ESTRATEGICOS r5 on (r5.CODIGO_OBJETIVO=t.objetivo_estrategico) left join POA_ACTIVIDADES r6 on (r6.CODIGO_ACTIVIDAD=t.actividad) left join POA_PROYECTOS_INVERSION r7 on (r7.CODIGO_PROYECTO_INVERSION=t.proyecto_inversion) left join POA_PRODUCTOS_PROCESO r8 on (r8.CODIGO_PRODUCTO_PROCESO=t.producto_proceso) left join POA_METAS_PLAN_DESARROLLO r9 on (r9.CODIGO_META_PLAN=t.meta_plan_de_desarrollo) left join POA_OBJETIVOS_SUBSISTEMA_SIG r10 on (r10.CODIGO_OBJETIVO_SUBSISTEMA=t.objetivo_subsistema) left join POA_METAS_PROYECTO r11 on (r11.CODIGO_META_PROYECTO=t.meta_proyecto) left join sis_multivalores m12 on (m12.tabla='POA_TIPOS_RECURSO' and m12.VALOR=t.tipo_recurso) left join sis_multivalores m13 on (m13.tabla='POA_PRIORIDADES_PRODUCTO' and m13.VALOR=t.prioridad_en_producto) left join sis_multivalores m14 on (m14.tabla='POA_PRIORIDADES_OBJETIVO' and m14.VALOR=t.prioridad_objetivo) left join sis_multivalores m15 on (m15.tabla='ESTADO_REGISTRO' and m15.VALOR=t.estado) where 1=1";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  324 */       if (codigoPoa > 0) {
/*  325 */         s = s + " and t.codigo_poa=" + codigoPoa;
/*      */       }
/*  327 */       if (actividad > 0) {
/*  328 */         s = s + " and t.actividad=" + actividad;
/*      */       }
/*  330 */       if (objetivo > 0) {
/*  331 */         s = s + " and t.objetivo_estrategico=" + objetivo;
/*      */       }
/*  333 */       s = s + " order by 1";
/*  334 */       boolean rtaDB = this.dat.parseSql(s);
/*  335 */       if (!rtaDB) {
/*  336 */         return resultados;
/*      */       }
/*  338 */       this.rs = this.dat.getResultSet();
/*  339 */       while (this.rs.next()) {
/*  340 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  343 */     catch (Exception e) {
/*  344 */       e.printStackTrace();
/*  345 */       Utilidades.writeError("PoaMaestroActividadesDAO:cargarTodos ", e);
/*      */     } 
/*  347 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<PoaMaestroActividadesDTO> cargarTodos(int area, int ciclo, String mes) {
/*  353 */     Collection<PoaMaestroActividadesDTO> resultados = new ArrayList<PoaMaestroActividadesDTO>();
/*      */     try {
/*  355 */       String s = "select t.codigo_poa,p.area,p.ciclo,t.codigo_poa_actividad,t.objetivo_estrategico,r5.DESCRIPCION as nombre_objetivo_estrategico,t.actividad,r6.DESCRIPCION as nombre_actividad,t.tipo_actividad,t.proyecto_inversion,r7.DESCRIPCION as nombre_proyecto_inversion,t.producto_proceso,r8.DESCRIPCION as nombre_producto_proceso,t.meta_plan_de_desarrollo,r9.DESCRIPCION as nombre_meta_plan_de_desarrollo,t.objetivo_subsistema,r10.DESCRIPCION as nombre_objetivo_subsistema,t.meta_proyecto,r11.DESCRIPCION as nombre_meta_proyecto,t.tipo_recurso,m12.DESCRIPCION as nombre_tipo_recurso,t.prioridad_en_producto,m13.DESCRIPCION as nombre_prioridad_en_producto,t.prioridad_objetivo,m14.DESCRIPCION as nombre_prioridad_objetivo,t.mes1,t.mes2,t.mes3,t.mes4,t.mes5,t.mes6,t.mes7,t.mes8,t.mes9,t.mes10,t.mes11,t.mes12,t.valor_mes1,t.valor_mes2,t.valor_mes3,t.valor_mes4,t.valor_mes5,t.valor_mes6,t.valor_mes7,t.valor_mes8,t.valor_mes9,t.valor_mes10,t.valor_mes11,t.valor_mes12,t.estado,m15.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_MAESTRO p  left join POA_MAESTRO_ACTIVIDADES t on (t.codigo_poa=p.codigo_poa) left join POA_OBJETIVOS_ESTRATEGICOS r5 on (r5.CODIGO_OBJETIVO=t.objetivo_estrategico) left join POA_ACTIVIDADES r6 on (r6.CODIGO_ACTIVIDAD=t.actividad) left join POA_PROYECTOS_INVERSION r7 on (r7.CODIGO_PROYECTO_INVERSION=t.proyecto_inversion) left join POA_PRODUCTOS_PROCESO r8 on (r8.CODIGO_PRODUCTO_PROCESO=t.producto_proceso) left join POA_METAS_PLAN_DESARROLLO r9 on (r9.CODIGO_META_PLAN=t.meta_plan_de_desarrollo) left join POA_OBJETIVOS_SUBSISTEMA_SIG r10 on (r10.CODIGO_OBJETIVO_SUBSISTEMA=t.objetivo_subsistema) left join POA_METAS_PROYECTO r11 on (r11.CODIGO_META_PROYECTO=t.meta_proyecto) left join sis_multivalores m12 on (m12.tabla='POA_TIPOS_RECURSO' and m12.VALOR=t.tipo_recurso) left join sis_multivalores m13 on (m13.tabla='POA_PRIORIDADES_PRODUCTO' and m13.VALOR=t.prioridad_en_producto) left join sis_multivalores m14 on (m14.tabla='POA_PRIORIDADES_OBJETIVO' and m14.VALOR=t.prioridad_objetivo) left join sis_multivalores m15 on (m15.tabla='ESTADO_REGISTRO' and m15.VALOR=t.estado) where 1=1";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  426 */       if (area > 0) {
/*  427 */         s = s + " and p.area=" + area;
/*      */       }
/*  429 */       if (ciclo > 0) {
/*  430 */         s = s + " and p.ciclo=" + ciclo;
/*      */       }
/*  432 */       if (mes.length() > 0) {
/*  433 */         s = s + " and t." + mes + "='S'";
/*      */       }
/*  435 */       s = s + " order by 1";
/*  436 */       boolean rtaDB = this.dat.parseSql(s);
/*  437 */       if (!rtaDB) {
/*  438 */         return resultados;
/*      */       }
/*  440 */       this.rs = this.dat.getResultSet();
/*  441 */       while (this.rs.next()) {
/*  442 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  445 */     catch (Exception e) {
/*  446 */       e.printStackTrace();
/*  447 */       Utilidades.writeError("PoaMaestroActividadesDAO:cargarTodos ", e);
/*      */     } 
/*  449 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<PoaMaestroActividadesDTO> cargarTodos(int area, int ciclo, String mes, int proceso) {
/*  455 */     Collection<PoaMaestroActividadesDTO> resultados = new ArrayList<PoaMaestroActividadesDTO>();
/*      */     try {
/*  457 */       String s = "select t.codigo_poa,p.area,p.ciclo,t.codigo_poa_actividad,t.objetivo_estrategico,r5.DESCRIPCION as nombre_objetivo_estrategico,t.actividad,r6.DESCRIPCION as nombre_actividad,t.tipo_actividad,t.proyecto_inversion,r7.DESCRIPCION as nombre_proyecto_inversion,t.producto_proceso,r8.DESCRIPCION as nombre_producto_proceso,t.meta_plan_de_desarrollo,r9.DESCRIPCION as nombre_meta_plan_de_desarrollo,t.objetivo_subsistema,r10.DESCRIPCION as nombre_objetivo_subsistema,t.meta_proyecto,r11.DESCRIPCION as nombre_meta_proyecto,t.tipo_recurso,m12.DESCRIPCION as nombre_tipo_recurso,t.prioridad_en_producto,m13.DESCRIPCION as nombre_prioridad_en_producto,t.prioridad_objetivo,m14.DESCRIPCION as nombre_prioridad_objetivo,t.mes1,t.mes2,t.mes3,t.mes4,t.mes5,t.mes6,t.mes7,t.mes8,t.mes9,t.mes10,t.mes11,t.mes12,t.valor_mes1,t.valor_mes2,t.valor_mes3,t.valor_mes4,t.valor_mes5,t.valor_mes6,t.valor_mes7,t.valor_mes8,t.valor_mes9,t.valor_mes10,t.valor_mes11,t.valor_mes12,t.estado,m15.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_MAESTRO p  left join POA_MAESTRO_ACTIVIDADES t on (t.codigo_poa=p.codigo_poa) left join POA_OBJETIVOS_ESTRATEGICOS r5 on (r5.CODIGO_OBJETIVO=t.objetivo_estrategico) left join POA_ACTIVIDADES r6 on (r6.CODIGO_ACTIVIDAD=t.actividad) left join POA_PROYECTOS_INVERSION r7 on (r7.CODIGO_PROYECTO_INVERSION=t.proyecto_inversion) left join POA_PRODUCTOS_PROCESO r8 on (r8.CODIGO_PRODUCTO_PROCESO=t.producto_proceso) left join POA_METAS_PLAN_DESARROLLO r9 on (r9.CODIGO_META_PLAN=t.meta_plan_de_desarrollo) left join POA_OBJETIVOS_SUBSISTEMA_SIG r10 on (r10.CODIGO_OBJETIVO_SUBSISTEMA=t.objetivo_subsistema) left join POA_METAS_PROYECTO r11 on (r11.CODIGO_META_PROYECTO=t.meta_proyecto) left join sis_multivalores m12 on (m12.tabla='POA_TIPOS_RECURSO' and m12.VALOR=t.tipo_recurso) left join sis_multivalores m13 on (m13.tabla='POA_PRIORIDADES_PRODUCTO' and m13.VALOR=t.prioridad_en_producto) left join sis_multivalores m14 on (m14.tabla='POA_PRIORIDADES_OBJETIVO' and m14.VALOR=t.prioridad_objetivo) left join sis_multivalores m15 on (m15.tabla='ESTADO_REGISTRO' and m15.VALOR=t.estado) where 1=1";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  528 */       if (area > 0) {
/*  529 */         s = s + " and p.area=" + area;
/*      */       }
/*  531 */       if (ciclo > 0) {
/*  532 */         s = s + " and p.ciclo=" + ciclo;
/*      */       }
/*  534 */       if (mes.length() > 0) {
/*  535 */         s = s + " and t." + mes + "='S'";
/*      */       }
/*  537 */       if (proceso > 0) {
/*  538 */         s = s + " and t.codigo_poa in (select poa.CODIGO_POA from POA_MAESTRO poa where poa.proceso = " + proceso + " and poa.area =" + area + " )";
/*      */       }
/*      */       
/*  541 */       s = s + " order by 1";
/*  542 */       boolean rtaDB = this.dat.parseSql(s);
/*  543 */       if (!rtaDB) {
/*  544 */         return resultados;
/*      */       }
/*  546 */       this.rs = this.dat.getResultSet();
/*  547 */       while (this.rs.next()) {
/*  548 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  551 */     catch (Exception e) {
/*  552 */       e.printStackTrace();
/*  553 */       Utilidades.writeError("PoaMaestroActividadesDAO:cargarTodos ", e);
/*      */     } 
/*  555 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<PoaMaestroActividadesDTO> cargarTodos(int codigoPoa) {
/*  561 */     Collection<PoaMaestroActividadesDTO> resultados = new ArrayList<PoaMaestroActividadesDTO>();
/*      */     try {
/*  563 */       String s = "select t.codigo_poa,p.area,p.ciclo,t.codigo_poa_actividad,t.objetivo_estrategico,r5.DESCRIPCION as nombre_objetivo_estrategico,t.actividad,r6.DESCRIPCION as nombre_actividad,t.tipo_actividad,t.proyecto_inversion,r7.DESCRIPCION as nombre_proyecto_inversion,t.producto_proceso,r8.DESCRIPCION as nombre_producto_proceso,t.meta_plan_de_desarrollo,r9.DESCRIPCION as nombre_meta_plan_de_desarrollo,t.objetivo_subsistema,r10.DESCRIPCION as nombre_objetivo_subsistema,t.meta_proyecto,r11.DESCRIPCION as nombre_meta_proyecto,t.tipo_recurso,m12.DESCRIPCION as nombre_tipo_recurso,t.prioridad_en_producto,m13.DESCRIPCION as nombre_prioridad_en_producto,t.prioridad_objetivo,m14.DESCRIPCION as nombre_prioridad_objetivo,t.mes1,t.mes2,t.mes3,t.mes4,t.mes5,t.mes6,t.mes7,t.mes8,t.mes9,t.mes10,t.mes11,t.mes12,t.valor_mes1,t.valor_mes2,t.valor_mes3,t.valor_mes4,t.valor_mes5,t.valor_mes6,t.valor_mes7,t.valor_mes8,t.valor_mes9,t.valor_mes10,t.valor_mes11,t.valor_mes12,t.estado,m15.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_MAESTRO p  left join POA_MAESTRO_ACTIVIDADES t on (t.codigo_poa=p.codigo_poa) left join POA_OBJETIVOS_ESTRATEGICOS r5 on (r5.CODIGO_OBJETIVO=t.objetivo_estrategico) left join POA_ACTIVIDADES r6 on (r6.CODIGO_ACTIVIDAD=t.actividad) left join POA_PROYECTOS_INVERSION r7 on (r7.CODIGO_PROYECTO_INVERSION=t.proyecto_inversion) left join POA_PRODUCTOS_PROCESO r8 on (r8.CODIGO_PRODUCTO_PROCESO=t.producto_proceso) left join POA_METAS_PLAN_DESARROLLO r9 on (r9.CODIGO_META_PLAN=t.meta_plan_de_desarrollo) left join POA_OBJETIVOS_SUBSISTEMA_SIG r10 on (r10.CODIGO_OBJETIVO_SUBSISTEMA=t.objetivo_subsistema) left join POA_METAS_PROYECTO r11 on (r11.CODIGO_META_PROYECTO=t.meta_proyecto) left join sis_multivalores m12 on (m12.tabla='POA_TIPOS_RECURSO' and m12.VALOR=t.tipo_recurso) left join sis_multivalores m13 on (m13.tabla='POA_PRIORIDADES_PRODUCTO' and m13.VALOR=t.prioridad_en_producto) left join sis_multivalores m14 on (m14.tabla='POA_PRIORIDADES_OBJETIVO' and m14.VALOR=t.prioridad_objetivo) left join sis_multivalores m15 on (m15.tabla='ESTADO_REGISTRO' and m15.VALOR=t.estado) where 1=1";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  634 */       if (codigoPoa > 0) {
/*  635 */         s = s + " and p.codigo_poa=" + codigoPoa;
/*      */       }
/*  637 */       s = s + " order by 1";
/*  638 */       boolean rtaDB = this.dat.parseSql(s);
/*  639 */       if (!rtaDB) {
/*  640 */         return resultados;
/*      */       }
/*  642 */       this.rs = this.dat.getResultSet();
/*  643 */       while (this.rs.next()) {
/*  644 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  647 */     catch (Exception e) {
/*  648 */       e.printStackTrace();
/*  649 */       Utilidades.writeError("PoaMaestroActividadesDAO:cargarTodos ", e);
/*      */     } 
/*  651 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PoaMaestroActividadesDTO cargarRegistro(int codigoPoaActividad) {
/*      */     try {
/*  661 */       String s = "select t.codigo_poa,t.codigo_poa_actividad,t.objetivo_estrategico,r5.DESCRIPCION as nombre_objetivo_estrategico,t.actividad,r6.DESCRIPCION as nombre_actividad,t.tipo_actividad,t.proyecto_inversion,r7.DESCRIPCION as nombre_proyecto_inversion,t.producto_proceso,r8.DESCRIPCION as nombre_producto_proceso,t.meta_plan_de_desarrollo,r9.DESCRIPCION as nombre_meta_plan_de_desarrollo,t.objetivo_subsistema,r10.DESCRIPCION as nombre_objetivo_subsistema,t.meta_proyecto,r11.DESCRIPCION as nombre_meta_proyecto,t.tipo_recurso,m12.DESCRIPCION as nombre_tipo_recurso,t.prioridad_en_producto,m13.DESCRIPCION as nombre_prioridad_en_producto,t.prioridad_objetivo,m14.DESCRIPCION as nombre_prioridad_objetivo,t.mes1,t.mes2,t.mes3,t.mes4,t.mes5,t.mes6,t.mes7,t.mes8,t.mes9,t.mes10,t.mes11,t.mes12,t.valor_mes1,t.valor_mes2,t.valor_mes3,t.valor_mes4,t.valor_mes5,t.valor_mes6,t.valor_mes7,t.valor_mes8,t.valor_mes9,t.valor_mes10,t.valor_mes11,t.valor_mes12,t.estado,m15.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_MAESTRO_ACTIVIDADES t  left join POA_OBJETIVOS_ESTRATEGICOS r5 on (r5.CODIGO_OBJETIVO=t.objetivo_estrategico) left join POA_ACTIVIDADES r6 on (r6.CODIGO_ACTIVIDAD=t.actividad) left join POA_PROYECTOS_INVERSION r7 on (r7.CODIGO_PROYECTO_INVERSION=t.proyecto_inversion) left join POA_PRODUCTOS_PROCESO r8 on (r8.CODIGO_PRODUCTO_PROCESO=t.producto_proceso) left join POA_METAS_PLAN_DESARROLLO r9 on (r9.CODIGO_META_PLAN=t.meta_plan_de_desarrollo) left join POA_OBJETIVOS_SUBSISTEMA_SIG r10 on (r10.CODIGO_OBJETIVO_SUBSISTEMA=t.objetivo_subsistema) left join POA_METAS_PROYECTO r11 on (r11.CODIGO_META_PROYECTO=t.meta_proyecto) left join sis_multivalores m12 on (m12.tabla='POA_TIPOS_RECURSO' and m12.VALOR=t.tipo_recurso) left join sis_multivalores m13 on (m13.tabla='POA_PRIORIDADES_PRODUCTO' and m13.VALOR=t.prioridad_en_producto) left join sis_multivalores m14 on (m14.tabla='POA_PRIORIDADES_OBJETIVO' and m14.VALOR=t.prioridad_objetivo) left join sis_multivalores m15 on (m15.tabla='ESTADO_REGISTRO' and m15.VALOR=t.estado) where t.codigo_poa_actividad= " + codigoPoaActividad + "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  730 */       boolean rtaDB = this.dat.parseSql(s);
/*  731 */       if (!rtaDB) {
/*  732 */         return null;
/*      */       }
/*  734 */       this.rs = this.dat.getResultSet();
/*  735 */       if (this.rs.next()) {
/*  736 */         return leerRegistro();
/*      */       }
/*      */     }
/*  739 */     catch (Exception e) {
/*  740 */       e.printStackTrace();
/*  741 */       Utilidades.writeError("PoaMaestroActividadesDAO:cargarPoaMaestroActividades", e);
/*      */     } 
/*  743 */     return null;
/*      */   }
/*      */   
/*      */   public int siguienteRegistro() {
/*  747 */     int inumero = 1;
/*  748 */     String s = "select max(codigo_poa_actividad) from POA_MAESTRO_ACTIVIDADES ";
/*      */     
/*      */     try {
/*  751 */       boolean rta = this.dat.parseSql(s);
/*  752 */       if (!rta) return 0; 
/*  753 */       this.rs = this.dat.getResultSet();
/*  754 */       if (this.rs.next()) {
/*  755 */         s = this.rs.getString(1);
/*  756 */         if (!this.rs.wasNull()) {
/*  757 */           inumero = Integer.parseInt(s) + 1;
/*      */         }
/*      */       } 
/*  760 */       return inumero;
/*      */     }
/*  762 */     catch (Exception e) {
/*  763 */       e.printStackTrace();
/*  764 */       Utilidades.writeError("PoaMaestroActividadesDAO:siguienteRegistro ", e);
/*      */       
/*  766 */       return 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int contarActividades(int codigoPoa) {
/*      */     try {
/*  777 */       String s = "select count(*) as num_actividades from poa_maestro_actividades where codigo_poa = " + codigoPoa;
/*      */       
/*  779 */       boolean rtaDB = this.dat.parseSql(s);
/*  780 */       if (!rtaDB) {
/*  781 */         return 0;
/*      */       }
/*  783 */       this.rs = this.dat.getResultSet();
/*  784 */       if (this.rs.next()) {
/*  785 */         return this.rs.getInt("num_actividades");
/*      */       }
/*      */     }
/*  788 */     catch (Exception e) {
/*  789 */       e.printStackTrace();
/*  790 */       Utilidades.writeError("PoaMaestroActividadesDAO:cargarPoaMaestroActividades", e);
/*      */     } 
/*  792 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RespuestaBD eliminarRegistro(int codigoPoaActividad) {
/*  804 */     RespuestaBD rta = new RespuestaBD();
/*      */     
/*      */     try {
/*  807 */       String s = "delete from POA_MAESTRO_ACTIVIDADES where  codigo_poa_actividad=" + codigoPoaActividad + "";
/*      */ 
/*      */ 
/*      */       
/*  811 */       rta = this.dat.executeUpdate2(s);
/*      */     }
/*  813 */     catch (Exception e) {
/*  814 */       e.printStackTrace();
/*  815 */       Utilidades.writeError("PoaMaestroActividadesDAO:eliminarRegistro ", e);
/*  816 */       rta.setMensaje(e.getMessage());
/*      */     } 
/*  818 */     return rta;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RespuestaBD crearRegistro(int codigoPoa, int codigoPoaActividad, int objetivoEstrategico, int variablesMedicion, int actividad, int tipoActividad, String proyectoInversion, int productoProceso, int metaPlanDeDesarrollo, int objetivoSubsistema, int metaProyecto, String tipoRecurso, String prioridadEnProducto, String prioridadObjetivo, String mes1, String mes2, String mes3, String mes4, String mes5, String mes6, String mes7, String mes8, String mes9, String mes10, String mes11, String mes12, int valorMes1, int valorMes2, int valorMes3, int valorMes4, int valorMes5, int valorMes6, int valorMes7, int valorMes8, int valorMes9, int valorMes10, int valorMes11, int valorMes12, String estado, String usuarioInsercion) {
/*  868 */     RespuestaBD rta = new RespuestaBD();
/*      */     
/*  870 */     int elSiguiente = siguienteRegistro();
/*  871 */     if (elSiguiente == 0) {
/*  872 */       rta.setMensaje("Generando secuencia");
/*  873 */       return rta;
/*      */     } 
/*      */     
/*      */     try {
/*  877 */       variablesMedicion = 1;
/*  878 */       String s = "insert into POA_MAESTRO_ACTIVIDADES(codigo_poa,codigo_poa_actividad,objetivo_estrategico,variables_medicion,actividad,tipo_actividad,proyecto_inversion,producto_proceso,meta_plan_de_desarrollo,objetivo_subsistema,meta_proyecto,tipo_recurso,prioridad_en_producto,prioridad_objetivo,mes1,mes2,mes3,mes4,mes5,mes6,mes7,mes8,mes9,mes10,mes11,mes12,valor_mes1,valor_mes2,valor_mes3,valor_mes4,valor_mes5,valor_mes6,valor_mes7,valor_mes8,valor_mes9,valor_mes10,valor_mes11,valor_mes12,estado,fecha_insercion,usuario_insercion) values (" + codigoPoa + "," + "" + elSiguiente + "," + "" + objetivoEstrategico + "," + "" + variablesMedicion + "," + "" + actividad + "," + "" + tipoActividad + "," + "'" + proyectoInversion + "'," + "" + productoProceso + "," + "" + metaPlanDeDesarrollo + "," + "" + objetivoSubsistema + "," + "" + metaProyecto + "," + "'" + tipoRecurso + "'," + "'" + prioridadEnProducto + "'," + "'" + prioridadObjetivo + "'," + "'" + mes1 + "'," + "'" + mes2 + "'," + "'" + mes3 + "'," + "'" + mes4 + "'," + "'" + mes5 + "'," + "'" + mes6 + "'," + "'" + mes7 + "'," + "'" + mes8 + "'," + "'" + mes9 + "'," + "'" + mes10 + "'," + "'" + mes11 + "'," + "'" + mes12 + "'," + "" + valorMes1 + "," + "" + valorMes2 + "," + "" + valorMes3 + "," + "" + valorMes4 + "," + "" + valorMes5 + "," + "" + valorMes6 + "," + "" + valorMes7 + "," + "" + valorMes8 + "," + "" + valorMes9 + "," + "" + valorMes10 + "," + "" + valorMes11 + "," + "" + valorMes12 + "," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  963 */       rta = this.dat.executeUpdate2(s);
/*  964 */       rta.setSecuencia(elSiguiente);
/*      */     }
/*  966 */     catch (Exception e) {
/*  967 */       e.printStackTrace();
/*  968 */       Utilidades.writeError("%PoaMaestroActividadesDAO:crearRegistro ", e);
/*  969 */       rta.setMensaje(e.getMessage());
/*      */     } 
/*  971 */     return rta;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RespuestaBD modificarRegistro(int codigoPoa, int codigoPoaActividad, int objetivoEstrategico, int actividad, int tipoActividad, String variablesMedicion, int productoProceso, int metaPlanDeDesarrollo, int objetivoSubsistema, int metaProyecto, String tipoRecurso, String prioridadEnProducto, String prioridadObjetivo, String mes1, String mes2, String mes3, String mes4, String mes5, String mes6, String mes7, String mes8, String mes9, String mes10, String mes11, String mes12, int valorMes1, int valorMes2, int valorMes3, int valorMes4, int valorMes5, int valorMes6, int valorMes7, int valorMes8, int valorMes9, int valorMes10, int valorMes11, int valorMes12, String estado, String usuarioModificacion) {
/* 1020 */     RespuestaBD rta = new RespuestaBD();
/*      */     
/*      */     try {
/* 1023 */       String s = "update POA_MAESTRO_ACTIVIDADES set  codigo_poa=" + codigoPoa + "," + " objetivo_estrategico=" + objetivoEstrategico + "," + " actividad=" + actividad + "," + " tipo_actividad=" + tipoActividad + "," + " variables_medicion='" + variablesMedicion + "'," + " producto_proceso=" + productoProceso + "," + " meta_plan_de_desarrollo=" + metaPlanDeDesarrollo + "," + " objetivo_subsistema=" + objetivoSubsistema + "," + " meta_proyecto=" + metaProyecto + "," + " tipo_recurso='" + tipoRecurso + "'," + " prioridad_en_producto='" + prioridadEnProducto + "'," + " prioridad_objetivo='" + prioridadObjetivo + "'," + " mes1='" + mes1 + "'," + " mes2='" + mes2 + "'," + " mes3='" + mes3 + "'," + " mes4='" + mes4 + "'," + " mes5='" + mes5 + "'," + " mes6='" + mes6 + "'," + " mes7='" + mes7 + "'," + " mes8='" + mes8 + "'," + " mes9='" + mes9 + "'," + " mes10='" + mes10 + "'," + " mes11='" + mes11 + "'," + " mes12='" + mes12 + "'," + " valor_mes1=" + valorMes1 + "," + " valor_mes2=" + valorMes2 + "," + " valor_mes3=" + valorMes3 + "," + " valor_mes4=" + valorMes4 + "," + " valor_mes5=" + valorMes5 + "," + " valor_mes6=" + valorMes6 + "," + " valor_mes7=" + valorMes7 + "," + " valor_mes8=" + valorMes8 + "," + " valor_mes9=" + valorMes9 + "," + " valor_mes10=" + valorMes10 + "," + " valor_mes11=" + valorMes11 + "," + " valor_mes12=" + valorMes12 + "," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo_poa_actividad=" + codigoPoaActividad + "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1066 */       rta = this.dat.executeUpdate2(s);
/*      */     }
/* 1068 */     catch (Exception e) {
/* 1069 */       e.printStackTrace();
/* 1070 */       Utilidades.writeError("PoaMaestroActividadesDAO:modificarRegistro ", e);
/* 1071 */       rta.setMensaje(e.getMessage());
/*      */     } 
/* 1073 */     return rta;
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PoaMaestroActividadesDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */