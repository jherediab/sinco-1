/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.CalMetasDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CalMetasDAO;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalMetasDAO
/*     */ {
/*     */   ResultSet rs;
/*  28 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  46 */       this.dat.close();
/*     */     }
/*  48 */     catch (Exception e) {
/*  49 */       Utilidades.writeError("CalMetasDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalMetasDTO next() {
/*     */     try {
/*  60 */       if (this.rs.next()) {
/*  61 */         return leerRegistro();
/*     */       }
/*     */     }
/*  64 */     catch (Exception e) {
/*  65 */       e.printStackTrace();
/*  66 */       Utilidades.writeError("CalMetasDAO:next ", e);
/*     */     } 
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalMetasDTO leerRegistro() {
/*     */     try {
/*  78 */       CalMetasDTO reg = new CalMetasDTO();
/*     */       
/*  80 */       reg.setCodigoMeta(this.rs.getInt("codigo_meta"));
/*  81 */       reg.setCodigoObjetivo(this.rs.getInt("codigo_objetivo"));
/*  82 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  83 */       reg.setJustificacion(this.rs.getString("justificacion"));
/*  84 */       reg.setValorMeta(this.rs.getDouble("valor_meta"));
/*  85 */       reg.setValorMinimo(this.rs.getDouble("valor_minimo"));
/*  86 */       reg.setValorMaximo(this.rs.getDouble("valor_maximo"));
/*  87 */       reg.setTipoMedicion(this.rs.getString("tipo_medicion"));
/*  88 */       reg.setEstado(this.rs.getString("estado"));
/*  89 */       reg.setFuenteDato(this.rs.getString("fuente_dato"));
/*  90 */       reg.setAplicaEn(this.rs.getString("aplica_en"));
/*  91 */       reg.setUnidadMedida(this.rs.getString("unidad_medida"));
/*  92 */       reg.setTipoGrafica(this.rs.getString("tipo_grafica"));
/*  93 */       reg.setMes01(this.rs.getString("mes01"));
/*  94 */       reg.setMes02(this.rs.getString("mes02"));
/*  95 */       reg.setMes03(this.rs.getString("mes03"));
/*  96 */       reg.setMes04(this.rs.getString("mes04"));
/*  97 */       reg.setMes05(this.rs.getString("mes05"));
/*  98 */       reg.setMes06(this.rs.getString("mes06"));
/*  99 */       reg.setMes07(this.rs.getString("mes07"));
/* 100 */       reg.setMes08(this.rs.getString("mes08"));
/* 101 */       reg.setMes09(this.rs.getString("mes09"));
/* 102 */       reg.setMes10(this.rs.getString("mes10"));
/* 103 */       reg.setMes11(this.rs.getString("mes11"));
/* 104 */       reg.setMes12(this.rs.getString("mes12"));
/* 105 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/* 106 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/* 107 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/* 108 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*     */       
/* 110 */       reg.setNombreTipoMedicion(this.rs.getString("nombreTipoMedicion"));
/* 111 */       reg.setNombreEstado(this.rs.getString("nombreEstado"));
/*     */       
/* 113 */       return reg;
/*     */     }
/* 115 */     catch (Exception e) {
/* 116 */       e.printStackTrace();
/* 117 */       Utilidades.writeError("CalMetasDAO:leerRegistro ", e);
/*     */       
/* 119 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<CalMetasDTO> cargarDeObjetivo(int objetivo) {
/* 128 */     Collection<CalMetasDTO> resultados = new ArrayList<CalMetasDTO>();
/*     */     try {
/* 130 */       String s = "select cal_metas.*,tm.descripcion as nombreTipoMedicion,est.descripcion as nombreEstado from cal_metas ,sis_multivalores tm,sis_multivalores est where  cal_metas.tipo_medicion =tm.valor and tm.tabla='CAL_TIPO_MEDICION' and cal_metas.estado  =est.valor and est.tabla='CAL_ESTADO_META' and cal_metas.codigo_objetivo=" + objetivo + "  order by cal_metas.descripcion";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 142 */       boolean rtaDB = this.dat.parseSql(s);
/* 143 */       if (!rtaDB) {
/* 144 */         return resultados;
/*     */       }
/* 146 */       this.rs = this.dat.getResultSet();
/* 147 */       while (this.rs.next()) {
/* 148 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 151 */     catch (Exception e) {
/* 152 */       e.printStackTrace();
/* 153 */       Utilidades.writeError("CalMetasDAO:cargarTodos ", e);
/*     */     } 
/* 155 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<CalMetasDTO> cargarDeObjetivoActivo(int objetivo) {
/* 164 */     Collection<CalMetasDTO> resultados = new ArrayList<CalMetasDTO>();
/*     */     try {
/* 166 */       String s = "select cal_metas.*,tm.descripcion as nombreTipoMedicion,est.descripcion as nombreEstado from cal_metas ,sis_multivalores tm,sis_multivalores est where  cal_metas.tipo_medicion =tm.valor and tm.tabla='CAL_TIPO_MEDICION' and cal_metas.estado  =est.valor and est.tabla='CAL_ESTADO_META' and cal_metas.estado='A' and cal_metas.codigo_objetivo=" + objetivo + "  order by cal_metas.descripcion";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 179 */       boolean rtaDB = this.dat.parseSql(s);
/* 180 */       if (!rtaDB) {
/* 181 */         return resultados;
/*     */       }
/* 183 */       this.rs = this.dat.getResultSet();
/* 184 */       while (this.rs.next()) {
/* 185 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 188 */     catch (Exception e) {
/* 189 */       e.printStackTrace();
/* 190 */       Utilidades.writeError("CalMetasDAO:cargarTodos ", e);
/*     */     } 
/* 192 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalMetasDTO cargarRegistro(int codigoMeta) {
/*     */     try {
/* 204 */       String s = "select cal_metas.*,tm.descripcion as nombreTipoMedicion,est.descripcion as nombreEstado from cal_metas ,sis_multivalores tm,sis_multivalores est where  cal_metas.tipo_medicion =tm.valor and tm.tabla='CAL_TIPO_MEDICION' and cal_metas.estado  =est.valor and est.tabla='CAL_ESTADO_META' and cal_metas.codigo_meta=" + codigoMeta;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 214 */       boolean rtaDB = this.dat.parseSql(s);
/* 215 */       if (!rtaDB) {
/* 216 */         return null;
/*     */       }
/* 218 */       this.rs = this.dat.getResultSet();
/* 219 */       if (this.rs.next()) {
/* 220 */         return leerRegistro();
/*     */       }
/*     */     }
/* 223 */     catch (Exception e) {
/* 224 */       e.printStackTrace();
/* 225 */       Utilidades.writeError("CalMetasDAO:cargarCalMetas", e);
/*     */     } 
/* 227 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 236 */     int inumero = 1;
/* 237 */     String s = "select max(codigo_meta) from cal_metas";
/*     */     try {
/* 239 */       boolean rta = this.dat.parseSql(s);
/* 240 */       if (!rta) return 0; 
/* 241 */       this.rs = this.dat.getResultSet();
/* 242 */       if (this.rs.next()) {
/* 243 */         s = this.rs.getString(1);
/* 244 */         if (!this.rs.wasNull()) {
/* 245 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 248 */       return inumero;
/*     */     }
/* 250 */     catch (Exception e) {
/* 251 */       e.printStackTrace();
/* 252 */       Utilidades.writeError("CalMetasDAO:siguienteRegistro ", e);
/*     */       
/* 254 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(int codigoMeta) {
/*     */     try {
/* 264 */       String s = "delete from cal_metas where  codigo_meta=" + codigoMeta + "";
/*     */ 
/*     */ 
/*     */       
/* 268 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 271 */     catch (Exception e) {
/* 272 */       e.printStackTrace();
/* 273 */       Utilidades.writeError("CalMetasDAO:eliminarRegistro ", e);
/*     */       
/* 275 */       return false;
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
/*     */   private boolean crearRecurso(int codigoMeta, int codigoRecurso, String estado, String usuarioInsercion) {
/*     */     try {
/* 293 */       String s = "select estado from  cal_recursos_meta  where codigo_meta=" + codigoMeta + "  and codigo_recurso=" + codigoRecurso;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 298 */       this.dat.parseSql(s);
/* 299 */       this.rs = this.dat.getResultSet();
/* 300 */       if (this.rs.next()) {
/* 301 */         s = "update cal_recursos_meta set estado='" + estado + "'," + " procesado='N'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioInsercion + "'" + " where codigo_meta=" + codigoMeta + " and codigo_recurso=" + codigoRecurso;
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 310 */         s = "insert into cal_recursos_meta (codigo_meta,codigo_recurso,estado,procesado,fecha_insercion,usuario_insercion) values (" + codigoMeta + "," + "" + codigoRecurso + "," + "'" + estado + "'," + "'N'," + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
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
/* 326 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 329 */     catch (Exception e) {
/* 330 */       e.printStackTrace();
/* 331 */       Utilidades.writeError("CalMetasDAO:crearRegistro", e);
/*     */       
/* 333 */       return false;
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
/*     */   private boolean crearResponsable(int codigoMeta, int codigoResponsable, String estado, String usuarioInsercion) {
/*     */     try {
/* 349 */       String s = "select estado from  cal_responsables_meta  where codigo_meta=" + codigoMeta + "  and codigo_responsable=" + codigoResponsable;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 354 */       this.dat.parseSql(s);
/* 355 */       this.rs = this.dat.getResultSet();
/* 356 */       if (this.rs.next()) {
/* 357 */         s = "update cal_responsables_meta set estado='" + estado + "'," + " procesado='N'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioInsercion + "'" + " where codigo_meta=" + codigoMeta + "      and codigo_responsable=" + codigoResponsable;
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 366 */         s = "insert into cal_responsables_meta (codigo_meta,codigo_responsable,estado,procesado,fecha_insercion,usuario_insercion) values (" + codigoMeta + "," + "" + codigoResponsable + "," + "'" + estado + "'," + "'N'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
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
/* 383 */       return this.dat.executeUpdate(s);
/*     */ 
/*     */     
/*     */     }
/* 387 */     catch (Exception e) {
/* 388 */       e.printStackTrace();
/* 389 */       Utilidades.writeError("CalResponsablesMetaFactory:crearRegistro", e);
/*     */       
/* 391 */       return false;
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
/*     */   public boolean crearRegistro(int codigoObjetivo, String descripcion, String justificacion, double valorMeta, double valorMinimo, double valorMaximo, String tipoMedicion, String estado, String fuenteDato, String aplicaEn, String unidadMedida, String tipoGrafica, String mes01, String mes02, String mes03, String mes04, String mes05, String mes06, String mes07, String mes08, String mes09, String mes10, String mes11, String mes12, String usuarioInsercion) {
/* 426 */     int elSiguiente = siguienteRegistro();
/* 427 */     if (elSiguiente == 0) {
/* 428 */       return false;
/*     */     }
/*     */     
/*     */     try {
/* 432 */       String s = "insert into cal_metas(codigo_meta,codigo_objetivo,descripcion,justificacion,valor_meta,valor_minimo,valor_maximo,tipo_medicion,estado,fuente_dato,aplica_en,unidad_medida,tipo_grafica,mes01,mes02,mes03,mes04,mes05,mes06,mes07,mes08,mes09,mes10,mes11,mes12,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "" + codigoObjetivo + "," + "'" + descripcion + "'," + "'" + justificacion + "'," + "" + valorMeta + "," + "" + valorMinimo + "," + "" + valorMaximo + "," + "'" + tipoMedicion + "'," + "'" + estado + "'," + "'" + fuenteDato + "'," + "'" + aplicaEn + "'," + "'" + unidadMedida + "'," + "'" + tipoGrafica + "'," + "'" + mes01 + "'," + "'" + mes02 + "'," + "'" + mes03 + "'," + "'" + mes04 + "'," + "'" + mes05 + "'," + "'" + mes06 + "'," + "'" + mes07 + "'," + "'" + mes08 + "'," + "'" + mes09 + "'," + "'" + mes10 + "'," + "'" + mes11 + "'," + "'" + mes12 + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 489 */       return this.dat.executeUpdate(s);
/*     */ 
/*     */     
/*     */     }
/* 493 */     catch (Exception e) {
/* 494 */       e.printStackTrace();
/* 495 */       Utilidades.writeError("%CalMetasDAO:crearRegistro ", e);
/*     */       
/* 497 */       return false;
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
/*     */   public boolean modificarRegistro(int codigoMeta, int codigoObjetivo, String descripcion, String justificacion, double valorMeta, double valorMinimo, double valorMaximo, String tipoMedicion, String estado, String fuenteDato, String aplicaEn, String unidadMedida, String tipoGrafica, String mes01, String mes02, String mes03, String mes04, String mes05, String mes06, String mes07, String mes08, String mes09, String mes10, String mes11, String mes12, String usuarioModificacion) {
/*     */     try {
/* 534 */       String s = "update cal_metas set  codigo_objetivo=" + codigoObjetivo + "," + " descripcion='" + descripcion + "'," + " justificacion='" + justificacion + "'," + " valor_meta=" + valorMeta + "," + " valor_minimo=" + valorMinimo + "," + " valor_maximo=" + valorMaximo + "," + " tipo_medicion='" + tipoMedicion + "'," + " estado='" + estado + "'," + " fuente_dato='" + fuenteDato + "'," + " aplica_en='" + aplicaEn + "'," + " unidad_medida='" + unidadMedida + "'," + " tipo_grafica='" + tipoGrafica + "'," + " mes01='" + mes01 + "'," + " mes02='" + mes02 + "'," + " mes03='" + mes03 + "'," + " mes04='" + mes04 + "'," + " mes05='" + mes05 + "'," + " mes06='" + mes06 + "'," + " mes07='" + mes07 + "'," + " mes08='" + mes08 + "'," + " mes09='" + mes09 + "'," + " mes10='" + mes10 + "'," + " mes11='" + mes11 + "'," + " mes12='" + mes12 + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo_meta=" + codigoMeta + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 564 */       return this.dat.executeUpdate(s);
/*     */ 
/*     */     
/*     */     }
/* 568 */     catch (Exception e) {
/* 569 */       Utilidades.writeError("CalMetasDAO:modificarRegistro ", e);
/* 570 */       e.printStackTrace();
/*     */       
/* 572 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\CalMetasDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */