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
/*     */ import sinco.business.SisMultiValoresDTO;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.business.TGeneralDTO;
/*     */ import sinco.data.SisMultiValoresDAO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.data.TGeneralDAO;
/*     */ import sinco.presentation.AudVerAuditores;
/*     */ import sinco.presentation.AudVerAuditoresHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AudVerAuditores
/*     */   implements HttpPresentation
/*     */ {
/*     */   private AudVerAuditoresHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  39 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  40 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  43 */     String _operacion = comms.request.getParameter("_operacion");
/*  44 */     if (_operacion == null || _operacion.length() == 0) {
/*  45 */       _operacion = "X";
/*     */     }
/*     */     
/*  48 */     String tipoAuditor = comms.request.getParameter("tipoAuditor");
/*  49 */     if (tipoAuditor == null) {
/*  50 */       tipoAuditor = "";
/*     */     }
/*     */     
/*  53 */     String estado = comms.request.getParameter("estadoAuditor");
/*  54 */     if (estado == null) {
/*  55 */       estado = "";
/*     */     }
/*     */     
/*  58 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  60 */     this.pagHTML = (AudVerAuditoresHTML)comms.xmlcFactory.create(AudVerAuditoresHTML.class);
/*     */ 
/*     */ 
/*     */     
/*  64 */     HTMLSelectElement combo = this.pagHTML.getElementEstadoAuditor();
/*  65 */     comboMultivalores(combo, "ESTADO_REGISTRO", "" + estado, true);
/*     */ 
/*     */     
/*  68 */     combo = this.pagHTML.getElementTipoAuditor();
/*  69 */     comboMultivalores(combo, "AUT_TIPO_AUDITOR", "" + tipoAuditor, true);
/*     */ 
/*     */     
/*  72 */     if (_operacion.equals("L") || _operacion.equals("X")) {
/*  73 */       listar(comms, _operacion);
/*     */     }
/*     */     
/*  76 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  77 */     comms.response.writeDOM(this.pagHTML);
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
/*  88 */     activarVista("consulta");
/*     */ 
/*     */     
/*  91 */     if (_operacion.equals("X")) {
/*     */       return;
/*     */     }
/*     */     
/*  95 */     String tipoAuditor = comms.request.getParameter("tipoAuditor");
/*  96 */     if (tipoAuditor == null) {
/*  97 */       tipoAuditor = "";
/*     */     }
/*     */     
/* 100 */     String estado = comms.request.getParameter("estadoAuditor");
/* 101 */     if (estado == null) {
/* 102 */       estado = "";
/*     */     }
/*     */     
/* 105 */     SisUsuariosDAO ob = new SisUsuariosDAO();
/* 106 */     Collection<SisUsuariosDTO> arr = ob.cargarAuditores(tipoAuditor, estado);
/*     */ 
/*     */     
/* 109 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/* 110 */     int cuantas = 0;
/* 111 */     Iterator<SisUsuariosDTO> iterator = arr.iterator();
/* 112 */     while (iterator.hasNext()) {
/* 113 */       SisUsuariosDTO reg = (SisUsuariosDTO)iterator.next();
/* 114 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/* 115 */       eltr.appendChild(newtd("" + reg.getNombres()));
/*     */       
/* 117 */       eltr.appendChild(newtd("" + reg.getApellidos()));
/* 118 */       eltr.appendChild(newtd("" + reg.getCargoGenerico()));
/* 119 */       eltr.appendChild(newtd("" + reg.getAuditorCordinador()));
/* 120 */       eltr.appendChild(newtd("" + reg.getAuditorLider()));
/* 121 */       eltr.appendChild(newtd("" + reg.getNumeroAuditorias()));
/* 122 */       hte.appendChild(eltr);
/* 123 */       cuantas++;
/*     */     } 
/* 125 */     arr.clear();
/* 126 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/*     */   private void activarVista(String vista) {
/* 180 */     if (!vista.equals("consulta")) {
/* 181 */       HTMLElement sel = this.pagHTML.getElementResultados();
/* 182 */       sel.getParentNode().removeChild(sel);
/* 183 */       sel = this.pagHTML.getElementResultados();
/* 184 */       sel.getParentNode().removeChild(sel);
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
/*     */   private Attr newAttr(String tipo, String valor) {
/* 258 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 259 */     atrib.setValue(valor);
/* 260 */     return atrib;
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
/* 273 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 274 */     Element enlace = this.pagHTML.createElement("a");
/* 275 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 276 */     enlace.appendChild(hijo);
/* 277 */     Attr donde = this.pagHTML.createAttribute("href");
/* 278 */     donde.setValue(vinculo);
/* 279 */     enlace.setAttributeNode(donde);
/* 280 */     td.appendChild(enlace);
/* 281 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 282 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 292 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 293 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 294 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 295 */     return td;
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
/* 318 */     if (dejarBlanco) {
/* 319 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 320 */       op.setValue("");
/* 321 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 322 */       combo.appendChild(op);
/*     */     } 
/* 324 */     TGeneralDAO rsTGen = new TGeneralDAO();
/* 325 */     Collection<TGeneralDTO> arr = rsTGen.cargarTabla(tabla, codigo, descripcion, condicion);
/* 326 */     rsTGen.close();
/* 327 */     Iterator<TGeneralDTO> iterator = arr.iterator();
/* 328 */     while (iterator.hasNext()) {
/* 329 */       TGeneralDTO regGeneral = (TGeneralDTO)iterator.next();
/* 330 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 331 */       op.setValue("" + regGeneral.getCodigoS());
/* 332 */       op.appendChild(this.pagHTML.createTextNode(regGeneral.getDescripcion()));
/* 333 */       if (defecto != null && defecto.equals(regGeneral.getCodigoS())) {
/* 334 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 335 */         escogida.setValue("on");
/* 336 */         op.setAttributeNode(escogida);
/*     */       } 
/* 338 */       combo.appendChild(op);
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
/*     */   private void comboMultivalores(HTMLSelectElement combo, String tabla, String defecto, boolean dejarBlanco) {
/* 356 */     SisMultiValoresDAO ob = new SisMultiValoresDAO();
/* 357 */     Collection<SisMultiValoresDTO> arr = ob.cargarTabla(tabla);
/* 358 */     ob.close();
/* 359 */     if (dejarBlanco) {
/* 360 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 361 */       op.setValue("");
/* 362 */       op.appendChild(this.pagHTML.createTextNode(""));
/* 363 */       combo.appendChild(op);
/*     */     } 
/* 365 */     Iterator<SisMultiValoresDTO> iterator = arr.iterator();
/* 366 */     while (iterator.hasNext()) {
/* 367 */       SisMultiValoresDTO reg = (SisMultiValoresDTO)iterator.next();
/* 368 */       HTMLOptionElement op = (HTMLOptionElement)this.pagHTML.createElement("option");
/* 369 */       op.setValue("" + reg.getCodigo());
/* 370 */       op.appendChild(this.pagHTML.createTextNode(reg.getDescripcion()));
/* 371 */       if (defecto.equals(reg.getCodigo())) {
/* 372 */         Attr escogida = this.pagHTML.createAttribute("selected");
/* 373 */         escogida.setValue("on");
/* 374 */         op.setAttributeNode(escogida);
/*     */       } 
/* 376 */       combo.appendChild(op);
/*     */     } 
/* 378 */     arr.clear();
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AudVerAuditores.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */