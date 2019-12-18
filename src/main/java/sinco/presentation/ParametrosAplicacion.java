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
/*     */ import sinco.business.ParametrosAplicacionDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ParametrosAplicacionDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.ParametrosAplicacion;
/*     */ import sinco.presentation.ParametrosAplicacionHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParametrosAplicacion
/*     */   implements HttpPresentation
/*     */ {
/*     */   private ParametrosAplicacionHTML pagHTML;
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
/*     */     
/*  52 */     this.pagHTML = (ParametrosAplicacionHTML)comms.xmlcFactory.create(ParametrosAplicacionHTML.class);
/*  53 */     permisos(comms);
/*     */     
/*  55 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  56 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  59 */     if (_operacion.equals("P")) {
/*  60 */       editar(comms);
/*     */     }
/*  62 */     else if (_operacion.equals("Nuevo")) {
/*  63 */       nuevo(comms);
/*     */     } 
/*     */     
/*  66 */     if (_operacion.equals("V")) {
/*  67 */       verRegistro(comms);
/*     */     }
/*  69 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  70 */     comms.response.writeDOM(this.pagHTML);
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
/*  82 */     String _operacion = comms.request.getParameter("_operacion");
/*  83 */     String elUsuario = "" + comms.session.getUser().getName();
/*  84 */     String codigo = comms.request.getParameter("codigo");
/*  85 */     if (codigo == null) {
/*  86 */       codigo = "";
/*     */     }
/*  88 */     RespuestaBD rta = new RespuestaBD();
/*  89 */     if (_operacion.equals("E")) {
/*  90 */       ParametrosAplicacionDAO rs = new ParametrosAplicacionDAO();
/*  91 */       if (!rs.getEstadoConexion()) {
/*  92 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*  94 */       rta = rs.eliminarRegistro(codigo);
/*  95 */       if (!rta.isRta()) {
/*  96 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorParametrosAplicacion&p1=" + rta.getMensaje()));
/*     */       }
/*  98 */       rs.close();
/*  99 */       String sPagina = "ParametrosAplicacion.po?_operacion=X";
/* 100 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 102 */     String nombre = comms.request.getParameter("nombre");
/* 103 */     int entero = 0;
/*     */     try {
/* 105 */       entero = Integer.parseInt(comms.request.getParameter("entero"));
/*     */     }
/* 107 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 110 */     double real = 0.0D;
/*     */     try {
/* 112 */       real = Double.parseDouble(comms.request.getParameter("real"));
/*     */     }
/* 114 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 117 */     String caracter = comms.request.getParameter("caracter");
/* 118 */     String fecha = comms.request.getParameter("fecha");
/* 119 */     String descripcion = comms.request.getParameter("descripcion");
/* 120 */     String control = comms.request.getParameter("control");
/*     */     
/* 122 */     String batch = comms.request.getParameter("batch");
/* 123 */     if (batch == null) {
/* 124 */       batch = "N";
/*     */     }
/* 126 */     String linea = comms.request.getParameter("linea");
/* 127 */     if (linea == null) {
/* 128 */       linea = "N";
/*     */     }
/* 130 */     ParametrosAplicacionDAO rs = new ParametrosAplicacionDAO();
/* 131 */     if (!rs.getEstadoConexion()) {
/* 132 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */ 
/*     */     
/* 136 */     if (_operacion.equals("C")) {
/* 137 */       rta = rs.crearRegistro(codigo, nombre, entero, real, caracter, fecha, descripcion, control, batch, linea, elUsuario);
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
/* 151 */       rta = rs.modificarRegistro(codigo, nombre, entero, real, caracter, fecha, descripcion, control, batch, linea, elUsuario);
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
/* 164 */     rs.close();
/* 165 */     if (!rta.isRta()) {
/* 166 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorParametrosAplicacion&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 169 */     String sPagina = "ParametrosAplicacion.po?_operacion=P&codigo=" + codigo + "";
/* 170 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 181 */     String codigo = comms.request.getParameter("codigo");
/* 182 */     ParametrosAplicacionDAO rs = new ParametrosAplicacionDAO();
/* 183 */     if (!rs.getEstadoConexion()) {
/* 184 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 186 */     ParametrosAplicacionDTO reg = rs.cargarRegistro(codigo);
/* 187 */     rs.close();
/* 188 */     if (reg != null) {
/* 189 */       this.pagHTML.getElementCodigo().setValue("" + reg.getCodigo());
/* 190 */       this.pagHTML.getElementNombre().setValue("" + reg.getNombre());
/* 191 */       this.pagHTML.getElementEntero().setValue("" + reg.getEntero());
/* 192 */       this.pagHTML.getElementReal().setValue("" + Utilidades.formatDouble2(reg.getReal()));
/* 193 */       this.pagHTML.getElementCaracter().setValue("" + reg.getCaracter());
/* 194 */       this.pagHTML.getElementFecha().setValue("" + Utilidades.darFormatoFecha(reg.getFecha()));
/* 195 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 196 */       if (reg.getBatch().equals("S")) {
/* 197 */         this.pagHTML.getElementBatch().setChecked(true);
/*     */       }
/* 199 */       if (reg.getLinea().equals("S")) {
/* 200 */         this.pagHTML.getElementLinea().setChecked(true);
/*     */       }
/* 202 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 203 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 204 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 205 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 206 */       HTMLSelectElement combo = this.pagHTML.getElementControl();
/* 207 */       comboMultivalores(combo, "TIPO_PARAMETRO", "" + reg.getControl(), true);
/*     */ 
/*     */       
/* 210 */       this.pagHTML.getElementCodigo().setReadOnly(true);
/*     */     } 
/* 212 */     this.pagHTML.getElement_operacion().setValue("M");
/* 213 */     activarVista("nuevo");
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
/* 225 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 227 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 228 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 230 */     catch (Exception e) {}
/*     */     
/* 232 */     activarVista("nuevo");
/* 233 */     HTMLSelectElement combo = this.pagHTML.getElementControl();
/* 234 */     comboMultivalores(combo, "TIPO_PARAMETRO", "", true);
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
/* 246 */     activarVista("consulta");
/* 247 */     String codigo = comms.request.getParameter("codigo");
/* 248 */     if (codigo == null) {
/* 249 */       codigo = "";
/*     */     }
/* 251 */     String nombre = comms.request.getParameter("nombre");
/* 252 */     if (nombre == null) {
/* 253 */       nombre = "";
/*     */     }
/* 255 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 260 */     ParametrosAplicacionDAO rs = new ParametrosAplicacionDAO();
/* 261 */     if (!rs.getEstadoConexion()) {
/* 262 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 264 */     Collection<ParametrosAplicacionDTO> arr = rs.cargarTodos(codigo, nombre);
/*     */ 
/*     */     
/* 267 */     rs.close();
/* 268 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 269 */     int cuantas = 0;
/* 270 */     Iterator<ParametrosAplicacionDTO> iterator = arr.iterator();
/* 271 */     while (iterator.hasNext()) {
/* 272 */       ParametrosAplicacionDTO reg = (ParametrosAplicacionDTO)iterator.next();
/* 273 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 274 */       eltr.appendChild(newtd("" + reg.getCodigo()));
/* 275 */       String url = "ParametrosAplicacion.po?_operacion=V&codigo=" + reg.getCodigo() + "";
/* 276 */       eltr.appendChild(newtdhref("" + reg.getNombre(), url));
/* 277 */       eltr.appendChild(newtd("" + reg.getEntero()));
/* 278 */       eltr.appendChild(newtd("" + Utilidades.miles(reg.getReal())));
/* 279 */       eltr.appendChild(newtd("" + reg.getCaracter()));
/* 280 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFecha())));
/* 281 */       eltr.appendChild(newtd("" + reg.getDescripcion()));
/* 282 */       eltr.appendChild(newtd("" + reg.getNombreControl()));
/* 283 */       eltr.appendChild(newtd("" + reg.getBatch()));
/* 284 */       eltr.appendChild(newtd("" + reg.getLinea()));
/* 285 */       hte.appendChild(eltr);
/* 286 */       cuantas++;
/*     */     } 
/* 288 */     arr.clear();
/* 289 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 302 */     String codigo = comms.request.getParameter("codigo");
/* 303 */     ParametrosAplicacionDAO rs = new ParametrosAplicacionDAO();
/* 304 */     if (!rs.getEstadoConexion()) {
/* 305 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 307 */     ParametrosAplicacionDTO reg = rs.cargarRegistro(codigo);
/* 308 */     rs.close();
/* 309 */     if (reg != null) {
/* 310 */       this.pagHTML.setTextCodigoEd("" + reg.getCodigo());
/* 311 */       this.pagHTML.setTextNombreEd("" + reg.getNombre());
/* 312 */       this.pagHTML.setTextEnteroEd("" + reg.getEntero());
/* 313 */       this.pagHTML.setTextRealEd("" + Utilidades.miles(reg.getReal()));
/* 314 */       this.pagHTML.setTextCaracterEd("" + reg.getCaracter());
/* 315 */       this.pagHTML.setTextFechaEd("" + Utilidades.darFormatoFecha(reg.getFecha()));
/* 316 */       this.pagHTML.setTextDescripcionEd("" + reg.getDescripcion());
/* 317 */       this.pagHTML.setTextControlEd("" + reg.getNombreControl());
/* 318 */       this.pagHTML.setTextBatchEd("" + reg.getBatch());
/* 319 */       this.pagHTML.setTextLineaEd("" + reg.getLinea());
/* 320 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 321 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 322 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/* 323 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/*     */       
/* 325 */       this.pagHTML.getElementCodigoKey().setValue("" + reg.getCodigo());
/* 326 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 328 */     activarVista("editar");
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
/* 339 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 341 */     Varios oVarios = new Varios();
/* 342 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Adm_ParametrosAplicacionAct");
/* 343 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Adm_ParametrosAplicacionDel");
/* 344 */     if (!oPermisoAct) {
/* 345 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 346 */       elem.getParentNode().removeChild(elem);
/* 347 */       elem = this.pagHTML.getElementBtnGrabar();
/* 348 */       elem.getParentNode().removeChild(elem);
/* 349 */       elem = this.pagHTML.getElementBtnModificar();
/* 350 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 352 */     if (!oPermisoDel) {
/* 353 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 354 */       elem.getParentNode().removeChild(elem);
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
/* 365 */     if (!vista.equals("nuevo")) {
/* 366 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 367 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 369 */     if (!vista.equals("editar")) {
/* 370 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 371 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 373 */     if (!vista.equals("consulta")) {
/* 374 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 375 */       sel.getParentNode().removeChild(sel);
/* 376 */       sel = this.pagHTML.getElementDivResultados();
/* 377 */       sel.getParentNode().removeChild(sel);
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
/* 391 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 392 */     atrib.setValue(valor);
/* 393 */     return atrib;
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
/* 406 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 407 */     Element enlace = this.pagHTML.createElement("a");
/* 408 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 409 */     enlace.appendChild(hijo);
/* 410 */     Attr donde = this.pagHTML.createAttribute("href");
/* 411 */     donde.setValue(vinculo);
/* 412 */     enlace.setAttributeNode(donde);
/* 413 */     td.appendChild(enlace);
/* 414 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 415 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 425 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 426 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 427 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 428 */     return td;
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
/* 443 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 444 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 445 */     rs.close();
/* 446 */     if (dejarBlanco) {
/* 447 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 448 */       op.setValue("");
/* 449 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 450 */       combo.appendChild(op);
/*     */     } 
/* 452 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 453 */     while (iterator.hasNext()) {
/* 454 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 455 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 456 */       op.setValue("" + reg.getCodigo());
/* 457 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 458 */       if (defecto.equals(reg.getCodigo())) {
/* 459 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 460 */         escogida.setValue("on");
/* 461 */         op.setAttributeNode(escogida);
/*     */       } 
/* 463 */       combo.appendChild(op);
/*     */     } 
/* 465 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ParametrosAplicacion.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */