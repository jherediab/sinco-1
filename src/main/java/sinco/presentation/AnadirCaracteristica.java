/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import sinco.business.CaracteristicasDTO;
/*     */ import sinco.business.EstadoDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.business.VSolicitudesDTO;
/*     */ import sinco.data.CaracteristicasDAO;
/*     */ import sinco.data.DetalleSolicitudDAO;
/*     */ import sinco.data.EstadoDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.data.VSolicitudesDAO;
/*     */ import sinco.presentation.AnadirCaracteristica;
/*     */ import sinco.spec.Utilidades2;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnadirCaracteristica
/*     */   implements HttpPresentation
/*     */ {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  37 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  38 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  41 */     int idsol = Integer.parseInt(comms.request.getParameter("solicitud"));
/*  42 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  43 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */     
/*  45 */     int helpDesk = 0;
/*     */     try {
/*  47 */       helpDesk = Integer.parseInt(comms.request.getParameter("helpDesk"));
/*     */     }
/*  49 */     catch (Exception e) {}
/*     */     
/*  51 */     String origen = comms.request.getParameter("origen");
/*  52 */     if (origen == null) origen = "";
/*     */     
/*  54 */     int caracteristicaDecide = 0;
/*  55 */     if (origen.equals("prv")) {
/*  56 */       ServiciosDAO rsServ = new ServiciosDAO();
/*  57 */       ServiciosDTO servicio = rsServ.cargarServicioSolicitud(idsol);
/*  58 */       rsServ.close();
/*  59 */       if (servicio != null) {
/*  60 */         caracteristicaDecide = servicio.getCaracteristicaDecide();
/*     */       }
/*     */     } 
/*     */     
/*  64 */     String respuestaDecide = "";
/*  65 */     int ejecutarProceso = 0;
/*  66 */     Enumeration<?> enumera = comms.request.getParameterNames();
/*  67 */     DetalleSolicitudDAO dsf = new DetalleSolicitudDAO();
/*     */     
/*  69 */     while (enumera.hasMoreElements()) {
/*  70 */       String param = (String)enumera.nextElement();
/*  71 */       if (!param.equals("solicitud") && !param.equals("pagina") && !param.equals("miBoton") && !param.equals("helpDesk") && !param.equals("origen")) {
/*  72 */         int caracteristicacodigo = Integer.parseInt(param);
/*  73 */         String caracteristicavalor = comms.request.getParameter("" + caracteristicacodigo);
/*  74 */         if (caracteristicavalor != null && !caracteristicavalor.trim().equals("")) {
/*  75 */           caracteristicavalor = caracteristicavalor.replaceAll("'", ".");
/*  76 */           dsf.crearDetalle(idsol, caracteristicacodigo, caracteristicavalor, elUsuario);
/*     */         } 
/*     */       } 
/*     */     } 
/*  80 */     dsf.close();
/*     */     
/*  82 */     if (origen.equals("prv") && 
/*  83 */       respuestaDecide.equals("1")) {
/*  84 */       verificarSolicitudes(idsol, idNav, elUsuario);
/*     */     }
/*     */     
/*  87 */     terminadaCierre(idsol, ejecutarProceso, comms);
/*     */ 
/*     */     
/*  90 */     String pagina = comms.request.getParameter("pagina") + "?solicitud=" + idsol + "&helpDesk=" + helpDesk;
/*  91 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(pagina));
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
/*     */   private void verificarSolicitudes(int numero, int idNav, String elUsuario) {
/* 103 */     VSolicitudesDAO rs = new VSolicitudesDAO();
/* 104 */     Collection<VSolicitudesDTO> arr = rs.cargarHijasGeneradas(numero);
/* 105 */     rs.close();
/*     */     
/* 107 */     Iterator<VSolicitudesDTO> iterator = arr.iterator();
/* 108 */     while (iterator.hasNext()) {
/* 109 */       VSolicitudesDTO reg = (VSolicitudesDTO)iterator.next();
/* 110 */       enviarSolicitud(reg, idNav, elUsuario);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean enviarSolicitud(VSolicitudesDTO regSol, int idNav, String elUsuario) {
/* 129 */     if (regSol == null) {
/* 130 */       return false;
/*     */     }
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
/* 155 */     String fechaVigencia = Utilidades2.fechaVigencia(regSol.getUnidadMedida());
/* 156 */     String fechaterminacion = Utilidades2.fechaTerminacion(fechaVigencia, regSol.getDuracion(), regSol.getUnidadMedida());
/*     */ 
/*     */     
/* 159 */     String observacion = "";
/*     */     
/* 161 */     EstadoDAO efa = new EstadoDAO();
/* 162 */     efa.cargarTodosTipo("PRV");
/* 163 */     EstadoDTO esta = efa.next();
/* 164 */     efa.close();
/*     */ 
/*     */     
/* 167 */     Varios oVarios = new Varios();
/* 168 */     oVarios.enviarSolicitud(idNav, regSol.getCodigoEstado(), esta.getCodigo(), observacion, regSol, true, fechaVigencia, fechaterminacion, 0, regSol.getDuracion(), regSol.getUnidadMedida(), elUsuario);
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
/* 183 */     VSolicitudesDAO sf = new VSolicitudesDAO();
/*     */     
/* 185 */     regSol = sf.getSolicitud(regSol.getNumero());
/* 186 */     while (regSol.getSolicitudPadre() != -1) {
/* 187 */       VSolicitudesDTO regSolPadre = sf.getSolicitud(regSol.getSolicitudPadre());
/*     */ 
/*     */       
/* 190 */       int diasServicio = Utilidades2.diasDelServicio(regSolPadre.getDuracion(), regSolPadre.getUnidadMedida());
/*     */       
/* 192 */       long diferencia = -1L * Utilidades2.diferenciaEnDias(regSol.getFechaVigencia(), regSolPadre.getFechaVigencia());
/*     */       
/* 194 */       String nuevaFecha = Utilidades2.fechaMasDias(regSol.getFechaEstimadaTerminacion(), (int)(diasServicio - diferencia));
/*     */       
/* 196 */       if (Utilidades2.compararFechas(regSolPadre.getFechaEstimadaTerminacion(), nuevaFecha) < 0) {
/* 197 */         String s = "fecha_estimada_terminacion=" + Utilidades.formatoFecha2(Utilidades.darFormatoFecha(nuevaFecha)) + ",";
/* 198 */         s = s + "fecha_base_escalamientos=" + Utilidades.formatoFecha2(nuevaFecha) + ",";
/* 199 */         s = s + "fecha_modificacion=" + Utilidades.formatoFecha(Utilidades.ahora()) + ",";
/* 200 */         s = s + "usuario_modificacion='" + elUsuario + "'";
/* 201 */         sf.actualizarCampos(regSolPadre.getNumero(), s);
/*     */       } 
/* 203 */       regSol = sf.getSolicitud(regSolPadre.getNumero());
/*     */     } 
/* 205 */     sf.close();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 213 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void terminadaCierre(int numeroSolicitud, int ejecutarProceso, HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 223 */     String elUsuario = "" + comms.session.getUser().getName();
/* 224 */     VSolicitudesDAO rsSol = new VSolicitudesDAO();
/* 225 */     VSolicitudesDTO regSol = rsSol.getSolicitud(numeroSolicitud);
/*     */     
/* 227 */     if (ejecutarProceso > 0) {
/* 228 */       rsSol.procesarNovedad(numeroSolicitud, regSol.getCodigoEstado(), elUsuario);
/*     */     }
/*     */     
/* 231 */     rsSol.close();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 237 */     CaracteristicasDAO rsCaracteristica = new CaracteristicasDAO();
/* 238 */     rsCaracteristica.cargarTodosParaServicioObligatorias(regSol.getCodigoServicio(), "P");
/* 239 */     CaracteristicasDTO car = rsCaracteristica.next();
/*     */     
/* 241 */     boolean cerrar = true;
/* 242 */     DetalleSolicitudDAO dsf = new DetalleSolicitudDAO();
/* 243 */     while (car != null) {
/* 244 */       dsf.cargarParaSolicitud(regSol.getNumero(), car.getCodigo());
/* 245 */       if (dsf.next() == null) {
/* 246 */         cerrar = false;
/*     */         break;
/*     */       } 
/* 249 */       car = rsCaracteristica.next();
/*     */     } 
/* 251 */     dsf.close();
/* 252 */     rsCaracteristica.close();
/*     */     
/* 254 */     if (cerrar) {
/* 255 */       ServiciosDAO serf = new ServiciosDAO();
/* 256 */       ServiciosDTO regServicio = serf.cargarRegistro(regSol.getCodigoServicio());
/* 257 */       serf.close();
/*     */       
/* 259 */       if (regServicio.getIndAvanzarCaracteristica().equals("S")) {
/*     */         
/* 261 */         EstadoDAO ef = new EstadoDAO();
/* 262 */         EstadoDTO estado = ef.getEstado("CAL");
/* 263 */         EstadoDTO estadoFinal = ef.getEstado("EF");
/* 264 */         ef.close();
/*     */ 
/*     */ 
/*     */         
/* 268 */         Varios oVarios = new Varios();
/* 269 */         int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */         
/* 271 */         boolean enviarMensaje = true;
/* 272 */         RespuestaBD rtabd = null;
/* 273 */         rtabd = oVarios.actualizarEstadoObj(idNav, regSol.getCodigoEstado(), estado.getCodigo(), "", regSol, enviarMensaje, elUsuario, 0, 0, 0, "");
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
/* 287 */         if (rtabd.getCerrarSolicitud().equals("N")) {
/* 288 */           throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoPuedoCerrarSolicitud&p1=" + rtabd.getCausal()));
/*     */         }
/*     */ 
/*     */         
/* 292 */         if (regServicio.getCalificarServicio().equals("S")) {
/* 293 */           oVarios.cerrarSolicitudSinCalificar(idNav, regSol, estadoFinal.getCodigo(), elUsuario);
/*     */         }
/*     */         
/* 296 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("SolicitudesPorAtender.po"));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AnadirCaracteristica.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */