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
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.SisMultiValores;
/*     */ import sinco.presentation.SisMultiValoresHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SisMultiValores
/*     */   implements HttpPresentation
/*     */ {
/*     */   private SisMultiValoresHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  35 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  36 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  39 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  40 */     String _operacion = comms.request.getParameter("_operacion");
/*  41 */     if (_operacion == null || _operacion.length() == 0) {
/*  42 */       _operacion = "X";
/*     */     }
/*     */     
/*  45 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  46 */       creacion(comms);
/*     */     }
/*     */     
/*  49 */     this.pagHTML = (SisMultiValoresHTML)comms.xmlcFactory.create(SisMultiValoresHTML.class);
/*  50 */     permisos(comms);
/*     */     
/*  52 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  53 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  56 */     if (_operacion.equals("P")) {
/*  57 */       editar(comms);
/*     */     }
/*  59 */     else if (_operacion.equals("Nuevo")) {
/*  60 */       nuevo(comms);
/*     */     } 
/*     */     
/*  63 */     if (_operacion.equals("V")) {
/*  64 */       verRegistro(comms);
/*     */     }
/*  66 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  67 */     comms.response.writeDOM(this.pagHTML);
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
/*  79 */     String _operacion = comms.request.getParameter("_operacion");
/*  80 */     String elUsuario = "" + comms.session.getUser().getName();
/*  81 */     String tabla = comms.request.getParameter("tabla");
/*  82 */     if (tabla == null) {
/*  83 */       tabla = "";
/*     */     }
/*  85 */     String valor = comms.request.getParameter("valor");
/*  86 */     if (valor == null) {
/*  87 */       valor = "";
/*     */     }
/*  89 */     RespuestaBD rta = new RespuestaBD();
/*  90 */     if (_operacion.equals("E")) {
/*  91 */       SisMultiValoresDAO rs = new SisMultiValoresDAO();
/*  92 */       if (!rs.getEstadoConexion()) {
/*  93 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*  95 */       rta = rs.eliminarRegistro(tabla, valor);
/*     */       
/*  97 */       if (!rta.isRta()) {
/*  98 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSisMultiValores&p1=" + rta.getMensaje()));
/*     */       }
/* 100 */       rs.close();
/* 101 */       String sPagina = "SisMultiValores.po?_operacion=X";
/* 102 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 104 */     String descripcion = comms.request.getParameter("descripcion");
/* 105 */     String estado = comms.request.getParameter("estado");
/* 106 */     String varios = comms.request.getParameter("varios");
/* 107 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 108 */     if (!rs.getEstadoConexion()) {
/* 109 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 111 */     if (_operacion.equals("C")) {
/* 112 */       rta = rs.crearRegistro(tabla, valor, descripcion, estado, varios, elUsuario);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 121 */       rta = rs.modificarRegistro(tabla, valor, descripcion, estado, varios, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     rs.close();
/* 130 */     if (!rta.isRta()) {
/* 131 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSisMultiValores&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 134 */     String sPagina = "SisMultiValores.po?_operacion=P&tabla=" + tabla + "&valor=" + valor + "";
/* 135 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 146 */     String tabla = comms.request.getParameter("tabla");
/* 147 */     String valor = comms.request.getParameter("valor");
/* 148 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 149 */     if (!rs.getEstadoConexion()) {
/* 150 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 152 */     SisMultiValoresDTO reg = rs.cargarRegistro(tabla, valor);
/*     */     
/* 154 */     rs.close();
/* 155 */     if (reg != null) {
/* 156 */       this.pagHTML.getElementTabla().setValue("" + reg.getTabla());
/* 157 */       this.pagHTML.getElementValor().setValue("" + reg.getValor());
/* 158 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 159 */       this.pagHTML.getElementVarios().setValue("" + reg.getVarios());
/* 160 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 161 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 162 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 163 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 164 */       HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 165 */       comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), true);
/*     */ 
/*     */       
/* 168 */       this.pagHTML.getElementTabla().setReadOnly(true);
/* 169 */       this.pagHTML.getElementValor().setReadOnly(true);
/*     */     } 
/* 171 */     this.pagHTML.getElement_operacion().setValue("M");
/* 172 */     activarVista("nuevo");
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
/* 184 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 186 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 187 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 189 */     catch (Exception e) {}
/*     */     
/* 191 */     activarVista("nuevo");
/* 192 */     HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 193 */     comboMultivalores(combo, "ESTADO_REGISTRO", "A", true);
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
/* 205 */     activarVista("consulta");
/*     */     
/* 207 */     String tabla = comms.request.getParameter("tabla");
/* 208 */     if (tabla == null) {
/* 209 */       tabla = "";
/*     */     }
/* 211 */     String descripcion = comms.request.getParameter("descripcion");
/* 212 */     if (descripcion == null) {
/* 213 */       descripcion = "";
/*     */     }
/* 215 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 220 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 221 */     if (!rs.getEstadoConexion()) {
/* 222 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 224 */     Collection<SisMultiValoresDTO> arr = rs.cargarTodos(tabla, descripcion);
/*     */ 
/*     */     
/* 227 */     rs.close();
/* 228 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 229 */     int cuantas = 0;
/* 230 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 231 */     while (iterator.hasNext()) {
/* 232 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 233 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 234 */       String url = "SisMultiValores.po?_operacion=V&tabla=" + reg.getTabla() + "&valor=" + reg.getValor() + "";
/* 235 */       eltr.appendChild(newtdhref("" + reg.getTabla(), url));
/* 236 */       eltr.appendChild(newtd("" + reg.getValor()));
/* 237 */       eltr.appendChild(newtd("" + reg.getDescripcion()));
/* 238 */       eltr.appendChild(newtd("" + reg.getEstado()));
/* 239 */       eltr.appendChild(newtd("" + reg.getVarios()));
/* 240 */       hte.appendChild(eltr);
/* 241 */       cuantas++;
/*     */     } 
/* 243 */     arr.clear();
/* 244 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/*     */   private void listarTabla(String tabla) throws HttpPresentationException, KeywordValueException {
/* 258 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 259 */     if (!rs.getEstadoConexion()) {
/*     */       return;
/*     */     }
/* 262 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 263 */     rs.close();
/*     */     
/* 265 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalleTabla();
/* 266 */     int cuantas = 0;
/* 267 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 268 */     while (iterator.hasNext()) {
/* 269 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 270 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/* 272 */       String url = "SisMultiValores.po?_operacion=V&tabla=" + reg.getTabla() + "&valor=" + reg.getValor() + "";
/* 273 */       eltr.appendChild(newtdhref("" + reg.getValor(), url));
/* 274 */       eltr.appendChild(newtdhref("" + reg.getDescripcion(), url));
/* 275 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 276 */       eltr.appendChild(newtd("" + reg.getVarios()));
/* 277 */       hte.appendChild(eltr);
/* 278 */       cuantas++;
/*     */     } 
/* 280 */     arr.clear();
/* 281 */     this.pagHTML.setTextNroRegistrosTabla("" + cuantas);
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
/*     */   private void verRegistro(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 295 */     String tabla = comms.request.getParameter("tabla");
/* 296 */     String valor = comms.request.getParameter("valor");
/* 297 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 298 */     if (!rs.getEstadoConexion()) {
/* 299 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 301 */     SisMultiValoresDTO reg = rs.cargarRegistro(tabla, valor);
/*     */     
/* 303 */     rs.close();
/* 304 */     if (reg != null) {
/* 305 */       this.pagHTML.setTextTablaEd("" + reg.getTabla());
/* 306 */       this.pagHTML.setTextValorEd("" + reg.getValor());
/* 307 */       this.pagHTML.setTextDescripcionEd("" + reg.getDescripcion());
/* 308 */       this.pagHTML.setTextEstadoEd("" + reg.getEstado());
/* 309 */       this.pagHTML.setTextVariosEd("" + reg.getVarios());
/* 310 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 311 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 312 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 313 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 315 */       this.pagHTML.getElementTablaKey().setValue("" + reg.getTabla());
/* 316 */       this.pagHTML.getElementValorKey().setValue("" + reg.getValor());
/* 317 */       this.pagHTML.getElement_operacion().setValue("P");
/* 318 */       listarTabla(reg.getTabla());
/*     */     } 
/* 320 */     activarVista("editar");
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
/* 331 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 333 */     Varios oVarios = new Varios();
/* 334 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Adm_SisMultiValoresAct");
/* 335 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Adm_SisMultiValoresDel");
/* 336 */     if (!oPermisoAct) {
/* 337 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 338 */       elem.getParentNode().removeChild(elem);
/* 339 */       elem = this.pagHTML.getElementBtnGrabar();
/* 340 */       elem.getParentNode().removeChild(elem);
/* 341 */       elem = this.pagHTML.getElementBtnModificar();
/* 342 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 344 */     if (!oPermisoDel) {
/* 345 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 346 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void activarVista(String vista) throws HttpPresentationException, KeywordValueException {
/* 357 */     if (!vista.equals("nuevo")) {
/* 358 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 359 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 361 */     if (!vista.equals("editar")) {
/* 362 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 363 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 365 */     if (!vista.equals("consulta")) {
/* 366 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 367 */       sel.getParentNode().removeChild(sel);
/* 368 */       sel = this.pagHTML.getElementDivResultados();
/* 369 */       sel.getParentNode().removeChild(sel);
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
/* 383 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 384 */     atrib.setValue(valor);
/* 385 */     return atrib;
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
/* 398 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 399 */     Element enlace = this.pagHTML.createElement("a");
/* 400 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 401 */     enlace.appendChild(hijo);
/* 402 */     Attr donde = this.pagHTML.createAttribute("href");
/* 403 */     donde.setValue(vinculo);
/* 404 */     enlace.setAttributeNode(donde);
/* 405 */     td.appendChild(enlace);
/* 406 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 407 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 417 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 418 */     td.appendChild(this.pagHTML.createTextNode(contenido));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void comboMultivalores(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco) {
/* 435 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 436 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 437 */     rs.close();
/* 438 */     if (dejarBlanco) {
/* 439 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 440 */       op.setValue("");
/* 441 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 442 */       combo.appendChild(op);
/*     */     } 
/* 444 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 445 */     while (iterator.hasNext()) {
/* 446 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 447 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 448 */       op.setValue("" + reg.getCodigo());
/* 449 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 450 */       if (defecto.equals(reg.getCodigo())) {
/* 451 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 452 */         escogida.setValue("on");
/* 453 */         op.setAttributeNode(escogida);
/*     */       } 
/* 455 */       combo.appendChild(op);
/*     */     } 
/* 457 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SisMultiValores.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */