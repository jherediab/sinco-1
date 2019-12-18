/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.AreasDTO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AreasDTO
/*     */ {
/*     */   private int codigo;
/*     */   private String descripcion;
/*     */   private int nivelSuperior;
/*     */   private String estado;
/*     */   private int nivel;
/*     */   private String secuencia;
/*     */   private String anidar;
/*     */   private String ubicacion;
/*     */   private String incluirCalidad;
/*     */   private String modificaLogros;
/*     */   private String tipoArea;
/*     */   private String municipioUbicacion;
/*     */   private String nivelOrganigrama;
/*     */   private String cicloAuditoria;
/*     */   private String cicloAnterior;
/*     */   private String fechaInsercion;
/*     */   private String usuarioInsercion;
/*     */   private String fechaModificacion;
/*     */   private String usuarioModificacion;
/*     */   private String nombreNivelSuperior;
/*     */   private String nombreEstado;
/*     */   private String nombreTipoArea;
/*     */   private String nombreMunicipioUbicacion;
/*     */   private String departamentoUbicacion;
/*     */   private String nombredepartamentoUbicacion;
/*     */   private String nombreNivelOrganigrama;
/*     */   private int personaResponsable;
/*     */   
/*  74 */   public void setCodigo(int p) { this.codigo = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   public int getCodigo() { return this.codigo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public void setDescripcion(String p) { this.descripcion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   public String getDescripcion() { return (this.descripcion == null) ? "" : this.descripcion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   public void setNivelSuperior(int p) { this.nivelSuperior = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public int getNivelSuperior() { return this.nivelSuperior; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   public void setEstado(String p) { this.estado = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public void setNivel(int p) { this.nivel = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 146 */   public int getNivel() { return this.nivel; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   public void setSecuencia(String p) { this.secuencia = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 162 */   public String getSecuencia() { return (this.secuencia == null) ? "" : this.secuencia; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   public void setAnidar(String p) { this.anidar = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 178 */   public String getAnidar() { return (this.anidar == null) ? "" : this.anidar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 186 */   public void setUbicacion(String p) { this.ubicacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 194 */   public String getUbicacion() { return (this.ubicacion == null) ? "" : this.ubicacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 202 */   public void setIncluirCalidad(String p) { this.incluirCalidad = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 210 */   public String getIncluirCalidad() { return (this.incluirCalidad == null) ? "" : this.incluirCalidad; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 219 */   public void setModificaLogros(String p) { this.modificaLogros = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 227 */   public String getModificaLogros() { return (this.modificaLogros == null) ? "" : this.modificaLogros; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isModificaLogros() {
/* 236 */     if (this.modificaLogros == null) {
/* 237 */       return false;
/*     */     }
/* 239 */     return this.modificaLogros.equals("S");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 248 */   public void setTipoArea(String p) { this.tipoArea = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 256 */   public String getTipoArea() { return (this.tipoArea == null) ? "" : this.tipoArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 264 */   public void setMunicipioUbicacion(String p) { this.municipioUbicacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 272 */   public String getMunicipioUbicacion() { return (this.municipioUbicacion == null) ? "" : this.municipioUbicacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 280 */   public void setNivelOrganigrama(String p) { this.nivelOrganigrama = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 288 */   public String getNivelOrganigrama() { return (this.nivelOrganigrama == null) ? "" : this.nivelOrganigrama; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 296 */   public void setCicloAuditoria(String p) { this.cicloAuditoria = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 304 */   public String getCicloAuditoria() { return (this.cicloAuditoria == null) ? "" : this.cicloAuditoria; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 312 */   public void setCicloAnterior(String p) { this.cicloAnterior = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 320 */   public String getCicloAnterior() { return (this.cicloAnterior == null) ? "" : this.cicloAnterior; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 329 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 337 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 345 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 353 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 361 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 369 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 377 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 385 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 390 */   public void setNombreNivelSuperior(String p) { this.nombreNivelSuperior = p; }
/*     */ 
/*     */   
/* 393 */   public String getNombreNivelSuperior() { return (this.nombreNivelSuperior == null) ? "" : this.nombreNivelSuperior; }
/*     */ 
/*     */   
/* 396 */   public void setNombreEstado(String p) { this.nombreEstado = p; }
/*     */ 
/*     */   
/* 399 */   public String getNombreEstado() { return (this.nombreEstado == null) ? "" : this.nombreEstado; }
/*     */ 
/*     */   
/* 402 */   public void setNombreTipoArea(String p) { this.nombreTipoArea = p; }
/*     */ 
/*     */   
/* 405 */   public String getNombreTipoArea() { return (this.nombreTipoArea == null) ? "" : this.nombreTipoArea; }
/*     */ 
/*     */   
/* 408 */   public void setNombreMunicipioUbicacion(String p) { this.nombreMunicipioUbicacion = p; }
/*     */ 
/*     */   
/* 411 */   public String getNombreMunicipioUbicacion() { return (this.nombreMunicipioUbicacion == null) ? "" : this.nombreMunicipioUbicacion; }
/*     */ 
/*     */   
/* 414 */   public void setNombreNivelOrganigrama(String p) { this.nombreNivelOrganigrama = p; }
/*     */ 
/*     */   
/* 417 */   public String getNombreNivelOrganigrama() { return (this.nombreNivelOrganigrama == null) ? "" : this.nombreNivelOrganigrama; }
/*     */ 
/*     */ 
/*     */   
/* 421 */   public int getPersonaResponsable() { return this.personaResponsable; }
/*     */ 
/*     */ 
/*     */   
/* 425 */   public void setPersonaResponsable(int personaResponsable) { this.personaResponsable = personaResponsable; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 432 */   public String getDepartamentoUbicacion() { return this.departamentoUbicacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 439 */   public void setDepartamentoUbicacion(String departamentoUbicacion) { this.departamentoUbicacion = departamentoUbicacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 446 */   public String getNombredepartamentoUbicacion() { return (this.nombredepartamentoUbicacion == null) ? "" : this.nombredepartamentoUbicacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 453 */   public void setNombredepartamentoUbicacion(String nombredepartamentoUbicacion) { this.nombredepartamentoUbicacion = nombredepartamentoUbicacion; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\AreasDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */