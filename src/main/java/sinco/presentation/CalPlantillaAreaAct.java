/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.Enumeration;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.CalCiclosDTO;
/*     */ import sinco.business.CalGenericaDTO;
/*     */ import sinco.business.CalPlantillasAreasDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.CalCiclosDAO;
/*     */ import sinco.data.CalGenericaDAO;
/*     */ import sinco.data.CalPlantillasAreasDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.CalPlantillaAreaAct;
/*     */ import sinco.presentation.CalPlantillaAreaActHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalPlantillaAreaAct
/*     */   implements HttpPresentation
/*     */ {
/*     */   private CalPlantillaAreaActHTML pagHTML;
/*  36 */   String idNav = "";
/*  37 */   String elUsuario = "";
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  40 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  41 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  44 */     this.idNav = (String)comms.session.getSessionData().get("miId");
/*  45 */     this.elUsuario = "" + comms.session.getUser().getName();
/*     */ 
/*     */ 
/*     */     
/*  49 */     String _operacion = comms.request.getParameter("_operacion");
/*  50 */     if (_operacion == null || _operacion.length() == 0) {
/*  51 */       _operacion = "FASE1";
/*     */     }
/*     */ 
/*     */     
/*  55 */     if (_operacion.equals("FASE1") || _operacion.equals("RECARGA")) {
/*  56 */       fase1(comms);
/*     */       
/*     */       return;
/*     */     } 
/*  60 */     if (_operacion.equals("SELAREA")) {
/*  61 */       mostrarActuales(comms);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  66 */     if (_operacion.equals("BUSCARNUEVAS")) {
/*  67 */       buscarAreasLibres(comms);
/*     */       
/*     */       return;
/*     */     } 
/*  71 */     if (_operacion.equals("NUEVOS")) {
/*  72 */       agregarNuevasAreas(comms);
/*     */       
/*     */       return;
/*     */     } 
/*  76 */     if (_operacion.equals("ELIMINAR")) {
/*  77 */       eliminarAreas(comms);
/*     */       
/*     */       return;
/*     */     } 
/*  81 */     this.pagHTML = (CalPlantillaAreaActHTML)comms.xmlcFactory.create(CalPlantillaAreaActHTML.class);
/*     */     
/*  83 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(this.idNav));
/*  84 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void fase1(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  90 */     int ciclo = 0;
/*     */     try {
/*  92 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/*  94 */     catch (Exception e) {}
/*     */ 
/*     */ 
/*     */     
/*  98 */     this.pagHTML = (CalPlantillaAreaActHTML)comms.xmlcFactory.create(CalPlantillaAreaActHTML.class);
/*     */     
/* 100 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 101 */     if (!rsTGen.getEstadoConexion()) {
/* 102 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 105 */     HTMLSelectElement combo = this.pagHTML.getElementIdCiclo();
/* 106 */     llenarCombo(rsTGen, combo, "cal_ciclos", "ciclo", "descripcion", "" + ciclo, "estado in('D','A')");
/*     */     
/* 108 */     combo = this.pagHTML.getElementIdPlantilla();
/* 109 */     llenarCombo(rsTGen, combo, "cal_plantillas", "codigo", "descripcion", "", "estado='A'");
/*     */     
/* 111 */     rsTGen.close();
/*     */     
/* 113 */     HTMLElement eltr = this.pagHTML.getElementTrAreas();
/* 114 */     eltr.getParentNode().removeChild(eltr);
/*     */     
/* 116 */     eltr = this.pagHTML.getElementTrNuevo();
/* 117 */     eltr.getParentNode().removeChild(eltr);
/*     */     
/* 119 */     int cuantos = 0;
/* 120 */     if (ciclo != 0) {
/* 121 */       cuantos = cargarPlantillasCiclo(ciclo);
/*     */     }
/* 123 */     if (cuantos == 0) {
/* 124 */       eltr = this.pagHTML.getElementDetPlantillas();
/* 125 */       eltr.getParentNode().removeChild(eltr);
/*     */     } 
/*     */     
/* 128 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + this.idNav));
/* 129 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int cargarPlantillasCiclo(int ciclo) {
/* 137 */     CalPlantillasAreasDAO rs = new CalPlantillasAreasDAO();
/* 138 */     if (!rs.getEstadoConexion()) {
/* 139 */       return -1;
/*     */     }
/* 141 */     HTMLTableElement hte = this.pagHTML.getElementDetPlantillas();
/* 142 */     boolean fondo = true;
/*     */     
/* 144 */     rs.cargarPlantillasCiclo(ciclo);
/*     */     
/* 146 */     CalPlantillasAreasDTO reg = rs.nextPlantilla();
/* 147 */     int cuantos = 0;
/* 148 */     while (reg != null) {
/* 149 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 150 */       fondo = !fondo;
/* 151 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/* 152 */       eltr.appendChild(newtdhref("" + reg.getNombrePlantilla(), "CalPlantillaAreaAct.po?_operacion=SELAREA&ciclo=" + ciclo + "&plantilla=" + reg.getCodigoPlantilla()));
/* 153 */       eltr.appendChild(newtd("" + reg.getNumeroAreas()));
/*     */       
/* 155 */       hte.appendChild(eltr);
/* 156 */       reg = rs.nextPlantilla();
/* 157 */       cuantos++;
/*     */     } 
/* 159 */     rs.close();
/* 160 */     return cuantos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void mostrarActuales(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 169 */     int ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/* 170 */     int plantilla = Integer.parseInt(comms.request.getParameter("plantilla"));
/*     */     
/* 172 */     CalGenericaDAO rsPlantilla = new CalGenericaDAO();
/* 173 */     CalGenericaDTO regPlantilla = rsPlantilla.cargarPlantilla(plantilla);
/* 174 */     rsPlantilla.close();
/*     */     
/* 176 */     this.pagHTML = (CalPlantillaAreaActHTML)comms.xmlcFactory.create(CalPlantillaAreaActHTML.class);
/*     */     
/* 178 */     CalCiclosDAO rsCalCiclos = new CalCiclosDAO();
/* 179 */     if (!rsCalCiclos.getEstadoConexion()) {
/* 180 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 182 */     CalCiclosDTO regCiclo = rsCalCiclos.cargarCalCiclos(ciclo);
/* 183 */     rsCalCiclos.close();
/*     */     
/* 185 */     if (regCiclo != null) {
/* 186 */       this.pagHTML.setTextNombreCicloA(regCiclo.getDescripcion());
/*     */     }
/* 188 */     this.pagHTML.getElementCicloA().setValue("" + ciclo);
/* 189 */     this.pagHTML.getElementPlantillaA().setValue("" + plantilla);
/*     */     
/* 191 */     this.pagHTML.getElementCicloE().setValue("" + ciclo);
/* 192 */     this.pagHTML.getElementPlantillaE().setValue("" + plantilla);
/*     */     
/* 194 */     this.pagHTML.setTextNombrePlantillaA(regPlantilla.getDescripcion());
/*     */     
/* 196 */     HTMLTableElement hte = this.pagHTML.getElementDetalleE();
/* 197 */     boolean fondo = true;
/*     */     
/* 199 */     CalPlantillasAreasDAO rs = new CalPlantillasAreasDAO();
/* 200 */     if (!rs.getEstadoConexion()) {
/* 201 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 204 */     rs.cargarTodos(ciclo, plantilla);
/* 205 */     int cuantas = 0;
/*     */     
/* 207 */     CalPlantillasAreasDTO reg = rs.next();
/* 208 */     while (reg != null) {
/* 209 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 210 */       fondo = !fondo;
/* 211 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/* 212 */       HTMLElement tdMarca = (HTMLElement)this.pagHTML.createElement("td");
/* 213 */       HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
/* 214 */       checkbox.setAttribute("type", "checkbox");
/* 215 */       checkbox.setName("X_" + reg.getCodigoArea());
/* 216 */       tdMarca.appendChild(checkbox);
/* 217 */       eltr.appendChild(tdMarca);
/*     */       
/* 219 */       eltr.appendChild(newtd("" + reg.getNombreArea()));
/* 220 */       eltr.appendChild(newtd(reg.getPermiteEspecificos() ? "SI" : " "));
/*     */       
/* 222 */       hte.appendChild(eltr);
/* 223 */       reg = rs.next();
/* 224 */       cuantas++;
/*     */     } 
/* 226 */     rs.close();
/*     */     
/* 228 */     HTMLElement eltr = this.pagHTML.getElementTrSeleccionar();
/* 229 */     eltr.getParentNode().removeChild(eltr);
/*     */     
/* 231 */     eltr = this.pagHTML.getElementTrNuevo();
/* 232 */     eltr.getParentNode().removeChild(eltr);
/*     */     
/* 234 */     if (cuantas == 0) {
/* 235 */       eltr = this.pagHTML.getElementDetE();
/* 236 */       eltr.getParentNode().removeChild(eltr);
/*     */     } 
/*     */     
/* 239 */     if (regPlantilla.getEstado().equals("I")) {
/* 240 */       HTMLElement elBoton = this.pagHTML.getElementBotonAgr();
/* 241 */       elBoton.getParentNode().removeChild(elBoton);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 249 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + this.idNav));
/* 250 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private void buscarAreasLibres(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 263 */     int ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/* 264 */     int plantilla = Integer.parseInt(comms.request.getParameter("plantilla"));
/* 265 */     String filtro = comms.request.getParameter("filtro");
/*     */     
/* 267 */     this.pagHTML = (CalPlantillaAreaActHTML)comms.xmlcFactory.create(CalPlantillaAreaActHTML.class);
/*     */     
/* 269 */     CalCiclosDAO rsCalCiclos = new CalCiclosDAO();
/* 270 */     if (!rsCalCiclos.getEstadoConexion()) {
/* 271 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 273 */     CalCiclosDTO regCiclo = rsCalCiclos.cargarCalCiclos(ciclo);
/* 274 */     rsCalCiclos.close();
/* 275 */     if (regCiclo != null) {
/* 276 */       this.pagHTML.setTextNombreCicloN(regCiclo.getDescripcion());
/*     */     }
/* 278 */     this.pagHTML.getElementCicloN().setValue("" + ciclo);
/* 279 */     this.pagHTML.getElementPlantillaN().setValue("" + plantilla);
/*     */     
/* 281 */     CalPlantillasAreasDAO rs = new CalPlantillasAreasDAO();
/* 282 */     if (!rs.getEstadoConexion()) {
/* 283 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 286 */     String sNombrePlantilla = rs.getNombrePlantilla(plantilla);
/* 287 */     if (sNombrePlantilla != null) {
/* 288 */       this.pagHTML.setTextNombrePlantillaN(sNombrePlantilla);
/*     */     }
/*     */     
/* 291 */     HTMLTableElement hte = this.pagHTML.getElementTableN();
/* 292 */     boolean fondo = true;
/*     */     
/* 294 */     rs.cargarLibres(ciclo, filtro);
/*     */     
/* 296 */     CalPlantillasAreasDTO reg = rs.next();
/* 297 */     while (reg != null) {
/* 298 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 299 */       fondo = !fondo;
/* 300 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/* 301 */       HTMLElement tdMarca = (HTMLElement)this.pagHTML.createElement("td");
/* 302 */       HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
/* 303 */       checkbox.setAttribute("type", "checkbox");
/* 304 */       checkbox.setName("X_" + reg.getCodigoArea());
/* 305 */       tdMarca.appendChild(checkbox);
/* 306 */       eltr.appendChild(tdMarca);
/*     */       
/* 308 */       eltr.appendChild(newtd("" + reg.getNombreArea()));
/* 309 */       eltr.appendChild(newtd(reg.getPermiteEspecificos() ? "SI" : " "));
/*     */       
/* 311 */       hte.appendChild(eltr);
/* 312 */       reg = rs.next();
/*     */     } 
/* 314 */     rs.close();
/* 315 */     HTMLElement eltr = this.pagHTML.getElementTrSeleccionar();
/* 316 */     eltr.getParentNode().removeChild(eltr);
/*     */     
/* 318 */     eltr = this.pagHTML.getElementTrAreas();
/* 319 */     eltr.getParentNode().removeChild(eltr);
/*     */     
/* 321 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + this.idNav));
/* 322 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private void agregarNuevasAreas(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 335 */     int ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/* 336 */     int plantilla = Integer.parseInt(comms.request.getParameter("plantilla"));
/*     */     
/* 338 */     CalPlantillasAreasDAO rs = new CalPlantillasAreasDAO();
/* 339 */     if (!rs.getEstadoConexion()) {
/* 340 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 343 */     Enumeration enumera = comms.request.getParameterNames();
/*     */     
/* 345 */     while (enumera.hasMoreElements()) {
/* 346 */       String param = (String)enumera.nextElement();
/*     */       
/* 348 */       if (param.substring(0, 2).equals("X_") && !param.equals("_operacion")) {
/* 349 */         int codigoArea = Integer.parseInt(param.substring(2, param.length()));
/*     */         
/* 351 */         CalPlantillasAreasDTO reg = rs.cargarRegistro(ciclo, plantilla, codigoArea);
/* 352 */         if (reg != null) {
/* 353 */           rs.modificarRegistro(ciclo, plantilla, codigoArea, "A", this.elUsuario);
/*     */           continue;
/*     */         } 
/* 356 */         rs.crearRegistro(ciclo, plantilla, codigoArea, "A", this.elUsuario);
/*     */       } 
/*     */     } 
/*     */     
/* 360 */     rs.close();
/*     */     
/* 362 */     String sPagina = "CalPlantillaAreaAct.po?ciclo=" + ciclo + "&plantilla=" + plantilla + "&_operacion=SELAREA";
/* 363 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void eliminarAreas(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 374 */     int ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/* 375 */     int plantilla = Integer.parseInt(comms.request.getParameter("plantilla"));
/*     */     
/* 377 */     CalPlantillasAreasDAO rs = new CalPlantillasAreasDAO();
/* 378 */     if (!rs.getEstadoConexion()) {
/* 379 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 382 */     Enumeration enumera = comms.request.getParameterNames();
/*     */     
/* 384 */     while (enumera.hasMoreElements()) {
/* 385 */       String param = (String)enumera.nextElement();
/*     */       
/* 387 */       if (param.substring(0, 2).equals("X_") && !param.equals("_operacion")) {
/* 388 */         int codigoArea = Integer.parseInt(param.substring(2, param.length()));
/* 389 */         rs.eliminarRegistro(ciclo, plantilla, codigoArea, this.elUsuario);
/*     */       } 
/*     */     } 
/* 392 */     rs.close();
/*     */     
/* 394 */     String sPagina = "CalPlantillaAreaAct.po?ciclo=" + ciclo + "&plantilla=" + plantilla + "&_operacion=SELAREA";
/* 395 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 409 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 410 */     atrib.setValue(valor);
/* 411 */     return atrib;
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
/* 424 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 425 */     Element enlace = this.pagHTML.createElement("a");
/* 426 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 427 */     enlace.appendChild(hijo);
/* 428 */     Attr donde = this.pagHTML.createAttribute("href");
/* 429 */     donde.setValue(vinculo);
/* 430 */     enlace.setAttributeNode(donde);
/* 431 */     td.appendChild(enlace);
/* 432 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 433 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 443 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 444 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 445 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 446 */     return td;
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
/*     */   private void llenarCombo(TGeneralDAO rsTGen, HTMLSelectElement combo, String tabla, String codigo, String descripcion, String defecto, String condicion) {
/* 466 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 467 */     op.setValue("0");
/* 468 */     op.appendChild(this.pagHTML.createTextNode(" "));
/* 469 */     combo.appendChild(op);
/*     */     
/* 471 */     rsTGen.cargarTodos(tabla, codigo, descripcion, condicion);
/*     */     TGeneralDTO RegGeneral;
/* 473 */     while ((RegGeneral = rsTGen.next()) != null) {
/* 474 */       op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 475 */       op.setValue("" + RegGeneral.getCodigo());
/* 476 */       op.appendChild(this.pagHTML.createTextNode(RegGeneral.getDescripcion()));
/* 477 */       if (defecto != null && defecto.equals(RegGeneral.getCodigo())) {
/* 478 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 479 */         escogida.setValue("on");
/* 480 */         op.setAttributeNode(escogida);
/*     */       } 
/* 482 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalPlantillaAreaAct.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */