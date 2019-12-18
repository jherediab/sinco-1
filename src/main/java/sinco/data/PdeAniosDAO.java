/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PdeAniosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PdeAniosDAO;
/*     */ 

/*     */ public class PdeAniosDAO
/*     */ {
/*     */   public PdeAniosDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  35 */       PdeAniosDTO reg = new PdeAniosDTO();
/*     */       
/*  37 */       reg.setIdAnio(rs.getInt("id_anio"));
/*  38 */       reg.setIdMeta(rs.getInt("id_meta"));
/*  39 */       reg.setAnio(rs.getInt("anio"));
/*  40 */       reg.setProgramado(rs.getString("programado"));
/*  41 */       reg.setEjecutado(rs.getString("ejecutado"));
/*     */       
/*  43 */       reg.setTr1Programado(rs.getString("tr1_programado"));
/*  44 */       reg.setTr1Ejecutado(rs.getString("tr1_ejecutado"));
/*     */       
/*  46 */       reg.setTr2Programado(rs.getString("tr2_programado"));
/*  47 */       reg.setTr2Ejecutado(rs.getString("tr2_ejecutado"));
/*     */       
/*  49 */       reg.setTr3Programado(rs.getString("tr3_programado"));
/*  50 */       reg.setTr3Ejecutado(rs.getString("tr3_ejecutado"));
/*     */       
/*  52 */       reg.setTr4Programado(rs.getString("tr4_programado"));
/*  53 */       reg.setTr4Ejecutado(rs.getString("tr4_ejecutado"));
/*     */       
/*  55 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  56 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  57 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  58 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  59 */       return reg;
/*     */     }
/*  61 */     catch (Exception e) {
/*  62 */       e.printStackTrace();
/*  63 */       Utilidades.writeError("PdeAniosDAO:leerRegistro ", e);
/*     */       
/*  65 */       return null;
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
/*     */   public Collection<PdeAniosDTO> cargarTodos(int idMeta, boolean activado) {
/*  77 */     Collection<PdeAniosDTO> resultados = new ArrayList<PdeAniosDTO>();
/*     */     
/*  79 */   DBManager  dat = new DBManager();
/*     */     try {
/*  81 */       String s = "select t.id_anio,t.id_meta,t.anio,t.programado,t.ejecutado,t.tr1_programado,t.tr1_ejecutado,t.tr2_programado,t.tr2_ejecutado,t.tr3_programado,t.tr3_ejecutado,t.tr4_programado,t.tr4_ejecutado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from pde_anios t  where 1=1";
/*     */ 

/*     */       
/* 101 */       if (idMeta > 0) {
/* 102 */         s = s + " and t.id_meta=" + idMeta;
/*     */       }
/* 104 */       s = s + " order by 1";
/* 105 */       boolean rtaDB = dat.parseSql(s);
/* 106 */       if (!rtaDB) {
/* 107 */         return resultados;
/*     */       }
/* 109 */       ResultSet rs = dat.getResultSet();
/* 110 */       while (rs.next()) {
/* 111 */         if (activado) {
/* 112 */           for (int i = 0; i < 4; i++)
/* 113 */             resultados.add(leerRegistro(rs)); 
/*     */           continue;
/*     */         } 
/* 116 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     
/*     */     }
/* 120 */     catch (Exception e) {
/* 121 */       e.printStackTrace();
/* 122 */       Utilidades.writeError("PdeAniosDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 125 */       dat.close();
/*     */     } 
/* 127 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PdeAniosDTO cargarRegistro(int idAnio, int idMeta) {
/* 138 */    DBManager dat = new DBManager();
/*     */     try {
/* 140 */       String s = "select t.id_anio,t.id_meta,t.anio,t.programado,t.ejecutado,t.tr1_programado,t.tr1_ejecutado,t.tr2_programado,t.tr2_ejecutado,t.tr3_programado,t.tr3_ejecutado,t.tr4_programado,t.tr4_ejecutado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from pde_anios t  where  t.anio=" + idAnio + " and t.id_meta=" + idMeta + "";
/*     */ 

/*     */       
/* 163 */       boolean rtaDB = dat.parseSql(s);
/* 164 */       if (!rtaDB) {
/* 165 */         return null;
/*     */       }
/* 167 */       ResultSet rs = dat.getResultSet();
/* 168 */       if (rs.next()) {
/* 169 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 172 */     catch (Exception e) {
/* 173 */       e.printStackTrace();
/* 174 */       Utilidades.writeError("PdeAniosDAO:cargarPdeAnios", e);
/*     */     } finally {
/*     */       
/* 177 */       dat.close();
/*     */     } 
/* 179 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 188 */     int inumero = 1;
/* 189 */     String s = "select max(id_anio) from pde_anios ";
/*     */ 
/*     */     
/* 192 */    DBManager dat = new DBManager();
/*     */     try {
/* 194 */       boolean rta = dat.parseSql(s);
/* 195 */       if (!rta) return 0; 
/* 196 */       ResultSet rs = dat.getResultSet();
/* 197 */       if (rs.next()) {
/* 198 */         s = rs.getString(1);
/* 199 */         if (!rs.wasNull()) {
/* 200 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 203 */       return inumero;
/*     */     }
/* 205 */     catch (Exception e) {
/* 206 */       e.printStackTrace();
/* 207 */       Utilidades.writeError("PdeAniosDAO:siguienteRegistro ", e);
/*     */     } finally {
/*     */       
/* 210 */       dat.close();
/*     */     } 
/* 212 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int idMeta) {
/* 222 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 224 */   DBManager  dat = new DBManager();
/*     */     try {
/* 226 */       String s = "delete from pde_anios where  id_meta=" + idMeta + "";
/*     */ 
/*     */ 
/*     */       
/* 230 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 232 */     catch (Exception e) {
/* 233 */       e.printStackTrace();
/* 234 */       Utilidades.writeError("PdeAniosDAO:eliminarRegistro ", e);
/* 235 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 238 */       dat.close();
/*     */     } 
/* 240 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int idAnio, int idMeta, int anio, String programado, String ejecutado, String usuarioInsercion) {
/* 256 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 258 */     int elSiguiente = siguienteRegistro();
/* 259 */     if (elSiguiente == 0) {
/* 260 */       rta.setMensaje("Generando secuencia");
/* 261 */       return rta;
/*     */     } 
/*     */     
/* 264 */    DBManager dat = new DBManager();
/*     */     try {
/* 266 */       String s = "insert into pde_anios(id_anio,id_meta,anio,programado,ejecutado,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "" + idMeta + "," + "" + anio + "," + "'" + programado + "'," + "'" + ejecutado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 

/*     */       
/* 283 */       rta = dat.executeUpdate2(s);
/* 284 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 286 */     catch (Exception e) {
/* 287 */       e.printStackTrace();
/* 288 */       Utilidades.writeError("%PdeAniosDAO:crearRegistro ", e);
/* 289 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 292 */       dat.close();
/*     */     } 
/* 294 */     return rta;
/*     */   }

/*     */ 
/*     */   
/*     */   public RespuestaBD crearRegistroUltimoNivel(int idAnio, int idMeta, int anio, String tr1Programado, String tr1Ejecutado, String tr2Programado, String tr2Ejecutado, String tr3Programado, String tr3Ejecutado, String tr4Programado, String tr4Ejecutado, String usuarioInsercion) {
/* 317 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 319 */     int elSiguiente = siguienteRegistro();
/* 320 */     if (elSiguiente == 0) {
/* 321 */       rta.setMensaje("Generando secuencia");
/* 322 */       return rta;
/*     */     } 
/*     */     
/* 325 */    DBManager dat = new DBManager();
/*     */     try {
/* 327 */       String s = "insert into pde_anios(id_anio,id_meta,anio,tr1_programado,tr1_ejecutado,tr2_programado,tr2_ejecutado,tr3_programado,tr3_ejecutado,tr4_programado,tr4_ejecutado,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "" + idMeta + "," + "" + anio + "," + "'" + tr1Programado + "'," + "'" + tr1Ejecutado + "'," + "'" + tr2Programado + "'," + "'" + tr2Ejecutado + "'," + "'" + tr3Programado + "'," + "'" + tr3Ejecutado + "'," + "'" + tr4Programado + "'," + "'" + tr4Ejecutado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 

/*     */       
/* 356 */       rta = dat.executeUpdate2(s);
/* 357 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 359 */     catch (Exception e) {
/* 360 */       e.printStackTrace();
/* 361 */       Utilidades.writeError("%PdeAniosDAO:crearRegistroUltimoNivel ", e);
/* 362 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 365 */       dat.close();
/*     */     } 
/* 367 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int idMeta, int anio, String programado, String ejecutado, String usuarioModificacion) {
/* 382 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 384 */    DBManager dat = new DBManager();
/*     */     try {
/* 386 */       String s = "update pde_anios set  anio=" + anio + "," + " programado='" + programado + "'," + " ejecutado='" + ejecutado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " id_meta=" + idMeta + " and anio=" + anio + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 396 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 398 */     catch (Exception e) {
/* 399 */       e.printStackTrace();
/* 400 */       Utilidades.writeError("PdeAniosDAO:modificarRegistro ", e);
/* 401 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 404 */       dat.close();
/*     */     } 
/* 406 */     return rta;
/*     */   }
/*     */ 

/*     */   
/*     */   public RespuestaBD modificarRegistroUltimoNivel(int idMeta, int anio, String tr1Programado, String tr1Ejecutado, String tr2Programado, String tr2Ejecutado, String tr3Programado, String tr3Ejecutado, String tr4Programado, String tr4Ejecutado, String usuarioModificacion) {
/* 428 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 430 */   DBManager  dat = new DBManager();
/*     */     try {
/* 432 */       String s = "update pde_anios set  anio=" + anio + "," + " tr1_programado='" + tr1Programado + "'," + " tr1_ejecutado='" + tr1Ejecutado + "'," + " tr2_programado='" + tr2Programado + "'," + " tr2_ejecutado='" + tr2Ejecutado + "'," + " tr3_programado='" + tr3Programado + "'," + " tr3_ejecutado='" + tr3Ejecutado + "'," + " tr4_programado='" + tr4Programado + "'," + " tr4_ejecutado='" + tr4Ejecutado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " id_meta=" + idMeta + " and anio=" + anio + "";
/*     */ 

/*     */ 
/*     */       
/* 448 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 450 */     catch (Exception e) {
/* 451 */       e.printStackTrace();
/* 452 */       Utilidades.writeError("PdeAniosDAO:modificarRegistroUltimoNivel ", e);
/* 453 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 456 */       dat.close();
/*     */     } 
/* 458 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PdeAniosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */