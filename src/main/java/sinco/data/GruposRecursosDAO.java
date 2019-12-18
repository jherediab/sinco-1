/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.GruposRecursosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.GruposRecursosDAO;
/*     */ 

/*     */ 
/*     */ public class GruposRecursosDAO
/*     */ {
/*     */   public GruposRecursosDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  35 */       GruposRecursosDTO reg = new GruposRecursosDTO();
/*     */       
/*  37 */       reg.setCodigoRecurso(rs.getInt("codigo_recurso"));
/*  38 */       reg.setNombreRecurso(rs.getString("nombre_recurso"));
/*  39 */       reg.setEstadoRecurso(rs.getString("estado_recurso"));
/*  40 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  41 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  42 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  43 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  44 */       reg.setNombreEstadoRecurso(rs.getString("nombre_estado_recurso"));
/*  45 */       return reg;
/*     */     }
/*  47 */     catch (Exception e) {
/*  48 */       e.printStackTrace();
/*  49 */       Utilidades.writeError("GruposRecursosDAO:leerRegistro ", e);
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
/*     */   public Collection<GruposRecursosDTO> cargarTodos(String nombreRecurso, String estadoRecurso) {
/*  62 */     Collection<GruposRecursosDTO> resultados = new ArrayList<GruposRecursosDTO>();
/*     */     
/*  64 */    DBManager dat = new DBManager();
/*     */     try {
/*  66 */       String s = "select t.codigo_recurso,t.nombre_recurso,t.estado_recurso,m1.DESCRIPCION as nombre_estado_recurso,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from sis_grupos_recursos t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado_recurso) where 1=1";
/*     */ 

/*     */       
/*  78 */       if (nombreRecurso.length() > 0) {
/*  79 */         s = s + " and upper(t.nombre_recurso) like upper('%" + nombreRecurso + "%')";
/*     */       }
/*  81 */       if (estadoRecurso.length() > 0) {
/*  82 */         s = s + " and upper(t.estado_recurso) like upper('%" + estadoRecurso + "%')";
/*     */       }
/*  84 */       s = s + " order by 1";
/*  85 */       boolean rtaDB = dat.parseSql(s);
/*  86 */       if (!rtaDB) {
/*  87 */         return resultados;
/*     */       }
/*  89 */       ResultSet rs = dat.getResultSet();
/*  90 */       while (rs.next()) {
/*  91 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/*  94 */     catch (Exception e) {
/*  95 */       e.printStackTrace();
/*  96 */       Utilidades.writeError("GruposRecursosDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/*  99 */       dat.close();
/*     */     } 
/* 101 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GruposRecursosDTO cargarRegistro(int codigoRecurso) {
/* 111 */    DBManager dat = new DBManager();
/*     */     try {
/* 113 */       String s = "select t.codigo_recurso,t.nombre_recurso,t.estado_recurso,m1.DESCRIPCION as nombre_estado_recurso,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from sis_grupos_recursos t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado_recurso) where  t.codigo_recurso=" + codigoRecurso + "";
/*     */ 

/*     */       
/* 127 */       boolean rtaDB = dat.parseSql(s);
/* 128 */       if (!rtaDB) {
/* 129 */         return null;
/*     */       }
/* 131 */       ResultSet rs = dat.getResultSet();
/* 132 */       if (rs.next()) {
/* 133 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 136 */     catch (Exception e) {
/* 137 */       e.printStackTrace();
/* 138 */       Utilidades.writeError("GruposRecursosDAO:cargarGruposRecursos", e);
/*     */     } finally {
/*     */       
/* 141 */       dat.close();
/*     */     } 
/* 143 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 152 */     int inumero = 1;
/* 153 */     String s = "select max(codigo_recurso) from sis_grupos_recursos ";
/*     */ 
/*     */     
/* 156 */   DBManager  dat = new DBManager();
/*     */     try {
/* 158 */       boolean rta = dat.parseSql(s);
/* 159 */       if (!rta) return 0; 
/* 160 */       ResultSet rs = dat.getResultSet();
/* 161 */       if (rs.next()) {
/* 162 */         s = rs.getString(1);
/* 163 */         if (!rs.wasNull()) {
/* 164 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 167 */       return inumero;
/*     */     }
/* 169 */     catch (Exception e) {
/* 170 */       e.printStackTrace();
/* 171 */       Utilidades.writeError("GruposRecursosDAO:siguienteRegistro ", e);
/*     */     } finally {
/*     */       
/* 174 */       dat.close();
/*     */     } 
/* 176 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int codigoRecurso) {
/* 186 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 188 */    DBManager dat = new DBManager();
/*     */     try {
/* 190 */       String s = "delete from sis_grupos_recursos where  codigo_recurso=" + codigoRecurso + "";
/*     */ 
/*     */ 
/*     */       
/* 194 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 196 */     catch (Exception e) {
/* 197 */       e.printStackTrace();
/* 198 */       Utilidades.writeError("GruposRecursosDAO:eliminarRegistro ", e);
/* 199 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 202 */       dat.close();
/*     */     } 
/* 204 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int codigoRecurso, String nombreRecurso, String estadoRecurso, String usuarioInsercion) {
/* 218 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 220 */     int elSiguiente = siguienteRegistro();
/* 221 */     if (elSiguiente == 0) {
/* 222 */       rta.setMensaje("Generando secuencia");
/* 223 */       return rta;
/*     */     } 
/*     */     
/* 226 */    DBManager dat = new DBManager();
/*     */     try {
/* 228 */       String s = "insert into sis_grupos_recursos(codigo_recurso,nombre_recurso,estado_recurso,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "'" + nombreRecurso + "'," + "'" + estadoRecurso + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 

/* 241 */       rta = dat.executeUpdate2(s);
/* 242 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 244 */     catch (Exception e) {
/* 245 */       e.printStackTrace();
/* 246 */       Utilidades.writeError("%GruposRecursosDAO:crearRegistro ", e);
/* 247 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 250 */       dat.close();
/*     */     } 
/* 252 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int codigoRecurso, String nombreRecurso, String estadoRecurso, String usuarioModificacion) {
/* 266 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 268 */  DBManager   dat = new DBManager();
/*     */     try {
/* 270 */       String s = "update sis_grupos_recursos set  nombre_recurso='" + nombreRecurso + "'," + " estado_recurso='" + estadoRecurso + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo_recurso=" + codigoRecurso + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 278 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 280 */     catch (Exception e) {
/* 281 */       e.printStackTrace();
/* 282 */       Utilidades.writeError("GruposRecursosDAO:modificarRegistro ", e);
/* 283 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 286 */       dat.close();
/*     */     } 
/* 288 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\GruposRecursosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */