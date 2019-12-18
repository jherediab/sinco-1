/*    */ package sinco.business;
/*    */ 
/*    */ import sinco.business.ContEstPrevActividadesDTO;
/*    */ 
/*    */ public class ContEstPrevActividadesDTO
/*    */ {
/*    */   int consecutivoActividad;
/*    */   String actividad;
/*    */   int porcentaje;
/*    */   String usuarioInsercion;
/*    */   String fechaInsercion;
/*    */   String usuarioModificacion;
/*    */   String fechaModificacion;
/*    */   
/* 15 */   public int getConsecutivoActividad() { return this.consecutivoActividad; }
/*    */ 
/*    */   
/* 18 */   public void setConsecutivoActividad(int consecutivoActividad) { this.consecutivoActividad = consecutivoActividad; }
/*    */ 
/*    */   
/* 21 */   public String getActividad() { return (this.actividad == null) ? "" : this.actividad; }
/*    */ 
/*    */   
/* 24 */   public void setActividad(String actividad) { this.actividad = actividad; }
/*    */ 
/*    */   
/* 27 */   public int getPorcentaje() { return this.porcentaje; }
/*    */ 
/*    */   
/* 30 */   public void setPorcentaje(int porcentaje) { this.porcentaje = porcentaje; }
/*    */ 
/*    */   
/* 33 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*    */ 
/*    */   
/* 36 */   public void setUsuarioInsercion(String usuarioInsercion) { this.usuarioInsercion = usuarioInsercion; }
/*    */ 
/*    */   
/* 39 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*    */ 
/*    */   
/* 42 */   public void setFechaInsercion(String fechaInsercion) { this.fechaInsercion = fechaInsercion; }
/*    */ 
/*    */   
/* 45 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*    */ 
/*    */   
/* 48 */   public void setUsuarioModificacion(String usuarioModificacion) { this.usuarioModificacion = usuarioModificacion; }
/*    */ 
/*    */   
/* 51 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*    */ 
/*    */   
/* 54 */   public void setFechaModificacion(String fechaModificacion) { this.fechaModificacion = fechaModificacion; }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\ContEstPrevActividadesDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */