/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import java.util.Enumeration;
/*    */ import sinco.data.DetalleEncuestaDAO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AnadirDetalleEncuesta
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 19 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 20 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */     
/* 23 */     int idsol = Integer.parseInt(comms.request.getParameter("encuesta"));
/* 24 */     String elUsuario = "" + comms.session.getUser().getName();
/*    */     
/* 26 */     Enumeration enumera = comms.request.getParameterNames();
/* 27 */     DetalleEncuestaDAO dsf = new DetalleEncuestaDAO();
/*    */     
/* 29 */     while (enumera.hasMoreElements()) {
/* 30 */       String param = (String)enumera.nextElement();
/* 31 */       if (!param.equals("encuesta") && !param.equals("pagina") && !param.equals("miBoton")) {
/* 32 */         int caracteristicacodigo = Integer.parseInt(param);
/* 33 */         String caracteristicavalor = comms.request.getParameter("" + caracteristicacodigo);
/* 34 */         if (caracteristicavalor != null && !caracteristicavalor.trim().equals("")) {
/* 35 */           dsf.crearDetalle(idsol, caracteristicacodigo, caracteristicavalor, elUsuario);
/*    */         }
/*    */       } 
/*    */     } 
/* 39 */     dsf.close();
/* 40 */     String pagina = comms.request.getParameter("pagina") + "?encuesta=" + idsol;
/* 41 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(pagina));
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AnadirDetalleEncuesta.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */