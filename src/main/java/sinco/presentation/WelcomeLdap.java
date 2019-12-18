/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import sinco.presentation.WelcomeLdapHTML;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WelcomeLdap
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 20 */     WelcomeLdapHTML pagHTML = (WelcomeLdapHTML)comms.xmlcFactory.create(WelcomeLdapHTML.class);
/* 21 */     comms.response.writeDOM(pagHTML);
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\WelcomeLdap.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */