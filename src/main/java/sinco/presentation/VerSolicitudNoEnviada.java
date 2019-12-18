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
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLImageElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.ArchivosSolicitudDTO;
/*     */ import sinco.business.CaracteristicasDTO;
/*     */ import sinco.business.CaracteristicasValorDTO;
/*     */ import sinco.business.DetalleSolicitudDTO;
/*     */ import sinco.business.EstadoDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.TemporalDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.business.VSolicitudesDTO;
/*     */ import sinco.data.ArchivosSolicitudDAO;
/*     */ import sinco.data.CaracteristicasDAO;
/*     */ import sinco.data.CaracteristicasValorDAO;
/*     */ import sinco.data.DetalleSolicitudDAO;
/*     */ import sinco.data.EstadoDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.data.VSolicitudesDAO;
/*     */ import sinco.presentation.VerSolicitudNoEnviada;
/*     */ import sinco.presentation.VerSolicitudNoEnviadaHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ public class VerSolicitudNoEnviada
/*     */   implements HttpPresentation
/*     */ {
/*     */   private VerSolicitudNoEnviadaHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  51 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  52 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  54 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  55 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */ 
/*     */     
/*  58 */     int idsol = Integer.parseInt(comms.request.getParameter("solicitud"));
/*  59 */     String mensaje = comms.request.getParameter("mensaje");
/*     */     
/*  61 */     VSolicitudesDAO sf = new VSolicitudesDAO();
/*  62 */     VSolicitudesDTO regSol = sf.getSolicitud(idsol);
/*  63 */     sf.close();
/*     */     
/*  65 */     int helpDesk = 0;
/*     */     try {
/*  67 */       helpDesk = Integer.parseInt(comms.request.getParameter("helpDesk"));
/*     */     }
/*  69 */     catch (Exception e) {}
/*     */     
/*  71 */     this.pagHTML = (VerSolicitudNoEnviadaHTML)comms.xmlcFactory.create(VerSolicitudNoEnviadaHTML.class);
/*  72 */     this.pagHTML.setTextNumerosolicitud("" + regSol.getNumeroMostrar());
/*     */     
/*  74 */     if (mensaje != null) {
/*  75 */       this.pagHTML.setTextMensajeArchivo(mensaje);
/*     */     } else {
/*     */       
/*  78 */       Element trMensaje = this.pagHTML.getElementTrMensaje();
/*  79 */       trMensaje.getParentNode().removeChild(trMensaje);
/*     */     } 
/*     */     
/*  82 */     this.pagHTML.setTextServicio(regSol.getNombreServicio());
/*  83 */     this.pagHTML.setTextAreap(regSol.getNombreAreaProveedora());
/*  84 */     this.pagHTML.setTextAreacliente(regSol.getNombreAreaCliente());
/*  85 */     this.pagHTML.setTextProveedor(regSol.getNombreProveedor());
/*  86 */     this.pagHTML.setTextCliente(regSol.getNombreCliente());
/*  87 */     this.pagHTML.setTextFecha(Utilidades.darFormatoFecha(regSol.getFechaGenerada()));
/*     */     
/*  89 */     ServiciosDAO serf = new ServiciosDAO();
/*  90 */     ServiciosDTO regServicio = serf.cargarRegistro(regSol.getCodigoServicio());
/*  91 */     serf.close();
/*     */ 
/*     */     
/*  94 */     this.pagHTML.getElementNumeroAnexos().setValue("" + regServicio.getNumeroAnexosEnvio());
/*     */     
/*  96 */     this.pagHTML.setTextMensaje("" + regServicio.getMensaje());
/*     */ 
/*     */ 
/*     */     
/* 100 */     this.pagHTML.getElementSolicitud().setValue("" + idsol);
/* 101 */     this.pagHTML.getElementSegundasolicitud().setValue("" + idsol);
/* 102 */     this.pagHTML.getElementSolicitudenviar().setValue("" + idsol);
/* 103 */     this.pagHTML.getElementSolicitudcancelar().setValue("" + idsol);
/* 104 */     this.pagHTML.getElementIdSolicitudModificar().setValue("" + idsol);
/* 105 */     this.pagHTML.getElementIdSolicitudArchivos().setValue("" + idsol);
/* 106 */     this.pagHTML.getElementPagina_siguienteArc().setValue("VerSolicitudNoEnviada.po");
/* 107 */     this.pagHTML.getElementIdsolicitudmail().setValue("" + idsol);
/* 108 */     this.pagHTML.getElementHelpDesk().setValue("" + helpDesk);
/* 109 */     this.pagHTML.getElementHelpDesk1().setValue("" + helpDesk);
/* 110 */     this.pagHTML.getElementHelpDesk2().setValue("" + helpDesk);
/*     */     
/* 112 */     caracteristicas(regSol, comms);
/*     */ 
/*     */     
/* 115 */     archivosAnexos(idsol, elUsuario);
/*     */ 
/*     */ 
/*     */     
/* 119 */     EstadoDAO efa = new EstadoDAO();
/* 120 */     efa.cargarTodosTipo("AN");
/* 121 */     EstadoDTO estadoanulado = efa.next();
/* 122 */     efa.close();
/*     */     
/* 124 */     this.pagHTML.getElementEstadocancelar().setValue("" + estadoanulado.getCodigo());
/*     */ 
/*     */     
/* 127 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 128 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/* 132 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 133 */     atrib.setValue(valor);
/* 134 */     return atrib;
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
/*     */   private HTMLElement newtd(String contenido) {
/* 147 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 148 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 149 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 150 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo, boolean nuevaVentana) {
/* 160 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 161 */     Element enlace = this.pagHTML.createElement("a");
/* 162 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 163 */     enlace.appendChild(hijo);
/* 164 */     Attr donde = this.pagHTML.createAttribute("href");
/* 165 */     donde.setValue(vinculo);
/* 166 */     enlace.setAttributeNode(donde);
/* 167 */     if (nuevaVentana) {
/* 168 */       enlace.setAttributeNode(newAttr("TARGET", "_blank"));
/*     */     }
/*     */     
/* 171 */     td.appendChild(enlace);
/* 172 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 173 */     return td;
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
/*     */   private void llenarCombo(CaracteristicasValorDAO rsCar, HTMLSelectElement combo, String tipoCaracteristica, int caract, int areaProveedor, int valorDepende) {
/* 191 */     Collection arr = rsCar.cargarTodos(caract, areaProveedor, tipoCaracteristica, valorDepende);
/* 192 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 193 */     op.setValue("");
/* 194 */     op.appendChild(this.pagHTML.createTextNode(" "));
/* 195 */     combo.appendChild(op);
/* 196 */     Iterator iterator = arr.iterator();
/* 197 */     while (iterator.hasNext()) {
/* 198 */       CaracteristicasValorDTO reg = (CaracteristicasValorDTO)iterator.next();
/* 199 */       op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 200 */       op.setValue("" + reg.getValor());
/* 201 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 202 */       combo.appendChild(op);
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
/*     */   private void comboFuncionariosArea(HTMLSelectElement combo, int areaCliente) {
/* 214 */     SisUsuariosDAO rs = new SisUsuariosDAO();
/* 215 */     Collection<SisUsuariosDTO> arr = rs.cargarActivoDeArea(areaCliente);
/*     */     
/* 217 */     Iterator<SisUsuariosDTO> iterator = arr.iterator();
/* 218 */     while (iterator.hasNext()) {
/* 219 */       SisUsuariosDTO reg = (SisUsuariosDTO)iterator.next();
/* 220 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 221 */       op.setValue("" + reg.getCodigoEmpleado());
/* 222 */       op.appendChild(this.pagHTML.createTextNode(reg.getApellidos() + " " + reg.getNombres()));
/* 223 */       combo.appendChild(op);
/*     */     } 
/* 225 */     arr.clear();
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
/*     */   private void caracteristicas(VSolicitudesDTO regSol, HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 241 */     CaracteristicasValorDAO rsTGen = new CaracteristicasValorDAO();
/*     */     
/* 243 */     HTMLTableElement tabla = this.pagHTML.getElementCaracteristicas();
/*     */     
/* 245 */     CaracteristicasDAO rsCaracteristicas = new CaracteristicasDAO();
/* 246 */     Collection<CaracteristicasDTO> arr = rsCaracteristicas.cargarTodosParaServicio(regSol.getCodigoServicio(), "C", "A");
/* 247 */     rsCaracteristicas.close();
/*     */     
/* 249 */     int solPadre = 0;
/*     */     try {
/* 251 */       solPadre = Integer.parseInt(comms.request.getParameter("solPadre"));
/*     */       
/* 253 */       comms.session.getSessionData().set("volverA", "" + solPadre);
/*     */     
/*     */     }
/* 256 */     catch (Exception e) {
/*     */       try {
/* 258 */         if (regSol.getSolicitudPadre() > 0) {
/* 259 */           comms.session.getSessionData().set("volverA", "" + regSol.getSolicitudPadre());
/*     */         }
/*     */       }
/* 262 */       catch (Exception ee) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 268 */     HTMLSelectElement extensiones = this.pagHTML.getElementExtensiones();
/* 269 */     boolean extender = false;
/*     */ 
/*     */     
/* 272 */     boolean fondo = true;
/* 273 */     DetalleSolicitudDAO dsf = new DetalleSolicitudDAO();
/* 274 */     int capturas = 0;
/* 275 */     String cadenaFechas = "";
/*     */     
/* 277 */     Iterator iterator = arr.iterator();
/* 278 */     Collection arrAnidadas = new ArrayList();
/* 279 */     Collection arrDependen = new ArrayList();
/*     */     
/* 281 */     while (iterator.hasNext()) {
/* 282 */       CaracteristicasDTO regCar = (CaracteristicasDTO)iterator.next();
/* 283 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 284 */       HTMLElement tdCaracteristica = (HTMLElement)this.pagHTML.createElement("td");
/* 285 */       HTMLElement tdDescripcion = (HTMLElement)this.pagHTML.createElement("td");
/*     */       
/* 287 */       fondo = !fondo;
/* 288 */       eltr.setAttributeNode(newAttr("class", "car ct" + (fondo ? "1" : "2")));
/* 289 */       eltr.setAttributeNode(newAttr("codigo", "" + regCar.getCodigo()));
/* 290 */       tdCaracteristica.appendChild(this.pagHTML.createTextNode(((regCar.getObliga() == true) ? "* " : "  ") + regCar.getDescripcion()));
/*     */       
/* 292 */       dsf.cargarParaSolicitud(regSol.getNumero(), regCar.getCodigo());
/* 293 */       DetalleSolicitudDTO ds = dsf.next();
/* 294 */       boolean hay = false;
/* 295 */       while (ds != null) {
/*     */         
/* 297 */         if ((regCar.getTipo().equals("2") || regCar.getTipo().equals("8")) && regCar.getCaracteristicaAnida() > 0) {
/* 298 */           TemporalDTO tt = new TemporalDTO();
/* 299 */           tt.setCaracteristica(regCar.getCaracteristicaAnida());
/* 300 */           tt.setValor(ds.getValor());
/* 301 */           arrAnidadas.add(tt);
/*     */         } 
/*     */         
/* 304 */         if (regCar.getTipo().equals("2")) {
/* 305 */           tdDescripcion.appendChild(this.pagHTML.createTextNode("" + leerDescripcion(rsTGen, regCar.getCodigo(), ds.getValor(), ds.getObservacion())));
/*     */           
/* 307 */           if (regCar.getCuantasDependen() > 0) {
/* 308 */             TemporalDTO tt = new TemporalDTO();
/* 309 */             tt.setCaracteristica(regCar.getCodigo());
/* 310 */             tt.setValor(ds.getValor());
/* 311 */             arrDependen.add(tt);
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 316 */           String ampliacion = "";
/* 317 */           if (regCar.getNombreProcedimiento().length() > 0) {
/* 318 */             ampliacion = ejecutarProcedimiento(regSol.getNumero(), regCar.getCodigo(), ds.getObservacion(), regCar.getNombreProcedimiento(), regCar.getCaracteristicaAnida());
/*     */           }
/*     */ 
/*     */           
/* 322 */           tdDescripcion.appendChild(this.pagHTML.createTextNode(ds.getObservacion() + " " + ampliacion + ". "));
/*     */         } 
/* 324 */         ds = dsf.next();
/* 325 */         hay = true;
/*     */       } 
/*     */ 
/*     */       
/* 329 */       if (!hay) {
/* 330 */         Iterator iterDepende; int valorDepende; HTMLSelectElement combo; HTMLInputElement inp; switch (Integer.parseInt(regCar.getTipo())) {
/*     */           case 1:
/*     */           case 3:
/*     */           case 4:
/* 334 */             inp = (HTMLInputElement)this.pagHTML.createElement("input");
/* 335 */             inp.setMaxLength(regCar.getLongitud());
/* 336 */             if (regCar.getLongitud() >= 200) {
/* 337 */               inp.setSize("70");
/*     */             }
/* 339 */             else if (regCar.getLongitud() <= 20) {
/* 340 */               inp.setSize("15");
/*     */             }
/* 342 */             else if (regCar.getLongitud() > 20 && regCar.getLongitud() < 100) {
/* 343 */               inp.setSize("30");
/*     */             } else {
/*     */               
/* 346 */               inp.setSize("40");
/*     */             } 
/*     */ 
/*     */             
/* 350 */             inp.setName("" + regCar.getCodigo());
/* 351 */             inp.setId("" + regCar.getCodigo());
/*     */             
/* 353 */             if (regCar.getNombreProcedimiento().length() > 0) {
/* 354 */               inp.setAttributeNode(newAttr("onblur", "ejecutarProcedimiento(this.name,this.value,'" + regCar.getNombreProcedimiento() + "'," + regCar.getCaracteristicaAnida() + ");"));
/*     */             }
/*     */             
/* 357 */             inp.setAttributeNode(newAttr("obliga", "" + ((regCar.getObliga() == true) ? "S" : "N")));
/* 358 */             inp.setAttributeNode(newAttr("tipo", "" + regCar.getTipo()));
/* 359 */             inp.setAttributeNode(newAttr("onkeyDown", "return f_salto2(event)"));
/* 360 */             inp.setAttributeNode(newAttr("onkeyUp", "return f_salto(event)"));
/* 361 */             inp.setAttributeNode(newAttr("class", "inp"));
/*     */             
/* 363 */             inp.setAttributeNode(newAttr("tipoVal", "" + regCar.getTipoValidacion()));
/* 364 */             inp.setAttributeNode(newAttr("carVal", "" + regCar.getCaracteristicaValida()));
/*     */             
/* 366 */             if (regCar.getTipo().equals("3")) {
/* 367 */               inp.setAttributeNode(newAttr("decimales", "" + regCar.getNumeroDecimales()));
/*     */             }
/*     */             
/* 370 */             switch (Integer.parseInt(regCar.getTipo())) {
/*     */               case 1:
/* 372 */                 inp.setAttributeNode(newAttr("onkeypress", "validarTecla(event, 'AL');"));
/*     */                 break;
/*     */               case 3:
/* 375 */                 inp.setAttributeNode(newAttr("onkeypress", "validarTecla(event, 'DE');"));
/* 376 */                 inp.setAttributeNode(newAttr("onFocus", "this.value=desformatnum(this.value);"));
/* 377 */                 inp.setAttributeNode(newAttr("onBlur", "this.value=formatnum(this.value);"));
/*     */                 break;
/*     */               case 4:
/* 380 */                 inp.setAttributeNode(newAttr("onkeypress", "validarTecla(event, 'FE');"));
/*     */                 break;
/*     */             } 
/*     */             
/* 384 */             tdDescripcion.appendChild(inp);
/*     */             
/* 386 */             if (regCar.getTipo().equals("4")) {
/* 387 */               cadenaFechas = cadenaFechas + agregarCapturaFecha("" + regCar.getCodigo());
/* 388 */               HTMLImageElement inp2 = (HTMLImageElement)this.pagHTML.createElement("IMG");
/* 389 */               inp2.setSrc("media/calendario.jpg");
/* 390 */               inp2.setId("b" + regCar.getCodigo());
/* 391 */               inp2.setAlt("Calendario");
/* 392 */               inp2.setHeight("20");
/* 393 */               inp2.setWidth("20");
/* 394 */               tdDescripcion.appendChild(inp2);
/*     */             } 
/*     */             
/* 397 */             if (regCar.getNombreProcedimiento().length() > 0) {
/* 398 */               HTMLElement inp3 = (HTMLElement)this.pagHTML.createElement("span");
/* 399 */               inp3.setId("msg" + regCar.getCodigo());
/* 400 */               inp3.setAttributeNode(newAttr("class", "error"));
/* 401 */               tdDescripcion.appendChild(inp3);
/*     */             } 
/*     */             break;
/*     */ 
/*     */           
/*     */           case 2:
/*     */           case 5:
/*     */           case 8:
/* 409 */             combo = (HTMLSelectElement)this.pagHTML.createElement("Select");
/* 410 */             combo.setAttributeNode(newAttr("obliga", "" + ((regCar.getObliga() == true) ? "S" : "N")));
/* 411 */             combo.setAttributeNode(newAttr("class", "inp"));
/*     */             
/* 413 */             combo.setName("" + regCar.getCodigo());
/* 414 */             combo.setId("" + regCar.getCodigo());
/*     */             
/* 416 */             valorDepende = 0;
/* 417 */             iterDepende = arrAnidadas.iterator();
/* 418 */             while (iterDepende.hasNext()) {
/* 419 */               TemporalDTO regTemp = (TemporalDTO)iterDepende.next();
/* 420 */               if (regTemp.getCaracteristica() == regCar.getCodigo()) {
/* 421 */                 valorDepende = regTemp.getValor();
/*     */               }
/*     */             } 
/*     */             
/* 425 */             if (regCar.getTipo().equals("2") || regCar.getTipo().equals("8")) {
/* 426 */               llenarCombo(rsTGen, combo, regCar.getTipo(), regCar.getCodigo(), regSol.getAreaProveedor(), valorDepende);
/*     */               
/* 428 */               if (regCar.getCaracteristicaAnida() > 0) {
/* 429 */                 combo.setAttributeNode(newAttr("onchange", "buscarAnidadas(this.name,this.value," + regCar.getCaracteristicaAnida() + ");"));
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/* 434 */               comboFuncionariosArea(combo, regSol.getAreaCliente());
/*     */             } 
/*     */             
/* 437 */             combo.setAttributeNode(newAttr("onkeyDown", "return f_salto2(event)"));
/* 438 */             combo.setAttributeNode(newAttr("onkeyUp", "return f_salto(event)"));
/* 439 */             if (regCar.getCuantasDependen() > 0) {
/* 440 */               combo.setAttributeNode(newAttr("onchange", "mostrarPendientes(this.name,this.value);"));
/*     */             }
/* 442 */             tdDescripcion.appendChild(combo);
/*     */             break;
/*     */         } 
/* 445 */         if (regCar.getObliga()) capturas++;
/*     */       
/* 447 */       } else if (!regCar.getTipo().equals("2") && regCar.getPermiteExtender().equals("S")) {
/* 448 */         HTMLOptionElement extension = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 449 */         extension.setValue("" + regCar.getCodigo());
/* 450 */         extension.appendChild(this.pagHTML.createTextNode(regCar.getDescripcion()));
/* 451 */         extensiones.appendChild(extension);
/* 452 */         extender = true;
/*     */       } 
/*     */ 
/*     */       
/* 456 */       if (regCar.getCaracteristicaDepende() > 0) {
/* 457 */         int valorMostrar = 0;
/* 458 */         Iterator iterDepende = arrDependen.iterator();
/* 459 */         while (iterDepende.hasNext()) {
/* 460 */           TemporalDTO regTemp = (TemporalDTO)iterDepende.next();
/* 461 */           if (regTemp.getCaracteristica() == regCar.getCaracteristicaDepende()) {
/* 462 */             valorMostrar = regTemp.getValor();
/*     */           }
/*     */         } 
/*     */         
/* 466 */         if (valorMostrar != regCar.getValorDepende()) {
/* 467 */           eltr.setAttributeNode(newAttr("style", "display:none"));
/*     */         }
/* 469 */         eltr.setAttributeNode(newAttr("depende", "" + regCar.getCaracteristicaDepende()));
/* 470 */         eltr.setAttributeNode(newAttr("valorDepende", "" + regCar.getValorDepende()));
/*     */       } 
/*     */ 
/*     */       
/* 474 */       eltr.appendChild(tdCaracteristica);
/* 475 */       eltr.appendChild(tdDescripcion);
/* 476 */       tabla.appendChild(eltr);
/*     */     } 
/* 478 */     dsf.close();
/* 479 */     rsTGen.close();
/* 480 */     arr.clear();
/*     */ 
/*     */ 
/*     */     
/* 484 */     if (cadenaFechas.length() > 0) {
/* 485 */       this.pagHTML.setTextJSValfechas(cadenaFechas);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 490 */     if (!extender) {
/* 491 */       Element divextender = this.pagHTML.getElementExtender();
/* 492 */       divextender.getParentNode().removeChild(divextender);
/*     */     } 
/*     */     
/* 495 */     Element menu = this.pagHTML.getElementMiBody();
/* 496 */     if (capturas > 0) {
/*     */ 
/*     */       
/* 499 */       menu.setAttribute("onload", "try {document.miForma.elements[0].focus();} catch(e){}");
/*     */     } else {
/*     */       
/* 502 */       menu.setAttribute("onload", "try {document.myForm.miBoton.focus();} catch(e){}");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void archivosAnexos(int numeroSolicitud, String elUsuario) {
/* 513 */     HTMLTableElement tabla = this.pagHTML.getElementIdArchivos();
/*     */     
/* 515 */     ArchivosSolicitudDAO asf = new ArchivosSolicitudDAO();
/* 516 */     asf.getArchivosSolicitud(numeroSolicitud);
/* 517 */     ArchivosSolicitudDTO asv = asf.next();
/*     */     
/* 519 */     int cuantos = 0;
/* 520 */     boolean hay = false;
/* 521 */     boolean fondo = true;
/* 522 */     while (asv != null) {
/* 523 */       hay = true;
/*     */       
/* 525 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 526 */       fondo = !fondo;
/* 527 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/* 528 */       HTMLElement tdMarca = (HTMLElement)this.pagHTML.createElement("td");
/*     */       
/* 530 */       HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
/* 531 */       checkbox.setAttribute("type", "checkbox");
/* 532 */       checkbox.setName("" + asv.getConsecutivo());
/* 533 */       tdMarca.appendChild(checkbox);
/*     */       
/* 535 */       eltr.appendChild(tdMarca);
/*     */ 
/*     */       
/* 538 */       if (ParametrosDTO.getString("medio_descarga_archivos").equals("ftp")) {
/* 539 */         eltr.appendChild(newtdhref(asv.getArchivo(), ParametrosDTO.getString("ftp") + asv.getArchivo(), true));
/*     */       } else {
/*     */         
/* 542 */         eltr.appendChild(newtdhref(asv.getArchivo(), "VerArchivo.po?archivo=" + asv.getArchivo() + "&fecha=" + Utilidades.darFormatoFecha(asv.getFechaInsercion()), true));
/*     */       } 
/*     */ 
/*     */       
/* 546 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(asv.getFechaInsercion())));
/*     */       
/* 548 */       if (elUsuario.equals(asv.getUsuarioInsercion())) {
/* 549 */         cuantos++;
/*     */       }
/*     */       
/* 552 */       tabla.appendChild(eltr);
/* 553 */       asv = asf.next();
/*     */     } 
/* 555 */     asf.close();
/*     */ 
/*     */     
/* 558 */     if (!hay) {
/* 559 */       Element divArchivos = this.pagHTML.getElementIdmostrararchivos();
/* 560 */       divArchivos.getParentNode().removeChild(divArchivos);
/*     */     } 
/*     */     
/* 563 */     this.pagHTML.getElementAnexosHechos().setValue("" + cuantos);
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
/*     */   private String leerDescripcion(CaracteristicasValorDAO rsTGen, int caract, int dato, String descripcion) {
/* 576 */     CaracteristicasValorDTO RegGeneral = rsTGen.cargarRegistro(caract, dato);
/* 577 */     if (RegGeneral != null) {
/* 578 */       return RegGeneral.getDescripcion();
/*     */     }
/* 580 */     return descripcion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 589 */   private String agregarCapturaFecha(String fecha) { return "Calendar.setup({inputField :  '" + fecha + "'," + "ifFormat   :  '%Y-%m-%d'," + "button     :  'b" + fecha + "'" + "});"; }
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
/*     */   private String ejecutarProcedimiento(int idsol, int caracteristica, String valor, String proceso, int anidada) {
/*     */     try {
/* 611 */       CaracteristicasDAO rs = new CaracteristicasDAO();
/* 612 */       RespuestaBD rta = rs.validarProcedimiento(idsol, caracteristica, valor, proceso, anidada);
/* 613 */       rs.close();
/*     */       
/* 615 */       if (rta != null) {
/* 616 */         return rta.getCausal();
/*     */       }
/*     */     }
/* 619 */     catch (Exception e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 624 */     return "";
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\VerSolicitudNoEnviada.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */