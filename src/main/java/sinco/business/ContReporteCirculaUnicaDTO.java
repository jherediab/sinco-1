/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.ContReporteCirculaUnicaDTO;
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
/*     */ public class ContReporteCirculaUnicaDTO
/*     */ {
/*     */   private String nitPrestador;
/*     */   private int dvPrestador;
/*     */   private String numeroContrato;
/*     */   private String fechaInicioContrato;
/*     */   private String fechaFinalContrato;
/*     */   private String tipoContrato;
/*     */   private String tipoPlan;
/*     */   private double numeroAfiliados;
/*     */   private int porcentajeUPC;
/*     */   private String mecanismoVerificacion;
/*     */   private double valorContrato;
/*     */   private String areaCobertura;
/*     */   private int codigoServicio;
/*     */   private int modalidadServicio;
/*     */   private String complejidadServicio;
/*     */   private String codigoIndicador;
/*     */   private double numerador;
/*     */   private double denominador;
/*     */   private String estandar;
/*     */   
/*  47 */   public String getNitPrestador() { return (this.nitPrestador == null) ? "" : this.nitPrestador; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public void setNitPrestador(String nitPrestador) { this.nitPrestador = nitPrestador; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   public int getDvPrestador() { return this.dvPrestador; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   public void setDvPrestador(int dvPrestador) { this.dvPrestador = dvPrestador; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public String getNumeroContrato() { return (this.numeroContrato == null) ? "" : this.numeroContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   public void setNumeroContrato(String numeroContrato) { this.numeroContrato = numeroContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   public String getFechaInicioContrato() { return (this.fechaInicioContrato == null) ? "" : this.fechaInicioContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   public void setFechaInicioContrato(String fechaInicioContrato) { this.fechaInicioContrato = fechaInicioContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   public String getFechaFinalContrato() { return (this.fechaFinalContrato == null) ? "" : this.fechaFinalContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   public void setFechaFinalContrato(String fechaFinalContrato) { this.fechaFinalContrato = fechaFinalContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   public String getTipoContrato() { return (this.tipoContrato == null) ? "" : this.tipoContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public void setTipoContrato(String tipoContrato) { this.tipoContrato = tipoContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   public String getTipoPlan() { return (this.tipoPlan == null) ? "" : this.tipoPlan; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public void setTipoPlan(String tipoPlan) { this.tipoPlan = tipoPlan; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 145 */   public double getNumeroAfiliados() { return this.numeroAfiliados; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   public void setNumeroAfiliados(int numeroAfiliados) { this.numeroAfiliados = numeroAfiliados; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 159 */   public int getPorcentajeUPC() { return this.porcentajeUPC; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public void setPorcentajeUPC(int porcentajeUPC) { this.porcentajeUPC = porcentajeUPC; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 173 */   public String getMecanismoVerificacion() { return (this.mecanismoVerificacion == null) ? "" : this.mecanismoVerificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 180 */   public void setMecanismoVerificacion(String mecanismoVerificacion) { this.mecanismoVerificacion = mecanismoVerificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 187 */   public double getValorContrato() { return this.valorContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 194 */   public void setValorContrato(double valorContrato) { this.valorContrato = valorContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 201 */   public String getAreaCobertura() { return (this.areaCobertura == null) ? "" : this.areaCobertura; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 208 */   public void setAreaCobertura(String areaCobertura) { this.areaCobertura = areaCobertura; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 215 */   public int getCodigoServicio() { return this.codigoServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 222 */   public void setCodigoServicio(int codigoServicio) { this.codigoServicio = codigoServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 229 */   public int getModalidadServicio() { return this.modalidadServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 236 */   public void setModalidadServicio(int modalidadServicio) { this.modalidadServicio = modalidadServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 243 */   public String getComplejidadServicio() { return (this.complejidadServicio == null) ? "" : this.complejidadServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 250 */   public void setComplejidadServicio(String complejidadServicio) { this.complejidadServicio = complejidadServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 257 */   public String getCodigoIndicador() { return (this.codigoIndicador == null) ? "" : this.codigoIndicador; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 264 */   public void setCodigoIndicador(String codigoIndicador) { this.codigoIndicador = codigoIndicador; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 271 */   public double getNumerador() { return this.numerador; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 278 */   public void setNumerador(double numerador) { this.numerador = numerador; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 285 */   public double getDenominador() { return this.denominador; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 292 */   public void setDenominador(double denominador) { this.denominador = denominador; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 299 */   public String getEstandar() { return (this.estandar == null) ? "" : this.estandar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 306 */   public void setEstandar(String estandar) { this.estandar = estandar; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\ContReporteCirculaUnicaDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */