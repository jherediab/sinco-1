/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.AMAccionesDTO;
/*     */ import sinco.business.AMCausasDTO;
/*     */ import sinco.business.AMSeguimientoDTO;
/*     */ import sinco.business.ArchivosSolicitudDTO;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AMAccionesDAO;
/*     */ import sinco.data.AMCausasDAO;
/*     */ import sinco.data.AMSeguimientoDAO;
/*     */ import sinco.data.ArchivosSolicitudDAO;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.SeguridadDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.AMDetalleCausasV2;
/*     */ import sinco.presentation.AMDetalleCausasV2HTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AMDetalleCausasV2
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AMDetalleCausasV2HTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  50 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  51 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  54 */     String operacion = comms.request.getParameter("_operacion");
/*  55 */     if (operacion == null || operacion.length() == 0) {
/*  56 */       operacion = "";
/*     */     }
/*     */     
/*  59 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  60 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/*  62 */     int numero = 0;
/*     */     try {
/*  64 */       numero = Integer.parseInt(comms.request.getParameter("numero"));
/*     */     }
/*  66 */     catch (Exception e) {
/*  67 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=numero"));
/*     */     } 
/*     */     
/*  70 */     int causa = 0;
/*     */     try {
/*  72 */       causa = Integer.parseInt(comms.request.getParameter("causa"));
/*     */     }
/*  74 */     catch (Exception e) {
/*  75 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=causa"));
/*     */     } 
/*     */     
/*  78 */     int consecutivo = 0;
/*  79 */     if (operacion.equals("P")) {
/*     */       try {
/*  81 */         consecutivo = Integer.parseInt(comms.request.getParameter("consecutivo"));
/*     */       }
/*  83 */       catch (Exception e) {
/*  84 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=consecutivo"));
/*     */       } 
/*     */     }
/*     */     
/*  88 */     int lectura = 0;
/*     */     try {
/*  90 */       lectura = Integer.parseInt(comms.request.getParameter("lectura"));
/*     */     }
/*  92 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  95 */     SisUsuariosDAO pf = new SisUsuariosDAO();
/*  96 */     SisUsuariosDTO elNavegante = pf.cargarRegistro(Integer.parseInt((String)comms.session.getSessionData().get("miId")));
/*     */     
/*  98 */     AMAccionesDAO rsAcciones = new AMAccionesDAO();
/*  99 */     AMAccionesDTO regAcciones = rsAcciones.cargarRegistro(numero);
/*     */     
/* 101 */     AreasDAO areaf = new AreasDAO();
/* 102 */     AreasDTO regAreaProveedor = areaf.getArea(regAcciones.getAreaImplanta());
/* 103 */     areaf.close();
/*     */     
/* 105 */     SeguridadDAO rsSeguridad = new SeguridadDAO();
/* 106 */     if (!rsSeguridad.getEstadoConexion()) {
/* 107 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 109 */     boolean permisoVerBitacora = rsSeguridad.tieneLlave(miGrupo, "oVerBitacora");
/* 110 */     rsSeguridad.close();
/*     */ 
/*     */     
/* 113 */     this.pagHTML = (AMDetalleCausasV2HTML)comms.xmlcFactory.create(AMDetalleCausasV2HTML.class);
/* 114 */     AMCausasDAO rsAMCausas = new AMCausasDAO();
/* 115 */     if (!rsAMCausas.getEstadoConexion()) {
/* 116 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 118 */     AMCausasDTO regAMCausas = rsAMCausas.cargarAMCausa(numero, causa);
/* 119 */     rsAMCausas.close();
/*     */     
/* 121 */     if (regAMCausas == null) {
/* 122 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoLeeRegistro&p1=AMCausas"));
/*     */     }
/* 124 */     this.pagHTML.setTextPorque("" + regAMCausas.getPorque());
/* 125 */     this.pagHTML.setTextAccion("" + regAMCausas.getAccion());
/*     */     
/* 127 */     this.pagHTML.setTextNumero("" + regAMCausas.getNumero());
/* 128 */     this.pagHTML.setTextBeneficio("" + regAMCausas.getBeneficio());
/* 129 */     this.pagHTML.setTextResponsable("" + regAMCausas.getNombreResponsable());
/* 130 */     this.pagHTML.setTextFecha_inicio("" + Utilidades.darFormatoFecha(regAMCausas.getFechaInicio()));
/* 131 */     this.pagHTML.setTextFecha_estimada_terminacion("" + Utilidades.darFormatoFecha(regAMCausas.getFechaEstimadaTerminacion()));
/* 132 */     this.pagHTML.setTextFecha_real_terminacion("" + Utilidades.darFormatoFecha(regAMCausas.getFechaRealTerminacion()));
/* 133 */     this.pagHTML.setTextNombreEstado("" + regAMCausas.getNombreEstado());
/* 134 */     this.pagHTML.setTextProrrogas("" + regAMCausas.getProrrogas());
/*     */     
/* 136 */     this.pagHTML.getElementNumeroV().setValue("" + numero);
/* 137 */     this.pagHTML.getElementNumeroAr().setValue("" + numero);
/* 138 */     this.pagHTML.getElementCausaAr().setValue("" + causa);
/* 139 */     this.pagHTML.getElementLlave().setValue("" + numero);
/* 140 */     this.pagHTML.getElementLlave2().setValue("" + causa);
/*     */ 
/*     */ 
/*     */     
/* 144 */     if ((idNav != regAMCausas.getResponsable() && idNav != regAreaProveedor.getPersonaResponsable() && idNav != regAcciones.getEmpleadoCliente()) || lectura == 1) {
/*     */ 
/*     */ 
/*     */       
/* 148 */       HTMLElement eltr = this.pagHTML.getElementTrAgregarArchivo();
/* 149 */       eltr.getParentNode().removeChild(eltr);
/*     */     } 
/*     */     
/* 152 */     boolean cerrada = (regAMCausas.getEstado() != 1);
/*     */     
/* 154 */     agregarArchivos(numero, causa, regAcciones.getEmpleadoCliente(), idNav);
/* 155 */     agregarSeguimiento(numero, causa, cerrada);
/*     */     
/* 157 */     if (!permisoVerBitacora) {
/* 158 */       HTMLElement eltr = this.pagHTML.getElementTrBitacora();
/* 159 */       eltr.getParentNode().removeChild(eltr);
/*     */     } 
/*     */ 
/*     */     
/* 163 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 164 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private int agregarSeguimiento(int numero, int causa, boolean cerrada) {
/* 181 */     AMSeguimientoDAO rs = new AMSeguimientoDAO();
/* 182 */     if (!rs.getEstadoConexion()) {
/* 183 */       return 0;
/*     */     }
/* 185 */     Collection arr = rs.cargarTodos(numero, causa);
/*     */ 
/*     */     
/* 188 */     rs.close();
/*     */ 
/*     */     
/* 191 */     HTMLTableElement hte = this.pagHTML.getElementDetalleS();
/* 192 */     boolean fondo = true;
/* 193 */     boolean hay = false;
/* 194 */     int cuantos = 0;
/*     */     
/* 196 */     Iterator iterator = arr.iterator();
/* 197 */     while (iterator.hasNext()) {
/* 198 */       AMSeguimientoDTO reg = (AMSeguimientoDTO)iterator.next();
/*     */       
/* 200 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 201 */       hay = true;
/* 202 */       fondo = !fondo;
/* 203 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 205 */       String automatico = reg.getAutomatico();
/*     */       
/* 207 */       if (automatico.equals("S") || cerrada || !Utilidades.darFormatoFecha(reg.getFecha()).equals(Utilidades.darFormatoFecha(Utilidades.fechaActual()))) {
/*     */ 
/*     */         
/* 210 */         eltr.appendChild(newtd("" + reg.getObservacion()));
/*     */       } else {
/*     */         
/* 213 */         String url = "AMAgregarSeguimiento.po?_operacion=P&numero=" + numero + "&causa=" + causa + "&consecutivo=" + reg.getConsecutivo();
/* 214 */         eltr.appendChild(newtdhref("" + reg.getObservacion(), url, false));
/*     */       } 
/*     */       
/* 217 */       eltr.appendChild(newtd("" + reg.getFecha()));
/* 218 */       eltr.appendChild(newtd("" + reg.getNombre()));
/* 219 */       hte.appendChild(eltr);
/*     */       
/* 221 */       if (automatico.equals("N")) {
/* 222 */         cuantos++;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 227 */     if (!hay) {
/* 228 */       Element divArchivos = this.pagHTML.getElementIdObservaciones();
/* 229 */       divArchivos.getParentNode().removeChild(divArchivos);
/*     */     } 
/* 231 */     return cuantos;
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
/*     */   private HTMLElement newtdhref(String contenido, String vinculo, boolean nuevaVentana) {
/* 248 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 249 */     Element enlace = this.pagHTML.createElement("a");
/* 250 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 251 */     enlace.appendChild(hijo);
/* 252 */     Attr donde = this.pagHTML.createAttribute("href");
/* 253 */     donde.setValue(vinculo);
/* 254 */     enlace.setAttributeNode(donde);
/* 255 */     if (nuevaVentana) {
/* 256 */       enlace.setAttributeNode(newAttr("TARGET", "_blank"));
/*     */     }
/*     */     
/* 259 */     td.appendChild(enlace);
/* 260 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 261 */     return td;
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
/*     */   private HTMLElement newtd(String contenido) {
/* 273 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 274 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 275 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 276 */     return td;
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
/*     */   private Attr newAttr(String tipo, String valor) {
/* 289 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 290 */     atrib.setValue(valor);
/* 291 */     return atrib;
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
/*     */   private void llenarCombo(TGeneralDAO rsTGen, HTMLSelectElement combo, String tabla, String codigo, String descripcion, String condicion) {
/* 313 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 314 */     op.setValue("");
/* 315 */     op.appendChild(this.pagHTML.createTextNode(""));
/* 316 */     combo.appendChild(op);
/*     */     
/* 318 */     rsTGen.cargarTodos(tabla, codigo, descripcion, condicion + " order by " + codigo);
/*     */     TGeneralDTO RegGeneral;
/* 320 */     while ((RegGeneral = rsTGen.next()) != null) {
/* 321 */       op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 322 */       op.setValue("" + RegGeneral.getCodigo());
/* 323 */       op.appendChild(this.pagHTML.createTextNode(RegGeneral.getDescripcion()));
/* 324 */       combo.appendChild(op);
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
/*     */   private void agregarArchivos(int numero, int causa, int cliente, int idNav) {
/* 338 */     HTMLTableElement tabla = this.pagHTML.getElementDetalleArchivos();
/* 339 */     ArchivosSolicitudDAO asf = new ArchivosSolicitudDAO();
/* 340 */     asf.getArchivosAccionMejora(numero, causa);
/* 341 */     ArchivosSolicitudDTO asv = asf.nextA();
/*     */     
/* 343 */     this.pagHTML.getElementIdNumeroArch().setValue("" + numero);
/* 344 */     this.pagHTML.getElementIdCausaArch().setValue("" + causa);
/*     */     
/* 346 */     boolean hay = false;
/* 347 */     boolean fondo = true;
/* 348 */     while (asv != null) {
/* 349 */       hay = true;
/*     */       
/* 351 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 352 */       fondo = !fondo;
/* 353 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 355 */       if (asv.getEstado().equals("B")) {
/* 356 */         eltr.appendChild(newtd("Borrado"));
/*     */       } else {
/*     */         
/* 359 */         HTMLElement tdMarca = (HTMLElement)this.pagHTML.createElement("td");
/*     */         
/* 361 */         HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
/* 362 */         checkbox.setAttribute("type", "checkbox");
/* 363 */         checkbox.setName("" + asv.getConsecutivo());
/* 364 */         tdMarca.appendChild(checkbox);
/*     */         
/* 366 */         eltr.appendChild(tdMarca);
/*     */       } 
/*     */       
/* 369 */       if (asv.getEstado().equals("B")) {
/* 370 */         eltr.appendChild(newtd("Borrado"));
/*     */       }
/* 372 */       else if (ParametrosDTO.getString("medio_descarga_archivos").equals("ftp")) {
/* 373 */         eltr.appendChild(newtdhref(asv.getArchivo(), ParametrosDTO.getString("ftp") + asv.getArchivo(), true));
/*     */       } else {
/*     */         
/* 376 */         eltr.appendChild(newtdhref(asv.getArchivo(), "VerArchivo.po?ruta=archivos_acciones&archivo=" + asv.getArchivo(), true));
/*     */       } 
/*     */       
/* 379 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(asv.getFechaInsercion())));
/*     */       
/* 381 */       tabla.appendChild(eltr);
/* 382 */       asv = asf.nextA();
/*     */     } 
/* 384 */     asf.close();
/*     */     
/* 386 */     if (cliente != idNav) {
/* 387 */       Element divArchivos = this.pagHTML.getElementIdBotonArchivos();
/* 388 */       divArchivos.getParentNode().removeChild(divArchivos);
/*     */     } 
/*     */ 
/*     */     
/* 392 */     if (!hay) {
/* 393 */       Element divArchivos = this.pagHTML.getElementIdmostrararchivos();
/* 394 */       divArchivos.getParentNode().removeChild(divArchivos);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AMDetalleCausasV2.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */