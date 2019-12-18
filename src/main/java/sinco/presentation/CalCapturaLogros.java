/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.CalCiclosDTO;
/*     */ import sinco.business.CalLogrosDTO;
/*     */ import sinco.business.CalMetasDTO;
/*     */ import sinco.business.CalObjetivosDTO;
/*     */ import sinco.business.CalPeriodosDTO;
/*     */ import sinco.business.CalPlanesDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.CalCiclosDAO;
/*     */ import sinco.data.CalLogrosDAO;
/*     */ import sinco.data.CalPeriodosDAO;
/*     */ import sinco.data.CalPlanMetasDAO;
/*     */ import sinco.data.CalPlanObjetivosDAO;
/*     */ import sinco.data.CalPlanesDAO;
/*     */ import sinco.data.SeguridadDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.CalCapturaLogros;
/*     */ import sinco.presentation.CalCapturaLogrosHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalCapturaLogros
/*     */   implements HttpPresentation
/*     */ {
/*     */   private CalCapturaLogrosHTML pagHTML;
/*  54 */   int idNav = 0;
/*  55 */   String elUsuario = "";
/*  56 */   int cuantasMetas = 0;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  59 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  60 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  63 */     this.idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  64 */     this.elUsuario = "" + comms.session.getUser().getName();
/*     */     
/*  66 */     String operacion = comms.request.getParameter("operacion");
/*  67 */     if (operacion == null || operacion.length() == 0) {
/*  68 */       operacion = "FASE1";
/*     */     }
/*     */ 
/*     */     
/*  72 */     if (operacion.equals("FASE1") || operacion.equals("RECARGA")) {
/*  73 */       fase1(comms);
/*     */       
/*     */       return;
/*     */     } 
/*  77 */     if (operacion.equals("VER")) {
/*  78 */       verPlan(comms);
/*     */       
/*     */       return;
/*     */     } 
/*  82 */     if (operacion.equals("SALVAR")) {
/*  83 */       salvar(comms);
/*     */       
/*     */       return;
/*     */     } 
/*  87 */     this.pagHTML = (CalCapturaLogrosHTML)comms.xmlcFactory.create(CalCapturaLogrosHTML.class);
/*  88 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + this.idNav));
/*  89 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private void fase1(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 101 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 103 */     int ciclo = 0;
/*     */     try {
/* 105 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 107 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 110 */     this.pagHTML = (CalCapturaLogrosHTML)comms.xmlcFactory.create(CalCapturaLogrosHTML.class);
/*     */     
/* 112 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 113 */     if (!rsTGen.getEstadoConexion()) {
/* 114 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 117 */     HTMLSelectElement combo = this.pagHTML.getElementIdCiclo();
/* 118 */     llenarCombo(rsTGen, combo, "cal_ciclos", "ciclo", "descripcion", "" + ciclo, "estado='A' order by ciclo desc");
/*     */     
/* 120 */     combo = this.pagHTML.getElementIdPeriodo();
/* 121 */     comboMeses(combo, ciclo, Utilidades.getMesActual());
/*     */ 
/*     */     
/* 124 */     if (ciclo > 0) {
/*     */       
/* 126 */       SisUsuariosDAO pf2 = new SisUsuariosDAO();
/* 127 */       SisUsuariosDTO p = pf2.cargarRegistro(this.idNav);
/*     */ 
/*     */       
/* 130 */       SeguridadDAO rsSeguridad = new SeguridadDAO();
/* 131 */       if (!rsSeguridad.getEstadoConexion()) {
/*     */         return;
/*     */       }
/* 134 */       boolean verTodas = rsSeguridad.tieneLlave(miGrupo, "cal_ver_todas_las_areas");
/* 135 */       boolean oVerArriba = rsSeguridad.tieneLlave(miGrupo, "cal_ver_area_superior");
/* 136 */       rsSeguridad.close();
/*     */       
/* 138 */       combo = this.pagHTML.getElementIdArea();
/* 139 */       comboAreas(combo, ciclo, p.getArea(), verTodas, oVerArriba);
/*     */     } else {
/*     */       
/* 142 */       HTMLElement sel = this.pagHTML.getElementTrArea();
/* 143 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 145 */     rsTGen.close();
/*     */     
/* 147 */     HTMLElement eltr = this.pagHTML.getElementTrPlan();
/* 148 */     eltr.getParentNode().removeChild(eltr);
/*     */     
/* 150 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + this.idNav));
/* 151 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private void verPlan(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 165 */     int ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/* 166 */     int area = Integer.parseInt(comms.request.getParameter("area"));
/* 167 */     int periodo = Integer.parseInt(comms.request.getParameter("periodo"));
/* 168 */     String resultado = comms.request.getParameter("resultado");
/*     */     
/* 170 */     SisUsuariosDAO rsPersona = new SisUsuariosDAO();
/* 171 */     SisUsuariosDTO regPersona = rsPersona.cargarRegistro(this.idNav);
/*     */ 
/*     */     
/* 174 */     AreasDAO af = new AreasDAO();
/* 175 */     AreasDTO regArea = af.cargarRegistro(area);
/* 176 */     af.close();
/*     */ 
/*     */     
/* 179 */     boolean bCapturaLogros = true;
/* 180 */     if (!regArea.isModificaLogros() && regPersona.getArea() != ParametrosDTO.getInt("codigo.gerencia.sinco.servicio")) {
/* 181 */       bCapturaLogros = false;
/*     */     }
/*     */     
/* 184 */     this.pagHTML = (CalCapturaLogrosHTML)comms.xmlcFactory.create(CalCapturaLogrosHTML.class);
/*     */     
/* 186 */     CalPlanesDAO rsPlanes = new CalPlanesDAO();
/* 187 */     if (!rsPlanes.getEstadoConexion()) {
/* 188 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 190 */     CalPlanesDTO regPlan = rsPlanes.cargarPlanArea(ciclo, area);
/* 191 */     rsPlanes.close();
/*     */     
/* 193 */     CalCiclosDAO rsCalCiclos = new CalCiclosDAO();
/* 194 */     if (!rsCalCiclos.getEstadoConexion()) {
/* 195 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 197 */     CalCiclosDTO regCiclo = rsCalCiclos.cargarCalCiclos(ciclo);
/* 198 */     rsCalCiclos.close();
/*     */     
/* 200 */     if (regCiclo != null) {
/* 201 */       this.pagHTML.setTextNombreCicloA(regCiclo.getDescripcion());
/*     */     }
/* 203 */     this.pagHTML.setTextNumeroPlan("" + regPlan.getCodigoPlan());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 208 */     HTMLSelectElement combo = this.pagHTML.getElementPeriodoV();
/* 209 */     comboMeses(combo, ciclo, periodo);
/*     */     
/* 211 */     this.pagHTML.setTextNombreAreaA((regArea != null) ? regArea.getDescripcion() : "");
/*     */     
/* 213 */     this.pagHTML.getElementCicloV().setValue("" + ciclo);
/* 214 */     this.pagHTML.getElementPlanV().setValue("" + regPlan.getCodigoPlan());
/*     */     
/* 216 */     this.pagHTML.getElementAreaV().setValue("" + area);
/*     */     
/* 218 */     if (resultado != null) {
/* 219 */       Varios oVarios = new Varios();
/* 220 */       this.pagHTML.setTextUltimoMensaje(oVarios.formatMensaje(resultado));
/*     */     } 
/*     */     
/* 223 */     CalPlanObjetivosDAO rs = new CalPlanObjetivosDAO();
/* 224 */     if (!rs.getEstadoConexion()) {
/* 225 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 228 */     HTMLTableElement hte = this.pagHTML.getElementPlan();
/* 229 */     boolean fondo = true;
/*     */     
/* 231 */     rs.cargarPlan(ciclo, regPlan.getCodigoPlan(), "A");
/* 232 */     int cuantas = 0;
/*     */     
/* 234 */     CalObjetivosDTO reg = rs.next();
/* 235 */     String proceso = "";
/* 236 */     String subProceso = "";
/* 237 */     while (reg != null) {
/*     */       
/* 239 */       if (cuantas == 0 || !proceso.equals(reg.getProceso()) || !subProceso.equals(reg.getSubProceso())) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 244 */         if (!proceso.equals(reg.getProceso()) || cuantas == 0) {
/* 245 */           HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 246 */           eltr.appendChild(newtd("" + reg.getNombreProceso() + reg.getEstadoProceso(), 3, "CA"));
/* 247 */           hte.appendChild(eltr);
/*     */         } 
/*     */         
/* 250 */         if (cuantas == 0 || !proceso.equals(reg.getProceso()) || !subProceso.equals(reg.getSubProceso())) {
/*     */ 
/*     */ 
/*     */           
/* 254 */           HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 255 */           eltr.appendChild(newtd("" + reg.getNombreSubProceso() + reg.getEstadoSubProceso(), 3, "CA2"));
/* 256 */           hte.appendChild(eltr);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 262 */       HTMLElement tdMetas = (HTMLElement)this.pagHTML.createElement("td");
/* 263 */       tdMetas = lasMetas(ciclo, regPlan.getCodigoPlan(), reg.getCodigoObjetivo(), periodo, reg.getTipoObjetivo(), bCapturaLogros);
/*     */ 
/*     */       
/* 266 */       if (this.cuantasMetas > 0) {
/* 267 */         HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 268 */         fondo = !fondo;
/* 269 */         eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */         
/* 271 */         eltr.appendChild(newtd("" + reg.getDescripcion() + reg.getNombreEstado(), 0, ""));
/* 272 */         eltr.appendChild(newtd(reg.getNombreTipoObjetivo(), 0, ""));
/* 273 */         eltr.appendChild(tdMetas);
/*     */         
/* 275 */         hte.appendChild(eltr);
/*     */       } 
/*     */       
/* 278 */       proceso = reg.getProceso();
/* 279 */       subProceso = reg.getSubProceso();
/* 280 */       reg = rs.next();
/* 281 */       cuantas++;
/*     */     } 
/* 283 */     rs.close();
/*     */     
/* 285 */     HTMLElement eltr = this.pagHTML.getElementTrSeleccionar();
/* 286 */     eltr.getParentNode().removeChild(eltr);
/*     */     
/* 288 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + this.idNav));
/* 289 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private HTMLElement lasMetas(int ciclo, int plan, int objetivo, int periodo, String tipoObjetivo, boolean bCapturaLogros) {
/* 305 */     HTMLElement laTabla = (HTMLElement)this.pagHTML.createElement("table");
/* 306 */     laTabla.setAttributeNode(newAttr("class", "fprofb"));
/* 307 */     laTabla.setAttributeNode(newAttr("align", "left"));
/* 308 */     laTabla.setAttributeNode(newAttr("width", "100%%"));
/*     */ 
/*     */     
/* 311 */     CalPlanMetasDAO rs = new CalPlanMetasDAO();
/* 312 */     Collection arr = rs.cargarDeObjetivo(ciclo, plan, objetivo, periodo, "A");
/* 313 */     rs.close();
/* 314 */     CalLogrosDAO rsLogros = new CalLogrosDAO();
/*     */     
/* 316 */     boolean fondo = true;
/*     */     
/* 318 */     this.cuantasMetas = 0;
/*     */     
/* 320 */     Iterator iterator = arr.iterator();
/* 321 */     while (iterator.hasNext()) {
/* 322 */       CalMetasDTO reg = (CalMetasDTO)iterator.next();
/*     */       
/* 324 */       CalLogrosDTO regLogro = rsLogros.cargarRegistro(ciclo, plan, objetivo, reg.getCodigoMeta(), periodo);
/* 325 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 326 */       fondo = !fondo;
/* 327 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/* 328 */       eltr.appendChild(newtd("" + reg.getDescripcion() + reg.getNombreEstadoVer(), 60));
/* 329 */       eltr.appendChild(newtd("" + reg.getNombreFrecuenciaMedicion(), 10));
/*     */ 
/*     */ 
/*     */       
/* 333 */       HTMLInputElement inp = (HTMLInputElement)this.pagHTML.createElement("input");
/* 334 */       inp.setMaxLength(15);
/* 335 */       inp.setSize("15");
/* 336 */       inp.setClassName("logro");
/* 337 */       inp.setAttributeNode(newAttr("minimo", "" + Utilidades.formatDouble(reg.getValorMinimo())));
/* 338 */       inp.setAttributeNode(newAttr("maximo", "" + Utilidades.formatDouble(reg.getValorMaximo())));
/* 339 */       inp.setName("_" + Utilidades.formato(objetivo, 5) + Utilidades.formato(reg.getCodigoMeta(), 5));
/* 340 */       inp.setAttributeNode(newAttr("onkeyDown", "return f_salto2(event)"));
/* 341 */       inp.setAttributeNode(newAttr("onkeyUp", "return f_salto(event)"));
/* 342 */       inp.setAttributeNode(newAttr("onkeypress", "validarTecla(event, 'DE');"));
/*     */       
/* 344 */       if (regLogro != null && !regLogro.getEstado().equals("T")) {
/* 345 */         inp.setValue("" + regLogro.getValorLogro());
/*     */       }
/*     */ 
/*     */       
/* 349 */       if (!bCapturaLogros && tipoObjetivo.equals("G")) {
/* 350 */         inp.setAttributeNode(newAttr("disabled", "true"));
/*     */       }
/*     */       
/* 353 */       HTMLElement tdValor = (HTMLElement)this.pagHTML.createElement("td");
/* 354 */       tdValor.appendChild(inp);
/* 355 */       eltr.appendChild(tdValor);
/*     */       
/* 357 */       eltr.appendChild(newtd("" + reg.getNombreUnidadMedida(), 10));
/*     */       
/* 359 */       laTabla.appendChild(eltr);
/* 360 */       this.cuantasMetas++;
/*     */     } 
/* 362 */     rsLogros.close();
/* 363 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 364 */     td.appendChild(laTabla);
/* 365 */     return td;
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
/*     */   private void salvar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 378 */     int ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/* 379 */     int periodo = Integer.parseInt(comms.request.getParameter("periodo"));
/* 380 */     int plan = Integer.parseInt(comms.request.getParameter("plan"));
/* 381 */     int area = Integer.parseInt(comms.request.getParameter("area"));
/*     */     
/* 383 */     CalPlanMetasDAO rsMetas = new CalPlanMetasDAO();
/* 384 */     if (!rsMetas.getEstadoConexion()) {
/* 385 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 388 */     CalLogrosDAO rs = new CalLogrosDAO();
/* 389 */     if (!rs.getEstadoConexion()) {
/* 390 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 393 */     Enumeration enumera = comms.request.getParameterNames();
/*     */     
/* 395 */     while (enumera.hasMoreElements()) {
/* 396 */       String param = (String)enumera.nextElement();
/*     */       
/* 398 */       if (param.substring(0, 1).equals("_")) {
/* 399 */         int objetivo = Integer.parseInt(param.substring(1, 6));
/* 400 */         int meta = Integer.parseInt(param.substring(6, param.length()));
/*     */         
/* 402 */         CalMetasDTO regMeta = rsMetas.cargarRegistro(ciclo, plan, meta);
/*     */         
/* 404 */         CalLogrosDTO regLogro = rs.cargarRegistro(ciclo, plan, objetivo, meta, periodo);
/*     */ 
/*     */         
/* 407 */         double valor = -999.0D;
/* 408 */         boolean esValido = false;
/*     */         try {
/* 410 */           valor = Double.parseDouble(comms.request.getParameter(param));
/* 411 */           esValido = true;
/*     */         }
/* 413 */         catch (Exception e) {
/* 414 */           esValido = false;
/*     */         } 
/* 416 */         if (esValido) {
/* 417 */           if (regLogro == null) {
/* 418 */             rs.crearRegistro(ciclo, plan, objetivo, meta, periodo, valor, regMeta.getValorMeta(), "", "A", this.elUsuario);
/*     */             continue;
/*     */           } 
/* 421 */           rs.modificarRegistro(ciclo, plan, objetivo, meta, periodo, valor, (regLogro.getEstado().equals("T") && regLogro.getValorMeta() == 0.0D) ? regMeta.getValorMeta() : regLogro.getValorMeta(), regLogro.getJustificacion(), (regLogro.getEstado().equals("T") && valor == 0.0D) ? "T" : "A", this.elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 434 */         if (regLogro != null) {
/* 435 */           if (regLogro.getEstado().equals("T")) {
/* 436 */             rs.modificarRegistro(ciclo, plan, objetivo, meta, periodo, 0.0D, regMeta.getValorMeta(), regLogro.getJustificacion(), "T", this.elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             continue;
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 449 */           rs.borrarRegistro(ciclo, plan, objetivo, meta, periodo);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 454 */     rs.close();
/* 455 */     rsMetas.close();
/*     */     
/* 457 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("CalCapturaLogros.po?ciclo=" + ciclo + "&area=" + area + "&plan=" + plan + "&periodo=" + periodo + "&resultado=OperacionRealizada&operacion=VER"));
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
/*     */   private Attr newAttr(String tipo, String valor) {
/* 471 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 472 */     atrib.setValue(valor);
/* 473 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido, int colspan, String clase) {
/* 484 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 485 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 486 */     if (colspan > 0) {
/* 487 */       td.setAttributeNode(newAttr("colspan", "" + colspan));
/*     */     }
/* 489 */     if (clase.length() > 0) {
/* 490 */       td.setAttributeNode(newAttr("class", clase));
/*     */     }
/* 492 */     return td;
/*     */   }
/*     */   
/*     */   private HTMLElement newtd(String contenido, int ancho) {
/* 496 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 497 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 498 */     td.setAttributeNode(newAttr("width", "" + ancho + "%%"));
/* 499 */     return td;
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
/*     */   private void llenarCombo(TGeneralDAO rsTGen, HTMLSelectElement combo, String tabla, String codigo, String descripcion, String defecto, String condicion) {
/* 520 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 521 */     op.setValue("0");
/* 522 */     op.appendChild(this.pagHTML.createTextNode(""));
/* 523 */     combo.appendChild(op);
/*     */     
/* 525 */     rsTGen.cargarTodos(tabla, codigo, descripcion, condicion);
/*     */     
/*     */     TGeneralDTO RegGeneral;
/* 528 */     while ((RegGeneral = rsTGen.next()) != null) {
/* 529 */       op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 530 */       op.setValue("" + RegGeneral.getCodigo());
/* 531 */       op.appendChild(this.pagHTML.createTextNode(RegGeneral.getDescripcion()));
/* 532 */       if (defecto != null && defecto.equals(RegGeneral.getCodigo())) {
/* 533 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 534 */         escogida.setValue("on");
/* 535 */         op.setAttributeNode(escogida);
/*     */       } 
/* 537 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean comboMeses(HTMLSelectElement combo, int ciclo, int defecto) {
/* 543 */     int maximo = 12;
/* 544 */     if (ciclo == Utilidades.getAnnoActual()) {
/* 545 */       maximo = Utilidades.getMesActual();
/* 546 */     } else if (ciclo > Utilidades.getAnnoActual()) {
/* 547 */       maximo = 0;
/*     */     } 
/*     */     
/* 550 */     CalPeriodosDAO rsPeriodos = new CalPeriodosDAO();
/* 551 */     if (!rsPeriodos.getEstadoConexion()) {
/* 552 */       return false;
/*     */     }
/* 554 */     rsPeriodos.cargarAbiertos(ciclo, maximo);
/* 555 */     CalPeriodosDTO reg = rsPeriodos.next();
/* 556 */     while (reg != null) {
/*     */       
/* 558 */       HTMLOptionElement op2 = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 559 */       op2.setValue("" + reg.getPeriodo());
/* 560 */       op2.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 561 */       if (reg.getPeriodo() == defecto) {
/* 562 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 563 */         escogida.setValue("on");
/* 564 */         op2.setAttributeNode(escogida);
/*     */       } 
/* 566 */       combo.appendChild(op2);
/* 567 */       reg = rsPeriodos.next();
/*     */     } 
/* 569 */     rsPeriodos.close();
/* 570 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void comboAreas(HTMLSelectElement combo, int ciclo, int area1, boolean verTodas, boolean oVerArriba) {
/* 577 */     Collection arr = new ArrayList();
/* 578 */     AreasDAO af = new AreasDAO();
/* 579 */     if (verTodas) {
/* 580 */       arr = af.cargarAreasCicloCalidad(ciclo);
/*     */     } else {
/*     */       
/* 583 */       String superior = "";
/* 584 */       if (oVerArriba) {
/* 585 */         Varios oVarios = new Varios();
/* 586 */         superior = oVarios.getNivelSuperior(area1, ParametrosDTO.getInt("cal_numero_areas_arriba"));
/*     */       } 
/* 588 */       arr = af.cargarSecuenciaCalidad(ciclo, area1, superior);
/*     */     } 
/* 590 */     af.close();
/*     */     
/* 592 */     Iterator iterator = arr.iterator();
/* 593 */     while (iterator.hasNext()) {
/* 594 */       AreasDTO area = (AreasDTO)iterator.next();
/* 595 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 596 */       op.setValue("" + area.getCodigo());
/* 597 */       op.appendChild(this.pagHTML.createTextNode(area.getDescripcion()));
/*     */       
/* 599 */       if (area1 == area.getCodigo()) {
/* 600 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 601 */         escogida.setValue("on");
/* 602 */         op.setAttributeNode(escogida);
/*     */       } 
/*     */       
/* 605 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalCapturaLogros.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */