/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.PoaLogrosDTO;
/*     */ import sinco.business.PoaMaestroActividadesDTO;
/*     */ import sinco.business.PoaMaestroDTO;
/*     */ import sinco.business.PoaObjetivosEstrategicosDTO;
/*     */ import sinco.business.PoaProcesosDTO;
/*     */ import sinco.business.PoaTableroColoresDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.PoaLogrosDAO;
/*     */ import sinco.data.PoaMaestroActividadesDAO;
/*     */ import sinco.data.PoaMaestroDAO;
/*     */ import sinco.data.PoaObjetivosEstrategicosDAO;
/*     */ import sinco.data.PoaProcesosDAO;
/*     */ import sinco.data.PoaTableroColoresDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.PoaTableroControl;
/*     */ import sinco.presentation.PoaTableroControlHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PoaTableroControl
/*     */   implements HttpPresentation
/*     */ {
/*     */   private PoaTableroControlHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  48 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  49 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  52 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  53 */     String _operacion = comms.request.getParameter("_operacion");
/*  54 */     if (_operacion == null || _operacion.length() == 0) {
/*  55 */       _operacion = "X";
/*     */     }
/*     */     
/*  58 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  59 */       creacion(comms);
/*     */     }
/*     */     
/*  62 */     this.pagHTML = (PoaTableroControlHTML)comms.xmlcFactory.create(PoaTableroControlHTML.class);
/*  63 */     permisos(comms);
/*     */ 
/*     */ 
/*     */     
/*  67 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  68 */       consulta(comms);
/*     */     }
/*  70 */     if (_operacion.equals("tablero")) {
/*  71 */       mostrarTablero(comms);
/*     */     }
/*     */     
/*  74 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  75 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void creacion(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  87 */     String _operacion = comms.request.getParameter("_operacion");
/*  88 */     String elUsuario = "" + comms.session.getUser().getName();
/*  89 */     int idLogro = 0;
/*     */     try {
/*  91 */       idLogro = Integer.parseInt(comms.request.getParameter("idLogro"));
/*     */     }
/*  93 */     catch (Exception e) {
/*  94 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idLogro"));
/*     */     } 
/*     */     
/*  97 */     RespuestaBD rta = new RespuestaBD();
/*  98 */     if (_operacion.equals("E")) {
/*  99 */       PoaLogrosDAO rs = new PoaLogrosDAO();
/* 100 */       if (!rs.getEstadoConexion()) {
/* 101 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 103 */       rta = rs.eliminarRegistro(idLogro);
/* 104 */       if (!rta.isRta()) {
/* 105 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaLogros&p1=" + rta.getMensaje()));
/*     */       }
/* 107 */       rs.close();
/* 108 */       String sPagina = "PoaLogros.po?_operacion=X";
/* 109 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 111 */     int codigoPoa = 0;
/*     */     try {
/* 113 */       codigoPoa = Integer.parseInt(comms.request.getParameter("codigoPoa"));
/*     */     }
/* 115 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 118 */     String mes = comms.request.getParameter("mes");
/* 119 */     int ejecucion = 0;
/*     */     try {
/* 121 */       ejecucion = Integer.parseInt(comms.request.getParameter("ejecucion"));
/*     */     }
/* 123 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 126 */     String avances = comms.request.getParameter("avances");
/* 127 */     String logrosYResultados = comms.request.getParameter("logrosYResultados");
/* 128 */     String retrasosDificultades = comms.request.getParameter("retrasosDificultades");
/* 129 */     String estado = comms.request.getParameter("estado");
/* 130 */     PoaLogrosDAO rs = new PoaLogrosDAO();
/* 131 */     if (!rs.getEstadoConexion()) {
/* 132 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 134 */     if (_operacion.equals("C")) {
/* 135 */       rta = rs.crearRegistro(idLogro, codigoPoa, mes, ejecucion, avances, logrosYResultados, retrasosDificultades, estado, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 145 */       idLogro = rta.getSecuencia();
/*     */     } else {
/*     */       
/* 148 */       rta = rs.modificarRegistro(idLogro, codigoPoa, mes, ejecucion, avances, logrosYResultados, retrasosDificultades, estado, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 159 */     rs.close();
/* 160 */     if (!rta.isRta()) {
/* 161 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaLogros&p1=" + rta.getMensaje()));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 183 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 185 */     Varios oVarios = new Varios();
/* 186 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Poa_PoaLogrosAct");
/* 187 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Poa_PoaLogrosDel");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void activarVista(String vista) {
/* 209 */     if (!vista.equals("tablero")) {
/* 210 */       HTMLElement sel = this.pagHTML.getElementDivTableroControl();
/* 211 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 213 */     if (!vista.equals("consulta")) {
/* 214 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 215 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 229 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 230 */     atrib.setValue(valor);
/* 231 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 244 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 245 */     Element enlace = this.pagHTML.createElement("a");
/* 246 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 247 */     enlace.appendChild(hijo);
/* 248 */     Attr donde = this.pagHTML.createAttribute("href");
/* 249 */     donde.setValue(vinculo);
/* 250 */     enlace.setAttributeNode(donde);
/* 251 */     td.appendChild(enlace);
/* 252 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 253 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 263 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 264 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 265 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 266 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido, int colspan) {
/* 277 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 278 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 279 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 280 */     td.setAttributeNode(newAttr("colspan", "" + colspan));
/* 281 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newth(String contenido, String clase, int colspan) {
/* 292 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("th");
/* 293 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 294 */     td.setAttributeNode(newAttr("class", "" + clase));
/* 295 */     td.setAttributeNode(newAttr("colspan", "" + colspan));
/* 296 */     td.setAttributeNode(newAttr("width", "95%"));
/* 297 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtdColor(String contenido, String color) {
/* 309 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 310 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 311 */     td.setAttributeNode(newAttr("bgcolor", "#" + color));
/* 312 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtdColor(String contenido, int rowspan, String color) {
/* 323 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 324 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 325 */     td.setAttributeNode(newAttr("bgcolor", "#" + color));
/* 326 */     td.setAttributeNode(newAttr("rowspan", "" + rowspan));
/* 327 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtdTitle(String contenido, String clase, int rowspan, String color) {
/* 338 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 339 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 340 */     td.setAttributeNode(newAttr("class", clase));
/* 341 */     td.setAttributeNode(newAttr("colspan", "1"));
/* 342 */     if (!color.equals("")) {
/* 343 */       td.setAttributeNode(newAttr("bgcolor", "#" + color));
/*     */     }
/* 345 */     td.setAttributeNode(newAttr("rowspan", "" + rowspan));
/* 346 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void consulta(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 352 */     activarVista("consulta");
/* 353 */     HTMLSelectElement combo = this.pagHTML.getElementFciclo();
/* 354 */     llenarCombo(combo, "POA_CICLOS", "CODIGO_CICLO", "DESCRIPCION", "1=1", "", false);
/* 355 */     combo = this.pagHTML.getElementFmes();
/* 356 */     llenarComboMeses(combo, "", false);
/* 357 */     combo = this.pagHTML.getElementFmesInicial();
/* 358 */     llenarComboMeses(combo, "", false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void mostrarTablero(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 365 */     activarVista("tablero");
/* 366 */     int area = 0;
/*     */ 
/*     */     
/* 369 */     int objetivo = 0;
/*     */ 
/*     */     
/* 372 */     int ciclo = 0;
/*     */     
/*     */     try {
/* 375 */       ciclo = Integer.parseInt(comms.request.getParameter("fciclo"));
/*     */     }
/* 377 */     catch (Exception e) {}
/*     */     
/* 379 */     String proceso = "";
/*     */     
/* 381 */     int mes = 0;
/*     */     
/*     */     try {
/* 384 */       mes = Integer.parseInt(comms.request.getParameter("fmes").replace("mes", ""));
/*     */     }
/* 386 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 389 */     int mesInicial = 0;
/*     */     
/*     */     try {
/* 392 */       mesInicial = Integer.parseInt(comms.request.getParameter("fmesInicial").replace("mes", ""));
/*     */     }
/* 394 */     catch (Exception e) {}
/*     */ 
/*     */ 
/*     */     
/* 398 */     PoaObjetivosEstrategicosDAO rsOb = new PoaObjetivosEstrategicosDAO();
/*     */     
/* 400 */     Collection<PoaObjetivosEstrategicosDTO> objs = rsOb.cargarTodos(objetivo);
/*     */     
/* 402 */     rsOb.close();
/*     */     
/* 404 */     Iterator<PoaObjetivosEstrategicosDTO> iteObjetivos = objs.iterator();
/* 405 */     HTMLTableSectionElement tabla = this.pagHTML.getElementTablaTablero();
/* 406 */     HTMLElement eltrTitulos = (HTMLElement)this.pagHTML.createElement("tr");
/* 407 */     if (mesInicial == mes) {
/* 408 */       eltrTitulos.appendChild(newth("Tablero de control mes: " + getMes(mesInicial) + " de " + ciclo, "ca4", 4));
/*     */     } else {
/* 410 */       eltrTitulos.appendChild(newth("Tablero de control " + getMes(mesInicial) + " a " + getMes(mes) + " de " + ciclo, "ca4", 4));
/*     */     } 
/*     */     
/* 413 */     tabla.appendChild(eltrTitulos);
/*     */ 
/*     */     
/* 416 */     HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */     
/* 418 */     while (iteObjetivos.hasNext()) {
/*     */       
/* 420 */       HTMLElement eltdObjetivo = (HTMLElement)this.pagHTML.createElement("td");
/* 421 */       HTMLElement tablaObjetivos = (HTMLElement)this.pagHTML.createElement("table");
/* 422 */       tablaObjetivos.setAttributeNode(newAttr("class", "table-condensed"));
/* 423 */       tablaObjetivos.setAttributeNode(newAttr("width", "100%"));
/*     */       
/* 425 */       PoaObjetivosEstrategicosDTO objetivoE = (PoaObjetivosEstrategicosDTO)iteObjetivos.next();
/*     */ 
/*     */ 
/*     */       
/* 429 */       PoaMaestroDAO rsM = new PoaMaestroDAO();
/* 430 */       Collection<Integer> codigosProcesos = rsM.cargarProcesosPorObjetivo(area, ciclo, proceso, objetivoE.getCODIGOOBJETIVO());
/* 431 */       Iterator<Integer> iteCodigo = codigosProcesos.iterator();
/* 432 */       if (rsM.cantidadActividadesPorObjetivo(area, ciclo, proceso, objetivoE.getCODIGOOBJETIVO()) > 0) {
/* 433 */         double totalObjetivo = totalObjetivo(objetivoE, area, ciclo, proceso, mesInicial, mesInicial);
/* 434 */         eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 435 */         eltr.setAttributeNode(newAttr("class", "area"));
/* 436 */         eltr.appendChild(newth("Objetivo: " + objetivoE.getDescripcion(), "ca4", 2));
/* 437 */         eltr.setAttributeNode(newAttr("data-toggle", "collapse"));
/* 438 */         eltr.setAttributeNode(newAttr("data-target", "." + objetivoE.getCODIGOOBJETIVO()));
/* 439 */         eltr.setAttributeNode(newAttr("class", "accordion-toggle"));
/* 440 */         eltr.appendChild(newtdColor("" + totalObjetivo + "%", color(totalObjetivo)));
/* 441 */         tablaObjetivos.appendChild(eltr);
/*     */       } 
/*     */ 
/*     */       
/* 445 */       HTMLElement eltrProcesos = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/* 447 */       while (iteCodigo.hasNext()) {
/*     */         
/* 449 */         HTMLElement eltdProceso = (HTMLElement)this.pagHTML.createElement("td");
/* 450 */         HTMLElement tablaProcesos = (HTMLElement)this.pagHTML.createElement("table");
/* 451 */         tablaProcesos.setAttributeNode(newAttr("class", "table-condensed"));
/* 452 */         tablaProcesos.setAttributeNode(newAttr("width", "100%"));
/*     */         
/* 454 */         int proc = ((Integer)iteCodigo.next()).intValue();
/* 455 */         PoaProcesosDAO obProceso = new PoaProcesosDAO();
/* 456 */         PoaProcesosDTO pro = obProceso.cargarRegistro(proc);
/*     */         
/* 458 */         double totalProceso = totalProceso(objetivoE, rsM, proc, area, ciclo, mesInicial, mes);
/*     */         
/* 460 */         eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 461 */         eltr.setAttributeNode(newAttr("data-toggle", "collapse"));
/* 462 */         eltr.setAttributeNode(newAttr("data-target", ".proc" + pro.getCodigoProceso() + "" + objetivoE.getCODIGOOBJETIVO()));
/* 463 */         eltr.setAttributeNode(newAttr("class", "accordion-toggle"));
/* 464 */         eltr.setAttributeNode(newAttr("width", "100%"));
/* 465 */         eltr.appendChild(newth("Proceso: " + pro.getDescripcion(), "ca3", 2));
/* 466 */         eltr.appendChild(newtdColor("" + totalProceso + "%", color(totalProceso)));
/* 467 */         tablaProcesos.appendChild(eltr);
/*     */         
/* 469 */         Collection<Integer> codigosM = rsM.cargarCodigosPorObjetivo(area, ciclo, "" + proc, objetivoE.getCODIGOOBJETIVO());
/* 470 */         Iterator<Integer> iteCodigosM = codigosM.iterator();
/*     */ 
/*     */         
/* 473 */         while (iteCodigosM.hasNext()) {
/* 474 */           HTMLElement eltdArea = (HTMLElement)this.pagHTML.createElement("td");
/* 475 */           HTMLElement eltdActividades = (HTMLElement)this.pagHTML.createElement("td");
/* 476 */           HTMLElement tablaAreas = (HTMLElement)this.pagHTML.createElement("table");
/* 477 */           HTMLElement tablaActividades = (HTMLElement)this.pagHTML.createElement("table");
/* 478 */           tablaAreas.setAttributeNode(newAttr("class", "table-condensed"));
/* 479 */           tablaAreas.setAttributeNode(newAttr("width", "100%"));
/* 480 */           tablaActividades.setAttributeNode(newAttr("width", "100%"));
/*     */           
/* 482 */           PoaMaestroDTO maestro = rsM.cargarRegistro(((Integer)iteCodigosM.next()).intValue());
/*     */           
/* 484 */           double totaArea = totalArea(objetivoE, maestro, rsM, pro, area, ciclo, mesInicial, mes);
/*     */ 
/*     */ 
/*     */           
/* 488 */           PoaMaestroActividadesDAO rsAct = new PoaMaestroActividadesDAO();
/* 489 */           Collection<PoaMaestroActividadesDTO> actividades = rsAct.cargarTodos(maestro.getCodigoPoa(), 0, objetivoE.getCODIGOOBJETIVO());
/* 490 */           rsAct.close();
/*     */ 
/*     */           
/* 493 */           eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 494 */           eltr.setAttributeNode(newAttr("class", "area"));
/* 495 */           eltr.appendChild(newth("Área: " + maestro.getNombreArea(), "ca4", 2));
/* 496 */           eltr.setAttributeNode(newAttr("data-toggle", "collapse"));
/* 497 */           eltr.setAttributeNode(newAttr("data-target", ".act" + pro.getCodigoProceso() + "" + maestro.getArea() + "" + objetivoE.getCODIGOOBJETIVO()));
/* 498 */           eltr.setAttributeNode(newAttr("class", "accordion-toggle"));
/* 499 */           eltr.appendChild(newtdColor("" + totaArea + "%", color(totaArea)));
/* 500 */           tablaAreas.appendChild(eltr);
/*     */           
/* 502 */           Iterator<PoaMaestroActividadesDTO> iteActividades = actividades.iterator();
/* 503 */           while (iteActividades.hasNext()) {
/* 504 */             PoaMaestroActividadesDTO actividad = (PoaMaestroActividadesDTO)iteActividades.next();
/* 505 */             int resultado = resultado(actividad, mesInicial, mes);
/*     */ 
/*     */             
/* 508 */             eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 509 */             eltr.appendChild(newtd(actividad.getNombreActividad()));
/* 510 */             eltr.appendChild(newtdColor("" + resultado + "%", color(resultado)));
/* 511 */             tablaActividades.appendChild(eltr);
/*     */             
/* 513 */             eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 514 */             eltdActividades.appendChild(tablaActividades);
/* 515 */             eltdActividades.setAttributeNode(newAttr("colspan", "2"));
/* 516 */             eltr.appendChild(eltdActividades);
/* 517 */             eltr.setAttributeNode(newAttr("class", "accordian-body collapse act" + pro.getCodigoProceso() + "" + maestro.getArea() + "" + objetivoE.getCODIGOOBJETIVO()));
/* 518 */             tablaAreas.appendChild(eltr);
/* 519 */             eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 520 */             eltdArea.appendChild(tablaAreas);
/* 521 */             eltdArea.setAttributeNode(newAttr("colspan", "2"));
/* 522 */             eltr.appendChild(eltdArea);
/* 523 */             eltr.setAttributeNode(newAttr("colspan", "4"));
/* 524 */             eltr.setAttributeNode(newAttr("class", "accordian-body collapse proc" + pro.getCodigoProceso() + "" + objetivoE.getCODIGOOBJETIVO()));
/* 525 */             tablaProcesos.appendChild(eltr);
/*     */             
/* 527 */             eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 528 */             eltdProceso.appendChild(tablaProcesos);
/* 529 */             eltdProceso.setAttributeNode(newAttr("colspan", "2"));
/* 530 */             eltr.appendChild(eltdProceso);
/* 531 */             eltr.setAttributeNode(newAttr("class", "accordian-body collapse " + objetivoE.getCODIGOOBJETIVO()));
/* 532 */             tablaObjetivos.appendChild(eltr);
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 538 */         eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 539 */         eltdObjetivo.appendChild(tablaObjetivos);
/* 540 */         eltr.appendChild(tablaObjetivos);
/* 541 */         tabla.appendChild(eltr);
/*     */       } 
/* 543 */       rsM.close();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private double totalObjetivo(PoaObjetivosEstrategicosDTO objetivoE, int area, int ciclo, String proceso, int mesInicial, int mes) {
/* 550 */     double total = 0.0D;
/* 551 */     PoaMaestroDAO rsM = new PoaMaestroDAO();
/* 552 */     Collection<Integer> codigosProcesos = rsM.cargarProcesosPorObjetivo(area, ciclo, proceso, objetivoE.getCODIGOOBJETIVO());
/* 553 */     Iterator<Integer> iteCodigo = codigosProcesos.iterator();
/* 554 */     double totalObjetivo = 0.0D;
/* 555 */     int totalActsObjetivo = rsM.cantidadActividadesPorObjetivo(area, ciclo, proceso, objetivoE.getCODIGOOBJETIVO());
/*     */ 
/*     */     
/* 558 */     HTMLElement eltrProcesos = (HTMLElement)this.pagHTML.createElement("tr");
/* 559 */     while (iteCodigo.hasNext()) {
/* 560 */       double totalProceso = 0.0D;
/* 561 */       int proc = ((Integer)iteCodigo.next()).intValue();
/* 562 */       PoaProcesosDAO obProceso = new PoaProcesosDAO();
/* 563 */       PoaProcesosDTO pro = obProceso.cargarRegistro(proc);
/*     */       
/* 565 */       int totalActsProceso = rsM.cantidadActividadesPorObjetivo(area, ciclo, "" + pro.getCodigoProceso(), objetivoE.getCODIGOOBJETIVO());
/*     */       
/* 567 */       Collection<Integer> codigosM = rsM.cargarCodigosPorObjetivo(area, ciclo, "" + proc, objetivoE.getCODIGOOBJETIVO());
/* 568 */       Iterator<Integer> iteCodigosM = codigosM.iterator();
/* 569 */       while (iteCodigosM.hasNext()) {
/* 570 */         double totalArea = 0.0D;
/* 571 */         PoaMaestroDTO maestro = rsM.cargarRegistro(((Integer)iteCodigosM.next()).intValue());
/* 572 */         int totalActsArea = rsM.cantidadActividadesPorObjetivo(maestro.getArea(), ciclo, "" + pro.getCodigoProceso(), objetivoE.getCODIGOOBJETIVO());
/*     */         
/* 574 */         PoaMaestroActividadesDAO rsAct = new PoaMaestroActividadesDAO();
/* 575 */         Collection<PoaMaestroActividadesDTO> actividades = rsAct.cargarTodos(maestro.getCodigoPoa(), 0, objetivoE.getCODIGOOBJETIVO());
/* 576 */         rsAct.close();
/*     */ 
/*     */         
/* 579 */         Iterator<PoaMaestroActividadesDTO> iteActividades = actividades.iterator();
/* 580 */         while (iteActividades.hasNext()) {
/*     */           
/* 582 */           PoaMaestroActividadesDTO actividad = (PoaMaestroActividadesDTO)iteActividades.next();
/* 583 */           int resultado = resultado(actividad, mesInicial, mes);
/* 584 */           total += resultado * 1.0D / totalActsObjetivo;
/*     */         } 
/*     */       } 
/*     */     } 
/* 588 */     rsM.close();
/*     */     
/* 590 */     return Math.round(total * 100.0D) / 100.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   private double totalProceso(PoaObjetivosEstrategicosDTO objetivoE, PoaMaestroDAO rsM, int proc, int area, int ciclo, int mesInicial, int mes) {
/* 595 */     double total = 0.0D;
/* 596 */     double totalProceso = 0.0D;
/*     */     
/* 598 */     PoaProcesosDAO obProceso = new PoaProcesosDAO();
/* 599 */     PoaProcesosDTO pro = obProceso.cargarRegistro(proc);
/*     */     
/* 601 */     int totalActsProceso = rsM.cantidadActividadesPorObjetivo(area, ciclo, "" + pro.getCodigoProceso(), objetivoE.getCODIGOOBJETIVO());
/*     */     
/* 603 */     Collection<Integer> codigosM = rsM.cargarCodigosPorObjetivo(area, ciclo, "" + proc, objetivoE.getCODIGOOBJETIVO());
/* 604 */     Iterator<Integer> iteCodigosM = codigosM.iterator();
/* 605 */     while (iteCodigosM.hasNext()) {
/* 606 */       double totalArea = 0.0D;
/* 607 */       PoaMaestroDTO maestro = rsM.cargarRegistro(((Integer)iteCodigosM.next()).intValue());
/* 608 */       int totalActsArea = rsM.cantidadActividadesPorObjetivo(maestro.getArea(), ciclo, "" + pro.getCodigoProceso(), objetivoE.getCODIGOOBJETIVO());
/*     */       
/* 610 */       PoaMaestroActividadesDAO rsAct = new PoaMaestroActividadesDAO();
/* 611 */       Collection<PoaMaestroActividadesDTO> actividades = rsAct.cargarTodos(maestro.getCodigoPoa(), 0, objetivoE.getCODIGOOBJETIVO());
/* 612 */       rsAct.close();
/*     */ 
/*     */       
/* 615 */       Iterator<PoaMaestroActividadesDTO> iteActividades = actividades.iterator();
/* 616 */       while (iteActividades.hasNext()) {
/*     */         
/* 618 */         PoaMaestroActividadesDTO actividad = (PoaMaestroActividadesDTO)iteActividades.next();
/* 619 */         int resultado = resultado(actividad, mesInicial, mes);
/* 620 */         total += resultado * 1.0D / totalActsProceso;
/*     */       } 
/*     */     } 
/* 623 */     return Math.round(total * 100.0D) / 100.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private double totalArea(PoaObjetivosEstrategicosDTO objetivoE, PoaMaestroDTO maestro, PoaMaestroDAO rsM, PoaProcesosDTO pro, int area, int ciclo, int mesInicial, int mes) {
/* 629 */     double total = 0.0D;
/* 630 */     double totalArea = 0.0D;
/*     */     
/* 632 */     int totalActsArea = rsM.cantidadActividadesPorObjetivo(maestro.getArea(), ciclo, "" + pro.getCodigoProceso(), objetivoE.getCODIGOOBJETIVO());
/*     */     
/* 634 */     PoaMaestroActividadesDAO rsAct = new PoaMaestroActividadesDAO();
/* 635 */     Collection<PoaMaestroActividadesDTO> actividades = rsAct.cargarTodos(maestro.getCodigoPoa(), 0, objetivoE.getCODIGOOBJETIVO());
/* 636 */     rsAct.close();
/*     */ 
/*     */     
/* 639 */     Iterator<PoaMaestroActividadesDTO> iteActividades = actividades.iterator();
/* 640 */     while (iteActividades.hasNext()) {
/*     */       
/* 642 */       PoaMaestroActividadesDTO actividad = (PoaMaestroActividadesDTO)iteActividades.next();
/* 643 */       int resultado = resultado(actividad, mesInicial, mes);
/* 644 */       total += resultado * 1.0D / totalActsArea;
/*     */     } 
/* 646 */     return Math.round(total * 100.0D) / 100.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void llenarCombo(HTMLSelectElement combo, String tabla, String codigo, String descripcion, String condicion, String defecto, boolean dejarBlanco) {
/* 668 */     if (dejarBlanco) {
/* 669 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 670 */       op.setValue("");
/* 671 */       op.appendChild(this.pagHTML.createTextNode("TODOS"));
/* 672 */       combo.appendChild(op);
/*     */     } 
/* 674 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 675 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/* 676 */     rsTGen.close();
/* 677 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 678 */     while (iterator.hasNext()) {
/* 679 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 680 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 681 */       op.setValue("" + regGeneral.getCodigoS());
/* 682 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 683 */       if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
/* 684 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 685 */         escogida.setValue("on");
/* 686 */         op.setAttributeNode(escogida);
/*     */       } 
/* 688 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void comboMultivalores(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco) {
/* 705 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 706 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 707 */     rs.close();
/* 708 */     if (dejarBlanco) {
/* 709 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 710 */       op.setValue("");
/* 711 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 712 */       combo.appendChild(op);
/*     */     } 
/* 714 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 715 */     while (iterator.hasNext()) {
/* 716 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 717 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 718 */       op.setValue("" + reg.getCodigo());
/* 719 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 720 */       if (defecto.equals(reg.getCodigo())) {
/* 721 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 722 */         escogida.setValue("on");
/* 723 */         op.setAttributeNode(escogida);
/*     */       } 
/* 725 */       combo.appendChild(op);
/*     */     } 
/* 727 */     arr.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void llenarComboMeses(HTMLSelectElement combo, String defecto, boolean dejarBlanco) {
/* 734 */     if (dejarBlanco) {
/* 735 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 736 */       op.setValue("");
/* 737 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 738 */       combo.appendChild(op);
/*     */     } 
/* 740 */     for (int i = 1; i <= 12; i++) {
/* 741 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 742 */       op.setValue("mes" + i);
/* 743 */       op.appendChild(this.pagHTML.createTextNode(getMes(i)));
/* 744 */       if (defecto.equals("mes" + i)) {
/* 745 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 746 */         escogida.setValue("on");
/* 747 */         op.setAttributeNode(escogida);
/*     */       } 
/* 749 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private String getMes(int mes) {
/* 755 */     switch (mes) {
/*     */       case 1:
/* 757 */         return "Enero";
/*     */       case 2:
/* 759 */         return "Febrero";
/*     */       
/*     */       case 3:
/* 762 */         return "Marzo";
/*     */       case 4:
/* 764 */         return "Abril";
/*     */       case 5:
/* 766 */         return "Mayo";
/*     */       case 6:
/* 768 */         return "Junio";
/*     */       case 7:
/* 770 */         return "Julio";
/*     */       case 8:
/* 772 */         return "Agosto";
/*     */       case 9:
/* 774 */         return "Septiembre";
/*     */       case 10:
/* 776 */         return "Octubre";
/*     */       case 11:
/* 778 */         return "Noviembre";
/*     */       case 12:
/* 780 */         return "Diciembre";
/*     */     } 
/*     */ 
/*     */     
/* 784 */     return "";
/*     */   }
/*     */   
/*     */   private String getMesNumero(String mes) {
/* 788 */     if (mes.equals("Enero")) {
/* 789 */       return "mes1";
/*     */     }
/* 791 */     if (mes.equals("Febrero")) {
/* 792 */       return "mes2";
/*     */     }
/* 794 */     if (mes.equals("Marzo")) {
/* 795 */       return "mes3";
/*     */     }
/* 797 */     if (mes.equals("Abril")) {
/* 798 */       return "mes4";
/*     */     }
/* 800 */     if (mes.equals("Mayo")) {
/* 801 */       return "mes5";
/*     */     }
/* 803 */     if (mes.equals("Junio")) {
/* 804 */       return "mes6";
/*     */     }
/* 806 */     if (mes.equals("Julio")) {
/* 807 */       return "mes7";
/*     */     }
/* 809 */     if (mes.equals("Agosto")) {
/* 810 */       return "mes8";
/*     */     }
/* 812 */     if (mes.equals("Septiembre")) {
/* 813 */       return "mes9";
/*     */     }
/* 815 */     if (mes.equals("Octubre")) {
/* 816 */       return "mes10";
/*     */     }
/* 818 */     if (mes.equals("Noviembre")) {
/* 819 */       return "mes11";
/*     */     }
/* 821 */     if (mes.equals("Diciembre")) {
/* 822 */       return "mes12";
/*     */     }
/* 824 */     return "";
/*     */   }
/*     */   
/*     */   private int getValorEsperado(int mes, PoaMaestroActividadesDTO poa) {
/* 828 */     switch (mes) {
/*     */       case 1:
/* 830 */         return poa.getValorMes1();
/*     */       case 2:
/* 832 */         return poa.getValorMes2();
/*     */       
/*     */       case 3:
/* 835 */         return poa.getValorMes3();
/*     */       case 4:
/* 837 */         return poa.getValorMes4();
/*     */       case 5:
/* 839 */         return poa.getValorMes5();
/*     */       case 6:
/* 841 */         return poa.getValorMes6();
/*     */       case 7:
/* 843 */         return poa.getValorMes7();
/*     */       case 8:
/* 845 */         return poa.getValorMes8();
/*     */       case 9:
/* 847 */         return poa.getValorMes9();
/*     */       case 10:
/* 849 */         return poa.getValorMes10();
/*     */       case 11:
/* 851 */         return poa.getValorMes11();
/*     */       case 12:
/* 853 */         return poa.getValorMes12();
/*     */     } 
/*     */ 
/*     */     
/* 857 */     return 0;
/*     */   }
/*     */   
/*     */   private int valorReal(int codigoPoa, int mes) {
/*     */     try {
/* 862 */       PoaLogrosDAO rs = new PoaLogrosDAO();
/* 863 */       PoaLogrosDTO reg = rs.cargarRegistro(codigoPoa, getMes(mes));
/* 864 */       rs.close();
/* 865 */       return reg.getEjecucion();
/* 866 */     } catch (Exception e) {
/*     */ 
/*     */       
/* 869 */       return 0;
/*     */     } 
/*     */   }
/*     */   private int[] totalEjecutado(int poaActivad, int mesInicial, int mes) {
/* 873 */     int totalEjecutado = 0;
/* 874 */     int[] data = new int[2];
/* 875 */     for (int i = mesInicial; i <= mes; i++) {
/* 876 */       totalEjecutado += valorReal(poaActivad, i);
/* 877 */       if (valorReal(poaActivad, i) > 0) {
/* 878 */         data[1] = i;
/*     */       }
/*     */     } 
/* 881 */     data[0] = totalEjecutado;
/* 882 */     return data;
/*     */   }
/*     */   
/*     */   private int totalEsperado(PoaMaestroActividadesDTO reg, int mesInicial, int mes) {
/* 886 */     int totalEsperado = 0;
/* 887 */     switch (mes) {
/*     */       case 12:
/* 889 */         totalEsperado += reg.getValorMes12();
/* 890 */         if (mesInicial >= 12) {
/*     */           break;
/*     */         }
/*     */       case 11:
/* 894 */         totalEsperado += reg.getValorMes11();
/* 895 */         if (mesInicial >= 11) {
/*     */           break;
/*     */         }
/*     */       case 10:
/* 899 */         totalEsperado += reg.getValorMes10();
/* 900 */         if (mesInicial >= 10) {
/*     */           break;
/*     */         }
/*     */       case 9:
/* 904 */         totalEsperado += reg.getValorMes9();
/* 905 */         if (mesInicial >= 9) {
/*     */           break;
/*     */         }
/*     */       case 8:
/* 909 */         totalEsperado += reg.getValorMes8();
/* 910 */         if (mesInicial >= 8) {
/*     */           break;
/*     */         }
/*     */       case 7:
/* 914 */         totalEsperado += reg.getValorMes7();
/* 915 */         if (mesInicial >= 7) {
/*     */           break;
/*     */         }
/*     */       case 6:
/* 919 */         totalEsperado += reg.getValorMes6();
/* 920 */         if (mesInicial >= 6) {
/*     */           break;
/*     */         }
/*     */       case 5:
/* 924 */         totalEsperado += reg.getValorMes5();
/* 925 */         if (mesInicial >= 5) {
/*     */           break;
/*     */         }
/*     */       case 4:
/* 929 */         totalEsperado += reg.getValorMes4();
/* 930 */         if (mesInicial >= 4) {
/*     */           break;
/*     */         }
/*     */       case 3:
/* 934 */         totalEsperado += reg.getValorMes3();
/* 935 */         if (mesInicial >= 3) {
/*     */           break;
/*     */         }
/*     */       case 2:
/* 939 */         totalEsperado += reg.getValorMes2();
/* 940 */         if (mesInicial >= 2) {
/*     */           break;
/*     */         }
/*     */       case 1:
/* 944 */         totalEsperado += reg.getValorMes1();
/* 945 */         if (mesInicial >= 1);
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 952 */     return totalEsperado;
/*     */   }
/*     */   
/*     */   private int resultado(PoaMaestroActividadesDTO reg, int mesInicial, int mes) {
/* 956 */     int[] data = totalEjecutado(reg.getCodigoPoaActividad(), mesInicial, mes);
/* 957 */     int totalEsperado = totalEsperado(reg, mesInicial, mes);
/* 958 */     int totalEjecutado = data[0];
/* 959 */     if (totalEsperado > 0) {
/* 960 */       return totalEjecutado * 100 / totalEsperado;
/*     */     }
/* 962 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private String color(double valor) {
/* 967 */     PoaTableroColoresDAO rs = new PoaTableroColoresDAO();
/* 968 */     Collection<PoaTableroColoresDTO> colores = rs.cargarTodos("");
/* 969 */     rs.close();
/* 970 */     Iterator<PoaTableroColoresDTO> iterator = colores.iterator();
/* 971 */     while (iterator.hasNext()) {
/* 972 */       PoaTableroColoresDTO color = (PoaTableroColoresDTO)iterator.next();
/* 973 */       if (valor <= color.getValorFinal() && valor >= color.getValorInicial()) {
/* 974 */         return color.getColor();
/*     */       }
/*     */     } 
/* 977 */     return "FFFFFF";
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PoaTableroControl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */