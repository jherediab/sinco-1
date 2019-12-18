/*     */ package sinco.business;
/*     */ 
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SisUsuariosDTO
/*     */ {
/*     */   private int codigoEmpleado;
/*     */   private long numeroIdentificacion;
/*     */   private String tipoIdentificacion;
/*     */   private String nombres;
/*     */   private String apellidos;
/*     */   private String estado;
/*     */   private String password;
/*     */   private String idcorreo;
/*     */   private String email;
/*     */   private String auditorCordinador;
/*     */   private String tipoAuditor;
/*     */   private String usuarioSupervisor;
/*     */   private String fechaInsercion;
/*     */   private String usuarioInsercion;
/*     */   private String fechaModificacion;
/*     */   private String usuarioModificacion;
/*     */   private String nombreTipoIdentificacion;
/*     */   private String nombreEstado;
/*     */   private String nombreTipoAuditor;
/*     */   private String nombreArea;
/*     */   private String nombreCargoGenerico;
/*     */   private String cargoGenerico;
/*     */   private String auditorLider;
/*     */   private int numeroAuditorias;
/*     */   private int area;
/*     */   
/*  65 */   public void setCodigoEmpleado(int p) { this.codigoEmpleado = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   public int getCodigoEmpleado() { return this.codigoEmpleado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public void setNumeroIdentificacion(long p) { this.numeroIdentificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   public long getNumeroIdentificacion() { return this.numeroIdentificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public void setTipoIdentificacion(String p) { this.tipoIdentificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public String getTipoIdentificacion() { return (this.tipoIdentificacion == null) ? "" : this.tipoIdentificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 113 */   public void setNombres(String p) { this.nombres = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   public String getNombres() { return (this.nombres == null) ? "" : this.nombres; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   public void setApellidos(String p) { this.apellidos = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   public String getApellidos() { return (this.apellidos == null) ? "" : this.apellidos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 145 */   public void setEstado(String p) { this.estado = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 153 */   public String getEstado() { return (this.estado == null) ? "" : this.estado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 161 */   public void setPassword(String p) { this.password = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 169 */   public String getPassword() { return (this.password == null) ? "" : this.password; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 177 */   public void setIdcorreo(String p) { this.idcorreo = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 185 */   public String getIdcorreo() { return (this.idcorreo == null) ? "" : this.idcorreo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 193 */   public void setEmail(String p) { this.email = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 201 */   public String getEmail() { return (this.email == null) ? "" : this.email; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 209 */   public void setAuditorCordinador(String p) { this.auditorCordinador = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 217 */   public String getAuditorCordinador() { return (this.auditorCordinador == null) ? "" : this.auditorCordinador; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 225 */   public void setTipoAuditor(String p) { this.tipoAuditor = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 233 */   public String getTipoAuditor() { return (this.tipoAuditor == null) ? "" : this.tipoAuditor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 241 */   public void setUsuarioSupervisor(String p) { this.usuarioSupervisor = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 249 */   public String getUsuarioSupervisor() { return (this.usuarioSupervisor == null) ? "" : this.usuarioSupervisor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 257 */   public void setFechaInsercion(String p) { this.fechaInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 265 */   public String getFechaInsercion() { return (this.fechaInsercion == null) ? "" : this.fechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 273 */   public void setUsuarioInsercion(String p) { this.usuarioInsercion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 281 */   public String getUsuarioInsercion() { return (this.usuarioInsercion == null) ? "" : this.usuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 289 */   public void setFechaModificacion(String p) { this.fechaModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 297 */   public String getFechaModificacion() { return (this.fechaModificacion == null) ? "" : this.fechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 305 */   public void setUsuarioModificacion(String p) { this.usuarioModificacion = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 313 */   public String getUsuarioModificacion() { return (this.usuarioModificacion == null) ? "" : this.usuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 318 */   public void setNombreTipoIdentificacion(String p) { this.nombreTipoIdentificacion = p; }
/*     */ 
/*     */   
/* 321 */   public String getNombreTipoIdentificacion() { return (this.nombreTipoIdentificacion == null) ? "" : this.nombreTipoIdentificacion; }
/*     */ 
/*     */   
/* 324 */   public void setNombreEstado(String p) { this.nombreEstado = p; }
/*     */ 
/*     */   
/* 327 */   public String getNombreEstado() { return (this.nombreEstado == null) ? "" : this.nombreEstado; }
/*     */ 
/*     */   
/* 330 */   public void setNombreTipoAuditor(String p) { this.nombreTipoAuditor = p; }
/*     */ 
/*     */   
/* 333 */   public String getNombreTipoAuditor() { return (this.nombreTipoAuditor == null) ? "" : this.nombreTipoAuditor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 341 */   public String getNombre() { return ((this.nombres == null) ? "" : this.nombres) + " " + ((this.apellidos == null) ? "" : this.apellidos); }
/*     */ 
/*     */ 
/*     */   
/* 345 */   public String getNombreArea() { return (this.nombreArea == null) ? "" : this.nombreArea; }
/*     */ 
/*     */ 
/*     */   
/* 349 */   public int getArea() { return this.area; }
/*     */ 
/*     */ 
/*     */   
/* 353 */   public void setNombreArea(String nombreArea) { this.nombreArea = nombreArea; }
/*     */ 
/*     */ 
/*     */   
/* 357 */   public void setArea(int area) { this.area = area; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 364 */   public String getNombreCargoGenerico() { return (this.nombreCargoGenerico == null) ? "" : this.nombreCargoGenerico; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 371 */   public void setNombreCargoGenerico(String nombreCargoGenerico) { this.nombreCargoGenerico = nombreCargoGenerico; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 378 */   public String getCargoGenerico() { return (this.cargoGenerico == null) ? "" : this.cargoGenerico; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 385 */   public void setCargoGenerico(String cargoGenerico) { this.cargoGenerico = cargoGenerico; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 390 */   public String getAuditorLider() { return (this.auditorLider == null) ? "" : this.auditorLider; }
/*     */ 
/*     */ 
/*     */   
/* 394 */   public void setAuditorLider(String auditorLider) { this.auditorLider = auditorLider; }
/*     */ 
/*     */ 
/*     */   
/* 398 */   public int getNumeroAuditorias() { return this.numeroAuditorias; }
/*     */ 
/*     */ 
/*     */   
/* 402 */   public void setNumeroAuditorias(int numeroAuditorias) { this.numeroAuditorias = numeroAuditorias; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\business\SisUsuariosDTO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */