/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PdeObjetivosEspecificosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PdeObjetivosEspecificosDAO;
/*     */ 

/*     */ 
/*     */ public class PdeObjetivosEspecificosDAO
/*     */ {
/*     */   public PdeObjetivosEspecificosDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  35 */       PdeObjetivosEspecificosDTO reg = new PdeObjetivosEspecificosDTO();
/*     */       
/*  37 */       reg.setIdObjetivoEspecifico(rs.getInt("id_objetivo_especifico"));
/*  38 */       reg.setCodigoObjetivoEspecifico(rs.getString("codigo_objetivo_especifico"));
/*  39 */       reg.setIdUnidadNivel(rs.getInt("id_unidad_nivel"));
/*  40 */       reg.setDescripcionObjetivoEspecifico(rs.getString("descripcion_objetivo_especifico"));
/*  41 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  42 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  43 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  44 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  45 */       return reg;
/*     */     }
/*  47 */     catch (Exception e) {
/*  48 */       e.printStackTrace();
/*  49 */       Utilidades.writeError("PdeObjetivosEspecificosDAO:leerRegistro ", e);
/*     */       
/*  51 */       return null;
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
/*     */   public Collection<PdeObjetivosEspecificosDTO> cargarTodos(String codigoObjetivoEspecifico, int idUnidadNivel, String descripcionObjetivoEspecifico) {
/*  63 */     Collection<PdeObjetivosEspecificosDTO> resultados = new ArrayList<PdeObjetivosEspecificosDTO>();
/*     */     
/*  65 */    DBManager dat = new DBManager();
/*     */     try {
/*  67 */       String s = "select t.id_objetivo_especifico,t.codigo_objetivo_especifico,t.id_unidad_nivel,t.descripcion_objetivo_especifico,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from pde_objetivos_especificos t  where 1=1";

/*     */       
/*  81 */       if (idUnidadNivel > 0) {
/*  82 */         s = s + " and t.id_unidad_nivel=" + idUnidadNivel;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*  87 */       s = s + " order by 1";
/*  88 */       boolean rtaDB = dat.parseSql(s);
/*  89 */       if (!rtaDB) {
/*  90 */         return resultados;
/*     */       }
/*  92 */       ResultSet rs = dat.getResultSet();
/*  93 */       while (rs.next()) {
/*  94 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/*  97 */     catch (Exception e) {
/*  98 */       e.printStackTrace();
/*  99 */       Utilidades.writeError("PdeObjetivosEspecificosDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 102 */       dat.close();
/*     */     } 
/* 104 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PdeObjetivosEspecificosDTO cargarRegistro(int idObjetivoEspecifico, int idUnidadNivel) {
/* 115 */    DBManager dat = new DBManager();
/*     */     try {
/* 117 */       String s = "select t.id_objetivo_especifico,t.codigo_objetivo_especifico,t.id_unidad_nivel,t.descripcion_objetivo_especifico,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from pde_objetivos_especificos t  where  t.id_objetivo_especifico=" + idObjetivoEspecifico;

/* 130 */       if (idUnidadNivel > 0) {
/* 131 */         s = s + " and t.id_unidad_nivel=" + idUnidadNivel;
/*     */       }
/*     */ 
/*     */       
/* 135 */       boolean rtaDB = dat.parseSql(s);
/* 136 */       if (!rtaDB) {
/* 137 */         return null;
/*     */       }
/* 139 */       ResultSet rs = dat.getResultSet();
/* 140 */       if (rs.next()) {
/* 141 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 144 */     catch (Exception e) {
/* 145 */       e.printStackTrace();
/* 146 */       Utilidades.writeError("PdeObjetivosEspecificosDAO:cargarPdeObjetivosEspecificos", e);
/*     */     } finally {
/*     */       
/* 149 */       dat.close();
/*     */     } 
/* 151 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 160 */     int inumero = 1;
/* 161 */     String s = "select max(id_objetivo_especifico) from pde_objetivos_especificos ";
/*     */ 
/*     */     
/* 164 */  DBManager  dat = new DBManager();
/*     */     try {
/* 166 */       boolean rta = dat.parseSql(s);
/* 167 */       if (!rta) return 0; 
/* 168 */       ResultSet rs = dat.getResultSet();
/* 169 */       if (rs.next()) {
/* 170 */         s = rs.getString(1);
/* 171 */         if (!rs.wasNull()) {
/* 172 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 175 */       return inumero;
/*     */     }
/* 177 */     catch (Exception e) {
/* 178 */       e.printStackTrace();
/* 179 */       Utilidades.writeError("PdeObjetivosEspecificosDAO:siguienteRegistro ", e);
/*     */     } finally {
/*     */       
/* 182 */       dat.close();
/*     */     } 
/* 184 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int idObjetivoEspecifico) {
/* 194 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 196 */   DBManager  dat = new DBManager();
/*     */     try {
/* 198 */       String s = "delete from pde_objetivos_especificos where  id_objetivo_especifico=" + idObjetivoEspecifico + "";
/*     */ 
/*     */ 
/*     */       
/* 202 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 204 */     catch (Exception e) {
/* 205 */       e.printStackTrace();
/* 206 */       Utilidades.writeError("PdeObjetivosEspecificosDAO:eliminarRegistro ", e);
/* 207 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 210 */       dat.close();
/*     */     } 
/* 212 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int idObjetivoEspecifico, String codigoObjetivoEspecifico, int idUnidadNivel, String descripcionObjetivoEspecifico, String usuarioInsercion) {
/* 227 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 229 */     int elSiguiente = siguienteRegistro();
/* 230 */     if (elSiguiente == 0) {
/* 231 */       rta.setMensaje("Generando secuencia");
/* 232 */       return rta;
/*     */     } 
/*     */     
/* 235 */    DBManager dat = new DBManager();
/*     */     try {
/* 237 */       String s = "insert into pde_objetivos_especificos(id_objetivo_especifico,codigo_objetivo_especifico,id_unidad_nivel,descripcion_objetivo_especifico,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "'" + codigoObjetivoEspecifico + "'," + "" + idUnidadNivel + "," + "'" + descripcionObjetivoEspecifico + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";

/*     */       
/* 252 */       rta = dat.executeUpdate2(s);
/* 253 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 255 */     catch (Exception e) {
/* 256 */       e.printStackTrace();
/* 257 */       Utilidades.writeError("%PdeObjetivosEspecificosDAO:crearRegistro ", e);
/* 258 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 261 */       dat.close();
/*     */     } 
/* 263 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int idObjetivoEspecifico, String codigoObjetivoEspecifico, int idUnidadNivel, String descripcionObjetivoEspecifico, String usuarioModificacion) {
/* 278 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 280 */  DBManager   dat = new DBManager();
/*     */     try {
/* 282 */       String s = "update pde_objetivos_especificos set  codigo_objetivo_especifico='" + codigoObjetivoEspecifico + "'," + " descripcion_objetivo_especifico='" + descripcionObjetivoEspecifico + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " id_objetivo_especifico=" + idObjetivoEspecifico + " and id_unidad_nivel=" + idUnidadNivel + "";

/*     */       
/* 291 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 293 */     catch (Exception e) {
/* 294 */       e.printStackTrace();
/* 295 */       Utilidades.writeError("PdeObjetivosEspecificosDAO:modificarRegistro ", e);
/* 296 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 299 */       dat.close();
/*     */     } 
/* 301 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PdeObjetivosEspecificosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */