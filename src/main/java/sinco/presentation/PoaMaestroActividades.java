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
/*      */ import org.w3c.dom.html.HTMLOptionElement;
/*      */ import org.w3c.dom.html.HTMLSelectElement;
/*      */ import org.w3c.dom.html.HTMLTableSectionElement;
/*      */ import sinco.business.PersonasAreaDTO;
/*      */ import sinco.business.PoaActividadResponsableDTO;
/*      */ import sinco.business.PoaActividadesDTO;
/*      */ import sinco.business.PoaInsumosDTO;
/*      */ import sinco.business.PoaLogrosDTO;
/*      */ import sinco.business.PoaMaestroActividadesDTO;
/*      */ import sinco.business.PoaMaestroDTO;
/*      */ import sinco.business.PoaMaestroInsumoProveedorDTO;
/*      */ import sinco.business.PoaMaestroMedioVerificacionDTO;
/*      */ import sinco.business.PoaMaestroMultivaloresDTO;
/*      */ import sinco.business.PoaMediosVerificacionDTO;
/*      */ import sinco.business.PoaProveedoresDTO;
/*      */ import sinco.business.RespuestaBD;
/*      */ import sinco.business.SisMultiValoresDTO;
/*      */ import sinco.business.TGeneralDTO;
/*      */ import sinco.data.PersonasAreaDAO;
/*      */ import sinco.data.PoaActividadResponsableDAO;
/*      */ import sinco.data.PoaActividadesDAO;
/*      */ import sinco.data.PoaInsumosDAO;
/*      */ import sinco.data.PoaLogrosDAO;
/*      */ import sinco.data.PoaMaestroActividadesDAO;
/*      */ import sinco.data.PoaMaestroDAO;
/*      */ import sinco.data.PoaMaestroInsumoProveedorDAO;
/*      */ import sinco.data.PoaMaestroMedioVerificacionDAO;
/*      */ import sinco.data.PoaMaestroMultivaloresDAO;
/*      */ import sinco.data.PoaMediosVerificacionDAO;
/*      */ import sinco.data.PoaProveedoresDAO;
/*      */ import sinco.data.SisMultiValoresDAO;
/*      */ import sinco.data.TGeneralDAO;
/*      */ import sinco.presentation.PoaMaestroActividades;
/*      */ import sinco.presentation.PoaMaestroActividadesHTML;
/*      */ import sinco.spec.MenuDO;
/*      */ import sinco.spec.Varios;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PoaMaestroActividades
/*      */   implements HttpPresentation
/*      */ {
/*      */   private PoaMaestroActividadesHTML pagHTML;
/*      */   
/*      */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*   63 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*   64 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*      */     }
/*      */     
/*   67 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*   68 */     String _operacion = comms.request.getParameter("_operacion");
/*   69 */     if (_operacion == null || _operacion.length() == 0) {
/*   70 */       _operacion = "X";
/*      */     }
/*      */     
/*   73 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*   74 */       creacion(comms);
/*      */     }
/*      */     
/*   77 */     this.pagHTML = (PoaMaestroActividadesHTML)comms.xmlcFactory.create(PoaMaestroActividadesHTML.class);
/*   78 */     permisos(comms);
/*      */ 
/*      */     
/*   81 */     int codigoPoa = 0;
/*      */     try {
/*   83 */       codigoPoa = Integer.parseInt(comms.request.getParameter("codigoPoa"));
/*      */     }
/*   85 */     catch (Exception e) {}
/*      */ 
/*      */     
/*   88 */     this.pagHTML.getElementCodigoPoaHidden().setValue("" + codigoPoa);
/*   89 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*   90 */       listar(comms, _operacion);
/*      */     }
/*      */     
/*   93 */     if (_operacion.equals("P")) {
/*   94 */       editar(comms);
/*      */     }
/*   96 */     else if (_operacion.equals("Nuevo")) {
/*   97 */       nuevo(comms);
/*      */     } 
/*      */     
/*  100 */     if (_operacion.equals("V")) {
/*  101 */       verRegistro(comms);
/*      */     }
/*  103 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  104 */     comms.response.writeDOM(this.pagHTML);
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
/*  116 */     String _operacion = comms.request.getParameter("_operacion");
/*  117 */     String elUsuario = "" + comms.session.getUser().getName();
/*  118 */     int codigoPoa = 0;
/*      */     try {
/*  120 */       codigoPoa = Integer.parseInt(comms.request.getParameter("codigoPoa"));
/*      */     }
/*  122 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  125 */     int codigoPoaActividad = 0;
/*      */     try {
/*  127 */       codigoPoaActividad = Integer.parseInt(comms.request.getParameter("codigoPoaActividad"));
/*      */     }
/*  129 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  132 */     RespuestaBD rta = new RespuestaBD();
/*  133 */     RespuestaBD rta2 = new RespuestaBD();
/*  134 */     RespuestaBD rta3 = new RespuestaBD();
/*  135 */     RespuestaBD rta4 = new RespuestaBD();
/*  136 */     RespuestaBD rta5 = new RespuestaBD();
/*  137 */     if (_operacion.equals("E")) {
/*  138 */       PoaMaestroActividadesDAO rs = new PoaMaestroActividadesDAO();
/*  139 */       PoaMaestroMedioVerificacionDAO rs2 = new PoaMaestroMedioVerificacionDAO();
/*  140 */       PoaActividadResponsableDAO rs3 = new PoaActividadResponsableDAO();
/*  141 */       PoaMaestroInsumoProveedorDAO rs4 = new PoaMaestroInsumoProveedorDAO();
/*  142 */       PoaMaestroMultivaloresDAO rs5 = new PoaMaestroMultivaloresDAO();
/*      */       
/*  144 */       if (!rs.getEstadoConexion()) {
/*  145 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */       }
/*  147 */       if (!rs2.getEstadoConexion()) {
/*  148 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */       }
/*  150 */       if (!rs3.getEstadoConexion()) {
/*  151 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */       }
/*  153 */       if (!rs4.getEstadoConexion()) {
/*  154 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */       }
/*      */       
/*  157 */       if (!rs5.getEstadoConexion()) {
/*  158 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */       }
/*      */ 
/*      */       
/*  162 */       rta5 = rs5.eliminarRegistroPorActividad(codigoPoaActividad);
/*  163 */       if (!rta5.isRta()) {
/*  164 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaMaestroActividades&p1=" + rta.getMensaje()));
/*      */       }
/*      */       
/*  167 */       rta4 = rs4.eliminarRegistro(codigoPoaActividad);
/*  168 */       if (!rta4.isRta()) {
/*  169 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaMaestroActividades&p1=" + rta.getMensaje()));
/*      */       }
/*      */       
/*  172 */       rta3 = rs3.eliminarRegistro(codigoPoaActividad);
/*  173 */       if (!rta3.isRta()) {
/*  174 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaMaestroActividades&p1=" + rta.getMensaje()));
/*      */       }
/*  176 */       rta2 = rs2.eliminarRegistroPorActividad(codigoPoaActividad);
/*  177 */       if (!rta2.isRta()) {
/*  178 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaMaestroActividades&p1=" + rta.getMensaje()));
/*      */       }
/*  180 */       rta = rs.eliminarRegistro(codigoPoaActividad);
/*      */       
/*  182 */       if (!rta.isRta()) {
/*  183 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaMaestroActividades&p1=" + rta.getMensaje()));
/*      */       }
/*  185 */       rs.close();
/*  186 */       rs2.close();
/*  187 */       rs3.close();
/*  188 */       rs4.close();
/*  189 */       rs5.close();
/*  190 */       String sPagina = "PoaMaestroActividades.po?_operacion=X&codigoPoa=" + codigoPoa + "&codigoPoaActividad=" + codigoPoaActividad + "";
/*  191 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*      */     } 
/*  193 */     int objetivoEstrategico = 0;
/*      */     try {
/*  195 */       objetivoEstrategico = Integer.parseInt(comms.request.getParameter("objetivoEstrategico"));
/*      */     }
/*  197 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  200 */     int actividad = 0;
/*      */     try {
/*  202 */       actividad = Integer.parseInt(comms.request.getParameter("actividad"));
/*      */     }
/*  204 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  207 */     int tipoActividad = 0;
/*      */     try {
/*  209 */       tipoActividad = Integer.parseInt(comms.request.getParameter("tipoActividad"));
/*      */     }
/*  211 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  214 */     String variablesMedicion = comms.request.getParameter("variablesMedicion");
/*  215 */     int mediosDeVerificacion = 0;
/*      */     try {
/*  217 */       mediosDeVerificacion = Integer.parseInt(comms.request.getParameter("mediosDeVerificacion"));
/*      */     }
/*  219 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  222 */     String proyectoInversion = comms.request.getParameter("proyectoInversion");
/*  223 */     int productoProceso = 0;
/*      */     try {
/*  225 */       productoProceso = Integer.parseInt(comms.request.getParameter("productoProceso"));
/*      */     }
/*  227 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  230 */     int metaPlanDeDesarrollo = 0;
/*      */     try {
/*  232 */       metaPlanDeDesarrollo = Integer.parseInt(comms.request.getParameter("metaPlanDeDesarrollo"));
/*      */     }
/*  234 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  237 */     int objetivoSubsistema = 0;
/*      */     try {
/*  239 */       objetivoSubsistema = Integer.parseInt(comms.request.getParameter("objetivoSubsistema"));
/*      */     }
/*  241 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  244 */     int metaProyecto = 0;
/*      */     try {
/*  246 */       metaProyecto = Integer.parseInt(comms.request.getParameter("metaProyecto"));
/*      */     }
/*  248 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  251 */     String tipoRecurso = comms.request.getParameter("tipoRecurso");
/*  252 */     String prioridadEnProducto = comms.request.getParameter("prioridadEnProducto");
/*  253 */     String prioridadObjetivo = comms.request.getParameter("prioridadObjetivo");
/*  254 */     String mes1 = comms.request.getParameter("mes1");
/*  255 */     String mes2 = comms.request.getParameter("mes2");
/*  256 */     String mes3 = comms.request.getParameter("mes3");
/*  257 */     String mes4 = comms.request.getParameter("mes4");
/*  258 */     String mes5 = comms.request.getParameter("mes5");
/*  259 */     String mes6 = comms.request.getParameter("mes6");
/*  260 */     String mes7 = comms.request.getParameter("mes7");
/*  261 */     String mes8 = comms.request.getParameter("mes8");
/*  262 */     String mes9 = comms.request.getParameter("mes9");
/*  263 */     String mes10 = comms.request.getParameter("mes10");
/*  264 */     String mes11 = comms.request.getParameter("mes11");
/*  265 */     String mes12 = comms.request.getParameter("mes12");
/*      */ 
/*      */     
/*  268 */     if (mes1 == null) mes1 = "N"; 
/*  269 */     if (mes2 == null) mes2 = "N"; 
/*  270 */     if (mes3 == null) mes3 = "N"; 
/*  271 */     if (mes4 == null) mes4 = "N"; 
/*  272 */     if (mes5 == null) mes5 = "N"; 
/*  273 */     if (mes6 == null) mes6 = "N"; 
/*  274 */     if (mes7 == null) mes7 = "N"; 
/*  275 */     if (mes8 == null) mes8 = "N"; 
/*  276 */     if (mes9 == null) mes9 = "N"; 
/*  277 */     if (mes10 == null) mes10 = "N"; 
/*  278 */     if (mes11 == null) mes11 = "N"; 
/*  279 */     if (mes12 == null) mes12 = "N";
/*      */     
/*  281 */     int valorMes1 = 0;
/*      */     try {
/*  283 */       valorMes1 = Integer.parseInt(comms.request.getParameter("valorMes1"));
/*      */     }
/*  285 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  288 */     int valorMes2 = 0;
/*      */     try {
/*  290 */       valorMes2 = Integer.parseInt(comms.request.getParameter("valorMes2"));
/*      */     }
/*  292 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  295 */     int valorMes3 = 0;
/*      */     try {
/*  297 */       valorMes3 = Integer.parseInt(comms.request.getParameter("valorMes3"));
/*      */     }
/*  299 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  302 */     int valorMes4 = 0;
/*      */     try {
/*  304 */       valorMes4 = Integer.parseInt(comms.request.getParameter("valorMes4"));
/*      */     }
/*  306 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  309 */     int valorMes5 = 0;
/*      */     try {
/*  311 */       valorMes5 = Integer.parseInt(comms.request.getParameter("valorMes5"));
/*      */     }
/*  313 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  316 */     int valorMes6 = 0;
/*      */     try {
/*  318 */       valorMes6 = Integer.parseInt(comms.request.getParameter("valorMes6"));
/*      */     }
/*  320 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  323 */     int valorMes7 = 0;
/*      */     try {
/*  325 */       valorMes7 = Integer.parseInt(comms.request.getParameter("valorMes7"));
/*      */     }
/*  327 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  330 */     int valorMes8 = 0;
/*      */     try {
/*  332 */       valorMes8 = Integer.parseInt(comms.request.getParameter("valorMes8"));
/*      */     }
/*  334 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  337 */     int valorMes9 = 0;
/*      */     try {
/*  339 */       valorMes9 = Integer.parseInt(comms.request.getParameter("valorMes9"));
/*      */     }
/*  341 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  344 */     int valorMes10 = 0;
/*      */     try {
/*  346 */       valorMes10 = Integer.parseInt(comms.request.getParameter("valorMes10"));
/*      */     }
/*  348 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  351 */     int valorMes11 = 0;
/*      */     try {
/*  353 */       valorMes11 = Integer.parseInt(comms.request.getParameter("valorMes11"));
/*      */     }
/*  355 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  358 */     int valorMes12 = 0;
/*      */     try {
/*  360 */       valorMes12 = Integer.parseInt(comms.request.getParameter("valorMes12"));
/*      */     }
/*  362 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  365 */     String estado = comms.request.getParameter("estado");
/*  366 */     PoaMaestroActividadesDAO rs = new PoaMaestroActividadesDAO();
/*  367 */     if (!rs.getEstadoConexion()) {
/*  368 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */     }
/*  370 */     if (_operacion.equals("C")) {
/*  371 */       int variablesMedicion2 = 1;
/*  372 */       rta = rs.crearRegistro(codigoPoa, codigoPoaActividad, objetivoEstrategico, variablesMedicion2, actividad, tipoActividad, proyectoInversion, productoProceso, metaPlanDeDesarrollo, objetivoSubsistema, metaProyecto, tipoRecurso, prioridadEnProducto, prioridadObjetivo, mes1, mes2, mes3, mes4, mes5, mes6, mes7, mes8, mes9, mes10, mes11, mes12, valorMes1, valorMes2, valorMes3, valorMes4, valorMes5, valorMes6, valorMes7, valorMes8, valorMes9, valorMes10, valorMes11, valorMes12, estado, elUsuario);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  414 */       codigoPoaActividad = rta.getSecuencia();
/*      */     }
/*      */     else {
/*      */       
/*  418 */       rta = rs.modificarRegistro(codigoPoa, codigoPoaActividad, objetivoEstrategico, actividad, tipoActividad, proyectoInversion, productoProceso, metaPlanDeDesarrollo, objetivoSubsistema, metaProyecto, tipoRecurso, prioridadEnProducto, prioridadObjetivo, mes1, mes2, mes3, mes4, mes5, mes6, mes7, mes8, mes9, mes10, mes11, mes12, valorMes1, valorMes2, valorMes3, valorMes4, valorMes5, valorMes6, valorMes7, valorMes8, valorMes9, valorMes10, valorMes11, valorMes12, estado, elUsuario);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  459 */     rs.close();
/*  460 */     agregarVariables(comms, codigoPoa, codigoPoaActividad);
/*  461 */     agregarMediosVerificacion(comms, codigoPoaActividad, codigoPoa);
/*  462 */     agregarResponsableActividad(comms, codigoPoaActividad, actividad);
/*  463 */     agregarInsumo(comms, codigoPoaActividad);
/*  464 */     if (!rta.isRta()) {
/*  465 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaMaestroActividades&p1=" + rta.getMensaje()));
/*      */     }
/*      */     
/*  468 */     String sPagina = "PoaMaestroActividades.po?_operacion=P&codigoPoa=" + codigoPoa + "&codigoPoaActividad=" + codigoPoaActividad + "";
/*  469 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/*  480 */     int codigoPoaActividad = 0;
/*      */     try {
/*  482 */       codigoPoaActividad = Integer.parseInt(comms.request.getParameter("codigoPoaActividad"));
/*      */     }
/*  484 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  487 */     PoaMaestroActividadesDAO rs = new PoaMaestroActividadesDAO();
/*  488 */     if (!rs.getEstadoConexion()) {
/*  489 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */     }
/*  491 */     PoaMaestroActividadesDTO reg = rs.cargarRegistro(codigoPoaActividad);
/*  492 */     rs.close();
/*      */ 
/*      */ 
/*      */     
/*  496 */     if (reg != null) {
/*  497 */       int codigoPoa = 0;
/*      */       try {
/*  499 */         codigoPoa = Integer.parseInt(comms.request.getParameter("codigoPoa"));
/*      */       }
/*  501 */       catch (Exception e) {}
/*      */ 
/*      */       
/*  504 */       codigoPoa = Integer.parseInt(this.pagHTML.getElementCodigoPoaHidden().getValue());
/*      */       
/*  506 */       PoaMaestroDAO res = new PoaMaestroDAO();
/*  507 */       PoaMaestroDTO poa = res.cargarRegistro(Integer.parseInt(this.pagHTML.getElementCodigoPoaHidden().getValue()));
/*  508 */       res.close();
/*      */       
/*  510 */       this.pagHTML.getElementCodigoPoaActividad().setValue("" + reg.getCodigoPoaActividad());
/*  511 */       this.pagHTML.getElementActividad().setValue("" + reg.getActividad());
/*      */       
/*  513 */       this.pagHTML.setTextTipoActividad("" + reg.getNombreActividad());
/*      */ 
/*      */ 
/*      */       
/*  517 */       if (reg.getMes1().equals("S")) {
/*  518 */         this.pagHTML.getElementMes1().setChecked(true);
/*      */       }
/*  520 */       if (reg.getMes2().equals("S")) {
/*  521 */         this.pagHTML.getElementMes2().setChecked(true);
/*      */       }
/*  523 */       if (reg.getMes3().equals("S")) {
/*  524 */         this.pagHTML.getElementMes3().setChecked(true);
/*      */       }
/*  526 */       if (reg.getMes4().equals("S")) {
/*  527 */         this.pagHTML.getElementMes4().setChecked(true);
/*      */       }
/*      */       
/*  530 */       if (reg.getMes5().equals("S")) {
/*  531 */         this.pagHTML.getElementMes5().setChecked(true);
/*      */       }
/*      */       
/*  534 */       if (reg.getMes6().equals("S")) {
/*  535 */         this.pagHTML.getElementMes6().setChecked(true);
/*      */       }
/*      */       
/*  538 */       if (reg.getMes7().equals("S")) {
/*  539 */         this.pagHTML.getElementMes7().setChecked(true);
/*      */       }
/*      */       
/*  542 */       if (reg.getMes8().equals("S")) {
/*  543 */         this.pagHTML.getElementMes8().setChecked(true);
/*      */       }
/*      */       
/*  546 */       if (reg.getMes9().equals("S")) {
/*  547 */         this.pagHTML.getElementMes9().setChecked(true);
/*      */       }
/*      */       
/*  550 */       if (reg.getMes10().equals("S")) {
/*  551 */         this.pagHTML.getElementMes10().setChecked(true);
/*      */       }
/*      */       
/*  554 */       if (reg.getMes11().equals("S")) {
/*  555 */         this.pagHTML.getElementMes11().setChecked(true);
/*      */       }
/*      */       
/*  558 */       if (reg.getMes12().equals("S")) {
/*  559 */         this.pagHTML.getElementMes12().setChecked(true);
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  564 */       this.pagHTML.getElementValorMes1().setValue("" + reg.getValorMes1());
/*  565 */       this.pagHTML.getElementValorMes2().setValue("" + reg.getValorMes2());
/*  566 */       this.pagHTML.getElementValorMes3().setValue("" + reg.getValorMes3());
/*  567 */       this.pagHTML.getElementValorMes4().setValue("" + reg.getValorMes4());
/*  568 */       this.pagHTML.getElementValorMes5().setValue("" + reg.getValorMes5());
/*  569 */       this.pagHTML.getElementValorMes6().setValue("" + reg.getValorMes6());
/*  570 */       this.pagHTML.getElementValorMes7().setValue("" + reg.getValorMes7());
/*  571 */       this.pagHTML.getElementValorMes8().setValue("" + reg.getValorMes8());
/*  572 */       this.pagHTML.getElementValorMes9().setValue("" + reg.getValorMes9());
/*  573 */       this.pagHTML.getElementValorMes10().setValue("" + reg.getValorMes10());
/*  574 */       this.pagHTML.getElementValorMes11().setValue("" + reg.getValorMes11());
/*  575 */       this.pagHTML.getElementValorMes12().setValue("" + reg.getValorMes12());
/*  576 */       this.pagHTML.getElementEstado().setValue("" + reg.getEstado());
/*  577 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/*  578 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/*  579 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/*  580 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/*  581 */       this.pagHTML.setTextScriptVariablesMedicion(cargarVariablesMedicion(comms, codigoPoaActividad));
/*  582 */       this.pagHTML.setTextScriptMediosVerificacion(cargarMediosVerificacion(comms, codigoPoaActividad));
/*  583 */       this.pagHTML.setTextScriptResponsables(cargarResponsablesActividad(comms, codigoPoaActividad));
/*  584 */       this.pagHTML.setTextScriptInsumos(cargarInsumos(comms, codigoPoaActividad));
/*      */ 
/*      */       
/*  587 */       HTMLSelectElement combo = this.pagHTML.getElementObjetivoEstrategico();
/*  588 */       llenarCombo(combo, "POA_OBJETIVOS_ESTRATEGICOS", "CODIGO_OBJETIVO", "DESCRIPCION", "1=1", "" + reg.getObjetivoEstrategico(), false);
/*      */       
/*  590 */       combo = this.pagHTML.getElementVariablesMedicion();
/*  591 */       comboMultivalores(combo, "POA_VARIABLES_MEDICION", "" + reg.getVariablesMedicion(), false);
/*      */       
/*  593 */       combo = this.pagHTML.getElementMediosDeVerificacion();
/*  594 */       llenarCombo(combo, "POA_MEDIOS_VERIFICACION", "CODIGO_MEDIO_VERIFICACION", "DESCRIPCION", "1=1", "" + reg.getMediosDeVerificacion(), false);
/*      */       
/*  596 */       combo = this.pagHTML.getElementResponsableActividad();
/*      */ 
/*      */ 
/*      */       
/*  600 */       String c = "u.codigo_empleado=ua.codigo_empleado and ua.codigo_area=" + poa.getArea() + " and ua.area_principal='S' AND U.estado='A'";
/*  601 */       llenarCombo(combo, "SIS_USUARIOS u,sis_usuarios_area ua", "U.CODIGO_EMPLEADO", "U.APELLIDOS ||' ' ||U.NOMBRES", c, "", false);
/*      */       
/*  603 */       combo = this.pagHTML.getElementProyectoInversion();
/*  604 */       llenarCombo(combo, "POA_PROYECTOS_INVERSION", "CODIGO_PROYECTO_INVERSION", "DESCRIPCION", "1=1", "" + reg.getProyectoInversion(), false);
/*      */       
/*  606 */       combo = this.pagHTML.getElementProductoProceso();
/*  607 */       llenarCombo(combo, "POA_PRODUCTOS_PROCESO", "CODIGO_PRODUCTO_PROCESO", "DESCRIPCION", "1=1", "" + reg.getProductoProceso(), false);
/*      */       
/*  609 */       combo = this.pagHTML.getElementInsumo();
/*  610 */       llenarCombo(combo, "POA_INSUMOS", "CODIGO_INSUMO", "DESCRPCION", "1=1", "", false);
/*      */       
/*  612 */       combo = this.pagHTML.getElementProveedor();
/*  613 */       llenarCombo(combo, "POA_PROVEEDORES", "CODIGO_PROVEEDOR", "DESCRIPCION", "1=1", "", false);
/*      */       
/*  615 */       combo = this.pagHTML.getElementMetaPlanDeDesarrollo();
/*  616 */       llenarCombo(combo, "POA_METAS_PLAN_DESARROLLO", "CODIGO_META_PLAN", "DESCRIPCION", "1=1", "" + reg.getMetaPlanDeDesarrollo(), false);
/*      */       
/*  618 */       combo = this.pagHTML.getElementActividad();
/*  619 */       llenarCombo(combo, "POA_ACTIVIDADES", "CODIGO_ACTIVIDAD", "DESCRIPCION", "1=1", "" + reg.getActividad(), false);
/*      */       
/*  621 */       combo = this.pagHTML.getElementObjetivoSubsistema();
/*  622 */       llenarCombo(combo, "POA_OBJETIVOS_SUBSISTEMA_SIG", "CODIGO_OBJETIVO_SUBSISTEMA", "DESCRIPCION", "1=1", "" + reg.getObjetivoSubsistema(), false);
/*      */       
/*  624 */       combo = this.pagHTML.getElementMetaProyecto();
/*  625 */       llenarCombo(combo, "POA_METAS_PROYECTO", "CODIGO_META_PROYECTO", "DESCRIPCION", "1=1", "" + reg.getMetaProyecto(), false);
/*      */       
/*  627 */       combo = this.pagHTML.getElementTipoRecurso();
/*  628 */       comboMultivalores(combo, "POA_TIPOS_RECURSO", "" + reg.getTipoRecurso(), false);
/*      */       
/*  630 */       combo = this.pagHTML.getElementPrioridadEnProducto();
/*  631 */       comboMultivalores(combo, "POA_PRIORIDADES_PRODUCTO", "" + reg.getPrioridadEnProducto(), false);
/*      */       
/*  633 */       combo = this.pagHTML.getElementPrioridadObjetivo();
/*  634 */       comboMultivalores(combo, "POA_PRIORIDADES_OBJETIVO", "" + reg.getPrioridadObjetivo(), false);
/*      */       
/*  636 */       combo = this.pagHTML.getElementEstado();
/*  637 */       comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), false);
/*      */ 
/*      */       
/*  640 */       this.pagHTML.getElementCodigoPoaActividad().setReadOnly(true);
/*      */     } 
/*  642 */     this.pagHTML.getElement_operacion().setValue("M");
/*  643 */     activarVista("nuevo");
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
/*  655 */     this.pagHTML.getElement_operacion().setValue("C");
/*      */     try {
/*  657 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/*  658 */       sel.getParentNode().removeChild(sel);
/*      */     }
/*  660 */     catch (Exception e) {}
/*      */     
/*  662 */     activarVista("nuevo");
/*  663 */     HTMLSelectElement combo = this.pagHTML.getElementObjetivoEstrategico();
/*  664 */     llenarCombo(combo, "POA_OBJETIVOS_ESTRATEGICOS", "CODIGO_OBJETIVO", "DESCRIPCION", "1=1", "", false);
/*      */     
/*  666 */     combo = this.pagHTML.getElementVariablesMedicion();
/*  667 */     comboMultivalores(combo, "POA_VARIABLES_MEDICION", "", false);
/*      */     
/*  669 */     combo = this.pagHTML.getElementMediosDeVerificacion();
/*  670 */     llenarCombo(combo, "POA_MEDIOS_VERIFICACION", "CODIGO_MEDIO_VERIFICACION", "DESCRIPCION", "1=1", "", false);
/*  671 */     int codigoPoa = 0;
/*      */     try {
/*  673 */       codigoPoa = Integer.parseInt(comms.request.getParameter("codigoPoa"));
/*      */     }
/*  675 */     catch (Exception e) {}
/*      */     
/*  677 */     PoaMaestroDAO res = new PoaMaestroDAO();
/*  678 */     PoaMaestroDTO poa = res.cargarRegistro(codigoPoa);
/*  679 */     res.close();
/*      */     
/*  681 */     int codigoArea = poa.getArea();
/*  682 */     this.pagHTML.setTextCodigoPoaSP("" + codigoPoa);
/*  683 */     this.pagHTML.getElementCodigoAreaHidden().setValue("" + codigoArea);
/*      */ 
/*      */ 
/*      */     
/*  687 */     combo = this.pagHTML.getElementInsumo();
/*  688 */     llenarCombo(combo, "POA_INSUMOS", "CODIGO_INSUMO", "DESCRPCION", "1=1", "", false);
/*      */     
/*  690 */     combo = this.pagHTML.getElementResponsableActividad();
/*      */     
/*  692 */     String c = "u.codigo_empleado=ua.codigo_empleado and ua.codigo_area=" + codigoArea + " and ua.area_principal='S' AND U.estado='A'";
/*  693 */     llenarCombo(combo, "SIS_USUARIOS u,sis_usuarios_area ua", "U.CODIGO_EMPLEADO", "U.APELLIDOS ||' ' ||U.NOMBRES", c, "", false);
/*      */ 
/*      */     
/*  696 */     combo = this.pagHTML.getElementProveedor();
/*  697 */     llenarCombo(combo, "POA_PROVEEDORES", "CODIGO_PROVEEDOR", "DESCRIPCION", "1=1", "", false);
/*      */     
/*  699 */     combo = this.pagHTML.getElementActividad();
/*  700 */     llenarCombo(combo, "POA_ACTIVIDADES", "CODIGO_ACTIVIDAD", "DESCRIPCION", "1=1", "", true);
/*      */     
/*  702 */     combo = this.pagHTML.getElementProyectoInversion();
/*  703 */     llenarCombo(combo, "POA_PROYECTOS_INVERSION", "CODIGO_PROYECTO_INVERSION", "DESCRIPCION", "1=1", "", false);
/*      */     
/*  705 */     combo = this.pagHTML.getElementProductoProceso();
/*  706 */     llenarCombo(combo, "POA_PRODUCTOS_PROCESO", "CODIGO_PRODUCTO_PROCESO", "DESCRIPCION", "1=1", "", false);
/*      */     
/*  708 */     combo = this.pagHTML.getElementMetaPlanDeDesarrollo();
/*  709 */     llenarCombo(combo, "POA_METAS_PLAN_DESARROLLO", "CODIGO_META_PLAN", "DESCRIPCION", "1=1", "", true);
/*      */     
/*  711 */     combo = this.pagHTML.getElementObjetivoSubsistema();
/*  712 */     llenarCombo(combo, "POA_OBJETIVOS_SUBSISTEMA_SIG", "CODIGO_OBJETIVO_SUBSISTEMA", "DESCRIPCION", "1=1", "", false);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  717 */     combo = this.pagHTML.getElementTipoRecurso();
/*  718 */     comboMultivalores(combo, "POA_TIPOS_RECURSO", "", false);
/*      */     
/*  720 */     combo = this.pagHTML.getElementPrioridadEnProducto();
/*  721 */     comboMultivalores(combo, "POA_PRIORIDADES_PRODUCTO", "", false);
/*      */     
/*  723 */     combo = this.pagHTML.getElementPrioridadObjetivo();
/*  724 */     comboMultivalores(combo, "POA_PRIORIDADES_OBJETIVO", "", false);
/*      */     
/*  726 */     combo = this.pagHTML.getElementEstado();
/*  727 */     comboMultivalores(combo, "ESTADO_REGISTRO", "", false);
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
/*      */   private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
/*  739 */     activarVista("consulta");
/*  740 */     int codigoPoa = 0;
/*      */     try {
/*  742 */       codigoPoa = Integer.parseInt(comms.request.getParameter("codigoPoa"));
/*      */     }
/*  744 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  747 */     int actividad = 0;
/*      */     try {
/*  749 */       actividad = Integer.parseInt(comms.request.getParameter("actividad"));
/*      */     }
/*  751 */     catch (Exception e) {}
/*      */ 
/*      */ 
/*      */     
/*  755 */     HTMLSelectElement combo = this.pagHTML.getElementFactividad();
/*  756 */     llenarCombo(combo, "POA_ACTIVIDADES", "CODIGO_ACTIVIDAD", "DESCRIPCION", "1=1", "" + actividad, true);
/*      */     
/*  758 */     if (_operacion.equals("X")) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  763 */     PoaMaestroActividadesDAO rs = new PoaMaestroActividadesDAO();
/*  764 */     if (!rs.getEstadoConexion()) {
/*  765 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */     }
/*  767 */     Collection<PoaMaestroActividadesDTO> arr = rs.cargarTodos(codigoPoa, actividad);
/*      */ 
/*      */     
/*  770 */     rs.close();
/*  771 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/*  772 */     int cuantas = 0;
/*      */     
/*  774 */     Iterator<PoaMaestroActividadesDTO> iterator = arr.iterator();
/*  775 */     while (iterator.hasNext()) {
/*  776 */       PoaMaestroActividadesDTO reg = (PoaMaestroActividadesDTO)iterator.next();
/*  777 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  778 */       eltr.appendChild(newtd("" + reg.getCodigoPoa()));
/*  779 */       String url = "PoaMaestroActividades.po?_operacion=V&codigoPoa=" + reg.getCodigoPoa() + "&codigoPoaActividad=" + reg.getCodigoPoaActividad() + "";
/*  780 */       eltr.appendChild(newtdhref("" + reg.getCodigoPoaActividad(), url));
/*  781 */       eltr.appendChild(newtd("" + reg.getNombreObjetivoEstrategico()));
/*  782 */       eltr.appendChild(newtd("" + reg.getNombreActividad()));
/*      */       
/*  784 */       hte.appendChild(eltr);
/*  785 */       cuantas++;
/*      */     } 
/*  787 */     arr.clear();
/*  788 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/*  801 */     int codigoPoaActividad = 0;
/*      */     try {
/*  803 */       codigoPoaActividad = Integer.parseInt(comms.request.getParameter("codigoPoaActividad"));
/*      */     }
/*  805 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  808 */     PoaMaestroActividadesDAO rs = new PoaMaestroActividadesDAO();
/*  809 */     if (!rs.getEstadoConexion()) {
/*  810 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */     }
/*  812 */     PoaMaestroActividadesDTO reg = rs.cargarRegistro(codigoPoaActividad);
/*      */     
/*  814 */     rs.close();
/*  815 */     if (reg != null) {
/*  816 */       int totalEsperado = 0;
/*  817 */       int totalEjecutado = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  823 */       PoaActividadesDAO poa = new PoaActividadesDAO();
/*  824 */       PoaActividadesDTO poaActividad = poa.cargarRegistro(reg.getActividad());
/*  825 */       poa.close();
/*      */       
/*  827 */       this.pagHTML.setTextCodigoPoaEd("" + reg.getCodigoPoa());
/*  828 */       this.pagHTML.setTextCodigoPoaActividadEd("" + reg.getCodigoPoaActividad());
/*  829 */       this.pagHTML.setTextObjetivoEstrategicoEd("" + reg.getNombreObjetivoEstrategico());
/*  830 */       this.pagHTML.setTextActividadEd("" + reg.getNombreActividad());
/*      */       
/*  832 */       if (poaActividad != null) {
/*  833 */         this.pagHTML.setTextTipoActividadEd("" + poaActividad.getNombreTipoActividad());
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  839 */       this.pagHTML.setTextVariablesMedicionEd("" + reg.getVariablesMedicion());
/*  840 */       this.pagHTML.setTextMediosDeVerificacionEd("" + reg.getNombreMediosDeVerificacion());
/*  841 */       this.pagHTML.setTextProyectoInversionEd("" + reg.getNombreProyectoInversion());
/*  842 */       this.pagHTML.setTextProductoProcesoEd("" + reg.getNombreProductoProceso());
/*  843 */       this.pagHTML.setTextMetaPlanDeDesarrolloEd("" + reg.getNombreMetaPlanDeDesarrollo());
/*  844 */       this.pagHTML.setTextObjetivoSubsistemaEd("" + reg.getNombreObjetivoSubsistema());
/*  845 */       this.pagHTML.setTextMetaProyectoEd("" + reg.getNombreMetaProyecto());
/*  846 */       this.pagHTML.setTextTipoRecursoEd("" + reg.getNombreTipoRecurso());
/*  847 */       this.pagHTML.setTextPrioridadEnProductoEd("" + reg.getPrioridadEnProducto());
/*  848 */       this.pagHTML.setTextPrioridadObjetivoEd("" + reg.getPrioridadObjetivo());
/*  849 */       this.pagHTML.setTextProductoProcesoEd("" + reg.getNombreProductoProceso());
/*      */       
/*  851 */       this.pagHTML.setTextMes1Ed("" + reg.getMes1());
/*  852 */       this.pagHTML.setTextMes2Ed("" + reg.getMes2());
/*  853 */       this.pagHTML.setTextMes3Ed("" + reg.getMes3());
/*  854 */       this.pagHTML.setTextMes4Ed("" + reg.getMes4());
/*  855 */       this.pagHTML.setTextMes5Ed("" + reg.getMes5());
/*  856 */       this.pagHTML.setTextMes6Ed("" + reg.getMes6());
/*  857 */       this.pagHTML.setTextMes7Ed("" + reg.getMes7());
/*  858 */       this.pagHTML.setTextMes8Ed("" + reg.getMes8());
/*  859 */       this.pagHTML.setTextMes9Ed("" + reg.getMes9());
/*  860 */       this.pagHTML.setTextMes10Ed("" + reg.getMes10());
/*  861 */       this.pagHTML.setTextMes11Ed("" + reg.getMes11());
/*  862 */       this.pagHTML.setTextMes12Ed("" + reg.getMes12());
/*  863 */       this.pagHTML.setTextValorMes1Ed("" + reg.getValorMes1());
/*  864 */       this.pagHTML.setTextValorMes1RealEd("" + valorReal(codigoPoaActividad, 1));
/*  865 */       this.pagHTML.setTextValorMes2Ed("" + reg.getValorMes2());
/*  866 */       this.pagHTML.setTextValorMes2RealEd("" + valorReal(codigoPoaActividad, 2));
/*  867 */       this.pagHTML.setTextValorMes3Ed("" + reg.getValorMes3());
/*  868 */       this.pagHTML.setTextValorMes3RealEd("" + valorReal(codigoPoaActividad, 3));
/*  869 */       this.pagHTML.setTextValorMes4Ed("" + reg.getValorMes4());
/*  870 */       this.pagHTML.setTextValorMes4RealEd("" + valorReal(codigoPoaActividad, 4));
/*  871 */       this.pagHTML.setTextValorMes5Ed("" + reg.getValorMes5());
/*  872 */       this.pagHTML.setTextValorMes5RealEd("" + valorReal(codigoPoaActividad, 5));
/*  873 */       this.pagHTML.setTextValorMes6Ed("" + reg.getValorMes6());
/*  874 */       this.pagHTML.setTextValorMes6RealEd("" + valorReal(codigoPoaActividad, 6));
/*  875 */       this.pagHTML.setTextValorMes7Ed("" + reg.getValorMes7());
/*  876 */       this.pagHTML.setTextValorMes7RealEd("" + valorReal(codigoPoaActividad, 7));
/*  877 */       this.pagHTML.setTextValorMes8Ed("" + reg.getValorMes8());
/*  878 */       this.pagHTML.setTextValorMes8RealEd("" + valorReal(codigoPoaActividad, 8));
/*  879 */       this.pagHTML.setTextValorMes9Ed("" + reg.getValorMes9());
/*  880 */       this.pagHTML.setTextValorMes9RealEd("" + valorReal(codigoPoaActividad, 9));
/*  881 */       this.pagHTML.setTextValorMes10Ed("" + reg.getValorMes10());
/*  882 */       this.pagHTML.setTextValorMes10RealEd("" + valorReal(codigoPoaActividad, 10));
/*  883 */       this.pagHTML.setTextValorMes11Ed("" + reg.getValorMes11());
/*  884 */       this.pagHTML.setTextValorMes11RealEd("" + valorReal(codigoPoaActividad, 11));
/*  885 */       this.pagHTML.setTextValorMes12Ed("" + reg.getValorMes12());
/*  886 */       this.pagHTML.setTextValorMes12RealEd("" + valorReal(codigoPoaActividad, 12));
/*  887 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/*  888 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/*  889 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/*  890 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/*  891 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*      */       
/*  893 */       this.pagHTML.setTextAvanceMes1Ed("" + avance(codigoPoaActividad, 1));
/*  894 */       this.pagHTML.setTextAvanceMes2Ed("" + avance(codigoPoaActividad, 2));
/*  895 */       this.pagHTML.setTextAvanceMes3Ed("" + avance(codigoPoaActividad, 3));
/*  896 */       this.pagHTML.setTextAvanceMes4Ed("" + avance(codigoPoaActividad, 4));
/*  897 */       this.pagHTML.setTextAvanceMes5Ed("" + avance(codigoPoaActividad, 5));
/*  898 */       this.pagHTML.setTextAvanceMes6Ed("" + avance(codigoPoaActividad, 6));
/*  899 */       this.pagHTML.setTextAvanceMes7Ed("" + avance(codigoPoaActividad, 7));
/*  900 */       this.pagHTML.setTextAvanceMes8Ed("" + avance(codigoPoaActividad, 8));
/*  901 */       this.pagHTML.setTextAvanceMes9Ed("" + avance(codigoPoaActividad, 9));
/*  902 */       this.pagHTML.setTextAvanceMes10Ed("" + avance(codigoPoaActividad, 10));
/*  903 */       this.pagHTML.setTextAvanceMes11Ed("" + avance(codigoPoaActividad, 11));
/*  904 */       this.pagHTML.setTextAvanceMes12Ed("" + avance(codigoPoaActividad, 12));
/*      */       
/*  906 */       this.pagHTML.setTextLogroMes1Ed("" + logro(codigoPoaActividad, 1));
/*  907 */       this.pagHTML.setTextLogroMes2Ed("" + logro(codigoPoaActividad, 2));
/*  908 */       this.pagHTML.setTextLogroMes3Ed("" + logro(codigoPoaActividad, 3));
/*  909 */       this.pagHTML.setTextLogroMes4Ed("" + logro(codigoPoaActividad, 4));
/*  910 */       this.pagHTML.setTextLogroMes5Ed("" + logro(codigoPoaActividad, 5));
/*  911 */       this.pagHTML.setTextLogroMes6Ed("" + logro(codigoPoaActividad, 6));
/*  912 */       this.pagHTML.setTextLogroMes7Ed("" + logro(codigoPoaActividad, 7));
/*  913 */       this.pagHTML.setTextLogroMes8Ed("" + logro(codigoPoaActividad, 8));
/*  914 */       this.pagHTML.setTextLogroMes9Ed("" + logro(codigoPoaActividad, 9));
/*  915 */       this.pagHTML.setTextLogroMes10Ed("" + logro(codigoPoaActividad, 10));
/*  916 */       this.pagHTML.setTextLogroMes11Ed("" + logro(codigoPoaActividad, 11));
/*  917 */       this.pagHTML.setTextLogroMes12Ed("" + logro(codigoPoaActividad, 12));
/*      */       
/*  919 */       this.pagHTML.setTextRetrasoMes1Ed("" + retraso(codigoPoaActividad, 1));
/*  920 */       this.pagHTML.setTextRetrasoMes2Ed("" + retraso(codigoPoaActividad, 2));
/*  921 */       this.pagHTML.setTextRetrasoMes3Ed("" + retraso(codigoPoaActividad, 3));
/*  922 */       this.pagHTML.setTextRetrasoMes4Ed("" + retraso(codigoPoaActividad, 4));
/*  923 */       this.pagHTML.setTextRetrasoMes5Ed("" + retraso(codigoPoaActividad, 5));
/*  924 */       this.pagHTML.setTextRetrasoMes6Ed("" + retraso(codigoPoaActividad, 6));
/*  925 */       this.pagHTML.setTextRetrasoMes7Ed("" + retraso(codigoPoaActividad, 7));
/*  926 */       this.pagHTML.setTextRetrasoMes8Ed("" + retraso(codigoPoaActividad, 8));
/*  927 */       this.pagHTML.setTextRetrasoMes9Ed("" + retraso(codigoPoaActividad, 9));
/*  928 */       this.pagHTML.setTextRetrasoMes10Ed("" + retraso(codigoPoaActividad, 10));
/*  929 */       this.pagHTML.setTextRetrasoMes11Ed("" + retraso(codigoPoaActividad, 11));
/*  930 */       this.pagHTML.setTextRetrasoMes12Ed("" + retraso(codigoPoaActividad, 12));
/*      */       
/*  932 */       totalEsperado += reg.getValorMes1();
/*  933 */       totalEsperado += reg.getValorMes2();
/*  934 */       totalEsperado += reg.getValorMes3();
/*  935 */       totalEsperado += reg.getValorMes4();
/*  936 */       totalEsperado += reg.getValorMes5();
/*  937 */       totalEsperado += reg.getValorMes6();
/*  938 */       totalEsperado += reg.getValorMes7();
/*  939 */       totalEsperado += reg.getValorMes8();
/*  940 */       totalEsperado += reg.getValorMes9();
/*  941 */       totalEsperado += reg.getValorMes10();
/*  942 */       totalEsperado += reg.getValorMes11();
/*  943 */       totalEsperado += reg.getValorMes12();
/*  944 */       for (int i = 1; i <= 12; i++) {
/*  945 */         totalEjecutado += valorReal(codigoPoaActividad, i);
/*      */       }
/*  947 */       this.pagHTML.setTextTotalEsperado("" + totalEsperado);
/*  948 */       this.pagHTML.setTextTotalEjecutado("" + totalEjecutado);
/*  949 */       if (totalEsperado > 0) {
/*  950 */         this.pagHTML.setTextPorcentajeEjecucion("" + (totalEjecutado * 100 / totalEsperado));
/*      */       } else {
/*  952 */         this.pagHTML.setTextPorcentajeEjecucion("0");
/*      */       } 
/*      */       
/*  955 */       this.pagHTML.getElementCodigoPoaActividadKey().setValue("" + reg.getCodigoPoaActividad());
/*  956 */       this.pagHTML.getElement_operacion().setValue("P");
/*      */     } 
/*  958 */     activarVista("editar");
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
/*  969 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*      */     
/*  971 */     Varios oVarios = new Varios();
/*  972 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Poa_PoaMaestroActividadesAct");
/*  973 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Poa_PoaMaestroActividadesDel");
/*  974 */     if (!oPermisoAct) {
/*  975 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/*  976 */       elem.getParentNode().removeChild(elem);
/*  977 */       elem = this.pagHTML.getElementBtnGrabar();
/*  978 */       elem.getParentNode().removeChild(elem);
/*  979 */       elem = this.pagHTML.getElementBtnModificar();
/*  980 */       elem.getParentNode().removeChild(elem);
/*      */     } 
/*  982 */     if (!oPermisoDel) {
/*  983 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/*  984 */       elem.getParentNode().removeChild(elem);
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
/*  995 */     if (!vista.equals("nuevo")) {
/*  996 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/*  997 */       sel.getParentNode().removeChild(sel);
/*      */     } 
/*  999 */     if (!vista.equals("editar")) {
/* 1000 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 1001 */       sel.getParentNode().removeChild(sel);
/*      */     } 
/* 1003 */     if (!vista.equals("consulta")) {
/* 1004 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 1005 */       sel.getParentNode().removeChild(sel);
/* 1006 */       sel = this.pagHTML.getElementDivResultados();
/* 1007 */       sel.getParentNode().removeChild(sel);
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
/* 1021 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 1022 */     atrib.setValue(valor);
/* 1023 */     return atrib;
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
/* 1036 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 1037 */     Element enlace = this.pagHTML.createElement("a");
/* 1038 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 1039 */     enlace.appendChild(hijo);
/* 1040 */     Attr donde = this.pagHTML.createAttribute("href");
/* 1041 */     donde.setValue(vinculo);
/* 1042 */     enlace.setAttributeNode(donde);
/* 1043 */     td.appendChild(enlace);
/* 1044 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 1045 */     return td;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private HTMLElement newtd(String contenido) {
/* 1055 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 1056 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 1057 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 1058 */     return td;
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
/*      */   private void comboMultivalores(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco) {
/* 1073 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 1074 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 1075 */     rs.close();
/* 1076 */     if (dejarBlanco) {
/* 1077 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1078 */       op.setValue("");
/* 1079 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 1080 */       combo.appendChild(op);
/*      */     } 
/* 1082 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 1083 */     while (iterator.hasNext()) {
/* 1084 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 1085 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1086 */       op.setValue("" + reg.getCodigo());
/* 1087 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 1088 */       if (defecto.equals(reg.getCodigo())) {
/* 1089 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 1090 */         escogida.setValue("on");
/* 1091 */         op.setAttributeNode(escogida);
/*      */       } 
/* 1093 */       combo.appendChild(op);
/*      */     } 
/* 1095 */     arr.clear();
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
/* 1116 */     if (dejarBlanco) {
/* 1117 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1118 */       op.setValue("");
/* 1119 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 1120 */       combo.appendChild(op);
/*      */     } 
/* 1122 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 1123 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/* 1124 */     rsTGen.close();
/* 1125 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 1126 */     while (iterator.hasNext()) {
/* 1127 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 1128 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1129 */       op.setValue("" + regGeneral.getCodigoS());
/* 1130 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 1131 */       if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
/* 1132 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 1133 */         escogida.setValue("on");
/* 1134 */         op.setAttributeNode(escogida);
/*      */       } 
/* 1136 */       combo.appendChild(op);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void agregarVariables(HttpPresentationComms comms, int codigoPoa, int codigoPoaActividad) throws HttpPresentationException, KeywordValueException {
/* 1145 */     eliminarVariables(comms, codigoPoa, codigoPoaActividad);
/* 1146 */     String variables = comms.request.getParameter("totalVariables");
/* 1147 */     String[] variable = variables.split(";");
/* 1148 */     for (int i = 0; i < variable.length; i++) {
/* 1149 */       String campo = variable[i];
/* 1150 */       PoaMaestroMultivaloresDAO rs = new PoaMaestroMultivaloresDAO();
/*      */       try {
/* 1152 */         String elUsuario = "" + comms.session.getUser().getName();
/* 1153 */         rs.crearRegistro(codigoPoa, codigoPoaActividad, "POA_VARIABLES_MEDICION", campo, "A", elUsuario);
/*      */       }
/* 1155 */       catch (Exception e) {}
/*      */ 
/*      */       
/* 1158 */       rs.close();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void eliminarVariables(HttpPresentationComms comms, int codigoPoa, int codigoPoaActividad) throws HttpPresentationException, KeywordValueException {
/* 1165 */     PoaMaestroMultivaloresDAO rs = new PoaMaestroMultivaloresDAO();
/* 1166 */     rs.eliminarRegistro(codigoPoa, codigoPoaActividad, "POA_VARIABLES_MEDICION", "");
/* 1167 */     rs.close();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void agregarMediosVerificacion(HttpPresentationComms comms, int codigoPoaActividad, int codigoPoa) throws HttpPresentationException, KeywordValueException {
/* 1173 */     eliminarMediosVerificacion(comms, codigoPoaActividad, codigoPoa);
/* 1174 */     String variables = comms.request.getParameter("totalMedios");
/* 1175 */     String[] variable = variables.split(";");
/* 1176 */     for (int i = 0; i < variable.length; i++) {
/* 1177 */       String campo = variable[i];
/* 1178 */       PoaMaestroMedioVerificacionDAO rs = new PoaMaestroMedioVerificacionDAO();
/*      */       try {
/* 1180 */         String elUsuario = "" + comms.session.getUser().getName();
/* 1181 */         rs.crearRegistro(codigoPoa, codigoPoaActividad, Integer.parseInt(campo), "A", elUsuario);
/* 1182 */       } catch (Exception e) {}
/*      */ 
/*      */       
/* 1185 */       rs.close();
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
/*      */   private void eliminarMediosVerificacion(HttpPresentationComms comms, int codigoPoaActividad, int codigoPoa) throws HttpPresentationException, KeywordValueException {
/* 1199 */     PoaMaestroMedioVerificacionDAO rs = new PoaMaestroMedioVerificacionDAO();
/* 1200 */     rs.eliminarRegistro(codigoPoaActividad);
/* 1201 */     rs.close();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void agregarResponsableActividad(HttpPresentationComms comms, int codigoPoaActividad, int actividad) throws HttpPresentationException, KeywordValueException {
/* 1207 */     eliminarResponsableActividad(comms, codigoPoaActividad);
/* 1208 */     String variables = comms.request.getParameter("totalResponsables");
/* 1209 */     String[] variable = variables.split(";");
/* 1210 */     for (int i = 0; i < variable.length; i++) {
/* 1211 */       String campo = variable[i];
/* 1212 */       PoaActividadResponsableDAO rs = new PoaActividadResponsableDAO();
/*      */       try {
/* 1214 */         String elUsuario = "" + comms.session.getUser().getName();
/* 1215 */         rs.crearRegistro(actividad, Integer.parseInt(campo), codigoPoaActividad, "A", elUsuario);
/* 1216 */       } catch (Exception e) {}
/*      */ 
/*      */       
/* 1219 */       rs.close();
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
/*      */   private void eliminarResponsableActividad(HttpPresentationComms comms, int codigoPoaActividad) throws HttpPresentationException, KeywordValueException {
/* 1233 */     PoaActividadResponsableDAO rs = new PoaActividadResponsableDAO();
/* 1234 */     rs.eliminarRegistro(codigoPoaActividad);
/* 1235 */     rs.close();
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
/*      */   private void agregarInsumo(HttpPresentationComms comms, int codigoPoaActividad) throws HttpPresentationException, KeywordValueException {
/* 1250 */     eliminarInsumo(comms, codigoPoaActividad);
/* 1251 */     String variables = comms.request.getParameter("totalInsumos");
/* 1252 */     String[] variable = variables.split(";");
/* 1253 */     for (int i = 0; i < variable.length; i++) {
/* 1254 */       String[] campos = variable[i].split(",");
/* 1255 */       PoaMaestroInsumoProveedorDAO rs = new PoaMaestroInsumoProveedorDAO();
/* 1256 */       String elUsuario = "" + comms.session.getUser().getName();
/*      */       try {
/* 1258 */         if (campos.length == 3) {
/* 1259 */           rs.crearRegistro(Integer.parseInt(campos[0]), Integer.parseInt(campos[1]), codigoPoaActividad, campos[2], campos[3], "A", elUsuario);
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */ 
/*      */           
/* 1267 */           rs.crearRegistro(Integer.parseInt(campos[0]), Integer.parseInt(campos[1]), codigoPoaActividad, campos[2], campos[3], "A", elUsuario);
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 1275 */       catch (Exception e) {}
/*      */       
/* 1277 */       rs.close();
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
/*      */   private void eliminarInsumo(HttpPresentationComms comms, int codigoPoaActividad) throws HttpPresentationException, KeywordValueException {
/* 1291 */     PoaMaestroInsumoProveedorDAO rs = new PoaMaestroInsumoProveedorDAO();
/* 1292 */     rs.eliminarRegistro(codigoPoaActividad);
/* 1293 */     rs.close();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private String cargarVariablesMedicion(HttpPresentationComms comms, int codigoPoa) throws HttpPresentationException, KeywordValueException {
/* 1299 */     PoaMaestroMultivaloresDAO rs = new PoaMaestroMultivaloresDAO();
/* 1300 */     Collection<PoaMaestroMultivaloresDTO> arr = rs.cargarTodos(codigoPoa, "POA_VARIABLES_MEDICION");
/* 1301 */     rs.close();
/* 1302 */     Iterator<PoaMaestroMultivaloresDTO> iterator = arr.iterator();
/*      */     
/* 1304 */     String script = "var variables=new Array(" + arr.size() + ");";
/* 1305 */     script = script + " var iCont=0;";
/* 1306 */     while (iterator.hasNext()) {
/* 1307 */       PoaMaestroMultivaloresDTO reg = (PoaMaestroMultivaloresDTO)iterator.next();
/* 1308 */       SisMultiValoresDAO mu = new SisMultiValoresDAO();
/* 1309 */       SisMultiValoresDTO variable = mu.cargarRegistro(reg.getTabla(), reg.getValor());
/* 1310 */       mu.close();
/* 1311 */       script = script + " variables[iCont++]=new variable(" + "'" + variable.getValor() + "'," + "'" + variable.getDescripcion() + "'" + ");";
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1317 */     return script;
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
/*      */   private String cargarMediosVerificacion(HttpPresentationComms comms, int codigoPoaActividad) throws HttpPresentationException, KeywordValueException {
/* 1331 */     PoaMaestroMedioVerificacionDAO rs = new PoaMaestroMedioVerificacionDAO();
/* 1332 */     Collection<PoaMaestroMedioVerificacionDTO> arr = rs.cargarTodos(codigoPoaActividad);
/* 1333 */     rs.close();
/* 1334 */     Iterator<PoaMaestroMedioVerificacionDTO> iterator = arr.iterator();
/*      */     
/* 1336 */     String script = "var medios=new Array(" + arr.size() + ");";
/* 1337 */     script = script + " var iCont=0;";
/* 1338 */     while (iterator.hasNext()) {
/* 1339 */       PoaMaestroMedioVerificacionDTO reg = (PoaMaestroMedioVerificacionDTO)iterator.next();
/* 1340 */       PoaMediosVerificacionDAO mu = new PoaMediosVerificacionDAO();
/* 1341 */       PoaMediosVerificacionDTO variable = mu.cargarRegistro(reg.getIdMedioVerificacion());
/* 1342 */       mu.close();
/* 1343 */       script = script + " medios[iCont++]=new medio(" + "'" + variable.getCODIGOMEDIOVERIFICACION() + "'," + "'" + variable.getDescripcion() + "'" + ");";
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1349 */     return script;
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
/*      */   private String cargarResponsablesActividad(HttpPresentationComms comms, int codigoPoaActividad) throws HttpPresentationException, KeywordValueException {
/* 1362 */     PoaActividadResponsableDAO rs = new PoaActividadResponsableDAO();
/* 1363 */     Collection<PoaActividadResponsableDTO> arr = rs.cargarTodos(codigoPoaActividad);
/* 1364 */     rs.close();
/* 1365 */     Iterator<PoaActividadResponsableDTO> iterator = arr.iterator();
/*      */     
/* 1367 */     String script = "var responsables=new Array(" + arr.size() + ");";
/* 1368 */     script = script + " var iCont=0;";
/* 1369 */     while (iterator.hasNext()) {
/* 1370 */       PoaActividadResponsableDTO reg = (PoaActividadResponsableDTO)iterator.next();
/* 1371 */       PersonasAreaDAO mu = new PersonasAreaDAO();
/* 1372 */       PersonasAreaDTO variable = mu.cargarPersona(reg.getIdUsuario());
/* 1373 */       mu.close();
/* 1374 */       script = script + " responsables[iCont++]=new responsableAct(" + "'" + variable.getCodigoEmpleado() + "'," + "'" + variable.getNombrePersona() + "'" + ");";
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1379 */     return script;
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
/*      */   private String cargarInsumos(HttpPresentationComms comms, int codigoPoaActividad) throws HttpPresentationException, KeywordValueException {
/* 1392 */     PoaMaestroInsumoProveedorDAO rs = new PoaMaestroInsumoProveedorDAO();
/* 1393 */     Collection<PoaMaestroInsumoProveedorDTO> arr = rs.cargarTodos(codigoPoaActividad);
/* 1394 */     rs.close();
/* 1395 */     Iterator<PoaMaestroInsumoProveedorDTO> iterator = arr.iterator();
/*      */     
/* 1397 */     String script = "var insumos=new Array(" + arr.size() + ");";
/* 1398 */     script = script + " var iCont=0;";
/* 1399 */     while (iterator.hasNext()) {
/* 1400 */       PoaMaestroInsumoProveedorDTO reg = (PoaMaestroInsumoProveedorDTO)iterator.next();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1405 */       PoaProveedoresDAO prove = new PoaProveedoresDAO();
/* 1406 */       PoaProveedoresDTO regProveedor = prove.cargarRegistro(reg.getIdProveedor());
/* 1407 */       prove.close();
/*      */ 
/*      */       
/* 1410 */       PoaInsumosDAO is = new PoaInsumosDAO();
/* 1411 */       PoaInsumosDTO insumo = is.cargarRegistro(reg.getIdInsumo());
/* 1412 */       is.close();
/* 1413 */       script = script + " insumos[iCont++]=new insumo(" + "'" + insumo.getCodigoInsumo() + "'," + "'" + insumo.getDescrpcion() + "'," + "'" + regProveedor.getCodigoProveedor() + "'," + "'" + regProveedor.getDescripcion() + "'," + "'" + reg.getFechaProgramadaEntrega() + "'," + "'" + reg.getFechaRealEntrega() + "'" + ");";
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1423 */     return script;
/*      */   }
/*      */ 
/*      */   
/*      */   private String getMes(int mes) {
/* 1428 */     switch (mes) {
/*      */       case 1:
/* 1430 */         return "Enero";
/*      */       case 2:
/* 1432 */         return "Febrero";
/*      */       
/*      */       case 3:
/* 1435 */         return "Marzo";
/*      */       case 4:
/* 1437 */         return "Abril";
/*      */       case 5:
/* 1439 */         return "Mayo";
/*      */       case 6:
/* 1441 */         return "Junio";
/*      */       case 7:
/* 1443 */         return "Julio";
/*      */       case 8:
/* 1445 */         return "Agosto";
/*      */       case 9:
/* 1447 */         return "Septiembre";
/*      */       case 10:
/* 1449 */         return "Octubre";
/*      */       case 11:
/* 1451 */         return "Noviembre";
/*      */       case 12:
/* 1453 */         return "Diciembre";
/*      */     } 
/*      */ 
/*      */     
/* 1457 */     return "";
/*      */   }
/*      */ 
/*      */   
/*      */   private int valorReal(int codigoPoa, int mes) {
/*      */     try {
/* 1463 */       PoaLogrosDAO rs = new PoaLogrosDAO();
/* 1464 */       PoaLogrosDTO reg = rs.cargarRegistro(codigoPoa, getMes(mes));
/* 1465 */       rs.close();
/* 1466 */       return reg.getEjecucion();
/* 1467 */     } catch (Exception e) {
/*      */ 
/*      */       
/* 1470 */       return 0;
/*      */     } 
/*      */   }
/*      */   private String avance(int codigoPoa, int mes) {
/*      */     try {
/* 1475 */       PoaLogrosDAO rs = new PoaLogrosDAO();
/* 1476 */       PoaLogrosDTO reg = rs.cargarRegistro(codigoPoa, getMes(mes));
/* 1477 */       rs.close();
/* 1478 */       return reg.getAvances();
/* 1479 */     } catch (Exception e) {
/*      */ 
/*      */       
/* 1482 */       return "";
/*      */     } 
/*      */   }
/*      */   private String logro(int codigoPoa, int mes) {
/*      */     try {
/* 1487 */       PoaLogrosDAO rs = new PoaLogrosDAO();
/* 1488 */       PoaLogrosDTO reg = rs.cargarRegistro(codigoPoa, getMes(mes));
/* 1489 */       rs.close();
/* 1490 */       return reg.getLogrosYResultados();
/* 1491 */     } catch (Exception e) {
/*      */ 
/*      */       
/* 1494 */       return "";
/*      */     } 
/*      */   }
/*      */   private String retraso(int codigoPoa, int mes) {
/*      */     try {
/* 1499 */       PoaLogrosDAO rs = new PoaLogrosDAO();
/* 1500 */       PoaLogrosDTO reg = rs.cargarRegistro(codigoPoa, getMes(mes));
/* 1501 */       rs.close();
/* 1502 */       return reg.getRetrasosDificultades();
/* 1503 */     } catch (Exception e) {
/*      */ 
/*      */       
/* 1506 */       return "";
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PoaMaestroActividades.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */