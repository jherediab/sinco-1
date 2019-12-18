/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.ArchivosSolicitudDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.business.VSolicitudesDTO;
/*     */ import sinco.data.ArchivosSolicitudDAO;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.VSolicitudesDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArchivosSolicitudDAO
/*     */ {
/*     */   ResultSet rs;
/*     */   
/*     */   public void close() {
/*     */     try {
/*  22 */       this.dat.close();
/*     */     }
/*  24 */     catch (Exception e) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  32 */   DBManager dat = new DBManager();
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
/*     */ 
/*     */   
/*     */   public ArchivosSolicitudDTO next() {
/*     */     try {
/*  49 */       if (this.rs.next()) {
/*  50 */         ArchivosSolicitudDTO reg = new ArchivosSolicitudDTO();
/*  51 */         reg.setNumeroSolicitud(this.rs.getInt("numero_solicitud"));
/*  52 */         reg.setConsecutivo(this.rs.getInt("consecutivo"));
/*  53 */         reg.setArchivo(this.rs.getString("archivo"));
/*  54 */         reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  55 */         reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  56 */         reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  57 */         reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  58 */         reg.setEstado(this.rs.getString("estado"));
/*  59 */         return reg;
/*     */       }
/*     */     
/*  62 */     } catch (SQLException e) {
/*  63 */       e.printStackTrace();
/*     */     } 
/*  65 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getArchivosSolicitud(int sol) {
/*     */     try {
/*  76 */       boolean rta = this.dat.parseSql("select * from archivos_solicitud where numero_solicitud =" + sol + " order by fecha_insercion");
/*  77 */       if (!rta) return false; 
/*  78 */       this.rs = this.dat.getResultSet();
/*  79 */       return true;
/*     */     }
/*  81 */     catch (Exception e) {
/*  82 */       e.printStackTrace();
/*  83 */       Utilidades.writeError("ArchivosSolicitudFactory ", e);
/*     */       
/*  85 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getArchivosSolicitud(int sol, int nroconsecutivo) {
/*     */     try {
/*  97 */       boolean rta = this.dat.parseSql("select * from archivos_solicitud where numero_solicitud =" + sol + " and consecutivo=" + nroconsecutivo);
/*  98 */       if (!rta) return false; 
/*  99 */       this.rs = this.dat.getResultSet();
/* 100 */       return true;
/*     */     }
/* 102 */     catch (Exception e) {
/* 103 */       e.printStackTrace();
/* 104 */       Utilidades.writeError("ArchivosSolicitudFactory ", e);
/*     */       
/* 106 */       return false;
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
/*     */   public boolean insertarArchivo(int sol, String archivo, int longitud, String usuario) {
/*     */     try {
/* 122 */       boolean rta = this.dat.parseSql("select MAX(consecutivo) from archivos_solicitud where numero_solicitud=" + sol);
/* 123 */       if (!rta) return false; 
/* 124 */       this.rs = this.dat.getResultSet();
/* 125 */       int numero = 1;
/* 126 */       if (this.rs.next()) {
/* 127 */         String temp = this.rs.getString(1);
/* 128 */         if (!this.rs.wasNull()) {
/* 129 */           numero = Integer.parseInt(temp) + 1;
/*     */         }
/*     */       } 
/* 132 */       String s = "insert into archivos_solicitud (numero_solicitud,consecutivo,archivo,longitud,fecha_insercion,usuario_insercion) VALUES(";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 139 */       s = s + "" + sol + ",";
/* 140 */       s = s + "" + numero + ",";
/* 141 */       s = s + "'" + archivo + "',";
/* 142 */       s = s + "" + longitud + ",";
/* 143 */       s = s + "" + Utilidades.formatoFecha(Utilidades.ahora()) + ",";
/* 144 */       s = s + "'" + usuario + "'";
/* 145 */       s = s + ")";
/* 146 */       this.dat.executeUpdate(s);
/* 147 */       return true;
/*     */     }
/* 149 */     catch (SQLException e) {
/* 150 */       e.printStackTrace();
/* 151 */       Utilidades.writeError("ArchivosSolicitudFactory ", e);
/*     */       
/* 153 */       return false;
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
/*     */   public boolean crearArchivo(int sol, String archivo, int longitud, String usuario) {
/* 171 */     VSolicitudesDAO sf = new VSolicitudesDAO();
/*     */     try {
/* 173 */       insertarArchivo(sol, archivo, longitud, usuario);
/*     */       
/* 175 */       VSolicitudesDTO regSol = sf.getPadre(sol);
/*     */       
/* 177 */       while (regSol.getSolicitudPadre() != -1) {
/* 178 */         VSolicitudesDTO regSolPadre = sf.getPadre(regSol.getSolicitudPadre());
/*     */         
/* 180 */         insertarArchivo(regSolPadre.getNumero(), archivo, longitud, usuario);
/*     */         
/* 182 */         regSol = regSolPadre;
/*     */       } 
/* 184 */       return true;
/*     */     }
/* 186 */     catch (Exception e) {
/* 187 */       e.printStackTrace();
/* 188 */       Utilidades.writeError("ArchivosSolicitudFactory ", e);
/*     */       
/* 190 */       sf.close();
/* 191 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean borrarArchivo(int sol, int consecutivo) {
/*     */     try {
/* 203 */       return this.dat.executeUpdate("delete from archivos_solicitud where numero_solicitud=" + sol + " and consecutivo=" + consecutivo);
/*     */     }
/* 205 */     catch (Exception e) {
/* 206 */       e.printStackTrace();
/* 207 */       Utilidades.writeError("ArchivosSolicitudFactory ", e);
/*     */       
/* 209 */       return false;
/*     */     } 
/*     */   }
/*     */   public boolean crearEncuesta(int sol, String Archivo, String usuario) {
/*     */     try {
/* 214 */       String s = "insert into archivo_encuesta (numero_encuesta,archivo,fecha_insercion,usuario_insercion) VALUES(";
/* 215 */       s = s + "" + sol + ",";
/* 216 */       s = s + "'" + Archivo + "',";
/* 217 */       s = s + "" + Utilidades.formatoFecha(Utilidades.ahora()) + ",";
/* 218 */       s = s + "'" + usuario + "'";
/* 219 */       s = s + ")";
/* 220 */       return this.dat.executeUpdate(s);
/*     */     }
/* 222 */     catch (Exception e) {
/* 223 */       e.printStackTrace();
/* 224 */       Utilidades.writeError("ArchivosSolicitudFactory ", e);
/*     */       
/* 226 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean borrarArchivoEncuesta(int sol) {
/*     */     try {
/* 236 */       return this.dat.executeUpdate("delete from archivo_encuesta where numero_encuesta=" + sol);
/*     */     }
/* 238 */     catch (Exception e) {
/* 239 */       e.printStackTrace();
/* 240 */       Utilidades.writeError("ArchivosSolicitudFactory ", e);
/*     */       
/* 242 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguiente(int sol) {
/*     */     try {
/* 252 */       boolean rta = this.dat.parseSql("select max(consecutivo) as numero from archivos_solicitud where numero_solicitud =" + sol);
/* 253 */       if (!rta) return 1; 
/* 254 */       this.rs = this.dat.getResultSet();
/* 255 */       int numero = 1;
/* 256 */       if (this.rs.next()) {
/* 257 */         String temp = this.rs.getString("numero");
/* 258 */         if (!this.rs.wasNull()) {
/* 259 */           numero = Integer.parseInt(temp) + 1;
/*     */         }
/*     */       } 
/* 262 */       return numero;
/*     */     }
/* 264 */     catch (Exception e) {
/* 265 */       e.printStackTrace();
/* 266 */       Utilidades.writeError("ArchivosSolicitudFactory ", e);
/*     */       
/* 268 */       return 1;
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
/*     */   public boolean crearSisArchivo(String tabla, int secuencia, int consecutivo, String archivo, String usuario) {
/*     */     try {
/* 286 */       String s = "insert into sis_archivos (tabla,secuencia,consecutivo,archivo,fecha_insercion,usuario_insercion) VALUES('" + tabla + "'," + "'" + secuencia + "'," + "" + consecutivo + "," + "'" + archivo + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuario + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 299 */       this.dat.executeUpdate(s);
/* 300 */       return true;
/*     */     }
/* 302 */     catch (Exception e) {
/* 303 */       e.printStackTrace();
/* 304 */       Utilidades.writeError("ArchivosSolicitudFactory ", e);
/*     */       
/* 306 */       return false;
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
/*     */   public int consecutivoSisArchivo(String tabla, int secuencia) {
/*     */     try {
/* 322 */       String s = "select MAX(consecutivo) from sis_archivos where  tabla='" + tabla + "'" + "        and secuencia='" + secuencia + "'";
/*     */ 
/*     */       
/* 325 */       boolean rta = this.dat.parseSql(s);
/* 326 */       if (!rta) return 0; 
/* 327 */       this.rs = this.dat.getResultSet();
/* 328 */       int numero = 1;
/* 329 */       if (this.rs.next()) {
/* 330 */         String temp = this.rs.getString(1);
/* 331 */         if (!this.rs.wasNull()) {
/* 332 */           numero = Integer.parseInt(temp) + 1;
/*     */         }
/*     */       } 
/* 335 */       return numero;
/*     */     }
/* 337 */     catch (SQLException e) {
/* 338 */       e.printStackTrace();
/* 339 */       Utilidades.writeError("ArchivosSolicitudFactory ", e);
/*     */       
/* 341 */       return 0;
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
/*     */   public Collection cargarSisArchivos(String tabla, int secuencia) {
/* 353 */     Collection resultados = new ArrayList();
/*     */     try {
/* 355 */       String s = "SELECT tabla,secuencia,consecutivo,archivo,estado,usuario_insercion,fecha_insercion,usuario_modificacion,fecha_modificacion FROM sis_archivos where tabla='" + tabla + "'" + " and secuencia='" + secuencia + "'" + " order by fecha_insercion";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 369 */       boolean rtaDB = this.dat.parseSql(s);
/* 370 */       if (!rtaDB) {
/* 371 */         return resultados;
/*     */       }
/* 373 */       this.rs = this.dat.getResultSet();
/* 374 */       while (this.rs.next()) {
/* 375 */         ArchivosSolicitudDTO reg = new ArchivosSolicitudDTO();
/* 376 */         reg.setNumeroSolicitud(this.rs.getInt("secuencia"));
/* 377 */         reg.setConsecutivo(this.rs.getInt("consecutivo"));
/* 378 */         reg.setArchivo(this.rs.getString("archivo"));
/* 379 */         reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/* 380 */         reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/* 381 */         reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/* 382 */         reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/* 383 */         reg.setEstado(this.rs.getString("estado"));
/* 384 */         resultados.add(reg);
/*     */       }
/*     */     
/* 387 */     } catch (Exception e) {
/* 388 */       e.printStackTrace();
/* 389 */       Utilidades.writeError("ArchivosSolicitudFactory ", e);
/*     */     } 
/* 391 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteAccion(int numero) {
/*     */     try {
/* 401 */       boolean rta = this.dat.parseSql("select max(consecutivo) as numero from am_archivos where numero_solicitud =" + numero);
/* 402 */       if (!rta) return 1; 
/* 403 */       this.rs = this.dat.getResultSet();
/* 404 */       int consecutivo = 1;
/* 405 */       if (this.rs.next()) {
/* 406 */         String temp = this.rs.getString("numero");
/* 407 */         if (!this.rs.wasNull()) {
/* 408 */           consecutivo = Integer.parseInt(temp) + 1;
/*     */         }
/*     */       } 
/* 411 */       return consecutivo;
/*     */     }
/* 413 */     catch (Exception e) {
/* 414 */       e.printStackTrace();
/*     */       
/* 416 */       return 1;
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
/*     */   public boolean crearArchivoAccion(int numero, int consecutivo, int causa, String archivo, String usuario) {
/*     */     try {
/* 437 */       String s = "insert into am_archivos (numero_solicitud,consecutivo,causa,archivo,fecha_insercion,usuario_insercion) VALUES(";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 443 */       s = s + "" + numero + ",";
/* 444 */       s = s + "" + consecutivo + ",";
/* 445 */       s = s + "" + causa + ",";
/* 446 */       s = s + "'" + archivo + "',";
/* 447 */       s = s + "" + Utilidades.getFechaBD() + ",";
/* 448 */       s = s + "'" + usuario + "'";
/* 449 */       s = s + ")";
/* 450 */       return this.dat.executeUpdate(s);
/*     */     }
/* 452 */     catch (Exception e) {
/* 453 */       e.printStackTrace();
/*     */       
/* 455 */       return false;
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
/*     */   public boolean borrarArchivoAccion(int numero, int consecutivo, int causa) {
/*     */     try {
/* 468 */       String s = "delete from  am_archivos  where numero_solicitud=" + numero + " and consecutivo=" + consecutivo + " and causa=" + causa;
/*     */ 
/*     */ 
/*     */       
/* 472 */       return this.dat.executeUpdate(s);
/*     */     }
/* 474 */     catch (Exception e) {
/* 475 */       e.printStackTrace();
/*     */       
/* 477 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getArchivosAccionMejora(int numero, int causa) {
/*     */     try {
/* 488 */       boolean rta = this.dat.parseSql("select * from am_archivos where numero_solicitud =" + numero + " and causa=" + causa + " order by fecha_insercion");
/* 489 */       if (!rta) return false; 
/* 490 */       this.rs = this.dat.getResultSet();
/* 491 */       return true;
/*     */     }
/* 493 */     catch (Exception e) {
/* 494 */       e.printStackTrace();
/*     */       
/* 496 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArchivosSolicitudDTO nextA() {
/*     */     try {
/* 505 */       if (this.rs.next()) {
/* 506 */         ArchivosSolicitudDTO ar = new ArchivosSolicitudDTO();
/* 507 */         ar.setNumeroSolicitud(this.rs.getInt("numero_solicitud"));
/* 508 */         ar.setConsecutivo(this.rs.getInt("consecutivo"));
/* 509 */         ar.setCausa(this.rs.getInt("causa"));
/* 510 */         ar.setArchivo(this.rs.getString("archivo"));
/* 511 */         ar.setFechaInsercion(this.rs.getString("fecha_insercion"));
/* 512 */         ar.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/* 513 */         ar.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/* 514 */         ar.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/* 515 */         ar.setEstado(this.rs.getString("estado"));
/* 516 */         return ar;
/*     */       }
/*     */     
/* 519 */     } catch (SQLException e) {
/* 520 */       e.printStackTrace();
/*     */     } 
/* 522 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ArchivosSolicitudDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */