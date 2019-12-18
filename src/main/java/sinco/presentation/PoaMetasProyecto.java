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
/*     */ import sinco.business.PoaMetasProyectoDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.data.PoaMetasProyectoDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.PoaMetasProyecto;
/*     */ import sinco.presentation.PoaMetasProyectoHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PoaMetasProyecto
/*     */   implements HttpPresentation
/*     */ {
/*     */   private PoaMetasProyectoHTML pagHTML;
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
/*  51 */     this.pagHTML = (PoaMetasProyectoHTML)comms.xmlcFactory.create(PoaMetasProyectoHTML.class);
/*  52 */     permisos(comms);
/*     */ 
/*     */     
/*  55 */     int codigoMetaPlan = 0;
/*     */     try {
/*  57 */       codigoMetaPlan = Integer.parseInt(comms.request.getParameter("codigoMetaPlan"));
/*     */     }
/*  59 */     catch (Exception e) {
/*  60 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoMetaPlan"));
/*     */     } 
/*     */     
/*  63 */     this.pagHTML.getElementCodigoMetaPlanHidden().setValue("" + codigoMetaPlan);
/*  64 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  65 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  68 */     if (_operacion.equals("P")) {
/*  69 */       editar(comms);
/*     */     }
/*  71 */     else if (_operacion.equals("Nuevo")) {
/*  72 */       nuevo(comms);
/*     */     } 
/*     */     
/*  75 */     if (_operacion.equals("V")) {
/*  76 */       verRegistro(comms);
/*     */     }
/*  78 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  79 */     comms.response.writeDOM(this.pagHTML);
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
/*  91 */     String _operacion = comms.request.getParameter("_operacion");
/*  92 */     String elUsuario = "" + comms.session.getUser().getName();
/*  93 */     int codigoMetaPlan = 0;
/*     */     try {
/*  95 */       codigoMetaPlan = Integer.parseInt(comms.request.getParameter("codigoMetaPlan"));
/*     */     }
/*  97 */     catch (Exception e) {
/*  98 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoMetaPlan"));
/*     */     } 
/*     */     
/* 101 */     int CODIGOMETAPROYECTO = 0;
/*     */     try {
/* 103 */       CODIGOMETAPROYECTO = Integer.parseInt(comms.request.getParameter("CODIGOMETAPROYECTO"));
/*     */     }
/* 105 */     catch (Exception e) {
/* 106 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=CODIGOMETAPROYECTO"));
/*     */     } 
/*     */     
/* 109 */     RespuestaBD rta = new RespuestaBD();
/* 110 */     if (_operacion.equals("E")) {
/* 111 */       PoaMetasProyectoDAO rs = new PoaMetasProyectoDAO();
/* 112 */       if (!rs.getEstadoConexion()) {
/* 113 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 115 */       rta = rs.eliminarRegistro(codigoMetaPlan, CODIGOMETAPROYECTO);
/*     */       
/* 117 */       if (!rta.isRta()) {
/* 118 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaMetasProyecto&p1=" + rta.getMensaje()));
/*     */       }
/* 120 */       rs.close();
/* 121 */       String sPagina = "PoaMetasProyecto.po?_operacion=X&codigoMetaPlan=" + codigoMetaPlan + "&CODIGOMETAPROYECTO=" + CODIGOMETAPROYECTO + "";
/* 122 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 124 */     String descripcion = comms.request.getParameter("descripcion");
/* 125 */     String estado = comms.request.getParameter("estado");
/* 126 */     PoaMetasProyectoDAO rs = new PoaMetasProyectoDAO();
/* 127 */     if (!rs.getEstadoConexion()) {
/* 128 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 130 */     if (_operacion.equals("C")) {
/* 131 */       rta = rs.crearRegistro(codigoMetaPlan, CODIGOMETAPROYECTO, descripcion, estado, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 137 */       CODIGOMETAPROYECTO = rta.getSecuencia();
/*     */     } else {
/*     */       
/* 140 */       rta = rs.modificarRegistro(codigoMetaPlan, CODIGOMETAPROYECTO, descripcion, estado, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 147 */     rs.close();
/* 148 */     if (!rta.isRta()) {
/* 149 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaMetasProyecto&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 152 */     String sPagina = "PoaMetasProyecto.po?_operacion=P&codigoMetaPlan=" + codigoMetaPlan + "&CODIGOMETAPROYECTO=" + CODIGOMETAPROYECTO + "";
/* 153 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 164 */     int codigoMetaPlan = 0;
/*     */     try {
/* 166 */       codigoMetaPlan = Integer.parseInt(comms.request.getParameter("codigoMetaPlan"));
/*     */     }
/* 168 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 171 */     int CODIGOMETAPROYECTO = 0;
/*     */     try {
/* 173 */       CODIGOMETAPROYECTO = Integer.parseInt(comms.request.getParameter("CODIGOMETAPROYECTO"));
/*     */     }
/* 175 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 178 */     PoaMetasProyectoDAO rs = new PoaMetasProyectoDAO();
/* 179 */     if (!rs.getEstadoConexion()) {
/* 180 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 182 */     PoaMetasProyectoDTO reg = rs.cargarRegistro(codigoMetaPlan, CODIGOMETAPROYECTO);
/*     */     
/* 184 */     rs.close();
/* 185 */     if (reg != null) {
/* 186 */       this.pagHTML.getElementCODIGOMETAPROYECTO().setValue("" + reg.getCODIGOMETAPROYECTO());
/* 187 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 188 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 189 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 190 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 191 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 192 */       HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 193 */       comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), false);
/*     */ 
/*     */       
/* 196 */       this.pagHTML.getElementCODIGOMETAPROYECTO().setReadOnly(true);
/*     */     } 
/* 198 */     this.pagHTML.getElement_operacion().setValue("M");
/* 199 */     activarVista("nuevo");
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
/* 211 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 213 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 214 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 216 */     catch (Exception e) {}
/*     */     
/* 218 */     activarVista("nuevo");
/* 219 */     HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 220 */     comboMultivalores(combo, "ESTADO_REGISTRO", "", false);
/*     */     
/* 222 */     this.pagHTML.getElementCODIGOMETAPROYECTO().setReadOnly(true);
/* 223 */     this.pagHTML.getElementCODIGOMETAPROYECTO().setValue("0");
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
/* 234 */     activarVista("consulta");
/* 235 */     int codigoMetaPlan = 0;
/*     */     try {
/* 237 */       codigoMetaPlan = Integer.parseInt(comms.request.getParameter("codigoMetaPlan"));
/*     */     }
/* 239 */     catch (Exception e) {}
/*     */ 
/*     */ 
/*     */     
/* 243 */     String descripcion = comms.request.getParameter("descripcion");
/* 244 */     if (descripcion == null) {
/* 245 */       descripcion = "";
/*     */     }
/* 247 */     String estado = comms.request.getParameter("estado");
/* 248 */     if (estado == null) {
/* 249 */       estado = "";
/*     */     }
/* 251 */     HTMLSelectElement combo = this.pagHTML.getElementFestado();
/* 252 */     comboMultivalores(combo, "ESTADO_REGISTRO", "" + estado, true);
/*     */ 
/*     */     
/* 255 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 260 */     PoaMetasProyectoDAO rs = new PoaMetasProyectoDAO();
/* 261 */     if (!rs.getEstadoConexion()) {
/* 262 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 264 */     Collection<PoaMetasProyectoDTO> arr = rs.cargarTodos(codigoMetaPlan, descripcion, estado);
/*     */ 
/*     */ 
/*     */     
/* 268 */     rs.close();
/* 269 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 270 */     int cuantas = 0;
/* 271 */     Iterator<PoaMetasProyectoDTO> iterator = arr.iterator();
/* 272 */     while (iterator.hasNext()) {
/* 273 */       PoaMetasProyectoDTO reg = (PoaMetasProyectoDTO)iterator.next();
/* 274 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 275 */       eltr.appendChild(newtd("" + reg.getCodigoMetaPlan()));
/* 276 */       String url = "PoaMetasProyecto.po?_operacion=V&codigoMetaPlan=" + reg.getCodigoMetaPlan() + "&CODIGOMETAPROYECTO=" + reg.getCODIGOMETAPROYECTO() + "";
/* 277 */       eltr.appendChild(newtdhref("" + reg.getCODIGOMETAPROYECTO(), url));
/* 278 */       eltr.appendChild(newtd("" + reg.getDescripcion()));
/* 279 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 280 */       hte.appendChild(eltr);
/* 281 */       cuantas++;
/*     */     } 
/* 283 */     arr.clear();
/* 284 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 297 */     int codigoMetaPlan = 0;
/*     */     try {
/* 299 */       codigoMetaPlan = Integer.parseInt(comms.request.getParameter("codigoMetaPlan"));
/*     */     }
/* 301 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 304 */     int CODIGOMETAPROYECTO = 0;
/*     */     try {
/* 306 */       CODIGOMETAPROYECTO = Integer.parseInt(comms.request.getParameter("CODIGOMETAPROYECTO"));
/*     */     }
/* 308 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 311 */     PoaMetasProyectoDAO rs = new PoaMetasProyectoDAO();
/* 312 */     if (!rs.getEstadoConexion()) {
/* 313 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 315 */     PoaMetasProyectoDTO reg = rs.cargarRegistro(codigoMetaPlan, CODIGOMETAPROYECTO);
/*     */     
/* 317 */     rs.close();
/* 318 */     if (reg != null) {
/* 319 */       this.pagHTML.setTextCodigoMetaPlanEd("" + reg.getCodigoMetaPlan());
/* 320 */       this.pagHTML.setTextCODIGOMETAPROYECTOEd("" + reg.getCODIGOMETAPROYECTO());
/* 321 */       this.pagHTML.setTextDescripcionEd("" + reg.getDescripcion());
/* 322 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/* 323 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 324 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 325 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 326 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 328 */       this.pagHTML.getElementCodigoMetaPlanKey().setValue("" + reg.getCodigoMetaPlan());
/* 329 */       this.pagHTML.getElementCODIGOMETAPROYECTOKey().setValue("" + reg.getCODIGOMETAPROYECTO());
/* 330 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 332 */     activarVista("editar");
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
/* 343 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 345 */     Varios oVarios = new Varios();
/* 346 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Poa_PoaMetasProyectoAct");
/* 347 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Poa_PoaMetasProyectoDel");
/* 348 */     if (!oPermisoAct) {
/* 349 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 350 */       elem.getParentNode().removeChild(elem);
/* 351 */       elem = this.pagHTML.getElementBtnGrabar();
/* 352 */       elem.getParentNode().removeChild(elem);
/* 353 */       elem = this.pagHTML.getElementBtnModificar();
/* 354 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 356 */     if (!oPermisoDel) {
/* 357 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 358 */       elem.getParentNode().removeChild(elem);
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
/* 369 */     if (!vista.equals("nuevo")) {
/* 370 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 371 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 373 */     if (!vista.equals("editar")) {
/* 374 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 375 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 377 */     if (!vista.equals("consulta")) {
/* 378 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 379 */       sel.getParentNode().removeChild(sel);
/* 380 */       sel = this.pagHTML.getElementDivResultados();
/* 381 */       sel.getParentNode().removeChild(sel);
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
/* 395 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 396 */     atrib.setValue(valor);
/* 397 */     return atrib;
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
/* 410 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 411 */     Element enlace = this.pagHTML.createElement("a");
/* 412 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 413 */     enlace.appendChild(hijo);
/* 414 */     Attr donde = this.pagHTML.createAttribute("href");
/* 415 */     donde.setValue(vinculo);
/* 416 */     enlace.setAttributeNode(donde);
/* 417 */     td.appendChild(enlace);
/* 418 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 419 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 429 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 430 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 431 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 432 */     return td;
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
/* 447 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 448 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 449 */     rs.close();
/* 450 */     if (dejarBlanco) {
/* 451 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 452 */       op.setValue("");
/* 453 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 454 */       combo.appendChild(op);
/*     */     } 
/* 456 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 457 */     while (iterator.hasNext()) {
/* 458 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 459 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 460 */       op.setValue("" + reg.getCodigo());
/* 461 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 462 */       if (defecto.equals(reg.getCodigo())) {
/* 463 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 464 */         escogida.setValue("on");
/* 465 */         op.setAttributeNode(escogida);
/*     */       } 
/* 467 */       combo.appendChild(op);
/*     */     } 
/* 469 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PoaMetasProyecto.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */