

/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import sinco.business.AMAccionesDTO;
/*     */ import sinco.business.AMCausasDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AMAccionesDAO;
/*     */ import sinco.data.AMCausasDAO;
/*     */ import sinco.data.SeguridadDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.AMActCausasV2;
/*     */ import sinco.presentation.AMActCausasV2HTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AMActCausasV2
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AMActCausasV2HTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  41 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  42 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  45 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  46 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/*  48 */     String operacion = comms.request.getParameter("_operacion");
/*  49 */     if (operacion == null || operacion.length() == 0) {
/*  50 */       operacion = "";
/*     */     }
/*  52 */     int numero = 0;
/*     */     try {
/*  54 */       numero = Integer.parseInt(comms.request.getParameter("numero"));
/*     */     }
/*  56 */     catch (Exception e) {
/*  57 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=numero"));
/*     */     } 
/*     */     
/*  60 */     AMAccionesDAO rsAcciones = new AMAccionesDAO();
/*  61 */     AMAccionesDTO regAcciones = rsAcciones.cargarRegistro(numero);
/*  62 */     if (regAcciones == null) {
/*  63 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoLeeRegistro&p1=AccionesMejoramiento"));
/*     */     }
/*     */     
/*  66 */     SeguridadDAO rsSeguridad = new SeguridadDAO();
/*  67 */     if (!rsSeguridad.getEstadoConexion()) {
/*  68 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*  70 */     boolean mostrarTodos = rsSeguridad.tieneLlave(miGrupo, "am_mostrarPersonasArea");
/*  71 */     rsSeguridad.close();
/*     */     
/*  73 */     int causa = 0;
/*     */     
/*  75 */     if (operacion.equals("M") || operacion.equals("P") || operacion.equals("RESP")) {
/*     */       try {
/*  77 */         causa = Integer.parseInt(comms.request.getParameter("causa"));
/*     */       }
/*  79 */       catch (Exception e) {
/*  80 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=causa"));
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*  85 */     if (operacion.equals("C") || operacion.equals("M")) {
/*  86 */       creacion(comms, regAcciones, operacion, causa);
/*     */     
/*     */     }
/*  89 */     else if (operacion.equals("RESP")) {
/*  90 */       cambioResponsable(comms, numero, causa, idNav);
/*     */     } 
/*     */     
/*  93 */     this.pagHTML = (AMActCausasV2HTML)comms.xmlcFactory.create(AMActCausasV2HTML.class);
/*     */ 
/*     */     
/*  96 */     if (operacion.equals("P")) {
/*  97 */       detalle(comms, regAcciones, causa, mostrarTodos);
/*     */     } else {
/*     */       
/* 100 */       nuevo(comms, regAcciones, mostrarTodos);
/*     */     } 
/*     */     
/* 103 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 104 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private void llenarCombo(TGeneralDAO rsTGen, HTMLSelectElement combo, String tabla, String codigo, String descripcion, String defecto) {
/* 124 */     rsTGen.cargarTodos(tabla, codigo, descripcion, "1=1 order by " + codigo);
/*     */     TGeneralDTO RegGeneral;
/* 126 */     while ((RegGeneral = rsTGen.next()) != null) {
/* 127 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 128 */       op.setValue("" + RegGeneral.getCodigo());
/* 129 */       op.appendChild(this.pagHTML.createTextNode(RegGeneral.getDescripcion()));
/* 130 */       if (defecto.equals(RegGeneral.getCodigo())) {
/* 131 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 132 */         escogida.setValue("on");
/* 133 */         op.setAttributeNode(escogida);
/*     */       } 
/* 135 */       combo.appendChild(op);
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
/*     */   
/*     */   private void comboPersonas(HTMLSelectElement combo, int numeroAccion, String impacto, int defecto, int area, boolean mostrarTodos) {
/* 155 */     SisUsuariosDAO pf = new SisUsuariosDAO();
/* 156 */     Collection arr = new ArrayList();
/*     */     
/* 158 */     if (mostrarTodos) {
/* 159 */       arr = pf.cargarActivoDeArea(area);
/*     */     } else {
/*     */       
/* 162 */       arr = pf.cargarPorCodigo(defecto);
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
/* 175 */     Iterator iterator = arr.iterator();
/* 176 */     while (iterator.hasNext()) {
/* 177 */       SisUsuariosDTO personaDelArea = (SisUsuariosDTO)iterator.next();
/*     */       
/* 179 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 180 */       op.setValue("" + personaDelArea.getCodigoEmpleado());
/* 181 */       op.appendChild(this.pagHTML.createTextNode(personaDelArea.getApellidos() + " " + personaDelArea.getNombres()));
/*     */       
/* 183 */       if (defecto == personaDelArea.getCodigoEmpleado()) {
/* 184 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 185 */         escogida.setValue("on");
/* 186 */         op.setAttributeNode(escogida);
/*     */       } 
/*     */       
/* 189 */       combo.appendChild(op);
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
/*     */   private void creacion(HttpPresentationComms comms, AMAccionesDTO regAcciones, String operacion, int causa) throws HttpPresentationException, KeywordValueException {
/* 206 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */     
/* 208 */     String porque = comms.request.getParameter("porque");
/* 209 */     String accion = comms.request.getParameter("accion");
/*     */     
/* 211 */     String beneficio = comms.request.getParameter("beneficio");
/* 212 */     int responsable = Integer.parseInt(comms.request.getParameter("responsable"));
/* 213 */     String fechaEstimadaTerminacion = comms.request.getParameter("fechaEstimadaTerminacion");
/* 214 */     String justificacion = "" + comms.request.getParameter("justificacion");
/*     */     
/* 216 */     if (!Utilidades.validarFormatoFecha(fechaEstimadaTerminacion)) {
/* 217 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=FechaNoValida&p1=fecha estimada terminacion"));
/*     */     }
/*     */     
/* 220 */     int estado = regAcciones.getCodigoEstado();
/*     */     
/* 222 */     AMCausasDAO rsAMCausas = new AMCausasDAO();
/* 223 */     if (!rsAMCausas.getEstadoConexion()) {
/* 224 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 226 */     RespuestaBD rta = new RespuestaBD();
/* 227 */     if (operacion.equals("C")) {
/* 228 */       rta = rsAMCausas.crearRegistro(regAcciones.getNumero(), porque, accion, beneficio, responsable, fechaEstimadaTerminacion, estado, elUsuario);
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
/*     */     }
/* 241 */     else if (operacion.equals("M")) {
/* 242 */       rta = rsAMCausas.modificarRegistro(regAcciones.getNumero(), causa, porque, accion, beneficio, responsable, fechaEstimadaTerminacion, estado, elUsuario, justificacion);
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
/* 255 */     if (rta.isRta() && regAcciones.getCodigoEstado() == 1) {
/* 256 */       rsAMCausas.actualizarFechaEstimadaTerminacion(regAcciones.getNumero(), elUsuario);
/*     */     }
/* 258 */     rsAMCausas.close();
/* 259 */     if (!rta.isRta()) {
/* 260 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorActualizandoTabla&p1=AMCausas"));
/*     */     }
/*     */     
/* 263 */     String sPagina = "AMDetalleV2.po?numero=" + regAcciones.getNumero();
/*     */     
/* 265 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/*     */   private void cambioResponsable(HttpPresentationComms comms, int numero, int causa, int idNav) throws HttpPresentationException, KeywordValueException {
/* 280 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */     
/* 282 */     int responsable = 0;
/*     */     try {
/* 284 */       responsable = Integer.parseInt(comms.request.getParameter("responsable"));
/*     */     }
/* 286 */     catch (Exception e) {}
/*     */     
/* 288 */     AMCausasDAO rsAMCausas = new AMCausasDAO();
/* 289 */     if (!rsAMCausas.getEstadoConexion()) {
/* 290 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 293 */     AMCausasDTO regAMCausa = rsAMCausas.cargarAMCausa(numero, causa);
/* 294 */     if (regAMCausa == null) {
/* 295 */       rsAMCausas.close();
/* 296 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoLeeRegistro&p1=AMCausas"));
/*     */     } 
/*     */     
/* 299 */     boolean rta = rsAMCausas.modificarResponsable(numero, causa, responsable, elUsuario);
/*     */     
/* 301 */     String observacion = "Responsable anterior " + regAMCausa.getNombreResponsable();
/* 302 */     rta = rsAMCausas.crearSeguimiento(numero, causa, observacion, idNav, 'S', elUsuario);
/*     */     
/* 304 */     rsAMCausas.close();
/* 305 */     if (!rta) {
/* 306 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorActualizandoTabla&p1=AMCausas"));
/*     */     }
/* 308 */     String sPagina = "AMDetalleCausasV2.po?numero=" + numero + "&causa=" + causa;
/* 309 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/*     */   private void detalle(HttpPresentationComms comms, AMAccionesDTO regAcciones, int causa, boolean mostrarTodos) throws HttpPresentationException, KeywordValueException {
/* 324 */     AMCausasDAO rsAMCausas = new AMCausasDAO();
/* 325 */     if (!rsAMCausas.getEstadoConexion()) {
/* 326 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 328 */     AMCausasDTO reg = rsAMCausas.cargarAMCausa(regAcciones.getNumero(), causa);
/* 329 */     rsAMCausas.close();
/* 330 */     if (reg == null) {
/* 331 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoLeeRegistro&p1=AMCausas"));
/*     */     }
/*     */     
/* 334 */     this.pagHTML.getElementNumero().setValue("" + reg.getNumero());
/* 335 */     this.pagHTML.getElementCausa().setValue("" + reg.getConsecutivo());
/*     */     
/* 337 */     this.pagHTML.getElementPorque().appendChild(this.pagHTML.createTextNode("" + reg.getPorque()));
/* 338 */     this.pagHTML.getElementAccion().appendChild(this.pagHTML.createTextNode("" + reg.getAccion()));
/* 339 */     this.pagHTML.getElementBeneficio().appendChild(this.pagHTML.createTextNode("" + reg.getBeneficio()));
/*     */     
/* 341 */     this.pagHTML.getElementFechaEstimadaTerminacion().setValue("" + Utilidades.darFormatoFecha(reg.getFechaEstimadaTerminacion()));
/* 342 */     this.pagHTML.getElementNumeroAr().setValue("" + reg.getNumero());
/* 343 */     this.pagHTML.getElementCausaAr().setValue("" + reg.getConsecutivo());
/* 344 */     this.pagHTML.getElementEstado().setValue("" + reg.getEstado());
/* 345 */     this.pagHTML.setTextNumeroCausa("" + reg.getConsecutivo());
/*     */ 
/*     */     
/* 348 */     HTMLSelectElement combo = this.pagHTML.getElementResponsable();
/* 349 */     comboPersonas(combo, regAcciones.getNumero(), regAcciones.getImpacto(), reg.getResponsable(), regAcciones.getAreaImplanta(), mostrarTodos);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 357 */     this.pagHTML.getElement_operacion().setValue("M");
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
/*     */   private void nuevo(HttpPresentationComms comms, AMAccionesDTO regAcciones, boolean mostrarTodos) throws HttpPresentationException, KeywordValueException {
/* 373 */     this.pagHTML.getElementNumero().setValue("" + regAcciones.getNumero());
/*     */     
/* 375 */     HTMLSelectElement combo = this.pagHTML.getElementResponsable();
/* 376 */     comboPersonas(combo, regAcciones.getNumero(), regAcciones.getImpacto(), regAcciones.getEmpleadoCliente(), regAcciones.getAreaImplanta(), mostrarTodos);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 384 */     HTMLElement eltr = this.pagHTML.getElementTrAgregarArchivo();
/* 385 */     eltr.getParentNode().removeChild(eltr);
/*     */ 
/*     */     
/* 388 */     AMCausasDAO rsAMCausas = new AMCausasDAO();
/* 389 */     if (!rsAMCausas.getEstadoConexion()) {
/* 390 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 392 */     int causas = rsAMCausas.numeroCausas(regAcciones.getNumero()) + 1;
/* 393 */     rsAMCausas.close();
/*     */     
/* 395 */     this.pagHTML.getElementCausa().setValue("" + causas);
/*     */ 
/*     */     
/* 398 */     this.pagHTML.setTextNumeroCausa("" + causas);
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AMActCausasV2.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */