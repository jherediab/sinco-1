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
/*     */ import sinco.business.EstadoDTO;
/*     */ import sinco.business.FechaDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.business.VSolicitudesDTO;
/*     */ import sinco.data.ArchivosSolicitudDAO;
/*     */ import sinco.data.EstadoDAO;
/*     */ import sinco.data.VSolicitudesDAO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArchivosAnadir
/*     */   implements HttpPresentation
/*     */ {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  32 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  33 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  36 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  37 */     String elUsuario = "" + comms.session.getUser().getName();
/*  38 */     int idsol = 0;
/*  39 */     String pagina = "";
/*  40 */     String fixedFileName = "anexo";
/*     */     
/*  42 */     File anexo = null;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  47 */       String type = "";
/*  48 */       String contentType = comms.request.getContentType();
/*  49 */       if (contentType == null) {
/*  50 */         contentType = "";
/*     */       }
/*  52 */       ContentHeader contentHdr = new ContentHeader("Content-Type: " + contentType);
/*     */       
/*  54 */       HttpPresentationInputStream input = comms.request.getInputStream();
/*  55 */       MultipartMimeInput mime = new MultipartMimeInput(input, contentHdr);
/*     */       MultipartMimeInputStream in;
/*  57 */       while ((in = mime.nextPart()) != null) {
/*  58 */         MimeHeader hdr = in.getHeader("Content-Type");
/*  59 */         if (hdr != null) {
/*  60 */           ContentHeader chdr = new ContentHeader(hdr);
/*  61 */           type = chdr.getValue();
/*     */         } else {
/*     */           
/*  64 */           type = "null";
/*     */         } 
/*     */         
/*  67 */         String contentName = null;
/*  68 */         if ((hdr = in.getHeader("Content-Disposition")) != null) {
/*  69 */           ContentHeader chdr = new ContentHeader(hdr);
/*  70 */           contentName = chdr.getParameter("name");
/*     */         } 
/*     */         
/*  73 */         if (contentName == null) contentName = "null"; 
/*  74 */         int count = 0;
/*  75 */         byte[] buffer = new byte[1024];
/*     */         
/*  77 */         if (contentName.equals("theFileName")) {
/*     */           
/*  79 */           StringBuffer sb = new StringBuffer(); int n;
/*  80 */           while ((n = in.read(buffer, 0, buffer.length)) > 0) {
/*  81 */             for (int i = 0; i < n; i++) {
/*  82 */               sb.append((char)(buffer[i] + 256 & 0xFF));
/*     */             }
/*     */           } 
/*  85 */           fixedFileName = new String(sb);
/*  86 */           if (fixedFileName.trim().equals("")) {
/*  87 */             fixedFileName = "anexo";
/*     */           }
/*     */           
/*  90 */           while (fixedFileName.indexOf("\\") != -1) {
/*  91 */             fixedFileName = fixedFileName.substring(fixedFileName.indexOf("\\") + 1);
/*     */           }
/*  93 */           while (fixedFileName.indexOf("/") != -1) {
/*  94 */             fixedFileName = fixedFileName.substring(fixedFileName.indexOf("/") + 1);
/*     */           }
/*     */           
/*  97 */           if (anexo != null) {
/*  98 */             fixedFileName = fixedFileName.replace('#', '_');
/*  99 */             anexo.renameTo(new File(fixedFileName));
/*     */           } 
/*     */         } 
/*     */         
/* 103 */         if (contentName.trim().equals("solicitud")) {
/* 104 */           int n; while ((n = in.read(buffer, 0, buffer.length)) > 0);
/*     */           
/* 106 */           String temporal = new String(buffer);
/* 107 */           idsol = Integer.parseInt(temporal.trim());
/* 108 */           in.close();
/*     */         } 
/*     */         
/* 111 */         if (contentName.trim().equals("pagina_siguiente")) {
/* 112 */           int n; while ((n = in.read(buffer, 0, buffer.length)) > 0);
/*     */           
/* 114 */           pagina = new String(buffer);
/* 115 */           pagina = pagina.trim();
/* 116 */           in.close();
/*     */         } 
/*     */         
/* 119 */         if (contentName.trim().equals("upload")) {
/* 120 */           if (fixedFileName.equals("")) {
/* 121 */             fixedFileName = "anexo";
/*     */           }
/* 123 */           ArchivosSolicitudDAO asf = new ArchivosSolicitudDAO();
/* 124 */           int siguiente = asf.siguiente(idsol);
/* 125 */           String nombreArchivo = idsol + "_" + siguiente + "_" + fixedFileName;
/*     */           
/* 127 */           FechaDTO oFecha = new FechaDTO(Utilidades.fechaActual());
/* 128 */           String toPath = Utilidades.verificarDirectorioDestino(ParametrosDTO.getString("archivos"), oFecha.getAnno(), oFecha.getMes());
/*     */           
/* 130 */           anexo = new File(toPath + "/" + nombreArchivo);
/*     */           
/* 132 */           FileOutputStream out = new FileOutputStream(anexo);
/* 133 */           count = 0;
/*     */           int n;
/* 135 */           while ((n = in.read(buffer, 0, buffer.length)) > 0) {
/* 136 */             out.write(buffer, 0, n);
/* 137 */             count += n;
/*     */           } 
/* 139 */           in.close();
/* 140 */           out.close();
/*     */           
/* 142 */           if (count == 0) {
/*     */             try {
/* 144 */               anexo.delete();
/*     */             }
/* 146 */             catch (Exception e) {
/* 147 */               e.printStackTrace();
/*     */             } 
/* 149 */             anexo = null;
/*     */           } 
/* 151 */           if (anexo != null) {
/* 152 */             if (count > ParametrosDTO.getFloat("tamano_archivos") * 1024.0F * 1024.0F) {
/* 153 */               anexo.delete();
/* 154 */               asf.close();
/* 155 */               throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ArchivoMuyGrande&p1=" + (count / 1024 / 1024) + "&p2=" + ParametrosDTO.getFloat("tamano_archivos")));
/*     */             } 
/* 157 */             asf.crearArchivo(idsol, nombreArchivo, count, elUsuario);
/*     */           } 
/* 159 */           asf.close();
/*     */         }
/*     */       
/*     */       } 
/* 163 */     } catch (Exception e) {
/* 164 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 167 */     VSolicitudesDAO sf = new VSolicitudesDAO();
/* 168 */     VSolicitudesDTO regSol = sf.getSolicitud(idsol);
/* 169 */     sf.close();
/*     */     
/* 171 */     EstadoDAO estadof = new EstadoDAO();
/* 172 */     EstadoDTO estado = estadof.getEstado(regSol.getCodigoEstado());
/* 173 */     estadof.close();
/*     */     
/* 175 */     if (!estado.getTipoEstado().trim().equals("INI")) {
/* 176 */       Varios oVarios = new Varios();
/* 177 */       oVarios.mensajeArchivo(idNav, regSol, anexo);
/*     */     } 
/* 179 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("PreArchivos.po?solicitud=" + idsol + "&pagina_siguiente=" + pagina));
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ArchivosAnadir.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */