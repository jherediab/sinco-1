/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.RedPrestadorDTO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RedPrestadorDTO
/*     */ {
/*     */   private String codigoHabilitacion;
/*     */   private String tipoIdentificacion;
/*     */   private String numeroIdentificacion;
/*     */   private int dv;
/*     */   private String nombreEntidad;
/*     */   private String direccion;
/*     */   private String telefono;
/*     */   private String tipoDocumentoRepresentate;
/*     */   private String documentoRepresentante;
/*     */   private String departamentoRepresentante;
/*     */   private String municipioRepresentante;
/*     */   private String usuarioInsercion;
/*     */   private String fechaInsercion;
/*     */   private String usuarioModificacion;
/*     */   private String fechaModificacion;
/*     */   private String resolucionCreacion;
/*     */   private String numeroRegistro;
/*     */   private String numeroLibro;
/*     */   private String numeroFolio;
/*     */   private String fechaInscripcion;
/*     */   private String primerNombreRepresentante;
/*     */   private String segundoNombreRepresentante;
/*     */   private String primerApellidoRepresentante;
/*     */   private String segundoApellidoRepresentante;
/*     */   private String clasePrestador;
/*     */   private String tipoPersona;
/*     */   private String naturalezaJuridica;
/*     */   private String sitioWeb;
/*     */   private String fax;
/*     */   private String email;
/*     */   private String formaVinculacionRepresentante;
/*     */   private String caracterTerritorial;
/*     */   private String empresaSocialEstado;
/*     */   private String actoConstitucion;
/*     */   private String numeroActo;
/*     */   private String fechaActo;
/*     */   private String entidadExpide;
/*     */   private String departamento;
/*     */   private String municipio;
/*     */   private String estado;
/*     */   private String complejidad;
/*     */   private String departamentoExpedicion;
/*     */   private String municipioExpedicion;
/*     */   private String nivelAtencion;
/*     */   
/* 102 */   public void setCodigoHabilitacion(String p) { this.codigoHabilitacion = p; }
/*     */ 
/*     */   
/* 105 */   public String getCodigoHabilitacion() { return (this.codigoHabilitacion == null) ? "" : this.codigoHabilitacion; }
/*     */ 
/*     */ 
/*     */   
/* 109 */   public void setTipoIdentificacion(String p) { this.tipoIdentificacion = p; }
/*     */ 
/*     */   
/* 112 */   public String getTipoIdentificacion() { return (this.tipoIdentificacion == null) ? "" : this.tipoIdentificacion; }
/*     */ 
/*     */ 
/*     */   
/* 116 */   public void setNumeroIdentificacion(String p) { this.numeroIdentificacion = p; }
/*     */ 
/*     */   
/* 119 */   public String getNumeroIdentificacion() { return (this.numeroIdentificacion == null) ? "" : this.numeroIdentificacion; }
/*     */ 
/*     */ 
/*     */   
/* 123 */   public void setDv(int p) { this.dv = p; }
/*     */ 
/*     */   
/* 126 */   public int getDv() { return this.dv; }
/*     */ 
/*     */ 
/*     */   
/* 130 */   public void setNombreEntidad(String p) { this.nombreEntidad = p; }
/*     */ 
/*     */   
/* 133 */   public String getNombreEntidad() { return (this.nombreEntidad == null) ? "" : this.nombreEntidad; }
/*     */ 
/*     */ 
/*     */   
/* 137 */   public void setDireccion(String p) { this.direccion = p; }
/*     */ 
/*     */   
/* 140 */   public String getDireccion() { return (this.direccion == null) ? "" : this.direccion; }
/*     */ 
/*     */ 
/*     */   
/* 144 */   public void setTelefono(String p) { this.telefono = p; }
/*     */ 
/*     */   
/* 147 */   public String getTelefono() { return (this.telefono == null) ? "" : this.telefono; }
/*     */ 
/*     */ 
/*     */   
/* 151 */   public void setTipoDocumentoRepresentate(String p) { this.tipoDocumentoRepresentate = p; }
/*     */ 
/*     */   
/* 154 */   public String getTipoDocumentoRepresentate() { return (this.tipoDocumentoRepresentate == null) ? "" : this.tipoDocumentoRepresentate; }
/*     */ 
/*     */ 
/*     */   
/* 158 */   public void setDocumentoRepresentante(String p) { this.documentoRepresentante = p; }
/*     */ 
/*     */   
/* 161 */   public String getDocumentoRepresentante() { return (this.documentoRepresentante == null) ? "" : this.documentoRepresentante; }
/*     */ 
/*     */ 
/*     */   
/* 165 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */   
/* 168 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */   
/* 172 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */   
/* 175 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */   
/* 179 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */   
/* 182 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */   
/* 186 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */   
/* 189 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */   
/* 192 */   public String getResolucionCreacion() { return (this.resolucionCreacion == null) ? "" : this.resolucionCreacion; }
/*     */ 
/*     */   
/* 195 */   public void setResolucionCreacion(String resolucionCreacion) { this.resolucionCreacion = resolucionCreacion; }
/*     */ 
/*     */   
/* 198 */   public String getPrimerNombreRepresentante() { return (this.primerNombreRepresentante == null) ? "" : this.primerNombreRepresentante; }
/*     */ 
/*     */   
/* 201 */   public void setPrimerNombreRepresentante(String primerNombreRepresentante) { this.primerNombreRepresentante = primerNombreRepresentante; }
/*     */ 
/*     */   
/* 204 */   public String getSegundoNombreRepresentante() { return (this.segundoNombreRepresentante == null) ? "" : this.segundoNombreRepresentante; }
/*     */ 
/*     */   
/* 207 */   public void setSegundoNombreRepresentante(String segundoNombreRepresentante) { this.segundoNombreRepresentante = segundoNombreRepresentante; }
/*     */ 
/*     */   
/* 210 */   public String getPrimerApellidoRepresentante() { return (this.primerApellidoRepresentante == null) ? "" : this.primerApellidoRepresentante; }
/*     */ 
/*     */   
/* 213 */   public void setPrimerApellidoRepresentante(String primerApellidoRepresentante) { this.primerApellidoRepresentante = primerApellidoRepresentante; }
/*     */ 
/*     */   
/* 216 */   public String getSegundoApellidoRepresentante() { return (this.segundoApellidoRepresentante == null) ? "" : this.segundoApellidoRepresentante; }
/*     */ 
/*     */   
/* 219 */   public void setSegundoApellidoRepresentante(String segundoApellidoRepresentante) { this.segundoApellidoRepresentante = segundoApellidoRepresentante; }
/*     */ 
/*     */   
/* 222 */   public String getClasePrestador() { return (this.clasePrestador == null) ? "" : this.clasePrestador; }
/*     */ 
/*     */   
/* 225 */   public void setClasePrestador(String clasePrestador) { this.clasePrestador = clasePrestador; }
/*     */ 
/*     */   
/* 228 */   public String getTipoPersona() { return (this.tipoPersona == null) ? "" : this.tipoPersona; }
/*     */ 
/*     */   
/* 231 */   public void setTipoPersona(String tipoPersona) { this.tipoPersona = tipoPersona; }
/*     */ 
/*     */   
/* 234 */   public String getNaturalezaJuridica() { return (this.naturalezaJuridica == null) ? "" : this.naturalezaJuridica; }
/*     */ 
/*     */   
/* 237 */   public void setNaturalezaJuridica(String naturalezaJuridica) { this.naturalezaJuridica = naturalezaJuridica; }
/*     */ 
/*     */   
/* 240 */   public String getSitioWeb() { return (this.sitioWeb == null) ? "" : this.sitioWeb; }
/*     */ 
/*     */   
/* 243 */   public void setSitioWeb(String sitioWeb) { this.sitioWeb = sitioWeb; }
/*     */ 
/*     */   
/* 246 */   public String getFax() { return (this.fax == null) ? "" : this.fax; }
/*     */ 
/*     */   
/* 249 */   public void setFax(String fax) { this.fax = fax; }
/*     */ 
/*     */   
/* 252 */   public String getEmail() { return (this.email == null) ? "" : this.email; }
/*     */ 
/*     */   
/* 255 */   public void setEmail(String email) { this.email = email; }
/*     */ 
/*     */   
/* 258 */   public String getFormaVinculacionRepresentante() { return (this.formaVinculacionRepresentante == null) ? "" : this.formaVinculacionRepresentante; }
/*     */ 
/*     */ 
/*     */   
/* 262 */   public void setFormaVinculacionRepresentante(String formaVinculacionRepresentante) { this.formaVinculacionRepresentante = formaVinculacionRepresentante; }
/*     */ 
/*     */   
/* 265 */   public String getCaracterTerritorial() { return (this.caracterTerritorial == null) ? "" : this.caracterTerritorial; }
/*     */ 
/*     */   
/* 268 */   public void setCaracterTerritorial(String caracterTerritorial) { this.caracterTerritorial = caracterTerritorial; }
/*     */ 
/*     */ 
/*     */   
/* 272 */   public String getEmpresaSocialEstado() { return (this.empresaSocialEstado == null) ? "" : this.empresaSocialEstado; }
/*     */ 
/*     */   
/* 275 */   public void setEmpresaSocialEstado(String empresaSocialEstado) { this.empresaSocialEstado = empresaSocialEstado; }
/*     */ 
/*     */   
/* 278 */   public String getActoConstitucion() { return (this.actoConstitucion == null) ? "" : this.actoConstitucion; }
/*     */ 
/*     */   
/* 281 */   public void setActoConstitucion(String actoConstitucion) { this.actoConstitucion = actoConstitucion; }
/*     */ 
/*     */   
/* 284 */   public String getNumeroActo() { return (this.numeroActo == null) ? "" : this.numeroActo; }
/*     */ 
/*     */   
/* 287 */   public void setNumeroActo(String numeroActo) { this.numeroActo = numeroActo; }
/*     */ 
/*     */   
/* 290 */   public String getFechaActo() { return (this.fechaActo == null) ? "" : this.fechaActo; }
/*     */ 
/*     */   
/* 293 */   public void setFechaActo(String fechaActo) { this.fechaActo = fechaActo; }
/*     */ 
/*     */   
/* 296 */   public String getEntidadExpide() { return (this.entidadExpide == null) ? "" : this.entidadExpide; }
/*     */ 
/*     */   
/* 299 */   public void setEntidadExpide(String entidadExpide) { this.entidadExpide = entidadExpide; }
/*     */ 
/*     */   
/* 302 */   public String getDepartamento() { return (this.departamento == null) ? "" : this.departamento; }
/*     */ 
/*     */   
/* 305 */   public void setDepartamento(String departamento) { this.departamento = departamento; }
/*     */ 
/*     */   
/* 308 */   public String getMunicipio() { return (this.municipio == null) ? "" : this.municipio; }
/*     */ 
/*     */   
/* 311 */   public void setMunicipio(String municipio) { this.municipio = municipio; }
/*     */ 
/*     */   
/* 314 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*     */ 
/*     */   
/* 317 */   public void setEstado(String estado) { this.estado = estado; }
/*     */ 
/*     */   
/* 320 */   public void setDepartamentoRepresentante(String departamentoRepresentante) { this.departamentoRepresentante = departamentoRepresentante; }
/*     */ 
/*     */   
/* 323 */   public String getDepartamentoRepresentante() { return (this.departamentoRepresentante == null) ? "" : this.departamentoRepresentante; }
/*     */ 
/*     */   
/* 326 */   public void setMunicipioRepresentante(String municipioRepresentante) { this.municipioRepresentante = municipioRepresentante; }
/*     */ 
/*     */   
/* 329 */   public String getMunicipioRepresentante() { return (this.municipioRepresentante == null) ? "" : this.municipioRepresentante; }
/*     */ 
/*     */   
/* 332 */   public void setComplejidad(String complejidad) { this.complejidad = complejidad; }
/*     */ 
/*     */   
/* 335 */   public String getComplejidad() { return (this.complejidad == null) ? "" : this.complejidad; }
/*     */ 
/*     */   
/* 338 */   public void setDepartamentoExpedicion(String departamentoExpedicion) { this.departamentoExpedicion = departamentoExpedicion; }
/*     */ 
/*     */   
/* 341 */   public String getDepartamentoExpedicion() { return (this.departamentoExpedicion == null) ? "" : this.departamentoExpedicion; }
/*     */ 
/*     */   
/* 344 */   public void setMunicipioExpedicion(String municipioExpedicion) { this.municipioExpedicion = municipioExpedicion; }
/*     */ 
/*     */   
/* 347 */   public String getMunicipioExpedicion() { return (this.municipioExpedicion == null) ? "" : this.municipioExpedicion; }
/*     */ 
/*     */   
/* 350 */   public void setNivelAtencion(String nivelAtencion) { this.nivelAtencion = nivelAtencion; }
/*     */ 
/*     */   
/* 353 */   public String getNivelAtencion() { return (this.nivelAtencion == null) ? "" : this.nivelAtencion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 359 */   public void setNumeroRegistro(String numeroRegistro) { this.numeroRegistro = numeroRegistro; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 365 */   public String getNumeroRegistro() { return (this.numeroRegistro == null) ? "" : this.numeroRegistro; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 371 */   public void setNumeroLibro(String numeroLibro) { this.numeroLibro = numeroLibro; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 377 */   public String getNumeroLibro() { return (this.numeroLibro == null) ? "" : this.numeroLibro; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 383 */   public void setNumeroFolio(String numeroFolio) { this.numeroFolio = numeroFolio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 389 */   public String getNumeroFolio() { return (this.numeroFolio == null) ? "" : this.numeroFolio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 395 */   public void setFechaInscripcion(String fechaInscripcion) { this.fechaInscripcion = fechaInscripcion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 401 */   public String getFechaInscripcion() { return (this.fechaInscripcion == null) ? "" : this.fechaInscripcion; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\RedPrestadorDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */