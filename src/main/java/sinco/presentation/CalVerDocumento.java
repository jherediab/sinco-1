/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationOutputStream;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.presentation.CalVerDocumento;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalVerDocumento
/*     */   implements HttpPresentation
/*     */ {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  27 */     String _operacion = comms.request.getParameter("_operacion");
/*     */     
/*  29 */     if (_operacion == null) {
/*  30 */       _operacion = "X";
/*     */     }
/*  32 */     String numeroDocumento = comms.request.getParameter("numeroDocumento");
/*  33 */     String tipoDocumento = comms.request.getParameter("tipoDocumento");
/*     */ 
/*     */     
/*  36 */     verDocumentoCalidad(comms, tipoDocumento, numeroDocumento);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void verDocumentoCalidad(HttpPresentationComms comms, String tipoDocumento, String numeroDocumento) throws HttpPresentationException, KeywordValueException {
/*  61 */     HttpPresentationOutputStream out = comms.response.getOutputStream();
/*     */     
/*  63 */     String extension = extensionArchivo(numeroDocumento);
/*     */     
/*  65 */     String sRuta = ParametrosDTO.getString("cal_archivos") + "/" + numeroDocumento;
/*     */     
/*  67 */     comms.response.setContentType("application/" + extension);
/*  68 */     comms.response.setHeader("Content-Disposition", "inline;filename=" + numeroDocumento);
/*  69 */     comms.response.setStatus(200, "Good job");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  75 */       File file = new File(sRuta);
/*  76 */       long bytesArchivo = file.length();
/*     */       
/*  78 */       comms.response.setContentLength((int)bytesArchivo);
/*     */ 
/*     */       
/*  81 */       FileInputStream fileInput = new FileInputStream(sRuta);
/*  82 */       BufferedInputStream bufferedInput = new BufferedInputStream(fileInput);
/*     */       
/*  84 */       byte[] buffer = new byte[ParametrosDTO.getInt("tamano_bloque")];
/*     */       
/*  86 */       long bytesTotales = 0L;
/*  87 */       int i = 0; int bytesRead;
/*  88 */       while ((bytesRead = bufferedInput.read(buffer)) > 0) {
/*  89 */         out.write(buffer, 0, bytesRead);
/*  90 */         bytesTotales += bytesRead;
/*  91 */         i++;
/*     */       } 
/*  93 */       fileInput.close();
/*     */       
/*  95 */       out.flush();
/*  96 */       Utilidades.writeError(comms.session.getUser().getName() + " Descarga " + bytesTotales + " Bytes  del archivo " + sRuta + " (" + bytesArchivo + " Bytes)");
/*     */     
/*     */     }
/*  99 */     catch (Exception e) {
/* 100 */       e.printStackTrace();
/* 101 */       Utilidades.writeError("Descargando archivo " + e.getMessage(), e);
/* 102 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorDescargaArchivo&texto=" + e.getMessage()));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String extensionArchivo(String nombreArchivo) {
/*     */     try {
/* 116 */       String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1).toLowerCase();
/* 117 */       if (extension == null) {
/* 118 */         extension = "";
/*     */       }
/* 120 */       return extension;
/*     */     }
/* 122 */     catch (Exception e) {
/*     */ 
/*     */       
/* 125 */       return "jpg";
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalVerDocumento.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */