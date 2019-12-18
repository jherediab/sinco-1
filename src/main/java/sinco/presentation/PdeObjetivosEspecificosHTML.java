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
/*     */ import org.w3c.dom.html.HTMLLabelElement;
/*     */ import org.w3c.dom.html.HTMLScriptElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.presentation.PdeObjetivosEspecificosHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PdeObjetivosEspecificosHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLInputElement $element_BtnAyuda;
/*     */   private HTMLInputElement $element_BtnConsultar;
/*     */   private HTMLInputElement $element_BtnCrear;
/*     */   private HTMLInputElement $element_BtnEliminar;
/*     */   private HTMLInputElement $element_BtnGrabar;
/*     */   private HTMLInputElement $element_BtnModificar;
/*     */   private HTMLInputElement $element_BtnSalir;
/*     */   private HTMLInputElement $element_BtnVolver;
/*     */   private HTMLInputElement $element_CodigoObjetivoEspecifico;
/*     */   private HTMLElement $element_CodigoObjetivoEspecificoEd;
/*     */   private HTMLInputElement $element_DescripcionObjetivoEspecifico;
/*     */   private HTMLElement $element_DescripcionObjetivoEspecificoEd;
/*     */   private HTMLTableSectionElement $element_Detalle;
/*     */   private HTMLDivElement $element_DivConsulta;
/*     */   private HTMLDivElement $element_DivCreacionRegistro;
/*     */   private HTMLDivElement $element_DivEdicion;
/*     */   private HTMLDivElement $element_DivResultados;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLInputElement $element_FcodigoObjetivoEspecifico;
/*     */   private HTMLInputElement $element_FdescripcionObjetivoEspecifico;
/*     */   private HTMLInputElement $element_FechaInsercion;
/*     */   private HTMLElement $element_FechaInsercionEd;
/*     */   private HTMLInputElement $element_FechaModificacion;
/*     */   private HTMLElement $element_FechaModificacionEd;
/*     */   private HTMLInputElement $element_IdNivelHidden;
/*     */   private HTMLInputElement $element_IdObjetivoEspecifico;
/*     */   private HTMLElement $element_IdObjetivoEspecificoEd;
/*     */   private HTMLInputElement $element_IdObjetivoEspecificoKey;
/*     */   private HTMLElement $element_IdUnidadNivelEd;
/*     */   private HTMLInputElement $element_IdUnidadNivelHidden;
/*     */   private HTMLInputElement $element_IdUnidadNivelKey;
/*     */   private HTMLInputElement $element_IdUnidadSuperiorHidden;
/*     */   private HTMLLabelElement $element_LabelNombre;
/*     */   private HTMLElement $element_NombreEntidad;
/*     */   private HTMLElement $element_NombreNivel;
/*     */   private HTMLElement $element_NombrePlan;
/*     */   private HTMLElement $element_NroRegistros;
/*     */   private HTMLElement $element_ObjetivoGeneralNivel;
/*     */   private HTMLInputElement $element_UsuarioInsercion;
/*     */   private HTMLElement $element_UsuarioInsercionEd;
/*     */   private HTMLInputElement $element_UsuarioModificacion;
/*     */   private HTMLElement $element_UsuarioModificacionEd;
/*     */   private HTMLInputElement $element__operacion;
/*     */   public static final String CLASS_BOB = "BOB";
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
/*     */   public static final String CLASS_datc = "datc";
/*     */   public static final String CLASS_resizable = "resizable";
/*     */   public static final String CLASS_sortable = "sortable";
/*     */   public static final String CLASS_tabf = "tabf";
/*     */   public static final String CLASS_tabw = "tabw";
/*     */   public static final String CLASS_tit = "tit";
/*     */   public static final String NAME__operacion = "_operacion";
/*     */   public static final String NAME_codigoObjetivoEspecifico = "codigoObjetivoEspecifico";
/*     */   public static final String NAME_descripcionObjetivoEspecifico = "descripcionObjetivoEspecifico";
/*     */   public static final String NAME_fechaInsercion = "fechaInsercion";
/*     */   public static final String NAME_fechaModificacion = "fechaModificacion";
/*     */   public static final String NAME_ff = "ff";
/*     */   public static final String NAME_idNivel = "idNivel";
/*     */   public static final String NAME_idObjetivoEspecifico = "idObjetivoEspecifico";
/*     */   public static final String NAME_idUnidadNivel = "idUnidadNivel";
/*     */   public static final String NAME_idUnidadSuperior = "idUnidadSuperior";
/*     */   public static final String NAME_labelNombre = "labelNombre";
/*     */   public static final String NAME_usuarioInsercion = "usuarioInsercion";
/*     */   public static final String NAME_usuarioModificacion = "usuarioModificacion";
/* 262 */   public static final Class XMLC_GENERATED_CLASS = PdeObjetivosEspecificosHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/PdeObjetivosEspecificos.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 273 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PdeObjetivosEspecificosHTML() {
/* 284 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 286 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 294 */   public PdeObjetivosEspecificosHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PdeObjetivosEspecificosHTML(PdeObjetivosEspecificosHTML src) {
/* 302 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 304 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 306 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PdeObjetivosEspecificosHTML(DocumentLoader loader, boolean buildDOM) {
/* 317 */     this.fDocumentLoader = loader;
/*     */     
/* 319 */     if (buildDOM)
/*     */     {
/* 321 */       buildDocument();
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
/* 333 */   public PdeObjetivosEspecificosHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 341 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 343 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 351 */     cloneDeepCheck(deep);
/*     */     
/* 353 */     return new PdeObjetivosEspecificosHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 361 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 369 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 378 */   public HTMLInputElement getElementBtnAyuda() { return this.$element_BtnAyuda; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 387 */   public HTMLInputElement getElementBtnConsultar() { return this.$element_BtnConsultar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 396 */   public HTMLInputElement getElementBtnCrear() { return this.$element_BtnCrear; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 405 */   public HTMLInputElement getElementBtnEliminar() { return this.$element_BtnEliminar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 414 */   public HTMLInputElement getElementBtnGrabar() { return this.$element_BtnGrabar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 423 */   public HTMLInputElement getElementBtnModificar() { return this.$element_BtnModificar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 432 */   public HTMLInputElement getElementBtnSalir() { return this.$element_BtnSalir; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 441 */   public HTMLInputElement getElementBtnVolver() { return this.$element_BtnVolver; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 450 */   public HTMLInputElement getElementCodigoObjetivoEspecifico() { return this.$element_CodigoObjetivoEspecifico; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 459 */   public HTMLElement getElementCodigoObjetivoEspecificoEd() { return this.$element_CodigoObjetivoEspecificoEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 468 */   public HTMLInputElement getElementDescripcionObjetivoEspecifico() { return this.$element_DescripcionObjetivoEspecifico; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 477 */   public HTMLElement getElementDescripcionObjetivoEspecificoEd() { return this.$element_DescripcionObjetivoEspecificoEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 486 */   public HTMLTableSectionElement getElementDetalle() { return this.$element_Detalle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 495 */   public HTMLDivElement getElementDivConsulta() { return this.$element_DivConsulta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 504 */   public HTMLDivElement getElementDivCreacionRegistro() { return this.$element_DivCreacionRegistro; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 513 */   public HTMLDivElement getElementDivEdicion() { return this.$element_DivEdicion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 522 */   public HTMLDivElement getElementDivResultados() { return this.$element_DivResultados; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 531 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 540 */   public HTMLInputElement getElementFcodigoObjetivoEspecifico() { return this.$element_FcodigoObjetivoEspecifico; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 549 */   public HTMLInputElement getElementFdescripcionObjetivoEspecifico() { return this.$element_FdescripcionObjetivoEspecifico; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 558 */   public HTMLInputElement getElementFechaInsercion() { return this.$element_FechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 567 */   public HTMLElement getElementFechaInsercionEd() { return this.$element_FechaInsercionEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 576 */   public HTMLInputElement getElementFechaModificacion() { return this.$element_FechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 585 */   public HTMLElement getElementFechaModificacionEd() { return this.$element_FechaModificacionEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 594 */   public HTMLInputElement getElementIdNivelHidden() { return this.$element_IdNivelHidden; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 603 */   public HTMLInputElement getElementIdObjetivoEspecifico() { return this.$element_IdObjetivoEspecifico; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 612 */   public HTMLElement getElementIdObjetivoEspecificoEd() { return this.$element_IdObjetivoEspecificoEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 621 */   public HTMLInputElement getElementIdObjetivoEspecificoKey() { return this.$element_IdObjetivoEspecificoKey; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 630 */   public HTMLElement getElementIdUnidadNivelEd() { return this.$element_IdUnidadNivelEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 639 */   public HTMLInputElement getElementIdUnidadNivelHidden() { return this.$element_IdUnidadNivelHidden; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 648 */   public HTMLInputElement getElementIdUnidadNivelKey() { return this.$element_IdUnidadNivelKey; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 657 */   public HTMLInputElement getElementIdUnidadSuperiorHidden() { return this.$element_IdUnidadSuperiorHidden; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 666 */   public HTMLLabelElement getElementLabelNombre() { return this.$element_LabelNombre; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 675 */   public HTMLElement getElementNombreEntidad() { return this.$element_NombreEntidad; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 684 */   public HTMLElement getElementNombreNivel() { return this.$element_NombreNivel; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 693 */   public HTMLElement getElementNombrePlan() { return this.$element_NombrePlan; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 702 */   public HTMLElement getElementNroRegistros() { return this.$element_NroRegistros; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 711 */   public HTMLElement getElementObjetivoGeneralNivel() { return this.$element_ObjetivoGeneralNivel; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 720 */   public HTMLInputElement getElementUsuarioInsercion() { return this.$element_UsuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 729 */   public HTMLElement getElementUsuarioInsercionEd() { return this.$element_UsuarioInsercionEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 738 */   public HTMLInputElement getElementUsuarioModificacion() { return this.$element_UsuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 747 */   public HTMLElement getElementUsuarioModificacionEd() { return this.$element_UsuarioModificacionEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 756 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 765 */   public void setTextCodigoObjetivoEspecificoEd(String text) { doSetText(this.$element_CodigoObjetivoEspecificoEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 774 */   public void setTextDescripcionObjetivoEspecificoEd(String text) { doSetText(this.$element_DescripcionObjetivoEspecificoEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 783 */   public void setTextDivConsulta(String text) { doSetText(this.$element_DivConsulta, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 792 */   public void setTextDivCreacionRegistro(String text) { doSetText(this.$element_DivCreacionRegistro, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 801 */   public void setTextDivEdicion(String text) { doSetText(this.$element_DivEdicion, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 810 */   public void setTextDivResultados(String text) { doSetText(this.$element_DivResultados, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 819 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 828 */   public void setTextFechaInsercionEd(String text) { doSetText(this.$element_FechaInsercionEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 837 */   public void setTextFechaModificacionEd(String text) { doSetText(this.$element_FechaModificacionEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 846 */   public void setTextIdObjetivoEspecificoEd(String text) { doSetText(this.$element_IdObjetivoEspecificoEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 855 */   public void setTextIdUnidadNivelEd(String text) { doSetText(this.$element_IdUnidadNivelEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 864 */   public void setTextLabelNombre(String text) { doSetText(this.$element_LabelNombre, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 873 */   public void setTextNombreEntidad(String text) { doSetText(this.$element_NombreEntidad, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 882 */   public void setTextNombreNivel(String text) { doSetText(this.$element_NombreNivel, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 891 */   public void setTextNombrePlan(String text) { doSetText(this.$element_NombrePlan, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 900 */   public void setTextNroRegistros(String text) { doSetText(this.$element_NroRegistros, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 909 */   public void setTextObjetivoGeneralNivel(String text) { doSetText(this.$element_ObjetivoGeneralNivel, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 918 */   public void setTextUsuarioInsercionEd(String text) { doSetText(this.$element_UsuarioInsercionEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 927 */   public void setTextUsuarioModificacionEd(String text) { doSetText(this.$element_UsuarioModificacionEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 936 */     if (node.getNodeType() != 9)
/*     */     {
/* 938 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 942 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 946 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 948 */       int substStart = "$element_".length();
/*     */       
/* 950 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 952 */         Field f = fs[i];
/*     */         
/* 954 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 956 */           String id = f.getName().substring(substStart);
/*     */           
/* 958 */           Node idNode = doc.getElementById(id);
/*     */           
/* 960 */           if (idNode == null) {
/*     */             
/* 962 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 964 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 968 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 972 */     } catch (Exception e) {
/*     */       
/* 974 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PdeObjetivosEspecificosHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */