/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.CalCiclosDTO;
/*     */ import sinco.business.CalPeriodosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.data.CalCiclosDAO;
/*     */ import sinco.data.CalPeriodosDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.CalCiclosAct;
/*     */ import sinco.presentation.CalCiclosActHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalCiclosAct
/*     */   implements HttpPresentation
/*     */ {
/*     */   private CalCiclosActHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  43 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  44 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  47 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  48 */     String _operacion = comms.request.getParameter("_operacion");
/*  49 */     if (_operacion == null || _operacion.length() == 0) {
/*  50 */       _operacion = "L";
/*     */     }
/*     */     
/*  53 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  54 */       creacion(comms);
/*     */     }
/*     */     
/*  57 */     this.pagHTML = (CalCiclosActHTML)comms.xmlcFactory.create(CalCiclosActHTML.class);
/*     */     
/*  59 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  60 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  63 */     if (_operacion.equals("P")) {
/*  64 */       editar(comms);
/*     */     }
/*  66 */     else if (_operacion.equals("Nuevo")) {
/*  67 */       nuevo(comms);
/*     */     } 
/*     */     
/*  70 */     if (_operacion.equals("V")) {
/*  71 */       verRegistro(comms);
/*     */     }
/*  73 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  74 */     comms.response.writeDOM(this.pagHTML);
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
/*  86 */     String _operacion = comms.request.getParameter("_operacion");
/*  87 */     String elUsuario = "" + comms.session.getUser().getName();
/*  88 */     int ciclo = 0;
/*     */     try {
/*  90 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/*  92 */     catch (Exception e) {
/*  93 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=ciclo"));
/*     */     } 
/*     */     
/*  96 */     RespuestaBD rta = new RespuestaBD();
/*  97 */     if (_operacion.equals("E")) {
/*  98 */       CalCiclosDAO rs = new CalCiclosDAO();
/*  99 */       if (!rs.getEstadoConexion()) {
/* 100 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 102 */       rta = rs.eliminarRegistro(ciclo);
/* 103 */       if (!rta.isRta()) {
/* 104 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCalCiclos&p1=" + rta.getMensaje()));
/*     */       }
/* 106 */       rs.close();
/* 107 */       String sPagina = "CalCiclosAct.po?_operacion=X";
/* 108 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 110 */     String descripcion = comms.request.getParameter("descripcion");
/* 111 */     String estado = comms.request.getParameter("estado");
/* 112 */     CalCiclosDAO rs = new CalCiclosDAO();
/* 113 */     if (!rs.getEstadoConexion()) {
/* 114 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 116 */     if (_operacion.equals("C")) {
/* 117 */       rta = rs.crearRegistro(ciclo, descripcion, estado, elUsuario);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 124 */       rta = rs.modificarRegistro(ciclo, descripcion, estado, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 130 */       actualizarPeriodos(comms, ciclo);
/*     */     } 
/* 132 */     rs.close();
/* 133 */     if (!rta.isRta()) {
/* 134 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCalCiclos&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 137 */     String sPagina = "CalCiclosAct.po?_operacion=P&ciclo=" + ciclo + "";
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
/* 149 */     int ciclo = 0;
/*     */     try {
/* 151 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 153 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 156 */     CalCiclosDAO rs = new CalCiclosDAO();
/* 157 */     if (!rs.getEstadoConexion()) {
/* 158 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 160 */     CalCiclosDTO reg = rs.cargarRegistro(ciclo);
/* 161 */     rs.close();
/* 162 */     if (reg != null) {
/* 163 */       this.pagHTML.getElementCiclo().setValue("" + reg.getCiclo());
/* 164 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 165 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 166 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 167 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 168 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/*     */       
/* 170 */       HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 171 */       comboMultivalores(combo, "CAL_ESTADO_CICLO", "" + reg.getEstado(), true);
/*     */ 
/*     */       
/* 174 */       mostrarPeriodos(ciclo);
/*     */       
/* 176 */       this.pagHTML.getElementCiclo().setReadOnly(true);
/*     */     } 
/* 178 */     this.pagHTML.getElement_operacion().setValue("M");
/* 179 */     activarVista("nuevo");
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
/* 191 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 193 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 194 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 196 */     catch (Exception e) {}
/*     */     
/* 198 */     activarVista("nuevo");
/* 199 */     HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 200 */     comboMultivalores(combo, "CAL_ESTADO_CICLO", "D", true);
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
/* 212 */     activarVista("consulta");
/* 213 */     int ciclo = 0;
/*     */     try {
/* 215 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 217 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 220 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 225 */     CalCiclosDAO rs = new CalCiclosDAO();
/* 226 */     if (!rs.getEstadoConexion()) {
/* 227 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 229 */     Collection arr = rs.cargarTodos(ciclo);
/*     */     
/* 231 */     rs.close();
/* 232 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 233 */     int cuantas = 0;
/* 234 */     Iterator iterator = arr.iterator();
/* 235 */     while (iterator.hasNext()) {
/* 236 */       CalCiclosDTO reg = (CalCiclosDTO)iterator.next();
/* 237 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 238 */       eltr.appendChild(newtd("" + reg.getCiclo()));
/* 239 */       String url = "CalCiclosAct.po?_operacion=P&ciclo=" + reg.getCiclo() + "";
/* 240 */       eltr.appendChild(newtdhref("" + reg.getDescripcion(), url));
/* 241 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 242 */       hte.appendChild(eltr);
/* 243 */       cuantas++;
/*     */     } 
/* 245 */     arr.clear();
/* 246 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 259 */     int ciclo = 0;
/*     */     try {
/* 261 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 263 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 266 */     CalCiclosDAO rs = new CalCiclosDAO();
/* 267 */     if (!rs.getEstadoConexion()) {
/* 268 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 270 */     CalCiclosDTO reg = rs.cargarRegistro(ciclo);
/* 271 */     rs.close();
/* 272 */     if (reg != null) {
/* 273 */       this.pagHTML.setTextCicloEd("" + reg.getCiclo());
/* 274 */       this.pagHTML.setTextDescripcionEd("" + reg.getDescripcion());
/* 275 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/* 276 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 277 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 278 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 279 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 281 */       this.pagHTML.getElementCicloKey().setValue("" + reg.getCiclo());
/* 282 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 284 */     activarVista("editar");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void activarVista(String vista) {
/* 295 */     if (!vista.equals("nuevo")) {
/* 296 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 297 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 299 */     if (!vista.equals("editar")) {
/* 300 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 301 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 303 */     if (!vista.equals("consulta")) {
/* 304 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 305 */       sel.getParentNode().removeChild(sel);
/* 306 */       sel = this.pagHTML.getElementDivResultados();
/* 307 */       sel.getParentNode().removeChild(sel);
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
/* 321 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 322 */     atrib.setValue(valor);
/* 323 */     return atrib;
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
/* 336 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 337 */     Element enlace = this.pagHTML.createElement("a");
/* 338 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 339 */     enlace.appendChild(hijo);
/* 340 */     Attr donde = this.pagHTML.createAttribute("href");
/* 341 */     donde.setValue(vinculo);
/* 342 */     enlace.setAttributeNode(donde);
/* 343 */     td.appendChild(enlace);
/* 344 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 345 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 355 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 356 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 357 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 358 */     return td;
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
/*     */   private void comboMultivalores(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco) {
/*     */     Collection arr;
/* 373 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/*     */     
/* 375 */     if (!defecto.equals("D")) {
/* 376 */       arr = rs.cargarTabla(tabla);
/*     */     } else {
/*     */       
/* 379 */       arr = rs.cargarTabla(tabla, defecto);
/*     */     } 
/*     */     
/* 382 */     rs.close();
/* 383 */     if (dejarBlanco) {
/* 384 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 385 */       op.setValue("");
/* 386 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 387 */       combo.appendChild(op);
/*     */     } 
/* 389 */     Iterator iterator = arr.iterator();
/* 390 */     while (iterator.hasNext()) {
/* 391 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 392 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 393 */       op.setValue("" + reg.getCodigo());
/* 394 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 395 */       if (defecto.equals(reg.getCodigo())) {
/* 396 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 397 */         escogida.setValue("on");
/* 398 */         op.setAttributeNode(escogida);
/*     */       } 
/* 400 */       combo.appendChild(op);
/*     */     } 
/* 402 */     arr.clear();
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
/*     */   private void comboPeriodos(HTMLSelectElement combo, String defecto) {
/* 415 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 416 */     op.setValue("A");
/* 417 */     op.appendChild(this.pagHTML.createTextNode("Abierto"));
/* 418 */     if (defecto.equals("A")) {
/* 419 */       Attr escogida = this.pagHTML.createAttribute("selected");
/* 420 */       escogida.setValue("on");
/* 421 */       op.setAttributeNode(escogida);
/*     */     } 
/* 423 */     combo.appendChild(op);
/*     */     
/* 425 */     HTMLOptionElement op2 = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 426 */     op2.setValue("C");
/* 427 */     op2.appendChild(this.pagHTML.createTextNode("Cerrado"));
/* 428 */     if (defecto.equals("C")) {
/* 429 */       Attr escogida = this.pagHTML.createAttribute("selected");
/* 430 */       escogida.setValue("on");
/* 431 */       op2.setAttributeNode(escogida);
/*     */     } 
/* 433 */     combo.appendChild(op2);
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
/*     */   private void actualizarPeriodos(HttpPresentationComms comms, int ciclo) throws HttpPresentationException, KeywordValueException {
/* 446 */     CalPeriodosDAO rs = new CalPeriodosDAO();
/* 447 */     if (!rs.getEstadoConexion()) {
/* 448 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 451 */     Enumeration enumera = comms.request.getParameterNames();
/*     */ 
/*     */     
/* 454 */     while (enumera.hasMoreElements()) {
/* 455 */       String param = (String)enumera.nextElement();
/* 456 */       if (param.substring(0, 2).equals("_P")) {
/* 457 */         int codigo = Integer.parseInt(param.substring(2, param.length()));
/* 458 */         String estado = comms.request.getParameter(param);
/* 459 */         rs.modificarRegistro(ciclo, codigo, estado, comms.session.getUser().getName());
/*     */       } 
/*     */     } 
/* 462 */     rs.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int mostrarPeriodos(int ciclo) {
/* 472 */     CalPeriodosDAO rsPeriodos = new CalPeriodosDAO();
/* 473 */     if (!rsPeriodos.getEstadoConexion()) {
/* 474 */       return -1;
/*     */     }
/* 476 */     rsPeriodos.cargarTodos(ciclo);
/* 477 */     CalPeriodosDTO reg = rsPeriodos.next();
/* 478 */     HTMLTableElement hte = this.pagHTML.getElementPeriodos();
/* 479 */     boolean fondo = true;
/* 480 */     int cuantos = 0;
/* 481 */     while (reg != null) {
/* 482 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 483 */       fondo = !fondo;
/* 484 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 486 */       eltr.appendChild(newtd("" + reg.getDescripcion()));
/*     */       
/* 488 */       HTMLSelectElement combo = (HTMLSelectElement)this.pagHTML.createElement("Select");
/* 489 */       combo.setName("_P" + reg.getPeriodo());
/* 490 */       comboPeriodos(combo, reg.getEstado());
/* 491 */       combo.setAttributeNode(newAttr("onkeyDown", "return f_salto2(event)"));
/* 492 */       combo.setAttributeNode(newAttr("onkeyUp", "return f_salto(event)"));
/* 493 */       HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 494 */       td.appendChild(combo);
/* 495 */       eltr.appendChild(td);
/*     */       
/* 497 */       hte.appendChild(eltr);
/* 498 */       cuantos++;
/*     */       
/* 500 */       reg = rsPeriodos.next();
/*     */     } 
/* 502 */     rsPeriodos.close();
/* 503 */     return cuantos;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalCiclosAct.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */