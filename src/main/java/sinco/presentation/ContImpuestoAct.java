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
/*     */ import sinco.business.ContImpuestoDTO;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContImpuestoDAO;
/*     */ import sinco.data.ContRpDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.ContImpuestoAct;
/*     */ import sinco.presentation.ContImpuestoActHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContImpuestoAct
/*     */   implements HttpPresentation
/*     */ {
/*     */   private ContImpuestoActHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  38 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  39 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  42 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*     */ 
/*     */     
/*  45 */     String _operacion = comms.request.getParameter("_operacion");
/*  46 */     if (_operacion == null || _operacion.length() == 0) {
/*  47 */       _operacion = "X";
/*     */     }
/*     */     
/*  50 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  51 */       creacion(comms);
/*     */     }
/*  53 */     this.pagHTML = (ContImpuestoActHTML)comms.xmlcFactory.create(ContImpuestoActHTML.class);
/*  54 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  55 */       listar(comms, _operacion);
/*     */     
/*     */     }
/*  58 */     else if (_operacion.equals("P")) {
/*  59 */       editar(comms);
/*  60 */       listar(comms, _operacion);
/*     */     }
/*  62 */     else if (_operacion.equals("Nuevo")) {
/*  63 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/*  64 */       sel.getParentNode().removeChild(sel);
/*  65 */       sel = this.pagHTML.getElementBtnAgregarImpuesto();
/*  66 */       sel.getParentNode().removeChild(sel);
/*  67 */       Varios oVarios = new Varios();
/*  68 */       int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */       
/*  70 */       boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "oOficinaJuridica");
/*  71 */       if (!oPermisoAct) {
/*  72 */         HTMLElement elem = this.pagHTML.getElementBtnGrabar();
/*  73 */         elem.getParentNode().removeChild(elem);
/*     */       } 
/*  75 */       nuevo(comms);
/*  76 */       listar(comms, _operacion);
/*     */     }
/*  78 */     else if (_operacion.equals("Impuesto")) {
/*  79 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/*  80 */       sel.getParentNode().removeChild(sel);
/*  81 */       sel = this.pagHTML.getElementBtnAgregarImpuesto();
/*  82 */       sel.getParentNode().removeChild(sel);
/*  83 */       impuesto(comms);
/*  84 */       listar(comms, _operacion);
/*     */     } 
/*  86 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  87 */       HTMLElement sel = this.pagHTML.getElementTrCreacionRegistro();
/*  88 */       sel.getParentNode().removeChild(sel);
/*  89 */       sel = this.pagHTML.getElementBtnAgregarImpuesto();
/*  90 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/*  92 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  93 */     comms.response.writeDOM(this.pagHTML);
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
/* 105 */     String _operacion = comms.request.getParameter("_operacion");
/* 106 */     String elUsuario = "" + comms.session.getUser().getName();
/* 107 */     int consecutivoContrato = 0;
/*     */     try {
/* 109 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/*     */     }
/* 111 */     catch (Exception e) {}
/*     */     
/* 113 */     int consecutivoAdicion = 0;
/*     */     try {
/* 115 */       consecutivoAdicion = Integer.parseInt(comms.request.getParameter("consecutivoAdicion"));
/*     */     }
/* 117 */     catch (Exception e) {}
/*     */     
/* 119 */     String numeroContrato = comms.request.getParameter("numeroContrato");
/* 120 */     if (numeroContrato == null) {
/* 121 */       numeroContrato = "";
/*     */     }
/* 123 */     String fechaRp = comms.request.getParameter("fechaRp");
/* 124 */     if (fechaRp == null) {
/* 125 */       fechaRp = "";
/*     */     }
/* 127 */     String numeroEstudio = comms.request.getParameter("numeroEstudio");
/* 128 */     if (numeroEstudio == null) {
/* 129 */       numeroEstudio = "";
/*     */     }
/*     */     
/* 132 */     String numeroRecibo = comms.request.getParameter("numeroRecibo");
/* 133 */     String descripcion = comms.request.getParameter("descripcion");
/* 134 */     String origen = comms.request.getParameter("origen");
/* 135 */     boolean rta = false;
/* 136 */     if (_operacion.equals("E")) {
/* 137 */       ContImpuestoDAO rs = new ContImpuestoDAO();
/* 138 */       if (!rs.getEstadoConexion()) {
/* 139 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 141 */       rta = rs.eliminarRegistro(consecutivoContrato, numeroRecibo, descripcion);
/*     */ 
/*     */       
/* 144 */       rs.close();
/*     */       
/* 146 */       if (!rta) {
/* 147 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorContImpuesto"));
/*     */       }
/* 149 */       String sPagina = "ContImpuestoAct.po?_operacion=Nuevo&consecutivoContrato=" + consecutivoContrato + "&numeroRecibo=" + numeroRecibo + "&descripcion=" + descripcion + "&numeroEstudio=" + numeroEstudio + "&fechaRp=" + fechaRp + "&numeroContrato=" + numeroContrato + "&origen=" + origen + "&consecutivoAdiion=" + consecutivoAdicion;
/*     */       
/* 151 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 153 */     String fechaPago = comms.request.getParameter("fechaPago");
/* 154 */     int valorPago = 0;
/*     */     try {
/* 156 */       valorPago = Integer.parseInt(comms.request.getParameter("valorPago"));
/*     */     }
/* 158 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 161 */     ContImpuestoDAO rs = new ContImpuestoDAO();
/* 162 */     if (!rs.getEstadoConexion()) {
/* 163 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 165 */     rta = false;
/* 166 */     if (_operacion.equals("C")) {
/* 167 */       rta = rs.crearRegistro(consecutivoContrato, numeroRecibo, fechaPago, valorPago, descripcion, elUsuario);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 176 */       rta = rs.modificarRegistro(consecutivoContrato, numeroRecibo, fechaPago, valorPago, descripcion, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 184 */     rs.close();
/* 185 */     if (!rta) {
/* 186 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorContImpuesto"));
/*     */     }
/* 188 */     String sPagina = "ContImpuestoAct.po?_operacion=P&consecutivoContrato=" + consecutivoContrato + "&numeroRecibo=" + numeroRecibo + "&descripcion=" + descripcion + "&numeroEstudio=" + numeroEstudio + "&fechaRp=" + fechaRp + "&numeroContrato=" + numeroContrato + "&origen=" + origen + "&consecutivoAdicion=" + consecutivoAdicion;
/*     */     
/* 190 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 201 */     int consecutivoContrato = 0;
/*     */     try {
/* 203 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/*     */     }
/* 205 */     catch (Exception e) {
/* 206 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=consecutivoContrato"));
/*     */     } 
/* 208 */     int consecutivoAdicion = 0;
/*     */     try {
/* 210 */       consecutivoAdicion = Integer.parseInt(comms.request.getParameter("consecutivoAdicion"));
/*     */     }
/* 212 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 215 */     String numeroRecibo = comms.request.getParameter("numeroRecibo");
/* 216 */     if (numeroRecibo == null) {
/* 217 */       numeroRecibo = "";
/*     */     }
/* 219 */     String descripcion = comms.request.getParameter("descripcion");
/* 220 */     if (descripcion == null) {
/* 221 */       descripcion = "";
/*     */     }
/* 223 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/* 226 */     String numeroEstudio = comms.request.getParameter("numeroEstudio");
/* 227 */     if (numeroEstudio == null) {
/* 228 */       numeroEstudio = "";
/*     */     }
/*     */     
/* 231 */     String origen = comms.request.getParameter("origen");
/* 232 */     String fechaRp = comms.request.getParameter("fechaRp");
/* 233 */     if (fechaRp == null) {
/* 234 */       ContRpDAO rs = new ContRpDAO();
/* 235 */       if (!rs.getEstadoConexion()) {
/* 236 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 238 */       rs.close();
/*     */     } 
/*     */     
/* 241 */     String numeroContrato = comms.request.getParameter("numeroContrato");
/* 242 */     if (numeroContrato == null) {
/* 243 */       numeroContrato = "";
/*     */     }
/*     */     
/* 246 */     ContImpuestoDAO rs = new ContImpuestoDAO();
/* 247 */     if (!rs.getEstadoConexion()) {
/* 248 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 250 */     Collection<ContImpuestoDTO> arr = rs.cargarTodos(consecutivoContrato, "", "", "", "");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 256 */     rs.close();
/* 257 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 258 */     int cuantas = 0;
/* 259 */     Iterator<ContImpuestoDTO> iterator = arr.iterator();
/* 260 */     while (iterator.hasNext()) {
/* 261 */       ContImpuestoDTO reg = (ContImpuestoDTO)iterator.next();
/* 262 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 263 */       eltr.appendChild(newtd("" + reg.getConsecutivoContrato()));
/* 264 */       String url = "ContImpuestoAct.po?_operacion=P&consecutivoContrato=" + reg.getConsecutivoContrato() + "&numeroRecibo=" + reg.getNumeroRecibo() + "&descripcion=" + reg.getDescripcion() + "&numeroEstudio=" + numeroEstudio + "&fechaRp=" + fechaRp + "&numeroContrato=" + numeroContrato + "&consecutivoContrato=" + consecutivoContrato + "&origen=" + origen + "&consecutivoAdicion=" + consecutivoAdicion;
/*     */ 
/*     */       
/* 267 */       eltr.appendChild(newtdhref("" + reg.getTipoImpuestoDescripcion(), url));
/* 268 */       eltr.appendChild(newtd("" + reg.getNumeroRecibo()));
/* 269 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaPago())));
/* 270 */       eltr.appendChild(newtd("" + reg.getValorPago()));
/* 271 */       hte.appendChild(eltr);
/* 272 */       cuantas++;
/*     */     } 
/* 274 */     arr.clear();
/* 275 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/*     */   private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 290 */     int consecutivoContrato = 0;
/*     */     try {
/* 292 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/*     */     }
/* 294 */     catch (Exception e) {}
/*     */     
/* 296 */     int consecutivoAdicion = 0;
/*     */     try {
/* 298 */       consecutivoAdicion = Integer.parseInt(comms.request.getParameter("consecutivoAdicion"));
/*     */     }
/* 300 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 303 */     String numeroRecibo = comms.request.getParameter("numeroRecibo");
/* 304 */     String descripcion = comms.request.getParameter("descripcion");
/* 305 */     String origen = comms.request.getParameter("origen");
/* 306 */     String fechaRp = comms.request.getParameter("fechaRp");
/* 307 */     if (fechaRp == null) {
/* 308 */       fechaRp = "";
/*     */     }
/* 310 */     String numeroEstudio = comms.request.getParameter("numeroEstudio");
/* 311 */     if (numeroEstudio == null) {
/* 312 */       numeroEstudio = "";
/*     */     }
/* 314 */     ContImpuestoDAO rs = new ContImpuestoDAO();
/* 315 */     if (!rs.getEstadoConexion()) {
/* 316 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 319 */     this.pagHTML.getElementOrigen().setValue("" + origen);
/* 320 */     this.pagHTML.getElementConsecutivoAdicion().setValue("" + consecutivoAdicion);
/* 321 */     this.pagHTML.getElementFechaRp().setValue("" + fechaRp);
/* 322 */     this.pagHTML.getElementConsecutivoContrato().setValue("" + consecutivoContrato);
/* 323 */     this.pagHTML.getElementNumeroEstudio().setValue("" + numeroEstudio);
/*     */     
/* 325 */     ContImpuestoDTO reg = rs.cargarRegistro(consecutivoContrato, numeroRecibo, descripcion);
/*     */ 
/*     */     
/* 328 */     rs.close();
/* 329 */     Varios oVarios = new Varios();
/* 330 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/* 331 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "oOficinaJuridica");
/*     */     
/* 333 */     if (!oPermisoAct) {
/* 334 */       HTMLElement elem = this.pagHTML.getElementBtnGrabar();
/* 335 */       elem.getParentNode().removeChild(elem);
/*     */       
/* 337 */       elem = this.pagHTML.getElementBtnEliminar();
/* 338 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 340 */     if (reg != null) {
/* 341 */       this.pagHTML.getElementConsecutivoContrato().setValue("" + reg.getConsecutivoContrato());
/* 342 */       this.pagHTML.getElementNumeroContrato().setValue("" + reg.getNumeroContrato());
/* 343 */       this.pagHTML.getElementNumeroRecibo().setValue("" + reg.getNumeroRecibo());
/* 344 */       this.pagHTML.getElementFechaPago().setValue("" + Utilidades.darFormatoFecha(reg.getFechaPago()));
/* 345 */       this.pagHTML.getElementValorPago().setValue("" + reg.getValorPago());
/* 346 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 347 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 348 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 349 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 350 */       HTMLSelectElement combo = this.pagHTML.getElementDescripcion();
/* 351 */       comboMultivalores(combo, "tipo_impuesto", "" + reg.getDescripcion(), true);
/*     */       
/* 353 */       this.pagHTML.getElementConsecutivoContrato().setReadOnly(true);
/* 354 */       this.pagHTML.getElementNumeroRecibo().setReadOnly(true);
/* 355 */       this.pagHTML.getElement_operacion().setValue("M");
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
/* 367 */     int consecutivoContrato = 0;
/*     */     try {
/* 369 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/* 370 */     } catch (Exception e) {}
/* 371 */     String numeroEstudio = comms.request.getParameter("numeroEstudio");
/* 372 */     if (numeroEstudio == null) {
/* 373 */       numeroEstudio = "";
/*     */     }
/* 375 */     String numeroContrato = comms.request.getParameter("numeroContrato");
/* 376 */     if (numeroContrato == null) {
/* 377 */       numeroContrato = "";
/*     */     }
/* 379 */     int consecutivoAdicion = 0;
/*     */     try {
/* 381 */       consecutivoAdicion = Integer.parseInt(comms.request.getParameter("consecutivoAdicion"));
/*     */     }
/* 383 */     catch (Exception e) {}
/*     */     
/* 385 */     String origen = comms.request.getParameter("origen");
/* 386 */     String fechaRp = comms.request.getParameter("fechaRp");
/* 387 */     if (fechaRp == null) {
/* 388 */       ContRpDAO rs = new ContRpDAO();
/* 389 */       if (!rs.getEstadoConexion()) {
/* 390 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 392 */       fechaRp = rs.cargarFecha(consecutivoContrato);
/* 393 */       rs.close();
/*     */       
/* 395 */       if (fechaRp != null) {
/* 396 */         fechaRp = Utilidades.darFormatoFecha(fechaRp);
/*     */       }
/*     */     } 
/* 399 */     this.pagHTML.getElementOrigen().setValue("" + origen);
/* 400 */     this.pagHTML.getElementConsecutivoAdicion().setValue("" + consecutivoAdicion);
/* 401 */     this.pagHTML.getElementNumeroContrato().setValue("" + numeroContrato);
/* 402 */     this.pagHTML.getElementConsecutivoContrato().setValue("" + consecutivoContrato);
/* 403 */     this.pagHTML.getElementNumeroEstudio().setValue("" + numeroEstudio);
/* 404 */     this.pagHTML.getElementNumeroContrato().setValue("" + numeroContrato);
/* 405 */     this.pagHTML.getElementFechaRp().setValue("" + fechaRp);
/* 406 */     HTMLSelectElement combo = this.pagHTML.getElementDescripcion();
/* 407 */     comboMultivalores(combo, "tipo_impuesto", "", true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void impuesto(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 417 */     String numeroContrato = comms.request.getParameter("numeroContrato");
/* 418 */     if (numeroContrato == null) {
/* 419 */       numeroContrato = "";
/*     */     }
/* 421 */     String numeroRecibo = comms.request.getParameter("numeroRecibo");
/* 422 */     if (numeroRecibo == null) {
/* 423 */       numeroRecibo = "";
/*     */     }
/* 425 */     String fechaPago = comms.request.getParameter("fechaPago");
/* 426 */     if (fechaPago == null) {
/* 427 */       fechaPago = "";
/*     */     }
/* 429 */     String fechaRp = comms.request.getParameter("fechaRp");
/* 430 */     if (fechaRp == null) {
/* 431 */       fechaRp = "";
/*     */     }
/* 433 */     String consecutivoContrato = comms.request.getParameter("consecutivoContrato");
/* 434 */     if (consecutivoContrato == null) {
/* 435 */       consecutivoContrato = "";
/*     */     }
/* 437 */     String numeroEstudio = comms.request.getParameter("numeroEstudio");
/* 438 */     if (numeroEstudio == null) {
/* 439 */       numeroEstudio = "";
/*     */     }
/* 441 */     String origen = comms.request.getParameter("origen");
/*     */     
/* 443 */     this.pagHTML.getElementOrigen().setValue("" + origen);
/* 444 */     this.pagHTML.getElementNumeroContrato().setValue("" + numeroContrato);
/* 445 */     this.pagHTML.getElementNumeroRecibo().setValue("" + numeroRecibo);
/* 446 */     this.pagHTML.getElementFechaPago().setValue("" + fechaPago);
/* 447 */     this.pagHTML.getElementFechaRp().setValue("" + fechaRp);
/* 448 */     this.pagHTML.getElementConsecutivoContrato().setValue("" + consecutivoContrato);
/* 449 */     this.pagHTML.getElementNumeroEstudio().setValue("" + numeroEstudio);
/* 450 */     HTMLSelectElement combo = this.pagHTML.getElementDescripcion();
/* 451 */     comboMultivalores(combo, "tipo_impuesto", "", true);
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
/*     */   private Attr newAttr(String tipo, String valor) {
/* 463 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 464 */     atrib.setValue(valor);
/* 465 */     return atrib;
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
/* 478 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 479 */     Element enlace = this.pagHTML.createElement("a");
/* 480 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 481 */     enlace.appendChild(hijo);
/* 482 */     Attr donde = this.pagHTML.createAttribute("href");
/* 483 */     donde.setValue(vinculo);
/* 484 */     enlace.setAttributeNode(donde);
/* 485 */     td.appendChild(enlace);
/* 486 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 487 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 497 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 498 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 499 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 500 */     return td;
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
/* 515 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 516 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 517 */     rs.close();
/* 518 */     if (dejarBlanco) {
/* 519 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 520 */       op.setValue("");
/* 521 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 522 */       combo.appendChild(op);
/*     */     } 
/* 524 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 525 */     while (iterator.hasNext()) {
/* 526 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 527 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 528 */       op.setValue("" + reg.getCodigo());
/* 529 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 530 */       if (defecto.equals(reg.getCodigo())) {
/* 531 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 532 */         escogida.setValue("on");
/* 533 */         op.setAttributeNode(escogida);
/*     */       } 
/* 535 */       combo.appendChild(op);
/*     */     } 
/* 537 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ContImpuestoAct.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */