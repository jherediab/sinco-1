/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationOutputStream;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.IndiceSatisfaccionDTO;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AcumuladosDAO;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.SeguridadDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.presentation.ReporteIndicePeriodos;
/*     */ import sinco.presentation.ReporteIndicePeriodosHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ReporteIndicePeriodos
/*     */   implements HttpPresentation
/*     */ {
/*     */   private ReporteIndicePeriodosHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  37 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  38 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  40 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  42 */     String _operacion = comms.request.getParameter("_operacion");
/*  43 */     if (_operacion == null || _operacion.length() == 0) {
/*  44 */       _operacion = "X";
/*     */     }
/*     */     
/*  47 */     this.pagHTML = (ReporteIndicePeriodosHTML)comms.xmlcFactory.create(ReporteIndicePeriodosHTML.class);
/*     */     
/*  49 */     parametros(comms, _operacion);
/*     */     
/*  51 */     if (_operacion.equals("L")) {
/*  52 */       String respuesta = listar(comms);
/*     */       
/*  54 */       if (respuesta != null) {
/*  55 */         comms.response.setContentType("application/xls");
/*  56 */         comms.response.setHeader("Content-Disposition", "inline;filename=IndiceSatisfaccion.xls");
/*  57 */         comms.response.setStatus(200, "Good job");
/*     */         
/*  59 */         HttpPresentationOutputStream out = comms.response.getOutputStream();
/*     */ 
/*     */         
/*     */         try {
/*  63 */           out.write(respuesta.getBytes());
/*  64 */           out.flush();
/*     */           
/*     */           return;
/*  67 */         } catch (Exception e) {
/*  68 */           throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorGenerandoReporte"));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  74 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
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
/*     */ 
/*     */ 
/*     */   
/*     */   private void parametros(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
/*  90 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  91 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/*  93 */     int anno = 0;
/*     */     try {
/*  95 */       anno = Integer.parseInt(comms.request.getParameter("anno"));
/*     */     }
/*  97 */     catch (Exception e) {
/*  98 */       anno = Utilidades.getAnnoActual();
/*     */     } 
/* 100 */     int iMes1 = 0;
/*     */     try {
/* 102 */       iMes1 = Integer.parseInt(comms.request.getParameter("mes1"));
/*     */     }
/* 104 */     catch (Exception e) {
/* 105 */       iMes1 = Utilidades.getMesActual();
/*     */     } 
/* 107 */     int iMes2 = 0;
/*     */     try {
/* 109 */       iMes2 = Integer.parseInt(comms.request.getParameter("mes2"));
/*     */     }
/* 111 */     catch (Exception e) {
/* 112 */       iMes2 = Utilidades.getMesActual();
/*     */     } 
/*     */     
/* 115 */     HTMLSelectElement annos = this.pagHTML.getElementIdAnno();
/* 116 */     for (int i = 2000; i <= Utilidades.getAnnoActual(); i++) {
/* 117 */       HTMLOptionElement op1 = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 118 */       op1.setValue(i + "");
/* 119 */       op1.appendChild(this.pagHTML.createTextNode(i + ""));
/* 120 */       annos.appendChild(op1);
/* 121 */       if (i == anno) {
/* 122 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 123 */         escogida.setValue("on");
/* 124 */         op1.setAttributeNode(escogida);
/*     */       } 
/*     */     } 
/*     */     
/* 128 */     HTMLSelectElement mes1 = this.pagHTML.getElementIdMes1();
/* 129 */     HTMLSelectElement mes2 = this.pagHTML.getElementIdMes2();
/*     */ 
/*     */     
/* 132 */     String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
/*     */     
/* 134 */     for (int mes = 1; mes <= 12; mes++) {
/* 135 */       HTMLOptionElement op2 = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 136 */       op2.setValue(mes + "");
/* 137 */       op2.appendChild(this.pagHTML.createTextNode(meses[mes - 1]));
/* 138 */       mes1.appendChild(op2);
/* 139 */       if (mes == iMes1) {
/* 140 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 141 */         escogida.setValue("on");
/* 142 */         op2.setAttributeNode(escogida);
/*     */       } 
/*     */       
/* 145 */       HTMLOptionElement op3 = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 146 */       op3.setValue(mes + "");
/* 147 */       op3.appendChild(this.pagHTML.createTextNode(meses[mes - 1]));
/* 148 */       mes2.appendChild(op3);
/* 149 */       if (mes == iMes2) {
/* 150 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 151 */         escogida.setValue("on");
/* 152 */         op3.setAttributeNode(escogida);
/*     */       } 
/*     */     } 
/*     */     
/* 156 */     SisUsuariosDAO rsPersona = new SisUsuariosDAO();
/* 157 */     SisUsuariosDTO p = rsPersona.cargarRegistro(idNav);
/*     */     
/* 159 */     int area = 0;
/*     */     try {
/* 161 */       area = Integer.parseInt(comms.request.getParameter("area"));
/*     */     }
/* 163 */     catch (Exception e) {
/* 164 */       area = p.getArea();
/*     */     } 
/*     */     
/* 167 */     HTMLSelectElement combo = this.pagHTML.getElementArea();
/*     */     
/* 169 */     SeguridadDAO rsSeguridad = new SeguridadDAO();
/* 170 */     if (!rsSeguridad.getEstadoConexion()) {
/* 171 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 173 */     boolean gIndiceArea = rsSeguridad.tieneLlave(miGrupo, "oIndiceArea");
/* 174 */     boolean bMostrarTodasAreas = rsSeguridad.tieneLlave(miGrupo, "oMostrarTodasLasAreas");
/* 175 */     rsSeguridad.close();
/*     */     
/* 177 */     Collection arr = null;
/*     */     
/* 179 */     AreasDAO af = new AreasDAO();
/* 180 */     if (bMostrarTodasAreas) {
/* 181 */       arr = af.cargarActivas();
/*     */     }
/* 183 */     else if (gIndiceArea) {
/* 184 */       arr = af.cargarAreasHijas(idNav);
/*     */     } else {
/*     */       
/* 187 */       arr = af.cargarMisAreas(idNav);
/*     */     } 
/* 189 */     af.close();
/*     */     
/* 191 */     Iterator iterator = arr.iterator();
/* 192 */     while (iterator.hasNext()) {
/* 193 */       AreasDTO reg = (AreasDTO)iterator.next();
/* 194 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 195 */       op.setValue("" + reg.getCodigo());
/* 196 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 197 */       combo.appendChild(op);
/*     */       
/* 199 */       if (area == reg.getCodigo()) {
/* 200 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 201 */         escogida.setValue("on");
/* 202 */         op.setAttributeNode(escogida);
/*     */       } 
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
/*     */   private String listar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 217 */     int anno = 0;
/*     */     try {
/* 219 */       anno = Integer.parseInt(comms.request.getParameter("anno"));
/*     */     }
/* 221 */     catch (Exception e) {
/* 222 */       anno = Utilidades.getAnnoActual();
/*     */     } 
/* 224 */     int iMes1 = 0;
/*     */     try {
/* 226 */       iMes1 = Integer.parseInt(comms.request.getParameter("mes1"));
/*     */     }
/* 228 */     catch (Exception e) {
/* 229 */       iMes1 = Utilidades.getMesActual();
/*     */     } 
/* 231 */     int iMes2 = 0;
/*     */     try {
/* 233 */       iMes2 = Integer.parseInt(comms.request.getParameter("mes2"));
/*     */     }
/* 235 */     catch (Exception e) {
/* 236 */       iMes2 = Utilidades.getMesActual();
/*     */     } 
/*     */     
/* 239 */     int area = 0;
/*     */     try {
/* 241 */       area = Integer.parseInt(comms.request.getParameter("area"));
/*     */     }
/* 243 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 246 */     AreasDAO afac = new AreasDAO();
/* 247 */     AreasDTO regArea = afac.getArea(area);
/* 248 */     afac.close();
/*     */ 
/*     */     
/* 251 */     String exportar = comms.request.getParameter("exportar");
/* 252 */     if (exportar == null) exportar = "N";
/*     */ 
/*     */     
/* 255 */     AcumuladosDAO rs = new AcumuladosDAO();
/* 256 */     Collection arr = rs.cargarIndiceSecuencia(regArea.getSecuencia(), anno, iMes1, iMes2);
/* 257 */     rs.close();
/*     */     
/* 259 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/*     */     
/* 261 */     String respuesta = "Area\tMes\tIndice de Oportunidad\tIndice de percepción del Servicio\tIndice de satisfacción";
/* 262 */     boolean fondo = true;
/* 263 */     String areaAnterior = "";
/*     */     
/* 265 */     int oe = 0, ob = 0, or = 0;
/* 266 */     int ce = 0, cb = 0, cr = 0;
/*     */     
/* 268 */     Iterator iterator = arr.iterator();
/* 269 */     int registros = 0;
/* 270 */     while (iterator.hasNext()) {
/* 271 */       IndiceSatisfaccionDTO reg = (IndiceSatisfaccionDTO)iterator.next();
/*     */       
/* 273 */       if (reg.getOportunidadExcelente() + reg.getOportunidadBuena() + reg.getOportunidadRegular() + reg.getConfiabilidadExcelente() + reg.getConfiabilidadBuena() + reg.getConfiabilidadRegular() > 0) {
/*     */ 
/*     */ 
/*     */         
/* 277 */         if (exportar.equals("S")) {
/* 278 */           respuesta = respuesta + "\n" + reg.getNombreArea() + "\t" + reg.getNombreMes() + "\t" + ((reg.getIndiceOportunidad() == 0.0F) ? "N/A" : (Utilidades.miles(Utilidades.round2(reg.getIndiceOportunidad()), 2) + " %")) + "\t" + ((reg.getIndiceConfiabilidad() == 0.0F) ? "N/A" : (Utilidades.miles(Utilidades.round2(reg.getIndiceConfiabilidad()), 2) + " %")) + "\t" + ((reg.getIndiceTotal() == 0.0D) ? "N/A" : (Utilidades.miles(Utilidades.round2(reg.getIndiceTotal()), 2) + " %"));
/*     */ 
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 287 */         HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 288 */         fondo = !fondo;
/* 289 */         eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */         
/* 291 */         if (areaAnterior.equals(reg.getNombreArea())) {
/* 292 */           eltr.appendChild(newtd(".", false));
/*     */         } else {
/*     */           
/* 295 */           eltr.appendChild(newtd("" + reg.getNombreArea(), false));
/*     */         } 
/* 297 */         eltr.appendChild(newtd("" + reg.getNombreMes(), false));
/*     */         
/* 299 */         eltr.appendChild(newtd((reg.getIndiceOportunidad() == 0.0F) ? "N/A" : (Utilidades.miles(Utilidades.round2(reg.getIndiceOportunidad()), 2) + " %"), true));
/* 300 */         eltr.appendChild(newtd((reg.getIndiceConfiabilidad() == 0.0F) ? "N/A" : (Utilidades.miles(Utilidades.round2(reg.getIndiceConfiabilidad()), 2) + " %"), true));
/*     */         
/* 302 */         double indice_total = reg.getIndiceTotal();
/*     */         
/* 304 */         eltr.appendChild(newtd("" + ((indice_total == 0.0D) ? "N/A" : (Utilidades.miles(Utilidades.round2(indice_total), 2) + " %")), true));
/*     */         
/* 306 */         hte.appendChild(eltr);
/* 307 */         areaAnterior = reg.getNombreArea();
/*     */         
/* 309 */         oe += reg.getOportunidadExcelente();
/* 310 */         ob += reg.getOportunidadBuena();
/* 311 */         or += reg.getOportunidadRegular();
/*     */         
/* 313 */         ce += reg.getConfiabilidadExcelente();
/* 314 */         cb += reg.getConfiabilidadBuena();
/* 315 */         cr += reg.getConfiabilidadRegular();
/* 316 */         registros++;
/*     */       } 
/*     */     } 
/* 319 */     HTMLElement eltr2 = (HTMLElement)this.pagHTML.createElement("tr");
/* 320 */     fondo = !fondo;
/*     */ 
/*     */     
/* 323 */     float indiceOportunidad = Utilidades.calcularIndice(oe, ob, or);
/* 324 */     float indiceConfiabilidad = Utilidades.calcularIndice(ce, cb, cr);
/*     */     
/* 326 */     double indice_total = 0.0D;
/* 327 */     if (indiceOportunidad == 0.0F || indiceConfiabilidad == 0.0F) {
/* 328 */       indice_total = (indiceOportunidad + indiceConfiabilidad);
/*     */     } else {
/*     */       
/* 331 */       indice_total = ((indiceOportunidad * ParametrosDTO.getInt("porcentaje.oportunidad") + indiceConfiabilidad * ParametrosDTO.getInt("porcentaje.confiabilidad")) / 100.0F);
/*     */     } 
/*     */     
/* 334 */     eltr2.appendChild(newtd("TOTAL", false, 2));
/* 335 */     eltr2.appendChild(newtd((indiceOportunidad == 0.0F) ? "N/A" : (Utilidades.miles(indiceOportunidad, 2) + " %"), true));
/* 336 */     eltr2.appendChild(newtd((indiceConfiabilidad == 0.0F) ? "N/A" : (Utilidades.miles(indiceConfiabilidad, 2) + " %"), true));
/* 337 */     eltr2.appendChild(newtd("" + ((indice_total == 0.0D) ? "N/A" : (Utilidades.miles(Utilidades.round2(indice_total), 2) + " %")), true));
/* 338 */     hte.appendChild(eltr2);
/*     */     
/* 340 */     if (exportar.equals("S")) {
/* 341 */       return respuesta;
/*     */     }
/* 343 */     return null;
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
/* 356 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 357 */     atrib.setValue(valor);
/* 358 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido, boolean align) {
/* 368 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 369 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 370 */     if (align) {
/* 371 */       td.setAttributeNode(newAttr("class", "ctdr"));
/*     */     } else {
/*     */       
/* 374 */       td.setAttributeNode(newAttr("class", "ctd"));
/*     */     } 
/* 376 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido, boolean align, int colspan) {
/* 386 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 387 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 388 */     if (align) {
/* 389 */       td.setAttributeNode(newAttr("class", "ctdr"));
/*     */     } else {
/*     */       
/* 392 */       td.setAttributeNode(newAttr("class", "ctd"));
/*     */     } 
/* 394 */     td.setAttribute("colspan", "" + colspan);
/* 395 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ReporteIndicePeriodos.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */