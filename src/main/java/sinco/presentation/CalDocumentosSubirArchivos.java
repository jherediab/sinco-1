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
/*     */ import sinco.business.CalDocumentosDTO;
/*     */ import sinco.business.CalObjetivosDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CalDocumentosDAO;
/*     */ import sinco.data.CalObjetivosDAO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalDocumentosSubirArchivos
/*     */   implements HttpPresentation
/*     */ {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  29 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  30 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  33 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  34 */     String elUsuario = "" + comms.session.getUser().getName();
/*  35 */     String fixedFileName = "anexo";
/*  36 */     String codigoDocumento = "";
/*  37 */     String tipoDocumento = "";
/*  38 */     String doc = "";
/*  39 */     String objetivoHidden = "";
/*     */ 
/*     */ 
/*     */     
/*  43 */     File anexo = null;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  48 */       String type = "";
/*  49 */       String contentType = comms.request.getContentType();
/*  50 */       if (contentType == null) {
/*  51 */         contentType = "";
/*     */       }
/*  53 */       ContentHeader contentHdr = new ContentHeader("Content-Type: " + contentType);
/*     */       
/*  55 */       HttpPresentationInputStream input = comms.request.getInputStream();
/*  56 */       MultipartMimeInput mime = new MultipartMimeInput(input, contentHdr);
/*     */       MultipartMimeInputStream in;
/*  58 */       while ((in = mime.nextPart()) != null) {
/*  59 */         MimeHeader hdr = in.getHeader("Content-Type");
/*  60 */         if (hdr != null) {
/*  61 */           ContentHeader chdr = new ContentHeader(hdr);
/*  62 */           type = chdr.getValue();
/*     */         } else {
/*     */           
/*  65 */           type = "null";
/*     */         } 
/*     */         
/*  68 */         String contentName = null;
/*  69 */         if ((hdr = in.getHeader("Content-Disposition")) != null) {
/*  70 */           ContentHeader chdr = new ContentHeader(hdr);
/*  71 */           contentName = chdr.getParameter("name");
/*     */         } 
/*     */         
/*  74 */         if (contentName == null) contentName = "null"; 
/*  75 */         int count = 0;
/*  76 */         byte[] buffer = new byte[1024];
/*     */         
/*  78 */         if (contentName.equals("theFileName")) {
/*     */           
/*  80 */           StringBuffer sb = new StringBuffer(); int n;
/*  81 */           while ((n = in.read(buffer, 0, buffer.length)) > 0) {
/*  82 */             for (int i = 0; i < n; i++) {
/*  83 */               sb.append((char)(buffer[i] + 256 & 0xFF));
/*     */             }
/*     */           } 
/*  86 */           fixedFileName = new String(sb);
/*  87 */           if (fixedFileName.trim().equals("")) {
/*  88 */             fixedFileName = "anexo";
/*     */           }
/*     */           
/*  91 */           while (fixedFileName.indexOf("\\") != -1) {
/*  92 */             fixedFileName = fixedFileName.substring(fixedFileName.indexOf("\\") + 1);
/*     */           }
/*  94 */           while (fixedFileName.indexOf("/") != -1) {
/*  95 */             fixedFileName = fixedFileName.substring(fixedFileName.indexOf("/") + 1);
/*     */           }
/*     */ 
/*     */           
/*  99 */           if (fixedFileName != null) {
/* 100 */             fixedFileName = Utilidades.removerAcentos(fixedFileName);
/*     */           }
/*     */         } 
/* 103 */         if (contentName.trim().equals("codigoDocumento")) {
/* 104 */           int n; while ((n = in.read(buffer, 0, buffer.length)) > 0);
/*     */           
/* 106 */           String temporal = new String(buffer);
/* 107 */           codigoDocumento = temporal.trim();
/* 108 */           in.close();
/*     */         } 
/*     */         
/* 111 */         if (contentName.trim().equals("tipoDocumento")) {
/* 112 */           int n; while ((n = in.read(buffer, 0, buffer.length)) > 0);
/*     */           
/* 114 */           String temporal = new String(buffer);
/* 115 */           tipoDocumento = temporal.trim();
/* 116 */           in.close();
/*     */         } 
/*     */         
/* 119 */         if (contentName.trim().equals("upload")) {
/* 120 */           if (fixedFileName.equals("")) {
/* 121 */             fixedFileName = "anexo";
/*     */           }
/* 123 */           count = 0;
/*     */           
/* 125 */           String nombreArchivo = codigoDocumento;
/* 126 */           anexo = new File(ParametrosDTO.getString("cal_archivos") + "/" + fixedFileName);
/*     */ 
/*     */           
/* 129 */           FileOutputStream out = new FileOutputStream(anexo);
/* 130 */           count = 0;
/*     */           int n;
/* 132 */           while ((n = in.read(buffer, 0, buffer.length)) > 0) {
/* 133 */             out.write(buffer, 0, n);
/* 134 */             count += n;
/*     */           } 
/* 136 */           out.close();
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
/* 156 */           in.close();
/*     */           
/* 158 */           if (count > 0)
/*     */           {
/* 160 */             CalObjetivosDAO obj = new CalObjetivosDAO();
/* 161 */             CalDocumentosDAO rs = new CalDocumentosDAO();
/* 162 */             rs.actualizarAnexos(codigoDocumento, tipoDocumento, fixedFileName, elUsuario);
/*     */             
/* 164 */             CalDocumentosDTO reg = rs.cargarDocumento(codigoDocumento);
/* 165 */             CalObjetivosDTO objDTO = obj.cargarRegistroPorProceso(reg.getProceso());
/*     */             
/* 167 */             objetivoHidden = Integer.toString(objDTO.getCodigoObjetivo());
/* 168 */             doc = reg.getAsociado_a();
/*     */ 
/*     */ 
/*     */             
/* 172 */             obj.close();
/* 173 */             rs.close();
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/* 178 */     } catch (Exception e) {
/* 179 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 182 */     if (doc == null || doc.length() == 0) doc = ""; 
/* 183 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("CalDocumentosAct.po?_operacion=P&Doc=" + doc + "&codigo=" + codigoDocumento + "&objetivoHidden=" + objetivoHidden));
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalDocumentosSubirArchivos.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */