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
/*     */ public class CrearSolicitud
/*     */   implements HttpPresentation
/*     */ {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  22 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  23 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */ 
/*     */     
/*  27 */     int laPersona = 0;
/*     */     try {
/*  29 */       laPersona = Integer.parseInt(comms.request.getParameter("persona"));
/*     */     }
/*  31 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  34 */     if (laPersona == 0) {
/*  35 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=SeleccionarElemento"));
/*     */     }
/*     */     
/*  38 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */     
/*  40 */     int elServicio = -1;
/*     */     try {
/*  42 */       elServicio = Integer.parseInt(comms.request.getParameter("servicio"));
/*     */     }
/*  44 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  47 */     int elPadre = -1;
/*     */     try {
/*  49 */       elPadre = Integer.parseInt(comms.request.getParameter("padre"));
/*     */     }
/*  51 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  54 */     int laArea = -1;
/*     */     try {
/*  56 */       laArea = Integer.parseInt(comms.request.getParameter("area"));
/*     */     }
/*  58 */     catch (Exception e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  64 */     SisUsuariosDAO pf = new SisUsuariosDAO();
/*     */     
/*  66 */     SisUsuariosDTO cliente = pf.cargarRegistro(Integer.parseInt((String)comms.session.getSessionData().get("miId")));
/*  67 */     SisUsuariosDTO proveedor = pf.cargarRegistro(laPersona);
/*     */     
/*  69 */     if (proveedor == null) {
/*  70 */       String sPagina = "TASetTipo.po?servicio=" + elServicio + "&padre=" + elPadre + "&area=" + laArea + "&mensaje=No Existe Proveedor";
/*  71 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/*  73 */     if (!proveedor.getEstado().equals("A")) {
/*  74 */       String sPagina = "TASetTipo.po?servicio=" + elServicio + "&padre=" + elPadre + "&area=" + laArea + "&mensaje=Proveedor Inactivo";
/*  75 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/*     */     
/*  78 */     int areaCliente = cliente.getArea();
/*     */     try {
/*  80 */       areaCliente = Integer.parseInt(comms.request.getParameter("areaCliente"));
/*     */     }
/*  82 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  85 */     ServiciosDAO serf = new ServiciosDAO();
/*  86 */     ServiciosDTO servicio = serf.cargarRegistro(elServicio);
/*  87 */     serf.close();
/*     */ 
/*     */ 
/*     */     
/*  91 */     int num = 1;
/*     */     
/*  93 */     SolicitudDAO sf = new SolicitudDAO();
/*  94 */     String ciclo = "";
/*  95 */     num = sf.crearSolicitud(areaCliente, cliente.getCodigoEmpleado(), elServicio, laArea, laPersona, elPadre, ciclo, servicio.getDuracion(), servicio.getUnidadMedida(), null, 0, elUsuario, servicio.getArchivoAnexo());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 110 */     sf.close();
/* 111 */     if (num == 0) {
/* 112 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorActualizandoTabla&p1=Solicitudes"));
/*     */     }
/*     */     
/* 115 */     comms.session.getSessionData().remove("ts");
/*     */ 
/*     */     
/* 118 */     String pagina = "VerSolicitudNoEnviada.po?solicitud=" + num;
/* 119 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(pagina));
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CrearSolicitud.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */