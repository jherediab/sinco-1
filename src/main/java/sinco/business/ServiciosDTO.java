/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.ServiciosDTO;
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
/*     */ public class ServiciosDTO
/*     */ {
/*     */   private int codigo;
/*     */   private String descripcion;
/*     */   private String especializado;
/*     */   private int duracion;
/*     */   private String unidadMedida;
/*     */   private int porcentajeEsc1;
/*     */   private int porcentajeEsc2;
/*     */   private int porcentajeEsc3;
/*     */   private int porcentajeEsc4;
/*     */   private String tipoServicio;
/*     */   private String anidar;
/*     */   private String cambiaProveedor;
/*     */   private String permiteDevolver;
/*     */   private String reasignarAuditor;
/*     */   private String calificarServicio;
/*     */   private String clientePreferencia;
/*     */   private String permiteDevolverPoliticas;
/*     */   private String autonumerarSolicitud;
/*     */   private String permiteDevolverAtencion;
/*     */   private String permitirEscogerProveedor;
/*     */   private String autoaceptarAplamientos;
/*     */   private String cerrarPorEscalamientos;
/*     */   private String indFlujoTrabajo;
/*     */   private String indCorreoCalificacion;
/*     */   private String indAvanzarCaracteristica;
/*     */   private String proceso;
/*     */   private String subproceso;
/*     */   private String archivoAnexo;
/*     */   private int numeroAnexos;
/*     */   private int numeroAnexosEnvio;
/*     */   private String correoNotificacion;
/*     */   private int escalarA;
/*     */   private String estado;
/*     */   private String observaciones;
/*     */   private String mensaje;
/*     */   private String fechaInsercion;
/*     */   private String usuarioInsercion;
/*     */   private String fechaModificacion;
/*     */   private String usuarioModificacion;
/*     */   private String nombreEspecializado;
/*     */   private String nombreUnidadMedida;
/*     */   private String nombreTipoSolicitud;
/*     */   private String nombreTipoServicio;
/*     */   private String nombreProceso;
/*     */   private String nombreEstado;
/*     */   private String nombreArea;
/*     */   private String nombreResponsable;
/*     */   private int codigoArea;
/*     */   
/* 113 */   public void setCodigo(int p) { this.codigo = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   public int getCodigo() { return this.codigo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   public void setDescripcion(String p) { this.descripcion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   public String getDescripcion() { return (this.descripcion == null) ? "" : this.descripcion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 145 */   public void setEspecializado(String p) { this.especializado = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 153 */   public String getEspecializado() { return (this.especializado == null) ? "" : this.especializado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 161 */   public void setDuracion(int p) { this.duracion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 169 */   public int getDuracion() { return this.duracion; }
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
/* 180 */   public void setPorcentajeEsc1(int p) { this.porcentajeEsc1 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 188 */   public int getPorcentajeEsc1() { return this.porcentajeEsc1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 196 */   public void setPorcentajeEsc2(int p) { this.porcentajeEsc2 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 204 */   public int getPorcentajeEsc2() { return this.porcentajeEsc2; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 212 */   public void setPorcentajeEsc3(int p) { this.porcentajeEsc3 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 220 */   public int getPorcentajeEsc3() { return this.porcentajeEsc3; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 228 */   public void setPorcentajeEsc4(int p) { this.porcentajeEsc4 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 236 */   public int getPorcentajeEsc4() { return this.porcentajeEsc4; }
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
/* 247 */   public void setAnidar(String p) { this.anidar = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 255 */   public String getAnidar() { return (this.anidar == null) ? "" : this.anidar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 263 */   public void setCambiaProveedor(String p) { this.cambiaProveedor = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 271 */   public String getCambiaProveedor() { return (this.cambiaProveedor == null) ? "" : this.cambiaProveedor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 280 */   public void setCalificarServicio(String p) { this.calificarServicio = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 288 */   public void setClientePreferencia(String p) { this.clientePreferencia = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 296 */   public String getClientePreferencia() { return (this.clientePreferencia == null) ? "" : this.clientePreferencia; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 304 */   public void setPermiteDevolverPoliticas(String p) { this.permiteDevolverPoliticas = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 312 */   public String getPermiteDevolverPoliticas() { return (this.permiteDevolverPoliticas == null) ? "" : this.permiteDevolverPoliticas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 320 */   public void setAutonumerarSolicitud(String p) { this.autonumerarSolicitud = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 328 */   public String getAutonumerarSolicitud() { return (this.autonumerarSolicitud == null) ? "" : this.autonumerarSolicitud; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 336 */   public void setPermiteDevolverAtencion(String p) { this.permiteDevolverAtencion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 344 */   public String getPermiteDevolverAtencion() { return (this.permiteDevolverAtencion == null) ? "" : this.permiteDevolverAtencion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 352 */   public void setPermitirEscogerProveedor(String p) { this.permitirEscogerProveedor = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 360 */   public String getPermitirEscogerProveedor() { return (this.permitirEscogerProveedor == null) ? "" : this.permitirEscogerProveedor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 369 */   public void setAutoaceptarAplamientos(String p) { this.autoaceptarAplamientos = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 377 */   public String getAutoaceptarAplamientos() { return (this.autoaceptarAplamientos == null) ? "" : this.autoaceptarAplamientos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 385 */   public void setCerrarPorEscalamientos(String p) { this.cerrarPorEscalamientos = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 393 */   public String getCerrarPorEscalamientos() { return (this.cerrarPorEscalamientos == null) ? "" : this.cerrarPorEscalamientos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 401 */   public void setIndFlujoTrabajo(String p) { this.indFlujoTrabajo = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 409 */   public String getIndFlujoTrabajo() { return (this.indFlujoTrabajo == null) ? "" : this.indFlujoTrabajo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 418 */   public void setIndCorreoCalificacion(String p) { this.indCorreoCalificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 426 */   public String getIndCorreoCalificacion() { return (this.indCorreoCalificacion == null) ? "" : this.indCorreoCalificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 434 */   public void setIndAvanzarCaracteristica(String p) { this.indAvanzarCaracteristica = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 442 */   public String getIndAvanzarCaracteristica() { return (this.indAvanzarCaracteristica == null) ? "" : this.indAvanzarCaracteristica; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 451 */   public void setArchivoAnexo(String p) { this.archivoAnexo = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 459 */   public String getArchivoAnexo() { return (this.archivoAnexo == null) ? "" : this.archivoAnexo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 467 */   public void setNumeroAnexos(int p) { this.numeroAnexos = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 475 */   public int getNumeroAnexos() { return this.numeroAnexos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 483 */   public void setNumeroAnexosEnvio(int p) { this.numeroAnexosEnvio = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 491 */   public int getNumeroAnexosEnvio() { return this.numeroAnexosEnvio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 499 */   public void setCorreoNotificacion(String p) { this.correoNotificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 507 */   public String getCorreoNotificacion() { return (this.correoNotificacion == null) ? "" : this.correoNotificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 515 */   public void setEscalarA(int p) { this.escalarA = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 523 */   public int getEscalarA() { return this.escalarA; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 531 */   public void setEstado(String p) { this.estado = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 539 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 547 */   public void setObservaciones(String p) { this.observaciones = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 555 */   public String getObservaciones() { return (this.observaciones == null) ? "" : this.observaciones; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 563 */   public void setMensaje(String p) { this.mensaje = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 571 */   public String getMensaje() { return (this.mensaje == null) ? "" : this.mensaje; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 579 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 587 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 595 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 603 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 611 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 619 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 627 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 635 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 640 */   public void setNombreEspecializado(String p) { this.nombreEspecializado = p; }
/*     */ 
/*     */   
/* 643 */   public String getNombreEspecializado() { return (this.nombreEspecializado == null) ? "" : this.nombreEspecializado; }
/*     */ 
/*     */   
/* 646 */   public void setNombreUnidadMedida(String p) { this.nombreUnidadMedida = p; }
/*     */ 
/*     */   
/* 649 */   public String getNombreUnidadMedida() { return (this.nombreUnidadMedida == null) ? "" : this.nombreUnidadMedida; }
/*     */ 
/*     */   
/* 652 */   public void setNombreTipoSolicitud(String p) { this.nombreTipoSolicitud = p; }
/*     */ 
/*     */   
/* 655 */   public String getNombreTipoSolicitud() { return (this.nombreTipoSolicitud == null) ? "" : this.nombreTipoSolicitud; }
/*     */ 
/*     */   
/* 658 */   public void setNombreTipoServicio(String p) { this.nombreTipoServicio = p; }
/*     */ 
/*     */   
/* 661 */   public String getNombreTipoServicio() { return (this.nombreTipoServicio == null) ? "" : this.nombreTipoServicio; }
/*     */ 
/*     */   
/* 664 */   public void setNombreProceso(String p) { this.nombreProceso = p; }
/*     */ 
/*     */   
/* 667 */   public String getNombreProceso() { return (this.nombreProceso == null) ? "" : this.nombreProceso; }
/*     */ 
/*     */   
/* 670 */   public void setNombreEstado(String p) { this.nombreEstado = p; }
/*     */ 
/*     */   
/* 673 */   public String getNombreEstado() { return (this.nombreEstado == null) ? "" : this.nombreEstado; }
/*     */ 
/*     */ 
/*     */   
/* 677 */   public int getCaracteristicaDecide() { return 0; }
/*     */ 
/*     */   
/*     */   public boolean esEspecializado() {
/* 681 */     if (this.especializado == null) {
/* 682 */       return false;
/*     */     }
/* 684 */     if (this.especializado.equals("S"))
/* 685 */       return true; 
/* 686 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean esMinimo() {
/* 691 */     if (this.especializado.equals("D"))
/* 692 */       return true; 
/* 693 */     return false;
/*     */   }
/*     */   
/*     */   public boolean esOrdenado() {
/* 697 */     if (this.especializado.equals("O"))
/* 698 */       return true; 
/* 699 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean esMultiple() {
/* 704 */     if (this.especializado.equals("M"))
/* 705 */       return true; 
/* 706 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 711 */   public String getNombreArea() { return (this.nombreArea == null) ? "" : this.nombreArea; }
/*     */ 
/*     */ 
/*     */   
/* 715 */   public void setNombreArea(String nombreArea) { this.nombreArea = nombreArea; }
/*     */ 
/*     */ 
/*     */   
/* 719 */   public String getNombreResponsable() { return (this.nombreResponsable == null) ? "" : this.nombreResponsable; }
/*     */ 
/*     */ 
/*     */   
/* 723 */   public void setNombreResponsable(String nombreResponsable) { this.nombreResponsable = nombreResponsable; }
/*     */ 
/*     */ 
/*     */   
/* 727 */   public int getCodigoArea() { return this.codigoArea; }
/*     */ 
/*     */ 
/*     */   
/* 731 */   public void setCodigoArea(int codigoArea) { this.codigoArea = codigoArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 740 */   public String getPermiteDevolver() { return (this.permiteDevolver == null) ? "" : this.permiteDevolver; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPermiteDevolver() {
/* 745 */     if (this.permiteDevolver == null) {
/* 746 */       return false;
/*     */     }
/* 748 */     return this.permiteDevolver.equals("S");
/*     */   }
/*     */ 
/*     */   
/* 752 */   public void setPermiteDevolver(String permiteDevolver) { this.permiteDevolver = permiteDevolver; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDevolverPoliticas() {
/* 762 */     if (this.permiteDevolverPoliticas == null) {
/* 763 */       return false;
/*     */     }
/* 765 */     return this.permiteDevolverPoliticas.equals("S");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 774 */   public String getCalificarServicio() { return (this.calificarServicio == null) ? "" : this.calificarServicio; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCalificarServicio() {
/* 779 */     if (this.calificarServicio == null) {
/* 780 */       return false;
/*     */     }
/* 782 */     return this.calificarServicio.equals("S");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 788 */   public String getReasignarAuditor() { return (this.reasignarAuditor == null) ? "" : this.reasignarAuditor; }
/*     */ 
/*     */ 
/*     */   
/* 792 */   public void setReasignarAuditor(String reasignarAuditor) { this.reasignarAuditor = reasignarAuditor; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReasignarAuditor() {
/* 798 */     if (this.reasignarAuditor == null) {
/* 799 */       return false;
/*     */     }
/* 801 */     return this.reasignarAuditor.equals("S");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 807 */   public String getProceso() { return (this.proceso == null) ? "" : this.proceso; }
/*     */ 
/*     */ 
/*     */   
/* 811 */   public void setProceso(String proceso) { this.proceso = proceso; }
/*     */ 
/*     */ 
/*     */   
/* 815 */   public String getSubproceso() { return (this.subproceso == null) ? "" : this.subproceso; }
/*     */ 
/*     */ 
/*     */   
/* 819 */   public void setSubproceso(String subproceso) { this.subproceso = subproceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 826 */   public String getUnidadMedida() { return (this.unidadMedida == null) ? "" : this.unidadMedida; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 833 */   public void setUnidadMedida(String unidadMedida) { this.unidadMedida = unidadMedida; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 840 */   public String getTipoServicio() { return (this.tipoServicio == null) ? "" : this.tipoServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 847 */   public void setTipoServicio(String tipoServicio) { this.tipoServicio = tipoServicio; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\ServiciosDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */