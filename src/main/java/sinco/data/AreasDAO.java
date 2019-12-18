/*      */ package sinco.data;
/*      */ 
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import sinco.business.AreasDTO;
/*      */ import sinco.business.ParametrosDTO;
/*      */ import sinco.business.RespuestaBD;
/*      */ import sinco.business.Utilidades;
/*      */ import sinco.data.AreasDAO;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ public class AreasDAO
/*      */ {
/*      */   ResultSet rs;
/*   32 */   DBManager dat = new DBManager();
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
/*   43 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close() {
/*      */     try {
/*   50 */       this.dat.close();
/*      */     }
/*   52 */     catch (Exception e) {
/*   53 */       Utilidades.writeError("AreasDAO:close ", e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AreasDTO leerRegistroAdm() {
/*      */     try {
/*   65 */       AreasDTO reg = new AreasDTO();
/*      */       
/*   67 */       reg.setCodigo(this.rs.getInt("codigo"));
/*   68 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*   69 */       reg.setNivelSuperior(this.rs.getInt("nivel_superior"));
/*   70 */       reg.setEstado(this.rs.getString("estado"));
/*   71 */       reg.setNivel(this.rs.getInt("nivel"));
/*   72 */       reg.setSecuencia(this.rs.getString("secuencia"));
/*   73 */       reg.setAnidar(this.rs.getString("anidar"));
/*   74 */       reg.setUbicacion(this.rs.getString("ubicacion"));
/*   75 */       reg.setIncluirCalidad(this.rs.getString("incluir_calidad"));
/*   76 */       reg.setModificaLogros(this.rs.getString("modifica_logros"));
/*   77 */       reg.setTipoArea(this.rs.getString("tipo_area"));
/*   78 */       reg.setMunicipioUbicacion(this.rs.getString("municipio_ubicacion"));
/*   79 */       reg.setNivelOrganigrama(this.rs.getString("nivel_organigrama"));
/*   80 */       reg.setCicloAuditoria(this.rs.getString("ciclo_auditoria"));
/*   81 */       reg.setCicloAnterior(this.rs.getString("ciclo_anterior"));
/*   82 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*   83 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*   84 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*   85 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*   86 */       reg.setNombreNivelSuperior(this.rs.getString("nombre_nivel_superior"));
/*   87 */       reg.setNombreEstado(this.rs.getString("nombre_estado"));
/*   88 */       reg.setNombreTipoArea(this.rs.getString("nombre_tipo_area"));
/*   89 */       reg.setNombreMunicipioUbicacion(this.rs.getString("nombre_municipio_ubicacion"));
/*   90 */       reg.setDepartamentoUbicacion(this.rs.getString("departamento_ubicacion"));
/*   91 */       reg.setNombredepartamentoUbicacion(this.rs.getString("nombre_departamento_ubicacion"));
/*   92 */       reg.setNombreNivelOrganigrama(this.rs.getString("nombre_nivel_organigrama"));
/*   93 */       reg.setPersonaResponsable(this.rs.getInt("persona_responsable"));
/*      */       
/*   95 */       return reg;
/*      */     }
/*   97 */     catch (Exception e) {
/*   98 */       e.printStackTrace();
/*   99 */       Utilidades.writeError("AreasDAO:leerRegistro ", e);
/*      */       
/*  101 */       return null;
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
/*      */   public Collection<AreasDTO> cargarTodos(int codigo, String descripcion, String estado) {
/*  113 */     Collection<AreasDTO> resultados = new ArrayList<AreasDTO>();
/*      */     try {
/*  115 */       String s = "select t.codigo,t.descripcion,t.nivel_superior,r1.DESCRIPCION as nombre_nivel_superior,t.estado,m2.DESCRIPCION as nombre_estado,t.nivel,t.secuencia,t.anidar,t.ubicacion,t.incluir_calidad,t.modifica_logros,t.tipo_area,m4.DESCRIPCION as nombre_tipo_area,t.departamento_ubicacion,r6.nombre_departamento as nombre_departamento_ubicacion,t.municipio_ubicacion,r5.nombre_ciudad as nombre_municipio_ubicacion,t.nivel_organigrama,m6.DESCRIPCION as nombre_nivel_organigrama,t.ciclo_auditoria,t.ciclo_anterior,t.persona_responsable,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from unidades_dependencia t  left join UNIDADES_DEPENDENCIA r1 on (r1.CODIGO=t.nivel_superior) left join sis_multivalores m2 on (m2.tabla='ESTADO_REGISTRO' and m2.VALOR=t.estado) left join sis_multivalores m4 on (m4.tabla='TIPO_AREA' and m4.VALOR=t.tipo_area) left join par_departamento r6 on (t.departamento_ubicacion=r6.codigo_departamento) left join par_ciudad r5 on (t.municipio_ubicacion=r5.codigo_ciudad and r5.codigo_departamento=r6.codigo_departamento) left join sis_multivalores m6 on (m6.tabla='NIVEL_ORGANIGRAMA' and m6.VALOR=t.nivel_organigrama) where 1=1";
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  151 */       if (codigo > 0) {
/*  152 */         s = s + " and t.codigo=" + codigo;
/*      */       }
/*  154 */       if (descripcion.length() > 0) {
/*  155 */         s = s + " and upper(t.descripcion) like upper('%" + descripcion + "%')";
/*      */       }
/*  157 */       if (estado.length() > 0) {
/*  158 */         s = s + " and upper(t.estado) like upper('%" + estado + "%')";
/*      */       }
/*  160 */       s = s + " order by 1";
/*  161 */       boolean rtaDB = this.dat.parseSql(s);
/*  162 */       if (!rtaDB) {
/*  163 */         return resultados;
/*      */       }
/*  165 */       this.rs = this.dat.getResultSet();
/*  166 */       while (this.rs.next()) {
/*  167 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  170 */     catch (Exception e) {
/*  171 */       e.printStackTrace();
/*  172 */       Utilidades.writeError("AreasDAO:cargarTodos ", e);
/*      */     } 
/*  174 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AreasDTO cargarRegistro(int codigo) {
/*      */     try {
/*  183 */       String s = "select t.codigo,t.descripcion,t.nivel_superior,r1.DESCRIPCION as nombre_nivel_superior,t.estado,m2.DESCRIPCION as nombre_estado,t.nivel,t.secuencia,t.anidar,t.ubicacion,t.incluir_calidad,t.modifica_logros,t.tipo_area,m4.DESCRIPCION as nombre_tipo_area,t.departamento_ubicacion,r6.nombre_departamento as nombre_departamento_ubicacion,t.municipio_ubicacion,r5.nombre_ciudad as nombre_municipio_ubicacion,t.nivel_organigrama,m6.DESCRIPCION as nombre_nivel_organigrama,t.ciclo_auditoria,t.ciclo_anterior,t.persona_responsable,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from unidades_dependencia t  left join UNIDADES_DEPENDENCIA r1 on (r1.CODIGO=t.nivel_superior) left join sis_multivalores m2 on (m2.tabla='ESTADO_REGISTRO' and m2.VALOR=t.estado) left join sis_multivalores m4 on (m4.tabla='TIPO_AREA' and m4.VALOR=t.tipo_area) left join par_departamento r6 on (t.departamento_ubicacion=r6.codigo_departamento) left join par_ciudad r5 on (t.municipio_ubicacion=r5.codigo_ciudad and r5.codigo_departamento=r6.codigo_departamento) left join sis_multivalores m6 on (m6.tabla='NIVEL_ORGANIGRAMA' and m6.VALOR=t.nivel_organigrama) where  t.codigo=" + codigo + "";
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  221 */       boolean rtaDB = this.dat.parseSql(s);
/*  222 */       if (!rtaDB) {
/*  223 */         return null;
/*      */       }
/*  225 */       this.rs = this.dat.getResultSet();
/*  226 */       if (this.rs.next()) {
/*  227 */         return leerRegistroAdm();
/*      */       }
/*      */     }
/*  230 */     catch (Exception e) {
/*  231 */       e.printStackTrace();
/*  232 */       Utilidades.writeError("AreasDAO:cargarAreas", e);
/*      */     } 
/*  234 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RespuestaBD eliminarRegistro(int codigo) {
/*  244 */     RespuestaBD rta = new RespuestaBD();
/*      */     
/*      */     try {
/*  247 */       String s = "delete from unidades_dependencia where  codigo=" + codigo + "";
/*      */ 
/*      */ 
/*      */       
/*  251 */       rta = this.dat.executeUpdate2(s);
/*      */     }
/*  253 */     catch (Exception e) {
/*  254 */       e.printStackTrace();
/*  255 */       Utilidades.writeError("AreasDAO:eliminarRegistro ", e);
/*  256 */       rta.setMensaje(e.getMessage());
/*      */     } 
/*  258 */     return rta;
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
/*      */   public RespuestaBD crearRegistro(int codigo, String descripcion, int nivelSuperior, String estado, String anidar, String ubicacion, String incluirCalidad, String modificaLogros, String tipoArea, String departamento, String municipio, String nivelOrganigrama, String usuarioInsercion) {
/*  281 */     RespuestaBD rta = new RespuestaBD();
/*      */     
/*      */     try {
/*  284 */       String s = "insert into unidades_dependencia(codigo,descripcion,nivel_superior,estado,anidar,ubicacion,incluir_calidad,modifica_logros,tipo_area,departamento_ubicacion,municipio_ubicacion,nivel_organigrama,fecha_insercion,usuario_insercion) values (" + codigo + "," + "'" + descripcion + "'," + "" + nivelSuperior + "," + "'" + estado + "'," + "'" + anidar + "'," + "'" + ubicacion + "'," + "'" + incluirCalidad + "'," + "'" + modificaLogros + "'," + "'" + tipoArea + "'," + "'" + departamento + "'," + "'" + municipio + "'," + "'" + nivelOrganigrama + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
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
/*      */       
/*  315 */       rta = this.dat.executeUpdate2(s);
/*      */     }
/*  317 */     catch (Exception e) {
/*  318 */       e.printStackTrace();
/*  319 */       Utilidades.writeError("%AreasDAO:crearRegistro ", e);
/*  320 */       rta.setMensaje(e.getMessage());
/*      */     } 
/*  322 */     return rta;
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
/*      */   public RespuestaBD modificarRegistro(int codigo, String descripcion, int nivelSuperior, String estado, String anidar, String ubicacion, String incluirCalidad, String modificaLogros, String tipoArea, String departamento, String municipio, String nivelOrganigrama, String usuarioModificacion) {
/*  345 */     RespuestaBD rta = new RespuestaBD();
/*      */     
/*      */     try {
/*  348 */       String s = "update unidades_dependencia set  descripcion='" + descripcion + "'," + " nivel_superior=" + nivelSuperior + "," + " estado='" + estado + "'," + " anidar='" + anidar + "'," + " ubicacion='" + ubicacion + "'," + " incluir_calidad='" + incluirCalidad + "'," + " modifica_logros='" + modificaLogros + "'," + " tipo_area='" + tipoArea + "'," + " departamento_ubicacion='" + departamento + "'," + " municipio_ubicacion='" + municipio + "'," + " nivel_organigrama='" + nivelOrganigrama + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo=" + codigo + "";
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
/*  365 */       rta = this.dat.executeUpdate2(s);
/*      */     }
/*  367 */     catch (Exception e) {
/*  368 */       e.printStackTrace();
/*  369 */       Utilidades.writeError("AreasDAO:modificarRegistro ", e);
/*  370 */       rta.setMensaje(e.getMessage());
/*      */     } 
/*  372 */     return rta;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private AreasDTO leerRegistro() {
/*      */     try {
/*  384 */       AreasDTO reg = new AreasDTO();
/*      */       
/*  386 */       reg.setCodigo(this.rs.getInt("codigo"));
/*  387 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*  388 */       reg.setNivelSuperior(this.rs.getInt("nivel_superior"));
/*  389 */       reg.setEstado(this.rs.getString("estado"));
/*  390 */       reg.setNivel(this.rs.getInt("nivel"));
/*  391 */       reg.setSecuencia(this.rs.getString("secuencia"));
/*  392 */       reg.setAnidar(this.rs.getString("anidar"));
/*  393 */       reg.setUbicacion(this.rs.getString("ubicacion"));
/*  394 */       reg.setIncluirCalidad(this.rs.getString("incluir_calidad"));
/*  395 */       reg.setModificaLogros(this.rs.getString("modifica_logros"));
/*  396 */       reg.setTipoArea(this.rs.getString("tipo_area"));
/*  397 */       reg.setMunicipioUbicacion(this.rs.getString("municipio_ubicacion"));
/*  398 */       reg.setNivelOrganigrama(this.rs.getString("nivel_organigrama"));
/*  399 */       reg.setCicloAuditoria(this.rs.getString("ciclo_auditoria"));
/*  400 */       reg.setCicloAnterior(this.rs.getString("ciclo_anterior"));
/*      */       try {
/*  402 */         reg.setNombreMunicipioUbicacion(this.rs.getString("nombre_municipio_ubicacion"));
/*  403 */         reg.setNombredepartamentoUbicacion(this.rs.getString("nombre_departamento_ubicacion"));
/*  404 */       } catch (Exception e) {}
/*      */ 
/*      */ 
/*      */       
/*  408 */       reg.setDepartamentoUbicacion(this.rs.getString("departamento_ubicacion"));
/*      */       
/*  410 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  411 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  412 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  413 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  414 */       reg.setPersonaResponsable(this.rs.getInt("persona_responsable"));
/*      */       
/*  416 */       return reg;
/*      */     
/*      */     }
/*  419 */     catch (SQLException e) {
/*  420 */       Utilidades.writeError("Leer Registro", e);
/*  421 */       e.printStackTrace();
/*      */       
/*  423 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<AreasDTO> cargarTodos() {
/*  432 */     Collection<AreasDTO> resultados = new ArrayList<AreasDTO>();
/*      */     try {
/*  434 */       boolean rta = this.dat.parseSql("select * from unidades_dependencia where estado='A' order by descripcion");
/*  435 */       if (!rta) {
/*  436 */         return resultados;
/*      */       }
/*  438 */       this.rs = this.dat.getResultSet();
/*  439 */       while (this.rs.next()) {
/*  440 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  443 */     catch (Exception e) {
/*  444 */       e.printStackTrace();
/*  445 */       Utilidades.writeError("", e);
/*      */     } 
/*  447 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<AreasDTO> cargarArea(int are) {
/*  456 */     Collection<AreasDTO> resultados = new ArrayList<AreasDTO>();
/*      */     try {
/*  458 */       boolean rta = this.dat.parseSql("select * from unidades_dependencia where codigo=" + are + " order by descripcion");
/*  459 */       if (!rta) {
/*  460 */         return resultados;
/*      */       }
/*  462 */       this.rs = this.dat.getResultSet();
/*  463 */       while (this.rs.next()) {
/*  464 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  467 */     catch (Exception e) {
/*  468 */       e.printStackTrace();
/*  469 */       Utilidades.writeError("", e);
/*      */     } 
/*  471 */     return resultados;
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
/*      */   public Collection<AreasDTO> cargarDebajo2(int area) {
/*  485 */     Collection<AreasDTO> resultados = new ArrayList<AreasDTO>();
/*      */     try {
/*  487 */       String s = "SELECT * FROM unidades_dependencia WHERE ESTADO='A' AND nivel_superior=" + area + " order by descripcion";
/*  488 */       boolean rta = this.dat.parseSql(s);
/*  489 */       if (!rta) {
/*  490 */         return resultados;
/*      */       }
/*  492 */       this.rs = this.dat.getResultSet();
/*  493 */       while (this.rs.next()) {
/*  494 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  497 */     catch (Exception e) {
/*  498 */       e.printStackTrace();
/*  499 */       Utilidades.writeError("", e);
/*      */     } 
/*  501 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<AreasDTO> cargarLasDeArriba() {
/*  511 */     Collection<AreasDTO> resultados = new ArrayList<AreasDTO>();
/*      */     try {
/*  513 */       String s = "SELECT * FROM unidades_dependencia WHERE ESTADO='A' AND nivel<=" + ParametrosDTO.getInt("nivel.produndidad") + " order by descripcion";
/*  514 */       boolean rta = this.dat.parseSql(s);
/*  515 */       if (!rta) {
/*  516 */         return resultados;
/*      */       }
/*  518 */       this.rs = this.dat.getResultSet();
/*  519 */       while (this.rs.next()) {
/*  520 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  523 */     catch (Exception e) {
/*  524 */       e.printStackTrace();
/*  525 */       Utilidades.writeError("", e);
/*      */     } 
/*  527 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<AreasDTO> cargarSecuencia(int area) {
/*  536 */     Collection<AreasDTO> resultados = new ArrayList<AreasDTO>();
/*      */     try {
/*  538 */       String s = "select * from unidades_dependencia where ESTADO='A' AND (secuencia like ( select secuencia||'%' from unidades_dependencia where ESTADO='A' and codigo =" + area + ")" + ") order by descripcion";
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  543 */       boolean rta = this.dat.parseSql(s);
/*  544 */       if (!rta) {
/*  545 */         return resultados;
/*      */       }
/*  547 */       this.rs = this.dat.getResultSet();
/*  548 */       while (this.rs.next()) {
/*  549 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  552 */     catch (Exception e) {
/*  553 */       e.printStackTrace();
/*  554 */       Utilidades.writeError("", e);
/*      */     } 
/*  556 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<AreasDTO> cargarTodosAbiertos(String filtro, int servicio) {
/*  566 */     Collection<AreasDTO> resultados = new ArrayList<AreasDTO>();
/*      */     
/*      */     try {
/*  569 */       String s = "select DISTINCT unidades_dependencia.* from unidades_dependencia,servicios_area,servicios";
/*  570 */       s = s + " where unidades_dependencia.codigo=servicios_area.codigo_area";
/*  571 */       s = s + " and servicios.codigo=servicios_area.codigo_servicio";
/*      */       
/*  573 */       if (servicio > 0) {
/*  574 */         s = s + " and servicios.codigo=" + servicio;
/*      */       }
/*      */       
/*  577 */       s = s + " and unidades_dependencia.estado='A'";
/*  578 */       s = s + " and upper(unidades_dependencia.descripcion) like upper('%" + filtro + "%')";
/*      */       
/*  580 */       s = s + " order by unidades_dependencia.descripcion";
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  585 */       boolean rta = this.dat.parseSql(s);
/*      */       
/*  587 */       if (!rta) {
/*  588 */         return resultados;
/*      */       }
/*  590 */       this.rs = this.dat.getResultSet();
/*  591 */       while (this.rs.next()) {
/*  592 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  595 */     catch (Exception e) {
/*  596 */       e.printStackTrace();
/*  597 */       Utilidades.writeError("", e);
/*      */     } 
/*  599 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<AreasDTO> cargarFrecuentes(int idNav, int servicio, String fechaI, String fechaF) {
/*  608 */     Collection<AreasDTO> resultados = new ArrayList<AreasDTO>();
/*      */     
/*      */     try {
/*  611 */       String s = "select DISTINCT unidades_dependencia.* from unidades_dependencia,servicios_area,servicios";
/*  612 */       s = s + " where unidades_dependencia.codigo=servicios_area.codigo_area";
/*  613 */       s = s + " and servicios.codigo=servicios_area.codigo_servicio";
/*      */       
/*  615 */       if (servicio > 0) {
/*  616 */         s = s + " and servicios.codigo=" + servicio;
/*      */       }
/*  618 */       s = s + " and unidades_dependencia.estado='A'";
/*  619 */       s = s + " and unidades_dependencia.codigo in (" + " select distinct solicitudes.area_proveedor " + " from solicitudes " + " where solicitudes.empleado_cliente=" + idNav;
/*      */ 
/*      */ 
/*      */       
/*  623 */       s = s + " and solicitudes.fecha_vigencia>=" + Utilidades.formatoFecha2(fechaI);
/*  624 */       s = s + " and solicitudes.fecha_vigencia<" + Utilidades.formatoFecha2(fechaF);
/*  625 */       s = s + ")";
/*  626 */       s = s + " order by unidades_dependencia.descripcion";
/*  627 */       boolean rta = this.dat.parseSql(s);
/*      */       
/*  629 */       if (!rta) {
/*  630 */         return resultados;
/*      */       }
/*  632 */       this.rs = this.dat.getResultSet();
/*  633 */       while (this.rs.next()) {
/*  634 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  637 */     catch (Exception e) {
/*  638 */       e.printStackTrace();
/*  639 */       Utilidades.writeError("", e);
/*      */     } 
/*  641 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<AreasDTO> cargarFrecuentesP(int idNav, int servicio, String fechaI, String fechaF) {
/*  651 */     Collection<AreasDTO> resultados = new ArrayList<AreasDTO>();
/*      */     
/*      */     try {
/*  654 */       String s = "select DISTINCT unidades_dependencia.*  from unidades_dependencia,servicios_area,servicios";
/*      */       
/*  656 */       s = s + " where unidades_dependencia.codigo=servicios_area.codigo_area";
/*  657 */       s = s + " and servicios.codigo=servicios_area.codigo_servicio";
/*      */       
/*  659 */       if (servicio > 0) {
/*  660 */         s = s + " and servicios.codigo=" + servicio;
/*      */       }
/*  662 */       s = s + " and unidades_dependencia.estado='A'";
/*  663 */       s = s + " and unidades_dependencia.codigo in (" + " select distinct solicitudes.area_cliente " + " from solicitudes where solicitudes.empleado_proveedor=" + idNav;
/*      */ 
/*      */       
/*  666 */       s = s + " and solicitudes.fecha_vigencia>=" + Utilidades.formatoFecha2(fechaI);
/*  667 */       s = s + " and solicitudes.fecha_vigencia<" + Utilidades.formatoFecha2(fechaF);
/*  668 */       s = s + ")";
/*  669 */       s = s + " order by unidades_dependencia.descripcion";
/*  670 */       boolean rta = this.dat.parseSql(s);
/*      */       
/*  672 */       if (!rta) {
/*  673 */         return resultados;
/*      */       }
/*  675 */       this.rs = this.dat.getResultSet();
/*  676 */       while (this.rs.next()) {
/*  677 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  680 */     catch (Exception e) {
/*  681 */       e.printStackTrace();
/*  682 */       Utilidades.writeError("", e);
/*      */     } 
/*  684 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<AreasDTO> cargarAreaFrecuente(int miArea) {
/*  693 */     Collection<AreasDTO> resultados = new ArrayList<AreasDTO>();
/*      */     
/*      */     try {
/*  696 */       String s = "select DISTINCT unidades_dependencia.* from unidades_dependencia where unidades_dependencia.estado='A' and  unidades_dependencia.codigo in ( select distinct solicitudes.area_proveedor  from solicitudes  where solicitudes.area_cliente=" + miArea;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  703 */       s = s + " union select distinct solicitudes.area_cliente " + " from solicitudes " + " where solicitudes.area_proveedor=" + miArea + ") order by unidades_dependencia.descripcion";
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  708 */       boolean rta = this.dat.parseSql(s);
/*  709 */       if (!rta) {
/*  710 */         return resultados;
/*      */       }
/*  712 */       this.rs = this.dat.getResultSet();
/*  713 */       while (this.rs.next()) {
/*  714 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  717 */     catch (Exception e) {
/*  718 */       e.printStackTrace();
/*  719 */       Utilidades.writeError("", e);
/*      */     } 
/*  721 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<AreasDTO> cargarParecidas(String nombre) {
/*  732 */     Collection<AreasDTO> resultados = new ArrayList<AreasDTO>();
/*      */     try {
/*  734 */       String s = "select * from unidades_dependencia where estado='A' ";
/*  735 */       s = s + " and upper(descripcion) like upper('" + nombre + "%')";
/*  736 */       s = s + " order by descripcion";
/*  737 */       boolean rtaDB = this.dat.parseSql(s);
/*  738 */       if (!rtaDB) {
/*  739 */         return resultados;
/*      */       }
/*  741 */       this.rs = this.dat.getResultSet();
/*  742 */       while (this.rs.next()) {
/*  743 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  746 */     catch (Exception e) {
/*  747 */       e.printStackTrace();
/*  748 */       Utilidades.writeError("AreasDAO:cargarTodos ", e);
/*      */     } 
/*  750 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<AreasDTO> cargarAreasCicloCalidad(int ciclo) {
/*  761 */     Collection<AreasDTO> resultados = new ArrayList<AreasDTO>();
/*      */     try {
/*  763 */       String s = "select unidades_dependencia.* from  cal_planes,unidades_dependencia";
/*  764 */       s = s + " where cal_planes.codigo_area=unidades_dependencia.codigo ";
/*  765 */       s = s + " and cal_planes.estado='A' ";
/*  766 */       s = s + " and ciclo=" + ciclo;
/*  767 */       s = s + " order by unidades_dependencia.descripcion";
/*  768 */       boolean rtaDB = this.dat.parseSql(s);
/*  769 */       if (!rtaDB) {
/*  770 */         return resultados;
/*      */       }
/*  772 */       this.rs = this.dat.getResultSet();
/*  773 */       while (this.rs.next()) {
/*  774 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  777 */     catch (Exception e) {
/*  778 */       e.printStackTrace();
/*  779 */       Utilidades.writeError("AreasDAO:cargarTodos ", e);
/*      */     } 
/*  781 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<AreasDTO> cargarSecuenciaCalidad(int ciclo, int area1, String superior) {
/*  790 */     Collection<AreasDTO> resultados = new ArrayList<AreasDTO>();
/*      */     try {
/*  792 */       String s = "select * from (select unidades_dependencia.* from cal_planes,unidades_dependencia";
/*  793 */       s = s + " where ";
/*  794 */       s = s + " cal_planes.codigo_area=unidades_dependencia.codigo ";
/*  795 */       s = s + " and unidades_dependencia.ESTADO='A'";
/*  796 */       s = s + " and cal_planes.ciclo=" + ciclo;
/*  797 */       s = s + " AND (";
/*  798 */       s = s + "unidades_dependencia.secuencia like (select secuencia||'%' from unidades_dependencia where ESTADO='A' and codigo =" + area1 + ")";
/*  799 */       s = s + ")";
/*  800 */       if (superior.length() > 0) {
/*  801 */         s = s + " union select unidades_dependencia.* from cal_planes,unidades_dependencia";
/*  802 */         s = s + " where ";
/*  803 */         s = s + " cal_planes.codigo_area=unidades_dependencia.codigo ";
/*  804 */         s = s + " and unidades_dependencia.ESTADO='A'";
/*  805 */         s = s + " and cal_planes.ciclo=" + ciclo;
/*  806 */         s = s + " and unidades_dependencia.codigo in(" + superior + ")";
/*      */       } 
/*  808 */       s = s + ")";
/*  809 */       if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
/*  810 */         s = s + " as foo";
/*      */       }
/*  812 */       s = s + " order by descripcion";
/*  813 */       boolean rtaDB = this.dat.parseSql(s);
/*  814 */       if (!rtaDB) {
/*  815 */         return resultados;
/*      */       }
/*  817 */       this.rs = this.dat.getResultSet();
/*  818 */       while (this.rs.next()) {
/*  819 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  822 */     catch (Exception e) {
/*  823 */       e.printStackTrace();
/*  824 */       Utilidades.writeError("AreasDAO:cargarTodos ", e);
/*      */     } 
/*  826 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<AreasDTO> cargarSecuencia(int area1, int areaSuperior1) {
/*  837 */     Collection<AreasDTO> resultados = new ArrayList<AreasDTO>();
/*      */     try {
/*  839 */       String s = "select * from (select * from unidades_dependencia where ESTADO='A' AND (";
/*  840 */       s = s + "secuencia like (select secuencia||'%' from unidades_dependencia where ESTADO='A' and codigo =" + area1 + ")";
/*  841 */       s = s + ") ";
/*  842 */       if (areaSuperior1 > 0) {
/*  843 */         s = s + " union select * from unidades_dependencia where ESTADO='A' and codigo=" + areaSuperior1;
/*      */       }
/*  845 */       s = s + ") ";
/*  846 */       if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
/*  847 */         s = s + " as foo";
/*      */       }
/*  849 */       s = s + " order by descripcion";
/*  850 */       boolean rtaDB = this.dat.parseSql(s);
/*  851 */       if (!rtaDB) {
/*  852 */         return resultados;
/*      */       }
/*  854 */       this.rs = this.dat.getResultSet();
/*  855 */       while (this.rs.next()) {
/*  856 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  859 */     catch (Exception e) {
/*  860 */       e.printStackTrace();
/*  861 */       Utilidades.writeError("AreasDAO:cargarTodos ", e);
/*      */     } 
/*  863 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<AreasDTO> cargarParecidas(String nombre, int codigoServicio) {
/*  874 */     Collection<AreasDTO> resultados = new ArrayList<AreasDTO>();
/*      */     try {
/*  876 */       String s = "select unidades_dependencia.* ,' ' as nombre_responsable from unidades_dependencia  where estado='A' and upper(descripcion) like upper('" + nombre + "%')" + " and codigo not in (select codigo_area from servicios_area where codigo_servicio=" + codigoServicio + ")" + " order by descripcion";
/*      */ 
/*      */ 
/*      */       
/*  880 */       boolean rtaDB = this.dat.parseSql(s);
/*  881 */       if (!rtaDB) {
/*  882 */         return resultados;
/*      */       }
/*  884 */       this.rs = this.dat.getResultSet();
/*  885 */       while (this.rs.next()) {
/*  886 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  889 */     catch (Exception e) {
/*  890 */       e.printStackTrace();
/*  891 */       Utilidades.writeError("AreasDAO:cargarTodos ", e);
/*      */     } 
/*  893 */     return resultados;
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
/*      */   public Collection<AreasDTO> cargarTodosParaServicioAbiertos(int servicio) {
/*  905 */     Collection<AreasDTO> resultados = new ArrayList<AreasDTO>();
/*      */     try {
/*  907 */       String s = "select unidades_dependencia.* from unidades_dependencia,servicios_area,servicios";
/*  908 */       s = s + " where unidades_dependencia.codigo=servicios_area.codigo_area";
/*  909 */       s = s + " and servicios.codigo=servicios_area.codigo_servicio";
/*  910 */       s = s + " and unidades_dependencia.codigo=servicios_area.codigo_area";
/*  911 */       s = s + " and servicios_area.codigo_servicio=" + servicio;
/*  912 */       s = s + " and unidades_dependencia.estado='A'";
/*  913 */       s = s + " order by unidades_dependencia.descripcion";
/*  914 */       boolean rtaDB = this.dat.parseSql(s);
/*  915 */       if (!rtaDB) {
/*  916 */         return resultados;
/*      */       }
/*  918 */       this.rs = this.dat.getResultSet();
/*  919 */       while (this.rs.next()) {
/*  920 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  923 */     catch (Exception e) {
/*  924 */       e.printStackTrace();
/*  925 */       Utilidades.writeError("AreasDAO:cargarTodos ", e);
/*      */     } 
/*  927 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<AreasDTO> cargarTodosParaServicioAbiertos(int servicio, String nombreArea) {
/*  938 */     Collection<AreasDTO> resultados = new ArrayList<AreasDTO>();
/*      */     try {
/*  940 */       String s = "select unidades_dependencia.* from  unidades_dependencia, servicios_area  where unidades_dependencia.codigo=servicios_area.codigo_area and servicios_area.codigo_servicio=" + servicio + " and unidades_dependencia.estado='A'" + " and upper(unidades_dependencia.descripcion)" + " like upper('%" + nombreArea + "%') order by unidades_dependencia.descripcion";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  948 */       boolean rtaDB = this.dat.parseSql(s);
/*  949 */       if (!rtaDB) {
/*  950 */         return resultados;
/*      */       }
/*  952 */       this.rs = this.dat.getResultSet();
/*  953 */       while (this.rs.next()) {
/*  954 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  957 */     catch (Exception e) {
/*  958 */       e.printStackTrace();
/*  959 */       Utilidades.writeError("AreasDAO:cargarTodos ", e);
/*      */     } 
/*  961 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AreasDTO getArea(int cod) {
/*      */     try {
/*  972 */       boolean rta = this.dat.parseSql("select * from unidades_dependencia where codigo=" + cod);
/*  973 */       if (!rta) return null; 
/*  974 */       this.rs = this.dat.getResultSet();
/*  975 */       if (this.rs.next()) {
/*  976 */         return leerRegistro();
/*      */       }
/*      */     }
/*  979 */     catch (SQLException e) {
/*  980 */       e.printStackTrace();
/*      */     } 
/*  982 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<AreasDTO> cargarActivas() {
/*  990 */     Collection<AreasDTO> resultados = new ArrayList<AreasDTO>();
/*      */     try {
/*  992 */       boolean rta = this.dat.parseSql("select * from unidades_dependencia where estado='A' order by descripcion");
/*  993 */       if (!rta) {
/*  994 */         return resultados;
/*      */       }
/*  996 */       this.rs = this.dat.getResultSet();
/*  997 */       while (this.rs.next()) {
/*  998 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/* 1001 */     catch (Exception e) {
/* 1002 */       e.printStackTrace();
/*      */     } 
/* 1004 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<AreasDTO> cargarActivas(String nombreArea) {
/* 1015 */     Collection<AreasDTO> resultados = new ArrayList<AreasDTO>();
/*      */     
/*      */     try {
/* 1018 */       String s = "select u.* from unidades_dependencia u where u.ESTADO='A' and upper(u.descripcion) like upper('%" + nombreArea + "%')" + " order by u.descripcion";
/*      */ 
/*      */ 
/*      */       
/* 1022 */       boolean rtaDB = this.dat.parseSql(s);
/*      */       
/* 1024 */       if (!rtaDB) {
/* 1025 */         return resultados;
/*      */       }
/*      */       
/* 1028 */       this.rs = this.dat.getResultSet();
/* 1029 */       while (this.rs.next()) {
/* 1030 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/* 1033 */     catch (Exception e) {
/* 1034 */       e.printStackTrace();
/* 1035 */       Utilidades.writeError("AreaFactory:cargarSecuencia ", e);
/*      */     } 
/* 1037 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<AreasDTO> cargarAreasFrecuentas(int codigoArea) {
/* 1048 */     Collection<AreasDTO> resultados = new ArrayList<AreasDTO>();
/*      */     
/*      */     try {
/* 1051 */       String s = "select DISTINCT unidades_dependencia.* from unidades_dependencia where unidades_dependencia.estado='A' and  unidades_dependencia.codigo in ( select distinct s.area_proveedor  from solicitudes s where s.area_cliente=" + codigoArea;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1057 */       if (ParametrosDTO.getInt("tipoBaseDatos") == 5) {
/* 1058 */         s = s + " and s.fecha_vigencia>=trunc(SYSDATE,'year')";
/*      */       }
/* 1060 */       else if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
/* 1061 */         s = s + " and s.fecha_vigencia>=date_trunc('year', now())";
/*      */       } 
/*      */       
/* 1064 */       s = s + " union select distinct s.area_cliente  from solicitudes s where s.area_proveedor=" + codigoArea;
/*      */ 
/*      */       
/* 1067 */       if (ParametrosDTO.getInt("tipoBaseDatos") == 5) {
/* 1068 */         s = s + " and s.fecha_vigencia>=trunc(SYSDATE,'year')";
/*      */       }
/* 1070 */       else if (ParametrosDTO.getInt("tipoBaseDatos") == 1) {
/* 1071 */         s = s + " and s.fecha_vigencia>=date_trunc('year', now())";
/*      */       } 
/* 1073 */       s = s + ") order by unidades_dependencia.descripcion";
/* 1074 */       boolean rta = this.dat.parseSql(s);
/* 1075 */       if (!rta) {
/* 1076 */         return resultados;
/*      */       }
/* 1078 */       this.rs = this.dat.getResultSet();
/* 1079 */       while (this.rs.next()) {
/* 1080 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/* 1083 */     catch (Exception e) {
/* 1084 */       e.printStackTrace();
/*      */     } 
/* 1086 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<AreasDTO> cargarAreasSecuencia(String secuencia) {
/* 1094 */     Collection<AreasDTO> resultados = new ArrayList<AreasDTO>();
/*      */     try {
/* 1096 */       boolean rta = this.dat.parseSql("select * from unidades_dependencia where ESTADO='A' AND upper(secuencia) like upper('" + secuencia + "%') order by descripcion");
/* 1097 */       if (!rta) {
/* 1098 */         return resultados;
/*      */       }
/* 1100 */       this.rs = this.dat.getResultSet();
/* 1101 */       while (this.rs.next()) {
/* 1102 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/* 1105 */     catch (Exception e) {
/* 1106 */       e.printStackTrace();
/*      */     } 
/* 1108 */     return resultados;
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
/*      */   public Collection<AreasDTO> cargarAreasHijas(int idNav) {
/* 1122 */     Collection<AreasDTO> arr = new ArrayList<AreasDTO>();
/* 1123 */     Collection<AreasDTO> resultados = new ArrayList<AreasDTO>();
/*      */     try {
/* 1125 */       String s = "select x.*  from   Unidades_Dependencia x,sis_usuarios_area  Pa where  Pa.Codigo_Area = x.Codigo        and Pa.Codigo_Empleado = " + idNav + "        and x.Estado = 'A'";
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1130 */       boolean rta = this.dat.parseSql(s);
/* 1131 */       if (!rta) {
/* 1132 */         return resultados;
/*      */       }
/* 1134 */       this.rs = this.dat.getResultSet();
/* 1135 */       while (this.rs.next()) {
/* 1136 */         arr.add(leerRegistro());
/*      */       }
/*      */       
/* 1139 */       Iterator<AreasDTO> iterator = arr.iterator();
/* 1140 */       String secuencia = "(";
/* 1141 */       while (iterator.hasNext()) {
/* 1142 */         AreasDTO reg = (AreasDTO)iterator.next();
/* 1143 */         if (secuencia.length() > 1) {
/* 1144 */           secuencia = secuencia + " or";
/*      */         }
/* 1146 */         secuencia = secuencia + " x.SECUENCIA LIKE '" + reg.getSecuencia() + "%'";
/*      */       } 
/* 1148 */       secuencia = secuencia + " or x.secuencia ='xxxxxxx')";
/* 1149 */       arr.clear();
/*      */ 
/*      */       
/* 1152 */       s = "select x.*  from   Unidades_Dependencia x where  x.Estado = 'A' and " + secuencia + " order by x.descripcion";
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1157 */       rta = this.dat.parseSql(s);
/* 1158 */       if (!rta) {
/* 1159 */         return resultados;
/*      */       }
/* 1161 */       this.rs = this.dat.getResultSet();
/* 1162 */       while (this.rs.next()) {
/* 1163 */         resultados.add(leerRegistro());
/*      */       
/*      */       }
/*      */     }
/* 1167 */     catch (Exception e) {
/* 1168 */       e.printStackTrace();
/*      */     } 
/* 1170 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<AreasDTO> cargarMisAreas(int idNav) {
/* 1179 */     Collection<AreasDTO> resultados = new ArrayList<AreasDTO>();
/*      */     try {
/* 1181 */       String s = "select x.*  from   Unidades_Dependencia x,sis_usuarios_area Pa where  Pa.Codigo_Area = x.Codigo        and Pa.Codigo_Empleado = " + idNav + "        and x.Estado = 'A'";
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1186 */       boolean rta = this.dat.parseSql(s);
/* 1187 */       if (!rta) {
/* 1188 */         return resultados;
/*      */       }
/* 1190 */       this.rs = this.dat.getResultSet();
/* 1191 */       while (this.rs.next()) {
/* 1192 */         resultados.add(leerRegistro());
/*      */       
/*      */       }
/*      */     }
/* 1196 */     catch (Exception e) {
/* 1197 */       e.printStackTrace();
/*      */     } 
/* 1199 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<AreasDTO> cargarTodosParaServicioActivos(int servicio) {
/* 1208 */     Collection<AreasDTO> resultados = new ArrayList<AreasDTO>();
/*      */     try {
/* 1210 */       String s = "select u.* from unidades_dependencia u,servicios_area sa,servicios s";
/* 1211 */       s = s + " where u.codigo=sa.codigo_area";
/* 1212 */       s = s + " and s.codigo=sa.codigo_servicio";
/* 1213 */       s = s + " and u.codigo=sa.codigo_area";
/* 1214 */       s = s + " and sa.codigo_servicio=" + servicio;
/* 1215 */       s = s + " and u.estado='A'";
/* 1216 */       s = s + " order by u.descripcion";
/* 1217 */       boolean rta = this.dat.parseSql(s);
/* 1218 */       if (!rta) {
/* 1219 */         return resultados;
/*      */       }
/* 1221 */       this.rs = this.dat.getResultSet();
/* 1222 */       while (this.rs.next()) {
/* 1223 */         resultados.add(leerRegistro());
/*      */       
/*      */       }
/*      */     }
/* 1227 */     catch (Exception e) {
/* 1228 */       e.printStackTrace();
/* 1229 */       Utilidades.writeError("cargarTodosParaServicioActivos", e);
/*      */     } 
/* 1231 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<AreasDTO> cargarAreas(int codigoEmpleado) {
/* 1242 */     Collection<AreasDTO> resultados = new ArrayList<AreasDTO>();
/*      */     try {
/* 1244 */       String s = "select * from unidades_dependencia  where estado='A'  and codigo in(select codigo_area from sis_usuarios_area where codigo_empleado=" + codigoEmpleado + ")" + " order by descripcion";
/*      */ 
/*      */ 
/*      */       
/* 1248 */       boolean rtaDB = this.dat.parseSql(s);
/* 1249 */       if (!rtaDB) {
/* 1250 */         return resultados;
/*      */       }
/* 1252 */       this.rs = this.dat.getResultSet();
/* 1253 */       while (this.rs.next()) {
/* 1254 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/* 1257 */     catch (Exception e) {
/* 1258 */       e.printStackTrace();
/* 1259 */       Utilidades.writeError("AreasDao:cargarAreas ", e);
/*      */     } 
/* 1261 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AreasDTO getNombreArea(int cod) {
/*      */     try {
/* 1273 */       String s = "select  u.descripcion, c.descripcion nombre_ciudad from unidades_dependencia u LEFT JOIN sis_ciudades c ON (u.municipio_ubicacion=c.codigo) where u.codigo=" + cod;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1278 */       boolean rta = this.dat.parseSql(s);
/* 1279 */       if (!rta) return null; 
/* 1280 */       this.rs = this.dat.getResultSet();
/* 1281 */       if (this.rs.next()) {
/* 1282 */         AreasDTO reg = new AreasDTO();
/* 1283 */         reg.setDescripcion(this.rs.getString("descripcion"));
/* 1284 */         reg.setNombreMunicipioUbicacion(this.rs.getString("nombre_ciudad"));
/* 1285 */         return reg;
/*      */       }
/*      */     
/* 1288 */     } catch (SQLException e) {
/* 1289 */       e.printStackTrace();
/* 1290 */       Utilidades.writeError("AreasDAO::getArea ", e);
/*      */     } 
/* 1292 */     return null;
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\AreasDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */