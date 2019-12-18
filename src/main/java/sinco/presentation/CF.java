/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.Enumeration;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.regex.Pattern;
/*     */ import sinco.business.AplazamientosSolicitudDTO;
/*     */ import sinco.business.EstadoDTO;
/*     */ import sinco.business.FechaDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.SolicitudDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AplazamientosSolicitudDAO;
/*     */ import sinco.data.EstadoDAO;
/*     */ import sinco.data.HistoriaSolicitudDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.data.SolicitudDAO;
/*     */ import sinco.spec.Utilidades2;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CF
/*     */   implements HttpPresentation
/*     */ {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException, Exception {
/*  36 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  37 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */ 
/*     */     
/*  41 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  42 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */     
/*  44 */     int numeroSolicitud = Integer.parseInt(comms.request.getParameter("solicitud"));
/*     */     
/*  46 */     SolicitudDAO sf = new SolicitudDAO();
/*  47 */     SolicitudDTO regSol = sf.getSolicitud(numeroSolicitud);
/*  48 */     sf.close();
/*     */     
/*  50 */     EstadoDAO estadof = new EstadoDAO();
/*  51 */     EstadoDTO estado = estadof.getEstado(regSol.getCodigoEstado());
/*  52 */     estadof.close();
/*     */     
/*  54 */     SisUsuariosDAO perf = new SisUsuariosDAO();
/*  55 */     SisUsuariosDTO regNavegante = perf.cargarRegistro(idNav);
/*     */     
/*  57 */     boolean salir = false;
/*  58 */     if (!regSol.getAbierta()) {
/*  59 */       salir = true;
/*     */     }
/*     */     
/*  62 */     if (estado.getTipoEstado().trim().equals("CAL") || estado.getTipoEstado().trim().equals("DV")) {
/*  63 */       salir = true;
/*     */     }
/*     */     
/*  66 */     if (salir) {
/*  67 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoAplazamiento"));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  72 */     Enumeration<?> enumera = comms.request.getParameterNames();
/*     */     
/*  74 */     while (enumera.hasMoreElements()) {
/*  75 */       String param = (String)enumera.nextElement();
/*  76 */       if (param.startsWith("APL_")) {
/*     */         
/*  78 */         Pattern p = Pattern.compile("_");
/*  79 */         String[] arregloDatos = p.split(param);
/*     */         
/*  81 */         int consecutivo = Integer.parseInt(arregloDatos[1]);
/*     */         
/*  83 */         AplazamientosSolicitudDAO rsAplazamiento = new AplazamientosSolicitudDAO();
/*  84 */         AplazamientosSolicitudDTO reg = rsAplazamiento.cargarRegistro(numeroSolicitud, consecutivo);
/*  85 */         rsAplazamiento.close();
/*     */         
/*  87 */         if (reg.getEstado() != 0) {
/*  88 */           throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoAplazamiento"));
/*     */         }
/*     */         
/*  91 */         String fecha = arregloDatos[2];
/*     */         
/*  93 */         String accion = comms.request.getParameter(param);
/*  94 */         String justificacion = comms.request.getParameter("JUS_" + consecutivo);
/*     */ 
/*     */         
/*  97 */         Varios oVarios = new Varios();
/*  98 */         if (accion.equals("N")) {
/*     */ 
/*     */           
/* 101 */           SisUsuariosDTO prov = oVarios.leerPersona(regSol.getEmpleadoProveedor());
/*     */           
/* 103 */           String mensaje = oVarios.formatMensaje("CorreoAplazamientoNegado", "" + regSol.getNumero(), justificacion);
/*     */           
/* 105 */           String from = regNavegante.getEmail();
/* 106 */           String to = prov.getEmail();
/*     */           
/* 108 */           Utilidades.sendMail(ParametrosDTO.getString("servidor.correo"), from, to, "Aplazamiento negado", mensaje);
/*     */ 
/*     */ 
/*     */           
/* 112 */           HistoriaSolicitudDAO hsf = new HistoriaSolicitudDAO();
/* 113 */           hsf.crearHistoria(regSol.getNumero(), regSol.getCodigoEstado(), regSol.getCodigoEstado(), "Aplazamiento negado", elUsuario);
/* 114 */           hsf.close();
/*     */ 
/*     */ 
/*     */           
/* 118 */           rsAplazamiento = new AplazamientosSolicitudDAO();
/* 119 */           rsAplazamiento.modificarRegistro(numeroSolicitud, consecutivo, 2, justificacion, elUsuario);
/* 120 */           rsAplazamiento.close();
/* 121 */           throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=AplazamientoNegado"));
/*     */         } 
/*     */ 
/*     */         
/* 125 */         if (regSol.getEmpleadoCliente() != idNav) {
/* 126 */           throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=UstesNoCliente"));
/*     */         }
/*     */         
/* 129 */         String total = fecha;
/*     */         try {
/* 131 */           GregorianCalendar gc = Utilidades.StringtoGC(total);
/* 132 */           if (Utilidades2.noHabil(gc)) {
/* 133 */             throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=AplazamientoNoHabil"));
/*     */           }
/* 135 */           if (Utilidades.GCtoString(gc).compareTo(Utilidades.darFormatoFecha(regSol.getFechaEstimadaTerminacion())) < 0) {
/* 136 */             throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=AplazamientoNoAntes"));
/*     */           }
/*     */           
/* 139 */           SolicitudDAO sfa = new SolicitudDAO();
/* 140 */           sfa.actualizarFechaTerminacion(numeroSolicitud, total, elUsuario);
/*     */           
/* 142 */           if (regSol.getNivelEscalamiento() > 0) {
/*     */             
/* 144 */             EstadoDAO efa = new EstadoDAO();
/* 145 */             efa.cargarTodosTipo("PRV");
/* 146 */             EstadoDTO estaPRV = efa.next();
/* 147 */             efa.close();
/*     */ 
/*     */             
/* 150 */             regSol.setCodigoEstado(estaPRV.getCodigo());
/*     */             
/* 152 */             sfa.limpiarOportunidad(numeroSolicitud, estaPRV.getCodigo(), elUsuario);
/*     */           } 
/* 154 */           sfa.close();
/*     */ 
/*     */ 
/*     */           
/* 158 */           rsAplazamiento = new AplazamientosSolicitudDAO();
/* 159 */           rsAplazamiento.modificarRegistro(numeroSolicitud, consecutivo, 1, "", elUsuario);
/* 160 */           rsAplazamiento.close();
/*     */ 
/*     */           
/* 163 */           HistoriaSolicitudDAO hsf = new HistoriaSolicitudDAO();
/*     */           
/* 165 */           hsf.crearHistoria(regSol.getNumero(), regSol.getCodigoEstado(), regSol.getCodigoEstado(), "Aplazamiento aceptado desde : " + Utilidades.darFormatoFecha(regSol.getFechaEstimadaTerminacion()) + " para : " + total, elUsuario);
/* 166 */           hsf.close();
/*     */ 
/*     */           
/* 169 */           SisUsuariosDTO prov = oVarios.leerPersona(regSol.getEmpleadoProveedor());
/* 170 */           String mensaje = oVarios.formatMensaje("CorreoAplazamientoAceptado", "" + regSol.getNumero(), fecha);
/*     */           
/* 172 */           String from = regNavegante.getEmail();
/* 173 */           String to = prov.getEmail();
/*     */           
/* 175 */           Utilidades.sendMail(ParametrosDTO.getString("servidor.correo"), from, to, "Aplazamiento aceptado", mensaje);
/*     */         }
/* 177 */         catch (Exception e) {
/* 178 */           throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=FormatoFechaNoValido"));
/*     */         } 
/*     */         
/* 181 */         SolicitudDAO rsSol = new SolicitudDAO();
/* 182 */         regSol = rsSol.getSolicitud(regSol.getNumero());
/* 183 */         while (regSol.getSolicitudPadre() != -1) {
/* 184 */           SolicitudDTO regSolPadre = rsSol.getSolicitud(regSol.getSolicitudPadre());
/*     */           
/* 186 */           ServiciosDAO rsServicio = new ServiciosDAO();
/* 187 */           ServiciosDTO regServicio = rsServicio.cargarRegistro(regSolPadre.getCodigoServicio());
/* 188 */           rsServicio.close();
/*     */           
/* 190 */           int diasServicio = Utilidades2.diasDelServicio(regServicio.getDuracion(), regServicio.getUnidadMedida());
/*     */           
/* 192 */           long diferencia = -1L * Utilidades2.diferenciaEnDias(regSol.getFechaVigencia(), regSolPadre.getFechaVigencia());
/*     */           
/* 194 */           FechaDTO fechaTerminacionPadre = new FechaDTO(regSolPadre.getFechaEstimadaTerminacion());
/* 195 */           FechaDTO fechaTerminacionHija = new FechaDTO(regSol.getFechaEstimadaTerminacion());
/*     */ 
/*     */           
/* 198 */           String fechaBase = regSol.getFechaEstimadaTerminacion();
/*     */           
/* 200 */           if (fechaTerminacionPadre.getJuliano() > fechaTerminacionHija.getJuliano()) {
/* 201 */             fechaBase = regSolPadre.getFechaEstimadaTerminacion();
/*     */           }
/*     */           
/* 204 */           String nuevaFecha = Utilidades2.fechaMasDias(fechaBase, (int)(diasServicio - diferencia + 1L));
/*     */           
/* 206 */           FechaDTO fechaNueva = new FechaDTO(nuevaFecha);
/* 207 */           FechaDTO fechaPropuesta = new FechaDTO(total);
/*     */ 
/*     */           
/* 210 */           if (fechaNueva.getJuliano() <= fechaPropuesta.getJuliano()) {
/* 211 */             nuevaFecha = Utilidades2.fechaMasDias(total, 1);
/* 212 */             fechaNueva = new FechaDTO(nuevaFecha);
/*     */           } 
/*     */ 
/*     */           
/* 216 */           if (fechaNueva.getJuliano() > fechaTerminacionPadre.getJuliano()) {
/* 217 */             rsSol.actualizarFechaTerminacion(regSolPadre.getNumero(), nuevaFecha, elUsuario);
/*     */ 
/*     */             
/* 220 */             HistoriaSolicitudDAO hsf2 = new HistoriaSolicitudDAO();
/* 221 */             hsf2.crearHistoria(regSolPadre.getNumero(), regSolPadre.getCodigoEstado(), regSolPadre.getCodigoEstado(), "Aplazamiento aceptado en Solicitud Hija, desde=" + regSolPadre.getFechaBaseEscalamientos() + ", Hasta=" + nuevaFecha, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 228 */             hsf2.close();
/*     */           } 
/*     */           
/* 231 */           regSol = rsSol.getSolicitud(regSolPadre.getNumero());
/*     */         } 
/* 233 */         rsSol.close();
/*     */ 
/*     */         
/* 236 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=AplazamientoAceptado&p1=" + fecha));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CF.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */