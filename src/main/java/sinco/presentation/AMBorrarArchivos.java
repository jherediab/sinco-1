/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import java.util.Enumeration;
/*    */ import sinco.data.ArchivosSolicitudDAO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AMBorrarArchivos
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 19 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 20 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/* 22 */     int numero = 0;
/*    */     try {
/* 24 */       numero = Integer.parseInt(comms.request.getParameter("numero"));
/*    */     }
/* 26 */     catch (Exception e) {
/* 27 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=numero"));
/*    */     } 
/*    */     
/* 30 */     int causa = 0;
/*    */     try {
/* 32 */       causa = Integer.parseInt(comms.request.getParameter("causa"));
/*    */     }
/* 34 */     catch (Exception e) {
/* 35 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=causa"));
/*    */     } 
/* 37 */     String sPagina = comms.request.getParameter("pagina");
/*    */     
/* 39 */     Enumeration enumera = comms.request.getParameterNames();
/*    */ 
/*    */     
/* 42 */     ArchivosSolicitudDAO asf = new ArchivosSolicitudDAO();
/*    */     
/* 44 */     while (enumera.hasMoreElements()) {
/* 45 */       String param = (String)enumera.nextElement();
/* 46 */       if (!param.equals("numero") && !param.equals("causa") && !param.equals("pagina")) {
/* 47 */         int archivonro = Integer.parseInt(param);
/*    */         
/* 49 */         asf.borrarArchivoAccion(numero, archivonro, causa);
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 56 */     asf.close();
/* 57 */     sPagina = sPagina + "?numero=" + numero + "&causa=" + causa;
/* 58 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AMBorrarArchivos.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */