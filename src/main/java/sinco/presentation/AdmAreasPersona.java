/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLOptionElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.PersonasAreaDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.PersonasAreaDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.AdmAreasPersona;
/*     */ import sinco.presentation.AdmAreasPersonaHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AdmAreasPersona
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AdmAreasPersonaHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  34 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  35 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  38 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  39 */     String elUsuario = "" + comms.session.getUser().getName();
/*     */     
/*  41 */     int codigoEmpleado = 0;
/*     */     try {
/*  43 */       codigoEmpleado = Integer.parseInt(comms.request.getParameter("codigoEmpleado"));
/*     */     }
/*  45 */     catch (Exception e) {
/*  46 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigo_area"));
/*     */     } 
/*     */ 
/*     */     
/*  50 */     String _operacion = comms.request.getParameter("_operacion");
/*  51 */     if (_operacion == null || _operacion.length() == 0) {
/*  52 */       _operacion = "L";
/*     */     }
/*     */     
/*  55 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  56 */       int codigoArea = 0;
/*     */       try {
/*  58 */         codigoArea = Integer.parseInt(comms.request.getParameter("codigoArea"));
/*     */       }
/*  60 */       catch (Exception e) {
/*  61 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoArea"));
/*     */       } 
/*     */       
/*  64 */       boolean rta = false;
/*  65 */       if (_operacion.equals("E")) {
/*  66 */         PersonasAreaDAO rsPersonasArea = new PersonasAreaDAO();
/*  67 */         if (!rsPersonasArea.getEstadoConexion()) {
/*  68 */           throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */         }
/*  70 */         rta = rsPersonasArea.eliminarRegistro(codigoArea, codigoEmpleado, elUsuario);
/*  71 */         if (!rta) {
/*  72 */           throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPersonasArea"));
/*     */         }
/*  74 */         rsPersonasArea.close();
/*  75 */         String sPagina = "AdmAreasPersona.po?_operacion=L&codigoEmpleado=" + codigoEmpleado;
/*  76 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */       } 
/*  78 */       PersonasAreaDAO rsPersonasArea = new PersonasAreaDAO();
/*  79 */       if (!rsPersonasArea.getEstadoConexion()) {
/*  80 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*  82 */       int clase = 0;
/*     */       try {
/*  84 */         clase = Integer.parseInt(comms.request.getParameter("clase"));
/*     */       }
/*  86 */       catch (Exception e) {
/*  87 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=clase"));
/*     */       } 
/*  89 */       String responsableArea = comms.request.getParameter("responsableArea");
/*  90 */       if (responsableArea == null) responsableArea = "N";
/*     */       
/*  92 */       String areaPrincipal = comms.request.getParameter("areaPrincipal");
/*  93 */       if (areaPrincipal == null) areaPrincipal = "N";
/*     */       
/*  95 */       rta = false;
/*  96 */       if (_operacion.equals("C")) {
/*  97 */         rta = rsPersonasArea.crearRegistro(codigoArea, codigoEmpleado, clase, responsableArea, areaPrincipal, elUsuario);
/*     */       } else {
/*     */         
/* 100 */         rta = rsPersonasArea.modificarRegistro(codigoArea, codigoEmpleado, clase, responsableArea, areaPrincipal, elUsuario);
/*     */       } 
/* 102 */       rsPersonasArea.close();
/* 103 */       if (!rta) {
/* 104 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorPersonasArea"));
/*     */       }
/* 106 */       String sPagina = "AdmAreasPersona.po?_operacion=L&codigoEmpleado=" + codigoEmpleado;
/* 107 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/*     */ 
/*     */     
/* 111 */     this.pagHTML = (AdmAreasPersonaHTML)comms.xmlcFactory.create(AdmAreasPersonaHTML.class);
/*     */ 
/*     */     
/* 114 */     this.pagHTML.getElementCodigoEmpleado().setValue("" + codigoEmpleado);
/* 115 */     SisUsuariosDAO rsPersonas = new SisUsuariosDAO();
/* 116 */     SisUsuariosDTO regPersona = rsPersonas.cargarRegistro(codigoEmpleado);
/* 117 */     if (regPersona != null) {
/* 118 */       this.pagHTML.getElementNombreFuncionario().setValue("" + regPersona.getNombre());
/*     */     }
/*     */ 
/*     */     
/* 122 */     PersonasAreaDAO rsPersonasArea = new PersonasAreaDAO();
/* 123 */     if (!rsPersonasArea.getEstadoConexion()) {
/* 124 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 126 */     rsPersonasArea.cargarDePersona(codigoEmpleado);
/* 127 */     PersonasAreaDTO reg = rsPersonasArea.next();
/* 128 */     HTMLTableElement hte = this.pagHTML.getElementDetalle();
/* 129 */     boolean fondo = true;
/* 130 */     while (reg != null) {
/* 131 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 132 */       fondo = !fondo;
/* 133 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/* 134 */       eltr.appendChild(newtdhref("" + reg.getNombreArea(), "AdmAreasPersona.po?_operacion=P&codigoArea=" + reg.getCodigoArea() + "&codigoEmpleado=" + reg.getCodigoEmpleado()));
/* 135 */       eltr.appendChild(newtd("" + reg.getNombreClase()));
/* 136 */       eltr.appendChild(newtd("" + (reg.getResponsableArea().equals("S") ? "SI" : "NO")));
/* 137 */       eltr.appendChild(newtd("" + (reg.getAreaPrincipal().equals("S") ? "SI" : "NO")));
/* 138 */       hte.appendChild(eltr);
/* 139 */       reg = rsPersonasArea.next();
/*     */     } 
/*     */     
/* 142 */     if (_operacion.equals("P")) {
/* 143 */       int codigoArea = 0;
/*     */       try {
/* 145 */         codigoArea = Integer.parseInt(comms.request.getParameter("codigoArea"));
/*     */       }
/* 147 */       catch (Exception e) {
/* 148 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigoArea"));
/*     */       } 
/*     */       
/* 151 */       reg = rsPersonasArea.cargarRegistro(codigoArea, codigoEmpleado);
/* 152 */       if (reg != null) {
/* 153 */         TGeneralDAO rsTGen = new TGeneralDAO();
/*     */         
/* 155 */         if (!rsTGen.getEstadoConexion()) {
/* 156 */           throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */         }
/*     */         
/* 159 */         HTMLSelectElement combo = this.pagHTML.getElementClase();
/* 160 */         llenarCombo(rsTGen, combo, "sis_grupos", "codigo", "descripcion", "1=1", "" + reg.getClase());
/*     */         
/* 162 */         if (reg.getResponsableArea().equals("S")) {
/* 163 */           this.pagHTML.getElementResponsableArea().setChecked(true);
/*     */         }
/*     */         
/* 166 */         if (reg.getAreaPrincipal().equals("S")) {
/* 167 */           this.pagHTML.getElementAreaPrincipal().setChecked(true);
/*     */         }
/*     */ 
/*     */         
/* 171 */         combo = this.pagHTML.getElementCodigoArea();
/* 172 */         llenarCombo(rsTGen, combo, "unidades_dependencia", "codigo", "descripcion", "codigo=" + reg.getCodigoArea(), "" + reg.getCodigoArea());
/*     */         
/* 174 */         this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 175 */         this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 176 */         this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 177 */         this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 178 */         this.pagHTML.getElement_operacion().setValue("M");
/* 179 */         rsTGen.close();
/*     */       } 
/*     */     } else {
/*     */       
/* 183 */       HTMLElement sel = this.pagHTML.getElementEliminarRegistro();
/* 184 */       sel.getParentNode().removeChild(sel);
/*     */       
/* 186 */       TGeneralDAO rsTGen = new TGeneralDAO();
/* 187 */       if (!rsTGen.getEstadoConexion()) {
/* 188 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/* 190 */       HTMLSelectElement combo = this.pagHTML.getElementClase();
/* 191 */       llenarCombo(rsTGen, combo, "sis_grupos", "codigo", "descripcion", "1=1", "");
/*     */ 
/*     */       
/* 194 */       combo = this.pagHTML.getElementCodigoArea();
/* 195 */       llenarCombo(rsTGen, combo, "unidades_dependencia U", "codigo", "descripcion", "estado='A' AND NOT EXISTS (SELECT 'X' FROM SIS_USUARIOS_AREA UA WHERE UA.CODIGO_AREA=U.CODIGO AND UA.CODIGO_EMPLEADO=" + codigoEmpleado + ")", "");
/*     */ 
/*     */       
/* 198 */       rsTGen.close();
/*     */     } 
/* 200 */     rsPersonasArea.close();
/*     */ 
/*     */     
/* 203 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/* 204 */     comms.response.writeDOM(this.pagHTML);
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
/* 217 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 218 */     atrib.setValue(valor);
/* 219 */     return atrib;
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
/* 232 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 233 */     Element enlace = this.pagHTML.createElement("a");
/* 234 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 235 */     enlace.appendChild(hijo);
/* 236 */     Attr donde = this.pagHTML.createAttribute("href");
/* 237 */     donde.setValue(vinculo);
/* 238 */     enlace.setAttributeNode(donde);
/* 239 */     td.appendChild(enlace);
/* 240 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 241 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 251 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 252 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 253 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 254 */     return td;
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
/*     */   private void llenarCombo(TGeneralDAO rsTGen, HTMLSelectElement combo, String tabla, String codigo, String descripcion, String where, String defecto) {
/* 272 */     rsTGen.cargarTodos(tabla, codigo, descripcion, where + " order by " + codigo);
/*     */     TGeneralDTO RegGeneral;
/* 274 */     while ((RegGeneral = rsTGen.next()) != null) {
/* 275 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 276 */       op.setValue("" + RegGeneral.getCodigo());
/* 277 */       op.appendChild(this.pagHTML.createTextNode(RegGeneral.getDescripcion()));
/* 278 */       if (defecto.equals(RegGeneral.getCodigo())) {
/* 279 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 280 */         escogida.setValue("on");
/* 281 */         op.setAttributeNode(escogida);
/*     */       } 
/* 283 */       combo.appendChild(op);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AdmAreasPersona.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */