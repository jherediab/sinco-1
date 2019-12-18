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
/*     */ import sinco.business.PrcDetalleProcedimientoDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.data.PrcDetalleProcedimientoDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.PrcDetalleProcedimiento;
/*     */ import sinco.presentation.PrcDetalleProcedimientoHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PrcDetalleProcedimiento
/*     */   implements HttpPresentation
/*     */ {
/*     */   private PrcDetalleProcedimientoHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  37 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  38 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  41 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  42 */     String _operacion = comms.request.getParameter("_operacion");
/*  43 */     if (_operacion == null || _operacion.length() == 0) {
/*  44 */       _operacion = "X";
/*     */     }
/*     */     
/*  47 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  48 */       creacion(comms);
/*     */     }
/*     */     
/*  51 */     this.pagHTML = (PrcDetalleProcedimientoHTML)comms.xmlcFactory.create(PrcDetalleProcedimientoHTML.class);
/*  52 */     permisos(comms);
/*     */     
/*  54 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  55 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  58 */     if (_operacion.equals("P")) {
/*  59 */       editar(comms);
/*     */     }
/*  61 */     else if (_operacion.equals("Nuevo")) {
/*  62 */       nuevo(comms);
/*     */     } 
/*     */     
/*  65 */     if (_operacion.equals("V")) {
/*  66 */       verRegistro(comms);
/*     */     }
/*  68 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  69 */     comms.response.writeDOM(this.pagHTML);
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
/*  81 */     String _operacion = comms.request.getParameter("_operacion");
/*  82 */     String elUsuario = "" + comms.session.getUser().getName();
/*  83 */     int idDetalleProcedimiento = 0;
/*     */     try {
/*  85 */       idDetalleProcedimiento = Integer.parseInt(comms.request.getParameter("idDetalleProcedimiento"));
/*     */     }
/*  87 */     catch (Exception e) {
/*  88 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idDetalleProcedimiento"));
/*     */     } 
/*     */     
/*  91 */     RespuestaBD rta = new RespuestaBD();
/*  92 */     if (_operacion.equals("E")) {
/*  93 */       PrcDetalleProcedimientoDAO ob = new PrcDetalleProcedimientoDAO();
/*  94 */       rta = ob.eliminarRegistro(idDetalleProcedimiento);
/*  95 */       if (!rta.isRta()) {
/*  96 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPrcDetalleProcedimiento&p1=" + rta.getMensaje()));
/*     */       }
/*  98 */       String sPagina = "PrcDetalleProcedimiento.po?_operacion=X";
/*  99 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 101 */     String tipoDimension = comms.request.getParameter("tipoDimension");
/* 102 */     int idProcedimiento = 0;
/*     */     try {
/* 104 */       idProcedimiento = Integer.parseInt(comms.request.getParameter("idProcedimiento"));
/*     */     }
/* 106 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 109 */     String descripcionDetalle = comms.request.getParameter("descripcionDetalle");
/* 110 */     int codigoEmpleado = 0;
/*     */     try {
/* 112 */       codigoEmpleado = Integer.parseInt(comms.request.getParameter("codigoEmpleado"));
/*     */     }
/* 114 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 117 */     String registroDetalle = comms.request.getParameter("registroDetalle");
/* 118 */     String estado = comms.request.getParameter("estado");
/* 119 */     PrcDetalleProcedimientoDAO ob = new PrcDetalleProcedimientoDAO();
/* 120 */     if (_operacion.equals("C")) {
/* 121 */       rta = ob.crearRegistro(idDetalleProcedimiento, tipoDimension, idProcedimiento, descripcionDetalle, codigoEmpleado, registroDetalle, estado, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 130 */       idDetalleProcedimiento = rta.getSecuencia();
/*     */     } else {
/*     */       
/* 133 */       rta = ob.modificarRegistro(idDetalleProcedimiento, tipoDimension, idProcedimiento, descripcionDetalle, codigoEmpleado, registroDetalle, estado, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 143 */     if (!rta.isRta()) {
/* 144 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPrcDetalleProcedimiento&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 147 */     String sPagina = "PrcDetalleProcedimiento.po?_operacion=P&idDetalleProcedimiento=" + idDetalleProcedimiento + "";
/* 148 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 159 */     int idDetalleProcedimiento = 0;
/*     */     try {
/* 161 */       idDetalleProcedimiento = Integer.parseInt(comms.request.getParameter("idDetalleProcedimiento"));
/*     */     }
/* 163 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 166 */     PrcDetalleProcedimientoDAO ob = new PrcDetalleProcedimientoDAO();
/* 167 */     PrcDetalleProcedimientoDTO reg = ob.cargarRegistro(idDetalleProcedimiento);
/* 168 */     if (reg != null) {
/* 169 */       this.pagHTML.getElementIdDetalleProcedimiento().setValue("" + reg.getIdDetalleProcedimiento());
/* 170 */       this.pagHTML.getElementDescripcionDetalle().setValue("" + reg.getDescripcionDetalle());
/* 171 */       this.pagHTML.getElementCodigoEmpleado().setValue("" + reg.getCodigoEmpleado());
/* 172 */       this.pagHTML.getElementRegistroDetalle().setValue("" + reg.getRegistroDetalle());
/* 173 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 174 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 175 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 176 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 177 */       HTMLSelectElement combo = this.pagHTML.getElementTipoDimension();
/* 178 */       comboMultivalores(combo, "TIPO_DIMENSION", "" + reg.getTipoDimension(), true);
/*     */       
/* 180 */       combo = this.pagHTML.getElementIdProcedimiento();
/* 181 */       comboMultivalores(combo, "prc_procedimiento", "" + reg.getIdProcedimiento(), true);
/*     */       
/* 183 */       combo = this.pagHTML.getElementEstado();
/* 184 */       comboMultivalores(combo, "estado_activo_inactivo", "" + reg.getEstado(), true);
/*     */ 
/*     */       
/* 187 */       this.pagHTML.getElementIdDetalleProcedimiento().setReadOnly(true);
/*     */     } 
/* 189 */     this.pagHTML.getElement_operacion().setValue("M");
/* 190 */     activarVista("nuevo");
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
/* 202 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 204 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 205 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 207 */     catch (Exception e) {}
/*     */     
/* 209 */     activarVista("nuevo");
/* 210 */     HTMLSelectElement combo = this.pagHTML.getElementTipoDimension();
/* 211 */     comboMultivalores(combo, "TIPO_DIMENSION", "", true);
/*     */     
/* 213 */     combo = this.pagHTML.getElementIdProcedimiento();
/* 214 */     comboMultivalores(combo, "prc_procedimiento", "", true);
/*     */     
/* 216 */     combo = this.pagHTML.getElementEstado();
/* 217 */     comboMultivalores(combo, "estado_activo_inactivo", "", true);
/*     */     
/* 219 */     this.pagHTML.getElementIdDetalleProcedimiento().setReadOnly(true);
/* 220 */     this.pagHTML.getElementIdDetalleProcedimiento().setValue("0");
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
/* 231 */     activarVista("consulta");
/* 232 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 237 */     PrcDetalleProcedimientoDAO ob = new PrcDetalleProcedimientoDAO();
/* 238 */     Collection<PrcDetalleProcedimientoDTO> arr = ob.cargarTodos(0);
/* 239 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 240 */     int cuantas = 0;
/* 241 */     Iterator<PrcDetalleProcedimientoDTO> iterator = arr.iterator();
/* 242 */     while (iterator.hasNext()) {
/* 243 */       PrcDetalleProcedimientoDTO reg = (PrcDetalleProcedimientoDTO)iterator.next();
/* 244 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 245 */       eltr.appendChild(newtd("" + reg.getIdDetalleProcedimiento()));
/* 246 */       String url = "PrcDetalleProcedimiento.po?_operacion=V&idDetalleProcedimiento=" + reg.getIdDetalleProcedimiento() + "";
/* 247 */       eltr.appendChild(newtdhref("" + reg.getNombreTipoDimension(), url));
/* 248 */       eltr.appendChild(newtd("" + reg.getNombreIdProcedimiento()));
/* 249 */       eltr.appendChild(newtd("" + reg.getDescripcionDetalle()));
/* 250 */       eltr.appendChild(newtd("" + reg.getNombreCodigoEmpleado()));
/* 251 */       eltr.appendChild(newtd("" + reg.getRegistroDetalle()));
/* 252 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 253 */       hte.appendChild(eltr);
/* 254 */       cuantas++;
/*     */     } 
/* 256 */     arr.clear();
/* 257 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 270 */     int idDetalleProcedimiento = 0;
/*     */     try {
/* 272 */       idDetalleProcedimiento = Integer.parseInt(comms.request.getParameter("idDetalleProcedimiento"));
/*     */     }
/* 274 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 277 */     PrcDetalleProcedimientoDAO ob = new PrcDetalleProcedimientoDAO();
/* 278 */     PrcDetalleProcedimientoDTO reg = ob.cargarRegistro(idDetalleProcedimiento);
/* 279 */     if (reg != null) {
/* 280 */       this.pagHTML.setTextIdDetalleProcedimientoEd("" + reg.getIdDetalleProcedimiento());
/* 281 */       this.pagHTML.setTextTipoDimensionEd("" + reg.getNombreTipoDimension());
/* 282 */       this.pagHTML.setTextIdProcedimientoEd("" + reg.getNombreIdProcedimiento());
/* 283 */       this.pagHTML.setTextDescripcionDetalleEd("" + reg.getDescripcionDetalle());
/* 284 */       this.pagHTML.setTextCodigoEmpleadoEd("" + reg.getNombreCodigoEmpleado());
/* 285 */       this.pagHTML.setTextRegistroDetalleEd("" + reg.getRegistroDetalle());
/* 286 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/* 287 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 288 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 289 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/* 290 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/*     */       
/* 292 */       this.pagHTML.getElementIdDetalleProcedimientoKey().setValue("" + reg.getIdDetalleProcedimiento());
/* 293 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 295 */     activarVista("editar");
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
/* 306 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 308 */     Varios oVarios = new Varios();
/* 309 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "admPrcDetalleProcedimientoAct");
/* 310 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "admPrcDetalleProcedimientoDel");
/* 311 */     if (!oPermisoAct) {
/* 312 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 313 */       elem.getParentNode().removeChild(elem);
/* 314 */       elem = this.pagHTML.getElementBtnGrabar();
/* 315 */       elem.getParentNode().removeChild(elem);
/* 316 */       elem = this.pagHTML.getElementBtnModificar();
/* 317 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 319 */     if (!oPermisoDel) {
/* 320 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 321 */       elem.getParentNode().removeChild(elem);
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
/* 332 */     if (!vista.equals("nuevo")) {
/* 333 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 334 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 336 */     if (!vista.equals("editar")) {
/* 337 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 338 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 340 */     if (!vista.equals("consulta")) {
/* 341 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 342 */       sel.getParentNode().removeChild(sel);
/* 343 */       sel = this.pagHTML.getElementDivResultados();
/* 344 */       sel.getParentNode().removeChild(sel);
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
/* 358 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 359 */     atrib.setValue(valor);
/* 360 */     return atrib;
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
/* 373 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 374 */     Element enlace = this.pagHTML.createElement("a");
/* 375 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 376 */     enlace.appendChild(hijo);
/* 377 */     Attr donde = this.pagHTML.createAttribute("href");
/* 378 */     donde.setValue(vinculo);
/* 379 */     enlace.setAttributeNode(donde);
/* 380 */     td.appendChild(enlace);
/* 381 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 382 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 392 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 393 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 394 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 395 */     return td;
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
/* 410 */     SisMultiValoresDAO ob = new SisMultiValoresDAO();
/* 411 */     Collection<SisMultiValoresDTO> arr = ob.cargarTabla(tabla);
/* 412 */     ob.close();
/* 413 */     if (dejarBlanco) {
/* 414 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 415 */       op.setValue("");
/* 416 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 417 */       combo.appendChild(op);
/*     */     } 
/* 419 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 420 */     while (iterator.hasNext()) {
/* 421 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 422 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 423 */       op.setValue("" + reg.getCodigo());
/* 424 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 425 */       if (defecto.equals(reg.getCodigo())) {
/* 426 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 427 */         escogida.setValue("on");
/* 428 */         op.setAttributeNode(escogida);
/*     */       } 
/* 430 */       combo.appendChild(op);
/*     */     } 
/* 432 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PrcDetalleProcedimiento.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */