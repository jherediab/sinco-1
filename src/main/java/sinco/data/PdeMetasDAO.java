/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PdeMetasDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PdeMetasDAO;

/*     */ public class PdeMetasDAO
/*     */ {
/*     */   public PdeMetasDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  35 */       PdeMetasDTO reg = new PdeMetasDTO();
/*     */       
/*  37 */       reg.setIdMeta(rs.getInt("id_meta"));
/*  38 */       reg.setCodigoMeta(rs.getString("codigo_meta"));
/*  39 */       reg.setIdUnidadNivel(rs.getInt("id_unidad_nivel"));
/*  40 */       reg.setIdObjetivoEspecifico(rs.getInt("id_objetivo_especifico"));
/*  41 */       reg.setCodigoUnidad(rs.getString("codigo_unidad"));
/*  42 */       reg.setNombreUnidad(rs.getString("nombre_unidad"));
/*  43 */       reg.setNombreMeta(rs.getString("nombre_meta"));
/*  44 */       reg.setTipoMeta(rs.getString("tipo_meta"));
/*  45 */       reg.setNombreTipoMeta(rs.getString("nombre_tipo_meta"));
/*  46 */       reg.setCantidad(rs.getString("cantidad"));
/*  47 */       reg.setLineaBase(rs.getString("linea_base"));
/*  48 */       reg.setIndicador(rs.getString("indicador"));
/*  49 */       reg.setProceso(rs.getString("proceso"));
/*  50 */       reg.setNombreProceso(rs.getString("nombre_proceso"));
/*  51 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  52 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  53 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  54 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  55 */       return reg;
/*     */     }
/*  57 */     catch (Exception e) {
/*  58 */       e.printStackTrace();
/*  59 */       Utilidades.writeError("PdeMetasDAO:leerRegistro ", e);
/*     */       
/*  61 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<PdeMetasDTO> cargarMetasUltimosNiveles(int idNivel) {
/*  72 */     Collection<PdeMetasDTO> resultados = new ArrayList<PdeMetasDTO>();
/*     */     
/*  74 */    DBManager dat = new DBManager();
/*     */     try {
/*  76 */       String s = "select u.codigo_unidad, u.nombre_unidad, m.codigo_meta, m.nombre_meta, m.linea_base, m.cantidad, m.indicador from pde_metas m, pde_unidad_nivel u where u.id_nivel=" + idNivel + " and u.id_unidad_nivel=m.id_unidad_nivel order by 1,3";
/*     */       
/*  78 */       boolean rtaDB = dat.parseSql(s);
/*  79 */       if (!rtaDB) {
/*  80 */         return resultados;
/*     */       }
/*  82 */       ResultSet rs = dat.getResultSet();
/*  83 */       while (rs.next()) {
/*  84 */         PdeMetasDTO reg = new PdeMetasDTO();
/*  85 */         reg.setCodigoUnidad(rs.getString("codigo_unidad"));
/*  86 */         reg.setNombreUnidad(rs.getString("nombre_unidad"));
/*  87 */         reg.setCodigoMeta(rs.getString("codigo_meta"));
/*  88 */         reg.setNombreMeta(rs.getString("nombre_meta"));
/*  89 */         reg.setLineaBase(rs.getString("linea_base"));
/*  90 */         reg.setCantidad(rs.getString("cantidad"));
/*  91 */         reg.setIndicador(rs.getString("indicador"));
/*  92 */         resultados.add(reg);
/*     */       }
/*     */     
/*  95 */     } catch (Exception e) {
/*  96 */       e.printStackTrace();
/*  97 */       Utilidades.writeError("PdeMetasDAO:cargarMetasUltimosNiveles ", e);
/*     */     } finally {
/*     */       
/* 100 */       dat.close();
/*     */     } 
/* 102 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<PdeMetasDTO> cargarTodos(int idUnidadNivel, int idObjetivoEspecifico) {
/* 113 */     Collection<PdeMetasDTO> resultados = new ArrayList<PdeMetasDTO>();
/*     */     
/* 115 */    DBManager dat = new DBManager();
/*     */     try {
/* 117 */       String s = "select t.id_meta,t.codigo_meta,t.id_unidad_nivel,t.id_objetivo_especifico,t.codigo_unidad,R1.nombre_unidad,t.nombre_meta,t.tipo_meta,m1.descripcion as nombre_tipo_meta,t.cantidad,t.linea_base,t.indicador,t.proceso,m2.descripcion as nombre_proceso,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from pde_metas t  LEFT JOIN sis_unidades_medida R1 on(R1.codigo_unidad=t.codigo_unidad) left join sis_multivalores m1 on (m1.tabla='TIPO_META' and m1.VALOR=t.tipo_meta) left join procesos m2 on (m2.codigo=t.proceso) where 1=1";
/*     */ 

/*     */ 
/*     */       
/* 141 */       if (idUnidadNivel > 0) {
/* 142 */         s = s + " and t.id_unidad_nivel=" + idUnidadNivel;
/*     */       }
/* 144 */       if (idObjetivoEspecifico > 0) {
/* 145 */         s = s + " and t.id_objetivo_especifico=" + idObjetivoEspecifico;
/*     */       }
/* 147 */       s = s + " order by 1";
/* 148 */       boolean rtaDB = dat.parseSql(s);
/* 149 */       if (!rtaDB) {
/* 150 */         return resultados;
/*     */       }
/* 152 */       ResultSet rs = dat.getResultSet();
/* 153 */       while (rs.next()) {
/* 154 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 157 */     catch (Exception e) {
/* 158 */       e.printStackTrace();
/* 159 */       Utilidades.writeError("PdeMetasDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 162 */       dat.close();
/*     */     } 
/* 164 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PdeMetasDTO cargarRegistro(int idMeta) {
/* 174 */   DBManager  dat = new DBManager();
/*     */     try {
/* 176 */       String s = "select t.id_meta,t.codigo_meta,t.id_unidad_nivel,t.id_objetivo_especifico,t.codigo_unidad,t.tipo_meta,m1.descripcion as nombre_tipo_meta,R1.nombre_unidad,t.nombre_meta,t.cantidad,t.linea_base,t.indicador,t.proceso,m2.descripcion as nombre_proceso,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from pde_metas t  LEFT JOIN sis_unidades_medida R1 on(R1.codigo_unidad=t.codigo_unidad) left join sis_multivalores m1 on (m1.tabla='TIPO_META' and m1.VALOR=t.tipo_meta) left join procesos m2 on (m2.codigo=t.proceso) where  t.id_meta=" + idMeta + "";
/*     */ 

/*     */       
/* 202 */       boolean rtaDB = dat.parseSql(s);
/* 203 */       if (!rtaDB) {
/* 204 */         return null;
/*     */       }
/* 206 */       ResultSet rs = dat.getResultSet();
/* 207 */       if (rs.next()) {
/* 208 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 211 */     catch (Exception e) {
/* 212 */       e.printStackTrace();
/* 213 */       Utilidades.writeError("PdeMetasDAO:cargarPdeMetas", e);
/*     */     } finally {
/*     */       
/* 216 */       dat.close();
/*     */     } 
/* 218 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 227 */     int inumero = 1;
/* 228 */     String s = "select max(id_meta) from pde_metas ";
/*     */ 
/*     */     
/* 231 */    DBManager dat = new DBManager();
/*     */     try {
/* 233 */       boolean rta = dat.parseSql(s);
/* 234 */       if (!rta) return 0; 
/* 235 */       ResultSet rs = dat.getResultSet();
/* 236 */       if (rs.next()) {
/* 237 */         s = rs.getString(1);
/* 238 */         if (!rs.wasNull()) {
/* 239 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 242 */       return inumero;
/*     */     }
/* 244 */     catch (Exception e) {
/* 245 */       e.printStackTrace();
/* 246 */       Utilidades.writeError("PdeMetasDAO:siguienteRegistro ", e);
/*     */     } finally {
/*     */       
/* 249 */       dat.close();
/*     */     } 
/* 251 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int idMeta) {
/* 261 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 263 */   DBManager  dat = new DBManager();
/*     */     try {
/* 265 */       String s = "delete from pde_metas where  id_meta=" + idMeta;
/*     */ 
/*     */       
/* 268 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 270 */     catch (Exception e) {
/* 271 */       e.printStackTrace();
/* 272 */       Utilidades.writeError("PdeMetasDAO:eliminarRegistro ", e);
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
/*     */   public RespuestaBD eliminarResponsables(int idMeta) {
/* 289 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 291 */  DBManager   dat = new DBManager();
/*     */     try {
/* 293 */       String s = "delete from pde_metas_responsables where  id_meta=" + idMeta;
/*     */ 
/*     */       
/* 296 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 298 */     catch (Exception e) {
/* 299 */       e.printStackTrace();
/* 300 */       Utilidades.writeError("PdeMetasDAO:eliminarResponsables ", e);
/* 301 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 304 */       dat.close();
/*     */     } 
/* 306 */     return rta;
/*     */   }
/*     */ 

/*     */   
/*     */   public RespuestaBD crearRegistro(int idMeta, String codigoMeta, String tipoMeta, int idUnidadNivel, int idObjetivoEspecifico, String proceso, String codigoUnidad, String nombreMeta, String cantidad, String lineaBase, String indicador, String usuarioInsercion) {
/* 329 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 331 */     int elSiguiente = siguienteRegistro();
/* 332 */     if (elSiguiente == 0) {
/* 333 */       rta.setMensaje("Generando secuencia");
/* 334 */       return rta;
/*     */     } 
/*     */     
/* 337 */    DBManager dat = new DBManager();
/*     */     
/*     */     try {
/* 340 */       if (idObjetivoEspecifico > 0) {
/* 341 */         if (proceso.length() > 0) {
/* 342 */           String s = "insert into pde_metas(id_meta,codigo_meta,tipo_meta,id_objetivo_especifico,proceso,codigo_unidad,nombre_meta,cantidad,linea_base,indicador,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "'" + codigoMeta + "'," + "'" + tipoMeta + "'," + "" + idObjetivoEspecifico + "," + "'" + proceso + "'," + "'" + codigoUnidad + "'," + "'" + nombreMeta + "'," + "'" + cantidad + "'," + "'" + lineaBase + "'," + "'" + indicador + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 

/*     */           
/* 369 */           rta = dat.executeUpdate2(s);
/* 370 */           rta.setSecuencia(elSiguiente);
/*     */         } else {
/* 372 */           String s = "insert into pde_metas(id_meta,codigo_meta,tipo_meta,id_objetivo_especifico,codigo_unidad,nombre_meta,cantidad,linea_base,indicador,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "'" + codigoMeta + "'," + "'" + tipoMeta + "'," + "" + idObjetivoEspecifico + "," + "'" + codigoUnidad + "'," + "'" + nombreMeta + "'," + "'" + cantidad + "'," + "'" + lineaBase + "'," + "'" + indicador + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 

/*     */           
/* 397 */           rta = dat.executeUpdate2(s);
/* 398 */           rta.setSecuencia(elSiguiente);
/*     */         
/*     */         }
/*     */       
/*     */       }
/* 403 */       else if (proceso.length() > 0) {
/* 404 */         String s = "insert into pde_metas(id_meta,codigo_meta,tipo_meta,id_unidad_nivel,codigo_unidad,nombre_meta,proceso,cantidad,linea_base,indicador,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "'" + codigoMeta + "'," + "'" + tipoMeta + "'," + "" + idUnidadNivel + "," + "'" + codigoUnidad + "'," + "'" + nombreMeta + "'," + "'" + proceso + "'," + "'" + cantidad + "'," + "'" + lineaBase + "'," + "'" + indicador + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 

/*     */         
/* 431 */         rta = dat.executeUpdate2(s);
/* 432 */         rta.setSecuencia(elSiguiente);
/*     */       } else {
/* 434 */         String s = "insert into pde_metas(id_meta,codigo_meta,tipo_meta,id_unidad_nivel,codigo_unidad,nombre_meta,cantidad,linea_base,indicador,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "'" + codigoMeta + "'," + "'" + tipoMeta + "'," + "" + idUnidadNivel + "," + "'" + codigoUnidad + "'," + "'" + nombreMeta + "'," + "'" + cantidad + "'," + "'" + lineaBase + "'," + "'" + indicador + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 

/*     */ 
/*     */         
/* 459 */         rta = dat.executeUpdate2(s);
/* 460 */         rta.setSecuencia(elSiguiente);
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 466 */     catch (Exception e) {
/* 467 */       e.printStackTrace();
/* 468 */       Utilidades.writeError("%PdeMetasDAO:crearRegistro ", e);
/* 469 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 472 */       dat.close();
/*     */     } 
/* 474 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int idMeta, String codigoMeta, String tipoMeta, int idUnidadNivel, int idObjetivoEspecifico, String proceso, String codigoUnidad, String nombreMeta, String cantidad, String lineaBase, String indicador, String usuarioModificacion) {
/* 496 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 498 */    DBManager dat = new DBManager();
/*     */     try {
/* 500 */       String s = "update pde_metas set  codigo_meta='" + codigoMeta + "'," + " tipo_meta='" + tipoMeta + "'," + " codigo_unidad='" + codigoUnidad + "'," + " nombre_meta='" + nombreMeta + "'," + " proceso='" + proceso + "'," + " cantidad='" + cantidad + "'," + " linea_base='" + lineaBase + "'," + " indicador='" + indicador + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " id_meta=" + idMeta + "";
/*     */ 

/*     */ 
/*     */       
/* 514 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 516 */     catch (Exception e) {
/* 517 */       e.printStackTrace();
/* 518 */       Utilidades.writeError("PdeMetasDAO:modificarRegistro ", e);
/* 519 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 522 */       dat.close();
/*     */     } 
/* 524 */     return rta;
/*     */   }
/*     */ 

/*     */   
/*     */   public RespuestaBD crearResponsable(int idMeta, String cargo, String usuarioInsercion) {
/* 536 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 538 */    DBManager dat = new DBManager();
/*     */     try {
/* 540 */       String s = "insert into pde_metas_responsables (id_meta,codigo_cargo,fecha_insercion,usuario_insercion)";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 545 */       s = s + " values (";
/* 546 */       s = s + "" + idMeta + ",";
/* 547 */       s = s + "'" + cargo + "',";
/* 548 */       s = s + "" + Utilidades.getFechaBD() + ",";
/* 549 */       s = s + "'" + usuarioInsercion + "'";
/* 550 */       s = s + ")";
/* 551 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 553 */     catch (Exception e) {
/* 554 */       e.printStackTrace();
/* 555 */       Utilidades.writeError("PdeMetasDAO:crearResponsable ", e);
/* 556 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 559 */       dat.close();
/*     */     } 
/* 561 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PdeMetasDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */