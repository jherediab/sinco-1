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
/*     */ import sinco.business.CalDocumentosDTO;
/*     */ import sinco.business.CalObjetivosDTO;
/*     */ import sinco.business.CalProcesosDTO;
/*     */ import sinco.business.CalSubProcesosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.SisParametrosDocDTO;
/*     */ import sinco.data.CalDocumentosDAO;
/*     */ import sinco.data.CalObjetivosDAO;
/*     */ import sinco.data.CalProcesosDAO;
/*     */ import sinco.data.CalSubProcesosDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.SisParametrosDocDAO;
/*     */ import sinco.presentation.CalProcesosAct;
/*     */ import sinco.presentation.CalProcesosActHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalProcesosAct
/*     */   implements HttpPresentation
/*     */ {
/*     */   private CalProcesosActHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  45 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  46 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  49 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  50 */     String _operacion = comms.request.getParameter("_operacion");
/*  51 */     if (_operacion == null) {
/*  52 */       _operacion = "L";
/*     */     }
/*     */     
/*  55 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  56 */       creacion(comms);
/*     */     }
/*     */     
/*  59 */     this.pagHTML = (CalProcesosActHTML)comms.xmlcFactory.create(CalProcesosActHTML.class);
/*  60 */     permisos(comms);
/*     */     
/*  62 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  63 */       listar(comms, _operacion);
/*     */     
/*     */     }
/*  66 */     else if (_operacion.equals("P")) {
/*  67 */       editar(comms);
/*     */     
/*     */     }
/*  70 */     else if (_operacion.equals("V")) {
/*  71 */       verRegistro(comms);
/*     */     }
/*  73 */     else if (_operacion.equals("Nuevo")) {
/*  74 */       nuevo(comms);
/*     */     } 
/*  76 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  77 */     comms.response.writeDOM(this.pagHTML);
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
/*  89 */     String _operacion = comms.request.getParameter("_operacion");
/*  90 */     String elUsuario = "" + comms.session.getUser().getName();
/*  91 */     String proceso = comms.request.getParameter("proceso");
/*  92 */     if (proceso == null) {
/*  93 */       proceso = "";
/*     */     }
/*  95 */     int objetivoHidden = 0;
/*     */     try {
/*  97 */       objetivoHidden = Integer.parseInt(comms.request.getParameter("objetivoHidden"));
/*     */     }
/*  99 */     catch (Exception e) {}
/*     */     
/* 101 */     RespuestaBD rta = new RespuestaBD();
/* 102 */     boolean rta2 = false;
/* 103 */     if (_operacion.equals("E")) {
/* 104 */       CalObjetivosDAO rs2 = new CalObjetivosDAO();
/* 105 */       if (!rs2.getEstadoConexion()) {
/* 106 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 108 */       rta2 = rs2.eliminarRegistro(objetivoHidden);
/* 109 */       if (!rta2) {
/* 110 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCalObjetivos"));
/*     */       }
/* 112 */       rs2.close();
/*     */       
/* 114 */       CalProcesosDAO rs = new CalProcesosDAO();
/* 115 */       if (!rs.getEstadoConexion()) {
/* 116 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 118 */       rta = rs.eliminarRegistro(proceso);
/* 119 */       if (!rta.isRta()) {
/* 120 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCalProcesos&p1=" + rta.getMensaje()));
/*     */       }
/* 122 */       rs.close();
/* 123 */       String sPagina = "CalProcesosAct.po?_operacion=X";
/* 124 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 126 */     String descripcion = comms.request.getParameter("descripcion");
/* 127 */     String estado = comms.request.getParameter("estado");
/* 128 */     String tipoProceso = comms.request.getParameter("tipoProceso");
/*     */     
/* 130 */     String objetivo = comms.request.getParameter("objetivo");
/* 131 */     if (objetivo == null) {
/* 132 */       objetivo = "";
/*     */     }
/*     */     
/* 135 */     String justificacion = comms.request.getParameter("justificacion");
/* 136 */     if (justificacion == null) {
/* 137 */       justificacion = "";
/*     */     }
/* 139 */     CalProcesosDAO rs = new CalProcesosDAO();
/* 140 */     CalObjetivosDAO rs2 = new CalObjetivosDAO();
/*     */     
/* 142 */     if (!rs2.getEstadoConexion()) {
/* 143 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 146 */     if (!rs.getEstadoConexion()) {
/* 147 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 149 */     if (_operacion.equals("C")) {
/* 150 */       rta = rs.crearRegistro(proceso, descripcion, estado, tipoProceso, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 157 */       if (rta.isRta())
/*     */       {
/*     */         
/* 160 */         rta2 = rs2.crearRegistro(objetivoHidden, proceso, "", objetivo, justificacion, "G", 0, estado, "", elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 175 */       rta = rs.modificarRegistro(proceso, descripcion, estado, tipoProceso, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 182 */       if (rta.isRta()) {
/* 183 */         if (objetivoHidden == 0) {
/*     */ 
/*     */           
/* 186 */           objetivoHidden = rs2.siguienteRegistro();
/* 187 */           rta2 = rs2.crearRegistro(objetivoHidden, proceso, "", objetivo, justificacion, "G", 0, estado, "", elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 198 */           rta2 = rs2.modificarRegistro(objetivoHidden, proceso, "", objetivo, justificacion, "G", 0, estado, "", elUsuario);
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
/* 213 */     rs.close();
/* 214 */     rs2.close();
/* 215 */     if (!rta.isRta()) {
/* 216 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCalProcesos&p1=" + rta.getMensaje()));
/*     */     }
/* 218 */     if (!rta2) {
/* 219 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCalProcesos&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 222 */     String sPagina = "CalProcesosAct.po?_operacion=P&proceso=" + proceso + "&objetivoHidden=" + objetivoHidden;
/* 223 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 234 */     activarVista("consulta");
/*     */ 
/*     */     
/* 237 */     String descripcion = comms.request.getParameter("descripcion");
/* 238 */     String estado = comms.request.getParameter("estado");
/* 239 */     if (estado == null) estado = ""; 
/* 240 */     if (descripcion == null) descripcion = "";
/*     */     
/* 242 */     HTMLSelectElement combo = this.pagHTML.getElementFestado();
/* 243 */     comboMultivalores(combo, "ESTADO_REGISTRO", "" + estado, true);
/*     */     
/* 245 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 251 */     CalProcesosDAO rs = new CalProcesosDAO();
/* 252 */     if (!rs.getEstadoConexion()) {
/* 253 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 255 */     Collection arr = rs.cargarTodos(descripcion, estado);
/*     */ 
/*     */     
/* 258 */     rs.close();
/*     */     
/* 260 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 261 */     HTMLElement eltrCabecera = this.pagHTML.getElementDetalleTr();
/* 262 */     int cuantas = 0;
/* 263 */     Iterator iterator = arr.iterator();
/*     */     
/* 265 */     SisParametrosDocDAO sis = new SisParametrosDocDAO();
/* 266 */     SisParametrosDocDTO parNormo = sis.cargarRegistro(1);
/* 267 */     SisParametrosDocDTO parMapa = sis.cargarRegistro(2);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 272 */     if (parNormo.getAsociadoA().equals("P") || parNormo.getAsociadoA().equals("A")) {
/* 273 */       eltrCabecera.appendChild(newth("Normograma", "cf2"));
/*     */     }
/*     */ 
/*     */     
/* 277 */     if (parMapa.getAsociadoA().equals("P") || parMapa.getAsociadoA().equals("A"))
/*     */     {
/* 279 */       if (eltrCabecera.getChildNodes().getLength() == 11) {
/* 280 */         eltrCabecera.appendChild(newth("Mapa de Riesgos", "cf2"));
/*     */       } else {
/* 282 */         eltrCabecera.appendChild(newth("Mapa de Riesgos", "cf1"));
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
/* 295 */     while (iterator.hasNext()) {
/*     */ 
/*     */       
/* 298 */       CalProcesosDTO reg = (CalProcesosDTO)iterator.next();
/* 299 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 300 */       CalDocumentosDAO dao = new CalDocumentosDAO();
/* 301 */       CalDocumentosDTO normograma = dao.cargarNormograma(reg.getProceso(), "");
/* 302 */       CalDocumentosDTO riesgos = dao.cargarRiesgos(reg.getProceso(), "");
/*     */       
/* 304 */       CalObjetivosDAO obj = new CalObjetivosDAO();
/* 305 */       CalObjetivosDTO regObj = obj.cargarRegistroPorProceso(reg.getProceso());
/* 306 */       dao.close();
/* 307 */       obj.close();
/* 308 */       String url = "";
/* 309 */       if (regObj != null) {
/* 310 */         url = "CalProcesosAct.po?_operacion=V&proceso=" + reg.getProceso() + "&objetivoHidden=" + regObj.getCodigoObjetivo();
/*     */       } else {
/* 312 */         url = "CalProcesosAct.po?_operacion=V&proceso=" + reg.getProceso();
/*     */       } 
/*     */       
/* 315 */       eltr.appendChild(newtdhref("" + reg.getProceso(), url, false));
/* 316 */       eltr.appendChild(newtd("" + reg.getDescripcion()));
/* 317 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 318 */       eltr.appendChild(newtd("" + reg.getNombreTipoProceso()));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 323 */       if (reg.getEstado().equals("A")) {
/* 324 */         if (parNormo.getAsociadoA().equals("P") || parNormo.getAsociadoA().equals("A"))
/*     */         {
/* 326 */           if (normograma.getCodigo() != "") {
/* 327 */             eltr.appendChild(newtdhref("Cargar Normograma", "CalDocumentosAct.po?_operacion=P&codigo=" + normograma.getCodigo() + "&Doc=Normograma&proceso=" + reg.getProceso(), false));
/*     */           } else {
/*     */             
/* 330 */             eltr.appendChild(newtdhref("Cargar Normograma", "CalDocumentosAct.po?_operacion=Nuevo&Doc=Normograma&proceso=" + reg.getProceso(), false));
/*     */           } 
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 336 */         if (parMapa.getAsociadoA().equals("P") || parMapa.getAsociadoA().equals("A"))
/*     */         {
/* 338 */           if (riesgos.getCodigo() != "") {
/* 339 */             eltr.appendChild(newtdhref("Cargar Mapa de Riesgos", "CalDocumentosAct.po?_operacion=P&codigo=" + riesgos.getCodigo() + "&Doc=Riesgos&proceso=" + reg.getProceso(), false));
/*     */           } else {
/* 341 */             eltr.appendChild(newtdhref("Cargar Mapa de Riesgos", "CalDocumentosAct.po?_operacion=Nuevo&Doc=Riesgos&proceso=" + reg.getProceso(), false));
/*     */           }
/*     */         
/*     */         }
/*     */       } else {
/*     */         
/* 347 */         if (parNormo.getAsociadoA().equals("P") || parNormo.getAsociadoA().equals("A"))
/*     */         {
/* 349 */           eltr.appendChild(newtd("Cargar Normograma"));
/*     */         }
/*     */         
/* 352 */         if (parMapa.getAsociadoA().equals("P") || parMapa.getAsociadoA().equals("A"))
/*     */         {
/*     */           
/* 355 */           eltr.appendChild(newtd("Cargar Mapa de Riesgos"));
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
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
/*     */   private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 384 */     String proceso = comms.request.getParameter("proceso");
/* 385 */     int objetivoHidden = 0;
/*     */     try {
/* 387 */       objetivoHidden = Integer.parseInt(comms.request.getParameter("objetivoHidden"));
/*     */     }
/* 389 */     catch (Exception e) {}
/*     */ 
/*     */ 
/*     */     
/* 393 */     CalObjetivosDAO obj = new CalObjetivosDAO();
/* 394 */     if (!obj.getEstadoConexion()) {
/* 395 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 397 */     CalObjetivosDTO reg2 = obj.cargarRegistro(objetivoHidden);
/*     */     
/* 399 */     obj.close();
/*     */     
/* 401 */     CalProcesosDAO rs = new CalProcesosDAO();
/* 402 */     if (!rs.getEstadoConexion()) {
/* 403 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 405 */     CalProcesosDTO reg = rs.cargarRegistro(proceso);
/* 406 */     rs.close();
/*     */     
/* 408 */     if (reg != null) {
/* 409 */       this.pagHTML.getElementProceso().setValue("" + reg.getProceso());
/* 410 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/*     */       
/* 412 */       if (reg2 != null) {
/* 413 */         this.pagHTML.getElementObjetivo().setValue("" + reg2.getDescripcion());
/* 414 */         this.pagHTML.getElementJustificacion().setValue("" + reg2.getJustificacion());
/* 415 */         this.pagHTML.getElementObjetivoHidden().setValue("" + objetivoHidden);
/*     */       } 
/*     */ 
/*     */       
/* 419 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 420 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 421 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 422 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 423 */       HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 424 */       comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), true);
/*     */       
/* 426 */       combo = this.pagHTML.getElementTipoProceso();
/* 427 */       comboMultivalores(combo, "CAL_NOMBRE_TIPO_PROCESO", "" + reg.getTipoProceso(), true);
/*     */ 
/*     */       
/* 430 */       this.pagHTML.getElementProceso().setReadOnly(true);
/*     */     } 
/* 432 */     this.pagHTML.getElement_operacion().setValue("M");
/* 433 */     activarVista("nuevo");
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
/*     */   private void verRegistro(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 445 */     String proceso = comms.request.getParameter("proceso");
/* 446 */     int objetivoHidden = 0;
/*     */     try {
/* 448 */       objetivoHidden = Integer.parseInt(comms.request.getParameter("objetivoHidden"));
/*     */     }
/* 450 */     catch (Exception e) {}
/*     */     
/* 452 */     CalProcesosDAO rs = new CalProcesosDAO();
/* 453 */     if (!rs.getEstadoConexion()) {
/* 454 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 456 */     CalProcesosDTO reg = rs.cargarRegistro(proceso);
/*     */     
/* 458 */     rs.close();
/*     */     
/* 460 */     CalObjetivosDAO obj = new CalObjetivosDAO();
/*     */     
/* 462 */     if (!obj.getEstadoConexion()) {
/* 463 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 465 */     CalObjetivosDTO reg2 = obj.cargarRegistro(objetivoHidden);
/* 466 */     CalObjetivosDTO reg3 = obj.cargarRegistroPorProceso(proceso);
/* 467 */     obj.close();
/*     */ 
/*     */ 
/*     */     
/* 471 */     if (reg != null) {
/* 472 */       this.pagHTML.setTextCodigoEd("" + reg.getProceso());
/* 473 */       this.pagHTML.setTextDescripcionEd("" + reg.getDescripcion());
/*     */       
/* 475 */       if (reg2 != null) {
/* 476 */         this.pagHTML.setTextObjetivoEd("" + reg2.getDescripcion());
/* 477 */         this.pagHTML.setTextJustificacionEd("" + reg2.getJustificacion());
/*     */       } else {
/*     */ 
/*     */         
/*     */         try {
/* 482 */           this.pagHTML.setTextObjetivoEd("" + reg3.getDescripcion());
/* 483 */           this.pagHTML.setTextJustificacionEd("" + reg3.getJustificacion());
/* 484 */         } catch (Exception e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 490 */           objetivoHidden = reg3.getCodigoObjetivo();
/* 491 */         } catch (Exception e) {}
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 500 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/* 501 */       this.pagHTML.setTextTipoProcesoEd("" + reg.getNombreTipoProceso());
/* 502 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 503 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 504 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/* 505 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/*     */       
/* 507 */       this.pagHTML.getElementObjetivoHidden().setValue("" + objetivoHidden);
/* 508 */       this.pagHTML.getElementProcesoKey().setValue("" + reg.getProceso());
/* 509 */       this.pagHTML.getElement_operacion().setValue("P");
/* 510 */       subprocesos(proceso, objetivoHidden);
/*     */     } 
/* 512 */     activarVista("editar");
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
/* 524 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 526 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 527 */       sel.getParentNode().removeChild(sel);
/*     */ 
/*     */     
/*     */     }
/* 531 */     catch (Exception e) {}
/*     */     
/* 533 */     activarVista("nuevo");
/* 534 */     HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 535 */     comboMultivalores(combo, "ESTADO_REGISTRO", "A", true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 541 */     HTMLElement sel = this.pagHTML.getElementSubprocesosEd();
/* 542 */     sel.getParentNode().removeChild(sel);
/*     */     
/* 544 */     CalObjetivosDAO rs = new CalObjetivosDAO();
/* 545 */     this.pagHTML.getElementObjetivoHidden().setValue("" + rs.siguienteRegistro());
/* 546 */     rs.close();
/*     */     
/* 548 */     combo = this.pagHTML.getElementTipoProceso();
/* 549 */     comboMultivalores(combo, "CAL_NOMBRE_TIPO_PROCESO", "", true);
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
/*     */   private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 561 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 563 */     Varios oVarios = new Varios();
/* 564 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "cal_procesos_act");
/* 565 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "cal_procesos_del");
/* 566 */     if (!oPermisoAct) {
/* 567 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 568 */       elem.getParentNode().removeChild(elem);
/* 569 */       elem = this.pagHTML.getElementBtnGrabar();
/* 570 */       elem.getParentNode().removeChild(elem);
/* 571 */       elem = this.pagHTML.getElementBtnModificar();
/* 572 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 574 */     if (!oPermisoDel) {
/* 575 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 576 */       elem.getParentNode().removeChild(elem);
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
/*     */   private void subprocesos(String proceso, int objetivo) throws HttpPresentationException, KeywordValueException {
/* 589 */     CalSubProcesosDAO rs = new CalSubProcesosDAO();
/* 590 */     if (!rs.getEstadoConexion()) {
/*     */       return;
/*     */     }
/*     */     
/* 594 */     CalProcesosDAO pro = new CalProcesosDAO();
/* 595 */     if (!pro.getEstadoConexion()) {
/*     */       return;
/*     */     }
/* 598 */     CalProcesosDTO proce = pro.cargarRegistro(proceso);
/* 599 */     pro.close();
/*     */     
/* 601 */     Collection arr = rs.cargarTodos(proceso);
/* 602 */     rs.close();
/*     */     
/* 604 */     HTMLTableSectionElement hte = this.pagHTML.getElementSubprocesos();
/* 605 */     int sumaFactor = 0;
/* 606 */     Iterator iterator = arr.iterator();
/*     */     
/* 608 */     HTMLElement eltrCabecera = this.pagHTML.getElementTrSubProcesos();
/* 609 */     int cuantas = 0;
/*     */ 
/*     */     
/* 612 */     SisParametrosDocDAO sis = new SisParametrosDocDAO();
/* 613 */     SisParametrosDocDTO parNormo = sis.cargarRegistro(1);
/* 614 */     SisParametrosDocDTO parMapa = sis.cargarRegistro(2);
/* 615 */     String objetivoHidden = this.pagHTML.getElementObjetivoHidden().getValue();
/*     */ 
/*     */     
/* 618 */     if (parNormo.getAsociadoA().equals("S") || parNormo.getAsociadoA().equals("A")) {
/* 619 */       eltrCabecera.appendChild(newth("Normograma", "cf1"));
/*     */     }
/*     */     
/* 622 */     if (parMapa.getAsociadoA().equals("S") || parMapa.getAsociadoA().equals("A"))
/*     */     {
/* 624 */       if (eltrCabecera.getChildNodes().getLength() == 9) {
/* 625 */         eltrCabecera.appendChild(newth("Mapa de Riesgos", "cf1"));
/*     */       } else {
/* 627 */         eltrCabecera.appendChild(newth("Mapa de Riesgos", "cf2"));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 632 */     if (eltrCabecera.getChildNodes().getLength() == 11 || eltrCabecera.getChildNodes().getLength() == 13) {
/* 633 */       eltrCabecera.appendChild(newth("Caracterizacion", "cf2"));
/*     */     } else {
/* 635 */       eltrCabecera.appendChild(newth("Caracterizacion", "cf1"));
/*     */     } 
/*     */ 
/*     */     
/* 639 */     while (iterator.hasNext()) {
/* 640 */       CalSubProcesosDTO reg = (CalSubProcesosDTO)iterator.next();
/*     */       
/* 642 */       CalDocumentosDAO dao = new CalDocumentosDAO();
/* 643 */       CalDocumentosDTO normograma = dao.cargarNormograma(reg.getProceso(), reg.getSubproceso());
/* 644 */       CalDocumentosDTO riesgos = dao.cargarRiesgos(reg.getProceso(), reg.getSubproceso());
/* 645 */       CalDocumentosDTO caracterizacion = dao.cargarCaracterizacion(reg.getProceso(), reg.getSubproceso());
/* 646 */       dao.close();
/*     */       
/* 648 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 649 */       String url = "CalSubProcesosAct.po?_operacion=P&proceso=" + reg.getProceso() + "&subproceso=" + reg.getSubproceso() + "&objetivoHidden=" + objetivo;
/* 650 */       eltr.appendChild(newtdhref("" + reg.getSubproceso(), url, false));
/* 651 */       eltr.appendChild(newtd("" + reg.getDescripcion()));
/* 652 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 653 */       eltr.appendChild(newtd("" + reg.getFactor()));
/*     */ 
/*     */ 
/*     */       
/* 657 */       if (proce.getEstado().equals("A")) {
/* 658 */         if (reg.getEstado().equals("A")) {
/* 659 */           if (parNormo.getAsociadoA().equals("S") || parNormo.getAsociadoA().equals("A")) {
/* 660 */             if (normograma.getCodigo() != "") {
/* 661 */               eltr.appendChild(newtdhref("Cargar Normograma", "CalDocumentosAct.po?_operacion=P&codigo=" + normograma.getCodigo() + "&Doc=Normograma&proceso=" + reg.getProceso() + "&subproceso=" + reg.getSubproceso() + "&objetivoHidden=" + objetivoHidden, false));
/*     */             } else {
/* 663 */               eltr.appendChild(newtdhref("Cargar Normograma", "CalDocumentosAct.po?_operacion=Nuevo&Doc=Normograma&proceso=" + reg.getProceso() + "&subproceso=" + reg.getSubproceso() + "&objetivoHidden=" + objetivoHidden, false));
/*     */             } 
/*     */           }
/*     */ 
/*     */           
/* 668 */           if (parMapa.getAsociadoA().equals("S") || parMapa.getAsociadoA().equals("A")) {
/* 669 */             if (riesgos.getCodigo() != "") {
/* 670 */               eltr.appendChild(newtdhref("Cargar Mapa de Riesgos", "CalDocumentosAct.po?_operacion=P&codigo=" + riesgos.getCodigo() + "&Doc=Riesgos&proceso=" + reg.getProceso() + "&subproceso=" + reg.getSubproceso() + "&objetivoHidden=" + objetivoHidden, false));
/*     */             } else {
/* 672 */               eltr.appendChild(newtdhref("Cargar Mapa de Riesgos", "CalDocumentosAct.po?_operacion=Nuevo&Doc=Riesgos&proceso=" + reg.getProceso() + "&subproceso=" + reg.getSubproceso() + "&objetivoHidden=" + objetivoHidden, false));
/*     */             } 
/*     */           }
/*     */ 
/*     */           
/* 677 */           if (caracterizacion.getCodigo() != "") {
/* 678 */             eltr.appendChild(newtdhref("Cargar Caracterización", "CalDocumentosAct.po?_operacion=P&codigo=" + caracterizacion.getCodigo() + "&Doc=Caracterizacion&proceso=" + reg.getProceso() + "&subproceso=" + reg.getSubproceso() + "&objetivoHidden=" + objetivoHidden, false));
/*     */           } else {
/* 680 */             eltr.appendChild(newtdhref("Cargar Caracterización", "CalDocumentosAct.po?_operacion=Nuevo&Doc=Caracterizacion&proceso=" + reg.getProceso() + "&subproceso=" + reg.getSubproceso() + "&objetivoHidden=" + objetivoHidden, false));
/*     */           } 
/*     */         } else {
/*     */           
/* 684 */           if (parNormo.getAsociadoA().equals("S") || parNormo.getAsociadoA().equals("A")) {
/* 685 */             eltr.appendChild(newtd("Cargar Normograma"));
/*     */           }
/* 687 */           if (parMapa.getAsociadoA().equals("S") || parMapa.getAsociadoA().equals("A")) {
/* 688 */             eltr.appendChild(newtd("Cargar Mapa de Riesgos"));
/*     */           }
/* 690 */           eltr.appendChild(newtd("Cargar Caracterización"));
/*     */         } 
/*     */       } else {
/* 693 */         if (parNormo.getAsociadoA().equals("S") || parNormo.getAsociadoA().equals("A")) {
/* 694 */           eltr.appendChild(newtd("Cargar Normograma"));
/*     */         }
/*     */         
/* 697 */         if (parMapa.getAsociadoA().equals("S") || parMapa.getAsociadoA().equals("A")) {
/* 698 */           eltr.appendChild(newtd("Cargar Mapa de Riesgos"));
/*     */         }
/* 700 */         eltr.appendChild(newtd("Cargar Caracterización"));
/*     */       } 
/*     */ 
/*     */       
/* 704 */       hte.appendChild(eltr);
/* 705 */       if (reg.getEstado().equals("A")) {
/* 706 */         sumaFactor += reg.getFactor();
/*     */       }
/*     */     } 
/* 709 */     arr.clear();
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
/*     */   private void activarVista(String vista) {
/* 721 */     if (!vista.equals("nuevo")) {
/* 722 */       HTMLElement sel = this.pagHTML.getElementTrCreacionRegistro();
/* 723 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 725 */     if (!vista.equals("editar")) {
/* 726 */       HTMLElement sel = this.pagHTML.getElementTrEdicion();
/* 727 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 729 */     if (!vista.equals("consulta")) {
/* 730 */       HTMLElement sel = this.pagHTML.getElementTrConsulta();
/* 731 */       sel.getParentNode().removeChild(sel);
/* 732 */       sel = this.pagHTML.getElementTrResultados();
/* 733 */       sel.getParentNode().removeChild(sel);
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
/* 747 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 748 */     atrib.setValue(valor);
/* 749 */     return atrib;
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
/*     */   private HTMLElement newtdhref(String contenido, String vinculo, boolean nueva) {
/* 762 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 763 */     Element enlace = this.pagHTML.createElement("a");
/* 764 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 765 */     enlace.appendChild(hijo);
/* 766 */     Attr donde = this.pagHTML.createAttribute("href");
/* 767 */     donde.setValue(vinculo);
/* 768 */     enlace.setAttributeNode(donde);
/* 769 */     if (nueva) {
/* 770 */       enlace.setAttributeNode(newAttr("TARGET", "_blank"));
/*     */     }
/* 772 */     td.appendChild(enlace);
/* 773 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 774 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 784 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 785 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 786 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 787 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newth(String contenido, String clase) {
/* 798 */     HTMLElement th = (HTMLElement)this.pagHTML.createElement("th");
/* 799 */     th.appendChild(this.pagHTML.createTextNode(contenido));
/* 800 */     th.setAttributeNode(newAttr("class", clase));
/* 801 */     return th;
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
/* 816 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 817 */     Collection arr = rs.cargarTabla(tabla);
/* 818 */     rs.close();
/* 819 */     if (dejarBlanco) {
/* 820 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 821 */       op.setValue("");
/* 822 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 823 */       combo.appendChild(op);
/*     */     } 
/* 825 */     Iterator iterator = arr.iterator();
/* 826 */     while (iterator.hasNext()) {
/* 827 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 828 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 829 */       op.setValue("" + reg.getCodigo());
/* 830 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 831 */       if (defecto.equals(reg.getCodigo())) {
/* 832 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 833 */         escogida.setValue("on");
/* 834 */         op.setAttributeNode(escogida);
/*     */       } 
/* 836 */       combo.appendChild(op);
/*     */     } 
/* 838 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalProcesosAct.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */