/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import sinco.business.ParametrosDTO;
/*    */ import sinco.business.ServiciosDTO;
/*    */ import sinco.business.SisUsuariosDTO;
/*    */ import sinco.business.Utilidades;
/*    */ import sinco.business.VSolicitudesDTO;
/*    */ import sinco.data.AtencionSolicitudDAO;
/*    */ import sinco.data.ServiciosDAO;
/*    */ import sinco.data.SisUsuariosDAO;
/*    */ import sinco.data.VSolicitudesDAO;
/*    */ import sinco.spec.Varios;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AnadirAtencion
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*    */     SisUsuariosDTO recipiente;
/* 26 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 27 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */     
/* 30 */     String elUsuario = "" + comms.session.getUser().getName();
/* 31 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*    */     
/* 33 */     String observacion = comms.request.getParameter("observacion");
/* 34 */     if (observacion.length() == 0) {
/* 35 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LongitudNoValida"));
/*    */     }
/*    */     
/* 38 */     int idsol = Integer.parseInt(comms.request.getParameter("solicitud"));
/*    */     
/* 40 */     VSolicitudesDAO rsVSol = new VSolicitudesDAO();
/* 41 */     VSolicitudesDTO regSol = rsVSol.getSolicitud(idsol);
/* 42 */     rsVSol.close();
/*    */     
/* 44 */     ServiciosDAO rsServ = new ServiciosDAO();
/* 45 */     ServiciosDTO regServ = rsServ.cargarRegistro(regSol.getCodigoServicio());
/* 46 */     rsServ.close();
/*    */     
/* 48 */     String Pagina = comms.request.getParameter("pagina_siguiente") + "?solicitud=" + idsol;
/*    */     
/* 50 */     observacion = observacion.replaceAll("'", ".");
/* 51 */     AtencionSolicitudDAO asf = new AtencionSolicitudDAO();
/* 52 */     asf.crearAtencion(idsol, observacion, idNav, elUsuario);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 58 */     asf.close();
/*    */ 
/*    */     
/* 61 */     SisUsuariosDAO perf = new SisUsuariosDAO();
/* 62 */     SisUsuariosDTO regNavegante = perf.cargarRegistro(idNav);
/*    */     
/* 64 */     if (idNav == regSol.getEmpleadoProveedor()) {
/* 65 */       recipiente = perf.cargarRegistro(regSol.getEmpleadoCliente());
/*    */     } else {
/*    */       
/* 68 */       recipiente = perf.cargarRegistro(regSol.getEmpleadoProveedor());
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 73 */     String url = "\n" + ParametrosDTO.getString("url.sistema") + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&";
/*    */     
/* 75 */     if (recipiente.getCodigoEmpleado() == regSol.getEmpleadoProveedor()) {
/* 76 */       url = url + "h=VSPorAt.po?solicitud=" + regSol.getNumero();
/*    */     } else {
/* 78 */       url = url + "h=VSEnCurso.po?solicitud=" + regSol.getNumero();
/*    */     } 
/* 80 */     Varios oVarios = new Varios();
/* 81 */     String mensaje = oVarios.formatMensaje("SolicitudRequiereAtencion", "" + regSol.getNumero(), regSol.getNombreServicio(), url);
/*    */ 
/*    */     
/* 84 */     String from = regNavegante.getEmail();
/* 85 */     String to = recipiente.getEmail();
/*    */     
/* 87 */     Utilidades.sendMail(ParametrosDTO.getString("servidor.correo"), from, to, regSol.getNombreServicio(), mensaje);
/*    */     
/* 89 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(Pagina));
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AnadirAtencion.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */