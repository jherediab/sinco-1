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
/*     */ import sinco.business.PoaMaestroDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.PoaMaestroDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.PoaMaestro;
/*     */ import sinco.presentation.PoaMaestroHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PoaMaestro
/*     */   implements HttpPresentation
/*     */ {
/*     */   private PoaMaestroHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  40 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  41 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  44 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  45 */     String _operacion = comms.request.getParameter("_operacion");
/*  46 */     if (_operacion == null || _operacion.length() == 0) {
/*  47 */       _operacion = "X";
/*     */     }
/*     */     
/*  50 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  51 */       creacion(comms);
/*     */     }
/*     */     
/*  54 */     this.pagHTML = (PoaMaestroHTML)comms.xmlcFactory.create(PoaMaestroHTML.class);
/*  55 */     permisos(comms);
/*     */     
/*  57 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  58 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  61 */     if (_operacion.equals("P")) {
/*  62 */       editar(comms);
/*     */     }
/*  64 */     else if (_operacion.equals("Nuevo")) {
/*  65 */       nuevo(comms);
/*     */     } 
/*     */     
/*  68 */     if (_operacion.equals("V")) {
/*  69 */       verRegistro(comms);
/*     */     }
/*  71 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  72 */     comms.response.writeDOM(this.pagHTML);
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
/*  84 */     String _operacion = comms.request.getParameter("_operacion");
/*  85 */     String elUsuario = "" + comms.session.getUser().getName();
/*  86 */     int codigoPoa = 0;
/*     */     try {
/*  88 */       codigoPoa = Integer.parseInt(comms.request.getParameter("codigoPoa"));
/*     */     }
/*  90 */     catch (Exception e) {
/*  91 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoPoa"));
/*     */     } 
/*     */     
/*  94 */     RespuestaBD rta = new RespuestaBD();
/*  95 */     if (_operacion.equals("E")) {
/*  96 */       PoaMaestroDAO rs = new PoaMaestroDAO();
/*  97 */       if (!rs.getEstadoConexion()) {
/*  98 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 100 */       rta = rs.eliminarRegistro(codigoPoa);
/* 101 */       if (!rta.isRta()) {
/* 102 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaMaestro&p1=" + rta.getMensaje()));
/*     */       }
/* 104 */       rs.close();
/* 105 */       String sPagina = "PoaMaestro.po?_operacion=X";
/* 106 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 108 */     int proceso = 0;
/*     */     try {
/* 110 */       proceso = Integer.parseInt(comms.request.getParameter("proceso"));
/*     */     }
/* 112 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 115 */     int area = 0;
/*     */     try {
/* 117 */       area = Integer.parseInt(comms.request.getParameter("area"));
/*     */     }
/* 119 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 122 */     int responsable = 0;
/*     */     try {
/* 124 */       responsable = Integer.parseInt(comms.request.getParameter("responsable"));
/*     */     }
/* 126 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 129 */     int ciclo = 0;
/*     */     try {
/* 131 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 133 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 136 */     String estado = comms.request.getParameter("estado");
/* 137 */     PoaMaestroDAO rs = new PoaMaestroDAO();
/* 138 */     if (!rs.getEstadoConexion()) {
/* 139 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 141 */     if (_operacion.equals("C")) {
/* 142 */       rta = rs.crearRegistro(codigoPoa, proceso, area, responsable, ciclo, estado, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 150 */       codigoPoa = rta.getSecuencia();
/*     */     } else {
/*     */       
/* 153 */       rta = rs.modificarRegistro(codigoPoa, proceso, area, responsable, ciclo, estado, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     rs.close();
/* 163 */     if (!rta.isRta()) {
/* 164 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaMaestro&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 167 */     String sPagina = "PoaMaestro.po?_operacion=P&codigoPoa=" + codigoPoa + "";
/* 168 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 179 */     int codigoPoa = 0;
/*     */     try {
/* 181 */       codigoPoa = Integer.parseInt(comms.request.getParameter("codigoPoa"));
/*     */     }
/* 183 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 186 */     PoaMaestroDAO rs = new PoaMaestroDAO();
/* 187 */     if (!rs.getEstadoConexion()) {
/* 188 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 190 */     PoaMaestroDTO reg = rs.cargarRegistro(codigoPoa);
/* 191 */     rs.close();
/* 192 */     if (reg != null) {
/* 193 */       this.pagHTML.getElementCodigoPoa().setValue("" + reg.getCodigoPoa());
/* 194 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 195 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 196 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 197 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 198 */       HTMLSelectElement combo = this.pagHTML.getElementProceso();
/* 199 */       llenarCombo(combo, "POA_PROCESOS", "CODIGO_PROCESO", "DESCRIPCION", "1=1", "" + reg.getProceso(), false);
/*     */       
/* 201 */       combo = this.pagHTML.getElementArea();
/* 202 */       llenarCombo(combo, "UNIDADES_DEPENDENCIA", "CODIGO", "DESCRIPCION", "1=1", "" + reg.getArea(), false);
/*     */       
/* 204 */       combo = this.pagHTML.getElementResponsable();
/* 205 */       llenarCombo(combo, "SIS_USUARIOS S, SIS_USUARIOS_AREA SA", "S.CODIGO_EMPLEADO", "S.NOMBRES,S.APELLIDOS", "S.codigo_empleado=SA.codigo_empleado AND SA.codigo_area=" + reg.getArea(), "" + reg.getResponsable(), false);
/*     */       
/* 207 */       combo = this.pagHTML.getElementCiclo();
/* 208 */       llenarCombo(combo, "POA_CICLOS", "CODIGO_CICLO", "DESCRIPCION", "1=1", "" + reg.getCiclo(), false);
/*     */       
/* 210 */       combo = this.pagHTML.getElementEstado();
/* 211 */       comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), false);
/*     */ 
/*     */       
/* 214 */       this.pagHTML.getElementCodigoPoa().setReadOnly(true);
/*     */     } 
/* 216 */     this.pagHTML.getElement_operacion().setValue("M");
/* 217 */     activarVista("nuevo");
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
/*     */   private void nuevo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 229 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 231 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 232 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 234 */     catch (Exception e) {}
/*     */     
/* 236 */     activarVista("nuevo");
/* 237 */     HTMLSelectElement combo = this.pagHTML.getElementProceso();
/* 238 */     llenarCombo(combo, "POA_PROCESOS", "CODIGO_PROCESO", "DESCRIPCION", "1=1", "", false);
/*     */     
/* 240 */     combo = this.pagHTML.getElementArea();
/* 241 */     llenarCombo(combo, "UNIDADES_DEPENDENCIA", "CODIGO", "DESCRIPCION", "1=1", "", false);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 246 */     combo = this.pagHTML.getElementCiclo();
/* 247 */     llenarCombo(combo, "POA_CICLOS", "CODIGO_CICLO", "DESCRIPCION", "1=1", "", false);
/*     */     
/* 249 */     combo = this.pagHTML.getElementEstado();
/* 250 */     comboMultivalores(combo, "ESTADO_REGISTRO", "", false);
/*     */     
/* 252 */     this.pagHTML.getElementCodigoPoa().setReadOnly(true);
/* 253 */     this.pagHTML.getElementCodigoPoa().setValue("0");
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
/* 264 */     activarVista("consulta");
/*     */     
/* 266 */     int ciclo = 0;
/*     */     try {
/* 268 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/*     */     }
/* 270 */     catch (Exception e) {
/* 271 */       ciclo = Utilidades.getAnnoActual();
/*     */     } 
/*     */ 
/*     */     
/* 275 */     HTMLSelectElement combo = this.pagHTML.getElementFciclo();
/* 276 */     llenarCombo(combo, "POA_CICLOS", "CODIGO_CICLO", "DESCRIPCION", "1=1", "" + ciclo, false);
/*     */     
/* 278 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 283 */     PoaMaestroDAO rs = new PoaMaestroDAO();
/* 284 */     if (!rs.getEstadoConexion()) {
/* 285 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 287 */     Collection<PoaMaestroDTO> arr = rs.cargarTodos(ciclo);
/* 288 */     rs.close();
/* 289 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 290 */     int cuantas = 0;
/* 291 */     Iterator<PoaMaestroDTO> iterator = arr.iterator();
/* 292 */     while (iterator.hasNext()) {
/* 293 */       PoaMaestroDTO reg = (PoaMaestroDTO)iterator.next();
/* 294 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 295 */       eltr.appendChild(newtd("" + reg.getCodigoPoa()));
/* 296 */       String url = "PoaMaestro.po?_operacion=V&codigoPoa=" + reg.getCodigoPoa() + "";
/* 297 */       eltr.appendChild(newtdhref("" + reg.getNombreProceso(), url));
/* 298 */       eltr.appendChild(newtd("" + reg.getNombreArea()));
/* 299 */       eltr.appendChild(newtd("" + reg.getNombreResponsable()));
/* 300 */       eltr.appendChild(newtd("" + reg.getNombreCiclo()));
/* 301 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 302 */       hte.appendChild(eltr);
/* 303 */       cuantas++;
/*     */     } 
/* 305 */     arr.clear();
/* 306 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/*     */   private void verRegistro(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 319 */     int codigoPoa = 0;
/*     */     try {
/* 321 */       codigoPoa = Integer.parseInt(comms.request.getParameter("codigoPoa"));
/*     */     }
/* 323 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 326 */     PoaMaestroDAO rs = new PoaMaestroDAO();
/* 327 */     if (!rs.getEstadoConexion()) {
/* 328 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 330 */     PoaMaestroDTO reg = rs.cargarRegistro(codigoPoa);
/* 331 */     rs.close();
/* 332 */     if (reg != null) {
/* 333 */       this.pagHTML.setTextCodigoPoaEd("" + reg.getCodigoPoa());
/* 334 */       this.pagHTML.setTextProcesoEd("" + reg.getNombreProceso());
/* 335 */       this.pagHTML.setTextAreaEd("" + reg.getNombreArea());
/* 336 */       this.pagHTML.setTextResponsableEd("" + reg.getNombreResponsable());
/* 337 */       this.pagHTML.setTextCicloEd("" + reg.getNombreCiclo());
/* 338 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/* 339 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 340 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 341 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 342 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 344 */       this.pagHTML.getElementCodigoPoaKey().setValue("" + reg.getCodigoPoa());
/* 345 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 347 */     activarVista("editar");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void permisos(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 358 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 360 */     Varios oVarios = new Varios();
/* 361 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Poa_PoaMaestroAct");
/* 362 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Poa_PoaMaestroDel");
/* 363 */     if (!oPermisoAct) {
/* 364 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 365 */       elem.getParentNode().removeChild(elem);
/* 366 */       elem = this.pagHTML.getElementBtnGrabar();
/* 367 */       elem.getParentNode().removeChild(elem);
/* 368 */       elem = this.pagHTML.getElementBtnModificar();
/* 369 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 371 */     if (!oPermisoDel) {
/* 372 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 373 */       elem.getParentNode().removeChild(elem);
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
/* 384 */     if (!vista.equals("nuevo")) {
/* 385 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 386 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 388 */     if (!vista.equals("editar")) {
/* 389 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 390 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 392 */     if (!vista.equals("consulta")) {
/* 393 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 394 */       sel.getParentNode().removeChild(sel);
/* 395 */       sel = this.pagHTML.getElementDivResultados();
/* 396 */       sel.getParentNode().removeChild(sel);
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
/* 410 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 411 */     atrib.setValue(valor);
/* 412 */     return atrib;
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
/* 425 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 426 */     Element enlace = this.pagHTML.createElement("a");
/* 427 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 428 */     enlace.appendChild(hijo);
/* 429 */     Attr donde = this.pagHTML.createAttribute("href");
/* 430 */     donde.setValue(vinculo);
/* 431 */     enlace.setAttributeNode(donde);
/* 432 */     td.appendChild(enlace);
/* 433 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 434 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 444 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 445 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 446 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 447 */     return td;
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
/* 462 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 463 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 464 */     rs.close();
/* 465 */     if (dejarBlanco) {
/* 466 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 467 */       op.setValue("");
/* 468 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 469 */       combo.appendChild(op);
/*     */     } 
/* 471 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 472 */     while (iterator.hasNext()) {
/* 473 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 474 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 475 */       op.setValue("" + reg.getCodigo());
/* 476 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 477 */       if (defecto.equals(reg.getCodigo())) {
/* 478 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 479 */         escogida.setValue("on");
/* 480 */         op.setAttributeNode(escogida);
/*     */       } 
/* 482 */       combo.appendChild(op);
/*     */     } 
/* 484 */     arr.clear();
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
/* 505 */     if (dejarBlanco) {
/* 506 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 507 */       op.setValue("");
/* 508 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 509 */       combo.appendChild(op);
/*     */     } 
/* 511 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 512 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/* 513 */     rsTGen.close();
/* 514 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 515 */     while (iterator.hasNext()) {
/* 516 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 517 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 518 */       op.setValue("" + regGeneral.getCodigoS());
/* 519 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 520 */       if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
/* 521 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 522 */         escogida.setValue("on");
/* 523 */         op.setAttributeNode(escogida);
/*     */       } 
/* 525 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PoaMaestro.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */