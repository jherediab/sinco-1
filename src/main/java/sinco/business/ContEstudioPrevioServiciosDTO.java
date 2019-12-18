/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.ContEstudioPrevioServiciosDTO;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContEstudioPrevioServiciosDTO
/*     */ {
/*     */   private int numeroEstudio;
/*     */   private int codigoServicio;
/*     */   private String ciudad;
/*     */   private String departamento;
/*     */   private double factor;
/*     */   private int afiliados;
/*     */   private double valorUpc;
/*     */   private double valorMes;
/*     */   private double porcentajeAfiliados;
/*     */   private int numeroMeses;
/*     */   private int numeroDias;
/*     */   private String imputacion;
/*     */   private String fechaInsercion;
/*     */   private String usuarioInsercion;
/*     */   private String fechaModificacion;
/*     */   private String usuarioModificacion;
/*     */   private String nombreServicio;
/*     */   private int tipoServicio;
/*     */   private String nombreMunicipio;
/*     */   
/*  55 */   public void setNumeroEstudio(int p) { this.numeroEstudio = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   public int getNumeroEstudio() { return this.numeroEstudio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   public void setCodigoServicio(int p) { this.codigoServicio = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   public int getCodigoServicio() { return this.codigoServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public void setCiudad(String p) { this.ciudad = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   public String getCiudad() { return (this.ciudad == null) ? "" : this.ciudad; }
/*     */ 
/*     */ 
/*     */   
/*  99 */   public void setDepartamento(String p) { this.departamento = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   public String getDepartamento() { return (this.departamento == null) ? "" : this.departamento; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   public void setFactor(double p) { this.factor = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   public double getFactor() { return this.factor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 131 */   public void setAfiliados(int p) { this.afiliados = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 139 */   public int getAfiliados() { return this.afiliados; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 147 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 155 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 163 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 171 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 179 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 187 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 195 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 203 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */   
/* 207 */   public String getNombreServicio() { return (this.nombreServicio == null) ? "" : this.nombreServicio; }
/*     */ 
/*     */ 
/*     */   
/* 211 */   public void setNombreServicio(String nombreServicio) { this.nombreServicio = nombreServicio; }
/*     */ 
/*     */ 
/*     */   
/* 215 */   public int getTipoServicio() { return this.tipoServicio; }
/*     */ 
/*     */ 
/*     */   
/* 219 */   public void setTipoServicio(int tipoServicio) { this.tipoServicio = tipoServicio; }
/*     */ 
/*     */ 
/*     */   
/* 223 */   public String getNombreMunicipio() { return (this.nombreMunicipio == null) ? "" : this.nombreMunicipio; }
/*     */ 
/*     */ 
/*     */   
/* 227 */   public void setNombreMunicipio(String nombreMunicipio) { this.nombreMunicipio = nombreMunicipio; }
/*     */ 
/*     */ 
/*     */   
/* 231 */   public double getValoUpc() { return this.valorUpc; }
/*     */ 
/*     */ 
/*     */   
/* 235 */   public void setValoUpc(double valoUpc) { this.valorUpc = valoUpc; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 242 */   public void setValorMes(double valorMes) { this.valorMes = valorMes; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 249 */   public double getValorMes() { return this.valorMes; }
/*     */ 
/*     */ 
/*     */   
/* 253 */   public void setPorcentajeAfiliados(double porcentajeAfiliados) { this.porcentajeAfiliados = porcentajeAfiliados; }
/*     */ 
/*     */   
/* 256 */   public double getPorcentajeAfiliados() { return this.porcentajeAfiliados; }
/*     */ 
/*     */ 
/*     */   
/* 260 */   public int getNumeroMeses() { return this.numeroMeses; }
/*     */ 
/*     */ 
/*     */   
/* 264 */   public void setNumeroMeses(int numeroMeses) { this.numeroMeses = numeroMeses; }
/*     */ 
/*     */ 
/*     */   
/* 268 */   public int getNumeroDias() { return this.numeroDias; }
/*     */ 
/*     */ 
/*     */   
/* 272 */   public void setNumeroDias(int numeroDias) { this.numeroDias = numeroDias; }
/*     */ 
/*     */ 
/*     */   
/* 276 */   public String getImputacion() { return (this.imputacion == null) ? "" : this.imputacion; }
/*     */ 
/*     */ 
/*     */   
/* 280 */   public void setImputacion(String imputacion) { this.imputacion = imputacion; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\ContEstudioPrevioServiciosDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */