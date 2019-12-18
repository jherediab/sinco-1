/*      */ package sinco.data;
/*      */ 
/*      */ import java.sql.ResultSet;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import sinco.business.ContContratoDTO;
/*      */ import sinco.business.Utilidades;
/*      */ import sinco.data.ContContratoDAO;
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
/*      */ public class ContContratoDAO
/*      */ {
/*      */   ResultSet rs;
/*   28 */   DBManager dat = new DBManager();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   39 */   public boolean getEstadoConexion() { return this.dat.getEstadoConexion(); }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close() {
/*      */     try {
/*   46 */       this.dat.close();
/*      */     }
/*   48 */     catch (Exception e) {
/*   49 */       Utilidades.writeError("ContContratoDAO:close ", e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ContContratoDTO next() {
/*      */     try {
/*   60 */       if (this.rs.next()) {
/*   61 */         return leerRegistro();
/*      */       }
/*      */     }
/*   64 */     catch (Exception e) {
/*   65 */       e.printStackTrace();
/*   66 */       Utilidades.writeError("ContContratoDAO:next ", e);
/*      */     } 
/*   68 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ContContratoDTO leerRegistro() {
/*      */     try {
/*   78 */       ContContratoDTO reg = new ContContratoDTO();
/*      */       
/*   80 */       reg.setConsecutivoContrato(this.rs.getInt("consecutivo_contrato"));
/*   81 */       reg.setValor(this.rs.getDouble("valor"));
/*   82 */       reg.setNumeroEstudio(this.rs.getInt("numero_estudio"));
/*   83 */       reg.setNombreSucursal(this.rs.getString("nombre_sede"));
/*   84 */       reg.setMunicipio(this.rs.getString("municipio_contrato"));
/*   85 */       reg.setDepartamento(this.rs.getString("departamento_contrato"));
/*   86 */       reg.setNombreMunicipio(this.rs.getString("nombre_ciudad"));
/*   87 */       reg.setNumeroContrato(this.rs.getString("numero_contrato"));
/*   88 */       reg.setCodigoSucursal(this.rs.getInt("codigo_sucursal"));
/*   89 */       reg.setnombreContratista(this.rs.getString("nombre_contratista"));
/*   90 */       reg.setnrocc(this.rs.getLong("nro_cc"));
/*   91 */       reg.setexpedida(this.rs.getString("expedida"));
/*   92 */       reg.setExpedidaMunicipio(this.rs.getString("expedidaMun"));
/*   93 */       reg.setvalorNumero(this.rs.getDouble("valor_numero"));
/*   94 */       reg.setdireccion(this.rs.getString("direccion"));
/*   95 */       reg.settelefonoCelular(this.rs.getString("telefono_celular"));
/*   96 */       reg.setemail(this.rs.getString("e_mail"));
/*   97 */       reg.setvalorLetra(this.rs.getString("valor_letra"));
/*   98 */       reg.setValorContratoLetra(this.rs.getString("valor_contrato_letra"));
/*   99 */       reg.setPlazoEjecucion(this.rs.getString("fecha_contrato_hasta"));
/*  100 */       reg.setcdpN(this.rs.getLong("cdp_n"));
/*  101 */       reg.setCodigoImputacion(this.rs.getString("codigo_imputacion"));
/*  102 */       reg.setvigencia(this.rs.getString("vigencia"));
/*  103 */       reg.setregimenTributario(this.rs.getString("regimen_tributario"));
/*  104 */       reg.setTipoContrato(this.rs.getString("tipo_contrato"));
/*  105 */       reg.setsupervisor(this.rs.getString("supervisor"));
/*  106 */       reg.setFechaInicio(this.rs.getString("fecha_inicio"));
/*  107 */       reg.setFechaFinal(this.rs.getString("fecha_final"));
/*  108 */       reg.setFechaContrato(this.rs.getString("fecha_contrato"));
/*  109 */       reg.setCodigoRp(this.rs.getString("codigo_rp"));
/*  110 */       reg.setTipoDocInterventor(this.rs.getString("tipo_doc_interventor"));
/*  111 */       reg.setDocInterventor(this.rs.getLong("doc_interventor"));
/*  112 */       reg.setNombreInterventor(this.rs.getString("nombreInterventor"));
/*  113 */       reg.setEstado(this.rs.getString("estado"));
/*  114 */       reg.setNombreEstado(this.rs.getString("nombre_estado"));
/*  115 */       reg.setUsuarioInsercion(this.rs.getString("usuario_insercion"));
/*  116 */       reg.setFechaInsercion(this.rs.getString("fecha_insercion"));
/*  117 */       reg.setUsuarioModificacion(this.rs.getString("usuario_modificacion"));
/*  118 */       reg.setFechaModificacion(this.rs.getString("fecha_modificacion"));
/*      */       try {
/*  120 */         reg.setTipoContrato(this.rs.getString("tipo_contrato"));
/*  121 */       } catch (Exception e) {}
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  126 */         reg.setCondicionesEspeciales(this.rs.getString("condiciones_especiales"));
/*  127 */       } catch (Exception e) {}
/*      */ 
/*      */ 
/*      */       
/*  131 */       return reg;
/*      */     }
/*  133 */     catch (Exception e) {
/*  134 */       e.printStackTrace();
/*  135 */       Utilidades.writeError("ContContratoDAO:leerRegistro ", e);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<ContContratoDTO> cargarTodos(int numeroEstudio, String numeroContrato, int codigoSucursal, String departamento, String municipio, String fechaInicioDesde, String fechaInicioHasta, String fechaFinalDesde, String fechaFinalHasta, String fechaContratoDesde, String fechaContratoHasta, String codigoServicio, String tipoRed, String imputaciones, String tipoEstudio, String estado) {
/*  164 */     Collection<ContContratoDTO> resultados = new ArrayList<ContContratoDTO>();
/*      */     try {
/*  166 */       String s = "select distinct on (t.consecutivo_contrato) t.consecutivo_contrato,t.valor,s.nombre_sede,c.codigo_ciudad,d.codigo_departamento,c.nombre_ciudad,t.numero_estudio,t.numero_contrato,t.codigo_sucursal,pre.nombre_entidad as nombre_contratista,t.nro_cc,pre.departamento_representante as expedida,pre.municipio_representante as expedidaMun,t.valor_numero,pre.direccion,pre.telefono as telefono_celular,pre.email as e_mail,t.valor_letra,t.valor_contrato_letra,t.fecha_contrato_hasta,t.cdp_n,cdp.codigo_imputacion,cdp.vigencia,t.regimen_tributario,t.tipo_contrato,t.supervisor,t.fecha_inicio,t.fecha_final,t.fecha_contrato,list(distinct(rp.numero_registro)) as codigo_rp,t.tipo_doc_interventor,t.doc_interventor,i.nombres||' '||i.apellidos as nombreInterventor,t.estado,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion, te.descripcion as nombre_estado,t.condiciones_especiales,t.municipio_contrato,t.departamento_contrato from cont_contrato t  left join cont_cdp_estudio cdp on (cdp.consecutivo_cdp = t.cdp_n) LEFT JOIN cont_estudio_previo est ON (est.numero_estudio=t.numero_estudio) left join cont_rp rp on (rp.consecutivo_contrato = t.consecutivo_contrato  and rp.numero_adicion=0) left join cont_interventor i on (i.tipo_documento=t.tipo_doc_interventor and i.numero_documento=t.doc_interventor) left join sis_multivalores te on (te.valor=t.estado and te.tabla='estado_contrato') LEFT JOIN red_sucursal s ON (s.codigo_sucursal=t.codigo_sucursal) LEFT JOIN red_prestador pre ON (pre.numero_identificacion=s.numero_identificacion) LEFT JOIN par_departamento d ON (d.codigo_departamento=s.departamento) LEFT JOIN par_ciudad c ON (c.codigo_ciudad=s.municipio and c.codigo_departamento=s.departamento) LEFT JOIN cont_estudio_previo_items it ON (it.tipo_item='imputacion' and it.numero_estudio=t.numero_estudio) LEFT JOIN cont_estudio_previo_servicios es ON (t.numero_estudio=es.numero_estudio) where 1=1 ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  222 */       if (numeroEstudio > 0) {
/*  223 */         s = s + " and t.numero_estudio=" + numeroEstudio;
/*      */       }
/*  225 */       if (tipoEstudio.length() > 0) {
/*  226 */         s = s + " and est.tipo_estudio='" + tipoEstudio + "'";
/*      */       }
/*  228 */       if (numeroContrato.length() > 0) {
/*  229 */         s = s + " and upper(t.numero_contrato) like upper('%" + numeroContrato + "%')";
/*      */       }
/*  231 */       if (departamento.length() > 0) {
/*  232 */         s = s + " and upper(s.departamento) like upper('%" + departamento + "%')";
/*      */       }
/*  234 */       if (municipio.length() > 0) {
/*  235 */         s = s + " and upper(s.municipio) like upper('%" + municipio + "%')";
/*      */       }
/*  237 */       if (codigoSucursal > 0) {
/*  238 */         s = s + " and t.codigo_sucursal=" + codigoSucursal;
/*      */       }
/*  240 */       if (fechaInicioDesde.length() > 0) {
/*  241 */         s = s + " and t.fecha_inicio>=" + Utilidades.formatoFecha2(fechaInicioDesde);
/*      */       }
/*  243 */       if (fechaInicioHasta.length() > 0) {
/*  244 */         s = s + " and t.fecha_inicio < " + Utilidades.formatoFecha2(fechaInicioHasta);
/*      */       }
/*  246 */       if (fechaContratoDesde.length() > 0) {
/*  247 */         s = s + " and t.fecha_contrato>=" + Utilidades.formatoFecha2(fechaContratoDesde);
/*      */       }
/*  249 */       if (fechaContratoHasta.length() > 0) {
/*  250 */         s = s + " and t.fecha_contrato < " + Utilidades.formatoFecha2(fechaContratoHasta);
/*      */       }
/*  252 */       if (fechaFinalDesde.length() > 0) {
/*  253 */         s = s + " and t.fecha_final>=" + Utilidades.formatoFecha2(fechaFinalDesde);
/*      */       }
/*  255 */       if (fechaFinalHasta.length() > 0) {
/*  256 */         s = s + " and t.fecha_final < " + Utilidades.formatoFecha2(fechaFinalHasta);
/*      */       }
/*  258 */       if (tipoRed.length() > 0) {
/*  259 */         s = s + " and pre.naturaleza_juridica='" + tipoRed + "'";
/*      */       }
/*  261 */       if (codigoServicio.length() > 0) {
/*  262 */         s = s + " and es.codigo_servicio=" + codigoServicio;
/*      */       }
/*  264 */       if (imputaciones.length() > 0) {
/*  265 */         s = s + " and it.descripcion_item='" + imputaciones + "'";
/*      */       }
/*  267 */       if (estado.length() > 0) {
/*  268 */         s = s + " and upper(t.estado) like upper('%" + estado + "%')";
/*      */       }
/*  270 */       s = s + " group by t.consecutivo_contrato," + "t.valor," + "s.nombre_sede," + "c.codigo_ciudad," + "d.codigo_departamento," + "c.nombre_ciudad," + "t.numero_estudio," + "t.numero_contrato," + "t.codigo_sucursal," + "pre.nombre_entidad," + "t.nro_cc," + "pre.departamento_representante," + "pre.municipio_representante," + "t.valor_numero," + "pre.direccion," + "pre.telefono," + "pre.email," + "t.valor_letra," + "t.valor_contrato_letra," + "t.fecha_contrato_hasta," + "t.cdp_n," + "cdp.codigo_imputacion," + "cdp.vigencia," + "t.regimen_tributario," + "t.tipo_contrato," + "t.supervisor," + "t.fecha_inicio," + "t.fecha_final," + "t.fecha_contrato," + "t.tipo_doc_interventor," + "t.doc_interventor," + "i.nombres,i.apellidos ," + "t.estado," + "t.usuario_insercion," + "t.fecha_insercion," + "t.usuario_modificacion," + "t.fecha_modificacion, " + "te.descripcion," + "t.condiciones_especiales," + "t.municipio_contrato," + "t.departamento_contrato" + " order by 1";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  312 */       boolean rtaDB = this.dat.parseSql(s);
/*  313 */       if (!rtaDB) {
/*  314 */         return resultados;
/*      */       }
/*  316 */       this.rs = this.dat.getResultSet();
/*  317 */       while (this.rs.next())
/*      */       {
/*  319 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  322 */     catch (Exception e) {
/*  323 */       e.printStackTrace();
/*  324 */       Utilidades.writeError("ContContratoDAO:cargarTodos ", e);
/*      */     } 
/*  326 */     return resultados;
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
/*      */   public Collection<ContContratoDTO> cargarTodosVigenteSucursal(int codigoSucursal, String fechaVerificacion) {
/*  338 */     Collection<ContContratoDTO> resultados = new ArrayList<ContContratoDTO>();
/*      */     try {
/*  340 */       String s = "SELECT t.consecutivo_contrato, t.numero_contrato, array_to_string(  ARRAY(   SELECT imp.codigo_imputacion||' '||UPPER(imp.descripcion)   FROM cont_estudio_previo_items i   LEFT JOIN cont_imputacion_presupuestal imp on (imp.codigo_imputacion=i.descripcion_item )   WHERE i.numero_estudio = t.numero_estudio and i.tipo_item='imputacion'),' , ' ) as imputaciones FROM cont_contrato t LEFT JOIN cont_estudio_previo e ON (t.numero_estudio = e.numero_estudio) WHERE e.tipo_estudio = 'S' ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  353 */       if (codigoSucursal > 0) {
/*  354 */         s = s + " AND t.codigo_sucursal=" + codigoSucursal;
/*      */       }
/*  356 */       if (fechaVerificacion.length() > 0) {
/*  357 */         s = s + " AND (" + Utilidades.formatoFecha2(fechaVerificacion) + " BETWEEN t.fecha_inicio AND t.fecha_final ";
/*  358 */         s = s + " OR " + Utilidades.formatoFecha2(fechaVerificacion) + " BETWEEN t.fecha_contrato AND t.fecha_final )";
/*      */       } 
/*  360 */       boolean rtaDB = this.dat.parseSql(s);
/*  361 */       if (!rtaDB) {
/*  362 */         return resultados;
/*      */       }
/*  364 */       this.rs = this.dat.getResultSet();
/*  365 */       while (this.rs.next()) {
/*  366 */         ContContratoDTO reg = new ContContratoDTO();
/*  367 */         reg.setConsecutivoContrato(this.rs.getInt("consecutivo_contrato"));
/*  368 */         reg.setNumeroContrato(this.rs.getString("numero_contrato"));
/*  369 */         reg.setImputaciones(this.rs.getString("imputaciones"));
/*  370 */         resultados.add(reg);
/*      */       }
/*      */     
/*  373 */     } catch (Exception e) {
/*  374 */       e.printStackTrace();
/*  375 */       Utilidades.writeError("ContContratoDAO:cargarTodosVigenteSucursal ", e);
/*      */     } 
/*  377 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ContContratoDTO cargarRegistro(int consecutivoContrato) {
/*      */     try {
/*  386 */       String s = "select  t.consecutivo_contrato,t.valor,t.numero_estudio,s.nombre_sede,c.codigo_ciudad,d.codigo_departamento,c.nombre_ciudad,t.numero_contrato,t.codigo_sucursal,r.nombre_entidad as nombre_contratista,t.nro_cc,r.departamento_representante as expedida,r.municipio_representante as expedidaMun,t.valor_numero,r.direccion,r.telefono as telefono_celular,r.email as e_mail,t.valor_letra,t.valor_contrato_letra,t.fecha_contrato_hasta,t.cdp_n,cdp.codigo_imputacion,cdp.vigencia,t.regimen_tributario,t.tipo_contrato,t.supervisor,t.fecha_inicio,t.fecha_final,t,fecha_contrato,list(distinct(rp.numero_registro)) as codigo_rp,t.tipo_doc_interventor,t.doc_interventor,i.nombres||' '||i.apellidos as nombreInterventor,t.estado,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion, t.fecha_liquidacion, t.valor_liquidacion, t.valor_cancelado, te.descripcion as nombre_estado, e.tipo_contrato as tipo_contrato,t.condiciones_especiales,t.municipio_contrato,t.departamento_contrato from cont_contrato t  left join cont_cdp_estudio cdp on (cdp.consecutivo_cdp = t.cdp_n) left join red_prestador r on (r.numero_identificacion = t.nro_cc) left join cont_rp rp on (rp.consecutivo_contrato = t.consecutivo_contrato  and rp.numero_adicion=0) left join cont_interventor i on (i.tipo_documento=t.tipo_doc_interventor and i.numero_documento=t.doc_interventor)  left join sis_multivalores te on (te.valor=t.estado and te.tabla='estado_contrato')  left join cont_estudio_previo e on (e.numero_estudio=t.numero_estudio), red_sucursal s, par_ciudad c, par_departamento d where t.codigo_sucursal=s.codigo_sucursal and s.departamento=d.codigo_departamento and s.municipio=c.codigo_ciudad and c.codigo_departamento=d.codigo_departamento and t.consecutivo_contrato=" + consecutivoContrato + " group by   " + "t.consecutivo_contrato," + "t.valor," + "t.numero_estudio," + "s.nombre_sede," + "c.codigo_ciudad," + "d.codigo_departamento," + "c.nombre_ciudad," + "t.numero_contrato," + "t.codigo_sucursal," + "r.nombre_entidad," + "t.nro_cc," + "r.departamento_representante," + "r.municipio_representante," + "t.valor_numero," + "r.direccion," + "r.telefono," + "r.email," + "t.valor_letra," + "t.valor_contrato_letra," + "t.fecha_contrato_hasta," + "t.cdp_n," + "cdp.codigo_imputacion," + "cdp.vigencia," + "t.regimen_tributario," + "t.tipo_contrato," + "t.supervisor," + "t.fecha_inicio," + "t.fecha_final," + "t,fecha_contrato," + "t.tipo_doc_interventor," + "t.doc_interventor," + "i.nombres,i.apellidos," + "t.estado," + "t.usuario_insercion," + "t.fecha_insercion," + "t.usuario_modificacion," + "t.fecha_modificacion, " + "t.fecha_liquidacion, " + "t.valor_liquidacion, " + "t.valor_cancelado, " + "te.descripcion," + "e.tipo_contrato," + "t.condiciones_especiales," + "t.municipio_contrato," + "t.departamento_contrato" + "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  493 */       boolean rtaDB = this.dat.parseSql(s);
/*  494 */       if (!rtaDB) {
/*  495 */         return null;
/*      */       }
/*  497 */       this.rs = this.dat.getResultSet();
/*  498 */       if (this.rs.next()) {
/*  499 */         ContContratoDTO reg = leerRegistro();
/*      */         try {
/*  501 */           reg.setFechaLiquidacion(this.rs.getString("fecha_liquidacion"));
/*  502 */           reg.setValorLiquidacion(this.rs.getDouble("valor_liquidacion"));
/*  503 */           reg.setValorCancelado(this.rs.getDouble("valor_cancelado"));
/*  504 */         } catch (Exception e) {}
/*  505 */         return reg;
/*      */       }
/*      */     
/*  508 */     } catch (Exception e) {
/*  509 */       e.printStackTrace();
/*  510 */       Utilidades.writeError("ContContratoDAO:cargarContContrato", e);
/*      */     } 
/*  512 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ContContratoDTO cargarRegistro(String numeroContrato) {
/*      */     try {
/*  522 */       String s = "select  t.consecutivo_contrato,t.valor,t.numero_estudio,s.nombre_sede,c.codigo_ciudad,d.codigo_departamento,c.nombre_ciudad,t.numero_contrato,t.codigo_sucursal,r.nombre_entidad as nombre_contratista,t.nro_cc,r.departamento_representante as expedida,r.municipio_representante as expedidaMun,t.valor_numero,r.direccion,r.telefono as telefono_celular,r.email as e_mail,t.valor_letra,t.valor_contrato_letra,t.fecha_contrato_hasta,t.cdp_n,cdp.codigo_imputacion,cdp.vigencia,t.regimen_tributario,t.tipo_contrato,t.supervisor,t.fecha_inicio,t.fecha_final,t,fecha_contrato,list(distinct(rp.numero_registro)) as codigo_rp,t.tipo_doc_interventor,t.doc_interventor,i.nombres||' '||i.apellidos as nombreInterventor,t.estado,t.condiciones_especiales,t.municipio_contrato,t.departamento_contrato,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion, te.descripcion as nombre_estado  from cont_contrato t  left join cont_cdp_estudio cdp on (cdp.consecutivo_cdp = t.cdp_n) left join red_prestador r on (r.numero_identificacion = t.nro_cc) left join cont_rp rp on (rp.consecutivo_contrato = t.consecutivo_contrato  and rp.numero_adicion=0) left join cont_interventor i on (i.tipo_documento=t.tipo_doc_interventor and i.numero_documento=t.doc_interventor)  left join sis_multivalores te on (te.valor=t.estado and te.tabla='estado_contrato'), red_sucursal s, par_ciudad c, par_departamento d where t.codigo_sucursal=s.codigo_sucursal and s.departamento=d.codigo_departamento and s.municipio=c.codigo_ciudad and c.codigo_departamento=d.codigo_departamento and t.numero_contrato='" + numeroContrato + "'" + " group by   " + "t.consecutivo_contrato," + "t.valor," + "t.numero_estudio," + "s.nombre_sede," + "c.codigo_ciudad," + "d.codigo_departamento," + "c.nombre_ciudad," + "t.numero_contrato," + "t.codigo_sucursal," + "r.nombre_entidad," + "t.nro_cc," + "r.departamento_representante," + "r.municipio_representante," + "t.valor_numero," + "r.direccion," + "r.telefono," + "r.email," + "t.valor_letra," + "t.valor_contrato_letra," + "t.fecha_contrato_hasta," + "t.cdp_n," + "cdp.codigo_imputacion," + "cdp.vigencia," + "t.regimen_tributario," + "t.tipo_contrato," + "t.supervisor," + "t.fecha_inicio," + "t.fecha_final," + "t,fecha_contrato," + "t.tipo_doc_interventor," + "t.doc_interventor," + "i.nombres, i.apellidos," + "t.estado," + "t.condiciones_especiales," + "t.municipio_contrato," + "t.departamento_contrato," + "t.usuario_insercion," + "t.fecha_insercion," + "t.usuario_modificacion," + "t.fecha_modificacion, " + "te.descripcion" + "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  620 */       boolean rtaDB = this.dat.parseSql(s);
/*  621 */       if (!rtaDB) {
/*  622 */         return null;
/*      */       }
/*  624 */       this.rs = this.dat.getResultSet();
/*  625 */       if (this.rs.next()) {
/*  626 */         return leerRegistro();
/*      */       }
/*      */     }
/*  629 */     catch (Exception e) {
/*  630 */       e.printStackTrace();
/*  631 */       Utilidades.writeError("ContContratoDAO:cargarContContrato", e);
/*      */     } 
/*  633 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<ContContratoDTO> cargarTodosPorPrestador(String numeroContrato, String prestador, int codigoSucursal) {
/*  641 */     ArrayList<ContContratoDTO> resultados = new ArrayList<ContContratoDTO>();
/*      */     try {
/*  643 */       String s = "select  t.consecutivo_contrato,t.valor,t.numero_estudio,s.nombre_sede,c.codigo_ciudad,d.codigo_departamento,c.nombre_ciudad,t.numero_contrato,t.codigo_sucursal,r.nombre_entidad as nombre_contratista,t.nro_cc,r.departamento_representante as expedida,r.municipio_representante as expedidaMun,t.valor_numero,r.direccion,r.telefono as telefono_celular,r.email as e_mail,t.valor_letra,t.fecha_contrato_hasta,t.cdp_n,cdp.codigo_imputacion,cdp.vigencia,t.regimen_tributario,t.supervisor,t.fecha_inicio,t.fecha_final,t.fecha_contrato,list(distinct(rp.numero_registro)) as codigo_rp,t.tipo_doc_interventor,t.doc_interventor,i.nombres||' '||i.apellidos as nombreInterventor,t.estado,t.condiciones_especiales,t.municipio_contrato,t.departamento_contrato,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion, te.descripcion as nombre_estado  from cont_contrato t  left join cont_cdp_estudio cdp on (cdp.consecutivo_cdp = t.cdp_n) left join red_prestador r on (r.numero_identificacion = t.nro_cc) left join cont_rp rp on (rp.consecutivo_contrato = t.consecutivo_contrato  and rp.numero_adicion=0) left join cont_interventor i on (i.tipo_documento=t.tipo_doc_interventor and i.numero_documento=t.doc_interventor)  left join sis_multivalores te on (te.valor=t.estado and te.tabla='estado_contrato'), red_sucursal s, par_ciudad c, par_departamento d where t.codigo_sucursal=s.codigo_sucursal and s.departamento=d.codigo_departamento and s.municipio=c.codigo_ciudad and c.codigo_departamento=d.codigo_departamento and t.numero_contrato like '%" + numeroContrato + "%'";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  696 */       if (prestador.length() > 0) {
/*  697 */         s = s + " and s.numero_identificacion = (select numero_identificacion from red_sucursal where codigo_habilitacion='" + prestador + "' limit 1)";
/*  698 */       } else if (codigoSucursal > 0) {
/*  699 */         s = s + " and s.numero_identificacion = (select numero_identificacion from red_sucursal where codigo_sucursal=" + codigoSucursal + ")";
/*      */       } 
/*  701 */       s = s + " and t.codigo_sucursal = s.codigo_sucursal" + "" + " group by   " + "t.consecutivo_contrato," + "t.valor," + "t.numero_estudio," + "s.nombre_sede," + "c.codigo_ciudad," + "d.codigo_departamento," + "c.nombre_ciudad," + "t.numero_contrato," + "t.codigo_sucursal," + "r.nombre_entidad as nombre_contratista," + "t.nro_cc," + "r.departamento_representante," + "r.municipio_representante," + "t.valor_numero," + "r.direccion," + "r.telefono," + "r.email," + "t.valor_letra," + "t.fecha_contrato_hasta," + "t.cdp_n," + "cdp.codigo_imputacion," + "cdp.vigencia," + "t.regimen_tributario," + "t.supervisor," + "t.fecha_inicio," + "t.fecha_final," + "t,fecha_contrato," + "t.tipo_doc_interventor," + "t.doc_interventor," + "i.nombres, i.apellidos  ," + "t.estado," + "t.condiciones_especiales," + "t.municipio_contrato," + "t.departamento_contrato," + "t.usuario_insercion," + "t.fecha_insercion," + "t.usuario_modificacion," + "t.fecha_modificacion, " + "te.descripcion  ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  743 */       boolean rtaDB = this.dat.parseSql(s);
/*  744 */       if (!rtaDB) {
/*  745 */         return resultados;
/*      */       }
/*  747 */       this.rs = this.dat.getResultSet();
/*  748 */       while (this.rs.next()) {
/*  749 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  752 */     catch (Exception e) {
/*  753 */       e.printStackTrace();
/*  754 */       Utilidades.writeError("ContContratoDAO:cargarRegistroPorContratoPrestador", e);
/*      */     } 
/*  756 */     return resultados;
/*      */   }
/*      */ 
/*      */   
/*      */   public Collection<ContContratoDTO> cargarTodosAVencerse(int dias) {
/*  761 */     ArrayList<ContContratoDTO> resultados = new ArrayList<ContContratoDTO>();
/*      */     try {
/*  763 */       String s = "select  t.consecutivo_contrato,t.valor,t.numero_estudio,s.nombre_sede,c.codigo_ciudad,d.codigo_departamento,c.nombre_ciudad,t.numero_contrato,t.codigo_sucursal,r.nombre_entidad as nombre_contratista,t.nro_cc,r.departamento_representante as expedida,r.municipio_representante as expedidaMun,t.valor_numero,r.direccion,r.telefono as telefono_celular,r.email as e_mail,t.valor_letra,t.fecha_contrato_hasta,t.cdp_n,cdp.codigo_imputacion,cdp.vigencia,t.regimen_tributario,t.supervisor,t.fecha_inicio,t.fecha_final,t,fecha_contrato,list(distinct(rp.numero_registro)) as codigo_rp,t.tipo_doc_interventor,t.doc_interventor,i.nombres||' '||i.apellidos as nombreInterventor,t.estado,t.condiciones_especiales,t.municipio_contrato,t.departamento_contrato,t.usuario_insercion,t.fecha_insercion,t.usuario_modificacion,t.fecha_modificacion, te.descripcion as nombre_estado  from cont_contrato t  left join cont_cdp_estudio cdp on (cdp.consecutivo_cdp = t.cdp_n) left join red_prestador r on (r.numero_identificacion = t.nro_cc) left join cont_rp rp on (rp.consecutivo_contrato = t.consecutivo_contrato  and rp.numero_adicion=0) left join cont_interventor i on (i.tipo_documento=t.tipo_doc_interventor and i.numero_documento=t.doc_interventor)  left join sis_multivalores te on (te.valor=t.estado and te.tabla='estado_contrato'), red_sucursal s, par_ciudad c, par_departamento d where t.codigo_sucursal=s.codigo_sucursal and s.departamento=d.codigo_departamento and s.municipio=c.codigo_ciudad and c.codigo_departamento=d.codigo_departamento";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  815 */       if (dias > 0) {
/*  816 */         s = s + " and now() BETWEEN t.fecha_final+'-" + dias + " day' AND t.fecha_final ";
/*      */       }
/*  818 */       s = s + " and t.codigo_sucursal = s.codigo_sucursal " + " GROUP BY  " + "t.consecutivo_contrato," + "t.valor," + "t.numero_estudio," + "s.nombre_sede," + "c.codigo_ciudad," + "d.codigo_departamento," + "c.nombre_ciudad," + "t.numero_contrato," + "t.codigo_sucursal," + "r.nombre_entidad as nombre_contratista," + "t.nro_cc," + "r.departamento_representante," + "r.municipio_representante," + "t.valor_numero," + "r.direccion," + "r.telefono as telefono_celular," + "r.email as e_mail," + "t.valor_letra," + "t.fecha_contrato_hasta," + "t.cdp_n," + "cdp.codigo_imputacion," + "cdp.vigencia," + "t.regimen_tributario," + "t.supervisor," + "t.fecha_inicio," + "t.fecha_final," + "t,fecha_contrato," + "t.tipo_doc_interventor," + "t.doc_interventor," + "i.nombres, i.apellidos  ," + "t.estado," + "t.condiciones_especiales," + "t.municipio_contrato," + "t.departamento_contrato," + "t.usuario_insercion," + "t.fecha_insercion," + "t.usuario_modificacion," + "t.fecha_modificacion, " + "te.descripcion  ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  859 */       boolean rtaDB = this.dat.parseSql(s);
/*  860 */       if (!rtaDB) {
/*  861 */         return resultados;
/*      */       }
/*  863 */       this.rs = this.dat.getResultSet();
/*  864 */       while (this.rs.next()) {
/*  865 */         resultados.add(leerRegistro());
/*      */       }
/*      */     }
/*  868 */     catch (Exception e) {
/*  869 */       e.printStackTrace();
/*  870 */       Utilidades.writeError("ContContratoDAO:cargarTodosAVencerse ", e);
/*      */     } 
/*  872 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<ContContratoDTO> cargarActivosPrestador(int codigoSucursal) {
/*  878 */     ArrayList<ContContratoDTO> resultados = new ArrayList<ContContratoDTO>();
/*      */     try {
/*  880 */       String s = "SELECT cont.consecutivo_contrato, cont.numero_contrato, est.forma_contrato FROM  cont_contrato cont, cont_estudio_previo est WHERE est.numero_estudio = cont.numero_estudio AND (now() BETWEEN fecha_inicio AND fecha_final OR now() BETWEEN fecha_contrato AND fecha_final) AND codigo_sucursal IN  (  SELECT  suc2.codigo_sucursal  FROM  red_sucursal suc,  red_prestador pre,  red_sucursal suc2  WHERE  pre.numero_identificacion = suc2.numero_identificacion  AND suc2.numero_identificacion = suc.numero_identificacion  AND suc.codigo_sucursal = " + codigoSucursal + ") " + "AND estado='A' " + "ORDER BY fecha_final desc ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  905 */       boolean rtaDB = this.dat.parseSql(s);
/*  906 */       if (!rtaDB) {
/*  907 */         return resultados;
/*      */       }
/*  909 */       this.rs = this.dat.getResultSet();
/*  910 */       while (this.rs.next()) {
/*  911 */         ContContratoDTO reg = new ContContratoDTO();
/*  912 */         reg.setConsecutivoContrato(this.rs.getInt("consecutivo_contrato"));
/*  913 */         reg.setNumeroContrato(this.rs.getString("numero_contrato"));
/*  914 */         reg.setFormaContrato(this.rs.getString("forma_contrato"));
/*  915 */         resultados.add(reg);
/*      */       }
/*      */     
/*  918 */     } catch (Exception e) {
/*  919 */       e.printStackTrace();
/*  920 */       Utilidades.writeError("ContContratoDAO:cargarRegistroPorContratoPrestador", e);
/*      */     } 
/*  922 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<ContContratoDTO> cargarEstadoProceso(String fechaDesde, String fechaHasta) {
/*  930 */     ArrayList<ContContratoDTO> resultados = new ArrayList<ContContratoDTO>();
/*      */     try {
/*  932 */       String s = "SELECT * FROM ( SELECT cont.consecutivo_contrato, cont.numero_contrato, cont.numero_estudio, est.tipo_estudio, pre.nombre_entidad, CASE WHEN list(distinct(cdp.codigo_certificado))='' THEN 'NO REGISTRA CDP ' ELSE list(distinct(cdp.codigo_certificado)) END as cdp, CASE WHEN coalesce(cont.doc_interventor,'')='' THEN 'NO REGISTRA INTERVENTOR ' ELSE i.nombres||' '||i.apellidos END as interventor, CASE WHEN list(distinct(rp.numero_registro))='' THEN 'NO REGISTRA RP' ELSE list(distinct(rp.numero_registro)) END rp, CASE  WHEN list(pol.tipo_poliza)='' THEN '' ELSE 'REGISTRA '||list(distinct(pol.tipo_poliza)) END ||   CASE WHEN list(distinct(CASE WHEN (itEstPol.descripcion_item is not null AND pol.tipo_poliza is null) THEN sisPol.valor ELSE '' END))='' THEN ''  ELSE  ' - NO REGISTRA '||list(distinct(CASE WHEN (itEstPol.descripcion_item is not null AND pol.tipo_poliza is null) THEN sisPol.valor ELSE '' END)) END as polizas, CASE WHEN list(distinct(sisImp.descripcion))='' THEN 'NO REGISTRA IMPUESTOS ' ELSE list(distinct(sisImp.descripcion)) END as impuestos, CASE WHEN act.fecha_acta IS NULL AND est.tipo_plazo='textoActa' THEN 'NO REGISTRA ACTA INICIO' ELSE to_char(act.fecha_acta,'DD/MM/YYYY') END as acta_inicio FROM cont_contrato cont LEFT JOIN cont_estudio_previo est ON (cont.numero_estudio = est.numero_estudio) LEFT JOIN red_sucursal suc ON (cont.codigo_sucursal = suc.codigo_sucursal) LEFT JOIN red_prestador pre ON (suc.numero_identificacion = pre.numero_identificacion) LEFT JOIN cont_cdp_contrato cdp ON (cont.consecutivo_contrato = cdp.consecutivo_contrato) LEFT JOIN cont_interventor i ON (cont.tipo_doc_interventor = i.tipo_documento AND cont.doc_interventor=i.numero_documento) LEFT JOIN cont_rp rp ON (cont.consecutivo_contrato = rp.consecutivo_contrato AND rp.numero_adicion=0) LEFT JOIN cont_estudio_previo_items itEstPol ON (est.numero_estudio = itEstPol.numero_estudio AND itEstPol.tipo_item='poliza') LEFT JOIN sis_multivalores sisPol ON (upper(itEstPol.descripcion_item) like upper('%'||sisPol.valor||'%') AND sisPol.tabla='tipo_poliza') LEFT JOIN cont_poliza pol ON (cont.consecutivo_contrato = pol.consecutivo_contrato AND pol.tipo_poliza=sisPol.valor) LEFT JOIN cont_impuesto imp ON (cont.consecutivo_contrato = imp.consecutivo_contrato) LEFT JOIN sis_multivalores sisImp ON (imp.descripcion = sisImp.valor AND sisImp.tabla='tipo_impuesto') LEFT JOIN cont_acta_inicio act ON (cont.consecutivo_contrato = act.consecutivo_contrato) ";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  961 */       s = s + "WHERE 1=1 ";
/*  962 */       if (fechaDesde.length() > 0) {
/*  963 */         s = s + " AND ( cont.fecha_inicio >= to_date('" + fechaDesde + "','DD/MM/YYYY')" + " OR cont.fecha_contrato >= to_date('" + fechaDesde + "','DD/MM/YYYY') )";
/*      */       }
/*      */       
/*  966 */       if (fechaHasta.length() > 0) {
/*  967 */         s = s + " AND cont.fecha_final <= to_date('" + fechaHasta + "','DD/MM/YYYY')";
/*      */       }
/*  969 */       s = s + " GROUP BY " + "cont.consecutivo_contrato,cont.numero_contrato,cont.numero_estudio,est.tipo_estudio,cont.doc_interventor," + "i.nombres||' '||i.apellidos,act.fecha_acta,est.tipo_plazo, pre.nombre_entidad" + ") AS FOO " + "WHERE " + "cdp like '%NO REGISTRA%' " + "OR interventor like '%NO REGISTRA%' " + "OR polizas like '%NO REGISTRA%' " + "OR rp like '%NO REGISTRA%' " + "OR impuestos like '%NO REGISTRA%' " + "OR acta_inicio like '%NO REGISTRA%'";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  980 */       boolean rtaDB = this.dat.parseSql(s);
/*  981 */       if (!rtaDB) {
/*  982 */         return resultados;
/*      */       }
/*  984 */       this.rs = this.dat.getResultSet();
/*  985 */       while (this.rs.next()) {
/*  986 */         ContContratoDTO reg = new ContContratoDTO();
/*  987 */         reg.setConsecutivoContrato(this.rs.getInt("consecutivo_contrato"));
/*  988 */         reg.setNumeroContrato(this.rs.getString("numero_contrato"));
/*  989 */         reg.setNumeroEstudio(this.rs.getInt("numero_estudio"));
/*  990 */         reg.setNombreSucursal(this.rs.getString("nombre_entidad"));
/*  991 */         reg.setCodigoCertificado(this.rs.getString("cdp"));
/*  992 */         reg.setNombreInterventor(this.rs.getString("interventor"));
/*  993 */         reg.setCodigoRp(this.rs.getString("rp"));
/*  994 */         reg.setPolizas(this.rs.getString("polizas"));
/*  995 */         reg.setImpuestos(this.rs.getString("impuestos"));
/*  996 */         reg.setActaInicio(this.rs.getString("acta_inicio"));
/*  997 */         resultados.add(reg);
/*      */       }
/*      */     
/* 1000 */     } catch (Exception e) {
/* 1001 */       e.printStackTrace();
/* 1002 */       Utilidades.writeError("ContContratoDAO:cargarEstadoProceso", e);
/*      */     } 
/* 1004 */     return resultados;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int siguienteRegistro() {
/* 1013 */     int inumero = 1;
/* 1014 */     String s = "select max(consecutivo_contrato) from cont_contrato ";
/*      */     
/*      */     try {
/* 1017 */       boolean rta = this.dat.parseSql(s);
/* 1018 */       if (!rta) return 0; 
/* 1019 */       this.rs = this.dat.getResultSet();
/* 1020 */       if (this.rs.next()) {
/* 1021 */         s = this.rs.getString(1);
/* 1022 */         if (!this.rs.wasNull()) {
/* 1023 */           inumero = Integer.parseInt(s) + 1;
/*      */         }
/*      */       } 
/* 1026 */       return inumero;
/*      */     }
/* 1028 */     catch (Exception e) {
/* 1029 */       e.printStackTrace();
/* 1030 */       Utilidades.writeError("ContContratoDAO:siguienteRegistro ", e);
/*      */       
/* 1032 */       return 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean eliminarRegistro(int consecutivoContrato) {
/*      */     try {
/* 1042 */       String s = "delete from cont_contrato where  consecutivo_contrato=" + consecutivoContrato + "";
/*      */ 
/*      */ 
/*      */       
/* 1046 */       return this.dat.executeUpdate(s);
/*      */     
/*      */     }
/* 1049 */     catch (Exception e) {
/* 1050 */       e.printStackTrace();
/* 1051 */       Utilidades.writeError("ContContratoDAO:eliminarRegistro ", e);
/*      */       
/* 1053 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean eliminarLiquidacion(int consecutivoContrato, String usuarioModificacion) {
/*      */     try {
/* 1063 */       String s = "update cont_contrato set  fecha_liquidacion=null, valor_liquidacion=null, valor_cancelado=null, estado='A', usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " consecutivo_contrato=" + consecutivoContrato + "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1073 */       return this.dat.executeUpdate(s);
/*      */     
/*      */     }
/* 1076 */     catch (Exception e) {
/* 1077 */       e.printStackTrace();
/* 1078 */       Utilidades.writeError("ContContratoDAO:eliminarLiquidacion ", e);
/*      */       
/* 1080 */       return false;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int crearRegistro(int numeroEstudio, String numeroContrato, int codigoSucursal, long nrocc, double valorNumero, String valorLetra, String valorContratoLetra, String fechaContratoHasta, long cdpN, String regimenTributario, String tipoContrato, String fechaContrato, String tipoDocInterventor, long docInterventor, double valor, String estado, String condicionesEspeciales, String municipioContrato, String departamentoContrato, String usuarioInsercion) {
/* 1118 */     int elSiguiente = siguienteRegistro();
/* 1119 */     if (elSiguiente == 0) {
/* 1120 */       return elSiguiente;
/*      */     }
/*      */     
/*      */     try {
/* 1124 */       String s = "insert into cont_contrato(consecutivo_contrato,numero_estudio,numero_contrato,codigo_sucursal,nro_cc,valor_numero,valor_letra,valor_contrato_letra,fecha_contrato_hasta,cdp_n,regimen_tributario,tipo_contrato,fecha_contrato,tipo_doc_interventor,doc_interventor,valor,saldo_contrato,estado,condiciones_especiales,municipio_contrato,departamento_contrato,usuario_insercion,fecha_insercion) values (" + elSiguiente + "," + "" + numeroEstudio + "," + "'" + numeroContrato + "'," + "" + codigoSucursal + "," + "" + nrocc + "," + "" + valorNumero + "," + "'" + valorLetra + "'," + "'" + valorContratoLetra + "'," + "" + ((fechaContratoHasta.length() > 0) ? Utilidades.formatoFecha2(fechaContratoHasta) : "null") + "," + "" + cdpN + "," + "'" + regimenTributario + "'," + "'" + tipoContrato + "'," + "" + ((fechaContrato.length() > 0) ? Utilidades.formatoFecha2(fechaContrato) : "null") + "," + "'" + tipoDocInterventor + "'," + "" + docInterventor + "," + "" + valor + "," + "" + valor + "," + "'" + estado + "'," + "'" + condicionesEspeciales + "'," + "'" + municipioContrato + "'," + "'" + departamentoContrato + "'," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1177 */       this.dat.executeUpdate(s);
/* 1178 */       return elSiguiente;
/*      */     }
/* 1180 */     catch (Exception e) {
/* 1181 */       e.printStackTrace();
/* 1182 */       Utilidades.writeError("%ContContratoDAO:crearRegistro ", e);
/*      */       
/* 1184 */       return elSiguiente;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean modificarRegistro(int consecutivoContrato, int numeroEstudio, int codigoSucursal, long nrocc, double valorNumero, String valorLetra, String valorContratoLetra, String fechaContratoHasta, long cdpN, String regimenTributario, String tipoContrato, String fechaContrato, String tipoDocInterventor, long docInterventor, double valor, String estado, String condicionesEspeciales, String municipioContrato, String departamentoContrato, String usuarioModificacion) {
/*      */     try {
/* 1223 */       String s = "update cont_contrato set  numero_estudio=" + numeroEstudio + "," + " codigo_sucursal=" + codigoSucursal + "," + " nro_cc=" + nrocc + "," + " valor_numero=" + valorNumero + "," + " valor_letra='" + valorLetra + "'," + " valor_contrato_letra='" + valorContratoLetra + "'," + " fecha_contrato_hasta=" + Utilidades.formatoFecha2(fechaContratoHasta) + "," + " cdp_n='" + cdpN + "'," + " regimen_tributario='" + regimenTributario + "'," + " tipo_contrato='" + tipoContrato + "'," + " fecha_contrato=" + Utilidades.formatoFecha2(fechaContrato) + "," + " tipo_doc_interventor='" + tipoDocInterventor + "'," + " doc_interventor=" + docInterventor + "," + " valor=" + valor + "," + " estado='" + estado + "'," + " condiciones_especiales='" + condicionesEspeciales + "'," + " municipio_contrato='" + municipioContrato + "'," + " departamento_contrato='" + departamentoContrato + "'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " consecutivo_contrato=" + consecutivoContrato + "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1250 */       return this.dat.executeUpdate(s);
/*      */     
/*      */     }
/* 1253 */     catch (Exception e) {
/* 1254 */       e.printStackTrace();
/* 1255 */       Utilidades.writeError("ContContratoDAO:modificarRegistro ", e);
/*      */       
/* 1257 */       return false;
/*      */     } 
/*      */   }
/*      */   public boolean existeContrato(String numeroContrato) {
/*      */     try {
/* 1262 */       String s = "select consecutivo_contrato  from cont_contrato  where numero_contrato='" + numeroContrato + "'";
/*      */ 
/*      */       
/* 1265 */       this.dat.parseSql(s);
/* 1266 */       this.rs = this.dat.getResultSet();
/* 1267 */       if (this.rs.next()) {
/* 1268 */         return true;
/*      */       }
/* 1270 */       return false;
/*      */     }
/* 1272 */     catch (Exception e) {
/* 1273 */       e.printStackTrace();
/* 1274 */       Utilidades.writeError("ContContratoDAO:existeContrato " + e.getMessage());
/*      */       
/* 1276 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ContContratoDTO cargarResumenContrato(int consecutivoContrato) {
/*      */     try {
/* 1285 */       String s = "select  ep.numero_estudio, c.numero_contrato, list(distinct(rp.numero_registro)) as codigo_rp,list(distinct(cdp.codigo_certificado)) as codigo_certificado, p.nombre_entidad, pc.nombre_ciudad, case when tca.descripcion is null then tcs.descripcion else tca.descripcion end as tipo_contrato, case when fca.descripcion is null then fcs.descripcion else fca.descripcion end as forma_contratacion, c.fecha_inicio, c.fecha_final, c.valor, ep.objeto_contratar, fpc.descripcion||' '||coalesce(ep.forma_pago_descripcion,'') as forma_pago, i.apellidos, i.nombres, ep.tarifa_descripcion, array_to_string(ARRAY(select clausulas from cont_adicion_contrato ad where ad.consecutivo_contrato = c.consecutivo_contrato),'Modificatorio: ') as modificatorios from cont_contrato c  left join cont_rp rp on (rp.consecutivo_contrato = c.consecutivo_contrato )left join cont_interventor i on (i.tipo_documento = c.tipo_doc_interventor and i.numero_documento=c.doc_interventor) left join cont_cdp_contrato cdp on (cdp.consecutivo_contrato=c.consecutivo_contrato), red_prestador p, red_sucursal s, par_ciudad pc, cont_estudio_previo ep left join sis_multivalores tcs on (tcs.valor=ep.tipo_contrato and tcs.tabla='tipo_contrato_salud') left join sis_multivalores fcs on (fcs.valor=ep.forma_contrato and fcs.tabla='forma_contrato_salud') left join sis_multivalores tca on (tca.valor=ep.tipo_contrato and tca.tabla='tipo_contrato_admin') left join sis_multivalores fca on (fca.valor=ep.forma_contrato and fca.tabla='forma_contrato_admin') left join sis_multivalores fpc on (fpc.valor=ep.forma_pago and fpc.tabla='forma_pago_contrato') where s.codigo_sucursal=c.codigo_sucursal and p.numero_identificacion=s.numero_identificacion and s.municipio=pc.codigo_ciudad and s.departamento=pc.codigo_departamento and ep.numero_estudio = c.numero_estudio and (rp.numero_adicion=0 or rp.numero_adicion is null) and c.consecutivo_contrato=" + consecutivoContrato + " group by ep.numero_estudio, c.numero_contrato,  p.nombre_entidad, pc.nombre_ciudad, " + "tca.descripcion, c.fecha_inicio, c.fecha_final, c.valor, tcs.descripcion,fcs.descripcion," + "ep.objeto_contratar, fca.descripcion, fpc.descripcion, ep.forma_pago_descripcion, " + "i.apellidos, i.nombres, ep.tarifa_descripcion, modificatorios";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1327 */       boolean rtaDB = this.dat.parseSql(s);
/* 1328 */       if (!rtaDB) {
/* 1329 */         return null;
/*      */       }
/* 1331 */       this.rs = this.dat.getResultSet();
/* 1332 */       if (this.rs.next()) {
/* 1333 */         ContContratoDTO reg = new ContContratoDTO();
/* 1334 */         reg.setNumeroEstudio(this.rs.getInt("numero_estudio"));
/* 1335 */         reg.setNumeroContrato(this.rs.getString("numero_contrato"));
/* 1336 */         reg.setCodigoRp(this.rs.getString("codigo_rp"));
/* 1337 */         reg.setnombreContratista(this.rs.getString("nombre_contratista"));
/* 1338 */         reg.setnrocc(this.rs.getInt("nro_cc"));
/* 1339 */         reg.setexpedida(this.rs.getString("expedida"));
/* 1340 */         reg.setvalorNumero(this.rs.getDouble("valor_numero"));
/* 1341 */         reg.setdireccion(this.rs.getString("direccion"));
/* 1342 */         reg.settelefonoCelular(this.rs.getString("telefono_celular"));
/* 1343 */         reg.setemail(this.rs.getString("e_mail"));
/* 1344 */         reg.setvalorLetra(this.rs.getString("valor_letra"));
/* 1345 */         reg.setplazoEjecucion(this.rs.getString("plazo_ejecucion"));
/* 1346 */         reg.setcdpN(this.rs.getInt("cdp_n"));
/* 1347 */         reg.setvigencia(this.rs.getString("vigencia"));
/* 1348 */         reg.setregimenTributario(this.rs.getString("regimen_tributario"));
/* 1349 */         reg.setsupervisor(this.rs.getString("supervisor"));
/* 1350 */         reg.setCodigoCertificado(this.rs.getString("codigo_certificado"));
/* 1351 */         reg.setNombreSucursal(this.rs.getString("nombre_entidad"));
/* 1352 */         reg.setNombreMunicipio(this.rs.getString("nombre_ciudad"));
/* 1353 */         reg.setTipoContrato(this.rs.getString("tipo_contrato"));
/* 1354 */         reg.setFormaContrato(this.rs.getString("forma_contratacion"));
/* 1355 */         reg.setFechaInicio(this.rs.getString("fecha_inicio"));
/* 1356 */         reg.setFechaFinal(this.rs.getString("fecha_final"));
/* 1357 */         reg.setValor(this.rs.getDouble("valor"));
/* 1358 */         reg.setObjetoContratar(this.rs.getString("objeto_contratar"));
/* 1359 */         reg.setFormaPago(this.rs.getString("forma_pago"));
/* 1360 */         reg.setApellidosInterventor(this.rs.getString("apellidos"));
/* 1361 */         reg.setNombreInterventor(this.rs.getString("nombres"));
/* 1362 */         reg.setTarifaDescripcion(this.rs.getString("tarifa_descripcion"));
/* 1363 */         reg.setModificatorios(this.rs.getString("modificatorios"));
/* 1364 */         return reg;
/*      */       }
/*      */     
/* 1367 */     } catch (Exception e) {
/* 1368 */       e.printStackTrace();
/* 1369 */       Utilidades.writeError("ContContratoDAO:cargarResumenContrato", e);
/*      */     } 
/* 1371 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean actualizarLiquidacion(int consecutivoContrato, String fechaLiquidacion, double valorLiquidacion, double valorCancelado, String usuarioModificacion) {
/*      */     try {
/* 1381 */       String s = "update cont_contrato set  fecha_liquidacion=" + Utilidades.formatoFecha2(fechaLiquidacion) + "," + " valor_liquidacion=" + valorLiquidacion + "," + " valor_cancelado=" + valorCancelado + "," + " estado='L'," + " usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " consecutivo_contrato='" + consecutivoContrato + "'";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1390 */       return this.dat.executeUpdate(s);
/*      */     
/*      */     }
/* 1393 */     catch (Exception e) {
/* 1394 */       e.printStackTrace();
/* 1395 */       Utilidades.writeError("ContContratoDAO:actualizarLiquidacion ", e);
/*      */       
/* 1397 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean actualizarRegistroAnticipo(int numeroCuenta, String fechaAnticipo, double valorAnticipo) {
/*      */     try {
/* 1405 */       String s = "UPDATE cont_contrato cont set  fecha_anticipo=" + Utilidades.formatoFecha2(fechaAnticipo) + "," + " valor_anticipo=" + valorAnticipo + "," + " saldo_anticipo=" + valorAnticipo + "," + " saldo_contrato = saldo_contrato - " + valorAnticipo + " FROM " + " rips_cuentas_por_pagar cta" + " WHERE " + " cont.numero_contrato = cta.numero_contrato " + " AND cta.numero_cuenta = " + numeroCuenta;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1415 */       return this.dat.executeUpdate(s);
/*      */     
/*      */     }
/* 1418 */     catch (Exception e) {
/* 1419 */       e.printStackTrace();
/* 1420 */       Utilidades.writeError("ContContratoDAO:actualizarRegistroAnticipo ", e);
/*      */       
/* 1422 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean actualizarSaldo(int numeroCuenta, double valorPagado) {
/*      */     try {
/* 1429 */       String s = "UPDATE cont_contrato cont set  saldo_contrato =  CASE  WHEN saldo_anticipo = 0 THEN saldo_contrato - " + valorPagado + " " + " WHEN saldo_anticipo > 0 AND saldo_anticipo < " + valorPagado + " THEN saldo_contrato + (saldo_anticipo - " + valorPagado + ") " + " ELSE saldo_contrato END, " + " saldo_anticipo = " + " CASE " + " WHEN saldo_anticipo > 0 AND saldo_anticipo > " + valorPagado + " THEN saldo_anticipo - " + valorPagado + " " + " WHEN saldo_anticipo > 0 AND saldo_anticipo < " + valorPagado + " THEN 0 ELSE 0 END " + " FROM " + " rips_cuentas_por_pagar cta" + " WHERE " + " cont.numero_contrato = cta.numero_contrato " + " AND cta.numero_cuenta = " + numeroCuenta;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1444 */       return this.dat.executeUpdate(s);
/*      */     
/*      */     }
/* 1447 */     catch (Exception e) {
/* 1448 */       e.printStackTrace();
/* 1449 */       Utilidades.writeError("ContContratoDAO:actualizarSaldo ", e);
/*      */       
/* 1451 */       return false;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean actualizarSaldoContrato(int consecutivoContrato, double valorAnticipo) {
/*      */     try {
/* 1458 */       String s = "UPDATE cont_contrato set  saldo_contrato = saldo_contrato +" + valorAnticipo + " WHERE " + " consecutivo_contrato = " + consecutivoContrato;
/*      */ 
/*      */ 
/*      */       
/* 1462 */       return this.dat.executeUpdate(s);
/*      */     
/*      */     }
/* 1465 */     catch (Exception e) {
/* 1466 */       e.printStackTrace();
/* 1467 */       Utilidades.writeError("ContContratoDAO:actualizarSaldoContrato ", e);
/*      */       
/* 1469 */       return false;
/*      */     } 
/*      */   }
/* 1472 */   public String generarFechas(int numeroEstudio, int consecutivoContrato) { return this.dat.generarFechasContrato(-1, consecutivoContrato); }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\ContContratoDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */