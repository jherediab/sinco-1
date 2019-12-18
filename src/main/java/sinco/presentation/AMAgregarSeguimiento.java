/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import sinco.business.AMAccionesDTO;
/*     */ import sinco.business.AMCausasDTO;
/*     */ import sinco.business.AMSeguimientoDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AMAccionesDAO;
/*     */ import sinco.data.AMCausasDAO;
/*     */ import sinco.data.AMSeguimientoDAO;
/*     */ import sinco.presentation.AMAgregarSeguimiento;
/*     */ import sinco.presentation.AMAgregarSeguimientoHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AMAgregarSeguimiento
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AMAgregarSeguimientoHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  31 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  32 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  35 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */ 
/*     */     
/*  38 */     String operacion = comms.request.getParameter("_operacion");
/*  39 */     if (operacion == null || operacion.length() == 0) {
/*  40 */       operacion = "";
/*     */     }
/*     */     
/*  43 */     if (operacion.equals("SEGV2")) {
/*  44 */       grabarSeguimientoNuevo(comms);
/*     */     }
/*  46 */     else if (operacion.equals("ActSeg")) {
/*  47 */       actualizarSeguimiento(comms);
/*     */     }
/*  49 */     else if (operacion.equals("Cacao")) {
/*  50 */       cacao(comms);
/*     */     }
/*  52 */     else if (operacion.equals("CambioResp")) {
/*  53 */       cambioResponsable(comms);
/*     */     }
/*  55 */     else if (operacion.equals("CambioEstado")) {
/*  56 */       cambioEstado(comms);
/*     */     }
/*  58 */     else if (operacion.equals("C") || operacion.equals("M") || operacion.equals("J") || operacion.equals("E")) {
/*  59 */       creacion(comms, operacion);
/*     */     
/*     */     }
/*  62 */     else if (operacion.equals("P")) {
/*  63 */       this.pagHTML = (AMAgregarSeguimientoHTML)comms.xmlcFactory.create(AMAgregarSeguimientoHTML.class);
/*  64 */       editar(comms);
/*  65 */       this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  66 */       comms.response.writeDOM(this.pagHTML);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  71 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=OperacionNoDefinida"));
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
/*     */   private boolean editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  85 */     int numero = 0;
/*     */     try {
/*  87 */       numero = Integer.parseInt(comms.request.getParameter("numero"));
/*     */     }
/*  89 */     catch (Exception e) {
/*  90 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=numero"));
/*     */     } 
/*     */     
/*  93 */     int causa = 0;
/*     */     try {
/*  95 */       causa = Integer.parseInt(comms.request.getParameter("causa"));
/*     */     }
/*  97 */     catch (Exception e) {
/*  98 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=causa"));
/*     */     } 
/*     */     
/* 101 */     int consecutivo = 0;
/*     */     try {
/* 103 */       consecutivo = Integer.parseInt(comms.request.getParameter("consecutivo"));
/*     */     }
/* 105 */     catch (Exception e) {
/* 106 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=consecutivo"));
/*     */     } 
/*     */ 
/*     */     
/* 110 */     AMSeguimientoDAO rs = new AMSeguimientoDAO();
/* 111 */     if (!rs.getEstadoConexion()) {
/* 112 */       return false;
/*     */     }
/* 114 */     AMSeguimientoDTO reg = rs.cargarRegistro(numero, causa, consecutivo);
/* 115 */     rs.close();
/*     */     
/* 117 */     this.pagHTML.getElementObservacion().appendChild(this.pagHTML.createTextNode("" + reg.getObservacion()));
/*     */     
/* 119 */     this.pagHTML.getElementNumero().setValue("" + reg.getNumero());
/* 120 */     this.pagHTML.getElementCausa().setValue("" + reg.getCausa());
/* 121 */     this.pagHTML.getElementConsecutivo().setValue("" + reg.getConsecutivo());
/*     */     
/* 123 */     return true;
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
/*     */   private void grabarSeguimientoNuevo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 139 */     String elUsuario = "" + comms.session.getUser().getName();
/* 140 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */ 
/*     */ 
/*     */     
/* 144 */     int numero = 0;
/*     */     try {
/* 146 */       numero = Integer.parseInt(comms.request.getParameter("numero"));
/*     */     }
/* 148 */     catch (Exception e) {
/* 149 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=numero"));
/*     */     } 
/*     */     
/* 152 */     int causa = 0;
/*     */     try {
/* 154 */       causa = Integer.parseInt(comms.request.getParameter("causa"));
/*     */     }
/* 156 */     catch (Exception e) {
/* 157 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=consecutivo"));
/*     */     } 
/*     */     
/* 160 */     String observacion = comms.request.getParameter("observacion");
/*     */     
/* 162 */     AMCausasDAO rsCausas = new AMCausasDAO();
/* 163 */     if (!rsCausas.getEstadoConexion()) {
/* 164 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 166 */     boolean rta = rsCausas.crearSeguimiento(numero, causa, observacion, idNav, 'N', elUsuario);
/* 167 */     rsCausas.close();
/*     */     
/* 169 */     String sPagina = "AMDetalleV2.po?numero=" + numero;
/* 170 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/*     */   private void actualizarSeguimiento(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 187 */     String elUsuario = "" + comms.session.getUser().getName();
/* 188 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */ 
/*     */     
/* 191 */     int numero = 0;
/*     */     try {
/* 193 */       numero = Integer.parseInt(comms.request.getParameter("numero"));
/*     */     }
/* 195 */     catch (Exception e) {
/* 196 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=numero"));
/*     */     } 
/*     */     
/* 199 */     int causa = 0;
/*     */     try {
/* 201 */       causa = Integer.parseInt(comms.request.getParameter("causa"));
/*     */     }
/* 203 */     catch (Exception e) {
/* 204 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=consecutivo"));
/*     */     } 
/*     */     
/* 207 */     int consecutivo = 0;
/*     */     try {
/* 209 */       consecutivo = Integer.parseInt(comms.request.getParameter("consecutivo"));
/*     */     }
/* 211 */     catch (Exception e) {
/* 212 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=consecutivo"));
/*     */     } 
/*     */     
/* 215 */     String observacion = comms.request.getParameter("observacion");
/*     */     
/* 217 */     AMCausasDAO rsCausas = new AMCausasDAO();
/* 218 */     if (!rsCausas.getEstadoConexion()) {
/* 219 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 221 */     boolean rta = rsCausas.modificarSeguimiento(numero, causa, consecutivo, observacion, elUsuario);
/* 222 */     rsCausas.close();
/*     */     
/* 224 */     String sPagina = "AMDetalleV2.po?numero=" + numero;
/* 225 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/*     */   private void cacao(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 241 */     String elUsuario = "" + comms.session.getUser().getName();
/* 242 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */ 
/*     */ 
/*     */     
/* 246 */     int numero = 0;
/*     */     try {
/* 248 */       numero = Integer.parseInt(comms.request.getParameter("numero"));
/*     */     }
/* 250 */     catch (Exception e) {
/* 251 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=numero"));
/*     */     } 
/*     */     
/* 254 */     int causa = 0;
/*     */     try {
/* 256 */       causa = Integer.parseInt(comms.request.getParameter("causa"));
/*     */     }
/* 258 */     catch (Exception e) {
/* 259 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=consecutivo"));
/*     */     } 
/*     */     
/* 262 */     String observacion = comms.request.getParameter("observacion");
/*     */     
/* 264 */     String fechaRevision = comms.request.getParameter("fechaRevision");
/* 265 */     if (!Utilidades.validarFormatoFecha(fechaRevision)) {
/* 266 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=FechaNoValida&p1=FechaRevision"));
/*     */     }
/*     */ 
/*     */     
/* 270 */     AMCausasDAO rs = new AMCausasDAO();
/* 271 */     if (!rs.getEstadoConexion()) {
/* 272 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 275 */     AMCausasDTO regCausa = rs.cargarAMCausa(numero, causa);
/*     */     
/* 277 */     int prorrogas = 0;
/* 278 */     if (regCausa != null) {
/* 279 */       prorrogas = regCausa.getProrrogas();
/*     */     }
/*     */ 
/*     */     
/* 283 */     observacion = "Prorroga Nro (" + (prorrogas + 1) + ") de " + ParametrosDTO.getInt("AM_numero.prorrogas") + "  solicitida para " + fechaRevision + ". Justificación, " + observacion;
/*     */ 
/*     */ 
/*     */     
/* 287 */     boolean rta = rs.crearSeguimiento(numero, causa, observacion, idNav, 'S', elUsuario);
/* 288 */     rta = rs.actulizarRevision(numero, causa, fechaRevision, 'S', elUsuario, observacion);
/*     */     
/* 290 */     rs.close();
/*     */     
/* 292 */     String sPagina = "AMDetalleV2.po?numero=" + numero;
/* 293 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/*     */   private void cambioResponsable(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 309 */     String elUsuario = "" + comms.session.getUser().getName();
/* 310 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */ 
/*     */ 
/*     */     
/* 314 */     int numero = 0;
/*     */     try {
/* 316 */       numero = Integer.parseInt(comms.request.getParameter("numero"));
/*     */     }
/* 318 */     catch (Exception e) {
/* 319 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=numero"));
/*     */     } 
/*     */     
/* 322 */     int causa = 0;
/*     */     try {
/* 324 */       causa = Integer.parseInt(comms.request.getParameter("causa"));
/*     */     }
/* 326 */     catch (Exception e) {
/* 327 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=consecutivo"));
/*     */     } 
/*     */     
/* 330 */     int responsable = 0;
/*     */     try {
/* 332 */       responsable = Integer.parseInt(comms.request.getParameter("responsable"));
/*     */     }
/* 334 */     catch (Exception e) {}
/*     */     
/* 336 */     AMCausasDAO rsAMCausas = new AMCausasDAO();
/* 337 */     if (!rsAMCausas.getEstadoConexion()) {
/* 338 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 341 */     AMCausasDTO regAMCausa = rsAMCausas.cargarAMCausa(numero, causa);
/* 342 */     if (regAMCausa == null) {
/* 343 */       rsAMCausas.close();
/* 344 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoLeeRegistro&p1=AMCausas"));
/*     */     } 
/*     */     
/* 347 */     boolean rta = rsAMCausas.modificarResponsable(numero, causa, responsable, elUsuario);
/*     */     
/* 349 */     String observacion = "Responsable anterior " + regAMCausa.getNombreResponsable();
/* 350 */     rta = rsAMCausas.crearSeguimiento(numero, causa, observacion, idNav, 'S', elUsuario);
/*     */     
/* 352 */     rsAMCausas.close();
/*     */ 
/*     */     
/* 355 */     String sPagina = "AMDetalleV2.po?numero=" + numero;
/* 356 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/*     */   private void cambioEstado(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 373 */     String elUsuario = "" + comms.session.getUser().getName();
/* 374 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/* 376 */     int numero = 0;
/*     */     try {
/* 378 */       numero = Integer.parseInt(comms.request.getParameter("numero"));
/*     */     }
/* 380 */     catch (Exception e) {
/* 381 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=numero"));
/*     */     } 
/*     */     
/* 384 */     int causa = 0;
/*     */     try {
/* 386 */       causa = Integer.parseInt(comms.request.getParameter("causa"));
/*     */     }
/* 388 */     catch (Exception e) {
/* 389 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=consecutivo"));
/*     */     } 
/*     */     
/* 392 */     String observacion = comms.request.getParameter("observacion");
/*     */     
/* 394 */     int estado = 0;
/*     */     try {
/* 396 */       estado = Integer.parseInt(comms.request.getParameter("estado"));
/*     */     }
/* 398 */     catch (Exception e) {
/* 399 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=estado"));
/*     */     } 
/*     */     
/* 402 */     AMCausasDAO rsCausas = new AMCausasDAO();
/* 403 */     if (!rsCausas.getEstadoConexion()) {
/* 404 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 407 */     observacion = "Cambio de estado. Análisis, " + observacion;
/* 408 */     boolean rta = rsCausas.crearSeguimiento(numero, causa, observacion, idNav, 'S', elUsuario);
/* 409 */     rta = rsCausas.actulizarEstado(numero, causa, estado, elUsuario, observacion);
/*     */     
/* 411 */     int causaAbiertas = rsCausas.numeroCausasAbiertas(numero);
/*     */     
/* 413 */     rsCausas.close();
/*     */     
/* 415 */     String sPagina = "AMDetalleV2.po?numero=" + numero;
/* 416 */     if (causaAbiertas == 0) {
/* 417 */       sPagina = "AMActV2.po?_operacion=FINALIZA&numero=" + numero;
/*     */     }
/*     */     
/* 420 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/*     */   private void creacion(HttpPresentationComms comms, String operacion) throws HttpPresentationException, KeywordValueException {
/* 434 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/* 435 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */ 
/*     */     
/* 438 */     int numero = 0;
/*     */     try {
/* 440 */       numero = Integer.parseInt(comms.request.getParameter("numero"));
/*     */     }
/* 442 */     catch (Exception e) {
/* 443 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=numero"));
/*     */     } 
/*     */     
/* 446 */     int causa = 0;
/*     */     try {
/* 448 */       causa = Integer.parseInt(comms.request.getParameter("causa"));
/*     */     }
/* 450 */     catch (Exception e) {
/* 451 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=consecutivo"));
/*     */     } 
/*     */     
/* 454 */     int lectura = 0;
/*     */     try {
/* 456 */       lectura = Integer.parseInt(comms.request.getParameter("lectura"));
/*     */     }
/* 458 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 461 */     int consecutivo = 0;
/* 462 */     if (operacion.equals("M") || operacion.equals("E") || operacion.equals("J")) {
/*     */       try {
/* 464 */         consecutivo = Integer.parseInt(comms.request.getParameter("consecutivo"));
/*     */       }
/* 466 */       catch (Exception e) {
/* 467 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=consecutivo"));
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 475 */     AMAccionesDAO rsAcciones = new AMAccionesDAO();
/* 476 */     AMAccionesDTO regAcciones = rsAcciones.cargarRegistro(numero);
/* 477 */     if (regAcciones == null) {
/* 478 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoLeeRegistro&p1=AccionesMejoramiento"));
/*     */     }
/*     */     
/* 481 */     boolean rta = false;
/* 482 */     String observacion = comms.request.getParameter("observacion");
/*     */     
/* 484 */     String fechaRevision = "";
/* 485 */     int prorroga = 0;
/* 486 */     if (operacion.equals("J")) {
/* 487 */       fechaRevision = comms.request.getParameter("fechaRevision");
/* 488 */       if (!Utilidades.validarFormatoFecha(fechaRevision)) {
/* 489 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=FechaNoValida&p1=FechaRevision"));
/*     */       }
/* 491 */       prorroga = Integer.parseInt(comms.request.getParameter("prorroga"));
/*     */     } 
/* 493 */     int estado = 0;
/* 494 */     if (operacion.equals("E")) {
/*     */       try {
/* 496 */         estado = Integer.parseInt(comms.request.getParameter("estado"));
/*     */       }
/* 498 */       catch (Exception e) {
/* 499 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=estado"));
/*     */       } 
/*     */     }
/*     */     
/* 503 */     AMCausasDAO rsCausas = new AMCausasDAO();
/* 504 */     if (!rsCausas.getEstadoConexion()) {
/* 505 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 507 */     rta = false;
/* 508 */     if (operacion.equals("C")) {
/* 509 */       rta = rsCausas.crearSeguimiento(numero, causa, observacion, idNav, 'N', elUsuario);
/*     */     }
/* 511 */     else if (operacion.equals("M")) {
/* 512 */       rta = rsCausas.modificarSeguimiento(numero, causa, consecutivo, observacion, elUsuario);
/*     */     }
/* 514 */     else if (operacion.equals("J")) {
/* 515 */       observacion = "Prorroga Nro (" + (prorroga + 1) + ") de " + ParametrosDTO.getInt("AM_numero.prorrogas") + "  solicitida para " + fechaRevision + ". Justificación, " + observacion;
/*     */ 
/*     */ 
/*     */       
/* 519 */       rta = rsCausas.crearSeguimiento(numero, causa, observacion, idNav, 'S', elUsuario);
/* 520 */       rta = rsCausas.actulizarRevision(numero, causa, fechaRevision, 'S', elUsuario, observacion);
/*     */     }
/* 522 */     else if (operacion.equals("E")) {
/* 523 */       observacion = "Cambio de estado. Análisis, " + observacion;
/* 524 */       rta = rsCausas.crearSeguimiento(numero, causa, observacion, idNav, 'S', elUsuario);
/* 525 */       rta = rsCausas.actulizarEstado(numero, causa, estado, elUsuario, observacion);
/*     */     } 
/* 527 */     int causaAbiertas = rsCausas.numeroCausasAbiertas(numero);
/* 528 */     rsCausas.close();
/*     */     
/* 530 */     if (!rta) {
/* 531 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorActualizandoTabla&p1=AccionesMejoramiento"));
/*     */     }
/*     */ 
/*     */     
/* 535 */     String sPagina = "";
/*     */     
/* 537 */     if (operacion.equals("E") && causaAbiertas == 0) {
/* 538 */       sPagina = "AMActV2.po?_operacion=FINALIZA&numero=" + numero;
/*     */     }
/* 540 */     else if (causa == 0) {
/* 541 */       sPagina = "AMDetalleV2.po?numero=" + numero + "&lectura=" + lectura;
/*     */     
/*     */     }
/* 544 */     else if (regAcciones.getAccion().equals("R")) {
/* 545 */       sPagina = "AMDetalleCorrecion.po?numero=" + numero + "&causa=" + causa;
/*     */     } else {
/*     */       
/* 548 */       sPagina = "AMDetalleCausasV2.po?numero=" + numero + "&causa=" + causa;
/*     */     } 
/*     */     
/* 551 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AMAgregarSeguimiento.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */