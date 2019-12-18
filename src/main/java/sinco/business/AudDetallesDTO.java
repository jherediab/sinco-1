/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.AudDetallesDTO;
/*     */ import sinco.business.ParametrosDTO;
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
/*     */ public class AudDetallesDTO
/*     */ {
/*     */   private int codigoEmpleado;
/*     */   private String ciclo;
/*     */   private int consecutivo;
/*     */   private String evaluacionAuditado;
/*     */   private String evaluacionGestion;
/*     */   private int tipoAuditoria;
/*     */   private int claseAuditoria;
/*     */   private int rol;
/*     */   private int areaAuditada;
/*     */   private String nombreAreaAuditor;
/*     */   private String ubicacion;
/*     */   private int numeroPersonasArea;
/*     */   private int auditoriasPrevias;
/*     */   private int solicitud;
/*     */   private String ntipoAuditoria;
/*     */   private String nclaseAuditoria;
/*     */   private String nrol;
/*     */   private String usuarioInsercion;
/*     */   private String fechaInsercion;
/*     */   private String usuarioModificacion;
/*     */   private String fechaModificacion;
/*     */   private int personaAuditada;
/*     */   private String justificacion;
/*     */   private String observaciones;
/*     */   private String codigoProveedor;
/*     */   private String proceso;
/*     */   private String subproceso;
/*     */   private String generado;
/*     */   private String tipoCompetencia;
/*     */   private String nombreAuditado;
/*     */   private String nombreAreaAuditada;
/*     */   private String nombreAuditor;
/*     */   private String nombreEvaluador;
/*     */   private String nombreCiclo;
/*     */   private String anno;
/*     */   private String nombreCaracteristica;
/*     */   private String asistio;
/*     */   private double promedio;
/*     */   private double comunicacionEscrita;
/*     */   private double comunicacionOral;
/*     */   private double desarrolloAuditoria;
/*     */   private double empatiaCapacidadEscucha;
/*     */   private double habilidadesPlanificacion;
/*     */   private double liderazgo;
/*     */   private double trabajoEquipo;
/*     */   private double serenidad;
/*     */   private int codigoServicio;
/*     */   private String nombreServicio;
/*     */   private String estado;
/*     */   private String auditorIso9000;
/*     */   private String auditorOhsas;
/*     */   private String auditorIso14000;
/*     */   private int numeroAuditorias;
/*     */   private String nombreCiudad;
/*     */   private String nombreCompetencia;
/*     */   
/* 105 */   public void setCodigoEmpleado(int p) { this.codigoEmpleado = p; }
/*     */ 
/*     */   
/* 108 */   public int getCodigoEmpleado() { return this.codigoEmpleado; }
/*     */ 
/*     */ 
/*     */   
/* 112 */   public void setCiclo(String p) { this.ciclo = p; }
/*     */ 
/*     */   
/* 115 */   public String getCiclo() { return (this.ciclo == null) ? "" : this.ciclo; }
/*     */ 
/*     */ 
/*     */   
/* 119 */   public void setConsecutivo(int p) { this.consecutivo = p; }
/*     */ 
/*     */   
/* 122 */   public int getConsecutivo() { return this.consecutivo; }
/*     */ 
/*     */ 
/*     */   
/* 126 */   public void setEvaluacionAuditado(String p) { this.evaluacionAuditado = p; }
/*     */ 
/*     */   
/* 129 */   public String getEvaluacionAuditado() { return (this.evaluacionAuditado == null) ? "" : this.evaluacionAuditado; }
/*     */ 
/*     */ 
/*     */   
/* 133 */   public void setEvaluacionGestion(String p) { this.evaluacionGestion = p; }
/*     */ 
/*     */   
/* 136 */   public String getEvaluacionGestion() { return (this.evaluacionGestion == null) ? "" : this.evaluacionGestion; }
/*     */ 
/*     */ 
/*     */   
/* 140 */   public void setTipoAuditoria(int p) { this.tipoAuditoria = p; }
/*     */ 
/*     */   
/* 143 */   public int getTipoAuditoria() { return this.tipoAuditoria; }
/*     */ 
/*     */ 
/*     */   
/* 147 */   public void setClaseAuditoria(int p) { this.claseAuditoria = p; }
/*     */ 
/*     */   
/* 150 */   public int getClaseAuditoria() { return this.claseAuditoria; }
/*     */ 
/*     */ 
/*     */   
/* 154 */   public void setRol(int p) { this.rol = p; }
/*     */ 
/*     */   
/* 157 */   public int getRol() { return this.rol; }
/*     */ 
/*     */ 
/*     */   
/* 161 */   public void setAreaAuditada(int p) { this.areaAuditada = p; }
/*     */ 
/*     */   
/* 164 */   public int getAreaAuditada() { return this.areaAuditada; }
/*     */ 
/*     */ 
/*     */   
/* 168 */   public void setUbicacion(String p) { this.ubicacion = p; }
/*     */ 
/*     */   
/* 171 */   public String getUbicacion() { return (this.ubicacion == null) ? "" : this.ubicacion; }
/*     */ 
/*     */ 
/*     */   
/* 175 */   public void setNumeroPersonasArea(int p) { this.numeroPersonasArea = p; }
/*     */ 
/*     */   
/* 178 */   public int getNumeroPersonasArea() { return this.numeroPersonasArea; }
/*     */ 
/*     */ 
/*     */   
/* 182 */   public void setAuditoriasPrevias(int p) { this.auditoriasPrevias = p; }
/*     */ 
/*     */   
/* 185 */   public int getAuditoriasPrevias() { return this.auditoriasPrevias; }
/*     */ 
/*     */ 
/*     */   
/* 189 */   public void setSolicitud(int p) { this.solicitud = p; }
/*     */ 
/*     */   
/* 192 */   public int getSolicitud() { return this.solicitud; }
/*     */ 
/*     */ 
/*     */   
/* 196 */   public void setNtipoAuditoria(String p) { this.ntipoAuditoria = p; }
/*     */ 
/*     */   
/* 199 */   public String getNtipoAuditoria() { return (this.ntipoAuditoria == null) ? "" : this.ntipoAuditoria; }
/*     */ 
/*     */ 
/*     */   
/* 203 */   public void setNclaseAuditoria(String p) { this.nclaseAuditoria = p; }
/*     */ 
/*     */   
/* 206 */   public String getNclaseAuditoria() { return (this.nclaseAuditoria == null) ? "" : this.nclaseAuditoria; }
/*     */ 
/*     */ 
/*     */   
/* 210 */   public void setNrol(String p) { this.nrol = p; }
/*     */ 
/*     */   
/* 213 */   public String getNrol() { return (this.nrol == null) ? "" : this.nrol; }
/*     */ 
/*     */ 
/*     */   
/* 217 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */   
/* 220 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */   
/* 224 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */   
/* 227 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */   
/* 231 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */   
/* 234 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */   
/* 238 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */   
/* 241 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */   
/* 245 */   public void setPersonaAuditada(int p) { this.personaAuditada = p; }
/*     */ 
/*     */   
/* 248 */   public int getPersonaAuditada() { return this.personaAuditada; }
/*     */ 
/*     */ 
/*     */   
/* 252 */   public void setJustificacion(String p) { this.justificacion = p; }
/*     */ 
/*     */   
/* 255 */   public String getJustificacion() { return (this.justificacion == null) ? "" : this.justificacion; }
/*     */ 
/*     */ 
/*     */   
/* 259 */   public void setObservaciones(String p) { this.observaciones = p; }
/*     */ 
/*     */   
/* 262 */   public String getObservaciones() { return (this.observaciones == null) ? "" : this.observaciones; }
/*     */ 
/*     */ 
/*     */   
/* 266 */   public void setCodigoProveedor(String p) { this.codigoProveedor = p; }
/*     */ 
/*     */   
/* 269 */   public String getCodigoProveedor() { return (this.codigoProveedor == null) ? "" : this.codigoProveedor; }
/*     */ 
/*     */ 
/*     */   
/* 273 */   public void setProceso(String p) { this.proceso = p; }
/*     */ 
/*     */   
/* 276 */   public String getProceso() { return (this.proceso == null) ? "" : this.proceso; }
/*     */ 
/*     */ 
/*     */   
/* 280 */   public void setSubproceso(String p) { this.subproceso = p; }
/*     */ 
/*     */   
/* 283 */   public String getSubproceso() { return (this.subproceso == null) ? "" : this.subproceso; }
/*     */ 
/*     */ 
/*     */   
/* 287 */   public void setGenerado(String p) { this.generado = p; }
/*     */ 
/*     */   
/* 290 */   public String getGenerado() { return (this.generado == null) ? "" : this.generado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 298 */   public String getAnno() { return this.anno; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 304 */   public double getPromedio() { return this.promedio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 310 */   public void setAnno(String anno) { this.anno = anno; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 316 */   public void setPromedio(double promedio) { this.promedio = promedio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 325 */   public String getNombreCompetencia() { return (this.nombreCompetencia == null) ? "" : this.nombreCompetencia; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 331 */   public void setNombreCompetencia(String nombreCompetencia) { this.nombreCompetencia = nombreCompetencia; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 337 */   public String getTipoCompetencia() { return (this.tipoCompetencia == null) ? "" : this.tipoCompetencia; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 343 */   public void setTipoCompetencia(String tipoCompetencia) { this.tipoCompetencia = tipoCompetencia; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 349 */   public String getNombreAreaAuditada() { return (this.nombreAreaAuditada == null) ? "" : this.nombreAreaAuditada; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 355 */   public void setNombreAreaAuditada(String nombreAreaAuditada) { this.nombreAreaAuditada = nombreAreaAuditada; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 361 */   public String getNombreAuditado() { return (this.nombreAuditado == null) ? "" : this.nombreAuditado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 367 */   public void setNombreAuditado(String nombreAuditado) { this.nombreAuditado = nombreAuditado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 373 */   public String getNombreAuditor() { return (this.nombreAuditor == null) ? "" : this.nombreAuditor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 379 */   public void setNombreAuditor(String nombreAuditor) { this.nombreAuditor = nombreAuditor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 385 */   public String getNombreEvaluador() { return (this.nombreEvaluador == null) ? "" : this.nombreEvaluador; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 391 */   public void setNombreEvaluador(String nombreEvaluador) { this.nombreEvaluador = nombreEvaluador; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 397 */   public String getNombreCiclo() { return (this.nombreCiclo == null) ? "" : this.nombreCiclo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 403 */   public void setNombreCiclo(String nombreCiclo) { this.nombreCiclo = nombreCiclo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 409 */   public String getNombreCaracteristica() { return (this.nombreCaracteristica == null) ? "" : this.nombreCaracteristica; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 415 */   public void setNombreCaracteristica(String nombreCaracteristica) { this.nombreCaracteristica = nombreCaracteristica; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 421 */   public double getComunicacionEscrita() { return this.comunicacionEscrita; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 427 */   public void setComunicacionEscrita(double comunicacionEscrita) { this.comunicacionEscrita = comunicacionEscrita; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 433 */   public double getComunicacionOral() { return this.comunicacionOral; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 439 */   public void setComunicacionOral(double comunicacionOral) { this.comunicacionOral = comunicacionOral; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 445 */   public double getDesarrolloAuditoria() { return this.desarrolloAuditoria; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 451 */   public void setDesarrolloAuditoria(double desarrolloAuditoria) { this.desarrolloAuditoria = desarrolloAuditoria; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 457 */   public double getEmpatiaCapacidadEscucha() { return this.empatiaCapacidadEscucha; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 463 */   public void setEmpatiaCapacidadEscucha(double empatiaCapacidadEscucha) { this.empatiaCapacidadEscucha = empatiaCapacidadEscucha; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 469 */   public double getHabilidadesPlanificacion() { return this.habilidadesPlanificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 475 */   public void setHabilidadesPlanificacion(double habilidadesPlanificacion) { this.habilidadesPlanificacion = habilidadesPlanificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 481 */   public double getLiderazgo() { return this.liderazgo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 487 */   public void setLiderazgo(double liderazgo) { this.liderazgo = liderazgo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 493 */   public double getTrabajoEquipo() { return this.trabajoEquipo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 499 */   public void setTrabajoEquipo(double trabajoEquipo) { this.trabajoEquipo = trabajoEquipo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 505 */   public double getSerenidad() { return this.serenidad; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 511 */   public void setSerenidad(double serenidad) { this.serenidad = serenidad; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 519 */   public int getCodigoServicio() { return this.codigoServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 525 */   public void setCodigoServicio(int codigoServicio) { this.codigoServicio = codigoServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 531 */   public String getNombreServicio() { return (this.nombreServicio == null) ? "" : this.nombreServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 537 */   public void setNombreServicio(String nombreServicio) { this.nombreServicio = nombreServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 543 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 549 */   public void setEstado(String estado) { this.estado = estado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 555 */   public String getAuditorIso9000() { return (this.auditorIso9000 == null) ? "" : this.auditorIso9000; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 561 */   public void setAuditorIso9000(String auditorIso9000) { this.auditorIso9000 = auditorIso9000; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 567 */   public String getAuditorOhsas() { return (this.auditorOhsas == null) ? "" : this.auditorOhsas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 573 */   public void setAuditorOhsas(String auditorOhsas) { this.auditorOhsas = auditorOhsas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 579 */   public String getAuditorIso14000() { return (this.auditorIso14000 == null) ? "" : this.auditorIso14000; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 585 */   public void setAuditorIso14000(String auditorIso14000) { this.auditorIso14000 = auditorIso14000; }
/*     */ 
/*     */   
/*     */   public double getEducacion() {
/* 589 */     double suma = 0.0D;
/*     */     
/* 591 */     if (getAuditorIso14000().equals("S")) {
/* 592 */       suma += 1.66D;
/*     */     }
/* 594 */     if (getAuditorIso9000().equals("S")) {
/* 595 */       suma += 1.67D;
/*     */     }
/* 597 */     if (getAuditorOhsas().equals("S")) {
/* 598 */       suma += 1.67D;
/*     */     }
/*     */     
/* 601 */     return suma;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double promedioCompetencias() {
/* 610 */     double promedio = 0.0D;
/* 611 */     int cuantos = 0;
/* 612 */     if (this.comunicacionEscrita > 0.0D) {
/* 613 */       promedio += this.comunicacionEscrita;
/* 614 */       cuantos++;
/*     */     } 
/*     */     
/* 617 */     if (this.comunicacionOral > 0.0D) {
/* 618 */       promedio += this.comunicacionOral;
/* 619 */       cuantos++;
/*     */     } 
/* 621 */     if (this.desarrolloAuditoria > 0.0D) {
/* 622 */       promedio += this.desarrolloAuditoria;
/* 623 */       cuantos++;
/*     */     } 
/* 625 */     if (this.empatiaCapacidadEscucha > 0.0D) {
/* 626 */       promedio += this.empatiaCapacidadEscucha;
/* 627 */       cuantos++;
/*     */     } 
/* 629 */     if (this.habilidadesPlanificacion > 0.0D) {
/* 630 */       promedio += this.habilidadesPlanificacion;
/* 631 */       cuantos++;
/*     */     } 
/* 633 */     if (this.liderazgo > 0.0D) {
/* 634 */       promedio += this.liderazgo;
/* 635 */       cuantos++;
/*     */     } 
/* 637 */     if (this.trabajoEquipo > 0.0D) {
/* 638 */       promedio += this.trabajoEquipo;
/* 639 */       cuantos++;
/*     */     } 
/* 641 */     if (this.serenidad > 0.0D) {
/* 642 */       promedio += this.serenidad;
/* 643 */       cuantos++;
/*     */     } 
/* 645 */     if (cuantos == 0) cuantos = 1; 
/* 646 */     double valor = promedio / cuantos;
/*     */     
/* 648 */     return (getEducacion() * ParametrosDTO.getInt("aud_porcentaje_calificacion_formacion") + valor * (100 - ParametrosDTO.getInt("aud_porcentaje_calificacion_formacion"))) / 100.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 656 */   public int getNumeroAuditorias() { return this.numeroAuditorias; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 662 */   public void setNumeroAuditorias(int numeroAuditorias) { this.numeroAuditorias = numeroAuditorias; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 668 */   public String getNombreCiudad() { return (this.nombreCiudad == null) ? "" : this.nombreCiudad; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 674 */   public void setNombreCiudad(String nombreCiudad) { this.nombreCiudad = nombreCiudad; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 680 */   public String getNombreAreaAuditor() { return (this.nombreAreaAuditor == null) ? "" : this.nombreAreaAuditor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 686 */   public void setNombreAreaAuditor(String nombreAreaAuditor) { this.nombreAreaAuditor = nombreAreaAuditor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 692 */   public String getAsistio() { return (this.asistio == null) ? "" : this.asistio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 698 */   public void setAsistio(String asistio) { this.asistio = asistio; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\AudDetallesDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */