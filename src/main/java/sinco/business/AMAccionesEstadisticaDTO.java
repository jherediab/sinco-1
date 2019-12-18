/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.AMAccionesEstadisticaDTO;
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
/*     */ public class AMAccionesEstadisticaDTO
/*     */ {
/*     */   private String accion;
/*     */   private int Total;
/*     */   private int proceso;
/*     */   private String codigoProceso;
/*     */   private String nombreProceso;
/*     */   private int numero;
/*     */   private int implantadas;
/*     */   private int noImplantadas;
/*     */   private int cumplieron;
/*     */   private int noCumplieron;
/*     */   private String nombreTipo;
/*     */   private String nombreArea;
/*     */   private int areaImplanta;
/*     */   private int porCalificar;
/*     */   int correctivas;
/*     */   int preventivas;
/*     */   int correcciones;
/*     */   private int numeroCausas;
/*     */   private int prorroga0;
/*     */   private int prorroga1;
/*     */   private int prorroga2;
/*     */   private int prorroga3;
/*     */   private int prorroga4;
/*     */   private int satisfactorias;
/*     */   private int insatisfactorias;
/*     */   
/*  56 */   public void setTotal(int p) { this.Total = p; }
/*     */ 
/*     */   
/*  59 */   public int getTotal() { return this.Total; }
/*     */ 
/*     */ 
/*     */   
/*  63 */   public void setProceso(int p) { this.proceso = p; }
/*     */ 
/*     */   
/*  66 */   public int getProceso() { return this.proceso; }
/*     */ 
/*     */ 
/*     */   
/*  70 */   public void setImplantadas(int p) { this.implantadas = p; }
/*     */ 
/*     */   
/*  73 */   public int getImplantadas() { return this.implantadas; }
/*     */ 
/*     */ 
/*     */   
/*  77 */   public void setNoImplantadas(int p) { this.noImplantadas = p; }
/*     */ 
/*     */   
/*  80 */   public int getNoImplantadas() { return this.noImplantadas; }
/*     */ 
/*     */ 
/*     */   
/*  84 */   public void setCumplieron(int p) { this.cumplieron = p; }
/*     */ 
/*     */   
/*  87 */   public int getCumplieron() { return this.cumplieron; }
/*     */ 
/*     */ 
/*     */   
/*  91 */   public void setNoCumplieron(int p) { this.noCumplieron = p; }
/*     */ 
/*     */   
/*  94 */   public int getNoCumplieron() { return this.noCumplieron; }
/*     */ 
/*     */ 
/*     */   
/*  98 */   public void setNombreTipo(String p) { this.nombreTipo = p; }
/*     */ 
/*     */ 
/*     */   
/* 102 */   public String getNombreTipo() { return (this.nombreTipo == null) ? "" : this.nombreTipo; }
/*     */ 
/*     */ 
/*     */   
/* 106 */   public void setAccion(String p) { this.accion = p; }
/*     */ 
/*     */   
/* 109 */   public String getAccion() { return (this.accion == null) ? "" : this.accion; }
/*     */ 
/*     */ 
/*     */   
/* 113 */   public void setNombreArea(String p) { this.nombreArea = p; }
/*     */ 
/*     */   
/* 116 */   public String getNombreArea() { return (this.nombreArea == null) ? "" : this.nombreArea; }
/*     */ 
/*     */ 
/*     */   
/* 120 */   public void setAreaImplanta(int p) { this.areaImplanta = p; }
/*     */ 
/*     */   
/* 123 */   public int getAreaImplanta() { return this.areaImplanta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   public int getCorrectivas() { return this.correctivas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   public void setCorrectivas(int correctivas) { this.correctivas = correctivas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 142 */   public int getPreventivas() { return this.preventivas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public void setPreventivas(int preventivas) { this.preventivas = preventivas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   public int getNumeroCausas() { return this.numeroCausas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 160 */   public void setNumeroCausas(int numeroCausas) { this.numeroCausas = numeroCausas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public int getProrroga0() { return this.prorroga0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 172 */   public void setProrroga0(int prorroga0) { this.prorroga0 = prorroga0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 178 */   public int getProrroga1() { return this.prorroga1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 184 */   public void setProrroga1(int prorroga1) { this.prorroga1 = prorroga1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 190 */   public int getProrroga2() { return this.prorroga2; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 196 */   public void setProrroga2(int prorroga2) { this.prorroga2 = prorroga2; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 202 */   public int getProrroga3() { return this.prorroga3; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 208 */   public void setProrroga3(int prorroga3) { this.prorroga3 = prorroga3; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 214 */   public int getProrroga4() { return this.prorroga4; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 220 */   public void setProrroga4(int prorroga4) { this.prorroga4 = prorroga4; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 226 */   public int getPorCalificar() { return this.porCalificar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 232 */   public void setPorCalificar(int porCalificar) { this.porCalificar = porCalificar; }
/*     */ 
/*     */ 
/*     */   
/* 236 */   public void setNombreProceso(String p) { this.nombreProceso = p; }
/*     */ 
/*     */   
/* 239 */   public String getNombreProceso() { return (this.nombreProceso == null) ? "" : this.nombreProceso; }
/*     */ 
/*     */ 
/*     */   
/* 243 */   public void setNumero(int p) { this.numero = p; }
/*     */ 
/*     */   
/* 246 */   public int getNumero() { return this.numero; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 252 */   public String getCodigoProceso() { return (this.codigoProceso == null) ? "" : this.codigoProceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 258 */   public void setCodigoProceso(String codigoProceso) { this.codigoProceso = codigoProceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 264 */   public int getCorrecciones() { return this.correcciones; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 270 */   public void setCorrecciones(int correcciones) { this.correcciones = correcciones; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 276 */   public int getSatisfactorias() { return this.satisfactorias; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 282 */   public void setSatisfactorias(int satisfactorias) { this.satisfactorias = satisfactorias; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 288 */   public int getInsatisfactorias() { return this.insatisfactorias; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 294 */   public void setInsatisfactorias(int insatisfactorias) { this.insatisfactorias = insatisfactorias; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\AMAccionesEstadisticaDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */