/*     */ package sinco.data;
/*     */ 
/*     */ import java.sql.ResultSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import sinco.business.ContEstudioPrevioDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContEstudioPrevioDAO;
/*     */ import sinco.data.DBManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContEstudioPrevioDAO
/*     */ {
/*     */   ResultSet rs;
/*  28 */   DBManager dat = new DBManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  46 */       this.dat.close();
/*     */     }
/*  48 */     catch (Exception e) {
/*  49 */       Utilidades.writeError("ContEstudioPrevioDAO:close ", e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContEstudioPrevioDTO next() {
/*     */     try {
/*  60 */       if (this.rs.next()) {
/*  61 */         return leerRegistro();
/*     */       }
/*     */     }
/*  64 */     catch (Exception e) {
/*  65 */       e.printStackTrace();
/*  66 */       Utilidades.writeError("ContEstudioPrevioDAO:next ", e);
/*     */     } 
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContEstudioPrevioDTO leerRegistro() {
/*     */     try {
/*  78 */       ContEstudioPrevioDTO reg = new ContEstudioPrevioDTO();
/*     */       
/*  80 */       reg.setNumeroEstudio(this.rs.getInt("numero_estudio"));
/*  81 */       reg.setTipoEstudio(this.rs.getString("tipo_estudio"));
/*  82 */       reg.setFechaPresentacion(this.rs.getString("fecha_presentacion"));
/*  83 */       reg.setObjetoContratar(this.rs.getString("objeto_contratar"));
/*  84 */       reg.setDescripcionNecesidad(this.rs.getString("descripcion_necesidad"));
/*  85 */       reg.setSoporteEconomico(this.rs.getString("soporte_economico"));
/*  86 */       reg.setValor(this.rs.getDouble("valor"));
/*  87 */       reg.setValorTexto(this.rs.getString("valor_texto"));
/*  88 */       reg.setFormaPago(this.rs.getString("forma_pago"));
/*  89 */       reg.setFormaPagoDescripcion(this.rs.getString("forma_pago_descripcion"));
/*  90 */       reg.setTipoPlazo(this.rs.getString("tipo_plazo"));
/*  91 */       reg.setPlazo(this.rs.getDouble("plazo"));
/*  92 */       reg.setPlazoDescripcion(this.rs.getString("plazo_descripcion"));
/*  93 */       reg.setTipoContrato(this.rs.getString("tipo_contrato"));
/*  94 */       reg.setFormaContrato(this.rs.getString("forma_contrato"));
/*  95 */       reg.setTarifa(this.rs.getString("tarifa"));
/*  96 */       reg.setTarifaDescripcion(this.rs.getString("tarifa_descripcion"));
/*  97 */       reg.setLugarEjecucion(this.rs.getString("lugar_ejecucion"));
/*  98 */       reg.setClaseGasto(this.rs.getString("clase_gasto"));
/*  99 */       reg.setAplicaIva(this.rs.getString("aplica_iva"));
/* 100 */       reg.setDependencia(this.rs.getInt("dependencia"));
/* 101 */       reg.setPorcentajeAnticipo(this.rs.getDouble("porcentaje_anticipo"));
/* 102 */       reg.setPorcentajeTarifa(this.rs.getDouble("porcentaje_tarifa"));
/* 103 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/* 104 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/* 105 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/* 106 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/* 107 */       reg.setDescripcionFormaContrato(this.rs.getString("descripcionFormaContrato"));
/* 108 */       reg.setDescripcionFormaPago(this.rs.getString("descripcionFormaPago"));
/* 109 */       reg.setDescripcionTipoContrato(this.rs.getString("descripcionTipoContrato"));
/* 110 */       reg.setEmpleadoSupervisor(this.rs.getInt("empleado_supervisor"));
/* 111 */       reg.setActividadEspecifica(this.rs.getString("actividad_especifica"));
/* 112 */       reg.setFechaDesde(this.rs.getString("fecha_desde"));
/* 113 */       reg.setFechaHasta(this.rs.getString("fecha_hasta"));
/* 114 */       reg.setJustificacionValor(this.rs.getString("justificacion_valor"));
/* 115 */       return reg;
/*     */     }
/* 117 */     catch (Exception e) {
/* 118 */       e.printStackTrace();
/* 119 */       Utilidades.writeError("ContEstudioPrevioDAO:leerRegistro ", e);
/*     */       
/* 121 */       return null;
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
/*     */   public Collection<ContEstudioPrevioDTO> cargarTodos(int numeroEstudio, String tipoEstudio, String fechaPresentacionDesde, String fechaPresentacionHasta, String tipoContrato, String formaContrato, int dependencia) {
/* 137 */     Collection<ContEstudioPrevioDTO> resultados = new ArrayList<ContEstudioPrevioDTO>();
/*     */     try {
/* 139 */       String s = "select  t.numero_estudio,t.tipo_estudio,t.fecha_presentacion,t.objeto_contratar,t.descripcion_necesidad,t.soporte_economico,t.valor,t.valor_texto,t.forma_pago,t.forma_pago_descripcion,t.tipo_plazo,t.plazo,t.plazo_descripcion,t.tipo_contrato,t.forma_contrato,t.tarifa,t.tarifa_descripcion,t.lugar_ejecucion,t.clase_gasto,t.aplica_iva,t.dependencia,t.porcentaje_anticipo,t.porcentaje_tarifa,t.empleado_supervisor,t.actividad_especifica,t.fecha_desde,t.fecha_hasta,t.justificacion_valor,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion, s1.descripcion as descripcionFormaPago, s2.descripcion as descripcionTipoContrato, s3.descripcion as descripcionFormaContrato  from cont_estudio_previo t  left join sis_multivalores s1 on(s1.valor=t.forma_pago and s1.tabla='tipo_pago_contrato')  left join sis_multivalores s2 on(s2.valor=t.tipo_contrato and s2.tabla in ('tipo_contrato_admin','tipo_contrato_salud'))  left join sis_multivalores s3 on(s3.valor=t.forma_contrato and s3.tabla in ('forma_contrato_admin','forma_contrato_salud'))  where 1=1";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 180 */       if (numeroEstudio > 0) {
/* 181 */         s = s + " and t.numero_estudio=" + numeroEstudio;
/*     */       }
/* 183 */       if (tipoEstudio.length() > 0) {
/* 184 */         s = s + " and upper(t.tipo_estudio) like upper('%" + tipoEstudio + "%')";
/*     */       }
/* 186 */       if (fechaPresentacionDesde.length() > 0) {
/* 187 */         s = s + " and t.fecha_presentacion>=" + Utilidades.formatoFecha2(fechaPresentacionDesde);
/*     */       }
/* 189 */       if (fechaPresentacionHasta.length() > 0) {
/* 190 */         s = s + " and t.fecha_presentacion < " + Utilidades.formatoFecha2(fechaPresentacionHasta);
/*     */       }
/* 192 */       if (tipoContrato.length() > 0) {
/* 193 */         s = s + " and upper(t.tipo_contrato) like upper('%" + tipoContrato + "%')";
/*     */       }
/* 195 */       if (formaContrato.length() > 0) {
/* 196 */         s = s + " and upper(t.forma_contrato) like upper('%" + formaContrato + "%')";
/*     */       }
/* 198 */       if (dependencia > 0) {
/* 199 */         s = s + " and t.dependencia = " + dependencia;
/*     */       }
/* 201 */       s = s + " order by 1";
/* 202 */       boolean rtaDB = this.dat.parseSql(s);
/* 203 */       if (!rtaDB) {
/* 204 */         return resultados;
/*     */       }
/* 206 */       this.rs = this.dat.getResultSet();
/* 207 */       while (this.rs.next()) {
/* 208 */         resultados.add(leerRegistro());
/*     */       }
/*     */     }
/* 211 */     catch (Exception e) {
/* 212 */       e.printStackTrace();
/* 213 */       Utilidades.writeError("ContEstudioPrevioDAO:cargarTodos ", e);
/*     */     } 
/* 215 */     return resultados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContEstudioPrevioDTO cargarRegistro(int numeroEstudio) {
/*     */     try {
/* 226 */       String s = "select t.numero_estudio,t.tipo_estudio,t.fecha_presentacion,t.objeto_contratar,t.descripcion_necesidad,t.soporte_economico,t.valor,t.valor_texto,t.forma_pago,t.forma_pago_descripcion,t.tipo_plazo,t.plazo,t.plazo_descripcion,t.tipo_contrato,t.forma_contrato,t.tarifa,t.tarifa_descripcion,t.lugar_ejecucion,t.clase_gasto,t.aplica_iva,t.dependencia,t.porcentaje_anticipo,t.porcentaje_tarifa,t.empleado_supervisor,t.actividad_especifica,t.fecha_desde,t.fecha_hasta,t.justificacion_valor,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion, s1.descripcion as descripcionFormaPago, s2.descripcion as descripcionTipoContrato, s3.descripcion as descripcionFormaContrato  from cont_estudio_previo t  left join sis_multivalores s1 on(s1.valor=t.forma_pago and s1.tabla='forma_pago_contrato')  left join sis_multivalores s2 on(s2.valor=t.tipo_contrato and s2.tabla in ('tipo_contrato_admin','tipo_contrato_salud'))  left join sis_multivalores s3 on(s3.valor=t.forma_contrato and s3.tabla in ('forma_contrato_admin','forma_contrato_salud'))  where t.numero_estudio=" + numeroEstudio;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 267 */       boolean rtaDB = this.dat.parseSql(s);
/* 268 */       if (!rtaDB) {
/* 269 */         return null;
/*     */       }
/* 271 */       this.rs = this.dat.getResultSet();
/* 272 */       if (this.rs.next()) {
/* 273 */         return leerRegistro();
/*     */       }
/*     */     }
/* 276 */     catch (Exception e) {
/* 277 */       e.printStackTrace();
/* 278 */       Utilidades.writeError("ContEstudioPrevioDAO:cargarContEstudioPrevio", e);
/*     */     } 
/* 280 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String fechaMaxima() {
/*     */     try {
/* 291 */       String s = "select coalesce(max(t.fecha_presentacion),to_date('2016-01-01','yyyy-mm-dd'))  fecha_presentacion from cont_estudio_previo t";
/*     */ 
/*     */       
/* 294 */       boolean rtaDB = this.dat.parseSql(s);
/* 295 */       if (!rtaDB) {
/* 296 */         return null;
/*     */       }
/* 298 */       this.rs = this.dat.getResultSet();
/* 299 */       if (this.rs.next()) {
/* 300 */         return this.rs.getString("fecha_presentacion");
/*     */       }
/*     */     }
/* 303 */     catch (Exception e) {
/* 304 */       e.printStackTrace();
/* 305 */       Utilidades.writeError("ContEstudioPrevioDAO:cargarContEstudioPrevio", e);
/*     */     } 
/* 307 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int siguienteRegistro() {
/* 316 */     int inumero = 1;
/* 317 */     String s = "select max(numero_estudio) from cont_estudio_previo ";
/*     */     try {
/* 319 */       boolean rta = this.dat.parseSql(s);
/* 320 */       if (!rta) return 0; 
/* 321 */       this.rs = this.dat.getResultSet();
/* 322 */       if (this.rs.next()) {
/* 323 */         s = this.rs.getString(1);
/* 324 */         if (!this.rs.wasNull()) {
/* 325 */           inumero = Integer.parseInt(s) + 1;
/*     */         }
/*     */       } 
/* 328 */       return inumero;
/*     */     }
/* 330 */     catch (Exception e) {
/* 331 */       e.printStackTrace();
/* 332 */       Utilidades.writeError("ContEstudioPrevioDAO:siguienteRegistro ", e);
/*     */       
/* 334 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean eliminarRegistro(int numeroEstudio) {
/*     */     try {
/* 344 */       String s = "delete from cont_estudio_previo where  numero_estudio=" + numeroEstudio + "";
/*     */ 
/*     */ 
/*     */       
/* 348 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 351 */     catch (Exception e) {
/* 352 */       e.printStackTrace();
/* 353 */       Utilidades.writeError("ContEstudioPrevioDAO:eliminarRegistro ", e);
/*     */       
/* 355 */       return false;
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
/*     */   public int crearRegistro(String tipoEstudio, String fechaPresentacion, String objetoContratar, String descripcionNecesidad, String soporteEconomico, double valor, String formaPago, String formaPagoDescripcion, String tipoPlazo, double plazo, String plazoDescripcion, String tipoContrato, String formaContrato, String tarifa, String tarifaDescripcion, String lugarEjecucion, String claseGasto, String aplicaIva, String valorTexto, int dependencia, double porcentajeAnticipo, double porcentajeTarifa, int empleadoSupervisor, String justificacionValor, String actividadEspecifica, String fechaDesde, String fechaHasta, String usuarioInsercion) {
/* 393 */     int elSiguiente = siguienteRegistro();
/* 394 */     if (elSiguiente == 0) {
/* 395 */       return 0;
/*     */     }
/*     */     try {
/* 398 */       String s = "insert into cont_estudio_previo(numero_estudio,tipo_estudio,fecha_presentacion,objeto_contratar,descripcion_necesidad,soporte_economico,valor,forma_pago,forma_pago_descripcion,tipo_plazo,plazo,plazo_descripcion,tipo_contrato,forma_contrato,tarifa,tarifa_descripcion,lugar_ejecucion,clase_gasto,aplica_iva,valor_texto,dependencia,porcentaje_anticipo,porcentaje_tarifa,empleado_supervisor,justificacion_valor,actividad_especifica,fecha_desde,fecha_hasta,fecha_insercion,usuario_insercion) values ('" + elSiguiente + "'," + "'" + tipoEstudio + "'," + "" + Utilidades.formatoFecha2(fechaPresentacion) + "," + "'" + objetoContratar + "'," + "'" + descripcionNecesidad + "'," + "'" + soporteEconomico + "'," + "" + valor + "," + "'" + formaPago + "'," + "'" + formaPagoDescripcion + "'," + "'" + tipoPlazo + "'," + "" + plazo + "," + "'" + plazoDescripcion + "'," + "'" + tipoContrato + "'," + "'" + formaContrato + "'," + "'" + tarifa + "'," + "'" + tarifaDescripcion + "'," + "'" + lugarEjecucion + "'," + "'" + claseGasto + "'," + "'" + aplicaIva + "'," + "'" + valorTexto + "'," + "" + dependencia + "," + "" + porcentajeAnticipo + "," + "" + porcentajeTarifa + "," + "" + ((empleadoSupervisor == 0) ? "null" : ("" + empleadoSupervisor)) + "," + "'" + justificacionValor + "'," + "'" + actividadEspecifica + "'," + "" + Utilidades.formatoFecha2(fechaDesde) + "," + "" + Utilidades.formatoFecha2(fechaHasta) + "," + "" + Utilidades.getFechaBD() + "," + "'" + usuarioInsercion + "'" + ")";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 461 */       this.dat.executeUpdate(s);
/* 462 */       return elSiguiente;
/*     */     }
/* 464 */     catch (Exception e) {
/* 465 */       e.printStackTrace();
/* 466 */       Utilidades.writeError("%ContEstudioPrevioDAO:crearRegistro ", e);
/*     */       
/* 468 */       return 0;
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
/*     */   public boolean modificarRegistro(int numeroEstudio, String tipoEstudio, String fechaPresentacion, String objetoContratar, String descripcionNecesidad, String soporteEconomico, double valor, String formaPago, String formaPagoDescripcion, String tipoPlazo, double plazo, String plazoDescripcion, String tipoContrato, String formaContrato, String tarifa, String tarifaDescripcion, String lugarEjecucion, String claseGasto, String aplicaIva, String valorTexto, int dependencia, double porcentajeAnticipo, double porcentajeTarifa, int empleadoSupervisor, String justificacionValor, String actividadEspecifica, String fechaDesde, String fechaHasta, String usuarioModificacion) {
/*     */     try {
/* 508 */       String s = "update cont_estudio_previo set  tipo_estudio='" + tipoEstudio + "'," + " fecha_presentacion=" + Utilidades.formatoFecha2(fechaPresentacion) + "," + " objeto_contratar='" + objetoContratar + "'," + " descripcion_necesidad='" + descripcionNecesidad + "'," + " soporte_economico='" + soporteEconomico + "'," + " valor=" + valor + "," + " forma_pago='" + formaPago + "'," + " forma_pago_descripcion='" + formaPagoDescripcion + "'," + " tipo_plazo='" + tipoPlazo + "'," + " plazo=" + plazo + "," + " plazo_descripcion='" + plazoDescripcion + "'," + " tipo_contrato='" + tipoContrato + "'," + " forma_contrato='" + formaContrato + "'," + " tarifa='" + tarifa + "'," + " tarifa_descripcion='" + tarifaDescripcion + "'," + " lugar_ejecucion='" + lugarEjecucion + "'," + " clase_gasto='" + claseGasto + "'," + " aplica_iva='" + aplicaIva + "'," + " valor_texto='" + valorTexto + "'," + " dependencia=" + dependencia + "," + " porcentaje_anticipo=" + porcentajeAnticipo + "," + " porcentaje_tarifa=" + porcentajeTarifa + "," + " empleado_supervisor=" + ((empleadoSupervisor == 0) ? "null" : ("" + empleadoSupervisor)) + "," + " justificacion_valor='" + justificacionValor + "'," + " actividad_especifica='" + actividadEspecifica + "'," + " fecha_desde=" + Utilidades.formatoFecha2(fechaDesde) + "," + " fecha_hasta=" + Utilidades.formatoFecha2(fechaHasta) + "," + " fecha_modificacion=" + Utilidades.getFechaBD() + "," + " usuario_modificacion='" + usuarioModificacion + "'" + " where" + " numero_estudio=" + numeroEstudio + "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 541 */       return this.dat.executeUpdate(s);
/*     */     
/*     */     }
/* 544 */     catch (Exception e) {
/* 545 */       e.printStackTrace();
/* 546 */       Utilidades.writeError("ContEstudioPrevioDAO:modificarRegistro ", e);
/*     */       
/* 548 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int copiarRegistro(int numeroEstudio, String elUsuario) {
/* 557 */     int elSiguiente = siguienteRegistro();
/* 558 */     if (elSiguiente == 0) {
/* 559 */       return -1;
/*     */     }
/* 561 */     String s = " INSERT INTO cont_estudio_previo(numero_estudio, tipo_estudio, fecha_presentacion,objeto_contratar, descripcion_necesidad, soporte_economico, valor, forma_pago, forma_pago_descripcion, tipo_plazo, plazo, plazo_descripcion, tipo_contrato, forma_contrato, tarifa, tarifa_descripcion, lugar_ejecucion, clase_gasto, aplica_iva, fecha_insercion, usuario_insercion, valor_texto,dependencia,porcentaje_anticipo,porcentaje_tarifa,empleado_supervisor,actividad_especifica,fecha_desde,fecha_hasta,justificacion_valor) SELECT  " + elSiguiente + "," + "tipo_estudio, " + "fecha_presentacion, " + "objeto_contratar, " + "descripcion_necesidad, " + "soporte_economico, " + "valor, " + "forma_pago, " + "forma_pago_descripcion, " + "tipo_plazo," + "plazo, " + "plazo_descripcion, " + "tipo_contrato, " + "forma_contrato, " + "tarifa, " + "tarifa_descripcion, " + "lugar_ejecucion, " + "clase_gasto, " + "aplica_iva, " + "" + Utilidades.getFechaBD() + ", " + "'" + elUsuario + "', " + "valor_texto," + "dependencia, " + "porcentaje_anticipo," + "porcentaje_tarifa," + "empleado_supervisor," + "actividad_especifica," + "fecha_desde," + "fecha_hasta," + "justificacion_valor" + " from cont_estudio_previo " + " where numero_estudio=" + numeroEstudio;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 624 */     boolean rtaBD = this.dat.executeUpdate(s);
/* 625 */     if (rtaBD == true) {
/* 626 */       s = "INSERT INTO cont_estudio_previo_items(numero_estudio, consecutivo_item, tipo_item, descripcion_item, fecha_insercion, usuario_insercion) SELECT " + elSiguiente + "," + "consecutivo_item, " + "tipo_item, " + "descripcion_item, " + "" + Utilidades.getFechaBD() + ", " + "'" + elUsuario + "' " + " from cont_estudio_previo_items " + " where numero_estudio=" + numeroEstudio + ";";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 642 */       s = s + "INSERT INTO cont_estudio_previo_servicios(" + "numero_estudio, " + "codigo_servicio, " + "departamento, " + "ciudad, " + "imputacion, " + "valor_upc, " + "factor, " + "afiliados, " + "meses, " + "dias, " + "fecha_insercion, " + "usuario_insercion, " + "valor_mes) " + "SELECT " + elSiguiente + "," + "codigo_servicio, " + "departamento, " + "ciudad, " + "imputacion, " + "valor_upc, " + "factor, " + "afiliados, " + "meses, " + "dias, " + "" + Utilidades.getFechaBD() + ", " + "'" + elUsuario + "', " + "valor_mes " + " from cont_estudio_previo_servicios " + " where numero_estudio=" + numeroEstudio + ";";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 672 */       s = s + "INSERT INTO cont_estudio_previo_tarifas(" + "titulo, numero_estudio, " + "tipo_cuadro, " + "consecutivo, " + "valor_1, " + "valor_2, " + "valor_3, " + "valor_4, " + "valor_5, " + "valor_6, " + "usuario_insercion, " + "fecha_insercion) " + "SELECT titulo," + elSiguiente + "," + "tipo_cuadro, " + "consecutivo, " + "valor_1, " + "valor_2, " + "valor_3, " + "valor_4, " + "valor_5, " + "valor_6, " + "'" + elUsuario + "', " + "" + Utilidades.getFechaBD() + " " + " FROM cont_estudio_previo_tarifas " + " where numero_estudio=" + numeroEstudio + ";";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 698 */       s = s + "INSERT INTO cont_est_prev_actividades(" + "numero_estudio, " + "consecutivo_actividad, " + "actividad, " + "porcentaje, " + "usuario_insercion, " + "fecha_insercion) " + "SELECT " + elSiguiente + "," + "consecutivo_actividad, " + "actividad, " + "porcentaje, " + "'" + elUsuario + "', " + "" + Utilidades.getFechaBD() + " " + " FROM cont_est_prev_actividades " + " where numero_estudio=" + numeroEstudio + ";";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 714 */       s = s + "INSERT INTO cont_est_prev_subactividades(" + "numero_estudio, " + "consecutivo_actividad, " + "consecutivo_subactividad, " + "subactividad, " + "usuario_insercion, " + "fecha_insercion) " + "SELECT " + elSiguiente + "," + "consecutivo_actividad, " + "consecutivo_subactividad, " + "subactividad, " + "'" + elUsuario + "', " + "" + Utilidades.getFechaBD() + " " + " FROM cont_est_prev_subactividades " + " where numero_estudio=" + numeroEstudio + ";";
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 749 */     this.dat.executeUpdate(s);
/* 750 */     return elSiguiente;
/*     */   }
/*     */   
/*     */   public String getImputacionAdmin(int numeroEstudio) {
/* 754 */     String s = "select item.descripcion_item from cont_estudio_previo e left join cont_estudio_previo_items item on (item.numero_estudio=e.numero_estudio)  where upper(item.tipo_item)='IMPUTACION' and item.numero_estudio = " + numeroEstudio;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 761 */       boolean rta = this.dat.parseSql(s);
/* 762 */       if (!rta) return ""; 
/* 763 */       this.rs = this.dat.getResultSet();
/* 764 */       if (this.rs.next()) {
/* 765 */         s = this.rs.getString(1);
/*     */       }
/* 767 */       return s;
/*     */     }
/* 769 */     catch (Exception e) {
/* 770 */       e.printStackTrace();
/* 771 */       Utilidades.writeError("ContEstudioPrevioDAO:siguienteRegistro ", e);
/*     */       
/* 773 */       return "";
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean existeNumeroEstudio(int numeroEstudio) {
/* 779 */     dat = new DBManager();
/*     */     try {
/* 781 */       String s = "select *  from cont_estudio_previo  where numero_estudio=" + numeroEstudio + "";
/*     */ 
/*     */       
/* 784 */       dat.parseSql(s);
/* 785 */       ResultSet rs = dat.getResultSet();
/* 786 */       if (rs.next()) {
/* 787 */         return true;
/*     */       }
/* 789 */       return false;
/*     */     }
/* 791 */     catch (Exception e) {
/* 792 */       e.printStackTrace();
/* 793 */       Utilidades.writeError("ContEstudioPrevioDAO:existeNumeroEstudio " + e.getMessage());
/*     */     } finally {
/*     */       
/* 796 */       dat.close();
/*     */     } 
/*     */     
/* 799 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ContEstudioPrevioDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */