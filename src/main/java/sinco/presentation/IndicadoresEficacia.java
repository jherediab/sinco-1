/*      */ package sinco.presentation;
/*      */ 
/*      */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*      */ import com.lutris.util.KeywordValueException;
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
/*      */ import sinco.presentation.IndicadoresEficacia;
/*      */ import sinco.presentation.IndicadoresEficaciaHTML;
/*      */ import sinco.spec.MenuDO;
/*      */ import sinco.spec.Varios;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class IndicadoresEficacia
/*      */   implements HttpPresentation
/*      */ {
/*      */   private IndicadoresEficaciaHTML pagHTML;
/*      */   
/*      */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*   58 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*   59 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*      */     }
/*      */     
/*   62 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*   63 */     String _operacion = comms.request.getParameter("_operacion");
/*   64 */     if (_operacion == null || _operacion.length() == 0) {
/*   65 */       _operacion = "X";
/*      */     }
/*      */     
/*   68 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*   69 */       creacion(comms);
/*      */     }
/*      */     
/*   72 */     this.pagHTML = (IndicadoresEficaciaHTML)comms.xmlcFactory.create(IndicadoresEficaciaHTML.class);
/*   73 */     permisos(comms);
/*      */ 
/*      */ 
/*      */     
/*   77 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*   78 */       consulta(comms);
/*      */     }
/*   80 */     if (_operacion.equals("tablero")) {
/*   81 */       mostrarTablero(comms);
/*      */     }
/*      */     
/*   84 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*   85 */     comms.response.writeDOM(this.pagHTML);
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
/*   97 */     String _operacion = comms.request.getParameter("_operacion");
/*   98 */     String elUsuario = "" + comms.session.getUser().getName();
/*   99 */     int idLogro = 0;
/*      */     try {
/*  101 */       idLogro = Integer.parseInt(comms.request.getParameter("idLogro"));
/*      */     }
/*  103 */     catch (Exception e) {
/*  104 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idLogro"));
/*      */     } 
/*      */     
/*  107 */     RespuestaBD rta = new RespuestaBD();
/*  108 */     if (_operacion.equals("E")) {
/*  109 */       PoaLogrosDAO rs = new PoaLogrosDAO();
/*  110 */       if (!rs.getEstadoConexion()) {
/*  111 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */       }
/*  113 */       rta = rs.eliminarRegistro(idLogro);
/*  114 */       if (!rta.isRta()) {
/*  115 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaLogros&p1=" + rta.getMensaje()));
/*      */       }
/*  117 */       rs.close();
/*  118 */       String sPagina = "PoaLogros.po?_operacion=X";
/*  119 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*      */     } 
/*  121 */     int codigoPoa = 0;
/*      */     try {
/*  123 */       codigoPoa = Integer.parseInt(comms.request.getParameter("codigoPoa"));
/*      */     }
/*  125 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  128 */     String mes = comms.request.getParameter("mes");
/*  129 */     int ejecucion = 0;
/*      */     try {
/*  131 */       ejecucion = Integer.parseInt(comms.request.getParameter("ejecucion"));
/*      */     }
/*  133 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  136 */     String avances = comms.request.getParameter("avances");
/*  137 */     String logrosYResultados = comms.request.getParameter("logrosYResultados");
/*  138 */     String retrasosDificultades = comms.request.getParameter("retrasosDificultades");
/*  139 */     String estado = comms.request.getParameter("estado");
/*  140 */     PoaLogrosDAO rs = new PoaLogrosDAO();
/*  141 */     if (!rs.getEstadoConexion()) {
/*  142 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */     }
/*  144 */     if (_operacion.equals("C")) {
/*  145 */       rta = rs.crearRegistro(idLogro, codigoPoa, mes, ejecucion, avances, logrosYResultados, retrasosDificultades, estado, elUsuario);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  155 */       idLogro = rta.getSecuencia();
/*      */     } else {
/*      */       
/*  158 */       rta = rs.modificarRegistro(idLogro, codigoPoa, mes, ejecucion, avances, logrosYResultados, retrasosDificultades, estado, elUsuario);
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
/*  169 */     rs.close();
/*  170 */     if (!rta.isRta()) {
/*  171 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaLogros&p1=" + rta.getMensaje()));
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
/*  193 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*      */     
/*  195 */     Varios oVarios = new Varios();
/*  196 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Poa_PoaLogrosAct");
/*  197 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Poa_PoaLogrosDel");
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
/*  219 */     if (!vista.equals("tablero")) {
/*  220 */       HTMLElement sel = this.pagHTML.getElementDivTableroControl();
/*  221 */       sel.getParentNode().removeChild(sel);
/*      */     } 
/*  223 */     if (!vista.equals("consulta")) {
/*  224 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/*  225 */       sel.getParentNode().removeChild(sel);
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
/*  239 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/*  240 */     atrib.setValue(valor);
/*  241 */     return atrib;
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
/*  254 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  255 */     Element enlace = this.pagHTML.createElement("a");
/*  256 */     Node hijo = this.pagHTML.createTextNode(contenido);
/*  257 */     enlace.appendChild(hijo);
/*  258 */     Attr donde = this.pagHTML.createAttribute("href");
/*  259 */     donde.setValue(vinculo);
/*  260 */     enlace.setAttributeNode(donde);
/*  261 */     td.appendChild(enlace);
/*  262 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  263 */     return td;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private HTMLElement newtd(String contenido) {
/*  273 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  274 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  275 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  276 */     return td;
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
/*  287 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  288 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  289 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  290 */     td.setAttributeNode(newAttr("colspan", "" + colspan));
/*  291 */     return td;
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
/*  302 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("th");
/*  303 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  304 */     td.setAttributeNode(newAttr("class", "" + clase));
/*  305 */     td.setAttributeNode(newAttr("colspan", "" + colspan));
/*  306 */     return td;
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
/*  318 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  319 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  320 */     td.setAttributeNode(newAttr("bgcolor", "#" + color));
/*  321 */     return td;
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
/*  332 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  333 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  334 */     td.setAttributeNode(newAttr("bgcolor", "#" + color));
/*  335 */     td.setAttributeNode(newAttr("colspan", "" + colspan));
/*  336 */     return td;
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
/*  347 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  348 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  349 */     td.setAttributeNode(newAttr("class", clase));
/*  350 */     td.setAttributeNode(newAttr("colspan", "30"));
/*  351 */     return td;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void consulta(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  357 */     activarVista("consulta");
/*  358 */     HTMLSelectElement combo = this.pagHTML.getElementFarea();
/*  359 */     llenarCombo(combo, "UNIDADES_DEPENDENCIA", "CODIGO", "DESCRIPCION", "1=1", "", true);
/*  360 */     combo = this.pagHTML.getElementFciclo();
/*  361 */     llenarCombo(combo, "POA_CICLOS", "CODIGO_CICLO", "DESCRIPCION", "1=1", "", false);
/*  362 */     combo = this.pagHTML.getElementFproceso();
/*  363 */     llenarCombo(combo, "POA_PROCESOS", "CODIGO_PROCESO", "DESCRIPCION", "1=1", "", true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void mostrarTablero(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  370 */     activarVista("tablero");
/*  371 */     int area = 0;
/*      */     try {
/*  373 */       area = Integer.parseInt(comms.request.getParameter("farea"));
/*      */     }
/*  375 */     catch (Exception e) {}
/*      */ 
/*      */     
/*  378 */     int ciclo = 0;
/*      */     try {
/*  380 */       ciclo = Integer.parseInt(comms.request.getParameter("fciclo"));
/*      */     }
/*  382 */     catch (Exception e) {}
/*      */     
/*  384 */     String proceso = comms.request.getParameter("fproceso");
/*      */     
/*  386 */     PoaMaestroDAO rsM = new PoaMaestroDAO();
/*      */     
/*  388 */     HTMLTableSectionElement tabla = this.pagHTML.getElementTablaTablero();
/*  389 */     int contadorActividadesGeneral = 0;
/*  390 */     double totalValoresGeneral = 0.0D;
/*  391 */     HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*      */     
/*  393 */     Collection<Integer> procesos = rsM.cargarProcesos(area, ciclo, proceso);
/*  394 */     int numTotalActividades = rsM.contarActividades(area, ciclo, proceso);
/*      */ 
/*      */     
/*  397 */     HSSFWorkbook wb = new HSSFWorkbook();
/*      */ 
/*      */ 
/*      */     
/*  401 */     HSSFSheet sheet = wb.createSheet("Indicadores eficacia");
/*      */ 
/*      */ 
/*      */     
/*  405 */     Calendar fecha = Calendar.getInstance();
/*  406 */     String elUsuario = "" + comms.session.getUser().getName();
/*  407 */     String nombreArchivo = "/eficacia" + elUsuario + fecha.get(1) + (fecha.get(2) + 1) + fecha.get(5) + fecha.get(11) + fecha.get(12) + fecha.get(13);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  413 */     String toPath = System.getProperty("user.home") + nombreArchivo + ".xls";
/*      */ 
/*      */     
/*  416 */     HSSFCellStyle estiloProceso = wb.createCellStyle();
/*  417 */     HSSFCellStyle estiloArea = wb.createCellStyle();
/*  418 */     HSSFCellStyle estiloAlto = wb.createCellStyle();
/*  419 */     HSSFCellStyle estiloMedio = wb.createCellStyle();
/*  420 */     HSSFCellStyle estiloBajo = wb.createCellStyle();
/*  421 */     HSSFCellStyle titulo = wb.createCellStyle();
/*  422 */     HSSFCellStyle normal = wb.createCellStyle();
/*      */ 
/*      */     
/*  425 */     estiloProceso.setFillForegroundColor((short)55);
/*  426 */     estiloProceso.setAlignment((short)2);
/*  427 */     estiloProceso.setFillPattern((short)1);
/*      */ 
/*      */     
/*  430 */     estiloArea.setFillForegroundColor((short)22);
/*  431 */     estiloArea.setFillPattern((short)1);
/*      */     
/*  433 */     titulo.setAlignment((short)2);
/*      */     
/*  435 */     Utilidades.grabarLog(toPath);
/*      */     
/*  437 */     int contadorFilas = 0;
/*  438 */     Iterator<Integer> iteProcesos = procesos.iterator();
/*  439 */     while (iteProcesos.hasNext()) {
/*  440 */       int proc = ((Integer)iteProcesos.next()).intValue();
/*  441 */       int actividadesProceso = rsM.contarActividades(area, ciclo, "" + proc);
/*  442 */       Collection<PoaMaestroDTO> maestros = rsM.cargarRegistros(area, ciclo, proc);
/*      */       
/*  444 */       Iterator<PoaMaestroDTO> iteMaestro = maestros.iterator();
/*      */       
/*  446 */       PoaProcesosDAO obProceso = new PoaProcesosDAO();
/*  447 */       PoaProcesosDTO pro = obProceso.cargarRegistro(proc);
/*      */       
/*  449 */       eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  450 */       eltr.appendChild(newtdTitle("Proceso: " + pro.getDescripcion(), "ca3"));
/*  451 */       tabla.appendChild(eltr);
/*      */ 
/*      */       
/*  454 */       HSSFRow row = sheet.createRow(contadorFilas);
/*  455 */       HSSFCell cell = row.createCell(0);
/*  456 */       cell.setCellValue(pro.getDescripcion());
/*  457 */       cell.setCellStyle(estiloProceso);
/*  458 */       sheet.addMergedRegion(new CellRangeAddress(contadorFilas, contadorFilas, 0, 31));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  464 */       contadorFilas++;
/*      */       
/*  466 */       double totalValoresProceso = 0.0D;
/*  467 */       while (iteMaestro.hasNext()) {
/*  468 */         int contadorActividadesProceso = 0;
/*  469 */         PoaMaestroDTO maestro = (PoaMaestroDTO)iteMaestro.next();
/*      */         
/*  471 */         eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  472 */         eltr.appendChild(newtdTitle("Área: " + maestro.getNombreArea(), "ca4"));
/*  473 */         tabla.appendChild(eltr);
/*      */ 
/*      */         
/*  476 */         row = sheet.createRow(contadorFilas);
/*  477 */         cell = row.createCell(0);
/*  478 */         cell.setCellValue(maestro.getNombreArea());
/*  479 */         cell.setCellStyle(estiloArea);
/*  480 */         sheet.addMergedRegion(new CellRangeAddress(contadorFilas, contadorFilas, 0, 30));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  486 */         contadorFilas++;
/*  487 */         row = sheet.createRow(contadorFilas);
/*  488 */         eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  489 */         eltr.appendChild(newth("Actividad", "cf3", 1));
/*      */         
/*  491 */         agregarCelda(row, sheet, contadorFilas, "Actividad", 0, 0, 3, titulo);
/*  492 */         eltr.appendChild(newth("Enero", "cf4", 2));
/*      */         
/*  494 */         agregarCelda(row, sheet, contadorFilas, "Enero", 4, 4, 5, titulo);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  499 */         eltr.appendChild(newth("Febrero", "cf3", 2));
/*  500 */         agregarCelda(row, sheet, contadorFilas, "Febrero", 6, 6, 7, titulo);
/*  501 */         eltr.appendChild(newth("Marzo", "cf4", 2));
/*  502 */         agregarCelda(row, sheet, contadorFilas, "Marzo", 8, 8, 9, titulo);
/*  503 */         eltr.appendChild(newth("Abril", "cf3", 2));
/*  504 */         agregarCelda(row, sheet, contadorFilas, "Abril", 10, 10, 11, titulo);
/*  505 */         eltr.appendChild(newth("Mayo", "cf4", 2));
/*  506 */         agregarCelda(row, sheet, contadorFilas, "Mayo", 12, 12, 13, titulo);
/*  507 */         eltr.appendChild(newth("Junio", "cf3", 2));
/*  508 */         agregarCelda(row, sheet, contadorFilas, "Junio", 14, 14, 15, titulo);
/*  509 */         eltr.appendChild(newth("Julio", "cf4", 2));
/*  510 */         agregarCelda(row, sheet, contadorFilas, "Julio", 16, 16, 17, titulo);
/*  511 */         eltr.appendChild(newth("Agosto", "cf3", 2));
/*  512 */         agregarCelda(row, sheet, contadorFilas, "Agosto", 18, 18, 19, titulo);
/*  513 */         eltr.appendChild(newth("Septiembre", "cf4", 2));
/*  514 */         agregarCelda(row, sheet, contadorFilas, "Septiembre", 20, 20, 21, titulo);
/*  515 */         eltr.appendChild(newth("Octubre", "cf3", 2));
/*  516 */         agregarCelda(row, sheet, contadorFilas, "Octubre", 22, 22, 23, titulo);
/*  517 */         eltr.appendChild(newth("Noviembre", "cf4", 2));
/*  518 */         agregarCelda(row, sheet, contadorFilas, "Noviembre", 24, 24, 25, titulo);
/*  519 */         eltr.appendChild(newth("Diciembre", "cf3", 2));
/*  520 */         agregarCelda(row, sheet, contadorFilas, "Diciembre", 26, 26, 27, titulo);
/*  521 */         tabla.appendChild(eltr);
/*  522 */         contadorFilas++;
/*      */ 
/*      */         
/*  525 */         row = sheet.createRow(contadorFilas);
/*  526 */         eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  527 */         eltr.appendChild(newth("", "cf3", 1));
/*  528 */         eltr.appendChild(newth("Es", "cf4", 1));
/*  529 */         agregarCelda(row, sheet, contadorFilas, "Estimado", 4, 4, 4, titulo);
/*  530 */         eltr.appendChild(newth("Ej", "cf3", 1));
/*  531 */         agregarCelda(row, sheet, contadorFilas, "Ejecutado", 5, 5, 5, titulo);
/*  532 */         eltr.appendChild(newth("Es", "cf4", 1));
/*  533 */         agregarCelda(row, sheet, contadorFilas, "Estimado", 6, 6, 6, titulo);
/*  534 */         eltr.appendChild(newth("Ej", "cf3", 1));
/*  535 */         agregarCelda(row, sheet, contadorFilas, "Ejecutado", 7, 7, 7, titulo);
/*  536 */         eltr.appendChild(newth("Es", "cf4", 1));
/*  537 */         agregarCelda(row, sheet, contadorFilas, "Estimado", 8, 8, 8, titulo);
/*  538 */         eltr.appendChild(newth("Ej", "cf3", 1));
/*  539 */         agregarCelda(row, sheet, contadorFilas, "Ejecutado", 9, 9, 9, titulo);
/*  540 */         eltr.appendChild(newth("Es", "cf4", 1));
/*  541 */         agregarCelda(row, sheet, contadorFilas, "Estimado", 10, 10, 10, titulo);
/*  542 */         eltr.appendChild(newth("Ej", "cf3", 1));
/*  543 */         agregarCelda(row, sheet, contadorFilas, "Ejecutado", 11, 11, 11, titulo);
/*  544 */         eltr.appendChild(newth("Es", "cf4", 1));
/*  545 */         agregarCelda(row, sheet, contadorFilas, "Estimado", 12, 12, 12, titulo);
/*  546 */         eltr.appendChild(newth("Ej", "cf3", 1));
/*  547 */         agregarCelda(row, sheet, contadorFilas, "Ejecutado", 13, 13, 13, titulo);
/*  548 */         eltr.appendChild(newth("Es", "cf4", 1));
/*  549 */         agregarCelda(row, sheet, contadorFilas, "Estimado", 14, 14, 14, titulo);
/*  550 */         eltr.appendChild(newth("Ej", "cf3", 1));
/*  551 */         agregarCelda(row, sheet, contadorFilas, "Ejecutado", 15, 15, 15, titulo);
/*  552 */         eltr.appendChild(newth("Es", "cf4", 1));
/*  553 */         agregarCelda(row, sheet, contadorFilas, "Estimado", 16, 16, 16, titulo);
/*  554 */         eltr.appendChild(newth("Ej", "cf3", 1));
/*  555 */         agregarCelda(row, sheet, contadorFilas, "Ejecutado", 17, 17, 17, titulo);
/*  556 */         eltr.appendChild(newth("Es", "cf4", 1));
/*  557 */         agregarCelda(row, sheet, contadorFilas, "Estimado", 18, 18, 18, titulo);
/*  558 */         eltr.appendChild(newth("Ej", "cf3", 1));
/*  559 */         agregarCelda(row, sheet, contadorFilas, "Ejecutado", 19, 19, 19, titulo);
/*  560 */         eltr.appendChild(newth("Es", "cf4", 1));
/*  561 */         agregarCelda(row, sheet, contadorFilas, "Estimado", 20, 20, 20, titulo);
/*  562 */         eltr.appendChild(newth("Ej", "cf3", 1));
/*  563 */         agregarCelda(row, sheet, contadorFilas, "Ejecutado", 21, 21, 21, titulo);
/*  564 */         eltr.appendChild(newth("Es", "cf4", 1));
/*  565 */         agregarCelda(row, sheet, contadorFilas, "Estimado", 22, 22, 22, titulo);
/*  566 */         eltr.appendChild(newth("Ej", "cf3", 1));
/*  567 */         agregarCelda(row, sheet, contadorFilas, "Ejecutado", 23, 23, 23, titulo);
/*  568 */         eltr.appendChild(newth("Es", "cf4", 1));
/*  569 */         agregarCelda(row, sheet, contadorFilas, "Estimado", 24, 24, 24, titulo);
/*  570 */         eltr.appendChild(newth("Ej", "cf3", 1));
/*  571 */         agregarCelda(row, sheet, contadorFilas, "Ejecutado", 25, 25, 25, titulo);
/*  572 */         eltr.appendChild(newth("Es", "cf4", 1));
/*  573 */         agregarCelda(row, sheet, contadorFilas, "Estimado", 26, 26, 26, titulo);
/*  574 */         eltr.appendChild(newth("Ej", "cf3", 1));
/*  575 */         agregarCelda(row, sheet, contadorFilas, "Ejecutado", 27, 27, 27, titulo);
/*      */         
/*  577 */         contadorFilas++;
/*      */         
/*  579 */         tabla.appendChild(eltr);
/*      */         
/*  581 */         PoaMaestroActividadesDAO rsAct = new PoaMaestroActividadesDAO();
/*  582 */         Collection<PoaMaestroActividadesDTO> actividades = rsAct.cargarTodos(maestro.getCodigoPoa(), 0);
/*  583 */         rsAct.close();
/*      */ 
/*      */         
/*  586 */         Iterator<PoaMaestroActividadesDTO> iteActividades = actividades.iterator();
/*  587 */         while (iteActividades.hasNext()) {
/*      */           
/*  589 */           int ejecucion = 0;
/*  590 */           contadorActividadesGeneral++;
/*  591 */           contadorActividadesProceso++;
/*  592 */           PoaMaestroActividadesDTO actividad = (PoaMaestroActividadesDTO)iteActividades.next();
/*  593 */           eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  594 */           HTMLElement eltr2 = (HTMLElement)this.pagHTML.createElement("tr");
/*  595 */           eltr.appendChild(newtd("" + actividad.getNombreActividad()));
/*  596 */           eltr2.appendChild(newtd("% Ejecución"));
/*      */ 
/*      */           
/*  599 */           row = sheet.createRow(contadorFilas);
/*  600 */           HSSFRow rowEjecucion = sheet.createRow(contadorFilas + 1);
/*  601 */           agregarCelda(row, sheet, contadorFilas, actividad.getNombreActividad(), 0, 0, 3, normal);
/*      */           
/*  603 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "% Ejecución", 0, 0, 3, normal);
/*      */           
/*  605 */           eltr.appendChild(newtd("" + actividad.getValorMes1()));
/*  606 */           agregarCelda(row, sheet, contadorFilas, "" + actividad.getValorMes1(), 4, 4, 4, normal);
/*  607 */           String url = urlLogro(actividad.getCodigoPoaActividad(), 1);
/*  608 */           eltr.appendChild(newtdhref("" + valorReal(actividad.getCodigoPoaActividad(), 1), url));
/*  609 */           agregarCelda(row, sheet, contadorFilas, "" + valorReal(actividad.getCodigoPoaActividad(), 1), 5, 5, 5, normal);
/*      */           try {
/*  611 */             ejecucion = valorReal(actividad.getCodigoPoaActividad(), 1) * 100 / actividad.getValorMes1();
/*  612 */           } catch (Exception e) {
/*  613 */             ejecucion = 0;
/*      */           } 
/*  615 */           if (actividad.getValorMes1() > 0 && !url.equals("#")) {
/*  616 */             eltr2.appendChild(newtdColor("" + ejecucion + " %", color(ejecucion), 2));
/*      */           } else {
/*  618 */             eltr2.appendChild(newtd("" + ejecucion + " %", 2));
/*      */           } 
/*  620 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "" + ejecucion, 4, 4, 5, titulo);
/*  621 */           eltr.appendChild(newtd("" + actividad.getValorMes2()));
/*  622 */           agregarCelda(row, sheet, contadorFilas, "" + actividad.getValorMes2(), 6, 6, 6, normal);
/*  623 */           url = urlLogro(actividad.getCodigoPoaActividad(), 2);
/*  624 */           eltr.appendChild(newtdhref("" + valorReal(actividad.getCodigoPoaActividad(), 2), url));
/*  625 */           agregarCelda(row, sheet, contadorFilas, "" + valorReal(actividad.getCodigoPoaActividad(), 2), 7, 7, 7, normal);
/*      */           try {
/*  627 */             ejecucion = valorReal(actividad.getCodigoPoaActividad(), 2) * 100 / actividad.getValorMes2();
/*  628 */           } catch (Exception e) {
/*  629 */             ejecucion = 0;
/*      */           } 
/*  631 */           if (actividad.getValorMes2() > 0 && !url.equals("#")) {
/*  632 */             eltr2.appendChild(newtdColor("" + ejecucion + " %", color(ejecucion), 2));
/*      */           } else {
/*  634 */             eltr2.appendChild(newtd("" + ejecucion + " %", 2));
/*      */           } 
/*  636 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "" + ejecucion, 6, 6, 7, titulo);
/*  637 */           eltr.appendChild(newtd("" + actividad.getValorMes3()));
/*  638 */           agregarCelda(row, sheet, contadorFilas, "" + actividad.getValorMes3(), 8, 8, 8, normal);
/*  639 */           url = urlLogro(actividad.getCodigoPoaActividad(), 3);
/*  640 */           eltr.appendChild(newtdhref("" + valorReal(actividad.getCodigoPoaActividad(), 3), url));
/*  641 */           agregarCelda(row, sheet, contadorFilas, "" + valorReal(actividad.getCodigoPoaActividad(), 3), 9, 9, 9, normal);
/*      */           try {
/*  643 */             ejecucion = valorReal(actividad.getCodigoPoaActividad(), 3) * 100 / actividad.getValorMes3();
/*  644 */           } catch (Exception e) {
/*  645 */             ejecucion = 0;
/*      */           } 
/*  647 */           if (actividad.getValorMes3() > 0 && !url.equals("#")) {
/*  648 */             eltr2.appendChild(newtdColor("" + ejecucion + " %", color(ejecucion), 2));
/*      */           } else {
/*  650 */             eltr2.appendChild(newtd("" + ejecucion + " %", 2));
/*      */           } 
/*  652 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "" + ejecucion, 8, 8, 9, titulo);
/*  653 */           eltr.appendChild(newtd("" + actividad.getValorMes4()));
/*  654 */           agregarCelda(row, sheet, contadorFilas, "" + actividad.getValorMes4(), 10, 10, 10, normal);
/*  655 */           url = urlLogro(actividad.getCodigoPoaActividad(), 4);
/*  656 */           eltr.appendChild(newtdhref("" + valorReal(actividad.getCodigoPoaActividad(), 4), url));
/*  657 */           agregarCelda(row, sheet, contadorFilas, "" + valorReal(actividad.getCodigoPoaActividad(), 4), 11, 11, 11, normal);
/*      */           try {
/*  659 */             ejecucion = valorReal(actividad.getCodigoPoaActividad(), 4) * 100 / actividad.getValorMes4();
/*  660 */           } catch (Exception e) {
/*  661 */             ejecucion = 0;
/*      */           } 
/*  663 */           if (actividad.getValorMes4() > 0 && !url.equals("#")) {
/*  664 */             eltr2.appendChild(newtdColor("" + ejecucion + " %", color(ejecucion), 2));
/*      */           } else {
/*  666 */             eltr2.appendChild(newtd("" + ejecucion + " %", 2));
/*      */           } 
/*  668 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "" + ejecucion, 10, 10, 11, titulo);
/*  669 */           eltr.appendChild(newtd("" + actividad.getValorMes5()));
/*  670 */           agregarCelda(row, sheet, contadorFilas, "" + actividad.getValorMes5(), 12, 12, 12, normal);
/*  671 */           url = urlLogro(actividad.getCodigoPoaActividad(), 5);
/*  672 */           eltr.appendChild(newtdhref("" + valorReal(actividad.getCodigoPoaActividad(), 5), url));
/*  673 */           agregarCelda(row, sheet, contadorFilas, "" + valorReal(actividad.getCodigoPoaActividad(), 5), 13, 13, 13, normal);
/*      */           try {
/*  675 */             ejecucion = valorReal(actividad.getCodigoPoaActividad(), 5) * 100 / actividad.getValorMes5();
/*  676 */           } catch (Exception e) {
/*  677 */             ejecucion = 0;
/*      */           } 
/*  679 */           if (actividad.getValorMes5() > 0 && !url.equals("#")) {
/*  680 */             eltr2.appendChild(newtdColor("" + ejecucion + " %", color(ejecucion), 2));
/*      */           } else {
/*  682 */             eltr2.appendChild(newtd("" + ejecucion + " %", 2));
/*      */           } 
/*  684 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "" + ejecucion, 12, 12, 13, titulo);
/*  685 */           eltr.appendChild(newtd("" + actividad.getValorMes6()));
/*  686 */           agregarCelda(row, sheet, contadorFilas, "" + actividad.getValorMes6(), 14, 14, 14, normal);
/*  687 */           url = urlLogro(actividad.getCodigoPoaActividad(), 6);
/*  688 */           eltr.appendChild(newtdhref("" + valorReal(actividad.getCodigoPoaActividad(), 6), url));
/*  689 */           agregarCelda(row, sheet, contadorFilas, "" + valorReal(actividad.getCodigoPoaActividad(), 6), 15, 15, 15, normal);
/*      */           try {
/*  691 */             ejecucion = valorReal(actividad.getCodigoPoaActividad(), 6) * 100 / actividad.getValorMes6();
/*  692 */           } catch (Exception e) {
/*  693 */             ejecucion = 0;
/*      */           } 
/*  695 */           if (actividad.getValorMes6() > 0 && !url.equals("#")) {
/*  696 */             eltr2.appendChild(newtdColor("" + ejecucion + " %", color(ejecucion), 2));
/*      */           } else {
/*  698 */             eltr2.appendChild(newtd("" + ejecucion + " %", 2));
/*      */           } 
/*  700 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "" + ejecucion, 14, 14, 15, titulo);
/*  701 */           eltr.appendChild(newtd("" + actividad.getValorMes7()));
/*  702 */           agregarCelda(row, sheet, contadorFilas, "" + actividad.getValorMes7(), 16, 16, 16, normal);
/*  703 */           url = urlLogro(actividad.getCodigoPoaActividad(), 7);
/*  704 */           eltr.appendChild(newtdhref("" + valorReal(actividad.getCodigoPoaActividad(), 7), url));
/*  705 */           agregarCelda(row, sheet, contadorFilas, "" + valorReal(actividad.getCodigoPoaActividad(), 7), 17, 17, 17, normal);
/*      */           try {
/*  707 */             ejecucion = valorReal(actividad.getCodigoPoaActividad(), 7) * 100 / actividad.getValorMes7();
/*  708 */           } catch (Exception e) {
/*  709 */             ejecucion = 0;
/*      */           } 
/*  711 */           if (actividad.getValorMes7() > 0 && !url.equals("#")) {
/*  712 */             eltr2.appendChild(newtdColor("" + ejecucion + " %", color(ejecucion), 2));
/*      */           } else {
/*  714 */             eltr2.appendChild(newtd("" + ejecucion + " %", 2));
/*      */           } 
/*  716 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "" + ejecucion, 16, 16, 17, titulo);
/*  717 */           eltr.appendChild(newtd("" + actividad.getValorMes8()));
/*  718 */           agregarCelda(row, sheet, contadorFilas, "" + actividad.getValorMes8(), 18, 18, 18, normal);
/*  719 */           url = urlLogro(actividad.getCodigoPoaActividad(), 8);
/*  720 */           eltr.appendChild(newtdhref("" + valorReal(actividad.getCodigoPoaActividad(), 8), url));
/*  721 */           agregarCelda(row, sheet, contadorFilas, "" + valorReal(actividad.getCodigoPoaActividad(), 8), 19, 19, 19, normal);
/*      */           try {
/*  723 */             ejecucion = valorReal(actividad.getCodigoPoaActividad(), 8) * 100 / actividad.getValorMes8();
/*  724 */           } catch (Exception e) {
/*  725 */             ejecucion = 0;
/*      */           } 
/*  727 */           if (actividad.getValorMes8() > 0 && !url.equals("#")) {
/*  728 */             eltr2.appendChild(newtdColor("" + ejecucion + " %", color(ejecucion), 2));
/*      */           } else {
/*  730 */             eltr2.appendChild(newtd("" + ejecucion + " %", 2));
/*      */           } 
/*  732 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "" + ejecucion, 18, 18, 19, titulo);
/*  733 */           eltr.appendChild(newtd("" + actividad.getValorMes9()));
/*  734 */           agregarCelda(row, sheet, contadorFilas, "" + actividad.getValorMes9(), 20, 20, 20, normal);
/*  735 */           url = urlLogro(actividad.getCodigoPoaActividad(), 9);
/*  736 */           eltr.appendChild(newtdhref("" + valorReal(actividad.getCodigoPoaActividad(), 9), url));
/*  737 */           agregarCelda(row, sheet, contadorFilas, "" + valorReal(actividad.getCodigoPoaActividad(), 9), 21, 21, 21, normal);
/*      */           try {
/*  739 */             ejecucion = valorReal(actividad.getCodigoPoaActividad(), 9) * 100 / actividad.getValorMes9();
/*  740 */           } catch (Exception e) {
/*  741 */             ejecucion = 0;
/*      */           } 
/*  743 */           if (actividad.getValorMes9() > 0 && !url.equals("#")) {
/*  744 */             eltr2.appendChild(newtdColor("" + ejecucion + " %", color(ejecucion), 2));
/*      */           } else {
/*  746 */             eltr2.appendChild(newtd("" + ejecucion + " %", 2));
/*      */           } 
/*  748 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "" + ejecucion, 20, 20, 21, titulo);
/*  749 */           eltr.appendChild(newtd("" + actividad.getValorMes10()));
/*  750 */           agregarCelda(row, sheet, contadorFilas, "" + actividad.getValorMes10(), 22, 22, 22, normal);
/*  751 */           url = urlLogro(actividad.getCodigoPoaActividad(), 10);
/*  752 */           eltr.appendChild(newtdhref("" + valorReal(actividad.getCodigoPoaActividad(), 10), url));
/*  753 */           agregarCelda(row, sheet, contadorFilas, "" + valorReal(actividad.getCodigoPoaActividad(), 10), 23, 23, 23, normal);
/*      */           try {
/*  755 */             ejecucion = valorReal(actividad.getCodigoPoaActividad(), 10) * 100 / actividad.getValorMes10();
/*  756 */           } catch (Exception e) {
/*  757 */             ejecucion = 0;
/*      */           } 
/*  759 */           if (actividad.getValorMes10() > 0 && !url.equals("#")) {
/*  760 */             eltr2.appendChild(newtdColor("" + ejecucion + " %", color(ejecucion), 2));
/*      */           } else {
/*  762 */             eltr2.appendChild(newtd("" + ejecucion + " %", 2));
/*      */           } 
/*  764 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "" + ejecucion, 22, 22, 23, titulo);
/*  765 */           eltr.appendChild(newtd("" + actividad.getValorMes11()));
/*  766 */           agregarCelda(row, sheet, contadorFilas, "" + actividad.getValorMes11(), 24, 24, 24, normal);
/*  767 */           url = urlLogro(actividad.getCodigoPoaActividad(), 11);
/*  768 */           eltr.appendChild(newtdhref("" + valorReal(actividad.getCodigoPoaActividad(), 11), url));
/*  769 */           agregarCelda(row, sheet, contadorFilas, "" + valorReal(actividad.getCodigoPoaActividad(), 11), 25, 25, 25, normal);
/*      */           try {
/*  771 */             ejecucion = valorReal(actividad.getCodigoPoaActividad(), 11) * 100 / actividad.getValorMes11();
/*  772 */           } catch (Exception e) {
/*  773 */             ejecucion = 0;
/*      */           } 
/*  775 */           if (actividad.getValorMes11() > 0 && !url.equals("#")) {
/*  776 */             eltr2.appendChild(newtdColor("" + ejecucion + " %", color(ejecucion), 2));
/*      */           } else {
/*  778 */             eltr2.appendChild(newtd("" + ejecucion + " %", 2));
/*      */           } 
/*  780 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "" + ejecucion, 24, 24, 25, titulo);
/*  781 */           eltr.appendChild(newtd("" + actividad.getValorMes12()));
/*  782 */           agregarCelda(row, sheet, contadorFilas, "" + actividad.getValorMes12(), 26, 26, 26, normal);
/*  783 */           url = urlLogro(actividad.getCodigoPoaActividad(), 12);
/*  784 */           eltr.appendChild(newtdhref("" + valorReal(actividad.getCodigoPoaActividad(), 12), url));
/*  785 */           agregarCelda(row, sheet, contadorFilas, "" + valorReal(actividad.getCodigoPoaActividad(), 12), 27, 27, 27, normal);
/*      */           try {
/*  787 */             ejecucion = valorReal(actividad.getCodigoPoaActividad(), 12) * 100 / actividad.getValorMes12();
/*  788 */           } catch (Exception e) {
/*  789 */             ejecucion = 0;
/*      */           } 
/*  791 */           if (actividad.getValorMes12() > 0 && !url.equals("#")) {
/*  792 */             eltr2.appendChild(newtdColor("" + ejecucion + " %", color(ejecucion), 2));
/*      */           } else {
/*  794 */             eltr2.appendChild(newtd("" + ejecucion + " %", 2));
/*      */           } 
/*  796 */           agregarCelda(rowEjecucion, sheet, contadorFilas + 1, "" + ejecucion, 26, 26, 27, titulo);
/*  797 */           tabla.appendChild(eltr);
/*  798 */           tabla.appendChild(eltr2);
/*  799 */           contadorFilas += 2;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  804 */     rsM.close();
/*      */     
/*  806 */     eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  807 */     eltr.appendChild(newtdhref("Descargar archivo Excel", "VerArchivo.po?ruta=ArchivosPQRSF&archivo=" + toPath));
/*  808 */     tabla.appendChild(eltr);
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  813 */       FileOutputStream fileOut = new FileOutputStream(toPath);
/*  814 */       wb.write(fileOut);
/*  815 */       fileOut.close();
/*  816 */     } catch (FileNotFoundException e1) {
/*  817 */       Utilidades.grabarLog(e1.getMessage() + ": " + e1.getCause());
/*  818 */       e1.printStackTrace();
/*  819 */     } catch (IOException e) {
/*  820 */       Utilidades.grabarLog("error");
/*  821 */       e.printStackTrace();
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
/*      */   private void llenarCombo(HTMLSelectElement combo, String tabla, String codigo, String descripcion, String condicion, String defecto, boolean dejarBlanco) {
/*  845 */     if (dejarBlanco) {
/*  846 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  847 */       op.setValue("");
/*  848 */       op.appendChild(this.pagHTML.createTextNode(""));
/*  849 */       combo.appendChild(op);
/*      */     } 
/*  851 */     TGeneralDAO rsTGen = new TGeneralDAO();
/*  852 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/*  853 */     rsTGen.close();
/*  854 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/*  855 */     while (iterator.hasNext()) {
/*  856 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/*  857 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  858 */       op.setValue("" + regGeneral.getCodigoS());
/*  859 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/*  860 */       if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
/*  861 */         Attr escogida = this.pagHTML.createAttribute("selected");
/*  862 */         escogida.setValue("on");
/*  863 */         op.setAttributeNode(escogida);
/*      */       } 
/*  865 */       combo.appendChild(op);
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
/*  882 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/*  883 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/*  884 */     rs.close();
/*  885 */     if (dejarBlanco) {
/*  886 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  887 */       op.setValue("");
/*  888 */       op.appendChild(this.pagHTML.createTextNode(""));
/*  889 */       combo.appendChild(op);
/*      */     } 
/*  891 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/*  892 */     while (iterator.hasNext()) {
/*  893 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/*  894 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  895 */       op.setValue("" + reg.getCodigo());
/*  896 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/*  897 */       if (defecto.equals(reg.getCodigo())) {
/*  898 */         Attr escogida = this.pagHTML.createAttribute("selected");
/*  899 */         escogida.setValue("on");
/*  900 */         op.setAttributeNode(escogida);
/*      */       } 
/*  902 */       combo.appendChild(op);
/*      */     } 
/*  904 */     arr.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void llenarComboMeses(HTMLSelectElement combo, String defecto, boolean dejarBlanco) {
/*  911 */     if (dejarBlanco) {
/*  912 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  913 */       op.setValue("");
/*  914 */       op.appendChild(this.pagHTML.createTextNode(""));
/*  915 */       combo.appendChild(op);
/*      */     } 
/*  917 */     for (int i = 1; i <= 12; i++) {
/*  918 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  919 */       op.setValue("mes" + i);
/*  920 */       op.appendChild(this.pagHTML.createTextNode(getMes(i)));
/*  921 */       if (defecto.equals("mes" + i)) {
/*  922 */         Attr escogida = this.pagHTML.createAttribute("selected");
/*  923 */         escogida.setValue("on");
/*  924 */         op.setAttributeNode(escogida);
/*      */       } 
/*  926 */       combo.appendChild(op);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private String getMes(int mes) {
/*  932 */     switch (mes) {
/*      */       case 1:
/*  934 */         return "Enero";
/*      */       case 2:
/*  936 */         return "Febrero";
/*      */       
/*      */       case 3:
/*  939 */         return "Marzo";
/*      */       case 4:
/*  941 */         return "Abril";
/*      */       case 5:
/*  943 */         return "Mayo";
/*      */       case 6:
/*  945 */         return "Junio";
/*      */       case 7:
/*  947 */         return "Julio";
/*      */       case 8:
/*  949 */         return "Agosto";
/*      */       case 9:
/*  951 */         return "Septiembre";
/*      */       case 10:
/*  953 */         return "Octubre";
/*      */       case 11:
/*  955 */         return "Noviembre";
/*      */       case 12:
/*  957 */         return "Diciembre";
/*      */     } 
/*      */ 
/*      */     
/*  961 */     return "";
/*      */   }
/*      */   
/*      */   private String getMesNumero(String mes) {
/*  965 */     if (mes.equals("Enero")) {
/*  966 */       return "mes1";
/*      */     }
/*  968 */     if (mes.equals("Febrero")) {
/*  969 */       return "mes2";
/*      */     }
/*  971 */     if (mes.equals("Marzo")) {
/*  972 */       return "mes3";
/*      */     }
/*  974 */     if (mes.equals("Abril")) {
/*  975 */       return "mes4";
/*      */     }
/*  977 */     if (mes.equals("Mayo")) {
/*  978 */       return "mes5";
/*      */     }
/*  980 */     if (mes.equals("Junio")) {
/*  981 */       return "mes6";
/*      */     }
/*  983 */     if (mes.equals("Julio")) {
/*  984 */       return "mes7";
/*      */     }
/*  986 */     if (mes.equals("Agosto")) {
/*  987 */       return "mes8";
/*      */     }
/*  989 */     if (mes.equals("Septiembre")) {
/*  990 */       return "mes9";
/*      */     }
/*  992 */     if (mes.equals("Octubre")) {
/*  993 */       return "mes10";
/*      */     }
/*  995 */     if (mes.equals("Noviembre")) {
/*  996 */       return "mes11";
/*      */     }
/*  998 */     if (mes.equals("Diciembre")) {
/*  999 */       return "mes12";
/*      */     }
/* 1001 */     return "";
/*      */   }
/*      */   
/*      */   private int getValorEsperado(int mes, PoaMaestroActividadesDTO poa) {
/* 1005 */     switch (mes) {
/*      */       case 1:
/* 1007 */         return poa.getValorMes1();
/*      */       case 2:
/* 1009 */         return poa.getValorMes2();
/*      */       
/*      */       case 3:
/* 1012 */         return poa.getValorMes3();
/*      */       case 4:
/* 1014 */         return poa.getValorMes4();
/*      */       case 5:
/* 1016 */         return poa.getValorMes5();
/*      */       case 6:
/* 1018 */         return poa.getValorMes6();
/*      */       case 7:
/* 1020 */         return poa.getValorMes7();
/*      */       case 8:
/* 1022 */         return poa.getValorMes8();
/*      */       case 9:
/* 1024 */         return poa.getValorMes9();
/*      */       case 10:
/* 1026 */         return poa.getValorMes10();
/*      */       case 11:
/* 1028 */         return poa.getValorMes11();
/*      */       case 12:
/* 1030 */         return poa.getValorMes12();
/*      */     } 
/*      */ 
/*      */     
/* 1034 */     return 0;
/*      */   }
/*      */   
/*      */   private int valorReal(int codigoPoa, int mes) {
/*      */     try {
/* 1039 */       PoaLogrosDAO rs = new PoaLogrosDAO();
/* 1040 */       PoaLogrosDTO reg = rs.cargarRegistro(codigoPoa, getMes(mes));
/* 1041 */       rs.close();
/* 1042 */       return reg.getEjecucion();
/* 1043 */     } catch (Exception e) {
/*      */ 
/*      */       
/* 1046 */       return 0;
/*      */     } 
/*      */   }
/*      */   private int[] totalEjecutado(int poaActivad) {
/* 1050 */     int totalEjecutado = 0;
/* 1051 */     int[] data = new int[2];
/* 1052 */     for (int i = 1; i <= 12; i++) {
/* 1053 */       totalEjecutado += valorReal(poaActivad, i);
/* 1054 */       if (valorReal(poaActivad, i) > 0) {
/* 1055 */         data[1] = i;
/*      */       }
/*      */     } 
/* 1058 */     data[0] = totalEjecutado;
/* 1059 */     return data;
/*      */   }
/*      */   
/*      */   private int totalEsperado(PoaMaestroActividadesDTO reg, int mes) {
/* 1063 */     int totalEsperado = 0;
/* 1064 */     switch (mes) {
/*      */       case 12:
/* 1066 */         totalEsperado += reg.getValorMes12();
/*      */       case 11:
/* 1068 */         totalEsperado += reg.getValorMes11();
/*      */       case 10:
/* 1070 */         totalEsperado += reg.getValorMes10();
/*      */       case 9:
/* 1072 */         totalEsperado += reg.getValorMes9();
/*      */       case 8:
/* 1074 */         totalEsperado += reg.getValorMes8();
/*      */       case 7:
/* 1076 */         totalEsperado += reg.getValorMes7();
/*      */       case 6:
/* 1078 */         totalEsperado += reg.getValorMes6();
/*      */       case 5:
/* 1080 */         totalEsperado += reg.getValorMes5();
/*      */       case 4:
/* 1082 */         totalEsperado += reg.getValorMes4();
/*      */       case 3:
/* 1084 */         totalEsperado += reg.getValorMes3();
/*      */       case 2:
/* 1086 */         totalEsperado += reg.getValorMes2();
/*      */       case 1:
/* 1088 */         totalEsperado += reg.getValorMes1();
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/* 1093 */     return totalEsperado;
/*      */   }
/*      */   
/*      */   private int resultado(PoaMaestroActividadesDTO reg) {
/* 1097 */     int[] data = totalEjecutado(reg.getCodigoPoaActividad());
/* 1098 */     int totalEsperado = totalEsperado(reg, data[1]);
/* 1099 */     int totalEjecutado = data[0];
/* 1100 */     if (totalEsperado > 0) {
/* 1101 */       return totalEjecutado * 100 / totalEsperado;
/*      */     }
/* 1103 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   private String color(double valor) {
/* 1108 */     PoaTableroColoresDAO rs = new PoaTableroColoresDAO();
/* 1109 */     Collection<PoaTableroColoresDTO> colores = rs.cargarTodos("");
/* 1110 */     rs.close();
/* 1111 */     Iterator<PoaTableroColoresDTO> iterator = colores.iterator();
/* 1112 */     while (iterator.hasNext()) {
/* 1113 */       PoaTableroColoresDTO color = (PoaTableroColoresDTO)iterator.next();
/* 1114 */       if (valor <= color.getValorFinal() && valor >= color.getValorInicial()) {
/* 1115 */         return color.getColor();
/*      */       }
/*      */     } 
/* 1118 */     return "FFFFFF";
/*      */   }
/*      */   
/*      */   private String urlLogro(int codigoPoa, int mes) {
/*      */     try {
/* 1123 */       PoaLogrosDAO rs = new PoaLogrosDAO();
/* 1124 */       PoaLogrosDTO reg = rs.cargarRegistro(codigoPoa, getMes(mes));
/* 1125 */       rs.close();
/* 1126 */       return "PoaLogros.po?_operacion=V&idLogro=" + reg.getIdLogro();
/*      */     }
/* 1128 */     catch (Exception e) {
/*      */ 
/*      */       
/* 1131 */       return "#";
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
/*      */   public void agregarCelda(HSSFRow row, HSSFSheet sheet, int contadorFilas, String mensaje, int numCelda, int colInicial, int colFinal, HSSFCellStyle style) {
/* 1143 */     HSSFCell cell = row.createCell(numCelda);
/* 1144 */     cell.setCellValue(mensaje);
/* 1145 */     cell.setCellStyle(style);
/* 1146 */     sheet.addMergedRegion(new CellRangeAddress(contadorFilas, contadorFilas, colInicial, colFinal));
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\IndicadoresEficacia.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */