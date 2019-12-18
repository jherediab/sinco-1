/*    */ package sinco.business;
/*    */ 
/*    */ import sinco.business.AplazamientosSolicitudDTO;
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
/*    */ public class AplazamientosSolicitudDTO
/*    */ {
/*    */   private int numeroSolicitud;
/*    */   private int consecutivo;
/*    */   private String justificacion;
/*    */   private String justificacionNega;
/*    */   private String fecha;
/*    */   private int estado;
/*    */   private String fechaestado;
/*    */   private String usuarioInsercion;
/*    */   private String usuarioModificacion;
/*    */   
/* 30 */   public void setNumeroSolicitud(int p) { this.numeroSolicitud = p; }
/*    */ 
/*    */   
/* 33 */   public int getNumeroSolicitud() { return this.numeroSolicitud; }
/*    */ 
/*    */ 
/*    */   
/* 37 */   public void setConsecutivo(int p) { this.consecutivo = p; }
/*    */ 
/*    */   
/* 40 */   public int getConsecutivo() { return this.consecutivo; }
/*    */ 
/*    */ 
/*    */   
/* 44 */   public void setJustificacion(String p) { this.justificacion = p; }
/*    */ 
/*    */   
/* 47 */   public String getJustificacion() { return (this.justificacion == null) ? "" : this.justificacion; }
/*    */ 
/*    */ 
/*    */   
/* 51 */   public void setFecha(String p) { this.fecha = p; }
/*    */ 
/*    */   
/* 54 */   public String getFecha() { return (this.fecha == null) ? "" : this.fecha; }
/*    */ 
/*    */ 
/*    */   
/* 58 */   public void setEstado(int p) { this.estado = p; }
/*    */ 
/*    */   
/* 61 */   public int getEstado() { return this.estado; }
/*    */ 
/*    */ 
/*    */   
/* 65 */   public void setFechaestado(String p) { this.fechaestado = p; }
/*    */ 
/*    */   
/* 68 */   public String getFechaestado() { return (this.fechaestado == null) ? "" : this.fechaestado; }
/*    */ 
/*    */ 
/*    */   
/* 72 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*    */ 
/*    */   
/* 75 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*    */ 
/*    */ 
/*    */   
/* 79 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*    */ 
/*    */   
/* 82 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 88 */   public String getJustificacionNega() { return (this.justificacionNega == null) ? "" : this.justificacionNega; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 94 */   public void setJustificacionNega(String justificacionNega) { this.justificacionNega = justificacionNega; }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\AplazamientosSolicitudDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */