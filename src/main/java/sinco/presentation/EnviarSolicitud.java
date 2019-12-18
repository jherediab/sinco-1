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
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.business.VSolicitudesDTO;
/*     */ import sinco.data.ArchivosSolicitudDAO;
/*     */ import sinco.data.CaracteristicasDAO;
/*     */ import sinco.data.EstadoDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.data.VSolicitudesDAO;
/*     */ import sinco.presentation.EnviarSolicitud;
/*     */ import sinco.spec.Utilidades2;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EnviarSolicitud
/*     */   implements HttpPresentation
/*     */ {
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  39 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  40 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  43 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  44 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */     
/*  46 */     String fixedFileName = "anexo";
/*  47 */     VSolicitudesDTO regSol = null;
/*  48 */     int idsol = 0;
/*  49 */     File anexo = null;
/*  50 */     int helpDesk = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  56 */       String contentType = comms.request.getContentType();
/*  57 */       if (contentType == null) {
/*  58 */         contentType = "";
/*     */       }
/*  60 */       ContentHeader contentHdr = new ContentHeader("Content-Type: " + contentType);
/*     */       
/*  62 */       HttpPresentationInputStream input = comms.request.getInputStream();
/*  63 */       MultipartMimeInput mime = new MultipartMimeInput(input, contentHdr);
/*     */       MultipartMimeInputStream in;
/*  65 */       while ((in = mime.nextPart()) != null) {
/*  66 */         MimeHeader hdr = in.getHeader("Content-Type");
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
/*  77 */         String contentName = null;
/*  78 */         if ((hdr = in.getHeader("Content-Disposition")) != null) {
/*  79 */           ContentHeader chdr = new ContentHeader(hdr);
/*  80 */           contentName = chdr.getParameter("name");
/*     */         } 
/*     */         
/*  83 */         if (contentName == null) contentName = "null"; 
/*  84 */         int count = 0;
/*  85 */         byte[] buffer = new byte[1024];
/*     */         
/*  87 */         if (contentName.equals("theFileName")) {
/*     */           
/*  89 */           StringBuffer sb = new StringBuffer(); int n;
/*  90 */           while ((n = in.read(buffer, 0, buffer.length)) > 0) {
/*  91 */             for (int i = 0; i < n; i++) {
/*  92 */               sb.append((char)(buffer[i] + 256 & 0xFF));
/*     */             }
/*     */           } 
/*  95 */           fixedFileName = new String(sb);
/*  96 */           if (fixedFileName.trim().equals("")) {
/*  97 */             fixedFileName = "anexo";
/*     */           }
/*     */           
/* 100 */           while (fixedFileName.indexOf("\\") != -1) {
/* 101 */             fixedFileName = fixedFileName.substring(fixedFileName.indexOf("\\") + 1);
/*     */           }
/* 103 */           while (fixedFileName.indexOf("/") != -1) {
/* 104 */             fixedFileName = fixedFileName.substring(fixedFileName.indexOf("/") + 1);
/*     */           }
/*     */           
/* 107 */           if (anexo != null) {
/* 108 */             fixedFileName = fixedFileName.replace('#', '_');
/* 109 */             anexo.renameTo(new File(fixedFileName));
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 115 */         if (contentName.trim().equals("solicitud")) {
/* 116 */           int n; while ((n = in.read(buffer, 0, buffer.length)) > 0);
/*     */           
/* 118 */           String temporal = new String(buffer);
/* 119 */           idsol = Integer.parseInt(temporal.trim());
/*     */           
/* 121 */           VSolicitudesDAO sf = new VSolicitudesDAO();
/* 122 */           regSol = sf.getSolicitud(idsol);
/* 123 */           sf.close();
/* 124 */           in.close();
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 129 */         if (contentName.trim().equals("helpDesk")) {
/* 130 */           int n; while ((n = in.read(buffer, 0, buffer.length)) > 0);
/*     */           
/* 132 */           String temporal = new String(buffer);
/* 133 */           helpDesk = Integer.parseInt(temporal.trim());
/* 134 */           in.close();
/*     */         } 
/*     */         
/* 137 */         if (contentName.trim().equals("upload")) {
/* 138 */           if (fixedFileName.equals("")) {
/* 139 */             fixedFileName = "anexo";
/*     */           }
/*     */           
/* 142 */           ArchivosSolicitudDAO asf = new ArchivosSolicitudDAO();
/* 143 */           int siguiente = asf.siguiente(idsol);
/* 144 */           String nombreArchivo = idsol + "_" + siguiente + "_" + fixedFileName;
/* 145 */           FechaDTO oFecha = new FechaDTO(Utilidades.fechaActual());
/* 146 */           String toPath = Utilidades.verificarDirectorioDestino(ParametrosDTO.getString("archivos"), oFecha.getAnno(), oFecha.getMes());
/* 147 */           anexo = new File(toPath + "/" + nombreArchivo);
/*     */           
/* 149 */           FileOutputStream out = new FileOutputStream(anexo);
/* 150 */           count = 0;
/*     */           int n;
/* 152 */           while ((n = in.read(buffer, 0, buffer.length)) > 0) {
/* 153 */             out.write(buffer, 0, n);
/* 154 */             count += n;
/*     */           } 
/* 156 */           in.close();
/* 157 */           out.close();
/*     */           
/* 159 */           if (count == 0) {
/*     */             try {
/* 161 */               anexo.delete();
/*     */             }
/* 163 */             catch (Exception e) {
/* 164 */               e.printStackTrace();
/*     */             } 
/* 166 */             anexo = null;
/*     */           } 
/*     */ 
/*     */           
/* 170 */           if (anexo != null) {
/*     */             
/* 172 */             if (count > ParametrosDTO.getFloat("tamano_archivos") * 1024.0F * 1024.0F) {
/* 173 */               anexo.delete();
/* 174 */               asf.close();
/*     */               
/* 176 */               Varios oVarios = new Varios();
/* 177 */               String mensaje = oVarios.formatMensaje("ArchivoMuyGrande", "" + (count / 1024 / 1024), "" + ParametrosDTO.getFloat("tamano_archivos"));
/* 178 */               throw new ClientPageRedirectException(comms.request.getAppFileURIPath("VerSolicitudNoEnviada.po?solicitud=" + idsol + "&mensaje=" + mensaje));
/*     */             } 
/* 180 */             asf.crearArchivo(idsol, nombreArchivo, count, elUsuario);
/*     */           } 
/* 182 */           asf.close();
/*     */         }
/*     */       
/*     */       } 
/* 186 */     } catch (Exception e) {
/* 187 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 190 */     int empleadoProveedor = 0;
/* 191 */     if (regSol != null) {
/* 192 */       String enviada = enviarSolicitud(regSol, idNav, anexo, helpDesk, elUsuario);
/* 193 */       if (enviada != null) {
/* 194 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=" + enviada));
/*     */       }
/* 196 */       empleadoProveedor = regSol.getEmpleadoProveedor();
/*     */     } 
/*     */     
/* 199 */     if (helpDesk == 1 && 
/* 200 */       idNav == empleadoProveedor) {
/* 201 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("VSPorAt.po?solicitud=" + idsol));
/*     */     }
/*     */ 
/*     */     
/* 205 */     if (comms.session.getSessionData().containsKey("volverA")) {
/* 206 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("VSPorAt.po?solicitud=" + (String)comms.session.getSessionData().get("volverA")));
/*     */     }
/*     */     
/* 209 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=SolicitudEnviada"));
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
/*     */   private String enviarSolicitud(VSolicitudesDTO regSol, int idNav, File anexo, int helpDesk, String elUsuario) {
/* 224 */     if (regSol == null) {
/* 225 */       return "ErrorInterno";
/*     */     }
/*     */     
/* 228 */     ServiciosDAO serf = new ServiciosDAO();
/* 229 */     ServiciosDTO servicio = serf.cargarRegistro(regSol.getCodigoServicio());
/* 230 */     serf.close();
/*     */     
/* 232 */     SisUsuariosDAO pers = new SisUsuariosDAO();
/* 233 */     SisUsuariosDTO persona = pers.cargarRegistro(regSol.getEmpleadoProveedor());
/*     */     
/* 235 */     if (!persona.getEstado().equals("A")) {
/* 236 */       return "ProveedorInactivo";
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 242 */     CaracteristicasDAO rsCaracteristica = new CaracteristicasDAO();
/* 243 */     int faltantes = rsCaracteristica.pendientes(regSol.getNumero(), servicio.getCodigo(), "C");
/* 244 */     CaracteristicasDTO car = rsCaracteristica.cargarValorTiempo(regSol.getNumero(), servicio.getCodigo());
/* 245 */     rsCaracteristica.close();
/*     */     
/* 247 */     if (faltantes > 0) {
/* 248 */       return "CamposObligatorios";
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 253 */     if (car != null) {
/* 254 */       regSol.setDuracion(car.getDuracion());
/* 255 */       regSol.setUnidadMedida(car.getUnidadMedida());
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 261 */     String fechaVigencia = Utilidades2.fechaVigencia(regSol.getUnidadMedida());
/*     */ 
/*     */     
/* 264 */     String fechaterminacion = Utilidades2.fechaTerminacion(fechaVigencia, regSol.getDuracion(), regSol.getUnidadMedida());
/*     */ 
/*     */ 
/*     */     
/* 268 */     String observacion = "";
/*     */     
/* 270 */     EstadoDAO efa = new EstadoDAO();
/* 271 */     efa.cargarTodosTipo("PRV");
/* 272 */     EstadoDTO esta = efa.next();
/* 273 */     efa.close();
/*     */     
/* 275 */     boolean enviarMensaje = (helpDesk == 0);
/*     */ 
/*     */     
/* 278 */     Varios oVarios = new Varios();
/* 279 */     if (anexo == null) {
/* 280 */       oVarios.enviarSolicitud(idNav, regSol.getCodigoEstado(), esta.getCodigo(), observacion, regSol, enviarMensaje, fechaVigencia, fechaterminacion, 0, regSol.getDuracion(), regSol.getUnidadMedida(), elUsuario);
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
/* 295 */       oVarios.enviarSolicitud(idNav, regSol.getCodigoEstado(), esta.getCodigo(), observacion, anexo, regSol, enviarMensaje, fechaVigencia, fechaterminacion, 0, regSol.getDuracion(), regSol.getUnidadMedida(), elUsuario);
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
/* 313 */     VSolicitudesDAO sf = new VSolicitudesDAO();
/*     */     
/* 315 */     regSol = sf.getSolicitud(regSol.getNumero());
/* 316 */     while (regSol.getSolicitudPadre() != -1) {
/* 317 */       VSolicitudesDTO regSolPadre = sf.getSolicitud(regSol.getSolicitudPadre());
/*     */ 
/*     */       
/* 320 */       int diasServicio = Utilidades2.diasDelServicio(regSolPadre.getDuracion(), regSolPadre.getUnidadMedida());
/*     */       
/* 322 */       long diferencia = -1L * Utilidades2.diferenciaEnDias(regSol.getFechaVigencia(), regSolPadre.getFechaVigencia());
/*     */       
/* 324 */       String nuevaFecha = Utilidades2.fechaMasDias(regSol.getFechaEstimadaTerminacion(), (int)(diasServicio - diferencia));
/*     */       
/* 326 */       if (Utilidades2.compararFechas(regSolPadre.getFechaEstimadaTerminacion(), nuevaFecha) < 0) {
/* 327 */         String s = "fecha_estimada_terminacion=" + Utilidades.formatoFecha2(Utilidades.darFormatoFecha(nuevaFecha)) + ",";
/* 328 */         s = s + "fecha_base_escalamientos=" + Utilidades.formatoFecha2(nuevaFecha) + ",";
/* 329 */         s = s + "fecha_modificacion=" + Utilidades.formatoFecha(Utilidades.ahora()) + ",";
/* 330 */         s = s + "usuario_modificacion='" + elUsuario + "'";
/* 331 */         sf.actualizarCampos(regSolPadre.getNumero(), s);
/*     */       } 
/* 333 */       regSol = sf.getSolicitud(regSolPadre.getNumero());
/*     */     } 
/*     */     
/* 336 */     sf.close();
/*     */     
/* 338 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\EnviarSolicitud.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */