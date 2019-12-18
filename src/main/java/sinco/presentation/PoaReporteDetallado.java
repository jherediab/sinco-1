/*      */ package sinco.presentation;
/*      */ 
/*      */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*      */ import com.lutris.util.KeywordValueException;
/*      */ import java.awt.Color;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.util.Calendar;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import org.apache.poi.hssf.usermodel.HSSFCell;
/*      */ import org.apache.poi.hssf.usermodel.HSSFCellStyle;
/*      */ import org.apache.poi.hssf.usermodel.HSSFRow;
/*      */ import org.apache.poi.hssf.usermodel.HSSFSheet;
/*      */ import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/*      */ import org.apache.poi.hssf.util.CellRangeAddress;
/*      */ import org.w3c.dom.Attr;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.html.HTMLElement;
/*      */ import org.w3c.dom.html.HTMLOptionElement;
/*      */ import org.w3c.dom.html.HTMLSelectElement;
/*      */ import org.w3c.dom.html.HTMLTableSectionElement;
/*      */ import sinco.business.PoaLogrosDTO;
/*      */ import sinco.business.PoaMaestroActividadesDTO;
/*      */ import sinco.business.PoaMaestroDTO;
/*      */ import sinco.business.PoaProcesosDTO;
/*      */ import sinco.business.PoaTableroColoresDTO;
/*      */ import sinco.business.RespuestaBD;
/*      */ import sinco.business.SisMultiValoresDTO;
/*      */ import sinco.business.TGeneralDTO;
/*      */ import sinco.business.Utilidades;
/*      */ import sinco.data.PoaLogrosDAO;
/*      */ import sinco.data.PoaMaestroActividadesDAO;
/*      */ import sinco.data.PoaMaestroDAO;
/*      */ import sinco.data.PoaProcesosDAO;
/*      */ import sinco.data.PoaTableroColoresDAO;
/*      */ import sinco.data.SisMultiValoresDAO;
/*      */ import sinco.data.TGeneralDAO;
/*      */ import sinco.presentation.PoaReporteDetallado;
/*      */ import sinco.presentation.PoaReporteDetalladoHTML;
/*      */ import sinco.spec.MenuDO;
/*      */ import sinco.spec.Varios;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PoaReporteDetallado
/*      */   implements HttpPresentation
/*      */ {
/*      */   private PoaReporteDetalladoHTML pagHTML;
/*      */   
/*      */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*   60 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*   61 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*      */     }
/*      */     
/*   64 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*   65 */     String _operacion = comms.request.getParameter("_operacion");
/*   66 */     if (_operacion == null || _operacion.length() == 0) {
/*   67 */       _operacion = "X";
/*      */     }
/*      */     
/*   70 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*   71 */       creacion(comms);
/*      */     }
/*      */     
/*   74 */     this.pagHTML = (PoaReporteDetalladoHTML)comms.xmlcFactory.create(PoaReporteDetalladoHTML.class);
/*   75 */     permisos(comms);
/*      */ 
/*      */ 
/*      */     
/*   79 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*   80 */       consulta(comms);
/*      */     }
/*   82 */     if (_operacion.equals("tablero")) {
/*   83 */       mostrarTablero(comms);
/*      */     }
/*      */     
/*   86 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*   87 */     comms.response.writeDOM(this.pagHTML);
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
/*   99 */     String _operacion = comms.request.getParameter("_operacion");
/*  100 */     String elUsuario = "" + comms.session.getUser().getName();
/*  101 */     int idLogro = 0;
/*      */     try {
/*  103 */       idLogro = Integer.parseInt(comms.request.getParameter("idLogro"));
/*      */     }
/*  105 */     catch (Exception e) {
/*  106 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idLogro"));
/*      */     } 
/*      */     
/*  109 */     RespuestaBD rta = new RespuestaBD();
/*  110 */     if (_operacion.equals("E")) {
/*  111 */       PoaLogrosDAO rs = new PoaLogrosDAO();
/*  112 */       if (!rs.getEstadoConexion()) {
/*  113 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */       }
/*  115 */       rta = rs.eliminarRegistro(idLogro);
/*  116 */       if (!rta.isRta()) {
/*  117 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaLogros&p1=" + rta.getMensaje()));
/*      */       }
/*  119 */       rs.close();
/*  120 */       String sPagina = "PoaLogros.po?_operacion=X";
/*  121 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*      */     } 
/*  123 */     int codigoPoa = 0;
/*      */     try {
/*  125 */       codigoPoa = Integer.parseInt(comms.request.getParameter("codigoPoa"));
/*      */     }
/*  127 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  130 */     String mes = comms.request.getParameter("mes");
/*  131 */     int ejecucion = 0;
/*      */     try {
/*  133 */       ejecucion = Integer.parseInt(comms.request.getParameter("ejecucion"));
/*      */     }
/*  135 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  138 */     String avances = comms.request.getParameter("avances");
/*  139 */     String logrosYResultados = comms.request.getParameter("logrosYResultados");
/*  140 */     String retrasosDificultades = comms.request.getParameter("retrasosDificultades");
/*  141 */     String estado = comms.request.getParameter("estado");
/*  142 */     PoaLogrosDAO rs = new PoaLogrosDAO();
/*  143 */     if (!rs.getEstadoConexion()) {
/*  144 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */     }
/*  146 */     if (_operacion.equals("C")) {
/*  147 */       rta = rs.crearRegistro(idLogro, codigoPoa, mes, ejecucion, avances, logrosYResultados, retrasosDificultades, estado, elUsuario);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  157 */       idLogro = rta.getSecuencia();
/*      */     } else {
/*      */       
/*  160 */       rta = rs.modificarRegistro(idLogro, codigoPoa, mes, ejecucion, avances, logrosYResultados, retrasosDificultades, estado, elUsuario);
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
/*  171 */     rs.close();
/*  172 */     if (!rta.isRta()) {
/*  173 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaLogros&p1=" + rta.getMensaje()));
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
/*      */   private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  195 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*      */     
/*  197 */     Varios oVarios = new Varios();
/*  198 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Poa_PoaLogrosAct");
/*  199 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Poa_PoaLogrosDel");
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
/*  221 */     if (!vista.equals("tablero")) {
/*  222 */       HTMLElement sel = this.pagHTML.getElementDivTableroControl();
/*  223 */       sel.getParentNode().removeChild(sel);
/*      */     } 
/*  225 */     if (!vista.equals("consulta")) {
/*  226 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/*  227 */       sel.getParentNode().removeChild(sel);
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
/*  241 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/*  242 */     atrib.setValue(valor);
/*  243 */     return atrib;
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
/*  256 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  257 */     Element enlace = this.pagHTML.createElement("a");
/*  258 */     Node hijo = this.pagHTML.createTextNode(contenido);
/*  259 */     enlace.appendChild(hijo);
/*  260 */     Attr donde = this.pagHTML.createAttribute("href");
/*  261 */     donde.setValue(vinculo);
/*  262 */     enlace.setAttributeNode(donde);
/*  263 */     td.appendChild(enlace);
/*  264 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  265 */     return td;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private HTMLElement newtd(String contenido) {
/*  275 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  276 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  277 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  278 */     return td;
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
/*  289 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  290 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  291 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  292 */     td.setAttributeNode(newAttr("colspan", "" + colspan));
/*  293 */     return td;
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
/*  304 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("th");
/*  305 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  306 */     td.setAttributeNode(newAttr("class", "" + clase));
/*  307 */     td.setAttributeNode(newAttr("colspan", "" + colspan));
/*  308 */     td.setAttributeNode(newAttr("width", "95%"));
/*  309 */     return td;
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
/*  321 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  322 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  323 */     td.setAttributeNode(newAttr("bgcolor", "#" + color));
/*  324 */     return td;
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
/*  335 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  336 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  337 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  338 */     td.setAttributeNode(newAttr("bgcolor", "#" + color));
/*  339 */     td.setAttributeNode(newAttr("colspan", "" + colspan));
/*  340 */     return td;
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
/*  351 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  352 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  353 */     td.setAttributeNode(newAttr("class", clase));
/*  354 */     td.setAttributeNode(newAttr("colspan", "30"));
/*  355 */     return td;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void consulta(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  361 */     activarVista("consulta");
/*  362 */     HTMLSelectElement combo = this.pagHTML.getElementFarea();
/*  363 */     llenarCombo(combo, "UNIDADES_DEPENDENCIA", "CODIGO", "DESCRIPCION", "1=1", "", true);
/*  364 */     combo = this.pagHTML.getElementFciclo();
/*  365 */     llenarCombo(combo, "POA_CICLOS", "CODIGO_CICLO", "DESCRIPCION", "1=1", "", false);
/*  366 */     combo = this.pagHTML.getElementFproceso();
/*  367 */     llenarCombo(combo, "POA_PROCESOS", "CODIGO_PROCESO", "DESCRIPCION", "1=1", "", true);
/*  368 */     combo = this.pagHTML.getElementFmes();
/*  369 */     llenarComboMeses(combo, "", false);
/*  370 */     combo = this.pagHTML.getElementFmesInicial();
/*  371 */     llenarComboMeses(combo, "", false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void mostrarTablero(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  378 */     activarVista("tablero");
/*  379 */     int area = 0;
/*      */     try {
/*  381 */       area = Integer.parseInt(comms.request.getParameter("farea"));
/*      */     }
/*  383 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  386 */     int ciclo = 0;
/*      */     try {
/*  388 */       ciclo = Integer.parseInt(comms.request.getParameter("fciclo"));
/*      */     }
/*  390 */     catch (Exception e) {}
/*      */     
/*  392 */     String proceso = comms.request.getParameter("fproceso");
/*      */     
/*  394 */     int mes = 0;
/*      */     
/*      */     try {
/*  397 */       mes = Integer.parseInt(comms.request.getParameter("fmes").replace("mes", ""));
/*      */     }
/*  399 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  402 */     int mesInicial = 0;
/*      */     
/*      */     try {
/*  405 */       mesInicial = Integer.parseInt(comms.request.getParameter("fmesInicial").replace("mes", ""));
/*      */     }
/*  407 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  410 */     PoaMaestroDAO rsM = new PoaMaestroDAO();
/*      */     
/*  412 */     HTMLTableSectionElement tabla = this.pagHTML.getElementTablaTablero();
/*  413 */     tabla.setAttributeNode(newAttr("class", "table table-condensed"));
/*      */     
/*  415 */     int contadorActividadesGeneral = 0;
/*  416 */     double totalValoresGeneral = 0.0D;
/*  417 */     HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  418 */     if (mesInicial == mes) {
/*  419 */       eltr.appendChild(newtdTitle("Reporte detallado mes: " + getMes(mesInicial) + " de " + ciclo, "ca3"));
/*      */     } else {
/*  421 */       eltr.appendChild(newtdTitle("Reporte detallado meses " + getMes(mesInicial) + " a " + getMes(mes) + " de " + ciclo, "ca3"));
/*      */     } 
/*      */     
/*  424 */     tabla.appendChild(eltr);
/*  425 */     Collection<Integer> procesos = rsM.cargarProcesos(area, ciclo, proceso);
/*  426 */     int numTotalActividades = rsM.contarActividades(area, ciclo, proceso);
/*      */ 
/*      */ 
/*      */     
/*  430 */     HSSFWorkbook wb = new HSSFWorkbook();
/*      */ 
/*      */ 
/*      */     
/*  434 */     HSSFSheet sheet = wb.createSheet("informe POA");
/*      */ 
/*      */ 
/*      */     
/*  438 */     Calendar fecha = Calendar.getInstance();
/*  439 */     String elUsuario = "" + comms.session.getUser().getName();
/*  440 */     String nombreArchivo = "/poa" + elUsuario + fecha.get(1) + (fecha.get(2) + 1) + fecha.get(5) + fecha.get(11) + fecha.get(12) + fecha.get(13);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  446 */     String toPath = System.getProperty("user.home") + nombreArchivo + ".xls";
/*      */ 
/*      */     
/*  449 */     HSSFCellStyle estiloProceso = wb.createCellStyle();
/*  450 */     HSSFCellStyle estiloArea = wb.createCellStyle();
/*  451 */     HSSFCellStyle estiloAlto = wb.createCellStyle();
/*  452 */     HSSFCellStyle estiloMedio = wb.createCellStyle();
/*  453 */     HSSFCellStyle estiloBajo = wb.createCellStyle();
/*  454 */     HSSFCellStyle titulo = wb.createCellStyle();
/*  455 */     HSSFCellStyle normal = wb.createCellStyle();
/*      */ 
/*      */     
/*  458 */     estiloProceso.setFillForegroundColor((short)55);
/*  459 */     estiloProceso.setAlignment((short)2);
/*  460 */     estiloProceso.setFillPattern((short)1);
/*      */ 
/*      */     
/*  463 */     estiloArea.setFillForegroundColor((short)22);
/*  464 */     estiloArea.setFillPattern((short)1);
/*      */     
/*  466 */     titulo.setAlignment((short)2);
/*      */     
/*  468 */     Utilidades.grabarLog(toPath);
/*      */ 
/*      */     
/*  471 */     int contadorFilas = 0;
/*      */     
/*  473 */     Iterator<Integer> iteProcesos = procesos.iterator();
/*  474 */     while (iteProcesos.hasNext()) {
/*  475 */       HTMLElement eltdProceso = (HTMLElement)this.pagHTML.createElement("td");
/*  476 */       HTMLElement tablaProcesos = (HTMLElement)this.pagHTML.createElement("table");
/*  477 */       tablaProcesos.setAttributeNode(newAttr("class", "table-condensed"));
/*  478 */       tablaProcesos.setAttributeNode(newAttr("width", "100%"));
/*  479 */       int proc = ((Integer)iteProcesos.next()).intValue();
/*  480 */       int actividadesProceso = rsM.contarActividades(area, ciclo, "" + proc);
/*  481 */       Collection<PoaMaestroDTO> maestros = rsM.cargarRegistros(area, ciclo, proc);
/*      */       
/*  483 */       Iterator<PoaMaestroDTO> iteMaestro = maestros.iterator();
/*      */       
/*  485 */       PoaProcesosDAO obProceso = new PoaProcesosDAO();
/*  486 */       PoaProcesosDTO pro = obProceso.cargarRegistro(proc);
/*      */       
/*  488 */       double totalProceso = totalProceso(rsM, proc, area, ciclo, mesInicial, mes);
/*      */       
/*  490 */       eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  491 */       eltr.setAttributeNode(newAttr("data-toggle", "collapse"));
/*  492 */       eltr.setAttributeNode(newAttr("data-target", "." + pro.getCodigoProceso()));
/*  493 */       eltr.setAttributeNode(newAttr("class", "accordion-toggle sortcol"));
/*  494 */       eltr.setAttributeNode(newAttr("width", "100%"));
/*      */ 
/*      */       
/*  497 */       eltr.appendChild(newth("Proceso: " + pro.getDescripcion(), "ca3", 2));
/*  498 */       eltr.appendChild(newtdColor("" + totalProceso + "%", color(totalProceso)));
/*  499 */       tablaProcesos.appendChild(eltr);
/*      */ 
/*      */       
/*  502 */       HSSFRow row = sheet.createRow(contadorFilas);
/*  503 */       HSSFCell cell = row.createCell(0);
/*  504 */       cell.setCellValue(pro.getDescripcion());
/*  505 */       cell.setCellStyle(estiloProceso);
/*  506 */       sheet.addMergedRegion(new CellRangeAddress(contadorFilas, contadorFilas, 0, 31));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  512 */       agregarCelda(row, sheet, contadorFilas, "" + totalProceso, 32, 32, 32, titulo);
/*  513 */       contadorFilas++;
/*  514 */       double totalValoresProceso = 0.0D;
/*  515 */       while (iteMaestro.hasNext()) {
/*  516 */         HTMLElement eltdArea = (HTMLElement)this.pagHTML.createElement("td");
/*  517 */         HTMLElement eltdActividades = (HTMLElement)this.pagHTML.createElement("td");
/*  518 */         HTMLElement tablaAreas = (HTMLElement)this.pagHTML.createElement("table");
/*  519 */         HTMLElement tablaActividades = (HTMLElement)this.pagHTML.createElement("table");
/*  520 */         tablaAreas.setAttributeNode(newAttr("class", "table-condensed"));
/*  521 */         tablaAreas.setAttributeNode(newAttr("width", "100%"));
/*  522 */         tablaActividades.setAttributeNode(newAttr("width", "100%"));
/*  523 */         int contadorActividadesArea = 0;
/*      */         
/*  525 */         PoaMaestroDTO maestro = (PoaMaestroDTO)iteMaestro.next();
/*      */         
/*  527 */         double totalArea1 = 0.0D;
/*  528 */         double totalArea = totalArea(maestro, rsM, pro, area, ciclo, mesInicial, mes);
/*  529 */         eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  530 */         eltr.setAttributeNode(newAttr("class", "area"));
/*  531 */         eltr.appendChild(newth("Área: " + maestro.getNombreArea(), "ca4", 2));
/*  532 */         eltr.appendChild(newtdColor("" + totalArea + "%", color(totalArea)));
/*      */         
/*  534 */         eltr.setAttributeNode(newAttr("data-toggle", "collapse"));
/*  535 */         eltr.setAttributeNode(newAttr("data-target", ".act" + pro.getCodigoProceso() + "" + maestro.getArea()));
/*  536 */         eltr.setAttributeNode(newAttr("class", "accordion-toggle sortcol"));
/*  537 */         tablaAreas.appendChild(eltr);
/*      */ 
/*      */         
/*  540 */         row = sheet.createRow(contadorFilas);
/*  541 */         cell = row.createCell(0);
/*  542 */         cell.setCellValue(maestro.getNombreArea());
/*  543 */         cell.setCellStyle(estiloArea);
/*  544 */         sheet.addMergedRegion(new CellRangeAddress(contadorFilas, contadorFilas, 0, 30));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  550 */         agregarCelda(row, sheet, contadorFilas, "" + totalArea, 31, 31, 31, titulo);
/*  551 */         contadorFilas++;
/*  552 */         int actividadesArea = rsM.contarActividades(maestro.getArea(), ciclo, "" + maestro.getProceso());
/*  553 */         row = sheet.createRow(contadorFilas);
/*  554 */         eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  555 */         eltr.appendChild(newth("Actividad", "cf3", 1));
/*      */         
/*  557 */         agregarCelda(row, sheet, contadorFilas, "Actividad", 0, 0, 3, titulo);
/*  558 */         eltr.appendChild(newth("Enero", "cf4", 2));
/*      */         
/*  560 */         agregarCelda(row, sheet, contadorFilas, "Enero", 4, 4, 5, titulo);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  565 */         eltr.appendChild(newth("Febrero", "cf3", 2));
/*  566 */         agregarCelda(row, sheet, contadorFilas, "Febrero", 6, 6, 7, titulo);
/*  567 */         eltr.appendChild(newth("Marzo", "cf4", 2));
/*  568 */         agregarCelda(row, sheet, contadorFilas, "Marzo", 8, 8, 9, titulo);
/*  569 */         eltr.appendChild(newth("Abril", "cf3", 2));
/*  570 */         agregarCelda(row, sheet, contadorFilas, "Abril", 10, 10, 11, titulo);
/*  571 */         eltr.appendChild(newth("Mayo", "cf4", 2));
/*  572 */         agregarCelda(row, sheet, contadorFilas, "Mayo", 12, 12, 13, titulo);
/*  573 */         eltr.appendChild(newth("Junio", "cf3", 2));
/*  574 */         agregarCelda(row, sheet, contadorFilas, "Junio", 14, 14, 15, titulo);
/*  575 */         eltr.appendChild(newth("Julio", "cf4", 2));
/*  576 */         agregarCelda(row, sheet, contadorFilas, "Julio", 16, 16, 17, titulo);
/*  577 */         eltr.appendChild(newth("Agosto", "cf3", 2));
/*  578 */         agregarCelda(row, sheet, contadorFilas, "Agosto", 18, 18, 19, titulo);
/*  579 */         eltr.appendChild(newth("Septiembre", "cf4", 2));
/*  580 */         agregarCelda(row, sheet, contadorFilas, "Septiembre", 20, 20, 21, titulo);
/*  581 */         eltr.appendChild(newth("Octubre", "cf3", 2));
/*  582 */         agregarCelda(row, sheet, contadorFilas, "Octubre", 22, 22, 23, titulo);
/*  583 */         eltr.appendChild(newth("Noviembre", "cf4", 2));
/*  584 */         agregarCelda(row, sheet, contadorFilas, "Noviembre", 24, 24, 25, titulo);
/*  585 */         eltr.appendChild(newth("Diciembre", "cf3", 2));
/*  586 */         agregarCelda(row, sheet, contadorFilas, "Diciembre", 26, 26, 27, titulo);
/*  587 */         tablaActividades.appendChild(eltr);
/*  588 */         contadorFilas++;
/*      */         
/*  590 */         row = sheet.createRow(contadorFilas);
/*  591 */         eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  592 */         eltr.appendChild(newth("", "cf3", 1));
/*  593 */         eltr.appendChild(newth("Es", "cf4", 1));
/*  594 */         agregarCelda(row, sheet, contadorFilas, "Estimado", 4, 4, 4, titulo);
/*  595 */         eltr.appendChild(newth("Ej", "cf3", 1));
/*  596 */         agregarCelda(row, sheet, contadorFilas, "Ejecutado", 5, 5, 5, titulo);
/*  597 */         eltr.appendChild(newth("Es", "cf4", 1));
/*  598 */         agregarCelda(row, sheet, contadorFilas, "Estimado", 6, 6, 6, titulo);
/*  599 */         eltr.appendChild(newth("Ej", "cf3", 1));
/*  600 */         agregarCelda(row, sheet, contadorFilas, "Ejecutado", 7, 7, 7, titulo);
/*  601 */         eltr.appendChild(newth("Es", "cf4", 1));
/*  602 */         agregarCelda(row, sheet, contadorFilas, "Estimado", 8, 8, 8, titulo);
/*  603 */         eltr.appendChild(newth("Ej", "cf3", 1));
/*  604 */         agregarCelda(row, sheet, contadorFilas, "Ejecutado", 9, 9, 9, titulo);
/*  605 */         eltr.appendChild(newth("Es", "cf4", 1));
/*  606 */         agregarCelda(row, sheet, contadorFilas, "Estimado", 10, 10, 10, titulo);
/*  607 */         eltr.appendChild(newth("Ej", "cf3", 1));
/*  608 */         agregarCelda(row, sheet, contadorFilas, "Ejecutado", 11, 11, 11, titulo);
/*  609 */         eltr.appendChild(newth("Es", "cf4", 1));
/*  610 */         agregarCelda(row, sheet, contadorFilas, "Estimado", 12, 12, 12, titulo);
/*  611 */         eltr.appendChild(newth("Ej", "cf3", 1));
/*  612 */         agregarCelda(row, sheet, contadorFilas, "Ejecutado", 13, 13, 13, titulo);
/*  613 */         eltr.appendChild(newth("Es", "cf4", 1));
/*  614 */         agregarCelda(row, sheet, contadorFilas, "Estimado", 14, 14, 14, titulo);
/*  615 */         eltr.appendChild(newth("Ej", "cf3", 1));
/*  616 */         agregarCelda(row, sheet, contadorFilas, "Ejecutado", 15, 15, 15, titulo);
/*  617 */         eltr.appendChild(newth("Es", "cf4", 1));
/*  618 */         agregarCelda(row, sheet, contadorFilas, "Estimado", 16, 16, 16, titulo);
/*  619 */         eltr.appendChild(newth("Ej", "cf3", 1));
/*  620 */         agregarCelda(row, sheet, contadorFilas, "Ejecutado", 17, 17, 17, titulo);
/*  621 */         eltr.appendChild(newth("Es", "cf4", 1));
/*  622 */         agregarCelda(row, sheet, contadorFilas, "Estimado", 18, 18, 18, titulo);
/*  623 */         eltr.appendChild(newth("Ej", "cf3", 1));
/*  624 */         agregarCelda(row, sheet, contadorFilas, "Ejecutado", 19, 19, 19, titulo);
/*  625 */         eltr.appendChild(newth("Es", "cf4", 1));
/*  626 */         agregarCelda(row, sheet, contadorFilas, "Estimado", 20, 20, 20, titulo);
/*  627 */         eltr.appendChild(newth("Ej", "cf3", 1));
/*  628 */         agregarCelda(row, sheet, contadorFilas, "Ejecutado", 21, 21, 21, titulo);
/*  629 */         eltr.appendChild(newth("Es", "cf4", 1));
/*  630 */         agregarCelda(row, sheet, contadorFilas, "Estimado", 22, 22, 22, titulo);
/*  631 */         eltr.appendChild(newth("Ej", "cf3", 1));
/*  632 */         agregarCelda(row, sheet, contadorFilas, "Ejecutado", 23, 23, 23, titulo);
/*  633 */         eltr.appendChild(newth("Es", "cf4", 1));
/*  634 */         agregarCelda(row, sheet, contadorFilas, "Estimado", 24, 24, 24, titulo);
/*  635 */         eltr.appendChild(newth("Ej", "cf3", 1));
/*  636 */         agregarCelda(row, sheet, contadorFilas, "Ejecutado", 25, 25, 25, titulo);
/*  637 */         eltr.appendChild(newth("Es", "cf4", 1));
/*  638 */         agregarCelda(row, sheet, contadorFilas, "Estimado", 26, 26, 26, titulo);
/*  639 */         eltr.appendChild(newth("Ej", "cf3", 1));
/*  640 */         agregarCelda(row, sheet, contadorFilas, "Ejecutado", 27, 27, 27, titulo);
/*  641 */         if (mesInicial == mes) {
/*  642 */           eltr.appendChild(newth("Resultado mes " + getMes(mesInicial), "cf4", 1));
/*  643 */           agregarCelda(row, sheet, contadorFilas, "Resultado mes " + getMes(mesInicial), 28, 28, 28, titulo);
/*      */         } else {
/*  645 */           eltr.appendChild(newth("Resultado meses " + getMes(mesInicial) + " a " + getMes(mes), "cf4", 1));
/*  646 */           agregarCelda(row, sheet, contadorFilas, "Resultado meses " + getMes(mesInicial) + " a " + getMes(mes), 28, 28, 28, titulo);
/*      */         } 
/*      */         
/*  649 */         tablaActividades.appendChild(eltr);
/*  650 */         contadorFilas++;
/*  651 */         PoaMaestroActividadesDAO rsAct = new PoaMaestroActividadesDAO();
/*  652 */         Collection<PoaMaestroActividadesDTO> actividades = rsAct.cargarTodos(maestro.getCodigoPoa(), 0);
/*  653 */         rsAct.close();
/*      */ 
/*      */         
/*  656 */         Iterator<PoaMaestroActividadesDTO> iteActividades = actividades.iterator();
/*  657 */         while (iteActividades.hasNext()) {
/*  658 */           int ejecucion = 0;
/*      */           
/*  660 */           contadorActividadesGeneral++;
/*  661 */           contadorActividadesArea++;
/*  662 */           PoaMaestroActividadesDTO actividad = (PoaMaestroActividadesDTO)iteActividades.next();
/*  663 */           eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  664 */           HTMLElement eltr2 = (HTMLElement)this.pagHTML.createElement("tr");
/*  665 */           eltr.appendChild(newtd("" + actividad.getNombreActividad()));
/*  666 */           eltr2.appendChild(newtd("% Ejecución"));
/*      */           
/*  668 */           row = sheet.createRow(contadorFilas);
/*  669 */           HSSFRow rowEjecucion = sheet.createRow(contadorFilas + 1);
/*  670 */           agregarCelda(row, sheet, contadorFilas, actividad.getNombreActividad(), 0, 0, 3, normal);
/*      */           
/*  672 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "Porcentaje Ejecución", 0, 0, 3, normal);
/*      */           
/*  674 */           eltr.appendChild(newtd("" + actividad.getValorMes1()));
/*  675 */           agregarCelda(row, sheet, contadorFilas, "" + actividad.getValorMes1(), 4, 4, 4, normal);
/*  676 */           String url = urlLogro(actividad.getCodigoPoaActividad(), 1);
/*  677 */           eltr.appendChild(newtdhref("" + valorReal(actividad.getCodigoPoaActividad(), 1), url));
/*  678 */           agregarCelda(row, sheet, contadorFilas, "" + valorReal(actividad.getCodigoPoaActividad(), 1), 5, 5, 5, normal);
/*      */           
/*      */           try {
/*  681 */             ejecucion = valorReal(actividad.getCodigoPoaActividad(), 1) * 100 / actividad.getValorMes1();
/*  682 */           } catch (Exception e) {
/*  683 */             ejecucion = 0;
/*      */           } 
/*      */           
/*  686 */           HSSFCellStyle ejecucionColor = wb.createCellStyle();
/*  687 */           if (actividad.getValorMes1() > 0 && mes >= 1) {
/*  688 */             eltr2.appendChild(newtdColor("" + ejecucion + " %", color(ejecucion), 2));
/*      */           } else {
/*  690 */             eltr2.appendChild(newtd("" + ejecucion + " %", 2));
/*      */           } 
/*  692 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "" + ejecucion, 4, 4, 5, titulo);
/*  693 */           eltr.appendChild(newtd("" + actividad.getValorMes2()));
/*  694 */           agregarCelda(row, sheet, contadorFilas, "" + actividad.getValorMes2(), 6, 6, 6, normal);
/*  695 */           url = urlLogro(actividad.getCodigoPoaActividad(), 2);
/*  696 */           eltr.appendChild(newtdhref("" + valorReal(actividad.getCodigoPoaActividad(), 2), url));
/*  697 */           agregarCelda(row, sheet, contadorFilas, "" + valorReal(actividad.getCodigoPoaActividad(), 2), 7, 7, 7, normal);
/*      */           try {
/*  699 */             ejecucion = valorReal(actividad.getCodigoPoaActividad(), 2) * 100 / actividad.getValorMes2();
/*  700 */           } catch (Exception e) {
/*  701 */             ejecucion = 0;
/*      */           } 
/*  703 */           if (actividad.getValorMes2() > 0 && mes >= 2) {
/*  704 */             eltr2.appendChild(newtdColor("" + ejecucion + " %", color(ejecucion), 2));
/*      */           } else {
/*  706 */             eltr2.appendChild(newtd("" + ejecucion + " %", 2));
/*      */           } 
/*  708 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "" + ejecucion, 6, 6, 7, titulo);
/*  709 */           eltr.appendChild(newtd("" + actividad.getValorMes3()));
/*  710 */           agregarCelda(row, sheet, contadorFilas, "" + actividad.getValorMes3(), 8, 8, 8, normal);
/*  711 */           url = urlLogro(actividad.getCodigoPoaActividad(), 3);
/*  712 */           eltr.appendChild(newtdhref("" + valorReal(actividad.getCodigoPoaActividad(), 3), url));
/*  713 */           agregarCelda(row, sheet, contadorFilas, "" + valorReal(actividad.getCodigoPoaActividad(), 3), 9, 9, 9, normal);
/*      */           try {
/*  715 */             ejecucion = valorReal(actividad.getCodigoPoaActividad(), 3) * 100 / actividad.getValorMes3();
/*  716 */           } catch (Exception e) {
/*  717 */             ejecucion = 0;
/*      */           } 
/*  719 */           if (actividad.getValorMes3() > 0 && mes >= 3) {
/*  720 */             eltr2.appendChild(newtdColor("" + ejecucion + " %", color(ejecucion), 2));
/*      */           } else {
/*  722 */             eltr2.appendChild(newtd("" + ejecucion + " %", 2));
/*      */           } 
/*  724 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "" + ejecucion, 8, 8, 9, titulo);
/*  725 */           eltr.appendChild(newtd("" + actividad.getValorMes4()));
/*  726 */           agregarCelda(row, sheet, contadorFilas, "" + actividad.getValorMes4(), 10, 10, 10, normal);
/*  727 */           url = urlLogro(actividad.getCodigoPoaActividad(), 4);
/*  728 */           eltr.appendChild(newtdhref("" + valorReal(actividad.getCodigoPoaActividad(), 4), url));
/*  729 */           agregarCelda(row, sheet, contadorFilas, "" + valorReal(actividad.getCodigoPoaActividad(), 4), 11, 11, 11, normal);
/*      */           try {
/*  731 */             ejecucion = valorReal(actividad.getCodigoPoaActividad(), 4) * 100 / actividad.getValorMes4();
/*  732 */           } catch (Exception e) {
/*  733 */             ejecucion = 0;
/*      */           } 
/*  735 */           if (actividad.getValorMes4() > 0 && mes >= 4) {
/*  736 */             eltr2.appendChild(newtdColor("" + ejecucion + " %", color(ejecucion), 2));
/*      */           } else {
/*  738 */             eltr2.appendChild(newtd("" + ejecucion + " %", 2));
/*      */           } 
/*  740 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "" + ejecucion, 10, 10, 11, titulo);
/*  741 */           eltr.appendChild(newtd("" + actividad.getValorMes5()));
/*  742 */           agregarCelda(row, sheet, contadorFilas, "" + actividad.getValorMes5(), 12, 12, 12, normal);
/*  743 */           url = urlLogro(actividad.getCodigoPoaActividad(), 5);
/*  744 */           eltr.appendChild(newtdhref("" + valorReal(actividad.getCodigoPoaActividad(), 5), url));
/*  745 */           agregarCelda(row, sheet, contadorFilas, "" + valorReal(actividad.getCodigoPoaActividad(), 5), 13, 13, 13, normal);
/*      */           try {
/*  747 */             ejecucion = valorReal(actividad.getCodigoPoaActividad(), 5) * 100 / actividad.getValorMes5();
/*  748 */           } catch (Exception e) {
/*  749 */             ejecucion = 0;
/*      */           } 
/*  751 */           if (actividad.getValorMes5() > 0 && mes >= 5) {
/*  752 */             eltr2.appendChild(newtdColor("" + ejecucion + " %", color(ejecucion), 2));
/*      */           } else {
/*  754 */             eltr2.appendChild(newtd("" + ejecucion + " %", 2));
/*      */           } 
/*  756 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "" + ejecucion, 12, 12, 13, titulo);
/*  757 */           eltr.appendChild(newtd("" + actividad.getValorMes6()));
/*  758 */           agregarCelda(row, sheet, contadorFilas, "" + actividad.getValorMes6(), 14, 14, 14, normal);
/*  759 */           url = urlLogro(actividad.getCodigoPoaActividad(), 6);
/*  760 */           eltr.appendChild(newtdhref("" + valorReal(actividad.getCodigoPoaActividad(), 6), url));
/*  761 */           agregarCelda(row, sheet, contadorFilas, "" + valorReal(actividad.getCodigoPoaActividad(), 6), 15, 15, 15, normal);
/*      */           try {
/*  763 */             ejecucion = valorReal(actividad.getCodigoPoaActividad(), 6) * 100 / actividad.getValorMes6();
/*  764 */           } catch (Exception e) {
/*  765 */             ejecucion = 0;
/*      */           } 
/*  767 */           if (actividad.getValorMes6() > 0 && mes >= 6) {
/*  768 */             eltr2.appendChild(newtdColor("" + ejecucion + " %", color(ejecucion), 2));
/*      */           } else {
/*  770 */             eltr2.appendChild(newtd("" + ejecucion + " %", 2));
/*      */           } 
/*  772 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "" + ejecucion, 14, 14, 15, titulo);
/*  773 */           eltr.appendChild(newtd("" + actividad.getValorMes7()));
/*  774 */           agregarCelda(row, sheet, contadorFilas, "" + actividad.getValorMes7(), 16, 16, 16, normal);
/*  775 */           url = urlLogro(actividad.getCodigoPoaActividad(), 7);
/*  776 */           eltr.appendChild(newtdhref("" + valorReal(actividad.getCodigoPoaActividad(), 7), url));
/*  777 */           agregarCelda(row, sheet, contadorFilas, "" + valorReal(actividad.getCodigoPoaActividad(), 7), 17, 17, 17, normal);
/*      */           try {
/*  779 */             ejecucion = valorReal(actividad.getCodigoPoaActividad(), 7) * 100 / actividad.getValorMes7();
/*  780 */           } catch (Exception e) {
/*  781 */             ejecucion = 0;
/*      */           } 
/*  783 */           if (actividad.getValorMes7() > 0 && mes >= 7) {
/*  784 */             eltr2.appendChild(newtdColor("" + ejecucion + " %", color(ejecucion), 2));
/*      */           } else {
/*  786 */             eltr2.appendChild(newtd("" + ejecucion + " %", 2));
/*      */           } 
/*  788 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "" + ejecucion, 16, 16, 17, titulo);
/*  789 */           eltr.appendChild(newtd("" + actividad.getValorMes8()));
/*  790 */           agregarCelda(row, sheet, contadorFilas, "" + actividad.getValorMes8(), 18, 18, 18, normal);
/*  791 */           url = urlLogro(actividad.getCodigoPoaActividad(), 8);
/*  792 */           eltr.appendChild(newtdhref("" + valorReal(actividad.getCodigoPoaActividad(), 8), url));
/*  793 */           agregarCelda(row, sheet, contadorFilas, "" + valorReal(actividad.getCodigoPoaActividad(), 8), 19, 19, 19, normal);
/*      */           try {
/*  795 */             ejecucion = valorReal(actividad.getCodigoPoaActividad(), 8) * 100 / actividad.getValorMes8();
/*  796 */           } catch (Exception e) {
/*  797 */             ejecucion = 0;
/*      */           } 
/*  799 */           if (actividad.getValorMes8() > 0 && mes >= 8) {
/*  800 */             eltr2.appendChild(newtdColor("" + ejecucion + " %", color(ejecucion), 2));
/*      */           } else {
/*  802 */             eltr2.appendChild(newtd("" + ejecucion + " %", 2));
/*      */           } 
/*  804 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "" + ejecucion, 18, 18, 19, titulo);
/*  805 */           eltr.appendChild(newtd("" + actividad.getValorMes9()));
/*  806 */           agregarCelda(row, sheet, contadorFilas, "" + actividad.getValorMes9(), 20, 20, 20, normal);
/*  807 */           url = urlLogro(actividad.getCodigoPoaActividad(), 9);
/*  808 */           eltr.appendChild(newtdhref("" + valorReal(actividad.getCodigoPoaActividad(), 9), url));
/*  809 */           agregarCelda(row, sheet, contadorFilas, "" + valorReal(actividad.getCodigoPoaActividad(), 9), 21, 21, 21, normal);
/*      */           try {
/*  811 */             ejecucion = valorReal(actividad.getCodigoPoaActividad(), 9) * 100 / actividad.getValorMes9();
/*  812 */           } catch (Exception e) {
/*  813 */             ejecucion = 0;
/*      */           } 
/*  815 */           if (actividad.getValorMes9() > 0 && mes >= 9) {
/*  816 */             eltr2.appendChild(newtdColor("" + ejecucion + " %", color(ejecucion), 2));
/*      */           } else {
/*  818 */             eltr2.appendChild(newtd("" + ejecucion + " %", 2));
/*      */           } 
/*  820 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "" + ejecucion, 20, 20, 21, titulo);
/*  821 */           eltr.appendChild(newtd("" + actividad.getValorMes10()));
/*  822 */           agregarCelda(row, sheet, contadorFilas, "" + actividad.getValorMes10(), 22, 22, 22, normal);
/*  823 */           url = urlLogro(actividad.getCodigoPoaActividad(), 10);
/*  824 */           eltr.appendChild(newtdhref("" + valorReal(actividad.getCodigoPoaActividad(), 10), url));
/*  825 */           agregarCelda(row, sheet, contadorFilas, "" + valorReal(actividad.getCodigoPoaActividad(), 10), 23, 23, 23, normal);
/*      */           try {
/*  827 */             ejecucion = valorReal(actividad.getCodigoPoaActividad(), 10) * 100 / actividad.getValorMes10();
/*  828 */           } catch (Exception e) {
/*  829 */             ejecucion = 0;
/*      */           } 
/*  831 */           if (actividad.getValorMes10() > 0 && mes >= 10) {
/*  832 */             eltr2.appendChild(newtdColor("" + ejecucion + " %", color(ejecucion), 2));
/*      */           } else {
/*  834 */             eltr2.appendChild(newtd("" + ejecucion + " %", 2));
/*      */           } 
/*  836 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "" + ejecucion, 22, 22, 23, titulo);
/*  837 */           eltr.appendChild(newtd("" + actividad.getValorMes11()));
/*  838 */           agregarCelda(row, sheet, contadorFilas, "" + actividad.getValorMes11(), 24, 24, 24, normal);
/*  839 */           url = urlLogro(actividad.getCodigoPoaActividad(), 11);
/*  840 */           eltr.appendChild(newtdhref("" + valorReal(actividad.getCodigoPoaActividad(), 11), url));
/*  841 */           agregarCelda(row, sheet, contadorFilas, "" + valorReal(actividad.getCodigoPoaActividad(), 11), 25, 25, 25, normal);
/*      */           try {
/*  843 */             ejecucion = valorReal(actividad.getCodigoPoaActividad(), 11) * 100 / actividad.getValorMes11();
/*  844 */           } catch (Exception e) {
/*  845 */             ejecucion = 0;
/*      */           } 
/*  847 */           if (actividad.getValorMes11() > 0 && mes >= 11) {
/*  848 */             eltr2.appendChild(newtdColor("" + ejecucion + " %", color(ejecucion), 2));
/*      */           } else {
/*  850 */             eltr2.appendChild(newtd("" + ejecucion + " %", 2));
/*      */           } 
/*  852 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "" + ejecucion, 24, 24, 25, titulo);
/*  853 */           eltr.appendChild(newtd("" + actividad.getValorMes12()));
/*  854 */           agregarCelda(row, sheet, contadorFilas, "" + actividad.getValorMes12(), 26, 26, 26, normal);
/*  855 */           url = urlLogro(actividad.getCodigoPoaActividad(), 12);
/*  856 */           eltr.appendChild(newtdhref("" + valorReal(actividad.getCodigoPoaActividad(), 12), url));
/*  857 */           agregarCelda(row, sheet, contadorFilas, "" + valorReal(actividad.getCodigoPoaActividad(), 12), 27, 27, 27, normal);
/*      */           try {
/*  859 */             ejecucion = valorReal(actividad.getCodigoPoaActividad(), 12) * 100 / actividad.getValorMes12();
/*  860 */           } catch (Exception e) {
/*  861 */             ejecucion = 0;
/*      */           } 
/*  863 */           if (actividad.getValorMes12() > 0 && mes >= 12) {
/*  864 */             eltr2.appendChild(newtdColor("" + ejecucion + " %", color(ejecucion), 2));
/*      */           } else {
/*  866 */             eltr2.appendChild(newtd("" + ejecucion + " %", 2));
/*      */           } 
/*  868 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "" + ejecucion, 26, 26, 27, titulo);
/*  869 */           int resultado = resultado(actividad, mesInicial, mes);
/*  870 */           totalValoresProceso += resultado * 1.0D / actividadesProceso;
/*  871 */           totalValoresGeneral += resultado * 1.0D / numTotalActividades;
/*  872 */           totalArea1 += resultado * 1.0D / actividadesArea;
/*      */           
/*  874 */           eltr.appendChild(newtdColor("" + resultado + " % ", color(resultado)));
/*  875 */           agregarCelda(row, sheet, contadorFilas, "" + resultado, 28, 28, 28, titulo);
/*  876 */           tablaActividades.appendChild(eltr);
/*  877 */           tablaActividades.appendChild(eltr2);
/*  878 */           contadorFilas += 2;
/*      */ 
/*      */ 
/*      */           
/*  882 */           eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  883 */           eltdActividades.appendChild(tablaActividades);
/*  884 */           eltdActividades.setAttributeNode(newAttr("colspan", "2"));
/*  885 */           eltr.appendChild(eltdActividades);
/*  886 */           eltr.setAttributeNode(newAttr("class", "accordian-body collapse sortable act" + pro.getCodigoProceso() + "" + maestro.getArea()));
/*  887 */           tablaAreas.appendChild(eltr);
/*  888 */           eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  889 */           eltdArea.appendChild(tablaAreas);
/*  890 */           eltdArea.setAttributeNode(newAttr("colspan", "2"));
/*  891 */           eltr.appendChild(eltdArea);
/*  892 */           eltr.setAttributeNode(newAttr("class", "accordian-body collapse " + pro.getCodigoProceso()));
/*  893 */           tablaProcesos.appendChild(eltr);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  900 */       eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  901 */       eltdProceso.appendChild(tablaProcesos);
/*  902 */       eltdProceso.setAttributeNode(newAttr("colspan", "2"));
/*  903 */       eltr.appendChild(eltdProceso);
/*  904 */       tabla.appendChild(eltr);
/*      */     } 
/*  906 */     rsM.close();
/*  907 */     eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  908 */     HSSFRow row = sheet.createRow(contadorFilas);
/*      */     try {
/*  910 */       totalValoresGeneral = Math.round(totalValoresGeneral * 100.0D) / 100.0D;
/*  911 */       eltr.appendChild(newtdColor("TOTAL : " + totalValoresGeneral + " %", color(totalValoresGeneral)));
/*  912 */       agregarCelda(row, sheet, contadorFilas, "TOTAL", 0, 0, 30, titulo);
/*  913 */       agregarCelda(row, sheet, contadorFilas, "" + totalValoresGeneral, 31, 31, 31, titulo);
/*  914 */     } catch (Exception e) {
/*      */       
/*  916 */       eltr.appendChild(newtdColor("TOTAL : 0 %", color(0.0D)));
/*  917 */       agregarCelda(row, sheet, contadorFilas, "TOTAL", 0, 0, 30, titulo);
/*  918 */       agregarCelda(row, sheet, contadorFilas, "0", 31, 31, 31, titulo);
/*      */     } 
/*  920 */     contadorFilas++;
/*  921 */     tabla.appendChild(eltr);
/*      */ 
/*      */     
/*  924 */     eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  932 */     eltr.appendChild(newtdhref("Descargar archivo Excel", "VerArchivo.po?ruta=ArchivosPQRSF&archivo=" + toPath));
/*  933 */     tabla.appendChild(eltr);
/*      */ 
/*      */     
/*  936 */     contadorFilas += 4;
/*  937 */     row = sheet.createRow(contadorFilas);
/*  938 */     HSSFCell cell = row.createCell(0);
/*  939 */     cell.setCellValue("Convenciones");
/*      */     
/*  941 */     contadorFilas++;
/*      */     
/*  943 */     row = sheet.createRow(contadorFilas);
/*  944 */     cell = row.createCell(0);
/*  945 */     cell.setCellValue("PROCESO");
/*  946 */     cell.setCellStyle(estiloProceso);
/*  947 */     contadorFilas++;
/*      */     
/*  949 */     row = sheet.createRow(contadorFilas);
/*  950 */     cell = row.createCell(0);
/*  951 */     cell.setCellValue("Área");
/*  952 */     cell.setCellStyle(estiloArea);
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  957 */       FileOutputStream fileOut = new FileOutputStream(toPath);
/*  958 */       wb.write(fileOut);
/*  959 */       fileOut.close();
/*  960 */     } catch (FileNotFoundException e1) {
/*  961 */       Utilidades.grabarLog(e1.getMessage() + ": " + e1.getCause());
/*  962 */       e1.printStackTrace();
/*  963 */     } catch (IOException e) {
/*  964 */       Utilidades.grabarLog("error");
/*  965 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private double totalProceso(PoaMaestroDAO rsM, int proc, int area, int ciclo, int mesInicial, int mes) {
/*  971 */     double total = 0.0D;
/*  972 */     double totalProceso = 0.0D;
/*      */     
/*  974 */     PoaProcesosDAO obProceso = new PoaProcesosDAO();
/*  975 */     PoaProcesosDTO pro = obProceso.cargarRegistro(proc);
/*      */     
/*  977 */     int totalActsProceso = rsM.cantidadActividadesPorObjetivo(area, ciclo, "" + pro.getCodigoProceso(), 0);
/*      */     
/*  979 */     Collection<Integer> codigosM = rsM.cargarCodigosPorObjetivo(area, ciclo, "" + proc, 0);
/*  980 */     Iterator<Integer> iteCodigosM = codigosM.iterator();
/*  981 */     while (iteCodigosM.hasNext()) {
/*  982 */       double totalArea = 0.0D;
/*  983 */       PoaMaestroDTO maestro = rsM.cargarRegistro(((Integer)iteCodigosM.next()).intValue());
/*  984 */       int totalActsArea = rsM.cantidadActividadesPorObjetivo(maestro.getArea(), ciclo, "" + pro.getCodigoProceso(), 0);
/*      */       
/*  986 */       PoaMaestroActividadesDAO rsAct = new PoaMaestroActividadesDAO();
/*  987 */       Collection<PoaMaestroActividadesDTO> actividades = rsAct.cargarTodos(maestro.getCodigoPoa(), 0, 0);
/*  988 */       rsAct.close();
/*      */ 
/*      */       
/*  991 */       Iterator<PoaMaestroActividadesDTO> iteActividades = actividades.iterator();
/*  992 */       while (iteActividades.hasNext()) {
/*      */         
/*  994 */         PoaMaestroActividadesDTO actividad = (PoaMaestroActividadesDTO)iteActividades.next();
/*  995 */         int resultado = resultado(actividad, mesInicial, mes);
/*  996 */         total += resultado * 1.0D / totalActsProceso;
/*      */       } 
/*      */     } 
/*  999 */     return Math.round(total * 100.0D) / 100.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private double totalArea(PoaMaestroDTO maestro, PoaMaestroDAO rsM, PoaProcesosDTO pro, int area, int ciclo, int mesInicial, int mes) {
/* 1006 */     double total = 0.0D;
/* 1007 */     double totalArea = 0.0D;
/*      */     
/* 1009 */     int totalActsArea = rsM.cantidadActividadesPorObjetivo(maestro.getArea(), ciclo, "" + pro.getCodigoProceso(), 0);
/*      */     
/* 1011 */     PoaMaestroActividadesDAO rsAct = new PoaMaestroActividadesDAO();
/* 1012 */     Collection<PoaMaestroActividadesDTO> actividades = rsAct.cargarTodos(maestro.getCodigoPoa(), 0, 0);
/* 1013 */     rsAct.close();
/*      */ 
/*      */     
/* 1016 */     Iterator<PoaMaestroActividadesDTO> iteActividades = actividades.iterator();
/* 1017 */     while (iteActividades.hasNext()) {
/*      */       
/* 1019 */       PoaMaestroActividadesDTO actividad = (PoaMaestroActividadesDTO)iteActividades.next();
/* 1020 */       int resultado = resultado(actividad, mesInicial, mes);
/* 1021 */       total += resultado * 1.0D / totalActsArea;
/*      */     } 
/* 1023 */     return Math.round(total * 100.0D) / 100.0D;
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
/* 1044 */     if (dejarBlanco) {
/* 1045 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1046 */       op.setValue("");
/* 1047 */       op.appendChild(this.pagHTML.createTextNode("TODOS"));
/* 1048 */       combo.appendChild(op);
/*      */     } 
/* 1050 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 1051 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/* 1052 */     rsTGen.close();
/* 1053 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 1054 */     while (iterator.hasNext()) {
/* 1055 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 1056 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1057 */       op.setValue("" + regGeneral.getCodigoS());
/* 1058 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 1059 */       if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
/* 1060 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 1061 */         escogida.setValue("on");
/* 1062 */         op.setAttributeNode(escogida);
/*      */       } 
/* 1064 */       combo.appendChild(op);
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
/* 1081 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 1082 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 1083 */     rs.close();
/* 1084 */     if (dejarBlanco) {
/* 1085 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1086 */       op.setValue("");
/* 1087 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 1088 */       combo.appendChild(op);
/*      */     } 
/* 1090 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 1091 */     while (iterator.hasNext()) {
/* 1092 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 1093 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1094 */       op.setValue("" + reg.getCodigo());
/* 1095 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 1096 */       if (defecto.equals(reg.getCodigo())) {
/* 1097 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 1098 */         escogida.setValue("on");
/* 1099 */         op.setAttributeNode(escogida);
/*      */       } 
/* 1101 */       combo.appendChild(op);
/*      */     } 
/* 1103 */     arr.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void llenarComboMeses(HTMLSelectElement combo, String defecto, boolean dejarBlanco) {
/* 1110 */     if (dejarBlanco) {
/* 1111 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1112 */       op.setValue("");
/* 1113 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 1114 */       combo.appendChild(op);
/*      */     } 
/* 1116 */     for (int i = 1; i <= 12; i++) {
/* 1117 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1118 */       op.setValue("mes" + i);
/* 1119 */       op.appendChild(this.pagHTML.createTextNode(getMes(i)));
/* 1120 */       if (defecto.equals("mes" + i)) {
/* 1121 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 1122 */         escogida.setValue("on");
/* 1123 */         op.setAttributeNode(escogida);
/*      */       } 
/* 1125 */       combo.appendChild(op);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private String getMes(int mes) {
/* 1131 */     switch (mes) {
/*      */       case 1:
/* 1133 */         return "Enero";
/*      */       case 2:
/* 1135 */         return "Febrero";
/*      */       
/*      */       case 3:
/* 1138 */         return "Marzo";
/*      */       case 4:
/* 1140 */         return "Abril";
/*      */       case 5:
/* 1142 */         return "Mayo";
/*      */       case 6:
/* 1144 */         return "Junio";
/*      */       case 7:
/* 1146 */         return "Julio";
/*      */       case 8:
/* 1148 */         return "Agosto";
/*      */       case 9:
/* 1150 */         return "Septiembre";
/*      */       case 10:
/* 1152 */         return "Octubre";
/*      */       case 11:
/* 1154 */         return "Noviembre";
/*      */       case 12:
/* 1156 */         return "Diciembre";
/*      */     } 
/*      */ 
/*      */     
/* 1160 */     return "";
/*      */   }
/*      */   
/*      */   private String getMesNumero(String mes) {
/* 1164 */     if (mes.equals("Enero")) {
/* 1165 */       return "mes1";
/*      */     }
/* 1167 */     if (mes.equals("Febrero")) {
/* 1168 */       return "mes2";
/*      */     }
/* 1170 */     if (mes.equals("Marzo")) {
/* 1171 */       return "mes3";
/*      */     }
/* 1173 */     if (mes.equals("Abril")) {
/* 1174 */       return "mes4";
/*      */     }
/* 1176 */     if (mes.equals("Mayo")) {
/* 1177 */       return "mes5";
/*      */     }
/* 1179 */     if (mes.equals("Junio")) {
/* 1180 */       return "mes6";
/*      */     }
/* 1182 */     if (mes.equals("Julio")) {
/* 1183 */       return "mes7";
/*      */     }
/* 1185 */     if (mes.equals("Agosto")) {
/* 1186 */       return "mes8";
/*      */     }
/* 1188 */     if (mes.equals("Septiembre")) {
/* 1189 */       return "mes9";
/*      */     }
/* 1191 */     if (mes.equals("Octubre")) {
/* 1192 */       return "mes10";
/*      */     }
/* 1194 */     if (mes.equals("Noviembre")) {
/* 1195 */       return "mes11";
/*      */     }
/* 1197 */     if (mes.equals("Diciembre")) {
/* 1198 */       return "mes12";
/*      */     }
/* 1200 */     return "";
/*      */   }
/*      */   
/*      */   private int getValorEsperado(int mes, PoaMaestroActividadesDTO poa) {
/* 1204 */     switch (mes) {
/*      */       case 1:
/* 1206 */         return poa.getValorMes1();
/*      */       case 2:
/* 1208 */         return poa.getValorMes2();
/*      */       
/*      */       case 3:
/* 1211 */         return poa.getValorMes3();
/*      */       case 4:
/* 1213 */         return poa.getValorMes4();
/*      */       case 5:
/* 1215 */         return poa.getValorMes5();
/*      */       case 6:
/* 1217 */         return poa.getValorMes6();
/*      */       case 7:
/* 1219 */         return poa.getValorMes7();
/*      */       case 8:
/* 1221 */         return poa.getValorMes8();
/*      */       case 9:
/* 1223 */         return poa.getValorMes9();
/*      */       case 10:
/* 1225 */         return poa.getValorMes10();
/*      */       case 11:
/* 1227 */         return poa.getValorMes11();
/*      */       case 12:
/* 1229 */         return poa.getValorMes12();
/*      */     } 
/*      */ 
/*      */     
/* 1233 */     return 0;
/*      */   }
/*      */   
/*      */   private int valorReal(int codigoPoa, int mes) {
/*      */     try {
/* 1238 */       PoaLogrosDAO rs = new PoaLogrosDAO();
/* 1239 */       PoaLogrosDTO reg = rs.cargarRegistro(codigoPoa, getMes(mes));
/* 1240 */       rs.close();
/* 1241 */       return reg.getEjecucion();
/* 1242 */     } catch (Exception e) {
/*      */ 
/*      */       
/* 1245 */       return 0;
/*      */     } 
/*      */   }
/*      */   private String urlLogro(int codigoPoa, int mes) {
/*      */     try {
/* 1250 */       PoaLogrosDAO rs = new PoaLogrosDAO();
/* 1251 */       PoaLogrosDTO reg = rs.cargarRegistro(codigoPoa, getMes(mes));
/* 1252 */       rs.close();
/* 1253 */       return "PoaLogros.po?_operacion=V&idLogro=" + reg.getIdLogro();
/*      */     }
/* 1255 */     catch (Exception e) {
/*      */ 
/*      */       
/* 1258 */       return "#";
/*      */     } 
/*      */   }
/*      */   private int[] totalEjecutado(int poaActivad, int mesInicial, int mes) {
/* 1262 */     int totalEjecutado = 0;
/* 1263 */     int[] data = new int[2];
/* 1264 */     for (int i = mesInicial; i <= mes; i++) {
/* 1265 */       totalEjecutado += valorReal(poaActivad, i);
/* 1266 */       if (valorReal(poaActivad, i) > 0) {
/* 1267 */         data[1] = i;
/*      */       }
/*      */     } 
/* 1270 */     data[0] = totalEjecutado;
/* 1271 */     return data;
/*      */   }
/*      */   
/*      */   private int totalEsperado(PoaMaestroActividadesDTO reg, int mesInicial, int mes) {
/* 1275 */     int totalEsperado = 0;
/* 1276 */     switch (mes) {
/*      */       case 12:
/* 1278 */         totalEsperado += reg.getValorMes12();
/* 1279 */         if (mesInicial >= 12) {
/*      */           break;
/*      */         }
/*      */       case 11:
/* 1283 */         totalEsperado += reg.getValorMes11();
/* 1284 */         if (mesInicial >= 11) {
/*      */           break;
/*      */         }
/*      */       case 10:
/* 1288 */         totalEsperado += reg.getValorMes10();
/* 1289 */         if (mesInicial >= 10) {
/*      */           break;
/*      */         }
/*      */       case 9:
/* 1293 */         totalEsperado += reg.getValorMes9();
/* 1294 */         if (mesInicial >= 9) {
/*      */           break;
/*      */         }
/*      */       case 8:
/* 1298 */         totalEsperado += reg.getValorMes8();
/* 1299 */         if (mesInicial >= 8) {
/*      */           break;
/*      */         }
/*      */       case 7:
/* 1303 */         totalEsperado += reg.getValorMes7();
/* 1304 */         if (mesInicial >= 7) {
/*      */           break;
/*      */         }
/*      */       case 6:
/* 1308 */         totalEsperado += reg.getValorMes6();
/* 1309 */         if (mesInicial >= 6) {
/*      */           break;
/*      */         }
/*      */       case 5:
/* 1313 */         totalEsperado += reg.getValorMes5();
/* 1314 */         if (mesInicial >= 5) {
/*      */           break;
/*      */         }
/*      */       case 4:
/* 1318 */         totalEsperado += reg.getValorMes4();
/* 1319 */         if (mesInicial >= 4) {
/*      */           break;
/*      */         }
/*      */       case 3:
/* 1323 */         totalEsperado += reg.getValorMes3();
/* 1324 */         if (mesInicial >= 3) {
/*      */           break;
/*      */         }
/*      */       case 2:
/* 1328 */         totalEsperado += reg.getValorMes2();
/* 1329 */         if (mesInicial >= 2) {
/*      */           break;
/*      */         }
/*      */       case 1:
/* 1333 */         totalEsperado += reg.getValorMes1();
/* 1334 */         if (mesInicial >= 1);
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1341 */     return totalEsperado;
/*      */   }
/*      */   
/*      */   private int resultado(PoaMaestroActividadesDTO reg, int mesInicial, int mes) {
/* 1345 */     int[] data = totalEjecutado(reg.getCodigoPoaActividad(), mesInicial, mes);
/* 1346 */     int totalEsperado = totalEsperado(reg, mesInicial, mes);
/* 1347 */     int totalEjecutado = data[0];
/* 1348 */     if (totalEsperado > 0) {
/* 1349 */       return totalEjecutado * 100 / totalEsperado;
/*      */     }
/* 1351 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   private String color(double valor) {
/* 1356 */     PoaTableroColoresDAO rs = new PoaTableroColoresDAO();
/* 1357 */     Collection<PoaTableroColoresDTO> colores = rs.cargarTodos("");
/* 1358 */     rs.close();
/* 1359 */     Iterator<PoaTableroColoresDTO> iterator = colores.iterator();
/* 1360 */     while (iterator.hasNext()) {
/* 1361 */       PoaTableroColoresDTO color = (PoaTableroColoresDTO)iterator.next();
/* 1362 */       if (valor <= color.getValorFinal() && valor >= color.getValorInicial()) {
/* 1363 */         return color.getColor();
/*      */       }
/*      */     } 
/* 1366 */     return "FFFFFF";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void agregarCelda(HSSFRow row, HSSFSheet sheet, int contadorFilas, String mensaje, int numCelda, int colInicial, int colFinal, HSSFCellStyle style) {
/* 1377 */     HSSFCell cell = row.createCell(numCelda);
/* 1378 */     cell.setCellValue(mensaje);
/* 1379 */     cell.setCellStyle(style);
/* 1380 */     sheet.addMergedRegion(new CellRangeAddress(contadorFilas, contadorFilas, colInicial, colFinal));
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
/* 1395 */   public Color hex2Rgb(String colorStr) { return new Color(Integer.valueOf(colorStr.substring(1, 3), 16).intValue(), Integer.valueOf(colorStr.substring(3, 5), 16).intValue(), Integer.valueOf(colorStr.substring(5, 7), 16).intValue()); }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PoaReporteDetallado.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */