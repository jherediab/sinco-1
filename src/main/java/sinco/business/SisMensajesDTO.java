/*     */ package sinco.business;
/*     */ 
/*     */ import java.text.MessageFormat;
/*     */ import sinco.business.SisMensajesDTO;
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
/*     */ public class SisMensajesDTO
/*     */ {
/*     */   private String codigo;
/*     */   private String descripcion;
/*     */   private String fechaInsercion;
/*     */   private String usuarioInsercion;
/*     */   private String fechaModificacion;
/*     */   private String usuarioModificacion;
/*     */   
/*  30 */   public void setCodigo(String p) { this.codigo = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   public String getCodigo() { return (this.codigo == null) ? "" : this.codigo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   public void setDescripcion(String p) { this.descripcion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public String getDescripcion() { return (this.descripcion == null) ? "" : this.descripcion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String format(String p1) {
/* 126 */     MessageFormat form1 = new MessageFormat(this.descripcion);
/* 127 */     Object[] testArgs2 = { p1 };
/* 128 */     return form1.format(testArgs2);
/*     */   }
/*     */   
/*     */   public String format(String p1, String p2) {
/* 132 */     MessageFormat form1 = new MessageFormat(this.descripcion);
/* 133 */     Object[] testArgs2 = { p1, p2 };
/* 134 */     return form1.format(testArgs2);
/*     */   }
/*     */   
/*     */   public String format(String p1, String p2, String p3) {
/* 138 */     MessageFormat form1 = new MessageFormat(this.descripcion);
/* 139 */     Object[] testArgs2 = { p1, p2, p3 };
/* 140 */     return form1.format(testArgs2);
/*     */   }
/*     */   
/*     */   public String format(String p1, String p2, String p3, String p4) {
/* 144 */     MessageFormat form1 = new MessageFormat(this.descripcion);
/* 145 */     Object[] testArgs2 = { p1, p2, p3, p4 };
/* 146 */     return form1.format(testArgs2);
/*     */   }
/*     */   
/*     */   public String format(String p1, String p2, String p3, String p4, String p5) {
/* 150 */     MessageFormat form1 = new MessageFormat(this.descripcion);
/* 151 */     Object[] testArgs2 = { p1, p2, p3, p4, p5 };
/* 152 */     return form1.format(testArgs2);
/*     */   }
/*     */   
/*     */   public String format(String p1, String p2, String p3, String p4, String p5, String p6) {
/* 156 */     MessageFormat form1 = new MessageFormat(this.descripcion);
/* 157 */     Object[] testArgs2 = { p1, p2, p3, p4, p5, p6 };
/* 158 */     return form1.format(testArgs2);
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\SisMensajesDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */