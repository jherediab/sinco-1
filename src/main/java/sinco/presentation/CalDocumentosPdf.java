/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationOutputStream;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import sinco.business.CalDocumentosDTO;
/*     */ import sinco.business.CalProcesosDTO;
/*     */ import sinco.data.CalDocumentosDAO;
/*     */ import sinco.data.CalProcesosDAO;
/*     */ import sinco.spec.PdfListaMaestra;
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
/*     */ public class CalDocumentosPdf
/*     */   implements HttpPresentation
/*     */ {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException, Exception {
/*  31 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  32 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion&texto=Su sesion no ha sido iniciada o ha caducado. Regresa a la pagina principal e ingrese su login y password de nuevo"));
/*     */     }
/*     */     
/*  35 */     comms.response.setContentType("application/pdf");
/*  36 */     comms.response.setHeader("Content-Disposition", "inline;filename=ListaMaestra.pdf");
/*  37 */     comms.response.setStatus(200, "Good job");
/*     */     
/*  39 */     comms.response.setHeader("Expires", "0");
/*  40 */     comms.response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
/*  41 */     comms.response.setHeader("Pragma", "public");
/*     */     
/*  43 */     HttpPresentationOutputStream out = comms.response.getOutputStream();
/*     */ 
/*     */ 
/*     */     
/*  47 */     CalProcesosDAO rsProc = new CalProcesosDAO();
/*  48 */     if (!rsProc.getEstadoConexion()) {
/*  49 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*  51 */     Collection arrProc = rsProc.cargarProcesosDocumentos();
/*  52 */     rsProc.close();
/*     */     
/*  54 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*     */     
/*  56 */     PdfListaMaestra miPdf = new PdfListaMaestra();
/*  57 */     if (!miPdf.abrirArchivo(baos)) {
/*  58 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoEscribePdf&texto=Abrir Archivo"));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  63 */     CalProcesosDAO rsSubProc = new CalProcesosDAO();
/*  64 */     if (!rsSubProc.getEstadoConexion()) {
/*  65 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */ 
/*     */     
/*  69 */     CalDocumentosDAO rsDoc = new CalDocumentosDAO();
/*  70 */     if (!rsDoc.getEstadoConexion()) {
/*  71 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/*  74 */     CalDocumentosDAO rs = new CalDocumentosDAO();
/*  75 */     if (!rs.getEstadoConexion()) {
/*  76 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/*  79 */     miPdf.escribirCabecera();
/*     */     
/*  81 */     Iterator iteratorProc = arrProc.iterator();
/*  82 */     int i = 1;
/*  83 */     while (iteratorProc.hasNext()) {
/*  84 */       CalProcesosDTO regP = (CalProcesosDTO)iteratorProc.next();
/*     */       
/*  86 */       miPdf.nombreProceso(regP.getProceso() + " " + regP.getDescripcion());
/*     */       
/*  88 */       Collection arrSubProc = rsSubProc.cargarSubProcesosDocumentos(regP.getProceso());
/*     */ 
/*     */       
/*  91 */       Iterator iteratorSubProc = arrSubProc.iterator();
/*  92 */       while (iteratorSubProc.hasNext()) {
/*  93 */         CalProcesosDTO regSP = (CalProcesosDTO)iteratorSubProc.next();
/*  94 */         miPdf.nombreSubProceso(regSP.getSubProceso() + " " + regSP.getDescripcion());
/*     */         
/*  96 */         Collection arrDoc = rsDoc.cargarDocumentosDeProceso(regP.getProceso(), regSP.getSubProceso(), "L");
/*     */ 
/*     */         
/*  99 */         Iterator iteratorDoc = arrDoc.iterator();
/* 100 */         while (iteratorDoc.hasNext()) {
/* 101 */           CalDocumentosDTO regDoc = (CalDocumentosDTO)iteratorDoc.next();
/*     */           
/* 103 */           Collection arrDistribuido = rs.cargarDistribuido(regDoc.getCodigo());
/* 104 */           Collection arrResponsables = rs.cargarResponsables(regDoc.getCodigo());
/*     */ 
/*     */           
/* 107 */           miPdf.detalle(i, (regSP.getSubProceso().length() == 0 || regDoc.getTipoDocumento().equals("A")) ? "No aplica" : regDoc.getCodigo(), regDoc.getCodigo() + " " + regDoc.getDescripcion(), regDoc.getVersion(), regDoc.getFechaVersion(), arrDistribuido, arrResponsables);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 116 */           i++;
/*     */         } 
/*     */       } 
/*     */     } 
/* 120 */     rs.close();
/* 121 */     rsSubProc.close();
/* 122 */     rsDoc.close();
/*     */     
/* 124 */     if (!miPdf.finDetalle()) {
/* 125 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoEscribePdf&texto=Escribiendo fin detalle"));
/*     */     }
/*     */     
/* 128 */     if (!miPdf.cerrarArchivo()) {
/* 129 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoEscribePdf&texto=Cerrando archivo"));
/*     */     }
/*     */     try {
/* 132 */       baos.writeTo(out);
/* 133 */       out.flush();
/*     */       
/*     */       return;
/* 136 */     } catch (Exception e) {
/*     */       
/* 138 */       e.printStackTrace();
/* 139 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorVisualizandoArchivo&texto=" + e.getMessage()));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalDocumentosPdf.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */