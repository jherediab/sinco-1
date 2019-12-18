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
/*     */ import sinco.business.PoaProductosProcesoDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.PoaProductosProcesoDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.PoaProductosProceso;
/*     */ import sinco.presentation.PoaProductosProcesoHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PoaProductosProceso
/*     */   implements HttpPresentation
/*     */ {
/*     */   private PoaProductosProcesoHTML pagHTML;
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
/*  53 */     this.pagHTML = (PoaProductosProcesoHTML)comms.xmlcFactory.create(PoaProductosProcesoHTML.class);
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
/*  85 */     int codigoProductoProceso = 0;
/*     */     try {
/*  87 */       codigoProductoProceso = Integer.parseInt(comms.request.getParameter("codigoProductoProceso"));
/*     */     }
/*  89 */     catch (Exception e) {
/*  90 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoProductoProceso"));
/*     */     } 
/*     */     
/*  93 */     RespuestaBD rta = new RespuestaBD();
/*  94 */     if (_operacion.equals("E")) {
/*  95 */       PoaProductosProcesoDAO rs = new PoaProductosProcesoDAO();
/*  96 */       if (!rs.getEstadoConexion()) {
/*  97 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*  99 */       rta = rs.eliminarRegistro(codigoProductoProceso);
/* 100 */       if (!rta.isRta()) {
/* 101 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaProductosProceso&p1=" + rta.getMensaje()));
/*     */       }
/* 103 */       rs.close();
/* 104 */       String sPagina = "PoaProductosProceso.po?_operacion=X";
/* 105 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 107 */     String descripcion = comms.request.getParameter("descripcion");
/* 108 */     int area = 0;
/*     */     try {
/* 110 */       area = Integer.parseInt(comms.request.getParameter("area"));
/*     */     }
/* 112 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 115 */     String estado = comms.request.getParameter("estado");
/* 116 */     PoaProductosProcesoDAO rs = new PoaProductosProcesoDAO();
/* 117 */     if (!rs.getEstadoConexion()) {
/* 118 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 120 */     if (_operacion.equals("C")) {
/* 121 */       rta = rs.crearRegistro(codigoProductoProceso, descripcion, area, estado, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 127 */       codigoProductoProceso = rta.getSecuencia();
/*     */     } else {
/*     */       
/* 130 */       rta = rs.modificarRegistro(codigoProductoProceso, descripcion, area, estado, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 137 */     rs.close();
/* 138 */     if (!rta.isRta()) {
/* 139 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaProductosProceso&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 142 */     String sPagina = "PoaProductosProceso.po?_operacion=P&codigoProductoProceso=" + codigoProductoProceso + "";
/* 143 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 154 */     int codigoProductoProceso = 0;
/*     */     try {
/* 156 */       codigoProductoProceso = Integer.parseInt(comms.request.getParameter("codigoProductoProceso"));
/*     */     }
/* 158 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 161 */     PoaProductosProcesoDAO rs = new PoaProductosProcesoDAO();
/* 162 */     if (!rs.getEstadoConexion()) {
/* 163 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 165 */     PoaProductosProcesoDTO reg = rs.cargarRegistro(codigoProductoProceso);
/* 166 */     rs.close();
/* 167 */     if (reg != null) {
/* 168 */       this.pagHTML.getElementCodigoProductoProceso().setValue("" + reg.getCodigoProductoProceso());
/* 169 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 170 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 171 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 172 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 173 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 174 */       HTMLSelectElement combo = this.pagHTML.getElementArea();
/* 175 */       llenarCombo(combo, "UNIDADES_DEPENDENCIA", "CODIGO", "DESCRIPCION", "1=1", "" + reg.getArea(), true);
/*     */       
/* 177 */       combo = this.pagHTML.getElementEstado();
/* 178 */       comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), false);
/*     */ 
/*     */       
/* 181 */       this.pagHTML.getElementCodigoProductoProceso().setReadOnly(true);
/*     */     } 
/* 183 */     this.pagHTML.getElement_operacion().setValue("M");
/* 184 */     activarVista("nuevo");
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
/* 196 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 198 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 199 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 201 */     catch (Exception e) {}
/*     */     
/* 203 */     activarVista("nuevo");
/* 204 */     HTMLSelectElement combo = this.pagHTML.getElementArea();
/* 205 */     llenarCombo(combo, "UNIDADES_DEPENDENCIA", "CODIGO", "DESCRIPCION", "1=1", "", true);
/*     */     
/* 207 */     combo = this.pagHTML.getElementEstado();
/* 208 */     comboMultivalores(combo, "ESTADO_REGISTRO", "", false);
/*     */     
/* 210 */     this.pagHTML.getElementCodigoProductoProceso().setReadOnly(true);
/* 211 */     this.pagHTML.getElementCodigoProductoProceso().setValue("0");
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
/* 222 */     activarVista("consulta");
/* 223 */     String descripcion = comms.request.getParameter("descripcion");
/* 224 */     if (descripcion == null) {
/* 225 */       descripcion = "";
/*     */     }
/* 227 */     int area = 0;
/*     */     try {
/* 229 */       area = Integer.parseInt(comms.request.getParameter("area"));
/*     */     }
/* 231 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 234 */     String estado = comms.request.getParameter("estado");
/* 235 */     if (estado == null) {
/* 236 */       estado = "";
/*     */     }
/* 238 */     HTMLSelectElement combo = this.pagHTML.getElementFarea();
/* 239 */     llenarCombo(combo, "UNIDADES_DEPENDENCIA", "CODIGO", "DESCRIPCION", "1=1", "" + area, true);
/*     */     
/* 241 */     combo = this.pagHTML.getElementFestado();
/* 242 */     comboMultivalores(combo, "ESTADO_REGISTRO", "" + estado, true);
/*     */ 
/*     */     
/* 245 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 250 */     PoaProductosProcesoDAO rs = new PoaProductosProcesoDAO();
/* 251 */     if (!rs.getEstadoConexion()) {
/* 252 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 254 */     Collection<PoaProductosProcesoDTO> arr = rs.cargarTodos(descripcion, area, estado);
/*     */ 
/*     */ 
/*     */     
/* 258 */     rs.close();
/* 259 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 260 */     int cuantas = 0;
/* 261 */     Iterator<PoaProductosProcesoDTO> iterator = arr.iterator();
/* 262 */     while (iterator.hasNext()) {
/* 263 */       PoaProductosProcesoDTO reg = (PoaProductosProcesoDTO)iterator.next();
/* 264 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 265 */       eltr.appendChild(newtd("" + reg.getCodigoProductoProceso()));
/* 266 */       String url = "PoaProductosProceso.po?_operacion=V&codigoProductoProceso=" + reg.getCodigoProductoProceso() + "";
/* 267 */       eltr.appendChild(newtdhref("" + reg.getDescripcion(), url));
/* 268 */       eltr.appendChild(newtd("" + reg.getNombreArea()));
/* 269 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 270 */       hte.appendChild(eltr);
/* 271 */       cuantas++;
/*     */     } 
/* 273 */     arr.clear();
/* 274 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 287 */     int codigoProductoProceso = 0;
/*     */     try {
/* 289 */       codigoProductoProceso = Integer.parseInt(comms.request.getParameter("codigoProductoProceso"));
/*     */     }
/* 291 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 294 */     PoaProductosProcesoDAO rs = new PoaProductosProcesoDAO();
/* 295 */     if (!rs.getEstadoConexion()) {
/* 296 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 298 */     PoaProductosProcesoDTO reg = rs.cargarRegistro(codigoProductoProceso);
/* 299 */     rs.close();
/* 300 */     if (reg != null) {
/* 301 */       this.pagHTML.setTextCodigoProductoProcesoEd("" + reg.getCodigoProductoProceso());
/* 302 */       this.pagHTML.setTextDescripcionEd("" + reg.getDescripcion());
/* 303 */       this.pagHTML.setTextAreaEd("" + reg.getNombreArea());
/* 304 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/* 305 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 306 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 307 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 308 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 310 */       this.pagHTML.getElementCodigoProductoProcesoKey().setValue("" + reg.getCodigoProductoProceso());
/* 311 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 313 */     activarVista("editar");
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
/* 324 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 326 */     Varios oVarios = new Varios();
/* 327 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Poa_PoaProductosProcesoAct");
/* 328 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Poa_PoaProductosProcesoDel");
/* 329 */     if (!oPermisoAct) {
/* 330 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 331 */       elem.getParentNode().removeChild(elem);
/* 332 */       elem = this.pagHTML.getElementBtnGrabar();
/* 333 */       elem.getParentNode().removeChild(elem);
/* 334 */       elem = this.pagHTML.getElementBtnModificar();
/* 335 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 337 */     if (!oPermisoDel) {
/* 338 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 339 */       elem.getParentNode().removeChild(elem);
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
/* 350 */     if (!vista.equals("nuevo")) {
/* 351 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 352 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 354 */     if (!vista.equals("editar")) {
/* 355 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 356 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 358 */     if (!vista.equals("consulta")) {
/* 359 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 360 */       sel.getParentNode().removeChild(sel);
/* 361 */       sel = this.pagHTML.getElementDivResultados();
/* 362 */       sel.getParentNode().removeChild(sel);
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
/* 376 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 377 */     atrib.setValue(valor);
/* 378 */     return atrib;
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
/* 391 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 392 */     Element enlace = this.pagHTML.createElement("a");
/* 393 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 394 */     enlace.appendChild(hijo);
/* 395 */     Attr donde = this.pagHTML.createAttribute("href");
/* 396 */     donde.setValue(vinculo);
/* 397 */     enlace.setAttributeNode(donde);
/* 398 */     td.appendChild(enlace);
/* 399 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 400 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 410 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 411 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 412 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 413 */     return td;
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
/* 428 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 429 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 430 */     rs.close();
/* 431 */     if (dejarBlanco) {
/* 432 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 433 */       op.setValue("");
/* 434 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 435 */       combo.appendChild(op);
/*     */     } 
/* 437 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 438 */     while (iterator.hasNext()) {
/* 439 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 440 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 441 */       op.setValue("" + reg.getCodigo());
/* 442 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 443 */       if (defecto.equals(reg.getCodigo())) {
/* 444 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 445 */         escogida.setValue("on");
/* 446 */         op.setAttributeNode(escogida);
/*     */       } 
/* 448 */       combo.appendChild(op);
/*     */     } 
/* 450 */     arr.clear();
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
/* 471 */     if (dejarBlanco) {
/* 472 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 473 */       op.setValue("");
/* 474 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 475 */       combo.appendChild(op);
/*     */     } 
/* 477 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 478 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/* 479 */     rsTGen.close();
/* 480 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 481 */     while (iterator.hasNext()) {
/* 482 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 483 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 484 */       op.setValue("" + regGeneral.getCodigoS());
/* 485 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 486 */       if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
/* 487 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 488 */         escogida.setValue("on");
/* 489 */         op.setAttributeNode(escogida);
/*     */       } 
/* 491 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PoaProductosProceso.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */