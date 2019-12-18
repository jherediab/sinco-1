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
/*     */ import sinco.business.CaracteristicasDTO;
/*     */ import sinco.business.EncuestaDTO;
/*     */ import sinco.business.FechaDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ArchivosSolicitudDAO;
/*     */ import sinco.data.CaracteristicasDAO;
/*     */ import sinco.data.DetalleEncuestaDAO;
/*     */ import sinco.data.EncuestaDAO;
/*     */ import sinco.data.EncuestadosDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EnviarEncuesta
/*     */   implements HttpPresentation
/*     */ {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  38 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  39 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */ 
/*     */     
/*  43 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */     
/*  45 */     EncuestaDAO rsEncuesta = new EncuestaDAO();
/*  46 */     EncuestaDTO regEncuesta = null;
/*  47 */     int miEncuesta = 0;
/*     */     
/*  49 */     String fixedFileName = "anexo";
/*  50 */     File anexo = null;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  55 */       String type = "";
/*  56 */       String contentType = comms.request.getContentType();
/*  57 */       if (contentType == null) {
/*  58 */         contentType = "";
/*     */       }
/*  60 */       ContentHeader contentHdr = new ContentHeader("Content-Type: " + contentType);
/*     */       
/*  62 */       HttpPresentationInputStream input = comms.request.getInputStream();
/*  63 */       MultipartMimeInput mime = new MultipartMimeInput(input, contentHdr);
/*     */       MultipartMimeInputStream in;
/*  65 */       while ((in = mime.nextPart()) != null) {
/*  66 */         MimeHeader hdr = in.getHeader("Content-Type");
/*  67 */         if (hdr != null) {
/*  68 */           ContentHeader chdr = new ContentHeader(hdr);
/*  69 */           type = chdr.getValue();
/*     */         } else {
/*     */           
/*  72 */           type = "null";
/*     */         } 
/*     */         
/*  75 */         String contentName = null;
/*  76 */         if ((hdr = in.getHeader("Content-Disposition")) != null) {
/*  77 */           ContentHeader chdr = new ContentHeader(hdr);
/*  78 */           contentName = chdr.getParameter("name");
/*     */         } 
/*     */         
/*  81 */         if (contentName == null) contentName = "null"; 
/*  82 */         int count = 0;
/*  83 */         byte[] buffer = new byte[1024];
/*     */         
/*  85 */         if (contentName.equals("theFileName")) {
/*     */           
/*  87 */           StringBuffer sb = new StringBuffer(); int n;
/*  88 */           while ((n = in.read(buffer, 0, buffer.length)) > 0) {
/*  89 */             for (int i = 0; i < n; i++) {
/*  90 */               sb.append((char)(buffer[i] + 256 & 0xFF));
/*     */             }
/*     */           } 
/*  93 */           fixedFileName = new String(sb);
/*  94 */           if (fixedFileName.trim().equals("")) {
/*  95 */             fixedFileName = "anexo";
/*     */           }
/*     */           
/*  98 */           while (fixedFileName.indexOf("\\") != -1) {
/*  99 */             fixedFileName = fixedFileName.substring(fixedFileName.indexOf("\\") + 1);
/*     */           }
/* 101 */           while (fixedFileName.indexOf("/") != -1) {
/* 102 */             fixedFileName = fixedFileName.substring(fixedFileName.indexOf("/") + 1);
/*     */           }
/*     */           
/* 105 */           if (anexo != null) {
/* 106 */             fixedFileName = fixedFileName.replace('#', '_');
/* 107 */             anexo.renameTo(new File(fixedFileName));
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 112 */         if (contentName.trim().equals("encuesta")) {
/* 113 */           int n; while ((n = in.read(buffer, 0, buffer.length)) > 0);
/*     */           
/* 115 */           String temporal = new String(buffer);
/* 116 */           miEncuesta = Integer.parseInt(temporal.trim());
/* 117 */           regEncuesta = rsEncuesta.cargaRegistro(miEncuesta);
/* 118 */           in.close();
/*     */         } 
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
/* 130 */         if (contentName.trim().equals("upload")) {
/* 131 */           if (fixedFileName.equals("")) {
/* 132 */             fixedFileName = "anexo";
/*     */           }
/* 134 */           String nombreArchivo = "SM_" + miEncuesta + "_" + fixedFileName;
/*     */           
/* 136 */           FechaDTO oFecha = new FechaDTO(Utilidades.fechaActual());
/* 137 */           String toPath = Utilidades.verificarDirectorioDestino(ParametrosDTO.getString("archivos"), oFecha.getAnno(), oFecha.getMes());
/* 138 */           anexo = new File(toPath + "/" + nombreArchivo);
/*     */ 
/*     */           
/* 141 */           FileOutputStream out = new FileOutputStream(anexo);
/* 142 */           count = 0;
/*     */           int n;
/* 144 */           while ((n = in.read(buffer, 0, buffer.length)) > 0) {
/* 145 */             out.write(buffer, 0, n);
/* 146 */             count += n;
/*     */           } 
/* 148 */           in.close();
/* 149 */           out.close();
/*     */           
/* 151 */           if (count == 0) {
/*     */             try {
/* 153 */               anexo.delete();
/*     */             }
/* 155 */             catch (Exception e) {
/* 156 */               e.printStackTrace();
/*     */             } 
/* 158 */             anexo = null;
/*     */           } 
/*     */           
/* 161 */           if (anexo != null) {
/* 162 */             ArchivosSolicitudDAO asf = new ArchivosSolicitudDAO();
/* 163 */             asf.crearEncuesta(miEncuesta, nombreArchivo, elUsuario);
/* 164 */             asf.close();
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/* 169 */     } catch (Exception e) {
/* 170 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 173 */     ServiciosDAO serf = new ServiciosDAO();
/* 174 */     ServiciosDTO regServicio = serf.cargarRegistro(regEncuesta.getCodigo_servicio());
/* 175 */     serf.close();
/*     */     
/* 177 */     if (regServicio == null) {
/* 178 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ServicioNoDisponible"));
/*     */     }
/*     */     
/* 181 */     if (!regServicio.getTipoServicio().equals(Integer.toString(2))) {
/*     */       
/* 183 */       CaracteristicasDAO rsCaracteristica = new CaracteristicasDAO();
/* 184 */       rsCaracteristica.cargarTodosParaServicioObligatorias(regEncuesta.getCodigo_servicio(), "C");
/* 185 */       CaracteristicasDTO car = rsCaracteristica.next();
/*     */       
/* 187 */       while (car != null) {
/* 188 */         DetalleEncuestaDAO dsf = new DetalleEncuestaDAO();
/* 189 */         dsf.cargarParaSolicitud(regEncuesta.getNumero(), car.getCodigo());
/* 190 */         if (dsf.next() == null) {
/* 191 */           rsCaracteristica.close();
/* 192 */           dsf.close();
/* 193 */           rsEncuesta.close();
/* 194 */           ArchivosSolicitudDAO asf = new ArchivosSolicitudDAO();
/* 195 */           asf.borrarArchivoEncuesta(miEncuesta);
/* 196 */           asf.close();
/* 197 */           throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=CamposObligatorios"));
/*     */         } 
/* 199 */         dsf.close();
/* 200 */         car = rsCaracteristica.next();
/*     */       } 
/* 202 */       rsCaracteristica.close();
/*     */     } 
/*     */     
/* 205 */     EncuestadosDAO rsEncuestados = new EncuestadosDAO();
/* 206 */     int cuantos = rsEncuestados.cuantos(miEncuesta);
/* 207 */     rsEncuestados.close();
/* 208 */     if (cuantos == 0) {
/* 209 */       rsEncuesta.close();
/* 210 */       ArchivosSolicitudDAO asf = new ArchivosSolicitudDAO();
/* 211 */       asf.borrarArchivoEncuesta(miEncuesta);
/* 212 */       asf.close();
/* 213 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=InsertarProveedores"));
/*     */     } 
/*     */     
/* 216 */     RespuestaBD rtaBD = rsEncuesta.enviarEncuesta(miEncuesta, 2, regServicio.getDescripcion(), elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 223 */     rsEncuesta.close();
/*     */ 
/*     */     
/* 226 */     if (rtaBD.isRta()) {
/* 227 */       Varios oVarios = new Varios();
/* 228 */       oVarios.EnviarSisCorreos(miEncuesta, "E", elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 233 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ServicioMultipleEnviado"));
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\EnviarEncuesta.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */