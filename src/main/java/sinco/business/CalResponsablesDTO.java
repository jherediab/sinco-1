/*    */ package sinco.business;
/*    */ 
/*    */ import sinco.business.CalResponsablesDTO;
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
/*    */ public class CalResponsablesDTO
/*    */ {
/*    */   private int codigo;
/*    */   private String codigoCargo;
/*    */   private String descripcion;
/*    */   private String estado;
/*    */   private String fechaInsercion;
/*    */   private String usuarioInsercion;
/*    */   private String fechaModificacion;
/*    */   private String usuarioModificacion;
/*    */   
/* 28 */   public void setCodigo(int p) { this.codigo = p; }
/*    */ 
/*    */   
/* 31 */   public int getCodigo() { return this.codigo; }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public void setDescripcion(String p) { this.descripcion = p; }
/*    */ 
/*    */   
/* 38 */   public String getDescripcion() { return (this.descripcion == null) ? "" : this.descripcion; }
/*    */ 
/*    */ 
/*    */   
/* 42 */   public void setEstado(String p) { this.estado = p; }
/*    */ 
/*    */   
/* 45 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*    */ 
/*    */ 
/*    */   
/* 49 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*    */ 
/*    */   
/* 52 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*    */ 
/*    */ 
/*    */   
/* 56 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*    */ 
/*    */   
/* 59 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*    */ 
/*    */ 
/*    */   
/* 63 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*    */ 
/*    */   
/* 66 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*    */ 
/*    */ 
/*    */   
/* 70 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*    */ 
/*    */   
/* 73 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 79 */   public String getCodigoCargo() { return (this.codigoCargo == null) ? "" : this.codigoCargo; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 85 */   public void setCodigoCargo(String codigoCargo) { this.codigoCargo = codigoCargo; }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\CalResponsablesDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */