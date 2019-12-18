/*      */ package sinco.data;
/*      */ 
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import sinco.business.RespuestaBD;
/*      */ import sinco.business.ServiciosDTO;
/*      */ import sinco.business.Utilidades;
/*      */ import sinco.data.DBManager;
/*      */ import sinco.data.ServiciosDAO;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ServiciosDAO
/*      */ {
/*      */   ResultSet rs;
/*   30 */   DBManager dat = new DBManager();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   41 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close() {
/*      */     try {
/*   48 */       this.dat.close();
/*      */     }
/*   50 */     catch (Exception e) {
/*   51 */       Utilidades.writeError("ServiciosDAO:close ", e);
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ServiciosDTO leerRegistro() {
/*      */     try {
/*   80 */       ServiciosDTO reg = new ServiciosDTO();
/*      */       
/*   82 */       reg.setCodigo(this.rs.getInt("codigo"));
/*   83 */       reg.setDescripcion(this.rs.getString("descripcion"));
/*   84 */       reg.setEspecializado(this.rs.getString("especializado"));
/*   85 */       reg.setDuracion(this.rs.getInt("duracion"));
/*   86 */       reg.setUnidadMedida(this.rs.getString("unidad_medida"));
/*   87 */       reg.setPorcentajeEsc1(this.rs.getInt("porcentaje_esc_1"));
/*   88 */       reg.setPorcentajeEsc2(this.rs.getInt("porcentaje_esc_2"));
/*   89 */       reg.setPorcentajeEsc3(this.rs.getInt("porcentaje_esc_3"));
/*   90 */       reg.setPorcentajeEsc4(this.rs.getInt("porcentaje_esc_4"));
/*   91 */       reg.setTipoServicio(this.rs.getString("tipo_servicio"));
/*   92 */       reg.setAnidar(this.rs.getString("anidar"));
/*   93 */       reg.setCambiaProveedor(this.rs.getString("cambia_proveedor"));
/*   94 */       reg.setPermiteDevolver(this.rs.getString("permite_devolver"));
/*   95 */       reg.setReasignarAuditor(this.rs.getString("reasignar_auditor"));
/*   96 */       reg.setCalificarServicio(this.rs.getString("calificar_servicio"));
/*   97 */       reg.setClientePreferencia(this.rs.getString("cliente_preferencia"));
/*   98 */       reg.setPermiteDevolverPoliticas(this.rs.getString("permite_devolver_politicas"));
/*   99 */       reg.setAutonumerarSolicitud(this.rs.getString("autonumerar_solicitud"));
/*  100 */       reg.setPermiteDevolverAtencion(this.rs.getString("permite_devolver_atencion"));
/*  101 */       reg.setPermitirEscogerProveedor(this.rs.getString("permitir_escoger_proveedor"));
/*  102 */       reg.setAutoaceptarAplamientos(this.rs.getString("autoaceptar_aplamientos"));
/*  103 */       reg.setCerrarPorEscalamientos(this.rs.getString("cerrar_por_escalamientos"));
/*  104 */       reg.setIndFlujoTrabajo(this.rs.getString("ind_flujo_trabajo"));
/*  105 */       reg.setIndCorreoCalificacion(this.rs.getString("ind_correo_calificacion"));
/*  106 */       reg.setIndAvanzarCaracteristica(this.rs.getString("ind_avanzar_caracteristica"));
/*  107 */       reg.setProceso(this.rs.getString("proceso"));
/*  108 */       reg.setSubproceso(this.rs.getString("subproceso"));
/*  109 */       reg.setArchivoAnexo(this.rs.getString("archivo_anexo"));
/*  110 */       reg.setNumeroAnexos(this.rs.getInt("numero_anexos"));
/*  111 */       reg.setNumeroAnexosEnvio(this.rs.getInt("numero_anexos_envio"));
/*  112 */       reg.setCorreoNotificacion(this.rs.getString("correo_notificacion"));
/*  113 */       reg.setEscalarA(this.rs.getInt("escalar_a"));
/*  114 */       reg.setEstado(this.rs.getString("estado"));
/*  115 */       reg.setObservaciones(this.rs.getString("observaciones"));
/*  116 */       reg.setMensaje(this.rs.getString("mensaje"));
/*  117 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  118 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  119 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*  120 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*      */       
/*      */       try {
/*  123 */         reg.setNombreEspecializado(this.rs.getString("nombre_especializado"));
/*  124 */         reg.setNombreUnidadMedida(this.rs.getString("nombre_unidad_medida"));
/*  125 */         reg.setNombreTipoServicio(this.rs.getString("nombre_tipo_servicio"));
/*      */         
/*  127 */         reg.setNombreEstado(this.rs.getString("nombre_estado"));
/*      */       
/*      */       }
/*  130 */       catch (Exception e) {}
/*  131 */       return reg;
/*      */     }
/*  133 */     catch (Exception e) {
/*  134 */       e.printStackTrace();
/*  135 */       Utilidades.writeError("ServiciosDAO:leerRegistro ", e);
/*      */       
/*  137 */       return null;
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
/*      */   public Collection<ServiciosDTO> cargarTodos(int codigo, String descripcion, String especializado) {
/*  149 */     Collection<ServiciosDTO> resultados = new ArrayList<ServiciosDTO>();
/*      */     try {
/*  151 */       String s = "select t.codigo,t.descripcion,t.especializado,m1.DESCRIPCION as nombre_especializado,t.duracion,t.unidad_medida,r2.nombre_unidad as nombre_unidad_medida,t.porcentaje_esc_1,t.porcentaje_esc_2,t.porcentaje_esc_3,t.porcentaje_esc_4,t.tipo_servicio,m4.DESCRIPCION as nombre_tipo_servicio,t.anidar,t.cambia_proveedor,t.permite_devolver,t.reasignar_auditor,t.calificar_servicio,t.cliente_preferencia,t.permite_devolver_politicas,t.autonumerar_solicitud,t.permite_devolver_atencion,t.permitir_escoger_proveedor,t.autoaceptar_aplamientos,t.cerrar_por_escalamientos,t.ind_flujo_trabajo,t.ind_correo_calificacion,t.ind_avanzar_caracteristica,t.proceso,t.subproceso,t.archivo_anexo,t.numero_anexos,t.numero_anexos_envio,t.correo_notificacion,t.escalar_a,t.estado,m6.DESCRIPCION as nombre_estado,t.observaciones,t.mensaje,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from servicios t  left join sis_multivalores m1 on (m1.tabla='CLASE_SERVICIO' and m1.VALOR=t.especializado) left join sis_unidades_medida r2 on (r2.codigo_unidad=t.unidad_medida) left join sis_multivalores m4 on (m4.tabla='TIPO_SERVICIO' and m4.VALOR=t.tipo_servicio) left join sis_multivalores m6 on (m6.tabla='ESTADO_REGISTRO' and m6.VALOR=t.estado) where 1=1";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  201 */       if (codigo > 0) {
/*  202 */         s = s + " and t.codigo=" + codigo;
/*      */       }
/*  204 */       if (descripcion.length() > 0) {
/*  205 */         s = s + " and upper(t.descripcion) like upper('%" + descripcion + "%')";
/*      */       }
/*  207 */       if (especializado.length() > 0) {
/*  208 */         s = s + " and upper(t.especializado) like upper('%" + especializado + "%')";
/*      */       }
/*  210 */       s = s + " order by 1";
/*  211 */       boolean rtaDB = this.dat.parseSql(s);
/*  212 */       if (!rtaDB) {
/*  213 */         return resultados;
/*      */       }
/*  215 */       this.rs = this.dat.getResultSet();
/*  216 */       while (this.rs.next()) {
/*  217 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  220 */     catch (Exception e) {
/*  221 */       e.printStackTrace();
/*  222 */       Utilidades.writeError("ServiciosDAO:cargarTodos ", e);
/*      */     } 
/*  224 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ServiciosDTO cargarRegistro(int codigo) {
/*      */     try {
/*  234 */       String s = "select t.codigo,t.descripcion,t.especializado,m1.DESCRIPCION as nombre_especializado,t.duracion,t.unidad_medida,r2.nombre_unidad as nombre_unidad_medida,t.porcentaje_esc_1,t.porcentaje_esc_2,t.porcentaje_esc_3,t.porcentaje_esc_4,t.tipo_servicio,m4.DESCRIPCION as nombre_tipo_servicio,t.anidar,t.cambia_proveedor,t.permite_devolver,t.reasignar_auditor,t.calificar_servicio,t.cliente_preferencia,t.permite_devolver_politicas,t.autonumerar_solicitud,t.permite_devolver_atencion,t.permitir_escoger_proveedor,t.autoaceptar_aplamientos,t.cerrar_por_escalamientos,t.ind_flujo_trabajo,t.ind_correo_calificacion,t.ind_avanzar_caracteristica,t.proceso,t.subproceso,t.archivo_anexo,t.numero_anexos,t.numero_anexos_envio,t.correo_notificacion,t.escalar_a,t.estado,m6.DESCRIPCION as nombre_estado,t.observaciones,t.mensaje,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from servicios t  left join sis_multivalores m1 on (m1.tabla='CLASE_SERVICIO' and m1.VALOR=t.especializado) left join sis_unidades_medida r2 on (r2.codigo_unidad=t.unidad_medida) left join sis_multivalores m4 on (m4.tabla='TIPO_SERVICIO' and m4.VALOR=t.tipo_servicio) left join sis_multivalores m6 on (m6.tabla='ESTADO_REGISTRO' and m6.VALOR=t.estado) where  t.codigo=" + codigo + "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  286 */       boolean rtaDB = this.dat.parseSql(s);
/*  287 */       if (!rtaDB) {
/*  288 */         return null;
/*      */       }
/*  290 */       this.rs = this.dat.getResultSet();
/*  291 */       if (this.rs.next()) {
/*  292 */         return leerRegistro();
/*      */       }
/*      */     }
/*  295 */     catch (Exception e) {
/*  296 */       e.printStackTrace();
/*  297 */       Utilidades.writeError("ServiciosDAO:cargarServicios", e);
/*      */     } 
/*  299 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int siguienteRegistro() {
/*  308 */     int inumero = 1;
/*  309 */     String s = "select max(codigo) from servicios ";
/*      */     
/*      */     try {
/*  312 */       boolean rta = this.dat.parseSql(s);
/*  313 */       if (!rta) return 0; 
/*  314 */       this.rs = this.dat.getResultSet();
/*  315 */       if (this.rs.next()) {
/*  316 */         s = this.rs.getString(1);
/*  317 */         if (!this.rs.wasNull()) {
/*  318 */           inumero = Integer.parseInt(s) + 1;
/*      */         }
/*      */       } 
/*  321 */       return inumero;
/*      */     }
/*  323 */     catch (Exception e) {
/*  324 */       e.printStackTrace();
/*  325 */       Utilidades.writeError("ServiciosDAO:siguienteRegistro ", e);
/*      */       
/*  327 */       return 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RespuestaBD eliminarRegistro(int codigo) {
/*  337 */     RespuestaBD rta = new RespuestaBD();
/*      */     
/*      */     try {
/*  340 */       String s = "delete from servicios where  codigo=" + codigo + "";
/*      */ 
/*      */ 
/*      */       
/*  344 */       rta = this.dat.executeUpdate2(s);
/*      */     }
/*  346 */     catch (Exception e) {
/*  347 */       e.printStackTrace();
/*  348 */       Utilidades.writeError("ServiciosDAO:eliminarRegistro ", e);
/*  349 */       rta.setMensaje(e.getMessage());
/*      */     } 
/*  351 */     return rta;
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
/*      */   public RespuestaBD crearRegistro(int codigo, String descripcion, String especializado, int duracion, String unidadMedida, int porcentajeEsc1, int porcentajeEsc2, int porcentajeEsc3, int porcentajeEsc4, String tipoServicio, String anidar, String cambiaproveedor, String permitedevolver, String reasignarauditor, String calificarservicio, String clientePreferencia, String permiteDevolverPoliticas, String autonumerarSolicitud, String permiteDevolverAtencion, String permitirEscogerProveedor, String autoaceptarAplamientos, String cerrarPorEscalamientos, String indFlujoTrabajo, String indCorreoCalificacion, String indAvanzarCaracteristica, String proceso, String subproceso, String archivoAnexo, int numeroAnexos, int numeroAnexosEnvio, String correoNotificacion, int escalarA, String estado, String observaciones, String mensaje, String usuarioInsercion) {
/*  397 */     RespuestaBD rta = new RespuestaBD();
/*      */     
/*  399 */     int elSiguiente = siguienteRegistro();
/*  400 */     if (elSiguiente == 0) {
/*  401 */       rta.setMensaje("Generando secuencia");
/*  402 */       return rta;
/*      */     } 
/*      */     
/*      */     try {
/*  406 */       String s = "insert into servicios(codigo,descripcion,especializado,duracion,unidad_medida,porcentaje_esc_1,porcentaje_esc_2,porcentaje_esc_3,porcentaje_esc_4,tipo_servicio,anidar,cambia_proveedor,permite_devolver,reasignar_auditor,calificar_servicio,cliente_preferencia,permite_devolver_politicas,autonumerar_solicitud,permite_devolver_atencion,permitir_escoger_proveedor,autoaceptar_aplamientos,cerrar_por_escalamientos,ind_flujo_trabajo,ind_correo_calificacion,ind_avanzar_caracteristica,proceso,subproceso,archivo_anexo,numero_anexos,numero_anexos_envio,correo_notificacion,escalar_a,estado,observaciones,mensaje,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "'" + descripcion + "'," + "'" + especializado + "'," + "" + duracion + "," + "'" + unidadMedida + "'," + "" + porcentajeEsc1 + "," + "" + porcentajeEsc2 + "," + "" + porcentajeEsc3 + "," + "" + porcentajeEsc4 + "," + "'" + tipoServicio + "'," + "'" + anidar + "'," + "'" + cambiaproveedor + "'," + "'" + permitedevolver + "'," + "'" + reasignarauditor + "'," + "'" + calificarservicio + "'," + "'" + clientePreferencia + "'," + "'" + permiteDevolverPoliticas + "'," + "'" + autonumerarSolicitud + "'," + "'" + permiteDevolverAtencion + "'," + "'" + permitirEscogerProveedor + "'," + "'" + autoaceptarAplamientos + "'," + "'" + cerrarPorEscalamientos + "'," + "'" + indFlujoTrabajo + "'," + "'" + indCorreoCalificacion + "'," + "'" + indAvanzarCaracteristica + "'," + "'" + ((proceso.length() > 0) ? ("" + proceso) : "null") + "'," + "'" + ((subproceso.length() > 0) ? ("" + subproceso) : "null") + "'," + "'" + archivoAnexo + "'," + "" + numeroAnexos + "," + "" + numeroAnexosEnvio + "," + "'" + correoNotificacion + "'," + "" + escalarA + "," + "'" + estado + "'," + "'" + observaciones + "'," + "'" + mensaje + "'," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  483 */       rta = this.dat.executeUpdate2(s);
/*  484 */       rta.setSecuencia(elSiguiente);
/*      */     }
/*  486 */     catch (Exception e) {
/*  487 */       e.printStackTrace();
/*  488 */       Utilidades.writeError("%ServiciosDAO:crearRegistro ", e);
/*  489 */       rta.setMensaje(e.getMessage());
/*      */     } 
/*  491 */     return rta;
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
/*      */   public RespuestaBD modificarRegistro(int codigo, String descripcion, String especializado, int duracion, String unidadMedida, int porcentajeEsc1, int porcentajeEsc2, int porcentajeEsc3, int porcentajeEsc4, String tipoServicio, String anidar, String cambiaproveedor, String permitedevolver, String reasignarauditor, String calificarservicio, String clientePreferencia, String permiteDevolverPoliticas, String autonumerarSolicitud, String permiteDevolverAtencion, String permitirEscogerProveedor, String autoaceptarAplamientos, String cerrarPorEscalamientos, String indFlujoTrabajo, String indCorreoCalificacion, String indAvanzarCaracteristica, String proceso, String subproceso, int numeroAnexos, int numeroAnexosEnvio, String correoNotificacion, int escalarA, String estado, String observaciones, String mensaje, String usuarioModificacion) {
/*  536 */     RespuestaBD rta = new RespuestaBD();
/*      */     
/*      */     try {
/*  539 */       String s = "update servicios set  descripcion='" + descripcion + "'," + " especializado='" + especializado + "'," + " duracion=" + duracion + "," + " unidad_medida='" + unidadMedida + "'," + " porcentaje_esc_1=" + porcentajeEsc1 + "," + " porcentaje_esc_2=" + porcentajeEsc2 + "," + " porcentaje_esc_3=" + porcentajeEsc3 + "," + " porcentaje_esc_4=" + porcentajeEsc4 + "," + " tipo_servicio='" + tipoServicio + "'," + " anidar='" + anidar + "'," + " cambia_proveedor='" + cambiaproveedor + "'," + " permite_devolver='" + permitedevolver + "'," + " reasignar_auditor='" + reasignarauditor + "'," + " calificar_servicio='" + calificarservicio + "'," + " cliente_preferencia='" + clientePreferencia + "'," + " permite_devolver_politicas='" + permiteDevolverPoliticas + "'," + " autonumerar_solicitud='" + autonumerarSolicitud + "'," + " permite_devolver_atencion='" + permiteDevolverAtencion + "'," + " permitir_escoger_proveedor='" + permitirEscogerProveedor + "'," + " autoaceptar_aplamientos='" + autoaceptarAplamientos + "'," + " cerrar_por_escalamientos='" + cerrarPorEscalamientos + "'," + " ind_flujo_trabajo='" + indFlujoTrabajo + "'," + " ind_correo_calificacion='" + indCorreoCalificacion + "'," + " ind_avanzar_caracteristica='" + indAvanzarCaracteristica + "'," + " proceso=" + ((proceso.length() > 0) ? ("'" + proceso + "'") : "null") + "," + " subproceso=" + ((subproceso.length() > 0) ? ("'" + subproceso + "'") : "null") + "," + " numero_anexos=" + numeroAnexos + "," + " numero_anexos_envio=" + numeroAnexosEnvio + "," + " correo_notificacion='" + correoNotificacion + "'," + " escalar_a=" + escalarA + "," + " estado='" + estado + "'," + " observaciones='" + observaciones + "'," + " mensaje='" + mensaje + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo=" + codigo + "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  578 */       rta = this.dat.executeUpdate2(s);
/*      */     }
/*  580 */     catch (Exception e) {
/*  581 */       e.printStackTrace();
/*  582 */       Utilidades.writeError("ServiciosDAO:modificarRegistro ", e);
/*  583 */       rta.setMensaje(e.getMessage());
/*      */     } 
/*  585 */     return rta;
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
/*      */   public RespuestaBD modificarRegistroArchivo(int codigo, String nombreArchivo, String usuarioModificacion) {
/*  599 */     RespuestaBD rta = new RespuestaBD();
/*      */     
/*      */     try {
/*  602 */       String s = "update servicios set  archivo_anexo='" + nombreArchivo + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " codigo=" + codigo + "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  609 */       rta = this.dat.executeUpdate2(s);
/*      */     }
/*  611 */     catch (Exception e) {
/*  612 */       e.printStackTrace();
/*  613 */       Utilidades.writeError("ServiciosDAO:modificarRegistroArchivo ", e);
/*  614 */       rta.setMensaje(e.getMessage());
/*      */     } 
/*  616 */     return rta;
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
/*      */   public Collection<ServiciosDTO> serviciosFlujo(int codigoFlujo, int codigoServicio, String cualCombo) {
/*  632 */     Collection<ServiciosDTO> resultados = new ArrayList<ServiciosDTO>();
/*      */     
/*      */     try {
/*  635 */       String s = "";
/*  636 */       if (cualCombo.equals("O")) {
/*  637 */         s = "select s.codigo,s.descripcion from   servicios   s, wkf_detalle w where  s.codigo = w.servicio_inicio       and w.codigo_flujo = " + codigoFlujo + "       and s.codigo <>" + codigoServicio + "       and s.estado = 'A'" + " union " + " select s.codigo," + " s.descripcion" + " from   servicios   s," + " wkf_detalle w" + " where  s.codigo = w.servicio_destino" + "       and w.codigo_flujo = " + codigoFlujo + "       and s.codigo <>" + codigoServicio + "       and s.estado = 'A'" + " order by descripcion";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*  656 */       else if (cualCombo.equals("U")) {
/*  657 */         s = "select s.codigo,s.descripcion from   servicios   s where  s.codigo =" + codigoServicio;
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  663 */         s = "select s.codigo,  s.descripcion from   servicios   s where  s.estado = 'A'  and s.ind_flujo_trabajo='S'  and s.especializado not in ('N')  and s.codigo <>" + codigoServicio + " order by s.descripcion";
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  673 */       boolean rta = this.dat.parseSql(s);
/*  674 */       if (!rta) {
/*  675 */         return resultados;
/*      */       }
/*  677 */       this.rs = this.dat.getResultSet();
/*  678 */       while (this.rs.next()) {
/*  679 */         ServiciosDTO reg = new ServiciosDTO();
/*  680 */         reg.setCodigo(this.rs.getInt("codigo"));
/*  681 */         reg.setDescripcion(this.rs.getString("descripcion"));
/*  682 */         resultados.add(reg);
/*      */       }
/*      */     
/*  685 */     } catch (Exception e) {
/*  686 */       e.printStackTrace();
/*      */     } 
/*  688 */     return resultados;
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
/*      */   public ServiciosDTO cargarServicioSolicitud(int solicitud) {
/*      */     try {
/*  702 */       String s = "select * from servicios where codigo=( select  codigo_servicio  from solicitudes s where s.numero=" + solicitud + ")";
/*      */ 
/*      */ 
/*      */       
/*  706 */       boolean rta = this.dat.parseSql(s);
/*  707 */       if (!rta) return null; 
/*  708 */       this.rs = this.dat.getResultSet();
/*  709 */       if (this.rs.next()) {
/*  710 */         return leerRegistro();
/*      */       }
/*      */     }
/*  713 */     catch (SQLException e) {
/*  714 */       e.printStackTrace();
/*  715 */       Utilidades.writeError("ServiciosDAO:cargarServicioSolicitud ", e);
/*      */     } 
/*  717 */     return null;
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
/*      */   public Collection<ServiciosDTO> cargarHechos(int area, int rol, String fechaInicio, String fechaFinal) {
/*  733 */     String s = "select distinct servicios.* from servicios,solicitudes where solicitudes.codigo_servicio=servicios.codigo";
/*  734 */     if (rol == 0) {
/*  735 */       s = s + " and solicitudes.area_proveedor=" + area;
/*      */     } else {
/*  737 */       s = s + " and solicitudes.area_cliente=" + area;
/*      */     } 
/*  739 */     if (fechaInicio.length() > 0) {
/*  740 */       s = s + " and solicitudes.fecha_vigencia>=" + Utilidades.formatoFecha2(fechaInicio);
/*      */     }
/*      */     
/*  743 */     if (fechaFinal.length() > 0) {
/*  744 */       s = s + " and solicitudes.fecha_vigencia<" + Utilidades.formatoFecha2(Utilidades.siguienteDia(fechaFinal));
/*      */     }
/*  746 */     s = s + " order by descripcion";
/*      */     
/*  748 */     Collection<ServiciosDTO> resultados = new ArrayList<ServiciosDTO>();
/*      */     
/*      */     try {
/*  751 */       boolean rta = this.dat.parseSql(s);
/*  752 */       if (!rta) return resultados; 
/*  753 */       this.rs = this.dat.getResultSet();
/*  754 */       while (this.rs.next()) {
/*  755 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  758 */     catch (Exception e) {
/*  759 */       e.printStackTrace();
/*  760 */       Utilidades.writeError("ServiciosDAO:cargarHechos ", e);
/*      */     } 
/*  762 */     return resultados;
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
/*      */   public Collection<ServiciosDTO> cargarTodosDeArea(int area) {
/*  774 */     Collection<ServiciosDTO> resultados = new ArrayList<ServiciosDTO>();
/*      */     try {
/*  776 */       String s = "";
/*  777 */       if (area != -1) {
/*  778 */         s = "select servicios.* from servicios,servicios_area  where servicios_area.codigo_servicio=servicios.codigo  and servicios.estado='A'  and servicios_area.codigo_area=" + area + " order by servicios.codigo";
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  785 */         s = "select servicios.* from servicios  where estado='A' order by servicios.codigo";
/*      */       } 
/*      */ 
/*      */       
/*  789 */       boolean rta = this.dat.parseSql(s);
/*  790 */       if (!rta) {
/*  791 */         return resultados;
/*      */       }
/*      */       
/*  794 */       this.rs = this.dat.getResultSet();
/*  795 */       while (this.rs.next()) {
/*  796 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  799 */     catch (Exception e) {
/*  800 */       e.printStackTrace();
/*  801 */       Utilidades.writeError("ServiciosDAO:cargarTodosDeArea ", e);
/*      */     } 
/*  803 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<ServiciosDTO> cargarServiciosDeFuncionario(int funcionario) {
/*  812 */     Collection<ServiciosDTO> resultados = new ArrayList<ServiciosDTO>();
/*      */     
/*      */     try {
/*  815 */       String s = "select s.Codigo,       s.Descripcion as Nombre_Servicio,       s.Duracion,       m.nombre_unidad as unidad_medida,       a.Descripcion Nombre_Area,       sa.codigo_area,       p.Nombres || ' ' || p.Apellidos as Nombre_Responsable from   Servicios            s,       Servicios_Area       Sa,       Sis_Unidades_Medida      m,       Unidades_Dependencia a,       sis_usuarios  p where  Sa.Codigo_Servicio = s.Codigo       and Sa.Codigo_Area = a.Codigo       and s.Unidad_Medida = m.Codigo_Unidad       and Sa.Persona_Cargo = p.Codigo_Empleado       and s.Estado = 'A'       and s.Especializado = 'S'       and Sa.Persona_Cargo = " + funcionario + " order  by s.Codigo";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  836 */       boolean rta = this.dat.parseSql(s);
/*  837 */       if (!rta) {
/*  838 */         return resultados;
/*      */       }
/*  840 */       this.rs = this.dat.getResultSet();
/*  841 */       while (this.rs.next()) {
/*  842 */         ServiciosDTO reg = new ServiciosDTO();
/*      */         
/*  844 */         reg.setCodigo(this.rs.getInt("codigo"));
/*  845 */         reg.setDescripcion(this.rs.getString("nombre_servicio"));
/*  846 */         reg.setDuracion(this.rs.getInt("duracion"));
/*  847 */         reg.setUnidadMedida(this.rs.getString("unidad_medida"));
/*  848 */         reg.setNombreArea(this.rs.getString("nombre_area"));
/*  849 */         reg.setCodigoArea(this.rs.getInt("codigo_area"));
/*  850 */         reg.setNombreResponsable(this.rs.getString("nombre_responsable"));
/*  851 */         resultados.add(reg);
/*      */       }
/*      */     
/*      */     }
/*  855 */     catch (Exception e) {
/*  856 */       e.printStackTrace();
/*  857 */       Utilidades.writeError("ServiciosDAO:cargarServiciosDeFuncionario ", e);
/*      */     } 
/*  859 */     return resultados;
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
/*      */   public Collection<ServiciosDTO> cargarServiciosDeFuncionario(int area, int funcionario) {
/*  873 */     Collection<ServiciosDTO> resultados = new ArrayList<ServiciosDTO>();
/*      */     try {
/*  875 */       String s = "select servicios.* from servicios,servicios_area";
/*  876 */       s = s + " where servicios.especializado='S' and servicios.estado='A'";
/*  877 */       s = s + " and servicios.codigo=servicios_area.codigo_servicio";
/*  878 */       s = s + " and servicios_area.persona_cargo=" + funcionario;
/*  879 */       s = s + " and codigo_area=" + area;
/*  880 */       s = s + " union ";
/*  881 */       s = s + " select servicios.* from servicios,servicios_area ";
/*  882 */       s = s + " where servicios.especializado='N'";
/*  883 */       s = s + " and servicios.estado='A'";
/*  884 */       s = s + " and servicios.codigo=servicios_area.codigo_servicio";
/*  885 */       s = s + " and codigo_area=" + area;
/*  886 */       s = s + " union";
/*  887 */       s = s + " select servicios.* from servicios,proveedor_multiple ";
/*  888 */       s = s + " where servicios.especializado in('D','M','O')";
/*  889 */       s = s + " and servicios.estado='A'";
/*  890 */       s = s + " and servicios.codigo=proveedor_multiple.codigo_servicio";
/*  891 */       s = s + " and proveedor_multiple.codigo_area=" + area;
/*  892 */       s = s + " and proveedor_multiple.persona_cargo=" + funcionario;
/*  893 */       boolean rta = this.dat.parseSql(s);
/*  894 */       if (!rta) {
/*  895 */         return resultados;
/*      */       }
/*  897 */       this.rs = this.dat.getResultSet();
/*  898 */       while (this.rs.next()) {
/*  899 */         ServiciosDTO reg = new ServiciosDTO();
/*      */         
/*  901 */         reg.setCodigo(this.rs.getInt("codigo"));
/*  902 */         reg.setDescripcion(this.rs.getString("nombre_servicio"));
/*  903 */         reg.setDuracion(this.rs.getInt("duracion"));
/*  904 */         reg.setUnidadMedida(this.rs.getString("unidad_medida"));
/*  905 */         resultados.add(reg);
/*      */       }
/*      */     
/*  908 */     } catch (Exception e) {
/*  909 */       e.printStackTrace();
/*  910 */       Utilidades.writeError("ServiciosDAO:cargarServiciosDeFuncionario ", e);
/*      */     } 
/*  912 */     return resultados;
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
/*      */   public Collection<ServiciosDTO> cargarTodosDeArea(int area, int codigo_persona) {
/*  924 */     Collection<ServiciosDTO> resultados = new ArrayList<ServiciosDTO>();
/*      */     
/*      */     try {
/*  927 */       String s = "";
/*  928 */       if (area != -1) {
/*  929 */         s = "select s.* from servicios s,servicios_area sa where sa.codigo_servicio=s.codigo  and s.estado='A' and coalesce(s.ind_flujo_trabajo,'N')='N' and sa.codigo_area=" + area + " and 1=case when s.cliente_preferencia ='S' " + "   and " + codigo_persona + " not in (" + " select codigo_persona from clientes_preferenciales cp" + " where cp.codigo_servicio=s.codigo)  then 0 else 1 end" + " order by s.descripcion";
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  941 */         s = "select s.* from servicios s where s.estado='A'  and coalesce(s.ind_flujo_trabajo,'N')='N' order by s.descripcion";
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  947 */       boolean rta = this.dat.parseSql(s);
/*  948 */       if (!rta) {
/*  949 */         return resultados;
/*      */       }
/*  951 */       this.rs = this.dat.getResultSet();
/*  952 */       while (this.rs.next()) {
/*  953 */         resultados.add(leerRegistro());
/*      */       
/*      */       }
/*      */     }
/*  957 */     catch (Exception e) {
/*  958 */       e.printStackTrace();
/*  959 */       Utilidades.writeError("ServiciosDAO:cargarTodosDeArea ", e);
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
/*      */ 
/*      */   
/*      */   public Collection<ServiciosDTO> cargarParecidos(String like, int codigo_persona) {
/*  973 */     Collection<ServiciosDTO> resultados = new ArrayList<ServiciosDTO>();
/*      */     
/*      */     try {
/*  976 */       String s = "select * from servicios where estado='A' and coalesce(ind_flujo_trabajo,'N')='N' and upper(descripcion) like upper('%" + like + "%')" + "   and 1=case when cliente_preferencia ='S' and " + codigo_persona + " not in (" + "       select codigo_persona from clientes_preferenciales" + "          where clientes_preferenciales.codigo_servicio=servicios.codigo)  then 0 " + " else 1 end" + " ORDER BY descripcion";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  985 */       boolean rta = this.dat.parseSql(s);
/*  986 */       if (!rta) {
/*  987 */         return resultados;
/*      */       }
/*      */       
/*  990 */       this.rs = this.dat.getResultSet();
/*  991 */       while (this.rs.next()) {
/*  992 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  995 */     catch (Exception e) {
/*  996 */       e.printStackTrace();
/*  997 */       Utilidades.writeError("ServiciosDAO:cargarParecidos ", e);
/*      */     } 
/*  999 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<ServiciosDTO> serviciosAuditoria() {
/* 1010 */     Collection<ServiciosDTO> resultados = new ArrayList<ServiciosDTO>();
/*      */     try {
/* 1012 */       String s = "select * from servicios s where s.tipo_servicio>1 and estado='A' order by descripcion";
/*      */ 
/*      */ 
/*      */       
/* 1016 */       boolean rtaDB = this.dat.parseSql(s);
/* 1017 */       if (!rtaDB) {
/* 1018 */         return resultados;
/*      */       }
/* 1020 */       this.rs = this.dat.getResultSet();
/* 1021 */       while (this.rs.next()) {
/* 1022 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/* 1025 */     catch (Exception e) {
/* 1026 */       e.printStackTrace();
/* 1027 */       Utilidades.writeError("ServiciosFactory::serviciosAuditoria ", e);
/*      */     } 
/* 1029 */     return resultados;
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
/*      */   public Collection<ServiciosDTO> cargarTodos(String descripcion, String especializado) {
/* 1043 */     Collection<ServiciosDTO> resultados = new ArrayList<ServiciosDTO>();
/*      */     try {
/* 1045 */       String s = "select * from servicios where estado='A'";
/*      */       
/* 1047 */       if (descripcion.length() > 0) {
/* 1048 */         s = s + " and upper(descripcion) like upper('%" + descripcion + "%')";
/*      */       }
/* 1050 */       if (especializado.length() > 0) {
/* 1051 */         s = s + " and upper(especializado) like upper('%" + especializado + "%')";
/*      */       }
/*      */       
/* 1054 */       s = s + " order by descripcion";
/* 1055 */       boolean rtaDB = this.dat.parseSql(s);
/* 1056 */       if (!rtaDB) {
/* 1057 */         return resultados;
/*      */       }
/* 1059 */       this.rs = this.dat.getResultSet();
/* 1060 */       while (this.rs.next())
/*      */       {
/* 1062 */         ServiciosDTO reg = new ServiciosDTO();
/* 1063 */         reg.setCodigo(this.rs.getInt("codigo"));
/* 1064 */         reg.setDescripcion(this.rs.getString("descripcion"));
/* 1065 */         reg.setDuracion(this.rs.getInt("duracion"));
/* 1066 */         reg.setUnidadMedida(this.rs.getString("unidad_medida"));
/* 1067 */         reg.setEspecializado(this.rs.getString("especializado"));
/* 1068 */         resultados.add(reg);
/*      */       }
/*      */     
/* 1071 */     } catch (Exception e) {
/* 1072 */       e.printStackTrace();
/* 1073 */       Utilidades.writeError("ServiciosDAO:cargarTodos ", e);
/*      */     } 
/* 1075 */     return resultados;
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ServiciosDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */