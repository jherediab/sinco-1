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
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.Servicios;
/*     */ import sinco.presentation.ServiciosHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Servicios
/*     */   implements HttpPresentation
/*     */ {
/*     */   private ServiciosHTML pagHTML;
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
/*  53 */     this.pagHTML = (ServiciosHTML)comms.xmlcFactory.create(ServiciosHTML.class);
/*  54 */     permisos(comms);
/*     */     
/*  56 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  57 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  60 */     if (_operacion.equals("P")) {
/*  61 */       editar(comms);
/*     */     }
/*  63 */     else if (_operacion.equals("Nuevo")) {
/*  64 */       nuevo(comms);
/*     */     } 
/*     */     
/*  67 */     if (_operacion.equals("V")) {
/*  68 */       verRegistro(comms);
/*     */     }
/*  70 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  71 */     comms.response.writeDOM(this.pagHTML);
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
/*  83 */     String _operacion = comms.request.getParameter("_operacion");
/*  84 */     String elUsuario = "" + comms.session.getUser().getName();
/*  85 */     int codigo = 0;
/*     */     try {
/*  87 */       codigo = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/*  89 */     catch (Exception e) {
/*  90 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigo"));
/*     */     } 
/*     */     
/*  93 */     RespuestaBD rta = new RespuestaBD();
/*  94 */     if (_operacion.equals("E")) {
/*  95 */       ServiciosDAO rs = new ServiciosDAO();
/*  96 */       if (!rs.getEstadoConexion()) {
/*  97 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*  99 */       rta = rs.eliminarRegistro(codigo);
/* 100 */       if (!rta.isRta()) {
/* 101 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorServicios&p1=" + rta.getMensaje()));
/*     */       }
/* 103 */       rs.close();
/* 104 */       String sPagina = "Servicios.po?_operacion=X";
/* 105 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 107 */     String descripcion = comms.request.getParameter("descripcion");
/* 108 */     String especializado = comms.request.getParameter("especializado");
/* 109 */     int duracion = 0;
/*     */     try {
/* 111 */       duracion = Integer.parseInt(comms.request.getParameter("duracion"));
/*     */     }
/* 113 */     catch (Exception e) {}
/*     */     
/* 115 */     String unidadMedida = comms.request.getParameter("unidadMedida");
/* 116 */     if (unidadMedida == null) {
/* 117 */       unidadMedida = "";
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 122 */     int porcentajeEsc1 = 0;
/*     */     try {
/* 124 */       porcentajeEsc1 = Integer.parseInt(comms.request.getParameter("porcentajeEsc1"));
/*     */     }
/* 126 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 129 */     int porcentajeEsc2 = 0;
/*     */     try {
/* 131 */       porcentajeEsc2 = Integer.parseInt(comms.request.getParameter("porcentajeEsc2"));
/*     */     }
/* 133 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 136 */     int porcentajeEsc3 = 0;
/*     */     try {
/* 138 */       porcentajeEsc3 = Integer.parseInt(comms.request.getParameter("porcentajeEsc3"));
/*     */     }
/* 140 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 143 */     int porcentajeEsc4 = 0;
/*     */     try {
/* 145 */       porcentajeEsc4 = Integer.parseInt(comms.request.getParameter("porcentajeEsc4"));
/*     */     }
/* 147 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 150 */     String tipoServicio = comms.request.getParameter("tipoServicio");
/* 151 */     if (tipoServicio == null) {
/* 152 */       tipoServicio = "";
/*     */     }
/*     */     
/* 155 */     String anidar = comms.request.getParameter("anidar");
/* 156 */     if (anidar == null) {
/* 157 */       anidar = "N";
/*     */     }
/* 159 */     String cambiaproveedor = comms.request.getParameter("cambiaproveedor");
/* 160 */     if (cambiaproveedor == null) {
/* 161 */       cambiaproveedor = "N";
/*     */     }
/* 163 */     String permitedevolver = comms.request.getParameter("permitedevolver");
/* 164 */     if (permitedevolver == null) {
/* 165 */       permitedevolver = "N";
/*     */     }
/* 167 */     String reasignarauditor = comms.request.getParameter("reasignarauditor");
/* 168 */     if (reasignarauditor == null) {
/* 169 */       reasignarauditor = "N";
/*     */     }
/* 171 */     String calificarservicio = comms.request.getParameter("calificarservicio");
/* 172 */     if (calificarservicio == null) {
/* 173 */       calificarservicio = "S";
/*     */     }
/* 175 */     String clientePreferencia = comms.request.getParameter("clientePreferencia");
/* 176 */     if (clientePreferencia == null) {
/* 177 */       clientePreferencia = "N";
/*     */     }
/* 179 */     String permiteDevolverPoliticas = comms.request.getParameter("permiteDevolverPoliticas");
/* 180 */     if (permiteDevolverPoliticas == null) {
/* 181 */       permiteDevolverPoliticas = "N";
/*     */     }
/* 183 */     String autonumerarSolicitud = comms.request.getParameter("autonumerarSolicitud");
/* 184 */     if (autonumerarSolicitud == null) {
/* 185 */       autonumerarSolicitud = "N";
/*     */     }
/* 187 */     String permiteDevolverAtencion = comms.request.getParameter("permiteDevolverAtencion");
/* 188 */     if (permiteDevolverAtencion == null) {
/* 189 */       permiteDevolverAtencion = "N";
/*     */     }
/* 191 */     String permitirEscogerProveedor = comms.request.getParameter("permitirEscogerProveedor");
/* 192 */     if (permitirEscogerProveedor == null) {
/* 193 */       permitirEscogerProveedor = "N";
/*     */     }
/* 195 */     String autoaceptarAplamientos = comms.request.getParameter("autoaceptarAplamientos");
/* 196 */     if (autoaceptarAplamientos == null) {
/* 197 */       autoaceptarAplamientos = "N";
/*     */     }
/* 199 */     String cerrarPorEscalamientos = comms.request.getParameter("cerrarPorEscalamientos");
/* 200 */     if (cerrarPorEscalamientos == null) {
/* 201 */       cerrarPorEscalamientos = "N";
/*     */     }
/* 203 */     String indFlujoTrabajo = comms.request.getParameter("indFlujoTrabajo");
/* 204 */     if (indFlujoTrabajo == null) {
/* 205 */       indFlujoTrabajo = "N";
/*     */     }
/* 207 */     String indCorreoCalificacion = comms.request.getParameter("indCorreoCalificacion");
/* 208 */     if (indCorreoCalificacion == null) {
/* 209 */       indCorreoCalificacion = "N";
/*     */     }
/* 211 */     String indAvanzarCaracteristica = comms.request.getParameter("indAvanzarCaracteristica");
/* 212 */     if (indAvanzarCaracteristica == null) {
/* 213 */       indAvanzarCaracteristica = "N";
/*     */     }
/*     */     
/* 216 */     String proceso = comms.request.getParameter("proceso");
/* 217 */     if (proceso == null) {
/* 218 */       proceso = "";
/*     */     }
/*     */     
/* 221 */     String subproceso = comms.request.getParameter("subproceso");
/* 222 */     if (subproceso == null) {
/* 223 */       subproceso = "";
/*     */     }
/*     */ 
/*     */     
/* 227 */     String archivoAnexo = comms.request.getParameter("documentoAnexo");
/* 228 */     if (archivoAnexo == null) {
/* 229 */       archivoAnexo = "";
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 234 */     int numeroAnexos = 0;
/*     */     try {
/* 236 */       numeroAnexos = Integer.parseInt(comms.request.getParameter("numeroAnexos"));
/*     */     }
/* 238 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 241 */     int numeroAnexosEnvio = 0;
/*     */     try {
/* 243 */       numeroAnexosEnvio = Integer.parseInt(comms.request.getParameter("numeroAnexosEnvio"));
/*     */     }
/* 245 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 248 */     String correoNotificacion = comms.request.getParameter("correoNotificacion");
/* 249 */     int escalarA = 0;
/*     */     try {
/* 251 */       escalarA = Integer.parseInt(comms.request.getParameter("escalarA"));
/*     */     }
/* 253 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 256 */     String estado = comms.request.getParameter("estado");
/* 257 */     String observaciones = comms.request.getParameter("observaciones");
/* 258 */     String mensaje = comms.request.getParameter("mensaje");
/* 259 */     ServiciosDAO rs = new ServiciosDAO();
/* 260 */     if (!rs.getEstadoConexion()) {
/* 261 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 263 */     if (_operacion.equals("C")) {
/* 264 */       rta = rs.crearRegistro(codigo, descripcion, especializado, duracion, unidadMedida, porcentajeEsc1, porcentajeEsc2, porcentajeEsc3, porcentajeEsc4, tipoServicio, anidar, cambiaproveedor, permitedevolver, reasignarauditor, calificarservicio, clientePreferencia, permiteDevolverPoliticas, autonumerarSolicitud, permiteDevolverAtencion, permitirEscogerProveedor, autoaceptarAplamientos, cerrarPorEscalamientos, indFlujoTrabajo, indCorreoCalificacion, indAvanzarCaracteristica, proceso, subproceso, archivoAnexo, numeroAnexos, numeroAnexosEnvio, correoNotificacion, escalarA, estado, observaciones, mensaje, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 301 */       codigo = rta.getSecuencia();
/*     */     } else {
/*     */       
/* 304 */       rta = rs.modificarRegistro(codigo, descripcion, especializado, duracion, unidadMedida, porcentajeEsc1, porcentajeEsc2, porcentajeEsc3, porcentajeEsc4, tipoServicio, anidar, cambiaproveedor, permitedevolver, reasignarauditor, calificarservicio, clientePreferencia, permiteDevolverPoliticas, autonumerarSolicitud, permiteDevolverAtencion, permitirEscogerProveedor, autoaceptarAplamientos, cerrarPorEscalamientos, indFlujoTrabajo, indCorreoCalificacion, indAvanzarCaracteristica, proceso, subproceso, numeroAnexos, numeroAnexosEnvio, correoNotificacion, escalarA, estado, observaciones, mensaje, elUsuario);
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
/* 341 */     rs.close();
/* 342 */     if (!rta.isRta()) {
/* 343 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorServicios&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 346 */     String sPagina = "Servicios.po?_operacion=P&codigo=" + codigo + "";
/* 347 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 358 */     int codigo = 0;
/*     */     try {
/* 360 */       codigo = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 362 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 365 */     ServiciosDAO rs = new ServiciosDAO();
/* 366 */     if (!rs.getEstadoConexion()) {
/* 367 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 369 */     ServiciosDTO reg = rs.cargarRegistro(codigo);
/* 370 */     rs.close();
/* 371 */     if (reg != null) {
/* 372 */       this.pagHTML.getElementCodigo().setValue("" + reg.getCodigo());
/* 373 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 374 */       this.pagHTML.getElementDuracion().setValue("" + reg.getDuracion());
/* 375 */       this.pagHTML.getElementPorcentajeEsc1().setValue("" + reg.getPorcentajeEsc1());
/* 376 */       this.pagHTML.getElementPorcentajeEsc2().setValue("" + reg.getPorcentajeEsc2());
/* 377 */       this.pagHTML.getElementPorcentajeEsc3().setValue("" + reg.getPorcentajeEsc3());
/* 378 */       this.pagHTML.getElementPorcentajeEsc4().setValue("" + reg.getPorcentajeEsc4());
/* 379 */       if (reg.getAnidar().equals("S")) {
/* 380 */         this.pagHTML.getElementAnidar().setChecked(true);
/*     */       }
/* 382 */       if (reg.getCambiaProveedor().equals("S")) {
/* 383 */         this.pagHTML.getElementCambiaproveedor().setChecked(true);
/*     */       }
/* 385 */       if (reg.getClientePreferencia().equals("S")) {
/* 386 */         this.pagHTML.getElementClientePreferencia().setChecked(true);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 408 */       if (reg.getPermiteDevolverAtencion().equals("S")) {
/* 409 */         this.pagHTML.getElementPermiteDevolverAtencion().setChecked(true);
/*     */       }
/* 411 */       if (reg.getPermitirEscogerProveedor().equals("S")) {
/* 412 */         this.pagHTML.getElementPermitirEscogerProveedor().setChecked(true);
/*     */       }
/* 414 */       if (reg.getAutoaceptarAplamientos().equals("S")) {
/* 415 */         this.pagHTML.getElementAutoaceptarAplamientos().setChecked(true);
/*     */       }
/* 417 */       if (reg.getCerrarPorEscalamientos().equals("S")) {
/* 418 */         this.pagHTML.getElementCerrarPorEscalamientos().setChecked(true);
/*     */       }
/* 420 */       if (reg.getIndFlujoTrabajo().equals("S")) {
/* 421 */         this.pagHTML.getElementIndFlujoTrabajo().setChecked(true);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 439 */       if (reg.getArchivoAnexo() != null) {
/* 440 */         String url = "CalVerDocumento.po?numeroDocumento=" + reg.getArchivoAnexo() + "&tipoDocumento=W&_operacion=VDC";
/* 441 */         Element enlaceC = this.pagHTML.getElementDocumentoAnexo();
/* 442 */         enlaceC.appendChild(newhref(reg.getArchivoAnexo(), url, true));
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 449 */       this.pagHTML.getElementNumeroAnexos().setValue("" + reg.getNumeroAnexos());
/* 450 */       this.pagHTML.getElementNumeroAnexosEnvio().setValue("" + reg.getNumeroAnexosEnvio());
/*     */       
/* 452 */       this.pagHTML.getElementEscalarA().setValue("" + reg.getEscalarA());
/* 453 */       this.pagHTML.getElementObservaciones().setValue("" + reg.getObservaciones());
/* 454 */       this.pagHTML.getElementMensaje().setValue("" + reg.getMensaje());
/* 455 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 456 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 457 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 458 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/*     */ 
/*     */       
/* 461 */       HTMLSelectElement combo = this.pagHTML.getElementEspecializado();
/* 462 */       comboMultivalores(combo, "CLASE_SERVICIO", "" + reg.getEspecializado(), true);
/*     */       
/* 464 */       combo = this.pagHTML.getElementUnidadMedida();
/* 465 */       llenarCombo(combo, "sis_unidades_medida", "codigo_unidad", "nombre_unidad", "codigo_grupo=5", "" + reg.getUnidadMedida(), true);
/*     */       
/* 467 */       combo = this.pagHTML.getElementTipoServicio();
/* 468 */       comboMultivalores(combo, "TIPO_SERVICIO", "" + reg.getTipoServicio(), true);
/*     */ 
/*     */       
/* 471 */       combo = this.pagHTML.getElementProceso();
/* 472 */       llenarCombo(combo, "PROCESOS", "CODIGO", "DESCRIPCION", "ESTADO='A'", "" + reg.getProceso(), true);
/*     */       
/* 474 */       if (reg.getProceso().length() > 0) {
/* 475 */         combo = this.pagHTML.getElementSubproceso();
/* 476 */         llenarCombo(combo, "SUBPROCESOS", "CODIGO", "DESCRIPCION", "ESTADO='A' AND PROCESO='" + reg.getProceso() + "'", "" + reg.getSubproceso(), true);
/*     */       } 
/*     */       
/* 479 */       combo = this.pagHTML.getElementEstado();
/* 480 */       comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), true);
/*     */ 
/*     */       
/* 483 */       this.pagHTML.getElementCodigo().setReadOnly(true);
/*     */     } 
/* 485 */     this.pagHTML.getElement_operacion().setValue("M");
/* 486 */     activarVista("nuevo");
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
/* 498 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 500 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 501 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 503 */     catch (Exception e) {}
/*     */     
/* 505 */     activarVista("nuevo");
/* 506 */     HTMLSelectElement combo = this.pagHTML.getElementEspecializado();
/* 507 */     comboMultivalores(combo, "CLASE_SERVICIO", "", true);
/*     */     
/* 509 */     combo = this.pagHTML.getElementUnidadMedida();
/* 510 */     llenarCombo(combo, "sis_unidades_medida", "codigo_unidad", "nombre_unidad", "codigo_grupo=5", "", true);
/*     */     
/* 512 */     combo = this.pagHTML.getElementTipoServicio();
/* 513 */     comboMultivalores(combo, "TIPO_SERVICIO", "", true);
/*     */     
/* 515 */     combo = this.pagHTML.getElementProceso();
/* 516 */     llenarCombo(combo, "PROCESOS", "CODIGO", "DESCRIPCION", "ESTADO='A'", "", true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 521 */     combo = this.pagHTML.getElementEstado();
/* 522 */     comboMultivalores(combo, "ESTADO_REGISTRO", "A", true);
/*     */     
/* 524 */     this.pagHTML.getElementCodigo().setReadOnly(true);
/* 525 */     this.pagHTML.getElementCodigo().setValue("0");
/*     */     
/* 527 */     this.pagHTML.getElementCambiaproveedor().setChecked(true);
/* 528 */     this.pagHTML.getElementPermiteDevolverAtencion().setChecked(true);
/* 529 */     this.pagHTML.getElementAutoaceptarAplamientos().setChecked(true);
/*     */     
/* 531 */     this.pagHTML.getElementCerrarPorEscalamientos().setChecked(true);
/* 532 */     this.pagHTML.getElementPermitirEscogerProveedor().setChecked(true);
/*     */     
/* 534 */     this.pagHTML.getElementPorcentajeEsc1().setValue("100");
/* 535 */     this.pagHTML.getElementPorcentajeEsc2().setValue("50");
/* 536 */     this.pagHTML.getElementPorcentajeEsc3().setValue("50");
/* 537 */     this.pagHTML.getElementPorcentajeEsc4().setValue("50");
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
/*     */   private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
/* 550 */     activarVista("consulta");
/* 551 */     int codigo = 0;
/*     */     try {
/* 553 */       codigo = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 555 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 558 */     String descripcion = comms.request.getParameter("descripcion");
/* 559 */     if (descripcion == null) {
/* 560 */       descripcion = "";
/*     */     }
/* 562 */     String especializado = comms.request.getParameter("especializado");
/* 563 */     if (especializado == null) {
/* 564 */       especializado = "";
/*     */     }
/* 566 */     HTMLSelectElement combo = this.pagHTML.getElementFespecializado();
/* 567 */     comboMultivalores(combo, "CLASE_SERVICIO", "" + especializado, true);
/*     */ 
/*     */     
/* 570 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 575 */     ServiciosDAO rs = new ServiciosDAO();
/* 576 */     if (!rs.getEstadoConexion()) {
/* 577 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 579 */     Collection<ServiciosDTO> arr = rs.cargarTodos(codigo, descripcion, especializado);
/*     */ 
/*     */ 
/*     */     
/* 583 */     rs.close();
/* 584 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 585 */     int cuantas = 0;
/* 586 */     Iterator<ServiciosDTO> iterator = arr.iterator();
/* 587 */     while (iterator.hasNext()) {
/* 588 */       ServiciosDTO reg = (ServiciosDTO)iterator.next();
/* 589 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 590 */       eltr.appendChild(newtd("" + reg.getCodigo()));
/* 591 */       String url = "Servicios.po?_operacion=V&codigo=" + reg.getCodigo() + "";
/* 592 */       eltr.appendChild(newtdhref("" + reg.getDescripcion(), url));
/* 593 */       eltr.appendChild(newtd("" + reg.getNombreEspecializado()));
/* 594 */       eltr.appendChild(newtd("" + reg.getDuracion()));
/* 595 */       eltr.appendChild(newtd("" + reg.getNombreUnidadMedida()));
/* 596 */       hte.appendChild(eltr);
/* 597 */       cuantas++;
/*     */     } 
/* 599 */     arr.clear();
/* 600 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 613 */     int codigo = 0;
/*     */     try {
/* 615 */       codigo = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 617 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 620 */     ServiciosDAO rs = new ServiciosDAO();
/* 621 */     if (!rs.getEstadoConexion()) {
/* 622 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 624 */     ServiciosDTO reg = rs.cargarRegistro(codigo);
/* 625 */     rs.close();
/* 626 */     if (reg != null) {
/* 627 */       this.pagHTML.setTextCodigoEd("" + reg.getCodigo());
/* 628 */       this.pagHTML.setTextDescripcionEd("" + reg.getDescripcion());
/* 629 */       this.pagHTML.setTextEspecializadoEd("" + reg.getNombreEspecializado());
/* 630 */       this.pagHTML.setTextDuracionEd("" + reg.getDuracion());
/* 631 */       this.pagHTML.setTextUnidadMedidaEd("" + reg.getNombreUnidadMedida());
/* 632 */       this.pagHTML.setTextPorcentajeEsc1Ed("" + reg.getPorcentajeEsc1());
/* 633 */       this.pagHTML.setTextPorcentajeEsc2Ed("" + reg.getPorcentajeEsc2());
/* 634 */       this.pagHTML.setTextPorcentajeEsc3Ed("" + reg.getPorcentajeEsc3());
/* 635 */       this.pagHTML.setTextPorcentajeEsc4Ed("" + reg.getPorcentajeEsc4());
/* 636 */       this.pagHTML.setTextAnidarEd("" + reg.getAnidar());
/* 637 */       this.pagHTML.setTextCambiaproveedorEd("" + reg.getCambiaProveedor());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 654 */       this.pagHTML.setTextClientePreferenciaEd("" + reg.getClientePreferencia());
/* 655 */       this.pagHTML.setTextPermiteDevolverAtencionEd("" + reg.getPermiteDevolverAtencion());
/* 656 */       this.pagHTML.setTextPermitirEscogerProveedorEd("" + reg.getPermitirEscogerProveedor());
/* 657 */       this.pagHTML.setTextAutoaceptarAplamientosEd("" + reg.getAutoaceptarAplamientos());
/* 658 */       this.pagHTML.setTextCerrarPorEscalamientosEd("" + reg.getCerrarPorEscalamientos());
/* 659 */       this.pagHTML.setTextIndFlujoTrabajoEd("" + reg.getIndFlujoTrabajo());
/* 660 */       this.pagHTML.setTextProcesoEd("" + reg.getNombreProceso());
/*     */ 
/*     */       
/* 663 */       if (reg.getArchivoAnexo() != null) {
/* 664 */         String url = "CalVerDocumento.po?numeroDocumento=" + reg.getArchivoAnexo() + "&tipoDocumento=W&_operacion=VDC";
/* 665 */         Element enlaceC = this.pagHTML.getElementArchivoAnexoEd();
/* 666 */         enlaceC.appendChild(newhref(reg.getArchivoAnexo(), url, true));
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 677 */       this.pagHTML.setTextNumeroAnexosEd("" + reg.getNumeroAnexos());
/* 678 */       this.pagHTML.setTextNumeroAnexosEnvioEd("" + reg.getNumeroAnexosEnvio());
/* 679 */       this.pagHTML.setTextEscalarAEd("" + reg.getEscalarA());
/* 680 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/* 681 */       this.pagHTML.setTextObservacionesEd("" + reg.getObservaciones());
/* 682 */       this.pagHTML.setTextMensajeEd("" + reg.getMensaje());
/* 683 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 684 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 685 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 686 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 688 */       this.pagHTML.getElementCodigoKey().setValue("" + reg.getCodigo());
/* 689 */       this.pagHTML.getElementEspecializadoKey().setValue("" + reg.getEspecializado());
/*     */       
/* 691 */       if (!reg.getClientePreferencia().equals("S")) {
/* 692 */         HTMLElement elem = this.pagHTML.getElementBClientes();
/* 693 */         elem.getParentNode().removeChild(elem);
/*     */       } 
/*     */       
/* 696 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 698 */     activarVista("editar");
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
/* 709 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 711 */     Varios oVarios = new Varios();
/* 712 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Adm_ServiciosAct");
/* 713 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Adm_ServiciosDel");
/* 714 */     if (!oPermisoAct) {
/* 715 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 716 */       elem.getParentNode().removeChild(elem);
/* 717 */       elem = this.pagHTML.getElementBtnGrabar();
/* 718 */       elem.getParentNode().removeChild(elem);
/* 719 */       elem = this.pagHTML.getElementBtnModificar();
/* 720 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 722 */     if (!oPermisoDel) {
/* 723 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 724 */       elem.getParentNode().removeChild(elem);
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
/* 735 */     if (!vista.equals("nuevo")) {
/* 736 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 737 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 739 */     if (!vista.equals("editar")) {
/* 740 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 741 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 743 */     if (!vista.equals("consulta")) {
/* 744 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 745 */       sel.getParentNode().removeChild(sel);
/* 746 */       sel = this.pagHTML.getElementDivResultados();
/* 747 */       sel.getParentNode().removeChild(sel);
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
/* 761 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 762 */     atrib.setValue(valor);
/* 763 */     return atrib;
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
/* 776 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 777 */     Element enlace = this.pagHTML.createElement("a");
/* 778 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 779 */     enlace.appendChild(hijo);
/* 780 */     Attr donde = this.pagHTML.createAttribute("href");
/* 781 */     donde.setValue(vinculo);
/* 782 */     enlace.setAttributeNode(donde);
/* 783 */     td.appendChild(enlace);
/* 784 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 785 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 795 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 796 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 797 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 798 */     return td;
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
/* 813 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 814 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 815 */     rs.close();
/* 816 */     if (dejarBlanco) {
/* 817 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 818 */       op.setValue("");
/* 819 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 820 */       combo.appendChild(op);
/*     */     } 
/* 822 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 823 */     while (iterator.hasNext()) {
/* 824 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 825 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 826 */       op.setValue("" + reg.getCodigo());
/* 827 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 828 */       if (defecto.equals(reg.getCodigo())) {
/* 829 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 830 */         escogida.setValue("on");
/* 831 */         op.setAttributeNode(escogida);
/*     */       } 
/* 833 */       combo.appendChild(op);
/*     */     } 
/* 835 */     arr.clear();
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
/* 856 */     if (dejarBlanco) {
/* 857 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 858 */       op.setValue("");
/* 859 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 860 */       combo.appendChild(op);
/*     */     } 
/* 862 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 863 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/* 864 */     rsTGen.close();
/* 865 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 866 */     while (iterator.hasNext()) {
/* 867 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 868 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 869 */       op.setValue("" + regGeneral.getCodigoS());
/* 870 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 871 */       if (defecto.equals(regGeneral.getCodigo())) {
/* 872 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 873 */         escogida.setValue("on");
/* 874 */         op.setAttributeNode(escogida);
/*     */       } 
/* 876 */       combo.appendChild(op);
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
/*     */   private Element newhref(String contenido, String vinculo, boolean nueva) {
/* 891 */     Element enlace = this.pagHTML.createElement("a");
/* 892 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 893 */     enlace.appendChild(hijo);
/* 894 */     Attr donde = this.pagHTML.createAttribute("href");
/* 895 */     donde.setValue(vinculo);
/* 896 */     enlace.setAttributeNode(donde);
/* 897 */     if (nueva) {
/* 898 */       enlace.setAttributeNode(newAttr("TARGET", "_blank"));
/*     */     }
/* 900 */     return enlace;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\Servicios.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */