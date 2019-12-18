/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.IndicadorCaracteristicaDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.IndicadorCaracteristicaDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IndicadorCaracteristicaDAO
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
/*  50 */       Utilidades.writeError("IndicadorCaracteristicaDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadorCaracteristicaDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("IndicadorCaracteristicaDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadorCaracteristicaDTO leerRegistro() {
/*     */     try {
/*  79 */       IndicadorCaracteristicaDTO reg = new IndicadorCaracteristicaDTO();
/*     */       
/*  81 */       reg.setIdRegistro(this.rs.getInt("id_registro"));
/*  82 */       reg.setCiclo(this.rs.getInt("ciclo"));
/*  83 */       reg.setIndicador(this.rs.getString("indicador"));
/*  84 */       reg.setMes(this.rs.getString("mes"));
/*  85 */       reg.setCaracteristica(this.rs.getInt("caracteristica"));
/*  86 */       reg.setValorProgramado(this.rs.getInt("valor_programado"));
/*  87 */       reg.setValorEjecutado(this.rs.getInt("valor_ejecutado"));
/*  88 */       reg.setEstado(this.rs.getString("estado"));
/*  89 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  90 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  91 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  92 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  93 */       reg.setNombreCiclo(this.rs.getString("nombre_ciclo"));
/*  94 */       reg.setNombreIndicador(this.rs.getString("nombre_indicador"));
/*  95 */       reg.setNombreEstado(this.rs.getString("nombre_estado"));
/*  96 */       return reg;
/*     */     }
/*  98 */     catch (Exception e) {
/*  99 */       e.printStackTrace();
/* 100 */       Utilidades.writeError("IndicadorCaracteristicaDAO:leerRegistro ", e);
/*     */       
/* 102 */       return null;
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
/*     */   public Collection<IndicadorCaracteristicaDTO> cargarTodos(int ciclo, String indicador, String mes) {
/* 114 */     Collection<IndicadorCaracteristicaDTO> resultados = new ArrayList<IndicadorCaracteristicaDTO>();
/*     */     try {
/* 116 */       String s = "select t.id_registro,t.ciclo,r1.DESCRIPCION as nombre_ciclo,t.indicador,r2.NOMBRE_INDICADOR as nombre_indicador,t.mes,t.caracteristica,t.valor_programado,t.valor_ejecutado,t.estado,m3.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from INDICADOR_CARACTERISTICA t  left join POA_CICLOS r1 on (r1.CODIGO_CICLO=t.ciclo) left join INDICADORES r2 on (r2.CODIGO_INDICADOR=t.indicador) left join sis_multivalores m3 on (m3.tabla='ESTADO_REGISTRO' and m3.VALOR=t.estado) where 1=1 and t.estado = 'A'";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 137 */       if (ciclo > 0) {
/* 138 */         s = s + " and t.ciclo=" + ciclo;
/*     */       }
/* 140 */       if (indicador.length() > 0) {
/* 141 */         s = s + " and upper(t.indicador) like upper('%" + indicador + "%')";
/*     */       }
/* 143 */       if (mes.length() > 0) {
/* 144 */         s = s + " and upper(t.mes) like upper('%" + mes + "%')";
/*     */       }
/* 146 */       s = s + " order by 1";
/* 147 */       boolean rtaDB = this.dat.parseSql(s);
/* 148 */       if (!rtaDB) {
/* 149 */         return resultados;
/*     */       }
/* 151 */       this.rs = this.dat.getResultSet();
/* 152 */       while (this.rs.next()) {
/* 153 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 156 */     catch (Exception e) {
/* 157 */       e.printStackTrace();
/* 158 */       Utilidades.writeError("IndicadorCaracteristicaDAO:cargarTodos ", e);
/*     */     } 
/* 160 */     return resultados;
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
/*     */   public Collection<IndicadorCaracteristicaDTO> cargarTodos(int ciclo, String indicador, String mes, int caracteristica) {
/* 175 */     Collection<IndicadorCaracteristicaDTO> resultados = new ArrayList<IndicadorCaracteristicaDTO>();
/*     */     try {
/* 177 */       String s = "select t.id_registro,t.ciclo,r1.DESCRIPCION as nombre_ciclo,t.indicador,r2.NOMBRE_INDICADOR as nombre_indicador,t.mes,t.caracteristica,t.valor_programado,t.valor_ejecutado,t.estado,m3.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from INDICADOR_CARACTERISTICA t  left join POA_CICLOS r1 on (r1.CODIGO_CICLO=t.ciclo) left join INDICADORES r2 on (r2.CODIGO_INDICADOR=t.indicador) left join sis_multivalores m3 on (m3.tabla='ESTADO_REGISTRO' and m3.VALOR=t.estado) where 1=1 and t.estado = 'A'";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 198 */       if (ciclo > 0) {
/* 199 */         s = s + " and t.ciclo=" + ciclo;
/*     */       }
/* 201 */       if (caracteristica > 0) {
/* 202 */         s = s + " and t.caracteristica=" + caracteristica;
/*     */       }
/* 204 */       if (indicador.length() > 0) {
/* 205 */         s = s + " and upper(t.indicador) like upper('%" + indicador + "%')";
/*     */       }
/* 207 */       if (mes.length() > 0) {
/* 208 */         s = s + " and upper(t.mes) like upper('%" + mes + "%')";
/*     */       }
/* 210 */       s = s + " order by 1";
/* 211 */       boolean rtaDB = this.dat.parseSql(s);
/* 212 */       if (!rtaDB) {
/* 213 */         return resultados;
/*     */       }
/* 215 */       this.rs = this.dat.getResultSet();
/* 216 */       while (this.rs.next()) {
/* 217 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 220 */     catch (Exception e) {
/* 221 */       e.printStackTrace();
/* 222 */       Utilidades.writeError("IndicadorCaracteristicaDAO:cargarTodos ", e);
/*     */     } 
/* 224 */     return resultados;
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
/*     */   public IndicadorCaracteristicaDTO cargarRegistro(int ciclo, String indicador, String mes, int caracteristica) {
/*     */     try {
/* 239 */       String s = "select t.id_registro,t.ciclo,r1.DESCRIPCION as nombre_ciclo,t.indicador,r2.NOMBRE_INDICADOR as nombre_indicador,t.mes,t.caracteristica,t.valor_programado,t.valor_ejecutado,t.estado,m3.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from INDICADOR_CARACTERISTICA t  left join POA_CICLOS r1 on (r1.CODIGO_CICLO=t.ciclo) left join INDICADORES r2 on (r2.CODIGO_INDICADOR=t.indicador) left join sis_multivalores m3 on (m3.tabla='ESTADO_REGISTRO' and m3.VALOR=t.estado) where  1=1 ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 262 */       if (ciclo > 0) {
/* 263 */         s = s + " and t.ciclo=" + ciclo;
/*     */       }
/* 265 */       if (caracteristica > 0) {
/* 266 */         s = s + " and t.caracteristica=" + caracteristica;
/*     */       }
/* 268 */       if (indicador.length() > 0) {
/* 269 */         s = s + " and upper(t.indicador) like upper('%" + indicador + "%')";
/*     */       }
/* 271 */       if (mes.length() > 0) {
/* 272 */         s = s + " and upper(t.mes) like upper('%" + mes + "%')";
/*     */       }
/* 274 */       boolean rtaDB = this.dat.parseSql(s);
/* 275 */       if (!rtaDB) {
/* 276 */         return null;
/*     */       }
/* 278 */       this.rs = this.dat.getResultSet();
/* 279 */       if (this.rs.next()) {
/* 280 */         return leerRegistro();
/*     */       }
/*     */     }
/* 283 */     catch (Exception e) {
/* 284 */       e.printStackTrace();
/* 285 */       Utilidades.writeError("IndicadorCaracteristicaDAO:cargarIndicadorCaracteristica", e);
/*     */     } 
/* 287 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadorCaracteristicaDTO cargarRegistro(int idRegistro) {
/*     */     try {
/* 298 */       String s = "select t.id_registro,t.ciclo,r1.DESCRIPCION as nombre_ciclo,t.indicador,r2.NOMBRE_INDICADOR as nombre_indicador,t.mes,t.caracteristica,t.valor_programado,t.valor_ejecutado,t.estado,m3.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from INDICADOR_CARACTERISTICA t  left join POA_CICLOS r1 on (r1.CODIGO_CICLO=t.ciclo) left join INDICADORES r2 on (r2.CODIGO_INDICADOR=t.indicador) left join sis_multivalores m3 on (m3.tabla='ESTADO_REGISTRO' and m3.VALOR=t.estado) where  t.id_registro=" + idRegistro + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 321 */       boolean rtaDB = this.dat.parseSql(s);
/* 322 */       if (!rtaDB) {
/* 323 */         return null;
/*     */       }
/* 325 */       this.rs = this.dat.getResultSet();
/* 326 */       if (this.rs.next()) {
/* 327 */         return leerRegistro();
/*     */       }
/*     */     }
/* 330 */     catch (Exception e) {
/* 331 */       e.printStackTrace();
/* 332 */       Utilidades.writeError("IndicadorCaracteristicaDAO:cargarIndicadorCaracteristica", e);
/*     */     } 
/* 334 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 343 */     int inumero = 1;
/* 344 */     String s = "select max(id_registro) from INDICADOR_CARACTERISTICA ";
/*     */     
/*     */     try {
/* 347 */       boolean rta = this.dat.parseSql(s);
/* 348 */       if (!rta) return 0; 
/* 349 */       this.rs = this.dat.getResultSet();
/* 350 */       if (this.rs.next()) {
/* 351 */         s = this.rs.getString(1);
/* 352 */         if (!this.rs.wasNull()) {
/* 353 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 356 */       return inumero;
/*     */     }
/* 358 */     catch (Exception e) {
/* 359 */       e.printStackTrace();
/* 360 */       Utilidades.writeError("IndicadorCaracteristicaDAO:siguienteRegistro ", e);
/*     */       
/* 362 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int idRegistro) {
/* 372 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 375 */       String s = "delete from INDICADOR_CARACTERISTICA where  id_registro=" + idRegistro + "";
/*     */ 
/*     */ 
/*     */       
/* 379 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 381 */     catch (Exception e) {
/* 382 */       e.printStackTrace();
/* 383 */       Utilidades.writeError("IndicadorCaracteristicaDAO:eliminarRegistro ", e);
/* 384 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 386 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int idRegistro, int ciclo, String indicador, String mes, int caracteristica, int valorProgramado, int valorEjecutado, String estado, String usuarioInsercion) {
/* 405 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 407 */     int elSiguiente = siguienteRegistro();
/* 408 */     if (elSiguiente == 0) {
/* 409 */       rta.setMensaje("Generando secuencia");
/* 410 */       return rta;
/*     */     } 
/*     */     
/*     */     try {
/* 414 */       String s = "insert into INDICADOR_CARACTERISTICA(id_registro,ciclo,indicador,mes,caracteristica,valor_programado,valor_ejecutado,estado,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "" + ciclo + "," + "'" + indicador + "'," + "'" + mes + "'," + "" + caracteristica + "," + "" + valorProgramado + "," + "" + valorEjecutado + "," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 437 */       rta = this.dat.executeUpdate2(s);
/* 438 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 440 */     catch (Exception e) {
/* 441 */       e.printStackTrace();
/* 442 */       Utilidades.writeError("%IndicadorCaracteristicaDAO:crearRegistro ", e);
/* 443 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 445 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int idRegistro, int ciclo, String indicador, String mes, int caracteristica, int valorProgramado, int valorEjecutado, String estado, String usuarioModificacion) {
/* 464 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 467 */       String s = "update INDICADOR_CARACTERISTICA set  ciclo=" + ciclo + "," + " indicador='" + indicador + "'," + " mes='" + mes + "'," + " caracteristica=" + caracteristica + "," + " valor_programado=" + valorProgramado + "," + " valor_ejecutado=" + valorEjecutado + "," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " id_registro=" + idRegistro + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 480 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 482 */     catch (Exception e) {
/* 483 */       e.printStackTrace();
/* 484 */       Utilidades.writeError("IndicadorCaracteristicaDAO:modificarRegistro ", e);
/* 485 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 487 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int idRegistro, int valorEjecutado, String usuarioModificacion) {
/* 501 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 504 */       String s = "update INDICADOR_CARACTERISTICA set  valor_ejecutado=" + valorEjecutado + "," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " id_registro=" + idRegistro + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 511 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 513 */     catch (Exception e) {
/* 514 */       e.printStackTrace();
/* 515 */       Utilidades.writeError("IndicadorCaracteristicaDAO:modificarRegistro ", e);
/* 516 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 518 */     return rta;
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
/*     */   public RespuestaBD calcularFormula(int ciclo, String indicador) {
/* 531 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 534 */       String s = "call PROC_CALCULAR_FORMULA(" + ciclo + "," + indicador + ")" + "";
/*     */ 
/*     */       
/* 537 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 539 */     catch (Exception e) {
/* 540 */       e.printStackTrace();
/* 541 */       Utilidades.writeError("IndicadorCaracteristicaDAO:calcularFormula ", e);
/* 542 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 544 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\IndicadorCaracteristicaDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */