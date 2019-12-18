/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.PrcDetalleProcedimientoDTO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PrcDetalleProcedimientoDTO
/*     */ {
/*     */   private int idDetalleProcedimiento;
/*     */   private String tipoDimension;
/*     */   private int idProcedimiento;
/*     */   private String descripcionDetalle;
/*     */   private int codigoEmpleado;
/*     */   private String registroDetalle;
/*     */   private String estado;
/*     */   private String usuarioInsercion;
/*     */   private String fechaInsercion;
/*     */   private String usuarioModificacion;
/*     */   private String fechaModificacion;
/*     */   private String nombreTipoDimension;
/*     */   private String nombreIdProcedimiento;
/*     */   private String nombreCodigoEmpleado;
/*     */   private String nombreEstado;
/*     */   
/*  46 */   public void setIdDetalleProcedimiento(int p) { this.idDetalleProcedimiento = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public int getIdDetalleProcedimiento() { return this.idDetalleProcedimiento; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   public void setTipoDimension(String p) { this.tipoDimension = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   public String getTipoDimension() { return (this.tipoDimension == null) ? "" : this.tipoDimension; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   public void setIdProcedimiento(int p) { this.idProcedimiento = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public int getIdProcedimiento() { return this.idProcedimiento; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   public void setDescripcionDetalle(String p) { this.descripcionDetalle = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public String getDescripcionDetalle() { return (this.descripcionDetalle == null) ? "" : this.descripcionDetalle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   public void setCodigoEmpleado(int p) { this.codigoEmpleado = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   public int getCodigoEmpleado() { return this.codigoEmpleado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 126 */   public void setRegistroDetalle(String p) { this.registroDetalle = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 134 */   public String getRegistroDetalle() { return (this.registroDetalle == null) ? "" : this.registroDetalle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 142 */   public void setEstado(String p) { this.estado = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 150 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 158 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 174 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 182 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 190 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 198 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 206 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 214 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 219 */   public void setNombreTipoDimension(String p) { this.nombreTipoDimension = p; }
/*     */ 
/*     */   
/* 222 */   public String getNombreTipoDimension() { return (this.nombreTipoDimension == null) ? "" : this.nombreTipoDimension; }
/*     */ 
/*     */   
/* 225 */   public void setNombreIdProcedimiento(String p) { this.nombreIdProcedimiento = p; }
/*     */ 
/*     */   
/* 228 */   public String getNombreIdProcedimiento() { return (this.nombreIdProcedimiento == null) ? "" : this.nombreIdProcedimiento; }
/*     */ 
/*     */   
/* 231 */   public void setNombreCodigoEmpleado(String p) { this.nombreCodigoEmpleado = p; }
/*     */ 
/*     */   
/* 234 */   public String getNombreCodigoEmpleado() { return (this.nombreCodigoEmpleado == null) ? "" : this.nombreCodigoEmpleado; }
/*     */ 
/*     */   
/* 237 */   public void setNombreEstado(String p) { this.nombreEstado = p; }
/*     */ 
/*     */   
/* 240 */   public String getNombreEstado() { return (this.nombreEstado == null) ? "" : this.nombreEstado; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\PrcDetalleProcedimientoDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */