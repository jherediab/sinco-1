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
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLScriptElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTextAreaElement;
/*     */ import sinco.presentation.AMPreCerrarHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AMPreCerrarHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLInputElement $element_Bfecha1;
/*     */   private HTMLInputElement $element_Bfecha2;
/*     */   private HTMLInputElement $element_Cargo;
/*     */   private HTMLInputElement $element_Cargo1;
/*     */   private HTMLSelectElement $element_Cumplio;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLInputElement $element_Fecha1;
/*     */   private HTMLInputElement $element_Fecha2;
/*     */   private HTMLInputElement $element_Funcionario;
/*     */   private HTMLInputElement $element_Funcionario1;
/*     */   private HTMLInputElement $element_IdNumero;
/*     */   private HTMLSelectElement $element_Implantada;
/*     */   private HTMLSelectElement $element_IrevisadaAuditor;
/*     */   private HTMLTextAreaElement $element_Justificacion;
/*     */   private HTMLScriptElement $element_MostrarMenu;
/*     */   private HTMLElement $element_Numero;
/*     */   private HTMLSelectElement $element_RevisadaComite;
/*     */   public static final String CLASS_BOT = "BOT";
/*     */   public static final String CLASS_INP = "INP";
/*     */   public static final String CLASS_ca = "ca";
/*     */   public static final String CLASS_cb = "cb";
/*     */   public static final String CLASS_container = "container";
/*     */   public static final String CLASS_cp = "cp";
/*     */   public static final String CLASS_dat = "dat";
/*     */   public static final String CLASS_dat2 = "dat2";
/*     */   public static final String CLASS_tabf = "tabf";
/*     */   public static final String CLASS_tit = "tit";
/*     */   public static final String NAME_cargo1 = "cargo1";
/*     */   public static final String NAME_cargo2 = "cargo2";
/*     */   public static final String NAME_cumplio = "cumplio";
/*     */   public static final String NAME_fecha1 = "fecha1";
/*     */   public static final String NAME_fecha2 = "fecha2";
/*     */   public static final String NAME_funcionario1 = "funcionario1";
/*     */   public static final String NAME_funcionario2 = "funcionario2";
/*     */   public static final String NAME_implantada = "implantada";
/*     */   public static final String NAME_justificacion = "justificacion";
/*     */   public static final String NAME_miBoton = "miBoton";
/*     */   public static final String NAME_miForma = "miForma";
/*     */   public static final String NAME_numero = "numero";
/*     */   public static final String NAME_operacion = "operacion";
/*     */   public static final String NAME_revisadaAuditor = "revisadaAuditor";
/*     */   public static final String NAME_revisadaComite = "revisadaComite";
/*     */   public static final String NAME_txtContJust = "txtContJust";
/* 185 */   public static final Class XMLC_GENERATED_CLASS = AMPreCerrarHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/AMPreCerrar.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 196 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AMPreCerrarHTML() {
/* 207 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 209 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 217 */   public AMPreCerrarHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AMPreCerrarHTML(AMPreCerrarHTML src) {
/* 225 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 227 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 229 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AMPreCerrarHTML(DocumentLoader loader, boolean buildDOM) {
/* 240 */     this.fDocumentLoader = loader;
/*     */     
/* 242 */     if (buildDOM)
/*     */     {
/* 244 */       buildDocument();
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
/* 256 */   public AMPreCerrarHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 264 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 266 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 274 */     cloneDeepCheck(deep);
/*     */     
/* 276 */     return new AMPreCerrarHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 284 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 292 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 301 */   public HTMLInputElement getElementBfecha1() { return this.$element_Bfecha1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 310 */   public HTMLInputElement getElementBfecha2() { return this.$element_Bfecha2; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 319 */   public HTMLInputElement getElementCargo() { return this.$element_Cargo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 328 */   public HTMLInputElement getElementCargo1() { return this.$element_Cargo1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 337 */   public HTMLSelectElement getElementCumplio() { return this.$element_Cumplio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 346 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 355 */   public HTMLInputElement getElementFecha1() { return this.$element_Fecha1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 364 */   public HTMLInputElement getElementFecha2() { return this.$element_Fecha2; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 373 */   public HTMLInputElement getElementFuncionario() { return this.$element_Funcionario; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 382 */   public HTMLInputElement getElementFuncionario1() { return this.$element_Funcionario1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 391 */   public HTMLInputElement getElementIdNumero() { return this.$element_IdNumero; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 400 */   public HTMLSelectElement getElementImplantada() { return this.$element_Implantada; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 409 */   public HTMLSelectElement getElementIrevisadaAuditor() { return this.$element_IrevisadaAuditor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 418 */   public HTMLTextAreaElement getElementJustificacion() { return this.$element_Justificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 427 */   public HTMLScriptElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 436 */   public HTMLElement getElementNumero() { return this.$element_Numero; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 445 */   public HTMLSelectElement getElementRevisadaComite() { return this.$element_RevisadaComite; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 454 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 463 */   public void setTextJustificacion(String text) { doSetText(this.$element_Justificacion, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 472 */   public void setTextMostrarMenu(String text) { doSetText(this.$element_MostrarMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 481 */   public void setTextNumero(String text) { doSetText(this.$element_Numero, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 490 */     if (node.getNodeType() != 9)
/*     */     {
/* 492 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 496 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 500 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 502 */       int substStart = "$element_".length();
/*     */       
/* 504 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 506 */         Field f = fs[i];
/*     */         
/* 508 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 510 */           String id = f.getName().substring(substStart);
/*     */           
/* 512 */           Node idNode = doc.getElementById(id);
/*     */           
/* 514 */           if (idNode == null) {
/*     */             
/* 516 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 518 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 522 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 526 */     } catch (Exception e) {
/*     */       
/* 528 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AMPreCerrarHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */