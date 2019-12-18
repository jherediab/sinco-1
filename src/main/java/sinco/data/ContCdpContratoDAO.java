/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.ContCdpContratoDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContCdpContratoDAO;
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
/*     */ public class ContCdpContratoDAO
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
/*     */   public ContCdpContratoDTO next() {
/*     */     try {
/*  60 */       if (this.rs.next()) {
/*  61 */         return leerRegistro();
/*     */       }
/*     */     }
/*  64 */     catch (Exception e) {
/*  65 */       e.printStackTrace();
/*  66 */       Utilidades.writeError("ContCdpContratoDAO:next ", e);
/*     */     } 
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContCdpContratoDTO leerRegistro() {
/*     */     try {
/*  78 */       ContCdpContratoDTO reg = new ContCdpContratoDTO();
/*  79 */       reg.setConsecutivoContrato(this.rs.getInt("consecutivo_contrato"));
/*  80 */       reg.setCodigoCertificado(this.rs.getString("codigo_certificado"));
/*  81 */       reg.setAnio(this.rs.getString("anio"));
/*  82 */       reg.setValorCertificado(this.rs.getDouble("valor_certificado"));
/*  83 */       reg.setFechaExpedicion(this.rs.getString("fecha_expedicion"));
/*  84 */       reg.setFechaVencimiento(this.rs.getString("fecha_vencimiento"));
/*  85 */       reg.setImputacion(this.rs.getString("imputacion"));
/*  86 */       reg.setNumeroContrato(this.rs.getString("numero_contrato"));
/*  87 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  88 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  89 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  90 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  91 */       return reg;
/*     */     }
/*  93 */     catch (Exception e) {
/*  94 */       e.printStackTrace();
/*  95 */       Utilidades.writeError("ContCdpContratoDAO:leerRegistro ", e);
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
/*     */   public String cargarTodosParaPoliza(int consecutivoContrato) {
/*     */     try {
/* 109 */       String s = "select  list(t.codigo_certificado) as codigo_certificado from cont_cdp t,  cont_cdp_contrato c where  t.codigo_certificado=c.codigo_certificado and t.anio=c.anio and c.consecutivo_contrato = " + consecutivoContrato;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 117 */       boolean rtaDB = this.dat.parseSql(s);
/* 118 */       if (!rtaDB) {
/* 119 */         return null;
/*     */       }
/* 121 */       this.rs = this.dat.getResultSet();
/* 122 */       if (this.rs.next()) {
/* 123 */         return this.rs.getString("codigo_certificado");
/*     */       }
/*     */     }
/* 126 */     catch (Exception e) {
/* 127 */       e.printStackTrace();
/* 128 */       Utilidades.writeError("ContCdpContratoDAO:cargarTodos ", e);
/*     */     } 
/* 130 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<ContCdpContratoDTO> cargarTodos(int consecutivoContrato) {
/* 141 */     Collection<ContCdpContratoDTO> resultados = new ArrayList<ContCdpContratoDTO>();
/*     */     try {
/* 143 */       String s = "select t.consecutivo_contrato,t.codigo_certificado,t.anio,t.imputacion,c.valor_certificado,c.fecha_expedicion,c.fecha_vencimiento,co.numero_contrato, t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion  from cont_cdp_contrato t left join cont_cdp c on (c.codigo_certificado = t.codigo_certificado and c.anio = t.anio ) left join cont_contrato co on (co.consecutivo_contrato = t.consecutivo_contrato) where 1=1 ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 160 */       if (consecutivoContrato > 0) {
/* 161 */         s = s + " and t.consecutivo_contrato = " + consecutivoContrato;
/*     */       }
/* 163 */       s = s + " order by 1";
/* 164 */       boolean rtaDB = this.dat.parseSql(s);
/* 165 */       if (!rtaDB) {
/* 166 */         return resultados;
/*     */       }
/* 168 */       this.rs = this.dat.getResultSet();
/* 169 */       while (this.rs.next()) {
/* 170 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 173 */     catch (Exception e) {
/* 174 */       e.printStackTrace();
/* 175 */       Utilidades.writeError("ContCdpContratoDAO:cargarTodos ", e);
/*     */     } 
/* 177 */     return resultados;
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
/*     */   public boolean eliminarRegistro(String codigoCertificado, String anio, int consecutivoContrato) {
/*     */     try {
/* 191 */       String s = "delete from cont_cdp_contrato where  codigo_certificado='" + codigoCertificado + "'" + " and anio='" + anio + "'" + " and consecutivo_contrato=" + consecutivoContrato;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 196 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 199 */     catch (Exception e) {
/* 200 */       e.printStackTrace();
/* 201 */       Utilidades.writeError("ContCdpContratoDAO:eliminarCDPContrato ", e);
/*     */       
/* 203 */       return false;
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
/*     */   public ContCdpContratoDTO cargarRegistro(int consecutivoContrato, String codigoCertificado, String anio) {
/*     */     try {
/* 217 */       String s = "select t.consecutivo_contrato,t.codigo_certificado,t.anio,t.imputacion,c.valor_certificado,c.fecha_expedicion,c.fecha_vencimiento,co.numero_contrato, t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion  from cont_cdp_contrato t left join cont_cdp c on (c.codigo_certificado = t.codigo_certificado and c.anio = t.anio ) left join cont_contrato co on (co.consecutivo_contrato = t.consecutivo_contrato) where  t.consecutivo_contrato = " + consecutivoContrato + " and t.codigo_certificado = '" + codigoCertificado + "'" + " and t.anio= '" + anio + "'";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 241 */       this.rs = this.dat.getResultSet();
/* 242 */       if (this.rs.next()) {
/* 243 */         return leerRegistro();
/*     */       }
/*     */     }
/* 246 */     catch (Exception e) {
/* 247 */       e.printStackTrace();
/* 248 */       Utilidades.writeError("ContCdpContratoDAO:cargarTodos ", e);
/*     */     } 
/* 250 */     return null;
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
/*     */   public boolean crearRegistro(int consecutivoContrato, int consecutivoAdicion, String codigoCertificado, String anio, String imputacion, String usuarioInsercion) {
/*     */     try {
/* 269 */       String s = "insert into cont_cdp_contrato(consecutivo_contrato,consecutivo_adicion,codigo_certificado,imputacion,anio,usuario_insercion,fecha_insercion) values (" + consecutivoContrato + "," + "" + consecutivoAdicion + "," + "'" + codigoCertificado + "'," + "'" + imputacion + "'," + "'" + anio + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 286 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 289 */     catch (Exception e) {
/* 290 */       e.printStackTrace();
/* 291 */       Utilidades.writeError("ContCdpContratoDAO:crearCDPContrato ", e);
/*     */       
/* 293 */       return false;
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
/*     */   public boolean modificarRegistro(int consecutivoContrato, int consecutivoAdicion, String codigoCertificado, String anio, String imputacion, String usuarioModificacion) {
/*     */     try {
/* 310 */       String s = "update cont_cdp_contrato set  consecutivo_contrato=" + consecutivoContrato + "," + " imputacion='" + imputacion + "'," + " codigo_certificado='" + codigoCertificado + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + " where" + " codigo_certificado='" + codigoCertificado + "'" + " and consecutivo_adicion=" + consecutivoAdicion + " and anio='" + anio + "'" + " and consecutivo_contrato=" + consecutivoContrato + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 322 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 325 */     catch (Exception e) {
/* 326 */       e.printStackTrace();
/* 327 */       Utilidades.writeError("ContCdpContratoDAO:modificarCDPContrato ", e);
/*     */       
/* 329 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ContCdpContratoDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */