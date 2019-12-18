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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SetFormaGeneral
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 19 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 20 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/* 22 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*    */ 
/*    */     
/* 25 */     String titulo = comms.request.getParameter("titulo");
/* 26 */     String tabla = comms.request.getParameter("tabla");
/* 27 */     String campo1 = comms.request.getParameter("campo1");
/* 28 */     String campo2 = comms.request.getParameter("campo2");
/*    */     
/* 30 */     int numerico = 0;
/*    */     try {
/* 32 */       numerico = Integer.parseInt(comms.request.getParameter("numerico"));
/*    */     }
/* 34 */     catch (Exception e) {
/* 35 */       numerico = 0;
/*    */     } 
/*    */     
/* 38 */     int size1 = 0;
/*    */     try {
/* 40 */       size1 = Integer.parseInt(comms.request.getParameter("size1"));
/*    */     }
/* 42 */     catch (Exception e) {
/* 43 */       size1 = 8;
/*    */     } 
/*    */     
/* 46 */     int size2 = 0;
/*    */     try {
/* 48 */       size2 = Integer.parseInt(comms.request.getParameter("size2"));
/*    */     }
/* 50 */     catch (Exception e) {
/* 51 */       size2 = 50;
/*    */     } 
/*    */     
/* 54 */     comms.session.getSessionData().remove("titulo");
/* 55 */     comms.session.getSessionData().remove("tabla");
/* 56 */     comms.session.getSessionData().remove("campo1");
/* 57 */     comms.session.getSessionData().remove("campo2");
/* 58 */     comms.session.getSessionData().remove("numerico");
/* 59 */     comms.session.getSessionData().remove("size1");
/* 60 */     comms.session.getSessionData().remove("size2");
/*    */     
/* 62 */     comms.session.getSessionData().set("titulo", "" + titulo);
/* 63 */     comms.session.getSessionData().set("tabla", "" + tabla);
/* 64 */     comms.session.getSessionData().set("campo1", "" + campo1);
/* 65 */     comms.session.getSessionData().set("campo2", "" + campo2);
/* 66 */     comms.session.getSessionData().set("numerico", "" + numerico);
/* 67 */     comms.session.getSessionData().set("size1", "" + size1);
/* 68 */     comms.session.getSessionData().set("size2", "" + size2);
/* 69 */     comms.session.getSessionData().remove("filtro");
/*    */     
/* 71 */     String pagina = "MantenimientoFormaGeneral.po";
/* 72 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(pagina));
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SetFormaGeneral.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */