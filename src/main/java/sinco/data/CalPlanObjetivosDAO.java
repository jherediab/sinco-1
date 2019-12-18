/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Vector;
/*     */ import sinco.business.CalObjetivosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CalPlanObjetivosDAO;
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
/*     */ public class CalPlanObjetivosDAO
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
/*  45 */       Utilidades.writeError("CalPlanObjetivosFactory:close", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalObjetivosDTO next() {
/*     */     try {
/*  56 */       if (this.rs.next()) {
/*  57 */         return leerRegistro();
/*     */       }
/*     */     }
/*  60 */     catch (Exception e) {
/*  61 */       e.printStackTrace();
/*  62 */       Utilidades.writeError("CalPlanObjetivosFactory:next ", e);
/*     */     } 
/*  64 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalObjetivosDTO leerRegistro() {
/*     */     try {
/*  74 */       CalObjetivosDTO reg = new CalObjetivosDTO();
/*  75 */       reg.setCodigoCiclo(this.rs.getInt("codigo_ciclo"));
/*  76 */       reg.setCodigoPlan(this.rs.getInt("codigo_plan"));
/*  77 */       reg.setCodigoObjetivo(this.rs.getInt("codigo_objetivo"));
/*  78 */       reg.setProceso(this.rs.getString("proceso"));
/*  79 */       reg.setSubProceso(this.rs.getString("subproceso"));
/*  80 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  81 */       reg.setTipoObjetivo(this.rs.getString("tipo_objetivo"));
/*  82 */       reg.setPerspectiva(this.rs.getInt("perspectiva"));
/*  83 */       reg.setJustificacion(this.rs.getString("justificacion"));
/*  84 */       reg.setEstado(this.rs.getString("estado"));
/*  85 */       reg.setAgregaValor(this.rs.getString("agrega_valor"));
/*  86 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  87 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  88 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  89 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*     */       
/*  91 */       reg.setNombreTipoObjetivo(this.rs.getString("nombre_tipo_objetivo"));
/*  92 */       reg.setNombreProceso(this.rs.getString("nombre_proceso"));
/*  93 */       reg.setNombreSubProceso(this.rs.getString("nombre_subproceso"));
/*     */       
/*  95 */       reg.setEstadoProceso(this.rs.getString("estado_proceso"));
/*  96 */       reg.setEstadoSubProceso(this.rs.getString("estado_subproceso"));
/*     */       
/*  98 */       return reg;
/*     */     }
/* 100 */     catch (Exception e) {
/* 101 */       e.printStackTrace();
/* 102 */       Utilidades.writeError("CalPlanObjetivosFactory:leerRegistro ", e);
/*     */       
/* 104 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarTodos(int ciclo, int plan, String proceso, String subProceso) {
/*     */     try {
/* 114 */       String s = "select po.*,case when tipo_objetivo in('E','M') THEN 'ESPECIFICO' ELSE 'GENERAL' END as nombre_tipo_objetivo,p.descripcion as nombre_proceso,subp.descripcion as nombre_subproceso,p.ESTADO estado_proceso,subp.ESTADO estado_subproceso from cal_plan_objetivos po,procesos p,subprocesos subp where po.proceso=p.codigo and po.proceso=subp.proceso and po.subproceso=subp.codigo and po.codigo_ciclo=" + ciclo + " and po.codigo_plan=" + plan + " and po.tipo_objetivo in('E')";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 127 */       if (proceso.length() > 0) {
/* 128 */         s = s + " and po.proceso ='" + proceso + "'";
/*     */       }
/* 130 */       if (subProceso.length() > 0) {
/* 131 */         s = s + " and po.subproceso ='" + subProceso + "'";
/*     */       }
/*     */       
/* 134 */       s = s + " order by p.descripcion,subp.descripcion,po.descripcion";
/*     */       
/* 136 */       boolean rtaDB = this.dat.parseSql(s);
/* 137 */       if (!rtaDB) {
/* 138 */         return false;
/*     */       }
/*     */       
/* 141 */       this.rs = this.dat.getResultSet();
/* 142 */       return true;
/*     */     }
/* 144 */     catch (Exception e) {
/* 145 */       e.printStackTrace();
/* 146 */       Utilidades.writeError("CalObjetivosFactory:cargarTodos ", e);
/*     */       
/* 148 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarExpecificosDelPlan(int ciclo, int codigoArea) {
/*     */     try {
/* 159 */       String s = "select po.*,case when tipo_objetivo in('E','M') THEN 'ESPECIFICO' ELSE 'GENERAL' END as nombre_tipo_objetivo,p.descripcion as nombre_proceso,subp.descripcion as nombre_subproceso,p.ESTADO estado_proceso,subp.ESTADO estado_subproceso from cal_planes,cal_plan_objetivos,procesos,subprocesos where cal_planes.ciclo=po.codigo_ciclo and cal_planes.codigo_plan=po.codigo_plan and po.proceso=p.codigo and po.proceso=subp.proceso and po.subproceso=subp.codigo and cal_planes.ciclo=" + (ciclo - 1) + " and cal_planes.codigo_area=" + codigoArea + " and po.tipo_objetivo in('E','M')" + " and p.estado='A'" + " and subp.estado='A'" + " order by p.descripcion,subp.descripcion,po.descripcion";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 176 */       boolean rtaDB = this.dat.parseSql(s);
/* 177 */       if (!rtaDB) {
/* 178 */         return false;
/*     */       }
/*     */       
/* 181 */       this.rs = this.dat.getResultSet();
/* 182 */       return true;
/*     */     }
/* 184 */     catch (Exception e) {
/* 185 */       e.printStackTrace();
/* 186 */       Utilidades.writeError("CalObjetivosFactory:cargarTodos ", e);
/*     */       
/* 188 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int contarMetas(int ciclo, int plan, int objetivo) {
/*     */     try {
/* 198 */       String s = "select count(0) as numero";
/* 199 */       s = s + " from cal_plan_metas";
/* 200 */       s = s + " where ";
/* 201 */       s = s + " codigo_ciclo =" + ciclo;
/* 202 */       s = s + " and codigo_plan =" + plan;
/* 203 */       s = s + " and codigo_objetivo=" + objetivo;
/* 204 */       boolean rtaDB = this.dat.parseSql(s);
/* 205 */       if (!rtaDB) {
/* 206 */         return 0;
/*     */       }
/*     */       
/* 209 */       this.rs = this.dat.getResultSet();
/* 210 */       if (this.rs.next()) {
/* 211 */         return this.rs.getInt("numero");
/*     */       }
/* 213 */       return 0;
/*     */     }
/* 215 */     catch (Exception e) {
/* 216 */       e.printStackTrace();
/* 217 */       Utilidades.writeError("CalObjetivosFactory:contarMetas ", e);
/*     */       
/* 219 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int contarDocumentos(int ciclo, int plan, int objetivo) {
/*     */     try {
/* 230 */       String s = "select count(0) as numero";
/* 231 */       s = s + " from cal_plan_documentos_objetivo";
/* 232 */       s = s + " where ";
/* 233 */       s = s + " codigo_ciclo =" + ciclo;
/* 234 */       s = s + " and codigo_plan =" + plan;
/* 235 */       s = s + " and codigo_objetivo=" + objetivo;
/* 236 */       boolean rtaDB = this.dat.parseSql(s);
/* 237 */       if (!rtaDB) {
/* 238 */         return 0;
/*     */       }
/*     */       
/* 241 */       this.rs = this.dat.getResultSet();
/* 242 */       if (this.rs.next()) {
/* 243 */         return this.rs.getInt("numero");
/*     */       }
/* 245 */       return 0;
/*     */     }
/* 247 */     catch (Exception e) {
/* 248 */       e.printStackTrace();
/* 249 */       Utilidades.writeError("CalObjetivosFactory:contarMetas ", e);
/*     */       
/* 251 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarPlan(int ciclo, int plan, String estado) {
/*     */     try {
/* 261 */       String s = "select po.*,case when tipo_objetivo in('E','M') THEN 'ESPECIFICO' ELSE 'GENERAL' END as nombre_tipo_objetivo,p.descripcion as nombre_proceso,subp.descripcion as nombre_subproceso,p.ESTADO estado_proceso,subp.ESTADO estado_subproceso from cal_plan_objetivos po,procesos p,subprocesos subp where po.proceso=p.codigo and po.proceso=subp.proceso and po.subproceso=subp.codigo and po.codigo_ciclo=" + ciclo + " and po.codigo_plan=" + plan;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 271 */       if (estado.equals("A")) {
/* 272 */         s = s + " and po.estado='A'";
/* 273 */         s = s + " and p.estado='A'";
/* 274 */         s = s + " and subp.estado='A'";
/*     */       } 
/* 276 */       s = s + " order by p.descripcion,subp.descripcion,po.descripcion";
/*     */       
/* 278 */       boolean rtaDB = this.dat.parseSql(s);
/* 279 */       if (!rtaDB) {
/* 280 */         return false;
/*     */       }
/*     */       
/* 283 */       this.rs = this.dat.getResultSet();
/* 284 */       return true;
/*     */     }
/* 286 */     catch (Exception e) {
/* 287 */       e.printStackTrace();
/* 288 */       Utilidades.writeError("CalPlanObjetivosFactory:cargarTodos ", e);
/*     */       
/* 290 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection cargarAreasDeObjetivo(int ciclo, int objetivo, String estado) {
/* 301 */     Collection resultados = new ArrayList();
/*     */     try {
/* 303 */       String s = "select Pl.Codigo_Plan, u.Codigo, u.Descripcion from   Cal_Plan_Objetivos   Po,        Cal_Planes           Pl,        Unidades_Dependencia u where  Po.Codigo_Ciclo = Pl.Ciclo        and Po.Codigo_Plan = Pl.Codigo_Plan        and Pl.Codigo_Area = u.Codigo        and Po.Codigo_Ciclo = " + ciclo + "        and Po.Codigo_Objetivo = " + objetivo + "        and Po.Estado = 'A'" + "      AND u.ESTADO='A'" + " order  by u.Descripcion";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 318 */       boolean rtaDB = this.dat.parseSql(s);
/* 319 */       if (!rtaDB) {
/* 320 */         return resultados;
/*     */       }
/* 322 */       this.rs = this.dat.getResultSet();
/* 323 */       while (this.rs.next()) {
/* 324 */         CalObjetivosDTO reg = new CalObjetivosDTO();
/* 325 */         reg.setCodigoPlan(this.rs.getInt("Codigo_Plan"));
/* 326 */         reg.setCodigoArea(this.rs.getInt("Codigo"));
/* 327 */         reg.setNombreArea(this.rs.getString("Descripcion"));
/*     */         
/* 329 */         resultados.add(reg);
/*     */       }
/*     */     
/* 332 */     } catch (Exception e) {
/* 333 */       e.printStackTrace();
/* 334 */       Utilidades.writeError("CalObjetivosDAO:cargarTodos ", e);
/*     */     } 
/* 336 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalObjetivosDTO cargarRegistro(int ciclo, int plan, int objetivo) {
/*     */     try {
/* 347 */       String s = "select po.*,case when tipo_objetivo in('E','M') THEN 'ESPECIFICO' ELSE 'GENERAL' END as nombre_tipo_objetivo,p.descripcion as nombre_proceso,subp.descripcion as nombre_subproceso,p.ESTADO estado_proceso,subp.ESTADO estado_subproceso from cal_plan_objetivos po,procesos p,subprocesos subp where po.proceso=p.codigo and po.proceso=subp.proceso and po.subproceso=subp.codigo and po.codigo_ciclo=" + ciclo + " and po.codigo_plan=" + plan + " and po.codigo_objetivo=" + objetivo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 359 */       boolean rtaDB = this.dat.parseSql(s);
/* 360 */       if (!rtaDB) {
/* 361 */         return null;
/*     */       }
/*     */       
/* 364 */       this.rs = this.dat.getResultSet();
/* 365 */       if (this.rs.next()) {
/* 366 */         return leerRegistro();
/*     */       }
/*     */     }
/* 369 */     catch (Exception e) {
/* 370 */       e.printStackTrace();
/* 371 */       Utilidades.writeError("CalPlanObjetivosFactory:cargarCalPlanObjetivos ", e);
/*     */     } 
/* 373 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro(int codigoCiclo, int codigoPlan) {
/* 382 */     int numero = 1;
/* 383 */     String s = "select max(codigo_objetivo) from cal_plan_objetivos  where codigo_ciclo=" + codigoCiclo;
/*     */     
/*     */     try {
/* 386 */       boolean rta = this.dat.parseSql(s);
/* 387 */       if (!rta) return 0; 
/* 388 */       this.rs = this.dat.getResultSet();
/* 389 */       if (this.rs.next()) {
/* 390 */         s = this.rs.getString(1);
/* 391 */         if (!this.rs.wasNull()) {
/* 392 */           numero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 395 */       if (numero < 10000) numero = 10000; 
/* 396 */       return numero;
/*     */     }
/* 398 */     catch (Exception e) {
/* 399 */       e.printStackTrace();
/* 400 */       Utilidades.writeError("CalPlanObjetivosFactory:siguienteRegistro", e);
/*     */       
/* 402 */       return 0;
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
/*     */   public boolean crearRegistro(int codigoCiclo, int codigoPlan, int codigoObjetivo, String proceso, String subproceso, String descripcion, String justificacion, String tipoObjetivo, int perspectiva, String estado, String agregaValor, String usuarioInsercion) {
/*     */     try {
/* 427 */       String s = "insert into cal_plan_objetivos(codigo_ciclo,codigo_plan,codigo_objetivo,proceso,subproceso,descripcion,justificacion,tipo_objetivo,perspectiva,estado,agrega_valor,fecha_insercion,usuario_insercion) values (" + codigoCiclo + "," + "" + codigoPlan + "," + "" + codigoObjetivo + "," + "'" + proceso + "'," + "'" + subproceso + "'," + "'" + descripcion + "'," + "'" + justificacion + "'," + "'" + tipoObjetivo + "'," + "" + perspectiva + "," + "'" + estado + "'," + "'" + agregaValor + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 456 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 459 */     catch (Exception e) {
/* 460 */       e.printStackTrace();
/* 461 */       Utilidades.writeError("%CalObjetivosDAO:crearRegistro ", e);
/*     */       
/* 463 */       return false;
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
/*     */   public boolean modificarRegistro(int codigoCiclo, int codigoPlan, int codigoObjetivo, String proceso, String subproceso, String descripcion, String justificacion, String tipoObjetivo, int perspectiva, String estado, String agregaValor, String usuarioModificacion) {
/*     */     try {
/* 486 */       String s = "update cal_plan_objetivos set  proceso='" + proceso + "'," + " subproceso='" + subproceso + "'," + " descripcion='" + descripcion + "'," + " justificacion='" + justificacion + "'," + " tipo_objetivo='" + tipoObjetivo + "'," + " perspectiva=" + perspectiva + "," + " estado='" + estado + "'," + " agrega_valor='" + agregaValor + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo_ciclo=" + codigoCiclo + " and codigo_plan=" + codigoPlan + " and codigo_objetivo=" + codigoObjetivo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 501 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 504 */     catch (Exception e) {
/* 505 */       e.printStackTrace();
/* 506 */       Utilidades.writeError("CalObjetivosDAO:modificarRegistro ", e);
/*     */       
/* 508 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteMeta(int codigoCiclo) {
/* 518 */     int numero = 1;
/* 519 */     String s = "select max(codigo_meta) from cal_plan_metas";
/* 520 */     s = s + "  where codigo_ciclo=" + codigoCiclo;
/*     */     
/*     */     try {
/* 523 */       boolean rta = this.dat.parseSql(s);
/* 524 */       if (!rta) return 0; 
/* 525 */       this.rs = this.dat.getResultSet();
/* 526 */       if (this.rs.next()) {
/* 527 */         s = this.rs.getString(1);
/* 528 */         if (!this.rs.wasNull()) {
/* 529 */           numero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 532 */       if (numero < 10000) numero = 10000; 
/* 533 */       return numero - 10000;
/*     */     }
/* 535 */     catch (Exception e) {
/* 536 */       e.printStackTrace();
/* 537 */       Utilidades.writeError("CalPlanMetasFactory:siguienteRegistro", e);
/*     */       
/* 539 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean importarMetas(int anteriorCiclo, int nuevoCiclo, int anteriorPlan, int nuevoPlan, int objetivoAnterior, int nuevoObjetivo, String usuarioInsercion) {
/*     */     try {
/* 550 */       String s = " select codigo_meta from cal_plan_metas where  codigo_ciclo=" + anteriorCiclo + " and codigo_plan=" + anteriorPlan + " and codigo_objetivo=" + objetivoAnterior + " and descripcion not in(select descripcion from cal_plan_metas" + " where " + " codigo_ciclo=" + nuevoCiclo + " and codigo_plan=" + nuevoPlan + " and codigo_objetivo=" + nuevoObjetivo + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 563 */       boolean rta = this.dat.parseSql(s);
/* 564 */       if (!rta) return false; 
/* 565 */       this.rs = this.dat.getResultSet();
/*     */       
/* 567 */       Vector vVector = new Vector();
/* 568 */       while (this.rs.next()) {
/* 569 */         int codigoMeta = this.rs.getInt("codigo_meta");
/* 570 */         vVector.add(new Integer(codigoMeta));
/*     */       } 
/*     */       
/* 573 */       for (int i = 0; i < vVector.size(); i++) {
/* 574 */         int codigoMeta = ((Integer)vVector.elementAt(i)).intValue();
/* 575 */         int elSiguiente = siguienteMeta(nuevoCiclo);
/* 576 */         s = "insert into cal_plan_metas ( codigo_ciclo, codigo_plan, codigo_meta, codigo_objetivo, descripcion, justificacion, valor_meta, unidad_medida , valor_minimo , valor_maximo, tipo_medicion, frecuencia_medicion, estado, fecha_insercion, usuario_insercion) select " + nuevoCiclo + "," + nuevoPlan + "," + (codigoMeta + elSiguiente) + "," + nuevoObjetivo + ",descripcion," + " justificacion," + " valor_meta," + " unidad_medida ," + " valor_minimo ," + " valor_maximo," + " tipo_medicion," + " frecuencia_medicion," + " estado," + " fecha_insercion," + " usuario_insercion" + " from cal_plan_metas" + " where " + " codigo_ciclo=" + anteriorCiclo + " and codigo_plan=" + anteriorPlan + " and codigo_meta=" + codigoMeta;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 612 */         rta = this.dat.executeUpdate(s);
/*     */         
/* 614 */         if (rta) {
/*     */           
/* 616 */           s = "insert into cal_plan_recursos_meta (codigo_ciclo,codigo_plan,codigo_meta,codigo_recurso,estado,fecha_insercion,usuario_insercion,fecha_modificacion,usuario_modificacion) select " + nuevoCiclo + "," + nuevoPlan + "," + (codigoMeta + elSiguiente) + ",codigo_recurso," + "estado," + "fecha_insercion," + "usuario_insercion," + "fecha_modificacion," + "usuario_modificacion" + " from cal_plan_recursos_meta " + " where " + " codigo_ciclo=" + anteriorCiclo + " and codigo_plan=" + anteriorPlan + " and codigo_meta=" + codigoMeta;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 640 */           rta = this.dat.executeUpdate(s);
/*     */ 
/*     */           
/* 643 */           s = "insert into cal_plan_responsables_meta (codigo_ciclo,codigo_plan,codigo_meta,codigo_responsable,estado,fecha_insercion,usuario_insercion,fecha_modificacion,usuario_modificacion) select " + nuevoCiclo + "," + nuevoPlan + "," + (codigoMeta + elSiguiente) + ",codigo_responsable," + "estado," + "fecha_insercion," + "usuario_insercion," + "fecha_modificacion," + "usuario_modificacion" + " from cal_plan_responsables_meta " + " where " + " codigo_ciclo=" + anteriorCiclo + " and codigo_plan=" + anteriorPlan + " and codigo_meta=" + codigoMeta;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 667 */           rta = this.dat.executeUpdate(s);
/*     */         } 
/*     */       } 
/* 670 */       return true;
/*     */     }
/* 672 */     catch (Exception e) {
/* 673 */       e.printStackTrace();
/* 674 */       Utilidades.writeError("CalPlanMetasFactory:crearRegistro", e);
/*     */       
/* 676 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean importarRegistro(int codigoCiclo, int planAnterior, int codigoPlan, int codigoObjetivo, String usuarioInsercion) {
/* 686 */     int elSiguiente = siguienteRegistro(codigoCiclo, codigoPlan);
/* 687 */     if (elSiguiente == 0) {
/* 688 */       return false;
/*     */     }
/*     */     try {
/* 691 */       String s = "select codigo_objetivo  from cal_plan_objetivos where codigo_ciclo=" + codigoCiclo + " and codigo_plan=" + codigoPlan + " and descripcion in (select descripcion from cal_plan_objetivos" + " where codigo_ciclo=" + (codigoCiclo - 1) + " and codigo_plan=" + planAnterior + " AND codigo_objetivo=" + codigoObjetivo + " and tipo_objetivo in('E','M')" + ") and estado='A' " + " and tipo_objetivo in('E','M')";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 703 */       boolean rta = this.dat.parseSql(s);
/* 704 */       this.rs = this.dat.getResultSet();
/* 705 */       if (this.rs.next()) {
/* 706 */         elSiguiente = this.rs.getInt("codigo_objetivo");
/*     */       } else {
/*     */         
/* 709 */         s = "insert into cal_plan_objetivos (codigo_ciclo,codigo_plan,codigo_objetivo,proceso,subproceso,descripcion,tipo_objetivo,perspectiva,justificacion,estado,agrega_valor,fecha_insercion,usuario_insercion)";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 723 */         s = s + " select ";
/* 724 */         s = s + "" + codigoCiclo + ",";
/* 725 */         s = s + "" + codigoPlan + ",";
/* 726 */         s = s + "" + elSiguiente + ",";
/* 727 */         s = s + "proceso,";
/* 728 */         s = s + "subproceso,";
/* 729 */         s = s + "descripcion,";
/* 730 */         s = s + "tipo_objetivo,";
/* 731 */         s = s + "perspectiva,";
/* 732 */         s = s + "justificacion,";
/* 733 */         s = s + "estado,";
/* 734 */         s = s + "agrega_valor,";
/* 735 */         s = s + "" + Utilidades.getFechaBD() + ",";
/* 736 */         s = s + "'" + usuarioInsercion + "'";
/* 737 */         s = s + " from cal_plan_objetivos ";
/* 738 */         s = s + " where ";
/* 739 */         s = s + " codigo_ciclo=" + (codigoCiclo - 1);
/* 740 */         s = s + " and codigo_plan=" + planAnterior;
/* 741 */         s = s + " and codigo_objetivo=" + codigoObjetivo;
/* 742 */         rta = this.dat.executeUpdate(s);
/*     */       } 
/* 744 */       if (rta) {
/* 745 */         rta = importarMetas(codigoCiclo - 1, codigoCiclo, planAnterior, codigoPlan, codigoObjetivo, elSiguiente, usuarioInsercion);
/*     */         
/* 747 */         s = "insert into cal_plan_documentos_objetivo (codigo_ciclo,codigo_plan,codigo_objetivo,documento,fecha_insercion,usuario_insercion,fecha_modificacion,usuario_modificacion)select " + codigoCiclo + "," + codigoPlan + "," + elSiguiente + ",documento," + "fecha_insercion," + "usuario_insercion," + "fecha_modificacion," + "usuario_modificacion" + " from cal_plan_documentos_objetivo " + " where " + " codigo_ciclo=" + (codigoCiclo - 1) + " and codigo_plan=" + planAnterior + " and codigo_objetivo=" + codigoObjetivo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 769 */         rta = this.dat.executeUpdate(s);
/*     */       } 
/* 771 */       return true;
/*     */     }
/* 773 */     catch (Exception e) {
/* 774 */       e.printStackTrace();
/* 775 */       Utilidades.writeError("CalPlanObjetivosFactory:crearRegistro", e);
/*     */       
/* 777 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\CalPlanObjetivosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */