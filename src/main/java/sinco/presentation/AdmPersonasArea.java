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
/*     */ import sinco.business.AreasDTO;
/*     */ import sinco.business.PersonasAreaDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.AreasDAO;
/*     */ import sinco.data.PersonasAreaDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.AdmPersonasArea;
/*     */ import sinco.presentation.AdmPersonasAreaHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AdmPersonasArea
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AdmPersonasAreaHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  37 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  38 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  41 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*     */     
/*  43 */     int codigoArea = 0;
/*     */     try {
/*  45 */       codigoArea = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/*  47 */     catch (Exception e) {
/*  48 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigo_area"));
/*     */     } 
/*     */ 
/*     */     
/*  52 */     String _operacion = comms.request.getParameter("_operacion");
/*  53 */     if (_operacion == null || _operacion.length() == 0) {
/*  54 */       _operacion = "L";
/*     */     }
/*     */ 
/*     */     
/*  58 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  59 */       creacion(comms);
/*     */     }
/*     */ 
/*     */     
/*  63 */     this.pagHTML = (AdmPersonasAreaHTML)comms.xmlcFactory.create(AdmPersonasAreaHTML.class);
/*     */ 
/*     */     
/*  66 */     this.pagHTML.getElementCodigoArea().setValue("" + codigoArea);
/*  67 */     AreasDAO rsAreas = new AreasDAO();
/*  68 */     if (!rsAreas.getEstadoConexion()) {
/*  69 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*  71 */     AreasDTO regArea = rsAreas.cargarRegistro(codigoArea);
/*  72 */     if (regArea != null) {
/*  73 */       this.pagHTML.getElementNombreArea().setValue("" + regArea.getDescripcion());
/*     */     }
/*  75 */     rsAreas.close();
/*     */ 
/*     */     
/*  78 */     PersonasAreaDAO rsPersonasArea = new PersonasAreaDAO();
/*  79 */     if (!rsPersonasArea.getEstadoConexion()) {
/*  80 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/*  82 */     Collection arr = rsPersonasArea.cargarTodos(codigoArea);
/*  83 */     HTMLTableElement hte = this.pagHTML.getElementDetalle();
/*  84 */     boolean fondo = true;
/*  85 */     Iterator<PersonasAreaDTO> iterator = arr.iterator();
/*  86 */     while (iterator.hasNext()) {
/*  87 */       PersonasAreaDTO reg = (PersonasAreaDTO)iterator.next();
/*     */ 
/*     */       
/*  90 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  91 */       fondo = !fondo;
/*  92 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*  93 */       eltr.appendChild(newtdhref("" + reg.getNombrePersona(), "AdmPersonasArea.po?_operacion=P&codigo=" + reg.getCodigoArea() + "&codigoEmpleado=" + reg.getCodigoEmpleado()));
/*  94 */       eltr.appendChild(newtd("" + reg.getNombreClase()));
/*  95 */       eltr.appendChild(newtd("" + (reg.getResponsableArea().equals("S") ? "SI" : "NO")));
/*  96 */       eltr.appendChild(newtd("" + (reg.getAreaPrincipal().equals("S") ? "SI" : "NO")));
/*  97 */       hte.appendChild(eltr);
/*  98 */       reg = rsPersonasArea.next();
/*     */     } 
/*     */     
/* 101 */     if (_operacion.equals("P")) {
/* 102 */       int codigoEmpleado = 0;
/*     */       try {
/* 104 */         codigoEmpleado = Integer.parseInt(comms.request.getParameter("codigoEmpleado"));
/*     */       }
/* 106 */       catch (Exception e) {
/* 107 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigo_empleado"));
/*     */       } 
/*     */       
/* 110 */       PersonasAreaDTO reg = rsPersonasArea.cargarRegistro(codigoArea, codigoEmpleado);
/* 111 */       if (reg != null) {
/* 112 */         TGeneralDAO rsTGen = new TGeneralDAO();
/*     */         
/* 114 */         if (!rsTGen.getEstadoConexion()) {
/* 115 */           throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */         }
/*     */         
/* 118 */         HTMLSelectElement combo = this.pagHTML.getElementCodigoEmpleado();
/* 119 */         llenarCombo(rsTGen, combo, "sis_usuarios", "codigo_empleado", "apellidos ||' '||nombres ", "codigo_empleado=" + codigoEmpleado, "" + codigoEmpleado, false);
/*     */         
/* 121 */         combo = this.pagHTML.getElementClase();
/* 122 */         llenarCombo(rsTGen, combo, "sis_grupos", "codigo", "descripcion", "1=1", "" + reg.getClase(), false);
/*     */         
/* 124 */         if (reg.getResponsableArea().equals("S")) {
/* 125 */           this.pagHTML.getElementResponsableArea().setChecked(true);
/*     */         }
/* 127 */         if (reg.getAreaPrincipal().equals("S")) {
/* 128 */           this.pagHTML.getElementAreaPrincipal().setChecked(true);
/*     */         }
/*     */         
/* 131 */         this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 132 */         this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 133 */         this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 134 */         this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 135 */         this.pagHTML.getElement_operacion().setValue("M");
/* 136 */         rsTGen.close();
/*     */       } 
/*     */     } else {
/*     */       
/* 140 */       HTMLElement sel = this.pagHTML.getElementEliminarRegistro();
/* 141 */       sel.getParentNode().removeChild(sel);
/* 142 */       TGeneralDAO rsTGen = new TGeneralDAO();
/* 143 */       if (!rsTGen.getEstadoConexion()) {
/* 144 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 146 */       HTMLSelectElement combo = this.pagHTML.getElementClase();
/* 147 */       llenarCombo(rsTGen, combo, "sis_grupos", "codigo", "descripcion", "1=1", "", true);
/*     */       
/* 149 */       rsTGen.close();
/*     */     } 
/* 151 */     rsPersonasArea.close();
/*     */ 
/*     */     
/* 154 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/* 155 */     comms.response.writeDOM(this.pagHTML);
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
/* 168 */     String _operacion = comms.request.getParameter("_operacion");
/* 169 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */ 
/*     */     
/* 172 */     int codigoArea = 0;
/*     */     try {
/* 174 */       codigoArea = Integer.parseInt(comms.request.getParameter("codigo"));
/*     */     }
/* 176 */     catch (Exception e) {
/* 177 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigo_area"));
/*     */     } 
/*     */     
/* 180 */     int codigoEmpleado = 0;
/*     */     try {
/* 182 */       codigoEmpleado = Integer.parseInt(comms.request.getParameter("codigoEmpleado"));
/*     */     }
/* 184 */     catch (Exception e) {
/* 185 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigo_empleado"));
/*     */     } 
/*     */     
/* 188 */     boolean rta = false;
/* 189 */     if (_operacion.equals("E")) {
/* 190 */       PersonasAreaDAO rsPersonasArea = new PersonasAreaDAO();
/* 191 */       if (!rsPersonasArea.getEstadoConexion()) {
/* 192 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 194 */       rta = rsPersonasArea.eliminarRegistro(codigoArea, codigoEmpleado, elUsuario);
/* 195 */       if (!rta) {
/* 196 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPersonasArea"));
/*     */       }
/* 198 */       rsPersonasArea.close();
/* 199 */       String sPagina = "AdmPersonasArea.po?_operacion=L&codigo=" + codigoArea;
/* 200 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/* 202 */     PersonasAreaDAO rsPersonasArea = new PersonasAreaDAO();
/* 203 */     if (!rsPersonasArea.getEstadoConexion()) {
/* 204 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 206 */     int clase = 0;
/*     */     try {
/* 208 */       clase = Integer.parseInt(comms.request.getParameter("clase"));
/*     */     }
/* 210 */     catch (Exception e) {
/* 211 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=clase"));
/*     */     } 
/* 213 */     String responsableArea = comms.request.getParameter("responsableArea");
/* 214 */     if (responsableArea == null) responsableArea = "N";
/*     */     
/* 216 */     String areaPrincipal = comms.request.getParameter("areaPrincipal");
/* 217 */     if (areaPrincipal == null) areaPrincipal = "N";
/*     */     
/* 219 */     rta = false;
/*     */     
/* 221 */     if (_operacion.equals("C")) {
/* 222 */       PersonasAreaDTO reg = rsPersonasArea.cargarRegistro(codigoArea, codigoEmpleado);
/* 223 */       if (reg != null) {
/* 224 */         _operacion = "M";
/*     */       }
/*     */     } 
/*     */     
/* 228 */     if (_operacion.equals("C")) {
/* 229 */       rta = rsPersonasArea.crearRegistro(codigoArea, codigoEmpleado, clase, responsableArea, areaPrincipal, elUsuario);
/*     */     } else {
/*     */       
/* 232 */       rta = rsPersonasArea.modificarRegistro(codigoArea, codigoEmpleado, clase, responsableArea, areaPrincipal, elUsuario);
/*     */     } 
/* 234 */     rsPersonasArea.close();
/* 235 */     if (!rta) {
/* 236 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPersonasArea"));
/*     */     }
/* 238 */     String sPagina = "AdmPersonasArea.po?_operacion=L&codigo=" + codigoArea;
/* 239 */     throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
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
/* 253 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 254 */     atrib.setValue(valor);
/* 255 */     return atrib;
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
/* 268 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 269 */     Element enlace = this.pagHTML.createElement("a");
/* 270 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 271 */     enlace.appendChild(hijo);
/* 272 */     Attr donde = this.pagHTML.createAttribute("href");
/* 273 */     donde.setValue(vinculo);
/* 274 */     enlace.setAttributeNode(donde);
/* 275 */     td.appendChild(enlace);
/* 276 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 277 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 287 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 288 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 289 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 290 */     return td;
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
/*     */   private void llenarCombo(TGeneralDAO rsTGen, HTMLSelectElement combo, String tabla, String codigo, String descripcion, String where, String defecto, boolean dejarBlanco) {
/* 309 */     if (dejarBlanco) {
/* 310 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 311 */       op.setValue("");
/* 312 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 313 */       combo.appendChild(op);
/*     */     } 
/*     */     
/* 316 */     rsTGen.cargarTodos(tabla, codigo, descripcion, where + " order by " + codigo);
/*     */     TGeneralDTO RegGeneral;
/* 318 */     while ((RegGeneral = rsTGen.next()) != null) {
/* 319 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 320 */       op.setValue("" + RegGeneral.getCodigo());
/* 321 */       op.appendChild(this.pagHTML.createTextNode(RegGeneral.getDescripcion()));
/* 322 */       if (defecto.equals(RegGeneral.getCodigo())) {
/* 323 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 324 */         escogida.setValue("on");
/* 325 */         op.setAttributeNode(escogida);
/*     */       } 
/* 327 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AdmPersonasArea.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */