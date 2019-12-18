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
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLScriptElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.presentation.ResponsablesEncuestaHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ResponsablesEncuestaHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLTableElement $element_Detalle;
/*     */   private HTMLDivElement $element_DivAreas;
/*     */   private HTMLDivElement $element_DivFiltro;
/*     */   private HTMLDivElement $element_DivPersonas;
/*     */   private HTMLDivElement $element_DivPersonasI;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLElement $element_Estado;
/*     */   private HTMLSelectElement $element_IdArea;
/*     */   private HTMLInputElement $element_IdEncuestaArea;
/*     */   private HTMLInputElement $element_IdEncuestaArea1;
/*     */   private HTMLInputElement $element_IdEncuestaPersona;
/*     */   private HTMLInputElement $element_IdEncuestaPersonas;
/*     */   private HTMLInputElement $element_IdEncuestaVolver;
/*     */   private HTMLScriptElement $element_MostrarMenu;
/*     */   private HTMLElement $element_NumeroEncuesta;
/*     */   private HTMLElement $element_Servicio;
/*     */   public static final String CLASS_BOT = "BOT";
/*     */   public static final String CLASS_INP = "INP";
/*     */   public static final String CLASS_ca = "ca";
/*     */   public static final String CLASS_cb = "cb";
/*     */   public static final String CLASS_cf1 = "cf1";
/*     */   public static final String CLASS_cf2 = "cf2";
/*     */   public static final String CLASS_container = "container";
/*     */   public static final String CLASS_dat = "dat";
/*     */   public static final String CLASS_tabf = "tabf";
/*     */   public static final String CLASS_tit = "tit";
/*     */   public static final String NAME_accion = "accion";
/*     */   public static final String NAME_aproximacion = "aproximacion";
/*     */   public static final String NAME_area = "area";
/*     */   public static final String NAME_clickcontrol = "clickcontrol";
/*     */   public static final String NAME_encuesta = "encuesta";
/*     */   public static final String NAME_filtro = "filtro";
/*     */   public static final String NAME_gmm_form = "gmm_form";
/*     */   public static final String NAME_recargar = "recargar";
/* 143 */   public static final Class XMLC_GENERATED_CLASS = ResponsablesEncuestaHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/ResponsablesEncuesta.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResponsablesEncuestaHTML() {
/* 165 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 167 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 175 */   public ResponsablesEncuestaHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResponsablesEncuestaHTML(ResponsablesEncuestaHTML src) {
/* 183 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 185 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 187 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResponsablesEncuestaHTML(DocumentLoader loader, boolean buildDOM) {
/* 198 */     this.fDocumentLoader = loader;
/*     */     
/* 200 */     if (buildDOM)
/*     */     {
/* 202 */       buildDocument();
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
/* 214 */   public ResponsablesEncuestaHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 222 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 224 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 232 */     cloneDeepCheck(deep);
/*     */     
/* 234 */     return new ResponsablesEncuestaHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 242 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 250 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 259 */   public HTMLTableElement getElementDetalle() { return this.$element_Detalle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 268 */   public HTMLDivElement getElementDivAreas() { return this.$element_DivAreas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 277 */   public HTMLDivElement getElementDivFiltro() { return this.$element_DivFiltro; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 286 */   public HTMLDivElement getElementDivPersonas() { return this.$element_DivPersonas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 295 */   public HTMLDivElement getElementDivPersonasI() { return this.$element_DivPersonasI; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 304 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 313 */   public HTMLElement getElementEstado() { return this.$element_Estado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 322 */   public HTMLSelectElement getElementIdArea() { return this.$element_IdArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 331 */   public HTMLInputElement getElementIdEncuestaArea() { return this.$element_IdEncuestaArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 340 */   public HTMLInputElement getElementIdEncuestaArea1() { return this.$element_IdEncuestaArea1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 349 */   public HTMLInputElement getElementIdEncuestaPersona() { return this.$element_IdEncuestaPersona; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 358 */   public HTMLInputElement getElementIdEncuestaPersonas() { return this.$element_IdEncuestaPersonas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 367 */   public HTMLInputElement getElementIdEncuestaVolver() { return this.$element_IdEncuestaVolver; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 376 */   public HTMLScriptElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 385 */   public HTMLElement getElementNumeroEncuesta() { return this.$element_NumeroEncuesta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 394 */   public HTMLElement getElementServicio() { return this.$element_Servicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 403 */   public void setTextDivAreas(String text) { doSetText(this.$element_DivAreas, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 412 */   public void setTextDivFiltro(String text) { doSetText(this.$element_DivFiltro, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 421 */   public void setTextDivPersonas(String text) { doSetText(this.$element_DivPersonas, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 430 */   public void setTextDivPersonasI(String text) { doSetText(this.$element_DivPersonasI, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 439 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 448 */   public void setTextEstado(String text) { doSetText(this.$element_Estado, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 457 */   public void setTextMostrarMenu(String text) { doSetText(this.$element_MostrarMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 466 */   public void setTextNumeroEncuesta(String text) { doSetText(this.$element_NumeroEncuesta, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 475 */   public void setTextServicio(String text) { doSetText(this.$element_Servicio, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 484 */     if (node.getNodeType() != 9)
/*     */     {
/* 486 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 490 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 494 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 496 */       int substStart = "$element_".length();
/*     */       
/* 498 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 500 */         Field f = fs[i];
/*     */         
/* 502 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 504 */           String id = f.getName().substring(substStart);
/*     */           
/* 506 */           Node idNode = doc.getElementById(id);
/*     */           
/* 508 */           if (idNode == null) {
/*     */             
/* 510 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 512 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 516 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 520 */     } catch (Exception e) {
/*     */       
/* 522 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ResponsablesEncuestaHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */