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
/*     */ import sinco.business.PoaCiclosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.data.PoaCiclosDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.PoaCiclos;
/*     */ import sinco.presentation.PoaCiclosHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PoaCiclos
/*     */   implements HttpPresentation
/*     */ {
/*     */   private PoaCiclosHTML pagHTML;
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
/*  51 */     this.pagHTML = (PoaCiclosHTML)comms.xmlcFactory.create(PoaCiclosHTML.class);
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
/*  83 */     int codigoCiclo = 0;
/*     */     try {
/*  85 */       codigoCiclo = Integer.parseInt(comms.request.getParameter("codigoCiclo"));
/*     */     }
/*  87 */     catch (Exception e) {
/*  88 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoCiclo"));
/*     */     } 
/*     */     
/*  91 */     RespuestaBD rta = new RespuestaBD();
/*  92 */     if (_operacion.equals("E")) {
/*  93 */       PoaCiclosDAO rs = new PoaCiclosDAO();
/*  94 */       if (!rs.getEstadoConexion()) {
/*  95 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*  97 */       rta = rs.eliminarRegistro(codigoCiclo);
/*  98 */       if (!rta.isRta()) {
/*  99 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaCiclos&p1=" + rta.getMensaje()));
/*     */       }
/* 101 */       rs.close();
/* 102 */       String sPagina = "PoaCiclos.po?_operacion=X";
/* 103 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 105 */     String descripcion = comms.request.getParameter("descripcion");
/* 106 */     String estado = comms.request.getParameter("estado");
/* 107 */     PoaCiclosDAO rs = new PoaCiclosDAO();
/* 108 */     if (!rs.getEstadoConexion()) {
/* 109 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 111 */     if (_operacion.equals("C")) {
/* 112 */       rta = rs.crearRegistro(codigoCiclo, descripcion, estado, elUsuario);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 119 */       rta = rs.modificarRegistro(codigoCiclo, descripcion, estado, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 125 */     rs.close();
/* 126 */     if (!rta.isRta()) {
/* 127 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaCiclos&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 130 */     String sPagina = "PoaCiclos.po?_operacion=P&codigoCiclo=" + codigoCiclo + "";
/* 131 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 142 */     int codigoCiclo = 0;
/*     */     try {
/* 144 */       codigoCiclo = Integer.parseInt(comms.request.getParameter("codigoCiclo"));
/*     */     }
/* 146 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 149 */     PoaCiclosDAO rs = new PoaCiclosDAO();
/* 150 */     if (!rs.getEstadoConexion()) {
/* 151 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 153 */     PoaCiclosDTO reg = rs.cargarRegistro(codigoCiclo);
/* 154 */     rs.close();
/* 155 */     if (reg != null) {
/* 156 */       this.pagHTML.getElementCodigoCiclo().setValue("" + reg.getCodigoCiclo());
/* 157 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 158 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 159 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 160 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 161 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 162 */       HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 163 */       comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), false);
/*     */ 
/*     */       
/* 166 */       this.pagHTML.getElementCodigoCiclo().setReadOnly(true);
/*     */     } 
/* 168 */     this.pagHTML.getElement_operacion().setValue("M");
/* 169 */     activarVista("nuevo");
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
/* 181 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 183 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 184 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 186 */     catch (Exception e) {}
/*     */     
/* 188 */     activarVista("nuevo");
/* 189 */     HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 190 */     comboMultivalores(combo, "ESTADO_REGISTRO", "", false);
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
/* 202 */     activarVista("consulta");
/* 203 */     String descripcion = comms.request.getParameter("descripcion");
/* 204 */     if (descripcion == null) {
/* 205 */       descripcion = "";
/*     */     }
/* 207 */     String estado = comms.request.getParameter("estado");
/* 208 */     if (estado == null) {
/* 209 */       estado = "";
/*     */     }
/* 211 */     HTMLSelectElement combo = this.pagHTML.getElementFestado();
/* 212 */     comboMultivalores(combo, "ESTADO_REGISTRO", "" + estado, true);
/*     */ 
/*     */     
/* 215 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 220 */     PoaCiclosDAO rs = new PoaCiclosDAO();
/* 221 */     if (!rs.getEstadoConexion()) {
/* 222 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 224 */     Collection<PoaCiclosDTO> arr = rs.cargarTodos(descripcion, estado);
/*     */ 
/*     */     
/* 227 */     rs.close();
/* 228 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 229 */     int cuantas = 0;
/* 230 */     Iterator<PoaCiclosDTO> iterator = arr.iterator();
/* 231 */     while (iterator.hasNext()) {
/* 232 */       PoaCiclosDTO reg = (PoaCiclosDTO)iterator.next();
/* 233 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 234 */       eltr.appendChild(newtd("" + reg.getCodigoCiclo()));
/* 235 */       String url = "PoaCiclos.po?_operacion=V&codigoCiclo=" + reg.getCodigoCiclo() + "";
/* 236 */       eltr.appendChild(newtdhref("" + reg.getDescripcion(), url));
/* 237 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 238 */       hte.appendChild(eltr);
/* 239 */       cuantas++;
/*     */     } 
/* 241 */     arr.clear();
/* 242 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 255 */     int codigoCiclo = 0;
/*     */     try {
/* 257 */       codigoCiclo = Integer.parseInt(comms.request.getParameter("codigoCiclo"));
/*     */     }
/* 259 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 262 */     PoaCiclosDAO rs = new PoaCiclosDAO();
/* 263 */     if (!rs.getEstadoConexion()) {
/* 264 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 266 */     PoaCiclosDTO reg = rs.cargarRegistro(codigoCiclo);
/* 267 */     rs.close();
/* 268 */     if (reg != null) {
/* 269 */       this.pagHTML.setTextCodigoCicloEd("" + reg.getCodigoCiclo());
/* 270 */       this.pagHTML.setTextDescripcionEd("" + reg.getDescripcion());
/* 271 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/* 272 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 273 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 274 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 275 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 277 */       this.pagHTML.getElementCodigoCicloKey().setValue("" + reg.getCodigoCiclo());
/* 278 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 280 */     activarVista("editar");
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
/* 291 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 293 */     Varios oVarios = new Varios();
/* 294 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Poa_PoaCiclosAct");
/* 295 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Poa_PoaCiclosDel");
/* 296 */     if (!oPermisoAct) {
/* 297 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 298 */       elem.getParentNode().removeChild(elem);
/* 299 */       elem = this.pagHTML.getElementBtnGrabar();
/* 300 */       elem.getParentNode().removeChild(elem);
/* 301 */       elem = this.pagHTML.getElementBtnModificar();
/* 302 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 304 */     if (!oPermisoDel) {
/* 305 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 306 */       elem.getParentNode().removeChild(elem);
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
/* 317 */     if (!vista.equals("nuevo")) {
/* 318 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 319 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 321 */     if (!vista.equals("editar")) {
/* 322 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 323 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 325 */     if (!vista.equals("consulta")) {
/* 326 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 327 */       sel.getParentNode().removeChild(sel);
/* 328 */       sel = this.pagHTML.getElementDivResultados();
/* 329 */       sel.getParentNode().removeChild(sel);
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
/* 343 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 344 */     atrib.setValue(valor);
/* 345 */     return atrib;
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
/* 358 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 359 */     Element enlace = this.pagHTML.createElement("a");
/* 360 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 361 */     enlace.appendChild(hijo);
/* 362 */     Attr donde = this.pagHTML.createAttribute("href");
/* 363 */     donde.setValue(vinculo);
/* 364 */     enlace.setAttributeNode(donde);
/* 365 */     td.appendChild(enlace);
/* 366 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 367 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 377 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 378 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 379 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 380 */     return td;
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
/* 395 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 396 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 397 */     rs.close();
/* 398 */     if (dejarBlanco) {
/* 399 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 400 */       op.setValue("");
/* 401 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 402 */       combo.appendChild(op);
/*     */     } 
/* 404 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 405 */     while (iterator.hasNext()) {
/* 406 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 407 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 408 */       op.setValue("" + reg.getCodigo());
/* 409 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 410 */       if (defecto.equals(reg.getCodigo())) {
/* 411 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 412 */         escogida.setValue("on");
/* 413 */         op.setAttributeNode(escogida);
/*     */       } 
/* 415 */       combo.appendChild(op);
/*     */     } 
/* 417 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PoaCiclos.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */