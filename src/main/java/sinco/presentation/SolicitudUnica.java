/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import sinco.business.AreasDTO;
/*    */ import sinco.business.SolicitudDTO;
/*    */ import sinco.business.Utilidades;
/*    */ import sinco.data.AreasDAO;
/*    */ import sinco.data.SeguridadDAO;
/*    */ import sinco.data.SolicitudDAO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SolicitudUnica
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 22 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 23 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/* 25 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/* 26 */     int miArea = Integer.parseInt((String)comms.session.getSessionData().get("miArea"));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 35 */     int idsol = 0;
/*    */     try {
/* 37 */       idsol = Integer.parseInt(comms.request.getParameter("solicitud").trim());
/*    */     }
/* 39 */     catch (Exception e) {
/* 40 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idsol"));
/*    */     } 
/*    */     
/* 43 */     SeguridadDAO rsSeguridad = new SeguridadDAO();
/* 44 */     if (!rsSeguridad.getEstadoConexion()) {
/* 45 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*    */     }
/* 47 */     boolean bVerTodas = rsSeguridad.tieneLlave(miGrupo, "oVerSolicitudesOrganizacion");
/* 48 */     boolean bVerHijas = rsSeguridad.tieneLlave(miGrupo, "oVerSolicitudesAreashijas");
/* 49 */     rsSeguridad.close();
/*    */     
/* 51 */     int proveedor = 0;
/*    */     
/* 53 */     SolicitudDAO sf = new SolicitudDAO();
/* 54 */     SolicitudDTO regSol = sf.getSolicitud(idsol);
/* 55 */     sf.close();
/*    */     
/* 57 */     if (regSol == null) {
/* 58 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=SolicitudNoExiste"));
/*    */     }
/*    */     
/* 61 */     proveedor = regSol.getAreaProveedor();
/*    */     
/* 63 */     AreasDAO areaf = new AreasDAO();
/* 64 */     AreasDTO areaProveedor = areaf.getArea(proveedor);
/* 65 */     AreasDTO areaNavega = areaf.getArea(miArea);
/* 66 */     areaf.close();
/*    */     
/* 68 */     boolean valido = Utilidades.esSecuenciaHija(areaProveedor.getSecuencia(), areaNavega.getSecuencia());
/*    */     
/* 70 */     if (!bVerTodas && (!bVerHijas || !valido)) {
/* 71 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=SolicitudNoExiste"));
/*    */     }
/*    */     
/* 74 */     String sPagina = "VSEnCurso.po?solicitud=" + idsol;
/* 75 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SolicitudUnica.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */