/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.RedPrestadorDocumentosDTO;
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
/*     */ public class RedPrestadorDocumentosDTO
/*     */ {
/*     */   private String numeroIdentificacion;
/*     */   private String tipoDocumento;
/*     */   private int codigoSucursal;
/*     */   private String tieneImagen;
/*     */   private String fechaEmision;
/*     */   private String fechaCaducidad;
/*     */   
/*  28 */   public void setNumeroIdentificacion(String p) { this.numeroIdentificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   public String getNumeroIdentificacion() { return (this.numeroIdentificacion == null) ? "" : this.numeroIdentificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   public void setTipoDocumento(String p) { this.tipoDocumento = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   public String getTipoDocumento() { return (this.tipoDocumento == null) ? "" : this.tipoDocumento; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   public void setCodigoSucursal(int p) { this.codigoSucursal = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   public int getCodigoSucursal() { return this.codigoSucursal; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   public void setTieneImagen(String p) { this.tieneImagen = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public String getTieneImagen() { return (this.tieneImagen == null) ? "" : this.tieneImagen; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   public void setFechaEmision(String p) { this.fechaEmision = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public String getFechaEmision() { return (this.fechaEmision == null) ? "" : this.fechaEmision; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public void setFechaCaducidad(String p) { this.fechaCaducidad = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public String getFechaCaducidad() { return (this.fechaCaducidad == null) ? "" : this.fechaCaducidad; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\RedPrestadorDocumentosDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */