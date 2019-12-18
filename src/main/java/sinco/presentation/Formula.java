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
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.FormulaDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.data.FormulaDAO;
/*     */ import sinco.presentation.Formula;
/*     */ import sinco.presentation.FormulaHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Formula
/*     */   implements HttpPresentation
/*     */ {
/*     */   private FormulaHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  33 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  34 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  37 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  38 */     String _operacion = comms.request.getParameter("_operacion");
/*  39 */     if (_operacion == null || _operacion.length() == 0) {
/*  40 */       _operacion = "X";
/*     */     }
/*     */     
/*  43 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  44 */       creacion(comms);
/*     */     }
/*     */     
/*  47 */     this.pagHTML = (FormulaHTML)comms.xmlcFactory.create(FormulaHTML.class);
/*  48 */     permisos(comms);
/*     */     
/*  50 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  51 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  54 */     if (_operacion.equals("P")) {
/*  55 */       editar(comms);
/*     */     }
/*  57 */     else if (_operacion.equals("Nuevo")) {
/*  58 */       nuevo(comms);
/*     */     } 
/*     */     
/*  61 */     if (_operacion.equals("V")) {
/*  62 */       verRegistro(comms);
/*     */     }
/*  64 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  65 */     comms.response.writeDOM(this.pagHTML);
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
/*  77 */     String _operacion = comms.request.getParameter("_operacion");
/*  78 */     String elUsuario = "" + comms.session.getUser().getName();
/*  79 */     int idFormula = 0;
/*     */     try {
/*  81 */       idFormula = Integer.parseInt(comms.request.getParameter("idFormula"));
/*     */     }
/*  83 */     catch (Exception e) {
/*  84 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=idFormula"));
/*     */     } 
/*     */     
/*  87 */     RespuestaBD rta = new RespuestaBD();
/*  88 */     if (_operacion.equals("E")) {
/*  89 */       FormulaDAO rs = new FormulaDAO();
/*  90 */       if (!rs.getEstadoConexion()) {
/*  91 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*  93 */       rta = rs.eliminarRegistro(idFormula);
/*  94 */       if (!rta.isRta()) {
/*  95 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorFormula&p1=" + rta.getMensaje()));
/*     */       }
/*  97 */       rs.close();
/*  98 */       String sPagina = "Formula.po?_operacion=X";
/*  99 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 101 */     String formula = comms.request.getParameter("formula");
/* 102 */     String descripcion = comms.request.getParameter("descripcion");
/* 103 */     String estado = comms.request.getParameter("estado");
/* 104 */     FormulaDAO rs = new FormulaDAO();
/* 105 */     if (!rs.getEstadoConexion()) {
/* 106 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 108 */     if (_operacion.equals("C")) {
/* 109 */       rta = rs.crearRegistro(idFormula, formula, descripcion, estado, elUsuario);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 117 */       rta = rs.modificarRegistro(idFormula, formula, descripcion, estado, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 124 */     rs.close();
/* 125 */     if (!rta.isRta()) {
/* 126 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorFormula&p1=" + rta.getMensaje()));
/*     */     }
/*     */     
/* 129 */     String sPagina = "Formula.po?_operacion=P&idFormula=" + idFormula + "";
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
/* 141 */     int idFormula = 0;
/*     */     try {
/* 143 */       idFormula = Integer.parseInt(comms.request.getParameter("idFormula"));
/*     */     }
/* 145 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 148 */     FormulaDAO rs = new FormulaDAO();
/* 149 */     if (!rs.getEstadoConexion()) {
/* 150 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 152 */     FormulaDTO reg = rs.cargarRegistro(idFormula);
/* 153 */     rs.close();
/* 154 */     if (reg != null) {
/* 155 */       this.pagHTML.getElementIdFormula().setValue("" + reg.getIdFormula());
/* 156 */       this.pagHTML.getElementFormula().setValue("" + reg.getFormula());
/* 157 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 158 */       this.pagHTML.getElementEstado().setValue("" + reg.getEstado());
/* 159 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 160 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 161 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 162 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/*     */       
/* 164 */       this.pagHTML.getElementIdFormula().setReadOnly(true);
/*     */     } 
/* 166 */     this.pagHTML.getElement_operacion().setValue("M");
/* 167 */     activarVista("nuevo");
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
/* 179 */     this.pagHTML.getElement_operacion().setValue("C");
/*     */     try {
/* 181 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/* 182 */       sel.getParentNode().removeChild(sel);
/*     */     }
/* 184 */     catch (Exception e) {}
/*     */     
/* 186 */     activarVista("nuevo");
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
/* 197 */     activarVista("consulta");
/* 198 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 203 */     FormulaDAO rs = new FormulaDAO();
/* 204 */     if (!rs.getEstadoConexion()) {
/* 205 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 207 */     Collection<FormulaDTO> arr = rs.cargarTodos();
/* 208 */     rs.close();
/* 209 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 210 */     int cuantas = 0;
/* 211 */     Iterator<FormulaDTO> iterator = arr.iterator();
/* 212 */     while (iterator.hasNext()) {
/* 213 */       FormulaDTO reg = (FormulaDTO)iterator.next();
/* 214 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 215 */       eltr.appendChild(newtd("" + reg.getIdFormula()));
/* 216 */       String url = "Formula.po?_operacion=V&idFormula=" + reg.getIdFormula() + "";
/* 217 */       eltr.appendChild(newtdhref("" + reg.getFormula(), url));
/* 218 */       eltr.appendChild(newtd("" + reg.getDescripcion()));
/* 219 */       eltr.appendChild(newtd("" + reg.getNombreEstado()));
/* 220 */       hte.appendChild(eltr);
/* 221 */       cuantas++;
/*     */     } 
/* 223 */     arr.clear();
/* 224 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/* 237 */     int idFormula = 0;
/*     */     try {
/* 239 */       idFormula = Integer.parseInt(comms.request.getParameter("idFormula"));
/*     */     }
/* 241 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 244 */     FormulaDAO rs = new FormulaDAO();
/* 245 */     if (!rs.getEstadoConexion()) {
/* 246 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 248 */     FormulaDTO reg = rs.cargarRegistro(idFormula);
/* 249 */     rs.close();
/* 250 */     if (reg != null) {
/* 251 */       this.pagHTML.setTextIdFormulaEd("" + reg.getIdFormula());
/* 252 */       this.pagHTML.setTextFormulaEd("" + reg.getFormula());
/* 253 */       this.pagHTML.setTextDescripcionEd("" + reg.getDescripcion());
/* 254 */       this.pagHTML.setTextEstadoEd("" + reg.getNombreEstado());
/* 255 */       this.pagHTML.setTextFechaInsercionEd("" + reg.getFechaInsercion());
/* 256 */       this.pagHTML.setTextUsuarioInsercionEd("" + reg.getUsuarioInsercion());
/* 257 */       this.pagHTML.setTextFechaModificacionEd("" + reg.getFechaModificacion());
/* 258 */       this.pagHTML.setTextUsuarioModificacionEd("" + reg.getUsuarioModificacion());
/*     */       
/* 260 */       this.pagHTML.getElementIdFormulaKey().setValue("" + reg.getIdFormula());
/* 261 */       this.pagHTML.getElement_operacion().setValue("P");
/*     */     } 
/* 263 */     activarVista("editar");
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
/* 274 */     int miGrupo = Integer.parseInt((String)comms.session.getSessionData().get("miGrupo"));
/*     */     
/* 276 */     Varios oVarios = new Varios();
/* 277 */     boolean oPermisoAct = oVarios.tienePermiso(miGrupo, "Poa_FormulaAct");
/* 278 */     boolean oPermisoDel = oVarios.tienePermiso(miGrupo, "Poa_FormulaDel");
/* 279 */     if (!oPermisoAct) {
/* 280 */       HTMLElement elem = this.pagHTML.getElementBtnCrear();
/* 281 */       elem.getParentNode().removeChild(elem);
/* 282 */       elem = this.pagHTML.getElementBtnGrabar();
/* 283 */       elem.getParentNode().removeChild(elem);
/* 284 */       elem = this.pagHTML.getElementBtnModificar();
/* 285 */       elem.getParentNode().removeChild(elem);
/*     */     } 
/* 287 */     if (!oPermisoDel) {
/* 288 */       HTMLElement elem = this.pagHTML.getElementBtnEliminar();
/* 289 */       elem.getParentNode().removeChild(elem);
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
/* 300 */     if (!vista.equals("nuevo")) {
/* 301 */       HTMLElement sel = this.pagHTML.getElementDivCreacionRegistro();
/* 302 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 304 */     if (!vista.equals("editar")) {
/* 305 */       HTMLElement sel = this.pagHTML.getElementDivEdicion();
/* 306 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/* 308 */     if (!vista.equals("consulta")) {
/* 309 */       HTMLElement sel = this.pagHTML.getElementDivConsulta();
/* 310 */       sel.getParentNode().removeChild(sel);
/* 311 */       sel = this.pagHTML.getElementDivResultados();
/* 312 */       sel.getParentNode().removeChild(sel);
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
/* 326 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 327 */     atrib.setValue(valor);
/* 328 */     return atrib;
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
/* 341 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 342 */     Element enlace = this.pagHTML.createElement("a");
/* 343 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 344 */     enlace.appendChild(hijo);
/* 345 */     Attr donde = this.pagHTML.createAttribute("href");
/* 346 */     donde.setValue(vinculo);
/* 347 */     enlace.setAttributeNode(donde);
/* 348 */     td.appendChild(enlace);
/* 349 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 350 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 360 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 361 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 362 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 363 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\Formula.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */