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
/*     */ import sinco.business.PoaMaestroMedioVerificacionDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.data.PoaMaestroMedioVerificacionDAO;
/*     */ import sinco.presentation.PoaMaestroMedioVerificacion;
/*     */ import sinco.presentation.PoaMaestroMedioVerificacionHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PoaMaestroMedioVerificacion
/*     */   implements HttpPresentation
/*     */ {
/*     */   private PoaMaestroMedioVerificacionHTML pagHTML;
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
/*  47 */     this.pagHTML = (PoaMaestroMedioVerificacionHTML)comms.xmlcFactory.create(PoaMaestroMedioVerificacionHTML.class);
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
/*  79 */     int idMaestro = 0;
/*     */     try {
/*  81 */       idMaestro = Integer.parseInt(comms.request.getParameter("idMaestro"));
/*     */     }
/*  83 */     catch (Exception e) {
/*  84 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idMaestro"));
/*     */     } 
/*     */     
/*  87 */     int idMedioVerificacion = 0;
/*     */     try {
/*  89 */       idMedioVerificacion = Integer.parseInt(comms.request.getParameter("idMedioVerificacion"));
/*     */     }
/*  91 */     catch (Exception e) {
/*  92 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idMedioVerificacion"));
/*     */     } 
/*     */     
/*  95 */     RespuestaBD rta = new RespuestaBD();
/*  96 */     if (_operacion.equals("E")) {
/*  97 */       PoaMaestroMedioVerificacionDAO rs = new PoaMaestroMedioVerificacionDAO();
/*  98 */       if (!rs.getEstadoConexion()) {
/*  99 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 101 */       rta = rs.eliminarRegistro(idMaestro, idMedioVerificacion);
/*     */       
/* 103 */       if (!rta.isRta()) {
/* 104 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaMaestroMedioVerificacion&p1=" + rta.getMensaje()));
/*     */       }
/* 106 */       rs.close();
/* 107 */       String sPagina = "PoaMaestroMedioVerificacion.po?_operacion=X";
/* 108 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 110 */     String estado = comms.request.getParameter("estado");
/* 111 */     PoaMaestroMedioVerificacionDAO rs = new PoaMaestroMedioVerificacionDAO();
/* 112 */     if (!rs.getEstadoConexion()) {
/* 113 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 115 */     if (_operacion.equals("C")) {
/* 116 */       rta = rs.crearRegistro(idMaestro, 0, idMedioVerificacion, estado, elUsuario);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 124 */       rta = rs.modificarRegistro(idMaestro, idMedioVerificacion, estado, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     rs.close();
/* 131 */     if (!rta.isRta()) {
/* 132 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaMaestroMedioVerificacion&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 135 */     String sPagina = "PoaMaestroMedioVerificacion.po?_operacion=P&idMaestro=" + idMaestro + "&idMedioVerificacion=" + idMedioVerificacion + "";
/* 136 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 147 */     int idMaestro = 0;
/*     */     try {
/* 149 */       idMaestro = Integer.parseInt(comms.request.getParameter("idMaestro"));
/*     */     }
/* 151 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 154 */     int idMedioVerificacion = 0;
/*     */     try {
/* 156 */       idMedioVerificacion = Integer.parseInt(comms.request.getParameter("idMedioVerificacion"));
/*     */     }
/* 158 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 161 */     PoaMaestroMedioVerificacionDAO rs = new PoaMaestroMedioVerificacionDAO();
/* 162 */     if (!rs.getEstadoConexion()) {
/* 163 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 165 */     PoaMaestroMedioVerificacionDTO reg = rs.cargarRegistro(idMaestro, idMedioVerificacion);
/*     */     
/* 167 */     rs.close();
/* 168 */     if (reg != null) {
/* 169 */       this.pagHTML.getElementIdMaestro().setValue("" + reg.getIdMaestro());
/* 170 */       this.pagHTML.getElementIdMedioVerificacion().setValue("" + reg.getIdMedioVerificacion());
/* 171 */       this.pagHTML.getElementEstado().setValue("" + reg.getEstado());
/* 172 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 173 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 174 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 175 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/*     */       
/* 177 */       this.pagHTML.getElementIdMaestro().setReadOnly(true);
/* 178 */       this.pagHTML.getElementIdMedioVerificacion().setReadOnly(true);
/*     */     } 
/* 180 */     this.pagHTML.getElement_operacion().setValue("M");
/* 181 */     activarVista("nuevo");
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
/* 193 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 195 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 196 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 198 */     catch (Exception e) {}
/*     */     
/* 200 */     activarVista("nuevo");
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
/* 211 */     activarVista("consulta");
/* 212 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 217 */     PoaMaestroMedioVerificacionDAO rs = new PoaMaestroMedioVerificacionDAO();
/* 218 */     if (!rs.getEstadoConexion()) {
/* 219 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 221 */     Collection<PoaMaestroMedioVerificacionDTO> arr = rs.cargarTodos();
/* 222 */     rs.close();
/* 223 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 224 */     int cuantas = 0;
/* 225 */     Iterator<PoaMaestroMedioVerificacionDTO> iterator = arr.iterator();
/* 226 */     while (iterator.hasNext()) {
/* 227 */       PoaMaestroMedioVerificacionDTO reg = (PoaMaestroMedioVerificacionDTO)iterator.next();
/* 228 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 229 */       eltr.appendChild(newtd("" + reg.getIdMaestro()));
/* 230 */       String url = "PoaMaestroMedioVerificacion.po?_operacion=V&idMaestro=" + reg.getIdMaestro() + "&idMedioVerificacion=" + reg.getIdMedioVerificacion() + "";
/* 231 */       eltr.appendChild(newtdhref("" + reg.getIdMedioVerificacion(), url));
/* 232 */       eltr.appendChild(newtd("" + reg.getEstado()));
/* 233 */       hte.appendChild(eltr);
/* 234 */       cuantas++;
/*     */     } 
/* 236 */     arr.clear();
/* 237 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 250 */     int idMaestro = 0;
/*     */     try {
/* 252 */       idMaestro = Integer.parseInt(comms.request.getParameter("idMaestro"));
/*     */     }
/* 254 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 257 */     int idMedioVerificacion = 0;
/*     */     try {
/* 259 */       idMedioVerificacion = Integer.parseInt(comms.request.getParameter("idMedioVerificacion"));
/*     */     }
/* 261 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 264 */     PoaMaestroMedioVerificacionDAO rs = new PoaMaestroMedioVerificacionDAO();
/* 265 */     if (!rs.getEstadoConexion()) {
/* 266 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 268 */     PoaMaestroMedioVerificacionDTO reg = rs.cargarRegistro(idMaestro, idMedioVerificacion);
/*     */     
/* 270 */     rs.close();
/* 271 */     if (reg != null) {
/* 272 */       this.pagHTML.setTextIdMaestroEd("" + reg.getIdMaestro());
/* 273 */       this.pagHTML.setTextIdMedioVerificacionEd("" + reg.getIdMedioVerificacion());
/* 274 */       this.pagHTML.setTextEstadoEd("" + reg.getEstado());
/* 275 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 276 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 277 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 278 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 280 */       this.pagHTML.getElementIdMaestroKey().setValue("" + reg.getIdMaestro());
/* 281 */       this.pagHTML.getElementIdMedioVerificacionKey().setValue("" + reg.getIdMedioVerificacion());
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
/*     */   private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 295 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 297 */     Varios oVarios = new Varios();
/* 298 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Poa_PoaMaestroMedioVerificacionAct");
/* 299 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Poa_PoaMaestroMedioVerificacionDel");
/* 300 */     if (!oPermisoAct) {
/* 301 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 302 */       elem.getParentNode().removeChild(elem);
/* 303 */       elem = this.pagHTML.getElementBtnGrabar();
/* 304 */       elem.getParentNode().removeChild(elem);
/* 305 */       elem = this.pagHTML.getElementBtnModificar();
/* 306 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 308 */     if (!oPermisoDel) {
/* 309 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 310 */       elem.getParentNode().removeChild(elem);
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
/* 321 */     if (!vista.equals("nuevo")) {
/* 322 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 323 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 325 */     if (!vista.equals("editar")) {
/* 326 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 327 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 329 */     if (!vista.equals("consulta")) {
/* 330 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 331 */       sel.getParentNode().removeChild(sel);
/* 332 */       sel = this.pagHTML.getElementDivResultados();
/* 333 */       sel.getParentNode().removeChild(sel);
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
/* 347 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 348 */     atrib.setValue(valor);
/* 349 */     return atrib;
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
/* 362 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 363 */     Element enlace = this.pagHTML.createElement("a");
/* 364 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 365 */     enlace.appendChild(hijo);
/* 366 */     Attr donde = this.pagHTML.createAttribute("href");
/* 367 */     donde.setValue(vinculo);
/* 368 */     enlace.setAttributeNode(donde);
/* 369 */     td.appendChild(enlace);
/* 370 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 371 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 381 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 382 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 383 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 384 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PoaMaestroMedioVerificacion.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */