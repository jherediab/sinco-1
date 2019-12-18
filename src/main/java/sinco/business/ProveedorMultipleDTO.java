/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.ProveedorMultipleDTO;
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
/*     */ public class ProveedorMultipleDTO
/*     */ {
/*     */   private int codigoArea;
/*     */   private int codigoServicio;
/*     */   private int personaCargo;
/*     */   private String usuarioInsercion;
/*     */   private String fechaInsercion;
/*     */   private String usuarioModificacion;
/*     */   private String fechaModificacion;
/*     */   private String nombreResponsable;
/*     */   private String existe;
/*     */   
/*  26 */   public void setCodigoArea(int p) { this.codigoArea = p; }
/*     */ 
/*     */   
/*  29 */   public int getCodigoArea() { return this.codigoArea; }
/*     */ 
/*     */ 
/*     */   
/*  33 */   public void setCodigoServicio(int p) { this.codigoServicio = p; }
/*     */ 
/*     */   
/*  36 */   public int getCodigoServicio() { return this.codigoServicio; }
/*     */ 
/*     */ 
/*     */   
/*  40 */   public void setPersonaCargo(int p) { this.personaCargo = p; }
/*     */ 
/*     */   
/*  43 */   public int getPersonaCargo() { return this.personaCargo; }
/*     */ 
/*     */ 
/*     */   
/*  47 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */   
/*  50 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */   
/*  54 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */   
/*  57 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */   
/*  61 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */   
/*  64 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */   
/*  68 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */   
/*  71 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   public String getNombreResponsable() { return (this.nombreResponsable == null) ? "" : this.nombreResponsable; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public void setNombreResponsable(String nombreResponsable) { this.nombreResponsable = nombreResponsable; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   public String getExiste() { return (this.existe == null) ? "" : this.existe; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   public void setExiste(String existe) { this.existe = existe; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\ProveedorMultipleDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */