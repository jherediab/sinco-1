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
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import org.w3c.dom.html.HTMLTableRowElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.presentation.ContContratoLiquidacionActHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ContContratoLiquidacionActHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLInputElement $element_Agregar;
/*     */   private HTMLInputElement $element_BfechaLiquidacion;
/*     */   private HTMLInputElement $element_BtnEliminar;
/*     */   private HTMLInputElement $element_BtnGrabar;
/*     */   private HTMLInputElement $element_ConsecutivoContrato;
/*     */   private HTMLTableElement $element_Cuentas;
/*     */   private HTMLTableSectionElement $element_DetalleCuentas;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLInputElement $element_FechaInsercion;
/*     */   private HTMLInputElement $element_FechaLiquidacion;
/*     */   private HTMLInputElement $element_FechaModificacion;
/*     */   private HTMLScriptElement $element_MostrarMenu;
/*     */   private HTMLInputElement $element_NumeroContrato;
/*     */   private HTMLInputElement $element_Origen;
/*     */   private HTMLElement $element_TotalAvalado;
/*     */   private HTMLElement $element_TotalCopago;
/*     */   private HTMLElement $element_TotalDescuentos;
/*     */   private HTMLElement $element_TotalFacturas;
/*     */   private HTMLElement $element_TotalObjecion;
/*     */   private HTMLElement $element_TotalPagado;
/*     */   private HTMLElement $element_TotalSaldo;
/*     */   private HTMLTableRowElement $element_TrCreacionRegistro;
/*     */   private HTMLTableRowElement $element_TrCuentas;
/*     */   private HTMLInputElement $element_UsuarioInsercion;
/*     */   private HTMLInputElement $element_UsuarioModificacion;
/*     */   private HTMLInputElement $element_ValorCancelado;
/*     */   private HTMLInputElement $element_ValorContrato;
/*     */   private HTMLElement $element_ValorDebe;
/*     */   private HTMLElement $element_ValorHaber;
/*     */   private HTMLInputElement $element_ValorLiquidacion;
/*     */   private HTMLInputElement $element_ValorSaldo;
/*     */   private HTMLInputElement $element__operacion;
/*     */   public static final String CLASS_BOT = "BOT";
/*     */   public static final String CLASS_IND = "IND";
/*     */   public static final String CLASS_INP = "INP";
/*     */   public static final String CLASS_INPD = "INPD";
/*     */   public static final String CLASS_PIE = "PIE";
/*     */   public static final String CLASS_PIED = "PIED";
/*     */   public static final String CLASS_btnCalendario = "btnCalendario";
/*     */   public static final String CLASS_ca = "ca";
/*     */   public static final String CLASS_cb = "cb";
/*     */   public static final String CLASS_cf1 = "cf1";
/*     */   public static final String CLASS_cf2 = "cf2";
/*     */   public static final String CLASS_dat = "dat";
/*     */   public static final String CLASS_dat2 = "dat2";
/*     */   public static final String CLASS_resizable = "resizable";
/*     */   public static final String CLASS_sortable = "sortable";
/*     */   public static final String CLASS_tabf = "tabf";
/*     */   public static final String CLASS_tit = "tit";
/*     */   public static final String NAME__operacion = "_operacion";
/*     */   public static final String NAME_consecutivoContrato = "consecutivoContrato";
/*     */   public static final String NAME_fechaInsercion = "fechaInsercion";
/*     */   public static final String NAME_fechaLiquidacion = "fechaLiquidacion";
/*     */   public static final String NAME_fechaModificacion = "fechaModificacion";
/*     */   public static final String NAME_miForma = "miForma";
/*     */   public static final String NAME_numeroContrato = "numeroContrato";
/*     */   public static final String NAME_origen = "origen";
/*     */   public static final String NAME_usuarioInsercion = "usuarioInsercion";
/*     */   public static final String NAME_usuarioModificacion = "usuarioModificacion";
/*     */   public static final String NAME_valorCancelado = "valorCancelado";
/*     */   public static final String NAME_valorContrato = "valorContrato";
/*     */   public static final String NAME_valorLiquidacion = "valorLiquidacion";
/*     */   public static final String NAME_valorSaldo = "valorSaldo";
/* 240 */   public static final Class XMLC_GENERATED_CLASS = ContContratoLiquidacionActHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/ContContratoLiquidacionAct.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 251 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContContratoLiquidacionActHTML() {
/* 262 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 264 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 272 */   public ContContratoLiquidacionActHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContContratoLiquidacionActHTML(ContContratoLiquidacionActHTML src) {
/* 280 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 282 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 284 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ContContratoLiquidacionActHTML(DocumentLoader loader, boolean buildDOM) {
/* 295 */     this.fDocumentLoader = loader;
/*     */     
/* 297 */     if (buildDOM)
/*     */     {
/* 299 */       buildDocument();
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
/* 311 */   public ContContratoLiquidacionActHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 319 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 321 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 329 */     cloneDeepCheck(deep);
/*     */     
/* 331 */     return new ContContratoLiquidacionActHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 339 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 347 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 356 */   public HTMLInputElement getElementAgregar() { return this.$element_Agregar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 365 */   public HTMLInputElement getElementBfechaLiquidacion() { return this.$element_BfechaLiquidacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 374 */   public HTMLInputElement getElementBtnEliminar() { return this.$element_BtnEliminar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 383 */   public HTMLInputElement getElementBtnGrabar() { return this.$element_BtnGrabar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 392 */   public HTMLInputElement getElementConsecutivoContrato() { return this.$element_ConsecutivoContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 401 */   public HTMLTableElement getElementCuentas() { return this.$element_Cuentas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 410 */   public HTMLTableSectionElement getElementDetalleCuentas() { return this.$element_DetalleCuentas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 419 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 428 */   public HTMLInputElement getElementFechaInsercion() { return this.$element_FechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 437 */   public HTMLInputElement getElementFechaLiquidacion() { return this.$element_FechaLiquidacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 446 */   public HTMLInputElement getElementFechaModificacion() { return this.$element_FechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 455 */   public HTMLScriptElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 464 */   public HTMLInputElement getElementNumeroContrato() { return this.$element_NumeroContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 473 */   public HTMLInputElement getElementOrigen() { return this.$element_Origen; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 482 */   public HTMLElement getElementTotalAvalado() { return this.$element_TotalAvalado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 491 */   public HTMLElement getElementTotalCopago() { return this.$element_TotalCopago; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 500 */   public HTMLElement getElementTotalDescuentos() { return this.$element_TotalDescuentos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 509 */   public HTMLElement getElementTotalFacturas() { return this.$element_TotalFacturas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 518 */   public HTMLElement getElementTotalObjecion() { return this.$element_TotalObjecion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 527 */   public HTMLElement getElementTotalPagado() { return this.$element_TotalPagado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 536 */   public HTMLElement getElementTotalSaldo() { return this.$element_TotalSaldo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 545 */   public HTMLTableRowElement getElementTrCreacionRegistro() { return this.$element_TrCreacionRegistro; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 554 */   public HTMLTableRowElement getElementTrCuentas() { return this.$element_TrCuentas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 563 */   public HTMLInputElement getElementUsuarioInsercion() { return this.$element_UsuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 572 */   public HTMLInputElement getElementUsuarioModificacion() { return this.$element_UsuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 581 */   public HTMLInputElement getElementValorCancelado() { return this.$element_ValorCancelado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 590 */   public HTMLInputElement getElementValorContrato() { return this.$element_ValorContrato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 599 */   public HTMLElement getElementValorDebe() { return this.$element_ValorDebe; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 608 */   public HTMLElement getElementValorHaber() { return this.$element_ValorHaber; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 617 */   public HTMLInputElement getElementValorLiquidacion() { return this.$element_ValorLiquidacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 626 */   public HTMLInputElement getElementValorSaldo() { return this.$element_ValorSaldo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 635 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 644 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 653 */   public void setTextMostrarMenu(String text) { doSetText(this.$element_MostrarMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 662 */   public void setTextTotalAvalado(String text) { doSetText(this.$element_TotalAvalado, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 671 */   public void setTextTotalCopago(String text) { doSetText(this.$element_TotalCopago, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 680 */   public void setTextTotalDescuentos(String text) { doSetText(this.$element_TotalDescuentos, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 689 */   public void setTextTotalFacturas(String text) { doSetText(this.$element_TotalFacturas, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 698 */   public void setTextTotalObjecion(String text) { doSetText(this.$element_TotalObjecion, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 707 */   public void setTextTotalPagado(String text) { doSetText(this.$element_TotalPagado, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 716 */   public void setTextTotalSaldo(String text) { doSetText(this.$element_TotalSaldo, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 725 */   public void setTextValorDebe(String text) { doSetText(this.$element_ValorDebe, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 734 */   public void setTextValorHaber(String text) { doSetText(this.$element_ValorHaber, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 743 */     if (node.getNodeType() != 9)
/*     */     {
/* 745 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 749 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 753 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 755 */       int substStart = "$element_".length();
/*     */       
/* 757 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 759 */         Field f = fs[i];
/*     */         
/* 761 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 763 */           String id = f.getName().substring(substStart);
/*     */           
/* 765 */           Node idNode = doc.getElementById(id);
/*     */           
/* 767 */           if (idNode == null) {
/*     */             
/* 769 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 771 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 775 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 779 */     } catch (Exception e) {
/*     */       
/* 781 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ContContratoLiquidacionActHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */