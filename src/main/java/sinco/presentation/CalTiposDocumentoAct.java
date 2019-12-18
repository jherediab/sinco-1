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
/*     */ import sinco.business.CalTiposDocumentoDTO;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.data.CalTiposDocumentoDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.presentation.CalTiposDocumentoAct;
/*     */ import sinco.presentation.CalTiposDocumentoActHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalTiposDocumentoAct
/*     */   implements HttpPresentation
/*     */ {
/*     */   private CalTiposDocumentoActHTML pagHTML;
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
/*  49 */     this.pagHTML = (CalTiposDocumentoActHTML)comms.xmlcFactory.create(CalTiposDocumentoActHTML.class);
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
/*  87 */     String codigo = comms.request.getParameter("codigo");
/*  88 */     boolean rta = false;
/*  89 */     if (_operacion.equals("E")) {
/*  90 */       CalTiposDocumentoDAO rs = new CalTiposDocumentoDAO();
/*  91 */       if (!rs.getEstadoConexion()) {
/*  92 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*  94 */       rta = rs.eliminarRegistro(codigo);
/*  95 */       if (!rta) {
/*  96 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCalTiposDocumento"));
/*     */       }
/*  98 */       rs.close();
/*  99 */       String sPagina = "CalTiposDocumentoAct.po?_operacion=L";
/* 100 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 102 */     String descripcion = comms.request.getParameter("descripcion");
/* 103 */     String mostrarEnListaMaestra = comms.request.getParameter("mostrarEnListaMaestra");
/* 104 */     if (mostrarEnListaMaestra == null) mostrarEnListaMaestra = "N"; 
/* 105 */     String mostrarEnMapa = comms.request.getParameter("mostrarEnMapa");
/* 106 */     if (mostrarEnMapa == null) mostrarEnMapa = "N"; 
/* 107 */     String mostrarEnPlanes = comms.request.getParameter("mostrarEnPlanes");
/* 108 */     if (mostrarEnPlanes == null) mostrarEnPlanes = "N"; 
/* 109 */     String estado = comms.request.getParameter("estado");
/* 110 */     CalTiposDocumentoDAO rs = new CalTiposDocumentoDAO();
/* 111 */     if (!rs.getEstadoConexion()) {
/* 112 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 114 */     rta = false;
/* 115 */     if (_operacion.equals("C")) {
/* 116 */       rta = rs.crearRegistro(codigo, descripcion, mostrarEnListaMaestra, mostrarEnMapa, mostrarEnPlanes, estado, elUsuario);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 126 */       rta = rs.modificarRegistro(codigo, descripcion, mostrarEnListaMaestra, mostrarEnMapa, mostrarEnPlanes, estado, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 135 */     rs.close();
/* 136 */     if (!rta) {
/* 137 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCalTiposDocumento"));
/*     */     }
/* 139 */     String sPagina = "CalTiposDocumentoAct.po?_operacion=L";
/* 140 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 151 */     String descripcion = comms.request.getParameter("descripcion");
/* 152 */     if (descripcion == null) {
/* 153 */       descripcion = "";
/*     */     }
/* 155 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */     
/* 159 */     CalTiposDocumentoDAO rs = new CalTiposDocumentoDAO();
/* 160 */     if (!rs.getEstadoConexion()) {
/* 161 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 163 */     Collection arr = rs.cargarTodos(descripcion);
/* 164 */     rs.close();
/* 165 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 166 */     int cuantas = 0;
/* 167 */     Iterator iterator = arr.iterator();
/* 168 */     while (iterator.hasNext()) {
/* 169 */       CalTiposDocumentoDTO reg = (CalTiposDocumentoDTO)iterator.next();
/* 170 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 171 */       String url = "CalTiposDocumentoAct.po?_operacion=P&codigo=" + reg.getCodigo() + "";
/* 172 */       eltr.appendChild(newtdhref("" + reg.getCodigo(), url));
/* 173 */       eltr.appendChild(newtd("" + reg.getDescripcion()));
/* 174 */       eltr.appendChild(newtd("" + reg.getMostrarEnListaMaestra()));
/* 175 */       eltr.appendChild(newtd("" + reg.getMostrarEnMapa()));
/* 176 */       eltr.appendChild(newtd("" + reg.getMostrarEnPlanes()));
/* 177 */       eltr.appendChild(newtd("" + reg.getEstado()));
/* 178 */       hte.appendChild(eltr);
/* 179 */       cuantas++;
/*     */     } 
/* 181 */     arr.clear();
/* 182 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/*     */   private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 195 */     String codigo = comms.request.getParameter("codigo");
/* 196 */     CalTiposDocumentoDAO rs = new CalTiposDocumentoDAO();
/* 197 */     if (!rs.getEstadoConexion()) {
/* 198 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 200 */     CalTiposDocumentoDTO reg = rs.cargarRegistro(codigo);
/* 201 */     rs.close();
/* 202 */     if (reg != null) {
/* 203 */       this.pagHTML.getElementCodigo().setValue("" + reg.getCodigo());
/* 204 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 205 */       if (reg.getMostrarEnListaMaestra().equals("S")) {
/* 206 */         this.pagHTML.getElementMostrarEnListaMaestra().setChecked(true);
/*     */       }
/*     */       
/* 209 */       if (reg.getMostrarEnMapa().equals("S")) {
/* 210 */         this.pagHTML.getElementMostrarEnMapa().setChecked(true);
/*     */       }
/* 212 */       if (reg.getMostrarEnPlanes().equals("S")) {
/* 213 */         this.pagHTML.getElementMostrarEnPlanes().setChecked(true);
/*     */       }
/* 215 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 216 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 217 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 218 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/*     */       
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
/*     */   private Attr newAttr(String tipo, String valor) {
/* 250 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 251 */     atrib.setValue(valor);
/* 252 */     return atrib;
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
/* 265 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 266 */     Element enlace = this.pagHTML.createElement("a");
/* 267 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 268 */     enlace.appendChild(hijo);
/* 269 */     Attr donde = this.pagHTML.createAttribute("href");
/* 270 */     donde.setValue(vinculo);
/* 271 */     enlace.setAttributeNode(donde);
/* 272 */     td.appendChild(enlace);
/* 273 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 274 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 284 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 285 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 286 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 287 */     return td;
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
/* 302 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 303 */     Collection arr = rs.cargarTabla(tabla);
/* 304 */     rs.close();
/* 305 */     if (dejarBlanco) {
/* 306 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 307 */       op.setValue("");
/* 308 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 309 */       combo.appendChild(op);
/*     */     } 
/* 311 */     Iterator iterator = arr.iterator();
/* 312 */     while (iterator.hasNext()) {
/* 313 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 314 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 315 */       op.setValue("" + reg.getCodigo());
/* 316 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 317 */       if (defecto.equals(reg.getCodigo())) {
/* 318 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 319 */         escogida.setValue("on");
/* 320 */         op.setAttributeNode(escogida);
/*     */       } 
/* 322 */       combo.appendChild(op);
/*     */     } 
/* 324 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalTiposDocumentoAct.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */