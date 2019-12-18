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
/*     */ import org.w3c.dom.html.HTMLScriptElement;
/*     */ import sinco.presentation.GraficaIndicadoresEfectividadHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GraficaIndicadoresEfectividadHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLDivElement $element_Container;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   public static final String CLASS_cb = "cb";
/*  30 */   public static final Class XMLC_GENERATED_CLASS = GraficaIndicadoresEfectividadHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/GraficaIndicadoresEfectividad.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraficaIndicadoresEfectividadHTML() {
/*  52 */     this(StandardDocumentLoader.getInstance());
/*     */     
/*  54 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   public GraficaIndicadoresEfectividadHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraficaIndicadoresEfectividadHTML(GraficaIndicadoresEfectividadHTML src) {
/*  70 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/*  72 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/*  74 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraficaIndicadoresEfectividadHTML(DocumentLoader loader, boolean buildDOM) {
/*  85 */     this.fDocumentLoader = loader;
/*     */     
/*  87 */     if (buildDOM)
/*     */     {
/*  89 */       buildDocument();
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
/* 101 */   public GraficaIndicadoresEfectividadHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 109 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 111 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 119 */     cloneDeepCheck(deep);
/*     */     
/* 121 */     return new GraficaIndicadoresEfectividadHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 146 */   public HTMLDivElement getElementContainer() { return this.$element_Container; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 155 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 164 */   public void setTextContainer(String text) { doSetText(this.$element_Container, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 173 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 182 */     if (node.getNodeType() != 9)
/*     */     {
/* 184 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 188 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 192 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 194 */       int substStart = "$element_".length();
/*     */       
/* 196 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 198 */         Field f = fs[i];
/*     */         
/* 200 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 202 */           String id = f.getName().substring(substStart);
/*     */           
/* 204 */           Node idNode = doc.getElementById(id);
/*     */           
/* 206 */           if (idNode == null) {
/*     */             
/* 208 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 210 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 214 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 218 */     } catch (Exception e) {
/*     */       
/* 220 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\GraficaIndicadoresEfectividadHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */