/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.ParametrosAplicacionDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.ParametrosAplicacionDAO;
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
/*     */ public class ParametrosAplicacionDAO
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
/*  50 */       Utilidades.writeError("ParametrosAplicacionDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ParametrosAplicacionDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("ParametrosAplicacionDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ParametrosAplicacionDTO leerRegistro() {
/*     */     try {
/*  79 */       ParametrosAplicacionDTO reg = new ParametrosAplicacionDTO();
/*     */       
/*  81 */       reg.setCodigo(this.rs.getString("codigo"));
/*  82 */       reg.setNombre(this.rs.getString("nombre"));
/*  83 */       reg.setEntero(this.rs.getInt("entero"));
/*  84 */       reg.setReal(this.rs.getDouble("real"));
/*  85 */       reg.setCaracter(this.rs.getString("caracter"));
/*  86 */       reg.setFecha(this.rs.getString("fecha"));
/*  87 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  88 */       reg.setControl(this.rs.getString("control"));
/*  89 */       reg.setBatch(this.rs.getString("batch"));
/*  90 */       reg.setLinea(this.rs.getString("linea"));
/*  91 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  92 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  93 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  94 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  95 */       reg.setNombreControl(this.rs.getString("nombre_control"));
/*  96 */       return reg;
/*     */     }
/*  98 */     catch (Exception e) {
/*  99 */       e.printStackTrace();
/* 100 */       Utilidades.writeError("ParametrosAplicacionDAO:leerRegistro ", e);
/*     */       
/* 102 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<ParametrosAplicacionDTO> cargarTodos(String codigo, String nombre) {
/* 113 */     Collection<ParametrosAplicacionDTO> resultados = new ArrayList<ParametrosAplicacionDTO>();
/*     */     try {
/* 115 */       String s = "select t.codigo,t.nombre,t.entero,t.real,t.caracter,t.fecha,t.descripcion,t.control,m1.DESCRIPCION as nombre_control,t.batch,t.linea,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from parametros_aplicacion t  left join sis_multivalores m1 on (m1.tabla='TIPO_PARAMETRO' and m1.VALOR=t.control) where 1=1";
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
/* 134 */       if (codigo.length() > 0) {
/* 135 */         s = s + " and upper(t.codigo) like upper('%" + codigo + "%')";
/*     */       }
/* 137 */       if (nombre.length() > 0) {
/* 138 */         s = s + " and upper(t.nombre) like upper('%" + nombre + "%')";
/*     */       }
/* 140 */       s = s + " order by 1";
/* 141 */       boolean rtaDB = this.dat.parseSql(s);
/* 142 */       if (!rtaDB) {
/* 143 */         return resultados;
/*     */       }
/* 145 */       this.rs = this.dat.getResultSet();
/* 146 */       while (this.rs.next()) {
/* 147 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 150 */     catch (Exception e) {
/* 151 */       e.printStackTrace();
/* 152 */       Utilidades.writeError("ParametrosAplicacionDAO:cargarTodos ", e);
/*     */     } 
/* 154 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ParametrosAplicacionDTO cargarRegistro(String codigo) {
/*     */     try {
/* 164 */       String s = "select t.codigo,t.nombre,t.entero,t.real,t.caracter,t.fecha,t.descripcion,t.control,m1.DESCRIPCION as nombre_control,t.batch,t.linea,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion from parametros_aplicacion t  left join sis_multivalores m1 on (m1.tabla='TIPO_PARAMETRO' and m1.VALOR=t.control) where  t.codigo='" + codigo + "'" + "";
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
/* 185 */       boolean rtaDB = this.dat.parseSql(s);
/* 186 */       if (!rtaDB) {
/* 187 */         return null;
/*     */       }
/* 189 */       this.rs = this.dat.getResultSet();
/* 190 */       if (this.rs.next()) {
/* 191 */         return leerRegistro();
/*     */       }
/*     */     }
/* 194 */     catch (Exception e) {
/* 195 */       e.printStackTrace();
/* 196 */       Utilidades.writeError("ParametrosAplicacionDAO:cargarParametrosAplicacion", e);
/*     */     } 
/* 198 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(String codigo) {
/* 208 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 211 */       String s = "delete from parametros_aplicacion where  codigo='" + codigo + "'" + "";
/*     */ 
/*     */ 
/*     */       
/* 215 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 217 */     catch (Exception e) {
/* 218 */       e.printStackTrace();
/* 219 */       Utilidades.writeError("ParametrosAplicacionDAO:eliminarRegistro ", e);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD crearRegistro(String codigo, String nombre, int entero, double real, String caracter, String fecha, String descripcion, String control, String batch, String linea, String usuarioInsercion) {
/* 243 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 246 */       String s = "insert into parametros_aplicacion(codigo,nombre,entero,real,caracter,fecha,descripcion,control,batch,linea,usuario_insercion,fecha_insercion) values ('" + codigo + "'," + "'" + nombre + "'," + "" + entero + "," + "" + real + "," + "'" + caracter + "'," + "" + ((fecha.length() > 0) ? Utilidades.formatoFecha2(fecha) : "null") + "," + "'" + descripcion + "'," + "'" + control + "'," + "'" + batch + "'," + "'" + linea + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 273 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 275 */     catch (Exception e) {
/* 276 */       e.printStackTrace();
/* 277 */       Utilidades.writeError("%ParametrosAplicacionDAO:crearRegistro ", e);
/* 278 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 280 */     return rta;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD modificarRegistro(String codigo, String nombre, int entero, double real, String caracter, String fecha, String descripcion, String control, String batch, String linea, String usuarioModificacion) {
/* 301 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 304 */       String s = "update parametros_aplicacion set  nombre='" + nombre + "'," + " entero=" + entero + "," + " real=" + real + "," + " caracter='" + caracter + "'," + " fecha=" + ((fecha.length() > 0) ? Utilidades.formatoFecha2(fecha) : "null") + "," + " descripcion='" + descripcion + "'," + " control='" + control + "'," + " batch='" + batch + "'," + " linea='" + linea + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " codigo='" + codigo + "'" + "";
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
/* 319 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 321 */     catch (Exception e) {
/* 322 */       e.printStackTrace();
/* 323 */       Utilidades.writeError("ParametrosAplicacionDAO:modificarRegistro ", e);
/* 324 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 326 */     return rta;
/*     */   }
/*     */   
/*     */   public String getDescripcion(String codigo, String nombre) {
/*     */     try {
/* 331 */       String s = "select descripcion from parametros_aplicacion  where upper(codigo) like upper('%" + codigo + "%') and upper(nombre) like " + " upper('%" + nombre + "%')";
/*     */ 
/*     */       
/* 334 */       boolean rtaDB = this.dat.parseSql(s);
/* 335 */       if (!rtaDB) {
/* 336 */         return null;
/*     */       }
/* 338 */       this.rs = this.dat.getResultSet();
/* 339 */       if (this.rs.next()) {
/* 340 */         return this.rs.getString("descripcion");
/*     */       }
/*     */     }
/* 343 */     catch (Exception e) {
/* 344 */       e.printStackTrace();
/* 345 */       Utilidades.writeError("ParametrosAplicacionDAO:getDescripcion", e);
/*     */     } 
/* 347 */     return null;
/*     */   }
/*     */   
/*     */   public String getValor(String valor, String codigo, String nombre) {
/*     */     try {
/* 352 */       String s = "select " + valor + " from parametros_aplicacion " + " where 1=1 ";
/*     */       
/* 354 */       if (codigo.length() > 0)
/* 355 */         s = s + " and upper(codigo) like upper('%" + codigo + "%') "; 
/* 356 */       if (nombre.length() > 0) {
/* 357 */         s = s + " and upper(nombre) like upper('%" + nombre + "%') ";
/*     */       }
/* 359 */       boolean rtaDB = this.dat.parseSql(s);
/* 360 */       if (!rtaDB) {
/* 361 */         return null;
/*     */       }
/* 363 */       this.rs = this.dat.getResultSet();
/* 364 */       if (this.rs.next()) {
/* 365 */         return this.rs.getString(valor);
/*     */       }
/*     */     }
/* 368 */     catch (Exception e) {
/* 369 */       e.printStackTrace();
/* 370 */       Utilidades.writeError("ParametrosAplicacionDAO:getDescripcion", e);
/*     */     } 
/* 372 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ParametrosAplicacionDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */