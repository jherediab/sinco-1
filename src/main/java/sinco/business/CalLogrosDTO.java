/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.CalLogrosDTO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalLogrosDTO
/*     */ {
/*     */   private int codigoCiclo;
/*     */   private int codigoPlan;
/*     */   private int codigoObjetivo;
/*     */   private int codigoMeta;
/*     */   private int periodo;
/*     */   private double valorLogro;
/*     */   private double valorMeta;
/*     */   private String justificacion;
/*     */   private String estado;
/*     */   private String fechaInsercion;
/*     */   private String usuarioInsercion;
/*     */   private String fechaModificacion;
/*     */   private String usuarioModificacion;
/*     */   private String tipoMedicion;
/*     */   private String frecuenciaMedicion;
/*     */   private String cumple;
/*     */   private String nombreMeta;
/*     */   private int accionNumero;
/*     */   private int codigoArea;
/*     */   private String nombreSubProceso;
/*     */   private String nombreProceso;
/*     */   private String proceso;
/*     */   private String subproceso;
/*     */   private int factor;
/*     */   private int cumplen;
/*     */   private int noCumplen;
/*     */   private String nombreArea;
/*     */   private double factorCumplimiento;
/*     */   private int numeroLogros;
/*     */   private double valorMetaPlan;
/*     */   
/*  43 */   public void setCodigoCiclo(int p) { this.codigoCiclo = p; }
/*     */ 
/*     */   
/*  46 */   public int getCodigoCiclo() { return this.codigoCiclo; }
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
/*  57 */   public void setCodigoObjetivo(int p) { this.codigoObjetivo = p; }
/*     */ 
/*     */   
/*  60 */   public int getCodigoObjetivo() { return this.codigoObjetivo; }
/*     */ 
/*     */ 
/*     */   
/*  64 */   public void setCodigoMeta(int p) { this.codigoMeta = p; }
/*     */ 
/*     */   
/*  67 */   public int getCodigoMeta() { return this.codigoMeta; }
/*     */ 
/*     */ 
/*     */   
/*  71 */   public void setPeriodo(int p) { this.periodo = p; }
/*     */ 
/*     */   
/*  74 */   public int getPeriodo() { return this.periodo; }
/*     */ 
/*     */ 
/*     */   
/*  78 */   public void setValorLogro(double p) { this.valorLogro = p; }
/*     */ 
/*     */   
/*  81 */   public double getValorLogro() { return this.valorLogro; }
/*     */ 
/*     */ 
/*     */   
/*  85 */   public void setValorMeta(double p) { this.valorMeta = p; }
/*     */ 
/*     */   
/*  88 */   public double getValorMeta() { return this.valorMeta; }
/*     */ 
/*     */ 
/*     */   
/*  92 */   public void setJustificacion(String p) { this.justificacion = p; }
/*     */ 
/*     */   
/*  95 */   public String getJustificacion() { return (this.justificacion == null) ? "" : this.justificacion; }
/*     */ 
/*     */ 
/*     */   
/*  99 */   public void setEstado(String p) { this.estado = p; }
/*     */ 
/*     */   
/* 102 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*     */ 
/*     */ 
/*     */   
/* 106 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */   
/* 109 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */   
/* 113 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */   
/* 116 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */   
/* 120 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */   
/* 123 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */   
/* 127 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */   
/* 130 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   public void setCumplen(int p) { this.cumplen = p; }
/*     */ 
/*     */   
/* 139 */   public int getCumplen() { return this.cumplen; }
/*     */ 
/*     */ 
/*     */   
/* 143 */   public void setNoCumplen(int p) { this.noCumplen = p; }
/*     */ 
/*     */   
/* 146 */   public int getNoCumplen() { return this.noCumplen; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   public void setTipoMedicion(String p) { this.tipoMedicion = p; }
/*     */ 
/*     */   
/* 155 */   public String getTipoMedicion() { return (this.tipoMedicion == null) ? "" : this.tipoMedicion; }
/*     */ 
/*     */ 
/*     */   
/* 159 */   public void setFrecuenciaMedicion(String p) { this.frecuenciaMedicion = p; }
/*     */ 
/*     */   
/* 162 */   public String getFrecuenciaMedicion() { return (this.frecuenciaMedicion == null) ? "" : this.frecuenciaMedicion; }
/*     */ 
/*     */ 
/*     */   
/* 166 */   public void setNombreMeta(String p) { this.nombreMeta = p; }
/*     */ 
/*     */   
/* 169 */   public String getNombreMeta() { return (this.nombreMeta == null) ? "" : this.nombreMeta; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 174 */   public void setProceso(String p) { this.proceso = p; }
/*     */ 
/*     */   
/* 177 */   public String getProceso() { return (this.proceso == null) ? "" : this.proceso; }
/*     */ 
/*     */ 
/*     */   
/* 181 */   public void setSubproceso(String p) { this.subproceso = p; }
/*     */ 
/*     */   
/* 184 */   public String getSubproceso() { return (this.subproceso == null) ? "" : this.subproceso; }
/*     */ 
/*     */ 
/*     */   
/* 188 */   public void setNombreProceso(String p) { this.nombreProceso = p; }
/*     */ 
/*     */   
/* 191 */   public String getNombreProceso() { return (this.nombreProceso == null) ? "" : this.nombreProceso; }
/*     */ 
/*     */   
/* 194 */   public void setNombreSubProceso(String p) { this.nombreSubProceso = p; }
/*     */ 
/*     */   
/* 197 */   public String getNombreSubProceso() { return (this.nombreSubProceso == null) ? "" : this.nombreSubProceso; }
/*     */ 
/*     */ 
/*     */   
/* 201 */   public void setNombreArea(String p) { this.nombreArea = p; }
/*     */ 
/*     */   
/* 204 */   public String getNombreArea() { return (this.nombreArea == null) ? "" : this.nombreArea; }
/*     */ 
/*     */ 
/*     */   
/* 208 */   public void setFactor(int p) { this.factor = p; }
/*     */ 
/*     */   
/* 211 */   public double getFactor() { return this.factor; }
/*     */ 
/*     */ 
/*     */   
/* 215 */   public void setCumple(String p) { this.cumple = p; }
/*     */ 
/*     */   
/* 218 */   public String getCumple() { return (this.cumple == null) ? "" : this.cumple; }
/*     */ 
/*     */ 
/*     */   
/* 222 */   public void setAccionNumero(int p) { this.accionNumero = p; }
/*     */ 
/*     */   
/* 225 */   public int getAccionNumero() { return this.accionNumero; }
/*     */ 
/*     */ 
/*     */   
/* 229 */   public void setCodigoArea(int p) { this.codigoArea = p; }
/*     */ 
/*     */   
/* 232 */   public int getCodigoArea() { return this.codigoArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 237 */   public void setFactorCumplimiento(double p) { this.factorCumplimiento = p; }
/*     */ 
/*     */   
/* 240 */   public double getFactorCumplimiento() { return this.factorCumplimiento; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 249 */   public int getNumeroLogros() { return this.numeroLogros; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 255 */   public void setNumeroLogros(int numeroLogros) { this.numeroLogros = numeroLogros; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 260 */   public void setValorMetaPlan(double p) { this.valorMetaPlan = p; }
/*     */ 
/*     */   
/* 263 */   public double getValorMetaPlan() { return this.valorMetaPlan; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\CalLogrosDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */