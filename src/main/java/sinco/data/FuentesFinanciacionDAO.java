/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.FuentesFinanciacionDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.FuentesFinanciacionDAO;
/*     */ 

/*     */ public class FuentesFinanciacionDAO
/*     */ {
/*     */   public FuentesFinanciacionDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  35 */       FuentesFinanciacionDTO reg = new FuentesFinanciacionDTO();
/*     */       
/*  37 */       reg.setCodigoFuente(rs.getInt("codigo_fuente"));
/*  38 */       reg.setCodigoRecurso(rs.getInt("codigo_recurso"));
/*  39 */       reg.setNombreFuente(rs.getString("nombre_fuente"));
/*  40 */       reg.setEstadoFuente(rs.getString("estado_fuente"));
/*  41 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  42 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  43 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  44 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  45 */       reg.setNombreEstadoFuente(rs.getString("nombre_estado_fuente"));
/*  46 */       return reg;
/*     */     }
/*  48 */     catch (Exception e) {
/*  49 */       e.printStackTrace();
/*  50 */       Utilidades.writeError("FuentesFinanciacionDAO:leerRegistro ", e);
/*     */       
/*  52 */       return null;
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
/*     */   public Collection<FuentesFinanciacionDTO> cargarTodos(int codigoRecurso, String nombreFuente, String estadoFuente) {
/*  64 */     Collection<FuentesFinanciacionDTO> resultados = new ArrayList<FuentesFinanciacionDTO>();
/*     */     
/*  66 */   DBManager  dat = new DBManager();
/*     */     try {
/*  68 */       String s = "select t.codigo_fuente,t.codigo_recurso,t.nombre_fuente,t.estado_fuente,m1.DESCRIPCION as nombre_estado_fuente,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from sis_fuentes_financiacion t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado_fuente) where 1=1";

/*     */       
/*  81 */       if (codigoRecurso > 0) {
/*  82 */         s = s + " and t.codigo_recurso=" + codigoRecurso;
/*     */       }
/*  84 */       if (nombreFuente.length() > 0) {
/*  85 */         s = s + " and upper(t.nombre_fuente) like upper('%" + nombreFuente + "%')";
/*     */       }
/*  87 */       if (estadoFuente.length() > 0) {
/*  88 */         s = s + " and upper(t.estado_fuente) like upper('%" + estadoFuente + "%')";
/*     */       }
/*  90 */       s = s + " order by 1";
/*  91 */       boolean rtaDB = dat.parseSql(s);
/*  92 */       if (!rtaDB) {
/*  93 */         return resultados;
/*     */       }
/*  95 */       ResultSet rs = dat.getResultSet();
/*  96 */       while (rs.next()) {
/*  97 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 100 */     catch (Exception e) {
/* 101 */       e.printStackTrace();
/* 102 */       Utilidades.writeError("FuentesFinanciacionDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 105 */       dat.close();
/*     */     } 
/* 107 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FuentesFinanciacionDTO cargarRegistro(int codigoFuente, int codigoRecurso) {
/* 118 */    DBManager dat = new DBManager();
/*     */     try {
/* 120 */       String s = "select t.codigo_fuente,t.codigo_recurso,t.nombre_fuente,t.estado_fuente,m1.DESCRIPCION as nombre_estado_fuente,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from sis_fuentes_financiacion t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado_fuente) where  t.codigo_fuente=" + codigoFuente + " and t.codigo_recurso=" + codigoRecurso + "";
/*     */ 

/* 136 */       boolean rtaDB = dat.parseSql(s);
/* 137 */       if (!rtaDB) {
/* 138 */         return null;
/*     */       }
/* 140 */       ResultSet rs = dat.getResultSet();
/* 141 */       if (rs.next()) {
/* 142 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 145 */     catch (Exception e) {
/* 146 */       e.printStackTrace();
/* 147 */       Utilidades.writeError("FuentesFinanciacionDAO:cargarFuentesFinanciacion", e);
/*     */     } finally {
/*     */       
/* 150 */       dat.close();
/*     */     } 
/* 152 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 161 */     int inumero = 1;
/* 162 */     String s = "select max(codigo_fuente) from sis_fuentes_financiacion ";
/*     */ 
/*     */     
/* 165 */    DBManager dat = new DBManager();
/*     */     try {
/* 167 */       boolean rta = dat.parseSql(s);
/* 168 */       if (!rta) return 0; 
/* 169 */       ResultSet rs = dat.getResultSet();
/* 170 */       if (rs.next()) {
/* 171 */         s = rs.getString(1);
/* 172 */         if (!rs.wasNull()) {
/* 173 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 176 */       return inumero;
/*     */     }
/* 178 */     catch (Exception e) {
/* 179 */       e.printStackTrace();
/* 180 */       Utilidades.writeError("FuentesFinanciacionDAO:siguienteRegistro ", e);
/*     */     } finally {
/*     */       
/* 183 */       dat.close();
/*     */     } 
/* 185 */     return 0;
/*     */   }

/*     */   
/*     */   public RespuestaBD eliminarRegistro(int codigoFuente, int codigoRecurso) {
/* 196 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 198 */   DBManager  dat = new DBManager();
/*     */     try {
/* 200 */       String s = "delete from sis_fuentes_financiacion where  codigo_fuente=" + codigoFuente + "  and codigo_recurso=" + codigoRecurso + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 205 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 207 */     catch (Exception e) {
/* 208 */       e.printStackTrace();
/* 209 */       Utilidades.writeError("FuentesFinanciacionDAO:eliminarRegistro ", e);
/* 210 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 213 */       dat.close();
/*     */     } 
/* 215 */     return rta;
/*     */   }
/*     */ 

/*     */   
/*     */   public RespuestaBD crearRegistro(int codigoFuente, int codigoRecurso, String nombreFuente, String estadoFuente, String usuarioInsercion) {
/* 230 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 232 */     int elSiguiente = siguienteRegistro();
/* 233 */     if (elSiguiente == 0) {
/* 234 */       rta.setMensaje("Generando secuencia");
/* 235 */       return rta;
/*     */     } 
/*     */     
/* 238 */  DBManager   dat = new DBManager();
/*     */     try {
/* 240 */       String s = "insert into sis_fuentes_financiacion(codigo_fuente,codigo_recurso,nombre_fuente,estado_fuente,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "" + codigoRecurso + "," + "'" + nombreFuente + "'," + "'" + estadoFuente + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 

/*     */       
/* 255 */       rta = dat.executeUpdate2(s);
/* 256 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 258 */     catch (Exception e) {
/* 259 */       e.printStackTrace();
/* 260 */       Utilidades.writeError("%FuentesFinanciacionDAO:crearRegistro ", e);
/* 261 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 264 */       dat.close();
/*     */     } 
/* 266 */     return rta;
/*     */   }
/*     */ 

/*     */   
/*     */   public RespuestaBD modificarRegistro(int codigoFuente, int codigoRecurso, String nombreFuente, String estadoFuente, String usuarioModificacion) {
/* 281 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 283 */   DBManager  dat = new DBManager();
/*     */     try {
/* 285 */       String s = "update sis_fuentes_financiacion set  nombre_fuente='" + nombreFuente + "'," + " estado_fuente='" + estadoFuente + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo_fuente=" + codigoFuente + " and codigo_recurso=" + codigoRecurso + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 294 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 296 */     catch (Exception e) {
/* 297 */       e.printStackTrace();
/* 298 */       Utilidades.writeError("FuentesFinanciacionDAO:modificarRegistro ", e);
/* 299 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 302 */       dat.close();
/*     */     } 
/* 304 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\FuentesFinanciacionDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */