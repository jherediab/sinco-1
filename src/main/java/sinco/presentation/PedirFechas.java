/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import org.w3c.dom.html.HTMLFormElement;
/*    */ import sinco.presentation.PedirFechas;
/*    */ import sinco.presentation.PedirFechasHTML;
/*    */ import sinco.spec.MenuDO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PedirFechas
/*    */   implements HttpPresentation
/*    */ {
/*    */   private PedirFechasHTML pagHTML;
/*    */   
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 25 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 26 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/* 28 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*    */     
/* 30 */     this.pagHTML = (PedirFechasHTML)comms.xmlcFactory.create(PedirFechasHTML.class);
/*    */     
/* 32 */     String pagina = comms.request.getParameter("pagina");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 42 */     HTMLFormElement forma = this.pagHTML.getElementForma();
/* 43 */     forma.setAction(pagina);
/*    */     
/* 45 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 46 */     comms.response.writeDOM(this.pagHTML);
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PedirFechas.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */