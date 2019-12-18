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
/*     */ import sinco.business.PoaProcesosDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.data.PoaProcesosDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.PoaProcesos;
/*     */ import sinco.presentation.PoaProcesosHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PoaProcesos
/*     */   implements HttpPresentation
/*     */ {
/*     */   private PoaProcesosHTML pagHTML;
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
/*  51 */     this.pagHTML = (PoaProcesosHTML)comms.xmlcFactory.create(PoaProcesosHTML.class);
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
/*  83 */     int codigoProceso = 0;
/*     */     try {
/*  85 */       codigoProceso = Integer.parseInt(comms.request.getParameter("codigoProceso"));
/*     */     }
/*  87 */     catch (Exception e) {
/*  88 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoProceso"));
/*     */     } 
/*     */     
/*  91 */     RespuestaBD rta = new RespuestaBD();
/*  92 */     if (_operacion.equals("E")) {
/*  93 */       PoaProcesosDAO ob = new PoaProcesosDAO();
/*  94 */       rta = ob.eliminarRegistro(codigoProceso);
/*  95 */       if (!rta.isRta()) {
/*  96 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaProcesos&p1=" + rta.getMensaje()));
/*     */       }
/*  98 */       String sPagina = "PoaProcesos.po?_operacion=X";
/*  99 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 101 */     String descripcion = comms.request.getParameter("descripcion");
/* 102 */     String tipoProceso = comms.request.getParameter("tipoProceso");
/* 103 */     String liderProceso = comms.request.getParameter("liderProceso");
/* 104 */     String estado = comms.request.getParameter("estado");
/* 105 */     PoaProcesosDAO ob = new PoaProcesosDAO();
/* 106 */     if (_operacion.equals("C")) {
/* 107 */       rta = ob.crearRegistro(codigoProceso, descripcion, tipoProceso, liderProceso, estado, elUsuario);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 114 */       codigoProceso = rta.getSecuencia();
/*     */     } else {
/*     */       
/* 117 */       rta = ob.modificarRegistro(codigoProceso, descripcion, tipoProceso, liderProceso, estado, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 125 */     if (!rta.isRta()) {
/* 126 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPoaProcesos&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 129 */     String sPagina = "PoaProcesos.po?_operacion=P&codigoProceso=" + codigoProceso + "";
/* 130 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 141 */     int codigoProceso = 0;
/*     */     try {
/* 143 */       codigoProceso = Integer.parseInt(comms.request.getParameter("codigoProceso"));
/*     */     }
/* 145 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 148 */     PoaProcesosDAO ob = new PoaProcesosDAO();
/* 149 */     PoaProcesosDTO reg = ob.cargarRegistro(codigoProceso);
/* 150 */     if (reg != null) {
/* 151 */       this.pagHTML.getElementCodigoProceso().setValue("" + reg.getCodigoProceso());
/* 152 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 153 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 154 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 155 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 156 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 157 */       HTMLSelectElement combo = this.pagHTML.getElementTipoProceso();
/* 158 */       comboMultivalores(combo, "CAL_TIPO_PROCESO", "" + reg.getTipoProceso(), true);
/*     */       
/* 160 */       combo = this.pagHTML.getElementLiderProceso();
/* 161 */       comboMultivalores(combo, "CAL_LIDER_PROCESO", "" + reg.getLiderProceso(), true);
/*     */       
/* 163 */       combo = this.pagHTML.getElementEstado();
/* 164 */       comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), true);
/*     */ 
/*     */       
/* 167 */       this.pagHTML.getElementCodigoProceso().setReadOnly(true);
/*     */     } 
/* 169 */     this.pagHTML.getElement_operacion().setValue("M");
/* 170 */     activarVista("nuevo");
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
/* 182 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 184 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 185 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 187 */     catch (Exception e) {}
/*     */     
/* 189 */     activarVista("nuevo");
/* 190 */     HTMLSelectElement combo = this.pagHTML.getElementTipoProceso();
/* 191 */     comboMultivalores(combo, "CAL_TIPO_PROCESO", "", true);
/*     */     
/* 193 */     combo = this.pagHTML.getElementLiderProceso();
/* 194 */     comboMultivalores(combo, "CAL_LIDER_PROCESO", "", true);
/*     */     
/* 196 */     combo = this.pagHTML.getElementEstado();
/* 197 */     comboMultivalores(combo, "ESTADO_REGISTRO", "", true);
/*     */     
/* 199 */     this.pagHTML.getElementCodigoProceso().setReadOnly(true);
/* 200 */     this.pagHTML.getElementCodigoProceso().setValue("0");
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
/* 211 */     activarVista("consulta");
/* 212 */     String descripcion = comms.request.getParameter("descripcion");
/* 213 */     if (descripcion == null) {
/* 214 */       descripcion = "";
/*     */     }
/* 216 */     String tipoProceso = comms.request.getParameter("tipoProceso");
/* 217 */     if (tipoProceso == null) {
/* 218 */       tipoProceso = "";
/*     */     }
/* 220 */     HTMLSelectElement combo = this.pagHTML.getElementFtipoProceso();
/* 221 */     comboMultivalores(combo, "CAL_TIPO_PROCESO", "" + tipoProceso, true);
/*     */ 
/*     */     
/* 224 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 229 */     PoaProcesosDAO ob = new PoaProcesosDAO();
/* 230 */     Collection<PoaProcesosDTO> arr = ob.cargarTodos(descripcion, tipoProceso);
/*     */ 
/*     */     
/* 233 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 234 */     int cuantas = 0;
/* 235 */     Iterator<PoaProcesosDTO> iterator = arr.iterator();
/* 236 */     while (iterator.hasNext()) {
/* 237 */       PoaProcesosDTO reg = (PoaProcesosDTO)iterator.next();
/* 238 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 239 */       eltr.appendChild(newtd("" + reg.getCodigoProceso()));
/* 240 */       String url = "PoaProcesos.po?_operacion=V&codigoProceso=" + reg.getCodigoProceso() + "";
/* 241 */       eltr.appendChild(newtdhref("" + reg.getDescripcion(), url));
/* 242 */       eltr.appendChild(newtd("" + reg.getNombreTipoProceso()));
/* 243 */       eltr.appendChild(newtd("" + reg.getNombreLiderProceso()));
/* 244 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 245 */       hte.appendChild(eltr);
/* 246 */       cuantas++;
/*     */     } 
/* 248 */     arr.clear();
/* 249 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 262 */     int codigoProceso = 0;
/*     */     try {
/* 264 */       codigoProceso = Integer.parseInt(comms.request.getParameter("codigoProceso"));
/*     */     }
/* 266 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 269 */     PoaProcesosDAO ob = new PoaProcesosDAO();
/* 270 */     PoaProcesosDTO reg = ob.cargarRegistro(codigoProceso);
/* 271 */     if (reg != null) {
/* 272 */       this.pagHTML.setTextCodigoProcesoEd("" + reg.getCodigoProceso());
/* 273 */       this.pagHTML.setTextDescripcionEd("" + reg.getDescripcion());
/* 274 */       this.pagHTML.setTextTipoProcesoEd("" + reg.getNombreTipoProceso());
/* 275 */       this.pagHTML.setTextLiderProcesoEd("" + reg.getNombreLiderProceso());
/* 276 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/* 277 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 278 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 279 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 280 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 282 */       this.pagHTML.getElementCodigoProcesoKey().setValue("" + reg.getCodigoProceso());
/* 283 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 285 */     activarVista("editar");
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
/* 296 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 298 */     Varios oVarios = new Varios();
/* 299 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Poa_PoaProcesosAct");
/* 300 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Poa_PoaProcesosDel");
/* 301 */     if (!oPermisoAct) {
/* 302 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 303 */       elem.getParentNode().removeChild(elem);
/* 304 */       elem = this.pagHTML.getElementBtnGrabar();
/* 305 */       elem.getParentNode().removeChild(elem);
/* 306 */       elem = this.pagHTML.getElementBtnModificar();
/* 307 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 309 */     if (!oPermisoDel) {
/* 310 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 311 */       elem.getParentNode().removeChild(elem);
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
/* 322 */     if (!vista.equals("nuevo")) {
/* 323 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 324 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 326 */     if (!vista.equals("editar")) {
/* 327 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 328 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 330 */     if (!vista.equals("consulta")) {
/* 331 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 332 */       sel.getParentNode().removeChild(sel);
/* 333 */       sel = this.pagHTML.getElementDivResultados();
/* 334 */       sel.getParentNode().removeChild(sel);
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
/* 348 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 349 */     atrib.setValue(valor);
/* 350 */     return atrib;
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
/* 363 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 364 */     Element enlace = this.pagHTML.createElement("a");
/* 365 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 366 */     enlace.appendChild(hijo);
/* 367 */     Attr donde = this.pagHTML.createAttribute("href");
/* 368 */     donde.setValue(vinculo);
/* 369 */     enlace.setAttributeNode(donde);
/* 370 */     td.appendChild(enlace);
/* 371 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 372 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 382 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 383 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 384 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 385 */     return td;
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
/* 400 */     SisMultiValoresDAO ob = new SisMultiValoresDAO();
/* 401 */     Collection<SisMultiValoresDTO> arr = ob.cargarTabla(tabla);
/* 402 */     ob.close();
/* 403 */     if (dejarBlanco) {
/* 404 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 405 */       op.setValue("");
/* 406 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 407 */       combo.appendChild(op);
/*     */     } 
/* 409 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 410 */     while (iterator.hasNext()) {
/* 411 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 412 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 413 */       op.setValue("" + reg.getCodigo());
/* 414 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 415 */       if (defecto.equals(reg.getCodigo())) {
/* 416 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 417 */         escogida.setValue("on");
/* 418 */         op.setAttributeNode(escogida);
/*     */       } 
/* 420 */       combo.appendChild(op);
/*     */     } 
/* 422 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PoaProcesos.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */