/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.CalMetasDTO;
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
/*     */ public class CalMetasDTO
/*     */ {
/*     */   private int codigoCiclo;
/*     */   private int codigoPlan;
/*     */   private int codigoMeta;
/*     */   private int codigoObjetivo;
/*     */   private String descripcion;
/*     */   private String justificacion;
/*     */   private double valorMeta;
/*     */   private double valorMinimo;
/*     */   private double valorMaximo;
/*     */   private String tipoMedicion;
/*     */   private String fuenteDato;
/*     */   private String aplicaEn;
/*     */   private String unidadMedida;
/*     */   private String tipoGrafica;
/*     */   private String nombreUnidadMedida;
/*     */   private String estado;
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
/*     */   private String nombreArea;
/*     */   private String nombreMeta;
/*     */   private String nombreObjetivo;
/*     */   private double valorLogro;
/*     */   private int numeroAcciones;
/*     */   private String nombreTipoMedicion;
/*     */   private String nombreEstado;
/*     */   
/*  86 */   public void setCodigoCiclo(int p) { this.codigoCiclo = p; }
/*     */ 
/*     */   
/*  89 */   public int getCodigoCiclo() { return this.codigoCiclo; }
/*     */ 
/*     */ 
/*     */   
/*  93 */   public void setCodigoPlan(int p) { this.codigoPlan = p; }
/*     */ 
/*     */   
/*  96 */   public int getCodigoPlan() { return this.codigoPlan; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public void setCodigoMeta(int p) { this.codigoMeta = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   public int getCodigoMeta() { return this.codigoMeta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   public void setCodigoObjetivo(int p) { this.codigoObjetivo = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   public int getCodigoObjetivo() { return this.codigoObjetivo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   public void setDescripcion(String p) { this.descripcion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 145 */   public String getDescripcion() { return (this.descripcion == null) ? "" : this.descripcion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 153 */   public void setJustificacion(String p) { this.justificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 161 */   public String getJustificacion() { return (this.justificacion == null) ? "" : this.justificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 169 */   public void setValorMeta(double p) { this.valorMeta = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 177 */   public double getValorMeta() { return this.valorMeta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 185 */   public void setTipoMedicion(String p) { this.tipoMedicion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 193 */   public String getTipoMedicion() { return (this.tipoMedicion == null) ? "" : this.tipoMedicion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 201 */   public void setEstado(String p) { this.estado = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 209 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 217 */   public void setMes01(String p) { this.mes01 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 225 */   public String getMes01() { return (this.mes01 == null) ? "N" : this.mes01; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 233 */   public void setMes02(String p) { this.mes02 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 241 */   public String getMes02() { return (this.mes02 == null) ? "N" : this.mes02; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 249 */   public void setMes03(String p) { this.mes03 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 257 */   public String getMes03() { return (this.mes03 == null) ? "N" : this.mes03; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 265 */   public void setMes04(String p) { this.mes04 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 273 */   public String getMes04() { return (this.mes04 == null) ? "N" : this.mes04; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 281 */   public void setMes05(String p) { this.mes05 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 289 */   public String getMes05() { return (this.mes05 == null) ? "N" : this.mes05; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 297 */   public void setMes06(String p) { this.mes06 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 305 */   public String getMes06() { return (this.mes06 == null) ? "N" : this.mes06; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 313 */   public void setMes07(String p) { this.mes07 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 321 */   public String getMes07() { return (this.mes07 == null) ? "N" : this.mes07; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 329 */   public void setMes08(String p) { this.mes08 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 337 */   public String getMes08() { return (this.mes08 == null) ? "N" : this.mes08; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 345 */   public void setMes09(String p) { this.mes09 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 353 */   public String getMes09() { return (this.mes09 == null) ? "N" : this.mes09; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 361 */   public void setMes10(String p) { this.mes10 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 369 */   public String getMes10() { return (this.mes10 == null) ? "N" : this.mes10; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 377 */   public void setMes11(String p) { this.mes11 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 385 */   public String getMes11() { return (this.mes11 == null) ? "N" : this.mes11; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 393 */   public void setMes12(String p) { this.mes12 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 401 */   public String getMes12() { return (this.mes12 == null) ? "N" : this.mes12; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 409 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 417 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 425 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 433 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 441 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 449 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 457 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 465 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 472 */   public void setNombreTipoMedicion(String p) { this.nombreTipoMedicion = p; }
/*     */ 
/*     */   
/* 475 */   public String getNombreTipoMedicion() { return (this.nombreTipoMedicion == null) ? "" : this.nombreTipoMedicion; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 480 */   public void setNombreEstado(String p) { this.nombreEstado = p; }
/*     */ 
/*     */   
/* 483 */   public String getNombreEstado() { return (this.nombreEstado == null) ? "" : this.nombreEstado; }
/*     */ 
/*     */   
/*     */   public String getNombreFrecuenciaMedicion() {
/* 487 */     String rta = "";
/*     */     
/* 489 */     if (getMes01().equals("S") && getMes02().equals("S") && getMes03().equals("S") && getMes04().equals("S") && getMes05().equals("S") && getMes06().equals("S") && getMes07().equals("S") && getMes08().equals("S") && getMes09().equals("S") && getMes10().equals("S") && getMes11().equals("S") && getMes12().equals("S"))
/*     */     {
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
/* 501 */       return "Mensual";
/*     */     }
/*     */     
/* 504 */     if (getMes01().equals("N") && getMes02().equals("N") && getMes03().equals("S") && getMes04().equals("N") && getMes05().equals("N") && getMes06().equals("S") && getMes07().equals("N") && getMes08().equals("N") && getMes09().equals("S") && getMes10().equals("N") && getMes11().equals("N") && getMes12().equals("S"))
/*     */     {
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
/* 516 */       return "Trimestral";
/*     */     }
/*     */     
/* 519 */     if (getMes01().equals("N") && getMes02().equals("S") && getMes03().equals("N") && getMes04().equals("S") && getMes05().equals("N") && getMes06().equals("S") && getMes07().equals("N") && getMes08().equals("S") && getMes09().equals("N") && getMes10().equals("S") && getMes11().equals("N") && getMes12().equals("S"))
/*     */     {
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
/* 531 */       return "Bimensual";
/*     */     }
/*     */     
/* 534 */     if (getMes01().equals("N") && getMes02().equals("N") && getMes03().equals("N") && getMes04().equals("S") && getMes05().equals("N") && getMes06().equals("N") && getMes07().equals("N") && getMes08().equals("S") && getMes09().equals("N") && getMes10().equals("N") && getMes11().equals("N") && getMes12().equals("S"))
/*     */     {
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
/* 546 */       return "Cuatrimestral";
/*     */     }
/*     */     
/* 549 */     if (getMes01().equals("N") && getMes02().equals("N") && getMes03().equals("N") && getMes04().equals("N") && getMes05().equals("N") && getMes06().equals("S") && getMes07().equals("N") && getMes08().equals("N") && getMes09().equals("N") && getMes10().equals("N") && getMes11().equals("N") && getMes12().equals("S"))
/*     */     {
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
/* 561 */       return "Semestral";
/*     */     }
/*     */     
/* 564 */     if (getMes01().equals("N") && getMes02().equals("N") && getMes03().equals("N") && getMes04().equals("N") && getMes05().equals("N") && getMes06().equals("N") && getMes07().equals("N") && getMes08().equals("N") && getMes09().equals("N") && getMes10().equals("N") && getMes11().equals("N") && getMes12().equals("S"))
/*     */     {
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
/* 576 */       return "Anual";
/*     */     }
/*     */     
/* 579 */     if (getMes01().equals("S")) {
/* 580 */       rta = rta + "ENE ";
/*     */     }
/*     */     
/* 583 */     if (getMes02().equals("S")) {
/* 584 */       rta = rta + "FEB ";
/*     */     }
/*     */     
/* 587 */     if (getMes03().equals("S")) {
/* 588 */       rta = rta + "MAR ";
/*     */     }
/*     */     
/* 591 */     if (getMes04().equals("S")) {
/* 592 */       rta = rta + "ABR ";
/*     */     }
/*     */     
/* 595 */     if (getMes05().equals("S")) {
/* 596 */       rta = rta + "MAY ";
/*     */     }
/*     */     
/* 599 */     if (getMes06().equals("S")) {
/* 600 */       rta = rta + "JUN ";
/*     */     }
/*     */     
/* 603 */     if (getMes07().equals("S")) {
/* 604 */       rta = rta + "JUL ";
/*     */     }
/*     */     
/* 607 */     if (getMes08().equals("S")) {
/* 608 */       rta = rta + "AGO ";
/*     */     }
/*     */     
/* 611 */     if (getMes09().equals("S")) {
/* 612 */       rta = rta + "SEP ";
/*     */     }
/*     */     
/* 615 */     if (getMes10().equals("S")) {
/* 616 */       rta = rta + "OCT ";
/*     */     }
/*     */     
/* 619 */     if (getMes11().equals("S")) {
/* 620 */       rta = rta + "NOV ";
/*     */     }
/*     */     
/* 623 */     if (getMes12().equals("S")) {
/* 624 */       rta = rta + "DIC ";
/*     */     }
/* 626 */     return rta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 632 */   public String getFuenteDato() { return (this.fuenteDato == null) ? "" : this.fuenteDato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 638 */   public void setFuenteDato(String fuenteDato) { this.fuenteDato = fuenteDato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 644 */   public String getAplicaEn() { return (this.aplicaEn == null) ? "" : this.aplicaEn; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 650 */   public void setAplicaEn(String aplicaEn) { this.aplicaEn = aplicaEn; }
/*     */ 
/*     */ 
/*     */   
/* 654 */   public String getNombreEstadoVer() { return (this.nombreEstado == null) ? "" : (this.nombreEstado.equals("I") ? " (INACTIVO)" : ""); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 660 */   public String getUnidadMedida() { return (this.unidadMedida == null) ? "" : this.unidadMedida; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 666 */   public void setUnidadMedida(String unidadMedida) { this.unidadMedida = unidadMedida; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 672 */   public double getValorMinimo() { return this.valorMinimo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 678 */   public void setValorMinimo(double valorMinimo) { this.valorMinimo = valorMinimo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 684 */   public double getValorMaximo() { return this.valorMaximo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 690 */   public void setValorMaximo(double valorMaximo) { this.valorMaximo = valorMaximo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 696 */   public String getNombreUnidadMedida() { return (this.nombreUnidadMedida == null) ? "" : this.nombreUnidadMedida; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 702 */   public void setNombreUnidadMedida(String nombreUnidadMedida) { this.nombreUnidadMedida = nombreUnidadMedida; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNombreUnidadMed() {
/* 710 */     String nombre = "";
/*     */     
/* 712 */     if (this.unidadMedida == null) {
/* 713 */       this.unidadMedida = "PO";
/*     */     }
/* 715 */     if (this.unidadMedida.equals("PO")) {
/* 716 */       nombre = "%";
/*     */     }
/* 718 */     if (!this.unidadMedida.equals("PO")) {
/* 719 */       nombre = this.unidadMedida;
/*     */     }
/* 721 */     return nombre;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 727 */   public String getNombreArea() { return (this.nombreArea == null) ? "" : this.nombreArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 733 */   public void setNombreArea(String nombreArea) { this.nombreArea = nombreArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 739 */   public double getValorLogro() { return this.valorLogro; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 745 */   public void setValorLogro(double valorLogro) { this.valorLogro = valorLogro; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 751 */   public String getNombreMeta() { return (this.nombreMeta == null) ? "" : this.nombreMeta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 757 */   public void setNombreMeta(String nombreMeta) { this.nombreMeta = nombreMeta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 763 */   public String getNombreObjetivo() { return (this.nombreObjetivo == null) ? "" : this.nombreObjetivo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 769 */   public void setNombreObjetivo(String nombreObjetivo) { this.nombreObjetivo = nombreObjetivo; }
/*     */ 
/*     */   
/* 772 */   public String getTipoGrafica() { return (this.tipoGrafica == null) ? "" : this.tipoGrafica; }
/*     */ 
/*     */   
/* 775 */   public void setTipoGrafica(String tipoGrafica) { this.tipoGrafica = tipoGrafica; }
/*     */ 
/*     */   
/* 778 */   public int getNumeroAcciones() { return this.numeroAcciones; }
/*     */ 
/*     */   
/* 781 */   public void setNumeroAcciones(int numeroAcciones) { this.numeroAcciones = numeroAcciones; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\CalMetasDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */