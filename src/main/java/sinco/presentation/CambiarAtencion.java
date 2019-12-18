/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import java.util.Enumeration;
/*    */ import sinco.data.AtencionSolicitudDAO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CambiarAtencion
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 19 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 20 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */     
/* 23 */     int idsol = Integer.parseInt(comms.request.getParameter("solicitud"));
/* 24 */     String elUsuario = "" + comms.session.getUser().getName();
/*    */     
/* 26 */     String sPagina = comms.request.getParameter("pagina_siguiente") + "?solicitud=" + idsol;
/* 27 */     Enumeration enumera = comms.request.getParameterNames();
/* 28 */     AtencionSolicitudDAO asf = new AtencionSolicitudDAO();
/*    */     
/* 30 */     while (enumera.hasMoreElements()) {
/* 31 */       String param = (String)enumera.nextElement();
/* 32 */       if (!param.equals("solicitud") && !param.equals("pagina_siguiente") && !param.equals("miBoton")) {
/* 33 */         int consecutivo = Integer.parseInt(param);
/* 34 */         String detallevalor = comms.request.getParameter(param);
/* 35 */         asf.actualizarDetalle(idsol, consecutivo, detallevalor, elUsuario);
/*    */       } 
/*    */     } 
/* 38 */     asf.close();
/*    */     
/* 40 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CambiarAtencion.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */