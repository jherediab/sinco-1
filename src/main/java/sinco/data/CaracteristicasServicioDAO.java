/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.CaracteristicasServicioDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CaracteristicasServicioDAO;
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
/*     */ 
/*     */ 
/*     */ public class CaracteristicasServicioDAO
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
/*  50 */       Utilidades.writeError("CaracteristicasServicioDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CaracteristicasServicioDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("CaracteristicasServicioDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CaracteristicasServicioDTO leerRegistro() {
/*     */     try {
/*  79 */       CaracteristicasServicioDTO reg = new CaracteristicasServicioDTO();
/*     */       
/*  81 */       reg.setCodigoServicio(this.rs.getInt("codigo_servicio"));
/*  82 */       reg.setCodigoCaracteristica(this.rs.getInt("codigo_caracteristica"));
/*  83 */       reg.setRol(this.rs.getString("rol"));
/*  84 */       reg.setObligatoria(this.rs.getString("obligatoria"));
/*  85 */       reg.setIndice(this.rs.getInt("indice"));
/*  86 */       reg.setEstado(this.rs.getString("estado"));
/*  87 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  88 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  89 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  90 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*     */       
/*     */       try {
/*  93 */         reg.setNombreCodigoCaracteristica(this.rs.getString("nombre_codigo_caracteristica"));
/*  94 */         reg.setNombreRol(this.rs.getString("nombre_rol"));
/*  95 */         reg.setNombreEstado(this.rs.getString("nombre_estado"));
/*     */       }
/*  97 */       catch (Exception e) {}
/*     */ 
/*     */ 
/*     */       
/* 101 */       return reg;
/*     */     }
/* 103 */     catch (Exception e) {
/* 104 */       e.printStackTrace();
/* 105 */       Utilidades.writeError("CaracteristicasServicioDAO:leerRegistro ", e);
/*     */       
/* 107 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<CaracteristicasServicioDTO> cargarTodos() {
/* 117 */     Collection<CaracteristicasServicioDTO> resultados = new ArrayList<CaracteristicasServicioDTO>();
/*     */     try {
/* 119 */       String s = "select t.codigo_servicio,t.codigo_caracteristica,r1.DESCRIPCION as nombre_codigo_caracteristica,t.rol,m2.DESCRIPCION as nombre_rol,t.obligatoria,t.indice,t.estado,m3.DESCRIPCION as nombre_estado,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from caracteristicas_servicio t  left join CARACTERISTICAS r1 on (r1.CODIGO=t.codigo_caracteristica) left join sis_multivalores m2 on (m2.tabla='ROL_CARACTERISTICA_EN_SERVICIO' and m2.VALOR=t.rol) left join sis_multivalores m3 on (m3.tabla='ESTADO_REGISTRO' and m3.VALOR=t.estado) where 1=1";
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
/*     */ 
/*     */ 
/*     */       
/* 138 */       s = s + " order by 1";
/* 139 */       boolean rtaDB = this.dat.parseSql(s);
/* 140 */       if (!rtaDB) {
/* 141 */         return resultados;
/*     */       }
/* 143 */       this.rs = this.dat.getResultSet();
/* 144 */       while (this.rs.next()) {
/* 145 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 148 */     catch (Exception e) {
/* 149 */       e.printStackTrace();
/* 150 */       Utilidades.writeError("CaracteristicasServicioDAO:cargarTodos ", e);
/*     */     } 
/* 152 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CaracteristicasServicioDTO cargarRegistro(int codigoServicio, int codigoCaracteristica) {
/*     */     try {
/* 163 */       String s = "select t.codigo_servicio,t.codigo_caracteristica,r1.DESCRIPCION as nombre_codigo_caracteristica,t.rol,m2.DESCRIPCION as nombre_rol,t.obligatoria,t.indice,t.estado,m3.DESCRIPCION as nombre_estado,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from caracteristicas_servicio t  left join CARACTERISTICAS r1 on (r1.CODIGO=t.codigo_caracteristica) left join sis_multivalores m2 on (m2.tabla='ROL_CARACTERISTICA_EN_SERVICIO' and m2.VALOR=t.rol) left join sis_multivalores m3 on (m3.tabla='ESTADO_REGISTRO' and m3.VALOR=t.estado) where  t.codigo_servicio=" + codigoServicio + " and t.codigo_caracteristica=" + codigoCaracteristica;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 184 */       boolean rtaDB = this.dat.parseSql(s);
/* 185 */       if (!rtaDB) {
/* 186 */         return null;
/*     */       }
/* 188 */       this.rs = this.dat.getResultSet();
/* 189 */       if (this.rs.next()) {
/* 190 */         return leerRegistro();
/*     */       }
/*     */     }
/* 193 */     catch (Exception e) {
/* 194 */       e.printStackTrace();
/* 195 */       Utilidades.writeError("CaracteristicasServicioDAO:cargarCaracteristicasServicio", e);
/*     */     } 
/* 197 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int codigoServicio, int codigoCaracteristica) {
/* 208 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 211 */       String s = "delete from caracteristicas_servicio where  codigo_servicio=" + codigoServicio + "  and codigo_caracteristica=" + codigoCaracteristica;
/*     */ 
/*     */ 
/*     */       
/* 215 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 217 */     catch (Exception e) {
/* 218 */       e.printStackTrace();
/* 219 */       Utilidades.writeError("CaracteristicasServicioDAO:eliminarRegistro ", e);
/* 220 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 222 */     return rta;
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
/*     */ 
/*     */   
/*     */   public RespuestaBD crearRegistro(int codigoServicio, int codigoCaracteristica, String rol, String obligatoria, int indice, String estado, String usuarioInsercion) {
/* 239 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 242 */       String s = "insert into caracteristicas_servicio(codigo_servicio,codigo_caracteristica,rol,obligatoria,indice,estado,usuario_insercion,fecha_insercion) values (" + codigoServicio + "," + "" + codigoCaracteristica + "," + "'" + rol + "'," + "'" + obligatoria + "'," + "" + indice + "," + "'" + estado + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
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
/*     */ 
/*     */ 
/*     */       
/* 261 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 263 */     catch (Exception e) {
/* 264 */       e.printStackTrace();
/* 265 */       Utilidades.writeError("%CaracteristicasServicioDAO:crearRegistro ", e);
/* 266 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 268 */     return rta;
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
/*     */ 
/*     */   
/*     */   public RespuestaBD modificarRegistro(int codigoServicio, int codigoCaracteristica, String rol, String obligatoria, int indice, String estado, String usuarioModificacion) {
/* 285 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 288 */       String s = "update caracteristicas_servicio set  obligatoria='" + obligatoria + "'," + " indice=" + indice + "," + " rol='" + rol + "'," + " estado='" + estado + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " codigo_servicio=" + codigoServicio + " and codigo_caracteristica=" + codigoCaracteristica;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 298 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 300 */     catch (Exception e) {
/* 301 */       e.printStackTrace();
/* 302 */       Utilidades.writeError("CaracteristicasServicioDAO:modificarRegistro ", e);
/* 303 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 305 */     return rta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<CaracteristicasServicioDTO> cargarParaCorreo(int servicio) {
/* 316 */     Collection<CaracteristicasServicioDTO> resultados = new ArrayList<CaracteristicasServicioDTO>();
/*     */     try {
/* 318 */       String s = "select c.Codigo, c.Descripcion from Caracteristicas c, Caracteristicas_Servicio Cs where  c.Codigo = Cs.Codigo_Caracteristica   and Cs.Codigo_Servicio = " + servicio + "   and upper(c.descripcion) like '%CORREO%'" + "  order by c.descripcion";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 327 */       boolean rta = this.dat.parseSql(s);
/* 328 */       if (!rta) return resultados;
/*     */       
/* 330 */       this.rs = this.dat.getResultSet();
/* 331 */       while (this.rs.next())
/*     */       {
/* 333 */         CaracteristicasServicioDTO reg = new CaracteristicasServicioDTO();
/* 334 */         reg.setCodigoCaracteristica(this.rs.getInt("codigo"));
/* 335 */         reg.setDescripcion(this.rs.getString("descripcion"));
/* 336 */         resultados.add(reg);
/*     */       }
/*     */     
/* 339 */     } catch (Exception e) {
/* 340 */       e.printStackTrace();
/* 341 */       Utilidades.writeError("CaracteristicasDAO:cargarTodosParaServicio ", e);
/*     */     } 
/* 343 */     return resultados;
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
/*     */   public Collection<CaracteristicasServicioDTO> cargarParaServicio(int servicio) {
/* 355 */     Collection<CaracteristicasServicioDTO> resultados = new ArrayList<CaracteristicasServicioDTO>();
/*     */     try {
/* 357 */       String s = "select c.Codigo, c.Descripcion from Caracteristicas c, Caracteristicas_Servicio Cs where  c.Codigo = Cs.Codigo_Caracteristica   and Cs.Codigo_Servicio = " + servicio + "   and c.tipo='2'" + "  order by c.descripcion";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 366 */       boolean rta = this.dat.parseSql(s);
/* 367 */       if (!rta) return resultados;
/*     */       
/* 369 */       this.rs = this.dat.getResultSet();
/* 370 */       while (this.rs.next())
/*     */       {
/* 372 */         CaracteristicasServicioDTO reg = new CaracteristicasServicioDTO();
/* 373 */         reg.setCodigoCaracteristica(this.rs.getInt("codigo"));
/* 374 */         reg.setDescripcion(this.rs.getString("descripcion"));
/* 375 */         resultados.add(reg);
/*     */       }
/*     */     
/* 378 */     } catch (Exception e) {
/* 379 */       e.printStackTrace();
/* 380 */       Utilidades.writeError("CaracteristicasDAO:cargarTodosParaServicio ", e);
/*     */     } 
/* 382 */     return resultados;
/*     */   }
/*     */   
/*     */   public boolean cargarTodosParaServicio(int servicio, String rol) {
/*     */     try {
/* 387 */       String s = "select caracteristicas_servicio.*,caracteristicas.descripcion nombre_codigo_caracteristica,caracteristicas.tipo,caracteristicas.longitud from caracteristicas,caracteristicas_servicio  where codigo_caracteristica=caracteristicas.codigo  and caracteristicas_servicio.codigo_servicio=" + servicio;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 395 */       if (rol.equals("C") || rol.equals("P")) {
/* 396 */         s = s + " and caracteristicas_servicio.rol='" + rol + "'";
/*     */       }
/* 398 */       if (rol.equals("F")) {
/* 399 */         s = s + " order by caracteristicas_servicio.Indice";
/*     */       } else {
/*     */         
/* 402 */         s = s + " order by caracteristicas_servicio.rol,caracteristicas_servicio.obligatoria desc,caracteristicas_servicio.Indice";
/*     */       } 
/* 404 */       boolean rta = this.dat.parseSql(s);
/* 405 */       if (!rta) return false; 
/* 406 */       this.rs = this.dat.getResultSet();
/* 407 */       return true;
/*     */     }
/* 409 */     catch (Exception e) {
/* 410 */       e.printStackTrace();
/* 411 */       Utilidades.writeError("CaracteristicasDAO:cargarTodosParaServicio ", e);
/*     */       
/* 413 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\CaracteristicasServicioDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */