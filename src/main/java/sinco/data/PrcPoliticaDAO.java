/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PrcPoliticaDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PrcPoliticaDAO;
/*     */ 
 
/*     */ 
/*     */ public class PrcPoliticaDAO
/*     */ {
/*     */   public PrcPoliticaDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  35 */       PrcPoliticaDTO reg = new PrcPoliticaDTO();
/*     */       
/*  37 */       reg.setIdPolitica(rs.getInt("id_politica"));
/*  38 */       reg.setDescripcionPolitica(rs.getString("descripcion_politica"));
/*  39 */       reg.setEstado(rs.getString("estado"));
/*  40 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  41 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  42 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  43 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  44 */       reg.setNombreEstado(rs.getString("nombre_estado"));
/*  45 */       return reg;
/*     */     }
/*  47 */     catch (Exception e) {
/*  48 */       e.printStackTrace();
/*  49 */       Utilidades.writeError("PrcPoliticaDAO:leerRegistro ", e);
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
/*     */   public Collection<PrcPoliticaDTO> cargarTodos(String descripcionPolitica) {
/*  61 */     Collection<PrcPoliticaDTO> resultados = new ArrayList<PrcPoliticaDTO>();
/*     */     
/*  63 */   DBManager  dat = new DBManager();
/*     */     try {
/*  65 */       String s = "select t.id_politica,t.descripcion_politica,t.estado,m1.descripcion as nombre_estado,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from prc_politica t  left join sis_multivalores m1 on (m1.tabla='estado_activo_inactivo' and m1.valor=t.estado) where 1=1";
 
/*  77 */       if (descripcionPolitica.length() > 0) {
/*  78 */         s = s + " and upper(t.descripcion_politica) like upper('%" + descripcionPolitica + "%')";
/*     */       }
/*  80 */       s = s + " order by 1";
/*  81 */       boolean rtaDB = dat.parseSql(s);
/*  82 */       if (!rtaDB) {
/*  83 */         return resultados;
/*     */       }
/*  85 */       ResultSet rs = dat.getResultSet();
/*  86 */       while (rs.next()) {
/*  87 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/*  90 */     catch (Exception e) {
/*  91 */       e.printStackTrace();
/*  92 */       Utilidades.writeError("PrcPoliticaDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/*  95 */       dat.close();
/*     */     } 
/*  97 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PrcPoliticaDTO cargarRegistro(int idPolitica) {
/* 107 */   DBManager  dat = new DBManager();
/*     */     try {
/* 109 */       String s = "select t.id_politica,t.descripcion_politica,t.estado,m1.descripcion as nombre_estado,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from prc_politica t  left join sis_multivalores m1 on (m1.tabla='estado_activo_inactivo' and m1.valor=t.estado) where  t.id_politica=" + idPolitica + "";
/*     */ 
 
/*     */       
/* 123 */       boolean rtaDB = dat.parseSql(s);
/* 124 */       if (!rtaDB) {
/* 125 */         return null;
/*     */       }
/* 127 */       ResultSet rs = dat.getResultSet();
/* 128 */       if (rs.next()) {
/* 129 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 132 */     catch (Exception e) {
/* 133 */       e.printStackTrace();
/* 134 */       Utilidades.writeError("PrcPoliticaDAO:cargarPrcPolitica", e);
/*     */     } finally {
/*     */       
/* 137 */       dat.close();
/*     */     } 
/* 139 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 148 */     int inumero = 1;
/* 149 */     String s = "select max(id_politica) from prc_politica ";
/*     */ 
/*     */     
/* 152 */    DBManager dat = new DBManager();
/*     */     try {
/* 154 */       boolean rta = dat.parseSql(s);
/* 155 */       if (!rta) return 0; 
/* 156 */       ResultSet rs = dat.getResultSet();
/* 157 */       if (rs.next()) {
/* 158 */         s = rs.getString(1);
/* 159 */         if (!rs.wasNull()) {
/* 160 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 163 */       return inumero;
/*     */     }
/* 165 */     catch (Exception e) {
/* 166 */       e.printStackTrace();
/* 167 */       Utilidades.writeError("PrcPoliticaDAO:siguienteRegistro ", e);
/*     */     } finally {
/*     */       
/* 170 */       dat.close();
/*     */     } 
/* 172 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int idPolitica) {
/* 182 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 184 */  DBManager   dat = new DBManager();
/*     */     try {
/* 186 */       String s = "delete from prc_politica where  id_politica=" + idPolitica + "";
/*     */ 
/*     */ 
/*     */       
/* 190 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 192 */     catch (Exception e) {
/* 193 */       e.printStackTrace();
/* 194 */       Utilidades.writeError("PrcPoliticaDAO:eliminarRegistro ", e);
/* 195 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 198 */       dat.close();
/*     */     } 
/* 200 */     return rta;
/*     */   }
/*     */ 
 
/*     */   public RespuestaBD crearRegistro(int idPolitica, String descripcionPolitica, String estado, String usuarioInsercion) {
/* 214 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 216 */     int elSiguiente = siguienteRegistro();
/* 217 */     if (elSiguiente == 0) {
/* 218 */       rta.setMensaje("Generando secuencia");
/* 219 */       return rta;
/*     */     } 
/*     */     
/* 222 */  DBManager   dat = new DBManager();
/*     */     try {
/* 224 */       String s = "insert into prc_politica(id_politica,descripcion_politica,estado,usuario_insercion,fecha_insercion) values (" + elSiguiente + "," + "'" + descripcionPolitica + "'," + "'" + estado + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
/*     */ 
 
/* 237 */       rta = dat.executeUpdate2(s);
/* 238 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 240 */     catch (Exception e) {
/* 241 */       e.printStackTrace();
/* 242 */       Utilidades.writeError("%PrcPoliticaDAO:crearRegistro ", e);
/* 243 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 246 */       dat.close();
/*     */     } 
/* 248 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int idPolitica, String descripcionPolitica, String estado, String usuarioModificacion) {
/* 262 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 264 */    DBManager dat = new DBManager();
/*     */     try {
/* 266 */       String s = "update prc_politica set  descripcion_politica='" + descripcionPolitica + "'," + " estado='" + estado + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " id_politica=" + idPolitica + "";
 
/*     */       
/* 274 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 276 */     catch (Exception e) {
/* 277 */       e.printStackTrace();
/* 278 */       Utilidades.writeError("PrcPoliticaDAO:modificarRegistro ", e);
/* 279 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 282 */       dat.close();
/*     */     } 
/* 284 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PrcPoliticaDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */