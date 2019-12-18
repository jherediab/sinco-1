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
/*     */ import sinco.business.PoaProyectosInversionDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.data.PoaProyectosInversionDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.PoaProyectosInversion;
/*     */ import sinco.presentation.PoaProyectosInversionHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PoaProyectosInversion
/*     */   implements HttpPresentation
/*     */ {
/*     */   private PoaProyectosInversionHTML pagHTML;
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
/*  51 */     this.pagHTML = (PoaProyectosInversionHTML)comms.xmlcFactory.create(PoaProyectosInversionHTML.class);
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
/*  83 */     String codigoProyectoInversion = comms.request.getParameter("codigoProyectoInversion");
/*  84 */     if (codigoProyectoInversion == null) {
/*  85 */       codigoProyectoInversion = "";
/*     */     }
/*  87 */     RespuestaBD rta = new RespuestaBD();
/*  88 */     if (_operacion.equals("E")) {
/*  89 */       PoaProyectosInversionDAO rs = new PoaProyectosInversionDAO();
/*  90 */       if (!rs.getEstadoConexion()) {
/*  91 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*  93 */       rta = rs.eliminarRegistro(codigoProyectoInversion);
/*  94 */       if (!rta.isRta()) {
/*  95 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaProyectosInversion&p1=" + rta.getMensaje()));
/*     */       }
/*  97 */       rs.close();
/*  98 */       String sPagina = "PoaProyectosInversion.po?_operacion=X";
/*  99 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/*     */     
/* 102 */     String descripcion = comms.request.getParameter("descripcion");
/*     */ 
/*     */     
/* 105 */     String objetivo = comms.request.getParameter("objetivo");
/* 106 */     if (objetivo == null) {
/* 107 */       objetivo = "";
/*     */     }
/*     */     
/* 110 */     String fechaRadicado = comms.request.getParameter("fechaRadicado");
/* 111 */     if (fechaRadicado == null) {
/* 112 */       fechaRadicado = "";
/*     */     }
/*     */     
/* 115 */     double valor = 0.0D;
/*     */     
/*     */     try {
/* 118 */       valor = Double.parseDouble(comms.request.getParameter("valor"));
/* 119 */     } catch (Exception e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 124 */     String fechaInicio = comms.request.getParameter("fechaInicio");
/* 125 */     if (fechaInicio == null) {
/* 126 */       fechaInicio = "";
/*     */     }
/*     */     
/* 129 */     String fechaTerminacion = comms.request.getParameter("fechaTerminacion");
/* 130 */     if (fechaTerminacion == null) {
/* 131 */       fechaTerminacion = "";
/*     */     }
/*     */     
/* 134 */     String fase = comms.request.getParameter("fase");
/* 135 */     if (fase == null) {
/* 136 */       fase = "";
/*     */     }
/*     */     
/* 139 */     String ejecutor = comms.request.getParameter("ejecutor");
/* 140 */     if (ejecutor == null) {
/* 141 */       ejecutor = "";
/*     */     }
/*     */     
/* 144 */     String fuente = comms.request.getParameter("fuente");
/* 145 */     if (fuente == null) {
/* 146 */       fuente = "";
/*     */     }
/*     */ 
/*     */     
/* 150 */     String estado = comms.request.getParameter("estado");
/* 151 */     PoaProyectosInversionDAO rs = new PoaProyectosInversionDAO();
/* 152 */     if (!rs.getEstadoConexion()) {
/* 153 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 155 */     if (_operacion.equals("C")) {
/* 156 */       rta = rs.crearRegistro(codigoProyectoInversion, descripcion, objetivo, fechaRadicado, valor, fechaInicio, fechaTerminacion, fase, ejecutor, fuente, estado, elUsuario);
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
/* 171 */       rta = rs.modificarRegistro(codigoProyectoInversion, descripcion, objetivo, fechaRadicado, valor, fechaInicio, fechaTerminacion, fase, ejecutor, fuente, estado, elUsuario);
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
/* 185 */     rs.close();
/* 186 */     if (!rta.isRta()) {
/* 187 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaProyectosInversion&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 190 */     String sPagina = "PoaProyectosInversion.po?_operacion=V&codigoProyectoInversion=" + codigoProyectoInversion;
/* 191 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 202 */     String codigoProyectoInversion = comms.request.getParameter("codigoProyectoInversion");
/* 203 */     PoaProyectosInversionDAO rs = new PoaProyectosInversionDAO();
/* 204 */     if (!rs.getEstadoConexion()) {
/* 205 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 207 */     PoaProyectosInversionDTO reg = rs.cargarRegistro(codigoProyectoInversion);
/* 208 */     rs.close();
/* 209 */     if (reg != null) {
/* 210 */       this.pagHTML.getElementCodigoProyectoInversion().setValue("" + reg.getCodigoProyectoInversion());
/* 211 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/*     */       
/* 213 */       this.pagHTML.getElementObjetivo().setValue("" + reg.getObjetivo());
/* 214 */       this.pagHTML.getElementFechaRadicado().setValue("" + reg.getFechaRadicado());
/* 215 */       this.pagHTML.getElementValor().setValue("" + reg.getValor());
/* 216 */       this.pagHTML.getElementFechaInicio().setValue("" + reg.getFechaInicio());
/* 217 */       this.pagHTML.getElementFechaTerminacion().setValue("" + reg.getFechaTerminacion());
/* 218 */       this.pagHTML.getElementFase().setValue("" + reg.getFase());
/* 219 */       this.pagHTML.getElementEjecutor().setValue("" + reg.getEjecutor());
/* 220 */       this.pagHTML.getElementFuente().setValue("" + reg.getFuente());
/*     */       
/* 222 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 223 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 224 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 225 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/*     */       
/* 227 */       HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 228 */       comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), false);
/*     */ 
/*     */       
/* 231 */       this.pagHTML.getElementCodigoProyectoInversion().setReadOnly(true);
/*     */     } 
/* 233 */     this.pagHTML.getElement_operacion().setValue("M");
/* 234 */     activarVista("nuevo");
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
/* 246 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 248 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 249 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 251 */     catch (Exception e) {}
/*     */     
/* 253 */     activarVista("nuevo");
/* 254 */     HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 255 */     comboMultivalores(combo, "ESTADO_REGISTRO", "", false);
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
/* 267 */     activarVista("consulta");
/* 268 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 273 */     PoaProyectosInversionDAO rs = new PoaProyectosInversionDAO();
/* 274 */     if (!rs.getEstadoConexion()) {
/* 275 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 277 */     Collection<PoaProyectosInversionDTO> arr = rs.cargarTodos();
/* 278 */     rs.close();
/* 279 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 280 */     int cuantas = 0;
/* 281 */     Iterator<PoaProyectosInversionDTO> iterator = arr.iterator();
/* 282 */     while (iterator.hasNext()) {
/* 283 */       PoaProyectosInversionDTO reg = (PoaProyectosInversionDTO)iterator.next();
/* 284 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 285 */       eltr.appendChild(newtd("" + reg.getCodigoProyectoInversion()));
/* 286 */       String url = "PoaProyectosInversion.po?_operacion=V&codigoProyectoInversion=" + reg.getCodigoProyectoInversion() + "";
/* 287 */       eltr.appendChild(newtdhref("" + reg.getDescripcion(), url));
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
/* 306 */     String codigoProyectoInversion = comms.request.getParameter("codigoProyectoInversion");
/* 307 */     PoaProyectosInversionDAO rs = new PoaProyectosInversionDAO();
/* 308 */     if (!rs.getEstadoConexion()) {
/* 309 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 311 */     PoaProyectosInversionDTO reg = rs.cargarRegistro(codigoProyectoInversion);
/* 312 */     rs.close();
/* 313 */     if (reg != null) {
/* 314 */       this.pagHTML.setTextCodigoProyectoInversionEd("" + reg.getCodigoProyectoInversion());
/* 315 */       this.pagHTML.setTextDescripcionEd("" + reg.getDescripcion());
/* 316 */       this.pagHTML.setTextObjetivoEd("" + reg.getObjetivo());
/* 317 */       this.pagHTML.setTextFechaRadicadoEd("" + reg.getFechaRadicado());
/* 318 */       this.pagHTML.setTextValorEd("" + reg.getValor());
/* 319 */       this.pagHTML.setTextFechaInicioEd("" + reg.getFechaInicio());
/* 320 */       this.pagHTML.setTextFechaTerminacionEd("" + reg.getFechaTerminacion());
/* 321 */       this.pagHTML.setTextFaseEd("" + reg.getFase());
/* 322 */       this.pagHTML.setTextEjecutorEd("" + reg.getEjecutor());
/* 323 */       this.pagHTML.setTextFuenteEd("" + reg.getFuente());
/* 324 */       this.pagHTML.setTextEstadoEd("" + reg.getEstado());
/* 325 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 326 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 327 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 328 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 330 */       this.pagHTML.getElementCodigoProyectoInversionKey().setValue("" + reg.getCodigoProyectoInversion());
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
/* 347 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Poa_PoaProyectosInversionAct");
/* 348 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Poa_PoaProyectosInversionDel");
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
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PoaProyectosInversion.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */