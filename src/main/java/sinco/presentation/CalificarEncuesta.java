/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import sinco.data.EncuestaDAO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CalificarEncuesta
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 18 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 19 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */     
/* 22 */     String elUsuario = "" + comms.session.getUser().getName();
/*    */     
/* 24 */     int encuesta = Integer.parseInt(comms.request.getParameter("encuesta"));
/* 25 */     String justificacion = comms.request.getParameter("observacion");
/* 26 */     String confiabilidad = comms.request.getParameter("confiabilidad");
/* 27 */     if (confiabilidad.equals("X")) {
/* 28 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=SeleccionarElemento"));
/*    */     }
/* 30 */     EncuestaDAO rsEncuesta = new EncuestaDAO();
/* 31 */     if (confiabilidad.equals("R") && justificacion.equals("")) {
/* 32 */       rsEncuesta.close();
/* 33 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=JustificarCalificacion"));
/*    */     } 
/* 35 */     if (justificacion.equals("")) {
/* 36 */       justificacion = "Calificacion Masiva";
/*    */     }
/* 38 */     rsEncuesta.calificarEncuesta(encuesta, confiabilidad, justificacion, elUsuario);
/* 39 */     rsEncuesta.close();
/* 40 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ServicioMultipleCalificado"));
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalificarEncuesta.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */