/*    */ package sinco.business;
/*    */ 
/*    */ import sinco.business.ContEstPrevSubactividadesDTO;
/*    */ 
/*    */ public class ContEstPrevSubactividadesDTO
/*    */ {
/*    */   int consecutivoSubactividad;
/*    */   String subActividad;
/*    */   String usuarioInsercion;
/*    */   String fechaInsercion;
/*    */   String usuarioModificacion;
/*    */   String fechaModificacion;
/*    */   
/* 14 */   public int getConsecutivoSubactividad() { return this.consecutivoSubactividad; }
/*    */ 
/*    */   
/* 17 */   public void setConsecutivoSubactividad(int consecutivoSubactividad) { this.consecutivoSubactividad = consecutivoSubactividad; }
/*    */ 
/*    */   
/* 20 */   public String getSubActividad() { return (this.subActividad == null) ? "" : this.subActividad; }
/*    */ 
/*    */   
/* 23 */   public void setSubActividad(String subActividad) { this.subActividad = subActividad; }
/*    */ 
/*    */   
/* 26 */   public String getUsuarioInsercion() { return this.usuarioInsercion; }
/*    */ 
/*    */   
/* 29 */   public void setUsuarioInsercion(String usuarioInsercion) { this.usuarioInsercion = usuarioInsercion; }
/*    */ 
/*    */   
/* 32 */   public String getFechaInsercion() { return this.fechaInsercion; }
/*    */ 
/*    */   
/* 35 */   public void setFechaInsercion(String fechaInsercion) { this.fechaInsercion = fechaInsercion; }
/*    */ 
/*    */   
/* 38 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*    */ 
/*    */   
/* 41 */   public void setUsuarioModificacion(String usuarioModificacion) { this.usuarioModificacion = usuarioModificacion; }
/*    */ 
/*    */   
/* 44 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*    */ 
/*    */   
/* 47 */   public void setFechaModificacion(String fechaModificacion) { this.fechaModificacion = fechaModificacion; }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\ContEstPrevSubactividadesDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */