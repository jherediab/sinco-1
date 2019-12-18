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
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.AMAccionesEstadisticaDTO;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AMEstadisticasDAO;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.presentation.AMEstadisticaAcciones;
/*     */ import sinco.presentation.AMEstadisticaAccionesHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AMEstadisticaAcciones
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AMEstadisticaAccionesHTML pagHTML;
/*     */   int codigo_area;
/*     */   int codigo_areaOld;
/*     */   int cuantos;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  39 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  40 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  42 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  44 */     int areaImplanta = 0;
/*     */     try {
/*  46 */       areaImplanta = Integer.parseInt(comms.request.getParameter("areaImplanta"));
/*     */     }
/*  48 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  51 */     String fechaDesde = comms.request.getParameter("fechaDesde");
/*  52 */     String fechaHasta = comms.request.getParameter("fechaHasta");
/*     */     
/*  54 */     int tipo = 0;
/*     */     try {
/*  56 */       tipo = Integer.parseInt(comms.request.getParameter("tipo"));
/*     */     }
/*  58 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  61 */     int codigo_estado = 0;
/*     */     try {
/*  63 */       codigo_estado = Integer.parseInt(comms.request.getParameter("codigo_estado"));
/*     */     }
/*  65 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  68 */     String accion = comms.request.getParameter("accion");
/*  69 */     String origen = comms.request.getParameter("origen");
/*  70 */     String proceso = comms.request.getParameter("proceso");
/*  71 */     String norma = comms.request.getParameter("norma");
/*     */ 
/*     */     
/*  74 */     this.pagHTML = (AMEstadisticaAccionesHTML)comms.xmlcFactory.create(AMEstadisticaAccionesHTML.class);
/*     */     
/*  76 */     int rta = 0;
/*  77 */     if (tipo == 1) {
/*  78 */       rta = detalle(areaImplanta, fechaDesde, fechaHasta, codigo_estado, accion, origen, proceso, norma);
/*     */     } else {
/*     */       
/*  81 */       rta = resumen(areaImplanta, fechaDesde, fechaHasta, codigo_estado, accion, origen, proceso, norma);
/*     */     } 
/*     */     
/*  84 */     if (rta == 1) {
/*  85 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/*  88 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  89 */     comms.response.writeDOM(this.pagHTML);
/*     */   }

/*     */   
/*     */   private int detalle(int areaImplanta, String fechaDesde, String fechaHasta, int codigo_estado, String accion, String origen, String proceso, String norma) {
/* 115 */     AreasDAO areaf = new AreasDAO();
/* 116 */     AreasDTO regArea = areaf.getArea(areaImplanta);
/* 117 */     areaf.close();
/*     */     
/* 119 */     if (regArea != null) {
/* 120 */       this.pagHTML.setTextArea("" + regArea.getDescripcion());
/*     */     }
/* 122 */     String periodo = "Periodo: ";
/* 123 */     if (fechaDesde.length() > 0) periodo = periodo + "" + fechaDesde; 
/* 124 */     if (fechaHasta.length() > 0) periodo = periodo + " hasta " + fechaHasta;
/*     */     
/* 126 */     this.pagHTML.setTextPeriodo(periodo);
/*     */     
/* 128 */     AMEstadisticasDAO rs = new AMEstadisticasDAO();
/* 129 */     if (!rs.getEstadoConexion()) {
/* 130 */       return 1;
/*     */     }
/* 132 */     Collection arr = rs.cargarEstadistica(regArea.getSecuencia(), fechaDesde, fechaHasta, codigo_estado, accion, origen, proceso, norma);
/*     */ 

/*     */ 
/*     */     
/* 142 */     rs.close();
/*     */     
/* 144 */     HTMLTableElement hte = this.pagHTML.getElementDetalle();
/* 145 */     boolean fondo = true;
/*     */     
/* 147 */     int itTotal = 0, itgTotal = 0;
/* 148 */     int itProceso = 0, itgProceso = 0;
/* 149 */     int itPorCalificar = 0, itgPorCalificar = 0;
/* 150 */     int itImplantadas = 0, itgImplantadas = 0;
/* 151 */     int itNoImplantadas = 0, itgNoImplantadas = 0;
/* 152 */     int itCumplieron = 0, itgCumplieron = 0;
/* 153 */     int itNoCumplieron = 0, itgNoCumplieron = 0;
/*     */     
/* 155 */     int itNumeroCausas = 0;
/* 156 */     int itProrroga0 = 0;
/* 157 */     int itProrroga1 = 0;
/* 158 */     int itProrroga2 = 0;
/* 159 */     int itProrroga3 = 0;
/* 160 */     int itProrroga4 = 0;
/*     */     
/* 162 */     int itgNumeroCausas = 0;
/* 163 */     int itgProrroga0 = 0;
/* 164 */     int itgProrroga1 = 0;
/* 165 */     int itgProrroga2 = 0;
/* 166 */     int itgProrroga3 = 0;
/* 167 */     int itgProrroga4 = 0;
/*     */     
/* 169 */     this.codigo_areaOld = 0;
/* 170 */     this.cuantos = 0;
/*     */     
/* 172 */     String parametros = "AMConsulta.po?limite=1&fechaDesde=" + fechaDesde + "&fechaHasta=" + fechaHasta + "&origen=" + origen + "&proceso=" + proceso + "&implantada=&revisada_comite=" + "&norma=" + norma;
/*     */ 
/*     */     
/* 175 */     Iterator iterator = arr.iterator();
/* 176 */     while (iterator.hasNext()) {
/* 177 */       AMAccionesEstadisticaDTO reg = (AMAccionesEstadisticaDTO)iterator.next();
/* 178 */       this.codigo_area = reg.getAreaImplanta();
/*     */       
/* 180 */       int rta = evaluarCorte();
/*     */ 
/*     */   HTMLElement eltr;
/*     */       
/* 184 */       switch (rta) {
/*     */         case 2:
/* 186 */           eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 187 */           eltr.appendChild(newtdn("Sub Total", false, 0));
/* 188 */           eltr.appendChild(newtd("" + itTotal));
/* 189 */           eltr.appendChild(newtd("" + itProceso));
/* 190 */           eltr.appendChild(newtd("" + itPorCalificar));
/* 191 */           eltr.appendChild(newtd("" + itImplantadas));
/* 192 */           eltr.appendChild(newtd("" + itCumplieron));
/* 193 */           eltr.appendChild(newtd("" + itNoCumplieron));
/* 194 */           eltr.appendChild(newtd("" + itNumeroCausas));
/* 195 */           eltr.appendChild(newtd("" + itProrroga0));
/* 196 */           eltr.appendChild(newtd("" + itProrroga1));
/* 197 */           eltr.appendChild(newtd("" + itProrroga2));
/* 198 */           eltr.appendChild(newtd("" + itProrroga3));
/* 199 */           eltr.appendChild(newtd("" + itProrroga4));
/*     */           
/* 201 */           if (itNumeroCausas > 0) {
/* 202 */             double total = ((itProrroga0 * ParametrosDTO.getInt("AM_porcentaje.Esc0") + itProrroga1 * ParametrosDTO.getInt("AM_porcentaje.Esc1") + itProrroga2 * ParametrosDTO.getInt("AM_porcentaje.Esc2") + itProrroga3 * ParametrosDTO.getInt("AM_porcentaje.Esc3") + itProrroga4 * ParametrosDTO.getInt("AM_porcentaje.Esc4")) / itNumeroCausas);
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 207 */             eltr.appendChild(newtd("" + Utilidades.miles(total, 0) + " %"));
/*     */           } else {
/*     */             
/* 210 */             eltr.appendChild(newtd("N/A"));
/*     */           } 
/*     */           
/* 213 */           if (itImplantadas > 0) {
/* 214 */             double dValor = itCumplieron / itImplantadas * 100.0D;
/* 215 */             eltr.appendChild(newtd("" + Utilidades.miles(dValor, 0) + " %"));
/*     */           } else {
/*     */             
/* 218 */             eltr.appendChild(newtd("N/A"));
/*     */           } 
/*     */           
/* 221 */           hte.appendChild(eltr);
/*     */           
/* 223 */           itTotal = 0;
/* 224 */           itProceso = 0;
/* 225 */           itPorCalificar = 0;
/* 226 */           itImplantadas = 0;
/* 227 */           itNoImplantadas = 0;
/* 228 */           itCumplieron = 0;
/* 229 */           itNoCumplieron = 0;
/*     */           
/* 231 */           itNumeroCausas = 0;
/* 232 */           itProrroga0 = 0;
/* 233 */           itProrroga1 = 0;
/* 234 */           itProrroga2 = 0;
/* 235 */           itProrroga3 = 0;
/* 236 */           itProrroga4 = 0;
/*     */ 
/*     */         
/*     */         case -1:
/* 240 */           eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 241 */           eltr.setAttributeNode(newAttr("bgcolor", "#FFFFFF"));
/* 242 */           eltr.appendChild(newtdn("" + reg.getNombreArea(), false, 15));
/* 243 */           hte.appendChild(eltr);
/*     */           break;
/*     */       } 
/*     */       
/* 247 */       this.cuantos++;
/*     */ 
/*     */       
/* 250 */       eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 251 */       fondo = !fondo;
/* 252 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/* 253 */       eltr.appendChild(newtd("" + reg.getNombreTipo()));
/*     */       
/* 255 */       eltr.appendChild(newtd("" + reg.getTotal()));
/* 256 */       eltr.appendChild(newtdhref("" + reg.getProceso(), parametros + "&areaImplanta=" + this.codigo_area + "&codigo_estado=1&accion=" + reg.getAccion()));
/* 257 */       eltr.appendChild(newtdhref("" + reg.getPorCalificar(), parametros + "&areaImplanta=" + this.codigo_area + "&codigo_estado=5&accion=" + reg.getAccion()));
/* 258 */       eltr.appendChild(newtdhref("" + reg.getImplantadas(), parametros + "&areaImplanta=" + this.codigo_area + "&codigo_estado=2&accion=" + reg.getAccion()));
/* 259 */       eltr.appendChild(newtdhref("" + reg.getCumplieron(), parametros + "&areaImplanta=" + this.codigo_area + "&cumplio=S&accion=" + reg.getAccion()));
/* 260 */       eltr.appendChild(newtdhref("" + reg.getNoCumplieron(), parametros + "&areaImplanta=" + this.codigo_area + "&cumplio=N&accion=" + reg.getAccion()));
/*     */       
/* 262 */       eltr.appendChild(newtd("" + reg.getNumeroCausas()));
/* 263 */       eltr.appendChild(newtd("" + reg.getProrroga0()));
/* 264 */       eltr.appendChild(newtd("" + reg.getProrroga1()));
/* 265 */       eltr.appendChild(newtd("" + reg.getProrroga2()));
/* 266 */       eltr.appendChild(newtd("" + reg.getProrroga3()));
/* 267 */       eltr.appendChild(newtd("" + reg.getProrroga4()));
/*     */       
/* 269 */       if (reg.getNumeroCausas() > 0) {
/*     */         
/* 271 */         double total = ((reg.getProrroga0() * ParametrosDTO.getInt("AM_porcentaje.Esc0") + reg.getProrroga1() * ParametrosDTO.getInt("AM_porcentaje.Esc1") + reg.getProrroga2() * ParametrosDTO.getInt("AM_porcentaje.Esc2") + reg.getProrroga3() * ParametrosDTO.getInt("AM_porcentaje.Esc3") + reg.getProrroga4() * ParametrosDTO.getInt("AM_porcentaje.Esc4")) / reg.getNumeroCausas());
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 276 */         eltr.appendChild(newtd("" + Utilidades.miles(total, 0) + " %"));
/*     */       } else {
/*     */         
/* 279 */         eltr.appendChild(newtd("N/A"));
/*     */       } 
/*     */       
/* 282 */       if (reg.getImplantadas() > 0) {
/* 283 */         double dValor = reg.getCumplieron() / reg.getImplantadas() * 100.0D;
/* 284 */         eltr.appendChild(newtd("" + Utilidades.miles(dValor, 0) + " %"));
/*     */       } else {
/*     */         
/* 287 */         eltr.appendChild(newtd("N/A"));
/*     */       } 
/*     */       
/* 290 */       hte.appendChild(eltr);
/*     */       
/* 292 */       itTotal += reg.getTotal();
/* 293 */       itgTotal += reg.getTotal();
/* 294 */       itProceso += reg.getProceso();
/* 295 */       itPorCalificar += reg.getPorCalificar();
/* 296 */       itgProceso += reg.getProceso();
/* 297 */       itgPorCalificar += reg.getPorCalificar();
/* 298 */       itImplantadas += reg.getImplantadas();
/* 299 */       itgImplantadas += reg.getImplantadas();
/* 300 */       itNoImplantadas += reg.getNoImplantadas();
/* 301 */       itgNoImplantadas += reg.getNoImplantadas();
/* 302 */       itCumplieron += reg.getCumplieron();
/* 303 */       itgCumplieron += reg.getCumplieron();
/* 304 */       itNoCumplieron += reg.getNoCumplieron();
/* 305 */       itgNoCumplieron += reg.getNoCumplieron();
/*     */       
/* 307 */       itNumeroCausas += reg.getNumeroCausas();
/* 308 */       itgNumeroCausas += reg.getNumeroCausas();
/* 309 */       itProrroga0 += reg.getProrroga0();
/* 310 */       itgProrroga0 += reg.getProrroga0();
/* 311 */       itProrroga1 += reg.getProrroga1();
/* 312 */       itgProrroga1 += reg.getProrroga1();
/* 313 */       itProrroga2 += reg.getProrroga2();
/* 314 */       itgProrroga2 += reg.getProrroga2();
/* 315 */       itProrroga3 += reg.getProrroga3();
/* 316 */       itgProrroga3 += reg.getProrroga3();
/* 317 */       itProrroga4 += reg.getProrroga4();
/* 318 */       itgProrroga4 += reg.getProrroga4();
/*     */       
/* 320 */       this.codigo_areaOld = this.codigo_area;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 326 */     HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 327 */     eltr.appendChild(newtdn("Sub Total", false, 0));
/* 328 */     eltr.appendChild(newtd("" + itTotal));
/* 329 */     eltr.appendChild(newtd("" + itProceso));
/* 330 */     eltr.appendChild(newtd("" + itPorCalificar));
/* 331 */     eltr.appendChild(newtd("" + itImplantadas));
/* 332 */     eltr.appendChild(newtd("" + itCumplieron));
/* 333 */     eltr.appendChild(newtd("" + itNoCumplieron));
/* 334 */     eltr.appendChild(newtd("" + itNumeroCausas));
/* 335 */     eltr.appendChild(newtd("" + itProrroga0));
/* 336 */     eltr.appendChild(newtd("" + itProrroga1));
/* 337 */     eltr.appendChild(newtd("" + itProrroga2));
/* 338 */     eltr.appendChild(newtd("" + itProrroga3));
/* 339 */     eltr.appendChild(newtd("" + itProrroga4));
/*     */ 
/*     */ 
/*     */     
/* 343 */     if (itNumeroCausas > 0) {
/* 344 */       double total = ((itProrroga0 * ParametrosDTO.getInt("AM_porcentaje.Esc0") + itProrroga1 * ParametrosDTO.getInt("AM_porcentaje.Esc1") + itProrroga2 * ParametrosDTO.getInt("AM_porcentaje.Esc2") + itProrroga3 * ParametrosDTO.getInt("AM_porcentaje.Esc3") + itProrroga4 * ParametrosDTO.getInt("AM_porcentaje.Esc4")) / itNumeroCausas);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 349 */       eltr.appendChild(newtd("" + Utilidades.miles(total, 0) + " %"));
/*     */     } else {
/*     */       
/* 352 */       eltr.appendChild(newtd("N/A"));
/*     */     } 
/*     */     
/* 355 */     if (itImplantadas > 0) {
/* 356 */       double dValor = itCumplieron / itImplantadas * 100.0D;
/* 357 */       eltr.appendChild(newtd("" + Utilidades.miles(dValor, 0) + " %"));
/*     */     } else {
/*     */       
/* 360 */       eltr.appendChild(newtd("N/A"));
/*     */     } 
/* 362 */     hte.appendChild(eltr);
/*     */ 
/*     */     
/* 365 */     eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 366 */     eltr.appendChild(newtdn("Totales", false, 0));
/* 367 */     eltr.appendChild(newtdp("" + itgTotal));
/* 368 */     eltr.appendChild(newtdp("" + itgProceso));
/* 369 */     eltr.appendChild(newtdp("" + itgPorCalificar));
/* 370 */     eltr.appendChild(newtdp("" + itgImplantadas));
/* 371 */     eltr.appendChild(newtdp("" + itgCumplieron));
/* 372 */     eltr.appendChild(newtdp("" + itgNoCumplieron));
/*     */     
/* 374 */     eltr.appendChild(newtdp("" + itgNumeroCausas));
/* 375 */     eltr.appendChild(newtdp("" + itgProrroga0));
/* 376 */     eltr.appendChild(newtdp("" + itgProrroga1));
/* 377 */     eltr.appendChild(newtdp("" + itgProrroga2));
/* 378 */     eltr.appendChild(newtdp("" + itgProrroga3));
/* 379 */     eltr.appendChild(newtdp("" + itgProrroga4));
/*     */ 
/*     */     
/* 382 */     if (itgNumeroCausas > 0) {
/* 383 */       double total = ((itgProrroga0 * ParametrosDTO.getInt("AM_porcentaje.Esc0") + itgProrroga1 * ParametrosDTO.getInt("AM_porcentaje.Esc1") + itgProrroga2 * ParametrosDTO.getInt("AM_porcentaje.Esc2") + itgProrroga3 * ParametrosDTO.getInt("AM_porcentaje.Esc3") + itgProrroga4 * ParametrosDTO.getInt("AM_porcentaje.Esc4")) / itgNumeroCausas);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 388 */       eltr.appendChild(newtdp("" + Utilidades.miles(total, 0) + " %"));
/*     */     } else {
/*     */       
/* 391 */       eltr.appendChild(newtdp("N/A"));
/*     */     } 
/*     */     
/* 394 */     if (itgImplantadas > 0) {
/* 395 */       double dValor = itgCumplieron / itgImplantadas * 100.0D;
/* 396 */       eltr.appendChild(newtdp("" + Utilidades.miles(dValor, 0) + " %"));
/*     */     } else {
/*     */       
/* 399 */       eltr.appendChild(newtdp("N/A"));
/*     */     } 
/*     */     
/* 402 */     hte.appendChild(eltr);
/*     */     
/* 404 */     return 0;
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
/*     */   private int resumen(int areaImplanta, String fechaDesde, String fechaHasta, int codigo_estado, String accion, String origen, String proceso, String norma) {
/* 430 */     AreasDAO areaf = new AreasDAO();
/* 431 */     AreasDTO regArea = areaf.getArea(areaImplanta);
/* 432 */     areaf.close();
/*     */     
/* 434 */     if (regArea != null) {
/* 435 */       this.pagHTML.setTextArea("" + regArea.getDescripcion());
/*     */     }
/* 437 */     String periodo = "Periodo: ";
/* 438 */     if (fechaDesde.length() > 0) periodo = periodo + "" + fechaDesde; 
/* 439 */     if (fechaHasta.length() > 0) periodo = periodo + " hasta " + fechaHasta;
/*     */     
/* 441 */     this.pagHTML.setTextPeriodo(periodo);
/*     */     
/* 443 */     AMEstadisticasDAO rsAcciones = new AMEstadisticasDAO();
/* 444 */     if (!rsAcciones.getEstadoConexion()) {
/* 445 */       return 1;
/*     */     }
/* 447 */     Collection arr = rsAcciones.resumenEstadistica(regArea.getSecuencia(), fechaDesde, fechaHasta, codigo_estado, accion, origen, proceso, norma);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 457 */     rsAcciones.close();
/*     */     
/* 459 */     HTMLTableElement hte = this.pagHTML.getElementDetalle();
/* 460 */     boolean fondo = true;
/*     */     
/* 462 */     int itgTotal = 0;
/* 463 */     int itgProceso = 0;
/* 464 */     int itgImplantadas = 0;
/* 465 */     int itgCumplieron = 0;
/* 466 */     int itgNoCumplieron = 0;
/*     */     
/* 468 */     int itgNumeroCausas = 0;
/* 469 */     int itgPorCalificar = 0;
/* 470 */     int itgProrroga0 = 0;
/* 471 */     int itgProrroga1 = 0;
/* 472 */     int itgProrroga2 = 0;
/* 473 */     int itgProrroga3 = 0;
/* 474 */     int itgProrroga4 = 0;
/*     */     
/* 476 */     this.cuantos = 0;
/*     */     
/* 478 */     Iterator iterator = arr.iterator();
/* 479 */     while (iterator.hasNext()) {
/* 480 */       AMAccionesEstadisticaDTO reg = (AMAccionesEstadisticaDTO)iterator.next();
/*     */       
/* 482 */       this.cuantos++;
/* 483 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 484 */       fondo = !fondo;
/* 485 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/* 486 */       eltr.appendChild(newtd("" + reg.getNombreTipo()));
/*     */       
/* 488 */       eltr.appendChild(newtd("" + reg.getTotal()));
/* 489 */       eltr.appendChild(newtd("" + reg.getProceso()));
/* 490 */       eltr.appendChild(newtd("" + reg.getPorCalificar()));
/* 491 */       eltr.appendChild(newtd("" + reg.getImplantadas()));
/* 492 */       eltr.appendChild(newtd("" + reg.getCumplieron()));
/* 493 */       eltr.appendChild(newtd("" + reg.getNoCumplieron()));
/*     */       
/* 495 */       eltr.appendChild(newtd("" + reg.getNumeroCausas()));
/* 496 */       eltr.appendChild(newtd("" + reg.getProrroga0()));
/* 497 */       eltr.appendChild(newtd("" + reg.getProrroga1()));
/* 498 */       eltr.appendChild(newtd("" + reg.getProrroga2()));
/* 499 */       eltr.appendChild(newtd("" + reg.getProrroga3()));
/* 500 */       eltr.appendChild(newtd("" + reg.getProrroga4()));
/*     */       
/* 502 */       if (reg.getNumeroCausas() > 0) {
/*     */         
/* 504 */         double total = ((reg.getProrroga0() * ParametrosDTO.getInt("AM_porcentaje.Esc0") + reg.getProrroga1() * ParametrosDTO.getInt("AM_porcentaje.Esc1") + reg.getProrroga2() * ParametrosDTO.getInt("AM_porcentaje.Esc2") + reg.getProrroga3() * ParametrosDTO.getInt("AM_porcentaje.Esc3") + reg.getProrroga4() * ParametrosDTO.getInt("AM_porcentaje.Esc4")) / reg.getNumeroCausas());
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 509 */         eltr.appendChild(newtd("" + Utilidades.miles(total, 0) + " %"));
/*     */       } else {
/*     */         
/* 512 */         eltr.appendChild(newtd("N/A"));
/*     */       } 
/*     */       
/* 515 */       if (reg.getImplantadas() > 0) {
/* 516 */         double dValor = reg.getCumplieron() / reg.getImplantadas() * 100.0D;
/* 517 */         eltr.appendChild(newtd("" + Utilidades.miles(dValor, 0) + " %"));
/*     */       } else {
/*     */         
/* 520 */         eltr.appendChild(newtd("N/A"));
/*     */       } 
/* 522 */       hte.appendChild(eltr);
/*     */       
/* 524 */       itgTotal += reg.getTotal();
/* 525 */       itgProceso += reg.getProceso();
/* 526 */       itgPorCalificar += reg.getPorCalificar();
/* 527 */       itgImplantadas += reg.getImplantadas();
/* 528 */       itgCumplieron += reg.getCumplieron();
/* 529 */       itgNoCumplieron += reg.getNoCumplieron();
/*     */       
/* 531 */       itgNumeroCausas += reg.getNumeroCausas();
/* 532 */       itgProrroga0 += reg.getProrroga0();
/* 533 */       itgProrroga1 += reg.getProrroga1();
/* 534 */       itgProrroga2 += reg.getProrroga2();
/* 535 */       itgProrroga3 += reg.getProrroga3();
/* 536 */       itgProrroga4 += reg.getProrroga4();
/*     */     } 
/*     */ 
/*     */     
/* 540 */     HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 541 */     eltr.appendChild(newtdn("Totales", false, 0));
/* 542 */     eltr.appendChild(newtdp("" + itgTotal));
/* 543 */     eltr.appendChild(newtdp("" + itgProceso));
/* 544 */     eltr.appendChild(newtdp("" + itgPorCalificar));
/* 545 */     eltr.appendChild(newtdp("" + itgImplantadas));
/* 546 */     eltr.appendChild(newtdp("" + itgCumplieron));
/* 547 */     eltr.appendChild(newtdp("" + itgNoCumplieron));
/*     */     
/* 549 */     eltr.appendChild(newtdp("" + itgNumeroCausas));
/* 550 */     eltr.appendChild(newtdp("" + itgProrroga0));
/* 551 */     eltr.appendChild(newtdp("" + itgProrroga1));
/* 552 */     eltr.appendChild(newtdp("" + itgProrroga2));
/* 553 */     eltr.appendChild(newtdp("" + itgProrroga3));
/* 554 */     eltr.appendChild(newtdp("" + itgProrroga4));
/*     */     
/* 556 */     if (itgNumeroCausas > 0) {
/* 557 */       double total = ((itgProrroga0 * ParametrosDTO.getInt("AM_porcentaje.Esc0") + itgProrroga1 * ParametrosDTO.getInt("AM_porcentaje.Esc1") + itgProrroga2 * ParametrosDTO.getInt("AM_porcentaje.Esc2") + itgProrroga3 * ParametrosDTO.getInt("AM_porcentaje.Esc3") + itgProrroga4 * ParametrosDTO.getInt("AM_porcentaje.Esc4")) / itgNumeroCausas);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 562 */       eltr.appendChild(newtdp("" + Utilidades.miles(total, 0) + " %"));
/*     */     } else {
/*     */       
/* 565 */       eltr.appendChild(newtdp("N/A"));
/*     */     } 
/*     */     
/* 568 */     if (itgImplantadas > 0) {
/* 569 */       double dValor = itgCumplieron / itgImplantadas * 100.0D;
/* 570 */       eltr.appendChild(newtdp("" + Utilidades.miles(dValor, 0) + " %"));
/*     */     } else {
/*     */       
/* 573 */       eltr.appendChild(newtdp("N/A"));
/*     */     } 
/* 575 */     hte.appendChild(eltr);
/* 576 */     return 0;
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
/* 590 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 591 */     atrib.setValue(valor);
/* 592 */     return atrib;
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
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 604 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 605 */     Element enlace = this.pagHTML.createElement("a");
/* 606 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 607 */     enlace.appendChild(hijo);
/* 608 */     Attr donde = this.pagHTML.createAttribute("href");
/* 609 */     donde.setValue(vinculo);
/* 610 */     enlace.setAttributeNode(donde);
/* 611 */     td.appendChild(enlace);
/* 612 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 613 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 624 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 625 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 626 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 627 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtdp(String contenido) {
/* 636 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 637 */     td.setAttributeNode(newAttr("class", "pie"));
/* 638 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 639 */     return td;
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
/*     */   private HTMLElement newtdn(String contenido, boolean alinear, int colspan) {
/* 651 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 652 */     Element negrita = this.pagHTML.createElement("b");
/* 653 */     negrita.appendChild(this.pagHTML.createTextNode(contenido));
/* 654 */     td.appendChild(negrita);
/* 655 */     if (alinear) {
/* 656 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 658 */     if (colspan > 0) {
/* 659 */       td.setAttributeNode(newAttr("colspan", "" + colspan));
/*     */     }
/* 661 */     td.setAttributeNode(newAttr("class", "pie"));
/* 662 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int evaluarCorte() {
/* 670 */     if (this.cuantos == 0) return -1;
/*     */     
/* 672 */     if (this.codigo_areaOld != this.codigo_area) return 2; 
/* 673 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AMEstadisticaAcciones.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */