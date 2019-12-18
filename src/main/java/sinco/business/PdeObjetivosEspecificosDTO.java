/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.PdeObjetivosEspecificosDTO;
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
/*     */ public class PdeObjetivosEspecificosDTO
/*     */ {
/*     */   private int idObjetivoEspecifico;
/*     */   private String codigoObjetivoEspecifico;
/*     */   private int idUnidadNivel;
/*     */   private String descripcionObjetivoEspecifico;
/*     */   private String fechaInsercion;
/*     */   private String usuarioInsercion;
/*     */   private String fechaModificacion;
/*     */   private String usuarioModificacion;
/*     */   
/*  32 */   public void setIdObjetivoEspecifico(int p) { this.idObjetivoEspecifico = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   public int getIdObjetivoEspecifico() { return this.idObjetivoEspecifico; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   public void setCodigoObjetivoEspecifico(String p) { this.codigoObjetivoEspecifico = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   public String getCodigoObjetivoEspecifico() { return (this.codigoObjetivoEspecifico == null) ? "" : this.codigoObjetivoEspecifico; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   public void setIdUnidadNivel(int p) { this.idUnidadNivel = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public int getIdUnidadNivel() { return this.idUnidadNivel; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   public void setDescripcionObjetivoEspecifico(String p) { this.descripcionObjetivoEspecifico = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   public String getDescripcionObjetivoEspecifico() { return (this.descripcionObjetivoEspecifico == null) ? "" : this.descripcionObjetivoEspecifico; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 128 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\PdeObjetivosEspecificosDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */