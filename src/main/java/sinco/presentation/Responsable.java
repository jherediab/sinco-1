/*    */ package sinco.presentation;
/*    */ 
/*    */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*    */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*    */ import com.lutris.util.KeywordValueException;
/*    */ import java.util.regex.Pattern;
/*    */ import sinco.business.ServiciosDTO;
/*    */ import sinco.business.SisUsuariosDTO;
/*    */ import sinco.business.Utilidades;
/*    */ import sinco.business.VSolicitudesDTO;
/*    */ import sinco.data.ServiciosDAO;
/*    */ import sinco.data.SisUsuariosDAO;
/*    */ import sinco.data.VSolicitudesDAO;
/*    */ import sinco.spec.Varios;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Responsable
/*    */   implements HttpPresentation
/*    */ {
/*    */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 27 */     if (!comms.session.getSessionData().containsKey("miId")) {
/* 28 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*    */     }
/*    */ 
/*    */     
/* 32 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/* 33 */     String elUsuario = "" + comms.session.getUser().getName();
/*    */     
/* 35 */     int numeroSolicitud = Integer.parseInt(comms.request.getParameter("solicitud"));
/*    */     
/* 37 */     String datoResponsable = comms.request.getParameter("nuevoresp");
/* 38 */     String notificar = "N";
/*    */     try {
/* 40 */       Integer.parseInt(comms.request.getParameter("notificar"));
/* 41 */       notificar = "S";
/*    */     }
/* 43 */     catch (Exception e) {}
/*    */     
/* 45 */     Pattern p = Pattern.compile("_");
/* 46 */     String[] arregloDatos = p.split(datoResponsable);
/*    */     
/* 48 */     int nuevoResponsable = Integer.parseInt(arregloDatos[1]);
/* 49 */     int areaResponsable = Integer.parseInt(arregloDatos[2]);
/*    */ 
/*    */     
/* 52 */     VSolicitudesDAO rsVSol = new VSolicitudesDAO();
/* 53 */     VSolicitudesDTO regSol = rsVSol.getSolicitud(numeroSolicitud);
/*    */     
/* 55 */     SisUsuariosDAO perf = new SisUsuariosDAO();
/* 56 */     SisUsuariosDTO elNavegador = perf.cargarRegistro(idNav);
/* 57 */     SisUsuariosDTO proveedor = perf.cargarRegistro(regSol.getEmpleadoProveedor());
/* 58 */     SisUsuariosDTO nuevoProveedor = perf.cargarRegistro(nuevoResponsable);
/*    */     
/* 60 */     ServiciosDAO serf = new ServiciosDAO();
/* 61 */     ServiciosDTO regServicio = serf.cargarRegistro(regSol.getCodigoServicio());
/* 62 */     serf.close();
/*    */     
/* 64 */     String observacion = "" + elNavegador.getNombres() + " Redirecciono de " + proveedor.getNombres() + " a " + nuevoProveedor.getNombres() + " en " + Utilidades.fechaActual();
/* 65 */     if (observacion.length() > 100) observacion = observacion.substring(0, 99);
/*    */ 
/*    */     
/* 68 */     String s = "empleado_proveedor=" + nuevoResponsable + ",";
/* 69 */     s = s + "proveedor_anterior=" + regSol.getEmpleadoProveedor() + ",";
/* 70 */     s = s + "area_proveedor=" + areaResponsable + ",";
/* 71 */     s = s + "notificar='" + notificar + "',";
/* 72 */     s = s + "fecha_modificacion=" + Utilidades.formatoFecha(Utilidades.ahora()) + ",";
/* 73 */     s = s + "usuario_modificacion='" + elUsuario + "'";
/*    */     
/* 75 */     rsVSol.actualizarCampos(regSol.getNumero(), s);
/* 76 */     rsVSol.close();
/*    */     
/* 78 */     regSol.setEmpleadoProveedor(nuevoResponsable);
/*    */     
/* 80 */     Varios oVarios = new Varios();
/* 81 */     oVarios.nuevoProveedor(idNav, observacion, regSol, elUsuario);
/*    */     
/* 83 */     if (regServicio.getTipoServicio().equals(Integer.toString(2))) {
/* 84 */       oVarios.actualizarPlaneacion(regSol, proveedor.getCodigoEmpleado(), nuevoResponsable, elUsuario);
/*    */     }
/*    */ 
/*    */     
/* 88 */     if (idNav != proveedor.getCodigoEmpleado()) {
/* 89 */       oVarios.viejoProveedor(regSol.getNumero(), proveedor.getCodigoEmpleado(), elNavegador, proveedor, regSol.getNombreServicio());
/*    */     }
/* 91 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=Afirmacion3&p1=" + proveedor.getApellidos() + ", " + proveedor.getNombres() + "&p2=" + nuevoProveedor.getApellidos() + ", " + nuevoProveedor.getNombres()));
/*    */   }
/*    */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\Responsable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */