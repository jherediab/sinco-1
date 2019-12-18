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
/*     */ import sinco.business.ProveedorMultipleDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.ServicioAreaDTO;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.ProveedorMultipleDAO;
/*     */ import sinco.data.ServicioAreaDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.AdmAreasServicio;
/*     */ import sinco.presentation.AdmAreasServicioHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AdmAreasServicio
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AdmAreasServicioHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  43 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  44 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  47 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*     */     
/*  49 */     String _operacion = comms.request.getParameter("_operacion");
/*  50 */     if (_operacion == null || _operacion.length() == 0) {
/*  51 */       _operacion = "L";
/*     */     }
/*     */     
/*  54 */     int codigoServicio = 0;
/*     */     try {
/*  56 */       codigoServicio = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/*  58 */     catch (Exception e) {
/*  59 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigo_servicio"));
/*     */     } 
/*     */     
/*  62 */     String especializado = comms.request.getParameter("especializado");
/*  63 */     if (especializado == null) {
/*  64 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=especializado"));
/*     */     }
/*     */ 
/*     */     
/*  68 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  69 */       creacion(comms);
/*     */     }
/*     */     
/*  72 */     this.pagHTML = (AdmAreasServicioHTML)comms.xmlcFactory.create(AdmAreasServicioHTML.class);
/*     */     
/*  74 */     ServiciosDAO serf = new ServiciosDAO();
/*  75 */     ServiciosDTO regServicio = serf.cargarRegistro(codigoServicio);
/*  76 */     serf.close();
/*     */ 
/*     */     
/*  79 */     this.pagHTML.getElementNombreServicio().setValue("" + regServicio.getDescripcion());
/*  80 */     this.pagHTML.getElementCodigoServicio().setValue("" + regServicio.getCodigo());
/*  81 */     this.pagHTML.getElementEspecializado().setValue("" + especializado);
/*     */     
/*  83 */     if (especializado.equals("S")) {
/*  84 */       HTMLElement sel = this.pagHTML.getElementTrMultiple();
/*  85 */       sel.getParentNode().removeChild(sel);
/*     */     }
/*  87 */     else if (especializado.equals("M") || especializado.equals("D") || especializado.equals("O")) {
/*  88 */       HTMLElement sel = this.pagHTML.getElementTrEspecializado();
/*  89 */       sel.getParentNode().removeChild(sel);
/*     */     } else {
/*     */       
/*  92 */       HTMLElement sel = this.pagHTML.getElementTrMultiple();
/*  93 */       sel.getParentNode().removeChild(sel);
/*     */       
/*  95 */       sel = this.pagHTML.getElementTrEspecializado();
/*  96 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/*     */     
/*  99 */     ServicioAreaDAO rsServiciosArea = new ServicioAreaDAO();
/* 100 */     if (!rsServiciosArea.getEstadoConexion()) {
/* 101 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 103 */     rsServiciosArea.cargarAreasDeServicio(codigoServicio);
/* 104 */     ServicioAreaDTO reg = rsServiciosArea.next2();
/* 105 */     HTMLTableElement hte = this.pagHTML.getElementDetalle();
/* 106 */     boolean fondo = true;
/* 107 */     while (reg != null) {
/* 108 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 109 */       fondo = !fondo;
/* 110 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/* 111 */       String sPagina = "AdmAreasServicio.po?_operacion=P&codigo=" + codigoServicio + "&especializado=" + especializado + "&codigoArea=" + reg.getCodigoArea();
/*     */       
/* 113 */       eltr.appendChild(newtdhref("" + reg.getNombreArea(), sPagina));
/* 114 */       eltr.appendChild(newtd("" + reg.getNombreResponsable()));
/* 115 */       hte.appendChild(eltr);
/* 116 */       reg = rsServiciosArea.next2();
/*     */     } 
/*     */     
/* 119 */     if (_operacion.equals("P")) {
/* 120 */       int codigoArea = 0;
/*     */       try {
/* 122 */         codigoArea = Integer.parseInt(comms.request.getParameter("codigoArea"));
/*     */       }
/* 124 */       catch (Exception e) {
/* 125 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigo_area"));
/*     */       } 
/*     */       
/* 128 */       reg = rsServiciosArea.getServicioArea(codigoArea, codigoServicio);
/* 129 */       if (reg != null) {
/*     */         
/* 131 */         HTMLSelectElement combo = this.pagHTML.getElementCodigoArea();
/* 132 */         llenarCombo(combo, "UNIDADES_DEPENDENCIA", "CODIGO", "DESCRIPCION", "codigo=" + codigoArea, "" + codigoArea, false);
/*     */         
/* 134 */         this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 135 */         this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 136 */         this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 137 */         this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 138 */         this.pagHTML.getElement_operacion().setValue("M");
/*     */         
/* 140 */         if (especializado.equals("S")) {
/* 141 */           combo = this.pagHTML.getElementPersonaCargo();
/* 142 */           comboEspecialistas(combo, reg.getCodigoArea(), reg.getPersonaCargo());
/*     */         }
/* 144 */         else if (especializado.equals("M") || especializado.equals("D") || especializado.equals("O")) {
/* 145 */           multiplesProveedores(comms, codigoArea, codigoServicio);
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 150 */       HTMLElement sel = this.pagHTML.getElementEliminarRegistro();
/* 151 */       sel.getParentNode().removeChild(sel);
/*     */       
/* 153 */       HTMLSelectElement combo = this.pagHTML.getElementCodigoArea();
/* 154 */       llenarCombo(combo, "UNIDADES_DEPENDENCIA", "CODIGO", "DESCRIPCION", "estado='A' AND codigo not in (select Sa.Codigo_Area from Servicios_Area Sa where  Sa.Codigo_Servicio =" + codigoServicio + ")", "", true);
/*     */     } 
/*     */     
/* 157 */     rsServiciosArea.close();
/* 158 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/* 159 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private void creacion(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 172 */     String _operacion = comms.request.getParameter("_operacion");
/* 173 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */ 
/*     */     
/* 176 */     int codigoServicio = 0;
/*     */     try {
/* 178 */       codigoServicio = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 180 */     catch (Exception e) {
/* 181 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigo_servicio"));
/*     */     } 
/*     */     
/* 184 */     int codigoArea = 0;
/*     */     try {
/* 186 */       codigoArea = Integer.parseInt(comms.request.getParameter("codigoArea"));
/*     */     }
/* 188 */     catch (Exception e) {
/* 189 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigo_area"));
/*     */     } 
/*     */     
/* 192 */     String especializado = comms.request.getParameter("especializado");
/*     */     
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
/* 205 */       String sPagina = "AdmAreasServicio.po?_operacion=L&codigo=" + codigoServicio + "&especializado=" + especializado;
/* 206 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 208 */     ServicioAreaDAO rsServiciosArea = new ServicioAreaDAO();
/* 209 */     if (!rsServiciosArea.getEstadoConexion()) {
/* 210 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 212 */     int personaCargo = 0;
/*     */     
/* 214 */     if (especializado.equals("S")) {
/*     */       try {
/* 216 */         personaCargo = Integer.parseInt(comms.request.getParameter("personaCargo"));
/*     */       }
/* 218 */       catch (Exception e) {
/* 219 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=persona_cargo"));
/*     */       } 
/*     */     }
/*     */     
/* 223 */     if (_operacion.equals("C")) {
/* 224 */       rta = rsServiciosArea.crearRegistro(codigoArea, codigoServicio, personaCargo, elUsuario);
/*     */     } else {
/*     */       
/* 227 */       rta = rsServiciosArea.modificarRegistro(codigoArea, codigoServicio, personaCargo, elUsuario);
/*     */     } 
/* 229 */     rsServiciosArea.close();
/* 230 */     if (!rta.isRta()) {
/* 231 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorServiciosArea"));
/*     */     }
/*     */     
/* 234 */     if (especializado.equals("M") || especializado.equals("D") || especializado.equals("O")) {
/* 235 */       ProveedorMultipleDAO rs = new ProveedorMultipleDAO();
/* 236 */       if (!rs.getEstadoConexion()) {
/* 237 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*     */       
/* 240 */       Enumeration enumera = comms.request.getParameterNames();
/*     */ 
/*     */       
/* 243 */       rs.eliminarRegistro(codigoArea, codigoServicio);
/*     */       
/* 245 */       while (enumera.hasMoreElements()) {
/* 246 */         String param = (String)enumera.nextElement();
/* 247 */         if (param.substring(0, 2).equals("M_")) {
/* 248 */           personaCargo = Integer.parseInt(param.substring(2, param.length()));
/* 249 */           rs.crearRegistro(codigoArea, codigoServicio, personaCargo, elUsuario);
/*     */         } 
/*     */       } 
/* 252 */       rs.close();
/*     */     } 
/*     */     
/* 255 */     String sPagina = "AdmAreasServicio.po?_operacion=L&codigo=" + codigoServicio + "&especializado=" + especializado;
/* 256 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 269 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 270 */     atrib.setValue(valor);
/* 271 */     return atrib;
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
/* 284 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 285 */     Element enlace = this.pagHTML.createElement("a");
/* 286 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 287 */     enlace.appendChild(hijo);
/* 288 */     Attr donde = this.pagHTML.createAttribute("href");
/* 289 */     donde.setValue(vinculo);
/* 290 */     enlace.setAttributeNode(donde);
/* 291 */     td.appendChild(enlace);
/* 292 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 293 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 303 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 304 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 305 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 306 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean comboEspecialistas(HTMLSelectElement combo, int codigoArea, int personaCargo) {
/* 317 */     SisUsuariosDAO pf = new SisUsuariosDAO();
/* 318 */     Collection arr = pf.cargarActivoDeArea(codigoArea);
/*     */     
/* 320 */     Iterator iterator = arr.iterator();
/* 321 */     while (iterator.hasNext()) {
/* 322 */       SisUsuariosDTO regPersona = (SisUsuariosDTO)iterator.next();
/* 323 */       HTMLOptionElement opersona = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 324 */       opersona.appendChild(this.pagHTML.createTextNode(regPersona.getApellidos() + ", " + regPersona.getNombres()));
/* 325 */       opersona.setValue("" + regPersona.getCodigoEmpleado());
/*     */       
/* 327 */       if (personaCargo == regPersona.getCodigoEmpleado()) {
/* 328 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 329 */         escogida.setValue("on");
/* 330 */         opersona.setAttributeNode(escogida);
/*     */       } 
/* 332 */       combo.appendChild(opersona);
/*     */     } 
/* 334 */     return true;
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
/* 349 */     ProveedorMultipleDAO rs = new ProveedorMultipleDAO();
/* 350 */     if (!rs.getEstadoConexion()) {
/* 351 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 353 */     rs.cargarMultiplesArea(codigoArea, codigoServicio);
/*     */     
/* 355 */     ProveedorMultipleDTO reg = rs.next2();
/* 356 */     HTMLTableElement hte = this.pagHTML.getElementTblMultiple();
/* 357 */     boolean fondo = true;
/* 358 */     while (reg != null) {
/* 359 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 360 */       fondo = !fondo;
/* 361 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/* 363 */       HTMLElement tdMarca = (HTMLElement)this.pagHTML.createElement("td");
/*     */       
/* 365 */       HTMLInputElement checkbox = (HTMLInputElement)this.pagHTML.createElement("input");
/* 366 */       checkbox.setAttribute("type", "checkbox");
/* 367 */       checkbox.setName("M_" + reg.getPersonaCargo());
/* 368 */       if (reg.getExiste().equals("S")) {
/* 369 */         checkbox.setChecked(true);
/*     */       }
/* 371 */       tdMarca.appendChild(checkbox);
/* 372 */       eltr.appendChild(tdMarca);
/* 373 */       eltr.appendChild(newtd("" + reg.getNombreResponsable()));
/*     */       
/* 375 */       hte.appendChild(eltr);
/* 376 */       reg = rs.next2();
/*     */     } 
/* 378 */     rs.close();
/* 379 */     return true;
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
/* 402 */     if (dejarBlanco) {
/* 403 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 404 */       op.setValue("");
/* 405 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 406 */       combo.appendChild(op);
/*     */     } 
/* 408 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 409 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/* 410 */     rsTGen.close();
/* 411 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 412 */     while (iterator.hasNext()) {
/* 413 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 414 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 415 */       op.setValue("" + regGeneral.getCodigoS());
/* 416 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 417 */       if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
/* 418 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 419 */         escogida.setValue("on");
/* 420 */         op.setAttributeNode(escogida);
/*     */       } 
/* 422 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AdmAreasServicio.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */