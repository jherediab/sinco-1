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
/*     */ import sinco.business.SisAplicacionesDTO;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.data.SisAplicacionesDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.SisAplicaciones;
/*     */ import sinco.presentation.SisAplicacionesHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SisAplicaciones
/*     */   implements HttpPresentation
/*     */ {
/*     */   private SisAplicacionesHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  37 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  38 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  41 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  42 */     String _operacion = comms.request.getParameter("_operacion");
/*  43 */     if (_operacion == null || _operacion.length() == 0) {
/*  44 */       _operacion = "X";
/*     */     }
/*     */     
/*  47 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  48 */       creacion(comms);
/*     */     }
/*     */     
/*  51 */     this.pagHTML = (SisAplicacionesHTML)comms.xmlcFactory.create(SisAplicacionesHTML.class);
/*  52 */     permisos(comms);
/*     */     
/*  54 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  55 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  58 */     if (_operacion.equals("P")) {
/*  59 */       editar(comms);
/*     */     }
/*  61 */     else if (_operacion.equals("Nuevo")) {
/*  62 */       nuevo(comms);
/*     */     } 
/*     */     
/*  65 */     if (_operacion.equals("V")) {
/*  66 */       verRegistro(comms);
/*     */     }
/*  68 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  69 */     comms.response.writeDOM(this.pagHTML);
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
/*  81 */     String _operacion = comms.request.getParameter("_operacion");
/*  82 */     String elUsuario = "" + comms.session.getUser().getName();
/*  83 */     String aplicacion = comms.request.getParameter("aplicacion");
/*  84 */     if (aplicacion == null) {
/*  85 */       aplicacion = "";
/*     */     }
/*  87 */     RespuestaBD rta = new RespuestaBD();
/*  88 */     if (_operacion.equals("E")) {
/*  89 */       SisAplicacionesDAO ob = new SisAplicacionesDAO();
/*  90 */       rta = ob.eliminarRegistro(aplicacion);
/*  91 */       if (!rta.isRta()) {
/*  92 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSisAplicaciones&p1=" + rta.getMensaje()));
/*     */       }
/*  94 */       String sPagina = "SisAplicaciones.po?_operacion=X";
/*  95 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/*  97 */     String descripcion = comms.request.getParameter("descripcion");
/*  98 */     String modulo = comms.request.getParameter("modulo");
/*  99 */     int ordenAplicacion = 0;
/*     */     try {
/* 101 */       ordenAplicacion = Integer.parseInt(comms.request.getParameter("ordenAplicacion"));
/*     */     }
/* 103 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 106 */     String linkAplicacion = comms.request.getParameter("linkAplicacion");
/* 107 */     String tipoAplicacion = comms.request.getParameter("tipoAplicacion");
/* 108 */     String menuAplicacion = comms.request.getParameter("menuAplicacion");
/* 109 */     int ancho = 0;
/*     */     try {
/* 111 */       ancho = Integer.parseInt(comms.request.getParameter("ancho"));
/*     */     }
/* 113 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 116 */     int alto = 0;
/*     */     try {
/* 118 */       alto = Integer.parseInt(comms.request.getParameter("alto"));
/*     */     }
/* 120 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 123 */     String entorno = comms.request.getParameter("entorno");
/* 124 */     SisAplicacionesDAO ob = new SisAplicacionesDAO();
/* 125 */     if (_operacion.equals("C")) {
/* 126 */       rta = ob.crearRegistro(aplicacion, descripcion, modulo, ordenAplicacion, linkAplicacion, tipoAplicacion, menuAplicacion, ancho, alto, entorno, elUsuario);
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
/* 140 */       rta = ob.modificarRegistro(aplicacion, descripcion, modulo, ordenAplicacion, linkAplicacion, tipoAplicacion, menuAplicacion, ancho, alto, entorno, elUsuario);
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
/* 153 */     if (!rta.isRta()) {
/* 154 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSisAplicaciones&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 157 */     String sPagina = "SisAplicaciones.po?_operacion=P&aplicacion=" + aplicacion + "";
/* 158 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 169 */     String aplicacion = comms.request.getParameter("aplicacion");
/* 170 */     SisAplicacionesDAO ob = new SisAplicacionesDAO();
/* 171 */     SisAplicacionesDTO reg = ob.cargarRegistro(aplicacion);
/* 172 */     if (reg != null) {
/* 173 */       this.pagHTML.getElementAplicacion().setValue("" + reg.getAplicacion());
/* 174 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 175 */       this.pagHTML.getElementModulo().setValue("" + reg.getModulo());
/* 176 */       this.pagHTML.getElementOrdenAplicacion().setValue("" + reg.getOrdenAplicacion());
/* 177 */       this.pagHTML.getElementLinkAplicacion().setValue("" + reg.getLinkAplicacion());
/* 178 */       this.pagHTML.getElementMenuAplicacion().setValue("" + reg.getMenuAplicacion());
/* 179 */       this.pagHTML.getElementAncho().setValue("" + reg.getAncho());
/* 180 */       this.pagHTML.getElementAlto().setValue("" + reg.getAlto());
/* 181 */       this.pagHTML.getElementEntorno().setValue("" + reg.getEntorno());
/* 182 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 183 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 184 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 185 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 186 */       HTMLSelectElement combo = this.pagHTML.getElementTipoAplicacion();
/* 187 */       comboMultivalores(combo, "tipo_aplicacion", "" + reg.getTipoAplicacion(), true);
/*     */ 
/*     */       
/* 190 */       this.pagHTML.getElementAplicacion().setReadOnly(true);
/*     */     } 
/* 192 */     this.pagHTML.getElement_operacion().setValue("M");
/* 193 */     activarVista("nuevo");
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
/* 205 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 207 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 208 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 210 */     catch (Exception e) {}
/*     */     
/* 212 */     activarVista("nuevo");
/* 213 */     HTMLSelectElement combo = this.pagHTML.getElementTipoAplicacion();
/* 214 */     comboMultivalores(combo, "tipo_aplicacion", "", true);
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
/* 226 */     activarVista("consulta");
/* 227 */     String aplicacion = comms.request.getParameter("aplicacion");
/* 228 */     String descripcion = comms.request.getParameter("descripcion");
/* 229 */     String modulo = comms.request.getParameter("modulo");
/* 230 */     String tipoAplicacion = comms.request.getParameter("tipoAplicacion");
/* 231 */     String menuAplicacion = comms.request.getParameter("menuAplicacion");
/* 232 */     HTMLSelectElement combo = this.pagHTML.getElementFtipoAplicacion();
/* 233 */     comboMultivalores(combo, "tipo_aplicacion", "" + tipoAplicacion, true);
/*     */ 
/*     */     
/* 236 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */     
/* 240 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/* 241 */     Varios oVarios = new Varios();
/* 242 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Adm_SisAplicacionesAct");
/*     */ 
/*     */     
/* 245 */     SisAplicacionesDAO ob = new SisAplicacionesDAO();
/* 246 */     Collection<SisAplicacionesDTO> arr = ob.cargarTodos(aplicacion, descripcion, modulo, tipoAplicacion, menuAplicacion);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 252 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 253 */     int cuantas = 0;
/* 254 */     Iterator<SisAplicacionesDTO> iterator = arr.iterator();
/* 255 */     while (iterator.hasNext()) {
/* 256 */       SisAplicacionesDTO reg = (SisAplicacionesDTO)iterator.next();
/* 257 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 258 */       eltr.appendChild(newtd("" + reg.getAplicacion()));
/* 259 */       String url = "SisAplicaciones.po?_operacion=" + (oPermisoAct ? "P" : "V") + "&aplicacion=" + reg.getAplicacion() + "";
/* 260 */       eltr.appendChild(newtdhref("" + reg.getDescripcion(), url));
/* 261 */       eltr.appendChild(newtd("" + reg.getModulo()));
/* 262 */       eltr.appendChild(newtd("" + reg.getOrdenAplicacion()));
/* 263 */       eltr.appendChild(newtd("" + reg.getNombreTipoAplicacion()));
/* 264 */       eltr.appendChild(newtd("" + reg.getMenuAplicacion()));
/* 265 */       hte.appendChild(eltr);
/* 266 */       cuantas++;
/*     */     } 
/* 268 */     arr.clear();
/* 269 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 282 */     String aplicacion = comms.request.getParameter("aplicacion");
/* 283 */     SisAplicacionesDAO ob = new SisAplicacionesDAO();
/* 284 */     SisAplicacionesDTO reg = ob.cargarRegistro(aplicacion);
/* 285 */     if (reg != null) {
/* 286 */       this.pagHTML.setTextAplicacionEd("" + reg.getAplicacion());
/* 287 */       this.pagHTML.setTextDescripcionEd("" + reg.getDescripcion());
/* 288 */       this.pagHTML.setTextModuloEd("" + reg.getModulo());
/* 289 */       this.pagHTML.setTextOrdenAplicacionEd("" + reg.getOrdenAplicacion());
/* 290 */       this.pagHTML.setTextLinkAplicacionEd("" + reg.getLinkAplicacion());
/* 291 */       this.pagHTML.setTextTipoAplicacionEd("" + reg.getNombreTipoAplicacion());
/* 292 */       this.pagHTML.setTextMenuAplicacionEd("" + reg.getMenuAplicacion());
/* 293 */       this.pagHTML.setTextAnchoEd("" + reg.getAncho());
/* 294 */       this.pagHTML.setTextAltoEd("" + reg.getAlto());
/* 295 */       this.pagHTML.setTextEntornoEd("" + reg.getEntorno());
/* 296 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 297 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 298 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 299 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 301 */       this.pagHTML.getElementAplicacionKey().setValue("" + reg.getAplicacion());
/* 302 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 304 */     activarVista("editar");
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
/* 315 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 317 */     Varios oVarios = new Varios();
/* 318 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Adm_SisAplicacionesAct");
/* 319 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Adm_SisAplicacionesDel");
/* 320 */     if (!oPermisoAct) {
/* 321 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 322 */       elem.getParentNode().removeChild(elem);
/* 323 */       elem = this.pagHTML.getElementBtnGrabar();
/* 324 */       elem.getParentNode().removeChild(elem);
/* 325 */       elem = this.pagHTML.getElementBtnModificar();
/* 326 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 328 */     if (!oPermisoDel) {
/* 329 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 330 */       elem.getParentNode().removeChild(elem);
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
/* 341 */     if (!vista.equals("nuevo")) {
/* 342 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 343 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 345 */     if (!vista.equals("editar")) {
/* 346 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 347 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 349 */     if (!vista.equals("consulta")) {
/* 350 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 351 */       sel.getParentNode().removeChild(sel);
/* 352 */       sel = this.pagHTML.getElementDivResultados();
/* 353 */       sel.getParentNode().removeChild(sel);
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
/* 367 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 368 */     atrib.setValue(valor);
/* 369 */     return atrib;
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
/* 382 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 383 */     Element enlace = this.pagHTML.createElement("a");
/* 384 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 385 */     enlace.appendChild(hijo);
/* 386 */     Attr donde = this.pagHTML.createAttribute("href");
/* 387 */     donde.setValue(vinculo);
/* 388 */     enlace.setAttributeNode(donde);
/* 389 */     td.appendChild(enlace);
/* 390 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 391 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 401 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 402 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 403 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 404 */     return td;
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
/* 419 */     SisMultiValoresDAO ob = new SisMultiValoresDAO();
/* 420 */     Collection<SisMultiValoresDTO> arr = ob.cargarTabla(tabla);
/* 421 */     ob.close();
/* 422 */     if (dejarBlanco) {
/* 423 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 424 */       op.setValue("");
/* 425 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 426 */       combo.appendChild(op);
/*     */     } 
/* 428 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 429 */     while (iterator.hasNext()) {
/* 430 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 431 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 432 */       op.setValue("" + reg.getCodigo());
/* 433 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 434 */       if (defecto.equals(reg.getCodigo())) {
/* 435 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 436 */         escogida.setValue("on");
/* 437 */         op.setAttributeNode(escogida);
/*     */       } 
/* 439 */       combo.appendChild(op);
/*     */     } 
/* 441 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SisAplicaciones.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */