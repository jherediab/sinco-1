/*      */ package sinco.presentation;
/*      */ 
/*      */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*      */ import com.lutris.util.KeywordValueException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Iterator;
/*      */ import java.util.StringTokenizer;
/*      */ import org.w3c.dom.Attr;
/*      */ import org.w3c.dom.html.HTMLElement;
/*      */ import org.w3c.dom.html.HTMLInputElement;
/*      */ import org.w3c.dom.html.HTMLOptionElement;
/*      */ import org.w3c.dom.html.HTMLSelectElement;
/*      */ import org.w3c.dom.html.HTMLTableElement;
/*      */ import sinco.business.AMAccionesDTO;
/*      */ import sinco.business.AreasDTO;
/*      */ import sinco.business.CalMetasDTO;
/*      */ import sinco.business.CalObjetivosDTO;
/*      */ import sinco.business.FechaDTO;
/*      */ import sinco.business.ParametrosDTO;
/*      */ import sinco.business.RespuestaBD;
/*      */ import sinco.business.SisMultiValoresDTO;
/*      */ import sinco.business.SisUsuariosDTO;
/*      */ import sinco.business.TGeneralDTO;
/*      */ import sinco.business.Utilidades;
/*      */ import sinco.data.AMAccionesDAO;
/*      */ import sinco.data.AMCausasDAO;
/*      */ import sinco.data.AreasDAO;
/*      */ import sinco.data.CalLogrosDAO;
/*      */ import sinco.data.CalPlanMetasDAO;
/*      */ import sinco.data.CalPlanObjetivosDAO;
/*      */ import sinco.data.SeguridadDAO;
/*      */ import sinco.data.SisMultiValoresDAO;
/*      */ import sinco.data.SisUsuariosDAO;
/*      */ import sinco.data.TGeneralDAO;
/*      */ import sinco.presentation.AMActV2;
/*      */ import sinco.presentation.AMActV2HTML;
/*      */ import sinco.spec.MenuDO;
/*      */ import sinco.spec.Varios;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class AMActV2
/*      */   implements HttpPresentation
/*      */ {
/*      */   private AMActV2HTML pagHTML;
/*      */   
/*      */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*   57 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*   58 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*      */     }
/*      */     
/*   61 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*      */     
/*   63 */     SisUsuariosDAO pf = new SisUsuariosDAO();
/*   64 */     SisUsuariosDTO p = pf.cargarRegistro(idNav);
/*      */     
/*   66 */     String operacion = comms.request.getParameter("_operacion");
/*   67 */     String operacionOld = operacion;
/*   68 */     if (operacion == null || operacion.length() == 0) {
/*   69 */       operacion = "";
/*      */     }
/*   71 */     int numero = 0;
/*      */     
/*   73 */     if (operacion.equals("M") || operacion.equals("E") || operacion.equals("P") || operacion.equals("D") || operacion.equals("A") || operacion.equals("R") || operacion.equals("F") || operacion.equals("OBSGERENCIA") || operacion.equals("FINALIZA") || operacion.equals("CALIFICAR")) {
/*      */       
/*      */       try {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*   84 */         numero = Integer.parseInt(comms.request.getParameter("numero"));
/*      */       }
/*   86 */       catch (Exception e) {
/*   87 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=numero"));
/*      */       } 
/*      */     }
/*   90 */     int recarga = 0;
/*      */     try {
/*   92 */       recarga = Integer.parseInt(comms.request.getParameter("recarga"));
/*      */     }
/*   94 */     catch (Exception e) {}
/*      */     
/*   96 */     if (recarga == 1) {
/*   97 */       operacion = "RECARGA";
/*      */     }
/*      */ 
/*      */     
/*  101 */     if (operacion.equals("C") || operacion.equals("M")) {
/*  102 */       creacion(comms, operacion, numero, idNav, p.getCodigoEmpleado(), p.getArea());
/*      */     }
/*  104 */     else if (operacion.equals("A") || operacion.equals("R")) {
/*  105 */       cambioEstado(comms, operacion, numero, idNav);
/*      */     }
/*  107 */     else if (operacion.equals("FINALIZA")) {
/*  108 */       finaliza(comms, numero, idNav, p.getCodigoEmpleado());
/*      */     }
/*  110 */     else if (operacion.equals("CALIFICAR")) {
/*  111 */       calificar(comms, numero, idNav);
/*      */     }
/*  113 */     else if (operacion.equals("OBSGERENCIA")) {
/*  114 */       observacionesGerencia(comms, numero, idNav);
/*      */     } 
/*      */     
/*  117 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*  118 */     SeguridadDAO rsSeguridad = new SeguridadDAO();
/*  119 */     boolean oTodaEntidad = rsSeguridad.tieneLlave(miGrupo, "AM_Permiso.crear.la.compania");
/*  120 */     boolean oVariasAreas = rsSeguridad.tieneLlave(miGrupo, "AM_Permiso.crear.varias.areas");
/*  121 */     rsSeguridad.close();
/*      */     
/*  123 */     AreasDAO areaf = new AreasDAO();
/*  124 */     AreasDTO regAreaNav = areaf.getArea(p.getArea());
/*  125 */     areaf.close();
/*      */     
/*  127 */     this.pagHTML = (AMActV2HTML)comms.xmlcFactory.create(AMActV2HTML.class);
/*      */ 
/*      */     
/*  130 */     if (operacion.equals("P")) {
/*  131 */       editar(comms, numero, p.getArea(), p.getCodigoEmpleado(), oTodaEntidad, oVariasAreas, "N");
/*      */     
/*      */     }
/*  134 */     else if (operacion.equals("RECARGA")) {
/*  135 */       recarga(comms, numero, operacionOld, p.getCodigoEmpleado(), oTodaEntidad, oVariasAreas, "N");
/*      */     } else {
/*      */       
/*  138 */       nuevo(comms, p.getArea(), p.getCodigoEmpleado(), oTodaEntidad, oVariasAreas, "N");
/*      */     } 
/*      */     
/*  141 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  142 */     comms.response.writeDOM(this.pagHTML);
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
/*      */   private void observacionesGerencia(HttpPresentationComms comms, int numero, int idNav) throws HttpPresentationException, KeywordValueException {
/*  158 */     String elUsuario = "" + comms.session.getUser().getName();
/*      */     
/*  160 */     AMAccionesDAO rs = new AMAccionesDAO();
/*      */     
/*  162 */     String justificacion = comms.request.getParameter("observacionGC");
/*  163 */     boolean rta = rs.observacionesGerencia(numero, justificacion, elUsuario);
/*  164 */     rs.crearSeguimiento(numero, 0, justificacion, idNav, 'S', elUsuario);
/*  165 */     if (!rta) {
/*  166 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorActualizandoTabla&p1=AccionesMejoramiento"));
/*      */     }
/*  168 */     String sPagina = "AMDetalleV2.po?numero=" + numero;
/*  169 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/*      */   private void calificar(HttpPresentationComms comms, int numero, int idNav) throws HttpPresentationException, KeywordValueException {
/*  183 */     String elUsuario = "" + comms.session.getUser().getName();
/*      */     
/*  185 */     String cumplio = comms.request.getParameter("cumplio");
/*  186 */     String funcionario1 = comms.request.getParameter("funcionario1");
/*  187 */     String cargo1 = comms.request.getParameter("cargo1");
/*  188 */     String fecha1 = comms.request.getParameter("fecha1");
/*  189 */     String justificacion = comms.request.getParameter("justificacion");
/*      */     
/*  191 */     AMAccionesDAO rs = new AMAccionesDAO();
/*  192 */     boolean rta = rs.cerrarAccion(numero, cumplio, cumplio, "S", funcionario1, cargo1, fecha1, "N", "", "", "", justificacion, elUsuario);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  207 */     rs.crearSeguimiento(numero, 0, "Acción de mejora calificada", idNav, 'S', elUsuario);
/*  208 */     if (!rta) {
/*  209 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorActualizandoTabla&p1=AccionesMejoramiento"));
/*      */     }
/*  211 */     String sPagina = "AMDetalleV2.po?numero=" + numero;
/*  212 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/*      */   private void finaliza(HttpPresentationComms comms, int numero, int idNav, int codigoEmpleado) throws HttpPresentationException, KeywordValueException {
/*  227 */     String elUsuario = "" + comms.session.getUser().getName();
/*      */     
/*  229 */     AMAccionesDAO rs = new AMAccionesDAO();
/*  230 */     boolean rta = rs.actualizarEstado(numero, 5, elUsuario, "Finaliza");
/*  231 */     rs.crearSeguimiento(numero, 0, "Finaliza acción de mejora", idNav, 'S', elUsuario);
/*  232 */     AMAccionesDTO reg = rs.cargarRegistro(numero);
/*      */     
/*  234 */     if (!rta) {
/*  235 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorActualizandoTabla&p1=AccionesMejoramiento"));
/*      */     }
/*      */     
/*  238 */     Varios oVarios = new Varios();
/*  239 */     SisUsuariosDTO recipiente = null;
/*      */     
/*  241 */     if (recipiente == null) {
/*  242 */       int primerActivo = oVarios.getPrimerActivo(reg.getAreaImplanta(), reg.getEmpleadoCliente(), "I", "N");
/*  243 */       if (primerActivo != -1) {
/*  244 */         recipiente = oVarios.leerPersona(primerActivo);
/*      */       } else {
/*      */         
/*  247 */         recipiente = oVarios.getJefeArea(reg.getAreaImplanta());
/*      */       } 
/*      */     } 
/*      */     
/*  251 */     generarCorreoJefe(numero, codigoEmpleado, reg.getEmpleadoCliente(), reg.getAreaImplanta(), recipiente);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  259 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("AMDetalleV2.po?numero=" + numero));
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
/*      */   private void cambioEstado(HttpPresentationComms comms, String operacion, int numero, int idNav) throws HttpPresentationException, KeywordValueException {
/*  277 */     String elUsuario = "" + comms.session.getUser().getName();
/*      */     
/*  279 */     AMAccionesDAO rs = new AMAccionesDAO();
/*      */     
/*  281 */     int codigoEstado = operacion.equals("A") ? 4 : 1;
/*      */     
/*  283 */     String justificacion = "" + comms.request.getParameter("justificacion");
/*  284 */     boolean rta = rs.actualizarEstado(numero, codigoEstado, elUsuario, justificacion);
/*  285 */     rs.crearSeguimiento(numero, 0, "Registro", idNav, 'S', elUsuario);
/*      */     
/*  287 */     if (!rta) {
/*  288 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorActualizandoTabla&p1=AccionesMejoramiento"));
/*      */     }
/*  290 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("AMDetalleV2.po?numero=" + numero));
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
/*      */   private void creacion(HttpPresentationComms comms, String operacion, int numero, int idNav, int codigoEmpleado, int areaNavega) throws HttpPresentationException, KeywordValueException {
/*  312 */     int area = Integer.parseInt(comms.request.getParameter("area"));
/*  313 */     String impacto = comms.request.getParameter("impacto");
/*      */     
/*  315 */     String elUsuario = "" + comms.session.getUser().getName();
/*      */     
/*  317 */     String objetivo = comms.request.getParameter("objetivo");
/*  318 */     String origen = comms.request.getParameter("origen");
/*  319 */     String proceso = comms.request.getParameter("proceso");
/*  320 */     String subproceso = "" + comms.request.getParameter("subproceso");
/*  321 */     String numeral = comms.request.getParameter("numeral");
/*  322 */     String norma = comms.request.getParameter("norma");
/*  323 */     String descripcion = comms.request.getParameter("descripcion");
/*  324 */     String justificacion = "" + comms.request.getParameter("justificacion");
/*  325 */     String tema = comms.request.getParameter("tema");
/*      */ 
/*      */     
/*  328 */     RespuestaBD rta = new RespuestaBD();
/*  329 */     AMAccionesDAO rsAcciones = new AMAccionesDAO();
/*  330 */     if (operacion.equals("C")) {
/*      */       
/*  332 */       int codigo_estado = 1;
/*      */       
/*  334 */       int plan = 0;
/*      */       try {
/*  336 */         plan = Integer.parseInt(comms.request.getParameter("plan"));
/*      */       }
/*  338 */       catch (Exception e) {}
/*      */ 
/*      */       
/*  341 */       String asociado = "";
/*  342 */       int ciclo = 0;
/*      */       try {
/*  344 */         ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*  345 */         asociado = comms.request.getParameter("asociado");
/*      */       
/*      */       }
/*  348 */       catch (Exception e) {}
/*      */ 
/*      */       
/*  351 */       int meta = 0;
/*      */       try {
/*  353 */         meta = Integer.parseInt(comms.request.getParameter("meta"));
/*      */       }
/*  355 */       catch (Exception e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  360 */       String porque = comms.request.getParameter("porque");
/*  361 */       String accion = comms.request.getParameter("accion");
/*  362 */       String beneficio = comms.request.getParameter("beneficio");
/*  363 */       int responsable = 0;
/*      */       try {
/*  365 */         responsable = Integer.parseInt(comms.request.getParameter("responsable"));
/*  366 */       } catch (Exception e) {}
/*      */       
/*  368 */       String fechaEstimadaTerminacion = comms.request.getParameter("fechaEstimadaTerminacion");
/*      */ 
/*      */       
/*  371 */       rta = rsAcciones.crearRegistro(area, codigoEmpleado, codigo_estado, objetivo, origen, proceso, subproceso, norma, numeral, descripcion, tema, fechaEstimadaTerminacion, impacto, ciclo, plan, meta, asociado, elUsuario);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  391 */       if (rta.isRta())
/*      */       {
/*  393 */         numero = rta.getSecuencia();
/*      */         
/*  395 */         rsAcciones.crearSeguimiento(numero, 0, "Creación", idNav, 'S', elUsuario);
/*      */         
/*  397 */         String codigoLogro = comms.request.getParameter("codigoLogro");
/*  398 */         if (codigoLogro != null) {
/*  399 */           actualizarLogro(numero, codigoLogro, elUsuario);
/*      */         }
/*      */         
/*  402 */         AMCausasDAO rsAMCausas = new AMCausasDAO();
/*  403 */         if (!rsAMCausas.getEstadoConexion()) {
/*  404 */           throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */         }
/*      */         
/*  407 */         rta = rsAMCausas.crearRegistro(numero, porque, accion, beneficio, responsable, fechaEstimadaTerminacion, 1, elUsuario);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  419 */         rsAMCausas.close();
/*      */       }
/*      */     
/*      */     }
/*  423 */     else if (operacion.equals("M")) {
/*  424 */       rta = rsAcciones.modificarRegistro(numero, area, objetivo, origen, proceso, subproceso, norma, numeral, descripcion, justificacion, tema, impacto, elUsuario);
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
/*  440 */     if (!rta.isRta()) {
/*  441 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorActualizandoTabla&p1=AccionesMejoramiento"));
/*      */     }
/*      */     
/*  444 */     if (impacto.equals("V")) {
/*  445 */       cargarAreasAccion(comms, numero);
/*      */     }
/*      */     
/*  448 */     String sPagina = "AMDetalleV2.po?numero=" + numero;
/*  449 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/*      */   private void nuevo(HttpPresentationComms comms, int area, int codigoEmpleado, boolean oTodaEntidad, boolean oVariasAreas, String liderAcciones) throws HttpPresentationException, KeywordValueException {
/*  472 */     int miArea = Integer.parseInt((String)comms.session.getSessionData().get("miArea"));
/*  473 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  474 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*      */     
/*  476 */     SeguridadDAO rsSeguridad = new SeguridadDAO();
/*  477 */     if (!rsSeguridad.getEstadoConexion()) {
/*  478 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */     }
/*  480 */     boolean mostrarTodos = rsSeguridad.tieneLlave(miGrupo, "am_mostrarPersonasArea");
/*  481 */     rsSeguridad.close();
/*      */ 
/*      */     
/*  484 */     int plan = 0;
/*      */     try {
/*  486 */       plan = Integer.parseInt(comms.request.getParameter("plan"));
/*      */     }
/*  488 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  491 */     int ciclo = 0;
/*  492 */     String asociado = "";
/*      */     try {
/*  494 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*  495 */       asociado = comms.request.getParameter("asociado");
/*      */     }
/*  497 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  500 */     int meta = 0;
/*      */     try {
/*  502 */       meta = Integer.parseInt(comms.request.getParameter("meta"));
/*      */     }
/*  504 */     catch (Exception e) {}
/*      */ 
/*      */ 
/*      */     
/*  508 */     CalObjetivosDTO objetivo = null;
/*  509 */     if (meta != 0) {
/*  510 */       CalPlanMetasDAO rsMetas = new CalPlanMetasDAO();
/*  511 */       CalMetasDTO regMeta = rsMetas.cargarRegistro(ciclo, plan, meta);
/*  512 */       rsMetas.close();
/*      */       
/*  514 */       if (regMeta != null) {
/*  515 */         this.pagHTML.getElementDescripcion().appendChild(this.pagHTML.createTextNode("" + regMeta.getDescripcion()));
/*      */         
/*  517 */         CalPlanObjetivosDAO rsobjetivo = new CalPlanObjetivosDAO();
/*  518 */         objetivo = rsobjetivo.cargarRegistro(ciclo, plan, regMeta.getCodigoObjetivo());
/*  519 */         rsobjetivo.close();
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  525 */     TGeneralDAO rsTGen = new TGeneralDAO();
/*  526 */     if (!rsTGen.getEstadoConexion()) {
/*  527 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */     }
/*  529 */     HTMLSelectElement combo = this.pagHTML.getElementObjetivo();
/*  530 */     llenarCombo(rsTGen, combo, "am_tipo", "codigo", "descripcion", "", "CODIGO<>'R'", true);
/*      */     
/*  532 */     combo = this.pagHTML.getElementOrigen();
/*  533 */     comboOrigen(rsTGen, combo, "", miArea, true);
/*      */     
/*  535 */     combo = this.pagHTML.getElementProceso();
/*  536 */     String c = "estado='A'";
/*  537 */     if (objetivo != null) {
/*  538 */       c = c + " and codigo='" + objetivo.getProceso() + "'";
/*      */     }
/*  540 */     c = c + " order by codigo";
/*      */     
/*  542 */     llenarCombo(rsTGen, combo, "procesos", "codigo", "descripcion", "01", c, false);
/*      */     
/*  544 */     combo = this.pagHTML.getElementNorma();
/*  545 */     llenarCombo(rsTGen, combo, "am_normas", "norma", "descripcion", "ISO-9001", "estado='A' order by descripcion", true);
/*      */     
/*  547 */     combo = this.pagHTML.getElementSubproceso();
/*      */     
/*  549 */     c = "estado='A'";
/*      */     
/*  551 */     if (objetivo != null) {
/*  552 */       c = c + " and proceso='" + objetivo.getProceso() + "'" + " and codigo='" + objetivo.getSubProceso() + "'";
/*      */     }
/*      */     else {
/*      */       
/*  556 */       c = c + " AND proceso='01'";
/*      */     } 
/*  558 */     c = c + " order by proceso,codigo";
/*      */     
/*  560 */     llenarCombo(rsTGen, combo, "subprocesos", "codigo", "descripcion", "", c, false);
/*      */ 
/*      */     
/*  563 */     String impacto = "A";
/*      */     
/*  565 */     combo = this.pagHTML.getElementResponsable();
/*  566 */     comboPersonas(combo, 0, impacto, idNav, miArea, mostrarTodos);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  576 */     HTMLElement eltr = this.pagHTML.getElementTrModificacion();
/*  577 */     eltr.getParentNode().removeChild(eltr);
/*  578 */     rsTGen.close();
/*      */ 
/*      */     
/*  581 */     String codigoLogro = comms.request.getParameter("codigoLogro");
/*  582 */     if (codigoLogro == null) codigoLogro = ""; 
/*  583 */     this.pagHTML.getElementCodigoLogro().setValue("" + codigoLogro);
/*      */ 
/*      */     
/*  586 */     if (plan != 0) {
/*  587 */       this.pagHTML.getElementPlan().setValue("" + plan);
/*      */     }
/*      */     
/*  590 */     if (ciclo != 0) {
/*  591 */       this.pagHTML.getElementCiclo().setValue("" + ciclo);
/*  592 */       this.pagHTML.getElementAsociado().setValue("" + asociado);
/*      */     } 
/*      */     
/*  595 */     if (meta != 0) {
/*  596 */       this.pagHTML.getElementMeta().setValue("" + meta);
/*      */     }
/*      */     
/*  599 */     combo = this.pagHTML.getElementImpacto();
/*  600 */     comboMultivalores(combo, "impacto_acciones", "A", false, oTodaEntidad, oVariasAreas, liderAcciones);
/*      */     
/*  602 */     combo = this.pagHTML.getElementArea();
/*  603 */     comboAreas(combo, area, codigoEmpleado);
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
/*      */   private void recarga(HttpPresentationComms comms, int numero, String operacionOld, int codigoEmpleado, boolean oTodaEntidad, boolean oVariasAreas, String liderAcciones) throws HttpPresentationException, KeywordValueException {
/*  629 */     int miArea = Integer.parseInt((String)comms.session.getSessionData().get("miArea"));
/*  630 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  631 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*      */     
/*  633 */     SeguridadDAO rsSeguridad = new SeguridadDAO();
/*  634 */     if (!rsSeguridad.getEstadoConexion()) {
/*  635 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */     }
/*  637 */     boolean mostrarTodos = rsSeguridad.tieneLlave(miGrupo, "am_mostrarPersonasArea");
/*  638 */     rsSeguridad.close();
/*      */ 
/*      */     
/*  641 */     int area = Integer.parseInt(comms.request.getParameter("area"));
/*      */     
/*  643 */     String impacto = comms.request.getParameter("impacto");
/*  644 */     String objetivo = comms.request.getParameter("objetivo");
/*  645 */     String origen = comms.request.getParameter("origen");
/*  646 */     String proceso = comms.request.getParameter("proceso");
/*  647 */     String subproceso = comms.request.getParameter("subproceso");
/*  648 */     if (subproceso == null) subproceso = ""; 
/*  649 */     String numeral = comms.request.getParameter("numeral");
/*  650 */     String norma = comms.request.getParameter("norma");
/*  651 */     String descripcion = comms.request.getParameter("descripcion");
/*      */ 
/*      */ 
/*      */     
/*  655 */     String porque = comms.request.getParameter("porque");
/*  656 */     String accion = comms.request.getParameter("accion");
/*  657 */     String beneficio = comms.request.getParameter("beneficio");
/*  658 */     int responsable = 0;
/*      */     try {
/*  660 */       responsable = Integer.parseInt(comms.request.getParameter("responsable"));
/*  661 */     } catch (Exception e) {}
/*      */     
/*  663 */     String fechaEstimadaTerminacion = comms.request.getParameter("fechaEstimadaTerminacion");
/*      */ 
/*      */ 
/*      */     
/*  667 */     String codigoLogro = comms.request.getParameter("codigoLogro");
/*      */     
/*  669 */     if (codigoLogro == null) codigoLogro = "";
/*      */     
/*  671 */     String tema = comms.request.getParameter("tema");
/*      */ 
/*      */     
/*  674 */     int plan = 0;
/*      */     try {
/*  676 */       plan = Integer.parseInt(comms.request.getParameter("plan"));
/*      */     }
/*  678 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  681 */     String asociado = "";
/*  682 */     int ciclo = 0;
/*      */     try {
/*  684 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*  685 */       asociado = comms.request.getParameter("ciclo");
/*      */     
/*      */     }
/*  688 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  691 */     int meta = 0;
/*      */     try {
/*  693 */       meta = Integer.parseInt(comms.request.getParameter("meta"));
/*      */     }
/*  695 */     catch (Exception e) {}
/*      */ 
/*      */ 
/*      */     
/*  699 */     if (plan != 0) {
/*  700 */       this.pagHTML.getElementPlan().setValue("" + plan);
/*      */     }
/*      */     
/*  703 */     if (ciclo != 0) {
/*  704 */       this.pagHTML.getElementCiclo().setValue("" + ciclo);
/*  705 */       this.pagHTML.getElementAsociado().setValue("" + asociado);
/*      */     } 
/*      */     
/*  708 */     if (meta != 0) {
/*  709 */       this.pagHTML.getElementMeta().setValue("" + meta);
/*      */     }
/*      */     
/*  712 */     TGeneralDAO rsTGen = new TGeneralDAO();
/*  713 */     if (!rsTGen.getEstadoConexion()) {
/*  714 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */     }
/*  716 */     this.pagHTML.getElementIdNumero().setValue("" + numero);
/*      */     
/*  718 */     HTMLSelectElement combo = this.pagHTML.getElementObjetivo();
/*  719 */     llenarCombo(rsTGen, combo, "am_tipo", "codigo", "descripcion", objetivo, "CODIGO<>'R'", true);
/*      */     
/*  721 */     combo = this.pagHTML.getElementProceso();
/*      */     
/*  723 */     String c = "estado='A' order by codigo";
/*  724 */     llenarCombo(rsTGen, combo, "procesos", "codigo", "descripcion", proceso, c, false);
/*      */     
/*  726 */     combo = this.pagHTML.getElementSubproceso();
/*  727 */     llenarCombo(rsTGen, combo, "subprocesos", "codigo", "descripcion", subproceso, "estado='A' AND proceso='" + proceso + "' and 1=case when proceso='00' then 1 when proceso<>'00' and codigo='00' then 0 else 1 end order by proceso,codigo", false);
/*      */     
/*  729 */     combo = this.pagHTML.getElementOrigen();
/*  730 */     comboOrigen(rsTGen, combo, origen, area, true);
/*      */     
/*  732 */     combo = this.pagHTML.getElementNorma();
/*  733 */     llenarCombo(rsTGen, combo, "am_normas", "norma", "descripcion", norma, "estado='A'", true);
/*      */     
/*  735 */     this.pagHTML.getElementTema().setValue("" + tema);
/*      */     
/*  737 */     this.pagHTML.getElementNumeral().setValue("" + numeral);
/*      */ 
/*      */     
/*  740 */     this.pagHTML.getElement_operacion().setValue("" + operacionOld);
/*      */     
/*  742 */     this.pagHTML.getElementPorque().appendChild(this.pagHTML.createTextNode("" + porque));
/*  743 */     this.pagHTML.getElementAccion().appendChild(this.pagHTML.createTextNode("" + accion));
/*  744 */     this.pagHTML.getElementDescripcion().appendChild(this.pagHTML.createTextNode("" + descripcion));
/*  745 */     this.pagHTML.getElementBeneficio().appendChild(this.pagHTML.createTextNode("" + beneficio));
/*  746 */     this.pagHTML.getElementFechaEstimadaTerminacion().setValue("" + fechaEstimadaTerminacion);
/*      */ 
/*      */     
/*  749 */     combo = this.pagHTML.getElementResponsable();
/*  750 */     comboPersonas(combo, 0, impacto, responsable, miArea, mostrarTodos);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  759 */     rsTGen.close();
/*      */     
/*  761 */     combo = this.pagHTML.getElementImpacto();
/*  762 */     comboMultivalores(combo, "impacto_acciones", "" + impacto, false, oTodaEntidad, oVariasAreas, liderAcciones);
/*      */     
/*  764 */     combo = this.pagHTML.getElementArea();
/*  765 */     comboAreas(combo, area, codigoEmpleado);
/*      */     
/*  767 */     this.pagHTML.getElementCodigoLogro().setValue("" + codigoLogro);
/*      */ 
/*      */     
/*  770 */     if (!operacionOld.equals("M")) {
/*  771 */       HTMLElement eltr = this.pagHTML.getElementTrModificacion();
/*  772 */       eltr.getParentNode().removeChild(eltr);
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
/*      */   private void editar(HttpPresentationComms comms, int numero, int area, int codigoEmpleado, boolean oTodaEntidad, boolean oVariasAreas, String liderAcciones) throws HttpPresentationException, KeywordValueException {
/*  795 */     AMAccionesDAO rs = new AMAccionesDAO();
/*  796 */     AMAccionesDTO reg = rs.cargarRegistro(numero);
/*      */     
/*  798 */     if (reg == null) {
/*  799 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoLeeRegistro&p1=AccionesMejoramiento"));
/*      */     }
/*      */     
/*  802 */     TGeneralDAO rsTGen = new TGeneralDAO();
/*  803 */     if (!rsTGen.getEstadoConexion()) {
/*  804 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */     }
/*  806 */     this.pagHTML.getElementIdNumero().setValue("" + reg.getNumero());
/*      */     
/*  808 */     String c = "CODIGO<>'R'";
/*      */     
/*  810 */     FechaDTO fCreacion = new FechaDTO(Utilidades.darFormatoFecha(reg.getFechaGenerada()));
/*  811 */     FechaDTO fHoy = new FechaDTO(Utilidades.darFormatoFecha(Utilidades.fechaActual()));
/*      */     
/*  813 */     if (fHoy.getJuliano() > fCreacion.getJuliano()) {
/*  814 */       c = c + " AND codigo='" + reg.getAccion() + "'";
/*      */     }
/*  816 */     HTMLSelectElement combo = this.pagHTML.getElementObjetivo();
/*  817 */     llenarCombo(rsTGen, combo, "am_tipo", "codigo", "descripcion", reg.getAccion(), c, false);
/*      */     
/*  819 */     combo = this.pagHTML.getElementProceso();
/*      */     
/*  821 */     c = "estado='A' order by codigo";
/*  822 */     llenarCombo(rsTGen, combo, "procesos", "codigo", "descripcion", reg.getProceso(), c, false);
/*      */     
/*  824 */     combo = this.pagHTML.getElementSubproceso();
/*  825 */     c = "estado='A' AND proceso='" + reg.getProceso() + "' order by proceso,codigo";
/*  826 */     llenarCombo(rsTGen, combo, "subprocesos", "codigo", "descripcion", reg.getSubproceso(), c, false);
/*      */     
/*  828 */     combo = this.pagHTML.getElementOrigen();
/*  829 */     comboOrigen(rsTGen, combo, reg.getOrigen(), reg.getAreaImplanta(), true);
/*      */     
/*  831 */     combo = this.pagHTML.getElementNorma();
/*  832 */     llenarCombo(rsTGen, combo, "am_normas", "norma", "descripcion", reg.getNorma(), "norma='" + reg.getNorma() + "' or estado='A' order by descripcion", true);
/*      */     
/*  834 */     this.pagHTML.getElementNumeral().setValue("" + reg.getNumeral());
/*      */     
/*  836 */     this.pagHTML.getElementDescripcion().appendChild(this.pagHTML.createTextNode("" + reg.getDescripcion()));
/*      */     
/*  838 */     this.pagHTML.getElement_operacion().setValue("M");
/*  839 */     rsTGen.close();
/*      */     
/*  841 */     combo = this.pagHTML.getElementImpacto();
/*  842 */     comboMultivalores(combo, "impacto_acciones", "" + reg.getImpacto(), false, oTodaEntidad, oVariasAreas, liderAcciones);
/*      */     
/*  844 */     this.pagHTML.getElementTema().setValue("" + reg.getTemaAccion());
/*      */     
/*  846 */     this.pagHTML.setTextNumeroAccion("" + numero);
/*      */     
/*  848 */     combo = this.pagHTML.getElementArea();
/*  849 */     comboAreas(combo, reg.getAreaImplanta(), codigoEmpleado);
/*      */     
/*  851 */     if (reg.getImpacto().equals("V")) {
/*  852 */       listarAreas(comms, numero);
/*      */     }
/*      */     
/*  855 */     HTMLElement eltr = this.pagHTML.getElementTblCausa();
/*  856 */     eltr.getParentNode().removeChild(eltr);
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
/*      */   private void comboAreas(HTMLSelectElement combo, int areaDefecto, int codigoEmpleado) {
/*  882 */     AreasDAO rs = new AreasDAO();
/*  883 */     Collection<AreasDTO> arr = rs.cargarAreas(codigoEmpleado);
/*  884 */     rs.close();
/*      */     
/*  886 */     Iterator<AreasDTO> iterator = arr.iterator();
/*  887 */     while (iterator.hasNext()) {
/*  888 */       AreasDTO reg = (AreasDTO)iterator.next();
/*  889 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  890 */       op.setValue("" + reg.getCodigo());
/*  891 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/*  892 */       if (areaDefecto == reg.getCodigo()) {
/*  893 */         Attr escogida = this.pagHTML.createAttribute("selected");
/*  894 */         escogida.setValue("on");
/*  895 */         op.setAttributeNode(escogida);
/*      */       } 
/*  897 */       combo.appendChild(op);
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
/*      */   private void llenarCombo(TGeneralDAO rsTGen, HTMLSelectElement combo, String tabla, String codigo, String descripcion, String defecto, String condicion, boolean dejarBlanco) {
/*  922 */     if (dejarBlanco) {
/*  923 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  924 */       op.setValue("");
/*  925 */       op.appendChild(this.pagHTML.createTextNode(""));
/*  926 */       combo.appendChild(op);
/*      */     } 
/*      */     
/*  929 */     rsTGen.cargarTodos(tabla, codigo, descripcion, condicion);
/*      */     TGeneralDTO RegGeneral;
/*  931 */     while ((RegGeneral = rsTGen.next()) != null) {
/*  932 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  933 */       op.setValue("" + RegGeneral.getCodigo());
/*  934 */       op.appendChild(this.pagHTML.createTextNode(RegGeneral.getDescripcion()));
/*  935 */       if (defecto != null && defecto.equals(RegGeneral.getCodigo())) {
/*  936 */         Attr escogida = this.pagHTML.createAttribute("selected");
/*  937 */         escogida.setValue("on");
/*  938 */         op.setAttributeNode(escogida);
/*      */       } 
/*  940 */       combo.appendChild(op);
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
/*      */   private void comboOrigen(TGeneralDAO rsTGen, HTMLSelectElement combo, String defecto, int area, boolean dejarBlanco) {
/*  962 */     if (dejarBlanco) {
/*  963 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  964 */       op.setValue("");
/*  965 */       op.appendChild(this.pagHTML.createTextNode(""));
/*  966 */       combo.appendChild(op);
/*      */     } 
/*      */     
/*  969 */     Collection<TGeneralDTO> arr = rsTGen.cargarAmOrigen(area);
/*      */     
/*  971 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/*  972 */     while (iterator.hasNext()) {
/*  973 */       TGeneralDTO reg = (TGeneralDTO)iterator.next();
/*  974 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  975 */       op.setValue("" + reg.getCodigo());
/*  976 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion() + " " + (reg.getCapturaNumeral().equals("S") ? " -SI" : "")));
/*  977 */       if (defecto.equals(reg.getCodigo())) {
/*  978 */         Attr escogida = this.pagHTML.createAttribute("selected");
/*  979 */         escogida.setValue("on");
/*  980 */         op.setAttributeNode(escogida);
/*      */       } 
/*  982 */       combo.appendChild(op);
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
/*      */   private void generarCorreoJefe(int numero, int iNav, int proveedor, int areaImplanta, SisUsuariosDTO recipiente) {
/* 1002 */     Varios oVarios = new Varios();
/* 1003 */     if (recipiente != null) {
/* 1004 */       SisUsuariosDAO pf = new SisUsuariosDAO();
/* 1005 */       SisUsuariosDTO regNavegante = pf.cargarRegistro(iNav);
/* 1006 */       SisUsuariosDTO regProveedor = pf.cargarRegistro(proveedor);
/*      */ 
/*      */       
/* 1009 */       String url = "\n" + ParametrosDTO.getString("url.calidad");
/* 1010 */       url = url + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&";
/* 1011 */       url = url + "h=AMDetalleV2.po?numero=" + numero;
/*      */       
/* 1013 */       String from = regNavegante.getEmail();
/* 1014 */       String to = recipiente.getEmail();
/*      */       
/* 1016 */       String subject = "[Sistema gestión de calidad] ";
/*      */       
/* 1018 */       String mensaje = oVarios.formatMensaje("AccionMejoraCalificar", "" + numero, regProveedor.getNombre(), url);
/* 1019 */       Utilidades.sendMail(ParametrosDTO.getString("servidor.correo"), from, to, subject, mensaje);
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
/*      */   void actualizarLogro(int numero, String codigoLogro, String elUsuario) {
/* 1032 */     if (codigoLogro.length() == 0)
/*      */       return; 
/* 1034 */     StringTokenizer st = new StringTokenizer(codigoLogro, "_");
/*      */     
/* 1036 */     int codigoCiclo = 0;
/* 1037 */     int codigoPlan = 0;
/*      */     
/* 1039 */     int codigoObjetivo = 0;
/* 1040 */     int codigoMeta = 0;
/* 1041 */     int periodo = 0;
/*      */     
/* 1043 */     boolean correcto = true;
/*      */     try {
/* 1045 */       codigoCiclo = Integer.parseInt(st.nextToken());
/* 1046 */       codigoPlan = Integer.parseInt(st.nextToken());
/* 1047 */       codigoObjetivo = Integer.parseInt(st.nextToken());
/* 1048 */       codigoMeta = Integer.parseInt(st.nextToken());
/* 1049 */       Integer.parseInt(st.nextToken());
/* 1050 */       periodo = Integer.parseInt(st.nextToken());
/*      */     }
/* 1052 */     catch (Exception e) {
/* 1053 */       correcto = false;
/*      */     } 
/*      */     
/* 1056 */     if (correcto) {
/* 1057 */       CalLogrosDAO rsLogros = new CalLogrosDAO();
/* 1058 */       rsLogros.modificarAccionMejora(codigoCiclo, codigoPlan, codigoObjetivo, codigoMeta, periodo, numero, elUsuario);
/* 1059 */       rsLogros.close();
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
/*      */   private void comboMultivalores(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco, boolean oTodaEntidad, boolean oVariasAreas, String liderAcciones) {
/* 1080 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 1081 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 1082 */     rs.close();
/* 1083 */     if (dejarBlanco) {
/* 1084 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1085 */       op.setValue("");
/* 1086 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 1087 */       combo.appendChild(op);
/*      */     } 
/* 1089 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 1090 */     while (iterator.hasNext()) {
/* 1091 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 1092 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1093 */       op.setValue("" + reg.getCodigo());
/* 1094 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 1095 */       if (defecto.equals(reg.getCodigo())) {
/* 1096 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 1097 */         escogida.setValue("on");
/* 1098 */         op.setAttributeNode(escogida);
/*      */       } 
/*      */       
/* 1101 */       if (reg.getCodigo().equals("C") && !liderAcciones.equals("S")) {
/*      */         continue;
/*      */       }
/*      */       
/* 1105 */       if ((!oTodaEntidad && reg.getCodigo().equals("C")) || (!oVariasAreas && reg.getCodigo().equals("V"))) {
/*      */         continue;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/* 1111 */       combo.appendChild(op);
/*      */     } 
/* 1113 */     arr.clear();
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
/*      */   private void cargarAreasAccion(HttpPresentationComms comms, int numero) throws HttpPresentationException, KeywordValueException {
/* 1128 */     String elUsuario = "" + comms.session.getUser().getName();
/*      */     
/* 1130 */     AMAccionesDAO rs = new AMAccionesDAO();
/* 1131 */     Enumeration enumera = comms.request.getParameterNames();
/*      */ 
/*      */     
/* 1134 */     rs.eliminarAreas(numero);
/*      */     
/* 1136 */     while (enumera.hasMoreElements()) {
/* 1137 */       String param = (String)enumera.nextElement();
/* 1138 */       if (param.substring(0, 2).equals("A_")) {
/* 1139 */         int codigo = Integer.parseInt(param.substring(2, param.length()));
/* 1140 */         rs.crearAreas(numero, codigo, elUsuario);
/*      */       } 
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
/* 1155 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 1156 */     atrib.setValue(valor);
/* 1157 */     return atrib;
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
/*      */   private HTMLElement newtd(String contenido) {
/* 1171 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 1172 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 1173 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 1174 */     return td;
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
/*      */   private void listarAreas(HttpPresentationComms comms, int numero) throws HttpPresentationException, KeywordValueException {
/* 1187 */     AMAccionesDAO rs = new AMAccionesDAO();
/* 1188 */     Collection arr = rs.cargarAreas(numero);
/* 1189 */     HTMLTableElement hte = this.pagHTML.getElementDetalleRes();
/* 1190 */     Iterator iterator = arr.iterator();
/* 1191 */     boolean fondo = false;
/* 1192 */     while (iterator.hasNext()) {
/* 1193 */       AMAccionesDTO reg = (AMAccionesDTO)iterator.next();
/* 1194 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 1195 */       fondo = !fondo;
/* 1196 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*      */       
/* 1198 */       HTMLElement tdMarca = (HTMLElement)this.pagHTML.createElement("td");
/*      */       
/* 1200 */       HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
/* 1201 */       checkbox.setAttribute("type", "checkbox");
/* 1202 */       checkbox.setName("A_" + reg.getAreaImplanta());
/* 1203 */       checkbox.setChecked(true);
/* 1204 */       tdMarca.appendChild(checkbox);
/* 1205 */       eltr.appendChild(tdMarca);
/* 1206 */       eltr.appendChild(newtd("" + reg.getNombreAreaImplanta()));
/* 1207 */       hte.appendChild(eltr);
/*      */     } 
/* 1209 */     arr.clear();
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
/*      */   private void comboPersonas(HTMLSelectElement combo, int numeroAccion, String impacto, int defecto, int area, boolean mostrarTodos) {
/* 1230 */     SisUsuariosDAO pf = new SisUsuariosDAO();
/* 1231 */     Collection arr = new ArrayList();
/* 1232 */     if (mostrarTodos) {
/* 1233 */       arr = pf.cargarActivoDeArea(area);
/*      */     } else {
/*      */       
/* 1236 */       arr = pf.cargarPorCodigo(defecto);
/*      */     } 
/*      */     
/* 1239 */     Iterator iterator = arr.iterator();
/* 1240 */     while (iterator.hasNext()) {
/* 1241 */       SisUsuariosDTO personaDelArea = (SisUsuariosDTO)iterator.next();
/*      */       
/* 1243 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1244 */       op.setValue("" + personaDelArea.getCodigoEmpleado());
/* 1245 */       op.appendChild(this.pagHTML.createTextNode(personaDelArea.getApellidos() + " " + personaDelArea.getNombres()));
/*      */       
/* 1247 */       if (defecto == personaDelArea.getCodigoEmpleado()) {
/* 1248 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 1249 */         escogida.setValue("on");
/* 1250 */         op.setAttributeNode(escogida);
/*      */       } 
/*      */       
/* 1253 */       combo.appendChild(op);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AMActV2.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */