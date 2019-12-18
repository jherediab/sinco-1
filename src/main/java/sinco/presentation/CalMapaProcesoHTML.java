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
/*     */ import org.w3c.dom.html.HTMLScriptElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.presentation.CalMapaProcesoHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalMapaProcesoHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLTableSectionElement $element_Detalle;
/*     */   private HTMLDivElement $element_DivAnexos;
/*     */   private HTMLDivElement $element_DivAnexosSub;
/*     */   private HTMLDivElement $element_DivDescSub;
/*     */   private HTMLDivElement $element_DivDocumentos;
/*     */   private HTMLDivElement $element_DivProcSub;
/*     */   private HTMLDivElement $element_DivSubprocesos;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLElement $element_NombreProceso;
/*     */   private HTMLElement $element_NombreSubProceso;
/*     */   private HTMLTableElement $element_TblProcesos;
/*     */   private HTMLTableElement $element_TblSubProcesos;
/*     */   public static final String CLASS_cb = "cb";
/*     */   public static final String CLASS_cf1 = "cf1";
/*     */   public static final String CLASS_cf2 = "cf2";
/*     */   public static final String CLASS_container = "container";
/*     */   public static final String CLASS_titulo = "titulo";
/*  70 */   public static final Class XMLC_GENERATED_CLASS = CalMapaProcesoHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/CalMapaProceso.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalMapaProcesoHTML() {
/*  92 */     this(StandardDocumentLoader.getInstance());
/*     */     
/*  94 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public CalMapaProcesoHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalMapaProcesoHTML(CalMapaProcesoHTML src) {
/* 110 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 112 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 114 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalMapaProcesoHTML(DocumentLoader loader, boolean buildDOM) {
/* 125 */     this.fDocumentLoader = loader;
/*     */     
/* 127 */     if (buildDOM)
/*     */     {
/* 129 */       buildDocument();
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
/* 141 */   public CalMapaProcesoHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 149 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 151 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 159 */     cloneDeepCheck(deep);
/*     */     
/* 161 */     return new CalMapaProcesoHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 169 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 177 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 186 */   public HTMLTableSectionElement getElementDetalle() { return this.$element_Detalle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 195 */   public HTMLDivElement getElementDivAnexos() { return this.$element_DivAnexos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 204 */   public HTMLDivElement getElementDivAnexosSub() { return this.$element_DivAnexosSub; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 213 */   public HTMLDivElement getElementDivDescSub() { return this.$element_DivDescSub; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 222 */   public HTMLDivElement getElementDivDocumentos() { return this.$element_DivDocumentos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 231 */   public HTMLDivElement getElementDivProcSub() { return this.$element_DivProcSub; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 240 */   public HTMLDivElement getElementDivSubprocesos() { return this.$element_DivSubprocesos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 249 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 258 */   public HTMLElement getElementNombreProceso() { return this.$element_NombreProceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 267 */   public HTMLElement getElementNombreSubProceso() { return this.$element_NombreSubProceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 276 */   public HTMLTableElement getElementTblProcesos() { return this.$element_TblProcesos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 285 */   public HTMLTableElement getElementTblSubProcesos() { return this.$element_TblSubProcesos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 294 */   public void setTextDivAnexos(String text) { doSetText(this.$element_DivAnexos, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 303 */   public void setTextDivAnexosSub(String text) { doSetText(this.$element_DivAnexosSub, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 312 */   public void setTextDivDescSub(String text) { doSetText(this.$element_DivDescSub, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 321 */   public void setTextDivDocumentos(String text) { doSetText(this.$element_DivDocumentos, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 330 */   public void setTextDivProcSub(String text) { doSetText(this.$element_DivProcSub, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 339 */   public void setTextDivSubprocesos(String text) { doSetText(this.$element_DivSubprocesos, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 348 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 357 */   public void setTextNombreProceso(String text) { doSetText(this.$element_NombreProceso, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 366 */   public void setTextNombreSubProceso(String text) { doSetText(this.$element_NombreSubProceso, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 375 */     if (node.getNodeType() != 9)
/*     */     {
/* 377 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 381 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 385 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 387 */       int substStart = "$element_".length();
/*     */       
/* 389 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 391 */         Field f = fs[i];
/*     */         
/* 393 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 395 */           String id = f.getName().substring(substStart);
/*     */           
/* 397 */           Node idNode = doc.getElementById(id);
/*     */           
/* 399 */           if (idNode == null) {
/*     */             
/* 401 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 403 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 407 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 411 */     } catch (Exception e) {
/*     */       
/* 413 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalMapaProcesoHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */