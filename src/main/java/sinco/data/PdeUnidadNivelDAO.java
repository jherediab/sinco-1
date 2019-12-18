/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PdeUnidadNivelDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PdeUnidadNivelDAO;
/*     */ 

/*     */ 
/*     */ public class PdeUnidadNivelDAO
/*     */ {
/*     */   public PdeUnidadNivelDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  35 */       PdeUnidadNivelDTO reg = new PdeUnidadNivelDTO();
/*     */       
/*  37 */       reg.setIdUnidadNivel(rs.getInt("id_unidad_nivel"));
/*  38 */       reg.setCodigoUnidad(rs.getString("codigo_unidad"));
/*  39 */       reg.setIdNivel(rs.getInt("id_nivel"));
/*  40 */       reg.setIdUnidadSuperior(rs.getInt("id_unidad_superior"));
/*  41 */       reg.setNombreUnidad(rs.getString("nombre_unidad"));
/*  42 */       reg.setObjetivoGeneral(rs.getString("objetivo_general"));
/*  43 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  44 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  45 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  46 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  47 */       return reg;
/*     */     }
/*  49 */     catch (Exception e) {
/*  50 */       e.printStackTrace();
/*  51 */       Utilidades.writeError("PdeUnidadNivelDAO:leerRegistro ", e);
/*     */       
/*  53 */       return null;
/*     */     } 
/*     */   }

/*     */   
/*     */   public Collection<PdeUnidadNivelDTO> cargarTodos(int idNivel, int idUnidadSuperior, String nombreUnidad) {
/*  65 */     Collection<PdeUnidadNivelDTO> resultados = new ArrayList<PdeUnidadNivelDTO>();
/*     */     
/*  67 */    DBManager dat = new DBManager();
/*     */     try {
/*  69 */       String s = "select t.id_unidad_nivel,t.codigo_unidad,t.id_nivel,t.id_unidad_superior,t.nombre_unidad,t.objetivo_general,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from pde_unidad_nivel t  where 1=1";

/*  82 */       if (idNivel > 0) {
/*  83 */         s = s + " and t.id_nivel=" + idNivel;
/*     */       }
/*  85 */       if (idUnidadSuperior > 0) {
/*  86 */         s = s + " and t.id_unidad_superior=" + idUnidadSuperior;
/*     */       }
/*  88 */       if (nombreUnidad.length() > 0) {
/*  89 */         s = s + " and upper(t.nombre_unidad) like upper('%" + nombreUnidad + "%')";
/*     */       }
/*  91 */       s = s + " order by 2,3";
/*  92 */       boolean rtaDB = dat.parseSql(s);
/*  93 */       if (!rtaDB) {
/*  94 */         return resultados;
/*     */       }
/*  96 */       ResultSet rs = dat.getResultSet();
/*  97 */       while (rs.next()) {
/*  98 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 101 */     catch (Exception e) {
/* 102 */       e.printStackTrace();
/* 103 */       Utilidades.writeError("PdeUnidadNivelDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 106 */       dat.close();
/*     */     } 
/* 108 */     return resultados;
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
/*     */   public PdeUnidadNivelDTO cargarRegistro(int idUnidadNivel, int idNivel, int idUnidadSuperior) {
/* 120 */   DBManager  dat = new DBManager();
/*     */     try {
/* 122 */       String s = "select t.id_unidad_nivel,t.codigo_unidad,t.id_nivel,t.id_unidad_superior,t.nombre_unidad,t.objetivo_general,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from pde_unidad_nivel t  where  t.id_unidad_nivel=" + idUnidadNivel;
/*     */ 

/*     */       
/* 137 */       if (idNivel > 0) {
/* 138 */         s = s + " and t.id_nivel=" + idNivel;
/*     */       }
/* 140 */       if (idUnidadSuperior > 0) {
/* 141 */         s = s + " and t.id_unidad_superior=" + idUnidadSuperior;
/*     */       }
/* 143 */       s = s + " order by 2";
/* 144 */       boolean rtaDB = dat.parseSql(s);
/* 145 */       if (!rtaDB) {
/* 146 */         return null;
/*     */       }
/* 148 */       ResultSet rs = dat.getResultSet();
/* 149 */       if (rs.next()) {
/* 150 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 153 */     catch (Exception e) {
/* 154 */       e.printStackTrace();
/* 155 */       Utilidades.writeError("PdeUnidadNivelDAO:cargarPdeUnidadNivel", e);
/*     */     } finally {
/*     */       
/* 158 */       dat.close();
/*     */     } 
/* 160 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 169 */     int inumero = 1;
/* 170 */     String s = "select max(id_unidad_nivel) from pde_unidad_nivel ";
/*     */ 
/*     */     
/* 173 */    DBManager dat = new DBManager();
/*     */     try {
/* 175 */       boolean rta = dat.parseSql(s);
/* 176 */       if (!rta) return 0; 
/* 177 */       ResultSet rs = dat.getResultSet();
/* 178 */       if (rs.next()) {
/* 179 */         s = rs.getString(1);
/* 180 */         if (!rs.wasNull()) {
/* 181 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 184 */       return inumero;
/*     */     }
/* 186 */     catch (Exception e) {
/* 187 */       e.printStackTrace();
/* 188 */       Utilidades.writeError("PdeUnidadNivelDAO:siguienteRegistro ", e);
/*     */     } finally {
/*     */       
/* 191 */       dat.close();
/*     */     } 
/* 193 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int idUnidadNivel) {
/* 203 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 205 */    DBManager dat = new DBManager();
/*     */     try {
/* 207 */       String s = "delete from pde_unidad_nivel where  id_unidad_nivel=" + idUnidadNivel + "";
/*     */ 
/*     */ 
/*     */       
/* 211 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 213 */     catch (Exception e) {
/* 214 */       e.printStackTrace();
/* 215 */       Utilidades.writeError("PdeUnidadNivelDAO:eliminarRegistro ", e);
/* 216 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 219 */       dat.close();
/*     */     } 
/* 221 */     return rta;
/*     */   }
/*     */ 
/*     */ 

/*     */ 
/*     */   
/*     */   public RespuestaBD crearRegistro(int idUnidadNivel, String codigoUnidad, int idNivel, int idUnidadSuperior, String nombreUnidad, String objetivoGeneral, String usuarioInsercion) {
/* 238 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 240 */     int elSiguiente = siguienteRegistro();
/* 241 */     if (elSiguiente == 0) {
/* 242 */       rta.setMensaje("Generando secuencia");
/* 243 */       return rta;
/*     */     } 
/*     */     
/* 246 */    DBManager dat = new DBManager();
/*     */     try {
/* 248 */       String s = "insert into pde_unidad_nivel(id_unidad_nivel,codigo_unidad,id_nivel,id_unidad_superior,nombre_unidad,objetivo_general,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "'" + codigoUnidad + "'," + "" + idNivel + "," + "" + idUnidadSuperior + "," + "'" + nombreUnidad + "'," + "'" + objetivoGeneral + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 

/*     */       
/* 267 */       rta = dat.executeUpdate2(s);
/* 268 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 270 */     catch (Exception e) {
/* 271 */       e.printStackTrace();
/* 272 */       Utilidades.writeError("%PdeUnidadNivelDAO:crearRegistro ", e);
/* 273 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 276 */       dat.close();
/*     */     } 
/* 278 */     return rta;
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
/*     */   public RespuestaBD crearRegistroSuperior(int idUnidadNivel, String codigoUnidad, int idNivel, String nombreUnidad, String objetivoGeneral, String usuarioInsercion) {
/* 295 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 297 */     int elSiguiente = siguienteRegistro();
/* 298 */     if (elSiguiente == 0) {
/* 299 */       rta.setMensaje("Generando secuencia");
/* 300 */       return rta;
/*     */     } 
/*     */     
/* 303 */    DBManager dat = new DBManager();
/*     */     try {
/* 305 */       String s = "insert into pde_unidad_nivel(id_unidad_nivel,codigo_unidad,id_nivel,nombre_unidad,objetivo_general,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "'" + codigoUnidad + "'," + "" + idNivel + "," + "'" + nombreUnidad + "'," + "'" + objetivoGeneral + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 

/*     */       
/* 322 */       rta = dat.executeUpdate2(s);
/* 323 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 325 */     catch (Exception e) {
/* 326 */       e.printStackTrace();
/* 327 */       Utilidades.writeError("%PdeUnidadNivelDAO:crearRegistroSuperior ", e);
/* 328 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 331 */       dat.close();
/*     */     } 
/* 333 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int idUnidadNivel, String codigoUnidad, int idNivel, int idUnidadSuperior, String nombreUnidad, String objetivoGeneral, String usuarioModificacion) {
/* 349 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 351 */    DBManager dat = new DBManager();
/*     */     try {
/* 353 */       String s = "update pde_unidad_nivel set  codigo_unidad='" + codigoUnidad + "'," + " nombre_unidad='" + nombreUnidad + "'," + " objetivo_general='" + objetivoGeneral + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " id_unidad_nivel=" + idUnidadNivel + "";

/*     */       
/* 362 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 364 */     catch (Exception e) {
/* 365 */       e.printStackTrace();
/* 366 */       Utilidades.writeError("PdeUnidadNivelDAO:modificarRegistro ", e);
/* 367 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 370 */       dat.close();
/*     */     } 
/* 372 */     return rta;
/*     */   }
/*     */ 

/*     */   public RespuestaBD modificarRegistroSuperior(int idUnidadNivel, String codigoUnidad, int idNivel, String nombreUnidad, String objetivoGeneral, String usuarioModificacion) {
/* 389 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 391 */   DBManager  dat = new DBManager();
/*     */     try {
/* 393 */       String s = "update pde_unidad_nivel set  codigo_unidad='" + codigoUnidad + "'," + " nombre_unidad='" + nombreUnidad + "'," + " objetivo_general='" + objetivoGeneral + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " id_unidad_nivel=" + idUnidadNivel + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 402 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 404 */     catch (Exception e) {
/* 405 */       e.printStackTrace();
/* 406 */       Utilidades.writeError("PdeUnidadNivelDAO:modificarRegistro ", e);
/* 407 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 410 */       dat.close();
/*     */     } 
/* 412 */     return rta;
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
/*     */   public boolean poseeNivelInferior(int idNivel) {
/* 425 */   DBManager  dat = new DBManager();
/*     */     try {
/* 427 */       String s = "select * from pde_unidad_nivel where id_unidad_superior =" + idNivel;
/*     */       
/* 429 */       boolean rtaDB = dat.parseSql(s);
/* 430 */       if (!rtaDB) {
/* 431 */         return false;
/*     */       }
/* 433 */       ResultSet rs = dat.getResultSet();
/* 434 */       if (rs.next()) {
/* 435 */         return true;
/*     */       }
/*     */       
/* 438 */       return false;
/*     */     
/*     */     }
/* 441 */     catch (Exception e) {
/* 442 */       e.printStackTrace();
/* 443 */       Utilidades.writeError("PdeUnidadNivelDAO:poseeNivelInferior", e);
/*     */     } finally {
/*     */       
/* 446 */       dat.close();
/*     */     } 
/* 448 */     return true;
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
/*     */   public boolean poseeObjetivosEspecificos(int idNivel) {
/* 461 */    DBManager dat = new DBManager();
/*     */     try {
/* 463 */       String s = "select * from pde_objetivos_especificos where id_unidad_nivel =" + idNivel;
/*     */       
/* 465 */       boolean rtaDB = dat.parseSql(s);
/* 466 */       if (!rtaDB) {
/* 467 */         return false;
/*     */       }
/* 469 */       ResultSet rs = dat.getResultSet();
/* 470 */       if (rs.next()) {
/* 471 */         return true;
/*     */       }
/* 473 */       return false;
/*     */     
/*     */     }
/* 476 */     catch (Exception e) {
/* 477 */       e.printStackTrace();
/* 478 */       Utilidades.writeError("PdeUnidadNivelDAO:poseeObjetivosEspecificos", e);
/*     */     } finally {
/*     */       
/* 481 */       dat.close();
/*     */     } 
/* 483 */     return true;
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
/*     */   public boolean poseeMetas(int idNivel) {
/* 496 */   DBManager  dat = new DBManager();
/*     */     try {
/* 498 */       String s = "select * from pde_metas where id_unidad_nivel =" + idNivel;
/*     */       
/* 500 */       boolean rtaDB = dat.parseSql(s);
/* 501 */       if (!rtaDB) {
/* 502 */         return false;
/*     */       }
/* 504 */       ResultSet rs = dat.getResultSet();
/* 505 */       if (rs.next()) {
/* 506 */         return true;
/*     */       }
/* 508 */       return false;
/*     */     
/*     */     }
/* 511 */     catch (Exception e) {
/* 512 */       e.printStackTrace();
/* 513 */       Utilidades.writeError("PdeUnidadNivelDAO:poseeMetas", e);
/*     */     } finally {
/*     */       
/* 516 */       dat.close();
/*     */     } 
/* 518 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PdeUnidadNivelDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */