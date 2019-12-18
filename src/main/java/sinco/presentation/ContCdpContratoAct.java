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
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.ContCdpContratoDTO;
/*     */ import sinco.business.ContImputacionPresupuestalDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContCdpContratoDAO;
/*     */ import sinco.data.ContCdpDAO;
/*     */ import sinco.data.ContImputacionPresupuestalDAO;
/*     */ import sinco.presentation.ContCdpContratoAct;
/*     */ import sinco.presentation.ContCdpContratoActHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContCdpContratoAct
/*     */   implements HttpPresentation
/*     */ {
/*     */   private ContCdpContratoActHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  37 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  38 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  41 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  42 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */ 
/*     */     
/*  45 */     String _operacion = comms.request.getParameter("_operacion");
/*  46 */     if (_operacion == null) {
/*  47 */       _operacion = "Nuevo";
/*     */     }
/*  49 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  50 */       creacion(comms);
/*     */     }
/*  52 */     this.pagHTML = (ContCdpContratoActHTML)comms.xmlcFactory.create(ContCdpContratoActHTML.class);
/*  53 */     if (_operacion.equals("P")) {
/*  54 */       editar(comms);
/*  55 */       listar(comms, "L");
/*     */     }
/*  57 */     else if (_operacion.equals("Nuevo")) {
/*  58 */       nuevo(comms);
/*  59 */       listar(comms, "L");
/*  60 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/*  61 */       elem.getParentNode().removeChild(elem);
/*  62 */       Varios oVarios = new Varios();
/*  63 */       boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "oOficinaJuridica");
/*  64 */       if (!oPermisoAct) {
/*  65 */         elem = this.pagHTML.getElementBtnGrabar();
/*  66 */         elem.getParentNode().removeChild(elem);
/*     */       }
/*     */     
/*  69 */     } else if (_operacion.equals("L") || _operacion.equals("X")) {
/*  70 */       HTMLElement sel = this.pagHTML.getElementTrCreacionRegistro();
/*  71 */       sel.getParentNode().removeChild(sel);
/*  72 */       sel = this.pagHTML.getElementTrResultados();
/*  73 */       sel.getParentNode().removeChild(sel);
/*  74 */       listar(comms, _operacion);
/*     */     } 
/*  76 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  77 */       HTMLElement sel = this.pagHTML.getElementTrCreacionRegistro();
/*  78 */       sel.getParentNode().removeChild(sel);
/*  79 */       Varios oVarios = new Varios();
/*     */       
/*  81 */       boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "oOficinaJuridica");
/*  82 */       if (!oPermisoAct);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  87 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  88 */     comms.response.writeDOM(this.pagHTML);
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
/* 100 */     String _operacion = comms.request.getParameter("_operacion");
/* 101 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */     
/* 103 */     String origen = comms.request.getParameter("origen");
/* 104 */     if (origen == null) {
/* 105 */       origen = "";
/*     */     }
/* 107 */     int consecutivoAdicion = 0;
/*     */     try {
/* 109 */       consecutivoAdicion = Integer.parseInt(comms.request.getParameter("consecutivoAdicion"));
/*     */     }
/* 111 */     catch (Exception e) {}
/*     */     
/* 113 */     String numeroContrato = comms.request.getParameter("numeroContrato");
/* 114 */     if (numeroContrato == null) {
/* 115 */       numeroContrato = "";
/*     */     }
/* 117 */     String numeroEstudio = comms.request.getParameter("numeroEstudio");
/* 118 */     if (numeroEstudio == null) {
/* 119 */       numeroEstudio = "";
/*     */     }
/*     */     
/* 122 */     int consecutivoContrato = 0;
/*     */     try {
/* 124 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/*     */     }
/* 126 */     catch (Exception e) {}
/* 127 */     String codigoCertificado = comms.request.getParameter("codigoCertificado");
/* 128 */     String anio = comms.request.getParameter("anio");
/* 129 */     String imputacion = comms.request.getParameter("imputacion");
/*     */     
/* 131 */     boolean rta = false;
/* 132 */     if (_operacion.equals("E")) {
/* 133 */       ContCdpContratoDAO rs = new ContCdpContratoDAO();
/* 134 */       if (!rs.getEstadoConexion()) {
/* 135 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 137 */       rta = rs.eliminarRegistro(codigoCertificado, anio, consecutivoContrato);
/* 138 */       rs.close();
/* 139 */       if (!rta) {
/* 140 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPypActividad"));
/*     */       }
/* 142 */       String sPagina = "ContCdpContratoAct.po?_operacion=Nuevo&consecutivoContrato=" + consecutivoContrato + "&numeroContrato=" + numeroContrato + "&numeroEstudio=" + numeroEstudio + "&origen=" + origen + "&consecutivoAdicion=" + consecutivoAdicion;
/*     */       
/* 144 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/*     */     
/* 147 */     double valorCertificado = 0.0D;
/*     */     try {
/* 149 */       valorCertificado = Double.parseDouble(comms.request.getParameter("valorCertificado"));
/*     */     }
/* 151 */     catch (Exception e) {}
/* 152 */     String _operacionCDP = comms.request.getParameter("_operacionCDP");
/*     */ 
/*     */     
/* 155 */     String fechaExpedicion = comms.request.getParameter("fechaExpedicion");
/* 156 */     String fechaVencimiento = comms.request.getParameter("fechaVencimiento");
/* 157 */     ContCdpDAO rs = new ContCdpDAO();
/* 158 */     if (!rs.getEstadoConexion()) {
/* 159 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 161 */     rta = false;
/* 162 */     if (_operacionCDP.equals("C")) {
/* 163 */       rta = rs.crearRegistro(codigoCertificado, valorCertificado, fechaExpedicion, fechaVencimiento, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 169 */       if (rta) {
/* 170 */         ContCdpContratoDAO rsCdpC = new ContCdpContratoDAO();
/* 171 */         rta = rsCdpC.crearRegistro(consecutivoContrato, consecutivoAdicion, codigoCertificado, anio, imputacion, elUsuario);
/* 172 */         rsCdpC.close();
/*     */       }
/*     */     
/* 175 */     } else if (_operacionCDP.equals("M")) {
/* 176 */       rta = rs.modificarRegistro(codigoCertificado, valorCertificado, fechaExpedicion, fechaVencimiento, elUsuario);
/* 177 */       ContCdpContratoDAO rsCdpC = new ContCdpContratoDAO();
/* 178 */       if (rta && _operacion.equals("M")) {
/* 179 */         rta = rsCdpC.modificarRegistro(consecutivoContrato, consecutivoAdicion, codigoCertificado, anio, imputacion, elUsuario);
/* 180 */       } else if (rta && _operacion.equals("C")) {
/* 181 */         rta = rsCdpC.crearRegistro(consecutivoContrato, consecutivoAdicion, codigoCertificado, anio, imputacion, elUsuario);
/*     */       } 
/* 183 */       rsCdpC.close();
/*     */     } 
/* 185 */     rs.close();
/* 186 */     if (!rta) {
/* 187 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorContCdpContrato"));
/*     */     }
/* 189 */     String sPagina = "ContCdpContratoAct.po?_operacion=P&codigoCertificado=" + codigoCertificado + "&consecutivoContrato=" + consecutivoContrato + "&numeroContrato=" + numeroContrato + "&numeroEstudio=" + numeroEstudio + "&origen=" + origen + "&consecutivoAdicion=" + consecutivoAdicion + "&anio=" + anio;
/*     */     
/* 191 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 202 */     String codigoCertificado = comms.request.getParameter("codigoCertificado");
/* 203 */     if (codigoCertificado == null) {
/* 204 */       codigoCertificado = "";
/*     */     }
/* 206 */     String anio = comms.request.getParameter("anio");
/* 207 */     if (anio == null) {
/* 208 */       anio = "";
/*     */     }
/* 210 */     int consecutivoContrato = 0;
/*     */     try {
/* 212 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/*     */     }
/* 214 */     catch (Exception e) {}
/*     */     
/* 216 */     int consecutivoAdicion = 0;
/*     */     try {
/* 218 */       consecutivoAdicion = Integer.parseInt(comms.request.getParameter("consecutivoAdicion"));
/*     */     }
/* 220 */     catch (Exception e) {}
/*     */     
/* 222 */     String numeroContrato = comms.request.getParameter("numeroContrato");
/* 223 */     if (numeroContrato == null) {
/* 224 */       numeroContrato = "";
/*     */     }
/* 226 */     String numeroEstudio = comms.request.getParameter("numeroEstudio");
/* 227 */     if (numeroEstudio == null) {
/* 228 */       numeroEstudio = "";
/*     */     }
/* 230 */     String origen = comms.request.getParameter("origen");
/*     */     
/* 232 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */     
/* 236 */     ContCdpContratoDAO rs = new ContCdpContratoDAO();
/* 237 */     if (!rs.getEstadoConexion()) {
/* 238 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 240 */     Collection arr = rs.cargarTodos(consecutivoContrato);
/* 241 */     rs.close();
/*     */     
/* 243 */     int cuantas = 0;
/* 244 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 245 */     Iterator iterator = arr.iterator();
/* 246 */     while (iterator.hasNext()) {
/* 247 */       ContCdpContratoDTO reg = (ContCdpContratoDTO)iterator.next();
/* 248 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 249 */       eltr.appendChild(newtd("" + reg.getCodigoCertificado()));
/* 250 */       String url = "ContCdpContratoAct.po?_operacion=P&consecutivoContrato=" + reg.getConsecutivoContrato() + "&codigoCertificado=" + reg.getCodigoCertificado() + "&anio=" + reg.getAnio() + "&imputacion=" + reg.getImputacion() + "&numeroContrato=" + reg.getNumeroContrato() + "&numeroEstudio=" + numeroEstudio + "&origen=" + origen + "&consecutivoAdicion=" + consecutivoAdicion;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 258 */       eltr.appendChild(newtdhref("" + Utilidades.miles(reg.getValorCertificado(), 2), url));
/* 259 */       eltr.appendChild(newtd("" + reg.getImputacion()));
/* 260 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaExpedicion())));
/* 261 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaVencimiento())));
/* 262 */       hte.appendChild(eltr);
/* 263 */       cuantas++;
/*     */     } 
/* 265 */     arr.clear();
/* 266 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 281 */     int consecutivoContrato = 0;
/*     */     try {
/* 283 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/* 284 */     } catch (Exception e) {}
/*     */     
/* 286 */     String codigoCertificado = comms.request.getParameter("codigoCertificado");
/* 287 */     if (codigoCertificado == null) {
/* 288 */       codigoCertificado = "";
/*     */     }
/* 290 */     String anio = comms.request.getParameter("anio");
/* 291 */     if (anio == null) {
/* 292 */       anio = "";
/*     */     }
/*     */     
/* 295 */     String imputacion = comms.request.getParameter("imputacion");
/* 296 */     if (imputacion == null) {
/* 297 */       imputacion = "";
/*     */     }
/*     */     
/* 300 */     int consecutivoAdicion = 0;
/*     */     try {
/* 302 */       consecutivoAdicion = Integer.parseInt(comms.request.getParameter("consecutivoAdicion"));
/*     */     }
/* 304 */     catch (Exception e) {}
/*     */     
/* 306 */     int numeroEstudio = 0;
/*     */     try {
/* 308 */       numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
/* 309 */     } catch (Exception e) {}
/*     */     
/* 311 */     String numeroContrato = comms.request.getParameter("numeroContrato");
/* 312 */     if (numeroContrato == null) {
/* 313 */       numeroContrato = "";
/*     */     }
/* 315 */     String origen = comms.request.getParameter("origen");
/* 316 */     this.pagHTML.getElementOrigen().setValue("" + origen);
/* 317 */     this.pagHTML.getElementConsecutivoAdicion().setValue("" + consecutivoAdicion);
/* 318 */     this.pagHTML.getElementNumeroEstudio().setValue("" + numeroEstudio);
/* 319 */     this.pagHTML.getElementNumeroContrato().setValue("" + numeroContrato);
/* 320 */     this.pagHTML.getElementConsecutivoContrato().setValue("" + consecutivoContrato);
/* 321 */     this.pagHTML.getElementAnio().setValue("" + anio);
/*     */     
/* 323 */     ContCdpContratoDAO rs = new ContCdpContratoDAO();
/* 324 */     if (!rs.getEstadoConexion()) {
/* 325 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 327 */     ContCdpContratoDTO reg = rs.cargarRegistro(consecutivoContrato, codigoCertificado, anio);
/* 328 */     rs.close();
/* 329 */     Varios oVarios = new Varios();
/* 330 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 332 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "oOficinaJuridica");
/* 333 */     if (!oPermisoAct) {
/* 334 */       HTMLElement elem = this.pagHTML.getElementBtnGrabar();
/* 335 */       elem.getParentNode().removeChild(elem);
/* 336 */       elem = this.pagHTML.getElementBtnEliminar();
/* 337 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 339 */     if (reg != null) {
/* 340 */       this.pagHTML.getElementCodigoCertificado().setValue("" + reg.getCodigoCertificado());
/* 341 */       this.pagHTML.getElementValorCertificado().setValue("" + Utilidades.formatDouble(reg.getValorCertificado()));
/* 342 */       this.pagHTML.getElementFechaExpedicion().setValue("" + Utilidades.darFormatoFecha(reg.getFechaExpedicion()));
/* 343 */       this.pagHTML.getElementFechaVencimiento().setValue("" + Utilidades.darFormatoFecha(reg.getFechaVencimiento()));
/*     */       
/* 345 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 346 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 347 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 348 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/*     */       
/* 350 */       cargarImputacion(comms, consecutivoContrato, codigoCertificado, anio, consecutivoAdicion);
/*     */       
/* 352 */       this.pagHTML.getElementCodigoCertificado().setReadOnly(true);
/* 353 */       this.pagHTML.getElement_operacion().setValue("M");
/* 354 */       this.pagHTML.getElement_operacionCDP().setValue("M");
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
/* 366 */     int numeroEstudio = 0;
/*     */     try {
/* 368 */       numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
/* 369 */     } catch (Exception e) {}
/*     */     
/* 371 */     String consecutivoContrato = comms.request.getParameter("consecutivoContrato");
/* 372 */     if (consecutivoContrato == null) {
/* 373 */       consecutivoContrato = "";
/*     */     }
/* 375 */     int consecutivoAdicion = 0;
/*     */     try {
/* 377 */       consecutivoAdicion = Integer.parseInt(comms.request.getParameter("consecutivoAdicion"));
/*     */     }
/* 379 */     catch (Exception e) {}
/*     */     
/* 381 */     String numeroContrato = comms.request.getParameter("numeroContrato");
/* 382 */     if (numeroContrato == null) {
/* 383 */       numeroContrato = "";
/*     */     }
/* 385 */     String origen = comms.request.getParameter("origen");
/* 386 */     this.pagHTML.getElementOrigen().setValue("" + origen);
/* 387 */     this.pagHTML.getElementConsecutivoAdicion().setValue("" + consecutivoAdicion);
/* 388 */     this.pagHTML.getElementNumeroEstudio().setValue("" + numeroEstudio);
/* 389 */     this.pagHTML.getElementNumeroContrato().setValue("" + numeroContrato);
/* 390 */     this.pagHTML.getElementConsecutivoContrato().setValue("" + consecutivoContrato);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void cargarImputacion(HttpPresentationComms comms, int consecutivoContrato, String codigoCertificado, String anio, int adicion) throws HttpPresentationException, KeywordValueException {
/* 398 */     ContCdpContratoDAO rs = new ContCdpContratoDAO();
/* 399 */     ContCdpContratoDTO reg = rs.cargarRegistro(consecutivoContrato, codigoCertificado, anio);
/* 400 */     rs.close();
/*     */ 
/*     */     
/* 403 */     ContImputacionPresupuestalDAO rsSis = new ContImputacionPresupuestalDAO();
/* 404 */     Collection arr = rsSis.cargarTodosCdp("", "", Integer.parseInt(anio), consecutivoContrato, adicion);
/* 405 */     rsSis.close();
/*     */     
/* 407 */     Iterator it = arr.iterator();
/* 408 */     HTMLTableElement hte = this.pagHTML.getElementTImputacion();
/* 409 */     boolean fondo = true;
/* 410 */     int cuantos = 0;
/* 411 */     while (it.hasNext()) {
/* 412 */       ContImputacionPresupuestalDTO regSis = (ContImputacionPresupuestalDTO)it.next();
/* 413 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 414 */       fondo = !fondo;
/* 415 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/* 416 */       HTMLElement tdSel = (HTMLElement)this.pagHTML.createElement("td");
/* 417 */       HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
/* 418 */       checkbox.setAttribute("type", "checkbox");
/* 419 */       checkbox.setAttribute("id", "imp" + cuantos);
/* 420 */       checkbox.setAttribute("value", regSis.getCodigoImputacion());
/*     */       
/* 422 */       if (reg.getImputacion().contains(regSis.getCodigoImputacion())) {
/* 423 */         checkbox.setAttribute("checked", "true");
/*     */       }
/*     */       
/* 426 */       tdSel.appendChild(checkbox);
/* 427 */       eltr.appendChild(tdSel);
/*     */       
/* 429 */       HTMLElement tdCodigo = (HTMLElement)this.pagHTML.createElement("td");
/* 430 */       tdCodigo.setAttribute("id", "codImp" + cuantos);
/* 431 */       tdCodigo.appendChild(this.pagHTML.createTextNode(regSis.getCodigoImputacion() + "- " + regSis.getDescripcion()));
/* 432 */       eltr.appendChild(tdCodigo);
/*     */       
/* 434 */       hte.appendChild(eltr);
/* 435 */       cuantos++;
/*     */     } 
/* 437 */     this.pagHTML.getElementCuantosImp().setValue("" + cuantos);
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
/*     */   private Attr newAttr(String tipo, String valor) {
/* 452 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 453 */     atrib.setValue(valor);
/* 454 */     return atrib;
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
/* 467 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 468 */     Element enlace = this.pagHTML.createElement("a");
/* 469 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 470 */     enlace.appendChild(hijo);
/* 471 */     Attr donde = this.pagHTML.createAttribute("href");
/* 472 */     donde.setValue(vinculo);
/* 473 */     enlace.setAttributeNode(donde);
/* 474 */     td.appendChild(enlace);
/* 475 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 476 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 486 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 487 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 488 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 489 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ContCdpContratoAct.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */