/*     */ package sinco.business;
/*     */ 
/*     *//*     */ 
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
/*     */ 
/*     */ 
/*     */ public class AMAccionesDTO
/*     */ {
/*     */   private int numero;
/*     */   private String fechaGenerada;
/*     */   private int areaImplanta;
/*     */   private int empleadoCliente;
/*     */   private int codigoEstado;
/*     */   private int nivelEscalamiento;
/*     */   private String fechaVigencia;
/*     */   private String fechaEstimadaTerminacion;
/*     */   private String fechaRealTerminacion;
/*     */   private String fechaBaseEscalamientos;
/*     */   private String accion;
/*     */   private String origen;
/*     */   private String proceso;
/*     */   private String subproceso;
/*     */   private String numeral;
/*     */   private String descripcion;
/*     */   private String cumplio;
/*     */   private String implantada;
/*     */   private String revisadaComite;
/*     */   private String funcionario1;
/*     */   private String cargo1;
/*     */   private String fecha1;
/*     */   private String revisadaAuditor;
/*     */   private String funcionario2;
/*     */   private String cargo2;
/*     */   private String fecha2;
/*     */   private String norma;
/*     */   private String justificacion;
/*     */   private String observacionesCierre;
/*     */   private String observacionesCalidad;
/*     */   private String fechaCalidad;
/*     */   private String impacto;
/*     */   private String temaAccion;
/*     */   private int solicitudOrigen;
/*     */   private int codigoCiclo;
/*     */   private int codigoPlan;
/*     */   private int codigoMeta;
/*     */   private String asociado;
/*     */   private String fechaInsercion;
/*     */   private String usuarioInsercion;
/*     */   private String fechaModificacion;
/*     */   private String usuarioModificacion;
/*     */   private String nombreAreaImplanta;
/*     */   private String nombreCodigoEstado;
/*     */   private String nombreAccion;
/*     */   private String nombreOrigen;
/*     */   private String nombreProceso;
/*     */   private String nombreSubproceso;
/*     */   private String nombreNorma;
/*     */   private String nombreImpacto;
/*     */   private String vieja;
/*     */   private String apellidos;
/*     */   private String nombres;
/*     */   
/* 129 */   public void setNumero(int p) { this.numero = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   public int getNumero() { return this.numero; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 145 */   public void setFechaGenerada(String p) { this.fechaGenerada = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 153 */   public String getFechaGenerada() { return (this.fechaGenerada == null) ? "" : this.fechaGenerada; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 161 */   public void setAreaImplanta(int p) { this.areaImplanta = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 169 */   public int getAreaImplanta() { return this.areaImplanta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 177 */   public void setEmpleadoCliente(int p) { this.empleadoCliente = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 185 */   public int getEmpleadoCliente() { return this.empleadoCliente; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 193 */   public void setCodigoEstado(int p) { this.codigoEstado = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 201 */   public int getCodigoEstado() { return this.codigoEstado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 209 */   public void setNivelEscalamiento(int p) { this.nivelEscalamiento = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 217 */   public int getNivelEscalamiento() { return this.nivelEscalamiento; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 225 */   public void setFechaVigencia(String p) { this.fechaVigencia = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 233 */   public String getFechaVigencia() { return (this.fechaVigencia == null) ? "" : this.fechaVigencia; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 241 */   public void setFechaEstimadaTerminacion(String p) { this.fechaEstimadaTerminacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 249 */   public String getFechaEstimadaTerminacion() { return (this.fechaEstimadaTerminacion == null) ? "" : this.fechaEstimadaTerminacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 257 */   public void setFechaRealTerminacion(String p) { this.fechaRealTerminacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 265 */   public String getFechaRealTerminacion() { return (this.fechaRealTerminacion == null) ? "" : this.fechaRealTerminacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 273 */   public void setFechaBaseEscalamientos(String p) { this.fechaBaseEscalamientos = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 281 */   public String getFechaBaseEscalamientos() { return (this.fechaBaseEscalamientos == null) ? "" : this.fechaBaseEscalamientos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 289 */   public void setAccion(String p) { this.accion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 297 */   public String getAccion() { return (this.accion == null) ? "" : this.accion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 305 */   public void setOrigen(String p) { this.origen = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 313 */   public String getOrigen() { return (this.origen == null) ? "" : this.origen; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 321 */   public void setProceso(String p) { this.proceso = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 329 */   public String getProceso() { return (this.proceso == null) ? "" : this.proceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 337 */   public void setSubproceso(String p) { this.subproceso = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 345 */   public String getSubproceso() { return (this.subproceso == null) ? "" : this.subproceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 353 */   public void setNumeral(String p) { this.numeral = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 361 */   public String getNumeral() { return (this.numeral == null) ? "" : this.numeral; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 369 */   public void setDescripcion(String p) { this.descripcion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 377 */   public String getDescripcion() { return (this.descripcion == null) ? "" : this.descripcion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 385 */   public void setCumplio(String p) { this.cumplio = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 393 */   public String getCumplio() { return (this.cumplio == null) ? "" : this.cumplio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 401 */   public void setImplantada(String p) { this.implantada = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 409 */   public String getImplantada() { return (this.implantada == null) ? "" : this.implantada; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 417 */   public void setRevisadaComite(String p) { this.revisadaComite = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 425 */   public String getRevisadaComite() { return (this.revisadaComite == null) ? "" : this.revisadaComite; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 433 */   public void setFuncionario1(String p) { this.funcionario1 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 441 */   public String getFuncionario1() { return (this.funcionario1 == null) ? "" : this.funcionario1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 449 */   public void setCargo1(String p) { this.cargo1 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 457 */   public String getCargo1() { return (this.cargo1 == null) ? "" : this.cargo1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 465 */   public void setFecha1(String p) { this.fecha1 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 473 */   public String getFecha1() { return (this.fecha1 == null) ? "" : this.fecha1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 481 */   public void setRevisadaAuditor(String p) { this.revisadaAuditor = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 489 */   public String getRevisadaAuditor() { return (this.revisadaAuditor == null) ? "" : this.revisadaAuditor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 497 */   public void setFuncionario2(String p) { this.funcionario2 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 505 */   public String getFuncionario2() { return (this.funcionario2 == null) ? "" : this.funcionario2; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 513 */   public void setCargo2(String p) { this.cargo2 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 521 */   public String getCargo2() { return (this.cargo2 == null) ? "" : this.cargo2; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 529 */   public void setFecha2(String p) { this.fecha2 = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 537 */   public String getFecha2() { return (this.fecha2 == null) ? "" : this.fecha2; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 545 */   public void setNorma(String p) { this.norma = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 553 */   public String getNorma() { return (this.norma == null) ? "" : this.norma; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 561 */   public void setJustificacion(String p) { this.justificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 569 */   public String getJustificacion() { return (this.justificacion == null) ? "" : this.justificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 577 */   public void setObservacionesCierre(String p) { this.observacionesCierre = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 585 */   public String getObservacionesCierre() { return (this.observacionesCierre == null) ? "" : this.observacionesCierre; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 593 */   public void setObservacionesCalidad(String p) { this.observacionesCalidad = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 601 */   public String getObservacionesCalidad() { return (this.observacionesCalidad == null) ? "" : this.observacionesCalidad; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 609 */   public void setFechaCalidad(String p) { this.fechaCalidad = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 617 */   public String getFechaCalidad() { return (this.fechaCalidad == null) ? "" : this.fechaCalidad; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 625 */   public void setImpacto(String p) { this.impacto = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 633 */   public String getImpacto() { return (this.impacto == null) ? "" : this.impacto; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 641 */   public void setTemaAccion(String p) { this.temaAccion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 649 */   public String getTemaAccion() { return (this.temaAccion == null) ? "" : this.temaAccion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 657 */   public void setSolicitudOrigen(int p) { this.solicitudOrigen = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 665 */   public int getSolicitudOrigen() { return this.solicitudOrigen; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 673 */   public void setCodigoCiclo(int p) { this.codigoCiclo = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 681 */   public int getCodigoCiclo() { return this.codigoCiclo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 689 */   public void setCodigoPlan(int p) { this.codigoPlan = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 697 */   public int getCodigoPlan() { return this.codigoPlan; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 705 */   public void setCodigoMeta(int p) { this.codigoMeta = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 713 */   public int getCodigoMeta() { return this.codigoMeta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 721 */   public void setAsociado(String p) { this.asociado = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 729 */   public String getAsociado() { return (this.asociado == null) ? "" : this.asociado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 737 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 745 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 753 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 761 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 769 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 777 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 785 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 793 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 798 */   public void setNombreAreaImplanta(String p) { this.nombreAreaImplanta = p; }
/*     */ 
/*     */   
/* 801 */   public String getNombreAreaImplanta() { return (this.nombreAreaImplanta == null) ? "" : this.nombreAreaImplanta; }
/*     */ 
/*     */   
/* 804 */   public void setNombreCodigoEstado(String p) { this.nombreCodigoEstado = p; }
/*     */ 
/*     */   
/* 807 */   public String getNombreEstado() { return (this.nombreCodigoEstado == null) ? "" : this.nombreCodigoEstado; }
/*     */ 
/*     */   
/* 810 */   public void setTipoAccion(String p) { this.nombreAccion = p; }
/*     */ 
/*     */   
/* 813 */   public String getTipoAccion() { return (this.nombreAccion == null) ? "" : this.nombreAccion; }
/*     */ 
/*     */   
/* 816 */   public void setOrigenAccion(String p) { this.nombreOrigen = p; }
/*     */ 
/*     */   
/* 819 */   public String getOrigenAccion() { return (this.nombreOrigen == null) ? "" : this.nombreOrigen; }
/*     */ 
/*     */   
/* 822 */   public void setNombreProceso(String p) { this.nombreProceso = p; }
/*     */ 
/*     */   
/* 825 */   public String getNombreProceso() { return (this.nombreProceso == null) ? "" : this.nombreProceso; }
/*     */ 
/*     */   
/* 828 */   public void setNombreSubproceso(String p) { this.nombreSubproceso = p; }
/*     */ 
/*     */   
/* 831 */   public String getNombreSubproceso() { return (this.nombreSubproceso == null) ? "" : this.nombreSubproceso; }
/*     */ 
/*     */   
/* 834 */   public void setNombreNorma(String p) { this.nombreNorma = p; }
/*     */ 
/*     */   
/* 837 */   public String getNombreNorma() { return (this.nombreNorma == null) ? "" : this.nombreNorma; }
/*     */ 
/*     */   
/* 840 */   public void setNombreImpacto(String p) { this.nombreImpacto = p; }
/*     */ 
/*     */   
/* 843 */   public String getNombreImpacto() { return (this.nombreImpacto == null) ? "" : this.nombreImpacto; }
/*     */ 
/*     */ 
/*     */   
/* 847 */   public String getApellidos() { return (this.apellidos == null) ? "" : this.apellidos; }
/*     */ 
/*     */ 
/*     */   
/* 851 */   public void setApellidos(String apellidos) { this.apellidos = apellidos; }
/*     */ 
/*     */ 
/*     */   
/* 855 */   public String getNombres() { return (this.nombres == null) ? "" : this.nombres; }
/*     */ 
/*     */ 
/*     */   
/* 859 */   public void setNombres(String nombres) { this.nombres = nombres; }
/*     */ 
/*     */ 
/*     */   
/* 863 */   public String getNombre() { return ((this.nombres == null) ? "" : this.nombres) + " " + ((this.apellidos == null) ? "" : this.apellidos); }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\AMAccionesDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */