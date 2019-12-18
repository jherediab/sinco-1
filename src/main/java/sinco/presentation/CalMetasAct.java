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
/*     */ import sinco.business.CalMetasDTO;
/*     */ import sinco.business.CalObjetivosDTO;
/*     */ import sinco.business.CalProcesosDTO;
/*     */ import sinco.business.CalSubProcesosDTO;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CalMetasDAO;
/*     */ import sinco.data.CalObjetivosDAO;
/*     */ import sinco.data.CalProcesosDAO;
/*     */ import sinco.data.CalSubProcesosDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.CalMetasAct;
/*     */ import sinco.presentation.CalMetasActHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalMetasAct
/*     */   implements HttpPresentation
/*     */ {
/*     */   private CalMetasActHTML pagHTML;
/*  41 */   int codigoObjetivo = 0;
/*     */ 
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  45 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  46 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  49 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  50 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/*     */     try {
/*  53 */       this.codigoObjetivo = Integer.parseInt(comms.request.getParameter("codigoObjetivo"));
/*     */     }
/*  55 */     catch (Exception e) {
/*  56 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoObjetivo"));
/*     */     } 
/*     */     
/*  59 */     String _operacion = comms.request.getParameter("_operacion");
/*  60 */     if (_operacion == null || _operacion.length() == 0) {
/*  61 */       _operacion = "X";
/*     */     }
/*     */     
/*  64 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  65 */       creacion(comms);
/*     */     }
/*  67 */     this.pagHTML = (CalMetasActHTML)comms.xmlcFactory.create(CalMetasActHTML.class);
/*  68 */     this.pagHTML.getElementCodigoObjetivo().setValue("" + this.codigoObjetivo);
/*  69 */     this.pagHTML.getElementCodigoObjetivoC().setValue("" + this.codigoObjetivo);
/*     */     
/*  71 */     titulos(comms);
/*     */     
/*  73 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  74 */       listar(comms, _operacion);
/*     */     
/*     */     }
/*  77 */     else if (_operacion.equals("P")) {
/*  78 */       editar(comms);
/*     */     }
/*  80 */     else if (_operacion.equals("Nuevo")) {
/*  81 */       nuevo(comms);
/*     */     } 
/*  83 */     if (_operacion.equals("P") || _operacion.equals("Nuevo")) {
/*  84 */       HTMLElement sel = this.pagHTML.getElementTrConsulta();
/*  85 */       sel.getParentNode().removeChild(sel);
/*  86 */       sel = this.pagHTML.getElementTrResultados();
/*  87 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/*  89 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  90 */       HTMLElement sel = this.pagHTML.getElementTrCreacionRegistro();
/*  91 */       sel.getParentNode().removeChild(sel);
/*  92 */       Varios oVarios = new Varios();
/*  93 */       boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "oCalMetasAct");
/*  94 */       if (!oPermisoAct) {
/*  95 */         HTMLElement elem = this.pagHTML.getElementBtnCrear();
/*  96 */         elem.getParentNode().removeChild(elem);
/*     */       } 
/*     */     } 
/*  99 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/* 100 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private void titulos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 112 */     CalObjetivosDAO rsCalObjetivos = new CalObjetivosDAO();
/* 113 */     if (!rsCalObjetivos.getEstadoConexion()) {
/* 114 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 116 */     CalObjetivosDTO regObj = rsCalObjetivos.cargarRegistro(this.codigoObjetivo);
/* 117 */     rsCalObjetivos.close();
/*     */ 
/*     */     
/* 120 */     if (regObj.getEstado().equals("I")) {
/* 121 */       HTMLElement sel = this.pagHTML.getElementBtnGrabar();
/* 122 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/*     */     
/* 125 */     this.pagHTML.getElementIdProceso().setValue("" + regObj.getProceso());
/*     */     
/* 127 */     this.pagHTML.setTextMiObjetivo(regObj.getDescripcion());
/*     */     
/* 129 */     CalProcesosDAO rsProceso = new CalProcesosDAO();
/* 130 */     if (!rsProceso.getEstadoConexion()) {
/* 131 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 133 */     CalProcesosDTO regProceso = rsProceso.cargarRegistro(regObj.getProceso());
/* 134 */     rsProceso.close();
/*     */     
/* 136 */     if (regProceso != null) {
/* 137 */       this.pagHTML.setTextMiProceso(regProceso.getDescripcion());
/*     */     }
/*     */     
/* 140 */     CalSubProcesosDAO rsSubProceso = new CalSubProcesosDAO();
/* 141 */     if (!rsSubProceso.getEstadoConexion()) {
/* 142 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 144 */     CalSubProcesosDTO regSubProceso = rsSubProceso.cargarRegistro(regObj.getProceso(), regObj.getSubProceso());
/* 145 */     rsSubProceso.close();
/*     */     
/* 147 */     if (regSubProceso != null) {
/* 148 */       this.pagHTML.setTextMiSubproceso(regSubProceso.getDescripcion());
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
/*     */   private void creacion(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 162 */     String _operacion = comms.request.getParameter("_operacion");
/* 163 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */     
/* 165 */     int codigoMeta = 0;
/*     */     try {
/* 167 */       codigoMeta = Integer.parseInt(comms.request.getParameter("codigoMeta"));
/*     */     }
/* 169 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 172 */     boolean rta = false;
/* 173 */     if (_operacion.equals("E")) {
/* 174 */       CalMetasDAO rs = new CalMetasDAO();
/* 175 */       if (!rs.getEstadoConexion()) {
/* 176 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 178 */       rta = rs.eliminarRegistro(codigoMeta);
/* 179 */       if (!rta) {
/* 180 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCalMetas"));
/*     */       }
/* 182 */       rs.close();
/* 183 */       String sPagina = "CalMetasAct.po?_operacion=L&codigoObjetivo=" + this.codigoObjetivo;
/* 184 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/*     */     
/* 187 */     String descripcion = comms.request.getParameter("descripcion");
/* 188 */     String justificacion = comms.request.getParameter("justificacion");
/* 189 */     double valorMeta = 0.0D;
/*     */     try {
/* 191 */       valorMeta = Double.parseDouble(comms.request.getParameter("valorMeta"));
/*     */     }
/* 193 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 196 */     double valorMinimo = 0.0D;
/*     */     try {
/* 198 */       valorMinimo = Double.parseDouble(comms.request.getParameter("valorMinimo"));
/*     */     }
/* 200 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 203 */     double valorMaximo = 0.0D;
/*     */     try {
/* 205 */       valorMaximo = Double.parseDouble(comms.request.getParameter("valorMaximo"));
/*     */     }
/* 207 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 210 */     String tipoMedicion = comms.request.getParameter("tipoMedicion");
/* 211 */     String estado = comms.request.getParameter("estado");
/* 212 */     String mes01 = comms.request.getParameter("mes01");
/* 213 */     String fuenteDato = comms.request.getParameter("fuenteDato");
/* 214 */     String aplicaEn = comms.request.getParameter("aplicaEn");
/* 215 */     String unidadMedida = comms.request.getParameter("unidadMedida");
/* 216 */     String tipoGrafica = comms.request.getParameter("tipoGrafica");
/*     */     
/* 218 */     if (mes01 == null) {
/* 219 */       mes01 = "N";
/*     */     }
/* 221 */     String mes02 = comms.request.getParameter("mes02");
/* 222 */     if (mes02 == null) {
/* 223 */       mes02 = "N";
/*     */     }
/* 225 */     String mes03 = comms.request.getParameter("mes03");
/* 226 */     if (mes03 == null) {
/* 227 */       mes03 = "N";
/*     */     }
/* 229 */     String mes04 = comms.request.getParameter("mes04");
/* 230 */     if (mes04 == null) {
/* 231 */       mes04 = "N";
/*     */     }
/* 233 */     String mes05 = comms.request.getParameter("mes05");
/* 234 */     if (mes05 == null) {
/* 235 */       mes05 = "N";
/*     */     }
/* 237 */     String mes06 = comms.request.getParameter("mes06");
/* 238 */     if (mes06 == null) {
/* 239 */       mes06 = "N";
/*     */     }
/* 241 */     String mes07 = comms.request.getParameter("mes07");
/* 242 */     if (mes07 == null) {
/* 243 */       mes07 = "N";
/*     */     }
/* 245 */     String mes08 = comms.request.getParameter("mes08");
/* 246 */     if (mes08 == null) {
/* 247 */       mes08 = "N";
/*     */     }
/* 249 */     String mes09 = comms.request.getParameter("mes09");
/* 250 */     if (mes09 == null) {
/* 251 */       mes09 = "N";
/*     */     }
/* 253 */     String mes10 = comms.request.getParameter("mes10");
/* 254 */     if (mes10 == null) {
/* 255 */       mes10 = "N";
/*     */     }
/* 257 */     String mes11 = comms.request.getParameter("mes11");
/* 258 */     if (mes11 == null) {
/* 259 */       mes11 = "N";
/*     */     }
/* 261 */     String mes12 = comms.request.getParameter("mes12");
/* 262 */     if (mes12 == null) {
/* 263 */       mes12 = "N";
/*     */     }
/*     */ 
/*     */     
/* 267 */     CalMetasDAO rs = new CalMetasDAO();
/* 268 */     if (!rs.getEstadoConexion()) {
/* 269 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 271 */     rta = false;
/* 272 */     if (_operacion.equals("C")) {
/* 273 */       rta = rs.crearRegistro(this.codigoObjetivo, descripcion, justificacion, valorMeta, valorMinimo, valorMaximo, tipoMedicion, estado, fuenteDato, aplicaEn, unidadMedida, tipoGrafica, mes01, mes02, mes03, mes04, mes05, mes06, mes07, mes08, mes09, mes10, mes11, mes12, elUsuario);
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
/*     */     }
/*     */     else {
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
/* 301 */       rta = rs.modificarRegistro(codigoMeta, this.codigoObjetivo, descripcion, justificacion, valorMeta, valorMinimo, valorMaximo, tipoMedicion, estado, fuenteDato, aplicaEn, unidadMedida, tipoGrafica, mes01, mes02, mes03, mes04, mes05, mes06, mes07, mes08, mes09, mes10, mes11, mes12, elUsuario);
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
/* 329 */     rs.close();
/* 330 */     if (!rta) {
/* 331 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCalMetas"));
/*     */     }
/* 333 */     String sPagina = "CalMetasAct.po?_operacion=L&codigoObjetivo=" + this.codigoObjetivo;
/* 334 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 345 */     CalMetasDAO rs = new CalMetasDAO();
/* 346 */     if (!rs.getEstadoConexion()) {
/* 347 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 349 */     Collection arr = rs.cargarDeObjetivo(this.codigoObjetivo);
/* 350 */     rs.close();
/* 351 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 352 */     int cuantas = 0;
/* 353 */     Iterator iterator = arr.iterator();
/* 354 */     while (iterator.hasNext()) {
/* 355 */       CalMetasDTO reg = (CalMetasDTO)iterator.next();
/* 356 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 357 */       eltr.appendChild(newtd("" + reg.getCodigoMeta()));
/* 358 */       String url = "CalMetasAct.po?_operacion=P&codigoMeta=" + reg.getCodigoMeta() + "&codigoObjetivo=" + this.codigoObjetivo;
/* 359 */       eltr.appendChild(newtdhref("" + reg.getDescripcion(), url));
/* 360 */       eltr.appendChild(newtd("" + reg.getJustificacion()));
/* 361 */       eltr.appendChild(newtd("" + Utilidades.miles2(reg.getValorMeta())));
/* 362 */       eltr.appendChild(newtd("" + reg.getUnidadMedida()));
/* 363 */       eltr.appendChild(newtd("" + Utilidades.miles2(reg.getValorMinimo())));
/* 364 */       eltr.appendChild(newtd("" + Utilidades.miles2(reg.getValorMaximo())));
/* 365 */       eltr.appendChild(newtd("" + reg.getTipoMedicion()));
/* 366 */       eltr.appendChild(newtd("" + reg.getEstado()));
/* 367 */       eltr.appendChild(newtd("" + reg.getNombreFrecuenciaMedicion()));
/* 368 */       eltr.appendChild(newtd("" + reg.getAplicaEn()));
/* 369 */       hte.appendChild(eltr);
/* 370 */       cuantas++;
/*     */     } 
/* 372 */     arr.clear();
/* 373 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 387 */     int codigoMeta = 0;
/*     */     try {
/* 389 */       codigoMeta = Integer.parseInt(comms.request.getParameter("codigoMeta"));
/*     */     }
/* 391 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 394 */     CalMetasDAO rs = new CalMetasDAO();
/* 395 */     if (!rs.getEstadoConexion()) {
/* 396 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 398 */     CalMetasDTO reg = rs.cargarRegistro(codigoMeta);
/* 399 */     rs.close();
/* 400 */     if (reg != null) {
/* 401 */       this.pagHTML.getElementCodigoMeta().setValue("" + reg.getCodigoMeta());
/* 402 */       this.pagHTML.getElementCodigoObjetivo().setValue("" + reg.getCodigoObjetivo());
/* 403 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 404 */       this.pagHTML.getElementJustificacion().setValue("" + reg.getJustificacion());
/* 405 */       this.pagHTML.getElementValorMeta().setValue("" + Utilidades.formatDouble(reg.getValorMeta()));
/*     */       
/* 407 */       this.pagHTML.getElementValorMinimo().setValue("" + Utilidades.formatDouble(reg.getValorMinimo()));
/* 408 */       this.pagHTML.getElementValorMaximo().setValue("" + Utilidades.formatDouble(reg.getValorMaximo()));
/*     */       
/* 410 */       this.pagHTML.getElementMes01().setChecked(false);
/* 411 */       this.pagHTML.getElementMes02().setChecked(false);
/* 412 */       this.pagHTML.getElementMes03().setChecked(false);
/* 413 */       this.pagHTML.getElementMes04().setChecked(false);
/* 414 */       this.pagHTML.getElementMes05().setChecked(false);
/* 415 */       this.pagHTML.getElementMes06().setChecked(false);
/* 416 */       this.pagHTML.getElementMes07().setChecked(false);
/* 417 */       this.pagHTML.getElementMes08().setChecked(false);
/* 418 */       this.pagHTML.getElementMes09().setChecked(false);
/* 419 */       this.pagHTML.getElementMes10().setChecked(false);
/* 420 */       this.pagHTML.getElementMes11().setChecked(false);
/* 421 */       this.pagHTML.getElementMes12().setChecked(false);
/*     */       
/* 423 */       if (reg.getMes01().equals("S")) {
/* 424 */         this.pagHTML.getElementMes01().setChecked(true);
/*     */       }
/* 426 */       if (reg.getMes02().equals("S")) {
/* 427 */         this.pagHTML.getElementMes02().setChecked(true);
/*     */       }
/* 429 */       if (reg.getMes03().equals("S")) {
/* 430 */         this.pagHTML.getElementMes03().setChecked(true);
/*     */       }
/* 432 */       if (reg.getMes04().equals("S")) {
/* 433 */         this.pagHTML.getElementMes04().setChecked(true);
/*     */       }
/* 435 */       if (reg.getMes05().equals("S")) {
/* 436 */         this.pagHTML.getElementMes05().setChecked(true);
/*     */       }
/* 438 */       if (reg.getMes06().equals("S")) {
/* 439 */         this.pagHTML.getElementMes06().setChecked(true);
/*     */       }
/* 441 */       if (reg.getMes07().equals("S")) {
/* 442 */         this.pagHTML.getElementMes07().setChecked(true);
/*     */       }
/* 444 */       if (reg.getMes08().equals("S")) {
/* 445 */         this.pagHTML.getElementMes08().setChecked(true);
/*     */       }
/* 447 */       if (reg.getMes09().equals("S")) {
/* 448 */         this.pagHTML.getElementMes09().setChecked(true);
/*     */       }
/* 450 */       if (reg.getMes10().equals("S")) {
/* 451 */         this.pagHTML.getElementMes10().setChecked(true);
/*     */       }
/* 453 */       if (reg.getMes11().equals("S")) {
/* 454 */         this.pagHTML.getElementMes11().setChecked(true);
/*     */       }
/* 456 */       if (reg.getMes12().equals("S")) {
/* 457 */         this.pagHTML.getElementMes12().setChecked(true);
/*     */       }
/* 459 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 460 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 461 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 462 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/*     */       
/* 464 */       HTMLSelectElement combo = this.pagHTML.getElementTipoMedicion();
/* 465 */       comboMultivalores(combo, "CAL_TIPO_MEDICION", "" + reg.getTipoMedicion(), false);
/*     */       
/* 467 */       combo = this.pagHTML.getElementUnidadMedida();
/* 468 */       comboMultivalores(combo, "CAL_UNIDAD_MEDIDA_META", "" + reg.getUnidadMedida(), false);
/*     */       
/* 470 */       combo = this.pagHTML.getElementEstado();
/* 471 */       comboMultivalores(combo, "CAL_ESTADO_META", reg.getEstado(), false);
/*     */       
/* 473 */       combo = this.pagHTML.getElementFuenteDato();
/* 474 */       comboMultivalores(combo, "CAL_FUENTE_DATO_METAS", "" + reg.getFuenteDato(), false);
/*     */       
/* 476 */       combo = this.pagHTML.getElementAplicaEn();
/* 477 */       comboMultivalores(combo, "CAL_APLICA_EN_METAS", "" + reg.getAplicaEn(), false);
/*     */       
/* 479 */       combo = this.pagHTML.getElementTipoGrafica();
/* 480 */       comboMultivalores(combo, "CAL_TIPO_GRAFICA", "" + reg.getTipoGrafica(), false);
/*     */       
/* 482 */       this.pagHTML.getElementCodigoMeta().setReadOnly(true);
/* 483 */       this.pagHTML.getElement_operacion().setValue("M");
/*     */     } 
/*     */ 
/*     */     
/* 487 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/* 488 */     Varios oVarios = new Varios();
/* 489 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "oCalMetasAct");
/* 490 */     if (!oPermisoAct) {
/* 491 */       HTMLElement elem = this.pagHTML.getElementBtnGrabar();
/* 492 */       elem.getParentNode().removeChild(elem);
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
/*     */   private void nuevo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 505 */     HTMLSelectElement combo = this.pagHTML.getElementTipoMedicion();
/* 506 */     comboMultivalores(combo, "CAL_TIPO_MEDICION", "", true);
/* 507 */     combo = this.pagHTML.getElementEstado();
/* 508 */     comboMultivalores(combo, "CAL_ESTADO_META", "A", false);
/*     */     
/* 510 */     combo = this.pagHTML.getElementFuenteDato();
/* 511 */     comboMultivalores(combo, "CAL_FUENTE_DATO_METAS", "MANUAL", false);
/*     */     
/* 513 */     combo = this.pagHTML.getElementAplicaEn();
/* 514 */     comboMultivalores(combo, "CAL_APLICA_EN_METAS", "PRO", false);
/*     */     
/* 516 */     combo = this.pagHTML.getElementUnidadMedida();
/* 517 */     comboMultivalores(combo, "CAL_UNIDAD_MEDIDA_META", "PO", false);
/*     */     
/* 519 */     combo = this.pagHTML.getElementTipoGrafica();
/* 520 */     comboMultivalores(combo, "CAL_TIPO_GRAFICA", "B", false);
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
/* 533 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 534 */     atrib.setValue(valor);
/* 535 */     return atrib;
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
/* 548 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 549 */     Element enlace = this.pagHTML.createElement("a");
/* 550 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 551 */     enlace.appendChild(hijo);
/* 552 */     Attr donde = this.pagHTML.createAttribute("href");
/* 553 */     donde.setValue(vinculo);
/* 554 */     enlace.setAttributeNode(donde);
/* 555 */     td.appendChild(enlace);
/* 556 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 557 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 567 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 568 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 569 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 570 */     return td;
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
/* 585 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 586 */     Collection arr = rs.cargarTabla(tabla);
/* 587 */     rs.close();
/* 588 */     if (dejarBlanco) {
/* 589 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 590 */       op.setValue("");
/* 591 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 592 */       combo.appendChild(op);
/*     */     } 
/* 594 */     Iterator iterator = arr.iterator();
/* 595 */     while (iterator.hasNext()) {
/* 596 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 597 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 598 */       op.setValue("" + reg.getCodigo());
/* 599 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 600 */       if (defecto.equals(reg.getCodigo())) {
/* 601 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 602 */         escogida.setValue("on");
/* 603 */         op.setAttributeNode(escogida);
/*     */       } 
/* 605 */       combo.appendChild(op);
/*     */     } 
/* 607 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalMetasAct.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */