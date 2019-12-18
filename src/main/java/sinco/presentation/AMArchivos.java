/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationInputStream;
/*     */ import com.lutris.mime.ContentHeader;
/*     */ import com.lutris.mime.MimeHeader;
/*     */ import com.lutris.mime.MultipartMimeInput;
/*     */ import com.lutris.mime.MultipartMimeInputStream;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.data.ArchivosSolicitudDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AMArchivos
/*     */   implements HttpPresentation
/*     */ {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  26 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  27 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  30 */     String elUsuario = "" + comms.session.getUser().getName();
/*  31 */     int numero = 0;
/*  32 */     int causa = 0;
/*  33 */     String fixedFileName = "anexo";
/*     */     
/*  35 */     File anexo = null;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  40 */       String type = "";
/*  41 */       String contentType = comms.request.getContentType();
/*  42 */       if (contentType == null) {
/*  43 */         contentType = "";
/*     */       }
/*  45 */       ContentHeader contentHdr = new ContentHeader("Content-Type: " + contentType);
/*     */       
/*  47 */       HttpPresentationInputStream input = comms.request.getInputStream();
/*  48 */       MultipartMimeInput mime = new MultipartMimeInput(input, contentHdr);
/*     */       MultipartMimeInputStream in;
/*  50 */       while ((in = mime.nextPart()) != null) {
/*  51 */         MimeHeader hdr = in.getHeader("Content-Type");
/*  52 */         if (hdr != null) {
/*  53 */           ContentHeader chdr = new ContentHeader(hdr);
/*  54 */           type = chdr.getValue();
/*     */         } else {
/*     */           
/*  57 */           type = "null";
/*     */         } 
/*     */         
/*  60 */         String contentName = null;
/*  61 */         if ((hdr = in.getHeader("Content-Disposition")) != null) {
/*  62 */           ContentHeader chdr = new ContentHeader(hdr);
/*  63 */           contentName = chdr.getParameter("name");
/*     */         } 
/*     */         
/*  66 */         if (contentName == null) contentName = "null"; 
/*  67 */         int count = 0;
/*  68 */         byte[] buffer = new byte[1024];
/*     */         
/*  70 */         if (contentName.equals("theFileName")) {
/*     */           
/*  72 */           StringBuffer sb = new StringBuffer(); int n;
/*  73 */           while ((n = in.read(buffer, 0, buffer.length)) > 0) {
/*  74 */             for (int i = 0; i < n; i++) {
/*  75 */               sb.append((char)(buffer[i] + 256 & 0xFF));
/*     */             }
/*     */           } 
/*  78 */           fixedFileName = new String(sb);
/*  79 */           if (fixedFileName.trim().equals("")) {
/*  80 */             fixedFileName = "anexo";
/*     */           }
/*     */           
/*  83 */           while (fixedFileName.indexOf("\\") != -1) {
/*  84 */             fixedFileName = fixedFileName.substring(fixedFileName.indexOf("\\") + 1);
/*     */           }
/*  86 */           while (fixedFileName.indexOf("/") != -1) {
/*  87 */             fixedFileName = fixedFileName.substring(fixedFileName.indexOf("/") + 1);
/*     */           }
/*     */           
/*  90 */           if (anexo != null) {
/*  91 */             fixedFileName = fixedFileName.replace('#', '_');
/*  92 */             anexo.renameTo(new File(fixedFileName));
/*     */           } 
/*     */         } 
/*     */         
/*  96 */         if (contentName.trim().equals("numero")) {
/*  97 */           int n; while ((n = in.read(buffer, 0, buffer.length)) > 0);
/*     */           
/*  99 */           String temporal = new String(buffer);
/* 100 */           numero = Integer.parseInt(temporal.trim());
/* 101 */           in.close();
/*     */         } 
/* 103 */         if (contentName.trim().equals("causa")) {
/* 104 */           int n; while ((n = in.read(buffer, 0, buffer.length)) > 0);
/*     */           
/* 106 */           String temporal = new String(buffer);
/* 107 */           causa = Integer.parseInt(temporal.trim());
/* 108 */           in.close();
/*     */         } 
/*     */         
/* 111 */         if (contentName.trim().equals("upload")) {
/* 112 */           if (fixedFileName.equals("")) {
/* 113 */             fixedFileName = "anexo";
/*     */           }
/* 115 */           ArchivosSolicitudDAO asf = new ArchivosSolicitudDAO();
/* 116 */           int siguiente = asf.siguienteAccion(numero);
/* 117 */           String nombreArchivo = "AM_" + numero + "_" + siguiente + "_" + fixedFileName;
/* 118 */           anexo = new File(ParametrosDTO.getString("archivos_acciones") + "/" + nombreArchivo);
/*     */           
/* 120 */           FileOutputStream out = new FileOutputStream(anexo);
/* 121 */           count = 0;
/*     */           int n;
/* 123 */           while ((n = in.read(buffer, 0, buffer.length)) > 0) {
/* 124 */             out.write(buffer, 0, n);
/* 125 */             count += n;
/*     */           } 
/* 127 */           in.close();
/* 128 */           out.close();
/*     */           
/* 130 */           if (count == 0) {
/*     */             try {
/* 132 */               anexo.delete();
/*     */             }
/* 134 */             catch (Exception e) {
/* 135 */               e.printStackTrace();
/*     */             } 
/* 137 */             anexo = null;
/*     */           } 
/* 139 */           if (anexo != null) {
/* 140 */             asf.crearArchivoAccion(numero, siguiente, causa, nombreArchivo, elUsuario);
/*     */           }
/* 142 */           asf.close();
/*     */         }
/*     */       
/*     */       } 
/* 146 */     } catch (Exception e) {
/* 147 */       e.printStackTrace();
/*     */     } 
/* 149 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("AMPreArchivos.po?numero=" + numero + "&causa=" + causa));
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AMArchivos.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */