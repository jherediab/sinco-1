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
/*     */ import sinco.presentation.RepIndiceSatPersonaHTML;
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
/*     */ public class RepIndiceSatPersonaHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLElement $element_ConBu;
/*     */   private HTMLElement $element_ConEx;
/*     */   private HTMLElement $element_ConIndSat;
/*     */   private HTMLElement $element_ConRe;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLElement $element_FechaFinal;
/*     */   private HTMLElement $element_FechaInicial;
/*     */   private HTMLElement $element_IdPersona;
/*     */   private HTMLElement $element_IdPorcentaje;
/*     */   private HTMLElement $element_Idservicio;
/*     */   private HTMLScriptElement $element_MostrarMenu;
/*     */   private HTMLElement $element_NumeroClientes;
/*     */   private HTMLElement $element_NumeroServicios;
/*     */   private HTMLElement $element_OpBu;
/*     */   private HTMLElement $element_OpEx;
/*     */   private HTMLElement $element_OpIndSat;
/*     */   private HTMLElement $element_OpRe;
/*     */   public static final String CLASS_ca = "ca";
/*     */   public static final String CLASS_cb = "cb";
/*     */   public static final String CLASS_cc = "cc";
/*     */   public static final String CLASS_cf1 = "cf1";
/*     */   public static final String CLASS_cf2 = "cf2";
/*     */   public static final String CLASS_container = "container";
/*     */   public static final String CLASS_dat = "dat";
/*     */   public static final String CLASS_datc = "datc";
/*     */   public static final String CLASS_tabf = "tabf";
/*     */   public static final String CLASS_tit = "tit";
/* 105 */   public static final Class XMLC_GENERATED_CLASS = RepIndiceSatPersonaHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/RepIndiceSatPersona.html";
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
/*     */   public RepIndiceSatPersonaHTML() {
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
/* 137 */   public RepIndiceSatPersonaHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RepIndiceSatPersonaHTML(RepIndiceSatPersonaHTML src) {
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
/*     */   public RepIndiceSatPersonaHTML(DocumentLoader loader, boolean buildDOM) {
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
/* 176 */   public RepIndiceSatPersonaHTML(DocumentLoader loader) { this(loader, true); }
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
/* 196 */     return new RepIndiceSatPersonaHTML(this);
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
/* 221 */   public HTMLElement getElementConBu() { return this.$element_ConBu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 230 */   public HTMLElement getElementConEx() { return this.$element_ConEx; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 239 */   public HTMLElement getElementConIndSat() { return this.$element_ConIndSat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 248 */   public HTMLElement getElementConRe() { return this.$element_ConRe; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 257 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 266 */   public HTMLElement getElementFechaFinal() { return this.$element_FechaFinal; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 275 */   public HTMLElement getElementFechaInicial() { return this.$element_FechaInicial; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 284 */   public HTMLElement getElementIdPersona() { return this.$element_IdPersona; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 293 */   public HTMLElement getElementIdPorcentaje() { return this.$element_IdPorcentaje; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 302 */   public HTMLElement getElementIdservicio() { return this.$element_Idservicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 311 */   public HTMLScriptElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 320 */   public HTMLElement getElementNumeroClientes() { return this.$element_NumeroClientes; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 329 */   public HTMLElement getElementNumeroServicios() { return this.$element_NumeroServicios; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 338 */   public HTMLElement getElementOpBu() { return this.$element_OpBu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 347 */   public HTMLElement getElementOpEx() { return this.$element_OpEx; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 356 */   public HTMLElement getElementOpIndSat() { return this.$element_OpIndSat; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 365 */   public HTMLElement getElementOpRe() { return this.$element_OpRe; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 374 */   public void setTextConBu(String text) { doSetText(this.$element_ConBu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 383 */   public void setTextConEx(String text) { doSetText(this.$element_ConEx, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 392 */   public void setTextConIndSat(String text) { doSetText(this.$element_ConIndSat, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 401 */   public void setTextConRe(String text) { doSetText(this.$element_ConRe, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 410 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 419 */   public void setTextFechaFinal(String text) { doSetText(this.$element_FechaFinal, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 428 */   public void setTextFechaInicial(String text) { doSetText(this.$element_FechaInicial, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 437 */   public void setTextIdPersona(String text) { doSetText(this.$element_IdPersona, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 446 */   public void setTextIdPorcentaje(String text) { doSetText(this.$element_IdPorcentaje, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 455 */   public void setTextIdservicio(String text) { doSetText(this.$element_Idservicio, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 464 */   public void setTextMostrarMenu(String text) { doSetText(this.$element_MostrarMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 473 */   public void setTextNumeroClientes(String text) { doSetText(this.$element_NumeroClientes, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 482 */   public void setTextNumeroServicios(String text) { doSetText(this.$element_NumeroServicios, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 491 */   public void setTextOpBu(String text) { doSetText(this.$element_OpBu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 500 */   public void setTextOpEx(String text) { doSetText(this.$element_OpEx, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 509 */   public void setTextOpIndSat(String text) { doSetText(this.$element_OpIndSat, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 518 */   public void setTextOpRe(String text) { doSetText(this.$element_OpRe, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 527 */     if (node.getNodeType() != 9)
/*     */     {
/* 529 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 533 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 537 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 539 */       int substStart = "$element_".length();
/*     */       
/* 541 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 543 */         Field f = fs[i];
/*     */         
/* 545 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 547 */           String id = f.getName().substring(substStart);
/*     */           
/* 549 */           Node idNode = doc.getElementById(id);
/*     */           
/* 551 */           if (idNode == null) {
/*     */             
/* 553 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 555 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 559 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 563 */     } catch (Exception e) {
/*     */       
/* 565 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\RepIndiceSatPersonaHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */