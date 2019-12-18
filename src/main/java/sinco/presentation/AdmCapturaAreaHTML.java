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
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLLinkElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableRowElement;
/*     */ import sinco.presentation.AdmCapturaAreaHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AdmCapturaAreaHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLSelectElement $element_Areas;
/*     */   private HTMLInputElement $element_BotonAgr;
/*     */   private HTMLInputElement $element_CodigoServicio;
/*     */   private HTMLInputElement $element_CodigoServicio2;
/*     */   private HTMLLinkElement $element_Elcss;
/*     */   private HTMLInputElement $element_Especializado;
/*     */   private HTMLInputElement $element_Especializado2;
/*     */   private HTMLTableRowElement $element_Funcionario;
/*     */   private HTMLSelectElement $element_Personas;
/*     */   private HTMLDivElement $element_TrAreas;
/*     */   private HTMLDivElement $element_TrNuevo;
/*     */   private HTMLInputElement $element__operacion;
/*     */   public static final String CLASS_BOT = "BOT";
/*     */   public static final String CLASS_INP = "INP";
/*     */   public static final String CLASS_ca = "ca";
/*     */   public static final String CLASS_cb = "cb";
/*     */   public static final String CLASS_container = "container";
/*     */   public static final String CLASS_dat = "dat";
/*     */   public static final String CLASS_dat2 = "dat2";
/*     */   public static final String CLASS_tabf = "tabf";
/*     */   public static final String CLASS_tit = "tit";
/*     */   public static final String NAME__operacion = "_operacion";
/*     */   public static final String NAME_areas = "areas";
/*     */   public static final String NAME_codigoServicio = "codigoServicio";
/*     */   public static final String NAME_descripcion = "descripcion";
/*     */   public static final String NAME_especializado = "especializado";
/*     */   public static final String NAME_miBoton = "miBoton";
/*     */   public static final String NAME_miForma1 = "miForma1";
/*     */   public static final String NAME_miForma2 = "miForma2";
/*     */   public static final String NAME_personas = "personas";
/* 135 */   public static final Class XMLC_GENERATED_CLASS = AdmCapturaAreaHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/AdmCapturaArea.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 146 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AdmCapturaAreaHTML() {
/* 157 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 159 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 167 */   public AdmCapturaAreaHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AdmCapturaAreaHTML(AdmCapturaAreaHTML src) {
/* 175 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 177 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 179 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AdmCapturaAreaHTML(DocumentLoader loader, boolean buildDOM) {
/* 190 */     this.fDocumentLoader = loader;
/*     */     
/* 192 */     if (buildDOM)
/*     */     {
/* 194 */       buildDocument();
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
/* 206 */   public AdmCapturaAreaHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 214 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 216 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 224 */     cloneDeepCheck(deep);
/*     */     
/* 226 */     return new AdmCapturaAreaHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 234 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 242 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 251 */   public HTMLSelectElement getElementAreas() { return this.$element_Areas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 260 */   public HTMLInputElement getElementBotonAgr() { return this.$element_BotonAgr; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 269 */   public HTMLInputElement getElementCodigoServicio() { return this.$element_CodigoServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 278 */   public HTMLInputElement getElementCodigoServicio2() { return this.$element_CodigoServicio2; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 287 */   public HTMLLinkElement getElementElcss() { return this.$element_Elcss; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 296 */   public HTMLInputElement getElementEspecializado() { return this.$element_Especializado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 305 */   public HTMLInputElement getElementEspecializado2() { return this.$element_Especializado2; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 314 */   public HTMLTableRowElement getElementFuncionario() { return this.$element_Funcionario; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 323 */   public HTMLSelectElement getElementPersonas() { return this.$element_Personas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 332 */   public HTMLDivElement getElementTrAreas() { return this.$element_TrAreas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 341 */   public HTMLDivElement getElementTrNuevo() { return this.$element_TrNuevo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 350 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 359 */   public void setTextTrAreas(String text) { doSetText(this.$element_TrAreas, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 368 */   public void setTextTrNuevo(String text) { doSetText(this.$element_TrNuevo, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 377 */     if (node.getNodeType() != 9)
/*     */     {
/* 379 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 383 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 387 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 389 */       int substStart = "$element_".length();
/*     */       
/* 391 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 393 */         Field f = fs[i];
/*     */         
/* 395 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 397 */           String id = f.getName().substring(substStart);
/*     */           
/* 399 */           Node idNode = doc.getElementById(id);
/*     */           
/* 401 */           if (idNode == null) {
/*     */             
/* 403 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 405 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 409 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 413 */     } catch (Exception e) {
/*     */       
/* 415 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AdmCapturaAreaHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */