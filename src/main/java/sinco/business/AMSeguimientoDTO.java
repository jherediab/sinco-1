/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.AMSeguimientoDTO;
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
/*     */ public class AMSeguimientoDTO
/*     */ {
/*     */   private int numero;
/*     */   private int causa;
/*     */   private int consecutivo;
/*     */   private String observacion;
/*     */   private String fecha;
/*     */   private int personaatendio;
/*     */   private String automatico;
/*     */   private String usuarioInsercion;
/*     */   private String fechaInsercion;
/*     */   private String usuarioModificacion;
/*     */   private String fechaModificacion;
/*     */   private String nombre;
/*     */   
/*  36 */   public void setNumero(int p) { this.numero = p; }
/*     */ 
/*     */   
/*  39 */   public int getNumero() { return this.numero; }
/*     */ 
/*     */ 
/*     */   
/*  43 */   public void setCausa(int p) { this.causa = p; }
/*     */ 
/*     */   
/*  46 */   public int getCausa() { return this.causa; }
/*     */ 
/*     */ 
/*     */   
/*  50 */   public void setConsecutivo(int p) { this.consecutivo = p; }
/*     */ 
/*     */   
/*  53 */   public int getConsecutivo() { return this.consecutivo; }
/*     */ 
/*     */ 
/*     */   
/*  57 */   public void setObservacion(String p) { this.observacion = p; }
/*     */ 
/*     */   
/*  60 */   public String getObservacion() { return (this.observacion == null) ? "" : this.observacion; }
/*     */ 
/*     */ 
/*     */   
/*  64 */   public void setFecha(String p) { this.fecha = p; }
/*     */ 
/*     */   
/*  67 */   public String getFecha() { return (this.fecha == null) ? "" : this.fecha; }
/*     */ 
/*     */ 
/*     */   
/*  71 */   public void setPersonaatendio(int p) { this.personaatendio = p; }
/*     */ 
/*     */   
/*  74 */   public int getPersonaatendio() { return this.personaatendio; }
/*     */ 
/*     */ 
/*     */   
/*  78 */   public void setAutomatico(String p) { this.automatico = p; }
/*     */ 
/*     */   
/*  81 */   public String getAutomatico() { return (this.automatico == null) ? "" : this.automatico; }
/*     */ 
/*     */ 
/*     */   
/*  85 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */   
/*  88 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */   
/*  92 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */   
/*  95 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */   
/*  99 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */   
/* 102 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */   
/* 106 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */   
/* 109 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   public String getNombre() { return (this.nombre == null) ? "" : this.nombre; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   public void setNombre(String nombre) { this.nombre = nombre; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\AMSeguimientoDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */