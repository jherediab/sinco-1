/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.CalPlantillasAreasDTO;
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
/*     */ public class CalPlantillasAreasDTO
/*     */ {
/*     */   private int codigoCiclo;
/*     */   private int codigoArea;
/*     */   private int codigoPlantilla;
/*     */   private String permiteEspecificos;
/*     */   private String estado;
/*     */   private String fechaInsercion;
/*     */   private String usuarioInsercion;
/*     */   private String fechaModificacion;
/*     */   private String usuarioModificacion;
/*     */   private String nombreArea;
/*     */   private String nombreCiclo;
/*     */   private String nombrePlantilla;
/*     */   private int numeroAreas;
/*     */   
/*  32 */   public void setCodigoCiclo(int p) { this.codigoCiclo = p; }
/*     */ 
/*     */   
/*  35 */   public int getCodigoCiclo() { return this.codigoCiclo; }
/*     */ 
/*     */ 
/*     */   
/*  39 */   public void setCodigoArea(int p) { this.codigoArea = p; }
/*     */ 
/*     */   
/*  42 */   public int getCodigoArea() { return this.codigoArea; }
/*     */ 
/*     */ 
/*     */   
/*  46 */   public void setCodigoPlantilla(int p) { this.codigoPlantilla = p; }
/*     */ 
/*     */   
/*  49 */   public int getCodigoPlantilla() { return this.codigoPlantilla; }
/*     */ 
/*     */ 
/*     */   
/*  53 */   public void setPermiteEspecificos(String p) { this.permiteEspecificos = p; }
/*     */ 
/*     */   
/*  56 */   public boolean getPermiteEspecificos() { return this.permiteEspecificos.equals("S"); }
/*     */ 
/*     */ 
/*     */   
/*  60 */   public void setEstado(String p) { this.estado = p; }
/*     */ 
/*     */ 
/*     */   
/*  64 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*     */ 
/*     */ 
/*     */   
/*  68 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */   
/*  71 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */   
/*  75 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */   
/*  78 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */   
/*  82 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */   
/*  85 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */   
/*  89 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */   
/*  92 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */   
/*  96 */   public void setNombreArea(String p) { this.nombreArea = p; }
/*     */ 
/*     */   
/*  99 */   public String getNombreArea() { return (this.nombreArea == null) ? "" : this.nombreArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public void setNombreCiclo(String p) { this.nombreCiclo = p; }
/*     */ 
/*     */   
/* 107 */   public String getNombreCiclo() { return (this.nombreCiclo == null) ? "" : this.nombreCiclo; }
/*     */ 
/*     */ 
/*     */   
/* 111 */   public void setNombrePlantilla(String p) { this.nombrePlantilla = p; }
/*     */ 
/*     */   
/* 114 */   public String getNombrePlantilla() { return (this.nombrePlantilla == null) ? "" : this.nombrePlantilla; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   public void setNumeroAreas(int p) { this.numeroAreas = p; }
/*     */ 
/*     */   
/* 122 */   public int getNumeroAreas() { return this.numeroAreas; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\CalPlantillasAreasDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */