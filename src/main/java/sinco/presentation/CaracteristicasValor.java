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
/*     */ import sinco.business.CaracteristicasDTO;
/*     */ import sinco.business.CaracteristicasValorDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.CaracteristicasDAO;
/*     */ import sinco.data.CaracteristicasValorDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.CaracteristicasValor;
/*     */ import sinco.presentation.CaracteristicasValorHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CaracteristicasValor
/*     */   implements HttpPresentation
/*     */ {
/*     */   private CaracteristicasValorHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  41 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  42 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  45 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  46 */     String _operacion = comms.request.getParameter("_operacion");
/*  47 */     if (_operacion == null || _operacion.length() == 0) {
/*  48 */       _operacion = "L";
/*     */     }
/*     */     
/*  51 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  52 */       creacion(comms);
/*     */     }
/*     */     
/*  55 */     this.pagHTML = (CaracteristicasValorHTML)comms.xmlcFactory.create(CaracteristicasValorHTML.class);
/*  56 */     permisos(comms);
/*     */ 
/*     */     
/*  59 */     int caracteristica = 0;
/*     */     try {
/*  61 */       caracteristica = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/*  63 */     catch (Exception e) {
/*  64 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=caracteristica"));
/*     */     } 
/*     */ 
/*     */     
/*  68 */     CaracteristicasDAO rsx = new CaracteristicasDAO();
/*  69 */     if (!rsx.getEstadoConexion()) {
/*  70 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*  72 */     CaracteristicasDTO car = rsx.cargarRegistro(caracteristica);
/*  73 */     rsx.close();
/*     */ 
/*     */     
/*  76 */     this.pagHTML.getElementCaracteristicaHidden().setValue("" + caracteristica);
/*  77 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  78 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  81 */     if (_operacion.equals("P")) {
/*  82 */       editar(comms, car);
/*     */     }
/*  84 */     else if (_operacion.equals("Nuevo")) {
/*  85 */       nuevo(comms, car);
/*     */     } 
/*  87 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  88 */     comms.response.writeDOM(this.pagHTML);
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
/* 100 */     String _operacion = comms.request.getParameter("_operacion");
/* 101 */     String elUsuario = "" + comms.session.getUser().getName();
/* 102 */     int caracteristica = 0;
/*     */     try {
/* 104 */       caracteristica = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 106 */     catch (Exception e) {
/* 107 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=caracteristica"));
/*     */     } 
/*     */     
/* 110 */     int valor = 0;
/*     */     try {
/* 112 */       valor = Integer.parseInt(comms.request.getParameter("valor"));
/*     */     }
/* 114 */     catch (Exception e) {
/* 115 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=valor"));
/*     */     } 
/*     */     
/* 118 */     RespuestaBD rta = new RespuestaBD();
/* 119 */     if (_operacion.equals("E")) {
/* 120 */       CaracteristicasValorDAO rs = new CaracteristicasValorDAO();
/* 121 */       if (!rs.getEstadoConexion()) {
/* 122 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 124 */       rta = rs.eliminarRegistro(caracteristica, valor);
/*     */       
/* 126 */       if (!rta.isRta()) {
/* 127 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCaracteristicasValor&p1=" + rta.getMensaje()));
/*     */       }
/* 129 */       rs.close();
/* 130 */       String sPagina = "CaracteristicasValor.po?_operacion=L&codigo=" + caracteristica;
/* 131 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 133 */     String descripcion = comms.request.getParameter("descripcion");
/* 134 */     int areaAplica = 0;
/*     */     try {
/* 136 */       areaAplica = Integer.parseInt(comms.request.getParameter("areaAplica"));
/*     */     }
/* 138 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 141 */     int duracion = 0;
/*     */     try {
/* 143 */       duracion = Integer.parseInt(comms.request.getParameter("duracion"));
/*     */     }
/* 145 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 148 */     String unidadMedida = comms.request.getParameter("unidadMedida");
/* 149 */     if (unidadMedida == null) {
/* 150 */       unidadMedida = "";
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 155 */     int valorPadre = 0;
/*     */     try {
/* 157 */       valorPadre = Integer.parseInt(comms.request.getParameter("valorPadre"));
/*     */     }
/* 159 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 162 */     String estado = comms.request.getParameter("estado");
/* 163 */     CaracteristicasValorDAO rs = new CaracteristicasValorDAO();
/* 164 */     if (!rs.getEstadoConexion()) {
/* 165 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 167 */     if (_operacion.equals("C")) {
/* 168 */       rta = rs.crearRegistro(caracteristica, valor, descripcion, areaAplica, duracion, unidadMedida, valorPadre, estado, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 180 */       rta = rs.modificarRegistro(caracteristica, valor, descripcion, areaAplica, duracion, unidadMedida, valorPadre, estado, elUsuario);
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
/* 191 */     rs.close();
/* 192 */     if (!rta.isRta()) {
/* 193 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCaracteristicasValor&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 196 */     String sPagina = "CaracteristicasValor.po?_operacion=L&codigo=" + caracteristica;
/* 197 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/*     */   private void editar(HttpPresentationComms comms, CaracteristicasDTO car) throws HttpPresentationException, KeywordValueException {
/* 209 */     int caracteristica = 0;
/*     */     try {
/* 211 */       caracteristica = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 213 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 216 */     int valor = 0;
/*     */     try {
/* 218 */       valor = Integer.parseInt(comms.request.getParameter("valor"));
/*     */     }
/* 220 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 223 */     CaracteristicasValorDAO rs = new CaracteristicasValorDAO();
/* 224 */     if (!rs.getEstadoConexion()) {
/* 225 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 227 */     CaracteristicasValorDTO reg = rs.cargarRegistro(caracteristica, valor);
/* 228 */     rs.close();
/*     */     
/* 230 */     if (reg != null) {
/* 231 */       this.pagHTML.getElementValor().setValue("" + reg.getValor());
/* 232 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/*     */       
/* 234 */       if (reg.getDuracion() > 0) {
/* 235 */         this.pagHTML.getElementDuracion().setValue("" + reg.getDuracion());
/*     */       }
/* 237 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 238 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 239 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 240 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/*     */       
/* 242 */       HTMLSelectElement combo = this.pagHTML.getElementAreaAplica();
/* 243 */       llenarCombo(combo, "UNIDADES_DEPENDENCIA", "CODIGO", "DESCRIPCION", "estado='A'", "" + reg.getAreaAplica(), true);
/*     */       
/* 245 */       combo = this.pagHTML.getElementUnidadMedida();
/* 246 */       llenarCombo(combo, "sis_unidades_medida", "codigo_unidad", "nombre_unidad", "codigo_grupo=5", "" + reg.getUnidadMedida(), true);
/*     */       
/* 248 */       if (car.getCaracteristicaDepende() > 0) {
/* 249 */         combo = this.pagHTML.getElementValorPadre();
/* 250 */         llenarCombo(combo, "CARACTERISTICAS_VALOR", "CARACTERISTICA", "DESCRIPCION", "caracteristica=" + car.getCaracteristicaDepende(), "" + reg.getValorPadre(), true);
/*     */       } 
/*     */       
/* 253 */       combo = this.pagHTML.getElementEstado();
/* 254 */       comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), true);
/*     */       
/* 256 */       this.pagHTML.getElementValor().setReadOnly(true);
/*     */     } 
/* 258 */     this.pagHTML.getElement_operacion().setValue("M");
/* 259 */     activarVista("nuevo");
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
/*     */   private void nuevo(HttpPresentationComms comms, CaracteristicasDTO car) throws HttpPresentationException, KeywordValueException {
/* 271 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 273 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 274 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 276 */     catch (Exception e) {}
/*     */     
/* 278 */     activarVista("nuevo");
/* 279 */     HTMLSelectElement combo = this.pagHTML.getElementAreaAplica();
/* 280 */     llenarCombo(combo, "UNIDADES_DEPENDENCIA", "CODIGO", "DESCRIPCION", "1=1", "", true);
/*     */     
/* 282 */     combo = this.pagHTML.getElementUnidadMedida();
/* 283 */     llenarCombo(combo, "sis_unidades_medida", "codigo_unidad", "nombre_unidad", "codigo_grupo=5", "", true);
/*     */ 
/*     */     
/* 286 */     combo = this.pagHTML.getElementValorPadre();
/* 287 */     llenarCombo(combo, "CARACTERISTICAS_VALOR", "CARACTERISTICA", "DESCRIPCION", "1=1", "", true);
/*     */     
/* 289 */     combo = this.pagHTML.getElementEstado();
/* 290 */     comboMultivalores(combo, "ESTADO_REGISTRO", "A", true);
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
/* 301 */     activarVista("consulta");
/*     */     
/* 303 */     int caracteristica = 0;
/*     */     try {
/* 305 */       caracteristica = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 307 */     catch (Exception e) {
/* 308 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=caracteristica"));
/*     */     } 
/*     */     
/* 311 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 316 */     CaracteristicasValorDAO rs = new CaracteristicasValorDAO();
/* 317 */     if (!rs.getEstadoConexion()) {
/* 318 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 320 */     Collection<CaracteristicasValorDTO> arr = rs.cargarTodos(caracteristica);
/*     */     
/* 322 */     rs.close();
/* 323 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 324 */     int cuantas = 0;
/* 325 */     Iterator<CaracteristicasValorDTO> iterator = arr.iterator();
/* 326 */     while (iterator.hasNext()) {
/* 327 */       CaracteristicasValorDTO reg = (CaracteristicasValorDTO)iterator.next();
/* 328 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 329 */       eltr.appendChild(newtd("" + reg.getValor()));
/* 330 */       String url = "CaracteristicasValor.po?_operacion=P&codigo=" + reg.getCaracteristica() + "&valor=" + reg.getValor() + "";
/* 331 */       eltr.appendChild(newtdhref("" + reg.getDescripcion(), url));
/* 332 */       eltr.appendChild(newtd("" + reg.getAreaAplica()));
/* 333 */       eltr.appendChild(newtd("" + ((reg.getDuracion() > 0) ? ("" + reg.getDuracion()) : "")));
/* 334 */       eltr.appendChild(newtd("" + reg.getNombreUnidadMedida()));
/* 335 */       eltr.appendChild(newtd("" + reg.getEstado()));
/* 336 */       hte.appendChild(eltr);
/* 337 */       cuantas++;
/*     */     } 
/* 339 */     arr.clear();
/* 340 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/*     */   private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 352 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 354 */     Varios oVarios = new Varios();
/* 355 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Adm_CaracteristicasValorAct");
/* 356 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Adm_CaracteristicasValorDel");
/* 357 */     if (!oPermisoAct) {
/* 358 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 359 */       elem.getParentNode().removeChild(elem);
/* 360 */       elem = this.pagHTML.getElementBtnGrabar();
/* 361 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 363 */     if (!oPermisoDel) {
/* 364 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 365 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void activarVista(String vista) {
/* 376 */     if (!vista.equals("nuevo")) {
/* 377 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 378 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 380 */     if (!vista.equals("consulta")) {
/* 381 */       HTMLElement sel = this.pagHTML.getElementDivResultados();
/* 382 */       sel.getParentNode().removeChild(sel);
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
/* 396 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 397 */     atrib.setValue(valor);
/* 398 */     return atrib;
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
/* 411 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 412 */     Element enlace = this.pagHTML.createElement("a");
/* 413 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 414 */     enlace.appendChild(hijo);
/* 415 */     Attr donde = this.pagHTML.createAttribute("href");
/* 416 */     donde.setValue(vinculo);
/* 417 */     enlace.setAttributeNode(donde);
/* 418 */     td.appendChild(enlace);
/* 419 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 420 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 430 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 431 */     td.appendChild(this.pagHTML.createTextNode(contenido));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void comboMultivalores(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco) {
/* 448 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 449 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 450 */     rs.close();
/* 451 */     if (dejarBlanco) {
/* 452 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 453 */       op.setValue("");
/* 454 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 455 */       combo.appendChild(op);
/*     */     } 
/* 457 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 458 */     while (iterator.hasNext()) {
/* 459 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 460 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 461 */       op.setValue("" + reg.getCodigo());
/* 462 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 463 */       if (defecto.equals(reg.getCodigo())) {
/* 464 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 465 */         escogida.setValue("on");
/* 466 */         op.setAttributeNode(escogida);
/*     */       } 
/* 468 */       combo.appendChild(op);
/*     */     } 
/* 470 */     arr.clear();
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
/*     */   private void llenarCombo(HTMLSelectElement combo, String tabla, String codigo, String descripcion, String condicion, String defecto, boolean dejarBlanco) {
/* 491 */     if (dejarBlanco) {
/* 492 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 493 */       op.setValue("");
/* 494 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 495 */       combo.appendChild(op);
/*     */     } 
/* 497 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 498 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/* 499 */     rsTGen.close();
/* 500 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 501 */     while (iterator.hasNext()) {
/* 502 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 503 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 504 */       op.setValue("" + regGeneral.getCodigoS());
/* 505 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 506 */       if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
/* 507 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 508 */         escogida.setValue("on");
/* 509 */         op.setAttributeNode(escogida);
/*     */       } 
/* 511 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CaracteristicasValor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */