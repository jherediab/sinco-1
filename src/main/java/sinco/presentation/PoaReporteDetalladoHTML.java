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
/*     */ import org.w3c.dom.html.HTMLLabelElement;
/*     */ import org.w3c.dom.html.HTMLScriptElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.presentation.PoaReporteDetalladoHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PoaReporteDetalladoHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLInputElement $element_BtnPrincipal;
/*     */   private HTMLDivElement $element_DivConsulta;
/*     */   private HTMLDivElement $element_DivTableroControl;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLSelectElement $element_Farea;
/*     */   private HTMLSelectElement $element_Fciclo;
/*     */   private HTMLSelectElement $element_Fmes;
/*     */   private HTMLSelectElement $element_FmesInicial;
/*     */   private HTMLSelectElement $element_Fproceso;
/*     */   private HTMLLabelElement $element_LabelNombre;
/*     */   private HTMLTableSectionElement $element_TablaTablero;
/*     */   private HTMLInputElement $element__operacion;
/*     */   public static final String CLASS_BOB = "BOB";
/*     */   public static final String CLASS_BOT = "BOT";
/*     */   public static final String CLASS_INP = "INP";
/*     */   public static final String CLASS_ca = "ca";
/*     */   public static final String CLASS_cb = "cb";
/*     */   public static final String CLASS_container = "container";
/*     */   public static final String CLASS_dat = "dat";
/*     */   public static final String CLASS_dat2 = "dat2";
/*     */   public static final String CLASS_resizable = "resizable";
/*     */   public static final String CLASS_tabf = "tabf";
/*     */   public static final String CLASS_tabw = "tabw";
/*     */   public static final String CLASS_tit = "tit";
/*     */   public static final String NAME__operacion = "_operacion";
/*     */   public static final String NAME_farea = "farea";
/*     */   public static final String NAME_fciclo = "fciclo";
/*     */   public static final String NAME_ff = "ff";
/*     */   public static final String NAME_fmes = "fmes";
/*     */   public static final String NAME_fmesInicial = "fmesInicial";
/*     */   public static final String NAME_fproceso = "fproceso";
/*     */   public static final String NAME_labelNombre = "labelNombre";
/* 145 */   public static final Class XMLC_GENERATED_CLASS = PoaReporteDetalladoHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/PoaReporteDetallado.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaReporteDetalladoHTML() {
/* 167 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 169 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 177 */   public PoaReporteDetalladoHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaReporteDetalladoHTML(PoaReporteDetalladoHTML src) {
/* 185 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 187 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 189 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PoaReporteDetalladoHTML(DocumentLoader loader, boolean buildDOM) {
/* 200 */     this.fDocumentLoader = loader;
/*     */     
/* 202 */     if (buildDOM)
/*     */     {
/* 204 */       buildDocument();
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
/* 216 */   public PoaReporteDetalladoHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 224 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 226 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 234 */     cloneDeepCheck(deep);
/*     */     
/* 236 */     return new PoaReporteDetalladoHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 244 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 252 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 261 */   public HTMLInputElement getElementBtnPrincipal() { return this.$element_BtnPrincipal; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 270 */   public HTMLDivElement getElementDivConsulta() { return this.$element_DivConsulta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 279 */   public HTMLDivElement getElementDivTableroControl() { return this.$element_DivTableroControl; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 288 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 297 */   public HTMLSelectElement getElementFarea() { return this.$element_Farea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 306 */   public HTMLSelectElement getElementFciclo() { return this.$element_Fciclo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 315 */   public HTMLSelectElement getElementFmes() { return this.$element_Fmes; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 324 */   public HTMLSelectElement getElementFmesInicial() { return this.$element_FmesInicial; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 333 */   public HTMLSelectElement getElementFproceso() { return this.$element_Fproceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 342 */   public HTMLLabelElement getElementLabelNombre() { return this.$element_LabelNombre; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 351 */   public HTMLTableSectionElement getElementTablaTablero() { return this.$element_TablaTablero; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 360 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 369 */   public void setTextDivConsulta(String text) { doSetText(this.$element_DivConsulta, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 378 */   public void setTextDivTableroControl(String text) { doSetText(this.$element_DivTableroControl, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 387 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 396 */   public void setTextLabelNombre(String text) { doSetText(this.$element_LabelNombre, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 405 */     if (node.getNodeType() != 9)
/*     */     {
/* 407 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 411 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 415 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 417 */       int substStart = "$element_".length();
/*     */       
/* 419 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 421 */         Field f = fs[i];
/*     */         
/* 423 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 425 */           String id = f.getName().substring(substStart);
/*     */           
/* 427 */           Node idNode = doc.getElementById(id);
/*     */           
/* 429 */           if (idNode == null) {
/*     */             
/* 431 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 433 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 437 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 441 */     } catch (Exception e) {
/*     */       
/* 443 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PoaReporteDetalladoHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */