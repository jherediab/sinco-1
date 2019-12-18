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
/*     */ import sinco.business.SiscontrolequiposcomputoDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.SiscontrolequiposcomputoDAO;
/*     */ import sinco.presentation.Siscontrolequiposcomputo;
/*     */ import sinco.presentation.SiscontrolequiposcomputoHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Siscontrolequiposcomputo
/*     */   implements HttpPresentation
/*     */ {
/*     */   private SiscontrolequiposcomputoHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  34 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  35 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  38 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  39 */     String _operacion = comms.request.getParameter("_operacion");
/*  40 */     if (_operacion == null || _operacion.length() == 0) {
/*  41 */       _operacion = "X";
/*     */     }
/*     */     
/*  44 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  45 */       creacion(comms);
/*     */     }
/*     */     
/*  48 */     this.pagHTML = (SiscontrolequiposcomputoHTML)comms.xmlcFactory.create(SiscontrolequiposcomputoHTML.class);
/*  49 */     permisos(comms);
/*     */     
/*  51 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  52 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  55 */     if (_operacion.equals("P")) {
/*  56 */       editar(comms);
/*     */     }
/*  58 */     else if (_operacion.equals("Nuevo")) {
/*  59 */       nuevo(comms);
/*     */     } 
/*     */     
/*  62 */     if (_operacion.equals("V")) {
/*  63 */       verRegistro(comms);
/*     */     }
/*  65 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  66 */     comms.response.writeDOM(this.pagHTML);
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
/*  78 */     String _operacion = comms.request.getParameter("_operacion");
/*  79 */     String elUsuario = "" + comms.session.getUser().getName();
/*  80 */     int nHojaControl = 0;
/*     */     try {
/*  82 */       nHojaControl = Integer.parseInt(comms.request.getParameter("nHojaControl"));
/*     */     }
/*  84 */     catch (Exception e) {
/*  85 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=nHojaControl"));
/*     */     } 
/*     */     
/*  88 */     RespuestaBD rta = new RespuestaBD();
/*  89 */     if (_operacion.equals("E")) {
/*  90 */       SiscontrolequiposcomputoDAO ob = new SiscontrolequiposcomputoDAO();
/*  91 */       rta = ob.eliminarRegistro(nHojaControl);
/*  92 */       if (!rta.isRta()) {
/*  93 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSiscontrolequiposcomputo&p1=" + rta.getMensaje()));
/*     */       }
/*  95 */       String sPagina = "Siscontrolequiposcomputo.po?_operacion=X";
/*  96 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/*  98 */     String fechaInventario = comms.request.getParameter("fechaInventario");
/*  99 */     String marca = comms.request.getParameter("marca");
/* 100 */     int serial = 0;
/*     */     try {
/* 102 */       serial = Integer.parseInt(comms.request.getParameter("serial"));
/*     */     }
/* 104 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 107 */     int nInventarioPc = 0;
/*     */     try {
/* 109 */       nInventarioPc = Integer.parseInt(comms.request.getParameter("nInventarioPc"));
/*     */     }
/* 111 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 114 */     String ubicacion = comms.request.getParameter("ubicacion");
/* 115 */     String estadoPc = comms.request.getParameter("estadoPc");
/* 116 */     String marcaCpu = comms.request.getParameter("marcaCpu");
/* 117 */     int serialCpu = 0;
/*     */     try {
/* 119 */       serialCpu = Integer.parseInt(comms.request.getParameter("serialCpu"));
/*     */     }
/* 121 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 124 */     int nInventarioCpu = 0;
/*     */     try {
/* 126 */       nInventarioCpu = Integer.parseInt(comms.request.getParameter("nInventarioCpu"));
/*     */     }
/* 128 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 131 */     String memoriaRam = comms.request.getParameter("memoriaRam");
/* 132 */     String marcaDiscoDuro = comms.request.getParameter("marcaDiscoDuro");
/* 133 */     int serialDiscoDuro = 0;
/*     */     try {
/* 135 */       serialDiscoDuro = Integer.parseInt(comms.request.getParameter("serialDiscoDuro"));
/*     */     }
/* 137 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 140 */     String procesador = comms.request.getParameter("procesador");
/* 141 */     String marcaBoard = comms.request.getParameter("marcaBoard");
/* 142 */     int serialBoard = 0;
/*     */     try {
/* 144 */       serialBoard = Integer.parseInt(comms.request.getParameter("serialBoard"));
/*     */     }
/* 146 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 149 */     String marcaTeclado = comms.request.getParameter("marcaTeclado");
/* 150 */     int nInventarioT = 0;
/*     */     try {
/* 152 */       nInventarioT = Integer.parseInt(comms.request.getParameter("nInventarioT"));
/*     */     }
/* 154 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 157 */     String estadoT = comms.request.getParameter("estadoT");
/* 158 */     String marcaMouse = comms.request.getParameter("marcaMouse");
/* 159 */     int nInventario = 0;
/*     */     try {
/* 161 */       nInventario = Integer.parseInt(comms.request.getParameter("nInventario"));
/*     */     }
/* 163 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 166 */     String estadoM = comms.request.getParameter("estadoM");
/* 167 */     String conectorMause = comms.request.getParameter("conectorMause");
/* 168 */     String conectorTeclado = comms.request.getParameter("conectorTeclado");
/* 169 */     String marcaUnidadCd = comms.request.getParameter("marcaUnidadCd");
/* 170 */     int serialUnidadCd = 0;
/*     */     try {
/* 172 */       serialUnidadCd = Integer.parseInt(comms.request.getParameter("serialUnidadCd"));
/*     */     }
/* 174 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 177 */     String marcaFuentePoder = comms.request.getParameter("marcaFuentePoder");
/* 178 */     String targetaRed = comms.request.getParameter("targetaRed");
/* 179 */     String sistemaOperativo = comms.request.getParameter("sistemaOperativo");
/* 180 */     String particionDiscoDuro = comms.request.getParameter("particionDiscoDuro");
/* 181 */     String microsoftOffice = comms.request.getParameter("microsoftOffice");
/* 182 */     String versionOffice = comms.request.getParameter("versionOffice");
/* 183 */     String navegadores = comms.request.getParameter("navegadores");
/* 184 */     String adobeReader = comms.request.getParameter("adobeReader");
/* 185 */     String versionAdobe = comms.request.getParameter("versionAdobe");
/* 186 */     String antivirus = comms.request.getParameter("antivirus");
/* 187 */     String deepFreeze = comms.request.getParameter("deepFreeze");
/* 188 */     String versionDeepFreeze = comms.request.getParameter("versionDeepFreeze");
/* 189 */     String otrosSoftware = comms.request.getParameter("otrosSoftware");
/* 190 */     String versionSoftware = comms.request.getParameter("versionSoftware");
/* 191 */     String tipoMatenimiento = comms.request.getParameter("tipoMatenimiento");
/* 192 */     String fechaRealizado = comms.request.getParameter("fechaRealizado");
/* 193 */     String realizo = comms.request.getParameter("realizo");
/* 194 */     String recibio = comms.request.getParameter("recibio");
/* 195 */     String descripcion = comms.request.getParameter("descripcion");
/* 196 */     String observaciones = comms.request.getParameter("observaciones");
/* 197 */     SiscontrolequiposcomputoDAO ob = new SiscontrolequiposcomputoDAO();
/* 198 */     if (_operacion.equals("C")) {
/* 199 */       rta = ob.crearRegistro(nHojaControl, fechaInventario, marca, serial, nInventarioPc, ubicacion, estadoPc, marcaCpu, serialCpu, nInventarioCpu, memoriaRam, marcaDiscoDuro, serialDiscoDuro, procesador, marcaBoard, serialBoard, marcaTeclado, nInventarioT, estadoT, marcaMouse, nInventario, estadoM, conectorMause, conectorTeclado, marcaUnidadCd, serialUnidadCd, marcaFuentePoder, targetaRed, sistemaOperativo, particionDiscoDuro, microsoftOffice, versionOffice, navegadores, adobeReader, versionAdobe, antivirus, deepFreeze, versionDeepFreeze, otrosSoftware, versionSoftware, tipoMatenimiento, fechaRealizado, realizo, recibio, descripcion, observaciones);
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
/* 246 */       nHojaControl = rta.getSecuencia();
/*     */     } else {
/*     */       
/* 249 */       rta = ob.modificarRegistro(nHojaControl, fechaInventario, marca, serial, nInventarioPc, ubicacion, estadoPc, marcaCpu, serialCpu, nInventarioCpu, memoriaRam, marcaDiscoDuro, serialDiscoDuro, procesador, marcaBoard, serialBoard, marcaTeclado, nInventarioT, estadoT, marcaMouse, nInventario, estadoM, conectorMause, conectorTeclado, marcaUnidadCd, serialUnidadCd, marcaFuentePoder, targetaRed, sistemaOperativo, particionDiscoDuro, microsoftOffice, versionOffice, navegadores, adobeReader, versionAdobe, antivirus, deepFreeze, versionDeepFreeze, otrosSoftware, versionSoftware, tipoMatenimiento, fechaRealizado, realizo, recibio, descripcion, observaciones);
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
/* 297 */     if (!rta.isRta()) {
/* 298 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSiscontrolequiposcomputo&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 301 */     String sPagina = "Siscontrolequiposcomputo.po?_operacion=P&nHojaControl=" + nHojaControl + "";
/* 302 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 313 */     int nHojaControl = 0;
/*     */     try {
/* 315 */       nHojaControl = Integer.parseInt(comms.request.getParameter("nHojaControl"));
/*     */     }
/* 317 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 320 */     SiscontrolequiposcomputoDAO ob = new SiscontrolequiposcomputoDAO();
/* 321 */     SiscontrolequiposcomputoDTO reg = ob.cargarRegistro(nHojaControl);
/* 322 */     if (reg != null) {
/* 323 */       this.pagHTML.getElementNHojaControl().setValue("" + reg.getNHojaControl());
/* 324 */       this.pagHTML.getElementFechaInventario().setValue("" + Utilidades.darFormatoFecha(reg.getFechaInventario()));
/* 325 */       this.pagHTML.getElementMarca().setValue("" + reg.getMarca());
/* 326 */       this.pagHTML.getElementSerial().setValue("" + reg.getSerial());
/* 327 */       this.pagHTML.getElementNInventarioPc().setValue("" + reg.getNInventarioPc());
/* 328 */       this.pagHTML.getElementUbicacion().setValue("" + reg.getUbicacion());
/* 329 */       this.pagHTML.getElementEstadoPc().setValue("" + reg.getEstadoPc());
/* 330 */       this.pagHTML.getElementMarcaCpu().setValue("" + reg.getMarcaCpu());
/* 331 */       this.pagHTML.getElementSerialCpu().setValue("" + reg.getSerialCpu());
/* 332 */       this.pagHTML.getElementNInventarioCpu().setValue("" + reg.getNInventarioCpu());
/* 333 */       this.pagHTML.getElementMemoriaRam().setValue("" + reg.getMemoriaRam());
/* 334 */       this.pagHTML.getElementMarcaDiscoDuro().setValue("" + reg.getMarcaDiscoDuro());
/* 335 */       this.pagHTML.getElementSerialDiscoDuro().setValue("" + reg.getSerialDiscoDuro());
/* 336 */       this.pagHTML.getElementProcesador().setValue("" + reg.getProcesador());
/* 337 */       this.pagHTML.getElementMarcaBoard().setValue("" + reg.getMarcaBoard());
/* 338 */       this.pagHTML.getElementSerialBoard().setValue("" + reg.getSerialBoard());
/* 339 */       this.pagHTML.getElementMarcaTeclado().setValue("" + reg.getMarcaTeclado());
/* 340 */       this.pagHTML.getElementNInventarioT().setValue("" + reg.getNInventarioT());
/* 341 */       this.pagHTML.getElementEstadoT().setValue("" + reg.getEstadoT());
/* 342 */       this.pagHTML.getElementMarcaMouse().setValue("" + reg.getMarcaMouse());
/* 343 */       this.pagHTML.getElementNInventario().setValue("" + reg.getNInventario());
/* 344 */       this.pagHTML.getElementEstadoM().setValue("" + reg.getEstadoM());
/* 345 */       this.pagHTML.getElementConectorMause().setValue("" + reg.getConectorMause());
/* 346 */       this.pagHTML.getElementConectorTeclado().setValue("" + reg.getConectorTeclado());
/* 347 */       this.pagHTML.getElementMarcaUnidadCd().setValue("" + reg.getMarcaUnidadCd());
/* 348 */       this.pagHTML.getElementSerialUnidadCd().setValue("" + reg.getSerialUnidadCd());
/* 349 */       this.pagHTML.getElementMarcaFuentePoder().setValue("" + reg.getMarcaFuentePoder());
/* 350 */       this.pagHTML.getElementTargetaRed().setValue("" + reg.getTargetaRed());
/* 351 */       this.pagHTML.getElementSistemaOperativo().setValue("" + reg.getSistemaOperativo());
/* 352 */       this.pagHTML.getElementParticionDiscoDuro().setValue("" + reg.getParticionDiscoDuro());
/* 353 */       this.pagHTML.getElementMicrosoftOffice().setValue("" + reg.getMicrosoftOffice());
/* 354 */       this.pagHTML.getElementVersionOffice().setValue("" + reg.getVersionOffice());
/* 355 */       this.pagHTML.getElementNavegadores().setValue("" + reg.getNavegadores());
/* 356 */       this.pagHTML.getElementAdobeReader().setValue("" + reg.getAdobeReader());
/* 357 */       this.pagHTML.getElementVersionAdobe().setValue("" + reg.getVersionAdobe());
/* 358 */       this.pagHTML.getElementAntivirus().setValue("" + reg.getAntivirus());
/* 359 */       this.pagHTML.getElementDeepFreeze().setValue("" + reg.getDeepFreeze());
/* 360 */       this.pagHTML.getElementVersionDeepFreeze().setValue("" + reg.getVersionDeepFreeze());
/* 361 */       this.pagHTML.getElementOtrosSoftware().setValue("" + reg.getOtrosSoftware());
/* 362 */       this.pagHTML.getElementVersionSoftware().setValue("" + reg.getVersionSoftware());
/* 363 */       this.pagHTML.getElementTipoMatenimiento().setValue("" + reg.getTipoMatenimiento());
/* 364 */       this.pagHTML.getElementFechaRealizado().setValue("" + Utilidades.darFormatoFecha(reg.getFechaRealizado()));
/* 365 */       this.pagHTML.getElementRealizo().setValue("" + reg.getRealizo());
/* 366 */       this.pagHTML.getElementRecibio().setValue("" + reg.getRecibio());
/* 367 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 368 */       this.pagHTML.getElementObservaciones().setValue("" + reg.getObservaciones());
/*     */       
/* 370 */       this.pagHTML.getElementNHojaControl().setReadOnly(true);
/*     */     } 
/* 372 */     this.pagHTML.getElement_operacion().setValue("M");
/* 373 */     activarVista("nuevo");
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
/* 385 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 387 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 388 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 390 */     catch (Exception e) {}
/*     */     
/* 392 */     activarVista("nuevo");
/* 393 */     this.pagHTML.getElementNHojaControl().setReadOnly(true);
/* 394 */     this.pagHTML.getElementNHojaControl().setValue("0");
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
/* 405 */     activarVista("consulta");
/* 406 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 411 */     SiscontrolequiposcomputoDAO ob = new SiscontrolequiposcomputoDAO();
/* 412 */     Collection<SiscontrolequiposcomputoDTO> arr = ob.cargarTodos();
/* 413 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 414 */     int cuantas = 0;
/* 415 */     Iterator<SiscontrolequiposcomputoDTO> iterator = arr.iterator();
/* 416 */     while (iterator.hasNext()) {
/* 417 */       SiscontrolequiposcomputoDTO reg = (SiscontrolequiposcomputoDTO)iterator.next();
/* 418 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 419 */       eltr.appendChild(newtd("" + reg.getNHojaControl()));
/* 420 */       String url = "Siscontrolequiposcomputo.po?_operacion=V&nHojaControl=" + reg.getNHojaControl() + "";
/* 421 */       eltr.appendChild(newtdhref("" + reg.getFechaInventario(), url));
/* 422 */       eltr.appendChild(newtd("" + reg.getMarca()));
/* 423 */       eltr.appendChild(newtd("" + reg.getSerial()));
/* 424 */       eltr.appendChild(newtd("" + reg.getNInventarioPc()));
/* 425 */       eltr.appendChild(newtd("" + reg.getUbicacion()));
/* 426 */       eltr.appendChild(newtd("" + reg.getEstadoPc()));
/* 427 */       eltr.appendChild(newtd("" + reg.getMarcaCpu()));
/* 428 */       eltr.appendChild(newtd("" + reg.getSerialCpu()));
/* 429 */       eltr.appendChild(newtd("" + reg.getNInventarioCpu()));
/* 430 */       eltr.appendChild(newtd("" + reg.getMemoriaRam()));
/* 431 */       eltr.appendChild(newtd("" + reg.getMarcaDiscoDuro()));
/* 432 */       eltr.appendChild(newtd("" + reg.getSerialDiscoDuro()));
/* 433 */       eltr.appendChild(newtd("" + reg.getProcesador()));
/* 434 */       eltr.appendChild(newtd("" + reg.getMarcaBoard()));
/* 435 */       eltr.appendChild(newtd("" + reg.getSerialBoard()));
/* 436 */       eltr.appendChild(newtd("" + reg.getMarcaTeclado()));
/* 437 */       eltr.appendChild(newtd("" + reg.getNInventarioT()));
/* 438 */       eltr.appendChild(newtd("" + reg.getEstadoT()));
/* 439 */       eltr.appendChild(newtd("" + reg.getMarcaMouse()));
/* 440 */       eltr.appendChild(newtd("" + reg.getNInventario()));
/* 441 */       eltr.appendChild(newtd("" + reg.getEstadoM()));
/* 442 */       eltr.appendChild(newtd("" + reg.getConectorMause()));
/* 443 */       eltr.appendChild(newtd("" + reg.getConectorTeclado()));
/* 444 */       eltr.appendChild(newtd("" + reg.getMarcaUnidadCd()));
/* 445 */       eltr.appendChild(newtd("" + reg.getSerialUnidadCd()));
/* 446 */       eltr.appendChild(newtd("" + reg.getMarcaFuentePoder()));
/* 447 */       eltr.appendChild(newtd("" + reg.getTargetaRed()));
/* 448 */       eltr.appendChild(newtd("" + reg.getSistemaOperativo()));
/* 449 */       eltr.appendChild(newtd("" + reg.getParticionDiscoDuro()));
/* 450 */       eltr.appendChild(newtd("" + reg.getMicrosoftOffice()));
/* 451 */       eltr.appendChild(newtd("" + reg.getVersionOffice()));
/* 452 */       eltr.appendChild(newtd("" + reg.getNavegadores()));
/* 453 */       eltr.appendChild(newtd("" + reg.getAdobeReader()));
/* 454 */       eltr.appendChild(newtd("" + reg.getVersionAdobe()));
/* 455 */       eltr.appendChild(newtd("" + reg.getAntivirus()));
/* 456 */       eltr.appendChild(newtd("" + reg.getDeepFreeze()));
/* 457 */       eltr.appendChild(newtd("" + reg.getVersionDeepFreeze()));
/* 458 */       eltr.appendChild(newtd("" + reg.getOtrosSoftware()));
/* 459 */       eltr.appendChild(newtd("" + reg.getVersionSoftware()));
/* 460 */       eltr.appendChild(newtd("" + reg.getTipoMatenimiento()));
/* 461 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaRealizado())));
/* 462 */       eltr.appendChild(newtd("" + reg.getRealizo()));
/* 463 */       eltr.appendChild(newtd("" + reg.getRecibio()));
/* 464 */       eltr.appendChild(newtd("" + reg.getDescripcion()));
/* 465 */       eltr.appendChild(newtd("" + reg.getObservaciones()));
/* 466 */       hte.appendChild(eltr);
/* 467 */       cuantas++;
/*     */     } 
/* 469 */     arr.clear();
/* 470 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 483 */     int nHojaControl = 0;
/*     */     try {
/* 485 */       nHojaControl = Integer.parseInt(comms.request.getParameter("nHojaControl"));
/*     */     }
/* 487 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 490 */     SiscontrolequiposcomputoDAO ob = new SiscontrolequiposcomputoDAO();
/* 491 */     SiscontrolequiposcomputoDTO reg = ob.cargarRegistro(nHojaControl);
/* 492 */     if (reg != null) {
/* 493 */       this.pagHTML.setTextNHojaControlEd("" + reg.getNHojaControl());
/* 494 */       this.pagHTML.setTextFechaInventarioEd("" + Utilidades.darFormatoFecha(reg.getFechaInventario()));
/* 495 */       this.pagHTML.setTextMarcaEd("" + reg.getMarca());
/* 496 */       this.pagHTML.setTextSerialEd("" + reg.getSerial());
/* 497 */       this.pagHTML.setTextNInventarioPcEd("" + reg.getNInventarioPc());
/* 498 */       this.pagHTML.setTextUbicacionEd("" + reg.getUbicacion());
/* 499 */       this.pagHTML.setTextEstadoPcEd("" + reg.getEstadoPc());
/* 500 */       this.pagHTML.setTextMarcaCpuEd("" + reg.getMarcaCpu());
/* 501 */       this.pagHTML.setTextSerialCpuEd("" + reg.getSerialCpu());
/* 502 */       this.pagHTML.setTextNInventarioCpuEd("" + reg.getNInventarioCpu());
/* 503 */       this.pagHTML.setTextMemoriaRamEd("" + reg.getMemoriaRam());
/* 504 */       this.pagHTML.setTextMarcaDiscoDuroEd("" + reg.getMarcaDiscoDuro());
/* 505 */       this.pagHTML.setTextSerialDiscoDuroEd("" + reg.getSerialDiscoDuro());
/* 506 */       this.pagHTML.setTextProcesadorEd("" + reg.getProcesador());
/* 507 */       this.pagHTML.setTextMarcaBoardEd("" + reg.getMarcaBoard());
/* 508 */       this.pagHTML.setTextSerialBoardEd("" + reg.getSerialBoard());
/* 509 */       this.pagHTML.setTextMarcaTecladoEd("" + reg.getMarcaTeclado());
/* 510 */       this.pagHTML.setTextNInventarioTEd("" + reg.getNInventarioT());
/* 511 */       this.pagHTML.setTextEstadoTEd("" + reg.getEstadoT());
/* 512 */       this.pagHTML.setTextMarcaMouseEd("" + reg.getMarcaMouse());
/* 513 */       this.pagHTML.setTextNInventarioEd("" + reg.getNInventario());
/* 514 */       this.pagHTML.setTextEstadoMEd("" + reg.getEstadoM());
/* 515 */       this.pagHTML.setTextConectorMauseEd("" + reg.getConectorMause());
/* 516 */       this.pagHTML.setTextConectorTecladoEd("" + reg.getConectorTeclado());
/* 517 */       this.pagHTML.setTextMarcaUnidadCdEd("" + reg.getMarcaUnidadCd());
/* 518 */       this.pagHTML.setTextSerialUnidadCdEd("" + reg.getSerialUnidadCd());
/* 519 */       this.pagHTML.setTextMarcaFuentePoderEd("" + reg.getMarcaFuentePoder());
/* 520 */       this.pagHTML.setTextTargetaRedEd("" + reg.getTargetaRed());
/* 521 */       this.pagHTML.setTextSistemaOperativoEd("" + reg.getSistemaOperativo());
/* 522 */       this.pagHTML.setTextParticionDiscoDuroEd("" + reg.getParticionDiscoDuro());
/* 523 */       this.pagHTML.setTextMicrosoftOfficeEd("" + reg.getMicrosoftOffice());
/* 524 */       this.pagHTML.setTextVersionOfficeEd("" + reg.getVersionOffice());
/* 525 */       this.pagHTML.setTextNavegadoresEd("" + reg.getNavegadores());
/* 526 */       this.pagHTML.setTextAdobeReaderEd("" + reg.getAdobeReader());
/* 527 */       this.pagHTML.setTextVersionAdobeEd("" + reg.getVersionAdobe());
/* 528 */       this.pagHTML.setTextAntivirusEd("" + reg.getAntivirus());
/* 529 */       this.pagHTML.setTextDeepFreezeEd("" + reg.getDeepFreeze());
/* 530 */       this.pagHTML.setTextVersionDeepFreezeEd("" + reg.getVersionDeepFreeze());
/* 531 */       this.pagHTML.setTextOtrosSoftwareEd("" + reg.getOtrosSoftware());
/* 532 */       this.pagHTML.setTextVersionSoftwareEd("" + reg.getVersionSoftware());
/* 533 */       this.pagHTML.setTextTipoMatenimientoEd("" + reg.getTipoMatenimiento());
/* 534 */       this.pagHTML.setTextFechaRealizadoEd("" + Utilidades.darFormatoFecha(reg.getFechaRealizado()));
/* 535 */       this.pagHTML.setTextRealizoEd("" + reg.getRealizo());
/* 536 */       this.pagHTML.setTextRecibioEd("" + reg.getRecibio());
/* 537 */       this.pagHTML.setTextDescripcionEd("" + reg.getDescripcion());
/* 538 */       this.pagHTML.setTextObservacionesEd("" + reg.getObservaciones());
/*     */       
/* 540 */       this.pagHTML.getElementNHojaControlKey().setValue("" + reg.getNHojaControl());
/* 541 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 543 */     activarVista("editar");
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
/* 554 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 556 */     Varios oVarios = new Varios();
/* 557 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "ecoSiscontrolequiposcomputoAct");
/* 558 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "ecoSiscontrolequiposcomputoDel");
/* 559 */     if (!oPermisoAct) {
/* 560 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 561 */       elem.getParentNode().removeChild(elem);
/* 562 */       elem = this.pagHTML.getElementBtnGrabar();
/* 563 */       elem.getParentNode().removeChild(elem);
/* 564 */       elem = this.pagHTML.getElementBtnModificar();
/* 565 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 567 */     if (!oPermisoDel) {
/* 568 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 569 */       elem.getParentNode().removeChild(elem);
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
/* 580 */     if (!vista.equals("nuevo")) {
/* 581 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 582 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 584 */     if (!vista.equals("editar")) {
/* 585 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 586 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 588 */     if (!vista.equals("consulta")) {
/* 589 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 590 */       sel.getParentNode().removeChild(sel);
/* 591 */       sel = this.pagHTML.getElementDivResultados();
/* 592 */       sel.getParentNode().removeChild(sel);
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
/* 606 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 607 */     atrib.setValue(valor);
/* 608 */     return atrib;
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
/* 621 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 622 */     Element enlace = this.pagHTML.createElement("a");
/* 623 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 624 */     enlace.appendChild(hijo);
/* 625 */     Attr donde = this.pagHTML.createAttribute("href");
/* 626 */     donde.setValue(vinculo);
/* 627 */     enlace.setAttributeNode(donde);
/* 628 */     td.appendChild(enlace);
/* 629 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 630 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 640 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 641 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 642 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 643 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\Siscontrolequiposcomputo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */