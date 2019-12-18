/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PoaActividadResponsableDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PoaActividadResponsableDAO;
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
/*     */ public class PoaActividadResponsableDAO
/*     */ {
/*     */   ResultSet rs;
/*  29 */   DBManager dat = new DBManager();
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
/*  40 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  47 */       this.dat.close();
/*     */     }
/*  49 */     catch (Exception e) {
/*  50 */       Utilidades.writeError("PoaActividadResponsableDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaActividadResponsableDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("PoaActividadResponsableDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaActividadResponsableDTO leerRegistro() {
/*     */     try {
/*  79 */       PoaActividadResponsableDTO reg = new PoaActividadResponsableDTO();
/*     */       
/*  81 */       reg.setIdActividad(this.rs.getInt("id_actividad"));
/*  82 */       reg.setIdUsuario(this.rs.getInt("id_usuario"));
/*  83 */       reg.setCodigoPoa(this.rs.getInt("codigo_poa"));
/*  84 */       reg.setEstado(this.rs.getString("estado"));
/*  85 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  86 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  87 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  88 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  89 */       return reg;
/*     */     }
/*  91 */     catch (Exception e) {
/*  92 */       e.printStackTrace();
/*  93 */       Utilidades.writeError("PoaActividadResponsableDAO:leerRegistro ", e);
/*     */       
/*  95 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<PoaActividadResponsableDTO> cargarTodos() {
/* 105 */     Collection<PoaActividadResponsableDTO> resultados = new ArrayList<PoaActividadResponsableDTO>();
/*     */     try {
/* 107 */       String s = "select t.id_actividad,t.id_usuario,t.codigo_poa,t.estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_ACTIVIDAD_RESPONSABLE t  where 1=1";
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
/* 118 */       s = s + " order by 1";
/* 119 */       boolean rtaDB = this.dat.parseSql(s);
/* 120 */       if (!rtaDB) {
/* 121 */         return resultados;
/*     */       }
/* 123 */       this.rs = this.dat.getResultSet();
/* 124 */       while (this.rs.next()) {
/* 125 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 128 */     catch (Exception e) {
/* 129 */       e.printStackTrace();
/* 130 */       Utilidades.writeError("PoaActividadResponsableDAO:cargarTodos ", e);
/*     */     } 
/* 132 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<PoaActividadResponsableDTO> cargarTodos(int codigoPoa) {
/* 138 */     Collection<PoaActividadResponsableDTO> resultados = new ArrayList<PoaActividadResponsableDTO>();
/*     */     try {
/* 140 */       String s = "select t.id_actividad,t.id_usuario,t.codigo_poa,t.estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_ACTIVIDAD_RESPONSABLE t  where t.codigo_poa = " + codigoPoa;
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
/* 151 */       s = s + " order by 1";
/* 152 */       boolean rtaDB = this.dat.parseSql(s);
/* 153 */       if (!rtaDB) {
/* 154 */         return resultados;
/*     */       }
/* 156 */       this.rs = this.dat.getResultSet();
/* 157 */       while (this.rs.next()) {
/* 158 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 161 */     catch (Exception e) {
/* 162 */       e.printStackTrace();
/* 163 */       Utilidades.writeError("PoaActividadResponsableDAO:cargarTodos ", e);
/*     */     } 
/* 165 */     return resultados;
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
/*     */   public PoaActividadResponsableDTO cargarRegistro(int idActividad, int idUsuario, int codigoPoa) {
/*     */     try {
/* 179 */       String s = "select t.id_actividad,t.id_usuario,t.codigo_poa,t.estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_ACTIVIDAD_RESPONSABLE t  where  t.id_actividad=" + idActividad + " and t.id_usuario=" + idUsuario + " and t.codigo_poa=" + codigoPoa + "";
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
/* 194 */       boolean rtaDB = this.dat.parseSql(s);
/* 195 */       if (!rtaDB) {
/* 196 */         return null;
/*     */       }
/* 198 */       this.rs = this.dat.getResultSet();
/* 199 */       if (this.rs.next()) {
/* 200 */         return leerRegistro();
/*     */       }
/*     */     }
/* 203 */     catch (Exception e) {
/* 204 */       e.printStackTrace();
/* 205 */       Utilidades.writeError("PoaActividadResponsableDAO:cargarPoaActividadResponsable", e);
/*     */     } 
/* 207 */     return null;
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
/*     */   public RespuestaBD eliminarRegistro(int idActividad, int idUsuario, int codigoPoa) {
/* 219 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 222 */       String s = "delete from POA_ACTIVIDAD_RESPONSABLE where  id_actividad=" + idActividad + "  and id_usuario=" + idUsuario + "  and codigo_poa=" + codigoPoa + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 228 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 230 */     catch (Exception e) {
/* 231 */       e.printStackTrace();
/* 232 */       Utilidades.writeError("PoaActividadResponsableDAO:eliminarRegistro ", e);
/* 233 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 235 */     return rta;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int codigoPoa) {
/* 241 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 244 */       String s = "delete from POA_ACTIVIDAD_RESPONSABLE where  codigo_poa=" + codigoPoa + "";
/*     */ 
/*     */ 
/*     */       
/* 248 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 250 */     catch (Exception e) {
/* 251 */       e.printStackTrace();
/* 252 */       Utilidades.writeError("PoaActividadResponsableDAO:eliminarRegistro ", e);
/* 253 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 255 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int idActividad, int idUsuario, int codigoPoa, String estado, String usuarioInsercion) {
/* 270 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 273 */       String s = "insert into POA_ACTIVIDAD_RESPONSABLE(id_actividad,id_usuario,codigo_poa,estado,fecha_insercion,usuario_insercion) values (" + idActividad + "," + "" + idUsuario + "," + "" + codigoPoa + "," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
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
/* 288 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 290 */     catch (Exception e) {
/* 291 */       e.printStackTrace();
/* 292 */       Utilidades.writeError("%PoaActividadResponsableDAO:crearRegistro ", e);
/* 293 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 295 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int idActividad, int idUsuario, int codigoPoa, String estado, String usuarioModificacion) {
/* 310 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 313 */       String s = "update POA_ACTIVIDAD_RESPONSABLE set  estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " id_actividad=" + idActividad + " and id_usuario=" + idUsuario + " and codigo_poa=" + codigoPoa + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 322 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 324 */     catch (Exception e) {
/* 325 */       e.printStackTrace();
/* 326 */       Utilidades.writeError("PoaActividadResponsableDAO:modificarRegistro ", e);
/* 327 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 329 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PoaActividadResponsableDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */