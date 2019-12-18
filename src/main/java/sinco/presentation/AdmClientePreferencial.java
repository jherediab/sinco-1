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
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.ClientesPreferencialesDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.ClientesPreferencialesDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.AdmClientePreferencial;
/*     */ import sinco.presentation.AdmClientePreferencialHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AdmClientePreferencial
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AdmClientePreferencialHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  38 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  39 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  42 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*     */     
/*  44 */     int codigoServicio = 0;
/*     */     try {
/*  46 */       codigoServicio = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/*  48 */     catch (Exception e) {
/*  49 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigo_area"));
/*     */     } 
/*     */ 
/*     */     
/*  53 */     String _operacion = comms.request.getParameter("_operacion");
/*  54 */     if (_operacion == null || _operacion.length() == 0) {
/*  55 */       _operacion = "L";
/*     */     }
/*     */     
/*  58 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  59 */       creacion(comms);
/*     */     }
/*     */ 
/*     */     
/*  63 */     this.pagHTML = (AdmClientePreferencialHTML)comms.xmlcFactory.create(AdmClientePreferencialHTML.class);
/*     */ 
/*     */     
/*  66 */     this.pagHTML.getElementCodigoServicio().setValue("" + codigoServicio);
/*  67 */     ServiciosDAO rsServicio = new ServiciosDAO();
/*  68 */     if (!rsServicio.getEstadoConexion()) {
/*  69 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*  71 */     ServiciosDTO regServicio = rsServicio.cargarRegistro(codigoServicio);
/*  72 */     if (regServicio != null) {
/*  73 */       this.pagHTML.getElementNombreServicio().setValue("" + regServicio.getDescripcion());
/*     */     }
/*  75 */     rsServicio.close();
/*     */ 
/*     */ 
/*     */     
/*  79 */     if (_operacion.equals("P")) {
/*  80 */       editar(comms);
/*     */     } else {
/*     */       
/*  83 */       HTMLElement sel = this.pagHTML.getElementEliminarRegistro();
/*  84 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/*  86 */     listar(comms);
/*     */     
/*  88 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  89 */     comms.response.writeDOM(this.pagHTML);
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
/* 101 */     String _operacion = comms.request.getParameter("_operacion");
/* 102 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */ 
/*     */     
/* 105 */     int codigoServicio = 0;
/*     */     try {
/* 107 */       codigoServicio = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 109 */     catch (Exception e) {
/* 110 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigo_area"));
/*     */     } 
/*     */     
/* 113 */     int codigoEmpleado = 0;
/*     */     try {
/* 115 */       codigoEmpleado = Integer.parseInt(comms.request.getParameter("codigoEmpleado"));
/*     */     }
/* 117 */     catch (Exception e) {
/* 118 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigo_empleado"));
/*     */     } 
/*     */     
/* 121 */     RespuestaBD rta = new RespuestaBD();
/* 122 */     if (_operacion.equals("E")) {
/* 123 */       ClientesPreferencialesDAO rs = new ClientesPreferencialesDAO();
/* 124 */       if (!rs.getEstadoConexion()) {
/* 125 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 127 */       rta = rs.eliminarRegistro(codigoServicio, codigoEmpleado);
/* 128 */       if (!rta.isRta()) {
/* 129 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPersonasArea"));
/*     */       }
/* 131 */       rs.close();
/* 132 */       String sPagina = "AdmClientePreferencial.po?_operacion=L&codigo=" + codigoServicio;
/* 133 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/*     */     
/* 136 */     ClientesPreferencialesDAO rs = new ClientesPreferencialesDAO();
/* 137 */     if (!rs.getEstadoConexion()) {
/* 138 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 140 */     if (_operacion.equals("C")) {
/*     */       
/* 142 */       ClientesPreferencialesDTO reg = rs.cargarRegistro(codigoServicio, codigoEmpleado);
/* 143 */       rta.setRta(true);
/* 144 */       if (reg == null) {
/* 145 */         rta = rs.crearRegistro(codigoServicio, codigoEmpleado, elUsuario);
/*     */       }
/*     */     } else {
/*     */       
/* 149 */       rta = rs.modificarRegistro(codigoServicio, codigoEmpleado, elUsuario);
/*     */     } 
/* 151 */     rs.close();
/* 152 */     if (!rta.isRta()) {
/* 153 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPersonasArea"));
/*     */     }
/* 155 */     String sPagina = "AdmClientePreferencial.po?_operacion=L&codigo=" + codigoServicio;
/* 156 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/*     */   private void listar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 170 */     int codigoServicio = 0;
/*     */     try {
/* 172 */       codigoServicio = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 174 */     catch (Exception e) {
/* 175 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigo_area"));
/*     */     } 
/*     */ 
/*     */     
/* 179 */     ClientesPreferencialesDAO rs = new ClientesPreferencialesDAO();
/* 180 */     if (!rs.getEstadoConexion()) {
/* 181 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 183 */     rs.cargarTodos(codigoServicio);
/* 184 */     ClientesPreferencialesDTO reg = rs.next();
/* 185 */     HTMLTableElement hte = this.pagHTML.getElementDetalle();
/* 186 */     boolean fondo = true;
/* 187 */     while (reg != null) {
/* 188 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 189 */       fondo = !fondo;
/* 190 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/* 191 */       eltr.appendChild(newtdhref("" + reg.getNombrePersona(), "AdmClientePreferencial.po?_operacion=P&codigo=" + reg.getCodigoServicio() + "&codigoEmpleado=" + reg.getCodigoPersona()));
/* 192 */       eltr.appendChild(newtd("" + reg.getNombreArea()));
/* 193 */       hte.appendChild(eltr);
/* 194 */       reg = rs.next();
/*     */     } 
/* 196 */     rs.close();
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
/* 211 */     int codigoServicio = 0;
/*     */     try {
/* 213 */       codigoServicio = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 215 */     catch (Exception e) {
/* 216 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigo_area"));
/*     */     } 
/* 218 */     int codigoEmpleado = 0;
/*     */     try {
/* 220 */       codigoEmpleado = Integer.parseInt(comms.request.getParameter("codigoEmpleado"));
/*     */     }
/* 222 */     catch (Exception e) {
/* 223 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigo_empleado"));
/*     */     } 
/*     */     
/* 226 */     ClientesPreferencialesDAO rs = new ClientesPreferencialesDAO();
/* 227 */     if (!rs.getEstadoConexion()) {
/* 228 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*     */     
/* 231 */     ClientesPreferencialesDTO reg = rs.cargarRegistro(codigoServicio, codigoEmpleado);
/* 232 */     rs.close();
/* 233 */     if (reg != null) {
/* 234 */       this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 235 */       this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 236 */       this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 237 */       this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 238 */       this.pagHTML.getElement_operacion().setValue("M");
/*     */       
/* 240 */       HTMLSelectElement combo = this.pagHTML.getElementCodigoEmpleado();
/* 241 */       llenarCombo(combo, "sis_usuarios", "codigo_empleado", "apellidos||' '||nombres", "codigo_empleado=" + reg.getCodigoPersona() + "", "", false);
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
/*     */   private Attr newAttr(String tipo, String valor) {
/* 259 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 260 */     atrib.setValue(valor);
/* 261 */     return atrib;
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
/* 274 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 275 */     Element enlace = this.pagHTML.createElement("a");
/* 276 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 277 */     enlace.appendChild(hijo);
/* 278 */     Attr donde = this.pagHTML.createAttribute("href");
/* 279 */     donde.setValue(vinculo);
/* 280 */     enlace.setAttributeNode(donde);
/* 281 */     td.appendChild(enlace);
/* 282 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 283 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 293 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 294 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 295 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 296 */     return td;
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
/*     */   
/*     */   private void llenarCombo(HTMLSelectElement combo, String tabla, String codigo, String descripcion, String condicion, String defecto, boolean dejarBlanco) {
/* 319 */     if (dejarBlanco) {
/* 320 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 321 */       op.setValue("");
/* 322 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 323 */       combo.appendChild(op);
/*     */     } 
/* 325 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 326 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/* 327 */     rsTGen.close();
/* 328 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 329 */     while (iterator.hasNext()) {
/* 330 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 331 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 332 */       op.setValue("" + regGeneral.getCodigoS());
/* 333 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 334 */       if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
/* 335 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 336 */         escogida.setValue("on");
/* 337 */         op.setAttributeNode(escogida);
/*     */       } 
/* 339 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AdmClientePreferencial.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */