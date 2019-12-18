/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.CalEstadisticasDTO;
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
/*     */ public class CalEstadisticasDTO
/*     */ {
/*     */   private String nombreArea;
/*     */   private int codigoCiclo;
/*     */   private int codigoPlan;
/*     */   private int codigoArea;
/*     */   private String nombreObjetivo;
/*     */   private String nombreMeta;
/*     */   private int codigoObjetivo;
/*     */   private int codigoMeta;
/*     */   private String tipoMedicion;
/*     */   private String unidadMedida;
/*     */   private String nombreProceso;
/*     */   private String nombreSubProceso;
/*     */   private String p01;
/*     */   private String p02;
/*     */   private String p03;
/*     */   private String p04;
/*     */   private String p05;
/*     */   private String p06;
/*     */   private String p07;
/*     */   private String p08;
/*     */   private String p09;
/*     */   private String p10;
/*     */   private String p11;
/*     */   private String p12;
/*     */   private double logroEne;
/*     */   private double logroFeb;
/*     */   private double logroMar;
/*     */   private double logroAbr;
/*     */   private double logroMay;
/*     */   private double logroJun;
/*     */   private double logroJul;
/*     */   private double logroAgo;
/*     */   private double logroSep;
/*     */   private double logroOct;
/*     */   private double logroNov;
/*     */   private double logroDic;
/*     */   private double metaEne;
/*     */   private double metaFeb;
/*     */   private double metaMar;
/*     */   private double metaAbr;
/*     */   private double metaMay;
/*     */   private double metaJun;
/*     */   private double metaJul;
/*     */   private double metaAgo;
/*     */   private double metaSep;
/*     */   private double metaOct;
/*     */   private double metaNov;
/*     */   private double metaDic;
/*     */   private double miniEne;
/*     */   private double miniFeb;
/*     */   private double miniMar;
/*     */   private double miniAbr;
/*     */   private double miniMay;
/*     */   private double miniJun;
/*     */   private double miniJul;
/*     */   private double miniAgo;
/*     */   private double miniSep;
/*     */   private double miniOct;
/*     */   private double miniNov;
/*     */   private double miniDic;
/*     */   private double maxiEne;
/*     */   private double maxiFeb;
/*     */   private double maxiMar;
/*     */   private double maxiAbr;
/*     */   private double maxiMay;
/*     */   private double maxiJun;
/*     */   private double maxiJul;
/*     */   private double maxiAgo;
/*     */   private double maxiSep;
/*     */   private double maxiOct;
/*     */   private double maxiNov;
/*     */   private double maxiDic;
/*     */   
/*  91 */   public String getNombreArea() { return (this.nombreArea == null) ? "" : this.nombreArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public void setNombreArea(String nombreArea) { this.nombreArea = nombreArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   public int getCodigoCiclo() { return this.codigoCiclo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   public void setCodigoCiclo(int codigoCiclo) { this.codigoCiclo = codigoCiclo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   public String getNombreObjetivo() { return (this.nombreObjetivo == null) ? "" : this.nombreObjetivo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   public void setNombreObjetivo(String nombreObjetivo) { this.nombreObjetivo = nombreObjetivo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   public String getNombreMeta() { return (this.nombreMeta == null) ? "" : this.nombreMeta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 133 */   public void setNombreMeta(String nombreMeta) { this.nombreMeta = nombreMeta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 139 */   public int getCodigoObjetivo() { return this.codigoObjetivo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 145 */   public void setCodigoObjetivo(int codigoObjetivo) { this.codigoObjetivo = codigoObjetivo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 151 */   public int getCodigoMeta() { return this.codigoMeta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 157 */   public void setCodigoMeta(int codigoMeta) { this.codigoMeta = codigoMeta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 164 */   public int getCodigoPlan() { return this.codigoPlan; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   public void setCodigoPlan(int codigoPlan) { this.codigoPlan = codigoPlan; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 176 */   public int getCodigoArea() { return this.codigoArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 182 */   public void setCodigoArea(int codigoArea) { this.codigoArea = codigoArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 188 */   public String getP01() { return (this.p01 == null) ? "" : this.p01; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 194 */   public void setP01(String p01) { this.p01 = p01; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 200 */   public String getP02() { return (this.p02 == null) ? "" : this.p02; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 206 */   public void setP02(String p02) { this.p02 = p02; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 212 */   public String getP03() { return (this.p03 == null) ? "" : this.p03; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 218 */   public void setP03(String p03) { this.p03 = p03; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 224 */   public String getP04() { return (this.p04 == null) ? "" : this.p04; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 230 */   public void setP04(String p04) { this.p04 = p04; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 236 */   public String getP05() { return (this.p05 == null) ? "" : this.p05; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 242 */   public void setP05(String p05) { this.p05 = p05; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 248 */   public String getP06() { return (this.p06 == null) ? "" : this.p06; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 254 */   public void setP06(String p06) { this.p06 = p06; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 260 */   public String getP07() { return (this.p07 == null) ? "" : this.p07; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 266 */   public void setP07(String p07) { this.p07 = p07; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 272 */   public String getP08() { return (this.p08 == null) ? "" : this.p08; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 278 */   public void setP08(String p08) { this.p08 = p08; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 284 */   public String getP09() { return (this.p09 == null) ? "" : this.p09; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 290 */   public void setP09(String p09) { this.p09 = p09; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 296 */   public String getP10() { return (this.p10 == null) ? "" : this.p10; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 302 */   public void setP10(String p10) { this.p10 = p10; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 308 */   public String getP11() { return (this.p11 == null) ? "" : this.p11; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 314 */   public void setP11(String p11) { this.p11 = p11; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 320 */   public String getP12() { return (this.p12 == null) ? "" : this.p12; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 326 */   public void setP12(String p12) { this.p12 = p12; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 332 */   public double getLogroEne() { return this.logroEne; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 338 */   public void setLogroEne(double logroEne) { this.logroEne = logroEne; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 344 */   public double getLogroFeb() { return this.logroFeb; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 350 */   public void setLogroFeb(double logroFeb) { this.logroFeb = logroFeb; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 356 */   public double getLogroMar() { return this.logroMar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 362 */   public void setLogroMar(double logroMar) { this.logroMar = logroMar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 368 */   public double getLogroAbr() { return this.logroAbr; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 374 */   public void setLogroAbr(double logroAbr) { this.logroAbr = logroAbr; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 380 */   public double getLogroMay() { return this.logroMay; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 386 */   public void setLogroMay(double logroMay) { this.logroMay = logroMay; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 392 */   public double getLogroJun() { return this.logroJun; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 398 */   public void setLogroJun(double logroJun) { this.logroJun = logroJun; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 404 */   public double getLogroJul() { return this.logroJul; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 410 */   public void setLogroJul(double logroJul) { this.logroJul = logroJul; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 416 */   public double getLogroAgo() { return this.logroAgo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 422 */   public void setLogroAgo(double logroAgo) { this.logroAgo = logroAgo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 428 */   public double getLogroSep() { return this.logroSep; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 434 */   public void setLogroSep(double logroSep) { this.logroSep = logroSep; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 440 */   public double getLogroOct() { return this.logroOct; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 446 */   public void setLogroOct(double logroOct) { this.logroOct = logroOct; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 452 */   public double getLogroNov() { return this.logroNov; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 458 */   public void setLogroNov(double logroNov) { this.logroNov = logroNov; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 464 */   public double getLogroDic() { return this.logroDic; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 470 */   public void setLogroDic(double logroDic) { this.logroDic = logroDic; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 476 */   public double getMetaEne() { return this.metaEne; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 482 */   public void setMetaEne(double metaEne) { this.metaEne = metaEne; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 488 */   public double getMetaFeb() { return this.metaFeb; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 494 */   public void setMetaFeb(double metaFeb) { this.metaFeb = metaFeb; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 500 */   public double getMetaMar() { return this.metaMar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 506 */   public void setMetaMar(double metaMar) { this.metaMar = metaMar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 512 */   public double getMetaAbr() { return this.metaAbr; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 518 */   public void setMetaAbr(double metaAbr) { this.metaAbr = metaAbr; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 524 */   public double getMetaMay() { return this.metaMay; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 530 */   public void setMetaMay(double metaMay) { this.metaMay = metaMay; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 536 */   public double getMetaJun() { return this.metaJun; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 542 */   public void setMetaJun(double metaJun) { this.metaJun = metaJun; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 548 */   public double getMetaJul() { return this.metaJul; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 554 */   public void setMetaJul(double metaJul) { this.metaJul = metaJul; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 560 */   public double getMetaAgo() { return this.metaAgo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 566 */   public void setMetaAgo(double metaAgo) { this.metaAgo = metaAgo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 572 */   public double getMetaSep() { return this.metaSep; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 578 */   public void setMetaSep(double metaSep) { this.metaSep = metaSep; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 584 */   public double getMetaOct() { return this.metaOct; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 590 */   public void setMetaOct(double metaOct) { this.metaOct = metaOct; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 596 */   public double getMetaNov() { return this.metaNov; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 602 */   public void setMetaNov(double metaNov) { this.metaNov = metaNov; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 608 */   public double getMetaDic() { return this.metaDic; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 614 */   public void setMetaDic(double metaDic) { this.metaDic = metaDic; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 620 */   public String getNombreProceso() { return (this.nombreProceso == null) ? "" : this.nombreProceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 626 */   public void setNombreProceso(String nombreProceso) { this.nombreProceso = nombreProceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 632 */   public String getNombreSubProceso() { return (this.nombreSubProceso == null) ? "" : this.nombreSubProceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 638 */   public void setNombreSubProceso(String nombreSubProceso) { this.nombreSubProceso = nombreSubProceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 644 */   public double getMiniEne() { return this.miniEne; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 650 */   public void setMiniEne(double miniEne) { this.miniEne = miniEne; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 656 */   public double getMiniFeb() { return this.miniFeb; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 662 */   public void setMiniFeb(double miniFeb) { this.miniFeb = miniFeb; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 668 */   public double getMiniMar() { return this.miniMar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 674 */   public void setMiniMar(double miniMar) { this.miniMar = miniMar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 680 */   public double getMiniAbr() { return this.miniAbr; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 686 */   public void setMiniAbr(double miniAbr) { this.miniAbr = miniAbr; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 692 */   public double getMiniMay() { return this.miniMay; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 698 */   public void setMiniMay(double miniMay) { this.miniMay = miniMay; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 704 */   public double getMiniJun() { return this.miniJun; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 710 */   public void setMiniJun(double miniJun) { this.miniJun = miniJun; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 716 */   public double getMiniJul() { return this.miniJul; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 722 */   public void setMiniJul(double miniJul) { this.miniJul = miniJul; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 728 */   public double getMiniAgo() { return this.miniAgo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 734 */   public void setMiniAgo(double miniAgo) { this.miniAgo = miniAgo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 740 */   public double getMiniSep() { return this.miniSep; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 746 */   public void setMiniSep(double miniSep) { this.miniSep = miniSep; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 752 */   public double getMiniOct() { return this.miniOct; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 758 */   public void setMiniOct(double miniOct) { this.miniOct = miniOct; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 764 */   public double getMiniNov() { return this.miniNov; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 770 */   public void setMiniNov(double miniNov) { this.miniNov = miniNov; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 776 */   public double getMiniDic() { return this.miniDic; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 782 */   public void setMiniDic(double miniDic) { this.miniDic = miniDic; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 788 */   public double getMaxiEne() { return this.maxiEne; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 794 */   public void setMaxiEne(double maxiEne) { this.maxiEne = maxiEne; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 800 */   public double getMaxiFeb() { return this.maxiFeb; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 806 */   public void setMaxiFeb(double maxiFeb) { this.maxiFeb = maxiFeb; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 812 */   public double getMaxiMar() { return this.maxiMar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 818 */   public void setMaxiMar(double maxiMar) { this.maxiMar = maxiMar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 824 */   public double getMaxiAbr() { return this.maxiAbr; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 830 */   public void setMaxiAbr(double maxiAbr) { this.maxiAbr = maxiAbr; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 836 */   public double getMaxiMay() { return this.maxiMay; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 842 */   public void setMaxiMay(double maxiMay) { this.maxiMay = maxiMay; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 848 */   public double getMaxiJun() { return this.maxiJun; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 854 */   public void setMaxiJun(double maxiJun) { this.maxiJun = maxiJun; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 860 */   public double getMaxiJul() { return this.maxiJul; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 866 */   public void setMaxiJul(double maxiJul) { this.maxiJul = maxiJul; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 872 */   public double getMaxiAgo() { return this.maxiAgo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 878 */   public void setMaxiAgo(double maxiAgo) { this.maxiAgo = maxiAgo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 884 */   public double getMaxiSep() { return this.maxiSep; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 890 */   public void setMaxiSep(double maxiSep) { this.maxiSep = maxiSep; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 896 */   public double getMaxiOct() { return this.maxiOct; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 902 */   public void setMaxiOct(double maxiOct) { this.maxiOct = maxiOct; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 908 */   public double getMaxiNov() { return this.maxiNov; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 914 */   public void setMaxiNov(double maxiNov) { this.maxiNov = maxiNov; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 920 */   public double getMaxiDic() { return this.maxiDic; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 926 */   public void setMaxiDic(double maxiDic) { this.maxiDic = maxiDic; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 932 */   public String getTipoMedicion() { return (this.tipoMedicion == null) ? "" : this.tipoMedicion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 938 */   public void setTipoMedicion(String tipoMedicion) { this.tipoMedicion = tipoMedicion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNombreUnidadMed() {
/* 945 */     String nombre = "";
/*     */     
/* 947 */     if (this.unidadMedida == null) {
/* 948 */       this.unidadMedida = "PO";
/*     */     }
/* 950 */     if (this.unidadMedida.equals("PO")) {
/* 951 */       nombre = "%";
/*     */     }
/* 953 */     if (!this.unidadMedida.equals("PO")) {
/* 954 */       nombre = this.unidadMedida;
/*     */     }
/* 956 */     return nombre;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 962 */   public String getUnidadMedida() { return (this.unidadMedida == null) ? "" : this.unidadMedida; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 968 */   public void setUnidadMedida(String unidadMedida) { this.unidadMedida = unidadMedida; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\CalEstadisticasDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */