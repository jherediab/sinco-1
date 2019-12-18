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
/*     */ import org.w3c.dom.html.HTMLBodyElement;
/*     */ import org.w3c.dom.html.HTMLDivElement;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLScriptElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.presentation.CalDocumentosArchivosHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalDocumentosArchivosHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLElement $element_Codigo;
/*     */   private HTMLInputElement $element_CodigoDocumento;
/*     */   private HTMLInputElement $element_CodigoV;
/*     */   private HTMLElement $element_Descripcion;
/*     */   private HTMLTableElement $element_Detalle;
/*     */   private HTMLInputElement $element_Doc;
/*     */   private HTMLInputElement $element_Doc2;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLElement $element_Estado;
/*     */   private HTMLElement $element_FechaVersion;
/*     */   private HTMLBodyElement $element_MiBody;
/*     */   private HTMLTableElement $element_MostrarMenu;
/*     */   private HTMLElement $element_NombreArchivoPdf;
/*     */   private HTMLElement $element_NombreArchivoWord;
/*     */   private HTMLInputElement $element_ObjetivoHidden;
/*     */   private HTMLInputElement $element_ObjetivoHidden2;
/*     */   private HTMLInputElement $element_Operacion;
/*     */   private HTMLElement $element_Proceso;
/*     */   private HTMLElement $element_Subproceso;
/*     */   private HTMLDivElement $element_TblVersiones;
/*     */   private HTMLDivElement $element_Tblsubir;
/*     */   private HTMLElement $element_TipoDocumento;
/*     */   private HTMLElement $element_Version;
/*     */   public static final String CLASS_BOT = "BOT";
/*     */   public static final String CLASS_INP = "INP";
/*     */   public static final String CLASS_ca = "ca";
/*     */   public static final String CLASS_cb = "cb";
/*     */   public static final String CLASS_cf1 = "cf1";
/*     */   public static final String CLASS_cf2 = "cf2";
/*     */   public static final String CLASS_container = "container";
/*     */   public static final String CLASS_dat = "dat";
/*     */   public static final String CLASS_dat2 = "dat2";
/*     */   public static final String CLASS_tabf = "tabf";
/*     */   public static final String CLASS_tabw = "tabw";
/*     */   public static final String CLASS_tit = "tit";
/*     */   public static final String NAME_Doc = "Doc";
/*     */   public static final String NAME_Doc2 = "Doc2";
/*     */   public static final String NAME__operacion = "_operacion";
/*     */   public static final String NAME_codigo = "codigo";
/*     */   public static final String NAME_codigoDocumento = "codigoDocumento";
/*     */   public static final String NAME_miForma = "miForma";
/*     */   public static final String NAME_miForma2 = "miForma2";
/*     */   public static final String NAME_objetivoHidden = "objetivoHidden";
/*     */   public static final String NAME_theFileName = "theFileName";
/*     */   public static final String NAME_tipoDocumento = "tipoDocumento";
/*     */   public static final String NAME_upload = "upload";
/* 182 */   public static final Class XMLC_GENERATED_CLASS = CalDocumentosArchivosHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/CalDocumentosArchivos.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 193 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalDocumentosArchivosHTML() {
/* 204 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 206 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 214 */   public CalDocumentosArchivosHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalDocumentosArchivosHTML(CalDocumentosArchivosHTML src) {
/* 222 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 224 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 226 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalDocumentosArchivosHTML(DocumentLoader loader, boolean buildDOM) {
/* 237 */     this.fDocumentLoader = loader;
/*     */     
/* 239 */     if (buildDOM)
/*     */     {
/* 241 */       buildDocument();
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
/* 253 */   public CalDocumentosArchivosHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 261 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 263 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 271 */     cloneDeepCheck(deep);
/*     */     
/* 273 */     return new CalDocumentosArchivosHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 281 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 289 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 298 */   public HTMLElement getElementCodigo() { return this.$element_Codigo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 307 */   public HTMLInputElement getElementCodigoDocumento() { return this.$element_CodigoDocumento; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 316 */   public HTMLInputElement getElementCodigoV() { return this.$element_CodigoV; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 325 */   public HTMLElement getElementDescripcion() { return this.$element_Descripcion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 334 */   public HTMLTableElement getElementDetalle() { return this.$element_Detalle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 343 */   public HTMLInputElement getElementDoc() { return this.$element_Doc; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 352 */   public HTMLInputElement getElementDoc2() { return this.$element_Doc2; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 361 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 370 */   public HTMLElement getElementEstado() { return this.$element_Estado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 379 */   public HTMLElement getElementFechaVersion() { return this.$element_FechaVersion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 388 */   public HTMLBodyElement getElementMiBody() { return this.$element_MiBody; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 397 */   public HTMLTableElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 406 */   public HTMLElement getElementNombreArchivoPdf() { return this.$element_NombreArchivoPdf; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 415 */   public HTMLElement getElementNombreArchivoWord() { return this.$element_NombreArchivoWord; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 424 */   public HTMLInputElement getElementObjetivoHidden() { return this.$element_ObjetivoHidden; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 433 */   public HTMLInputElement getElementObjetivoHidden2() { return this.$element_ObjetivoHidden2; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 442 */   public HTMLInputElement getElementOperacion() { return this.$element_Operacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 451 */   public HTMLElement getElementProceso() { return this.$element_Proceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 460 */   public HTMLElement getElementSubproceso() { return this.$element_Subproceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 469 */   public HTMLDivElement getElementTblVersiones() { return this.$element_TblVersiones; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 478 */   public HTMLDivElement getElementTblsubir() { return this.$element_Tblsubir; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 487 */   public HTMLElement getElementTipoDocumento() { return this.$element_TipoDocumento; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 496 */   public HTMLElement getElementVersion() { return this.$element_Version; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 505 */   public void setTextCodigo(String text) { doSetText(this.$element_Codigo, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 514 */   public void setTextDescripcion(String text) { doSetText(this.$element_Descripcion, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 523 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 532 */   public void setTextEstado(String text) { doSetText(this.$element_Estado, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 541 */   public void setTextFechaVersion(String text) { doSetText(this.$element_FechaVersion, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 550 */   public void setTextMiBody(String text) { doSetText(this.$element_MiBody, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 559 */   public void setTextNombreArchivoPdf(String text) { doSetText(this.$element_NombreArchivoPdf, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 568 */   public void setTextNombreArchivoWord(String text) { doSetText(this.$element_NombreArchivoWord, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 577 */   public void setTextProceso(String text) { doSetText(this.$element_Proceso, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 586 */   public void setTextSubproceso(String text) { doSetText(this.$element_Subproceso, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 595 */   public void setTextTblVersiones(String text) { doSetText(this.$element_TblVersiones, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 604 */   public void setTextTblsubir(String text) { doSetText(this.$element_Tblsubir, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 613 */   public void setTextTipoDocumento(String text) { doSetText(this.$element_TipoDocumento, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 622 */   public void setTextVersion(String text) { doSetText(this.$element_Version, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 631 */     if (node.getNodeType() != 9)
/*     */     {
/* 633 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 637 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 641 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 643 */       int substStart = "$element_".length();
/*     */       
/* 645 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 647 */         Field f = fs[i];
/*     */         
/* 649 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 651 */           String id = f.getName().substring(substStart);
/*     */           
/* 653 */           Node idNode = doc.getElementById(id);
/*     */           
/* 655 */           if (idNode == null) {
/*     */             
/* 657 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 659 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 663 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 667 */     } catch (Exception e) {
/*     */       
/* 669 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalDocumentosArchivosHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */