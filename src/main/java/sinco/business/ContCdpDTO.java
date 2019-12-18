/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.ContCdpDTO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContCdpDTO
/*     */ {
/*     */   private String codigoCertificado;
/*     */   private String anio;
/*     */   private double valorCertificado;
/*     */   private String fechaExpedicion;
/*     */   private String fechaVencimiento;
/*     */   private String usuarioInsercion;
/*     */   private String fechaInsercion;
/*     */   private String usuarioModificacion;
/*     */   private String fechaModificacion;
/*     */   
/*  34 */   public void setCodigoCertificado(String p) { this.codigoCertificado = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   public String getCodigoCertificado() { return (this.codigoCertificado == null) ? "" : this.codigoCertificado; }
/*     */ 
/*     */ 
/*     */   
/*  46 */   public void setAnio(String anio) { this.anio = anio; }
/*     */ 
/*     */ 
/*     */   
/*  50 */   public String getAnio() { return (this.anio == null) ? "" : this.anio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   public void setValorCertificado(double p) { this.valorCertificado = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   public double getValorCertificado() { return this.valorCertificado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   public void setFechaExpedicion(String p) { this.fechaExpedicion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   public String getFechaExpedicion() { return (this.fechaExpedicion == null) ? "" : this.fechaExpedicion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public void setFechaVencimiento(String p) { this.fechaVencimiento = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   public String getFechaVencimiento() { return (this.fechaVencimiento == null) ? "" : this.fechaVencimiento; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 146 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 162 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\ContCdpDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */