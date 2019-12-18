/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.ContEstudioPrevioDTO;
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
/*     */ public class ContEstudioPrevioDTO
/*     */ {
/*     */   private int numeroEstudio;
/*     */   private String tipoEstudio;
/*     */   private String fechaPresentacion;
/*     */   private String objetoContratar;
/*     */   private String descripcionNecesidad;
/*     */   private String soporteEconomico;
/*     */   private double valor;
/*     */   private String valorTexto;
/*     */   private String formaPago;
/*     */   private String formaPagoDescripcion;
/*     */   private String tipoPlazo;
/*     */   private double plazo;
/*     */   private String plazoDescripcion;
/*     */   private String tipoContrato;
/*     */   private String formaContrato;
/*     */   private String tarifa;
/*     */   private String tarifaDescripcion;
/*     */   private String lugarEjecucion;
/*     */   private String claseGasto;
/*     */   private String aplicaIva;
/*     */   private int dependencia;
/*     */   private double porcentajeAnticipo;
/*     */   private double porcentajeTarifa;
/*     */   private String interventor;
/*     */   private String fechaInsercion;
/*     */   private String usuarioInsercion;
/*     */   private String fechaModificacion;
/*     */   private String usuarioModificacion;
/*     */   private String descripcionFormaContrato;
/*     */   private String descripcionTipoContrato;
/*     */   private String descripcionFormaPago;
/*     */   private String justificacionValor;
/*     */   private int empleadoSupervisor;
/*     */   private String actividadEspecifica;
/*     */   private String fechaDesde;
/*     */   private String fechaHasta;
/*     */   
/*  90 */   public void setNumeroEstudio(int p) { this.numeroEstudio = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   public int getNumeroEstudio() { return this.numeroEstudio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   public void setTipoEstudio(String p) { this.tipoEstudio = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public String getTipoEstudio() { return (this.tipoEstudio == null) ? "" : this.tipoEstudio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   public void setFechaPresentacion(String p) { this.fechaPresentacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   public String getFechaPresentacion() { return (this.fechaPresentacion == null) ? "" : this.fechaPresentacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public void setObjetoContratar(String p) { this.objetoContratar = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 146 */   public String getObjetoContratar() { return (this.objetoContratar == null) ? "" : this.objetoContratar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   public void setDescripcionNecesidad(String p) { this.descripcionNecesidad = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 162 */   public String getDescripcionNecesidad() { return (this.descripcionNecesidad == null) ? "" : this.descripcionNecesidad; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   public void setSoporteEconomico(String p) { this.soporteEconomico = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 178 */   public String getSoporteEconomico() { return (this.soporteEconomico == null) ? "" : this.soporteEconomico; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 186 */   public void setValor(double p) { this.valor = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 194 */   public double getValor() { return this.valor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 202 */   public void setFormaPago(String p) { this.formaPago = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 210 */   public String getFormaPago() { return (this.formaPago == null) ? "" : this.formaPago; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 218 */   public void setFormaPagoDescripcion(String p) { this.formaPagoDescripcion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 226 */   public String getFormaPagoDescripcion() { return (this.formaPagoDescripcion == null) ? "" : this.formaPagoDescripcion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 234 */   public void setTipoPlazo(String p) { this.tipoPlazo = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 242 */   public String getTipoPlazo() { return (this.tipoPlazo == null) ? "" : this.tipoPlazo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 250 */   public void setPlazo(double p) { this.plazo = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 258 */   public double getPlazo() { return this.plazo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 266 */   public void setPlazoDescripcion(String p) { this.plazoDescripcion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 274 */   public String getPlazoDescripcion() { return (this.plazoDescripcion == null) ? "" : this.plazoDescripcion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 282 */   public void setTipoContrato(String p) { this.tipoContrato = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 290 */   public String getTipoContrato() { return (this.tipoContrato == null) ? "" : this.tipoContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 298 */   public void setFormaContrato(String p) { this.formaContrato = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 306 */   public String getFormaContrato() { return (this.formaContrato == null) ? "" : this.formaContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 314 */   public void setTarifa(String p) { this.tarifa = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 322 */   public String getTarifa() { return (this.tarifa == null) ? "" : this.tarifa; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 330 */   public void setTarifaDescripcion(String p) { this.tarifaDescripcion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 338 */   public String getTarifaDescripcion() { return (this.tarifaDescripcion == null) ? "" : this.tarifaDescripcion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 346 */   public void setLugarEjecucion(String p) { this.lugarEjecucion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 354 */   public String getLugarEjecucion() { return (this.lugarEjecucion == null) ? "" : this.lugarEjecucion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 362 */   public void setClaseGasto(String p) { this.claseGasto = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 370 */   public String getClaseGasto() { return (this.claseGasto == null) ? "" : this.claseGasto; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 378 */   public void setAplicaIva(String p) { this.aplicaIva = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 386 */   public String getAplicaIva() { return (this.aplicaIva == null) ? "" : this.aplicaIva; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 393 */   public void setDependencia(int dependencia) { this.dependencia = dependencia; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 400 */   public int getDependencia() { return this.dependencia; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 407 */   public double getPorcentajeAnticipo() { return this.porcentajeAnticipo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 414 */   public void setPorcentajeAnticipo(double porcentajeAnticipo) { this.porcentajeAnticipo = porcentajeAnticipo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 421 */   public double getPorcentajeTarifa() { return this.porcentajeTarifa; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 428 */   public void setPorcentajeTarifa(double porcentajeTarifa) { this.porcentajeTarifa = porcentajeTarifa; }
/*     */ 
/*     */ 
/*     */   
/* 432 */   public String getInterventor() { return (this.interventor == null) ? "" : this.interventor; }
/*     */ 
/*     */ 
/*     */   
/* 436 */   public void setInterventor(String interventor) { this.interventor = interventor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 444 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 452 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 460 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 468 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 476 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 484 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 492 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 500 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 507 */   public void setDescripcionFormaContrato(String descripcionFormaContrato) { this.descripcionFormaContrato = descripcionFormaContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 514 */   public String getDescripcionFormaContrato() { return (this.descripcionFormaContrato == null) ? "" : this.descripcionFormaContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 521 */   public void setDescripcionTipoContrato(String descripcionTipoContrato) { this.descripcionTipoContrato = descripcionTipoContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 528 */   public String getDescripcionTipoContrato() { return this.descripcionTipoContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 535 */   public void setDescripcionFormaPago(String descripcionFormaPago) { this.descripcionFormaPago = descripcionFormaPago; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 542 */   public String getDescripcionFormaPago() { return this.descripcionFormaPago; }
/*     */ 
/*     */ 
/*     */   
/* 546 */   public String getJustificacionValor() { return (this.justificacionValor == null) ? "" : this.justificacionValor; }
/*     */ 
/*     */ 
/*     */   
/* 550 */   public void setJustificacionValor(String justificacionValor) { this.justificacionValor = justificacionValor; }
/*     */ 
/*     */ 
/*     */   
/* 554 */   public int getEmpleadoSupervisor() { return this.empleadoSupervisor; }
/*     */ 
/*     */ 
/*     */   
/* 558 */   public void setEmpleadoSupervisor(int empleadoSupervisor) { this.empleadoSupervisor = empleadoSupervisor; }
/*     */ 
/*     */ 
/*     */   
/* 562 */   public String getActividadEspecifica() { return (this.actividadEspecifica == null) ? "" : this.actividadEspecifica; }
/*     */ 
/*     */ 
/*     */   
/* 566 */   public void setActividadEspecifica(String actividadEspecifica) { this.actividadEspecifica = actividadEspecifica; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 573 */   public String getValorTexto() { return (this.valorTexto == null) ? "" : this.valorTexto; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 580 */   public void setValorTexto(String valorTexto) { this.valorTexto = valorTexto; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 587 */   public String getFechaDesde() { return (this.fechaDesde == null) ? "" : this.fechaDesde; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 594 */   public void setFechaDesde(String fechaDesde) { this.fechaDesde = fechaDesde; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 601 */   public String getFechaHasta() { return (this.fechaHasta == null) ? "" : this.fechaHasta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 608 */   public void setFechaHasta(String fechaHasta) { this.fechaHasta = fechaHasta; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\ContEstudioPrevioDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */