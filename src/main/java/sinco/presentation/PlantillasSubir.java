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
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.SisPlantillasDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlantillasSubir
/*     */   implements HttpPresentation
/*     */ {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  24 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  25 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  28 */     String elUsuario = "" + comms.session.getUser().getName();
/*  29 */     String fixedFileName = "anexo";
/*  30 */     String codigo = "";
/*     */ 
/*     */     
/*  33 */     File anexo = null;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  38 */       String type = "";
/*  39 */       String contentType = comms.request.getContentType();
/*  40 */       if (contentType == null) {
/*  41 */         contentType = "";
/*     */       }
/*  43 */       ContentHeader contentHdr = new ContentHeader("Content-Type: " + contentType);
/*     */       
/*  45 */       HttpPresentationInputStream input = comms.request.getInputStream();
/*  46 */       MultipartMimeInput mime = new MultipartMimeInput(input, contentHdr);
/*     */       MultipartMimeInputStream in;
/*  48 */       while ((in = mime.nextPart()) != null) {
/*  49 */         MimeHeader hdr = in.getHeader("Content-Type");
/*  50 */         if (hdr != null) {
/*  51 */           ContentHeader chdr = new ContentHeader(hdr);
/*  52 */           type = chdr.getValue();
/*     */         } else {
/*     */           
/*  55 */           type = "null";
/*     */         } 
/*     */         
/*  58 */         String contentName = null;
/*  59 */         if ((hdr = in.getHeader("Content-Disposition")) != null) {
/*  60 */           ContentHeader chdr = new ContentHeader(hdr);
/*  61 */           contentName = chdr.getParameter("name");
/*     */         } 
/*     */         
/*  64 */         if (contentName == null) contentName = "null"; 
/*  65 */         int count = 0;
/*  66 */         byte[] buffer = new byte[1024];
/*     */         
/*  68 */         if (contentName.equals("theFileName")) {
/*     */           
/*  70 */           StringBuffer sb = new StringBuffer(); int n;
/*  71 */           while ((n = in.read(buffer, 0, buffer.length)) > 0) {
/*  72 */             for (int i = 0; i < n; i++) {
/*  73 */               sb.append((char)(buffer[i] + 256 & 0xFF));
/*     */             }
/*     */           } 
/*  76 */           fixedFileName = new String(sb);
/*  77 */           if (fixedFileName.trim().equals("")) {
/*  78 */             fixedFileName = "anexo";
/*     */           }
/*     */           
/*  81 */           while (fixedFileName.indexOf("\\") != -1) {
/*  82 */             fixedFileName = fixedFileName.substring(fixedFileName.indexOf("\\") + 1);
/*     */           }
/*  84 */           while (fixedFileName.indexOf("/") != -1) {
/*  85 */             fixedFileName = fixedFileName.substring(fixedFileName.indexOf("/") + 1);
/*     */           }
/*     */         } 
/*  88 */         if (contentName.trim().equals("codigo")) {
/*  89 */           int n; while ((n = in.read(buffer, 0, buffer.length)) > 0);
/*     */           
/*  91 */           String temporal = new String(buffer);
/*  92 */           codigo = temporal.trim();
/*  93 */           in.close();
/*     */         } 
/*     */         
/*  96 */         if (contentName.trim().equals("upload")) {
/*  97 */           if (fixedFileName.equals("")) {
/*  98 */             fixedFileName = "anexo";
/*     */           }
/* 100 */           count = 0;
/* 101 */           byte[] archivo = null; int n;
/* 102 */           while ((n = in.read(buffer, 0, buffer.length)) > 0) {
/*     */             
/* 104 */             if (count == 0) {
/* 105 */               archivo = new byte[n];
/* 106 */               System.arraycopy(buffer, 0, archivo, 0, n);
/*     */             } else {
/*     */               
/* 109 */               byte[] old = new byte[count];
/* 110 */               System.arraycopy(archivo, 0, old, 0, count);
/* 111 */               archivo = new byte[n + count];
/* 112 */               System.arraycopy(old, 0, archivo, 0, count);
/* 113 */               System.arraycopy(buffer, 0, archivo, count, n);
/*     */             } 
/* 115 */             count += n;
/*     */           } 
/* 117 */           in.close();
/*     */           
/* 119 */           if (count > 0) {
/* 120 */             SisPlantillasDAO rs = new SisPlantillasDAO();
/* 121 */             rs.actualizar(codigo, archivo);
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/* 126 */     } catch (Exception e) {
/* 127 */       e.printStackTrace();
/* 128 */       Utilidades.writeError("Subir Plantilla", e);
/*     */     } 
/* 130 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("SisPlantillas.po?_operacion=V&codigo=" + codigo));
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PlantillasSubir.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */