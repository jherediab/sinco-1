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
/*      */ import sinco.presentation.SisAplicacionesHTML;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class SisAplicacionesHTML
/*      */   extends HTMLObjectImpl
/*      */   implements XMLObject, HTMLObject
/*      */ {
/*      */   private HTMLInputElement $element_Alto;
/*      */   private HTMLElement $element_AltoEd;
/*      */   private HTMLInputElement $element_Ancho;
/*      */   private HTMLElement $element_AnchoEd;
/*      */   private HTMLInputElement $element_Aplicacion;
/*      */   private HTMLElement $element_AplicacionEd;
/*      */   private HTMLInputElement $element_AplicacionKey;
/*      */   private HTMLInputElement $element_BtnAyuda;
/*      */   private HTMLInputElement $element_BtnConsultar;
/*      */   private HTMLInputElement $element_BtnCrear;
/*      */   private HTMLInputElement $element_BtnEliminar;
/*      */   private HTMLInputElement $element_BtnGrabar;
/*      */   private HTMLInputElement $element_BtnModificar;
/*      */   private HTMLInputElement $element_BtnSalir;
/*      */   private HTMLInputElement $element_Descripcion;
/*      */   private HTMLElement $element_DescripcionEd;
/*      */   private HTMLTableSectionElement $element_Detalle;
/*      */   private HTMLDivElement $element_DivConsulta;
/*      */   private HTMLDivElement $element_DivCreacionRegistro;
/*      */   private HTMLDivElement $element_DivEdicion;
/*      */   private HTMLDivElement $element_DivResultados;
/*      */   private HTMLScriptElement $element_ElMenu;
/*      */   private HTMLInputElement $element_Entorno;
/*      */   private HTMLElement $element_EntornoEd;
/*      */   private HTMLInputElement $element_Faplicacion;
/*      */   private HTMLInputElement $element_Fdescripcion;
/*      */   private HTMLInputElement $element_FechaInsercion;
/*      */   private HTMLElement $element_FechaInsercionEd;
/*      */   private HTMLInputElement $element_FechaModificacion;
/*      */   private HTMLElement $element_FechaModificacionEd;
/*      */   private HTMLInputElement $element_FmenuAplicacion;
/*      */   private HTMLInputElement $element_Fmodulo;
/*      */   private HTMLSelectElement $element_FtipoAplicacion;
/*      */   private HTMLLabelElement $element_LabelNombre;
/*      */   private HTMLInputElement $element_LinkAplicacion;
/*      */   private HTMLElement $element_LinkAplicacionEd;
/*      */   private HTMLInputElement $element_MenuAplicacion;
/*      */   private HTMLElement $element_MenuAplicacionEd;
/*      */   private HTMLInputElement $element_Modulo;
/*      */   private HTMLElement $element_ModuloEd;
/*      */   private HTMLElement $element_NroRegistros;
/*      */   private HTMLInputElement $element_OrdenAplicacion;
/*      */   private HTMLElement $element_OrdenAplicacionEd;
/*      */   private HTMLSelectElement $element_TipoAplicacion;
/*      */   private HTMLElement $element_TipoAplicacionEd;
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
/*      */   public static final String NAME_alto = "alto";
/*      */   public static final String NAME_ancho = "ancho";
/*      */   public static final String NAME_aplicacion = "aplicacion";
/*      */   public static final String NAME_descripcion = "descripcion";
/*      */   public static final String NAME_entorno = "entorno";
/*      */   public static final String NAME_fechaInsercion = "fechaInsercion";
/*      */   public static final String NAME_fechaModificacion = "fechaModificacion";
/*      */   public static final String NAME_ff = "ff";
/*      */   public static final String NAME_labelNombre = "labelNombre";
/*      */   public static final String NAME_linkAplicacion = "linkAplicacion";
/*      */   public static final String NAME_menuAplicacion = "menuAplicacion";
/*      */   public static final String NAME_modulo = "modulo";
/*      */   public static final String NAME_ordenAplicacion = "ordenAplicacion";
/*      */   public static final String NAME_tipoAplicacion = "tipoAplicacion";
/*      */   public static final String NAME_usuarioInsercion = "usuarioInsercion";
/*      */   public static final String NAME_usuarioModificacion = "usuarioModificacion";
/*  291 */   public static final Class XMLC_GENERATED_CLASS = SisAplicacionesHTML.class;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/SisAplicaciones.html";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  302 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final DocumentLoader fDocumentLoader;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SisAplicacionesHTML() {
/*  313 */     this(StandardDocumentLoader.getInstance());
/*      */     
/*  315 */     buildDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  323 */   public SisAplicacionesHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SisAplicacionesHTML(SisAplicacionesHTML src) {
/*  331 */     this.fDocumentLoader = src.getDocumentLoader();
/*      */     
/*  333 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*      */     
/*  335 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SisAplicacionesHTML(DocumentLoader loader, boolean buildDOM) {
/*  346 */     this.fDocumentLoader = loader;
/*      */     
/*  348 */     if (buildDOM)
/*      */     {
/*  350 */       buildDocument();
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
/*  362 */   public SisAplicacionesHTML(DocumentLoader loader) { this(loader, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void buildDocument() {
/*  370 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*      */     
/*  372 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node cloneNode(boolean deep) {
/*  380 */     cloneDeepCheck(deep);
/*      */     
/*  382 */     return new SisAplicacionesHTML(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  390 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  398 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  407 */   public HTMLInputElement getElementAlto() { return this.$element_Alto; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  416 */   public HTMLElement getElementAltoEd() { return this.$element_AltoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  425 */   public HTMLInputElement getElementAncho() { return this.$element_Ancho; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  434 */   public HTMLElement getElementAnchoEd() { return this.$element_AnchoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  443 */   public HTMLInputElement getElementAplicacion() { return this.$element_Aplicacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  452 */   public HTMLElement getElementAplicacionEd() { return this.$element_AplicacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  461 */   public HTMLInputElement getElementAplicacionKey() { return this.$element_AplicacionKey; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  470 */   public HTMLInputElement getElementBtnAyuda() { return this.$element_BtnAyuda; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  479 */   public HTMLInputElement getElementBtnConsultar() { return this.$element_BtnConsultar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  488 */   public HTMLInputElement getElementBtnCrear() { return this.$element_BtnCrear; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  497 */   public HTMLInputElement getElementBtnEliminar() { return this.$element_BtnEliminar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  506 */   public HTMLInputElement getElementBtnGrabar() { return this.$element_BtnGrabar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  515 */   public HTMLInputElement getElementBtnModificar() { return this.$element_BtnModificar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  524 */   public HTMLInputElement getElementBtnSalir() { return this.$element_BtnSalir; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  533 */   public HTMLInputElement getElementDescripcion() { return this.$element_Descripcion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  542 */   public HTMLElement getElementDescripcionEd() { return this.$element_DescripcionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  551 */   public HTMLTableSectionElement getElementDetalle() { return this.$element_Detalle; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  560 */   public HTMLDivElement getElementDivConsulta() { return this.$element_DivConsulta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  569 */   public HTMLDivElement getElementDivCreacionRegistro() { return this.$element_DivCreacionRegistro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  578 */   public HTMLDivElement getElementDivEdicion() { return this.$element_DivEdicion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  587 */   public HTMLDivElement getElementDivResultados() { return this.$element_DivResultados; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  596 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  605 */   public HTMLInputElement getElementEntorno() { return this.$element_Entorno; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  614 */   public HTMLElement getElementEntornoEd() { return this.$element_EntornoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  623 */   public HTMLInputElement getElementFaplicacion() { return this.$element_Faplicacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  632 */   public HTMLInputElement getElementFdescripcion() { return this.$element_Fdescripcion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  641 */   public HTMLInputElement getElementFechaInsercion() { return this.$element_FechaInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  650 */   public HTMLElement getElementFechaInsercionEd() { return this.$element_FechaInsercionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  659 */   public HTMLInputElement getElementFechaModificacion() { return this.$element_FechaModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  668 */   public HTMLElement getElementFechaModificacionEd() { return this.$element_FechaModificacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  677 */   public HTMLInputElement getElementFmenuAplicacion() { return this.$element_FmenuAplicacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  686 */   public HTMLInputElement getElementFmodulo() { return this.$element_Fmodulo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  695 */   public HTMLSelectElement getElementFtipoAplicacion() { return this.$element_FtipoAplicacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  704 */   public HTMLLabelElement getElementLabelNombre() { return this.$element_LabelNombre; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  713 */   public HTMLInputElement getElementLinkAplicacion() { return this.$element_LinkAplicacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  722 */   public HTMLElement getElementLinkAplicacionEd() { return this.$element_LinkAplicacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  731 */   public HTMLInputElement getElementMenuAplicacion() { return this.$element_MenuAplicacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  740 */   public HTMLElement getElementMenuAplicacionEd() { return this.$element_MenuAplicacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  749 */   public HTMLInputElement getElementModulo() { return this.$element_Modulo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  758 */   public HTMLElement getElementModuloEd() { return this.$element_ModuloEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  767 */   public HTMLElement getElementNroRegistros() { return this.$element_NroRegistros; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  776 */   public HTMLInputElement getElementOrdenAplicacion() { return this.$element_OrdenAplicacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  785 */   public HTMLElement getElementOrdenAplicacionEd() { return this.$element_OrdenAplicacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  794 */   public HTMLSelectElement getElementTipoAplicacion() { return this.$element_TipoAplicacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  803 */   public HTMLElement getElementTipoAplicacionEd() { return this.$element_TipoAplicacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  812 */   public HTMLInputElement getElementUsuarioInsercion() { return this.$element_UsuarioInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  821 */   public HTMLElement getElementUsuarioInsercionEd() { return this.$element_UsuarioInsercionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  830 */   public HTMLInputElement getElementUsuarioModificacion() { return this.$element_UsuarioModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  839 */   public HTMLElement getElementUsuarioModificacionEd() { return this.$element_UsuarioModificacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  848 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  857 */   public void setTextAltoEd(String text) { doSetText(this.$element_AltoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  866 */   public void setTextAnchoEd(String text) { doSetText(this.$element_AnchoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  875 */   public void setTextAplicacionEd(String text) { doSetText(this.$element_AplicacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  884 */   public void setTextDescripcionEd(String text) { doSetText(this.$element_DescripcionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  893 */   public void setTextDivConsulta(String text) { doSetText(this.$element_DivConsulta, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  902 */   public void setTextDivCreacionRegistro(String text) { doSetText(this.$element_DivCreacionRegistro, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  911 */   public void setTextDivEdicion(String text) { doSetText(this.$element_DivEdicion, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  920 */   public void setTextDivResultados(String text) { doSetText(this.$element_DivResultados, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  929 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  938 */   public void setTextEntornoEd(String text) { doSetText(this.$element_EntornoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  947 */   public void setTextFechaInsercionEd(String text) { doSetText(this.$element_FechaInsercionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  956 */   public void setTextFechaModificacionEd(String text) { doSetText(this.$element_FechaModificacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  965 */   public void setTextLabelNombre(String text) { doSetText(this.$element_LabelNombre, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  974 */   public void setTextLinkAplicacionEd(String text) { doSetText(this.$element_LinkAplicacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  983 */   public void setTextMenuAplicacionEd(String text) { doSetText(this.$element_MenuAplicacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  992 */   public void setTextModuloEd(String text) { doSetText(this.$element_ModuloEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1001 */   public void setTextNroRegistros(String text) { doSetText(this.$element_NroRegistros, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1010 */   public void setTextOrdenAplicacionEd(String text) { doSetText(this.$element_OrdenAplicacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1019 */   public void setTextTipoAplicacionEd(String text) { doSetText(this.$element_TipoAplicacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1028 */   public void setTextUsuarioInsercionEd(String text) { doSetText(this.$element_UsuarioInsercionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1037 */   public void setTextUsuarioModificacionEd(String text) { doSetText(this.$element_UsuarioModificacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void syncWithDocument(Node node) {
/* 1046 */     if (node.getNodeType() != 9)
/*      */     {
/* 1048 */       throw new XMLCRuntimeException("Node must be a document node");
/*      */     }
/*      */ 
/*      */     
/* 1052 */     Document doc = (Document)node;
/*      */ 
/*      */     
/*      */     try {
/* 1056 */       Field[] fs = getClass().getDeclaredFields();
/*      */       
/* 1058 */       int substStart = "$element_".length();
/*      */       
/* 1060 */       for (int i = 0; i < fs.length; i++) {
/*      */         
/* 1062 */         Field f = fs[i];
/*      */         
/* 1064 */         if (f.getName().startsWith("$element_")) {
/*      */           
/* 1066 */           String id = f.getName().substring(substStart);
/*      */           
/* 1068 */           Node idNode = doc.getElementById(id);
/*      */           
/* 1070 */           if (idNode == null) {
/*      */             
/* 1072 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*      */             
/* 1074 */             idNode = doc.getElementById(id);
/*      */           } 
/*      */ 
/*      */           
/* 1078 */           if (idNode != null) f.set(this, idNode);
/*      */         
/*      */         } 
/*      */       } 
/* 1082 */     } catch (Exception e) {
/*      */       
/* 1084 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SisAplicacionesHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */