/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.ContImputacionPresupuestalDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContImputacionPresupuestalDAO;
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
/*     */ public class ContImputacionPresupuestalDAO
/*     */ {
/*     */   ResultSet rs;
/*  28 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  46 */       this.dat.close();
/*     */     }
/*  48 */     catch (Exception e) {
/*  49 */       Utilidades.writeError("ContImputacionPresupuestalDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContImputacionPresupuestalDTO next() {
/*     */     try {
/*  60 */       if (this.rs.next()) {
/*  61 */         return leerRegistro();
/*     */       }
/*     */     }
/*  64 */     catch (Exception e) {
/*  65 */       e.printStackTrace();
/*  66 */       Utilidades.writeError("ContImputacionPresupuestalDAO:next ", e);
/*     */     } 
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContImputacionPresupuestalDTO leerRegistro() {
/*     */     try {
/*  78 */       ContImputacionPresupuestalDTO reg = new ContImputacionPresupuestalDTO();
/*     */       
/*  80 */       reg.setCodigoImputacion(this.rs.getString("codigo_imputacion"));
/*  81 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  82 */       reg.setAnio(this.rs.getInt("anio"));
/*  83 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  84 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  85 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  86 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  87 */       return reg;
/*     */     }
/*  89 */     catch (Exception e) {
/*  90 */       e.printStackTrace();
/*  91 */       Utilidades.writeError("ContImputacionPresupuestalDAO:leerRegistro ", e);
/*     */       
/*  93 */       return null;
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
/*     */   public Collection<ContImputacionPresupuestalDTO> cargarTodos(String codigoImputacion, String descripcion, int anio) {
/* 105 */     Collection<ContImputacionPresupuestalDTO> resultados = new ArrayList<ContImputacionPresupuestalDTO>();
/*     */     try {
/* 107 */       String s = "select  t.codigo_imputacion,t.descripcion,t.anio,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion  from cont_imputacion_presupuestal t where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 117 */       if (codigoImputacion.length() > 0) {
/* 118 */         s = s + " and upper(t.codigo_imputacion) like upper('%" + codigoImputacion + "%')";
/*     */       }
/* 120 */       if (descripcion.length() > 0) {
/* 121 */         s = s + " and upper(t.descripcion) like upper('%" + descripcion + "%')";
/*     */       }
/* 123 */       if (anio > 0) {
/* 124 */         s = s + " and t.anio=" + anio;
/*     */       }
/* 126 */       s = s + " order by 2";
/* 127 */       boolean rtaDB = this.dat.parseSql(s);
/* 128 */       if (!rtaDB) {
/* 129 */         return resultados;
/*     */       }
/* 131 */       this.rs = this.dat.getResultSet();
/* 132 */       while (this.rs.next()) {
/* 133 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 136 */     catch (Exception e) {
/* 137 */       e.printStackTrace();
/* 138 */       Utilidades.writeError("ContImputacionPresupuestalDAO:cargarTodos ", e);
/*     */     } 
/* 140 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<ContImputacionPresupuestalDTO> cargarTodosCdp(String codigoImputacion, String descripcion, int anio, int consecutivoContrato, int adicion) {
/* 151 */     Collection<ContImputacionPresupuestalDTO> resultados = new ArrayList<ContImputacionPresupuestalDTO>();
/*     */     try {
/* 153 */       String s = "select t.codigo_imputacion,t.descripcion,t.anio,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion  from cont_imputacion_presupuestal t where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 164 */       if (anio > 0) {
/* 165 */         s = s + " and t.anio=" + anio;
/*     */       }
/* 167 */       s = s + " order by 1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 204 */         return resultados;
/*     */       }
/* 206 */       this.rs = this.dat.getResultSet();
/* 207 */       while (this.rs.next()) {
/* 208 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 211 */     catch (Exception e) {
/* 212 */       e.printStackTrace();
/* 213 */       Utilidades.writeError("ContImputacionPresupuestalDAO:cargarTodos ", e);
/*     */     } 
/* 215 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContImputacionPresupuestalDTO cargarRegistro(String codigoImputacion, int anio) {
/*     */     try {
/* 226 */       String s = "select  t.codigo_imputacion,t.descripcion,t.anio,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion  from cont_imputacion_presupuestal t where  t.codigo_imputacion='" + codigoImputacion + "'" + " and t.anio=" + anio;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 238 */       boolean rtaDB = this.dat.parseSql(s);
/* 239 */       if (!rtaDB) {
/* 240 */         return null;
/*     */       }
/* 242 */       this.rs = this.dat.getResultSet();
/* 243 */       if (this.rs.next()) {
/* 244 */         return leerRegistro();
/*     */       }
/*     */     }
/* 247 */     catch (Exception e) {
/* 248 */       e.printStackTrace();
/* 249 */       Utilidades.writeError("ContImputacionPresupuestalDAO:cargarContImputacionPresupuestal", e);
/*     */     } 
/* 251 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(String codigoImputacion, int anio) {
/*     */     try {
/* 263 */       String s = "delete from cont_imputacion_presupuestal where  codigo_imputacion='" + codigoImputacion + "'" + " and anio=" + anio;
/*     */ 
/*     */ 
/*     */       
/* 267 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 270 */     catch (Exception e) {
/* 271 */       e.printStackTrace();
/* 272 */       Utilidades.writeError("ContImputacionPresupuestalDAO:eliminarRegistro ", e);
/*     */       
/* 274 */       return false;
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
/*     */   
/*     */   public boolean crearRegistro(String codigoImputacion, String descripcion, int anio, String usuarioInsercion) {
/*     */     try {
/* 289 */       String s = "insert into cont_imputacion_presupuestal(codigo_imputacion,descripcion,anio,fecha_insercion,usuario_insercion) values ('" + codigoImputacion + "'," + "'" + descripcion + "'," + "" + anio + "," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 302 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 305 */     catch (Exception e) {
/* 306 */       e.printStackTrace();
/* 307 */       Utilidades.writeError("%ContImputacionPresupuestalDAO:crearRegistro ", e);
/*     */       
/* 309 */       return false;
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
/*     */   
/*     */   public boolean modificarRegistro(String codigoImputacion, String descripcion, int anio, String usuarioModificacion) {
/*     */     try {
/* 324 */       String s = "update cont_imputacion_presupuestal set  descripcion='" + descripcion + "'," + " anio=" + anio + "," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo_imputacion='" + codigoImputacion + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 332 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 335 */     catch (Exception e) {
/* 336 */       e.printStackTrace();
/* 337 */       Utilidades.writeError("ContImputacionPresupuestalDAO:modificarRegistro ", e);
/*     */       
/* 339 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ContImputacionPresupuestalDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */