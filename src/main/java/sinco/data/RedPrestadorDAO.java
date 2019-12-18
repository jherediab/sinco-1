/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.RedPrestadorDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.RedPrestadorDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RedPrestadorDAO
/*     */ {
/*     */   ResultSet rs;
/*  24 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  40 */       this.dat.close();
/*     */     }
/*  42 */     catch (Exception e) {
/*  43 */       Utilidades.writeError("RedPrestadorDAO:close " + e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RedPrestadorDTO next() {
/*     */     try {
/*  54 */       if (this.rs.next()) {
/*  55 */         return leerRegistro();
/*     */       }
/*     */     }
/*  58 */     catch (Exception e) {
/*  59 */       e.printStackTrace();
/*  60 */       Utilidades.writeError("RedPrestadorDAO:next " + e.getMessage());
/*     */     } 
/*  62 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RedPrestadorDTO leerRegistro() {
/*     */     try {
/*  72 */       RedPrestadorDTO reg = new RedPrestadorDTO();
/*  73 */       reg.setCodigoHabilitacion(this.rs.getString("codigo_habilitacion"));
/*  74 */       reg.setTipoIdentificacion(this.rs.getString("tipo_identificacion"));
/*  75 */       reg.setNumeroIdentificacion(this.rs.getString("numero_identificacion"));
/*  76 */       reg.setDv(this.rs.getInt("dv"));
/*  77 */       reg.setNombreEntidad(this.rs.getString("nombre_entidad"));
/*  78 */       reg.setDireccion(this.rs.getString("direccion"));
/*  79 */       reg.setTelefono(this.rs.getString("telefono"));
/*  80 */       reg.setTipoDocumentoRepresentate(this.rs.getString("tipo_documento_representate"));
/*  81 */       reg.setDocumentoRepresentante(this.rs.getString("documento_representante"));
/*  82 */       reg.setDepartamentoRepresentante(this.rs.getString("departamento_representante"));
/*  83 */       reg.setMunicipioRepresentante(this.rs.getString("municipio_representante"));
/*  84 */       reg.setComplejidad(this.rs.getString("complejidad"));
/*  85 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  86 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  87 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  88 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  89 */       reg.setResolucionCreacion(this.rs.getString("resolucion_creacion"));
/*  90 */       reg.setNumeroRegistro(this.rs.getString("numero_registro"));
/*  91 */       reg.setNumeroLibro(this.rs.getString("numero_libro"));
/*  92 */       reg.setNumeroFolio(this.rs.getString("numero_folio"));
/*  93 */       reg.setFechaInscripcion(this.rs.getString("fecha_inscripcion"));
/*  94 */       reg.setPrimerNombreRepresentante(this.rs.getString("primer_nombre_representante"));
/*  95 */       reg.setSegundoNombreRepresentante(this.rs.getString("segundo_nombre_representante"));
/*  96 */       reg.setPrimerApellidoRepresentante(this.rs.getString("primer_apellido_representante"));
/*  97 */       reg.setSegundoApellidoRepresentante(this.rs.getString("segundo_apellido_representante"));
/*  98 */       reg.setClasePrestador(this.rs.getString("clase_prestador"));
/*  99 */       reg.setTipoPersona(this.rs.getString("tipo_persona"));
/* 100 */       reg.setNaturalezaJuridica(this.rs.getString("naturaleza_juridica"));
/* 101 */       reg.setSitioWeb(this.rs.getString("sitio_web"));
/* 102 */       reg.setFax(this.rs.getString("fax"));
/* 103 */       reg.setEmail(this.rs.getString("email"));
/* 104 */       reg.setFormaVinculacionRepresentante(this.rs.getString("forma_vinculacion_representante"));
/* 105 */       reg.setCaracterTerritorial(this.rs.getString("caracter_territorial"));
/* 106 */       reg.setEmpresaSocialEstado(this.rs.getString("empresa_social_estado"));
/* 107 */       reg.setActoConstitucion(this.rs.getString("acto_constitucion"));
/* 108 */       reg.setNumeroActo(this.rs.getString("numero_acto"));
/* 109 */       reg.setFechaActo(this.rs.getString("fecha_acto"));
/* 110 */       reg.setEntidadExpide(this.rs.getString("entidad_expide"));
/* 111 */       reg.setDepartamento(this.rs.getString("departamento"));
/* 112 */       reg.setMunicipio(this.rs.getString("municipio"));
/* 113 */       reg.setEstado(this.rs.getString("estado"));
/* 114 */       reg.setDepartamentoExpedicion(this.rs.getString("departamento_expedicion"));
/* 115 */       reg.setMunicipioExpedicion(this.rs.getString("municipio_expedicion"));
/* 116 */       reg.setNivelAtencion(this.rs.getString("nivel_atencion"));
/* 117 */       return reg;
/*     */     }
/* 119 */     catch (Exception e) {
/* 120 */       e.printStackTrace();
/* 121 */       Utilidades.writeError("RedPrestadorDAO:leerRegistro " + e.getMessage());
/*     */       
/* 123 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<RedPrestadorDTO> cargarTodos(String codigoHabilitacion, String numeroIdentificacion, String nombreEntidad, String departamento, String municipio, String complejidad, String primerNombre, String primerApellido, String tipoDocumento, String tipoPrestador) {
/* 142 */     Collection<RedPrestadorDTO> resultados = new ArrayList<RedPrestadorDTO>();
/*     */     try {
/* 144 */       String s = "select  codigo_habilitacion, tipo_identificacion, numero_identificacion, dv, nombre_entidad, direccion, telefono, tipo_documento_representate, documento_representante,  departamento_representante, municipio_representante, complejidad, usuario_insercion, fecha_insercion, usuario_modificacion, fecha_modificacion, resolucion_creacion, numero_registro, numero_libro, numero_folio, fecha_inscripcion, primer_nombre_representante, segundo_nombre_representante, primer_apellido_representante, segundo_apellido_representante, clase_prestador, tipo_persona, naturaleza_juridica, sitio_web, fax, email, forma_vinculacion_representante, caracter_territorial, empresa_social_estado, acto_constitucion, numero_acto, fecha_acto, entidad_expide, d.nombre_departamento as departamento, c.nombre_ciudad as municipio, estado, departamento_expedicion, municipio_expedicion, nivel_atencion FROM red_prestador left join par_departamento d on (departamento=d.codigo_departamento) left join par_ciudad c on (municipio=c.codigo_ciudad and c.codigo_departamento=d.codigo_departamento  ) where  1=1 ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 194 */       if (codigoHabilitacion.length() > 0) {
/* 195 */         s = s + " and upper(codigo_habilitacion)=upper('" + codigoHabilitacion + "')";
/*     */       }
/* 197 */       if (numeroIdentificacion.length() > 0) {
/* 198 */         s = s + " and upper(numero_identificacion) =upper('" + numeroIdentificacion + "')";
/*     */       }
/* 200 */       if (nombreEntidad.length() > 0) {
/* 201 */         s = s + " and upper(nombre_entidad) like upper('%" + nombreEntidad + "%')";
/*     */       }
/* 203 */       if (departamento.length() > 0) {
/* 204 */         s = s + " and departamento='" + departamento + "'";
/*     */       }
/* 206 */       if (municipio.length() > 0) {
/* 207 */         s = s + " and municipio='" + municipio + "'";
/*     */       }
/* 209 */       if (complejidad.length() > 0) {
/* 210 */         s = s + " and complejidad='" + complejidad + "'";
/*     */       }
/* 212 */       if (primerNombre.length() > 0) {
/* 213 */         s = s + " and upper(primer_nombre_representante) like upper('%" + primerNombre + "%')";
/*     */       }
/* 215 */       if (primerApellido.length() > 0) {
/* 216 */         s = s + " and upper(primer_apellido_representante) like upper ('%" + primerApellido + "%')";
/*     */       }
/* 218 */       if (tipoDocumento.length() > 0) {
/* 219 */         s = s + " and tipo_identificacion='" + tipoDocumento + "'";
/*     */       }
/* 221 */       if (tipoPrestador.length() > 0) {
/* 222 */         s = s + " and tipo_prestador='" + tipoPrestador + "'";
/*     */       }
/* 224 */       s = s + " order by nombre_entidad";
/* 225 */       boolean rtaDB = this.dat.parseSql(s);
/* 226 */       if (!rtaDB) {
/* 227 */         return resultados;
/*     */       }
/* 229 */       this.rs = this.dat.getResultSet();
/* 230 */       while (this.rs.next()) {
/* 231 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 234 */     catch (Exception e) {
/* 235 */       e.printStackTrace();
/* 236 */       Utilidades.writeError("RedPrestadorDAO:cargarTodos " + e.getMessage());
/*     */     } 
/* 238 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RedPrestadorDTO cargarRegistro(String numeroIdentificacion) {
/*     */     try {
/* 248 */       String s = "select  codigo_habilitacion, tipo_identificacion, numero_identificacion, dv, nombre_entidad, direccion, telefono, tipo_documento_representate, documento_representante,  departamento_representante, municipio_representante, complejidad, usuario_insercion, fecha_insercion, usuario_modificacion, fecha_modificacion, resolucion_creacion, numero_registro, numero_libro, numero_folio, fecha_inscripcion, primer_nombre_representante, segundo_nombre_representante, primer_apellido_representante, segundo_apellido_representante, clase_prestador, tipo_persona, naturaleza_juridica, sitio_web, fax, email, forma_vinculacion_representante, caracter_territorial, empresa_social_estado, acto_constitucion, numero_acto, fecha_acto, entidad_expide, departamento, municipio, estado, departamento_expedicion, municipio_expedicion, nivel_atencion FROM red_prestador where  numero_identificacion='" + numeroIdentificacion + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 297 */       boolean rtaDB = this.dat.parseSql(s);
/* 298 */       if (!rtaDB) {
/* 299 */         return null;
/*     */       }
/* 301 */       this.rs = this.dat.getResultSet();
/* 302 */       if (this.rs.next()) {
/* 303 */         return leerRegistro();
/*     */       }
/*     */     }
/* 306 */     catch (Exception e) {
/* 307 */       e.printStackTrace();
/* 308 */       Utilidades.writeError("RedPrestadorDAO:cargarRedPrestador " + e.getMessage());
/*     */     } 
/* 310 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro(String numeroIdentificacion) {
/* 319 */     int inumero = 1;
/* 320 */     String s = "select max(0) from red_prestador  where  numero_identificacion='" + numeroIdentificacion + "'" + "";
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 325 */       boolean rta = this.dat.parseSql(s);
/* 326 */       if (!rta) return 0; 
/* 327 */       this.rs = this.dat.getResultSet();
/* 328 */       if (this.rs.next()) {
/* 329 */         s = this.rs.getString(1);
/* 330 */         if (!this.rs.wasNull()) {
/* 331 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 334 */       return inumero;
/*     */     }
/* 336 */     catch (Exception e) {
/* 337 */       e.printStackTrace();
/* 338 */       Utilidades.writeError("RedPrestadorDAO:siguienteRegistro " + e.getMessage());
/*     */       
/* 340 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(String numeroIdentificacion) {
/*     */     try {
/* 350 */       String s = "delete from red_prestador where   numero_identificacion='" + numeroIdentificacion + "'" + "";
/*     */ 
/*     */ 
/*     */       
/* 354 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 357 */     catch (Exception e) {
/* 358 */       e.printStackTrace();
/* 359 */       Utilidades.writeError("RedPrestadorDAO:eliminarRegistro " + e.getMessage());
/*     */       
/* 361 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean crearRegistro(String codigoHabilitacion, String tipoIdentificacion, String numeroIdentificacion, int dv, String nombreEntidad, String direccion, String telefono, String tipoDocumentoRepresentate, String documentoRepresentante, String departamentoRepresentante, String municipioRepresentante, String complejidad, String usuarioInsercion, String resolucionCreacion, String numeroRegistro, String numeroLibro, String numeroFolio, String fechaInscripcion, String primerNombreRepresentante, String segundoNombreRepresentante, String primerApellidoRepresentante, String segundoApellidoRepresentante, String clasePrestador, String tipoPersona, String naturalezaJuridica, String sitioWeb, String fax, String email, String formaVinculacionRepresentante, String caracterTerritorial, String empresaSocialEstado, String actoConstitucion, String numeroActo, String fechaActo, String entidadExpide, String departamento, String municipio, String estado, String departamentoExpedicion, String municipioExpedicion, String nivelAtencion, String tipoPrestador) {
/*     */     try {
/* 419 */       String s = "insert into red_prestador ( codigo_habilitacion, tipo_identificacion, numero_identificacion, dv, nombre_entidad, direccion, telefono, tipo_documento_representate, documento_representante, departamento_representante, municipio_representante, complejidad, usuario_insercion, fecha_insercion, resolucion_creacion, numero_registro, numero_libro, numero_folio,";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 438 */       if (fechaInscripcion.length() > 0) {
/* 439 */         s = s + " fecha_inscripcion,";
/*     */       }
/* 441 */       s = s + " primer_nombre_representante," + " segundo_nombre_representante," + " primer_apellido_representante," + " segundo_apellido_representante," + " clase_prestador," + " tipo_persona," + " naturaleza_juridica," + " sitio_web," + " fax, email," + " forma_vinculacion_representante," + " caracter_territorial," + " empresa_social_estado," + " acto_constitucion," + " numero_acto,";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 455 */       if (fechaActo.length() > 0) {
/* 456 */         s = s + " fecha_acto,";
/*     */       }
/* 458 */       s = s + " entidad_expide," + " departamento," + " municipio," + " estado," + " departamento_expedicion," + " municipio_expedicion," + " nivel_atencion," + " tipo_prestador" + ") values (" + "'" + codigoHabilitacion + "'," + "'" + tipoIdentificacion + "'," + "'" + numeroIdentificacion + "'," + "" + dv + "," + "'" + nombreEntidad + "'," + "'" + direccion + "'," + "'" + telefono + "'," + "'" + tipoDocumentoRepresentate + "'," + "'" + documentoRepresentante + "'," + "'" + departamentoRepresentante + "'," + "'" + municipioRepresentante + "'," + "'" + complejidad + "'," + "'" + usuarioInsercion + "'," + " now()," + "'" + resolucionCreacion + "'," + "'" + numeroRegistro + "'," + "'" + numeroLibro + "'," + "'" + numeroFolio + "',";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 485 */       if (fechaInscripcion.length() > 0) {
/* 486 */         s = s + "" + fechaInscripcion + ",";
/*     */       }
/* 488 */       s = s + "'" + primerNombreRepresentante + "'," + "'" + segundoNombreRepresentante + "'," + "'" + primerApellidoRepresentante + "'," + "'" + segundoApellidoRepresentante + "'," + "'" + clasePrestador + "'," + "'" + tipoPersona + "'," + "'" + naturalezaJuridica + "'," + "'" + sitioWeb + "'," + "'" + fax + "'," + "'" + email + "'," + "'" + formaVinculacionRepresentante + "'," + "'" + caracterTerritorial + "'," + "'" + empresaSocialEstado + "'," + "'" + actoConstitucion + "'," + "'" + numeroActo + "',";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 503 */       if (fechaActo.length() > 0) {
/* 504 */         s = s + "" + fechaActo + ",";
/*     */       }
/* 506 */       s = s + "'" + entidadExpide + "'," + "'" + departamento + "'," + "'" + municipio + "'," + "'" + estado + "'," + "'" + departamentoExpedicion + "'," + "'" + municipioExpedicion + "'," + "'" + nivelAtencion + "'," + "'" + tipoPrestador + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 515 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 518 */     catch (Exception e) {
/* 519 */       e.printStackTrace();
/* 520 */       Utilidades.writeError("RedPrestadorDAO:crearRegistro " + e.getMessage());
/*     */       
/* 522 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean crearRegistroPersonal(String tipoIdentificacion, String numeroIdentificacion, int dv, String nombreEntidad, String direccion, String telefono, String tipoDocumentoRepresentate, String documentoRepresentante, String departamentoExpedicion, String municipioExpedicion, String usuarioInsercion, String primerNombreRepresentante, String segundoNombreRepresentante, String primerApellidoRepresentante, String segundoApellidoRepresentante, String clasePrestador, String tipoPersona, String sitioWeb, String fax, String email, String departamento, String municipio, String estado, String tipoPrestador) {
/*     */     try {
/* 562 */       String s = "insert into red_prestador ( tipo_identificacion, numero_identificacion, dv, nombre_entidad, direccion, telefono, tipo_documento_representate, documento_representante, departamento_representante, municipio_representante, usuario_insercion, fecha_insercion, primer_nombre_representante, segundo_nombre_representante, primer_apellido_representante, segundo_apellido_representante, clase_prestador, tipo_persona, sitio_web, fax, email, departamento, municipio, estado, tipo_prestador) values ('" + tipoIdentificacion + "'," + "'" + numeroIdentificacion + "'," + "" + dv + "," + "'" + nombreEntidad + "'," + "'" + direccion + "'," + "'" + telefono + "'," + "'" + tipoDocumentoRepresentate + "'," + "'" + documentoRepresentante + "'," + "'" + departamentoExpedicion + "'," + "'" + municipioExpedicion + "'," + "'" + usuarioInsercion + "'," + " now()," + "'" + primerNombreRepresentante + "'," + "'" + segundoNombreRepresentante + "'," + "'" + primerApellidoRepresentante + "'," + "'" + segundoApellidoRepresentante + "'," + "'" + clasePrestador + "'," + "'" + tipoPersona + "'," + "'" + sitioWeb + "'," + "'" + fax + "'," + "'" + email + "'," + "'" + departamento + "'," + "'" + municipio + "'," + "'" + estado + "'," + "'" + tipoPrestador + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 615 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 618 */     catch (Exception e) {
/* 619 */       e.printStackTrace();
/* 620 */       Utilidades.writeError("RedPrestadorDAO:crearRegistro " + e.getMessage());
/*     */       
/* 622 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean modificarRegistro(String id, String codigoHabilitacion, String tipoIdentificacion, String numeroIdentificacion, int dv, String nombreEntidad, String direccion, String telefono, String tipoDocumentoRepresentate, String documentoRepresentante, String departamentoRepresentante, String municipioRepresentante, String complejidad, String usuarioModificacion, String resolucionCreacion, String numeroRegistro, String numeroLibro, String numeroFolio, String fechaInscripcion, String primerNombreRepresentante, String segundoNombreRepresentante, String primerApellidoRepresentante, String segundoApellidoRepresentante, String clasePrestador, String tipoPersona, String naturalezaJuridica, String sitioWeb, String fax, String email, String formaVinculacionRepresentante, String caracterTerritorial, String empresaSocialEstado, String actoConstitucion, String numeroActo, String fechaActo, String entidadExpide, String departamento, String municipio, String estado, String departamentoExpedicion, String municipioExpedicion, String nivelAtencion) {
/*     */     try {
/* 674 */       String s = "update red_prestador set  tipo_identificacion='" + tipoIdentificacion + "'," + " numero_identificacion='" + numeroIdentificacion + "'," + " codigo_habilitacion='" + codigoHabilitacion + "'," + " dv=" + dv + "," + " nombre_entidad='" + nombreEntidad + "'," + " direccion='" + direccion + "'," + " telefono='" + telefono + "'," + " tipo_documento_representate='" + tipoDocumentoRepresentate + "'," + " documento_representante='" + documentoRepresentante + "'," + " departamento_representante='" + departamentoRepresentante + "'," + " municipio_representante='" + municipioRepresentante + "'," + " complejidad='" + complejidad + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " resolucion_creacion='" + resolucionCreacion + "'," + " numero_registro='" + numeroRegistro + "'," + " numero_libro='" + numeroLibro + "'," + " numero_folio='" + numeroFolio + "',";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 694 */       if (fechaInscripcion.length() > 0) {
/* 695 */         s = s + " fecha_inscripcion=" + fechaInscripcion + ",";
/*     */       }
/* 697 */       s = s + " primer_nombre_representante='" + primerNombreRepresentante + "'," + " segundo_nombre_representante='" + segundoNombreRepresentante + "'," + " primer_apellido_representante='" + primerApellidoRepresentante + "'," + " segundo_apellido_representante='" + segundoApellidoRepresentante + "'," + " clase_prestador='" + clasePrestador + "'," + " tipo_persona='" + tipoPersona + "'," + " naturaleza_juridica='" + naturalezaJuridica + "'," + " sitio_web='" + sitioWeb + "'," + " fax='" + fax + "'," + " email='" + email + "'," + " forma_vinculacion_representante='" + formaVinculacionRepresentante + "'," + " caracter_territorial='" + caracterTerritorial + "'," + " empresa_social_estado='" + empresaSocialEstado + "'," + " acto_constitucion='" + actoConstitucion + "'," + " numero_acto='" + numeroActo + "',";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 712 */       if (fechaActo.length() > 0) {
/* 713 */         s = s + " fecha_acto=" + fechaActo + ",";
/*     */       }
/* 715 */       s = s + " entidad_expide='" + entidadExpide + "'," + " departamento='" + departamento + "'," + " municipio='" + municipio + "'," + " estado ='" + estado + "'," + " departamento_expedicion ='" + departamentoExpedicion + "'," + " municipio_expedicion ='" + municipioExpedicion + "'," + " nivel_atencion ='" + nivelAtencion + "'" + " where" + " numero_identificacion='" + id + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 725 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 728 */     catch (Exception e) {
/* 729 */       e.printStackTrace();
/* 730 */       Utilidades.writeError("RedPrestadorDAO:modificarRegistro " + e.getMessage());
/*     */       
/* 732 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean modificarRegistroPersonal(String tipoIdentificacion, String numeroIdentificacion, int dv, String nombreEntidad, String direccion, String telefono, String tipoDocumentoRepresentate, String documentoRepresentante, String departamentoRepresentante, String municipioRepresentante, String usuarioModificacion, String primerNombreRepresentante, String segundoNombreRepresentante, String primerApellidoRepresentante, String segundoApellidoRepresentante, String clasePrestador, String tipoPersona, String sitioWeb, String fax, String email, String estado, String departamento, String municipio) {
/*     */     try {
/* 766 */       String s = "update red_prestador set  tipo_identificacion='" + tipoIdentificacion + "'," + " numero_identificacion='" + numeroIdentificacion + "'," + " dv=" + dv + "," + " nombre_entidad='" + nombreEntidad + "'," + " direccion='" + direccion + "'," + " telefono='" + telefono + "'," + " tipo_documento_representate='" + tipoDocumentoRepresentate + "'," + " documento_representante='" + documentoRepresentante + "'," + " departamento_representante='" + departamentoRepresentante + "'," + " municipio_representante='" + municipioRepresentante + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " primer_nombre_representante='" + primerNombreRepresentante + "'," + " segundo_nombre_representante='" + segundoNombreRepresentante + "'," + " primer_apellido_representante='" + primerApellidoRepresentante + "'," + " segundo_apellido_representante='" + segundoApellidoRepresentante + "'," + " clase_prestador='" + clasePrestador + "'," + " tipo_persona='" + tipoPersona + "'," + " sitio_web='" + sitioWeb + "'," + " fax='" + fax + "'," + " email='" + email + "'," + " departamento='" + departamento + "'," + " municipio='" + municipio + "'," + " estado ='" + estado + "'" + " where" + " numero_identificacion='" + numeroIdentificacion + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 794 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 797 */     catch (Exception e) {
/* 798 */       e.printStackTrace();
/* 799 */       Utilidades.writeError("RedPrestadorDAO:modificarRegistro " + e.getMessage());
/*     */       
/* 801 */       return false;
/*     */     } 
/*     */   }
/*     */   public boolean existeNit(String nitIps) {
/*     */     try {
/* 806 */       String s = "select nombre_entidad  from red_prestador  where numero_identificacion='" + nitIps + "'";
/*     */ 
/*     */       
/* 809 */       this.dat.parseSql(s);
/* 810 */       this.rs = this.dat.getResultSet();
/* 811 */       if (this.rs.next()) {
/* 812 */         return true;
/*     */       }
/* 814 */       return false;
/*     */     }
/* 816 */     catch (Exception e) {
/* 817 */       e.printStackTrace();
/* 818 */       Utilidades.writeError("RedPrestadorDAO:cargaIps " + e.getMessage());
/*     */       
/* 820 */       return false;
/*     */     } 
/*     */   }
/*     */   public boolean existeCodigoHabilitacion(String codigo) {
/*     */     try {
/* 825 */       String s = "select nombre_entidad  from red_prestador  where codigo_habilitacion='" + codigo + "'";
/*     */ 
/*     */       
/* 828 */       this.dat.parseSql(s);
/* 829 */       this.rs = this.dat.getResultSet();
/* 830 */       if (this.rs.next()) {
/* 831 */         return true;
/*     */       }
/* 833 */       return false;
/*     */     }
/* 835 */     catch (Exception e) {
/* 836 */       e.printStackTrace();
/* 837 */       Utilidades.writeError("RedPrestadorDAO:cargaIps " + e.getMessage());
/*     */       
/* 839 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\RedPrestadorDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */