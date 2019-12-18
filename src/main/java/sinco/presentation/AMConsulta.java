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
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.AMAccionesDTO;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.AMAccionesDAO;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.SeguridadDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.AMConsulta;
/*     */ import sinco.presentation.AMConsultaHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AMConsulta
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AMConsultaHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  44 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  45 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  47 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  49 */     String _operacion = comms.request.getParameter("_operacion");
/*  50 */     if (_operacion == null || _operacion.length() == 0) {
/*  51 */       _operacion = "X";
/*     */     }
/*     */     
/*  54 */     this.pagHTML = (AMConsultaHTML)comms.xmlcFactory.create(AMConsultaHTML.class);
/*     */     
/*  56 */     listar(comms, _operacion);
/*     */     
/*  58 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  59 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private void nuevo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  74 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*  75 */     int miArea = Integer.parseInt((String)comms.session.getSessionData().get("miArea"));
/*     */ 
/*     */     
/*  78 */     SeguridadDAO rsSeguridad = new SeguridadDAO();
/*  79 */     if (!rsSeguridad.getEstadoConexion()) {
/*  80 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*  82 */     boolean bMostrarAreas = rsSeguridad.tieneLlave(miGrupo, "oMostrarTodasLasAreas");
/*  83 */     rsSeguridad.close();
/*     */ 
/*     */     
/*  86 */     int areaImplanta = 0;
/*     */     try {
/*  88 */       areaImplanta = Integer.parseInt(comms.request.getParameter("areaImplanta"));
/*     */     }
/*  90 */     catch (Exception e) {
/*  91 */       areaImplanta = miArea;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  99 */     int codigoEstado = -1;
/*     */     try {
/* 101 */       codigoEstado = Integer.parseInt(comms.request.getParameter("codigoEstado"));
/*     */     }
/* 103 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 106 */     String tema = comms.request.getParameter("tema");
/*     */     
/* 108 */     String fechaDesde = comms.request.getParameter("fechaDesde");
/* 109 */     String fechaHasta = comms.request.getParameter("fechaHasta");
/* 110 */     String accion = comms.request.getParameter("accion");
/* 111 */     String origen = comms.request.getParameter("origen");
/* 112 */     String proceso = comms.request.getParameter("proceso");
/* 113 */     String numeral = comms.request.getParameter("numeral");
/* 114 */     String norma = comms.request.getParameter("norma");
/* 115 */     String implantada = comms.request.getParameter("implantada");
/* 116 */     String impacto = comms.request.getParameter("impacto");
/* 117 */     if (impacto == null) impacto = "";
/*     */     
/* 119 */     if (impacto.equals("")) impacto = "A";
/*     */     
/* 121 */     if (fechaDesde != null) {
/* 122 */       this.pagHTML.getElementFechaDesde().setValue("" + fechaDesde);
/*     */     } else {
/*     */       
/* 125 */       this.pagHTML.getElementFechaDesde().setValue("" + Utilidades.getAnnoActual() + "-01-01");
/*     */     } 
/*     */     
/* 128 */     if (fechaHasta != null) {
/* 129 */       this.pagHTML.getElementFechaHasta().setValue("" + fechaHasta);
/*     */     } else {
/*     */       
/* 132 */       this.pagHTML.getElementFechaHasta().setValue("" + Utilidades.darFormatoFecha(Utilidades.fechaActual()));
/*     */     } 
/*     */     
/* 135 */     HTMLSelectElement combo = this.pagHTML.getElementIdAreaImplanta();
/* 136 */     comboAreas(combo, areaImplanta, bMostrarAreas);
/*     */     
/* 138 */     TGeneralDAO rsTGen = new TGeneralDAO();
/*     */     
/* 140 */     if (!rsTGen.getEstadoConexion()) {
/* 141 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 144 */     combo = this.pagHTML.getElementCodigoEstado();
/* 145 */     llenarCombo(rsTGen, combo, "am_estados", "codigo", "descripcion", "" + codigoEstado);
/*     */     
/* 147 */     combo = this.pagHTML.getElementAccion();
/* 148 */     llenarCombo(rsTGen, combo, "am_tipo", "codigo", "descripcion", "" + accion);
/*     */     
/* 150 */     combo = this.pagHTML.getElementOrigen();
/* 151 */     llenarCombo(rsTGen, combo, "am_origen", "codigo", "descripcion", "" + origen);
/*     */     
/* 153 */     combo = this.pagHTML.getElementProceso();
/* 154 */     llenarCombo(rsTGen, combo, "procesos", "codigo", "descripcion", "" + proceso);
/*     */     
/* 156 */     combo = this.pagHTML.getElementNorma();
/* 157 */     llenarCombo(rsTGen, combo, "am_normas", "norma", "descripcion", "" + norma);
/*     */     
/* 159 */     combo = this.pagHTML.getElementImplantada();
/* 160 */     comboMultivalores(combo, "CONS_SI_NO", "" + implantada, true);
/* 161 */     rsTGen.close();
/*     */     
/* 163 */     if (numeral != null) {
/* 164 */       this.pagHTML.getElementNumeral().setValue("" + numeral);
/*     */     }
/*     */     
/* 167 */     if (tema != null) {
/* 168 */       this.pagHTML.getElementTema().setValue("" + tema);
/*     */     }
/*     */     
/* 171 */     combo = this.pagHTML.getElementImpacto();
/* 172 */     comboMultivalores(combo, "impacto_acciones", "" + impacto, false);
/*     */     
/* 174 */     if (miGrupo != 0) {
/* 175 */       HTMLInputElement checkbox = this.pagHTML.getElementCheckBox();
/* 176 */       checkbox.setChecked(true);
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
/*     */   private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
/* 192 */     nuevo(comms);
/*     */     
/* 194 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */     
/* 198 */     int miArea = Integer.parseInt((String)comms.session.getSessionData().get("miArea"));
/* 199 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/* 201 */     int areaImplanta = 0;
/*     */     try {
/* 203 */       areaImplanta = Integer.parseInt(comms.request.getParameter("areaImplanta"));
/*     */     }
/* 205 */     catch (Exception e) {
/* 206 */       areaImplanta = miArea;
/*     */     } 
/*     */     
/* 209 */     int limite = 0;
/*     */     try {
/* 211 */       limite = Integer.parseInt(comms.request.getParameter("limite"));
/*     */     }
/* 213 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 216 */     int codigoEstado = -1;
/*     */     try {
/* 218 */       codigoEstado = Integer.parseInt(comms.request.getParameter("codigoEstado"));
/*     */     }
/* 220 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 223 */     String tema = comms.request.getParameter("tema");
/* 224 */     if (tema == null) {
/* 225 */       tema = "";
/*     */     }
/*     */     
/* 228 */     String fechaDesde = comms.request.getParameter("fechaDesde");
/* 229 */     String fechaHasta = comms.request.getParameter("fechaHasta");
/*     */     
/* 231 */     String accion = comms.request.getParameter("accion");
/* 232 */     if (accion == null) accion = "";
/*     */     
/* 234 */     String origen = comms.request.getParameter("origen");
/* 235 */     if (origen == null) origen = "";
/*     */     
/* 237 */     String proceso = comms.request.getParameter("proceso");
/* 238 */     if (proceso == null) proceso = "";
/*     */     
/* 240 */     String numeral = comms.request.getParameter("numeral");
/* 241 */     if (numeral == null) numeral = "";
/*     */     
/* 243 */     String norma = comms.request.getParameter("norma");
/* 244 */     if (norma == null) norma = "";
/*     */     
/* 246 */     String cumplio = "";
/* 247 */     String implantada = comms.request.getParameter("implantada");
/* 248 */     if (implantada == null) implantada = "";
/*     */     
/* 250 */     String impacto = comms.request.getParameter("impacto");
/* 251 */     if (impacto == null) impacto = "";
/*     */     
/* 253 */     int areasHijas = 0;
/*     */     try {
/* 255 */       areasHijas = Integer.parseInt(comms.request.getParameter("areasHijas"));
/*     */     }
/* 257 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 260 */     AreasDAO areaf = new AreasDAO();
/* 261 */     AreasDTO regArea = areaf.getArea(areaImplanta);
/* 262 */     areaf.close();
/*     */     
/* 264 */     SisUsuariosDAO ps = new SisUsuariosDAO();
/* 265 */     SisUsuariosDTO jefeArea = ps.getJefeArea(miArea);
/*     */ 
/*     */ 
/*     */     
/* 269 */     AMAccionesDAO rsAcciones = new AMAccionesDAO();
/* 270 */     Collection arr = rsAcciones.cargarTodos(idNav, miArea, (jefeArea != null) ? jefeArea.getCodigoEmpleado() : 0, areaImplanta, codigoEstado, fechaDesde, fechaHasta, accion, origen, proceso, norma, numeral, implantada, limite, areasHijas, regArea.getSecuencia(), tema, impacto, cumplio);
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
/* 292 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 293 */     int iNumero = 0;
/*     */     
/* 295 */     Iterator iterator = arr.iterator();
/* 296 */     while (iterator.hasNext()) {
/* 297 */       AMAccionesDTO reg = (AMAccionesDTO)iterator.next();
/* 298 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/* 300 */       String pagina = "AMDetalleV2.po?numero=" + reg.getNumero();
/* 301 */       eltr.appendChild(newtdhref("" + reg.getNumero(), pagina));
/* 302 */       eltr.appendChild(newtd("" + reg.getNombreAreaImplanta()));
/* 303 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 304 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(reg.getFechaVigencia())));
/* 305 */       eltr.appendChild(newtd("" + reg.getTipoAccion()));
/* 306 */       eltr.appendChild(newtd("" + reg.getOrigenAccion()));
/* 307 */       eltr.appendChild(newtd("" + reg.getNombreProceso()));
/* 308 */       eltr.appendChild(newtd("" + reg.getNombreImpacto()));
/* 309 */       eltr.appendChild(newtd("" + reg.getImplantada()));
/* 310 */       eltr.appendChild(newtd("" + reg.getNumeral()));
/* 311 */       eltr.appendChild(newtd("" + reg.getTemaAccion()));
/* 312 */       hte.appendChild(eltr);
/* 313 */       iNumero++;
/*     */     } 
/* 315 */     this.pagHTML.setTextNroRegistros("" + iNumero);
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
/* 328 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 329 */     atrib.setValue(valor);
/* 330 */     return atrib;
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
/* 342 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 343 */     Element enlace = this.pagHTML.createElement("a");
/* 344 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 345 */     enlace.appendChild(hijo);
/* 346 */     Attr donde = this.pagHTML.createAttribute("href");
/* 347 */     donde.setValue(vinculo);
/* 348 */     enlace.setAttributeNode(donde);
/* 349 */     td.appendChild(enlace);
/* 350 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 351 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 361 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 362 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 363 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 364 */     return td;
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
/*     */   private void llenarCombo(TGeneralDAO rsTGen, HTMLSelectElement combo, String tabla, String codigo, String descripcion, String defecto) {
/* 392 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 393 */     op.setValue("");
/* 394 */     op.appendChild(this.pagHTML.createTextNode(""));
/* 395 */     combo.appendChild(op);
/*     */     
/* 397 */     rsTGen.cargarTodos(tabla, codigo, descripcion, "1=1 order by " + descripcion);
/*     */     TGeneralDTO reg;
/* 399 */     while ((reg = rsTGen.next()) != null) {
/* 400 */       op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 401 */       op.setValue("" + reg.getCodigo());
/* 402 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/*     */       
/* 404 */       if (defecto != null && defecto.equals(reg.getCodigo())) {
/* 405 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 406 */         escogida.setValue("on");
/* 407 */         op.setAttributeNode(escogida);
/*     */       } 
/*     */       
/* 410 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void llenarComboSiNo(HTMLSelectElement combo, String defecto) {
/* 420 */     HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 421 */     op.setValue("");
/* 422 */     op.appendChild(this.pagHTML.createTextNode(" "));
/* 423 */     combo.appendChild(op);
/*     */     
/* 425 */     op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 426 */     op.setValue("S");
/* 427 */     op.appendChild(this.pagHTML.createTextNode("SI"));
/* 428 */     combo.appendChild(op);
/*     */     
/* 430 */     op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 431 */     op.setValue("N");
/* 432 */     op.appendChild(this.pagHTML.createTextNode("NO"));
/* 433 */     combo.appendChild(op);
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
/*     */   private void comboAreas(HTMLSelectElement combo, int area1, boolean bMostrarAreas) {
/* 445 */     Collection arr = new ArrayList();
/*     */     
/* 447 */     AreasDAO af = new AreasDAO();
/* 448 */     if (bMostrarAreas) {
/* 449 */       arr = af.cargarTodos();
/*     */     } else {
/*     */       
/* 452 */       arr = af.cargarSecuencia(area1);
/*     */     } 
/* 454 */     af.close();
/*     */     
/* 456 */     Iterator iterator = arr.iterator();
/* 457 */     while (iterator.hasNext()) {
/* 458 */       AreasDTO area = (AreasDTO)iterator.next();
/* 459 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 460 */       op.setValue("" + area.getCodigo());
/* 461 */       op.appendChild(this.pagHTML.createTextNode(area.getDescripcion()));
/*     */       
/* 463 */       if (area1 == area.getCodigo()) {
/* 464 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 465 */         escogida.setValue("on");
/* 466 */         op.setAttributeNode(escogida);
/*     */       } 
/*     */       
/* 469 */       combo.appendChild(op);
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
/*     */   private void comboMultivalores(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco) {
/* 489 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 490 */     Collection arr = rs.cargarTabla(tabla);
/* 491 */     rs.close();
/*     */     
/* 493 */     if (dejarBlanco) {
/* 494 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 495 */       op.setValue("");
/* 496 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 497 */       combo.appendChild(op);
/*     */     } 
/* 499 */     Iterator iterator = arr.iterator();
/* 500 */     while (iterator.hasNext()) {
/* 501 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 502 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 503 */       op.setValue("" + reg.getCodigo());
/* 504 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 505 */       if (defecto.equals(reg.getCodigo())) {
/* 506 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 507 */         escogida.setValue("on");
/* 508 */         op.setAttributeNode(escogida);
/*     */       } 
/* 510 */       combo.appendChild(op);
/*     */     } 
/* 512 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AMConsulta.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */