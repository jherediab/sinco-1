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
/*     */ import sinco.business.ContPolizaDTO;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContCdpDAO;
/*     */ import sinco.data.ContContratoDAO;
/*     */ import sinco.data.ContPolizaDAO;
/*     */ import sinco.data.ContRpDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.ContPolizaAct;
/*     */ import sinco.presentation.ContPolizaActHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContPolizaAct
/*     */   implements HttpPresentation
/*     */ {
/*     */   private ContPolizaActHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  40 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  41 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */ 
/*     */     
/*  45 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*     */ 
/*     */     
/*  48 */     String _operacion = comms.request.getParameter("_operacion");
/*  49 */     if (_operacion == null || _operacion.length() == 0) {
/*  50 */       _operacion = "X";
/*     */     }
/*     */     
/*  53 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  54 */       creacion(comms);
/*     */     }
/*  56 */     this.pagHTML = (ContPolizaActHTML)comms.xmlcFactory.create(ContPolizaActHTML.class);
/*  57 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  58 */       listar(comms, _operacion);
/*     */     
/*     */     }
/*  61 */     else if (_operacion.equals("P")) {
/*  62 */       editar(comms);
/*  63 */       listar(comms, _operacion);
/*     */     }
/*  65 */     else if (_operacion.equals("Nuevo")) {
/*  66 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/*  67 */       sel.getParentNode().removeChild(sel);
/*  68 */       sel = this.pagHTML.getElementBtnAgregarAmparo();
/*  69 */       sel.getParentNode().removeChild(sel);
/*  70 */       Varios oVarios = new Varios();
/*  71 */       int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */       
/*  73 */       boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "oOficinaJuridica");
/*  74 */       if (!oPermisoAct) {
/*  75 */         HTMLElement elem = this.pagHTML.getElementBtnGrabar();
/*  76 */         elem.getParentNode().removeChild(elem);
/*     */       } 
/*  78 */       nuevo(comms);
/*  79 */       listar(comms, _operacion);
/*     */     }
/*  81 */     else if (_operacion.equals("Amparo")) {
/*  82 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/*  83 */       sel.getParentNode().removeChild(sel);
/*  84 */       amparo(comms);
/*  85 */       listar(comms, _operacion);
/*     */     } 
/*  87 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  88 */       HTMLElement sel = this.pagHTML.getElementTrCreacionRegistro();
/*  89 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/*  91 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  92 */     comms.response.writeDOM(this.pagHTML);
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
/* 104 */     String _operacion = comms.request.getParameter("_operacion");
/* 105 */     String elUsuario = "" + comms.session.getUser().getName();
/* 106 */     String numeroEstudio = comms.request.getParameter("numeroEstudio");
/* 107 */     if (numeroEstudio == null) {
/* 108 */       numeroEstudio = "";
/*     */     }
/* 110 */     int consecutivoContrato = 0;
/*     */     try {
/* 112 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/*     */     }
/* 114 */     catch (Exception e) {}
/*     */     
/* 116 */     int consecutivoAdicion = 0;
/*     */     try {
/* 118 */       consecutivoAdicion = Integer.parseInt(comms.request.getParameter("consecutivoAdicion"));
/*     */     }
/* 120 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 123 */     String fechaInicioCont = comms.request.getParameter("fechaInicio");
/* 124 */     if (fechaInicioCont == null) {
/* 125 */       fechaInicioCont = "";
/*     */     }
/* 127 */     String fechaFinalCont = comms.request.getParameter("fechaFinal");
/* 128 */     if (fechaFinalCont == null) {
/* 129 */       fechaFinalCont = "";
/*     */     }
/* 131 */     String fechaRp = comms.request.getParameter("fechaRp");
/* 132 */     if (fechaRp == null) {
/* 133 */       fechaRp = "";
/*     */     }
/* 135 */     String numeroContrato = comms.request.getParameter("numeroContrato");
/* 136 */     if (numeroContrato == null) {
/* 137 */       numeroContrato = "";
/*     */     }
/* 139 */     String valorContrato = comms.request.getParameter("valorContrato");
/* 140 */     String numeroPoliza = comms.request.getParameter("numeroPoliza");
/* 141 */     String tipoPoliza = comms.request.getParameter("tipoPoliza");
/* 142 */     String origen = comms.request.getParameter("origen");
/* 143 */     boolean rta = false;
/* 144 */     if (_operacion.equals("E")) {
/* 145 */       ContPolizaDAO rs = new ContPolizaDAO();
/* 146 */       if (!rs.getEstadoConexion()) {
/* 147 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 149 */       rta = rs.eliminarRegistro(consecutivoContrato, numeroPoliza, tipoPoliza);
/*     */ 
/*     */       
/* 152 */       rs.close();
/* 153 */       if (!rta) {
/* 154 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorContPoliza"));
/*     */       }
/* 156 */       String sPagina = "ContPolizaAct.po?_operacion=Nuevo&numeroPoliza=" + numeroPoliza + "&tipoPoliza=" + tipoPoliza + "&numeroEstudio=" + numeroEstudio + "&fechaRp=" + fechaRp + "&valorContrato=" + valorContrato + "&numeroContrato=" + numeroContrato + "&consecutivoContrato=" + consecutivoContrato + "&origen=" + origen + "&fechaInicio=" + fechaInicioCont + "&fechaFinal=" + fechaFinalCont + "&consecutivoAdicion=" + consecutivoAdicion;
/*     */ 
/*     */       
/* 159 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/*     */     
/* 162 */     String entidadExpide = comms.request.getParameter("entidadExpide");
/* 163 */     String fechaExpedicion = comms.request.getParameter("fechaExpedicion");
/* 164 */     String fechaAprobacion = comms.request.getParameter("fechaAprobacion");
/* 165 */     String fechaInicioPoliza = comms.request.getParameter("fechaInicioPoliza");
/* 166 */     String fechaFinalPoliza = comms.request.getParameter("fechaFinalPoliza");
/* 167 */     double valor = 0.0D;
/*     */     try {
/* 169 */       valor = Double.parseDouble(comms.request.getParameter("valor"));
/*     */     }
/* 171 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 174 */     ContPolizaDAO rs = new ContPolizaDAO();
/* 175 */     if (!rs.getEstadoConexion()) {
/* 176 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 178 */     rta = false;
/* 179 */     if (_operacion.equals("C")) {
/* 180 */       rta = rs.crearRegistro(consecutivoContrato, numeroPoliza, tipoPoliza, entidadExpide, fechaExpedicion, fechaAprobacion, fechaInicioPoliza, fechaFinalPoliza, valor, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 193 */       rta = rs.modificarRegistro(consecutivoContrato, numeroPoliza, tipoPoliza, entidadExpide, fechaExpedicion, fechaAprobacion, fechaInicioPoliza, fechaFinalPoliza, valor, elUsuario);
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
/* 205 */     rs.close();
/*     */     
/* 207 */     ContContratoDAO rsC = new ContContratoDAO();
/* 208 */     rsC.generarFechas(-1, consecutivoContrato);
/* 209 */     rsC.close();
/*     */     
/* 211 */     if (!rta) {
/* 212 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorContPoliza"));
/*     */     }
/* 214 */     String sPagina = "ContPolizaAct.po?_operacion=P&numeroPoliza=" + numeroPoliza + "&tipoPoliza=" + tipoPoliza + "&numeroEstudio=" + numeroEstudio + "&fechaRp=" + fechaRp + "&valorContrato=" + valorContrato + "&numeroContrato=" + numeroContrato + "&consecutivoContrato=" + consecutivoContrato + "&origen=" + origen + "&consecutivoAdicion=" + consecutivoAdicion;
/*     */ 
/*     */     
/* 217 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 228 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/* 231 */     String numeroEstudio = comms.request.getParameter("numeroEstudio");
/* 232 */     if (numeroEstudio == null) {
/* 233 */       numeroEstudio = "";
/*     */     }
/* 235 */     int consecutivoContrato = 0;
/*     */     try {
/* 237 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/*     */     }
/* 239 */     catch (Exception e) {}
/*     */     
/* 241 */     String fechaRp = comms.request.getParameter("fechaRp");
/* 242 */     if (fechaRp == null) {
/* 243 */       ContRpDAO rs = new ContRpDAO();
/* 244 */       if (!rs.getEstadoConexion()) {
/* 245 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 247 */       rs.close();
/*     */     } 
/* 249 */     String numeroContrato = comms.request.getParameter("numeroContrato");
/* 250 */     if (numeroContrato == null) {
/* 251 */       numeroContrato = "";
/*     */     }
/* 253 */     String valorContrato = comms.request.getParameter("valorContrato");
/* 254 */     String origen = comms.request.getParameter("origen");
/*     */     
/* 256 */     int consecutivoAdicion = 0;
/*     */     try {
/* 258 */       consecutivoAdicion = Integer.parseInt(comms.request.getParameter("consecutivoAdicion"));
/*     */     }
/* 260 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 263 */     ContPolizaDAO rs = new ContPolizaDAO();
/* 264 */     if (!rs.getEstadoConexion()) {
/* 265 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 267 */     Collection<ContPolizaDTO> arr = rs.cargarTodos(consecutivoContrato);
/*     */     
/* 269 */     rs.close();
/* 270 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 271 */     int cuantas = 0;
/* 272 */     Iterator<ContPolizaDTO> iterator = arr.iterator();
/* 273 */     while (iterator.hasNext()) {
/* 274 */       ContPolizaDTO reg = (ContPolizaDTO)iterator.next();
/* 275 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 276 */       eltr.appendChild(newtd("" + reg.getConsecutivoContrato()));
/* 277 */       String url = "ContPolizaAct.po?_operacion=P&numeroPoliza=" + reg.getNumeroPoliza() + "&tipoPoliza=" + reg.getTipoPoliza() + "&numeroEstudio=" + numeroEstudio + "&fechaRp=" + fechaRp + "&valorContrato=" + valorContrato + "&numeroContrato=" + numeroContrato + "&consecutivoContrato=" + consecutivoContrato + "&origen=" + origen + "&consecutivoAdicion=" + consecutivoAdicion;
/*     */ 
/*     */       
/* 280 */       eltr.appendChild(newtdhref("" + reg.getNumeroPoliza(), url));
/* 281 */       eltr.appendChild(newtd("" + reg.getDescripcionTipoPoliza()));
/* 282 */       eltr.appendChild(newtd("" + reg.getEntidadExpide()));
/* 283 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaExpedicion())));
/* 284 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaAprobacion())));
/* 285 */       eltr.appendChild(newtd("" + Utilidades.miles(reg.getValor(), 2)));
/* 286 */       hte.appendChild(eltr);
/* 287 */       cuantas++;
/*     */     } 
/* 289 */     arr.clear();
/* 290 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/*     */     
/* 292 */     ContCdpDAO rsCdp = new ContCdpDAO();
/* 293 */     if (!rsCdp.getEstadoConexion()) {
/* 294 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 296 */     String arrCdp = rsCdp.cargarTodosParaPoliza(consecutivoContrato);
/*     */     
/* 298 */     rsCdp.close();
/* 299 */     this.pagHTML.getElementNumeroCdp().setValue("" + arrCdp);
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
/* 314 */     String numeroEstudio = comms.request.getParameter("numeroEstudio");
/* 315 */     if (numeroEstudio == null) {
/* 316 */       numeroEstudio = "";
/*     */     }
/*     */     
/* 319 */     String fechaRp = comms.request.getParameter("fechaRp");
/* 320 */     if (fechaRp == null) {
/* 321 */       fechaRp = "";
/*     */     }
/* 323 */     String numeroContrato = comms.request.getParameter("numeroContrato");
/* 324 */     if (numeroContrato == null) {
/* 325 */       numeroContrato = "";
/*     */     }
/* 327 */     int consecutivoAdicion = 0;
/*     */     try {
/* 329 */       consecutivoAdicion = Integer.parseInt(comms.request.getParameter("consecutivoAdicion"));
/*     */     }
/* 331 */     catch (Exception e) {}
/*     */     
/* 333 */     String valorContrato = comms.request.getParameter("valorContrato");
/* 334 */     String numeroPoliza = comms.request.getParameter("numeroPoliza");
/* 335 */     String tipoPoliza = comms.request.getParameter("tipoPoliza");
/* 336 */     String origen = comms.request.getParameter("origen");
/* 337 */     ContPolizaDAO rs = new ContPolizaDAO();
/* 338 */     if (!rs.getEstadoConexion()) {
/* 339 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 341 */     int consecutivoContrato = 0;
/*     */     try {
/* 343 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/* 344 */     } catch (Exception e) {}
/* 345 */     this.pagHTML.getElementConsecutivoAdicion().setValue("" + consecutivoAdicion);
/* 346 */     this.pagHTML.getElementOrigen().setValue("" + origen);
/* 347 */     this.pagHTML.getElementFechaRp().setValue("" + fechaRp);
/* 348 */     this.pagHTML.getElementValorContrato().setValue("" + valorContrato);
/* 349 */     this.pagHTML.getElementConsecutivoContrato().setValue("" + consecutivoContrato);
/* 350 */     this.pagHTML.getElementNumeroEstudio().setValue("" + numeroEstudio);
/*     */     
/* 352 */     ContPolizaDTO reg = rs.cargarRegistro(consecutivoContrato, numeroPoliza, tipoPoliza);
/*     */ 
/*     */     
/* 355 */     rs.close();
/* 356 */     Varios oVarios = new Varios();
/* 357 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/* 358 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "oOficinaJuridica");
/* 359 */     if (!oPermisoAct) {
/* 360 */       HTMLElement elem = this.pagHTML.getElementBtnGrabar();
/* 361 */       elem.getParentNode().removeChild(elem);
/*     */       
/* 363 */       elem = this.pagHTML.getElementBtnEliminar();
/* 364 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 366 */     if (reg != null) {
/* 367 */       this.pagHTML.getElementConsecutivoContrato().setValue("" + consecutivoContrato);
/* 368 */       this.pagHTML.getElementNumeroContrato().setValue("" + reg.getNumeroContrato());
/* 369 */       this.pagHTML.getElementNumeroPoliza().setValue("" + reg.getNumeroPoliza());
/* 370 */       this.pagHTML.getElementEntidadExpide().setValue("" + reg.getEntidadExpide());
/* 371 */       this.pagHTML.getElementFechaExpedicion().setValue("" + Utilidades.darFormatoFecha(reg.getFechaExpedicion()));
/* 372 */       this.pagHTML.getElementFechaAprobacion().setValue("" + Utilidades.darFormatoFecha(reg.getFechaAprobacion()));
/* 373 */       this.pagHTML.getElementFechaInicioPoliza().setValue("" + Utilidades.darFormatoFecha(reg.getFechaInicio()));
/* 374 */       this.pagHTML.getElementFechaFinalPoliza().setValue("" + Utilidades.darFormatoFecha(reg.getFechaFinal()));
/* 375 */       this.pagHTML.getElementFechaInicio().setValue("" + Utilidades.darFormatoFecha(reg.getFechaInicioCont()));
/* 376 */       this.pagHTML.getElementFechaFinal().setValue("" + Utilidades.darFormatoFecha(reg.getFechaFinalCont()));
/* 377 */       this.pagHTML.getElementValor().setValue("" + Utilidades.formatDouble(reg.getValor()));
/* 378 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 379 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 380 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 381 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 382 */       HTMLSelectElement combo = this.pagHTML.getElementTipoPoliza();
/* 383 */       comboMultivaloresUnico(combo, "tipo_poliza", "" + reg.getTipoPoliza());
/*     */       
/* 385 */       this.pagHTML.getElementNumeroPoliza().setReadOnly(true);
/* 386 */       this.pagHTML.getElement_operacion().setValue("M");
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
/*     */   private void nuevo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 400 */     int consecutivoAdicion = 0;
/*     */     try {
/* 402 */       consecutivoAdicion = Integer.parseInt(comms.request.getParameter("consecutivoAdicion"));
/*     */     }
/* 404 */     catch (Exception e) {}
/*     */     
/* 406 */     String numeroEstudio = comms.request.getParameter("numeroEstudio");
/* 407 */     if (numeroEstudio == null) {
/* 408 */       numeroEstudio = "";
/*     */     }
/* 410 */     int consecutivoContrato = 0;
/*     */     try {
/* 412 */       consecutivoContrato = Integer.parseInt(comms.request.getParameter("consecutivoContrato"));
/* 413 */     } catch (Exception e) {}
/* 414 */     String fechaInicioCont = comms.request.getParameter("fechaInicio");
/* 415 */     if (fechaInicioCont == null) {
/* 416 */       fechaInicioCont = "";
/*     */     }
/* 418 */     String fechaFinalCont = comms.request.getParameter("fechaFinal");
/* 419 */     if (fechaFinalCont == null) {
/* 420 */       fechaFinalCont = "";
/*     */     }
/* 422 */     String numeroContrato = comms.request.getParameter("numeroContrato");
/* 423 */     if (numeroContrato == null) {
/* 424 */       numeroContrato = "";
/*     */     }
/* 426 */     String valorContrato = comms.request.getParameter("valorContrato");
/* 427 */     String origen = comms.request.getParameter("origen");
/* 428 */     String fechaRp = comms.request.getParameter("fechaRp");
/* 429 */     if (fechaRp == null) {
/* 430 */       ContRpDAO rs = new ContRpDAO();
/* 431 */       if (!rs.getEstadoConexion()) {
/* 432 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 434 */       rs.close();
/*     */     } 
/* 436 */     this.pagHTML.getElementOrigen().setValue("" + origen);
/* 437 */     this.pagHTML.getElementConsecutivoAdicion().setValue("" + consecutivoAdicion);
/* 438 */     this.pagHTML.getElementNumeroEstudio().setValue("" + numeroEstudio);
/* 439 */     this.pagHTML.getElementFechaInicio().setValue("" + fechaInicioCont);
/* 440 */     this.pagHTML.getElementFechaFinal().setValue("" + fechaFinalCont);
/* 441 */     this.pagHTML.getElementFechaRp().setValue("" + fechaRp);
/* 442 */     this.pagHTML.getElementValorContrato().setValue("" + valorContrato);
/* 443 */     this.pagHTML.getElementNumeroContrato().setValue("" + numeroContrato);
/* 444 */     this.pagHTML.getElementConsecutivoContrato().setValue("" + consecutivoContrato);
/* 445 */     HTMLSelectElement combo = this.pagHTML.getElementTipoPoliza();
/* 446 */     comboMultivalores(combo, "tipo_poliza", "", true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void amparo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 457 */     String numeroEstudio = comms.request.getParameter("numeroEstudio");
/* 458 */     if (numeroEstudio == null) {
/* 459 */       numeroEstudio = "";
/*     */     }
/* 461 */     String consecutivoContrato = comms.request.getParameter("consecutivoContrato");
/* 462 */     if (consecutivoContrato == null) {
/* 463 */       consecutivoContrato = "";
/*     */     }
/* 465 */     String numeroPoliza = comms.request.getParameter("numeroPoliza");
/* 466 */     if (numeroPoliza == null) {
/* 467 */       numeroPoliza = "";
/*     */     }
/* 469 */     String entidadExpide = comms.request.getParameter("entidadExpide");
/* 470 */     if (entidadExpide == null) {
/* 471 */       entidadExpide = "";
/*     */     }
/* 473 */     String fechaExpedicion = comms.request.getParameter("fechaExpedicion");
/* 474 */     if (fechaExpedicion == null) {
/* 475 */       fechaExpedicion = "";
/*     */     }
/* 477 */     String fechaAprobacion = comms.request.getParameter("fechaAprobacion");
/* 478 */     if (fechaAprobacion == null) {
/* 479 */       fechaAprobacion = "";
/*     */     }
/* 481 */     String fechaInicioCont = comms.request.getParameter("fechaInicio");
/* 482 */     if (fechaInicioCont == null) {
/* 483 */       fechaInicioCont = "";
/*     */     }
/* 485 */     String fechaFinalCont = comms.request.getParameter("fechaFinal");
/* 486 */     if (fechaFinalCont == null) {
/* 487 */       fechaFinalCont = "";
/*     */     }
/* 489 */     String fechaRp = comms.request.getParameter("fechaRp");
/* 490 */     if (fechaRp == null) {
/* 491 */       fechaRp = "";
/*     */     }
/* 493 */     String numeroContrato = comms.request.getParameter("numeroContrato");
/* 494 */     if (numeroContrato == null) {
/* 495 */       numeroContrato = "";
/*     */     }
/* 497 */     String origen = comms.request.getParameter("origen");
/* 498 */     String valorContrato = comms.request.getParameter("valorContrato");
/* 499 */     this.pagHTML.getElementNumeroPoliza().setValue("" + numeroPoliza);
/* 500 */     this.pagHTML.getElementEntidadExpide().setValue("" + entidadExpide);
/* 501 */     this.pagHTML.getElementFechaExpedicion().setValue("" + fechaExpedicion);
/* 502 */     this.pagHTML.getElementFechaAprobacion().setValue("" + fechaAprobacion);
/* 503 */     this.pagHTML.getElementFechaInicio().setValue("" + fechaInicioCont);
/* 504 */     this.pagHTML.getElementFechaFinal().setValue("" + fechaFinalCont);
/* 505 */     this.pagHTML.getElementFechaRp().setValue("" + fechaRp);
/* 506 */     this.pagHTML.getElementValorContrato().setValue("" + valorContrato);
/* 507 */     this.pagHTML.getElementConsecutivoContrato().setValue("" + consecutivoContrato);
/* 508 */     this.pagHTML.getElementNumeroEstudio().setValue("" + numeroEstudio);
/* 509 */     this.pagHTML.getElementNumeroContrato().setValue("" + numeroContrato);
/* 510 */     this.pagHTML.getElementOrigen().setValue("" + origen);
/* 511 */     HTMLSelectElement combo = this.pagHTML.getElementTipoPoliza();
/* 512 */     comboMultivalores(combo, "tipo_poliza", "", true);
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
/* 524 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 525 */     atrib.setValue(valor);
/* 526 */     return atrib;
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
/* 539 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 540 */     Element enlace = this.pagHTML.createElement("a");
/* 541 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 542 */     enlace.appendChild(hijo);
/* 543 */     Attr donde = this.pagHTML.createAttribute("href");
/* 544 */     donde.setValue(vinculo);
/* 545 */     enlace.setAttributeNode(donde);
/* 546 */     td.appendChild(enlace);
/* 547 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 548 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 558 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 559 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 560 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 561 */     return td;
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
/* 576 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 577 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 578 */     rs.close();
/* 579 */     if (dejarBlanco) {
/* 580 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 581 */       op.setValue("");
/* 582 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 583 */       combo.appendChild(op);
/*     */     } 
/* 585 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 586 */     while (iterator.hasNext()) {
/* 587 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 588 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 589 */       op.setValue("" + reg.getCodigo());
/* 590 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 591 */       if (defecto.equals(reg.getCodigo())) {
/* 592 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 593 */         escogida.setValue("on");
/* 594 */         op.setAttributeNode(escogida);
/*     */       } 
/* 596 */       combo.appendChild(op);
/*     */     } 
/* 598 */     arr.clear();
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
/*     */   private void comboMultivaloresUnico(HTMLSelectElement combo, String tabla, String defecto) {
/* 612 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 613 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla, defecto);
/* 614 */     rs.close();
/*     */     
/* 616 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 617 */     while (iterator.hasNext()) {
/* 618 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 619 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 620 */       op.setValue("" + reg.getCodigo());
/* 621 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 622 */       if (defecto.equals(reg.getCodigo())) {
/* 623 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 624 */         escogida.setValue("on");
/* 625 */         op.setAttributeNode(escogida);
/*     */       } 
/* 627 */       combo.appendChild(op);
/*     */     } 
/* 629 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ContPolizaAct.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */