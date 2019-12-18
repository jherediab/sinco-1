/*      */ package sinco.presentation;
/*      */ 
/*      */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*      */ import com.lutris.util.KeywordValueException;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import org.w3c.dom.Attr;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.html.HTMLElement;
/*      */ import org.w3c.dom.html.HTMLInputElement;
/*      */ import org.w3c.dom.html.HTMLOptionElement;
/*      */ import org.w3c.dom.html.HTMLSelectElement;
/*      */ import org.w3c.dom.html.HTMLTableSectionElement;
/*      */ import sinco.business.PrcProcedimientoDTO;
/*      */ import sinco.business.RespuestaBD;
/*      */ import sinco.business.SisMultiValoresDTO;
/*      */ import sinco.business.TGeneralDTO;
/*      */ import sinco.data.PrcDetalleProcedimientoDAO;
/*      */ import sinco.data.PrcProcedimientoDAO;
/*      */ import sinco.data.PrcRecursoDAO;
/*      */ import sinco.data.SisMultiValoresDAO;
/*      */ import sinco.data.TGeneralDAO;
/*      */ import sinco.presentation.PrcProcedimiento;
/*      */ import sinco.presentation.PrcProcedimientoHTML;
/*      */ import sinco.spec.MenuDO;
/*      */ import sinco.spec.Varios;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PrcProcedimiento
/*      */   implements HttpPresentation
/*      */ {
/*      */   private PrcProcedimientoHTML pagHTML;
/*      */   
/*      */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*   42 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*   43 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*      */     }
/*      */     
/*   46 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*   47 */     String _operacion = comms.request.getParameter("_operacion");
/*   48 */     if (_operacion == null || _operacion.length() == 0) {
/*   49 */       _operacion = "X";
/*      */     }
/*      */     
/*   52 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*   53 */       creacion(comms);
/*      */     }
/*      */     
/*   56 */     this.pagHTML = (PrcProcedimientoHTML)comms.xmlcFactory.create(PrcProcedimientoHTML.class);
/*   57 */     permisos(comms);
/*      */     
/*   59 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*   60 */       listar(comms, _operacion);
/*      */     }
/*      */     
/*   63 */     if (_operacion.equals("P")) {
/*   64 */       editar(comms);
/*      */     }
/*   66 */     else if (_operacion.equals("Nuevo")) {
/*   67 */       nuevo(comms);
/*      */     } 
/*      */     
/*   70 */     if (_operacion.equals("V")) {
/*   71 */       verRegistro(comms);
/*      */     }
/*   73 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*   74 */     comms.response.writeDOM(this.pagHTML);
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
/*      */   private void creacion(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*   86 */     String _operacion = comms.request.getParameter("_operacion");
/*   87 */     String elUsuario = "" + comms.session.getUser().getName();
/*   88 */     int idProcedimiento = 0;
/*      */     try {
/*   90 */       idProcedimiento = Integer.parseInt(comms.request.getParameter("idProcedimiento"));
/*      */     }
/*   92 */     catch (Exception e) {
/*   93 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idProcedimiento"));
/*      */     } 
/*      */     
/*   96 */     RespuestaBD rta = new RespuestaBD();
/*   97 */     if (_operacion.equals("E")) {
/*   98 */       PrcProcedimientoDAO ob = new PrcProcedimientoDAO();
/*   99 */       rta = ob.eliminarRegistro(idProcedimiento);
/*  100 */       if (!rta.isRta()) {
/*  101 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPrcProcedimiento&p1=" + rta.getMensaje()));
/*      */       }
/*  103 */       String sPagina = "PrcProcedimiento.po?_operacion=X";
/*  104 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*      */     } 
/*  106 */     int procesoId = 0;
/*      */     try {
/*  108 */       procesoId = Integer.parseInt(comms.request.getParameter("procesoId"));
/*      */     }
/*  110 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  113 */     int subprocesoId = 0;
/*      */     try {
/*  115 */       subprocesoId = Integer.parseInt(comms.request.getParameter("subprocesoId"));
/*      */     }
/*  117 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  120 */     int servicioUnidadId = 0;
/*      */     try {
/*  122 */       servicioUnidadId = Integer.parseInt(comms.request.getParameter("servicioUnidadId"));
/*      */     }
/*  124 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  127 */     int codigoEmpleado = 0;
/*      */     try {
/*  129 */       codigoEmpleado = Integer.parseInt(comms.request.getParameter("codigoEmpleado"));
/*      */     }
/*  131 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  134 */     String objetivo = comms.request.getParameter("objetivo");
/*  135 */     String alcance = comms.request.getParameter("alcance");
/*  136 */     String definiciones = comms.request.getParameter("definiciones");
/*  137 */     String concepto = comms.request.getParameter("concepto");
/*  138 */     String estado = comms.request.getParameter("estado");
/*      */     
/*  140 */     String PoliticasProc = comms.request.getParameter("PoliticasCant");
/*  141 */     if (PoliticasProc == null) PoliticasProc = "";
/*      */     
/*  143 */     String AntecesoresProc = comms.request.getParameter("AnterioresCant");
/*  144 */     if (AntecesoresProc == null) AntecesoresProc = "";
/*      */     
/*  146 */     String ProveedoresProc = comms.request.getParameter("ProveedoresCant");
/*  147 */     if (ProveedoresProc == null) ProveedoresProc = "";
/*      */     
/*  149 */     String EntradasProc = comms.request.getParameter("EntradasCant");
/*  150 */     if (EntradasProc == null) EntradasProc = "";
/*      */     
/*  152 */     String ClientesProc = comms.request.getParameter("ClientesCant");
/*  153 */     if (ClientesProc == null) ClientesProc = "";
/*      */     
/*  155 */     String SalidasProc = comms.request.getParameter("SalidasCant");
/*  156 */     if (SalidasProc == null) SalidasProc = "";
/*      */     
/*  158 */     String PlanearProc = comms.request.getParameter("PlanearCant");
/*  159 */     if (PlanearProc == null) PlanearProc = "";
/*      */     
/*  161 */     String ResPlanearProc = comms.request.getParameter("RespPlanearCant");
/*  162 */     if (ResPlanearProc == null) ResPlanearProc = "";
/*      */     
/*  164 */     String RegPlanearProc = comms.request.getParameter("RegiPlanearCant");
/*  165 */     if (RegPlanearProc == null) RegPlanearProc = "";
/*      */     
/*  167 */     String HacerProc = comms.request.getParameter("HacerCant");
/*  168 */     if (HacerProc == null) HacerProc = "";
/*      */     
/*  170 */     String ResHacerProc = comms.request.getParameter("RespHacerCant");
/*  171 */     if (ResHacerProc == null) ResHacerProc = "";
/*      */     
/*  173 */     String RegHacerProc = comms.request.getParameter("RegiHacerCant");
/*  174 */     if (RegHacerProc == null) RegHacerProc = "";
/*      */     
/*  176 */     String VerificarProc = comms.request.getParameter("VerificarCant");
/*  177 */     if (VerificarProc == null) VerificarProc = "";
/*      */     
/*  179 */     String ResVerificarProc = comms.request.getParameter("RespVerificarCant");
/*  180 */     if (ResVerificarProc == null) ResVerificarProc = "";
/*      */     
/*  182 */     String RegVerificarProc = comms.request.getParameter("RegiVerificarCant");
/*  183 */     if (RegVerificarProc == null) RegVerificarProc = "";
/*      */     
/*  185 */     String ActuarProc = comms.request.getParameter("ActuarCant");
/*  186 */     if (ActuarProc == null) ActuarProc = "";
/*      */     
/*  188 */     String ResActuarProc = comms.request.getParameter("RespActuarCant");
/*  189 */     if (ResActuarProc == null) ResActuarProc = "";
/*      */     
/*  191 */     String RegActuarProc = comms.request.getParameter("RegiActuarCant");
/*  192 */     if (RegActuarProc == null) RegActuarProc = "";
/*      */     
/*  194 */     String RecursoProc = comms.request.getParameter("RecursoCant");
/*  195 */     if (RecursoProc == null) RecursoProc = "";
/*      */     
/*  197 */     String DescRecursoProc = comms.request.getParameter("DescRecursoCant");
/*  198 */     if (DescRecursoProc == null) DescRecursoProc = "";
/*      */     
/*  200 */     int AnterioresText = 0;
/*      */     try {
/*  202 */       AnterioresText = Integer.parseInt(comms.request.getParameter("AnterioresText"));
/*      */     }
/*  204 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  207 */     int Proveedores = 0;
/*      */     try {
/*  209 */       Proveedores = Integer.parseInt(comms.request.getParameter("Proveedores"));
/*      */     }
/*  211 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  214 */     int Entradas = 0;
/*      */     try {
/*  216 */       Entradas = Integer.parseInt(comms.request.getParameter("Entradas"));
/*      */     }
/*  218 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  221 */     int Clientes = 0;
/*      */     try {
/*  223 */       Clientes = Integer.parseInt(comms.request.getParameter("Clientes"));
/*      */     }
/*  225 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  228 */     int Salidas = 0;
/*      */     try {
/*  230 */       Salidas = Integer.parseInt(comms.request.getParameter("Salidas"));
/*      */     }
/*  232 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  235 */     int codigoEmpleadoDescPlan = 0;
/*      */     try {
/*  237 */       codigoEmpleadoDescPlan = Integer.parseInt(comms.request.getParameter("codigoEmpleadoDescPlan"));
/*      */     }
/*  239 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  242 */     int codigoEmpleadoDescHac = 0;
/*      */     try {
/*  244 */       codigoEmpleadoDescHac = Integer.parseInt(comms.request.getParameter("codigoEmpleadoDescHac"));
/*      */     }
/*  246 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  249 */     int codigoEmpleadoDescVer = 0;
/*      */     try {
/*  251 */       codigoEmpleadoDescVer = Integer.parseInt(comms.request.getParameter("codigoEmpleadoDescVer"));
/*      */     }
/*  253 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  256 */     int codigoEmpleadoDescAct = 0;
/*      */     try {
/*  258 */       codigoEmpleadoDescAct = Integer.parseInt(comms.request.getParameter("codigoEmpleadoDescAct"));
/*      */     }
/*  260 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  263 */     String tipoRecurso = comms.request.getParameter("tipoRecurso");
/*  264 */     PrcProcedimientoDAO ob = new PrcProcedimientoDAO();
/*  265 */     if (_operacion.equals("C")) {
/*  266 */       rta = ob.crearRegistro(idProcedimiento, procesoId, subprocesoId, servicioUnidadId, codigoEmpleado, objetivo, alcance, definiciones, concepto, estado, elUsuario);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  278 */       idProcedimiento = rta.getSecuencia();
/*      */       
/*  280 */       guardarInfoProcedimiento(idProcedimiento, PoliticasProc, AntecesoresProc, ProveedoresProc, EntradasProc, ClientesProc, SalidasProc, PlanearProc, ResPlanearProc, RegPlanearProc, HacerProc, ResHacerProc, RegHacerProc, VerificarProc, ResVerificarProc, RegVerificarProc, ActuarProc, ResActuarProc, RegActuarProc, RecursoProc, DescRecursoProc, 0, elUsuario);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  308 */       rta = ob.modificarRegistro(idProcedimiento, procesoId, subprocesoId, servicioUnidadId, codigoEmpleado, objetivo, alcance, definiciones, concepto, estado, AnterioresText, Proveedores, Entradas, Clientes, Salidas, codigoEmpleadoDescPlan, codigoEmpleadoDescHac, codigoEmpleadoDescVer, codigoEmpleadoDescAct, tipoRecurso, elUsuario);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  331 */       guardarInfoProcedimiento(idProcedimiento, PoliticasProc, AntecesoresProc, ProveedoresProc, EntradasProc, ClientesProc, SalidasProc, PlanearProc, ResPlanearProc, RegPlanearProc, HacerProc, ResHacerProc, RegHacerProc, VerificarProc, ResVerificarProc, RegVerificarProc, ActuarProc, ResActuarProc, RegActuarProc, RecursoProc, DescRecursoProc, 1, elUsuario);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  357 */     if (!rta.isRta()) {
/*  358 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPrcProcedimiento&p1=" + rta.getMensaje()));
/*      */     }
/*      */     
/*  361 */     String sPagina = "PrcProcedimiento.po?_operacion=P&idProcedimiento=" + idProcedimiento + "";
/*  362 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  373 */     int idProcedimiento = 0;
/*      */     try {
/*  375 */       idProcedimiento = Integer.parseInt(comms.request.getParameter("idProcedimiento"));
/*      */     }
/*  377 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  380 */     PrcProcedimientoDAO ob = new PrcProcedimientoDAO();
/*  381 */     PrcProcedimientoDTO reg = ob.cargarRegistro(idProcedimiento);
/*  382 */     if (reg != null) {
/*  383 */       this.pagHTML.getElementIdProcedimiento().setValue("" + reg.getIdProcedimiento());
/*  384 */       this.pagHTML.getElementProcesoId().setValue("" + reg.getProcesoId());
/*  385 */       this.pagHTML.getElementSubprocesoId().setValue("" + reg.getSubprocesoId());
/*  386 */       this.pagHTML.getElementServicioUnidadId().setValue("" + reg.getServicioUnidadId());
/*  387 */       ((HTMLInputElement)this.pagHTML.getElementObjetivo()).setValue("" + reg.getObjetivo());
/*  388 */       ((HTMLInputElement)this.pagHTML.getElementAlcance()).setValue("" + reg.getAlcance());
/*  389 */       ((HTMLInputElement)this.pagHTML.getElementDefiniciones()).setValue("" + reg.getDefiniciones());
/*  390 */       ((HTMLInputElement)this.pagHTML.getElementConcepto()).setValue("" + reg.getConcepto());
/*  391 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/*  392 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/*  393 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/*  394 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/*  395 */       HTMLSelectElement combo = this.pagHTML.getElementCodigoEmpleado();
/*  396 */       llenarCombo(combo, "sis_usuarios", "CODIGO_EMPLEADO", "APELLIDOS", "1=1", "" + reg.getCodigoEmpleado(), true);
/*      */       
/*  398 */       combo = this.pagHTML.getElementEstado();
/*  399 */       comboMultivalores(combo, "estado_activo_inactivo", "" + reg.getEstado(), true);
/*      */       
/*  401 */       combo = this.pagHTML.getElementAnterioresText();
/*  402 */       llenarCombo(combo, "prc_procedimientos", "id_procedimiento", "objetivo", "1=1", "" + reg.getAnterioresText(), true);
/*      */       
/*  404 */       combo = this.pagHTML.getElementProveedores();
/*  405 */       llenarCombo(combo, "prc_proveedor", "id_proveedor", "nombre_proveedor", "1=1", "" + reg.getProveedores(), true);
/*      */       
/*  407 */       combo = this.pagHTML.getElementEntradas();
/*  408 */       llenarCombo(combo, "prc_entrada", "id_entrada", "descripcion_entrada", "1=1", "" + reg.getEntradas(), true);
/*      */       
/*  410 */       combo = this.pagHTML.getElementClientes();
/*  411 */       llenarCombo(combo, "prc_cliente", "id_cliente", "nombre_cliente", "1=1", "" + reg.getClientes(), true);
/*      */       
/*  413 */       combo = this.pagHTML.getElementSalidas();
/*  414 */       llenarCombo(combo, "prc_salida", "id_salida", "desc_salida", "1=1", "" + reg.getSalidas(), true);
/*      */       
/*  416 */       combo = this.pagHTML.getElementCodigoEmpleadoDescPlan();
/*  417 */       llenarCombo(combo, "sis_usuarios", "CODIGO_EMPLEADO", "APELLIDOS", "1=1", "" + reg.getCodigoEmpleadoDescPlan(), true);
/*      */       
/*  419 */       combo = this.pagHTML.getElementCodigoEmpleadoDescHac();
/*  420 */       llenarCombo(combo, "sis_usuarios", "CODIGO_EMPLEADO", "APELLIDOS", "1=1", "" + reg.getCodigoEmpleadoDescHac(), true);
/*      */       
/*  422 */       combo = this.pagHTML.getElementCodigoEmpleadoDescVer();
/*  423 */       llenarCombo(combo, "sis_usuarios", "CODIGO_EMPLEADO", "APELLIDOS", "1=1", "" + reg.getCodigoEmpleadoDescVer(), true);
/*      */       
/*  425 */       combo = this.pagHTML.getElementCodigoEmpleadoDescAct();
/*  426 */       llenarCombo(combo, "sis_usuarios", "CODIGO_EMPLEADO", "APELLIDOS", "1=1", "" + reg.getCodigoEmpleadoDescAct(), true);
/*      */       
/*  428 */       combo = this.pagHTML.getElementTipoRecurso();
/*  429 */       comboMultivalores(combo, "tipo_recurso", "" + reg.getTipoRecurso(), true);
/*      */       
/*  431 */       this.pagHTML.getElementIdProcedimiento().setReadOnly(true);
/*      */       
/*  433 */       this.pagHTML.setTextScriptCarga(cargarScripts(idProcedimiento));
/*      */     } 
/*  435 */     this.pagHTML.getElement_operacion().setValue("M");
/*  436 */     activarVista("nuevo");
/*      */   }
/*      */   
/*      */   private String cargarScripts(int idProcedimiento) {
/*  440 */     PrcProcedimientoDAO procItem = new PrcProcedimientoDAO();
/*  441 */     PrcDetalleProcedimientoDAO procDetDTO = new PrcDetalleProcedimientoDAO();
/*      */ 
/*      */     
/*  444 */     int tablaPoliticas = 0;
/*  445 */     int tablaEncadenamiento = 0;
/*  446 */     int tablaProveedores = 0;
/*  447 */     int tablaClientes = 0;
/*  448 */     int tablaPlanear = 0;
/*  449 */     int tablahacer = 0;
/*  450 */     int tablaVerificar = 0;
/*  451 */     int tablaActuar = 0;
/*  452 */     int tablaRecurso = 0;
/*      */     
/*  454 */     Collection resultados = procItem.cargarPoliticas(idProcedimiento);
/*  455 */     Iterator it = resultados.iterator();
/*  456 */     String script1 = " ";
/*      */     
/*  458 */     while (it.hasNext()) {
/*  459 */       PrcProcedimientoDTO procDTO = (PrcProcedimientoDTO)it.next();
/*  460 */       script1 = script1 + " nuevasPoliticas[" + tablaPoliticas + "]=" + procDTO.getNombrePoliticas().replaceAll("\r\n", " ").replaceAll("\r", " ").replaceAll("\n", " ") + "';";
/*  461 */       tablaPoliticas++;
/*      */     } 
/*      */     
/*  464 */     Collection resultadosEnc = procItem.cargarEncadenamiento(idProcedimiento);
/*  465 */     Iterator itEnc = resultadosEnc.iterator();
/*      */     
/*  467 */     while (itEnc.hasNext()) {
/*  468 */       PrcProcedimientoDTO procDTO = (PrcProcedimientoDTO)itEnc.next();
/*  469 */       script1 = script1 + "nuevosEncadenamientos[" + tablaEncadenamiento + "]=" + procDTO.getAnterioresText();
/*  470 */       tablaEncadenamiento++;
/*      */     } 
/*      */     
/*  473 */     Collection resultadosProv = procItem.cargarProveedoresEntradas(idProcedimiento);
/*  474 */     Iterator itProv = resultadosProv.iterator();
/*      */     
/*  476 */     while (itProv.hasNext()) {
/*  477 */       PrcProcedimientoDTO procDTO = (PrcProcedimientoDTO)itProv.next();
/*  478 */       script1 = script1 + "nuevosProveedoresEntradas[" + tablaProveedores + ",1]=" + procDTO.getProveedores();
/*  479 */       script1 = script1 + "nuevosProveedoresEntradas[" + tablaProveedores + ",2]=" + procDTO.getEntradas();
/*  480 */       tablaProveedores++;
/*      */     } 
/*      */     
/*  483 */     Collection resultadosSal = procItem.cargarClienteSalida(idProcedimiento);
/*  484 */     Iterator itSal = resultadosSal.iterator();
/*      */     
/*  486 */     while (itSal.hasNext()) {
/*  487 */       PrcProcedimientoDTO procDTO = (PrcProcedimientoDTO)itSal.next();
/*  488 */       script1 = script1 + "nuevosProveedoresEntradas[" + tablaClientes + ",1]=" + procDTO.getClientes();
/*  489 */       script1 = script1 + "nuevosProveedoresEntradas[" + tablaClientes + ",2]=" + procDTO.getSalidas();
/*  490 */       tablaClientes++;
/*      */     } 
/*      */     
/*  493 */     Collection resultadosRec = procItem.cargarRecurso(idProcedimiento);
/*  494 */     Iterator itRec = resultadosRec.iterator();
/*      */     
/*  496 */     while (itRec.hasNext()) {
/*  497 */       PrcProcedimientoDTO procDTO = (PrcProcedimientoDTO)itRec.next();
/*  498 */       script1 = script1 + "nuevosProveedoresEntradas[" + tablaRecurso + ",1]=" + procDTO.getTipoRecurso();
/*  499 */       script1 = script1 + "nuevosProveedoresEntradas[" + tablaRecurso + ",2]=" + procDTO.getDescRecurso();
/*  500 */       tablaRecurso++;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  512 */     return "";
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
/*      */   private void nuevo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  524 */     this.pagHTML.getElement_operacion().setValue("C");
/*      */     try {
/*  526 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/*  527 */       sel.getParentNode().removeChild(sel);
/*      */     }
/*  529 */     catch (Exception e) {}
/*      */     
/*  531 */     activarVista("nuevo");
/*  532 */     HTMLSelectElement combo = this.pagHTML.getElementCodigoEmpleado();
/*  533 */     llenarCombo(combo, "sis_usuarios", "CODIGO_EMPLEADO", "APELLIDOS", "1=1", "", true);
/*      */     
/*  535 */     combo = this.pagHTML.getElementEstado();
/*  536 */     comboMultivalores(combo, "estado_activo_inactivo", "", true);
/*      */     
/*  538 */     combo = this.pagHTML.getElementAnterioresText();
/*  539 */     llenarCombo(combo, "prc_procedimientos", "id_procedimiento", "objetivo", "1=1", "", true);
/*      */     
/*  541 */     combo = this.pagHTML.getElementProveedores();
/*  542 */     llenarCombo(combo, "prc_proveedor", "id_proveedor", "nombre_proveedor", "1=1", "", true);
/*      */     
/*  544 */     combo = this.pagHTML.getElementEntradas();
/*  545 */     llenarCombo(combo, "prc_entrada", "id_entrada", "descripcion_entrada", "1=1", "", true);
/*      */     
/*  547 */     combo = this.pagHTML.getElementClientes();
/*  548 */     llenarCombo(combo, "prc_cliente", "id_cliente", "nombre_cliente", "1=1", "", true);
/*      */     
/*  550 */     combo = this.pagHTML.getElementSalidas();
/*  551 */     llenarCombo(combo, "prc_salida", "id_salida", "desc_salida", "1=1", "", true);
/*      */     
/*  553 */     combo = this.pagHTML.getElementCodigoEmpleadoDescPlan();
/*  554 */     llenarCombo(combo, "sis_usuarios", "CODIGO_EMPLEADO", "APELLIDOS", "1=1", "", true);
/*      */     
/*  556 */     combo = this.pagHTML.getElementCodigoEmpleadoDescHac();
/*  557 */     llenarCombo(combo, "sis_usuarios", "CODIGO_EMPLEADO", "APELLIDOS", "1=1", "", true);
/*      */     
/*  559 */     combo = this.pagHTML.getElementCodigoEmpleadoDescVer();
/*  560 */     llenarCombo(combo, "sis_usuarios", "CODIGO_EMPLEADO", "APELLIDOS", "1=1", "", true);
/*      */     
/*  562 */     combo = this.pagHTML.getElementCodigoEmpleadoDescAct();
/*  563 */     llenarCombo(combo, "sis_usuarios", "CODIGO_EMPLEADO", "APELLIDOS", "1=1", "", true);
/*      */     
/*  565 */     combo = this.pagHTML.getElementTipoRecurso();
/*  566 */     comboMultivalores(combo, "tipo_recurso", "", true);
/*      */     
/*  568 */     this.pagHTML.getElementIdProcedimiento().setReadOnly(true);
/*  569 */     this.pagHTML.getElementIdProcedimiento().setValue("0");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
/*  580 */     activarVista("consulta");
/*  581 */     int idProcedimiento = 0;
/*      */     try {
/*  583 */       idProcedimiento = Integer.parseInt(comms.request.getParameter("idProcedimiento"));
/*      */     }
/*  585 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  588 */     int codigoEmpleado = 0;
/*      */     try {
/*  590 */       codigoEmpleado = Integer.parseInt(comms.request.getParameter("codigoEmpleado"));
/*      */     }
/*  592 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  595 */     String objetivo = comms.request.getParameter("objetivo");
/*  596 */     String alcance = comms.request.getParameter("alcance");
/*  597 */     HTMLSelectElement combo = this.pagHTML.getElementFcodigoEmpleado();
/*  598 */     llenarCombo(combo, "sis_usuarios", "CODIGO_EMPLEADO", "APELLIDOS", "1=1", "" + codigoEmpleado, true);
/*  599 */     if (_operacion.equals("X")) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  604 */     PrcProcedimientoDAO ob = new PrcProcedimientoDAO();
/*  605 */     Collection<PrcProcedimientoDTO> arr = ob.cargarTodos(idProcedimiento, codigoEmpleado, objetivo, alcance);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  610 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/*  611 */     int cuantas = 0;
/*  612 */     Iterator<PrcProcedimientoDTO> iterator = arr.iterator();
/*  613 */     while (iterator.hasNext()) {
/*  614 */       PrcProcedimientoDTO reg = (PrcProcedimientoDTO)iterator.next();
/*  615 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  616 */       String url = "PrcProcedimiento.po?_operacion=V&idProcedimiento=" + reg.getIdProcedimiento() + "";
/*  617 */       eltr.appendChild(newtdhref("" + reg.getIdProcedimiento(), url));
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  622 */       eltr.appendChild(newtd("" + reg.getObjetivo()));
/*  623 */       eltr.appendChild(newtd("" + reg.getAlcance()));
/*  624 */       eltr.appendChild(newtd("" + reg.getDefiniciones()));
/*  625 */       eltr.appendChild(newtd("" + reg.getConcepto()));
/*  626 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  637 */       hte.appendChild(eltr);
/*  638 */       cuantas++;
/*      */     } 
/*  640 */     arr.clear();
/*  641 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/*      */   private void verRegistro(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  654 */     int idProcedimiento = 0;
/*      */     try {
/*  656 */       idProcedimiento = Integer.parseInt(comms.request.getParameter("idProcedimiento"));
/*      */     }
/*  658 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  661 */     PrcProcedimientoDAO ob = new PrcProcedimientoDAO();
/*  662 */     PrcProcedimientoDTO reg = ob.cargarRegistro(idProcedimiento);
/*  663 */     if (reg != null) {
/*  664 */       this.pagHTML.setTextIdProcedimientoEd("" + reg.getIdProcedimiento());
/*  665 */       this.pagHTML.setTextProcesoIdEd("" + reg.getProcesoId());
/*  666 */       this.pagHTML.setTextSubprocesoIdEd("" + reg.getSubprocesoId());
/*  667 */       this.pagHTML.setTextServicioUnidadIdEd("" + reg.getServicioUnidadId());
/*  668 */       this.pagHTML.setTextCodigoEmpleadoEd("" + reg.getNombreCodigoEmpleado());
/*  669 */       this.pagHTML.setTextObjetivoEd("" + reg.getObjetivo());
/*  670 */       this.pagHTML.setTextAlcanceEd("" + reg.getAlcance());
/*  671 */       this.pagHTML.setTextDefinicionesEd("" + reg.getDefiniciones());
/*  672 */       this.pagHTML.setTextConceptoEd("" + reg.getConcepto());
/*  673 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/*  674 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/*  675 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/*  676 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*  677 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/*      */       
/*  679 */       this.pagHTML.getElementIdProcedimientoKey().setValue("" + reg.getIdProcedimiento());
/*  680 */       this.pagHTML.getElement_operacion().setValue("P");
/*      */     } 
/*  682 */     activarVista("editar");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  693 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*      */     
/*  695 */     Varios oVarios = new Varios();
/*  696 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "sincoPrcProcedimientoAct");
/*  697 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "sincoPrcProcedimientoDel");
/*  698 */     if (!oPermisoAct) {
/*  699 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/*  700 */       elem.getParentNode().removeChild(elem);
/*  701 */       elem = this.pagHTML.getElementBtnGrabar();
/*  702 */       elem.getParentNode().removeChild(elem);
/*  703 */       elem = this.pagHTML.getElementBtnModificar();
/*  704 */       elem.getParentNode().removeChild(elem);
/*      */     } 
/*  706 */     if (!oPermisoDel) {
/*  707 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/*  708 */       elem.getParentNode().removeChild(elem);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void activarVista(String vista) {
/*  719 */     if (!vista.equals("nuevo")) {
/*  720 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/*  721 */       sel.getParentNode().removeChild(sel);
/*      */     } 
/*  723 */     if (!vista.equals("editar")) {
/*  724 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/*  725 */       sel.getParentNode().removeChild(sel);
/*      */     } 
/*  727 */     if (!vista.equals("consulta")) {
/*  728 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/*  729 */       sel.getParentNode().removeChild(sel);
/*  730 */       sel = this.pagHTML.getElementDivResultados();
/*      */ 
/*      */       
/*  733 */       sel.getParentNode().removeChild(sel);
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
/*      */   private Attr newAttr(String tipo, String valor) {
/*  747 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/*  748 */     atrib.setValue(valor);
/*  749 */     return atrib;
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
/*      */   private HTMLElement newtdhref(String contenido, String vinculo) {
/*  762 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  763 */     Element enlace = this.pagHTML.createElement("a");
/*  764 */     Node hijo = this.pagHTML.createTextNode(contenido);
/*  765 */     enlace.appendChild(hijo);
/*  766 */     Attr donde = this.pagHTML.createAttribute("href");
/*  767 */     donde.setValue(vinculo);
/*  768 */     enlace.setAttributeNode(donde);
/*  769 */     td.appendChild(enlace);
/*  770 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  771 */     return td;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private HTMLElement newtd(String contenido) {
/*  781 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  782 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  783 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  784 */     return td;
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
/*      */   private void guardarInfoProcedimiento(int idProcedimiento, String PoliticasProc, String AntecesoresProc, String ProveedoresProc, String EntradasProc, String ClientesProc, String SalidasProc, String PlanearProc, String ResPlanearProc, String RegPlanearProc, String HacerProc, String ResHacerProc, String RegHacerProc, String VerificarProc, String ResVerificarProc, String RegVerificarProc, String ActuarProc, String ResActuarProc, String RegActuarProc, String RecursoProc, String DescRecursoProc, int operacion, String elUsuario) {
/*  812 */     PrcProcedimientoDAO ppd = new PrcProcedimientoDAO();
/*  813 */     if (operacion == 1);
/*      */ 
/*      */ 
/*      */     
/*  817 */     String[] items = PoliticasProc.split("~");
/*  818 */     for (int i = 0; i < items.length; i++) {
/*  819 */       ppd.crearRegistroRompimiento2("prc_procedimiento_politica", idProcedimiento, items[i]);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  825 */     if (!AntecesoresProc.equals("")) {
/*  826 */       items = AntecesoresProc.split("~");
/*  827 */       for (int i = 0; i < items.length; i++) {
/*  828 */         ppd.crearRegistroRompimiento2Int("prc_procedimiento_proc", Integer.parseInt(items[i]), idProcedimiento);
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  836 */     items = ProveedoresProc.split("~");
/*  837 */     String[] complemento = EntradasProc.split("~");
/*  838 */     for (int i = 0; i < items.length; i++) {
/*  839 */       ppd.crearRegistroRompimiento3("prc_proveedor_entrada", idProcedimiento, Integer.parseInt(items[i]), Integer.parseInt(complemento[i]));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  847 */     items = ClientesProc.split("~");
/*  848 */     complemento = SalidasProc.split("~");
/*  849 */     for (int i = 0; i < items.length; i++) {
/*  850 */       ppd.crearRegistroRompimiento3("prc_cliente_salida", idProcedimiento, Integer.parseInt(items[i]), Integer.parseInt(complemento[i]));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  858 */     PrcDetalleProcedimientoDAO pdp = new PrcDetalleProcedimientoDAO();
/*  859 */     items = PlanearProc.split("~");
/*  860 */     complemento = ResPlanearProc.split("~");
/*  861 */     String[] complemento1 = RegPlanearProc.split("~");
/*  862 */     for (int i = 0; i < items.length; i++) {
/*  863 */       pdp.crearRegistro(0, "Planear", idProcedimiento, items[i], Integer.parseInt(complemento[i]), complemento1[i], "A", elUsuario);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  874 */     items = HacerProc.split("~");
/*  875 */     complemento = ResHacerProc.split("~");
/*  876 */     String[] complemento2 = RegHacerProc.split("~");
/*  877 */     for (int i = 0; i < items.length; i++) {
/*  878 */       pdp.crearRegistro(0, "Hacer", idProcedimiento, items[i], Integer.parseInt(complemento[i]), complemento2[i], "A", elUsuario);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  889 */     items = VerificarProc.split("~");
/*  890 */     complemento = ResVerificarProc.split("~");
/*  891 */     String[] complemento3 = RegVerificarProc.split("~");
/*  892 */     for (int i = 0; i < items.length; i++) {
/*  893 */       pdp.crearRegistro(0, "Verificar", idProcedimiento, items[i], Integer.parseInt(complemento[i]), complemento3[i], "A", elUsuario);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  904 */     items = ActuarProc.split("~");
/*  905 */     complemento = ResVerificarProc.split("~");
/*  906 */     String[] complemento4 = RegActuarProc.split("~");
/*  907 */     for (int i = 0; i < items.length; i++) {
/*  908 */       pdp.crearRegistro(0, "Actuar", idProcedimiento, items[i], Integer.parseInt(complemento[i]), complemento4[i], "A", elUsuario);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  919 */     PrcRecursoDAO prd = new PrcRecursoDAO();
/*  920 */     items = RecursoProc.split("~");
/*  921 */     complemento = DescRecursoProc.split("~");
/*  922 */     for (int i = 0; i < items.length; i++) {
/*  923 */       prd.crearRegistro(0, items[i], complemento[i], idProcedimiento, "A", elUsuario);
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
/*      */   private void comboMultivalores(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco) {
/*  946 */     SisMultiValoresDAO ob = new SisMultiValoresDAO();
/*  947 */     Collection<SisMultiValoresDTO> arr = ob.cargarTabla(tabla);
/*  948 */     ob.close();
/*  949 */     if (dejarBlanco) {
/*  950 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  951 */       op.setValue("");
/*  952 */       op.appendChild(this.pagHTML.createTextNode(""));
/*  953 */       combo.appendChild(op);
/*      */     } 
/*  955 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/*  956 */     while (iterator.hasNext()) {
/*  957 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/*  958 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  959 */       op.setValue("" + reg.getCodigo());
/*  960 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/*  961 */       if (defecto.equals(reg.getCodigo())) {
/*  962 */         Attr escogida = this.pagHTML.createAttribute("selected");
/*  963 */         escogida.setValue("on");
/*  964 */         op.setAttributeNode(escogida);
/*      */       } 
/*  966 */       combo.appendChild(op);
/*      */     } 
/*  968 */     arr.clear();
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
/*      */   private void llenarCombo(HTMLSelectElement combo, String tabla, String codigo, String descripcion, String condicion, String defecto, boolean dejarBlanco) {
/*  989 */     if (dejarBlanco) {
/*  990 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  991 */       op.setValue("");
/*  992 */       op.appendChild(this.pagHTML.createTextNode(""));
/*  993 */       combo.appendChild(op);
/*      */     } 
/*  995 */     TGeneralDAO rsTGen = new TGeneralDAO();
/*  996 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/*  997 */     rsTGen.close();
/*  998 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/*  999 */     while (iterator.hasNext()) {
/* 1000 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 1001 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1002 */       op.setValue("" + regGeneral.getCodigoS());
/* 1003 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 1004 */       if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
/* 1005 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 1006 */         escogida.setValue("on");
/* 1007 */         op.setAttributeNode(escogida);
/*      */       } 
/* 1009 */       combo.appendChild(op);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PrcProcedimiento.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */