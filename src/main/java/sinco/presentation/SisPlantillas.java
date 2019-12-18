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
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.SisPlantillasDTO;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.SisPlantillasDAO;
/*     */ import sinco.presentation.SisPlantillas;
/*     */ import sinco.presentation.SisPlantillasHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SisPlantillas
/*     */   implements HttpPresentation
/*     */ {
/*     */   private SisPlantillasHTML pagHTML;
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
/*  53 */     else if (_operacion.equals("Desc")) {
/*  54 */       descargar(comms);
/*     */     } 
/*     */ 
/*     */     
/*  58 */     this.pagHTML = (SisPlantillasHTML)comms.xmlcFactory.create(SisPlantillasHTML.class);
/*     */ 
/*     */     
/*  61 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  62 */       listar(comms, _operacion);
/*     */     
/*     */     }
/*  65 */     else if (_operacion.equals("P")) {
/*  66 */       editar(comms);
/*     */     }
/*  68 */     else if (_operacion.equals("Nuevo")) {
/*  69 */       nuevo(comms);
/*     */     }
/*  71 */     else if (_operacion.equals("Arc")) {
/*  72 */       archivo(comms);
/*     */     
/*     */     }
/*  75 */     else if (_operacion.equals("V")) {
/*  76 */       verRegistro(comms);
/*     */     } 
/*     */     
/*  79 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  80 */     comms.response.writeDOM(this.pagHTML);
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
/*  92 */     String _operacion = comms.request.getParameter("_operacion");
/*  93 */     String elUsuario = "" + comms.session.getUser().getName();
/*  94 */     String codigo = comms.request.getParameter("codigo");
/*  95 */     if (codigo == null) {
/*  96 */       codigo = "";
/*     */     }
/*  98 */     RespuestaBD rta = new RespuestaBD();
/*  99 */     if (_operacion.equals("E")) {
/* 100 */       SisPlantillasDAO ob = new SisPlantillasDAO();
/* 101 */       rta = ob.eliminarRegistro(codigo);
/* 102 */       if (!rta.isRta()) {
/* 103 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSisPlantillas&p1=" + rta.getMensaje()));
/*     */       }
/* 105 */       String sPagina = "SisPlantillas.po?_operacion=X";
/* 106 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 108 */     String descripcion = comms.request.getParameter("descripcion");
/* 109 */     String estado = comms.request.getParameter("estado");
/* 110 */     SisPlantillasDAO ob = new SisPlantillasDAO();
/* 111 */     if (_operacion.equals("C")) {
/* 112 */       rta = ob.crearRegistro(codigo, descripcion, estado, elUsuario);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 119 */       rta = ob.modificarRegistro(codigo, descripcion, estado, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 125 */     if (!rta.isRta()) {
/* 126 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorSisPlantillas&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 129 */     String sPagina = "SisPlantillas.po?_operacion=P&codigo=" + codigo + "";
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
/* 141 */     String codigo = comms.request.getParameter("codigo");
/* 142 */     SisPlantillasDAO ob = new SisPlantillasDAO();
/* 143 */     SisPlantillasDTO reg = ob.cargarRegistro(codigo);
/* 144 */     if (reg != null) {
/* 145 */       this.pagHTML.getElementCodigo().setValue("" + reg.getCodigo());
/* 146 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/*     */       
/* 148 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 149 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 150 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 151 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 152 */       HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 153 */       comboMultivalores(combo, "estado_activo_inactivo", "" + reg.getEstado(), true);
/*     */ 
/*     */       
/* 156 */       this.pagHTML.getElementCodigo().setReadOnly(true);
/*     */     } 
/* 158 */     this.pagHTML.getElement_operacion().setValue("M");
/* 159 */     activarVista("nuevo");
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
/*     */   private void descargar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 173 */     String codigo = comms.request.getParameter("codigo");
/* 174 */     SisPlantillasDAO ob = new SisPlantillasDAO();
/* 175 */     SisPlantillasDTO reg = ob.cargarRegistro(codigo);
/* 176 */     if (reg != null) {
/* 177 */       comms.response.setContentType("application/rtf");
/* 178 */       comms.response.setHeader("Content-Disposition", "inline;filename=" + codigo + ".rtf");
/*     */       
/* 180 */       comms.response.setStatus(200, "Good job");
/*     */       
/* 182 */       HttpPresentationOutputStream out = comms.response.getOutputStream();
/*     */       
/*     */       try {
/* 185 */         out.write(reg.getDocumento().getBytes(), 0, reg.getDocumento().length());
/* 186 */         out.flush();
/*     */         
/*     */         return;
/* 189 */       } catch (Exception e) {
/* 190 */         e.printStackTrace();
/* 191 */         Utilidades.writeError("Descargando archivo ", e);
/* 192 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=" + e.getMessage()));
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
/*     */   private void nuevo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 206 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 208 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 209 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 211 */     catch (Exception e) {}
/*     */     
/* 213 */     activarVista("nuevo");
/* 214 */     HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 215 */     comboMultivalores(combo, "estado_activo_inactivo", "", true);
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
/*     */   private void archivo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 229 */     String codigo = comms.request.getParameter("codigo");
/* 230 */     this.pagHTML.getElementCodigoFileKey().setValue("" + codigo);
/* 231 */     activarVista("archivo");
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
/*     */   private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
/* 244 */     activarVista("consulta");
/* 245 */     String codigo = comms.request.getParameter("codigo");
/* 246 */     String descripcion = comms.request.getParameter("descripcion");
/* 247 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 252 */     SisPlantillasDAO ob = new SisPlantillasDAO();
/* 253 */     Collection<SisPlantillasDTO> arr = ob.cargarTodos(codigo, descripcion);
/*     */ 
/*     */     
/* 256 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 257 */     int cuantas = 0;
/* 258 */     Iterator<SisPlantillasDTO> iterator = arr.iterator();
/* 259 */     while (iterator.hasNext()) {
/* 260 */       SisPlantillasDTO reg = (SisPlantillasDTO)iterator.next();
/* 261 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 262 */       eltr.appendChild(newtd("" + reg.getCodigo()));
/* 263 */       String url = "SisPlantillas.po?_operacion=V&codigo=" + reg.getCodigo() + "";
/* 264 */       eltr.appendChild(newtdhref("" + reg.getDescripcion(), url));
/* 265 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 266 */       hte.appendChild(eltr);
/* 267 */       cuantas++;
/*     */     } 
/* 269 */     arr.clear();
/* 270 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 283 */     String codigo = comms.request.getParameter("codigo");
/* 284 */     SisPlantillasDAO ob = new SisPlantillasDAO();
/* 285 */     SisPlantillasDTO reg = ob.cargarRegistro(codigo);
/* 286 */     if (reg != null) {
/* 287 */       this.pagHTML.setTextCodigoEd("" + reg.getCodigo());
/* 288 */       this.pagHTML.setTextDescripcionEd("" + reg.getDescripcion());
/* 289 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/* 290 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 291 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 292 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 293 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 295 */       this.pagHTML.getElementCodigoKey().setValue("" + reg.getCodigo());
/* 296 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 298 */     activarVista("editar");
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
/* 309 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 311 */     Varios oVarios = new Varios();
/* 312 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "SIF_SisPlantillasAct");
/* 313 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "SIF_SisPlantillasDel");
/* 314 */     if (!oPermisoAct) {
/* 315 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 316 */       elem.getParentNode().removeChild(elem);
/* 317 */       elem = this.pagHTML.getElementBtnGrabar();
/* 318 */       elem.getParentNode().removeChild(elem);
/* 319 */       elem = this.pagHTML.getElementBtnModificar();
/* 320 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 322 */     if (!oPermisoDel) {
/* 323 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 324 */       elem.getParentNode().removeChild(elem);
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
/* 335 */     if (!vista.equals("nuevo")) {
/* 336 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 337 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 339 */     if (!vista.equals("editar")) {
/* 340 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 341 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 343 */     if (!vista.equals("consulta")) {
/* 344 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 345 */       sel.getParentNode().removeChild(sel);
/* 346 */       sel = this.pagHTML.getElementDivResultados();
/* 347 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 349 */     if (!vista.equals("archivo")) {
/* 350 */       HTMLElement sel = this.pagHTML.getElementDivArchivo();
/* 351 */       sel.getParentNode().removeChild(sel);
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
/* 365 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 366 */     atrib.setValue(valor);
/* 367 */     return atrib;
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
/* 380 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 381 */     Element enlace = this.pagHTML.createElement("a");
/* 382 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 383 */     enlace.appendChild(hijo);
/* 384 */     Attr donde = this.pagHTML.createAttribute("href");
/* 385 */     donde.setValue(vinculo);
/* 386 */     enlace.setAttributeNode(donde);
/* 387 */     td.appendChild(enlace);
/* 388 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 389 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 399 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 400 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 401 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 402 */     return td;
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
/* 417 */     SisMultiValoresDAO ob = new SisMultiValoresDAO();
/* 418 */     Collection<SisMultiValoresDTO> arr = ob.cargarTabla(tabla);
/* 419 */     ob.close();
/* 420 */     if (dejarBlanco) {
/* 421 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 422 */       op.setValue("");
/* 423 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 424 */       combo.appendChild(op);
/*     */     } 
/* 426 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 427 */     while (iterator.hasNext()) {
/* 428 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 429 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 430 */       op.setValue("" + reg.getCodigo());
/* 431 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 432 */       if (defecto.equals(reg.getCodigo())) {
/* 433 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 434 */         escogida.setValue("on");
/* 435 */         op.setAttributeNode(escogida);
/*     */       } 
/* 437 */       combo.appendChild(op);
/*     */     } 
/* 439 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SisPlantillas.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */