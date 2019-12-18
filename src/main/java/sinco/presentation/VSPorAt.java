/*      */ package sinco.presentation;
/*      */ 
/*      */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*      */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*      */ import com.lutris.util.KeywordValueException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import org.w3c.dom.Attr;
/*      */ import org.w3c.dom.Element;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.html.HTMLElement;
/*      */ import org.w3c.dom.html.HTMLFormElement;
/*      */ import org.w3c.dom.html.HTMLImageElement;
/*      */ import org.w3c.dom.html.HTMLInputElement;
/*      */ import org.w3c.dom.html.HTMLOptionElement;
/*      */ import org.w3c.dom.html.HTMLSelectElement;
/*      */ import org.w3c.dom.html.HTMLTableElement;
/*      */ import sinco.business.AplazamientosSolicitudDTO;
/*      */ import sinco.business.ArchivosSolicitudDTO;
/*      */ import sinco.business.AreasDTO;
/*      */ import sinco.business.AtencionSolicitudDTO;
/*      */ import sinco.business.CaracteristicasDTO;
/*      */ import sinco.business.CaracteristicasValorDTO;
/*      */ import sinco.business.DetalleSolicitudDTO;
/*      */ import sinco.business.EstadoDTO;
/*      */ import sinco.business.HistoriaSolicitudDTO;
/*      */ import sinco.business.ParametrosDTO;
/*      */ import sinco.business.RespuestaBD;
/*      */ import sinco.business.ServiciosDTO;
/*      */ import sinco.business.SisMultiValoresDTO;
/*      */ import sinco.business.SisUsuariosDTO;
/*      */ import sinco.business.SolicitudDTO;
/*      */ import sinco.business.TGeneralDTO;
/*      */ import sinco.business.TemporalDTO;
/*      */ import sinco.business.TipoCalificacionDTO;
/*      */ import sinco.business.Utilidades;
/*      */ import sinco.business.VSolicitudesDTO;
/*      */ import sinco.data.AplazamientosSolicitudDAO;
/*      */ import sinco.data.ArchivosSolicitudDAO;
/*      */ import sinco.data.AreasDAO;
/*      */ import sinco.data.AtencionSolicitudDAO;
/*      */ import sinco.data.CaracteristicasDAO;
/*      */ import sinco.data.CaracteristicasValorDAO;
/*      */ import sinco.data.DetalleSolicitudDAO;
/*      */ import sinco.data.EstadoDAO;
/*      */ import sinco.data.HistoriaSolicitudDAO;
/*      */ import sinco.data.SeguridadDAO;
/*      */ import sinco.data.ServiciosDAO;
/*      */ import sinco.data.SisMultiValoresDAO;
/*      */ import sinco.data.SisUsuariosDAO;
/*      */ import sinco.data.SolicitudDAO;
/*      */ import sinco.data.TGeneralDAO;
/*      */ import sinco.data.TipoCalificacionDAO;
/*      */ import sinco.data.VSolicitudesDAO;
/*      */ import sinco.presentation.VSPorAt;
/*      */ import sinco.presentation.VSPorAtHTML;
/*      */ import sinco.spec.MenuDO;
/*      */ import sinco.spec.Utilidades2;
/*      */ 
/*      */ 
/*      */ public class VSPorAt
/*      */   implements HttpPresentation
/*      */ {
/*      */   private VSPorAtHTML pagHTML;
/*      */   private boolean tieneTareas = false;
/*      */   
/*      */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*   71 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*   72 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*      */     }
/*   74 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*   75 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*   76 */     String elUsuario = "" + comms.session.getUser().getName();
/*      */     
/*   78 */     comms.session.getSessionData().remove("volverA");
/*      */ 
/*      */     
/*   81 */     VSolicitudesDAO sf = new VSolicitudesDAO();
/*   82 */     int idsol = Integer.parseInt(comms.request.getParameter("solicitud"));
/*   83 */     VSolicitudesDTO regSol = sf.getSolicitud(idsol);
/*   84 */     sf.close();
/*      */     
/*   86 */     if (regSol == null) {
/*   87 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=SolicitudAnulada"));
/*      */     }
/*   89 */     ServiciosDAO serf = new ServiciosDAO();
/*   90 */     ServiciosDTO regServicio = serf.cargarRegistro(regSol.getCodigoServicio());
/*   91 */     serf.close();
/*      */     
/*   93 */     AreasDAO areaf = new AreasDAO();
/*   94 */     AreasDTO areaProveedor = areaf.getArea(regSol.getAreaProveedor());
/*   95 */     areaf.close();
/*      */     
/*   97 */     SisUsuariosDAO perf = new SisUsuariosDAO();
/*   98 */     SisUsuariosDTO elNavegante = perf.cargarRegistro(idNav);
/*   99 */     SisUsuariosDTO elCliente = perf.cargarRegistro(regSol.getEmpleadoCliente());
/*      */     
/*  101 */     EstadoDAO estadof = new EstadoDAO();
/*  102 */     EstadoDTO estado = estadof.getEstado(regSol.getCodigoEstado());
/*  103 */     estadof.close();
/*      */ 
/*      */ 
/*      */     
/*  107 */     this.pagHTML = (VSPorAtHTML)comms.xmlcFactory.create(VSPorAtHTML.class);
/*      */     
/*  109 */     this.pagHTML.setTextNumerosolicitud("" + regSol.getNumeroMostrar());
/*      */     
/*  111 */     this.pagHTML.setTextServicio(regSol.getNombreServicio());
/*  112 */     this.pagHTML.setTextAreap(regSol.getNombreAreaProveedora());
/*  113 */     this.pagHTML.setTextAreacliente(regSol.getNombreAreaCliente());
/*  114 */     this.pagHTML.setTextProveedor(regSol.getNombreProveedor());
/*  115 */     this.pagHTML.setTextCliente(regSol.getNombreCliente());
/*      */     
/*  117 */     this.pagHTML.setTextFechavigencia(regSol.getFechaVigencia());
/*  118 */     this.pagHTML.setTextFechaestimada(regSol.getFechaEstimadaTerminacion());
/*  119 */     this.pagHTML.setTextFechaEscalamientos(regSol.getFechaBaseEscalamientos());
/*  120 */     this.pagHTML.setTextFechaRealTerminacion(regSol.getFechaRealTerminacion());
/*  121 */     this.pagHTML.setTextFechaGenerada(regSol.getFechaGenerada());
/*  122 */     this.pagHTML.setTextEstado(estado.getDescripcion());
/*  123 */     this.pagHTML.setTextEscalamiento("" + regSol.getNivelEscalamiento());
/*      */     
/*  125 */     this.pagHTML.setTextUnidad("" + regSol.getDuracion() + " " + regSol.getUnidadMedida());
/*  126 */     this.pagHTML.setTextTiempoServicio("" + regSol.getTiempoServicio());
/*      */     
/*  128 */     this.pagHTML.setTextMensaje("" + regServicio.getMensaje());
/*      */     
/*  130 */     this.pagHTML.getElementIdsolicitud().setValue("" + regSol.getNumero());
/*  131 */     this.pagHTML.getElementIdsolicitudped().setValue("" + regSol.getNumero());
/*  132 */     this.pagHTML.getElementIdSolicitudResp().setValue("" + regSol.getNumero());
/*  133 */     this.pagHTML.getElementSolicitudatencion().setValue("" + regSol.getNumero());
/*  134 */     this.pagHTML.getElementIdSolicitudRecordar().setValue("" + regSol.getNumero());
/*  135 */     this.pagHTML.getElementIdsolicitudmail().setValue("" + regSol.getNumero());
/*  136 */     this.pagHTML.getElementIdSolicitudModificar().setValue("" + regSol.getNumero());
/*  137 */     this.pagHTML.getElementIdSolicitudNueva().setValue("" + regSol.getNumero());
/*  138 */     this.pagHTML.getElementIdSolicitudCaracter().setValue("" + regSol.getNumero());
/*  139 */     this.pagHTML.getElementIdSolicitudExtender().setValue("" + regSol.getNumero());
/*  140 */     this.pagHTML.getElementPagina_siguiente().setValue("VSPorAt.po");
/*  141 */     this.pagHTML.getElementPagina_siguienteAtencion().setValue("VSPorAt.po");
/*  142 */     this.pagHTML.getElementPagina_siguienteArc().setValue("VSPorAt.po");
/*  143 */     this.pagHTML.getElementIdSolicitudArchivos().setValue("" + regSol.getNumero());
/*  144 */     this.pagHTML.getElementIdSolicitudReplica().setValue("" + regSol.getNumero());
/*  145 */     this.pagHTML.getElementIdSolicitudMejora().setValue("" + regSol.getNumero());
/*      */     
/*  147 */     this.pagHTML.getElementFechaEstCacao().setValue("" + Utilidades.darFormatoFecha(regSol.getFechaEstimadaTerminacion()));
/*  148 */     this.pagHTML.getElementFechaCacao().setValue("" + Utilidades.darFormatoFecha(regSol.getFechaEstimadaTerminacion()));
/*      */     
/*  150 */     this.pagHTML.getElementNumeroAnexos().setValue("" + regServicio.getNumeroAnexos());
/*      */ 
/*      */ 
/*      */     
/*  154 */     boolean quitarPadre = true;
/*  155 */     if (regSol.getSolicitudPadre() >= 1) {
/*  156 */       SolicitudDAO sf2 = new SolicitudDAO();
/*  157 */       SolicitudDTO regSolPadre = sf2.getSolicitud(regSol.getSolicitudPadre());
/*  158 */       sf2.close();
/*      */       
/*  160 */       if (regSolPadre != null) {
/*  161 */         this.pagHTML.getElementIdSolicitudPadre().setValue("" + regSol.getSolicitudPadre());
/*      */         
/*  163 */         if (regSolPadre.getEmpleadoProveedor() == idNav) {
/*  164 */           HTMLFormElement forma = this.pagHTML.getElementFormaPadre();
/*  165 */           forma.setAction("VSPorAt.po");
/*      */         } 
/*  167 */         quitarPadre = false;
/*      */       } 
/*      */     } 
/*      */     
/*  171 */     if (quitarPadre) {
/*  172 */       Element divPadre = this.pagHTML.getElementDivSolicitudPadre();
/*  173 */       divPadre.getParentNode().removeChild(divPadre);
/*      */     } 
/*      */ 
/*      */     
/*  177 */     int capturas = caracteristicas(comms, regSol.getNumero(), regServicio.getCodigo(), estado.getTipoEstado(), idNav, regSol.getAreaProveedor(), regSol.getEmpleadoProveedor(), elCliente.getIdcorreo());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  187 */     calicacionSolicitud(regSol.getCodigoOportunidad(), regSol.getCodigoConfiabilidad(), regServicio.isCalificarServicio());
/*      */     
/*  189 */     if (regServicio.getAnidar().equals("S") || areaProveedor.getAnidar().equals("S")) {
/*  190 */       Element div = this.pagHTML.getElementIdNuevaSolicitud();
/*  191 */       div.getParentNode().removeChild(div);
/*      */     } 
/*      */     
/*  194 */     long diasCorridos = Utilidades2.diferenciaEnDias(regSol.getFechaVigencia(), Utilidades.fechaActual());
/*  195 */     long diasServicio = Utilidades2.diasDelServicio(regSol.getDuracion(), regSol.getUnidadMedida());
/*  196 */     float porcentajeConsumido = 100.0F;
/*  197 */     if (diasCorridos < diasServicio) porcentajeConsumido = (float)diasCorridos / (float)diasServicio * 100.0F; 
/*  198 */     if (porcentajeConsumido < 0.0F) porcentajeConsumido = 0.0F;
/*      */     
/*  200 */     boolean valido = true;
/*      */ 
/*      */     
/*  203 */     if (diasCorridos > diasServicio * ParametrosDTO.getInt("porcentaje.redireccionar.servicio") / 100L) {
/*  204 */       valido = false;
/*      */     }
/*      */ 
/*      */     
/*  208 */     aplazamientosSolicitud(comms, regSol.getNumero());
/*      */     
/*  210 */     boolean hay = false;
/*      */ 
/*      */     
/*  213 */     if (!hay) {
/*  214 */       Element divAplazamientos = this.pagHTML.getElementAuditorCompa();
/*  215 */       divAplazamientos.getParentNode().removeChild(divAplazamientos);
/*      */     } 
/*      */ 
/*      */     
/*  219 */     atencionesSolicitud(comms, regSol);
/*      */ 
/*      */     
/*  222 */     SeguridadDAO rsSeguridad = new SeguridadDAO();
/*  223 */     if (!rsSeguridad.getEstadoConexion()) {
/*  224 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */     }
/*  226 */     boolean tienePermisoDireccionar = rsSeguridad.tieneLlave(miGrupo, "oCambiarProveedor");
/*  227 */     boolean tienePermisoReplicar = rsSeguridad.tieneLlave(miGrupo, "oReplicarSolicitud");
/*  228 */     rsSeguridad.close();
/*      */     
/*  230 */     historiaSolicitud(comms, regSol.getNumero());
/*      */     
/*      */     try {
/*  233 */       if (estado.getTipoEstado().trim().equals("EF") || estado.getTipoEstado().trim().equals("NCO") || estado.getTipoEstado().trim().equals("AN")) {
/*      */         
/*  235 */         Element divatencion = this.pagHTML.getElementNuevaatencion();
/*  236 */         divatencion.getParentNode().removeChild(divatencion);
/*      */       } 
/*      */       
/*  239 */       if (elNavegante.getCodigoEmpleado() == regSol.getEmpleadoCliente() || elNavegante.getCodigoEmpleado() == regSol.getEmpleadoProveedor() || estado.getTipoEstado().trim().equals("EF")) {
/*  240 */         Element recordarAtencion = this.pagHTML.getElementIdRecordarAtencion();
/*  241 */         recordarAtencion.getParentNode().removeChild(recordarAtencion);
/*      */       }
/*      */     
/*  244 */     } catch (Exception e) {
/*  245 */       e.printStackTrace();
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  250 */     permitirCambioResponsable(comms, regSol, estado.getTipoEstado(), tienePermisoDireccionar, valido, areaProveedor.getSecuencia(), areaProveedor.getNivel(), regServicio.getCambiaProveedor());
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
/*  261 */     if (estado.getTipoEstado().equals("CAL") || !regSol.getAbierta()) {
/*  262 */       Element idmodi = this.pagHTML.getElementIdModCaracteristica();
/*  263 */       idmodi.getParentNode().removeChild(idmodi);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  269 */     if (!estado.getTipoEstado().trim().equals("PRV") || (porcentajeConsumido >= ParametrosDTO.getInt("porcentaje.solicitar.aplazamiento") && regServicio.getTipoServicio().equals("0"))) {
/*      */ 
/*      */ 
/*      */       
/*  273 */       Element pedirc = this.pagHTML.getElementPedircacao();
/*  274 */       pedirc.getParentNode().removeChild(pedirc);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  279 */     cambiosEstado(comms, regSol, estado, regServicio, porcentajeConsumido);
/*      */     
/*  281 */     hay = false;
/*      */ 
/*      */     
/*  284 */     int cuantasSinCerrar = solicitudesHijas(comms, regSol.getNumero(), regSol.getNumeroFlujo(), idNav);
/*      */     try {
/*  286 */       if (cuantasSinCerrar > 0) {
/*  287 */         Element ce = this.pagHTML.getElementCambiarestado();
/*  288 */         ce.getParentNode().removeChild(ce);
/*      */       }
/*      */     
/*  291 */     } catch (Exception e) {}
/*      */     
/*  293 */     int archivosAnexos = archivosAnexos(regSol.getNumero(), elUsuario);
/*      */     
/*  295 */     this.pagHTML.getElementAnexosHechos().setValue("" + archivosAnexos);
/*      */     
/*  297 */     Element menu = this.pagHTML.getElementMiBody();
/*  298 */     if (capturas > 0) {
/*  299 */       menu.setAttribute("onload", "try {document.miForma.elements[0].focus();} catch(e){}");
/*  300 */     } else if (cuantasSinCerrar == 0) {
/*  301 */       menu.setAttribute("onload", "try {document.myForm.nuevoestado.focus();} catch(e){}");
/*      */     } 
/*      */     
/*  304 */     if (!regSol.getAbierta()) {
/*  305 */       Element unElemento = this.pagHTML.getElementIdMasArchivos();
/*  306 */       unElemento.getParentNode().removeChild(unElemento);
/*      */     } 
/*      */ 
/*      */     
/*  310 */     if (!estado.getTipoEstado().trim().equals("PRV") || !tienePermisoReplicar) {
/*  311 */       Element unElemento = this.pagHTML.getElementIdReplicar();
/*  312 */       unElemento.getParentNode().removeChild(unElemento);
/*      */     } 
/*      */     
/*  315 */     if (!regServicio.getTipoServicio().equals(Integer.toString(2))) {
/*  316 */       Element unElemento = this.pagHTML.getElementTdAcciones();
/*  317 */       unElemento.getParentNode().removeChild(unElemento);
/*      */     } 
/*      */     
/*  320 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  321 */     comms.response.writeDOM(this.pagHTML);
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
/*      */   private void cambiosEstado(HttpPresentationComms comms, VSolicitudesDTO regSol, EstadoDTO estado, ServiciosDTO regServicio, float porcentajeConsumido) throws HttpPresentationException, KeywordValueException {
/*  343 */     boolean hayestados = false;
/*      */ 
/*      */ 
/*      */     
/*  347 */     EstadoDAO rsEstado = new EstadoDAO();
/*      */     
/*  349 */     rsEstado.cargarTodosSiguientesParaEstadoParaProveedor(estado.getCodigo(), porcentajeConsumido, regSol.getNumeroDevoluciones());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  354 */     HTMLSelectElement hse = this.pagHTML.getElementEstados();
/*  355 */     HTMLOptionElement hoe = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  356 */     hoe.setValue("");
/*  357 */     hoe.appendChild(this.pagHTML.createTextNode(""));
/*  358 */     hse.appendChild(hoe);
/*      */     EstadoDTO regEstado;
/*  360 */     while ((regEstado = rsEstado.next()) != null) {
/*      */       
/*  362 */       if (!regServicio.isPermiteDevolver() && regEstado.getTipoEstado().trim().equals("DV")) {
/*      */         continue;
/*      */       }
/*  365 */       if (!regServicio.isDevolverPoliticas() && regEstado.getTipoEstado().trim().equals("DFP")) {
/*      */         continue;
/*      */       }
/*      */       
/*  369 */       hayestados = true;
/*  370 */       hoe = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  371 */       hoe.setValue("" + regEstado.getCodigo());
/*  372 */       hoe.appendChild(this.pagHTML.createTextNode(regEstado.getDescripcion()));
/*  373 */       hse.appendChild(hoe);
/*      */     } 
/*      */     
/*  376 */     if (!hayestados) {
/*  377 */       Element ce = this.pagHTML.getElementCambiarestado();
/*  378 */       ce.getParentNode().removeChild(ce);
/*      */     } 
/*  380 */     rsEstado.close();
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
/*      */   
/*      */   private void permitirCambioResponsable(HttpPresentationComms comms, VSolicitudesDTO regSol, String tipoEstado, boolean tienePermisoDireccionar, boolean valido, String secuencia, int nivel, String cambiaProveedor) throws HttpPresentationException, KeywordValueException {
/*  407 */     if (!tienePermisoDireccionar || tipoEstado.trim().equals("EF") || tipoEstado.trim().equals("CAL") || !cambiaProveedor.equals("S") || !valido) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  412 */       Element nuevoResp = this.pagHTML.getElementCambiarResp();
/*  413 */       nuevoResp.getParentNode().removeChild(nuevoResp);
/*      */     }
/*      */     else {
/*      */       
/*  417 */       SisUsuariosDAO pf = new SisUsuariosDAO();
/*  418 */       Collection arr = pf.cargarsubditos(secuencia, nivel);
/*      */       
/*  420 */       HTMLSelectElement hpArea = this.pagHTML.getElementNvoResp();
/*  421 */       boolean hayOtrasPersonas = false;
/*      */       
/*  423 */       Iterator iterator = arr.iterator();
/*  424 */       while (iterator.hasNext()) {
/*  425 */         SisUsuariosDTO personaDelArea = (SisUsuariosDTO)iterator.next();
/*  426 */         if (regSol.getEmpleadoProveedor() != personaDelArea.getCodigoEmpleado()) {
/*  427 */           hayOtrasPersonas = true;
/*  428 */           HTMLOptionElement hpa = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  429 */           hpa.setValue("pa_" + personaDelArea.getCodigoEmpleado() + "_" + personaDelArea.getArea());
/*  430 */           hpa.appendChild(this.pagHTML.createTextNode(personaDelArea.getApellidos() + " " + personaDelArea.getNombres() + " de " + personaDelArea.getNombreArea()));
/*  431 */           hpArea.appendChild(hpa);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  436 */       if (!hayOtrasPersonas) {
/*  437 */         Element nuevoResp1 = this.pagHTML.getElementCambiarResp();
/*  438 */         nuevoResp1.getParentNode().removeChild(nuevoResp1);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int archivosAnexos(int numeroSolicitud, String elUsuario) {
/*  449 */     HTMLTableElement tabla = this.pagHTML.getElementIdArchivos();
/*      */     
/*  451 */     ArchivosSolicitudDAO rs = new ArchivosSolicitudDAO();
/*  452 */     rs.getArchivosSolicitud(numeroSolicitud);
/*  453 */     ArchivosSolicitudDTO reg = rs.next();
/*      */     
/*  455 */     int cuantos = 0;
/*  456 */     boolean fondo = false;
/*  457 */     boolean hay = false;
/*  458 */     while (reg != null) {
/*  459 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  460 */       fondo = !fondo;
/*  461 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*      */       
/*  463 */       if (reg.getEstado().equals("B")) {
/*  464 */         eltr.appendChild(newtd("Borrado"));
/*      */       }
/*  466 */       else if (reg.getUsuarioInsercion().equals(elUsuario)) {
/*  467 */         HTMLElement tdMarca = (HTMLElement)this.pagHTML.createElement("td");
/*      */         
/*  469 */         HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
/*  470 */         checkbox.setAttribute("type", "checkbox");
/*  471 */         checkbox.setName("" + reg.getConsecutivo());
/*  472 */         tdMarca.appendChild(checkbox);
/*  473 */         eltr.appendChild(tdMarca);
/*      */       } else {
/*      */         
/*  476 */         eltr.appendChild(newtd(""));
/*      */       } 
/*      */       
/*  479 */       if (reg.getEstado().equals("B")) {
/*  480 */         eltr.appendChild(newtd(reg.getArchivo()));
/*      */       } else {
/*      */         
/*  483 */         eltr.appendChild(newtdhref(reg.getArchivo(), "VerArchivo.po?origen=A&archivo=" + reg.getArchivo() + "&fecha=" + Utilidades.darFormatoFecha(reg.getFechaInsercion()), true));
/*      */       } 
/*  485 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaInsercion())));
/*  486 */       eltr.appendChild(newtd("" + reg.getUsuarioInsercion()));
/*  487 */       tabla.appendChild(eltr);
/*      */       
/*  489 */       if (elUsuario.equals(reg.getUsuarioInsercion())) {
/*  490 */         cuantos++;
/*      */       }
/*      */       
/*  493 */       reg = rs.next();
/*      */       
/*  495 */       hay = true;
/*      */     } 
/*  497 */     rs.close();
/*      */ 
/*      */ 
/*      */     
/*  501 */     if (!hay) {
/*  502 */       Element divArchivos = this.pagHTML.getElementIdmostrararchivos();
/*  503 */       divArchivos.getParentNode().removeChild(divArchivos);
/*      */     } 
/*  505 */     return cuantos;
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
/*      */ 
/*      */   
/*      */   private int caracteristicas(HttpPresentationComms comms, int numeroSolicitud, int codigoServicio, String tipoEstado, int idNav, int areaProveedor, int codigoProveedor, String idCorreoCliente) throws HttpPresentationException, KeywordValueException {
/*  533 */     HTMLTableElement tabla = this.pagHTML.getElementCaracteristicas();
/*      */     
/*  535 */     CaracteristicasDAO rsCaracteristicas = new CaracteristicasDAO();
/*  536 */     Collection arr = rsCaracteristicas.cargarTodosParaServicio(codigoServicio, "T", "A");
/*  537 */     rsCaracteristicas.close();
/*      */     
/*  539 */     HTMLSelectElement extensiones = this.pagHTML.getElementExtensiones();
/*      */     
/*  541 */     int capturas = 0;
/*      */     
/*  543 */     boolean encontro = false;
/*  544 */     boolean extender = false;
/*      */ 
/*      */     
/*  547 */     DetalleSolicitudDAO dsf = new DetalleSolicitudDAO();
/*      */ 
/*      */     
/*  550 */     CaracteristicasValorDAO rsCarValor = new CaracteristicasValorDAO();
/*      */     
/*  552 */     boolean fondo = false;
/*  553 */     String cadenaFechas = "";
/*      */     
/*  555 */     Collection arrAnidadas = new ArrayList();
/*  556 */     Collection arrDependen = new ArrayList();
/*      */     
/*  558 */     Iterator iterator = arr.iterator();
/*  559 */     while (iterator.hasNext()) {
/*      */       
/*  561 */       CaracteristicasDTO regCar = (CaracteristicasDTO)iterator.next();
/*      */       
/*  563 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*      */       
/*  565 */       fondo = !fondo;
/*  566 */       eltr.setAttributeNode(newAttr("class", "car ct" + (fondo ? "1" : "2")));
/*  567 */       eltr.setAttributeNode(newAttr("codigo", "" + regCar.getCodigo()));
/*      */       
/*  569 */       eltr.appendChild(newtd("" + ((regCar.getObliga() == true) ? "* " : "  ") + regCar.getDescripcion()));
/*      */ 
/*      */       
/*  572 */       HTMLElement tdDescripcion = (HTMLElement)this.pagHTML.createElement("td");
/*  573 */       tdDescripcion.setAttributeNode(newAttr("class", "ctd"));
/*      */       
/*  575 */       dsf.cargarParaSolicitud(numeroSolicitud, regCar.getCodigo());
/*  576 */       DetalleSolicitudDTO ds = dsf.next();
/*  577 */       boolean hay = false;
/*  578 */       String descripcion = "";
/*  579 */       String sCodigoDocumento = "";
/*  580 */       while (ds != null) {
/*  581 */         descripcion = descripcion + ds.getObservacion() + ". ";
/*  582 */         sCodigoDocumento = ds.getObservacion();
/*      */         
/*  584 */         if (regCar.getNombreProcedimiento().length() > 0) {
/*  585 */           descripcion = descripcion + ejecutarProcedimiento(numeroSolicitud, regCar.getCodigo(), ds.getObservacion(), regCar.getNombreProcedimiento(), regCar.getCaracteristicaAnida());
/*      */         }
/*      */         
/*  588 */         if (regCar.getTipo().equals("2") && 
/*  589 */           regCar.getCuantasDependen() > 0) {
/*  590 */           TemporalDTO tt = new TemporalDTO();
/*  591 */           tt.setCaracteristica(regCar.getCodigo());
/*  592 */           tt.setValor(ds.getValor());
/*  593 */           arrDependen.add(tt);
/*      */         } 
/*      */ 
/*      */         
/*  597 */         if ((regCar.getTipo().equals("2") || regCar.getTipo().equals("8")) && regCar.getCaracteristicaAnida() > 0) {
/*  598 */           TemporalDTO tt = new TemporalDTO();
/*  599 */           tt.setCaracteristica(regCar.getCaracteristicaAnida());
/*  600 */           tt.setValor(ds.getValor());
/*  601 */           arrAnidadas.add(tt);
/*      */         } 
/*      */ 
/*      */         
/*  605 */         ds = dsf.next();
/*  606 */         hay = true;
/*      */       } 
/*      */       
/*  609 */       if (hay)
/*      */       {
/*  611 */         if (regCar.getTipo().equals("6")) {
/*  612 */           TGeneralDAO rsGen = new TGeneralDAO();
/*  613 */           TGeneralDTO regDoc = rsGen.cargarDocumento(sCodigoDocumento);
/*  614 */           rsGen.close();
/*  615 */           if (regDoc != null) {
/*      */ 
/*      */             
/*  618 */             String url = "VerArchivo.po?ruta=cal_archivos&archivo=" + regDoc.getDescripcion();
/*  619 */             if (idNav == codigoProveedor) {
/*  620 */               url = "VerArchivo.po?ruta=cal_archivos&archivo=" + regDoc.getCodigo();
/*      */             }
/*  622 */             tdDescripcion.appendChild(newhref(descripcion, url, true));
/*      */           } else {
/*      */             
/*  625 */             tdDescripcion.appendChild(this.pagHTML.createTextNode(descripcion));
/*      */           }
/*      */         
/*  628 */         } else if (!regCar.getTipo().equals("7")) {
/*      */ 
/*      */ 
/*      */           
/*  632 */           tdDescripcion.appendChild(this.pagHTML.createTextNode(descripcion));
/*      */         } 
/*      */       }
/*      */       
/*  636 */       if (regCar.getRol().equals("P") && (tipoEstado.trim().equals("PRV") || tipoEstado.trim().equals("ESC"))) {
/*  637 */         if (!hay) {
/*  638 */           Iterator iterDepende; int valorDepende; HTMLSelectElement combo; HTMLInputElement inp; encontro = true;
/*  639 */           switch (Integer.parseInt(regCar.getTipo())) {
/*      */             case 1:
/*      */             case 3:
/*      */             case 4:
/*  643 */               inp = (HTMLInputElement)this.pagHTML.createElement("input");
/*  644 */               inp.setMaxLength(regCar.getLongitud());
/*  645 */               if (regCar.getLongitud() >= 200) {
/*  646 */                 inp.setSize("70");
/*      */               }
/*  648 */               else if (regCar.getLongitud() <= 20) {
/*  649 */                 inp.setSize("15");
/*      */               }
/*  651 */               else if (regCar.getLongitud() > 20 && regCar.getLongitud() < 100) {
/*  652 */                 inp.setSize("30");
/*      */               } else {
/*      */                 
/*  655 */                 inp.setSize("40");
/*      */               } 
/*      */               
/*  658 */               inp.setName("" + regCar.getCodigo());
/*  659 */               inp.setAttributeNode(newAttr("onkeyDown", "return f_salto2(event)"));
/*  660 */               inp.setAttributeNode(newAttr("onkeyUp", "return f_salto(event)"));
/*  661 */               inp.setAttributeNode(newAttr("class", "inp"));
/*      */               
/*  663 */               inp.setAttributeNode(newAttr("obliga", "" + ((regCar.getObliga() == true) ? "S" : "N")));
/*  664 */               inp.setAttributeNode(newAttr("tipo", "" + regCar.getTipo()));
/*      */               
/*  666 */               inp.setAttributeNode(newAttr("tipoVal", "" + regCar.getTipoValidacion()));
/*  667 */               inp.setAttributeNode(newAttr("carVal", "" + regCar.getCaracteristicaValida()));
/*  668 */               if (regCar.getTipo().equals("3")) {
/*  669 */                 inp.setAttributeNode(newAttr("decimales", "" + regCar.getNumeroDecimales()));
/*      */               }
/*      */               
/*  672 */               if (regCar.getNombreProcedimiento().length() > 0) {
/*  673 */                 inp.setAttributeNode(newAttr("onblur", "ejecutarProcedimiento(this.name,this.value,'" + regCar.getNombreProcedimiento() + "'," + regCar.getCaracteristicaAnida() + ");"));
/*      */               }
/*      */               
/*  676 */               switch (Integer.parseInt(regCar.getTipo())) {
/*      */                 case 1:
/*  678 */                   inp.setAttributeNode(newAttr("onkeypress", "validarTecla(event, 'AL');"));
/*      */                   break;
/*      */                 case 3:
/*  681 */                   inp.setAttributeNode(newAttr("onkeypress", "validarTecla(event, 'DE');"));
/*  682 */                   inp.setAttributeNode(newAttr("onFocus", "this.value=desformatnum(this.value);"));
/*  683 */                   inp.setAttributeNode(newAttr("onBlur", "this.value=formatnum(this.value);"));
/*      */                   break;
/*      */                 case 4:
/*  686 */                   inp.setAttributeNode(newAttr("onkeypress", "validarTecla(event, 'FE');"));
/*      */                   break;
/*      */               } 
/*  689 */               inp.setId("" + regCar.getCodigo());
/*  690 */               tdDescripcion.appendChild(inp);
/*      */               
/*  692 */               if (regCar.getTipo().equals("4")) {
/*  693 */                 cadenaFechas = cadenaFechas + agregarCapturaFecha("" + regCar.getCodigo());
/*  694 */                 HTMLImageElement inp2 = (HTMLImageElement)this.pagHTML.createElement("IMG");
/*  695 */                 inp2.setSrc("media/calendario.jpg");
/*  696 */                 inp2.setId("b" + regCar.getCodigo());
/*  697 */                 inp2.setAlt("Calendario");
/*  698 */                 inp2.setHeight("20");
/*  699 */                 inp2.setWidth("20");
/*  700 */                 tdDescripcion.appendChild(inp2);
/*      */               } 
/*      */               
/*  703 */               if (regCar.getNombreProcedimiento().length() > 0) {
/*  704 */                 HTMLElement inp3 = (HTMLElement)this.pagHTML.createElement("span");
/*  705 */                 inp3.setId("msg" + regCar.getCodigo());
/*  706 */                 inp3.setAttributeNode(newAttr("class", "error"));
/*  707 */                 tdDescripcion.appendChild(inp3);
/*      */               } 
/*      */               break;
/*      */ 
/*      */ 
/*      */             
/*      */             case 2:
/*      */             case 8:
/*  715 */               combo = (HTMLSelectElement)this.pagHTML.createElement("Select");
/*  716 */               combo.setName("" + regCar.getCodigo());
/*  717 */               combo.setId("" + regCar.getCodigo());
/*      */               
/*  719 */               if (regCar.getCaracteristicaAnida() > 0) {
/*  720 */                 combo.setAttributeNode(newAttr("onchange", "buscarAnidadas(this.name,this.value," + regCar.getCaracteristicaAnida() + ");"));
/*      */               }
/*      */               
/*  723 */               valorDepende = 0;
/*  724 */               iterDepende = arrAnidadas.iterator();
/*  725 */               while (iterDepende.hasNext()) {
/*  726 */                 TemporalDTO regTemp = (TemporalDTO)iterDepende.next();
/*  727 */                 if (regTemp.getCaracteristica() == regCar.getCodigo()) {
/*  728 */                   valorDepende = regTemp.getValor();
/*      */                 }
/*      */               } 
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
/*  746 */               llenarCombo(rsCarValor, combo, regCar.getTipo(), regCar.getCodigo(), areaProveedor, valorDepende);
/*  747 */               combo.setAttributeNode(newAttr("onkeyDown", "return f_salto2(event)"));
/*  748 */               combo.setAttributeNode(newAttr("onkeyUp", "return f_salto(event)"));
/*  749 */               combo.setAttributeNode(newAttr("class", "inp"));
/*      */               
/*  751 */               if (regCar.getCuantasDependen() > 0) {
/*  752 */                 combo.setAttributeNode(newAttr("onchange", "mostrarPendientes(this.name,this.value);"));
/*      */               }
/*      */               
/*  755 */               tdDescripcion.appendChild(combo);
/*      */               break;
/*      */             
/*      */             case 6:
/*  759 */               combo = (HTMLSelectElement)this.pagHTML.createElement("Select");
/*  760 */               combo.setName("" + regCar.getCodigo());
/*  761 */               comboDocumentos(combo);
/*  762 */               combo.setAttributeNode(newAttr("onkeyDown", "return f_salto2(event)"));
/*  763 */               combo.setAttributeNode(newAttr("onkeyUp", "return f_salto(event)"));
/*  764 */               combo.setAttributeNode(newAttr("class", "inp"));
/*  765 */               tdDescripcion.appendChild(combo);
/*      */               break;
/*      */           } 
/*      */           
/*  769 */           if (regCar.getObliga()) capturas++;
/*      */         
/*  771 */         } else if (!regCar.getTipo().equals("2")) {
/*  772 */           HTMLOptionElement extension = (HTMLOptionElement)this.pagHTML.createElement("option");
/*  773 */           extension.setValue("" + regCar.getCodigo());
/*  774 */           extension.appendChild(this.pagHTML.createTextNode(regCar.getDescripcion()));
/*  775 */           extensiones.appendChild(extension);
/*  776 */           extender = true;
/*      */         } 
/*      */       }
/*  779 */       if (!hay) tdDescripcion.appendChild(this.pagHTML.createTextNode(""));
/*      */ 
/*      */       
/*  782 */       if (regCar.getCaracteristicaDepende() > 0) {
/*  783 */         int valorMostrar = 0;
/*  784 */         Iterator iterDepende = arrDependen.iterator();
/*  785 */         while (iterDepende.hasNext()) {
/*  786 */           TemporalDTO regTemp = (TemporalDTO)iterDepende.next();
/*  787 */           if (regTemp.getCaracteristica() == regCar.getCaracteristicaDepende()) {
/*  788 */             valorMostrar = regTemp.getValor();
/*      */           }
/*      */         } 
/*      */         
/*  792 */         if (valorMostrar != regCar.getValorDepende()) {
/*  793 */           eltr.setAttributeNode(newAttr("style", "display:none"));
/*      */         }
/*  795 */         eltr.setAttributeNode(newAttr("depende", "" + regCar.getCaracteristicaDepende()));
/*  796 */         eltr.setAttributeNode(newAttr("valorDepende", "" + regCar.getValorDepende()));
/*      */       } 
/*      */       
/*  799 */       eltr.appendChild(tdDescripcion);
/*  800 */       tabla.appendChild(eltr);
/*      */     } 
/*  802 */     dsf.close();
/*  803 */     rsCarValor.close();
/*  804 */     arr.clear();
/*      */ 
/*      */     
/*  807 */     if (cadenaFechas.length() > 0) {
/*  808 */       this.pagHTML.setTextJSValfechas(cadenaFechas);
/*      */     }
/*      */     
/*  811 */     if (!encontro) {
/*  812 */       Element elemento = this.pagHTML.getElementNueva();
/*  813 */       elemento.getParentNode().removeChild(elemento);
/*      */     } 
/*  815 */     if (!extender) {
/*  816 */       Element elemento = this.pagHTML.getElementExtender();
/*  817 */       elemento.getParentNode().removeChild(elemento);
/*      */     } 
/*  819 */     return capturas;
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
/*  834 */     if (!calificacionOportunidad.equals("")) {
/*  835 */       TipoCalificacionDAO tcf = new TipoCalificacionDAO();
/*  836 */       TipoCalificacionDTO tipocali = tcf.getTipoCalificacion(calificacionOportunidad.charAt(0));
/*  837 */       this.pagHTML.setTextOportunidad("" + tipocali.getDescripcion());
/*  838 */       tcf.close();
/*      */     } 
/*      */     
/*  841 */     if (!calificaServicio) {
/*  842 */       this.pagHTML.setTextConfiabilidad("No aplica");
/*      */     }
/*  844 */     else if (!calificacionConfiabilidad.equals("")) {
/*  845 */       TipoCalificacionDAO tcf = new TipoCalificacionDAO();
/*  846 */       TipoCalificacionDTO tipocali = tcf.getTipoCalificacion(calificacionConfiabilidad.charAt(0));
/*  847 */       this.pagHTML.setTextConfiabilidad("" + tipocali.getDescripcion());
/*  848 */       tcf.close();
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
/*      */   private void aplazamientosSolicitud(HttpPresentationComms comms, int numeroSolicitud) throws HttpPresentationException, KeywordValueException {
/*  863 */     AplazamientosSolicitudDAO rs = new AplazamientosSolicitudDAO();
/*  864 */     if (!rs.getEstadoConexion()) {
/*  865 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */     }
/*  867 */     Collection arr = rs.cargarTodos(numeroSolicitud);
/*  868 */     rs.close();
/*      */     
/*  870 */     Iterator iterator = arr.iterator();
/*  871 */     boolean fondo = false;
/*  872 */     HTMLTableElement tabla = this.pagHTML.getElementIdDetalleAplazamientos();
/*  873 */     while (iterator.hasNext()) {
/*  874 */       AplazamientosSolicitudDTO reg = (AplazamientosSolicitudDTO)iterator.next();
/*  875 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  876 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*      */       
/*  878 */       eltr.appendChild(newtd("" + reg.getJustificacion()));
/*  879 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFecha())));
/*      */ 
/*      */       
/*  882 */       int estadoA = reg.getEstado();
/*  883 */       switch (estadoA) {
/*      */         case 0:
/*  885 */           eltr.appendChild(newtd("POR ATENDER"));
/*  886 */           eltr.appendChild(newtd(""));
/*      */           break;
/*      */         case 1:
/*  889 */           eltr.appendChild(newtd("ACEPTADA"));
/*  890 */           eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaestado())));
/*      */           break;
/*      */         case 2:
/*  893 */           eltr.appendChild(newtd("RECHAZADA"));
/*  894 */           eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaestado())));
/*      */           break;
/*      */         case 3:
/*  897 */           eltr.appendChild(newtd("ANULADO"));
/*  898 */           eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaestado())));
/*      */           break;
/*      */       } 
/*  901 */       eltr.appendChild(newtd("" + reg.getJustificacionNega()));
/*  902 */       tabla.appendChild(eltr);
/*      */     } 
/*      */ 
/*      */     
/*  906 */     if (arr.size() == 0) {
/*      */       try {
/*  908 */         Element divAplazamientos = this.pagHTML.getElementIdAplazamientos();
/*  909 */         divAplazamientos.getParentNode().removeChild(divAplazamientos);
/*      */       }
/*  911 */       catch (Exception e) {}
/*      */     }
/*  913 */     arr.clear();
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
/*      */   private void atencionesSolicitud(HttpPresentationComms comms, VSolicitudesDTO regSol) throws HttpPresentationException, KeywordValueException {
/*  930 */     HTMLTableElement tablaat = this.pagHTML.getElementAtencion();
/*  931 */     AtencionSolicitudDAO rs = new AtencionSolicitudDAO();
/*      */     
/*  933 */     if (!rs.getEstadoConexion()) {
/*  934 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */     }
/*      */     
/*  937 */     rs.cargarTodosParaSolicitud(regSol.getNumero());
/*  938 */     AtencionSolicitudDTO reg = rs.next();
/*  939 */     boolean hay = false;
/*      */ 
/*      */     
/*  942 */     boolean fondo = false;
/*  943 */     while (reg != null) {
/*  944 */       hay = true;
/*  945 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*      */       
/*  947 */       fondo = !fondo;
/*  948 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*      */       
/*  950 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFecha())));
/*      */       
/*      */       try {
/*  953 */         if (reg.getPersonaAtendio() != 0) {
/*  954 */           if (regSol.getEmpleadoCliente() == reg.getPersonaAtendio()) {
/*  955 */             eltr.appendChild(newtd("" + regSol.getNombreCliente()));
/*      */           }
/*  957 */           else if (regSol.getEmpleadoProveedor() == reg.getPersonaAtendio()) {
/*  958 */             eltr.appendChild(newtd("" + regSol.getNombreProveedor()));
/*      */           } else {
/*  960 */             SisUsuariosDAO perf = new SisUsuariosDAO();
/*  961 */             SisUsuariosDTO otraPersona = perf.cargarRegistro(reg.getPersonaAtendio());
/*  962 */             eltr.appendChild(newtd(("" + otraPersona != null) ? otraPersona.getNombre() : "Desconocido"));
/*      */           } 
/*      */         } else {
/*      */           
/*  966 */           eltr.appendChild(newtd("Desconocido"));
/*      */         }
/*      */       
/*  969 */       } catch (Exception e) {
/*  970 */         eltr.appendChild(newtd("Desconocido"));
/*      */       } 
/*  972 */       eltr.appendChild(newtd(reg.getObservacion()));
/*  973 */       tablaat.appendChild(eltr);
/*  974 */       reg = rs.next();
/*      */     } 
/*  976 */     rs.close();
/*      */     
/*  978 */     if (!hay) {
/*  979 */       tablaat.getParentNode().removeChild(tablaat);
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
/*      */   private void historiaSolicitud(HttpPresentationComms comms, int numeroSolicitud) throws HttpPresentationException, KeywordValueException {
/*  995 */     boolean fondo = false;
/*      */     
/*  997 */     HTMLTableElement tablahistoria = this.pagHTML.getElementHistoria();
/*      */     
/*  999 */     HistoriaSolicitudDAO rs = new HistoriaSolicitudDAO();
/*      */     
/* 1001 */     if (!rs.getEstadoConexion()) {
/* 1002 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */     }
/*      */     
/* 1005 */     rs.cargarTodosParaSolicitud(numeroSolicitud);
/* 1006 */     HistoriaSolicitudDTO reg = rs.next();
/*      */     
/* 1008 */     EstadoDAO rsEstado = new EstadoDAO();
/*      */     
/* 1010 */     while (reg != null) {
/* 1011 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*      */       
/* 1013 */       fondo = !fondo;
/* 1014 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*      */       
/* 1016 */       EstadoDTO regEstado = rsEstado.getEstado(reg.getEstadoFinal());
/* 1017 */       eltr.appendChild(newtd("" + ((regEstado != null) ? regEstado.getDescripcion() : ("" + reg.getEstadoFinal()))));
/* 1018 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFecha())));
/* 1019 */       eltr.appendChild(newtd("" + reg.getObservaciones()));
/* 1020 */       tablahistoria.appendChild(eltr);
/* 1021 */       reg = rs.next();
/*      */     } 
/* 1023 */     rs.close();
/* 1024 */     rsEstado.close();
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
/*      */   private int solicitudesHijas(HttpPresentationComms comms, int numeroSolicitud, int numeroFlujo, int idNav) throws HttpPresentationException, KeywordValueException {
/* 1039 */     VSolicitudesDAO rs = new VSolicitudesDAO();
/*      */     
/* 1041 */     if (!rs.getEstadoConexion()) {
/* 1042 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*      */     }
/*      */     
/* 1045 */     Collection<VSolicitudesDTO> arr = rs.cargarHijas(numeroSolicitud, numeroFlujo);
/* 1046 */     rs.close();
/*      */     
/* 1048 */     HTMLTableElement hte = this.pagHTML.getElementDetalleTramite();
/*      */     
/* 1050 */     boolean fondo = true;
/* 1051 */     boolean hay = false;
/* 1052 */     int cuantasSinCerrar = 0;
/* 1053 */     Iterator<VSolicitudesDTO> iterator = arr.iterator();
/* 1054 */     while (iterator.hasNext()) {
/* 1055 */       VSolicitudesDTO regSolHijas = (VSolicitudesDTO)iterator.next();
/*      */       
/* 1057 */       hay = true;
/* 1058 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*      */       
/* 1060 */       fondo = !fondo;
/* 1061 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*      */       
/* 1063 */       eltr.appendChild(newtd("" + regSolHijas.getNombreAreaCliente()));
/* 1064 */       eltr.appendChild(newtd("" + regSolHijas.getNombreCliente()));
/* 1065 */       eltr.appendChild(newtd("" + regSolHijas.getNombreAreaProveedora()));
/* 1066 */       eltr.appendChild(newtd("" + regSolHijas.getNombreProveedor()));
/*      */       
/* 1068 */       String sPagina = "";
/* 1069 */       if (regSolHijas.getAreaProveedor() == 0) {
/* 1070 */         sPagina = "SolicitudPendProveedor.po?_operacion=P&solicitud=" + regSolHijas.getNumero() + "&solPadre=" + numeroSolicitud;
/*      */       }
/* 1072 */       else if (regSolHijas.getEmpleadoCliente() == idNav && regSolHijas.getTipoEstado().equals("INI")) {
/* 1073 */         sPagina = "VerSolicitudNoEnviada.po?solicitud=" + regSolHijas.getNumero() + "&solPadre=" + numeroSolicitud;
/*      */       }
/* 1075 */       else if (regSolHijas.getEmpleadoProveedor() == idNav && (regSolHijas.getTipoEstado().equals("PRV") || regSolHijas.getTipoEstado().equals("ESC"))) {
/* 1076 */         sPagina = "VSPorAt.po?solicitud=" + regSolHijas.getNumero();
/*      */       }
/* 1078 */       else if (regSolHijas.getTipoEstado().equals("INI")) {
/* 1079 */         sPagina = "";
/*      */       } else {
/*      */         
/* 1082 */         sPagina = "VSEnCurso.po?solicitud=" + regSolHijas.getNumero() + "&lectura=1";
/*      */       } 
/*      */       
/* 1085 */       if (sPagina.length() > 0) {
/* 1086 */         eltr.appendChild(newtdhref("" + regSolHijas.getNombreServicio(), sPagina, false));
/*      */       } else {
/*      */         
/* 1089 */         eltr.appendChild(newtd("" + regSolHijas.getNombreServicio()));
/*      */       } 
/*      */       
/* 1092 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(regSolHijas.getFechaEstimadaTerminacion())));
/* 1093 */       eltr.appendChild(newtd("" + regSolHijas.getNombreEstado()));
/*      */       
/* 1095 */       hte.appendChild(eltr);
/* 1096 */       if (regSolHijas.getHija().equals("S") && (regSolHijas.getTipoEstado().equals("PRV") || regSolHijas.getTipoEstado().equals("ESC"))) {
/* 1097 */         cuantasSinCerrar++;
/*      */       }
/*      */     } 
/* 1100 */     if (!hay) {
/* 1101 */       Element ce = this.pagHTML.getElementSolicitudesTramite();
/* 1102 */       ce.getParentNode().removeChild(ce);
/*      */     } 
/* 1104 */     return cuantasSinCerrar;
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
/* 1115 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 1116 */     atrib.setValue(valor);
/* 1117 */     return atrib;
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
/*      */   private void llenarCombo(CaracteristicasValorDAO rsCar, HTMLSelectElement combo, String tipoCaracteristica, int caract, int areaProveedor, int valorDepende) {
/* 1135 */     Collection arr = rsCar.cargarTodos(caract, areaProveedor, tipoCaracteristica, valorDepende);
/*      */     
/* 1137 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1138 */     op.setValue("");
/* 1139 */     op.appendChild(this.pagHTML.createTextNode(" "));
/* 1140 */     combo.appendChild(op);
/*      */     
/* 1142 */     Iterator iterator = arr.iterator();
/* 1143 */     while (iterator.hasNext()) {
/*      */       
/* 1145 */       CaracteristicasValorDTO reg = (CaracteristicasValorDTO)iterator.next();
/*      */       
/* 1147 */       op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1148 */       op.setValue("" + reg.getValor());
/* 1149 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 1150 */       combo.appendChild(op);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void comboDocumentos(HTMLSelectElement combo) {
/* 1159 */     TGeneralDAO rs = new TGeneralDAO();
/* 1160 */     Collection arr = rs.cargarDocumentos();
/* 1161 */     rs.close();
/*      */     
/* 1163 */     Iterator iterator = arr.iterator();
/* 1164 */     while (iterator.hasNext()) {
/* 1165 */       TGeneralDTO reg = (TGeneralDTO)iterator.next();
/* 1166 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1167 */       op.setValue("" + reg.getCodigo());
/* 1168 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 1169 */       combo.appendChild(op);
/*      */     } 
/* 1171 */     arr.clear();
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
/*      */   private void comboMultivalores(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco) {
/* 1188 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 1189 */     Collection arr = rs.cargarTabla(tabla);
/* 1190 */     rs.close();
/* 1191 */     if (dejarBlanco) {
/* 1192 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1193 */       op.setValue("");
/* 1194 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 1195 */       combo.appendChild(op);
/*      */     } 
/* 1197 */     Iterator iterator = arr.iterator();
/* 1198 */     while (iterator.hasNext()) {
/* 1199 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 1200 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 1201 */       op.setValue("" + reg.getCodigo());
/* 1202 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 1203 */       if (defecto.equals(reg.getCodigo())) {
/* 1204 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 1205 */         escogida.setValue("on");
/* 1206 */         op.setAttributeNode(escogida);
/*      */       } 
/* 1208 */       combo.appendChild(op);
/*      */     } 
/* 1210 */     arr.clear();
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
/*      */   private HTMLElement newtd(String contenido) {
/* 1225 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 1226 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 1227 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 1228 */     return td;
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
/* 1239 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 1240 */     Element enlace = this.pagHTML.createElement("a");
/* 1241 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 1242 */     enlace.appendChild(hijo);
/* 1243 */     Attr donde = this.pagHTML.createAttribute("href");
/* 1244 */     donde.setValue(vinculo);
/* 1245 */     enlace.setAttributeNode(donde);
/* 1246 */     if (nuevaVentana) {
/* 1247 */       enlace.setAttributeNode(newAttr("TARGET", "_blank"));
/*      */     }
/* 1249 */     td.appendChild(enlace);
/* 1250 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 1251 */     return td;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1261 */   private String agregarCapturaFecha(String fecha) { return "Calendar.setup({inputField :  '" + fecha + "'," + "ifFormat   :  '%Y-%m-%d'," + "button     :  'b" + fecha + "'" + "});"; }
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
/*      */   private Element newhref(String contenido, String vinculo, boolean nuevaVentana) {
/* 1280 */     Element enlace = this.pagHTML.createElement("a");
/* 1281 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 1282 */     enlace.appendChild(hijo);
/* 1283 */     if (nuevaVentana) {
/* 1284 */       enlace.setAttributeNode(newAttr("TARGET", "_blank"));
/*      */     }
/* 1286 */     Attr donde = this.pagHTML.createAttribute("href");
/* 1287 */     donde.setValue(vinculo);
/* 1288 */     enlace.setAttributeNode(donde);
/* 1289 */     return enlace;
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
/*      */   private String ejecutarProcedimiento(int idsol, int caracteristica, String valor, String proceso, int anidada) {
/*      */     try {
/* 1307 */       CaracteristicasDAO rs = new CaracteristicasDAO();
/* 1308 */       RespuestaBD rta = rs.validarProcedimiento(idsol, caracteristica, valor, proceso, anidada);
/* 1309 */       rs.close();
/*      */       
/* 1311 */       if (rta != null) {
/* 1312 */         return rta.getCausal();
/*      */       }
/*      */     }
/* 1315 */     catch (Exception e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1320 */     return "";
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\VSPorAt.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */