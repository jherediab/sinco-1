/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.CaracteristiasDTO;
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
/*     */ public class CaracteristiasDTO
/*     */ {
/*     */   private int codigo;
/*     */   private String descripcion;
/*     */   private int tipo;
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
/*     */   
/*  60 */   public void setCodigo(int p) { this.codigo = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   public int getCodigo() { return this.codigo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   public void setDescripcion(String p) { this.descripcion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public String getDescripcion() { return (this.descripcion == null) ? "" : this.descripcion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   public void setTipo(int p) { this.tipo = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public int getTipo() { return this.tipo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public void setCalificar(String p) { this.calificar = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public String getCalificar() { return (this.calificar == null) ? "" : this.calificar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public void setLongitud(int p) { this.longitud = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   public int getLongitud() { return this.longitud; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   public void setCompetencia(String p) { this.competencia = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public String getCompetencia() { return (this.competencia == null) ? "" : this.competencia; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   public void setCaracteristicaAnida(int p) { this.caracteristicaAnida = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 164 */   public int getCaracteristicaAnida() { return this.caracteristicaAnida; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 172 */   public void setPermiteExtender(String p) { this.permiteExtender = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 180 */   public String getPermiteExtender() { return (this.permiteExtender == null) ? "" : this.permiteExtender; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 188 */   public void setCaracteristicaDepende(int p) { this.caracteristicaDepende = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 196 */   public int getCaracteristicaDepende() { return this.caracteristicaDepende; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 204 */   public void setValorDepende(int p) { this.valorDepende = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 212 */   public int getValorDepende() { return this.valorDepende; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 220 */   public void setTipoValidacion(String p) { this.tipoValidacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 228 */   public String getTipoValidacion() { return (this.tipoValidacion == null) ? "" : this.tipoValidacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 236 */   public void setCaracteristicaValida(int p) { this.caracteristicaValida = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 244 */   public int getCaracteristicaValida() { return this.caracteristicaValida; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 252 */   public void setNombreProcedimiento(String p) { this.nombreProcedimiento = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 260 */   public String getNombreProcedimiento() { return (this.nombreProcedimiento == null) ? "" : this.nombreProcedimiento; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 268 */   public void setNumeroDecimales(int p) { this.numeroDecimales = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 276 */   public int getNumeroDecimales() { return this.numeroDecimales; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 284 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 292 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 300 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 308 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 316 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 324 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 332 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 340 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 345 */   public void setNombreTipo(String p) { this.nombreTipo = p; }
/*     */ 
/*     */   
/* 348 */   public String getNombreTipo() { return (this.nombreTipo == null) ? "" : this.nombreTipo; }
/*     */ 
/*     */   
/* 351 */   public void setNombreCompetencia(String p) { this.nombreCompetencia = p; }
/*     */ 
/*     */   
/* 354 */   public String getNombreCompetencia() { return (this.nombreCompetencia == null) ? "" : this.nombreCompetencia; }
/*     */ 
/*     */   
/* 357 */   public void setNombreValorDepende(String p) { this.nombreValorDepende = p; }
/*     */ 
/*     */   
/* 360 */   public String getNombreValorDepende() { return (this.nombreValorDepende == null) ? "" : this.nombreValorDepende; }
/*     */ 
/*     */   
/* 363 */   public void setNombreTipoValidacion(String p) { this.nombreTipoValidacion = p; }
/*     */ 
/*     */   
/* 366 */   public String getNombreTipoValidacion() { return (this.nombreTipoValidacion == null) ? "" : this.nombreTipoValidacion; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\CaracteristiasDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */