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
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.presentation.SisSedesHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SisSedesHTML
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
/*     */   private HTMLSelectElement $element_Departamento;
/*     */   private HTMLElement $element_DepartamentoEd;
/*     */   private HTMLTableSectionElement $element_Detalle;
/*     */   private HTMLInputElement $element_Direccion;
/*     */   private HTMLElement $element_DireccionEd;
/*     */   private HTMLDivElement $element_DivConsulta;
/*     */   private HTMLDivElement $element_DivCreacionRegistro;
/*     */   private HTMLDivElement $element_DivEdicion;
/*     */   private HTMLDivElement $element_DivResultados;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLInputElement $element_FechaInsercion;
/*     */   private HTMLElement $element_FechaInsercionEd;
/*     */   private HTMLInputElement $element_FechaModificacion;
/*     */   private HTMLElement $element_FechaModificacionEd;
/*     */   private HTMLInputElement $element_IdSede;
/*     */   private HTMLElement $element_IdSedeEd;
/*     */   private HTMLInputElement $element_IdSedeKey;
/*     */   private HTMLLabelElement $element_LabelNombre;
/*     */   private HTMLSelectElement $element_Municipio;
/*     */   private HTMLElement $element_MunicipioEd;
/*     */   private HTMLElement $element_NitEntidadEd;
/*     */   private HTMLInputElement $element_NitEntidadHidden;
/*     */   private HTMLInputElement $element_NombreSede;
/*     */   private HTMLInputElement $element_NombreSedeConsulta;
/*     */   private HTMLElement $element_NombreSedeEd;
/*     */   private HTMLElement $element_NroRegistros;
/*     */   private HTMLElement $element_SedesEntidad;
/*     */   private HTMLInputElement $element_Telefono;
/*     */   private HTMLElement $element_TelefonoEd;
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
/*     */   public static final String NAME_departamento = "departamento";
/*     */   public static final String NAME_direccion = "direccion";
/*     */   public static final String NAME_fechaInsercion = "fechaInsercion";
/*     */   public static final String NAME_fechaModificacion = "fechaModificacion";
/*     */   public static final String NAME_ff = "ff";
/*     */   public static final String NAME_idSede = "idSede";
/*     */   public static final String NAME_labelNombre = "labelNombre";
/*     */   public static final String NAME_municipio = "municipio";
/*     */   public static final String NAME_nitEntidad = "nitEntidad";
/*     */   public static final String NAME_nombreSede = "nombreSede";
/*     */   public static final String NAME_nombreSedeConsulta = "nombreSedeConsulta";
/*     */   public static final String NAME_telefono = "telefono";
/*     */   public static final String NAME_usuarioInsercion = "usuarioInsercion";
/*     */   public static final String NAME_usuarioModificacion = "usuarioModificacion";
/* 270 */   public static final Class XMLC_GENERATED_CLASS = SisSedesHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/SisSedes.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 281 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisSedesHTML() {
/* 292 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 294 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 302 */   public SisSedesHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisSedesHTML(SisSedesHTML src) {
/* 310 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 312 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 314 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SisSedesHTML(DocumentLoader loader, boolean buildDOM) {
/* 325 */     this.fDocumentLoader = loader;
/*     */     
/* 327 */     if (buildDOM)
/*     */     {
/* 329 */       buildDocument();
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
/* 341 */   public SisSedesHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 349 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 351 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 359 */     cloneDeepCheck(deep);
/*     */     
/* 361 */     return new SisSedesHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 369 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 377 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 386 */   public HTMLInputElement getElementBtnAyuda() { return this.$element_BtnAyuda; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 395 */   public HTMLInputElement getElementBtnConsultar() { return this.$element_BtnConsultar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 404 */   public HTMLInputElement getElementBtnCrear() { return this.$element_BtnCrear; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 413 */   public HTMLInputElement getElementBtnEliminar() { return this.$element_BtnEliminar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 422 */   public HTMLInputElement getElementBtnGrabar() { return this.$element_BtnGrabar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 431 */   public HTMLInputElement getElementBtnModificar() { return this.$element_BtnModificar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 440 */   public HTMLInputElement getElementBtnSalir() { return this.$element_BtnSalir; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 449 */   public HTMLInputElement getElementBtnVolver() { return this.$element_BtnVolver; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 458 */   public HTMLSelectElement getElementDepartamento() { return this.$element_Departamento; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 467 */   public HTMLElement getElementDepartamentoEd() { return this.$element_DepartamentoEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 476 */   public HTMLTableSectionElement getElementDetalle() { return this.$element_Detalle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 485 */   public HTMLInputElement getElementDireccion() { return this.$element_Direccion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 494 */   public HTMLElement getElementDireccionEd() { return this.$element_DireccionEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 503 */   public HTMLDivElement getElementDivConsulta() { return this.$element_DivConsulta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 512 */   public HTMLDivElement getElementDivCreacionRegistro() { return this.$element_DivCreacionRegistro; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 521 */   public HTMLDivElement getElementDivEdicion() { return this.$element_DivEdicion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 530 */   public HTMLDivElement getElementDivResultados() { return this.$element_DivResultados; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 539 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 548 */   public HTMLInputElement getElementFechaInsercion() { return this.$element_FechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 557 */   public HTMLElement getElementFechaInsercionEd() { return this.$element_FechaInsercionEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 566 */   public HTMLInputElement getElementFechaModificacion() { return this.$element_FechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 575 */   public HTMLElement getElementFechaModificacionEd() { return this.$element_FechaModificacionEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 584 */   public HTMLInputElement getElementIdSede() { return this.$element_IdSede; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 593 */   public HTMLElement getElementIdSedeEd() { return this.$element_IdSedeEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 602 */   public HTMLInputElement getElementIdSedeKey() { return this.$element_IdSedeKey; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 611 */   public HTMLLabelElement getElementLabelNombre() { return this.$element_LabelNombre; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 620 */   public HTMLSelectElement getElementMunicipio() { return this.$element_Municipio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 629 */   public HTMLElement getElementMunicipioEd() { return this.$element_MunicipioEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 638 */   public HTMLElement getElementNitEntidadEd() { return this.$element_NitEntidadEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 647 */   public HTMLInputElement getElementNitEntidadHidden() { return this.$element_NitEntidadHidden; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 656 */   public HTMLInputElement getElementNombreSede() { return this.$element_NombreSede; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 665 */   public HTMLInputElement getElementNombreSedeConsulta() { return this.$element_NombreSedeConsulta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 674 */   public HTMLElement getElementNombreSedeEd() { return this.$element_NombreSedeEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 683 */   public HTMLElement getElementNroRegistros() { return this.$element_NroRegistros; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 692 */   public HTMLElement getElementSedesEntidad() { return this.$element_SedesEntidad; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 701 */   public HTMLInputElement getElementTelefono() { return this.$element_Telefono; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 710 */   public HTMLElement getElementTelefonoEd() { return this.$element_TelefonoEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 719 */   public HTMLInputElement getElementUsuarioInsercion() { return this.$element_UsuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 728 */   public HTMLElement getElementUsuarioInsercionEd() { return this.$element_UsuarioInsercionEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 737 */   public HTMLInputElement getElementUsuarioModificacion() { return this.$element_UsuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 746 */   public HTMLElement getElementUsuarioModificacionEd() { return this.$element_UsuarioModificacionEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 755 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 764 */   public void setTextDepartamentoEd(String text) { doSetText(this.$element_DepartamentoEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 773 */   public void setTextDireccionEd(String text) { doSetText(this.$element_DireccionEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 782 */   public void setTextDivConsulta(String text) { doSetText(this.$element_DivConsulta, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 791 */   public void setTextDivCreacionRegistro(String text) { doSetText(this.$element_DivCreacionRegistro, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 800 */   public void setTextDivEdicion(String text) { doSetText(this.$element_DivEdicion, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 809 */   public void setTextDivResultados(String text) { doSetText(this.$element_DivResultados, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 818 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 827 */   public void setTextFechaInsercionEd(String text) { doSetText(this.$element_FechaInsercionEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 836 */   public void setTextFechaModificacionEd(String text) { doSetText(this.$element_FechaModificacionEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 845 */   public void setTextIdSedeEd(String text) { doSetText(this.$element_IdSedeEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 854 */   public void setTextLabelNombre(String text) { doSetText(this.$element_LabelNombre, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 863 */   public void setTextMunicipioEd(String text) { doSetText(this.$element_MunicipioEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 872 */   public void setTextNitEntidadEd(String text) { doSetText(this.$element_NitEntidadEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 881 */   public void setTextNombreSedeEd(String text) { doSetText(this.$element_NombreSedeEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 890 */   public void setTextNroRegistros(String text) { doSetText(this.$element_NroRegistros, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 899 */   public void setTextSedesEntidad(String text) { doSetText(this.$element_SedesEntidad, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 908 */   public void setTextTelefonoEd(String text) { doSetText(this.$element_TelefonoEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 917 */   public void setTextUsuarioInsercionEd(String text) { doSetText(this.$element_UsuarioInsercionEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 926 */   public void setTextUsuarioModificacionEd(String text) { doSetText(this.$element_UsuarioModificacionEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 935 */     if (node.getNodeType() != 9)
/*     */     {
/* 937 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 941 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 945 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 947 */       int substStart = "$element_".length();
/*     */       
/* 949 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 951 */         Field f = fs[i];
/*     */         
/* 953 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 955 */           String id = f.getName().substring(substStart);
/*     */           
/* 957 */           Node idNode = doc.getElementById(id);
/*     */           
/* 959 */           if (idNode == null) {
/*     */             
/* 961 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 963 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 967 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 971 */     } catch (Exception e) {
/*     */       
/* 973 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SisSedesHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */