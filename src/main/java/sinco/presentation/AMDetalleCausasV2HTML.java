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
/*     */ import org.w3c.dom.html.HTMLTableCellElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.presentation.AMDetalleCausasV2HTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AMDetalleCausasV2HTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLElement $element_Accion;
/*     */   private HTMLElement $element_Beneficio;
/*     */   private HTMLInputElement $element_CausaAr;
/*     */   private HTMLTableElement $element_Detalle;
/*     */   private HTMLTableElement $element_DetalleArchivos;
/*     */   private HTMLTableElement $element_DetalleS;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLElement $element_Fecha_estimada_terminacion;
/*     */   private HTMLElement $element_Fecha_inicio;
/*     */   private HTMLElement $element_Fecha_real_terminacion;
/*     */   private HTMLInputElement $element_IdBotonArchivos;
/*     */   private HTMLInputElement $element_IdCausaArch;
/*     */   private HTMLInputElement $element_IdNumeroArch;
/*     */   private HTMLDivElement $element_IdObservaciones;
/*     */   private HTMLDivElement $element_Idmostrararchivos;
/*     */   private HTMLInputElement $element_Llave;
/*     */   private HTMLInputElement $element_Llave2;
/*     */   private HTMLTableElement $element_MostrarMenu;
/*     */   private HTMLElement $element_NombreEstado;
/*     */   private HTMLElement $element_Numero;
/*     */   private HTMLInputElement $element_NumeroAr;
/*     */   private HTMLInputElement $element_NumeroV;
/*     */   private HTMLElement $element_Porque;
/*     */   private HTMLElement $element_Prorrogas;
/*     */   private HTMLElement $element_Responsable;
/*     */   private HTMLTableCellElement $element_TrAgregarArchivo;
/*     */   private HTMLTableCellElement $element_TrBitacora;
/*     */   public static final String CLASS_BOT = "BOT";
/*     */   public static final String CLASS_ca = "ca";
/*     */   public static final String CLASS_cb = "cb";
/*     */   public static final String CLASS_cf1 = "cf1";
/*     */   public static final String CLASS_cf2 = "cf2";
/*     */   public static final String CLASS_container = "container";
/*     */   public static final String CLASS_dat = "dat";
/*     */   public static final String CLASS_ne = "ne";
/*     */   public static final String CLASS_tabf = "tabf";
/*     */   public static final String CLASS_tabp = "tabp";
/*     */   public static final String CLASS_tabw = "tabw";
/*     */   public static final String CLASS_tit = "tit";
/*     */   public static final String NAME_causa = "causa";
/*     */   public static final String NAME_llave = "llave";
/*     */   public static final String NAME_llave2 = "llave2";
/*     */   public static final String NAME_miBoton = "miBoton";
/*     */   public static final String NAME_miForma4 = "miForma4";
/*     */   public static final String NAME_miForma5 = "miForma5";
/*     */   public static final String NAME_miForma7 = "miForma7";
/*     */   public static final String NAME_numero = "numero";
/*     */   public static final String NAME_pagina = "pagina";
/*     */   public static final String NAME_tabla = "tabla";
/* 185 */   public static final Class XMLC_GENERATED_CLASS = AMDetalleCausasV2HTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/AMDetalleCausasV2.html";
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
/*     */   public AMDetalleCausasV2HTML() {
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
/* 217 */   public AMDetalleCausasV2HTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AMDetalleCausasV2HTML(AMDetalleCausasV2HTML src) {
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
/*     */   public AMDetalleCausasV2HTML(DocumentLoader loader, boolean buildDOM) {
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
/* 256 */   public AMDetalleCausasV2HTML(DocumentLoader loader) { this(loader, true); }
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
/* 276 */     return new AMDetalleCausasV2HTML(this);
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
/* 301 */   public HTMLElement getElementAccion() { return this.$element_Accion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 310 */   public HTMLElement getElementBeneficio() { return this.$element_Beneficio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 319 */   public HTMLInputElement getElementCausaAr() { return this.$element_CausaAr; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 328 */   public HTMLTableElement getElementDetalle() { return this.$element_Detalle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 337 */   public HTMLTableElement getElementDetalleArchivos() { return this.$element_DetalleArchivos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 346 */   public HTMLTableElement getElementDetalleS() { return this.$element_DetalleS; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 355 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 364 */   public HTMLElement getElementFecha_estimada_terminacion() { return this.$element_Fecha_estimada_terminacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 373 */   public HTMLElement getElementFecha_inicio() { return this.$element_Fecha_inicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 382 */   public HTMLElement getElementFecha_real_terminacion() { return this.$element_Fecha_real_terminacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 391 */   public HTMLInputElement getElementIdBotonArchivos() { return this.$element_IdBotonArchivos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 400 */   public HTMLInputElement getElementIdCausaArch() { return this.$element_IdCausaArch; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 409 */   public HTMLInputElement getElementIdNumeroArch() { return this.$element_IdNumeroArch; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 418 */   public HTMLDivElement getElementIdObservaciones() { return this.$element_IdObservaciones; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 427 */   public HTMLDivElement getElementIdmostrararchivos() { return this.$element_Idmostrararchivos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 436 */   public HTMLInputElement getElementLlave() { return this.$element_Llave; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 445 */   public HTMLInputElement getElementLlave2() { return this.$element_Llave2; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 454 */   public HTMLTableElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 463 */   public HTMLElement getElementNombreEstado() { return this.$element_NombreEstado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 472 */   public HTMLElement getElementNumero() { return this.$element_Numero; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 481 */   public HTMLInputElement getElementNumeroAr() { return this.$element_NumeroAr; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 490 */   public HTMLInputElement getElementNumeroV() { return this.$element_NumeroV; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 499 */   public HTMLElement getElementPorque() { return this.$element_Porque; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 508 */   public HTMLElement getElementProrrogas() { return this.$element_Prorrogas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 517 */   public HTMLElement getElementResponsable() { return this.$element_Responsable; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 526 */   public HTMLTableCellElement getElementTrAgregarArchivo() { return this.$element_TrAgregarArchivo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 535 */   public HTMLTableCellElement getElementTrBitacora() { return this.$element_TrBitacora; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 544 */   public void setTextAccion(String text) { doSetText(this.$element_Accion, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 553 */   public void setTextBeneficio(String text) { doSetText(this.$element_Beneficio, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 562 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 571 */   public void setTextFecha_estimada_terminacion(String text) { doSetText(this.$element_Fecha_estimada_terminacion, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 580 */   public void setTextFecha_inicio(String text) { doSetText(this.$element_Fecha_inicio, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 589 */   public void setTextFecha_real_terminacion(String text) { doSetText(this.$element_Fecha_real_terminacion, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 598 */   public void setTextIdObservaciones(String text) { doSetText(this.$element_IdObservaciones, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 607 */   public void setTextIdmostrararchivos(String text) { doSetText(this.$element_Idmostrararchivos, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 616 */   public void setTextNombreEstado(String text) { doSetText(this.$element_NombreEstado, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 625 */   public void setTextNumero(String text) { doSetText(this.$element_Numero, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 634 */   public void setTextPorque(String text) { doSetText(this.$element_Porque, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 643 */   public void setTextProrrogas(String text) { doSetText(this.$element_Prorrogas, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 652 */   public void setTextResponsable(String text) { doSetText(this.$element_Responsable, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 661 */   public void setTextTrAgregarArchivo(String text) { doSetText(this.$element_TrAgregarArchivo, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 670 */   public void setTextTrBitacora(String text) { doSetText(this.$element_TrBitacora, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 679 */     if (node.getNodeType() != 9)
/*     */     {
/* 681 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 685 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 689 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 691 */       int substStart = "$element_".length();
/*     */       
/* 693 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 695 */         Field f = fs[i];
/*     */         
/* 697 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 699 */           String id = f.getName().substring(substStart);
/*     */           
/* 701 */           Node idNode = doc.getElementById(id);
/*     */           
/* 703 */           if (idNode == null) {
/*     */             
/* 705 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 707 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 711 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 715 */     } catch (Exception e) {
/*     */       
/* 717 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AMDetalleCausasV2HTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */