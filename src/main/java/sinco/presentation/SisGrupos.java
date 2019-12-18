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
/*     */ import sinco.business.SisGruposDTO;
/*     */ import sinco.data.SisGruposDAO;
/*     */ import sinco.presentation.SisGrupos;
/*     */ import sinco.presentation.SisGruposHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SisGrupos
/*     */   implements HttpPresentation
/*     */ {
/*     */   private SisGruposHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  33 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  34 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  37 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  38 */     String _operacion = comms.request.getParameter("_operacion");
/*  39 */     if (_operacion == null || _operacion.length() == 0) {
/*  40 */       _operacion = "X";
/*     */     }
/*     */     
/*  43 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  44 */       creacion(comms);
/*     */     }
/*     */     
/*  47 */     this.pagHTML = (SisGruposHTML)comms.xmlcFactory.create(SisGruposHTML.class);
/*  48 */     permisos(comms);
/*     */     
/*  50 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  51 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  54 */     if (_operacion.equals("P")) {
/*  55 */       editar(comms);
/*     */     }
/*  57 */     else if (_operacion.equals("Nuevo")) {
/*  58 */       nuevo(comms);
/*     */     } 
/*     */     
/*  61 */     if (_operacion.equals("V")) {
/*  62 */       verRegistro(comms);
/*     */     }
/*  64 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  65 */     comms.response.writeDOM(this.pagHTML);
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
/*  77 */     String _operacion = comms.request.getParameter("_operacion");
/*  78 */     String elUsuario = "" + comms.session.getUser().getName();
/*  79 */     int codigo = 0;
/*     */     try {
/*  81 */       codigo = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/*  83 */     catch (Exception e) {
/*  84 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigo"));
/*     */     } 
/*     */     
/*  87 */     RespuestaBD rta = new RespuestaBD();
/*  88 */     if (_operacion.equals("E")) {
/*  89 */       SisGruposDAO rs = new SisGruposDAO();
/*  90 */       if (!rs.getEstadoConexion()) {
/*  91 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*  93 */       rta = rs.eliminarRegistro(codigo);
/*  94 */       if (!rta.isRta()) {
/*  95 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSisGrupos&p1=" + rta.getMensaje()));
/*     */       }
/*  97 */       rs.close();
/*  98 */       String sPagina = "SisGrupos.po?_operacion=X";
/*  99 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 101 */     String descripcion = comms.request.getParameter("descripcion");
/* 102 */     SisGruposDAO rs = new SisGruposDAO();
/* 103 */     if (!rs.getEstadoConexion()) {
/* 104 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 106 */     if (_operacion.equals("C")) {
/* 107 */       rta = rs.crearRegistro(codigo, descripcion, elUsuario);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 113 */       rta = rs.modificarRegistro(codigo, descripcion, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 118 */     rs.close();
/* 119 */     if (!rta.isRta()) {
/* 120 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSisGrupos&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 123 */     String sPagina = "SisGrupos.po?_operacion=P&codigo=" + codigo + "";
/* 124 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 135 */     int codigo = 0;
/*     */     try {
/* 137 */       codigo = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 139 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 142 */     SisGruposDAO rs = new SisGruposDAO();
/* 143 */     if (!rs.getEstadoConexion()) {
/* 144 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 146 */     SisGruposDTO reg = rs.cargarRegistro(codigo);
/* 147 */     rs.close();
/* 148 */     if (reg != null) {
/* 149 */       this.pagHTML.getElementCodigo().setValue("" + reg.getCodigo());
/* 150 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 151 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 152 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 153 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 154 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
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
/* 189 */     activarVista("consulta");
/* 190 */     int codigo = 0;
/*     */     try {
/* 192 */       codigo = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 194 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 197 */     String descripcion = comms.request.getParameter("descripcion");
/* 198 */     if (descripcion == null) {
/* 199 */       descripcion = "";
/*     */     }
/* 201 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 206 */     SisGruposDAO rs = new SisGruposDAO();
/* 207 */     if (!rs.getEstadoConexion()) {
/* 208 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 210 */     Collection<SisGruposDTO> arr = rs.cargarTodos(codigo, descripcion);
/*     */ 
/*     */     
/* 213 */     rs.close();
/* 214 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 215 */     int cuantas = 0;
/* 216 */     Iterator<SisGruposDTO> iterator = arr.iterator();
/* 217 */     while (iterator.hasNext()) {
/* 218 */       SisGruposDTO reg = (SisGruposDTO)iterator.next();
/* 219 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 220 */       eltr.appendChild(newtd("" + reg.getCodigo()));
/* 221 */       String url = "SisGrupos.po?_operacion=V&codigo=" + reg.getCodigo() + "";
/* 222 */       eltr.appendChild(newtdhref("" + reg.getDescripcion(), url));
/* 223 */       hte.appendChild(eltr);
/* 224 */       cuantas++;
/*     */     } 
/* 226 */     arr.clear();
/* 227 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 240 */     int codigo = 0;
/*     */     try {
/* 242 */       codigo = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 244 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 247 */     SisGruposDAO rs = new SisGruposDAO();
/* 248 */     if (!rs.getEstadoConexion()) {
/* 249 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 251 */     SisGruposDTO reg = rs.cargarRegistro(codigo);
/* 252 */     rs.close();
/* 253 */     if (reg != null) {
/* 254 */       this.pagHTML.setTextCodigoEd("" + reg.getCodigo());
/* 255 */       this.pagHTML.setTextDescripcionEd("" + reg.getDescripcion());
/* 256 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 257 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 258 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/* 259 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/*     */       
/* 261 */       this.pagHTML.getElementCodigoKey().setValue("" + reg.getCodigo());
/* 262 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 264 */     activarVista("editar");
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
/* 275 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 277 */     Varios oVarios = new Varios();
/* 278 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Adm_SisGruposAct");
/* 279 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Adm_SisGruposDel");
/* 280 */     if (!oPermisoAct) {
/* 281 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 282 */       elem.getParentNode().removeChild(elem);
/* 283 */       elem = this.pagHTML.getElementBtnGrabar();
/* 284 */       elem.getParentNode().removeChild(elem);
/* 285 */       elem = this.pagHTML.getElementBtnModificar();
/* 286 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 288 */     if (!oPermisoDel) {
/* 289 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 290 */       elem.getParentNode().removeChild(elem);
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
/* 301 */     if (!vista.equals("nuevo")) {
/* 302 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 303 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 305 */     if (!vista.equals("editar")) {
/* 306 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 307 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 309 */     if (!vista.equals("consulta")) {
/* 310 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 311 */       sel.getParentNode().removeChild(sel);
/* 312 */       sel = this.pagHTML.getElementDivResultados();
/* 313 */       sel.getParentNode().removeChild(sel);
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
/* 327 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 328 */     atrib.setValue(valor);
/* 329 */     return atrib;
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
/* 342 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 343 */     Element enlace = this.pagHTML.createElement("a");
/* 344 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 345 */     enlace.appendChild(hijo);
/* 346 */     Attr donde = this.pagHTML.createAttribute("href");
/* 347 */     donde.setValue(vinculo);
/* 348 */     enlace.setAttributeNode(donde);
/* 349 */     td.appendChild(enlace);
/* 350 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 351 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 361 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 362 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 363 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 364 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SisGrupos.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */