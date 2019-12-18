/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import java.io.File;
/*    */ import java.util.Enumeration;
/*    */ import sinco.business.ArchivosSolicitudDTO;
/*    */ import sinco.business.FechaDTO;
/*    */ import sinco.business.ParametrosDTO;
/*    */ import sinco.business.SisUsuariosDTO;
/*    */ import sinco.business.Utilidades;
/*    */ import sinco.data.ArchivosSolicitudDAO;
/*    */ import sinco.data.SisUsuariosDAO;
/*    */ import sinco.spec.Varios;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArchivosEnviarMail
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 26 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 27 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/* 29 */     int idsol = Integer.parseInt(comms.request.getParameter("solicitud"));
/*    */     
/* 31 */     Enumeration enumera = comms.request.getParameterNames();
/*    */ 
/*    */     
/* 34 */     SisUsuariosDAO perf = new SisUsuariosDAO();
/* 35 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/* 36 */     SisUsuariosDTO elNavegante = perf.cargarRegistro(idNav);
/*    */     
/* 38 */     ArchivosSolicitudDAO asf = new ArchivosSolicitudDAO();
/*    */     
/* 40 */     int contador = 0;
/* 41 */     while (enumera.hasMoreElements()) {
/* 42 */       String param = (String)enumera.nextElement();
/* 43 */       if (!param.equals("solicitud")) {
/* 44 */         int archivonro = Integer.parseInt(param);
/* 45 */         asf.getArchivosSolicitud(idsol, archivonro);
/* 46 */         ArchivosSolicitudDTO asv = asf.next();
/* 47 */         if (asv != null) {
/* 48 */           FechaDTO oFecha = new FechaDTO(Utilidades.darFormatoFecha(asv.getFechaInsercion()));
/* 49 */           File anexo = new File(ParametrosDTO.getString("archivos") + "/" + oFecha.getAnno() + "/" + Utilidades.nombreMes(oFecha.getMes()) + "/" + asv.getArchivo());
/*    */           
/* 51 */           if (anexo.exists()) {
/*    */             
/* 53 */             Varios oVarios = new Varios();
/* 54 */             String mensaje = oVarios.formatMensaje("SolicitudConArchivo", "" + idsol, asv.getArchivo());
/*    */             
/* 56 */             String from = elNavegante.getEmail();
/*    */             
/* 58 */             Utilidades.sendMail(ParametrosDTO.getString("servidor.correo"), from, from, "Archivo Solicitado anexo", mensaje, anexo);
/* 59 */             contador++;
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/* 64 */     asf.close();
/* 65 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ArchivosEnvidados&p1=" + contador));
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ArchivosEnviarMail.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */