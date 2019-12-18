/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PoaMetasProyectoDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PoaMetasProyectoDAO;
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
/*     */ public class PoaMetasProyectoDAO
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
/*  50 */       Utilidades.writeError("PoaMetasProyectoDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaMetasProyectoDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("PoaMetasProyectoDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaMetasProyectoDTO leerRegistro() {
/*     */     try {
/*  79 */       PoaMetasProyectoDTO reg = new PoaMetasProyectoDTO();
/*     */       
/*  81 */       reg.setCodigoMetaPlan(this.rs.getInt("meta_plan"));
/*  82 */       reg.setCODIGOMETAPROYECTO(this.rs.getInt("CODIGO_META_PROYECTO"));
/*  83 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  84 */       reg.setEstado(this.rs.getString("estado"));
/*  85 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  86 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  87 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  88 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  89 */       reg.setNombreEstado(this.rs.getString("nombre_estado"));
/*  90 */       return reg;
/*     */     }
/*  92 */     catch (Exception e) {
/*  93 */       e.printStackTrace();
/*  94 */       Utilidades.writeError("PoaMetasProyectoDAO:leerRegistro ", e);
/*     */       
/*  96 */       return null;
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
/*     */   public Collection<PoaMetasProyectoDTO> cargarTodos(int codigoMetaPlan, String descripcion, String estado) {
/* 108 */     Collection<PoaMetasProyectoDTO> resultados = new ArrayList<PoaMetasProyectoDTO>();
/*     */     try {
/* 110 */       String s = "select t.meta_plan,t.CODIGO_META_PROYECTO,t.descripcion,t.estado,m1.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_METAS_PROYECTO t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where 1=1";
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
/* 123 */       if (codigoMetaPlan > 0) {
/* 124 */         s = s + " and t.meta_plan=" + codigoMetaPlan;
/*     */       }
/* 126 */       if (descripcion.length() > 0) {
/* 127 */         s = s + " and upper(t.descripcion) like upper('%" + descripcion + "%')";
/*     */       }
/* 129 */       if (estado.length() > 0) {
/* 130 */         s = s + " and upper(t.estado) like upper('%" + estado + "%')";
/*     */       }
/* 132 */       s = s + " order by 1";
/* 133 */       boolean rtaDB = this.dat.parseSql(s);
/* 134 */       if (!rtaDB) {
/* 135 */         return resultados;
/*     */       }
/* 137 */       this.rs = this.dat.getResultSet();
/* 138 */       while (this.rs.next()) {
/* 139 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 142 */     catch (Exception e) {
/* 143 */       e.printStackTrace();
/* 144 */       Utilidades.writeError("PoaMetasProyectoDAO:cargarTodos ", e);
/*     */     } 
/* 146 */     return resultados;
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
/*     */   public Collection<PoaMetasProyectoDTO> cargarTodos(int codigoMetaPlan) {
/* 158 */     Collection<PoaMetasProyectoDTO> resultados = new ArrayList<PoaMetasProyectoDTO>();
/*     */     try {
/* 160 */       String s = "select t.meta_plan,t.CODIGO_META_PROYECTO,t.descripcion,t.estado,m1.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_METAS_PROYECTO t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where 1=1";
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
/* 173 */       if (codigoMetaPlan > 0) {
/* 174 */         s = s + " and t.meta_plan=" + codigoMetaPlan;
/*     */       }
/* 176 */       s = s + " order by 1";
/* 177 */       boolean rtaDB = this.dat.parseSql(s);
/* 178 */       if (!rtaDB) {
/* 179 */         return resultados;
/*     */       }
/* 181 */       this.rs = this.dat.getResultSet();
/* 182 */       while (this.rs.next()) {
/* 183 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 186 */     catch (Exception e) {
/* 187 */       e.printStackTrace();
/* 188 */       Utilidades.writeError("PoaMetasProyectoDAO:cargarTodos ", e);
/*     */     } 
/* 190 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaMetasProyectoDTO cargarRegistro(int codigoMetaPlan, int CODIGOMETAPROYECTO) {
/*     */     try {
/* 201 */       String s = "select t.meta_plan,t.CODIGO_META_PROYECTO,t.descripcion,t.estado,m1.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_METAS_PROYECTO t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where  t.meta_plan=" + codigoMetaPlan + " and t.CODIGO_META_PROYECTO=" + CODIGOMETAPROYECTO + "";
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
/* 217 */       boolean rtaDB = this.dat.parseSql(s);
/* 218 */       if (!rtaDB) {
/* 219 */         return null;
/*     */       }
/* 221 */       this.rs = this.dat.getResultSet();
/* 222 */       if (this.rs.next()) {
/* 223 */         return leerRegistro();
/*     */       }
/*     */     }
/* 226 */     catch (Exception e) {
/* 227 */       e.printStackTrace();
/* 228 */       Utilidades.writeError("PoaMetasProyectoDAO:cargarPoaMetasProyecto", e);
/*     */     } 
/* 230 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaMetasProyectoDTO cargarRegistro(int codigoMetaPlan) {
/*     */     try {
/* 242 */       String s = "select t.meta_plan,t.CODIGO_META_PROYECTO,t.descripcion,t.estado,m1.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_METAS_PROYECTO t  left join sis_multivalores m1 on (m1.tabla='ESTADO_REGISTRO' and m1.VALOR=t.estado) where  t.meta_plan=" + codigoMetaPlan + "";
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
/* 257 */       boolean rtaDB = this.dat.parseSql(s);
/* 258 */       if (!rtaDB) {
/* 259 */         return null;
/*     */       }
/* 261 */       this.rs = this.dat.getResultSet();
/* 262 */       if (this.rs.next()) {
/* 263 */         return leerRegistro();
/*     */       }
/*     */     }
/* 266 */     catch (Exception e) {
/* 267 */       e.printStackTrace();
/* 268 */       Utilidades.writeError("PoaMetasProyectoDAO:cargarPoaMetasProyecto", e);
/*     */     } 
/* 270 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro(int codigoMetaPlan) {
/* 278 */     int inumero = 1;
/* 279 */     String s = "select max(CODIGO_META_PROYECTO) from POA_METAS_PROYECTO ";
/*     */     
/*     */     try {
/* 282 */       boolean rta = this.dat.parseSql(s);
/* 283 */       if (!rta) return 0; 
/* 284 */       this.rs = this.dat.getResultSet();
/* 285 */       if (this.rs.next()) {
/* 286 */         s = this.rs.getString(1);
/* 287 */         if (!this.rs.wasNull()) {
/* 288 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 291 */       return inumero;
/*     */     }
/* 293 */     catch (Exception e) {
/* 294 */       e.printStackTrace();
/* 295 */       Utilidades.writeError("PoaMetasProyectoDAO:siguienteRegistro ", e);
/*     */       
/* 297 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int codigoMetaPlan, int CODIGOMETAPROYECTO) {
/* 308 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 311 */       String s = "delete from POA_METAS_PROYECTO where  meta_plan=" + codigoMetaPlan + "  and CODIGO_META_PROYECTO=" + CODIGOMETAPROYECTO + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 316 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 318 */     catch (Exception e) {
/* 319 */       e.printStackTrace();
/* 320 */       Utilidades.writeError("PoaMetasProyectoDAO:eliminarRegistro ", e);
/* 321 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 323 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int codigoMetaPlan, int CODIGOMETAPROYECTO, String descripcion, String estado, String usuarioInsercion) {
/* 338 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 340 */     int elSiguiente = siguienteRegistro(codigoMetaPlan);
/* 341 */     if (elSiguiente == 0) {
/* 342 */       rta.setMensaje("Generando secuencia");
/* 343 */       return rta;
/*     */     } 
/*     */     
/*     */     try {
/* 347 */       String s = "insert into POA_METAS_PROYECTO(meta_plan,CODIGO_META_PROYECTO,descripcion,estado,fecha_insercion,usuario_insercion) values (" + codigoMetaPlan + "," + "" + elSiguiente + "," + "'" + descripcion + "'," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
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
/* 362 */       rta = this.dat.executeUpdate2(s);
/* 363 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 365 */     catch (Exception e) {
/* 366 */       e.printStackTrace();
/* 367 */       Utilidades.writeError("%PoaMetasProyectoDAO:crearRegistro ", e);
/* 368 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 370 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int codigoMetaPlan, int CODIGOMETAPROYECTO, String descripcion, String estado, String usuarioModificacion) {
/* 385 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 388 */       String s = "update POA_METAS_PROYECTO set  descripcion='" + descripcion + "'," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " meta_plan=" + codigoMetaPlan + " and CODIGO_META_PROYECTO=" + CODIGOMETAPROYECTO + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 397 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 399 */     catch (Exception e) {
/* 400 */       e.printStackTrace();
/* 401 */       Utilidades.writeError("PoaMetasProyectoDAO:modificarRegistro ", e);
/* 402 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 404 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PoaMetasProyectoDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */