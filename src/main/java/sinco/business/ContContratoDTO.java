/*      */ package sinco.business;
/*      */ 
/*      */ import sinco.business.ContContratoDTO;
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
/*      */ public class ContContratoDTO
/*      */ {
/*      */   private int consecutivoContrato;
/*      */   private double valor;
/*      */   private int numeroEstudio;
/*      */   private String numeroContrato;
/*      */   private int codigoSucursal;
/*      */   private long cdpN;
/*      */   private String nombreContratista;
/*      */   private long nrocc;
/*      */   private String email;
/*      */   private String expedida;
/*      */   private String expedidaMunicipio;
/*      */   private String regimenTributario;
/*      */   private String supervisor;
/*      */   private String telefonoCelular;
/*      */   private String plazoEjecucion;
/*      */   private String valorLetra;
/*      */   private String valorContratoLetra;
/*      */   private double valorNumero;
/*      */   private String vigencia;
/*      */   private String direccion;
/*      */   private String fechaInicio;
/*      */   private String fechaFinal;
/*      */   private String fechaContrato;
/*      */   private String codigoRp;
/*      */   private String tipoDocInterventor;
/*      */   private long docInterventor;
/*      */   private String nombreInterventor;
/*      */   private String estado;
/*      */   private String usuarioInsercion;
/*      */   private String fechaInsercion;
/*      */   private String usuarioModificacion;
/*      */   private String fechaModificacion;
/*      */   private String nombreSucursal;
/*      */   private String nombreMunicipio;
/*      */   private String departamento;
/*      */   private String municipio;
/*      */   private String codigoCertificado;
/*      */   private String tipoContrato;
/*      */   private String formaContrato;
/*      */   private String objetoContratar;
/*      */   private String formaPago;
/*      */   private String apellidosInterventor;
/*      */   private String tarifaDescripcion;
/*      */   private String modificatorios;
/*      */   private double valorLiquidacion;
/*      */   private double valorCancelado;
/*      */   private String fechaLiquidacion;
/*      */   private String nombreEstado;
/*      */   private String impuestos;
/*      */   private String polizas;
/*      */   private String actaInicio;
/*      */   private String imputaciones;
/*      */   private String condicionesEspeciales;
/*      */   private String codigoImputacion;
/*      */   
/*  128 */   public void setConsecutivoContrato(int p) { this.consecutivoContrato = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  136 */   public int getConsecutivoContrato() { return this.consecutivoContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  143 */   public void setValor(double valor) { this.valor = valor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  150 */   public double getValor() { return this.valor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  158 */   public void setNumeroEstudio(int p) { this.numeroEstudio = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  166 */   public int getNumeroEstudio() { return this.numeroEstudio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  174 */   public void setNumeroContrato(String p) { this.numeroContrato = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  182 */   public String getNumeroContrato() { return (this.numeroContrato == null) ? "" : this.numeroContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  190 */   public void setCodigoSucursal(int p) { this.codigoSucursal = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  198 */   public int getCodigoSucursal() { return this.codigoSucursal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  206 */   public void setNombreContratista(String p) { this.nombreContratista = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  214 */   public long getcdpN() { return this.cdpN; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  222 */   public void setcdpN(long p) { this.cdpN = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  230 */   public void setnrocc(long p) { this.nrocc = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  238 */   public long getnrocc() { return this.nrocc; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  246 */   public void setEmail(String p) { this.email = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  254 */   public String getEmail() { return (this.email == null) ? "" : this.email; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  262 */   public void setExpedida(String p) { this.expedida = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  270 */   public String getExpedida() { return (this.expedida == null) ? "" : this.expedida; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  278 */   public void setSupervisor(String p) { this.supervisor = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  286 */   public String getSupervisor() { return (this.supervisor == null) ? "" : this.supervisor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  294 */   public void setPlazoEjecucion(String p) { this.plazoEjecucion = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  302 */   public String getPlazoEjecucion() { return (this.plazoEjecucion == null) ? "" : this.plazoEjecucion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  310 */   public void setValorLetra(String p) { this.valorLetra = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  318 */   public String getValorLetra() { return (this.valorLetra == null) ? "" : this.valorLetra; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  326 */   public void setValorNumero(double p) { this.valorNumero = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  333 */   public double getValorNumero() { return this.valorNumero; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  341 */   public void setDireccion(String p) { this.direccion = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  349 */   public String getDireccion() { return (this.direccion == null) ? "" : this.direccion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  357 */   public void setFechaInicio(String p) { this.fechaInicio = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  365 */   public String getFechaInicio() { return (this.fechaInicio == null) ? "" : this.fechaInicio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  373 */   public void setFechaFinal(String p) { this.fechaFinal = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  381 */   public String getFechaFinal() { return (this.fechaFinal == null) ? "" : this.fechaFinal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  388 */   public void setFechaContrato(String fechaContrato) { this.fechaContrato = fechaContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  395 */   public String getFechaContrato() { return (this.fechaContrato == null) ? "" : this.fechaContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  403 */   public void setCodigoRp(String p) { this.codigoRp = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  411 */   public String getCodigoRp() { return (this.codigoRp == null) ? "" : this.codigoRp; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  419 */   public void setTipoDocInterventor(String p) { this.tipoDocInterventor = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  427 */   public String getTipoDocInterventor() { return (this.tipoDocInterventor == null) ? "" : this.tipoDocInterventor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  435 */   public void setNombreInterventor(String nombreInterventor) { this.nombreInterventor = nombreInterventor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  442 */   public String getNombreInterventor() { return (this.nombreInterventor == null) ? "" : this.nombreInterventor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  450 */   public void setEstado(String p) { this.estado = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  458 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  466 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  474 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  482 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  490 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  498 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  506 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  514 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  522 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  529 */   public void setNombreSucursal(String nombreSucursal) { this.nombreSucursal = nombreSucursal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  536 */   public String getNombreSucursal() { return (this.nombreSucursal == null) ? "" : this.nombreSucursal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  543 */   public void setNombreMunicipio(String nombreMunicipio) { this.nombreMunicipio = nombreMunicipio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  550 */   public String getNombreMunicipio() { return (this.nombreMunicipio == null) ? "" : this.nombreMunicipio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  557 */   public void setDepartamento(String departamento) { this.departamento = departamento; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  564 */   public String getDepartamento() { return (this.departamento == null) ? "" : this.departamento; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  571 */   public void setMunicipio(String municipio) { this.municipio = municipio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  578 */   public String getMunicipio() { return (this.municipio == null) ? "" : this.municipio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  585 */   public void setCodigoCertificado(String codigoCertificado) { this.codigoCertificado = codigoCertificado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  592 */   public String getCodigoCertificado() { return (this.codigoCertificado == null) ? "" : this.codigoCertificado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  599 */   public void setTipoContrato(String tipoContrato) { this.tipoContrato = tipoContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  606 */   public String getTipoContrato() { return (this.tipoContrato == null) ? "" : this.tipoContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  613 */   public void setFormaContrato(String formaContrato) { this.formaContrato = formaContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  620 */   public String getFormaContrato() { return (this.formaContrato == null) ? "" : this.formaContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  627 */   public void setObjetoContratar(String objetoContratar) { this.objetoContratar = objetoContratar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  634 */   public String getObjetoContratar() { return (this.objetoContratar == null) ? "" : this.objetoContratar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  641 */   public void setFormaPago(String formaPago) { this.formaPago = formaPago; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  648 */   public String getFormaPago() { return (this.formaPago == null) ? "" : this.formaPago; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  655 */   public void setApellidosInterventor(String apellidosInterventor) { this.apellidosInterventor = apellidosInterventor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  662 */   public String getApellidosInterventor() { return (this.apellidosInterventor == null) ? "" : this.apellidosInterventor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  669 */   public void setTarifaDescripcion(String tarifaDescripcion) { this.tarifaDescripcion = tarifaDescripcion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  676 */   public String getTarifaDescripcion() { return (this.tarifaDescripcion == null) ? "" : this.tarifaDescripcion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  683 */   public void setModificatorios(String modificatorios) { this.modificatorios = modificatorios; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  690 */   public String getModificatorios() { return (this.modificatorios == null) ? "" : this.modificatorios; }
/*      */ 
/*      */ 
/*      */   
/*  694 */   public double getValorLiquidacion() { return this.valorLiquidacion; }
/*      */ 
/*      */ 
/*      */   
/*  698 */   public void setValorLiquidacion(double valorLiquidacion) { this.valorLiquidacion = valorLiquidacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  705 */   public void setValorCancelado(double valorCancelado) { this.valorCancelado = valorCancelado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  712 */   public double getValorCancelado() { return this.valorCancelado; }
/*      */ 
/*      */ 
/*      */   
/*  716 */   public String getFechaLiquidacion() { return (this.fechaLiquidacion == null) ? "" : this.fechaLiquidacion; }
/*      */ 
/*      */ 
/*      */   
/*  720 */   public void setFechaLiquidacion(String fechaLiquidacion) { this.fechaLiquidacion = fechaLiquidacion; }
/*      */ 
/*      */ 
/*      */   
/*  724 */   public void setNombreEstado(String nombreEstado) { this.nombreEstado = nombreEstado; }
/*      */ 
/*      */ 
/*      */   
/*  728 */   public String getNombreEstado() { return (this.nombreEstado == null) ? "" : this.nombreEstado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  735 */   public void setImpuestos(String impuestos) { this.impuestos = impuestos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  742 */   public String getImpuestos() { return (this.impuestos == null) ? "" : this.impuestos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  749 */   public void setPolizas(String polizas) { this.polizas = polizas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  756 */   public String getPolizas() { return (this.polizas == null) ? "" : this.polizas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  763 */   public void setActaInicio(String actaInicio) { this.actaInicio = actaInicio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  770 */   public String getActaInicio() { return (this.actaInicio == null) ? "" : this.actaInicio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  777 */   public void setImputaciones(String imputaciones) { this.imputaciones = imputaciones; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  784 */   public String getImputaciones() { return (this.imputaciones == null) ? "" : this.imputaciones; }
/*      */ 
/*      */ 
/*      */   
/*  788 */   public String getCondicionesEspeciales() { return (this.condicionesEspeciales == null) ? "" : this.condicionesEspeciales; }
/*      */ 
/*      */ 
/*      */   
/*  792 */   public void setCondicionesEspeciales(String condicionesEspeciales) { this.condicionesEspeciales = condicionesEspeciales; }
/*      */ 
/*      */ 
/*      */   
/*  796 */   public long getDocInterventor() { return this.docInterventor; }
/*      */ 
/*      */ 
/*      */   
/*  800 */   public void setDocInterventor(long docInterventor) { this.docInterventor = docInterventor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  809 */   public String getnombreContratista() { return (this.nombreContratista == null) ? "" : this.nombreContratista; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  817 */   public void setnombreContratista(String nombreContratista) { this.nombreContratista = nombreContratista; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  827 */   public long cdpN() { return this.cdpN; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  835 */   public void setcdpN(int int1) { this.cdpN = this.cdpN; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  843 */   public void setplazoEjecucion(String p) { this.plazoEjecucion = p; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  851 */   public String getplazoEjecucion() { return (this.plazoEjecucion == null) ? "" : this.plazoEjecucion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  859 */   public long nrocc() { return this.nrocc; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  867 */   public void setnrocc(int nrocc) { this.nrocc = nrocc; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  875 */   public String getemail() { return (this.email == null) ? "" : this.email; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  884 */   public void setemail(String email) { this.email = email; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  893 */   public String getexpedida() { return (this.expedida == null) ? "" : this.expedida; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  901 */   public void setexpedida(String expedida) { this.expedida = expedida; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  910 */   public String getregimenTributario() { return (this.regimenTributario == null) ? "" : this.regimenTributario; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  918 */   public void setregimenTributario(String regimenTributario) { this.regimenTributario = regimenTributario; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  926 */   public String getsupervisor() { return (this.supervisor == null) ? "" : this.supervisor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  934 */   public void setsupervisor(String supervisor) { this.supervisor = supervisor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  944 */   public String gettelefonoCelular() { return (this.telefonoCelular == null) ? "" : this.telefonoCelular; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  952 */   public void settelefonoCelular(String telefonoCelular) { this.telefonoCelular = telefonoCelular; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  961 */   public String getvalorLetra() { return (this.valorLetra == null) ? "" : this.valorLetra; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  969 */   public void setvalorLetra(String valorLetra) { this.valorLetra = valorLetra; }
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
/*  980 */   public double getvalorNumero() { return this.valorNumero; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  988 */   public void setvalorNumero(double valorNumero) { this.valorNumero = valorNumero; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  997 */   public String getvigencia() { return (this.vigencia == null) ? "" : this.vigencia; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1005 */   public void setvigencia(String vigencia) { this.vigencia = vigencia; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1014 */   public String getdireccion() { return (this.direccion == null) ? "" : this.direccion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1022 */   public void setdireccion(String direccion) { this.direccion = direccion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1029 */   public void setValorContratoLetra(String valorContratoLetra) { this.valorContratoLetra = valorContratoLetra; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1036 */   public String getValorContratoLetra() { return (this.valorContratoLetra == null) ? "" : this.valorContratoLetra; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1043 */   public String getExpedidaMunicipio() { return (this.expedidaMunicipio == null) ? "" : this.expedidaMunicipio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1050 */   public void setExpedidaMunicipio(String expedidaMunicipio) { this.expedidaMunicipio = expedidaMunicipio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1057 */   public String getCodigoImputacion() { return (this.codigoImputacion == null) ? "" : this.codigoImputacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1064 */   public void setCodigoImputacion(String codigoImputacion) { this.codigoImputacion = codigoImputacion; }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\ContContratoDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */