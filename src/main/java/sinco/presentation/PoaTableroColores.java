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
/*     */ import sinco.business.PoaTableroColoresDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.data.PoaTableroColoresDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.PoaTableroColores;
/*     */ import sinco.presentation.PoaTableroColoresHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PoaTableroColores
/*     */   implements HttpPresentation
/*     */ {
/*     */   private PoaTableroColoresHTML pagHTML;
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
/*  51 */     this.pagHTML = (PoaTableroColoresHTML)comms.xmlcFactory.create(PoaTableroColoresHTML.class);
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
/*  93 */       PoaTableroColoresDAO rs = new PoaTableroColoresDAO();
/*  94 */       if (!rs.getEstadoConexion()) {
/*  95 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*  97 */       rta = rs.eliminarRegistro(codigo);
/*  98 */       if (!rta.isRta()) {
/*  99 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaTableroColores&p1=" + rta.getMensaje()));
/*     */       }
/* 101 */       rs.close();
/* 102 */       String sPagina = "PoaTableroColores.po?_operacion=X";
/* 103 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 105 */     String color = comms.request.getParameter("color");
/* 106 */     int valorInicial = 0;
/*     */     try {
/* 108 */       valorInicial = Integer.parseInt(comms.request.getParameter("valorInicial"));
/*     */     }
/* 110 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 113 */     int valorFinal = 0;
/*     */     try {
/* 115 */       valorFinal = Integer.parseInt(comms.request.getParameter("valorFinal"));
/*     */     }
/* 117 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 120 */     String estado = comms.request.getParameter("estado");
/* 121 */     PoaTableroColoresDAO rs = new PoaTableroColoresDAO();
/* 122 */     if (!rs.getEstadoConexion()) {
/* 123 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 125 */     if (_operacion.equals("C")) {
/* 126 */       rta = rs.crearRegistro(codigo, color, valorInicial, valorFinal, estado, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 133 */       codigo = rta.getSecuencia();
/*     */     } else {
/*     */       
/* 136 */       rta = rs.modificarRegistro(codigo, color, valorInicial, valorFinal, estado, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 144 */     rs.close();
/* 145 */     if (!rta.isRta()) {
/* 146 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaTableroColores&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 149 */     String sPagina = "PoaTableroColores.po?_operacion=P&codigo=" + codigo + "";
/* 150 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 161 */     int codigo = 0;
/*     */     try {
/* 163 */       codigo = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 165 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 168 */     PoaTableroColoresDAO rs = new PoaTableroColoresDAO();
/* 169 */     if (!rs.getEstadoConexion()) {
/* 170 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 172 */     PoaTableroColoresDTO reg = rs.cargarRegistro(codigo);
/* 173 */     rs.close();
/* 174 */     if (reg != null) {
/* 175 */       this.pagHTML.getElementCodigo().setValue("" + reg.getCodigo());
/* 176 */       this.pagHTML.getElementColor().setValue("" + reg.getColor());
/* 177 */       this.pagHTML.getElementValorInicial().setValue("" + reg.getValorInicial());
/* 178 */       this.pagHTML.getElementValorFinal().setValue("" + reg.getValorFinal());
/* 179 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 180 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 181 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 182 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 183 */       HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 184 */       comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), true);
/*     */ 
/*     */       
/* 187 */       this.pagHTML.getElementCodigo().setReadOnly(true);
/*     */     } 
/* 189 */     this.pagHTML.getElement_operacion().setValue("M");
/* 190 */     activarVista("nuevo");
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
/* 202 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 204 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 205 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 207 */     catch (Exception e) {}
/*     */     
/* 209 */     activarVista("nuevo");
/* 210 */     HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 211 */     comboMultivalores(combo, "ESTADO_REGISTRO", "A", false);
/*     */     
/* 213 */     this.pagHTML.getElementCodigo().setReadOnly(true);
/* 214 */     this.pagHTML.getElementCodigo().setValue("0");
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
/* 225 */     activarVista("consulta");
/* 226 */     String color = comms.request.getParameter("color");
/* 227 */     if (color == null) {
/* 228 */       color = "";
/*     */     }
/* 230 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 235 */     PoaTableroColoresDAO rs = new PoaTableroColoresDAO();
/* 236 */     if (!rs.getEstadoConexion()) {
/* 237 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 239 */     Collection<PoaTableroColoresDTO> arr = rs.cargarTodos(color);
/*     */     
/* 241 */     rs.close();
/* 242 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 243 */     int cuantas = 0;
/* 244 */     Iterator<PoaTableroColoresDTO> iterator = arr.iterator();
/* 245 */     while (iterator.hasNext()) {
/* 246 */       PoaTableroColoresDTO reg = (PoaTableroColoresDTO)iterator.next();
/* 247 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 248 */       eltr.appendChild(newtdColor("", "" + reg.getColor()));
/* 249 */       String url = "PoaTableroColores.po?_operacion=V&codigo=" + reg.getCodigo() + "";
/* 250 */       eltr.appendChild(newtdhref("" + reg.getValorInicial(), url));
/* 251 */       eltr.appendChild(newtd("" + reg.getValorFinal()));
/* 252 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 253 */       hte.appendChild(eltr);
/* 254 */       cuantas++;
/*     */     } 
/* 256 */     arr.clear();
/* 257 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 270 */     int codigo = 0;
/*     */     try {
/* 272 */       codigo = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 274 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 277 */     PoaTableroColoresDAO rs = new PoaTableroColoresDAO();
/* 278 */     if (!rs.getEstadoConexion()) {
/* 279 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 281 */     PoaTableroColoresDTO reg = rs.cargarRegistro(codigo);
/* 282 */     rs.close();
/* 283 */     if (reg != null) {
/* 284 */       this.pagHTML.setTextCodigoEd("" + reg.getCodigo());
/* 285 */       this.pagHTML.setTextColorEd("" + reg.getColor());
/* 286 */       this.pagHTML.getElementColorEd().setAttribute("bgcolor", "#" + reg.getColor());
/* 287 */       this.pagHTML.setTextValorInicialEd("" + reg.getValorInicial());
/* 288 */       this.pagHTML.setTextValorFinalEd("" + reg.getValorFinal());
/* 289 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/* 290 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 291 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 292 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 293 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 295 */       this.pagHTML.getElementCodigoKey().setValue("" + reg.getCodigo());
/* 296 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 298 */     activarVista("editar");
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
/* 309 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 311 */     Varios oVarios = new Varios();
/* 312 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Poa_PoaTableroColoresAct");
/* 313 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Poa_PoaTableroColoresDel");
/* 314 */     if (!oPermisoAct) {
/* 315 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 316 */       elem.getParentNode().removeChild(elem);
/* 317 */       elem = this.pagHTML.getElementBtnGrabar();
/* 318 */       elem.getParentNode().removeChild(elem);
/* 319 */       elem = this.pagHTML.getElementBtnModificar();
/* 320 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 322 */     if (!oPermisoDel) {
/* 323 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 324 */       elem.getParentNode().removeChild(elem);
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
/* 335 */     if (!vista.equals("nuevo")) {
/* 336 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 337 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 339 */     if (!vista.equals("editar")) {
/* 340 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 341 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 343 */     if (!vista.equals("consulta")) {
/* 344 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 345 */       sel.getParentNode().removeChild(sel);
/* 346 */       sel = this.pagHTML.getElementDivResultados();
/* 347 */       sel.getParentNode().removeChild(sel);
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
/* 361 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 362 */     atrib.setValue(valor);
/* 363 */     return atrib;
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
/* 376 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 377 */     Element enlace = this.pagHTML.createElement("a");
/* 378 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 379 */     enlace.appendChild(hijo);
/* 380 */     Attr donde = this.pagHTML.createAttribute("href");
/* 381 */     donde.setValue(vinculo);
/* 382 */     enlace.setAttributeNode(donde);
/* 383 */     td.appendChild(enlace);
/* 384 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 385 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 395 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 396 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 397 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 398 */     return td;
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
/* 413 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 414 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 415 */     rs.close();
/* 416 */     if (dejarBlanco) {
/* 417 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 418 */       op.setValue("");
/* 419 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 420 */       combo.appendChild(op);
/*     */     } 
/* 422 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 423 */     while (iterator.hasNext()) {
/* 424 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 425 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 426 */       op.setValue("" + reg.getCodigo());
/* 427 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 428 */       if (defecto.equals(reg.getCodigo())) {
/* 429 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 430 */         escogida.setValue("on");
/* 431 */         op.setAttributeNode(escogida);
/*     */       } 
/* 433 */       combo.appendChild(op);
/*     */     } 
/* 435 */     arr.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtdColor(String contenido, String color) {
/* 446 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 447 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 448 */     td.setAttributeNode(newAttr("bgcolor", "#" + color));
/* 449 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PoaTableroColores.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */