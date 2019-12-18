/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import java.util.Enumeration;
/*    */ import java.util.regex.Pattern;
/*    */ import sinco.business.ServiciosDTO;
/*    */ import sinco.business.VSolicitudesDTO;
/*    */ import sinco.data.AtencionSolicitudDAO;
/*    */ import sinco.data.DetalleSolicitudDAO;
/*    */ import sinco.data.ServiciosDAO;
/*    */ import sinco.data.VSolicitudesDAO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CambiarCaracteristica
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 25 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 26 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */     
/* 29 */     int idsol = Integer.parseInt(comms.request.getParameter("solicitud"));
/* 30 */     String elUsuario = "" + comms.session.getUser().getName();
/*    */     
/* 32 */     String pagina = comms.request.getParameter("pagina_siguiente") + "?solicitud=" + idsol;
/* 33 */     Enumeration enumera = comms.request.getParameterNames();
/* 34 */     DetalleSolicitudDAO dsf = new DetalleSolicitudDAO();
/*    */     
/* 36 */     while (enumera.hasMoreElements()) {
/* 37 */       String param = (String)enumera.nextElement();
/* 38 */       if (param.startsWith("C_")) {
/*    */         
/* 40 */         Pattern p = Pattern.compile("_");
/* 41 */         String[] arregloDatos = p.split(param);
/*    */         
/* 43 */         int caracteristica = Integer.parseInt(arregloDatos[1]);
/* 44 */         int consecutivo = Integer.parseInt(arregloDatos[2]);
/* 45 */         String caracteristicavalor = comms.request.getParameter(param);
/* 46 */         dsf.actualizarDetalle(idsol, caracteristica, consecutivo, caracteristicavalor, elUsuario);
/*    */       } 
/*    */     } 
/* 49 */     dsf.close();
/*    */ 
/*    */ 
/*    */     
/* 53 */     VSolicitudesDAO sf = new VSolicitudesDAO();
/* 54 */     VSolicitudesDTO regSol = sf.getSolicitud(idsol);
/* 55 */     sf.close();
/*    */     
/* 57 */     if (regSol != null) {
/*    */       
/* 59 */       ServiciosDAO serf = new ServiciosDAO();
/* 60 */       ServiciosDTO regServicio = serf.cargarRegistro(regSol.getCodigoServicio());
/* 61 */       serf.close();
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 68 */     AtencionSolicitudDAO asf = new AtencionSolicitudDAO();
/*    */     
/* 70 */     enumera = comms.request.getParameterNames();
/* 71 */     while (enumera.hasMoreElements()) {
/* 72 */       String param = (String)enumera.nextElement();
/* 73 */       if (param.startsWith("A_")) {
/*    */         
/* 75 */         Pattern p = Pattern.compile("_");
/* 76 */         String[] arregloDatos = p.split(param);
/*    */         
/* 78 */         int consecutivo = Integer.parseInt(arregloDatos[1]);
/*    */         
/* 80 */         String detallevalor = comms.request.getParameter(param);
/*    */         
/* 82 */         asf.actualizarDetalle(idsol, consecutivo, detallevalor, elUsuario);
/*    */       } 
/*    */     } 
/*    */     
/* 86 */     asf.close();
/*    */     
/* 88 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(pagina));
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CambiarCaracteristica.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */