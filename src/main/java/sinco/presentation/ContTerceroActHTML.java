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
/*      */ import org.w3c.dom.html.HTMLElement;
/*      */ import org.w3c.dom.html.HTMLInputElement;
/*      */ import org.w3c.dom.html.HTMLScriptElement;
/*      */ import org.w3c.dom.html.HTMLSelectElement;
/*      */ import org.w3c.dom.html.HTMLTableCellElement;
/*      */ import org.w3c.dom.html.HTMLTableElement;
/*      */ import org.w3c.dom.html.HTMLTableRowElement;
/*      */ import org.w3c.dom.html.HTMLTableSectionElement;
/*      */ import sinco.presentation.ContTerceroActHTML;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ContTerceroActHTML
/*      */   extends HTMLObjectImpl
/*      */   implements XMLObject, HTMLObject
/*      */ {
/*      */   private HTMLInputElement $element_BfechaInscripcion;
/*      */   private HTMLInputElement $element_BotonAgregarDocumentos;
/*      */   private HTMLInputElement $element_BtnEliminar;
/*      */   private HTMLInputElement $element_CnombreIps;
/*      */   private HTMLInputElement $element_CualMunicipio;
/*      */   private HTMLSelectElement $element_Departamento;
/*      */   private HTMLSelectElement $element_DepartamentoIps;
/*      */   private HTMLSelectElement $element_DepartamentoRepresentante;
/*      */   private HTMLTableSectionElement $element_Detalle;
/*      */   private HTMLInputElement $element_Direccion;
/*      */   private HTMLInputElement $element_Documentos;
/*      */   private HTMLSelectElement $element_DocumentosSelect;
/*      */   private HTMLInputElement $element_Dv;
/*      */   private HTMLTableCellElement $element_Dv1;
/*      */   private HTMLTableCellElement $element_Dv2;
/*      */   private HTMLInputElement $element_EMail;
/*      */   private HTMLScriptElement $element_ElMenu;
/*      */   private HTMLInputElement $element_Fax;
/*      */   private HTMLInputElement $element_FechaInscripcion;
/*      */   private HTMLInputElement $element_FechaInsercion;
/*      */   private HTMLInputElement $element_FechaModificacion;
/*      */   private HTMLInputElement $element_Idcodentidad;
/*      */   private HTMLInputElement $element_MiBotonC;
/*      */   private HTMLInputElement $element_MiBotonG;
/*      */   private HTMLInputElement $element_MiBotonM;
/*      */   private HTMLScriptElement $element_MostrarMenu;
/*      */   private HTMLSelectElement $element_Municipio;
/*      */   private HTMLSelectElement $element_MunicipioIps;
/*      */   private HTMLSelectElement $element_MunicipioRepresentante;
/*      */   private HTMLSelectElement $element_NaturalezaJuridica;
/*      */   private HTMLInputElement $element_NombreEntidad;
/*      */   private HTMLElement $element_NroRegistros;
/*      */   private HTMLInputElement $element_NumeroDocumento;
/*      */   private HTMLInputElement $element_NumeroFolio;
/*      */   private HTMLInputElement $element_NumeroIdentificacion;
/*      */   private HTMLInputElement $element_NumeroIdentificacionRepresentante;
/*      */   private HTMLInputElement $element_NumeroLibro;
/*      */   private HTMLInputElement $element_NumeroRegistro;
/*      */   private HTMLInputElement $element_PrimerApellidoRepresentante;
/*      */   private HTMLInputElement $element_PrimerNombreRepresentante;
/*      */   private HTMLInputElement $element_Salir;
/*      */   private HTMLScriptElement $element_ScriptCarga;
/*      */   private HTMLInputElement $element_SegundoApellidoRepresentante;
/*      */   private HTMLInputElement $element_SegundoNombreRepresentante;
/*      */   private HTMLInputElement $element_SitioWeb;
/*      */   private HTMLTableElement $element_TablaDocumentosSelect;
/*      */   private HTMLInputElement $element_Telefono;
/*      */   private HTMLSelectElement $element_TipoIdentificacion;
/*      */   private HTMLSelectElement $element_TipoIdentificacionRepresentante;
/*      */   private HTMLSelectElement $element_TipoPersona;
/*      */   private HTMLTableRowElement $element_TrCamposJuridicos;
/*      */   private HTMLTableRowElement $element_TrConsulta;
/*      */   private HTMLTableRowElement $element_TrCreacionRegistro;
/*      */   private HTMLTableRowElement $element_TrRepresentante;
/*      */   private HTMLTableRowElement $element_TrResultados;
/*      */   private HTMLInputElement $element_UsuarioInsercion;
/*      */   private HTMLInputElement $element_UsuarioModificacion;
/*      */   private HTMLInputElement $element__operacion;
/*      */   private HTMLInputElement $element__operacionCarga;
/*      */   public static final String CLASS_BOT = "BOT";
/*      */   public static final String CLASS_IND = "IND";
/*      */   public static final String CLASS_INP = "INP";
/*      */   public static final String CLASS_PIE = "PIE";
/*      */   public static final String CLASS_bot = "bot";
/*      */   public static final String CLASS_btnCalendario = "btnCalendario";
/*      */   public static final String CLASS_ca = "ca";
/*      */   public static final String CLASS_cb = "cb";
/*      */   public static final String CLASS_cf1 = "cf1";
/*      */   public static final String CLASS_cf2 = "cf2";
/*      */   public static final String CLASS_container = "container";
/*      */   public static final String CLASS_dat = "dat";
/*      */   public static final String CLASS_dat2 = "dat2";
/*      */   public static final String CLASS_resizable = "resizable";
/*      */   public static final String CLASS_sortable = "sortable";
/*      */   public static final String CLASS_tabf = "tabf";
/*      */   public static final String CLASS_tabw = "tabw";
/*      */   public static final String CLASS_tit = "tit";
/*      */   public static final String NAME__operacion = "_operacion";
/*      */   public static final String NAME__operacionCarga = "_operacionCarga";
/*      */   public static final String NAME_botonAgregarDocumentos = "botonAgregarDocumentos";
/*      */   public static final String NAME_cualMunicipio = "cualMunicipio";
/*      */   public static final String NAME_departamento = "departamento";
/*      */   public static final String NAME_departamentoIps = "departamentoIps";
/*      */   public static final String NAME_departamentoRepresentante = "departamentoRepresentante";
/*      */   public static final String NAME_direccion = "direccion";
/*      */   public static final String NAME_documentos = "documentos";
/*      */   public static final String NAME_documentosSelect = "documentosSelect";
/*      */   public static final String NAME_dv = "dv";
/*      */   public static final String NAME_eMail = "eMail";
/*      */   public static final String NAME_fax = "fax";
/*      */   public static final String NAME_fechaInscripcion = "fechaInscripcion";
/*      */   public static final String NAME_fechaInsercion = "fechaInsercion";
/*      */   public static final String NAME_fechaModificacion = "fechaModificacion";
/*      */   public static final String NAME_idcodentidad = "idcodentidad";
/*      */   public static final String NAME_miBotonC = "miBotonC";
/*      */   public static final String NAME_miBotonG = "miBotonG";
/*      */   public static final String NAME_miBotonM = "miBotonM";
/*      */   public static final String NAME_miForma = "miForma";
/*      */   public static final String NAME_miForma2 = "miForma2";
/*      */   public static final String NAME_municipio = "municipio";
/*      */   public static final String NAME_municipioIps = "municipioIps";
/*      */   public static final String NAME_municipioRepresentante = "municipioRepresentante";
/*      */   public static final String NAME_naturalezaJuridica = "naturalezaJuridica";
/*      */   public static final String NAME_nombreEntidad = "nombreEntidad";
/*      */   public static final String NAME_nombreIps = "nombreIps";
/*      */   public static final String NAME_numeroDocumento = "numeroDocumento";
/*      */   public static final String NAME_numeroFolio = "numeroFolio";
/*      */   public static final String NAME_numeroIdentificacion = "numeroIdentificacion";
/*      */   public static final String NAME_numeroIdentificacionRepresentante = "numeroIdentificacionRepresentante";
/*      */   public static final String NAME_numeroLibro = "numeroLibro";
/*      */   public static final String NAME_numeroRegistro = "numeroRegistro";
/*      */   public static final String NAME_primerApellidoRepresentante = "primerApellidoRepresentante";
/*      */   public static final String NAME_primerNombreRepresentante = "primerNombreRepresentante";
/*      */   public static final String NAME_salir = "salir";
/*      */   public static final String NAME_segundoApellidoRepresentante = "segundoApellidoRepresentante";
/*      */   public static final String NAME_segundoNombreRepresentante = "segundoNombreRepresentante";
/*      */   public static final String NAME_sitioWeb = "sitioWeb";
/*      */   public static final String NAME_telefono = "telefono";
/*      */   public static final String NAME_tipoIdentificacion = "tipoIdentificacion";
/*      */   public static final String NAME_tipoIdentificacionRepresentante = "tipoIdentificacionRepresentante";
/*      */   public static final String NAME_tipoPersona = "tipoPersona";
/*      */   public static final String NAME_txtCont7 = "txtCont7";
/*      */   public static final String NAME_usuarioInsercion = "usuarioInsercion";
/*      */   public static final String NAME_usuarioModificacion = "usuarioModificacion";
/*  464 */   public static final Class XMLC_GENERATED_CLASS = ContTerceroActHTML.class;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/ContTerceroAct.html";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  475 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final DocumentLoader fDocumentLoader;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ContTerceroActHTML() {
/*  486 */     this(StandardDocumentLoader.getInstance());
/*      */     
/*  488 */     buildDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  496 */   public ContTerceroActHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ContTerceroActHTML(ContTerceroActHTML src) {
/*  504 */     this.fDocumentLoader = src.getDocumentLoader();
/*      */     
/*  506 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*      */     
/*  508 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ContTerceroActHTML(DocumentLoader loader, boolean buildDOM) {
/*  519 */     this.fDocumentLoader = loader;
/*      */     
/*  521 */     if (buildDOM)
/*      */     {
/*  523 */       buildDocument();
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
/*  535 */   public ContTerceroActHTML(DocumentLoader loader) { this(loader, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void buildDocument() {
/*  543 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*      */     
/*  545 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node cloneNode(boolean deep) {
/*  553 */     cloneDeepCheck(deep);
/*      */     
/*  555 */     return new ContTerceroActHTML(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  563 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  571 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  580 */   public HTMLInputElement getElementBfechaInscripcion() { return this.$element_BfechaInscripcion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  589 */   public HTMLInputElement getElementBotonAgregarDocumentos() { return this.$element_BotonAgregarDocumentos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  598 */   public HTMLInputElement getElementBtnEliminar() { return this.$element_BtnEliminar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  607 */   public HTMLInputElement getElementCnombreIps() { return this.$element_CnombreIps; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  616 */   public HTMLInputElement getElementCualMunicipio() { return this.$element_CualMunicipio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  625 */   public HTMLSelectElement getElementDepartamento() { return this.$element_Departamento; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  634 */   public HTMLSelectElement getElementDepartamentoIps() { return this.$element_DepartamentoIps; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  643 */   public HTMLSelectElement getElementDepartamentoRepresentante() { return this.$element_DepartamentoRepresentante; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  652 */   public HTMLTableSectionElement getElementDetalle() { return this.$element_Detalle; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  661 */   public HTMLInputElement getElementDireccion() { return this.$element_Direccion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  670 */   public HTMLInputElement getElementDocumentos() { return this.$element_Documentos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  679 */   public HTMLSelectElement getElementDocumentosSelect() { return this.$element_DocumentosSelect; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  688 */   public HTMLInputElement getElementDv() { return this.$element_Dv; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  697 */   public HTMLTableCellElement getElementDv1() { return this.$element_Dv1; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  706 */   public HTMLTableCellElement getElementDv2() { return this.$element_Dv2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  715 */   public HTMLInputElement getElementEMail() { return this.$element_EMail; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  724 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  733 */   public HTMLInputElement getElementFax() { return this.$element_Fax; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  742 */   public HTMLInputElement getElementFechaInscripcion() { return this.$element_FechaInscripcion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  751 */   public HTMLInputElement getElementFechaInsercion() { return this.$element_FechaInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  760 */   public HTMLInputElement getElementFechaModificacion() { return this.$element_FechaModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  769 */   public HTMLInputElement getElementIdcodentidad() { return this.$element_Idcodentidad; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  778 */   public HTMLInputElement getElementMiBotonC() { return this.$element_MiBotonC; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  787 */   public HTMLInputElement getElementMiBotonG() { return this.$element_MiBotonG; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  796 */   public HTMLInputElement getElementMiBotonM() { return this.$element_MiBotonM; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  805 */   public HTMLScriptElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  814 */   public HTMLSelectElement getElementMunicipio() { return this.$element_Municipio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  823 */   public HTMLSelectElement getElementMunicipioIps() { return this.$element_MunicipioIps; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  832 */   public HTMLSelectElement getElementMunicipioRepresentante() { return this.$element_MunicipioRepresentante; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  841 */   public HTMLSelectElement getElementNaturalezaJuridica() { return this.$element_NaturalezaJuridica; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  850 */   public HTMLInputElement getElementNombreEntidad() { return this.$element_NombreEntidad; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  859 */   public HTMLElement getElementNroRegistros() { return this.$element_NroRegistros; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  868 */   public HTMLInputElement getElementNumeroDocumento() { return this.$element_NumeroDocumento; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  877 */   public HTMLInputElement getElementNumeroFolio() { return this.$element_NumeroFolio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  886 */   public HTMLInputElement getElementNumeroIdentificacion() { return this.$element_NumeroIdentificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  895 */   public HTMLInputElement getElementNumeroIdentificacionRepresentante() { return this.$element_NumeroIdentificacionRepresentante; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  904 */   public HTMLInputElement getElementNumeroLibro() { return this.$element_NumeroLibro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  913 */   public HTMLInputElement getElementNumeroRegistro() { return this.$element_NumeroRegistro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  922 */   public HTMLInputElement getElementPrimerApellidoRepresentante() { return this.$element_PrimerApellidoRepresentante; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  931 */   public HTMLInputElement getElementPrimerNombreRepresentante() { return this.$element_PrimerNombreRepresentante; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  940 */   public HTMLInputElement getElementSalir() { return this.$element_Salir; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  949 */   public HTMLScriptElement getElementScriptCarga() { return this.$element_ScriptCarga; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  958 */   public HTMLInputElement getElementSegundoApellidoRepresentante() { return this.$element_SegundoApellidoRepresentante; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  967 */   public HTMLInputElement getElementSegundoNombreRepresentante() { return this.$element_SegundoNombreRepresentante; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  976 */   public HTMLInputElement getElementSitioWeb() { return this.$element_SitioWeb; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  985 */   public HTMLTableElement getElementTablaDocumentosSelect() { return this.$element_TablaDocumentosSelect; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  994 */   public HTMLInputElement getElementTelefono() { return this.$element_Telefono; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1003 */   public HTMLSelectElement getElementTipoIdentificacion() { return this.$element_TipoIdentificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1012 */   public HTMLSelectElement getElementTipoIdentificacionRepresentante() { return this.$element_TipoIdentificacionRepresentante; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1021 */   public HTMLSelectElement getElementTipoPersona() { return this.$element_TipoPersona; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1030 */   public HTMLTableRowElement getElementTrCamposJuridicos() { return this.$element_TrCamposJuridicos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1039 */   public HTMLTableRowElement getElementTrConsulta() { return this.$element_TrConsulta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1048 */   public HTMLTableRowElement getElementTrCreacionRegistro() { return this.$element_TrCreacionRegistro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1057 */   public HTMLTableRowElement getElementTrRepresentante() { return this.$element_TrRepresentante; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1066 */   public HTMLTableRowElement getElementTrResultados() { return this.$element_TrResultados; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1075 */   public HTMLInputElement getElementUsuarioInsercion() { return this.$element_UsuarioInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1084 */   public HTMLInputElement getElementUsuarioModificacion() { return this.$element_UsuarioModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1093 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1102 */   public HTMLInputElement getElement_operacionCarga() { return this.$element__operacionCarga; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1111 */   public void setTextDv1(String text) { doSetText(this.$element_Dv1, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1120 */   public void setTextDv2(String text) { doSetText(this.$element_Dv2, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1129 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1138 */   public void setTextMostrarMenu(String text) { doSetText(this.$element_MostrarMenu, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1147 */   public void setTextNroRegistros(String text) { doSetText(this.$element_NroRegistros, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1156 */   public void setTextScriptCarga(String text) { doSetText(this.$element_ScriptCarga, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void syncWithDocument(Node node) {
/* 1165 */     if (node.getNodeType() != 9)
/*      */     {
/* 1167 */       throw new XMLCRuntimeException("Node must be a document node");
/*      */     }
/*      */ 
/*      */     
/* 1171 */     Document doc = (Document)node;
/*      */ 
/*      */     
/*      */     try {
/* 1175 */       Field[] fs = getClass().getDeclaredFields();
/*      */       
/* 1177 */       int substStart = "$element_".length();
/*      */       
/* 1179 */       for (int i = 0; i < fs.length; i++) {
/*      */         
/* 1181 */         Field f = fs[i];
/*      */         
/* 1183 */         if (f.getName().startsWith("$element_")) {
/*      */           
/* 1185 */           String id = f.getName().substring(substStart);
/*      */           
/* 1187 */           Node idNode = doc.getElementById(id);
/*      */           
/* 1189 */           if (idNode == null) {
/*      */             
/* 1191 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*      */             
/* 1193 */             idNode = doc.getElementById(id);
/*      */           } 
/*      */ 
/*      */           
/* 1197 */           if (idNode != null) f.set(this, idNode);
/*      */         
/*      */         } 
/*      */       } 
/* 1201 */     } catch (Exception e) {
/*      */       
/* 1203 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ContTerceroActHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */