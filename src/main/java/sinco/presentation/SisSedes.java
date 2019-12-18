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
/*     */ import sinco.business.SisEntidadDTO;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.SisSedesDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.SisEntidadDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.SisSedesDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.SisSedes;
/*     */ import sinco.presentation.SisSedesHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SisSedes
/*     */   implements HttpPresentation
/*     */ {
/*     */   private SisSedesHTML pagHTML;
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
/*  55 */     this.pagHTML = (SisSedesHTML)comms.xmlcFactory.create(SisSedesHTML.class);
/*  56 */     permisos(comms);
/*     */ 
/*     */ 
/*     */     
/*  60 */     long nitEntidad = 0L;
/*     */     try {
/*  62 */       nitEntidad = Long.parseLong(comms.request.getParameter("nitEntidad"));
/*     */     }
/*  64 */     catch (Exception e) {
/*  65 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoEntidad"));
/*     */     } 
/*     */     
/*  68 */     this.pagHTML.getElementNitEntidadHidden().setValue("" + nitEntidad);
/*     */     
/*  70 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  71 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  74 */     if (_operacion.equals("P")) {
/*  75 */       editar(comms);
/*     */     }
/*  77 */     else if (_operacion.equals("Nuevo")) {
/*  78 */       nuevo(comms);
/*     */     } 
/*     */     
/*  81 */     if (_operacion.equals("V")) {
/*  82 */       verRegistro(comms);
/*     */     }
/*  84 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  85 */     comms.response.writeDOM(this.pagHTML);
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
/*  97 */     String _operacion = comms.request.getParameter("_operacion");
/*  98 */     String elUsuario = "" + comms.session.getUser().getName();
/*  99 */     int idSede = 0;
/*     */     try {
/* 101 */       idSede = Integer.parseInt(comms.request.getParameter("idSede"));
/*     */     }
/* 103 */     catch (Exception e) {
/* 104 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idSede"));
/*     */     } 
/*     */ 
/*     */     
/* 108 */     long nitEntidad = 0L;
/*     */     try {
/* 110 */       nitEntidad = Long.parseLong(comms.request.getParameter("nitEntidad"));
/*     */     }
/* 112 */     catch (Exception e) {
/* 113 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoEntidad"));
/*     */     } 
/*     */     
/* 116 */     RespuestaBD rta = new RespuestaBD();
/* 117 */     if (_operacion.equals("E")) {
/* 118 */       SisSedesDAO ob = new SisSedesDAO();
/* 119 */       rta = ob.eliminarRegistro(idSede, nitEntidad);
/*     */       
/* 121 */       if (!rta.isRta()) {
/* 122 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSisSedes&p1=" + rta.getMensaje()));
/*     */       }
/* 124 */       String sPagina = "SisSedes.po?_operacion=L&nitEntidad=" + nitEntidad + "";
/* 125 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/*     */     
/* 128 */     String nombreSede = comms.request.getParameter("nombreSede");
/* 129 */     String direccion = comms.request.getParameter("direccion");
/* 130 */     String departamento = comms.request.getParameter("departamento");
/* 131 */     String municipio = comms.request.getParameter("municipio");
/* 132 */     String telefono = comms.request.getParameter("telefono");
/* 133 */     SisSedesDAO ob = new SisSedesDAO();
/* 134 */     if (_operacion.equals("C")) {
/* 135 */       rta = ob.crearRegistro(idSede, nitEntidad, nombreSede, direccion, departamento, municipio, telefono, elUsuario);
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
/* 147 */       rta = ob.modificarRegistro(idSede, nitEntidad, nombreSede, direccion, departamento, municipio, telefono, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 157 */     if (!rta.isRta()) {
/* 158 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSisSedes&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 161 */     String sPagina = "SisSedes.po?_operacion=P&idSede=" + idSede + "&nitEntidad=" + nitEntidad + "";
/* 162 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 173 */     int idSede = 0;
/*     */     try {
/* 175 */       idSede = Integer.parseInt(comms.request.getParameter("idSede"));
/*     */     }
/* 177 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 180 */     long nitEntidad = 0L;
/*     */     try {
/* 182 */       nitEntidad = Long.parseLong(comms.request.getParameter("nitEntidad"));
/*     */     }
/* 184 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 187 */     SisEntidadDAO ent = new SisEntidadDAO();
/* 188 */     SisEntidadDTO regEnt = ent.cargarRegistro(nitEntidad);
/* 189 */     this.pagHTML.setTextSedesEntidad(" " + regEnt.getNombre());
/*     */     
/* 191 */     SisSedesDAO ob = new SisSedesDAO();
/* 192 */     SisSedesDTO reg = ob.cargarRegistro(idSede, nitEntidad);
/* 193 */     if (reg != null) {
/* 194 */       this.pagHTML.getElementIdSede().setValue("" + reg.getIdSede());
/* 195 */       this.pagHTML.getElementNombreSede().setValue("" + reg.getNombreSede());
/* 196 */       this.pagHTML.getElementDireccion().setValue("" + reg.getDireccion());
/* 197 */       this.pagHTML.getElementTelefono().setValue("" + reg.getTelefono());
/* 198 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 199 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 200 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 201 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/*     */ 
/*     */       
/* 204 */       HTMLSelectElement combo = this.pagHTML.getElementDepartamento();
/* 205 */       llenarCombo(combo, "par_departamento", "codigo_departamento", "nombre_departamento", "1=1", "" + reg.getDepartamento(), true);
/*     */       
/* 207 */       if (reg.getDepartamento().length() > 0) {
/* 208 */         combo = this.pagHTML.getElementMunicipio();
/* 209 */         llenarCombo(combo, "par_ciudad", "codigo_ciudad", "nombre_ciudad", "codigo_departamento='" + reg.getDepartamento() + "'", "" + reg.getMunicipio(), true);
/*     */       } 
/*     */       
/* 212 */       this.pagHTML.getElementIdSede().setReadOnly(true);
/*     */     } 
/* 214 */     this.pagHTML.getElement_operacion().setValue("M");
/* 215 */     activarVista("nuevo");
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
/* 227 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 229 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 230 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 232 */     catch (Exception e) {}
/*     */     
/* 234 */     long nitEntidad = 0L;
/*     */     try {
/* 236 */       nitEntidad = Long.parseLong(comms.request.getParameter("nitEntidad"));
/*     */     }
/* 238 */     catch (Exception e) {}
/*     */     
/* 240 */     SisEntidadDAO ent = new SisEntidadDAO();
/* 241 */     SisEntidadDTO regEnt = ent.cargarRegistro(nitEntidad);
/* 242 */     this.pagHTML.setTextSedesEntidad(" " + regEnt.getNombre());
/* 243 */     activarVista("nuevo");
/* 244 */     HTMLSelectElement combo = this.pagHTML.getElementDepartamento();
/* 245 */     llenarCombo(combo, "par_departamento", "codigo_departamento", "nombre_departamento", "1=1", "", true);
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
/*     */   private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
/* 258 */     activarVista("consulta");
/* 259 */     long nitEntidad = 0L;
/*     */     try {
/* 261 */       nitEntidad = Long.parseLong(comms.request.getParameter("nitEntidad"));
/*     */     }
/* 263 */     catch (Exception e) {}
/*     */     
/* 265 */     String nombreSede = comms.request.getParameter("nombreSedeConsulta");
/* 266 */     if (nombreSede == null) {
/* 267 */       nombreSede = "";
/*     */     }
/*     */     
/* 270 */     SisEntidadDAO ent = new SisEntidadDAO();
/* 271 */     SisEntidadDTO regEnt = ent.cargarRegistro(nitEntidad);
/* 272 */     this.pagHTML.setTextSedesEntidad(" " + regEnt.getNombre());
/* 273 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 279 */     SisSedesDAO ob = new SisSedesDAO();
/* 280 */     Collection<SisSedesDTO> arr = ob.cargarTodos(nitEntidad, nombreSede);
/*     */     
/* 282 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 283 */     int cuantas = 0;
/* 284 */     Iterator<SisSedesDTO> iterator = arr.iterator();
/* 285 */     while (iterator.hasNext()) {
/* 286 */       SisSedesDTO reg = (SisSedesDTO)iterator.next();
/* 287 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 288 */       String url = "SisSedes.po?_operacion=V&idSede=" + reg.getIdSede() + "&nitEntidad=" + reg.getNitEntidad() + "";
/*     */       
/* 290 */       eltr.appendChild(newtdhref("" + reg.getIdSede(), url));
/* 291 */       eltr.appendChild(newtdhref("" + reg.getNombreSede(), url));
/* 292 */       eltr.appendChild(newtd("" + reg.getDireccion()));
/* 293 */       eltr.appendChild(newtd("" + reg.getNombreDepartamento()));
/* 294 */       eltr.appendChild(newtd("" + reg.getNombreMunicipio()));
/* 295 */       eltr.appendChild(newtd("" + reg.getTelefono()));
/* 296 */       hte.appendChild(eltr);
/* 297 */       cuantas++;
/*     */     } 
/* 299 */     arr.clear();
/* 300 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 313 */     int idSede = 0;
/*     */     try {
/* 315 */       idSede = Integer.parseInt(comms.request.getParameter("idSede"));
/*     */     }
/* 317 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 320 */     long nitEntidad = 0L;
/*     */     try {
/* 322 */       nitEntidad = Long.parseLong(comms.request.getParameter("nitEntidad"));
/*     */     }
/* 324 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 327 */     SisEntidadDAO ent = new SisEntidadDAO();
/* 328 */     SisEntidadDTO regEnt = ent.cargarRegistro(nitEntidad);
/* 329 */     this.pagHTML.setTextSedesEntidad(" " + regEnt.getNombre());
/*     */     
/* 331 */     SisSedesDAO ob = new SisSedesDAO();
/* 332 */     SisSedesDTO reg = ob.cargarRegistro(idSede, nitEntidad);
/* 333 */     if (reg != null) {
/* 334 */       this.pagHTML.setTextIdSedeEd("" + reg.getIdSede());
/* 335 */       this.pagHTML.setTextNitEntidadEd("" + reg.getNombreNitEntidad());
/* 336 */       this.pagHTML.setTextNombreSedeEd("" + reg.getNombreSede());
/* 337 */       this.pagHTML.setTextDireccionEd("" + reg.getDireccion());
/* 338 */       this.pagHTML.setTextDepartamentoEd("" + reg.getNombreDepartamento());
/* 339 */       this.pagHTML.setTextMunicipioEd("" + reg.getNombreMunicipio());
/* 340 */       this.pagHTML.setTextTelefonoEd("" + reg.getTelefono());
/* 341 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 342 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 343 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/* 344 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 345 */       this.pagHTML.getElementNitEntidadHidden().setValue("" + reg.getNitEntidad());
/* 346 */       this.pagHTML.getElementIdSedeKey().setValue("" + reg.getIdSede());
/* 347 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 349 */     activarVista("editar");
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
/* 360 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 362 */     Varios oVarios = new Varios();
/* 363 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "ecoSisSedesAct");
/* 364 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "ecoSisSedesDel");
/* 365 */     if (!oPermisoAct) {
/* 366 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 367 */       elem.getParentNode().removeChild(elem);
/* 368 */       elem = this.pagHTML.getElementBtnGrabar();
/* 369 */       elem.getParentNode().removeChild(elem);
/* 370 */       elem = this.pagHTML.getElementBtnModificar();
/* 371 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 373 */     if (!oPermisoDel) {
/* 374 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 375 */       elem.getParentNode().removeChild(elem);
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
/* 386 */     if (!vista.equals("nuevo")) {
/* 387 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 388 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 390 */     if (!vista.equals("editar")) {
/* 391 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 392 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 394 */     if (!vista.equals("consulta")) {
/* 395 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 396 */       sel.getParentNode().removeChild(sel);
/* 397 */       sel = this.pagHTML.getElementDivResultados();
/* 398 */       sel.getParentNode().removeChild(sel);
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
/* 412 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 413 */     atrib.setValue(valor);
/* 414 */     return atrib;
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
/* 427 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 428 */     Element enlace = this.pagHTML.createElement("a");
/* 429 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 430 */     enlace.appendChild(hijo);
/* 431 */     Attr donde = this.pagHTML.createAttribute("href");
/* 432 */     donde.setValue(vinculo);
/* 433 */     enlace.setAttributeNode(donde);
/* 434 */     td.appendChild(enlace);
/* 435 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 436 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 446 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 447 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 448 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 449 */     return td;
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
/* 464 */     SisMultiValoresDAO ob = new SisMultiValoresDAO();
/* 465 */     Collection<SisMultiValoresDTO> arr = ob.cargarTabla(tabla);
/* 466 */     ob.close();
/* 467 */     if (dejarBlanco) {
/* 468 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 469 */       op.setValue("");
/* 470 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 471 */       combo.appendChild(op);
/*     */     } 
/* 473 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 474 */     while (iterator.hasNext()) {
/* 475 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 476 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 477 */       op.setValue("" + reg.getCodigo());
/* 478 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 479 */       if (defecto.equals(reg.getCodigo())) {
/* 480 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 481 */         escogida.setValue("on");
/* 482 */         op.setAttributeNode(escogida);
/*     */       } 
/* 484 */       combo.appendChild(op);
/*     */     } 
/* 486 */     arr.clear();
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
/* 507 */     if (dejarBlanco) {
/* 508 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 509 */       op.setValue("");
/* 510 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 511 */       combo.appendChild(op);
/*     */     } 
/* 513 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 514 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/* 515 */     rsTGen.close();
/* 516 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 517 */     while (iterator.hasNext()) {
/* 518 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 519 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 520 */       op.setValue("" + regGeneral.getCodigoS());
/* 521 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 522 */       if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
/* 523 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 524 */         escogida.setValue("on");
/* 525 */         op.setAttributeNode(escogida);
/*     */       } 
/* 527 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SisSedes.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */