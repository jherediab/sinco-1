/*      */ package sinco.data;
/*      */ 
/*      */ import java.sql.ResultSet;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import sinco.business.AMAccionesDTO;
/*      */ import sinco.business.ParametrosDTO;
/*      */ import sinco.business.RespuestaBD;
/*      */ import sinco.business.Utilidades;
/*      */ import sinco.data.AMAccionesDAO;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class AMAccionesDAO
/*      */ {
/*      */   public AMAccionesDTO leerRegistro(ResultSet rs) {
/*      */     try {
/*   36 */       AMAccionesDTO reg = new AMAccionesDTO();
/*      */       
/*   38 */       reg.setNumero(rs.getInt("numero"));
/*   39 */       reg.setFechaGenerada(rs.getString("fecha_generada"));
/*   40 */       reg.setAreaImplanta(rs.getInt("area_implanta"));
/*   41 */       reg.setEmpleadoCliente(rs.getInt("empleado_cliente"));
/*   42 */       reg.setCodigoEstado(rs.getInt("codigo_estado"));
/*   43 */       reg.setNivelEscalamiento(rs.getInt("nivel_escalamiento"));
/*   44 */       reg.setFechaVigencia(rs.getString("fecha_vigencia"));
/*   45 */       reg.setFechaEstimadaTerminacion(rs.getString("fecha_estimada_terminacion"));
/*   46 */       reg.setFechaRealTerminacion(rs.getString("fecha_real_terminacion"));
/*   47 */       reg.setFechaBaseEscalamientos(rs.getString("fecha_base_escalamientos"));
/*   48 */       reg.setAccion(rs.getString("accion"));
/*   49 */       reg.setOrigen(rs.getString("origen"));
/*   50 */       reg.setProceso(rs.getString("proceso"));
/*   51 */       reg.setSubproceso(rs.getString("subproceso"));
/*   52 */       reg.setNumeral(rs.getString("numeral"));
/*   53 */       reg.setDescripcion(rs.getString("descripcion"));
/*   54 */       reg.setCumplio(rs.getString("cumplio"));
/*   55 */       reg.setImplantada(rs.getString("implantada"));
/*   56 */       reg.setRevisadaComite(rs.getString("revisada_comite"));
/*   57 */       reg.setFuncionario1(rs.getString("funcionario1"));
/*   58 */       reg.setCargo1(rs.getString("cargo1"));
/*   59 */       reg.setFecha1(rs.getString("fecha1"));
/*   60 */       reg.setRevisadaAuditor(rs.getString("revisada_auditor"));
/*   61 */       reg.setFuncionario2(rs.getString("funcionario2"));
/*   62 */       reg.setCargo2(rs.getString("cargo2"));
/*   63 */       reg.setFecha2(rs.getString("fecha2"));
/*   64 */       reg.setNorma(rs.getString("norma"));
/*   65 */       reg.setJustificacion(rs.getString("justificacion"));
/*   66 */       reg.setObservacionesCierre(rs.getString("observaciones_cierre"));
/*   67 */       reg.setObservacionesCalidad(rs.getString("observaciones_calidad"));
/*   68 */       reg.setFechaCalidad(rs.getString("fecha_calidad"));
/*   69 */       reg.setImpacto(rs.getString("impacto"));
/*   70 */       reg.setTemaAccion(rs.getString("tema_accion"));
/*      */       
/*      */       try {
/*   73 */         reg.setSolicitudOrigen(rs.getInt("solicitud_origen"));
/*   74 */       } catch (Exception e) {}
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*   79 */         reg.setCodigoCiclo(rs.getInt("codigo_ciclo"));
/*   80 */       } catch (Exception e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*   86 */         reg.setCodigoPlan(rs.getInt("codigo_plan"));
/*   87 */       } catch (Exception e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*   93 */         reg.setCodigoMeta(rs.getInt("codigo_meta"));
/*   94 */       } catch (Exception e) {}
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*   99 */         reg.setAsociado(rs.getString("asociado"));
/*  100 */       } catch (Exception e) {}
/*      */ 
/*      */ 
/*      */       
/*  104 */       reg.setFechaInsercion(rs.getString("fecha_insercion"));
/*  105 */       reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/*  106 */       reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/*  107 */       reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/*      */       
/*      */       try {
/*  110 */         reg.setNombreAreaImplanta(rs.getString("nombre_area_implanta"));
/*  111 */       } catch (Exception e) {}
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  116 */         reg.setNombreCodigoEstado(rs.getString("nombre_codigo_estado"));
/*  117 */       } catch (Exception e) {}
/*      */ 
/*      */       
/*      */       try {
/*  121 */         reg.setTipoAccion(rs.getString("nombre_accion"));
/*  122 */       } catch (Exception e) {}
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  127 */         reg.setOrigenAccion(rs.getString("nombre_origen"));
/*  128 */       } catch (Exception e) {}
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  133 */         reg.setNombreProceso(rs.getString("nombre_proceso"));
/*  134 */       } catch (Exception e) {}
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  139 */         reg.setNombreSubproceso(rs.getString("nombre_subproceso"));
/*  140 */       } catch (Exception e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  146 */         reg.setNombreNorma(rs.getString("nombre_norma"));
/*  147 */       } catch (Exception e) {}
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  152 */         reg.setNombreImpacto(rs.getString("nombre_impacto"));
/*  153 */       } catch (Exception e) {}
/*      */ 
/*      */ 
/*      */       
/*  157 */       reg.setApellidos(rs.getString("apellidos"));
/*  158 */       reg.setNombres(rs.getString("nombres"));
/*  159 */       return reg;
/*      */     }
/*  161 */     catch (Exception e) {
/*  162 */       e.printStackTrace();
/*  163 */       Utilidades.writeError("AMAccionesDAO:leerRegistro ", e);
/*      */       
/*  165 */       return null;
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
/*      */   public Collection<AMAccionesDTO> cargarTodos(int numero, int areaImplanta, int codigoEstado) {
/*  178 */     Collection<AMAccionesDTO> resultados = new ArrayList<AMAccionesDTO>();
/*      */     
/*  180 */    DBManager dat = new DBManager();
/*      */     try {
/*  182 */       String s = "select t.numero,t.fecha_generada,t.area_implanta,r1.descripcion as nombre_area_implanta,t.empleado_cliente,r2.apellidos ,r2.nombres,t.codigo_estado,r3.descripcion as nombre_codigo_estado,t.nivel_escalamiento,t.fecha_vigencia,t.fecha_estimada_terminacion,t.fecha_real_terminacion,t.fecha_base_escalamientos,t.accion,r4.descripcion as nombre_accion,t.origen,r5.descripcion as nombre_origen,t.proceso,r6.descripcion as nombre_proceso,t.subproceso,r7.descripcion as nombre_subproceso,t.numeral,t.descripcion,t.cumplio,t.implantada,t.revisada_comite,t.funcionario1,t.cargo1,t.fecha1,t.revisada_auditor,t.funcionario2,t.cargo2,t.fecha2,t.norma,r8.descripcion as nombre_norma,t.justificacion,t.observaciones_cierre,t.observaciones_calidad,t.fecha_calidad,t.impacto,m9.descripcion as nombre_impacto,t.tema_accion,t.solicitud_origen,t.codigo_ciclo,t.codigo_plan,t.codigo_meta,t.asociado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from am_acciones t  left join Unidades_Dependencia r1 on (r1.codigo=t.area_implanta) left join sis_usuarios r2 on (r2.codigo_empleado=t.empleado_cliente) left join am_estados r3 on (r3.codigo=t.codigo_estado) left join am_tipo r4 on (r4.codigo=t.accion) left join am_origen r5 on (r5.codigo=t.origen) left join procesos r6 on (r6.codigo=t.proceso) left join subprocesos r7 on (r7.codigo=t.subproceso  and r7.proceso=t.proceso) left join am_normas r8 on (r8.norma=t.norma) left join sis_multivalores m9 on (m9.tabla='impacto_acciones' and m9.valor=t.impacto) where 1=1";
 
/*  246 */       if (numero > 0) {
/*  247 */         s = s + " and t.numero=" + numero;
/*      */       }
/*  249 */       if (areaImplanta > 0) {
/*  250 */         s = s + " and t.area_implanta=" + areaImplanta;
/*      */       }
/*  252 */       if (codigoEstado > 0) {
/*  253 */         s = s + " and t.codigo_estado=" + codigoEstado;
/*      */       }
/*  255 */       s = s + " order by 1";
/*  256 */       boolean rtaDB = dat.parseSql(s);
/*  257 */       if (!rtaDB) {
/*  258 */         return resultados;
/*      */       }
/*  260 */       ResultSet rs = dat.getResultSet();
/*  261 */       while (rs.next()) {
/*  262 */         resultados.add(leerRegistro(rs));
/*      */       }
/*      */     }
/*  265 */     catch (Exception e) {
/*  266 */       e.printStackTrace();
/*  267 */       Utilidades.writeError("AMAccionesDAO:cargarTodos ", e);
/*      */     } finally {
/*      */       
/*  270 */       dat.close();
/*      */     } 
/*  272 */     return resultados;
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
/*      */   public Collection<AMAccionesDTO> cargarTodos(int idNav, int areaIdNav, int jefeArea, int area_implanta, int codigo_estado, String fecha_desde, String fecha_hasta, String accion, String origen, String proceso, String norma, String numeral, String implantada, int limite, int areasHijas, String secuencia, String tema, String impacto, String cumplio) {
/*  301 */     Collection<AMAccionesDTO> resultados = new ArrayList<AMAccionesDTO>();
/*      */     
/*  303 */    DBManager dat = new DBManager();
/*      */     try {
/*  305 */       String s = "select t.numero,t.fecha_generada,t.area_implanta,r1.descripcion as nombre_area_implanta,t.empleado_cliente,r2.apellidos ,r2.nombres,t.codigo_estado,r3.descripcion as nombre_codigo_estado,t.nivel_escalamiento,t.fecha_vigencia,t.fecha_estimada_terminacion,t.fecha_real_terminacion,t.fecha_base_escalamientos,t.accion,r4.descripcion as nombre_accion,t.origen,r5.descripcion as nombre_origen,t.proceso,r6.descripcion as nombre_proceso,t.subproceso,r7.descripcion as nombre_subproceso,t.numeral,t.descripcion,t.cumplio,t.implantada,t.revisada_comite,t.funcionario1,t.cargo1,t.fecha1,t.revisada_auditor,t.funcionario2,t.cargo2,t.fecha2,t.norma,r8.descripcion as nombre_norma,t.justificacion,t.observaciones_cierre,t.observaciones_calidad,t.fecha_calidad,t.impacto,m9.descripcion as nombre_impacto,t.tema_accion,t.solicitud_origen,t.codigo_ciclo,t.codigo_plan,t.codigo_meta,t.asociado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from am_acciones t  left join Unidades_Dependencia r1 on (r1.codigo=t.area_implanta) left join sis_usuarios r2 on (r2.codigo_empleado=t.empleado_cliente) left join am_estados r3 on (r3.codigo=t.codigo_estado) left join am_tipo r4 on (r4.codigo=t.accion) left join am_origen r5 on (r5.codigo=t.origen) left join procesos r6 on (r6.codigo=t.proceso) left join subprocesos r7 on (r7.codigo=t.subproceso and r7.proceso=t.proceso) left join am_normas r8 on (r8.norma=t.norma) left join sis_multivalores m9 on (m9.tabla='impacto_acciones' and m9.valor=t.impacto) where 1=1";
/*      */ 
  
/*      */       
/*  369 */       if (area_implanta != 0) {
/*  370 */         if (areasHijas == 0) {
/*  371 */           s = s + " and 1= case when t.impacto='C' then 1  when t.NUMERO IN ( SELECT  NUMERO FROM AM_ACCIONES_AREAS ai WHERE ai.AREA_IMPLANTA=" + area_implanta + ") THEN 1" + " WHEN t.area_implanta=" + area_implanta + " THEN 1 " + " ELSE 0 END";
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/*  377 */           s = s + " and 1= case when t.impacto='C' then 1  when t.NUMERO IN ( SELECT  NUMERO FROM AM_ACCIONES_AREAS ai WHERE ai.AREA_IMPLANTA=" + area_implanta + ") THEN 1" + " WHEN r1.secuencia like '" + secuencia + "%' THEN 1 ELSE 0 END";
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  383 */       if (limite == 1) {
/*  384 */         s = s + " and t.codigo_estado not in(0,4)";
/*      */       }
/*  386 */       if (codigo_estado >= 0) {
/*  387 */         s = s + " and t.codigo_estado=" + codigo_estado;
/*      */       }
/*      */       
/*  390 */       if (fecha_desde.length() > 0) {
/*  391 */         s = s + " and t.fecha_vigencia>=" + Utilidades.formatoFecha2(fecha_desde);
/*      */       }
/*      */       
/*  394 */       if (fecha_hasta.length() > 0) {
/*  395 */         s = s + " and t.fecha_vigencia <" + Utilidades.formatoFecha2(fecha_hasta) + "+1";
/*      */       }
/*      */       
/*  398 */       if (accion != null && accion.length() > 0) {
/*  399 */         s = s + " and t.accion='" + accion + "'";
/*      */       }
/*      */       
/*  402 */       if (norma != null && norma.length() > 0) {
/*  403 */         s = s + " and t.norma='" + norma + "'";
/*      */       }
/*      */       
/*  406 */       if (origen != null && origen.length() > 0) {
/*  407 */         s = s + " and t.origen='" + origen + "'";
/*      */       }
/*      */       
/*  410 */       if (proceso != null && proceso.length() > 0) {
/*  411 */         s = s + " and t.proceso='" + proceso + "'";
/*      */       }
/*      */       
/*  414 */       if (numeral != null && numeral.length() > 0) {
/*  415 */         s = s + " and t.numeral='" + numeral + "'";
/*      */       }
/*      */       
/*  418 */       if (implantada != null && implantada.length() > 0) {
/*  419 */         s = s + " and t.implantada='" + implantada + "'";
/*      */       }
/*  421 */       if (tema.length() > 0) {
/*  422 */         s = s + " and  upper(t.tema_accion) like upper('%" + tema + "%')";
/*      */       }
/*      */       
/*  425 */       if (impacto != null && impacto.length() > 0) {
/*  426 */         s = s + " and t.impacto='" + impacto + "'";
/*      */       }
/*      */       
/*  429 */       if (cumplio.length() > 0) {
/*  430 */         s = s + " and t.cumplio='" + cumplio + "'";
/*      */       }
/*      */       
/*  433 */       s = s + " order by t.numero";
/*      */ 
/*      */       
/*  436 */       boolean rtaDB = dat.parseSql(s);
/*  437 */       if (!rtaDB) {
/*  438 */         return resultados;
/*      */       }
/*  440 */       ResultSet rs = dat.getResultSet();
/*  441 */       while (rs.next()) {
/*  442 */         resultados.add(leerRegistro(rs));
/*      */       }
/*      */     }
/*  445 */     catch (Exception e) {
/*  446 */       e.printStackTrace();
/*  447 */       Utilidades.writeError("AMAccionesDAO:cargarTodos ", e);
/*      */     } finally {
/*      */       
/*  450 */       dat.close();
/*      */     } 
/*  452 */     return resultados;
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
/*      */   public Collection<AMAccionesDTO> cargarAccionesDe(int idNav, int areaIdNav, int jefeArea, int cliente, int estado, int area, String secuencia) {
/*  469 */     Collection<AMAccionesDTO> resultados = new ArrayList<AMAccionesDTO>();
/*      */     
/*  471 */    DBManager dat = new DBManager();
/*      */     try {
/*  473 */       String s = "select t.numero,t.fecha_generada,t.area_implanta,r1.descripcion as nombre_area_implanta,t.empleado_cliente,r2.apellidos ,r2.nombres,t.codigo_estado,r3.descripcion as nombre_codigo_estado,t.nivel_escalamiento,t.fecha_vigencia,t.fecha_estimada_terminacion,t.fecha_real_terminacion,t.fecha_base_escalamientos,t.accion,r4.descripcion as nombre_accion,t.origen,r5.descripcion as nombre_origen,t.proceso,r6.descripcion as nombre_proceso,t.subproceso,r7.descripcion as nombre_subproceso,t.numeral,t.descripcion,t.cumplio,t.implantada,t.revisada_comite,t.funcionario1,t.cargo1,t.fecha1,t.revisada_auditor,t.funcionario2,t.cargo2,t.fecha2,t.norma,r8.descripcion as nombre_norma,t.justificacion,t.observaciones_cierre,t.observaciones_calidad,t.fecha_calidad,t.impacto,m9.descripcion as nombre_impacto,t.tema_accion,t.solicitud_origen,t.codigo_ciclo,t.codigo_plan,t.codigo_meta,t.asociado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from am_acciones t  inner join Unidades_Dependencia r1 on (r1.codigo=t.area_implanta) left join sis_usuarios r2 on (r2.codigo_empleado=t.empleado_cliente) left join am_estados r3 on (r3.codigo=t.codigo_estado) left join am_tipo r4 on (r4.codigo=t.accion) left join am_origen r5 on (r5.codigo=t.origen) left join procesos r6 on (r6.codigo=t.proceso) left join subprocesos r7 on (r7.codigo=t.subproceso and r7.proceso=t.proceso) left join am_normas r8 on (r8.norma=t.norma) left join sis_multivalores m9 on (m9.tabla='impacto_acciones' and m9.valor=t.impacto) where 1=1";
/*      */ 

/*      */       
/*  537 */       s = s + "        and t.codigo_estado=" + estado + "        and 'S' = CASE WHEN t.EMPLEADO_CLIENTE=" + idNav + " THEN 'S'" + "         WHEN r5.VER_GERENCIA_CALIDAD='S' AND " + areaIdNav + "=" + ParametrosDTO.getInt("codigo.gerencia.calidad.servicio") + " THEN 'S'" + "         WHEN r5.VER_JEFE_AREA='S' AND " + jefeArea + " IN (" + "               SELECT PA.CODIGO_EMPLEADO " + "                FROM sis_usuarios_area PA " + "               WHERE PA.CODIGO_AREA in(t.AREA_IMPLANTA," + areaIdNav + ") AND PA.RESPONSABLE_AREA='S') THEN 'S'" + "         WHEN r5.VER_OTROS='S' THEN 'S'" + "         ELSE 'N' END";

/*  548 */       if (area > 0) {
/*  549 */         s = s + " AND 1= CASE WHEN t.codigo_estado=5 AND t.ORIGEN='ACI' AND r1.CODIGO_TUTOR=" + cliente + " THEN 1" + " when  r1.secuencia like '" + secuencia + "%' THEN 1 ELSE  0 END";
/*      */       
/*      */       }
/*  552 */       else if (area != -1) {
/*  553 */         s = s + " and t.empleado_cliente=" + cliente;
/*      */       } 
/*      */       
/*  556 */       s = s + " order by t.numero";
/*      */ 
/*      */       
/*  559 */       boolean rtaDB = dat.parseSql(s);
/*  560 */       if (!rtaDB) {
/*  561 */         return resultados;
/*      */       }
/*  563 */       ResultSet rs = dat.getResultSet();
/*  564 */       while (rs.next()) {
/*  565 */         resultados.add(leerRegistro(rs));
/*      */       }
/*      */     }
/*  568 */     catch (Exception e) {
/*  569 */       e.printStackTrace();
/*  570 */       Utilidades.writeError("AMAccionesDAO:cargarTodos ", e);
/*      */     } finally {
/*      */       
/*  573 */       dat.close();
/*      */     } 
/*  575 */     return resultados;
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
/*      */   public Collection<AMAccionesDTO> cargarAccionesDe(int idNav, int areaIdNav, int jefeArea, int empleado, int estado) {
/*  591 */     Collection<AMAccionesDTO> resultados = new ArrayList<AMAccionesDTO>();
/*      */     
/*  593 */    DBManager dat = new DBManager();
/*      */     try {
/*  595 */       String s = "select t.numero,t.fecha_generada,t.area_implanta,r1.descripcion as nombre_area_implanta,t.empleado_cliente,r2.apellidos ,r2.nombres,t.codigo_estado,r3.descripcion as nombre_codigo_estado,t.nivel_escalamiento,t.fecha_vigencia,t.fecha_estimada_terminacion,t.fecha_real_terminacion,t.fecha_base_escalamientos,t.accion,r4.descripcion as nombre_accion,t.origen,r5.descripcion as nombre_origen,t.proceso,r6.descripcion as nombre_proceso,t.subproceso,r7.descripcion as nombre_subproceso,t.numeral,t.descripcion,t.cumplio,t.implantada,t.revisada_comite,t.funcionario1,t.cargo1,t.fecha1,t.revisada_auditor,t.funcionario2,t.cargo2,t.fecha2,t.norma,r8.descripcion as nombre_norma,t.justificacion,t.observaciones_cierre,t.observaciones_calidad,t.fecha_calidad,t.impacto,m9.descripcion as nombre_impacto,t.tema_accion,t.solicitud_origen,t.codigo_ciclo,t.codigo_plan,t.codigo_meta,t.asociado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from am_acciones t  left join Unidades_Dependencia r1 on (r1.codigo=t.area_implanta) left join sis_usuarios r2 on (r2.codigo_empleado=t.empleado_cliente) left join am_estados r3 on (r3.codigo=t.codigo_estado) left join am_tipo r4 on (r4.codigo=t.accion) left join am_origen r5 on (r5.codigo=t.origen) left join procesos r6 on (r6.codigo=t.proceso) left join subprocesos r7 on (r7.codigo=t.subproceso and r7.proceso=t.proceso) left join am_normas r8 on (r8.norma=t.norma) left join sis_multivalores m9 on (m9.tabla='impacto_acciones' and m9.valor=t.impacto) where 1=1";

/*      */ 
/*      */       
/*  659 */       s = s + "        and t.codigo_estado=" + estado + "        and 'S' = CASE WHEN t.EMPLEADO_CLIENTE=" + idNav + " THEN 'S'" + "         WHEN r5.VER_GERENCIA_CALIDAD='S' AND " + areaIdNav + "=" + ParametrosDTO.getInt("codigo.gerencia.calidad.servicio") + " THEN 'S'" + "         WHEN r5.VER_JEFE_AREA='S' AND " + jefeArea + " IN (" + "               SELECT PA.CODIGO_EMPLEADO " + "                FROM sis_usuarios_area PA " + "               WHERE PA.CODIGO_AREA in(t.AREA_IMPLANTA," + areaIdNav + ") AND PA.RESPONSABLE_AREA='S') THEN 'S'" + "         WHEN r5.VER_OTROS='S' THEN 'S'" + "         ELSE 'N' END";
  
/*  669 */       s = s + " order by t.numero";
/*      */ 
/*      */       
/*  672 */       boolean rtaDB = dat.parseSql(s);
/*  673 */       if (!rtaDB) {
/*  674 */         return resultados;
/*      */       }
/*  676 */       ResultSet rs = dat.getResultSet();
/*  677 */       while (rs.next()) {
/*  678 */         resultados.add(leerRegistro(rs));
/*      */       }
/*      */     }
/*  681 */     catch (Exception e) {
/*  682 */       e.printStackTrace();
/*  683 */       Utilidades.writeError("AMAccionesDAO:cargarTodos ", e);
/*      */     } finally {
/*      */       
/*  686 */       dat.close();
/*      */     } 
/*  688 */     return resultados;
/*      */   }

/*      */   
/*      */   public Collection<AMAccionesDTO> cargarAccionesPorCausa(int cliente, int estado) {
/*  702 */     Collection<AMAccionesDTO> resultados = new ArrayList<AMAccionesDTO>();
/*      */     
/*  704 */   DBManager  dat = new DBManager();
/*      */     try {
/*  706 */       String s = "select t.numero,t.fecha_generada,t.area_implanta,r1.descripcion as nombre_area_implanta,t.empleado_cliente,r2.apellidos ,r2.nombres,t.codigo_estado,r3.descripcion as nombre_codigo_estado,t.nivel_escalamiento,t.fecha_vigencia,t.fecha_estimada_terminacion,t.fecha_real_terminacion,t.fecha_base_escalamientos,t.accion,r4.descripcion as nombre_accion,t.origen,r5.descripcion as nombre_origen,t.proceso,r6.descripcion as nombre_proceso,t.subproceso,r7.descripcion as nombre_subproceso,t.numeral,t.descripcion,t.cumplio,t.implantada,t.revisada_comite,t.funcionario1,t.cargo1,t.fecha1,t.revisada_auditor,t.funcionario2,t.cargo2,t.fecha2,t.norma,r8.descripcion as nombre_norma,t.justificacion,t.observaciones_cierre,t.observaciones_calidad,t.fecha_calidad,t.impacto,m9.descripcion as nombre_impacto,t.tema_accion,t.solicitud_origen,t.codigo_ciclo,t.codigo_plan,t.codigo_meta,t.asociado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from am_acciones t  left join Unidades_Dependencia r1 on (r1.codigo=t.area_implanta) left join sis_usuarios r2 on (r2.codigo_empleado=t.empleado_cliente) left join am_estados r3 on (r3.codigo=t.codigo_estado) left join am_tipo r4 on (r4.codigo=t.accion) left join am_origen r5 on (r5.codigo=t.origen) left join procesos r6 on (r6.codigo=t.proceso) left join subprocesos r7 on (r7.codigo=t.subproceso and r7.proceso=t.proceso) left join am_normas r8 on (r8.norma=t.norma) left join sis_multivalores m9 on (m9.tabla='impacto_acciones' and m9.valor=t.impacto) where t.codigo_estado=" + estado + "        and t.numero in (select c.numero from am_causas c where c.responsable=" + cliente + ")" + " order by t.numero";
  
/*  774 */       boolean rtaDB = dat.parseSql(s);
/*  775 */       if (!rtaDB) {
/*  776 */         return resultados;
/*      */       }
/*  778 */       ResultSet rs = dat.getResultSet();
/*  779 */       while (rs.next()) {
/*  780 */         resultados.add(leerRegistro(rs));
/*      */       }
/*      */     }
/*  783 */     catch (Exception e) {
/*  784 */       e.printStackTrace();
/*  785 */       Utilidades.writeError("AMAccionesDAO:cargarTodos ", e);
/*      */     } finally {
/*      */       
/*  788 */       dat.close();
/*      */     } 
/*  790 */     return resultados;
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
/*      */   public AMAccionesDTO cargarRegistro(int numero) {
/*  802 */    DBManager dat = new DBManager();
/*      */     try {
/*  804 */       String s = "select t.numero,t.fecha_generada,t.area_implanta,r1.descripcion as nombre_area_implanta,t.empleado_cliente,r2.apellidos ,r2.nombres,t.codigo_estado,r3.descripcion as nombre_codigo_estado,t.nivel_escalamiento,t.fecha_vigencia,t.fecha_estimada_terminacion,t.fecha_real_terminacion,t.fecha_base_escalamientos,t.accion,r4.descripcion as nombre_accion,t.origen,r5.descripcion as nombre_origen,t.proceso,r6.descripcion as nombre_proceso,t.subproceso,r7.descripcion as nombre_subproceso,t.numeral,t.descripcion,t.cumplio,t.implantada,t.revisada_comite,t.funcionario1,t.cargo1,t.fecha1,t.revisada_auditor,t.funcionario2,t.cargo2,t.fecha2,t.norma,r8.descripcion as nombre_norma,t.justificacion,t.observaciones_cierre,t.observaciones_calidad,t.fecha_calidad,t.impacto,m9.descripcion as nombre_impacto,t.tema_accion,t.solicitud_origen,t.codigo_ciclo,t.codigo_plan,t.codigo_meta,t.asociado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from am_acciones t  left join Unidades_Dependencia r1 on (r1.codigo=t.area_implanta) left join sis_usuarios r2 on (r2.codigo_empleado=t.empleado_cliente) left join am_estados r3 on (r3.codigo=t.codigo_estado) left join am_tipo r4 on (r4.codigo=t.accion) left join am_origen r5 on (r5.codigo=t.origen) left join procesos r6 on (r6.codigo=t.proceso) left join subprocesos r7 on (r7.codigo=t.subproceso and r7.proceso=t.proceso) left join am_normas r8 on (r8.norma=t.norma) left join sis_multivalores m9 on (m9.tabla='impacto_acciones' and m9.valor=t.impacto) where  t.numero=" + numero + "";

/*      */       
/*  870 */       boolean rtaDB = dat.parseSql(s);
/*  871 */       if (!rtaDB) {
/*  872 */         return null;
/*      */       }
/*  874 */       ResultSet rs = dat.getResultSet();
/*  875 */       if (rs.next()) {
/*  876 */         return leerRegistro(rs);
/*      */       }
/*      */     }
/*  879 */     catch (Exception e) {
/*  880 */       e.printStackTrace();
/*  881 */       Utilidades.writeError("AMAccionesDAO:cargarAMAcciones", e);
/*      */     } finally {
/*      */       
/*  884 */       dat.close();
/*      */     } 
/*  886 */     return null;
/*      */   }

/*      */   
/*      */   public int siguienteRegistro() {
/*  895 */     int inumero = 1;
/*  896 */     String s = "select max(numero) from am_acciones ";
/*      */ 
/*      */     
/*  899 */    DBManager dat = new DBManager();
/*      */     try {
/*  901 */       boolean rta = dat.parseSql(s);
/*  902 */       if (!rta) return 0; 
/*  903 */       ResultSet rs = dat.getResultSet();
/*  904 */       if (rs.next()) {
/*  905 */         s = rs.getString(1);
/*  906 */         if (!rs.wasNull()) {
/*  907 */           inumero = Integer.parseInt(s) + 1;
/*      */         }
/*      */       } 
/*  910 */       return inumero;
/*      */     }
/*  912 */     catch (Exception e) {
/*  913 */       e.printStackTrace();
/*  914 */       Utilidades.writeError("AMAccionesDAO:siguienteRegistro ", e);
/*      */     } finally {
/*      */       
/*  917 */       dat.close();
/*      */     } 
/*  919 */     return 0;
/*      */   }
/*      */ 

/*      */   
/*      */   public RespuestaBD eliminarRegistro(int numero) {
/*  929 */     RespuestaBD rta = new RespuestaBD();
/*      */     
/*  931 */   DBManager  dat = new DBManager();
/*      */     try {
/*  933 */       String s = "delete from am_acciones where  numero=" + numero + "";
/*      */ 
/*      */ 
/*      */       
/*  937 */       rta = dat.executeUpdate2(s);
/*      */     }
/*  939 */     catch (Exception e) {
/*  940 */       e.printStackTrace();
/*  941 */       Utilidades.writeError("AMAccionesDAO:eliminarRegistro ", e);
/*  942 */       rta.setMensaje(e.getMessage());
/*      */     } finally {
/*      */       
/*  945 */       dat.close();
/*      */     } 
/*  947 */     return rta;
/*      */   }
/*      */ 
 
/*      */   public RespuestaBD crearRegistro(int areaImplanta, int empleadoCliente, int codigoEstado, String objetivo, String origen, String proceso, String subproceso, String norma, String numeral, String descripcion, String tema, String fechaEstimadaTerminacion, String impacto, int ciclo, int plan, int meta, String asociado, String usuario) {
/*  976 */     RespuestaBD rta = new RespuestaBD();
/*      */     
/*  978 */     int elSiguiente = siguienteRegistro();
/*  979 */     if (elSiguiente == 0) {
/*  980 */       rta.setMensaje("Generando secuencia");
/*  981 */       return rta;
/*      */     } 
/*      */     
/*  984 */    DBManager dat = new DBManager();
/*      */     try {
/*  986 */       String s = "insert into am_acciones (numero,fecha_generada,area_implanta,empleado_cliente,codigo_estado,nivel_escalamiento,accion,origen,proceso,subproceso,norma,numeral,descripcion,tema_accion,fecha_vigencia,fecha_estimada_terminacion,impacto,codigo_ciclo,codigo_plan,codigo_meta,asociado,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "" + Utilidades.getFechaBD() + "," + "" + areaImplanta + "," + "" + empleadoCliente + "," + "" + codigoEstado + "," + "0,";
/*      */ 
   
/* 1017 */       s = s + "'" + objetivo + "',";
/* 1018 */       s = s + "'" + origen + "',";
/* 1019 */       s = s + "'" + proceso + "',";
/* 1020 */       s = s + "'" + subproceso + "',";
/* 1021 */       s = s + "'" + norma + "',";
/* 1022 */       s = s + "'" + numeral + "',";
/* 1023 */       s = s + "'" + descripcion + "',";
/* 1024 */       s = s + "'" + tema + "',";
/* 1025 */       s = s + "" + Utilidades.getFechaBD() + ",";
/* 1026 */       s = s + "" + Utilidades.formatoFecha2(fechaEstimadaTerminacion) + ",";
/* 1027 */       s = s + "'" + impacto + "',";
/* 1028 */       s = s + "" + ((ciclo != 0) ? ("" + ciclo) : "null") + ",";
/* 1029 */       s = s + "" + ((plan != 0) ? ("" + plan) : "null") + ",";
/* 1030 */       s = s + "" + ((meta != 0) ? ("" + meta) : "null") + ",";
/* 1031 */       s = s + "'" + asociado + "',";
/* 1032 */       s = s + "" + Utilidades.getFechaBD() + ",";
/* 1033 */       s = s + "'" + usuario + "'";
/* 1034 */       s = s + ")";
/* 1035 */       rta = dat.executeUpdate2(s);
/* 1036 */       rta.setSecuencia(elSiguiente);
/*      */     }
/* 1038 */     catch (Exception e) {
/* 1039 */       e.printStackTrace();
/* 1040 */       Utilidades.writeError("%AMAccionesDAO:crearRegistro ", e);
/* 1041 */       rta.setMensaje(e.getMessage());
/*      */     } finally {
/*      */       
/* 1044 */       dat.close();
/*      */     } 
/* 1046 */     return rta;
/*      */   }
/*      */ 
/*      */ 
/*      */ 

/*      */   
/*      */   public RespuestaBD crearRegistro(int areaImplanta, int empleadoCliente, int codigoEstado, String objetivo, String origen, String proceso, String subproceso, String norma, String numeral, String descripcion, String tema, String impacto, int ciclo, int plan, int meta, String asociado, String usuario) {
/* 1073 */     RespuestaBD rta = new RespuestaBD();
/*      */     
/* 1075 */     int elSiguiente = siguienteRegistro();
/* 1076 */     if (elSiguiente == 0) {
/* 1077 */       rta.setMensaje("Generando secuencia");
/* 1078 */       return rta;
/*      */     } 
/*      */     
/* 1081 */   DBManager  dat = new DBManager();
/*      */     try {
/* 1083 */       String s = "insert into am_acciones (numero,fecha_generada,area_implanta,empleado_cliente,codigo_estado,nivel_escalamiento,accion,origen,proceso,subproceso,norma,numeral,descripcion,tema_accion,impacto,codigo_ciclo,codigo_plan,codigo_meta,asociado,fecha_insercion,usuario_insercion) values (" + elSiguiente + "," + "" + Utilidades.getFechaBD() + "," + "" + areaImplanta + "," + "" + empleadoCliente + "," + "" + codigoEstado + "," + "0,";
/*      */ 

/*      */       
/* 1112 */       s = s + "'" + objetivo + "',";
/* 1113 */       s = s + "'" + origen + "',";
/* 1114 */       s = s + "'" + proceso + "',";
/* 1115 */       s = s + "'" + subproceso + "',";
/* 1116 */       s = s + "'" + norma + "',";
/* 1117 */       s = s + "'" + numeral + "',";
/* 1118 */       s = s + "'" + descripcion + "',";
/* 1119 */       s = s + "'" + tema + "',";
/* 1120 */       s = s + "'" + impacto + "',";
/* 1121 */       s = s + "" + ((ciclo != 0) ? ("" + ciclo) : "null") + ",";
/* 1122 */       s = s + "" + ((plan != 0) ? ("" + plan) : "null") + ",";
/* 1123 */       s = s + "" + ((meta != 0) ? ("" + meta) : "null") + ",";
/* 1124 */       s = s + "'" + asociado + "',";
/* 1125 */       s = s + "" + Utilidades.getFechaBD() + ",";
/* 1126 */       s = s + "'" + usuario + "'";
/* 1127 */       s = s + ")";
/* 1128 */       rta = dat.executeUpdate2(s);
/* 1129 */       rta.setSecuencia(elSiguiente);
/*      */     }
/* 1131 */     catch (Exception e) {
/* 1132 */       e.printStackTrace();
/* 1133 */       Utilidades.writeError("%AMAccionesDAO:crearRegistro ", e);
/* 1134 */       rta.setMensaje(e.getMessage());
/*      */     } finally {
/*      */       
/* 1137 */       dat.close();
/*      */     } 
/* 1139 */     return rta;
/*      */   }
/*      */ 

/*      */   
/*      */   public RespuestaBD modificarRegistro(int numero, int area, String objetivo, String origen, String proceso, String subproceso, String norma, String numeral, String descripcion, String justificacion, String tema, String impacto, String usuario) {
/* 1163 */     RespuestaBD rta = new RespuestaBD();
/*      */     
/* 1165 */    DBManager dat = new DBManager();
/*      */     try {
/* 1167 */       String s = "update am_acciones set ";
/* 1168 */       s = s + "area_implanta=" + area + ",";
/* 1169 */       s = s + "accion='" + objetivo + "',";
/* 1170 */       s = s + "origen='" + origen + "',";
/* 1171 */       s = s + "proceso='" + proceso + "',";
/* 1172 */       s = s + "subproceso='" + subproceso + "',";
/* 1173 */       s = s + "norma='" + norma + "',";
/* 1174 */       s = s + "numeral='" + numeral + "',";
/* 1175 */       s = s + "descripcion='" + descripcion + "',";
/* 1176 */       s = s + "justificacion='" + justificacion + "',";
/* 1177 */       s = s + "impacto='" + impacto + "',";
/* 1178 */       s = s + "tema_accion='" + tema + "',";
/* 1179 */       s = s + "fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 1180 */       s = s + "usuario_modificacion='" + usuario + "'";
/* 1181 */       s = s + " where";
/* 1182 */       s = s + " numero=" + numero + "";
/* 1183 */       rta = dat.executeUpdate2(s);
/*      */     }
/* 1185 */     catch (Exception e) {
/* 1186 */       e.printStackTrace();
/* 1187 */       Utilidades.writeError("AMAccionesDAO:modificarRegistro ", e);
/* 1188 */       rta.setMensaje(e.getMessage());
/*      */     } finally {
/*      */       
/* 1191 */       dat.close();
/*      */     } 
/* 1193 */     return rta;
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
/*      */   public int siguienteSeguimiento(int numero, int causa) {
/* 1205 */     int retorno = 1;
/*      */     
/* 1207 */     RespuestaBD rta = new RespuestaBD();
/*      */     
/* 1209 */     DBManager dat = new DBManager();
/*      */ 
/*      */     
/* 1212 */     String s = "select max(consecutivo) from am_seguimiento where numero=" + numero + " and causa=" + causa;
/*      */     try {
/* 1214 */       boolean rtabd = dat.parseSql(s);
/* 1215 */       if (!rtabd) return 0; 
/* 1216 */       ResultSet rs = dat.getResultSet();
/* 1217 */       if (rs.next()) {
/* 1218 */         s = rs.getString(1);
/* 1219 */         if (!rs.wasNull()) {
/* 1220 */           retorno = Integer.parseInt(s) + 1;
/*      */         }
/*      */       } 
/* 1223 */       return retorno;
/*      */     }
/* 1225 */     catch (Exception e) {
/* 1226 */       e.printStackTrace();
/* 1227 */       Utilidades.writeError("AMAccionesFactory:siguienteRegistro", e);
/*      */     } finally {
/*      */       
/* 1230 */       dat.close();
/*      */     } 
/* 1232 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RespuestaBD crearSeguimiento(int numero, int causa, String observacion, int personaatendio, char automatico, String usuario) {
/* 1242 */     int elSiguiente = siguienteSeguimiento(numero, causa);
/* 1243 */     RespuestaBD rta = new RespuestaBD();
/* 1244 */     if (elSiguiente == 0) {
/* 1245 */       return rta;
/*      */     }
/*      */ 
/*      */     
/* 1249 */    DBManager dat = new DBManager();
/*      */ 
/*      */     
/*      */     try {
/* 1253 */       String s = "insert into am_seguimiento (numero,causa,consecutivo,observacion,fecha,personaatendio,automatico,fecha_insercion,usuario_insercion)";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1263 */       s = s + " values (";
/* 1264 */       s = s + "" + numero + ",";
/* 1265 */       s = s + "" + causa + ",";
/* 1266 */       s = s + "" + elSiguiente + ",";
/* 1267 */       s = s + "'" + observacion + "',";
/* 1268 */       s = s + "" + Utilidades.getFechaBD() + ",";
/* 1269 */       s = s + "" + personaatendio + ",";
/* 1270 */       s = s + "'" + automatico + "',";
/* 1271 */       s = s + "" + Utilidades.getFechaBD() + ",";
/* 1272 */       s = s + "'" + usuario + "'";
/* 1273 */       s = s + ")";
/* 1274 */       rta = dat.executeUpdate2(s);
/* 1275 */       return rta;
/*      */     }
/* 1277 */     catch (Exception e) {
/* 1278 */       e.printStackTrace();
/* 1279 */       Utilidades.writeError("AMAccionesFactory:crearRegistro", e);
/*      */     } finally {
/*      */       
/* 1282 */       dat.close();
/*      */     } 
/* 1284 */     return rta;
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
/*      */   public boolean observacionesGerencia(int numero, String justificacion, String usuario) {
/* 1297 */  DBManager   dat = new DBManager();
/*      */     try {
/* 1299 */       String s = "update am_acciones set ";
/* 1300 */       s = s + "fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 1301 */       s = s + "fecha_calidad=" + Utilidades.getFechaBD() + ",";
/* 1302 */       s = s + "observaciones_calidad='" + justificacion + "',";
/* 1303 */       s = s + "usuario_modificacion='" + usuario + "'";
/* 1304 */       s = s + " where ";
/* 1305 */       s = s + " numero=" + numero;
/* 1306 */       boolean rta = dat.executeUpdate(s);
/* 1307 */       return rta;
/*      */     }
/* 1309 */     catch (Exception e) {
/* 1310 */       e.printStackTrace();
/* 1311 */       Utilidades.writeError("AccionesFactory:cerrarAccion ", e);
/*      */     } finally {
/*      */       
/* 1314 */       dat.close();
/*      */     } 
/* 1316 */     return false;
/*      */   }
/*      */ 
/*      */ 

/*      */ 
/*      */   
/*      */   public boolean cerrarAccion(int numero, String cumplio, String implantada, String revisada_comite, String funcionario1, String cargo1, String fecha1, String revisada_auditor, String funcionario2, String cargo2, String fecha2, String justificacion, String usuario) {
/* 1343 */   DBManager  dat = new DBManager();
/*      */     try {
/* 1345 */       String s = "update am_acciones set";
/* 1346 */       s = s + " codigo_estado='" + (implantada.equals("S") ? 2 : 3) + "',";
/* 1347 */       s = s + "cumplio='" + cumplio + "',";
/* 1348 */       s = s + "implantada='" + implantada + "',";
/* 1349 */       s = s + "revisada_comite='" + revisada_comite + "',";
/* 1350 */       s = s + "fecha_real_terminacion=" + Utilidades.formatoFecha(Utilidades.fechaActual()) + ",";
/* 1351 */       s = s + "fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 1352 */       s = s + "observaciones_cierre='" + justificacion + "',";
/* 1353 */       s = s + "justificacion='Cierre final',";
/* 1354 */       s = s + "usuario_modificacion='" + usuario + "',";
/* 1355 */       if (revisada_comite.equals("S")) {
/* 1356 */         s = s + "funcionario1='" + funcionario1 + "',";
/* 1357 */         s = s + "cargo1='" + cargo1 + "',";
/* 1358 */         s = s + "fecha1=" + Utilidades.formatoFecha2(fecha1) + ",";
/*      */       } 
/* 1360 */       s = s + "revisada_auditor='" + revisada_auditor + "'";
/* 1361 */       if (revisada_auditor.equals("S")) {
/* 1362 */         s = s + ",funcionario2='" + funcionario2 + "',";
/* 1363 */         s = s + "cargo2='" + cargo2 + "',";
/* 1364 */         s = s + "fecha2=" + Utilidades.formatoFecha2(fecha2);
/*      */       } 
/* 1366 */       s = s + " where ";
/* 1367 */       s = s + " numero=" + numero;
/* 1368 */       boolean rta = dat.executeUpdate(s);
/* 1369 */       return rta;
/*      */     }
/* 1371 */     catch (Exception e) {
/* 1372 */       e.printStackTrace();
/* 1373 */       Utilidades.writeError("AccionesFactory:cerrarAccion ", e);
/*      */     } finally {
/*      */       
/* 1376 */       dat.close();
/*      */     } 
/* 1378 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String fechaEstimadaTerminacion(int numero) {
/* 1388 */     String fecha = "";
/* 1389 */   DBManager  dat = new DBManager();
/*      */     try {
/* 1391 */       String s = "select max(fecha_estimada_terminacion) from am_causas where numero=" + numero;
/* 1392 */       boolean rta = dat.parseSql(s);
/* 1393 */       if (!rta) return ""; 
/* 1394 */       ResultSet rs = dat.getResultSet();
/* 1395 */       if (rs.next()) {
/* 1396 */         fecha = rs.getString(1);
/* 1397 */         if (!rs.wasNull()) {
/* 1398 */           rs.close();
/* 1399 */           return fecha;
/*      */         } 
/*      */       } 
/* 1402 */       rs.close();
/*      */     }
/* 1404 */     catch (Exception e) {
/* 1405 */       e.printStackTrace();
/* 1406 */       Utilidades.writeError("AMAccionesFactory:fechaEstimadaTerminacion ", e);
/*      */     } finally {
/*      */       
/* 1409 */       dat.close();
/*      */     } 
/* 1411 */     return "";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean actualizarEstado(int numero, int codigo_estado, String usuario, String justificacion) {
/* 1422 */     String fechaEstimada = fechaEstimadaTerminacion(numero);
/*      */     
/* 1424 */   DBManager  dat = new DBManager();
/*      */     try {
/* 1426 */       String s = "update am_acciones set ";
/* 1427 */       s = s + "justificacion='" + justificacion + "',";
/* 1428 */       s = s + " codigo_estado=" + codigo_estado;
/* 1429 */       if (codigo_estado == 1) {
/* 1430 */         s = s + ",fecha_vigencia=" + Utilidades.getFechaBD();
/* 1431 */         s = s + ",fecha_estimada_terminacion=" + Utilidades.formatoFecha2(fechaEstimada);
/*      */       } 
/* 1433 */       s = s + ",fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 1434 */       s = s + "usuario_modificacion='" + usuario + "'";
/* 1435 */       s = s + " where ";
/* 1436 */       s = s + " numero=" + numero;
/* 1437 */       boolean rta = dat.executeUpdate(s);
/*      */       
/* 1439 */       if (codigo_estado == 1) {
/* 1440 */         s = "update am_causas set estado=1,";
/* 1441 */         s = s + "justificacion='" + justificacion + "',";
/* 1442 */         s = s + "fecha_modificacion=" + Utilidades.getFechaBD() + ",";
/* 1443 */         s = s + "usuario_modificacion='" + usuario + "'";
/* 1444 */         s = s + " where numero=" + numero + " and estado=0";
/* 1445 */         rta = dat.executeUpdate(s);
/*      */       } 
/*      */       
/* 1448 */       return rta;
/*      */     }
/* 1450 */     catch (Exception e) {
/* 1451 */       e.printStackTrace();
/* 1452 */       Utilidades.writeError("AMAccionesFactory:actualizarEstado", e);
/*      */     } finally {
/*      */       
/* 1455 */       dat.close();
/*      */     } 
/* 1457 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean eliminarAreas(int numero) {
/* 1467 */   DBManager  dat = new DBManager();
/*      */     try {
/* 1469 */       String s = "delete from am_acciones_areas where   numero=" + numero;
/*      */ 
/*      */       
/* 1472 */       boolean rta = dat.executeUpdate(s);
/* 1473 */       return rta;
/*      */     }
/* 1475 */     catch (Exception e) {
/* 1476 */       e.printStackTrace();
/* 1477 */       Utilidades.writeError("AMAccionesAreasDAO:eliminarRegistro ", e);
/*      */     } finally {
/*      */       
/* 1480 */       dat.close();
/*      */     } 
/* 1482 */     return false;
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
/*      */   public boolean crearAreas(int numero, int areaImplanta, String usuarioInsercion) {
/* 1495 */    DBManager dat = new DBManager();
/*      */     try {
/* 1497 */       String s = "insert into am_acciones_areas (numero,area_implanta,usuario_insercion,fecha_insercion) values (" + numero + "," + "" + areaImplanta + "," + "'" + usuarioInsercion + "'," + "" + Utilidades.getFechaBD() + "" + ")";
/*      */ 
/*      */ 
 
/* 1508 */       boolean rta = dat.executeUpdate(s);
/* 1509 */       return rta;
/*      */     }
/* 1511 */     catch (Exception e) {
/* 1512 */       e.printStackTrace();
/* 1513 */       Utilidades.writeError("AMAccionesAreasDAO:crearRegistro ", e);
/*      */     } finally {
/*      */       
/* 1516 */       dat.close();
/*      */     } 
/* 1518 */     return false;
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
/*      */   public boolean modificarAreas(int numero, int areaImplanta, String usuarioModificacion) {
/* 1531 */  DBManager   dat = new DBManager();
/*      */     try {
/* 1533 */       String s = "update am_acciones_areas set  usuario_modificacion='" + usuarioModificacion + "'," + " fecha_modificacion=" + Utilidades.getFechaBD() + "" + " where" + " numero=" + numero + " and area_implanta=" + areaImplanta + "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1540 */       boolean rta = dat.executeUpdate(s);
/* 1541 */       return rta;
/*      */     }
/* 1543 */     catch (Exception e) {
/* 1544 */       e.printStackTrace();
/* 1545 */       Utilidades.writeError("AMAccionesAreasDAO:modificarRegistro ", e);
/*      */     } finally {
/*      */       
/* 1548 */       dat.close();
/*      */     } 
/* 1550 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<AMAccionesDTO> cargarAreas(int numero) {
/* 1560 */     Collection<AMAccionesDTO> resultados = new ArrayList<AMAccionesDTO>();
/* 1561 */    DBManager dat = new DBManager();
/*      */     
/*      */     try {
/* 1564 */       String s = "select a.*,u.descripcion as nombre_area from am_acciones_areas a,unidades_dependencia u where a.area_implanta=u.codigo  and a.numero=" + numero + "  order by u.descripcion";
/*      */ 
/*      */ 
/*      */       
/* 1568 */       boolean rtaDB = dat.parseSql(s);
/* 1569 */       if (!rtaDB) {
/* 1570 */         return resultados;
/*      */       }
/* 1572 */       ResultSet rs = dat.getResultSet();
/* 1573 */       while (rs.next())
/*      */       {
/* 1575 */         AMAccionesDTO reg = new AMAccionesDTO();
/* 1576 */         reg.setNumero(rs.getInt("numero"));
/* 1577 */         reg.setAreaImplanta(rs.getInt("area_implanta"));
/* 1578 */         reg.setUsuarioInsercion(rs.getString("usuario_insercion"));
/* 1579 */         reg.setFechaInsercion(rs.getString("fecha_insercion"));
/* 1580 */         reg.setUsuarioModificacion(rs.getString("usuario_modificacion"));
/* 1581 */         reg.setFechaModificacion(rs.getString("fecha_modificacion"));
/* 1582 */         reg.setNombreAreaImplanta(rs.getString("nombre_area"));
/* 1583 */         resultados.add(reg);
/*      */       }
/*      */     
/* 1586 */     } catch (Exception e) {
/* 1587 */       e.printStackTrace();
/* 1588 */       Utilidades.writeError("AMAccionesAreasDAO:cargarTodos ", e);
/*      */     } finally {
/*      */       
/* 1591 */       dat.close();
/*      */     } 
/* 1593 */     return resultados;
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
/*      */   public Collection<AMAccionesDTO> cargarParaNoEfectivas(int area_implanta, String fecha_desde, String fecha_hasta, String accion, String cumplio) {
/* 1610 */     Collection<AMAccionesDTO> resultados = new ArrayList<AMAccionesDTO>();
/*      */     
/* 1612 */     String s = "select a.Numero,a.Fecha_Generada,a.Area_Implanta,a.Empleado_Cliente,a.Codigo_Estado,a.Nivel_Escalamiento,a.Fecha_Vigencia,a.Fecha_Estimada_Terminacion,a.Fecha_Real_Terminacion,a.Fecha_Base_Escalamientos,a.Accion,a.Origen,a.Proceso,a.Subproceso,a.Numeral,a.Descripcion,a.Cumplio,a.Implantada,a.Revisada_Comite,a.Funcionario1,a.Cargo1,a.Fecha1,a.Revisada_Auditor,a.Funcionario2,a.Cargo2,a.Fecha2,a.Norma,a.Usuario_Insercion,a.Fecha_Insercion,a.Usuario_Modificacion,a.Fecha_Modificacion,a.Justificacion,a.Observaciones_Cierre,a.Observaciones_Calidad,a.Fecha_Calidad,a.Tema,a.Impacto,a.Tema_Accion,u.Descripcion as Nombrearea,cli.Nombres,cli.Apellidos,am.Descripcion as Nombreestado,amt.Descripcion as Tipoaccion,ori.Descripcion as Origenaccion,p.Descripcion as Nombreproceso,subp.Descripcion as Nombresubproceso,u.Secuencia,s.Descripcion as Nombre_Impacto,u.Codigo_Tutor,ori.Ver_Jefe_Area,ori.Ver_Gerencia_Calidad,ori.Ver_Otros from Sis_Multivalores s, Am_Acciones a  LEFT JOIN Unidades_Dependencia u on (a.Area_Implanta = u.Codigo) LEFT JOIN sis_usuarios cli on (a.Empleado_Cliente = cli.Codigo_Empleado)  LEFT JOIN Am_Estados am on (a.Codigo_Estado = am.Codigo)  LEFT JOIN Am_Tipo amt on (a.Accion = amt.Codigo)  LEFT JOIN Am_Origen ori on (a.Origen = ori.Codigo)  LEFT JOIN procesos p on (a.Proceso = p.Codigo)   LEFT JOIN subprocesos subp on (a.Proceso = subp.Proceso and a.Subproceso = subp.Codigo)  WHERE a.Impacto = s.Valor and s.Tabla = 'impacto_acciones' and a.area_implanta=" + area_implanta;
/*      */ 

/*      */ 
/*      */     
/* 1676 */     if (fecha_desde.length() > 0) {
/* 1677 */       s = s + " and a.fecha_vigencia>=" + Utilidades.formatoFecha2(fecha_desde);
/*      */     }
/*      */     
/* 1680 */     if (fecha_hasta.length() > 0) {
/* 1681 */       s = s + " and a.fecha_vigencia <" + Utilidades.formatoFecha2(fecha_hasta) + "+1";
/*      */     }
/*      */     
/* 1684 */     if (accion != null && accion.length() > 0) {
/* 1685 */       s = s + " and a.accion='" + accion + "'";
/*      */     }
/* 1687 */     if (cumplio.length() > 0) {
/* 1688 */       s = s + " and a.cumplio='" + cumplio + "'";
/*      */     }
/*      */     
/* 1691 */     s = s + " order by a.numero";
/*      */     
/* 1693 */    DBManager dat = new DBManager();
/*      */     
/*      */     try {
/* 1696 */       boolean rtaDB = dat.parseSql(s);
/* 1697 */       if (!rtaDB) {
/* 1698 */         return resultados;
/*      */       }
/* 1700 */       ResultSet rs = dat.getResultSet();
/* 1701 */       while (rs.next()) {
/* 1702 */         resultados.add(leerRegistro(rs));
/*      */       }
/*      */     }
/* 1705 */     catch (Exception e) {
/* 1706 */       e.printStackTrace();
/* 1707 */       Utilidades.writeError("AMAccionesAreasDAO:cargarParaNoEfectivas ", e);
/*      */     } finally {
/*      */       
/* 1710 */       dat.close();
/*      */     } 
/* 1712 */     return resultados;
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
/*      */   public Collection<AMAccionesDTO> cargarParaNoEfectivasPorCausa(int area_implanta, String fecha_desde, String fecha_hasta, int estado) {
/* 1728 */     Collection<AMAccionesDTO> resultados = new ArrayList<AMAccionesDTO>();
/*      */     
/* 1730 */     String s = "select a.Numero,a.Fecha_Generada,a.Area_Implanta,a.Empleado_Cliente,a.Codigo_Estado,a.Nivel_Escalamiento,a.Fecha_Vigencia,a.Fecha_Estimada_Terminacion,a.Fecha_Real_Terminacion,a.Fecha_Base_Escalamientos,a.Accion,a.Origen,a.Proceso,a.Subproceso,a.Numeral,a.Descripcion,a.Cumplio,a.Implantada,a.Revisada_Comite,a.Funcionario1,a.Cargo1,a.Fecha1,a.Revisada_Auditor,a.Funcionario2,a.Cargo2,a.Fecha2,a.Norma,a.Usuario_Insercion,a.Fecha_Insercion,a.Usuario_Modificacion,a.Fecha_Modificacion,a.Justificacion,a.Observaciones_Cierre,a.Observaciones_Calidad,a.Fecha_Calidad,a.Tema,a.Impacto,a.Tema_Accion,u.Descripcion as Nombrearea,cli.Nombres,cli.Apellidos,am.Descripcion as Nombreestado,amt.Descripcion as Tipoaccion,ori.Descripcion as Origenaccion,p.Descripcion as Nombreproceso,subp.Descripcion as Nombresubproceso,u.Secuencia,s.Descripcion as Nombre_Impacto,u.Codigo_Tutor,ori.Ver_Jefe_Area,ori.Ver_Gerencia_Calidad,ori.Ver_Otros from Sis_Multivalores s, Am_Acciones a  LEFT JOIN Unidades_Dependencia u on (a.Area_Implanta = u.Codigo) LEFT JOIN sis_usuarios cli on (a.Empleado_Cliente = cli.Codigo_Empleado)  LEFT JOIN Am_Estados am on (a.Codigo_Estado = am.Codigo)  LEFT JOIN Am_Tipo amt on (a.Accion = amt.Codigo)  LEFT JOIN Am_Origen ori on (a.Origen = ori.Codigo)  LEFT JOIN procesos p on (a.Proceso = p.Codigo)   LEFT JOIN subprocesos subp on (a.Proceso = subp.Proceso and a.Subproceso = subp.Codigo)  WHERE a.Impacto = s.Valor and s.Tabla = 'impacto_acciones' and a.area_implanta=" + area_implanta;
/*      */ 

/*      */ 
/*      */ 
/*      */     
/* 1796 */     if (fecha_desde.length() > 0) {
/* 1797 */       s = s + " and a.fecha_vigencia>=" + Utilidades.formatoFecha2(fecha_desde);
/*      */     }
/*      */     
/* 1800 */     if (fecha_hasta.length() > 0) {
/* 1801 */       s = s + " and a.fecha_vigencia <" + Utilidades.formatoFecha2(fecha_hasta) + "+1";
/*      */     }
/*      */ 
/*      */     
/* 1805 */     s = s + " and a.numero in (select c.numero from am_causas c where c.estado=" + estado + ")" + " order by a.numero";
/*      */ 
/*      */ 
/*      */     
/* 1809 */    DBManager dat = new DBManager();
/*      */ 
/*      */     
/*      */     try {
/* 1813 */       boolean rtaDB = dat.parseSql(s);
/* 1814 */       if (!rtaDB) {
/* 1815 */         return resultados;
/*      */       }
/* 1817 */       ResultSet rs = dat.getResultSet();
/* 1818 */       while (rs.next()) {
/* 1819 */         resultados.add(leerRegistro(rs));
/*      */       }
/*      */     }
/* 1822 */     catch (Exception e) {
/* 1823 */       e.printStackTrace();
/* 1824 */       Utilidades.writeError("AMAccionesAreasDAO:cargarParaNoEfectivas ", e);
/*      */     } finally {
/*      */       
/* 1827 */       dat.close();
/*      */     } 
/* 1829 */     return resultados;
/*      */   }
/*      */ 

/*      */   
/*      */   public Collection<AMAccionesDTO> cargarDePlan(int ciclo, int plan, int meta, String asociado) {
/* 1844 */     Collection<AMAccionesDTO> resultados = new ArrayList<AMAccionesDTO>();
/*      */     
/* 1846 */    DBManager dat = new DBManager();
/*      */     try {
/* 1848 */       String s = "select t.numero,t.fecha_generada,t.area_implanta,r1.descripcion as nombre_area_implanta,t.empleado_cliente,r2.apellidos ,r2.nombres,t.codigo_estado,r3.descripcion as nombre_codigo_estado,t.nivel_escalamiento,t.fecha_vigencia,t.fecha_estimada_terminacion,t.fecha_real_terminacion,t.fecha_base_escalamientos,t.accion,r4.descripcion as nombre_accion,t.origen,r5.descripcion as nombre_origen,t.proceso,r6.descripcion as nombre_proceso,t.subproceso,r7.descripcion as nombre_subproceso,t.numeral,t.descripcion,t.cumplio,t.implantada,t.revisada_comite,t.funcionario1,t.cargo1,t.fecha1,t.revisada_auditor,t.funcionario2,t.cargo2,t.fecha2,t.norma,r8.descripcion as nombre_norma,t.justificacion,t.observaciones_cierre,t.observaciones_calidad,t.fecha_calidad,t.impacto,m9.descripcion as nombre_impacto,t.tema_accion,t.solicitud_origen,t.codigo_ciclo,t.codigo_plan,t.codigo_meta,t.asociado,t.fecha_insercion,t.usuario_insercion,t.fecha_modificacion,t.usuario_modificacion from am_acciones t  left join Unidades_Dependencia r1 on (r1.codigo=t.area_implanta) left join sis_usuarios r2 on (r2.codigo_empleado=t.empleado_cliente) left join am_estados r3 on (r3.codigo=t.codigo_estado) left join am_tipo r4 on (r4.codigo=t.accion) left join am_origen r5 on (r5.codigo=t.origen) left join procesos r6 on (r6.codigo=t.proceso) left join subprocesos r7 on (r7.codigo=t.subproceso and r7.proceso=t.proceso) left join am_normas r8 on (r8.norma=t.norma) left join sis_multivalores m9 on (m9.tabla='impacto_acciones' and m9.valor=t.impacto) where t.asociado='" + asociado + "'" + "  and t.codigo_plan=" + plan;
/*      */ 

/*      */ 
/*      */       
/* 1914 */       if (asociado.equals("A")) {
/* 1915 */         s = s + "  and t.codigo_estado in(0,1,5)";
/*      */       }
/*      */       
/* 1918 */       if (ciclo != 0) {
/* 1919 */         s = s + "  and t.codigo_ciclo=" + ciclo;
/*      */       }
/* 1921 */       if (meta > 0) {
/* 1922 */         s = s + "  and t.codigo_meta=" + meta;
/*      */       }
/* 1924 */       s = s + " order by 1";
/* 1925 */       boolean rtaDB = dat.parseSql(s);
/* 1926 */       if (!rtaDB) {
/* 1927 */         return resultados;
/*      */       }
/* 1929 */       ResultSet rs = dat.getResultSet();
/* 1930 */       while (rs.next()) {
/* 1931 */         resultados.add(leerRegistro(rs));
/*      */       }
/*      */     }
/* 1934 */     catch (Exception e) {
/* 1935 */       e.printStackTrace();
/* 1936 */       Utilidades.writeError("AMAccionesDAO:cargarTodos ", e);
/*      */     } finally {
/*      */       
/* 1939 */       dat.close();
/*      */     } 
/* 1941 */     return resultados;
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\data\AMAccionesDAO.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */