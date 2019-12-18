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
/*     */ import sinco.business.IndicadorActividadesDTO;
/*     */ import sinco.business.IndicadoresDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.IndicadorActividadesDAO;
/*     */ import sinco.data.IndicadoresDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.IndicadorActividades;
/*     */ import sinco.presentation.IndicadorActividadesHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IndicadorActividades
/*     */   implements HttpPresentation
/*     */ {
/*     */   private IndicadorActividadesHTML pagHTML;
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
/*  54 */     this.pagHTML = (IndicadorActividadesHTML)comms.xmlcFactory.create(IndicadorActividadesHTML.class);
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
/*  86 */     String idIndicador = "";
/*     */     try {
/*  88 */       idIndicador = comms.request.getParameter("idIndicador");
/*     */     }
/*  90 */     catch (Exception e) {
/*  91 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idIndicador"));
/*     */     } 
/*     */     
/*  94 */     String mes = comms.request.getParameter("mes");
/*     */ 
/*     */     
/*  97 */     RespuestaBD rta = new RespuestaBD();
/*  98 */     if (_operacion.equals("E")) {
/*  99 */       IndicadorActividadesDAO rs = new IndicadorActividadesDAO();
/* 100 */       if (!rs.getEstadoConexion()) {
/* 101 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 103 */       rta = rs.eliminarRegistro(idIndicador, mes);
/*     */       
/* 105 */       if (!rta.isRta()) {
/* 106 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorIndicadorActividades&p1=" + rta.getMensaje()));
/*     */       }
/* 108 */       rs.close();
/* 109 */       String sPagina = "IndicadorActividades.po?_operacion=X";
/* 110 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 112 */     int ciclo = 0;
/*     */     try {
/* 114 */       ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/* 115 */     } catch (Exception e) {}
/*     */     
/* 117 */     int porcentaje = 0;
/*     */     try {
/* 119 */       porcentaje = Integer.parseInt(comms.request.getParameter("porcentaje"));
/*     */     }
/* 121 */     catch (Exception e) {}
/*     */ 
/*     */ 
/*     */     
/* 125 */     String retrasosSoluciones = comms.request.getParameter("retrasosSoluciones");
/* 126 */     String avances = comms.request.getParameter("avances");
/* 127 */     String logrosBeneficios = comms.request.getParameter("logrosBeneficios");
/* 128 */     String comentariosAdicionales = comms.request.getParameter("comentariosAdicionales");
/* 129 */     String accionTomada = comms.request.getParameter("accionTomada");
/*     */ 
/*     */     
/* 132 */     String estado = comms.request.getParameter("estado");
/* 133 */     IndicadorActividadesDAO rs = new IndicadorActividadesDAO();
/* 134 */     if (!rs.getEstadoConexion()) {
/* 135 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 137 */     if (_operacion.equals("C")) {
/* 138 */       rta = rs.crearRegistro(idIndicador, mes, porcentaje, ciclo, estado, elUsuario, retrasosSoluciones, avances, logrosBeneficios, comentariosAdicionales, accionTomada);
/*     */ 
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
/*     */       
/* 152 */       rta = rs.modificarRegistro(idIndicador, mes, porcentaje, ciclo, estado, elUsuario, retrasosSoluciones, avances, logrosBeneficios, comentariosAdicionales, accionTomada);
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
/* 165 */     rs.close();
/* 166 */     if (!rta.isRta()) {
/* 167 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorIndicadorActividades&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 170 */     String sPagina = "IndicadorActividades.po?_operacion=P&idIndicador=" + idIndicador + "&mes=" + mes + "";
/* 171 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/*     */   private void consulta(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 183 */     activarVista("consulta");
/* 184 */     HTMLSelectElement combo = this.pagHTML.getElementFindicador();
/* 185 */     llenarCombo(combo, "INDICADORES", "CODIGO_INDICADOR", "NOMBRE_INDICADOR", "1=1", "", true);
/*     */     
/* 187 */     String mes = comms.request.getParameter("fmes");
/* 188 */     combo = this.pagHTML.getElementFmes();
/* 189 */     llenarComboMeses(combo, "" + mes, false);
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
/*     */   private void llenarCombo(HTMLSelectElement combo, String tabla, String codigo, String descripcion, String condicion, String defecto, boolean dejarBlanco) {
/* 203 */     if (dejarBlanco) {
/* 204 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 205 */       op.setValue("");
/* 206 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 207 */       combo.appendChild(op);
/*     */     } 
/* 209 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 210 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/* 211 */     rsTGen.close();
/* 212 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 213 */     while (iterator.hasNext()) {
/* 214 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 215 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 216 */       op.setValue("" + regGeneral.getCodigoS());
/* 217 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 218 */       if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
/* 219 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 220 */         escogida.setValue("on");
/* 221 */         op.setAttributeNode(escogida);
/*     */       } 
/* 223 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getMes(int mes) {
/* 228 */     switch (mes) {
/*     */       case 1:
/* 230 */         return "Enero";
/*     */       case 2:
/* 232 */         return "Febrero";
/*     */       
/*     */       case 3:
/* 235 */         return "Marzo";
/*     */       case 4:
/* 237 */         return "Abril";
/*     */       case 5:
/* 239 */         return "Mayo";
/*     */       case 6:
/* 241 */         return "Junio";
/*     */       case 7:
/* 243 */         return "Julio";
/*     */       case 8:
/* 245 */         return "Agosto";
/*     */       case 9:
/* 247 */         return "Septiembre";
/*     */       case 10:
/* 249 */         return "Octubre";
/*     */       case 11:
/* 251 */         return "Noviembre";
/*     */       case 12:
/* 253 */         return "Diciembre";
/*     */     } 
/*     */ 
/*     */     
/* 257 */     return "";
/*     */   }
/*     */   
/*     */   private String getMesNumero(String mes) {
/* 261 */     if (mes.equals("Enero")) {
/* 262 */       return "mes1";
/*     */     }
/* 264 */     if (mes.equals("Febrero")) {
/* 265 */       return "mes2";
/*     */     }
/* 267 */     if (mes.equals("Marzo")) {
/* 268 */       return "mes3";
/*     */     }
/* 270 */     if (mes.equals("Abril")) {
/* 271 */       return "mes4";
/*     */     }
/* 273 */     if (mes.equals("Mayo")) {
/* 274 */       return "mes5";
/*     */     }
/* 276 */     if (mes.equals("Junio")) {
/* 277 */       return "mes6";
/*     */     }
/* 279 */     if (mes.equals("Julio")) {
/* 280 */       return "mes7";
/*     */     }
/* 282 */     if (mes.equals("Agosto")) {
/* 283 */       return "mes8";
/*     */     }
/* 285 */     if (mes.equals("Septiembre")) {
/* 286 */       return "mes9";
/*     */     }
/* 288 */     if (mes.equals("Octubre")) {
/* 289 */       return "mes10";
/*     */     }
/* 291 */     if (mes.equals("Noviembre")) {
/* 292 */       return "mes11";
/*     */     }
/* 294 */     if (mes.equals("Diciembre")) {
/* 295 */       return "mes12";
/*     */     }
/* 297 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void llenarComboMeses(HTMLSelectElement combo, String defecto, boolean dejarBlanco) {
/* 304 */     if (dejarBlanco) {
/* 305 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 306 */       op.setValue("");
/* 307 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 308 */       combo.appendChild(op);
/*     */     } 
/*     */     
/* 311 */     for (int i = 1; i <= 12; i++) {
/* 312 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 313 */       op.setValue("mes" + i);
/* 314 */       op.appendChild(this.pagHTML.createTextNode(getMes(i)));
/* 315 */       if (defecto.equals("mes" + i)) {
/* 316 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 317 */         escogida.setValue("on");
/* 318 */         op.setAttributeNode(escogida);
/*     */       } 
/* 320 */       combo.appendChild(op);
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
/*     */   private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 339 */     String idIndicador = "";
/*     */     try {
/* 341 */       idIndicador = comms.request.getParameter("idIndicador");
/*     */     }
/* 343 */     catch (Exception e) {}
/*     */ 
/*     */ 
/*     */     
/* 347 */     String mes = comms.request.getParameter("mes");
/*     */     
/* 349 */     IndicadorActividadesDAO rs = new IndicadorActividadesDAO();
/* 350 */     if (!rs.getEstadoConexion()) {
/* 351 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 353 */     IndicadorActividadesDTO reg = rs.cargarRegistro(idIndicador, mes);
/*     */     
/* 355 */     rs.close();
/* 356 */     if (reg != null) {
/* 357 */       this.pagHTML.getElementIdIndicador().setValue("" + reg.getIdIndicador());
/* 358 */       this.pagHTML.getElementMes().setValue("" + reg.getMes());
/* 359 */       this.pagHTML.getElementPorcentaje().setValue("" + reg.getPorcentaje());
/* 360 */       this.pagHTML.getElementCiclo().setValue("" + reg.getCiclo());
/* 361 */       this.pagHTML.getElementEstado().setValue("" + reg.getEstado());
/* 362 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 363 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 364 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 365 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 366 */       this.pagHTML.getElementRetrasosSoluciones().setTextContent("" + reg.getRetrasosSoluciones());
/* 367 */       this.pagHTML.getElementAvances().setTextContent("" + reg.getAvances());
/* 368 */       this.pagHTML.getElementLogrosBeneficios().setTextContent("" + reg.getLogrosBeneficios());
/* 369 */       this.pagHTML.getElementComentariosAdicionales().setTextContent("" + reg.getComentariosAdicionales());
/* 370 */       this.pagHTML.getElementAccionTomada().setValue("" + reg.getAccionTomada());
/*     */       
/* 372 */       this.pagHTML.getElementIdIndicador().setReadOnly(true);
/* 373 */       this.pagHTML.getElementMes().setReadOnly(true);
/*     */     } 
/*     */     
/* 376 */     this.pagHTML.getElement_operacion().setValue("M");
/* 377 */     activarVista("nuevo");
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
/* 389 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 391 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 392 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 394 */     catch (Exception e) {}
/*     */     
/* 396 */     activarVista("nuevo");
/*     */     
/* 398 */     String mes = comms.request.getParameter("mes");
/* 399 */     int indicador = Integer.parseInt(comms.request.getParameter("idIndicador"));
/* 400 */     int ciclo = Integer.parseInt(comms.request.getParameter("ciclo"));
/* 401 */     String estado = "A";
/* 402 */     this.pagHTML.getElementIdIndicador().setValue("" + indicador);
/*     */     
/* 404 */     this.pagHTML.getElementCiclo().setValue("" + ciclo);
/* 405 */     this.pagHTML.getElementEstado().setValue("" + estado);
/* 406 */     String periodo = mes.replace("mes", "");
/* 407 */     this.pagHTML.getElementMes().setValue("" + getMes(Integer.parseInt(periodo)));
/*     */ 
/*     */     
/* 410 */     this.pagHTML.getElementIdIndicador().setReadOnly(true);
/* 411 */     this.pagHTML.getElementMes().setReadOnly(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
/* 421 */     activarVista("consulta");
/* 422 */     int idIndicador = 0;
/*     */     try {
/* 424 */       idIndicador = Integer.parseInt(comms.request.getParameter("findicador"));
/*     */     }
/* 426 */     catch (Exception e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 431 */     String mes = comms.request.getParameter("fmes");
/*     */     
/* 433 */     HTMLSelectElement combo = this.pagHTML.getElementFindicador();
/* 434 */     llenarCombo(combo, "INDICADORES", "CODIGO_INDICADOR", "NOMBRE_INDICADOR", "1=1", "" + idIndicador, false);
/*     */ 
/*     */     
/* 437 */     combo = this.pagHTML.getElementFmes();
/* 438 */     llenarComboMeses(combo, "" + mes, false);
/*     */ 
/*     */     
/* 441 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 453 */       HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/*     */       
/* 455 */       IndicadoresDAO reg = new IndicadoresDAO();
/* 456 */       String periodo = mes.replace("mes", "");
/* 457 */       IndicadoresDTO ind = reg.cargarRegistro(Integer.parseInt(comms.request.getParameter("findicador")), getMes(Integer.parseInt(periodo)));
/* 458 */       int ciclo = ind.getCiclo();
/*     */       
/* 460 */       IndicadorActividadesDAO rs1 = new IndicadorActividadesDAO();
/*     */       
/* 462 */       IndicadorActividadesDTO indicador = rs1.cargarRegistro(ind.getCodigoIndicador(), getMes(Integer.parseInt(periodo)));
/* 463 */       rs1.close();
/* 464 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 465 */       String url = "IndicadorActividades.po?_operacion=Nuevo&idIndicador=" + ind.getCodigoIndicador() + "&mes=" + mes + "&ciclo=" + ciclo;
/* 466 */       eltr.appendChild(newtd("" + getMes(Integer.parseInt(periodo))));
/* 467 */       if (indicador != null) {
/* 468 */         url = "IndicadorActividades.po?_operacion=P&idIndicador=" + indicador.getIdIndicador() + "&mes=" + indicador.getMes();
/*     */       }
/* 470 */       eltr.appendChild(newtdhref("" + ciclo, url));
/* 471 */       if (indicador != null) {
/* 472 */         eltr.appendChild(newtd("" + indicador.getPorcentaje()));
/* 473 */         eltr.appendChild(newtd("" + indicador.getFechaInsercion()));
/* 474 */         eltr.appendChild(newtd("" + indicador.getFechaModificacion()));
/*     */       } 
/* 476 */       hte.appendChild(eltr);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 481 */     catch (Exception e) {}
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
/*     */   private void verRegistro(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 497 */     String idIndicador = "";
/*     */     try {
/* 499 */       idIndicador = comms.request.getParameter("idIndicador");
/*     */     }
/* 501 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 504 */     String mes = comms.request.getParameter("mes");
/*     */ 
/*     */     
/* 507 */     IndicadorActividadesDAO rs = new IndicadorActividadesDAO();
/* 508 */     if (!rs.getEstadoConexion()) {
/* 509 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 511 */     IndicadorActividadesDTO reg = rs.cargarRegistro(idIndicador, mes);
/*     */     
/* 513 */     rs.close();
/* 514 */     if (reg != null) {
/* 515 */       this.pagHTML.setTextIdIndicadorEd("" + reg.getIdIndicador());
/* 516 */       this.pagHTML.setTextMesEd("" + reg.getMes());
/* 517 */       this.pagHTML.setTextPorcentajeEd("" + reg.getPorcentaje());
/* 518 */       this.pagHTML.setTextCicloEd("" + reg.getCiclo());
/* 519 */       this.pagHTML.setTextEstadoEd("" + reg.getEstado());
/* 520 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 521 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 522 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 523 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/* 524 */       this.pagHTML.setTextRetrasosSolucionesEd("" + reg.getRetrasosSoluciones());
/* 525 */       this.pagHTML.setTextAvancesEd("" + reg.getAvances());
/* 526 */       this.pagHTML.setTextLogrosBeneficiosEd("" + reg.getLogrosBeneficios());
/* 527 */       this.pagHTML.setTextComentariosAdicionalesEd("" + reg.getComentariosAdicionales());
/* 528 */       this.pagHTML.setTextAccionTomadaEd("" + reg.getAccionTomada());
/*     */       
/* 530 */       this.pagHTML.getElementIdIndicadorKey().setValue("" + reg.getIdIndicador());
/*     */       
/* 532 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 534 */     activarVista("editar");
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
/* 545 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 547 */     Varios oVarios = new Varios();
/* 548 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Poa_IndicadorActividadesAct");
/* 549 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Poa_IndicadorActividadesDel");
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
/*     */   private void activarVista(String vista) {
/* 571 */     if (!vista.equals("nuevo")) {
/* 572 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 573 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 575 */     if (!vista.equals("editar")) {
/* 576 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 577 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 579 */     if (!vista.equals("consulta")) {
/* 580 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 581 */       sel.getParentNode().removeChild(sel);
/* 582 */       sel = this.pagHTML.getElementDivResultados();
/* 583 */       sel.getParentNode().removeChild(sel);
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
/* 597 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 598 */     atrib.setValue(valor);
/* 599 */     return atrib;
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
/* 612 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 613 */     Element enlace = this.pagHTML.createElement("a");
/* 614 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 615 */     enlace.appendChild(hijo);
/* 616 */     Attr donde = this.pagHTML.createAttribute("href");
/* 617 */     donde.setValue(vinculo);
/* 618 */     enlace.setAttributeNode(donde);
/* 619 */     td.appendChild(enlace);
/* 620 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 621 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 631 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 632 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 633 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 634 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\IndicadorActividades.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */