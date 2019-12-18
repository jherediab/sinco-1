/*    */ package sinco.business;
/*    */ 
/*    */ import sinco.business.CalPeriodosDTO;
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
/*    */ public class CalPeriodosDTO
/*    */ {
/*    */   private int ciclo;
/*    */   private int periodo;
/*    */   private String descripcion;
/*    */   private String estado;
/*    */   private String fechaInsercion;
/*    */   private String usuarioInsercion;
/*    */   private String fechaModificacion;
/*    */   private String usuarioModificacion;
/*    */   
/* 28 */   public void setCiclo(int p) { this.ciclo = p; }
/*    */ 
/*    */   
/* 31 */   public int getCiclo() { return this.ciclo; }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public void setPeriodo(int p) { this.periodo = p; }
/*    */ 
/*    */   
/* 38 */   public int getPeriodo() { return this.periodo; }
/*    */ 
/*    */ 
/*    */   
/* 42 */   public void setDescripcion(String p) { this.descripcion = p; }
/*    */ 
/*    */   
/* 45 */   public String getDescripcion() { return (this.descripcion == null) ? "" : this.descripcion; }
/*    */ 
/*    */ 
/*    */   
/* 49 */   public void setEstado(String p) { this.estado = p; }
/*    */ 
/*    */   
/* 52 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*    */ 
/*    */ 
/*    */   
/* 56 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*    */ 
/*    */   
/* 59 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*    */ 
/*    */ 
/*    */   
/* 63 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*    */ 
/*    */   
/* 66 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*    */ 
/*    */ 
/*    */   
/* 70 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*    */ 
/*    */   
/* 73 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*    */ 
/*    */ 
/*    */   
/* 77 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*    */ 
/*    */   
/* 80 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\CalPeriodosDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */