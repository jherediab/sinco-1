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
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.presentation.RepProntitudHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RepProntitudHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLSelectElement $element_Anno;
/*     */   private HTMLSelectElement $element_AreaProveedor;
/*     */   private HTMLTableSectionElement $element_Detalle;
/*     */   private HTMLTableSectionElement $element_DetalleServicio;
/*     */   private HTMLTableSectionElement $element_DetalleSolicitud;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLInputElement $element_Exportar;
/*     */   private HTMLElement $element_LineaBase;
/*     */   private HTMLElement $element_LineaBaseDet;
/*     */   private HTMLElement $element_LineaMovil;
/*     */   private HTMLElement $element_LineaMovilDet;
/*     */   private HTMLSelectElement $element_Mes1;
/*     */   private HTMLElement $element_NombreArea;
/*     */   private HTMLElement $element_NombreServicio;
/*     */   private HTMLElement $element_NroRegistros;
/*     */   private HTMLElement $element_NroRegistrosDet;
/*     */   private HTMLElement $element_NroRegistrosSol;
/*     */   private HTMLElement $element_Prontitud;
/*     */   private HTMLElement $element_ProntitudDet;
/*     */   private HTMLElement $element_Tiempo;
/*     */   private HTMLDivElement $element_TrConsulta;
/*     */   private HTMLDivElement $element_TrDetallado;
/*     */   private HTMLDivElement $element_TrResultados;
/*     */   private HTMLDivElement $element_TrSolicitudes;
/*     */   public static final String CLASS_BOT = "BOT";
/*     */   public static final String CLASS_INP = "INP";
/*     */   public static final String CLASS_PIE = "PIE";
/*     */   public static final String CLASS_PIED = "PIED";
/*     */   public static final String CLASS_ca = "ca";
/*     */   public static final String CLASS_cb = "cb";
/*     */   public static final String CLASS_cf1 = "cf1";
/*     */   public static final String CLASS_cf2 = "cf2";
/*     */   public static final String CLASS_container = "container";
/*     */   public static final String CLASS_dat = "dat";
/*     */   public static final String CLASS_dat2 = "dat2";
/*     */   public static final String CLASS_resizable = "resizable";
/*     */   public static final String CLASS_sortable = "sortable";
/*     */   public static final String CLASS_tabf = "tabf";
/*     */   public static final String CLASS_tit = "tit";
/*     */   public static final String NAME__operacion = "_operacion";
/*     */   public static final String NAME_anno = "anno";
/*     */   public static final String NAME_areaProveedor = "areaProveedor";
/*     */   public static final String NAME_exportar = "exportar";
/*     */   public static final String NAME_mes1 = "mes1";
/*     */   public static final String NAME_miBoton = "miBoton";
/*     */   public static final String NAME_miForma = "miForma";
/* 179 */   public static final Class XMLC_GENERATED_CLASS = RepProntitudHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/RepProntitud.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 190 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RepProntitudHTML() {
/* 201 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 203 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 211 */   public RepProntitudHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RepProntitudHTML(RepProntitudHTML src) {
/* 219 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 221 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 223 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RepProntitudHTML(DocumentLoader loader, boolean buildDOM) {
/* 234 */     this.fDocumentLoader = loader;
/*     */     
/* 236 */     if (buildDOM)
/*     */     {
/* 238 */       buildDocument();
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
/* 250 */   public RepProntitudHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 258 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 260 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 268 */     cloneDeepCheck(deep);
/*     */     
/* 270 */     return new RepProntitudHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 278 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 286 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 295 */   public HTMLSelectElement getElementAnno() { return this.$element_Anno; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 304 */   public HTMLSelectElement getElementAreaProveedor() { return this.$element_AreaProveedor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 313 */   public HTMLTableSectionElement getElementDetalle() { return this.$element_Detalle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 322 */   public HTMLTableSectionElement getElementDetalleServicio() { return this.$element_DetalleServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 331 */   public HTMLTableSectionElement getElementDetalleSolicitud() { return this.$element_DetalleSolicitud; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 340 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 349 */   public HTMLInputElement getElementExportar() { return this.$element_Exportar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 358 */   public HTMLElement getElementLineaBase() { return this.$element_LineaBase; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 367 */   public HTMLElement getElementLineaBaseDet() { return this.$element_LineaBaseDet; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 376 */   public HTMLElement getElementLineaMovil() { return this.$element_LineaMovil; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 385 */   public HTMLElement getElementLineaMovilDet() { return this.$element_LineaMovilDet; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 394 */   public HTMLSelectElement getElementMes1() { return this.$element_Mes1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 403 */   public HTMLElement getElementNombreArea() { return this.$element_NombreArea; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 412 */   public HTMLElement getElementNombreServicio() { return this.$element_NombreServicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 421 */   public HTMLElement getElementNroRegistros() { return this.$element_NroRegistros; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 430 */   public HTMLElement getElementNroRegistrosDet() { return this.$element_NroRegistrosDet; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 439 */   public HTMLElement getElementNroRegistrosSol() { return this.$element_NroRegistrosSol; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 448 */   public HTMLElement getElementProntitud() { return this.$element_Prontitud; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 457 */   public HTMLElement getElementProntitudDet() { return this.$element_ProntitudDet; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 466 */   public HTMLElement getElementTiempo() { return this.$element_Tiempo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 475 */   public HTMLDivElement getElementTrConsulta() { return this.$element_TrConsulta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 484 */   public HTMLDivElement getElementTrDetallado() { return this.$element_TrDetallado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 493 */   public HTMLDivElement getElementTrResultados() { return this.$element_TrResultados; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 502 */   public HTMLDivElement getElementTrSolicitudes() { return this.$element_TrSolicitudes; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 511 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 520 */   public void setTextLineaBase(String text) { doSetText(this.$element_LineaBase, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 529 */   public void setTextLineaBaseDet(String text) { doSetText(this.$element_LineaBaseDet, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 538 */   public void setTextLineaMovil(String text) { doSetText(this.$element_LineaMovil, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 547 */   public void setTextLineaMovilDet(String text) { doSetText(this.$element_LineaMovilDet, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 556 */   public void setTextNombreArea(String text) { doSetText(this.$element_NombreArea, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 565 */   public void setTextNombreServicio(String text) { doSetText(this.$element_NombreServicio, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 574 */   public void setTextNroRegistros(String text) { doSetText(this.$element_NroRegistros, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 583 */   public void setTextNroRegistrosDet(String text) { doSetText(this.$element_NroRegistrosDet, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 592 */   public void setTextNroRegistrosSol(String text) { doSetText(this.$element_NroRegistrosSol, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 601 */   public void setTextProntitud(String text) { doSetText(this.$element_Prontitud, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 610 */   public void setTextProntitudDet(String text) { doSetText(this.$element_ProntitudDet, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 619 */   public void setTextTiempo(String text) { doSetText(this.$element_Tiempo, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 628 */   public void setTextTrConsulta(String text) { doSetText(this.$element_TrConsulta, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 637 */   public void setTextTrDetallado(String text) { doSetText(this.$element_TrDetallado, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 646 */   public void setTextTrResultados(String text) { doSetText(this.$element_TrResultados, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 655 */   public void setTextTrSolicitudes(String text) { doSetText(this.$element_TrSolicitudes, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 664 */     if (node.getNodeType() != 9)
/*     */     {
/* 666 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 670 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 674 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 676 */       int substStart = "$element_".length();
/*     */       
/* 678 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 680 */         Field f = fs[i];
/*     */         
/* 682 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 684 */           String id = f.getName().substring(substStart);
/*     */           
/* 686 */           Node idNode = doc.getElementById(id);
/*     */           
/* 688 */           if (idNode == null) {
/*     */             
/* 690 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 692 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 696 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 700 */     } catch (Exception e) {
/*     */       
/* 702 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\RepProntitudHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */