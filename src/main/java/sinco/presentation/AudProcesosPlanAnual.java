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
/*     */ import sinco.business.AudProcesosPlanAnualDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.AudProcesosPlanAnualDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.AudProcesosPlanAnual;
/*     */ import sinco.presentation.AudProcesosPlanAnualHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AudProcesosPlanAnual
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AudProcesosPlanAnualHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  42 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  43 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  46 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  47 */     String _operacion = comms.request.getParameter("_operacion");
/*  48 */     if (_operacion == null || _operacion.length() == 0) {
/*  49 */       _operacion = "X";
/*     */     }
/*     */     
/*  52 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  53 */       creacion(comms);
/*     */     }
/*     */     
/*  56 */     this.pagHTML = (AudProcesosPlanAnualHTML)comms.xmlcFactory.create(AudProcesosPlanAnualHTML.class);
/*  57 */     permisos(comms);
/*     */ 
/*     */     
/*  60 */     int ciclo = 0;
/*     */     try {
/*  62 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/*  64 */     catch (Exception e) {
/*  65 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=ciclo"));
/*     */     } 
/*     */     
/*  68 */     this.pagHTML.getElementCicloHidden().setValue("" + ciclo);
/*  69 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  70 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  73 */     if (_operacion.equals("P")) {
/*  74 */       editar(comms);
/*     */     }
/*  76 */     else if (_operacion.equals("Nuevo")) {
/*  77 */       nuevo(comms);
/*     */     }
/*  79 */     else if (_operacion.equals("V")) {
/*  80 */       verRegistro(comms);
/*     */     } 
/*  82 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  83 */     comms.response.writeDOM(this.pagHTML);
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
/*  95 */     String _operacion = comms.request.getParameter("_operacion");
/*  96 */     String elUsuario = "" + comms.session.getUser().getName();
/*  97 */     int ciclo = 0;
/*     */     try {
/*  99 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 101 */     catch (Exception e) {
/* 102 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=ciclo"));
/*     */     } 
/*     */     
/* 105 */     String codigoProceso = comms.request.getParameter("codigoProceso");
/* 106 */     if (codigoProceso == null) {
/* 107 */       codigoProceso = "";
/*     */     }
/*     */     
/* 110 */     int areaResponsable = 0;
/*     */     try {
/* 112 */       areaResponsable = Integer.parseInt(comms.request.getParameter("areaResponsable"));
/*     */     }
/* 114 */     catch (Exception e) {
/* 115 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=areaResponsable"));
/*     */     } 
/*     */ 
/*     */     
/* 119 */     RespuestaBD rta = new RespuestaBD();
/* 120 */     if (_operacion.equals("E")) {
/* 121 */       AudProcesosPlanAnualDAO ob = new AudProcesosPlanAnualDAO();
/* 122 */       rta = ob.eliminarRegistro(ciclo, codigoProceso);
/*     */       
/* 124 */       if (!rta.isRta()) {
/* 125 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorAudProcesosPlanAnual&p1=" + rta.getMensaje()));
/*     */       }
/* 127 */       String sPagina = "AudProcesosPlanAnual.po?_operacion=X&ciclo=" + ciclo + "&codigoProceso=" + codigoProceso + "";
/* 128 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 130 */     String tipoAuditoria = comms.request.getParameter("tipoAuditoria");
/* 131 */     String coordinadorAuditoría = comms.request.getParameter("coordinadorAuditoría");
/* 132 */     String equipoAuditor = comms.request.getParameter("equipoAuditor");
/* 133 */     String mes01 = comms.request.getParameter("mes01");
/*     */     
/* 135 */     String titulo = comms.request.getParameter("tituloAuditoria");
/* 136 */     if (titulo == null) {
/* 137 */       titulo = "";
/*     */     }
/*     */     
/* 140 */     String objetivos_especificos = comms.request.getParameter("objetivosEspecificosText");
/* 141 */     if (objetivos_especificos == null) {
/* 142 */       objetivos_especificos = "";
/*     */     }
/*     */     
/* 145 */     String alcance = comms.request.getParameter("alcanceText");
/* 146 */     if (alcance == null) {
/* 147 */       alcance = "";
/*     */     }
/*     */     
/* 150 */     if (mes01 == null) {
/* 151 */       mes01 = "N";
/*     */     }
/* 153 */     String mes02 = comms.request.getParameter("mes02");
/* 154 */     if (mes02 == null) {
/* 155 */       mes02 = "N";
/*     */     }
/* 157 */     String mes03 = comms.request.getParameter("mes03");
/* 158 */     if (mes03 == null) {
/* 159 */       mes03 = "N";
/*     */     }
/* 161 */     String mes04 = comms.request.getParameter("mes04");
/* 162 */     if (mes04 == null) {
/* 163 */       mes04 = "N";
/*     */     }
/* 165 */     String mes05 = comms.request.getParameter("mes05");
/* 166 */     if (mes05 == null) {
/* 167 */       mes05 = "N";
/*     */     }
/* 169 */     String mes06 = comms.request.getParameter("mes06");
/* 170 */     if (mes06 == null) {
/* 171 */       mes06 = "N";
/*     */     }
/* 173 */     String mes07 = comms.request.getParameter("mes07");
/* 174 */     if (mes07 == null) {
/* 175 */       mes07 = "N";
/*     */     }
/* 177 */     String mes08 = comms.request.getParameter("mes08");
/* 178 */     if (mes08 == null) {
/* 179 */       mes08 = "N";
/*     */     }
/* 181 */     String mes09 = comms.request.getParameter("mes09");
/* 182 */     if (mes09 == null) {
/* 183 */       mes09 = "N";
/*     */     }
/* 185 */     String mes10 = comms.request.getParameter("mes10");
/* 186 */     if (mes10 == null) {
/* 187 */       mes10 = "N";
/*     */     }
/* 189 */     String mes11 = comms.request.getParameter("mes11");
/* 190 */     if (mes11 == null) {
/* 191 */       mes11 = "N";
/*     */     }
/* 193 */     String mes12 = comms.request.getParameter("mes12");
/* 194 */     if (mes12 == null) {
/* 195 */       mes12 = "N";
/*     */     }
/* 197 */     AudProcesosPlanAnualDAO ob = new AudProcesosPlanAnualDAO();
/*     */     
/* 199 */     if (_operacion.equals("C")) {
/* 200 */       AudProcesosPlanAnualDTO reg = ob.cargarRegistro(ciclo, codigoProceso);
/* 201 */       if (reg != null) {
/* 202 */         _operacion = "M";
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 207 */     if (_operacion.equals("C")) {
/* 208 */       rta = ob.crearRegistro(ciclo, codigoProceso, areaResponsable, tipoAuditoria, coordinadorAuditoría, equipoAuditor, mes01, mes02, mes03, mes04, mes05, mes06, mes07, mes08, mes09, mes10, mes11, mes12, titulo, objetivos_especificos, alcance, elUsuario);
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
/*     */     }
/*     */     else {
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
/* 233 */       rta = ob.modificarRegistro(ciclo, codigoProceso, areaResponsable, tipoAuditoria, coordinadorAuditoría, equipoAuditor, mes01, mes02, mes03, mes04, mes05, mes06, mes07, mes08, mes09, mes10, mes11, mes12, titulo, objetivos_especificos, alcance, elUsuario);
/*     */     } 
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
/* 257 */     if (!rta.isRta()) {
/* 258 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorAudProcesosPlanAnual&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 261 */     String sPagina = "AudProcesosPlanAnual.po?_operacion=P&ciclo=" + ciclo + "&codigoProceso=" + codigoProceso + "";
/* 262 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 273 */     int ciclo = 0;
/*     */     try {
/* 275 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 277 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 280 */     String codigoProceso = comms.request.getParameter("codigoProceso");
/* 281 */     if (codigoProceso == null) {
/* 282 */       codigoProceso = "";
/*     */     }
/*     */     
/* 285 */     AudProcesosPlanAnualDAO ob = new AudProcesosPlanAnualDAO();
/* 286 */     AudProcesosPlanAnualDTO reg = ob.cargarRegistro(ciclo, codigoProceso);
/*     */ 
/*     */     
/* 289 */     if (reg != null) {
/*     */       
/* 291 */       this.pagHTML.getElementEquipoAuditor2().setValue("" + reg.getEquipoAuditor());
/* 292 */       this.pagHTML.getElementTituloAuditoria().setValue("" + reg.getTitulo());
/* 293 */       this.pagHTML.getElementAlcance().setValue("" + reg.getAlcance());
/* 294 */       this.pagHTML.getElementObjetivosEspecificos().setValue("" + reg.getObjetivos_especificos());
/*     */       
/* 296 */       if (reg.getMes01().equals("S")) {
/* 297 */         this.pagHTML.getElementMes01().setChecked(true);
/*     */       }
/* 299 */       if (reg.getMes02().equals("S")) {
/* 300 */         this.pagHTML.getElementMes02().setChecked(true);
/*     */       }
/* 302 */       if (reg.getMes03().equals("S")) {
/* 303 */         this.pagHTML.getElementMes03().setChecked(true);
/*     */       }
/* 305 */       if (reg.getMes04().equals("S")) {
/* 306 */         this.pagHTML.getElementMes04().setChecked(true);
/*     */       }
/* 308 */       if (reg.getMes05().equals("S")) {
/* 309 */         this.pagHTML.getElementMes05().setChecked(true);
/*     */       }
/* 311 */       if (reg.getMes06().equals("S")) {
/* 312 */         this.pagHTML.getElementMes06().setChecked(true);
/*     */       }
/* 314 */       if (reg.getMes07().equals("S")) {
/* 315 */         this.pagHTML.getElementMes07().setChecked(true);
/*     */       }
/* 317 */       if (reg.getMes08().equals("S")) {
/* 318 */         this.pagHTML.getElementMes08().setChecked(true);
/*     */       }
/* 320 */       if (reg.getMes09().equals("S")) {
/* 321 */         this.pagHTML.getElementMes09().setChecked(true);
/*     */       }
/* 323 */       if (reg.getMes10().equals("S")) {
/* 324 */         this.pagHTML.getElementMes10().setChecked(true);
/*     */       }
/* 326 */       if (reg.getMes11().equals("S")) {
/* 327 */         this.pagHTML.getElementMes11().setChecked(true);
/*     */       }
/* 329 */       if (reg.getMes12().equals("S")) {
/* 330 */         this.pagHTML.getElementMes12().setChecked(true);
/*     */       }
/* 332 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 333 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 334 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 335 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/*     */ 
/*     */       
/* 338 */       HTMLSelectElement combo = this.pagHTML.getElementCodigoProceso();
/* 339 */       llenarCombo(combo, "procesos", "codigo", "Descripcion", "codigo='" + reg.getCodigoProceso() + "'", "" + reg.getCodigoProceso(), false);
/*     */       
/* 341 */       combo = this.pagHTML.getElementTipoAuditoria();
/* 342 */       comboMultivalores(combo, "CAL_TIPO_AUDITORIA", "" + reg.getTipoAuditoria(), true);
/*     */       
/* 344 */       combo = this.pagHTML.getElementCoordinadorAuditoría();
/* 345 */       comboMultivalores(combo, "CAL_COORDINADOR_AUDITORIA", "" + reg.getcoordinadorAuditoria(), true);
/*     */       
/* 347 */       combo = this.pagHTML.getElementAreaResponsable();
/* 348 */       llenarCombo(combo, "Unidades_Dependencia", "Codigo", "Descripcion", "Codigo=" + reg.getAreaResponsable(), "" + reg.getAreaResponsable(), true);
/*     */     } 
/*     */     
/* 351 */     this.pagHTML.getElement_operacion().setValue("M");
/* 352 */     activarVista("nuevo");
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
/* 364 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 366 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 367 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 369 */     catch (Exception e) {}
/*     */     
/* 371 */     activarVista("nuevo");
/* 372 */     HTMLSelectElement combo = this.pagHTML.getElementCodigoProceso();
/* 373 */     llenarCombo(combo, "procesos", "codigo", "Descripcion", "1=1", "", true);
/*     */     
/* 375 */     combo = this.pagHTML.getElementTipoAuditoria();
/* 376 */     comboMultivalores(combo, "CAL_TIPO_AUDITORIA", "", true);
/*     */     
/* 378 */     combo = this.pagHTML.getElementCoordinadorAuditoría();
/* 379 */     comboMultivalores(combo, "CAL_COORDINADOR_AUDITORIA", "", true);
/*     */     
/* 381 */     combo = this.pagHTML.getElementAreaResponsable();
/* 382 */     llenarCombo(combo, "Unidades_Dependencia", "Codigo", "Descripcion", "1=1", "", true);
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
/* 394 */     activarVista("consulta");
/* 395 */     int ciclo = 0;
/*     */     try {
/* 397 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 399 */     catch (Exception e) {
/* 400 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=ciclo"));
/*     */     } 
/*     */     
/* 403 */     String tipoAuditoria = comms.request.getParameter("tipoAuditoria");
/* 404 */     if (tipoAuditoria == null) {
/* 405 */       tipoAuditoria = "";
/*     */     }
/*     */     
/* 408 */     HTMLSelectElement combo = this.pagHTML.getElementFtipoAuditoria();
/* 409 */     comboMultivalores(combo, "CAL_TIPO_AUDITORIA", "" + tipoAuditoria, true);
/*     */ 
/*     */     
/* 412 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 417 */     AudProcesosPlanAnualDAO ob = new AudProcesosPlanAnualDAO();
/* 418 */     Collection<AudProcesosPlanAnualDTO> arr = ob.cargarTodos(ciclo, tipoAuditoria);
/*     */ 
/*     */     
/* 421 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 422 */     int cuantas = 0;
/* 423 */     Iterator<AudProcesosPlanAnualDTO> iterator = arr.iterator();
/* 424 */     while (iterator.hasNext()) {
/* 425 */       AudProcesosPlanAnualDTO reg = (AudProcesosPlanAnualDTO)iterator.next();
/* 426 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 427 */       String url = "AudProcesosPlanAnual.po?_operacion=V&ciclo=" + reg.getCiclo() + "&codigoProceso=" + reg.getCodigoProceso() + "";
/* 428 */       eltr.appendChild(newtd("" + reg.getCiclo()));
/* 429 */       eltr.appendChild(newtdhref("" + reg.getNombreCodigoProceso(), url));
/* 430 */       eltr.appendChild(newtd("" + reg.getNombreTipoAuditoria()));
/* 431 */       eltr.appendChild(newtd("" + reg.getNombrecoordinadorAuditoria()));
/* 432 */       eltr.appendChild(newtd("" + reg.getEquipoAuditor()));
/* 433 */       hte.appendChild(eltr);
/* 434 */       cuantas++;
/*     */     } 
/* 436 */     arr.clear();
/* 437 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 450 */     int ciclo = 0;
/*     */     try {
/* 452 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 454 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 457 */     String codigoProceso = comms.request.getParameter("codigoProceso");
/* 458 */     if (codigoProceso == null) {
/* 459 */       codigoProceso = "";
/*     */     }
/*     */ 
/*     */     
/* 463 */     AudProcesosPlanAnualDAO ob = new AudProcesosPlanAnualDAO();
/* 464 */     AudProcesosPlanAnualDTO reg = ob.cargarRegistro(ciclo, codigoProceso);
/*     */     
/* 466 */     if (reg != null) {
/* 467 */       this.pagHTML.setTextCicloEd("" + reg.getCiclo());
/* 468 */       this.pagHTML.setTextCodigoProcesoEd("" + reg.getNombreCodigoProceso());
/* 469 */       this.pagHTML.setTextTipoAuditoriaEd("" + reg.getNombreTipoAuditoria());
/* 470 */       this.pagHTML.setTextTituloAuditoriaEd("" + reg.getTitulo());
/* 471 */       this.pagHTML.setTextObjetivosEspecificosEd("" + reg.getObjetivos_especificos());
/* 472 */       this.pagHTML.setTextAlcanceEd("" + reg.getAlcance());
/* 473 */       this.pagHTML.setTextCoordinadorAuditoríaEd("" + reg.getNombrecoordinadorAuditoria());
/* 474 */       this.pagHTML.setTextEquipoAuditorEd("" + reg.getEquipoAuditor());
/* 475 */       this.pagHTML.setTextAreaResponsableEd("" + reg.getNombreAreaResponsable());
/* 476 */       this.pagHTML.setTextMes01Ed("" + reg.getMes01());
/* 477 */       this.pagHTML.setTextMes02Ed("" + reg.getMes02());
/* 478 */       this.pagHTML.setTextMes03Ed("" + reg.getMes03());
/* 479 */       this.pagHTML.setTextMes04Ed("" + reg.getMes04());
/* 480 */       this.pagHTML.setTextMes05Ed("" + reg.getMes05());
/* 481 */       this.pagHTML.setTextMes06Ed("" + reg.getMes06());
/* 482 */       this.pagHTML.setTextMes07Ed("" + reg.getMes07());
/* 483 */       this.pagHTML.setTextMes08Ed("" + reg.getMes08());
/* 484 */       this.pagHTML.setTextMes09Ed("" + reg.getMes09());
/* 485 */       this.pagHTML.setTextMes10Ed("" + reg.getMes10());
/* 486 */       this.pagHTML.setTextMes11Ed("" + reg.getMes11());
/* 487 */       this.pagHTML.setTextMes12Ed("" + reg.getMes12());
/* 488 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 489 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 490 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 491 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 493 */       this.pagHTML.getElementCicloKey().setValue("" + reg.getCiclo());
/* 494 */       this.pagHTML.getElementCodigoProcesoKey().setValue("" + reg.getCodigoProceso());
/* 495 */       this.pagHTML.getElementCodigoPadre().setValue("" + reg.getCodigoProceso());
/* 496 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 498 */     activarVista("editar");
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
/* 512 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 514 */     Varios oVarios = new Varios();
/* 515 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Aud_AudProcesosPlanAnualAct");
/* 516 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Aud_AudProcesosPlanAnualDel");
/* 517 */     if (!oPermisoAct) {
/* 518 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 519 */       elem.getParentNode().removeChild(elem);
/* 520 */       elem = this.pagHTML.getElementBtnGrabar();
/* 521 */       elem.getParentNode().removeChild(elem);
/* 522 */       elem = this.pagHTML.getElementBtnModificar();
/* 523 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 525 */     if (!oPermisoDel) {
/* 526 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 527 */       elem.getParentNode().removeChild(elem);
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
/* 538 */     if (!vista.equals("nuevo")) {
/* 539 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 540 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 542 */     if (!vista.equals("editar")) {
/* 543 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 544 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 546 */     if (!vista.equals("consulta")) {
/* 547 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 548 */       sel.getParentNode().removeChild(sel);
/* 549 */       sel = this.pagHTML.getElementDivResultados();
/* 550 */       sel.getParentNode().removeChild(sel);
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
/* 564 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 565 */     atrib.setValue(valor);
/* 566 */     return atrib;
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
/* 579 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 580 */     Element enlace = this.pagHTML.createElement("a");
/* 581 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 582 */     enlace.appendChild(hijo);
/* 583 */     Attr donde = this.pagHTML.createAttribute("href");
/* 584 */     donde.setValue(vinculo);
/* 585 */     enlace.setAttributeNode(donde);
/* 586 */     td.appendChild(enlace);
/* 587 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 588 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 598 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 599 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 600 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 601 */     return td;
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
/* 616 */     SisMultiValoresDAO ob = new SisMultiValoresDAO();
/* 617 */     Collection<SisMultiValoresDTO> arr = ob.cargarTabla(tabla);
/* 618 */     ob.close();
/* 619 */     if (dejarBlanco) {
/* 620 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 621 */       op.setValue("");
/* 622 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 623 */       combo.appendChild(op);
/*     */     } 
/* 625 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 626 */     while (iterator.hasNext()) {
/* 627 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 628 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 629 */       op.setValue("" + reg.getCodigo());
/* 630 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 631 */       if (defecto.equals(reg.getCodigo())) {
/* 632 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 633 */         escogida.setValue("on");
/* 634 */         op.setAttributeNode(escogida);
/*     */       } 
/* 636 */       combo.appendChild(op);
/*     */     } 
/* 638 */     arr.clear();
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
/*     */   private void comboMultivaloresUnico(HTMLSelectElement combo, String tabla, String defecto) {
/* 652 */     SisMultiValoresDAO ob = new SisMultiValoresDAO();
/* 653 */     Collection<SisMultiValoresDTO> arr = ob.cargarTabla(tabla, defecto);
/*     */     
/* 655 */     ob.close();
/* 656 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 657 */     while (iterator.hasNext()) {
/* 658 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 659 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 660 */       op.setValue("" + reg.getCodigo());
/* 661 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 662 */       if (defecto.equals(reg.getCodigo())) {
/* 663 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 664 */         escogida.setValue("on");
/* 665 */         op.setAttributeNode(escogida);
/*     */       } 
/* 667 */       combo.appendChild(op);
/*     */     } 
/* 669 */     arr.clear();
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
/*     */   private void llenarCombo(HTMLSelectElement combo, String tabla, String codigo, String descripcion, String condicion, String defecto, boolean dejarBlanco) {
/* 691 */     if (dejarBlanco) {
/* 692 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 693 */       op.setValue("");
/* 694 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 695 */       combo.appendChild(op);
/*     */     } 
/* 697 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 698 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/* 699 */     rsTGen.close();
/* 700 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 701 */     while (iterator.hasNext()) {
/* 702 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 703 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 704 */       op.setValue("" + regGeneral.getCodigo());
/* 705 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 706 */       if (defecto != null && defecto.equals(regGeneral.getCodigo())) {
/* 707 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 708 */         escogida.setValue("on");
/* 709 */         op.setAttributeNode(escogida);
/*     */       } 
/* 711 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AudProcesosPlanAnual.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */