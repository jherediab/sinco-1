/*      */ package sinco.business;
/*      */ 
/*      */ import sinco.business.IndicadoresDTO;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class IndicadoresDTO
/*      */ {
/*      */   private String codigoIndicador;
/*      */   private String nombreIndicador;
/*      */   private int proceso;
/*      */   private int ciclo;
/*      */   private int area;
/*      */   private int objetivoEstrategico;
/*      */   private String indicadorAcuerdo;
/*      */   private String tipoIndicador;
/*      */   private String proyectoInversion;
/*      */   private int metaPlanDeDesarrollo;
/*      */   private int metaProyecto;
/*      */   private String objetivoIndicador;
/*      */   private String prioridadEnProducto;
/*      */   private String unidadMedida;
/*      */   private String frecuenciaMedicion;
/*      */   private String formula;
/*      */   private String variable1;
/*      */   private String variable2;
/*      */   private String variable3;
/*      */   private String variable4;
/*      */   private String variable5;
/*      */   private String mes1;
/*      */   private String mes2;
/*      */   private String mes3;
/*      */   private String mes4;
/*      */   private String mes5;
/*      */   private String mes6;
/*      */   private String mes7;
/*      */   private String mes8;
/*      */   private String mes9;
/*      */   private String mes10;
/*      */   private String mes11;
/*      */   private String mes12;
/*      */   private String productoPMR;
/*      */   private int valormes1;
/*      */   private int valormes2;
/*      */   private int valormes3;
/*      */   private int valormes4;
/*      */   private int valormes5;
/*      */   private int valormes6;
/*      */   private int valormes7;
/*      */   private int valormes8;
/*      */   private int valormes9;
/*      */   private int valormes10;
/*      */   private int valormes11;
/*      */   private int valormes12;
/*      */   private String estado;
/*      */   private String fechaInsercion;
/*      */   private String usuarioInsercion;
/*      */   private String fechaModificacion;
/*      */   private String usuarioModificacion;
/*      */   private String nombreProceso;
/*      */   private String nombreCiclo;
/*      */   private String nombreArea;
/*      */   private String nombreObjetivoEstrategico;
/*      */   private String nombreTipoIndicador;
/*      */   private String nombrePrioridadEnProducto;
/*      */   private String nombreProyectoInversion;
/*      */   private String nombreMetaPlanDeDesarrollo;
/*      */   private String nombreMetaProyecto;
/*      */   private String nombreUnidadMedida;
/*      */   private String nombreFrecuenciaMedicion;
/*      */   private String nombreEstado;
/*      */   private String nombreProductoPMR;
/*      */   private int metaTotalProgramada;
/*      */   private int metaEstaVigencia;
/*      */   private int acumuladoVigencias;
/*      */   private int acumuladoEstaVigencia;
/*      */   private int logroTotal;
/*      */   private int logroAcumuladoTotal;
/*      */   
/*  156 */   public void setCodigoIndicador(String p) { this.codigoIndicador = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  164 */   public String getCodigoIndicador() { return this.codigoIndicador; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  172 */   public void setNombreIndicador(String p) { this.nombreIndicador = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  180 */   public String getNombreIndicador() { return (this.nombreIndicador == null) ? "" : this.nombreIndicador; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  188 */   public void setProceso(int p) { this.proceso = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  196 */   public int getProceso() { return this.proceso; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  204 */   public void setCiclo(int p) { this.ciclo = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  212 */   public int getCiclo() { return this.ciclo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  220 */   public void setArea(int p) { this.area = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  228 */   public int getArea() { return this.area; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  236 */   public void setObjetivoEstrategico(int p) { this.objetivoEstrategico = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  244 */   public int getObjetivoEstrategico() { return this.objetivoEstrategico; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  252 */   public void setIndicadorAcuerdo(String p) { this.indicadorAcuerdo = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  260 */   public String getIndicadorAcuerdo() { return (this.indicadorAcuerdo == null) ? "" : this.indicadorAcuerdo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  268 */   public void setTipoIndicador(String p) { this.tipoIndicador = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  278 */   public String getTipoIndicador() { return (this.tipoIndicador == null) ? "" : this.tipoIndicador; }
/*      */ 
/*      */ 
/*      */   
/*  282 */   public String getPrioridadEnProducto() { return (this.prioridadEnProducto == null) ? "" : this.prioridadEnProducto; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  290 */   public void setProyectoInversion(String p) { this.proyectoInversion = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  298 */   public String getProyectoInversion() { return (this.proyectoInversion == null) ? "" : this.proyectoInversion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  306 */   public void setMetaPlanDeDesarrollo(int p) { this.metaPlanDeDesarrollo = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  314 */   public int getMetaPlanDeDesarrollo() { return this.metaPlanDeDesarrollo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  322 */   public void setMetaProyecto(int p) { this.metaProyecto = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  330 */   public int getMetaProyecto() { return this.metaProyecto; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  338 */   public void setObjetivoIndicador(String p) { this.objetivoIndicador = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  346 */   public String getObjetivoIndicador() { return (this.objetivoIndicador == null) ? "" : this.objetivoIndicador; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  354 */   public void setPrioridadEnProducto(String p) { this.prioridadEnProducto = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  365 */   public void setFrecuenciaMedicion(String p) { this.frecuenciaMedicion = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  373 */   public String getFrecuenciaMedicion() { return (this.frecuenciaMedicion == null) ? "" : this.frecuenciaMedicion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  381 */   public void setFormula(String p) { this.formula = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  389 */   public String getFormula() { return (this.formula == null) ? "" : this.formula; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  397 */   public void setVariable1(String p) { this.variable1 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  405 */   public String getVariable1() { return (this.variable1 == null) ? "" : this.variable1; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  413 */   public void setVariable2(String p) { this.variable2 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  421 */   public String getVariable2() { return (this.variable2 == null) ? "" : this.variable2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  429 */   public void setVariable3(String p) { this.variable3 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  437 */   public String getVariable3() { return (this.variable3 == null) ? "" : this.variable3; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  445 */   public void setVariable4(String p) { this.variable4 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  453 */   public String getVariable4() { return (this.variable4 == null) ? "" : this.variable4; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  461 */   public void setVariable5(String p) { this.variable5 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  469 */   public String getVariable5() { return (this.variable5 == null) ? "" : this.variable5; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  477 */   public void setMes1(String p) { this.mes1 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  485 */   public String getMes1() { return (this.mes1 == null) ? "" : this.mes1; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  493 */   public void setMes2(String p) { this.mes2 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  501 */   public String getMes2() { return (this.mes2 == null) ? "" : this.mes2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  509 */   public void setMes3(String p) { this.mes3 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  517 */   public String getMes3() { return (this.mes3 == null) ? "" : this.mes3; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  525 */   public void setMes4(String p) { this.mes4 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  533 */   public String getMes4() { return (this.mes4 == null) ? "" : this.mes4; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  541 */   public void setMes5(String p) { this.mes5 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  549 */   public String getMes5() { return (this.mes5 == null) ? "" : this.mes5; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  557 */   public void setMes6(String p) { this.mes6 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  565 */   public String getMes6() { return (this.mes6 == null) ? "" : this.mes6; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  573 */   public void setMes7(String p) { this.mes7 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  581 */   public String getMes7() { return (this.mes7 == null) ? "" : this.mes7; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  589 */   public void setMes8(String p) { this.mes8 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  597 */   public String getMes8() { return (this.mes8 == null) ? "" : this.mes8; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  605 */   public void setMes9(String p) { this.mes9 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  613 */   public String getMes9() { return (this.mes9 == null) ? "" : this.mes9; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  621 */   public void setMes10(String p) { this.mes10 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  629 */   public String getMes10() { return (this.mes10 == null) ? "" : this.mes10; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  637 */   public void setMes11(String p) { this.mes11 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  645 */   public String getMes11() { return (this.mes11 == null) ? "" : this.mes11; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  653 */   public void setMes12(String p) { this.mes12 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  661 */   public String getMes12() { return (this.mes12 == null) ? "" : this.mes12; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  669 */   public void setValormes1(int p) { this.valormes1 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  677 */   public int getValormes1() { return this.valormes1; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  685 */   public void setValormes2(int p) { this.valormes2 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  693 */   public int getValormes2() { return this.valormes2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  701 */   public void setValormes3(int p) { this.valormes3 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  709 */   public int getValormes3() { return this.valormes3; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  717 */   public void setValormes4(int p) { this.valormes4 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  725 */   public int getValormes4() { return this.valormes4; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  733 */   public void setValormes5(int p) { this.valormes5 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  741 */   public int getValormes5() { return this.valormes5; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  749 */   public void setValormes6(int p) { this.valormes6 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  757 */   public int getValormes6() { return this.valormes6; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  765 */   public void setValormes7(int p) { this.valormes7 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  773 */   public int getValormes7() { return this.valormes7; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  781 */   public void setValormes8(int p) { this.valormes8 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  789 */   public int getValormes8() { return this.valormes8; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  797 */   public void setValormes9(int p) { this.valormes9 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  805 */   public int getValormes9() { return this.valormes9; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  813 */   public void setValormes10(int p) { this.valormes10 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  821 */   public int getValormes10() { return this.valormes10; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  829 */   public void setValormes11(int p) { this.valormes11 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  837 */   public int getValormes11() { return this.valormes11; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  845 */   public void setValormes12(int p) { this.valormes12 = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  853 */   public int getValormes12() { return this.valormes12; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  861 */   public void setEstado(String p) { this.estado = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  869 */   public String getProductoPMR() { return (this.productoPMR == null) ? "" : this.productoPMR; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  874 */   public void setProductoPMR(String p) { this.productoPMR = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  882 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  891 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  899 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  907 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  915 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  923 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  931 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  939 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  947 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  952 */   public void setNombreProceso(String p) { this.nombreProceso = p; }
/*      */ 
/*      */   
/*  955 */   public String getNombreProceso() { return (this.nombreProceso == null) ? "" : this.nombreProceso; }
/*      */ 
/*      */   
/*  958 */   public void setNombreCiclo(String p) { this.nombreCiclo = p; }
/*      */ 
/*      */   
/*  961 */   public String getNombreCiclo() { return (this.nombreCiclo == null) ? "" : this.nombreCiclo; }
/*      */ 
/*      */   
/*  964 */   public void setNombreArea(String p) { this.nombreArea = p; }
/*      */ 
/*      */   
/*  967 */   public String getNombreArea() { return (this.nombreArea == null) ? "" : this.nombreArea; }
/*      */ 
/*      */   
/*  970 */   public void setNombreObjetivoEstrategico(String p) { this.nombreObjetivoEstrategico = p; }
/*      */ 
/*      */   
/*  973 */   public String getNombreObjetivoEstrategico() { return (this.nombreObjetivoEstrategico == null) ? "" : this.nombreObjetivoEstrategico; }
/*      */ 
/*      */   
/*  976 */   public void setNombreTipoIndicador(String p) { this.nombreTipoIndicador = p; }
/*      */ 
/*      */   
/*  979 */   public void setNombrePrioridadEnProducto(String p) { this.nombrePrioridadEnProducto = p; }
/*      */ 
/*      */   
/*  982 */   public String getNombrePrioridadEnProducto() { return (this.nombrePrioridadEnProducto == null) ? "" : this.nombrePrioridadEnProducto; }
/*      */ 
/*      */   
/*  985 */   public String getNombreTipoIndicador() { return (this.nombreTipoIndicador == null) ? "" : this.nombreTipoIndicador; }
/*      */ 
/*      */   
/*  988 */   public void setNombreProyectoInversion(String p) { this.nombreProyectoInversion = p; }
/*      */ 
/*      */   
/*  991 */   public String getNombreProyectoInversion() { return (this.nombreProyectoInversion == null) ? "" : this.nombreProyectoInversion; }
/*      */ 
/*      */   
/*  994 */   public void setNombreMetaPlanDeDesarrollo(String p) { this.nombreMetaPlanDeDesarrollo = p; }
/*      */ 
/*      */   
/*  997 */   public String getNombreMetaPlanDeDesarrollo() { return (this.nombreMetaPlanDeDesarrollo == null) ? "" : this.nombreMetaPlanDeDesarrollo; }
/*      */ 
/*      */   
/* 1000 */   public void setNombreMetaProyecto(String p) { this.nombreMetaProyecto = p; }
/*      */ 
/*      */   
/* 1003 */   public String getNombreMetaProyecto() { return (this.nombreMetaProyecto == null) ? "" : this.nombreMetaProyecto; }
/*      */ 
/*      */   
/* 1006 */   public void setNombreUnidadMedida(String p) { this.nombreUnidadMedida = p; }
/*      */ 
/*      */   
/* 1009 */   public String getNombreUnidadMedida() { return (this.nombreUnidadMedida == null) ? "" : this.nombreUnidadMedida; }
/*      */ 
/*      */   
/* 1012 */   public void setNombreFrecuenciaMedicion(String p) { this.nombreFrecuenciaMedicion = p; }
/*      */ 
/*      */   
/* 1015 */   public String getNombreFrecuenciaMedicion() { return (this.nombreFrecuenciaMedicion == null) ? "" : this.nombreFrecuenciaMedicion; }
/*      */ 
/*      */   
/* 1018 */   public void setNombreEstado(String p) { this.nombreEstado = p; }
/*      */ 
/*      */   
/* 1021 */   public String getNombreEstado() { return (this.nombreEstado == null) ? "" : this.nombreEstado; }
/*      */ 
/*      */   
/* 1024 */   public void setNombreProductoPMR(String p) { this.nombreProductoPMR = p; }
/*      */ 
/*      */   
/* 1027 */   public String getNombreProductoPMR() { return (this.nombreProductoPMR == null) ? "" : this.nombreProductoPMR; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1034 */   public int getMetaTotalProgramada() { return this.metaTotalProgramada; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1041 */   public void setMetaTotalProgramada(int metaTotalProgramada) { this.metaTotalProgramada = metaTotalProgramada; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1048 */   public int getMetaEstaVigencia() { return this.metaEstaVigencia; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1055 */   public void setMetaEstaVigencia(int metaEstaVigencia) { this.metaEstaVigencia = metaEstaVigencia; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1062 */   public int getAcumuladoVigencias() { return this.acumuladoVigencias; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1069 */   public void setAcumuladoVigencias(int acumuladoVigencias) { this.acumuladoVigencias = acumuladoVigencias; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1076 */   public int getAcumuladoEstaVigencia() { return this.acumuladoEstaVigencia; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1083 */   public void setAcumuladoEstaVigencia(int acumuladoEstaVigencia) { this.acumuladoEstaVigencia = acumuladoEstaVigencia; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1090 */   public int getLogroTotal() { return this.logroTotal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1097 */   public void setLogroTotal(int logroTotal) { this.logroTotal = logroTotal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1104 */   public int getLogroAcumuladoTotal() { return this.logroAcumuladoTotal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1111 */   public void setLogroAcumuladoTotal(int logroAcumuladoTotal) { this.logroAcumuladoTotal = logroAcumuladoTotal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1118 */   public String getUnidadMedida() { return (this.unidadMedida == null) ? "" : this.unidadMedida; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1125 */   public void setUnidadMedida(String unidadMedida) { this.unidadMedida = unidadMedida; }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\IndicadoresDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */