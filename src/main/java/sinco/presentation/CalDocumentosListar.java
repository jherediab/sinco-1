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
/*     */ import sinco.business.CalDocumentosDTO;
/*     */ import sinco.business.CalProcesosDTO;
/*     */ import sinco.business.CalResponsablesDTO;
/*     */ import sinco.business.CalSubProcesosDTO;
/*     */ import sinco.business.CalTiposDocumentoDTO;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.data.CalDocumentosDAO;
/*     */ import sinco.data.CalProcesosDAO;
/*     */ import sinco.data.CalResponsablesDAO;
/*     */ import sinco.data.CalSubProcesosDAO;
/*     */ import sinco.data.CalTiposDocumentoDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.CalDocumentosListar;
/*     */ import sinco.presentation.CalDocumentosListarHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalDocumentosListar
/*     */   implements HttpPresentation
/*     */ {
/*  40 */   String elUsuario = "";
/*     */ 
/*     */   
/*     */   private CalDocumentosListarHTML pagHTML;
/*     */ 
/*     */ 
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  48 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  49 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  52 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*     */     
/*  54 */     this.elUsuario = "" + comms.session.getUser().getName();
/*  55 */     String _operacion = comms.request.getParameter("_operacion");
/*  56 */     if (_operacion == null || _operacion.length() == 0) {
/*  57 */       _operacion = "X";
/*     */     }
/*     */     
/*  60 */     this.pagHTML = (CalDocumentosListarHTML)comms.xmlcFactory.create(CalDocumentosListarHTML.class);
/*     */     
/*  62 */     listar(comms, _operacion);
/*     */     
/*  64 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  65 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
/*  82 */     String codigo = comms.request.getParameter("codigo");
/*  83 */     String tipoDocumento = comms.request.getParameter("tipoDocumento");
/*  84 */     String proceso = comms.request.getParameter("proceso");
/*  85 */     String descripcion = comms.request.getParameter("descripcion");
/*  86 */     String estado = comms.request.getParameter("estado");
/*  87 */     String observaciones = comms.request.getParameter("fobservaciones");
/*  88 */     String subproceso = comms.request.getParameter("subproceso");
/*  89 */     String unidadServicio = comms.request.getParameter("unidadServicio");
/*  90 */     String documento = comms.request.getParameter("documento");
/*  91 */     String fechaVersion = comms.request.getParameter("fechaVersion");
/*  92 */     String version = comms.request.getParameter("version");
/*  93 */     String fechaEmision = comms.request.getParameter("fechaEmision");
/*     */     
/*  95 */     int responsable = 0;
/*     */     try {
/*  97 */       responsable = Integer.parseInt(comms.request.getParameter("responsable"));
/*  98 */     } catch (Exception e) {}
/*     */ 
/*     */     
/* 101 */     int distribuido = 0;
/*     */     try {
/* 103 */       distribuido = Integer.parseInt(comms.request.getParameter("distribuido"));
/* 104 */     } catch (Exception e) {}
/*     */ 
/*     */     
/* 107 */     if (codigo == null) codigo = ""; 
/* 108 */     if (tipoDocumento == null) tipoDocumento = ""; 
/* 109 */     if (estado == null) estado = ""; 
/* 110 */     if (proceso == null) proceso = ""; 
/* 111 */     if (subproceso == null) subproceso = ""; 
/* 112 */     if (unidadServicio == null) unidadServicio = ""; 
/* 113 */     if (documento == null) documento = ""; 
/* 114 */     if (descripcion == null) descripcion = ""; 
/* 115 */     if (observaciones == null) observaciones = "";
/*     */     
/* 117 */     if (fechaVersion == null) fechaVersion = ""; 
/* 118 */     if (version == null) version = ""; 
/* 119 */     if (fechaEmision == null) fechaEmision = "";
/*     */     
/* 121 */     HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 122 */     comboMultivalores(combo, "CAL_ESTADO_DOCUMENTOS", "" + estado, true);
/*     */     
/* 124 */     combo = this.pagHTML.getElementTipoDocumento();
/* 125 */     comboTipoDocumentos(combo, "" + tipoDocumento, true);
/*     */     
/* 127 */     comboProcesos(comms, proceso);
/* 128 */     comboSubProcesos(comms, proceso, subproceso);
/* 129 */     comboUnidades(comms, proceso, subproceso, unidadServicio);
/*     */     
/* 131 */     this.pagHTML.setTextElScript("" + generarScript());
/*     */     
/* 133 */     CalResponsablesDAO rsArea = new CalResponsablesDAO();
/* 134 */     Collection arr = rsArea.cargarAreasCalidad();
/* 135 */     rsArea.close();
/*     */     
/* 137 */     combo = this.pagHTML.getElementResponsable();
/* 138 */     poblarComboArea(combo, arr, responsable);
/*     */     
/* 140 */     combo = this.pagHTML.getElementDistribuido();
/* 141 */     poblarComboArea(combo, arr, distribuido);
/*     */     
/* 143 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */     
/* 147 */     this.pagHTML.getElementDescripcion().setValue("" + descripcion);
/* 148 */     this.pagHTML.getElementObservaciones().setValue("" + observaciones);
/* 149 */     this.pagHTML.getElementVersion().setValue("" + version);
/* 150 */     this.pagHTML.getElementFechaVersion().setValue("" + fechaVersion);
/* 151 */     this.pagHTML.getElementFechaEmision().setValue("" + fechaEmision);
/*     */ 
/*     */     
/* 154 */     CalDocumentosDAO rs = new CalDocumentosDAO();
/* 155 */     if (!rs.getEstadoConexion()) {
/* 156 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 159 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/*     */     
/* 161 */     Collection arrDoc = rs.cargarTodos(codigo, tipoDocumento, proceso, subproceso, unidadServicio, descripcion, version, fechaVersion, fechaEmision, observaciones, estado, responsable, distribuido);
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
/* 176 */     rs.close();
/*     */ 
/*     */     
/* 179 */     Iterator iterator = arrDoc.iterator();
/* 180 */     while (iterator.hasNext()) {
/* 181 */       CalDocumentosDTO reg = (CalDocumentosDTO)iterator.next();
/* 182 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 183 */       eltr.appendChild(newtdhref("" + reg.getCodigo(), "CalDocumentosAct.po?_operacion=P&codigo=" + reg.getCodigo(), false));
/* 184 */       eltr.appendChild(newtd("" + reg.getDescripcion()));
/* 185 */       eltr.appendChild(newtd("" + reg.getNombreProceso()));
/* 186 */       eltr.appendChild(newtd("" + reg.getNombreSubProceso()));
/* 187 */       eltr.appendChild(newtd("" + reg.getVersion()));
/* 188 */       eltr.appendChild(newtd("" + reg.getFechaVersion()));
/* 189 */       eltr.appendChild(newtd("" + reg.getFechaEmision()));
/* 190 */       eltr.appendChild(newtd("" + reg.getObservaciones()));
/* 191 */       eltr.appendChild(newtd("" + reg.getEstado()));
/* 192 */       eltr.appendChild(newtd("" + reg.getResponsables()));
/* 193 */       eltr.appendChild(newtd("" + reg.getOrden()));
/* 194 */       eltr.appendChild(newtd("" + reg.getFechaModificacion()));
/*     */       
/* 196 */       hte.appendChild(eltr);
/*     */     } 
/* 198 */     this.pagHTML.setTextNroRegistros("" + arrDoc.size());
/* 199 */     arrDoc.clear();
/* 200 */     arr.clear();
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
/* 213 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 214 */     atrib.setValue(valor);
/* 215 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 225 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 226 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 227 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 228 */     return td;
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
/*     */   private HTMLElement newtdhref(String contenido, String vinculo, boolean nueva) {
/* 241 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 242 */     Element enlace = this.pagHTML.createElement("a");
/* 243 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 244 */     enlace.appendChild(hijo);
/* 245 */     Attr donde = this.pagHTML.createAttribute("href");
/* 246 */     donde.setValue(vinculo);
/* 247 */     enlace.setAttributeNode(donde);
/* 248 */     if (nueva) {
/* 249 */       enlace.setAttributeNode(newAttr("TARGET", "_blank"));
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void comboTipoDocumentos(HTMLSelectElement combo, String defecto, boolean dejarBlanco) {
/* 267 */     CalTiposDocumentoDAO rs = new CalTiposDocumentoDAO();
/* 268 */     Collection arr = rs.cargarTodos("");
/* 269 */     rs.close();
/* 270 */     if (dejarBlanco) {
/* 271 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 272 */       op.setValue("");
/* 273 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 274 */       combo.appendChild(op);
/*     */     } 
/* 276 */     Iterator iterator = arr.iterator();
/* 277 */     while (iterator.hasNext()) {
/* 278 */       CalTiposDocumentoDTO reg = (CalTiposDocumentoDTO)iterator.next();
/* 279 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 280 */       op.setValue("" + reg.getCodigo());
/* 281 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 282 */       if (defecto.equals(reg.getCodigo())) {
/* 283 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 284 */         escogida.setValue("on");
/* 285 */         op.setAttributeNode(escogida);
/*     */       } 
/* 287 */       combo.appendChild(op);
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
/*     */   private void comboMultivalores(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco) {
/* 303 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 304 */     Collection arr = rs.cargarTabla(tabla);
/* 305 */     rs.close();
/* 306 */     if (dejarBlanco) {
/* 307 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 308 */       op.setValue("");
/* 309 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 310 */       combo.appendChild(op);
/*     */     } 
/* 312 */     Iterator iterator = arr.iterator();
/* 313 */     while (iterator.hasNext()) {
/* 314 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 315 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 316 */       op.setValue("" + reg.getCodigo());
/* 317 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 318 */       if (defecto.equals(reg.getCodigo())) {
/* 319 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 320 */         escogida.setValue("on");
/* 321 */         op.setAttributeNode(escogida);
/*     */       } 
/* 323 */       combo.appendChild(op);
/*     */     } 
/* 325 */     arr.clear();
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
/*     */   private void comboProcesos(HttpPresentationComms comms, String defecto) throws HttpPresentationException, KeywordValueException {
/* 339 */     HTMLSelectElement combo = this.pagHTML.getElementProceso();
/*     */     
/* 341 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 342 */     op.setValue("");
/* 343 */     op.appendChild(this.pagHTML.createTextNode(""));
/* 344 */     combo.appendChild(op);
/*     */     
/* 346 */     CalProcesosDAO rsTGen = new CalProcesosDAO();
/*     */     
/* 348 */     if (!rsTGen.getEstadoConexion()) {
/* 349 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 352 */     Collection arr = rsTGen.cargarTablaProcesos();
/* 353 */     rsTGen.close();
/*     */     
/* 355 */     Iterator iterator = arr.iterator();
/* 356 */     while (iterator.hasNext()) {
/* 357 */       CalProcesosDTO reg = (CalProcesosDTO)iterator.next();
/*     */       
/* 359 */       op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 360 */       op.setValue("" + reg.getProceso());
/* 361 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 362 */       if (defecto.equals(reg.getProceso())) {
/* 363 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 364 */         escogida.setValue("on");
/* 365 */         op.setAttributeNode(escogida);
/*     */       } 
/* 367 */       combo.appendChild(op);
/*     */     } 
/* 369 */     arr.clear();
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
/*     */   private void comboSubProcesos(HttpPresentationComms comms, String proceso, String defecto) throws HttpPresentationException, KeywordValueException {
/* 382 */     HTMLSelectElement combo = this.pagHTML.getElementSubproceso();
/*     */     
/* 384 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 385 */     op.setValue("");
/* 386 */     op.appendChild(this.pagHTML.createTextNode(""));
/* 387 */     combo.appendChild(op);
/*     */     
/* 389 */     CalSubProcesosDAO rs = new CalSubProcesosDAO();
/* 390 */     if (!rs.getEstadoConexion()) {
/* 391 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 394 */     Collection arr = rs.cargarDeProceso(proceso);
/* 395 */     rs.close();
/*     */     
/* 397 */     Iterator iterator = arr.iterator();
/* 398 */     while (iterator.hasNext()) {
/* 399 */       CalSubProcesosDTO reg = (CalSubProcesosDTO)iterator.next();
/* 400 */       op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 401 */       op.setValue("" + reg.getSubproceso());
/* 402 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 403 */       if (defecto.equals(reg.getSubproceso())) {
/* 404 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 405 */         escogida.setValue("on");
/* 406 */         op.setAttributeNode(escogida);
/*     */       } 
/* 408 */       combo.appendChild(op);
/*     */     } 
/* 410 */     arr.clear();
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
/*     */   private void comboUnidades(HttpPresentationComms comms, String proceso, String subProceso, String defecto) throws HttpPresentationException, KeywordValueException {
/* 424 */     HTMLSelectElement combo = this.pagHTML.getElementUnidadServicio();
/*     */     
/* 426 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 427 */     op.setValue("");
/* 428 */     op.appendChild(this.pagHTML.createTextNode(""));
/* 429 */     combo.appendChild(op);
/*     */     
/* 431 */     CalSubProcesosDAO rs = new CalSubProcesosDAO();
/*     */     
/* 433 */     if (!rs.getEstadoConexion()) {
/* 434 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 437 */     Collection arr = rs.cargarServicios(proceso, subProceso, "A");
/* 438 */     rs.close();
/*     */     
/* 440 */     Iterator iterator = arr.iterator();
/* 441 */     while (iterator.hasNext()) {
/* 442 */       CalSubProcesosDTO reg = (CalSubProcesosDTO)iterator.next();
/* 443 */       op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 444 */       op.setValue("" + reg.getServicio());
/* 445 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 446 */       if (defecto.equals(reg.getServicio())) {
/* 447 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 448 */         escogida.setValue("on");
/* 449 */         op.setAttributeNode(escogida);
/*     */       } 
/* 451 */       combo.appendChild(op);
/*     */     } 
/* 453 */     arr.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String generarScript() {
/* 463 */     CalSubProcesosDAO rs = new CalSubProcesosDAO();
/* 464 */     Collection arr = rs.cargarTodosActivos();
/* 465 */     rs.close();
/*     */     
/* 467 */     String script = "var matriz=new Array(" + arr.size() + ");";
/* 468 */     script = script + " var iCont=0;";
/* 469 */     int i = 0;
/*     */     
/* 471 */     Iterator iterator = arr.iterator();
/* 472 */     while (iterator.hasNext()) {
/* 473 */       CalSubProcesosDTO reg = (CalSubProcesosDTO)iterator.next();
/* 474 */       script = script + " matriz[iCont++]=new add_member('" + reg.getProceso() + "','" + reg.getSubproceso() + "','" + reg.getDescripcion() + "');";
/* 475 */       i++;
/*     */     } 
/* 477 */     arr.clear();
/* 478 */     return script;
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
/*     */   private void poblarComboArea(HTMLSelectElement combo, Collection arr, int defecto) {
/* 494 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 495 */     op.setValue("");
/* 496 */     op.appendChild(this.pagHTML.createTextNode(""));
/* 497 */     combo.appendChild(op);
/*     */     
/* 499 */     Iterator iterator = arr.iterator();
/* 500 */     while (iterator.hasNext()) {
/* 501 */       CalResponsablesDTO reg = (CalResponsablesDTO)iterator.next();
/*     */       
/* 503 */       op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 504 */       op.setValue("" + reg.getCodigo());
/* 505 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 506 */       if (defecto == reg.getCodigo()) {
/* 507 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 508 */         escogida.setValue("on");
/* 509 */         op.setAttributeNode(escogida);
/*     */       } 
/* 511 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalDocumentosListar.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */