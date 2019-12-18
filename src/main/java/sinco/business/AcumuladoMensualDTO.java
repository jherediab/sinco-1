/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.AcumuladoMensualDTO;
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
/*     */ public class AcumuladoMensualDTO
/*     */ {
/*     */   private int anno;
/*     */   private int mes;
/*     */   private int codigoarea;
/*     */   private int codigoservicio;
/*     */   private int tiempoestablecido;
/*     */   private int tiempopromedio;
/*     */   private int escnivel1;
/*     */   private int escnivel2;
/*     */   private int escnivel3;
/*     */   private int escnivel4;
/*     */   
/*  27 */   public void setAnno(int anno) { this.anno = anno; }
/*     */   private int escnivel5; private int escnivel6; private int cantidadclientes; private int serviciossolicitados; private int serviciostiempo; private int serviciosfueratiempo; private int serviciosnoprestados; private int confiabilidadexcelentes; private int confiabilidadbuenas; private int confiabilidadregulares;
/*     */   private String indicesatisfaccion;
/*     */   
/*  31 */   public int getAnno() { return this.anno; }
/*     */ 
/*     */ 
/*     */   
/*  35 */   public void setMes(int mes) { this.mes = mes; }
/*     */ 
/*     */ 
/*     */   
/*  39 */   public int getMes() { return this.mes; }
/*     */ 
/*     */ 
/*     */   
/*  43 */   public void setCodigoArea(int codigoarea) { this.codigoarea = codigoarea; }
/*     */ 
/*     */ 
/*     */   
/*  47 */   public int getCodigoArea() { return this.codigoarea; }
/*     */ 
/*     */ 
/*     */   
/*  51 */   public void setCodigoServicio(int codigoservicio) { this.codigoservicio = codigoservicio; }
/*     */ 
/*     */ 
/*     */   
/*  55 */   public int getCodigoServicio() { return this.codigoservicio; }
/*     */ 
/*     */ 
/*     */   
/*  59 */   public void setTiempoEstablecido(int tiempoestablecido) { this.tiempoestablecido = tiempoestablecido; }
/*     */ 
/*     */ 
/*     */   
/*  63 */   public int getTiempoEstablecido() { return this.tiempoestablecido; }
/*     */ 
/*     */ 
/*     */   
/*  67 */   public void setTiempoPromedio(int tiempopromedio) { this.tiempopromedio = tiempopromedio; }
/*     */ 
/*     */ 
/*     */   
/*  71 */   public int getTiempoPromedio() { return this.tiempopromedio; }
/*     */ 
/*     */ 
/*     */   
/*  75 */   public void setEscNivel1(int escnivel1) { this.escnivel1 = escnivel1; }
/*     */ 
/*     */ 
/*     */   
/*  79 */   public int getEscNivel1() { return this.escnivel1; }
/*     */ 
/*     */ 
/*     */   
/*  83 */   public void setEscNivel2(int escnivel2) { this.escnivel2 = escnivel2; }
/*     */ 
/*     */ 
/*     */   
/*  87 */   public int getEscNivel2() { return this.escnivel2; }
/*     */ 
/*     */ 
/*     */   
/*  91 */   public void setEscNivel3(int escnivel3) { this.escnivel3 = escnivel3; }
/*     */ 
/*     */ 
/*     */   
/*  95 */   public int getEscNivel3() { return this.escnivel3; }
/*     */ 
/*     */ 
/*     */   
/*  99 */   public void setEscNivel4(int escnivel4) { this.escnivel4 = escnivel4; }
/*     */ 
/*     */ 
/*     */   
/* 103 */   public int getEscNivel4() { return this.escnivel4; }
/*     */ 
/*     */ 
/*     */   
/* 107 */   public void setEscNivel5(int escnivel5) { this.escnivel5 = escnivel5; }
/*     */ 
/*     */ 
/*     */   
/* 111 */   public int getEscNivel5() { return this.escnivel5; }
/*     */ 
/*     */ 
/*     */   
/* 115 */   public void setEscNivel6(int escnivel6) { this.escnivel6 = escnivel6; }
/*     */ 
/*     */ 
/*     */   
/* 119 */   public int getEscNivel6() { return this.escnivel6; }
/*     */ 
/*     */ 
/*     */   
/* 123 */   public void setCantidadClientes(int cantidadclientes) { this.cantidadclientes = cantidadclientes; }
/*     */ 
/*     */ 
/*     */   
/* 127 */   public int getCantidadClientes() { return this.cantidadclientes; }
/*     */ 
/*     */ 
/*     */   
/* 131 */   public void setServiciosSolicitados(int serviciossolicitados) { this.serviciossolicitados = serviciossolicitados; }
/*     */ 
/*     */ 
/*     */   
/* 135 */   public int getServiciosSolicitados() { return this.serviciossolicitados; }
/*     */ 
/*     */ 
/*     */   
/* 139 */   public void setServiciosTiempo(int serviciostiempo) { this.serviciostiempo = serviciostiempo; }
/*     */ 
/*     */ 
/*     */   
/* 143 */   public int getServiciosTiempo() { return this.serviciostiempo; }
/*     */ 
/*     */ 
/*     */   
/* 147 */   public void setServiciosFueraTiempo(int serviciosfueratiempo) { this.serviciosfueratiempo = serviciosfueratiempo; }
/*     */ 
/*     */ 
/*     */   
/* 151 */   public int getServiciosFueraTiempo() { return this.serviciosfueratiempo; }
/*     */ 
/*     */ 
/*     */   
/* 155 */   public void setServiciosNoPrestados(int serviciosnoprestados) { this.serviciosnoprestados = serviciosnoprestados; }
/*     */ 
/*     */ 
/*     */   
/* 159 */   public int getServiciosNoPrestados() { return this.serviciosnoprestados; }
/*     */ 
/*     */ 
/*     */   
/* 163 */   public void setConfiabilidadExcelentes(int confiabilidadexcelentes) { this.confiabilidadexcelentes = confiabilidadexcelentes; }
/*     */ 
/*     */ 
/*     */   
/* 167 */   public int getConfiabilidadExcelentes() { return this.confiabilidadexcelentes; }
/*     */ 
/*     */ 
/*     */   
/* 171 */   public void setConfiabilidadBuenas(int confiabilidadbuenas) { this.confiabilidadbuenas = confiabilidadbuenas; }
/*     */ 
/*     */ 
/*     */   
/* 175 */   public int getConfiabilidadBuenas() { return this.confiabilidadbuenas; }
/*     */ 
/*     */ 
/*     */   
/* 179 */   public void setConfiabilidadRegulares(int confiabilidadregulares) { this.confiabilidadregulares = confiabilidadregulares; }
/*     */ 
/*     */ 
/*     */   
/* 183 */   public int getConfiabilidadRegulares() { return this.confiabilidadregulares; }
/*     */ 
/*     */ 
/*     */   
/* 187 */   public void setIndiceSatisfaccion(String indicesatisfaccion) { this.indicesatisfaccion = indicesatisfaccion; }
/*     */ 
/*     */ 
/*     */   
/* 191 */   public String getIndiceSatisfaccion() { return this.indicesatisfaccion; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\AcumuladoMensualDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */