/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.ContActaInicioDTO;
/*     */ 
/*     */ 

/*     */ 
/*     */ public class ContActaInicioDTO
/*     */ {
/*     */   private int consecutivoContrato;
/*     */   private String numeroContrato;
/*     */   private String fechaActa;
/*     */   private String anexoPasadojud;
/*     */   private String anexoRut;
/*     */   private String anexoProcuraduria;
/*     */   private String anexoContraloria;
/*     */   private String anexoContrato;
/*     */   private String anexoPolizas;
/*     */   private String anexoImpuestos;
/*     */   private String anexoCedula;
/*     */   private String anexoRegHab;
/*     */   private String usuarioInsercion;
/*     */   private String fechaInsercion;
/*     */   private String usuarioModificacion;
/*     */   private String fechaModificacion;
/*     */   
/*  48 */   public void setConsecutivoContrato(int p) { this.consecutivoContrato = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   public int getConsecutivoContrato() { return this.consecutivoContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   public void setNumeroContrato(String numeroContrato) { this.numeroContrato = numeroContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   public String getNumeroContrato() { return this.numeroContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   public void setFechaActa(String p) { this.fechaActa = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public String getFechaActa() { return (this.fechaActa == null) ? "" : this.fechaActa; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   public void setAnexoPasadojud(String p) { this.anexoPasadojud = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public String getAnexoPasadojud() { return (this.anexoPasadojud == null) ? "" : this.anexoPasadojud; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   public void setAnexoRut(String p) { this.anexoRut = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   public String getAnexoRut() { return (this.anexoRut == null) ? "" : this.anexoRut; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 126 */   public void setAnexoProcuraduria(String p) { this.anexoProcuraduria = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 134 */   public String getAnexoProcuraduria() { return (this.anexoProcuraduria == null) ? "" : this.anexoProcuraduria; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 142 */   public void setAnexoContraloria(String p) { this.anexoContraloria = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 150 */   public String getAnexoContraloria() { return (this.anexoContraloria == null) ? "" : this.anexoContraloria; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 158 */   public void setAnexoContrato(String p) { this.anexoContrato = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   public String getAnexoContrato() { return (this.anexoContrato == null) ? "" : this.anexoContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 174 */   public void setAnexoPolizas(String p) { this.anexoPolizas = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 182 */   public String getAnexoPolizas() { return (this.anexoPolizas == null) ? "" : this.anexoPolizas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 190 */   public void setAnexoImpuestos(String p) { this.anexoImpuestos = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 198 */   public String getAnexoImpuestos() { return (this.anexoImpuestos == null) ? "" : this.anexoImpuestos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 206 */   public void setAnexoCedula(String p) { this.anexoCedula = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 214 */   public String getAnexoCedula() { return (this.anexoCedula == null) ? "" : this.anexoCedula; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 222 */   public void setAnexoRegHab(String p) { this.anexoRegHab = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 230 */   public String getAnexoRegHab() { return (this.anexoRegHab == null) ? "" : this.anexoRegHab; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 238 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 246 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 254 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 262 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 270 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 278 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 286 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 294 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\ContActaInicioDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */