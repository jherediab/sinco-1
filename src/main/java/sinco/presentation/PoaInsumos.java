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
/*     */ import sinco.business.PoaInsumosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.data.PoaInsumosDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.PoaInsumos;
/*     */ import sinco.presentation.PoaInsumosHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PoaInsumos
/*     */   implements HttpPresentation
/*     */ {
/*     */   private PoaInsumosHTML pagHTML;
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
/*  51 */     this.pagHTML = (PoaInsumosHTML)comms.xmlcFactory.create(PoaInsumosHTML.class);
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
/*  83 */     int codigoInsumo = 0;
/*     */     try {
/*  85 */       codigoInsumo = Integer.parseInt(comms.request.getParameter("codigoInsumo"));
/*     */     }
/*  87 */     catch (Exception e) {
/*  88 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoInsumo"));
/*     */     } 
/*     */     
/*  91 */     RespuestaBD rta = new RespuestaBD();
/*  92 */     if (_operacion.equals("E")) {
/*  93 */       PoaInsumosDAO rs = new PoaInsumosDAO();
/*  94 */       if (!rs.getEstadoConexion()) {
/*  95 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*  97 */       rta = rs.eliminarRegistro(codigoInsumo);
/*  98 */       if (!rta.isRta()) {
/*  99 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaInsumos&p1=" + rta.getMensaje()));
/*     */       }
/* 101 */       rs.close();
/* 102 */       String sPagina = "PoaInsumos.po?_operacion=X";
/* 103 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 105 */     String descrpcion = comms.request.getParameter("descrpcion");
/* 106 */     String estado = comms.request.getParameter("estado");
/* 107 */     PoaInsumosDAO rs = new PoaInsumosDAO();
/* 108 */     if (!rs.getEstadoConexion()) {
/* 109 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 111 */     if (_operacion.equals("C")) {
/* 112 */       rta = rs.crearRegistro(codigoInsumo, descrpcion, estado, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 117 */       codigoInsumo = rta.getSecuencia();
/*     */     } else {
/*     */       
/* 120 */       rta = rs.modificarRegistro(codigoInsumo, descrpcion, estado, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 126 */     rs.close();
/* 127 */     if (!rta.isRta()) {
/* 128 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaInsumos&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 131 */     String sPagina = "PoaInsumos.po?_operacion=P&codigoInsumo=" + codigoInsumo + "";
/* 132 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 143 */     int codigoInsumo = 0;
/*     */     try {
/* 145 */       codigoInsumo = Integer.parseInt(comms.request.getParameter("codigoInsumo"));
/*     */     }
/* 147 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 150 */     PoaInsumosDAO rs = new PoaInsumosDAO();
/* 151 */     if (!rs.getEstadoConexion()) {
/* 152 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 154 */     PoaInsumosDTO reg = rs.cargarRegistro(codigoInsumo);
/* 155 */     rs.close();
/* 156 */     if (reg != null) {
/* 157 */       this.pagHTML.getElementCodigoInsumo().setValue("" + reg.getCodigoInsumo());
/* 158 */       this.pagHTML.getElementDescrpcion().setValue("" + reg.getDescrpcion());
/* 159 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 160 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 161 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 162 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 163 */       HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 164 */       comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), false);
/*     */ 
/*     */       
/* 167 */       this.pagHTML.getElementCodigoInsumo().setReadOnly(true);
/*     */     } 
/* 169 */     this.pagHTML.getElement_operacion().setValue("M");
/* 170 */     activarVista("nuevo");
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
/* 182 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 184 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 185 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 187 */     catch (Exception e) {}
/*     */     
/* 189 */     activarVista("nuevo");
/* 190 */     HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 191 */     comboMultivalores(combo, "ESTADO_REGISTRO", "", false);
/*     */     
/* 193 */     this.pagHTML.getElementCodigoInsumo().setReadOnly(true);
/* 194 */     this.pagHTML.getElementCodigoInsumo().setValue("0");
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
/* 205 */     activarVista("consulta");
/* 206 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 211 */     PoaInsumosDAO rs = new PoaInsumosDAO();
/* 212 */     if (!rs.getEstadoConexion()) {
/* 213 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 215 */     Collection<PoaInsumosDTO> arr = rs.cargarTodos();
/* 216 */     rs.close();
/* 217 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 218 */     int cuantas = 0;
/* 219 */     Iterator<PoaInsumosDTO> iterator = arr.iterator();
/* 220 */     while (iterator.hasNext()) {
/* 221 */       PoaInsumosDTO reg = (PoaInsumosDTO)iterator.next();
/* 222 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 223 */       eltr.appendChild(newtd("" + reg.getCodigoInsumo()));
/* 224 */       String url = "PoaInsumos.po?_operacion=V&codigoInsumo=" + reg.getCodigoInsumo() + "";
/* 225 */       eltr.appendChild(newtdhref("" + reg.getDescrpcion(), url));
/* 226 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 227 */       hte.appendChild(eltr);
/* 228 */       cuantas++;
/*     */     } 
/* 230 */     arr.clear();
/* 231 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 244 */     int codigoInsumo = 0;
/*     */     try {
/* 246 */       codigoInsumo = Integer.parseInt(comms.request.getParameter("codigoInsumo"));
/*     */     }
/* 248 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 251 */     PoaInsumosDAO rs = new PoaInsumosDAO();
/* 252 */     if (!rs.getEstadoConexion()) {
/* 253 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 255 */     PoaInsumosDTO reg = rs.cargarRegistro(codigoInsumo);
/* 256 */     rs.close();
/* 257 */     if (reg != null) {
/* 258 */       this.pagHTML.setTextCodigoInsumoEd("" + reg.getCodigoInsumo());
/* 259 */       this.pagHTML.setTextDescrpcionEd("" + reg.getDescrpcion());
/* 260 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/* 261 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 262 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 263 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 264 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 266 */       this.pagHTML.getElementCodigoInsumoKey().setValue("" + reg.getCodigoInsumo());
/* 267 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 269 */     activarVista("editar");
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
/* 280 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 282 */     Varios oVarios = new Varios();
/* 283 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Poa_PoaInsumosAct");
/* 284 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Poa_PoaInsumosDel");
/* 285 */     if (!oPermisoAct) {
/* 286 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 287 */       elem.getParentNode().removeChild(elem);
/* 288 */       elem = this.pagHTML.getElementBtnGrabar();
/* 289 */       elem.getParentNode().removeChild(elem);
/* 290 */       elem = this.pagHTML.getElementBtnModificar();
/* 291 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 293 */     if (!oPermisoDel) {
/* 294 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 295 */       elem.getParentNode().removeChild(elem);
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
/* 306 */     if (!vista.equals("nuevo")) {
/* 307 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 308 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 310 */     if (!vista.equals("editar")) {
/* 311 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 312 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 314 */     if (!vista.equals("consulta")) {
/* 315 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 316 */       sel.getParentNode().removeChild(sel);
/* 317 */       sel = this.pagHTML.getElementDivResultados();
/* 318 */       sel.getParentNode().removeChild(sel);
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
/* 332 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 333 */     atrib.setValue(valor);
/* 334 */     return atrib;
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
/* 347 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 348 */     Element enlace = this.pagHTML.createElement("a");
/* 349 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 350 */     enlace.appendChild(hijo);
/* 351 */     Attr donde = this.pagHTML.createAttribute("href");
/* 352 */     donde.setValue(vinculo);
/* 353 */     enlace.setAttributeNode(donde);
/* 354 */     td.appendChild(enlace);
/* 355 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 356 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 366 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 367 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 368 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 369 */     return td;
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
/* 384 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 385 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 386 */     rs.close();
/* 387 */     if (dejarBlanco) {
/* 388 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 389 */       op.setValue("");
/* 390 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 391 */       combo.appendChild(op);
/*     */     } 
/* 393 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 394 */     while (iterator.hasNext()) {
/* 395 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 396 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 397 */       op.setValue("" + reg.getCodigo());
/* 398 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 399 */       if (defecto.equals(reg.getCodigo())) {
/* 400 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 401 */         escogida.setValue("on");
/* 402 */         op.setAttributeNode(escogida);
/*     */       } 
/* 404 */       combo.appendChild(op);
/*     */     } 
/* 406 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PoaInsumos.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */