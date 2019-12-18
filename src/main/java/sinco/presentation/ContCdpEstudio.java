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
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.ContCdpEstudioDTO;
/*     */ import sinco.business.FechaDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.ContCdpEstudioDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.ContCdpEstudio;
/*     */ import sinco.presentation.ContCdpEstudioHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContCdpEstudio
/*     */   implements HttpPresentation
/*     */ {
/*     */   private ContCdpEstudioHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  38 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  39 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  42 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  43 */     String _operacion = comms.request.getParameter("_operacion");
/*  44 */     if (_operacion == null || _operacion.length() == 0) {
/*  45 */       _operacion = "L";
/*     */     }
/*     */     
/*  48 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  49 */       creacion(comms);
/*     */     }
/*     */     
/*  52 */     this.pagHTML = (ContCdpEstudioHTML)comms.xmlcFactory.create(ContCdpEstudioHTML.class);
/*     */ 
/*     */     
/*  55 */     int numeroEstudio = 0;
/*     */     try {
/*  57 */       numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
/*     */     }
/*  59 */     catch (Exception e) {
/*  60 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=numeroEstudio"));
/*     */     } 
/*     */     
/*  63 */     this.pagHTML.getElementNumeroEstudioHidden().setValue("" + numeroEstudio);
/*  64 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  65 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  68 */     if (_operacion.equals("P")) {
/*  69 */       editar(comms);
/*     */     }
/*  71 */     else if (_operacion.equals("Nuevo")) {
/*  72 */       nuevo(comms);
/*     */     } 
/*  74 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  75 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private void creacion(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  87 */     String _operacion = comms.request.getParameter("_operacion");
/*  88 */     String elUsuario = "" + comms.session.getUser().getName();
/*  89 */     int numeroEstudio = 0;
/*     */     try {
/*  91 */       numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
/*     */     }
/*  93 */     catch (Exception e) {
/*  94 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=numeroEstudio"));
/*     */     } 
/*     */     
/*  97 */     int consecutivoCdp = 0;
/*     */     try {
/*  99 */       consecutivoCdp = Integer.parseInt(comms.request.getParameter("consecutivoCdp"));
/*     */     }
/* 101 */     catch (Exception e) {
/* 102 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=consecutivoCdp"));
/*     */     } 
/*     */     
/* 105 */     RespuestaBD rta = new RespuestaBD();
/* 106 */     if (_operacion.equals("E")) {
/* 107 */       ContCdpEstudioDAO ob = new ContCdpEstudioDAO();
/* 108 */       rta = ob.eliminarRegistro(numeroEstudio, consecutivoCdp);
/*     */       
/* 110 */       if (!rta.isRta()) {
/* 111 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorContCdpEstudio&p1=" + rta.getMensaje()));
/*     */       }
/* 113 */       String sPagina = "ContCdpEstudio.po?_operacion=X&numeroEstudio=" + numeroEstudio + "&consecutivoCdp=" + consecutivoCdp + "";
/* 114 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 116 */     String fechaExpedicion = comms.request.getParameter("fechaExpedicion");
/* 117 */     int codigoArea = 0;
/*     */     try {
/* 119 */       codigoArea = Integer.parseInt(comms.request.getParameter("codigoArea"));
/*     */     }
/* 121 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 124 */     String descripcion = comms.request.getParameter("descripcion");
/* 125 */     int anio = 0;
/*     */     try {
/* 127 */       anio = Integer.parseInt(comms.request.getParameter("anio"));
/*     */     }
/* 129 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 132 */     String codigoImputacion = comms.request.getParameter("codigoImputacion");
/* 133 */     double valorCertificado = 0.0D;
/*     */     try {
/* 135 */       valorCertificado = Double.parseDouble(comms.request.getParameter("valorCertificado"));
/*     */     }
/* 137 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 140 */     String vigencia = comms.request.getParameter("vigencia");
/*     */     
/* 142 */     ContCdpEstudioDAO ob = new ContCdpEstudioDAO();
/* 143 */     if (_operacion.equals("C")) {
/*     */       
/* 145 */       FechaDTO fec = new FechaDTO(fechaExpedicion);
/* 146 */       anio = fec.getAnno();
/*     */       
/* 148 */       ContCdpEstudioDTO reg = ob.cargarRegistro(anio, consecutivoCdp);
/* 149 */       if (reg != null) {
/* 150 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=CdpYaExiste"));
/*     */       }
/*     */       
/* 153 */       rta = ob.crearRegistro(numeroEstudio, anio, consecutivoCdp, fechaExpedicion, codigoArea, descripcion, codigoImputacion, valorCertificado, vigencia, elUsuario);
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
/* 164 */       consecutivoCdp = rta.getSecuencia();
/*     */     } else {
/*     */       
/* 167 */       rta = ob.modificarRegistro(numeroEstudio, anio, consecutivoCdp, fechaExpedicion, codigoArea, descripcion, codigoImputacion, valorCertificado, vigencia, elUsuario);
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
/* 179 */     if (!rta.isRta()) {
/* 180 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorContCdpEstudio&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 183 */     String sPagina = "ContCdpEstudio.po?_operacion=L&numeroEstudio=" + numeroEstudio + "&consecutivoCdp=" + consecutivoCdp + "";
/* 184 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 195 */     int anio = 0;
/*     */     try {
/* 197 */       anio = Integer.parseInt(comms.request.getParameter("anio"));
/*     */     }
/* 199 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 202 */     int consecutivoCdp = 0;
/*     */     try {
/* 204 */       consecutivoCdp = Integer.parseInt(comms.request.getParameter("consecutivoCdp"));
/*     */     }
/* 206 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 209 */     ContCdpEstudioDAO ob = new ContCdpEstudioDAO();
/* 210 */     ContCdpEstudioDTO reg = ob.cargarRegistro(anio, consecutivoCdp);
/* 211 */     if (reg != null) {
/* 212 */       this.pagHTML.getElementConsecutivoCdp().setValue("" + reg.getConsecutivoCdp());
/* 213 */       this.pagHTML.getElementFechaExpedicion().setValue("" + Utilidades.darFormatoFecha(reg.getFechaExpedicion()));
/* 214 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 215 */       this.pagHTML.getElementValorCertificado().setValue("" + Utilidades.formatDouble(reg.getValorCertificado()));
/* 216 */       this.pagHTML.getElementVigencia().setValue("" + reg.getVigencia());
/* 217 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 218 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 219 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 220 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 221 */       HTMLSelectElement combo = this.pagHTML.getElementCodigoArea();
/* 222 */       llenarCombo(combo, "unidades_dependencia", "codigo ", "descripcion", "1=1", "" + reg.getCodigoArea(), true);
/*     */       
/* 224 */       combo = this.pagHTML.getElementCodigoImputacion();
/* 225 */       llenarCombo(combo, "cont_imputacion_presupuestal", "codigo_imputacion", "descripcion", "anio=" + reg.getAnio(), "" + reg.getCodigoImputacion(), true);
/*     */       
/* 227 */       this.pagHTML.getElementConsecutivoCdp().setReadOnly(true);
/* 228 */       this.pagHTML.getElementAnio().setValue("" + reg.getAnio());
/*     */     } 
/* 230 */     this.pagHTML.getElement_operacion().setValue("M");
/* 231 */     activarVista("nuevo");
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
/*     */   private void nuevo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 243 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 245 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 246 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 248 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 251 */     ContCdpEstudioDAO rs = new ContCdpEstudioDAO();
/* 252 */     ContCdpEstudioDTO reg = rs.consultarConsecutivoCDP();
/* 253 */     int cdp = reg.getConsecutivoCdp() + 1;
/* 254 */     this.pagHTML.getElementConsecutivoCdp().setValue("" + cdp);
/* 255 */     this.pagHTML.getElementConsecutivoCdp().setReadOnly(true);
/*     */ 
/*     */ 
/*     */     
/* 259 */     activarVista("nuevo");
/* 260 */     HTMLSelectElement combo = this.pagHTML.getElementCodigoArea();
/* 261 */     llenarCombo(combo, "unidades_dependencia", "codigo ", "descripcion", "1=1", "", true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
/* 272 */     activarVista("consulta");
/* 273 */     int numeroEstudio = 0;
/*     */     try {
/* 275 */       numeroEstudio = Integer.parseInt(comms.request.getParameter("numeroEstudio"));
/*     */     }
/* 277 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 280 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 285 */     ContCdpEstudioDAO ob = new ContCdpEstudioDAO();
/* 286 */     Collection<ContCdpEstudioDTO> arr = ob.cargarTodos(numeroEstudio);
/*     */     
/* 288 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 289 */     int cuantas = 0;
/* 290 */     Iterator<ContCdpEstudioDTO> iterator = arr.iterator();
/* 291 */     while (iterator.hasNext()) {
/* 292 */       ContCdpEstudioDTO reg = (ContCdpEstudioDTO)iterator.next();
/* 293 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 294 */       String url = "ContCdpEstudio.po?_operacion=P&anio=" + reg.getAnio() + "&numeroEstudio=" + reg.getNumeroEstudio() + "&consecutivoCdp=" + reg.getConsecutivoCdp() + "";
/* 295 */       eltr.appendChild(newtdhref("" + reg.getConsecutivoCdp(), url));
/* 296 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaExpedicion())));
/* 297 */       eltr.appendChild(newtd("" + reg.getNombreCodigoArea()));
/* 298 */       eltr.appendChild(newtd("" + reg.getDescripcion()));
/* 299 */       eltr.appendChild(newtd("" + reg.getNombreCodigoImputacion()));
/* 300 */       eltr.appendChild(newtd("" + Utilidades.miles(reg.getValorCertificado())));
/* 301 */       hte.appendChild(eltr);
/* 302 */       cuantas++;
/*     */     } 
/* 304 */     arr.clear();
/* 305 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/*     */   private void activarVista(String vista) {
/* 318 */     if (!vista.equals("nuevo")) {
/* 319 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 320 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 322 */     if (!vista.equals("consulta")) {
/* 323 */       HTMLElement sel = this.pagHTML.getElementDivResultados();
/* 324 */       sel.getParentNode().removeChild(sel);
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
/*     */   private Attr newAttr(String tipo, String valor) {
/* 338 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 339 */     atrib.setValue(valor);
/* 340 */     return atrib;
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
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 353 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 354 */     Element enlace = this.pagHTML.createElement("a");
/* 355 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 356 */     enlace.appendChild(hijo);
/* 357 */     Attr donde = this.pagHTML.createAttribute("href");
/* 358 */     donde.setValue(vinculo);
/* 359 */     enlace.setAttributeNode(donde);
/* 360 */     td.appendChild(enlace);
/* 361 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 362 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 372 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 373 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 374 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 375 */     return td;
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
/*     */   private void llenarCombo(HTMLSelectElement combo, String tabla, String codigo, String descripcion, String condicion, String defecto, boolean dejarBlanco) {
/* 397 */     if (dejarBlanco) {
/* 398 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 399 */       op.setValue("");
/* 400 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 401 */       combo.appendChild(op);
/*     */     } 
/*     */     
/* 404 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 405 */     Collection arr = rsTGen.cargarTodosArr(tabla, codigo, descripcion, condicion);
/* 406 */     rsTGen.close();
/*     */     
/* 408 */     Iterator iterator = arr.iterator();
/* 409 */     while (iterator.hasNext()) {
/* 410 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 411 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 412 */       op.setValue("" + regGeneral.getCodigo());
/* 413 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 414 */       if (defecto != null && defecto.equals(regGeneral.getCodigo())) {
/* 415 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 416 */         escogida.setValue("on");
/* 417 */         op.setAttributeNode(escogida);
/*     */       } 
/* 419 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ContCdpEstudio.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */