/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.IndiceSatisfaccionDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.Utilidades;
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
/*     */ public class IndiceSatisfaccionDTO
/*     */ {
/*     */   private String nombre;
/*     */   private int codigo;
/*     */   private int codigoArea;
/*     */   private int anno;
/*     */   private int mes;
/*     */   private String nombreArea;
/*     */   private int oportunidadExcelente;
/*     */   private int oportunidadBuena;
/*     */   private int oportunidadRegular;
/*     */   private int confiabilidadExcelente;
/*     */   private int confiabilidadBuena;
/*     */   private int confiabilidadRegular;
/*     */   private int recibidas;
/*     */   private int atendidas;
/*     */   private int porAtender;
/*     */   private int escaladas;
/*     */   private double tiempoBase;
/*     */   private int numeroBase;
/*     */   private double tiempoMovil;
/*     */   private int numeroMovil;
/*     */   private int codigoServicio;
/*     */   private String nombreServicio;
/*     */   private int annoBase;
/*     */   private int mesBase1;
/*     */   private int mesBase2;
/*     */   
/*  54 */   public void setNombre(String p) { this.nombre = p; }
/*     */ 
/*     */   
/*  57 */   public String getNombre() { return (this.nombre == null) ? "" : this.nombre; }
/*     */ 
/*     */ 
/*     */   
/*  61 */   public void setCodigo(int p) { this.codigo = p; }
/*     */ 
/*     */   
/*  64 */   public int getCodigo() { return this.codigo; }
/*     */ 
/*     */ 
/*     */   
/*  68 */   public void setOportunidadExcelente(int p) { this.oportunidadExcelente = p; }
/*     */ 
/*     */   
/*  71 */   public int getOportunidadExcelente() { return this.oportunidadExcelente; }
/*     */ 
/*     */ 
/*     */   
/*  75 */   public void setOportunidadBuena(int p) { this.oportunidadBuena = p; }
/*     */ 
/*     */   
/*  78 */   public int getOportunidadBuena() { return this.oportunidadBuena; }
/*     */ 
/*     */ 
/*     */   
/*  82 */   public void setOportunidadRegular(int p) { this.oportunidadRegular = p; }
/*     */ 
/*     */   
/*  85 */   public int getOportunidadRegular() { return this.oportunidadRegular; }
/*     */ 
/*     */ 
/*     */   
/*  89 */   public void setConfiabilidadExcelente(int p) { this.confiabilidadExcelente = p; }
/*     */ 
/*     */   
/*  92 */   public int getConfiabilidadExcelente() { return this.confiabilidadExcelente; }
/*     */ 
/*     */ 
/*     */   
/*  96 */   public void setConfiabilidadBuena(int p) { this.confiabilidadBuena = p; }
/*     */ 
/*     */   
/*  99 */   public int getConfiabilidadBuena() { return this.confiabilidadBuena; }
/*     */ 
/*     */ 
/*     */   
/* 103 */   public void setConfiabilidadRegular(int p) { this.confiabilidadRegular = p; }
/*     */ 
/*     */   
/* 106 */   public int getConfiabilidadRegular() { return this.confiabilidadRegular; }
/*     */ 
/*     */ 
/*     */   
/* 110 */   public float getIndiceOportunidad() { return Utilidades.calcularIndice(this.oportunidadExcelente, this.oportunidadBuena, this.oportunidadRegular); }
/*     */ 
/*     */ 
/*     */   
/* 114 */   public float getIndiceConfiabilidad() { return Utilidades.calcularIndice(this.confiabilidadExcelente, this.confiabilidadBuena, this.confiabilidadRegular); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getIndiceTotal() {
/* 122 */     float indiceOportunidad = Utilidades.calcularIndice(this.oportunidadExcelente, this.oportunidadBuena, this.oportunidadRegular);
/* 123 */     float indiceConfiabilidad = Utilidades.calcularIndice(this.confiabilidadExcelente, this.confiabilidadBuena, this.confiabilidadRegular);
/*     */     
/* 125 */     double indiceTotal = 0.0D;
/* 126 */     if (indiceOportunidad == 0.0F || indiceConfiabilidad == 0.0F) {
/* 127 */       indiceTotal = (indiceOportunidad + indiceConfiabilidad);
/*     */     } else {
/* 129 */       indiceTotal = ((indiceOportunidad * ParametrosDTO.getInt("porcentaje.oportunidad") + indiceConfiabilidad * ParametrosDTO.getInt("porcentaje.confiabilidad")) / 100.0F);
/* 130 */     }  return indiceTotal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   public int getRecibidas() { return this.recibidas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 142 */   public void setRecibidas(int recibidas) { this.recibidas = recibidas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public int getAtendidas() { return this.atendidas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   public void setAtendidas(int atendidas) { this.atendidas = atendidas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 160 */   public int getPorAtender() { return this.porAtender; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public void setPorAtender(int porAtender) { this.porAtender = porAtender; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 172 */   public int getEscaladas() { return this.escaladas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 178 */   public void setEscaladas(int escaladas) { this.escaladas = escaladas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 184 */   public int getCodigoArea() { return this.codigoArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 190 */   public void setCodigoArea(int codigoArea) { this.codigoArea = codigoArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 196 */   public int getAnno() { return this.anno; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 202 */   public void setAnno(int anno) { this.anno = anno; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 208 */   public int getMes() { return this.mes; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 214 */   public void setMes(int mes) { this.mes = mes; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 220 */   public String getNombreArea() { return (this.nombreArea == null) ? "" : this.nombreArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 226 */   public void setNombreArea(String descripcion) { this.nombreArea = descripcion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 233 */   public String getNombreMes() { return Utilidades.nombreMes(this.mes); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 239 */   public double getTiempoBase() { return this.tiempoBase; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 245 */   public void setTiempoBase(double tiempoBase) { this.tiempoBase = tiempoBase; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 251 */   public int getNumeroBase() { return this.numeroBase; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 257 */   public void setNumeroBase(int numeroBase) { this.numeroBase = numeroBase; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 263 */   public double getTiempoMovil() { return this.tiempoMovil; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 269 */   public void setTiempoMovil(double tiempoMovil) { this.tiempoMovil = tiempoMovil; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 275 */   public int getNumeroMovil() { return this.numeroMovil; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 281 */   public void setNumeroMovil(int numeroMovil) { this.numeroMovil = numeroMovil; }
/*     */ 
/*     */   
/*     */   public double getLineaBase(double tiempo, int servicios) {
/* 285 */     if (servicios > 0) {
/* 286 */       return tiempo / servicios;
/*     */     }
/* 288 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public double getLineaMovil(double tiempo, int servicios) {
/* 292 */     if (servicios > 0) {
/* 293 */       return tiempo / servicios;
/*     */     }
/* 295 */     return 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getProntitud(double base, double movil) {
/* 306 */     if (movil == 0.0D) {
/* 307 */       return 0.0D;
/*     */     }
/* 309 */     if (base != 0.0D) {
/* 310 */       return 100.0D * (base - movil) / base;
/*     */     }
/* 312 */     return movil;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 318 */   public int getCodigoServicio() { return this.codigoServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 324 */   public void setCodigoServicio(int codigoServicio) { this.codigoServicio = codigoServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 330 */   public String getNombreServicio() { return (this.nombreServicio == null) ? "" : this.nombreServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 336 */   public void setNombreServicio(String nombreServicio) { this.nombreServicio = nombreServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 342 */   public int getAnnoBase() { return this.annoBase; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 348 */   public void setAnnoBase(int annoBase) { this.annoBase = annoBase; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 354 */   public int getMesBase1() { return this.mesBase1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 360 */   public void setMesBase1(int mesBase1) { this.mesBase1 = mesBase1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 366 */   public int getMesBase2() { return this.mesBase2; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 372 */   public void setMesBase2(int mesBase2) { this.mesBase2 = mesBase2; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\IndiceSatisfaccionDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */