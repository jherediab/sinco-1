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
/*      */ import sinco.presentation.AreasHTML;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class AreasHTML
/*      */   extends HTMLObjectImpl
/*      */   implements XMLObject, HTMLObject
/*      */ {
/*      */   private HTMLInputElement $element_Anidar;
/*      */   private HTMLElement $element_AnidarEd;
/*      */   private HTMLInputElement $element_BtnAyuda;
/*      */   private HTMLInputElement $element_BtnConsultar;
/*      */   private HTMLInputElement $element_BtnCrear;
/*      */   private HTMLInputElement $element_BtnEliminar;
/*      */   private HTMLInputElement $element_BtnGrabar;
/*      */   private HTMLInputElement $element_BtnModificar;
/*      */   private HTMLInputElement $element_BtnPrincipal;
/*      */   private HTMLInputElement $element_BtnSalir;
/*      */   private HTMLInputElement $element_Codigo;
/*      */   private HTMLElement $element_CodigoEd;
/*      */   private HTMLInputElement $element_CodigoKey;
/*      */   private HTMLSelectElement $element_DepartamentoUbicacion;
/*      */   private HTMLElement $element_DepartamentoUbicacionEd;
/*      */   private HTMLInputElement $element_Descripcion;
/*      */   private HTMLElement $element_DescripcionEd;
/*      */   private HTMLTableSectionElement $element_Detalle;
/*      */   private HTMLDivElement $element_DivConsulta;
/*      */   private HTMLDivElement $element_DivCreacionRegistro;
/*      */   private HTMLDivElement $element_DivEdicion;
/*      */   private HTMLDivElement $element_DivResultados;
/*      */   private HTMLScriptElement $element_ElMenu;
/*      */   private HTMLSelectElement $element_Estado;
/*      */   private HTMLElement $element_EstadoEd;
/*      */   private HTMLInputElement $element_Fcodigo;
/*      */   private HTMLInputElement $element_Fdescripcion;
/*      */   private HTMLInputElement $element_FechaInsercion;
/*      */   private HTMLElement $element_FechaInsercionEd;
/*      */   private HTMLInputElement $element_FechaModificacion;
/*      */   private HTMLElement $element_FechaModificacionEd;
/*      */   private HTMLSelectElement $element_Festado;
/*      */   private HTMLInputElement $element_IncluirCalidad;
/*      */   private HTMLElement $element_IncluirCalidadEd;
/*      */   private HTMLLabelElement $element_LabelNombre;
/*      */   private HTMLInputElement $element_ModificaLogros;
/*      */   private HTMLElement $element_ModificaLogrosEd;
/*      */   private HTMLSelectElement $element_MunicipioUbicacion;
/*      */   private HTMLElement $element_MunicipioUbicacionEd;
/*      */   private HTMLSelectElement $element_NivelOrganigrama;
/*      */   private HTMLElement $element_NivelOrganigramaEd;
/*      */   private HTMLSelectElement $element_NivelSuperior;
/*      */   private HTMLElement $element_NivelSuperiorEd;
/*      */   private HTMLElement $element_NroRegistros;
/*      */   private HTMLElement $element_PermiteActasEd;
/*      */   private HTMLInputElement $element_Personas;
/*      */   private HTMLInputElement $element_Servicios;
/*      */   private HTMLSelectElement $element_TipoArea;
/*      */   private HTMLElement $element_TipoAreaEd;
/*      */   private HTMLInputElement $element_Ubicacion;
/*      */   private HTMLElement $element_UbicacionEd;
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
/*      */   public static final String NAME_anidar = "anidar";
/*      */   public static final String NAME_codigo = "codigo";
/*      */   public static final String NAME_departamentoUbicacion = "departamentoUbicacion";
/*      */   public static final String NAME_descripcion = "descripcion";
/*      */   public static final String NAME_estado = "estado";
/*      */   public static final String NAME_fechaInsercion = "fechaInsercion";
/*      */   public static final String NAME_fechaModificacion = "fechaModificacion";
/*      */   public static final String NAME_ff = "ff";
/*      */   public static final String NAME_incluirCalidad = "incluirCalidad";
/*      */   public static final String NAME_labelNombre = "labelNombre";
/*      */   public static final String NAME_modificaLogros = "modificaLogros";
/*      */   public static final String NAME_municipioUbicacion = "municipioUbicacion";
/*      */   public static final String NAME_nivelOrganigrama = "nivelOrganigrama";
/*      */   public static final String NAME_nivelSuperior = "nivelSuperior";
/*      */   public static final String NAME_tipoArea = "tipoArea";
/*      */   public static final String NAME_ubicacion = "ubicacion";
/*      */   public static final String NAME_usuarioInsercion = "usuarioInsercion";
/*      */   public static final String NAME_usuarioModificacion = "usuarioModificacion";
/*  313 */   public static final Class XMLC_GENERATED_CLASS = AreasHTML.class;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/Areas.html";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  324 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final DocumentLoader fDocumentLoader;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AreasHTML() {
/*  335 */     this(StandardDocumentLoader.getInstance());
/*      */     
/*  337 */     buildDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  345 */   public AreasHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AreasHTML(AreasHTML src) {
/*  353 */     this.fDocumentLoader = src.getDocumentLoader();
/*      */     
/*  355 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*      */     
/*  357 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AreasHTML(DocumentLoader loader, boolean buildDOM) {
/*  368 */     this.fDocumentLoader = loader;
/*      */     
/*  370 */     if (buildDOM)
/*      */     {
/*  372 */       buildDocument();
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
/*  384 */   public AreasHTML(DocumentLoader loader) { this(loader, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void buildDocument() {
/*  392 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*      */     
/*  394 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node cloneNode(boolean deep) {
/*  402 */     cloneDeepCheck(deep);
/*      */     
/*  404 */     return new AreasHTML(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  412 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  420 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  429 */   public HTMLInputElement getElementAnidar() { return this.$element_Anidar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  438 */   public HTMLElement getElementAnidarEd() { return this.$element_AnidarEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  447 */   public HTMLInputElement getElementBtnAyuda() { return this.$element_BtnAyuda; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  456 */   public HTMLInputElement getElementBtnConsultar() { return this.$element_BtnConsultar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  465 */   public HTMLInputElement getElementBtnCrear() { return this.$element_BtnCrear; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  474 */   public HTMLInputElement getElementBtnEliminar() { return this.$element_BtnEliminar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  483 */   public HTMLInputElement getElementBtnGrabar() { return this.$element_BtnGrabar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  492 */   public HTMLInputElement getElementBtnModificar() { return this.$element_BtnModificar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  501 */   public HTMLInputElement getElementBtnPrincipal() { return this.$element_BtnPrincipal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  510 */   public HTMLInputElement getElementBtnSalir() { return this.$element_BtnSalir; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  519 */   public HTMLInputElement getElementCodigo() { return this.$element_Codigo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  528 */   public HTMLElement getElementCodigoEd() { return this.$element_CodigoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  537 */   public HTMLInputElement getElementCodigoKey() { return this.$element_CodigoKey; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  546 */   public HTMLSelectElement getElementDepartamentoUbicacion() { return this.$element_DepartamentoUbicacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  555 */   public HTMLElement getElementDepartamentoUbicacionEd() { return this.$element_DepartamentoUbicacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  564 */   public HTMLInputElement getElementDescripcion() { return this.$element_Descripcion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  573 */   public HTMLElement getElementDescripcionEd() { return this.$element_DescripcionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  582 */   public HTMLTableSectionElement getElementDetalle() { return this.$element_Detalle; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  591 */   public HTMLDivElement getElementDivConsulta() { return this.$element_DivConsulta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  600 */   public HTMLDivElement getElementDivCreacionRegistro() { return this.$element_DivCreacionRegistro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  609 */   public HTMLDivElement getElementDivEdicion() { return this.$element_DivEdicion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  618 */   public HTMLDivElement getElementDivResultados() { return this.$element_DivResultados; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  627 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  636 */   public HTMLSelectElement getElementEstado() { return this.$element_Estado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  645 */   public HTMLElement getElementEstadoEd() { return this.$element_EstadoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  654 */   public HTMLInputElement getElementFcodigo() { return this.$element_Fcodigo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  663 */   public HTMLInputElement getElementFdescripcion() { return this.$element_Fdescripcion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  672 */   public HTMLInputElement getElementFechaInsercion() { return this.$element_FechaInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  681 */   public HTMLElement getElementFechaInsercionEd() { return this.$element_FechaInsercionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  690 */   public HTMLInputElement getElementFechaModificacion() { return this.$element_FechaModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  699 */   public HTMLElement getElementFechaModificacionEd() { return this.$element_FechaModificacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  708 */   public HTMLSelectElement getElementFestado() { return this.$element_Festado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  717 */   public HTMLInputElement getElementIncluirCalidad() { return this.$element_IncluirCalidad; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  726 */   public HTMLElement getElementIncluirCalidadEd() { return this.$element_IncluirCalidadEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  735 */   public HTMLLabelElement getElementLabelNombre() { return this.$element_LabelNombre; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  744 */   public HTMLInputElement getElementModificaLogros() { return this.$element_ModificaLogros; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  753 */   public HTMLElement getElementModificaLogrosEd() { return this.$element_ModificaLogrosEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  762 */   public HTMLSelectElement getElementMunicipioUbicacion() { return this.$element_MunicipioUbicacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  771 */   public HTMLElement getElementMunicipioUbicacionEd() { return this.$element_MunicipioUbicacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  780 */   public HTMLSelectElement getElementNivelOrganigrama() { return this.$element_NivelOrganigrama; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  789 */   public HTMLElement getElementNivelOrganigramaEd() { return this.$element_NivelOrganigramaEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  798 */   public HTMLSelectElement getElementNivelSuperior() { return this.$element_NivelSuperior; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  807 */   public HTMLElement getElementNivelSuperiorEd() { return this.$element_NivelSuperiorEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  816 */   public HTMLElement getElementNroRegistros() { return this.$element_NroRegistros; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  825 */   public HTMLElement getElementPermiteActasEd() { return this.$element_PermiteActasEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  834 */   public HTMLInputElement getElementPersonas() { return this.$element_Personas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  843 */   public HTMLInputElement getElementServicios() { return this.$element_Servicios; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  852 */   public HTMLSelectElement getElementTipoArea() { return this.$element_TipoArea; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  861 */   public HTMLElement getElementTipoAreaEd() { return this.$element_TipoAreaEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  870 */   public HTMLInputElement getElementUbicacion() { return this.$element_Ubicacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  879 */   public HTMLElement getElementUbicacionEd() { return this.$element_UbicacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  888 */   public HTMLInputElement getElementUsuarioInsercion() { return this.$element_UsuarioInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  897 */   public HTMLElement getElementUsuarioInsercionEd() { return this.$element_UsuarioInsercionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  906 */   public HTMLInputElement getElementUsuarioModificacion() { return this.$element_UsuarioModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  915 */   public HTMLElement getElementUsuarioModificacionEd() { return this.$element_UsuarioModificacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  924 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  933 */   public void setTextAnidarEd(String text) { doSetText(this.$element_AnidarEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  942 */   public void setTextCodigoEd(String text) { doSetText(this.$element_CodigoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  951 */   public void setTextDepartamentoUbicacionEd(String text) { doSetText(this.$element_DepartamentoUbicacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  960 */   public void setTextDescripcionEd(String text) { doSetText(this.$element_DescripcionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  969 */   public void setTextDivConsulta(String text) { doSetText(this.$element_DivConsulta, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  978 */   public void setTextDivCreacionRegistro(String text) { doSetText(this.$element_DivCreacionRegistro, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  987 */   public void setTextDivEdicion(String text) { doSetText(this.$element_DivEdicion, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  996 */   public void setTextDivResultados(String text) { doSetText(this.$element_DivResultados, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1005 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1014 */   public void setTextEstadoEd(String text) { doSetText(this.$element_EstadoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1023 */   public void setTextFechaInsercionEd(String text) { doSetText(this.$element_FechaInsercionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1032 */   public void setTextFechaModificacionEd(String text) { doSetText(this.$element_FechaModificacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1041 */   public void setTextIncluirCalidadEd(String text) { doSetText(this.$element_IncluirCalidadEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1050 */   public void setTextLabelNombre(String text) { doSetText(this.$element_LabelNombre, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1059 */   public void setTextModificaLogrosEd(String text) { doSetText(this.$element_ModificaLogrosEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1068 */   public void setTextMunicipioUbicacionEd(String text) { doSetText(this.$element_MunicipioUbicacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1077 */   public void setTextNivelOrganigramaEd(String text) { doSetText(this.$element_NivelOrganigramaEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1086 */   public void setTextNivelSuperiorEd(String text) { doSetText(this.$element_NivelSuperiorEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1095 */   public void setTextNroRegistros(String text) { doSetText(this.$element_NroRegistros, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1104 */   public void setTextPermiteActasEd(String text) { doSetText(this.$element_PermiteActasEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1113 */   public void setTextTipoAreaEd(String text) { doSetText(this.$element_TipoAreaEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1122 */   public void setTextUbicacionEd(String text) { doSetText(this.$element_UbicacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1131 */   public void setTextUsuarioInsercionEd(String text) { doSetText(this.$element_UsuarioInsercionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1140 */   public void setTextUsuarioModificacionEd(String text) { doSetText(this.$element_UsuarioModificacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void syncWithDocument(Node node) {
/* 1149 */     if (node.getNodeType() != 9)
/*      */     {
/* 1151 */       throw new XMLCRuntimeException("Node must be a document node");
/*      */     }
/*      */ 
/*      */     
/* 1155 */     Document doc = (Document)node;
/*      */ 
/*      */     
/*      */     try {
/* 1159 */       Field[] fs = getClass().getDeclaredFields();
/*      */       
/* 1161 */       int substStart = "$element_".length();
/*      */       
/* 1163 */       for (int i = 0; i < fs.length; i++) {
/*      */         
/* 1165 */         Field f = fs[i];
/*      */         
/* 1167 */         if (f.getName().startsWith("$element_")) {
/*      */           
/* 1169 */           String id = f.getName().substring(substStart);
/*      */           
/* 1171 */           Node idNode = doc.getElementById(id);
/*      */           
/* 1173 */           if (idNode == null) {
/*      */             
/* 1175 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*      */             
/* 1177 */             idNode = doc.getElementById(id);
/*      */           } 
/*      */ 
/*      */           
/* 1181 */           if (idNode != null) f.set(this, idNode);
/*      */         
/*      */         } 
/*      */       } 
/* 1185 */     } catch (Exception e) {
/*      */       
/* 1187 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AreasHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */