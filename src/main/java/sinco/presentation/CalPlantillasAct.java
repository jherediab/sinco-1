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
/*     */ import sinco.business.CalPlantillasDTO;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.data.CalPlantillasDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.CalPlantillasAct;
/*     */ import sinco.presentation.CalPlantillasActHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalPlantillasAct
/*     */   implements HttpPresentation
/*     */ {
/*     */   private CalPlantillasActHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  35 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  36 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  39 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*     */     
/*  41 */     String _operacion = comms.request.getParameter("_operacion");
/*  42 */     if (_operacion == null || _operacion.length() == 0) {
/*  43 */       _operacion = "X";
/*     */     }
/*     */     
/*  46 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  47 */       creacion(comms);
/*     */     }
/*  49 */     this.pagHTML = (CalPlantillasActHTML)comms.xmlcFactory.create(CalPlantillasActHTML.class);
/*  50 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  51 */       listar(comms, _operacion);
/*     */     
/*     */     }
/*  54 */     else if (_operacion.equals("P")) {
/*  55 */       editar(comms);
/*     */     }
/*  57 */     else if (_operacion.equals("Nuevo")) {
/*  58 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/*  59 */       sel.getParentNode().removeChild(sel);
/*  60 */       nuevo(comms);
/*     */     } 
/*  62 */     if (_operacion.equals("P") || _operacion.equals("Nuevo")) {
/*  63 */       HTMLElement sel = this.pagHTML.getElementTrConsulta();
/*  64 */       sel.getParentNode().removeChild(sel);
/*  65 */       sel = this.pagHTML.getElementTrResultados();
/*  66 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/*  68 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  69 */       HTMLElement sel = this.pagHTML.getElementTrCreacionRegistro();
/*  70 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/*  72 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  73 */     comms.response.writeDOM(this.pagHTML);
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
/*  85 */     String _operacion = comms.request.getParameter("_operacion");
/*  86 */     String elUsuario = "" + comms.session.getUser().getName();
/*  87 */     int codigo = 0;
/*     */     try {
/*  89 */       codigo = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/*  91 */     catch (Exception e) {}
/*     */ 
/*     */     
/*  94 */     boolean rta = false;
/*  95 */     if (_operacion.equals("E")) {
/*  96 */       CalPlantillasDAO rs = new CalPlantillasDAO();
/*  97 */       if (!rs.getEstadoConexion()) {
/*  98 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 100 */       rta = rs.eliminarRegistro(codigo);
/* 101 */       if (!rta) {
/* 102 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCalPlantillas"));
/*     */       }
/* 104 */       rs.close();
/* 105 */       String sPagina = "CalPlantillasAct.po?_operacion=X";
/* 106 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 108 */     String descripcion = comms.request.getParameter("descripcion");
/* 109 */     String estado = comms.request.getParameter("estado");
/*     */     
/* 111 */     int plantillaAnterior = 0;
/*     */     try {
/* 113 */       plantillaAnterior = Integer.parseInt(comms.request.getParameter("plantillaAnterior"));
/*     */     }
/* 115 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 118 */     CalPlantillasDAO rs = new CalPlantillasDAO();
/* 119 */     if (!rs.getEstadoConexion()) {
/* 120 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 122 */     rta = false;
/* 123 */     if (_operacion.equals("C")) {
/* 124 */       rta = rs.crearRegistro(codigo, descripcion, estado, plantillaAnterior, elUsuario);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 132 */       rta = rs.modificarRegistro(codigo, descripcion, estado, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 138 */     rs.close();
/* 139 */     if (!rta) {
/* 140 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCalPlantillas"));
/*     */     }
/* 142 */     String sPagina = "CalPlantillasAct.po?_operacion=P&codigo=" + codigo + "";
/* 143 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 154 */     String descripcion = comms.request.getParameter("fdescripcion");
/* 155 */     if (descripcion == null) {
/* 156 */       descripcion = "";
/*     */     }
/* 158 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */     
/* 162 */     CalPlantillasDAO rs = new CalPlantillasDAO();
/* 163 */     if (!rs.getEstadoConexion()) {
/* 164 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 166 */     Collection arr = rs.cargarTodos(descripcion, "");
/* 167 */     rs.close();
/* 168 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 169 */     int cuantas = 0;
/* 170 */     Iterator iterator = arr.iterator();
/* 171 */     while (iterator.hasNext()) {
/* 172 */       CalPlantillasDTO reg = (CalPlantillasDTO)iterator.next();
/* 173 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 174 */       eltr.appendChild(newtd("" + reg.getCodigo()));
/* 175 */       String url = "CalPlantillasAct.po?_operacion=P&codigo=" + reg.getCodigo() + "";
/* 176 */       eltr.appendChild(newtdhref("" + reg.getDescripcion(), url));
/*     */       
/* 178 */       url = "CalPlantillasObjAct.po?_operacion=L&plantilla=" + reg.getCodigo() + "";
/* 179 */       eltr.appendChild(newtdhref("" + reg.getEstado(), url));
/*     */       
/* 181 */       hte.appendChild(eltr);
/* 182 */       cuantas++;
/*     */     } 
/* 184 */     arr.clear();
/* 185 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/*     */   private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 199 */     int codigo = 0;
/*     */     try {
/* 201 */       codigo = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 203 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 206 */     CalPlantillasDAO rs = new CalPlantillasDAO();
/* 207 */     if (!rs.getEstadoConexion()) {
/* 208 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 210 */     CalPlantillasDTO reg = rs.cargarRegistro(codigo);
/* 211 */     rs.close();
/*     */     
/* 213 */     if (reg != null) {
/* 214 */       this.pagHTML.getElementCodigo().setValue("" + reg.getCodigo());
/* 215 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 216 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 217 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 218 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 219 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 220 */       HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 221 */       comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), true);
/*     */       
/* 223 */       this.pagHTML.getElementCodigo().setReadOnly(true);
/* 224 */       this.pagHTML.getElement_operacion().setValue("M");
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
/* 237 */     HTMLSelectElement combo = this.pagHTML.getElementEstado();
/* 238 */     comboMultivalores(combo, "ESTADO_REGISTRO", "A", true);
/*     */     
/* 240 */     combo = this.pagHTML.getElementPlantillaAnterior();
/* 241 */     llenarCombo(combo, true);
/*     */     
/* 243 */     CalPlantillasDAO rs = new CalPlantillasDAO();
/* 244 */     if (!rs.getEstadoConexion()) {
/* 245 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 247 */     this.pagHTML.getElementCodigo().setValue("" + rs.siguienteRegistro());
/* 248 */     rs.close();
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
/* 262 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 263 */     atrib.setValue(valor);
/* 264 */     return atrib;
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
/* 277 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 278 */     Element enlace = this.pagHTML.createElement("a");
/* 279 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 280 */     enlace.appendChild(hijo);
/* 281 */     Attr donde = this.pagHTML.createAttribute("href");
/* 282 */     donde.setValue(vinculo);
/* 283 */     enlace.setAttributeNode(donde);
/* 284 */     td.appendChild(enlace);
/* 285 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 286 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 296 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 297 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 298 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 299 */     return td;
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
/* 314 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 315 */     Collection arr = rs.cargarTabla(tabla);
/* 316 */     rs.close();
/* 317 */     if (dejarBlanco) {
/* 318 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 319 */       op.setValue("");
/* 320 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 321 */       combo.appendChild(op);
/*     */     } 
/* 323 */     Iterator iterator = arr.iterator();
/* 324 */     while (iterator.hasNext()) {
/* 325 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 326 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 327 */       op.setValue("" + reg.getCodigo());
/* 328 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 329 */       if (defecto.equals(reg.getCodigo())) {
/* 330 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 331 */         escogida.setValue("on");
/* 332 */         op.setAttributeNode(escogida);
/*     */       } 
/* 334 */       combo.appendChild(op);
/*     */     } 
/* 336 */     arr.clear();
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
/*     */   private void llenarCombo(HTMLSelectElement combo, boolean dejarBlanco) {
/* 353 */     if (dejarBlanco) {
/* 354 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 355 */       op.setValue("");
/* 356 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 357 */       combo.appendChild(op);
/*     */     } 
/*     */     
/* 360 */     CalPlantillasDAO rs = new CalPlantillasDAO();
/* 361 */     Collection arr = rs.cargarTodos("", "A");
/* 362 */     rs.close();
/*     */     
/* 364 */     Iterator iterator = arr.iterator();
/* 365 */     while (iterator.hasNext()) {
/* 366 */       CalPlantillasDTO regGeneral = (CalPlantillasDTO)iterator.next();
/* 367 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 368 */       op.setValue("" + regGeneral.getCodigo());
/* 369 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 370 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalPlantillasAct.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */