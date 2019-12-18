/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.ContRpDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContRpDAO;
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
/*     */ public class ContRpDAO
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
/*  49 */       Utilidades.writeError("ContRpDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContRpDTO next() {
/*     */     try {
/*  60 */       if (this.rs.next()) {
/*  61 */         return leerRegistro();
/*     */       }
/*     */     }
/*  64 */     catch (Exception e) {
/*  65 */       e.printStackTrace();
/*  66 */       Utilidades.writeError("ContRpDAO:next ", e);
/*     */     } 
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContRpDTO leerRegistro() {
/*     */     try {
/*  78 */       ContRpDTO reg = new ContRpDTO();
/*     */       
/*  80 */       reg.setNumeroRegistro(this.rs.getString("numero_registro"));
/*  81 */       reg.setFechaExpedicion(this.rs.getString("fecha_expedicion"));
/*  82 */       reg.setValor(this.rs.getDouble("valor"));
/*  83 */       reg.setDependencia(this.rs.getString("dependencia"));
/*  84 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  85 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  86 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  87 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  88 */       reg.setNombreDependencia(this.rs.getString("descripcion"));
/*  89 */       reg.setConsecutivoContrato(this.rs.getInt("consecutivo_contrato"));
/*  90 */       reg.setNumeroAdicion(this.rs.getInt("numero_adicion"));
/*  91 */       reg.setImputacion(this.rs.getString("imputacion"));
/*  92 */       reg.setAnio(this.rs.getString("anio"));
/*  93 */       return reg;
/*     */     }
/*  95 */     catch (Exception e) {
/*  96 */       e.printStackTrace();
/*  97 */       Utilidades.writeError("ContRpDAO:leerRegistro ", e);
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
/*     */   
/*     */   public Collection cargarTodos(int consecutivoContrato, String numeroRegistro, String fechaExpedicionDesde, String fechaExpedicionHasta, int numeroAdicion) {
/* 113 */     Collection resultados = new ArrayList();
/*     */     try {
/* 115 */       String s = "select  t.numero_registro,t.numero_adicion,t.fecha_expedicion,t.consecutivo_contrato,t.anio,t.valor,t.dependencia,u.descripcion,t.imputacion,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion  from cont_rp t, unidades_dependencia u where u.codigo=t.dependencia";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 131 */       if (consecutivoContrato > 0) {
/* 132 */         s = s + " and t.consecutivo_contrato = " + consecutivoContrato;
/*     */       }
/* 134 */       if (numeroRegistro.length() > 0) {
/* 135 */         s = s + " and upper(t.numero_registro) like upper('%" + numeroRegistro + "%')";
/*     */       }
/* 137 */       if (fechaExpedicionDesde.length() > 0) {
/* 138 */         s = s + " and t.fecha_expedicion>=" + Utilidades.formatoFecha2(fechaExpedicionDesde);
/*     */       }
/* 140 */       if (fechaExpedicionHasta.length() > 0) {
/* 141 */         s = s + " and t.fecha_expedicion < " + Utilidades.formatoFecha2(fechaExpedicionHasta);
/*     */       }
/* 143 */       if (numeroAdicion >= 0) {
/* 144 */         s = s + " and t.numero_adicion = " + numeroAdicion;
/*     */       }
/* 146 */       s = s + " order by 1";
/* 147 */       boolean rtaDB = this.dat.parseSql(s);
/* 148 */       if (!rtaDB) {
/* 149 */         return resultados;
/*     */       }
/* 151 */       this.rs = this.dat.getResultSet();
/* 152 */       while (this.rs.next()) {
/* 153 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 156 */     catch (Exception e) {
/* 157 */       e.printStackTrace();
/* 158 */       Utilidades.writeError("ContRpDAO:cargarTodos ", e);
/*     */     } 
/* 160 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContRpDTO cargarRegistro(String numeroRegistro, String anio) {
/*     */     try {
/* 169 */       String s = "select  t.numero_registro,t.numero_adicion,t.consecutivo_contrato,t.fecha_expedicion,t.valor,t.anio,t.dependencia,t.imputacion,u.descripcion,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion  from cont_rp t, unidades_dependencia u where u.codigo=t.dependencia  and t.anio='" + anio + "'" + " and t.numero_registro = '" + numeroRegistro + "'";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 187 */       boolean rtaDB = this.dat.parseSql(s);
/* 188 */       if (!rtaDB) {
/* 189 */         return null;
/*     */       }
/* 191 */       this.rs = this.dat.getResultSet();
/* 192 */       if (this.rs.next()) {
/* 193 */         return leerRegistro();
/*     */       }
/*     */     }
/* 196 */     catch (Exception e) {
/* 197 */       e.printStackTrace();
/* 198 */       Utilidades.writeError("ContRpDAO:cargarContRp", e);
/*     */     } 
/* 200 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(String numeroRegistro, String anio) {
/*     */     try {
/* 211 */       String s = "delete from cont_rp where  anio='" + anio + "'" + " and numero_registro = '" + numeroRegistro + "'";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 216 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 219 */     catch (Exception e) {
/* 220 */       e.printStackTrace();
/* 221 */       Utilidades.writeError("ContRpDAO:eliminarRegistro ", e);
/*     */       
/* 223 */       return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean crearRegistro(String numeroRegistro, String anio, String fechaExpedicion, double valor, String dependencia, int consecutivoContrato, int numeroAdicion, String imputacion, String usuarioInsercion) {
/*     */     try {
/* 244 */       String s = "insert into cont_rp(numero_registro,anio,fecha_expedicion,valor,dependencia,consecutivo_contrato,numero_adicion,imputacion,usuario_insercion,fecha_insercion) values ('" + numeroRegistro + "'," + "'" + anio + "'," + "" + Utilidades.formatoFecha2(fechaExpedicion) + "," + "" + valor + "," + "'" + dependencia + "'," + "" + consecutivoContrato + "," + "" + numeroAdicion + "," + "'" + imputacion + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 267 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 270 */     catch (Exception e) {
/* 271 */       e.printStackTrace();
/* 272 */       Utilidades.writeError("%ContRpDAO:crearRegistro ", e);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean modificarRegistro(String numeroRegistro, String anio, String fechaExpedicion, double valor, String dependencia, int consecutivoContrato, int numeroAdicion, String imputacion, String usuarioModificacion) {
/*     */     try {
/* 294 */       String s = "update cont_rp set  fecha_expedicion=" + Utilidades.formatoFecha2(fechaExpedicion) + "," + " valor=" + valor + "," + " dependencia='" + dependencia + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " imputacion='" + imputacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " numero_registro='" + numeroRegistro + "'" + " and anio='" + anio + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 305 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 308 */     catch (Exception e) {
/* 309 */       e.printStackTrace();
/* 310 */       Utilidades.writeError("ContRpDAO:modificarRegistro ", e);
/*     */       
/* 312 */       return false;
/*     */     } 
/*     */   }
/*     */   public String cargarFecha(int consecutivoContrato) {
/*     */     try {
/* 317 */       String s = "select    max(t.fecha_expedicion) as fecha  from cont_rp t where  t.consecutivo_contrato=" + consecutivoContrato + "";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 322 */       boolean rtaDB = this.dat.parseSql(s);
/* 323 */       if (!rtaDB) {
/* 324 */         return null;
/*     */       }
/* 326 */       this.rs = this.dat.getResultSet();
/* 327 */       if (this.rs.next()) {
/* 328 */         return this.rs.getString("fecha");
/*     */       }
/*     */     }
/* 331 */     catch (Exception e) {
/* 332 */       e.printStackTrace();
/* 333 */       Utilidades.writeError("ContRpDAO:cargarContRp", e);
/*     */     } 
/* 335 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ContRpDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */