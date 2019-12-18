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
/*      */ import org.w3c.dom.html.HTMLLabelElement;
/*      */ import org.w3c.dom.html.HTMLScriptElement;
/*      */ import org.w3c.dom.html.HTMLSelectElement;
/*      */ import org.w3c.dom.html.HTMLTableSectionElement;
/*      */ import sinco.presentation.AudInformesHTML;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ public class AudInformesHTML
/*      */   extends HTMLObjectImpl
/*      */   implements XMLObject, HTMLObject
/*      */ {
/*      */   private HTMLInputElement $element_Apoyo;
/*      */   private HTMLElement $element_ApoyoEd;
/*      */   private HTMLInputElement $element_BtnAyuda;
/*      */   private HTMLInputElement $element_BtnConsultar;
/*      */   private HTMLInputElement $element_BtnCrear;
/*      */   private HTMLInputElement $element_BtnEliminar;
/*      */   private HTMLInputElement $element_BtnGrabar;
/*      */   private HTMLInputElement $element_BtnModificar;
/*      */   private HTMLInputElement $element_BtnSalir;
/*      */   private HTMLInputElement $element_Codigo;
/*      */   private HTMLElement $element_CodigoEd;
/*      */   private HTMLInputElement $element_CodigoKey;
/*      */   private HTMLTableSectionElement $element_Detalle;
/*      */   private HTMLDivElement $element_DivConsulta;
/*      */   private HTMLDivElement $element_DivCreacionRegistro;
/*      */   private HTMLDivElement $element_DivEdicion;
/*      */   private HTMLDivElement $element_DivResultados;
/*      */   private HTMLScriptElement $element_ElMenu;
/*      */   private HTMLSelectElement $element_Estado;
/*      */   private HTMLElement $element_EstadoEd;
/*      */   private HTMLInputElement $element_Estrategico;
/*      */   private HTMLElement $element_EstrategicoEd;
/*      */   private HTMLInputElement $element_Evaluacion;
/*      */   private HTMLElement $element_EvaluacionEd;
/*      */   private HTMLInputElement $element_FechaInsercion;
/*      */   private HTMLElement $element_FechaInsercionEd;
/*      */   private HTMLInputElement $element_FechaModificacion;
/*      */   private HTMLElement $element_FechaModificacionEd;
/*      */   private HTMLInputElement $element_Fnombre;
/*      */   private HTMLSelectElement $element_FtipoInforme;
/*      */   private HTMLLabelElement $element_LabelNombre;
/*      */   private HTMLInputElement $element_Misional;
/*      */   private HTMLElement $element_MisionalEd;
/*      */   private HTMLInputElement $element_Nombre;
/*      */   private HTMLElement $element_NombreEd;
/*      */   private HTMLElement $element_NroRegistros;
/*      */   private HTMLSelectElement $element_Responsable;
/*      */   private HTMLElement $element_ResponsableEd;
/*      */   private HTMLSelectElement $element_TipoInforme;
/*      */   private HTMLElement $element_TipoInformeEd;
/*      */   private HTMLInputElement $element_UsuarioInsercion;
/*      */   private HTMLElement $element_UsuarioInsercionEd;
/*      */   private HTMLInputElement $element_UsuarioModificacion;
/*      */   private HTMLElement $element_UsuarioModificacionEd;
/*      */   private HTMLInputElement $element__operacion;
/*      */   public static final String CLASS_BOB = "BOB";
/*      */   public static final String CLASS_BOT = "BOT";
/*      */   public static final String CLASS_IND = "IND";
/*      */   public static final String CLASS_INP = "INP";
/*      */   public static final String CLASS_PIE = "PIE";
/*      */   public static final String CLASS_ca = "ca";
/*      */   public static final String CLASS_cb = "cb";
/*      */   public static final String CLASS_cf1 = "cf1";
/*      */   public static final String CLASS_cf2 = "cf2";
/*      */   public static final String CLASS_container = "container";
/*      */   public static final String CLASS_dat = "dat";
/*      */   public static final String CLASS_dat2 = "dat2";
/*      */   public static final String CLASS_datc = "datc";
/*      */   public static final String CLASS_resizable = "resizable";
/*      */   public static final String CLASS_sortable = "sortable";
/*      */   public static final String CLASS_tabf = "tabf";
/*      */   public static final String CLASS_tit = "tit";
/*      */   public static final String NAME__operacion = "_operacion";
/*      */   public static final String NAME_apoyo = "apoyo";
/*      */   public static final String NAME_codigo = "codigo";
/*      */   public static final String NAME_estado = "estado";
/*      */   public static final String NAME_estrategico = "estrategico";
/*      */   public static final String NAME_evaluacion = "evaluacion";
/*      */   public static final String NAME_fechaInsercion = "fechaInsercion";
/*      */   public static final String NAME_fechaModificacion = "fechaModificacion";
/*      */   public static final String NAME_ff = "ff";
/*      */   public static final String NAME_labelNombre = "labelNombre";
/*      */   public static final String NAME_misional = "misional";
/*      */   public static final String NAME_nombre = "nombre";
/*      */   public static final String NAME_responsable = "responsable";
/*      */   public static final String NAME_tipoInforme = "tipoInforme";
/*      */   public static final String NAME_usuarioInsercion = "usuarioInsercion";
/*      */   public static final String NAME_usuarioModificacion = "usuarioModificacion";
/*  276 */   public static final Class XMLC_GENERATED_CLASS = AudInformesHTML.class;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/AudInformes.html";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  287 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final DocumentLoader fDocumentLoader;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AudInformesHTML() {
/*  298 */     this(StandardDocumentLoader.getInstance());
/*      */     
/*  300 */     buildDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  308 */   public AudInformesHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AudInformesHTML(AudInformesHTML src) {
/*  316 */     this.fDocumentLoader = src.getDocumentLoader();
/*      */     
/*  318 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*      */     
/*  320 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AudInformesHTML(DocumentLoader loader, boolean buildDOM) {
/*  331 */     this.fDocumentLoader = loader;
/*      */     
/*  333 */     if (buildDOM)
/*      */     {
/*  335 */       buildDocument();
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
/*  347 */   public AudInformesHTML(DocumentLoader loader) { this(loader, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void buildDocument() {
/*  355 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*      */     
/*  357 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node cloneNode(boolean deep) {
/*  365 */     cloneDeepCheck(deep);
/*      */     
/*  367 */     return new AudInformesHTML(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  375 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  383 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  392 */   public HTMLInputElement getElementApoyo() { return this.$element_Apoyo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  401 */   public HTMLElement getElementApoyoEd() { return this.$element_ApoyoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  410 */   public HTMLInputElement getElementBtnAyuda() { return this.$element_BtnAyuda; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  419 */   public HTMLInputElement getElementBtnConsultar() { return this.$element_BtnConsultar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  428 */   public HTMLInputElement getElementBtnCrear() { return this.$element_BtnCrear; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  437 */   public HTMLInputElement getElementBtnEliminar() { return this.$element_BtnEliminar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  446 */   public HTMLInputElement getElementBtnGrabar() { return this.$element_BtnGrabar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  455 */   public HTMLInputElement getElementBtnModificar() { return this.$element_BtnModificar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  464 */   public HTMLInputElement getElementBtnSalir() { return this.$element_BtnSalir; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  473 */   public HTMLInputElement getElementCodigo() { return this.$element_Codigo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  482 */   public HTMLElement getElementCodigoEd() { return this.$element_CodigoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  491 */   public HTMLInputElement getElementCodigoKey() { return this.$element_CodigoKey; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  500 */   public HTMLTableSectionElement getElementDetalle() { return this.$element_Detalle; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  509 */   public HTMLDivElement getElementDivConsulta() { return this.$element_DivConsulta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  518 */   public HTMLDivElement getElementDivCreacionRegistro() { return this.$element_DivCreacionRegistro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  527 */   public HTMLDivElement getElementDivEdicion() { return this.$element_DivEdicion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  536 */   public HTMLDivElement getElementDivResultados() { return this.$element_DivResultados; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  545 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  554 */   public HTMLSelectElement getElementEstado() { return this.$element_Estado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  563 */   public HTMLElement getElementEstadoEd() { return this.$element_EstadoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  572 */   public HTMLInputElement getElementEstrategico() { return this.$element_Estrategico; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  581 */   public HTMLElement getElementEstrategicoEd() { return this.$element_EstrategicoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  590 */   public HTMLInputElement getElementEvaluacion() { return this.$element_Evaluacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  599 */   public HTMLElement getElementEvaluacionEd() { return this.$element_EvaluacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  608 */   public HTMLInputElement getElementFechaInsercion() { return this.$element_FechaInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  617 */   public HTMLElement getElementFechaInsercionEd() { return this.$element_FechaInsercionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  626 */   public HTMLInputElement getElementFechaModificacion() { return this.$element_FechaModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  635 */   public HTMLElement getElementFechaModificacionEd() { return this.$element_FechaModificacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  644 */   public HTMLInputElement getElementFnombre() { return this.$element_Fnombre; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  653 */   public HTMLSelectElement getElementFtipoInforme() { return this.$element_FtipoInforme; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  662 */   public HTMLLabelElement getElementLabelNombre() { return this.$element_LabelNombre; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  671 */   public HTMLInputElement getElementMisional() { return this.$element_Misional; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  680 */   public HTMLElement getElementMisionalEd() { return this.$element_MisionalEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  689 */   public HTMLInputElement getElementNombre() { return this.$element_Nombre; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  698 */   public HTMLElement getElementNombreEd() { return this.$element_NombreEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  707 */   public HTMLElement getElementNroRegistros() { return this.$element_NroRegistros; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  716 */   public HTMLSelectElement getElementResponsable() { return this.$element_Responsable; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  725 */   public HTMLElement getElementResponsableEd() { return this.$element_ResponsableEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  734 */   public HTMLSelectElement getElementTipoInforme() { return this.$element_TipoInforme; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  743 */   public HTMLElement getElementTipoInformeEd() { return this.$element_TipoInformeEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  752 */   public HTMLInputElement getElementUsuarioInsercion() { return this.$element_UsuarioInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  761 */   public HTMLElement getElementUsuarioInsercionEd() { return this.$element_UsuarioInsercionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  770 */   public HTMLInputElement getElementUsuarioModificacion() { return this.$element_UsuarioModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  779 */   public HTMLElement getElementUsuarioModificacionEd() { return this.$element_UsuarioModificacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  788 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  797 */   public void setTextApoyoEd(String text) { doSetText(this.$element_ApoyoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  806 */   public void setTextCodigoEd(String text) { doSetText(this.$element_CodigoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  815 */   public void setTextDivConsulta(String text) { doSetText(this.$element_DivConsulta, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  824 */   public void setTextDivCreacionRegistro(String text) { doSetText(this.$element_DivCreacionRegistro, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  833 */   public void setTextDivEdicion(String text) { doSetText(this.$element_DivEdicion, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  842 */   public void setTextDivResultados(String text) { doSetText(this.$element_DivResultados, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  851 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  860 */   public void setTextEstadoEd(String text) { doSetText(this.$element_EstadoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  869 */   public void setTextEstrategicoEd(String text) { doSetText(this.$element_EstrategicoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  878 */   public void setTextEvaluacionEd(String text) { doSetText(this.$element_EvaluacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  887 */   public void setTextFechaInsercionEd(String text) { doSetText(this.$element_FechaInsercionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  896 */   public void setTextFechaModificacionEd(String text) { doSetText(this.$element_FechaModificacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  905 */   public void setTextLabelNombre(String text) { doSetText(this.$element_LabelNombre, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  914 */   public void setTextMisionalEd(String text) { doSetText(this.$element_MisionalEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  923 */   public void setTextNombreEd(String text) { doSetText(this.$element_NombreEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  932 */   public void setTextNroRegistros(String text) { doSetText(this.$element_NroRegistros, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  941 */   public void setTextResponsableEd(String text) { doSetText(this.$element_ResponsableEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  950 */   public void setTextTipoInformeEd(String text) { doSetText(this.$element_TipoInformeEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  959 */   public void setTextUsuarioInsercionEd(String text) { doSetText(this.$element_UsuarioInsercionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  968 */   public void setTextUsuarioModificacionEd(String text) { doSetText(this.$element_UsuarioModificacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void syncWithDocument(Node node) {
/*  977 */     if (node.getNodeType() != 9)
/*      */     {
/*  979 */       throw new XMLCRuntimeException("Node must be a document node");
/*      */     }
/*      */ 
/*      */     
/*  983 */     Document doc = (Document)node;
/*      */ 
/*      */     
/*      */     try {
/*  987 */       Field[] fs = getClass().getDeclaredFields();
/*      */       
/*  989 */       int substStart = "$element_".length();
/*      */       
/*  991 */       for (int i = 0; i < fs.length; i++) {
/*      */         
/*  993 */         Field f = fs[i];
/*      */         
/*  995 */         if (f.getName().startsWith("$element_")) {
/*      */           
/*  997 */           String id = f.getName().substring(substStart);
/*      */           
/*  999 */           Node idNode = doc.getElementById(id);
/*      */           
/* 1001 */           if (idNode == null) {
/*      */             
/* 1003 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*      */             
/* 1005 */             idNode = doc.getElementById(id);
/*      */           } 
/*      */ 
/*      */           
/* 1009 */           if (idNode != null) f.set(this, idNode);
/*      */         
/*      */         } 
/*      */       } 
/* 1013 */     } catch (Exception e) {
/*      */       
/* 1015 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AudInformesHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */