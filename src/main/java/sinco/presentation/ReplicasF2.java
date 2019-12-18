/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import java.util.Enumeration;
/*    */ import sinco.business.EstadoDTO;
/*    */ import sinco.business.VSolicitudesDTO;
/*    */ import sinco.data.EstadoDAO;
/*    */ import sinco.data.SolicitudDAO;
/*    */ import sinco.data.VSolicitudesDAO;
/*    */ import sinco.spec.Utilidades2;
/*    */ import sinco.spec.Varios;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ReplicasF2
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 26 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 27 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */     
/* 30 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/* 31 */     int idsol = Integer.parseInt(comms.request.getParameter("solicitud"));
/* 32 */     String elUsuario = "" + comms.session.getUser().getName();
/*    */     
/* 34 */     VSolicitudesDAO rsSol = new VSolicitudesDAO();
/* 35 */     VSolicitudesDTO regSol = rsSol.getSolicitud(idsol);
/* 36 */     rsSol.close();
/*    */     
/* 38 */     EstadoDAO efa = new EstadoDAO();
/* 39 */     efa.cargarTodosTipo("PRV");
/* 40 */     EstadoDTO eProveedor = efa.next();
/* 41 */     efa.cargarTodosTipo("INI");
/* 42 */     EstadoDTO eInicial = efa.next();
/* 43 */     efa.close();
/*    */     
/* 45 */     SolicitudDAO sf = new SolicitudDAO();
/* 46 */     String fechaVigencia = Utilidades2.fechaVigencia(regSol.getUnidadMedida());
/* 47 */     String fechaterminacion = Utilidades2.fechaTerminacion(fechaVigencia, regSol.getDuracion(), regSol.getUnidadMedida());
/*    */     
/* 49 */     Varios oVarios = new Varios();
/*    */     
/* 51 */     Enumeration enumera = comms.request.getParameterNames();
/* 52 */     while (enumera.hasMoreElements()) {
/* 53 */       String param = (String)enumera.nextElement();
/* 54 */       if (!param.equals("solicitud") && !param.equals("miBoton") && !param.equals("miForma") && !param.equals("clickcontrol")) {
/* 55 */         int persona = Integer.parseInt(comms.request.getParameter(param));
/* 56 */         if (persona > 0) {
/* 57 */           int numero = sf.replicar(idsol, persona, fechaVigencia, fechaterminacion, eInicial.getCodigo(), eProveedor.getCodigo(), elUsuario);
/* 58 */           if (numero > 0) {
/* 59 */             oVarios.enviarCorreoSolicitud(numero, idNav, persona, regSol);
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/* 64 */     sf.close();
/* 65 */     String pagina = "VSPorAt.po?solicitud=" + idsol;
/* 66 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(pagina));
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ReplicasF2.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */