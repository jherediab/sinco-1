/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import sinco.presentation.PedirNombrePersonaHTML;
/*    */ import sinco.spec.MenuDO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PedirNombrePersona
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 23 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 24 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */     
/* 27 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/* 28 */     PedirNombrePersonaHTML pagHTML = (PedirNombrePersonaHTML)comms.xmlcFactory.create(PedirNombrePersonaHTML.class);
/* 29 */     pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 30 */     comms.response.writeDOM(pagHTML);
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PedirNombrePersona.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */