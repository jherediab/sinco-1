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
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.PdeNivelPlanDTO;
/*     */ import sinco.business.PdePlanDesarrolloDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisEntidadDTO;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.PdeNivelPlanDAO;
/*     */ import sinco.data.PdePlanDesarrolloDAO;
/*     */ import sinco.data.SisEntidadDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.PdePlanDesarrollo;
/*     */ import sinco.presentation.PdePlanDesarrolloHTML;
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
/*     */ public class PdePlanDesarrollo
/*     */   implements HttpPresentation
/*     */ {
/*     */   private PdePlanDesarrolloHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  47 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  48 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  51 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  52 */     String _operacion = comms.request.getParameter("_operacion");
/*  53 */     if (_operacion == null || _operacion.length() == 0) {
/*  54 */       _operacion = "X";
/*     */     }
/*     */     
/*  57 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  58 */       creacion(comms);
/*     */     }
/*     */     
/*  61 */     this.pagHTML = (PdePlanDesarrolloHTML)comms.xmlcFactory.create(PdePlanDesarrolloHTML.class);
/*  62 */     permisos(comms);
/*     */ 
/*     */     
/*  65 */     long nitEntidad = 0L;
/*     */     try {
/*  67 */       nitEntidad = Long.parseLong(comms.request.getParameter("nitEntidad"));
/*     */     }
/*  69 */     catch (Exception e) {
/*  70 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=nitEntidad"));
/*     */     } 
/*     */     
/*  73 */     SisEntidadDAO ent = new SisEntidadDAO();
/*  74 */     SisEntidadDTO regEnt = ent.cargarRegistro(nitEntidad);
/*  75 */     this.pagHTML.setTextPlanDesarrolloEntidad(" " + regEnt.getNombre());
/*     */     
/*  77 */     this.pagHTML.getElementNitEntidadHidden().setValue("" + nitEntidad);
/*  78 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  79 */       listar(comms, _operacion);
/*     */     }
/*  81 */     if (_operacion.equals("A")) {
/*  82 */       listarPlanes(comms, _operacion);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     if (_operacion.equals("P")) {
/*  89 */       editar(comms);
/*     */     }
/*  91 */     else if (_operacion.equals("Nuevo")) {
/*  92 */       nuevo(comms);
/*     */     } 
/*     */     
/*  95 */     if (_operacion.equals("V")) {
/*  96 */       verRegistro(comms);
/*     */     }
/*  98 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  99 */     comms.response.writeDOM(this.pagHTML);
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
/* 111 */     String _operacion = comms.request.getParameter("_operacion");
/* 112 */     String elUsuario = "" + comms.session.getUser().getName();
/* 113 */     int idPlanDesarrollo = 0;
/*     */     try {
/* 115 */       idPlanDesarrollo = Integer.parseInt(comms.request.getParameter("idPlanDesarrollo"));
/*     */     }
/* 117 */     catch (Exception e) {}
/*     */ 
/*     */ 
/*     */     
/* 121 */     long nitEntidad = 0L;
/*     */     try {
/* 123 */       nitEntidad = Long.parseLong(comms.request.getParameter("nitEntidad"));
/*     */     }
/* 125 */     catch (Exception e) {
/* 126 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=nitEntidad"));
/*     */     } 
/*     */     
/* 129 */     RespuestaBD rta = new RespuestaBD();
/* 130 */     RespuestaBD rta2 = new RespuestaBD();
/* 131 */     if (_operacion.equals("E")) {
/* 132 */       PdePlanDesarrolloDAO ob = new PdePlanDesarrolloDAO();
/* 133 */       rta = ob.eliminarRegistro(idPlanDesarrollo, nitEntidad);
/*     */       
/* 135 */       if (!rta.isRta()) {
/* 136 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPdePlanDesarrollo&p1=" + rta.getMensaje()));
/*     */       }
/* 138 */       String sPagina = "PdePlanDesarrollo.po?_operacion=X&idPlanDesarrollo=" + idPlanDesarrollo + "&nitEntidad=" + nitEntidad + "";
/* 139 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/*     */     
/* 142 */     String nombrePlanDesarrollo = comms.request.getParameter("nombrePlanDesarrollo");
/* 143 */     String aprobacion = comms.request.getParameter("aprobacion");
/* 144 */     int nroAprobacion = 0;
/*     */     try {
/* 146 */       nroAprobacion = Integer.parseInt(comms.request.getParameter("nroAprobacion"));
/*     */     }
/* 148 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 151 */     String fechaAprobacion = comms.request.getParameter("fechaAprobacion");
/* 152 */     String fechaInicial = comms.request.getParameter("fechaInicial");
/* 153 */     String fechaFinal = comms.request.getParameter("fechaFinal");
/*     */ 
/*     */     
/* 156 */     Collection<PdeNivelPlanDTO> resultados = new ArrayList<PdeNivelPlanDTO>();
/*     */     
/* 158 */     Enumeration<?> enumera = comms.request.getParameterNames();
/*     */     
/* 160 */     while (enumera.hasMoreElements()) {
/* 161 */       String param = (String)enumera.nextElement();
/*     */       
/* 163 */       if (param.startsWith("_S_")) {
/* 164 */         PdeNivelPlanDTO reg = new PdeNivelPlanDTO();
/* 165 */         int idFila = Integer.parseInt(param.substring(3));
/*     */         
/* 167 */         int numeroNivel = Integer.parseInt(comms.request.getParameter("_S_" + idFila));
/*     */ 
/*     */         
/* 170 */         if (numeroNivel > 0) {
/*     */           
/* 172 */           reg.setIdPlanDesarrollo(idPlanDesarrollo);
/*     */ 
/*     */           
/* 175 */           reg.setNombreNivel(comms.request.getParameter("_D_" + idFila));
/* 176 */           reg.setTipoNivel(numeroNivel);
/*     */           
/* 178 */           boolean myCheckBox = (comms.request.getParameter("_G_" + idFila) != null);
/* 179 */           if (myCheckBox) {
/* 180 */             reg.setObjetivoGeneral("S");
/*     */           } else {
/*     */             
/* 183 */             reg.setObjetivoGeneral("N");
/*     */           } 
/*     */           
/* 186 */           myCheckBox = (comms.request.getParameter("_E_" + idFila) != null);
/* 187 */           if (myCheckBox) {
/* 188 */             reg.setObjetivoEspecifico("S");
/*     */           } else {
/*     */             
/* 191 */             reg.setObjetivoEspecifico("N");
/*     */           } 
/*     */           
/* 194 */           myCheckBox = (comms.request.getParameter("_M_" + idFila) != null);
/* 195 */           if (myCheckBox) {
/* 196 */             reg.setMetasGeneral("S");
/*     */           } else {
/*     */             
/* 199 */             reg.setMetasGeneral("N");
/*     */           } 
/*     */           
/* 202 */           myCheckBox = (comms.request.getParameter("_N_" + idFila) != null);
/* 203 */           if (myCheckBox) {
/* 204 */             reg.setMetasEspecifico("S");
/*     */           } else {
/*     */             
/* 207 */             reg.setMetasEspecifico("N");
/*     */           } 
/* 209 */           resultados.add(reg);
/*     */         } 
/*     */       } 
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
/* 235 */     PdePlanDesarrolloDAO ob = new PdePlanDesarrolloDAO();
/* 236 */     if (_operacion.equals("C")) {
/* 237 */       rta = ob.crearRegistro(idPlanDesarrollo, nitEntidad, nombrePlanDesarrollo, aprobacion, nroAprobacion, fechaAprobacion, fechaInicial, fechaFinal, resultados, elUsuario);
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
/* 248 */       idPlanDesarrollo = rta.getSecuencia();
/*     */ 
/*     */       
/* 251 */       if (rta.isRta()) {
/*     */ 
/*     */         
/* 254 */         PdeNivelPlanDAO reg = new PdeNivelPlanDAO();
/*     */         
/* 256 */         Iterator<PdeNivelPlanDTO> iterator = resultados.iterator();
/* 257 */         while (iterator.hasNext()) {
/* 258 */           PdeNivelPlanDTO regDet = (PdeNivelPlanDTO)iterator.next();
/* 259 */           if (regDet.getTipoNivel() > 1) {
/* 260 */             rta2 = reg.crearRegistro(0, idPlanDesarrollo, 0, regDet.getNombreNivel(), regDet.getTipoNivel(), regDet.getObjetivoGeneral(), regDet.getObjetivoEspecifico(), regDet.getMetasGeneral(), regDet.getMetasEspecifico(), elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           }
/*     */           else {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 271 */             rta2 = reg.crearRegistro2(0, idPlanDesarrollo, regDet.getNombreNivel(), regDet.getTipoNivel(), regDet.getObjetivoGeneral(), regDet.getObjetivoEspecifico(), regDet.getMetasGeneral(), regDet.getMetasEspecifico(), elUsuario);
/*     */           } 
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
/* 285 */           if (!rta2.isRta()) {
/* 286 */             throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPdeNivelesDesarrollo&p1=" + rta2.getMensaje()));
/*     */           }
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 293 */       rta = ob.modificarRegistro(idPlanDesarrollo, nitEntidad, nombrePlanDesarrollo, aprobacion, nroAprobacion, fechaAprobacion, fechaInicial, fechaFinal, elUsuario);
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
/* 304 */       if (rta.isRta()) {
/* 305 */         PdeNivelPlanDAO reg = new PdeNivelPlanDAO();
/*     */         
/* 307 */         Iterator<PdeNivelPlanDTO> iterator = resultados.iterator();
/* 308 */         while (iterator.hasNext()) {
/* 309 */           PdeNivelPlanDTO regDet = (PdeNivelPlanDTO)iterator.next();
/* 310 */           rta2 = reg.modificarRegistro(regDet.getIdPlanDesarrollo(), regDet.getNombreNivel(), regDet.getObjetivoGeneral(), regDet.getObjetivoEspecifico(), regDet.getMetasGeneral(), regDet.getMetasEspecifico(), elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 318 */           if (!rta2.isRta()) {
/* 319 */             throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPdeNivelesPlan&p1=" + rta2.getMensaje()));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 326 */     if (!rta.isRta()) {
/* 327 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPdePlanDesarrollo&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 330 */     String sPagina = "PdePlanDesarrollo.po?_operacion=V&idPlanDesarrollo=" + idPlanDesarrollo + "&nitEntidad=" + nitEntidad + "";
/* 331 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 342 */     int idPlanDesarrollo = 0;
/*     */     try {
/* 344 */       idPlanDesarrollo = Integer.parseInt(comms.request.getParameter("idPlanDesarrollo"));
/*     */     }
/* 346 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 349 */     long nitEntidad = 0L;
/*     */     try {
/* 351 */       nitEntidad = Long.parseLong(comms.request.getParameter("nitEntidad"));
/*     */     }
/* 353 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 356 */     PdePlanDesarrolloDAO ob = new PdePlanDesarrolloDAO();
/* 357 */     PdePlanDesarrolloDTO reg = ob.cargarRegistro(idPlanDesarrollo, nitEntidad);
/*     */     
/* 359 */     PdeNivelPlanDAO nivelDao = new PdeNivelPlanDAO();
/* 360 */     int niveles = ob.contarNivelesPlan(idPlanDesarrollo);
/* 361 */     int variable = 0;
/* 362 */     int PrimerNivel = nivelDao.cargarPrimerNivel(idPlanDesarrollo);
/* 363 */     if (reg != null) {
/* 364 */       this.pagHTML.getElementIdPlanDesarrollo().setValue("" + reg.getIdPlanDesarrollo());
/* 365 */       this.pagHTML.getElementNombrePlanDesarrollo().setValue("" + reg.getNombrePlanDesarrollo());
/* 366 */       this.pagHTML.getElementNroAprobacion().setValue("" + reg.getNroAprobacion());
/* 367 */       this.pagHTML.getElementFechaAprobacion().setValue("" + Utilidades.darFormatoFecha(reg.getFechaAprobacion()));
/* 368 */       this.pagHTML.getElementFechaInicial().setValue("" + Utilidades.darFormatoFecha(reg.getFechaInicial()));
/* 369 */       this.pagHTML.getElementFechaFinal().setValue("" + Utilidades.darFormatoFecha(reg.getFechaFinal()));
/* 370 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 371 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 372 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 373 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/*     */ 
/*     */       
/* 376 */       HTMLSelectElement combo = this.pagHTML.getElementNumeroNiveles();
/* 377 */       comboMultivalores(combo, "niveles_plan_desarrollo", "" + niveles, true);
/*     */       
/* 379 */       combo = this.pagHTML.getElementAprobacion();
/* 380 */       comboMultivalores(combo, "aprobacion_plan", "" + reg.getAprobacion(), true);
/*     */ 
/*     */       
/* 383 */       HTMLTableSectionElement hte = (HTMLTableSectionElement)this.pagHTML.getElementTblNiveles().getElementsByTagName("TBODY").item(0);
/* 384 */       while (variable < niveles) {
/* 385 */         HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 386 */         PdeNivelPlanDTO nivelDTO = nivelDao.cargarRegistro(PrimerNivel, idPlanDesarrollo, 0);
/* 387 */         eltr.setAttribute("id", "FilaLineas" + nivelDTO.getTipoNivel());
/* 388 */         eltr.appendChild(newinputNumeroNivel(nivelDTO.getTipoNivel(), "" + nivelDTO.getTipoNivel()));
/*     */         
/* 390 */         eltr.appendChild(newinputNombre(nivelDTO.getTipoNivel(), "" + nivelDTO.getNombreNivel()));
/* 391 */         eltr.appendChild(newRadioButton(nivelDTO.getTipoNivel(), "G", "" + nivelDTO.getObjetivoGeneral()));
/* 392 */         eltr.appendChild(newRadioButton(nivelDTO.getTipoNivel(), "M", "" + nivelDTO.getMetasGeneral()));
/* 393 */         eltr.appendChild(newtdEmpty());
/* 394 */         eltr.appendChild(newRadioButton(nivelDTO.getTipoNivel(), "E", "" + nivelDTO.getObjetivoEspecifico()));
/* 395 */         eltr.appendChild(newRadioButton(nivelDTO.getTipoNivel(), "N", "" + nivelDTO.getMetasEspecifico()));
/* 396 */         hte.appendChild(eltr);
/* 397 */         variable++;
/* 398 */         PrimerNivel++;
/*     */       } 
/*     */ 
/*     */       
/* 402 */       this.pagHTML.getElementNumeroNiveles().setDisabled(true);
/*     */ 
/*     */       
/* 405 */       this.pagHTML.getElementIdPlanDesarrollo().setReadOnly(true);
/* 406 */       this.pagHTML.getElementIdPlanDesarrolloKey().setValue("" + idPlanDesarrollo);
/*     */     } 
/* 408 */     this.pagHTML.getElement_operacion().setValue("M");
/* 409 */     activarVista("nuevo");
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
/* 421 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 423 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 424 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 426 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 429 */     HTMLSelectElement combo = this.pagHTML.getElementAprobacion();
/* 430 */     comboMultivalores(combo, "aprobacion_plan", "", true);
/*     */     
/* 432 */     combo = this.pagHTML.getElementNumeroNiveles();
/* 433 */     comboMultivalores(combo, "niveles_plan_desarrollo", "", true);
/*     */     
/* 435 */     activarVista("nuevo");
/* 436 */     this.pagHTML.getElementIdPlanDesarrollo().setReadOnly(true);
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
/* 447 */     activarVista("consulta");
/*     */     
/* 449 */     long nitEntidad = 0L;
/*     */     try {
/* 451 */       nitEntidad = Long.parseLong(comms.request.getParameter("nitEntidad"));
/*     */     }
/* 453 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 456 */     String nombrePlanDesarrollo = comms.request.getParameter("nombrePlanDesarrollo");
/* 457 */     if (nombrePlanDesarrollo == null) {
/* 458 */       nombrePlanDesarrollo = "";
/*     */     }
/* 460 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */     
/* 464 */     HTMLElement td2 = this.pagHTML.getElementReport();
/* 465 */     td2.getParentNode().removeChild(td2);
/*     */     
/* 467 */     HTMLElement td = this.pagHTML.getElementPie();
/* 468 */     td.setAttribute("colspan", "4");
/*     */ 
/*     */     
/* 471 */     PdePlanDesarrolloDAO ob = new PdePlanDesarrolloDAO();
/* 472 */     Collection<PdePlanDesarrolloDTO> arr = ob.cargarTodos(nitEntidad, nombrePlanDesarrollo);
/*     */ 
/*     */     
/* 475 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 476 */     int cuantas = 0;
/* 477 */     Iterator<PdePlanDesarrolloDTO> iterator = arr.iterator();
/* 478 */     while (iterator.hasNext()) {
/* 479 */       PdePlanDesarrolloDTO reg = (PdePlanDesarrolloDTO)iterator.next();
/* 480 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 481 */       String url = "PdePlanDesarrollo.po?_operacion=V&idPlanDesarrollo=" + reg.getIdPlanDesarrollo() + "&nitEntidad=" + reg.getNitEntidad() + "";
/* 482 */       eltr.appendChild(newtdhref("" + (cuantas + 1), url));
/* 483 */       eltr.appendChild(newtdhref("" + reg.getNombrePlanDesarrollo(), url));
/* 484 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaInicial())));
/* 485 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaFinal())));
/* 486 */       hte.appendChild(eltr);
/* 487 */       cuantas++;
/*     */     } 
/* 489 */     arr.clear();
/* 490 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/*     */   private void listarPlanes(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
/* 502 */     activarVista("consulta");
/*     */     
/* 504 */     long nitEntidad = 0L;
/*     */     try {
/* 506 */       nitEntidad = Long.parseLong(comms.request.getParameter("nitEntidad"));
/*     */     }
/* 508 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 511 */     HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 512 */     sel.getParentNode().removeChild(sel);
/*     */     
/* 514 */     sel = this.pagHTML.getElementBtnCrear();
/* 515 */     sel.getParentNode().removeChild(sel);
/*     */     
/* 517 */     sel = this.pagHTML.getElementBtnConsultar();
/* 518 */     sel.getParentNode().removeChild(sel);
/*     */     
/* 520 */     sel = this.pagHTML.getElementBtnVolver();
/* 521 */     sel.setAttribute("onClick", "volverEntidad()");
/*     */     
/* 523 */     this.pagHTML.setTextPlanDesarrolloEntidad("");
/* 524 */     sel = this.pagHTML.getElementPlanDesarrolloEntidad();
/* 525 */     sel.setAttribute("innerHTML", "Seleccione Plan");
/*     */     
/* 527 */     PdePlanDesarrolloDAO ob = new PdePlanDesarrolloDAO();
/* 528 */     Collection<PdePlanDesarrolloDTO> arr = ob.cargarTodos(nitEntidad, "");
/*     */ 
/*     */     
/* 531 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 532 */     int cuantas = 0;
/* 533 */     Iterator<PdePlanDesarrolloDTO> iterator = arr.iterator();
/* 534 */     while (iterator.hasNext()) {
/* 535 */       PdePlanDesarrolloDTO reg = (PdePlanDesarrolloDTO)iterator.next();
/* 536 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 537 */       String url = "PdeNivelPlan.po?_operacion=Z&idPlanDesarrollo=" + reg.getIdPlanDesarrollo() + "&nitEntidad=" + reg.getNitEntidad() + "";
/* 538 */       String url2 = "CalVerDocumento.po?numeroDocumento=planDesarrollo.xlsx&tipoDocumento=W&_operacion=VDC";
/* 539 */       eltr.appendChild(newtdhref("" + (cuantas + 1), url));
/* 540 */       eltr.appendChild(newtdhref("" + reg.getNombrePlanDesarrollo(), url));
/* 541 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaInicial())));
/* 542 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaFinal())));
/* 543 */       eltr.appendChild(newtdhref("Reporte Plan de Desarrollo", url2));
/* 544 */       hte.appendChild(eltr);
/* 545 */       cuantas++;
/*     */     } 
/* 547 */     arr.clear();
/* 548 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 561 */     int idPlanDesarrollo = 0;
/*     */     try {
/* 563 */       idPlanDesarrollo = Integer.parseInt(comms.request.getParameter("idPlanDesarrollo"));
/*     */     }
/* 565 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 568 */     long nitEntidad = 0L;
/*     */     try {
/* 570 */       nitEntidad = Long.parseLong(comms.request.getParameter("nitEntidad"));
/*     */     }
/* 572 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 575 */     PdePlanDesarrolloDAO ob = new PdePlanDesarrolloDAO();
/* 576 */     PdePlanDesarrolloDTO reg = ob.cargarRegistro(idPlanDesarrollo, nitEntidad);
/*     */     
/* 578 */     if (reg != null) {
/*     */       
/* 580 */       this.pagHTML.setTextIdPlanDesarrolloEd("" + reg.getIdPlanDesarrollo());
/* 581 */       this.pagHTML.setTextNitEntidadEd("" + reg.getNitEntidad());
/* 582 */       this.pagHTML.setTextNombrePlanDesarrolloEd("" + reg.getNombrePlanDesarrollo());
/* 583 */       this.pagHTML.setTextAprobacionEd("" + (reg.getAprobacion().equals("D") ? "Decreto" : "Acuerdo"));
/* 584 */       this.pagHTML.setTextNroAprobacionEd("" + reg.getNroAprobacion());
/* 585 */       this.pagHTML.setTextFechaAprobacionEd("" + Utilidades.darFormatoFecha(reg.getFechaAprobacion()));
/* 586 */       this.pagHTML.setTextFechaInicialEd("" + Utilidades.darFormatoFecha(reg.getFechaInicial()));
/* 587 */       this.pagHTML.setTextFechaFinalEd("" + Utilidades.darFormatoFecha(reg.getFechaFinal()));
/* 588 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 589 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 590 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 591 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 593 */       PdeNivelPlanDAO obj = new PdeNivelPlanDAO();
/* 594 */       Collection<PdeNivelPlanDTO> arr = obj.cargarTodos("", idPlanDesarrollo);
/*     */ 
/*     */       
/* 597 */       HTMLTableSectionElement hte = this.pagHTML.getElementDetalleEd();
/* 598 */       Iterator<PdeNivelPlanDTO> iterator = arr.iterator();
/* 599 */       while (iterator.hasNext()) {
/* 600 */         PdeNivelPlanDTO regi = (PdeNivelPlanDTO)iterator.next();
/* 601 */         HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 602 */         eltr.appendChild(newtd("" + regi.getTipoNivel()));
/*     */         
/* 604 */         eltr.appendChild(newtd("" + regi.getNombreNivel()));
/*     */ 
/*     */         
/* 607 */         eltr.appendChild(newtd("" + (regi.getObjetivoGeneral().equals("S") ? "Si" : "No")));
/* 608 */         eltr.appendChild(newtd("" + (regi.getMetasGeneral().equals("S") ? "Si" : "No")));
/* 609 */         eltr.appendChild(newtdEmpty());
/* 610 */         eltr.appendChild(newtd("" + (regi.getObjetivoEspecifico().equals("S") ? "Si" : "No")));
/* 611 */         eltr.appendChild(newtd("" + (regi.getMetasEspecifico().equals("S") ? "Si" : "No")));
/*     */ 
/*     */ 
/*     */         
/* 615 */         hte.appendChild(eltr);
/*     */       } 
/* 617 */       arr.clear();
/*     */       
/* 619 */       this.pagHTML.getElementIdPlanDesarrolloKey().setValue("" + reg.getIdPlanDesarrollo());
/* 620 */       this.pagHTML.getElementNitEntidadKey().setValue("" + reg.getNitEntidad());
/* 621 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 623 */     activarVista("editar");
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
/* 634 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 636 */     Varios oVarios = new Varios();
/* 637 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "pdePdePlanDesarrolloAct");
/* 638 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "pdePdePlanDesarrolloDel");
/* 639 */     if (!oPermisoAct) {
/* 640 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 641 */       elem.getParentNode().removeChild(elem);
/* 642 */       elem = this.pagHTML.getElementBtnGrabar();
/* 643 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/*     */ 
/*     */     
/* 647 */     if (!oPermisoDel) {
/* 648 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 649 */       elem.getParentNode().removeChild(elem);
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
/* 660 */     if (!vista.equals("nuevo")) {
/* 661 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 662 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 664 */     if (!vista.equals("editar")) {
/* 665 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 666 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 668 */     if (!vista.equals("consulta")) {
/* 669 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 670 */       sel.getParentNode().removeChild(sel);
/* 671 */       sel = this.pagHTML.getElementDivResultados();
/* 672 */       sel.getParentNode().removeChild(sel);
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
/* 686 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 687 */     atrib.setValue(valor);
/* 688 */     return atrib;
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
/* 701 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 702 */     Element enlace = this.pagHTML.createElement("a");
/* 703 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 704 */     enlace.appendChild(hijo);
/* 705 */     Attr donde = this.pagHTML.createAttribute("href");
/* 706 */     donde.setValue(vinculo);
/* 707 */     enlace.setAttributeNode(donde);
/* 708 */     td.appendChild(enlace);
/* 709 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 710 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 720 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 721 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 722 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 723 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newinputNumeroNivel(int id, String valor) {
/* 734 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 735 */     td.setAttributeNode(newAttr("style", "text-align:center;"));
/* 736 */     HTMLElement input = (HTMLElement)this.pagHTML.createElement("input");
/* 737 */     input.setAttribute("id", "_S_" + id);
/* 738 */     input.setAttribute("name", "_S_" + id);
/* 739 */     input.setAttribute("size", "5");
/* 740 */     input.setAttribute("value", valor);
/* 741 */     input.setAttribute("maxlength", "5");
/* 742 */     input.setAttribute("readOnly", "true");
/* 743 */     input.setAttribute("class", "INP");
/* 744 */     input.setAttribute("style", "text-align:center;");
/* 745 */     td.appendChild(input);
/* 746 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newinputCodigo(int id, String valor) {
/* 757 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 758 */     td.setAttributeNode(newAttr("style", "text-align:center;"));
/* 759 */     HTMLElement input = (HTMLElement)this.pagHTML.createElement("input");
/* 760 */     input.setAttribute("id", "_C_" + id);
/* 761 */     input.setAttribute("name", "_C_" + id);
/* 762 */     input.setAttribute("size", "10");
/* 763 */     input.setAttribute("value", valor);
/* 764 */     input.setAttribute("maxLength", "10");
/* 765 */     input.setAttribute("readOnly", "true");
/* 766 */     input.setAttribute("dato", "ALFA");
/* 767 */     input.setAttribute("onkeypress", "validarTecla2");
/* 768 */     input.setAttribute("class", "INP");
/* 769 */     td.appendChild(input);
/* 770 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newinputNombre(int id, String valor) {
/* 781 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 782 */     td.setAttributeNode(newAttr("style", "text-align:center;"));
/* 783 */     HTMLElement input = (HTMLElement)this.pagHTML.createElement("input");
/* 784 */     input.setAttribute("id", "_D_" + id);
/* 785 */     input.setAttribute("name", "_D_" + id);
/* 786 */     input.setAttribute("size", "50");
/* 787 */     input.setAttribute("value", valor);
/* 788 */     input.setAttribute("maxLength", "50");
/* 789 */     input.setAttribute("dato", "ALFA");
/* 790 */     input.setAttribute("onkeypress", "validarTecla2");
/* 791 */     input.setAttribute("class", "INP");
/* 792 */     td.appendChild(input);
/* 793 */     return td;
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
/*     */   private HTMLElement newRadioButton(int id, String letra, String valor) {
/* 805 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 806 */     td.setAttributeNode(newAttr("style", "text-align:center;"));
/* 807 */     HTMLElement input = (HTMLElement)this.pagHTML.createElement("input");
/* 808 */     input.setAttribute("type", "checkbox");
/* 809 */     input.setAttribute("id", "_" + letra + "_" + id);
/* 810 */     input.setAttribute("name", "_" + letra + "_" + id);
/* 811 */     input.setAttribute("style", "width:15px;height:15px;");
/* 812 */     if (valor.equals("S")) {
/* 813 */       input.setAttribute("checked", "");
/*     */     }
/*     */     
/* 816 */     td.appendChild(input);
/* 817 */     return td;
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
/* 828 */   private HTMLElement newtdEmpty() { return (HTMLElement)this.pagHTML.createElement("td"); }
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
/*     */   private void comboMultivalores(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco) {
/* 845 */     SisMultiValoresDAO ob = new SisMultiValoresDAO();
/* 846 */     Collection<SisMultiValoresDTO> arr = ob.cargarTabla(tabla);
/* 847 */     ob.close();
/* 848 */     if (dejarBlanco) {
/* 849 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 850 */       op.setValue("");
/* 851 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 852 */       combo.appendChild(op);
/*     */     } 
/* 854 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 855 */     while (iterator.hasNext()) {
/* 856 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 857 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 858 */       op.setValue("" + reg.getCodigo());
/* 859 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 860 */       if (defecto.equals(reg.getCodigo())) {
/* 861 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 862 */         escogida.setValue("on");
/* 863 */         op.setAttributeNode(escogida);
/*     */       } 
/* 865 */       combo.appendChild(op);
/*     */     } 
/* 867 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PdePlanDesarrollo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */