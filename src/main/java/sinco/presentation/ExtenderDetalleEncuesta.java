/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import sinco.data.DetalleEncuestaDAO;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExtenderDetalleEncuesta
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 16 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 17 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */     
/* 20 */     int idsol = Integer.parseInt(comms.request.getParameter("encuesta"));
/* 21 */     String elUsuario = "" + comms.session.getUser().getName();
/*    */     
/* 23 */     DetalleEncuestaDAO dsf = new DetalleEncuestaDAO();
/*    */     
/* 25 */     String caracteristicavalor = comms.request.getParameter("observacion");
/* 26 */     int caracteristicacodigo = Integer.parseInt(comms.request.getParameter("extensiones"));
/* 27 */     if (caracteristicavalor != null && !caracteristicavalor.trim().equals("")) {
/* 28 */       dsf.crearDetalle(idsol, caracteristicacodigo, caracteristicavalor, elUsuario);
/*    */     }
/* 30 */     dsf.close();
/* 31 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("LlenarEncuesta.po?encuesta=" + idsol));
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ExtenderDetalleEncuesta.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */