/*      */ package sinco.data;
/*      */ 
/*      */ import java.sql.ResultSet;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import sinco.business.CalDocumentosDTO;
/*      */ import sinco.business.Utilidades;
/*      */ import sinco.data.CalDocumentosDAO;
/*      */ import sinco.data.DBManager;
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
/*      */ public class CalDocumentosDAO
/*      */ {
/*      */   ResultSet rs;
/*   25 */   DBManager dat = new DBManager();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   34 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close() {
/*      */     try {
/*   41 */       this.dat.close();
/*      */     }
/*   43 */     catch (Exception e) {
/*   44 */       Utilidades.writeError("CalDocumentosFactory:close", e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CalDocumentosDTO next() {
/*      */     try {
/*   55 */       if (this.rs.next()) {
/*   56 */         return leerRegistro();
/*      */       }
/*      */     }
/*   59 */     catch (Exception e) {
/*   60 */       e.printStackTrace();
/*   61 */       Utilidades.writeError("CalDocumentosFactory:next ", e);
/*      */     } 
/*   63 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private CalDocumentosDTO leerRegistro() {
/*      */     try {
/*   73 */       CalDocumentosDTO reg = new CalDocumentosDTO();
/*   74 */       reg.setCodigo(this.rs.getString("codigo"));
/*   75 */       reg.setTipoDocumento(this.rs.getString("tipo_documento"));
/*   76 */       reg.setProceso(this.rs.getString("proceso"));
/*   77 */       reg.setSubproceso(this.rs.getString("subproceso"));
/*   78 */       reg.setServicio(this.rs.getString("servicio"));
/*   79 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*   80 */       reg.setVersion(this.rs.getString("version"));
/*   81 */       reg.setFechaVersion(this.rs.getString("fecha_version"));
/*   82 */       reg.setFechaEmision(this.rs.getString("fecha_emision"));
/*   83 */       reg.setEstado(this.rs.getString("estado"));
/*   84 */       reg.setExistePdf(this.rs.getString("existe_pdf"));
/*   85 */       reg.setExisteWord(this.rs.getString("existe_word"));
/*   86 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*   87 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*   88 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*   89 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*   90 */       reg.setNombreProceso(this.rs.getString("nombre_proceso"));
/*   91 */       reg.setNombreSubProceso(this.rs.getString("nombre_subproceso"));
/*   92 */       reg.setNombreArchivoWord(this.rs.getString("nombre_archivo_word"));
/*   93 */       reg.setNombreArchivoPdf(this.rs.getString("nombre_archivo_pdf"));
/*   94 */       reg.setFechaRevision(this.rs.getString("fecha_revision"));
/*   95 */       reg.setObservaciones(this.rs.getString("observaciones"));
/*   96 */       reg.setResponsables(this.rs.getString("responsables"));
/*   97 */       reg.setOrden(this.rs.getInt("orden"));
/*   98 */       reg.setAsociado_a(this.rs.getString("asociado_a"));
/*   99 */       return reg;
/*      */     }
/*  101 */     catch (Exception e) {
/*  102 */       e.printStackTrace();
/*  103 */       Utilidades.writeError("CalDocumentosFactory:leerRegistro ", e);
/*      */       
/*  105 */       return null;
/*      */     } 
/*      */   }
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
/*      */   public boolean cargarTodos(String codigo, String tipoDocumento, String proceso, String subproceso, String servicio, String estado, String descripcion, String observaciones) {
/*      */     try {
/*  125 */       String s = "select doc.codigo,doc.tipo_documento,doc.proceso,doc.subproceso,doc.servicio,doc.descripcion,doc.version,doc.fecha_version,doc.fecha_emision,doc.estado,case when trim(nombre_archivo_word) is not null then 'S' else 'N' end existe_word,case when trim(nombre_archivo_pdf) is not null then 'S' else 'N' end existe_pdf,doc.fecha_insercion,doc.usuario_insercion,doc.fecha_modificacion,doc.usuario_modificacion,doc.nombre_archivo_word,doc.nombre_archivo_pdf,doc.observaciones,doc.fecha_revision,doc.orden,doc.asociado_a,procesos.descripcion as nombre_proceso,subprocesos.descripcion as nombre_subproceso,FUN_RESPONSABLES_DOCUMENTO(doc.codigo) as responsables from   Cal_Documentos Doc left   join Procesos  on     (Doc.Proceso = Procesos.Codigo and Procesos.Estado = 'A') left   join Subprocesos on     (Doc.Proceso = Subprocesos.Proceso and Doc.Subproceso = Subprocesos.Codigo and Subprocesos.Estado = 'A') where 1=1";
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
/*  154 */       if (codigo.length() > 0) {
/*  155 */         s = s + " and  doc.codigo='" + codigo + "'";
/*      */       }
/*  157 */       if (proceso != null && proceso.length() > 0) {
/*  158 */         s = s + " and doc.proceso='" + proceso + "'";
/*      */       }
/*  160 */       if (subproceso.length() > 0) {
/*  161 */         s = s + " and doc.subproceso='" + subproceso + "'";
/*      */       }
/*      */       
/*  164 */       if (servicio.length() > 0) {
/*  165 */         s = s + " and doc.servicio='" + servicio + "'";
/*      */       }
/*  167 */       if (tipoDocumento.length() > 0) {
/*  168 */         s = s + " and doc.tipo_documento='" + tipoDocumento + "'";
/*      */       }
/*  170 */       if (descripcion.length() > 0) {
/*  171 */         s = s + " and upper(doc.descripcion) like upper('%" + descripcion + "%')";
/*      */       }
/*  173 */       if (observaciones.length() > 0) {
/*  174 */         s = s + " and upper(doc.observaciones) like upper('%" + observaciones + "%')";
/*      */       }
/*  176 */       if (estado.length() > 0) {
/*  177 */         s = s + " and doc.estado='" + estado + "'";
/*      */       }
/*      */       
/*  180 */       s = s + " order by doc.proceso,doc.subproceso,doc.codigo";
/*  181 */       boolean rtaDB = this.dat.parseSql(s);
/*  182 */       if (!rtaDB) {
/*  183 */         return false;
/*      */       }
/*      */       
/*  186 */       this.rs = this.dat.getResultSet();
/*  187 */       return true;
/*      */     }
/*  189 */     catch (Exception e) {
/*  190 */       e.printStackTrace();
/*  191 */       Utilidades.writeError("CalDocumentosFactory:cargarTodos ", e);
/*      */       
/*  193 */       return false;
/*      */     } 
/*      */   }
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
/*      */   public Collection<CalDocumentosDTO> cargarTodos(String codigo, String tipoDocumento, String proceso, String subproceso, String unidadServicio, String descripcion, String version, String fechaVersion, String fechaEmision, String observaciones, String estado, int responsable, int distribuido) {
/*  217 */     Collection<CalDocumentosDTO> resultados = new ArrayList<CalDocumentosDTO>();
/*      */     
/*      */     try {
/*  220 */       String s = "select doc.codigo,doc.tipo_documento,doc.proceso,doc.subproceso,doc.servicio,doc.descripcion,doc.version,doc.fecha_version,doc.fecha_emision,doc.estado,case when trim(nombre_archivo_word) is not null then 'S' else 'N' end existe_word,case when trim(nombre_archivo_pdf) is not null then 'S' else 'N' end existe_pdf,doc.fecha_insercion,doc.usuario_insercion,doc.fecha_modificacion,doc.usuario_modificacion,doc.nombre_archivo_word,doc.nombre_archivo_pdf,doc.observaciones,doc.orden,doc.asociado_a,doc.fecha_revision,procesos.descripcion as nombre_proceso,subprocesos.descripcion as nombre_subproceso,FUN_RESPONSABLES_DOCUMENTO(doc.codigo) as responsables from   Cal_Documentos Doc left   join Procesos  on     (Doc.Proceso = Procesos.Codigo and Procesos.Estado = 'A') left   join Subprocesos on     (Doc.Proceso = Subprocesos.Proceso and Doc.Subproceso = Subprocesos.Codigo and Subprocesos.Estado = 'A') where 1=1";
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
/*  250 */       if (codigo.length() > 0) {
/*  251 */         s = s + " and  doc.codigo='" + codigo + "'";
/*      */       }
/*  253 */       if (proceso != null && proceso.length() > 0) {
/*  254 */         s = s + " and doc.proceso='" + proceso + "'";
/*      */       }
/*  256 */       if (subproceso.length() > 0) {
/*  257 */         s = s + " and doc.subproceso='" + subproceso + "'";
/*      */       }
/*      */       
/*  260 */       if (unidadServicio.length() > 0) {
/*  261 */         s = s + " and doc.servicio='" + unidadServicio + "'";
/*      */       }
/*      */       
/*  264 */       if (tipoDocumento.length() > 0) {
/*  265 */         s = s + " and doc.tipo_documento='" + tipoDocumento + "'";
/*      */       }
/*  267 */       if (descripcion.length() > 0) {
/*  268 */         s = s + " and upper(doc.descripcion) like upper('%" + descripcion + "%')";
/*      */       }
/*  270 */       if (observaciones.length() > 0) {
/*  271 */         s = s + " and upper(doc.observaciones) like upper('%" + observaciones + "%')";
/*      */       }
/*  273 */       if (estado.length() > 0) {
/*  274 */         s = s + " and doc.estado='" + estado + "'";
/*      */       }
/*      */       
/*  277 */       if (version.length() > 0) {
/*  278 */         s = s + " and upper(doc.version) like upper('%" + version + "%')";
/*      */       }
/*  280 */       if (fechaVersion.length() > 0) {
/*  281 */         s = s + " and upper(doc.fecha_version) like upper('%" + fechaVersion + "%')";
/*      */       }
/*  283 */       if (fechaEmision.length() > 0) {
/*  284 */         s = s + " and upper(doc.fecha_emision) like upper('%" + fechaEmision + "%')";
/*      */       }
/*      */       
/*  287 */       if (responsable > 0) {
/*  288 */         s = s + " and exists (select 'x' from cal_documentos_responsables d" + " where doc.codigo=d.codigo_documento and d.codigo_area=" + responsable + ")";
/*      */       }
/*      */ 
/*      */       
/*  292 */       if (distribuido > 0) {
/*  293 */         s = s + " and exists (select 'x' from cal_documentos_distribuido d" + " where doc.codigo=d.codigo_documento and d.codigo_area=" + distribuido + ")";
/*      */       }
/*      */ 
/*      */       
/*  297 */       s = s + " order by doc.proceso,doc.subproceso,doc.codigo";
/*  298 */       boolean rtaDB = this.dat.parseSql(s);
/*  299 */       if (!rtaDB) {
/*  300 */         return resultados;
/*      */       }
/*  302 */       this.rs = this.dat.getResultSet();
/*  303 */       while (this.rs.next()) {
/*  304 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  307 */     catch (Exception e) {
/*  308 */       e.printStackTrace();
/*  309 */       Utilidades.writeError("CalDocumentosDAO:cargarTodos ", e);
/*      */     } 
/*  311 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CalDocumentosDTO cargarDocumento(String codigo) {
/*      */     try {
/*  322 */       String s = "select doc.codigo,doc.tipo_documento,doc.proceso,doc.subproceso,doc.servicio,doc.descripcion,doc.version,doc.fecha_version,doc.fecha_emision,doc.estado,case when trim(nombre_archivo_word) is not null then 'S' else 'N' end existe_word,case when trim(nombre_archivo_pdf) is not null then 'S' else 'N' end existe_pdf,doc.fecha_insercion,doc.usuario_insercion,doc.fecha_modificacion,doc.usuario_modificacion,doc.nombre_archivo_word,doc.nombre_archivo_pdf,doc.observaciones,doc.orden,doc.asociado_a,doc.fecha_revision,procesos.descripcion as nombre_proceso,subprocesos.descripcion as nombre_subproceso,'' as responsables from   Cal_Documentos Doc left   join Procesos  on     (Doc.Proceso = Procesos.Codigo and Procesos.Estado = 'A') left   join Subprocesos on     (Doc.Proceso = Subprocesos.Proceso and Doc.Subproceso = Subprocesos.Codigo and Subprocesos.Estado = 'A') where doc.codigo='" + codigo + "'";
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
/*  351 */       boolean rtaDB = this.dat.parseSql(s);
/*  352 */       if (!rtaDB) {
/*  353 */         return null;
/*      */       }
/*      */       
/*  356 */       this.rs = this.dat.getResultSet();
/*  357 */       if (this.rs.next()) {
/*  358 */         return leerRegistro();
/*      */       }
/*      */     }
/*  361 */     catch (Exception e) {
/*  362 */       e.printStackTrace();
/*  363 */       Utilidades.writeError("CalDocumentosFactory:cargarCalDocumentos ", e);
/*      */     } 
/*  365 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean eliminarRegistro(String codigo) {
/*      */     try {
/*  376 */       String s = "delete from  cal_documentos where codigo='" + codigo + "'";
/*  377 */       return this.dat.executeUpdate(s);
/*      */     
/*      */     }
/*  380 */     catch (Exception e) {
/*  381 */       e.printStackTrace();
/*  382 */       Utilidades.writeError("CalDocumentosFactory:eliminarRegistro", e);
/*      */       
/*  384 */       return false;
/*      */     } 
/*      */   }
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
/*      */   public boolean crearRegistro(String codigo, String tipoDocumento, String proceso, String subproceso, String servicio, String descripcion, String version, String fechaVersion, String fechaEmision, String estado, String observaciones, int orden, String asociado_a, String usuarioInsercion) {
/*      */     try {
/*  409 */       String s = "insert into cal_documentos (";
/*  410 */       s = s + "codigo,";
/*  411 */       s = s + "tipo_documento,";
/*  412 */       s = s + "proceso,";
/*  413 */       s = s + "subproceso,";
/*  414 */       s = s + "servicio,";
/*  415 */       s = s + "descripcion,";
/*  416 */       s = s + "version,";
/*  417 */       s = s + "fecha_version,";
/*  418 */       s = s + "fecha_emision,";
/*  419 */       s = s + "estado,";
/*  420 */       s = s + "observaciones,";
/*  421 */       s = s + "orden,";
/*  422 */       s = s + "asociado_a,";
/*  423 */       s = s + "fecha_insercion,";
/*  424 */       s = s + "usuario_insercion)";
/*  425 */       s = s + " values (";
/*  426 */       s = s + "'" + codigo + "',";
/*  427 */       s = s + "'" + tipoDocumento + "',";
/*  428 */       s = s + "'" + proceso + "',";
/*  429 */       s = s + ((subproceso.length() == 0) ? "null" : ("'" + subproceso + "'")) + ",";
/*  430 */       s = s + ((servicio.length() == 0) ? "null" : ("'" + servicio + "'")) + ",";
/*  431 */       s = s + "'" + descripcion + "',";
/*  432 */       s = s + "'" + version + "',";
/*  433 */       s = s + "'" + fechaVersion + "',";
/*  434 */       s = s + "'" + fechaEmision + "',";
/*  435 */       s = s + "'" + estado + "',";
/*  436 */       s = s + "'" + observaciones + "',";
/*  437 */       s = s + "" + orden + ",";
/*  438 */       s = s + "'" + asociado_a + "',";
/*  439 */       s = s + "" + Utilidades.getFechaBD() + ",";
/*  440 */       s = s + "'" + usuarioInsercion + "'";
/*  441 */       s = s + ")";
/*  442 */       return this.dat.executeUpdate(s);
/*      */     
/*      */     }
/*  445 */     catch (Exception e) {
/*  446 */       e.printStackTrace();
/*  447 */       Utilidades.writeError("CalDocumentosFactory:crearRegistro", e);
/*      */       
/*  449 */       return false;
/*      */     } 
/*      */   }
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
/*      */   public boolean modificarRegistro(String codigo, String tipoDocumento, String proceso, String subproceso, String servicio, String descripcion, String version, String fechaVersion, String fechaEmision, String estado, String observaciones, int orden, String usuarioModificacion) {
/*      */     try {
/*  473 */       String s = "update cal_documentos set ";
/*  474 */       s = s + " tipo_documento='" + tipoDocumento + "',";
/*  475 */       s = s + " proceso='" + proceso + "',";
/*  476 */       s = s + " subproceso=" + ((subproceso.length() == 0) ? "null" : ("'" + subproceso + "'")) + ",";
/*  477 */       s = s + " servicio=" + ((servicio.length() == 0) ? "null" : ("'" + servicio + "'")) + ",";
/*  478 */       s = s + " descripcion='" + descripcion + "',";
/*  479 */       s = s + " version='" + version + "',";
/*  480 */       s = s + " fecha_version='" + fechaVersion + "',";
/*  481 */       s = s + " fecha_emision='" + fechaEmision + "',";
/*  482 */       s = s + " estado='" + estado + "',";
/*  483 */       s = s + " observaciones='" + observaciones + "',";
/*  484 */       s = s + " orden=" + orden + ",";
/*  485 */       s = s + " fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/*  486 */       s = s + " usuario_modificacion='" + usuarioModificacion + "'";
/*  487 */       s = s + " where ";
/*  488 */       s = s + " codigo='" + codigo + "'";
/*  489 */       return this.dat.executeUpdate(s);
/*      */     
/*      */     }
/*  492 */     catch (Exception e) {
/*  493 */       e.printStackTrace();
/*  494 */       Utilidades.writeError("CalDocumentosFactory:modificarRegistro", e);
/*      */       
/*  496 */       return false;
/*      */     } 
/*      */   }
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
/*      */   public boolean actualizarAnexos(String codigo, String tipoDocumento, String nombreArchivo, String usuarioModificacion) {
/*      */     try {
/*  513 */       String s = "update cal_documentos set ";
/*      */       
/*  515 */       if (tipoDocumento.equals("W")) {
/*  516 */         s = s + " nombre_archivo_word='" + nombreArchivo + "',";
/*      */       } else {
/*      */         
/*  519 */         s = s + " nombre_archivo_pdf='" + nombreArchivo + "',";
/*      */       } 
/*  521 */       s = s + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where " + " codigo='" + codigo + "'";
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  526 */       return this.dat.executeUpdate(s);
/*      */     
/*      */     }
/*  529 */     catch (Exception e) {
/*  530 */       e.printStackTrace();
/*  531 */       Utilidades.writeError("CalDocumentosFactory:modificarRegistro", e);
/*      */       
/*  533 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean eliminarAreasDistribuido(String documento) {
/*      */     try {
/*  540 */       String s = "delete from cal_documentos_distribuido where codigo_documento='" + documento + "'";
/*  541 */       return this.dat.executeUpdate(s);
/*      */     
/*      */     }
/*  544 */     catch (Exception e) {
/*  545 */       e.printStackTrace();
/*  546 */       Utilidades.writeError("CalDocumentosFactory:crearRegistro", e);
/*      */       
/*  548 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean crearAreasDistribuido(String documento, int area, String usuarioInsercion) {
/*      */     try {
/*  559 */       String s = "insert into cal_documentos_distribuido (codigo_documento,codigo_area,fecha_insercion,usuario_insercion)";
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  564 */       s = s + " values (";
/*  565 */       s = s + "'" + documento + "',";
/*  566 */       s = s + "" + area + ",";
/*  567 */       s = s + "" + Utilidades.getFechaBD() + ",";
/*  568 */       s = s + "'" + usuarioInsercion + "'";
/*  569 */       s = s + ")";
/*  570 */       return this.dat.executeUpdate(s);
/*      */     
/*      */     }
/*  573 */     catch (Exception e) {
/*  574 */       e.printStackTrace();
/*  575 */       Utilidades.writeError("CalDocumentosFactory:crearRegistro", e);
/*      */       
/*  577 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean crearResponsable(String documento, int area, String usuarioInsercion) {
/*      */     try {
/*  589 */       String s = "insert into cal_documentos_responsables (codigo_documento,codigo_area,fecha_insercion,usuario_insercion)";
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  594 */       s = s + " values (";
/*  595 */       s = s + "'" + documento + "',";
/*  596 */       s = s + "" + area + ",";
/*  597 */       s = s + "" + Utilidades.getFechaBD() + ",";
/*  598 */       s = s + "'" + usuarioInsercion + "'";
/*  599 */       s = s + ")";
/*  600 */       return this.dat.executeUpdate(s);
/*      */     
/*      */     }
/*  603 */     catch (Exception e) {
/*  604 */       e.printStackTrace();
/*  605 */       Utilidades.writeError("CalDocumentosFactory:crearRegistro", e);
/*      */       
/*  607 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean eliminarResponsables(String documento) {
/*      */     try {
/*  615 */       String s = "delete from cal_documentos_responsables where codigo_documento='" + documento + "'";
/*  616 */       return this.dat.executeUpdate(s);
/*      */     
/*      */     }
/*  619 */     catch (Exception e) {
/*  620 */       e.printStackTrace();
/*  621 */       Utilidades.writeError("CalDocumentosFactory:crearRegistro", e);
/*      */       
/*  623 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<CalDocumentosDTO> cargarResponsables(String documento) {
/*  633 */     Collection<CalDocumentosDTO> resultados = new ArrayList<CalDocumentosDTO>();
/*      */     try {
/*  635 */       String s = "select cal_documentos_responsables.codigo_area,view_responsables.descripcion";
/*  636 */       s = s + " from cal_documentos_responsables,view_responsables";
/*  637 */       s = s + " where  cal_documentos_responsables.codigo_area=view_responsables.codigo";
/*  638 */       s = s + " and cal_documentos_responsables.codigo_documento='" + documento + "'";
/*  639 */       s = s + " order by view_responsables.descripcion";
/*  640 */       boolean rtaDB = this.dat.parseSql(s);
/*  641 */       if (!rtaDB) {
/*  642 */         return resultados;
/*      */       }
/*  644 */       this.rs = this.dat.getResultSet();
/*  645 */       if (!rtaDB) {
/*  646 */         return resultados;
/*      */       }
/*      */       
/*  649 */       while (this.rs.next()) {
/*  650 */         CalDocumentosDTO reg = new CalDocumentosDTO();
/*  651 */         reg.setCodigoArea(this.rs.getInt("codigo_area"));
/*  652 */         reg.setDescripcion(this.rs.getString("descripcion"));
/*  653 */         resultados.add(reg);
/*      */       }
/*      */     
/*  656 */     } catch (Exception e) {
/*  657 */       e.printStackTrace();
/*  658 */       Utilidades.writeError("CalDocumentosFactory:cargarDistribuido ", e);
/*      */     } 
/*  660 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<CalDocumentosDTO> cargarDistribuido(String documento) {
/*  670 */     Collection<CalDocumentosDTO> resultados = new ArrayList<CalDocumentosDTO>();
/*      */     try {
/*  672 */       String s = "select cal_documentos_distribuido.codigo_area,view_responsables.descripcion from cal_documentos_distribuido,view_responsables where  cal_documentos_distribuido.codigo_area=view_responsables.codigo and cal_documentos_distribuido.codigo_documento='" + documento + "'" + " order by view_responsables.descripcion";
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  677 */       boolean rtaDB = this.dat.parseSql(s);
/*  678 */       if (!rtaDB) {
/*  679 */         return resultados;
/*      */       }
/*  681 */       this.rs = this.dat.getResultSet();
/*  682 */       if (!rtaDB) {
/*  683 */         return resultados;
/*      */       }
/*      */       
/*  686 */       while (this.rs.next()) {
/*  687 */         CalDocumentosDTO reg = new CalDocumentosDTO();
/*  688 */         reg.setCodigoArea(this.rs.getInt("codigo_area"));
/*  689 */         reg.setDescripcion(this.rs.getString("descripcion"));
/*  690 */         resultados.add(reg);
/*      */       }
/*      */     
/*  693 */     } catch (Exception e) {
/*  694 */       e.printStackTrace();
/*  695 */       Utilidades.writeError("CalDocumentosFactory:cargarDistribuido ", e);
/*      */     } 
/*  697 */     return resultados;
/*      */   }
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
/*      */   public Collection<CalDocumentosDTO> cargarDocumentosDeProceso(String proceso, String subproceso, String visualizar) {
/*  711 */     Collection<CalDocumentosDTO> resultados = new ArrayList<CalDocumentosDTO>();
/*      */     try {
/*  713 */       String s = "select  doc.codigo, doc.tipo_documento, doc.descripcion, doc.version, doc.fecha_revision, doc.fecha_version from cal_documentos doc,cal_tipos_documento t where doc.tipo_documento=t.codigo  and doc.PROCESO='" + proceso + "'" + " and doc.estado in('A','R')";
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
/*  725 */       if (visualizar.equals("M")) {
/*  726 */         s = s + " and t.mostrar_en_mapa='S'";
/*      */       }
/*  728 */       else if (visualizar.equals("L")) {
/*  729 */         s = s + " and t.mostrar_en_lista_maestra='S'";
/*      */       }
/*  731 */       else if (visualizar.equals("P")) {
/*  732 */         s = s + " and t.mostrar_en_planes='S'";
/*      */       } 
/*      */       
/*  735 */       if (subproceso.length() > 0) {
/*  736 */         s = s + " AND doc.SUBPROCESO='" + subproceso + "'";
/*      */       } else {
/*      */         
/*  739 */         s = s + " AND doc.SUBPROCESO is null";
/*      */       } 
/*  741 */       s = s + " order by doc.codigo";
/*  742 */       boolean rtaDB = this.dat.parseSql(s);
/*  743 */       this.rs = this.dat.getResultSet();
/*  744 */       if (!rtaDB) {
/*  745 */         return resultados;
/*      */       }
/*      */       
/*  748 */       while (this.rs.next()) {
/*  749 */         CalDocumentosDTO reg = new CalDocumentosDTO();
/*      */         
/*  751 */         reg.setCodigo(this.rs.getString("codigo"));
/*  752 */         reg.setTipoDocumento(this.rs.getString("tipo_documento"));
/*  753 */         reg.setDescripcion(this.rs.getString("descripcion"));
/*  754 */         reg.setVersion(this.rs.getString("version"));
/*  755 */         reg.setFechaVersion(this.rs.getString("fecha_version"));
/*  756 */         reg.setFechaRevision(this.rs.getString("fecha_revision"));
/*      */         
/*  758 */         resultados.add(reg);
/*      */       }
/*      */     
/*  761 */     } catch (Exception e) {
/*  762 */       e.printStackTrace();
/*  763 */       Utilidades.writeError("CalProcesosDAO:cargarDocumentosDeProceso ", e);
/*      */     } 
/*  765 */     return resultados;
/*      */   }
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
/*      */   public Collection<CalDocumentosDTO> cargarDocumentosDeProcesoPorTipo(String proceso, String subproceso, String tipoDocumento, String visualizar) {
/*  780 */     Collection<CalDocumentosDTO> resultados = new ArrayList<CalDocumentosDTO>();
/*      */     try {
/*  782 */       String s = "select  doc.codigo, doc.tipo_documento, doc.descripcion, doc.version, doc.fecha_version, doc.estado, doc.fecha_revision, doc.nombre_archivo_word, doc.nombre_archivo_pdf from cal_documentos doc,cal_tipos_documento t where doc.tipo_documento=t.codigo and doc.PROCESO='" + proceso + "'" + " and (doc.nombre_archivo_pdf is not null OR doc.nombre_archivo_word is not null)";
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
/*  799 */       if (subproceso.equals("")) {
/*  800 */         s = s + " AND doc.SUBPROCESO is null";
/*      */ 
/*      */         
/*  803 */         if (tipoDocumento.equals("NM")) {
/*  804 */           s = s + " and doc.asociado_a is not null";
/*      */         }
/*  806 */         else if (tipoDocumento.length() > 0) {
/*  807 */           s = s + " and doc.tipo_documento='" + tipoDocumento + "'";
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*  813 */       else if (subproceso.length() > 0) {
/*  814 */         s = s + " AND doc.SUBPROCESO='" + subproceso + "'";
/*      */         
/*  816 */         if (tipoDocumento.equals("NM")) {
/*  817 */           s = s + " and doc.asociado_a is not null";
/*      */         }
/*  819 */         else if (tipoDocumento.length() > 0) {
/*  820 */           s = s + " and doc.tipo_documento='" + tipoDocumento + "'";
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  827 */       if (visualizar.equals("M")) {
/*  828 */         s = s + " and t.mostrar_en_mapa='S'";
/*      */       }
/*  830 */       else if (visualizar.equals("L")) {
/*  831 */         s = s + " and t.mostrar_en_lista_maestra='S'";
/*      */       }
/*  833 */       else if (visualizar.equals("P")) {
/*  834 */         s = s + " and t.mostrar_en_planes='S'";
/*      */       } 
/*      */ 
/*      */       
/*  838 */       s = s + " order by coalesce(doc.orden,0),doc.descripcion";
/*  839 */       boolean rtaDB = this.dat.parseSql(s);
/*  840 */       this.rs = this.dat.getResultSet();
/*  841 */       if (!rtaDB) {
/*  842 */         return resultados;
/*      */       }
/*      */       
/*  845 */       while (this.rs.next()) {
/*  846 */         CalDocumentosDTO reg = new CalDocumentosDTO();
/*      */         
/*  848 */         reg.setCodigo(this.rs.getString("codigo"));
/*  849 */         reg.setTipoDocumento(this.rs.getString("tipo_documento"));
/*  850 */         reg.setDescripcion(this.rs.getString("descripcion"));
/*  851 */         reg.setVersion(this.rs.getString("version"));
/*  852 */         reg.setEstado(this.rs.getString("estado"));
/*  853 */         reg.setFechaVersion(this.rs.getString("fecha_version"));
/*  854 */         reg.setFechaRevision(this.rs.getString("fecha_revision"));
/*  855 */         reg.setNombreArchivoPdf(this.rs.getString("nombre_archivo_pdf"));
/*  856 */         reg.setNombreArchivoWord(this.rs.getString("nombre_archivo_word"));
/*  857 */         resultados.add(reg);
/*      */       }
/*      */     
/*  860 */     } catch (Exception e) {
/*  861 */       e.printStackTrace();
/*  862 */       Utilidades.writeError("CalProcesosDAO:cargarDocumentosDeProceso ", e);
/*      */     } 
/*  864 */     return resultados;
/*      */   }
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
/*      */   public Collection<CalDocumentosDTO> cargarHistoria(String codigo) {
/*  879 */     Collection<CalDocumentosDTO> resultados = new ArrayList<CalDocumentosDTO>();
/*      */     try {
/*  881 */       String s = "select codigo,version,tipo_documento,fecha_version,estado,nombre_archivo_word,nombre_archivo_pdf,fecha_insercion,usuario_insercion from cal_documentos_versiones where codigo='" + codigo + "'" + " order by version";
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
/*  894 */       boolean rtaDB = this.dat.parseSql(s);
/*  895 */       if (!rtaDB) {
/*  896 */         return resultados;
/*      */       }
/*  898 */       this.rs = this.dat.getResultSet();
/*  899 */       while (this.rs.next()) {
/*  900 */         CalDocumentosDTO reg = new CalDocumentosDTO();
/*  901 */         reg.setCodigo(this.rs.getString("codigo"));
/*  902 */         reg.setVersion(this.rs.getString("version"));
/*  903 */         reg.setTipoDocumento(this.rs.getString("tipo_documento"));
/*  904 */         reg.setFechaVersion(this.rs.getString("fecha_version"));
/*  905 */         reg.setEstado(this.rs.getString("estado"));
/*  906 */         reg.setNombreArchivoWord(this.rs.getString("nombre_archivo_word"));
/*  907 */         reg.setNombreArchivoPdf(this.rs.getString("nombre_archivo_pdf"));
/*  908 */         reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  909 */         reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  910 */         resultados.add(reg);
/*      */       }
/*      */     
/*  913 */     } catch (Exception e) {
/*  914 */       e.printStackTrace();
/*  915 */       Utilidades.writeError("CalDocumentosDAO:cargarHistoria ", e);
/*      */     } 
/*  917 */     return resultados;
/*      */   }
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
/*      */   public boolean crearVersion(String codigo, String version, String nombreDestinoWord, String nombreDestinoPdf, String usuario) {
/*      */     try {
/*  938 */       String sd = "delete from cal_documentos_versiones WHERE CODIGO='" + codigo + "'" + " and version='" + version + "'";
/*      */ 
/*      */       
/*  941 */       this.dat.executeUpdate(sd);
/*      */       
/*  943 */       String s = "insert into cal_documentos_versiones(codigo,version,tipo_documento,estado,nombre_archivo_word,nombre_archivo_pdf,fecha_version,fecha_insercion,usuario_insercion) SELECT  codigo,version,tipo_documento,estado,'" + nombreDestinoWord + "'," + "'" + nombreDestinoPdf + "'," + "fecha_version," + "SYSDATE," + "'" + usuario + "'" + " FROM CAL_DOCUMENTOS d" + " WHERE d.CODIGO='" + codigo + "'";
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
/*  966 */       return this.dat.executeUpdate(s);
/*      */     
/*      */     }
/*  969 */     catch (Exception e) {
/*  970 */       e.printStackTrace();
/*  971 */       Utilidades.writeError("CalDocumentosDAO:cargarHistoria ", e);
/*      */       
/*  973 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CalDocumentosDTO cargarNormograma(String proceso, String subproceso) {
/*  983 */     CalDocumentosDTO resultados = new CalDocumentosDTO();
/*      */     try {
/*  985 */       String s = "select codigo, asociado_a";
/*  986 */       s = s + " from cal_documentos";
/*  987 */       s = s + " where proceso='" + proceso + "'";
/*  988 */       s = s + " and upper(asociado_a)=upper('Normograma')";
/*  989 */       if (subproceso.length() > 0) {
/*  990 */         s = s + " and subproceso='" + subproceso + "'";
/*      */       } else {
/*  992 */         s = s + " and subproceso IS NULL";
/*      */       } 
/*  994 */       boolean rtaDB = this.dat.parseSql(s);
/*  995 */       if (!rtaDB) {
/*  996 */         return resultados;
/*      */       }
/*  998 */       this.rs = this.dat.getResultSet();
/*  999 */       if (!rtaDB) {
/* 1000 */         return resultados;
/*      */       }
/*      */       
/* 1003 */       while (this.rs.next()) {
/* 1004 */         resultados.setCodigo(this.rs.getString("codigo"));
/* 1005 */         resultados.setAsociado_a(this.rs.getString("asociado_a"));
/*      */       }
/*      */     
/* 1008 */     } catch (Exception e) {
/* 1009 */       e.printStackTrace();
/* 1010 */       Utilidades.writeError("CalDocumentosFactory:cargarNormograma ", e);
/*      */     } 
/* 1012 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CalDocumentosDTO cargarRiesgos(String proceso, String subproceso) {
/* 1021 */     CalDocumentosDTO resultados = new CalDocumentosDTO();
/*      */     try {
/* 1023 */       String s = "select codigo, asociado_a";
/* 1024 */       s = s + " from cal_documentos";
/* 1025 */       s = s + " where proceso='" + proceso + "'";
/* 1026 */       s = s + " and upper(asociado_a)=upper('Riesgos')";
/* 1027 */       if (subproceso.length() > 0) {
/* 1028 */         s = s + " and subproceso='" + subproceso + "'";
/*      */       } else {
/* 1030 */         s = s + " and subproceso IS NULL";
/*      */       } 
/* 1032 */       boolean rtaDB = this.dat.parseSql(s);
/* 1033 */       if (!rtaDB) {
/* 1034 */         return resultados;
/*      */       }
/* 1036 */       this.rs = this.dat.getResultSet();
/* 1037 */       if (!rtaDB) {
/* 1038 */         return resultados;
/*      */       }
/*      */       
/* 1041 */       while (this.rs.next()) {
/* 1042 */         resultados.setCodigo(this.rs.getString("codigo"));
/* 1043 */         resultados.setAsociado_a(this.rs.getString("asociado_a"));
/*      */       }
/*      */     
/* 1046 */     } catch (Exception e) {
/* 1047 */       e.printStackTrace();
/* 1048 */       Utilidades.writeError("CalDocumentosFactory:cargarRiesgos ", e);
/*      */     } 
/* 1050 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CalDocumentosDTO cargarCaracterizacion(String proceso, String subproceso) {
/* 1059 */     CalDocumentosDTO resultados = new CalDocumentosDTO();
/*      */     try {
/* 1061 */       String s = "select codigo, asociado_a";
/* 1062 */       s = s + " from cal_documentos";
/* 1063 */       s = s + " where proceso='" + proceso + "'";
/* 1064 */       s = s + " and upper(asociado_a)=upper('Caracterizacion')";
/* 1065 */       if (subproceso.length() > 0) {
/* 1066 */         s = s + " and subproceso='" + subproceso + "'";
/*      */       } else {
/* 1068 */         s = s + " and subproceso IS NULL";
/*      */       } 
/* 1070 */       boolean rtaDB = this.dat.parseSql(s);
/* 1071 */       if (!rtaDB) {
/* 1072 */         return resultados;
/*      */       }
/* 1074 */       this.rs = this.dat.getResultSet();
/* 1075 */       if (!rtaDB) {
/* 1076 */         return resultados;
/*      */       }
/*      */       
/* 1079 */       while (this.rs.next()) {
/* 1080 */         resultados.setCodigo(this.rs.getString("codigo"));
/* 1081 */         resultados.setAsociado_a(this.rs.getString("asociado_a"));
/*      */       }
/*      */     
/* 1084 */     } catch (Exception e) {
/* 1085 */       e.printStackTrace();
/* 1086 */       Utilidades.writeError("CalDocumentosFactory:cargarCaracterizacion ", e);
/*      */     } 
/* 1088 */     return resultados;
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\CalDocumentosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */