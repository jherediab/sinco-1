/*    */ package sinco.presentation;
/*    */ 
      import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import java.lang.Object.*;
//import com.lutris.airsent.presentation.*;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AMEstadistica
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 20 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 21 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */     
/* 24 */     int areaImplanta = 0;
/*    */     try {
/* 26 */       areaImplanta = Integer.parseInt(comms.request.getParameter("areaImplanta"));
/*    */     }
/* 28 */     catch (Exception e) {}
/*    */ 
/*    */     
/* 31 */     int tipo = 0;
/*    */     try {
/* 33 */       tipo = Integer.parseInt(comms.request.getParameter("tipo"));
/*    */     }
/* 35 */     catch (Exception e) {}
/*    */     
/* 37 */     int codigo_estado = 0;
/*    */     try {
/* 39 */       codigo_estado = Integer.parseInt(comms.request.getParameter("codigo_estado"));
/*    */     }
/* 41 */     catch (Exception e) {}
/*    */ 
/*    */     
/* 44 */     String operacion = comms.request.getParameter("_operacion");
/*    */     
/* 46 */     String fechaDesde = comms.request.getParameter("fechaDesde");
/* 47 */     String fechaHasta = comms.request.getParameter("fechaHasta");
/* 48 */     String accion = comms.request.getParameter("accion");
/* 49 */     String origen = comms.request.getParameter("origen");
/* 50 */     String proceso = comms.request.getParameter("proceso");
/* 51 */     String norma = comms.request.getParameter("norma");
/*    */     
/* 53 */     String pagina = operacion + "?areaImplanta=" + areaImplanta + "&fechaDesde=" + fechaDesde + "&fechaHasta=" + fechaHasta + "&tipo=" + tipo;
/* 54 */     pagina = pagina + "&codigo_estado=" + codigo_estado + "&accion=" + accion + "&origen=" + origen + "&proceso=" + proceso + "&norma=" + norma;
/*    */     
/* 56 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(pagina));
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AMEstadistica.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */