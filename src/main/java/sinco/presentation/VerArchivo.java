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
/*     */ import sinco.business.FechaDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.presentation.VerArchivo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VerArchivo
/*     */   implements HttpPresentation
/*     */ {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  29 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  30 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  33 */     String sFileName = comms.request.getParameter("archivo");
/*  34 */     String sRuta = comms.request.getParameter("ruta");
/*  35 */     String extension = extensionArchivo(sFileName);
/*     */     
/*  37 */     if (sRuta == null) {
/*  38 */       sRuta = "archivos";
/*     */     }
/*     */     
/*  41 */     String sRutaBase = ParametrosDTO.getString(sRuta);
/*  42 */     if (sRuta.equals("archivos")) {
/*  43 */       String sFecha = comms.request.getParameter("fecha");
/*     */       
/*  45 */       if (sFecha != null) {
/*  46 */         FechaDTO oFecha = new FechaDTO(sFecha);
/*  47 */         sRutaBase = sRutaBase + "/" + oFecha.getAnno() + "/" + Utilidades.nombreMes(oFecha.getMes());
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  52 */     String sNombreArchivo = sRutaBase + "/" + sFileName;
/*     */     
/*  54 */     comms.response.setContentType("application/" + extension);
/*  55 */     comms.response.setHeader("Content-Disposition", "inline;filename=" + sFileName);
/*     */     
/*  57 */     comms.response.setStatus(200, "Good job");
/*     */ 
/*     */     
/*  60 */     HttpPresentationOutputStream out = comms.response.getOutputStream();
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  65 */       File file = new File(sNombreArchivo);
/*     */       
/*  67 */       if (!file.exists()) {
/*  68 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ArchivoNoExiste&texto=" + sFileName));
/*     */       }
/*  70 */       long bytesArchivo = file.length();
/*     */       
/*  72 */       comms.response.setContentLength((int)bytesArchivo);
/*     */       
/*  74 */       FileInputStream in = new FileInputStream(sNombreArchivo);
/*  75 */       BufferedInputStream bufferedInput = new BufferedInputStream(in);
/*  76 */       byte[] buffer = new byte[ParametrosDTO.getInt("tamano_bloque")];
/*     */       
/*  78 */       long bytesTotales = 0L;
/*  79 */       int i = 0; int bytesRead;
/*  80 */       while ((bytesRead = bufferedInput.read(buffer)) > 0) {
/*  81 */         out.write(buffer, 0, bytesRead);
/*  82 */         bytesTotales += bytesRead;
/*  83 */         i++;
/*     */       } 
/*  85 */       in.close();
/*     */       
/*  87 */       out.flush();
/*     */       
/*  89 */       Utilidades.writeError(comms.session.getUser().getName() + " Descarga " + bytesTotales + " Bytes  del archivo " + sNombreArchivo + " (" + bytesArchivo + " Bytes)");
/*     */       
/*     */       return;
/*  92 */     } catch (Exception e) {
/*  93 */       e.printStackTrace();
/*  94 */       Utilidades.writeError("Descargando archivo ", e);
/*  95 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=" + e.getMessage()));
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
/*     */   private String extensionArchivo(String nombreArchivo) {
/*     */     try {
/* 108 */       String extension = nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1).toLowerCase();
/* 109 */       if (extension == null) {
/* 110 */         extension = "";
/*     */       }
/* 112 */       return extension;
/*     */     }
/* 114 */     catch (Exception e) {
/*     */ 
/*     */       
/* 117 */       return "jpg";
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\VerArchivo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */