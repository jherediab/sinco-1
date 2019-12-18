/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SisMultiValoresDAO
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
/*  50 */       Utilidades.writeError("SisMultiValoresDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisMultiValoresDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("SisMultiValoresDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisMultiValoresDTO leerRegistro() {
/*     */     try {
/*  79 */       SisMultiValoresDTO reg = new SisMultiValoresDTO();
/*     */       
/*  81 */       reg.setTabla(this.rs.getString("tabla"));
/*  82 */       reg.setValor(this.rs.getString("valor"));
/*  83 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  84 */       reg.setEstado(this.rs.getString("estado"));
/*  85 */       reg.setVarios(this.rs.getString("varios"));
/*  86 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  87 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  88 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  89 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  90 */       return reg;
/*     */     }
/*  92 */     catch (Exception e) {
/*  93 */       e.printStackTrace();
/*  94 */       Utilidades.writeError("SisMultiValoresDAO:leerRegistro ", e);
/*     */       
/*  96 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisMultiValoresDTO leerRegistro(String columna) {
/*     */     try {
/* 105 */       SisMultiValoresDTO reg = new SisMultiValoresDTO();
/* 106 */       if (columna.equals("tabla")) {
/* 107 */         reg.setTabla(this.rs.getString("tabla"));
/* 108 */       } else if (columna.equals("valor")) {
/* 109 */         reg.setValor(this.rs.getString("valor"));
/* 110 */       } else if (columna.equals("descripcion")) {
/* 111 */         reg.setDescripcion(this.rs.getString("descripcion"));
/* 112 */       } else if (columna.equals("estado")) {
/* 113 */         reg.setEstado(this.rs.getString("estado"));
/* 114 */       } else if (columna.equals("varios")) {
/* 115 */         reg.setVarios(this.rs.getString("varios"));
/* 116 */       } else if (columna.equals("fecha_insercion")) {
/* 117 */         reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/* 118 */       } else if (columna.equals("usuario_insercion")) {
/* 119 */         reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/* 120 */       } else if (columna.equals("fecha_modificacion")) {
/* 121 */         reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/* 122 */       } else if (columna.equals("fecha_modificacion")) {
/* 123 */         reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*     */       } 
/* 125 */       return reg;
/*     */     }
/* 127 */     catch (Exception e) {
/* 128 */       e.printStackTrace();
/* 129 */       Utilidades.writeError("SisMultiValoresDAO:leerRegistro ", e);
/*     */       
/* 131 */       return null;
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
/*     */   public Collection<SisMultiValoresDTO> cargarTodos(String tabla, String descripcion) {
/* 144 */     Collection<SisMultiValoresDTO> resultados = new ArrayList<SisMultiValoresDTO>();
/*     */     try {
/* 146 */       String s = "select t.tabla,t.valor,t.descripcion,t.estado,m1.DESCRIPCION as nombre_estado,t.varios,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from sis_multivalores t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 160 */       if (tabla.length() > 0) {
/* 161 */         s = s + " and upper(t.tabla) like upper('%" + tabla + "%')";
/*     */       }
/* 163 */       if (descripcion.length() > 0) {
/* 164 */         s = s + " and upper(t.descripcion) like upper('%" + descripcion + "%')";
/*     */       }
/* 166 */       s = s + " order by 1";
/* 167 */       boolean rtaDB = this.dat.parseSql(s);
/* 168 */       if (!rtaDB) {
/* 169 */         return resultados;
/*     */       }
/* 171 */       this.rs = this.dat.getResultSet();
/* 172 */       while (this.rs.next()) {
/* 173 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 176 */     catch (Exception e) {
/* 177 */       e.printStackTrace();
/* 178 */       Utilidades.writeError("SisMultiValoresDAO:cargarTodos ", e);
/*     */     } 
/* 180 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisMultiValoresDTO cargarRegistro(String tabla, String valor) {
/*     */     try {
/* 191 */       String s = "select t.tabla,t.valor,t.descripcion,t.estado,m1.DESCRIPCION as nombre_estado,t.varios,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from sis_multivalores t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where  t.tabla='" + tabla + "'" + " and t.valor='" + valor + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 208 */       boolean rtaDB = this.dat.parseSql(s);
/* 209 */       if (!rtaDB) {
/* 210 */         return null;
/*     */       }
/* 212 */       this.rs = this.dat.getResultSet();
/* 213 */       if (this.rs.next()) {
/* 214 */         return leerRegistro();
/*     */       }
/*     */     }
/* 217 */     catch (Exception e) {
/* 218 */       e.printStackTrace();
/* 219 */       Utilidades.writeError("SisMultiValoresDAO:cargarSisMultiValores", e);
/*     */     } 
/* 221 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(String tabla, String valor) {
/* 232 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 235 */       String s = "delete from sis_multivalores where  tabla='" + tabla + "'" + "  and valor='" + valor + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 240 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 242 */     catch (Exception e) {
/* 243 */       e.printStackTrace();
/* 244 */       Utilidades.writeError("SisMultiValoresDAO:eliminarRegistro ", e);
/* 245 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 247 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(String tabla, String valor, String descripcion, String estado, String varios, String usuarioInsercion) {
/* 263 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 266 */       String s = "insert into sis_multivalores(tabla,valor,descripcion,estado,varios,fecha_insercion,usuario_insercion) values ('" + tabla + "'," + "'" + valor + "'," + "'" + descripcion + "'," + "'" + estado + "'," + "'" + varios + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 283 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 285 */     catch (Exception e) {
/* 286 */       e.printStackTrace();
/* 287 */       Utilidades.writeError("%SisMultiValoresDAO:crearRegistro ", e);
/* 288 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 290 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(String tabla, String valor, String descripcion, String estado, String varios, String usuarioModificacion) {
/* 306 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 309 */       String s = "update sis_multivalores set  descripcion='" + descripcion + "'," + " estado='" + estado + "'," + " varios='" + varios + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " tabla='" + tabla + "'" + " and valor='" + valor + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 319 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 321 */     catch (Exception e) {
/* 322 */       e.printStackTrace();
/* 323 */       Utilidades.writeError("SisMultiValoresDAO:modificarRegistro ", e);
/* 324 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 326 */     return rta;
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
/*     */   public Collection<SisMultiValoresDTO> cargarTabla(String tabla) {
/* 338 */     Collection<SisMultiValoresDTO> resultados = new ArrayList<SisMultiValoresDTO>();
/*     */     try {
/* 340 */       String s = "select * from sis_multivalores  where tabla='" + tabla + "'" + " and estado='A'" + " order by valor";
/*     */ 
/*     */ 
/*     */       
/* 344 */       boolean rta = this.dat.parseSql(s);
/* 345 */       if (!rta) {
/* 346 */         return resultados;
/*     */       }
/* 348 */       this.rs = this.dat.getResultSet();
/* 349 */       while (this.rs.next()) {
/* 350 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 353 */     catch (Exception e) {
/* 354 */       e.printStackTrace();
/*     */     } 
/* 356 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<SisMultiValoresDTO> cargarTabla(String tabla, String codigo) {
/* 367 */     Collection<SisMultiValoresDTO> resultados = new ArrayList<SisMultiValoresDTO>();
/*     */     try {
/* 369 */       String s = "select * from sis_multivalores  where tabla='" + tabla + "'" + " and valor='" + codigo + "'" + " order by 1";
/*     */ 
/*     */ 
/*     */       
/* 373 */       boolean rta = this.dat.parseSql(s);
/* 374 */       if (!rta) {
/* 375 */         return resultados;
/*     */       }
/* 377 */       this.rs = this.dat.getResultSet();
/* 378 */       while (this.rs.next()) {
/* 379 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 382 */     catch (Exception e) {
/* 383 */       e.printStackTrace();
/*     */     } 
/* 385 */     return resultados;
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
/*     */   public Collection<SisMultiValoresDTO> cargarTabla(String tabla, String estado, String varios) {
/* 397 */     Collection<SisMultiValoresDTO> resultados = new ArrayList<SisMultiValoresDTO>();
/*     */     try {
/* 399 */       String s = "select * from sis_multivalores  where tabla='" + tabla + "'" + " and estado='" + estado + "'" + " and varios like '" + varios + "'" + " order by valor";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 404 */       boolean rta = this.dat.parseSql(s);
/* 405 */       if (!rta) {
/* 406 */         return resultados;
/*     */       }
/* 408 */       this.rs = this.dat.getResultSet();
/* 409 */       while (this.rs.next()) {
/* 410 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 413 */     catch (Exception e) {
/* 414 */       e.printStackTrace();
/*     */     } 
/* 416 */     return resultados;
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
/*     */   public Collection<SisMultiValoresDTO> cargarTablaConIdentificacion(String tabla, String estado, String varios, String numeroIdentificacion) {
/* 429 */     Collection<SisMultiValoresDTO> resultados = new ArrayList<SisMultiValoresDTO>();
/*     */     try {
/* 431 */       String s = "select sm.tabla, sm.valor,sm.descripcion,sm.estado,sm.varios,sm.fecha_insercion,sm.usuario_insercion,sm.fecha_modificacion,sm.usuario_modificacion  from sis_multivalores sm where sm.tabla='" + tabla + "' " + "and sm.varios like '" + varios + "' " + "and sm.descripcion NOT IN(select rpd.tipo_documento from red_prestador_documentos " + "rpd where numero_identificacion='" + numeroIdentificacion + "'); ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 437 */       boolean rta = this.dat.parseSql(s);
/* 438 */       if (!rta) {
/* 439 */         return resultados;
/*     */       }
/* 441 */       this.rs = this.dat.getResultSet();
/* 442 */       while (this.rs.next()) {
/* 443 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 446 */     catch (Exception e) {
/* 447 */       e.printStackTrace();
/*     */     } 
/* 449 */     return resultados;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<SisMultiValoresDTO> cargarTablaActivos(String tabla) {
/* 454 */     ArrayList<SisMultiValoresDTO> resultados = new ArrayList<SisMultiValoresDTO>();
/*     */     try {
/* 456 */       String s = "select sis.* from sis_multivalores sis where tabla='" + tabla + "'" + " and sis.estado='A'" + " order by sis.descripcion";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 462 */       boolean rtaDB = this.dat.parseSql(s);
/* 463 */       this.rs = this.dat.getResultSet();
/* 464 */       if (rtaDB) {
/* 465 */         while (this.rs.next()) {
/* 466 */           resultados.add(leerRegistro());
/*     */         }
/*     */       }
/* 469 */       return resultados;
/*     */     }
/* 471 */     catch (Exception e) {
/* 472 */       e.printStackTrace();
/* 473 */       Utilidades.writeError("SisMultivaloresDAO:cargarTabla " + e.getMessage());
/*     */       
/* 475 */       return null;
/*     */     } 
/*     */   }
/*     */   public SisMultiValoresDTO cargarRegistroNombre(String tabla, String nombre) {
/*     */     try {
/* 480 */       String s = "select * from sis_multivalores  where  tabla='" + tabla + "'" + " and descripcion='" + nombre + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 485 */       boolean rtaDB = this.dat.parseSql(s);
/* 486 */       if (!rtaDB) {
/* 487 */         return null;
/*     */       }
/* 489 */       this.rs = this.dat.getResultSet();
/* 490 */       if (this.rs.next()) {
/* 491 */         return leerRegistro();
/*     */       }
/*     */     }
/* 494 */     catch (Exception e) {
/* 495 */       e.printStackTrace();
/* 496 */       Utilidades.writeError("SisMultivaloresDAO:cargarSisMultivalores " + e.getMessage());
/*     */     } 
/* 498 */     return null;
/*     */   }
/*     */   
/*     */   public Collection<SisMultiValoresDTO> cargarTablaOrderEntero(String tabla) {
/* 502 */     ArrayList<SisMultiValoresDTO> resultados = new ArrayList<SisMultiValoresDTO>();
/*     */     try {
/* 504 */       String s = "select sis.* from sis_multivalores sis where tabla='" + tabla + "'" + " order by sis.entero";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 509 */       boolean rtaDB = this.dat.parseSql(s);
/* 510 */       this.rs = this.dat.getResultSet();
/* 511 */       if (rtaDB) {
/* 512 */         while (this.rs.next()) {
/* 513 */           resultados.add(leerRegistro());
/*     */         }
/*     */       }
/* 516 */       return resultados;
/*     */     }
/* 518 */     catch (Exception e) {
/* 519 */       e.printStackTrace();
/* 520 */       Utilidades.writeError("SisMultivaloresDAO:cargarTabla ", e);
/*     */       
/* 522 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\SisMultiValoresDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */