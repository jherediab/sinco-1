/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.ArchivosSolicitudDTO;
/*     */ 
/*     */ public class ArchivosSolicitudDTO {
/*     */   private int numeroSolicitud;
/*     */   private int consecutivo;
/*     */   private String archivo;
/*     */   private int causa;
/*     */   private String fechaInsercion;
/*     */   private String usuarioInsercion;
/*     */   private String fechaModificacion;
/*     */   private String usuarioModificacion;
/*     */   private String estado;
/*     */   
/*  16 */   public void setNumeroSolicitud(int n) { this.numeroSolicitud = n; }
/*     */ 
/*     */ 
/*     */   
/*  20 */   public void setConsecutivo(int n) { this.consecutivo = n; }
/*     */ 
/*     */ 
/*     */   
/*  24 */   public void setArchivo(String s) { this.archivo = s; }
/*     */ 
/*     */ 
/*     */   
/*  28 */   public void setFechaInsercion(String f) { this.fechaInsercion = f; }
/*     */ 
/*     */ 
/*     */   
/*  32 */   public String getFechaInsercion() { return this.fechaInsercion; }
/*     */ 
/*     */   
/*  35 */   public String getArchivo() { return this.archivo; }
/*     */ 
/*     */ 
/*     */   
/*  39 */   public int getConsecutivo() { return this.consecutivo; }
/*     */ 
/*     */   
/*  42 */   public int getNumeroSolicitud() { return this.numeroSolicitud; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   public void setUsuarioInsercion(String usuarioInsercion) { this.usuarioInsercion = usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   public void setFechaModificacion(String fechaModificacion) { this.fechaModificacion = fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public void setUsuarioModificacion(String usuarioModificacion) { this.usuarioModificacion = usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   public void setEstado(String estado) { this.estado = estado; }
/*     */ 
/*     */ 
/*     */   
/* 102 */   public int getCausa() { return this.causa; }
/*     */ 
/*     */ 
/*     */   
/* 106 */   public void setCausa(int causa) { this.causa = causa; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\ArchivosSolicitudDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */