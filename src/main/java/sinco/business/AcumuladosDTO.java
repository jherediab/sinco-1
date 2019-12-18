/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.AcumuladosDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AcumuladosDTO
/*     */ {
/*     */   private int codigoArea;
/*     */   private int areaSuperior;
/*     */   private int anno;
/*     */   private int mes;
/*     */   private int solicitudesRecibidas;
/*     */   private int solicitudesAtendidas;
/*     */   private int solicitudesNoAtendidas;
/*     */   private int solicitudesEscaladas;
/*     */   
/*  24 */   public void setCodigoArea(int p) { this.codigoArea = p; }
/*     */   private int oportunidadExcelente; private int oportunidadBuena; private int oportunidadRegular; private int confiabilidadExcelente; private int confiabilidadBuena; private int confiabilidadRegular; private float indiceOportunidad; private float indiceConfiabilidad;
/*     */   private String descripcion;
/*     */   
/*  28 */   public void setAreaSuperior(int p) { this.areaSuperior = p; }
/*     */ 
/*     */ 
/*     */   
/*  32 */   public void setSolicitudesRecibidas(int p) { this.solicitudesRecibidas = p; }
/*     */ 
/*     */ 
/*     */   
/*  36 */   public void setSolicitudesAtendidas(int p) { this.solicitudesAtendidas = p; }
/*     */ 
/*     */ 
/*     */   
/*  40 */   public void setSolicitudesNoAtendidas(int p) { this.solicitudesNoAtendidas = p; }
/*     */ 
/*     */ 
/*     */   
/*  44 */   public void setSolicitudesEscaladas(int p) { this.solicitudesEscaladas = p; }
/*     */ 
/*     */ 
/*     */   
/*  48 */   public void setOportunidadExcelente(int p) { this.oportunidadExcelente = p; }
/*     */ 
/*     */ 
/*     */   
/*  52 */   public void setOportunidadBuena(int p) { this.oportunidadBuena = p; }
/*     */ 
/*     */ 
/*     */   
/*  56 */   public void setOportunidadRegular(int p) { this.oportunidadRegular = p; }
/*     */ 
/*     */ 
/*     */   
/*  60 */   public void setConfiabilidadExcelente(int p) { this.confiabilidadExcelente = p; }
/*     */ 
/*     */ 
/*     */   
/*  64 */   public void setConfiabilidadBuena(int p) { this.confiabilidadBuena = p; }
/*     */ 
/*     */ 
/*     */   
/*  68 */   public void setConfiabilidadRegular(int p) { this.confiabilidadRegular = p; }
/*     */ 
/*     */ 
/*     */   
/*  72 */   public void setDescripcion(String p) { this.descripcion = p; }
/*     */ 
/*     */ 
/*     */   
/*  76 */   public void setIndiceOportunidad(float p) { this.indiceOportunidad = p; }
/*     */ 
/*     */ 
/*     */   
/*  80 */   public void setIndiceConfiabilidad(float p) { this.indiceConfiabilidad = p; }
/*     */ 
/*     */ 
/*     */   
/*  84 */   public int getCodigoArea() { return this.codigoArea; }
/*     */ 
/*     */ 
/*     */   
/*  88 */   public int getAreaSuperior() { return this.areaSuperior; }
/*     */ 
/*     */ 
/*     */   
/*  92 */   public int getSolicitudesRecibidas() { return this.solicitudesRecibidas; }
/*     */ 
/*     */ 
/*     */   
/*  96 */   public int getSolicitudesAtendidas() { return this.solicitudesAtendidas; }
/*     */ 
/*     */ 
/*     */   
/* 100 */   public int getSolicitudesNoAtendidas() { return this.solicitudesNoAtendidas; }
/*     */ 
/*     */ 
/*     */   
/* 104 */   public int getSolicitudesEscaladas() { return this.solicitudesEscaladas; }
/*     */ 
/*     */ 
/*     */   
/* 108 */   public int getOportunidadExcelente() { return this.oportunidadExcelente; }
/*     */ 
/*     */ 
/*     */   
/* 112 */   public int getOportunidadBuena() { return this.oportunidadBuena; }
/*     */ 
/*     */ 
/*     */   
/* 116 */   public int getOportunidadRegular() { return this.oportunidadRegular; }
/*     */ 
/*     */ 
/*     */   
/* 120 */   public int getConfiabilidadExcelente() { return this.oportunidadRegular; }
/*     */ 
/*     */ 
/*     */   
/* 124 */   public int getConfiabilidadBuena() { return this.confiabilidadBuena; }
/*     */ 
/*     */ 
/*     */   
/* 128 */   public int getConfiabilidadRegular() { return this.confiabilidadRegular; }
/*     */ 
/*     */   
/*     */   public float getIndiceOportunidad() {
/* 132 */     int total = this.oportunidadExcelente + this.oportunidadBuena + this.oportunidadRegular;
/* 133 */     this.indiceOportunidad = 0.0F;
/* 134 */     if (total != 0) {
/* 135 */       this.indiceOportunidad = 100.0F * (this.oportunidadExcelente * ParametrosDTO.getFloat("porcentajeE") + this.oportunidadBuena * ParametrosDTO.getFloat("porcentajeB") + this.oportunidadRegular * ParametrosDTO.getFloat("porcentajeR")) / total;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 142 */     return this.indiceOportunidad;
/*     */   }
/*     */   
/*     */   public float getIndiceConfiabilidad() {
/* 146 */     int total = this.confiabilidadExcelente + this.confiabilidadBuena + this.confiabilidadRegular;
/* 147 */     this.indiceConfiabilidad = 0.0F;
/* 148 */     if (total != 0) {
/* 149 */       this.indiceConfiabilidad = 100.0F * (this.confiabilidadExcelente * ParametrosDTO.getFloat("porcentajeE") + this.confiabilidadBuena * ParametrosDTO.getFloat("porcentajeB") + this.confiabilidadRegular * ParametrosDTO.getFloat("porcentajeR")) / total;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 155 */     return this.indiceConfiabilidad;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getIndiceTotal() {
/* 163 */     int total = this.oportunidadExcelente + this.oportunidadBuena + this.oportunidadRegular;
/* 164 */     this.indiceOportunidad = 0.0F;
/* 165 */     if (total != 0) {
/* 166 */       this.indiceOportunidad = (this.oportunidadExcelente * ParametrosDTO.getFloat("porcentajeE") + this.oportunidadBuena * ParametrosDTO.getFloat("porcentajeB") + this.oportunidadRegular * ParametrosDTO.getFloat("porcentajeR")) / total;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 172 */     total = this.confiabilidadExcelente + this.confiabilidadBuena + this.confiabilidadRegular;
/* 173 */     this.indiceConfiabilidad = 0.0F;
/* 174 */     if (total != 0) {
/* 175 */       this.indiceConfiabilidad = (this.confiabilidadExcelente * ParametrosDTO.getFloat("porcentajeE") + this.confiabilidadBuena * ParametrosDTO.getFloat("porcentajeB") + this.confiabilidadRegular * ParametrosDTO.getFloat("porcentajeR")) / total;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 181 */     if (this.indiceOportunidad == 0.0F || this.indiceConfiabilidad == 0.0F) return (this.indiceOportunidad + this.indiceConfiabilidad) * 100.0F; 
/* 182 */     return this.indiceOportunidad * ParametrosDTO.getInt("porcentaje.oportunidad") + this.indiceConfiabilidad * ParametrosDTO.getInt("porcentaje.confiabilidad");
/*     */   }
/*     */ 
/*     */   
/* 186 */   public String getDescripcion() { return this.descripcion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 193 */   public int getAnno() { return this.anno; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 200 */   public void setAnno(int anno) { this.anno = anno; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 207 */   public int getMes() { return this.mes; }
/*     */ 
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
/*     */   
/* 221 */   public String getNombreMes() { return Utilidades.nombreMes(this.mes); }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\AcumuladosDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */