/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.AudCriteriosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AudCriteriosDAO;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AudCriteriosDAO
/*     */ {
/*     */   public AudCriteriosDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  35 */       AudCriteriosDTO reg = new AudCriteriosDTO();
/*     */       
/*  37 */       reg.setCriterio(rs.getString("criterio"));
/*  38 */       reg.setDescripcion(rs.getString("descripcion"));
/*  39 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  40 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  41 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  42 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  43 */       return reg;
/*     */     }
/*  45 */     catch (Exception e) {
/*  46 */       e.printStackTrace();
/*  47 */       Utilidades.writeError("AudCriteriosDAO:leerRegistro ", e);
/*     */       
/*  49 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<AudCriteriosDTO> cargarTodos(String descripcion) {
/*  59 */     Collection<AudCriteriosDTO> resultados = new ArrayList<AudCriteriosDTO>();
/*     */     
/*  61 */   DBManager  dat = new DBManager();
/*     */     try {
/*  63 */       String s = "select t.criterio,t.descripcion,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from aud_criterios t  where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  72 */       if (descripcion.length() > 0) {
/*  73 */         s = s + " and upper(t.descripcion) like upper('%" + descripcion + "%')";
/*     */       }
/*  75 */       s = s + " order by 1";
/*  76 */       boolean rtaDB = dat.parseSql(s);
/*  77 */       if (!rtaDB) {
/*  78 */         return resultados;
/*     */       }
/*  80 */       ResultSet rs = dat.getResultSet();
/*  81 */       while (rs.next()) {
/*  82 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/*  85 */     catch (Exception e) {
/*  86 */       e.printStackTrace();
/*  87 */       Utilidades.writeError("AudCriteriosDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/*  90 */       dat.close();
/*     */     } 
/*  92 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AudCriteriosDTO cargarRegistro(String criterio) {
/* 102 */   DBManager  dat = new DBManager();
/*     */     try {
/* 104 */       String s = "select t.criterio,t.descripcion,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from aud_criterios t  where  t.criterio='" + criterio + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 115 */       boolean rtaDB = dat.parseSql(s);
/* 116 */       if (!rtaDB) {
/* 117 */         return null;
/*     */       }
/* 119 */       ResultSet rs = dat.getResultSet();
/* 120 */       if (rs.next()) {
/* 121 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 124 */     catch (Exception e) {
/* 125 */       e.printStackTrace();
/* 126 */       Utilidades.writeError("AudCriteriosDAO:cargarAudCriterios", e);
/*     */     } finally {
/*     */       
/* 129 */       dat.close();
/*     */     } 
/* 131 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(String criterio) {
/* 141 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 143 */    DBManager dat = new DBManager();
/*     */     try {
/* 145 */       String s = "delete from aud_criterios where  criterio='" + criterio + "'" + "";
/*     */ 
/*     */ 
/*     */       
/* 149 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 151 */     catch (Exception e) {
/* 152 */       e.printStackTrace();
/* 153 */       Utilidades.writeError("AudCriteriosDAO:eliminarRegistro ", e);
/* 154 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 157 */       dat.close();
/*     */     } 
/* 159 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(String criterio, String descripcion, String usuarioInsercion) {
/* 172 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 174 */  DBManager   dat = new DBManager();
/*     */     try {
/* 176 */       String s = "insert into aud_criterios(criterio,descripcion,fecha_insercion,usuario_insercion) values ('" + criterio + "'," + "'" + descripcion + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 187 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 189 */     catch (Exception e) {
/* 190 */       e.printStackTrace();
/* 191 */       Utilidades.writeError("%AudCriteriosDAO:crearRegistro ", e);
/* 192 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 195 */       dat.close();
/*     */     } 
/* 197 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(String criterio, String descripcion, String usuarioModificacion) {
/* 210 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 212 */   DBManager  dat = new DBManager();
/*     */     try {
/* 214 */       String s = "update aud_criterios set  descripcion='" + descripcion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " criterio='" + criterio + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 221 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 223 */     catch (Exception e) {
/* 224 */       e.printStackTrace();
/* 225 */       Utilidades.writeError("AudCriteriosDAO:modificarRegistro ", e);
/* 226 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 229 */       dat.close();
/*     */     } 
/* 231 */     return rta;
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
/*     */   public Collection<AudCriteriosDTO> cargarParaAsignar(int ciclo, int bloque) {
/* 243 */     Collection<AudCriteriosDTO> resultados = new ArrayList<AudCriteriosDTO>();
/*     */     
/* 245 */   DBManager  dat = new DBManager();
/*     */     try {
/* 247 */       String s = " select c.Criterio, \t\t c.Descripcion, \t\t 'S' Existe from   Aud_Bloques_Criterio Bc, \t\t Aud_Criterios        c where  Bc.Ciclo = " + ciclo + " \t\t and Bc.Bloque = " + bloque + " \t\t and Bc.Criterio = c.Criterio" + " union" + " select c.Criterio," + " \t\t c.Descripcion," + " \t\t 'N' Existe" + " from   Aud_Criterios c" + " where  not exists (select 'X'" + " \t\t  from   Aud_Bloques_Criterio Bc" + " \t\t  where  Bc.Ciclo = " + ciclo + " \t\t\t\t\tand Bc.Bloque = " + bloque + " \t\t\t\t\tand Bc.Criterio = c.Criterio)" + " ORDER BY 3 DESC,1";
/*     */ 
/*     */ 

/*     */ 
/*     */       
/* 268 */       boolean rtaDB = dat.parseSql(s);
/* 269 */       if (!rtaDB) {
/* 270 */         return resultados;
/*     */       }
/*     */ 
/*     */       
/* 274 */       ResultSet rs = dat.getResultSet();
/* 275 */       while (rs.next()) {
/* 276 */         AudCriteriosDTO reg = new AudCriteriosDTO();
/* 277 */         reg.setCriterio(rs.getString("criterio"));
/* 278 */         reg.setDescripcion(rs.getString("descripcion"));
/* 279 */         reg.setExiste(rs.getString("existe"));
/* 280 */         resultados.add(reg);
/*     */       }
/*     */     
/* 283 */     } catch (Exception e) {
/* 284 */       e.printStackTrace();
/* 285 */       Utilidades.writeError("ConsTiposDocumentosDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 288 */       dat.close();
/*     */     } 
/* 290 */     return resultados;
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
/*     */   public Collection<AudCriteriosDTO> cargarAsignados(int ciclo, String codigoPadre, int bloque) {
/* 302 */     Collection<AudCriteriosDTO> resultados = new ArrayList<AudCriteriosDTO>();
/*     */     
/* 304 */    DBManager dat = new DBManager();
/*     */     try {
/* 306 */       String s = " select c.Criterio, \t\t c.Descripcion from   Aud_Bloques_Criterio Bc, \t\t Aud_Criterios        c where  Bc.Ciclo = " + ciclo + " \t\t and Bc.Bloque = " + bloque + " \t\t and Bc.Criterio = c.Criterio" + " ORDER BY 1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 316 */       boolean rtaDB = dat.parseSql(s);
/* 317 */       if (!rtaDB) {
/* 318 */         return resultados;
/*     */       }
/*     */ 
/*     */       
/* 322 */       ResultSet rs = dat.getResultSet();
/* 323 */       while (rs.next()) {
/* 324 */         AudCriteriosDTO reg = new AudCriteriosDTO();
/* 325 */         reg.setCriterio(rs.getString("criterio"));
/* 326 */         reg.setDescripcion(rs.getString("descripcion"));
/* 327 */         resultados.add(reg);
/*     */       }
/*     */     
/* 330 */     } catch (Exception e) {
/* 331 */       e.printStackTrace();
/* 332 */       Utilidades.writeError("ConsTiposDocumentosDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 335 */       dat.close();
/*     */     } 
/* 337 */     return resultados;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\AudCriteriosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */