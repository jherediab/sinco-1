/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.GruposUnidadesDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.GruposUnidadesDAO;
/*     */ 

/*     */ 
/*     */ public class GruposUnidadesDAO
/*     */ {
/*     */   public GruposUnidadesDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  35 */       GruposUnidadesDTO reg = new GruposUnidadesDTO();
/*     */       
/*  37 */       reg.setCodigoGrupo(rs.getInt("codigo_grupo"));
/*  38 */       reg.setNombreGrupo(rs.getString("nombre_grupo"));
/*  39 */       reg.setEstadoGrupo(rs.getString("estado_grupo"));
/*  40 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  41 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  42 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  43 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  44 */       reg.setNombreEstadoGrupo(rs.getString("nombre_estado_grupo"));
/*  45 */       return reg;
/*     */     }
/*  47 */     catch (Exception e) {
/*  48 */       e.printStackTrace();
/*  49 */       Utilidades.writeError("GruposUnidadesDAO:leerRegistro ", e);
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
/*     */   public Collection<GruposUnidadesDTO> cargarTodos(String nombreGrupo, String estadoGrupo) {
/*  62 */     Collection<GruposUnidadesDTO> resultados = new ArrayList<GruposUnidadesDTO>();
/*     */     
/*  64 */    DBManager dat = new DBManager();
/*     */     try {
/*  66 */       String s = "select t.codigo_grupo,t.nombre_grupo,t.estado_grupo,m1.DESCRIPCION as nombre_estado_grupo,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from sis_grupos_unidades t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado_grupo) where 1=1";
/*     */ 

/*     */       
/*  78 */       if (nombreGrupo.length() > 0) {
/*  79 */         s = s + " and upper(t.nombre_grupo) like upper('%" + nombreGrupo + "%')";
/*     */       }
/*  81 */       if (estadoGrupo.length() > 0) {
/*  82 */         s = s + " and upper(t.estado_grupo) like upper('%" + estadoGrupo + "%')";
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
/*  96 */       Utilidades.writeError("GruposUnidadesDAO:cargarTodos ", e);
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
/*     */   public GruposUnidadesDTO cargarRegistro(int codigoGrupo) {
/* 111 */   DBManager  dat = new DBManager();
/*     */     try {
/* 113 */       String s = "select t.codigo_grupo,t.nombre_grupo,t.estado_grupo,m1.DESCRIPCION as nombre_estado_grupo,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from sis_grupos_unidades t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado_grupo) where  t.codigo_grupo=" + codigoGrupo + "";
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
/* 138 */       Utilidades.writeError("GruposUnidadesDAO:cargarGruposUnidades", e);
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
/* 153 */     String s = "select max(codigo_grupo) from sis_grupos_unidades ";
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
/* 171 */       Utilidades.writeError("GruposUnidadesDAO:siguienteRegistro ", e);
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
/*     */   public RespuestaBD eliminarRegistro(int codigoGrupo) {
/* 186 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 188 */   DBManager  dat = new DBManager();
/*     */     try {
/* 190 */       String s = "delete from sis_grupos_unidades where  codigo_grupo=" + codigoGrupo + "";
/*     */ 
/*     */ 
/*     */       
/* 194 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 196 */     catch (Exception e) {
/* 197 */       e.printStackTrace();
/* 198 */       Utilidades.writeError("GruposUnidadesDAO:eliminarRegistro ", e);
/* 199 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 202 */       dat.close();
/*     */     } 
/* 204 */     return rta;
/*     */   }
/*     */ 

/*     */   
/*     */   public RespuestaBD crearRegistro(int codigoGrupo, String nombreGrupo, String estadoGrupo, String usuarioInsercion) {
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
/* 228 */       String s = "insert into sis_grupos_unidades(codigo_grupo,nombre_grupo,estado_grupo,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "'" + nombreGrupo + "'," + "'" + estadoGrupo + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 

/*     */ 
/*     */       
/* 241 */       rta = dat.executeUpdate2(s);
/* 242 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 244 */     catch (Exception e) {
/* 245 */       e.printStackTrace();
/* 246 */       Utilidades.writeError("%GruposUnidadesDAO:crearRegistro ", e);
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
/*     */   public RespuestaBD modificarRegistro(int codigoGrupo, String nombreGrupo, String estadoGrupo, String usuarioModificacion) {
/* 266 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 268 */   DBManager  dat = new DBManager();
/*     */     try {
/* 270 */       String s = "update sis_grupos_unidades set  nombre_grupo='" + nombreGrupo + "'," + " estado_grupo='" + estadoGrupo + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo_grupo=" + codigoGrupo + "";
/*     */ 

/*     */       
/* 278 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 280 */     catch (Exception e) {
/* 281 */       e.printStackTrace();
/* 282 */       Utilidades.writeError("GruposUnidadesDAO:modificarRegistro ", e);
/* 283 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 286 */       dat.close();
/*     */     } 
/* 288 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\GruposUnidadesDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */