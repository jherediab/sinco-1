/*      */ package sinco.presentation;
/*      */ 
/*      */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*      */ import com.lutris.util.KeywordValueException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import org.w3c.dom.Attr;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.html.HTMLElement;
/*      */ import org.w3c.dom.html.HTMLImageElement;
/*      */ import org.w3c.dom.html.HTMLInputElement;
/*      */ import org.w3c.dom.html.HTMLOptionElement;
/*      */ import org.w3c.dom.html.HTMLSelectElement;
/*      */ import org.w3c.dom.html.HTMLTableElement;
/*      */ import org.w3c.dom.html.HTMLTableSectionElement;
/*      */ import sinco.business.CaracteristicasDTO;
/*      */ import sinco.business.DetalleSolicitudDTO;
/*      */ import sinco.business.IndicadorVariablesDTO;
/*      */ import sinco.business.IndicadoresDTO;
/*      */ import sinco.business.RespuestaBD;
/*      */ import sinco.business.SisMultiValoresDTO;
/*      */ import sinco.business.SisUnidadesMedidaDTO;
/*      */ import sinco.business.TGeneralDTO;
/*      */ import sinco.business.TemporalDTO;
/*      */ import sinco.business.Utilidades;
/*      */ import sinco.data.CaracteristicasDAO;
/*      */ import sinco.data.CaracteristicasValorDAO;
/*      */ import sinco.data.DetalleSolicitudDAO;
/*      */ import sinco.data.IndicadorCaracteristicaDAO;
/*      */ import sinco.data.IndicadorVariablesDAO;
/*      */ import sinco.data.IndicadoresDAO;
/*      */ import sinco.data.SisMultiValoresDAO;
/*      */ import sinco.data.SisUnidadesMedidaDAO;
/*      */ import sinco.data.TGeneralDAO;
/*      */ import sinco.presentation.Indicadores;
/*      */ import sinco.presentation.IndicadoresHTML;
/*      */ import sinco.spec.MenuDO;
/*      */ import sinco.spec.Varios;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class Indicadores
/*      */   implements HttpPresentation
/*      */ {
/*      */   private IndicadoresHTML pagHTML;
/*      */   
/*      */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*   54 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*   55 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*      */     }
/*      */     
/*   58 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*   59 */     String _operacion = comms.request.getParameter("_operacion");
/*   60 */     if (_operacion == null || _operacion.length() == 0) {
/*   61 */       _operacion = "X";
/*      */     }
/*      */     
/*   64 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*   65 */       creacion(comms);
/*      */     }
/*      */     
/*   68 */     this.pagHTML = (IndicadoresHTML)comms.xmlcFactory.create(IndicadoresHTML.class);
/*   69 */     permisos(comms);
/*      */     
/*   71 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*   72 */       listar(comms, _operacion);
/*      */     }
/*      */     
/*   75 */     if (_operacion.equals("P")) {
/*   76 */       editar(comms);
/*      */     }
/*   78 */     else if (_operacion.equals("Nuevo")) {
/*   79 */       nuevo(comms);
/*      */     } 
/*      */     
/*   82 */     if (_operacion.equals("V")) {
/*   83 */       verRegistro(comms);
/*      */     }
/*      */     
/*   86 */     if (_operacion.equals("formula")) {
/*   87 */       activarVista("variables");
/*   88 */       caracteristicas(comms);
/*      */     } 
/*      */     
/*   91 */     if (_operacion.equals("guardarVariables")) {
/*   92 */       guardarVariables(comms);
/*      */     }
/*      */     
/*   95 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*   96 */     comms.response.writeDOM(this.pagHTML);
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
/*  108 */     String _operacion = comms.request.getParameter("_operacion");
/*  109 */     String elUsuario = "" + comms.session.getUser().getName();
/*  110 */     String codigoIndicador = comms.request.getParameter("codigoIndicador");
/*      */ 
/*      */ 
/*      */     
/*  114 */     RespuestaBD rta = new RespuestaBD();
/*  115 */     if (_operacion.equals("E")) {
/*  116 */       IndicadoresDAO rs = new IndicadoresDAO();
/*  117 */       if (!rs.getEstadoConexion()) {
/*  118 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */       }
/*  120 */       rta = rs.eliminarRegistro(codigoIndicador);
/*  121 */       if (!rta.isRta()) {
/*  122 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorIndicadores&p1=" + rta.getMensaje()));
/*      */       }
/*  124 */       rs.close();
/*  125 */       String sPagina = "Indicadores.po?_operacion=X";
/*  126 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*      */     } 
/*  128 */     String nombreIndicador = comms.request.getParameter("nombreIndicador");
/*  129 */     int proceso = 0;
/*      */     try {
/*  131 */       proceso = Integer.parseInt(comms.request.getParameter("proceso"));
/*      */     }
/*  133 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  136 */     int ciclo = 0;
/*      */     try {
/*  138 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*      */     }
/*  140 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  143 */     int area = 0;
/*      */     try {
/*  145 */       area = Integer.parseInt(comms.request.getParameter("area"));
/*      */     }
/*  147 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  150 */     int objetivoEstrategico = 0;
/*      */     try {
/*  152 */       objetivoEstrategico = Integer.parseInt(comms.request.getParameter("objetivoEstrategico"));
/*      */     }
/*  154 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  157 */     String indicadorAcuerdo = comms.request.getParameter("indicadorAcuerdo");
/*  158 */     if (indicadorAcuerdo == null) {
/*  159 */       indicadorAcuerdo = "";
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  164 */     String tipoIndicador = comms.request.getParameter("tipoIndicador");
/*  165 */     String proyectoInversion = comms.request.getParameter("proyectoInversion");
/*  166 */     if (proyectoInversion == null) {
/*  167 */       proyectoInversion = "";
/*      */     }
/*      */ 
/*      */     
/*  171 */     int metaPlanDeDesarrollo = 0;
/*      */     try {
/*  173 */       metaPlanDeDesarrollo = Integer.parseInt(comms.request.getParameter("metaPlanDeDesarrollo"));
/*      */     }
/*  175 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  178 */     int metaProyecto = 0;
/*      */     try {
/*  180 */       metaProyecto = Integer.parseInt(comms.request.getParameter("metaProyecto"));
/*      */     }
/*  182 */     catch (Exception e) {}
/*      */     
/*  184 */     String variable = comms.request.getParameter("variable");
/*      */     
/*  186 */     String objetivoIndicador = comms.request.getParameter("objetivoIndicador");
/*  187 */     String prioridadEnProducto = comms.request.getParameter("prioridadEnProducto");
/*      */ 
/*      */ 
/*      */     
/*  191 */     String unidadMedida = comms.request.getParameter("unidadMedida");
/*  192 */     if (unidadMedida == null) {
/*  193 */       unidadMedida = "";
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  200 */     String frecuenciaMedicion = comms.request.getParameter("frecuenciaMedicion");
/*  201 */     String formula = comms.request.getParameter("formula");
/*  202 */     if (formula == null) {
/*  203 */       formula = "";
/*      */     }
/*      */     
/*  206 */     String mes1 = comms.request.getParameter("mes1");
/*  207 */     if (mes1 == null) {
/*  208 */       mes1 = "N";
/*      */     }
/*  210 */     String mes2 = comms.request.getParameter("mes2");
/*  211 */     if (mes2 == null) {
/*  212 */       mes2 = "N";
/*      */     }
/*  214 */     String mes3 = comms.request.getParameter("mes3");
/*  215 */     if (mes3 == null) {
/*  216 */       mes3 = "N";
/*      */     }
/*  218 */     String mes4 = comms.request.getParameter("mes4");
/*  219 */     if (mes4 == null) {
/*  220 */       mes4 = "N";
/*      */     }
/*  222 */     String mes5 = comms.request.getParameter("mes5");
/*  223 */     if (mes5 == null) {
/*  224 */       mes5 = "N";
/*      */     }
/*  226 */     String mes6 = comms.request.getParameter("mes6");
/*  227 */     if (mes6 == null) {
/*  228 */       mes6 = "N";
/*      */     }
/*  230 */     String mes7 = comms.request.getParameter("mes7");
/*  231 */     if (mes7 == null) {
/*  232 */       mes7 = "N";
/*      */     }
/*  234 */     String mes8 = comms.request.getParameter("mes8");
/*  235 */     if (mes8 == null) {
/*  236 */       mes8 = "N";
/*      */     }
/*  238 */     String mes9 = comms.request.getParameter("mes9");
/*  239 */     if (mes9 == null) {
/*  240 */       mes9 = "N";
/*      */     }
/*  242 */     String mes10 = comms.request.getParameter("mes10");
/*  243 */     if (mes10 == null) {
/*  244 */       mes10 = "N";
/*      */     }
/*  246 */     String mes11 = comms.request.getParameter("mes11");
/*  247 */     if (mes11 == null) {
/*  248 */       mes11 = "N";
/*      */     }
/*  250 */     String mes12 = comms.request.getParameter("mes12");
/*  251 */     if (mes12 == null) {
/*  252 */       mes12 = "N";
/*      */     }
/*  254 */     int valormes1 = 0;
/*      */     try {
/*  256 */       valormes1 = Integer.parseInt(comms.request.getParameter("valormes1"));
/*      */     }
/*  258 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  261 */     int valormes2 = 0;
/*      */     try {
/*  263 */       valormes2 = Integer.parseInt(comms.request.getParameter("valormes2"));
/*      */     }
/*  265 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  268 */     int valormes3 = 0;
/*      */     try {
/*  270 */       valormes3 = Integer.parseInt(comms.request.getParameter("valormes3"));
/*      */     }
/*  272 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  275 */     int valormes4 = 0;
/*      */     try {
/*  277 */       valormes4 = Integer.parseInt(comms.request.getParameter("valormes4"));
/*      */     }
/*  279 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  282 */     int valormes5 = 0;
/*      */     try {
/*  284 */       valormes5 = Integer.parseInt(comms.request.getParameter("valormes5"));
/*      */     }
/*  286 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  289 */     int valormes6 = 0;
/*      */     try {
/*  291 */       valormes6 = Integer.parseInt(comms.request.getParameter("valormes6"));
/*      */     }
/*  293 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  296 */     int valormes7 = 0;
/*      */     try {
/*  298 */       valormes7 = Integer.parseInt(comms.request.getParameter("valormes7"));
/*      */     }
/*  300 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  303 */     int valormes8 = 0;
/*      */     try {
/*  305 */       valormes8 = Integer.parseInt(comms.request.getParameter("valormes8"));
/*      */     }
/*  307 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  310 */     int valormes9 = 0;
/*      */     try {
/*  312 */       valormes9 = Integer.parseInt(comms.request.getParameter("valormes9"));
/*      */     }
/*  314 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  317 */     int valormes10 = 0;
/*      */     try {
/*  319 */       valormes10 = Integer.parseInt(comms.request.getParameter("valormes10"));
/*      */     }
/*  321 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  324 */     int valormes11 = 0;
/*      */     try {
/*  326 */       valormes11 = Integer.parseInt(comms.request.getParameter("valormes11"));
/*      */     }
/*  328 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  331 */     int valormes12 = 0;
/*      */     try {
/*  333 */       valormes12 = Integer.parseInt(comms.request.getParameter("valormes12"));
/*      */     }
/*  335 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  338 */     String estado = comms.request.getParameter("estado");
/*      */     
/*  340 */     String productoPMR = comms.request.getParameter("productoPMR");
/*  341 */     if (productoPMR == null) {
/*  342 */       productoPMR = "";
/*      */     }
/*      */     
/*  345 */     IndicadoresDAO rs = new IndicadoresDAO();
/*  346 */     if (!rs.getEstadoConexion()) {
/*  347 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */     }
/*      */     
/*  350 */     int metaTotalProgramada = 0;
/*      */     try {
/*  352 */       metaTotalProgramada = Integer.parseInt(comms.request.getParameter("metaTotalProgramada"));
/*      */     }
/*  354 */     catch (Exception e) {}
/*      */     
/*  356 */     int metaEstaVigencia = 0;
/*      */     try {
/*  358 */       metaEstaVigencia = Integer.parseInt(comms.request.getParameter("metaEstaVigencia"));
/*      */     }
/*  360 */     catch (Exception e) {}
/*      */     
/*  362 */     int acumuladoVigencias = 0;
/*      */     try {
/*  364 */       acumuladoVigencias = Integer.parseInt(comms.request.getParameter("acumuladoVigencias"));
/*      */     }
/*  366 */     catch (Exception e) {}
/*      */     
/*  368 */     int acumuladoEstaVigencia = 0;
/*      */     try {
/*  370 */       acumuladoEstaVigencia = Integer.parseInt(comms.request.getParameter("acumuladoEstaVigencia"));
/*      */     }
/*  372 */     catch (Exception e) {}
/*      */     
/*  374 */     int logroTotal = 0;
/*      */     try {
/*  376 */       logroTotal = Integer.parseInt(comms.request.getParameter("logroTotal"));
/*      */     }
/*  378 */     catch (Exception e) {}
/*      */     
/*  380 */     int logroTotalAcumulado = 0;
/*      */     try {
/*  382 */       logroTotalAcumulado = Integer.parseInt(comms.request.getParameter("logroTotalAcumulado"));
/*      */     }
/*  384 */     catch (Exception e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  389 */     if (_operacion.equals("C")) {
/*  390 */       rta = rs.crearRegistro(codigoIndicador, nombreIndicador, proceso, ciclo, area, objetivoEstrategico, indicadorAcuerdo, tipoIndicador, proyectoInversion, metaPlanDeDesarrollo, metaProyecto, objetivoIndicador, prioridadEnProducto, unidadMedida, frecuenciaMedicion, formula, mes1, mes2, mes3, mes4, mes5, mes6, mes7, mes8, mes9, mes10, mes11, mes12, valormes1, valormes2, valormes3, valormes4, valormes5, valormes6, valormes7, valormes8, valormes9, valormes10, valormes11, valormes12, estado, productoPMR, elUsuario, metaTotalProgramada, metaEstaVigencia, acumuladoVigencias, acumuladoEstaVigencia, logroTotal, logroTotalAcumulado);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  445 */       rta = rs.modificarRegistro(codigoIndicador, nombreIndicador, proceso, ciclo, area, objetivoEstrategico, indicadorAcuerdo, tipoIndicador, proyectoInversion, metaPlanDeDesarrollo, metaProyecto, objetivoIndicador, prioridadEnProducto, unidadMedida, frecuenciaMedicion, formula, mes1, mes2, mes3, mes4, mes5, mes6, mes7, mes8, mes9, mes10, mes11, mes12, valormes1, valormes2, valormes3, valormes4, valormes5, valormes6, valormes7, valormes8, valormes9, valormes10, valormes11, valormes12, estado, productoPMR, elUsuario, metaTotalProgramada, metaEstaVigencia, acumuladoVigencias, acumuladoEstaVigencia, logroTotal, logroTotalAcumulado);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  496 */     rs.close();
/*      */     
/*  498 */     if (!rta.isRta()) {
/*  499 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorIndicadores&p1=" + rta.getMensaje()));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  505 */     String sPagina = "Indicadores.po?_operacion=L";
/*  506 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/*  517 */     String codigoIndicador = "";
/*      */     try {
/*  519 */       codigoIndicador = comms.request.getParameter("codigoIndicador");
/*      */     }
/*  521 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  524 */     IndicadoresDAO rs = new IndicadoresDAO();
/*  525 */     if (!rs.getEstadoConexion()) {
/*  526 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */     }
/*  528 */     IndicadoresDTO reg = rs.cargarRegistro(codigoIndicador);
/*  529 */     rs.close();
/*  530 */     if (reg != null) {
/*  531 */       this.pagHTML.getElementCodigoIndicador().setValue("" + reg.getCodigoIndicador());
/*  532 */       this.pagHTML.getElementNombreIndicador().setValue("" + reg.getNombreIndicador());
/*      */       
/*  534 */       this.pagHTML.getElementObjetivoIndicador().setValue("" + reg.getObjetivoIndicador());
/*      */       
/*  536 */       this.pagHTML.getElementMetaTotalProgramada().setValue("" + reg.getMetaTotalProgramada());
/*  537 */       this.pagHTML.getElementMetaEstaVigencia().setValue("" + reg.getMetaEstaVigencia());
/*  538 */       this.pagHTML.getElementAcumuladoVigencias().setValue("" + reg.getAcumuladoVigencias());
/*  539 */       this.pagHTML.getElementAcumuladoEstaVigencia().setValue("" + reg.getAcumuladoEstaVigencia());
/*  540 */       this.pagHTML.getElementLogroTotal().setValue("" + reg.getLogroTotal());
/*  541 */       this.pagHTML.getElementLogroTotalAcumulado().setValue("" + reg.getLogroAcumuladoTotal());
/*      */       
/*  543 */       if (reg.getMes1().equals("S")) {
/*  544 */         this.pagHTML.getElementMes1().setChecked(true);
/*      */       } else {
/*  546 */         this.pagHTML.getElementMes1().setChecked(false);
/*  547 */         this.pagHTML.getElementValormes1().setReadOnly(true);
/*      */       } 
/*  549 */       if (reg.getMes2().equals("S")) {
/*  550 */         this.pagHTML.getElementMes2().setChecked(true);
/*      */       } else {
/*  552 */         this.pagHTML.getElementMes2().setChecked(false);
/*  553 */         this.pagHTML.getElementValormes2().setReadOnly(true);
/*      */       } 
/*  555 */       if (reg.getMes3().equals("S")) {
/*  556 */         this.pagHTML.getElementMes3().setChecked(true);
/*      */       } else {
/*  558 */         this.pagHTML.getElementMes3().setChecked(false);
/*  559 */         this.pagHTML.getElementValormes3().setReadOnly(true);
/*      */       } 
/*  561 */       if (reg.getMes4().equals("S")) {
/*  562 */         this.pagHTML.getElementMes4().setChecked(true);
/*      */       } else {
/*  564 */         this.pagHTML.getElementMes4().setChecked(false);
/*  565 */         this.pagHTML.getElementValormes4().setReadOnly(true);
/*      */       } 
/*  567 */       if (reg.getMes5().equals("S")) {
/*  568 */         this.pagHTML.getElementMes5().setChecked(true);
/*      */       } else {
/*  570 */         this.pagHTML.getElementMes5().setChecked(false);
/*  571 */         this.pagHTML.getElementValormes5().setReadOnly(true);
/*      */       } 
/*  573 */       if (reg.getMes6().equals("S")) {
/*  574 */         this.pagHTML.getElementMes6().setChecked(true);
/*      */       } else {
/*  576 */         this.pagHTML.getElementMes6().setChecked(false);
/*  577 */         this.pagHTML.getElementValormes6().setReadOnly(true);
/*      */       } 
/*  579 */       if (reg.getMes7().equals("S")) {
/*  580 */         this.pagHTML.getElementMes7().setChecked(true);
/*      */       } else {
/*  582 */         this.pagHTML.getElementMes7().setChecked(false);
/*  583 */         this.pagHTML.getElementValormes7().setReadOnly(true);
/*      */       } 
/*  585 */       if (reg.getMes8().equals("S")) {
/*  586 */         this.pagHTML.getElementMes8().setChecked(true);
/*      */       } else {
/*  588 */         this.pagHTML.getElementMes8().setChecked(false);
/*  589 */         this.pagHTML.getElementValormes8().setReadOnly(true);
/*      */       } 
/*  591 */       if (reg.getMes9().equals("S")) {
/*  592 */         this.pagHTML.getElementMes9().setChecked(true);
/*      */       } else {
/*  594 */         this.pagHTML.getElementMes9().setChecked(false);
/*  595 */         this.pagHTML.getElementValormes9().setReadOnly(true);
/*      */       } 
/*  597 */       if (reg.getMes10().equals("S")) {
/*  598 */         this.pagHTML.getElementMes10().setChecked(true);
/*      */       } else {
/*  600 */         this.pagHTML.getElementMes10().setChecked(false);
/*  601 */         this.pagHTML.getElementValormes10().setReadOnly(true);
/*      */       } 
/*  603 */       if (reg.getMes11().equals("S")) {
/*  604 */         this.pagHTML.getElementMes11().setChecked(true);
/*      */       } else {
/*  606 */         this.pagHTML.getElementMes11().setChecked(false);
/*  607 */         this.pagHTML.getElementValormes11().setReadOnly(true);
/*      */       } 
/*  609 */       if (reg.getMes12().equals("S")) {
/*  610 */         this.pagHTML.getElementMes12().setChecked(true);
/*      */       } else {
/*  612 */         this.pagHTML.getElementMes12().setChecked(false);
/*  613 */         this.pagHTML.getElementValormes12().setReadOnly(true);
/*      */       } 
/*  615 */       this.pagHTML.getElementValormes1().setValue("" + reg.getValormes1());
/*  616 */       this.pagHTML.getElementValormes2().setValue("" + reg.getValormes2());
/*  617 */       this.pagHTML.getElementValormes3().setValue("" + reg.getValormes3());
/*  618 */       this.pagHTML.getElementValormes4().setValue("" + reg.getValormes4());
/*  619 */       this.pagHTML.getElementValormes5().setValue("" + reg.getValormes5());
/*  620 */       this.pagHTML.getElementValormes6().setValue("" + reg.getValormes6());
/*  621 */       this.pagHTML.getElementValormes7().setValue("" + reg.getValormes7());
/*  622 */       this.pagHTML.getElementValormes8().setValue("" + reg.getValormes8());
/*  623 */       this.pagHTML.getElementValormes9().setValue("" + reg.getValormes9());
/*  624 */       this.pagHTML.getElementValormes10().setValue("" + reg.getValormes10());
/*  625 */       this.pagHTML.getElementValormes11().setValue("" + reg.getValormes11());
/*  626 */       this.pagHTML.getElementValormes12().setValue("" + reg.getValormes12());
/*  627 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/*  628 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/*  629 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/*  630 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/*      */       
/*  632 */       HTMLSelectElement combo = this.pagHTML.getElementProceso();
/*  633 */       llenarCombo(combo, "POA_PROCESOS", "CODIGO_PROCESO", "DESCRIPCION", "1=1", "" + reg.getProceso(), true);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  638 */       combo = this.pagHTML.getElementArea();
/*  639 */       llenarCombo(combo, "UNIDADES_DEPENDENCIA", "CODIGO", "DESCRIPCION", "1=1", "" + reg.getArea(), false);
/*      */       
/*  641 */       combo = this.pagHTML.getElementFormula();
/*  642 */       llenarCombo(combo, "FORMULA", "ID_FORMULA", "FORMULA", "1=1", "" + reg.getFormula(), false);
/*      */       
/*  644 */       combo = this.pagHTML.getElementObjetivoEstrategico();
/*  645 */       llenarCombo(combo, "POA_OBJETIVOS_ESTRATEGICOS", "CODIGO_OBJETIVO", "DESCRIPCION", "1=1", "" + reg.getObjetivoEstrategico(), false);
/*      */       
/*  647 */       combo = this.pagHTML.getElementTipoIndicador();
/*  648 */       comboMultivalores(combo, "TIPO_INDICADOR", "" + reg.getTipoIndicador(), false);
/*      */       
/*  650 */       combo = this.pagHTML.getElementPrioridadEnProducto();
/*  651 */       comboMultivalores(combo, "PROY_PRIORIDAD", "" + reg.getPrioridadEnProducto(), false);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  656 */       combo = this.pagHTML.getElementMetaPlanDeDesarrollo();
/*  657 */       llenarCombo(combo, "POA_METAS_PLAN_DESARROLLO", "CODIGO_META_PLAN", "DESCRIPCION", "1=1", "" + reg.getMetaPlanDeDesarrollo(), false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  663 */       SisUnidadesMedidaDAO dao2 = new SisUnidadesMedidaDAO();
/*  664 */       SisUnidadesMedidaDTO regis = dao2.cargarRegistro(reg.getUnidadMedida(), 0);
/*      */       
/*  666 */       combo = this.pagHTML.getElementTipoUnidad();
/*  667 */       llenarCombo(combo, "sis_grupos_unidades", "codigo_grupo", "nombre_grupo", "1=1", "" + regis.getCodigoGrupo(), true);
/*      */ 
/*      */       
/*  670 */       combo = this.pagHTML.getElementUnidadMedida();
/*  671 */       llenarCombo(combo, "sis_unidades_medida", "codigo_unidad", "nombre_unidad", "codigo_grupo=" + regis.getCodigoGrupo(), "" + reg.getUnidadMedida(), true);
/*      */ 
/*      */ 
/*      */       
/*  675 */       this.pagHTML.getElementIndicadorAcuerdo().setValue("" + reg.getIndicadorAcuerdo());
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  680 */       combo = this.pagHTML.getElementFrecuenciaMedicion();
/*  681 */       comboMultivalores(combo, "FRECUENTA_MEDICION_INDICADORES", "" + reg.getFrecuenciaMedicion(), false);
/*      */       
/*  683 */       combo = this.pagHTML.getElementEstado();
/*  684 */       comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  690 */       this.pagHTML.getElementCodigoIndicador().setReadOnly(true);
/*      */     } 
/*  692 */     this.pagHTML.getElement_operacion().setValue("M");
/*  693 */     activarVista("nuevo");
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
/*  705 */     this.pagHTML.getElement_operacion().setValue("C");
/*      */     try {
/*  707 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/*  708 */       sel.getParentNode().removeChild(sel);
/*      */     }
/*  710 */     catch (Exception e) {}
/*      */     
/*  712 */     activarVista("nuevo");
/*  713 */     HTMLSelectElement combo = this.pagHTML.getElementProceso();
/*  714 */     llenarCombo(combo, "POA_PROCESOS", "CODIGO_PROCESO", "DESCRIPCION", "1=1", "", false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  720 */     combo = this.pagHTML.getElementArea();
/*  721 */     llenarCombo(combo, "UNIDADES_DEPENDENCIA", "CODIGO", "DESCRIPCION", "1=1", "", false);
/*      */     
/*  723 */     combo = this.pagHTML.getElementObjetivoEstrategico();
/*  724 */     llenarCombo(combo, "POA_OBJETIVOS_ESTRATEGICOS", "CODIGO_OBJETIVO", "DESCRIPCION", "1=1", "", false);
/*      */     
/*  726 */     combo = this.pagHTML.getElementTipoIndicador();
/*  727 */     comboMultivalores(combo, "TIPO_INDICADOR", "", false);
/*      */     
/*  729 */     combo = this.pagHTML.getElementPrioridadEnProducto();
/*  730 */     comboMultivalores(combo, "PROY_PRIORIDAD", "", false);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  735 */     combo = this.pagHTML.getElementMetaPlanDeDesarrollo();
/*  736 */     llenarCombo(combo, "POA_METAS_PLAN_DESARROLLO", "CODIGO_META_PLAN", "DESCRIPCION", "1=1", "", false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  744 */     combo = this.pagHTML.getElementTipoUnidad();
/*  745 */     llenarCombo(combo, "sis_grupos_unidades", "codigo_grupo", "nombre_grupo", "1=1", "", true);
/*      */     
/*  747 */     combo = this.pagHTML.getElementFrecuenciaMedicion();
/*  748 */     comboMultivalores(combo, "FRECUENTA_MEDICION_INDICADORES", "", false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  754 */     combo = this.pagHTML.getElementEstado();
/*  755 */     comboMultivalores(combo, "ESTADO_REGISTRO", "", false);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  760 */     combo = this.pagHTML.getElementFormula();
/*  761 */     llenarCombo(combo, "SERVICIOS", "CODIGO", "DESCRIPCION", "TIPO_SERVICIO ='5'", "", false);
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
/*  773 */     activarVista("consulta");
/*  774 */     if (_operacion.equals("X")) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  779 */     IndicadoresDAO rs = new IndicadoresDAO();
/*  780 */     if (!rs.getEstadoConexion()) {
/*  781 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */     }
/*  783 */     Collection<IndicadoresDTO> arr = rs.cargarTodos();
/*  784 */     rs.close();
/*  785 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/*  786 */     int cuantas = 0;
/*  787 */     Iterator<IndicadoresDTO> iterator = arr.iterator();
/*  788 */     while (iterator.hasNext()) {
/*  789 */       IndicadoresDTO reg = (IndicadoresDTO)iterator.next();
/*  790 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  791 */       eltr.appendChild(newtd("" + reg.getCodigoIndicador()));
/*  792 */       String url = "Indicadores.po?_operacion=V&codigoIndicador=" + reg.getCodigoIndicador() + "";
/*  793 */       eltr.appendChild(newtdhref("" + reg.getNombreIndicador(), url));
/*  794 */       eltr.appendChild(newtd("" + reg.getNombreProceso()));
/*  795 */       eltr.appendChild(newtd("" + reg.getNombreArea()));
/*  796 */       eltr.appendChild(newtd("" + reg.getNombreObjetivoEstrategico()));
/*      */       
/*  798 */       hte.appendChild(eltr);
/*  799 */       cuantas++;
/*      */     } 
/*  801 */     arr.clear();
/*  802 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/*  815 */     String codigoIndicador = "";
/*      */     try {
/*  817 */       codigoIndicador = comms.request.getParameter("codigoIndicador");
/*      */     }
/*  819 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  822 */     IndicadoresDAO rs = new IndicadoresDAO();
/*  823 */     if (!rs.getEstadoConexion()) {
/*  824 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */     }
/*  826 */     IndicadoresDTO reg = rs.cargarRegistro(codigoIndicador);
/*  827 */     rs.close();
/*  828 */     if (reg != null) {
/*  829 */       this.pagHTML.setTextCodigoIndicadorEd("" + reg.getCodigoIndicador());
/*  830 */       this.pagHTML.setTextNombreIndicadorEd("" + reg.getNombreIndicador());
/*  831 */       this.pagHTML.setTextProcesoEd("" + reg.getNombreProceso());
/*      */       
/*  833 */       this.pagHTML.setTextAreaEd("" + reg.getNombreArea());
/*  834 */       this.pagHTML.setTextObjetivoEstrategicoEd("" + reg.getNombreObjetivoEstrategico());
/*  835 */       this.pagHTML.setTextIndicadorAcuerdoEd("" + reg.getIndicadorAcuerdo());
/*  836 */       this.pagHTML.setTextTipoIndicadorEd("" + reg.getNombreTipoIndicador());
/*      */       
/*  838 */       this.pagHTML.setTextMetaPlanDeDesarrolloEd("" + reg.getNombreMetaPlanDeDesarrollo());
/*      */       
/*  840 */       this.pagHTML.setTextObjetivoIndicadorEd("" + reg.getObjetivoIndicador());
/*  841 */       this.pagHTML.setTextPrioridadEnProductoEd("" + reg.getNombrePrioridadEnProducto());
/*      */       
/*  843 */       this.pagHTML.setTextFrecuenciaMedicionEd("" + reg.getNombreFrecuenciaMedicion());
/*  844 */       this.pagHTML.setTextFormulaEd("" + reg.getFormula());
/*      */ 
/*      */       
/*  847 */       this.pagHTML.setTextMes1Ed("" + reg.getMes1());
/*  848 */       this.pagHTML.setTextMes2Ed("" + reg.getMes2());
/*  849 */       this.pagHTML.setTextMes3Ed("" + reg.getMes3());
/*  850 */       this.pagHTML.setTextMes4Ed("" + reg.getMes4());
/*  851 */       this.pagHTML.setTextMes5Ed("" + reg.getMes5());
/*  852 */       this.pagHTML.setTextMes6Ed("" + reg.getMes6());
/*  853 */       this.pagHTML.setTextMes7Ed("" + reg.getMes7());
/*  854 */       this.pagHTML.setTextMes8Ed("" + reg.getMes8());
/*  855 */       this.pagHTML.setTextMes9Ed("" + reg.getMes9());
/*  856 */       this.pagHTML.setTextMes10Ed("" + reg.getMes10());
/*  857 */       this.pagHTML.setTextMes11Ed("" + reg.getMes11());
/*  858 */       this.pagHTML.setTextMes12Ed("" + reg.getMes12());
/*  859 */       this.pagHTML.setTextValormes1Ed("" + reg.getValormes1());
/*  860 */       this.pagHTML.setTextValormes2Ed("" + reg.getValormes2());
/*  861 */       this.pagHTML.setTextValormes3Ed("" + reg.getValormes3());
/*  862 */       this.pagHTML.setTextValormes4Ed("" + reg.getValormes4());
/*  863 */       this.pagHTML.setTextValormes5Ed("" + reg.getValormes5());
/*  864 */       this.pagHTML.setTextValormes6Ed("" + reg.getValormes6());
/*  865 */       this.pagHTML.setTextValormes7Ed("" + reg.getValormes7());
/*  866 */       this.pagHTML.setTextValormes8Ed("" + reg.getValormes8());
/*  867 */       this.pagHTML.setTextValormes9Ed("" + reg.getValormes9());
/*  868 */       this.pagHTML.setTextValormes10Ed("" + reg.getValormes10());
/*  869 */       this.pagHTML.setTextValormes11Ed("" + reg.getValormes11());
/*  870 */       this.pagHTML.setTextValormes12Ed("" + reg.getValormes12());
/*  871 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/*  872 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/*  873 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/*  874 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/*  875 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*      */       
/*  877 */       this.pagHTML.getElementCodigoIndicadorKey().setValue("" + reg.getCodigoIndicador());
/*  878 */       this.pagHTML.getElement_operacion().setValue("P");
/*      */     } 
/*  880 */     activarVista("editar");
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
/*  891 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*      */     
/*  893 */     Varios oVarios = new Varios();
/*  894 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Poa_IndicadoresAct");
/*  895 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Poa_IndicadoresDel");
/*  896 */     if (!oPermisoAct) {
/*  897 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/*  898 */       elem.getParentNode().removeChild(elem);
/*  899 */       elem = this.pagHTML.getElementBtnGrabar();
/*  900 */       elem.getParentNode().removeChild(elem);
/*  901 */       elem = this.pagHTML.getElementBtnModificar();
/*  902 */       elem.getParentNode().removeChild(elem);
/*      */     } 
/*  904 */     if (!oPermisoDel) {
/*  905 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/*  906 */       elem.getParentNode().removeChild(elem);
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
/*  917 */     if (!vista.equals("nuevo")) {
/*  918 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/*  919 */       sel.getParentNode().removeChild(sel);
/*      */     } 
/*  921 */     if (!vista.equals("editar")) {
/*  922 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/*  923 */       sel.getParentNode().removeChild(sel);
/*      */     } 
/*  925 */     if (!vista.equals("variables")) {
/*  926 */       HTMLElement sel = this.pagHTML.getElementDivVariables();
/*  927 */       sel.getParentNode().removeChild(sel);
/*      */     } 
/*  929 */     if (!vista.equals("consulta")) {
/*  930 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/*  931 */       sel.getParentNode().removeChild(sel);
/*  932 */       sel = this.pagHTML.getElementDivResultados();
/*  933 */       sel.getParentNode().removeChild(sel);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void agregarVariables(HttpPresentationComms comms, String codigoIndicador) throws HttpPresentationException, KeywordValueException {
/*  941 */     eliminarVariable(comms, codigoIndicador);
/*  942 */     String variables = comms.request.getParameter("totalVariables");
/*  943 */     String[] variable = variables.split(";");
/*  944 */     for (int i = 0; i < variable.length; i++) {
/*  945 */       String[] campos = variable[i].split(",");
/*  946 */       IndicadorVariablesDAO rs = new IndicadorVariablesDAO();
/*  947 */       String elUsuario = "" + comms.session.getUser().getName();
/*      */       try {
/*  949 */         if (campos.length == 3) {
/*  950 */           rs.crearRegistro(campos[0], campos[1], campos[2], codigoIndicador, "A", elUsuario);
/*      */ 
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/*  957 */           rs.crearRegistro(campos[0], campos[1], campos[2], codigoIndicador, "A", elUsuario);
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*  964 */       catch (Exception e) {}
/*      */       
/*  966 */       rs.close();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void eliminarVariable(HttpPresentationComms comms, String codigoIndicador) throws HttpPresentationException, KeywordValueException {
/*  974 */     IndicadorVariablesDAO rs = new IndicadorVariablesDAO();
/*  975 */     rs.eliminarRegistro(codigoIndicador);
/*  976 */     rs.close();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private String cargarVariables(HttpPresentationComms comms, String codigoIndicador) throws HttpPresentationException, KeywordValueException {
/*  982 */     IndicadorVariablesDAO rs = new IndicadorVariablesDAO();
/*  983 */     Collection<IndicadorVariablesDTO> arr = rs.cargarTodos(codigoIndicador);
/*  984 */     rs.close();
/*  985 */     Iterator<IndicadorVariablesDTO> iterator = arr.iterator();
/*      */     
/*  987 */     String script = "var variables=new Array(" + arr.size() + ");";
/*  988 */     script = script + " var iCont=0;";
/*  989 */     while (iterator.hasNext()) {
/*  990 */       IndicadorVariablesDTO reg = (IndicadorVariablesDTO)iterator.next();
/*  991 */       IndicadorVariablesDAO is = new IndicadorVariablesDAO();
/*  992 */       IndicadorVariablesDTO var = is.cargarRegistro(reg.getIdVariable());
/*  993 */       is.close();
/*  994 */       script = script + " variables[iCont++]=new variable(" + "'" + var.getNombre() + "'" + "'" + var.getDescripcion() + "'" + "'" + var.getFuente() + "'" + ");";
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1002 */     return script;
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
/*      */   private Attr newAttr(String tipo, String valor) {
/* 1014 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 1015 */     atrib.setValue(valor);
/* 1016 */     return atrib;
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
/* 1029 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 1030 */     Element enlace = this.pagHTML.createElement("a");
/* 1031 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 1032 */     enlace.appendChild(hijo);
/* 1033 */     Attr donde = this.pagHTML.createAttribute("href");
/* 1034 */     donde.setValue(vinculo);
/* 1035 */     enlace.setAttributeNode(donde);
/* 1036 */     td.appendChild(enlace);
/* 1037 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 1038 */     return td;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private HTMLElement newtd(String contenido) {
/* 1048 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 1049 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 1050 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 1051 */     return td;
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
/* 1066 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 1067 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 1068 */     rs.close();
/* 1069 */     if (dejarBlanco) {
/* 1070 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1071 */       op.setValue("");
/* 1072 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 1073 */       combo.appendChild(op);
/*      */     } 
/* 1075 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 1076 */     while (iterator.hasNext()) {
/* 1077 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 1078 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1079 */       op.setValue("" + reg.getCodigo());
/* 1080 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 1081 */       if (defecto.equals(reg.getCodigo())) {
/* 1082 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 1083 */         escogida.setValue("on");
/* 1084 */         op.setAttributeNode(escogida);
/*      */       } 
/* 1086 */       combo.appendChild(op);
/*      */     } 
/* 1088 */     arr.clear();
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
/* 1109 */     if (dejarBlanco) {
/* 1110 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1111 */       op.setValue("");
/* 1112 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 1113 */       combo.appendChild(op);
/*      */     } 
/* 1115 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 1116 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/* 1117 */     rsTGen.close();
/* 1118 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 1119 */     while (iterator.hasNext()) {
/* 1120 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 1121 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1122 */       op.setValue("" + regGeneral.getCodigoS());
/* 1123 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 1124 */       if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
/* 1125 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 1126 */         escogida.setValue("on");
/* 1127 */         op.setAttributeNode(escogida);
/*      */       } 
/* 1129 */       combo.appendChild(op);
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
/*      */   private void caracteristicas(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 1143 */     int codigo = 0;
/*      */     
/*      */     try {
/* 1146 */       codigo = Integer.parseInt(comms.request.getParameter("codCar"));
/* 1147 */     } catch (Exception e) {}
/*      */ 
/*      */ 
/*      */     
/* 1151 */     Utilidades.grabarLog("codigo caracteristiaca " + codigo);
/* 1152 */     this.pagHTML.getElement_operacion().setValue("guardarVariables");
/* 1153 */     this.pagHTML.getElementCodIndicador().setValue(comms.request.getParameter("codInd"));
/* 1154 */     this.pagHTML.getElementCodCiclo().setValue(comms.request.getParameter("codCic"));
/* 1155 */     this.pagHTML.getElementCodigoCar().setValue(comms.request.getParameter("codCar"));
/* 1156 */     this.pagHTML.getElementElUsuario().setValue(comms.request.getParameter("usuario"));
/*      */ 
/*      */ 
/*      */     
/* 1160 */     CaracteristicasValorDAO rsTGen = new CaracteristicasValorDAO();
/*      */     
/* 1162 */     HTMLTableElement tabla = this.pagHTML.getElementTablaVariables();
/*      */     
/* 1164 */     CaracteristicasDAO rsCaracteristicas = new CaracteristicasDAO();
/* 1165 */     Collection arr = rsCaracteristicas.cargarTodosParaServicio(codigo, "C", "A");
/* 1166 */     rsCaracteristicas.close();
/*      */     
/* 1168 */     int solPadre = 0;
/*      */     try {
/* 1170 */       solPadre = Integer.parseInt(comms.request.getParameter("solPadre"));
/*      */       
/* 1172 */       comms.session.getSessionData().set("volverA", "" + solPadre);
/*      */     
/*      */     }
/* 1175 */     catch (Exception e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1180 */     boolean extender = false;
/*      */ 
/*      */     
/* 1183 */     boolean fondo = true;
/* 1184 */     DetalleSolicitudDAO dsf = new DetalleSolicitudDAO();
/* 1185 */     int capturas = 0;
/* 1186 */     String cadenaFechas = "";
/*      */     
/* 1188 */     Iterator iterator = arr.iterator();
/* 1189 */     Collection arrAnidadas = new ArrayList();
/* 1190 */     Collection arrDependen = new ArrayList();
/* 1191 */     HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 1192 */     eltr.appendChild(newtdTitle("Mes", "cf1"));
/* 1193 */     while (iterator.hasNext()) {
/* 1194 */       CaracteristicasDTO regCar = (CaracteristicasDTO)iterator.next();
/*      */       
/* 1196 */       HTMLElement tdCaracteristica = (HTMLElement)this.pagHTML.createElement("th");
/*      */       
/* 1198 */       if (regCar.getCalificar().equals("N")) {
/* 1199 */         fondo = !fondo;
/* 1200 */         eltr.setAttributeNode(newAttr("class", "car"));
/* 1201 */         eltr.setAttributeNode(newAttr("codigo", "" + regCar.getCodigo()));
/*      */         
/* 1203 */         tdCaracteristica = newtdTitle(regCar.getDescripcion(), "cf1");
/*      */         
/* 1205 */         eltr.appendChild(tdCaracteristica);
/*      */       } 
/*      */     } 
/*      */     
/* 1209 */     tabla.appendChild(eltr);
/*      */ 
/*      */ 
/*      */     
/* 1213 */     for (int i = 0; i < 13; i++) {
/* 1214 */       iterator = arr.iterator();
/* 1215 */       eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 1216 */       if (i < 12) {
/* 1217 */         eltr.appendChild(newtd(getMes(i + 1)));
/*      */       } else {
/* 1219 */         eltr.appendChild(newtd("Estado"));
/*      */       } 
/*      */       
/* 1222 */       while (iterator.hasNext()) {
/* 1223 */         CaracteristicasDTO regCar = (CaracteristicasDTO)iterator.next();
/*      */         
/* 1225 */         HTMLElement tdDescripcion = (HTMLElement)this.pagHTML.createElement("td");
/*      */         
/* 1227 */         fondo = !fondo;
/* 1228 */         eltr.setAttributeNode(newAttr("class", "car"));
/* 1229 */         eltr.setAttributeNode(newAttr("codigo", "" + regCar.getCodigo()));
/*      */ 
/*      */         
/* 1232 */         tdDescripcion.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*      */         
/* 1234 */         dsf.cargarParaSolicitud(codigo, regCar.getCodigo());
/* 1235 */         DetalleSolicitudDTO ds = dsf.next();
/* 1236 */         boolean hay = false;
/*      */         
/* 1238 */         while (ds != null) {
/*      */           
/* 1240 */           if ((regCar.getTipo().equals("2") || regCar.getTipo().equals("8")) && regCar.getCaracteristicaAnida() > 0) {
/* 1241 */             TemporalDTO tt = new TemporalDTO();
/* 1242 */             tt.setCaracteristica(regCar.getCaracteristicaAnida());
/* 1243 */             tt.setValor(ds.getValor());
/* 1244 */             arrAnidadas.add(tt);
/*      */           } 
/*      */           
/* 1247 */           if (regCar.getTipo().equals("2")) {
/*      */             
/* 1249 */             if (regCar.getCuantasDependen() > 0) {
/* 1250 */               TemporalDTO tt = new TemporalDTO();
/* 1251 */               tt.setCaracteristica(regCar.getCodigo());
/* 1252 */               tt.setValor(ds.getValor());
/* 1253 */               arrDependen.add(tt);
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/* 1258 */             String ampliacion = "";
/* 1259 */             if (regCar.getNombreProcedimiento().length() > 0);
/*      */ 
/*      */ 
/*      */             
/* 1263 */             tdDescripcion.appendChild(this.pagHTML.createTextNode(ds.getObservacion() + " " + ampliacion + ". "));
/*      */           } 
/* 1265 */           ds = dsf.next();
/* 1266 */           hay = true;
/*      */         } 
/*      */         
/* 1269 */         if (i < 12) {
/*      */ 
/*      */           
/* 1272 */           if (!hay) {
/* 1273 */             HTMLInputElement inp; switch (Integer.parseInt(regCar.getTipo())) {
/*      */               case 1:
/*      */               case 3:
/*      */               case 4:
/* 1277 */                 inp = (HTMLInputElement)this.pagHTML.createElement("input");
/* 1278 */                 inp.setMaxLength(regCar.getLongitud());
/* 1279 */                 if (regCar.getLongitud() >= 200) {
/* 1280 */                   inp.setSize("70");
/*      */                 }
/* 1282 */                 else if (regCar.getLongitud() <= 20) {
/* 1283 */                   inp.setSize("15");
/*      */                 }
/* 1285 */                 else if (regCar.getLongitud() > 20 && regCar.getLongitud() < 100) {
/* 1286 */                   inp.setSize("30");
/*      */                 } else {
/*      */                   
/* 1289 */                   inp.setSize("40");
/*      */                 } 
/*      */ 
/*      */                 
/* 1293 */                 inp.setName("" + regCar.getCodigo() + "" + i);
/* 1294 */                 inp.setId("" + regCar.getCodigo() + "" + i);
/*      */                 
/* 1296 */                 if (regCar.getNombreProcedimiento().length() > 0) {
/* 1297 */                   inp.setAttributeNode(newAttr("onblur", "ejecutarProcedimiento(this.name,this.value,'" + regCar.getNombreProcedimiento() + "'," + regCar.getCaracteristicaAnida() + ");"));
/*      */                 }
/*      */                 
/* 1300 */                 inp.setAttributeNode(newAttr("obliga", "" + ((regCar.getObliga() == true) ? "S" : "N")));
/* 1301 */                 inp.setAttributeNode(newAttr("tipo", "" + regCar.getTipo()));
/* 1302 */                 inp.setAttributeNode(newAttr("onkeyDown", "return f_salto2(event)"));
/* 1303 */                 inp.setAttributeNode(newAttr("onkeyUp", "return f_salto(event)"));
/* 1304 */                 inp.setAttributeNode(newAttr("class", "inp"));
/*      */                 
/* 1306 */                 inp.setAttributeNode(newAttr("tipoVal", "" + regCar.getTipoValidacion()));
/* 1307 */                 inp.setAttributeNode(newAttr("carVal", "" + regCar.getCaracteristicaValida()));
/*      */ 
/*      */ 
/*      */                 
/* 1311 */                 switch (Integer.parseInt(regCar.getTipo())) {
/*      */                   case 1:
/* 1313 */                     inp.setAttributeNode(newAttr("onkeypress", "validarTecla(event, 'AL');"));
/*      */                     break;
/*      */                   case 3:
/* 1316 */                     inp.setAttributeNode(newAttr("onkeypress", "validarTecla(event, 'EN');"));
/*      */                     break;
/*      */                   case 4:
/* 1319 */                     inp.setAttributeNode(newAttr("onkeypress", "validarTecla(event, 'FE');"));
/*      */                     break;
/*      */                 } 
/*      */                 
/* 1323 */                 tdDescripcion.appendChild(inp);
/*      */                 
/* 1325 */                 if (regCar.getTipo().equals("4")) {
/*      */                   
/* 1327 */                   HTMLImageElement inp2 = (HTMLImageElement)this.pagHTML.createElement("IMG");
/* 1328 */                   inp2.setSrc("media/calendario.jpg");
/* 1329 */                   inp2.setId("b" + regCar.getCodigo());
/* 1330 */                   inp2.setAlt("Calendario");
/* 1331 */                   inp2.setHeight("20");
/* 1332 */                   inp2.setWidth("20");
/* 1333 */                   tdDescripcion.appendChild(inp2);
/*      */                 } 
/*      */                 
/* 1336 */                 if (regCar.getNombreProcedimiento().length() > 0) {
/* 1337 */                   HTMLElement inp3 = (HTMLElement)this.pagHTML.createElement("span");
/* 1338 */                   inp3.setId("msg" + regCar.getCodigo());
/* 1339 */                   inp3.setAttributeNode(newAttr("class", "error"));
/* 1340 */                   tdDescripcion.appendChild(inp3);
/*      */                 } 
/*      */                 break;
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1349 */             if (regCar.getObliga()) capturas++;
/*      */           
/* 1351 */           } else if (!regCar.getTipo().equals("2") && regCar.getPermiteExtender().equals("S")) {
/* 1352 */             HTMLOptionElement extension = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1353 */             extension.setValue("" + regCar.getCodigo());
/* 1354 */             extension.appendChild(this.pagHTML.createTextNode(regCar.getDescripcion()));
/*      */             
/* 1356 */             extender = true;
/*      */           } 
/*      */ 
/*      */           
/* 1360 */           if (regCar.getCaracteristicaDepende() > 0) {
/* 1361 */             int valorMostrar = 0;
/* 1362 */             Iterator iterDepende = arrDependen.iterator();
/* 1363 */             while (iterDepende.hasNext()) {
/* 1364 */               TemporalDTO regTemp = (TemporalDTO)iterDepende.next();
/* 1365 */               if (regTemp.getCaracteristica() == regCar.getCaracteristicaDepende()) {
/* 1366 */                 valorMostrar = regTemp.getValor();
/*      */               }
/*      */             } 
/*      */             
/* 1370 */             if (valorMostrar != regCar.getValorDepende()) {
/* 1371 */               eltr.setAttributeNode(newAttr("style", "display:none"));
/*      */             }
/* 1373 */             eltr.setAttributeNode(newAttr("depende", "" + regCar.getCaracteristicaDepende()));
/* 1374 */             eltr.setAttributeNode(newAttr("valorDepende", "" + regCar.getValorDepende()));
/*      */           }
/*      */         
/*      */         }
/*      */         else {
/*      */           
/* 1380 */           HTMLSelectElement inp = (HTMLSelectElement)this.pagHTML.createElement("Select");
/*      */           
/* 1382 */           if (codigo == 11 || codigo == 9) {
/* 1383 */             comboMultivalores(inp, "ESTADO_REGISTRO", "", false);
/*      */           } else {
/* 1385 */             HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1386 */             op.setValue("A");
/* 1387 */             op.appendChild(this.pagHTML.createTextNode("Activo"));
/* 1388 */             inp.appendChild(op);
/*      */           } 
/* 1390 */           inp.setName("" + regCar.getCodigo() + "" + i);
/* 1391 */           inp.setId("" + regCar.getCodigo() + "" + i);
/*      */           
/* 1393 */           tdDescripcion.appendChild(inp);
/*      */         } 
/*      */         
/* 1396 */         if (regCar.getCalificar().equals("N")) {
/* 1397 */           eltr.appendChild(tdDescripcion);
/*      */         }
/*      */       } 
/* 1400 */       tabla.appendChild(eltr);
/*      */     } 
/*      */     
/* 1403 */     dsf.close();
/* 1404 */     rsTGen.close();
/* 1405 */     arr.clear();
/* 1406 */     especiales(comms);
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
/*      */   private void especiales(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 1420 */     int codigo = 0;
/*      */     
/*      */     try {
/* 1423 */       codigo = Integer.parseInt(comms.request.getParameter("codCar"));
/* 1424 */     } catch (Exception e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1430 */     CaracteristicasValorDAO rsTGen = new CaracteristicasValorDAO();
/*      */     
/* 1432 */     HTMLTableElement tabla = this.pagHTML.getElementTablaEspeciales();
/*      */     
/* 1434 */     CaracteristicasDAO rsCaracteristicas = new CaracteristicasDAO();
/* 1435 */     Collection arr = rsCaracteristicas.cargarTodosParaServicio(codigo, "C", "A");
/* 1436 */     rsCaracteristicas.close();
/*      */     
/* 1438 */     int solPadre = 0;
/*      */     try {
/* 1440 */       solPadre = Integer.parseInt(comms.request.getParameter("solPadre"));
/*      */       
/* 1442 */       comms.session.getSessionData().set("volverA", "" + solPadre);
/*      */     
/*      */     }
/* 1445 */     catch (Exception e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1450 */     boolean extender = false;
/*      */ 
/*      */     
/* 1453 */     boolean fondo = true;
/* 1454 */     DetalleSolicitudDAO dsf = new DetalleSolicitudDAO();
/* 1455 */     int capturas = 0;
/* 1456 */     String cadenaFechas = "";
/*      */     
/* 1458 */     Iterator iterator = arr.iterator();
/* 1459 */     Collection arrAnidadas = new ArrayList();
/* 1460 */     Collection arrDependen = new ArrayList();
/* 1461 */     HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 1462 */     while (iterator.hasNext()) {
/* 1463 */       CaracteristicasDTO regCar = (CaracteristicasDTO)iterator.next();
/*      */       
/* 1465 */       HTMLElement tdCaracteristica = (HTMLElement)this.pagHTML.createElement("th");
/*      */       
/* 1467 */       if (regCar.getCalificar().equals("S")) {
/* 1468 */         fondo = !fondo;
/* 1469 */         eltr.setAttributeNode(newAttr("class", "car"));
/* 1470 */         eltr.setAttributeNode(newAttr("codigo", "" + regCar.getCodigo()));
/*      */         
/* 1472 */         tdCaracteristica = newtdTitle(regCar.getDescripcion(), "cf1");
/*      */         
/* 1474 */         eltr.appendChild(tdCaracteristica);
/*      */       } 
/*      */     } 
/* 1477 */     tabla.appendChild(eltr);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1482 */     iterator = arr.iterator();
/* 1483 */     eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*      */ 
/*      */     
/* 1486 */     while (iterator.hasNext()) {
/* 1487 */       CaracteristicasDTO regCar = (CaracteristicasDTO)iterator.next();
/*      */       
/* 1489 */       HTMLElement tdDescripcion = (HTMLElement)this.pagHTML.createElement("td");
/*      */       
/* 1491 */       fondo = !fondo;
/* 1492 */       eltr.setAttributeNode(newAttr("class", "car"));
/* 1493 */       eltr.setAttributeNode(newAttr("codigo", "" + regCar.getCodigo()));
/*      */ 
/*      */       
/* 1496 */       tdDescripcion.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*      */       
/* 1498 */       dsf.cargarParaSolicitud(codigo, regCar.getCodigo());
/* 1499 */       DetalleSolicitudDTO ds = dsf.next();
/* 1500 */       boolean hay = false;
/*      */       
/* 1502 */       while (ds != null) {
/*      */         
/* 1504 */         if ((regCar.getTipo().equals("2") || regCar.getTipo().equals("8")) && regCar.getCaracteristicaAnida() > 0) {
/* 1505 */           TemporalDTO tt = new TemporalDTO();
/* 1506 */           tt.setCaracteristica(regCar.getCaracteristicaAnida());
/* 1507 */           tt.setValor(ds.getValor());
/* 1508 */           arrAnidadas.add(tt);
/*      */         } 
/*      */         
/* 1511 */         if (regCar.getTipo().equals("2")) {
/*      */           
/* 1513 */           if (regCar.getCuantasDependen() > 0) {
/* 1514 */             TemporalDTO tt = new TemporalDTO();
/* 1515 */             tt.setCaracteristica(regCar.getCodigo());
/* 1516 */             tt.setValor(ds.getValor());
/* 1517 */             arrDependen.add(tt);
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 1522 */           String ampliacion = "";
/* 1523 */           if (regCar.getNombreProcedimiento().length() > 0);
/*      */ 
/*      */ 
/*      */           
/* 1527 */           tdDescripcion.appendChild(this.pagHTML.createTextNode(ds.getObservacion() + " " + ampliacion + ". "));
/*      */         } 
/* 1529 */         ds = dsf.next();
/* 1530 */         hay = true;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1536 */       if (!hay) {
/* 1537 */         HTMLInputElement inp; switch (Integer.parseInt(regCar.getTipo())) {
/*      */           case 1:
/*      */           case 3:
/*      */           case 4:
/* 1541 */             inp = (HTMLInputElement)this.pagHTML.createElement("input");
/* 1542 */             inp.setMaxLength(regCar.getLongitud());
/* 1543 */             if (regCar.getLongitud() >= 200) {
/* 1544 */               inp.setSize("70");
/*      */             }
/* 1546 */             else if (regCar.getLongitud() <= 20) {
/* 1547 */               inp.setSize("15");
/*      */             }
/* 1549 */             else if (regCar.getLongitud() > 20 && regCar.getLongitud() < 100) {
/* 1550 */               inp.setSize("30");
/*      */             } else {
/*      */               
/* 1553 */               inp.setSize("40");
/*      */             } 
/*      */ 
/*      */             
/* 1557 */             inp.setName("" + regCar.getCodigo() + "extra");
/* 1558 */             inp.setId("" + regCar.getCodigo() + "extra");
/*      */             
/* 1560 */             if (regCar.getNombreProcedimiento().length() > 0) {
/* 1561 */               inp.setAttributeNode(newAttr("onblur", "ejecutarProcedimiento(this.name,this.value,'" + regCar.getNombreProcedimiento() + "'," + regCar.getCaracteristicaAnida() + ");"));
/*      */             }
/*      */             
/* 1564 */             inp.setAttributeNode(newAttr("obliga", "" + ((regCar.getObliga() == true) ? "S" : "N")));
/* 1565 */             inp.setAttributeNode(newAttr("tipo", "" + regCar.getTipo()));
/* 1566 */             inp.setAttributeNode(newAttr("onkeyDown", "return f_salto2(event)"));
/* 1567 */             inp.setAttributeNode(newAttr("onkeyUp", "return f_salto(event)"));
/* 1568 */             inp.setAttributeNode(newAttr("class", "inp"));
/*      */             
/* 1570 */             inp.setAttributeNode(newAttr("tipoVal", "" + regCar.getTipoValidacion()));
/* 1571 */             inp.setAttributeNode(newAttr("carVal", "" + regCar.getCaracteristicaValida()));
/*      */ 
/*      */ 
/*      */             
/* 1575 */             switch (Integer.parseInt(regCar.getTipo())) {
/*      */               case 1:
/* 1577 */                 inp.setAttributeNode(newAttr("onkeypress", "validarTecla(event, 'AL');"));
/*      */                 break;
/*      */               case 3:
/* 1580 */                 inp.setAttributeNode(newAttr("onkeypress", "validarTecla(event, 'EN');"));
/*      */                 break;
/*      */               case 4:
/* 1583 */                 inp.setAttributeNode(newAttr("onkeypress", "validarTecla(event, 'FE');"));
/*      */                 break;
/*      */             } 
/*      */             
/* 1587 */             tdDescripcion.appendChild(inp);
/*      */             
/* 1589 */             if (regCar.getTipo().equals("4")) {
/*      */               
/* 1591 */               HTMLImageElement inp2 = (HTMLImageElement)this.pagHTML.createElement("IMG");
/* 1592 */               inp2.setSrc("media/calendario.jpg");
/* 1593 */               inp2.setId("b" + regCar.getCodigo());
/* 1594 */               inp2.setAlt("Calendario");
/* 1595 */               inp2.setHeight("20");
/* 1596 */               inp2.setWidth("20");
/* 1597 */               tdDescripcion.appendChild(inp2);
/*      */             } 
/*      */             
/* 1600 */             if (regCar.getNombreProcedimiento().length() > 0) {
/* 1601 */               HTMLElement inp3 = (HTMLElement)this.pagHTML.createElement("span");
/* 1602 */               inp3.setId("msg" + regCar.getCodigo());
/* 1603 */               inp3.setAttributeNode(newAttr("class", "error"));
/* 1604 */               tdDescripcion.appendChild(inp3);
/*      */             } 
/*      */             break;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1613 */         if (regCar.getObliga()) capturas++;
/*      */       
/* 1615 */       } else if (!regCar.getTipo().equals("2") && regCar.getPermiteExtender().equals("S")) {
/* 1616 */         HTMLOptionElement extension = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1617 */         extension.setValue("" + regCar.getCodigo());
/* 1618 */         extension.appendChild(this.pagHTML.createTextNode(regCar.getDescripcion()));
/*      */         
/* 1620 */         extender = true;
/*      */       } 
/*      */ 
/*      */       
/* 1624 */       if (regCar.getCaracteristicaDepende() > 0) {
/* 1625 */         int valorMostrar = 0;
/* 1626 */         Iterator iterDepende = arrDependen.iterator();
/* 1627 */         while (iterDepende.hasNext()) {
/* 1628 */           TemporalDTO regTemp = (TemporalDTO)iterDepende.next();
/* 1629 */           if (regTemp.getCaracteristica() == regCar.getCaracteristicaDepende()) {
/* 1630 */             valorMostrar = regTemp.getValor();
/*      */           }
/*      */         } 
/*      */         
/* 1634 */         if (valorMostrar != regCar.getValorDepende()) {
/* 1635 */           eltr.setAttributeNode(newAttr("style", "display:none"));
/*      */         }
/* 1637 */         eltr.setAttributeNode(newAttr("depende", "" + regCar.getCaracteristicaDepende()));
/* 1638 */         eltr.setAttributeNode(newAttr("valorDepende", "" + regCar.getValorDepende()));
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1644 */       HTMLSelectElement inp = (HTMLSelectElement)this.pagHTML.createElement("Select");
/* 1645 */       comboMultivalores(inp, "ESTADO_REGISTRO", "", false);
/* 1646 */       inp.setName("" + regCar.getCodigo() + "estado");
/* 1647 */       inp.setId("" + regCar.getCodigo() + "estado");
/* 1648 */       tdDescripcion.appendChild(inp);
/*      */       
/* 1650 */       if (regCar.getCalificar().equals("S")) {
/* 1651 */         eltr.appendChild(tdDescripcion);
/*      */       }
/*      */     } 
/* 1654 */     tabla.appendChild(eltr);
/*      */ 
/*      */     
/* 1657 */     dsf.close();
/* 1658 */     rsTGen.close();
/* 1659 */     arr.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private HTMLElement newtdTitle(String contenido, String clase) {
/* 1670 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 1671 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 1672 */     td.setAttributeNode(newAttr("class", clase));
/* 1673 */     return td;
/*      */   }
/*      */ 
/*      */   
/*      */   private void guardarVariablesEspeciales(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 1678 */     String codigoIndicador = comms.request.getParameter("codIndicador");
/* 1679 */     String elUsuario = comms.request.getParameter("elUsuario");
/* 1680 */     int ciclo = 0;
/*      */     try {
/* 1682 */       ciclo = Integer.parseInt(comms.request.getParameter("codCiclo"));
/* 1683 */     } catch (Exception e) {}
/*      */ 
/*      */ 
/*      */     
/* 1687 */     int codigo = 0;
/*      */     try {
/* 1689 */       codigo = Integer.parseInt(comms.request.getParameter("codigoCar"));
/* 1690 */     } catch (Exception e) {}
/*      */ 
/*      */ 
/*      */     
/* 1694 */     CaracteristicasDAO rsCaracteristicas = new CaracteristicasDAO();
/* 1695 */     Collection arr = rsCaracteristicas.cargarTodosParaServicio(codigo, "C", "A");
/* 1696 */     rsCaracteristicas.close();
/*      */ 
/*      */     
/* 1699 */     String estado = "";
/* 1700 */     IndicadorCaracteristicaDAO rsIndCar = new IndicadorCaracteristicaDAO();
/* 1701 */     Iterator iterator = arr.iterator();
/* 1702 */     while (iterator.hasNext()) {
/* 1703 */       CaracteristicasDTO regCar = (CaracteristicasDTO)iterator.next();
/* 1704 */       int valor = 0;
/*      */       try {
/* 1706 */         valor = Integer.parseInt(comms.request.getParameter("" + regCar.getCodigo() + "extra"));
/* 1707 */       } catch (Exception e) {}
/*      */       
/* 1709 */       estado = comms.request.getParameter("" + regCar.getCodigo() + "estado");
/* 1710 */       if (regCar.getCalificar().equals("S")) {
/* 1711 */         rsIndCar.crearRegistro(0, ciclo, codigoIndicador, "NO", regCar.getCodigo(), valor, 0, estado, elUsuario);
/*      */       }
/*      */     } 
/* 1714 */     rsIndCar.close();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void guardarVariables(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 1721 */     String codigoIndicador = comms.request.getParameter("codIndicador");
/* 1722 */     String elUsuario = comms.request.getParameter("elUsuario");
/* 1723 */     int ciclo = 0;
/*      */     try {
/* 1725 */       ciclo = Integer.parseInt(comms.request.getParameter("codCiclo"));
/* 1726 */     } catch (Exception e) {}
/*      */ 
/*      */ 
/*      */     
/* 1730 */     int codigo = 0;
/*      */     try {
/* 1732 */       codigo = Integer.parseInt(comms.request.getParameter("codigoCar"));
/* 1733 */     } catch (Exception e) {}
/*      */ 
/*      */ 
/*      */     
/* 1737 */     CaracteristicasDAO rsCaracteristicas = new CaracteristicasDAO();
/* 1738 */     Collection arr = rsCaracteristicas.cargarTodosParaServicio(codigo, "C", "A");
/* 1739 */     rsCaracteristicas.close();
/*      */ 
/*      */     
/* 1742 */     String estado = "";
/* 1743 */     IndicadorCaracteristicaDAO rsIndCar = new IndicadorCaracteristicaDAO();
/* 1744 */     for (int i = 0; i < 12; i++) {
/* 1745 */       Iterator iterator = arr.iterator();
/* 1746 */       while (iterator.hasNext()) {
/* 1747 */         CaracteristicasDTO regCar = (CaracteristicasDTO)iterator.next();
/* 1748 */         int valor = 0;
/*      */         try {
/* 1750 */           valor = Integer.parseInt(comms.request.getParameter("" + regCar.getCodigo() + "" + i));
/* 1751 */         } catch (Exception e) {}
/*      */         
/* 1753 */         estado = comms.request.getParameter("" + regCar.getCodigo() + "" + '\f');
/* 1754 */         rsIndCar.crearRegistro(0, ciclo, codigoIndicador, getMes(i + 1), regCar.getCodigo(), valor, 0, estado, elUsuario);
/*      */       } 
/*      */     } 
/* 1757 */     guardarVariablesEspeciales(comms);
/*      */     
/* 1759 */     rsIndCar.close();
/*      */     
/* 1761 */     String sPagina = "Indicadores.po?_operacion=P&codigoIndicador=" + codigoIndicador + "";
/* 1762 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*      */   }
/*      */   
/*      */   private String getMes(int mes) {
/* 1766 */     switch (mes) {
/*      */       case 1:
/* 1768 */         return "Enero";
/*      */       case 2:
/* 1770 */         return "Febrero";
/*      */       
/*      */       case 3:
/* 1773 */         return "Marzo";
/*      */       case 4:
/* 1775 */         return "Abril";
/*      */       case 5:
/* 1777 */         return "Mayo";
/*      */       case 6:
/* 1779 */         return "Junio";
/*      */       case 7:
/* 1781 */         return "Julio";
/*      */       case 8:
/* 1783 */         return "Agosto";
/*      */       case 9:
/* 1785 */         return "Septiembre";
/*      */       case 10:
/* 1787 */         return "Octubre";
/*      */       case 11:
/* 1789 */         return "Noviembre";
/*      */       case 12:
/* 1791 */         return "Diciembre";
/*      */     } 
/*      */ 
/*      */     
/* 1795 */     return "";
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\Indicadores.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */