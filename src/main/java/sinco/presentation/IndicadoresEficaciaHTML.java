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
/*     */ import sinco.presentation.IndicadoresEficaciaHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IndicadoresEficaciaHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLInputElement $element_BtnPrincipal;
/*     */   private HTMLDivElement $element_DivConsulta;
/*     */   private HTMLDivElement $element_DivTableroControl;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLSelectElement $element_Farea;
/*     */   private HTMLSelectElement $element_Fciclo;
/*     */   private HTMLSelectElement $element_Fproceso;
/*     */   private HTMLLabelElement $element_LabelNombre;
/*     */   private HTMLTableSectionElement $element_TablaTablero;
/*     */   private HTMLInputElement $element__operacion;
/*     */   public static final String CLASS_BOB = "BOB";
/*     */   public static final String CLASS_BOT = "BOT";
/*     */   public static final String CLASS_INP = "INP";
/*     */   public static final String CLASS_ca = "ca";
/*     */   public static final String CLASS_cb = "cb";
/*     */   public static final String CLASS_cf2 = "cf2";
/*     */   public static final String CLASS_container = "container";
/*     */   public static final String CLASS_dat = "dat";
/*     */   public static final String CLASS_dat2 = "dat2";
/*     */   public static final String CLASS_datc = "datc";
/*     */   public static final String CLASS_resizable = "resizable";
/*     */   public static final String CLASS_sortable = "sortable";
/*     */   public static final String CLASS_tabf = "tabf";
/*     */   public static final String CLASS_tit = "tit";
/*     */   public static final String NAME__operacion = "_operacion";
/*     */   public static final String NAME_farea = "farea";
/*     */   public static final String NAME_fciclo = "fciclo";
/*     */   public static final String NAME_ff = "ff";
/*     */   public static final String NAME_fproceso = "fproceso";
/*     */   public static final String NAME_labelNombre = "labelNombre";
/* 141 */   public static final Class XMLC_GENERATED_CLASS = IndicadoresEficaciaHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/IndicadoresEficacia.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadoresEficaciaHTML() {
/* 163 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 165 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 173 */   public IndicadoresEficaciaHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadoresEficaciaHTML(IndicadoresEficaciaHTML src) {
/* 181 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 183 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 185 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadoresEficaciaHTML(DocumentLoader loader, boolean buildDOM) {
/* 196 */     this.fDocumentLoader = loader;
/*     */     
/* 198 */     if (buildDOM)
/*     */     {
/* 200 */       buildDocument();
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
/* 212 */   public IndicadoresEficaciaHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 220 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 222 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 230 */     cloneDeepCheck(deep);
/*     */     
/* 232 */     return new IndicadoresEficaciaHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 240 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 248 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 257 */   public HTMLInputElement getElementBtnPrincipal() { return this.$element_BtnPrincipal; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 266 */   public HTMLDivElement getElementDivConsulta() { return this.$element_DivConsulta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 275 */   public HTMLDivElement getElementDivTableroControl() { return this.$element_DivTableroControl; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 284 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 293 */   public HTMLSelectElement getElementFarea() { return this.$element_Farea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 302 */   public HTMLSelectElement getElementFciclo() { return this.$element_Fciclo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 311 */   public HTMLSelectElement getElementFproceso() { return this.$element_Fproceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 320 */   public HTMLLabelElement getElementLabelNombre() { return this.$element_LabelNombre; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 329 */   public HTMLTableSectionElement getElementTablaTablero() { return this.$element_TablaTablero; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 338 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 347 */   public void setTextDivConsulta(String text) { doSetText(this.$element_DivConsulta, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 356 */   public void setTextDivTableroControl(String text) { doSetText(this.$element_DivTableroControl, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 365 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 374 */   public void setTextLabelNombre(String text) { doSetText(this.$element_LabelNombre, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 383 */     if (node.getNodeType() != 9)
/*     */     {
/* 385 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 389 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 393 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 395 */       int substStart = "$element_".length();
/*     */       
/* 397 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 399 */         Field f = fs[i];
/*     */         
/* 401 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 403 */           String id = f.getName().substring(substStart);
/*     */           
/* 405 */           Node idNode = doc.getElementById(id);
/*     */           
/* 407 */           if (idNode == null) {
/*     */             
/* 409 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 411 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 415 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 419 */     } catch (Exception e) {
/*     */       
/* 421 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\IndicadoresEficaciaHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */