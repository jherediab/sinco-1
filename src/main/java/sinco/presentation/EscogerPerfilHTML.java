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
/*     */ import org.w3c.dom.html.HTMLLinkElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.presentation.EscogerPerfilHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EscogerPerfilHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLTableElement $element_Detalle;
/*     */   private HTMLLinkElement $element_Elcss;
/*     */   public static final String CLASS_cc = "cc";
/*     */   public static final String CLASS_cf1 = "cf1";
/*     */   public static final String CLASS_cf2 = "cf2";
/*     */   public static final String CLASS_container = "container";
/*     */   public static final String CLASS_tabf = "tabf";
/*     */   public static final String CLASS_tabla_header = "tabla_header";
/*     */   public static final String CLASS_tabw = "tabw";
/*  60 */   public static final Class XMLC_GENERATED_CLASS = EscogerPerfilHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/EscogerPerfil.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EscogerPerfilHTML() {
/*  82 */     this(StandardDocumentLoader.getInstance());
/*     */     
/*  84 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   public EscogerPerfilHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EscogerPerfilHTML(EscogerPerfilHTML src) {
/* 100 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 102 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 104 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EscogerPerfilHTML(DocumentLoader loader, boolean buildDOM) {
/* 115 */     this.fDocumentLoader = loader;
/*     */     
/* 117 */     if (buildDOM)
/*     */     {
/* 119 */       buildDocument();
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
/* 131 */   public EscogerPerfilHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 139 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 141 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 149 */     cloneDeepCheck(deep);
/*     */     
/* 151 */     return new EscogerPerfilHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 159 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 167 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 176 */   public HTMLTableElement getElementDetalle() { return this.$element_Detalle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 185 */   public HTMLLinkElement getElementElcss() { return this.$element_Elcss; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 194 */     if (node.getNodeType() != 9)
/*     */     {
/* 196 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 200 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 204 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 206 */       int substStart = "$element_".length();
/*     */       
/* 208 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 210 */         Field f = fs[i];
/*     */         
/* 212 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 214 */           String id = f.getName().substring(substStart);
/*     */           
/* 216 */           Node idNode = doc.getElementById(id);
/*     */           
/* 218 */           if (idNode == null) {
/*     */             
/* 220 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 222 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 226 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 230 */     } catch (Exception e) {
/*     */       
/* 232 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\EscogerPerfilHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */