/*    */ package sinco.business;
/*    */ 
/*    */ import sinco.business.CalGenericaDTO;
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
/*    */ public class CalGenericaDTO
/*    */ {
/*    */   private int codigo;
/*    */   private String descripcion;
/*    */   private String estado;
/*    */   private String fechaInsercion;
/*    */   private String usuarioInsercion;
/*    */   private String fechaModificacion;
/*    */   private String usuarioModificacion;
/*    */   
/* 27 */   public void setCodigo(int p) { this.codigo = p; }
/*    */ 
/*    */   
/* 30 */   public int getCodigo() { return this.codigo; }
/*    */ 
/*    */ 
/*    */   
/* 34 */   public void setDescripcion(String p) { this.descripcion = p; }
/*    */ 
/*    */   
/* 37 */   public String getDescripcion() { return (this.descripcion == null) ? "" : this.descripcion; }
/*    */ 
/*    */ 
/*    */   
/* 41 */   public void setEstado(String p) { this.estado = p; }
/*    */ 
/*    */   
/* 44 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*    */ 
/*    */ 
/*    */   
/* 48 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*    */ 
/*    */   
/* 51 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*    */ 
/*    */ 
/*    */   
/* 55 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*    */ 
/*    */   
/* 58 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*    */ 
/*    */ 
/*    */   
/* 62 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*    */ 
/*    */   
/* 65 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*    */ 
/*    */ 
/*    */   
/* 69 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*    */ 
/*    */   
/* 72 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\CalGenericaDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */