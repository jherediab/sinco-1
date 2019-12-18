/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.Enumeration;
/*     */ import sinco.business.EstadoDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.business.VSolicitudesDTO;
/*     */ import sinco.data.AtencionSolicitudDAO;
/*     */ import sinco.data.EstadoDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.data.VSolicitudesDAO;
/*     */ import sinco.presentation.Calificar;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Calificar
/*     */   implements HttpPresentation
/*     */ {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  32 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  33 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  36 */     String _operacion = comms.request.getParameter("_operacion");
/*  37 */     if (_operacion == null || _operacion.length() == 0) {
/*  38 */       _operacion = "U";
/*     */     }
/*     */     
/*  41 */     if (_operacion.equals("MUL")) {
/*  42 */       calificarMultiples(comms);
/*     */     } else {
/*     */       
/*  45 */       calificarUna(comms);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void calificarMultiples(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  58 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  59 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */     
/*  61 */     Enumeration enumera = comms.request.getParameterNames();
/*     */ 
/*     */     
/*  64 */     while (enumera.hasMoreElements()) {
/*  65 */       String param = (String)enumera.nextElement();
/*  66 */       if (param.startsWith("SOL_")) {
/*     */         try {
/*  68 */           String estado = comms.request.getParameter(param);
/*  69 */           if (estado == null) estado = ""; 
/*  70 */           if (estado.length() > 0) {
/*  71 */             int solicitud = Integer.parseInt(param.substring(4));
/*  72 */             calificar(comms, solicitud, estado, "", idNav, elUsuario);
/*     */           }
/*     */         
/*  75 */         } catch (Exception e) {
/*  76 */           Utilidades.writeError("Calificacion Multiple ", e);
/*     */         } 
/*     */       }
/*     */     } 
/*  80 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("SolicitudesPorCalificar.po"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void calificarUna(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  90 */     int idsol = Integer.parseInt(comms.request.getParameter("solicitud"));
/*     */ 
/*     */     
/*  93 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  94 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */ 
/*     */     
/*  97 */     String justificacion = comms.request.getParameter("observacion");
/*  98 */     justificacion = justificacion.replaceAll("'", ".");
/*     */     
/* 100 */     String confiabilidad = comms.request.getParameter("confiabilidad");
/* 101 */     if (confiabilidad.equals("X")) {
/* 102 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=SeleccionarElemento"));
/*     */     }
/* 104 */     if (confiabilidad.equals("R") && justificacion.equals("")) {
/* 105 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=JustificarCalificacion"));
/*     */     }
/*     */     
/* 108 */     if (confiabilidad.equals("0")) {
/* 109 */       devolverSolicitud(comms, idsol, justificacion);
/*     */     } else {
/*     */       
/* 112 */       calificar(comms, idsol, confiabilidad, justificacion, idNav, elUsuario);
/* 113 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("SolicitudesPorCalificar.po"));
/*     */     } 
/*     */   }
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
/*     */   private void devolverSolicitud(HttpPresentationComms comms, int numeroSolicitud, String justificacion) throws HttpPresentationException, KeywordValueException {
/* 127 */     VSolicitudesDAO rsVSol = new VSolicitudesDAO();
/* 128 */     VSolicitudesDTO regSol = rsVSol.getSolicitud(numeroSolicitud);
/* 129 */     rsVSol.close();
/*     */     
/* 131 */     String sPagina = "CambiarEstadoSolicitud.po?solicitud=" + numeroSolicitud + "&observacion=" + justificacion + "&nuevoestado=" + regSol.getEstadoAnterior() + "&devuelta=1";
/*     */ 
/*     */ 
/*     */     
/* 135 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void calificar(HttpPresentationComms comms, int idsol, String confiabilidad, String justificacion, int idNav, String elUsuario) throws HttpPresentationException, KeywordValueException {
/* 166 */     VSolicitudesDAO rsVSol = new VSolicitudesDAO();
/* 167 */     VSolicitudesDTO regSol = rsVSol.getSolicitud(idsol);
/* 168 */     rsVSol.close();
/*     */     
/* 170 */     EstadoDAO ef = new EstadoDAO();
/* 171 */     ef.cargarTodosTipo("EF");
/* 172 */     EstadoDTO estado = ef.next();
/* 173 */     ef.close();
/*     */     
/* 175 */     boolean enviarMensaje = true;
/*     */     
/* 177 */     ServiciosDAO serf = new ServiciosDAO();
/* 178 */     ServiciosDTO regServicio = serf.cargarRegistro(regSol.getCodigoServicio());
/* 179 */     serf.close();
/*     */ 
/*     */     
/* 182 */     if (!regServicio.getIndCorreoCalificacion().equals("S")) {
/* 183 */       enviarMensaje = false;
/*     */     }
/*     */     
/* 186 */     Varios oVarios = new Varios();
/* 187 */     oVarios.actualizarEstadoObj(idNav, regSol.getCodigoEstado(), estado.getCodigo(), justificacion, regSol, enviarMensaje, elUsuario, 0, 0, 0, confiabilidad);
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
/*     */ 
/*     */ 
/*     */     
/* 201 */     AtencionSolicitudDAO asf = new AtencionSolicitudDAO();
/* 202 */     if (justificacion != null && justificacion.length() > 0) {
/* 203 */       asf.crearAtencion(idsol, justificacion, idNav, elUsuario);
/*     */     }
/*     */ 
/*     */     
/* 207 */     if (confiabilidad.equals("R")) {
/* 208 */       SisUsuariosDTO recipiente = oVarios.getJefeProveedorObj(regSol.getEmpleadoProveedor());
/* 209 */       if (recipiente != null) {
/*     */         
/* 211 */         SisUsuariosDAO perf = new SisUsuariosDAO();
/* 212 */         SisUsuariosDTO regNavegante = perf.cargarRegistro(idNav);
/* 213 */         SisUsuariosDTO regProveedor = perf.cargarRegistro(regSol.getEmpleadoProveedor());
/*     */         
/* 215 */         String url = "\n" + ParametrosDTO.getString("url.sistema");
/* 216 */         url = url + "LU.po?l=" + recipiente.getCodigoEmpleado() + "&p=" + recipiente.getPassword() + "&t=m&";
/* 217 */         url = url + "h=VSEnCurso.po?solicitud=" + regSol.getNumero();
/* 218 */         String from = regNavegante.getEmail();
/* 219 */         String to = recipiente.getEmail();
/*     */         
/* 221 */         String mensaje = oVarios.formatMensaje("SolicitudCalificadaNC", "" + regSol.getNumero(), regSol.getNombreServicio(), regProveedor.getNombre(), url);
/* 222 */         Utilidades.sendMail(ParametrosDTO.getString("servidor.correo"), from, to, regSol.getNombreServicio(), mensaje);
/*     */       } 
/*     */     } 
/*     */     
/* 226 */     if (estado.getTipoEstado().trim().equals("EF") && regServicio.getTipoServicio().equals(Integer.toString(2)))
/*     */     {
/* 228 */       asf.incluirTarea(regSol.getNumero(), regServicio.getTipoServicio(), elUsuario);
/*     */     }
/* 230 */     asf.close();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\Calificar.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */