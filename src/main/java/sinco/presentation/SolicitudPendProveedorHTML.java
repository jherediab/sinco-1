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
/*     */ import org.w3c.dom.html.HTMLScriptElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.presentation.SolicitudPendProveedorHTML;
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
/*     */ public class SolicitudPendProveedorHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLScriptElement $element_MostrarMenu;
/*     */   private HTMLTableSectionElement $element_Personas;
/*     */   private HTMLInputElement $element_SolPadre;
/*     */   private HTMLInputElement $element_Solicitud;
/*     */   private HTMLInputElement $element__operacion;
/*     */   public static final String CLASS_BOT = "BOT";
/*     */   public static final String CLASS_INP = "INP";
/*     */   public static final String CLASS_ca = "ca";
/*     */   public static final String CLASS_cb = "cb";
/*     */   public static final String CLASS_cf1 = "cf1";
/*     */   public static final String CLASS_cf2 = "cf2";
/*     */   public static final String CLASS_container = "container";
/*     */   public static final String NAME__operacion = "_operacion";
/*     */   public static final String NAME_solPadre = "solPadre";
/*     */   public static final String NAME_solicitud = "solicitud";
/*  83 */   public static final Class XMLC_GENERATED_CLASS = SolicitudPendProveedorHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/SolicitudPendProveedor.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SolicitudPendProveedorHTML() {
/* 105 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 107 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 115 */   public SolicitudPendProveedorHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SolicitudPendProveedorHTML(SolicitudPendProveedorHTML src) {
/* 123 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 125 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 127 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SolicitudPendProveedorHTML(DocumentLoader loader, boolean buildDOM) {
/* 138 */     this.fDocumentLoader = loader;
/*     */     
/* 140 */     if (buildDOM)
/*     */     {
/* 142 */       buildDocument();
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
/* 154 */   public SolicitudPendProveedorHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 162 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 164 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 172 */     cloneDeepCheck(deep);
/*     */     
/* 174 */     return new SolicitudPendProveedorHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 182 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 190 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 199 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 208 */   public HTMLScriptElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 217 */   public HTMLTableSectionElement getElementPersonas() { return this.$element_Personas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 226 */   public HTMLInputElement getElementSolPadre() { return this.$element_SolPadre; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 235 */   public HTMLInputElement getElementSolicitud() { return this.$element_Solicitud; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 244 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 253 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 262 */   public void setTextMostrarMenu(String text) { doSetText(this.$element_MostrarMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 271 */     if (node.getNodeType() != 9)
/*     */     {
/* 273 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 277 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 281 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 283 */       int substStart = "$element_".length();
/*     */       
/* 285 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 287 */         Field f = fs[i];
/*     */         
/* 289 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 291 */           String id = f.getName().substring(substStart);
/*     */           
/* 293 */           Node idNode = doc.getElementById(id);
/*     */           
/* 295 */           if (idNode == null) {
/*     */             
/* 297 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 299 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 303 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 307 */     } catch (Exception e) {
/*     */       
/* 309 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SolicitudPendProveedorHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */