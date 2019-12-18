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
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.ContAdicionContratoDTO;
/*     */ import sinco.business.ContEstudioPrevioServiciosDTO;
/*     */ import sinco.business.LetrasDTO;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContAdicionContratoDAO;
/*     */ import sinco.data.ContContratoDAO;
/*     */ import sinco.data.ContEstudioPrevioServiciosDAO;
/*     */ import sinco.data.DocumentosDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.ContAdicionContratoAct;
/*     */ import sinco.presentation.ContAdicionContratoActHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContAdicionContratoAct
/*     */   implements HttpPresentation
/*     */ {
/*     */   private ContAdicionContratoActHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  43 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  44 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  47 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  48 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */ 
/*     */     
/*  51 */     String _operacion = comms.request.getParameter("_operacion");
/*  52 */     if (_operacion == null || _operacion.length() == 0) {
/*  53 */       _operacion = "X";
/*     */     }
/*     */     
/*  56 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  57 */       creacion(comms);
/*  58 */       listar(comms, _operacion);
/*     */     } 
/*  60 */     this.pagHTML = (ContAdicionContratoActHTML)comms.xmlcFactory.create(ContAdicionContratoActHTML.class);
/*  61 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  62 */       listar(comms, _operacion);
/*     */     
/*     */     }
/*  65 */     else if (_operacion.equals("P")) {
/*  66 */       editar(comms);
/*  67 */       listar(comms, _operacion);
/*     */     }
/*  69 */     else if (_operacion.equals("Nuevo")) {
/*  70 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/*  71 */       sel.getParentNode().removeChild(sel);
/*  72 */       sel = this.pagHTML.getElementPolizas();
/*  73 */       sel.getParentNode().removeChild(sel);
/*  74 */       sel = this.pagHTML.getElementImpuestos();
/*  75 */       sel.getParentNode().removeChild(sel);
/*  76 */       sel = this.pagHTML.getElementAgregarCdp();
/*  77 */       sel.getParentNode().removeChild(sel);
/*  78 */       sel = this.pagHTML.getElementBtnCodigoRp();
/*  79 */       sel.getParentNode().removeChild(sel);
/*  80 */       sel = this.pagHTML.getElementGenerarAdicion();
/*  81 */       sel.getParentNode().removeChild(sel);
/*  82 */       sel = this.pagHTML.getElementBtnNuevo();
/*  83 */       sel.getParentNode().removeChild(sel);
/*  84 */       Varios oVarios = new Varios();
/*     */       
/*  86 */       boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "oOficinaJuridica");
/*  87 */       if (!oPermisoAct) {
/*  88 */         HTMLElement elem = this.pagHTML.getElementBtnGrabar();
/*  89 */         elem.getParentNode().removeChild(elem);
/*     */       } 
/*  91 */       nuevo(comms);
/*  92 */       listar(comms, _operacion);
/*     */     }
/*  94 */     else if (_operacion.equals("GenerarAdicion")) {
/*  95 */       generarAdicion(comms);
/*     */     } 
/*  97 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  98 */       HTMLElement sel = this.pagHTML.getElementTrCreacionRegistro();
/*  99 */       sel.getParentNode().removeChild(sel);
/* 100 */       Varios oVarios = new Varios();
/* 101 */       boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "oOficinaJuridica");
/* 102 */       if (!oPermisoAct);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 107 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/* 108 */     comms.response.writeDOM(this.pagHTML);
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
/* 120 */     String _operacion = comms.request.getParameter("_operacion");
/* 121 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */     
/* 123 */     int consecutivoContrato = 0;
/*     */     try {
/* 125 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/*     */     }
/* 127 */     catch (Exception e) {}
/*     */     
/* 129 */     int consecutivoAdicion = 0;
/*     */     try {
/* 131 */       consecutivoAdicion = Integer.parseInt(comms.request.getParameter("consecutivoAdicion"));
/*     */     }
/* 133 */     catch (Exception e) {}
/*     */     
/* 135 */     String valorContrato = comms.request.getParameter("valorContrato");
/* 136 */     if (valorContrato == null) {
/* 137 */       valorContrato = "";
/*     */     }
/* 139 */     String numeroContrato = comms.request.getParameter("numeroContrato");
/* 140 */     if (numeroContrato == null) {
/* 141 */       numeroContrato = "";
/*     */     }
/* 143 */     int numeroEstudio = 0;
/*     */     try {
/* 145 */       numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
/*     */     }
/* 147 */     catch (Exception e) {}
/*     */     
/* 149 */     boolean rta = false;
/* 150 */     if (_operacion.equals("E")) {
/* 151 */       ContAdicionContratoDAO rs = new ContAdicionContratoDAO();
/* 152 */       if (!rs.getEstadoConexion()) {
/* 153 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 155 */       rta = rs.eliminarRegistro(consecutivoContrato, consecutivoAdicion);
/* 156 */       rs.close();
/* 157 */       if (!rta) {
/* 158 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorContAdicionContrato"));
/*     */       }
/* 160 */       String sPagina = "ContAdicionContratoAct.po?_operacion=Nuevo&valorContrato=" + valorContrato + "&consecutivoContrato=" + consecutivoContrato + "&numeroContrato=" + numeroContrato + "&numeroEstudio=" + numeroEstudio;
/*     */       
/* 162 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 164 */     String tipoAdicion = comms.request.getParameter("tipoAdicion");
/*     */     
/* 166 */     double valorAdicionado = 0.0D;
/*     */     try {
/* 168 */       valorAdicionado = Double.parseDouble(comms.request.getParameter("valorAdicionado"));
/*     */     }
/* 170 */     catch (Exception e) {}
/*     */     
/* 172 */     int plazoAdicionado = 0;
/*     */     try {
/* 174 */       plazoAdicionado = Integer.parseInt(comms.request.getParameter("plazoAdicionado"));
/*     */     }
/* 176 */     catch (Exception e) {}
/*     */     
/* 178 */     int unidadPlazo = 0;
/*     */     try {
/* 180 */       unidadPlazo = Integer.parseInt(comms.request.getParameter("unidadPlazo"));
/*     */     }
/* 182 */     catch (Exception e) {}
/*     */     
/* 184 */     String numCertificacionAdd = comms.request.getParameter("numCertificacionAdd");
/*     */     
/* 186 */     String fechaCertificacionAdd = comms.request.getParameter("fechaCertificacionAdd");
/*     */     
/* 188 */     String servicioAdicionado = comms.request.getParameter("servicioAdicionado");
/*     */     
/* 190 */     String clausulas = comms.request.getParameter("clausulas");
/*     */     
/* 192 */     String justificacion = comms.request.getParameter("justificacion");
/*     */ 
/*     */     
/* 195 */     ContAdicionContratoDAO rs = new ContAdicionContratoDAO();
/* 196 */     if (!rs.getEstadoConexion()) {
/* 197 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 199 */     rta = false;
/* 200 */     LetrasDTO lt = new LetrasDTO(valorAdicionado);
/* 201 */     String valorAdicionadoTexto = lt.toString();
/* 202 */     if (_operacion.equals("C")) {
/* 203 */       int registro = rs.crearRegistro(consecutivoContrato, tipoAdicion, valorAdicionado, plazoAdicionado, unidadPlazo, clausulas, justificacion, numCertificacionAdd, fechaCertificacionAdd, valorAdicionadoTexto, servicioAdicionado, elUsuario);
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
/* 217 */       if (registro > 0) {
/* 218 */         rta = true;
/* 219 */         consecutivoAdicion = registro;
/*     */       } 
/*     */     } else {
/*     */       
/* 223 */       rta = rs.modificarRegistro(consecutivoAdicion, consecutivoContrato, tipoAdicion, valorAdicionado, plazoAdicionado, unidadPlazo, clausulas, justificacion, numCertificacionAdd, fechaCertificacionAdd, valorAdicionadoTexto, servicioAdicionado, elUsuario);
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
/* 239 */     rs.close();
/* 240 */     ContContratoDAO rsC = new ContContratoDAO();
/* 241 */     rsC.generarFechas(-1, consecutivoContrato);
/* 242 */     rsC.close();
/* 243 */     if (!rta) {
/* 244 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorContAdicionContrato"));
/*     */     }
/* 246 */     String sPagina = "ContAdicionContratoAct.po?_operacion=P&consecutivoAdicion=" + consecutivoAdicion + "&valorContrato=" + valorContrato + "&consecutivoContrato=" + consecutivoContrato + "&numeroEstudio=" + numeroEstudio;
/*     */     
/* 248 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 259 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/* 262 */     int consecutivoContrato = 0;
/*     */     try {
/* 264 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/*     */     }
/* 266 */     catch (Exception e) {}
/*     */     
/* 268 */     int numeroEstudio = 0;
/*     */     try {
/* 270 */       numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
/*     */     }
/* 272 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 275 */     ContAdicionContratoDAO rs = new ContAdicionContratoDAO();
/* 276 */     if (!rs.getEstadoConexion()) {
/* 277 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 279 */     Collection arr = rs.cargarTodos(consecutivoContrato);
/*     */     
/* 281 */     rs.close();
/* 282 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 283 */     int cuantas = 0;
/* 284 */     Iterator iterator = arr.iterator();
/* 285 */     while (iterator.hasNext()) {
/* 286 */       ContAdicionContratoDTO reg = (ContAdicionContratoDTO)iterator.next();
/* 287 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 288 */       String url = "ContAdicionContratoAct.po?_operacion=P&consecutivoAdicion=" + reg.getConsecutivoAdicion() + "&valorContrato=" + reg.getValorContrato() + "&consecutivoContrato=" + reg.getConsecutivoContrato() + "&numeroEstudio=" + numeroEstudio;
/*     */       
/* 290 */       eltr.appendChild(newtdhref("" + reg.getDescripcionTipoAdicion(), url));
/* 291 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaCertificacionAdd())));
/* 292 */       eltr.appendChild(newtd("" + ((reg.getClausulas().length() > 50) ? (reg.getClausulas().substring(0, 50) + "...") : reg.getClausulas())));
/* 293 */       hte.appendChild(eltr);
/* 294 */       cuantas++;
/*     */     } 
/* 296 */     arr.clear();
/* 297 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/*     */   private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 311 */     int consecutivoAdicion = 0;
/*     */     try {
/* 313 */       consecutivoAdicion = Integer.parseInt(comms.request.getParameter("consecutivoAdicion"));
/* 314 */     } catch (Exception e) {}
/*     */     
/* 316 */     int consecutivoContrato = 0;
/*     */     try {
/* 318 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/*     */     }
/* 320 */     catch (Exception e) {}
/*     */     
/* 322 */     int numeroEstudio = 0;
/*     */     try {
/* 324 */       numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
/*     */     }
/* 326 */     catch (Exception e) {}
/*     */     
/* 328 */     this.pagHTML.getElementNumeroEstudio().setValue("" + numeroEstudio);
/* 329 */     ContAdicionContratoDAO rs = new ContAdicionContratoDAO();
/* 330 */     if (!rs.getEstadoConexion()) {
/* 331 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 333 */     ContAdicionContratoDTO reg = rs.cargarRegistro(consecutivoContrato, consecutivoAdicion);
/* 334 */     rs.close();
/* 335 */     Varios oVarios = new Varios();
/* 336 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/* 337 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "oOficinaJuridica");
/*     */     
/* 339 */     if (!oPermisoAct) {
/* 340 */       HTMLElement elem = this.pagHTML.getElementBtnGrabar();
/* 341 */       elem.getParentNode().removeChild(elem);
/*     */       
/* 343 */       elem = this.pagHTML.getElementBtnEliminar();
/* 344 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 346 */     if (reg != null) {
/* 347 */       this.pagHTML.getElementConsecutivoAdicion().setValue("" + reg.getConsecutivoAdicion());
/* 348 */       this.pagHTML.getElementConsecutivoContrato().setValue("" + reg.getConsecutivoContrato());
/* 349 */       this.pagHTML.getElementPlazoAdicionado().setValue("" + reg.getPlazoAdicionado());
/* 350 */       this.pagHTML.getElementNumCertificacionAdd().setValue("" + reg.getNumCertificadoAdd());
/* 351 */       this.pagHTML.getElementFechaCertificacionAdd().setValue("" + Utilidades.darFormatoFecha(reg.getFechaCertificacionAdd()));
/* 352 */       this.pagHTML.getElementNumeroContrato().setValue("" + reg.getNumeroContrato());
/* 353 */       this.pagHTML.getElementValorAdicionado().setValue("" + Utilidades.formatDouble(reg.getValorAdicionado()));
/* 354 */       this.pagHTML.getElementValorContrato().setValue("" + reg.getValorContrato());
/* 355 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 356 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 357 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 358 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 359 */       if (reg.getTipoAdicion().equals("2")) {
/* 360 */         cargarServicios(comms);
/*     */       }
/* 362 */       this.pagHTML.setTextJustificacion("" + reg.getJustificacion());
/* 363 */       this.pagHTML.setTextServicioAdicionado("" + reg.getServicioAdicionado());
/* 364 */       this.pagHTML.setTextScriptClausulas("" + cargarClausulas(reg.getClausulas()));
/*     */       
/* 366 */       HTMLSelectElement combo = this.pagHTML.getElementTipoAdicion();
/* 367 */       comboMultivalores(combo, "tipo_adicion_contrato", "" + reg.getTipoAdicion(), false);
/*     */       
/* 369 */       combo = this.pagHTML.getElementUnidadPlazo();
/* 370 */       comboMultivalores(combo, "tipo_plazo", "" + reg.getUnidadPlazo(), true);
/*     */       
/* 372 */       this.pagHTML.getElementConsecutivoAdicion().setReadOnly(true);
/* 373 */       this.pagHTML.getElement_operacion().setValue("M");
/*     */     } 
/*     */   }
/*     */   
/*     */   private String cargarClausulas(String clausulas) {
/* 378 */     String[] arrClausulas = clausulas.split("~");
/* 379 */     String script = "var arrClausulas=new Array(" + arrClausulas.length + ");";
/* 380 */     script = script + " var iCont=0;";
/* 381 */     int i = 0;
/* 382 */     while (i < arrClausulas.length) {
/* 383 */       script = script + " arrClausulas[iCont++]='" + arrClausulas[i] + "';";
/* 384 */       i++;
/*     */     } 
/* 386 */     return script;
/*     */   }
/*     */ 
/*     */   
/*     */   private void cargarServicios(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 391 */     ContEstudioPrevioServiciosDAO rsSrvc = new ContEstudioPrevioServiciosDAO();
/* 392 */     if (!rsSrvc.getEstadoConexion()) {
/* 393 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 395 */     int numeroEstudio = 0;
/*     */     try {
/* 397 */       numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
/*     */     }
/* 399 */     catch (Exception e) {}
/* 400 */     int consecutivoContrato = 0;
/*     */     try {
/* 402 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/*     */     }
/* 404 */     catch (Exception e) {}
/* 405 */     Collection arr = rsSrvc.cargarTodosAdicion(numeroEstudio, consecutivoContrato);
/* 406 */     rsSrvc.close();
/* 407 */     Iterator it = arr.iterator();
/* 408 */     HTMLTableElement hte = this.pagHTML.getElementTSrvc();
/* 409 */     boolean fondo = false;
/* 410 */     while (it.hasNext()) {
/* 411 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 412 */       fondo = !fondo;
/* 413 */       ContEstudioPrevioServiciosDTO reg = (ContEstudioPrevioServiciosDTO)it.next();
/* 414 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/* 415 */       eltr.appendChild(newtd("" + reg.getCodigoServicio()));
/* 416 */       eltr.appendChild(newtd("" + reg.getNombreServicio()));
/* 417 */       eltr.appendChild(newtd("" + reg.getNombreMunicipio()));
/* 418 */       eltr.appendChild(newtd("" + reg.getFactor()));
/* 419 */       eltr.appendChild(newtd("" + reg.getAfiliados()));
/*     */       
/* 421 */       int valorMes = (int)(reg.getValoUpc() * reg.getFactor() * reg.getAfiliados() / 100.0D);
/* 422 */       eltr.appendChild(newtd("" + Utilidades.miles(valorMes)));
/* 423 */       hte.appendChild(eltr);
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
/*     */   private void nuevo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 435 */     String consecutivoContrato = comms.request.getParameter("consecutivoContrato");
/* 436 */     if (consecutivoContrato == null) {
/* 437 */       consecutivoContrato = "";
/*     */     }
/* 439 */     String numeroContrato = comms.request.getParameter("numeroContrato");
/* 440 */     if (numeroContrato == null) {
/* 441 */       numeroContrato = "";
/*     */     }
/* 443 */     String valorContrato = comms.request.getParameter("valorContrato");
/* 444 */     if (valorContrato == null) {
/* 445 */       valorContrato = "";
/*     */     }
/* 447 */     int numeroEstudio = 0;
/*     */     try {
/* 449 */       numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
/*     */     }
/* 451 */     catch (Exception e) {}
/*     */     
/* 453 */     this.pagHTML.getElementNumeroEstudio().setValue("" + numeroEstudio);
/* 454 */     this.pagHTML.getElementValorContrato().setValue("" + valorContrato);
/* 455 */     this.pagHTML.getElementNumeroContrato().setValue("" + numeroContrato);
/* 456 */     this.pagHTML.getElementConsecutivoContrato().setValue("" + consecutivoContrato);
/* 457 */     HTMLSelectElement combo = this.pagHTML.getElementTipoAdicion();
/* 458 */     comboMultivalores(combo, "tipo_adicion_contrato", "", true);
/* 459 */     combo = this.pagHTML.getElementUnidadPlazo();
/* 460 */     comboMultivalores(combo, "tipo_plazo", "", true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void generarAdicion(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 465 */     String elUsuario = "" + comms.session.getUser().getName();
/* 466 */     String tipoEstudio = comms.request.getParameter("tipoEstudio");
/* 467 */     int consecutivoContrato = 0;
/*     */     try {
/* 469 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/*     */     }
/* 471 */     catch (Exception e) {}
/*     */     
/* 473 */     int consecutivoAdicion = 0;
/*     */     try {
/* 475 */       consecutivoAdicion = Integer.parseInt(comms.request.getParameter("consecutivoAdicion"));
/*     */     }
/* 477 */     catch (Exception e) {}
/*     */     
/* 479 */     int numeroEstudio = 0;
/*     */     try {
/* 481 */       numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
/*     */     }
/* 483 */     catch (Exception e) {}
/*     */     
/* 485 */     DocumentosDAO rs = new DocumentosDAO();
/* 486 */     if (!rs.getEstadoConexion()) {
/* 487 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 489 */     String rta = rs.crearFormatoAdicion(consecutivoContrato, consecutivoAdicion, elUsuario);
/* 490 */     rs.close();
/*     */     
/* 492 */     if (rta != null) {
/* 493 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorGenerandoDocContrato&texto=" + rta));
/*     */     }
/* 495 */     String sPagina = "ContContratoAct.po?_operacion=P&consecutivoContrato=" + consecutivoContrato + "&tipoEstudio=" + tipoEstudio + "&numeroEstudio=" + numeroEstudio;
/* 496 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/*     */   private Attr newAttr(String tipo, String valor) {
/* 510 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 511 */     atrib.setValue(valor);
/* 512 */     return atrib;
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
/* 525 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 526 */     Element enlace = this.pagHTML.createElement("a");
/* 527 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 528 */     enlace.appendChild(hijo);
/* 529 */     Attr donde = this.pagHTML.createAttribute("href");
/* 530 */     donde.setValue(vinculo);
/* 531 */     enlace.setAttributeNode(donde);
/* 532 */     td.appendChild(enlace);
/* 533 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 534 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 544 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 545 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 546 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 547 */     return td;
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
/*     */   private void comboMultivalores(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco) {
/* 562 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 563 */     Collection arr = rs.cargarTabla(tabla);
/* 564 */     rs.close();
/* 565 */     if (dejarBlanco) {
/* 566 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 567 */       op.setValue("");
/* 568 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 569 */       combo.appendChild(op);
/*     */     } 
/* 571 */     Iterator iterator = arr.iterator();
/* 572 */     while (iterator.hasNext()) {
/* 573 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 574 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 575 */       op.setValue("" + reg.getCodigo());
/* 576 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 577 */       if (defecto.equals(reg.getCodigo())) {
/* 578 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 579 */         escogida.setValue("on");
/* 580 */         op.setAttributeNode(escogida);
/*     */       } 
/* 582 */       combo.appendChild(op);
/*     */     } 
/* 584 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ContAdicionContratoAct.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */