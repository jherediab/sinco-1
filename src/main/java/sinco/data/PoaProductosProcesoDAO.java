/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PoaProductosProcesoDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PoaProductosProcesoDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PoaProductosProcesoDAO
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
/*  50 */       Utilidades.writeError("PoaProductosProcesoDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaProductosProcesoDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("PoaProductosProcesoDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaProductosProcesoDTO leerRegistro() {
/*     */     try {
/*  79 */       PoaProductosProcesoDTO reg = new PoaProductosProcesoDTO();
/*     */       
/*  81 */       reg.setCodigoProductoProceso(this.rs.getInt("codigo_producto_proceso"));
/*  82 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  83 */       reg.setArea(this.rs.getInt("area"));
/*  84 */       reg.setEstado(this.rs.getString("estado"));
/*  85 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  86 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  87 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  88 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  89 */       reg.setNombreArea(this.rs.getString("nombre_area"));
/*  90 */       reg.setNombreEstado(this.rs.getString("nombre_estado"));
/*  91 */       return reg;
/*     */     }
/*  93 */     catch (Exception e) {
/*  94 */       e.printStackTrace();
/*  95 */       Utilidades.writeError("PoaProductosProcesoDAO:leerRegistro ", e);
/*     */       
/*  97 */       return null;
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
/*     */   public Collection<PoaProductosProcesoDTO> cargarTodos(String descripcion, int area, String estado) {
/* 109 */     Collection<PoaProductosProcesoDTO> resultados = new ArrayList<PoaProductosProcesoDTO>();
/*     */     try {
/* 111 */       String s = "select t.codigo_producto_proceso,t.descripcion,t.area,r1.DESCRIPCION as nombre_area,t.estado,m2.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_PRODUCTOS_PROCESO t  left join UNIDADES_DEPENDENCIA r1 on (r1.CODIGO=t.area) left join sis_multivalores m2 on (m2.tabla='ESTADO_REGISTRO' and m2.VALOR=t.estado) where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 126 */       if (descripcion.length() > 0) {
/* 127 */         s = s + " and upper(t.descripcion) like upper('%" + descripcion + "%')";
/*     */       }
/* 129 */       if (area > 0) {
/* 130 */         s = s + " and t.area=" + area;
/*     */       }
/* 132 */       if (estado.length() > 0) {
/* 133 */         s = s + " and upper(t.estado) like upper('%" + estado + "%')";
/*     */       }
/* 135 */       s = s + " order by 1";
/* 136 */       boolean rtaDB = this.dat.parseSql(s);
/* 137 */       if (!rtaDB) {
/* 138 */         return resultados;
/*     */       }
/* 140 */       this.rs = this.dat.getResultSet();
/* 141 */       while (this.rs.next()) {
/* 142 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 145 */     catch (Exception e) {
/* 146 */       e.printStackTrace();
/* 147 */       Utilidades.writeError("PoaProductosProcesoDAO:cargarTodos ", e);
/*     */     } 
/* 149 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaProductosProcesoDTO cargarRegistro(int codigoProductoProceso) {
/*     */     try {
/* 159 */       String s = "select t.codigo_producto_proceso,t.descripcion,t.area,r1.DESCRIPCION as nombre_area,t.estado,m2.DESCRIPCION as nombre_estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_PRODUCTOS_PROCESO t  left join UNIDADES_DEPENDENCIA r1 on (r1.CODIGO=t.area) left join sis_multivalores m2 on (m2.tabla='ESTADO_REGISTRO' and m2.VALOR=t.estado) where  t.codigo_producto_proceso=" + codigoProductoProceso + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 176 */       boolean rtaDB = this.dat.parseSql(s);
/* 177 */       if (!rtaDB) {
/* 178 */         return null;
/*     */       }
/* 180 */       this.rs = this.dat.getResultSet();
/* 181 */       if (this.rs.next()) {
/* 182 */         return leerRegistro();
/*     */       }
/*     */     }
/* 185 */     catch (Exception e) {
/* 186 */       e.printStackTrace();
/* 187 */       Utilidades.writeError("PoaProductosProcesoDAO:cargarPoaProductosProceso", e);
/*     */     } 
/* 189 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 198 */     int inumero = 1;
/* 199 */     String s = "select max(codigo_producto_proceso) from POA_PRODUCTOS_PROCESO ";
/*     */     
/*     */     try {
/* 202 */       boolean rta = this.dat.parseSql(s);
/* 203 */       if (!rta) return 0; 
/* 204 */       this.rs = this.dat.getResultSet();
/* 205 */       if (this.rs.next()) {
/* 206 */         s = this.rs.getString(1);
/* 207 */         if (!this.rs.wasNull()) {
/* 208 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 211 */       return inumero;
/*     */     }
/* 213 */     catch (Exception e) {
/* 214 */       e.printStackTrace();
/* 215 */       Utilidades.writeError("PoaProductosProcesoDAO:siguienteRegistro ", e);
/*     */       
/* 217 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int codigoProductoProceso) {
/* 227 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 230 */       String s = "delete from POA_PRODUCTOS_PROCESO where  codigo_producto_proceso=" + codigoProductoProceso + "";
/*     */ 
/*     */ 
/*     */       
/* 234 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 236 */     catch (Exception e) {
/* 237 */       e.printStackTrace();
/* 238 */       Utilidades.writeError("PoaProductosProcesoDAO:eliminarRegistro ", e);
/* 239 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 241 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int codigoProductoProceso, String descripcion, int area, String estado, String usuarioInsercion) {
/* 256 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 258 */     int elSiguiente = siguienteRegistro();
/* 259 */     if (elSiguiente == 0) {
/* 260 */       rta.setMensaje("Generando secuencia");
/* 261 */       return rta;
/*     */     } 
/*     */     
/*     */     try {
/* 265 */       String s = "insert into POA_PRODUCTOS_PROCESO(codigo_producto_proceso,descripcion,area,estado,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "'" + descripcion + "'," + "" + area + "," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 280 */       rta = this.dat.executeUpdate2(s);
/* 281 */       rta.setSecuencia(elSiguiente);
/*     */     }
/* 283 */     catch (Exception e) {
/* 284 */       e.printStackTrace();
/* 285 */       Utilidades.writeError("%PoaProductosProcesoDAO:crearRegistro ", e);
/* 286 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 288 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int codigoProductoProceso, String descripcion, int area, String estado, String usuarioModificacion) {
/* 303 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 306 */       String s = "update POA_PRODUCTOS_PROCESO set  descripcion='" + descripcion + "'," + " area=" + area + "," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo_producto_proceso=" + codigoProductoProceso + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 315 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 317 */     catch (Exception e) {
/* 318 */       e.printStackTrace();
/* 319 */       Utilidades.writeError("PoaProductosProcesoDAO:modificarRegistro ", e);
/* 320 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 322 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PoaProductosProcesoDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */