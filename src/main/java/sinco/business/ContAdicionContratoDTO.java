/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.ContAdicionContratoDTO;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContAdicionContratoDTO
/*     */ {
/*     */   private int consecutivoContrato;
/*     */   private int consecutivoAdicion;
/*     */   private String tipoAdicion;
/*     */   private double valorAdicionado;
/*     */   private int plazoAdicionado;
/*     */   private int unidadPlazo;
/*     */   private String clausulas;
/*     */   private String justificacion;
/*     */   private String numCertificadoAdd;
/*     */   private String fechaCertificacionAdd;
/*     */   private String usuarioInsercion;
/*     */   private String fechaInsercion;
/*     */   private String usuarioModificacion;
/*     */   private String fechaModificacion;
/*     */   private String numeroContrato;
/*     */   private String valorContrato;
/*     */   private String descripcionTipoAdicion;
/*     */   private String descripcionUnidadPlazo;
/*     */   private String servicioAdicionado;
/*     */   
/*  55 */   public int getConsecutivoContrato() { return this.consecutivoContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   public void setConsecutivoContrato(int consecutivoContrato) { this.consecutivoContrato = consecutivoContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   public int getConsecutivoAdicion() { return this.consecutivoAdicion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   public void setConsecutivoAdicion(int consecutivoAdicion) { this.consecutivoAdicion = consecutivoAdicion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public String getTipoAdicion() { return this.tipoAdicion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public void setTipoAdicion(String tipoAdicion) { this.tipoAdicion = tipoAdicion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public double getValorAdicionado() { return this.valorAdicionado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public void setValorAdicionado(double valorAdicionado) { this.valorAdicionado = valorAdicionado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   public int getPlazoAdicionado() { return this.plazoAdicionado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   public void setPlazoAdicionado(int plazoAdicionado) { this.plazoAdicionado = plazoAdicionado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 125 */   public int getUnidadPlazo() { return this.unidadPlazo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   public void setUnidadPlazo(int unidadPlazo) { this.unidadPlazo = unidadPlazo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 139 */   public String getClausulas() { return (this.clausulas == null) ? "" : this.clausulas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 146 */   public void setClausulas(String clausulas) { this.clausulas = clausulas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 153 */   public String getJustificacion() { return (this.justificacion == null) ? "" : this.justificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 160 */   public void setJustificacion(String justificacion) { this.justificacion = justificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 167 */   public String getNumCertificadoAdd() { return (this.numCertificadoAdd == null) ? "" : this.numCertificadoAdd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 174 */   public void setNumCertificadoAdd(String numCertificadoAdd) { this.numCertificadoAdd = numCertificadoAdd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 181 */   public String getFechaCertificacionAdd() { return (this.fechaCertificacionAdd == null) ? "" : this.fechaCertificacionAdd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 188 */   public void setFechaCertificacionAdd(String fechaCertificacionAdd) { this.fechaCertificacionAdd = fechaCertificacionAdd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 195 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 202 */   public void setUsuarioInsercion(String usuarioInsercion) { this.usuarioInsercion = usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 209 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 216 */   public void setFechaInsercion(String fechaInsercion) { this.fechaInsercion = fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 223 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 230 */   public void setUsuarioModificacion(String usuarioModificacion) { this.usuarioModificacion = usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 237 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 244 */   public void setFechaModificacion(String fechaModificacion) { this.fechaModificacion = fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 251 */   public String getNumeroContrato() { return (this.numeroContrato == null) ? "" : this.numeroContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 258 */   public void setNumeroContrato(String numeroContrato) { this.numeroContrato = numeroContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 265 */   public String getValorContrato() { return this.valorContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 272 */   public void setValorContrato(String valorContrato) { this.valorContrato = valorContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 279 */   public String getDescripcionTipoAdicion() { return (this.descripcionTipoAdicion == null) ? "" : this.descripcionTipoAdicion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 286 */   public void setDescripcionTipoAdicion(String descripcionTipoAdicion) { this.descripcionTipoAdicion = descripcionTipoAdicion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 293 */   public String getDescripcionUnidadPlazo() { return (this.descripcionUnidadPlazo == null) ? "" : this.descripcionUnidadPlazo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 300 */   public void setDescripcionUnidadPlazo(String descripcionUnidadPlazo) { this.descripcionUnidadPlazo = descripcionUnidadPlazo; }
/*     */ 
/*     */ 
/*     */   
/* 304 */   public String getServicioAdicionado() { return (this.servicioAdicionado == null) ? "" : this.servicioAdicionado; }
/*     */ 
/*     */ 
/*     */   
/* 308 */   public void setServicioAdicionado(String servicioAdicionado) { this.servicioAdicionado = servicioAdicionado; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\ContAdicionContratoDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */