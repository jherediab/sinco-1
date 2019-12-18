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
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLScriptElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableRowElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.presentation.RedPersonalActHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RedPersonalActHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLInputElement $element_BtnEliminar;
/*     */   private HTMLInputElement $element_CartaPresentacion;
/*     */   private HTMLInputElement $element_CertificadoContraloria;
/*     */   private HTMLInputElement $element_CertificadoProcuraduria;
/*     */   private HTMLSelectElement $element_ClasePrestador;
/*     */   private HTMLInputElement $element_CopiaDocumento;
/*     */   private HTMLInputElement $element_CopiaPasadoJudicial;
/*     */   private HTMLInputElement $element_CopiaRut;
/*     */   private HTMLSelectElement $element_Departamento;
/*     */   private HTMLSelectElement $element_DepartamentoExpedicion;
/*     */   private HTMLTableSectionElement $element_Detalle;
/*     */   private HTMLInputElement $element_Direccion;
/*     */   private HTMLInputElement $element_Dv;
/*     */   private HTMLInputElement $element_EMail;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLSelectElement $element_FDepartamento;
/*     */   private HTMLSelectElement $element_FMunicipio;
/*     */   private HTMLInputElement $element_FNumeroIdentificacion;
/*     */   private HTMLInputElement $element_FPrimerApellido;
/*     */   private HTMLInputElement $element_FPrimerNombre;
/*     */   private HTMLSelectElement $element_FTipoIdentificacion;
/*     */   private HTMLInputElement $element_Fax;
/*     */   private HTMLInputElement $element_GarantiaSeriedad;
/*     */   private HTMLInputElement $element_HojaVida;
/*     */   private HTMLInputElement $element_MiBotonC;
/*     */   private HTMLInputElement $element_MiBotonG;
/*     */   private HTMLInputElement $element_MiBotonM;
/*     */   private HTMLScriptElement $element_MostrarMenu;
/*     */   private HTMLSelectElement $element_Municipio;
/*     */   private HTMLSelectElement $element_MunicipioExpedicion;
/*     */   private HTMLElement $element_NroRegistros;
/*     */   private HTMLInputElement $element_NumeroIdentificacion;
/*     */   private HTMLInputElement $element_PrimerApellido;
/*     */   private HTMLInputElement $element_PrimerNombre;
/*     */   private HTMLInputElement $element_PropuestaTecnica;
/*     */   private HTMLInputElement $element_Salir;
/*     */   private HTMLInputElement $element_SegundoApellido;
/*     */   private HTMLInputElement $element_SegundoNombre;
/*     */   private HTMLInputElement $element_SitioWeb;
/*     */   private HTMLInputElement $element_Telefono;
/*     */   private HTMLSelectElement $element_TipoIdentificacion;
/*     */   private HTMLSelectElement $element_TipoPersona;
/*     */   private HTMLTableRowElement $element_TrConsulta;
/*     */   private HTMLTableRowElement $element_TrCreacionRegistro;
/*     */   private HTMLTableRowElement $element_TrResultados;
/*     */   private HTMLInputElement $element__operacion;
/*     */   public static final String CLASS_BOT = "BOT";
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
/*     */   public static final String NAME_clasePrestador = "clasePrestador";
/*     */   public static final String NAME_departamento = "departamento";
/*     */   public static final String NAME_departamentoExpedicion = "departamentoExpedicion";
/*     */   public static final String NAME_direccion = "direccion";
/*     */   public static final String NAME_documento1 = "documento1";
/*     */   public static final String NAME_documento2 = "documento2";
/*     */   public static final String NAME_documento3 = "documento3";
/*     */   public static final String NAME_documento4 = "documento4";
/*     */   public static final String NAME_documento5 = "documento5";
/*     */   public static final String NAME_documento6 = "documento6";
/*     */   public static final String NAME_documento7 = "documento7";
/*     */   public static final String NAME_documento8 = "documento8";
/*     */   public static final String NAME_documento9 = "documento9";
/*     */   public static final String NAME_dv = "dv";
/*     */   public static final String NAME_eMail = "eMail";
/*     */   public static final String NAME_fax = "fax";
/*     */   public static final String NAME_miBotonC = "miBotonC";
/*     */   public static final String NAME_miBotonG = "miBotonG";
/*     */   public static final String NAME_miBotonM = "miBotonM";
/*     */   public static final String NAME_miForma = "miForma";
/*     */   public static final String NAME_miForma2 = "miForma2";
/*     */   public static final String NAME_municipio = "municipio";
/*     */   public static final String NAME_municipioExpedicion = "municipioExpedicion";
/*     */   public static final String NAME_numeroIdentificacion = "numeroIdentificacion";
/*     */   public static final String NAME_primerApellido = "primerApellido";
/*     */   public static final String NAME_primerNombre = "primerNombre";
/*     */   public static final String NAME_salir = "salir";
/*     */   public static final String NAME_segundoApellido = "segundoApellido";
/*     */   public static final String NAME_segundoNombre = "segundoNombre";
/*     */   public static final String NAME_sitioWeb = "sitioWeb";
/*     */   public static final String NAME_telefono = "telefono";
/*     */   public static final String NAME_tipoIdentificacion = "tipoIdentificacion";
/*     */   public static final String NAME_tipoPersona = "tipoPersona";
/* 358 */   public static final Class XMLC_GENERATED_CLASS = RedPersonalActHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/RedPersonalAct.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 369 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RedPersonalActHTML() {
/* 380 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 382 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 390 */   public RedPersonalActHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RedPersonalActHTML(RedPersonalActHTML src) {
/* 398 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 400 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 402 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RedPersonalActHTML(DocumentLoader loader, boolean buildDOM) {
/* 413 */     this.fDocumentLoader = loader;
/*     */     
/* 415 */     if (buildDOM)
/*     */     {
/* 417 */       buildDocument();
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
/* 429 */   public RedPersonalActHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 437 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 439 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 447 */     cloneDeepCheck(deep);
/*     */     
/* 449 */     return new RedPersonalActHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 457 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 465 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 474 */   public HTMLInputElement getElementBtnEliminar() { return this.$element_BtnEliminar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 483 */   public HTMLInputElement getElementCartaPresentacion() { return this.$element_CartaPresentacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 492 */   public HTMLInputElement getElementCertificadoContraloria() { return this.$element_CertificadoContraloria; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 501 */   public HTMLInputElement getElementCertificadoProcuraduria() { return this.$element_CertificadoProcuraduria; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 510 */   public HTMLSelectElement getElementClasePrestador() { return this.$element_ClasePrestador; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 519 */   public HTMLInputElement getElementCopiaDocumento() { return this.$element_CopiaDocumento; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 528 */   public HTMLInputElement getElementCopiaPasadoJudicial() { return this.$element_CopiaPasadoJudicial; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 537 */   public HTMLInputElement getElementCopiaRut() { return this.$element_CopiaRut; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 546 */   public HTMLSelectElement getElementDepartamento() { return this.$element_Departamento; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 555 */   public HTMLSelectElement getElementDepartamentoExpedicion() { return this.$element_DepartamentoExpedicion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 564 */   public HTMLTableSectionElement getElementDetalle() { return this.$element_Detalle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 573 */   public HTMLInputElement getElementDireccion() { return this.$element_Direccion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 582 */   public HTMLInputElement getElementDv() { return this.$element_Dv; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 591 */   public HTMLInputElement getElementEMail() { return this.$element_EMail; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 600 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 609 */   public HTMLSelectElement getElementFDepartamento() { return this.$element_FDepartamento; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 618 */   public HTMLSelectElement getElementFMunicipio() { return this.$element_FMunicipio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 627 */   public HTMLInputElement getElementFNumeroIdentificacion() { return this.$element_FNumeroIdentificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 636 */   public HTMLInputElement getElementFPrimerApellido() { return this.$element_FPrimerApellido; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 645 */   public HTMLInputElement getElementFPrimerNombre() { return this.$element_FPrimerNombre; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 654 */   public HTMLSelectElement getElementFTipoIdentificacion() { return this.$element_FTipoIdentificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 663 */   public HTMLInputElement getElementFax() { return this.$element_Fax; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 672 */   public HTMLInputElement getElementGarantiaSeriedad() { return this.$element_GarantiaSeriedad; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 681 */   public HTMLInputElement getElementHojaVida() { return this.$element_HojaVida; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 690 */   public HTMLInputElement getElementMiBotonC() { return this.$element_MiBotonC; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 699 */   public HTMLInputElement getElementMiBotonG() { return this.$element_MiBotonG; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 708 */   public HTMLInputElement getElementMiBotonM() { return this.$element_MiBotonM; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 717 */   public HTMLScriptElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 726 */   public HTMLSelectElement getElementMunicipio() { return this.$element_Municipio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 735 */   public HTMLSelectElement getElementMunicipioExpedicion() { return this.$element_MunicipioExpedicion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 744 */   public HTMLElement getElementNroRegistros() { return this.$element_NroRegistros; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 753 */   public HTMLInputElement getElementNumeroIdentificacion() { return this.$element_NumeroIdentificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 762 */   public HTMLInputElement getElementPrimerApellido() { return this.$element_PrimerApellido; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 771 */   public HTMLInputElement getElementPrimerNombre() { return this.$element_PrimerNombre; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 780 */   public HTMLInputElement getElementPropuestaTecnica() { return this.$element_PropuestaTecnica; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 789 */   public HTMLInputElement getElementSalir() { return this.$element_Salir; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 798 */   public HTMLInputElement getElementSegundoApellido() { return this.$element_SegundoApellido; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 807 */   public HTMLInputElement getElementSegundoNombre() { return this.$element_SegundoNombre; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 816 */   public HTMLInputElement getElementSitioWeb() { return this.$element_SitioWeb; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 825 */   public HTMLInputElement getElementTelefono() { return this.$element_Telefono; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 834 */   public HTMLSelectElement getElementTipoIdentificacion() { return this.$element_TipoIdentificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 843 */   public HTMLSelectElement getElementTipoPersona() { return this.$element_TipoPersona; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 852 */   public HTMLTableRowElement getElementTrConsulta() { return this.$element_TrConsulta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 861 */   public HTMLTableRowElement getElementTrCreacionRegistro() { return this.$element_TrCreacionRegistro; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 870 */   public HTMLTableRowElement getElementTrResultados() { return this.$element_TrResultados; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 879 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 888 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 897 */   public void setTextMostrarMenu(String text) { doSetText(this.$element_MostrarMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 906 */   public void setTextNroRegistros(String text) { doSetText(this.$element_NroRegistros, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 915 */     if (node.getNodeType() != 9)
/*     */     {
/* 917 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 921 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 925 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 927 */       int substStart = "$element_".length();
/*     */       
/* 929 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 931 */         Field f = fs[i];
/*     */         
/* 933 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 935 */           String id = f.getName().substring(substStart);
/*     */           
/* 937 */           Node idNode = doc.getElementById(id);
/*     */           
/* 939 */           if (idNode == null) {
/*     */             
/* 941 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 943 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 947 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 951 */     } catch (Exception e) {
/*     */       
/* 953 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\RedPersonalActHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */