/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.ContCdpEstudioDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContCdpEstudioDAO;
/*     */ import sinco.data.DBManager;
/*     */ 

/*     */ 
/*     */ public class ContCdpEstudioDAO
/*     */ {
/*     */   public ContCdpEstudioDTO leerRegistro(ResultSet rs) {
/*     */     try {
/*  35 */       ContCdpEstudioDTO reg = new ContCdpEstudioDTO();
/*     */       
/*  37 */       reg.setNumeroEstudio(rs.getInt("numero_estudio"));
/*  38 */       reg.setConsecutivoCdp(rs.getInt("consecutivo_cdp"));
/*  39 */       reg.setFechaExpedicion(rs.getString("fecha_expedicion"));
/*  40 */       reg.setCodigoArea(rs.getInt("codigo_area"));
/*  41 */       reg.setDescripcion(rs.getString("descripcion"));
/*  42 */       reg.setAnio(rs.getInt("anio"));
/*  43 */       reg.setCodigoImputacion(rs.getString("codigo_imputacion"));
/*  44 */       reg.setValorCertificado(rs.getDouble("valor_certificado"));
/*  45 */       reg.setVigencia(rs.getString("vigencia"));
/*  46 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  47 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  48 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  49 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*  50 */       reg.setNombreCodigoArea(rs.getString("nombre_codigo_area"));
/*  51 */       reg.setNombreCodigoImputacion(rs.getString("nombre_codigo_imputacion"));
/*  52 */       return reg;
/*     */     }
/*  54 */     catch (Exception e) {
/*  55 */       e.printStackTrace();
/*  56 */       Utilidades.writeError("ContCdpEstudioDAO:leerRegistro ", e);
/*     */       
/*  58 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContCdpEstudioDTO leerUltimoCDP(ResultSet rs) {
/*     */     try {
/*  68 */       ContCdpEstudioDTO reg = new ContCdpEstudioDTO();
/*     */       
/*  70 */       reg.setConsecutivoCdp(rs.getInt("maximo"));
/*     */       
/*  72 */       return reg;
/*     */     }
/*  74 */     catch (Exception e) {
/*  75 */       e.printStackTrace();
/*  76 */       Utilidades.writeError("ContCdpEstudioDAO:leerUltimoCDP ", e);
/*     */       
/*  78 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<ContCdpEstudioDTO> cargarTodos(int numeroEstudio) {
/*  88 */     Collection<ContCdpEstudioDTO> resultados = new ArrayList<ContCdpEstudioDTO>();
/*     */     
/*  90 */    DBManager dat = new DBManager();
/*     */     try {
/*  92 */       String s = "select t.numero_estudio,t.consecutivo_cdp,t.fecha_expedicion,t.codigo_area,r1.descripcion as nombre_codigo_area,t.descripcion,t.anio,t.codigo_imputacion,r2.descripcion as nombre_codigo_imputacion,t.valor_certificado,t.vigencia,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from cont_cdp_estudio t  left join unidades_dependencia r1 on (r1.codigo =t.codigo_area) left join cont_imputacion_presupuestal r2 on (r2.codigo_imputacion=t.codigo_imputacion and r2.anio=t.anio) where 1=1";
/*     */ 
  
/* 112 */       if (numeroEstudio > 0) {
/* 113 */         s = s + " and t.numero_estudio=" + numeroEstudio;
/*     */       }
/* 115 */       s = s + " order by 2";
/* 116 */       boolean rtaDB = dat.parseSql(s);
/* 117 */       if (!rtaDB) {
/* 118 */         return resultados;
/*     */       }
/* 120 */       ResultSet rs = dat.getResultSet();
/* 121 */       while (rs.next()) {
/* 122 */         resultados.add(leerRegistro(rs));
/*     */       }
/*     */     }
/* 125 */     catch (Exception e) {
/* 126 */       e.printStackTrace();
/* 127 */       Utilidades.writeError("ContCdpEstudioDAO:cargarTodos ", e);
/*     */     } finally {
/*     */       
/* 130 */       dat.close();
/*     */     } 
/* 132 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContCdpEstudioDTO cargarRegistro(int anio, int consecutivoCdp) {
/* 141 */    DBManager dat = new DBManager();
/*     */     try {
/* 143 */       String s = "select t.numero_estudio,t.consecutivo_cdp,t.fecha_expedicion,t.codigo_area,r1.descripcion as nombre_codigo_area,t.descripcion,t.anio,t.codigo_imputacion,r2.descripcion as nombre_codigo_imputacion,t.valor_certificado,t.vigencia,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from cont_cdp_estudio t  left join unidades_dependencia r1 on (r1.codigo =t.codigo_area) left join cont_imputacion_presupuestal r2 on (r2.codigo_imputacion=t.codigo_imputacion and r2.anio=t.anio) where  t.consecutivo_cdp=" + consecutivoCdp + " and t.anio=" + anio;

/*     */       
/* 164 */       boolean rtaDB = dat.parseSql(s);
/* 165 */       if (!rtaDB) {
/* 166 */         return null;
/*     */       }
/* 168 */       ResultSet rs = dat.getResultSet();
/* 169 */       if (rs.next()) {
/* 170 */         return leerRegistro(rs);
/*     */       }
/*     */     }
/* 173 */     catch (Exception e) {
/* 174 */       e.printStackTrace();
/* 175 */       Utilidades.writeError("ContCdpEstudioDAO:cargarContCdpEstudio", e);
/*     */     } finally {
/*     */       
/* 178 */       dat.close();
/*     */     } 
/* 180 */     return null;
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
/*     */   public RespuestaBD eliminarRegistro(int anio, int consecutivoCdp) {
/* 192 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 194 */   DBManager  dat = new DBManager();
/*     */     try {
/* 196 */       String s = "delete from cont_cdp_estudio where consecutivo_cdp=" + consecutivoCdp + " and anio=" + anio;
/*     */ 
/*     */       
/* 199 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 201 */     catch (Exception e) {
/* 202 */       e.printStackTrace();
/* 203 */       Utilidades.writeError("ContCdpEstudioDAO:eliminarRegistro ", e);
/* 204 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 207 */       dat.close();
/*     */     } 
/* 209 */     return rta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContCdpEstudioDTO consultarConsecutivoCDP() {
/* 219 */   DBManager  dat = new DBManager();
/*     */     try {
/* 221 */       String s = "select max(consecutivo_cdp) as maximo from cont_cdp_estudio";
/* 222 */       boolean rtaDB = dat.parseSql(s);
/* 223 */       if (!rtaDB) {
/* 224 */         return null;
/*     */       }
/* 226 */       ResultSet rs = dat.getResultSet();
/* 227 */       if (rs.next()) {
/* 228 */         return leerUltimoCDP(rs);
/*     */       }
/*     */     }
/* 231 */     catch (Exception e) {
/* 232 */       e.printStackTrace();
/* 233 */       Utilidades.writeError("ContCdpEstudioDAO:consultarConsecutivoCDP ", e);
/*     */     } finally {
/*     */       
/* 236 */       dat.close();
/*     */     } 
/* 238 */     return null;
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
/*     */   
/*     */   public RespuestaBD crearRegistro(int numeroEstudio, int anio, int consecutivoCdp, String fechaExpedicion, int codigoArea, String descripcion, String codigoImputacion, double valorCertificado, String vigencia, String usuarioInsercion) {
/* 260 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 262 */    DBManager dat = new DBManager();
/*     */     try {
/* 264 */       String s = "insert into cont_cdp_estudio(numero_estudio,consecutivo_cdp,fecha_expedicion,codigo_area,descripcion,anio,codigo_imputacion,valor_certificado,vigencia,fecha_insercion,usuario_insercion) values (" + numeroEstudio + "," + "" + consecutivoCdp + "," + "" + ((fechaExpedicion.length() > 0) ? Utilidades.formatoFecha2(fechaExpedicion) : "null") + "," + "" + codigoArea + "," + "'" + descripcion + "'," + "" + anio + "," + "'" + codigoImputacion + "'," + "" + valorCertificado + "," + "'" + vigencia + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 

/*     */ 
/*     */       
/* 289 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 291 */     catch (Exception e) {
/* 292 */       e.printStackTrace();
/* 293 */       Utilidades.writeError("%ContCdpEstudioDAO:crearRegistro ", e);
/* 294 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 297 */       dat.close();
/*     */     } 
/* 299 */     return rta;
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
/*     */   public RespuestaBD modificarRegistro(int numeroEstudio, int anio, int consecutivoCdp, String fechaExpedicion, int codigoArea, String descripcion, String codigoImputacion, double valorCertificado, String vigencia, String usuarioModificacion) {
/* 319 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/* 321 */   DBManager  dat = new DBManager();
/*     */     try {
/* 323 */       String s = "update cont_cdp_estudio set  fecha_expedicion=" + ((fechaExpedicion.length() > 0) ? Utilidades.formatoFecha2(fechaExpedicion) : "null") + "," + " codigo_area=" + codigoArea + "," + " descripcion='" + descripcion + "'," + " numero_estudio=" + numeroEstudio + "," + " codigo_imputacion='" + codigoImputacion + "'," + " valor_certificado=" + valorCertificado + "," + " vigencia='" + vigencia + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " anio=" + anio + "" + " and consecutivo_cdp=" + consecutivoCdp + "";

/*     */ 
/*     */       
/* 337 */       rta = dat.executeUpdate2(s);
/*     */     }
/* 339 */     catch (Exception e) {
/* 340 */       e.printStackTrace();
/* 341 */       Utilidades.writeError("ContCdpEstudioDAO:modificarRegistro ", e);
/* 342 */       rta.setMensaje(e.getMessage());
/*     */     } finally {
/*     */       
/* 345 */       dat.close();
/*     */     } 
/* 347 */     return rta;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ContCdpEstudioDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */