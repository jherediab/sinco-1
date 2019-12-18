/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import sinco.business.EstadoDTO;
/*     */ import sinco.business.FechaDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.business.VSolicitudesDTO;
/*     */ import sinco.data.EstadoDAO;
/*     */ import sinco.data.SolicitudDAO;
/*     */ import sinco.data.VSolicitudesDAO;
/*     */ import sinco.spec.Utilidades2;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CambiarEstadoSolicitud
/*     */   implements HttpPresentation
/*     */ {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  27 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  28 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */ 
/*     */     
/*  32 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  33 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */     
/*  35 */     int idsol = Integer.parseInt(comms.request.getParameter("solicitud"));
/*  36 */     String observacion = comms.request.getParameter("observacion");
/*  37 */     int nuevoestado = Integer.parseInt(comms.request.getParameter("nuevoestado"));
/*  38 */     if (nuevoestado == 0) {
/*  39 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=SeleccionarElemento"));
/*     */     }
/*  41 */     int devuelta = 0;
/*     */     try {
/*  43 */       devuelta = Integer.parseInt(comms.request.getParameter("devuelta"));
/*     */     }
/*  45 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  48 */     VSolicitudesDAO rsVSol = new VSolicitudesDAO();
/*  49 */     VSolicitudesDTO regSol = rsVSol.getSolicitud(idsol);
/*  50 */     rsVSol.close();
/*     */     
/*  52 */     EstadoDAO ef = new EstadoDAO();
/*  53 */     EstadoDTO estado = ef.getEstado(nuevoestado);
/*  54 */     EstadoDTO estadoactual = ef.getEstado(regSol.getCodigoEstado());
/*  55 */     ef.close();
/*     */ 
/*     */ 
/*     */     
/*  59 */     if (estado.getTipoEstado().trim().equals("DV") && observacion.trim().equals("")) {
/*  60 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=DigilenciarObservacion"));
/*     */     }
/*     */ 
/*     */     
/*  64 */     if (estadoactual.getTipoEstado().trim().equals("CAL") && estado.getTipoEstado().trim().equals("EF") && regSol.getCodigoConfiabilidad().trim().equals(""))
/*     */     {
/*     */       
/*  67 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=PrimeroCalificar"));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  72 */     Varios oVarios = new Varios();
/*  73 */     int nuevoResponsable = 0;
/*  74 */     if (devuelta == 1 && regSol.getNumeroDevoluciones() == ParametrosDTO.getInt("maximo.numero.devoluciones") - 1) {
/*  75 */       nuevoResponsable = oVarios.getPrimerActivoConCorreo(regSol.getAreaProveedor(), regSol.getEmpleadoProveedor(), "I");
/*     */     }
/*     */     
/*  78 */     int diasSumar = 0;
/*     */     
/*  80 */     if (devuelta == 1 && estado.getTipoEstado().equals("PRV") && regSol.getUnidadMedida().equals("DI")) {
/*  81 */       FechaDTO miFechaA = new FechaDTO(Utilidades.fechaActual());
/*  82 */       FechaDTO miFechaE = new FechaDTO(regSol.getFechaEstimadaTerminacion());
/*  83 */       if (miFechaA.getJuliano() >= miFechaE.getJuliano()) {
/*  84 */         FechaDTO miFechaO = new FechaDTO(regSol.getFechaOportunidad());
/*  85 */         diasSumar = (int)(miFechaE.getJuliano() - miFechaO.getJuliano()) - Utilidades2.festivosEntre(regSol.getFechaOportunidad(), regSol.getFechaEstimadaTerminacion());
/*  86 */         if (diasSumar < 0) diasSumar = 0; 
/*     */       } 
/*     */     } 
/*  89 */     RespuestaBD rtabd = oVarios.actualizarEstadoObj(idNav, regSol.getCodigoEstado(), estado.getCodigo(), observacion, regSol, true, elUsuario, devuelta, nuevoResponsable, diasSumar, "");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     if (rtabd.getCerrarSolicitud().equals("N")) {
/* 105 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoPuedoCerrarSolicitud&p1=" + rtabd.getCausal()));
/*     */     }
/*     */     
/* 108 */     if ((estado.getTipoEstado().trim().equals("DV") || estado.getTipoEstado().trim().equals("AN")) && regSol.getEncuesta() > 0) {
/* 109 */       SolicitudDAO sf = new SolicitudDAO();
/* 110 */       sf.desvincularEncuesta(idsol, elUsuario);
/* 111 */       sf.close();
/*     */     } 
/*     */     
/* 114 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=SolicitudCambioEstado&p1=" + estado.getDescripcion()));
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CambiarEstadoSolicitud.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */