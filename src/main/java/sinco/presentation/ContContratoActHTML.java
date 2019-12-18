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
/*      */ import org.w3c.dom.html.HTMLTableElement;
/*      */ import org.w3c.dom.html.HTMLTableRowElement;
/*      */ import org.w3c.dom.html.HTMLTableSectionElement;
/*      */ import org.w3c.dom.html.HTMLTextAreaElement;
/*      */ import sinco.presentation.ContContratoActHTML;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ContContratoActHTML
/*      */   extends HTMLObjectImpl
/*      */   implements XMLObject, HTMLObject
/*      */ {
/*      */   private HTMLInputElement $element_ActaInicio;
/*      */   private HTMLInputElement $element_Adicion;
/*      */   private HTMLInputElement $element_BfechaContrato;
/*      */   private HTMLInputElement $element_BfechaContratoHasta;
/*      */   private HTMLInputElement $element_BffechaContratoDesde;
/*      */   private HTMLInputElement $element_BffechaContratoHasta;
/*      */   private HTMLInputElement $element_BffechaFinalDesde;
/*      */   private HTMLInputElement $element_BffechaFinalHasta;
/*      */   private HTMLInputElement $element_BffechaInicioDesde;
/*      */   private HTMLInputElement $element_BffechaInicioHasta;
/*      */   private HTMLInputElement $element_BtnBuscar;
/*      */   private HTMLInputElement $element_BtnCodigoRp;
/*      */   private HTMLInputElement $element_BtnCrear;
/*      */   private HTMLInputElement $element_BtnEliminar;
/*      */   private HTMLInputElement $element_BtnGrabar;
/*      */   private HTMLInputElement $element_BtnInterventor;
/*      */   private HTMLInputElement $element_BtnNuevo;
/*      */   private HTMLInputElement $element_BtnServicios;
/*      */   private HTMLInputElement $element_Cdp;
/*      */   private HTMLInputElement $element_CdpN;
/*      */   private HTMLSelectElement $element_CodigoServicio;
/*      */   private HTMLSelectElement $element_CodigoSucursal;
/*      */   private HTMLInputElement $element_CodigoSucursalHidden;
/*      */   private HTMLTextAreaElement $element_CondicionesEspeciales;
/*      */   private HTMLInputElement $element_ConsecutivoContrato;
/*      */   private HTMLSelectElement $element_Departamento;
/*      */   private HTMLTableSectionElement $element_Detalle;
/*      */   private HTMLTableElement $element_DetalleDocumentos;
/*      */   private HTMLInputElement $element_Direccion;
/*      */   private HTMLInputElement $element_DocInterventor;
/*      */   private HTMLScriptElement $element_ElMenu;
/*      */   private HTMLInputElement $element_Email;
/*      */   private HTMLSelectElement $element_Estado;
/*      */   private HTMLSelectElement $element_Expedida;
/*      */   private HTMLSelectElement $element_ExpedidaMun;
/*      */   private HTMLSelectElement $element_FcodigoSucursal;
/*      */   private HTMLSelectElement $element_Fdepartamento;
/*      */   private HTMLInputElement $element_FechaContrato;
/*      */   private HTMLInputElement $element_FechaContratoHasta;
/*      */   private HTMLInputElement $element_FechaFinal;
/*      */   private HTMLInputElement $element_FechaInicio;
/*      */   private HTMLInputElement $element_FechaInscripcion;
/*      */   private HTMLInputElement $element_FechaInsercion;
/*      */   private HTMLInputElement $element_FechaModificacion;
/*      */   private HTMLSelectElement $element_Festado;
/*      */   private HTMLInputElement $element_FfechaContratoDesde;
/*      */   private HTMLInputElement $element_FfechaContratoHasta;
/*      */   private HTMLInputElement $element_FfechaFinalDesde;
/*      */   private HTMLInputElement $element_FfechaFinalHasta;
/*      */   private HTMLInputElement $element_FfechaInicioDesde;
/*      */   private HTMLInputElement $element_FfechaInicioHasta;
/*      */   private HTMLInputElement $element_FiltroServicio;
/*      */   private HTMLSelectElement $element_Fimputacion;
/*      */   private HTMLSelectElement $element_Fmunicipio;
/*      */   private HTMLInputElement $element_FnumeroContrato;
/*      */   private HTMLInputElement $element_FnumeroEstudio;
/*      */   private HTMLInputElement $element_FormaContrato;
/*      */   private HTMLElement $element_FormaPago;
/*      */   private HTMLSelectElement $element_FtipoEstudio;
/*      */   private HTMLInputElement $element_GenerarContrato;
/*      */   private HTMLInputElement $element_Impuestos;
/*      */   private HTMLInputElement $element_Liquidar;
/*      */   private HTMLScriptElement $element_MostrarMenu;
/*      */   private HTMLSelectElement $element_Municipio;
/*      */   private HTMLSelectElement $element_NaturalezaJuridica;
/*      */   private HTMLInputElement $element_NombreInterventor;
/*      */   private HTMLElement $element_NroRegistros;
/*      */   private HTMLInputElement $element_Nrocc;
/*      */   private HTMLInputElement $element_NumeroAdicion;
/*      */   private HTMLInputElement $element_NumeroContrato;
/*      */   private HTMLInputElement $element_NumeroEstudio;
/*      */   private HTMLInputElement $element_NumeroFolio;
/*      */   private HTMLInputElement $element_NumeroLibro;
/*      */   private HTMLInputElement $element_NumeroRegistro;
/*      */   private HTMLInputElement $element_Origen;
/*      */   private HTMLInputElement $element_Polizas;
/*      */   private HTMLSelectElement $element_RegimenTributario;
/*      */   private HTMLSelectElement $element_Supervisor;
/*      */   private HTMLInputElement $element_TelefonoCelular;
/*      */   private HTMLInputElement $element_Tipo;
/*      */   private HTMLSelectElement $element_TipoContrato;
/*      */   private HTMLInputElement $element_TipoDocInterventor;
/*      */   private HTMLInputElement $element_TipoEstudio;
/*      */   private HTMLSelectElement $element_TipoPersona;
/*      */   private HTMLSelectElement $element_TipoRed;
/*      */   private HTMLTableRowElement $element_TrCamposJuridicos;
/*      */   private HTMLDivElement $element_TrConsulta;
/*      */   private HTMLDivElement $element_TrCreacionRegistro;
/*      */   private HTMLDivElement $element_TrDocumentos;
/*      */   private HTMLDivElement $element_TrResultados;
/*      */   private HTMLElement $element_TxtFormaContrato;
/*      */   private HTMLInputElement $element_UsuarioInsercion;
/*      */   private HTMLInputElement $element_UsuarioModificacion;
/*      */   private HTMLInputElement $element_ValorContrato;
/*      */   private HTMLInputElement $element_ValorContratoLetra;
/*      */   private HTMLInputElement $element_Vigencia;
/*      */   private HTMLInputElement $element__operacion;
/*      */   public static final String CLASS_BOT = "BOT";
/*      */   public static final String CLASS_IND = "IND";
/*      */   public static final String CLASS_INP = "INP";
/*      */   public static final String CLASS_INPD = "INPD";
/*      */   public static final String CLASS_PIE = "PIE";
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
/*      */   public static final String NAME_btnBuscar = "btnBuscar";
/*      */   public static final String NAME_cdp = "cdp";
/*      */   public static final String NAME_cdpN = "cdpN";
/*      */   public static final String NAME_codigoServicio = "codigoServicio";
/*      */   public static final String NAME_codigoSucursal = "codigoSucursal";
/*      */   public static final String NAME_codigoSucursalHidden = "codigoSucursalHidden";
/*      */   public static final String NAME_condicionesEspeciales = "condicionesEspeciales";
/*      */   public static final String NAME_consecutivoContrato = "consecutivoContrato";
/*      */   public static final String NAME_departamento = "departamento";
/*      */   public static final String NAME_direccion = "direccion";
/*      */   public static final String NAME_docInterventor = "docInterventor";
/*      */   public static final String NAME_email = "email";
/*      */   public static final String NAME_estado = "estado";
/*      */   public static final String NAME_expedida = "expedida";
/*      */   public static final String NAME_expedidaMun = "expedidaMun";
/*      */   public static final String NAME_fcodigoSucursal = "fcodigoSucursal";
/*      */   public static final String NAME_fdepartamento = "fdepartamento";
/*      */   public static final String NAME_fechaContrato = "fechaContrato";
/*      */   public static final String NAME_fechaContratoDesde = "fechaContratoDesde";
/*      */   public static final String NAME_fechaContratoHasta = "fechaContratoHasta";
/*      */   public static final String NAME_fechaFinal = "fechaFinal";
/*      */   public static final String NAME_fechaFinalDesde = "fechaFinalDesde";
/*      */   public static final String NAME_fechaFinalHasta = "fechaFinalHasta";
/*      */   public static final String NAME_fechaInicio = "fechaInicio";
/*      */   public static final String NAME_fechaInicioDesde = "fechaInicioDesde";
/*      */   public static final String NAME_fechaInicioHasta = "fechaInicioHasta";
/*      */   public static final String NAME_fechaInscripcion = "fechaInscripcion";
/*      */   public static final String NAME_fechaInsercion = "fechaInsercion";
/*      */   public static final String NAME_fechaModificacion = "fechaModificacion";
/*      */   public static final String NAME_filtroServicio = "filtroServicio";
/*      */   public static final String NAME_fimputacion = "fimputacion";
/*      */   public static final String NAME_fmunicipio = "fmunicipio";
/*      */   public static final String NAME_formaContrato = "formaContrato";
/*      */   public static final String NAME_formatoExportacion = "formatoExportacion";
/*      */   public static final String NAME_ftipoEstudio = "ftipoEstudio";
/*      */   public static final String NAME_miForma = "miForma";
/*      */   public static final String NAME_miForma2 = "miForma2";
/*      */   public static final String NAME_municipio = "municipio";
/*      */   public static final String NAME_naturalezaJuridica = "naturalezaJuridica";
/*      */   public static final String NAME_nombreInterventor = "nombreInterventor";
/*      */   public static final String NAME_nrocc = "nrocc";
/*      */   public static final String NAME_numeroAdicion = "numeroAdicion";
/*      */   public static final String NAME_numeroContrato = "numeroContrato";
/*      */   public static final String NAME_numeroEstudio = "numeroEstudio";
/*      */   public static final String NAME_numeroFolio = "numeroFolio";
/*      */   public static final String NAME_numeroLibro = "numeroLibro";
/*      */   public static final String NAME_numeroRegistro = "numeroRegistro";
/*      */   public static final String NAME_origen = "origen";
/*      */   public static final String NAME_regimenTributario = "regimenTributario";
/*      */   public static final String NAME_reporte = "reporte";
/*      */   public static final String NAME_supervisor = "supervisor";
/*      */   public static final String NAME_telefonoCelular = "telefonoCelular";
/*      */   public static final String NAME_tipo = "tipo";
/*      */   public static final String NAME_tipoContrato = "tipoContrato";
/*      */   public static final String NAME_tipoDocInterventor = "tipoDocInterventor";
/*      */   public static final String NAME_tipoEstudio = "tipoEstudio";
/*      */   public static final String NAME_tipoPersona = "tipoPersona";
/*      */   public static final String NAME_tipoRed = "tipoRed";
/*      */   public static final String NAME_txtCont5 = "txtCont5";
/*      */   public static final String NAME_usuarioInsercion = "usuarioInsercion";
/*      */   public static final String NAME_usuarioModificacion = "usuarioModificacion";
/*      */   public static final String NAME_valorContrato = "valorContrato";
/*      */   public static final String NAME_valorContratoLetra = "valorContratoLetra";
/*      */   public static final String NAME_vigencia = "vigencia";
/*  630 */   public static final Class XMLC_GENERATED_CLASS = ContContratoActHTML.class;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/ContContratoAct.html";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  641 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final DocumentLoader fDocumentLoader;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ContContratoActHTML() {
/*  652 */     this(StandardDocumentLoader.getInstance());
/*      */     
/*  654 */     buildDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  662 */   public ContContratoActHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ContContratoActHTML(ContContratoActHTML src) {
/*  670 */     this.fDocumentLoader = src.getDocumentLoader();
/*      */     
/*  672 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*      */     
/*  674 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ContContratoActHTML(DocumentLoader loader, boolean buildDOM) {
/*  685 */     this.fDocumentLoader = loader;
/*      */     
/*  687 */     if (buildDOM)
/*      */     {
/*  689 */       buildDocument();
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
/*  701 */   public ContContratoActHTML(DocumentLoader loader) { this(loader, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void buildDocument() {
/*  709 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*      */     
/*  711 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node cloneNode(boolean deep) {
/*  719 */     cloneDeepCheck(deep);
/*      */     
/*  721 */     return new ContContratoActHTML(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  729 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  737 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  746 */   public HTMLInputElement getElementActaInicio() { return this.$element_ActaInicio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  755 */   public HTMLInputElement getElementAdicion() { return this.$element_Adicion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  764 */   public HTMLInputElement getElementBfechaContrato() { return this.$element_BfechaContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  773 */   public HTMLInputElement getElementBfechaContratoHasta() { return this.$element_BfechaContratoHasta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  782 */   public HTMLInputElement getElementBffechaContratoDesde() { return this.$element_BffechaContratoDesde; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  791 */   public HTMLInputElement getElementBffechaContratoHasta() { return this.$element_BffechaContratoHasta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  800 */   public HTMLInputElement getElementBffechaFinalDesde() { return this.$element_BffechaFinalDesde; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  809 */   public HTMLInputElement getElementBffechaFinalHasta() { return this.$element_BffechaFinalHasta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  818 */   public HTMLInputElement getElementBffechaInicioDesde() { return this.$element_BffechaInicioDesde; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  827 */   public HTMLInputElement getElementBffechaInicioHasta() { return this.$element_BffechaInicioHasta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  836 */   public HTMLInputElement getElementBtnBuscar() { return this.$element_BtnBuscar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  845 */   public HTMLInputElement getElementBtnCodigoRp() { return this.$element_BtnCodigoRp; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  854 */   public HTMLInputElement getElementBtnCrear() { return this.$element_BtnCrear; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  863 */   public HTMLInputElement getElementBtnEliminar() { return this.$element_BtnEliminar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  872 */   public HTMLInputElement getElementBtnGrabar() { return this.$element_BtnGrabar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  881 */   public HTMLInputElement getElementBtnInterventor() { return this.$element_BtnInterventor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  890 */   public HTMLInputElement getElementBtnNuevo() { return this.$element_BtnNuevo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  899 */   public HTMLInputElement getElementBtnServicios() { return this.$element_BtnServicios; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  908 */   public HTMLInputElement getElementCdp() { return this.$element_Cdp; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  917 */   public HTMLInputElement getElementCdpN() { return this.$element_CdpN; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  926 */   public HTMLSelectElement getElementCodigoServicio() { return this.$element_CodigoServicio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  935 */   public HTMLSelectElement getElementCodigoSucursal() { return this.$element_CodigoSucursal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  944 */   public HTMLInputElement getElementCodigoSucursalHidden() { return this.$element_CodigoSucursalHidden; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  953 */   public HTMLTextAreaElement getElementCondicionesEspeciales() { return this.$element_CondicionesEspeciales; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  962 */   public HTMLInputElement getElementConsecutivoContrato() { return this.$element_ConsecutivoContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  971 */   public HTMLSelectElement getElementDepartamento() { return this.$element_Departamento; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  980 */   public HTMLTableSectionElement getElementDetalle() { return this.$element_Detalle; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  989 */   public HTMLTableElement getElementDetalleDocumentos() { return this.$element_DetalleDocumentos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  998 */   public HTMLInputElement getElementDireccion() { return this.$element_Direccion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1007 */   public HTMLInputElement getElementDocInterventor() { return this.$element_DocInterventor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1016 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1025 */   public HTMLInputElement getElementEmail() { return this.$element_Email; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1034 */   public HTMLSelectElement getElementEstado() { return this.$element_Estado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1043 */   public HTMLSelectElement getElementExpedida() { return this.$element_Expedida; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1052 */   public HTMLSelectElement getElementExpedidaMun() { return this.$element_ExpedidaMun; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1061 */   public HTMLSelectElement getElementFcodigoSucursal() { return this.$element_FcodigoSucursal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1070 */   public HTMLSelectElement getElementFdepartamento() { return this.$element_Fdepartamento; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1079 */   public HTMLInputElement getElementFechaContrato() { return this.$element_FechaContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1088 */   public HTMLInputElement getElementFechaContratoHasta() { return this.$element_FechaContratoHasta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1097 */   public HTMLInputElement getElementFechaFinal() { return this.$element_FechaFinal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1106 */   public HTMLInputElement getElementFechaInicio() { return this.$element_FechaInicio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1115 */   public HTMLInputElement getElementFechaInscripcion() { return this.$element_FechaInscripcion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1124 */   public HTMLInputElement getElementFechaInsercion() { return this.$element_FechaInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1133 */   public HTMLInputElement getElementFechaModificacion() { return this.$element_FechaModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1142 */   public HTMLSelectElement getElementFestado() { return this.$element_Festado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1151 */   public HTMLInputElement getElementFfechaContratoDesde() { return this.$element_FfechaContratoDesde; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1160 */   public HTMLInputElement getElementFfechaContratoHasta() { return this.$element_FfechaContratoHasta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1169 */   public HTMLInputElement getElementFfechaFinalDesde() { return this.$element_FfechaFinalDesde; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1178 */   public HTMLInputElement getElementFfechaFinalHasta() { return this.$element_FfechaFinalHasta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1187 */   public HTMLInputElement getElementFfechaInicioDesde() { return this.$element_FfechaInicioDesde; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1196 */   public HTMLInputElement getElementFfechaInicioHasta() { return this.$element_FfechaInicioHasta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1205 */   public HTMLInputElement getElementFiltroServicio() { return this.$element_FiltroServicio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1214 */   public HTMLSelectElement getElementFimputacion() { return this.$element_Fimputacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1223 */   public HTMLSelectElement getElementFmunicipio() { return this.$element_Fmunicipio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1232 */   public HTMLInputElement getElementFnumeroContrato() { return this.$element_FnumeroContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1241 */   public HTMLInputElement getElementFnumeroEstudio() { return this.$element_FnumeroEstudio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1250 */   public HTMLInputElement getElementFormaContrato() { return this.$element_FormaContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1259 */   public HTMLElement getElementFormaPago() { return this.$element_FormaPago; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1268 */   public HTMLSelectElement getElementFtipoEstudio() { return this.$element_FtipoEstudio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1277 */   public HTMLInputElement getElementGenerarContrato() { return this.$element_GenerarContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1286 */   public HTMLInputElement getElementImpuestos() { return this.$element_Impuestos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1295 */   public HTMLInputElement getElementLiquidar() { return this.$element_Liquidar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1304 */   public HTMLScriptElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1313 */   public HTMLSelectElement getElementMunicipio() { return this.$element_Municipio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1322 */   public HTMLSelectElement getElementNaturalezaJuridica() { return this.$element_NaturalezaJuridica; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1331 */   public HTMLInputElement getElementNombreInterventor() { return this.$element_NombreInterventor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1340 */   public HTMLElement getElementNroRegistros() { return this.$element_NroRegistros; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1349 */   public HTMLInputElement getElementNrocc() { return this.$element_Nrocc; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1358 */   public HTMLInputElement getElementNumeroAdicion() { return this.$element_NumeroAdicion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1367 */   public HTMLInputElement getElementNumeroContrato() { return this.$element_NumeroContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1376 */   public HTMLInputElement getElementNumeroEstudio() { return this.$element_NumeroEstudio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1385 */   public HTMLInputElement getElementNumeroFolio() { return this.$element_NumeroFolio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1394 */   public HTMLInputElement getElementNumeroLibro() { return this.$element_NumeroLibro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1403 */   public HTMLInputElement getElementNumeroRegistro() { return this.$element_NumeroRegistro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1412 */   public HTMLInputElement getElementOrigen() { return this.$element_Origen; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1421 */   public HTMLInputElement getElementPolizas() { return this.$element_Polizas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1430 */   public HTMLSelectElement getElementRegimenTributario() { return this.$element_RegimenTributario; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1439 */   public HTMLSelectElement getElementSupervisor() { return this.$element_Supervisor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1448 */   public HTMLInputElement getElementTelefonoCelular() { return this.$element_TelefonoCelular; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1457 */   public HTMLInputElement getElementTipo() { return this.$element_Tipo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1466 */   public HTMLSelectElement getElementTipoContrato() { return this.$element_TipoContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1475 */   public HTMLInputElement getElementTipoDocInterventor() { return this.$element_TipoDocInterventor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1484 */   public HTMLInputElement getElementTipoEstudio() { return this.$element_TipoEstudio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1493 */   public HTMLSelectElement getElementTipoPersona() { return this.$element_TipoPersona; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1502 */   public HTMLSelectElement getElementTipoRed() { return this.$element_TipoRed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1511 */   public HTMLTableRowElement getElementTrCamposJuridicos() { return this.$element_TrCamposJuridicos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1520 */   public HTMLDivElement getElementTrConsulta() { return this.$element_TrConsulta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1529 */   public HTMLDivElement getElementTrCreacionRegistro() { return this.$element_TrCreacionRegistro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1538 */   public HTMLDivElement getElementTrDocumentos() { return this.$element_TrDocumentos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1547 */   public HTMLDivElement getElementTrResultados() { return this.$element_TrResultados; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1556 */   public HTMLElement getElementTxtFormaContrato() { return this.$element_TxtFormaContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1565 */   public HTMLInputElement getElementUsuarioInsercion() { return this.$element_UsuarioInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1574 */   public HTMLInputElement getElementUsuarioModificacion() { return this.$element_UsuarioModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1583 */   public HTMLInputElement getElementValorContrato() { return this.$element_ValorContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1592 */   public HTMLInputElement getElementValorContratoLetra() { return this.$element_ValorContratoLetra; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1601 */   public HTMLInputElement getElementVigencia() { return this.$element_Vigencia; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1610 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1619 */   public void setTextCondicionesEspeciales(String text) { doSetText(this.$element_CondicionesEspeciales, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1628 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1637 */   public void setTextFormaPago(String text) { doSetText(this.$element_FormaPago, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1646 */   public void setTextMostrarMenu(String text) { doSetText(this.$element_MostrarMenu, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1655 */   public void setTextNroRegistros(String text) { doSetText(this.$element_NroRegistros, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1664 */   public void setTextTrConsulta(String text) { doSetText(this.$element_TrConsulta, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1673 */   public void setTextTrCreacionRegistro(String text) { doSetText(this.$element_TrCreacionRegistro, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1682 */   public void setTextTrDocumentos(String text) { doSetText(this.$element_TrDocumentos, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1691 */   public void setTextTrResultados(String text) { doSetText(this.$element_TrResultados, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1700 */   public void setTextTxtFormaContrato(String text) { doSetText(this.$element_TxtFormaContrato, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void syncWithDocument(Node node) {
/* 1709 */     if (node.getNodeType() != 9)
/*      */     {
/* 1711 */       throw new XMLCRuntimeException("Node must be a document node");
/*      */     }
/*      */ 
/*      */     
/* 1715 */     Document doc = (Document)node;
/*      */ 
/*      */     
/*      */     try {
/* 1719 */       Field[] fs = getClass().getDeclaredFields();
/*      */       
/* 1721 */       int substStart = "$element_".length();
/*      */       
/* 1723 */       for (int i = 0; i < fs.length; i++) {
/*      */         
/* 1725 */         Field f = fs[i];
/*      */         
/* 1727 */         if (f.getName().startsWith("$element_")) {
/*      */           
/* 1729 */           String id = f.getName().substring(substStart);
/*      */           
/* 1731 */           Node idNode = doc.getElementById(id);
/*      */           
/* 1733 */           if (idNode == null) {
/*      */             
/* 1735 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*      */             
/* 1737 */             idNode = doc.getElementById(id);
/*      */           } 
/*      */ 
/*      */           
/* 1741 */           if (idNode != null) f.set(this, idNode);
/*      */         
/*      */         } 
/*      */       } 
/* 1745 */     } catch (Exception e) {
/*      */       
/* 1747 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ContContratoActHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */