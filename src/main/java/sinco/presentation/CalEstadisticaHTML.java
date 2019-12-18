/*      */ package sinco.presentation;
/*      */ 
/*      */ import java.lang.reflect.Field;
/*      */ import org.enhydra.xml.xmlc.XMLCRuntimeException;
/*      */ import org.enhydra.xml.xmlc.XMLObject;
/*      */ import org.enhydra.xml.xmlc.deferredparsing.DocumentLoader;
/*      */ import org.enhydra.xml.xmlc.deferredparsing.StandardDocumentLoader;
/*      */ import org.enhydra.xml.xmlc.dom.XMLCDomFactory;
/*      */ import org.enhydra.xml.xmlc.dom.XMLCDomFactoryCache;
/*      */ import org.enhydra.xml.xmlc.html.HTMLObject;
/*      */ import org.enhydra.xml.xmlc.html.HTMLObjectImpl;
/*      */ import org.w3c.dom.Document;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.html.HTMLDivElement;
/*      */ import org.w3c.dom.html.HTMLElement;
/*      */ import org.w3c.dom.html.HTMLInputElement;
/*      */ import org.w3c.dom.html.HTMLScriptElement;
/*      */ import org.w3c.dom.html.HTMLSelectElement;
/*      */ import org.w3c.dom.html.HTMLTableCellElement;
/*      */ import org.w3c.dom.html.HTMLTableElement;
/*      */ import org.w3c.dom.html.HTMLTableRowElement;
/*      */ import sinco.presentation.CalEstadisticaHTML;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CalEstadisticaHTML
/*      */   extends HTMLObjectImpl
/*      */   implements XMLObject, HTMLObject
/*      */ {
/*      */   private HTMLSelectElement $element_Area;
/*      */   private HTMLInputElement $element_AreaEA;
/*      */   private HTMLInputElement $element_AreaPRO;
/*      */   private HTMLInputElement $element_AreaRES;
/*      */   private HTMLInputElement $element_AreaSUB;
/*      */   private HTMLInputElement $element_AreasHijasPRO;
/*      */   private HTMLInputElement $element_AreasHijasRES;
/*      */   private HTMLInputElement $element_AreasHijasSUB;
/*      */   private HTMLInputElement $element_CheckBox;
/*      */   private HTMLInputElement $element_CicloEA;
/*      */   private HTMLInputElement $element_CicloPRO;
/*      */   private HTMLInputElement $element_CicloRES;
/*      */   private HTMLInputElement $element_CicloSUB;
/*      */   private HTMLElement $element_Cumplimiento;
/*      */   private HTMLScriptElement $element_ElMenu;
/*      */   private HTMLSelectElement $element_IdCiclo;
/*      */   private HTMLSelectElement $element_IdTipoProceso;
/*      */   private HTMLTableElement $element_MostrarMenu;
/*      */   private HTMLElement $element_NombreAreaEA;
/*      */   private HTMLElement $element_NombreAreaPRO;
/*      */   private HTMLElement $element_NombreAreaRES;
/*      */   private HTMLElement $element_NombreAreaSUB;
/*      */   private HTMLElement $element_NombreCicloEA;
/*      */   private HTMLElement $element_NombreCicloPRO;
/*      */   private HTMLElement $element_NombreCicloRES;
/*      */   private HTMLElement $element_NombreCicloSUB;
/*      */   private HTMLElement $element_NombrePeriodo1EA;
/*      */   private HTMLElement $element_NombrePeriodo1PRO;
/*      */   private HTMLElement $element_NombrePeriodo1RES;
/*      */   private HTMLElement $element_NombrePeriodo1SUB;
/*      */   private HTMLElement $element_NombrePeriodo2EA;
/*      */   private HTMLElement $element_NombrePeriodo2PRO;
/*      */   private HTMLElement $element_NombrePeriodo2RES;
/*      */   private HTMLElement $element_NombrePeriodo2SUB;
/*      */   private HTMLSelectElement $element_Periodo1;
/*      */   private HTMLInputElement $element_Periodo1PRO;
/*      */   private HTMLInputElement $element_Periodo1RES;
/*      */   private HTMLInputElement $element_Periodo1SUB;
/*      */   private HTMLSelectElement $element_Periodo2;
/*      */   private HTMLInputElement $element_Periodo2PRO;
/*      */   private HTMLInputElement $element_Periodo2RES;
/*      */   private HTMLInputElement $element_Periodo2SUB;
/*      */   private HTMLInputElement $element_PlanEA;
/*      */   private HTMLInputElement $element_PlanPRO;
/*      */   private HTMLInputElement $element_PlanRES;
/*      */   private HTMLInputElement $element_PlanSUB;
/*      */   private HTMLSelectElement $element_Proceso;
/*      */   private HTMLInputElement $element_ProcesoEA;
/*      */   private HTMLInputElement $element_ProcesoPRO;
/*      */   private HTMLInputElement $element_ProcesoRES;
/*      */   private HTMLInputElement $element_ProcesoSUB;
/*      */   private HTMLInputElement $element_Reporte;
/*      */   private HTMLTableElement $element_TblEstArea;
/*      */   private HTMLTableElement $element_TblEstRES;
/*      */   private HTMLTableElement $element_TblPRO;
/*      */   private HTMLTableElement $element_TblSUB;
/*      */   private HTMLInputElement $element_TipoProcesoPRO;
/*      */   private HTMLInputElement $element_TipoProcesoRES;
/*      */   private HTMLInputElement $element_TipoProcesoSUB;
/*      */   private HTMLTableRowElement $element_TrArea;
/*      */   private HTMLTableCellElement $element_TrEfPRO1;
/*      */   private HTMLTableCellElement $element_TrEfPRO2;
/*      */   private HTMLTableCellElement $element_TrEfSUB1;
/*      */   private HTMLTableCellElement $element_TrEfSUB2;
/*      */   private HTMLDivElement $element_TrEstArea;
/*      */   private HTMLDivElement $element_TrEstRES;
/*      */   private HTMLDivElement $element_TrProcesos;
/*      */   private HTMLDivElement $element_TrSeleccionar;
/*      */   private HTMLDivElement $element_TrSubprocesos;
/*      */   private HTMLTableCellElement $element_TrTit1;
/*      */   private HTMLTableCellElement $element_TrTit2;
/*      */   public static final String CLASS_BOT = "BOT";
/*      */   public static final String CLASS_INP = "INP";
/*      */   public static final String CLASS_PIE = "PIE";
/*      */   public static final String CLASS_ca = "ca";
/*      */   public static final String CLASS_cb = "cb";
/*      */   public static final String CLASS_cf1 = "cf1";
/*      */   public static final String CLASS_cf2 = "cf2";
/*      */   public static final String CLASS_container = "container";
/*      */   public static final String CLASS_dat = "dat";
/*      */   public static final String CLASS_dat2 = "dat2";
/*      */   public static final String CLASS_tabf = "tabf";
/*      */   public static final String CLASS_tabw = "tabw";
/*      */   public static final String CLASS_tit = "tit";
/*      */   public static final String NAME_area = "area";
/*      */   public static final String NAME_areasHijas = "areasHijas";
/*      */   public static final String NAME_ciclo = "ciclo";
/*      */   public static final String NAME_miBoton = "miBoton";
/*      */   public static final String NAME_miForma = "miForma";
/*      */   public static final String NAME_miForma3 = "miForma3";
/*      */   public static final String NAME_operacion = "operacion";
/*      */   public static final String NAME_periodo1 = "periodo1";
/*      */   public static final String NAME_periodo2 = "periodo2";
/*      */   public static final String NAME_plan = "plan";
/*      */   public static final String NAME_proceso = "proceso";
/*      */   public static final String NAME_reporte = "reporte";
/*      */   public static final String NAME_tipoProceso = "tipoProceso";
/*  293 */   public static final Class XMLC_GENERATED_CLASS = CalEstadisticaHTML.class;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/CalEstadistica.html";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  304 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final DocumentLoader fDocumentLoader;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CalEstadisticaHTML() {
/*  315 */     this(StandardDocumentLoader.getInstance());
/*      */     
/*  317 */     buildDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  325 */   public CalEstadisticaHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CalEstadisticaHTML(CalEstadisticaHTML src) {
/*  333 */     this.fDocumentLoader = src.getDocumentLoader();
/*      */     
/*  335 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*      */     
/*  337 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CalEstadisticaHTML(DocumentLoader loader, boolean buildDOM) {
/*  348 */     this.fDocumentLoader = loader;
/*      */     
/*  350 */     if (buildDOM)
/*      */     {
/*  352 */       buildDocument();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  364 */   public CalEstadisticaHTML(DocumentLoader loader) { this(loader, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void buildDocument() {
/*  372 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*      */     
/*  374 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node cloneNode(boolean deep) {
/*  382 */     cloneDeepCheck(deep);
/*      */     
/*  384 */     return new CalEstadisticaHTML(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  392 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  400 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  409 */   public HTMLSelectElement getElementArea() { return this.$element_Area; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  418 */   public HTMLInputElement getElementAreaEA() { return this.$element_AreaEA; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  427 */   public HTMLInputElement getElementAreaPRO() { return this.$element_AreaPRO; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  436 */   public HTMLInputElement getElementAreaRES() { return this.$element_AreaRES; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  445 */   public HTMLInputElement getElementAreaSUB() { return this.$element_AreaSUB; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  454 */   public HTMLInputElement getElementAreasHijasPRO() { return this.$element_AreasHijasPRO; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  463 */   public HTMLInputElement getElementAreasHijasRES() { return this.$element_AreasHijasRES; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  472 */   public HTMLInputElement getElementAreasHijasSUB() { return this.$element_AreasHijasSUB; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  481 */   public HTMLInputElement getElementCheckBox() { return this.$element_CheckBox; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  490 */   public HTMLInputElement getElementCicloEA() { return this.$element_CicloEA; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  499 */   public HTMLInputElement getElementCicloPRO() { return this.$element_CicloPRO; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  508 */   public HTMLInputElement getElementCicloRES() { return this.$element_CicloRES; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  517 */   public HTMLInputElement getElementCicloSUB() { return this.$element_CicloSUB; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  526 */   public HTMLElement getElementCumplimiento() { return this.$element_Cumplimiento; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  535 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  544 */   public HTMLSelectElement getElementIdCiclo() { return this.$element_IdCiclo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  553 */   public HTMLSelectElement getElementIdTipoProceso() { return this.$element_IdTipoProceso; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  562 */   public HTMLTableElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  571 */   public HTMLElement getElementNombreAreaEA() { return this.$element_NombreAreaEA; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  580 */   public HTMLElement getElementNombreAreaPRO() { return this.$element_NombreAreaPRO; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  589 */   public HTMLElement getElementNombreAreaRES() { return this.$element_NombreAreaRES; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  598 */   public HTMLElement getElementNombreAreaSUB() { return this.$element_NombreAreaSUB; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  607 */   public HTMLElement getElementNombreCicloEA() { return this.$element_NombreCicloEA; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  616 */   public HTMLElement getElementNombreCicloPRO() { return this.$element_NombreCicloPRO; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  625 */   public HTMLElement getElementNombreCicloRES() { return this.$element_NombreCicloRES; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  634 */   public HTMLElement getElementNombreCicloSUB() { return this.$element_NombreCicloSUB; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  643 */   public HTMLElement getElementNombrePeriodo1EA() { return this.$element_NombrePeriodo1EA; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  652 */   public HTMLElement getElementNombrePeriodo1PRO() { return this.$element_NombrePeriodo1PRO; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  661 */   public HTMLElement getElementNombrePeriodo1RES() { return this.$element_NombrePeriodo1RES; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  670 */   public HTMLElement getElementNombrePeriodo1SUB() { return this.$element_NombrePeriodo1SUB; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  679 */   public HTMLElement getElementNombrePeriodo2EA() { return this.$element_NombrePeriodo2EA; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  688 */   public HTMLElement getElementNombrePeriodo2PRO() { return this.$element_NombrePeriodo2PRO; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  697 */   public HTMLElement getElementNombrePeriodo2RES() { return this.$element_NombrePeriodo2RES; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  706 */   public HTMLElement getElementNombrePeriodo2SUB() { return this.$element_NombrePeriodo2SUB; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  715 */   public HTMLSelectElement getElementPeriodo1() { return this.$element_Periodo1; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  724 */   public HTMLInputElement getElementPeriodo1PRO() { return this.$element_Periodo1PRO; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  733 */   public HTMLInputElement getElementPeriodo1RES() { return this.$element_Periodo1RES; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  742 */   public HTMLInputElement getElementPeriodo1SUB() { return this.$element_Periodo1SUB; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  751 */   public HTMLSelectElement getElementPeriodo2() { return this.$element_Periodo2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  760 */   public HTMLInputElement getElementPeriodo2PRO() { return this.$element_Periodo2PRO; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  769 */   public HTMLInputElement getElementPeriodo2RES() { return this.$element_Periodo2RES; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  778 */   public HTMLInputElement getElementPeriodo2SUB() { return this.$element_Periodo2SUB; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  787 */   public HTMLInputElement getElementPlanEA() { return this.$element_PlanEA; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  796 */   public HTMLInputElement getElementPlanPRO() { return this.$element_PlanPRO; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  805 */   public HTMLInputElement getElementPlanRES() { return this.$element_PlanRES; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  814 */   public HTMLInputElement getElementPlanSUB() { return this.$element_PlanSUB; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  823 */   public HTMLSelectElement getElementProceso() { return this.$element_Proceso; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  832 */   public HTMLInputElement getElementProcesoEA() { return this.$element_ProcesoEA; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  841 */   public HTMLInputElement getElementProcesoPRO() { return this.$element_ProcesoPRO; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  850 */   public HTMLInputElement getElementProcesoRES() { return this.$element_ProcesoRES; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  859 */   public HTMLInputElement getElementProcesoSUB() { return this.$element_ProcesoSUB; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  868 */   public HTMLInputElement getElementReporte() { return this.$element_Reporte; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  877 */   public HTMLTableElement getElementTblEstArea() { return this.$element_TblEstArea; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  886 */   public HTMLTableElement getElementTblEstRES() { return this.$element_TblEstRES; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  895 */   public HTMLTableElement getElementTblPRO() { return this.$element_TblPRO; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  904 */   public HTMLTableElement getElementTblSUB() { return this.$element_TblSUB; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  913 */   public HTMLInputElement getElementTipoProcesoPRO() { return this.$element_TipoProcesoPRO; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  922 */   public HTMLInputElement getElementTipoProcesoRES() { return this.$element_TipoProcesoRES; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  931 */   public HTMLInputElement getElementTipoProcesoSUB() { return this.$element_TipoProcesoSUB; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  940 */   public HTMLTableRowElement getElementTrArea() { return this.$element_TrArea; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  949 */   public HTMLTableCellElement getElementTrEfPRO1() { return this.$element_TrEfPRO1; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  958 */   public HTMLTableCellElement getElementTrEfPRO2() { return this.$element_TrEfPRO2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  967 */   public HTMLTableCellElement getElementTrEfSUB1() { return this.$element_TrEfSUB1; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  976 */   public HTMLTableCellElement getElementTrEfSUB2() { return this.$element_TrEfSUB2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  985 */   public HTMLDivElement getElementTrEstArea() { return this.$element_TrEstArea; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  994 */   public HTMLDivElement getElementTrEstRES() { return this.$element_TrEstRES; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1003 */   public HTMLDivElement getElementTrProcesos() { return this.$element_TrProcesos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1012 */   public HTMLDivElement getElementTrSeleccionar() { return this.$element_TrSeleccionar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1021 */   public HTMLDivElement getElementTrSubprocesos() { return this.$element_TrSubprocesos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1030 */   public HTMLTableCellElement getElementTrTit1() { return this.$element_TrTit1; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1039 */   public HTMLTableCellElement getElementTrTit2() { return this.$element_TrTit2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1048 */   public void setTextCumplimiento(String text) { doSetText(this.$element_Cumplimiento, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1057 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1066 */   public void setTextNombreAreaEA(String text) { doSetText(this.$element_NombreAreaEA, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1075 */   public void setTextNombreAreaPRO(String text) { doSetText(this.$element_NombreAreaPRO, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1084 */   public void setTextNombreAreaRES(String text) { doSetText(this.$element_NombreAreaRES, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1093 */   public void setTextNombreAreaSUB(String text) { doSetText(this.$element_NombreAreaSUB, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1102 */   public void setTextNombreCicloEA(String text) { doSetText(this.$element_NombreCicloEA, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1111 */   public void setTextNombreCicloPRO(String text) { doSetText(this.$element_NombreCicloPRO, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1120 */   public void setTextNombreCicloRES(String text) { doSetText(this.$element_NombreCicloRES, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1129 */   public void setTextNombreCicloSUB(String text) { doSetText(this.$element_NombreCicloSUB, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1138 */   public void setTextNombrePeriodo1EA(String text) { doSetText(this.$element_NombrePeriodo1EA, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1147 */   public void setTextNombrePeriodo1PRO(String text) { doSetText(this.$element_NombrePeriodo1PRO, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1156 */   public void setTextNombrePeriodo1RES(String text) { doSetText(this.$element_NombrePeriodo1RES, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1165 */   public void setTextNombrePeriodo1SUB(String text) { doSetText(this.$element_NombrePeriodo1SUB, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1174 */   public void setTextNombrePeriodo2EA(String text) { doSetText(this.$element_NombrePeriodo2EA, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1183 */   public void setTextNombrePeriodo2PRO(String text) { doSetText(this.$element_NombrePeriodo2PRO, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1192 */   public void setTextNombrePeriodo2RES(String text) { doSetText(this.$element_NombrePeriodo2RES, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1201 */   public void setTextNombrePeriodo2SUB(String text) { doSetText(this.$element_NombrePeriodo2SUB, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1210 */   public void setTextTrEfPRO1(String text) { doSetText(this.$element_TrEfPRO1, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1219 */   public void setTextTrEfPRO2(String text) { doSetText(this.$element_TrEfPRO2, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1228 */   public void setTextTrEfSUB1(String text) { doSetText(this.$element_TrEfSUB1, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1237 */   public void setTextTrEfSUB2(String text) { doSetText(this.$element_TrEfSUB2, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1246 */   public void setTextTrEstArea(String text) { doSetText(this.$element_TrEstArea, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1255 */   public void setTextTrEstRES(String text) { doSetText(this.$element_TrEstRES, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1264 */   public void setTextTrProcesos(String text) { doSetText(this.$element_TrProcesos, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1273 */   public void setTextTrSeleccionar(String text) { doSetText(this.$element_TrSeleccionar, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1282 */   public void setTextTrSubprocesos(String text) { doSetText(this.$element_TrSubprocesos, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1291 */   public void setTextTrTit1(String text) { doSetText(this.$element_TrTit1, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1300 */   public void setTextTrTit2(String text) { doSetText(this.$element_TrTit2, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void syncWithDocument(Node node) {
/* 1309 */     if (node.getNodeType() != 9)
/*      */     {
/* 1311 */       throw new XMLCRuntimeException("Node must be a document node");
/*      */     }
/*      */ 
/*      */     
/* 1315 */     Document doc = (Document)node;
/*      */ 
/*      */     
/*      */     try {
/* 1319 */       Field[] fs = getClass().getDeclaredFields();
/*      */       
/* 1321 */       int substStart = "$element_".length();
/*      */       
/* 1323 */       for (int i = 0; i < fs.length; i++) {
/*      */         
/* 1325 */         Field f = fs[i];
/*      */         
/* 1327 */         if (f.getName().startsWith("$element_")) {
/*      */           
/* 1329 */           String id = f.getName().substring(substStart);
/*      */           
/* 1331 */           Node idNode = doc.getElementById(id);
/*      */           
/* 1333 */           if (idNode == null) {
/*      */             
/* 1335 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*      */             
/* 1337 */             idNode = doc.getElementById(id);
/*      */           } 
/*      */ 
/*      */           
/* 1341 */           if (idNode != null) f.set(this, idNode);
/*      */         
/*      */         } 
/*      */       } 
/* 1345 */     } catch (Exception e) {
/*      */       
/* 1347 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalEstadisticaHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */