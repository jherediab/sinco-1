/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.PoaMaestroInsumoProveedorDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.PoaMaestroInsumoProveedorDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PoaMaestroInsumoProveedorDAO
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
/*  50 */       Utilidades.writeError("PoaMaestroInsumoProveedorDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaMaestroInsumoProveedorDTO next() {
/*     */     try {
/*  61 */       if (this.rs.next()) {
/*  62 */         return leerRegistro();
/*     */       }
/*     */     }
/*  65 */     catch (Exception e) {
/*  66 */       e.printStackTrace();
/*  67 */       Utilidades.writeError("PoaMaestroInsumoProveedorDAO:next ", e);
/*     */     } 
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaMaestroInsumoProveedorDTO leerRegistro() {
/*     */     try {
/*  79 */       PoaMaestroInsumoProveedorDTO reg = new PoaMaestroInsumoProveedorDTO();
/*     */       
/*  81 */       reg.setIdInsumo(this.rs.getInt("id_insumo"));
/*  82 */       reg.setIdProveedor(this.rs.getInt("id_proveedor"));
/*  83 */       reg.setIdMaestro(this.rs.getInt("id_maestro"));
/*  84 */       reg.setFechaProgramadaEntrega(this.rs.getString("fecha_programada_entrega"));
/*  85 */       reg.setFechaRealEntrega(this.rs.getString("fecha_real_entrega"));
/*  86 */       reg.setEstado(this.rs.getString("estado"));
/*  87 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  88 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  89 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  90 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  91 */       return reg;
/*     */     }
/*  93 */     catch (Exception e) {
/*  94 */       e.printStackTrace();
/*  95 */       Utilidades.writeError("PoaMaestroInsumoProveedorDAO:leerRegistro ", e);
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
/*     */   public Collection<PoaMaestroInsumoProveedorDTO> cargarTodos() {
/* 107 */     Collection<PoaMaestroInsumoProveedorDTO> resultados = new ArrayList<PoaMaestroInsumoProveedorDTO>();
/*     */     try {
/* 109 */       String s = "select t.id_insumo,t.id_proveedor,t.id_maestro,t.fecha_programada_entrega,t.fecha_real_entrega,t.estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_MAESTRO_INSUMO_PROVEEDOR t  where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 122 */       s = s + " order by 1";
/* 123 */       boolean rtaDB = this.dat.parseSql(s);
/* 124 */       if (!rtaDB) {
/* 125 */         return resultados;
/*     */       }
/* 127 */       this.rs = this.dat.getResultSet();
/* 128 */       while (this.rs.next()) {
/* 129 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 132 */     catch (Exception e) {
/* 133 */       e.printStackTrace();
/* 134 */       Utilidades.writeError("PoaMaestroInsumoProveedorDAO:cargarTodos ", e);
/*     */     } 
/* 136 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<PoaMaestroInsumoProveedorDTO> cargarTodos(int codigoPoa) {
/* 143 */     Collection<PoaMaestroInsumoProveedorDTO> resultados = new ArrayList<PoaMaestroInsumoProveedorDTO>();
/*     */     try {
/* 145 */       String s = "select t.id_insumo,t.id_proveedor,t.id_maestro,t.fecha_programada_entrega,t.fecha_real_entrega,t.estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_MAESTRO_INSUMO_PROVEEDOR t where t.id_maestro=" + codigoPoa;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 158 */       s = s + " order by 1";
/* 159 */       boolean rtaDB = this.dat.parseSql(s);
/* 160 */       if (!rtaDB) {
/* 161 */         return resultados;
/*     */       }
/* 163 */       this.rs = this.dat.getResultSet();
/* 164 */       while (this.rs.next()) {
/* 165 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 168 */     catch (Exception e) {
/* 169 */       e.printStackTrace();
/* 170 */       Utilidades.writeError("PoaMaestroInsumoProveedorDAO:cargarTodos ", e);
/*     */     } 
/* 172 */     return resultados;
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
/*     */   public PoaMaestroInsumoProveedorDTO cargarRegistro(int idInsumo, int idProveedor, int idMaestro) {
/*     */     try {
/* 185 */       String s = "select t.id_insumo,t.id_proveedor,t.id_maestro,t.fecha_programada_entrega,t.fecha_real_entrega,t.estado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from POA_MAESTRO_INSUMO_PROVEEDOR t  where  t.id_insumo=" + idInsumo + " and t.id_proveedor=" + idProveedor + " and t.id_maestro=" + idMaestro + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 202 */       boolean rtaDB = this.dat.parseSql(s);
/* 203 */       if (!rtaDB) {
/* 204 */         return null;
/*     */       }
/* 206 */       this.rs = this.dat.getResultSet();
/* 207 */       if (this.rs.next()) {
/* 208 */         return leerRegistro();
/*     */       }
/*     */     }
/* 211 */     catch (Exception e) {
/* 212 */       e.printStackTrace();
/* 213 */       Utilidades.writeError("PoaMaestroInsumoProveedorDAO:cargarPoaMaestroInsumoProveedor", e);
/*     */     } 
/* 215 */     return null;
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
/*     */   public RespuestaBD eliminarRegistro(int idInsumo, int idProveedor, int idMaestro) {
/* 227 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 230 */       String s = "delete from POA_MAESTRO_INSUMO_PROVEEDOR where  id_insumo=" + idInsumo + "  and id_proveedor=" + idProveedor + "  and id_maestro=" + idMaestro + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 236 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 238 */     catch (Exception e) {
/* 239 */       e.printStackTrace();
/* 240 */       Utilidades.writeError("PoaMaestroInsumoProveedorDAO:eliminarRegistro ", e);
/* 241 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 243 */     return rta;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RespuestaBD eliminarRegistro(int idMaestro) {
/* 249 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 252 */       String s = "delete from POA_MAESTRO_INSUMO_PROVEEDOR where   id_maestro=" + idMaestro + "";
/*     */ 
/*     */ 
/*     */       
/* 256 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 258 */     catch (Exception e) {
/* 259 */       e.printStackTrace();
/* 260 */       Utilidades.writeError("PoaMaestroInsumoProveedorDAO:eliminarRegistro ", e);
/* 261 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 263 */     return rta;
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
/*     */   public RespuestaBD crearRegistro(int idInsumo, int idProveedor, int idMaestro, String fechaProgramadaEntrega, String fechaRealEntrega, String estado, String usuarioInsercion) {
/* 279 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 282 */       String s = "insert into POA_MAESTRO_INSUMO_PROVEEDOR(id_insumo,id_proveedor,id_maestro,fecha_programada_entrega,fecha_real_entrega,estado,fecha_insercion,usuario_insercion) values (" + idInsumo + "," + "" + idProveedor + "," + "" + idMaestro + "," + "" + ((fechaProgramadaEntrega.length() > 0) ? Utilidades.formatoFecha2(fechaProgramadaEntrega) : "null") + "," + "" + ((fechaRealEntrega.length() > 0) ? Utilidades.formatoFecha2(fechaRealEntrega) : "null") + "," + "'" + estado + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 301 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 303 */     catch (Exception e) {
/* 304 */       e.printStackTrace();
/* 305 */       Utilidades.writeError("%PoaMaestroInsumoProveedorDAO:crearRegistro ", e);
/* 306 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 308 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int idInsumo, int idProveedor, int idMaestro, String fechaProgramadaEntrega, String fechaRealEntrega, String estado, String usuarioModificacion) {
/* 325 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*     */     try {
/* 328 */       String s = "update POA_MAESTRO_INSUMO_PROVEEDOR set  fecha_programada_entrega=" + ((fechaProgramadaEntrega.length() > 0) ? Utilidades.formatoFecha2(fechaProgramadaEntrega) : "null") + "," + " fecha_real_entrega=" + ((fechaRealEntrega.length() > 0) ? Utilidades.formatoFecha2(fechaRealEntrega) : "null") + "," + " estado='" + estado + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " id_insumo=" + idInsumo + " and id_proveedor=" + idProveedor + " and id_maestro=" + idMaestro + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 339 */       rta = this.dat.executeUpdate2(s);
/*     */     }
/* 341 */     catch (Exception e) {
/* 342 */       e.printStackTrace();
/* 343 */       Utilidades.writeError("PoaMaestroInsumoProveedorDAO:modificarRegistro ", e);
/* 344 */       rta.setMensaje(e.getMessage());
/*     */     } 
/* 346 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\PoaMaestroInsumoProveedorDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */