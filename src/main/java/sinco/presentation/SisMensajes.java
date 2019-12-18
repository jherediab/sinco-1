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
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMensajesDTO;
/*     */ import sinco.data.SisMensajesDAO;
/*     */ import sinco.presentation.SisMensajes;
/*     */ import sinco.presentation.SisMensajesHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SisMensajes
/*     */   implements HttpPresentation
/*     */ {
/*     */   private SisMensajesHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  34 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  35 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  38 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*     */     
/*  40 */     String _operacion = comms.request.getParameter("_operacion");
/*     */ 
/*     */     
/*  43 */     if (_operacion == null || _operacion.length() == 0) {
/*  44 */       _operacion = "X";
/*     */     }
/*     */     
/*  47 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  48 */       creacion(comms);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  53 */     this.pagHTML = (SisMensajesHTML)comms.xmlcFactory.create(SisMensajesHTML.class);
/*     */     
/*  55 */     permisos(comms);
/*     */     
/*  57 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  58 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  61 */     if (_operacion.equals("P")) {
/*  62 */       editar(comms);
/*     */     }
/*  64 */     else if (_operacion.equals("Nuevo")) {
/*  65 */       nuevo(comms);
/*     */     } 
/*     */     
/*  68 */     if (_operacion.equals("V")) {
/*  69 */       verRegistro(comms);
/*     */     }
/*  71 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*     */ 
/*     */     
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
/*     */   
/*     */   private void creacion(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  87 */     String _operacion = comms.request.getParameter("_operacion");
/*  88 */     String elUsuario = "" + comms.session.getUser().getName();
/*  89 */     String codigo = comms.request.getParameter("codigo");
/*  90 */     if (codigo == null) {
/*  91 */       codigo = "";
/*     */     }
/*     */     
/*  94 */     RespuestaBD rta = new RespuestaBD();
/*     */     
/*  96 */     if (_operacion.equals("E")) {
/*     */       
/*  98 */       SisMensajesDAO rs = new SisMensajesDAO();
/*  99 */       if (!rs.getEstadoConexion()) {
/* 100 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*     */       
/* 103 */       rta = rs.eliminarRegistro(codigo);
/* 104 */       rs.close();
/*     */       
/* 106 */       if (!rta.isRta()) {
/* 107 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSisMensajes&p1=" + rta.getMensaje()));
/*     */       }
/*     */       
/* 110 */       String sPagina = "SisMensajes.po?_operacion=X";
/* 111 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/*     */     
/* 114 */     String descripcion = comms.request.getParameter("descripcion");
/*     */     
/* 116 */     SisMensajesDAO rs = new SisMensajesDAO();
/* 117 */     if (!rs.getEstadoConexion()) {
/* 118 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 121 */     if (_operacion.equals("C")) {
/* 122 */       rta = rs.crearRegistro(codigo, descripcion, elUsuario);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 128 */       rta = rs.modificarRegistro(codigo, descripcion, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 133 */     rs.close();
/*     */     
/* 135 */     if (!rta.isRta()) {
/* 136 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSisMensajes&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 139 */     String sPagina = "SisMensajes.po?_operacion=P&codigo=" + codigo + "";
/* 140 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 154 */     String codigo = comms.request.getParameter("codigo");
/*     */     
/* 156 */     SisMensajesDAO rs = new SisMensajesDAO();
/*     */     
/* 158 */     if (!rs.getEstadoConexion()) {
/* 159 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 162 */     SisMensajesDTO reg = rs.cargarRegistro(codigo);
/* 163 */     rs.close();
/*     */     
/* 165 */     if (reg != null) {
/*     */       
/* 167 */       this.pagHTML.setTextCodigoEd("" + reg.getCodigo());
/* 168 */       this.pagHTML.setTextDescripcionEd("" + reg.getDescripcion());
/* 169 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 170 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 171 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 172 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 174 */       this.pagHTML.getElementCodigoKey().setValue("" + reg.getCodigo());
/* 175 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/*     */ 
/*     */     
/* 179 */     activarVista("editar");
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
/*     */   private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 191 */     String codigo = comms.request.getParameter("codigo");
/*     */     
/* 193 */     SisMensajesDAO rs = new SisMensajesDAO();
/* 194 */     if (!rs.getEstadoConexion()) {
/* 195 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 198 */     SisMensajesDTO reg = rs.cargarRegistro(codigo);
/* 199 */     rs.close();
/*     */     
/* 201 */     if (reg != null) {
/* 202 */       this.pagHTML.getElementCodigo().setValue("" + reg.getCodigo());
/* 203 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 204 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 205 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 206 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 207 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/*     */       
/* 209 */       this.pagHTML.getElementCodigo().setReadOnly(true);
/*     */     } 
/* 211 */     this.pagHTML.getElement_operacion().setValue("M");
/* 212 */     activarVista("nuevo");
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
/* 224 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     
/*     */     try {
/* 227 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 228 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 230 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 233 */     activarVista("nuevo");
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
/* 245 */     activarVista("consulta");
/*     */     
/* 247 */     String codigo = comms.request.getParameter("codigo");
/* 248 */     if (codigo == null) {
/* 249 */       codigo = "";
/*     */     }
/* 251 */     String descripcion = comms.request.getParameter("descripcion");
/* 252 */     if (descripcion == null) {
/* 253 */       descripcion = "";
/*     */     }
/* 255 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 260 */     SisMensajesDAO rs = new SisMensajesDAO();
/*     */ 
/*     */     
/* 263 */     if (!rs.getEstadoConexion()) {
/* 264 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 267 */     Collection<SisMensajesDTO> arr = rs.cargarTodos(codigo, descripcion);
/*     */ 
/*     */ 
/*     */     
/* 271 */     rs.close();
/*     */     
/* 273 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 274 */     int cuantas = 0;
/*     */     
/* 276 */     Iterator<SisMensajesDTO> iterator = arr.iterator();
/* 277 */     while (iterator.hasNext()) {
/*     */       
/* 279 */       SisMensajesDTO reg = (SisMensajesDTO)iterator.next();
/*     */       
/* 281 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/* 283 */       eltr.appendChild(newtd("" + reg.getCodigo()));
/*     */ 
/*     */       
/* 286 */       String url = "SisMensajes.po?_operacion=V&codigo=" + reg.getCodigo() + "";
/*     */       
/* 288 */       eltr.appendChild(newtdhref("" + reg.getDescripcion(), url));
/*     */       
/* 290 */       hte.appendChild(eltr);
/*     */       
/* 292 */       cuantas++;
/*     */     } 
/*     */ 
/*     */     
/* 296 */     arr.clear();
/* 297 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/*     */   private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 312 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 314 */     Varios oVarios = new Varios();
/* 315 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Adm_SisMensajesAct");
/* 316 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Adm_SisMensajesDel");
/*     */     
/* 318 */     if (!oPermisoAct) {
/* 319 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 320 */       elem.getParentNode().removeChild(elem);
/*     */       
/* 322 */       elem = this.pagHTML.getElementBtnGrabar();
/* 323 */       elem.getParentNode().removeChild(elem);
/*     */       
/* 325 */       elem = this.pagHTML.getElementBtnModificar();
/* 326 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/*     */     
/* 329 */     if (!oPermisoDel) {
/* 330 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 331 */       elem.getParentNode().removeChild(elem);
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
/*     */   private void activarVista(String vista) {
/* 343 */     if (!vista.equals("nuevo")) {
/* 344 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 345 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 347 */     if (!vista.equals("editar")) {
/* 348 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 349 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/*     */     
/* 352 */     if (!vista.equals("consulta")) {
/* 353 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 354 */       sel.getParentNode().removeChild(sel);
/* 355 */       sel = this.pagHTML.getElementDivResultados();
/* 356 */       sel.getParentNode().removeChild(sel);
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
/* 370 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 371 */     atrib.setValue(valor);
/* 372 */     return atrib;
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
/* 385 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 386 */     Element enlace = this.pagHTML.createElement("a");
/* 387 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 388 */     enlace.appendChild(hijo);
/* 389 */     Attr donde = this.pagHTML.createAttribute("href");
/* 390 */     donde.setValue(vinculo);
/* 391 */     enlace.setAttributeNode(donde);
/* 392 */     td.appendChild(enlace);
/* 393 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 394 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 404 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 405 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 406 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 407 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SisMensajes.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */