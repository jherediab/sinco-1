/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import sinco.presentation.DigiteNumeroHTML;
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
/*    */ 
/*    */ 
/*    */ public class DigiteNumero
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 25 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 26 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/* 28 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*    */     
/* 30 */     String tipo = comms.request.getParameter("tipo");
/*    */     
/* 32 */     DigiteNumeroHTML pagHTML = (DigiteNumeroHTML)comms.xmlcFactory.create(DigiteNumeroHTML.class);
/*    */     
/* 34 */     if (tipo.equals("accion")) {
/* 35 */       pagHTML.setTextTitulo("Ver Acción Número");
/* 36 */       pagHTML.getElementIdTipo().setValue("accion");
/*    */     } 
/* 38 */     pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 39 */     comms.response.writeDOM(pagHTML);
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\DigiteNumero.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */