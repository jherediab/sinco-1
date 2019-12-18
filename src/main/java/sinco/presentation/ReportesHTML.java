/*     */ package sinco.presentation;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import org.enhydra.xml.xmlc.XMLCRuntimeException;
/*     */ import org.enhydra.xml.xmlc.XMLObject;
/*     */ import org.enhydra.xml.xmlc.deferredparsing.DocumentLoader;
/*     */ import org.enhydra.xml.xmlc.deferredparsing.StandardDocumentLoader;
/*     */ import org.enhydra.xml.xmlc.dom.XMLCDomFactory;
/*     */ import org.enhydra.xml.xmlc.dom.XMLCDomFactoryCache;
/*     */ import org.enhydra.xml.xmlc.html.HTMLObject;
/*     */ import org.enhydra.xml.xmlc.html.HTMLObjectImpl;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLDivElement;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLLIElement;
/*     */ import org.w3c.dom.html.HTMLScriptElement;
/*     */ import sinco.presentation.ReportesHTML;
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
/*     */ public class ReportesHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLDivElement $element_Borde;
/*     */   private HTMLDivElement $element_Contenedor;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLScriptElement $element_MostrarMenu;
/*     */   private HTMLElement $element_Nombre;
/*     */   private HTMLLIElement $element_RepAdministrador;
/*     */   private HTMLLIElement $element_RepAreasAuditadas;
/*     */   private HTMLLIElement $element_RepAuditores;
/*     */   private HTMLLIElement $element_RepAuditorias;
/*     */   private HTMLLIElement $element_RepCalificacion;
/*     */   private HTMLLIElement $element_RepCiclosAuditoria;
/*     */   private HTMLLIElement $element_RepConsultaGenerica;
/*     */   private HTMLLIElement $element_RepConsultaPlanes;
/*     */   private HTMLLIElement $element_RepEscalamientos;
/*     */   private HTMLLIElement $element_RepEspecifico;
/*     */   private HTMLLIElement $element_RepEstadisticasDigalo;
/*     */   private HTMLLIElement $element_RepEstadistico;
/*     */   private HTMLLIElement $element_RepIndiceAreas;
/*     */   private HTMLLIElement $element_RepIndicePersonal;
/*     */   private HTMLLIElement $element_RepIndiceSatisfaccion;
/*     */   private HTMLLIElement $element_RepMeAuditaron;
/*     */   private HTMLLIElement $element_RepPromedioServicios;
/*     */   private HTMLLIElement $element_RepReportesArea;
/*     */   private HTMLLIElement $element_RepServicios;
/*     */   private HTMLLIElement $element_RepServiciosPorArea;
/*     */   private HTMLLIElement $element_RepServiciosPorPersona;
/*     */   private HTMLLIElement $element_RepSolicitudesAtendidas;
/*     */   private HTMLLIElement $element_RepSolicitudesPorNumero;
/*     */   public static final String CLASS_cabecera = "cabecera";
/*     */   public static final String CLASS_cb = "cb";
/*     */   public static final String CLASS_desplegable = "desplegable";
/*     */   public static final String CLASS_navegador = "navegador";
/*     */   public static final String CLASS_nombre = "nombre";
/*     */   public static final String CLASS_subnavegador = "subnavegador";
/* 107 */   public static final Class XMLC_GENERATED_CLASS = ReportesHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/Reportes.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ReportesHTML() {
/* 129 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 131 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 139 */   public ReportesHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ReportesHTML(ReportesHTML src) {
/* 147 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 149 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 151 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ReportesHTML(DocumentLoader loader, boolean buildDOM) {
/* 162 */     this.fDocumentLoader = loader;
/*     */     
/* 164 */     if (buildDOM)
/*     */     {
/* 166 */       buildDocument();
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
/* 178 */   public ReportesHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 186 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 188 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 196 */     cloneDeepCheck(deep);
/*     */     
/* 198 */     return new ReportesHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 206 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 214 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 223 */   public HTMLDivElement getElementBorde() { return this.$element_Borde; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 232 */   public HTMLDivElement getElementContenedor() { return this.$element_Contenedor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 241 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 250 */   public HTMLScriptElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 259 */   public HTMLElement getElementNombre() { return this.$element_Nombre; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 268 */   public HTMLLIElement getElementRepAdministrador() { return this.$element_RepAdministrador; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 277 */   public HTMLLIElement getElementRepAreasAuditadas() { return this.$element_RepAreasAuditadas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 286 */   public HTMLLIElement getElementRepAuditores() { return this.$element_RepAuditores; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 295 */   public HTMLLIElement getElementRepAuditorias() { return this.$element_RepAuditorias; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 304 */   public HTMLLIElement getElementRepCalificacion() { return this.$element_RepCalificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 313 */   public HTMLLIElement getElementRepCiclosAuditoria() { return this.$element_RepCiclosAuditoria; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 322 */   public HTMLLIElement getElementRepConsultaGenerica() { return this.$element_RepConsultaGenerica; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 331 */   public HTMLLIElement getElementRepConsultaPlanes() { return this.$element_RepConsultaPlanes; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 340 */   public HTMLLIElement getElementRepEscalamientos() { return this.$element_RepEscalamientos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 349 */   public HTMLLIElement getElementRepEspecifico() { return this.$element_RepEspecifico; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 358 */   public HTMLLIElement getElementRepEstadisticasDigalo() { return this.$element_RepEstadisticasDigalo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 367 */   public HTMLLIElement getElementRepEstadistico() { return this.$element_RepEstadistico; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 376 */   public HTMLLIElement getElementRepIndiceAreas() { return this.$element_RepIndiceAreas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 385 */   public HTMLLIElement getElementRepIndicePersonal() { return this.$element_RepIndicePersonal; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 394 */   public HTMLLIElement getElementRepIndiceSatisfaccion() { return this.$element_RepIndiceSatisfaccion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 403 */   public HTMLLIElement getElementRepMeAuditaron() { return this.$element_RepMeAuditaron; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 412 */   public HTMLLIElement getElementRepPromedioServicios() { return this.$element_RepPromedioServicios; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 421 */   public HTMLLIElement getElementRepReportesArea() { return this.$element_RepReportesArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 430 */   public HTMLLIElement getElementRepServicios() { return this.$element_RepServicios; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 439 */   public HTMLLIElement getElementRepServiciosPorArea() { return this.$element_RepServiciosPorArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 448 */   public HTMLLIElement getElementRepServiciosPorPersona() { return this.$element_RepServiciosPorPersona; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 457 */   public HTMLLIElement getElementRepSolicitudesAtendidas() { return this.$element_RepSolicitudesAtendidas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 466 */   public HTMLLIElement getElementRepSolicitudesPorNumero() { return this.$element_RepSolicitudesPorNumero; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 475 */   public void setTextBorde(String text) { doSetText(this.$element_Borde, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 484 */   public void setTextContenedor(String text) { doSetText(this.$element_Contenedor, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 493 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 502 */   public void setTextMostrarMenu(String text) { doSetText(this.$element_MostrarMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 511 */   public void setTextNombre(String text) { doSetText(this.$element_Nombre, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 520 */   public void setTextRepAdministrador(String text) { doSetText(this.$element_RepAdministrador, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 529 */   public void setTextRepAreasAuditadas(String text) { doSetText(this.$element_RepAreasAuditadas, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 538 */   public void setTextRepAuditores(String text) { doSetText(this.$element_RepAuditores, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 547 */   public void setTextRepAuditorias(String text) { doSetText(this.$element_RepAuditorias, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 556 */   public void setTextRepCalificacion(String text) { doSetText(this.$element_RepCalificacion, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 565 */   public void setTextRepCiclosAuditoria(String text) { doSetText(this.$element_RepCiclosAuditoria, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 574 */   public void setTextRepConsultaGenerica(String text) { doSetText(this.$element_RepConsultaGenerica, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 583 */   public void setTextRepConsultaPlanes(String text) { doSetText(this.$element_RepConsultaPlanes, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 592 */   public void setTextRepEscalamientos(String text) { doSetText(this.$element_RepEscalamientos, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 601 */   public void setTextRepEspecifico(String text) { doSetText(this.$element_RepEspecifico, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 610 */   public void setTextRepEstadisticasDigalo(String text) { doSetText(this.$element_RepEstadisticasDigalo, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 619 */   public void setTextRepEstadistico(String text) { doSetText(this.$element_RepEstadistico, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 628 */   public void setTextRepIndiceAreas(String text) { doSetText(this.$element_RepIndiceAreas, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 637 */   public void setTextRepIndicePersonal(String text) { doSetText(this.$element_RepIndicePersonal, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 646 */   public void setTextRepIndiceSatisfaccion(String text) { doSetText(this.$element_RepIndiceSatisfaccion, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 655 */   public void setTextRepMeAuditaron(String text) { doSetText(this.$element_RepMeAuditaron, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 664 */   public void setTextRepPromedioServicios(String text) { doSetText(this.$element_RepPromedioServicios, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 673 */   public void setTextRepReportesArea(String text) { doSetText(this.$element_RepReportesArea, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 682 */   public void setTextRepServicios(String text) { doSetText(this.$element_RepServicios, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 691 */   public void setTextRepServiciosPorArea(String text) { doSetText(this.$element_RepServiciosPorArea, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 700 */   public void setTextRepServiciosPorPersona(String text) { doSetText(this.$element_RepServiciosPorPersona, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 709 */   public void setTextRepSolicitudesAtendidas(String text) { doSetText(this.$element_RepSolicitudesAtendidas, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 718 */   public void setTextRepSolicitudesPorNumero(String text) { doSetText(this.$element_RepSolicitudesPorNumero, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 727 */     if (node.getNodeType() != 9)
/*     */     {
/* 729 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 733 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 737 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 739 */       int substStart = "$element_".length();
/*     */       
/* 741 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 743 */         Field f = fs[i];
/*     */         
/* 745 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 747 */           String id = f.getName().substring(substStart);
/*     */           
/* 749 */           Node idNode = doc.getElementById(id);
/*     */           
/* 751 */           if (idNode == null) {
/*     */             
/* 753 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 755 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 759 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 763 */     } catch (Exception e) {
/*     */       
/* 765 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ReportesHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */