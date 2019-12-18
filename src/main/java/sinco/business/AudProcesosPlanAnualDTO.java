/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.AudProcesosPlanAnualDTO;
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
/*     */ public class AudProcesosPlanAnualDTO
/*     */ {
/*     */   private int ciclo;
/*     */   private String codigoProceso;
/*     */   private int areaResponsable;
/*     */   private String tipoAuditoria;
/*     */   private String coordinadorAuditoria;
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
/*     */   private String fechaInsercion;
/*     */   private String usuarioInsercion;
/*     */   private String fechaModificacion;
/*     */   private String usuarioModificacion;
/*     */   private String nombreCodigoProceso;
/*     */   private String nombreAreaResponsable;
/*     */   private String nombreTipoAuditoria;
/*     */   private String nombrecoordinadorAuditoria;
/*     */   private String nombreTipoProceso;
/*     */   private String nombreLiderProceso;
/*     */   private String titulo;
/*     */   private String objetivos_especificos;
/*     */   private String alcance;
/*     */   
/*  78 */   public void setCiclo(int p) { this.ciclo = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public int getCiclo() { return this.ciclo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   public int getAreaResponsable() { return this.areaResponsable; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public void setAreaResponsable(int areaResponsable) { this.areaResponsable = areaResponsable; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   public void setTipoAuditoria(String p) { this.tipoAuditoria = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   public String getTipoAuditoria() { return (this.tipoAuditoria == null) ? "" : this.tipoAuditoria; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   public void setcoordinadorAuditoria(String p) { this.coordinadorAuditoria = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   public String getcoordinadorAuditoria() { return (this.coordinadorAuditoria == null) ? "" : this.coordinadorAuditoria; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   public void setEquipoAuditor(String p) { this.equipoAuditor = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 151 */   public String getEquipoAuditor() { return (this.equipoAuditor == null) ? "" : this.equipoAuditor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 159 */   public void setMes01(String p) { this.mes01 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 167 */   public String getMes01() { return (this.mes01 == null) ? "" : this.mes01; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 175 */   public void setMes02(String p) { this.mes02 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 183 */   public String getMes02() { return (this.mes02 == null) ? "" : this.mes02; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 191 */   public void setMes03(String p) { this.mes03 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 199 */   public String getMes03() { return (this.mes03 == null) ? "" : this.mes03; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 207 */   public void setMes04(String p) { this.mes04 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 215 */   public String getMes04() { return (this.mes04 == null) ? "" : this.mes04; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 223 */   public void setMes05(String p) { this.mes05 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 231 */   public String getMes05() { return (this.mes05 == null) ? "" : this.mes05; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 239 */   public void setMes06(String p) { this.mes06 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 247 */   public String getMes06() { return (this.mes06 == null) ? "" : this.mes06; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 255 */   public void setMes07(String p) { this.mes07 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 263 */   public String getMes07() { return (this.mes07 == null) ? "" : this.mes07; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 271 */   public void setMes08(String p) { this.mes08 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 279 */   public String getMes08() { return (this.mes08 == null) ? "" : this.mes08; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 287 */   public void setMes09(String p) { this.mes09 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 295 */   public String getMes09() { return (this.mes09 == null) ? "" : this.mes09; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 303 */   public void setMes10(String p) { this.mes10 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 311 */   public String getMes10() { return (this.mes10 == null) ? "" : this.mes10; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 319 */   public void setMes11(String p) { this.mes11 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 327 */   public String getMes11() { return (this.mes11 == null) ? "" : this.mes11; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 335 */   public void setMes12(String p) { this.mes12 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 343 */   public String getMes12() { return (this.mes12 == null) ? "" : this.mes12; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 351 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 359 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 367 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 375 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 383 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 391 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 399 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 407 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 412 */   public void setNombreCodigoProceso(String p) { this.nombreCodigoProceso = p; }
/*     */ 
/*     */   
/* 415 */   public String getNombreCodigoProceso() { return (this.nombreCodigoProceso == null) ? "" : this.nombreCodigoProceso; }
/*     */ 
/*     */   
/* 418 */   public void setNombreTipoAuditoria(String p) { this.nombreTipoAuditoria = p; }
/*     */ 
/*     */   
/* 421 */   public String getNombreTipoAuditoria() { return (this.nombreTipoAuditoria == null) ? "" : this.nombreTipoAuditoria; }
/*     */ 
/*     */   
/* 424 */   public void setNombrecoordinadorAuditoria(String p) { this.nombrecoordinadorAuditoria = p; }
/*     */ 
/*     */   
/* 427 */   public String getNombrecoordinadorAuditoria() { return (this.nombrecoordinadorAuditoria == null) ? "" : this.nombrecoordinadorAuditoria; }
/*     */ 
/*     */ 
/*     */   
/* 431 */   public String getNombreTipoProceso() { return (this.nombreTipoProceso == null) ? "" : this.nombreTipoProceso; }
/*     */ 
/*     */ 
/*     */   
/* 435 */   public String getNombreLiderProceso() { return (this.nombreLiderProceso == null) ? "" : this.nombreLiderProceso; }
/*     */ 
/*     */ 
/*     */   
/* 439 */   public void setNombreTipoProceso(String nombreTipoProceso) { this.nombreTipoProceso = nombreTipoProceso; }
/*     */ 
/*     */ 
/*     */   
/* 443 */   public void setNombreLiderProceso(String nombreLiderProceso) { this.nombreLiderProceso = nombreLiderProceso; }
/*     */ 
/*     */ 
/*     */   
/* 447 */   public String getNombreAreaResponsable() { return (this.nombreAreaResponsable == null) ? "" : this.nombreAreaResponsable; }
/*     */ 
/*     */ 
/*     */   
/* 451 */   public void setNombreAreaResponsable(String nombreAreaResponsable) { this.nombreAreaResponsable = nombreAreaResponsable; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 458 */   public String getCodigoProceso() { return (this.codigoProceso == null) ? "" : this.codigoProceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 465 */   public void setCodigoProceso(String codigoProceso) { this.codigoProceso = codigoProceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 472 */   public String getCoordinadorAuditoria() { return (this.coordinadorAuditoria == null) ? "" : this.coordinadorAuditoria; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 479 */   public void setCoordinadorAuditoria(String coordinadorAuditoria) { this.coordinadorAuditoria = coordinadorAuditoria; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 486 */   public String getTitulo() { return (this.titulo == null) ? "" : this.titulo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 493 */   public void setTitulo(String titulo) { this.titulo = titulo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 500 */   public String getObjetivos_especificos() { return (this.objetivos_especificos == null) ? "" : this.objetivos_especificos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 507 */   public void setObjetivos_especificos(String objetivosEspecificos) { this.objetivos_especificos = objetivosEspecificos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 514 */   public String getAlcance() { return (this.alcance == null) ? "" : this.alcance; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 521 */   public void setAlcance(String alcance) { this.alcance = alcance; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\AudProcesosPlanAnualDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */