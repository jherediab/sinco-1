/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.PersonasAreaDTO;
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
/*     */ public class PersonasAreaDTO
/*     */ {
/*     */   private int codigoArea;
/*     */   private int codigoEmpleado;
/*     */   private int clase;
/*     */   private String responsableArea;
/*     */   private String areaPrincipal;
/*     */   private String nombreArea;
/*     */   private String nombrePersona;
/*     */   private String nombreClase;
/*     */   private String usuarioInsercion;
/*     */   private String fechaInsercion;
/*     */   private String usuarioModificacion;
/*     */   private String fechaModificacion;
/*     */   
/*  36 */   public void setCodigoArea(int p) { this.codigoArea = p; }
/*     */ 
/*     */   
/*  39 */   public int getCodigoArea() { return this.codigoArea; }
/*     */ 
/*     */ 
/*     */   
/*  43 */   public void setCodigoEmpleado(int p) { this.codigoEmpleado = p; }
/*     */ 
/*     */   
/*  46 */   public int getCodigoEmpleado() { return this.codigoEmpleado; }
/*     */ 
/*     */ 
/*     */   
/*  50 */   public void setClase(int p) { this.clase = p; }
/*     */ 
/*     */   
/*  53 */   public int getClase() { return this.clase; }
/*     */ 
/*     */ 
/*     */   
/*  57 */   public void setResponsableArea(String p) { this.responsableArea = p; }
/*     */ 
/*     */   
/*  60 */   public String getResponsableArea() { return (this.responsableArea == null) ? "" : this.responsableArea; }
/*     */ 
/*     */ 
/*     */   
/*  64 */   public void setNombreArea(String p) { this.nombreArea = p; }
/*     */ 
/*     */   
/*  67 */   public String getNombreArea() { return (this.nombreArea == null) ? "" : this.nombreArea; }
/*     */ 
/*     */ 
/*     */   
/*  71 */   public void setNombrePersona(String p) { this.nombrePersona = p; }
/*     */ 
/*     */   
/*  74 */   public String getNombrePersona() { return (this.nombrePersona == null) ? "" : this.nombrePersona; }
/*     */ 
/*     */ 
/*     */   
/*  78 */   public void setNombreClase(String p) { this.nombreClase = p; }
/*     */ 
/*     */   
/*  81 */   public String getNombreClase() { return (this.nombreClase == null) ? "" : this.nombreClase; }
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
/* 112 */   public String getAreaPrincipal() { return (this.areaPrincipal == null) ? "" : this.areaPrincipal; }
/*     */ 
/*     */   
/* 115 */   public void setAreaPrincipal(String areaPrincipal) { this.areaPrincipal = areaPrincipal; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\PersonasAreaDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */