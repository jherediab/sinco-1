/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.PdeAniosDTO;
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
/*     */ public class PdeAniosDTO
/*     */ {
/*     */   private int idAnio;
/*     */   private int idMeta;
/*     */   private int anio;
/*     */   private String programado;
/*     */   private String ejecutado;
/*     */   private String tr1Programado;
/*     */   private String tr1Ejecutado;
/*     */   private String tr2Programado;
/*     */   private String tr2Ejecutado;
/*     */   private String tr3Programado;
/*     */   private String tr3Ejecutado;
/*     */   private String tr4Programado;
/*     */   private String tr4Ejecutado;
/*     */   private String fechaInsercion;
/*     */   private String usuarioInsercion;
/*     */   private String fechaModificacion;
/*     */   private String usuarioModificacion;
/*     */   
/*  50 */   public void setIdAnio(int p) { this.idAnio = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   public int getIdAnio() { return this.idAnio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   public void setIdMeta(int p) { this.idMeta = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   public int getIdMeta() { return this.idMeta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   public void setAnio(int p) { this.anio = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public int getAnio() { return this.anio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   public void setProgramado(String p) { this.programado = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   public String getProgramado() { return (this.programado == null) ? "" : this.programado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 146 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 162 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 177 */   public String getEjecutado() { return (this.ejecutado == null) ? "" : this.ejecutado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 184 */   public void setEjecutado(String ejecutado) { this.ejecutado = ejecutado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 191 */   public String getTr1Programado() { return (this.tr1Programado == null) ? "" : this.tr1Programado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 198 */   public void setTr1Programado(String tr1Programado) { this.tr1Programado = tr1Programado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 205 */   public String getTr1Ejecutado() { return (this.tr1Ejecutado == null) ? "" : this.tr1Ejecutado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 212 */   public void setTr1Ejecutado(String tr1Ejecutado) { this.tr1Ejecutado = tr1Ejecutado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 219 */   public String getTr2Programado() { return (this.tr2Programado == null) ? "" : this.tr2Programado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 226 */   public void setTr2Programado(String tr2Programado) { this.tr2Programado = tr2Programado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 233 */   public String getTr2Ejecutado() { return (this.tr2Ejecutado == null) ? "" : this.tr2Ejecutado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 240 */   public void setTr2Ejecutado(String tr2Ejecutado) { this.tr2Ejecutado = tr2Ejecutado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 247 */   public String getTr3Programado() { return (this.tr3Programado == null) ? "" : this.tr3Programado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 254 */   public void setTr3Programado(String tr3Programado) { this.tr3Programado = tr3Programado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 261 */   public String getTr3Ejecutado() { return (this.tr3Ejecutado == null) ? "" : this.tr3Ejecutado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 268 */   public void setTr3Ejecutado(String tr3Ejecutado) { this.tr3Ejecutado = tr3Ejecutado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 275 */   public String getTr4Programado() { return (this.tr4Programado == null) ? "" : this.tr4Programado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 282 */   public void setTr4Programado(String tr4Programado) { this.tr4Programado = tr4Programado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 289 */   public String getTr4Ejecutado() { return (this.tr4Ejecutado == null) ? "" : this.tr4Ejecutado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 296 */   public void setTr4Ejecutado(String tr4Ejecutado) { this.tr4Ejecutado = tr4Ejecutado; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\PdeAniosDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */