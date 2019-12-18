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
/*     */ import sinco.business.AudGrupoAuditorDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.AudGrupoAuditorDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.AudGrupoAuditor;
/*     */ import sinco.presentation.AudGrupoAuditorHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AudGrupoAuditor
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AudGrupoAuditorHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  39 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  40 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  43 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  44 */     String _operacion = comms.request.getParameter("_operacion");
/*  45 */     if (_operacion == null || _operacion.length() == 0) {
/*  46 */       _operacion = "X";
/*     */     }
/*     */     
/*  49 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  50 */       creacion(comms);
/*     */     }
/*     */     
/*  53 */     this.pagHTML = (AudGrupoAuditorHTML)comms.xmlcFactory.create(AudGrupoAuditorHTML.class);
/*  54 */     permisos(comms);
/*     */ 
/*     */     
/*  57 */     int ciclo = 0;
/*     */     try {
/*  59 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/*  61 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  64 */     String codigoProceso = comms.request.getParameter("codigoProceso");
/*     */     
/*  66 */     if (codigoProceso == null) {
/*  67 */       codigoProceso = "";
/*     */     }
/*     */     
/*  70 */     int codigoInforme = 0;
/*     */     try {
/*  72 */       codigoInforme = Integer.parseInt(comms.request.getParameter("codigoInforme"));
/*     */     }
/*  74 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  77 */     int codigoPadre = 0;
/*     */     try {
/*  79 */       codigoPadre = Integer.parseInt(comms.request.getParameter("codigoPadre"));
/*     */     }
/*  81 */     catch (Exception e) {}
/*     */ 
/*     */ 
/*     */     
/*  85 */     String codigoPadre2 = comms.request.getParameter("codigoPadre");
/*     */     
/*  87 */     if (codigoPadre2 == null) {
/*  88 */       codigoPadre2 = "";
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  94 */     String asociadoA = comms.request.getParameter("asociadoA");
/*  95 */     if (asociadoA == null) {
/*  96 */       asociadoA = "";
/*     */     }
/*  98 */     this.pagHTML.getElementCicloHidden().setValue("" + ciclo);
/*  99 */     this.pagHTML.getElementCodigoProcesoHidden().setValue("" + codigoProceso);
/* 100 */     this.pagHTML.getElementCodigoInformeHidden().setValue("" + codigoInforme);
/* 101 */     if (codigoPadre == 0) {
/* 102 */       this.pagHTML.getElementCodigoPadreHidden().setValue("" + codigoPadre2);
/*     */     } else {
/* 104 */       this.pagHTML.getElementCodigoPadreHidden().setValue("" + codigoPadre);
/*     */     } 
/*     */     
/* 107 */     this.pagHTML.getElementAsociadoAHidden().setValue("" + asociadoA);
/* 108 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/* 109 */       listar(comms, _operacion);
/*     */     }
/*     */     
/* 112 */     if (_operacion.equals("P")) {
/* 113 */       editar(comms);
/*     */     }
/* 115 */     else if (_operacion.equals("Nuevo")) {
/* 116 */       nuevo(comms);
/*     */     } 
/* 118 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/* 119 */     comms.response.writeDOM(this.pagHTML);
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
/* 131 */     String _operacion = comms.request.getParameter("_operacion");
/* 132 */     String elUsuario = "" + comms.session.getUser().getName();
/* 133 */     int codigoEmpleado = 0;
/*     */     try {
/* 135 */       codigoEmpleado = Integer.parseInt(comms.request.getParameter("codigoEmpleado"));
/*     */     }
/* 137 */     catch (Exception e) {
/* 138 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoEmpleado"));
/*     */     } 
/*     */     
/* 141 */     int consecutivo = 0;
/*     */     try {
/* 143 */       consecutivo = Integer.parseInt(comms.request.getParameter("consecutivo"));
/*     */     }
/* 145 */     catch (Exception e) {
/* 146 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=consecutivo"));
/*     */     } 
/*     */     
/* 149 */     int ciclo = 0;
/*     */     try {
/* 151 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 153 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 156 */     String codigoProceso = comms.request.getParameter("codigoProceso");
/*     */     
/* 158 */     if (codigoProceso == null) {
/* 159 */       codigoProceso = "";
/*     */     }
/*     */     
/* 162 */     int codigoPadre = 0;
/*     */     try {
/* 164 */       codigoPadre = Integer.parseInt(comms.request.getParameter("codigoPadre"));
/*     */     }
/* 166 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 169 */     String codigoPadre2 = comms.request.getParameter("codigoPadre");
/*     */     
/* 171 */     if (codigoPadre2 == null) {
/* 172 */       codigoPadre2 = "";
/*     */     }
/*     */     
/* 175 */     int codigoInforme = 0;
/*     */     try {
/* 177 */       codigoInforme = Integer.parseInt(comms.request.getParameter("codigoInforme"));
/*     */     }
/* 179 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 182 */     String asociadoA = comms.request.getParameter("asociadoA");
/* 183 */     if (asociadoA == null) {
/* 184 */       asociadoA = "";
/*     */     }
/* 186 */     RespuestaBD rta = new RespuestaBD();
/* 187 */     if (_operacion.equals("E")) {
/* 188 */       AudGrupoAuditorDAO ob = new AudGrupoAuditorDAO();
/* 189 */       rta = ob.eliminarRegistro(codigoEmpleado, consecutivo);
/*     */       
/* 191 */       if (!rta.isRta()) {
/* 192 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorAudGrupoAuditor&p1=" + rta.getMensaje()));
/*     */       }
/* 194 */       String sPagina = "";
/* 195 */       if (codigoPadre == 0) {
/* 196 */         sPagina = "AudGrupoAuditor.po?_operacion=L&ciclo=" + ciclo + "&codigoProceso=" + codigoProceso + "&codigoInforme=" + codigoInforme + "&asociadoA=" + asociadoA + "&codigoPadre=" + codigoPadre2;
/*     */       } else {
/* 198 */         sPagina = "AudGrupoAuditor.po?_operacion=L&ciclo=" + ciclo + "&codigoProceso=" + codigoProceso + "&codigoInforme=" + codigoInforme + "&asociadoA=" + asociadoA + "&codigoPadre=" + codigoPadre;
/*     */       } 
/*     */ 
/*     */       
/* 202 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 204 */     String rol = comms.request.getParameter("rol");
/* 205 */     AudGrupoAuditorDAO ob = new AudGrupoAuditorDAO();
/* 206 */     if (_operacion.equals("C")) {
/* 207 */       rta = ob.crearRegistro(codigoEmpleado, consecutivo, rol, ciclo, codigoProceso, codigoInforme, asociadoA, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 216 */       consecutivo = rta.getSecuencia();
/*     */     } else {
/*     */       
/* 219 */       rta = ob.modificarRegistro(codigoEmpleado, consecutivo, rol, ciclo, codigoProceso, codigoInforme, asociadoA, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 229 */     if (!rta.isRta()) {
/* 230 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorAudGrupoAuditor&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 233 */     String sPagina = "";
/*     */     
/* 235 */     if (codigoPadre == 0) {
/* 236 */       sPagina = "AudGrupoAuditor.po?_operacion=L&ciclo=" + ciclo + "&codigoProceso=" + codigoProceso + "&codigoInforme=" + codigoInforme + "&asociadoA=" + asociadoA + "&codigoPadre=" + codigoPadre2;
/*     */     } else {
/* 238 */       sPagina = "AudGrupoAuditor.po?_operacion=L&ciclo=" + ciclo + "&codigoProceso=" + codigoProceso + "&codigoInforme=" + codigoInforme + "&asociadoA=" + asociadoA + "&codigoPadre=" + codigoPadre;
/*     */     } 
/*     */     
/* 241 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 252 */     int codigoEmpleado = 0;
/*     */     try {
/* 254 */       codigoEmpleado = Integer.parseInt(comms.request.getParameter("codigoEmpleado"));
/*     */     }
/* 256 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 259 */     int consecutivo = 0;
/*     */     try {
/* 261 */       consecutivo = Integer.parseInt(comms.request.getParameter("consecutivo"));
/*     */     }
/* 263 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 266 */     AudGrupoAuditorDAO ob = new AudGrupoAuditorDAO();
/* 267 */     AudGrupoAuditorDTO reg = ob.cargarRegistro(codigoEmpleado, consecutivo);
/*     */     
/* 269 */     if (reg != null) {
/* 270 */       this.pagHTML.getElementConsecutivo().setValue("" + reg.getConsecutivo());
/* 271 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 272 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 273 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 274 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/*     */       
/* 276 */       HTMLSelectElement combo = this.pagHTML.getElementCodigoEmpleado();
/* 277 */       llenarCombo(combo, "sis_usuarios", "codigo_empleado", "apellidos||' '||nombres", "CODIGO_EMPLEADO=" + reg.getCodigoEmpleado(), "", false);
/* 278 */       combo = this.pagHTML.getElementRol();
/* 279 */       comboMultivalores(combo, "ROL_AUDITOR", "" + reg.getRol(), true);
/*     */ 
/*     */       
/* 282 */       this.pagHTML.getElementConsecutivo().setReadOnly(true);
/*     */     } 
/* 284 */     this.pagHTML.getElement_operacion().setValue("M");
/* 285 */     activarVista("nuevo");
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
/* 297 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 299 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 300 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 302 */     catch (Exception e) {}
/*     */     
/* 304 */     activarVista("nuevo");
/* 305 */     HTMLSelectElement combo = this.pagHTML.getElementCodigoEmpleado();
/* 306 */     llenarCombo(combo, "sis_usuarios", "codigo_empleado", "apellidos||' '||nombres", "ESTADO='A' and TIPO_AUDITOR IN ('J','S','M')", "", true);
/*     */     
/* 308 */     combo = this.pagHTML.getElementRol();
/* 309 */     comboMultivalores(combo, "ROL_AUDITOR", "", true);
/*     */     
/* 311 */     this.pagHTML.getElementConsecutivo().setValue("0");
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
/* 322 */     activarVista("consulta");
/* 323 */     int ciclo = 0;
/*     */     try {
/* 325 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 327 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 330 */     int codigoPadre = 0;
/*     */     try {
/* 332 */       codigoPadre = Integer.parseInt(comms.request.getParameter("codigoPadre"));
/*     */     }
/* 334 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 337 */     String codigoPadre2 = comms.request.getParameter("codigoPadre");
/*     */     
/* 339 */     if (codigoPadre2 == null) {
/* 340 */       codigoPadre2 = "";
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 345 */     String asociadoA = comms.request.getParameter("asociadoA");
/* 346 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 351 */     AudGrupoAuditorDAO ob = new AudGrupoAuditorDAO();
/* 352 */     Collection<AudGrupoAuditorDTO> arr = ob.cargarTodos(ciclo, codigoPadre2, codigoPadre, asociadoA);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 357 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 358 */     int cuantas = 0;
/* 359 */     Iterator<AudGrupoAuditorDTO> iterator = arr.iterator();
/* 360 */     while (iterator.hasNext()) {
/* 361 */       AudGrupoAuditorDTO reg = (AudGrupoAuditorDTO)iterator.next();
/* 362 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 363 */       String url = "AudGrupoAuditor.po?_operacion=P&codigoEmpleado=" + reg.getCodigoEmpleado() + "&consecutivo=" + reg.getConsecutivo() + "&ciclo=" + reg.getCiclo() + "&codigoProceso=" + reg.getCodigoProceso() + "&codigoInforme=" + reg.getCodigoInforme() + "&asociadoA=" + reg.getAsociadoA() + "";
/* 364 */       eltr.appendChild(newtdhref("" + reg.getNombreCodigoEmpleado(), url));
/* 365 */       eltr.appendChild(newtd("" + reg.getNombreRol()));
/* 366 */       hte.appendChild(eltr);
/* 367 */       cuantas++;
/*     */     } 
/* 369 */     arr.clear();
/* 370 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/*     */   private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 384 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 386 */     Varios oVarios = new Varios();
/* 387 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Aud_AudGrupoAuditorAct");
/* 388 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Aud_AudGrupoAuditorDel");
/* 389 */     if (!oPermisoAct) {
/* 390 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 391 */       elem.getParentNode().removeChild(elem);
/* 392 */       elem = this.pagHTML.getElementBtnGrabar();
/* 393 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 395 */     if (!oPermisoDel) {
/* 396 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 397 */       elem.getParentNode().removeChild(elem);
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
/* 408 */     if (!vista.equals("nuevo")) {
/* 409 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 410 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/*     */     
/* 413 */     if (!vista.equals("consulta")) {
/* 414 */       HTMLElement sel = this.pagHTML.getElementDivResultados();
/* 415 */       sel.getParentNode().removeChild(sel);
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
/* 429 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 430 */     atrib.setValue(valor);
/* 431 */     return atrib;
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
/* 444 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 445 */     Element enlace = this.pagHTML.createElement("a");
/* 446 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 447 */     enlace.appendChild(hijo);
/* 448 */     Attr donde = this.pagHTML.createAttribute("href");
/* 449 */     donde.setValue(vinculo);
/* 450 */     enlace.setAttributeNode(donde);
/* 451 */     td.appendChild(enlace);
/* 452 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 453 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 463 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 464 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 465 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 466 */     return td;
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
/* 481 */     SisMultiValoresDAO ob = new SisMultiValoresDAO();
/* 482 */     Collection<SisMultiValoresDTO> arr = ob.cargarTabla(tabla);
/* 483 */     ob.close();
/* 484 */     if (dejarBlanco) {
/* 485 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 486 */       op.setValue("");
/* 487 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 488 */       combo.appendChild(op);
/*     */     } 
/* 490 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 491 */     while (iterator.hasNext()) {
/* 492 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 493 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 494 */       op.setValue("" + reg.getCodigo());
/* 495 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 496 */       if (defecto.equals(reg.getCodigo())) {
/* 497 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 498 */         escogida.setValue("on");
/* 499 */         op.setAttributeNode(escogida);
/*     */       } 
/* 501 */       combo.appendChild(op);
/*     */     } 
/* 503 */     arr.clear();
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
/*     */   private void llenarCombo(HTMLSelectElement combo, String tabla, String codigo, String descripcion, String condicion, String defecto, boolean dejarBlanco) {
/* 524 */     if (dejarBlanco) {
/* 525 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 526 */       op.setValue("");
/* 527 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 528 */       combo.appendChild(op);
/*     */     } 
/* 530 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 531 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/* 532 */     rsTGen.close();
/* 533 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 534 */     while (iterator.hasNext()) {
/* 535 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 536 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 537 */       op.setValue("" + regGeneral.getCodigoS());
/* 538 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 539 */       if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
/* 540 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 541 */         escogida.setValue("on");
/* 542 */         op.setAttributeNode(escogida);
/*     */       } 
/* 544 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AudGrupoAuditor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */