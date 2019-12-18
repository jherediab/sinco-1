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
/*     */ import sinco.business.IndicadorCaracteristicaDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.CaracteristicasDAO;
/*     */ import sinco.data.IndicadorCaracteristicaDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.IndicadorCaracteristica;
/*     */ import sinco.presentation.IndicadorCaracteristicaHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IndicadorCaracteristica
/*     */   implements HttpPresentation
/*     */ {
/*     */   private IndicadorCaracteristicaHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  41 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  42 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  45 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  46 */     String _operacion = comms.request.getParameter("_operacion");
/*  47 */     if (_operacion == null || _operacion.length() == 0) {
/*  48 */       _operacion = "X";
/*     */     }
/*     */     
/*  51 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  52 */       creacion(comms);
/*     */     }
/*     */     
/*  55 */     this.pagHTML = (IndicadorCaracteristicaHTML)comms.xmlcFactory.create(IndicadorCaracteristicaHTML.class);
/*  56 */     permisos(comms);
/*     */     
/*  58 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  59 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  62 */     if (_operacion.equals("P")) {
/*  63 */       editar(comms);
/*     */     }
/*  65 */     else if (_operacion.equals("Nuevo")) {
/*  66 */       nuevo(comms);
/*     */     } 
/*     */     
/*  69 */     if (_operacion.equals("V")) {
/*  70 */       verRegistro(comms);
/*     */     }
/*  72 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  73 */     comms.response.writeDOM(this.pagHTML);
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
/*  85 */     String _operacion = comms.request.getParameter("_operacion");
/*  86 */     String elUsuario = "" + comms.session.getUser().getName();
/*  87 */     int idRegistro = 0;
/*     */     try {
/*  89 */       idRegistro = Integer.parseInt(comms.request.getParameter("idRegistro"));
/*     */     }
/*  91 */     catch (Exception e) {
/*  92 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idRegistro"));
/*     */     } 
/*     */     
/*  95 */     RespuestaBD rta = new RespuestaBD();
/*  96 */     if (_operacion.equals("E")) {
/*  97 */       IndicadorCaracteristicaDAO rs = new IndicadorCaracteristicaDAO();
/*  98 */       if (!rs.getEstadoConexion()) {
/*  99 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 101 */       rta = rs.eliminarRegistro(idRegistro);
/* 102 */       if (!rta.isRta()) {
/* 103 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorIndicadorCaracteristica&p1=" + rta.getMensaje()));
/*     */       }
/* 105 */       rs.close();
/* 106 */       String sPagina = "IndicadorCaracteristica.po?_operacion=X";
/* 107 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 109 */     int ciclo = 0;
/*     */     try {
/* 111 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 113 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 116 */     String indicador = comms.request.getParameter("indicador");
/* 117 */     String mes = comms.request.getParameter("mes");
/* 118 */     int caracteristica = 0;
/*     */     try {
/* 120 */       caracteristica = Integer.parseInt(comms.request.getParameter("caracteristica"));
/*     */     }
/* 122 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 125 */     int valorProgramado = 0;
/*     */     try {
/* 127 */       valorProgramado = Integer.parseInt(comms.request.getParameter("valorProgramado"));
/*     */     }
/* 129 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 132 */     int valorEjecutado = 0;
/*     */     try {
/* 134 */       valorEjecutado = Integer.parseInt(comms.request.getParameter("valorEjecutado"));
/*     */     }
/* 136 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 139 */     String estado = comms.request.getParameter("estado");
/* 140 */     IndicadorCaracteristicaDAO rs = new IndicadorCaracteristicaDAO();
/* 141 */     if (!rs.getEstadoConexion()) {
/* 142 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 144 */     if (_operacion.equals("C")) {
/* 145 */       rta = rs.crearRegistro(idRegistro, ciclo, indicador, mes, caracteristica, valorProgramado, valorEjecutado, estado, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 155 */       idRegistro = rta.getSecuencia();
/*     */     } else {
/*     */       
/* 158 */       rta = rs.modificarRegistro(idRegistro, ciclo, indicador, mes, caracteristica, valorProgramado, valorEjecutado, estado, elUsuario);
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
/* 169 */     rs.close();
/* 170 */     if (!rta.isRta()) {
/* 171 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorIndicadorCaracteristica&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 174 */     String sPagina = "IndicadorCaracteristica.po?_operacion=L&ciclo=" + ciclo + "&indicador=" + indicador + "&mes=" + getMesNumero(mes);
/* 175 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 186 */     int idRegistro = 0;
/*     */     try {
/* 188 */       idRegistro = Integer.parseInt(comms.request.getParameter("idRegistro"));
/*     */     }
/* 190 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 193 */     IndicadorCaracteristicaDAO rs = new IndicadorCaracteristicaDAO();
/* 194 */     if (!rs.getEstadoConexion()) {
/* 195 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 197 */     IndicadorCaracteristicaDTO reg = rs.cargarRegistro(idRegistro);
/* 198 */     rs.close();
/* 199 */     if (reg != null) {
/* 200 */       this.pagHTML.getElementIdRegistro().setValue("" + reg.getIdRegistro());
/* 201 */       this.pagHTML.getElementCaracteristica().setValue("" + reg.getCaracteristica());
/* 202 */       this.pagHTML.getElementValorProgramado().setValue("" + reg.getValorProgramado());
/* 203 */       this.pagHTML.getElementValorEjecutado().setValue("" + reg.getValorEjecutado());
/* 204 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 205 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 206 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 207 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 208 */       this.pagHTML.getElementCiclo().setValue("" + reg.getCiclo());
/* 209 */       this.pagHTML.getElementIndicador().setValue("" + reg.getIndicador());
/* 210 */       this.pagHTML.getElementMes().setValue("" + reg.getMes());
/* 211 */       HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 212 */       comboMultivalores(combo, "ESTADO_REGISTRO", "A", false);
/*     */       
/* 214 */       this.pagHTML.getElementCaracteristica().setReadOnly(true);
/* 215 */       this.pagHTML.getElementIdRegistro().setReadOnly(true);
/* 216 */       this.pagHTML.getElementMes().setReadOnly(true);
/* 217 */       this.pagHTML.getElementIndicador().setReadOnly(true);
/* 218 */       this.pagHTML.getElementCiclo().setReadOnly(true);
/*     */     } 
/* 220 */     this.pagHTML.getElement_operacion().setValue("M");
/* 221 */     activarVista("nuevo");
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
/* 233 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 235 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 236 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 238 */     catch (Exception e) {}
/*     */     
/* 240 */     activarVista("nuevo");
/* 241 */     HTMLSelectElement combo = (HTMLSelectElement)this.pagHTML.getElementCiclo();
/* 242 */     llenarCombo(combo, "POA_CICLOS", "CODIGO_CICLO", "DESCRIPCION", "1=1", "", true);
/*     */     
/* 244 */     combo = (HTMLSelectElement)this.pagHTML.getElementIndicador();
/* 245 */     llenarCombo(combo, "INDICADORES", "CODIGO_INDICADOR", "NOMBRE_INDICADOR", "1=1", "", true);
/*     */     
/* 247 */     combo = (HTMLSelectElement)this.pagHTML.getElementMes();
/* 248 */     llenarCombo(combo, "", "codigo", "descripcion", "1=1", "", true);
/*     */     
/* 250 */     this.pagHTML.getElementIdRegistro().setReadOnly(true);
/* 251 */     this.pagHTML.getElementIdRegistro().setValue("0");
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
/* 262 */     activarVista("consulta");
/* 263 */     int ciclo = 0;
/*     */     try {
/* 265 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 267 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 270 */     String indicador = comms.request.getParameter("indicador");
/* 271 */     if (indicador == null) {
/* 272 */       indicador = "";
/*     */     }
/* 274 */     String mes = "";
/*     */     try {
/* 276 */       mes = getMes(Integer.parseInt(comms.request.getParameter("mes").replace("mes", "")));
/* 277 */     } catch (Exception e) {}
/*     */ 
/*     */     
/* 280 */     HTMLSelectElement combo = this.pagHTML.getElementFciclo();
/* 281 */     llenarCombo(combo, "POA_CICLOS", "CODIGO_CICLO", "DESCRIPCION", "1=1", "" + ciclo, false);
/* 282 */     combo = this.pagHTML.getElementFindicador();
/* 283 */     llenarCombo(combo, "INDICADORES", "CODIGO_INDICADOR", "NOMBRE_INDICADOR", "1=1", "" + indicador, false);
/* 284 */     combo = this.pagHTML.getElementFmes();
/* 285 */     llenarComboMeses(combo, "", false);
/* 286 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 291 */     IndicadorCaracteristicaDAO rs = new IndicadorCaracteristicaDAO();
/* 292 */     if (!rs.getEstadoConexion()) {
/* 293 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 295 */     Collection<IndicadorCaracteristicaDTO> arr = rs.cargarTodos(ciclo, indicador, mes);
/*     */ 
/*     */ 
/*     */     
/* 299 */     rs.close();
/* 300 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 301 */     int cuantas = 0;
/* 302 */     Iterator<IndicadorCaracteristicaDTO> iterator = arr.iterator();
/* 303 */     CaracteristicasDAO cars = new CaracteristicasDAO();
/* 304 */     while (iterator.hasNext()) {
/* 305 */       IndicadorCaracteristicaDTO reg = (IndicadorCaracteristicaDTO)iterator.next();
/* 306 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 307 */       eltr.appendChild(newtd("" + reg.getIdRegistro()));
/* 308 */       String url = "IndicadorCaracteristica.po?_operacion=V&idRegistro=" + reg.getIdRegistro() + "";
/* 309 */       eltr.appendChild(newtd("" + reg.getNombreCiclo()));
/* 310 */       eltr.appendChild(newtd("" + reg.getNombreIndicador()));
/* 311 */       eltr.appendChild(newtd("" + reg.getMes()));
/* 312 */       CaracteristicasDTO caracteristica = cars.cargarRegistro(reg.getCaracteristica());
/* 313 */       eltr.appendChild(newtdhref("" + caracteristica.getDescripcion(), url));
/* 314 */       eltr.appendChild(newtd("" + reg.getValorProgramado()));
/* 315 */       eltr.appendChild(newtd("" + reg.getValorEjecutado()));
/* 316 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 317 */       hte.appendChild(eltr);
/* 318 */       cuantas++;
/*     */     } 
/* 320 */     cars.close();
/* 321 */     arr.clear();
/* 322 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 335 */     int idRegistro = 0;
/*     */     try {
/* 337 */       idRegistro = Integer.parseInt(comms.request.getParameter("idRegistro"));
/*     */     }
/* 339 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 342 */     IndicadorCaracteristicaDAO rs = new IndicadorCaracteristicaDAO();
/* 343 */     if (!rs.getEstadoConexion()) {
/* 344 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 346 */     IndicadorCaracteristicaDTO reg = rs.cargarRegistro(idRegistro);
/* 347 */     rs.close();
/* 348 */     if (reg != null) {
/* 349 */       this.pagHTML.setTextIdRegistroEd("" + reg.getIdRegistro());
/* 350 */       this.pagHTML.setTextCicloEd("" + reg.getNombreCiclo());
/* 351 */       this.pagHTML.setTextIndicadorEd("" + reg.getNombreIndicador());
/* 352 */       this.pagHTML.setTextMesEd("" + reg.getMes());
/* 353 */       this.pagHTML.setTextCaracteristicaEd("" + reg.getCaracteristica());
/* 354 */       this.pagHTML.setTextValorProgramadoEd("" + reg.getValorProgramado());
/* 355 */       this.pagHTML.setTextValorEjecutadoEd("" + reg.getValorEjecutado());
/* 356 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/* 357 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 358 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 359 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 360 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 362 */       this.pagHTML.getElementIdRegistroKey().setValue("" + reg.getIdRegistro());
/* 363 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 365 */     activarVista("editar");
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
/* 376 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 378 */     Varios oVarios = new Varios();
/* 379 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "IndicadorIndicadorCaracteristicaAct");
/* 380 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "IndicadorIndicadorCaracteristicaDel");
/* 381 */     if (!oPermisoAct) {
/* 382 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 383 */       elem.getParentNode().removeChild(elem);
/* 384 */       elem = this.pagHTML.getElementBtnGrabar();
/* 385 */       elem.getParentNode().removeChild(elem);
/* 386 */       elem = this.pagHTML.getElementBtnModificar();
/* 387 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 389 */     if (!oPermisoDel) {
/* 390 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 391 */       elem.getParentNode().removeChild(elem);
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
/* 402 */     if (!vista.equals("nuevo")) {
/* 403 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 404 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 406 */     if (!vista.equals("editar")) {
/* 407 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 408 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 410 */     if (!vista.equals("consulta")) {
/* 411 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 412 */       sel.getParentNode().removeChild(sel);
/* 413 */       sel = this.pagHTML.getElementDivResultados();
/* 414 */       sel.getParentNode().removeChild(sel);
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
/* 428 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 429 */     atrib.setValue(valor);
/* 430 */     return atrib;
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
/* 443 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 444 */     Element enlace = this.pagHTML.createElement("a");
/* 445 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 446 */     enlace.appendChild(hijo);
/* 447 */     Attr donde = this.pagHTML.createAttribute("href");
/* 448 */     donde.setValue(vinculo);
/* 449 */     enlace.setAttributeNode(donde);
/* 450 */     td.appendChild(enlace);
/* 451 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 452 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 462 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 463 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 464 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 465 */     return td;
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
/* 480 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 481 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 482 */     rs.close();
/* 483 */     if (dejarBlanco) {
/* 484 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 485 */       op.setValue("");
/* 486 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 487 */       combo.appendChild(op);
/*     */     } 
/* 489 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 490 */     while (iterator.hasNext()) {
/* 491 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 492 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 493 */       op.setValue("" + reg.getCodigo());
/* 494 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 495 */       if (defecto.equals(reg.getCodigo())) {
/* 496 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 497 */         escogida.setValue("on");
/* 498 */         op.setAttributeNode(escogida);
/*     */       } 
/* 500 */       combo.appendChild(op);
/*     */     } 
/* 502 */     arr.clear();
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
/* 523 */     if (dejarBlanco) {
/* 524 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 525 */       op.setValue("");
/* 526 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 527 */       combo.appendChild(op);
/*     */     } 
/* 529 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 530 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/* 531 */     rsTGen.close();
/* 532 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 533 */     while (iterator.hasNext()) {
/* 534 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 535 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 536 */       op.setValue("" + regGeneral.getCodigoS());
/* 537 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 538 */       if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
/* 539 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 540 */         escogida.setValue("on");
/* 541 */         op.setAttributeNode(escogida);
/*     */       } 
/* 543 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void llenarComboMeses(HTMLSelectElement combo, String defecto, boolean dejarBlanco) {
/* 550 */     if (dejarBlanco) {
/* 551 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 552 */       op.setValue("");
/* 553 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 554 */       combo.appendChild(op);
/*     */     } 
/* 556 */     for (int i = 1; i <= 12; i++) {
/* 557 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 558 */       op.setValue("mes" + i);
/* 559 */       op.appendChild(this.pagHTML.createTextNode(getMes(i)));
/* 560 */       if (defecto.equals("mes" + i)) {
/* 561 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 562 */         escogida.setValue("on");
/* 563 */         op.setAttributeNode(escogida);
/*     */       } 
/* 565 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private String getMes(int mes) {
/* 571 */     switch (mes) {
/*     */       case 1:
/* 573 */         return "Enero";
/*     */       case 2:
/* 575 */         return "Febrero";
/*     */       
/*     */       case 3:
/* 578 */         return "Marzo";
/*     */       case 4:
/* 580 */         return "Abril";
/*     */       case 5:
/* 582 */         return "Mayo";
/*     */       case 6:
/* 584 */         return "Junio";
/*     */       case 7:
/* 586 */         return "Julio";
/*     */       case 8:
/* 588 */         return "Agosto";
/*     */       case 9:
/* 590 */         return "Septiembre";
/*     */       case 10:
/* 592 */         return "Octubre";
/*     */       case 11:
/* 594 */         return "Noviembre";
/*     */       case 12:
/* 596 */         return "Diciembre";
/*     */     } 
/*     */ 
/*     */     
/* 600 */     return "";
/*     */   }
/*     */   
/*     */   private String getMesNumero(String mes) {
/* 604 */     if (mes.equals("Enero")) {
/* 605 */       return "mes1";
/*     */     }
/* 607 */     if (mes.equals("Febrero")) {
/* 608 */       return "mes2";
/*     */     }
/* 610 */     if (mes.equals("Marzo")) {
/* 611 */       return "mes3";
/*     */     }
/* 613 */     if (mes.equals("Abril")) {
/* 614 */       return "mes4";
/*     */     }
/* 616 */     if (mes.equals("Mayo")) {
/* 617 */       return "mes5";
/*     */     }
/* 619 */     if (mes.equals("Junio")) {
/* 620 */       return "mes6";
/*     */     }
/* 622 */     if (mes.equals("Julio")) {
/* 623 */       return "mes7";
/*     */     }
/* 625 */     if (mes.equals("Agosto")) {
/* 626 */       return "mes8";
/*     */     }
/* 628 */     if (mes.equals("Septiembre")) {
/* 629 */       return "mes9";
/*     */     }
/* 631 */     if (mes.equals("Octubre")) {
/* 632 */       return "mes10";
/*     */     }
/* 634 */     if (mes.equals("Noviembre")) {
/* 635 */       return "mes11";
/*     */     }
/* 637 */     if (mes.equals("Diciembre")) {
/* 638 */       return "mes12";
/*     */     }
/* 640 */     return "";
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\IndicadorCaracteristica.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */