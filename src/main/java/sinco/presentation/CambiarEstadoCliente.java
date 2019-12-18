/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationInputStream;
/*     */ import com.lutris.mime.ContentHeader;
/*     */ import com.lutris.mime.MimeHeader;
/*     */ import com.lutris.mime.MultipartMimeInput;
/*     */ import com.lutris.mime.MultipartMimeInputStream;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import sinco.business.CaracteristicasDTO;
/*     */ import sinco.business.EstadoDTO;
/*     */ import sinco.business.FechaDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.business.VSolicitudesDTO;
/*     */ import sinco.data.ArchivosSolicitudDAO;
/*     */ import sinco.data.AtencionSolicitudDAO;
/*     */ import sinco.data.CaracteristicasDAO;
/*     */ import sinco.data.DetalleSolicitudDAO;
/*     */ import sinco.data.EstadoDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.data.VSolicitudesDAO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CambiarEstadoCliente
/*     */   implements HttpPresentation
/*     */ {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  42 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  43 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  46 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  47 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */     
/*  49 */     String fixedFileName = "anexo";
/*     */     
/*  51 */     SisUsuariosDAO perf = new SisUsuariosDAO();
/*  52 */     SisUsuariosDTO regNavegante = perf.cargarRegistro(idNav);
/*     */     
/*  54 */     VSolicitudesDAO rsSol = new VSolicitudesDAO();
/*  55 */     VSolicitudesDTO regSol = null;
/*     */     
/*  57 */     int idsol = 0;
/*  58 */     File anexo = null;
/*  59 */     String observacion = "";
/*  60 */     int nuevoestado = 0;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  65 */       String type = "";
/*  66 */       String contentType = comms.request.getContentType();
/*  67 */       if (contentType == null) {
/*  68 */         contentType = "";
/*     */       }
/*  70 */       ContentHeader contentHdr = new ContentHeader("Content-Type: " + contentType);
/*     */ 
/*     */       
/*  73 */       HttpPresentationInputStream input = comms.request.getInputStream();
/*  74 */       MultipartMimeInput mime = new MultipartMimeInput(input, contentHdr);
/*     */       MultipartMimeInputStream in;
/*  76 */       while ((in = mime.nextPart()) != null) {
/*  77 */         MimeHeader hdr = in.getHeader("Content-Type");
/*  78 */         if (hdr != null) {
/*  79 */           ContentHeader chdr = new ContentHeader(hdr);
/*  80 */           type = chdr.getValue();
/*     */         } else {
/*     */           
/*  83 */           type = "null";
/*     */         } 
/*     */         
/*  86 */         String contentName = null;
/*  87 */         if ((hdr = in.getHeader("Content-Disposition")) != null) {
/*  88 */           ContentHeader chdr = new ContentHeader(hdr);
/*  89 */           contentName = chdr.getParameter("name");
/*     */         } 
/*     */         
/*  92 */         if (contentName == null) contentName = "null"; 
/*  93 */         int count = 0;
/*  94 */         byte[] buffer = new byte[1024];
/*     */         
/*  96 */         if (contentName.equals("theFileName")) {
/*     */           
/*  98 */           StringBuffer sb = new StringBuffer(); int n;
/*  99 */           while ((n = in.read(buffer, 0, buffer.length)) > 0) {
/* 100 */             for (int i = 0; i < n; i++) {
/* 101 */               sb.append((char)(buffer[i] + 256 & 0xFF));
/*     */             }
/*     */           } 
/* 104 */           fixedFileName = new String(sb);
/* 105 */           if (fixedFileName.trim().equals("")) {
/* 106 */             fixedFileName = "anexo";
/*     */           }
/*     */           
/* 109 */           while (fixedFileName.indexOf("\\") != -1) {
/* 110 */             fixedFileName = fixedFileName.substring(fixedFileName.indexOf("\\") + 1);
/*     */           }
/* 112 */           while (fixedFileName.indexOf("/") != -1) {
/* 113 */             fixedFileName = fixedFileName.substring(fixedFileName.indexOf("/") + 1);
/*     */           }
/*     */           
/* 116 */           if (anexo != null) {
/* 117 */             fixedFileName = fixedFileName.replace('#', '_');
/* 118 */             anexo.renameTo(new File(fixedFileName));
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 123 */         if (contentName.trim().equals("solicitud")) {
/* 124 */           int n; while ((n = in.read(buffer, 0, buffer.length)) > 0);
/*     */           
/* 126 */           String temporal = new String(buffer);
/* 127 */           idsol = Integer.parseInt(temporal.trim());
/* 128 */           regSol = rsSol.getSolicitud(idsol);
/* 129 */           in.close();
/*     */         } 
/*     */         
/* 132 */         if (contentName.trim().equals("nuevoestado")) {
/* 133 */           int n; while ((n = in.read(buffer, 0, buffer.length)) > 0);
/*     */           
/* 135 */           String temporal = new String(buffer);
/* 136 */           nuevoestado = Integer.parseInt(temporal.trim());
/* 137 */           in.close();
/*     */         } 
/*     */         
/* 140 */         if (contentName.trim().equals("observacion")) {
/* 141 */           int n; while ((n = in.read(buffer, 0, buffer.length)) > 0);
/*     */           
/* 143 */           observacion = new String(buffer);
/* 144 */           in.close();
/*     */         } 
/*     */         
/* 147 */         if (contentName.trim().equals("upload")) {
/* 148 */           if (fixedFileName.equals("")) {
/* 149 */             fixedFileName = "anexo";
/*     */           }
/* 151 */           ArchivosSolicitudDAO asf = new ArchivosSolicitudDAO();
/* 152 */           int siguiente = asf.siguiente(idsol);
/* 153 */           String nombreArchivo = idsol + "_" + siguiente + "_" + fixedFileName;
/*     */           
/* 155 */           FechaDTO oFecha = new FechaDTO(Utilidades.fechaActual());
/* 156 */           String toPath = Utilidades.verificarDirectorioDestino(ParametrosDTO.getString("archivos"), oFecha.getAnno(), oFecha.getMes());
/* 157 */           anexo = new File(toPath + "/" + nombreArchivo);
/*     */           
/* 159 */           FileOutputStream out = new FileOutputStream(anexo);
/* 160 */           count = 0;
/*     */           int n;
/* 162 */           while ((n = in.read(buffer, 0, buffer.length)) > 0) {
/* 163 */             out.write(buffer, 0, n);
/* 164 */             count += n;
/*     */           } 
/* 166 */           in.close();
/* 167 */           out.close();
/*     */           
/* 169 */           if (count == 0) {
/*     */             try {
/* 171 */               anexo.delete();
/*     */             }
/* 173 */             catch (Exception e) {
/* 174 */               e.printStackTrace();
/*     */             } 
/* 176 */             anexo = null;
/*     */           } 
/* 178 */           if (anexo != null) {
/* 179 */             if (count > ParametrosDTO.getFloat("tamano_archivos") * 1024.0F * 1024.0F) {
/* 180 */               anexo.delete();
/* 181 */               rsSol.close();
/* 182 */               asf.close();
/* 183 */               throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ArchivoMuyGrande&p1=" + (count / 1024 / 1024) + "&p2=" + ParametrosDTO.getFloat("tamano_archivos")));
/*     */             } 
/* 185 */             asf.crearArchivo(idsol, nombreArchivo, count, elUsuario);
/*     */           } 
/* 187 */           asf.close();
/*     */         }
/*     */       
/*     */       } 
/* 191 */     } catch (Exception e) {
/* 192 */       Utilidades.writeError("Error al crear directorio " + ParametrosDTO.getString("archivos"), e);
/* 193 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 196 */     if (nuevoestado == 0) {
/* 197 */       rsSol.close();
/* 198 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=SeleccionarElemento"));
/*     */     } 
/*     */     
/* 201 */     EstadoDAO ef = new EstadoDAO();
/* 202 */     EstadoDTO estado = ef.getEstado(nuevoestado);
/* 203 */     EstadoDTO estadoactual = ef.getEstado(regSol.getCodigoEstado());
/*     */     
/* 205 */     if (estado.getTipoEstado().trim().equals("PRV") && regSol.getFechaVigencia().length() == 0) {
/* 206 */       rsSol.close();
/* 207 */       ef.close();
/* 208 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ProcedimientoEquivocado"));
/*     */     } 
/*     */     
/* 211 */     ef.cargarTodosTipo("EF");
/* 212 */     EstadoDTO estadoFinal = ef.next();
/*     */     
/* 214 */     ef.close();
/*     */ 
/*     */ 
/*     */     
/* 218 */     observacion = observacion.trim();
/*     */     
/* 220 */     if (estado.getTipoEstado().trim().equals("DV") && observacion.trim().equals("")) {
/* 221 */       rsSol.close();
/* 222 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=DigilenciarObservacion"));
/*     */     } 
/*     */     
/* 225 */     ServiciosDAO serf = new ServiciosDAO();
/* 226 */     ServiciosDTO regServicio = serf.cargarRegistro(regSol.getCodigoServicio());
/* 227 */     serf.close();
/*     */     
/* 229 */     if (estado.getTipoEstado().trim().equals("CAL")) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 234 */       CaracteristicasDAO rsCaracteristica = new CaracteristicasDAO();
/* 235 */       rsCaracteristica.cargarTodosParaServicioObligatorias(regSol.getCodigoServicio(), "P");
/* 236 */       CaracteristicasDTO car = rsCaracteristica.next();
/*     */       
/* 238 */       DetalleSolicitudDAO dsf = new DetalleSolicitudDAO();
/* 239 */       while (car != null) {
/* 240 */         dsf.cargarParaSolicitud(regSol.getNumero(), car.getCodigo());
/* 241 */         if (dsf.next() == null) {
/* 242 */           rsCaracteristica.close();
/* 243 */           dsf.close();
/* 244 */           rsSol.close();
/* 245 */           throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=CamposObligatorios"));
/*     */         } 
/* 247 */         car = rsCaracteristica.next();
/*     */       } 
/* 249 */       dsf.close();
/* 250 */       rsCaracteristica.close();
/*     */     } 
/*     */ 
/*     */     
/* 254 */     if (estadoactual.getTipoEstado().trim().equals("CAL") && estado.getTipoEstado().trim().equals("EF") && regSol.getCodigoConfiabilidad().trim().equals("")) {
/* 255 */       rsSol.close();
/* 256 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=PrimeroCalificar"));
/*     */     } 
/*     */     
/* 259 */     Varios oVarios = new Varios();
/*     */     
/* 261 */     boolean enviarMensaje = true;
/* 262 */     if (regServicio.getCalificarServicio().equals("N")) enviarMensaje = false;
/*     */ 
/*     */     
/* 265 */     RespuestaBD rtabd = null;
/* 266 */     if (anexo == null) {
/* 267 */       rtabd = oVarios.actualizarEstadoObj(idNav, regSol.getCodigoEstado(), estado.getCodigo(), observacion, regSol, enviarMensaje, elUsuario, 0, 0, 0, "");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 282 */       rtabd = oVarios.actualizarEstadoObj(idNav, regSol.getCodigoEstado(), estado.getCodigo(), observacion, anexo, regSol, enviarMensaje, elUsuario);
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
/* 294 */     if (rtabd.getCerrarSolicitud().equals("N")) {
/* 295 */       rsSol.close();
/* 296 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoPuedoCerrarSolicitud&p1=" + rtabd.getCausal()));
/*     */     } 
/*     */     
/* 299 */     AtencionSolicitudDAO asf = new AtencionSolicitudDAO();
/* 300 */     if (observacion != null && observacion.length() > 0) {
/* 301 */       asf.crearAtencion(idsol, observacion, Integer.parseInt((String)comms.session.getSessionData().get("miId")), elUsuario);
/*     */     }
/*     */     
/* 304 */     if (estado.getTipoEstado().trim().equals("CAL") && (regServicio.getTipoServicio().equals(Integer.toString(3)) || regServicio.getTipoServicio().equals(Integer.toString(4)) || regServicio.getTipoServicio().equals(Integer.toString(5))))
/*     */     {
/*     */ 
/*     */       
/* 308 */       asf.incluirTarea(regSol.getNumero(), regServicio.getTipoServicio(), elUsuario);
/*     */     }
/*     */     
/* 311 */     if (estado.getTipoEstado().trim().equals("CAL") && regServicio.getTipoServicio().equals(Integer.toString(0))) {
/* 312 */       asf.incluirTarea(regSol.getNumero(), "0", elUsuario);
/*     */     }
/*     */     
/* 315 */     asf.close();
/*     */     
/* 317 */     if (regSol.getNotificar()) {
/* 318 */       regSol = rsSol.getSolicitud(idsol);
/* 319 */       oVarios.notificacionProveedorAnterior(regSol, regNavegante.getEmail());
/*     */     } 
/*     */     
/* 322 */     int cuantasPendientes = rsSol.existenHijas(idsol);
/*     */     
/* 324 */     rsSol.close();
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
/* 338 */     if (cuantasPendientes > 0) {
/* 339 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("SolicitudesNoEnviadas.po"));
/*     */     }
/*     */     
/* 342 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("SolicitudesPorAtender.po"));
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CambiarEstadoCliente.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */