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
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLScriptElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.presentation.ServiciosDeFuncionarioHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ServiciosDeFuncionarioHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLScriptElement $element_MostrarMenu;
/*     */   private HTMLElement $element_NombreArea;
/*     */   private HTMLElement $element_NroRegistros;
/*     */   private HTMLTableElement $element_Servicios;
/*     */   public static final String CLASS_PIE = "PIE";
/*     */   public static final String CLASS_ca = "ca";
/*     */   public static final String CLASS_cb = "cb";
/*     */   public static final String CLASS_cf1 = "cf1";
/*     */   public static final String CLASS_cf2 = "cf2";
/*     */   public static final String CLASS_container = "container";
/*     */   public static final String CLASS_tabf = "tabf";
/*  66 */   public static final Class XMLC_GENERATED_CLASS = ServiciosDeFuncionarioHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/ServiciosDeFuncionario.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ServiciosDeFuncionarioHTML() {
/*  88 */     this(StandardDocumentLoader.getInstance());
/*     */     
/*  90 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   public ServiciosDeFuncionarioHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ServiciosDeFuncionarioHTML(ServiciosDeFuncionarioHTML src) {
/* 106 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 108 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 110 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ServiciosDeFuncionarioHTML(DocumentLoader loader, boolean buildDOM) {
/* 121 */     this.fDocumentLoader = loader;
/*     */     
/* 123 */     if (buildDOM)
/*     */     {
/* 125 */       buildDocument();
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
/* 137 */   public ServiciosDeFuncionarioHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 145 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 147 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 155 */     cloneDeepCheck(deep);
/*     */     
/* 157 */     return new ServiciosDeFuncionarioHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 165 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 173 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 182 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 191 */   public HTMLScriptElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 200 */   public HTMLElement getElementNombreArea() { return this.$element_NombreArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 209 */   public HTMLElement getElementNroRegistros() { return this.$element_NroRegistros; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 218 */   public HTMLTableElement getElementServicios() { return this.$element_Servicios; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 227 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 236 */   public void setTextMostrarMenu(String text) { doSetText(this.$element_MostrarMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 245 */   public void setTextNombreArea(String text) { doSetText(this.$element_NombreArea, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 254 */   public void setTextNroRegistros(String text) { doSetText(this.$element_NroRegistros, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 263 */     if (node.getNodeType() != 9)
/*     */     {
/* 265 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 269 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 273 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 275 */       int substStart = "$element_".length();
/*     */       
/* 277 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 279 */         Field f = fs[i];
/*     */         
/* 281 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 283 */           String id = f.getName().substring(substStart);
/*     */           
/* 285 */           Node idNode = doc.getElementById(id);
/*     */           
/* 287 */           if (idNode == null) {
/*     */             
/* 289 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 291 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 295 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 299 */     } catch (Exception e) {
/*     */       
/* 301 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ServiciosDeFuncionarioHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */