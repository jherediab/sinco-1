/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationOutputStream;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import sinco.business.DocumentosDTO;
/*    */ import sinco.data.DocumentosDAO;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VerDocumento
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 18 */     String numeroDocumento = comms.request.getParameter("numeroDocumento");
/* 19 */     String tipoDocumento = comms.request.getParameter("tipoDocumento");
/*    */     
/* 21 */     DocumentosDAO rs = new DocumentosDAO();
/* 22 */     if (!rs.getEstadoConexion()) {
/* 23 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*    */     }
/* 25 */     DocumentosDTO reg = rs.cargarRegistro(tipoDocumento, numeroDocumento);
/*    */     
/* 27 */     if (reg == null) {
/* 28 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorLeyendoDocumento"));
/*    */     }
/* 30 */     rs.close();
/* 31 */     comms.response.setContentType("application/rtf");
/*    */     
/* 33 */     comms.response.setHeader("Content-Disposition", "inline;filename=" + reg.getTipoDocumento() + "_" + reg.getNumeroDocumento() + ".rtf");
/* 34 */     comms.response.setStatus(200, "Good job");
/*    */     
/* 36 */     HttpPresentationOutputStream out = comms.response.getOutputStream();
/*    */     try {
/* 38 */       byte[] byteContents = reg.getDocumento().getBytes();
/* 39 */       out.write(byteContents);
/* 40 */       out.flush();
/* 41 */       out.close();
/*    */     }
/* 43 */     catch (Exception e) {
/* 44 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=" + e.getMessage()));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\VerDocumento.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */