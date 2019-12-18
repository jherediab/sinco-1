/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.PrcProcedimientoDTO;
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
/*     */ public class PrcProcedimientoDTO
/*     */ {
/*     */   private int idProcedimiento;
/*     */   private int procesoId;
/*     */   private int subprocesoId;
/*     */   private int servicioUnidadId;
/*     */   private int codigoEmpleado;
/*     */   private String objetivo;
/*     */   private String alcance;
/*     */   private String definiciones;
/*     */   private String concepto;
/*     */   private String estado;
/*     */   private int AnterioresText;
/*     */   private int Proveedores;
/*     */   private int Entradas;
/*     */   private int Clientes;
/*     */   private int Salidas;
/*     */   private int codigoEmpleadoDescPlan;
/*     */   private int codigoEmpleadoDescHac;
/*     */   private int codigoEmpleadoDescVer;
/*     */   private int codigoEmpleadoDescAct;
/*     */   private String tipoRecurso;
/*     */   private String DescRecurso;
/*     */   private String usuarioInsercion;
/*     */   private String fechaInsercion;
/*     */   private String usuarioModificacion;
/*     */   private String fechaModificacion;
/*     */   private String nombreCodigoEmpleado;
/*     */   private String nombreEstado;
/*     */   private String nombreAnterioresText;
/*     */   private String nombreProveedores;
/*     */   private String nombreEntradas;
/*     */   private String nombreClientes;
/*     */   private String nombreSalidas;
/*     */   private String nombreCodigoEmpleadoDescPlan;
/*     */   private String nombreCodigoEmpleadoDescHac;
/*     */   private String nombreCodigoEmpleadoDescVer;
/*     */   private String nombreCodigoEmpleadoDescAct;
/*     */   private String nombreTipoRecurso;
/*     */   private String nombrePoliticas;
/*     */   
/*  88 */   public String getDescRecurso() { return this.DescRecurso; }
/*     */ 
/*     */ 
/*     */   
/*  92 */   public void setDescRecurso(String descRecurso) { this.DescRecurso = descRecurso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public void setIdProcedimiento(int p) { this.idProcedimiento = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public int getIdProcedimiento() { return this.idProcedimiento; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public void setProcesoId(int p) { this.procesoId = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public int getProcesoId() { return this.procesoId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   public void setSubprocesoId(int p) { this.subprocesoId = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   public int getSubprocesoId() { return this.subprocesoId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public void setServicioUnidadId(int p) { this.servicioUnidadId = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   public int getServicioUnidadId() { return this.servicioUnidadId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 164 */   public void setCodigoEmpleado(int p) { this.codigoEmpleado = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 172 */   public int getCodigoEmpleado() { return this.codigoEmpleado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 180 */   public void setObjetivo(String p) { this.objetivo = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 188 */   public String getObjetivo() { return (this.objetivo == null) ? "" : this.objetivo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 196 */   public void setAlcance(String p) { this.alcance = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 204 */   public String getAlcance() { return (this.alcance == null) ? "" : this.alcance; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 212 */   public void setDefiniciones(String p) { this.definiciones = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 220 */   public String getDefiniciones() { return (this.definiciones == null) ? "" : this.definiciones; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 228 */   public void setConcepto(String p) { this.concepto = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 236 */   public String getConcepto() { return (this.concepto == null) ? "" : this.concepto; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 244 */   public void setEstado(String p) { this.estado = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 252 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 260 */   public void setAnterioresText(int p) { this.AnterioresText = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 268 */   public int getAnterioresText() { return this.AnterioresText; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 276 */   public void setProveedores(int p) { this.Proveedores = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 284 */   public int getProveedores() { return this.Proveedores; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 292 */   public void setEntradas(int p) { this.Entradas = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 300 */   public int getEntradas() { return this.Entradas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 308 */   public void setClientes(int p) { this.Clientes = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 316 */   public int getClientes() { return this.Clientes; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 324 */   public void setSalidas(int p) { this.Salidas = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 332 */   public int getSalidas() { return this.Salidas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 340 */   public void setCodigoEmpleadoDescPlan(int p) { this.codigoEmpleadoDescPlan = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 348 */   public int getCodigoEmpleadoDescPlan() { return this.codigoEmpleadoDescPlan; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 356 */   public void setCodigoEmpleadoDescHac(int p) { this.codigoEmpleadoDescHac = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 364 */   public int getCodigoEmpleadoDescHac() { return this.codigoEmpleadoDescHac; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 372 */   public void setCodigoEmpleadoDescVer(int p) { this.codigoEmpleadoDescVer = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 380 */   public int getCodigoEmpleadoDescVer() { return this.codigoEmpleadoDescVer; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 388 */   public void setCodigoEmpleadoDescAct(int p) { this.codigoEmpleadoDescAct = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 396 */   public int getCodigoEmpleadoDescAct() { return this.codigoEmpleadoDescAct; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 404 */   public void setTipoRecurso(String p) { this.tipoRecurso = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 412 */   public String getTipoRecurso() { return (this.tipoRecurso == null) ? "" : this.tipoRecurso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 420 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 428 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 436 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 444 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 452 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 460 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 468 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 476 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 481 */   public void setNombreCodigoEmpleado(String p) { this.nombreCodigoEmpleado = p; }
/*     */ 
/*     */   
/* 484 */   public String getNombreCodigoEmpleado() { return (this.nombreCodigoEmpleado == null) ? "" : this.nombreCodigoEmpleado; }
/*     */ 
/*     */   
/* 487 */   public void setNombreEstado(String p) { this.nombreEstado = p; }
/*     */ 
/*     */   
/* 490 */   public String getNombreEstado() { return (this.nombreEstado == null) ? "" : this.nombreEstado; }
/*     */ 
/*     */   
/* 493 */   public void setNombreAnterioresText(String p) { this.nombreAnterioresText = p; }
/*     */ 
/*     */   
/* 496 */   public String getNombreAnterioresText() { return (this.nombreAnterioresText == null) ? "" : this.nombreAnterioresText; }
/*     */ 
/*     */   
/* 499 */   public void setNombreProveedores(String p) { this.nombreProveedores = p; }
/*     */ 
/*     */   
/* 502 */   public String getNombreProveedores() { return (this.nombreProveedores == null) ? "" : this.nombreProveedores; }
/*     */ 
/*     */   
/* 505 */   public void setNombreEntradas(String p) { this.nombreEntradas = p; }
/*     */ 
/*     */   
/* 508 */   public String getNombreEntradas() { return (this.nombreEntradas == null) ? "" : this.nombreEntradas; }
/*     */ 
/*     */   
/* 511 */   public void setNombreClientes(String p) { this.nombreClientes = p; }
/*     */ 
/*     */   
/* 514 */   public String getNombreClientes() { return (this.nombreClientes == null) ? "" : this.nombreClientes; }
/*     */ 
/*     */   
/* 517 */   public void setNombreSalidas(String p) { this.nombreSalidas = p; }
/*     */ 
/*     */   
/* 520 */   public String getNombreSalidas() { return (this.nombreSalidas == null) ? "" : this.nombreSalidas; }
/*     */ 
/*     */   
/* 523 */   public void setNombreCodigoEmpleadoDescPlan(String p) { this.nombreCodigoEmpleadoDescPlan = p; }
/*     */ 
/*     */   
/* 526 */   public String getNombreCodigoEmpleadoDescPlan() { return (this.nombreCodigoEmpleadoDescPlan == null) ? "" : this.nombreCodigoEmpleadoDescPlan; }
/*     */ 
/*     */   
/* 529 */   public void setNombreCodigoEmpleadoDescHac(String p) { this.nombreCodigoEmpleadoDescHac = p; }
/*     */ 
/*     */   
/* 532 */   public String getNombreCodigoEmpleadoDescHac() { return (this.nombreCodigoEmpleadoDescHac == null) ? "" : this.nombreCodigoEmpleadoDescHac; }
/*     */ 
/*     */   
/* 535 */   public void setNombreCodigoEmpleadoDescVer(String p) { this.nombreCodigoEmpleadoDescVer = p; }
/*     */ 
/*     */   
/* 538 */   public String getNombreCodigoEmpleadoDescVer() { return (this.nombreCodigoEmpleadoDescVer == null) ? "" : this.nombreCodigoEmpleadoDescVer; }
/*     */ 
/*     */   
/* 541 */   public void setNombreCodigoEmpleadoDescAct(String p) { this.nombreCodigoEmpleadoDescAct = p; }
/*     */ 
/*     */   
/* 544 */   public String getNombreCodigoEmpleadoDescAct() { return (this.nombreCodigoEmpleadoDescAct == null) ? "" : this.nombreCodigoEmpleadoDescAct; }
/*     */ 
/*     */   
/* 547 */   public void setNombreTipoRecurso(String p) { this.nombreTipoRecurso = p; }
/*     */ 
/*     */   
/* 550 */   public String getNombreTipoRecurso() { return (this.nombreTipoRecurso == null) ? "" : this.nombreTipoRecurso; }
/*     */ 
/*     */ 
/*     */   
/* 554 */   public void setNombrePoliticas(String nombrePoliticas) { this.nombrePoliticas = nombrePoliticas; }
/*     */ 
/*     */ 
/*     */   
/* 558 */   public String getNombrePoliticas() { return this.nombrePoliticas; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\PrcProcedimientoDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */