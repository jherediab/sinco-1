/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.AudBloquesDTO;
/*     */ import sinco.business.AudCriteriosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.data.AudBloquesDAO;
/*     */ import sinco.data.AudCriteriosDAO;
/*     */ import sinco.presentation.AudBloques;
/*     */ import sinco.presentation.AudBloquesHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AudBloques
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AudBloquesHTML pagHTML;
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
/*  51 */     this.pagHTML = (AudBloquesHTML)comms.xmlcFactory.create(AudBloquesHTML.class);
/*  52 */     permisos(comms);
/*     */ 
/*     */     
/*  55 */     int ciclo = 0;
/*     */     try {
/*  57 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/*  59 */     catch (Exception e) {
/*  60 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=ciclo"));
/*     */     } 
/*     */     
/*  63 */     int codigoPadre = 0;
/*     */     try {
/*  65 */       codigoPadre = Integer.parseInt(comms.request.getParameter("codigoPadre"));
/*     */     }
/*  67 */     catch (Exception e) {}
/*     */ 
/*     */ 
/*     */     
/*  71 */     String codigoPadre2 = comms.request.getParameter("codigoPadre");
/*  72 */     if (codigoPadre2 == null) {
/*  73 */       codigoPadre2 = "";
/*     */     }
/*     */     
/*  76 */     String asociadoA = comms.request.getParameter("asociadoA");
/*  77 */     if (asociadoA == null) {
/*  78 */       asociadoA = "P";
/*     */     }
/*  80 */     if (codigoPadre == 0) {
/*  81 */       this.pagHTML.getElementCodigoPadreHidden().setValue("" + codigoPadre2);
/*  82 */       this.pagHTML.getElementCodigoInformeKey().setValue("" + codigoPadre2);
/*  83 */       this.pagHTML.getElementCodigoProcesoKey().setValue("" + codigoPadre2);
/*     */     } else {
/*  85 */       this.pagHTML.getElementCodigoPadreHidden().setValue("" + codigoPadre);
/*  86 */       this.pagHTML.getElementCodigoInformeKey().setValue("" + codigoPadre);
/*  87 */       this.pagHTML.getElementCodigoProcesoKey().setValue("" + codigoPadre);
/*     */     } 
/*     */ 
/*     */     
/*  91 */     this.pagHTML.getElementCicloHidden().setValue("" + ciclo);
/*  92 */     this.pagHTML.getElementAsociadoAHidden().setValue("" + asociadoA);
/*     */     
/*  94 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  95 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  98 */     if (_operacion.equals("P")) {
/*  99 */       editar(comms);
/*     */     }
/* 101 */     else if (_operacion.equals("Nuevo")) {
/* 102 */       nuevo(comms);
/*     */     } 
/* 104 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/* 105 */     comms.response.writeDOM(this.pagHTML);
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
/* 117 */     String _operacion = comms.request.getParameter("_operacion");
/* 118 */     String elUsuario = "" + comms.session.getUser().getName();
/* 119 */     int ciclo = 0;
/*     */     try {
/* 121 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 123 */     catch (Exception e) {
/* 124 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=ciclo"));
/*     */     } 
/*     */     
/* 127 */     int codigoPadre = 0;
/*     */     try {
/* 129 */       codigoPadre = Integer.parseInt(comms.request.getParameter("codigoPadre"));
/*     */     }
/* 131 */     catch (Exception e) {}
/*     */ 
/*     */ 
/*     */     
/* 135 */     String codigoPadre2 = comms.request.getParameter("codigoPadre");
/* 136 */     if (codigoPadre2 == null) {
/* 137 */       codigoPadre2 = "";
/*     */     }
/*     */     
/* 140 */     int bloque = 0;
/*     */     try {
/* 142 */       bloque = Integer.parseInt(comms.request.getParameter("bloque"));
/*     */     }
/* 144 */     catch (Exception e) {
/* 145 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=bloque"));
/*     */     } 
/*     */     
/* 148 */     String asociadoA = comms.request.getParameter("asociadoA");
/* 149 */     if (asociadoA == null) {
/* 150 */       asociadoA = "";
/*     */     }
/* 152 */     RespuestaBD rta = new RespuestaBD();
/* 153 */     if (_operacion.equals("E")) {
/* 154 */       AudBloquesDAO ob = new AudBloquesDAO();
/* 155 */       rta = ob.eliminarRegistro(ciclo, bloque);
/*     */       
/* 157 */       if (!rta.isRta()) {
/* 158 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorAudBloques&p1=" + rta.getMensaje()));
/*     */       }
/* 160 */       String sPagina = "";
/* 161 */       if (codigoPadre == 0) {
/* 162 */         sPagina = "AudBloques.po?_operacion=X&ciclo=" + ciclo + "&codigoPadre=" + codigoPadre2 + "&bloque=" + bloque + "&asociadoA=" + asociadoA + "";
/*     */       } else {
/* 164 */         sPagina = "AudBloques.po?_operacion=X&ciclo=" + ciclo + "&codigoPadre=" + codigoPadre + "&bloque=" + bloque + "&asociadoA=" + asociadoA + "";
/*     */       } 
/*     */       
/* 167 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/*     */ 
/*     */     
/* 171 */     Enumeration enumera = comms.request.getParameterNames();
/*     */ 
/*     */     
/* 174 */     Collection<AudCriteriosDTO> criterios = new ArrayList<AudCriteriosDTO>();
/* 175 */     Collection<AudBloquesDTO> preguntas = new ArrayList<AudBloquesDTO>();
/*     */     
/* 177 */     while (enumera.hasMoreElements()) {
/* 178 */       String param = (String)enumera.nextElement();
/* 179 */       if (param.substring(0, 2).equals("C_")) {
/*     */         
/* 181 */         AudCriteriosDTO cri = new AudCriteriosDTO();
/* 182 */         cri.setCriterio(param.substring(2, param.length()));
/* 183 */         criterios.add(cri); continue;
/*     */       } 
/* 185 */       if (param.substring(0, 2).equals("P_")) {
/*     */         
/* 187 */         AudBloquesDTO pre = new AudBloquesDTO();
/*     */         
/* 189 */         pre.setPregunta(Integer.parseInt(param.substring(2, param.length())));
/* 190 */         pre.setDescripcionPregunta(comms.request.getParameter(param));
/* 191 */         preguntas.add(pre);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 196 */     AudBloquesDAO ob = new AudBloquesDAO();
/* 197 */     if (_operacion.equals("C")) {
/* 198 */       rta = ob.crearRegistro(ciclo, codigoPadre2, codigoPadre, bloque, asociadoA, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 205 */       bloque = rta.getSecuencia();
/*     */     } else {
/*     */       
/* 208 */       rta = ob.modificarRegistro(ciclo, bloque, criterios, preguntas, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 215 */     if (!rta.isRta()) {
/* 216 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorAudBloques&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 219 */     String sPagina = "";
/* 220 */     if (codigoPadre == 0) {
/* 221 */       sPagina = "AudBloques.po?_operacion=P&ciclo=" + ciclo + "&codigoPadre=" + codigoPadre2 + "&bloque=" + bloque + "&asociadoA=" + asociadoA + "";
/*     */     } else {
/* 223 */       sPagina = "AudBloques.po?_operacion=P&ciclo=" + ciclo + "&codigoPadre=" + codigoPadre + "&bloque=" + bloque + "&asociadoA=" + asociadoA + "";
/*     */     } 
/*     */     
/* 226 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 237 */     int ciclo = 0;
/*     */     try {
/* 239 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 241 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 244 */     int bloque = 0;
/*     */     try {
/* 246 */       bloque = Integer.parseInt(comms.request.getParameter("bloque"));
/*     */     }
/* 248 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 251 */     AudBloquesDAO ob = new AudBloquesDAO();
/* 252 */     AudBloquesDTO reg = ob.cargarRegistro(ciclo, bloque);
/* 253 */     if (reg != null) {
/* 254 */       this.pagHTML.getElementBloque().setValue("" + reg.getBloque());
/* 255 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 256 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 257 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 258 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/*     */       
/* 260 */       this.pagHTML.getElementBloque().setReadOnly(true);
/*     */ 
/*     */       
/* 263 */       criterios(comms, ciclo, bloque);
/* 264 */       preguntas(ciclo, bloque);
/*     */     } 
/* 266 */     this.pagHTML.getElement_operacion().setValue("M");
/* 267 */     activarVista("nuevo");
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
/* 279 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 281 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 282 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 284 */     catch (Exception e) {}
/*     */     
/* 286 */     activarVista("nuevo");
/* 287 */     this.pagHTML.getElementBloque().setReadOnly(true);
/*     */     
/* 289 */     int ciclo = 0;
/*     */     try {
/* 291 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 293 */     catch (Exception e) {
/* 294 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=ciclo"));
/*     */     } 
/*     */ 
/*     */     
/* 298 */     AudBloquesDAO ob = new AudBloquesDAO();
/* 299 */     this.pagHTML.getElementBloque().setValue("" + ob.siguienteRegistro(ciclo));
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
/*     */   private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
/* 313 */     activarVista("consulta");
/* 314 */     int ciclo = 0;
/*     */     try {
/* 316 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 318 */     catch (Exception e) {
/* 319 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=ciclo"));
/*     */     } 
/*     */     
/* 322 */     int codigoPadre = 0;
/*     */     try {
/* 324 */       codigoPadre = Integer.parseInt(comms.request.getParameter("codigoPadre"));
/*     */     }
/* 326 */     catch (Exception e) {}
/*     */ 
/*     */ 
/*     */     
/* 330 */     String codigoPadre2 = comms.request.getParameter("codigoPadre");
/* 331 */     if (codigoPadre2 == null) {
/* 332 */       codigoPadre2 = "";
/*     */     }
/*     */     
/* 335 */     String asociadoA = comms.request.getParameter("asociadoA");
/* 336 */     if (asociadoA == null) {
/* 337 */       asociadoA = "";
/*     */     }
/* 339 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 344 */     AudBloquesDAO ob = new AudBloquesDAO();
/* 345 */     Collection<AudBloquesDTO> arr = ob.cargarTodos(ciclo, codigoPadre2, codigoPadre, asociadoA);
/* 346 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 347 */     int cuantas = 0;
/* 348 */     Iterator<AudBloquesDTO> iterator = arr.iterator();
/* 349 */     while (iterator.hasNext()) {
/* 350 */       AudBloquesDTO reg = (AudBloquesDTO)iterator.next();
/* 351 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 352 */       String url = "AudBloques.po?_operacion=P&ciclo=" + reg.getCiclo() + "&bloque=" + reg.getBloque() + "&codigoPadre=" + reg.getCodigoPadre() + "&asociadoA=" + reg.getAsociadoA();
/* 353 */       eltr.appendChild(newtdhref("" + reg.getBloque(), url));
/* 354 */       eltr.appendChild(newtd("" + reg.getNombreAsociadoA()));
/* 355 */       hte.appendChild(eltr);
/* 356 */       cuantas++;
/*     */     } 
/* 358 */     arr.clear();
/* 359 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 373 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 375 */     Varios oVarios = new Varios();
/* 376 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Aud_AudBloquesAct");
/* 377 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Aud_AudBloquesDel");
/* 378 */     if (!oPermisoAct) {
/* 379 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 380 */       elem.getParentNode().removeChild(elem);
/* 381 */       elem = this.pagHTML.getElementBtnGrabar();
/* 382 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 384 */     if (!oPermisoDel) {
/* 385 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 386 */       elem.getParentNode().removeChild(elem);
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
/* 397 */     if (!vista.equals("nuevo")) {
/* 398 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 399 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 401 */     if (!vista.equals("consulta")) {
/* 402 */       HTMLElement sel = this.pagHTML.getElementDivResultados();
/* 403 */       sel.getParentNode().removeChild(sel);
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
/* 417 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 418 */     atrib.setValue(valor);
/* 419 */     return atrib;
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
/* 432 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 433 */     Element enlace = this.pagHTML.createElement("a");
/* 434 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 435 */     enlace.appendChild(hijo);
/* 436 */     Attr donde = this.pagHTML.createAttribute("href");
/* 437 */     donde.setValue(vinculo);
/* 438 */     enlace.setAttributeNode(donde);
/* 439 */     td.appendChild(enlace);
/* 440 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 441 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 451 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 452 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 453 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 454 */     return td;
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
/*     */   private boolean criterios(HttpPresentationComms comms, int ciclo, int bloque) throws HttpPresentationException, KeywordValueException {
/* 472 */     AudCriteriosDAO rs = new AudCriteriosDAO();
/* 473 */     Collection<AudCriteriosDTO> arr = rs.cargarParaAsignar(ciclo, bloque);
/*     */ 
/*     */     
/* 476 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalleCri();
/* 477 */     boolean fondo = true;
/* 478 */     Iterator<AudCriteriosDTO> iterator = arr.iterator();
/* 479 */     while (iterator.hasNext()) {
/* 480 */       AudCriteriosDTO reg = (AudCriteriosDTO)iterator.next();
/*     */       
/* 482 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 483 */       fondo = !fondo;
/* 484 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 486 */       HTMLElement tdMarca = (HTMLElement)this.pagHTML.createElement("td");
/* 487 */       HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
/* 488 */       checkbox.setAttribute("type", "checkbox");
/* 489 */       checkbox.setName("C_" + reg.getCriterio());
/* 490 */       if (reg.getExiste().equals("S")) {
/* 491 */         checkbox.setChecked(true);
/*     */       }
/* 493 */       tdMarca.appendChild(checkbox);
/*     */       
/* 495 */       eltr.appendChild(tdMarca);
/* 496 */       eltr.appendChild(newtd("" + reg.getCriterio()));
/* 497 */       eltr.appendChild(newtd("" + reg.getDescripcion()));
/*     */       
/* 499 */       hte.appendChild(eltr);
/*     */     } 
/* 501 */     return true;
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
/*     */   private void preguntas(int ciclo, int bloque) {
/* 515 */     AudBloquesDAO ob = new AudBloquesDAO();
/* 516 */     Collection<AudBloquesDTO> arr = ob.cargarPreguntas(ciclo, bloque);
/*     */ 
/*     */ 
/*     */     
/* 520 */     String script = "";
/* 521 */     Iterator<AudBloquesDTO> iterator = arr.iterator();
/* 522 */     while (iterator.hasNext()) {
/* 523 */       AudBloquesDTO reg = (AudBloquesDTO)iterator.next();
/*     */       
/* 525 */       script = script + " InsertarPregunta(" + reg.getPregunta() + ",'" + reg.getDescripcionPregunta() + "');";
/*     */     } 
/* 527 */     arr.clear();
/* 528 */     this.pagHTML.setTextElScript("" + script);
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AudBloques.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */