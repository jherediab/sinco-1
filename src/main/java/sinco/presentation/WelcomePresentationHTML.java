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
/*     */ import sinco.presentation.WelcomePresentationHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WelcomePresentationHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   public static final String NAME_cabecera = "cabecera";
/*     */   public static final String NAME_principal = "principal";
/*  31 */   public static final Class XMLC_GENERATED_CLASS = WelcomePresentationHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/WelcomePresentation.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WelcomePresentationHTML() {
/*  53 */     this(StandardDocumentLoader.getInstance());
/*     */     
/*  55 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   public WelcomePresentationHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WelcomePresentationHTML(WelcomePresentationHTML src) {
/*  71 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/*  73 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/*  75 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WelcomePresentationHTML(DocumentLoader loader, boolean buildDOM) {
/*  86 */     this.fDocumentLoader = loader;
/*     */     
/*  88 */     if (buildDOM)
/*     */     {
/*  90 */       buildDocument();
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
/* 102 */   public WelcomePresentationHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 110 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 112 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 120 */     cloneDeepCheck(deep);
/*     */     
/* 122 */     return new WelcomePresentationHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 147 */     if (node.getNodeType() != 9)
/*     */     {
/* 149 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 153 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 157 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 159 */       int substStart = "$element_".length();
/*     */       
/* 161 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 163 */         Field f = fs[i];
/*     */         
/* 165 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 167 */           String id = f.getName().substring(substStart);
/*     */           
/* 169 */           Node idNode = doc.getElementById(id);
/*     */           
/* 171 */           if (idNode == null) {
/*     */             
/* 173 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 175 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 179 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 183 */     } catch (Exception e) {
/*     */       
/* 185 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\WelcomePresentationHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */