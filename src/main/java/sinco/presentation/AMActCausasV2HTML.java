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
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import org.w3c.dom.html.HTMLTextAreaElement;
/*     */ import sinco.presentation.AMActCausasV2HTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AMActCausasV2HTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLTextAreaElement $element_Accion;
/*     */   private HTMLTextAreaElement $element_Beneficio;
/*     */   private HTMLInputElement $element_BfechaEstimadaTerminacion;
/*     */   private HTMLInputElement $element_Causa;
/*     */   private HTMLInputElement $element_CausaAr;
/*     */   private HTMLDivElement $element_Creacion;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLInputElement $element_Estado;
/*     */   private HTMLInputElement $element_FechaEstimadaTerminacion;
/*     */   private HTMLTableElement $element_MostrarMenu;
/*     */   private HTMLInputElement $element_Numero;
/*     */   private HTMLInputElement $element_NumeroAr;
/*     */   private HTMLElement $element_NumeroCausa;
/*     */   private HTMLTextAreaElement $element_Porque;
/*     */   private HTMLSelectElement $element_Responsable;
/*     */   private HTMLDivElement $element_TrAgregarArchivo;
/*     */   private HTMLInputElement $element__operacion;
/*     */   public static final String CLASS_BOT = "BOT";
/*     */   public static final String CLASS_INP = "INP";
/*     */   public static final String CLASS_ca = "ca";
/*     */   public static final String CLASS_cb = "cb";
/*     */   public static final String CLASS_container = "container";
/*     */   public static final String CLASS_dat = "dat";
/*     */   public static final String CLASS_dat2 = "dat2";
/*     */   public static final String CLASS_tabf = "tabf";
/*     */   public static final String CLASS_tabw = "tabw";
/*     */   public static final String CLASS_tit = "tit";
/*     */   public static final String NAME__operacion = "_operacion";
/*     */   public static final String NAME_accion = "accion";
/*     */   public static final String NAME_beneficio = "beneficio";
/*     */   public static final String NAME_causa = "causa";
/*     */   public static final String NAME_estado = "estado";
/*     */   public static final String NAME_fechaEstimadaTerminacion = "fechaEstimadaTerminacion";
/*     */   public static final String NAME_miBoton = "miBoton";
/*     */   public static final String NAME_miForma = "miForma";
/*     */   public static final String NAME_numero = "numero";
/*     */   public static final String NAME_porque = "porque";
/*     */   public static final String NAME_responsable = "responsable";
/*     */   public static final String NAME_txtContAccion = "txtContAccion";
/*     */   public static final String NAME_txtContBeneficio = "txtContBeneficio";
/*     */   public static final String NAME_txtContPorque = "txtContPorque";
/* 175 */   public static final Class XMLC_GENERATED_CLASS = AMActCausasV2HTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/AMActCausasV2.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 186 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AMActCausasV2HTML() {
/* 197 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 199 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 207 */   public AMActCausasV2HTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AMActCausasV2HTML(AMActCausasV2HTML src) {
/* 215 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 217 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 219 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AMActCausasV2HTML(DocumentLoader loader, boolean buildDOM) {
/* 230 */     this.fDocumentLoader = loader;
/*     */     
/* 232 */     if (buildDOM)
/*     */     {
/* 234 */       buildDocument();
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
/* 246 */   public AMActCausasV2HTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 254 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 256 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 264 */     cloneDeepCheck(deep);
/*     */     
/* 266 */     return new AMActCausasV2HTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 274 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 282 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 291 */   public HTMLTextAreaElement getElementAccion() { return this.$element_Accion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 300 */   public HTMLTextAreaElement getElementBeneficio() { return this.$element_Beneficio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 309 */   public HTMLInputElement getElementBfechaEstimadaTerminacion() { return this.$element_BfechaEstimadaTerminacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 318 */   public HTMLInputElement getElementCausa() { return this.$element_Causa; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 327 */   public HTMLInputElement getElementCausaAr() { return this.$element_CausaAr; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 336 */   public HTMLDivElement getElementCreacion() { return this.$element_Creacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 345 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 354 */   public HTMLInputElement getElementEstado() { return this.$element_Estado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 363 */   public HTMLInputElement getElementFechaEstimadaTerminacion() { return this.$element_FechaEstimadaTerminacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 372 */   public HTMLTableElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 381 */   public HTMLInputElement getElementNumero() { return this.$element_Numero; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 390 */   public HTMLInputElement getElementNumeroAr() { return this.$element_NumeroAr; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 399 */   public HTMLElement getElementNumeroCausa() { return this.$element_NumeroCausa; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 408 */   public HTMLTextAreaElement getElementPorque() { return this.$element_Porque; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 417 */   public HTMLSelectElement getElementResponsable() { return this.$element_Responsable; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 426 */   public HTMLDivElement getElementTrAgregarArchivo() { return this.$element_TrAgregarArchivo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 435 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 444 */   public void setTextAccion(String text) { doSetText(this.$element_Accion, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 453 */   public void setTextBeneficio(String text) { doSetText(this.$element_Beneficio, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 462 */   public void setTextCreacion(String text) { doSetText(this.$element_Creacion, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 471 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 480 */   public void setTextNumeroCausa(String text) { doSetText(this.$element_NumeroCausa, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 489 */   public void setTextPorque(String text) { doSetText(this.$element_Porque, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 498 */   public void setTextTrAgregarArchivo(String text) { doSetText(this.$element_TrAgregarArchivo, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 507 */     if (node.getNodeType() != 9)
/*     */     {
/* 509 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 513 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 517 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 519 */       int substStart = "$element_".length();
/*     */       
/* 521 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 523 */         Field f = fs[i];
/*     */         
/* 525 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 527 */           String id = f.getName().substring(substStart);
/*     */           
/* 529 */           Node idNode = doc.getElementById(id);
/*     */           
/* 531 */           if (idNode == null) {
/*     */             
/* 533 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 535 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 539 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 543 */     } catch (Exception e) {
/*     */       
/* 545 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AMActCausasV2HTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */