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
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.SisUsuarios;
/*     */ import sinco.presentation.SisUsuariosHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SisUsuarios
/*     */   implements HttpPresentation
/*     */ {
/*     */   private SisUsuariosHTML pagHTML;
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
/*  53 */     this.pagHTML = (SisUsuariosHTML)comms.xmlcFactory.create(SisUsuariosHTML.class);
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
/*  85 */     int codigoEmpleado = 0;
/*     */     try {
/*  87 */       codigoEmpleado = Integer.parseInt(comms.request.getParameter("codigoEmpleado"));
/*     */     }
/*  89 */     catch (Exception e) {
/*  90 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoEmpleado"));
/*     */     } 
/*     */     
/*  93 */     RespuestaBD rta = new RespuestaBD();
/*  94 */     if (_operacion.equals("E")) {
/*  95 */       SisUsuariosDAO ob = new SisUsuariosDAO();
/*  96 */       rta = ob.eliminarRegistro(codigoEmpleado);
/*  97 */       if (!rta.isRta()) {
/*  98 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSisUsuarios&p1=" + rta.getMensaje()));
/*     */       }
/* 100 */       String sPagina = "SisUsuarios.po?_operacion=X";
/* 101 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 103 */     long numeroIdentificacion = 0L;
/*     */     try {
/* 105 */       numeroIdentificacion = Long.parseLong(comms.request.getParameter("numeroIdentificacion"));
/*     */     }
/* 107 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 110 */     String tipoIdentificacion = comms.request.getParameter("tipoIdentificacion");
/* 111 */     String nombres = comms.request.getParameter("nombres");
/* 112 */     String apellidos = comms.request.getParameter("apellidos");
/* 113 */     String estado = comms.request.getParameter("estado");
/* 114 */     String password = comms.request.getParameter("password");
/* 115 */     String idcorreo = comms.request.getParameter("idcorreo");
/* 116 */     String email = comms.request.getParameter("email");
/* 117 */     String auditorCordinador = comms.request.getParameter("auditorCordinador");
/* 118 */     if (auditorCordinador == null) {
/* 119 */       auditorCordinador = "N";
/*     */     }
/* 121 */     String tipoAuditor = comms.request.getParameter("tipoAuditor");
/* 122 */     String usuarioSupervisor = comms.request.getParameter("usuarioSupervisor");
/* 123 */     String cargoGenerico = comms.request.getParameter("cargoGenerico");
/* 124 */     if (usuarioSupervisor == null) {
/* 125 */       usuarioSupervisor = "N";
/*     */     }
/* 127 */     SisUsuariosDAO ob = new SisUsuariosDAO();
/* 128 */     if (_operacion.equals("C")) {
/* 129 */       rta = ob.crearRegistro(codigoEmpleado, numeroIdentificacion, tipoIdentificacion, nombres, apellidos, estado, password, idcorreo, email, cargoGenerico, auditorCordinador, tipoAuditor, usuarioSupervisor, elUsuario);
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
/* 146 */       rta = ob.modificarRegistro(codigoEmpleado, numeroIdentificacion, tipoIdentificacion, nombres, apellidos, estado, password, idcorreo, email, cargoGenerico, auditorCordinador, tipoAuditor, usuarioSupervisor, elUsuario);
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
/* 162 */     if (!rta.isRta()) {
/* 163 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSisUsuarios&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 166 */     String sPagina = "SisUsuarios.po?_operacion=P&codigoEmpleado=" + codigoEmpleado + "";
/* 167 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 178 */     int codigoEmpleado = 0;
/*     */     try {
/* 180 */       codigoEmpleado = Integer.parseInt(comms.request.getParameter("codigoEmpleado"));
/*     */     }
/* 182 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 185 */     SisUsuariosDAO ob = new SisUsuariosDAO();
/* 186 */     SisUsuariosDTO reg = ob.cargarRegistro(codigoEmpleado);
/* 187 */     if (reg != null) {
/* 188 */       this.pagHTML.getElementCodigoEmpleado().setValue("" + reg.getCodigoEmpleado());
/* 189 */       this.pagHTML.getElementNumeroIdentificacion().setValue("" + reg.getNumeroIdentificacion());
/* 190 */       this.pagHTML.getElementNombres().setValue("" + reg.getNombres());
/* 191 */       this.pagHTML.getElementApellidos().setValue("" + reg.getApellidos());
/* 192 */       this.pagHTML.getElementPassword().setValue("" + reg.getPassword());
/* 193 */       this.pagHTML.getElementIdcorreo().setValue("" + reg.getIdcorreo());
/* 194 */       this.pagHTML.getElementEmail().setValue("" + reg.getEmail());
/*     */ 
/*     */       
/* 197 */       if (reg.getAuditorCordinador().equals("S")) {
/* 198 */         this.pagHTML.getElementAuditorCordinador().setChecked(true);
/*     */       }
/* 200 */       if (reg.getUsuarioSupervisor().equals("S")) {
/* 201 */         this.pagHTML.getElementUsuarioSupervisor().setChecked(true);
/*     */       }
/* 203 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 204 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 205 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 206 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 207 */       HTMLSelectElement combo = this.pagHTML.getElementTipoIdentificacion();
/* 208 */       comboMultivalores(combo, "tipo_documento", "" + reg.getTipoIdentificacion(), true);
/*     */       
/* 210 */       combo = this.pagHTML.getElementEstado();
/* 211 */       comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), true);
/*     */       
/* 213 */       combo = this.pagHTML.getElementTipoAuditor();
/* 214 */       comboMultivalores(combo, "AUT_TIPO_AUDITOR", "" + reg.getTipoAuditor(), true);
/*     */       
/* 216 */       combo = this.pagHTML.getElementCargoGenerico();
/* 217 */       llenarCombo(combo, "sis_cargos", "CODIGO", "DESCRIPCION", "1=1", "" + reg.getCargoGenerico(), true);
/*     */ 
/*     */       
/* 220 */       this.pagHTML.getElementCodigoEmpleado().setReadOnly(true);
/*     */     } 
/* 222 */     this.pagHTML.getElement_operacion().setValue("M");
/* 223 */     activarVista("nuevo");
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
/* 235 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 237 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 238 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 240 */     catch (Exception e) {}
/*     */     
/* 242 */     activarVista("nuevo");
/* 243 */     HTMLSelectElement combo = this.pagHTML.getElementTipoIdentificacion();
/* 244 */     comboMultivalores(combo, "tipo_documento", "", true);
/*     */     
/* 246 */     combo = this.pagHTML.getElementEstado();
/* 247 */     comboMultivalores(combo, "ESTADO_REGISTRO", "", true);
/*     */     
/* 249 */     combo = this.pagHTML.getElementTipoAuditor();
/* 250 */     comboMultivalores(combo, "AUT_TIPO_AUDITOR", "", true);
/*     */     
/* 252 */     combo = this.pagHTML.getElementCargoGenerico();
/* 253 */     llenarCombo(combo, "sis_cargos", "CODIGO", "DESCRIPCION", "1=1", "", true);
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
/* 265 */     activarVista("consulta");
/* 266 */     int codigoEmpleado = 0;
/*     */     try {
/* 268 */       codigoEmpleado = Integer.parseInt(comms.request.getParameter("codigoEmpleado"));
/*     */     }
/* 270 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 273 */     String nombres = comms.request.getParameter("nombres");
/* 274 */     String apellidos = comms.request.getParameter("apellidos");
/* 275 */     String estado = comms.request.getParameter("estado");
/* 276 */     if (estado == null) {
/* 277 */       estado = "";
/*     */     }
/*     */ 
/*     */     
/* 281 */     HTMLSelectElement combo = this.pagHTML.getElementFestado();
/* 282 */     comboMultivalores(combo, "ESTADO_REGISTRO", "" + estado, true);
/*     */ 
/*     */     
/* 285 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 290 */     SisUsuariosDAO ob = new SisUsuariosDAO();
/* 291 */     Collection<SisUsuariosDTO> arr = ob.cargarTodos(codigoEmpleado, nombres, apellidos, estado);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 296 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 297 */     int cuantas = 0;
/* 298 */     Iterator<SisUsuariosDTO> iterator = arr.iterator();
/* 299 */     while (iterator.hasNext()) {
/* 300 */       SisUsuariosDTO reg = (SisUsuariosDTO)iterator.next();
/* 301 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 302 */       eltr.appendChild(newtd("" + reg.getCodigoEmpleado()));
/* 303 */       String url = "SisUsuarios.po?_operacion=V&codigoEmpleado=" + reg.getCodigoEmpleado() + "";
/* 304 */       eltr.appendChild(newtdhref("" + reg.getNumeroIdentificacion(), url));
/* 305 */       eltr.appendChild(newtd("" + reg.getNombreTipoIdentificacion()));
/* 306 */       eltr.appendChild(newtd("" + reg.getNombres()));
/* 307 */       eltr.appendChild(newtd("" + reg.getApellidos()));
/* 308 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 309 */       eltr.appendChild(newtd("" + reg.getIdcorreo()));
/* 310 */       eltr.appendChild(newtd("" + reg.getNombreCargoGenerico()));
/* 311 */       hte.appendChild(eltr);
/* 312 */       cuantas++;
/*     */     } 
/* 314 */     arr.clear();
/* 315 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 328 */     int codigoEmpleado = 0;
/*     */     try {
/* 330 */       codigoEmpleado = Integer.parseInt(comms.request.getParameter("codigoEmpleado"));
/*     */     }
/* 332 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 335 */     SisUsuariosDAO ob = new SisUsuariosDAO();
/* 336 */     SisUsuariosDTO reg = ob.cargarRegistro(codigoEmpleado);
/* 337 */     if (reg != null) {
/* 338 */       this.pagHTML.setTextCodigoEmpleadoEd("" + reg.getCodigoEmpleado());
/* 339 */       this.pagHTML.setTextNumeroIdentificacionEd("" + reg.getNumeroIdentificacion());
/* 340 */       this.pagHTML.setTextTipoIdentificacionEd("" + reg.getNombreTipoIdentificacion());
/* 341 */       this.pagHTML.setTextNombresEd("" + reg.getNombres());
/* 342 */       this.pagHTML.setTextApellidosEd("" + reg.getApellidos());
/* 343 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/* 344 */       this.pagHTML.setTextPasswordEd("" + reg.getPassword());
/* 345 */       this.pagHTML.setTextIdcorreoEd("" + reg.getIdcorreo());
/* 346 */       this.pagHTML.setTextEmailEd("" + reg.getEmail());
/* 347 */       this.pagHTML.setTextCargoGenericoEd("" + reg.getNombreCargoGenerico());
/* 348 */       this.pagHTML.setTextAuditorCordinadorEd("" + reg.getAuditorCordinador());
/* 349 */       this.pagHTML.setTextTipoAuditorEd("" + reg.getNombreTipoAuditor());
/* 350 */       this.pagHTML.setTextUsuarioSupervisorEd("" + reg.getUsuarioSupervisor());
/* 351 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 352 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 353 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 354 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 356 */       this.pagHTML.getElementCodigoEmpleadoKey().setValue("" + reg.getCodigoEmpleado());
/* 357 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 359 */     activarVista("editar");
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
/* 370 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 372 */     Varios oVarios = new Varios();
/* 373 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "admSisUsuariosAct");
/* 374 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "admSisUsuariosDel");
/* 375 */     if (!oPermisoAct) {
/* 376 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 377 */       elem.getParentNode().removeChild(elem);
/* 378 */       elem = this.pagHTML.getElementBtnGrabar();
/* 379 */       elem.getParentNode().removeChild(elem);
/* 380 */       elem = this.pagHTML.getElementBtnModificar();
/* 381 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 383 */     if (!oPermisoDel) {
/* 384 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 385 */       elem.getParentNode().removeChild(elem);
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
/* 396 */     if (!vista.equals("nuevo")) {
/* 397 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 398 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 400 */     if (!vista.equals("editar")) {
/* 401 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 402 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 404 */     if (!vista.equals("consulta")) {
/* 405 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 406 */       sel.getParentNode().removeChild(sel);
/* 407 */       sel = this.pagHTML.getElementDivResultados();
/* 408 */       sel.getParentNode().removeChild(sel);
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
/* 422 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 423 */     atrib.setValue(valor);
/* 424 */     return atrib;
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
/* 437 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 438 */     Element enlace = this.pagHTML.createElement("a");
/* 439 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 440 */     enlace.appendChild(hijo);
/* 441 */     Attr donde = this.pagHTML.createAttribute("href");
/* 442 */     donde.setValue(vinculo);
/* 443 */     enlace.setAttributeNode(donde);
/* 444 */     td.appendChild(enlace);
/* 445 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 446 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 456 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 457 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 458 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 459 */     return td;
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
/* 482 */     if (dejarBlanco) {
/* 483 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 484 */       op.setValue("");
/* 485 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 486 */       combo.appendChild(op);
/*     */     } 
/* 488 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 489 */     Collection arr = rsTGen.cargarTodosArr(tabla, codigo, descripcion, condicion);
/* 490 */     rsTGen.close();
/* 491 */     Iterator iterator = arr.iterator();
/* 492 */     while (iterator.hasNext()) {
/* 493 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 494 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 495 */       op.setValue("" + regGeneral.getCodigo());
/* 496 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 497 */       if (defecto != null && defecto.equals(regGeneral.getCodigo())) {
/* 498 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 499 */         escogida.setValue("on");
/* 500 */         op.setAttributeNode(escogida);
/*     */       } 
/* 502 */       combo.appendChild(op);
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
/* 519 */     SisMultiValoresDAO ob = new SisMultiValoresDAO();
/* 520 */     Collection<SisMultiValoresDTO> arr = ob.cargarTabla(tabla);
/* 521 */     ob.close();
/* 522 */     if (dejarBlanco) {
/* 523 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 524 */       op.setValue("");
/* 525 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 526 */       combo.appendChild(op);
/*     */     } 
/* 528 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 529 */     while (iterator.hasNext()) {
/* 530 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 531 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 532 */       op.setValue("" + reg.getCodigo());
/* 533 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 534 */       if (defecto.equals(reg.getCodigo())) {
/* 535 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 536 */         escogida.setValue("on");
/* 537 */         op.setAttributeNode(escogida);
/*     */       } 
/* 539 */       combo.appendChild(op);
/*     */     } 
/* 541 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SisUsuarios.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */