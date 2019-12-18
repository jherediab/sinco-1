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
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import org.w3c.dom.html.HTMLTableRowElement;
/*     */ import sinco.presentation.AMCapturaAreasHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AMCapturaAreasHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLInputElement $element_BotonAgr;
/*     */   private HTMLTableElement $element_Detalle;
/*     */   private HTMLTableRowElement $element_TrAreas;
/*     */   private HTMLTableRowElement $element_TrNuevo;
/*     */   public static final String CLASS_BOT = "BOT";
/*     */   public static final String CLASS_CF2 = "CF2";
/*     */   public static final String CLASS_INP = "INP";
/*     */   public static final String CLASS_ca = "ca";
/*     */   public static final String CLASS_cb = "cb";
/*     */   public static final String CLASS_cf1 = "cf1";
/*     */   public static final String CLASS_container = "container";
/*     */   public static final String CLASS_dat2 = "dat2";
/*     */   public static final String NAME_clickcontrol1 = "clickcontrol1";
/*     */   public static final String NAME_descripcion = "descripcion";
/*     */   public static final String NAME_miBoton = "miBoton";
/*     */   public static final String NAME_miForma2 = "miForma2";
/*     */   public static final String NAME_miForma4 = "miForma4";
/*  94 */   public static final Class XMLC_GENERATED_CLASS = AMCapturaAreasHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/AMCapturaAreas.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AMCapturaAreasHTML() {
/* 116 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 118 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 126 */   public AMCapturaAreasHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AMCapturaAreasHTML(AMCapturaAreasHTML src) {
/* 134 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 136 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 138 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AMCapturaAreasHTML(DocumentLoader loader, boolean buildDOM) {
/* 149 */     this.fDocumentLoader = loader;
/*     */     
/* 151 */     if (buildDOM)
/*     */     {
/* 153 */       buildDocument();
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
/* 165 */   public AMCapturaAreasHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 173 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 175 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 183 */     cloneDeepCheck(deep);
/*     */     
/* 185 */     return new AMCapturaAreasHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 193 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 201 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 210 */   public HTMLInputElement getElementBotonAgr() { return this.$element_BotonAgr; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 219 */   public HTMLTableElement getElementDetalle() { return this.$element_Detalle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 228 */   public HTMLTableRowElement getElementTrAreas() { return this.$element_TrAreas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 237 */   public HTMLTableRowElement getElementTrNuevo() { return this.$element_TrNuevo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 246 */     if (node.getNodeType() != 9)
/*     */     {
/* 248 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 252 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 256 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 258 */       int substStart = "$element_".length();
/*     */       
/* 260 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 262 */         Field f = fs[i];
/*     */         
/* 264 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 266 */           String id = f.getName().substring(substStart);
/*     */           
/* 268 */           Node idNode = doc.getElementById(id);
/*     */           
/* 270 */           if (idNode == null) {
/*     */             
/* 272 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 274 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 278 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 282 */     } catch (Exception e) {
/*     */       
/* 284 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AMCapturaAreasHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */