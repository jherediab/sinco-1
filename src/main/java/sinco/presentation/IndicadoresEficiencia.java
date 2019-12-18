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
/*      */ import sinco.presentation.IndicadoresEficiencia;
/*      */ import sinco.presentation.IndicadoresEficienciaHTML;
/*      */ import sinco.spec.MenuDO;
/*      */ import sinco.spec.Varios;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class IndicadoresEficiencia
/*      */   implements HttpPresentation
/*      */ {
/*      */   private IndicadoresEficienciaHTML pagHTML;
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
/*   73 */     this.pagHTML = (IndicadoresEficienciaHTML)comms.xmlcFactory.create(IndicadoresEficienciaHTML.class);
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
/*   88 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*   89 */     comms.response.writeDOM(this.pagHTML);
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
/*  109 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*      */     
/*  111 */     Varios oVarios = new Varios();
/*  112 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Ind_indicadoresEficienciaAct");
/*  113 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Ind_indicadoresEficienciaDel");
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
/*  135 */     if (!vista.equals("tablero")) {
/*  136 */       HTMLElement sel = this.pagHTML.getElementDivTableroControl();
/*  137 */       sel.getParentNode().removeChild(sel);
/*      */     } 
/*  139 */     if (!vista.equals("consulta")) {
/*  140 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/*  141 */       sel.getParentNode().removeChild(sel);
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
/*  155 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/*  156 */     atrib.setValue(valor);
/*  157 */     return atrib;
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
/*  170 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  171 */     Element enlace = this.pagHTML.createElement("a");
/*  172 */     Node hijo = this.pagHTML.createTextNode(contenido);
/*  173 */     enlace.appendChild(hijo);
/*  174 */     Attr donde = this.pagHTML.createAttribute("href");
/*  175 */     donde.setValue(vinculo);
/*  176 */     enlace.setAttributeNode(donde);
/*  177 */     td.appendChild(enlace);
/*  178 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  179 */     return td;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private HTMLElement newtd(String contenido) {
/*  189 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  190 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  191 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  192 */     return td;
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
/*  203 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  204 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  205 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  206 */     td.setAttributeNode(newAttr("colspan", "" + colspan));
/*  207 */     return td;
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
/*  218 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("th");
/*  219 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  220 */     td.setAttributeNode(newAttr("class", "" + clase));
/*  221 */     td.setAttributeNode(newAttr("colspan", "" + colspan));
/*  222 */     return td;
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
/*  234 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  235 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  236 */     td.setAttributeNode(newAttr("bgcolor", "#" + color));
/*  237 */     return td;
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
/*  248 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  249 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  250 */     td.setAttributeNode(newAttr("bgcolor", "#" + color));
/*  251 */     td.setAttributeNode(newAttr("colspan", "" + colspan));
/*  252 */     return td;
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
/*  263 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  264 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  265 */     td.setAttributeNode(newAttr("class", "ca"));
/*      */     
/*  267 */     return td;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void consulta(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  273 */     activarVista("consulta");
/*      */ 
/*      */     
/*  276 */     String c = " tipo_indicador='2'";
/*  277 */     HTMLSelectElement combo = this.pagHTML.getElementFindicador();
/*  278 */     llenarCombo(combo, "INDICADORES", "CODIGO_INDICADOR", "NOMBRE_INDICADOR", c, "", false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void mostrarTablero(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  287 */     activarVista("tablero");
/*      */     
/*  289 */     String indicador = "";
/*      */     try {
/*  291 */       indicador = comms.request.getParameter("findicador");
/*      */     }
/*  293 */     catch (Exception e) {
/*  294 */       String sPagina = "IndicadoresEfectividad.po?_operacion=X";
/*  295 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*      */     } 
/*      */     
/*  298 */     IndicadoresDAO res = new IndicadoresDAO();
/*  299 */     IndicadoresDTO ind = res.cargarRegistro(comms.request.getParameter("findicador"));
/*  300 */     String cod = ind.getCodigoIndicador();
/*      */ 
/*      */ 
/*      */     
/*  304 */     this.pagHTML.setTextMetaTotalProgramadaEd("" + ind.getMetaTotalProgramada());
/*  305 */     this.pagHTML.setTextMetaEstaVigenciaEd("" + ind.getMetaEstaVigencia());
/*  306 */     this.pagHTML.setTextAcumuladoVigenciasEd("" + ind.getAcumuladoVigencias());
/*  307 */     this.pagHTML.setTextAcumuladoEstaVigenciaEd("" + ind.getAcumuladoEstaVigencia());
/*  308 */     this.pagHTML.setTextLogroTotalEd("" + ind.getLogroTotal());
/*  309 */     this.pagHTML.setTextLogroTotalAcumuladoEd("" + ind.getLogroAcumuladoTotal());
/*      */ 
/*      */     
/*  312 */     this.pagHTML.setTextCodigoIndicadorEd("" + ind.getCodigoIndicador());
/*  313 */     this.pagHTML.setTextNombreIndicadorEd("" + ind.getNombreIndicador());
/*  314 */     this.pagHTML.setTextProcesoEd("" + ind.getNombreProceso());
/*  315 */     this.pagHTML.setTextCicloEd("" + ind.getNombreCiclo());
/*  316 */     this.pagHTML.setTextAreaEd("" + ind.getNombreArea());
/*  317 */     this.pagHTML.setTextObjetivoEstrategicoEd("" + ind.getNombreObjetivoEstrategico());
/*  318 */     this.pagHTML.setTextIndicadorAcuerdoEd("" + ind.getIndicadorAcuerdo());
/*  319 */     this.pagHTML.setTextTipoIndicadorEd("" + ind.getNombreTipoIndicador());
/*  320 */     this.pagHTML.setTextProyectoInversionEd("" + ind.getNombreProyectoInversion());
/*  321 */     this.pagHTML.setTextMetaPlanDeDesarrolloEd("" + ind.getNombreMetaPlanDeDesarrollo());
/*  322 */     this.pagHTML.setTextMetaProyectoEd("" + ind.getNombreMetaProyecto());
/*  323 */     this.pagHTML.setTextObjetivoIndicadorEd("" + ind.getObjetivoIndicador());
/*      */     
/*  325 */     this.pagHTML.setTextUnidadMedidaEd("" + ind.getNombreUnidadMedida());
/*  326 */     this.pagHTML.setTextFrecuenciaMedicionEd("" + ind.getNombreFrecuenciaMedicion());
/*  327 */     this.pagHTML.setTextFormulaEd("" + ind.getFormula());
/*      */ 
/*      */ 
/*      */     
/*  331 */     IndicadorActividadesDAO rsAct = new IndicadorActividadesDAO();
/*  332 */     Collection<IndicadorActividadesDTO> actividades = rsAct.cargarTodos(indicador);
/*  333 */     rsAct.close();
/*      */ 
/*      */     
/*  336 */     Iterator<IndicadorActividadesDTO> iteActividades = actividades.iterator();
/*      */     
/*  338 */     int ejecucion = 0;
/*      */     
/*      */     try {
/*  341 */       IndicadorActividadesDTO actividad = (IndicadorActividadesDTO)iteActividades.next();
/*      */ 
/*      */ 
/*      */       
/*  345 */       int esperado1 = ind.getValormes1();
/*  346 */       int esperado2 = ind.getValormes2();
/*  347 */       int esperado3 = ind.getValormes3();
/*  348 */       int esperado4 = ind.getValormes4();
/*  349 */       int esperado5 = ind.getValormes5();
/*  350 */       int esperado6 = ind.getValormes6();
/*  351 */       int esperado7 = ind.getValormes7();
/*  352 */       int esperado8 = ind.getValormes8();
/*  353 */       int esperado9 = ind.getValormes9();
/*  354 */       int esperado10 = ind.getValormes10();
/*  355 */       int esperado11 = ind.getValormes11();
/*  356 */       int esperado12 = ind.getValormes12();
/*      */       
/*  358 */       int valorReal1 = valorReal(actividad.getIdIndicador(), "Enero");
/*  359 */       int valorReal2 = valorReal(actividad.getIdIndicador(), "Febrero");
/*  360 */       int valorReal3 = valorReal(actividad.getIdIndicador(), "Marzo");
/*  361 */       int valorReal4 = valorReal(actividad.getIdIndicador(), "Abril");
/*  362 */       int valorReal5 = valorReal(actividad.getIdIndicador(), "Mayo");
/*  363 */       int valorReal6 = valorReal(actividad.getIdIndicador(), "Junio");
/*  364 */       int valorReal7 = valorReal(actividad.getIdIndicador(), "Julio");
/*  365 */       int valorReal8 = valorReal(actividad.getIdIndicador(), "Agosto");
/*  366 */       int valorReal9 = valorReal(actividad.getIdIndicador(), "Septiembre");
/*  367 */       int valorReal10 = valorReal(actividad.getIdIndicador(), "Octubre");
/*  368 */       int valorReal11 = valorReal(actividad.getIdIndicador(), "Noviembre");
/*  369 */       int valorReal12 = valorReal(actividad.getIdIndicador(), "Diciembre");
/*      */ 
/*      */       
/*      */       try {
/*  373 */         ejecucion = valorReal1 * 100 / esperado1;
/*      */       }
/*  375 */       catch (Exception e) {
/*  376 */         ejecucion = 0;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  382 */         ejecucion = valorReal2 * 100 / esperado2;
/*  383 */       } catch (Exception e) {
/*  384 */         ejecucion = 0;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  389 */         ejecucion = valorReal3 * 100 / esperado3;
/*  390 */       } catch (Exception e) {
/*  391 */         ejecucion = 0;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  396 */         ejecucion = valorReal4 * 100 / esperado4;
/*  397 */       } catch (Exception e) {
/*  398 */         ejecucion = 0;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  403 */         ejecucion = valorReal5 * 100 / esperado5;
/*  404 */       } catch (Exception e) {
/*  405 */         ejecucion = 0;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  410 */         ejecucion = valorReal6 * 100 / esperado6;
/*  411 */       } catch (Exception e) {
/*  412 */         ejecucion = 0;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  417 */         ejecucion = valorReal7 * 100 / esperado7;
/*  418 */       } catch (Exception e) {
/*  419 */         ejecucion = 0;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  424 */         ejecucion = valorReal8 * 100 / esperado8;
/*  425 */       } catch (Exception e) {
/*  426 */         ejecucion = 0;
/*      */       } 
/*      */       
/*      */       try {
/*  430 */         ejecucion = valorReal9 * 100 / esperado9;
/*  431 */       } catch (Exception e) {
/*  432 */         ejecucion = 0;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  437 */         ejecucion = valorReal10 * 100 / esperado10;
/*  438 */       } catch (Exception e) {
/*  439 */         ejecucion = 0;
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  444 */         ejecucion = valorReal11 * 100 / esperado11;
/*  445 */       } catch (Exception e) {
/*  446 */         ejecucion = 0;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  454 */         ejecucion = valorReal12 * 100 / esperado12;
/*      */       }
/*  456 */       catch (Exception e) {
/*  457 */         ejecucion = 0;
/*      */       }
/*      */     
/*  460 */     } catch (Exception e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  467 */     caracteristicas(Integer.parseInt(ind.getFormula()), comms, ind);
/*      */     
/*  469 */     HTMLImageElement gra = this.pagHTML.getElementGrafica();
/*  470 */     gra.setSrc("IndicadoresEfectividad.po?indicador=" + cod + "&_operacion=GR1");
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
/*  484 */     String indicador = comms.request.getParameter("indicador");
/*      */ 
/*      */ 
/*      */     
/*  488 */     IndicadoresDAO res = new IndicadoresDAO();
/*  489 */     IndicadoresDTO ind = res.cargarRegistro(indicador);
/*      */     
/*  491 */     IndicadorActividadesDAO rsAct = new IndicadorActividadesDAO();
/*  492 */     Collection<IndicadorActividadesDTO> actividades = rsAct.cargarTodos(indicador);
/*  493 */     rsAct.close();
/*      */ 
/*      */     
/*  496 */     Iterator<IndicadorActividadesDTO> iteActividades = actividades.iterator();
/*  497 */     IndicadorActividadesDTO actividad = (IndicadorActividadesDTO)iteActividades.next();
/*      */ 
/*      */     
/*  500 */     int esperado1 = ind.getValormes1();
/*  501 */     int esperado2 = ind.getValormes2();
/*  502 */     int esperado3 = ind.getValormes3();
/*  503 */     int esperado4 = ind.getValormes4();
/*  504 */     int esperado5 = ind.getValormes5();
/*  505 */     int esperado6 = ind.getValormes6();
/*  506 */     int esperado7 = ind.getValormes7();
/*  507 */     int esperado8 = ind.getValormes8();
/*  508 */     int esperado9 = ind.getValormes9();
/*  509 */     int esperado10 = ind.getValormes10();
/*  510 */     int esperado11 = ind.getValormes11();
/*  511 */     int esperado12 = ind.getValormes12();
/*      */     
/*  513 */     int valorReal1 = valorReal(actividad.getIdIndicador(), "Enero");
/*  514 */     int valorReal2 = valorReal(actividad.getIdIndicador(), "Febrero");
/*  515 */     int valorReal3 = valorReal(actividad.getIdIndicador(), "Marzo");
/*  516 */     int valorReal4 = valorReal(actividad.getIdIndicador(), "Abril");
/*  517 */     int valorReal5 = valorReal(actividad.getIdIndicador(), "Mayo");
/*  518 */     int valorReal6 = valorReal(actividad.getIdIndicador(), "Junio");
/*  519 */     int valorReal7 = valorReal(actividad.getIdIndicador(), "Julio");
/*  520 */     int valorReal8 = valorReal(actividad.getIdIndicador(), "Agosto");
/*  521 */     int valorReal9 = valorReal(actividad.getIdIndicador(), "Septiembre");
/*  522 */     int valorReal10 = valorReal(actividad.getIdIndicador(), "Octubre");
/*  523 */     int valorReal11 = valorReal(actividad.getIdIndicador(), "Noviembre");
/*  524 */     int valorReal12 = valorReal(actividad.getIdIndicador(), "Diciembre");
/*      */     
/*  526 */     String frecuencia = ind.getFrecuenciaMedicion();
/*  527 */     DefaultCategoryDataset objDatos = new DefaultCategoryDataset();
/*      */     
/*  529 */     Utilidades.grabarLog(frecuencia);
/*      */ 
/*      */     
/*  532 */     if (frecuencia.equals("1")) {
/*      */       
/*  534 */       objDatos.addValue(esperado1, "esperado", "Enero");
/*  535 */       objDatos.addValue(esperado2, "esperado", "Febrero");
/*  536 */       objDatos.addValue(esperado3, "esperado", "Marzo");
/*  537 */       objDatos.addValue(esperado4, "esperado", "Abril");
/*  538 */       objDatos.addValue(esperado5, "esperado", "Mayo");
/*  539 */       objDatos.addValue(esperado6, "esperado", "Junio");
/*  540 */       objDatos.addValue(esperado7, "esperado", "Julio");
/*  541 */       objDatos.addValue(esperado8, "esperado", "Agosto");
/*  542 */       objDatos.addValue(esperado9, "esperado", "Septiembre");
/*  543 */       objDatos.addValue(esperado10, "esperado", "Ooctubre");
/*  544 */       objDatos.addValue(esperado11, "esperado", "Noviembre");
/*  545 */       objDatos.addValue(esperado12, "esperado", "Diciembre");
/*      */ 
/*      */       
/*  548 */       objDatos.addValue(valorReal1, "Real", "Enero");
/*  549 */       objDatos.addValue(valorReal2, "Real", "Febrero");
/*  550 */       objDatos.addValue(valorReal3, "Real", "Marzo");
/*  551 */       objDatos.addValue(valorReal4, "Real", "Abril");
/*  552 */       objDatos.addValue(valorReal5, "Real", "Mayo");
/*  553 */       objDatos.addValue(valorReal6, "Real", "Junio");
/*  554 */       objDatos.addValue(valorReal7, "Real", "Julio");
/*  555 */       objDatos.addValue(valorReal8, "Real", "Agosto");
/*  556 */       objDatos.addValue(valorReal9, "Real", "Septiembre");
/*  557 */       objDatos.addValue(valorReal10, "Real", "Octubre");
/*  558 */       objDatos.addValue(valorReal11, "Real", "Noviembre");
/*  559 */       objDatos.addValue(valorReal12, "Real", "Diciembre");
/*      */     } 
/*      */     
/*  562 */     if (frecuencia.equals("2")) {
/*      */       
/*  564 */       objDatos.addValue(esperado2, "esperado", "Febrero");
/*  565 */       objDatos.addValue(esperado4, "esperado", "Abril");
/*  566 */       objDatos.addValue(esperado6, "esperado", "Junio");
/*  567 */       objDatos.addValue(esperado8, "esperado", "Agosto");
/*  568 */       objDatos.addValue(esperado10, "esperado", "Ooctubre");
/*  569 */       objDatos.addValue(esperado12, "esperado", "Diciembre");
/*      */       
/*  571 */       objDatos.addValue(valorReal2, "Real", "Febrero");
/*  572 */       objDatos.addValue(valorReal4, "Real", "Abril");
/*  573 */       objDatos.addValue(valorReal6, "Real", "Junio");
/*  574 */       objDatos.addValue(valorReal8, "Real", "Agosto");
/*  575 */       objDatos.addValue(valorReal10, "Real", "Octubre");
/*  576 */       objDatos.addValue(valorReal12, "Real", "Diciembre");
/*      */     } 
/*      */ 
/*      */     
/*  580 */     if (frecuencia.equals("3")) {
/*      */ 
/*      */       
/*  583 */       objDatos.addValue(esperado3, "esperado", "Marzo");
/*  584 */       objDatos.addValue(esperado6, "esperado", "Junio");
/*  585 */       objDatos.addValue(esperado9, "esperado", "Septiembre");
/*  586 */       objDatos.addValue(esperado12, "esperado", "Diciembre");
/*      */ 
/*      */       
/*  589 */       objDatos.addValue(valorReal3, "Real", "Marzo");
/*  590 */       objDatos.addValue(valorReal6, "Real", "Junio");
/*  591 */       objDatos.addValue(valorReal9, "Real", "Septiembre");
/*  592 */       objDatos.addValue(valorReal12, "Real", "Diciembre");
/*      */     } 
/*  594 */     if (frecuencia.equals("4")) {
/*      */       
/*  596 */       objDatos.addValue(esperado4, "esperado", "Abril");
/*  597 */       objDatos.addValue(esperado8, "esperado", "Agosto");
/*  598 */       objDatos.addValue(esperado12, "esperado", "Diciembre");
/*      */ 
/*      */       
/*  601 */       objDatos.addValue(valorReal4, "Real", "Abril");
/*  602 */       objDatos.addValue(valorReal8, "Real", "Agosto");
/*  603 */       objDatos.addValue(valorReal12, "Real", "Diciembre");
/*      */     } 
/*  605 */     if (frecuencia.equals("5")) {
/*      */       
/*  607 */       objDatos.addValue(esperado6, "esperado", "Junio");
/*  608 */       objDatos.addValue(esperado12, "esperado", "Diciembre");
/*      */ 
/*      */       
/*  611 */       objDatos.addValue(valorReal6, "Real", "Junio");
/*  612 */       objDatos.addValue(valorReal12, "Real", "Diciembre");
/*      */     } 
/*      */     
/*  615 */     if (frecuencia.equals("6")) {
/*      */       
/*  617 */       objDatos.addValue(esperado12, "esperado", "Diciembre");
/*      */       
/*  619 */       objDatos.addValue(valorReal12, "Real", "Diciembre");
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  624 */     SpiderWebPlot spiderwebplot = new SpiderWebPlot(objDatos);
/*      */     
/*  626 */     JFreeChart jfreechart = new JFreeChart("Esperado vs Ejecutado", TextTitle.DEFAULT_FONT, spiderwebplot, false);
/*  627 */     LegendTitle legendtitle = new LegendTitle(spiderwebplot);
/*  628 */     legendtitle.setPosition(RectangleEdge.BOTTOM);
/*  629 */     jfreechart.addSubtitle(legendtitle);
/*      */ 
/*      */     
/*      */     try {
/*  633 */       ChartUtilities.writeChartAsJPEG(comms.response.getOutputStream(), jfreechart, 700, 350);
/*      */     }
/*  635 */     catch (Exception e) {}
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
/*      */ 
/*      */   
/*      */   private int totalEsperado(PoaMaestroActividadesDTO reg, int mes) {
/*  869 */     int totalEsperado = 0;
/*  870 */     switch (mes) {
/*      */       case 12:
/*  872 */         totalEsperado += reg.getValorMes12();
/*      */       case 11:
/*  874 */         totalEsperado += reg.getValorMes11();
/*      */       case 10:
/*  876 */         totalEsperado += reg.getValorMes10();
/*      */       case 9:
/*  878 */         totalEsperado += reg.getValorMes9();
/*      */       case 8:
/*  880 */         totalEsperado += reg.getValorMes8();
/*      */       case 7:
/*  882 */         totalEsperado += reg.getValorMes7();
/*      */       case 6:
/*  884 */         totalEsperado += reg.getValorMes6();
/*      */       case 5:
/*  886 */         totalEsperado += reg.getValorMes5();
/*      */       case 4:
/*  888 */         totalEsperado += reg.getValorMes4();
/*      */       case 3:
/*  890 */         totalEsperado += reg.getValorMes3();
/*      */       case 2:
/*  892 */         totalEsperado += reg.getValorMes2();
/*      */       case 1:
/*  894 */         totalEsperado += reg.getValorMes1();
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/*  899 */     return totalEsperado;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private String color(int valor) {
/*  905 */     PoaTableroColoresDAO rs = new PoaTableroColoresDAO();
/*  906 */     Collection<PoaTableroColoresDTO> colores = rs.cargarTodos("");
/*  907 */     rs.close();
/*  908 */     Iterator<PoaTableroColoresDTO> iterator = colores.iterator();
/*  909 */     while (iterator.hasNext()) {
/*  910 */       PoaTableroColoresDTO color = (PoaTableroColoresDTO)iterator.next();
/*  911 */       if (valor <= color.getValorFinal() && valor >= color.getValorInicial()) {
/*  912 */         return color.getColor();
/*      */       }
/*      */     } 
/*  915 */     return "FFFFFF";
/*      */   }
/*      */   
/*      */   private Element crearEnlace(String contenido, String link) {
/*  919 */     Element enlace = this.pagHTML.createElement("a");
/*  920 */     Node hijo = this.pagHTML.createTextNode(contenido);
/*  921 */     Attr donde = this.pagHTML.createAttribute("href");
/*  922 */     donde.setValue(link);
/*  923 */     enlace.setAttributeNode(donde);
/*  924 */     enlace.appendChild(hijo);
/*  925 */     return enlace;
/*      */   }
/*      */   
/*      */   private String urlLogro(String indicador, String mes) {
/*      */     try {
/*  930 */       IndicadorActividadesDAO rs = new IndicadorActividadesDAO();
/*  931 */       IndicadorActividadesDTO reg = rs.cargarRegistro(indicador, mes);
/*  932 */       rs.close();
/*      */       
/*  934 */       return "IndicadorActividades.po?_operacion=V&idIndicador=" + reg.getIdIndicador() + "&mes=" + mes;
/*  935 */     } catch (Exception e) {
/*      */ 
/*      */       
/*  938 */       return "#";
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
/*      */   private void caracteristicas(int codigo, HttpPresentationComms comms, IndicadoresDTO indicador) throws HttpPresentationException, KeywordValueException {
/*  954 */     CaracteristicasValorDAO rsTGen = new CaracteristicasValorDAO();
/*      */     
/*  956 */     HTMLTableElement tabla = this.pagHTML.getElementTablaVariables();
/*      */     
/*  958 */     CaracteristicasDAO rsCaracteristicas = new CaracteristicasDAO();
/*  959 */     Collection arr = rsCaracteristicas.cargarTodosParaServicio(codigo, "C", "A");
/*  960 */     rsCaracteristicas.close();
/*      */     
/*  962 */     int solPadre = 0;
/*      */     try {
/*  964 */       solPadre = Integer.parseInt(comms.request.getParameter("solPadre"));
/*      */       
/*  966 */       comms.session.getSessionData().set("volverA", "" + solPadre);
/*      */     
/*      */     }
/*  969 */     catch (Exception e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  974 */     boolean extender = false;
/*      */ 
/*      */     
/*  977 */     boolean fondo = true;
/*  978 */     DetalleSolicitudDAO dsf = new DetalleSolicitudDAO();
/*  979 */     int capturas = 0;
/*  980 */     String cadenaFechas = "";
/*      */     
/*  982 */     Iterator iterator = arr.iterator();
/*  983 */     Collection arrAnidadas = new ArrayList();
/*  984 */     Collection arrDependen = new ArrayList();
/*  985 */     HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  986 */     eltr.appendChild(newtdTitle("Mes", "cf1"));
/*  987 */     while (iterator.hasNext()) {
/*  988 */       CaracteristicasDTO regCar = (CaracteristicasDTO)iterator.next();
/*  989 */       IndicadorCaracteristicaDAO icrs = new IndicadorCaracteristicaDAO();
/*      */       
/*  991 */       HTMLElement tdCaracteristica = (HTMLElement)this.pagHTML.createElement("th");
/*      */ 
/*      */ 
/*      */       
/*  995 */       fondo = !fondo;
/*  996 */       eltr.setAttributeNode(newAttr("class", "car"));
/*  997 */       IndicadorCaracteristicaDTO car = icrs.cargarRegistro(indicador.getCiclo(), indicador.getCodigoIndicador(), "", regCar.getCodigo());
/*  998 */       if (car.getEstado().equals("A") && regCar.getCalificar().equals("N")) {
/*  999 */         eltr.setAttributeNode(newAttr("codigo", "" + regCar.getCodigo()));
/*      */         
/* 1001 */         tdCaracteristica = newtdTitle(regCar.getDescripcion(), "cf1");
/* 1002 */         eltr.appendChild(tdCaracteristica);
/* 1003 */         if (indicador.getFormula().equals("9")) {
/* 1004 */           tdCaracteristica = newtdTitle(regCar.getDescripcion() + " %", "cf1");
/* 1005 */           eltr.appendChild(tdCaracteristica);
/*      */         } 
/*      */       } 
/* 1008 */       icrs.close();
/*      */     } 
/* 1010 */     if (!indicador.getFormula().equals("9")) {
/* 1011 */       eltr.appendChild(newtdTitle("Programado", "cf1"));
/* 1012 */       eltr.appendChild(newtdTitle("Ejecutado", "cf1"));
/* 1013 */       eltr.appendChild(newtdTitle("Cumplimiento", "cf1"));
/*      */     } 
/* 1015 */     tabla.appendChild(eltr);
/*      */ 
/*      */ 
/*      */     
/* 1019 */     for (int i = 0; i < 12; i++) {
/* 1020 */       iterator = arr.iterator();
/* 1021 */       eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 1022 */       eltr.appendChild(newtd(getMes(i + 1)));
/* 1023 */       IndicadorCaracteristicaDAO icrs = new IndicadorCaracteristicaDAO();
/* 1024 */       Collection<IndicadorCaracteristicaDTO> caracteristicas = icrs.cargarTodos(indicador.getCiclo(), indicador.getCodigoIndicador(), getMes(i + 1));
/* 1025 */       Iterator<IndicadorCaracteristicaDTO> iterator1 = caracteristicas.iterator();
/* 1026 */       int formula = Integer.parseInt(indicador.getFormula());
/* 1027 */       calcularFormula(icrs, formula, indicador.getCiclo(), indicador.getCodigoIndicador(), getMes(i + 1));
/* 1028 */       IndicadorResultadoDAO ird = new IndicadorResultadoDAO();
/* 1029 */       while (iterator1.hasNext()) {
/* 1030 */         IndicadorCaracteristicaDTO car = (IndicadorCaracteristicaDTO)iterator1.next();
/* 1031 */         String url = "IndicadorCaracteristica.po?_operacion=V&idRegistro=" + car.getIdRegistro();
/* 1032 */         eltr.appendChild(newtdhref("" + car.getValorEjecutado(), url));
/* 1033 */         if (indicador.getFormula().equals("9")) {
/*      */           try {
/* 1035 */             IndicadorResultadoDTO resultado = ird.cargarRegistro(indicador.getCiclo(), indicador.getCodigoIndicador(), getMes(i + 1));
/* 1036 */             DecimalFormat decimales = new DecimalFormat("0.0000");
/* 1037 */             if (resultado.getValorEjecutado() > 0.0D) {
/* 1038 */               double result = car.getValorEjecutado() / resultado.getValorEjecutado();
/* 1039 */               eltr.appendChild(newtdColor(decimales.format(result), color(result))); continue;
/*      */             } 
/* 1041 */             eltr.appendChild(newtd("N.A"));
/*      */           }
/* 1043 */           catch (Exception e) {
/* 1044 */             eltr.appendChild(newtd("N.A"));
/*      */           } 
/*      */         }
/*      */       } 
/* 1048 */       if (!indicador.getFormula().equals("9")) {
/*      */         try {
/* 1050 */           IndicadorResultadoDTO resultado = ird.cargarRegistro(indicador.getCiclo(), indicador.getCodigoIndicador(), getMes(i + 1));
/* 1051 */           eltr.appendChild(newtd("" + resultado.getValorProgramado()));
/* 1052 */           eltr.appendChild(newtd("" + resultado.getValorEjecutado()));
/* 1053 */           if (resultado.getValorEjecutado() > 0.0D) {
/* 1054 */             DecimalFormat decimales = new DecimalFormat("0.0000");
/* 1055 */             double result = resultado.getValorProgramado() / resultado.getValorEjecutado();
/* 1056 */             eltr.appendChild(newtdColor(decimales.format(result), color(result)));
/*      */           } else {
/* 1058 */             eltr.appendChild(newtd("N.A"));
/*      */           } 
/* 1060 */         } catch (Exception e) {}
/*      */       }
/*      */       
/* 1063 */       ird.close();
/* 1064 */       icrs.close();
/* 1065 */       tabla.appendChild(eltr);
/*      */     } 
/* 1067 */     dsf.close();
/* 1068 */     rsTGen.close();
/* 1069 */     arr.clear();
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
/* 1080 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 1081 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 1082 */     td.setAttributeNode(newAttr("class", clase));
/* 1083 */     return td; } private void calcularFormula(IndicadorCaracteristicaDAO daoCar, int formula, int ciclo, String indicador, String mes) { IndicadorResultadoDAO rs4; double promedioProgramado, promedioEjecutado; IndicadorCaracteristicaDTO alph5, alph4, alph3, alph2, alph1, ap5, ap4, ap3, ap2, ap1; IndicadorResultadoDAO rs3; IndicadorCaracteristicaDTO a5, a4, a3, a2, a1; IndicadorResultadoDAO rs2; int contador; double multiplicacionProgramado, multiplicacionEjecutado; IndicadorCaracteristicaDTO x5, x4, x3, x2, x1; IndicadorResultadoDAO rs1; IndicadorCaracteristicaDTO ejecutado;
/*      */     IndicadorResultadoDAO rs;
/*      */     IndicadorCaracteristicaDTO v2, v1;
/*      */     IndicadorResultadoDAO rs0;
/*      */     IndicadorCaracteristicaDTO pr, pg, pe, er, eg, ee;
/* 1088 */     double resultadoEjecutado = 0.0D;
/* 1089 */     double resultadoProgramado = 0.0D;
/*      */     
/* 1091 */     switch (formula) {
/*      */       case 5:
/* 1093 */         ee = daoCar.cargarRegistro(ciclo, indicador, mes, 5);
/* 1094 */         eg = daoCar.cargarRegistro(ciclo, indicador, mes, 6);
/* 1095 */         er = daoCar.cargarRegistro(ciclo, indicador, mes, 7);
/* 1096 */         pe = daoCar.cargarRegistro(ciclo, indicador, "NO", 17);
/* 1097 */         pg = daoCar.cargarRegistro(ciclo, indicador, "NO", 18);
/* 1098 */         pr = daoCar.cargarRegistro(ciclo, indicador, "NO", 19);
/* 1099 */         resultadoEjecutado = 0.0D;
/* 1100 */         resultadoProgramado = 0.0D;
/*      */         try {
/* 1102 */           resultadoEjecutado = (ee.getValorEjecutado() * pe.getValorProgramado()) + (eg.getValorEjecutado() * pg.getValorProgramado()) + (er.getValorEjecutado() * pr.getValorProgramado());
/*      */         
/*      */         }
/* 1105 */         catch (Exception ex) {}
/*      */ 
/*      */         
/*      */         try {
/* 1109 */           resultadoProgramado = (ee.getValorProgramado() * pe.getValorProgramado()) + (eg.getValorProgramado() * pg.getValorProgramado()) + (er.getValorProgramado() * pr.getValorProgramado());
/*      */         
/*      */         }
/* 1112 */         catch (Exception ex) {}
/*      */ 
/*      */         
/* 1115 */         rs0 = new IndicadorResultadoDAO();
/* 1116 */         if (rs0.cargarRegistro(ciclo, indicador, mes) != null) {
/*      */           try {
/* 1118 */             rs0.modificarRegistro(ciclo, indicador, mes, resultadoProgramado, resultadoEjecutado, "A");
/* 1119 */           } catch (Exception ex) {}
/*      */         } else {
/*      */ 
/*      */           
/*      */           try {
/* 1124 */             rs0.crearRegistro(ciclo, indicador, mes, resultadoProgramado, resultadoEjecutado, "A");
/* 1125 */           } catch (Exception ex) {}
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1130 */         rs0.close();
/*      */         break;
/*      */       case 6:
/* 1133 */         v1 = daoCar.cargarRegistro(ciclo, indicador, mes, 8);
/* 1134 */         v2 = daoCar.cargarRegistro(ciclo, indicador, mes, 9);
/* 1135 */         resultadoEjecutado = 0.0D;
/* 1136 */         resultadoProgramado = 0.0D;
/*      */         try {
/* 1138 */           resultadoEjecutado = (v1.getValorEjecutado() - v2.getValorEjecutado()) / v2.getValorEjecutado();
/* 1139 */         } catch (Exception e) {}
/*      */ 
/*      */         
/*      */         try {
/* 1143 */           resultadoProgramado = (v1.getValorProgramado() - v2.getValorProgramado()) / v2.getValorProgramado();
/* 1144 */         } catch (Exception e) {}
/*      */ 
/*      */         
/* 1147 */         rs = new IndicadorResultadoDAO();
/* 1148 */         if (rs.cargarRegistro(ciclo, indicador, mes) != null) {
/*      */           try {
/* 1150 */             rs.modificarRegistro(ciclo, indicador, mes, resultadoProgramado, resultadoEjecutado, "A");
/* 1151 */           } catch (Exception e) {}
/*      */         } else {
/*      */ 
/*      */           
/*      */           try {
/* 1156 */             rs.crearRegistro(ciclo, indicador, mes, resultadoProgramado, resultadoEjecutado, "A");
/* 1157 */           } catch (Exception e) {}
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1162 */         rs.close();
/*      */         break;
/*      */       case 7:
/* 1165 */         ejecutado = daoCar.cargarRegistro(ciclo, indicador, mes, 10);
/* 1166 */         resultadoProgramado = 0.0D;
/* 1167 */         resultadoEjecutado = 0.0D;
/*      */         try {
/* 1169 */           resultadoProgramado = ejecutado.getValorProgramado();
/* 1170 */           resultadoEjecutado = ejecutado.getValorEjecutado() / ejecutado.getValorProgramado() * 100.0D;
/* 1171 */         } catch (Exception e) {}
/*      */ 
/*      */         
/* 1174 */         rs1 = new IndicadorResultadoDAO();
/* 1175 */         if (rs1.cargarRegistro(ciclo, indicador, mes) != null) {
/*      */           try {
/* 1177 */             rs1.modificarRegistro(ciclo, indicador, mes, resultadoProgramado, resultadoEjecutado, "A");
/* 1178 */           } catch (Exception e) {}
/*      */         } else {
/*      */ 
/*      */           
/*      */           try {
/* 1183 */             rs1.crearRegistro(ciclo, indicador, mes, resultadoProgramado, resultadoEjecutado, "A");
/* 1184 */           } catch (Exception e) {}
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1189 */         rs1.close();
/*      */         break;
/*      */       
/*      */       case 8:
/* 1193 */         x1 = daoCar.cargarRegistro(ciclo, indicador, mes, 12);
/* 1194 */         x2 = daoCar.cargarRegistro(ciclo, indicador, mes, 13);
/* 1195 */         x3 = daoCar.cargarRegistro(ciclo, indicador, mes, 14);
/* 1196 */         x4 = daoCar.cargarRegistro(ciclo, indicador, mes, 15);
/* 1197 */         x5 = daoCar.cargarRegistro(ciclo, indicador, mes, 16);
/* 1198 */         resultadoProgramado = 0.0D;
/* 1199 */         resultadoEjecutado = 0.0D;
/* 1200 */         multiplicacionEjecutado = 1.0D;
/* 1201 */         multiplicacionProgramado = 1.0D;
/* 1202 */         contador = 0;
/*      */         try {
/* 1204 */           if (x1.getEstado().equals("A")) {
/* 1205 */             multiplicacionEjecutado *= x1.getValorEjecutado();
/* 1206 */             contador++;
/*      */           } 
/* 1208 */           if (x2.getEstado().equals("A")) {
/* 1209 */             multiplicacionEjecutado *= x2.getValorEjecutado();
/* 1210 */             contador++;
/*      */           } 
/* 1212 */           if (x3.getEstado().equals("A")) {
/* 1213 */             multiplicacionEjecutado *= x3.getValorEjecutado();
/* 1214 */             contador++;
/*      */           } 
/* 1216 */           if (x4.getEstado().equals("A")) {
/* 1217 */             multiplicacionEjecutado *= x4.getValorEjecutado();
/* 1218 */             contador++;
/*      */           } 
/* 1220 */           if (x5.getEstado().equals("A")) {
/* 1221 */             multiplicacionEjecutado *= x5.getValorEjecutado();
/* 1222 */             contador++;
/*      */           } 
/* 1224 */           resultadoEjecutado = Math.pow(multiplicacionEjecutado, 1.0D / contador);
/* 1225 */         } catch (Exception e) {}
/*      */ 
/*      */         
/* 1228 */         contador = 0;
/*      */         try {
/* 1230 */           if (x1.getEstado().equals("A")) {
/* 1231 */             multiplicacionProgramado *= x1.getValorProgramado();
/* 1232 */             contador++;
/*      */           } 
/* 1234 */           if (x2.getEstado().equals("A")) {
/* 1235 */             multiplicacionProgramado *= x2.getValorProgramado();
/* 1236 */             contador++;
/*      */           } 
/* 1238 */           if (x3.getEstado().equals("A")) {
/* 1239 */             multiplicacionProgramado *= x3.getValorProgramado();
/* 1240 */             contador++;
/*      */           } 
/* 1242 */           if (x4.getEstado().equals("A")) {
/* 1243 */             multiplicacionProgramado *= x4.getValorProgramado();
/* 1244 */             contador++;
/*      */           } 
/* 1246 */           if (x5.getEstado().equals("A")) {
/* 1247 */             multiplicacionProgramado *= x5.getValorProgramado();
/* 1248 */             contador++;
/*      */           } 
/* 1250 */           resultadoProgramado = Math.pow(multiplicacionProgramado, 1.0D / contador);
/* 1251 */         } catch (Exception e) {}
/*      */ 
/*      */         
/* 1254 */         rs2 = new IndicadorResultadoDAO();
/* 1255 */         if (rs2.cargarRegistro(ciclo, indicador, mes) != null) {
/*      */           try {
/* 1257 */             rs2.modificarRegistro(ciclo, indicador, mes, resultadoProgramado, resultadoEjecutado, "A");
/* 1258 */           } catch (Exception e) {}
/*      */         } else {
/*      */ 
/*      */           
/*      */           try {
/* 1263 */             rs2.crearRegistro(ciclo, indicador, mes, resultadoProgramado, resultadoEjecutado, "A");
/* 1264 */           } catch (Exception e) {}
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1269 */         rs2.close();
/*      */         break;
/*      */       
/*      */       case 9:
/* 1273 */         a1 = daoCar.cargarRegistro(ciclo, indicador, mes, 21);
/* 1274 */         a2 = daoCar.cargarRegistro(ciclo, indicador, mes, 22);
/* 1275 */         a3 = daoCar.cargarRegistro(ciclo, indicador, mes, 23);
/* 1276 */         a4 = daoCar.cargarRegistro(ciclo, indicador, mes, 24);
/* 1277 */         a5 = daoCar.cargarRegistro(ciclo, indicador, mes, 25);
/* 1278 */         resultadoProgramado = 0.0D;
/* 1279 */         resultadoEjecutado = 0.0D;
/*      */         try {
/* 1281 */           if (a1.getEstado().equals("A")) {
/* 1282 */             resultadoEjecutado += a1.getValorEjecutado();
/*      */           }
/* 1284 */           if (a2.getEstado().equals("A")) {
/* 1285 */             resultadoEjecutado += a2.getValorEjecutado();
/*      */           }
/* 1287 */           if (a3.getEstado().equals("A")) {
/* 1288 */             resultadoEjecutado += a3.getValorEjecutado();
/*      */           }
/* 1290 */           if (a4.getEstado().equals("A")) {
/* 1291 */             resultadoEjecutado += a4.getValorEjecutado();
/*      */           }
/* 1293 */           if (a5.getEstado().equals("A")) {
/* 1294 */             resultadoEjecutado += a5.getValorEjecutado();
/*      */           }
/* 1296 */         } catch (Exception e) {}
/*      */ 
/*      */         
/* 1299 */         contador = 0;
/*      */         try {
/* 1301 */           if (a1.getEstado().equals("A")) {
/* 1302 */             resultadoProgramado += a1.getValorProgramado();
/*      */           }
/* 1304 */           if (a2.getEstado().equals("A")) {
/* 1305 */             resultadoProgramado += a2.getValorProgramado();
/*      */           }
/* 1307 */           if (a3.getEstado().equals("A")) {
/* 1308 */             resultadoProgramado += a3.getValorProgramado();
/*      */           }
/* 1310 */           if (a4.getEstado().equals("A")) {
/* 1311 */             resultadoProgramado += a4.getValorProgramado();
/*      */           }
/* 1313 */           if (a5.getEstado().equals("A")) {
/* 1314 */             resultadoProgramado += a5.getValorProgramado();
/*      */           }
/* 1316 */         } catch (Exception e) {}
/*      */ 
/*      */         
/* 1319 */         rs3 = new IndicadorResultadoDAO();
/* 1320 */         if (rs3.cargarRegistro(ciclo, indicador, mes) != null) {
/*      */           try {
/* 1322 */             rs3.modificarRegistro(ciclo, indicador, mes, resultadoProgramado, resultadoEjecutado, "A");
/* 1323 */           } catch (Exception e) {}
/*      */         } else {
/*      */ 
/*      */           
/*      */           try {
/* 1328 */             rs3.crearRegistro(ciclo, indicador, mes, resultadoProgramado, resultadoEjecutado, "A");
/* 1329 */           } catch (Exception e) {}
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1334 */         rs3.close();
/*      */         break;
/*      */       
/*      */       case 11:
/* 1338 */         ap1 = daoCar.cargarRegistro(ciclo, indicador, mes, 21);
/* 1339 */         ap2 = daoCar.cargarRegistro(ciclo, indicador, mes, 22);
/* 1340 */         ap3 = daoCar.cargarRegistro(ciclo, indicador, mes, 23);
/* 1341 */         ap4 = daoCar.cargarRegistro(ciclo, indicador, mes, 24);
/* 1342 */         ap5 = daoCar.cargarRegistro(ciclo, indicador, mes, 25);
/* 1343 */         alph1 = daoCar.cargarRegistro(ciclo, indicador, "NO", 26);
/* 1344 */         alph2 = daoCar.cargarRegistro(ciclo, indicador, "NO", 27);
/* 1345 */         alph3 = daoCar.cargarRegistro(ciclo, indicador, "NO", 28);
/* 1346 */         alph4 = daoCar.cargarRegistro(ciclo, indicador, "NO", 29);
/* 1347 */         alph5 = daoCar.cargarRegistro(ciclo, indicador, "NO", 30);
/* 1348 */         resultadoProgramado = 0.0D;
/* 1349 */         resultadoEjecutado = 0.0D;
/* 1350 */         promedioEjecutado = 0.0D;
/* 1351 */         promedioProgramado = 0.0D;
/*      */         try {
/* 1353 */           if (ap1.getEstado().equals("A")) {
/* 1354 */             promedioEjecutado += ap1.getValorEjecutado() * alph1.getValorProgramado();
/*      */           }
/* 1356 */           if (ap2.getEstado().equals("A")) {
/* 1357 */             promedioEjecutado += ap2.getValorEjecutado() * alph2.getValorProgramado();
/*      */           }
/* 1359 */           if (ap3.getEstado().equals("A")) {
/* 1360 */             promedioEjecutado += ap3.getValorEjecutado() * alph3.getValorProgramado();
/*      */           }
/* 1362 */           if (ap4.getEstado().equals("A")) {
/* 1363 */             promedioEjecutado += ap4.getValorEjecutado() * alph4.getValorProgramado();
/*      */           }
/* 1365 */           if (ap5.getEstado().equals("A")) {
/* 1366 */             promedioEjecutado += ap5.getValorEjecutado() * alph5.getValorProgramado();
/*      */           }
/* 1368 */           resultadoEjecutado = promedioEjecutado;
/* 1369 */         } catch (Exception e) {}
/*      */ 
/*      */         
/* 1372 */         contador = 0;
/*      */         try {
/* 1374 */           if (ap1.getEstado().equals("A")) {
/* 1375 */             promedioProgramado += ap1.getValorProgramado() * alph1.getValorProgramado();
/*      */           }
/* 1377 */           if (ap2.getEstado().equals("A")) {
/* 1378 */             promedioProgramado += ap2.getValorProgramado() * alph2.getValorProgramado();
/*      */           }
/* 1380 */           if (ap3.getEstado().equals("A")) {
/* 1381 */             promedioProgramado += ap3.getValorProgramado() * alph3.getValorProgramado();
/*      */           }
/* 1383 */           if (ap4.getEstado().equals("A")) {
/* 1384 */             promedioProgramado += ap4.getValorProgramado() * alph4.getValorProgramado();
/*      */           }
/* 1386 */           if (ap5.getEstado().equals("A")) {
/* 1387 */             promedioProgramado += ap5.getValorProgramado() * alph5.getValorProgramado();
/*      */           }
/* 1389 */           resultadoProgramado = promedioProgramado;
/* 1390 */         } catch (Exception e) {}
/*      */ 
/*      */         
/* 1393 */         rs4 = new IndicadorResultadoDAO();
/* 1394 */         if (rs4.cargarRegistro(ciclo, indicador, mes) != null) {
/*      */           try {
/* 1396 */             rs4.modificarRegistro(ciclo, indicador, mes, resultadoProgramado, resultadoEjecutado, "A");
/* 1397 */           } catch (Exception e) {}
/*      */         } else {
/*      */ 
/*      */           
/*      */           try {
/* 1402 */             rs4.crearRegistro(ciclo, indicador, mes, resultadoProgramado, resultadoEjecutado, "A");
/* 1403 */           } catch (Exception e) {}
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1408 */         rs4.close();
/*      */         break;
/*      */     }  }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String color(double valor) {
/* 1416 */     PoaTableroColoresDAO rs = new PoaTableroColoresDAO();
/* 1417 */     Collection<PoaTableroColoresDTO> colores = rs.cargarTodos("");
/* 1418 */     rs.close();
/* 1419 */     Iterator<PoaTableroColoresDTO> iterator = colores.iterator();
/* 1420 */     while (iterator.hasNext()) {
/* 1421 */       PoaTableroColoresDTO color = (PoaTableroColoresDTO)iterator.next();
/* 1422 */       if (valor <= color.getValorFinal() && valor >= color.getValorInicial()) {
/* 1423 */         return color.getColor();
/*      */       }
/*      */     } 
/* 1426 */     return "FFFFFF";
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\IndicadoresEficiencia.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */