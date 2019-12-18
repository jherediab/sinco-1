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
/*     */ import org.w3c.dom.html.HTMLFormElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLScriptElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.presentation.ModificarDetallesHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModificarDetallesHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLElement $element_Areap;
/*     */   private HTMLDivElement $element_Atencion;
/*     */   private HTMLDivElement $element_Detalles;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLElement $element_Fecha;
/*     */   private HTMLFormElement $element_FrmCar;
/*     */   private HTMLInputElement $element_IdSolDetalle;
/*     */   private HTMLTableElement $element_IdtblAtencion;
/*     */   private HTMLTableElement $element_IdtblDetalle;
/*     */   private HTMLScriptElement $element_JSValfechas;
/*     */   private HTMLScriptElement $element_MostrarMenu;
/*     */   private HTMLElement $element_Numerosolicitud;
/*     */   private HTMLInputElement $element_Pagina_siguiente;
/*     */   private HTMLElement $element_Proveedor;
/*     */   private HTMLElement $element_Servicio;
/*     */   private HTMLInputElement $element_SolAuditoria;
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
/*     */   public static final String CLASS_tit = "tit";
/*     */   public static final String NAME_miForma = "miForma";
/*     */   public static final String NAME_pagina_siguiente = "pagina_siguiente";
/*     */   public static final String NAME_solAuditoria = "solAuditoria";
/*     */   public static final String NAME_solicitud = "solicitud";
/* 128 */   public static final Class XMLC_GENERATED_CLASS = ModificarDetallesHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/ModificarDetalles.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 139 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModificarDetallesHTML() {
/* 150 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 152 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 160 */   public ModificarDetallesHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModificarDetallesHTML(ModificarDetallesHTML src) {
/* 168 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 170 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 172 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModificarDetallesHTML(DocumentLoader loader, boolean buildDOM) {
/* 183 */     this.fDocumentLoader = loader;
/*     */     
/* 185 */     if (buildDOM)
/*     */     {
/* 187 */       buildDocument();
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
/* 199 */   public ModificarDetallesHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 207 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 209 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 217 */     cloneDeepCheck(deep);
/*     */     
/* 219 */     return new ModificarDetallesHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 227 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 235 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 244 */   public HTMLElement getElementAreap() { return this.$element_Areap; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 253 */   public HTMLDivElement getElementAtencion() { return this.$element_Atencion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 262 */   public HTMLDivElement getElementDetalles() { return this.$element_Detalles; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 271 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 280 */   public HTMLElement getElementFecha() { return this.$element_Fecha; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 289 */   public HTMLFormElement getElementFrmCar() { return this.$element_FrmCar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 298 */   public HTMLInputElement getElementIdSolDetalle() { return this.$element_IdSolDetalle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 307 */   public HTMLTableElement getElementIdtblAtencion() { return this.$element_IdtblAtencion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 316 */   public HTMLTableElement getElementIdtblDetalle() { return this.$element_IdtblDetalle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 325 */   public HTMLScriptElement getElementJSValfechas() { return this.$element_JSValfechas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 334 */   public HTMLScriptElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 343 */   public HTMLElement getElementNumerosolicitud() { return this.$element_Numerosolicitud; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 352 */   public HTMLInputElement getElementPagina_siguiente() { return this.$element_Pagina_siguiente; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 361 */   public HTMLElement getElementProveedor() { return this.$element_Proveedor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 370 */   public HTMLElement getElementServicio() { return this.$element_Servicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 379 */   public HTMLInputElement getElementSolAuditoria() { return this.$element_SolAuditoria; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 388 */   public void setTextAreap(String text) { doSetText(this.$element_Areap, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 397 */   public void setTextAtencion(String text) { doSetText(this.$element_Atencion, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 406 */   public void setTextDetalles(String text) { doSetText(this.$element_Detalles, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 415 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 424 */   public void setTextFecha(String text) { doSetText(this.$element_Fecha, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 433 */   public void setTextFrmCar(String text) { doSetText(this.$element_FrmCar, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 442 */   public void setTextJSValfechas(String text) { doSetText(this.$element_JSValfechas, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 451 */   public void setTextMostrarMenu(String text) { doSetText(this.$element_MostrarMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 460 */   public void setTextNumerosolicitud(String text) { doSetText(this.$element_Numerosolicitud, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 469 */   public void setTextProveedor(String text) { doSetText(this.$element_Proveedor, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 478 */   public void setTextServicio(String text) { doSetText(this.$element_Servicio, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 487 */     if (node.getNodeType() != 9)
/*     */     {
/* 489 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 493 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 497 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 499 */       int substStart = "$element_".length();
/*     */       
/* 501 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 503 */         Field f = fs[i];
/*     */         
/* 505 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 507 */           String id = f.getName().substring(substStart);
/*     */           
/* 509 */           Node idNode = doc.getElementById(id);
/*     */           
/* 511 */           if (idNode == null) {
/*     */             
/* 513 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 515 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 519 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 523 */     } catch (Exception e) {
/*     */       
/* 525 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ModificarDetallesHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */