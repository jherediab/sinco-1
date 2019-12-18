/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationOutputStream;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import sinco.business.ParametrosDTO;
/*    */ import sinco.business.SisAyudaDTO;
/*    */ import sinco.business.Utilidades;
/*    */ import sinco.data.SisAyudaDAO;
/*    */ import sinco.presentation.VerAyuda;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VerAyuda
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException, Exception {
/* 28 */     String pantalla = comms.request.getParameter("pantalla");
/*    */     
/* 30 */     SisAyudaDAO rs = new SisAyudaDAO();
/* 31 */     SisAyudaDTO reg = rs.cargarRegistro(ParametrosDTO.getString("Aplicacion"), pantalla);
/* 32 */     rs.close();
/*    */     
/* 34 */     if (reg == null) {
/* 35 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoAyudaParaPantalla&p1=" + pantalla));
/*    */     }
/*    */     
/* 38 */     String extension = extensionArchivo(reg.getArchivoAnexo());
/*    */     
/* 40 */     String sNombreArchivo = ParametrosDTO.getString("archivos_ayuda") + "/" + ParametrosDTO.getString("Aplicacion") + "/" + reg.getArchivoAnexo();
/*    */     
/* 42 */     File file = new File(sNombreArchivo);
/*    */     
/* 44 */     if (!file.exists()) {
/* 45 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ArchivoNoExiste&texto=" + sNombreArchivo));
/*    */     }
/* 47 */     long bytesArchivo = file.length();
/*    */     
/* 49 */     comms.response.setContentType("application/" + extension);
/* 50 */     comms.response.setHeader("Content-Disposition", "inline;filename=" + reg.getArchivoAnexo());
/* 51 */     comms.response.setStatus(200, "Good job");
/* 52 */     comms.response.setContentLength((int)bytesArchivo);
/*    */     
/* 54 */     HttpPresentationOutputStream out = comms.response.getOutputStream();
/*    */ 
/*    */     
/*    */     try {
/* 58 */       FileInputStream in = new FileInputStream(sNombreArchivo);
/*    */       
/* 60 */       byte[] buffer = new byte[102400];
/*    */       
/* 62 */       int i = 0; int bytesRead;
/* 63 */       while ((bytesRead = in.read(buffer)) > 0) {
/* 64 */         out.write(buffer, 0, bytesRead);
/* 65 */         i++;
/*    */       } 
/* 67 */       in.close();
/*    */       
/* 69 */       out.flush();
/*    */ 
/*    */       
/*    */       return;
/* 73 */     } catch (Exception e) {
/* 74 */       e.printStackTrace();
/* 75 */       Utilidades.writeError("Descargando archivo ", e);
/* 76 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoArchivoAyuda&p1=" + pantalla));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private String extensionArchivo(String nombreArchivo) {
/*    */     try {
/* 89 */       String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1).toLowerCase();
/* 90 */       if (extension == null) {
/* 91 */         extension = "";
/*    */       }
/* 93 */       return extension;
/*    */     }
/* 95 */     catch (Exception e) {
/*    */ 
/*    */       
/* 98 */       return "pdf";
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\VerAyuda.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */