/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.SolicitudDTO;
/*     */ 
/*     */ public class SolicitudDTO {
/*     */   private int numero;
/*     */   private String fechaGenerada;
/*     */   private int areaCliente;
/*     */   private int empleadoCliente;
/*     */   private int codigoServicio;
/*     */   private int areaProveedor;
/*     */   private int empleadoProveedor;
/*     */   private int codigoEstado;
/*     */   private boolean abierta;
/*     */   private int nivelEscalamiento;
/*     */   private String fechaVigencia;
/*     */   private String fechaEstimadaTerminacion;
/*     */   private String fechaRealTerminacion;
/*     */   private String fechaBaseEscalamientos;
/*     */   private int solicitudPadre;
/*     */   private String codigoOportunidad;
/*     */   private String fechaOportunidad;
/*     */   private String codigoConfiabilidad;
/*     */   private String fechaConfiabilidad;
/*     */   private String observaciones;
/*     */   private int duracion;
/*     */   private String unidadMedida;
/*     */   private int proveedorAnterior;
/*     */   private String ciclo;
/*     */   private String notificar;
/*     */   private int numeroMacroservicio;
/*     */   
/*  33 */   public void setNumero(int numero) { this.numero = numero; }
/*     */ 
/*     */ 
/*     */   
/*  37 */   public int getNumero() { return this.numero; }
/*     */ 
/*     */ 
/*     */   
/*  41 */   public void setFechaGenerada(String fechagenerada) { this.fechaGenerada = fechagenerada; }
/*     */ 
/*     */ 
/*     */   
/*  45 */   public String getFechaGenerada() { return this.fechaGenerada; }
/*     */ 
/*     */ 
/*     */   
/*  49 */   public void setAreaCliente(int areacliente) { this.areaCliente = areacliente; }
/*     */ 
/*     */ 
/*     */   
/*  53 */   public int getAreaCliente() { return this.areaCliente; }
/*     */ 
/*     */ 
/*     */   
/*  57 */   public void setEmpleadoCliente(int empleadocliente) { this.empleadoCliente = empleadocliente; }
/*     */ 
/*     */ 
/*     */   
/*  61 */   public int getEmpleadoCliente() { return this.empleadoCliente; }
/*     */ 
/*     */ 
/*     */   
/*  65 */   public void setCodigoServicio(int codigoservicio) { this.codigoServicio = codigoservicio; }
/*     */ 
/*     */ 
/*     */   
/*  69 */   public int getCodigoServicio() { return this.codigoServicio; }
/*     */ 
/*     */ 
/*     */   
/*  73 */   public void setAreaProveedor(int areaproveedor) { this.areaProveedor = areaproveedor; }
/*     */ 
/*     */ 
/*     */   
/*  77 */   public int getAreaProveedor() { return this.areaProveedor; }
/*     */ 
/*     */ 
/*     */   
/*  81 */   public void setEmpleadoProveedor(int empleadoproveedor) { this.empleadoProveedor = empleadoproveedor; }
/*     */ 
/*     */ 
/*     */   
/*  85 */   public int getEmpleadoProveedor() { return this.empleadoProveedor; }
/*     */ 
/*     */ 
/*     */   
/*  89 */   public void setCodigoEstado(int codigoestado) { this.codigoEstado = codigoestado; }
/*     */ 
/*     */ 
/*     */   
/*  93 */   public int getCodigoEstado() { return this.codigoEstado; }
/*     */ 
/*     */ 
/*     */   
/*  97 */   public void setAbierta(boolean abierta) { this.abierta = abierta; }
/*     */ 
/*     */ 
/*     */   
/* 101 */   public boolean getAbierta() { return this.abierta; }
/*     */ 
/*     */ 
/*     */   
/* 105 */   public void setNivelEscalamiento(int nivelescalamiento) { this.nivelEscalamiento = nivelescalamiento; }
/*     */ 
/*     */ 
/*     */   
/* 109 */   public int getNivelEscalamiento() { return this.nivelEscalamiento; }
/*     */ 
/*     */ 
/*     */   
/* 113 */   public void setFechaVigencia(String fecha) { this.fechaVigencia = fecha; }
/*     */ 
/*     */ 
/*     */   
/* 117 */   public String getFechaVigencia() { return this.fechaVigencia; }
/*     */ 
/*     */ 
/*     */   
/* 121 */   public void setFechaEstimadaTerminacion(String fecha) { this.fechaEstimadaTerminacion = fecha; }
/*     */ 
/*     */ 
/*     */   
/* 125 */   public String getFechaEstimadaTerminacion() { return this.fechaEstimadaTerminacion; }
/*     */ 
/*     */ 
/*     */   
/* 129 */   public void setFechaRealTerminacion(String fecha) { this.fechaRealTerminacion = fecha; }
/*     */ 
/*     */ 
/*     */   
/* 133 */   public String getFechaRealTerminacion() { return this.fechaRealTerminacion; }
/*     */ 
/*     */ 
/*     */   
/* 137 */   public void setFechaBaseEscalamientos(String fecha) { this.fechaBaseEscalamientos = fecha; }
/*     */ 
/*     */ 
/*     */   
/* 141 */   public String getFechaBaseEscalamientos() { return this.fechaBaseEscalamientos; }
/*     */ 
/*     */ 
/*     */   
/* 145 */   public void setSolicitudPadre(int solicitudpadre) { this.solicitudPadre = solicitudpadre; }
/*     */ 
/*     */ 
/*     */   
/* 149 */   public int getSolicitudPadre() { return this.solicitudPadre; }
/*     */ 
/*     */ 
/*     */   
/* 153 */   public void setCodigoOportunidad(String codigooportunidad) { this.codigoOportunidad = codigooportunidad; }
/*     */ 
/*     */ 
/*     */   
/* 157 */   public String getCodigoOportunidad() { return this.codigoOportunidad; }
/*     */ 
/*     */ 
/*     */   
/* 161 */   public void setFechaOportunidad(String fechaoportunidad) { this.fechaOportunidad = fechaoportunidad; }
/*     */ 
/*     */ 
/*     */   
/* 165 */   public String getFechaOportunidad() { return this.fechaOportunidad; }
/*     */ 
/*     */ 
/*     */   
/* 169 */   public void setCodigoConfiabilidad(String codigoconfiabilidad) { this.codigoConfiabilidad = codigoconfiabilidad; }
/*     */ 
/*     */ 
/*     */   
/* 173 */   public String getCodigoConfiabilidad() { return this.codigoConfiabilidad; }
/*     */ 
/*     */ 
/*     */   
/* 177 */   public void setFechaConfiabilidad(String fecha) { this.fechaConfiabilidad = fecha; }
/*     */ 
/*     */ 
/*     */   
/* 181 */   public String getFechaConfiabilidad() { return this.fechaConfiabilidad; }
/*     */ 
/*     */ 
/*     */   
/* 185 */   public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
/*     */ 
/*     */ 
/*     */   
/* 189 */   public String getObservaciones() { return this.observaciones; }
/*     */ 
/*     */ 
/*     */   
/* 193 */   public void setDuracion(int d) { this.duracion = d; }
/*     */ 
/*     */ 
/*     */   
/* 197 */   public int getDuracion() { return this.duracion; }
/*     */ 
/*     */ 
/*     */   
/* 201 */   public String getUnidad() { return this.unidadMedida; }
/*     */ 
/*     */   
/* 204 */   public void setUnidad(String u) { this.unidadMedida = u; }
/*     */ 
/*     */ 
/*     */   
/* 208 */   public void setProveedorAnterior(int p) { this.proveedorAnterior = p; }
/*     */ 
/*     */   
/* 211 */   public int getProveedorAnterior() { return this.proveedorAnterior; }
/*     */ 
/*     */ 
/*     */   
/* 215 */   public void setNotificar(String p) { this.notificar = p; }
/*     */ 
/*     */ 
/*     */   
/* 219 */   public boolean getNotificar() { return (this.notificar == null) ? false : this.notificar.equals("S"); }
/*     */ 
/*     */ 
/*     */   
/* 223 */   public void setCiclo(String p) { this.ciclo = p; }
/*     */ 
/*     */   
/* 226 */   public String getCiclo() { return (this.ciclo == null) ? "" : this.ciclo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 233 */   public int getNumeroMacroservicio() { return this.numeroMacroservicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 240 */   public void setNumeroMacroservicio(int numeroMacroservicio) { this.numeroMacroservicio = numeroMacroservicio; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\SolicitudDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */