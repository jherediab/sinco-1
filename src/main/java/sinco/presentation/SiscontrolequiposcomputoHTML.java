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
/*      */ import org.w3c.dom.html.HTMLTableSectionElement;
/*      */ import sinco.presentation.SiscontrolequiposcomputoHTML;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class SiscontrolequiposcomputoHTML
/*      */   extends HTMLObjectImpl
/*      */   implements XMLObject, HTMLObject
/*      */ {
/*      */   private HTMLInputElement $element_AdobeReader;
/*      */   private HTMLElement $element_AdobeReaderEd;
/*      */   private HTMLInputElement $element_Antivirus;
/*      */   private HTMLElement $element_AntivirusEd;
/*      */   private HTMLInputElement $element_BfechaInventario;
/*      */   private HTMLInputElement $element_BfechaRealizado;
/*      */   private HTMLInputElement $element_BtnAyuda;
/*      */   private HTMLInputElement $element_BtnConsultar;
/*      */   private HTMLInputElement $element_BtnCrear;
/*      */   private HTMLInputElement $element_BtnEliminar;
/*      */   private HTMLInputElement $element_BtnGrabar;
/*      */   private HTMLInputElement $element_BtnModificar;
/*      */   private HTMLInputElement $element_BtnSalir;
/*      */   private HTMLInputElement $element_ConectorMause;
/*      */   private HTMLElement $element_ConectorMauseEd;
/*      */   private HTMLInputElement $element_ConectorTeclado;
/*      */   private HTMLElement $element_ConectorTecladoEd;
/*      */   private HTMLInputElement $element_DeepFreeze;
/*      */   private HTMLElement $element_DeepFreezeEd;
/*      */   private HTMLInputElement $element_Descripcion;
/*      */   private HTMLElement $element_DescripcionEd;
/*      */   private HTMLTableSectionElement $element_Detalle;
/*      */   private HTMLDivElement $element_DivConsulta;
/*      */   private HTMLDivElement $element_DivCreacionRegistro;
/*      */   private HTMLDivElement $element_DivEdicion;
/*      */   private HTMLDivElement $element_DivResultados;
/*      */   private HTMLScriptElement $element_ElMenu;
/*      */   private HTMLInputElement $element_EstadoM;
/*      */   private HTMLElement $element_EstadoMEd;
/*      */   private HTMLInputElement $element_EstadoPc;
/*      */   private HTMLElement $element_EstadoPcEd;
/*      */   private HTMLInputElement $element_EstadoT;
/*      */   private HTMLElement $element_EstadoTEd;
/*      */   private HTMLInputElement $element_FechaInventario;
/*      */   private HTMLElement $element_FechaInventarioEd;
/*      */   private HTMLInputElement $element_FechaRealizado;
/*      */   private HTMLElement $element_FechaRealizadoEd;
/*      */   private HTMLLabelElement $element_LabelNombre;
/*      */   private HTMLInputElement $element_Marca;
/*      */   private HTMLInputElement $element_MarcaBoard;
/*      */   private HTMLElement $element_MarcaBoardEd;
/*      */   private HTMLInputElement $element_MarcaCpu;
/*      */   private HTMLElement $element_MarcaCpuEd;
/*      */   private HTMLInputElement $element_MarcaDiscoDuro;
/*      */   private HTMLElement $element_MarcaDiscoDuroEd;
/*      */   private HTMLElement $element_MarcaEd;
/*      */   private HTMLInputElement $element_MarcaFuentePoder;
/*      */   private HTMLElement $element_MarcaFuentePoderEd;
/*      */   private HTMLInputElement $element_MarcaMouse;
/*      */   private HTMLElement $element_MarcaMouseEd;
/*      */   private HTMLInputElement $element_MarcaTeclado;
/*      */   private HTMLElement $element_MarcaTecladoEd;
/*      */   private HTMLInputElement $element_MarcaUnidadCd;
/*      */   private HTMLElement $element_MarcaUnidadCdEd;
/*      */   private HTMLInputElement $element_MemoriaRam;
/*      */   private HTMLElement $element_MemoriaRamEd;
/*      */   private HTMLInputElement $element_MicrosoftOffice;
/*      */   private HTMLElement $element_MicrosoftOfficeEd;
/*      */   private HTMLInputElement $element_NHojaControl;
/*      */   private HTMLElement $element_NHojaControlEd;
/*      */   private HTMLInputElement $element_NHojaControlKey;
/*      */   private HTMLInputElement $element_NInventario;
/*      */   private HTMLInputElement $element_NInventarioCpu;
/*      */   private HTMLElement $element_NInventarioCpuEd;
/*      */   private HTMLElement $element_NInventarioEd;
/*      */   private HTMLInputElement $element_NInventarioPc;
/*      */   private HTMLElement $element_NInventarioPcEd;
/*      */   private HTMLInputElement $element_NInventarioT;
/*      */   private HTMLElement $element_NInventarioTEd;
/*      */   private HTMLInputElement $element_Navegadores;
/*      */   private HTMLElement $element_NavegadoresEd;
/*      */   private HTMLElement $element_NroRegistros;
/*      */   private HTMLInputElement $element_Observaciones;
/*      */   private HTMLElement $element_ObservacionesEd;
/*      */   private HTMLInputElement $element_OtrosSoftware;
/*      */   private HTMLElement $element_OtrosSoftwareEd;
/*      */   private HTMLInputElement $element_ParticionDiscoDuro;
/*      */   private HTMLElement $element_ParticionDiscoDuroEd;
/*      */   private HTMLInputElement $element_Procesador;
/*      */   private HTMLElement $element_ProcesadorEd;
/*      */   private HTMLInputElement $element_Realizo;
/*      */   private HTMLElement $element_RealizoEd;
/*      */   private HTMLInputElement $element_Recibio;
/*      */   private HTMLElement $element_RecibioEd;
/*      */   private HTMLInputElement $element_Serial;
/*      */   private HTMLInputElement $element_SerialBoard;
/*      */   private HTMLElement $element_SerialBoardEd;
/*      */   private HTMLInputElement $element_SerialCpu;
/*      */   private HTMLElement $element_SerialCpuEd;
/*      */   private HTMLInputElement $element_SerialDiscoDuro;
/*      */   private HTMLElement $element_SerialDiscoDuroEd;
/*      */   private HTMLElement $element_SerialEd;
/*      */   private HTMLInputElement $element_SerialUnidadCd;
/*      */   private HTMLElement $element_SerialUnidadCdEd;
/*      */   private HTMLInputElement $element_SistemaOperativo;
/*      */   private HTMLElement $element_SistemaOperativoEd;
/*      */   private HTMLInputElement $element_TargetaRed;
/*      */   private HTMLElement $element_TargetaRedEd;
/*      */   private HTMLInputElement $element_TipoMatenimiento;
/*      */   private HTMLElement $element_TipoMatenimientoEd;
/*      */   private HTMLInputElement $element_Ubicacion;
/*      */   private HTMLElement $element_UbicacionEd;
/*      */   private HTMLInputElement $element_VersionAdobe;
/*      */   private HTMLElement $element_VersionAdobeEd;
/*      */   private HTMLInputElement $element_VersionDeepFreeze;
/*      */   private HTMLElement $element_VersionDeepFreezeEd;
/*      */   private HTMLInputElement $element_VersionOffice;
/*      */   private HTMLElement $element_VersionOfficeEd;
/*      */   private HTMLInputElement $element_VersionSoftware;
/*      */   private HTMLElement $element_VersionSoftwareEd;
/*      */   private HTMLInputElement $element__operacion;
/*      */   public static final String CLASS_BOB = "BOB";
/*      */   public static final String CLASS_BOT = "BOT";
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
/*      */   public static final String CLASS_tabw = "tabw";
/*      */   public static final String CLASS_tit = "tit";
/*      */   public static final String NAME__operacion = "_operacion";
/*      */   public static final String NAME_adobeReader = "adobeReader";
/*      */   public static final String NAME_antivirus = "antivirus";
/*      */   public static final String NAME_conectorMause = "conectorMause";
/*      */   public static final String NAME_conectorTeclado = "conectorTeclado";
/*      */   public static final String NAME_deepFreeze = "deepFreeze";
/*      */   public static final String NAME_descripcion = "descripcion";
/*      */   public static final String NAME_estadoM = "estadoM";
/*      */   public static final String NAME_estadoPc = "estadoPc";
/*      */   public static final String NAME_estadoT = "estadoT";
/*      */   public static final String NAME_fechaInventario = "fechaInventario";
/*      */   public static final String NAME_fechaRealizado = "fechaRealizado";
/*      */   public static final String NAME_ff = "ff";
/*      */   public static final String NAME_labelNombre = "labelNombre";
/*      */   public static final String NAME_marca = "marca";
/*      */   public static final String NAME_marcaBoard = "marcaBoard";
/*      */   public static final String NAME_marcaCpu = "marcaCpu";
/*      */   public static final String NAME_marcaDiscoDuro = "marcaDiscoDuro";
/*      */   public static final String NAME_marcaFuentePoder = "marcaFuentePoder";
/*      */   public static final String NAME_marcaMouse = "marcaMouse";
/*      */   public static final String NAME_marcaTeclado = "marcaTeclado";
/*      */   public static final String NAME_marcaUnidadCd = "marcaUnidadCd";
/*      */   public static final String NAME_memoriaRam = "memoriaRam";
/*      */   public static final String NAME_microsoftOffice = "microsoftOffice";
/*      */   public static final String NAME_nHojaControl = "nHojaControl";
/*      */   public static final String NAME_nInventario = "nInventario";
/*      */   public static final String NAME_nInventarioCpu = "nInventarioCpu";
/*      */   public static final String NAME_nInventarioPc = "nInventarioPc";
/*      */   public static final String NAME_nInventarioT = "nInventarioT";
/*      */   public static final String NAME_navegadores = "navegadores";
/*      */   public static final String NAME_observaciones = "observaciones";
/*      */   public static final String NAME_otrosSoftware = "otrosSoftware";
/*      */   public static final String NAME_particionDiscoDuro = "particionDiscoDuro";
/*      */   public static final String NAME_procesador = "procesador";
/*      */   public static final String NAME_realizo = "realizo";
/*      */   public static final String NAME_recibio = "recibio";
/*      */   public static final String NAME_serial = "serial";
/*      */   public static final String NAME_serialBoard = "serialBoard";
/*      */   public static final String NAME_serialCpu = "serialCpu";
/*      */   public static final String NAME_serialDiscoDuro = "serialDiscoDuro";
/*      */   public static final String NAME_serialUnidadCd = "serialUnidadCd";
/*      */   public static final String NAME_sistemaOperativo = "sistemaOperativo";
/*      */   public static final String NAME_targetaRed = "targetaRed";
/*      */   public static final String NAME_tipoMatenimiento = "tipoMatenimiento";
/*      */   public static final String NAME_ubicacion = "ubicacion";
/*      */   public static final String NAME_versionAdobe = "versionAdobe";
/*      */   public static final String NAME_versionDeepFreeze = "versionDeepFreeze";
/*      */   public static final String NAME_versionOffice = "versionOffice";
/*      */   public static final String NAME_versionSoftware = "versionSoftware";
/*  573 */   public static final Class XMLC_GENERATED_CLASS = SiscontrolequiposcomputoHTML.class;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/Siscontrolequiposcomputo.html";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  584 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final DocumentLoader fDocumentLoader;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SiscontrolequiposcomputoHTML() {
/*  595 */     this(StandardDocumentLoader.getInstance());
/*      */     
/*  597 */     buildDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  605 */   public SiscontrolequiposcomputoHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SiscontrolequiposcomputoHTML(SiscontrolequiposcomputoHTML src) {
/*  613 */     this.fDocumentLoader = src.getDocumentLoader();
/*      */     
/*  615 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*      */     
/*  617 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SiscontrolequiposcomputoHTML(DocumentLoader loader, boolean buildDOM) {
/*  628 */     this.fDocumentLoader = loader;
/*      */     
/*  630 */     if (buildDOM)
/*      */     {
/*  632 */       buildDocument();
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
/*  644 */   public SiscontrolequiposcomputoHTML(DocumentLoader loader) { this(loader, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void buildDocument() {
/*  652 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*      */     
/*  654 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node cloneNode(boolean deep) {
/*  662 */     cloneDeepCheck(deep);
/*      */     
/*  664 */     return new SiscontrolequiposcomputoHTML(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  672 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  680 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  689 */   public HTMLInputElement getElementAdobeReader() { return this.$element_AdobeReader; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  698 */   public HTMLElement getElementAdobeReaderEd() { return this.$element_AdobeReaderEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  707 */   public HTMLInputElement getElementAntivirus() { return this.$element_Antivirus; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  716 */   public HTMLElement getElementAntivirusEd() { return this.$element_AntivirusEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  725 */   public HTMLInputElement getElementBfechaInventario() { return this.$element_BfechaInventario; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  734 */   public HTMLInputElement getElementBfechaRealizado() { return this.$element_BfechaRealizado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  743 */   public HTMLInputElement getElementBtnAyuda() { return this.$element_BtnAyuda; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  752 */   public HTMLInputElement getElementBtnConsultar() { return this.$element_BtnConsultar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  761 */   public HTMLInputElement getElementBtnCrear() { return this.$element_BtnCrear; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  770 */   public HTMLInputElement getElementBtnEliminar() { return this.$element_BtnEliminar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  779 */   public HTMLInputElement getElementBtnGrabar() { return this.$element_BtnGrabar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  788 */   public HTMLInputElement getElementBtnModificar() { return this.$element_BtnModificar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  797 */   public HTMLInputElement getElementBtnSalir() { return this.$element_BtnSalir; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  806 */   public HTMLInputElement getElementConectorMause() { return this.$element_ConectorMause; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  815 */   public HTMLElement getElementConectorMauseEd() { return this.$element_ConectorMauseEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  824 */   public HTMLInputElement getElementConectorTeclado() { return this.$element_ConectorTeclado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  833 */   public HTMLElement getElementConectorTecladoEd() { return this.$element_ConectorTecladoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  842 */   public HTMLInputElement getElementDeepFreeze() { return this.$element_DeepFreeze; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  851 */   public HTMLElement getElementDeepFreezeEd() { return this.$element_DeepFreezeEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  860 */   public HTMLInputElement getElementDescripcion() { return this.$element_Descripcion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  869 */   public HTMLElement getElementDescripcionEd() { return this.$element_DescripcionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  878 */   public HTMLTableSectionElement getElementDetalle() { return this.$element_Detalle; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  887 */   public HTMLDivElement getElementDivConsulta() { return this.$element_DivConsulta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  896 */   public HTMLDivElement getElementDivCreacionRegistro() { return this.$element_DivCreacionRegistro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  905 */   public HTMLDivElement getElementDivEdicion() { return this.$element_DivEdicion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  914 */   public HTMLDivElement getElementDivResultados() { return this.$element_DivResultados; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  923 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  932 */   public HTMLInputElement getElementEstadoM() { return this.$element_EstadoM; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  941 */   public HTMLElement getElementEstadoMEd() { return this.$element_EstadoMEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  950 */   public HTMLInputElement getElementEstadoPc() { return this.$element_EstadoPc; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  959 */   public HTMLElement getElementEstadoPcEd() { return this.$element_EstadoPcEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  968 */   public HTMLInputElement getElementEstadoT() { return this.$element_EstadoT; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  977 */   public HTMLElement getElementEstadoTEd() { return this.$element_EstadoTEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  986 */   public HTMLInputElement getElementFechaInventario() { return this.$element_FechaInventario; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  995 */   public HTMLElement getElementFechaInventarioEd() { return this.$element_FechaInventarioEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1004 */   public HTMLInputElement getElementFechaRealizado() { return this.$element_FechaRealizado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1013 */   public HTMLElement getElementFechaRealizadoEd() { return this.$element_FechaRealizadoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1022 */   public HTMLLabelElement getElementLabelNombre() { return this.$element_LabelNombre; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1031 */   public HTMLInputElement getElementMarca() { return this.$element_Marca; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1040 */   public HTMLInputElement getElementMarcaBoard() { return this.$element_MarcaBoard; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1049 */   public HTMLElement getElementMarcaBoardEd() { return this.$element_MarcaBoardEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1058 */   public HTMLInputElement getElementMarcaCpu() { return this.$element_MarcaCpu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1067 */   public HTMLElement getElementMarcaCpuEd() { return this.$element_MarcaCpuEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1076 */   public HTMLInputElement getElementMarcaDiscoDuro() { return this.$element_MarcaDiscoDuro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1085 */   public HTMLElement getElementMarcaDiscoDuroEd() { return this.$element_MarcaDiscoDuroEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1094 */   public HTMLElement getElementMarcaEd() { return this.$element_MarcaEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1103 */   public HTMLInputElement getElementMarcaFuentePoder() { return this.$element_MarcaFuentePoder; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1112 */   public HTMLElement getElementMarcaFuentePoderEd() { return this.$element_MarcaFuentePoderEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1121 */   public HTMLInputElement getElementMarcaMouse() { return this.$element_MarcaMouse; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1130 */   public HTMLElement getElementMarcaMouseEd() { return this.$element_MarcaMouseEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1139 */   public HTMLInputElement getElementMarcaTeclado() { return this.$element_MarcaTeclado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1148 */   public HTMLElement getElementMarcaTecladoEd() { return this.$element_MarcaTecladoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1157 */   public HTMLInputElement getElementMarcaUnidadCd() { return this.$element_MarcaUnidadCd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1166 */   public HTMLElement getElementMarcaUnidadCdEd() { return this.$element_MarcaUnidadCdEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1175 */   public HTMLInputElement getElementMemoriaRam() { return this.$element_MemoriaRam; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1184 */   public HTMLElement getElementMemoriaRamEd() { return this.$element_MemoriaRamEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1193 */   public HTMLInputElement getElementMicrosoftOffice() { return this.$element_MicrosoftOffice; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1202 */   public HTMLElement getElementMicrosoftOfficeEd() { return this.$element_MicrosoftOfficeEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1211 */   public HTMLInputElement getElementNHojaControl() { return this.$element_NHojaControl; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1220 */   public HTMLElement getElementNHojaControlEd() { return this.$element_NHojaControlEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1229 */   public HTMLInputElement getElementNHojaControlKey() { return this.$element_NHojaControlKey; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1238 */   public HTMLInputElement getElementNInventario() { return this.$element_NInventario; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1247 */   public HTMLInputElement getElementNInventarioCpu() { return this.$element_NInventarioCpu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1256 */   public HTMLElement getElementNInventarioCpuEd() { return this.$element_NInventarioCpuEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1265 */   public HTMLElement getElementNInventarioEd() { return this.$element_NInventarioEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1274 */   public HTMLInputElement getElementNInventarioPc() { return this.$element_NInventarioPc; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1283 */   public HTMLElement getElementNInventarioPcEd() { return this.$element_NInventarioPcEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1292 */   public HTMLInputElement getElementNInventarioT() { return this.$element_NInventarioT; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1301 */   public HTMLElement getElementNInventarioTEd() { return this.$element_NInventarioTEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1310 */   public HTMLInputElement getElementNavegadores() { return this.$element_Navegadores; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1319 */   public HTMLElement getElementNavegadoresEd() { return this.$element_NavegadoresEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1328 */   public HTMLElement getElementNroRegistros() { return this.$element_NroRegistros; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1337 */   public HTMLInputElement getElementObservaciones() { return this.$element_Observaciones; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1346 */   public HTMLElement getElementObservacionesEd() { return this.$element_ObservacionesEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1355 */   public HTMLInputElement getElementOtrosSoftware() { return this.$element_OtrosSoftware; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1364 */   public HTMLElement getElementOtrosSoftwareEd() { return this.$element_OtrosSoftwareEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1373 */   public HTMLInputElement getElementParticionDiscoDuro() { return this.$element_ParticionDiscoDuro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1382 */   public HTMLElement getElementParticionDiscoDuroEd() { return this.$element_ParticionDiscoDuroEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1391 */   public HTMLInputElement getElementProcesador() { return this.$element_Procesador; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1400 */   public HTMLElement getElementProcesadorEd() { return this.$element_ProcesadorEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1409 */   public HTMLInputElement getElementRealizo() { return this.$element_Realizo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1418 */   public HTMLElement getElementRealizoEd() { return this.$element_RealizoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1427 */   public HTMLInputElement getElementRecibio() { return this.$element_Recibio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1436 */   public HTMLElement getElementRecibioEd() { return this.$element_RecibioEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1445 */   public HTMLInputElement getElementSerial() { return this.$element_Serial; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1454 */   public HTMLInputElement getElementSerialBoard() { return this.$element_SerialBoard; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1463 */   public HTMLElement getElementSerialBoardEd() { return this.$element_SerialBoardEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1472 */   public HTMLInputElement getElementSerialCpu() { return this.$element_SerialCpu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1481 */   public HTMLElement getElementSerialCpuEd() { return this.$element_SerialCpuEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1490 */   public HTMLInputElement getElementSerialDiscoDuro() { return this.$element_SerialDiscoDuro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1499 */   public HTMLElement getElementSerialDiscoDuroEd() { return this.$element_SerialDiscoDuroEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1508 */   public HTMLElement getElementSerialEd() { return this.$element_SerialEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1517 */   public HTMLInputElement getElementSerialUnidadCd() { return this.$element_SerialUnidadCd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1526 */   public HTMLElement getElementSerialUnidadCdEd() { return this.$element_SerialUnidadCdEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1535 */   public HTMLInputElement getElementSistemaOperativo() { return this.$element_SistemaOperativo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1544 */   public HTMLElement getElementSistemaOperativoEd() { return this.$element_SistemaOperativoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1553 */   public HTMLInputElement getElementTargetaRed() { return this.$element_TargetaRed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1562 */   public HTMLElement getElementTargetaRedEd() { return this.$element_TargetaRedEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1571 */   public HTMLInputElement getElementTipoMatenimiento() { return this.$element_TipoMatenimiento; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1580 */   public HTMLElement getElementTipoMatenimientoEd() { return this.$element_TipoMatenimientoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1589 */   public HTMLInputElement getElementUbicacion() { return this.$element_Ubicacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1598 */   public HTMLElement getElementUbicacionEd() { return this.$element_UbicacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1607 */   public HTMLInputElement getElementVersionAdobe() { return this.$element_VersionAdobe; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1616 */   public HTMLElement getElementVersionAdobeEd() { return this.$element_VersionAdobeEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1625 */   public HTMLInputElement getElementVersionDeepFreeze() { return this.$element_VersionDeepFreeze; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1634 */   public HTMLElement getElementVersionDeepFreezeEd() { return this.$element_VersionDeepFreezeEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1643 */   public HTMLInputElement getElementVersionOffice() { return this.$element_VersionOffice; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1652 */   public HTMLElement getElementVersionOfficeEd() { return this.$element_VersionOfficeEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1661 */   public HTMLInputElement getElementVersionSoftware() { return this.$element_VersionSoftware; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1670 */   public HTMLElement getElementVersionSoftwareEd() { return this.$element_VersionSoftwareEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1679 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1688 */   public void setTextAdobeReaderEd(String text) { doSetText(this.$element_AdobeReaderEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1697 */   public void setTextAntivirusEd(String text) { doSetText(this.$element_AntivirusEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1706 */   public void setTextConectorMauseEd(String text) { doSetText(this.$element_ConectorMauseEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1715 */   public void setTextConectorTecladoEd(String text) { doSetText(this.$element_ConectorTecladoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1724 */   public void setTextDeepFreezeEd(String text) { doSetText(this.$element_DeepFreezeEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1733 */   public void setTextDescripcionEd(String text) { doSetText(this.$element_DescripcionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1742 */   public void setTextDivConsulta(String text) { doSetText(this.$element_DivConsulta, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1751 */   public void setTextDivCreacionRegistro(String text) { doSetText(this.$element_DivCreacionRegistro, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1760 */   public void setTextDivEdicion(String text) { doSetText(this.$element_DivEdicion, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1769 */   public void setTextDivResultados(String text) { doSetText(this.$element_DivResultados, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1778 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1787 */   public void setTextEstadoMEd(String text) { doSetText(this.$element_EstadoMEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1796 */   public void setTextEstadoPcEd(String text) { doSetText(this.$element_EstadoPcEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1805 */   public void setTextEstadoTEd(String text) { doSetText(this.$element_EstadoTEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1814 */   public void setTextFechaInventarioEd(String text) { doSetText(this.$element_FechaInventarioEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1823 */   public void setTextFechaRealizadoEd(String text) { doSetText(this.$element_FechaRealizadoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1832 */   public void setTextLabelNombre(String text) { doSetText(this.$element_LabelNombre, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1841 */   public void setTextMarcaBoardEd(String text) { doSetText(this.$element_MarcaBoardEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1850 */   public void setTextMarcaCpuEd(String text) { doSetText(this.$element_MarcaCpuEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1859 */   public void setTextMarcaDiscoDuroEd(String text) { doSetText(this.$element_MarcaDiscoDuroEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1868 */   public void setTextMarcaEd(String text) { doSetText(this.$element_MarcaEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1877 */   public void setTextMarcaFuentePoderEd(String text) { doSetText(this.$element_MarcaFuentePoderEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1886 */   public void setTextMarcaMouseEd(String text) { doSetText(this.$element_MarcaMouseEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1895 */   public void setTextMarcaTecladoEd(String text) { doSetText(this.$element_MarcaTecladoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1904 */   public void setTextMarcaUnidadCdEd(String text) { doSetText(this.$element_MarcaUnidadCdEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1913 */   public void setTextMemoriaRamEd(String text) { doSetText(this.$element_MemoriaRamEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1922 */   public void setTextMicrosoftOfficeEd(String text) { doSetText(this.$element_MicrosoftOfficeEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1931 */   public void setTextNHojaControlEd(String text) { doSetText(this.$element_NHojaControlEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1940 */   public void setTextNInventarioCpuEd(String text) { doSetText(this.$element_NInventarioCpuEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1949 */   public void setTextNInventarioEd(String text) { doSetText(this.$element_NInventarioEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1958 */   public void setTextNInventarioPcEd(String text) { doSetText(this.$element_NInventarioPcEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1967 */   public void setTextNInventarioTEd(String text) { doSetText(this.$element_NInventarioTEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1976 */   public void setTextNavegadoresEd(String text) { doSetText(this.$element_NavegadoresEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1985 */   public void setTextNroRegistros(String text) { doSetText(this.$element_NroRegistros, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1994 */   public void setTextObservacionesEd(String text) { doSetText(this.$element_ObservacionesEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2003 */   public void setTextOtrosSoftwareEd(String text) { doSetText(this.$element_OtrosSoftwareEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2012 */   public void setTextParticionDiscoDuroEd(String text) { doSetText(this.$element_ParticionDiscoDuroEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2021 */   public void setTextProcesadorEd(String text) { doSetText(this.$element_ProcesadorEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2030 */   public void setTextRealizoEd(String text) { doSetText(this.$element_RealizoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2039 */   public void setTextRecibioEd(String text) { doSetText(this.$element_RecibioEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2048 */   public void setTextSerialBoardEd(String text) { doSetText(this.$element_SerialBoardEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2057 */   public void setTextSerialCpuEd(String text) { doSetText(this.$element_SerialCpuEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2066 */   public void setTextSerialDiscoDuroEd(String text) { doSetText(this.$element_SerialDiscoDuroEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2075 */   public void setTextSerialEd(String text) { doSetText(this.$element_SerialEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2084 */   public void setTextSerialUnidadCdEd(String text) { doSetText(this.$element_SerialUnidadCdEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2093 */   public void setTextSistemaOperativoEd(String text) { doSetText(this.$element_SistemaOperativoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2102 */   public void setTextTargetaRedEd(String text) { doSetText(this.$element_TargetaRedEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2111 */   public void setTextTipoMatenimientoEd(String text) { doSetText(this.$element_TipoMatenimientoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2120 */   public void setTextUbicacionEd(String text) { doSetText(this.$element_UbicacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2129 */   public void setTextVersionAdobeEd(String text) { doSetText(this.$element_VersionAdobeEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2138 */   public void setTextVersionDeepFreezeEd(String text) { doSetText(this.$element_VersionDeepFreezeEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2147 */   public void setTextVersionOfficeEd(String text) { doSetText(this.$element_VersionOfficeEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2156 */   public void setTextVersionSoftwareEd(String text) { doSetText(this.$element_VersionSoftwareEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void syncWithDocument(Node node) {
/* 2165 */     if (node.getNodeType() != 9)
/*      */     {
/* 2167 */       throw new XMLCRuntimeException("Node must be a document node");
/*      */     }
/*      */ 
/*      */     
/* 2171 */     Document doc = (Document)node;
/*      */ 
/*      */     
/*      */     try {
/* 2175 */       Field[] fs = getClass().getDeclaredFields();
/*      */       
/* 2177 */       int substStart = "$element_".length();
/*      */       
/* 2179 */       for (int i = 0; i < fs.length; i++) {
/*      */         
/* 2181 */         Field f = fs[i];
/*      */         
/* 2183 */         if (f.getName().startsWith("$element_")) {
/*      */           
/* 2185 */           String id = f.getName().substring(substStart);
/*      */           
/* 2187 */           Node idNode = doc.getElementById(id);
/*      */           
/* 2189 */           if (idNode == null) {
/*      */             
/* 2191 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*      */             
/* 2193 */             idNode = doc.getElementById(id);
/*      */           } 
/*      */ 
/*      */           
/* 2197 */           if (idNode != null) f.set(this, idNode);
/*      */         
/*      */         } 
/*      */       } 
/* 2201 */     } catch (Exception e) {
/*      */       
/* 2203 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SiscontrolequiposcomputoHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */