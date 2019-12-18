/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import sinco.business.SolicitudDTO;
/*    */ import sinco.data.SolicitudDAO;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SolicitudHija
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 17 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 18 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */ 
/*    */     
/* 22 */     int nroSol = Integer.parseInt(comms.request.getParameter("solicitud"));
/*    */     
/* 24 */     SolicitudDAO sf = new SolicitudDAO();
/* 25 */     SolicitudDTO regSol = sf.getSolicitud(nroSol);
/* 26 */     sf.close();
/*    */     
/* 28 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("TASetTipo.po?padre=" + nroSol));
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SolicitudHija.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */