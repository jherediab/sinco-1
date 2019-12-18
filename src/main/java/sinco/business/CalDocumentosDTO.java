/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.CalDocumentosDTO;

/*     */ public class CalDocumentosDTO
/*     */ {
/*     */   private String proceso;
/*     */   private String subproceso;
/*     */   private String servicio;
/*     */   private String codigo;
/*     */   private String tipoDocumento;
/*     */   private String descripcion;
/*     */   private String version;
/*     */   private String fechaVersion;
/*     */   private String fechaEmision;
/*     */   private String responsable;
/*     */   private String distribuidoA;
/*     */   private String url;
/*     */   private String estado;
/*     */   private String fechaInsercion;
/*     */   private String usuarioInsercion;
/*     */   private String fechaModificacion;
/*     */   private String usuarioModificacion;
/*     */   private String nombreProceso;
/*     */   private String nombreSubProceso;
/*     */   private String existeWord;
/*     */   private String existePdf;
/*     */   private String nombreArchivoWord;
/*     */   private String nombreArchivoPdf;
/*     */   private String fechaRevision;
/*     */   private int codigoArea;
/*     */   private String observaciones;
/*     */   private String responsables;
/*     */   private String asociado_a;
/*     */   private int orden;
/*     */   
/*  66 */   public void setProceso(String p) { this.proceso = p; }
/*     */ 
/*     */   
/*  69 */   public String getProceso() { return (this.proceso == null) ? "" : this.proceso; }
/*     */ 
/*     */ 
/*     */   
/*  73 */   public void setSubproceso(String p) { this.subproceso = p; }
/*     */ 
/*     */   
/*  76 */   public String getSubproceso() { return (this.subproceso == null) ? "" : this.subproceso; }
/*     */ 
/*     */ 
/*     */   
/*  80 */   public void setCodigo(String p) { this.codigo = p; }
/*     */ 
/*     */   
/*  83 */   public String getCodigo() { return (this.codigo == null) ? "" : this.codigo; }
/*     */ 
/*     */ 
/*     */   
/*  87 */   public void setDescripcion(String p) { this.descripcion = p; }
/*     */ 
/*     */   
/*  90 */   public String getDescripcion() { return (this.descripcion == null) ? "" : this.descripcion; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   public void setFechaVersion(String p) { this.fechaVersion = p; }
/*     */ 
/*     */   
/*  98 */   public String getFechaVersion() { return (this.fechaVersion == null) ? "" : this.fechaVersion; }
/*     */ 
/*     */ 
/*     */   
/* 102 */   public void setResponsable(String p) { this.responsable = p; }
/*     */ 
/*     */   
/* 105 */   public String getResponsable() { return (this.responsable == null) ? "" : this.responsable; }
/*     */ 
/*     */ 
/*     */   
/* 109 */   public void setDistribuidoA(String p) { this.distribuidoA = p; }
/*     */ 
/*     */   
/* 112 */   public String getDistribuidoA() { return (this.distribuidoA == null) ? "" : this.distribuidoA; }
/*     */ 
/*     */ 
/*     */   
/* 116 */   public void setUrl(String p) { this.url = p; }
/*     */ 
/*     */   
/* 119 */   public String getUrl() { return (this.url == null) ? "" : this.url; }
/*     */ 
/*     */ 
/*     */   
/* 123 */   public void setEstado(String p) { this.estado = p; }
/*     */ 
/*     */   
/* 126 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*     */ 
/*     */ 
/*     */   
/* 130 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */   
/* 133 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */   
/* 137 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */   
/* 140 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */   
/* 144 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */   
/* 147 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */   
/* 151 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */   
/* 154 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */   
/* 158 */   public void setNombreProceso(String p) { this.nombreProceso = p; }
/*     */ 
/*     */   
/* 161 */   public String getNombreProceso() { return (this.nombreProceso == null) ? "" : this.nombreProceso; }
/*     */ 
/*     */ 
/*     */   
/* 165 */   public void setNombreSubProceso(String p) { this.nombreSubProceso = p; }
/*     */ 
/*     */   
/* 168 */   public String getNombreSubProceso() { return (this.nombreSubProceso == null) ? "" : this.nombreSubProceso; }
/*     */ 
/*     */ 
/*     */   
/* 172 */   public void setCodigoArea(int p) { this.codigoArea = p; }
/*     */ 
/*     */   
/* 175 */   public int getCodigoArea() { return this.codigoArea; }
/*     */ 
/*     */ 
/*     */   
/* 179 */   public void setTipoDocumento(String p) { this.tipoDocumento = p; }
/*     */ 
/*     */   
/* 182 */   public String getTipoDocumento() { return (this.tipoDocumento == null) ? "" : this.tipoDocumento; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 189 */   public String getExistePdf() { return (this.existePdf == null) ? "" : this.existePdf; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 195 */   public void setExistePdf(String existePdf) { this.existePdf = existePdf; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 201 */   public String getExisteWord() { return (this.existeWord == null) ? "" : this.existeWord; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 207 */   public void setExisteWord(String existeWord) { this.existeWord = existeWord; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 213 */   public String getNombreArchivoPdf() { return (this.nombreArchivoPdf == null) ? "" : this.nombreArchivoPdf; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 219 */   public void setNombreArchivoPdf(String nombreArchivoPdf) { this.nombreArchivoPdf = nombreArchivoPdf; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 225 */   public String getNombreArchivoWord() { return (this.nombreArchivoWord == null) ? "" : this.nombreArchivoWord; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 231 */   public void setNombreArchivoWord(String nombreArchivoWord) { this.nombreArchivoWord = nombreArchivoWord; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 237 */   public String getVersion() { return (this.version == null) ? "" : this.version; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 243 */   public void setVersion(String version) { this.version = version; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 249 */   public String getFechaEmision() { return (this.fechaEmision == null) ? "" : this.fechaEmision; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 255 */   public void setFechaEmision(String fechaEmision) { this.fechaEmision = fechaEmision; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 261 */   public String getObservaciones() { return (this.observaciones == null) ? "" : this.observaciones; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 267 */   public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 273 */   public int getOrden() { return this.orden; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 279 */   public void setOrden(int orden) { this.orden = orden; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 285 */   public String getFechaRevision() { return (this.fechaRevision == null) ? "" : this.fechaRevision; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 291 */   public void setFechaRevision(String fechaRevision) { this.fechaRevision = fechaRevision; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 297 */   public String getResponsables() { return (this.responsables == null) ? "" : this.responsables; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 303 */   public void setResponsables(String responsables) { this.responsables = responsables; }
/*     */ 
/*     */   
/* 306 */   public String getServicio() { return (this.servicio == null) ? "" : this.servicio; }
/*     */ 
/*     */   
/* 309 */   public void setServicio(String servicio) { this.servicio = servicio; }
/*     */ 
/*     */   
/* 312 */   public String getAsociado_a() { return (this.asociado_a == null) ? "" : this.asociado_a; }
/*     */ 
/*     */   
/* 315 */   public void setAsociado_a(String asociadoA) { this.asociado_a = asociadoA; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\CalDocumentosDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */