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
/*    */ import sinco.business.Utilidades;
/*    */ import sinco.data.ArchivosSolicitudDAO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArchivosBorrar
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 23 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 24 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/* 26 */     int idsol = Integer.parseInt(comms.request.getParameter("solicitud"));
/* 27 */     String origen = comms.request.getParameter("origen");
/*    */     
/* 29 */     Enumeration enumera = comms.request.getParameterNames();
/*    */ 
/*    */     
/* 32 */     ArchivosSolicitudDAO asf = new ArchivosSolicitudDAO();
/* 33 */     int contador = 0;
/* 34 */     while (enumera.hasMoreElements()) {
/* 35 */       String param = (String)enumera.nextElement();
/* 36 */       if (!param.equals("solicitud") && !param.equals("origen")) {
/* 37 */         int archivonro = Integer.parseInt(param);
/* 38 */         asf.getArchivosSolicitud(idsol, archivonro);
/* 39 */         ArchivosSolicitudDTO asv = asf.next();
/* 40 */         if (asv != null) {
/*    */           
/* 42 */           String sFecha = Utilidades.darFormatoFecha(asv.getFechaInsercion());
/*    */           
/* 44 */           if (sFecha != null) {
/* 45 */             FechaDTO oFecha = new FechaDTO(sFecha);
/* 46 */             String sRutaBase = ParametrosDTO.getString("archivos") + "/" + oFecha.getAnno() + "/" + Utilidades.nombreMes(oFecha.getMes());
/* 47 */             File anexo = new File(sRutaBase);
/* 48 */             if (anexo.exists()) {
/* 49 */               anexo.delete();
/* 50 */               asf.borrarArchivo(idsol, archivonro);
/* 51 */               contador++;
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/* 57 */     asf.close();
/*    */     
/* 59 */     String sPagina = "";
/* 60 */     if (origen.equals("A")) {
/* 61 */       sPagina = "VSPorAt.po?solicitud=" + idsol;
/*    */     }
/* 63 */     else if (origen.equals("C")) {
/* 64 */       sPagina = "VSEnCurso.po?solicitud=" + idsol;
/*    */     } else {
/*    */       
/* 67 */       sPagina = "VerSolicitudNoEnviada.po?solicitud=" + idsol;
/*    */     } 
/*    */     
/* 70 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ArchivosBorrar.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */