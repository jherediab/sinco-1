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
/*      */ import org.w3c.dom.html.HTMLTextAreaElement;
/*      */ import sinco.presentation.AudInformesPlanAnualHTML;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class AudInformesPlanAnualHTML
/*      */   extends HTMLObjectImpl
/*      */   implements XMLObject, HTMLObject
/*      */ {
/*      */   private HTMLInputElement $element_Alcance;
/*      */   private HTMLElement $element_AlcanceEd;
/*      */   private HTMLTextAreaElement $element_AlcanceText;
/*      */   private HTMLSelectElement $element_AreaResponsable;
/*      */   private HTMLElement $element_AreaResponsableEd;
/*      */   private HTMLInputElement $element_AsociadoA;
/*      */   private HTMLInputElement $element_BtnAyuda;
/*      */   private HTMLInputElement $element_BtnConsultar;
/*      */   private HTMLInputElement $element_BtnCrear;
/*      */   private HTMLInputElement $element_BtnEliminar;
/*      */   private HTMLInputElement $element_BtnGrabar;
/*      */   private HTMLInputElement $element_BtnGrupo;
/*      */   private HTMLInputElement $element_BtnModificar;
/*      */   private HTMLInputElement $element_BtnPreguntas;
/*      */   private HTMLInputElement $element_BtnSalir;
/*      */   private HTMLInputElement $element_BtnVolver;
/*      */   private HTMLElement $element_CicloEd;
/*      */   private HTMLInputElement $element_CicloHidden;
/*      */   private HTMLInputElement $element_CicloKey;
/*      */   private HTMLSelectElement $element_CodigoInforme;
/*      */   private HTMLElement $element_CodigoInformeEd;
/*      */   private HTMLInputElement $element_CodigoInformeKey;
/*      */   private HTMLInputElement $element_CodigoPadre;
/*      */   private HTMLSelectElement $element_CoordinadorAuditoría;
/*      */   private HTMLElement $element_CoordinadorAuditoríaEd;
/*      */   private HTMLTableSectionElement $element_Detalle;
/*      */   private HTMLDivElement $element_DivConsulta;
/*      */   private HTMLDivElement $element_DivCreacionRegistro;
/*      */   private HTMLDivElement $element_DivEdicion;
/*      */   private HTMLDivElement $element_DivResultados;
/*      */   private HTMLScriptElement $element_ElMenu;
/*      */   private HTMLTextAreaElement $element_EquipoAuditor;
/*      */   private HTMLInputElement $element_EquipoAuditor2;
/*      */   private HTMLElement $element_EquipoAuditorEd;
/*      */   private HTMLInputElement $element_FechaInsercion;
/*      */   private HTMLElement $element_FechaInsercionEd;
/*      */   private HTMLInputElement $element_FechaModificacion;
/*      */   private HTMLElement $element_FechaModificacionEd;
/*      */   private HTMLLabelElement $element_LabelNombre;
/*      */   private HTMLInputElement $element_Mes01;
/*      */   private HTMLElement $element_Mes01Ed;
/*      */   private HTMLInputElement $element_Mes02;
/*      */   private HTMLElement $element_Mes02Ed;
/*      */   private HTMLInputElement $element_Mes03;
/*      */   private HTMLElement $element_Mes03Ed;
/*      */   private HTMLInputElement $element_Mes04;
/*      */   private HTMLElement $element_Mes04Ed;
/*      */   private HTMLInputElement $element_Mes05;
/*      */   private HTMLElement $element_Mes05Ed;
/*      */   private HTMLInputElement $element_Mes06;
/*      */   private HTMLElement $element_Mes06Ed;
/*      */   private HTMLInputElement $element_Mes07;
/*      */   private HTMLElement $element_Mes07Ed;
/*      */   private HTMLInputElement $element_Mes08;
/*      */   private HTMLElement $element_Mes08Ed;
/*      */   private HTMLInputElement $element_Mes09;
/*      */   private HTMLElement $element_Mes09Ed;
/*      */   private HTMLInputElement $element_Mes10;
/*      */   private HTMLElement $element_Mes10Ed;
/*      */   private HTMLInputElement $element_Mes11;
/*      */   private HTMLElement $element_Mes11Ed;
/*      */   private HTMLInputElement $element_Mes12;
/*      */   private HTMLElement $element_Mes12Ed;
/*      */   private HTMLElement $element_NroRegistros;
/*      */   private HTMLInputElement $element_ObjetivosEspecificos;
/*      */   private HTMLElement $element_ObjetivosEspecificosEd;
/*      */   private HTMLTextAreaElement $element_ObjetivosEspecificosText;
/*      */   private HTMLSelectElement $element_TipoInforme;
/*      */   private HTMLInputElement $element_TituloAuditoria;
/*      */   private HTMLElement $element_TituloAuditoriaEd;
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
/*      */   public static final String NAME_alcance = "alcance";
/*      */   public static final String NAME_alcanceText = "alcanceText";
/*      */   public static final String NAME_areaResponsable = "areaResponsable";
/*      */   public static final String NAME_asociadoA = "asociadoA";
/*      */   public static final String NAME_ciclo = "ciclo";
/*      */   public static final String NAME_codigoInforme = "codigoInforme";
/*      */   public static final String NAME_codigoPadre = "codigoPadre";
/*      */   public static final String NAME_coordinadorAuditoría = "coordinadorAuditoría";
/*      */   public static final String NAME_equipoAuditor = "equipoAuditor";
/*      */   public static final String NAME_equipoAuditor2 = "equipoAuditor2";
/*      */   public static final String NAME_fechaInsercion = "fechaInsercion";
/*      */   public static final String NAME_fechaModificacion = "fechaModificacion";
/*      */   public static final String NAME_ff = "ff";
/*      */   public static final String NAME_labelNombre = "labelNombre";
/*      */   public static final String NAME_mes01 = "mes01";
/*      */   public static final String NAME_mes02 = "mes02";
/*      */   public static final String NAME_mes03 = "mes03";
/*      */   public static final String NAME_mes04 = "mes04";
/*      */   public static final String NAME_mes05 = "mes05";
/*      */   public static final String NAME_mes06 = "mes06";
/*      */   public static final String NAME_mes07 = "mes07";
/*      */   public static final String NAME_mes08 = "mes08";
/*      */   public static final String NAME_mes09 = "mes09";
/*      */   public static final String NAME_mes10 = "mes10";
/*      */   public static final String NAME_mes11 = "mes11";
/*      */   public static final String NAME_mes12 = "mes12";
/*      */   public static final String NAME_objetivosEspecificos = "objetivosEspecificos";
/*      */   public static final String NAME_objetivosEspecificosText = "objetivosEspecificosText";
/*      */   public static final String NAME_tipoInforme = "tipoInforme";
/*      */   public static final String NAME_tituloAuditoria = "tituloAuditoria";
/*      */   public static final String NAME_txtAlcance = "txtAlcance";
/*      */   public static final String NAME_txtContequipoAuditor = "txtContequipoAuditor";
/*      */   public static final String NAME_usuarioInsercion = "usuarioInsercion";
/*      */   public static final String NAME_usuarioModificacion = "usuarioModificacion";
/*  431 */   public static final Class XMLC_GENERATED_CLASS = AudInformesPlanAnualHTML.class;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/AudInformesPlanAnual.html";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  442 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final DocumentLoader fDocumentLoader;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AudInformesPlanAnualHTML() {
/*  453 */     this(StandardDocumentLoader.getInstance());
/*      */     
/*  455 */     buildDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  463 */   public AudInformesPlanAnualHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AudInformesPlanAnualHTML(AudInformesPlanAnualHTML src) {
/*  471 */     this.fDocumentLoader = src.getDocumentLoader();
/*      */     
/*  473 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*      */     
/*  475 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AudInformesPlanAnualHTML(DocumentLoader loader, boolean buildDOM) {
/*  486 */     this.fDocumentLoader = loader;
/*      */     
/*  488 */     if (buildDOM)
/*      */     {
/*  490 */       buildDocument();
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
/*  502 */   public AudInformesPlanAnualHTML(DocumentLoader loader) { this(loader, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void buildDocument() {
/*  510 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*      */     
/*  512 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node cloneNode(boolean deep) {
/*  520 */     cloneDeepCheck(deep);
/*      */     
/*  522 */     return new AudInformesPlanAnualHTML(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  530 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  538 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  547 */   public HTMLInputElement getElementAlcance() { return this.$element_Alcance; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  556 */   public HTMLElement getElementAlcanceEd() { return this.$element_AlcanceEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  565 */   public HTMLTextAreaElement getElementAlcanceText() { return this.$element_AlcanceText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  574 */   public HTMLSelectElement getElementAreaResponsable() { return this.$element_AreaResponsable; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  583 */   public HTMLElement getElementAreaResponsableEd() { return this.$element_AreaResponsableEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  592 */   public HTMLInputElement getElementAsociadoA() { return this.$element_AsociadoA; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  601 */   public HTMLInputElement getElementBtnAyuda() { return this.$element_BtnAyuda; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  610 */   public HTMLInputElement getElementBtnConsultar() { return this.$element_BtnConsultar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  619 */   public HTMLInputElement getElementBtnCrear() { return this.$element_BtnCrear; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  628 */   public HTMLInputElement getElementBtnEliminar() { return this.$element_BtnEliminar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  637 */   public HTMLInputElement getElementBtnGrabar() { return this.$element_BtnGrabar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  646 */   public HTMLInputElement getElementBtnGrupo() { return this.$element_BtnGrupo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  655 */   public HTMLInputElement getElementBtnModificar() { return this.$element_BtnModificar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  664 */   public HTMLInputElement getElementBtnPreguntas() { return this.$element_BtnPreguntas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  673 */   public HTMLInputElement getElementBtnSalir() { return this.$element_BtnSalir; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  682 */   public HTMLInputElement getElementBtnVolver() { return this.$element_BtnVolver; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  691 */   public HTMLElement getElementCicloEd() { return this.$element_CicloEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  700 */   public HTMLInputElement getElementCicloHidden() { return this.$element_CicloHidden; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  709 */   public HTMLInputElement getElementCicloKey() { return this.$element_CicloKey; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  718 */   public HTMLSelectElement getElementCodigoInforme() { return this.$element_CodigoInforme; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  727 */   public HTMLElement getElementCodigoInformeEd() { return this.$element_CodigoInformeEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  736 */   public HTMLInputElement getElementCodigoInformeKey() { return this.$element_CodigoInformeKey; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  745 */   public HTMLInputElement getElementCodigoPadre() { return this.$element_CodigoPadre; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  754 */   public HTMLSelectElement getElementCoordinadorAuditoría() { return this.$element_CoordinadorAuditoría; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  763 */   public HTMLElement getElementCoordinadorAuditoríaEd() { return this.$element_CoordinadorAuditoríaEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  772 */   public HTMLTableSectionElement getElementDetalle() { return this.$element_Detalle; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  781 */   public HTMLDivElement getElementDivConsulta() { return this.$element_DivConsulta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  790 */   public HTMLDivElement getElementDivCreacionRegistro() { return this.$element_DivCreacionRegistro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  799 */   public HTMLDivElement getElementDivEdicion() { return this.$element_DivEdicion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  808 */   public HTMLDivElement getElementDivResultados() { return this.$element_DivResultados; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  817 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  826 */   public HTMLTextAreaElement getElementEquipoAuditor() { return this.$element_EquipoAuditor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  835 */   public HTMLInputElement getElementEquipoAuditor2() { return this.$element_EquipoAuditor2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  844 */   public HTMLElement getElementEquipoAuditorEd() { return this.$element_EquipoAuditorEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  853 */   public HTMLInputElement getElementFechaInsercion() { return this.$element_FechaInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  862 */   public HTMLElement getElementFechaInsercionEd() { return this.$element_FechaInsercionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  871 */   public HTMLInputElement getElementFechaModificacion() { return this.$element_FechaModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  880 */   public HTMLElement getElementFechaModificacionEd() { return this.$element_FechaModificacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  889 */   public HTMLLabelElement getElementLabelNombre() { return this.$element_LabelNombre; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  898 */   public HTMLInputElement getElementMes01() { return this.$element_Mes01; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  907 */   public HTMLElement getElementMes01Ed() { return this.$element_Mes01Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  916 */   public HTMLInputElement getElementMes02() { return this.$element_Mes02; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  925 */   public HTMLElement getElementMes02Ed() { return this.$element_Mes02Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  934 */   public HTMLInputElement getElementMes03() { return this.$element_Mes03; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  943 */   public HTMLElement getElementMes03Ed() { return this.$element_Mes03Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  952 */   public HTMLInputElement getElementMes04() { return this.$element_Mes04; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  961 */   public HTMLElement getElementMes04Ed() { return this.$element_Mes04Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  970 */   public HTMLInputElement getElementMes05() { return this.$element_Mes05; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  979 */   public HTMLElement getElementMes05Ed() { return this.$element_Mes05Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  988 */   public HTMLInputElement getElementMes06() { return this.$element_Mes06; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  997 */   public HTMLElement getElementMes06Ed() { return this.$element_Mes06Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1006 */   public HTMLInputElement getElementMes07() { return this.$element_Mes07; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1015 */   public HTMLElement getElementMes07Ed() { return this.$element_Mes07Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1024 */   public HTMLInputElement getElementMes08() { return this.$element_Mes08; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1033 */   public HTMLElement getElementMes08Ed() { return this.$element_Mes08Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1042 */   public HTMLInputElement getElementMes09() { return this.$element_Mes09; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1051 */   public HTMLElement getElementMes09Ed() { return this.$element_Mes09Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1060 */   public HTMLInputElement getElementMes10() { return this.$element_Mes10; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1069 */   public HTMLElement getElementMes10Ed() { return this.$element_Mes10Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1078 */   public HTMLInputElement getElementMes11() { return this.$element_Mes11; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1087 */   public HTMLElement getElementMes11Ed() { return this.$element_Mes11Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1096 */   public HTMLInputElement getElementMes12() { return this.$element_Mes12; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1105 */   public HTMLElement getElementMes12Ed() { return this.$element_Mes12Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1114 */   public HTMLElement getElementNroRegistros() { return this.$element_NroRegistros; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1123 */   public HTMLInputElement getElementObjetivosEspecificos() { return this.$element_ObjetivosEspecificos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1132 */   public HTMLElement getElementObjetivosEspecificosEd() { return this.$element_ObjetivosEspecificosEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1141 */   public HTMLTextAreaElement getElementObjetivosEspecificosText() { return this.$element_ObjetivosEspecificosText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1150 */   public HTMLSelectElement getElementTipoInforme() { return this.$element_TipoInforme; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1159 */   public HTMLInputElement getElementTituloAuditoria() { return this.$element_TituloAuditoria; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1168 */   public HTMLElement getElementTituloAuditoriaEd() { return this.$element_TituloAuditoriaEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1177 */   public HTMLInputElement getElementUsuarioInsercion() { return this.$element_UsuarioInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1186 */   public HTMLElement getElementUsuarioInsercionEd() { return this.$element_UsuarioInsercionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1195 */   public HTMLInputElement getElementUsuarioModificacion() { return this.$element_UsuarioModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1204 */   public HTMLElement getElementUsuarioModificacionEd() { return this.$element_UsuarioModificacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1213 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1222 */   public void setTextAlcanceEd(String text) { doSetText(this.$element_AlcanceEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1231 */   public void setTextAlcanceText(String text) { doSetText(this.$element_AlcanceText, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1240 */   public void setTextAreaResponsableEd(String text) { doSetText(this.$element_AreaResponsableEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1249 */   public void setTextCicloEd(String text) { doSetText(this.$element_CicloEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1258 */   public void setTextCodigoInformeEd(String text) { doSetText(this.$element_CodigoInformeEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1267 */   public void setTextCoordinadorAuditoríaEd(String text) { doSetText(this.$element_CoordinadorAuditoríaEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1276 */   public void setTextDivConsulta(String text) { doSetText(this.$element_DivConsulta, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1285 */   public void setTextDivCreacionRegistro(String text) { doSetText(this.$element_DivCreacionRegistro, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1294 */   public void setTextDivEdicion(String text) { doSetText(this.$element_DivEdicion, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1303 */   public void setTextDivResultados(String text) { doSetText(this.$element_DivResultados, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1312 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1321 */   public void setTextEquipoAuditor(String text) { doSetText(this.$element_EquipoAuditor, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1330 */   public void setTextEquipoAuditorEd(String text) { doSetText(this.$element_EquipoAuditorEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1339 */   public void setTextFechaInsercionEd(String text) { doSetText(this.$element_FechaInsercionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1348 */   public void setTextFechaModificacionEd(String text) { doSetText(this.$element_FechaModificacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1357 */   public void setTextLabelNombre(String text) { doSetText(this.$element_LabelNombre, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1366 */   public void setTextMes01Ed(String text) { doSetText(this.$element_Mes01Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1375 */   public void setTextMes02Ed(String text) { doSetText(this.$element_Mes02Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1384 */   public void setTextMes03Ed(String text) { doSetText(this.$element_Mes03Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1393 */   public void setTextMes04Ed(String text) { doSetText(this.$element_Mes04Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1402 */   public void setTextMes05Ed(String text) { doSetText(this.$element_Mes05Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1411 */   public void setTextMes06Ed(String text) { doSetText(this.$element_Mes06Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1420 */   public void setTextMes07Ed(String text) { doSetText(this.$element_Mes07Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1429 */   public void setTextMes08Ed(String text) { doSetText(this.$element_Mes08Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1438 */   public void setTextMes09Ed(String text) { doSetText(this.$element_Mes09Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1447 */   public void setTextMes10Ed(String text) { doSetText(this.$element_Mes10Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1456 */   public void setTextMes11Ed(String text) { doSetText(this.$element_Mes11Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1465 */   public void setTextMes12Ed(String text) { doSetText(this.$element_Mes12Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1474 */   public void setTextNroRegistros(String text) { doSetText(this.$element_NroRegistros, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1483 */   public void setTextObjetivosEspecificosEd(String text) { doSetText(this.$element_ObjetivosEspecificosEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1492 */   public void setTextObjetivosEspecificosText(String text) { doSetText(this.$element_ObjetivosEspecificosText, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1501 */   public void setTextTituloAuditoriaEd(String text) { doSetText(this.$element_TituloAuditoriaEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1510 */   public void setTextUsuarioInsercionEd(String text) { doSetText(this.$element_UsuarioInsercionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1519 */   public void setTextUsuarioModificacionEd(String text) { doSetText(this.$element_UsuarioModificacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void syncWithDocument(Node node) {
/* 1528 */     if (node.getNodeType() != 9)
/*      */     {
/* 1530 */       throw new XMLCRuntimeException("Node must be a document node");
/*      */     }
/*      */ 
/*      */     
/* 1534 */     Document doc = (Document)node;
/*      */ 
/*      */     
/*      */     try {
/* 1538 */       Field[] fs = getClass().getDeclaredFields();
/*      */       
/* 1540 */       int substStart = "$element_".length();
/*      */       
/* 1542 */       for (int i = 0; i < fs.length; i++) {
/*      */         
/* 1544 */         Field f = fs[i];
/*      */         
/* 1546 */         if (f.getName().startsWith("$element_")) {
/*      */           
/* 1548 */           String id = f.getName().substring(substStart);
/*      */           
/* 1550 */           Node idNode = doc.getElementById(id);
/*      */           
/* 1552 */           if (idNode == null) {
/*      */             
/* 1554 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*      */             
/* 1556 */             idNode = doc.getElementById(id);
/*      */           } 
/*      */ 
/*      */           
/* 1560 */           if (idNode != null) f.set(this, idNode);
/*      */         
/*      */         } 
/*      */       } 
/* 1564 */     } catch (Exception e) {
/*      */       
/* 1566 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AudInformesPlanAnualHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */