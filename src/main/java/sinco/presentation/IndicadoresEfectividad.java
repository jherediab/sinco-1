/*      */ package sinco.presentation;
/*      */ 
/*      */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*      */ import com.lutris.util.KeywordValueException;
/*      */ import java.text.DecimalFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import org.jfree.chart.ChartUtilities;
/*      */ import org.jfree.chart.JFreeChart;
/*      */ import org.jfree.chart.plot.SpiderWebPlot;
/*      */ import org.jfree.chart.title.LegendTitle;
/*      */ import org.jfree.chart.title.TextTitle;
/*      */ import org.jfree.data.category.DefaultCategoryDataset;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.w3c.dom.Attr;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.html.HTMLElement;
/*      */ import org.w3c.dom.html.HTMLImageElement;
/*      */ import org.w3c.dom.html.HTMLOptionElement;
/*      */ import org.w3c.dom.html.HTMLSelectElement;
/*      */ import org.w3c.dom.html.HTMLTableElement;
/*      */ import sinco.business.CaracteristicasDTO;
/*      */ import sinco.business.IndicadorActividadesDTO;
/*      */ import sinco.business.IndicadorCaracteristicaDTO;
/*      */ import sinco.business.IndicadorResultadoDTO;
/*      */ import sinco.business.IndicadoresDTO;
/*      */ import sinco.business.PoaMaestroActividadesDTO;
/*      */ import sinco.business.PoaTableroColoresDTO;
/*      */ import sinco.business.SisMultiValoresDTO;
/*      */ import sinco.business.TGeneralDTO;
/*      */ import sinco.business.Utilidades;
/*      */ import sinco.data.CaracteristicasDAO;
/*      */ import sinco.data.CaracteristicasValorDAO;
/*      */ import sinco.data.DetalleSolicitudDAO;
/*      */ import sinco.data.IndicadorActividadesDAO;
/*      */ import sinco.data.IndicadorCaracteristicaDAO;
/*      */ import sinco.data.IndicadorResultadoDAO;
/*      */ import sinco.data.IndicadoresDAO;
/*      */ import sinco.data.PoaTableroColoresDAO;
/*      */ import sinco.data.SisMultiValoresDAO;
/*      */ import sinco.data.TGeneralDAO;
/*      */ import sinco.presentation.IndicadoresEfectividad;
/*      */ import sinco.presentation.IndicadoresEfectividadHTML;
/*      */ import sinco.spec.MenuDO;
/*      */ import sinco.spec.Varios;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class IndicadoresEfectividad
/*      */   implements HttpPresentation
/*      */ {
/*      */   private IndicadoresEfectividadHTML pagHTML;
/*      */   
/*      */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*   61 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*   62 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*      */     }
/*      */     
/*   65 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*   66 */     String _operacion = comms.request.getParameter("_operacion");
/*   67 */     if (_operacion == null || _operacion.length() == 0) {
/*   68 */       _operacion = "X";
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*   73 */     this.pagHTML = (IndicadoresEfectividadHTML)comms.xmlcFactory.create(IndicadoresEfectividadHTML.class);
/*   74 */     permisos(comms);
/*      */ 
/*      */ 
/*      */     
/*   78 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*   79 */       consulta(comms);
/*      */     }
/*   81 */     if (_operacion.equals("tablero")) {
/*   82 */       mostrarTablero(comms);
/*      */     }
/*   84 */     if (_operacion.equals("GR1")) {
/*   85 */       grafica(comms, idNav);
/*      */     }
/*      */ 
/*      */     
/*   89 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*   90 */     comms.response.writeDOM(this.pagHTML);
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
/*      */   private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  110 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*      */     
/*  112 */     Varios oVarios = new Varios();
/*  113 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Ind_indicadoresEfectividadAct");
/*  114 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Ind_indicadoresEfectividadDel");
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
/*      */   private void activarVista(String vista) {
/*  136 */     if (!vista.equals("tablero")) {
/*  137 */       HTMLElement sel = this.pagHTML.getElementDivTableroControl();
/*  138 */       sel.getParentNode().removeChild(sel);
/*      */     } 
/*  140 */     if (!vista.equals("consulta")) {
/*  141 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/*  142 */       sel.getParentNode().removeChild(sel);
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
/*  156 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/*  157 */     atrib.setValue(valor);
/*  158 */     return atrib;
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
/*  171 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  172 */     Element enlace = this.pagHTML.createElement("a");
/*  173 */     Node hijo = this.pagHTML.createTextNode(contenido);
/*  174 */     enlace.appendChild(hijo);
/*  175 */     Attr donde = this.pagHTML.createAttribute("href");
/*  176 */     donde.setValue(vinculo);
/*  177 */     enlace.setAttributeNode(donde);
/*  178 */     td.appendChild(enlace);
/*  179 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  180 */     return td;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private HTMLElement newtd(String contenido) {
/*  190 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  191 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  192 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  193 */     return td;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private HTMLElement newtd(String contenido, int colspan) {
/*  204 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  205 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  206 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  207 */     td.setAttributeNode(newAttr("colspan", "" + colspan));
/*  208 */     return td;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private HTMLElement newth(String contenido, String clase, int colspan) {
/*  219 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("th");
/*  220 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  221 */     td.setAttributeNode(newAttr("class", "" + clase));
/*  222 */     td.setAttributeNode(newAttr("colspan", "" + colspan));
/*  223 */     return td;
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
/*      */   private HTMLElement newtdColor(String contenido, String color) {
/*  235 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  236 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  237 */     td.setAttributeNode(newAttr("bgcolor", "#" + color));
/*  238 */     return td;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private HTMLElement newtdColor(String contenido, String color, int colspan) {
/*  249 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  250 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  251 */     td.setAttributeNode(newAttr("bgcolor", "#" + color));
/*  252 */     td.setAttributeNode(newAttr("colspan", "" + colspan));
/*  253 */     return td;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private HTMLElement newtdTitle(String contenido) {
/*  264 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  265 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  266 */     td.setAttributeNode(newAttr("class", "ca"));
/*      */     
/*  268 */     return td;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void consulta(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  274 */     activarVista("consulta");
/*      */     
/*  276 */     String c = " tipo_indicador='3'";
/*  277 */     HTMLSelectElement combo = this.pagHTML.getElementFindicador();
/*  278 */     llenarCombo(combo, "INDICADORES", "CODIGO_INDICADOR", "NOMBRE_INDICADOR", c, "", false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void mostrarTablero(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  286 */     activarVista("tablero");
/*      */     
/*  288 */     String indicador = "";
/*      */     try {
/*  290 */       indicador = comms.request.getParameter("findicador");
/*      */     }
/*  292 */     catch (Exception e) {
/*  293 */       String sPagina = "IndicadoresEfectividad.po?_operacion=X";
/*  294 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*      */     } 
/*      */     
/*  297 */     IndicadoresDAO res = new IndicadoresDAO();
/*  298 */     IndicadoresDTO ind = res.cargarRegistro(comms.request.getParameter("findicador"));
/*  299 */     String cod = ind.getCodigoIndicador();
/*      */ 
/*      */ 
/*      */     
/*  303 */     this.pagHTML.setTextMetaTotalProgramadaEd("" + ind.getMetaTotalProgramada());
/*  304 */     this.pagHTML.setTextMetaEstaVigenciaEd("" + ind.getMetaEstaVigencia());
/*  305 */     this.pagHTML.setTextAcumuladoVigenciasEd("" + ind.getAcumuladoVigencias());
/*  306 */     this.pagHTML.setTextAcumuladoEstaVigenciaEd("" + ind.getAcumuladoEstaVigencia());
/*  307 */     this.pagHTML.setTextLogroTotalEd("" + ind.getLogroTotal());
/*  308 */     this.pagHTML.setTextLogroTotalAcumuladoEd("" + ind.getLogroAcumuladoTotal());
/*      */ 
/*      */     
/*  311 */     this.pagHTML.setTextCodigoIndicadorEd("" + ind.getCodigoIndicador());
/*  312 */     this.pagHTML.setTextNombreIndicadorEd("" + ind.getNombreIndicador());
/*  313 */     this.pagHTML.setTextProcesoEd("" + ind.getNombreProceso());
/*  314 */     this.pagHTML.setTextCicloEd("" + ind.getNombreCiclo());
/*  315 */     this.pagHTML.setTextAreaEd("" + ind.getNombreArea());
/*  316 */     this.pagHTML.setTextObjetivoEstrategicoEd("" + ind.getNombreObjetivoEstrategico());
/*  317 */     this.pagHTML.setTextIndicadorAcuerdoEd("" + ind.getIndicadorAcuerdo());
/*  318 */     this.pagHTML.setTextTipoIndicadorEd("" + ind.getNombreTipoIndicador());
/*  319 */     this.pagHTML.setTextProyectoInversionEd("" + ind.getNombreProyectoInversion());
/*  320 */     this.pagHTML.setTextMetaPlanDeDesarrolloEd("" + ind.getNombreMetaPlanDeDesarrollo());
/*  321 */     this.pagHTML.setTextMetaProyectoEd("" + ind.getNombreMetaProyecto());
/*  322 */     this.pagHTML.setTextObjetivoIndicadorEd("" + ind.getObjetivoIndicador());
/*      */     
/*  324 */     this.pagHTML.setTextUnidadMedidaEd("" + ind.getNombreUnidadMedida());
/*  325 */     this.pagHTML.setTextFrecuenciaMedicionEd("" + ind.getNombreFrecuenciaMedicion());
/*  326 */     this.pagHTML.setTextFormulaEd("" + ind.getFormula());
/*      */ 
/*      */ 
/*      */     
/*  330 */     IndicadorActividadesDAO rsAct = new IndicadorActividadesDAO();
/*  331 */     Collection<IndicadorActividadesDTO> actividades = rsAct.cargarTodos(indicador);
/*  332 */     rsAct.close();
/*      */ 
/*      */     
/*  335 */     Iterator<IndicadorActividadesDTO> iteActividades = actividades.iterator();
/*      */     
/*  337 */     int ejecucion = 0;
/*      */     
/*      */     try {
/*  340 */       IndicadorActividadesDTO actividad = (IndicadorActividadesDTO)iteActividades.next();
/*      */ 
/*      */ 
/*      */       
/*  344 */       int esperado1 = ind.getValormes1();
/*  345 */       int esperado2 = ind.getValormes2();
/*  346 */       int esperado3 = ind.getValormes3();
/*  347 */       int esperado4 = ind.getValormes4();
/*  348 */       int esperado5 = ind.getValormes5();
/*  349 */       int esperado6 = ind.getValormes6();
/*  350 */       int esperado7 = ind.getValormes7();
/*  351 */       int esperado8 = ind.getValormes8();
/*  352 */       int esperado9 = ind.getValormes9();
/*  353 */       int esperado10 = ind.getValormes10();
/*  354 */       int esperado11 = ind.getValormes11();
/*  355 */       int esperado12 = ind.getValormes12();
/*      */       
/*  357 */       int valorReal1 = valorReal(actividad.getIdIndicador(), "Enero");
/*  358 */       int valorReal2 = valorReal(actividad.getIdIndicador(), "Febrero");
/*  359 */       int valorReal3 = valorReal(actividad.getIdIndicador(), "Marzo");
/*  360 */       int valorReal4 = valorReal(actividad.getIdIndicador(), "Abril");
/*  361 */       int valorReal5 = valorReal(actividad.getIdIndicador(), "Mayo");
/*  362 */       int valorReal6 = valorReal(actividad.getIdIndicador(), "Junio");
/*  363 */       int valorReal7 = valorReal(actividad.getIdIndicador(), "Julio");
/*  364 */       int valorReal8 = valorReal(actividad.getIdIndicador(), "Agosto");
/*  365 */       int valorReal9 = valorReal(actividad.getIdIndicador(), "Septiembre");
/*  366 */       int valorReal10 = valorReal(actividad.getIdIndicador(), "Octubre");
/*  367 */       int valorReal11 = valorReal(actividad.getIdIndicador(), "Noviembre");
/*  368 */       int valorReal12 = valorReal(actividad.getIdIndicador(), "Diciembre");
/*      */ 
/*      */       
/*      */       try {
/*  372 */         ejecucion = valorReal1 * 100 / esperado1;
/*      */       }
/*  374 */       catch (Exception e) {
/*  375 */         ejecucion = 0;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  381 */         ejecucion = valorReal2 * 100 / esperado2;
/*  382 */       } catch (Exception e) {
/*  383 */         ejecucion = 0;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  388 */         ejecucion = valorReal3 * 100 / esperado3;
/*  389 */       } catch (Exception e) {
/*  390 */         ejecucion = 0;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  395 */         ejecucion = valorReal4 * 100 / esperado4;
/*  396 */       } catch (Exception e) {
/*  397 */         ejecucion = 0;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  402 */         ejecucion = valorReal5 * 100 / esperado5;
/*  403 */       } catch (Exception e) {
/*  404 */         ejecucion = 0;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  409 */         ejecucion = valorReal6 * 100 / esperado6;
/*  410 */       } catch (Exception e) {
/*  411 */         ejecucion = 0;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  416 */         ejecucion = valorReal7 * 100 / esperado7;
/*  417 */       } catch (Exception e) {
/*  418 */         ejecucion = 0;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  423 */         ejecucion = valorReal8 * 100 / esperado8;
/*  424 */       } catch (Exception e) {
/*  425 */         ejecucion = 0;
/*      */       } 
/*      */       
/*      */       try {
/*  429 */         ejecucion = valorReal9 * 100 / esperado9;
/*  430 */       } catch (Exception e) {
/*  431 */         ejecucion = 0;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  436 */         ejecucion = valorReal10 * 100 / esperado10;
/*  437 */       } catch (Exception e) {
/*  438 */         ejecucion = 0;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  443 */         ejecucion = valorReal11 * 100 / esperado11;
/*  444 */       } catch (Exception e) {
/*  445 */         ejecucion = 0;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  453 */         ejecucion = valorReal12 * 100 / esperado12;
/*      */       }
/*  455 */       catch (Exception e) {
/*  456 */         ejecucion = 0;
/*      */       }
/*      */     
/*  459 */     } catch (Exception e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  466 */     caracteristicas(Integer.parseInt(ind.getFormula()), comms, ind);
/*      */     
/*  468 */     HTMLImageElement gra = this.pagHTML.getElementGrafica();
/*  469 */     gra.setSrc("IndicadoresEfectividad.po?indicador=" + cod + "&_operacion=GR1");
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
/*      */   private void grafica(HttpPresentationComms comms, String idNav) throws HttpPresentationException, KeywordValueException {
/*  483 */     String indicador = comms.request.getParameter("indicador");
/*      */ 
/*      */ 
/*      */     
/*  487 */     IndicadoresDAO res = new IndicadoresDAO();
/*  488 */     IndicadoresDTO ind = res.cargarRegistro(indicador);
/*      */     
/*  490 */     IndicadorActividadesDAO rsAct = new IndicadorActividadesDAO();
/*  491 */     Collection<IndicadorActividadesDTO> actividades = rsAct.cargarTodos(indicador);
/*  492 */     rsAct.close();
/*      */ 
/*      */     
/*  495 */     Iterator<IndicadorActividadesDTO> iteActividades = actividades.iterator();
/*  496 */     IndicadorActividadesDTO actividad = (IndicadorActividadesDTO)iteActividades.next();
/*      */ 
/*      */     
/*  499 */     int esperado1 = ind.getValormes1();
/*  500 */     int esperado2 = ind.getValormes2();
/*  501 */     int esperado3 = ind.getValormes3();
/*  502 */     int esperado4 = ind.getValormes4();
/*  503 */     int esperado5 = ind.getValormes5();
/*  504 */     int esperado6 = ind.getValormes6();
/*  505 */     int esperado7 = ind.getValormes7();
/*  506 */     int esperado8 = ind.getValormes8();
/*  507 */     int esperado9 = ind.getValormes9();
/*  508 */     int esperado10 = ind.getValormes10();
/*  509 */     int esperado11 = ind.getValormes11();
/*  510 */     int esperado12 = ind.getValormes12();
/*      */     
/*  512 */     int valorReal1 = valorReal(actividad.getIdIndicador(), "Enero");
/*  513 */     int valorReal2 = valorReal(actividad.getIdIndicador(), "Febrero");
/*  514 */     int valorReal3 = valorReal(actividad.getIdIndicador(), "Marzo");
/*  515 */     int valorReal4 = valorReal(actividad.getIdIndicador(), "Abril");
/*  516 */     int valorReal5 = valorReal(actividad.getIdIndicador(), "Mayo");
/*  517 */     int valorReal6 = valorReal(actividad.getIdIndicador(), "Junio");
/*  518 */     int valorReal7 = valorReal(actividad.getIdIndicador(), "Julio");
/*  519 */     int valorReal8 = valorReal(actividad.getIdIndicador(), "Agosto");
/*  520 */     int valorReal9 = valorReal(actividad.getIdIndicador(), "Septiembre");
/*  521 */     int valorReal10 = valorReal(actividad.getIdIndicador(), "Octubre");
/*  522 */     int valorReal11 = valorReal(actividad.getIdIndicador(), "Noviembre");
/*  523 */     int valorReal12 = valorReal(actividad.getIdIndicador(), "Diciembre");
/*      */     
/*  525 */     String frecuencia = ind.getFrecuenciaMedicion();
/*  526 */     DefaultCategoryDataset objDatos = new DefaultCategoryDataset();
/*      */     
/*  528 */     Utilidades.grabarLog(frecuencia);
/*      */ 
/*      */     
/*  531 */     if (frecuencia.equals("1")) {
/*      */       
/*  533 */       objDatos.addValue(esperado1, "esperado", "Enero");
/*  534 */       objDatos.addValue(esperado2, "esperado", "Febrero");
/*  535 */       objDatos.addValue(esperado3, "esperado", "Marzo");
/*  536 */       objDatos.addValue(esperado4, "esperado", "Abril");
/*  537 */       objDatos.addValue(esperado5, "esperado", "Mayo");
/*  538 */       objDatos.addValue(esperado6, "esperado", "Junio");
/*  539 */       objDatos.addValue(esperado7, "esperado", "Julio");
/*  540 */       objDatos.addValue(esperado8, "esperado", "Agosto");
/*  541 */       objDatos.addValue(esperado9, "esperado", "Septiembre");
/*  542 */       objDatos.addValue(esperado10, "esperado", "Ooctubre");
/*  543 */       objDatos.addValue(esperado11, "esperado", "Noviembre");
/*  544 */       objDatos.addValue(esperado12, "esperado", "Diciembre");
/*      */ 
/*      */       
/*  547 */       objDatos.addValue(valorReal1, "Real", "Enero");
/*  548 */       objDatos.addValue(valorReal2, "Real", "Febrero");
/*  549 */       objDatos.addValue(valorReal3, "Real", "Marzo");
/*  550 */       objDatos.addValue(valorReal4, "Real", "Abril");
/*  551 */       objDatos.addValue(valorReal5, "Real", "Mayo");
/*  552 */       objDatos.addValue(valorReal6, "Real", "Junio");
/*  553 */       objDatos.addValue(valorReal7, "Real", "Julio");
/*  554 */       objDatos.addValue(valorReal8, "Real", "Agosto");
/*  555 */       objDatos.addValue(valorReal9, "Real", "Septiembre");
/*  556 */       objDatos.addValue(valorReal10, "Real", "Octubre");
/*  557 */       objDatos.addValue(valorReal11, "Real", "Noviembre");
/*  558 */       objDatos.addValue(valorReal12, "Real", "Diciembre");
/*      */     } 
/*      */     
/*  561 */     if (frecuencia.equals("2")) {
/*      */       
/*  563 */       objDatos.addValue(esperado2, "esperado", "Febrero");
/*  564 */       objDatos.addValue(esperado4, "esperado", "Abril");
/*  565 */       objDatos.addValue(esperado6, "esperado", "Junio");
/*  566 */       objDatos.addValue(esperado8, "esperado", "Agosto");
/*  567 */       objDatos.addValue(esperado10, "esperado", "Ooctubre");
/*  568 */       objDatos.addValue(esperado12, "esperado", "Diciembre");
/*      */       
/*  570 */       objDatos.addValue(valorReal2, "Real", "Febrero");
/*  571 */       objDatos.addValue(valorReal4, "Real", "Abril");
/*  572 */       objDatos.addValue(valorReal6, "Real", "Junio");
/*  573 */       objDatos.addValue(valorReal8, "Real", "Agosto");
/*  574 */       objDatos.addValue(valorReal10, "Real", "Octubre");
/*  575 */       objDatos.addValue(valorReal12, "Real", "Diciembre");
/*      */     } 
/*      */ 
/*      */     
/*  579 */     if (frecuencia.equals("3")) {
/*      */ 
/*      */       
/*  582 */       objDatos.addValue(esperado3, "esperado", "Marzo");
/*  583 */       objDatos.addValue(esperado6, "esperado", "Junio");
/*  584 */       objDatos.addValue(esperado9, "esperado", "Septiembre");
/*  585 */       objDatos.addValue(esperado12, "esperado", "Diciembre");
/*      */ 
/*      */       
/*  588 */       objDatos.addValue(valorReal3, "Real", "Marzo");
/*  589 */       objDatos.addValue(valorReal6, "Real", "Junio");
/*  590 */       objDatos.addValue(valorReal9, "Real", "Septiembre");
/*  591 */       objDatos.addValue(valorReal12, "Real", "Diciembre");
/*      */     } 
/*  593 */     if (frecuencia.equals("4")) {
/*      */       
/*  595 */       objDatos.addValue(esperado4, "esperado", "Abril");
/*  596 */       objDatos.addValue(esperado8, "esperado", "Agosto");
/*  597 */       objDatos.addValue(esperado12, "esperado", "Diciembre");
/*      */ 
/*      */       
/*  600 */       objDatos.addValue(valorReal4, "Real", "Abril");
/*  601 */       objDatos.addValue(valorReal8, "Real", "Agosto");
/*  602 */       objDatos.addValue(valorReal12, "Real", "Diciembre");
/*      */     } 
/*  604 */     if (frecuencia.equals("5")) {
/*      */       
/*  606 */       objDatos.addValue(esperado6, "esperado", "Junio");
/*  607 */       objDatos.addValue(esperado12, "esperado", "Diciembre");
/*      */ 
/*      */       
/*  610 */       objDatos.addValue(valorReal6, "Real", "Junio");
/*  611 */       objDatos.addValue(valorReal12, "Real", "Diciembre");
/*      */     } 
/*      */     
/*  614 */     if (frecuencia.equals("6")) {
/*      */       
/*  616 */       objDatos.addValue(esperado12, "esperado", "Diciembre");
/*      */       
/*  618 */       objDatos.addValue(valorReal12, "Real", "Diciembre");
/*      */     } 
/*      */     
/*  621 */     SpiderWebPlot spiderwebplot = new SpiderWebPlot(objDatos);
/*      */     
/*  623 */     JFreeChart jfreechart = new JFreeChart("Esperado vs Ejecutado", TextTitle.DEFAULT_FONT, spiderwebplot, false);
/*  624 */     LegendTitle legendtitle = new LegendTitle(spiderwebplot);
/*  625 */     legendtitle.setPosition(RectangleEdge.BOTTOM);
/*  626 */     jfreechart.addSubtitle(legendtitle);
/*      */ 
/*      */     
/*      */     try {
/*  630 */       ChartUtilities.writeChartAsJPEG(comms.response.getOutputStream(), jfreechart, 700, 350);
/*      */     }
/*  632 */     catch (Exception e) {}
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
/*      */   private void llenarCombo(HTMLSelectElement combo, String tabla, String codigo, String descripcion, String condicion, String defecto, boolean dejarBlanco) {
/*  662 */     if (dejarBlanco) {
/*  663 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  664 */       op.setValue("");
/*  665 */       op.appendChild(this.pagHTML.createTextNode(""));
/*  666 */       combo.appendChild(op);
/*      */     } 
/*  668 */     TGeneralDAO rsTGen = new TGeneralDAO();
/*  669 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/*  670 */     rsTGen.close();
/*  671 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/*  672 */     while (iterator.hasNext()) {
/*  673 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/*  674 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  675 */       op.setValue("" + regGeneral.getCodigoS());
/*  676 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/*  677 */       if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
/*  678 */         Attr escogida = this.pagHTML.createAttribute("selected");
/*  679 */         escogida.setValue("on");
/*  680 */         op.setAttributeNode(escogida);
/*      */       } 
/*  682 */       combo.appendChild(op);
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
/*      */   private void comboMultivalores(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco) {
/*  699 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/*  700 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/*  701 */     rs.close();
/*  702 */     if (dejarBlanco) {
/*  703 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  704 */       op.setValue("");
/*  705 */       op.appendChild(this.pagHTML.createTextNode(""));
/*  706 */       combo.appendChild(op);
/*      */     } 
/*  708 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/*  709 */     while (iterator.hasNext()) {
/*  710 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/*  711 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  712 */       op.setValue("" + reg.getCodigo());
/*  713 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/*  714 */       if (defecto.equals(reg.getCodigo())) {
/*  715 */         Attr escogida = this.pagHTML.createAttribute("selected");
/*  716 */         escogida.setValue("on");
/*  717 */         op.setAttributeNode(escogida);
/*      */       } 
/*  719 */       combo.appendChild(op);
/*      */     } 
/*  721 */     arr.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void llenarComboMeses(HTMLSelectElement combo, String defecto, boolean dejarBlanco) {
/*  728 */     if (dejarBlanco) {
/*  729 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  730 */       op.setValue("");
/*  731 */       op.appendChild(this.pagHTML.createTextNode(""));
/*  732 */       combo.appendChild(op);
/*      */     } 
/*  734 */     for (int i = 1; i <= 12; i++) {
/*  735 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  736 */       op.setValue("mes" + i);
/*  737 */       op.appendChild(this.pagHTML.createTextNode(getMes(i)));
/*  738 */       if (defecto.equals("mes" + i)) {
/*  739 */         Attr escogida = this.pagHTML.createAttribute("selected");
/*  740 */         escogida.setValue("on");
/*  741 */         op.setAttributeNode(escogida);
/*      */       } 
/*  743 */       combo.appendChild(op);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private String getMes(int mes) {
/*  749 */     switch (mes) {
/*      */       case 1:
/*  751 */         return "Enero";
/*      */       case 2:
/*  753 */         return "Febrero";
/*      */       
/*      */       case 3:
/*  756 */         return "Marzo";
/*      */       case 4:
/*  758 */         return "Abril";
/*      */       case 5:
/*  760 */         return "Mayo";
/*      */       case 6:
/*  762 */         return "Junio";
/*      */       case 7:
/*  764 */         return "Julio";
/*      */       case 8:
/*  766 */         return "Agosto";
/*      */       case 9:
/*  768 */         return "Septiembre";
/*      */       case 10:
/*  770 */         return "Octubre";
/*      */       case 11:
/*  772 */         return "Noviembre";
/*      */       case 12:
/*  774 */         return "Diciembre";
/*      */     } 
/*      */ 
/*      */     
/*  778 */     return "";
/*      */   }
/*      */   
/*      */   private String getMesNumero(String mes) {
/*  782 */     if (mes.equals("Enero")) {
/*  783 */       return "mes1";
/*      */     }
/*  785 */     if (mes.equals("Febrero")) {
/*  786 */       return "mes2";
/*      */     }
/*  788 */     if (mes.equals("Marzo")) {
/*  789 */       return "mes3";
/*      */     }
/*  791 */     if (mes.equals("Abril")) {
/*  792 */       return "mes4";
/*      */     }
/*  794 */     if (mes.equals("Mayo")) {
/*  795 */       return "mes5";
/*      */     }
/*  797 */     if (mes.equals("Junio")) {
/*  798 */       return "mes6";
/*      */     }
/*  800 */     if (mes.equals("Julio")) {
/*  801 */       return "mes7";
/*      */     }
/*  803 */     if (mes.equals("Agosto")) {
/*  804 */       return "mes8";
/*      */     }
/*  806 */     if (mes.equals("Septiembre")) {
/*  807 */       return "mes9";
/*      */     }
/*  809 */     if (mes.equals("Octubre")) {
/*  810 */       return "mes10";
/*      */     }
/*  812 */     if (mes.equals("Noviembre")) {
/*  813 */       return "mes11";
/*      */     }
/*  815 */     if (mes.equals("Diciembre")) {
/*  816 */       return "mes12";
/*      */     }
/*  818 */     return "";
/*      */   }
/*      */   
/*      */   private int getValorEsperado(int mes, PoaMaestroActividadesDTO poa) {
/*  822 */     switch (mes) {
/*      */       case 1:
/*  824 */         return poa.getValorMes1();
/*      */       case 2:
/*  826 */         return poa.getValorMes2();
/*      */       
/*      */       case 3:
/*  829 */         return poa.getValorMes3();
/*      */       case 4:
/*  831 */         return poa.getValorMes4();
/*      */       case 5:
/*  833 */         return poa.getValorMes5();
/*      */       case 6:
/*  835 */         return poa.getValorMes6();
/*      */       case 7:
/*  837 */         return poa.getValorMes7();
/*      */       case 8:
/*  839 */         return poa.getValorMes8();
/*      */       case 9:
/*  841 */         return poa.getValorMes9();
/*      */       case 10:
/*  843 */         return poa.getValorMes10();
/*      */       case 11:
/*  845 */         return poa.getValorMes11();
/*      */       case 12:
/*  847 */         return poa.getValorMes12();
/*      */     } 
/*      */ 
/*      */     
/*  851 */     return 0;
/*      */   }
/*      */   
/*      */   private int valorReal(String indicador, String mes) {
/*      */     try {
/*  856 */       IndicadorActividadesDAO rs = new IndicadorActividadesDAO();
/*  857 */       IndicadorActividadesDTO reg = rs.cargarRegistro(indicador, mes);
/*  858 */       rs.close();
/*  859 */       return reg.getPorcentaje();
/*  860 */     } catch (Exception e) {
/*      */ 
/*      */       
/*  863 */       return 0;
/*      */     } 
/*      */   }
/*      */   private String urlLogro(String indicador, String mes) {
/*      */     try {
/*  868 */       IndicadorActividadesDAO rs = new IndicadorActividadesDAO();
/*  869 */       IndicadorActividadesDTO reg = rs.cargarRegistro(indicador, mes);
/*  870 */       rs.close();
/*      */       
/*  872 */       return "IndicadorActividades.po?_operacion=V&idIndicador=" + reg.getIdIndicador() + "&mes=" + mes;
/*  873 */     } catch (Exception e) {
/*      */ 
/*      */       
/*  876 */       return "#";
/*      */     } 
/*      */   }
/*      */   private int totalEsperado(PoaMaestroActividadesDTO reg, int mes) {
/*  880 */     int totalEsperado = 0;
/*  881 */     switch (mes) {
/*      */       case 12:
/*  883 */         totalEsperado += reg.getValorMes12();
/*      */       case 11:
/*  885 */         totalEsperado += reg.getValorMes11();
/*      */       case 10:
/*  887 */         totalEsperado += reg.getValorMes10();
/*      */       case 9:
/*  889 */         totalEsperado += reg.getValorMes9();
/*      */       case 8:
/*  891 */         totalEsperado += reg.getValorMes8();
/*      */       case 7:
/*  893 */         totalEsperado += reg.getValorMes7();
/*      */       case 6:
/*  895 */         totalEsperado += reg.getValorMes6();
/*      */       case 5:
/*  897 */         totalEsperado += reg.getValorMes5();
/*      */       case 4:
/*  899 */         totalEsperado += reg.getValorMes4();
/*      */       case 3:
/*  901 */         totalEsperado += reg.getValorMes3();
/*      */       case 2:
/*  903 */         totalEsperado += reg.getValorMes2();
/*      */       case 1:
/*  905 */         totalEsperado += reg.getValorMes1();
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/*  910 */     return totalEsperado;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private String color(double valor) {
/*  916 */     PoaTableroColoresDAO rs = new PoaTableroColoresDAO();
/*  917 */     Collection<PoaTableroColoresDTO> colores = rs.cargarTodos("");
/*  918 */     rs.close();
/*  919 */     Iterator<PoaTableroColoresDTO> iterator = colores.iterator();
/*  920 */     while (iterator.hasNext()) {
/*  921 */       PoaTableroColoresDTO color = (PoaTableroColoresDTO)iterator.next();
/*  922 */       if (valor <= color.getValorFinal() && valor >= color.getValorInicial()) {
/*  923 */         return color.getColor();
/*      */       }
/*      */     } 
/*  926 */     return "FFFFFF";
/*      */   }
/*      */   
/*      */   private Element crearEnlace(String contenido, String link) {
/*  930 */     Element enlace = this.pagHTML.createElement("a");
/*  931 */     Node hijo = this.pagHTML.createTextNode(contenido);
/*  932 */     Attr donde = this.pagHTML.createAttribute("href");
/*  933 */     donde.setValue(link);
/*  934 */     enlace.setAttributeNode(donde);
/*  935 */     enlace.appendChild(hijo);
/*  936 */     return enlace;
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
/*      */   private void caracteristicas(int codigo, HttpPresentationComms comms, IndicadoresDTO indicador) throws HttpPresentationException, KeywordValueException {
/*  951 */     CaracteristicasValorDAO rsTGen = new CaracteristicasValorDAO();
/*      */     
/*  953 */     HTMLTableElement tabla = this.pagHTML.getElementTablaVariables();
/*      */     
/*  955 */     CaracteristicasDAO rsCaracteristicas = new CaracteristicasDAO();
/*  956 */     Collection arr = rsCaracteristicas.cargarTodosParaServicio(codigo, "C", "A");
/*  957 */     rsCaracteristicas.close();
/*      */     
/*  959 */     int solPadre = 0;
/*      */     try {
/*  961 */       solPadre = Integer.parseInt(comms.request.getParameter("solPadre"));
/*      */       
/*  963 */       comms.session.getSessionData().set("volverA", "" + solPadre);
/*      */     
/*      */     }
/*  966 */     catch (Exception e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  971 */     boolean extender = false;
/*      */ 
/*      */     
/*  974 */     boolean fondo = true;
/*  975 */     DetalleSolicitudDAO dsf = new DetalleSolicitudDAO();
/*  976 */     int capturas = 0;
/*  977 */     String cadenaFechas = "";
/*      */     
/*  979 */     Iterator iterator = arr.iterator();
/*  980 */     Collection arrAnidadas = new ArrayList();
/*  981 */     Collection arrDependen = new ArrayList();
/*  982 */     HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  983 */     eltr.appendChild(newtdTitle("Mes", "cf1"));
/*  984 */     while (iterator.hasNext()) {
/*  985 */       CaracteristicasDTO regCar = (CaracteristicasDTO)iterator.next();
/*  986 */       IndicadorCaracteristicaDAO icrs = new IndicadorCaracteristicaDAO();
/*      */       
/*  988 */       HTMLElement tdCaracteristica = (HTMLElement)this.pagHTML.createElement("th");
/*      */ 
/*      */ 
/*      */       
/*  992 */       fondo = !fondo;
/*  993 */       eltr.setAttributeNode(newAttr("class", "car"));
/*  994 */       IndicadorCaracteristicaDTO car = icrs.cargarRegistro(indicador.getCiclo(), indicador.getCodigoIndicador(), "", regCar.getCodigo());
/*  995 */       if (car.getEstado().equals("A") && regCar.getCalificar().equals("N")) {
/*  996 */         eltr.setAttributeNode(newAttr("codigo", "" + regCar.getCodigo()));
/*      */         
/*  998 */         tdCaracteristica = newtdTitle(regCar.getDescripcion(), "cf1");
/*  999 */         eltr.appendChild(tdCaracteristica);
/* 1000 */         if (indicador.getFormula().equals("9")) {
/* 1001 */           tdCaracteristica = newtdTitle(regCar.getDescripcion() + " %", "cf1");
/* 1002 */           eltr.appendChild(tdCaracteristica);
/*      */         } 
/*      */       } 
/* 1005 */       icrs.close();
/*      */     } 
/* 1007 */     if (!indicador.getFormula().equals("9")) {
/* 1008 */       eltr.appendChild(newtdTitle("Programado", "cf1"));
/* 1009 */       eltr.appendChild(newtdTitle("Ejecutado", "cf1"));
/* 1010 */       eltr.appendChild(newtdTitle("Cumplimiento", "cf1"));
/*      */     } 
/* 1012 */     tabla.appendChild(eltr);
/*      */ 
/*      */ 
/*      */     
/* 1016 */     for (int i = 0; i < 12; i++) {
/* 1017 */       iterator = arr.iterator();
/* 1018 */       eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 1019 */       eltr.appendChild(newtd(getMes(i + 1)));
/* 1020 */       IndicadorCaracteristicaDAO icrs = new IndicadorCaracteristicaDAO();
/* 1021 */       Collection<IndicadorCaracteristicaDTO> caracteristicas = icrs.cargarTodos(indicador.getCiclo(), indicador.getCodigoIndicador(), getMes(i + 1));
/* 1022 */       Iterator<IndicadorCaracteristicaDTO> iterator1 = caracteristicas.iterator();
/* 1023 */       int formula = Integer.parseInt(indicador.getFormula());
/* 1024 */       calcularFormula(icrs, formula, indicador.getCiclo(), indicador.getCodigoIndicador(), getMes(i + 1));
/* 1025 */       IndicadorResultadoDAO ird = new IndicadorResultadoDAO();
/* 1026 */       while (iterator1.hasNext()) {
/* 1027 */         IndicadorCaracteristicaDTO car = (IndicadorCaracteristicaDTO)iterator1.next();
/* 1028 */         String url = "IndicadorCaracteristica.po?_operacion=V&idRegistro=" + car.getIdRegistro();
/* 1029 */         eltr.appendChild(newtdhref("" + car.getValorEjecutado(), url));
/* 1030 */         if (indicador.getFormula().equals("9")) {
/*      */           try {
/* 1032 */             IndicadorResultadoDTO resultado = ird.cargarRegistro(indicador.getCiclo(), indicador.getCodigoIndicador(), getMes(i + 1));
/* 1033 */             DecimalFormat decimales = new DecimalFormat("0.0000");
/* 1034 */             if (resultado.getValorEjecutado() > 0.0D) {
/* 1035 */               double result = car.getValorEjecutado() / resultado.getValorProgramado() * 100.0D;
/* 1036 */               eltr.appendChild(newtdColor(decimales.format(result), color(result))); continue;
/*      */             } 
/* 1038 */             eltr.appendChild(newtd("N.A"));
/*      */           }
/* 1040 */           catch (Exception e) {
/* 1041 */             eltr.appendChild(newtd("N.A"));
/*      */           } 
/*      */         }
/*      */       } 
/* 1045 */       if (!indicador.getFormula().equals("9")) {
/*      */         try {
/* 1047 */           IndicadorResultadoDTO resultado = ird.cargarRegistro(indicador.getCiclo(), indicador.getCodigoIndicador(), getMes(i + 1));
/* 1048 */           eltr.appendChild(newtd("" + resultado.getValorProgramado()));
/* 1049 */           eltr.appendChild(newtd("" + resultado.getValorEjecutado()));
/* 1050 */           if (resultado.getValorEjecutado() > 0.0D) {
/* 1051 */             DecimalFormat decimales = new DecimalFormat("0.0000");
/* 1052 */             double result = resultado.getValorProgramado() / resultado.getValorEjecutado();
/* 1053 */             eltr.appendChild(newtdColor(decimales.format(result), color(result)));
/*      */           } else {
/* 1055 */             eltr.appendChild(newtd("N.A"));
/*      */           } 
/* 1057 */         } catch (Exception e) {}
/*      */       }
/*      */       
/* 1060 */       ird.close();
/* 1061 */       icrs.close();
/* 1062 */       tabla.appendChild(eltr);
/*      */     } 
/* 1064 */     dsf.close();
/* 1065 */     rsTGen.close();
/* 1066 */     arr.clear();
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
/* 1077 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 1078 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 1079 */     td.setAttributeNode(newAttr("class", clase));
/* 1080 */     return td; } private void calcularFormula(IndicadorCaracteristicaDAO daoCar, int formula, int ciclo, String indicador, String mes) { IndicadorResultadoDAO rs4; double promedioProgramado, promedioEjecutado; IndicadorCaracteristicaDTO alph5, alph4, alph3, alph2, alph1, ap5, ap4, ap3, ap2, ap1; IndicadorResultadoDAO rs3; IndicadorCaracteristicaDTO a5, a4, a3, a2, a1; IndicadorResultadoDAO rs2; int contador; double multiplicacionProgramado, multiplicacionEjecutado; IndicadorCaracteristicaDTO x5, x4, x3, x2, x1; IndicadorResultadoDAO rs1; IndicadorCaracteristicaDTO ejecutado;
/*      */     IndicadorResultadoDAO rs;
/*      */     IndicadorCaracteristicaDTO v2, v1;
/*      */     IndicadorResultadoDAO rs0;
/*      */     IndicadorCaracteristicaDTO pr, pg, pe, er, eg, ee;
/* 1085 */     double resultadoEjecutado = 0.0D;
/* 1086 */     double resultadoProgramado = 0.0D;
/*      */     
/* 1088 */     switch (formula) {
/*      */       case 5:
/* 1090 */         ee = daoCar.cargarRegistro(ciclo, indicador, mes, 5);
/* 1091 */         eg = daoCar.cargarRegistro(ciclo, indicador, mes, 6);
/* 1092 */         er = daoCar.cargarRegistro(ciclo, indicador, mes, 7);
/* 1093 */         pe = daoCar.cargarRegistro(ciclo, indicador, "NO", 17);
/* 1094 */         pg = daoCar.cargarRegistro(ciclo, indicador, "NO", 18);
/* 1095 */         pr = daoCar.cargarRegistro(ciclo, indicador, "NO", 19);
/* 1096 */         resultadoEjecutado = 0.0D;
/* 1097 */         resultadoProgramado = 0.0D;
/*      */         try {
/* 1099 */           resultadoEjecutado = (ee.getValorEjecutado() * pe.getValorProgramado()) + (eg.getValorEjecutado() * pg.getValorProgramado()) + (er.getValorEjecutado() * pr.getValorProgramado());
/*      */         
/*      */         }
/* 1102 */         catch (Exception ex) {}
/*      */ 
/*      */         
/*      */         try {
/* 1106 */           resultadoProgramado = (ee.getValorProgramado() * pe.getValorProgramado()) + (eg.getValorProgramado() * pg.getValorProgramado()) + (er.getValorProgramado() * pr.getValorProgramado());
/*      */         
/*      */         }
/* 1109 */         catch (Exception ex) {}
/*      */ 
/*      */         
/* 1112 */         rs0 = new IndicadorResultadoDAO();
/* 1113 */         if (rs0.cargarRegistro(ciclo, indicador, mes) != null) {
/*      */           try {
/* 1115 */             rs0.modificarRegistro(ciclo, indicador, mes, resultadoProgramado, resultadoEjecutado, "A");
/* 1116 */           } catch (Exception ex) {}
/*      */         } else {
/*      */ 
/*      */           
/*      */           try {
/* 1121 */             rs0.crearRegistro(ciclo, indicador, mes, resultadoProgramado, resultadoEjecutado, "A");
/* 1122 */           } catch (Exception ex) {}
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1127 */         rs0.close();
/*      */         break;
/*      */       case 6:
/* 1130 */         v1 = daoCar.cargarRegistro(ciclo, indicador, mes, 8);
/* 1131 */         v2 = daoCar.cargarRegistro(ciclo, indicador, mes, 9);
/* 1132 */         resultadoEjecutado = 0.0D;
/* 1133 */         resultadoProgramado = 0.0D;
/*      */         try {
/* 1135 */           resultadoEjecutado = (v1.getValorEjecutado() - v2.getValorEjecutado()) / v2.getValorEjecutado();
/* 1136 */         } catch (Exception e) {}
/*      */ 
/*      */         
/*      */         try {
/* 1140 */           resultadoProgramado = (v1.getValorProgramado() - v2.getValorProgramado()) / v2.getValorProgramado();
/* 1141 */         } catch (Exception e) {}
/*      */ 
/*      */         
/* 1144 */         rs = new IndicadorResultadoDAO();
/* 1145 */         if (rs.cargarRegistro(ciclo, indicador, mes) != null) {
/*      */           try {
/* 1147 */             rs.modificarRegistro(ciclo, indicador, mes, resultadoProgramado, resultadoEjecutado, "A");
/* 1148 */           } catch (Exception e) {}
/*      */         } else {
/*      */ 
/*      */           
/*      */           try {
/* 1153 */             rs.crearRegistro(ciclo, indicador, mes, resultadoProgramado, resultadoEjecutado, "A");
/* 1154 */           } catch (Exception e) {}
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1159 */         rs.close();
/*      */         break;
/*      */       case 7:
/* 1162 */         ejecutado = daoCar.cargarRegistro(ciclo, indicador, mes, 10);
/* 1163 */         resultadoProgramado = 0.0D;
/* 1164 */         resultadoEjecutado = 0.0D;
/*      */         try {
/* 1166 */           resultadoProgramado = ejecutado.getValorProgramado();
/* 1167 */           resultadoEjecutado = ejecutado.getValorEjecutado() / ejecutado.getValorProgramado() * 100.0D;
/* 1168 */         } catch (Exception e) {}
/*      */ 
/*      */         
/* 1171 */         rs1 = new IndicadorResultadoDAO();
/* 1172 */         if (rs1.cargarRegistro(ciclo, indicador, mes) != null) {
/*      */           try {
/* 1174 */             rs1.modificarRegistro(ciclo, indicador, mes, resultadoProgramado, resultadoEjecutado, "A");
/* 1175 */           } catch (Exception e) {}
/*      */         } else {
/*      */ 
/*      */           
/*      */           try {
/* 1180 */             rs1.crearRegistro(ciclo, indicador, mes, resultadoProgramado, resultadoEjecutado, "A");
/* 1181 */           } catch (Exception e) {}
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1186 */         rs1.close();
/*      */         break;
/*      */       
/*      */       case 8:
/* 1190 */         x1 = daoCar.cargarRegistro(ciclo, indicador, mes, 12);
/* 1191 */         x2 = daoCar.cargarRegistro(ciclo, indicador, mes, 13);
/* 1192 */         x3 = daoCar.cargarRegistro(ciclo, indicador, mes, 14);
/* 1193 */         x4 = daoCar.cargarRegistro(ciclo, indicador, mes, 15);
/* 1194 */         x5 = daoCar.cargarRegistro(ciclo, indicador, mes, 16);
/* 1195 */         resultadoProgramado = 0.0D;
/* 1196 */         resultadoEjecutado = 0.0D;
/* 1197 */         multiplicacionEjecutado = 1.0D;
/* 1198 */         multiplicacionProgramado = 1.0D;
/* 1199 */         contador = 0;
/*      */         try {
/* 1201 */           if (x1.getEstado().equals("A")) {
/* 1202 */             multiplicacionEjecutado *= x1.getValorEjecutado();
/* 1203 */             contador++;
/*      */           } 
/* 1205 */           if (x2.getEstado().equals("A")) {
/* 1206 */             multiplicacionEjecutado *= x2.getValorEjecutado();
/* 1207 */             contador++;
/*      */           } 
/* 1209 */           if (x3.getEstado().equals("A")) {
/* 1210 */             multiplicacionEjecutado *= x3.getValorEjecutado();
/* 1211 */             contador++;
/*      */           } 
/* 1213 */           if (x4.getEstado().equals("A")) {
/* 1214 */             multiplicacionEjecutado *= x4.getValorEjecutado();
/* 1215 */             contador++;
/*      */           } 
/* 1217 */           if (x5.getEstado().equals("A")) {
/* 1218 */             multiplicacionEjecutado *= x5.getValorEjecutado();
/* 1219 */             contador++;
/*      */           } 
/* 1221 */           resultadoEjecutado = Math.pow(multiplicacionEjecutado, 1.0D / contador);
/* 1222 */         } catch (Exception e) {}
/*      */ 
/*      */         
/* 1225 */         contador = 0;
/*      */         try {
/* 1227 */           if (x1.getEstado().equals("A")) {
/* 1228 */             multiplicacionProgramado *= x1.getValorProgramado();
/* 1229 */             contador++;
/*      */           } 
/* 1231 */           if (x2.getEstado().equals("A")) {
/* 1232 */             multiplicacionProgramado *= x2.getValorProgramado();
/* 1233 */             contador++;
/*      */           } 
/* 1235 */           if (x3.getEstado().equals("A")) {
/* 1236 */             multiplicacionProgramado *= x3.getValorProgramado();
/* 1237 */             contador++;
/*      */           } 
/* 1239 */           if (x4.getEstado().equals("A")) {
/* 1240 */             multiplicacionProgramado *= x4.getValorProgramado();
/* 1241 */             contador++;
/*      */           } 
/* 1243 */           if (x5.getEstado().equals("A")) {
/* 1244 */             multiplicacionProgramado *= x5.getValorProgramado();
/* 1245 */             contador++;
/*      */           } 
/* 1247 */           resultadoProgramado = Math.pow(multiplicacionProgramado, 1.0D / contador);
/* 1248 */         } catch (Exception e) {}
/*      */ 
/*      */         
/* 1251 */         rs2 = new IndicadorResultadoDAO();
/* 1252 */         if (rs2.cargarRegistro(ciclo, indicador, mes) != null) {
/*      */           try {
/* 1254 */             rs2.modificarRegistro(ciclo, indicador, mes, resultadoProgramado, resultadoEjecutado, "A");
/* 1255 */           } catch (Exception e) {}
/*      */         } else {
/*      */ 
/*      */           
/*      */           try {
/* 1260 */             rs2.crearRegistro(ciclo, indicador, mes, resultadoProgramado, resultadoEjecutado, "A");
/* 1261 */           } catch (Exception e) {}
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1266 */         rs2.close();
/*      */         break;
/*      */       
/*      */       case 9:
/* 1270 */         a1 = daoCar.cargarRegistro(ciclo, indicador, mes, 21);
/* 1271 */         a2 = daoCar.cargarRegistro(ciclo, indicador, mes, 22);
/* 1272 */         a3 = daoCar.cargarRegistro(ciclo, indicador, mes, 23);
/* 1273 */         a4 = daoCar.cargarRegistro(ciclo, indicador, mes, 24);
/* 1274 */         a5 = daoCar.cargarRegistro(ciclo, indicador, mes, 25);
/* 1275 */         resultadoProgramado = 0.0D;
/* 1276 */         resultadoEjecutado = 0.0D;
/*      */         try {
/* 1278 */           if (a1.getEstado().equals("A")) {
/* 1279 */             resultadoEjecutado += a1.getValorEjecutado();
/*      */           }
/* 1281 */           if (a2.getEstado().equals("A")) {
/* 1282 */             resultadoEjecutado += a2.getValorEjecutado();
/*      */           }
/* 1284 */           if (a3.getEstado().equals("A")) {
/* 1285 */             resultadoEjecutado += a3.getValorEjecutado();
/*      */           }
/* 1287 */           if (a4.getEstado().equals("A")) {
/* 1288 */             resultadoEjecutado += a4.getValorEjecutado();
/*      */           }
/* 1290 */           if (a5.getEstado().equals("A")) {
/* 1291 */             resultadoEjecutado += a5.getValorEjecutado();
/*      */           }
/* 1293 */         } catch (Exception e) {}
/*      */ 
/*      */         
/* 1296 */         contador = 0;
/*      */         try {
/* 1298 */           if (a1.getEstado().equals("A")) {
/* 1299 */             resultadoProgramado += a1.getValorProgramado();
/*      */           }
/* 1301 */           if (a2.getEstado().equals("A")) {
/* 1302 */             resultadoProgramado += a2.getValorProgramado();
/*      */           }
/* 1304 */           if (a3.getEstado().equals("A")) {
/* 1305 */             resultadoProgramado += a3.getValorProgramado();
/*      */           }
/* 1307 */           if (a4.getEstado().equals("A")) {
/* 1308 */             resultadoProgramado += a4.getValorProgramado();
/*      */           }
/* 1310 */           if (a5.getEstado().equals("A")) {
/* 1311 */             resultadoProgramado += a5.getValorProgramado();
/*      */           }
/* 1313 */         } catch (Exception e) {}
/*      */ 
/*      */         
/* 1316 */         rs3 = new IndicadorResultadoDAO();
/* 1317 */         if (rs3.cargarRegistro(ciclo, indicador, mes) != null) {
/*      */           try {
/* 1319 */             rs3.modificarRegistro(ciclo, indicador, mes, resultadoProgramado, resultadoEjecutado, "A");
/* 1320 */           } catch (Exception e) {}
/*      */         } else {
/*      */ 
/*      */           
/*      */           try {
/* 1325 */             rs3.crearRegistro(ciclo, indicador, mes, resultadoProgramado, resultadoEjecutado, "A");
/* 1326 */           } catch (Exception e) {}
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1331 */         rs3.close();
/*      */         break;
/*      */       
/*      */       case 11:
/* 1335 */         ap1 = daoCar.cargarRegistro(ciclo, indicador, mes, 21);
/* 1336 */         ap2 = daoCar.cargarRegistro(ciclo, indicador, mes, 22);
/* 1337 */         ap3 = daoCar.cargarRegistro(ciclo, indicador, mes, 23);
/* 1338 */         ap4 = daoCar.cargarRegistro(ciclo, indicador, mes, 24);
/* 1339 */         ap5 = daoCar.cargarRegistro(ciclo, indicador, mes, 25);
/* 1340 */         alph1 = daoCar.cargarRegistro(ciclo, indicador, "NO", 26);
/* 1341 */         alph2 = daoCar.cargarRegistro(ciclo, indicador, "NO", 27);
/* 1342 */         alph3 = daoCar.cargarRegistro(ciclo, indicador, "NO", 28);
/* 1343 */         alph4 = daoCar.cargarRegistro(ciclo, indicador, "NO", 29);
/* 1344 */         alph5 = daoCar.cargarRegistro(ciclo, indicador, "NO", 30);
/* 1345 */         resultadoProgramado = 0.0D;
/* 1346 */         resultadoEjecutado = 0.0D;
/* 1347 */         promedioEjecutado = 0.0D;
/* 1348 */         promedioProgramado = 0.0D;
/*      */         try {
/* 1350 */           if (ap1.getEstado().equals("A")) {
/* 1351 */             promedioEjecutado += ap1.getValorEjecutado() * alph1.getValorProgramado();
/*      */           }
/* 1353 */           if (ap2.getEstado().equals("A")) {
/* 1354 */             promedioEjecutado += ap2.getValorEjecutado() * alph2.getValorProgramado();
/*      */           }
/* 1356 */           if (ap3.getEstado().equals("A")) {
/* 1357 */             promedioEjecutado += ap3.getValorEjecutado() * alph3.getValorProgramado();
/*      */           }
/* 1359 */           if (ap4.getEstado().equals("A")) {
/* 1360 */             promedioEjecutado += ap4.getValorEjecutado() * alph4.getValorProgramado();
/*      */           }
/* 1362 */           if (ap5.getEstado().equals("A")) {
/* 1363 */             promedioEjecutado += ap5.getValorEjecutado() * alph5.getValorProgramado();
/*      */           }
/* 1365 */           resultadoEjecutado = promedioEjecutado;
/* 1366 */         } catch (Exception e) {}
/*      */ 
/*      */         
/* 1369 */         contador = 0;
/*      */         try {
/* 1371 */           if (ap1.getEstado().equals("A")) {
/* 1372 */             promedioProgramado += ap1.getValorProgramado() * alph1.getValorProgramado();
/*      */           }
/* 1374 */           if (ap2.getEstado().equals("A")) {
/* 1375 */             promedioProgramado += ap2.getValorProgramado() * alph2.getValorProgramado();
/*      */           }
/* 1377 */           if (ap3.getEstado().equals("A")) {
/* 1378 */             promedioProgramado += ap3.getValorProgramado() * alph3.getValorProgramado();
/*      */           }
/* 1380 */           if (ap4.getEstado().equals("A")) {
/* 1381 */             promedioProgramado += ap4.getValorProgramado() * alph4.getValorProgramado();
/*      */           }
/* 1383 */           if (ap5.getEstado().equals("A")) {
/* 1384 */             promedioProgramado += ap5.getValorProgramado() * alph5.getValorProgramado();
/*      */           }
/* 1386 */           resultadoProgramado = promedioProgramado;
/* 1387 */         } catch (Exception e) {}
/*      */ 
/*      */         
/* 1390 */         rs4 = new IndicadorResultadoDAO();
/* 1391 */         if (rs4.cargarRegistro(ciclo, indicador, mes) != null) {
/*      */           try {
/* 1393 */             rs4.modificarRegistro(ciclo, indicador, mes, resultadoProgramado, resultadoEjecutado, "A");
/* 1394 */           } catch (Exception e) {}
/*      */         } else {
/*      */ 
/*      */           
/*      */           try {
/* 1399 */             rs4.crearRegistro(ciclo, indicador, mes, resultadoProgramado, resultadoEjecutado, "A");
/* 1400 */           } catch (Exception e) {}
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1405 */         rs4.close();
/*      */         break;
/*      */     }  }
/*      */ 
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\IndicadoresEfectividad.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */