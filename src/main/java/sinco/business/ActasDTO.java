/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.ActasDTO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ActasDTO
/*     */ {
/*     */   private int annoActa;
/*     */   private int codigoArea;
/*     */   private int numeroActa;
/*     */   private int anotador;
/*     */   private int eficacia;
/*     */   private int nivelImplantacion;
/*     */   private String lecturaActaAnt;
/*     */   private String auditoriaInterna;
/*     */   private String auditoriaExterna;
/*     */   private String retroAudiServicio;
/*     */   private String retroQuejasReclamos;
/*     */   private String productoNoConforme;
/*     */   private String accionesMejora;
/*     */   private String segumientoAcciones;
/*     */   private String cambiosAfectaCalidad;
/*     */   private String recomendacionesMejora;
/*     */   private String eficaciaSimasol;
/*     */   private String eficaciaSeguridad;
/*     */   private String eficaciaDesempeno;
/*     */   private String necesidadRecursos;
/*     */   private String temasVarios;
/*     */   private String medioAmbiente;
/*     */   private int proximoAnotador;
/*     */   private String proximaReunion;
/*     */   private String estado;
/*     */   private String indLecturaActaAnt;
/*     */   private String indResultadosAuditoria;
/*     */   private String indRetroalimentacion;
/*     */   private String indSeguimientoActividades;
/*     */   private String indProductoNoConforme;
/*     */   private String indAccionesMejora;
/*     */   private String indCambiosAfectaCalidad;
/*     */   private String indRecomendacionesMejora;
/*     */   private String indEficaciaGestion;
/*     */   private String indNecesidadRecursos;
/*     */   private String indTemasVarios;
/*     */   private String fechaInsercion;
/*     */   private String usuarioInsercion;
/*     */   private String fechaModificacion;
/*     */   private String usuarioModificacion;
/*     */   private String nombreAnotador;
/*     */   private String nombreProximoAnotador;
/*     */   
/*  93 */   public void setAnnoActa(int p) { this.annoActa = p; }
/*     */ 
/*     */   
/*  96 */   public int getAnnoActa() { return this.annoActa; }
/*     */ 
/*     */ 
/*     */   
/* 100 */   public void setCodigoArea(int p) { this.codigoArea = p; }
/*     */ 
/*     */   
/* 103 */   public int getCodigoArea() { return this.codigoArea; }
/*     */ 
/*     */ 
/*     */   
/* 107 */   public void setNumeroActa(int p) { this.numeroActa = p; }
/*     */ 
/*     */   
/* 110 */   public int getNumeroActa() { return this.numeroActa; }
/*     */ 
/*     */ 
/*     */   
/* 114 */   public void setAnotador(int p) { this.anotador = p; }
/*     */ 
/*     */   
/* 117 */   public int getAnotador() { return this.anotador; }
/*     */ 
/*     */ 
/*     */   
/* 121 */   public void setEficacia(int p) { this.eficacia = p; }
/*     */ 
/*     */   
/* 124 */   public int getEficacia() { return this.eficacia; }
/*     */ 
/*     */ 
/*     */   
/* 128 */   public void setNivelImplantacion(int p) { this.nivelImplantacion = p; }
/*     */ 
/*     */   
/* 131 */   public int getNivelImplantacion() { return this.nivelImplantacion; }
/*     */ 
/*     */ 
/*     */   
/* 135 */   public void setLecturaActaAnt(String p) { this.lecturaActaAnt = p; }
/*     */ 
/*     */   
/* 138 */   public String getLecturaActaAnt() { return (this.lecturaActaAnt == null) ? "" : this.lecturaActaAnt; }
/*     */ 
/*     */ 
/*     */   
/* 142 */   public void setAuditoriaInterna(String p) { this.auditoriaInterna = p; }
/*     */ 
/*     */   
/* 145 */   public String getAuditoriaInterna() { return (this.auditoriaInterna == null) ? "" : this.auditoriaInterna; }
/*     */ 
/*     */ 
/*     */   
/* 149 */   public void setAuditoriaExterna(String p) { this.auditoriaExterna = p; }
/*     */ 
/*     */   
/* 152 */   public String getAuditoriaExterna() { return (this.auditoriaExterna == null) ? "" : this.auditoriaExterna; }
/*     */ 
/*     */ 
/*     */   
/* 156 */   public void setRetroAudiServicio(String p) { this.retroAudiServicio = p; }
/*     */ 
/*     */   
/* 159 */   public String getRetroAudiServicio() { return (this.retroAudiServicio == null) ? "" : this.retroAudiServicio; }
/*     */ 
/*     */ 
/*     */   
/* 163 */   public void setRetroQuejasReclamos(String p) { this.retroQuejasReclamos = p; }
/*     */ 
/*     */   
/* 166 */   public String getRetroQuejasReclamos() { return (this.retroQuejasReclamos == null) ? "" : this.retroQuejasReclamos; }
/*     */ 
/*     */ 
/*     */   
/* 170 */   public void setProductoNoConforme(String p) { this.productoNoConforme = p; }
/*     */ 
/*     */   
/* 173 */   public String getProductoNoConforme() { return (this.productoNoConforme == null) ? "" : this.productoNoConforme; }
/*     */ 
/*     */ 
/*     */   
/* 177 */   public void setAccionesMejora(String p) { this.accionesMejora = p; }
/*     */ 
/*     */   
/* 180 */   public String getAccionesMejora() { return (this.accionesMejora == null) ? "" : this.accionesMejora; }
/*     */ 
/*     */ 
/*     */   
/* 184 */   public void setSegumientoAcciones(String p) { this.segumientoAcciones = p; }
/*     */ 
/*     */   
/* 187 */   public String getSegumientoAcciones() { return (this.segumientoAcciones == null) ? "" : this.segumientoAcciones; }
/*     */ 
/*     */ 
/*     */   
/* 191 */   public void setCambiosAfectaCalidad(String p) { this.cambiosAfectaCalidad = p; }
/*     */ 
/*     */   
/* 194 */   public String getCambiosAfectaCalidad() { return (this.cambiosAfectaCalidad == null) ? "" : this.cambiosAfectaCalidad; }
/*     */ 
/*     */ 
/*     */   
/* 198 */   public void setRecomendacionesMejora(String p) { this.recomendacionesMejora = p; }
/*     */ 
/*     */   
/* 201 */   public String getRecomendacionesMejora() { return (this.recomendacionesMejora == null) ? "" : this.recomendacionesMejora; }
/*     */ 
/*     */ 
/*     */   
/* 205 */   public void setEficaciaSimasol(String p) { this.eficaciaSimasol = p; }
/*     */ 
/*     */   
/* 208 */   public String getEficaciaSimasol() { return (this.eficaciaSimasol == null) ? "" : this.eficaciaSimasol; }
/*     */ 
/*     */ 
/*     */   
/* 212 */   public void setEficaciaSeguridad(String p) { this.eficaciaSeguridad = p; }
/*     */ 
/*     */   
/* 215 */   public String getEficaciaSeguridad() { return (this.eficaciaSeguridad == null) ? "" : this.eficaciaSeguridad; }
/*     */ 
/*     */ 
/*     */   
/* 219 */   public void setEficaciaDesempeno(String p) { this.eficaciaDesempeno = p; }
/*     */ 
/*     */   
/* 222 */   public String getEficaciaDesempeno() { return (this.eficaciaDesempeno == null) ? "" : this.eficaciaDesempeno; }
/*     */ 
/*     */ 
/*     */   
/* 226 */   public void setNecesidadRecursos(String p) { this.necesidadRecursos = p; }
/*     */ 
/*     */   
/* 229 */   public String getNecesidadRecursos() { return (this.necesidadRecursos == null) ? "" : this.necesidadRecursos; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 234 */   public void setProximoAnotador(int p) { this.proximoAnotador = p; }
/*     */ 
/*     */   
/* 237 */   public int getProximoAnotador() { return this.proximoAnotador; }
/*     */ 
/*     */ 
/*     */   
/* 241 */   public void setProximaReunion(String p) { this.proximaReunion = p; }
/*     */ 
/*     */   
/* 244 */   public String getProximaReunion() { return (this.proximaReunion == null) ? "" : this.proximaReunion; }
/*     */ 
/*     */ 
/*     */   
/* 248 */   public void setEstado(String p) { this.estado = p; }
/*     */ 
/*     */   
/* 251 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*     */ 
/*     */ 
/*     */   
/* 255 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */   
/* 258 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */   
/* 262 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */   
/* 265 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */   
/* 269 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */   
/* 272 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */   
/* 276 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */   
/* 279 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */   
/* 283 */   public String getTemasVarios() { return (this.temasVarios == null) ? "" : this.temasVarios; }
/*     */ 
/*     */   
/* 286 */   public void setTemasVarios(String p) { this.temasVarios = p; }
/*     */ 
/*     */ 
/*     */   
/* 290 */   public String getNombreAnotador() { return (this.nombreAnotador == null) ? "" : this.nombreAnotador; }
/*     */ 
/*     */   
/* 293 */   public void setNombreAnotador(String p) { this.nombreAnotador = p; }
/*     */ 
/*     */ 
/*     */   
/* 297 */   public void setIndLecturaActaAnt(String p) { this.indLecturaActaAnt = p; }
/*     */ 
/*     */   
/* 300 */   public String getIndLecturaActaAnt() { return (this.indLecturaActaAnt == null) ? "" : this.indLecturaActaAnt; }
/*     */ 
/*     */ 
/*     */   
/* 304 */   public void setIndResultadosAuditoria(String p) { this.indResultadosAuditoria = p; }
/*     */ 
/*     */   
/* 307 */   public String getIndResultadosAuditoria() { return (this.indResultadosAuditoria == null) ? "" : this.indResultadosAuditoria; }
/*     */ 
/*     */ 
/*     */   
/* 311 */   public void setIndRetroalimentacion(String p) { this.indRetroalimentacion = p; }
/*     */ 
/*     */   
/* 314 */   public String getIndRetroalimentacion() { return (this.indRetroalimentacion == null) ? "" : this.indRetroalimentacion; }
/*     */ 
/*     */ 
/*     */   
/* 318 */   public void setIndProductoNoConforme(String p) { this.indProductoNoConforme = p; }
/*     */ 
/*     */   
/* 321 */   public String getIndProductoNoConforme() { return (this.indProductoNoConforme == null) ? "" : this.indProductoNoConforme; }
/*     */ 
/*     */ 
/*     */   
/* 325 */   public void setIndAccionesMejora(String p) { this.indAccionesMejora = p; }
/*     */ 
/*     */   
/* 328 */   public String getIndAccionesMejora() { return (this.indAccionesMejora == null) ? "" : this.indAccionesMejora; }
/*     */ 
/*     */ 
/*     */   
/* 332 */   public void setIndCambiosAfectaCalidad(String p) { this.indCambiosAfectaCalidad = p; }
/*     */ 
/*     */   
/* 335 */   public String getIndCambiosAfectaCalidad() { return (this.indCambiosAfectaCalidad == null) ? "" : this.indCambiosAfectaCalidad; }
/*     */ 
/*     */ 
/*     */   
/* 339 */   public void setIndRecomendacionesMejora(String p) { this.indRecomendacionesMejora = p; }
/*     */ 
/*     */   
/* 342 */   public String getIndRecomendacionesMejora() { return (this.indRecomendacionesMejora == null) ? "" : this.indRecomendacionesMejora; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 349 */   public String getIndSeguimientoActividades() { return (this.indSeguimientoActividades == null) ? "" : this.indSeguimientoActividades; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 355 */   public void setIndSeguimientoActividades(String indSeguimientoActividades) { this.indSeguimientoActividades = indSeguimientoActividades; }
/*     */ 
/*     */ 
/*     */   
/* 359 */   public void setIndEficaciaGestion(String p) { this.indEficaciaGestion = p; }
/*     */ 
/*     */   
/* 362 */   public String getIndEficaciaGestion() { return (this.indEficaciaGestion == null) ? "" : this.indEficaciaGestion; }
/*     */ 
/*     */ 
/*     */   
/* 366 */   public void setIndNecesidadRecursos(String p) { this.indNecesidadRecursos = p; }
/*     */ 
/*     */   
/* 369 */   public String getIndNecesidadRecursos() { return (this.indNecesidadRecursos == null) ? "" : this.indNecesidadRecursos; }
/*     */ 
/*     */ 
/*     */   
/* 373 */   public void setIndTemasVarios(String p) { this.indTemasVarios = p; }
/*     */ 
/*     */   
/* 376 */   public String getIndTemasVarios() { return (this.indTemasVarios == null) ? "" : this.indTemasVarios; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 385 */   public String getNombreProximoAnotador() { return (this.nombreProximoAnotador == null) ? "" : this.nombreProximoAnotador; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 390 */   public void setNombreProximoAnotador(String p) { this.nombreProximoAnotador = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 396 */   public String getMedioAmbiente() { return (this.medioAmbiente == null) ? "" : this.medioAmbiente; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 402 */   public void setMedioAmbiente(String medioAmbiente) { this.medioAmbiente = medioAmbiente; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\ActasDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */