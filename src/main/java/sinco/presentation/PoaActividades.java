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
/*     */ import sinco.business.PoaActividadesDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.PoaActividadesDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.PoaActividades;
/*     */ import sinco.presentation.PoaActividadesHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PoaActividades
/*     */   implements HttpPresentation
/*     */ {
/*     */   private PoaActividadesHTML pagHTML;
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
/*  53 */     this.pagHTML = (PoaActividadesHTML)comms.xmlcFactory.create(PoaActividadesHTML.class);
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
/*  85 */     int codigoActividad = 0;
/*     */     try {
/*  87 */       codigoActividad = Integer.parseInt(comms.request.getParameter("codigoActividad"));
/*     */     }
/*  89 */     catch (Exception e) {
/*  90 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoActividad"));
/*     */     } 
/*     */     
/*  93 */     RespuestaBD rta = new RespuestaBD();
/*  94 */     if (_operacion.equals("E")) {
/*  95 */       PoaActividadesDAO rs = new PoaActividadesDAO();
/*  96 */       if (!rs.getEstadoConexion()) {
/*  97 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*  99 */       rta = rs.eliminarRegistro(codigoActividad);
/* 100 */       if (!rta.isRta()) {
/* 101 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaActividades&p1=" + rta.getMensaje()));
/*     */       }
/* 103 */       rs.close();
/* 104 */       String sPagina = "PoaActividades.po?_operacion=X";
/* 105 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 107 */     String descripcion = comms.request.getParameter("descripcion");
/* 108 */     String tipoActividad = comms.request.getParameter("tipoActividad");
/* 109 */     int area = 0;
/*     */     try {
/* 111 */       area = Integer.parseInt(comms.request.getParameter("area"));
/*     */     }
/* 113 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 116 */     String estado = comms.request.getParameter("estado");
/* 117 */     PoaActividadesDAO rs = new PoaActividadesDAO();
/* 118 */     if (!rs.getEstadoConexion()) {
/* 119 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 121 */     if (_operacion.equals("C")) {
/* 122 */       rta = rs.crearRegistro(codigoActividad, descripcion, tipoActividad, area, estado, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 129 */       codigoActividad = rta.getSecuencia();
/*     */     } else {
/*     */       
/* 132 */       rta = rs.modificarRegistro(codigoActividad, descripcion, tipoActividad, area, estado, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 140 */     rs.close();
/* 141 */     if (!rta.isRta()) {
/* 142 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaActividades&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 145 */     String sPagina = "PoaActividades.po?_operacion=P&codigoActividad=" + codigoActividad + "";
/* 146 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 157 */     int codigoActividad = 0;
/*     */     try {
/* 159 */       codigoActividad = Integer.parseInt(comms.request.getParameter("codigoActividad"));
/*     */     }
/* 161 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 164 */     PoaActividadesDAO rs = new PoaActividadesDAO();
/* 165 */     if (!rs.getEstadoConexion()) {
/* 166 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 168 */     PoaActividadesDTO reg = rs.cargarRegistro(codigoActividad);
/* 169 */     rs.close();
/* 170 */     if (reg != null) {
/* 171 */       this.pagHTML.getElementCodigoActividad().setValue("" + reg.getCodigoActividad());
/* 172 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 173 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 174 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 175 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 176 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 177 */       HTMLSelectElement combo = this.pagHTML.getElementTipoActividad();
/* 178 */       comboMultivalores(combo, "POA_TIPOS_ACTIVIDAD", "" + reg.getTipoActividad(), true);
/*     */       
/* 180 */       combo = this.pagHTML.getElementArea();
/* 181 */       llenarCombo(combo, "UNIDADES_DEPENDENCIA", "CODIGO", "DESCRIPCION", "1=1", "" + reg.getArea(), true);
/*     */       
/* 183 */       combo = this.pagHTML.getElementEstado();
/* 184 */       comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), false);
/*     */ 
/*     */       
/* 187 */       this.pagHTML.getElementCodigoActividad().setReadOnly(true);
/*     */     } 
/* 189 */     this.pagHTML.getElement_operacion().setValue("M");
/* 190 */     activarVista("nuevo");
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
/* 202 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 204 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 205 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 207 */     catch (Exception e) {}
/*     */     
/* 209 */     activarVista("nuevo");
/* 210 */     HTMLSelectElement combo = this.pagHTML.getElementTipoActividad();
/* 211 */     comboMultivalores(combo, "POA_TIPOS_ACTIVIDAD", "", true);
/*     */     
/* 213 */     combo = this.pagHTML.getElementArea();
/* 214 */     llenarCombo(combo, "UNIDADES_DEPENDENCIA", "CODIGO", "DESCRIPCION", "1=1", "", true);
/*     */     
/* 216 */     combo = this.pagHTML.getElementEstado();
/* 217 */     comboMultivalores(combo, "ESTADO_REGISTRO", "", false);
/*     */     
/* 219 */     this.pagHTML.getElementCodigoActividad().setReadOnly(true);
/* 220 */     this.pagHTML.getElementCodigoActividad().setValue("0");
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
/* 231 */     activarVista("consulta");
/* 232 */     String descripcion = comms.request.getParameter("descripcion");
/* 233 */     if (descripcion == null) {
/* 234 */       descripcion = "";
/*     */     }
/* 236 */     String tipoActividad = comms.request.getParameter("tipoActividad");
/* 237 */     if (tipoActividad == null) {
/* 238 */       tipoActividad = "";
/*     */     }
/* 240 */     int area = 0;
/*     */     try {
/* 242 */       area = Integer.parseInt(comms.request.getParameter("area"));
/*     */     }
/* 244 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 247 */     String estado = comms.request.getParameter("estado");
/* 248 */     if (estado == null) {
/* 249 */       estado = "";
/*     */     }
/* 251 */     HTMLSelectElement combo = this.pagHTML.getElementFtipoActividad();
/* 252 */     comboMultivalores(combo, "POA_TIPOS_ACTIVIDAD", "" + tipoActividad, true);
/*     */ 
/*     */     
/* 255 */     combo = this.pagHTML.getElementFarea();
/* 256 */     llenarCombo(combo, "UNIDADES_DEPENDENCIA", "CODIGO", "DESCRIPCION", "1=1", "" + area, true);
/*     */     
/* 258 */     combo = this.pagHTML.getElementFestado();
/* 259 */     comboMultivalores(combo, "ESTADO_REGISTRO", "" + estado, true);
/*     */ 
/*     */     
/* 262 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 267 */     PoaActividadesDAO rs = new PoaActividadesDAO();
/* 268 */     if (!rs.getEstadoConexion()) {
/* 269 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 271 */     Collection<PoaActividadesDTO> arr = rs.cargarTodos(descripcion, tipoActividad, area, estado);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 276 */     rs.close();
/* 277 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 278 */     int cuantas = 0;
/* 279 */     Iterator<PoaActividadesDTO> iterator = arr.iterator();
/* 280 */     while (iterator.hasNext()) {
/* 281 */       PoaActividadesDTO reg = (PoaActividadesDTO)iterator.next();
/* 282 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 283 */       eltr.appendChild(newtd("" + reg.getCodigoActividad()));
/* 284 */       String url = "PoaActividades.po?_operacion=V&codigoActividad=" + reg.getCodigoActividad() + "";
/* 285 */       eltr.appendChild(newtdhref("" + reg.getDescripcion(), url));
/* 286 */       eltr.appendChild(newtd("" + reg.getNombreTipoActividad()));
/* 287 */       eltr.appendChild(newtd("" + reg.getNombreArea()));
/* 288 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 289 */       hte.appendChild(eltr);
/* 290 */       cuantas++;
/*     */     } 
/* 292 */     arr.clear();
/* 293 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 306 */     int codigoActividad = 0;
/*     */     try {
/* 308 */       codigoActividad = Integer.parseInt(comms.request.getParameter("codigoActividad"));
/*     */     }
/* 310 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 313 */     PoaActividadesDAO rs = new PoaActividadesDAO();
/* 314 */     if (!rs.getEstadoConexion()) {
/* 315 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 317 */     PoaActividadesDTO reg = rs.cargarRegistro(codigoActividad);
/* 318 */     rs.close();
/* 319 */     if (reg != null) {
/* 320 */       this.pagHTML.setTextCodigoActividadEd("" + reg.getCodigoActividad());
/* 321 */       this.pagHTML.setTextDescripcionEd("" + reg.getDescripcion());
/* 322 */       this.pagHTML.setTextTipoActividadEd("" + reg.getNombreTipoActividad());
/* 323 */       this.pagHTML.setTextAreaEd("" + reg.getNombreArea());
/* 324 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/* 325 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 326 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 327 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 328 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 330 */       this.pagHTML.getElementCodigoActividadKey().setValue("" + reg.getCodigoActividad());
/* 331 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 333 */     activarVista("editar");
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
/* 344 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 346 */     Varios oVarios = new Varios();
/* 347 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Poa_PoaActividadesAct");
/* 348 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Poa_PoaActividadesDel");
/* 349 */     if (!oPermisoAct) {
/* 350 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 351 */       elem.getParentNode().removeChild(elem);
/* 352 */       elem = this.pagHTML.getElementBtnGrabar();
/* 353 */       elem.getParentNode().removeChild(elem);
/* 354 */       elem = this.pagHTML.getElementBtnModificar();
/* 355 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 357 */     if (!oPermisoDel) {
/* 358 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 359 */       elem.getParentNode().removeChild(elem);
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
/* 370 */     if (!vista.equals("nuevo")) {
/* 371 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 372 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 374 */     if (!vista.equals("editar")) {
/* 375 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 376 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 378 */     if (!vista.equals("consulta")) {
/* 379 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 380 */       sel.getParentNode().removeChild(sel);
/* 381 */       sel = this.pagHTML.getElementDivResultados();
/* 382 */       sel.getParentNode().removeChild(sel);
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
/* 396 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 397 */     atrib.setValue(valor);
/* 398 */     return atrib;
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
/* 411 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 412 */     Element enlace = this.pagHTML.createElement("a");
/* 413 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 414 */     enlace.appendChild(hijo);
/* 415 */     Attr donde = this.pagHTML.createAttribute("href");
/* 416 */     donde.setValue(vinculo);
/* 417 */     enlace.setAttributeNode(donde);
/* 418 */     td.appendChild(enlace);
/* 419 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 420 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 430 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 431 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 432 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 433 */     return td;
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
/* 448 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 449 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 450 */     rs.close();
/* 451 */     if (dejarBlanco) {
/* 452 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 453 */       op.setValue("");
/* 454 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 455 */       combo.appendChild(op);
/*     */     } 
/* 457 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 458 */     while (iterator.hasNext()) {
/* 459 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 460 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 461 */       op.setValue("" + reg.getCodigo());
/* 462 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 463 */       if (defecto.equals(reg.getCodigo())) {
/* 464 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 465 */         escogida.setValue("on");
/* 466 */         op.setAttributeNode(escogida);
/*     */       } 
/* 468 */       combo.appendChild(op);
/*     */     } 
/* 470 */     arr.clear();
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
/* 491 */     if (dejarBlanco) {
/* 492 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 493 */       op.setValue("");
/* 494 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 495 */       combo.appendChild(op);
/*     */     } 
/* 497 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 498 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/* 499 */     rsTGen.close();
/* 500 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 501 */     while (iterator.hasNext()) {
/* 502 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 503 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 504 */       op.setValue("" + regGeneral.getCodigoS());
/* 505 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 506 */       if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
/* 507 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 508 */         escogida.setValue("on");
/* 509 */         op.setAttributeNode(escogida);
/*     */       } 
/* 511 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PoaActividades.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */