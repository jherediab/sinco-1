/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.CalProcesosDTO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalProcesosDTO
/*     */ {
/*     */   private String proceso;
/*     */   private String descripcion;
/*     */   private String estado;
/*     */   private String tipoProceso;
/*     */   private String subProceso;
/*     */   private int factor;
/*     */   private String usuarioInsercion;
/*     */   private String fechaInsercion;
/*     */   private String usuarioModificacion;
/*     */   private String fechaModificacion;
/*     */   private String nombreEstado;
/*     */   private String nombreTipoProceso;
/*     */   
/*  40 */   public void setProceso(String p) { this.proceso = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   public String getProceso() { return (this.proceso == null) ? "" : this.proceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   public void setDescripcion(String p) { this.descripcion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   public String getDescripcion() { return (this.descripcion == null) ? "" : this.descripcion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public void setEstado(String p) { this.estado = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   public void setTipoProceso(String p) { this.tipoProceso = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   public String getTipoProceso() { return (this.tipoProceso == null) ? "" : this.tipoProceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 160 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 165 */   public void setNombreEstado(String p) { this.nombreEstado = p; }
/*     */ 
/*     */   
/* 168 */   public String getNombreEstado() { return (this.nombreEstado == null) ? "" : this.nombreEstado; }
/*     */ 
/*     */   
/* 171 */   public void setNombreTipoProceso(String p) { this.nombreTipoProceso = p; }
/*     */ 
/*     */   
/* 174 */   public String getNombreTipoProceso() { return (this.nombreTipoProceso == null) ? "" : this.nombreTipoProceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 181 */   public String getSubProceso() { return (this.subProceso == null) ? "" : this.subProceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 188 */   public void setSubProceso(String subproceso) { this.subProceso = subproceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 195 */   public int getFactor() { return this.factor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 202 */   public void setFactor(int factor) { this.factor = factor; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\CalProcesosDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */