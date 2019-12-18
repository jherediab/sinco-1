/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import sinco.business.EstadoDTO;
/*    */ import sinco.business.VSolicitudesDTO;
/*    */ import sinco.data.EstadoDAO;
/*    */ import sinco.data.VSolicitudesDAO;
/*    */ import sinco.spec.Varios;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CopiarSolicitud
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 21 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 22 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */     
/* 25 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/* 26 */     String elUsuario = "" + comms.session.getUser().getName();
/*    */ 
/*    */     
/* 29 */     int servicio = Integer.parseInt(comms.request.getParameter("servicio"));
/* 30 */     int area = Integer.parseInt(comms.request.getParameter("area"));
/* 31 */     int padre = Integer.parseInt(comms.request.getParameter("padre"));
/* 32 */     int persona = Integer.parseInt(comms.request.getParameter("persona"));
/*    */ 
/*    */ 
/*    */     
/* 36 */     VSolicitudesDAO sf = new VSolicitudesDAO();
/* 37 */     VSolicitudesDTO regSol = sf.getSolicitud(padre);
/* 38 */     sf.close();
/*    */     
/* 40 */     EstadoDAO ef = new EstadoDAO();
/* 41 */     ef.cargarTodosTipo("AN");
/* 42 */     EstadoDTO estado = ef.next();
/* 43 */     ef.close();
/*    */     
/* 45 */     Varios oVarios = new Varios();
/* 46 */     oVarios.actualizarEstadoObj(idNav, regSol.getCodigoEstado(), estado.getCodigo(), "Solicitud Copiada y Anulada", regSol, true, elUsuario, 0, 0, 0, "");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 59 */     if (regSol.getCodigoEstado() == 1) {
/* 60 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("CrearSolicitud.po?servicio=" + servicio + "&padre=" + padre + "&area=" + area + "&persona=" + persona));
/*    */     }
/* 62 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("TASetTipo.po?area=" + area + "&servicio=" + servicio + "&padre=" + regSol.getSolicitudPadre()));
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CopiarSolicitud.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */