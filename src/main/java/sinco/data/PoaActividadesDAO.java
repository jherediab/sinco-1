/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PoaActividadesDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PoaActividadesDAO;
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
/*     */ public class PoaActividadesDAO
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
/*  50 */       Utilidades.writeError("PoaActividadesDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaActividadesDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("PoaActividadesDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaActividadesDTO leerRegistro() {
/*     */     try {
/*  79 */       PoaActividadesDTO reg = new PoaActividadesDTO();
/*     */       
/*  81 */       reg.setCodigoActividad(this.rs.getInt("codigo_actividad"));
/*  82 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  83 */       reg.setTipoActividad(this.rs.getString("tipo_actividad"));
/*  84 */       reg.setArea(this.rs.getInt("area"));
/*  85 */       reg.setEstado(this.rs.getString("estado"));
/*  86 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  87 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  88 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  89 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  90 */       reg.setNombreTipoActividad(this.rs.getString("nombre_tipo_actividad"));
/*  91 */       reg.setNombreArea(this.rs.getString("nombre_area"));
/*  92 */       reg.setNombreEstado(this.rs.getString("nombre_estado"));
/*  93 */       return reg;
/*     */     }
/*  95 */     catch (Exception e) {
/*  96 */       e.printStackTrace();
/*  97 */       Utilidades.writeError("PoaActividadesDAO:leerRegistro ", e);
/*     */       
/*  99 */       return null;
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
/*     */   
/*     */   public Collection<PoaActividadesDTO> cargarTodos(String descripcion, String tipoActividad, int area, String estado) {
/* 112 */     Collection<PoaActividadesDTO> resultados = new ArrayList<PoaActividadesDTO>();
/*     */     try {
/* 114 */       String s = "select t.codigo_actividad,t.descripcion,t.tipo_actividad,m1.DESCRIPCION as nombre_tipo_actividad,t.area,r2.DESCRIPCION as nombre_area,t.estado,m3.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_ACTIVIDADES t  left join sis_multivalores m1 on (m1.tabla='POA_TIPOS_ACTIVIDAD' and m1.VALOR=t.tipo_actividad) left join UNIDADES_DEPENDENCIA r2 on (r2.CODIGO=t.area) left join sis_multivalores m3 on (m3.tabla='ESTADO_REGISTRO' and m3.VALOR=t.estado) where 1=1";
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
/* 132 */       if (descripcion.length() > 0) {
/* 133 */         s = s + " and upper(t.descripcion) like upper('%" + descripcion + "%')";
/*     */       }
/* 135 */       if (tipoActividad.length() > 0) {
/* 136 */         s = s + " and upper(t.tipo_actividad) like upper('%" + tipoActividad + "%')";
/*     */       }
/* 138 */       if (area > 0) {
/* 139 */         s = s + " and t.area=" + area;
/*     */       }
/* 141 */       if (estado.length() > 0) {
/* 142 */         s = s + " and upper(t.estado) like upper('%" + estado + "%')";
/*     */       }
/* 144 */       s = s + " order by 1";
/* 145 */       boolean rtaDB = this.dat.parseSql(s);
/* 146 */       if (!rtaDB) {
/* 147 */         return resultados;
/*     */       }
/* 149 */       this.rs = this.dat.getResultSet();
/* 150 */       while (this.rs.next()) {
/* 151 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 154 */     catch (Exception e) {
/* 155 */       e.printStackTrace();
/* 156 */       Utilidades.writeError("PoaActividadesDAO:cargarTodos ", e);
/*     */     } 
/* 158 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaActividadesDTO cargarRegistro(int codigoActividad) {
/*     */     try {
/* 168 */       String s = "select t.codigo_actividad,t.descripcion,t.tipo_actividad,m1.DESCRIPCION as nombre_tipo_actividad,t.area,r2.DESCRIPCION as nombre_area,t.estado,m3.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_ACTIVIDADES t  left join sis_multivalores m1 on (m1.tabla='POA_TIPOS_ACTIVIDAD' and m1.VALOR=t.tipo_actividad) left join UNIDADES_DEPENDENCIA r2 on (r2.CODIGO=t.area) left join sis_multivalores m3 on (m3.tabla='ESTADO_REGISTRO' and m3.VALOR=t.estado) where  t.codigo_actividad=" + codigoActividad + "";
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
/* 199 */       Utilidades.writeError("PoaActividadesDAO:cargarPoaActividades", e);
/*     */     } 
/* 201 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 210 */     int inumero = 1;
/* 211 */     String s = "select max(codigo_actividad) from POA_ACTIVIDADES ";
/*     */     
/*     */     try {
/* 214 */       boolean rta = this.dat.parseSql(s);
/* 215 */       if (!rta) return 0; 
/* 216 */       this.rs = this.dat.getResultSet();
/* 217 */       if (this.rs.next()) {
/* 218 */         s = this.rs.getString(1);
/* 219 */         if (!this.rs.wasNull()) {
/* 220 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 223 */       return inumero;
/*     */     }
/* 225 */     catch (Exception e) {
/* 226 */       e.printStackTrace();
/* 227 */       Utilidades.writeError("PoaActividadesDAO:siguienteRegistro ", e);
/*     */       
/* 229 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int codigoActividad) {
/* 239 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 242 */       String s = "delete from POA_ACTIVIDADES where  codigo_actividad=" + codigoActividad + "";
/*     */ 
/*     */ 
/*     */       
/* 246 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 248 */     catch (Exception e) {
/* 249 */       e.printStackTrace();
/* 250 */       Utilidades.writeError("PoaActividadesDAO:eliminarRegistro ", e);
/* 251 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 253 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int codigoActividad, String descripcion, String tipoActividad, int area, String estado, String usuarioInsercion) {
/* 269 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 271 */     int elSiguiente = siguienteRegistro();
/* 272 */     if (elSiguiente == 0) {
/* 273 */       rta.setMensaje("Generando secuencia");
/* 274 */       return rta;
/*     */     } 
/*     */     
/*     */     try {
/* 278 */       String s = "insert into POA_ACTIVIDADES(codigo_actividad,descripcion,tipo_actividad,area,estado,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "'" + descripcion + "'," + "'" + tipoActividad + "'," + "" + area + "," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
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
/* 295 */       rta = this.dat.executeUpdate2(s);
/* 296 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 298 */     catch (Exception e) {
/* 299 */       e.printStackTrace();
/* 300 */       Utilidades.writeError("%PoaActividadesDAO:crearRegistro ", e);
/* 301 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 303 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int codigoActividad, String descripcion, String tipoActividad, int area, String estado, String usuarioModificacion) {
/* 319 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 322 */       String s = "update POA_ACTIVIDADES set  descripcion='" + descripcion + "'," + " tipo_actividad='" + tipoActividad + "'," + " area=" + area + "," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo_actividad=" + codigoActividad + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 332 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 334 */     catch (Exception e) {
/* 335 */       e.printStackTrace();
/* 336 */       Utilidades.writeError("PoaActividadesDAO:modificarRegistro ", e);
/* 337 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 339 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PoaActividadesDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */