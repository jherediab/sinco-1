/*    */ package sinco.business;
/*    */ 
/*    */ import sinco.business.DescCompetenciasDTO;
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
/*    */ public class DescCompetenciasDTO
/*    */ {
/*    */   private double valor;
/*    */   private String descripcion;
/*    */   private String estado;
/*    */   private String usuarioInsercion;
/*    */   private String fechaInsercion;
/*    */   private String usuarioModificacion;
/*    */   private String fechaModificacion;
/*    */   
/* 26 */   public void setValor(double p) { this.valor = p; }
/*    */ 
/*    */   
/* 29 */   public double getValor() { return this.valor; }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public void setDescripcion(String p) { this.descripcion = p; }
/*    */ 
/*    */   
/* 36 */   public String getDescripcion() { return (this.descripcion == null) ? "" : this.descripcion; }
/*    */ 
/*    */ 
/*    */   
/* 40 */   public void setEstado(String p) { this.estado = p; }
/*    */ 
/*    */   
/* 43 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*    */ 
/*    */ 
/*    */   
/* 47 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*    */ 
/*    */   
/* 50 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*    */ 
/*    */ 
/*    */   
/* 54 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*    */ 
/*    */   
/* 57 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*    */ 
/*    */ 
/*    */   
/* 61 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*    */ 
/*    */   
/* 64 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*    */ 
/*    */ 
/*    */   
/* 68 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*    */ 
/*    */   
/* 71 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\DescCompetenciasDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */