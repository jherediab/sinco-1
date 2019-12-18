/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.AudInformesPlanAnualDTO;
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
/*     */ public class AudInformesPlanAnualDTO
/*     */ {
/*     */   private int ciclo;
/*     */   private int codigoInforme;
/*     */   private int areaResponsable;
/*     */   private String coordinadorAuditoría;
/*     */   private String equipoAuditor;
/*     */   private String mes01;
/*     */   private String mes02;
/*     */   private String mes03;
/*     */   private String mes04;
/*     */   private String mes05;
/*     */   private String mes06;
/*     */   private String mes07;
/*     */   private String mes08;
/*     */   private String mes09;
/*     */   private String mes10;
/*     */   private String mes11;
/*     */   private String mes12;
/*     */   private String usuarioInsercion;
/*     */   private String fechaInsercion;
/*     */   private String usuarioModificacion;
/*     */   private String fechaModificacion;
/*     */   private String nombreCodigoInforme;
/*     */   private String nombreAreaResponsable;
/*     */   private String nombreCoordinadorAuditoría;
/*     */   private String nombreTipoProceso;
/*     */   private String nombreLiderProceso;
/*     */   private String nombreTipoInforme;
/*     */   private String titulo;
/*     */   private String objetivos_especificos;
/*     */   private String alcance;
/*     */   
/*  76 */   public void setCiclo(int p) { this.ciclo = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public int getCiclo() { return this.ciclo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   public void setCodigoInforme(int p) { this.codigoInforme = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public int getCodigoInforme() { return this.codigoInforme; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public int getAreaResponsable() { return this.areaResponsable; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public void setAreaResponsable(int areaResponsable) { this.areaResponsable = areaResponsable; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public void setCoordinadorAuditoría(String p) { this.coordinadorAuditoría = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   public String getCoordinadorAuditoría() { return (this.coordinadorAuditoría == null) ? "" : this.coordinadorAuditoría; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   public void setEquipoAuditor(String p) { this.equipoAuditor = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public String getEquipoAuditor() { return (this.equipoAuditor == null) ? "" : this.equipoAuditor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   public void setMes01(String p) { this.mes01 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 164 */   public String getMes01() { return (this.mes01 == null) ? "" : this.mes01; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 172 */   public void setMes02(String p) { this.mes02 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 180 */   public String getMes02() { return (this.mes02 == null) ? "" : this.mes02; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 188 */   public void setMes03(String p) { this.mes03 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 196 */   public String getMes03() { return (this.mes03 == null) ? "" : this.mes03; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 204 */   public void setMes04(String p) { this.mes04 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 212 */   public String getMes04() { return (this.mes04 == null) ? "" : this.mes04; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 220 */   public void setMes05(String p) { this.mes05 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 228 */   public String getMes05() { return (this.mes05 == null) ? "" : this.mes05; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 236 */   public void setMes06(String p) { this.mes06 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 244 */   public String getMes06() { return (this.mes06 == null) ? "" : this.mes06; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 252 */   public void setMes07(String p) { this.mes07 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 260 */   public String getMes07() { return (this.mes07 == null) ? "" : this.mes07; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 268 */   public void setMes08(String p) { this.mes08 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 276 */   public String getMes08() { return (this.mes08 == null) ? "" : this.mes08; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 284 */   public void setMes09(String p) { this.mes09 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 292 */   public String getMes09() { return (this.mes09 == null) ? "" : this.mes09; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 300 */   public void setMes10(String p) { this.mes10 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 308 */   public String getMes10() { return (this.mes10 == null) ? "" : this.mes10; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 316 */   public void setMes11(String p) { this.mes11 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 324 */   public String getMes11() { return (this.mes11 == null) ? "" : this.mes11; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 332 */   public void setMes12(String p) { this.mes12 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 340 */   public String getMes12() { return (this.mes12 == null) ? "" : this.mes12; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 348 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 356 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 364 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 372 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 380 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 388 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 396 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 404 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 409 */   public void setNombreCodigoInforme(String p) { this.nombreCodigoInforme = p; }
/*     */ 
/*     */   
/* 412 */   public String getNombreCodigoInforme() { return (this.nombreCodigoInforme == null) ? "" : this.nombreCodigoInforme; }
/*     */ 
/*     */   
/* 415 */   public void setNombreCoordinadorAuditoría(String p) { this.nombreCoordinadorAuditoría = p; }
/*     */ 
/*     */   
/* 418 */   public String getNombreCoordinadorAuditoría() { return (this.nombreCoordinadorAuditoría == null) ? "" : this.nombreCoordinadorAuditoría; }
/*     */ 
/*     */ 
/*     */   
/* 422 */   public void setNombreTipoProceso(String nombreTipoProceso) { this.nombreTipoProceso = nombreTipoProceso; }
/*     */ 
/*     */ 
/*     */   
/* 426 */   public void setNombreLiderProceso(String nombreLiderProceso) { this.nombreLiderProceso = nombreLiderProceso; }
/*     */ 
/*     */ 
/*     */   
/* 430 */   public String getNombreTipoProceso() { return (this.nombreTipoProceso == null) ? "" : this.nombreTipoProceso; }
/*     */ 
/*     */ 
/*     */   
/* 434 */   public String getNombreLiderProceso() { return (this.nombreLiderProceso == null) ? "" : this.nombreLiderProceso; }
/*     */ 
/*     */ 
/*     */   
/* 438 */   public String getNombreTipoInforme() { return (this.nombreTipoInforme == null) ? "" : this.nombreTipoInforme; }
/*     */ 
/*     */ 
/*     */   
/* 442 */   public void setNombreTipoInforme(String nombreTipoInforme) { this.nombreTipoInforme = nombreTipoInforme; }
/*     */ 
/*     */ 
/*     */   
/* 446 */   public String getNombreAreaResponsable() { return this.nombreAreaResponsable; }
/*     */ 
/*     */ 
/*     */   
/* 450 */   public void setNombreAreaResponsable(String nombreAreaResponsable) { this.nombreAreaResponsable = nombreAreaResponsable; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 457 */   public String getTitulo() { return (this.titulo == null) ? "" : this.titulo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 464 */   public void setTitulo(String titulo) { this.titulo = titulo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 471 */   public String getObjetivos_especificos() { return (this.objetivos_especificos == null) ? "" : this.objetivos_especificos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 478 */   public void setObjetivos_especificos(String objetivosEspecificos) { this.objetivos_especificos = objetivosEspecificos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 485 */   public String getAlcance() { return (this.alcance == null) ? "" : this.alcance; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 492 */   public void setAlcance(String alcance) { this.alcance = alcance; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\AudInformesPlanAnualDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */