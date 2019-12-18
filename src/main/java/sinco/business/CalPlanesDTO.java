/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.CalPlanesDTO;
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
/*     */ public class CalPlanesDTO
/*     */ {
/*     */   private int ciclo;
/*     */   private int codigoPlan;
/*     */   private int codigoArea;
/*     */   private String estado;
/*     */   private String fechaInsercion;
/*     */   private String usuarioInsercion;
/*     */   private String fechaModificacion;
/*     */   private String usuarioModificacion;
/*     */   private String nombreArea;
/*     */   private String nombres;
/*     */   private String apellidos;
/*     */   private String idcorreo;
/*     */   private String estadoPersona;
/*     */   private String dominio;
/*     */   
/*  43 */   public void setCiclo(int p) { this.ciclo = p; }
/*     */ 
/*     */   
/*  46 */   public int getCiclo() { return this.ciclo; }
/*     */ 
/*     */ 
/*     */   
/*  50 */   public void setCodigoPlan(int p) { this.codigoPlan = p; }
/*     */ 
/*     */   
/*  53 */   public int getCodigoPlan() { return this.codigoPlan; }
/*     */ 
/*     */ 
/*     */   
/*  57 */   public void setCodigoArea(int p) { this.codigoArea = p; }
/*     */ 
/*     */   
/*  60 */   public int getCodigoArea() { return this.codigoArea; }
/*     */ 
/*     */ 
/*     */   
/*  64 */   public void setEstado(String p) { this.estado = p; }
/*     */ 
/*     */   
/*  67 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*     */ 
/*     */ 
/*     */   
/*  71 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */   
/*  74 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */   
/*  78 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */   
/*  81 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */   
/*  85 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */   
/*  88 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */   
/*  92 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */   
/*  95 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */   
/*  99 */   public void setNombreArea(String p) { this.nombreArea = p; }
/*     */ 
/*     */   
/* 102 */   public String getNombreArea() { return (this.nombreArea == null) ? "" : this.nombreArea; }
/*     */ 
/*     */ 
/*     */   
/* 106 */   public void setNombres(String p) { this.nombres = p; }
/*     */ 
/*     */   
/* 109 */   public String getNombres() { return (this.nombres == null) ? "" : this.nombres; }
/*     */ 
/*     */ 
/*     */   
/* 113 */   public void setApellidos(String p) { this.apellidos = p; }
/*     */ 
/*     */   
/* 116 */   public String getApellidos() { return (this.apellidos == null) ? "" : this.apellidos; }
/*     */ 
/*     */ 
/*     */   
/* 120 */   public void setEmail(String p) { this.idcorreo = p; }
/*     */ 
/*     */   
/* 123 */   public String getEmail() { return (this.idcorreo == null) ? "" : this.idcorreo; }
/*     */ 
/*     */ 
/*     */   
/* 127 */   public void setEstadoPersona(String p) { this.estadoPersona = p; }
/*     */ 
/*     */   
/* 130 */   public String getEstadoPersona() { return (this.estadoPersona == null) ? "" : this.estadoPersona; }
/*     */ 
/*     */ 
/*     */   
/* 134 */   public void setDominio(String p) { this.dominio = p; }
/*     */   
/*     */   public String getDominio(String miDominio) {
/* 137 */     if (this.dominio == null) {
/* 138 */       return miDominio;
/*     */     }
/* 140 */     if (this.dominio.trim().length() == 0) {
/* 141 */       return miDominio;
/*     */     }
/* 143 */     return this.dominio;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\CalPlanesDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */