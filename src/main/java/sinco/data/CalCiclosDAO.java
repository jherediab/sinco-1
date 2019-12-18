/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.CalCiclosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CalCiclosDAO;
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
/*     */ public class CalCiclosDAO
/*     */ {
/*     */   ResultSet rs;
/*     */   DBManager dat;
/*     */   
/*  29 */   public CalCiclosDAO() { this.dat = new DBManager(); }
/*     */ 
/*     */ 
/*     */   
/*  33 */   public CalCiclosDAO(boolean autocommit) { this.dat = new DBManager(autocommit); }
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
/*  45 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  52 */       this.dat.close();
/*     */     }
/*  54 */     catch (Exception e) {
/*  55 */       Utilidades.writeError("CalCiclosDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalCiclosDTO next() {
/*     */     try {
/*  66 */       if (this.rs.next()) {
/*  67 */         return leerRegistro();
/*     */       }
/*     */     }
/*  70 */     catch (Exception e) {
/*  71 */       e.printStackTrace();
/*  72 */       Utilidades.writeError("CalCiclosDAO:next ", e);
/*     */     } 
/*  74 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalCiclosDTO leerRegistro() {
/*     */     try {
/*  84 */       CalCiclosDTO reg = new CalCiclosDTO();
/*     */       
/*  86 */       reg.setCiclo(this.rs.getInt("ciclo"));
/*  87 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  88 */       reg.setEstado(this.rs.getString("estado"));
/*  89 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  90 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  91 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  92 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  93 */       reg.setNombreEstado(this.rs.getString("nombre_estado"));
/*  94 */       return reg;
/*     */     }
/*  96 */     catch (Exception e) {
/*  97 */       e.printStackTrace();
/*  98 */       Utilidades.writeError("CalCiclosDAO:leerRegistro ", e);
/*     */       
/* 100 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection cargarTodos(int ciclo) {
/* 110 */     Collection resultados = new ArrayList();
/*     */     try {
/* 112 */       String s = "select t.ciclo,t.descripcion,t.estado,m1.descripcion as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from CAL_CICLOS t  left join sis_multivalores m1 on (m1.tabla='CAL_ESTADO_CICLO' and m1.valor=t.estado) where 1=1";
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
/* 124 */       if (ciclo > 0) {
/* 125 */         s = s + " and t.ciclo=" + ciclo;
/*     */       }
/* 127 */       s = s + " order by 1 desc";
/* 128 */       boolean rtaDB = this.dat.parseSql(s);
/* 129 */       if (!rtaDB) {
/* 130 */         return resultados;
/*     */       }
/* 132 */       this.rs = this.dat.getResultSet();
/* 133 */       while (this.rs.next()) {
/* 134 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 137 */     catch (Exception e) {
/* 138 */       e.printStackTrace();
/* 139 */       Utilidades.writeError("CalCiclosDAO:cargarTodos ", e);
/*     */     } 
/* 141 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalCiclosDTO cargarRegistro(int ciclo) {
/*     */     try {
/* 151 */       String s = "select t.ciclo,t.descripcion,t.estado,m1.descripcion as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from CAL_CICLOS t  left join sis_multivalores m1 on (m1.tabla='CAL_ESTADO_CICLO' and m1.valor=t.estado) where  t.ciclo=" + ciclo + "";
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
/* 165 */       boolean rtaDB = this.dat.parseSql(s);
/* 166 */       if (!rtaDB) {
/* 167 */         return null;
/*     */       }
/* 169 */       this.rs = this.dat.getResultSet();
/* 170 */       if (this.rs.next()) {
/* 171 */         return leerRegistro();
/*     */       }
/*     */     }
/* 174 */     catch (Exception e) {
/* 175 */       e.printStackTrace();
/* 176 */       Utilidades.writeError("CalCiclosDAO:cargarCalCiclos", e);
/*     */     } 
/* 178 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean cargarTodosEnDefinicion() {
/*     */     try {
/* 189 */       String s = "select t.ciclo,t.descripcion,t.estado,m1.descripcion as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from CAL_CICLOS t  left join sis_multivalores m1 on (m1.tabla='CAL_ESTADO_CICLO' and m1.valor=t.estado) where t.estado IN('D','A') order by 1 desc";
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
/* 201 */       boolean rtaDB = this.dat.parseSql(s);
/* 202 */       if (!rtaDB) {
/* 203 */         return false;
/*     */       }
/*     */       
/* 206 */       this.rs = this.dat.getResultSet();
/* 207 */       return true;
/*     */     }
/* 209 */     catch (Exception e) {
/* 210 */       e.printStackTrace();
/* 211 */       Utilidades.writeError("CalCiclosFactory:cargarTodos ", e);
/*     */       
/* 213 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalCiclosDTO cargarCalCiclos(int ciclo) {
/*     */     try {
/* 224 */       String s = "select t.ciclo,t.descripcion,t.estado,m1.descripcion as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from CAL_CICLOS t  left join sis_multivalores m1 on (m1.tabla='CAL_ESTADO_CICLO' and m1.valor=t.estado) where  t.ciclo=" + ciclo;
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
/* 237 */       boolean rtaDB = this.dat.parseSql(s);
/* 238 */       if (!rtaDB) {
/* 239 */         return null;
/*     */       }
/*     */       
/* 242 */       this.rs = this.dat.getResultSet();
/* 243 */       if (this.rs.next()) {
/* 244 */         return leerRegistro();
/*     */       }
/*     */     }
/* 247 */     catch (Exception e) {
/* 248 */       e.printStackTrace();
/* 249 */       Utilidades.writeError("CalCiclosFactory:cargarCalCiclos ", e);
/*     */     } 
/* 251 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int cargarEnEstado(String estado) {
/*     */     try {
/* 261 */       String s = "select count(0) as numero from cal_ciclos where estado='" + estado + "'";
/* 262 */       boolean rtaDB = this.dat.parseSql(s);
/* 263 */       if (!rtaDB) {
/* 264 */         return 0;
/*     */       }
/*     */       
/* 267 */       this.rs = this.dat.getResultSet();
/* 268 */       if (this.rs.next()) {
/* 269 */         return this.rs.getInt("numero");
/*     */       }
/*     */     }
/* 272 */     catch (Exception e) {
/* 273 */       e.printStackTrace();
/* 274 */       Utilidades.writeError("CalCiclosFactory:cargarCalCiclos ", e);
/*     */     } 
/* 276 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String crearPlanes(int ciclo, String usuario) {
/* 281 */     String resultado = null;
/*     */     try {
/* 283 */       resultado = this.dat.crearPlanes(ciclo, usuario);
/*     */     }
/* 285 */     catch (Exception e) {
/* 286 */       e.printStackTrace();
/* 287 */       Utilidades.writeError("CalCiclosFactory:modificarRegistro", e);
/*     */     } 
/* 289 */     return resultado;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String abrirCiclo(int ciclo, String usuarioModificacion) {
/* 298 */     String respuesta = null;
/*     */     try {
/* 300 */       String s = "update cal_ciclos set ";
/* 301 */       s = s + " estado='A',";
/* 302 */       s = s + " fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 303 */       s = s + " usuario_modificacion='" + usuarioModificacion + "'";
/* 304 */       s = s + " where ";
/* 305 */       s = s + " ciclo=" + ciclo;
/* 306 */       boolean rta = this.dat.executeUpdate(s);
/*     */       
/* 308 */       if (rta) {
/* 309 */         respuesta = crearPlanes(ciclo, usuarioModificacion);
/*     */       }
/*     */       
/* 312 */       return respuesta;
/*     */     }
/* 314 */     catch (Exception e) {
/* 315 */       e.printStackTrace();
/* 316 */       Utilidades.writeError("CalCiclosFactory:modificarRegistro", e);
/*     */       
/* 318 */       return respuesta;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection cargarCiclosAbiertos(int defecto) {
/* 328 */     Collection resultados = new ArrayList();
/*     */     try {
/* 330 */       String s = "select t.ciclo,t.descripcion,t.estado,m1.descripcion as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from CAL_CICLOS t  left join sis_multivalores m1 on (m1.tabla='CAL_ESTADO_CICLO' and m1.valor=t.estado) where t.estado='A'";
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
/* 343 */       if (defecto > 0) {
/* 344 */         s = s + " and ciclo=" + defecto;
/*     */       }
/* 346 */       s = s + " order by 1";
/* 347 */       boolean rtaDB = this.dat.parseSql(s);
/* 348 */       if (!rtaDB) {
/* 349 */         return resultados;
/*     */       }
/* 351 */       this.rs = this.dat.getResultSet();
/* 352 */       while (this.rs.next()) {
/* 353 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 356 */     catch (Exception e) {
/* 357 */       e.printStackTrace();
/* 358 */       Utilidades.writeError("CalCiclosDAO:cargarTodos ", e);
/*     */     } 
/* 360 */     return resultados;
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
/*     */   public RespuestaBD eliminarRegistro(int ciclo) {
/* 373 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 376 */       String s = "delete from CAL_CICLOS where  ciclo=" + ciclo + "";
/*     */ 
/*     */ 
/*     */       
/* 380 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 382 */     catch (Exception e) {
/* 383 */       e.printStackTrace();
/* 384 */       Utilidades.writeError("CalCiclosDAO:eliminarRegistro ", e);
/* 385 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 387 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int ciclo, String descripcion, String estado, String usuarioInsercion) {
/* 401 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 404 */       String s = "insert into CAL_CICLOS(ciclo,descripcion,estado,fecha_insercion,usuario_insercion) values (" + ciclo + "," + "'" + descripcion + "'," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
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
/* 417 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 419 */     catch (Exception e) {
/* 420 */       e.printStackTrace();
/* 421 */       Utilidades.writeError("%CalCiclosDAO:crearRegistro ", e);
/* 422 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 424 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int ciclo, String descripcion, String estado, String usuarioModificacion) {
/* 438 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 441 */       String s = "update CAL_CICLOS set  descripcion='" + descripcion + "'," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " ciclo=" + ciclo + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 449 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 451 */     catch (Exception e) {
/* 452 */       e.printStackTrace();
/* 453 */       Utilidades.writeError("CalCiclosDAO:modificarRegistro ", e);
/* 454 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 456 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\CalCiclosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */