/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.CalObjetivosDTO;
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
/*     */ public class CalObjetivosDTO
/*     */ {
/*     */   private int codigoCiclo;
/*     */   private int codigoPlan;
/*     */   private int codigoObjetivo;
/*     */   private String proceso;
/*     */   private String subProceso;
/*     */   private String descripcion;
/*     */   private String justificacion;
/*     */   private String tipoObjetivo;
/*     */   private int perspectiva;
/*     */   private String estado;
/*     */   private String agregaValor;
/*     */   private String fechaInsercion;
/*     */   private String usuarioInsercion;
/*     */   private String fechaModificacion;
/*     */   private String usuarioModificacion;
/*     */   private String nombreProceso;
/*     */   private String nombreSubProceso;
/*     */   private String nombreTipoObjetivo;
/*     */   private String estadoProceso;
/*     */   private String estadoSubProceso;
/*     */   private String nombreArea;
/*     */   private int codigoArea;
/*     */   
/*  53 */   public void setCodigoCiclo(int p) { this.codigoCiclo = p; }
/*     */ 
/*     */   
/*  56 */   public int getCodigoCiclo() { return this.codigoCiclo; }
/*     */ 
/*     */ 
/*     */   
/*  60 */   public void setCodigoPlan(int p) { this.codigoPlan = p; }
/*     */ 
/*     */   
/*  63 */   public int getCodigoPlan() { return this.codigoPlan; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public void setCodigoObjetivo(int p) { this.codigoObjetivo = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   public int getCodigoObjetivo() { return this.codigoObjetivo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   public void setProceso(String p) { this.proceso = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   public String getProceso() { return (this.proceso == null) ? "" : this.proceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public void setSubProceso(String p) { this.subProceso = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   public String getSubProceso() { return (this.subProceso == null) ? "" : this.subProceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   public void setDescripcion(String p) { this.descripcion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   public String getDescripcion() { return (this.descripcion == null) ? "" : this.descripcion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   public void setJustificacion(String p) { this.justificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   public String getJustificacion() { return (this.justificacion == null) ? "" : this.justificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   public void setTipoObjetivo(String p) { this.tipoObjetivo = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 160 */   public String getTipoObjetivo() { return (this.tipoObjetivo == null) ? "" : this.tipoObjetivo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 168 */   public void setPerspectiva(int p) { this.perspectiva = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 176 */   public int getPerspectiva() { return this.perspectiva; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 184 */   public void setEstado(String p) { this.estado = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 192 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 200 */   public void setAgregaValor(String p) { this.agregaValor = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 208 */   public String getAgregaValor() { return (this.agregaValor == null) ? "" : this.agregaValor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 216 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 224 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 232 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 240 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 248 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 256 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 264 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 272 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 277 */   public void setNombreProceso(String p) { this.nombreProceso = p; }
/*     */ 
/*     */   
/* 280 */   public String getNombreProceso() { return (this.nombreProceso == null) ? "" : this.nombreProceso; }
/*     */ 
/*     */ 
/*     */   
/* 284 */   public void setNombreSubProceso(String p) { this.nombreSubProceso = p; }
/*     */ 
/*     */   
/* 287 */   public String getNombreSubProceso() { return (this.nombreSubProceso == null) ? "" : this.nombreSubProceso; }
/*     */ 
/*     */   
/* 290 */   public void setNombreTipoObjetivo(String p) { this.nombreTipoObjetivo = p; }
/*     */ 
/*     */   
/* 293 */   public String getNombreTipoObjetivo() { return (this.nombreTipoObjetivo == null) ? "" : this.nombreTipoObjetivo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 299 */   public String getEstadoProceso() { return (this.estadoProceso == null) ? "" : (this.estadoProceso.equals("I") ? " (INACTIVO)" : ""); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 305 */   public void setEstadoProceso(String estadoProceso) { this.estadoProceso = estadoProceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 311 */   public String getEstadoSubProceso() { return (this.estadoSubProceso == null) ? "" : (this.estadoSubProceso.equals("I") ? " (INACTIVO)" : ""); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 317 */   public void setEstadoSubProceso(String estadoSubProceso) { this.estadoSubProceso = estadoSubProceso; }
/*     */ 
/*     */ 
/*     */   
/* 321 */   public String getNombreEstado() { return (this.estado == null) ? "" : (this.estado.equals("I") ? " (INACTIVO)" : ""); }
/*     */ 
/*     */ 
/*     */   
/* 325 */   public String getNombreEstado2() { return (this.estado == null) ? "" : (this.estado.equals("I") ? " (INACTIVO)" : "ACTIVO"); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 332 */   public String getNombreArea() { return (this.nombreArea == null) ? "" : this.nombreArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 338 */   public void setNombreArea(String nombreArea) { this.nombreArea = nombreArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 344 */   public int getCodigoArea() { return this.codigoArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 350 */   public void setCodigoArea(int codigoArea) { this.codigoArea = codigoArea; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\CalObjetivosDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */