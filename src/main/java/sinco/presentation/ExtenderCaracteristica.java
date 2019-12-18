/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import sinco.data.DetalleSolicitudDAO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExtenderCaracteristica
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 17 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 18 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */     
/* 21 */     int idsol = Integer.parseInt(comms.request.getParameter("solicitud"));
/* 22 */     String elUsuario = "" + comms.session.getUser().getName();
/*    */     
/* 24 */     int helpDesk = 0;
/*    */     try {
/* 26 */       helpDesk = Integer.parseInt(comms.request.getParameter("helpDesk"));
/*    */     }
/* 28 */     catch (Exception e) {}
/*    */     
/* 30 */     String pagina = comms.request.getParameter("pagina");
/*    */     
/* 32 */     DetalleSolicitudDAO dsf = new DetalleSolicitudDAO();
/*    */     
/* 34 */     String caracteristicavalor = comms.request.getParameter("observacion");
/* 35 */     int caracteristicacodigo = Integer.parseInt(comms.request.getParameter("extensiones"));
/* 36 */     if (caracteristicavalor != null && !caracteristicavalor.trim().equals("")) {
/* 37 */       dsf.crearDetalle(idsol, caracteristicacodigo, caracteristicavalor, elUsuario);
/*    */     }
/*    */     
/* 40 */     dsf.close();
/* 41 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(pagina + "?solicitud=" + idsol + "&helpDesk=" + helpDesk));
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ExtenderCaracteristica.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */