/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.RedSucursalDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.DBManager;
/*     */ import sinco.data.RedSucursalDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RedSucursalDAO
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
/*  43 */       Utilidades.writeError("RedSucursalDAO:close " + e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RedSucursalDTO next() {
/*     */     try {
/*  54 */       if (this.rs.next()) {
/*  55 */         return leerRegistro();
/*     */       }
/*     */     }
/*  58 */     catch (Exception e) {
/*  59 */       e.printStackTrace();
/*  60 */       Utilidades.writeError("RedSucursalDAO:next " + e.getMessage());
/*     */     } 
/*  62 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RedSucursalDTO leerRegistro() {
/*     */     try {
/*  72 */       RedSucursalDTO reg = new RedSucursalDTO();
/*  73 */       reg.setCodigoSucursal(this.rs.getInt("codigo_sucursal"));
/*  74 */       reg.setNumeroIdentificacion(this.rs.getString("numero_identificacion"));
/*  75 */       reg.setCodigoHabilitacion(this.rs.getString("codigo_habilitacion"));
/*  76 */       reg.setNumeroSede(this.rs.getInt("numero_sede"));
/*  77 */       reg.setNombreSede(this.rs.getString("nombre_sede"));
/*  78 */       reg.setDireccion(this.rs.getString("direccion"));
/*  79 */       reg.setTelefono(this.rs.getString("telefono"));
/*  80 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  81 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  82 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  83 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*     */ 
/*     */       
/*  86 */       reg.setDepartamento(this.rs.getString("departamento"));
/*  87 */       reg.setMunicipio(this.rs.getString("municipio"));
/*  88 */       reg.setDepartamentosExpedicion(this.rs.getString("departamentoExpedicion"));
/*  89 */       reg.setMunicipioExpedicion(this.rs.getString("municipioExpedicion"));
/*  90 */       reg.setEstado(this.rs.getString("estado"));
/*  91 */       reg.setSedePrincipal(this.rs.getString("sede_principal"));
/*  92 */       reg.setZona(this.rs.getString("zona"));
/*  93 */       reg.setBarrio(this.rs.getString("barrio"));
/*  94 */       reg.setFax(this.rs.getString("fax"));
/*  95 */       reg.setEmail(this.rs.getString("email"));
/*  96 */       reg.setPrimerNombreDirector(this.rs.getString("primer_nombre_director"));
/*  97 */       reg.setSegundoNombredirector(this.rs.getString("segundo_nombre_director"));
/*  98 */       reg.setPrimerApellidoDirector(this.rs.getString("primer_apellido_director"));
/*  99 */       reg.setSegundoApellidoDirector(this.rs.getString("segundo_apellido_director"));
/* 100 */       reg.setTipoIdentificacionDirector(this.rs.getString("tipo_identificacion_director"));
/* 101 */       reg.setNumeroIdentificacionDirector(this.rs.getString("numero_identificacion_director"));
/* 102 */       reg.setCentroPoblado(this.rs.getString("centro_poblado"));
/* 103 */       reg.setNombreEntidad(this.rs.getString("nombre_entidad"));
/* 104 */       reg.setFacturacionAgrupada(this.rs.getString("facturacion_agrupada"));
/*     */       try {
/* 106 */         reg.setNombreMunicipio(this.rs.getString("nombre_ciudad"));
/* 107 */       } catch (Exception e) {}
/* 108 */       if (this.rs.getString("tipo_persona") != null && this.rs.getString("tipo_persona") != "") {
/* 109 */         reg.setTipoPersona(this.rs.getString("tipo_persona"));
/*     */       }
/* 111 */       if (this.rs.getString("naturaleza_juridica") != null && this.rs.getString("naturaleza_juridica") != "") {
/* 112 */         reg.setNaturalezaJuridica(this.rs.getString("naturaleza_juridica"));
/*     */       }
/* 114 */       if (this.rs.getString("numero_registro") != null && this.rs.getString("numero_registro") != "") {
/* 115 */         reg.setNumeroRegistro(this.rs.getString("numero_registro"));
/*     */       }
/* 117 */       if (this.rs.getString("numero_libro") != null && this.rs.getString("numero_libro") != "") {
/* 118 */         reg.setNumeroLibro(this.rs.getString("numero_libro"));
/*     */       }
/* 120 */       if (this.rs.getString("numero_folio") != null && this.rs.getString("numero_folio") != "") {
/* 121 */         reg.setNumeroFolio(this.rs.getString("numero_folio"));
/*     */       }
/* 123 */       if (this.rs.getString("fecha_inscripcion") != null && this.rs.getString("fecha_inscripcion") != "") {
/* 124 */         reg.setFechaInscripcion(this.rs.getString("fecha_inscripcion"));
/*     */       }
/* 126 */       return reg;
/*     */     }
/* 128 */     catch (Exception e) {
/* 129 */       e.printStackTrace();
/* 130 */       Utilidades.writeError("RedSucursalDAO:leerRegistro " + e.getMessage());
/*     */       
/* 132 */       return null;
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
/*     */   public Collection<RedSucursalDTO> cargarTodos(String numeroIdentificacion, String nombreIps, String nombreSucursal, String departamento, String municipio, String tipoPrestador) {
/* 148 */     Collection<RedSucursalDTO> resultados = new ArrayList<RedSucursalDTO>();
/*     */     try {
/* 150 */       String s = "select  s.codigo_sucursal, s.codigo_habilitacion,  s.numero_identificacion, s.numero_sede, s.nombre_sede, p.nombre_entidad, d.nombre_departamento, c.nombre_ciudad, d.nombre_departamento as departamento, c.nombre_ciudad as municipio, s.direccion, s.telefono, s.usuario_insercion, s.fecha_insercion, s.usuario_modificacion, s.fecha_modificacion, s.estado, s.sede_principal, s.zona, s.barrio, s.fax, s.email, s.primer_nombre_director, s.segundo_nombre_director, s.primer_apellido_director, s.segundo_apellido_director, s.tipo_identificacion_director, s.numero_identificacion_director, s.centro_poblado, s.facturacion_agrupada from red_sucursal s,red_prestador p,par_departamento d,par_ciudad c where s.departamento=d.codigo_departamento  and s.municipio=c.codigo_ciudad  and s.departamento = c.Codigo_Departamento and c.codigo_departamento=d.codigo_departamento ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 186 */       s = s + " and s.numero_identificacion = p.numero_identificacion";
/* 187 */       if (numeroIdentificacion.length() > 0) {
/* 188 */         s = s + " and s.numero_identificacion='" + numeroIdentificacion + "'";
/*     */       }
/* 190 */       if (nombreIps.length() > 0) {
/* 191 */         s = s + " and upper(p.nombre_entidad) like upper('%" + nombreIps + "%')";
/*     */       }
/* 193 */       if (nombreSucursal.length() > 0) {
/* 194 */         s = s + " and upper(s.nombre_sede) like upper('%" + nombreSucursal + "%')";
/*     */       }
/* 196 */       if (departamento.length() > 0) {
/* 197 */         s = s + " and s.departamento='" + departamento + "'";
/*     */       }
/* 199 */       if (municipio.length() > 0) {
/* 200 */         s = s + " and s.municipio='" + municipio + "'";
/*     */       }
/* 202 */       if (tipoPrestador.length() > 0) {
/* 203 */         s = s + " and p.tipo_prestador IN ('" + tipoPrestador + "')";
/*     */       }
/* 205 */       s = s + " order by p.nombre_entidad";
/* 206 */       boolean rtaDB = this.dat.parseSql(s);
/* 207 */       if (!rtaDB) {
/* 208 */         return resultados;
/*     */       }
/* 210 */       this.rs = this.dat.getResultSet();
/* 211 */       while (this.rs.next()) {
/* 212 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 215 */     catch (Exception e) {
/* 216 */       e.printStackTrace();
/* 217 */       Utilidades.writeError("RedSucursalDAO:cargarTodos " + e.getMessage());
/*     */     } 
/* 219 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String nombreDepartamento(String codigoDepartamento) {
/* 227 */     String nombreDepartameto = "";
/*     */     try {
/* 229 */       String s = "select  codigo_departamento,  nombre_departamento  from par_departamento where 1=1  and codigo_departamento = '" + codigoDepartamento + "'";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 235 */       boolean rtaDB = this.dat.parseSql(s);
/* 236 */       if (!rtaDB) {
/* 237 */         return "no hubo resultado";
/*     */       }
/* 239 */       this.rs = this.dat.getResultSet();
/* 240 */       while (this.rs.next()) {
/* 241 */         nombreDepartameto = this.rs.getString("nombre_departamento");
/*     */       }
/*     */     }
/* 244 */     catch (Exception e) {
/* 245 */       e.printStackTrace();
/* 246 */       Utilidades.writeError("RedSucursalDAO:nombreDepartamento " + e.getMessage());
/*     */     } 
/*     */     
/* 249 */     return nombreDepartameto;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String nombreMunicipio(String codigoMunicipio, String codigoDepartamento) {
/* 257 */     String nombreMunicipio = "";
/*     */     try {
/* 259 */       String s = "SELECT codigo_ciudad, nombre_ciudad FROM public.par_ciudad where 1=1 and codigo_ciudad='" + codigoMunicipio + "' " + "and codigo_departamento='" + codigoDepartamento + "'";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 264 */       boolean rtaDB = this.dat.parseSql(s);
/* 265 */       if (!rtaDB) {
/* 266 */         return "no hubo resultado";
/*     */       }
/* 268 */       this.rs = this.dat.getResultSet();
/* 269 */       while (this.rs.next()) {
/* 270 */         nombreMunicipio = this.rs.getString("nombre_ciudad");
/*     */       }
/*     */     }
/* 273 */     catch (Exception e) {
/* 274 */       e.printStackTrace();
/* 275 */       Utilidades.writeError("RedSucursalDAO:nombreDepartamento " + e.getMessage());
/*     */     } 
/*     */     
/* 278 */     return nombreMunicipio;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String nombreNaturalezaJuridica(String codigoNaturaleza) {
/* 286 */     String nombreNaturaleza = "";
/* 287 */     if (codigoNaturaleza.equals("")) {
/* 288 */       codigoNaturaleza = "0";
/*     */     }
/*     */     try {
/* 291 */       String s = "SELECT descripcion, entero FROM sis_multivalores where 1=1 and tabla='naturaleza_juridica' and entero='" + codigoNaturaleza + "'";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 296 */       boolean rtaDB = this.dat.parseSql(s);
/* 297 */       if (!rtaDB) {
/* 298 */         return "no hubo resultado";
/*     */       }
/* 300 */       this.rs = this.dat.getResultSet();
/* 301 */       while (this.rs.next()) {
/* 302 */         nombreNaturaleza = this.rs.getString("descripcion");
/*     */       }
/*     */     }
/* 305 */     catch (Exception e) {
/* 306 */       e.printStackTrace();
/* 307 */       Utilidades.writeError("RedSucursalDAO:nombreDepartamento " + e.getMessage());
/*     */     } 
/*     */     
/* 310 */     return nombreNaturaleza;
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
/*     */   public Collection<RedSucursalDTO> cargarTodos2(String codigoSucursalContratista, String nombreIps, String nombreSucursal, String departamento, String municipio, String tipoPrestador, String tipoPersona) {
/* 327 */     Collection<RedSucursalDTO> resultados = new ArrayList<RedSucursalDTO>();
/*     */     try {
/* 329 */       String s = "select  s.codigo_sucursal, s.codigo_habilitacion,  s.numero_identificacion,  s.numero_sede,  s.nombre_sede,  p.nombre_entidad,  s.direccion,  s.telefono,  s.usuario_insercion,  s.fecha_insercion,  s.usuario_modificacion,  s.fecha_modificacion,  s.estado,  s.sede_principal,  s.zona,  s.barrio,  s.fax,  s.email,  s.primer_nombre_director,  s.segundo_nombre_director,  s.primer_apellido_director,  s.segundo_apellido_director,  s.tipo_identificacion_director,  s.numero_identificacion_director,  s.centro_poblado,  s.facturacion_agrupada,  p.tipo_prestador,  p.tipo_persona,  p.municipio,  p.departamento, p.municipio_representante as municipioExpedicion,  p.departamento_representante as departamentoExpedicion, p.naturaleza_juridica,  p.numero_registro,\t p.numero_libro,  p.numero_folio,  p.fecha_inscripcion  from red_sucursal s,red_prestador p where 1=1  and s.numero_identificacion = p.numero_identificacion ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 370 */       if (codigoSucursalContratista.length() > 0) {
/* 371 */         s = s + " and s.codigo_sucursal='" + codigoSucursalContratista + "'";
/*     */       }
/* 373 */       if (nombreIps.length() > 0) {
/* 374 */         s = s + " and upper(p.nombre_entidad) like upper('%" + nombreIps + "%')";
/*     */       }
/* 376 */       if (nombreSucursal.length() > 0) {
/* 377 */         s = s + " and upper(s.nombre_sede) like upper('%" + nombreSucursal + "%')";
/*     */       }
/* 379 */       if (departamento.length() > 0) {
/* 380 */         s = s + " and s.departamento='" + departamento + "'";
/*     */       }
/* 382 */       if (municipio.length() > 0) {
/* 383 */         s = s + " and s.municipio='" + municipio + "'";
/*     */       }
/* 385 */       if (tipoPrestador.length() > 0) {
/* 386 */         s = s + " and p.tipo_prestador IN ('" + tipoPrestador + "')";
/*     */       }
/* 388 */       if (tipoPersona.length() > 0) {
/* 389 */         s = s + " and p.tipo_persona ='" + tipoPersona + "'";
/*     */       }
/* 391 */       s = s + " order by p.nombre_entidad";
/* 392 */       boolean rtaDB = this.dat.parseSql(s);
/* 393 */       if (!rtaDB) {
/* 394 */         return resultados;
/*     */       }
/* 396 */       this.rs = this.dat.getResultSet();
/* 397 */       while (this.rs.next()) {
/* 398 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 401 */     catch (Exception e) {
/* 402 */       e.printStackTrace();
/* 403 */       Utilidades.writeError("RedSucursalDAO:cargarTodos " + e.getMessage());
/*     */     } 
/* 405 */     return resultados;
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
/*     */   public RedSucursalDTO cargarRegistro(int codigoSucursal) {
/*     */     try {
/* 418 */       String s = "select  p.tipo_persona, p.numero_registro, p.numero_libro, p.numero_folio, p.naturaleza_juridica, p.fecha_inscripcion, s.codigo_sucursal, s.codigo_habilitacion,  s.numero_identificacion, s.numero_sede, s.nombre_sede, p.nombre_entidad, d.nombre_departamento, c.nombre_ciudad, s.departamento, s.municipio, s.direccion, s.telefono, s.usuario_insercion, s.fecha_insercion, s.usuario_modificacion, s.fecha_modificacion, p.estado, s.sede_principal, s.zona, s.barrio, s.fax, s.email, p.municipio_representante as municipioExpedicion,  p.departamento_representante as departamentoExpedicion, s.primer_nombre_director, s.segundo_nombre_director, s.primer_apellido_director, s.segundo_apellido_director, s.tipo_identificacion_director, s.numero_identificacion_director, s.centro_poblado, s.facturacion_agrupada from red_sucursal s,red_prestador p ,par_departamento d,par_ciudad c";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 459 */       s = s + " where s.numero_identificacion = p.numero_identificacion" + " and codigo_sucursal=" + codigoSucursal + "" + " and s.departamento=d.codigo_departamento " + " and s.municipio=c.codigo_ciudad " + " and s.departamento = c.Codigo_Departamento" + " and c.codigo_departamento=d.codigo_departamento ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 465 */       boolean rtaDB = this.dat.parseSql(s);
/* 466 */       if (!rtaDB) {
/* 467 */         return null;
/*     */       }
/* 469 */       this.rs = this.dat.getResultSet();
/* 470 */       if (this.rs.next()) {
/* 471 */         return leerRegistro();
/*     */       }
/*     */     }
/* 474 */     catch (Exception e) {
/* 475 */       e.printStackTrace();
/* 476 */       Utilidades.writeError("RedSucursalDAO:cargarRedSucursal " + e.getMessage());
/*     */     } 
/* 478 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RedSucursalDTO cargarRegistroPorCodigoHab(String codigoHabilitacion) {
/*     */     try {
/* 490 */       String s = "select  s.codigo_sucursal, s.codigo_habilitacion,  s.numero_identificacion, s.numero_sede, s.nombre_sede, p.nombre_entidad, d.nombre_departamento, c.nombre_ciudad, s.departamento, s.municipio, s.direccion, s.telefono, s.usuario_insercion, s.fecha_insercion, s.usuario_modificacion, s.fecha_modificacion, s.estado, s.sede_principal, s.zona, s.barrio, s.fax, s.email, s.primer_nombre_director, s.segundo_nombre_director, s.primer_apellido_director, s.segundo_apellido_director, s.tipo_identificacion_director, s.numero_identificacion_director, s.centro_poblado, s.facturacion_agrupada from red_sucursal s,red_prestador p,par_departamento d,par_ciudad c";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 522 */       s = s + " where s.numero_identificacion = p.numero_identificacion" + " and s.codigo_habilitacion='" + codigoHabilitacion + "'" + " and s.departamento=d.codigo_departamento " + " and s.municipio=c.codigo_ciudad " + " and s.departamento = c.Codigo_Departamento" + " and c.codigo_departamento=d.codigo_departamento ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 528 */       boolean rtaDB = this.dat.parseSql(s);
/* 529 */       if (!rtaDB) {
/* 530 */         return null;
/*     */       }
/* 532 */       this.rs = this.dat.getResultSet();
/* 533 */       if (this.rs.next()) {
/* 534 */         return leerRegistro();
/*     */       }
/*     */     }
/* 537 */     catch (Exception e) {
/* 538 */       e.printStackTrace();
/* 539 */       Utilidades.writeError("RedSucursalDAO:cargarRedSucursal " + e.getMessage());
/*     */     } 
/* 541 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro(String numeroIdentificacion) {
/* 551 */     int inumero = 1;
/* 552 */     String s = "select max(codigo_sucursal) from red_sucursal ";
/*     */     
/*     */     try {
/* 555 */       boolean rta = this.dat.parseSql(s);
/* 556 */       if (!rta) return 0; 
/* 557 */       this.rs = this.dat.getResultSet();
/* 558 */       if (this.rs.next()) {
/* 559 */         s = this.rs.getString(1);
/* 560 */         if (!this.rs.wasNull()) {
/* 561 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 564 */       return inumero;
/*     */     }
/* 566 */     catch (Exception e) {
/* 567 */       e.printStackTrace();
/* 568 */       Utilidades.writeError("RedSucursalDAO:siguienteRegistro " + e.getMessage());
/*     */       
/* 570 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(int codigoSucursal) {
/*     */     try {
/* 580 */       String s = "delete from red_sucursal where   codigo_sucursal=" + codigoSucursal + "" + "";
/*     */ 
/*     */ 
/*     */       
/* 584 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 587 */     catch (Exception e) {
/* 588 */       e.printStackTrace();
/* 589 */       Utilidades.writeError("RedSucursalDAO:eliminarRegistro " + e.getMessage());
/*     */       
/* 591 */       return false;
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
/*     */   public int crearRegistro(int numeroSede, String codigoHabilitacion, String numeroIdentificacion, String departamento, String municipio, String direccion, String telefono, String nombreSede, String usuarioInsercion, String estado, String sedePrincipal, String zona, String barrio, String fax, String email, String primerNombreDirector, String segundoNombreDirector, String primerApellidoDirector, String segundoApellidoDirector, String tipoIdentificacionDirector, String numeroIdentificacionDirector, String centroPoblado) {
/* 624 */     int elSiguiente = siguienteRegistro(numeroIdentificacion);
/* 625 */     if (elSiguiente == 0) {
/* 626 */       return elSiguiente;
/*     */     }
/*     */     try {
/* 629 */       String s = "INSERT INTO red_sucursal( codigo_sucursal, codigo_habilitacion, numero_identificacion, numero_sede, nombre_sede, departamento, municipio, direccion, telefono, usuario_insercion, fecha_insercion, estado, sede_principal, zona, barrio, fax, email, primer_nombre_director, segundo_nombre_director, primer_apellido_director, segundo_apellido_director, tipo_identificacion_director, numero_identificacion_director, centro_poblado) VALUES (" + elSiguiente + "," + "'" + codigoHabilitacion + "'," + "'" + numeroIdentificacion + "'," + "" + numeroSede + "," + "'" + nombreSede + "'," + "'" + departamento + "'," + "'" + municipio + "'," + "'" + direccion + "'," + "'" + telefono + "'," + "'" + usuarioInsercion + "'," + " now()," + "'" + estado + "'," + "'" + sedePrincipal + "'," + "'" + zona + "'," + "'" + barrio + "'," + "'" + fax + "'," + "'" + email + "'," + "'" + primerNombreDirector + "'," + "'" + segundoNombreDirector + "'," + "'" + primerApellidoDirector + "'," + "'" + segundoApellidoDirector + "'," + "'" + tipoIdentificacionDirector + "'," + "'" + numeroIdentificacionDirector + "'," + "'" + centroPoblado + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 680 */       this.dat.executeUpdate(s);
/* 681 */       return elSiguiente;
/*     */     }
/* 683 */     catch (Exception e) {
/* 684 */       e.printStackTrace();
/* 685 */       Utilidades.writeError("RedSucursalDAO:crearRegistro " + e.getMessage());
/*     */       
/* 687 */       return elSiguiente;
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
/*     */   public boolean modificarRegistro(int codigoSucursal, int numeroSede, String codigoHabilitacion, String departamento, String municipio, String direccion, String telefono, String nombreSede, String usuarioModificacion, String estado, String sedePrincipal, String zona, String barrio, String fax, String email, String primerNombreDirector, String segundoNombreDirector, String primerApellidoDirector, String segundoApellidoDirector, String tipoIdentificacionDirector, String numeroIdentificacionDirector) {
/*     */     try {
/* 719 */       String s = "update red_sucursal set  numero_sede=" + numeroSede + "," + " codigo_habilitacion='" + codigoHabilitacion + "'," + " nombre_sede='" + nombreSede + "'," + " departamento='" + departamento + "'," + " municipio='" + municipio + "'," + " direccion='" + direccion + "'," + " telefono='" + telefono + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=now()," + " estado='" + estado + "'," + " sede_principal='" + sedePrincipal + "'," + " zona='" + zona + "'," + " barrio='" + barrio + "'," + " fax='" + fax + "'," + " email='" + email + "'," + " primer_nombre_director='" + primerNombreDirector + "'," + " segundo_nombre_director='" + segundoNombreDirector + "'," + " primer_apellido_director='" + primerApellidoDirector + "'," + " segundo_apellido_director='" + segundoApellidoDirector + "'," + " tipo_identificacion_director='" + tipoIdentificacionDirector + "'," + " numero_identificacion_director='" + numeroIdentificacionDirector + "'" + " where codigo_sucursal=" + codigoSucursal + "" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 743 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 746 */     catch (Exception e) {
/* 747 */       e.printStackTrace();
/* 748 */       Utilidades.writeError("RedSucursalDAO:modificarRegistro " + e.getMessage());
/*     */       
/* 750 */       return false;
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
/*     */   public boolean modificarNombrePersonal(String numeroIdentificacion, String nombreSede, String departamento, String municipio, String direccion, String telefono, String fax, String email, String usuarioModificacion) {
/*     */     try {
/* 764 */       String s = "update red_sucursal set  nombre_sede='" + nombreSede + "'," + " departamento='" + departamento + "'," + " municipio='" + municipio + "'," + " direccion='" + direccion + "'," + " telefono='" + telefono + "'," + " fax='" + fax + "'," + " email='" + email + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=now()" + " where numero_identificacion='" + numeroIdentificacion + "'" + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 776 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 779 */     catch (Exception e) {
/* 780 */       e.printStackTrace();
/* 781 */       Utilidades.writeError("RedSucursalDAO:modificarRegistro " + e.getMessage());
/*     */       
/* 783 */       return false;
/*     */     } 
/*     */   }
/*     */   public boolean existeSedePrincipal(String codigo) {
/*     */     try {
/* 788 */       String s = "select codigo_sucursal from red_sucursal  where numero_identificacion='" + codigo + "'" + " and sede_principal='S'";
/*     */ 
/*     */ 
/*     */       
/* 792 */       this.dat.parseSql(s);
/* 793 */       this.rs = this.dat.getResultSet();
/* 794 */       if (this.rs.next()) {
/* 795 */         return true;
/*     */       }
/* 797 */       return false;
/*     */     }
/* 799 */     catch (Exception e) {
/* 800 */       e.printStackTrace();
/* 801 */       Utilidades.writeError("RedSucursalDAO:cargaIps " + e.getMessage());
/*     */       
/* 803 */       return false;
/*     */     } 
/*     */   }
/*     */   public RedSucursalDTO obtenerSedePrincipal(String codigo) {
/*     */     try {
/* 808 */       String s = "select codigo_sucursal from red_sucursal  where numero_identificacion='" + codigo + "'" + " and sede_principal='S'";
/*     */ 
/*     */ 
/*     */       
/* 812 */       RedSucursalDTO reg = new RedSucursalDTO();
/* 813 */       boolean rtaDB = this.dat.parseSql(s);
/* 814 */       if (!rtaDB) {
/* 815 */         return null;
/*     */       }
/* 817 */       this.rs = this.dat.getResultSet();
/* 818 */       if (this.rs.next()) {
/* 819 */         reg.setCodigoSucursal(this.rs.getInt("codigo_sucursal"));
/* 820 */         return reg;
/*     */       }
/*     */     
/* 823 */     } catch (Exception e) {
/* 824 */       e.printStackTrace();
/* 825 */       Utilidades.writeError("RedSucursalDAO:obtenerSedePrincipal " + e.getMessage());
/*     */     } 
/* 827 */     return null;
/*     */   }
/*     */   
/*     */   public boolean existeCodigoHabilitacion(String codigo) {
/*     */     try {
/* 832 */       String s = "select nombre_sede  from red_sucursal  where codigo_habilitacion='" + codigo + "'";
/*     */ 
/*     */       
/* 835 */       this.dat.parseSql(s);
/* 836 */       this.rs = this.dat.getResultSet();
/* 837 */       if (this.rs.next()) {
/* 838 */         return true;
/*     */       }
/* 840 */       return false;
/*     */     }
/* 842 */     catch (Exception e) {
/* 843 */       e.printStackTrace();
/* 844 */       Utilidades.writeError("RedsucursalDAO:existeCodigoHabilitacion " + e.getMessage());
/*     */       
/* 846 */       return false;
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
/*     */   public Collection<RedSucursalDTO> cargarTodosDistinct(String nombre, String departamento, String municipio) {
/* 860 */     Collection<RedSucursalDTO> resultados = new ArrayList<RedSucursalDTO>();
/*     */     try {
/* 862 */       String s = "select DISTINCT (s.codigo_habilitacion), s.codigo_sucursal, s.codigo_habilitacion,  s.numero_identificacion, s.numero_sede, s.nombre_sede, p.nombre_entidad, d.nombre_departamento, c.nombre_ciudad, d.nombre_departamento as departamento, c.nombre_ciudad as municipio, s.direccion, s.telefono, s.usuario_insercion, s.fecha_insercion, s.usuario_modificacion, s.fecha_modificacion, s.estado, s.sede_principal, s.zona, s.barrio, s.fax, s.email, s.primer_nombre_director, s.segundo_nombre_director, s.primer_apellido_director, s.segundo_apellido_director, s.tipo_identificacion_director, s.numero_identificacion_director, s.centro_poblado, s.facturacion_agrupada, CASE WHEN s.sede_principal='S' THEN 1 ELSE 2 END AS orden from red_sucursal s,red_prestador p,par_departamento d,par_ciudad c where s.departamento=d.codigo_departamento  and s.municipio=c.codigo_ciudad  and s.departamento = c.Codigo_Departamento and c.codigo_departamento=d.codigo_departamento  and s.numero_identificacion = p.numero_identificacion";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 901 */       if (nombre.length() > 0) {
/* 902 */         s = s + " and upper(p.nombre_entidad) like upper('%" + nombre + "%')";
/*     */       }
/* 904 */       if (departamento.length() > 0) {
/* 905 */         s = s + " and s.departamento='" + departamento + "'";
/*     */       }
/* 907 */       if (municipio.length() > 0) {
/* 908 */         s = s + " and s.municipio='" + municipio + "'";
/*     */       }
/* 910 */       s = s + " order by p.nombre_entidad,orden";
/* 911 */       boolean rtaDB = this.dat.parseSql(s);
/* 912 */       if (!rtaDB) {
/* 913 */         return resultados;
/*     */       }
/* 915 */       this.rs = this.dat.getResultSet();
/* 916 */       while (this.rs.next()) {
/* 917 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 920 */     catch (Exception e) {
/* 921 */       e.printStackTrace();
/* 922 */       Utilidades.writeError("RedSucursalDAO:cargarTodos " + e.getMessage());
/*     */     } 
/* 924 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean modificarSede(int codigoSucursal) {
/*     */     try {
/* 931 */       String s = "update red_sucursal set  sede_principal='N' where codigo_sucursal=" + codigoSucursal + "" + "";
/*     */ 
/*     */ 
/*     */       
/* 935 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 938 */     catch (Exception e) {
/* 939 */       e.printStackTrace();
/* 940 */       Utilidades.writeError("RedSucursalDAO:modificarRegistro " + e.getMessage());
/*     */       
/* 942 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean modificarInformacionFacturacion(int codigoSucursal, String facturacionAgrupada) {
/*     */     try {
/* 949 */       String s = "update red_sucursal set  facturacion_agrupada='" + facturacionAgrupada + "'" + " where codigo_sucursal=" + codigoSucursal + "" + "";
/*     */ 
/*     */ 
/*     */       
/* 953 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 956 */     catch (Exception e) {
/* 957 */       e.printStackTrace();
/* 958 */       Utilidades.writeError("RedSucursalDAO:modificarInformacionFacturacion " + e.getMessage());
/*     */       
/* 960 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\RedSucursalDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */