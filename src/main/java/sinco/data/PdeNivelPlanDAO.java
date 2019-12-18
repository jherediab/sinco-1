/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PdeNivelPlanDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PdeNivelPlanDAO;
/*     */ 

/*     */ 
/*     */ 
/*     */ public class PdeNivelPlanDAO
/*     */ {
/*     */   public PdeNivelPlanDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  37 */       PdeNivelPlanDTO reg = new PdeNivelPlanDTO();
/*     */       
/*  39 */       reg.setIdNivel(rs.getInt("id_nivel"));
/*  40 */       reg.setIdPlanDesarrollo(rs.getInt("id_plan_desarrollo"));
/*  41 */       reg.setNivelSuperior(rs.getInt("nivel_superior"));
/*  42 */       reg.setNombreNivel(rs.getString("nombre_nivel"));
/*  43 */       reg.setTipoNivel(rs.getInt("tipo_nivel"));
/*  44 */       reg.setObjetivoGeneral(rs.getString("objetivo_general"));
/*  45 */       reg.setObjetivoEspecifico(rs.getString("objetivo_especifico"));
/*  46 */       reg.setMetasGeneral(rs.getString("metas_general"));
/*  47 */       reg.setMetasEspecifico(rs.getString("metas_especifico"));
/*  48 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  49 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  50 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  51 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  52 */       return reg;
/*     */     }
/*  54 */     catch (Exception e) {
/*  55 */       e.printStackTrace();
/*  56 */       Utilidades.writeError("PdeNivelPlanDAO:leerRegistro ", e);
/*     */       
/*  58 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<PdeNivelPlanDTO> cargarTodos(String nombreNivel, int idPlanDesarrollo) {
/*  69 */     Collection<PdeNivelPlanDTO> resultados = new ArrayList<PdeNivelPlanDTO>();
/*     */     
/*  71 */  DBManager   dat = new DBManager();
/*     */     try {
/*  73 */       String s = "select t.id_nivel,t.id_plan_desarrollo,t.nivel_superior,t.nombre_nivel,t.tipo_nivel,t.objetivo_general,t.objetivo_especifico,t.metas_general,t.metas_especifico,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from pde_nivel_plan t  where 1=1";
/*     */ 

/*     */       
/*  89 */       if (nombreNivel.length() > 0) {
/*  90 */         s = s + " and upper(t.nombre_nivel) like upper('%" + nombreNivel + "%')";
/*     */       }
/*  92 */       if (idPlanDesarrollo > 0) {
/*  93 */         s = s + " and t.id_plan_desarrollo=" + idPlanDesarrollo;
/*     */       }
/*     */ 
/*     */       
/*  97 */       s = s + " order by 1";
/*  98 */       boolean rtaDB = dat.parseSql(s);
/*  99 */       if (!rtaDB) {
/* 100 */         return resultados;
/*     */       }
/* 102 */       ResultSet rs = dat.getResultSet();
/* 103 */       while (rs.next()) {
/* 104 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 107 */     catch (Exception e) {
/* 108 */       e.printStackTrace();
/* 109 */       Utilidades.writeError("PdeNivelPlanDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 112 */       dat.close();
/*     */     } 
/* 114 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int cargarPrimerNivel(int idPlanDesarrollo) {
/* 125 */    DBManager dat = new DBManager();
/*     */     try {
/* 127 */       String s = "select id_nivel from pde_nivel_plan where id_plan_desarrollo=" + idPlanDesarrollo + " and nivel_superior IS NULL ";
/* 128 */       boolean rtaDB = dat.parseSql(s);
/* 129 */       if (!rtaDB) {
/* 130 */         return 0;
/*     */       }
/* 132 */       ResultSet rs = dat.getResultSet();
/* 133 */       if (rs.next()) {
/* 134 */         s = rs.getString(1);
/* 135 */         if (!rs.wasNull()) {
/* 136 */           return Integer.parseInt(s);
/*     */         }
/*     */       }
/*     */     
/* 140 */     } catch (Exception e) {
/* 141 */       e.printStackTrace();
/* 142 */       Utilidades.writeError("PdeNivelPlanDAO:cargarPrimerNivel", e);
/*     */     } finally {
/*     */       
/* 145 */       dat.close();
/*     */     } 
/* 147 */     return 0;
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
/*     */   public PdeNivelPlanDTO cargarUltimoNivel(int idPlanDesarrollo) {
/* 162 */    DBManager dat = new DBManager();
/*     */     try {
/* 164 */       String s = "WITH niveluno AS (\tselect * from pde_nivel_plan where id_plan_desarrollo=" + idPlanDesarrollo + "     ), niveldos AS (" + "        select * from pde_nivel_plan where id_plan_desarrollo=" + idPlanDesarrollo + "     )" + "select * from niveluno a left join niveldos b on " + "(a.id_nivel=b.nivel_superior) " + "where b.nivel_superior IS NULL;";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 173 */       boolean rtaDB = dat.parseSql(s);
/* 174 */       if (!rtaDB) {
/* 175 */         return null;
/*     */       }
/* 177 */       ResultSet rs = dat.getResultSet();
/* 178 */       if (rs.next()) {
/* 179 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 182 */     catch (Exception e) {
/* 183 */       e.printStackTrace();
/* 184 */       Utilidades.writeError("PdeNivelPlanDAO:cargarPdeNivelPlan", e);
/*     */     } finally {
/*     */       
/* 187 */       dat.close();
/*     */     } 
/* 189 */     return null;
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
/*     */   public PdeNivelPlanDTO cargarRegistro(int idNivel, int idPlanDesarrollo, int nivelSuperior) {
/* 203 */   DBManager  dat = new DBManager();
/*     */     try {
/* 205 */       String s = "select t.id_nivel,t.id_plan_desarrollo,t.nivel_superior,t.nombre_nivel,t.tipo_nivel,t.objetivo_general,t.objetivo_especifico,t.metas_general,t.metas_especifico,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from pde_nivel_plan t  where 1=1";
/*     */ 

/*     */       
/* 221 */       if (idNivel > 0) {
/* 222 */         s = s + " and t.id_nivel=" + idNivel;
/*     */       }
/* 224 */       if (idPlanDesarrollo > 0) {
/* 225 */         s = s + " and t.id_plan_desarrollo=" + idPlanDesarrollo;
/*     */       }
/* 227 */       if (nivelSuperior > 0) {
/* 228 */         s = s + " and t.nivel_superior=" + nivelSuperior;
/*     */       }
/* 230 */       s = s + " order by t.id_nivel";
/* 231 */       boolean rtaDB = dat.parseSql(s);
/* 232 */       if (!rtaDB) {
/* 233 */         return null;
/*     */       }
/* 235 */       ResultSet rs = dat.getResultSet();
/* 236 */       if (rs.next()) {
/* 237 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 240 */     catch (Exception e) {
/* 241 */       e.printStackTrace();
/* 242 */       Utilidades.writeError("PdeNivelPlanDAO:cargarPdeNivelPlan", e);
/*     */     } finally {
/*     */       
/* 245 */       dat.close();
/*     */     } 
/* 247 */     return null;
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
/*     */   public boolean ultimoNivel(int idNivel) {
/* 260 */   DBManager  dat = new DBManager();
/*     */     try {
/* 262 */       String s = "select * from pde_nivel_plan where nivel_superior =" + idNivel;
/*     */       
/* 264 */       boolean rtaDB = dat.parseSql(s);
/* 265 */       if (!rtaDB) {
/* 266 */         return true;
/*     */       }
/* 268 */       ResultSet rs = dat.getResultSet();
/* 269 */       if (rs.next()) {
/* 270 */         return false;
/*     */       }
/*     */     }
/* 273 */     catch (Exception e) {
/* 274 */       e.printStackTrace();
/* 275 */       Utilidades.writeError("PdeNivelPlanDAO:ultimoNivel", e);
/*     */     } finally {
/*     */       
/* 278 */       dat.close();
/*     */     } 
/* 280 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 290 */     int inumero = 1;
/* 291 */     String s = "select max(id_nivel) from pde_nivel_plan ";
/*     */ 
/*     */     
/* 294 */   DBManager  dat = new DBManager();
/*     */     try {
/* 296 */       boolean rta = dat.parseSql(s);
/* 297 */       if (!rta) return 0; 
/* 298 */       ResultSet rs = dat.getResultSet();
/* 299 */       if (rs.next()) {
/* 300 */         s = rs.getString(1);
/* 301 */         if (!rs.wasNull()) {
/* 302 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 305 */       return inumero;
/*     */     }
/* 307 */     catch (Exception e) {
/* 308 */       e.printStackTrace();
/* 309 */       Utilidades.writeError("PdeNivelPlanDAO:siguienteRegistro ", e);
/*     */     } finally {
/*     */       
/* 312 */       dat.close();
/*     */     } 
/* 314 */     return 0;
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
/*     */   public RespuestaBD eliminarRegistro(int idNivel, int idPlanDesarrollo, int nivelSuperior) {
/* 326 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 328 */   DBManager  dat = new DBManager();
/*     */     try {
/* 330 */       String s = "delete from pde_nivel_plan where  id_nivel=" + idNivel + "  and id_plan_desarrollo=" + idPlanDesarrollo + "  and nivel_superior=" + nivelSuperior + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 336 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 338 */     catch (Exception e) {
/* 339 */       e.printStackTrace();
/* 340 */       Utilidades.writeError("PdeNivelPlanDAO:eliminarRegistro ", e);
/* 341 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 344 */       dat.close();
/*     */     } 
/* 346 */     return rta;
/*     */   }
/*     */ 

/*     */   
/*     */   public RespuestaBD crearRegistro(int idNivel, int idPlanDesarrollo, int nivelSuperior, String nombreNivel, int tipoNivel, String objetivoGeneral, String objetivoEspecifico, String metasGeneral, String metasEspecifico, String usuarioInsercion) {
/* 366 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 368 */     int elSiguiente = siguienteRegistro();
/* 369 */     if (elSiguiente == 0) {
/* 370 */       rta.setMensaje("Generando secuencia");
/* 371 */       return rta;
/*     */     } 
/*     */     
/* 374 */   DBManager  dat = new DBManager();
/*     */     try {
/* 376 */       String s = "insert into pde_nivel_plan(id_nivel,id_plan_desarrollo,nivel_superior,nombre_nivel,tipo_nivel,objetivo_general,objetivo_especifico,metas_general,metas_especifico,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "" + idPlanDesarrollo + "," + "" + (elSiguiente - 1) + "," + "'" + nombreNivel + "'," + "" + tipoNivel + "," + "'" + objetivoGeneral + "'," + "'" + objetivoEspecifico + "'," + "'" + metasGeneral + "'," + "'" + metasEspecifico + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 

/*     */       
/* 401 */       rta = dat.executeUpdate2(s);
/* 402 */       rta.setSecuencia(elSiguiente);
/*     */     
/*     */     }
/* 405 */     catch (Exception e) {
/* 406 */       e.printStackTrace();
/* 407 */       Utilidades.writeError("%PdeNivelPlanDAO:crearRegistro ", e);
/* 408 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 411 */       dat.close();
/*     */     } 
/* 413 */     return rta;
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
/*     */   public RespuestaBD crearRegistro2(int idNivel, int idPlanDesarrollo, String nombreNivel, int tipoNivel, String objetivoGeneral, String objetivoEspecifico, String metasGeneral, String metasEspecifico, String usuarioInsercion) {
/* 433 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 435 */     int elSiguiente = siguienteRegistro();
/* 436 */     if (elSiguiente == 0) {
/* 437 */       rta.setMensaje("Generando secuencia");
/* 438 */       return rta;
/*     */     } 
/*     */     
/* 441 */    DBManager dat = new DBManager();
/*     */     try {
/* 443 */       String s = "insert into pde_nivel_plan(id_nivel,id_plan_desarrollo,nombre_nivel,tipo_nivel,objetivo_general,objetivo_especifico,metas_general,metas_especifico,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "" + idPlanDesarrollo + "," + "'" + nombreNivel + "'," + "" + tipoNivel + "," + "'" + objetivoGeneral + "'," + "'" + objetivoEspecifico + "'," + "'" + metasGeneral + "'," + "'" + metasEspecifico + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";

/*     */       
/* 466 */       rta = dat.executeUpdate2(s);
/* 467 */       rta.setSecuencia(elSiguiente);
/*     */     
/*     */     }
/* 470 */     catch (Exception e) {
/* 471 */       e.printStackTrace();
/* 472 */       Utilidades.writeError("%PdeNivelPlanDAO:crearRegistro ", e);
/* 473 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 476 */       dat.close();
/*     */     } 
/* 478 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int idPlanDesarrollo, String nombreNivel, String objetivoGeneral, String objetivoEspecifico, String metasGeneral, String metasEspecifico, String usuarioModificacion) {
/* 495 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 497 */   DBManager  dat = new DBManager();
/*     */     try {
/* 499 */       String s = "update pde_nivel_plan set  nombre_nivel='" + nombreNivel + "'," + " objetivo_general='" + objetivoGeneral + "'," + " objetivo_especifico='" + objetivoEspecifico + "'," + " metas_general='" + metasGeneral + "'," + " metas_especifico='" + metasEspecifico + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " id_plan_desarrollo=" + idPlanDesarrollo + "";

/*     */       
/* 510 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 512 */     catch (Exception e) {
/* 513 */       e.printStackTrace();
/* 514 */       Utilidades.writeError("PdeNivelPlanDAO:modificarRegistro ", e);
/* 515 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 518 */       dat.close();
/*     */     } 
/* 520 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PdeNivelPlanDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */