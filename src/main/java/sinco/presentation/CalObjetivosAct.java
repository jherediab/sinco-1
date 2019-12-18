/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.CalMetasDTO;
/*     */ import sinco.business.CalObjetivosDTO;
/*     */ import sinco.business.CalPlantillasDTO;
/*     */ import sinco.business.CalProcesosDTO;
/*     */ import sinco.business.CalSubProcesosDTO;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.data.CalMetasDAO;
/*     */ import sinco.data.CalObjetivosDAO;
/*     */ import sinco.data.CalPlantillasObjetivosDAO;
/*     */ import sinco.data.CalProcesosDAO;
/*     */ import sinco.data.CalSubProcesosDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.CalObjetivosAct;
/*     */ import sinco.presentation.CalObjetivosActHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalObjetivosAct
/*     */   implements HttpPresentation
/*     */ {
/*     */   private CalObjetivosActHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  45 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  46 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  49 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  50 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/*  52 */     String _operacion = comms.request.getParameter("_operacion");
/*  53 */     if (_operacion == null || _operacion.length() == 0) {
/*  54 */       _operacion = "L";
/*     */     }
/*     */     
/*  57 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  58 */       creacion(comms);
/*     */     }
/*  60 */     else if (_operacion.equals("CP")) {
/*  61 */       cargarPlantillas(comms);
/*     */     } 
/*     */     
/*  64 */     this.pagHTML = (CalObjetivosActHTML)comms.xmlcFactory.create(CalObjetivosActHTML.class);
/*  65 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  66 */       listar(comms, _operacion);
/*     */     
/*     */     }
/*  69 */     else if (_operacion.equals("P")) {
/*  70 */       editar(comms);
/*     */     }
/*  72 */     else if (_operacion.equals("Nuevo")) {
/*  73 */       nuevo(comms);
/*     */     } 
/*  75 */     if (_operacion.equals("P") || _operacion.equals("Nuevo")) {
/*  76 */       HTMLElement sel = this.pagHTML.getElementTrConsulta();
/*  77 */       sel.getParentNode().removeChild(sel);
/*  78 */       sel = this.pagHTML.getElementTrResultados();
/*  79 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/*  81 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  82 */       HTMLElement sel = this.pagHTML.getElementTrCreacionRegistro();
/*  83 */       sel.getParentNode().removeChild(sel);
/*  84 */       Varios oVarios = new Varios();
/*  85 */       boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "oCalObjetivosAct");
/*  86 */       if (!oPermisoAct) {
/*  87 */         HTMLElement elem = this.pagHTML.getElementBtnCrear();
/*  88 */         elem.getParentNode().removeChild(elem);
/*     */       } 
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
/* 106 */     int codigo = 0;
/*     */     try {
/* 108 */       codigo = Integer.parseInt(comms.request.getParameter("codigoObjetivo"));
/*     */     }
/* 110 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 113 */     boolean rta = false;
/* 114 */     if (_operacion.equals("E")) {
/* 115 */       CalObjetivosDAO rs = new CalObjetivosDAO();
/* 116 */       if (!rs.getEstadoConexion()) {
/* 117 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 119 */       rta = rs.eliminarRegistro(codigo);
/* 120 */       rs.close();
/* 121 */       if (!rta) {
/* 122 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCalObjetivos"));
/*     */       }
/*     */       
/* 125 */       String sPagina = "CalObjetivosAct.po?_operacion=X";
/* 126 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 128 */     String proceso = comms.request.getParameter("proceso");
/* 129 */     String subproceso = comms.request.getParameter("subproceso");
/* 130 */     String descripcion = comms.request.getParameter("descripcion");
/* 131 */     String justificacion = comms.request.getParameter("justificacion");
/* 132 */     String tipoObjetivo = comms.request.getParameter("tipoObjetivo");
/*     */     
/* 134 */     String estado = comms.request.getParameter("estado");
/* 135 */     CalObjetivosDAO rs = new CalObjetivosDAO();
/* 136 */     if (!rs.getEstadoConexion()) {
/* 137 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 139 */     rta = false;
/* 140 */     if (_operacion.equals("C")) {
/* 141 */       rta = rs.crearRegistro(codigo, proceso, subproceso, descripcion, justificacion, tipoObjetivo, 0, estado, "", elUsuario);
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
/* 154 */       rta = rs.modificarRegistro(codigo, proceso, subproceso, descripcion, justificacion, tipoObjetivo, 0, estado, "", elUsuario);
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
/* 166 */     rs.close();
/* 167 */     if (!rta) {
/* 168 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCalObjetivos"));
/*     */     }
/* 170 */     String sPagina = "CalObjetivosAct.po?_operacion=P&codigoObjetivo=" + codigo + "";
/* 171 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 182 */     String proceso = comms.request.getParameter("fproceso");
/* 183 */     if (proceso == null) {
/* 184 */       proceso = "";
/*     */     }
/* 186 */     String descripcion = comms.request.getParameter("fdescripcion");
/* 187 */     if (descripcion == null) descripcion = ""; 
/* 188 */     String tipoObjetivo = comms.request.getParameter("ftipoObjetivo");
/* 189 */     if (tipoObjetivo == null) tipoObjetivo = "";
/*     */     
/* 191 */     HTMLSelectElement combo = this.pagHTML.getElementFproceso();
/* 192 */     comboProcesos(comms, combo, proceso);
/*     */     
/* 194 */     combo = this.pagHTML.getElementFtipoObjetivo();
/* 195 */     comboMultivalores(combo, "CAL_TIPO_OBJETIVO", "" + tipoObjetivo, true);
/* 196 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */     
/* 200 */     CalObjetivosDAO rs = new CalObjetivosDAO();
/* 201 */     if (!rs.getEstadoConexion()) {
/* 202 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 204 */     Collection<CalObjetivosDTO> arr = rs.cargarTodos(proceso, descripcion, tipoObjetivo);
/*     */ 
/*     */ 
/*     */     
/* 208 */     rs.close();
/* 209 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 210 */     int cuantas = 0;
/* 211 */     Iterator<CalObjetivosDTO> iterator = arr.iterator();
/* 212 */     while (iterator.hasNext()) {
/* 213 */       CalObjetivosDTO reg = (CalObjetivosDTO)iterator.next();
/* 214 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/* 216 */       eltr.appendChild(newtd("" + reg.getCodigoObjetivo()));
/*     */       
/* 218 */       String url = "CalObjetivosAct.po?_operacion=P&codigoObjetivo=" + reg.getCodigoObjetivo();
/*     */       
/* 220 */       eltr.appendChild(newtdhref("" + reg.getDescripcion(), url));
/* 221 */       eltr.appendChild(newtd("" + reg.getNombreProceso()));
/* 222 */       eltr.appendChild(newtd("" + reg.getNombreSubProceso()));
/* 223 */       eltr.appendChild(newtd("" + reg.getNombreTipoObjetivo()));
/* 224 */       eltr.appendChild(newtd("" + reg.getEstado()));
/* 225 */       hte.appendChild(eltr);
/* 226 */       cuantas++;
/*     */     } 
/* 228 */     arr.clear();
/* 229 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/*     */   private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 242 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 244 */     int codigo = 0;
/*     */     try {
/* 246 */       codigo = Integer.parseInt(comms.request.getParameter("codigoObjetivo"));
/*     */     }
/* 248 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 251 */     CalObjetivosDAO rs = new CalObjetivosDAO();
/* 252 */     if (!rs.getEstadoConexion()) {
/* 253 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 255 */     CalObjetivosDTO reg = rs.cargarRegistro(codigo);
/* 256 */     rs.close();
/* 257 */     Varios oVarios = new Varios();
/* 258 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "oCalObjetivosAct");
/* 259 */     if (!oPermisoAct) {
/* 260 */       HTMLElement elem = this.pagHTML.getElementBtnGrabar();
/* 261 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 263 */     if (reg != null) {
/* 264 */       this.pagHTML.getElementCodigoObjetivo().setValue("" + reg.getCodigoObjetivo());
/* 265 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 266 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 267 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 268 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 269 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/*     */       
/* 271 */       HTMLSelectElement combo = this.pagHTML.getElementProceso();
/* 272 */       comboProcesos(comms, combo, reg.getProceso());
/*     */       
/* 274 */       combo = this.pagHTML.getElementSubproceso();
/* 275 */       comboSubProcesos(comms, combo, reg.getProceso(), reg.getSubProceso());
/*     */       
/* 277 */       combo = this.pagHTML.getElementTipoObjetivo();
/* 278 */       comboMultivalores(combo, "CAL_TIPO_OBJETIVO", "" + reg.getTipoObjetivo(), true);
/*     */       
/* 280 */       combo = this.pagHTML.getElementEstado();
/* 281 */       comboMultivalores(combo, "CAL_ESTADO_OBJETIVO", "" + reg.getEstado(), true);
/*     */       
/* 283 */       this.pagHTML.getElementCodigoObjetivo().setReadOnly(true);
/* 284 */       this.pagHTML.getElement_operacion().setValue("M");
/*     */       
/* 286 */       listarMetas(comms, codigo);
/*     */       
/* 288 */       cargarPlantillas(comms, codigo);
/*     */     } 
/*     */     
/* 291 */     String elScript = generarScript();
/* 292 */     this.pagHTML.setTextElScript("" + elScript);
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
/* 304 */     HTMLSelectElement combo = this.pagHTML.getElementProceso();
/* 305 */     comboProcesos(comms, combo, "");
/*     */     
/* 307 */     String elScript = generarScript();
/* 308 */     this.pagHTML.setTextElScript("" + elScript);
/*     */     
/* 310 */     combo = this.pagHTML.getElementTipoObjetivo();
/* 311 */     comboMultivalores(combo, "CAL_TIPO_OBJETIVO", "", true);
/*     */     
/* 313 */     combo = this.pagHTML.getElementEstado();
/* 314 */     comboMultivalores(combo, "CAL_ESTADO_OBJETIVO", "", true);
/*     */     
/* 316 */     CalObjetivosDAO rs = new CalObjetivosDAO();
/* 317 */     this.pagHTML.getElementCodigoObjetivo().setValue("" + rs.siguienteRegistro());
/* 318 */     rs.close();
/*     */     
/* 320 */     HTMLElement sel = this.pagHTML.getElementMetas();
/* 321 */     sel.getParentNode().removeChild(sel);
/*     */     
/* 323 */     sel = this.pagHTML.getElementTrMetas();
/* 324 */     sel.getParentNode().removeChild(sel);
/*     */     
/* 326 */     sel = this.pagHTML.getElementTblPlantillas();
/* 327 */     sel.getParentNode().removeChild(sel);
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
/* 339 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 340 */     atrib.setValue(valor);
/* 341 */     return atrib;
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
/* 354 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 355 */     Element enlace = this.pagHTML.createElement("a");
/* 356 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 357 */     enlace.appendChild(hijo);
/* 358 */     Attr donde = this.pagHTML.createAttribute("href");
/* 359 */     donde.setValue(vinculo);
/* 360 */     enlace.setAttributeNode(donde);
/* 361 */     td.appendChild(enlace);
/* 362 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 363 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 373 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 374 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 375 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 376 */     return td;
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
/* 392 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 393 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 394 */     rs.close();
/* 395 */     if (dejarBlanco) {
/* 396 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 397 */       op.setValue("");
/* 398 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 399 */       combo.appendChild(op);
/*     */     } 
/* 401 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 402 */     while (iterator.hasNext()) {
/* 403 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/*     */ 
/*     */       
/* 406 */       if (tabla.equals("CAL_TIPO_OBJETIVO") && reg.getCodigo().equals("E")) {
/*     */         continue;
/*     */       }
/*     */       
/* 410 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 411 */       op.setValue("" + reg.getCodigo());
/* 412 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 413 */       if (defecto.equals(reg.getCodigo())) {
/* 414 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 415 */         escogida.setValue("on");
/* 416 */         op.setAttributeNode(escogida);
/*     */       } 
/* 418 */       combo.appendChild(op);
/*     */     } 
/* 420 */     arr.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void comboProcesos(HttpPresentationComms comms, HTMLSelectElement combo, String defecto) throws HttpPresentationException, KeywordValueException {
/* 431 */     CalProcesosDAO rs = new CalProcesosDAO();
/*     */     
/* 433 */     if (!rs.getEstadoConexion()) {
/* 434 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 437 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 438 */     op.setValue("");
/* 439 */     op.appendChild(this.pagHTML.createTextNode(""));
/* 440 */     combo.appendChild(op);
/*     */     
/* 442 */     rs.cargarTodos("A");
/*     */     CalProcesosDTO reg;
/* 444 */     while ((reg = rs.next()) != null) {
/* 445 */       op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 446 */       op.setValue("" + reg.getProceso());
/* 447 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 448 */       if (defecto.equals(reg.getProceso())) {
/* 449 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 450 */         escogida.setValue("on");
/* 451 */         op.setAttributeNode(escogida);
/*     */       } 
/* 453 */       combo.appendChild(op);
/*     */     } 
/* 455 */     rs.close();
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
/*     */   private void comboSubProcesos(HttpPresentationComms comms, HTMLSelectElement combo, String proceso, String defecto) throws HttpPresentationException, KeywordValueException {
/* 469 */     CalSubProcesosDAO rs = new CalSubProcesosDAO();
/*     */     
/* 471 */     if (!rs.getEstadoConexion()) {
/* 472 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 475 */     Collection<CalSubProcesosDTO> arr = rs.cargarDeProceso(proceso);
/* 476 */     rs.close();
/*     */     
/* 478 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 479 */     op.setValue("");
/* 480 */     op.appendChild(this.pagHTML.createTextNode(""));
/* 481 */     combo.appendChild(op);
/*     */     
/* 483 */     Iterator<CalSubProcesosDTO> iterator = arr.iterator();
/* 484 */     while (iterator.hasNext()) {
/* 485 */       CalSubProcesosDTO reg = (CalSubProcesosDTO)iterator.next();
/*     */       
/* 487 */       op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 488 */       op.setValue("" + reg.getSubproceso());
/* 489 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 490 */       if (defecto.equals(reg.getSubproceso())) {
/* 491 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 492 */         escogida.setValue("on");
/* 493 */         op.setAttributeNode(escogida);
/*     */       } 
/* 495 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String generarScript() {
/* 506 */     CalSubProcesosDAO rs = new CalSubProcesosDAO();
/* 507 */     Collection<CalSubProcesosDTO> arr = rs.cargarTodosActivos();
/* 508 */     rs.close();
/*     */ 
/*     */     
/* 511 */     String script = "var matriz=new Array(" + arr.size() + ");";
/* 512 */     script = script + " var iCont=0;";
/* 513 */     Iterator<CalSubProcesosDTO> iterator = arr.iterator();
/* 514 */     while (iterator.hasNext()) {
/* 515 */       CalSubProcesosDTO reg = (CalSubProcesosDTO)iterator.next();
/* 516 */       script = script + " matriz[iCont++]=new add_member('" + reg.getProceso() + "','" + reg.getSubproceso() + "','" + reg.getDescripcion() + "');";
/*     */     } 
/* 518 */     return script;
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
/*     */   private void listarMetas(HttpPresentationComms comms, int codigoObjetivo) throws HttpPresentationException, KeywordValueException {
/* 531 */     CalMetasDAO rs = new CalMetasDAO();
/* 532 */     if (!rs.getEstadoConexion()) {
/* 533 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 535 */     Collection<CalMetasDTO> arr = rs.cargarDeObjetivo(codigoObjetivo);
/* 536 */     rs.close();
/* 537 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalleMetas();
/* 538 */     int cuantas = 0;
/* 539 */     Iterator<CalMetasDTO> iterator = arr.iterator();
/* 540 */     while (iterator.hasNext()) {
/* 541 */       CalMetasDTO reg = (CalMetasDTO)iterator.next();
/* 542 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 543 */       String url = "CalMetasAct.po?_operacion=P&codigoMeta=" + reg.getCodigoMeta() + "&codigoObjetivo=" + codigoObjetivo;
/* 544 */       eltr.appendChild(newtdhref("" + reg.getDescripcion(), url));
/* 545 */       eltr.appendChild(newtd("" + reg.getJustificacion()));
/* 546 */       eltr.appendChild(newtd("" + reg.getValorMeta()));
/* 547 */       eltr.appendChild(newtd("" + reg.getTipoMedicion()));
/* 548 */       eltr.appendChild(newtd("" + reg.getEstado()));
/* 549 */       eltr.appendChild(newtd("" + reg.getNombreFrecuenciaMedicion()));
/* 550 */       hte.appendChild(eltr);
/* 551 */       cuantas++;
/*     */     } 
/* 553 */     arr.clear();
/* 554 */     this.pagHTML.setTextNroMetas("" + cuantas);
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
/*     */   private void cargarPlantillas(HttpPresentationComms comms, int codigoObjetivo) throws HttpPresentationException, KeywordValueException {
/* 568 */     this.pagHTML.getElementCodigoObjetivoP().setValue("" + codigoObjetivo);
/*     */     
/* 570 */     CalPlantillasObjetivosDAO rs = new CalPlantillasObjetivosDAO();
/* 571 */     if (!rs.getEstadoConexion()) {
/* 572 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 574 */     Collection<CalPlantillasDTO> arr = rs.cargarPlantillasObjetivos(codigoObjetivo);
/* 575 */     rs.close();
/* 576 */     HTMLTableSectionElement hte = this.pagHTML.getElementPlantillas();
/* 577 */     Iterator<CalPlantillasDTO> iterator = arr.iterator();
/* 578 */     while (iterator.hasNext()) {
/* 579 */       CalPlantillasDTO reg = (CalPlantillasDTO)iterator.next();
/* 580 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */ 
/*     */       
/* 583 */       HTMLElement tdMarca = (HTMLElement)this.pagHTML.createElement("td");
/* 584 */       HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
/* 585 */       checkbox.setAttribute("type", "checkbox");
/* 586 */       checkbox.setName("_X" + reg.getCodigo());
/* 587 */       checkbox.setChecked(reg.getExiste().equals("S"));
/* 588 */       tdMarca.appendChild(checkbox);
/*     */       
/* 590 */       eltr.appendChild(tdMarca);
/*     */       
/* 592 */       eltr.appendChild(newtd("" + reg.getCodigo()));
/* 593 */       eltr.appendChild(newtd("" + reg.getDescripcion()));
/* 594 */       eltr.appendChild(newtd("" + reg.getEstado()));
/* 595 */       hte.appendChild(eltr);
/*     */     } 
/* 597 */     arr.clear();
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
/*     */   private void cargarPlantillas(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 610 */     String elUsuario = "" + comms.session.getUser().getName();
/* 611 */     int codigo = 0;
/*     */     try {
/* 613 */       codigo = Integer.parseInt(comms.request.getParameter("codigoObjetivo"));
/*     */     }
/* 615 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 618 */     CalPlantillasObjetivosDAO rs = new CalPlantillasObjetivosDAO();
/* 619 */     if (!rs.getEstadoConexion()) {
/* 620 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 623 */     rs.eliminarPlantilla(codigo, elUsuario);
/*     */     
/* 625 */     Enumeration enumera = comms.request.getParameterNames();
/*     */ 
/*     */     
/* 628 */     while (enumera.hasMoreElements()) {
/* 629 */       String param = (String)enumera.nextElement();
/* 630 */       if (param.substring(0, 2).equals("_X")) {
/* 631 */         int codigoPlantilla = Integer.parseInt(param.substring(2, param.length()));
/* 632 */         rs.crearRegistro(codigoPlantilla, codigo, elUsuario);
/*     */       } 
/*     */     } 
/* 635 */     rs.close();
/*     */     
/* 637 */     String sPagina = "CalObjetivosAct.po?_operacion=P&codigoObjetivo=" + codigo + "";
/* 638 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalObjetivosAct.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */