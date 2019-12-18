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
/*     */ import sinco.business.SisPermisosDTO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.SisPermisosDAO;
/*     */ import sinco.presentation.SisPermisos;
/*     */ import sinco.presentation.SisPermisosHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SisPermisos
/*     */   implements HttpPresentation
/*     */ {
/*     */   private SisPermisosHTML pagHTML;
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
/*  51 */     this.pagHTML = (SisPermisosHTML)comms.xmlcFactory.create(SisPermisosHTML.class);
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
/*  83 */     String codigo = comms.request.getParameter("codigo");
/*  84 */     if (codigo == null) {
/*  85 */       codigo = "";
/*     */     }
/*  87 */     RespuestaBD rta = new RespuestaBD();
/*  88 */     if (_operacion.equals("E")) {
/*  89 */       SisPermisosDAO rs = new SisPermisosDAO();
/*  90 */       if (!rs.getEstadoConexion()) {
/*  91 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*  93 */       rta = rs.eliminarRegistro(codigo);
/*  94 */       if (!rta.isRta()) {
/*  95 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSisPermisos&p1=" + rta.getMensaje()));
/*     */       }
/*  97 */       rs.close();
/*  98 */       String sPagina = "SisPermisos.po?_operacion=X";
/*  99 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 101 */     String nombre = comms.request.getParameter("nombre");
/* 102 */     String estado = comms.request.getParameter("estado");
/* 103 */     SisPermisosDAO rs = new SisPermisosDAO();
/* 104 */     if (!rs.getEstadoConexion()) {
/* 105 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 107 */     if (_operacion.equals("C")) {
/* 108 */       rta = rs.crearRegistro(codigo, nombre, estado, elUsuario);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 115 */       rta = rs.modificarRegistro(codigo, nombre, estado, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 121 */     rs.close();
/* 122 */     if (!rta.isRta()) {
/* 123 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSisPermisos&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 126 */     String sPagina = "SisPermisos.po?_operacion=P&codigo=" + codigo + "";
/* 127 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 138 */     String codigo = comms.request.getParameter("codigo");
/* 139 */     SisPermisosDAO rs = new SisPermisosDAO();
/* 140 */     if (!rs.getEstadoConexion()) {
/* 141 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 143 */     SisPermisosDTO reg = rs.cargarRegistro(codigo);
/* 144 */     rs.close();
/* 145 */     if (reg != null) {
/* 146 */       this.pagHTML.getElementCodigo().setValue("" + reg.getCodigo());
/* 147 */       this.pagHTML.getElementNombre().setValue("" + reg.getNombre());
/* 148 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 149 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 150 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 151 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 152 */       HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 153 */       comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), true);
/*     */ 
/*     */       
/* 156 */       this.pagHTML.getElementCodigo().setReadOnly(true);
/*     */     } 
/* 158 */     this.pagHTML.getElement_operacion().setValue("M");
/* 159 */     activarVista("nuevo");
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
/* 171 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 173 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 174 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 176 */     catch (Exception e) {}
/*     */     
/* 178 */     activarVista("nuevo");
/* 179 */     HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 180 */     comboMultivalores(combo, "ESTADO_REGISTRO", "", true);
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
/* 192 */     activarVista("consulta");
/* 193 */     String codigo = comms.request.getParameter("codigo");
/* 194 */     if (codigo == null) {
/* 195 */       codigo = "";
/*     */     }
/* 197 */     String nombre = comms.request.getParameter("nombre");
/* 198 */     if (nombre == null) {
/* 199 */       nombre = "";
/*     */     }
/* 201 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 206 */     SisPermisosDAO rs = new SisPermisosDAO();
/* 207 */     if (!rs.getEstadoConexion()) {
/* 208 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 210 */     Collection<SisPermisosDTO> arr = rs.cargarTodos(codigo, nombre);
/*     */ 
/*     */     
/* 213 */     rs.close();
/* 214 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 215 */     int cuantas = 0;
/* 216 */     Iterator<SisPermisosDTO> iterator = arr.iterator();
/* 217 */     while (iterator.hasNext()) {
/* 218 */       SisPermisosDTO reg = (SisPermisosDTO)iterator.next();
/* 219 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 220 */       eltr.appendChild(newtd("" + reg.getCodigo()));
/* 221 */       String url = "SisPermisos.po?_operacion=V&codigo=" + reg.getCodigo() + "";
/* 222 */       eltr.appendChild(newtdhref("" + reg.getNombre(), url));
/* 223 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 224 */       hte.appendChild(eltr);
/* 225 */       cuantas++;
/*     */     } 
/* 227 */     arr.clear();
/* 228 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 241 */     String codigo = comms.request.getParameter("codigo");
/* 242 */     SisPermisosDAO rs = new SisPermisosDAO();
/* 243 */     if (!rs.getEstadoConexion()) {
/* 244 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 246 */     SisPermisosDTO reg = rs.cargarRegistro(codigo);
/* 247 */     rs.close();
/* 248 */     if (reg != null) {
/* 249 */       this.pagHTML.setTextCodigoEd("" + reg.getCodigo());
/* 250 */       this.pagHTML.setTextNombreEd("" + reg.getNombre());
/* 251 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/* 252 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 253 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 254 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 255 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 257 */       this.pagHTML.getElementCodigoKey().setValue("" + reg.getCodigo());
/* 258 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 260 */     activarVista("editar");
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
/* 271 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 273 */     Varios oVarios = new Varios();
/* 274 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Adm_SisPermisosAct");
/* 275 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Adm_SisPermisosDel");
/* 276 */     if (!oPermisoAct) {
/* 277 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 278 */       elem.getParentNode().removeChild(elem);
/* 279 */       elem = this.pagHTML.getElementBtnGrabar();
/* 280 */       elem.getParentNode().removeChild(elem);
/* 281 */       elem = this.pagHTML.getElementBtnModificar();
/* 282 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 284 */     if (!oPermisoDel) {
/* 285 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 286 */       elem.getParentNode().removeChild(elem);
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
/* 297 */     if (!vista.equals("nuevo")) {
/* 298 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 299 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 301 */     if (!vista.equals("editar")) {
/* 302 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 303 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 305 */     if (!vista.equals("consulta")) {
/* 306 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 307 */       sel.getParentNode().removeChild(sel);
/* 308 */       sel = this.pagHTML.getElementDivResultados();
/* 309 */       sel.getParentNode().removeChild(sel);
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
/* 323 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 324 */     atrib.setValue(valor);
/* 325 */     return atrib;
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
/* 338 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 339 */     Element enlace = this.pagHTML.createElement("a");
/* 340 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 341 */     enlace.appendChild(hijo);
/* 342 */     Attr donde = this.pagHTML.createAttribute("href");
/* 343 */     donde.setValue(vinculo);
/* 344 */     enlace.setAttributeNode(donde);
/* 345 */     td.appendChild(enlace);
/* 346 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 347 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 357 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 358 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 359 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 360 */     return td;
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
/* 375 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 376 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 377 */     rs.close();
/* 378 */     if (dejarBlanco) {
/* 379 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 380 */       op.setValue("");
/* 381 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 382 */       combo.appendChild(op);
/*     */     } 
/* 384 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 385 */     while (iterator.hasNext()) {
/* 386 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 387 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 388 */       op.setValue("" + reg.getCodigo());
/* 389 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 390 */       if (defecto.equals(reg.getCodigo())) {
/* 391 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 392 */         escogida.setValue("on");
/* 393 */         op.setAttributeNode(escogida);
/*     */       } 
/* 395 */       combo.appendChild(op);
/*     */     } 
/* 397 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SisPermisos.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */