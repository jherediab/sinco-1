/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.ContCdpContratoDTO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContCdpContratoDTO
/*     */ {
/*     */   private int consecutivoContrato;
/*     */   private String codigoCertificado;
/*     */   private String anio;
/*     */   private double valorCertificado;
/*     */   private String fechaExpedicion;
/*     */   private String fechaVencimiento;
/*     */   private String imputacion;
/*     */   private String usuarioInsercion;
/*     */   private String fechaInsercion;
/*     */   private String usuarioModificacion;
/*     */   private String fechaModificacion;
/*     */   private String numeroContrato;
/*     */   
/*  39 */   public void setConsecutivoContrato(int p) { this.consecutivoContrato = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   public int getConsecutivoContrato() { return this.consecutivoContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public void setCodigoCertificado(String p) { this.codigoCertificado = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   public String getCodigoCertificado() { return (this.codigoCertificado == null) ? "" : this.codigoCertificado; }
/*     */ 
/*     */ 
/*     */   
/*  66 */   public void setAnio(String anio) { this.anio = anio; }
/*     */ 
/*     */ 
/*     */   
/*  70 */   public String getAnio() { return (this.anio == null) ? "" : this.anio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   public void setValorCertificado(double p) { this.valorCertificado = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public double getValorCertificado() { return this.valorCertificado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   public void setFechaExpedicion(String p) { this.fechaExpedicion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public String getFechaExpedicion() { return (this.fechaExpedicion == null) ? "" : this.fechaExpedicion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   public void setFechaVencimiento(String p) { this.fechaVencimiento = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   public String getFechaVencimiento() { return (this.fechaVencimiento == null) ? "" : this.fechaVencimiento; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 125 */   public void setImputacion(String imputacion) { this.imputacion = imputacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   public String getImputacion() { return (this.imputacion == null) ? "" : this.imputacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 164 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 172 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 180 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 188 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 196 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */   
/* 200 */   public void setNumeroContrato(String numeroContrato) { this.numeroContrato = numeroContrato; }
/*     */ 
/*     */ 
/*     */   
/* 204 */   public String getNumeroContrato() { return (this.numeroContrato == null) ? "" : this.numeroContrato; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\ContCdpContratoDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */