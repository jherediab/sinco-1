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
/*     */ import org.w3c.dom.html.HTMLDivElement;
/*     */ import org.w3c.dom.html.HTMLScriptElement;
/*     */ import sinco.presentation.PrincipalHTML;
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
/*     */ public class PrincipalHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLDivElement $element_Agenda;
/*     */   private HTMLDivElement $element_Borde;
/*     */   private HTMLAnchorElement $element_CPorAsignar;
/*     */   private HTMLAnchorElement $element_CPorAtender;
/*     */   private HTMLDivElement $element_Contenedor;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLAnchorElement $element_EnCurso;
/*     */   private HTMLAnchorElement $element_EscalanHoy;
/*     */   private HTMLScriptElement $element_MostrarMenu;
/*     */   private HTMLAnchorElement $element_NoEnviadas;
/*     */   private HTMLAnchorElement $element_NoEscalamientos;
/*     */   private HTMLAnchorElement $element_PorAtender;
/*     */   private HTMLAnchorElement $element_PorCalificar;
/*     */   public static final String CLASS_reference = "reference";
/*  52 */   public static final Class XMLC_GENERATED_CLASS = PrincipalHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/Principal.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PrincipalHTML() {
/*  74 */     this(StandardDocumentLoader.getInstance());
/*     */     
/*  76 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public PrincipalHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PrincipalHTML(PrincipalHTML src) {
/*  92 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/*  94 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/*  96 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PrincipalHTML(DocumentLoader loader, boolean buildDOM) {
/* 107 */     this.fDocumentLoader = loader;
/*     */     
/* 109 */     if (buildDOM)
/*     */     {
/* 111 */       buildDocument();
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
/* 123 */   public PrincipalHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 131 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 133 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 141 */     cloneDeepCheck(deep);
/*     */     
/* 143 */     return new PrincipalHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 151 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 159 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 168 */   public HTMLDivElement getElementAgenda() { return this.$element_Agenda; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 177 */   public HTMLDivElement getElementBorde() { return this.$element_Borde; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 186 */   public HTMLAnchorElement getElementCPorAsignar() { return this.$element_CPorAsignar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 195 */   public HTMLAnchorElement getElementCPorAtender() { return this.$element_CPorAtender; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 204 */   public HTMLDivElement getElementContenedor() { return this.$element_Contenedor; }
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
/* 222 */   public HTMLAnchorElement getElementEnCurso() { return this.$element_EnCurso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 231 */   public HTMLAnchorElement getElementEscalanHoy() { return this.$element_EscalanHoy; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 240 */   public HTMLScriptElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 249 */   public HTMLAnchorElement getElementNoEnviadas() { return this.$element_NoEnviadas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 258 */   public HTMLAnchorElement getElementNoEscalamientos() { return this.$element_NoEscalamientos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 267 */   public HTMLAnchorElement getElementPorAtender() { return this.$element_PorAtender; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 276 */   public HTMLAnchorElement getElementPorCalificar() { return this.$element_PorCalificar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 285 */   public void setTextAgenda(String text) { doSetText(this.$element_Agenda, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 294 */   public void setTextBorde(String text) { doSetText(this.$element_Borde, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 303 */   public void setTextCPorAsignar(String text) { doSetText(this.$element_CPorAsignar, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 312 */   public void setTextCPorAtender(String text) { doSetText(this.$element_CPorAtender, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 321 */   public void setTextContenedor(String text) { doSetText(this.$element_Contenedor, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 330 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 339 */   public void setTextEnCurso(String text) { doSetText(this.$element_EnCurso, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 348 */   public void setTextEscalanHoy(String text) { doSetText(this.$element_EscalanHoy, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 357 */   public void setTextMostrarMenu(String text) { doSetText(this.$element_MostrarMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 366 */   public void setTextNoEnviadas(String text) { doSetText(this.$element_NoEnviadas, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 375 */   public void setTextNoEscalamientos(String text) { doSetText(this.$element_NoEscalamientos, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 384 */   public void setTextPorAtender(String text) { doSetText(this.$element_PorAtender, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 393 */   public void setTextPorCalificar(String text) { doSetText(this.$element_PorCalificar, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 402 */     if (node.getNodeType() != 9)
/*     */     {
/* 404 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 408 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 412 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 414 */       int substStart = "$element_".length();
/*     */       
/* 416 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 418 */         Field f = fs[i];
/*     */         
/* 420 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 422 */           String id = f.getName().substring(substStart);
/*     */           
/* 424 */           Node idNode = doc.getElementById(id);
/*     */           
/* 426 */           if (idNode == null) {
/*     */             
/* 428 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 430 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 434 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 438 */     } catch (Exception e) {
/*     */       
/* 440 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PrincipalHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */