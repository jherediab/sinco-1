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
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.PoaLogrosDAO;
/*     */ import sinco.data.PoaMaestroActividadesDAO;
/*     */ import sinco.data.PoaMaestroDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.PoaLogros;
/*     */ import sinco.presentation.PoaLogrosHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PoaLogros
/*     */   implements HttpPresentation
/*     */ {
/*     */   private PoaLogrosHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  45 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  46 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  49 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  50 */     String _operacion = comms.request.getParameter("_operacion");
/*  51 */     if (_operacion == null || _operacion.length() == 0) {
/*  52 */       _operacion = "X";
/*     */     }
/*     */     
/*  55 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  56 */       creacion(comms);
/*     */     }
/*     */     
/*  59 */     this.pagHTML = (PoaLogrosHTML)comms.xmlcFactory.create(PoaLogrosHTML.class);
/*  60 */     permisos(comms);
/*     */ 
/*     */ 
/*     */     
/*  64 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  65 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  68 */     if (_operacion.equals("P")) {
/*  69 */       editar(comms);
/*     */     }
/*  71 */     else if (_operacion.equals("Nuevo")) {
/*  72 */       nuevo(comms);
/*     */     } 
/*     */     
/*  75 */     if (_operacion.equals("V")) {
/*  76 */       verRegistro(comms);
/*     */     }
/*  78 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  79 */     comms.response.writeDOM(this.pagHTML);
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
/*  91 */     String _operacion = comms.request.getParameter("_operacion");
/*  92 */     String elUsuario = "" + comms.session.getUser().getName();
/*  93 */     int idLogro = 0;
/*     */     try {
/*  95 */       idLogro = Integer.parseInt(comms.request.getParameter("idLogro"));
/*     */     }
/*  97 */     catch (Exception e) {
/*  98 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idLogro"));
/*     */     } 
/*     */     
/* 101 */     RespuestaBD rta = new RespuestaBD();
/* 102 */     if (_operacion.equals("E")) {
/* 103 */       PoaLogrosDAO rs = new PoaLogrosDAO();
/* 104 */       if (!rs.getEstadoConexion()) {
/* 105 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 107 */       rta = rs.eliminarRegistro(idLogro);
/* 108 */       if (!rta.isRta()) {
/* 109 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaLogros&p1=" + rta.getMensaje()));
/*     */       }
/* 111 */       rs.close();
/* 112 */       String sPagina = "PoaLogros.po?_operacion=X";
/* 113 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 115 */     int codigoPoaActividad = 0;
/*     */     try {
/* 117 */       codigoPoaActividad = Integer.parseInt(comms.request.getParameter("codigoPoa"));
/*     */     }
/* 119 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 122 */     int codigoPoa = 0;
/*     */     try {
/* 124 */       codigoPoa = Integer.parseInt(comms.request.getParameter("idPoa"));
/* 125 */     } catch (Exception e) {}
/*     */ 
/*     */     
/* 128 */     int proceso = 0;
/*     */     try {
/* 130 */       proceso = Integer.parseInt(comms.request.getParameter("proceso"));
/* 131 */     } catch (Exception e) {}
/*     */ 
/*     */     
/* 134 */     String mes = comms.request.getParameter("mes");
/* 135 */     int ejecucion = 0;
/*     */     try {
/* 137 */       ejecucion = Integer.parseInt(comms.request.getParameter("ejecucion"));
/*     */     }
/* 139 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 142 */     PoaMaestroActividadesDAO rsPM = new PoaMaestroActividadesDAO();
/* 143 */     PoaMaestroActividadesDTO act = rsPM.cargarRegistro(codigoPoaActividad);
/* 144 */     rsPM.close();
/* 145 */     PoaMaestroDAO rsP = new PoaMaestroDAO();
/* 146 */     PoaMaestroDTO poa = rsP.cargarRegistro(act.getCodigoPoa());
/* 147 */     rsP.close();
/* 148 */     Utilidades.grabarLog("" + poa.getCodigoPoa());
/*     */ 
/*     */     
/* 151 */     String avances = comms.request.getParameter("avances");
/* 152 */     String logrosYResultados = comms.request.getParameter("logrosYResultados");
/* 153 */     String retrasosDificultades = comms.request.getParameter("retrasosDificultades");
/* 154 */     String estado = comms.request.getParameter("estado");
/* 155 */     PoaLogrosDAO rs = new PoaLogrosDAO();
/* 156 */     if (!rs.getEstadoConexion()) {
/* 157 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 159 */     if (_operacion.equals("C")) {
/* 160 */       rta = rs.crearRegistro(idLogro, codigoPoaActividad, mes, ejecucion, avances, logrosYResultados, retrasosDificultades, estado, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 170 */       idLogro = rta.getSecuencia();
/*     */     } else {
/*     */       
/* 173 */       rta = rs.modificarRegistro(idLogro, codigoPoaActividad, mes, ejecucion, avances, logrosYResultados, retrasosDificultades, estado, elUsuario);
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
/* 184 */     rs.close();
/*     */     
/* 186 */     if (rta.isRta()) {
/* 187 */       String url = "PoaLogros.po?_operacion=L&fciclo=" + poa.getCiclo() + "&farea=" + poa.getArea() + "&fperiodo=" + getMesNumero(mes) + "&proceso=" + proceso;
/* 188 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(url));
/*     */     } 
/* 190 */     if (!rta.isRta()) {
/* 191 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaLogros&p1=" + rta.getMensaje()));
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
/*     */   private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 208 */     int idLogro = 0;
/*     */     try {
/* 210 */       idLogro = Integer.parseInt(comms.request.getParameter("idLogro"));
/*     */     }
/* 212 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 215 */     PoaLogrosDAO rs = new PoaLogrosDAO();
/* 216 */     if (!rs.getEstadoConexion()) {
/* 217 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 219 */     PoaLogrosDTO reg = rs.cargarRegistro(idLogro);
/* 220 */     rs.close();
/* 221 */     if (reg != null) {
/* 222 */       PoaMaestroActividadesDAO rs1 = new PoaMaestroActividadesDAO();
/* 223 */       PoaMaestroActividadesDTO poa = rs1.cargarRegistro(reg.getCodigoPoa());
/* 224 */       rs1.close();
/* 225 */       int periodo = Integer.parseInt(getMesNumero(reg.getMes()).replace("mes", ""));
/* 226 */       this.pagHTML.setTextValorEsperadoPoa("" + getValorEsperado(periodo, poa));
/* 227 */       this.pagHTML.getElementIdLogro().setValue("" + reg.getIdLogro());
/* 228 */       this.pagHTML.getElementCodigoPoa().setValue("" + reg.getCodigoPoa());
/* 229 */       this.pagHTML.getElementMes().setValue("" + reg.getMes());
/* 230 */       this.pagHTML.getElementEjecucion().setValue("" + reg.getEjecucion());
/* 231 */       this.pagHTML.getElementAvances().setTextContent("" + reg.getAvances());
/* 232 */       this.pagHTML.getElementLogrosYResultados().setTextContent("" + reg.getLogrosYResultados());
/* 233 */       this.pagHTML.getElementRetrasosDificultades().setTextContent("" + reg.getRetrasosDificultades());
/* 234 */       comboMultivalores(this.pagHTML.getElementEstado(), "ESTADO_REGISTRO", "A", false);
/* 235 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 236 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 237 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 238 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/*     */       
/* 240 */       this.pagHTML.getElementIdLogro().setReadOnly(true);
/*     */     } 
/* 242 */     this.pagHTML.getElement_operacion().setValue("M");
/* 243 */     activarVista("nuevo");
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
/* 255 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 257 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 258 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 260 */     catch (Exception e) {}
/*     */     
/* 262 */     activarVista("nuevo");
/* 263 */     this.pagHTML.getElementIdLogro().setReadOnly(true);
/* 264 */     this.pagHTML.getElementIdLogro().setValue("0");
/* 265 */     String mes = comms.request.getParameter("periodo");
/* 266 */     int idPoa = Integer.parseInt(comms.request.getParameter("idPoa"));
/* 267 */     int ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/* 268 */     int valor = Integer.parseInt(comms.request.getParameter("valor"));
/* 269 */     int proceso = Integer.parseInt(comms.request.getParameter("proceso"));
/* 270 */     this.pagHTML.getElementCodigoPoa().setValue("" + idPoa);
/* 271 */     String periodo = mes.replace("mes", "");
/* 272 */     this.pagHTML.getElementMes().setValue("" + getMes(Integer.parseInt(periodo)));
/*     */     
/* 274 */     comboMultivalores(this.pagHTML.getElementEstado(), "ESTADO_REGISTRO", "A", false);
/* 275 */     this.pagHTML.setTextValorEsperadoPoa("" + valor);
/* 276 */     this.pagHTML.getElementCodigoPoa().setReadOnly(true);
/* 277 */     this.pagHTML.getElementMes().setReadOnly(true);
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
/* 288 */     activarVista("consulta");
/* 289 */     int area = 0;
/*     */     try {
/* 291 */       area = Integer.parseInt(comms.request.getParameter("farea"));
/*     */     }
/* 293 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 296 */     int ciclo = 0;
/*     */     try {
/* 298 */       ciclo = Integer.parseInt(comms.request.getParameter("fciclo"));
/*     */     }
/* 300 */     catch (Exception e) {}
/*     */     
/* 302 */     int proceso = 0;
/*     */     try {
/* 304 */       proceso = Integer.parseInt(comms.request.getParameter("fproceso"));
/*     */     }
/* 306 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 309 */     String mes = comms.request.getParameter("fperiodo");
/*     */     
/* 311 */     HTMLSelectElement combo = this.pagHTML.getElementFarea();
/* 312 */     llenarCombo(combo, "UNIDADES_DEPENDENCIA", "CODIGO", "DESCRIPCION", "1=1", "" + area, false);
/*     */     
/* 314 */     combo = this.pagHTML.getElementFciclo();
/* 315 */     llenarCombo(combo, "POA_CICLOS", "CODIGO_CICLO", "DESCRIPCION", "1=1", "" + ciclo, false);
/*     */     
/* 317 */     combo = this.pagHTML.getElementFperiodo();
/* 318 */     llenarComboMeses(combo, "" + mes, false);
/*     */     
/* 320 */     combo = this.pagHTML.getElementFproceso();
/* 321 */     llenarCombo(combo, "POA_PROCESOS", "CODIGO_PROCESO", "DESCRIPCION", "1=1", "" + proceso, false);
/*     */     
/* 323 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 330 */     PoaMaestroActividadesDAO rs = new PoaMaestroActividadesDAO();
/* 331 */     if (!rs.getEstadoConexion()) {
/* 332 */       rs.close();
/* 333 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     } 
/* 335 */     Collection<PoaMaestroActividadesDTO> arr = rs.cargarTodos(area, ciclo, mes, proceso);
/* 336 */     rs.close();
/* 337 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 338 */     int cuantas = 0;
/* 339 */     Iterator<PoaMaestroActividadesDTO> iterator = arr.iterator();
/* 340 */     while (iterator.hasNext()) {
/* 341 */       PoaMaestroActividadesDTO reg = (PoaMaestroActividadesDTO)iterator.next();
/* 342 */       PoaLogrosDAO rs1 = new PoaLogrosDAO();
/* 343 */       String periodo = mes.replace("mes", "");
/* 344 */       PoaLogrosDTO logro = rs1.cargarRegistro(reg.getCodigoPoaActividad(), getMes(Integer.parseInt(periodo)));
/* 345 */       rs1.close();
/* 346 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 347 */       eltr.appendChild(newtd("" + getMes(Integer.parseInt(periodo))));
/* 348 */       String url = "PoaLogros.po?_operacion=Nuevo&idPoa=" + reg.getCodigoPoaActividad() + "&periodo=" + mes + "&ciclo=" + ciclo + "" + "&valor=" + getValorEsperado(Integer.parseInt(periodo), reg) + "&proceso=" + proceso;
/*     */       
/* 350 */       if (logro != null) {
/* 351 */         url = "PoaLogros.po?_operacion=P&idLogro=" + logro.getIdLogro();
/*     */       }
/* 353 */       eltr.appendChild(newtd("" + reg.getNombreObjetivoEstrategico()));
/* 354 */       eltr.appendChild(newtdhref("" + reg.getNombreActividad(), url));
/* 355 */       if (logro != null) {
/* 356 */         eltr.appendChild(newtd("" + logro.getEjecucion()));
/* 357 */         eltr.appendChild(newtd("" + logro.getFechaInsercion()));
/* 358 */         eltr.appendChild(newtd("" + logro.getFechaModificacion()));
/*     */       } 
/* 360 */       hte.appendChild(eltr);
/* 361 */       cuantas++;
/*     */     } 
/* 363 */     arr.clear();
/* 364 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 377 */     int idLogro = 0;
/*     */     try {
/* 379 */       idLogro = Integer.parseInt(comms.request.getParameter("idLogro"));
/*     */     }
/* 381 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 384 */     PoaLogrosDAO rs = new PoaLogrosDAO();
/* 385 */     if (!rs.getEstadoConexion()) {
/* 386 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 388 */     PoaLogrosDTO reg = rs.cargarRegistro(idLogro);
/* 389 */     rs.close();
/* 390 */     PoaMaestroActividadesDAO rs1 = new PoaMaestroActividadesDAO();
/* 391 */     PoaMaestroActividadesDTO maestro = rs1.cargarRegistro(reg.getCodigoPoa());
/* 392 */     rs1.close();
/* 393 */     if (reg != null) {
/* 394 */       this.pagHTML.setTextIdLogroEd("" + reg.getIdLogro());
/* 395 */       this.pagHTML.setTextCodigoPoaEd("" + reg.getCodigoPoa());
/* 396 */       this.pagHTML.setTextActividadEd("" + maestro.getNombreActividad());
/* 397 */       this.pagHTML.setTextObjetivoEd("" + maestro.getNombreObjetivoEstrategico());
/* 398 */       this.pagHTML.setTextMesEd("" + reg.getMes());
/* 399 */       this.pagHTML.setTextEjecucionEd("" + reg.getEjecucion());
/* 400 */       this.pagHTML.setTextAvancesEd("" + reg.getAvances());
/* 401 */       this.pagHTML.setTextLogrosYResultadosEd("" + reg.getLogrosYResultados());
/* 402 */       this.pagHTML.setTextRetrasosDificultadesEd("" + reg.getRetrasosDificultades());
/* 403 */       this.pagHTML.setTextEstadoEd("" + reg.getEstado());
/* 404 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 405 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 406 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 407 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 409 */       this.pagHTML.getElementIdLogroKey().setValue("" + reg.getIdLogro());
/* 410 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 412 */     activarVista("editar");
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
/* 423 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 425 */     Varios oVarios = new Varios();
/* 426 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Poa_PoaLogrosAct");
/* 427 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Poa_PoaLogrosDel");
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
/* 449 */     if (!vista.equals("nuevo")) {
/* 450 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 451 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 453 */     if (!vista.equals("editar")) {
/* 454 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 455 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 457 */     if (!vista.equals("consulta")) {
/* 458 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 459 */       sel.getParentNode().removeChild(sel);
/* 460 */       sel = this.pagHTML.getElementDivResultados();
/* 461 */       sel.getParentNode().removeChild(sel);
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
/* 475 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 476 */     atrib.setValue(valor);
/* 477 */     return atrib;
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
/* 490 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 491 */     Element enlace = this.pagHTML.createElement("a");
/* 492 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 493 */     enlace.appendChild(hijo);
/* 494 */     Attr donde = this.pagHTML.createAttribute("href");
/* 495 */     donde.setValue(vinculo);
/* 496 */     enlace.setAttributeNode(donde);
/* 497 */     td.appendChild(enlace);
/* 498 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 499 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 509 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 510 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 511 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 512 */     return td;
/*     */   }
/*     */ 
/*     */   
/*     */   private void consulta(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 517 */     activarVista("consulta");
/* 518 */     HTMLSelectElement combo = this.pagHTML.getElementFarea();
/* 519 */     llenarCombo(combo, "UNIDADES_DEPENDENCIA", "CODIGO", "DESCRIPCION", "1=1", "", false);
/* 520 */     combo = this.pagHTML.getElementFciclo();
/* 521 */     llenarCombo(combo, "POA_CICLOS", "CODIGO_CICLO", "DESCRIPCION", "1=1", "", false);
/* 522 */     combo = this.pagHTML.getElementFperiodo();
/* 523 */     llenarComboMeses(combo, "", false);
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
/*     */   
/*     */   private void llenarCombo(HTMLSelectElement combo, String tabla, String codigo, String descripcion, String condicion, String defecto, boolean dejarBlanco) {
/* 546 */     if (dejarBlanco) {
/* 547 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 548 */       op.setValue("");
/* 549 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 550 */       combo.appendChild(op);
/*     */     } 
/* 552 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 553 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/* 554 */     rsTGen.close();
/* 555 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 556 */     while (iterator.hasNext()) {
/* 557 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 558 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 559 */       op.setValue("" + regGeneral.getCodigoS());
/* 560 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 561 */       if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
/* 562 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 563 */         escogida.setValue("on");
/* 564 */         op.setAttributeNode(escogida);
/*     */       } 
/* 566 */       combo.appendChild(op);
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
/* 583 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 584 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 585 */     rs.close();
/* 586 */     if (dejarBlanco) {
/* 587 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 588 */       op.setValue("");
/* 589 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 590 */       combo.appendChild(op);
/*     */     } 
/* 592 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 593 */     while (iterator.hasNext()) {
/* 594 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 595 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 596 */       op.setValue("" + reg.getCodigo());
/* 597 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 598 */       if (defecto.equals(reg.getCodigo())) {
/* 599 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 600 */         escogida.setValue("on");
/* 601 */         op.setAttributeNode(escogida);
/*     */       } 
/* 603 */       combo.appendChild(op);
/*     */     } 
/* 605 */     arr.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void llenarComboMeses(HTMLSelectElement combo, String defecto, boolean dejarBlanco) {
/* 612 */     if (dejarBlanco) {
/* 613 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 614 */       op.setValue("");
/* 615 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 616 */       combo.appendChild(op);
/*     */     } 
/* 618 */     for (int i = 1; i <= 12; i++) {
/* 619 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 620 */       op.setValue("mes" + i);
/* 621 */       op.appendChild(this.pagHTML.createTextNode(getMes(i)));
/* 622 */       if (defecto.equals("mes" + i)) {
/* 623 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 624 */         escogida.setValue("on");
/* 625 */         op.setAttributeNode(escogida);
/*     */       } 
/* 627 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private String getMes(int mes) {
/* 633 */     switch (mes) {
/*     */       case 1:
/* 635 */         return "Enero";
/*     */       case 2:
/* 637 */         return "Febrero";
/*     */       
/*     */       case 3:
/* 640 */         return "Marzo";
/*     */       case 4:
/* 642 */         return "Abril";
/*     */       case 5:
/* 644 */         return "Mayo";
/*     */       case 6:
/* 646 */         return "Junio";
/*     */       case 7:
/* 648 */         return "Julio";
/*     */       case 8:
/* 650 */         return "Agosto";
/*     */       case 9:
/* 652 */         return "Septiembre";
/*     */       case 10:
/* 654 */         return "Octubre";
/*     */       case 11:
/* 656 */         return "Noviembre";
/*     */       case 12:
/* 658 */         return "Diciembre";
/*     */     } 
/*     */ 
/*     */     
/* 662 */     return "";
/*     */   }
/*     */   
/*     */   private String getMesNumero(String mes) {
/* 666 */     if (mes.equals("Enero")) {
/* 667 */       return "mes1";
/*     */     }
/* 669 */     if (mes.equals("Febrero")) {
/* 670 */       return "mes2";
/*     */     }
/* 672 */     if (mes.equals("Marzo")) {
/* 673 */       return "mes3";
/*     */     }
/* 675 */     if (mes.equals("Abril")) {
/* 676 */       return "mes4";
/*     */     }
/* 678 */     if (mes.equals("Mayo")) {
/* 679 */       return "mes5";
/*     */     }
/* 681 */     if (mes.equals("Junio")) {
/* 682 */       return "mes6";
/*     */     }
/* 684 */     if (mes.equals("Julio")) {
/* 685 */       return "mes7";
/*     */     }
/* 687 */     if (mes.equals("Agosto")) {
/* 688 */       return "mes8";
/*     */     }
/* 690 */     if (mes.equals("Septiembre")) {
/* 691 */       return "mes9";
/*     */     }
/* 693 */     if (mes.equals("Octubre")) {
/* 694 */       return "mes10";
/*     */     }
/* 696 */     if (mes.equals("Noviembre")) {
/* 697 */       return "mes11";
/*     */     }
/* 699 */     if (mes.equals("Diciembre")) {
/* 700 */       return "mes12";
/*     */     }
/* 702 */     return "";
/*     */   }
/*     */   
/*     */   private int getValorEsperado(int mes, PoaMaestroActividadesDTO poa) {
/* 706 */     switch (mes) {
/*     */       case 1:
/* 708 */         return poa.getValorMes1();
/*     */       case 2:
/* 710 */         return poa.getValorMes2();
/*     */       
/*     */       case 3:
/* 713 */         return poa.getValorMes3();
/*     */       case 4:
/* 715 */         return poa.getValorMes4();
/*     */       case 5:
/* 717 */         return poa.getValorMes5();
/*     */       case 6:
/* 719 */         return poa.getValorMes6();
/*     */       case 7:
/* 721 */         return poa.getValorMes7();
/*     */       case 8:
/* 723 */         return poa.getValorMes8();
/*     */       case 9:
/* 725 */         return poa.getValorMes9();
/*     */       case 10:
/* 727 */         return poa.getValorMes10();
/*     */       case 11:
/* 729 */         return poa.getValorMes11();
/*     */       case 12:
/* 731 */         return poa.getValorMes12();
/*     */     } 
/*     */ 
/*     */     
/* 735 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PoaLogros.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */