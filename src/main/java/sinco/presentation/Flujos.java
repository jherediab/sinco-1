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
/*     */ import sinco.business.FlujoDetalleDTO;
/*     */ import sinco.business.FlujosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.FlujoDetalleDAO;
/*     */ import sinco.data.FlujosDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.Flujos;
/*     */ import sinco.presentation.FlujosHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Flujos
/*     */   implements HttpPresentation
/*     */ {
/*     */   private FlujosHTML pagHTML;
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
/*  55 */     this.pagHTML = (FlujosHTML)comms.xmlcFactory.create(FlujosHTML.class);
/*  56 */     permisos(comms);
/*     */     
/*  58 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  59 */       listar(comms, _operacion);
/*     */     
/*     */     }
/*  62 */     else if (_operacion.equals("P")) {
/*  63 */       editar(comms);
/*     */     
/*     */     }
/*  66 */     else if (_operacion.equals("V")) {
/*  67 */       verRegistro(comms);
/*     */     }
/*  69 */     else if (_operacion.equals("Nuevo")) {
/*  70 */       nuevo(comms);
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
/*  87 */     int codigoFlujo = 0;
/*     */     try {
/*  89 */       codigoFlujo = Integer.parseInt(comms.request.getParameter("codigoFlujo"));
/*     */     }
/*  91 */     catch (Exception e) {
/*  92 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoFlujo"));
/*     */     } 
/*     */     
/*  95 */     RespuestaBD rta = new RespuestaBD();
/*  96 */     if (_operacion.equals("E")) {
/*  97 */       FlujosDAO rs = new FlujosDAO();
/*  98 */       if (!rs.getEstadoConexion()) {
/*  99 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 101 */       rta = rs.eliminarRegistro(codigoFlujo);
/* 102 */       if (!rta.isRta()) {
/* 103 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorFlujos&p1=" + rta.getMensaje()));
/*     */       }
/* 105 */       rs.close();
/* 106 */       String sPagina = "Flujos.po?_operacion=X";
/* 107 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 109 */     String descripcion = comms.request.getParameter("descripcion");
/* 110 */     int servicioInicial = 0;
/*     */     try {
/* 112 */       servicioInicial = Integer.parseInt(comms.request.getParameter("servicioInicial"));
/*     */     }
/* 114 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 117 */     String estado = comms.request.getParameter("estado");
/* 118 */     FlujosDAO rs = new FlujosDAO();
/* 119 */     if (!rs.getEstadoConexion()) {
/* 120 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 122 */     if (_operacion.equals("C")) {
/* 123 */       rta = rs.crearRegistro(codigoFlujo, descripcion, servicioInicial, estado, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 129 */       codigoFlujo = rta.getSecuencia();
/*     */     } else {
/*     */       
/* 132 */       rta = rs.modificarRegistro(codigoFlujo, descripcion, servicioInicial, estado, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 139 */     rs.close();
/* 140 */     if (!rta.isRta()) {
/* 141 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorFlujos&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 144 */     String sPagina = "Flujos.po?_operacion=P&codigoFlujo=" + codigoFlujo + "";
/* 145 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 156 */     activarVista("consulta");
/* 157 */     int codigoFlujo = 0;
/*     */     try {
/* 159 */       codigoFlujo = Integer.parseInt(comms.request.getParameter("codigoFlujo"));
/*     */     }
/* 161 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 164 */     String descripcion = comms.request.getParameter("descripcion");
/* 165 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */     
/* 169 */     FlujosDAO rs = new FlujosDAO();
/* 170 */     if (!rs.getEstadoConexion()) {
/* 171 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 173 */     Collection<FlujosDTO> arr = rs.cargarTodos(codigoFlujo, descripcion);
/*     */ 
/*     */     
/* 176 */     rs.close();
/* 177 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 178 */     int cuantas = 0;
/* 179 */     Iterator<FlujosDTO> iterator = arr.iterator();
/* 180 */     while (iterator.hasNext()) {
/* 181 */       FlujosDTO reg = (FlujosDTO)iterator.next();
/* 182 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 183 */       eltr.appendChild(newtd("" + reg.getCodigoFlujo()));
/* 184 */       String url = "Flujos.po?_operacion=V&codigoFlujo=" + reg.getCodigoFlujo() + "";
/* 185 */       eltr.appendChild(newtdhref("" + reg.getDescripcion(), url));
/* 186 */       eltr.appendChild(newtd("" + reg.getNombreServicioInicial()));
/* 187 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 188 */       hte.appendChild(eltr);
/* 189 */       cuantas++;
/*     */     } 
/* 191 */     arr.clear();
/* 192 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 205 */     int codigoFlujo = 0;
/*     */     try {
/* 207 */       codigoFlujo = Integer.parseInt(comms.request.getParameter("codigoFlujo"));
/*     */     }
/* 209 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 212 */     FlujosDAO rs = new FlujosDAO();
/* 213 */     if (!rs.getEstadoConexion()) {
/* 214 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 216 */     FlujosDTO reg = rs.cargarRegistro(codigoFlujo);
/* 217 */     rs.close();
/* 218 */     if (reg != null) {
/* 219 */       this.pagHTML.getElementCodigoFlujo().setValue("" + reg.getCodigoFlujo());
/* 220 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 221 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 222 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 223 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 224 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/*     */       
/* 226 */       HTMLSelectElement combo = this.pagHTML.getElementServicioInicial();
/* 227 */       llenarCombo(combo, "SERVICIOS", "CODIGO", "descripcion", "ESPECIALIZADO='S' and estado='A' order by descripcion", "" + reg.getServicioInicial(), true);
/*     */       
/* 229 */       combo = this.pagHTML.getElementEstado();
/* 230 */       comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), true);
/*     */ 
/*     */       
/* 233 */       this.pagHTML.getElementCodigoFlujo().setReadOnly(true);
/*     */     } 
/* 235 */     this.pagHTML.getElement_operacion().setValue("M");
/* 236 */     activarVista("nuevo");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void verRegistro(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 247 */     int codigoFlujo = 0;
/*     */     try {
/* 249 */       codigoFlujo = Integer.parseInt(comms.request.getParameter("codigoFlujo"));
/*     */     }
/* 251 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 254 */     FlujosDAO rs = new FlujosDAO();
/* 255 */     if (!rs.getEstadoConexion()) {
/* 256 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 258 */     FlujosDTO reg = rs.cargarRegistro(codigoFlujo);
/* 259 */     rs.close();
/* 260 */     if (reg != null) {
/* 261 */       this.pagHTML.setTextCodigoFlujoEd("" + reg.getCodigoFlujo());
/* 262 */       this.pagHTML.setTextDescripcionEd("" + reg.getDescripcion());
/* 263 */       this.pagHTML.setTextServicioInicialEd("" + reg.getNombreServicioInicial());
/* 264 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/* 265 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 266 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 267 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/* 268 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/*     */       
/* 270 */       this.pagHTML.getElementCodigoFlujoKey().setValue("" + reg.getCodigoFlujo());
/* 271 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */       
/* 273 */       listarDetalle(comms, codigoFlujo);
/*     */     } 
/* 275 */     activarVista("editar");
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
/* 287 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 289 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 290 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 292 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 295 */     activarVista("nuevo");
/*     */     
/* 297 */     HTMLSelectElement combo = this.pagHTML.getElementServicioInicial();
/* 298 */     llenarCombo(combo, "SERVICIOS", "CODIGO", "descripcion", "ESPECIALIZADO='S' and coalesce(ind_flujo_trabajo,'N') ='N' AND estado='A' order by descripcion", "", true);
/*     */     
/* 300 */     combo = this.pagHTML.getElementEstado();
/* 301 */     comboMultivalores(combo, "ESTADO_REGISTRO", "A", true);
/*     */     
/* 303 */     this.pagHTML.getElementCodigoFlujo().setReadOnly(true);
/*     */     
/* 305 */     FlujosDAO rs = new FlujosDAO();
/* 306 */     int siguienteRegistro = rs.siguienteRegistro();
/* 307 */     rs.close();
/* 308 */     this.pagHTML.getElementCodigoFlujo().setValue("" + siguienteRegistro);
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
/*     */   private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 320 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 322 */     Varios oVarios = new Varios();
/* 323 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "FlujosAct");
/* 324 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "FlujosDel");
/* 325 */     if (!oPermisoAct) {
/* 326 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 327 */       elem.getParentNode().removeChild(elem);
/* 328 */       elem = this.pagHTML.getElementBtnGrabar();
/* 329 */       elem.getParentNode().removeChild(elem);
/* 330 */       elem = this.pagHTML.getElementBtnModificar();
/* 331 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 333 */     if (!oPermisoDel) {
/* 334 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 335 */       elem.getParentNode().removeChild(elem);
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
/* 346 */     if (!vista.equals("nuevo")) {
/* 347 */       HTMLElement sel = this.pagHTML.getElementTrCreacionRegistro();
/* 348 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 350 */     if (!vista.equals("editar")) {
/* 351 */       HTMLElement sel = this.pagHTML.getElementTrEdicion();
/* 352 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 354 */     if (!vista.equals("consulta")) {
/* 355 */       HTMLElement sel = this.pagHTML.getElementTrConsulta();
/* 356 */       sel.getParentNode().removeChild(sel);
/* 357 */       sel = this.pagHTML.getElementTrResultados();
/* 358 */       sel.getParentNode().removeChild(sel);
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
/* 372 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 373 */     atrib.setValue(valor);
/* 374 */     return atrib;
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
/* 387 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 388 */     Element enlace = this.pagHTML.createElement("a");
/* 389 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 390 */     enlace.appendChild(hijo);
/* 391 */     Attr donde = this.pagHTML.createAttribute("href");
/* 392 */     donde.setValue(vinculo);
/* 393 */     enlace.setAttributeNode(donde);
/* 394 */     td.appendChild(enlace);
/* 395 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 396 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 406 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 407 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 408 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 409 */     return td;
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
/* 424 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 425 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 426 */     rs.close();
/* 427 */     if (dejarBlanco) {
/* 428 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 429 */       op.setValue("");
/* 430 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 431 */       combo.appendChild(op);
/*     */     } 
/* 433 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 434 */     while (iterator.hasNext()) {
/* 435 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 436 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 437 */       op.setValue("" + reg.getCodigo());
/* 438 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 439 */       if (defecto.equals(reg.getCodigo())) {
/* 440 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 441 */         escogida.setValue("on");
/* 442 */         op.setAttributeNode(escogida);
/*     */       } 
/* 444 */       combo.appendChild(op);
/*     */     } 
/* 446 */     arr.clear();
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
/* 467 */     if (dejarBlanco) {
/* 468 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 469 */       op.setValue("");
/* 470 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 471 */       combo.appendChild(op);
/*     */     } 
/* 473 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 474 */     Collection<TGeneralDTO> arr = rsTGen.cargarTodosArray(tabla, codigo, descripcion, condicion);
/* 475 */     rsTGen.close();
/* 476 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 477 */     while (iterator.hasNext()) {
/* 478 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 479 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 480 */       op.setValue("" + regGeneral.getCodigo());
/* 481 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 482 */       if (defecto != null && defecto.equals(regGeneral.getCodigo())) {
/* 483 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 484 */         escogida.setValue("on");
/* 485 */         op.setAttributeNode(escogida);
/*     */       } 
/* 487 */       combo.appendChild(op);
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
/*     */   private void listarDetalle(HttpPresentationComms comms, int codigoFlujo) throws HttpPresentationException, KeywordValueException {
/* 501 */     FlujoDetalleDAO rs = new FlujoDetalleDAO();
/* 502 */     if (!rs.getEstadoConexion()) {
/* 503 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 505 */     Collection<FlujoDetalleDTO> arr = rs.cargarTodos(codigoFlujo);
/* 506 */     rs.close();
/*     */     
/* 508 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalleFlujo();
/* 509 */     int cuantas = 0;
/* 510 */     Iterator<FlujoDetalleDTO> iterator = arr.iterator();
/* 511 */     while (iterator.hasNext()) {
/* 512 */       FlujoDetalleDTO reg = (FlujoDetalleDTO)iterator.next();
/* 513 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 514 */       eltr.appendChild(newtd("" + reg.getSecuencia()));
/* 515 */       String url = "FlujoDetalle.po?_operacion=P&codigoFlujo=" + reg.getCodigoFlujo() + "&secuencia=" + reg.getSecuencia() + "";
/* 516 */       eltr.appendChild(newtdhref("" + reg.getNombreServicioInicio(), url));
/* 517 */       eltr.appendChild(newtd("" + reg.getNombreCodigoEstado()));
/* 518 */       eltr.appendChild(newtd("" + reg.getNombreServicioDestino()));
/* 519 */       eltr.appendChild(newtd("" + reg.getNombreProcedimiento()));
/* 520 */       eltr.appendChild(newtd("" + reg.getCorreoDestino()));
/*     */       
/* 522 */       eltr.appendChild(newtd("" + reg.getNombreCaracteristica()));
/* 523 */       eltr.appendChild(newtd("" + reg.getDescripcionValor()));
/*     */       
/* 525 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 526 */       hte.appendChild(eltr);
/* 527 */       cuantas++;
/*     */     } 
/* 529 */     arr.clear();
/* 530 */     this.pagHTML.setTextNroDetalle("" + cuantas);
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\Flujos.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */