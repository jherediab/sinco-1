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
/*     */ import org.w3c.dom.html.HTMLTableRowElement;
/*     */ import org.w3c.dom.html.HTMLTextAreaElement;
/*     */ import sinco.presentation.AMActV2HTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AMActV2HTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLTextAreaElement $element_Accion;
/*     */   private HTMLSelectElement $element_Area;
/*     */   private HTMLInputElement $element_Asociado;
/*     */   private HTMLElement $element_Ayuda;
/*     */   private HTMLTextAreaElement $element_Beneficio;
/*     */   private HTMLInputElement $element_BfechaEstimadaTerminacion;
/*     */   private HTMLInputElement $element_Ciclo;
/*     */   private HTMLInputElement $element_CodigoLogro;
/*     */   private HTMLDivElement $element_DArea;
/*     */   private HTMLTextAreaElement $element_Descripcion;
/*     */   private HTMLTableElement $element_Detalle;
/*     */   private HTMLTableElement $element_DetalleRes;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLInputElement $element_FechaEstimadaTerminacion;
/*     */   private HTMLInputElement $element_IdNumero;
/*     */   private HTMLSelectElement $element_Impacto;
/*     */   private HTMLInputElement $element_Meta;
/*     */   private HTMLTableElement $element_MostrarMenu;
/*     */   private HTMLSelectElement $element_Norma;
/*     */   private HTMLInputElement $element_Numeral;
/*     */   private HTMLElement $element_NumeroAccion;
/*     */   private HTMLElement $element_NumeroCausa;
/*     */   private HTMLSelectElement $element_Objetivo;
/*     */   private HTMLSelectElement $element_Origen;
/*     */   private HTMLInputElement $element_Plan;
/*     */   private HTMLTextAreaElement $element_Porque;
/*     */   private HTMLSelectElement $element_Proceso;
/*     */   private HTMLInputElement $element_Recarga;
/*     */   private HTMLSelectElement $element_Responsable;
/*     */   private HTMLSelectElement $element_Subproceso;
/*     */   private HTMLTableElement $element_TblCausa;
/*     */   private HTMLInputElement $element_Tema;
/*     */   private HTMLDivElement $element_TiNumeral;
/*     */   private HTMLTableRowElement $element_TrAreas;
/*     */   private HTMLTableRowElement $element_TrModificacion;
/*     */   private HTMLInputElement $element__operacion;
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
/*     */   public static final String NAME__operacion = "_operacion";
/*     */   public static final String NAME_accion = "accion";
/*     */   public static final String NAME_area = "area";
/*     */   public static final String NAME_asociado = "asociado";
/*     */   public static final String NAME_beneficio = "beneficio";
/*     */   public static final String NAME_ciclo = "ciclo";
/*     */   public static final String NAME_clickcontrol2 = "clickcontrol2";
/*     */   public static final String NAME_codigoLogro = "codigoLogro";
/*     */   public static final String NAME_descripcion = "descripcion";
/*     */   public static final String NAME_fechaEstimadaTerminacion = "fechaEstimadaTerminacion";
/*     */   public static final String NAME_ff = "ff";
/*     */   public static final String NAME_impacto = "impacto";
/*     */   public static final String NAME_justificacion = "justificacion";
/*     */   public static final String NAME_meta = "meta";
/*     */   public static final String NAME_miBoton = "miBoton";
/*     */   public static final String NAME_norma = "norma";
/*     */   public static final String NAME_numeral = "numeral";
/*     */   public static final String NAME_numero = "numero";
/*     */   public static final String NAME_objetivo = "objetivo";
/*     */   public static final String NAME_origen = "origen";
/*     */   public static final String NAME_plan = "plan";
/*     */   public static final String NAME_porque = "porque";
/*     */   public static final String NAME_proceso = "proceso";
/*     */   public static final String NAME_recarga = "recarga";
/*     */   public static final String NAME_responsable = "responsable";
/*     */   public static final String NAME_subproceso = "subproceso";
/*     */   public static final String NAME_tema = "tema";
/*     */   public static final String NAME_txtContAccion = "txtContAccion";
/*     */   public static final String NAME_txtContBeneficio = "txtContBeneficio";
/*     */   public static final String NAME_txtContDescripcion = "txtContDescripcion";
/*     */   public static final String NAME_txtContPorque = "txtContPorque";
/* 308 */   public static final Class XMLC_GENERATED_CLASS = AMActV2HTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/AMActV2.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 319 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AMActV2HTML() {
/* 330 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 332 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 340 */   public AMActV2HTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AMActV2HTML(AMActV2HTML src) {
/* 348 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 350 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 352 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AMActV2HTML(DocumentLoader loader, boolean buildDOM) {
/* 363 */     this.fDocumentLoader = loader;
/*     */     
/* 365 */     if (buildDOM)
/*     */     {
/* 367 */       buildDocument();
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
/* 379 */   public AMActV2HTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 387 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 389 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 397 */     cloneDeepCheck(deep);
/*     */     
/* 399 */     return new AMActV2HTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 407 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 415 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 424 */   public HTMLTextAreaElement getElementAccion() { return this.$element_Accion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 433 */   public HTMLSelectElement getElementArea() { return this.$element_Area; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 442 */   public HTMLInputElement getElementAsociado() { return this.$element_Asociado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 451 */   public HTMLElement getElementAyuda() { return this.$element_Ayuda; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 460 */   public HTMLTextAreaElement getElementBeneficio() { return this.$element_Beneficio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 469 */   public HTMLInputElement getElementBfechaEstimadaTerminacion() { return this.$element_BfechaEstimadaTerminacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 478 */   public HTMLInputElement getElementCiclo() { return this.$element_Ciclo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 487 */   public HTMLInputElement getElementCodigoLogro() { return this.$element_CodigoLogro; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 496 */   public HTMLDivElement getElementDArea() { return this.$element_DArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 505 */   public HTMLTextAreaElement getElementDescripcion() { return this.$element_Descripcion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 514 */   public HTMLTableElement getElementDetalle() { return this.$element_Detalle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 523 */   public HTMLTableElement getElementDetalleRes() { return this.$element_DetalleRes; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 532 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 541 */   public HTMLInputElement getElementFechaEstimadaTerminacion() { return this.$element_FechaEstimadaTerminacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 550 */   public HTMLInputElement getElementIdNumero() { return this.$element_IdNumero; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 559 */   public HTMLSelectElement getElementImpacto() { return this.$element_Impacto; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 568 */   public HTMLInputElement getElementMeta() { return this.$element_Meta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 577 */   public HTMLTableElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 586 */   public HTMLSelectElement getElementNorma() { return this.$element_Norma; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 595 */   public HTMLInputElement getElementNumeral() { return this.$element_Numeral; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 604 */   public HTMLElement getElementNumeroAccion() { return this.$element_NumeroAccion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 613 */   public HTMLElement getElementNumeroCausa() { return this.$element_NumeroCausa; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 622 */   public HTMLSelectElement getElementObjetivo() { return this.$element_Objetivo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 631 */   public HTMLSelectElement getElementOrigen() { return this.$element_Origen; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 640 */   public HTMLInputElement getElementPlan() { return this.$element_Plan; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 649 */   public HTMLTextAreaElement getElementPorque() { return this.$element_Porque; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 658 */   public HTMLSelectElement getElementProceso() { return this.$element_Proceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 667 */   public HTMLInputElement getElementRecarga() { return this.$element_Recarga; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 676 */   public HTMLSelectElement getElementResponsable() { return this.$element_Responsable; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 685 */   public HTMLSelectElement getElementSubproceso() { return this.$element_Subproceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 694 */   public HTMLTableElement getElementTblCausa() { return this.$element_TblCausa; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 703 */   public HTMLInputElement getElementTema() { return this.$element_Tema; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 712 */   public HTMLDivElement getElementTiNumeral() { return this.$element_TiNumeral; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 721 */   public HTMLTableRowElement getElementTrAreas() { return this.$element_TrAreas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 730 */   public HTMLTableRowElement getElementTrModificacion() { return this.$element_TrModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 739 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 748 */   public void setTextAccion(String text) { doSetText(this.$element_Accion, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 757 */   public void setTextAyuda(String text) { doSetText(this.$element_Ayuda, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 766 */   public void setTextBeneficio(String text) { doSetText(this.$element_Beneficio, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 775 */   public void setTextDArea(String text) { doSetText(this.$element_DArea, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 784 */   public void setTextDescripcion(String text) { doSetText(this.$element_Descripcion, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 793 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 802 */   public void setTextNumeroAccion(String text) { doSetText(this.$element_NumeroAccion, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 811 */   public void setTextNumeroCausa(String text) { doSetText(this.$element_NumeroCausa, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 820 */   public void setTextPorque(String text) { doSetText(this.$element_Porque, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 829 */   public void setTextTiNumeral(String text) { doSetText(this.$element_TiNumeral, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 838 */     if (node.getNodeType() != 9)
/*     */     {
/* 840 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 844 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 848 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 850 */       int substStart = "$element_".length();
/*     */       
/* 852 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 854 */         Field f = fs[i];
/*     */         
/* 856 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 858 */           String id = f.getName().substring(substStart);
/*     */           
/* 860 */           Node idNode = doc.getElementById(id);
/*     */           
/* 862 */           if (idNode == null) {
/*     */             
/* 864 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 866 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 870 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 874 */     } catch (Exception e) {
/*     */       
/* 876 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AMActV2HTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */