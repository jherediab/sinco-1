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
/*     */ import sinco.business.CaracteristicasDTO;
/*     */ import sinco.business.CaracteristicasServicioDTO;
/*     */ import sinco.business.RespuestaBD;
/*     */ import sinco.business.ServiciosDTO;
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.CaracteristicasDAO;
/*     */ import sinco.data.CaracteristicasServicioDAO;
/*     */ import sinco.data.ServiciosDAO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.AdmCaracteristicaServicio;
/*     */ import sinco.presentation.AdmCaracteristicaServicioHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AdmCaracteristicaServicio
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AdmCaracteristicaServicioHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  42 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  43 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  46 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*  47 */     String elUsuario = "" + comms.session.getUser().getName();
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
/*     */     
/*  63 */     if (_operacion.equals("C") || _operacion.equals("M") || _operacion.equals("E")) {
/*  64 */       int codigoCaracteristica = 0;
/*     */       try {
/*  66 */         codigoCaracteristica = Integer.parseInt(comms.request.getParameter("codigoCaracteristica"));
/*     */       }
/*  68 */       catch (Exception e) {
/*  69 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigo_caracteristica"));
/*     */       } 
/*     */       
/*  72 */       RespuestaBD rta = new RespuestaBD();
/*  73 */       if (_operacion.equals("E")) {
/*  74 */         CaracteristicasServicioDAO rsCaracteristicaServicio = new CaracteristicasServicioDAO();
/*  75 */         if (!rsCaracteristicaServicio.getEstadoConexion()) {
/*  76 */           throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */         }
/*  78 */         rta = rsCaracteristicaServicio.eliminarRegistro(codigoServicio, codigoCaracteristica);
/*  79 */         if (!rta.isRta()) {
/*  80 */           throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCaracteristicaServicio"));
/*     */         }
/*  82 */         rsCaracteristicaServicio.close();
/*  83 */         String sPagina = "AdmCaracteristicaServicio.po?_operacion=L&codigo=" + codigoServicio;
/*  84 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */       } 
/*  86 */       CaracteristicasServicioDAO rsCaracteristicaServicio = new CaracteristicasServicioDAO();
/*  87 */       if (!rsCaracteristicaServicio.getEstadoConexion()) {
/*  88 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */       }
/*  90 */       String rol = comms.request.getParameter("rol");
/*  91 */       String obligatoria = comms.request.getParameter("obligatoria");
/*  92 */       String estado = comms.request.getParameter("estado");
/*  93 */       if (obligatoria == null) obligatoria = "N"; 
/*  94 */       int indice = 0;
/*     */       try {
/*  96 */         indice = Integer.parseInt(comms.request.getParameter("indice"));
/*     */       }
/*  98 */       catch (Exception e) {
/*  99 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=indice"));
/*     */       } 
/*     */       
/* 102 */       if (_operacion.equals("C")) {
/* 103 */         rta = rsCaracteristicaServicio.crearRegistro(codigoServicio, codigoCaracteristica, rol, obligatoria, indice, estado, elUsuario);
/*     */       } else {
/*     */         
/* 106 */         rta = rsCaracteristicaServicio.modificarRegistro(codigoServicio, codigoCaracteristica, rol, obligatoria, indice, estado, elUsuario);
/*     */       } 
/* 108 */       rsCaracteristicaServicio.close();
/* 109 */       if (!rta.isRta()) {
/* 110 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ErrorCaracteristicaServicio"));
/*     */       }
/* 112 */       String sPagina = "AdmCaracteristicaServicio.po?_operacion=L&codigo=" + codigoServicio;
/* 113 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath(sPagina));
/*     */     } 
/*     */ 
/*     */     
/* 117 */     this.pagHTML = (AdmCaracteristicaServicioHTML)comms.xmlcFactory.create(AdmCaracteristicaServicioHTML.class);
/*     */     
/* 119 */     this.pagHTML.getElementCodigoServicio().setValue("" + codigoServicio);
/*     */     
/* 121 */     ServiciosDAO rsServicios = new ServiciosDAO();
/* 122 */     ServiciosDTO regServicio = rsServicios.cargarRegistro(codigoServicio);
/* 123 */     rsServicios.close();
/* 124 */     this.pagHTML.getElementNombreServicio().setValue("" + regServicio.getDescripcion());
/*     */     
/* 126 */     CaracteristicasServicioDAO rsCaracteristicaServicio = new CaracteristicasServicioDAO();
/* 127 */     if (!rsCaracteristicaServicio.getEstadoConexion()) {
/* 128 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=LoginNoValido"));
/*     */     }
/* 130 */     rsCaracteristicaServicio.cargarTodosParaServicio(codigoServicio, "F");
/* 131 */     CaracteristicasServicioDTO reg = rsCaracteristicaServicio.next();
/* 132 */     HTMLTableElement hte = this.pagHTML.getElementDetalle();
/* 133 */     boolean fondo = true;
/* 134 */     while (reg != null) {
/* 135 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 136 */       fondo = !fondo;
/* 137 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/* 138 */       eltr.appendChild(newtd("" + reg.getIndice()));
/* 139 */       eltr.appendChild(newtdhref("" + reg.getNombreCodigoCaracteristica(), "AdmCaracteristicaServicio.po?_operacion=P&codigo=" + reg.getCodigoServicio() + "&codigoCaracteristica=" + reg.getCodigoCaracteristica()));
/* 140 */       eltr.appendChild(newtd("" + (reg.getRol().equals("C") ? "Cliente" : "Proveedor")));
/* 141 */       eltr.appendChild(newtd("" + (reg.getObligatoria().equals("S") ? "SI" : "NO")));
/* 142 */       eltr.appendChild(newtd("" + reg.getEstado()));
/* 143 */       hte.appendChild(eltr);
/* 144 */       reg = rsCaracteristicaServicio.next();
/*     */     } 
/*     */     
/* 147 */     if (_operacion.equals("P")) {
/*     */       
/* 149 */       int codigoCaracteristica = 0;
/*     */       try {
/* 151 */         codigoCaracteristica = Integer.parseInt(comms.request.getParameter("codigoCaracteristica"));
/*     */       }
/* 153 */       catch (Exception e) {
/* 154 */         throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=ValorNoValido&p1=codigo_caracteristica"));
/*     */       } 
/*     */       
/* 157 */       reg = rsCaracteristicaServicio.cargarRegistro(codigoServicio, codigoCaracteristica);
/* 158 */       if (reg != null) {
/*     */         
/* 160 */         this.pagHTML.getElementObligatoria().setChecked(false);
/* 161 */         if (reg.getObligatoria().equals("S")) {
/* 162 */           this.pagHTML.getElementObligatoria().setChecked(true);
/*     */         }
/* 164 */         this.pagHTML.getElementIndice().setValue("" + reg.getIndice());
/*     */         
/* 166 */         this.pagHTML.getElementUsuarioInsercion().setValue("" + reg.getUsuarioInsercion());
/* 167 */         this.pagHTML.getElementFechaInsercion().setValue("" + reg.getFechaInsercion());
/* 168 */         this.pagHTML.getElementUsuarioModificacion().setValue("" + reg.getUsuarioModificacion());
/* 169 */         this.pagHTML.getElementFechaModificacion().setValue("" + reg.getFechaModificacion());
/* 170 */         this.pagHTML.getElement_operacion().setValue("M");
/*     */         
/* 172 */         HTMLSelectElement combo = this.pagHTML.getElementCodigoCaracteristica();
/* 173 */         comboCaracteristica(combo, codigoServicio, reg.getCodigoCaracteristica());
/*     */         
/* 175 */         combo = this.pagHTML.getElementRol();
/* 176 */         comboMultivalores(combo, "ROL_CARACTERISTICA_EN_SERVICIO", "" + reg.getRol(), false);
/*     */         
/* 178 */         combo = this.pagHTML.getElementEstado();
/* 179 */         comboMultivalores(combo, "ESTADO_REGISTRO", "" + reg.getEstado(), false);
/*     */       } 
/*     */     } else {
/*     */       
/* 183 */       HTMLElement sel = this.pagHTML.getElementEliminarRegistro();
/* 184 */       sel.getParentNode().removeChild(sel);
/*     */       
/* 186 */       HTMLSelectElement combo = this.pagHTML.getElementCodigoCaracteristica();
/* 187 */       comboCaracteristica(combo, codigoServicio, 0);
/*     */       
/* 189 */       combo = this.pagHTML.getElementRol();
/* 190 */       comboMultivalores(combo, "ROL_CARACTERISTICA_EN_SERVICIO", "C", false);
/*     */       
/* 192 */       combo = this.pagHTML.getElementEstado();
/* 193 */       comboMultivalores(combo, "ESTADO_REGISTRO", "A", false);
/*     */     } 
/*     */     
/* 196 */     rsCaracteristicaServicio.close();
/*     */ 
/*     */     
/* 199 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/* 200 */     comms.response.writeDOM(this.pagHTML);
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
/* 212 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 213 */     atrib.setValue(valor);
/* 214 */     return atrib;
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
/* 227 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 228 */     Element enlace = this.pagHTML.createElement("a");
/* 229 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 230 */     enlace.appendChild(hijo);
/* 231 */     Attr donde = this.pagHTML.createAttribute("href");
/* 232 */     donde.setValue(vinculo);
/* 233 */     enlace.setAttributeNode(donde);
/* 234 */     td.appendChild(enlace);
/* 235 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 236 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 246 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 247 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 248 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 249 */     return td;
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
/*     */   private boolean comboCaracteristica(HTMLSelectElement combo, int servicio, int caracteristica) {
/* 261 */     CaracteristicasDAO rsCar = new CaracteristicasDAO();
/* 262 */     if (!rsCar.getEstadoConexion()) {
/* 263 */       return false;
/*     */     }
/*     */     
/* 266 */     Collection<CaracteristicasDTO> arr = rsCar.cargarCaracteristicaServicio(servicio, caracteristica);
/*     */     
/* 268 */     rsCar.close();
/*     */     
/* 270 */     Iterator<CaracteristicasDTO> iterator = arr.iterator();
/* 271 */     while (iterator.hasNext()) {
/* 272 */       CaracteristicasDTO reg = (CaracteristicasDTO)iterator.next();
/* 273 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 274 */       op.setValue("" + reg.getCodigo());
/* 275 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 276 */       if (caracteristica == reg.getCodigo()) {
/* 277 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 278 */         escogida.setValue("on");
/* 279 */         op.setAttributeNode(escogida);
/*     */       } 
/* 281 */       combo.appendChild(op);
/*     */     } 
/* 283 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean comboRol(HTMLSelectElement combo, String rol) {
/* 293 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 294 */     if (!rsTGen.getEstadoConexion()) {
/* 295 */       return false;
/*     */     }
/*     */     
/* 298 */     rsTGen.cargarTodos("sis_multivalores", "valor", "descripcion", " tabla='ROL_CARACTERISTICA_EN_SERVICIO' order by 2");
/*     */     TGeneralDTO RegGeneral;
/* 300 */     while ((RegGeneral = rsTGen.next()) != null) {
/* 301 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 302 */       op.setValue("" + RegGeneral.getCodigo());
/* 303 */       op.appendChild(this.pagHTML.createTextNode(RegGeneral.getDescripcion()));
/* 304 */       if (rol.equals(RegGeneral.getCodigo())) {
/* 305 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 306 */         escogida.setValue("on");
/* 307 */         op.setAttributeNode(escogida);
/*     */       } 
/* 309 */       combo.appendChild(op);
/*     */     } 
/* 311 */     rsTGen.close();
/* 312 */     return true;
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
/*     */   private void comboMultivalores(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco) {
/* 330 */     SisMultiValoresDAO rs = new SisMultiValoresDAO();
/* 331 */     Collection<SisMultiValoresDTO> arr = rs.cargarTabla(tabla);
/* 332 */     rs.close();
/* 333 */     if (dejarBlanco) {
/* 334 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 335 */       op.setValue("");
/* 336 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 337 */       combo.appendChild(op);
/*     */     } 
/* 339 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 340 */     while (iterator.hasNext()) {
/* 341 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 342 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 343 */       op.setValue("" + reg.getCodigo());
/* 344 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 345 */       if (defecto.equals(reg.getCodigo())) {
/* 346 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 347 */         escogida.setValue("on");
/* 348 */         op.setAttributeNode(escogida);
/*     */       } 
/* 350 */       combo.appendChild(op);
/*     */     } 
/* 352 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AdmCaracteristicaServicio.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */