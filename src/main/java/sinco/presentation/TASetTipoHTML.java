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
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLScriptElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableRowElement;
/*     */ import sinco.presentation.TASetTipoHTML;
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
/*     */ public class TASetTipoHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLInputElement $element_Area;
/*     */   private HTMLSelectElement $element_AreaBA;
/*     */   private HTMLSelectElement $element_AreaBS;
/*     */   private HTMLSelectElement $element_AreaCliente;
/*     */   private HTMLDivElement $element_DivAreas;
/*     */   private HTMLDivElement $element_DivMensaje;
/*     */   private HTMLDivElement $element_DivServicios;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLInputElement $element_Encuesta;
/*     */   private HTMLInputElement $element_Filtro;
/*     */   private HTMLInputElement $element_FiltroServicio;
/*     */   private HTMLTableRowElement $element_IdEncuesta;
/*     */   private HTMLElement $element_Mensaje;
/*     */   private HTMLScriptElement $element_MostrarMenu;
/*     */   private HTMLInputElement $element_OptArea;
/*     */   private HTMLInputElement $element_OptServicio;
/*     */   private HTMLInputElement $element_Padre;
/*     */   private HTMLInputElement $element_Persona;
/*     */   private HTMLSelectElement $element_PersonaBA;
/*     */   private HTMLSelectElement $element_PersonaBS;
/*     */   private HTMLInputElement $element_Servicio;
/*     */   private HTMLSelectElement $element_ServicioBA;
/*     */   private HTMLSelectElement $element_ServicioBS;
/*     */   private HTMLInputElement $element_TipoSol;
/*     */   private HTMLTableRowElement $element_TrAreaCliente;
/*     */   private HTMLTableRowElement $element_TrPersona;
/*     */   private HTMLTableRowElement $element_TrServicio;
/*     */   public static final String CLASS_BOT = "BOT";
/*     */   public static final String CLASS_INP = "INP";
/*     */   public static final String CLASS_ca = "ca";
/*     */   public static final String CLASS_cb = "cb";
/*     */   public static final String CLASS_container = "container";
/*     */   public static final String CLASS_dat = "dat";
/*     */   public static final String CLASS_dat2 = "dat2";
/*     */   public static final String CLASS_error = "error";
/*     */   public static final String CLASS_tabf = "tabf";
/*     */   public static final String CLASS_tit = "tit";
/*     */   public static final String NAME__operacion = "_operacion";
/*     */   public static final String NAME_area = "area";
/*     */   public static final String NAME_areaBA = "areaBA";
/*     */   public static final String NAME_areaBS = "areaBS";
/*     */   public static final String NAME_areaCliente = "areaCliente";
/*     */   public static final String NAME_busqueda = "busqueda";
/*     */   public static final String NAME_encuesta = "encuesta";
/*     */   public static final String NAME_ff = "ff";
/*     */   public static final String NAME_filtro = "filtro";
/*     */   public static final String NAME_filtroServicio = "filtroServicio";
/*     */   public static final String NAME_padre = "padre";
/*     */   public static final String NAME_persona = "persona";
/*     */   public static final String NAME_personaBA = "personaBA";
/*     */   public static final String NAME_personaBS = "personaBS";
/*     */   public static final String NAME_servicio = "servicio";
/*     */   public static final String NAME_servicioBA = "servicioBA";
/*     */   public static final String NAME_servicioBS = "servicioBS";
/*     */   public static final String NAME_tipoSol = "tipoSol";
/* 215 */   public static final Class XMLC_GENERATED_CLASS = TASetTipoHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/TASetTipo.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 226 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TASetTipoHTML() {
/* 237 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 239 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 247 */   public TASetTipoHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TASetTipoHTML(TASetTipoHTML src) {
/* 255 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 257 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 259 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TASetTipoHTML(DocumentLoader loader, boolean buildDOM) {
/* 270 */     this.fDocumentLoader = loader;
/*     */     
/* 272 */     if (buildDOM)
/*     */     {
/* 274 */       buildDocument();
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
/* 286 */   public TASetTipoHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 294 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 296 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 304 */     cloneDeepCheck(deep);
/*     */     
/* 306 */     return new TASetTipoHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 314 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 322 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 331 */   public HTMLInputElement getElementArea() { return this.$element_Area; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 340 */   public HTMLSelectElement getElementAreaBA() { return this.$element_AreaBA; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 349 */   public HTMLSelectElement getElementAreaBS() { return this.$element_AreaBS; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 358 */   public HTMLSelectElement getElementAreaCliente() { return this.$element_AreaCliente; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 367 */   public HTMLDivElement getElementDivAreas() { return this.$element_DivAreas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 376 */   public HTMLDivElement getElementDivMensaje() { return this.$element_DivMensaje; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 385 */   public HTMLDivElement getElementDivServicios() { return this.$element_DivServicios; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 394 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 403 */   public HTMLInputElement getElementEncuesta() { return this.$element_Encuesta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 412 */   public HTMLInputElement getElementFiltro() { return this.$element_Filtro; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 421 */   public HTMLInputElement getElementFiltroServicio() { return this.$element_FiltroServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 430 */   public HTMLTableRowElement getElementIdEncuesta() { return this.$element_IdEncuesta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 439 */   public HTMLElement getElementMensaje() { return this.$element_Mensaje; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 448 */   public HTMLScriptElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 457 */   public HTMLInputElement getElementOptArea() { return this.$element_OptArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 466 */   public HTMLInputElement getElementOptServicio() { return this.$element_OptServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 475 */   public HTMLInputElement getElementPadre() { return this.$element_Padre; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 484 */   public HTMLInputElement getElementPersona() { return this.$element_Persona; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 493 */   public HTMLSelectElement getElementPersonaBA() { return this.$element_PersonaBA; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 502 */   public HTMLSelectElement getElementPersonaBS() { return this.$element_PersonaBS; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 511 */   public HTMLInputElement getElementServicio() { return this.$element_Servicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 520 */   public HTMLSelectElement getElementServicioBA() { return this.$element_ServicioBA; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 529 */   public HTMLSelectElement getElementServicioBS() { return this.$element_ServicioBS; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 538 */   public HTMLInputElement getElementTipoSol() { return this.$element_TipoSol; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 547 */   public HTMLTableRowElement getElementTrAreaCliente() { return this.$element_TrAreaCliente; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 556 */   public HTMLTableRowElement getElementTrPersona() { return this.$element_TrPersona; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 565 */   public HTMLTableRowElement getElementTrServicio() { return this.$element_TrServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 574 */   public void setTextDivAreas(String text) { doSetText(this.$element_DivAreas, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 583 */   public void setTextDivMensaje(String text) { doSetText(this.$element_DivMensaje, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 592 */   public void setTextDivServicios(String text) { doSetText(this.$element_DivServicios, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 601 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 610 */   public void setTextMensaje(String text) { doSetText(this.$element_Mensaje, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 619 */   public void setTextMostrarMenu(String text) { doSetText(this.$element_MostrarMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 628 */     if (node.getNodeType() != 9)
/*     */     {
/* 630 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 634 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 638 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 640 */       int substStart = "$element_".length();
/*     */       
/* 642 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 644 */         Field f = fs[i];
/*     */         
/* 646 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 648 */           String id = f.getName().substring(substStart);
/*     */           
/* 650 */           Node idNode = doc.getElementById(id);
/*     */           
/* 652 */           if (idNode == null) {
/*     */             
/* 654 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 656 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 660 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 664 */     } catch (Exception e) {
/*     */       
/* 666 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\TASetTipoHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */