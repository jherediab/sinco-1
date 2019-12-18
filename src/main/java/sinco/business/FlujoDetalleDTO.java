/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.FlujoDetalleDTO;
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
/*     */ public class FlujoDetalleDTO
/*     */ {
/*     */   private int codigoFlujo;
/*     */   private int secuencia;
/*     */   private int servicioInicio;
/*     */   private int codigoEstado;
/*     */   private int servicioDestino;
/*     */   private String nombreProcedimiento;
/*     */   private String correoDestino;
/*     */   private String estado;
/*     */   private String enviarSolicitud;
/*     */   private int caracteristica;
/*     */   private int caracteristicaCorreo;
/*     */   private int caracteristicaValor;
/*     */   private String usuarioInsercion;
/*     */   private String fechaInsercion;
/*     */   private String usuarioModificacion;
/*     */   private String fechaModificacion;
/*     */   private String nombreServicioInicio;
/*     */   private String nombreCodigoEstado;
/*     */   private String nombreServicioDestino;
/*     */   private String nombreEstado;
/*     */   private String nombreCaracteristica;
/*     */   private String descripcionValor;
/*     */   private String mismoProveedor;
/*     */   private String metodoSeleccionProveedor;
/*     */   private String mismoCliente;
/*     */   private String indCorreoCliente;
/*     */   private String enviar_hermana;
/*     */   private String enviar_si_hermana_cerrada;
/*     */   private String ind_cliente_inicial;
/*     */   
/*  75 */   public void setCodigoFlujo(int p) { this.codigoFlujo = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public int getCodigoFlujo() { return this.codigoFlujo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public void setSecuencia(int p) { this.secuencia = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   public int getSecuencia() { return this.secuencia; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   public void setServicioInicio(int p) { this.servicioInicio = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   public int getServicioInicio() { return this.servicioInicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public void setCodigoEstado(int p) { this.codigoEstado = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   public int getCodigoEstado() { return this.codigoEstado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   public void setServicioDestino(int p) { this.servicioDestino = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public int getServicioDestino() { return this.servicioDestino; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   public void setNombreProcedimiento(String p) { this.nombreProcedimiento = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 164 */   public String getNombreProcedimiento() { return (this.nombreProcedimiento == null) ? "" : this.nombreProcedimiento; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 172 */   public void setCorreoDestino(String p) { this.correoDestino = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 180 */   public String getCorreoDestino() { return (this.correoDestino == null) ? "" : this.correoDestino; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 188 */   public void setEstado(String p) { this.estado = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 196 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 204 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 212 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 220 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 228 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 236 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 244 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 252 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 260 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 265 */   public void setNombreServicioInicio(String p) { this.nombreServicioInicio = p; }
/*     */ 
/*     */   
/* 268 */   public String getNombreServicioInicio() { return (this.nombreServicioInicio == null) ? "" : this.nombreServicioInicio; }
/*     */ 
/*     */   
/* 271 */   public void setNombreCodigoEstado(String p) { this.nombreCodigoEstado = p; }
/*     */ 
/*     */   
/* 274 */   public String getNombreCodigoEstado() { return (this.nombreCodigoEstado == null) ? "" : this.nombreCodigoEstado; }
/*     */ 
/*     */   
/* 277 */   public void setNombreServicioDestino(String p) { this.nombreServicioDestino = p; }
/*     */ 
/*     */   
/* 280 */   public String getNombreServicioDestino() { return (this.nombreServicioDestino == null) ? "" : this.nombreServicioDestino; }
/*     */ 
/*     */   
/* 283 */   public void setNombreEstado(String p) { this.nombreEstado = p; }
/*     */ 
/*     */   
/* 286 */   public String getNombreEstado() { return (this.nombreEstado == null) ? "" : this.nombreEstado; }
/*     */ 
/*     */ 
/*     */   
/* 290 */   public String getEnviarSolicitud() { return (this.enviarSolicitud == null) ? "" : this.enviarSolicitud; }
/*     */ 
/*     */ 
/*     */   
/* 294 */   public void setEnviarSolicitud(String enviarSolicitud) { this.enviarSolicitud = enviarSolicitud; }
/*     */ 
/*     */ 
/*     */   
/* 298 */   public int getCaracteristica() { return this.caracteristica; }
/*     */ 
/*     */ 
/*     */   
/* 302 */   public void setCaracteristica(int caracteristica) { this.caracteristica = caracteristica; }
/*     */ 
/*     */ 
/*     */   
/* 306 */   public int getCaracteristicaValor() { return this.caracteristicaValor; }
/*     */ 
/*     */ 
/*     */   
/* 310 */   public void setCaracteristicaValor(int caracteristicaValor) { this.caracteristicaValor = caracteristicaValor; }
/*     */ 
/*     */ 
/*     */   
/* 314 */   public String getNombreCaracteristica() { return (this.nombreCaracteristica == null) ? "" : this.nombreCaracteristica; }
/*     */ 
/*     */ 
/*     */   
/* 318 */   public void setNombreCaracteristica(String nombreCaracteristica) { this.nombreCaracteristica = nombreCaracteristica; }
/*     */ 
/*     */ 
/*     */   
/* 322 */   public String getDescripcionValor() { return (this.descripcionValor == null) ? "" : this.descripcionValor; }
/*     */ 
/*     */ 
/*     */   
/* 326 */   public void setDescripcionValor(String descripcionValor) { this.descripcionValor = descripcionValor; }
/*     */ 
/*     */ 
/*     */   
/* 330 */   public int getCaracteristicaCorreo() { return this.caracteristicaCorreo; }
/*     */ 
/*     */ 
/*     */   
/* 334 */   public void setCaracteristicaCorreo(int caracteristicaCorreo) { this.caracteristicaCorreo = caracteristicaCorreo; }
/*     */ 
/*     */ 
/*     */   
/* 338 */   public String getMismoProveedor() { return (this.mismoProveedor == null) ? "" : this.mismoProveedor; }
/*     */ 
/*     */ 
/*     */   
/* 342 */   public void setMismoProveedor(String mismoProveedor) { this.mismoProveedor = mismoProveedor; }
/*     */ 
/*     */ 
/*     */   
/* 346 */   public String getMismoCliente() { return (this.mismoCliente == null) ? "" : this.mismoCliente; }
/*     */ 
/*     */ 
/*     */   
/* 350 */   public void setMismoCliente(String mismoCliente) { this.mismoCliente = mismoCliente; }
/*     */ 
/*     */ 
/*     */   
/* 354 */   public String getMetodoSeleccionProveedor() { return (this.metodoSeleccionProveedor == null) ? "" : this.metodoSeleccionProveedor; }
/*     */ 
/*     */ 
/*     */   
/* 358 */   public void setMetodoSeleccionProveedor(String metodoSeleccionProveedor) { this.metodoSeleccionProveedor = metodoSeleccionProveedor; }
/*     */ 
/*     */ 
/*     */   
/* 362 */   public String getIndCorreoCliente() { return (this.indCorreoCliente == null) ? "" : this.indCorreoCliente; }
/*     */ 
/*     */ 
/*     */   
/* 366 */   public void setIndCorreoCliente(String indCorreoCliente) { this.indCorreoCliente = indCorreoCliente; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 373 */   public String getEnviar_hermana() { return (this.enviar_hermana == null) ? "" : this.enviar_hermana; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 380 */   public void setEnviar_hermana(String enviarHermana) { this.enviar_hermana = enviarHermana; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 387 */   public String getEnviar_si_hermana_cerrada() { return (this.enviar_si_hermana_cerrada == null) ? "" : this.enviar_si_hermana_cerrada; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 394 */   public void setEnviar_si_hermana_cerrada(String enviarSiHermanaCerrada) { this.enviar_si_hermana_cerrada = enviarSiHermanaCerrada; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 401 */   public String getInd_cliente_inicial() { return (this.ind_cliente_inicial == null) ? "" : this.ind_cliente_inicial; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 408 */   public void setInd_cliente_inicial(String indClienteInicial) { this.ind_cliente_inicial = indClienteInicial; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\FlujoDetalleDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */