/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.AMCausasDTO;
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
/*     */ public class AMCausasDTO
/*     */ {
/*     */   private int numero;
/*     */   private int consecutivo;
/*     */   private String tipo;
/*     */   private String porque;
/*     */   private String accion;
/*     */   private String beneficios;
/*     */   private String beneficio;
/*     */   private int responsable;
/*     */   private String fechaInicio;
/*     */   private String fechaEstimadaTerminacion;
/*     */   private String fechaRealTerminacion;
/*     */   private int estado;
/*     */   private String tipoCausa;
/*     */   private String nombreEstado;
/*     */   private int prorrogas;
/*     */   private String apellidos;
/*     */   private String nombres;
/*     */   private int seguimientos;
/*     */   
/*  47 */   public void setNumero(int p) { this.numero = p; }
/*     */ 
/*     */   
/*  50 */   public int getNumero() { return this.numero; }
/*     */ 
/*     */ 
/*     */   
/*  54 */   public void setConsecutivo(int p) { this.consecutivo = p; }
/*     */ 
/*     */   
/*  57 */   public int getConsecutivo() { return this.consecutivo; }
/*     */ 
/*     */ 
/*     */   
/*  61 */   public void setTipo(String p) { this.tipo = p; }
/*     */ 
/*     */   
/*  64 */   public String getTipo() { return (this.tipo == null) ? "" : this.tipo; }
/*     */ 
/*     */ 
/*     */   
/*  68 */   public void setPorque(String p) { this.porque = p; }
/*     */ 
/*     */   
/*  71 */   public String getPorque() { return (this.porque == null) ? "" : this.porque; }
/*     */ 
/*     */ 
/*     */   
/*  75 */   public void setAccion(String p) { this.accion = p; }
/*     */ 
/*     */   
/*  78 */   public String getAccion() { return (this.accion == null) ? "" : this.accion; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public void setBeneficios(String p) { this.beneficios = p; }
/*     */ 
/*     */ 
/*     */   
/*  87 */   public String getBeneficios() { return (this.beneficios == null) ? "" : this.beneficios; }
/*     */ 
/*     */   
/*     */   public boolean getBeneficios(int i) {
/*     */     try {
/*  92 */       return (this.beneficios.charAt(i) == 'S');
/*     */     }
/*  94 */     catch (Exception e) {
/*  95 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 100 */   public void setBeneficio(String p) { this.beneficio = p; }
/*     */ 
/*     */   
/* 103 */   public String getBeneficio() { return (this.beneficio == null) ? "" : this.beneficio; }
/*     */ 
/*     */ 
/*     */   
/* 107 */   public void setResponsable(int p) { this.responsable = p; }
/*     */ 
/*     */   
/* 110 */   public int getResponsable() { return this.responsable; }
/*     */ 
/*     */ 
/*     */   
/* 114 */   public void setFechaInicio(String p) { this.fechaInicio = p; }
/*     */ 
/*     */   
/* 117 */   public String getFechaInicio() { return (this.fechaInicio == null) ? "" : this.fechaInicio; }
/*     */ 
/*     */ 
/*     */   
/* 121 */   public void setFechaEstimadaTerminacion(String p) { this.fechaEstimadaTerminacion = p; }
/*     */ 
/*     */   
/* 124 */   public String getFechaEstimadaTerminacion() { return (this.fechaEstimadaTerminacion == null) ? "" : this.fechaEstimadaTerminacion; }
/*     */ 
/*     */ 
/*     */   
/* 128 */   public void setFechaRealTerminacion(String p) { this.fechaRealTerminacion = p; }
/*     */ 
/*     */   
/* 131 */   public String getFechaRealTerminacion() { return (this.fechaRealTerminacion == null) ? "" : this.fechaRealTerminacion; }
/*     */ 
/*     */   
/* 134 */   public void setEstado(int p) { this.estado = p; }
/*     */ 
/*     */   
/* 137 */   public int getEstado() { return this.estado; }
/*     */ 
/*     */ 
/*     */   
/* 141 */   public void setTipoCausa(String p) { this.tipoCausa = p; }
/*     */ 
/*     */   
/* 144 */   public String getTipoCausa() { return (this.tipoCausa == null) ? "" : this.tipoCausa; }
/*     */ 
/*     */ 
/*     */   
/* 148 */   public void setNombreEstado(String p) { this.nombreEstado = p; }
/*     */ 
/*     */   
/* 151 */   public String getNombreEstado() { return (this.nombreEstado == null) ? "" : this.nombreEstado; }
/*     */ 
/*     */ 
/*     */   
/* 155 */   public void setProrrogas(int p) { this.prorrogas = p; }
/*     */ 
/*     */   
/* 158 */   public int getProrrogas() { return this.prorrogas; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 163 */   public void setApellidos(String p) { this.apellidos = p; }
/*     */ 
/*     */   
/* 166 */   public String getApellidos() { return (this.apellidos == null) ? "" : this.apellidos; }
/*     */ 
/*     */ 
/*     */   
/* 170 */   public void setNombres(String p) { this.nombres = p; }
/*     */ 
/*     */   
/* 173 */   public String getNombres() { return (this.nombres == null) ? "" : this.nombres; }
/*     */ 
/*     */ 
/*     */   
/* 177 */   public String getNombreResponsable() { return ((this.apellidos == null) ? "" : this.apellidos) + ", " + ((this.nombres == null) ? "" : this.nombres); }
/*     */ 
/*     */   
/* 180 */   public int getSeguimientos() { return this.seguimientos; }
/*     */ 
/*     */   
/* 183 */   public void setSeguimientos(int seguimientos) { this.seguimientos = seguimientos; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\AMCausasDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */