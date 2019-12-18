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
/*      */ import org.w3c.dom.html.HTMLTableElement;
/*      */ import org.w3c.dom.html.HTMLTableSectionElement;
/*      */ import sinco.presentation.IndicadoresHTML;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class IndicadoresHTML
/*      */   extends HTMLObjectImpl
/*      */   implements XMLObject, HTMLObject
/*      */ {
/*      */   private HTMLInputElement $element_AcumuladoEstaVigencia;
/*      */   private HTMLInputElement $element_AcumuladoVigencias;
/*      */   private HTMLSelectElement $element_Area;
/*      */   private HTMLElement $element_AreaEd;
/*      */   private HTMLInputElement $element_BtnAyuda;
/*      */   private HTMLInputElement $element_BtnConsultar;
/*      */   private HTMLInputElement $element_BtnCrear;
/*      */   private HTMLInputElement $element_BtnEliminar;
/*      */   private HTMLInputElement $element_BtnGrabar;
/*      */   private HTMLInputElement $element_BtnModificar;
/*      */   private HTMLInputElement $element_BtnSalir;
/*      */   private HTMLInputElement $element_CodCiclo;
/*      */   private HTMLInputElement $element_CodIndicador;
/*      */   private HTMLInputElement $element_CodigoCar;
/*      */   private HTMLInputElement $element_CodigoIndicador;
/*      */   private HTMLElement $element_CodigoIndicadorEd;
/*      */   private HTMLInputElement $element_CodigoIndicadorKey;
/*      */   private HTMLTableSectionElement $element_Detalle;
/*      */   private HTMLDivElement $element_DivConsulta;
/*      */   private HTMLDivElement $element_DivCreacionRegistro;
/*      */   private HTMLDivElement $element_DivEdicion;
/*      */   private HTMLDivElement $element_DivResultados;
/*      */   private HTMLDivElement $element_DivVariables;
/*      */   private HTMLScriptElement $element_ElMenu;
/*      */   private HTMLInputElement $element_ElUsuario;
/*      */   private HTMLSelectElement $element_Estado;
/*      */   private HTMLElement $element_EstadoEd;
/*      */   private HTMLInputElement $element_FechaInsercion;
/*      */   private HTMLElement $element_FechaInsercionEd;
/*      */   private HTMLInputElement $element_FechaModificacion;
/*      */   private HTMLElement $element_FechaModificacionEd;
/*      */   private HTMLSelectElement $element_Formula;
/*      */   private HTMLElement $element_FormulaEd;
/*      */   private HTMLSelectElement $element_FrecuenciaMedicion;
/*      */   private HTMLElement $element_FrecuenciaMedicionEd;
/*      */   private HTMLInputElement $element_GuardarVaraibles;
/*      */   private HTMLInputElement $element_IndicadorAcuerdo;
/*      */   private HTMLElement $element_IndicadorAcuerdoEd;
/*      */   private HTMLLabelElement $element_LabelNombre;
/*      */   private HTMLInputElement $element_LogroTotal;
/*      */   private HTMLInputElement $element_LogroTotalAcumulado;
/*      */   private HTMLInputElement $element_Mes1;
/*      */   private HTMLInputElement $element_Mes10;
/*      */   private HTMLElement $element_Mes10Ed;
/*      */   private HTMLInputElement $element_Mes11;
/*      */   private HTMLElement $element_Mes11Ed;
/*      */   private HTMLInputElement $element_Mes12;
/*      */   private HTMLElement $element_Mes12Ed;
/*      */   private HTMLElement $element_Mes1Ed;
/*      */   private HTMLInputElement $element_Mes2;
/*      */   private HTMLElement $element_Mes2Ed;
/*      */   private HTMLInputElement $element_Mes3;
/*      */   private HTMLElement $element_Mes3Ed;
/*      */   private HTMLInputElement $element_Mes4;
/*      */   private HTMLElement $element_Mes4Ed;
/*      */   private HTMLInputElement $element_Mes5;
/*      */   private HTMLElement $element_Mes5Ed;
/*      */   private HTMLInputElement $element_Mes6;
/*      */   private HTMLElement $element_Mes6Ed;
/*      */   private HTMLInputElement $element_Mes7;
/*      */   private HTMLElement $element_Mes7Ed;
/*      */   private HTMLInputElement $element_Mes8;
/*      */   private HTMLElement $element_Mes8Ed;
/*      */   private HTMLInputElement $element_Mes9;
/*      */   private HTMLElement $element_Mes9Ed;
/*      */   private HTMLInputElement $element_MetaEstaVigencia;
/*      */   private HTMLSelectElement $element_MetaPlanDeDesarrollo;
/*      */   private HTMLElement $element_MetaPlanDeDesarrolloEd;
/*      */   private HTMLInputElement $element_MetaTotalProgramada;
/*      */   private HTMLInputElement $element_NombreIndicador;
/*      */   private HTMLElement $element_NombreIndicadorEd;
/*      */   private HTMLElement $element_NroRegistros;
/*      */   private HTMLSelectElement $element_ObjetivoEstrategico;
/*      */   private HTMLElement $element_ObjetivoEstrategicoEd;
/*      */   private HTMLInputElement $element_ObjetivoIndicador;
/*      */   private HTMLElement $element_ObjetivoIndicadorEd;
/*      */   private HTMLSelectElement $element_PrioridadEnProducto;
/*      */   private HTMLElement $element_PrioridadEnProductoEd;
/*      */   private HTMLSelectElement $element_Proceso;
/*      */   private HTMLElement $element_ProcesoEd;
/*      */   private HTMLScriptElement $element_ScriptVariables;
/*      */   private HTMLTableElement $element_TablaDatosVigencia;
/*      */   private HTMLTableElement $element_TablaEspeciales;
/*      */   private HTMLTableElement $element_TablaMedicionCr;
/*      */   private HTMLTableElement $element_TablaMedicionEd;
/*      */   private HTMLTableElement $element_TablaVariables;
/*      */   private HTMLSelectElement $element_TipoIndicador;
/*      */   private HTMLElement $element_TipoIndicadorEd;
/*      */   private HTMLSelectElement $element_TipoUnidad;
/*      */   private HTMLInputElement $element_TotalVariables;
/*      */   private HTMLSelectElement $element_UnidadMedida;
/*      */   private HTMLInputElement $element_UsuarioInsercion;
/*      */   private HTMLElement $element_UsuarioInsercionEd;
/*      */   private HTMLInputElement $element_UsuarioModificacion;
/*      */   private HTMLElement $element_UsuarioModificacionEd;
/*      */   private HTMLInputElement $element_Valormes1;
/*      */   private HTMLInputElement $element_Valormes10;
/*      */   private HTMLElement $element_Valormes10Ed;
/*      */   private HTMLInputElement $element_Valormes11;
/*      */   private HTMLElement $element_Valormes11Ed;
/*      */   private HTMLInputElement $element_Valormes12;
/*      */   private HTMLElement $element_Valormes12Ed;
/*      */   private HTMLElement $element_Valormes1Ed;
/*      */   private HTMLInputElement $element_Valormes2;
/*      */   private HTMLElement $element_Valormes2Ed;
/*      */   private HTMLInputElement $element_Valormes3;
/*      */   private HTMLElement $element_Valormes3Ed;
/*      */   private HTMLInputElement $element_Valormes4;
/*      */   private HTMLElement $element_Valormes4Ed;
/*      */   private HTMLInputElement $element_Valormes5;
/*      */   private HTMLElement $element_Valormes5Ed;
/*      */   private HTMLInputElement $element_Valormes6;
/*      */   private HTMLElement $element_Valormes6Ed;
/*      */   private HTMLInputElement $element_Valormes7;
/*      */   private HTMLElement $element_Valormes7Ed;
/*      */   private HTMLInputElement $element_Valormes8;
/*      */   private HTMLElement $element_Valormes8Ed;
/*      */   private HTMLInputElement $element_Valormes9;
/*      */   private HTMLElement $element_Valormes9Ed;
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
/*      */   public static final String NAME_acumuladoEstaVigencia = "acumuladoEstaVigencia";
/*      */   public static final String NAME_acumuladoVigencias = "acumuladoVigencias";
/*      */   public static final String NAME_area = "area";
/*      */   public static final String NAME_codCiclo = "codCiclo";
/*      */   public static final String NAME_codIndicador = "codIndicador";
/*      */   public static final String NAME_codigoCar = "codigoCar";
/*      */   public static final String NAME_codigoIndicador = "codigoIndicador";
/*      */   public static final String NAME_elUsuario = "elUsuario";
/*      */   public static final String NAME_estado = "estado";
/*      */   public static final String NAME_fechaInsercion = "fechaInsercion";
/*      */   public static final String NAME_fechaModificacion = "fechaModificacion";
/*      */   public static final String NAME_ff = "ff";
/*      */   public static final String NAME_formula = "formula";
/*      */   public static final String NAME_frecuenciaMedicion = "frecuenciaMedicion";
/*      */   public static final String NAME_indicadorAcuerdo = "indicadorAcuerdo";
/*      */   public static final String NAME_labelNombre = "labelNombre";
/*      */   public static final String NAME_logroTotal = "logroTotal";
/*      */   public static final String NAME_logroTotalAcumulado = "logroTotalAcumulado";
/*      */   public static final String NAME_mes1 = "mes1";
/*      */   public static final String NAME_mes10 = "mes10";
/*      */   public static final String NAME_mes11 = "mes11";
/*      */   public static final String NAME_mes12 = "mes12";
/*      */   public static final String NAME_mes2 = "mes2";
/*      */   public static final String NAME_mes3 = "mes3";
/*      */   public static final String NAME_mes4 = "mes4";
/*      */   public static final String NAME_mes5 = "mes5";
/*      */   public static final String NAME_mes6 = "mes6";
/*      */   public static final String NAME_mes7 = "mes7";
/*      */   public static final String NAME_mes8 = "mes8";
/*      */   public static final String NAME_mes9 = "mes9";
/*      */   public static final String NAME_metaEstaVigencia = "metaEstaVigencia";
/*      */   public static final String NAME_metaPlanDeDesarrollo = "metaPlanDeDesarrollo";
/*      */   public static final String NAME_metaTotalProgramada = "metaTotalProgramada";
/*      */   public static final String NAME_nombreIndicador = "nombreIndicador";
/*      */   public static final String NAME_objetivoEstrategico = "objetivoEstrategico";
/*      */   public static final String NAME_objetivoIndicador = "objetivoIndicador";
/*      */   public static final String NAME_prioridadEnProducto = "prioridadEnProducto";
/*      */   public static final String NAME_proceso = "proceso";
/*      */   public static final String NAME_tipoIndicador = "tipoIndicador";
/*      */   public static final String NAME_tipoUnidad = "tipoUnidad";
/*      */   public static final String NAME_totalVariables = "totalVariables";
/*      */   public static final String NAME_unidadMedida = "unidadMedida";
/*      */   public static final String NAME_usuarioInsercion = "usuarioInsercion";
/*      */   public static final String NAME_usuarioModificacion = "usuarioModificacion";
/*      */   public static final String NAME_valormes1 = "valormes1";
/*      */   public static final String NAME_valormes10 = "valormes10";
/*      */   public static final String NAME_valormes11 = "valormes11";
/*      */   public static final String NAME_valormes12 = "valormes12";
/*      */   public static final String NAME_valormes2 = "valormes2";
/*      */   public static final String NAME_valormes3 = "valormes3";
/*      */   public static final String NAME_valormes4 = "valormes4";
/*      */   public static final String NAME_valormes5 = "valormes5";
/*      */   public static final String NAME_valormes6 = "valormes6";
/*      */   public static final String NAME_valormes7 = "valormes7";
/*      */   public static final String NAME_valormes8 = "valormes8";
/*      */   public static final String NAME_valormes9 = "valormes9";
/*  631 */   public static final Class XMLC_GENERATED_CLASS = IndicadoresHTML.class;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/Indicadores.html";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  642 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final DocumentLoader fDocumentLoader;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IndicadoresHTML() {
/*  653 */     this(StandardDocumentLoader.getInstance());
/*      */     
/*  655 */     buildDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  663 */   public IndicadoresHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IndicadoresHTML(IndicadoresHTML src) {
/*  671 */     this.fDocumentLoader = src.getDocumentLoader();
/*      */     
/*  673 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*      */     
/*  675 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IndicadoresHTML(DocumentLoader loader, boolean buildDOM) {
/*  686 */     this.fDocumentLoader = loader;
/*      */     
/*  688 */     if (buildDOM)
/*      */     {
/*  690 */       buildDocument();
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
/*  702 */   public IndicadoresHTML(DocumentLoader loader) { this(loader, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void buildDocument() {
/*  710 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*      */     
/*  712 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node cloneNode(boolean deep) {
/*  720 */     cloneDeepCheck(deep);
/*      */     
/*  722 */     return new IndicadoresHTML(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  730 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  738 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  747 */   public HTMLInputElement getElementAcumuladoEstaVigencia() { return this.$element_AcumuladoEstaVigencia; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  756 */   public HTMLInputElement getElementAcumuladoVigencias() { return this.$element_AcumuladoVigencias; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  765 */   public HTMLSelectElement getElementArea() { return this.$element_Area; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  774 */   public HTMLElement getElementAreaEd() { return this.$element_AreaEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  783 */   public HTMLInputElement getElementBtnAyuda() { return this.$element_BtnAyuda; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  792 */   public HTMLInputElement getElementBtnConsultar() { return this.$element_BtnConsultar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  801 */   public HTMLInputElement getElementBtnCrear() { return this.$element_BtnCrear; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  810 */   public HTMLInputElement getElementBtnEliminar() { return this.$element_BtnEliminar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  819 */   public HTMLInputElement getElementBtnGrabar() { return this.$element_BtnGrabar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  828 */   public HTMLInputElement getElementBtnModificar() { return this.$element_BtnModificar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  837 */   public HTMLInputElement getElementBtnSalir() { return this.$element_BtnSalir; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  846 */   public HTMLInputElement getElementCodCiclo() { return this.$element_CodCiclo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  855 */   public HTMLInputElement getElementCodIndicador() { return this.$element_CodIndicador; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  864 */   public HTMLInputElement getElementCodigoCar() { return this.$element_CodigoCar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  873 */   public HTMLInputElement getElementCodigoIndicador() { return this.$element_CodigoIndicador; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  882 */   public HTMLElement getElementCodigoIndicadorEd() { return this.$element_CodigoIndicadorEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  891 */   public HTMLInputElement getElementCodigoIndicadorKey() { return this.$element_CodigoIndicadorKey; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  900 */   public HTMLTableSectionElement getElementDetalle() { return this.$element_Detalle; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  909 */   public HTMLDivElement getElementDivConsulta() { return this.$element_DivConsulta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  918 */   public HTMLDivElement getElementDivCreacionRegistro() { return this.$element_DivCreacionRegistro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  927 */   public HTMLDivElement getElementDivEdicion() { return this.$element_DivEdicion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  936 */   public HTMLDivElement getElementDivResultados() { return this.$element_DivResultados; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  945 */   public HTMLDivElement getElementDivVariables() { return this.$element_DivVariables; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  954 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  963 */   public HTMLInputElement getElementElUsuario() { return this.$element_ElUsuario; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  972 */   public HTMLSelectElement getElementEstado() { return this.$element_Estado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  981 */   public HTMLElement getElementEstadoEd() { return this.$element_EstadoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  990 */   public HTMLInputElement getElementFechaInsercion() { return this.$element_FechaInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  999 */   public HTMLElement getElementFechaInsercionEd() { return this.$element_FechaInsercionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1008 */   public HTMLInputElement getElementFechaModificacion() { return this.$element_FechaModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1017 */   public HTMLElement getElementFechaModificacionEd() { return this.$element_FechaModificacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1026 */   public HTMLSelectElement getElementFormula() { return this.$element_Formula; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1035 */   public HTMLElement getElementFormulaEd() { return this.$element_FormulaEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1044 */   public HTMLSelectElement getElementFrecuenciaMedicion() { return this.$element_FrecuenciaMedicion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1053 */   public HTMLElement getElementFrecuenciaMedicionEd() { return this.$element_FrecuenciaMedicionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1062 */   public HTMLInputElement getElementGuardarVaraibles() { return this.$element_GuardarVaraibles; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1071 */   public HTMLInputElement getElementIndicadorAcuerdo() { return this.$element_IndicadorAcuerdo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1080 */   public HTMLElement getElementIndicadorAcuerdoEd() { return this.$element_IndicadorAcuerdoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1089 */   public HTMLLabelElement getElementLabelNombre() { return this.$element_LabelNombre; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1098 */   public HTMLInputElement getElementLogroTotal() { return this.$element_LogroTotal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1107 */   public HTMLInputElement getElementLogroTotalAcumulado() { return this.$element_LogroTotalAcumulado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1116 */   public HTMLInputElement getElementMes1() { return this.$element_Mes1; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1125 */   public HTMLInputElement getElementMes10() { return this.$element_Mes10; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1134 */   public HTMLElement getElementMes10Ed() { return this.$element_Mes10Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1143 */   public HTMLInputElement getElementMes11() { return this.$element_Mes11; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1152 */   public HTMLElement getElementMes11Ed() { return this.$element_Mes11Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1161 */   public HTMLInputElement getElementMes12() { return this.$element_Mes12; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1170 */   public HTMLElement getElementMes12Ed() { return this.$element_Mes12Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1179 */   public HTMLElement getElementMes1Ed() { return this.$element_Mes1Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1188 */   public HTMLInputElement getElementMes2() { return this.$element_Mes2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1197 */   public HTMLElement getElementMes2Ed() { return this.$element_Mes2Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1206 */   public HTMLInputElement getElementMes3() { return this.$element_Mes3; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1215 */   public HTMLElement getElementMes3Ed() { return this.$element_Mes3Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1224 */   public HTMLInputElement getElementMes4() { return this.$element_Mes4; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1233 */   public HTMLElement getElementMes4Ed() { return this.$element_Mes4Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1242 */   public HTMLInputElement getElementMes5() { return this.$element_Mes5; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1251 */   public HTMLElement getElementMes5Ed() { return this.$element_Mes5Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1260 */   public HTMLInputElement getElementMes6() { return this.$element_Mes6; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1269 */   public HTMLElement getElementMes6Ed() { return this.$element_Mes6Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1278 */   public HTMLInputElement getElementMes7() { return this.$element_Mes7; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1287 */   public HTMLElement getElementMes7Ed() { return this.$element_Mes7Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1296 */   public HTMLInputElement getElementMes8() { return this.$element_Mes8; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1305 */   public HTMLElement getElementMes8Ed() { return this.$element_Mes8Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1314 */   public HTMLInputElement getElementMes9() { return this.$element_Mes9; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1323 */   public HTMLElement getElementMes9Ed() { return this.$element_Mes9Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1332 */   public HTMLInputElement getElementMetaEstaVigencia() { return this.$element_MetaEstaVigencia; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1341 */   public HTMLSelectElement getElementMetaPlanDeDesarrollo() { return this.$element_MetaPlanDeDesarrollo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1350 */   public HTMLElement getElementMetaPlanDeDesarrolloEd() { return this.$element_MetaPlanDeDesarrolloEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1359 */   public HTMLInputElement getElementMetaTotalProgramada() { return this.$element_MetaTotalProgramada; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1368 */   public HTMLInputElement getElementNombreIndicador() { return this.$element_NombreIndicador; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1377 */   public HTMLElement getElementNombreIndicadorEd() { return this.$element_NombreIndicadorEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1386 */   public HTMLElement getElementNroRegistros() { return this.$element_NroRegistros; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1395 */   public HTMLSelectElement getElementObjetivoEstrategico() { return this.$element_ObjetivoEstrategico; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1404 */   public HTMLElement getElementObjetivoEstrategicoEd() { return this.$element_ObjetivoEstrategicoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1413 */   public HTMLInputElement getElementObjetivoIndicador() { return this.$element_ObjetivoIndicador; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1422 */   public HTMLElement getElementObjetivoIndicadorEd() { return this.$element_ObjetivoIndicadorEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1431 */   public HTMLSelectElement getElementPrioridadEnProducto() { return this.$element_PrioridadEnProducto; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1440 */   public HTMLElement getElementPrioridadEnProductoEd() { return this.$element_PrioridadEnProductoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1449 */   public HTMLSelectElement getElementProceso() { return this.$element_Proceso; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1458 */   public HTMLElement getElementProcesoEd() { return this.$element_ProcesoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1467 */   public HTMLScriptElement getElementScriptVariables() { return this.$element_ScriptVariables; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1476 */   public HTMLTableElement getElementTablaDatosVigencia() { return this.$element_TablaDatosVigencia; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1485 */   public HTMLTableElement getElementTablaEspeciales() { return this.$element_TablaEspeciales; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1494 */   public HTMLTableElement getElementTablaMedicionCr() { return this.$element_TablaMedicionCr; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1503 */   public HTMLTableElement getElementTablaMedicionEd() { return this.$element_TablaMedicionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1512 */   public HTMLTableElement getElementTablaVariables() { return this.$element_TablaVariables; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1521 */   public HTMLSelectElement getElementTipoIndicador() { return this.$element_TipoIndicador; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1530 */   public HTMLElement getElementTipoIndicadorEd() { return this.$element_TipoIndicadorEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1539 */   public HTMLSelectElement getElementTipoUnidad() { return this.$element_TipoUnidad; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1548 */   public HTMLInputElement getElementTotalVariables() { return this.$element_TotalVariables; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1557 */   public HTMLSelectElement getElementUnidadMedida() { return this.$element_UnidadMedida; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1566 */   public HTMLInputElement getElementUsuarioInsercion() { return this.$element_UsuarioInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1575 */   public HTMLElement getElementUsuarioInsercionEd() { return this.$element_UsuarioInsercionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1584 */   public HTMLInputElement getElementUsuarioModificacion() { return this.$element_UsuarioModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1593 */   public HTMLElement getElementUsuarioModificacionEd() { return this.$element_UsuarioModificacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1602 */   public HTMLInputElement getElementValormes1() { return this.$element_Valormes1; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1611 */   public HTMLInputElement getElementValormes10() { return this.$element_Valormes10; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1620 */   public HTMLElement getElementValormes10Ed() { return this.$element_Valormes10Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1629 */   public HTMLInputElement getElementValormes11() { return this.$element_Valormes11; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1638 */   public HTMLElement getElementValormes11Ed() { return this.$element_Valormes11Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1647 */   public HTMLInputElement getElementValormes12() { return this.$element_Valormes12; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1656 */   public HTMLElement getElementValormes12Ed() { return this.$element_Valormes12Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1665 */   public HTMLElement getElementValormes1Ed() { return this.$element_Valormes1Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1674 */   public HTMLInputElement getElementValormes2() { return this.$element_Valormes2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1683 */   public HTMLElement getElementValormes2Ed() { return this.$element_Valormes2Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1692 */   public HTMLInputElement getElementValormes3() { return this.$element_Valormes3; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1701 */   public HTMLElement getElementValormes3Ed() { return this.$element_Valormes3Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1710 */   public HTMLInputElement getElementValormes4() { return this.$element_Valormes4; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1719 */   public HTMLElement getElementValormes4Ed() { return this.$element_Valormes4Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1728 */   public HTMLInputElement getElementValormes5() { return this.$element_Valormes5; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1737 */   public HTMLElement getElementValormes5Ed() { return this.$element_Valormes5Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1746 */   public HTMLInputElement getElementValormes6() { return this.$element_Valormes6; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1755 */   public HTMLElement getElementValormes6Ed() { return this.$element_Valormes6Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1764 */   public HTMLInputElement getElementValormes7() { return this.$element_Valormes7; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1773 */   public HTMLElement getElementValormes7Ed() { return this.$element_Valormes7Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1782 */   public HTMLInputElement getElementValormes8() { return this.$element_Valormes8; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1791 */   public HTMLElement getElementValormes8Ed() { return this.$element_Valormes8Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1800 */   public HTMLInputElement getElementValormes9() { return this.$element_Valormes9; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1809 */   public HTMLElement getElementValormes9Ed() { return this.$element_Valormes9Ed; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1818 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1827 */   public void setTextAreaEd(String text) { doSetText(this.$element_AreaEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1836 */   public void setTextCodigoIndicadorEd(String text) { doSetText(this.$element_CodigoIndicadorEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1845 */   public void setTextDivConsulta(String text) { doSetText(this.$element_DivConsulta, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1854 */   public void setTextDivCreacionRegistro(String text) { doSetText(this.$element_DivCreacionRegistro, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1863 */   public void setTextDivEdicion(String text) { doSetText(this.$element_DivEdicion, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1872 */   public void setTextDivResultados(String text) { doSetText(this.$element_DivResultados, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1881 */   public void setTextDivVariables(String text) { doSetText(this.$element_DivVariables, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1890 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1899 */   public void setTextEstadoEd(String text) { doSetText(this.$element_EstadoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1908 */   public void setTextFechaInsercionEd(String text) { doSetText(this.$element_FechaInsercionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1917 */   public void setTextFechaModificacionEd(String text) { doSetText(this.$element_FechaModificacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1926 */   public void setTextFormulaEd(String text) { doSetText(this.$element_FormulaEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1935 */   public void setTextFrecuenciaMedicionEd(String text) { doSetText(this.$element_FrecuenciaMedicionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1944 */   public void setTextIndicadorAcuerdoEd(String text) { doSetText(this.$element_IndicadorAcuerdoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1953 */   public void setTextLabelNombre(String text) { doSetText(this.$element_LabelNombre, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1962 */   public void setTextMes10Ed(String text) { doSetText(this.$element_Mes10Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1971 */   public void setTextMes11Ed(String text) { doSetText(this.$element_Mes11Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1980 */   public void setTextMes12Ed(String text) { doSetText(this.$element_Mes12Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1989 */   public void setTextMes1Ed(String text) { doSetText(this.$element_Mes1Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1998 */   public void setTextMes2Ed(String text) { doSetText(this.$element_Mes2Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2007 */   public void setTextMes3Ed(String text) { doSetText(this.$element_Mes3Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2016 */   public void setTextMes4Ed(String text) { doSetText(this.$element_Mes4Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2025 */   public void setTextMes5Ed(String text) { doSetText(this.$element_Mes5Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2034 */   public void setTextMes6Ed(String text) { doSetText(this.$element_Mes6Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2043 */   public void setTextMes7Ed(String text) { doSetText(this.$element_Mes7Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2052 */   public void setTextMes8Ed(String text) { doSetText(this.$element_Mes8Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2061 */   public void setTextMes9Ed(String text) { doSetText(this.$element_Mes9Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2070 */   public void setTextMetaPlanDeDesarrolloEd(String text) { doSetText(this.$element_MetaPlanDeDesarrolloEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2079 */   public void setTextNombreIndicadorEd(String text) { doSetText(this.$element_NombreIndicadorEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2088 */   public void setTextNroRegistros(String text) { doSetText(this.$element_NroRegistros, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2097 */   public void setTextObjetivoEstrategicoEd(String text) { doSetText(this.$element_ObjetivoEstrategicoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2106 */   public void setTextObjetivoIndicadorEd(String text) { doSetText(this.$element_ObjetivoIndicadorEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2115 */   public void setTextPrioridadEnProductoEd(String text) { doSetText(this.$element_PrioridadEnProductoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2124 */   public void setTextProcesoEd(String text) { doSetText(this.$element_ProcesoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2133 */   public void setTextScriptVariables(String text) { doSetText(this.$element_ScriptVariables, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2142 */   public void setTextTipoIndicadorEd(String text) { doSetText(this.$element_TipoIndicadorEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2151 */   public void setTextUsuarioInsercionEd(String text) { doSetText(this.$element_UsuarioInsercionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2160 */   public void setTextUsuarioModificacionEd(String text) { doSetText(this.$element_UsuarioModificacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2169 */   public void setTextValormes10Ed(String text) { doSetText(this.$element_Valormes10Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2178 */   public void setTextValormes11Ed(String text) { doSetText(this.$element_Valormes11Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2187 */   public void setTextValormes12Ed(String text) { doSetText(this.$element_Valormes12Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2196 */   public void setTextValormes1Ed(String text) { doSetText(this.$element_Valormes1Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2205 */   public void setTextValormes2Ed(String text) { doSetText(this.$element_Valormes2Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2214 */   public void setTextValormes3Ed(String text) { doSetText(this.$element_Valormes3Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2223 */   public void setTextValormes4Ed(String text) { doSetText(this.$element_Valormes4Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2232 */   public void setTextValormes5Ed(String text) { doSetText(this.$element_Valormes5Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2241 */   public void setTextValormes6Ed(String text) { doSetText(this.$element_Valormes6Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2250 */   public void setTextValormes7Ed(String text) { doSetText(this.$element_Valormes7Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2259 */   public void setTextValormes8Ed(String text) { doSetText(this.$element_Valormes8Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2268 */   public void setTextValormes9Ed(String text) { doSetText(this.$element_Valormes9Ed, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void syncWithDocument(Node node) {
/* 2277 */     if (node.getNodeType() != 9)
/*      */     {
/* 2279 */       throw new XMLCRuntimeException("Node must be a document node");
/*      */     }
/*      */ 
/*      */     
/* 2283 */     Document doc = (Document)node;
/*      */ 
/*      */     
/*      */     try {
/* 2287 */       Field[] fs = getClass().getDeclaredFields();
/*      */       
/* 2289 */       int substStart = "$element_".length();
/*      */       
/* 2291 */       for (int i = 0; i < fs.length; i++) {
/*      */         
/* 2293 */         Field f = fs[i];
/*      */         
/* 2295 */         if (f.getName().startsWith("$element_")) {
/*      */           
/* 2297 */           String id = f.getName().substring(substStart);
/*      */           
/* 2299 */           Node idNode = doc.getElementById(id);
/*      */           
/* 2301 */           if (idNode == null) {
/*      */             
/* 2303 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*      */             
/* 2305 */             idNode = doc.getElementById(id);
/*      */           } 
/*      */ 
/*      */           
/* 2309 */           if (idNode != null) f.set(this, idNode);
/*      */         
/*      */         } 
/*      */       } 
/* 2313 */     } catch (Exception e) {
/*      */       
/* 2315 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\IndicadoresHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */