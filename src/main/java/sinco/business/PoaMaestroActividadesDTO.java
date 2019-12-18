/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.PoaMaestroActividadesDTO;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PoaMaestroActividadesDTO
/*     */ {
/*     */   private int codigoPoa;
/*     */   private int codigoPoaActividad;
/*     */   private int objetivoEstrategico;
/*     */   private int actividad;
/*     */   private int tipoActividad;
/*     */   private String variablesMedicion;
/*     */   private int mediosDeVerificacion;
/*     */   private String proyectoInversion;
/*     */   private int productoProceso;
/*     */   private int metaPlanDeDesarrollo;
/*     */   private int objetivoSubsistema;
/*     */   private int metaProyecto;
/*     */   private String tipoRecurso;
/*     */   private String prioridadEnProducto;
/*     */   private String prioridadObjetivo;
/*     */   private String mes1;
/*     */   private String mes2;
/*     */   private String mes3;
/*     */   private String mes4;
/*     */   private String mes5;
/*     */   private String mes6;
/*     */   private String mes7;
/*     */   private String mes8;
/*     */   private String mes9;
/*     */   private String mes10;
/*     */   private String mes11;
/*     */   private String mes12;
/*     */   private int valorMes1;
/*     */   private int valorMes2;
/*     */   private int valorMes3;
/*     */   private int valorMes4;
/*     */   private int valorMes5;
/*     */   private int valorMes6;
/*     */   private int valorMes7;
/*     */   private int valorMes8;
/*     */   private int valorMes9;
/*     */   private int valorMes10;
/*     */   private int valorMes11;
/*     */   private int valorMes12;
/*     */   private String estado;
/*     */   private String fechaInsercion;
/*     */   private String usuarioInsercion;
/*     */   private String fechaModificacion;
/*     */   private String usuarioModificacion;
/*     */   private String nombreObjetivoEstrategico;
/*     */   private String nombreActividad;
/*     */   private String nombreVariablesMedicion;
/*     */   private String nombreMediosDeVerificacion;
/*     */   private String nombreProyectoInversion;
/*     */   private String nombreProductoProceso;
/*     */   private String nombreMetaPlanDeDesarrollo;
/*     */   private String nombreObjetivoSubsistema;
/*     */   private String nombreMetaProyecto;
/*     */   private String nombreTipoRecurso;
/*     */   private String nombrePrioridadEnProducto;
/*     */   private String nombrePrioridadObjetivo;
/*     */   private String nombreEstado;
/*     */   
/* 130 */   public void setCodigoPoa(int p) { this.codigoPoa = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public int getCodigoPoa() { return this.codigoPoa; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 146 */   public void setCodigoPoaActividad(int p) { this.codigoPoaActividad = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   public int getCodigoPoaActividad() { return this.codigoPoaActividad; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 162 */   public void setObjetivoEstrategico(int p) { this.objetivoEstrategico = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   public int getObjetivoEstrategico() { return this.objetivoEstrategico; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 178 */   public void setActividad(int p) { this.actividad = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 186 */   public int getActividad() { return this.actividad; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 194 */   public void setTipoActividad(int p) { this.tipoActividad = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 202 */   public int getTipoActividad() { return this.tipoActividad; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 210 */   public void setVariablesMedicion(String p) { this.variablesMedicion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 218 */   public String getVariablesMedicion() { return (this.variablesMedicion == null) ? "" : this.variablesMedicion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 226 */   public void setMediosDeVerificacion(int p) { this.mediosDeVerificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 234 */   public int getMediosDeVerificacion() { return this.mediosDeVerificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 242 */   public void setProyectoInversion(String p) { this.proyectoInversion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 250 */   public String getProyectoInversion() { return (this.proyectoInversion == null) ? "" : this.proyectoInversion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 258 */   public void setProductoProceso(int p) { this.productoProceso = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 266 */   public int getProductoProceso() { return this.productoProceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 274 */   public void setMetaPlanDeDesarrollo(int p) { this.metaPlanDeDesarrollo = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 282 */   public int getMetaPlanDeDesarrollo() { return this.metaPlanDeDesarrollo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 290 */   public void setObjetivoSubsistema(int p) { this.objetivoSubsistema = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 298 */   public int getObjetivoSubsistema() { return this.objetivoSubsistema; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 306 */   public void setMetaProyecto(int p) { this.metaProyecto = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 314 */   public int getMetaProyecto() { return this.metaProyecto; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 322 */   public void setTipoRecurso(String p) { this.tipoRecurso = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 330 */   public String getTipoRecurso() { return (this.tipoRecurso == null) ? "" : this.tipoRecurso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 338 */   public void setPrioridadEnProducto(String p) { this.prioridadEnProducto = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 346 */   public String getPrioridadEnProducto() { return (this.prioridadEnProducto == null) ? "" : this.prioridadEnProducto; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 354 */   public void setPrioridadObjetivo(String p) { this.prioridadObjetivo = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 362 */   public String getPrioridadObjetivo() { return (this.prioridadObjetivo == null) ? "" : this.prioridadObjetivo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 370 */   public void setMes1(String p) { this.mes1 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 378 */   public String getMes1() { return (this.mes1 == null) ? "" : this.mes1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 386 */   public void setMes2(String p) { this.mes2 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 394 */   public String getMes2() { return (this.mes2 == null) ? "" : this.mes2; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 402 */   public void setMes3(String p) { this.mes3 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 410 */   public String getMes3() { return (this.mes3 == null) ? "" : this.mes3; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 418 */   public void setMes4(String p) { this.mes4 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 426 */   public String getMes4() { return (this.mes4 == null) ? "" : this.mes4; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 434 */   public void setMes5(String p) { this.mes5 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 442 */   public String getMes5() { return (this.mes5 == null) ? "" : this.mes5; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 450 */   public void setMes6(String p) { this.mes6 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 458 */   public String getMes6() { return (this.mes6 == null) ? "" : this.mes6; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 466 */   public void setMes7(String p) { this.mes7 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 474 */   public String getMes7() { return (this.mes7 == null) ? "" : this.mes7; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 482 */   public void setMes8(String p) { this.mes8 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 490 */   public String getMes8() { return (this.mes8 == null) ? "" : this.mes8; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 498 */   public void setMes9(String p) { this.mes9 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 506 */   public String getMes9() { return (this.mes9 == null) ? "" : this.mes9; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 514 */   public void setMes10(String p) { this.mes10 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 522 */   public String getMes10() { return (this.mes10 == null) ? "" : this.mes10; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 530 */   public void setMes11(String p) { this.mes11 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 538 */   public String getMes11() { return (this.mes11 == null) ? "" : this.mes11; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 546 */   public void setMes12(String p) { this.mes12 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 554 */   public String getMes12() { return (this.mes12 == null) ? "" : this.mes12; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 562 */   public void setValorMes1(int p) { this.valorMes1 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 570 */   public int getValorMes1() { return this.valorMes1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 578 */   public void setValorMes2(int p) { this.valorMes2 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 586 */   public int getValorMes2() { return this.valorMes2; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 594 */   public void setValorMes3(int p) { this.valorMes3 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 602 */   public int getValorMes3() { return this.valorMes3; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 610 */   public void setValorMes4(int p) { this.valorMes4 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 618 */   public int getValorMes4() { return this.valorMes4; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 626 */   public void setValorMes5(int p) { this.valorMes5 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 634 */   public int getValorMes5() { return this.valorMes5; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 642 */   public void setValorMes6(int p) { this.valorMes6 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 650 */   public int getValorMes6() { return this.valorMes6; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 658 */   public void setValorMes7(int p) { this.valorMes7 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 666 */   public int getValorMes7() { return this.valorMes7; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 674 */   public void setValorMes8(int p) { this.valorMes8 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 682 */   public int getValorMes8() { return this.valorMes8; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 690 */   public void setValorMes9(int p) { this.valorMes9 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 698 */   public int getValorMes9() { return this.valorMes9; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 706 */   public void setValorMes10(int p) { this.valorMes10 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 714 */   public int getValorMes10() { return this.valorMes10; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 722 */   public void setValorMes11(int p) { this.valorMes11 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 730 */   public int getValorMes11() { return this.valorMes11; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 738 */   public void setValorMes12(int p) { this.valorMes12 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 746 */   public int getValorMes12() { return this.valorMes12; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 754 */   public void setEstado(String p) { this.estado = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 762 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 770 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 778 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 786 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 794 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 802 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 810 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 818 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 826 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 831 */   public void setNombreObjetivoEstrategico(String p) { this.nombreObjetivoEstrategico = p; }
/*     */ 
/*     */   
/* 834 */   public String getNombreObjetivoEstrategico() { return (this.nombreObjetivoEstrategico == null) ? "" : this.nombreObjetivoEstrategico; }
/*     */ 
/*     */   
/* 837 */   public void setNombreActividad(String p) { this.nombreActividad = p; }
/*     */ 
/*     */   
/* 840 */   public String getNombreActividad() { return (this.nombreActividad == null) ? "" : this.nombreActividad; }
/*     */ 
/*     */   
/* 843 */   public void setNombreVariablesMedicion(String p) { this.nombreVariablesMedicion = p; }
/*     */ 
/*     */   
/* 846 */   public String getNombreVariablesMedicion() { return (this.nombreVariablesMedicion == null) ? "" : this.nombreVariablesMedicion; }
/*     */ 
/*     */   
/* 849 */   public void setNombreMediosDeVerificacion(String p) { this.nombreMediosDeVerificacion = p; }
/*     */ 
/*     */   
/* 852 */   public String getNombreMediosDeVerificacion() { return (this.nombreMediosDeVerificacion == null) ? "" : this.nombreMediosDeVerificacion; }
/*     */ 
/*     */   
/* 855 */   public void setNombreProyectoInversion(String p) { this.nombreProyectoInversion = p; }
/*     */ 
/*     */   
/* 858 */   public String getNombreProyectoInversion() { return (this.nombreProyectoInversion == null) ? "" : this.nombreProyectoInversion; }
/*     */ 
/*     */   
/* 861 */   public void setNombreProductoProceso(String p) { this.nombreProductoProceso = p; }
/*     */ 
/*     */   
/* 864 */   public String getNombreProductoProceso() { return (this.nombreProductoProceso == null) ? "" : this.nombreProductoProceso; }
/*     */ 
/*     */   
/* 867 */   public void setNombreMetaPlanDeDesarrollo(String p) { this.nombreMetaPlanDeDesarrollo = p; }
/*     */ 
/*     */   
/* 870 */   public String getNombreMetaPlanDeDesarrollo() { return (this.nombreMetaPlanDeDesarrollo == null) ? "" : this.nombreMetaPlanDeDesarrollo; }
/*     */ 
/*     */   
/* 873 */   public void setNombreObjetivoSubsistema(String p) { this.nombreObjetivoSubsistema = p; }
/*     */ 
/*     */   
/* 876 */   public String getNombreObjetivoSubsistema() { return (this.nombreObjetivoSubsistema == null) ? "" : this.nombreObjetivoSubsistema; }
/*     */ 
/*     */   
/* 879 */   public void setNombreMetaProyecto(String p) { this.nombreMetaProyecto = p; }
/*     */ 
/*     */   
/* 882 */   public String getNombreMetaProyecto() { return (this.nombreMetaProyecto == null) ? "" : this.nombreMetaProyecto; }
/*     */ 
/*     */   
/* 885 */   public void setNombreTipoRecurso(String p) { this.nombreTipoRecurso = p; }
/*     */ 
/*     */   
/* 888 */   public String getNombreTipoRecurso() { return (this.nombreTipoRecurso == null) ? "" : this.nombreTipoRecurso; }
/*     */ 
/*     */   
/* 891 */   public void setNombrePrioridadEnProducto(String p) { this.nombrePrioridadEnProducto = p; }
/*     */ 
/*     */   
/* 894 */   public String getNombrePrioridadEnProducto() { return (this.nombrePrioridadEnProducto == null) ? "" : this.nombrePrioridadEnProducto; }
/*     */ 
/*     */   
/* 897 */   public void setNombrePrioridadObjetivo(String p) { this.nombrePrioridadObjetivo = p; }
/*     */ 
/*     */   
/* 900 */   public String getNombrePrioridadObjetivo() { return (this.nombrePrioridadObjetivo == null) ? "" : this.nombrePrioridadObjetivo; }
/*     */ 
/*     */   
/* 903 */   public void setNombreEstado(String p) { this.nombreEstado = p; }
/*     */ 
/*     */   
/* 906 */   public String getNombreEstado() { return (this.nombreEstado == null) ? "" : this.nombreEstado; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\PoaMaestroActividadesDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */