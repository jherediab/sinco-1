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
/*     */ import sinco.presentation.NavegacionHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NavegacionHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLElement $element_Apellidos;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLElement $element_Fecha;
/*     */   private HTMLElement $element_Nombres;
/*     */   private HTMLElement $element_Usuario;
/*     */   public static final String CLASS_apellidos = "apellidos";
/*     */   public static final String CLASS_cb = "cb";
/*     */   public static final String CLASS_fecha = "fecha";
/*     */   public static final String CLASS_nombre2 = "nombre2";
/*     */   public static final String CLASS_usuario = "usuario";
/*  56 */   public static final Class XMLC_GENERATED_CLASS = NavegacionHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/Navegacion.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NavegacionHTML() {
/*  78 */     this(StandardDocumentLoader.getInstance());
/*     */     
/*  80 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   public NavegacionHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NavegacionHTML(NavegacionHTML src) {
/*  96 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/*  98 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 100 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NavegacionHTML(DocumentLoader loader, boolean buildDOM) {
/* 111 */     this.fDocumentLoader = loader;
/*     */     
/* 113 */     if (buildDOM)
/*     */     {
/* 115 */       buildDocument();
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
/* 127 */   public NavegacionHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 135 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 137 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 145 */     cloneDeepCheck(deep);
/*     */     
/* 147 */     return new NavegacionHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 155 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 163 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 172 */   public HTMLElement getElementApellidos() { return this.$element_Apellidos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 181 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 190 */   public HTMLElement getElementFecha() { return this.$element_Fecha; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 199 */   public HTMLElement getElementNombres() { return this.$element_Nombres; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 208 */   public HTMLElement getElementUsuario() { return this.$element_Usuario; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 217 */   public void setTextApellidos(String text) { doSetText(this.$element_Apellidos, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 226 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 235 */   public void setTextFecha(String text) { doSetText(this.$element_Fecha, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 244 */   public void setTextNombres(String text) { doSetText(this.$element_Nombres, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 253 */   public void setTextUsuario(String text) { doSetText(this.$element_Usuario, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 262 */     if (node.getNodeType() != 9)
/*     */     {
/* 264 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 268 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 272 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 274 */       int substStart = "$element_".length();
/*     */       
/* 276 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 278 */         Field f = fs[i];
/*     */         
/* 280 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 282 */           String id = f.getName().substring(substStart);
/*     */           
/* 284 */           Node idNode = doc.getElementById(id);
/*     */           
/* 286 */           if (idNode == null) {
/*     */             
/* 288 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 290 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 294 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 298 */     } catch (Exception e) {
/*     */       
/* 300 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\NavegacionHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */