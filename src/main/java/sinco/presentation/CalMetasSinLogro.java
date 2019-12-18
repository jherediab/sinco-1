/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationOutputStream;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.CalEstadisticasDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.CalEstadisticasDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.CalMetasSinLogro;
/*     */ import sinco.presentation.CalMetasSinLogroHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class CalMetasSinLogro
/*     */   implements HttpPresentation
/*     */ {
/*     */   private CalMetasSinLogroHTML pagHTML;
/*  33 */   String idNav = "";
/*  34 */   String elUsuario = "";
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  37 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  38 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  41 */     this.idNav = (String)comms.session.getSessionData().get("miId");
/*  42 */     this.elUsuario = "" + comms.session.getUser().getName();
/*     */     
/*  44 */     String _operacion = comms.request.getParameter("_operacion");
/*  45 */     if (_operacion == null || _operacion.length() == 0) {
/*  46 */       _operacion = "FASE1";
/*     */     }
/*     */ 
/*     */     
/*  50 */     this.pagHTML = (CalMetasSinLogroHTML)comms.xmlcFactory.create(CalMetasSinLogroHTML.class);
/*  51 */     fase1(comms);
/*     */     
/*  53 */     String exportar = comms.request.getParameter("exportar");
/*  54 */     if (exportar == null) {
/*  55 */       exportar = "N";
/*     */     }
/*     */ 
/*     */     
/*  59 */     if (_operacion.equals("Listar")) {
/*  60 */       if (exportar.equals("S")) {
/*  61 */         String respuesta = exportar(comms);
/*  62 */         if (respuesta != null) {
/*  63 */           comms.response.setContentType("application/xls");
/*  64 */           comms.response.setHeader("Content-Disposition", "inline;filename=LogrosPlan.xls");
/*  65 */           comms.response.setStatus(200, "Good job");
/*     */           
/*  67 */           HttpPresentationOutputStream out = comms.response.getOutputStream();
/*     */ 
/*     */           
/*     */           try {
/*  71 */             out.write(respuesta.getBytes());
/*  72 */             out.flush();
/*     */             
/*     */             return;
/*  75 */           } catch (Exception e) {
/*  76 */             throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorGenerandoReporte"));
/*     */           } 
/*     */         } 
/*     */       } else {
/*     */         
/*  81 */         verPlan(comms);
/*     */       } 
/*     */     }
/*     */     
/*  85 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(this.idNav));
/*  86 */     comms.response.writeDOM(this.pagHTML);
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
/*  98 */     int ciclo = 0;
/*     */     try {
/* 100 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 102 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 105 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 106 */     if (!rsTGen.getEstadoConexion()) {
/* 107 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 110 */     HTMLSelectElement combo = this.pagHTML.getElementIdCiclo();
/* 111 */     llenarCombo(rsTGen, combo, "cal_ciclos", "ciclo", "descripcion", "" + ciclo, "estado<>'D' order by ciclo desc");
/* 112 */     rsTGen.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String exportar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 122 */     int ciclo = 0;
/*     */     try {
/* 124 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 126 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 129 */     if (ciclo == 0) {
/* 130 */       return null;
/*     */     }
/*     */     
/* 133 */     CalEstadisticasDAO rs = new CalEstadisticasDAO();
/* 134 */     if (!rs.getEstadoConexion()) {
/* 135 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 138 */     Collection arr = rs.cargarNoDiligenciadas(ciclo);
/* 139 */     rs.close();
/*     */     
/* 141 */     String ret = "Area\tObjetivo\tMeta\tP01\tP02\tP03\tP04\tP05\tP06\tP07\tP08\tP09\tP10\tP11\tP12\tLogro Ene\tLogro Feb\tLogro Mar\tLogro Abr\tLogro May\tLogro Jun\tLogro Jul\tLogro Ago\tLogro Sep\tLogro Oct\tLogro Nov\tLogro Dic\tMeta Ene\tMeta Feb\tMeta Mar\tMeta Abr\tMeta May\tMeta Jun\tMeta Jul\tMeta Ago\tMeta Sep\tMeta Oct\tMeta Nov\tMeta Dic\t";
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 181 */     Iterator iterator = arr.iterator();
/* 182 */     while (iterator.hasNext()) {
/* 183 */       CalEstadisticasDTO reg = (CalEstadisticasDTO)iterator.next();
/* 184 */       ret = ret + "\n" + reg.getNombreArea() + "\t" + reg.getNombreObjetivo() + "\t" + reg.getNombreMeta() + "\t" + reg.getP01() + "\t" + reg.getP02() + "\t" + reg.getP03() + "\t" + reg.getP04() + "\t" + reg.getP05() + "\t" + reg.getP06() + "\t" + reg.getP07() + "\t" + reg.getP08() + "\t" + reg.getP09() + "\t" + reg.getP10() + "\t" + reg.getP11() + "\t" + reg.getP12() + "\t" + reg.getLogroEne() + "\t" + reg.getLogroFeb() + "\t" + reg.getLogroMar() + "\t" + reg.getLogroAbr() + "\t" + reg.getLogroMay() + "\t" + reg.getLogroJun() + "\t" + reg.getLogroJul() + "\t" + reg.getLogroAgo() + "\t" + reg.getLogroSep() + "\t" + reg.getLogroOct() + "\t" + reg.getLogroNov() + "\t" + reg.getLogroDic() + "\t" + reg.getMetaEne() + "\t" + reg.getMetaFeb() + "\t" + reg.getMetaMar() + "\t" + reg.getMetaAbr() + "\t" + reg.getMetaMay() + "\t" + reg.getMetaJun() + "\t" + reg.getMetaJul() + "\t" + reg.getMetaAgo() + "\t" + reg.getMetaSep() + "\t" + reg.getMetaOct() + "\t" + reg.getMetaNov() + "\t" + reg.getMetaDic() + "\t";
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
/* 225 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void verPlan(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 235 */     int ciclo = 0;
/*     */     try {
/* 237 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 239 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 242 */     if (ciclo == 0) {
/*     */       return;
/*     */     }
/*     */     
/* 246 */     CalEstadisticasDAO rs = new CalEstadisticasDAO();
/* 247 */     if (!rs.getEstadoConexion()) {
/* 248 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 251 */     HTMLTableElement hte = this.pagHTML.getElementPlan();
/*     */     
/* 253 */     Collection arr = rs.cargarNoDiligenciadas(ciclo);
/* 254 */     rs.close();
/*     */     
/* 256 */     String nombreArea = "";
/* 257 */     String objetivo = "";
/*     */     
/* 259 */     int hoy = Utilidades.getMesActual();
/*     */     
/* 261 */     Collection bloque = new ArrayList();
/* 262 */     int cuantas = 0;
/* 263 */     boolean fondo = false;
/*     */     
/* 265 */     Iterator iterator = arr.iterator();
/* 266 */     while (iterator.hasNext()) {
/* 267 */       CalEstadisticasDTO reg = (CalEstadisticasDTO)iterator.next();
/*     */       
/* 269 */       boolean continuar = false;
/*     */       
/* 271 */       if ((reg.getP01().equals("N") || reg.getP02().equals("N") || reg.getP03().equals("N") || reg.getP04().equals("N") || reg.getP05().equals("N") || reg.getP06().equals("N") || reg.getP07().equals("N") || reg.getP08().equals("N") || reg.getP09().equals("N") || reg.getP10().equals("N") || reg.getP11().equals("N") || reg.getP12().equals("N")) && ciclo < Utilidades.getAnnoActual()) {
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
/* 283 */         continuar = true;
/*     */       } else {
/*     */         
/* 286 */         if (reg.getP01().equals("N") && hoy >= 1) {
/* 287 */           continuar = true;
/*     */         }
/* 289 */         if (reg.getP02().equals("N") && hoy >= 2) {
/* 290 */           continuar = true;
/*     */         }
/*     */         
/* 293 */         if (reg.getP03().equals("N") && hoy >= 3) {
/* 294 */           continuar = true;
/*     */         }
/* 296 */         if (reg.getP04().equals("N") && hoy >= 4) {
/* 297 */           continuar = true;
/*     */         }
/* 299 */         if (reg.getP05().equals("N") && hoy >= 5) {
/* 300 */           continuar = true;
/*     */         }
/* 302 */         if (reg.getP06().equals("N") && hoy >= 6) {
/* 303 */           continuar = true;
/*     */         }
/*     */         
/* 306 */         if (reg.getP07().equals("N") && hoy >= 7) {
/* 307 */           continuar = true;
/*     */         }
/* 309 */         if (reg.getP08().equals("N") && hoy >= 8) {
/* 310 */           continuar = true;
/*     */         }
/* 312 */         if (reg.getP09().equals("N") && hoy >= 9) {
/* 313 */           continuar = true;
/*     */         }
/* 315 */         if (reg.getP10().equals("N") && hoy >= 10) {
/* 316 */           continuar = true;
/*     */         }
/* 318 */         if (reg.getP11().equals("N") && hoy >= 11) {
/* 319 */           continuar = true;
/*     */         }
/* 321 */         if (reg.getP12().equals("N") && hoy >= 12) {
/* 322 */           continuar = true;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 327 */       if (!continuar) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/* 332 */       if (!nombreArea.equals(reg.getNombreArea()) || cuantas == 0 || objetivo.equals(reg.getNombreObjetivo())) {
/*     */ 
/*     */ 
/*     */         
/* 336 */         if (cuantas > 0) {
/* 337 */           HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 338 */           eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/* 339 */           eltr.appendChild(newtd("" + objetivo, "", 0));
/* 340 */           eltr.appendChild(lasMetas(bloque));
/* 341 */           hte.appendChild(eltr);
/* 342 */           bloque.clear();
/*     */         } 
/*     */         
/* 345 */         if (!nombreArea.equals(reg.getNombreArea()) || cuantas == 0) {
/*     */           
/* 347 */           HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 348 */           eltr.appendChild(newtd("" + reg.getNombreArea(), "CA", 0));
/* 349 */           eltr.appendChild(losTitulos());
/* 350 */           hte.appendChild(eltr);
/*     */         } 
/*     */       } 
/*     */       
/* 354 */       nombreArea = reg.getNombreArea();
/* 355 */       objetivo = reg.getNombreObjetivo();
/*     */       
/* 357 */       bloque.add(reg);
/* 358 */       cuantas++;
/*     */     } 
/* 360 */     if (cuantas > 0) {
/* 361 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 362 */       eltr.appendChild(newtd("" + objetivo, "", 0));
/* 363 */       eltr.appendChild(lasMetas(bloque));
/* 364 */       hte.appendChild(eltr);
/* 365 */       bloque.clear();
/*     */     } 
/*     */     
/* 368 */     arr.clear();
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
/*     */   private HTMLElement lasMetas(Collection arr) {
/* 382 */     HTMLElement laTabla = (HTMLElement)this.pagHTML.createElement("table");
/* 383 */     laTabla.setAttributeNode(newAttr("class", "fprofb"));
/* 384 */     laTabla.setAttributeNode(newAttr("align", "left"));
/* 385 */     laTabla.setAttributeNode(newAttr("width", "100%%"));
/*     */     
/* 387 */     int annoActual = Utilidades.getAnnoActual();
/* 388 */     int mesActual = Utilidades.getMesActual();
/*     */     
/* 390 */     boolean fondo = true;
/* 391 */     Iterator iterator = arr.iterator();
/* 392 */     while (iterator.hasNext()) {
/* 393 */       CalEstadisticasDTO reg = (CalEstadisticasDTO)iterator.next();
/*     */       
/* 395 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 396 */       fondo = !fondo;
/* 397 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 399 */       eltr.appendChild(newtd("" + reg.getNombreMeta(), 22, ""));
/* 400 */       String sPagina = "CalCapturaLogros.po?operacion=VER&ciclo=" + reg.getCodigoCiclo() + "&area=" + reg.getCodigoArea() + "&periodo=";
/*     */       
/* 402 */       if (reg.getP01().equals("N") && ((annoActual == reg.getCodigoCiclo() && 1 < mesActual) || reg.getCodigoCiclo() < annoActual)) {
/* 403 */         eltr.appendChild(newtdhref(ParametrosDTO.getString("cal_mensaje_no_dato_logro"), sPagina + "1", 6, "verde", "center", ""));
/*     */       } else {
/*     */         
/* 406 */         eltr.appendChild(newtd("" + reg.getP01(), 6, ""));
/*     */       } 
/*     */       
/* 409 */       if (reg.getP02().equals("N") && ((annoActual == reg.getCodigoCiclo() && 2 < mesActual) || reg.getCodigoCiclo() < annoActual)) {
/* 410 */         eltr.appendChild(newtdhref(ParametrosDTO.getString("cal_mensaje_no_dato_logro"), sPagina + "2", 6, "verde", "center", ""));
/*     */       } else {
/*     */         
/* 413 */         eltr.appendChild(newtd("" + reg.getP02(), 6, ""));
/*     */       } 
/*     */       
/* 416 */       if (reg.getP03().equals("N") && ((annoActual == reg.getCodigoCiclo() && 3 < mesActual) || reg.getCodigoCiclo() < annoActual)) {
/* 417 */         eltr.appendChild(newtdhref(ParametrosDTO.getString("cal_mensaje_no_dato_logro"), sPagina + "3", 6, "verde", "center", ""));
/*     */       } else {
/*     */         
/* 420 */         eltr.appendChild(newtd("" + reg.getP02(), 6, ""));
/*     */       } 
/* 422 */       if (reg.getP04().equals("N") && ((annoActual == reg.getCodigoCiclo() && 4 < mesActual) || reg.getCodigoCiclo() < annoActual)) {
/* 423 */         eltr.appendChild(newtdhref(ParametrosDTO.getString("cal_mensaje_no_dato_logro"), sPagina + "4", 6, "verde", "center", ""));
/*     */       } else {
/*     */         
/* 426 */         eltr.appendChild(newtd("" + reg.getP02(), 6, ""));
/*     */       } 
/*     */       
/* 429 */       if (reg.getP05().equals("N") && ((annoActual == reg.getCodigoCiclo() && 5 < mesActual) || reg.getCodigoCiclo() < annoActual)) {
/* 430 */         eltr.appendChild(newtdhref(ParametrosDTO.getString("cal_mensaje_no_dato_logro"), sPagina + "5", 6, "verde", "center", ""));
/*     */       } else {
/*     */         
/* 433 */         eltr.appendChild(newtd("" + reg.getP02(), 6, ""));
/*     */       } 
/*     */       
/* 436 */       if (reg.getP06().equals("N") && ((annoActual == reg.getCodigoCiclo() && 6 < mesActual) || reg.getCodigoCiclo() < annoActual)) {
/* 437 */         eltr.appendChild(newtdhref(ParametrosDTO.getString("cal_mensaje_no_dato_logro"), sPagina + "6", 6, "verde", "center", ""));
/*     */       } else {
/*     */         
/* 440 */         eltr.appendChild(newtd("" + reg.getP02(), 6, ""));
/*     */       } 
/*     */       
/* 443 */       if (reg.getP07().equals("N") && ((annoActual == reg.getCodigoCiclo() && 7 < mesActual) || reg.getCodigoCiclo() < annoActual)) {
/* 444 */         eltr.appendChild(newtdhref(ParametrosDTO.getString("cal_mensaje_no_dato_logro"), sPagina + "7", 6, "verde", "center", ""));
/*     */       } else {
/*     */         
/* 447 */         eltr.appendChild(newtd("" + reg.getP07(), 6, ""));
/*     */       } 
/*     */       
/* 450 */       if (reg.getP08().equals("N") && ((annoActual == reg.getCodigoCiclo() && 8 < mesActual) || reg.getCodigoCiclo() < annoActual)) {
/* 451 */         eltr.appendChild(newtdhref(ParametrosDTO.getString("cal_mensaje_no_dato_logro"), sPagina + "8", 6, "verde", "center", ""));
/*     */       } else {
/*     */         
/* 454 */         eltr.appendChild(newtd("" + reg.getP02(), 6, ""));
/*     */       } 
/*     */       
/* 457 */       if (reg.getP09().equals("N") && ((annoActual == reg.getCodigoCiclo() && 9 < mesActual) || reg.getCodigoCiclo() < annoActual)) {
/* 458 */         eltr.appendChild(newtdhref(ParametrosDTO.getString("cal_mensaje_no_dato_logro"), sPagina + "9", 6, "verde", "center", ""));
/*     */       } else {
/*     */         
/* 461 */         eltr.appendChild(newtd("" + reg.getP02(), 6, ""));
/*     */       } 
/*     */       
/* 464 */       if (reg.getP10().equals("N") && ((annoActual == reg.getCodigoCiclo() && 10 < mesActual) || reg.getCodigoCiclo() < annoActual)) {
/* 465 */         eltr.appendChild(newtdhref(ParametrosDTO.getString("cal_mensaje_no_dato_logro"), sPagina + "10", 6, "verde", "center", ""));
/*     */       } else {
/*     */         
/* 468 */         eltr.appendChild(newtd("" + reg.getP02(), 6, ""));
/*     */       } 
/* 470 */       if (reg.getP11().equals("N") && ((annoActual == reg.getCodigoCiclo() && 11 < mesActual) || reg.getCodigoCiclo() < annoActual)) {
/* 471 */         eltr.appendChild(newtdhref(ParametrosDTO.getString("cal_mensaje_no_dato_logro"), sPagina + "11", 6, "verde", "center", ""));
/*     */       } else {
/*     */         
/* 474 */         eltr.appendChild(newtd("" + reg.getP02(), 6, ""));
/*     */       } 
/* 476 */       if (reg.getP12().equals("N") && ((annoActual == reg.getCodigoCiclo() && 12 < mesActual) || reg.getCodigoCiclo() < annoActual)) {
/* 477 */         eltr.appendChild(newtdhref(ParametrosDTO.getString("cal_mensaje_no_dato_logro"), sPagina + "12", 6, "verde", "center", ""));
/*     */       } else {
/*     */         
/* 480 */         eltr.appendChild(newtd("" + reg.getP02(), 6, ""));
/*     */       } 
/*     */ 
/*     */       
/* 484 */       laTabla.appendChild(eltr);
/*     */     } 
/* 486 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 487 */     td.appendChild(laTabla);
/* 488 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement losTitulos() {
/* 496 */     HTMLElement laTabla = (HTMLElement)this.pagHTML.createElement("table");
/* 497 */     laTabla.setAttributeNode(newAttr("class", "fprofb"));
/* 498 */     laTabla.setAttributeNode(newAttr("align", "left"));
/* 499 */     laTabla.setAttributeNode(newAttr("width", "100%%"));
/*     */     
/* 501 */     HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 502 */     eltr.appendChild(newtdMeta("Meta", 22, "cf2", "center", "Descripción Meta"));
/*     */     
/* 504 */     for (int i = 1; i <= 12; i++) {
/* 505 */       eltr.appendChild(newtdMeta(Utilidades.nombreMes(i).substring(0, 3), 6, "cf" + ((i % 2 == 0) ? 1 : 2), "center", Utilidades.nombreMes(i)));
/*     */     }
/* 507 */     laTabla.appendChild(eltr);
/*     */     
/* 509 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 510 */     laTabla.setAttributeNode(newAttr("class", "CF2"));
/* 511 */     td.appendChild(laTabla);
/* 512 */     return td;
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
/* 525 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 526 */     atrib.setValue(valor);
/* 527 */     return atrib;
/*     */   }
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo, int ancho, String clase, String align, String title) {
/* 531 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 532 */     Element enlace = this.pagHTML.createElement("a");
/* 533 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 534 */     enlace.appendChild(hijo);
/* 535 */     Attr donde = this.pagHTML.createAttribute("href");
/*     */     
/* 537 */     if (clase.equals("rojo")) {
/* 538 */       enlace.setAttributeNode(newAttr("class", "blanco"));
/*     */     }
/* 540 */     donde.setValue(vinculo);
/* 541 */     enlace.setAttributeNode(donde);
/* 542 */     if (ancho > 0) {
/* 543 */       td.setAttributeNode(newAttr("width", "" + ancho + "%%"));
/*     */     }
/* 545 */     if (clase.length() > 0) {
/* 546 */       td.setAttributeNode(newAttr("class", clase));
/*     */     }
/* 548 */     if (align.length() > 0) {
/* 549 */       td.setAttributeNode(newAttr("align", "center"));
/*     */     }
/*     */     
/* 552 */     if (title.length() > 0) {
/* 553 */       td.setAttributeNode(newAttr("title", title));
/*     */     }
/*     */     
/* 556 */     td.appendChild(enlace);
/* 557 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtdMeta(String contenido, int ancho, String clase, String align, String title) {
/* 568 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 569 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*     */     
/* 571 */     if (ancho > 0) {
/* 572 */       td.setAttributeNode(newAttr("width", "" + ancho + "%%"));
/*     */     }
/* 574 */     if (clase.length() > 0) {
/* 575 */       td.setAttributeNode(newAttr("class", clase));
/*     */     }
/* 577 */     if (align.length() > 0) {
/* 578 */       td.setAttributeNode(newAttr("align", "center"));
/*     */     }
/*     */     
/* 581 */     if (title.length() > 0) {
/* 582 */       td.setAttributeNode(newAttr("title", title));
/*     */     }
/*     */     
/* 585 */     return td;
/*     */   }
/*     */   
/*     */   private HTMLElement newtd(String contenido, String clase, int colspan) {
/* 589 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 590 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 591 */     if (colspan > 0) {
/* 592 */       td.setAttributeNode(newAttr("colspan", "" + colspan));
/*     */     }
/* 594 */     if (clase.length() > 0) {
/* 595 */       td.setAttributeNode(newAttr("class", clase));
/*     */     }
/* 597 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido, int ancho, String clase) {
/* 608 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 609 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 610 */     td.setAttributeNode(newAttr("width", "" + ancho + "%%"));
/* 611 */     if (clase.length() > 0) {
/* 612 */       td.setAttributeNode(newAttr("class", clase));
/*     */     }
/* 614 */     return td;
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
/* 635 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 636 */     op.setValue("0");
/* 637 */     op.appendChild(this.pagHTML.createTextNode(""));
/* 638 */     combo.appendChild(op);
/*     */     
/* 640 */     rsTGen.cargarTodos(tabla, codigo, descripcion, condicion);
/*     */     TGeneralDTO RegGeneral;
/* 642 */     while ((RegGeneral = rsTGen.next()) != null) {
/* 643 */       op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 644 */       op.setValue("" + RegGeneral.getCodigo());
/* 645 */       op.appendChild(this.pagHTML.createTextNode(RegGeneral.getDescripcion()));
/* 646 */       if (defecto != null && defecto.equals(RegGeneral.getCodigo())) {
/* 647 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 648 */         escogida.setValue("on");
/* 649 */         op.setAttributeNode(escogida);
/*     */       } 
/* 651 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalMetasSinLogro.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */