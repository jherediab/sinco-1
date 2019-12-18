/*    */ package sinco.business;
/*    */ 
/*    */ import sinco.business.FormaGeneralDTO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FormaGeneralDTO
/*    */ {
/*    */   private String codigo;
/*    */   private String nombre;
/*    */   private String estado;
/*    */   private String fechaInsercion;
/*    */   private String usuarioInsercion;
/*    */   private String fechaModificacion;
/*    */   private String usuarioModificacion;
/*    */   
/* 29 */   public void setCodigo(String p) { this.codigo = p; }
/*    */ 
/*    */   
/* 32 */   public String getCodigo() { return this.codigo; }
/*    */ 
/*    */ 
/*    */   
/* 36 */   public void setNombre(String p) { this.nombre = p; }
/*    */ 
/*    */   
/* 39 */   public String getNombre() { return this.nombre; }
/*    */ 
/*    */ 
/*    */   
/* 43 */   public void setEstado(String p) { this.estado = p; }
/*    */ 
/*    */   
/* 46 */   public String getEstado() { return this.estado; }
/*    */ 
/*    */ 
/*    */   
/* 50 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*    */ 
/*    */   
/* 53 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*    */ 
/*    */ 
/*    */   
/* 57 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*    */ 
/*    */   
/* 60 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*    */ 
/*    */ 
/*    */   
/* 64 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*    */ 
/*    */   
/* 67 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*    */ 
/*    */ 
/*    */   
/* 71 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*    */ 
/*    */   
/* 74 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\FormaGeneralDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */