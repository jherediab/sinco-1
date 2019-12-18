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
/*     */ import sinco.business.PoaObjetivosEstrategicosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.data.PoaObjetivosEstrategicosDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.PoaObjetivosEstrategicos;
/*     */ import sinco.presentation.PoaObjetivosEstrategicosHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PoaObjetivosEstrategicos
/*     */   implements HttpPresentation
/*     */ {
/*     */   private PoaObjetivosEstrategicosHTML pagHTML;
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
/*  51 */     this.pagHTML = (PoaObjetivosEstrategicosHTML)comms.xmlcFactory.create(PoaObjetivosEstrategicosHTML.class);
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
/*  83 */     int CODIGOOBJETIVO = 0;
/*     */     try {
/*  85 */       CODIGOOBJETIVO = Integer.parseInt(comms.request.getParameter("CODIGOOBJETIVO"));
/*     */     }
/*  87 */     catch (Exception e) {
/*  88 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=CODIGOOBJETIVO"));
/*     */     } 
/*     */     
/*  91 */     RespuestaBD rta = new RespuestaBD();
/*  92 */     if (_operacion.equals("E")) {
/*  93 */       PoaObjetivosEstrategicosDAO rs = new PoaObjetivosEstrategicosDAO();
/*  94 */       if (!rs.getEstadoConexion()) {
/*  95 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*  97 */       rta = rs.eliminarRegistro(CODIGOOBJETIVO);
/*  98 */       if (!rta.isRta()) {
/*  99 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaObjetivosEstrategicos&p1=" + rta.getMensaje()));
/*     */       }
/* 101 */       rs.close();
/* 102 */       String sPagina = "PoaObjetivosEstrategicos.po?_operacion=X";
/* 103 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 105 */     String descripcion = comms.request.getParameter("descripcion");
/* 106 */     String estado = comms.request.getParameter("estado");
/* 107 */     PoaObjetivosEstrategicosDAO rs = new PoaObjetivosEstrategicosDAO();
/* 108 */     if (!rs.getEstadoConexion()) {
/* 109 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 111 */     if (_operacion.equals("C")) {
/* 112 */       rta = rs.crearRegistro(CODIGOOBJETIVO, descripcion, estado, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 117 */       CODIGOOBJETIVO = rta.getSecuencia();
/*     */     } else {
/*     */       
/* 120 */       rta = rs.modificarRegistro(CODIGOOBJETIVO, descripcion, estado, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 126 */     rs.close();
/* 127 */     if (!rta.isRta()) {
/* 128 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaObjetivosEstrategicos&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 131 */     String sPagina = "PoaObjetivosEstrategicos.po?_operacion=P&CODIGOOBJETIVO=" + CODIGOOBJETIVO + "";
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
/* 143 */     int CODIGOOBJETIVO = 0;
/*     */     try {
/* 145 */       CODIGOOBJETIVO = Integer.parseInt(comms.request.getParameter("CODIGOOBJETIVO"));
/*     */     }
/* 147 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 150 */     PoaObjetivosEstrategicosDAO rs = new PoaObjetivosEstrategicosDAO();
/* 151 */     if (!rs.getEstadoConexion()) {
/* 152 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 154 */     PoaObjetivosEstrategicosDTO reg = rs.cargarRegistro(CODIGOOBJETIVO);
/* 155 */     rs.close();
/* 156 */     if (reg != null) {
/* 157 */       this.pagHTML.getElementCODIGOOBJETIVO().setValue("" + reg.getCODIGOOBJETIVO());
/* 158 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 159 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 160 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 161 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 162 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 163 */       HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 164 */       comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), false);
/*     */ 
/*     */       
/* 167 */       this.pagHTML.getElementCODIGOOBJETIVO().setReadOnly(true);
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
/* 193 */     this.pagHTML.getElementCODIGOOBJETIVO().setReadOnly(true);
/* 194 */     this.pagHTML.getElementCODIGOOBJETIVO().setValue("0");
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
/* 206 */     String descripcion = comms.request.getParameter("descripcion");
/* 207 */     if (descripcion == null) {
/* 208 */       descripcion = "";
/*     */     }
/* 210 */     String estado = comms.request.getParameter("estado");
/* 211 */     if (estado == null) {
/* 212 */       estado = "";
/*     */     }
/* 214 */     HTMLSelectElement combo = this.pagHTML.getElementFestado();
/* 215 */     comboMultivalores(combo, "ESTADO_REGISTRO", "" + estado, true);
/*     */ 
/*     */     
/* 218 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 223 */     PoaObjetivosEstrategicosDAO rs = new PoaObjetivosEstrategicosDAO();
/* 224 */     if (!rs.getEstadoConexion()) {
/* 225 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 227 */     Collection<PoaObjetivosEstrategicosDTO> arr = rs.cargarTodos(descripcion, estado);
/*     */ 
/*     */     
/* 230 */     rs.close();
/* 231 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 232 */     int cuantas = 0;
/* 233 */     Iterator<PoaObjetivosEstrategicosDTO> iterator = arr.iterator();
/* 234 */     while (iterator.hasNext()) {
/* 235 */       PoaObjetivosEstrategicosDTO reg = (PoaObjetivosEstrategicosDTO)iterator.next();
/* 236 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 237 */       eltr.appendChild(newtd("" + reg.getCODIGOOBJETIVO()));
/* 238 */       String url = "PoaObjetivosEstrategicos.po?_operacion=V&CODIGOOBJETIVO=" + reg.getCODIGOOBJETIVO() + "";
/* 239 */       eltr.appendChild(newtdhref("" + reg.getDescripcion(), url));
/* 240 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 241 */       hte.appendChild(eltr);
/* 242 */       cuantas++;
/*     */     } 
/* 244 */     arr.clear();
/* 245 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 258 */     int CODIGOOBJETIVO = 0;
/*     */     try {
/* 260 */       CODIGOOBJETIVO = Integer.parseInt(comms.request.getParameter("CODIGOOBJETIVO"));
/*     */     }
/* 262 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 265 */     PoaObjetivosEstrategicosDAO rs = new PoaObjetivosEstrategicosDAO();
/* 266 */     if (!rs.getEstadoConexion()) {
/* 267 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 269 */     PoaObjetivosEstrategicosDTO reg = rs.cargarRegistro(CODIGOOBJETIVO);
/* 270 */     rs.close();
/* 271 */     if (reg != null) {
/* 272 */       this.pagHTML.setTextCODIGOOBJETIVOEd("" + reg.getCODIGOOBJETIVO());
/* 273 */       this.pagHTML.setTextDescripcionEd("" + reg.getDescripcion());
/* 274 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/* 275 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 276 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 277 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 278 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 280 */       this.pagHTML.getElementCODIGOOBJETIVOKey().setValue("" + reg.getCODIGOOBJETIVO());
/* 281 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 283 */     activarVista("editar");
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
/* 294 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 296 */     Varios oVarios = new Varios();
/* 297 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Poa_PoaObjetivosEstrategicosAct");
/* 298 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Poa_PoaObjetivosEstrategicosDel");
/* 299 */     if (!oPermisoAct) {
/* 300 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 301 */       elem.getParentNode().removeChild(elem);
/* 302 */       elem = this.pagHTML.getElementBtnGrabar();
/* 303 */       elem.getParentNode().removeChild(elem);
/* 304 */       elem = this.pagHTML.getElementBtnModificar();
/* 305 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 307 */     if (!oPermisoDel) {
/* 308 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 309 */       elem.getParentNode().removeChild(elem);
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
/* 320 */     if (!vista.equals("nuevo")) {
/* 321 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 322 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 324 */     if (!vista.equals("editar")) {
/* 325 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 326 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 328 */     if (!vista.equals("consulta")) {
/* 329 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 330 */       sel.getParentNode().removeChild(sel);
/* 331 */       sel = this.pagHTML.getElementDivResultados();
/* 332 */       sel.getParentNode().removeChild(sel);
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
/* 346 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 347 */     atrib.setValue(valor);
/* 348 */     return atrib;
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
/* 361 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 362 */     Element enlace = this.pagHTML.createElement("a");
/* 363 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 364 */     enlace.appendChild(hijo);
/* 365 */     Attr donde = this.pagHTML.createAttribute("href");
/* 366 */     donde.setValue(vinculo);
/* 367 */     enlace.setAttributeNode(donde);
/* 368 */     td.appendChild(enlace);
/* 369 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 370 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 380 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 381 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 382 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 383 */     return td;
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
/* 398 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 399 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 400 */     rs.close();
/* 401 */     if (dejarBlanco) {
/* 402 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 403 */       op.setValue("");
/* 404 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 405 */       combo.appendChild(op);
/*     */     } 
/* 407 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 408 */     while (iterator.hasNext()) {
/* 409 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 410 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 411 */       op.setValue("" + reg.getCodigo());
/* 412 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 413 */       if (defecto.equals(reg.getCodigo())) {
/* 414 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 415 */         escogida.setValue("on");
/* 416 */         op.setAttributeNode(escogida);
/*     */       } 
/* 418 */       combo.appendChild(op);
/*     */     } 
/* 420 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PoaObjetivosEstrategicos.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */