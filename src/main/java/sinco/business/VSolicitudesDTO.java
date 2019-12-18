/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.VSolicitudesDTO;
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
/*     */ public class VSolicitudesDTO
/*     */ {
/*     */   private int numero;
/*     */   private String fechaGenerada;
/*     */   private int areaCliente;
/*     */   private int empleadoCliente;
/*     */   private int codigoServicio;
/*     */   private int areaProveedor;
/*     */   private int empleadoProveedor;
/*     */   private int codigoEstado;
/*     */   private int estadoAnterior;
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
/*     */   private String atencion;
/*     */   private int proveedorAnterior;
/*     */   private String ciclo;
/*     */   private String notificar;
/*     */   private String nombreAreaProveedora;
/*     */   private String nombreAreaCliente;
/*     */   private String nombreCliente;
/*     */   private String nombreProveedor;
/*     */   private String nombreServicio;
/*     */   private String nombreEstado;
/*     */   private int duracion;
/*     */   private String unidadMedida;
/*     */   private int encuesta;
/*     */   private int tipoServicio;
/*     */   private String tipoEstado;
/*     */   private String tipoCalificacionAuditor;
/*     */   private int empleadoAuditor;
/*     */   private String macroServicio;
/*     */   private int numeroDevoluciones;
/*     */   private int tiempoServicio;
/*     */   private String tiempoPromedio;
/*     */   private String fechaContacto;
/*     */   private int secuenciaExterna;
/*     */   private int numeroMostrar;
/*     */   private double tiempoTotal;
/*     */   private int numeroHijas;
/*     */   private int numeroFlujo;
/*     */   private int numeroHijasAbiertas;
/*     */   private String hija;
/*     */   
/*  67 */   public void setNumero(int numero) { this.numero = numero; }
/*     */ 
/*     */ 
/*     */   
/*  71 */   public int getNumero() { return this.numero; }
/*     */ 
/*     */ 
/*     */   
/*  75 */   public void setFechaGenerada(String fechaGenerada) { this.fechaGenerada = fechaGenerada; }
/*     */ 
/*     */ 
/*     */   
/*  79 */   public String getFechaGenerada() { return (this.fechaGenerada == null) ? "" : this.fechaGenerada; }
/*     */ 
/*     */ 
/*     */   
/*  83 */   public void setAreaCliente(int areaCliente) { this.areaCliente = areaCliente; }
/*     */ 
/*     */ 
/*     */   
/*  87 */   public int getAreaCliente() { return this.areaCliente; }
/*     */ 
/*     */ 
/*     */   
/*  91 */   public void setEmpleadoCliente(int empleadoCliente) { this.empleadoCliente = empleadoCliente; }
/*     */ 
/*     */ 
/*     */   
/*  95 */   public int getEmpleadoCliente() { return this.empleadoCliente; }
/*     */ 
/*     */ 
/*     */   
/*  99 */   public void setCodigoServicio(int codigoServicio) { this.codigoServicio = codigoServicio; }
/*     */ 
/*     */ 
/*     */   
/* 103 */   public int getCodigoServicio() { return this.codigoServicio; }
/*     */ 
/*     */ 
/*     */   
/* 107 */   public void setAreaProveedor(int areaProveedor) { this.areaProveedor = areaProveedor; }
/*     */ 
/*     */ 
/*     */   
/* 111 */   public int getAreaProveedor() { return this.areaProveedor; }
/*     */ 
/*     */ 
/*     */   
/* 115 */   public void setEmpleadoProveedor(int empleadoProveedor) { this.empleadoProveedor = empleadoProveedor; }
/*     */ 
/*     */ 
/*     */   
/* 119 */   public int getEmpleadoProveedor() { return this.empleadoProveedor; }
/*     */ 
/*     */ 
/*     */   
/* 123 */   public void setCodigoEstado(int codigoEstado) { this.codigoEstado = codigoEstado; }
/*     */ 
/*     */ 
/*     */   
/* 127 */   public int getCodigoEstado() { return this.codigoEstado; }
/*     */ 
/*     */ 
/*     */   
/* 131 */   public void setAbierta(boolean abierta) { this.abierta = abierta; }
/*     */ 
/*     */ 
/*     */   
/* 135 */   public boolean getAbierta() { return this.abierta; }
/*     */ 
/*     */ 
/*     */   
/* 139 */   public void setNivelEscalamiento(int nivelEscalamiento) { this.nivelEscalamiento = nivelEscalamiento; }
/*     */ 
/*     */ 
/*     */   
/* 143 */   public int getNivelEscalamiento() { return this.nivelEscalamiento; }
/*     */ 
/*     */ 
/*     */   
/* 147 */   public void setFechaVigencia(String fecha) { this.fechaVigencia = fecha; }
/*     */ 
/*     */ 
/*     */   
/* 151 */   public String getFechaVigencia() { return (this.fechaVigencia == null) ? "" : this.fechaVigencia; }
/*     */ 
/*     */ 
/*     */   
/* 155 */   public void setFechaEstimadaTerminacion(String fecha) { this.fechaEstimadaTerminacion = fecha; }
/*     */ 
/*     */ 
/*     */   
/* 159 */   public String getFechaEstimadaTerminacion() { return (this.fechaEstimadaTerminacion == null) ? "" : this.fechaEstimadaTerminacion; }
/*     */ 
/*     */ 
/*     */   
/* 163 */   public void setFechaRealTerminacion(String fecha) { this.fechaRealTerminacion = fecha; }
/*     */ 
/*     */ 
/*     */   
/* 167 */   public String getFechaRealTerminacion() { return (this.fechaRealTerminacion == null) ? "" : this.fechaRealTerminacion; }
/*     */ 
/*     */ 
/*     */   
/* 171 */   public void setFechaBaseEscalamientos(String fecha) { this.fechaBaseEscalamientos = fecha; }
/*     */ 
/*     */ 
/*     */   
/* 175 */   public String getFechaBaseEscalamientos() { return (this.fechaBaseEscalamientos == null) ? "" : this.fechaBaseEscalamientos; }
/*     */ 
/*     */ 
/*     */   
/* 179 */   public void setSolicitudPadre(int solicitudPadre) { this.solicitudPadre = solicitudPadre; }
/*     */ 
/*     */ 
/*     */   
/* 183 */   public int getSolicitudPadre() { return this.solicitudPadre; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 188 */   public void setCodigoOportunidad(String codigoOportunidad) { this.codigoOportunidad = codigoOportunidad; }
/*     */ 
/*     */ 
/*     */   
/* 192 */   public String getCodigoOportunidad() { return (this.codigoOportunidad == null) ? "" : this.codigoOportunidad; }
/*     */ 
/*     */ 
/*     */   
/* 196 */   public void setFechaOportunidad(String fechaOportunidad) { this.fechaOportunidad = fechaOportunidad; }
/*     */ 
/*     */ 
/*     */   
/* 200 */   public String getFechaOportunidad() { return (this.fechaOportunidad == null) ? "" : this.fechaOportunidad; }
/*     */ 
/*     */ 
/*     */   
/* 204 */   public void setCodigoConfiabilidad(String codigoConfiabilidad) { this.codigoConfiabilidad = codigoConfiabilidad; }
/*     */ 
/*     */ 
/*     */   
/* 208 */   public String getCodigoConfiabilidad() { return (this.codigoConfiabilidad == null) ? "" : this.codigoConfiabilidad; }
/*     */ 
/*     */ 
/*     */   
/* 212 */   public void setFechaConfiabilidad(String fecha) { this.fechaConfiabilidad = fecha; }
/*     */ 
/*     */ 
/*     */   
/* 216 */   public String getFechaConfiabilidad() { return (this.fechaConfiabilidad == null) ? "" : this.fechaConfiabilidad; }
/*     */ 
/*     */ 
/*     */   
/* 220 */   public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
/*     */ 
/*     */ 
/*     */   
/* 224 */   public String getObservaciones() { return (this.observaciones == null) ? "" : this.observaciones; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 229 */   public void setNombreAreaProveedora(String c) { this.nombreAreaProveedora = c; }
/*     */ 
/*     */ 
/*     */   
/* 233 */   public void setNombreAreaCliente(String c) { this.nombreAreaCliente = c; }
/*     */ 
/*     */ 
/*     */   
/* 237 */   public void setNombreCliente(String c) { this.nombreCliente = c; }
/*     */ 
/*     */ 
/*     */   
/* 241 */   public void setNombreProveedor(String c) { this.nombreProveedor = c; }
/*     */ 
/*     */   
/* 244 */   public void setNombreServicio(String c) { this.nombreServicio = c; }
/*     */ 
/*     */   
/* 247 */   public void setNombreEstado(String c) { this.nombreEstado = c; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 252 */   public String getNombreAreaProveedora() { return (this.nombreAreaProveedora == null) ? "" : this.nombreAreaProveedora; }
/*     */ 
/*     */   
/* 255 */   public String getNombreProveedor() { return (this.nombreProveedor == null) ? "" : this.nombreProveedor; }
/*     */ 
/*     */   
/* 258 */   public String getNombreAreaCliente() { return (this.nombreAreaCliente == null) ? "" : this.nombreAreaCliente; }
/*     */ 
/*     */ 
/*     */   
/* 262 */   public String getNombreCliente() { return (this.nombreCliente == null) ? "" : this.nombreCliente; }
/*     */ 
/*     */   
/* 265 */   public String getNombreServicio() { return (this.nombreServicio == null) ? "" : this.nombreServicio; }
/*     */ 
/*     */ 
/*     */   
/* 269 */   public String getNombreEstado() { return (this.nombreEstado == null) ? "" : this.nombreEstado; }
/*     */ 
/*     */ 
/*     */   
/* 273 */   public void setDuracion(int d) { this.duracion = d; }
/*     */ 
/*     */ 
/*     */   
/* 277 */   public int getDuracion() { return this.duracion; }
/*     */ 
/*     */ 
/*     */   
/* 281 */   public void setProveedorAnterior(int p) { this.proveedorAnterior = p; }
/*     */ 
/*     */   
/* 284 */   public int getProveedorAnterior() { return this.proveedorAnterior; }
/*     */ 
/*     */ 
/*     */   
/* 288 */   public void setCiclo(String p) { this.ciclo = p; }
/*     */ 
/*     */   
/* 291 */   public String getCiclo() { return (this.ciclo == null) ? "" : this.ciclo; }
/*     */ 
/*     */ 
/*     */   
/* 295 */   public void setNotificar(String p) { this.notificar = p; }
/*     */ 
/*     */ 
/*     */   
/* 299 */   public boolean getNotificar() { return (this.notificar == null) ? false : this.notificar.equals("S"); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 306 */   public int getEncuesta() { return this.encuesta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 312 */   public void setEncuesta(int encuesta) { this.encuesta = encuesta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 318 */   public int getEmpleadoAuditor() { return this.empleadoAuditor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 324 */   public void setEmpleadoAuditor(int empleadoAuditor) { this.empleadoAuditor = empleadoAuditor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 330 */   public String getTipoEstado() { return (this.tipoEstado == null) ? "" : this.tipoEstado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 336 */   public void setTipoEstado(String tipoEstado) { this.tipoEstado = tipoEstado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 342 */   public int getTipoServicio() { return this.tipoServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 348 */   public void setTipoServicio(int tipoServicio) { this.tipoServicio = tipoServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 354 */   public String getTipoCalificacionAuditor() { return (this.tipoCalificacionAuditor == null) ? "" : this.tipoCalificacionAuditor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 360 */   public void setTipoCalificacionAuditor(String tipoCalificacionAuditor) { this.tipoCalificacionAuditor = tipoCalificacionAuditor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 367 */   public int getEstadoAnterior() { return this.estadoAnterior; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 374 */   public void setEstadoAnterior(int estadoAnterior) { this.estadoAnterior = estadoAnterior; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 381 */   public String getMacroServicio() { return (this.macroServicio == null) ? "" : this.macroServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 388 */   public void setMacroServicio(String macroServicio) { this.macroServicio = macroServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 395 */   public int getNumeroDevoluciones() { return this.numeroDevoluciones; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 402 */   public void setNumeroDevoluciones(int numeroDevoluciones) { this.numeroDevoluciones = numeroDevoluciones; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 409 */   public int getTiempoServicio() { return this.tiempoServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 416 */   public void setTiempoServicio(int tiempoServicio) { this.tiempoServicio = tiempoServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 423 */   public String getTiempoPromedio() { return (this.tiempoPromedio == null) ? "" : this.tiempoPromedio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 430 */   public void setTiempoPromedio(String tiempoPromedio) { this.tiempoPromedio = tiempoPromedio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 437 */   public int getSecuenciaExterna() { return this.secuenciaExterna; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 444 */   public void setSecuenciaExterna(int secuenciaExterna) { this.secuenciaExterna = secuenciaExterna; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 451 */   public int getNumeroMostrar() { return this.numeroMostrar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 458 */   public void setNumeroMostrar(int numeroMostrar) { this.numeroMostrar = numeroMostrar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 465 */   public double getTiempoTotal() { return this.tiempoTotal; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 472 */   public void setTiempoTotal(double tiempoTotal) { this.tiempoTotal = tiempoTotal; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 479 */   public String getFechaContacto() { return (this.fechaContacto == null) ? "" : this.fechaContacto; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 486 */   public void setFechaContacto(String fechaContacto) { this.fechaContacto = fechaContacto; }
/*     */ 
/*     */ 
/*     */   
/* 490 */   public String getAtencion() { return (this.atencion == null) ? "" : this.atencion; }
/*     */ 
/*     */ 
/*     */   
/* 494 */   public void setAtencion(String atencion) { this.atencion = atencion; }
/*     */ 
/*     */ 
/*     */   
/* 498 */   public int getNumeroHijas() { return this.numeroHijas; }
/*     */ 
/*     */ 
/*     */   
/* 502 */   public void setNumeroHijas(int numeroHijas) { this.numeroHijas = numeroHijas; }
/*     */ 
/*     */ 
/*     */   
/* 506 */   public int getNumeroHijasAbiertas() { return this.numeroHijasAbiertas; }
/*     */ 
/*     */ 
/*     */   
/* 510 */   public void setNumeroHijasAbiertas(int numeroHijasAbiertas) { this.numeroHijasAbiertas = numeroHijasAbiertas; }
/*     */ 
/*     */ 
/*     */   
/* 514 */   public int getNumeroFlujo() { return this.numeroFlujo; }
/*     */ 
/*     */ 
/*     */   
/* 518 */   public void setNumeroFlujo(int numeroFlujo) { this.numeroFlujo = numeroFlujo; }
/*     */ 
/*     */ 
/*     */   
/* 522 */   public String getHija() { return (this.hija == null) ? "" : this.hija; }
/*     */ 
/*     */ 
/*     */   
/* 526 */   public void setHija(String hija) { this.hija = hija; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 533 */   public String getUnidadMedida() { return (this.unidadMedida == null) ? "" : this.unidadMedida; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 540 */   public void setUnidadMedida(String unidadMedida) { this.unidadMedida = unidadMedida; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\VSolicitudesDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */