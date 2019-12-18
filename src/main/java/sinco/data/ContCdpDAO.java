/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.ContCdpDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContCdpDAO;
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
/*     */ public class ContCdpDAO
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
/*  49 */       Utilidades.writeError("ContCdpDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContCdpDTO next() {
/*     */     try {
/*  60 */       if (this.rs.next()) {
/*  61 */         return leerRegistro();
/*     */       }
/*     */     }
/*  64 */     catch (Exception e) {
/*  65 */       e.printStackTrace();
/*  66 */       Utilidades.writeError("ContCdpDAO:next ", e);
/*     */     } 
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContCdpDTO leerRegistro() {
/*     */     try {
/*  78 */       ContCdpDTO reg = new ContCdpDTO();
/*  79 */       reg.setCodigoCertificado(this.rs.getString("codigo_certificado"));
/*  80 */       reg.setAnio(this.rs.getString("anio"));
/*  81 */       reg.setValorCertificado(this.rs.getDouble("valor_certificado"));
/*  82 */       reg.setFechaExpedicion(this.rs.getString("fecha_expedicion"));
/*  83 */       reg.setFechaVencimiento(this.rs.getString("fecha_vencimiento"));
/*  84 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  85 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  86 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  87 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  88 */       return reg;
/*     */     }
/*  90 */     catch (Exception e) {
/*  91 */       e.printStackTrace();
/*  92 */       Utilidades.writeError("ContCdpDAO:leerRegistro ", e);
/*     */       
/*  94 */       return null;
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
/*     */   public Collection<ContCdpDTO> cargarTodos(String codigoCertificado, String fechaExpedicionDesde, String fechaExpedicionHasta, String fechaVencimientoDesde, String fechaVencimientoHasta) {
/* 108 */     Collection<ContCdpDTO> resultados = new ArrayList<ContCdpDTO>();
/*     */     try {
/* 110 */       String s = "select  t.codigo_certificado,t.anio,t.valor_certificado,t.fecha_expedicion,t.fecha_vencimiento,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion  from cont_cdp t  where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 123 */       if (codigoCertificado.length() > 0) {
/* 124 */         s = s + " and t.codigo_certificado = '" + codigoCertificado + "'";
/*     */       }
/* 126 */       if (fechaExpedicionDesde.length() > 0) {
/* 127 */         s = s + " and t.fecha_expedicion>=" + Utilidades.formatoFecha2(fechaExpedicionDesde);
/*     */       }
/* 129 */       if (fechaExpedicionHasta.length() > 0) {
/* 130 */         s = s + " and t.fecha_expedicion < " + Utilidades.formatoFecha2(fechaExpedicionHasta);
/*     */       }
/* 132 */       if (fechaVencimientoDesde.length() > 0) {
/* 133 */         s = s + " and t.fecha_vencimiento>=" + Utilidades.formatoFecha2(fechaVencimientoDesde);
/*     */       }
/* 135 */       if (fechaVencimientoHasta.length() > 0) {
/* 136 */         s = s + " and t.fecha_vencimiento < " + Utilidades.formatoFecha2(fechaVencimientoHasta);
/*     */       }
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
/* 150 */       Utilidades.writeError("ContCdpDAO:cargarTodos ", e);
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
/*     */   
/*     */   public String cargarTodosParaPoliza(int consecutivoContrato) {
/*     */     try {
/* 164 */       String s = "select  list(t.codigo_certificado) as codigo_certificado from cont_cdp t, cont_cdp_contrato c where t.codigo_certificado=c.codigo_certificado and t.anio=c.anio";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 170 */       if (consecutivoContrato > 0) {
/* 171 */         s = s + " and c.consecutivo_contrato = " + consecutivoContrato;
/*     */       }
/* 173 */       s = s + " order by 1";
/* 174 */       boolean rtaDB = this.dat.parseSql(s);
/* 175 */       if (!rtaDB) {
/* 176 */         return null;
/*     */       }
/* 178 */       this.rs = this.dat.getResultSet();
/* 179 */       if (this.rs.next()) {
/* 180 */         return this.rs.getString("codigo_certificado");
/*     */       }
/*     */     }
/* 183 */     catch (Exception e) {
/* 184 */       e.printStackTrace();
/* 185 */       Utilidades.writeError("ContCdpDAO:cargarTodos ", e);
/*     */     } 
/* 187 */     return null;
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
/*     */   public Collection<ContCdpDTO> cargarCDPsContrato(int consecutivoContrato, String codigoCertificado, String anio) {
/* 200 */     Collection<ContCdpDTO> resultados = new ArrayList<ContCdpDTO>();
/*     */     try {
/* 202 */       String s = "select t.codigo_certificado,t.anio,t.valor_certificado,t.fecha_expedicion,c.imputacion,t.fecha_vencimiento,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion, co.numero_contrato  from cont_cdp t left join cont_cdp_contrato c on (c.codigo_certificado = t.codigo_certificado and  c.anio = t.anio ) left join cont_contrato co on (co.consecutivo_contrato = c.consecutivo_contrato) where 1=1 ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 219 */       if (consecutivoContrato > 0) {
/* 220 */         s = s + " and c.consecutivo_contrato = " + consecutivoContrato;
/*     */       }
/* 222 */       if (codigoCertificado.length() > 0) {
/* 223 */         s = s + " and t.codigo_certificado = '" + codigoCertificado + "'";
/*     */       }
/* 225 */       if (anio.length() > 0) {
/* 226 */         s = s + " and t.anio= '" + anio + "'";
/*     */       }
/* 228 */       s = s + " order by 1";
/* 229 */       boolean rtaDB = this.dat.parseSql(s);
/* 230 */       if (!rtaDB) {
/* 231 */         return resultados;
/*     */       }
/* 233 */       this.rs = this.dat.getResultSet();
/* 234 */       while (this.rs.next()) {
/* 235 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 238 */     catch (Exception e) {
/* 239 */       e.printStackTrace();
/* 240 */       Utilidades.writeError("ContCdpDAO:cargarTodos ", e);
/*     */     } 
/* 242 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContCdpDTO cargarRegistro(String codigoCertificado, String anio) {
/*     */     try {
/* 253 */       String s = "select t.codigo_certificado,t.anio,t.valor_certificado,t.fecha_expedicion,t.fecha_vencimiento,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion  from cont_cdp t where  t.codigo_certificado='" + codigoCertificado + "'" + " and t.anio='" + anio + "'";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 267 */       boolean rtaDB = this.dat.parseSql(s);
/* 268 */       if (!rtaDB) {
/* 269 */         return null;
/*     */       }
/* 271 */       this.rs = this.dat.getResultSet();
/* 272 */       if (this.rs.next()) {
/* 273 */         return leerRegistro();
/*     */       
/*     */       }
/*     */     }
/* 277 */     catch (Exception e) {
/* 278 */       e.printStackTrace();
/* 279 */       Utilidades.writeError("ContCdpDAO:cargarContCdp", e);
/*     */     } 
/* 281 */     return null;
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
/*     */   public boolean crearRegistro(String codigoCertificado, double valorCertificado, String fechaExpedicion, String fechaVencimiento, String usuarioInsercion) {
/*     */     try {
/* 297 */       String s = "insert into cont_cdp(codigo_certificado,anio,valor_certificado,fecha_expedicion,fecha_vencimiento,usuario_insercion,fecha_insercion) values ('" + codigoCertificado + "'," + "'" + fechaExpedicion.substring(6, 10) + "'," + "" + valorCertificado + "," + "" + Utilidades.formatoFecha2(fechaExpedicion) + "," + "" + Utilidades.formatoFecha2(fechaVencimiento) + "," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 314 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 317 */     catch (Exception e) {
/* 318 */       e.printStackTrace();
/* 319 */       Utilidades.writeError("ContCdpDAO:crearRegistro ", e);
/*     */       
/* 321 */       return false;
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
/*     */   public boolean modificarRegistro(String codigoCertificado, double valorCertificado, String fechaExpedicion, String fechaVencimiento, String usuarioModificacion) {
/*     */     try {
/* 337 */       String s = "update cont_cdp set  valor_certificado=" + valorCertificado + "," + " fecha_expedicion=" + Utilidades.formatoFecha2(fechaExpedicion) + "," + " fecha_vencimiento=" + Utilidades.formatoFecha2(fechaVencimiento) + "," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " codigo_certificado='" + codigoCertificado + "'" + " and anio='" + fechaExpedicion.substring(6, 10) + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 347 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 350 */     catch (Exception e) {
/* 351 */       e.printStackTrace();
/* 352 */       Utilidades.writeError("ContCdpDAO:modificarRegistro ", e);
/*     */       
/* 354 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ContCdpDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */