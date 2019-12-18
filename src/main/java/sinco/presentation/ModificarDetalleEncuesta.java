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
/*    */ public class ModificarDetalleEncuesta
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 18 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 19 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */     
/* 22 */     String elUsuario = "" + comms.session.getUser().getName();
/* 23 */     int idsol = Integer.parseInt(comms.request.getParameter("encuesta"));
/* 24 */     String Pagina = comms.request.getParameter("pagina_siguiente") + "?encuesta=" + idsol;
/* 25 */     Enumeration enumera = comms.request.getParameterNames();
/* 26 */     DetalleEncuestaDAO dsf = new DetalleEncuestaDAO();
/*    */     
/* 28 */     while (enumera.hasMoreElements()) {
/* 29 */       String param = (String)enumera.nextElement();
/* 30 */       if (!param.equals("encuesta") && !param.equals("pagina_siguiente") && !param.equals("miBoton")) {
/*    */         
/* 32 */         int caracteristica = Integer.parseInt(param.substring(0, param.indexOf("_")));
/* 33 */         int consecutivo = Integer.parseInt(param.substring(param.indexOf("_") + 1, param.length()));
/* 34 */         String caracteristicavalor = comms.request.getParameter(param);
/* 35 */         dsf.actualizarDetalle(idsol, caracteristica, consecutivo, caracteristicavalor, elUsuario);
/*    */       } 
/*    */     } 
/* 38 */     dsf.close();
/*    */     
/* 40 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(Pagina));
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ModificarDetalleEncuesta.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */