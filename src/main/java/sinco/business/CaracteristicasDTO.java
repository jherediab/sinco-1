/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.CaracteristicasDTO;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CaracteristicasDTO
/*     */ {
/*     */   private int codigo;
/*     */   private String descripcion;
/*     */   private String tipo;
/*     */   private String calificar;
/*     */   private int longitud;
/*     */   private String competencia;
/*     */   private int caracteristicaAnida;
/*     */   private String permiteExtender;
/*     */   private int caracteristicaDepende;
/*     */   private int valorDepende;
/*     */   private String tipoValidacion;
/*     */   private int caracteristicaValida;
/*     */   private String nombreProcedimiento;
/*     */   private int numeroDecimales;
/*     */   private String fechaInsercion;
/*     */   private String usuarioInsercion;
/*     */   private String fechaModificacion;
/*     */   private String usuarioModificacion;
/*     */   private String nombreTipo;
/*     */   private String nombreCompetencia;
/*     */   private String nombreValorDepende;
/*     */   private String nombreTipoValidacion;
/*     */   private int cuantasDependen;
/*     */   private String estado;
/*     */   private boolean obliga;
/*     */   private String rol;
/*     */   private String unidadMedida;
/*     */   private int duracion;
/*     */   
/*  71 */   public void setCodigo(int p) { this.codigo = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   public int getCodigo() { return this.codigo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public void setDescripcion(String p) { this.descripcion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   public String getDescripcion() { return (this.descripcion == null) ? "" : this.descripcion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   public void setTipo(String p) { this.tipo = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   public String getTipo() { return (this.tipo == null) ? "" : this.tipo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   public void setCalificar(String p) { this.calificar = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   public String getCalificar() { return (this.calificar == null) ? "" : this.calificar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   public void setLongitud(int p) { this.longitud = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   public int getLongitud() { return this.longitud; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 151 */   public void setCompetencia(String p) { this.competencia = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 159 */   public String getCompetencia() { return (this.competencia == null) ? "" : this.competencia; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 167 */   public void setCaracteristicaAnida(int p) { this.caracteristicaAnida = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 175 */   public int getCaracteristicaAnida() { return this.caracteristicaAnida; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 183 */   public void setPermiteExtender(String p) { this.permiteExtender = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 191 */   public String getPermiteExtender() { return (this.permiteExtender == null) ? "" : this.permiteExtender; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 199 */   public void setCaracteristicaDepende(int p) { this.caracteristicaDepende = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 207 */   public int getCaracteristicaDepende() { return this.caracteristicaDepende; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 215 */   public void setValorDepende(int p) { this.valorDepende = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 223 */   public int getValorDepende() { return this.valorDepende; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 231 */   public void setTipoValidacion(String p) { this.tipoValidacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 239 */   public String getTipoValidacion() { return (this.tipoValidacion == null) ? "" : this.tipoValidacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 247 */   public void setCaracteristicaValida(int p) { this.caracteristicaValida = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 255 */   public int getCaracteristicaValida() { return this.caracteristicaValida; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 263 */   public void setNombreProcedimiento(String p) { this.nombreProcedimiento = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 271 */   public String getNombreProcedimiento() { return (this.nombreProcedimiento == null) ? "" : this.nombreProcedimiento; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 279 */   public void setNumeroDecimales(int p) { this.numeroDecimales = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 287 */   public int getNumeroDecimales() { return this.numeroDecimales; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 295 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 303 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 311 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 319 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 327 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 335 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 343 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 351 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 356 */   public void setNombreTipo(String p) { this.nombreTipo = p; }
/*     */ 
/*     */   
/* 359 */   public String getNombreTipo() { return (this.nombreTipo == null) ? "" : this.nombreTipo; }
/*     */ 
/*     */   
/* 362 */   public void setNombreCompetencia(String p) { this.nombreCompetencia = p; }
/*     */ 
/*     */   
/* 365 */   public String getNombreCompetencia() { return (this.nombreCompetencia == null) ? "" : this.nombreCompetencia; }
/*     */ 
/*     */   
/* 368 */   public void setNombreValorDepende(String p) { this.nombreValorDepende = p; }
/*     */ 
/*     */   
/* 371 */   public String getNombreValorDepende() { return (this.nombreValorDepende == null) ? "" : this.nombreValorDepende; }
/*     */ 
/*     */   
/* 374 */   public void setNombreTipoValidacion(String p) { this.nombreTipoValidacion = p; }
/*     */ 
/*     */   
/* 377 */   public String getNombreTipoValidacion() { return (this.nombreTipoValidacion == null) ? "" : this.nombreTipoValidacion; }
/*     */ 
/*     */ 
/*     */   
/* 381 */   public boolean getObliga() { return this.obliga; }
/*     */ 
/*     */ 
/*     */   
/* 385 */   public void setObliga(boolean obliga) { this.obliga = obliga; }
/*     */ 
/*     */ 
/*     */   
/* 389 */   public String getRol() { return (this.rol == null) ? "" : this.rol; }
/*     */ 
/*     */ 
/*     */   
/* 393 */   public void setRol(String rol) { this.rol = rol; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 399 */   public int getDuracion() { return this.duracion; }
/*     */ 
/*     */ 
/*     */   
/* 403 */   public void setDuracion(int duracion) { this.duracion = duracion; }
/*     */ 
/*     */ 
/*     */   
/* 407 */   public int getCuantasDependen() { return this.cuantasDependen; }
/*     */ 
/*     */ 
/*     */   
/* 411 */   public void setCuantasDependen(int cuantasDependen) { this.cuantasDependen = cuantasDependen; }
/*     */ 
/*     */ 
/*     */   
/* 415 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*     */ 
/*     */ 
/*     */   
/* 419 */   public void setEstado(String estado) { this.estado = estado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 426 */   public String getUnidadMedida() { return (this.unidadMedida == null) ? "" : this.unidadMedida; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 433 */   public void setUnidadMedida(String unidadMedida) { this.unidadMedida = unidadMedida; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\CaracteristicasDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */