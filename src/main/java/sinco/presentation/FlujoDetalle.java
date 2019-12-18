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
/*     */ import sinco.business.CaracteristicasServicioDTO;
/*     */ import sinco.business.CaracteristicasValorDTO;
/*     */ import sinco.business.FlujoDetalleDTO;
/*     */ import sinco.business.FlujosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.CaracteristicasServicioDAO;
/*     */ import sinco.data.CaracteristicasValorDAO;
/*     */ import sinco.data.FlujoDetalleDAO;
/*     */ import sinco.data.FlujosDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.FlujoDetalle;
/*     */ import sinco.presentation.FlujoDetalleHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlujoDetalle
/*     */   implements HttpPresentation
/*     */ {
/*     */   private FlujoDetalleHTML pagHTML;
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
/*  61 */     this.pagHTML = (FlujoDetalleHTML)comms.xmlcFactory.create(FlujoDetalleHTML.class);
/*  62 */     permisos(comms);
/*     */ 
/*     */     
/*  65 */     int codigoFlujo = 0;
/*     */     try {
/*  67 */       codigoFlujo = Integer.parseInt(comms.request.getParameter("codigoFlujo"));
/*     */     }
/*  69 */     catch (Exception e) {
/*  70 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoFlujo"));
/*     */     } 
/*     */     
/*  73 */     FlujosDAO rs = new FlujosDAO();
/*  74 */     FlujosDTO reg = rs.cargarRegistro(codigoFlujo);
/*  75 */     rs.close();
/*     */     
/*  77 */     this.pagHTML.setTextNombreFlujo(reg.getDescripcion());
/*     */     
/*  79 */     this.pagHTML.getElementCodigoFlujoHidden().setValue("" + codigoFlujo);
/*  80 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  81 */       listar(comms, _operacion);
/*     */     
/*     */     }
/*  84 */     else if (_operacion.equals("P")) {
/*  85 */       editar(comms);
/*     */     }
/*  87 */     else if (_operacion.equals("Nuevo")) {
/*  88 */       nuevo(comms, reg.getCodigoFlujo(), reg.getServicioInicial());
/*     */     } 
/*     */     
/*  91 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  92 */     comms.response.writeDOM(this.pagHTML);
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
/* 104 */     String _operacion = comms.request.getParameter("_operacion");
/* 105 */     String elUsuario = "" + comms.session.getUser().getName();
/* 106 */     int codigoFlujo = 0;
/*     */     try {
/* 108 */       codigoFlujo = Integer.parseInt(comms.request.getParameter("codigoFlujo"));
/*     */     }
/* 110 */     catch (Exception e) {
/* 111 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoFlujo"));
/*     */     } 
/*     */     
/* 114 */     int secuencia = 0;
/*     */     try {
/* 116 */       secuencia = Integer.parseInt(comms.request.getParameter("secuencia"));
/*     */     }
/* 118 */     catch (Exception e) {
/* 119 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=secuencia"));
/*     */     } 
/*     */     
/* 122 */     RespuestaBD rta = new RespuestaBD();
/* 123 */     if (_operacion.equals("E")) {
/* 124 */       FlujoDetalleDAO rs = new FlujoDetalleDAO();
/* 125 */       if (!rs.getEstadoConexion()) {
/* 126 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 128 */       rta = rs.eliminarRegistro(codigoFlujo, secuencia);
/*     */       
/* 130 */       if (!rta.isRta()) {
/* 131 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorFlujoDetalle&p1=" + rta.getMensaje()));
/*     */       }
/* 133 */       rs.close();
/* 134 */       String sPagina = "FlujoDetalle.po?_operacion=L&codigoFlujo=" + codigoFlujo;
/* 135 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 137 */     int servicioInicio = 0;
/*     */     try {
/* 139 */       servicioInicio = Integer.parseInt(comms.request.getParameter("servicioInicio"));
/*     */     }
/* 141 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 144 */     int codigoEstado = 0;
/*     */     try {
/* 146 */       codigoEstado = Integer.parseInt(comms.request.getParameter("codigoEstado"));
/*     */     }
/* 148 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 151 */     int servicioDestino = 0;
/*     */     try {
/* 153 */       servicioDestino = Integer.parseInt(comms.request.getParameter("servicioDestino"));
/*     */     }
/* 155 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 158 */     String enviar = comms.request.getParameter("enviar");
/* 159 */     if (enviar == null) {
/* 160 */       enviar = "N";
/*     */     }
/*     */     
/* 163 */     String mismoCliente = comms.request.getParameter("mismoCliente");
/* 164 */     if (mismoCliente == null) {
/* 165 */       mismoCliente = "N";
/*     */     }
/*     */     
/* 168 */     String mismoProveedor = comms.request.getParameter("mismoProveedor");
/* 169 */     if (mismoProveedor == null) {
/* 170 */       mismoProveedor = "N";
/*     */     }
/*     */ 
/*     */     
/* 174 */     String indCorreoCliente = comms.request.getParameter("indCorreoCliente");
/* 175 */     if (indCorreoCliente == null) {
/* 176 */       indCorreoCliente = "N";
/*     */     }
/*     */     
/* 179 */     String indHermana = comms.request.getParameter("indHermana");
/* 180 */     if (indHermana == null) {
/* 181 */       indHermana = "N";
/*     */     }
/*     */     
/* 184 */     String indHermanaCerrada = comms.request.getParameter("indHermanaCerrada");
/* 185 */     if (indHermanaCerrada == null) {
/* 186 */       indHermanaCerrada = "N";
/*     */     }
/*     */     
/* 189 */     String indfuncionario = comms.request.getParameter("indfuncionario");
/* 190 */     if (indfuncionario == null) {
/* 191 */       indfuncionario = "N";
/*     */     }
/*     */     
/* 194 */     int caracteristica = 0;
/*     */     try {
/* 196 */       caracteristica = Integer.parseInt(comms.request.getParameter("caracteristica"));
/*     */     }
/* 198 */     catch (Exception e) {}
/*     */ 
/*     */ 
/*     */     
/* 202 */     int valorCaracteristica = 0;
/*     */     try {
/* 204 */       valorCaracteristica = Integer.parseInt(comms.request.getParameter("valorCaracteristica"));
/*     */     }
/* 206 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 209 */     String nombreProcedimiento = comms.request.getParameter("nombreProcedimiento");
/* 210 */     if (nombreProcedimiento == null) {
/* 211 */       nombreProcedimiento = "";
/*     */     }
/*     */     
/* 214 */     String correoDestino = comms.request.getParameter("correoDestino");
/* 215 */     if (correoDestino == null) {
/* 216 */       correoDestino = "";
/*     */     }
/*     */     
/* 219 */     String metodoSeleccionProveedor = comms.request.getParameter("metodoSeleccionProveedor");
/* 220 */     if (metodoSeleccionProveedor == null) {
/* 221 */       metodoSeleccionProveedor = "";
/*     */     }
/*     */     
/* 224 */     int caracteristicaCorreo = 0;
/*     */     try {
/* 226 */       caracteristicaCorreo = Integer.parseInt(comms.request.getParameter("caracteristicaCorreo"));
/*     */     }
/* 228 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 231 */     String estado = comms.request.getParameter("estado");
/* 232 */     if (estado == null) {
/* 233 */       estado = "";
/*     */     }
/*     */     
/* 236 */     int accion = 0;
/*     */     
/* 238 */     FlujoDetalleDAO rs = new FlujoDetalleDAO();
/* 239 */     if (!rs.getEstadoConexion()) {
/* 240 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 242 */     if (_operacion.equals("C")) {
/* 243 */       rta = rs.crearRegistro(codigoFlujo, secuencia, servicioInicio, accion, codigoEstado, servicioDestino, nombreProcedimiento, correoDestino, enviar, mismoProveedor, mismoCliente, estado, caracteristica, valorCaracteristica, caracteristicaCorreo, metodoSeleccionProveedor, indCorreoCliente, indHermana, indHermanaCerrada, indfuncionario, elUsuario);
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
/* 265 */       secuencia = rta.getSecuencia();
/*     */     } else {
/*     */       
/* 268 */       rta = rs.modificarRegistro(codigoFlujo, secuencia, servicioInicio, accion, codigoEstado, servicioDestino, nombreProcedimiento, correoDestino, enviar, mismoProveedor, mismoCliente, estado, caracteristica, valorCaracteristica, caracteristicaCorreo, metodoSeleccionProveedor, indCorreoCliente, indHermana, indHermanaCerrada, indfuncionario, elUsuario);
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
/* 291 */     rs.close();
/* 292 */     if (!rta.isRta()) {
/* 293 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorFlujoDetalle&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 296 */     String sPagina = "FlujoDetalle.po?_operacion=P&codigoFlujo=" + codigoFlujo + "&secuencia=" + secuencia + "";
/* 297 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 308 */     activarVista("consulta");
/* 309 */     int codigoFlujo = 0;
/*     */     try {
/* 311 */       codigoFlujo = Integer.parseInt(comms.request.getParameter("codigoFlujo"));
/*     */     }
/* 313 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 316 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */     
/* 320 */     FlujoDetalleDAO rs = new FlujoDetalleDAO();
/* 321 */     if (!rs.getEstadoConexion()) {
/* 322 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 324 */     Collection<FlujoDetalleDTO> arr = rs.cargarTodos(codigoFlujo);
/* 325 */     rs.close();
/* 326 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 327 */     int cuantas = 0;
/* 328 */     Iterator<FlujoDetalleDTO> iterator = arr.iterator();
/* 329 */     while (iterator.hasNext()) {
/* 330 */       FlujoDetalleDTO reg = (FlujoDetalleDTO)iterator.next();
/* 331 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 332 */       eltr.appendChild(newtd("" + reg.getSecuencia()));
/* 333 */       String url = "FlujoDetalle.po?_operacion=P&codigoFlujo=" + reg.getCodigoFlujo() + "&secuencia=" + reg.getSecuencia() + "";
/* 334 */       eltr.appendChild(newtdhref("" + reg.getNombreServicioInicio(), url));
/* 335 */       eltr.appendChild(newtd("" + reg.getNombreCodigoEstado()));
/* 336 */       eltr.appendChild(newtd("" + reg.getNombreServicioDestino()));
/* 337 */       eltr.appendChild(newtd("" + reg.getNombreProcedimiento()));
/* 338 */       eltr.appendChild(newtd("" + reg.getCorreoDestino()));
/*     */       
/* 340 */       eltr.appendChild(newtd("" + reg.getNombreCaracteristica()));
/* 341 */       eltr.appendChild(newtd("" + reg.getDescripcionValor()));
/* 342 */       eltr.appendChild(newtd("" + reg.getCaracteristicaCorreo()));
/* 343 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 344 */       eltr.appendChild(newtd("" + reg.getMetodoSeleccionProveedor()));
/*     */       
/* 346 */       hte.appendChild(eltr);
/* 347 */       cuantas++;
/*     */     } 
/* 349 */     arr.clear();
/* 350 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/*     */   private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 363 */     int codigoFlujo = 0;
/*     */     try {
/* 365 */       codigoFlujo = Integer.parseInt(comms.request.getParameter("codigoFlujo"));
/*     */     }
/* 367 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 370 */     int secuencia = 0;
/*     */     try {
/* 372 */       secuencia = Integer.parseInt(comms.request.getParameter("secuencia"));
/*     */     }
/* 374 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 377 */     FlujoDetalleDAO rs = new FlujoDetalleDAO();
/* 378 */     if (!rs.getEstadoConexion()) {
/* 379 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 381 */     FlujoDetalleDTO reg = rs.cargarRegistro(codigoFlujo, secuencia);
/*     */     
/* 383 */     rs.close();
/* 384 */     if (reg != null) {
/* 385 */       this.pagHTML.getElementSecuencia().setValue("" + reg.getSecuencia());
/* 386 */       this.pagHTML.getElementNombreProcedimiento().setValue("" + reg.getNombreProcedimiento());
/* 387 */       this.pagHTML.getElementCorreoDestino().setValue("" + reg.getCorreoDestino());
/* 388 */       this.pagHTML.getElementMetodoSeleccionProveedor().setValue("" + reg.getMetodoSeleccionProveedor());
/* 389 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 390 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 391 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 392 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/*     */ 
/*     */ 
/*     */       
/* 396 */       if (reg.getEnviarSolicitud().equals("S")) {
/* 397 */         this.pagHTML.getElementEnviar().setChecked(true);
/*     */       }
/* 399 */       if (reg.getMismoProveedor().equals("S")) {
/* 400 */         this.pagHTML.getElementMismoProveedor().setChecked(true);
/*     */       }
/* 402 */       if (reg.getMismoCliente().equals("S")) {
/* 403 */         this.pagHTML.getElementMismoCliente().setChecked(true);
/*     */       }
/* 405 */       if (reg.getIndCorreoCliente().equals("S")) {
/* 406 */         this.pagHTML.getElementIndCorreoCliente().setChecked(true);
/*     */       }
/*     */       
/* 409 */       if (reg.getEnviar_hermana().equals("S")) {
/* 410 */         this.pagHTML.getElementIndHermana().setChecked(true);
/*     */       }
/*     */       
/* 413 */       if (reg.getEnviar_si_hermana_cerrada().equals("S")) {
/* 414 */         this.pagHTML.getElementIndHermanaCerrada().setChecked(true);
/*     */       }
/*     */       
/* 417 */       if (reg.getInd_cliente_inicial().equals("S")) {
/* 418 */         this.pagHTML.getElementIndfuncionario().setChecked(true);
/*     */       }
/*     */       
/* 421 */       HTMLSelectElement combo = this.pagHTML.getElementServicioInicio();
/* 422 */       comboServicios(combo, "U", codigoFlujo, reg.getServicioInicio(), reg.getServicioInicio(), false);
/*     */       
/* 424 */       combo = this.pagHTML.getElementCodigoEstado();
/* 425 */       llenarCombo(combo, "ESTADOS", "codigo", "descripcion", "1=1", "" + reg.getCodigoEstado(), true);
/*     */       
/* 427 */       combo = this.pagHTML.getElementServicioDestino();
/* 428 */       comboServicios(combo, "D", codigoFlujo, reg.getServicioInicio(), reg.getServicioDestino(), true);
/*     */       
/* 430 */       combo = this.pagHTML.getElementEstado();
/* 431 */       comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), true);
/*     */       
/* 433 */       if (reg.getServicioInicio() > 0) {
/*     */         
/* 435 */         combo = this.pagHTML.getElementCaracteristicaCorreo();
/* 436 */         comboCaracteristicasCorreo(combo, reg.getServicioInicio(), reg.getCaracteristicaCorreo(), true);
/*     */         
/* 438 */         combo = this.pagHTML.getElementCaracteristica();
/* 439 */         comboCaracteristicas(combo, reg.getServicioInicio(), reg.getCaracteristica(), true);
/*     */         
/* 441 */         combo = this.pagHTML.getElementValorCaracteristica();
/* 442 */         comboValorCaracteristicas(combo, reg.getCaracteristica(), reg.getCaracteristicaValor(), true);
/*     */       } 
/*     */       
/* 445 */       this.pagHTML.getElementSecuencia().setReadOnly(true);
/*     */     } 
/* 447 */     this.pagHTML.getElement_operacion().setValue("M");
/* 448 */     activarVista("nuevo");
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
/*     */   private void nuevo(HttpPresentationComms comms, int codigoFlujo, int codigoServicio) throws HttpPresentationException, KeywordValueException {
/* 461 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 463 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 464 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 466 */     catch (Exception e) {}
/*     */     
/* 468 */     activarVista("nuevo");
/* 469 */     HTMLSelectElement combo = this.pagHTML.getElementServicioInicio();
/* 470 */     comboServicios(combo, "U", codigoFlujo, codigoServicio, 0, true);
/*     */     
/* 472 */     if (codigoServicio > 0) {
/* 473 */       combo = this.pagHTML.getElementCaracteristica();
/* 474 */       comboCaracteristicas(combo, codigoServicio, 0, true);
/*     */     } 
/*     */     
/* 477 */     combo = this.pagHTML.getElementCodigoEstado();
/* 478 */     llenarCombo(combo, "ESTADOS", "codigo", "descripcion", "1=1", "", true);
/*     */     
/* 480 */     combo = this.pagHTML.getElementServicioDestino();
/* 481 */     comboServicios(combo, "D", codigoFlujo, codigoServicio, 0, true);
/*     */     
/* 483 */     combo = this.pagHTML.getElementEstado();
/* 484 */     comboMultivalores(combo, "ESTADO_REGISTRO", "A", true);
/*     */     
/* 486 */     this.pagHTML.getElementSecuencia().setReadOnly(true);
/*     */     
/* 488 */     combo = this.pagHTML.getElementCaracteristicaCorreo();
/* 489 */     comboCaracteristicasCorreo(combo, codigoServicio, 0, true);
/*     */     
/* 491 */     FlujoDetalleDAO rs = new FlujoDetalleDAO();
/* 492 */     int siguienteRegistro = rs.siguienteRegistro(codigoFlujo);
/* 493 */     rs.close();
/* 494 */     this.pagHTML.getElementSecuencia().setValue("" + siguienteRegistro);
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
/* 505 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 507 */     Varios oVarios = new Varios();
/* 508 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "FlujoDetalleAct");
/* 509 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "FlujoDetalleDel");
/* 510 */     if (!oPermisoAct) {
/* 511 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 512 */       elem.getParentNode().removeChild(elem);
/* 513 */       elem = this.pagHTML.getElementBtnGrabar();
/* 514 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 516 */     if (!oPermisoDel) {
/* 517 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 518 */       elem.getParentNode().removeChild(elem);
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
/* 529 */     if (!vista.equals("nuevo")) {
/* 530 */       HTMLElement sel = this.pagHTML.getElementTrCreacionRegistro();
/* 531 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 533 */     if (!vista.equals("consulta")) {
/* 534 */       HTMLElement sel = this.pagHTML.getElementTrResultados();
/* 535 */       sel.getParentNode().removeChild(sel);
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
/* 549 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 550 */     atrib.setValue(valor);
/* 551 */     return atrib;
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
/* 564 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 565 */     Element enlace = this.pagHTML.createElement("a");
/* 566 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 567 */     enlace.appendChild(hijo);
/* 568 */     Attr donde = this.pagHTML.createAttribute("href");
/* 569 */     donde.setValue(vinculo);
/* 570 */     enlace.setAttributeNode(donde);
/* 571 */     td.appendChild(enlace);
/* 572 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 573 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 583 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 584 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 585 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 586 */     return td;
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
/* 601 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 602 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 603 */     rs.close();
/* 604 */     if (dejarBlanco) {
/* 605 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 606 */       op.setValue("");
/* 607 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 608 */       combo.appendChild(op);
/*     */     } 
/* 610 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 611 */     while (iterator.hasNext()) {
/* 612 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 613 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 614 */       op.setValue("" + reg.getCodigo());
/* 615 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 616 */       if (defecto.equals(reg.getCodigo())) {
/* 617 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 618 */         escogida.setValue("on");
/* 619 */         op.setAttributeNode(escogida);
/*     */       } 
/* 621 */       combo.appendChild(op);
/*     */     } 
/* 623 */     arr.clear();
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
/* 645 */     if (dejarBlanco) {
/* 646 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 647 */       op.setValue("");
/* 648 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 649 */       combo.appendChild(op);
/*     */     } 
/* 651 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 652 */     Collection<TGeneralDTO> arr = rsTGen.cargarTodosArray(tabla, codigo, descripcion, condicion);
/* 653 */     rsTGen.close();
/* 654 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 655 */     while (iterator.hasNext()) {
/* 656 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 657 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 658 */       op.setValue("" + regGeneral.getCodigo());
/* 659 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 660 */       if (defecto != null && defecto.equals(regGeneral.getCodigo())) {
/* 661 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 662 */         escogida.setValue("on");
/* 663 */         op.setAttributeNode(escogida);
/*     */       } 
/* 665 */       combo.appendChild(op);
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
/*     */   private void comboServicios(HTMLSelectElement combo, String cualCombo, int codigoFlujo, int codigoServicio, int defecto, boolean dejarBlanco) {
/* 688 */     if (dejarBlanco) {
/* 689 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 690 */       op.setValue("");
/* 691 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 692 */       combo.appendChild(op);
/*     */     } 
/*     */     
/* 695 */     ServiciosDAO rs = new ServiciosDAO();
/* 696 */     Collection<ServiciosDTO> arr = rs.serviciosFlujo(codigoFlujo, codigoServicio, cualCombo);
/*     */     
/* 698 */     rs.close();
/*     */     
/* 700 */     Iterator<ServiciosDTO> iterator = arr.iterator();
/* 701 */     while (iterator.hasNext()) {
/* 702 */       ServiciosDTO reg = (ServiciosDTO)iterator.next();
/* 703 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 704 */       op.setValue("" + reg.getCodigo());
/* 705 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 706 */       if (defecto == reg.getCodigo()) {
/* 707 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 708 */         escogida.setValue("on");
/* 709 */         op.setAttributeNode(escogida);
/*     */       } 
/* 711 */       combo.appendChild(op);
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
/*     */   private void comboCaracteristicas(HTMLSelectElement combo, int codigoServicio, int caracteristica, boolean dejarBlanco) {
/* 732 */     if (dejarBlanco) {
/* 733 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 734 */       op.setValue("");
/* 735 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 736 */       combo.appendChild(op);
/*     */     } 
/*     */     
/* 739 */     CaracteristicasServicioDAO rs = new CaracteristicasServicioDAO();
/* 740 */     Collection<CaracteristicasServicioDTO> arr = rs.cargarParaServicio(codigoServicio);
/* 741 */     rs.close();
/*     */     
/* 743 */     Iterator<CaracteristicasServicioDTO> iterator = arr.iterator();
/* 744 */     while (iterator.hasNext()) {
/*     */       
/* 746 */       CaracteristicasServicioDTO reg = (CaracteristicasServicioDTO)iterator.next();
/* 747 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 748 */       op.setValue("" + reg.getCodigoCaracteristica());
/*     */       
/* 750 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/*     */       
/* 752 */       if (caracteristica == reg.getCodigoCaracteristica()) {
/* 753 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 754 */         escogida.setValue("on");
/* 755 */         op.setAttributeNode(escogida);
/*     */       } 
/* 757 */       combo.appendChild(op);
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
/*     */   private void comboCaracteristicasCorreo(HTMLSelectElement combo, int codigoServicio, int caracteristica, boolean dejarBlanco) {
/* 778 */     if (dejarBlanco) {
/* 779 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 780 */       op.setValue("");
/* 781 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 782 */       combo.appendChild(op);
/*     */     } 
/*     */     
/* 785 */     CaracteristicasServicioDAO rs = new CaracteristicasServicioDAO();
/* 786 */     Collection<CaracteristicasServicioDTO> arr = rs.cargarParaCorreo(codigoServicio);
/* 787 */     rs.close();
/*     */     
/* 789 */     Iterator<CaracteristicasServicioDTO> iterator = arr.iterator();
/* 790 */     while (iterator.hasNext()) {
/*     */       
/* 792 */       CaracteristicasServicioDTO reg = (CaracteristicasServicioDTO)iterator.next();
/* 793 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 794 */       op.setValue("" + reg.getCodigoCaracteristica());
/*     */       
/* 796 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/*     */       
/* 798 */       if (caracteristica == reg.getCodigoCaracteristica()) {
/* 799 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 800 */         escogida.setValue("on");
/* 801 */         op.setAttributeNode(escogida);
/*     */       } 
/* 803 */       combo.appendChild(op);
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
/*     */   private void comboValorCaracteristicas(HTMLSelectElement combo, int caracteristica, int valor, boolean dejarBlanco) {
/* 823 */     if (dejarBlanco) {
/* 824 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 825 */       op.setValue("");
/* 826 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 827 */       combo.appendChild(op);
/*     */     } 
/*     */     
/* 830 */     CaracteristicasValorDAO rs = new CaracteristicasValorDAO();
/* 831 */     Collection<CaracteristicasValorDTO> arr = rs.cargarParaCaracteristica(caracteristica);
/* 832 */     rs.close();
/*     */     
/* 834 */     Iterator<CaracteristicasValorDTO> iterator = arr.iterator();
/* 835 */     while (iterator.hasNext()) {
/*     */       
/* 837 */       CaracteristicasValorDTO reg = (CaracteristicasValorDTO)iterator.next();
/* 838 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 839 */       op.setValue("" + reg.getValor());
/*     */       
/* 841 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/*     */       
/* 843 */       if (valor == reg.getValor()) {
/* 844 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 845 */         escogida.setValue("on");
/* 846 */         op.setAttributeNode(escogida);
/*     */       } 
/* 848 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\FlujoDetalle.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */