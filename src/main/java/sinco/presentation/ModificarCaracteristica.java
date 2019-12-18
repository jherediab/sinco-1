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
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLImageElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.AtencionSolicitudDTO;
/*     */ import sinco.business.CaracteristicasDTO;
/*     */ import sinco.business.CaracteristicasValorDTO;
/*     */ import sinco.business.DetalleSolicitudDTO;
/*     */ import sinco.business.EstadoDTO;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.business.VSolicitudesDTO;
/*     */ import sinco.data.AtencionSolicitudDAO;
/*     */ import sinco.data.CaracteristicasDAO;
/*     */ import sinco.data.CaracteristicasValorDAO;
/*     */ import sinco.data.DetalleSolicitudDAO;
/*     */ import sinco.data.EstadoDAO;
/*     */ import sinco.data.SeguridadDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.data.VSolicitudesDAO;
/*     */ import sinco.presentation.ModificarCaracteristica;
/*     */ import sinco.presentation.ModificarDetallesHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModificarCaracteristica
/*     */   implements HttpPresentation
/*     */ {
/*     */   private ModificarDetallesHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  46 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  47 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  50 */     int idsol = Integer.parseInt(comms.request.getParameter("solicitud"));
/*     */     
/*  52 */     VSolicitudesDAO sf = new VSolicitudesDAO();
/*  53 */     VSolicitudesDTO regSol = sf.getSolicitud(idsol);
/*  54 */     sf.close();
/*     */     
/*  56 */     EstadoDAO estadof = new EstadoDAO();
/*  57 */     EstadoDTO estado = estadof.getEstado(regSol.getCodigoEstado());
/*  58 */     estadof.close();
/*     */     
/*  60 */     ServiciosDAO serf = new ServiciosDAO();
/*  61 */     ServiciosDTO regServicio = serf.cargarRegistro(regSol.getCodigoServicio());
/*  62 */     serf.close();
/*     */     
/*  64 */     this.pagHTML = (ModificarDetallesHTML)comms.xmlcFactory.create(ModificarDetallesHTML.class);
/*     */     
/*  66 */     this.pagHTML.setTextNumerosolicitud("" + regSol.getNumeroMostrar());
/*  67 */     this.pagHTML.setTextServicio(regSol.getNombreServicio());
/*  68 */     this.pagHTML.setTextAreap(regSol.getNombreAreaProveedora());
/*  69 */     this.pagHTML.setTextProveedor(regSol.getNombreProveedor());
/*  70 */     this.pagHTML.setTextFecha(Utilidades.darFormatoFecha(regSol.getFechaGenerada()));
/*     */ 
/*     */     
/*  73 */     caracteristicas(comms, regSol, regServicio, estado);
/*     */ 
/*     */     
/*  76 */     atenciones(comms, idsol);
/*     */ 
/*     */     
/*  79 */     HTMLInputElement hies = this.pagHTML.getElementIdSolDetalle();
/*  80 */     hies.setValue("" + idsol);
/*     */     
/*  82 */     String pagina = comms.request.getParameter("pagina_siguiente");
/*  83 */     this.pagHTML.getElementPagina_siguiente().setValue(pagina);
/*     */ 
/*     */     
/*  86 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */ 
/*     */     
/*  89 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  90 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   public void caracteristicas(HttpPresentationComms comms, VSolicitudesDTO regSol, ServiciosDTO regServicio, EstadoDTO estado) throws HttpPresentationException, KeywordValueException {
/* 112 */     HTMLTableElement tabla = this.pagHTML.getElementIdtblDetalle();
/* 113 */     DetalleSolicitudDAO dsf = new DetalleSolicitudDAO();
/*     */     
/* 115 */     String elUsuario = "" + comms.session.getUser().getName();
/* 116 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/* 117 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 119 */     boolean haycaracteristicas = false;
/*     */     
/* 121 */     SeguridadDAO rsSeguridad = new SeguridadDAO();
/* 122 */     if (!rsSeguridad.getEstadoConexion()) {
/* 123 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 125 */     boolean oCalificarFortalezas = rsSeguridad.tieneLlave(miGrupo, "oCalificarFortalezas");
/* 126 */     rsSeguridad.close();
/*     */     
/* 128 */     if (regServicio.getTipoServicio().equals(Integer.toString(2)) && idNav != regSol.getEmpleadoProveedor()) {
/* 129 */       dsf.crearHallazgos(regSol.getNumero(), regServicio.getCodigo(), elUsuario);
/* 130 */       this.pagHTML.getElementSolAuditoria().setValue("S");
/*     */     } 
/*     */     
/* 133 */     dsf.cargarParaSolicitud(regSol.getNumero());
/* 134 */     DetalleSolicitudDTO regDs = dsf.next();
/*     */     
/* 136 */     CaracteristicasDAO cf = new CaracteristicasDAO();
/* 137 */     CaracteristicasValorDAO rsTGen = new CaracteristicasValorDAO();
/*     */     
/* 139 */     String cadenaFechas = "";
/*     */ 
/*     */     
/* 142 */     boolean fondo = true;
/* 143 */     while (regDs != null) {
/*     */       HTMLSelectElement combo; HTMLInputElement inp;
/* 145 */       CaracteristicasDTO regCar = cf.cargarRegistro(regSol.getCodigoServicio(), regDs.getCodigoCaracteristica());
/*     */       
/* 147 */       if (regCar == null) {
/* 148 */         regDs = dsf.next();
/*     */         
/*     */         continue;
/*     */       } 
/* 152 */       int metodo = 0;
/* 153 */       if (idNav == regSol.getEmpleadoCliente() && estado.getTipoEstado().equals("INI") && regCar.getRol().equals("C"))
/*     */       {
/*     */         
/* 156 */         metodo = 1;
/*     */       }
/*     */       
/* 159 */       if (idNav == regSol.getEmpleadoProveedor() && estado.getTipoEstado().equals("PRV") && regCar.getRol().equals("P"))
/*     */       {
/*     */         
/* 162 */         metodo = 2;
/*     */       }
/*     */       
/* 165 */       if (metodo == 0) {
/* 166 */         regDs = dsf.next();
/*     */         
/*     */         continue;
/*     */       } 
/* 170 */       haycaracteristicas = true;
/* 171 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 172 */       HTMLElement tdCaracteristica = (HTMLElement)this.pagHTML.createElement("td");
/* 173 */       HTMLElement tdDescripcion = (HTMLElement)this.pagHTML.createElement("td");
/*     */       
/* 175 */       fondo = !fondo;
/* 176 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */ 
/*     */       
/* 179 */       tdCaracteristica.appendChild(this.pagHTML.createTextNode(regCar.getDescripcion()));
/*     */       
/* 181 */       switch (Integer.parseInt(regCar.getTipo())) {
/*     */         case 1:
/*     */         case 3:
/*     */         case 4:
/* 185 */           inp = (HTMLInputElement)this.pagHTML.createElement("input");
/* 186 */           inp.setName("C_" + regCar.getCodigo() + "_" + regDs.getConsecutivoDetalle());
/* 187 */           inp.setId("C_" + regCar.getCodigo() + "_" + regDs.getConsecutivoDetalle());
/* 188 */           inp.setValue(regDs.getObservacion());
/* 189 */           inp.setAttributeNode(newAttr("tipo", "" + regCar.getTipo()));
/* 190 */           inp.setAttributeNode(newAttr("obliga", "" + ((regCar.getObliga() == true) ? "S" : "N")));
/* 191 */           inp.setAttributeNode(newAttr("tipoVal", "" + regCar.getTipoValidacion()));
/* 192 */           inp.setAttributeNode(newAttr("carVal", "" + regCar.getCaracteristicaValida()));
/*     */           
/* 194 */           inp.setAttributeNode(newAttr("onkeyDown", "return f_salto2(event)"));
/* 195 */           inp.setAttributeNode(newAttr("onkeyUp", "return f_salto(event)"));
/* 196 */           inp.setMaxLength(regCar.getLongitud());
/*     */ 
/*     */           
/* 199 */           if (regCar.getNombreProcedimiento().length() > 0) {
/* 200 */             inp.setAttributeNode(newAttr("onblur", "ejecutarProcedimiento(this.name,this.value,'" + regCar.getNombreProcedimiento() + "'," + regCar.getCaracteristicaAnida() + ");"));
/*     */           }
/*     */           
/* 203 */           switch (Integer.parseInt(regCar.getTipo())) {
/*     */             case 1:
/* 205 */               inp.setAttributeNode(newAttr("onkeypress", "validarTecla(event, 'AL');"));
/* 206 */               inp.setSize("120");
/*     */               break;
/*     */             case 3:
/* 209 */               inp.setAttributeNode(newAttr("onkeypress", "validarTecla(event, 'DE');"));
/* 210 */               inp.setSize("30");
/*     */               break;
/*     */             case 4:
/* 213 */               inp.setAttributeNode(newAttr("onkeypress", "validarTecla(event, 'FE');"));
/* 214 */               inp.setSize("30");
/*     */               break;
/*     */           } 
/*     */           
/* 218 */           tdDescripcion.appendChild(inp);
/*     */           
/* 220 */           if (regCar.getTipo().equals("4")) {
/* 221 */             cadenaFechas = cadenaFechas + agregarCapturaFecha("C_" + regCar.getCodigo() + "_" + regDs.getConsecutivoDetalle(), "bC_" + regCar.getCodigo() + "_" + regDs.getConsecutivoDetalle());
/*     */             
/* 223 */             HTMLImageElement inp2 = (HTMLImageElement)this.pagHTML.createElement("IMG");
/* 224 */             inp2.setSrc("media/calendario.jpg");
/* 225 */             inp2.setId("bC_" + regCar.getCodigo() + "_" + regDs.getConsecutivoDetalle());
/* 226 */             inp2.setAlt("Calendario");
/* 227 */             inp2.setHeight("20");
/* 228 */             inp2.setWidth("20");
/* 229 */             tdDescripcion.appendChild(inp2);
/*     */           } 
/*     */           
/* 232 */           if (regCar.getNombreProcedimiento().length() > 0) {
/* 233 */             HTMLElement inp3 = (HTMLElement)this.pagHTML.createElement("span");
/* 234 */             inp3.setId("msgC_" + regCar.getCodigo() + "_" + regDs.getConsecutivoDetalle());
/* 235 */             inp3.setAttributeNode(newAttr("class", "error"));
/* 236 */             tdDescripcion.appendChild(inp3);
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 2:
/*     */         case 8:
/* 242 */           combo = (HTMLSelectElement)this.pagHTML.createElement("Select");
/* 243 */           combo.setName("C_" + regCar.getCodigo() + "_" + regDs.getConsecutivoDetalle());
/* 244 */           combo.setId("" + regCar.getCodigo());
/* 245 */           llenarCombo(rsTGen, combo, regCar.getTipo(), regCar.getCodigo(), regSol.getAreaProveedor(), regDs.getValor());
/*     */           
/* 247 */           if (regCar.getCaracteristicaAnida() > 0) {
/* 248 */             combo.setAttributeNode(newAttr("onchange", "buscarAnidadas(this.name,this.value," + regCar.getCaracteristicaAnida() + ");"));
/*     */           }
/*     */           
/* 251 */           combo.setAttributeNode(newAttr("onkeyDown", "return f_salto2(event)"));
/* 252 */           combo.setAttributeNode(newAttr("onkeyUp", "return f_salto(event)"));
/* 253 */           tdDescripcion.appendChild(combo);
/*     */           break;
/*     */       } 
/*     */       
/* 257 */       eltr.appendChild(tdCaracteristica);
/* 258 */       eltr.appendChild(tdDescripcion);
/* 259 */       tabla.appendChild(eltr);
/*     */       
/* 261 */       regDs = dsf.next();
/*     */     } 
/* 263 */     dsf.close();
/* 264 */     cf.close();
/* 265 */     rsTGen.close();
/*     */     
/* 267 */     if (cadenaFechas.length() > 0) {
/* 268 */       this.pagHTML.setTextJSValfechas(cadenaFechas);
/*     */     }
/*     */     
/* 271 */     if (!haycaracteristicas) {
/* 272 */       Element ce = this.pagHTML.getElementDetalles();
/* 273 */       ce.getParentNode().removeChild(ce);
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
/*     */   
/*     */   public void atenciones(HttpPresentationComms comms, int numeroSolicitud) throws HttpPresentationException, KeywordValueException {
/* 294 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/* 296 */     HTMLTableElement tablaat = this.pagHTML.getElementIdtblAtencion();
/* 297 */     AtencionSolicitudDAO af = new AtencionSolicitudDAO();
/* 298 */     af.cargarTodosParaSolicitud(numeroSolicitud);
/* 299 */     AtencionSolicitudDTO ate = af.next();
/* 300 */     boolean haycaracteristicas = false;
/*     */     
/* 302 */     boolean fondo = true;
/*     */     
/* 304 */     while (ate != null) {
/* 305 */       haycaracteristicas = true;
/* 306 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 307 */       HTMLElement tdFecha = (HTMLElement)this.pagHTML.createElement("td");
/* 308 */       HTMLElement tdDescripcion = (HTMLElement)this.pagHTML.createElement("td");
/* 309 */       tdFecha.appendChild(this.pagHTML.createTextNode(Utilidades.darFormatoFecha(ate.getFecha())));
/*     */       
/* 311 */       fondo = !fondo;
/* 312 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 314 */       int resta = Utilidades.restaFechas(ate.getFecha(), Utilidades.fechaActual());
/*     */       
/* 316 */       if (idNav == ate.getPersonaAtendio() && resta <= 1) {
/* 317 */         HTMLInputElement inp = (HTMLInputElement)this.pagHTML.createElement("input");
/* 318 */         inp.setMaxLength(1024);
/* 319 */         inp.setSize("150");
/* 320 */         inp.setName("A_" + ate.getConsecutivo());
/* 321 */         inp.setValue(ate.getObservacion());
/* 322 */         inp.setAttributeNode(newAttr("onkeypress", "validarTecla(event, 'AL');"));
/* 323 */         inp.setAttributeNode(newAttr("onkeyDown", "return f_salto2(event)"));
/* 324 */         inp.setAttributeNode(newAttr("onkeyUp", "return f_salto(event)"));
/* 325 */         tdDescripcion.appendChild(inp);
/*     */       } else {
/*     */         
/* 328 */         tdDescripcion.appendChild(this.pagHTML.createTextNode(ate.getObservacion()));
/*     */       } 
/*     */       
/* 331 */       ate = af.next();
/* 332 */       eltr.appendChild(tdFecha);
/* 333 */       eltr.appendChild(tdDescripcion);
/* 334 */       tablaat.appendChild(eltr);
/*     */     } 
/* 336 */     af.close();
/*     */ 
/*     */     
/* 339 */     if (!haycaracteristicas) {
/* 340 */       Element ce = this.pagHTML.getElementAtencion();
/* 341 */       ce.getParentNode().removeChild(ce);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 346 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 347 */     atrib.setValue(valor);
/* 348 */     return atrib;
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
/*     */   private void llenarCombo(CaracteristicasValorDAO rsTGen, HTMLSelectElement combo, String tipoCaracteristica, int caract, int areaProveedor, int defecto) {
/* 367 */     Collection arr = rsTGen.cargarTodos(caract, areaProveedor, tipoCaracteristica, 0);
/* 368 */     Iterator iterator = arr.iterator();
/* 369 */     while (iterator.hasNext()) {
/* 370 */       CaracteristicasValorDTO reg = (CaracteristicasValorDTO)iterator.next();
/* 371 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 372 */       op.setValue("" + reg.getValor());
/* 373 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 374 */       if (defecto == reg.getValor()) {
/* 375 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 376 */         escogida.setValue("on");
/* 377 */         op.setAttributeNode(escogida);
/*     */       } 
/* 379 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 389 */   private String agregarCapturaFecha(String input, String boton) { return "Calendar.setup({inputField :  '" + input + "'," + "ifFormat   :  '%Y-%m-%d'," + "button     :  '" + boton + "'" + "});"; }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ModificarCaracteristica.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */