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
/*     */ import org.w3c.dom.html.HTMLAnchorElement;
/*     */ import org.w3c.dom.html.HTMLScriptElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.presentation.VerTipoSolicitudHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VerTipoSolicitudHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLTableElement $element_Detalle;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLAnchorElement $element_HelpDesk;
/*     */   private HTMLScriptElement $element_MostrarMenu;
/*     */   public static final String CLASS_ca = "ca";
/*     */   public static final String CLASS_cb = "cb";
/*     */   public static final String CLASS_cc = "cc";
/*     */   public static final String CLASS_container = "container";
/*     */   public static final String CLASS_ne = "ne";
/*     */   public static final String CLASS_tabf = "tabf";
/*  59 */   public static final Class XMLC_GENERATED_CLASS = VerTipoSolicitudHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/VerTipoSolicitud.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VerTipoSolicitudHTML() {
/*  81 */     this(StandardDocumentLoader.getInstance());
/*     */     
/*  83 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public VerTipoSolicitudHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VerTipoSolicitudHTML(VerTipoSolicitudHTML src) {
/*  99 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 101 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 103 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VerTipoSolicitudHTML(DocumentLoader loader, boolean buildDOM) {
/* 114 */     this.fDocumentLoader = loader;
/*     */     
/* 116 */     if (buildDOM)
/*     */     {
/* 118 */       buildDocument();
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
/* 130 */   public VerTipoSolicitudHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 138 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 140 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 148 */     cloneDeepCheck(deep);
/*     */     
/* 150 */     return new VerTipoSolicitudHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 158 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 166 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 175 */   public HTMLTableElement getElementDetalle() { return this.$element_Detalle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 184 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 193 */   public HTMLAnchorElement getElementHelpDesk() { return this.$element_HelpDesk; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 202 */   public HTMLScriptElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 211 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 220 */   public void setTextHelpDesk(String text) { doSetText(this.$element_HelpDesk, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 229 */   public void setTextMostrarMenu(String text) { doSetText(this.$element_MostrarMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 238 */     if (node.getNodeType() != 9)
/*     */     {
/* 240 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 244 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 248 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 250 */       int substStart = "$element_".length();
/*     */       
/* 252 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 254 */         Field f = fs[i];
/*     */         
/* 256 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 258 */           String id = f.getName().substring(substStart);
/*     */           
/* 260 */           Node idNode = doc.getElementById(id);
/*     */           
/* 262 */           if (idNode == null) {
/*     */             
/* 264 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 266 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 270 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 274 */     } catch (Exception e) {
/*     */       
/* 276 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\VerTipoSolicitudHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */