/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import sinco.business.VSolicitudesDTO;
/*    */ import sinco.data.VSolicitudesDAO;
/*    */ import sinco.spec.Varios;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecordarCalificacion
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 19 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 20 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */ 
/*    */     
/* 24 */     int numeroSolicitud = Integer.parseInt(comms.request.getParameter("solicitud"));
/* 25 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/* 26 */     String elUsuario = "" + comms.session.getUser().getName();
/*    */     
/* 28 */     VSolicitudesDAO rsVSol = new VSolicitudesDAO();
/* 29 */     VSolicitudesDTO regSol = rsVSol.getSolicitud(numeroSolicitud);
/* 30 */     rsVSol.close();
/*    */     
/* 32 */     Varios oVarios = new Varios();
/* 33 */     oVarios.recordarCalificacion(1, idNav, regSol, elUsuario);
/* 34 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=Afirmacion2"));
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\RecordarCalificacion.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */