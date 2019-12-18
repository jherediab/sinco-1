/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.data.SolicitudDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HelpDeskF2
/*     */   implements HttpPresentation
/*     */ {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  22 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  23 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  26 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */     
/*  28 */     int elServicio = 0;
/*     */     try {
/*  30 */       elServicio = Integer.parseInt(comms.request.getParameter("servicio"));
/*     */     }
/*  32 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  35 */     int laPersona = -1;
/*     */     try {
/*  37 */       laPersona = Integer.parseInt(comms.request.getParameter("persona"));
/*     */     }
/*  39 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  42 */     int laArea = -1;
/*     */     try {
/*  44 */       laArea = Integer.parseInt(comms.request.getParameter("area"));
/*     */     }
/*  46 */     catch (Exception e) {}
/*     */ 
/*     */ 
/*     */     
/*  50 */     ServiciosDAO serf = new ServiciosDAO();
/*  51 */     ServiciosDTO servicio = serf.cargarRegistro(elServicio);
/*  52 */     serf.close();
/*     */     
/*  54 */     SisUsuariosDAO pf = new SisUsuariosDAO();
/*  55 */     SisUsuariosDTO proveedor = pf.cargarRegistro(Integer.parseInt((String)comms.session.getSessionData().get("miId")));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  72 */     int num = 1;
/*     */     
/*  74 */     SolicitudDAO sf = new SolicitudDAO();
/*  75 */     String ciclo = "";
/*     */     
/*  77 */     num = sf.crearSolicitud(laArea, laPersona, elServicio, proveedor.getArea(), proveedor.getCodigoEmpleado(), -1, ciclo, servicio.getDuracion(), servicio.getUnidadMedida(), null, 0, elUsuario, servicio.getArchivoAnexo());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  92 */     sf.close();
/*  93 */     if (num == 0) {
/*  94 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorActualizandoTabla&p1=Solicitudes"));
/*     */     }
/*     */ 
/*     */     
/*  98 */     String pagina = "VerSolicitudNoEnviada.po?solicitud=" + num + "&helpDesk=1";
/*     */     
/* 100 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(pagina));
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\HelpDeskF2.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */