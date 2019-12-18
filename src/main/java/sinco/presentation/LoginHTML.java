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
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import sinco.presentation.LoginHTML;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LoginHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLDivElement $element_DivTabla;
/*     */   private HTMLDivElement $element_Eco;
/*     */   private HTMLInputElement $element_H;
/*     */   private HTMLInputElement $element_L;
/*     */   private HTMLInputElement $element_P;
/*     */   private HTMLDivElement $element_TablaLogin;
/*     */   private HTMLDivElement $element_TituloHospital;
/*     */   public static final String CLASS_INP = "INP";
/*     */   public static final String CLASS_ca = "ca";
/*     */   public static final String CLASS_dat = "dat";
/*     */   public static final String CLASS_dat2 = "dat2";
/*     */   public static final String CLASS_eco = "eco";
/*     */   public static final String CLASS_tabp = "tabp";
/*     */   public static final String CLASS_tit = "tit";
/*     */   public static final String CLASS_tituloHospital = "tituloHospital";
/*     */   public static final String NAME_h = "h";
/*     */   public static final String NAME_l = "l";
/*     */   public static final String NAME_miBoton = "miBoton";
/*     */   public static final String NAME_miForma = "miForma";
/*     */   public static final String NAME_p = "p";
/*     */   public static final String NAME_t = "t";
/* 105 */   public static final Class XMLC_GENERATED_CLASS = LoginHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/Login.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LoginHTML() {
/* 127 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 129 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   public LoginHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LoginHTML(LoginHTML src) {
/* 145 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 147 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 149 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LoginHTML(DocumentLoader loader, boolean buildDOM) {
/* 160 */     this.fDocumentLoader = loader;
/*     */     
/* 162 */     if (buildDOM)
/*     */     {
/* 164 */       buildDocument();
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
/* 176 */   public LoginHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 184 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 186 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 194 */     cloneDeepCheck(deep);
/*     */     
/* 196 */     return new LoginHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 204 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 212 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 221 */   public HTMLDivElement getElementDivTabla() { return this.$element_DivTabla; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 230 */   public HTMLDivElement getElementEco() { return this.$element_Eco; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 239 */   public HTMLInputElement getElementH() { return this.$element_H; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 248 */   public HTMLInputElement getElementL() { return this.$element_L; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 257 */   public HTMLInputElement getElementP() { return this.$element_P; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 266 */   public HTMLDivElement getElementTablaLogin() { return this.$element_TablaLogin; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 275 */   public HTMLDivElement getElementTituloHospital() { return this.$element_TituloHospital; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 284 */   public void setTextDivTabla(String text) { doSetText(this.$element_DivTabla, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 293 */   public void setTextEco(String text) { doSetText(this.$element_Eco, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 302 */   public void setTextTablaLogin(String text) { doSetText(this.$element_TablaLogin, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 311 */   public void setTextTituloHospital(String text) { doSetText(this.$element_TituloHospital, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 320 */     if (node.getNodeType() != 9)
/*     */     {
/* 322 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 326 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 330 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 332 */       int substStart = "$element_".length();
/*     */       
/* 334 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 336 */         Field f = fs[i];
/*     */         
/* 338 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 340 */           String id = f.getName().substring(substStart);
/*     */           
/* 342 */           Node idNode = doc.getElementById(id);
/*     */           
/* 344 */           if (idNode == null) {
/*     */             
/* 346 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 348 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 352 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 356 */     } catch (Exception e) {
/*     */       
/* 358 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\LoginHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */