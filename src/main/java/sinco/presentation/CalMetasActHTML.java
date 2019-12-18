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
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.presentation.CalMetasActHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalMetasActHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLSelectElement $element_AplicaEn;
/*     */   private HTMLInputElement $element_BtnCrear;
/*     */   private HTMLInputElement $element_BtnGrabar;
/*     */   private HTMLInputElement $element_CodigoMeta;
/*     */   private HTMLInputElement $element_CodigoObjetivo;
/*     */   private HTMLInputElement $element_CodigoObjetivoC;
/*     */   private HTMLInputElement $element_Descripcion;
/*     */   private HTMLTableSectionElement $element_Detalle;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLSelectElement $element_Estado;
/*     */   private HTMLInputElement $element_FechaInsercion;
/*     */   private HTMLInputElement $element_FechaModificacion;
/*     */   private HTMLSelectElement $element_FuenteDato;
/*     */   private HTMLInputElement $element_IdProceso;
/*     */   private HTMLInputElement $element_Justificacion;
/*     */   private HTMLInputElement $element_Mes01;
/*     */   private HTMLInputElement $element_Mes02;
/*     */   private HTMLInputElement $element_Mes03;
/*     */   private HTMLInputElement $element_Mes04;
/*     */   private HTMLInputElement $element_Mes05;
/*     */   private HTMLInputElement $element_Mes06;
/*     */   private HTMLInputElement $element_Mes07;
/*     */   private HTMLInputElement $element_Mes08;
/*     */   private HTMLInputElement $element_Mes09;
/*     */   private HTMLInputElement $element_Mes10;
/*     */   private HTMLInputElement $element_Mes11;
/*     */   private HTMLInputElement $element_Mes12;
/*     */   private HTMLElement $element_MiObjetivo;
/*     */   private HTMLElement $element_MiProceso;
/*     */   private HTMLElement $element_MiSubproceso;
/*     */   private HTMLTableElement $element_MostrarMenu;
/*     */   private HTMLElement $element_NroRegistros;
/*     */   private HTMLSelectElement $element_TipoGrafica;
/*     */   private HTMLSelectElement $element_TipoMedicion;
/*     */   private HTMLDivElement $element_TrConsulta;
/*     */   private HTMLDivElement $element_TrCreacionRegistro;
/*     */   private HTMLDivElement $element_TrResultados;
/*     */   private HTMLSelectElement $element_UnidadMedida;
/*     */   private HTMLInputElement $element_UsuarioInsercion;
/*     */   private HTMLInputElement $element_UsuarioModificacion;
/*     */   private HTMLInputElement $element_ValorMaximo;
/*     */   private HTMLInputElement $element_ValorMeta;
/*     */   private HTMLInputElement $element_ValorMinimo;
/*     */   private HTMLInputElement $element__operacion;
/*     */   public static final String CLASS_BOT = "BOT";
/*     */   public static final String CLASS_IND = "IND";
/*     */   public static final String CLASS_INP = "INP";
/*     */   public static final String CLASS_PIE = "PIE";
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
/*     */   public static final String CLASS_tabw = "tabw";
/*     */   public static final String CLASS_tit = "tit";
/*     */   public static final String NAME__operacion = "_operacion";
/*     */   public static final String NAME_aplicaEn = "aplicaEn";
/*     */   public static final String NAME_codigoMeta = "codigoMeta";
/*     */   public static final String NAME_codigoObjetivo = "codigoObjetivo";
/*     */   public static final String NAME_descripcion = "descripcion";
/*     */   public static final String NAME_estado = "estado";
/*     */   public static final String NAME_fechaInsercion = "fechaInsercion";
/*     */   public static final String NAME_fechaModificacion = "fechaModificacion";
/*     */   public static final String NAME_fuenteDato = "fuenteDato";
/*     */   public static final String NAME_justificacion = "justificacion";
/*     */   public static final String NAME_mes01 = "mes01";
/*     */   public static final String NAME_mes02 = "mes02";
/*     */   public static final String NAME_mes03 = "mes03";
/*     */   public static final String NAME_mes04 = "mes04";
/*     */   public static final String NAME_mes05 = "mes05";
/*     */   public static final String NAME_mes06 = "mes06";
/*     */   public static final String NAME_mes07 = "mes07";
/*     */   public static final String NAME_mes08 = "mes08";
/*     */   public static final String NAME_mes09 = "mes09";
/*     */   public static final String NAME_mes10 = "mes10";
/*     */   public static final String NAME_mes11 = "mes11";
/*     */   public static final String NAME_mes12 = "mes12";
/*     */   public static final String NAME_miBotonO = "miBotonO";
/*     */   public static final String NAME_miForma = "miForma";
/*     */   public static final String NAME_miForma2 = "miForma2";
/*     */   public static final String NAME_proceso = "proceso";
/*     */   public static final String NAME_tipoGrafica = "tipoGrafica";
/*     */   public static final String NAME_tipoMedicion = "tipoMedicion";
/*     */   public static final String NAME_unidadMedida = "unidadMedida";
/*     */   public static final String NAME_usuarioInsercion = "usuarioInsercion";
/*     */   public static final String NAME_usuarioModificacion = "usuarioModificacion";
/*     */   public static final String NAME_valorMaximo = "valorMaximo";
/*     */   public static final String NAME_valorMeta = "valorMeta";
/*     */   public static final String NAME_valorMinimo = "valorMinimo";
/* 359 */   public static final Class XMLC_GENERATED_CLASS = CalMetasActHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/CalMetasAct.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 370 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalMetasActHTML() {
/* 381 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 383 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 391 */   public CalMetasActHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalMetasActHTML(CalMetasActHTML src) {
/* 399 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 401 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 403 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalMetasActHTML(DocumentLoader loader, boolean buildDOM) {
/* 414 */     this.fDocumentLoader = loader;
/*     */     
/* 416 */     if (buildDOM)
/*     */     {
/* 418 */       buildDocument();
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
/* 430 */   public CalMetasActHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 438 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 440 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 448 */     cloneDeepCheck(deep);
/*     */     
/* 450 */     return new CalMetasActHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 458 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 466 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 475 */   public HTMLSelectElement getElementAplicaEn() { return this.$element_AplicaEn; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 484 */   public HTMLInputElement getElementBtnCrear() { return this.$element_BtnCrear; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 493 */   public HTMLInputElement getElementBtnGrabar() { return this.$element_BtnGrabar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 502 */   public HTMLInputElement getElementCodigoMeta() { return this.$element_CodigoMeta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 511 */   public HTMLInputElement getElementCodigoObjetivo() { return this.$element_CodigoObjetivo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 520 */   public HTMLInputElement getElementCodigoObjetivoC() { return this.$element_CodigoObjetivoC; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 529 */   public HTMLInputElement getElementDescripcion() { return this.$element_Descripcion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 538 */   public HTMLTableSectionElement getElementDetalle() { return this.$element_Detalle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 547 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 556 */   public HTMLSelectElement getElementEstado() { return this.$element_Estado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 565 */   public HTMLInputElement getElementFechaInsercion() { return this.$element_FechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 574 */   public HTMLInputElement getElementFechaModificacion() { return this.$element_FechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 583 */   public HTMLSelectElement getElementFuenteDato() { return this.$element_FuenteDato; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 592 */   public HTMLInputElement getElementIdProceso() { return this.$element_IdProceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 601 */   public HTMLInputElement getElementJustificacion() { return this.$element_Justificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 610 */   public HTMLInputElement getElementMes01() { return this.$element_Mes01; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 619 */   public HTMLInputElement getElementMes02() { return this.$element_Mes02; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 628 */   public HTMLInputElement getElementMes03() { return this.$element_Mes03; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 637 */   public HTMLInputElement getElementMes04() { return this.$element_Mes04; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 646 */   public HTMLInputElement getElementMes05() { return this.$element_Mes05; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 655 */   public HTMLInputElement getElementMes06() { return this.$element_Mes06; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 664 */   public HTMLInputElement getElementMes07() { return this.$element_Mes07; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 673 */   public HTMLInputElement getElementMes08() { return this.$element_Mes08; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 682 */   public HTMLInputElement getElementMes09() { return this.$element_Mes09; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 691 */   public HTMLInputElement getElementMes10() { return this.$element_Mes10; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 700 */   public HTMLInputElement getElementMes11() { return this.$element_Mes11; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 709 */   public HTMLInputElement getElementMes12() { return this.$element_Mes12; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 718 */   public HTMLElement getElementMiObjetivo() { return this.$element_MiObjetivo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 727 */   public HTMLElement getElementMiProceso() { return this.$element_MiProceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 736 */   public HTMLElement getElementMiSubproceso() { return this.$element_MiSubproceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 745 */   public HTMLTableElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 754 */   public HTMLElement getElementNroRegistros() { return this.$element_NroRegistros; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 763 */   public HTMLSelectElement getElementTipoGrafica() { return this.$element_TipoGrafica; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 772 */   public HTMLSelectElement getElementTipoMedicion() { return this.$element_TipoMedicion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 781 */   public HTMLDivElement getElementTrConsulta() { return this.$element_TrConsulta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 790 */   public HTMLDivElement getElementTrCreacionRegistro() { return this.$element_TrCreacionRegistro; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 799 */   public HTMLDivElement getElementTrResultados() { return this.$element_TrResultados; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 808 */   public HTMLSelectElement getElementUnidadMedida() { return this.$element_UnidadMedida; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 817 */   public HTMLInputElement getElementUsuarioInsercion() { return this.$element_UsuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 826 */   public HTMLInputElement getElementUsuarioModificacion() { return this.$element_UsuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 835 */   public HTMLInputElement getElementValorMaximo() { return this.$element_ValorMaximo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 844 */   public HTMLInputElement getElementValorMeta() { return this.$element_ValorMeta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 853 */   public HTMLInputElement getElementValorMinimo() { return this.$element_ValorMinimo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 862 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 871 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 880 */   public void setTextMiObjetivo(String text) { doSetText(this.$element_MiObjetivo, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 889 */   public void setTextMiProceso(String text) { doSetText(this.$element_MiProceso, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 898 */   public void setTextMiSubproceso(String text) { doSetText(this.$element_MiSubproceso, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 907 */   public void setTextNroRegistros(String text) { doSetText(this.$element_NroRegistros, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 916 */   public void setTextTrConsulta(String text) { doSetText(this.$element_TrConsulta, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 925 */   public void setTextTrCreacionRegistro(String text) { doSetText(this.$element_TrCreacionRegistro, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 934 */   public void setTextTrResultados(String text) { doSetText(this.$element_TrResultados, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 943 */     if (node.getNodeType() != 9)
/*     */     {
/* 945 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 949 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 953 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 955 */       int substStart = "$element_".length();
/*     */       
/* 957 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 959 */         Field f = fs[i];
/*     */         
/* 961 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 963 */           String id = f.getName().substring(substStart);
/*     */           
/* 965 */           Node idNode = doc.getElementById(id);
/*     */           
/* 967 */           if (idNode == null) {
/*     */             
/* 969 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 971 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 975 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 979 */     } catch (Exception e) {
/*     */       
/* 981 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalMetasActHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */