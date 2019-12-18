/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PreCrearEncuesta
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 16 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 17 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */     
/* 20 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/* 21 */     int codigo_servicio = Integer.parseInt(comms.request.getParameter("servicio"));
/*    */     
/* 23 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("CrearEncuesta.po?servicio=" + codigo_servicio));
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PreCrearEncuesta.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */