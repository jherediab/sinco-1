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
/*     */ import sinco.business.AudPlanAnualDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.data.AudPlanAnualDAO;
/*     */ import sinco.presentation.AudPlanAnual;
/*     */ import sinco.presentation.AudPlanAnualHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AudPlanAnual
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AudPlanAnualHTML pagHTML;
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
/*  47 */     this.pagHTML = (AudPlanAnualHTML)comms.xmlcFactory.create(AudPlanAnualHTML.class);
/*  48 */     permisos(comms);
/*     */ 
/*     */ 
/*     */     
/*  52 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  53 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  56 */     if (_operacion.equals("P")) {
/*  57 */       editar(comms);
/*     */     }
/*  59 */     else if (_operacion.equals("Nuevo")) {
/*  60 */       nuevo(comms);
/*     */     } 
/*     */     
/*  63 */     if (_operacion.equals("V")) {
/*  64 */       verRegistro(comms);
/*     */     }
/*  66 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  67 */     comms.response.writeDOM(this.pagHTML);
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
/*  79 */     String _operacion = comms.request.getParameter("_operacion");
/*  80 */     String elUsuario = "" + comms.session.getUser().getName();
/*  81 */     int ciclo = 0;
/*     */     try {
/*  83 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/*  85 */     catch (Exception e) {
/*  86 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=ciclo"));
/*     */     } 
/*     */     
/*  89 */     RespuestaBD rta = new RespuestaBD();
/*  90 */     if (_operacion.equals("E")) {
/*  91 */       AudPlanAnualDAO ob = new AudPlanAnualDAO();
/*  92 */       rta = ob.eliminarRegistro(ciclo);
/*  93 */       if (!rta.isRta()) {
/*  94 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorAudPlanAnual&p1=" + rta.getMensaje()));
/*     */       }
/*  96 */       String sPagina = "AudPlanAnual.po?_operacion=X";
/*  97 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/*  99 */     String objetivo = comms.request.getParameter("objetivo");
/* 100 */     String alcance = comms.request.getParameter("alcance");
/* 101 */     String criterios = comms.request.getParameter("criterios");
/* 102 */     String recursos = comms.request.getParameter("recursos");
/* 103 */     AudPlanAnualDAO ob = new AudPlanAnualDAO();
/*     */ 
/*     */     
/* 106 */     if (_operacion.equals("C")) {
/* 107 */       AudPlanAnualDTO reg = ob.cargarRegistro(ciclo);
/* 108 */       if (reg != null) {
/* 109 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorAudPlanAnual&p1=El ciclo ya existe"));
/*     */       }
/*     */     } 
/*     */     
/* 113 */     if (_operacion.equals("C")) {
/* 114 */       rta = ob.crearRegistro(ciclo, objetivo, alcance, criterios, recursos, elUsuario);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 123 */       rta = ob.modificarRegistro(ciclo, objetivo, alcance, criterios, recursos, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 131 */     if (!rta.isRta()) {
/* 132 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorAudPlanAnual&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 135 */     String sPagina = "AudPlanAnual.po?_operacion=P&ciclo=" + ciclo + "";
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
/* 147 */     int ciclo = 0;
/*     */     try {
/* 149 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 151 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 154 */     AudPlanAnualDAO ob = new AudPlanAnualDAO();
/* 155 */     AudPlanAnualDTO reg = ob.cargarRegistro(ciclo);
/* 156 */     if (reg != null) {
/* 157 */       this.pagHTML.getElementCiclo().setValue("" + reg.getCiclo());
/* 158 */       this.pagHTML.getElementObjetivo2().setValue("" + reg.getObjetivo());
/* 159 */       this.pagHTML.getElementAlcance2().setValue("" + reg.getAlcance());
/* 160 */       this.pagHTML.getElementCriterios2().setValue("" + reg.getCriterios());
/* 161 */       this.pagHTML.getElementRecursos2().setValue("" + reg.getRecursos());
/* 162 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 163 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 164 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 165 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/*     */       
/* 167 */       this.pagHTML.getElementCiclo().setReadOnly(true);
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
/* 200 */     activarVista("consulta");
/* 201 */     int ciclo = 0;
/*     */     try {
/* 203 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 205 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 208 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 213 */     AudPlanAnualDAO ob = new AudPlanAnualDAO();
/* 214 */     Collection<AudPlanAnualDTO> arr = ob.cargarTodos(ciclo);
/*     */     
/* 216 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 217 */     int cuantas = 0;
/* 218 */     Iterator<AudPlanAnualDTO> iterator = arr.iterator();
/* 219 */     while (iterator.hasNext()) {
/* 220 */       AudPlanAnualDTO reg = (AudPlanAnualDTO)iterator.next();
/* 221 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 222 */       eltr.appendChild(newtd("" + reg.getCiclo()));
/* 223 */       String url = "AudPlanAnual.po?_operacion=V&ciclo=" + reg.getCiclo() + "";
/* 224 */       eltr.appendChild(newtdhref("" + reg.getObjetivo(), url));
/* 225 */       eltr.appendChild(newtd("" + reg.getAlcance()));
/* 226 */       eltr.appendChild(newtd("" + reg.getCriterios()));
/* 227 */       eltr.appendChild(newtd("" + reg.getRecursos()));
/* 228 */       hte.appendChild(eltr);
/* 229 */       cuantas++;
/*     */     } 
/* 231 */     arr.clear();
/* 232 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 245 */     int ciclo = 0;
/*     */     try {
/* 247 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 249 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 252 */     AudPlanAnualDAO ob = new AudPlanAnualDAO();
/* 253 */     AudPlanAnualDTO reg = ob.cargarRegistro(ciclo);
/* 254 */     if (reg != null) {
/* 255 */       this.pagHTML.setTextCicloEd("" + reg.getCiclo());
/* 256 */       this.pagHTML.getElementObjetivoEd().appendChild(this.pagHTML.createCDATASection(reg.getObjetivo()));
/* 257 */       this.pagHTML.getElementAlcanceEd().appendChild(this.pagHTML.createCDATASection(reg.getAlcance()));
/* 258 */       this.pagHTML.getElementCriteriosEd().appendChild(this.pagHTML.createCDATASection(reg.getCriterios()));
/* 259 */       this.pagHTML.getElementRecursosEd().appendChild(this.pagHTML.createCDATASection(reg.getRecursos()));
/* 260 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 261 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 262 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 263 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 265 */       this.pagHTML.getElementCicloKey().setValue("" + reg.getCiclo());
/* 266 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 268 */     activarVista("editar");
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
/* 279 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 281 */     Varios oVarios = new Varios();
/* 282 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Aud_AudPlanAnualAct");
/* 283 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Aud_AudPlanAnualDel");
/* 284 */     if (!oPermisoAct) {
/* 285 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 286 */       elem.getParentNode().removeChild(elem);
/* 287 */       elem = this.pagHTML.getElementBtnGrabar();
/* 288 */       elem.getParentNode().removeChild(elem);
/* 289 */       elem = this.pagHTML.getElementBtnModificar();
/* 290 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 292 */     if (!oPermisoDel) {
/* 293 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 294 */       elem.getParentNode().removeChild(elem);
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
/* 305 */     if (!vista.equals("nuevo")) {
/* 306 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 307 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 309 */     if (!vista.equals("editar")) {
/* 310 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 311 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 313 */     if (!vista.equals("consulta")) {
/* 314 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 315 */       sel.getParentNode().removeChild(sel);
/* 316 */       sel = this.pagHTML.getElementDivResultados();
/* 317 */       sel.getParentNode().removeChild(sel);
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
/* 331 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 332 */     atrib.setValue(valor);
/* 333 */     return atrib;
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
/* 346 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 347 */     Element enlace = this.pagHTML.createElement("a");
/* 348 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 349 */     enlace.appendChild(hijo);
/* 350 */     Attr donde = this.pagHTML.createAttribute("href");
/* 351 */     donde.setValue(vinculo);
/* 352 */     enlace.setAttributeNode(donde);
/* 353 */     td.appendChild(enlace);
/* 354 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 355 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 365 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 366 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 367 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 368 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AudPlanAnual.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */