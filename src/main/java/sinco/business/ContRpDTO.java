/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.ContRpDTO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContRpDTO
/*     */ {
/*     */   private String numeroRegistro;
/*     */   private String fechaExpedicion;
/*     */   private double valor;
/*     */   private String dependencia;
/*     */   private String usuarioInsercion;
/*     */   private String fechaInsercion;
/*     */   private String usuarioModificacion;
/*     */   private String fechaModificacion;
/*     */   private String nombreDependencia;
/*     */   private int consecutivoContrato;
/*     */   private int numeroAdicion;
/*     */   private String imputacion;
/*     */   private String anio;
/*     */   
/*  43 */   public void setNumeroRegistro(String p) { this.numeroRegistro = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   public String getNumeroRegistro() { return (this.numeroRegistro == null) ? "" : this.numeroRegistro; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   public void setFechaExpedicion(String p) { this.fechaExpedicion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   public String getFechaExpedicion() { return (this.fechaExpedicion == null) ? "" : this.fechaExpedicion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public void setValor(double p) { this.valor = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public double getValor() { return this.valor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public void setDependencia(String p) { this.dependencia = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   public String getDependencia() { return (this.dependencia == null) ? "" : this.dependencia; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 139 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 147 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 155 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 163 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   public void setNombreDependencia(String nombreDependencia) { this.nombreDependencia = nombreDependencia; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 177 */   public String getNombreDependencia() { return (this.nombreDependencia == null) ? "" : this.nombreDependencia; }
/*     */ 
/*     */ 
/*     */   
/* 181 */   public int getConsecutivoContrato() { return this.consecutivoContrato; }
/*     */ 
/*     */ 
/*     */   
/* 185 */   public void setConsecutivoContrato(int consecutivoContrato) { this.consecutivoContrato = consecutivoContrato; }
/*     */ 
/*     */ 
/*     */   
/* 189 */   public int getNumeroAdicion() { return this.numeroAdicion; }
/*     */ 
/*     */ 
/*     */   
/* 193 */   public void setNumeroAdicion(int numeroAdicion) { this.numeroAdicion = numeroAdicion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 200 */   public void setImputacion(String imputacion) { this.imputacion = imputacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 207 */   public String getImputacion() { return (this.imputacion == null) ? "" : this.imputacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 214 */   public String getAnio() { return (this.anio == null) ? "" : this.anio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 221 */   public void setAnio(String anio) { this.anio = anio; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\ContRpDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */