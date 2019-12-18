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
/*     */ import sinco.business.PrcRecursoDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.PrcRecursoDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.PrcRecurso;
/*     */ import sinco.presentation.PrcRecursoHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PrcRecurso
/*     */   implements HttpPresentation
/*     */ {
/*     */   private PrcRecursoHTML pagHTML;
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
/*  53 */     this.pagHTML = (PrcRecursoHTML)comms.xmlcFactory.create(PrcRecursoHTML.class);
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
/*  85 */     int idRecurso = 0;
/*     */     try {
/*  87 */       idRecurso = Integer.parseInt(comms.request.getParameter("idRecurso"));
/*     */     }
/*  89 */     catch (Exception e) {
/*  90 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idRecurso"));
/*     */     } 
/*     */     
/*  93 */     RespuestaBD rta = new RespuestaBD();
/*  94 */     if (_operacion.equals("E")) {
/*  95 */       PrcRecursoDAO ob = new PrcRecursoDAO();
/*  96 */       rta = ob.eliminarRegistro(idRecurso);
/*  97 */       if (!rta.isRta()) {
/*  98 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPrcRecurso&p1=" + rta.getMensaje()));
/*     */       }
/* 100 */       String sPagina = "PrcRecurso.po?_operacion=X";
/* 101 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 103 */     String idTipoRecurso = comms.request.getParameter("idTipoRecurso");
/* 104 */     String descripcionRecurso = comms.request.getParameter("descripcionRecurso");
/* 105 */     int idProcedimiento = 0;
/*     */     try {
/* 107 */       idProcedimiento = Integer.parseInt(comms.request.getParameter("idProcedimiento"));
/*     */     }
/* 109 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 112 */     String estado = comms.request.getParameter("estado");
/* 113 */     PrcRecursoDAO ob = new PrcRecursoDAO();
/* 114 */     if (_operacion.equals("C")) {
/* 115 */       rta = ob.crearRegistro(idRecurso, idTipoRecurso, descripcionRecurso, idProcedimiento, estado, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 122 */       idRecurso = rta.getSecuencia();
/*     */     } else {
/*     */       
/* 125 */       rta = ob.modificarRegistro(idRecurso, idTipoRecurso, descripcionRecurso, idProcedimiento, estado, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 133 */     if (!rta.isRta()) {
/* 134 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPrcRecurso&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 137 */     String sPagina = "PrcRecurso.po?_operacion=P&idRecurso=" + idRecurso + "";
/* 138 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 149 */     int idRecurso = 0;
/*     */     try {
/* 151 */       idRecurso = Integer.parseInt(comms.request.getParameter("idRecurso"));
/*     */     }
/* 153 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 156 */     PrcRecursoDAO ob = new PrcRecursoDAO();
/* 157 */     PrcRecursoDTO reg = ob.cargarRegistro(idRecurso);
/* 158 */     if (reg != null) {
/* 159 */       this.pagHTML.getElementIdRecurso().setValue("" + reg.getIdRecurso());
/* 160 */       this.pagHTML.getElementDescripcionRecurso().setValue("" + reg.getDescripcionRecurso());
/* 161 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 162 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 163 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 164 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 165 */       HTMLSelectElement combo = this.pagHTML.getElementIdTipoRecurso();
/* 166 */       comboMultivalores(combo, "tipo_recurso", "" + reg.getIdTipoRecurso(), true);
/*     */       
/* 168 */       combo = this.pagHTML.getElementIdProcedimiento();
/* 169 */       llenarCombo(combo, "prc_procedimientos", "id_procedimiento", "objetivo", "1=1", "" + reg.getIdProcedimiento(), true);
/*     */       
/* 171 */       combo = this.pagHTML.getElementEstado();
/* 172 */       comboMultivalores(combo, "estado_activo_inactivo", "" + reg.getEstado(), true);
/*     */ 
/*     */       
/* 175 */       this.pagHTML.getElementIdRecurso().setReadOnly(true);
/*     */     } 
/* 177 */     this.pagHTML.getElement_operacion().setValue("M");
/* 178 */     activarVista("nuevo");
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
/* 190 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 192 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 193 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 195 */     catch (Exception e) {}
/*     */     
/* 197 */     activarVista("nuevo");
/* 198 */     HTMLSelectElement combo = this.pagHTML.getElementIdTipoRecurso();
/* 199 */     comboMultivalores(combo, "tipo_recurso", "", true);
/*     */     
/* 201 */     combo = this.pagHTML.getElementIdProcedimiento();
/* 202 */     llenarCombo(combo, "prc_procedimientos", "id_procedimiento", "objetivo", "1=1", "", true);
/*     */     
/* 204 */     combo = this.pagHTML.getElementEstado();
/* 205 */     comboMultivalores(combo, "estado_activo_inactivo", "", true);
/*     */     
/* 207 */     this.pagHTML.getElementIdRecurso().setReadOnly(true);
/* 208 */     this.pagHTML.getElementIdRecurso().setValue("0");
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
/* 219 */     activarVista("consulta");
/* 220 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 225 */     PrcRecursoDAO ob = new PrcRecursoDAO();
/* 226 */     Collection<PrcRecursoDTO> arr = ob.cargarTodos();
/* 227 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 228 */     int cuantas = 0;
/* 229 */     Iterator<PrcRecursoDTO> iterator = arr.iterator();
/* 230 */     while (iterator.hasNext()) {
/* 231 */       PrcRecursoDTO reg = (PrcRecursoDTO)iterator.next();
/* 232 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 233 */       eltr.appendChild(newtd("" + reg.getIdRecurso()));
/* 234 */       String url = "PrcRecurso.po?_operacion=V&idRecurso=" + reg.getIdRecurso() + "";
/* 235 */       eltr.appendChild(newtdhref("" + reg.getNombreIdTipoRecurso(), url));
/* 236 */       eltr.appendChild(newtd("" + reg.getDescripcionRecurso()));
/* 237 */       eltr.appendChild(newtd("" + reg.getNombreIdProcedimiento()));
/* 238 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 239 */       hte.appendChild(eltr);
/* 240 */       cuantas++;
/*     */     } 
/* 242 */     arr.clear();
/* 243 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 256 */     int idRecurso = 0;
/*     */     try {
/* 258 */       idRecurso = Integer.parseInt(comms.request.getParameter("idRecurso"));
/*     */     }
/* 260 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 263 */     PrcRecursoDAO ob = new PrcRecursoDAO();
/* 264 */     PrcRecursoDTO reg = ob.cargarRegistro(idRecurso);
/* 265 */     if (reg != null) {
/* 266 */       this.pagHTML.setTextIdRecursoEd("" + reg.getIdRecurso());
/* 267 */       this.pagHTML.setTextIdTipoRecursoEd("" + reg.getNombreIdTipoRecurso());
/* 268 */       this.pagHTML.setTextDescripcionRecursoEd("" + reg.getDescripcionRecurso());
/* 269 */       this.pagHTML.setTextIdProcedimientoEd("" + reg.getNombreIdProcedimiento());
/* 270 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/* 271 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 272 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/* 273 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/*     */       
/* 275 */       this.pagHTML.getElementIdRecursoKey().setValue("" + reg.getIdRecurso());
/* 276 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 278 */     activarVista("editar");
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
/* 289 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 291 */     Varios oVarios = new Varios();
/* 292 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "admPrcRecursoAct");
/* 293 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "admPrcRecursoDel");
/* 294 */     if (!oPermisoAct) {
/* 295 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 296 */       elem.getParentNode().removeChild(elem);
/* 297 */       elem = this.pagHTML.getElementBtnGrabar();
/* 298 */       elem.getParentNode().removeChild(elem);
/* 299 */       elem = this.pagHTML.getElementBtnModificar();
/* 300 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 302 */     if (!oPermisoDel) {
/* 303 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 304 */       elem.getParentNode().removeChild(elem);
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
/* 315 */     if (!vista.equals("nuevo")) {
/* 316 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 317 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 319 */     if (!vista.equals("editar")) {
/* 320 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 321 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 323 */     if (!vista.equals("consulta")) {
/* 324 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 325 */       sel.getParentNode().removeChild(sel);
/* 326 */       sel = this.pagHTML.getElementDivResultados();
/* 327 */       sel.getParentNode().removeChild(sel);
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
/* 341 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 342 */     atrib.setValue(valor);
/* 343 */     return atrib;
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
/* 356 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 357 */     Element enlace = this.pagHTML.createElement("a");
/* 358 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 359 */     enlace.appendChild(hijo);
/* 360 */     Attr donde = this.pagHTML.createAttribute("href");
/* 361 */     donde.setValue(vinculo);
/* 362 */     enlace.setAttributeNode(donde);
/* 363 */     td.appendChild(enlace);
/* 364 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 365 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 375 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 376 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 377 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 378 */     return td;
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
/* 393 */     SisMultiValoresDAO ob = new SisMultiValoresDAO();
/* 394 */     Collection<SisMultiValoresDTO> arr = ob.cargarTabla(tabla);
/* 395 */     ob.close();
/* 396 */     if (dejarBlanco) {
/* 397 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 398 */       op.setValue("");
/* 399 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 400 */       combo.appendChild(op);
/*     */     } 
/* 402 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 403 */     while (iterator.hasNext()) {
/* 404 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 405 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 406 */       op.setValue("" + reg.getCodigo());
/* 407 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 408 */       if (defecto.equals(reg.getCodigo())) {
/* 409 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 410 */         escogida.setValue("on");
/* 411 */         op.setAttributeNode(escogida);
/*     */       } 
/* 413 */       combo.appendChild(op);
/*     */     } 
/* 415 */     arr.clear();
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
/* 436 */     if (dejarBlanco) {
/* 437 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 438 */       op.setValue("");
/* 439 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 440 */       combo.appendChild(op);
/*     */     } 
/* 442 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 443 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/* 444 */     rsTGen.close();
/* 445 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 446 */     while (iterator.hasNext()) {
/* 447 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 448 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 449 */       op.setValue("" + regGeneral.getCodigoS());
/* 450 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 451 */       if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
/* 452 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 453 */         escogida.setValue("on");
/* 454 */         op.setAttributeNode(escogida);
/*     */       } 
/* 456 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PrcRecurso.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */