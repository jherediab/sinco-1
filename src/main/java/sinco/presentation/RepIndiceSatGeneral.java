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
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import org.jfree.chart.ChartFactory;
/*     */ import org.jfree.chart.ChartUtilities;
/*     */ import org.jfree.chart.JFreeChart;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.category.DefaultCategoryDataset;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.IndiceSatisfaccionDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.IndiceSatisfaccionDAO;
/*     */ import sinco.data.SeguridadDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.presentation.RepIndiceSatGeneral;
/*     */ import sinco.presentation.RepIndiceSatGeneralHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RepIndiceSatGeneral
/*     */   implements HttpPresentation
/*     */ {
/*     */   private RepIndiceSatGeneralHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  48 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  49 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  51 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  53 */     String _operacion = comms.request.getParameter("_operacion");
/*  54 */     if (_operacion == null || _operacion.length() == 0) {
/*  55 */       _operacion = "X";
/*     */     }
/*     */     
/*  58 */     this.pagHTML = (RepIndiceSatGeneralHTML)comms.xmlcFactory.create(RepIndiceSatGeneralHTML.class);
/*     */     
/*  60 */     if (_operacion.equals("X")) {
/*  61 */       parametros(comms, _operacion);
/*     */       
/*  63 */       Element division = this.pagHTML.getElementTblResultados();
/*  64 */       division.getParentNode().removeChild(division);
/*     */       
/*  66 */       combos(comms);
/*     */     }
/*  68 */     else if (_operacion.equals("L")) {
/*     */       
/*  70 */       combos(comms);
/*     */       
/*  72 */       Element division = this.pagHTML.getElementTrFiltros();
/*  73 */       division.getParentNode().removeChild(division);
/*     */       
/*  75 */       Collection arrAreas = cargarAreas(comms);
/*     */       
/*  77 */       String respuesta = listar(comms, arrAreas);
/*     */       
/*  79 */       if (respuesta != null) {
/*  80 */         comms.response.setContentType("application/xls");
/*  81 */         comms.response.setHeader("Content-Disposition", "inline;filename=IndiceSatisfaccion.xls");
/*  82 */         comms.response.setStatus(200, "Good job");
/*     */         
/*  84 */         HttpPresentationOutputStream out = comms.response.getOutputStream();
/*     */ 
/*     */         
/*     */         try {
/*  88 */           out.write(respuesta.getBytes());
/*  89 */           out.flush();
/*     */           
/*     */           return;
/*  92 */         } catch (Exception e) {
/*  93 */           throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorGenerandoReporte"));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  99 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/* 100 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private void parametros(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
/* 115 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/* 116 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 118 */     SisUsuariosDAO rsPersona = new SisUsuariosDAO();
/* 119 */     SisUsuariosDTO p = rsPersona.cargarRegistro(idNav);
/*     */     
/* 121 */     SeguridadDAO rsSeguridad = new SeguridadDAO();
/* 122 */     if (!rsSeguridad.getEstadoConexion()) {
/* 123 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 125 */     boolean gIndiceArea = rsSeguridad.tieneLlave(miGrupo, "oIndiceArea");
/* 126 */     boolean bMostrarTodasAreas = rsSeguridad.tieneLlave(miGrupo, "oMostrarTodasLasAreas");
/* 127 */     rsSeguridad.close();
/*     */     
/* 129 */     Collection arr = null;
/*     */     
/* 131 */     AreasDAO af = new AreasDAO();
/* 132 */     if (bMostrarTodasAreas) {
/* 133 */       arr = af.cargarActivas();
/*     */     }
/* 135 */     else if (gIndiceArea) {
/* 136 */       arr = af.cargarAreasHijas(idNav);
/*     */     } else {
/*     */       
/* 139 */       arr = af.cargarMisAreas(idNav);
/*     */     } 
/* 141 */     af.close();
/*     */     
/* 143 */     boolean fondo = false;
/* 144 */     Iterator iterator = arr.iterator();
/* 145 */     HTMLTableElement hte = this.pagHTML.getElementDetAreas();
/* 146 */     while (iterator.hasNext()) {
/* 147 */       AreasDTO reg = (AreasDTO)iterator.next();
/*     */       
/* 149 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 150 */       HTMLElement tdMarca = (HTMLElement)this.pagHTML.createElement("td");
/*     */       
/* 152 */       fondo = !fondo;
/* 153 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 155 */       HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
/* 156 */       checkbox.setAttribute("type", "checkbox");
/* 157 */       checkbox.setName("A_" + reg.getCodigo());
/* 158 */       tdMarca.appendChild(checkbox);
/* 159 */       tdMarca.setAttributeNode(newAttr("class", "ctd"));
/*     */       
/* 161 */       eltr.appendChild(tdMarca);
/* 162 */       eltr.appendChild(newtd("" + reg.getDescripcion(), false));
/*     */       
/* 164 */       hte.appendChild(eltr);
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
/*     */   private String listar(HttpPresentationComms comms, Collection arrAreas) throws HttpPresentationException, KeywordValueException {
/* 181 */     int anno = Integer.parseInt(comms.request.getParameter("anno"));
/* 182 */     int mes1 = Integer.parseInt(comms.request.getParameter("mes1"));
/* 183 */     int mes2 = Integer.parseInt(comms.request.getParameter("mes2"));
/*     */     
/* 185 */     String exportar = comms.request.getParameter("exportar");
/* 186 */     if (exportar == null) exportar = "N";
/*     */ 
/*     */     
/* 189 */     IndiceSatisfaccionDAO rs = new IndiceSatisfaccionDAO();
/* 190 */     Collection arr = rs.cargarIndiceSecuencia(arrAreas, anno, mes1, mes2);
/* 191 */     rs.close();
/*     */     
/* 193 */     String graficar = comms.request.getParameter("graficar");
/* 194 */     if (graficar == null) graficar = "N";
/*     */     
/* 196 */     if (graficar.equals("S")) {
/* 197 */       graficar(comms, arr);
/* 198 */       return null;
/*     */     } 
/*     */     
/* 201 */     int oe = 0, ob = 0, or = 0;
/* 202 */     int ce = 0, cb = 0, cr = 0;
/*     */     
/* 204 */     this.pagHTML.setTextParAnno("" + anno);
/* 205 */     this.pagHTML.setTextParMes1("" + Utilidades.nombreMes(mes1));
/* 206 */     this.pagHTML.setTextParMes2("" + Utilidades.nombreMes(mes2));
/*     */     
/* 208 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/*     */     
/* 210 */     String respuesta = "Area\tOport Excede\tOport Cumple\tOport No Cumple\tPerc. Serv. Excede\tPerc. Serv. Cumple\tPerc. Serv. No Cumple\tInd Oportunidad\tInd Perc. Serv.\tInd Satisfacción";
/*     */     
/* 212 */     Iterator iterator = arr.iterator();
/* 213 */     while (iterator.hasNext()) {
/* 214 */       IndiceSatisfaccionDTO reg = (IndiceSatisfaccionDTO)iterator.next();
/*     */       
/* 216 */       if (reg.getOportunidadExcelente() + reg.getOportunidadBuena() + reg.getOportunidadRegular() + reg.getConfiabilidadExcelente() + reg.getConfiabilidadBuena() + reg.getConfiabilidadRegular() > 0) {
/*     */ 
/*     */ 
/*     */         
/* 220 */         if (exportar.equals("S")) {
/* 221 */           respuesta = respuesta + "\n" + reg.getNombre() + "\t" + reg.getOportunidadExcelente() + "\t" + reg.getOportunidadBuena() + "\t" + reg.getOportunidadRegular() + "\t" + reg.getConfiabilidadExcelente() + "\t" + reg.getConfiabilidadBuena() + "\t" + reg.getConfiabilidadRegular() + "\t" + ((reg.getIndiceOportunidad() == 0.0F) ? "N/A" : (Utilidades.miles(Utilidades.round2(reg.getIndiceOportunidad()), 2) + " %")) + "\t" + ((reg.getIndiceConfiabilidad() == 0.0F) ? "N/A" : (Utilidades.miles(Utilidades.round2(reg.getIndiceConfiabilidad()), 2) + " %")) + "\t" + ((reg.getIndiceTotal() == 0.0D) ? "N/A" : (Utilidades.miles(Utilidades.round2(reg.getIndiceTotal()), 2) + " %"));
/*     */ 
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
/* 235 */         HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */         
/* 237 */         eltr.appendChild(newtdhref("" + reg.getNombre(), "RepIndiceSatArea.po?sujeto=" + reg.getCodigo() + "&anno=" + anno + "&mes1=" + mes1 + "&mes2=" + mes2));
/*     */         
/* 239 */         eltr.appendChild(newtd("" + reg.getOportunidadExcelente(), true));
/* 240 */         eltr.appendChild(newtd("" + reg.getOportunidadBuena(), true));
/* 241 */         eltr.appendChild(newtd("" + reg.getOportunidadRegular(), true));
/*     */         
/* 243 */         eltr.appendChild(newtd("" + reg.getConfiabilidadExcelente(), true));
/* 244 */         eltr.appendChild(newtd("" + reg.getConfiabilidadBuena(), true));
/* 245 */         eltr.appendChild(newtd("" + reg.getConfiabilidadRegular(), true));
/*     */         
/* 247 */         eltr.appendChild(newtd((reg.getIndiceOportunidad() == 0.0F) ? "N/A" : (Utilidades.miles(Utilidades.round2(reg.getIndiceOportunidad()), 2) + " %"), true));
/* 248 */         eltr.appendChild(newtd((reg.getIndiceConfiabilidad() == 0.0F) ? "N/A" : (Utilidades.miles(Utilidades.round2(reg.getIndiceConfiabilidad()), 2) + " %"), true));
/*     */         
/* 250 */         oe += reg.getOportunidadExcelente();
/* 251 */         ob += reg.getOportunidadBuena();
/* 252 */         or += reg.getOportunidadRegular();
/*     */         
/* 254 */         ce += reg.getConfiabilidadExcelente();
/* 255 */         cb += reg.getConfiabilidadBuena();
/* 256 */         cr += reg.getConfiabilidadRegular();
/*     */         
/* 258 */         double indice_total = reg.getIndiceTotal();
/*     */         
/* 260 */         eltr.appendChild(newtd("" + ((indice_total == 0.0D) ? "N/A" : (Utilidades.miles(Utilidades.round2(indice_total), 2) + " %")), true));
/*     */         
/* 262 */         hte.appendChild(eltr);
/*     */       } 
/*     */     } 
/*     */     
/* 266 */     if (exportar.equals("S")) {
/* 267 */       return respuesta;
/*     */     }
/*     */ 
/*     */     
/* 271 */     float indiceOportunidad = Utilidades.calcularIndice(oe, ob, or);
/* 272 */     float indiceConfiabilidad = Utilidades.calcularIndice(ce, cb, cr);
/*     */     
/* 274 */     double indice_total = 0.0D;
/* 275 */     if (indiceOportunidad == 0.0F || indiceConfiabilidad == 0.0F) {
/* 276 */       indice_total = (indiceOportunidad + indiceConfiabilidad);
/*     */     } else {
/*     */       
/* 279 */       indice_total = ((indiceOportunidad * ParametrosDTO.getInt("porcentaje.oportunidad") + indiceConfiabilidad * ParametrosDTO.getInt("porcentaje.confiabilidad")) / 100.0F);
/*     */     } 
/*     */     
/* 282 */     this.pagHTML.setTextOE("" + oe);
/* 283 */     this.pagHTML.setTextOB("" + ob);
/* 284 */     this.pagHTML.setTextOR("" + or);
/* 285 */     this.pagHTML.setTextPE("" + ce);
/* 286 */     this.pagHTML.setTextPB("" + cb);
/* 287 */     this.pagHTML.setTextPR("" + cr);
/*     */     
/* 289 */     this.pagHTML.setTextIO("" + ((indiceOportunidad == 0.0D) ? "N/A" : (Utilidades.miles(Utilidades.round2(indiceOportunidad), 2) + " %")));
/* 290 */     this.pagHTML.setTextIP("" + ((indiceConfiabilidad == 0.0D) ? "N/A" : (Utilidades.miles(Utilidades.round2(indiceConfiabilidad), 2) + " %")));
/* 291 */     this.pagHTML.setTextIS("" + ((indice_total == 0.0D) ? "N/A" : (Utilidades.miles(Utilidades.round2(indice_total), 2) + " %")));
/* 292 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void graficar(HttpPresentationComms comms, Collection arr) throws HttpPresentationException, KeywordValueException {
/* 302 */     DefaultCategoryDataset objDatos = new DefaultCategoryDataset();
/*     */     
/* 304 */     Iterator iterator = arr.iterator();
/* 305 */     while (iterator.hasNext()) {
/* 306 */       IndiceSatisfaccionDTO reg = (IndiceSatisfaccionDTO)iterator.next();
/*     */       
/* 308 */       if (reg.getOportunidadExcelente() + reg.getOportunidadBuena() + reg.getOportunidadRegular() + reg.getConfiabilidadExcelente() + reg.getConfiabilidadBuena() + reg.getConfiabilidadRegular() > 0) {
/*     */ 
/*     */ 
/*     */         
/* 312 */         if (reg.getIndiceOportunidad() > 0.0F) {
/* 313 */           objDatos.addValue(reg.getIndiceOportunidad(), reg.getNombre(), "Ind Opor");
/*     */         }
/*     */         
/* 316 */         if (reg.getIndiceConfiabilidad() > 0.0F) {
/* 317 */           objDatos.addValue(reg.getIndiceConfiabilidad(), reg.getNombre(), "Ind Per");
/*     */         }
/*     */         
/* 320 */         if (reg.getIndiceTotal() > 0.0D) {
/* 321 */           objDatos.addValue(reg.getIndiceTotal(), reg.getNombre(), "Ind. Satisfaccion");
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 327 */     JFreeChart objGrafico = ChartFactory.createBarChart3D("Indices", "Areas", "Indice", objDatos, PlotOrientation.VERTICAL, true, true, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 339 */       ChartUtilities.writeChartAsJPEG(comms.response.getOutputStream(), objGrafico, 1200, 400);
/*     */     }
/* 341 */     catch (Exception e) {
/* 342 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorGenerandoGrafica"));
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
/*     */   private Collection cargarAreas(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 356 */     Collection resultados = new ArrayList();
/* 357 */     Enumeration enumera = comms.request.getParameterNames();
/*     */ 
/*     */     
/* 360 */     while (enumera.hasMoreElements()) {
/* 361 */       String param = (String)enumera.nextElement();
/* 362 */       if (param.substring(0, 2).equals("A_")) {
/* 363 */         int codigo = Integer.parseInt(param.substring(2, param.length()));
/* 364 */         resultados.add(new Integer(codigo));
/*     */       } 
/*     */     } 
/* 367 */     return resultados;
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
/*     */   private Attr newAttr(String tipo, String valor) {
/* 382 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 383 */     atrib.setValue(valor);
/* 384 */     return atrib;
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
/* 396 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 397 */     Element enlace = this.pagHTML.createElement("a");
/* 398 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 399 */     enlace.appendChild(hijo);
/* 400 */     Attr donde = this.pagHTML.createAttribute("href");
/* 401 */     donde.setValue(vinculo);
/* 402 */     enlace.setAttributeNode(donde);
/* 403 */     td.appendChild(enlace);
/* 404 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 405 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido, boolean align) {
/* 415 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 416 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 417 */     if (align) {
/* 418 */       td.setAttributeNode(newAttr("class", "ctdr"));
/*     */     } else {
/*     */       
/* 421 */       td.setAttributeNode(newAttr("class", "ctd"));
/*     */     } 
/* 423 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void combos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 432 */     int anno = 0;
/*     */     try {
/* 434 */       anno = Integer.parseInt(comms.request.getParameter("anno"));
/*     */     }
/* 436 */     catch (Exception e) {
/* 437 */       anno = Utilidades.getAnnoActual();
/*     */     } 
/* 439 */     int mes1 = 0;
/*     */     try {
/* 441 */       mes1 = Integer.parseInt(comms.request.getParameter("mes1"));
/*     */     }
/* 443 */     catch (Exception e) {
/* 444 */       mes1 = Utilidades.getMesActual();
/*     */     } 
/* 446 */     int mes2 = 0;
/*     */     try {
/* 448 */       mes2 = Integer.parseInt(comms.request.getParameter("mes2"));
/*     */     }
/* 450 */     catch (Exception e) {
/* 451 */       mes2 = Utilidades.getMesActual();
/*     */     } 
/*     */     
/* 454 */     HTMLSelectElement annos = this.pagHTML.getElementIdAnno();
/* 455 */     for (int j = 2000; j <= Utilidades.getAnnoActual(); j++) {
/* 456 */       HTMLOptionElement op1 = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 457 */       op1.setValue("" + j);
/* 458 */       op1.appendChild(this.pagHTML.createTextNode("" + j));
/* 459 */       annos.appendChild(op1);
/* 460 */       if (j == anno) {
/* 461 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 462 */         escogida.setValue("on");
/* 463 */         op1.setAttributeNode(escogida);
/*     */       } 
/*     */     } 
/*     */     
/* 467 */     HTMLSelectElement sMes1 = this.pagHTML.getElementIdMes1();
/* 468 */     HTMLSelectElement sMes2 = this.pagHTML.getElementIdMes2();
/* 469 */     String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
/*     */     
/* 471 */     for (int j = 1; j <= 12; j++) {
/* 472 */       HTMLOptionElement op2 = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 473 */       op2.setValue("" + j);
/* 474 */       op2.appendChild(this.pagHTML.createTextNode(meses[j - 1]));
/* 475 */       sMes1.appendChild(op2);
/* 476 */       if (j == mes1) {
/* 477 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 478 */         escogida.setValue("on");
/* 479 */         op2.setAttributeNode(escogida);
/*     */       } 
/*     */       
/* 482 */       HTMLOptionElement op3 = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 483 */       op3.setValue("" + j);
/* 484 */       op3.appendChild(this.pagHTML.createTextNode(meses[j - 1]));
/* 485 */       sMes2.appendChild(op3);
/* 486 */       if (j == mes2) {
/* 487 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 488 */         escogida.setValue("on");
/* 489 */         op3.setAttributeNode(escogida);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\RepIndiceSatGeneral.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */