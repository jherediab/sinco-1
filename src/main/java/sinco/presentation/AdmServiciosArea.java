/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.ProveedorMultipleDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.ServicioAreaDTO;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.ProveedorMultipleDAO;
/*     */ import sinco.data.ServicioAreaDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.AdmServiciosArea;
/*     */ import sinco.presentation.AdmServiciosAreaHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AdmServiciosArea
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AdmServiciosAreaHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  46 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  47 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  50 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  51 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */     
/*  53 */     int codigoArea = 0;
/*     */     try {
/*  55 */       codigoArea = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/*  57 */     catch (Exception e) {
/*  58 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigo_area"));
/*     */     } 
/*     */ 
/*     */     
/*  62 */     String _operacion = comms.request.getParameter("_operacion");
/*  63 */     if (_operacion == null || _operacion.length() == 0) {
/*  64 */       _operacion = "L";
/*     */     }
/*     */ 
/*     */     
/*  68 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  69 */       creacion(comms);
/*     */     }
/*     */ 
/*     */     
/*  73 */     this.pagHTML = (AdmServiciosAreaHTML)comms.xmlcFactory.create(AdmServiciosAreaHTML.class);
/*     */ 
/*     */     
/*  76 */     this.pagHTML.getElementCodigoArea().setValue("" + codigoArea);
/*  77 */     AreasDAO rsAreas = new AreasDAO();
/*  78 */     if (!rsAreas.getEstadoConexion()) {
/*  79 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*  81 */     AreasDTO regArea = rsAreas.cargarRegistro(codigoArea);
/*  82 */     if (regArea != null) {
/*  83 */       this.pagHTML.getElementNombreArea().setValue("" + regArea.getDescripcion());
/*     */     }
/*  85 */     rsAreas.close();
/*     */ 
/*     */     
/*  88 */     ServicioAreaDAO rsServiciosArea = new ServicioAreaDAO();
/*  89 */     if (!rsServiciosArea.getEstadoConexion()) {
/*  90 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*  92 */     Collection arr = rsServiciosArea.cargarServiciosDeArea(codigoArea);
/*     */     
/*  94 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/*     */     
/*  96 */     boolean fondo = true;
/*  97 */     int cuantas = 0;
/*     */     
/*  99 */     Iterator<ServicioAreaDTO> iterator = arr.iterator();
/* 100 */     while (iterator.hasNext()) {
/* 101 */       ServicioAreaDTO reg = (ServicioAreaDTO)iterator.next();
/* 102 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 103 */       fondo = !fondo;
/* 104 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/* 105 */       eltr.appendChild(newtdhref("" + reg.getNombreServicio(), "AdmServiciosArea.po?_operacion=P&codigo=" + reg.getCodigoArea() + "&codigoServicio=" + reg.getCodigoServicio()));
/* 106 */       eltr.appendChild(newtd("" + reg.getNombreResponsable()));
/* 107 */       hte.appendChild(eltr);
/* 108 */       cuantas++;
/*     */     } 
/*     */     
/* 111 */     if (_operacion.equals("P")) {
/* 112 */       int codigoServicio = 0;
/*     */       try {
/* 114 */         codigoServicio = Integer.parseInt(comms.request.getParameter("codigoServicio"));
/*     */       }
/* 116 */       catch (Exception e) {
/* 117 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoServicio"));
/*     */       } 
/*     */       
/* 120 */       ServiciosDAO rsServicios = new ServiciosDAO();
/* 121 */       if (!rsServicios.getEstadoConexion()) {
/* 122 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 124 */       ServiciosDTO regs = rsServicios.cargarRegistro(codigoServicio);
/* 125 */       rsServicios.close();
/*     */       
/* 127 */       ServicioAreaDTO reg = rsServiciosArea.getServicioArea(codigoArea, codigoServicio);
/* 128 */       if (reg != null) {
/* 129 */         HTMLSelectElement combo = this.pagHTML.getElementCodigoServicio();
/* 130 */         llenarCombo(combo, "servicios", "CODIGO", "DESCRIPCION", "codigo=" + codigoServicio, "" + codigoServicio, false);
/*     */         
/* 132 */         this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 133 */         this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 134 */         this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 135 */         this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 136 */         this.pagHTML.getElement_operacion().setValue("M");
/* 137 */         this.pagHTML.getElementEspecializado().setValue("" + regs.getEspecializado());
/*     */         
/* 139 */         if (regs.getEspecializado().equals("S")) {
/* 140 */           combo = this.pagHTML.getElementPersonaCargo();
/* 141 */           comboEspecialistas(combo, reg.getCodigoArea(), reg.getPersonaCargo());
/*     */         }
/* 143 */         else if (regs.getEspecializado().equals("M")) {
/* 144 */           multiplesProveedores(comms, codigoArea, codigoServicio);
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 149 */       HTMLElement sel = this.pagHTML.getElementEliminarRegistro();
/* 150 */       sel.getParentNode().removeChild(sel);
/*     */       
/* 152 */       HTMLSelectElement combo = this.pagHTML.getElementCodigoServicio();
/* 153 */       llenarCombo(combo, "servicios", "CODIGO", "DESCRIPCION", "estado='A' AND codigo not in (select Sa.Codigo_servicio from Servicios_Area Sa where  Sa.Codigo_area =" + codigoArea + ")", "", true);
/*     */     } 
/*     */     
/* 156 */     rsServiciosArea.close();
/*     */ 
/*     */     
/* 159 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/* 160 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private void creacion(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 174 */     String _operacion = comms.request.getParameter("_operacion");
/* 175 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */     
/* 177 */     int codigoArea = 0;
/*     */     try {
/* 179 */       codigoArea = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 181 */     catch (Exception e) {
/* 182 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigo_area"));
/*     */     } 
/*     */ 
/*     */     
/* 186 */     String especializado = comms.request.getParameter("especializado");
/* 187 */     int codigoServicio = 0;
/*     */     try {
/* 189 */       codigoServicio = Integer.parseInt(comms.request.getParameter("codigoServicio"));
/*     */     }
/* 191 */     catch (Exception e) {
/* 192 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoServicio"));
/*     */     } 
/* 194 */     RespuestaBD rta = new RespuestaBD();
/* 195 */     if (_operacion.equals("E")) {
/* 196 */       ServicioAreaDAO rsServiciosArea = new ServicioAreaDAO();
/* 197 */       if (!rsServiciosArea.getEstadoConexion()) {
/* 198 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 200 */       rta = rsServiciosArea.eliminarRegistro(codigoArea, codigoServicio);
/* 201 */       if (!rta.isRta()) {
/* 202 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorServiciosArea"));
/*     */       }
/* 204 */       rsServiciosArea.close();
/* 205 */       String sPagina = "AdmServiciosArea.po?_operacion=L&codigo=" + codigoArea;
/* 206 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/*     */     
/* 209 */     ServicioAreaDAO rsServiciosArea = new ServicioAreaDAO();
/* 210 */     if (!rsServiciosArea.getEstadoConexion()) {
/* 211 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 213 */     int personaCargo = 0;
/*     */     
/* 215 */     if (especializado.equals("S")) {
/*     */       try {
/* 217 */         personaCargo = Integer.parseInt(comms.request.getParameter("personaCargo"));
/*     */       }
/* 219 */       catch (Exception e) {
/* 220 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=persona_cargo"));
/*     */       } 
/*     */     }
/*     */     
/* 224 */     if (_operacion.equals("C")) {
/* 225 */       rta = rsServiciosArea.crearRegistro(codigoArea, codigoServicio, personaCargo, elUsuario);
/*     */     } else {
/*     */       
/* 228 */       rta = rsServiciosArea.modificarRegistro(codigoArea, codigoServicio, personaCargo, elUsuario);
/*     */     } 
/* 230 */     rsServiciosArea.close();
/* 231 */     if (!rta.isRta()) {
/* 232 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorServiciosArea"));
/*     */     }
/*     */     
/* 235 */     if (especializado.equals("M")) {
/* 236 */       ProveedorMultipleDAO rs = new ProveedorMultipleDAO();
/* 237 */       if (!rs.getEstadoConexion()) {
/* 238 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*     */       
/* 241 */       Enumeration enumera = comms.request.getParameterNames();
/*     */ 
/*     */       
/* 244 */       rs.eliminarRegistro(codigoArea, codigoServicio);
/*     */       
/* 246 */       while (enumera.hasMoreElements()) {
/* 247 */         String param = (String)enumera.nextElement();
/* 248 */         if (param.substring(0, 2).equals("M_")) {
/* 249 */           personaCargo = Integer.parseInt(param.substring(2, param.length()));
/* 250 */           rs.crearRegistro(codigoArea, codigoServicio, personaCargo, elUsuario);
/*     */         } 
/*     */       } 
/* 253 */       rs.close();
/*     */     } 
/*     */     
/* 256 */     if (!rta.isRta()) {
/* 257 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPersonasArea"));
/*     */     }
/* 259 */     String sPagina = "AdmServiciosArea.po?_operacion=L&codigo=" + codigoArea;
/* 260 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 274 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 275 */     atrib.setValue(valor);
/* 276 */     return atrib;
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
/* 289 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 290 */     Element enlace = this.pagHTML.createElement("a");
/* 291 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 292 */     enlace.appendChild(hijo);
/* 293 */     Attr donde = this.pagHTML.createAttribute("href");
/* 294 */     donde.setValue(vinculo);
/* 295 */     enlace.setAttributeNode(donde);
/* 296 */     td.appendChild(enlace);
/* 297 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 298 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 308 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 309 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 310 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 311 */     return td;
/*     */   }
/*     */   private boolean comboEspecialistas(HTMLSelectElement combo, int codigoArea, int personaCargo) {
/* 314 */     SisUsuariosDAO pf = new SisUsuariosDAO();
/* 315 */     Collection<SisUsuariosDTO> arr = pf.cargarActivoDeArea(codigoArea);
/* 316 */     Iterator<SisUsuariosDTO> iterator = arr.iterator();
/* 317 */     while (iterator.hasNext()) {
/* 318 */       SisUsuariosDTO regPersona = (SisUsuariosDTO)iterator.next();
/* 319 */       HTMLOptionElement opersona = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 320 */       opersona.appendChild(this.pagHTML.createTextNode(regPersona.getApellidos() + ", " + regPersona.getNombres()));
/* 321 */       opersona.setValue("" + regPersona.getCodigoEmpleado());
/*     */       
/* 323 */       if (personaCargo == regPersona.getCodigoEmpleado()) {
/* 324 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 325 */         escogida.setValue("on");
/* 326 */         opersona.setAttributeNode(escogida);
/*     */       } 
/* 328 */       combo.appendChild(opersona);
/*     */     } 
/* 330 */     return true;
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
/*     */   private boolean multiplesProveedores(HttpPresentationComms comms, int codigoArea, int codigoServicio) throws HttpPresentationException, KeywordValueException {
/* 345 */     ProveedorMultipleDAO rsProveedorMultiple = new ProveedorMultipleDAO();
/* 346 */     if (!rsProveedorMultiple.getEstadoConexion()) {
/* 347 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 349 */     rsProveedorMultiple.cargarMultiplesArea(codigoArea, codigoServicio);
/*     */     
/* 351 */     ProveedorMultipleDTO reg = rsProveedorMultiple.next2();
/* 352 */     HTMLTableElement hte = this.pagHTML.getElementTblMultiple();
/* 353 */     boolean fondo = true;
/* 354 */     while (reg != null) {
/* 355 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 356 */       fondo = !fondo;
/* 357 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 359 */       HTMLElement tdMarca = (HTMLElement)this.pagHTML.createElement("td");
/*     */       
/* 361 */       HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
/* 362 */       checkbox.setAttribute("type", "checkbox");
/* 363 */       checkbox.setName("M_" + reg.getPersonaCargo());
/* 364 */       if (reg.getExiste().equals("S")) {
/* 365 */         checkbox.setChecked(true);
/*     */       }
/* 367 */       tdMarca.appendChild(checkbox);
/* 368 */       eltr.appendChild(tdMarca);
/* 369 */       eltr.appendChild(newtd("" + reg.getNombreResponsable()));
/*     */       
/* 371 */       hte.appendChild(eltr);
/* 372 */       reg = rsProveedorMultiple.next2();
/*     */     } 
/* 374 */     rsProveedorMultiple.close();
/* 375 */     return true;
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
/* 398 */     if (dejarBlanco) {
/* 399 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 400 */       op.setValue("");
/* 401 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 402 */       combo.appendChild(op);
/*     */     } 
/* 404 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 405 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/* 406 */     rsTGen.close();
/* 407 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 408 */     while (iterator.hasNext()) {
/* 409 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 410 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 411 */       op.setValue("" + regGeneral.getCodigoS());
/* 412 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 413 */       if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
/* 414 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 415 */         escogida.setValue("on");
/* 416 */         op.setAttributeNode(escogida);
/*     */       } 
/* 418 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AdmServiciosArea.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */