/*      */ package sinco.presentation;
/*      */ 
/*      */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*      */ import com.lutris.util.KeywordValueException;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import org.w3c.dom.Attr;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.html.HTMLElement;
/*      */ import org.w3c.dom.html.HTMLFormElement;
/*      */ import org.w3c.dom.html.HTMLInputElement;
/*      */ import org.w3c.dom.html.HTMLOptionElement;
/*      */ import org.w3c.dom.html.HTMLSelectElement;
/*      */ import org.w3c.dom.html.HTMLTableElement;
/*      */ import org.w3c.dom.html.HTMLTableSectionElement;
/*      */ import sinco.business.AplazamientosSolicitudDTO;
/*      */ import sinco.business.ArchivosSolicitudDTO;
/*      */ import sinco.business.AtencionSolicitudDTO;
/*      */ import sinco.business.CaracteristicasDTO;
/*      */ import sinco.business.DetalleSolicitudDTO;
/*      */ import sinco.business.EstadoDTO;
/*      */ import sinco.business.HistoriaSolicitudDTO;
/*      */ import sinco.business.RespuestaBD;
/*      */ import sinco.business.ServiciosDTO;
/*      */ import sinco.business.SisUsuariosDTO;
/*      */ import sinco.business.SolicitudDTO;
/*      */ import sinco.business.TGeneralDTO;
/*      */ import sinco.business.TipoCalificacionDTO;
/*      */ import sinco.business.Utilidades;
/*      */ import sinco.business.VSolicitudesDTO;
/*      */ import sinco.data.AplazamientosSolicitudDAO;
/*      */ import sinco.data.ArchivosSolicitudDAO;
/*      */ import sinco.data.AtencionSolicitudDAO;
/*      */ import sinco.data.CaracteristicasDAO;
/*      */ import sinco.data.DetalleSolicitudDAO;
/*      */ import sinco.data.EstadoDAO;
/*      */ import sinco.data.HistoriaSolicitudDAO;
/*      */ import sinco.data.SeguridadDAO;
/*      */ import sinco.data.ServiciosDAO;
/*      */ import sinco.data.SisUsuariosDAO;
/*      */ import sinco.data.SolicitudDAO;
/*      */ import sinco.data.TGeneralDAO;
/*      */ import sinco.data.TipoCalificacionDAO;
/*      */ import sinco.data.VSolicitudesDAO;
/*      */ import sinco.presentation.VSEnCurso;
/*      */ import sinco.presentation.VSEnCursoHTML;
/*      */ import sinco.spec.MenuDO;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class VSEnCurso
/*      */   implements HttpPresentation
/*      */ {
/*      */   private VSEnCursoHTML pagHTML;
/*      */   
/*      */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*   62 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*   63 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*      */     }
/*   65 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*   66 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*      */ 
/*      */     
/*   69 */     VSolicitudesDAO sf = new VSolicitudesDAO();
/*   70 */     int idsol = Integer.parseInt(comms.request.getParameter("solicitud"));
/*      */     
/*   72 */     int lectura = 0;
/*      */     try {
/*   74 */       lectura = Integer.parseInt(comms.request.getParameter("lectura"));
/*      */     }
/*   76 */     catch (Exception e) {}
/*      */     
/*   78 */     VSolicitudesDTO regSol = sf.getSolicitud(idsol);
/*   79 */     sf.close();
/*      */     
/*   81 */     if (regSol == null) {
/*   82 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=SolicitudAnulada"));
/*      */     }
/*      */     
/*   85 */     SisUsuariosDAO perf = new SisUsuariosDAO();
/*   86 */     SisUsuariosDTO elNavegante = perf.cargarRegistro(idNav);
/*   87 */     SisUsuariosDTO elProveedor = perf.cargarRegistro(regSol.getEmpleadoProveedor());
/*      */     
/*   89 */     ServiciosDAO serf = new ServiciosDAO();
/*   90 */     ServiciosDTO regServicio = serf.cargarRegistro(regSol.getCodigoServicio());
/*   91 */     serf.close();
/*      */     
/*   93 */     EstadoDAO estadof = new EstadoDAO();
/*   94 */     EstadoDTO estado = estadof.getEstado(regSol.getCodigoEstado());
/*   95 */     estadof.close();
/*      */     
/*   97 */     String nombreEstado = "";
/*   98 */     if (elProveedor.getEstado().equals("I")) {
/*   99 */       nombreEstado = "(Retirado)";
/*      */     }
/*  101 */     else if (elProveedor.getEstado().equals("T")) {
/*  102 */       nombreEstado = "(Temporalmente Inactivo)";
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  109 */     this.pagHTML = (VSEnCursoHTML)comms.xmlcFactory.create(VSEnCursoHTML.class);
/*      */     
/*  111 */     this.pagHTML.setTextNumerosolicitud("" + regSol.getNumeroMostrar());
/*      */     
/*  113 */     this.pagHTML.setTextServicio(regSol.getNombreServicio());
/*  114 */     this.pagHTML.setTextAreap(regSol.getNombreAreaProveedora());
/*  115 */     this.pagHTML.setTextAreacliente(regSol.getNombreAreaCliente());
/*  116 */     this.pagHTML.setTextProveedor(regSol.getNombreProveedor() + " " + nombreEstado);
/*  117 */     this.pagHTML.setTextCliente(regSol.getNombreCliente());
/*      */ 
/*      */     
/*  120 */     this.pagHTML.setTextFechavigencia(regSol.getFechaVigencia());
/*  121 */     this.pagHTML.setTextFechaestimada(regSol.getFechaEstimadaTerminacion());
/*  122 */     this.pagHTML.setTextFechaEscalamientos(regSol.getFechaBaseEscalamientos());
/*  123 */     this.pagHTML.setTextFechaRealTerminacion(regSol.getFechaRealTerminacion());
/*  124 */     this.pagHTML.setTextEstado(estado.getDescripcion());
/*  125 */     this.pagHTML.setTextEscalamiento("" + regSol.getNivelEscalamiento());
/*  126 */     this.pagHTML.setTextFechaGenerada(regSol.getFechaGenerada());
/*      */     
/*  128 */     this.pagHTML.setTextUnidad("" + regSol.getDuracion() + " " + regSol.getUnidadMedida());
/*  129 */     this.pagHTML.setTextTiempoServicio("" + regSol.getTiempoServicio());
/*  130 */     this.pagHTML.setTextMensaje("" + regServicio.getMensaje());
/*      */     
/*  132 */     this.pagHTML.getElementIdsolicitud().setValue("" + regSol.getNumero());
/*  133 */     this.pagHTML.getElementIdsolicitudcal().setValue("" + regSol.getNumero());
/*  134 */     this.pagHTML.getElementIdsolicitudmail().setValue("" + regSol.getNumero());
/*  135 */     this.pagHTML.getElementIdSolicitudModificar().setValue("" + regSol.getNumero());
/*  136 */     this.pagHTML.getElementIdSolicitudModificar().setValue("" + regSol.getNumero());
/*  137 */     this.pagHTML.getElementPagina_siguiente().setValue("VSEnCurso.po");
/*  138 */     this.pagHTML.getElementPagina_siguienteF().setValue("VSEnCurso.po");
/*  139 */     this.pagHTML.getElementSolicitudatencion().setValue("" + regSol.getNumero());
/*  140 */     this.pagHTML.getElementIdSolicitudFortelezas().setValue("" + regSol.getNumero());
/*  141 */     this.pagHTML.getElementPagina_siguienteAtencion().setValue("VSEnCurso.po");
/*  142 */     this.pagHTML.getElementPagina_siguienteArc().setValue("VSEnCurso.po");
/*  143 */     this.pagHTML.getElementIdSolicitudArchivos().setValue("" + regSol.getNumero());
/*  144 */     this.pagHTML.getElementIdSolicitudMejora().setValue("" + regSol.getNumero());
/*  145 */     this.pagHTML.getElementLlave().setValue("" + regSol.getNumero());
/*  146 */     this.pagHTML.getElementSolicitudApla().setValue("" + regSol.getNumero());
/*      */     
/*  148 */     SeguridadDAO rsSeguridad = new SeguridadDAO();
/*  149 */     if (!rsSeguridad.getEstadoConexion()) {
/*  150 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */     }
/*  152 */     boolean oCalificarSolicitudes = rsSeguridad.tieneLlave(miGrupo, "oCalificarSolicitudes");
/*  153 */     boolean oCalificarCualquiera = rsSeguridad.tieneLlave(miGrupo, "oCalificarDeOrganizacion");
/*  154 */     boolean oCalificarFortalezas = rsSeguridad.tieneLlave(miGrupo, "oCalificarFortalezas");
/*  155 */     boolean oVerDetalleCalificacion = rsSeguridad.tieneLlave(miGrupo, "oAudVerDetalleCalificacion");
/*  156 */     boolean permisoVerBitacora = rsSeguridad.tieneLlave(miGrupo, "oVerBitacora");
/*  157 */     rsSeguridad.close();
/*      */ 
/*      */     
/*  160 */     if (!permisoVerBitacora) {
/*  161 */       HTMLElement eltr = this.pagHTML.getElementTrBitacora();
/*  162 */       eltr.getParentNode().removeChild(eltr);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  169 */     boolean quitarPadre = true;
/*  170 */     if (regSol.getSolicitudPadre() >= 1) {
/*  171 */       SolicitudDAO sf2 = new SolicitudDAO();
/*  172 */       SolicitudDTO regSolPadre = sf2.getSolicitud(regSol.getSolicitudPadre());
/*  173 */       sf2.close();
/*      */       
/*  175 */       if (regSolPadre != null) {
/*  176 */         this.pagHTML.getElementIdSolicitudPadre().setValue("" + regSol.getSolicitudPadre());
/*      */         
/*  178 */         if (regSolPadre.getEmpleadoProveedor() == idNav) {
/*  179 */           HTMLFormElement forma = this.pagHTML.getElementFormaPadre();
/*  180 */           forma.setAction("VSPorAt.po");
/*      */         } 
/*  182 */         quitarPadre = false;
/*      */       } 
/*      */     } 
/*      */     
/*  186 */     if (quitarPadre) {
/*  187 */       Element divPadre = this.pagHTML.getElementDivSolicitudPadre();
/*  188 */       divPadre.getParentNode().removeChild(divPadre);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  193 */     int aceptadas = caracteristicas(comms, regSol.getNumero(), regSol.getCodigoServicio(), regSol.getNombreAreaCliente(), idNav, regSol.getEmpleadoProveedor());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  201 */     atencionesSolicitud(comms, regSol);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  206 */     boolean hay = false;
/*  207 */     int cuantasSinCerrar = solicitudesHijas(comms, regSol.getNumero(), regSol.getNumeroFlujo(), idNav, oVerDetalleCalificacion);
/*      */ 
/*      */     
/*  210 */     historiaSolicitud(comms, regSol.getNumero());
/*      */     
/*  212 */     hay = false;
/*      */ 
/*      */     
/*  215 */     if (!hay) {
/*  216 */       Element divAuditorCompa = this.pagHTML.getElementAuditorCompa();
/*  217 */       divAuditorCompa.getParentNode().removeChild(divAuditorCompa);
/*      */     } 
/*      */     
/*  220 */     archivosAnexos(regSol.getNumero(), comms.session.getUser().getName());
/*      */ 
/*      */ 
/*      */     
/*  224 */     aplazamientosSolicitud(comms, regSol.getNumero());
/*      */     
/*  226 */     calicacionSolicitud(regSol.getCodigoOportunidad(), regSol.getCodigoConfiabilidad(), regServicio.isCalificarServicio());
/*      */ 
/*      */     
/*  229 */     if (aceptadas == 0 || !oCalificarFortalezas || lectura == 1 || !estado.getTipoEstado().trim().equals("CAL") || elNavegante.getAuditorCordinador().equals("N")) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  236 */       Element divatencion = this.pagHTML.getElementIdAceptarFortalezas();
/*  237 */       divatencion.getParentNode().removeChild(divatencion);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  257 */     if (lectura == 1 || estado.getTipoEstado().trim().equals("EF") || estado.getTipoEstado().trim().equals("NCO") || estado.getTipoEstado().trim().equals("AN")) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  263 */       Element divatencion = this.pagHTML.getElementNuevaatencion();
/*  264 */       divatencion.getParentNode().removeChild(divatencion);
/*      */       
/*  266 */       Element idmodi = this.pagHTML.getElementIdModCaracteristica();
/*  267 */       idmodi.getParentNode().removeChild(idmodi);
/*      */     }
/*  269 */     else if (!regServicio.getTipoServicio().equals(Integer.toString(2)) || regSol.getCiclo().length() <= 0 || oCalificarFortalezas != true) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  275 */       if (estado.getTipoEstado().equals("CAL") || !regSol.getAbierta()) {
/*  276 */         Element idmodi = this.pagHTML.getElementIdModCaracteristica();
/*  277 */         idmodi.getParentNode().removeChild(idmodi);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  282 */     cambiosDeEstado(estado, regSol.getEmpleadoCliente(), idNav);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  292 */     calificarSolicitud(regSol, regServicio, estado, idNav, oCalificarSolicitudes, oCalificarCualquiera, oCalificarFortalezas, elNavegante.getArea(), cuantasSinCerrar, elProveedor.getEstado());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  307 */     if (!estado.getTipoEstado().trim().equals("DV")) {
/*  308 */       Element copi = this.pagHTML.getElementCopiar();
/*  309 */       copi.getParentNode().removeChild(copi);
/*      */     } else {
/*      */       
/*  312 */       this.pagHTML.setTextTitulosolicitud("SOLICITUD DEVUELTA");
/*  313 */       this.pagHTML.getElementServiciocp().setValue("" + regSol.getCodigoServicio());
/*  314 */       this.pagHTML.getElementAreacp().setValue("" + regSol.getAreaProveedor());
/*  315 */       this.pagHTML.getElementPadre().setValue("" + regSol.getNumero());
/*  316 */       this.pagHTML.getElementPersonaProv().setValue("" + regSol.getEmpleadoProveedor());
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  321 */     if (lectura == 1 || estado.getTipoEstado().trim().equals("DV") || estado.getTipoEstado().trim().equals("AN") || estado.getTipoEstado().trim().equals("NAT") || estado.getTipoEstado().trim().equals("NCO")) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  327 */       Element idmodi = this.pagHTML.getElementIdMasArchivos();
/*  328 */       idmodi.getParentNode().removeChild(idmodi);
/*      */     } 
/*      */     
/*  331 */     if (!regServicio.getTipoServicio().equals(Integer.toString(2))) {
/*  332 */       Element unElemento = this.pagHTML.getElementTdAcciones();
/*  333 */       unElemento.getParentNode().removeChild(unElemento);
/*      */     } 
/*      */     
/*  336 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  337 */     comms.response.writeDOM(this.pagHTML);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void calificarSolicitud(VSolicitudesDTO regSol, ServiciosDTO regServicio, EstadoDTO estado, int idNav, boolean oCalificarSolicitudes, boolean oCalificarCualquiera, boolean oCalificarFortalezas, int areaNavegante, int cuantasSinCerrar, String estadoProveedor) {
/*  363 */     boolean quitarOpcion = false;
/*      */     
/*  365 */     if (regServicio.isCalificarServicio() == true && estado.getTipoEstado().equals("CAL") && (idNav == regSol.getEmpleadoCliente() || (oCalificarSolicitudes && areaNavegante == regSol.getAreaCliente()) || oCalificarCualquiera)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  373 */       this.pagHTML.setTextTitulosolicitud("SOLICITUD POR CALIFICAR");
/*  374 */       HTMLSelectElement calsel = this.pagHTML.getElementConfiabilidades();
/*      */       
/*  376 */       HTMLOptionElement toe = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  377 */       toe.setValue("X");
/*  378 */       toe.appendChild(this.pagHTML.createTextNode(" "));
/*  379 */       calsel.appendChild(toe);
/*      */       
/*  381 */       int numeroDevoluciones = 0;
/*      */       
/*  383 */       if (estadoProveedor.equals("I") || estadoProveedor.equals("T")) {
/*  384 */         numeroDevoluciones = 100;
/*      */       }
/*      */       
/*  387 */       TipoCalificacionDAO tcf = new TipoCalificacionDAO();
/*      */       
/*  389 */       tcf.cargarTodos(regSol.getNumeroDevoluciones() + numeroDevoluciones);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  394 */       TipoCalificacionDTO tipocal = tcf.next();
/*  395 */       while (tipocal != null) {
/*      */         
/*  397 */         toe = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  398 */         toe.setValue("" + tipocal.getCodigo());
/*  399 */         toe.appendChild(this.pagHTML.createTextNode(tipocal.getDescripcion()));
/*  400 */         calsel.appendChild(toe);
/*  401 */         tipocal = tcf.next();
/*      */       } 
/*  403 */       tcf.close();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  428 */       quitarOpcion = true;
/*      */     } 
/*      */     
/*  431 */     if (quitarOpcion || cuantasSinCerrar > 0) {
/*  432 */       Element cali = this.pagHTML.getElementCalificar();
/*  433 */       cali.getParentNode().removeChild(cali);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void cambiosDeEstado(EstadoDTO estado, int empleadoCliente, int idNav) {
/*  456 */     EstadoDAO rsEstado = new EstadoDAO();
/*  457 */     rsEstado.cargarTodosSiguientesParaEstadoParaCliente(estado.getCodigo());
/*  458 */     EstadoDTO regEstado = rsEstado.next();
/*  459 */     HTMLSelectElement hse = this.pagHTML.getElementEstados();
/*      */     
/*  461 */     HTMLOptionElement hoe = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  462 */     hoe.setValue("0");
/*  463 */     hoe.appendChild(this.pagHTML.createTextNode(" "));
/*  464 */     hse.appendChild(hoe);
/*      */     
/*  466 */     boolean hayestados = false;
/*  467 */     while (regEstado != null) {
/*  468 */       hayestados = true;
/*  469 */       hoe = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  470 */       hoe.setValue("" + regEstado.getCodigo());
/*  471 */       hoe.appendChild(this.pagHTML.createTextNode(regEstado.getDescripcion()));
/*  472 */       hse.appendChild(hoe);
/*  473 */       regEstado = rsEstado.next();
/*      */     } 
/*  475 */     rsEstado.close();
/*      */ 
/*      */     
/*  478 */     if ((estado.getTipoEstado().trim().equals("INI") && idNav != empleadoCliente) || !hayestados || estado.getTipoEstado().trim().equals("CAL")) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  483 */       Element ce = this.pagHTML.getElementCambiarestado();
/*  484 */       ce.getParentNode().removeChild(ce);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void archivosAnexos(int numeroSolicitud, String elUsuario) {
/*  496 */     HTMLTableElement tabla = this.pagHTML.getElementIdArchivos();
/*  497 */     ArchivosSolicitudDAO rs = new ArchivosSolicitudDAO();
/*  498 */     rs.getArchivosSolicitud(numeroSolicitud);
/*  499 */     ArchivosSolicitudDTO reg = rs.next();
/*      */     
/*  501 */     boolean hay = false;
/*  502 */     boolean fondo = true;
/*  503 */     while (reg != null) {
/*  504 */       hay = true;
/*      */       
/*  506 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  507 */       fondo = !fondo;
/*  508 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*      */       
/*  510 */       if (reg.getEstado().equals("B")) {
/*  511 */         eltr.appendChild(newtd("Borrado"));
/*      */       }
/*  513 */       else if (reg.getUsuarioInsercion().equals(elUsuario)) {
/*  514 */         HTMLElement tdMarca = (HTMLElement)this.pagHTML.createElement("td");
/*      */         
/*  516 */         HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
/*  517 */         checkbox.setAttribute("type", "checkbox");
/*      */         
/*  519 */         checkbox.setName("" + reg.getConsecutivo());
/*  520 */         tdMarca.appendChild(checkbox);
/*      */         
/*  522 */         eltr.appendChild(tdMarca);
/*      */       } else {
/*      */         
/*  525 */         eltr.appendChild(newtd(""));
/*      */       } 
/*      */ 
/*      */       
/*  529 */       if (reg.getEstado().equals("B")) {
/*  530 */         eltr.appendChild(newtd(reg.getArchivo()));
/*      */       } else {
/*      */         
/*  533 */         eltr.appendChild(newtdhref(reg.getArchivo(), "VerArchivo.po?origen=C&archivo=" + reg.getArchivo() + "&fecha=" + Utilidades.darFormatoFecha(reg.getFechaInsercion()), true));
/*      */       } 
/*      */       
/*  536 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaInsercion())));
/*  537 */       eltr.appendChild(newtd("" + reg.getUsuarioInsercion()));
/*      */       
/*  539 */       tabla.appendChild(eltr);
/*  540 */       reg = rs.next();
/*      */     } 
/*  542 */     rs.close();
/*      */ 
/*      */     
/*  545 */     if (!hay) {
/*  546 */       Element divArchivos = this.pagHTML.getElementIdmostrararchivos();
/*  547 */       divArchivos.getParentNode().removeChild(divArchivos);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Attr newAttr(String tipo, String valor) {
/*  559 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/*  560 */     atrib.setValue(valor);
/*  561 */     return atrib;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private HTMLElement newtd(String contenido) {
/*  573 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  574 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  575 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  576 */     return td;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private HTMLElement newtdhref(String contenido, String vinculo) {
/*  585 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  586 */     Element enlace = this.pagHTML.createElement("a");
/*  587 */     Node hijo = this.pagHTML.createTextNode(contenido);
/*  588 */     enlace.appendChild(hijo);
/*  589 */     Attr donde = this.pagHTML.createAttribute("href");
/*  590 */     donde.setValue(vinculo);
/*  591 */     enlace.setAttributeNode(donde);
/*  592 */     td.appendChild(enlace);
/*  593 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  594 */     return td;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private HTMLElement newtdhref(String contenido, String vinculo, boolean nuevaVentana) {
/*  605 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  606 */     Element enlace = this.pagHTML.createElement("a");
/*  607 */     Node hijo = this.pagHTML.createTextNode(contenido);
/*  608 */     enlace.appendChild(hijo);
/*  609 */     Attr donde = this.pagHTML.createAttribute("href");
/*  610 */     donde.setValue(vinculo);
/*  611 */     enlace.setAttributeNode(donde);
/*  612 */     if (nuevaVentana) {
/*  613 */       enlace.setAttributeNode(newAttr("TARGET", "_blank"));
/*      */     }
/*  615 */     td.appendChild(enlace);
/*  616 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  617 */     return td;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int caracteristicas(HttpPresentationComms comms, int numeroSolicitud, int codigoServicio, String nombreAreaCliente, int idNav, int codigoProveedor) throws HttpPresentationException, KeywordValueException {
/*  642 */     HTMLTableElement tabla = this.pagHTML.getElementCaracteristicas();
/*  643 */     CaracteristicasDAO cf = new CaracteristicasDAO();
/*  644 */     Collection arr = cf.cargarTodosParaServicio(codigoServicio, "T", "T");
/*  645 */     cf.close();
/*      */ 
/*      */     
/*  648 */     boolean hay = false;
/*  649 */     int aceptadas = 0;
/*  650 */     DetalleSolicitudDAO dsf = new DetalleSolicitudDAO();
/*      */     
/*  652 */     boolean fondo = false;
/*  653 */     int valorMostrar = 0;
/*      */     
/*  655 */     Iterator iterator = arr.iterator();
/*  656 */     while (iterator.hasNext()) {
/*  657 */       CaracteristicasDTO regCar = (CaracteristicasDTO)iterator.next();
/*      */       
/*  659 */       hay = true;
/*  660 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*      */       
/*  662 */       fondo = !fondo;
/*  663 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*      */       
/*  665 */       eltr.appendChild(newtd((regCar.getRol().equals("P") ? "P* " : "   ") + regCar.getDescripcion()));
/*      */       
/*  667 */       dsf.cargarParaSolicitud(numeroSolicitud, regCar.getCodigo());
/*  668 */       DetalleSolicitudDTO ds = dsf.next();
/*      */       
/*  670 */       String descripcion = "";
/*  671 */       String sCodigoDocumento = "";
/*  672 */       while (ds != null) {
/*  673 */         descripcion = descripcion + ds.getObservacion() + " ";
/*  674 */         sCodigoDocumento = ds.getObservacion();
/*      */         
/*  676 */         if (regCar.getNombreProcedimiento().length() > 0) {
/*  677 */           descripcion = descripcion + ejecutarProcedimiento(numeroSolicitud, regCar.getCodigo(), ds.getObservacion(), regCar.getNombreProcedimiento(), regCar.getCaracteristicaAnida());
/*      */         }
/*      */         
/*  680 */         if (regCar.getTipo().equals("2") && 
/*  681 */           regCar.getCuantasDependen() > 0) {
/*  682 */           valorMostrar = ds.getValor();
/*      */         }
/*      */ 
/*      */         
/*  686 */         ds = dsf.next();
/*      */       } 
/*      */       
/*  689 */       if (regCar.getEstado().equals("I") && descripcion.length() == 0) {
/*      */         continue;
/*      */       }
/*      */       
/*  693 */       if (regCar.getTipo().equals("6")) {
/*  694 */         TGeneralDAO rsGen = new TGeneralDAO();
/*  695 */         TGeneralDTO regDoc = rsGen.cargarDocumento(sCodigoDocumento);
/*  696 */         rsGen.close();
/*  697 */         if (regDoc != null) {
/*      */           
/*  699 */           String url = "VerArchivo.po?ruta=cal_archivos&archivo=" + regDoc.getDescripcion();
/*  700 */           if (idNav == codigoProveedor) {
/*  701 */             url = "VerArchivo.po?ruta=cal_archivos&archivo=" + regDoc.getCodigo();
/*      */           }
/*  703 */           eltr.appendChild(newtdhref(descripcion, url, true));
/*      */         } else {
/*      */           
/*  706 */           eltr.appendChild(newtd(descripcion));
/*      */         } 
/*      */       } else {
/*      */         
/*  710 */         eltr.appendChild(newtd(descripcion));
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  736 */       if (regCar.getCaracteristicaDepende() > 0) {
/*  737 */         if (valorMostrar != regCar.getValorDepende()) {
/*  738 */           eltr.setAttributeNode(newAttr("style", "display:none"));
/*      */         }
/*  740 */         eltr.setAttributeNode(newAttr("depende", "" + regCar.getCaracteristicaDepende()));
/*  741 */         eltr.setAttributeNode(newAttr("valorDepende", "" + regCar.getValorDepende()));
/*      */       } 
/*      */ 
/*      */       
/*  745 */       tabla.appendChild(eltr);
/*      */     } 
/*      */     
/*  748 */     dsf.close();
/*      */     
/*  750 */     arr.clear();
/*      */ 
/*      */     
/*  753 */     if (!hay) {
/*  754 */       Element divcaracteristicas = this.pagHTML.getElementMostrarcaracteristicas();
/*  755 */       divcaracteristicas.getParentNode().removeChild(divcaracteristicas);
/*      */     } 
/*      */     
/*  758 */     if (aceptadas == 0) {
/*  759 */       Element divcaracteristicas = this.pagHTML.getElementCalificacion();
/*  760 */       divcaracteristicas.getParentNode().removeChild(divcaracteristicas);
/*      */     } 
/*  762 */     return aceptadas;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void atencionesSolicitud(HttpPresentationComms comms, VSolicitudesDTO regSol) throws HttpPresentationException, KeywordValueException {
/*  782 */     HTMLTableElement tablaat = this.pagHTML.getElementAtencion();
/*  783 */     AtencionSolicitudDAO rs = new AtencionSolicitudDAO();
/*      */     
/*  785 */     if (!rs.getEstadoConexion()) {
/*  786 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */     }
/*      */     
/*  789 */     rs.cargarTodosParaSolicitud(regSol.getNumero());
/*  790 */     AtencionSolicitudDTO reg = rs.next();
/*  791 */     boolean hay = false;
/*      */ 
/*      */     
/*  794 */     boolean fondo = false;
/*  795 */     while (reg != null) {
/*  796 */       hay = true;
/*  797 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*      */       
/*  799 */       fondo = !fondo;
/*  800 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*      */       
/*  802 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFecha())));
/*      */       
/*      */       try {
/*  805 */         if (reg.getPersonaAtendio() != 0) {
/*  806 */           if (regSol.getEmpleadoCliente() == reg.getPersonaAtendio()) {
/*  807 */             eltr.appendChild(newtd("" + regSol.getNombreCliente()));
/*      */           }
/*  809 */           else if (regSol.getEmpleadoProveedor() == reg.getPersonaAtendio()) {
/*  810 */             eltr.appendChild(newtd("" + regSol.getNombreProveedor()));
/*      */           } else {
/*  812 */             SisUsuariosDAO perf = new SisUsuariosDAO();
/*  813 */             SisUsuariosDTO otraPersona = perf.cargarRegistro(reg.getPersonaAtendio());
/*  814 */             eltr.appendChild(newtd(("" + otraPersona != null) ? otraPersona.getNombre() : "Desconocido"));
/*      */           } 
/*      */         } else {
/*      */           
/*  818 */           eltr.appendChild(newtd("Desconocido"));
/*      */         }
/*      */       
/*  821 */       } catch (Exception e) {
/*  822 */         eltr.appendChild(newtd("Desconocido"));
/*      */       } 
/*  824 */       eltr.appendChild(newtd(reg.getObservacion()));
/*  825 */       tablaat.appendChild(eltr);
/*  826 */       reg = rs.next();
/*      */     } 
/*  828 */     rs.close();
/*      */     
/*  830 */     if (!hay) {
/*  831 */       tablaat.getParentNode().removeChild(tablaat);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int solicitudesHijas(HttpPresentationComms comms, int numeroSolicitud, int numeroFlujo, int idNav, boolean oVerDetalleCalificacion) throws HttpPresentationException, KeywordValueException {
/*  848 */     VSolicitudesDAO rsSF = new VSolicitudesDAO();
/*  849 */     Collection<VSolicitudesDTO> arr = rsSF.cargarHijas(numeroSolicitud, numeroFlujo);
/*  850 */     rsSF.close();
/*      */     
/*  852 */     HTMLTableElement hte = this.pagHTML.getElementDetalleTramite();
/*      */     
/*  854 */     boolean fondo = true;
/*  855 */     boolean hay = false;
/*  856 */     int cuantasSinCerrar = 0;
/*      */     
/*  858 */     Iterator iterator = arr.iterator();
/*  859 */     while (iterator.hasNext()) {
/*      */       
/*  861 */       VSolicitudesDTO regSolHijas = (VSolicitudesDTO)iterator.next();
/*  862 */       hay = true;
/*  863 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*      */       
/*  865 */       fondo = !fondo;
/*  866 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*      */       
/*  868 */       eltr.appendChild(newtd("" + regSolHijas.getNombreAreaCliente()));
/*  869 */       eltr.appendChild(newtd("" + regSolHijas.getNombreCliente()));
/*  870 */       eltr.appendChild(newtd("" + regSolHijas.getNombreAreaProveedora()));
/*  871 */       eltr.appendChild(newtd("" + regSolHijas.getNombreProveedor()));
/*      */       
/*  873 */       String sPagina = "";
/*      */       
/*  875 */       if (regSolHijas.getEmpleadoCliente() == idNav && regSolHijas.getAreaProveedor() == 0) {
/*  876 */         sPagina = "SolicitudPendProveedor.po?_operacion=P&solicitud=" + regSolHijas.getNumero();
/*  877 */         eltr.appendChild(newtdhref("" + regSolHijas.getNombreServicio(), sPagina));
/*      */       }
/*  879 */       else if (regSolHijas.getEmpleadoProveedor() == idNav && (regSolHijas.getTipoEstado().equals("PRV") || regSolHijas.getTipoEstado().equals("ESC"))) {
/*  880 */         sPagina = "VSPorAt.po?solicitud=" + regSolHijas.getNumero();
/*  881 */         eltr.appendChild(newtdhref("" + regSolHijas.getNombreServicio(), sPagina));
/*      */       
/*      */       }
/*  884 */       else if (!oVerDetalleCalificacion && regSolHijas.getTipoServicio() == 3) {
/*  885 */         eltr.appendChild(newtd("" + regSolHijas.getNombreServicio()));
/*      */       } else {
/*      */         
/*  888 */         sPagina = "VSEnCurso.po?solicitud=" + regSolHijas.getNumero() + "&lectura=1";
/*  889 */         eltr.appendChild(newtdhref("" + regSolHijas.getNombreServicio(), sPagina));
/*      */       } 
/*      */       
/*  892 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(regSolHijas.getFechaEstimadaTerminacion())));
/*  893 */       eltr.appendChild(newtd("" + regSolHijas.getNombreEstado()));
/*  894 */       hte.appendChild(eltr);
/*      */       
/*  896 */       if (regSolHijas.getTipoEstado().equals("PRV") || regSolHijas.getTipoEstado().equals("ESC")) {
/*  897 */         cuantasSinCerrar++;
/*      */       }
/*      */     } 
/*  900 */     if (!hay) {
/*  901 */       Element ce = this.pagHTML.getElementSolicitudesTramite();
/*  902 */       ce.getParentNode().removeChild(ce);
/*      */     } 
/*  904 */     return cuantasSinCerrar;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void historiaSolicitud(HttpPresentationComms comms, int numeroSolicitud) throws HttpPresentationException, KeywordValueException {
/*  923 */     boolean fondo = false;
/*      */     
/*  925 */     HTMLTableElement tablahistoria = this.pagHTML.getElementHistoria();
/*      */     
/*  927 */     HistoriaSolicitudDAO rs = new HistoriaSolicitudDAO();
/*      */     
/*  929 */     if (!rs.getEstadoConexion()) {
/*  930 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */     }
/*      */     
/*  933 */     rs.cargarTodosParaSolicitud(numeroSolicitud);
/*  934 */     HistoriaSolicitudDTO reg = rs.next();
/*      */     
/*  936 */     EstadoDAO rsEstado = new EstadoDAO();
/*      */     
/*  938 */     while (reg != null) {
/*  939 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*      */       
/*  941 */       fondo = !fondo;
/*  942 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*      */       
/*  944 */       EstadoDTO regEstado = rsEstado.getEstado(reg.getEstadoFinal());
/*  945 */       eltr.appendChild(newtd("" + ((regEstado != null) ? regEstado.getDescripcion() : ("" + reg.getEstadoFinal()))));
/*  946 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFecha())));
/*  947 */       eltr.appendChild(newtd("" + reg.getObservaciones()));
/*  948 */       tablahistoria.appendChild(eltr);
/*  949 */       reg = rs.next();
/*      */     } 
/*  951 */     rs.close();
/*  952 */     rsEstado.close();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void aplazamientosSolicitud(HttpPresentationComms comms, int numeroSolicitud) throws HttpPresentationException, KeywordValueException {
/*  971 */     AplazamientosSolicitudDAO rs = new AplazamientosSolicitudDAO();
/*  972 */     if (!rs.getEstadoConexion()) {
/*  973 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */     }
/*  975 */     Collection arr = rs.cargarTodos(numeroSolicitud);
/*  976 */     rs.close();
/*      */     
/*  978 */     Iterator iterator = arr.iterator();
/*  979 */     boolean fondo = false;
/*      */     
/*  981 */     int cuantas = 0;
/*      */     
/*  983 */     HTMLTableSectionElement tabla = this.pagHTML.getElementTblAplazamientos();
/*  984 */     while (iterator.hasNext()) {
/*      */       HTMLElement tdJustificacion; HTMLInputElement inp; HTMLElement tdDescripcion; HTMLOptionElement op; Attr escogida; HTMLOptionElement opv; HTMLSelectElement combo;
/*  986 */       AplazamientosSolicitudDTO reg = (AplazamientosSolicitudDTO)iterator.next();
/*  987 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  988 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*      */       
/*  990 */       eltr.appendChild(newtd("" + reg.getJustificacion()));
/*  991 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFecha())));
/*      */ 
/*      */       
/*  994 */       int estadoA = reg.getEstado();
/*  995 */       switch (estadoA) {
/*      */         case 0:
/*  997 */           eltr.appendChild(newtd("POR ATENDER"));
/*  998 */           eltr.appendChild(newtd(""));
/*      */           
/* 1000 */           combo = (HTMLSelectElement)this.pagHTML.createElement("Select");
/*      */           
/* 1002 */           combo.setAttributeNode(newAttr("onkeyDown", "return f_salto2(event)"));
/* 1003 */           combo.setAttributeNode(newAttr("onkeyUp", "return f_salto(event)"));
/*      */           
/* 1005 */           combo.setName("APL_" + reg.getConsecutivo() + "_" + Utilidades.darFormatoFecha(reg.getFecha()));
/*      */           
/* 1007 */           opv = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1008 */           opv.setValue("");
/* 1009 */           opv.appendChild(this.pagHTML.createTextNode("Seleccione Una Opción"));
/*      */           
/* 1011 */           escogida = this.pagHTML.createAttribute("selected");
/* 1012 */           escogida.setValue("on");
/* 1013 */           opv.setAttributeNode(escogida);
/* 1014 */           combo.appendChild(opv);
/*      */           
/* 1016 */           op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1017 */           op.setValue("S");
/* 1018 */           op.appendChild(this.pagHTML.createTextNode("ACEPTAR"));
/* 1019 */           combo.appendChild(op);
/*      */           
/* 1021 */           op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1022 */           op.setValue("N");
/* 1023 */           op.appendChild(this.pagHTML.createTextNode("NEGAR"));
/* 1024 */           combo.appendChild(op);
/*      */           
/* 1026 */           tdDescripcion = (HTMLElement)this.pagHTML.createElement("td");
/* 1027 */           tdDescripcion.appendChild(combo);
/* 1028 */           eltr.appendChild(tdDescripcion);
/*      */ 
/*      */           
/* 1031 */           inp = (HTMLInputElement)this.pagHTML.createElement("input");
/* 1032 */           inp.setMaxLength(255);
/* 1033 */           inp.setSize("70");
/*      */           
/* 1035 */           inp.setName("JUS_" + reg.getConsecutivo());
/* 1036 */           inp.setId("JUS_" + reg.getConsecutivo());
/*      */           
/* 1038 */           inp.setAttributeNode(newAttr("onkeyDown", "return f_salto2(event)"));
/* 1039 */           inp.setAttributeNode(newAttr("onkeyUp", "return f_salto(event)"));
/*      */           
/* 1041 */           tdJustificacion = (HTMLElement)this.pagHTML.createElement("td");
/* 1042 */           tdJustificacion.appendChild(inp);
/* 1043 */           eltr.appendChild(tdJustificacion);
/* 1044 */           cuantas++;
/*      */           break;
/*      */         
/*      */         case 1:
/* 1048 */           eltr.appendChild(newtd("ACEPTADA"));
/* 1049 */           eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaestado())));
/* 1050 */           eltr.appendChild(newtd(""));
/* 1051 */           eltr.appendChild(newtd(""));
/*      */           break;
/*      */         case 2:
/* 1054 */           eltr.appendChild(newtd("RECHAZADA"));
/* 1055 */           eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaestado())));
/* 1056 */           eltr.appendChild(newtd(""));
/* 1057 */           eltr.appendChild(newtd("" + reg.getJustificacionNega()));
/*      */           break;
/*      */         case 3:
/* 1060 */           eltr.appendChild(newtd("ANULADO"));
/* 1061 */           eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaestado())));
/* 1062 */           eltr.appendChild(newtd(""));
/* 1063 */           eltr.appendChild(newtd(""));
/*      */           break;
/*      */       } 
/*      */       
/* 1067 */       tabla.appendChild(eltr);
/*      */     } 
/* 1069 */     if (cuantas == 0) {
/* 1070 */       Element divAplazamientos = this.pagHTML.getElementTrPie();
/* 1071 */       divAplazamientos.getParentNode().removeChild(divAplazamientos);
/*      */     } 
/*      */ 
/*      */     
/* 1075 */     if (arr.size() == 0) {
/* 1076 */       Element divAplazamientos = this.pagHTML.getElementIdAplazamientos();
/* 1077 */       divAplazamientos.getParentNode().removeChild(divAplazamientos);
/*      */     } 
/* 1079 */     arr.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void calicacionSolicitud(String calificacionOportunidad, String calificacionConfiabilidad, boolean calificaServicio) {
/* 1094 */     if (!calificacionOportunidad.equals("")) {
/* 1095 */       TipoCalificacionDAO tcf = new TipoCalificacionDAO();
/* 1096 */       TipoCalificacionDTO tipocali = tcf.getTipoCalificacion(calificacionOportunidad.charAt(0));
/* 1097 */       this.pagHTML.setTextOportunidad("" + tipocali.getDescripcion());
/* 1098 */       tcf.close();
/*      */     } 
/*      */     
/* 1101 */     if (!calificaServicio) {
/* 1102 */       this.pagHTML.setTextConfiabilidad("No aplica");
/*      */     }
/* 1104 */     else if (!calificacionConfiabilidad.equals("")) {
/* 1105 */       TipoCalificacionDAO tcf = new TipoCalificacionDAO();
/* 1106 */       TipoCalificacionDTO tipocali = tcf.getTipoCalificacion(calificacionConfiabilidad.charAt(0));
/* 1107 */       this.pagHTML.setTextConfiabilidad("" + tipocali.getDescripcion());
/* 1108 */       tcf.close();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String ejecutarProcedimiento(int idsol, int caracteristica, String valor, String proceso, int anidada) {
/*      */     try {
/* 1126 */       CaracteristicasDAO rs = new CaracteristicasDAO();
/* 1127 */       RespuestaBD rta = rs.validarProcedimiento(idsol, caracteristica, valor, proceso, anidada);
/* 1128 */       rs.close();
/*      */       
/* 1130 */       if (rta != null) {
/* 1131 */         return rta.getCausal();
/*      */       }
/*      */     }
/* 1134 */     catch (Exception e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1139 */     return "";
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\VSEnCurso.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */