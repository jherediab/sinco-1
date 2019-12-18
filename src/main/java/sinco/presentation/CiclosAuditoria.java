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
/*     */ import sinco.business.CiclosAuditoriaDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CiclosAuditoriaDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.CiclosAuditoria;
/*     */ import sinco.presentation.CiclosAuditoriaHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CiclosAuditoria
/*     */   implements HttpPresentation
/*     */ {
/*     */   private CiclosAuditoriaHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  38 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  39 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  42 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  43 */     String _operacion = comms.request.getParameter("_operacion");
/*  44 */     if (_operacion == null || _operacion.length() == 0) {
/*  45 */       _operacion = "X";
/*     */     }
/*     */     
/*  48 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  49 */       creacion(comms);
/*     */     }
/*  51 */     if (_operacion.equals("ES")) {
/*  52 */       enviarSolicitudes(comms);
/*     */     }
/*     */     
/*  55 */     this.pagHTML = (CiclosAuditoriaHTML)comms.xmlcFactory.create(CiclosAuditoriaHTML.class);
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
/*  87 */     String ciclo = comms.request.getParameter("ciclo");
/*  88 */     if (ciclo == null) {
/*  89 */       ciclo = "";
/*     */     }
/*  91 */     RespuestaBD rta = new RespuestaBD();
/*  92 */     if (_operacion.equals("E")) {
/*  93 */       CiclosAuditoriaDAO rs = new CiclosAuditoriaDAO();
/*  94 */       rta = rs.eliminarRegistro(ciclo);
/*  95 */       if (!rta.isRta()) {
/*  96 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCiclosAuditoria&p1=" + rta.getMensaje()));
/*     */       }
/*  98 */       String sPagina = "CiclosAuditoria.po?_operacion=X";
/*  99 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 101 */     String estado = comms.request.getParameter("estado");
/* 102 */     String descripcion = comms.request.getParameter("descripcion");
/* 103 */     String fechaCierre = comms.request.getParameter("fechaCierre");
/* 104 */     int servicioAuditoria = 0;
/*     */     try {
/* 106 */       servicioAuditoria = Integer.parseInt(comms.request.getParameter("servicioAuditoria"));
/*     */     }
/* 108 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 111 */     int servicioCalificarGrupo = 0;
/*     */     try {
/* 113 */       servicioCalificarGrupo = Integer.parseInt(comms.request.getParameter("servicioCalificarGrupo"));
/*     */     }
/* 115 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 118 */     int servicioCalificarAuditor = 0;
/*     */     try {
/* 120 */       servicioCalificarAuditor = Integer.parseInt(comms.request.getParameter("servicioCalificarAuditor"));
/*     */     }
/* 122 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 125 */     String mensajeRealizarAuditoria = comms.request.getParameter("mensajeRealizarAuditoria");
/* 126 */     String mensajeCalificarAuditor = comms.request.getParameter("mensajeCalificarAuditor");
/* 127 */     String mensajeCalificarGrupo = comms.request.getParameter("mensajeCalificarGrupo");
/* 128 */     CiclosAuditoriaDAO rs = new CiclosAuditoriaDAO();
/* 129 */     if (_operacion.equals("C")) {
/* 130 */       rta = rs.crearRegistro(ciclo, estado, descripcion, fechaCierre, servicioAuditoria, servicioCalificarGrupo, servicioCalificarAuditor, mensajeRealizarAuditoria, mensajeCalificarAuditor, mensajeCalificarGrupo, elUsuario);
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
/* 144 */       rta = rs.modificarRegistro(ciclo, estado, descripcion, fechaCierre, servicioAuditoria, servicioCalificarGrupo, servicioCalificarAuditor, mensajeRealizarAuditoria, mensajeCalificarAuditor, mensajeCalificarGrupo, elUsuario);
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
/* 157 */     if (!rta.isRta()) {
/* 158 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCiclosAuditoria&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 161 */     String sPagina = "CiclosAuditoria.po?_operacion=P&ciclo=" + ciclo + "";
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void enviarSolicitudes(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 177 */     String elUsuario = "" + comms.session.getUser().getName();
/* 178 */     String ciclo = comms.request.getParameter("ciclo");
/* 179 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/* 181 */     RespuestaBD rta = new RespuestaBD();
/* 182 */     CiclosAuditoriaDAO rs = new CiclosAuditoriaDAO();
/* 183 */     rta = rs.enviarSolicitudes(ciclo, idNav, elUsuario);
/* 184 */     if (!rta.isRta()) {
/* 185 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCiclosAuditoria&p1=" + rta.getMensaje()));
/*     */     }
/* 187 */     String sPagina = "CiclosAuditoria.po?_operacion=V&ciclo=" + ciclo;
/* 188 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 199 */     String ciclo = comms.request.getParameter("ciclo");
/* 200 */     CiclosAuditoriaDAO rs = new CiclosAuditoriaDAO();
/* 201 */     CiclosAuditoriaDTO reg = rs.cargarRegistro(ciclo);
/* 202 */     if (reg != null) {
/* 203 */       this.pagHTML.getElementCiclo().setValue("" + reg.getCiclo());
/* 204 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 205 */       this.pagHTML.getElementFechaCierre().setValue("" + Utilidades.darFormatoFecha(reg.getFechaCierre()));
/* 206 */       this.pagHTML.getElementServicioAuditoria().setValue("" + reg.getServicioAuditoria());
/* 207 */       this.pagHTML.getElementServicioCalificarGrupo().setValue("" + reg.getServicioCalificarGrupo());
/* 208 */       this.pagHTML.getElementServicioCalificarAuditor().setValue("" + reg.getServicioCalificarAuditor());
/* 209 */       this.pagHTML.getElementMensajeRealizarAuditoria().setValue("" + reg.getMensajeRealizarAuditoria());
/* 210 */       this.pagHTML.getElementMensajeCalificarAuditor().setValue("" + reg.getMensajeCalificarAuditor());
/* 211 */       this.pagHTML.getElementMensajeCalificarGrupo().setValue("" + reg.getMensajeCalificarGrupo());
/* 212 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 213 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 214 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 215 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 216 */       HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 217 */       comboMultivalores(combo, "ESTADO_CICLOS_AUDITORIA", "" + reg.getEstado(), true);
/*     */ 
/*     */       
/* 220 */       this.pagHTML.getElementCiclo().setReadOnly(true);
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
/* 243 */     HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 244 */     comboMultivalores(combo, "ESTADO_CICLOS_AUDITORIA", "", true);
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
/* 256 */     activarVista("consulta");
/* 257 */     String ciclo = comms.request.getParameter("ciclo");
/* 258 */     if (ciclo == null) {
/* 259 */       ciclo = "";
/*     */     }
/* 261 */     String estado = comms.request.getParameter("estado");
/* 262 */     if (estado == null) {
/* 263 */       estado = "";
/*     */     }
/* 265 */     String descripcion = comms.request.getParameter("descripcion");
/* 266 */     if (descripcion == null) {
/* 267 */       descripcion = "";
/*     */     }
/* 269 */     HTMLSelectElement combo = this.pagHTML.getElementFestado();
/* 270 */     comboMultivalores(combo, "ESTADO_CICLOS_AUDITORIA", "" + estado, true);
/*     */ 
/*     */     
/* 273 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 278 */     CiclosAuditoriaDAO rs = new CiclosAuditoriaDAO();
/* 279 */     Collection<CiclosAuditoriaDTO> arr = rs.cargarTodos(ciclo, estado, descripcion);
/*     */ 
/*     */ 
/*     */     
/* 283 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 284 */     int cuantas = 0;
/* 285 */     Iterator<CiclosAuditoriaDTO> iterator = arr.iterator();
/* 286 */     while (iterator.hasNext()) {
/* 287 */       CiclosAuditoriaDTO reg = (CiclosAuditoriaDTO)iterator.next();
/* 288 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 289 */       eltr.appendChild(newtd("" + reg.getCiclo()));
/* 290 */       String url = "CiclosAuditoria.po?_operacion=V&ciclo=" + reg.getCiclo() + "";
/* 291 */       eltr.appendChild(newtdhref("" + reg.getNombreEstado(), url));
/* 292 */       eltr.appendChild(newtd("" + reg.getDescripcion()));
/* 293 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaCierre())));
/* 294 */       eltr.appendChild(newtd("" + reg.getServicioAuditoria()));
/* 295 */       eltr.appendChild(newtd("" + reg.getServicioCalificarGrupo()));
/* 296 */       eltr.appendChild(newtd("" + reg.getServicioCalificarAuditor()));
/* 297 */       eltr.appendChild(newtd("" + reg.getMensajeRealizarAuditoria()));
/* 298 */       eltr.appendChild(newtd("" + reg.getMensajeCalificarAuditor()));
/* 299 */       eltr.appendChild(newtd("" + reg.getMensajeCalificarGrupo()));
/* 300 */       hte.appendChild(eltr);
/* 301 */       cuantas++;
/*     */     } 
/* 303 */     arr.clear();
/* 304 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 317 */     String ciclo = comms.request.getParameter("ciclo");
/* 318 */     CiclosAuditoriaDAO rs = new CiclosAuditoriaDAO();
/* 319 */     CiclosAuditoriaDTO reg = rs.cargarRegistro(ciclo);
/* 320 */     if (reg != null) {
/* 321 */       this.pagHTML.setTextCicloEd("" + reg.getCiclo());
/* 322 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/* 323 */       this.pagHTML.setTextDescripcionEd("" + reg.getDescripcion());
/* 324 */       this.pagHTML.setTextFechaCierreEd("" + Utilidades.darFormatoFecha(reg.getFechaCierre()));
/* 325 */       this.pagHTML.setTextServicioAuditoriaEd("" + reg.getServicioAuditoria());
/* 326 */       this.pagHTML.setTextServicioCalificarGrupoEd("" + reg.getServicioCalificarGrupo());
/* 327 */       this.pagHTML.setTextServicioCalificarAuditorEd("" + reg.getServicioCalificarAuditor());
/* 328 */       this.pagHTML.setTextMensajeRealizarAuditoriaEd("" + reg.getMensajeRealizarAuditoria());
/* 329 */       this.pagHTML.setTextMensajeCalificarAuditorEd("" + reg.getMensajeCalificarAuditor());
/* 330 */       this.pagHTML.setTextMensajeCalificarGrupoEd("" + reg.getMensajeCalificarGrupo());
/* 331 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 332 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 333 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 334 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 336 */       this.pagHTML.getElementCicloKey().setValue("" + reg.getCiclo());
/* 337 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 339 */     activarVista("editar");
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
/* 350 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 352 */     Varios oVarios = new Varios();
/* 353 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Aud_menuCiclosAuditoriaAct");
/* 354 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Aud_menuCiclosAuditoriaDel");
/* 355 */     if (!oPermisoAct) {
/* 356 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 357 */       elem.getParentNode().removeChild(elem);
/* 358 */       elem = this.pagHTML.getElementBtnGrabar();
/* 359 */       elem.getParentNode().removeChild(elem);
/* 360 */       elem = this.pagHTML.getElementBtnModificar();
/* 361 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 363 */     if (!oPermisoDel) {
/* 364 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 365 */       elem.getParentNode().removeChild(elem);
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
/* 376 */     if (!vista.equals("nuevo")) {
/* 377 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 378 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 380 */     if (!vista.equals("editar")) {
/* 381 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 382 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 384 */     if (!vista.equals("consulta")) {
/* 385 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 386 */       sel.getParentNode().removeChild(sel);
/* 387 */       sel = this.pagHTML.getElementDivResultados();
/* 388 */       sel.getParentNode().removeChild(sel);
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
/* 402 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 403 */     atrib.setValue(valor);
/* 404 */     return atrib;
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
/* 417 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 418 */     Element enlace = this.pagHTML.createElement("a");
/* 419 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 420 */     enlace.appendChild(hijo);
/* 421 */     Attr donde = this.pagHTML.createAttribute("href");
/* 422 */     donde.setValue(vinculo);
/* 423 */     enlace.setAttributeNode(donde);
/* 424 */     td.appendChild(enlace);
/* 425 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 426 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 436 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 437 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 438 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 439 */     return td;
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
/* 454 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 455 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 456 */     rs.close();
/* 457 */     if (dejarBlanco) {
/* 458 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 459 */       op.setValue("");
/* 460 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 461 */       combo.appendChild(op);
/*     */     } 
/* 463 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 464 */     while (iterator.hasNext()) {
/* 465 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 466 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 467 */       op.setValue("" + reg.getCodigo());
/* 468 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 469 */       if (defecto.equals(reg.getCodigo())) {
/* 470 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 471 */         escogida.setValue("on");
/* 472 */         op.setAttributeNode(escogida);
/*     */       } 
/* 474 */       combo.appendChild(op);
/*     */     } 
/* 476 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CiclosAuditoria.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */