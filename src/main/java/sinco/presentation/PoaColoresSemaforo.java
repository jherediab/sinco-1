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
/*     */ import sinco.business.PoaColoresSemaforoDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.data.PoaColoresSemaforoDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.PoaColoresSemaforo;
/*     */ import sinco.presentation.PoaColoresSemaforoHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PoaColoresSemaforo
/*     */   implements HttpPresentation
/*     */ {
/*     */   private PoaColoresSemaforoHTML pagHTML;
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
/*  51 */     this.pagHTML = (PoaColoresSemaforoHTML)comms.xmlcFactory.create(PoaColoresSemaforoHTML.class);
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
/*  83 */     int codigo = 0;
/*     */     try {
/*  85 */       codigo = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/*  87 */     catch (Exception e) {
/*  88 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigo"));
/*     */     } 
/*     */     
/*  91 */     RespuestaBD rta = new RespuestaBD();
/*  92 */     if (_operacion.equals("E")) {
/*  93 */       PoaColoresSemaforoDAO rs = new PoaColoresSemaforoDAO();
/*  94 */       if (!rs.getEstadoConexion()) {
/*  95 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*  97 */       rta = rs.eliminarRegistro(codigo);
/*  98 */       if (!rta.isRta()) {
/*  99 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaColoresSemaforo&p1=" + rta.getMensaje()));
/*     */       }
/* 101 */       rs.close();
/* 102 */       String sPagina = "PoaColoresSemaforo.po?_operacion=X";
/* 103 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 105 */     String tipoSemaforo = comms.request.getParameter("tipoSemaforo");
/* 106 */     String color = comms.request.getParameter("color");
/* 107 */     int valorInicial = 0;
/*     */     try {
/* 109 */       valorInicial = Integer.parseInt(comms.request.getParameter("valorInicial"));
/*     */     }
/* 111 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 114 */     int valorFinal = 0;
/*     */     try {
/* 116 */       valorFinal = Integer.parseInt(comms.request.getParameter("valorFinal"));
/*     */     }
/* 118 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 121 */     String estado = comms.request.getParameter("estado");
/* 122 */     PoaColoresSemaforoDAO rs = new PoaColoresSemaforoDAO();
/* 123 */     if (!rs.getEstadoConexion()) {
/* 124 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 126 */     if (_operacion.equals("C")) {
/* 127 */       rta = rs.crearRegistro(codigo, tipoSemaforo, color, valorInicial, valorFinal, estado, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 135 */       codigo = rta.getSecuencia();
/*     */     } else {
/*     */       
/* 138 */       rta = rs.modificarRegistro(codigo, tipoSemaforo, color, valorInicial, valorFinal, estado, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 147 */     rs.close();
/* 148 */     if (!rta.isRta()) {
/* 149 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaColoresSemaforo&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 152 */     String sPagina = "PoaColoresSemaforo.po?_operacion=P&codigo=" + codigo + "";
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
/* 164 */     int codigo = 0;
/*     */     try {
/* 166 */       codigo = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 168 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 171 */     PoaColoresSemaforoDAO rs = new PoaColoresSemaforoDAO();
/* 172 */     if (!rs.getEstadoConexion()) {
/* 173 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 175 */     PoaColoresSemaforoDTO reg = rs.cargarRegistro(codigo);
/* 176 */     rs.close();
/* 177 */     if (reg != null) {
/* 178 */       this.pagHTML.getElementCodigo().setValue("" + reg.getCodigo());
/* 179 */       this.pagHTML.getElementColor().setValue("" + reg.getColor());
/* 180 */       this.pagHTML.getElementValorInicial().setValue("" + reg.getValorInicial());
/* 181 */       this.pagHTML.getElementValorFinal().setValue("" + reg.getValorFinal());
/* 182 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 183 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 184 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 185 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 186 */       HTMLSelectElement combo = this.pagHTML.getElementTipoSemaforo();
/* 187 */       comboMultivalores(combo, "TABLERO_CONTROL_SEMAFORO", "" + reg.getTipoSemaforo(), true);
/*     */       
/* 189 */       combo = this.pagHTML.getElementEstado();
/* 190 */       comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), true);
/*     */ 
/*     */       
/* 193 */       this.pagHTML.getElementCodigo().setReadOnly(true);
/*     */     } 
/* 195 */     this.pagHTML.getElement_operacion().setValue("M");
/* 196 */     activarVista("nuevo");
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
/* 208 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 210 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 211 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 213 */     catch (Exception e) {}
/*     */     
/* 215 */     activarVista("nuevo");
/* 216 */     HTMLSelectElement combo = this.pagHTML.getElementTipoSemaforo();
/* 217 */     comboMultivalores(combo, "TABLERO_CONTROL_SEMAFORO", "", false);
/*     */     
/* 219 */     combo = this.pagHTML.getElementEstado();
/* 220 */     comboMultivalores(combo, "ESTADO_REGISTRO", "", false);
/*     */     
/* 222 */     this.pagHTML.getElementCodigo().setReadOnly(true);
/* 223 */     this.pagHTML.getElementCodigo().setValue("0");
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
/* 235 */     String tipoSemaforo = comms.request.getParameter("tipoSemaforo");
/* 236 */     if (tipoSemaforo == null) {
/* 237 */       tipoSemaforo = "";
/*     */     }
/* 239 */     int valorInicial = 0;
/*     */     try {
/* 241 */       valorInicial = Integer.parseInt(comms.request.getParameter("valorInicial"));
/*     */     }
/* 243 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 246 */     int valorFinal = 0;
/*     */     try {
/* 248 */       valorFinal = Integer.parseInt(comms.request.getParameter("valorFinal"));
/*     */     }
/* 250 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 253 */     HTMLSelectElement combo = this.pagHTML.getElementFtipoSemaforo();
/* 254 */     comboMultivalores(combo, "TABLERO_CONTROL_SEMAFORO", "" + tipoSemaforo, true);
/*     */ 
/*     */     
/* 257 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 262 */     PoaColoresSemaforoDAO rs = new PoaColoresSemaforoDAO();
/* 263 */     if (!rs.getEstadoConexion()) {
/* 264 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 266 */     Collection<PoaColoresSemaforoDTO> arr = rs.cargarTodos(tipoSemaforo, valorInicial, valorFinal);
/*     */ 
/*     */ 
/*     */     
/* 270 */     rs.close();
/* 271 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 272 */     int cuantas = 0;
/* 273 */     Iterator<PoaColoresSemaforoDTO> iterator = arr.iterator();
/* 274 */     while (iterator.hasNext()) {
/* 275 */       PoaColoresSemaforoDTO reg = (PoaColoresSemaforoDTO)iterator.next();
/* 276 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 277 */       eltr.appendChild(newtd("" + reg.getNombreTipoSemaforo()));
/* 278 */       String url = "PoaColoresSemaforo.po?_operacion=V&codigo=" + reg.getCodigo() + "";
/* 279 */       eltr.appendChild(newtdhref("" + reg.getColor(), url));
/* 280 */       eltr.appendChild(newtd("" + reg.getValorInicial()));
/* 281 */       eltr.appendChild(newtd("" + reg.getValorFinal()));
/* 282 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 283 */       hte.appendChild(eltr);
/* 284 */       cuantas++;
/*     */     } 
/* 286 */     arr.clear();
/* 287 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 300 */     int codigo = 0;
/*     */     try {
/* 302 */       codigo = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 304 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 307 */     PoaColoresSemaforoDAO rs = new PoaColoresSemaforoDAO();
/* 308 */     if (!rs.getEstadoConexion()) {
/* 309 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 311 */     PoaColoresSemaforoDTO reg = rs.cargarRegistro(codigo);
/* 312 */     rs.close();
/* 313 */     if (reg != null) {
/* 314 */       this.pagHTML.setTextCodigoEd("" + reg.getCodigo());
/* 315 */       this.pagHTML.setTextTipoSemaforoEd("" + reg.getNombreTipoSemaforo());
/* 316 */       this.pagHTML.setTextColorEd("" + reg.getColor());
/* 317 */       this.pagHTML.setTextValorInicialEd("" + reg.getValorInicial());
/* 318 */       this.pagHTML.setTextValorFinalEd("" + reg.getValorFinal());
/* 319 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/* 320 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 321 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 322 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 323 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
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
/* 342 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Poa_PoaColoresSemaforoAct");
/* 343 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Poa_PoaColoresSemaforoDel");
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


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PoaColoresSemaforo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */