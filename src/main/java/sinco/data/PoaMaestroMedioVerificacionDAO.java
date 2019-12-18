/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PoaMaestroMedioVerificacionDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PoaMaestroMedioVerificacionDAO;
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
/*     */ public class PoaMaestroMedioVerificacionDAO
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
/*  50 */       Utilidades.writeError("PoaMaestroMedioVerificacionDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaMaestroMedioVerificacionDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("PoaMaestroMedioVerificacionDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaMaestroMedioVerificacionDTO leerRegistro() {
/*     */     try {
/*  79 */       PoaMaestroMedioVerificacionDTO reg = new PoaMaestroMedioVerificacionDTO();
/*     */       
/*  81 */       reg.setIdMaestro(this.rs.getInt("id_maestro"));
/*  82 */       reg.setIdMedioVerificacion(this.rs.getInt("id_medio_verificacion"));
/*  83 */       reg.setEstado(this.rs.getString("estado"));
/*  84 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  85 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  86 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  87 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  88 */       return reg;
/*     */     }
/*  90 */     catch (Exception e) {
/*  91 */       e.printStackTrace();
/*  92 */       Utilidades.writeError("PoaMaestroMedioVerificacionDAO:leerRegistro ", e);
/*     */       
/*  94 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<PoaMaestroMedioVerificacionDTO> cargarTodos() {
/* 104 */     Collection<PoaMaestroMedioVerificacionDTO> resultados = new ArrayList<PoaMaestroMedioVerificacionDTO>();
/*     */     try {
/* 106 */       String s = "select t.id_maestro,t.id_medio_verificacion,t.estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_MAESTRO_MEDIO_VERIF t  where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 116 */       s = s + " order by 1";
/* 117 */       boolean rtaDB = this.dat.parseSql(s);
/* 118 */       if (!rtaDB) {
/* 119 */         return resultados;
/*     */       }
/* 121 */       this.rs = this.dat.getResultSet();
/* 122 */       while (this.rs.next()) {
/* 123 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 126 */     catch (Exception e) {
/* 127 */       e.printStackTrace();
/* 128 */       Utilidades.writeError("PoaMaestroMedioVerificacionDAO:cargarTodos ", e);
/*     */     } 
/* 130 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<PoaMaestroMedioVerificacionDTO> cargarTodos(int codigoPoa) {
/* 137 */     Collection<PoaMaestroMedioVerificacionDTO> resultados = new ArrayList<PoaMaestroMedioVerificacionDTO>();
/*     */     try {
/* 139 */       String s = "select t.id_maestro,t.id_medio_verificacion,t.estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_MAESTRO_MEDIO_VERIF t  where t.codigo_poa_actividad = " + codigoPoa;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 149 */       s = s + " order by 1";
/* 150 */       boolean rtaDB = this.dat.parseSql(s);
/* 151 */       if (!rtaDB) {
/* 152 */         return resultados;
/*     */       }
/* 154 */       this.rs = this.dat.getResultSet();
/* 155 */       while (this.rs.next()) {
/* 156 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 159 */     catch (Exception e) {
/* 160 */       e.printStackTrace();
/* 161 */       Utilidades.writeError("PoaMaestroMedioVerificacionDAO:cargarTodos ", e);
/*     */     } 
/* 163 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaMaestroMedioVerificacionDTO cargarRegistro(int idMaestro, int idMedioVerificacion) {
/*     */     try {
/* 175 */       String s = "select t.id_maestro,t.id_medio_verificacion,t.estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_MAESTRO_MEDIO_VERIF t  where  t.id_maestro=" + idMaestro + " and t.id_medio_verificacion=" + idMedioVerificacion + "";
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
/* 188 */       boolean rtaDB = this.dat.parseSql(s);
/* 189 */       if (!rtaDB) {
/* 190 */         return null;
/*     */       }
/* 192 */       this.rs = this.dat.getResultSet();
/* 193 */       if (this.rs.next()) {
/* 194 */         return leerRegistro();
/*     */       }
/*     */     }
/* 197 */     catch (Exception e) {
/* 198 */       e.printStackTrace();
/* 199 */       Utilidades.writeError("PoaMaestroMedioVerificacionDAO:cargarPoaMaestroMedioVerificacion", e);
/*     */     } 
/* 201 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int idMaestro, int idMedioVerificacion) {
/* 212 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 215 */       String s = "delete from POA_MAESTRO_MEDIO_VERIF where  id_maestro=" + idMaestro + "  and id_medio_verificacion=" + idMedioVerificacion + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 220 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 222 */     catch (Exception e) {
/* 223 */       e.printStackTrace();
/* 224 */       Utilidades.writeError("PoaMaestroMedioVerificacionDAO:eliminarRegistro ", e);
/* 225 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 227 */     return rta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistroPorActividad(int idActividad) {
/* 237 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 240 */       String s = "delete from POA_MAESTRO_MEDIO_VERIF where  codigo_poa_actividad=" + idActividad;
/*     */ 
/*     */       
/* 243 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 245 */     catch (Exception e) {
/* 246 */       e.printStackTrace();
/* 247 */       Utilidades.writeError("PoaMaestroMedioVerificacionDAO:eliminarRegistro ", e);
/* 248 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 250 */     return rta;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int idMaestro) {
/* 256 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 259 */       String s = "delete from POA_MAESTRO_MEDIO_VERIF where  codigo_poa_actividad=" + idMaestro + "";
/*     */ 
/*     */ 
/*     */       
/* 263 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 265 */     catch (Exception e) {
/* 266 */       e.printStackTrace();
/* 267 */       Utilidades.writeError("PoaMaestroMedioVerificacionDAO:eliminarRegistro ", e);
/* 268 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 270 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int idMaestro, int codigoPoaActividad, int idMedioVerificacion, String estado, String usuarioInsercion) {
/* 284 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 287 */       String s = "insert into POA_MAESTRO_MEDIO_VERIF(id_maestro,id_medio_verificacion,codigo_poa_actividad,estado,fecha_insercion,usuario_insercion) values (" + idMaestro + "," + "" + idMedioVerificacion + "," + "" + codigoPoaActividad + "," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
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
/* 302 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 304 */     catch (Exception e) {
/* 305 */       e.printStackTrace();
/* 306 */       Utilidades.writeError("%PoaMaestroMedioVerificacionDAO:crearRegistro ", e);
/* 307 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 309 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int idMaestro, int idMedioVerificacion, String estado, String usuarioModificacion) {
/* 323 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 326 */       String s = "update POA_MAESTRO_MEDIO_VERIF set  estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " id_maestro=" + idMaestro + " and id_medio_verificacion=" + idMedioVerificacion + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 334 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 336 */     catch (Exception e) {
/* 337 */       e.printStackTrace();
/* 338 */       Utilidades.writeError("PoaMaestroMedioVerificacionDAO:modificarRegistro ", e);
/* 339 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 341 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PoaMaestroMedioVerificacionDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */