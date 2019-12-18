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
/*     */ import org.w3c.dom.html.HTMLFormElement;
/*     */ import org.w3c.dom.html.HTMLScriptElement;
/*     */ import sinco.presentation.PedirNombrePersonaHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PedirNombrePersonaHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLFormElement $element_Ff;
/*     */   private HTMLScriptElement $element_MostrarMenu;
/*     */   public static final String CLASS_BOT = "BOT";
/*     */   public static final String CLASS_INP = "INP";
/*     */   public static final String CLASS_ca = "ca";
/*     */   public static final String CLASS_cb = "cb";
/*     */   public static final String CLASS_container = "container";
/*     */   public static final String CLASS_dat = "dat";
/*     */   public static final String CLASS_dat2 = "dat2";
/*     */   public static final String CLASS_tabf = "tabf";
/*     */   public static final String CLASS_tit = "tit";
/*     */   public static final String NAME_apellidos = "apellidos";
/*     */   public static final String NAME_ff = "ff";
/*     */   public static final String NAME_miBoton = "miBoton";
/*     */   public static final String NAME_nombres = "nombres";
/*     */   public static final String NAME_tipo = "tipo";
/*  97 */   public static final Class XMLC_GENERATED_CLASS = PedirNombrePersonaHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/PedirNombrePersona.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PedirNombrePersonaHTML() {
/* 119 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 121 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   public PedirNombrePersonaHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PedirNombrePersonaHTML(PedirNombrePersonaHTML src) {
/* 137 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 139 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 141 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PedirNombrePersonaHTML(DocumentLoader loader, boolean buildDOM) {
/* 152 */     this.fDocumentLoader = loader;
/*     */     
/* 154 */     if (buildDOM)
/*     */     {
/* 156 */       buildDocument();
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
/* 168 */   public PedirNombrePersonaHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 176 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 178 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 186 */     cloneDeepCheck(deep);
/*     */     
/* 188 */     return new PedirNombrePersonaHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 196 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 204 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 213 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 222 */   public HTMLFormElement getElementFf() { return this.$element_Ff; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 231 */   public HTMLScriptElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 240 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 249 */   public void setTextFf(String text) { doSetText(this.$element_Ff, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 258 */   public void setTextMostrarMenu(String text) { doSetText(this.$element_MostrarMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 267 */     if (node.getNodeType() != 9)
/*     */     {
/* 269 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 273 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 277 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 279 */       int substStart = "$element_".length();
/*     */       
/* 281 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 283 */         Field f = fs[i];
/*     */         
/* 285 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 287 */           String id = f.getName().substring(substStart);
/*     */           
/* 289 */           Node idNode = doc.getElementById(id);
/*     */           
/* 291 */           if (idNode == null) {
/*     */             
/* 293 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 295 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 299 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 303 */     } catch (Exception e) {
/*     */       
/* 305 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PedirNombrePersonaHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */