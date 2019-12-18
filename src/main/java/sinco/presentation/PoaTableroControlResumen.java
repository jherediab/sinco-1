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
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.PoaColoresSemaforoDTO;
/*     */ import sinco.business.PoaLogrosDTO;
/*     */ import sinco.business.PoaMaestroActividadesDTO;
/*     */ import sinco.business.PoaMaestroDTO;
/*     */ import sinco.business.PoaTableroColoresDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.PoaColoresSemaforoDAO;
/*     */ import sinco.data.PoaLogrosDAO;
/*     */ import sinco.data.PoaMaestroActividadesDAO;
/*     */ import sinco.data.PoaMaestroDAO;
/*     */ import sinco.data.PoaTableroColoresDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.PoaTableroControlResumen;
/*     */ import sinco.presentation.PoaTableroControlResumenHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PoaTableroControlResumen
/*     */   implements HttpPresentation
/*     */ {
/*     */   private PoaTableroControlResumenHTML pagHTML;
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
/*  62 */     this.pagHTML = (PoaTableroControlResumenHTML)comms.xmlcFactory.create(PoaTableroControlResumenHTML.class);
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
/* 296 */     return td;
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
/* 308 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 309 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 310 */     td.setAttributeNode(newAttr("bgcolor", "#" + color));
/* 311 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtdColor(String contenido, String color, int colspan) {
/* 322 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 323 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 324 */     td.setAttributeNode(newAttr("bgcolor", "#" + color));
/* 325 */     td.setAttributeNode(newAttr("colspan", "" + colspan));
/* 326 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtdTitle(String contenido) {
/* 337 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 338 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 339 */     td.setAttributeNode(newAttr("class", "ca"));
/* 340 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void consulta(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 346 */     activarVista("consulta");
/* 347 */     HTMLSelectElement combo = this.pagHTML.getElementFarea();
/* 348 */     llenarCombo(combo, "UNIDADES_DEPENDENCIA", "CODIGO", "DESCRIPCION", "1=1", "", true);
/* 349 */     combo = this.pagHTML.getElementFciclo();
/* 350 */     llenarCombo(combo, "POA_CICLOS", "CODIGO_CICLO", "DESCRIPCION", "1=1", "", false);
/* 351 */     combo = this.pagHTML.getElementFproceso();
/* 352 */     llenarCombo(combo, "POA_PROCESOS", "CODIGO_PROCESO", "DESCRIPCION", "1=1", "", true);
/* 353 */     combo = this.pagHTML.getElementFmes();
/* 354 */     llenarComboMeses(combo, "", false);
/* 355 */     combo = this.pagHTML.getElementFmesInicial();
/* 356 */     llenarComboMeses(combo, "", false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void mostrarTablero(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 363 */     activarVista("tablero");
/* 364 */     int area = 0;
/*     */     try {
/* 366 */       area = Integer.parseInt(comms.request.getParameter("farea"));
/*     */     }
/* 368 */     catch (Exception e) {}
/*     */     
/* 370 */     int ciclo = 0;
/*     */     try {
/* 372 */       ciclo = Integer.parseInt(comms.request.getParameter("fciclo"));
/*     */     }
/* 374 */     catch (Exception e) {}
/*     */     
/* 376 */     String proceso = comms.request.getParameter("fproceso");
/*     */     
/* 378 */     int mes = 0;
/*     */     
/*     */     try {
/* 381 */       mes = Integer.parseInt(comms.request.getParameter("fmes").replace("mes", ""));
/*     */     }
/* 383 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 386 */     int mesInicial = 0;
/*     */     
/*     */     try {
/* 389 */       mesInicial = Integer.parseInt(comms.request.getParameter("fmesInicial").replace("mes", ""));
/*     */     }
/* 391 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 394 */     PoaMaestroDAO rsM = new PoaMaestroDAO();
/*     */     
/* 396 */     Collection<Integer> areas = rsM.cargarAreas(area, ciclo, proceso);
/*     */     
/* 398 */     Iterator<Integer> iteMaestro = areas.iterator();
/*     */     
/* 400 */     HTMLTableSectionElement tabla = this.pagHTML.getElementTablaTablero();
/*     */     
/* 402 */     HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 403 */     eltr.appendChild(newtdTitle(""));
/* 404 */     eltr.appendChild(newtdTitle("Número de actividades"));
/* 405 */     eltr.appendChild(newtdTitle("Número de actividades con avance inferior al 90% con corte entre los meses " + getMes(mesInicial) + " y " + getMes(mes)));
/* 406 */     eltr.appendChild(newtdTitle("Eficacia promedio mes de " + getMes(mes)));
/* 407 */     eltr.appendChild(newtdTitle("Eficacia promedio acumulada al mes de " + getMes(mes) + " de " + ciclo));
/* 408 */     eltr.appendChild(newtdTitle("Avance vigencia"));
/* 409 */     tabla.appendChild(eltr);
/* 410 */     while (iteMaestro.hasNext()) {
/*     */       
/* 412 */       AreasDAO rsA = new AreasDAO();
/* 413 */       AreasDTO ar = rsA.cargarRegistro(((Integer)iteMaestro.next()).intValue());
/* 414 */       rsA.close();
/*     */ 
/*     */       
/* 417 */       eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 418 */       eltr.appendChild(newtdTitle("" + ar.getDescripcion()));
/* 419 */       tabla.appendChild(eltr);
/* 420 */       Collection<PoaMaestroDTO> maestros = rsM.cargarRegistros(ar.getCodigo(), ciclo, proceso);
/* 421 */       Iterator<PoaMaestroDTO> iteratorM = maestros.iterator();
/* 422 */       while (iteratorM.hasNext()) {
/* 423 */         PoaMaestroDTO maestro = (PoaMaestroDTO)iteratorM.next();
/* 424 */         eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 425 */         eltr.appendChild(newtd("" + maestro.getNombreProceso()));
/*     */         
/* 427 */         PoaMaestroActividadesDAO rsAct = new PoaMaestroActividadesDAO();
/* 428 */         Collection<PoaMaestroActividadesDTO> actividades = rsAct.cargarTodos(maestro.getCodigoPoa(), 0);
/* 429 */         Iterator<PoaMaestroActividadesDTO> iteratoAct = actividades.iterator();
/* 430 */         int numActividadesMes = 0;
/* 431 */         int ejecucionMes = 0;
/* 432 */         int acumuladoMes = 0;
/* 433 */         int actividadesAvanceInferior = 0;
/* 434 */         while (iteratoAct.hasNext()) {
/* 435 */           numActividadesMes++;
/* 436 */           PoaMaestroActividadesDTO actividad = (PoaMaestroActividadesDTO)iteratoAct.next();
/*     */           try {
/* 438 */             ejecucionMes += valorReal(actividad.getCodigoPoaActividad(), mes) * 100 / getValorEsperado(mes, actividad);
/* 439 */           } catch (Exception e) {}
/*     */           
/*     */           try {
/* 442 */             acumuladoMes += resultado(actividad, mesInicial, mes);
/* 443 */           } catch (Exception e) {}
/*     */           
/* 445 */           if (resultado(actividad, mesInicial, mes) < 90) {
/* 446 */             actividadesAvanceInferior++;
/*     */           }
/*     */         } 
/* 449 */         int promedioMes = 0;
/* 450 */         int promedioAcumuladoMes = 0;
/* 451 */         int numActividades = rsAct.contarActividades(maestro.getCodigoPoa());
/*     */         try {
/* 453 */           promedioMes = ejecucionMes / numActividadesMes;
/* 454 */         } catch (Exception e) {}
/*     */ 
/*     */         
/*     */         try {
/* 458 */           promedioAcumuladoMes = acumuladoMes / numActividades;
/* 459 */         } catch (Exception e) {}
/*     */ 
/*     */         
/* 462 */         eltr.appendChild(newtd("" + numActividades));
/* 463 */         eltr.appendChild(newtd("" + actividadesAvanceInferior));
/* 464 */         eltr.appendChild(newtdColor("" + promedioMes + " % ", colorTablero(promedioMes, "M")));
/* 465 */         eltr.appendChild(newtdColor("" + promedioAcumuladoMes + " % ", colorTablero(promedioAcumuladoMes, "A")));
/* 466 */         eltr.appendChild(newtdColor("" + promedioAcumuladoMes + " % ", colorTablero(promedioAcumuladoMes, "A")));
/* 467 */         rsAct.close();
/*     */         
/* 469 */         tabla.appendChild(eltr);
/*     */       } 
/*     */     } 
/*     */     
/* 473 */     rsM.close();
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
/* 495 */     if (dejarBlanco) {
/* 496 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 497 */       op.setValue("");
/* 498 */       op.appendChild(this.pagHTML.createTextNode("TODOS"));
/* 499 */       combo.appendChild(op);
/*     */     } 
/* 501 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 502 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/* 503 */     rsTGen.close();
/* 504 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 505 */     while (iterator.hasNext()) {
/* 506 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 507 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 508 */       op.setValue("" + regGeneral.getCodigoS());
/* 509 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 510 */       if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
/* 511 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 512 */         escogida.setValue("on");
/* 513 */         op.setAttributeNode(escogida);
/*     */       } 
/* 515 */       combo.appendChild(op);
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
/* 532 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 533 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 534 */     rs.close();
/* 535 */     if (dejarBlanco) {
/* 536 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 537 */       op.setValue("");
/* 538 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 539 */       combo.appendChild(op);
/*     */     } 
/* 541 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 542 */     while (iterator.hasNext()) {
/* 543 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 544 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 545 */       op.setValue("" + reg.getCodigo());
/* 546 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 547 */       if (defecto.equals(reg.getCodigo())) {
/* 548 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 549 */         escogida.setValue("on");
/* 550 */         op.setAttributeNode(escogida);
/*     */       } 
/* 552 */       combo.appendChild(op);
/*     */     } 
/* 554 */     arr.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void llenarComboMeses(HTMLSelectElement combo, String defecto, boolean dejarBlanco) {
/* 561 */     if (dejarBlanco) {
/* 562 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 563 */       op.setValue("");
/* 564 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 565 */       combo.appendChild(op);
/*     */     } 
/* 567 */     for (int i = 1; i <= 12; i++) {
/* 568 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 569 */       op.setValue("mes" + i);
/* 570 */       op.appendChild(this.pagHTML.createTextNode(getMes(i)));
/* 571 */       if (defecto.equals("mes" + i)) {
/* 572 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 573 */         escogida.setValue("on");
/* 574 */         op.setAttributeNode(escogida);
/*     */       } 
/* 576 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private String getMes(int mes) {
/* 582 */     switch (mes) {
/*     */       case 1:
/* 584 */         return "Enero";
/*     */       case 2:
/* 586 */         return "Febrero";
/*     */       
/*     */       case 3:
/* 589 */         return "Marzo";
/*     */       case 4:
/* 591 */         return "Abril";
/*     */       case 5:
/* 593 */         return "Mayo";
/*     */       case 6:
/* 595 */         return "Junio";
/*     */       case 7:
/* 597 */         return "Julio";
/*     */       case 8:
/* 599 */         return "Agosto";
/*     */       case 9:
/* 601 */         return "Septiembre";
/*     */       case 10:
/* 603 */         return "Octubre";
/*     */       case 11:
/* 605 */         return "Noviembre";
/*     */       case 12:
/* 607 */         return "Diciembre";
/*     */     } 
/*     */ 
/*     */     
/* 611 */     return "";
/*     */   }
/*     */   
/*     */   private String getMesNumero(String mes) {
/* 615 */     if (mes.equals("Enero")) {
/* 616 */       return "mes1";
/*     */     }
/* 618 */     if (mes.equals("Febrero")) {
/* 619 */       return "mes2";
/*     */     }
/* 621 */     if (mes.equals("Marzo")) {
/* 622 */       return "mes3";
/*     */     }
/* 624 */     if (mes.equals("Abril")) {
/* 625 */       return "mes4";
/*     */     }
/* 627 */     if (mes.equals("Mayo")) {
/* 628 */       return "mes5";
/*     */     }
/* 630 */     if (mes.equals("Junio")) {
/* 631 */       return "mes6";
/*     */     }
/* 633 */     if (mes.equals("Julio")) {
/* 634 */       return "mes7";
/*     */     }
/* 636 */     if (mes.equals("Agosto")) {
/* 637 */       return "mes8";
/*     */     }
/* 639 */     if (mes.equals("Septiembre")) {
/* 640 */       return "mes9";
/*     */     }
/* 642 */     if (mes.equals("Octubre")) {
/* 643 */       return "mes10";
/*     */     }
/* 645 */     if (mes.equals("Noviembre")) {
/* 646 */       return "mes11";
/*     */     }
/* 648 */     if (mes.equals("Diciembre")) {
/* 649 */       return "mes12";
/*     */     }
/* 651 */     return "";
/*     */   }
/*     */   
/*     */   private int getValorEsperado(int mes, PoaMaestroActividadesDTO poa) {
/* 655 */     switch (mes) {
/*     */       case 1:
/* 657 */         return poa.getValorMes1();
/*     */       case 2:
/* 659 */         return poa.getValorMes2();
/*     */       
/*     */       case 3:
/* 662 */         return poa.getValorMes3();
/*     */       case 4:
/* 664 */         return poa.getValorMes4();
/*     */       case 5:
/* 666 */         return poa.getValorMes5();
/*     */       case 6:
/* 668 */         return poa.getValorMes6();
/*     */       case 7:
/* 670 */         return poa.getValorMes7();
/*     */       case 8:
/* 672 */         return poa.getValorMes8();
/*     */       case 9:
/* 674 */         return poa.getValorMes9();
/*     */       case 10:
/* 676 */         return poa.getValorMes10();
/*     */       case 11:
/* 678 */         return poa.getValorMes11();
/*     */       case 12:
/* 680 */         return poa.getValorMes12();
/*     */     } 
/*     */ 
/*     */     
/* 684 */     return 0;
/*     */   }
/*     */   
/*     */   private int valorReal(int codigoPoa, int mes) {
/*     */     try {
/* 689 */       PoaLogrosDAO rs = new PoaLogrosDAO();
/* 690 */       PoaLogrosDTO reg = rs.cargarRegistro(codigoPoa, getMes(mes));
/* 691 */       rs.close();
/* 692 */       return reg.getEjecucion();
/* 693 */     } catch (Exception e) {
/*     */ 
/*     */       
/* 696 */       return 0;
/*     */     } 
/*     */   }
/*     */   private int[] totalEjecutado(int poaActivad) {
/* 700 */     int totalEjecutado = 0;
/* 701 */     int[] data = new int[2];
/* 702 */     for (int i = 1; i <= 12; i++) {
/* 703 */       totalEjecutado += valorReal(poaActivad, i);
/* 704 */       if (valorReal(poaActivad, i) > 0) {
/* 705 */         data[1] = i;
/*     */       }
/*     */     } 
/* 708 */     data[0] = totalEjecutado;
/* 709 */     return data;
/*     */   }
/*     */   
/*     */   private int[] totalEjecutado(int poaActivad, int mesInicial, int mes) {
/* 713 */     int totalEjecutado = 0;
/* 714 */     int[] data = new int[2];
/* 715 */     for (int i = mesInicial; i <= mes; i++) {
/* 716 */       totalEjecutado += valorReal(poaActivad, i);
/* 717 */       if (valorReal(poaActivad, i) > 0) {
/* 718 */         data[1] = i;
/*     */       }
/*     */     } 
/* 721 */     data[0] = totalEjecutado;
/* 722 */     return data;
/*     */   }
/*     */   
/*     */   private int totalEsperado(PoaMaestroActividadesDTO reg, int mesInicial, int mes) {
/* 726 */     int totalEsperado = 0;
/* 727 */     switch (mes) {
/*     */       case 12:
/* 729 */         totalEsperado += reg.getValorMes12();
/* 730 */         if (mesInicial >= 12) {
/*     */           break;
/*     */         }
/*     */       case 11:
/* 734 */         totalEsperado += reg.getValorMes11();
/* 735 */         if (mesInicial >= 11) {
/*     */           break;
/*     */         }
/*     */       case 10:
/* 739 */         totalEsperado += reg.getValorMes10();
/* 740 */         if (mesInicial >= 10) {
/*     */           break;
/*     */         }
/*     */       case 9:
/* 744 */         totalEsperado += reg.getValorMes9();
/* 745 */         if (mesInicial >= 9) {
/*     */           break;
/*     */         }
/*     */       case 8:
/* 749 */         totalEsperado += reg.getValorMes8();
/* 750 */         if (mesInicial >= 8) {
/*     */           break;
/*     */         }
/*     */       case 7:
/* 754 */         totalEsperado += reg.getValorMes7();
/* 755 */         if (mesInicial >= 7) {
/*     */           break;
/*     */         }
/*     */       case 6:
/* 759 */         totalEsperado += reg.getValorMes6();
/* 760 */         if (mesInicial >= 6) {
/*     */           break;
/*     */         }
/*     */       case 5:
/* 764 */         totalEsperado += reg.getValorMes5();
/* 765 */         if (mesInicial >= 5) {
/*     */           break;
/*     */         }
/*     */       case 4:
/* 769 */         totalEsperado += reg.getValorMes4();
/* 770 */         if (mesInicial >= 4) {
/*     */           break;
/*     */         }
/*     */       case 3:
/* 774 */         totalEsperado += reg.getValorMes3();
/* 775 */         if (mesInicial >= 3) {
/*     */           break;
/*     */         }
/*     */       case 2:
/* 779 */         totalEsperado += reg.getValorMes2();
/* 780 */         if (mesInicial >= 2) {
/*     */           break;
/*     */         }
/*     */       case 1:
/* 784 */         totalEsperado += reg.getValorMes1();
/* 785 */         if (mesInicial >= 1);
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 792 */     return totalEsperado;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int resultado(PoaMaestroActividadesDTO reg, int mesInicial, int mes) {
/* 798 */     int[] data = totalEjecutado(reg.getCodigoPoaActividad(), mesInicial, mes);
/* 799 */     int totalEsperado = totalEsperado(reg, mesInicial, mes);
/* 800 */     int totalEjecutado = data[0];
/* 801 */     if (totalEsperado > 0) {
/* 802 */       return totalEjecutado * 100 / totalEsperado;
/*     */     }
/* 804 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private String color(int valor) {
/* 809 */     PoaTableroColoresDAO rs = new PoaTableroColoresDAO();
/* 810 */     Collection<PoaTableroColoresDTO> colores = rs.cargarTodos("");
/* 811 */     rs.close();
/* 812 */     Iterator<PoaTableroColoresDTO> iterator = colores.iterator();
/* 813 */     while (iterator.hasNext()) {
/* 814 */       PoaTableroColoresDTO color = (PoaTableroColoresDTO)iterator.next();
/* 815 */       if (valor <= color.getValorFinal() && valor >= color.getValorInicial()) {
/* 816 */         return color.getColor();
/*     */       }
/*     */     } 
/* 819 */     return "FFFFFF";
/*     */   }
/*     */   
/*     */   private String colorTablero(int valor, String tipo) {
/* 823 */     PoaColoresSemaforoDAO rs = new PoaColoresSemaforoDAO();
/* 824 */     Collection<PoaColoresSemaforoDTO> colores = rs.cargarTodos(tipo, 0, 0);
/* 825 */     rs.close();
/* 826 */     Iterator<PoaColoresSemaforoDTO> iterator = colores.iterator();
/* 827 */     while (iterator.hasNext()) {
/* 828 */       PoaColoresSemaforoDTO color = (PoaColoresSemaforoDTO)iterator.next();
/* 829 */       if (valor <= color.getValorFinal() && valor >= color.getValorInicial()) {
/* 830 */         return color.getColor();
/*     */       }
/*     */     } 
/* 833 */     return "FFFFFF";
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PoaTableroControlResumen.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */