/*     */ package sinco.presentation.media.jscolor;
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
/*     */ import sinco.presentation.media.jscolor.demoHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class demoHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   public static final String CLASS_color = "color";
/*  26 */   public static final Class XMLC_GENERATED_CLASS = demoHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/media/jscolor/demo.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  37 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public demoHTML() {
/*  48 */     this(StandardDocumentLoader.getInstance());
/*     */     
/*  50 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   public demoHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public demoHTML(demoHTML src) {
/*  66 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/*  68 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/*  70 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public demoHTML(DocumentLoader loader, boolean buildDOM) {
/*  81 */     this.fDocumentLoader = loader;
/*     */     
/*  83 */     if (buildDOM)
/*     */     {
/*  85 */       buildDocument();
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
/*  97 */   public demoHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 105 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 107 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 115 */     cloneDeepCheck(deep);
/*     */     
/* 117 */     return new demoHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 125 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 133 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 142 */     if (node.getNodeType() != 9)
/*     */     {
/* 144 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 148 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 152 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 154 */       int substStart = "$element_".length();
/*     */       
/* 156 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 158 */         Field f = fs[i];
/*     */         
/* 160 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 162 */           String id = f.getName().substring(substStart);
/*     */           
/* 164 */           Node idNode = doc.getElementById(id);
/*     */           
/* 166 */           if (idNode == null) {
/*     */             
/* 168 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 170 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 174 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 178 */     } catch (Exception e) {
/*     */       
/* 180 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\media\jscolor\demoHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */