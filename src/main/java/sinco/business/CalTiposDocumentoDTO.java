/*    */ package sinco.business;
/*    */ 
/*    */ import sinco.business.CalTiposDocumentoDTO;
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
/*    */ public class CalTiposDocumentoDTO
/*    */ {
/*    */   private String codigo;
/*    */   private String descripcion;
/*    */   private String mostrarEnListaMaestra;
/*    */   private String mostrarEnMapa;
/*    */   private String mostrarEnPlanes;
/*    */   private String estado;
/*    */   private String fechaInsercion;
/*    */   private String usuarioInsercion;
/*    */   private String fechaModificacion;
/*    */   private String usuarioModificacion;
/*    */   
/* 32 */   public void setCodigo(String p) { this.codigo = p; }
/*    */ 
/*    */   
/* 35 */   public String getCodigo() { return (this.codigo == null) ? "" : this.codigo; }
/*    */ 
/*    */ 
/*    */   
/* 39 */   public void setDescripcion(String p) { this.descripcion = p; }
/*    */ 
/*    */   
/* 42 */   public String getDescripcion() { return (this.descripcion == null) ? "" : this.descripcion; }
/*    */ 
/*    */ 
/*    */   
/* 46 */   public void setMostrarEnListaMaestra(String p) { this.mostrarEnListaMaestra = p; }
/*    */ 
/*    */   
/* 49 */   public String getMostrarEnListaMaestra() { return (this.mostrarEnListaMaestra == null) ? "" : this.mostrarEnListaMaestra; }
/*    */ 
/*    */ 
/*    */   
/* 53 */   public void setMostrarEnMapa(String p) { this.mostrarEnMapa = p; }
/*    */ 
/*    */   
/* 56 */   public String getMostrarEnMapa() { return (this.mostrarEnMapa == null) ? "" : this.mostrarEnMapa; }
/*    */ 
/*    */ 
/*    */   
/* 60 */   public void setMostrarEnPlanes(String p) { this.mostrarEnPlanes = p; }
/*    */ 
/*    */   
/* 63 */   public String getMostrarEnPlanes() { return (this.mostrarEnPlanes == null) ? "" : this.mostrarEnPlanes; }
/*    */ 
/*    */ 
/*    */   
/* 67 */   public void setEstado(String p) { this.estado = p; }
/*    */ 
/*    */   
/* 70 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*    */ 
/*    */ 
/*    */   
/* 74 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*    */ 
/*    */   
/* 77 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*    */ 
/*    */ 
/*    */   
/* 81 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*    */ 
/*    */   
/* 84 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*    */ 
/*    */ 
/*    */   
/* 88 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*    */ 
/*    */   
/* 91 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*    */ 
/*    */ 
/*    */   
/* 95 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*    */ 
/*    */   
/* 98 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\CalTiposDocumentoDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */