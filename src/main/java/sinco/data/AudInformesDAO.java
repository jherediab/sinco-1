/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.AudInformesDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AudInformesDAO;
/*     */ import sinco.data.DBManager;
/*     */ 
/*     */ 

/*     */ 
/*     */ public class AudInformesDAO
/*     */ {
/*     */   public AudInformesDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  35 */       AudInformesDTO reg = new AudInformesDTO();
/*     */       
/*  37 */       reg.setCodigo(rs.getInt("codigo"));
/*  38 */       reg.setNombre(rs.getString("nombre"));
/*  39 */       reg.setTipoInforme(rs.getString("tipo_informe"));
/*  40 */       reg.setEstrategico(rs.getString("estrategico"));
/*  41 */       reg.setMisional(rs.getString("misional"));
/*  42 */       reg.setApoyo(rs.getString("apoyo"));
/*  43 */       reg.setEvaluacion(rs.getString("evaluacion"));
/*  44 */       reg.setResponsable(rs.getString("responsable"));
/*  45 */       reg.setEstado(rs.getString("estado"));
/*  46 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  47 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  48 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  49 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  50 */       reg.setNombreTipoInforme(rs.getString("nombre_tipo_informe"));
/*  51 */       reg.setNombreResponsable(rs.getString("nombre_responsable"));
/*  52 */       reg.setNombreEstado(rs.getString("nombre_estado"));
/*  53 */       return reg;
/*     */     }
/*  55 */     catch (Exception e) {
/*  56 */       e.printStackTrace();
/*  57 */       Utilidades.writeError("AudInformesDAO:leerRegistro ", e);
/*     */       
/*  59 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<AudInformesDTO> cargarTodos(String nombre, String tipoInforme) {
/*  70 */     Collection<AudInformesDTO> resultados = new ArrayList<AudInformesDTO>();
/*     */     
/*  72 */    DBManager dat = new DBManager();
/*     */     try {
/*  74 */       String s = "select t.codigo,t.nombre,t.tipo_informe,m1.DESCRIPCION as nombre_tipo_informe,t.estrategico,t.misional,t.apoyo,t.evaluacion,t.responsable,m2.DESCRIPCION as nombre_responsable,t.estado,m3.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from AUD_INFORMES t  left join sis_multivalores m1 on (m1.tabla='CAL_TIPO_INFORME' and m1.VALOR=t.tipo_informe) left join sis_multivalores m2 on (m2.tabla='CAL_LIDER_PROCESO' and m2.VALOR=t.responsable) left join sis_multivalores m3 on (m3.tabla='ESTADO_REGISTRO' and m3.VALOR=t.estado) where 1=1";
/*     */ 

/*     */ 
/*     */       
/*  96 */       if (nombre.length() > 0) {
/*  97 */         s = s + " and upper(t.nombre) like upper('%" + nombre + "%')";
/*     */       }
/*  99 */       if (tipoInforme.length() > 0) {
/* 100 */         s = s + " and upper(t.tipo_informe) like upper('%" + tipoInforme + "%')";
/*     */       }
/* 102 */       s = s + " order by 1";
/* 103 */       boolean rtaDB = dat.parseSql(s);
/* 104 */       if (!rtaDB) {
/* 105 */         return resultados;
/*     */       }
/* 107 */       ResultSet rs = dat.getResultSet();
/* 108 */       while (rs.next()) {
/* 109 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 112 */     catch (Exception e) {
/* 113 */       e.printStackTrace();
/* 114 */       Utilidades.writeError("AudInformesDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 117 */       dat.close();
/*     */     } 
/* 119 */     return resultados;
/*     */   }
/*     */ 

/*     */ 
/*     */   
/*     */   public AudInformesDTO cargarRegistro(int codigo) {
/* 129 */   DBManager  dat = new DBManager();
/*     */     try {
/* 131 */       String s = "select t.codigo,t.nombre,t.tipo_informe,m1.DESCRIPCION as nombre_tipo_informe,t.estrategico,t.misional,t.apoyo,t.evaluacion,t.responsable,m2.DESCRIPCION as nombre_responsable,t.estado,m3.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from AUD_INFORMES t  left join sis_multivalores m1 on (m1.tabla='CAL_TIPO_INFORME' and m1.VALOR=t.tipo_informe) left join sis_multivalores m2 on (m2.tabla='CAL_LIDER_PROCESO' and m2.VALOR=t.responsable) left join sis_multivalores m3 on (m3.tabla='ESTADO_REGISTRO' and m3.VALOR=t.estado) where  t.codigo=" + codigo + "";
/*     */ 

/*     */       
/* 155 */       boolean rtaDB = dat.parseSql(s);
/* 156 */       if (!rtaDB) {
/* 157 */         return null;
/*     */       }
/* 159 */       ResultSet rs = dat.getResultSet();
/* 160 */       if (rs.next()) {
/* 161 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 164 */     catch (Exception e) {
/* 165 */       e.printStackTrace();
/* 166 */       Utilidades.writeError("AudInformesDAO:cargarAudInformes", e);
/*     */     } finally {
/*     */       
/* 169 */       dat.close();
/*     */     } 
/* 171 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 180 */     int inumero = 1;
/* 181 */     String s = "select max(codigo) from AUD_INFORMES ";
/*     */ 
/*     */     
/* 184 */   DBManager  dat = new DBManager();
/*     */     try {
/* 186 */       boolean rta = dat.parseSql(s);
/* 187 */       if (!rta) return 0; 
/* 188 */       ResultSet rs = dat.getResultSet();
/* 189 */       if (rs.next()) {
/* 190 */         s = rs.getString(1);
/* 191 */         if (!rs.wasNull()) {
/* 192 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 195 */       return inumero;
/*     */     }
/* 197 */     catch (Exception e) {
/* 198 */       e.printStackTrace();
/* 199 */       Utilidades.writeError("AudInformesDAO:siguienteRegistro ", e);
/*     */     } finally {
/*     */       
/* 202 */       dat.close();
/*     */     } 
/* 204 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int codigo) {
/* 214 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 216 */   DBManager  dat = new DBManager();
/*     */     try {
/* 218 */       String s = "delete from AUD_INFORMES where  codigo=" + codigo + "";
/*     */ 
/*     */ 
/*     */       
/* 222 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 224 */     catch (Exception e) {
/* 225 */       e.printStackTrace();
/* 226 */       Utilidades.writeError("AudInformesDAO:eliminarRegistro ", e);
/* 227 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 230 */       dat.close();
/*     */     } 
/* 232 */     return rta;
/*     */   }

/*     */ 
/*     */   
/*     */   public RespuestaBD crearRegistro(int codigo, String nombre, String tipoInforme, String estrategico, String misional, String apoyo, String evaluacion, String responsable, String estado, String usuarioInsercion) {
/* 252 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 254 */     int elSiguiente = siguienteRegistro();
/* 255 */     if (elSiguiente == 0) {
/* 256 */       rta.setMensaje("Generando secuencia");
/* 257 */       return rta;
/*     */     } 
/*     */     
/* 260 */   DBManager  dat = new DBManager();
/*     */     try {
/* 262 */       String s = "insert into AUD_INFORMES(codigo,nombre,tipo_informe,estrategico,misional,apoyo,evaluacion,responsable,estado,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "'" + nombre + "'," + "'" + tipoInforme + "'," + "'" + estrategico + "'," + "'" + misional + "'," + "'" + apoyo + "'," + "'" + evaluacion + "'," + "'" + responsable + "'," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 

/*     */ 
/*     */       
/* 287 */       rta = dat.executeUpdate2(s);
/* 288 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 290 */     catch (Exception e) {
/* 291 */       e.printStackTrace();
/* 292 */       Utilidades.writeError("%AudInformesDAO:crearRegistro ", e);
/* 293 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 296 */       dat.close();
/*     */     } 
/* 298 */     return rta;
/*     */   }
/*     */ 

/*     */ 
/*     */   
/*     */   public RespuestaBD modificarRegistro(int codigo, String nombre, String tipoInforme, String estrategico, String misional, String apoyo, String evaluacion, String responsable, String estado, String usuarioModificacion) {
/* 318 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 320 */    DBManager dat = new DBManager();
/*     */     try {
/* 322 */       String s = "update AUD_INFORMES set  nombre='" + nombre + "'," + " tipo_informe='" + tipoInforme + "'," + " estrategico='" + estrategico + "'," + " misional='" + misional + "'," + " apoyo='" + apoyo + "'," + " evaluacion='" + evaluacion + "'," + " responsable='" + responsable + "'," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo=" + codigo + "";
/*     */ 
 
/* 336 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 338 */     catch (Exception e) {
/* 339 */       e.printStackTrace();
/* 340 */       Utilidades.writeError("AudInformesDAO:modificarRegistro ", e);
/* 341 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 344 */       dat.close();
/*     */     } 
/* 346 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\AudInformesDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */