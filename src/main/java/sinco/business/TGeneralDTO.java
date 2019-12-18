/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.TGeneralDTO;
/*     */ 
/*     */ 
/*     */ public class TGeneralDTO
/*     */ {
/*     */   private String codigo;
/*     */   private String descripcion;
/*     */   private String incest;
/*     */   private String inccod;
/*     */   private String idActivo;
/*     */   private String inclop;
/*     */   private String incres;
/*     */   private String incdes;
/*     */   private String capturaNumeral;
/*     */   
/*  18 */   public void setCodigo(String c) { this.codigo = c; }
/*     */ 
/*     */ 
/*     */   
/*  22 */   public void setDescripcion(String n) { this.descripcion = n; }
/*     */ 
/*     */ 
/*     */   
/*  26 */   public String getCodigo() { return this.codigo; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  31 */   public String getCodigoS() { return this.codigo; }
/*     */ 
/*     */ 
/*     */   
/*  35 */   public int getCodigoInt() { return Integer.parseInt(this.codigo); }
/*     */ 
/*     */ 
/*     */   
/*  39 */   public String getDescripcion() { return this.descripcion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   public String getIncest() { return (this.incest == null) ? "" : this.incest; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   public void setIncest(String incest) { this.incest = incest; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   public String getInccod() { return (this.inccod == null) ? "" : this.inccod; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   public void setInccod(String inccod) { this.inccod = inccod; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   public String getIdActivo() { return (this.idActivo == null) ? "" : this.idActivo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public void setIdActivo(String idActivo) { this.idActivo = idActivo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   public String getInclop() { return (this.inclop == null) ? "" : this.inclop; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   public void setInclop(String inclop) { this.inclop = inclop; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public String getIncres() { return (this.incres == null) ? "" : this.incres; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   public void setIncres(String incres) { this.incres = incres; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   public String getIncdes() { return (this.incdes == null) ? "" : this.incdes; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public void setIncdes(String p) { this.incdes = p; }
/*     */ 
/*     */ 
/*     */   
/* 128 */   public String getCapturaNumeral() { return (this.capturaNumeral == null) ? "" : this.capturaNumeral; }
/*     */ 
/*     */ 
/*     */   
/* 132 */   public void setCapturaNumeral(String capturaNumeral) { this.capturaNumeral = capturaNumeral; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\TGeneralDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */