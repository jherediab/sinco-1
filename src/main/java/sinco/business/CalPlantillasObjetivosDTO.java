/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.CalPlantillasObjetivosDTO;
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
/*     */ public class CalPlantillasObjetivosDTO
/*     */ {
/*     */   private int codigoPlantilla;
/*     */   private int codigoObjetivo;
/*     */   private String nombreObjetivo;
/*     */   private String fechaInsercion;
/*     */   private String usuarioInsercion;
/*     */   private String fechaModificacion;
/*     */   private String usuarioModificacion;
/*     */   private String nombreProceso;
/*     */   private String nombreSubproceso;
/*     */   private String tipoObjetivo;
/*     */   private String nombrePlantilla;
/*     */   private String existe;
/*     */   
/*  32 */   public void setCodigoPlantilla(int p) { this.codigoPlantilla = p; }
/*     */ 
/*     */   
/*  35 */   public int getCodigoPlantilla() { return this.codigoPlantilla; }
/*     */ 
/*     */ 
/*     */   
/*  39 */   public void setCodigoObjetivo(int p) { this.codigoObjetivo = p; }
/*     */ 
/*     */   
/*  42 */   public int getCodigoObjetivo() { return this.codigoObjetivo; }
/*     */ 
/*     */ 
/*     */   
/*  46 */   public void setNombreObjetivo(String p) { this.nombreObjetivo = p; }
/*     */ 
/*     */   
/*  49 */   public String getNombreObjetivo() { return (this.nombreObjetivo == null) ? "" : this.nombreObjetivo; }
/*     */ 
/*     */ 
/*     */   
/*  53 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */   
/*  56 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */   
/*  60 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */   
/*  63 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */   
/*  67 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */   
/*  70 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */   
/*  74 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */   
/*  77 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */   
/*  81 */   public void setNombreProceso(String p) { this.nombreProceso = p; }
/*     */ 
/*     */   
/*  84 */   public String getNombreProceso() { return (this.nombreProceso == null) ? "" : this.nombreProceso; }
/*     */ 
/*     */ 
/*     */   
/*  88 */   public void setNombreSubproceso(String p) { this.nombreSubproceso = p; }
/*     */ 
/*     */   
/*  91 */   public String getNombreSubproceso() { return (this.nombreSubproceso == null) ? "" : this.nombreSubproceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   public void setNombrePlantilla(String p) { this.nombrePlantilla = p; }
/*     */ 
/*     */   
/*  99 */   public String getNombrePlantilla() { return (this.nombrePlantilla == null) ? "" : this.nombrePlantilla; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public void setExiste(String p) { this.existe = p; }
/*     */   
/*     */   public boolean getExiste() {
/* 107 */     if (this.existe == null) return false; 
/* 108 */     if (this.existe.equals("X")) return true; 
/* 109 */     return false;
/*     */   }
/*     */ 
/*     */   
/* 113 */   public void setTipoObjetivo(String p) { this.tipoObjetivo = p; }
/*     */ 
/*     */   
/* 116 */   public String getTipoObjetivo() { return this.tipoObjetivo.equals("E") ? "ESPECIFICO" : "GENERAL"; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\CalPlantillasObjetivosDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */