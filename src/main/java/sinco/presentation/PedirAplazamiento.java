/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.GregorianCalendar;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.SolicitudDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AplazamientosSolicitudDAO;
/*     */ import sinco.data.AtencionSolicitudDAO;
/*     */ import sinco.data.HistoriaSolicitudDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.data.SolicitudDAO;
/*     */ import sinco.spec.Utilidades2;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PedirAplazamiento
/*     */   implements HttpPresentation
/*     */ {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException, Exception {
/*  29 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  30 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  32 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */ 
/*     */     
/*  35 */     int idsol = Integer.parseInt(comms.request.getParameter("solicitud"));
/*     */     
/*  37 */     String fecha = comms.request.getParameter("fecha");
/*  38 */     String justificacion = comms.request.getParameter("observacion");
/*     */     
/*  40 */     if (justificacion.trim().length() == 0) {
/*  41 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=JustificacionRequerida"));
/*     */     }
/*     */     
/*  44 */     SolicitudDAO sf = new SolicitudDAO();
/*  45 */     SolicitudDTO regSol = sf.getSolicitud(idsol);
/*  46 */     sf.close();
/*     */     
/*  48 */     Varios oVarios = new Varios();
/*  49 */     SisUsuariosDTO proveedor = oVarios.leerPersona(regSol.getEmpleadoProveedor());
/*  50 */     SisUsuariosDTO cliente = oVarios.leerPersona(regSol.getEmpleadoCliente());
/*     */ 
/*     */     
/*     */     try {
/*  54 */       if (!Utilidades.validarFormatoFecha(fecha)) {
/*  55 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=FechaNoValida&p1=digitada"));
/*     */       }
/*  57 */       GregorianCalendar gc = Utilidades.StringtoGC(fecha);
/*  58 */       if (Utilidades2.noHabil(gc)) {
/*  59 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=AplazamientoNoHabil"));
/*     */       }
/*     */       
/*  62 */       if (Utilidades.GCtoString(gc).compareTo(Utilidades.darFormatoFecha(regSol.getFechaEstimadaTerminacion())) < 0) {
/*  63 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=AplazamientoNoAntes"));
/*     */       }
/*     */     }
/*  66 */     catch (Exception e) {
/*  67 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=FormatoFechaNoValido"));
/*     */     } 
/*     */ 
/*     */     
/*  71 */     AplazamientosSolicitudDAO rsAplazamiento = new AplazamientosSolicitudDAO();
/*  72 */     rsAplazamiento.crearRegistro(idsol, justificacion, fecha, 0, elUsuario);
/*  73 */     rsAplazamiento.close();
/*     */ 
/*     */     
/*  76 */     ServiciosDAO rsServ = new ServiciosDAO();
/*  77 */     ServiciosDTO regServicio = rsServ.cargarRegistro(regSol.getCodigoServicio());
/*  78 */     rsServ.close();
/*     */     
/*  80 */     HistoriaSolicitudDAO hsf = new HistoriaSolicitudDAO();
/*  81 */     hsf.crearHistoria(regSol.getNumero(), regSol.getCodigoEstado(), regSol.getCodigoEstado(), "Aplazamiento pedido para la fecha " + fecha, elUsuario);
/*  82 */     hsf.close();
/*     */     
/*  84 */     AtencionSolicitudDAO asf = new AtencionSolicitudDAO();
/*  85 */     asf.crearAtencion(idsol, justificacion, Integer.parseInt((String)comms.session.getSessionData().get("miId")), elUsuario);
/*  86 */     asf.close();
/*     */ 
/*     */     
/*  89 */     String url = ParametrosDTO.getString("url.sistema") + "LU.po?t=m&l=" + cliente.getCodigoEmpleado() + "&p=" + cliente.getPassword() + "&h=VSEnCurso.po?solicitud=" + regSol.getNumero() + "\n\n";
/*     */ 
/*     */ 
/*     */     
/*  93 */     String mensaje = oVarios.formatMensaje("CorreoAplazamientoSolicitado", proveedor.getNombres() + " " + proveedor.getApellidos(), "" + regSol.getNumero() + " " + regServicio.getDescripcion(), Utilidades.darFormatoFecha(regSol.getFechaEstimadaTerminacion()), fecha, justificacion, url);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     String from = proveedor.getEmail();
/* 104 */     String to = cliente.getEmail();
/*     */     
/* 106 */     Utilidades.sendMail(ParametrosDTO.getString("servidor.correo"), from, to, "Aplazamiento pedido", mensaje);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=InformarCliente"));
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PedirAplazamiento.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */