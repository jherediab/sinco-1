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
/*     */ import sinco.business.ContImputacionPresupuestalDTO;
/*     */ import sinco.data.ContImputacionPresupuestalDAO;
/*     */ import sinco.presentation.ContImputacionPresupuestalAct;
/*     */ import sinco.presentation.ContImputacionPresupuestalActHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContImputacionPresupuestalAct
/*     */   implements HttpPresentation
/*     */ {
/*     */   private ContImputacionPresupuestalActHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  31 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  32 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  35 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*     */ 
/*     */     
/*  38 */     String _operacion = comms.request.getParameter("_operacion");
/*  39 */     if (_operacion == null || _operacion.length() == 0) {
/*  40 */       _operacion = "X";
/*     */     }
/*     */     
/*  43 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  44 */       creacion(comms);
/*     */     }
/*  46 */     this.pagHTML = (ContImputacionPresupuestalActHTML)comms.xmlcFactory.create(ContImputacionPresupuestalActHTML.class);
/*  47 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  48 */       listar(comms, _operacion);
/*     */     
/*     */     }
/*  51 */     else if (_operacion.equals("P")) {
/*  52 */       editar(comms);
/*     */     }
/*  54 */     else if (_operacion.equals("Nuevo")) {
/*  55 */       HTMLElement sel = this.pagHTML.getElementBtnEliminar();
/*  56 */       sel.getParentNode().removeChild(sel);
/*  57 */       nuevo(comms);
/*     */     } 
/*  59 */     if (_operacion.equals("P") || _operacion.equals("Nuevo")) {
/*  60 */       HTMLElement sel = this.pagHTML.getElementTrConsulta();
/*  61 */       sel.getParentNode().removeChild(sel);
/*  62 */       sel = this.pagHTML.getElementTrResultados();
/*  63 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/*  65 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  66 */       HTMLElement sel = this.pagHTML.getElementTrCreacionRegistro();
/*  67 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/*     */     
/*  70 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  71 */     comms.response.writeDOM(this.pagHTML);
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
/*  83 */     String _operacion = comms.request.getParameter("_operacion");
/*  84 */     String elUsuario = "" + comms.session.getUser().getName();
/*  85 */     String codigoImputacion = comms.request.getParameter("codigoImputacion");
/*  86 */     int anio = 0;
/*     */     try {
/*  88 */       anio = Integer.parseInt(comms.request.getParameter("anio"));
/*     */     }
/*  90 */     catch (Exception e) {}
/*     */     
/*  92 */     boolean rta = false;
/*  93 */     if (_operacion.equals("E")) {
/*     */       
/*  95 */       ContImputacionPresupuestalDAO rs = new ContImputacionPresupuestalDAO();
/*  96 */       if (!rs.getEstadoConexion()) {
/*  97 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*  99 */       rta = rs.eliminarRegistro(codigoImputacion, anio);
/* 100 */       rs.close();
/*     */       
/* 102 */       if (!rta) {
/* 103 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorContImputacionPresupuestal"));
/*     */       }
/* 105 */       String sPagina = "ContImputacionPresupuestalAct.po?_operacion=X";
/* 106 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 108 */     String descripcion = comms.request.getParameter("descripcion");
/*     */ 
/*     */     
/* 111 */     ContImputacionPresupuestalDAO rs = new ContImputacionPresupuestalDAO();
/* 112 */     if (!rs.getEstadoConexion()) {
/* 113 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 115 */     rta = false;
/* 116 */     if (_operacion.equals("C")) {
/* 117 */       rta = rs.crearRegistro(codigoImputacion, descripcion, anio, elUsuario);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 124 */       rta = rs.modificarRegistro(codigoImputacion, descripcion, anio, elUsuario);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     rs.close();
/* 131 */     if (!rta) {
/* 132 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorContImputacionPresupuestal"));
/*     */     }
/* 134 */     String sPagina = "ContImputacionPresupuestalAct.po?_operacion=P&codigoImputacion=" + codigoImputacion + "&anio=" + anio;
/* 135 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/*     */   private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
/* 147 */     String codigoImputacion = comms.request.getParameter("codigoImputacion");
/* 148 */     if (codigoImputacion == null) {
/* 149 */       codigoImputacion = "";
/*     */     }
/* 151 */     String descripcion = comms.request.getParameter("descripcion");
/* 152 */     if (descripcion == null) {
/* 153 */       descripcion = "";
/*     */     }
/* 155 */     int anio = 0;
/*     */     try {
/* 157 */       anio = Integer.parseInt(comms.request.getParameter("anio"));
/*     */     }
/* 159 */     catch (Exception e) {}
/*     */ 
/*     */     
/* 162 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */     
/* 166 */     ContImputacionPresupuestalDAO rs = new ContImputacionPresupuestalDAO();
/* 167 */     if (!rs.getEstadoConexion()) {
/* 168 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 170 */     Collection<ContImputacionPresupuestalDTO> arr = rs.cargarTodos(codigoImputacion, descripcion, anio);
/*     */ 
/*     */ 
/*     */     
/* 174 */     rs.close();
/* 175 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 176 */     int cuantas = 0;
/* 177 */     Iterator<ContImputacionPresupuestalDTO> iterator = arr.iterator();
/* 178 */     while (iterator.hasNext()) {
/* 179 */       ContImputacionPresupuestalDTO reg = (ContImputacionPresupuestalDTO)iterator.next();
/* 180 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 181 */       eltr.appendChild(newtd("" + reg.getCodigoImputacion()));
/* 182 */       String url = "ContImputacionPresupuestalAct.po?_operacion=P&codigoImputacion=" + reg.getCodigoImputacion() + "&anio=" + reg.getAnio();
/* 183 */       eltr.appendChild(newtdhref("" + reg.getDescripcion(), url));
/* 184 */       eltr.appendChild(newtd("" + reg.getAnio()));
/* 185 */       hte.appendChild(eltr);
/* 186 */       cuantas++;
/*     */     } 
/* 188 */     arr.clear();
/* 189 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/*     */   private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 204 */     String codigoImputacion = comms.request.getParameter("codigoImputacion");
/* 205 */     int anio = 0;
/*     */     try {
/* 207 */       anio = Integer.parseInt(comms.request.getParameter("anio"));
/*     */     }
/* 209 */     catch (Exception e) {}
/*     */     
/* 211 */     ContImputacionPresupuestalDAO rs = new ContImputacionPresupuestalDAO();
/* 212 */     if (!rs.getEstadoConexion()) {
/* 213 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 215 */     ContImputacionPresupuestalDTO reg = rs.cargarRegistro(codigoImputacion, anio);
/* 216 */     rs.close();
/*     */     
/* 218 */     if (reg != null) {
/* 219 */       this.pagHTML.getElementCodigoImputacion().setValue("" + reg.getCodigoImputacion());
/* 220 */       this.pagHTML.getElementDescripcion().setValue("" + reg.getDescripcion());
/* 221 */       this.pagHTML.getElementAnio().setValue("" + reg.getAnio());
/* 222 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 223 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 224 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 225 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/*     */       
/* 227 */       this.pagHTML.getElementCodigoImputacion().setReadOnly(true);
/* 228 */       this.pagHTML.getElement_operacion().setValue("M");
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
/*     */   private void nuevo(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {}
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
/* 251 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 252 */     atrib.setValue(valor);
/* 253 */     return atrib;
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
/* 266 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 267 */     Element enlace = this.pagHTML.createElement("a");
/* 268 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 269 */     enlace.appendChild(hijo);
/* 270 */     Attr donde = this.pagHTML.createAttribute("href");
/* 271 */     donde.setValue(vinculo);
/* 272 */     enlace.setAttributeNode(donde);
/* 273 */     td.appendChild(enlace);
/* 274 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 275 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 285 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 286 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 287 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 288 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ContImputacionPresupuestalAct.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */