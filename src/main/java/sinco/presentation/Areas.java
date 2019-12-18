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
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.Areas;
/*     */ import sinco.presentation.AreasHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Areas
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AreasHTML pagHTML;
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
/*  53 */     this.pagHTML = (AreasHTML)comms.xmlcFactory.create(AreasHTML.class);
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
/*  85 */     int codigo = 0;
/*     */     try {
/*  87 */       codigo = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/*  89 */     catch (Exception e) {
/*  90 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigo"));
/*     */     } 
/*     */     
/*  93 */     RespuestaBD rta = new RespuestaBD();
/*  94 */     if (_operacion.equals("E")) {
/*  95 */       AreasDAO rs = new AreasDAO();
/*  96 */       if (!rs.getEstadoConexion()) {
/*  97 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*  99 */       rta = rs.eliminarRegistro(codigo);
/* 100 */       if (!rta.isRta()) {
/* 101 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorAreas&p1=" + rta.getMensaje()));
/*     */       }
/* 103 */       rs.close();
/* 104 */       String sPagina = "Areas.po?_operacion=X";
/* 105 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 107 */     String descripcion = comms.request.getParameter("descripcion");
/* 108 */     int nivelSuperior = 0;
/*     */     try {
/* 110 */       nivelSuperior = Integer.parseInt(comms.request.getParameter("nivelSuperior"));
/*     */     }
/* 112 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 115 */     String estado = comms.request.getParameter("estado");
/* 116 */     String anidar = comms.request.getParameter("anidar");
/* 117 */     if (anidar == null) {
/* 118 */       anidar = "N";
/*     */     }
/* 120 */     String ubicacion = comms.request.getParameter("ubicacion");
/* 121 */     String incluirCalidad = comms.request.getParameter("incluirCalidad");
/* 122 */     if (incluirCalidad == null) {
/* 123 */       incluirCalidad = "N";
/*     */     }
/* 125 */     String permiteActas = comms.request.getParameter("permiteActas");
/* 126 */     if (permiteActas == null) {
/* 127 */       permiteActas = "N";
/*     */     }
/* 129 */     String modificaLogros = comms.request.getParameter("modificaLogros");
/* 130 */     if (modificaLogros == null) {
/* 131 */       modificaLogros = "N";
/*     */     }
/*     */     
/* 134 */     String liderAccionesMejora = comms.request.getParameter("liderAccionesMejora");
/* 135 */     if (liderAccionesMejora == null) {
/* 136 */       liderAccionesMejora = "N";
/*     */     }
/* 138 */     String tipoArea = comms.request.getParameter("tipoArea");
/*     */     
/* 140 */     String departamento = comms.request.getParameter("departamentoUbicacion");
/* 141 */     if (departamento == null) {
/* 142 */       departamento = "";
/*     */     }
/* 144 */     String municipio = comms.request.getParameter("municipioUbicacion");
/* 145 */     if (municipio == null) {
/* 146 */       municipio = "";
/*     */     }
/*     */     
/* 149 */     String nivelOrganigrama = comms.request.getParameter("nivelOrganigrama");
/* 150 */     String codigoExterno = comms.request.getParameter("codigoExterno");
/* 151 */     AreasDAO rs = new AreasDAO();
/* 152 */     if (!rs.getEstadoConexion()) {
/* 153 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 155 */     if (_operacion.equals("C")) {
/* 156 */       rta = rs.crearRegistro(codigo, descripcion, nivelSuperior, estado, anidar, ubicacion, incluirCalidad, modificaLogros, tipoArea, departamento, municipio, nivelOrganigrama, elUsuario);
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
/* 172 */       rta = rs.modificarRegistro(codigo, descripcion, nivelSuperior, estado, anidar, ubicacion, incluirCalidad, modificaLogros, tipoArea, departamento, municipio, nivelOrganigrama, elUsuario);
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
/* 187 */     rs.close();
/* 188 */     if (!rta.isRta()) {
/* 189 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorAreas&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 192 */     String sPagina = "Areas.po?_operacion=P&codigo=" + codigo + "";
/* 193 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 204 */     int codigo = 0;
/*     */     try {
/* 206 */       codigo = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 208 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 211 */     AreasDAO rs = new AreasDAO();
/* 212 */     if (!rs.getEstadoConexion()) {
/* 213 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 215 */     AreasDTO reg = rs.cargarRegistro(codigo);
/* 216 */     rs.close();
/* 217 */     if (reg != null) {
/* 218 */       this.pagHTML.getElementCodigo().setValue("" + reg.getCodigo());
/* 219 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 220 */       if (reg.getAnidar().equals("S")) {
/* 221 */         this.pagHTML.getElementAnidar().setChecked(true);
/*     */       }
/* 223 */       this.pagHTML.getElementUbicacion().setValue("" + reg.getUbicacion());
/* 224 */       if (reg.getIncluirCalidad().equals("S")) {
/* 225 */         this.pagHTML.getElementIncluirCalidad().setChecked(true);
/*     */       }
/* 227 */       if (reg.getModificaLogros().equals("S")) {
/* 228 */         this.pagHTML.getElementModificaLogros().setChecked(true);
/*     */       }
/* 230 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 231 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 232 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 233 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 234 */       HTMLSelectElement combo = this.pagHTML.getElementNivelSuperior();
/* 235 */       llenarCombo(combo, "UNIDADES_DEPENDENCIA", "CODIGO", "DESCRIPCION", "estado='A'", "" + reg.getNivelSuperior(), true);
/*     */       
/* 237 */       combo = this.pagHTML.getElementEstado();
/* 238 */       comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), true);
/*     */       
/* 240 */       combo = this.pagHTML.getElementTipoArea();
/* 241 */       comboMultivalores(combo, "TIPO_AREA", "" + reg.getTipoArea(), true);
/*     */       
/* 243 */       combo = this.pagHTML.getElementDepartamentoUbicacion();
/* 244 */       llenarCombo(combo, "par_departamento", "codigo_departamento", "nombre_departamento", "1=1", "" + reg.getDepartamentoUbicacion(), true);
/*     */       
/* 246 */       combo = this.pagHTML.getElementMunicipioUbicacion();
/* 247 */       llenarCombo(combo, "par_ciudad", "codigo_ciudad", "nombre_ciudad", "codigo_departamento='" + reg.getDepartamentoUbicacion() + "'", "" + reg.getMunicipioUbicacion(), true);
/*     */       
/* 249 */       combo = this.pagHTML.getElementNivelOrganigrama();
/* 250 */       comboMultivalores(combo, "NIVEL_ORGANIGRAMA", "" + reg.getNivelOrganigrama(), true);
/*     */ 
/*     */       
/* 253 */       this.pagHTML.getElementCodigo().setReadOnly(true);
/*     */     } 
/* 255 */     this.pagHTML.getElement_operacion().setValue("M");
/* 256 */     activarVista("nuevo");
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
/* 268 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 270 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 271 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 273 */     catch (Exception e) {}
/*     */     
/* 275 */     activarVista("nuevo");
/* 276 */     HTMLSelectElement combo = this.pagHTML.getElementNivelSuperior();
/* 277 */     llenarCombo(combo, "UNIDADES_DEPENDENCIA", "CODIGO", "DESCRIPCION", "estado='A'", "", true);
/*     */     
/* 279 */     combo = this.pagHTML.getElementEstado();
/* 280 */     comboMultivalores(combo, "ESTADO_REGISTRO", "A", true);
/*     */     
/* 282 */     combo = this.pagHTML.getElementTipoArea();
/* 283 */     comboMultivalores(combo, "TIPO_AREA", "", true);
/*     */     
/* 285 */     combo = this.pagHTML.getElementDepartamentoUbicacion();
/* 286 */     llenarCombo(combo, "par_departamento", "codigo_departamento", "nombre_departamento", "1=1", "", true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 291 */     combo = this.pagHTML.getElementNivelOrganigrama();
/* 292 */     comboMultivalores(combo, "NIVEL_ORGANIGRAMA", "", true);
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
/*     */   private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
/* 304 */     activarVista("consulta");
/* 305 */     int codigo = 0;
/*     */     try {
/* 307 */       codigo = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 309 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 312 */     String descripcion = comms.request.getParameter("descripcion");
/* 313 */     if (descripcion == null) {
/* 314 */       descripcion = "";
/*     */     }
/* 316 */     String estado = comms.request.getParameter("estado");
/* 317 */     if (estado == null) {
/* 318 */       estado = "";
/*     */     }
/* 320 */     HTMLSelectElement combo = this.pagHTML.getElementFestado();
/* 321 */     comboMultivalores(combo, "ESTADO_REGISTRO", "" + estado, true);
/*     */ 
/*     */     
/* 324 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 329 */     AreasDAO rs = new AreasDAO();
/* 330 */     if (!rs.getEstadoConexion()) {
/* 331 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 333 */     Collection<AreasDTO> arr = rs.cargarTodos(codigo, descripcion, estado);
/*     */ 
/*     */ 
/*     */     
/* 337 */     rs.close();
/* 338 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 339 */     int cuantas = 0;
/* 340 */     Iterator<AreasDTO> iterator = arr.iterator();
/* 341 */     while (iterator.hasNext()) {
/* 342 */       AreasDTO reg = (AreasDTO)iterator.next();
/* 343 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 344 */       eltr.appendChild(newtd("" + reg.getCodigo()));
/* 345 */       String url = "Areas.po?_operacion=V&codigo=" + reg.getCodigo() + "";
/* 346 */       eltr.appendChild(newtdhref("" + reg.getDescripcion(), url));
/* 347 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 348 */       hte.appendChild(eltr);
/* 349 */       cuantas++;
/*     */     } 
/* 351 */     arr.clear();
/* 352 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 365 */     int codigo = 0;
/*     */     try {
/* 367 */       codigo = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 369 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 372 */     AreasDAO rs = new AreasDAO();
/* 373 */     if (!rs.getEstadoConexion()) {
/* 374 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 376 */     AreasDTO reg = rs.cargarRegistro(codigo);
/* 377 */     rs.close();
/* 378 */     if (reg != null) {
/* 379 */       this.pagHTML.setTextCodigoEd("" + reg.getCodigo());
/* 380 */       this.pagHTML.setTextDescripcionEd("" + reg.getDescripcion());
/*     */ 
/*     */ 
/*     */       
/* 384 */       this.pagHTML.setTextNivelSuperiorEd("" + reg.getNombreNivelSuperior());
/* 385 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/* 386 */       this.pagHTML.setTextTipoAreaEd("" + reg.getNombreTipoArea());
/* 387 */       this.pagHTML.setTextNivelOrganigramaEd("" + reg.getNombreNivelOrganigrama());
/*     */ 
/*     */       
/* 390 */       this.pagHTML.setTextAnidarEd("" + reg.getAnidar());
/* 391 */       this.pagHTML.setTextUbicacionEd("" + reg.getUbicacion());
/* 392 */       this.pagHTML.setTextIncluirCalidadEd("" + reg.getIncluirCalidad());
/* 393 */       this.pagHTML.setTextModificaLogrosEd("" + reg.getModificaLogros());
/*     */       
/* 395 */       this.pagHTML.setTextMunicipioUbicacionEd("" + reg.getNombreMunicipioUbicacion());
/* 396 */       this.pagHTML.setTextDepartamentoUbicacionEd("" + reg.getNombredepartamentoUbicacion());
/*     */       
/* 398 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 399 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 400 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 401 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 403 */       this.pagHTML.getElementCodigoKey().setValue("" + reg.getCodigo());
/* 404 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 406 */     activarVista("editar");
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
/* 417 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 419 */     Varios oVarios = new Varios();
/* 420 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Adm_AreasAct");
/* 421 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Adm_AreasDel");
/* 422 */     if (!oPermisoAct) {
/* 423 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 424 */       elem.getParentNode().removeChild(elem);
/* 425 */       elem = this.pagHTML.getElementBtnGrabar();
/* 426 */       elem.getParentNode().removeChild(elem);
/* 427 */       elem = this.pagHTML.getElementBtnModificar();
/* 428 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 430 */     if (!oPermisoDel) {
/* 431 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 432 */       elem.getParentNode().removeChild(elem);
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
/* 443 */     if (!vista.equals("nuevo")) {
/* 444 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 445 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 447 */     if (!vista.equals("editar")) {
/* 448 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 449 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 451 */     if (!vista.equals("consulta")) {
/* 452 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 453 */       sel.getParentNode().removeChild(sel);
/* 454 */       sel = this.pagHTML.getElementDivResultados();
/* 455 */       sel.getParentNode().removeChild(sel);
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
/* 469 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 470 */     atrib.setValue(valor);
/* 471 */     return atrib;
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
/* 484 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 485 */     Element enlace = this.pagHTML.createElement("a");
/* 486 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 487 */     enlace.appendChild(hijo);
/* 488 */     Attr donde = this.pagHTML.createAttribute("href");
/* 489 */     donde.setValue(vinculo);
/* 490 */     enlace.setAttributeNode(donde);
/* 491 */     td.appendChild(enlace);
/* 492 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 493 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 503 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 504 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 505 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 506 */     return td;
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
/* 521 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 522 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 523 */     rs.close();
/* 524 */     if (dejarBlanco) {
/* 525 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 526 */       op.setValue("");
/* 527 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 528 */       combo.appendChild(op);
/*     */     } 
/* 530 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 531 */     while (iterator.hasNext()) {
/* 532 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 533 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 534 */       op.setValue("" + reg.getCodigo());
/* 535 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 536 */       if (defecto.equals(reg.getCodigo())) {
/* 537 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 538 */         escogida.setValue("on");
/* 539 */         op.setAttributeNode(escogida);
/*     */       } 
/* 541 */       combo.appendChild(op);
/*     */     } 
/* 543 */     arr.clear();
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
/* 564 */     if (dejarBlanco) {
/* 565 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 566 */       op.setValue("");
/* 567 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 568 */       combo.appendChild(op);
/*     */     } 
/* 570 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 571 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/* 572 */     rsTGen.close();
/* 573 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 574 */     while (iterator.hasNext()) {
/* 575 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 576 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 577 */       op.setValue("" + regGeneral.getCodigoS());
/* 578 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 579 */       if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
/* 580 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 581 */         escogida.setValue("on");
/* 582 */         op.setAttributeNode(escogida);
/*     */       } 
/* 584 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\Areas.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */