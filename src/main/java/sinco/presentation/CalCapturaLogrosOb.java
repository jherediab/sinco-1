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
/*     */ import java.util.regex.Pattern;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import sinco.business.CalLogrosDTO;
/*     */ import sinco.business.CalMetasDTO;
/*     */ import sinco.business.CalPeriodosDTO;
/*     */ import sinco.business.CalProcesosDTO;
/*     */ import sinco.business.CalSubProcesosDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CalLogrosDAO;
/*     */ import sinco.data.CalPeriodosDAO;
/*     */ import sinco.data.CalPlanMetasDAO;
/*     */ import sinco.data.CalProcesosDAO;
/*     */ import sinco.data.CalSubProcesosDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.CalCapturaLogrosOb;
/*     */ import sinco.presentation.CalCapturaLogrosObHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalCapturaLogrosOb
/*     */   implements HttpPresentation
/*     */ {
/*     */   private CalCapturaLogrosObHTML pagHTML;
/*  46 */   int idNav = 0;
/*  47 */   String elUsuario = "";
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  50 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  51 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  54 */     this.idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  55 */     this.elUsuario = "" + comms.session.getUser().getName();
/*     */     
/*  57 */     String operacion = comms.request.getParameter("_operacion");
/*  58 */     if (operacion == null || operacion.length() == 0) {
/*  59 */       operacion = "FASE1";
/*     */     }
/*     */ 
/*     */     
/*  63 */     if (operacion.equals("SALVAR")) {
/*  64 */       salvar(comms);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  69 */     this.pagHTML = (CalCapturaLogrosObHTML)comms.xmlcFactory.create(CalCapturaLogrosObHTML.class);
/*  70 */     fase1(comms);
/*  71 */     verPlan(comms);
/*     */     
/*  73 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + this.idNav));
/*  74 */     comms.response.writeDOM(this.pagHTML);
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
/*  86 */     int ciclo = 0;
/*     */     try {
/*  88 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/*  90 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  93 */     String proceso = comms.request.getParameter("proceso");
/*  94 */     if (proceso == null) {
/*  95 */       proceso = "";
/*     */     }
/*     */     
/*  98 */     String subproceso = comms.request.getParameter("subproceso");
/*  99 */     if (subproceso == null) {
/* 100 */       subproceso = "";
/*     */     }
/*     */     
/* 103 */     int periodo = 0;
/*     */     try {
/* 105 */       periodo = Integer.parseInt(comms.request.getParameter("periodo"));
/*     */     }
/* 107 */     catch (Exception e) {
/* 108 */       periodo = Utilidades.getMesActual();
/*     */     } 
/*     */     
/* 111 */     HTMLSelectElement combo = this.pagHTML.getElementProceso();
/* 112 */     comboProcesos(comms, combo, proceso);
/*     */     
/* 114 */     if (proceso.length() > 0) {
/* 115 */       combo = this.pagHTML.getElementSubproceso();
/* 116 */       comboSubProcesos(comms, combo, proceso, subproceso);
/*     */     } 
/*     */     
/* 119 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 120 */     if (!rsTGen.getEstadoConexion()) {
/* 121 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 124 */     combo = this.pagHTML.getElementCiclo();
/* 125 */     llenarCombo(rsTGen, combo, "cal_ciclos", "ciclo", "descripcion", "" + ciclo, "estado='A' order by ciclo desc");
/* 126 */     rsTGen.close();
/*     */     
/* 128 */     combo = this.pagHTML.getElementPeriodo();
/* 129 */     comboMeses(combo, ciclo, periodo);
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
/*     */   private void verPlan(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 165 */     int ciclo = 0;
/*     */     try {
/* 167 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 169 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 172 */     String proceso = comms.request.getParameter("proceso");
/* 173 */     if (proceso == null) {
/* 174 */       proceso = "";
/*     */     }
/*     */     
/* 177 */     String subproceso = comms.request.getParameter("subproceso");
/* 178 */     if (subproceso == null) {
/* 179 */       subproceso = "";
/*     */     }
/*     */     
/* 182 */     int periodo = 0;
/*     */     try {
/* 184 */       periodo = Integer.parseInt(comms.request.getParameter("periodo"));
/*     */     }
/* 186 */     catch (Exception e) {
/* 187 */       periodo = Utilidades.getMesActual();
/*     */     } 
/*     */     
/* 190 */     String resultado = comms.request.getParameter("resultado");
/*     */     
/* 192 */     if (ciclo == 0 || proceso.length() == 0 || subproceso.length() == 0 || periodo == 0) {
/*     */       return;
/*     */     }
/*     */     
/* 196 */     if (resultado != null) {
/* 197 */       Varios oVarios = new Varios();
/* 198 */       this.pagHTML.setTextUltimoMensaje(oVarios.formatMensaje(resultado));
/*     */     } 
/*     */     
/* 201 */     CalPlanMetasDAO rs = new CalPlanMetasDAO();
/* 202 */     Collection arr = rs.cargarMasivo(ciclo, proceso, subproceso, periodo);
/* 203 */     rs.close();
/*     */     
/* 205 */     int objetivoOld = 0;
/* 206 */     int cuantas = 0;
/*     */ 
/*     */     
/* 209 */     HTMLElement div = this.pagHTML.getElementDetalle();
/*     */     
/* 211 */     Collection bloque = new ArrayList();
/*     */     
/* 213 */     Iterator iterator = arr.iterator();
/* 214 */     while (iterator.hasNext()) {
/* 215 */       HTMLElement objet; CalMetasDTO reg = (CalMetasDTO)iterator.next();
/*     */       
/* 217 */       int rta = evaluarCorteO(cuantas, objetivoOld, reg.getCodigoObjetivo());
/*     */ 
/*     */ 
/*     */       
/* 221 */       switch (rta) {
/*     */         
/*     */         case -1:
/* 224 */           bloque = new ArrayList();
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 1:
/* 230 */           objet = escribirObjetivo(comms, objetivoOld, bloque);
/* 231 */           div.appendChild(objet);
/* 232 */           div.appendChild(this.pagHTML.createElement("p"));
/*     */           
/* 234 */           bloque = new ArrayList();
/*     */           break;
/*     */       } 
/*     */       
/* 238 */       bloque.add(reg);
/*     */ 
/*     */       
/* 241 */       objetivoOld = reg.getCodigoObjetivo();
/* 242 */       cuantas++;
/*     */     } 
/*     */     
/* 245 */     if (bloque.size() > 0) {
/* 246 */       HTMLElement objet = escribirObjetivo(comms, objetivoOld, bloque);
/* 247 */       div.appendChild(objet);
/* 248 */       div.appendChild(this.pagHTML.createElement("p"));
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
/*     */   private HTMLElement escribirObjetivo(HttpPresentationComms comms, int objetivoOld, Collection objetivo) throws HttpPresentationException, KeywordValueException {
/* 264 */     HTMLElement div = (HTMLElement)this.pagHTML.createElement("div");
/*     */     
/* 266 */     HTMLElement divmetas = (HTMLElement)this.pagHTML.createElement("div");
/* 267 */     divmetas.setAttribute("style", "display:none");
/*     */     
/* 269 */     HTMLElement titulos = (HTMLElement)this.pagHTML.createElement("table");
/* 270 */     titulos.setAttributeNode(newAttr("class", "tabf"));
/* 271 */     titulos.setAttributeNode(newAttr("align", "center"));
/* 272 */     titulos.setAttributeNode(newAttr("width", "100%%"));
/*     */     
/* 274 */     int metaOld = 0;
/* 275 */     int cuantas = 0;
/*     */     
/* 277 */     Collection metas = new ArrayList();
/*     */     
/* 279 */     Iterator iterator = objetivo.iterator();
/* 280 */     while (iterator.hasNext()) {
/*     */       HTMLElement mm;
/* 282 */       CalMetasDTO reg = (CalMetasDTO)iterator.next();
/*     */       
/* 284 */       if (cuantas == 0) {
/* 285 */         HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */         
/* 287 */         HTMLElement tdMarca = (HTMLElement)this.pagHTML.createElement("td");
/* 288 */         tdMarca.setAttribute("width", "3%");
/* 289 */         HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
/* 290 */         checkbox.setAttribute("type", "checkbox");
/* 291 */         checkbox.setAttribute("onclick", "Procesar(this,'obj_" + reg.getCodigoObjetivo() + "')");
/* 292 */         checkbox.setName("");
/* 293 */         tdMarca.appendChild(checkbox);
/* 294 */         tdMarca.setAttributeNode(newAttr("class", "ca"));
/* 295 */         eltr.appendChild(tdMarca);
/*     */         
/* 297 */         eltr.appendChild(newtd("Objetivo: " + reg.getNombreObjetivo(), "ca"));
/*     */ 
/*     */         
/* 300 */         titulos.appendChild(eltr);
/*     */         
/* 302 */         div.appendChild(titulos);
/*     */         
/* 304 */         divmetas.setId("obj_" + reg.getCodigoObjetivo());
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 309 */       int rta = evaluarCorteM(cuantas, metaOld, reg.getCodigoMeta());
/*     */ 
/*     */ 
/*     */       
/* 313 */       switch (rta) {
/*     */         
/*     */         case -1:
/* 316 */           metas = new ArrayList();
/*     */           break;
/*     */         
/*     */         case 1:
/* 320 */           mm = escribirMetas(comms, objetivoOld, metaOld, metas);
/* 321 */           divmetas.appendChild(mm);
/*     */           
/* 323 */           metas = new ArrayList();
/*     */           break;
/*     */       } 
/* 326 */       metas.add(reg);
/*     */       
/* 328 */       metaOld = reg.getCodigoMeta();
/* 329 */       cuantas++;
/*     */     } 
/*     */     
/* 332 */     if (metas.size() > 0) {
/* 333 */       HTMLElement mm = escribirMetas(comms, objetivoOld, metaOld, metas);
/* 334 */       divmetas.appendChild(mm);
/*     */     } 
/* 336 */     div.appendChild(divmetas);
/* 337 */     return div;
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
/*     */   private HTMLElement escribirMetas(HttpPresentationComms comms, int codigoObjetivo, int codigoMeta, Collection metas) throws HttpPresentationException, KeywordValueException {
/* 351 */     HTMLElement div = (HTMLElement)this.pagHTML.createElement("div");
/*     */     
/* 353 */     HTMLElement titulos = (HTMLElement)this.pagHTML.createElement("table");
/*     */     
/* 355 */     titulos.setAttributeNode(newAttr("align", "center"));
/* 356 */     titulos.setAttributeNode(newAttr("width", "100%%"));
/*     */     
/* 358 */     HTMLElement laTabla = (HTMLElement)this.pagHTML.createElement("table");
/* 359 */     laTabla.setAttributeNode(newAttr("class", "tabf"));
/* 360 */     laTabla.setAttributeNode(newAttr("align", "center"));
/* 361 */     laTabla.setAttributeNode(newAttr("width", "100%%"));
/*     */ 
/*     */     
/* 364 */     boolean fondo = true;
/*     */     
/* 366 */     Iterator iterator = metas.iterator();
/* 367 */     int cuantas = 0;
/* 368 */     while (iterator.hasNext()) {
/* 369 */       CalMetasDTO reg = (CalMetasDTO)iterator.next();
/*     */       
/* 371 */       if (cuantas == 0) {
/* 372 */         HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 373 */         HTMLElement tdMarca = (HTMLElement)this.pagHTML.createElement("td");
/* 374 */         tdMarca.setAttribute("width", "3%");
/* 375 */         HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
/* 376 */         checkbox.setAttribute("type", "checkbox");
/* 377 */         checkbox.setChecked(true);
/* 378 */         checkbox.setAttribute("onclick", "Procesar(this,'log_" + reg.getCodigoObjetivo() + "_" + reg.getCodigoMeta() + "')");
/* 379 */         checkbox.setName("");
/* 380 */         tdMarca.appendChild(checkbox);
/* 381 */         tdMarca.setAttributeNode(newAttr("class", "ca2"));
/* 382 */         eltr.appendChild(tdMarca);
/*     */         
/* 384 */         eltr.appendChild(newtd("Meta: " + reg.getNombreMeta(), "ca2"));
/* 385 */         titulos.appendChild(eltr);
/*     */         
/* 387 */         div.appendChild(titulos);
/*     */         
/* 389 */         laTabla.setId("log_" + reg.getCodigoObjetivo() + "_" + reg.getCodigoMeta());
/*     */       } 
/*     */       
/* 392 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 393 */       fondo = !fondo;
/* 394 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 396 */       eltr.appendChild(newtd("" + reg.getNombreArea()));
/*     */ 
/*     */       
/* 399 */       HTMLInputElement inp = (HTMLInputElement)this.pagHTML.createElement("input");
/* 400 */       inp.setMaxLength(15);
/* 401 */       inp.setSize("15");
/* 402 */       inp.setClassName("logro");
/* 403 */       inp.setAttributeNode(newAttr("minimo", "" + Utilidades.formatDouble(reg.getValorMinimo())));
/* 404 */       inp.setAttributeNode(newAttr("maximo", "" + Utilidades.formatDouble(reg.getValorMaximo())));
/* 405 */       inp.setName("plan_" + reg.getCodigoPlan() + "_" + reg.getCodigoObjetivo() + "_" + reg.getCodigoMeta());
/* 406 */       inp.setAttributeNode(newAttr("onkeyDown", "return f_salto2(event)"));
/* 407 */       inp.setAttributeNode(newAttr("onkeyUp", "return f_salto(event)"));
/* 408 */       inp.setAttributeNode(newAttr("onkeypress", "validarTecla(event, 'DE');"));
/*     */       
/* 410 */       if (reg.getEstado().equals("A")) {
/* 411 */         inp.setValue("" + reg.getValorLogro());
/*     */       }
/*     */       
/* 414 */       HTMLElement tdValor = (HTMLElement)this.pagHTML.createElement("td");
/* 415 */       tdValor.appendChild(inp);
/* 416 */       eltr.appendChild(tdValor);
/*     */       
/* 418 */       eltr.appendChild(newtd("" + reg.getNombreUnidadMedida()));
/*     */       
/* 420 */       laTabla.appendChild(eltr);
/* 421 */       cuantas++;
/*     */     } 
/*     */     
/* 424 */     div.appendChild(laTabla);
/* 425 */     div.appendChild(this.pagHTML.createElement("p"));
/* 426 */     return div;
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
/*     */   private void salvar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 440 */     int ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/* 441 */     int periodo = Integer.parseInt(comms.request.getParameter("periodo"));
/* 442 */     String proceso = comms.request.getParameter("proceso");
/* 443 */     if (proceso == null) {
/* 444 */       proceso = "";
/*     */     }
/* 446 */     String subproceso = comms.request.getParameter("subproceso");
/* 447 */     if (subproceso == null) {
/* 448 */       subproceso = "";
/*     */     }
/*     */     
/* 451 */     CalPlanMetasDAO rsMetas = new CalPlanMetasDAO();
/* 452 */     if (!rsMetas.getEstadoConexion()) {
/* 453 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 456 */     CalLogrosDAO rs = new CalLogrosDAO();
/* 457 */     if (!rs.getEstadoConexion()) {
/* 458 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 461 */     Enumeration enumera = comms.request.getParameterNames();
/*     */     
/* 463 */     while (enumera.hasMoreElements()) {
/* 464 */       String param = (String)enumera.nextElement();
/*     */       
/* 466 */       if (param.startsWith("plan_")) {
/*     */         
/* 468 */         Pattern p = Pattern.compile("_");
/* 469 */         String[] items = p.split(param);
/* 470 */         int plan = Integer.parseInt(items[1]);
/* 471 */         int objetivo = Integer.parseInt(items[2]);
/* 472 */         int meta = Integer.parseInt(items[3]);
/*     */         
/* 474 */         CalMetasDTO regMeta = rsMetas.cargarRegistro(ciclo, plan, meta);
/* 475 */         CalLogrosDTO regLogro = rs.cargarRegistro(ciclo, plan, objetivo, meta, periodo);
/*     */         
/* 477 */         double valor = -999.0D;
/* 478 */         boolean esValido = false;
/*     */         try {
/* 480 */           valor = Double.parseDouble(comms.request.getParameter(param));
/* 481 */           esValido = true;
/*     */         }
/* 483 */         catch (Exception e) {
/* 484 */           esValido = false;
/*     */         } 
/* 486 */         if (esValido) {
/* 487 */           if (regLogro == null) {
/* 488 */             rs.crearRegistro(ciclo, plan, objetivo, meta, periodo, valor, regMeta.getValorMeta(), "", "A", this.elUsuario);
/*     */             continue;
/*     */           } 
/* 491 */           rs.modificarRegistro(ciclo, plan, objetivo, meta, periodo, valor, (regLogro.getEstado().equals("T") && regLogro.getValorMeta() == 0.0D) ? regMeta.getValorMeta() : regLogro.getValorMeta(), regLogro.getJustificacion(), (regLogro.getEstado().equals("T") && valor == 0.0D) ? "T" : "A", this.elUsuario);
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
/* 504 */         if (regLogro != null) {
/* 505 */           if (regLogro.getEstado().equals("T")) {
/* 506 */             rs.modificarRegistro(ciclo, plan, objetivo, meta, periodo, 0.0D, regMeta.getValorMeta(), regLogro.getJustificacion(), "T", this.elUsuario);
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
/* 519 */           rs.borrarRegistro(ciclo, plan, objetivo, meta, periodo);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 524 */     rs.close();
/* 525 */     rsMetas.close();
/*     */     
/* 527 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath("CalCapturaLogrosOb.po?ciclo=" + ciclo + "&periodo=" + periodo + "&resultado=OperacionRealizada&operacion=RECARGA&proceso=" + proceso + "&subproceso=" + subproceso));
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
/* 541 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 542 */     atrib.setValue(valor);
/* 543 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 553 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 554 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 555 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido, String clase) {
/* 565 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 566 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 567 */     td.setAttributeNode(newAttr("class", clase));
/* 568 */     return td;
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
/* 589 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 590 */     op.setValue("0");
/* 591 */     op.appendChild(this.pagHTML.createTextNode(""));
/* 592 */     combo.appendChild(op);
/*     */     
/* 594 */     rsTGen.cargarTodos(tabla, codigo, descripcion, condicion);
/*     */     
/*     */     TGeneralDTO RegGeneral;
/* 597 */     while ((RegGeneral = rsTGen.next()) != null) {
/* 598 */       op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 599 */       op.setValue("" + RegGeneral.getCodigo());
/* 600 */       op.appendChild(this.pagHTML.createTextNode(RegGeneral.getDescripcion()));
/* 601 */       if (defecto != null && defecto.equals(RegGeneral.getCodigo())) {
/* 602 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 603 */         escogida.setValue("on");
/* 604 */         op.setAttributeNode(escogida);
/*     */       } 
/* 606 */       combo.appendChild(op);
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
/*     */   private boolean comboMeses(HTMLSelectElement combo, int ciclo, int defecto) {
/* 620 */     int maximo = 12;
/* 621 */     if (ciclo == Utilidades.getAnnoActual()) {
/* 622 */       maximo = Utilidades.getMesActual();
/* 623 */     } else if (ciclo > Utilidades.getAnnoActual()) {
/* 624 */       maximo = 0;
/*     */     } 
/*     */     
/* 627 */     CalPeriodosDAO rsPeriodos = new CalPeriodosDAO();
/* 628 */     if (!rsPeriodos.getEstadoConexion()) {
/* 629 */       return false;
/*     */     }
/* 631 */     rsPeriodos.cargarAbiertos(ciclo, maximo);
/* 632 */     CalPeriodosDTO reg = rsPeriodos.next();
/* 633 */     while (reg != null) {
/*     */       
/* 635 */       HTMLOptionElement op2 = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 636 */       op2.setValue("" + reg.getPeriodo());
/* 637 */       op2.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 638 */       if (reg.getPeriodo() == defecto) {
/* 639 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 640 */         escogida.setValue("on");
/* 641 */         op2.setAttributeNode(escogida);
/*     */       } 
/* 643 */       combo.appendChild(op2);
/* 644 */       reg = rsPeriodos.next();
/*     */     } 
/* 646 */     rsPeriodos.close();
/* 647 */     return true;
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
/*     */   private void comboProcesos(HttpPresentationComms comms, HTMLSelectElement combo, String defecto) throws HttpPresentationException, KeywordValueException {
/* 660 */     CalProcesosDAO rs = new CalProcesosDAO();
/*     */     
/* 662 */     if (!rs.getEstadoConexion()) {
/* 663 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 665 */     rs.cargarTodos("A");
/*     */     CalProcesosDTO reg;
/* 667 */     while ((reg = rs.next()) != null) {
/* 668 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 669 */       op.setValue("" + reg.getProceso());
/* 670 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 671 */       if (defecto.equals(reg.getProceso())) {
/* 672 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 673 */         escogida.setValue("on");
/* 674 */         op.setAttributeNode(escogida);
/*     */       } 
/* 676 */       combo.appendChild(op);
/*     */     } 
/* 678 */     rs.close();
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
/*     */   private void comboSubProcesos(HttpPresentationComms comms, HTMLSelectElement combo, String proceso, String defecto) throws HttpPresentationException, KeywordValueException {
/* 692 */     CalSubProcesosDAO rs = new CalSubProcesosDAO();
/*     */     
/* 694 */     if (!rs.getEstadoConexion()) {
/* 695 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 698 */     Collection arr = rs.cargarDeProceso(proceso);
/* 699 */     rs.close();
/*     */     
/* 701 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 702 */     op.setValue("");
/* 703 */     op.appendChild(this.pagHTML.createTextNode(""));
/* 704 */     combo.appendChild(op);
/*     */     
/* 706 */     Iterator iterator = arr.iterator();
/* 707 */     while (iterator.hasNext()) {
/* 708 */       CalSubProcesosDTO reg = (CalSubProcesosDTO)iterator.next();
/*     */       
/* 710 */       op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 711 */       op.setValue("" + reg.getSubproceso());
/* 712 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 713 */       if (defecto.equals(reg.getSubproceso())) {
/* 714 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 715 */         escogida.setValue("on");
/* 716 */         op.setAttributeNode(escogida);
/*     */       } 
/* 718 */       combo.appendChild(op);
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
/*     */   private int evaluarCorteO(int cuantas, int objetivoOld, int objetivo) {
/* 736 */     if (cuantas == 0) {
/* 737 */       return -1;
/*     */     }
/* 739 */     if (objetivoOld != objetivo) {
/* 740 */       return 1;
/*     */     }
/* 742 */     return 0;
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
/*     */   private int evaluarCorteM(int cuantas, int metaOld, int meta) {
/* 754 */     if (cuantas == 0) {
/* 755 */       return -1;
/*     */     }
/* 757 */     if (metaOld != meta) {
/* 758 */       return 1;
/*     */     }
/* 760 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalCapturaLogrosOb.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */