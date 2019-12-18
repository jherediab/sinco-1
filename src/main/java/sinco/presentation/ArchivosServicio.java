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
/*     */ import sinco.data.ServiciosDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArchivosServicio
/*     */   implements HttpPresentation
/*     */ {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  26 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  27 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  30 */     String elUsuario = "" + comms.session.getUser().getName();
/*  31 */     int codigo = 0;
/*  32 */     String fixedFileName = "anexo";
/*     */     
/*  34 */     File anexo = null;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
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
/*  52 */           chdr.getValue();
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/*  57 */         String contentName = null;
/*  58 */         if ((hdr = in.getHeader("Content-Disposition")) != null) {
/*  59 */           ContentHeader chdr = new ContentHeader(hdr);
/*  60 */           contentName = chdr.getParameter("name");
/*     */         } 
/*     */         
/*  63 */         if (contentName == null) contentName = "null"; 
/*  64 */         int count = 0;
/*  65 */         byte[] buffer = new byte[1024];
/*     */         
/*  67 */         if (contentName.equals("theFileName")) {
/*     */           
/*  69 */           StringBuffer sb = new StringBuffer(); int n;
/*  70 */           while ((n = in.read(buffer, 0, buffer.length)) > 0) {
/*  71 */             for (int i = 0; i < n; i++) {
/*  72 */               sb.append((char)(buffer[i] + 256 & 0xFF));
/*     */             }
/*     */           } 
/*  75 */           fixedFileName = new String(sb);
/*  76 */           if (fixedFileName.trim().equals("")) {
/*  77 */             fixedFileName = "anexo";
/*     */           }
/*     */           
/*  80 */           while (fixedFileName.indexOf("\\") != -1) {
/*  81 */             fixedFileName = fixedFileName.substring(fixedFileName.indexOf("\\") + 1);
/*     */           }
/*  83 */           while (fixedFileName.indexOf("/") != -1) {
/*  84 */             fixedFileName = fixedFileName.substring(fixedFileName.indexOf("/") + 1);
/*     */           }
/*     */           
/*  87 */           if (anexo != null) {
/*  88 */             fixedFileName = fixedFileName.replace('#', '_');
/*  89 */             anexo.renameTo(new File(fixedFileName));
/*     */           } 
/*     */         } 
/*     */         
/*  93 */         if (contentName.trim().equals("codigo")) {
/*  94 */           int n; while ((n = in.read(buffer, 0, buffer.length)) > 0);
/*     */           
/*  96 */           String temporal = new String(buffer);
/*  97 */           codigo = Integer.parseInt(temporal.trim());
/*  98 */           in.close();
/*     */         } 
/*     */         
/* 101 */         if (contentName.trim().equals("upload")) {
/* 102 */           if (fixedFileName.equals("")) {
/* 103 */             fixedFileName = "anexo";
/*     */           }
/* 105 */           ServiciosDAO asf = new ServiciosDAO();
/* 106 */           String nombreArchivo = "Serv_" + codigo + "_" + fixedFileName;
/* 107 */           anexo = new File(ParametrosDTO.getString("cal_archivos") + "/" + nombreArchivo);
/* 108 */           FileOutputStream out = new FileOutputStream(anexo);
/* 109 */           count = 0;
/*     */           int n;
/* 111 */           while ((n = in.read(buffer, 0, buffer.length)) > 0) {
/* 112 */             out.write(buffer, 0, n);
/* 113 */             count += n;
/*     */           } 
/* 115 */           in.close();
/* 116 */           out.close();
/*     */           
/* 118 */           if (count == 0) {
/*     */             try {
/* 120 */               anexo.delete();
/*     */             }
/* 122 */             catch (Exception e) {
/* 123 */               e.printStackTrace();
/*     */             } 
/* 125 */             anexo = null;
/*     */           } 
/* 127 */           if (anexo != null) {
/* 128 */             asf.modificarRegistroArchivo(codigo, nombreArchivo, elUsuario);
/*     */           }
/* 130 */           asf.close();
/*     */         }
/*     */       
/*     */       } 
/* 134 */     } catch (Exception e) {
/* 135 */       e.printStackTrace();
/*     */     } 
/* 137 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Servicios.po?_operacion=P&codigo=" + codigo));
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ArchivosServicio.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */