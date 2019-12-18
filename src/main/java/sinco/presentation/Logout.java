/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.appserver.server.session.SessionException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import sinco.presentation.LogoutHTML;
/*    */ 
/*    */ public class Logout
/*    */   implements HttpPresentation {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 13 */     LogoutHTML pagHTML = (LogoutHTML)comms.xmlcFactory.create(LogoutHTML.class);
/*    */     try {
/* 15 */       comms.session.getSessionManager().deleteSession(comms.session.getSessionKey());
/*    */     }
/* 17 */     catch (SessionException e) {}
/* 18 */     comms.response.writeDOM(pagHTML);
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\Logout.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */