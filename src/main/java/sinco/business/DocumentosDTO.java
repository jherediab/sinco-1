/*    */ package sinco.business;
/*    */ 
/*    */ import sinco.business.DocumentosDTO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DocumentosDTO
/*    */ {
/*    */   private String tipoDocumento;
/*    */   private String numeroDocumento;
/*    */   private String documento;
/*    */   private String estado;
/*    */   private String fechaInsercion;
/*    */   private String usuarioInsercion;
/*    */   private String fechaModificacion;
/*    */   private String usuarioModificacion;
/*    */   
/* 24 */   public void setTipoDocumento(String p) { this.tipoDocumento = p; }
/*    */ 
/*    */   
/* 27 */   public String getTipoDocumento() { return (this.tipoDocumento == null) ? "" : this.tipoDocumento; }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public void setNumeroDocumento(String p) { this.numeroDocumento = p; }
/*    */ 
/*    */   
/* 34 */   public String getNumeroDocumento() { return (this.numeroDocumento == null) ? "" : this.numeroDocumento; }
/*    */ 
/*    */ 
/*    */   
/* 38 */   public void setDocumento(String p) { this.documento = p; }
/*    */ 
/*    */   
/* 41 */   public String getDocumento() { return (this.documento == null) ? "" : this.documento; }
/*    */ 
/*    */ 
/*    */   
/* 45 */   public void setEstado(String p) { this.estado = p; }
/*    */ 
/*    */   
/* 48 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*    */ 
/*    */ 
/*    */   
/* 52 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*    */ 
/*    */   
/* 55 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*    */ 
/*    */ 
/*    */   
/* 59 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*    */ 
/*    */   
/* 62 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*    */ 
/*    */ 
/*    */   
/* 66 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*    */ 
/*    */   
/* 69 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*    */ 
/*    */ 
/*    */   
/* 73 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*    */ 
/*    */   
/* 76 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\DocumentosDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */