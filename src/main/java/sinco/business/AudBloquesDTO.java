/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.AudBloquesDTO;
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
/*     */ public class AudBloquesDTO
/*     */ {
/*     */   private int ciclo;
/*     */   private int bloque;
/*     */   private String codigoProceso;
/*     */   private int codigoInforme;
/*     */   private String nombreProceso;
/*     */   private String asociadoA;
/*     */   private String fechaInsercion;
/*     */   private String usuarioInsercion;
/*     */   private String fechaModificacion;
/*     */   private String usuarioModificacion;
/*     */   private int pregunta;
/*     */   private String descripcionPregunta;
/*     */   private String indConformidad;
/*     */   private String respuesta;
/*     */   private String anotaciones;
/*     */   
/*  42 */   public void setCiclo(int p) { this.ciclo = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   public int getCiclo() { return this.ciclo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   public void setBloque(int p) { this.bloque = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   public int getBloque() { return this.bloque; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public void setAsociadoA(String p) { this.asociadoA = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public String getAsociadoA() { return (this.asociadoA == null) ? "" : this.asociadoA; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNombreAsociadoA() {
/*  92 */     if (this.asociadoA == null) {
/*  93 */       return "";
/*     */     }
/*  95 */     return this.asociadoA.equals("P") ? "Proceso" : "informe";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 160 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */   
/* 164 */   public int getPregunta() { return this.pregunta; }
/*     */ 
/*     */ 
/*     */   
/* 168 */   public void setPregunta(int pregunta) { this.pregunta = pregunta; }
/*     */ 
/*     */ 
/*     */   
/* 172 */   public String getDescripcionPregunta() { return (this.descripcionPregunta == null) ? "" : this.descripcionPregunta; }
/*     */ 
/*     */ 
/*     */   
/* 176 */   public void setDescripcionPregunta(String descripcionPregunta) { this.descripcionPregunta = descripcionPregunta; }
/*     */ 
/*     */ 
/*     */   
/* 180 */   public String getIndConformidad() { return (this.indConformidad == null) ? "" : this.indConformidad; }
/*     */ 
/*     */ 
/*     */   
/* 184 */   public String getRespuesta() { return (this.respuesta == null) ? "" : this.respuesta; }
/*     */ 
/*     */ 
/*     */   
/* 188 */   public String getAnotaciones() { return (this.anotaciones == null) ? "" : this.anotaciones; }
/*     */ 
/*     */ 
/*     */   
/* 192 */   public void setIndConformidad(String indConformidad) { this.indConformidad = indConformidad; }
/*     */ 
/*     */ 
/*     */   
/* 196 */   public void setRespuesta(String respuesta) { this.respuesta = respuesta; }
/*     */ 
/*     */ 
/*     */   
/* 200 */   public void setAnotaciones(String anotaciones) { this.anotaciones = anotaciones; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 205 */   public String getNombreProceso() { return (this.nombreProceso == null) ? "" : this.nombreProceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 210 */   public void setNombreProceso(String nombreProceso) { this.nombreProceso = nombreProceso; }
/*     */ 
/*     */ 
/*     */   
/* 214 */   public int getCodigoInforme() { return this.codigoInforme; }
/*     */ 
/*     */ 
/*     */   
/* 218 */   public void setCodigoInforme(int codigoInforme) { this.codigoInforme = codigoInforme; }
/*     */ 
/*     */   
/*     */   public String getCodigoPadre() {
/* 222 */     if (getAsociadoA().equals("P")) {
/* 223 */       return getCodigoProceso();
/*     */     }
/* 225 */     return Integer.toString(getCodigoInforme());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 233 */   public String getCodigoProceso() { return (this.codigoProceso == null) ? "" : this.codigoProceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 240 */   public void setCodigoProceso(String codigoProceso) { this.codigoProceso = codigoProceso; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\AudBloquesDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */