/*      */ package sinco.data;
/*      */ 
/*      */ import java.sql.ResultSet;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import sinco.business.AudDetallesDTO;
/*      */ import sinco.business.Utilidades;
/*      */ import sinco.data.AudDetallesDAO;
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
/*      */ public class AudDetallesDAO
/*      */ {
/*      */   ResultSet rs;
/*   27 */   DBManager dat = new DBManager();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   36 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close() {
/*      */     try {
/*   43 */       this.dat.close();
/*      */     }
/*   45 */     catch (Exception e) {
/*   46 */       Utilidades.writeError("AudDetallesDAO:close", e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AudDetallesDTO next() {
/*      */     try {
/*   57 */       if (this.rs.next()) {
/*   58 */         return leerRegistro();
/*      */       }
/*      */     }
/*   61 */     catch (Exception e) {
/*   62 */       e.printStackTrace();
/*   63 */       Utilidades.writeError("AudDetallesDAO:next ", e);
/*      */     } 
/*   65 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AudDetallesDTO leerRegistro() {
/*      */     try {
/*   75 */       AudDetallesDTO reg = new AudDetallesDTO();
/*   76 */       reg.setCodigoEmpleado(this.rs.getInt("codigo_empleado"));
/*   77 */       reg.setCiclo(this.rs.getString("ciclo"));
/*   78 */       reg.setConsecutivo(this.rs.getInt("consecutivo"));
/*   79 */       reg.setEvaluacionAuditado(this.rs.getString("evaluacion_auditado"));
/*   80 */       reg.setEvaluacionGestion(this.rs.getString("evaluacion_gestion"));
/*   81 */       reg.setTipoAuditoria(this.rs.getInt("tipo_auditoria"));
/*   82 */       reg.setClaseAuditoria(this.rs.getInt("clase_auditoria"));
/*   83 */       reg.setRol(this.rs.getInt("rol"));
/*   84 */       reg.setAreaAuditada(this.rs.getInt("area_auditada"));
/*   85 */       reg.setUbicacion(this.rs.getString("ubicacion"));
/*   86 */       reg.setNumeroPersonasArea(this.rs.getInt("numero_personas_area"));
/*   87 */       reg.setAuditoriasPrevias(this.rs.getInt("auditorias_previas"));
/*   88 */       reg.setSolicitud(this.rs.getInt("solicitud"));
/*   89 */       reg.setNtipoAuditoria(this.rs.getString("ntipo_auditoria"));
/*   90 */       reg.setNclaseAuditoria(this.rs.getString("nclase_auditoria"));
/*   91 */       reg.setNrol(this.rs.getString("nrol"));
/*   92 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*   93 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*   94 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*   95 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*   96 */       reg.setPersonaAuditada(this.rs.getInt("persona_auditada"));
/*   97 */       reg.setJustificacion(this.rs.getString("justificacion"));
/*   98 */       reg.setObservaciones(this.rs.getString("observaciones"));
/*   99 */       reg.setCodigoProveedor(this.rs.getString("codigo_proveedor"));
/*  100 */       reg.setProceso(this.rs.getString("proceso"));
/*  101 */       reg.setSubproceso(this.rs.getString("subproceso"));
/*  102 */       reg.setGenerado(this.rs.getString("generado"));
/*      */       
/*  104 */       reg.setNombreAuditado(this.rs.getString("nombre_auditado"));
/*  105 */       reg.setNombreAreaAuditada(this.rs.getString("nombre_area_auditada"));
/*  106 */       reg.setNombreAuditor(this.rs.getString("nombre_auditor"));
/*  107 */       reg.setNombreCiclo(this.rs.getString("nombre_ciclo"));
/*      */       
/*  109 */       return reg;
/*      */     }
/*  111 */     catch (Exception e) {
/*  112 */       e.printStackTrace();
/*  113 */       Utilidades.writeError("AudDetallesDAO:leerRegistro ", e);
/*      */       
/*  115 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection cargarDeAuditado(int areaAuditada, String ciclo) {
/*  124 */     Collection resultados = new ArrayList();
/*      */     try {
/*  126 */       String s = "select da.*,auditado.nombres||' '||auditado.apellidos as nombre_auditado,u.descripcion as nombre_area_auditada,auditor.nombres ||' '||auditor.apellidos as nombre_auditor,c.descripcion as nombre_ciclo from detalle_auditoria da,personas auditado,unidades_dependencia u,personas auditor,ciclos_auditoria c where da.persona_auditada=auditado.codigo_empleado and da.area_auditada=u.codigo and auditor.codigo_empleado=da.codigo_empleado and da.ciclo=c.ciclo and da.area_auditada=" + areaAuditada + " and da.ciclo like '" + ciclo + "%'" + " and da.rol=1" + " AND coalesce(da.estado_registro,'A')='A'" + " order by da.ciclo,da.rol";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  141 */       boolean rtaDB = this.dat.parseSql(s);
/*  142 */       if (!rtaDB) {
/*  143 */         return resultados;
/*      */       }
/*      */       
/*  146 */       this.rs = this.dat.getResultSet();
/*  147 */       while (this.rs.next()) {
/*  148 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  151 */     catch (Exception e) {
/*  152 */       e.printStackTrace();
/*  153 */       Utilidades.writeError("AudDetallesDAO:cargarTodos ", e);
/*      */     } 
/*  155 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean cargarDeAuditor(int codigoEmpleado, String ciclo) {
/*      */     try {
/*  166 */       String s = "select da.*,auditado.nombres||' '||auditado.apellidos as nombre_auditado,u.descripcion as nombre_area_auditada,auditor.nombres ||' '||auditor.apellidos as nombre_auditor,ciclos_auditoria.descripcion as nombre_ciclo from detalle_auditoria da,personas auditado,unidades_dependencia u,personas auditor,ciclos_auditoria where da.persona_auditada=auditado.codigo_empleado and da.area_auditada=u.codigo and auditor.codigo_empleado=da.codigo_empleado and da.ciclo=ciclos_auditoria.ciclo and da.codigo_empleado=" + codigoEmpleado + " and da.ciclo like '" + ciclo + "%'" + " AND coalesce(da.estado_registro,'A')='A'" + " and coalesce(da.asistio,'S')='S'" + " order by da.ciclo desc";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
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
/*  191 */       Utilidades.writeError("AudDetallesDAO:cargarTodos ", e);
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
/*      */   public int getNumeroSolicitud(String ciclo, int areaAuditada, int rol) {
/*      */     try {
/*  204 */       String s = "select solicitud from detalle_auditoria  where ciclo='" + ciclo + "'" + " and area_auditada=" + areaAuditada + " AND coalesce(estado_registro,'A')='A'" + " and rol=" + rol;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  209 */       boolean rtaDB = this.dat.parseSql(s);
/*  210 */       if (!rtaDB) {
/*  211 */         return 0;
/*      */       }
/*      */       
/*  214 */       this.rs = this.dat.getResultSet();
/*  215 */       if (this.rs.next()) {
/*  216 */         return this.rs.getInt("solicitud");
/*      */       }
/*      */     }
/*  219 */     catch (Exception e) {
/*  220 */       e.printStackTrace();
/*  221 */       Utilidades.writeError("AudDetallesDAO:getNumeroSolicitud ", e);
/*      */     } 
/*  223 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int auditoriasEnAnno(int codigoEmpleado, String ciclo) {
/*      */     try {
/*  233 */       String s = "select count(0) as numero from detalle_auditoria da, solicitudes s  where da.codigo_empleado=" + codigoEmpleado + " and substr(da.ciclo,1,4)='" + ciclo + "'" + " and da.solicitud=s.numero" + " and coalesce(da.estado_registro,'A')='A'" + " and s.codigo_estado in (select e.codigo from estados e where e.tipo_estado in('EF')" + " and s.tipo_solicitud=e.tipo_solicitud)";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  240 */       boolean rtaDB = this.dat.parseSql(s);
/*  241 */       if (!rtaDB) {
/*  242 */         return 0;
/*      */       }
/*      */       
/*  245 */       this.rs = this.dat.getResultSet();
/*  246 */       if (this.rs.next()) {
/*  247 */         return this.rs.getInt("numero");
/*      */       }
/*      */     }
/*  250 */     catch (Exception e) {
/*  251 */       e.printStackTrace();
/*  252 */       Utilidades.writeError("AudDetallesDAO:cargarAudDetalles ", e);
/*      */     } 
/*  254 */     return 0;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AudDetallesDTO nextResumenUsuario() {
/*      */     try {
/*  480 */       if (this.rs.next()) {
/*  481 */         AudDetallesDTO reg = new AudDetallesDTO();
/*  482 */         reg.setCodigoEmpleado(this.rs.getInt("empleado_auditor"));
/*  483 */         reg.setAnno(this.rs.getString("anno"));
/*  484 */         reg.setPromedio(this.rs.getDouble("promedio"));
/*  485 */         return reg;
/*      */       }
/*      */     
/*  488 */     } catch (Exception e) {
/*  489 */       e.printStackTrace();
/*  490 */       Utilidades.writeError("AudDetallesDAO:nextResumenUsuario ", e);
/*      */     } 
/*  492 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean cargarDetalleUsuario(int usuario, String ciclo) {
/*      */     try {
/*  502 */       String s = "select sis_multivalores.valor as tipo_competencia, sis_multivalores.descripcion as nombre_competencia, avg(detalles_solicitud.valor) as promedio from solicitudes,servicios,detalles_solicitud,caracteristicas,sis_multivalores where solicitudes.numero=detalles_solicitud.numero_solicitud and solicitudes.codigo_servicio=servicios.codigo and servicios.tipo_servicio=3 and detalles_solicitud.codigo_caracteristica=caracteristicas.codigo and caracteristicas.competencia is not null and detalles_solicitud.valor>0 and caracteristicas.competencia = sis_multivalores.valor and sis_multivalores.tabla='COMPETENCIAS' and substr(solicitudes.ciclo,1,4)='" + ciclo + "'" + " and solicitudes.empleado_auditor=" + usuario + " group by sis_multivalores.valor,sis_multivalores.descripcion " + " order by 2";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  519 */       boolean rtaDB = this.dat.parseSql(s);
/*  520 */       if (!rtaDB) {
/*  521 */         return false;
/*      */       }
/*      */       
/*  524 */       this.rs = this.dat.getResultSet();
/*  525 */       return true;
/*      */     }
/*  527 */     catch (Exception e) {
/*  528 */       e.printStackTrace();
/*  529 */       Utilidades.writeError("AudDetallesDAO:cargarTodos ", e);
/*      */       
/*  531 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AudDetallesDTO nextDetalleUsuario() {
/*      */     try {
/*  542 */       if (this.rs.next()) {
/*  543 */         AudDetallesDTO reg = new AudDetallesDTO();
/*  544 */         reg.setTipoCompetencia(this.rs.getString("tipo_competencia"));
/*  545 */         reg.setNombreCompetencia(this.rs.getString("nombre_competencia"));
/*  546 */         reg.setPromedio(this.rs.getDouble("promedio"));
/*  547 */         return reg;
/*      */       }
/*      */     
/*  550 */     } catch (Exception e) {
/*  551 */       e.printStackTrace();
/*  552 */       Utilidades.writeError("AudDetallesDAO:nextResumenUsuario ", e);
/*      */     } 
/*  554 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean cargarDetalleCompetencia(int usuario, String ciclo, String tipoCompetencia) {
/*      */     try {
/*  564 */       String s = "select caracteristicas.descripcion as nombre_competencia, avg(detalles_solicitud.valor) as promedio from solicitudes,servicios,detalles_solicitud,caracteristicas where solicitudes.numero=detalles_solicitud.numero_solicitud and solicitudes.codigo_servicio=servicios.codigo and servicios.tipo_servicio=3 and detalles_solicitud.codigo_caracteristica=caracteristicas.codigo and caracteristicas.competencia is not null and detalles_solicitud.valor>0 and substr(solicitudes.ciclo,1,4)='" + ciclo + "'" + " and solicitudes.empleado_auditor=" + usuario + " and caracteristicas.competencia='" + tipoCompetencia + "'" + " group by caracteristicas.descripcion" + " order by 1";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  579 */       boolean rtaDB = this.dat.parseSql(s);
/*  580 */       if (!rtaDB) {
/*  581 */         return false;
/*      */       }
/*      */       
/*  584 */       this.rs = this.dat.getResultSet();
/*  585 */       return true;
/*      */     }
/*  587 */     catch (Exception e) {
/*  588 */       e.printStackTrace();
/*  589 */       Utilidades.writeError("AudDetallesDAO:cargarDetalleCompetencia ", e);
/*      */       
/*  591 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AudDetallesDTO nextDetalleCompetencia() {
/*      */     try {
/*  603 */       if (this.rs.next()) {
/*  604 */         AudDetallesDTO reg = new AudDetallesDTO();
/*  605 */         reg.setNombreCompetencia(this.rs.getString("nombre_competencia"));
/*  606 */         reg.setPromedio(this.rs.getDouble("promedio"));
/*  607 */         return reg;
/*      */       }
/*      */     
/*  610 */     } catch (Exception e) {
/*  611 */       e.printStackTrace();
/*  612 */       Utilidades.writeError("AudDetallesDAO:nextResumenUsuario ", e);
/*      */     } 
/*  614 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean cargarCalificacionesSolicitud(int auditor, int solicitudPadre) {
/*      */     try {
/*  625 */       String s = "select solicitudes.numero,personas.nombres||' ' ||personas.apellidos as nombre_evaluador,avg(detalles_solicitud.valor) as promedio  from solicitudes,servicios,personas,detalles_solicitud,caracteristicas,sis_multivalores where solicitudes.numero=detalles_solicitud.numero_solicitud and solicitudes.codigo_servicio=servicios.codigo and servicios.tipo_servicio=3 and solicitudes.empleado_proveedor=personas.codigo_empleado and detalles_solicitud.codigo_caracteristica=caracteristicas.codigo and caracteristicas.competencia is not null and detalles_solicitud.valor>0 and caracteristicas.competencia = sis_multivalores.valor and sis_multivalores.tabla='COMPETENCIAS' and solicitudes.solicitud_padre=" + solicitudPadre + " and solicitudes.empleado_auditor=" + auditor + " group by solicitudes.numero, personas.nombres||' ' ||personas.apellidos " + " order by 1,2";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  644 */       boolean rtaDB = this.dat.parseSql(s);
/*  645 */       this.rs = this.dat.getResultSet();
/*  646 */       return rtaDB;
/*      */     }
/*  648 */     catch (Exception e) {
/*  649 */       e.printStackTrace();
/*  650 */       Utilidades.writeError("AudDetallesDAO:cargarTodos ", e);
/*      */       
/*  652 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AudDetallesDTO nextCalificacion() {
/*      */     try {
/*  662 */       if (this.rs.next()) {
/*  663 */         AudDetallesDTO reg = new AudDetallesDTO();
/*  664 */         reg.setSolicitud(this.rs.getInt("numero"));
/*  665 */         reg.setNombreEvaluador(this.rs.getString("nombre_evaluador"));
/*  666 */         reg.setPromedio(this.rs.getDouble("promedio"));
/*  667 */         return reg;
/*      */       }
/*      */     
/*  670 */     } catch (Exception e) {
/*  671 */       e.printStackTrace();
/*  672 */       Utilidades.writeError("AudDetallesDAO:nextCalificacion ", e);
/*      */     } 
/*  674 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean cargarAuditores(int anno, String ciclo, String categoria) {
/*      */     try {
/*  685 */       String s = "select foo.codigo_empleado,foo.numero,sis_multivalores.descripcion as tipo_auditor,unidades_dependencia.descripcion as nombre_area_auditor,personas.nombres ||' '||personas.apellidos as nombre_auditor from unidades_dependencia,sis_multivalores,personas,(select codigo_empleado,count(0)  as numero from\tdetalle_auditoria da where da.ciclo like '" + anno + "%'" + " and coalesce(da.asistio,'S')='S'" + " and coalesce(da.estado_registro,'A')='A'";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  696 */       if (ciclo.length() > 0) {
/*  697 */         s = s + " and da.ciclo='" + ciclo + "'";
/*      */       }
/*  699 */       s = s + " group by da.codigo_empleado" + " ) foo " + " where foo.codigo_empleado=personas.codigo_empleado" + " and personas.area=unidades_dependencia.codigo" + " and sis_multivalores.tabla='CATEGORIA_AUDITOR'" + " and sis_multivalores.valor=personas.tipo_auditor";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  705 */       if (categoria.length() > 0) {
/*  706 */         s = s + " and personas.tipo_auditor='" + categoria + "'";
/*      */       }
/*  708 */       s = s + " order by personas.nombres,personas.apellidos";
/*      */       
/*  710 */       boolean rtaDB = this.dat.parseSql(s);
/*  711 */       this.rs = this.dat.getResultSet();
/*  712 */       return rtaDB;
/*      */     }
/*  714 */     catch (Exception e) {
/*  715 */       e.printStackTrace();
/*  716 */       Utilidades.writeError("AudDetallesDAO:cargarAuditores ", e);
/*      */       
/*  718 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AudDetallesDTO nextAuditor() {
/*      */     try {
/*  728 */       if (this.rs.next()) {
/*  729 */         AudDetallesDTO reg = new AudDetallesDTO();
/*  730 */         reg.setCodigoEmpleado(this.rs.getInt("codigo_empleado"));
/*  731 */         reg.setNombreAuditor(this.rs.getString("nombre_auditor"));
/*  732 */         reg.setNombreAreaAuditada(this.rs.getString("nombre_area_auditor"));
/*  733 */         reg.setObservaciones(this.rs.getString("tipo_auditor"));
/*  734 */         reg.setSolicitud(this.rs.getInt("numero"));
/*  735 */         return reg;
/*      */       }
/*      */     
/*  738 */     } catch (Exception e) {
/*  739 */       e.printStackTrace();
/*  740 */       Utilidades.writeError("AudDetallesDAO:nextAuditor ", e);
/*      */     } 
/*  742 */     return null;
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
/*      */   public Collection cargarNivelCompetencia(int anno, String ciclo, int empleadoAuditor, Collection codigos) {
/*  757 */     Collection resultados = new ArrayList();
/*      */     try {
/*  759 */       String s = "select solicitudes.empleado_auditor,personas.nombres||' '||personas.apellidos as nombre_auditor,sis_multivalores.valor as tipo_competencia,sis_multivalores.descripcion as nombre_competencia,caracteristicas.descripcion as nombre_caracteristica,avg(detalles_solicitud.valor) as promedio from solicitudes,servicios,detalles_solicitud,caracteristicas,sis_multivalores,personas where solicitudes.numero=detalles_solicitud.numero_solicitud and solicitudes.codigo_servicio=servicios.codigo and servicios.tipo_servicio=3 and detalles_solicitud.codigo_caracteristica=caracteristicas.codigo and caracteristicas.competencia is not null and caracteristicas.competencia = sis_multivalores.valor and solicitudes.empleado_auditor=personas.codigo_empleado and sis_multivalores.tabla='COMPETENCIAS'";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  774 */       if (ciclo.length() == 0) {
/*  775 */         s = s + " and solicitudes.ciclo like '" + anno + "%'";
/*      */       } else {
/*      */         
/*  778 */         s = s + " and solicitudes.ciclo = '" + ciclo + "'";
/*      */       } 
/*  780 */       if (empleadoAuditor > 0) {
/*  781 */         s = s + " and solicitudes.empleado_auditor = " + empleadoAuditor;
/*      */       }
/*      */       
/*  784 */       Iterator iterator = codigos.iterator();
/*  785 */       String cadena = "";
/*  786 */       while (iterator.hasNext()) {
/*  787 */         String codigo = (String)iterator.next();
/*  788 */         if (cadena.length() > 0) cadena = cadena + ","; 
/*  789 */         cadena = cadena + "" + codigo + "";
/*      */       } 
/*      */       
/*  792 */       if (cadena.length() > 0) {
/*  793 */         s = s + " and solicitudes.codigo_servicio in(" + cadena + ")";
/*      */       }
/*      */       
/*  796 */       s = s + " group by " + "solicitudes.empleado_auditor," + "personas.nombres||' '||personas.apellidos," + "sis_multivalores.valor," + "sis_multivalores.descripcion," + "caracteristicas.descripcion" + " order by 1,2,3";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  803 */       boolean rtaDB = this.dat.parseSql(s);
/*  804 */       if (!rtaDB) {
/*  805 */         return resultados;
/*      */       }
/*      */       
/*  808 */       this.rs = this.dat.getResultSet();
/*  809 */       while (this.rs.next()) {
/*  810 */         AudDetallesDTO reg = new AudDetallesDTO();
/*  811 */         reg.setCodigoEmpleado(this.rs.getInt("empleado_auditor"));
/*  812 */         reg.setNombreCompetencia(this.rs.getString("nombre_competencia"));
/*  813 */         reg.setPromedio(this.rs.getDouble("promedio"));
/*  814 */         reg.setNombreAuditor(this.rs.getString("nombre_auditor"));
/*  815 */         reg.setTipoCompetencia(this.rs.getString("tipo_competencia"));
/*  816 */         reg.setNombreCaracteristica(this.rs.getString("nombre_caracteristica"));
/*  817 */         resultados.add(reg);
/*      */       }
/*      */     
/*  820 */     } catch (Exception e) {
/*  821 */       e.printStackTrace();
/*  822 */       Utilidades.writeError("AudDetallesDAO:cargarNivelCompetencia ", e);
/*      */     } 
/*  824 */     return resultados;
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
/*      */   public Collection resumenNivelCompetencia(int anno, String ciclo, int empleadoAuditor, Collection codigos) {
/*  842 */     Collection resultados = new ArrayList();
/*      */     try {
/*  844 */       String s = "SELECT EMPLEADO_AUDITOR,NOMBRE_AUDITOR,CI.DESCRIPCION NOMBRE_CIUDAD,Auditor_Iso9000,Auditor_Ohsas,Auditor_Iso14000,CASE WHEN CCOM_ESC>0 THEN COM_ESC/ CCOM_ESC ELSE 0 END comunicacion_escrita,CASE WHEN CCOM_ORA>0 THEN COM_ORA/ CCOM_ORA ELSE 0 END Comunicacion_oral,CASE WHEN CDES_AUD>0 THEN DES_AUD/ CDES_AUD ELSE 0 END Desarrollo_auditoria,CASE WHEN CEMP_CAP_ESC>0 THEN EMP_CAP_ESC/ CEMP_CAP_ESC ELSE 0 END Empatia_Capacidad_Escucha,CASE WHEN CHAL_PLA>0 THEN HAL_PLA/ CHAL_PLA ELSE 0 END Habilidades_planificacion,CASE WHEN CLID>0 THEN LID/ CLID ELSE 0 END liderazgo,CASE WHEN CTRA_EQU>0 THEN TRA_EQU/ CTRA_EQU ELSE 0 END Trabajo_Equipo,CASE WHEN CSER>0 THEN SER/ CSER ELSE 0 END serenidad FROM ( select SOLICITUDES.EMPLEADO_AUDITOR,       PERSONAS.NOMBRES || ' ' || PERSONAS.APELLIDOS as NOMBRE_AUDITOR,       Personas.Auditor_Iso9000,       Personas.Auditor_Ohsas,       Personas.Auditor_Iso14000,       SUM(CASE WHEN SIS_MULTIVALORES.VALOR ='COM_ESC' THEN DETALLES_SOLICITUD.VALOR ELSE 0 END ) COM_ESC,      sum(CASE WHEN SIS_MULTIVALORES.VALOR ='COM_ORA' THEN DETALLES_SOLICITUD.VALOR ELSE 0 END ) COM_ORA,       sum(CASE WHEN SIS_MULTIVALORES.VALOR ='DES_AUD' THEN DETALLES_SOLICITUD.VALOR ELSE 0 END ) DES_AUD,      sum(CASE WHEN SIS_MULTIVALORES.VALOR ='EMP_CAP_ESC' THEN DETALLES_SOLICITUD.VALOR ELSE 0 END ) EMP_CAP_ESC,      sum(CASE WHEN SIS_MULTIVALORES.VALOR ='HAL_PLA' THEN DETALLES_SOLICITUD.VALOR ELSE 0 END ) HAL_PLA,      sum(CASE WHEN SIS_MULTIVALORES.VALOR ='LID' THEN DETALLES_SOLICITUD.VALOR ELSE 0 END ) LID,      sum(CASE WHEN SIS_MULTIVALORES.VALOR ='TRA_EQU' THEN DETALLES_SOLICITUD.VALOR ELSE 0 END ) TRA_EQU,      sum(CASE WHEN SIS_MULTIVALORES.VALOR ='SER' THEN DETALLES_SOLICITUD.VALOR ELSE 0 END ) SER,       SUM(CASE WHEN SIS_MULTIVALORES.VALOR ='COM_ESC' THEN 1 ELSE 0 END ) CCOM_ESC,      sum(CASE WHEN SIS_MULTIVALORES.VALOR ='COM_ORA' THEN 1 ELSE 0 END ) CCOM_ORA,       sum(CASE WHEN SIS_MULTIVALORES.VALOR ='DES_AUD' THEN 1 ELSE 0 END ) CDES_AUD,      sum(CASE WHEN SIS_MULTIVALORES.VALOR ='EMP_CAP_ESC' THEN 1 ELSE 0 END ) CEMP_CAP_ESC,      sum(CASE WHEN SIS_MULTIVALORES.VALOR ='HAL_PLA' THEN 1 ELSE 0 END ) CHAL_PLA,      sum(CASE WHEN SIS_MULTIVALORES.VALOR ='LID' THEN 1 ELSE 0 END ) CLID,      sum(CASE WHEN SIS_MULTIVALORES.VALOR ='TRA_EQU' THEN 1ELSE 0 END ) CTRA_EQU,      sum(CASE WHEN SIS_MULTIVALORES.VALOR ='SER' THEN 1 ELSE 0 END) CSER from   SOLICITUDES,       SERVICIOS,       DETALLES_SOLICITUD,       CARACTERISTICAS,       SIS_MULTIVALORES,       PERSONAS where  SOLICITUDES.NUMERO = DETALLES_SOLICITUD.NUMERO_SOLICITUD       and SOLICITUDES.CODIGO_SERVICIO = SERVICIOS.CODIGO       and SERVICIOS.TIPO_SERVICIO = 3       and detalles_solicitud.valor>0       and DETALLES_SOLICITUD.CODIGO_CARACTERISTICA = CARACTERISTICAS.CODIGO       and CARACTERISTICAS.COMPETENCIA is not null       and CARACTERISTICAS.COMPETENCIA = SIS_MULTIVALORES.VALOR       and SOLICITUDES.EMPLEADO_AUDITOR = PERSONAS.CODIGO_EMPLEADO       and SIS_MULTIVALORES.TABLA = 'COMPETENCIAS'";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  896 */       if (empleadoAuditor > 0) {
/*  897 */         s = s + " and solicitudes.empleado_auditor = " + empleadoAuditor;
/*      */       }
/*  899 */       if (ciclo.length() == 0) {
/*  900 */         s = s + " and solicitudes.ciclo like '" + anno + "%'";
/*      */       } else {
/*      */         
/*  903 */         s = s + " and solicitudes.ciclo = '" + ciclo + "'";
/*      */       } 
/*      */       
/*  906 */       Iterator iterator = codigos.iterator();
/*  907 */       String cadena = "";
/*  908 */       while (iterator.hasNext()) {
/*  909 */         String codigo = (String)iterator.next();
/*  910 */         if (cadena.length() > 0) cadena = cadena + ","; 
/*  911 */         cadena = cadena + "" + codigo + "";
/*      */       } 
/*      */       
/*  914 */       if (cadena.length() > 0) {
/*  915 */         s = s + " and solicitudes.codigo_servicio in(" + cadena + ")";
/*      */       }
/*      */ 
/*      */       
/*  919 */       s = s + " group  by SOLICITUDES.EMPLEADO_AUDITOR,       PERSONAS.NOMBRES || ' ' || PERSONAS.APELLIDOS,       Personas.Auditor_Iso9000,       Personas.Auditor_Ohsas,       Personas.Auditor_Iso14000) FOOD,PERSONAS_AREA pa,UNIDADES_DEPENDENCIA U,SIS_CIUDADES CI  WHERE FOOD.Empleado_Auditor=pa.CODIGO_EMPLEADO AND U.CODIGO=PA.CODIGO_AREA AND U.MUNICIPIO_UBICACION= CI.CODIGO (+) AND pa.AREA_PRINCIPAL='S' order  by 1,2";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  935 */       boolean rtaDB = this.dat.parseSql(s);
/*  936 */       if (!rtaDB) {
/*  937 */         return resultados;
/*      */       }
/*      */       
/*  940 */       this.rs = this.dat.getResultSet();
/*  941 */       while (this.rs.next()) {
/*  942 */         AudDetallesDTO reg = new AudDetallesDTO();
/*  943 */         reg.setCodigoEmpleado(this.rs.getInt("empleado_auditor"));
/*  944 */         reg.setNombreAuditor(this.rs.getString("nombre_auditor"));
/*  945 */         reg.setComunicacionEscrita(this.rs.getDouble("comunicacion_escrita"));
/*  946 */         reg.setComunicacionOral(this.rs.getDouble("comunicacion_oral"));
/*  947 */         reg.setDesarrolloAuditoria(this.rs.getDouble("desarrollo_auditoria"));
/*  948 */         reg.setEmpatiaCapacidadEscucha(this.rs.getDouble("empatia_capacidad_escucha"));
/*  949 */         reg.setHabilidadesPlanificacion(this.rs.getDouble("habilidades_planificacion"));
/*  950 */         reg.setTrabajoEquipo(this.rs.getDouble("trabajo_equipo"));
/*  951 */         reg.setSerenidad(this.rs.getDouble("serenidad"));
/*  952 */         reg.setLiderazgo(this.rs.getDouble("liderazgo"));
/*  953 */         reg.setAuditorIso9000(this.rs.getString("auditor_iso9000"));
/*  954 */         reg.setAuditorOhsas(this.rs.getString("auditor_ohsas"));
/*  955 */         reg.setAuditorIso14000(this.rs.getString("auditor_iso14000"));
/*  956 */         reg.setNombreCiudad(this.rs.getString("nombre_ciudad"));
/*  957 */         resultados.add(reg);
/*      */       }
/*      */     
/*  960 */     } catch (Exception e) {
/*  961 */       e.printStackTrace();
/*  962 */       Utilidades.writeError("AudDetallesDAO:resumenNivelCompetencia ", e);
/*      */     } 
/*  964 */     return resultados;
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
/*      */   public Collection reporteAuditories(int anno, String ciclo, int empleadoAuditor, Collection codigos) {
/*  983 */     Collection resultados = new ArrayList();
/*      */     try {
/*  985 */       String s = "select Food.Empleado_Auditor,        u.Descripcion Nombre_Area,        Food.Nombre_Auditor,        Ci.Descripcion Nombre_Ciudad,        da.nrol,        NVL(da.asistio,'S') asistio,        ara.descripcion nombre_area_auditada,        Food.Auditor_Iso9000,        Food.Auditor_Ohsas,        Food.Auditor_Iso14000,        Food.Ciclo,        sum(case           when Ccom_Esc > 0 then            Com_Esc / Ccom_Esc           else            0        end) Comunicacion_Escrita,        sum(case           when Ccom_Ora > 0 then            Com_Ora / Ccom_Ora           else            0        end) Comunicacion_Oral,        sum(case           when Cdes_Aud > 0 then            Des_Aud / Cdes_Aud           else            0        end) Desarrollo_Auditoria,        sum(case           when Cemp_Cap_Esc > 0 then            Emp_Cap_Esc / Cemp_Cap_Esc           else            0        end) Empatia_Capacidad_Escucha,        sum(case           when Chal_Pla > 0 then            Hal_Pla / Chal_Pla           else            0        end) Habilidades_Planificacion,        sum(case           when Clid > 0 then            Lid / Clid           else            0        end) Liderazgo,        sum(case           when Ctra_Equ > 0 then            Tra_Equ / Ctra_Equ           else            0        end) Trabajo_Equipo,        sum(case           when Cser > 0 then            Ser / Cser           else            0        end) Serenidad,        count(0) Nro_Auditorias  from   (                  select s.Ciclo,                 s.Empleado_Auditor,                 Au.Nombres || ' ' || Au.Apellidos as Nombre_Auditor,                 Au.Auditor_Iso9000,                 Au.Auditor_Ohsas,                 Au.Auditor_Iso14000,                                  sum(case                        when m.Valor = 'COM_ESC' then                         Ds.Valor                        else                         0                     end) Com_Esc,                 sum(case                        when m.Valor = 'COM_ORA' then                         Ds.Valor                        else                         0                     end) Com_Ora,                 sum(case                        when m.Valor = 'DES_AUD' then                         Ds.Valor                        else                         0                     end) Des_Aud,                 sum(case                        when m.Valor = 'EMP_CAP_ESC' then                         Ds.Valor                        else                         0                     end) Emp_Cap_Esc,                 sum(case                        when m.Valor = 'HAL_PLA' then                         Ds.Valor                        else                         0                     end) Hal_Pla,                 sum(case                        when m.Valor = 'LID' then                         Ds.Valor                        else                         0                     end) Lid,                 sum(case                        when m.Valor = 'TRA_EQU' then                         Ds.Valor                        else                         0                     end) Tra_Equ,                 sum(case                        when m.Valor = 'SER' then                         Ds.Valor                        else                         0                     end) Ser,                 sum(case                        when m.Valor = 'COM_ESC' then                         1                        else                         0                     end) Ccom_Esc,                 sum(case                        when m.Valor = 'COM_ORA' then                         1                        else                         0                     end) Ccom_Ora,                 sum(case                        when m.Valor = 'DES_AUD' then                         1                        else                         0                     end) Cdes_Aud,                 sum(case                        when m.Valor = 'EMP_CAP_ESC' then                         1                        else                         0                     end) Cemp_Cap_Esc,                 sum(case                        when m.Valor = 'HAL_PLA' then                         1                        else                         0                     end) Chal_Pla,                 sum(case                        when m.Valor = 'LID' then                         1                        else                         0                     end) Clid,                 sum(case                        when m.Valor = 'TRA_EQU' then                         1else 0                     end) Ctra_Equ,                 sum(case                        when m.Valor = 'SER' then                         1                        else                         0                     end) Cser         from   Solicitudes        s,                 Servicios          Se,                 Detalles_Solicitud Ds,                 Caracteristicas    Car,                 Sis_Multivalores   m,                 Personas           Au         where  s.Numero = Ds.Numero_Solicitud                and s.Codigo_Servicio = Se.Codigo                and Se.Tipo_Servicio = 3                and Ds.Valor > 0                and Ds.Codigo_Caracteristica = Car.Codigo                and Car.Competencia is not null                and Car.Competencia = m.Valor                and s.Empleado_Auditor = Au.Codigo_Empleado                and m.Tabla = 'COMPETENCIAS'";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1164 */       if (empleadoAuditor > 0) {
/* 1165 */         s = s + " and s.empleado_auditor = " + empleadoAuditor;
/*      */       }
/* 1167 */       if (ciclo.length() == 0) {
/* 1168 */         s = s + " and s.ciclo like '" + anno + "%'";
/*      */       } else {
/*      */         
/* 1171 */         s = s + " and s.ciclo = '" + ciclo + "'";
/*      */       } 
/*      */       
/* 1174 */       Iterator iterator = codigos.iterator();
/* 1175 */       String cadena = "";
/* 1176 */       while (iterator.hasNext()) {
/* 1177 */         String codigo = (String)iterator.next();
/* 1178 */         if (cadena.length() > 0) cadena = cadena + ","; 
/* 1179 */         cadena = cadena + "" + codigo + "";
/*      */       } 
/*      */       
/* 1182 */       if (cadena.length() > 0) {
/* 1183 */         s = s + " and s.codigo_servicio in(" + cadena + ")";
/*      */       }
/*      */       
/* 1186 */       s = s + "         group  by s.Ciclo,                    s.Empleado_Auditor,                    Au.Nombres || ' ' || Au.Apellidos,                    Au.Auditor_Iso9000,                    Au.Auditor_Ohsas,                    Au.Auditor_Iso14000                  ) Food,        Personas_Area Pa,        Unidades_Dependencia u,        Sis_Ciudades Ci,        Detalle_Auditoria Da,        Unidades_Dependencia ara where  Food.Empleado_Auditor = Pa.Codigo_Empleado        and Pa.Codigo_Area=u.Codigo                 and Food.Empleado_Auditor = Da.Codigo_Empleado        and da.area_auditada=ara.codigo        and Food.Ciclo = Da.Ciclo        and u.Municipio_Ubicacion = Ci.Codigo(+)        and Pa.Area_Principal = 'S' group  by Food.Empleado_Auditor,           u.Descripcion,           Food.Nombre_Auditor,           Ci.Descripcion,           da.nrol,           NVL(da.asistio,'S'),           ara.descripcion,           Food.Auditor_Iso9000,           Food.Auditor_Ohsas,           Food.Auditor_Iso14000,         Food.Ciclo order  by 17 desc,3";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1220 */       boolean rtaDB = this.dat.parseSql(s);
/* 1221 */       if (!rtaDB) {
/* 1222 */         return resultados;
/*      */       }
/*      */       
/* 1225 */       this.rs = this.dat.getResultSet();
/* 1226 */       while (this.rs.next()) {
/* 1227 */         AudDetallesDTO reg = new AudDetallesDTO();
/* 1228 */         reg.setCodigoEmpleado(this.rs.getInt("empleado_auditor"));
/* 1229 */         reg.setNombreAuditor(this.rs.getString("nombre_auditor"));
/* 1230 */         reg.setComunicacionEscrita(this.rs.getDouble("comunicacion_escrita"));
/* 1231 */         reg.setComunicacionOral(this.rs.getDouble("comunicacion_oral"));
/* 1232 */         reg.setDesarrolloAuditoria(this.rs.getDouble("desarrollo_auditoria"));
/* 1233 */         reg.setEmpatiaCapacidadEscucha(this.rs.getDouble("empatia_capacidad_escucha"));
/* 1234 */         reg.setHabilidadesPlanificacion(this.rs.getDouble("habilidades_planificacion"));
/* 1235 */         reg.setTrabajoEquipo(this.rs.getDouble("trabajo_equipo"));
/* 1236 */         reg.setSerenidad(this.rs.getDouble("serenidad"));
/* 1237 */         reg.setLiderazgo(this.rs.getDouble("liderazgo"));
/* 1238 */         reg.setAuditorIso9000(this.rs.getString("auditor_iso9000"));
/* 1239 */         reg.setAuditorOhsas(this.rs.getString("auditor_ohsas"));
/* 1240 */         reg.setAuditorIso14000(this.rs.getString("auditor_iso14000"));
/* 1241 */         reg.setNombreCiudad(this.rs.getString("nombre_ciudad"));
/* 1242 */         reg.setNombreAreaAuditor(this.rs.getString("nombre_area"));
/* 1243 */         reg.setCiclo(this.rs.getString("ciclo"));
/* 1244 */         reg.setNumeroAuditorias(this.rs.getInt("nro_auditorias"));
/* 1245 */         reg.setNombreAreaAuditada(this.rs.getString("nombre_area_auditada"));
/* 1246 */         reg.setNrol(this.rs.getString("nrol"));
/* 1247 */         reg.setAsistio(this.rs.getString("asistio"));
/*      */         
/* 1249 */         resultados.add(reg);
/*      */       } 
/*      */       
/* 1252 */       s = "select p.Codigo_Empleado,        p.Nombres || ' ' || p.Apellidos as Nombre_Auditor,        u.Descripcion Nombre_Area,        Ci.Descripcion Nombre_Ciudad from   Personas             p,        Personas_Area        Pa,        Unidades_Dependencia u,        Sis_Ciudades         Ci where  p.Codigo_Empleado = Pa.Codigo_Empleado        and Pa.Codigo_Area = u.Codigo        and u.Municipio_Ubicacion = Ci.Codigo(+)        and Pa.Area_Principal = 'S'        and p.Auditor = 1        and not exists (select 'X'         from   Detalle_Auditoria Da         where  Da.Ciclo like '" + ciclo + "%'" + "                and Da.Codigo_Empleado = p.Codigo_Empleado)" + " order  by 2";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1272 */       rtaDB = this.dat.parseSql(s);
/* 1273 */       if (!rtaDB) {
/* 1274 */         return resultados;
/*      */       }
/*      */       
/* 1277 */       this.rs = this.dat.getResultSet();
/* 1278 */       while (this.rs.next()) {
/* 1279 */         AudDetallesDTO reg = new AudDetallesDTO();
/* 1280 */         reg.setCodigoEmpleado(this.rs.getInt("Codigo_Empleado"));
/* 1281 */         reg.setNombreAuditor(this.rs.getString("nombre_auditor"));
/* 1282 */         reg.setNombreCiudad(this.rs.getString("nombre_ciudad"));
/* 1283 */         reg.setNombreAreaAuditor(this.rs.getString("nombre_area"));
/* 1284 */         reg.setCiclo("NO PLANEADO");
/* 1285 */         resultados.add(reg);
/*      */       }
/*      */     
/* 1288 */     } catch (Exception e) {
/* 1289 */       e.printStackTrace();
/* 1290 */       Utilidades.writeError("AudDetallesDAO:resumenNivelCompetencia ", e);
/*      */     } 
/* 1292 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection serviciosAuditoria() {
/* 1303 */     Collection resultados = new ArrayList();
/*      */     try {
/* 1305 */       String s = "select codigo,descripcion from servicios s where s.tipo_servicio=3 and estado='A' order by descripcion";
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1310 */       boolean rtaDB = this.dat.parseSql(s);
/* 1311 */       if (!rtaDB) {
/* 1312 */         return resultados;
/*      */       }
/* 1314 */       this.rs = this.dat.getResultSet();
/* 1315 */       while (this.rs.next()) {
/* 1316 */         AudDetallesDTO reg = new AudDetallesDTO();
/* 1317 */         reg.setCodigoServicio(this.rs.getInt("codigo"));
/* 1318 */         reg.setNombreServicio(this.rs.getString("descripcion"));
/* 1319 */         resultados.add(reg);
/*      */       }
/*      */     
/* 1322 */     } catch (Exception e) {
/* 1323 */       e.printStackTrace();
/* 1324 */       Utilidades.writeError("ServiciosFactory::serviciosAuditoria ", e);
/*      */     } 
/* 1326 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<AudDetallesDTO> areasAuditadas() {
/* 1336 */     Collection<AudDetallesDTO> resultados = new ArrayList<AudDetallesDTO>();
/*      */     try {
/* 1338 */       String s = "select distinct a.area, u.descripcion  from aud_areas_auditadas a, unidades_dependencia u  where a.area=u.codigo ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1346 */       s = s + " order by u.descripcion";
/*      */ 
/*      */       
/* 1349 */       boolean rtaDB = this.dat.parseSql(s);
/* 1350 */       if (!rtaDB) {
/* 1351 */         return resultados;
/*      */       }
/*      */       
/* 1354 */       this.rs = this.dat.getResultSet();
/* 1355 */       while (this.rs.next()) {
/* 1356 */         AudDetallesDTO reg = new AudDetallesDTO();
/* 1357 */         reg.setAreaAuditada(this.rs.getInt("area"));
/* 1358 */         reg.setNombreAreaAuditada(this.rs.getString("descripcion"));
/* 1359 */         resultados.add(reg);
/*      */       }
/*      */     
/* 1362 */     } catch (Exception e) {
/* 1363 */       e.printStackTrace();
/* 1364 */       Utilidades.writeError("AudDetallesDAO:cargarTodos ", e);
/*      */     } 
/* 1366 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection ciclosAuditoria() {
/* 1376 */     Collection resultados = new ArrayList();
/*      */     try {
/* 1378 */       String s = "select c.Ciclo, c.Descripcion, c.Estado, COUNT(0) numero_auditorias from   Ciclos_Auditoria c,DETALLE_AUDITORIA det WHERE c.CICLO=det.CICLO AND det.ROL=1 AND coalesce(det.estado_registro,'A')='A' GROUP BY c.Ciclo, c.Descripcion, c.Estado order  by Ciclo";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1391 */       boolean rtaDB = this.dat.parseSql(s);
/* 1392 */       if (!rtaDB) {
/* 1393 */         return resultados;
/*      */       }
/*      */       
/* 1396 */       this.rs = this.dat.getResultSet();
/* 1397 */       while (this.rs.next()) {
/* 1398 */         AudDetallesDTO reg = new AudDetallesDTO();
/* 1399 */         reg.setCiclo(this.rs.getString("ciclo"));
/* 1400 */         reg.setNombreCiclo(this.rs.getString("descripcion"));
/* 1401 */         reg.setEstado(this.rs.getString("estado"));
/* 1402 */         reg.setNumeroAuditorias(this.rs.getInt("numero_auditorias"));
/* 1403 */         resultados.add(reg);
/*      */       }
/*      */     
/* 1406 */     } catch (Exception e) {
/* 1407 */       e.printStackTrace();
/* 1408 */       Utilidades.writeError("AudDetallesDAO:cargarTodos ", e);
/*      */     } 
/* 1410 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection misAuditorias(int idNav) {
/* 1420 */     Collection resultados = new ArrayList();
/*      */     try {
/* 1422 */       String s = "select ca.Ciclo, ca.Descripcion, ca.Estado, COUNT(0) numero_auditorias from   Ciclos_Auditoria ca, Datos_Auditoria da, detalle_auditoria det where  ca.Ciclo = da.Ciclo and da.Codigo_Empleado = " + idNav + " and da.codigo_empleado=det.codigo_empleado" + " AND coalesce(det.estado_registro,'A')='A'" + " and da.ciclo=det.ciclo" + " and coalesce(det.asistio,'S')='S'" + " GROUP BY Ca.Ciclo,Ca.Descripcion,Ca.Estado" + " order by ca.Ciclo";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1438 */       boolean rtaDB = this.dat.parseSql(s);
/* 1439 */       if (!rtaDB) {
/* 1440 */         return resultados;
/*      */       }
/*      */       
/* 1443 */       this.rs = this.dat.getResultSet();
/* 1444 */       while (this.rs.next()) {
/* 1445 */         AudDetallesDTO reg = new AudDetallesDTO();
/* 1446 */         reg.setCiclo(this.rs.getString("ciclo"));
/* 1447 */         reg.setNombreCiclo(this.rs.getString("descripcion"));
/* 1448 */         reg.setEstado(this.rs.getString("estado"));
/* 1449 */         reg.setNumeroAuditorias(this.rs.getInt("numero_auditorias"));
/* 1450 */         resultados.add(reg);
/*      */       }
/*      */     
/* 1453 */     } catch (Exception e) {
/* 1454 */       e.printStackTrace();
/* 1455 */       Utilidades.writeError("AudDetallesDAO:cargarTodos ", e);
/*      */     } 
/* 1457 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection ciclosAuditoriaArea(int area) {
/* 1466 */     Collection resultados = new ArrayList();
/*      */     try {
/* 1468 */       String s = "select ca.Ciclo, ca.Descripcion, ca.Estado, count(0) numero_auditorias from Ciclos_Auditoria ca, Detalle_Auditoria da where  ca.Ciclo = da.Ciclo and coalesce(da.estado_registro,'A')='A' and da.Area_Auditada = " + area + " and da.rol=1" + " group by ca.Ciclo," + " ca.Descripcion," + " ca.Estado" + " order  by ca.Ciclo";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1483 */       boolean rtaDB = this.dat.parseSql(s);
/* 1484 */       if (!rtaDB) {
/* 1485 */         return resultados;
/*      */       }
/*      */       
/* 1488 */       this.rs = this.dat.getResultSet();
/* 1489 */       while (this.rs.next()) {
/* 1490 */         AudDetallesDTO reg = new AudDetallesDTO();
/* 1491 */         reg.setCiclo(this.rs.getString("ciclo"));
/* 1492 */         reg.setNombreCiclo(this.rs.getString("descripcion"));
/* 1493 */         reg.setEstado(this.rs.getString("estado"));
/* 1494 */         reg.setNumeroAuditorias(this.rs.getInt("numero_auditorias"));
/* 1495 */         resultados.add(reg);
/*      */       }
/*      */     
/* 1498 */     } catch (Exception e) {
/* 1499 */       e.printStackTrace();
/* 1500 */       Utilidades.writeError("AudDetallesDAO:cargarTodos ", e);
/*      */     } 
/* 1502 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection actuacionAuditor(int personal, int codigoEmpleado, int area, String ciclo) {
/* 1512 */     Collection resultados = new ArrayList();
/*      */     try {
/* 1514 */       String s = "select da.*,   p.nombres|| ' '||p.apellidos nombre_auditado,   u.descripcion nombre_area_auditada,   auditor.nombres || ' '||auditor.apellidos as nombre_auditor from detalle_auditoria da,personas p,unidades_dependencia u,personas auditor where da.persona_auditada=p.codigo_empleado       and da.area_auditada=u.codigo       and auditor.codigo_empleado=da.codigo_empleado AND coalesce(da.estado_registro,'A')='A' and coalesce(da.asistio,'S')='S'";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1525 */       if (personal == 1) {
/* 1526 */         s = s + " and da.codigo_empleado=" + codigoEmpleado;
/*      */       }
/* 1528 */       else if (personal == 2) {
/* 1529 */         s = s + " and da.area_auditada=" + area;
/*      */       } 
/*      */       
/* 1532 */       if (ciclo != null && ciclo.length() > 0) {
/* 1533 */         s = s + " and da.ciclo='" + ciclo + "'";
/*      */       }
/*      */       
/* 1536 */       s = s + " order by da.ciclo desc";
/*      */       
/* 1538 */       boolean rtaDB = this.dat.parseSql(s);
/* 1539 */       if (!rtaDB) {
/* 1540 */         return resultados;
/*      */       }
/*      */       
/* 1543 */       this.rs = this.dat.getResultSet();
/* 1544 */       while (this.rs.next()) {
/* 1545 */         AudDetallesDTO reg = new AudDetallesDTO();
/* 1546 */         reg.setCodigoEmpleado(this.rs.getInt("codigo_empleado"));
/* 1547 */         reg.setCiclo(this.rs.getString("ciclo"));
/* 1548 */         reg.setConsecutivo(this.rs.getInt("consecutivo"));
/* 1549 */         reg.setTipoAuditoria(this.rs.getInt("tipo_auditoria"));
/* 1550 */         reg.setClaseAuditoria(this.rs.getInt("clase_auditoria"));
/* 1551 */         reg.setRol(this.rs.getInt("rol"));
/* 1552 */         reg.setAreaAuditada(this.rs.getInt("area_auditada"));
/* 1553 */         reg.setUbicacion(this.rs.getString("ubicacion"));
/* 1554 */         reg.setNumeroPersonasArea(this.rs.getInt("numero_personas_area"));
/* 1555 */         reg.setAuditoriasPrevias(this.rs.getInt("auditorias_previas"));
/* 1556 */         reg.setSolicitud(this.rs.getInt("solicitud"));
/* 1557 */         reg.setNtipoAuditoria(this.rs.getString("ntipo_auditoria"));
/* 1558 */         reg.setNclaseAuditoria(this.rs.getString("nclase_auditoria"));
/* 1559 */         reg.setNrol(this.rs.getString("nrol"));
/* 1560 */         reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/* 1561 */         reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/* 1562 */         reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/* 1563 */         reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/* 1564 */         reg.setPersonaAuditada(this.rs.getInt("persona_auditada"));
/* 1565 */         reg.setJustificacion(this.rs.getString("justificacion"));
/* 1566 */         reg.setObservaciones(this.rs.getString("observaciones"));
/* 1567 */         reg.setCodigoProveedor(this.rs.getString("codigo_proveedor"));
/* 1568 */         reg.setProceso(this.rs.getString("proceso"));
/* 1569 */         reg.setSubproceso(this.rs.getString("subproceso"));
/* 1570 */         reg.setGenerado(this.rs.getString("generado"));
/*      */         
/* 1572 */         reg.setNombreAuditado(this.rs.getString("nombre_auditado"));
/* 1573 */         reg.setNombreAreaAuditada(this.rs.getString("nombre_area_auditada"));
/* 1574 */         reg.setNombreAuditor(this.rs.getString("nombre_auditor"));
/* 1575 */         resultados.add(reg);
/*      */       }
/*      */     
/* 1578 */     } catch (Exception e) {
/* 1579 */       e.printStackTrace();
/* 1580 */       Utilidades.writeError("AudDetallesDAO:cargarTodos ", e);
/*      */     } 
/* 1582 */     return resultados;
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
/*      */   public Collection gestionAuditor(int empleadoAuditor) {
/* 1595 */     Collection resultados = new ArrayList();
/*      */     try {
/* 1597 */       String s = " select  Ciclo, Empleado_Auditor, Nombre_Auditor, Auditor_Iso9000, Auditor_Ohsas, Auditor_Iso14000,        case           when Ccom_Esc > 0 then            Com_Esc / Ccom_Esc           else            0        end Comunicacion_Escrita,        case           when Ccom_Ora > 0 then            Com_Ora / Ccom_Ora           else            0        end Comunicacion_Oral,        case           when Cdes_Aud > 0 then            Des_Aud / Cdes_Aud           else            0        end Desarrollo_Auditoria,        case           when Cemp_Cap_Esc > 0 then            Emp_Cap_Esc / Cemp_Cap_Esc           else            0        end Empatia_Capacidad_Escucha,        case           when Chal_Pla > 0 then            Hal_Pla / Chal_Pla           else            0        end Habilidades_Planificacion,        case           when Clid > 0 then            Lid / Clid           else            0        end Liderazgo,        case           when Ctra_Equ > 0 then            Tra_Equ / Ctra_Equ           else            0        end Trabajo_Equipo,        case           when Cser > 0 then            Ser / Cser           else            0        end Serenidad from   (select substr(sol.Ciclo,1,4) Ciclo,sol.Empleado_Auditor,       Personas.Nombres || ' ' || Personas.Apellidos as Nombre_Auditor,       Personas.Auditor_Iso9000,       Personas.Auditor_Ohsas,       Personas.Auditor_Iso14000,                sum(case                       when Sis_Multivalores.Valor = 'COM_ESC' then                        det.Valor                       else                        0                    end) Com_Esc,                sum(case                       when Sis_Multivalores.Valor = 'COM_ORA' then                        det.Valor                       else                        0                    end) Com_Ora,                sum(case                       when Sis_Multivalores.Valor = 'DES_AUD' then                        det.Valor                       else                        0                    end) Des_Aud,                sum(case                       when Sis_Multivalores.Valor = 'EMP_CAP_ESC' then                        det.Valor                       else                        0                    end) Emp_Cap_Esc,                sum(case                       when Sis_Multivalores.Valor = 'HAL_PLA' then                        det.Valor                       else                        0                    end) Hal_Pla,                sum(case                       when Sis_Multivalores.Valor = 'LID' then                        det.Valor                       else                        0                    end) Lid,                sum(case                       when Sis_Multivalores.Valor = 'TRA_EQU' then                        det.Valor                       else                        0                    end) Tra_Equ,                sum(case                       when Sis_Multivalores.Valor = 'SER' then                        det.Valor                       else                        0                    end) Ser,                sum(case                       when Sis_Multivalores.Valor = 'COM_ESC' then                        1                       else                        0                    end) Ccom_Esc,                sum(case                       when Sis_Multivalores.Valor = 'COM_ORA' then                        1                       else                        0                    end) Ccom_Ora,                sum(case                       when Sis_Multivalores.Valor = 'DES_AUD' then                        1                       else                        0                    end) Cdes_Aud,                sum(case                       when Sis_Multivalores.Valor = 'EMP_CAP_ESC' then                        1                       else                        0                    end) Cemp_Cap_Esc,                sum(case                       when Sis_Multivalores.Valor = 'HAL_PLA' then                        1                       else                        0                    end) Chal_Pla,                sum(case                       when Sis_Multivalores.Valor = 'LID' then                        1                       else                        0                    end) Clid,                sum(case                       when Sis_Multivalores.Valor = 'TRA_EQU' then                        1else 0                    end) Ctra_Equ,                sum(case                       when Sis_Multivalores.Valor = 'SER' then                        1                       else                        0                    end) Cser         from   Solicitudes sol,                Servicios,                 Detalles_Solicitud det,                Caracteristicas,                Sis_Multivalores,                Personas         where  sol.Numero = det.Numero_Solicitud                and sol.Codigo_Servicio = Servicios.Codigo                and Servicios.Tipo_Servicio = 3                and det.Valor > 0                and det.Codigo_Caracteristica = Caracteristicas.Codigo                and Caracteristicas.Competencia is not null                and Caracteristicas.Competencia = Sis_Multivalores.Valor                and sol.Empleado_Auditor = Personas.Codigo_Empleado                and Sis_Multivalores.Tabla = 'COMPETENCIAS'              AND sol.Empleado_Auditor=" + empleadoAuditor + "         group  by " + "       substr(sol.Ciclo,1,4)," + "       sol.Empleado_Auditor," + "       Personas.Nombres || ' ' || Personas.Apellidos," + "       Personas.Auditor_Iso9000," + "       Personas.Auditor_Ohsas," + "       Personas.Auditor_Iso14000" + "   )" + " order  by 1,2";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1778 */       boolean rtaDB = this.dat.parseSql(s);
/* 1779 */       if (!rtaDB) {
/* 1780 */         return resultados;
/*      */       }
/*      */       
/* 1783 */       this.rs = this.dat.getResultSet();
/* 1784 */       while (this.rs.next()) {
/* 1785 */         AudDetallesDTO reg = new AudDetallesDTO();
/* 1786 */         reg.setCiclo(this.rs.getString("ciclo"));
/* 1787 */         reg.setCodigoEmpleado(this.rs.getInt("empleado_auditor"));
/* 1788 */         reg.setNombreAuditor(this.rs.getString("nombre_auditor"));
/* 1789 */         reg.setComunicacionEscrita(this.rs.getDouble("comunicacion_escrita"));
/* 1790 */         reg.setComunicacionOral(this.rs.getDouble("comunicacion_oral"));
/* 1791 */         reg.setDesarrolloAuditoria(this.rs.getDouble("desarrollo_auditoria"));
/* 1792 */         reg.setEmpatiaCapacidadEscucha(this.rs.getDouble("empatia_capacidad_escucha"));
/* 1793 */         reg.setHabilidadesPlanificacion(this.rs.getDouble("habilidades_planificacion"));
/* 1794 */         reg.setTrabajoEquipo(this.rs.getDouble("trabajo_equipo"));
/* 1795 */         reg.setSerenidad(this.rs.getDouble("serenidad"));
/* 1796 */         reg.setLiderazgo(this.rs.getDouble("liderazgo"));
/* 1797 */         reg.setAuditorIso9000(this.rs.getString("auditor_iso9000"));
/* 1798 */         reg.setAuditorOhsas(this.rs.getString("auditor_ohsas"));
/* 1799 */         reg.setAuditorIso14000(this.rs.getString("auditor_iso14000"));
/*      */         
/* 1801 */         resultados.add(reg);
/*      */       }
/*      */     
/* 1804 */     } catch (Exception e) {
/* 1805 */       e.printStackTrace();
/* 1806 */       Utilidades.writeError("AudDetallesDAO:resumenNivelCompetencia ", e);
/*      */     } 
/* 1808 */     return resultados;
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\AudDetallesDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */