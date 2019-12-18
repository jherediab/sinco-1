/*     */ package sinco.presentation;
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import sinco.business.PdeAniosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.data.PdeAniosDAO;
/*     */ import sinco.presentation.PdeAnios;
/*     */ import sinco.presentation.PdeAniosHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ public class PdeAnios implements HttpPresentation {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  21 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  22 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  25 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  26 */     String _operacion = comms.request.getParameter("_operacion");
/*  27 */     if (_operacion == null || _operacion.length() == 0) {
/*  28 */       _operacion = "X";
/*     */     }
/*     */     
/*  31 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  32 */       creacion(comms);
/*     */     }
/*     */     
/*  35 */     this.pagHTML = (PdeAniosHTML)comms.xmlcFactory.create(PdeAniosHTML.class);
/*  36 */     permisos(comms);
/*     */ 
/*     */     
/*  39 */     int idMeta = 0;
/*     */     try {
/*  41 */       idMeta = Integer.parseInt(comms.request.getParameter("idMeta"));
/*     */     }
/*  43 */     catch (Exception e) {
/*  44 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idMeta"));
/*     */     } 
/*     */     
/*  47 */     this.pagHTML.getElementIdMetaHidden().setValue("" + idMeta);
/*  48 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  49 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  52 */     if (_operacion.equals("P")) {
/*  53 */       editar(comms);
/*     */     }
/*  55 */     else if (_operacion.equals("Nuevo")) {
/*  56 */       nuevo(comms);
/*     */     } 
/*     */     
/*  59 */     if (_operacion.equals("V")) {
/*  60 */       verRegistro(comms);
/*     */     }
/*  62 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  63 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PdeAniosHTML pagHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void creacion(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  75 */     String _operacion = comms.request.getParameter("_operacion");
/*  76 */     String elUsuario = "" + comms.session.getUser().getName();
/*  77 */     int idAnio = 0;
/*     */     try {
/*  79 */       idAnio = Integer.parseInt(comms.request.getParameter("idAnio"));
/*     */     }
/*  81 */     catch (Exception e) {
/*  82 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idAnio"));
/*     */     } 
/*     */     
/*  85 */     int idMeta = 0;
/*     */     try {
/*  87 */       idMeta = Integer.parseInt(comms.request.getParameter("idMeta"));
/*     */     }
/*  89 */     catch (Exception e) {
/*  90 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idMeta"));
/*     */     } 
/*     */     
/*  93 */     RespuestaBD rta = new RespuestaBD();
/*  94 */     if (_operacion.equals("E")) {
/*  95 */       PdeAniosDAO ob = new PdeAniosDAO();
/*  96 */       rta = ob.eliminarRegistro(idMeta);
/*     */       
/*  98 */       if (!rta.isRta()) {
/*  99 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPdeAnios&p1=" + rta.getMensaje()));
/*     */       }
/* 101 */       String sPagina = "PdeAnios.po?_operacion=X&idAnio=" + idAnio + "&idMeta=" + idMeta + "";
/* 102 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 104 */     int anio = 0;
/*     */     try {
/* 106 */       anio = Integer.parseInt(comms.request.getParameter("anio"));
/*     */     }
/* 108 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 111 */     String valor = comms.request.getParameter("valor");
/* 112 */     PdeAniosDAO ob = new PdeAniosDAO();
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
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 129 */     if (!rta.isRta()) {
/* 130 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPdeAnios&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 133 */     String sPagina = "PdeAnios.po?_operacion=P&idAnio=" + idAnio + "&idMeta=" + idMeta + "";
/* 134 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 145 */     int idAnio = 0;
/*     */     try {
/* 147 */       idAnio = Integer.parseInt(comms.request.getParameter("idAnio"));
/*     */     }
/* 149 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 152 */     int idMeta = 0;
/*     */     try {
/* 154 */       idMeta = Integer.parseInt(comms.request.getParameter("idMeta"));
/*     */     }
/* 156 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 159 */     PdeAniosDAO ob = new PdeAniosDAO();
/* 160 */     PdeAniosDTO reg = ob.cargarRegistro(idAnio, idMeta);
/*     */     
/* 162 */     if (reg != null) {
/* 163 */       this.pagHTML.getElementIdAnio().setValue("" + reg.getIdAnio());
/* 164 */       this.pagHTML.getElementAnio().setValue("" + reg.getAnio());
/*     */       
/* 166 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 167 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 168 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 169 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/*     */       
/* 171 */       this.pagHTML.getElementIdAnio().setReadOnly(true);
/*     */     } 
/* 173 */     this.pagHTML.getElement_operacion().setValue("M");
/* 174 */     activarVista("nuevo");
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
/* 186 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 188 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 189 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 191 */     catch (Exception e) {}
/*     */     
/* 193 */     activarVista("nuevo");
/* 194 */     this.pagHTML.getElementIdAnio().setReadOnly(true);
/* 195 */     this.pagHTML.getElementIdAnio().setValue("0");
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
/* 206 */     activarVista("consulta");
/* 207 */     int idMeta = 0;
/*     */     try {
/* 209 */       idMeta = Integer.parseInt(comms.request.getParameter("idMeta"));
/*     */     }
/* 211 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 214 */     if (_operacion.equals("X")) {
/*     */       return;
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
/* 250 */     int idAnio = 0;
/*     */     try {
/* 252 */       idAnio = Integer.parseInt(comms.request.getParameter("idAnio"));
/*     */     }
/* 254 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 257 */     int idMeta = 0;
/*     */     try {
/* 259 */       idMeta = Integer.parseInt(comms.request.getParameter("idMeta"));
/*     */     }
/* 261 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 264 */     PdeAniosDAO ob = new PdeAniosDAO();
/* 265 */     PdeAniosDTO reg = ob.cargarRegistro(idAnio, idMeta);
/*     */     
/* 267 */     if (reg != null) {
/* 268 */       this.pagHTML.setTextIdAnioEd("" + reg.getIdAnio());
/* 269 */       this.pagHTML.setTextIdMetaEd("" + reg.getIdMeta());
/* 270 */       this.pagHTML.setTextAnioEd("" + reg.getAnio());
/*     */       
/* 272 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 273 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 274 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 275 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 277 */       this.pagHTML.getElementIdAnioKey().setValue("" + reg.getIdAnio());
/* 278 */       this.pagHTML.getElementIdMetaKey().setValue("" + reg.getIdMeta());
/* 279 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 281 */     activarVista("editar");
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
/* 292 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 294 */     Varios oVarios = new Varios();
/* 295 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "ecoPdeAniosAct");
/* 296 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "ecoPdeAniosDel");
/* 297 */     if (!oPermisoAct) {
/* 298 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 299 */       elem.getParentNode().removeChild(elem);
/* 300 */       elem = this.pagHTML.getElementBtnGrabar();
/* 301 */       elem.getParentNode().removeChild(elem);
/* 302 */       elem = this.pagHTML.getElementBtnModificar();
/* 303 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 305 */     if (!oPermisoDel) {
/* 306 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 307 */       elem.getParentNode().removeChild(elem);
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
/* 318 */     if (!vista.equals("nuevo")) {
/* 319 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 320 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 322 */     if (!vista.equals("editar")) {
/* 323 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 324 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 326 */     if (!vista.equals("consulta")) {
/* 327 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 328 */       sel.getParentNode().removeChild(sel);
/* 329 */       sel = this.pagHTML.getElementDivResultados();
/* 330 */       sel.getParentNode().removeChild(sel);
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
/* 344 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 345 */     atrib.setValue(valor);
/* 346 */     return atrib;
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
/* 359 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 360 */     Element enlace = this.pagHTML.createElement("a");
/* 361 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 362 */     enlace.appendChild(hijo);
/* 363 */     Attr donde = this.pagHTML.createAttribute("href");
/* 364 */     donde.setValue(vinculo);
/* 365 */     enlace.setAttributeNode(donde);
/* 366 */     td.appendChild(enlace);
/* 367 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 368 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 378 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 379 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 380 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 381 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PdeAnios.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */