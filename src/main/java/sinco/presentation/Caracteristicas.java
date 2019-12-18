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
/*     */ import sinco.business.CaracteristicasDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.CaracteristicasDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.Caracteristicas;
/*     */ import sinco.presentation.CaracteristicasHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Caracteristicas
/*     */   implements HttpPresentation
/*     */ {
/*     */   private CaracteristicasHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  39 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  40 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  43 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  44 */     String _operacion = comms.request.getParameter("_operacion");
/*  45 */     if (_operacion == null || _operacion.length() == 0) {
/*  46 */       _operacion = "X";
/*     */     }
/*     */     
/*  49 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  50 */       creacion(comms);
/*     */     }
/*     */     
/*  53 */     this.pagHTML = (CaracteristicasHTML)comms.xmlcFactory.create(CaracteristicasHTML.class);
/*  54 */     permisos(comms);
/*     */     
/*  56 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  57 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  60 */     if (_operacion.equals("P")) {
/*  61 */       editar(comms);
/*     */     }
/*  63 */     else if (_operacion.equals("Nuevo")) {
/*  64 */       nuevo(comms);
/*     */     } 
/*     */     
/*  67 */     if (_operacion.equals("V")) {
/*  68 */       verRegistro(comms);
/*     */     }
/*  70 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  71 */     comms.response.writeDOM(this.pagHTML);
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
/*  83 */     String _operacion = comms.request.getParameter("_operacion");
/*  84 */     String elUsuario = "" + comms.session.getUser().getName();
/*  85 */     int codigo = 0;
/*     */     try {
/*  87 */       codigo = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/*  89 */     catch (Exception e) {
/*  90 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigo"));
/*     */     } 
/*     */     
/*  93 */     RespuestaBD rta = new RespuestaBD();
/*  94 */     if (_operacion.equals("E")) {
/*  95 */       CaracteristicasDAO rs = new CaracteristicasDAO();
/*  96 */       if (!rs.getEstadoConexion()) {
/*  97 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*  99 */       rta = rs.eliminarRegistro(codigo);
/* 100 */       if (!rta.isRta()) {
/* 101 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCaracteristicas&p1=" + rta.getMensaje()));
/*     */       }
/* 103 */       rs.close();
/* 104 */       String sPagina = "Caracteristicas.po?_operacion=X";
/* 105 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 107 */     String descripcion = comms.request.getParameter("descripcion");
/* 108 */     String tipo = "1";
/*     */     try {
/* 110 */       tipo = comms.request.getParameter("tipo");
/*     */     }
/* 112 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 115 */     String calificar = comms.request.getParameter("calificar");
/* 116 */     if (calificar == null) calificar = "N"; 
/* 117 */     int longitud = 0;
/*     */     try {
/* 119 */       longitud = Integer.parseInt(comms.request.getParameter("longitud"));
/*     */     }
/* 121 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 124 */     String competencia = "";
/* 125 */     int caracteristicaAnida = 0;
/*     */     try {
/* 127 */       caracteristicaAnida = Integer.parseInt(comms.request.getParameter("caracteristicaAnida"));
/*     */     }
/* 129 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 132 */     String permiteExtender = comms.request.getParameter("permiteExtender");
/* 133 */     if (permiteExtender == null) {
/* 134 */       permiteExtender = "N";
/*     */     }
/* 136 */     int caracteristicaDepende = 0;
/*     */     try {
/* 138 */       caracteristicaDepende = Integer.parseInt(comms.request.getParameter("caracteristicaDepende"));
/*     */     }
/* 140 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 143 */     int valorDepende = 0;
/*     */     try {
/* 145 */       valorDepende = Integer.parseInt(comms.request.getParameter("valorDepende"));
/*     */     }
/* 147 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 150 */     String tipoValidacion = comms.request.getParameter("tipoValidacion");
/* 151 */     int caracteristicaValida = 0;
/*     */     try {
/* 153 */       caracteristicaValida = Integer.parseInt(comms.request.getParameter("caracteristicaValida"));
/*     */     }
/* 155 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 158 */     String nombreProcedimiento = comms.request.getParameter("nombreProcedimiento");
/* 159 */     int numeroDecimales = 0;
/*     */     try {
/* 161 */       numeroDecimales = Integer.parseInt(comms.request.getParameter("numeroDecimales"));
/*     */     }
/* 163 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 166 */     CaracteristicasDAO rs = new CaracteristicasDAO();
/* 167 */     if (!rs.getEstadoConexion()) {
/* 168 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 170 */     if (_operacion.equals("C")) {
/* 171 */       rta = rs.crearRegistro(codigo, descripcion, tipo, calificar, longitud, competencia, caracteristicaAnida, permiteExtender, caracteristicaDepende, valorDepende, tipoValidacion, caracteristicaValida, nombreProcedimiento, numeroDecimales, elUsuario);
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
/* 187 */       codigo = rta.getSecuencia();
/*     */     } else {
/*     */       
/* 190 */       rta = rs.modificarRegistro(codigo, descripcion, tipo, calificar, longitud, competencia, caracteristicaAnida, permiteExtender, caracteristicaDepende, valorDepende, tipoValidacion, caracteristicaValida, nombreProcedimiento, numeroDecimales, elUsuario);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 207 */     rs.close();
/* 208 */     if (!rta.isRta()) {
/* 209 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCaracteristicas&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 212 */     String sPagina = "Caracteristicas.po?_operacion=P&codigo=" + codigo + "";
/* 213 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 224 */     int codigo = 0;
/*     */     try {
/* 226 */       codigo = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 228 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 231 */     CaracteristicasDAO rs = new CaracteristicasDAO();
/* 232 */     if (!rs.getEstadoConexion()) {
/* 233 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 235 */     CaracteristicasDTO reg = rs.cargarRegistro(codigo);
/* 236 */     rs.close();
/* 237 */     if (reg != null) {
/* 238 */       this.pagHTML.getElementCodigo().setValue("" + reg.getCodigo());
/* 239 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 240 */       this.pagHTML.getElementCalificar().setValue("" + reg.getCalificar());
/* 241 */       this.pagHTML.getElementLongitud().setValue("" + reg.getLongitud());
/* 242 */       if (reg.getCaracteristicaAnida() > 0) {
/* 243 */         this.pagHTML.getElementCaracteristicaAnida().setValue("" + reg.getCaracteristicaAnida());
/*     */       }
/* 245 */       if (reg.getPermiteExtender().equals("S")) {
/* 246 */         this.pagHTML.getElementPermiteExtender().setChecked(true);
/*     */       }
/*     */       
/* 249 */       if (reg.getCaracteristicaDepende() > 0) {
/* 250 */         this.pagHTML.getElementCaracteristicaDepende().setValue("" + reg.getCaracteristicaDepende());
/* 251 */         HTMLSelectElement combo = this.pagHTML.getElementValorDepende();
/* 252 */         llenarCombo(combo, "caracteristicas_valor", "valor", "DESCRIPCION", "caracteristica=" + reg.getCaracteristicaDepende(), "" + reg.getValorDepende(), true);
/*     */       } 
/* 254 */       if (reg.getCaracteristicaValida() > 0) {
/* 255 */         this.pagHTML.getElementCaracteristicaValida().setValue("" + reg.getCaracteristicaValida());
/*     */       }
/* 257 */       this.pagHTML.getElementNombreProcedimiento().setValue("" + reg.getNombreProcedimiento());
/* 258 */       if (reg.getNumeroDecimales() > 0) {
/* 259 */         this.pagHTML.getElementNumeroDecimales().setValue("" + reg.getNumeroDecimales());
/*     */       }
/* 261 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 262 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 263 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 264 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/*     */       
/* 266 */       HTMLSelectElement combo = this.pagHTML.getElementTipo();
/* 267 */       comboMultivalores(combo, "TIPO_CARACTERISTICA", "" + reg.getTipo(), true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 274 */       combo = this.pagHTML.getElementTipoValidacion();
/* 275 */       comboMultivalores(combo, "VALIDACION_CARACTERISTICA", "" + reg.getTipoValidacion(), true);
/*     */ 
/*     */       
/* 278 */       this.pagHTML.getElementCodigo().setReadOnly(true);
/*     */     } 
/* 280 */     this.pagHTML.getElement_operacion().setValue("M");
/* 281 */     activarVista("nuevo");
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
/*     */   private void nuevo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 293 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 295 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 296 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 298 */     catch (Exception e) {}
/*     */     
/* 300 */     activarVista("nuevo");
/* 301 */     HTMLSelectElement combo = this.pagHTML.getElementTipo();
/* 302 */     comboMultivalores(combo, "TIPO_CARACTERISTICA", "", true);
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
/* 314 */     combo = this.pagHTML.getElementTipoValidacion();
/* 315 */     comboMultivalores(combo, "VALIDACION_CARACTERISTICA", "", true);
/*     */     
/* 317 */     this.pagHTML.getElementCodigo().setReadOnly(true);
/* 318 */     this.pagHTML.getElementCodigo().setValue("0");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
/* 329 */     activarVista("consulta");
/* 330 */     int codigo = 0;
/*     */     try {
/* 332 */       codigo = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 334 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 337 */     String descripcion = comms.request.getParameter("descripcion");
/* 338 */     if (descripcion == null) {
/* 339 */       descripcion = "";
/*     */     }
/* 341 */     String tipo = "";
/*     */     try {
/* 343 */       tipo = comms.request.getParameter("tipo");
/*     */     }
/* 345 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 348 */     HTMLSelectElement combo = this.pagHTML.getElementFtipo();
/* 349 */     comboMultivalores(combo, "TIPO_CARACTERISTICA", "" + tipo, true);
/*     */ 
/*     */     
/* 352 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 357 */     CaracteristicasDAO rs = new CaracteristicasDAO();
/* 358 */     if (!rs.getEstadoConexion()) {
/* 359 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 361 */     Collection<CaracteristicasDTO> arr = rs.cargarTodos(codigo, descripcion, tipo);
/*     */ 
/*     */ 
/*     */     
/* 365 */     rs.close();
/* 366 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 367 */     int cuantas = 0;
/* 368 */     Iterator<CaracteristicasDTO> iterator = arr.iterator();
/* 369 */     while (iterator.hasNext()) {
/* 370 */       CaracteristicasDTO reg = (CaracteristicasDTO)iterator.next();
/* 371 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 372 */       eltr.appendChild(newtd("" + reg.getCodigo()));
/* 373 */       String url = "Caracteristicas.po?_operacion=V&codigo=" + reg.getCodigo() + "";
/* 374 */       eltr.appendChild(newtdhref("" + reg.getDescripcion(), url));
/* 375 */       eltr.appendChild(newtd("" + reg.getNombreTipo()));
/* 376 */       eltr.appendChild(newtd("" + reg.getCalificar()));
/* 377 */       eltr.appendChild(newtd("" + reg.getLongitud()));
/* 378 */       hte.appendChild(eltr);
/* 379 */       cuantas++;
/*     */     } 
/* 381 */     arr.clear();
/* 382 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/*     */   private void verRegistro(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 395 */     int codigo = 0;
/*     */     try {
/* 397 */       codigo = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 399 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 402 */     CaracteristicasDAO rs = new CaracteristicasDAO();
/* 403 */     if (!rs.getEstadoConexion()) {
/* 404 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 406 */     CaracteristicasDTO reg = rs.cargarRegistro(codigo);
/* 407 */     rs.close();
/* 408 */     if (reg != null) {
/* 409 */       this.pagHTML.setTextCodigoEd("" + reg.getCodigo());
/* 410 */       this.pagHTML.setTextDescripcionEd("" + reg.getDescripcion());
/* 411 */       this.pagHTML.setTextTipoEd("" + reg.getNombreTipo());
/* 412 */       this.pagHTML.setTextCalificarEd("" + reg.getCalificar());
/* 413 */       this.pagHTML.setTextLongitudEd("" + reg.getLongitud());
/*     */       
/* 415 */       this.pagHTML.setTextCaracteristicaAnidaEd("" + reg.getCaracteristicaAnida());
/* 416 */       this.pagHTML.setTextPermiteExtenderEd("" + reg.getPermiteExtender());
/* 417 */       this.pagHTML.setTextCaracteristicaDependeEd("" + reg.getCaracteristicaDepende());
/* 418 */       this.pagHTML.setTextValorDependeEd("" + reg.getNombreValorDepende());
/* 419 */       this.pagHTML.setTextTipoValidacionEd("" + reg.getNombreTipoValidacion());
/* 420 */       this.pagHTML.setTextCaracteristicaValidaEd("" + reg.getCaracteristicaValida());
/* 421 */       this.pagHTML.setTextNombreProcedimientoEd("" + reg.getNombreProcedimiento());
/* 422 */       this.pagHTML.setTextNumeroDecimalesEd("" + reg.getNumeroDecimales());
/* 423 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 424 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 425 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 426 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 428 */       this.pagHTML.getElementCodigoKey().setValue("" + reg.getCodigo());
/* 429 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */       
/* 431 */       if (!reg.getTipo().equals("2")) {
/* 432 */         HTMLElement elem = this.pagHTML.getElementListaValores();
/* 433 */         elem.getParentNode().removeChild(elem);
/*     */       } 
/*     */     } 
/* 436 */     activarVista("editar");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 447 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 449 */     Varios oVarios = new Varios();
/* 450 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Adm_CaracteristicasAct");
/* 451 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Adm_CaracteristicasDel");
/* 452 */     if (!oPermisoAct) {
/* 453 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 454 */       elem.getParentNode().removeChild(elem);
/* 455 */       elem = this.pagHTML.getElementBtnGrabar();
/* 456 */       elem.getParentNode().removeChild(elem);
/* 457 */       elem = this.pagHTML.getElementBtnModificar();
/* 458 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 460 */     if (!oPermisoDel) {
/* 461 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 462 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void activarVista(String vista) {
/* 473 */     if (!vista.equals("nuevo")) {
/* 474 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 475 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 477 */     if (!vista.equals("editar")) {
/* 478 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 479 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 481 */     if (!vista.equals("consulta")) {
/* 482 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 483 */       sel.getParentNode().removeChild(sel);
/* 484 */       sel = this.pagHTML.getElementDivResultados();
/* 485 */       sel.getParentNode().removeChild(sel);
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
/* 499 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 500 */     atrib.setValue(valor);
/* 501 */     return atrib;
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
/* 514 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 515 */     Element enlace = this.pagHTML.createElement("a");
/* 516 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 517 */     enlace.appendChild(hijo);
/* 518 */     Attr donde = this.pagHTML.createAttribute("href");
/* 519 */     donde.setValue(vinculo);
/* 520 */     enlace.setAttributeNode(donde);
/* 521 */     td.appendChild(enlace);
/* 522 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 523 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 533 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 534 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 535 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 536 */     return td;
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
/* 552 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 553 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 554 */     rs.close();
/* 555 */     if (dejarBlanco) {
/* 556 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 557 */       op.setValue("");
/* 558 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 559 */       combo.appendChild(op);
/*     */     } 
/* 561 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 562 */     while (iterator.hasNext()) {
/* 563 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 564 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 565 */       op.setValue("" + reg.getCodigo());
/* 566 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 567 */       if (defecto.equals(reg.getCodigo())) {
/* 568 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 569 */         escogida.setValue("on");
/* 570 */         op.setAttributeNode(escogida);
/*     */       } 
/* 572 */       combo.appendChild(op);
/*     */     } 
/* 574 */     arr.clear();
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
/*     */   private void llenarCombo(HTMLSelectElement combo, String tabla, String codigo, String descripcion, String condicion, String defecto, boolean dejarBlanco) {
/* 595 */     if (dejarBlanco) {
/* 596 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 597 */       op.setValue("");
/* 598 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 599 */       combo.appendChild(op);
/*     */     } 
/* 601 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 602 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/* 603 */     rsTGen.close();
/* 604 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 605 */     while (iterator.hasNext()) {
/* 606 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 607 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 608 */       op.setValue("" + regGeneral.getCodigoS());
/* 609 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 610 */       if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
/* 611 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 612 */         escogida.setValue("on");
/* 613 */         op.setAttributeNode(escogida);
/*     */       } 
/* 615 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\Caracteristicas.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */