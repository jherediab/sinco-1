/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import sinco.business.SisUsuariosDTO;
/*    */ import sinco.data.EncuestaDAO;
/*    */ import sinco.data.SisUsuariosDAO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrearEncuesta
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 20 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 21 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/* 23 */     int empleadoCliente = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/* 24 */     String elUsuario = "" + comms.session.getUser().getName();
/*    */     
/* 26 */     SisUsuariosDAO perf = new SisUsuariosDAO();
/* 27 */     SisUsuariosDTO elNavegante = perf.cargarRegistro(empleadoCliente);
/* 28 */     int miArea = elNavegante.getArea();
/*    */     
/* 30 */     if (miArea == 0) {
/* 31 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=AreaNoValida"));
/*    */     }
/*    */     
/* 34 */     int nuevoestado = 0;
/*    */     try {
/* 36 */       nuevoestado = Integer.parseInt(comms.request.getParameter("nuevoestado"));
/*    */     }
/* 38 */     catch (Exception e) {}
/*    */     
/* 40 */     if (nuevoestado == 4) {
/* 41 */       int encuesta = Integer.parseInt(comms.request.getParameter("encuesta"));
/* 42 */       EncuestaDAO rsEncuesta = new EncuestaDAO();
/* 43 */       rsEncuesta.modificarRegistro(encuesta, nuevoestado, elUsuario);
/* 44 */       rsEncuesta.close();
/* 45 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ServicioMultipleAnulado"));
/*    */     } 
/*    */     
/* 48 */     int codigo_servicio = Integer.parseInt(comms.request.getParameter("servicio"));
/* 49 */     String ciclo = comms.request.getParameter("ciclo");
/* 50 */     if (ciclo == null) ciclo = "";
/*    */ 
/*    */     
/* 53 */     EncuestaDAO rsEncuesta = new EncuestaDAO();
/* 54 */     int num = rsEncuesta.crearRegistro(empleadoCliente, miArea, codigo_servicio, 1, ciclo, elUsuario);
/* 55 */     rsEncuesta.close();
/*    */     
/* 57 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("LlenarEncuesta.po?encuesta=" + num));
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CrearEncuesta.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */