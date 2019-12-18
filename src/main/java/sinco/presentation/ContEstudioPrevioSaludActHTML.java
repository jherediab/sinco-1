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
/*      */ import org.w3c.dom.html.HTMLFormElement;
/*      */ import org.w3c.dom.html.HTMLInputElement;
/*      */ import org.w3c.dom.html.HTMLScriptElement;
/*      */ import org.w3c.dom.html.HTMLSelectElement;
/*      */ import org.w3c.dom.html.HTMLTableElement;
/*      */ import org.w3c.dom.html.HTMLTableRowElement;
/*      */ import org.w3c.dom.html.HTMLTableSectionElement;
/*      */ import org.w3c.dom.html.HTMLTextAreaElement;
/*      */ import sinco.presentation.ContEstudioPrevioSaludActHTML;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ContEstudioPrevioSaludActHTML
/*      */   extends HTMLObjectImpl
/*      */   implements XMLObject, HTMLObject
/*      */ {
/*      */   private HTMLInputElement $element_ActividadesADesarrollar;
/*      */   private HTMLTextAreaElement $element_ActividadesDesarrollarText;
/*      */   private HTMLInputElement $element_AddActividades;
/*      */   private HTMLInputElement $element_AddServicios;
/*      */   private HTMLInputElement $element_AddTarifa;
/*      */   private HTMLInputElement $element_AlternativasEjecucion;
/*      */   private HTMLTextAreaElement $element_AlternativasEjecucionText;
/*      */   private HTMLInputElement $element_BffechaPresentacionDesde;
/*      */   private HTMLInputElement $element_BffechaPresentacionHasta;
/*      */   private HTMLInputElement $element_BotonAgregarActividadesDesarrollar;
/*      */   private HTMLInputElement $element_BotonAgregarAlternativasEjecucion;
/*      */   private HTMLInputElement $element_BotonAgregarCuadroAct;
/*      */   private HTMLInputElement $element_BotonAgregarDefinicionTecnica;
/*      */   private HTMLInputElement $element_BotonAgregarDescripcionRiesgos;
/*      */   private HTMLInputElement $element_BotonAgregarEspecificacionesTecnicas;
/*      */   private HTMLInputElement $element_BotonAgregarPolizas;
/*      */   private HTMLInputElement $element_BotonAgregarProductoEntregar;
/*      */   private HTMLInputElement $element_BotonFechaPlazo;
/*      */   private HTMLInputElement $element_BotonFechaPresentacion;
/*      */   private HTMLInputElement $element_BtnFirmas;
/*      */   private HTMLSelectElement $element_ClaseGasto;
/*      */   private HTMLInputElement $element_Copiar;
/*      */   private HTMLInputElement $element_Cuantos;
/*      */   private HTMLInputElement $element_CuantosImp;
/*      */   private HTMLInputElement $element_DefinicionTecnica;
/*      */   private HTMLTextAreaElement $element_DefinicionTecnicaText;
/*      */   private HTMLSelectElement $element_Dependencia;
/*      */   private HTMLInputElement $element_DescripcionNecesidad;
/*      */   private HTMLTextAreaElement $element_DescripcionNecesidadText;
/*      */   private HTMLInputElement $element_DescripcionRiesgos;
/*      */   private HTMLTextAreaElement $element_DescripcionRiesgosText;
/*      */   private HTMLTableSectionElement $element_Detalle;
/*      */   private HTMLTableElement $element_DetalleDocumentos;
/*      */   private HTMLScriptElement $element_ElMenu;
/*      */   private HTMLInputElement $element_EliminarRegistro;
/*      */   private HTMLInputElement $element_EspecificacionesTecnicas;
/*      */   private HTMLTextAreaElement $element_EspecificacionesTecnicasText;
/*      */   private HTMLSelectElement $element_Fdependencia;
/*      */   private HTMLInputElement $element_FechaInsercion;
/*      */   private HTMLInputElement $element_FechaModificacion;
/*      */   private HTMLInputElement $element_FechaPresentacion;
/*      */   private HTMLInputElement $element_FfechaPresentacionDesde;
/*      */   private HTMLInputElement $element_FfechaPresentacionHasta;
/*      */   private HTMLSelectElement $element_FformaContrato;
/*      */   private HTMLInputElement $element_FnumeroEstudio;
/*      */   private HTMLSelectElement $element_FormaContrato;
/*      */   private HTMLSelectElement $element_FormaPago;
/*      */   private HTMLTextAreaElement $element_FormaPagoText;
/*      */   private HTMLSelectElement $element_FtipoContrato;
/*      */   private HTMLSelectElement $element_FtipoEstudio;
/*      */   private HTMLDivElement $element_IdCreacionRegistro;
/*      */   private HTMLInputElement $element_Interventor;
/*      */   private HTMLSelectElement $element_Iva;
/*      */   private HTMLInputElement $element_LugarEjecucion;
/*      */   private HTMLInputElement $element_MiBotonContrato;
/*      */   private HTMLInputElement $element_MiBotonG;
/*      */   private HTMLInputElement $element_MiBotonM;
/*      */   private HTMLFormElement $element_MiForma;
/*      */   private HTMLScriptElement $element_MostrarMenu;
/*      */   private HTMLInputElement $element_NombreImputacionPresupuestal;
/*      */   private HTMLElement $element_NroRegistros;
/*      */   private HTMLInputElement $element_NumeroEstudio;
/*      */   private HTMLInputElement $element_ObjetoContratar;
/*      */   private HTMLTextAreaElement $element_ObjetoContratarText;
/*      */   private HTMLInputElement $element_Plazo;
/*      */   private HTMLInputElement $element_PlazoD;
/*      */   private HTMLInputElement $element_PlazoFecha;
/*      */   private HTMLInputElement $element_PlazoMeses;
/*      */   private HTMLInputElement $element_PlazoT;
/*      */   private HTMLInputElement $element_Polizas;
/*      */   private HTMLTextAreaElement $element_PolizasText;
/*      */   private HTMLInputElement $element_PorcentajeAnticipo;
/*      */   private HTMLInputElement $element_PorcentajeTarifa;
/*      */   private HTMLInputElement $element_ProductosAEntregar;
/*      */   private HTMLTextAreaElement $element_ProductosEntregarText;
/*      */   private HTMLScriptElement $element_ScriptCarga;
/*      */   private HTMLInputElement $element_SoporteEconomico;
/*      */   private HTMLTextAreaElement $element_SoporteEconomicoText;
/*      */   private HTMLInputElement $element_SoporteTecnicoEconomico;
/*      */   private HTMLInputElement $element_SoporteTecnicoEconomicoText;
/*      */   private HTMLTableElement $element_TImputacion;
/*      */   private HTMLTableElement $element_TPoliza;
/*      */   private HTMLTableElement $element_TSrvc;
/*      */   private HTMLTableElement $element_TablaActividadesDesarrollarText;
/*      */   private HTMLTableElement $element_TablaAlternativasEjecucionText;
/*      */   private HTMLTableElement $element_TablaDefinicionTecnicaText;
/*      */   private HTMLTableElement $element_TablaDescripcionRiesgosText;
/*      */   private HTMLTableElement $element_TablaEspecificacionesTecnicasText;
/*      */   private HTMLTableElement $element_TablaPolizasText;
/*      */   private HTMLTableElement $element_TablaProductosEntregarText;
/*      */   private HTMLTableRowElement $element_TarifaPropiaDescripcion;
/*      */   private HTMLSelectElement $element_TarifaSalud;
/*      */   private HTMLInputElement $element_TarifaSaludDescripcion;
/*      */   private HTMLTextAreaElement $element_TarifaSaludDescripcionTxt;
/*      */   private HTMLSelectElement $element_TieneAnticipo;
/*      */   private HTMLInputElement $element_Tipo;
/*      */   private HTMLSelectElement $element_TipoContrato;
/*      */   private HTMLInputElement $element_TipoEstudio;
/*      */   private HTMLInputElement $element_TipoPlazoTA;
/*      */   private HTMLInputElement $element_TipoPlazoTP;
/*      */   private HTMLDivElement $element_TrConsulta;
/*      */   private HTMLDivElement $element_TrDocumentos;
/*      */   private HTMLDivElement $element_TrResultados;
/*      */   private HTMLInputElement $element_UsuarioInsercion;
/*      */   private HTMLInputElement $element_UsuarioModificacion;
/*      */   private HTMLInputElement $element_Valor;
/*      */   private HTMLInputElement $element__operacion;
/*      */   private HTMLInputElement $element__operacionCarga;
/*      */   public static final String CLASS_BOT = "BOT";
/*      */   public static final String CLASS_IND = "IND";
/*      */   public static final String CLASS_INP = "INP";
/*      */   public static final String CLASS_INPD = "INPD";
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
/*      */   public static final String NAME_TImputacion = "TImputacion";
/*      */   public static final String NAME_TPoliza = "TPoliza";
/*      */   public static final String NAME_TSrvc = "TSrvc";
/*      */   public static final String NAME__operacion = "_operacion";
/*      */   public static final String NAME__operacionCarga = "_operacionCarga";
/*      */   public static final String NAME_actividadesADesarrollar = "actividadesADesarrollar";
/*      */   public static final String NAME_actividadesDesarrollarText = "actividadesDesarrollarText";
/*      */   public static final String NAME_alternativasEjecucion = "alternativasEjecucion";
/*      */   public static final String NAME_alternativasEjecucionText = "alternativasEjecucionText";
/*      */   public static final String NAME_botonAgregarActividadesDesarrollar = "botonAgregarActividadesDesarrollar";
/*      */   public static final String NAME_botonAgregarAlternativasEjecucion = "botonAgregarAlternativasEjecucion";
/*      */   public static final String NAME_botonAgregarCuadroAct = "botonAgregarCuadroAct";
/*      */   public static final String NAME_botonAgregarDefinicionTecnica = "botonAgregarDefinicionTecnica";
/*      */   public static final String NAME_botonAgregarDescripcionRiesgos = "botonAgregarDescripcionRiesgos";
/*      */   public static final String NAME_botonAgregarEspecificacionesTecnicas = "botonAgregarEspecificacionesTecnicas";
/*      */   public static final String NAME_botonAgregarProductoEntregar = "botonAgregarProductoEntregar";
/*      */   public static final String NAME_botonAgregarpolizas = "botonAgregarpolizas";
/*      */   public static final String NAME_claseGasto = "claseGasto";
/*      */   public static final String NAME_cuantos = "cuantos";
/*      */   public static final String NAME_cuantosImp = "cuantosImp";
/*      */   public static final String NAME_definicionTecnica = "definicionTecnica";
/*      */   public static final String NAME_definicionTecnicaText = "definicionTecnicaText";
/*      */   public static final String NAME_dependencia = "dependencia";
/*      */   public static final String NAME_descripcionNecesidad = "descripcionNecesidad";
/*      */   public static final String NAME_descripcionNecesidadText = "descripcionNecesidadText";
/*      */   public static final String NAME_descripcionRiesgos = "descripcionRiesgos";
/*      */   public static final String NAME_descripcionRiesgosText = "descripcionRiesgosText";
/*      */   public static final String NAME_detalle = "detalle";
/*      */   public static final String NAME_especificacionesTecnicas = "especificacionesTecnicas";
/*      */   public static final String NAME_especificacionesTecnicasText = "especificacionesTecnicasText";
/*      */   public static final String NAME_fechaInsercion = "fechaInsercion";
/*      */   public static final String NAME_fechaModificacion = "fechaModificacion";
/*      */   public static final String NAME_fechaPresentacion = "fechaPresentacion";
/*      */   public static final String NAME_fechaPresentacionDesde = "fechaPresentacionDesde";
/*      */   public static final String NAME_fechaPresentacionHasta = "fechaPresentacionHasta";
/*      */   public static final String NAME_formaContrato = "formaContrato";
/*      */   public static final String NAME_formaPago = "formaPago";
/*      */   public static final String NAME_formaPagoText = "formaPagoText";
/*      */   public static final String NAME_idCreacionRegistro = "idCreacionRegistro";
/*      */   public static final String NAME_interventor = "interventor";
/*      */   public static final String NAME_iva = "iva";
/*      */   public static final String NAME_lugarEjecucion = "lugarEjecucion";
/*      */   public static final String NAME_miBotonContrato = "miBotonContrato";
/*      */   public static final String NAME_miBotonE = "miBotonE";
/*      */   public static final String NAME_miBotonG = "miBotonG";
/*      */   public static final String NAME_miBotonM = "miBotonM";
/*      */   public static final String NAME_miForma = "miForma";
/*      */   public static final String NAME_miForma2 = "miForma2";
/*      */   public static final String NAME_miForma3 = "miForma3";
/*      */   public static final String NAME_nombreImputacionPresupuestal = "nombreImputacionPresupuestal";
/*      */   public static final String NAME_numeroEstudio = "numeroEstudio";
/*      */   public static final String NAME_objetoContratar = "objetoContratar";
/*      */   public static final String NAME_objetoContratarText = "objetoContratarText";
/*      */   public static final String NAME_plazo = "plazo";
/*      */   public static final String NAME_plazoD = "plazoD";
/*      */   public static final String NAME_plazoT = "plazoT";
/*      */   public static final String NAME_polizas = "polizas";
/*      */   public static final String NAME_polizasText = "polizasText";
/*      */   public static final String NAME_porcentajeAnticipo = "porcentajeAnticipo";
/*      */   public static final String NAME_porcentajeTarifa = "porcentajeTarifa";
/*      */   public static final String NAME_productosAEntregar = "productosAEntregar";
/*      */   public static final String NAME_productosEntregarText = "productosEntregarText";
/*      */   public static final String NAME_soporteEconomico = "soporteEconomico";
/*      */   public static final String NAME_soporteEconomicoText = "soporteEconomicoText";
/*      */   public static final String NAME_soporteTecnicoEconomico = "soporteTecnicoEconomico";
/*      */   public static final String NAME_soporteTecnicoEconomicoText = "soporteTecnicoEconomicoText";
/*      */   public static final String NAME_tarifaSalud = "tarifaSalud";
/*      */   public static final String NAME_tarifaSaludDescripcion = "tarifaSaludDescripcion";
/*      */   public static final String NAME_tarifaSaludDescripcionTxt = "tarifaSaludDescripcionTxt";
/*      */   public static final String NAME_tieneAnticipo = "tieneAnticipo";
/*      */   public static final String NAME_tipo = "tipo";
/*      */   public static final String NAME_tipoContrato = "tipoContrato";
/*      */   public static final String NAME_tipoEstudio = "tipoEstudio";
/*      */   public static final String NAME_tipoPlazo = "tipoPlazo";
/*      */   public static final String NAME_tipoPlazo2 = "tipoPlazo2";
/*      */   public static final String NAME_txtCont1 = "txtCont1";
/*      */   public static final String NAME_txtCont10 = "txtCont10";
/*      */   public static final String NAME_txtCont11 = "txtCont11";
/*      */   public static final String NAME_txtCont12 = "txtCont12";
/*      */   public static final String NAME_txtCont2 = "txtCont2";
/*      */   public static final String NAME_txtCont3 = "txtCont3";
/*      */   public static final String NAME_txtCont4 = "txtCont4";
/*      */   public static final String NAME_txtCont48 = "txtCont48";
/*      */   public static final String NAME_txtCont5 = "txtCont5";
/*      */   public static final String NAME_txtCont7 = "txtCont7";
/*      */   public static final String NAME_txtCont8 = "txtCont8";
/*      */   public static final String NAME_txtCont9 = "txtCont9";
/*      */   public static final String NAME_usuarioInsercion = "usuarioInsercion";
/*      */   public static final String NAME_usuarioModificacion = "usuarioModificacion";
/*      */   public static final String NAME_valor = "valor";
/*  782 */   public static final Class XMLC_GENERATED_CLASS = ContEstudioPrevioSaludActHTML.class;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/ContEstudioPrevioSaludAct.html";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  793 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final DocumentLoader fDocumentLoader;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ContEstudioPrevioSaludActHTML() {
/*  804 */     this(StandardDocumentLoader.getInstance());
/*      */     
/*  806 */     buildDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  814 */   public ContEstudioPrevioSaludActHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ContEstudioPrevioSaludActHTML(ContEstudioPrevioSaludActHTML src) {
/*  822 */     this.fDocumentLoader = src.getDocumentLoader();
/*      */     
/*  824 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*      */     
/*  826 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ContEstudioPrevioSaludActHTML(DocumentLoader loader, boolean buildDOM) {
/*  837 */     this.fDocumentLoader = loader;
/*      */     
/*  839 */     if (buildDOM)
/*      */     {
/*  841 */       buildDocument();
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
/*  853 */   public ContEstudioPrevioSaludActHTML(DocumentLoader loader) { this(loader, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void buildDocument() {
/*  861 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*      */     
/*  863 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node cloneNode(boolean deep) {
/*  871 */     cloneDeepCheck(deep);
/*      */     
/*  873 */     return new ContEstudioPrevioSaludActHTML(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  881 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  889 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  898 */   public HTMLInputElement getElementActividadesADesarrollar() { return this.$element_ActividadesADesarrollar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  907 */   public HTMLTextAreaElement getElementActividadesDesarrollarText() { return this.$element_ActividadesDesarrollarText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  916 */   public HTMLInputElement getElementAddActividades() { return this.$element_AddActividades; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  925 */   public HTMLInputElement getElementAddServicios() { return this.$element_AddServicios; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  934 */   public HTMLInputElement getElementAddTarifa() { return this.$element_AddTarifa; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  943 */   public HTMLInputElement getElementAlternativasEjecucion() { return this.$element_AlternativasEjecucion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  952 */   public HTMLTextAreaElement getElementAlternativasEjecucionText() { return this.$element_AlternativasEjecucionText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  961 */   public HTMLInputElement getElementBffechaPresentacionDesde() { return this.$element_BffechaPresentacionDesde; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  970 */   public HTMLInputElement getElementBffechaPresentacionHasta() { return this.$element_BffechaPresentacionHasta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  979 */   public HTMLInputElement getElementBotonAgregarActividadesDesarrollar() { return this.$element_BotonAgregarActividadesDesarrollar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  988 */   public HTMLInputElement getElementBotonAgregarAlternativasEjecucion() { return this.$element_BotonAgregarAlternativasEjecucion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  997 */   public HTMLInputElement getElementBotonAgregarCuadroAct() { return this.$element_BotonAgregarCuadroAct; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1006 */   public HTMLInputElement getElementBotonAgregarDefinicionTecnica() { return this.$element_BotonAgregarDefinicionTecnica; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1015 */   public HTMLInputElement getElementBotonAgregarDescripcionRiesgos() { return this.$element_BotonAgregarDescripcionRiesgos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1024 */   public HTMLInputElement getElementBotonAgregarEspecificacionesTecnicas() { return this.$element_BotonAgregarEspecificacionesTecnicas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1033 */   public HTMLInputElement getElementBotonAgregarPolizas() { return this.$element_BotonAgregarPolizas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1042 */   public HTMLInputElement getElementBotonAgregarProductoEntregar() { return this.$element_BotonAgregarProductoEntregar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1051 */   public HTMLInputElement getElementBotonFechaPlazo() { return this.$element_BotonFechaPlazo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1060 */   public HTMLInputElement getElementBotonFechaPresentacion() { return this.$element_BotonFechaPresentacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1069 */   public HTMLInputElement getElementBtnFirmas() { return this.$element_BtnFirmas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1078 */   public HTMLSelectElement getElementClaseGasto() { return this.$element_ClaseGasto; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1087 */   public HTMLInputElement getElementCopiar() { return this.$element_Copiar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1096 */   public HTMLInputElement getElementCuantos() { return this.$element_Cuantos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1105 */   public HTMLInputElement getElementCuantosImp() { return this.$element_CuantosImp; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1114 */   public HTMLInputElement getElementDefinicionTecnica() { return this.$element_DefinicionTecnica; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1123 */   public HTMLTextAreaElement getElementDefinicionTecnicaText() { return this.$element_DefinicionTecnicaText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1132 */   public HTMLSelectElement getElementDependencia() { return this.$element_Dependencia; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1141 */   public HTMLInputElement getElementDescripcionNecesidad() { return this.$element_DescripcionNecesidad; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1150 */   public HTMLTextAreaElement getElementDescripcionNecesidadText() { return this.$element_DescripcionNecesidadText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1159 */   public HTMLInputElement getElementDescripcionRiesgos() { return this.$element_DescripcionRiesgos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1168 */   public HTMLTextAreaElement getElementDescripcionRiesgosText() { return this.$element_DescripcionRiesgosText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1177 */   public HTMLTableSectionElement getElementDetalle() { return this.$element_Detalle; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1186 */   public HTMLTableElement getElementDetalleDocumentos() { return this.$element_DetalleDocumentos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1195 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1204 */   public HTMLInputElement getElementEliminarRegistro() { return this.$element_EliminarRegistro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1213 */   public HTMLInputElement getElementEspecificacionesTecnicas() { return this.$element_EspecificacionesTecnicas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1222 */   public HTMLTextAreaElement getElementEspecificacionesTecnicasText() { return this.$element_EspecificacionesTecnicasText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1231 */   public HTMLSelectElement getElementFdependencia() { return this.$element_Fdependencia; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1240 */   public HTMLInputElement getElementFechaInsercion() { return this.$element_FechaInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1249 */   public HTMLInputElement getElementFechaModificacion() { return this.$element_FechaModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1258 */   public HTMLInputElement getElementFechaPresentacion() { return this.$element_FechaPresentacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1267 */   public HTMLInputElement getElementFfechaPresentacionDesde() { return this.$element_FfechaPresentacionDesde; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1276 */   public HTMLInputElement getElementFfechaPresentacionHasta() { return this.$element_FfechaPresentacionHasta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1285 */   public HTMLSelectElement getElementFformaContrato() { return this.$element_FformaContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1294 */   public HTMLInputElement getElementFnumeroEstudio() { return this.$element_FnumeroEstudio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1303 */   public HTMLSelectElement getElementFormaContrato() { return this.$element_FormaContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1312 */   public HTMLSelectElement getElementFormaPago() { return this.$element_FormaPago; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1321 */   public HTMLTextAreaElement getElementFormaPagoText() { return this.$element_FormaPagoText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1330 */   public HTMLSelectElement getElementFtipoContrato() { return this.$element_FtipoContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1339 */   public HTMLSelectElement getElementFtipoEstudio() { return this.$element_FtipoEstudio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1348 */   public HTMLDivElement getElementIdCreacionRegistro() { return this.$element_IdCreacionRegistro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1357 */   public HTMLInputElement getElementInterventor() { return this.$element_Interventor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1366 */   public HTMLSelectElement getElementIva() { return this.$element_Iva; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1375 */   public HTMLInputElement getElementLugarEjecucion() { return this.$element_LugarEjecucion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1384 */   public HTMLInputElement getElementMiBotonContrato() { return this.$element_MiBotonContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1393 */   public HTMLInputElement getElementMiBotonG() { return this.$element_MiBotonG; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1402 */   public HTMLInputElement getElementMiBotonM() { return this.$element_MiBotonM; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1411 */   public HTMLFormElement getElementMiForma() { return this.$element_MiForma; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1420 */   public HTMLScriptElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1429 */   public HTMLInputElement getElementNombreImputacionPresupuestal() { return this.$element_NombreImputacionPresupuestal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1438 */   public HTMLElement getElementNroRegistros() { return this.$element_NroRegistros; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1447 */   public HTMLInputElement getElementNumeroEstudio() { return this.$element_NumeroEstudio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1456 */   public HTMLInputElement getElementObjetoContratar() { return this.$element_ObjetoContratar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1465 */   public HTMLTextAreaElement getElementObjetoContratarText() { return this.$element_ObjetoContratarText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1474 */   public HTMLInputElement getElementPlazo() { return this.$element_Plazo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1483 */   public HTMLInputElement getElementPlazoD() { return this.$element_PlazoD; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1492 */   public HTMLInputElement getElementPlazoFecha() { return this.$element_PlazoFecha; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1501 */   public HTMLInputElement getElementPlazoMeses() { return this.$element_PlazoMeses; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1510 */   public HTMLInputElement getElementPlazoT() { return this.$element_PlazoT; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1519 */   public HTMLInputElement getElementPolizas() { return this.$element_Polizas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1528 */   public HTMLTextAreaElement getElementPolizasText() { return this.$element_PolizasText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1537 */   public HTMLInputElement getElementPorcentajeAnticipo() { return this.$element_PorcentajeAnticipo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1546 */   public HTMLInputElement getElementPorcentajeTarifa() { return this.$element_PorcentajeTarifa; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1555 */   public HTMLInputElement getElementProductosAEntregar() { return this.$element_ProductosAEntregar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1564 */   public HTMLTextAreaElement getElementProductosEntregarText() { return this.$element_ProductosEntregarText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1573 */   public HTMLScriptElement getElementScriptCarga() { return this.$element_ScriptCarga; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1582 */   public HTMLInputElement getElementSoporteEconomico() { return this.$element_SoporteEconomico; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1591 */   public HTMLTextAreaElement getElementSoporteEconomicoText() { return this.$element_SoporteEconomicoText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1600 */   public HTMLInputElement getElementSoporteTecnicoEconomico() { return this.$element_SoporteTecnicoEconomico; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1609 */   public HTMLInputElement getElementSoporteTecnicoEconomicoText() { return this.$element_SoporteTecnicoEconomicoText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1618 */   public HTMLTableElement getElementTImputacion() { return this.$element_TImputacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1627 */   public HTMLTableElement getElementTPoliza() { return this.$element_TPoliza; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1636 */   public HTMLTableElement getElementTSrvc() { return this.$element_TSrvc; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1645 */   public HTMLTableElement getElementTablaActividadesDesarrollarText() { return this.$element_TablaActividadesDesarrollarText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1654 */   public HTMLTableElement getElementTablaAlternativasEjecucionText() { return this.$element_TablaAlternativasEjecucionText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1663 */   public HTMLTableElement getElementTablaDefinicionTecnicaText() { return this.$element_TablaDefinicionTecnicaText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1672 */   public HTMLTableElement getElementTablaDescripcionRiesgosText() { return this.$element_TablaDescripcionRiesgosText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1681 */   public HTMLTableElement getElementTablaEspecificacionesTecnicasText() { return this.$element_TablaEspecificacionesTecnicasText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1690 */   public HTMLTableElement getElementTablaPolizasText() { return this.$element_TablaPolizasText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1699 */   public HTMLTableElement getElementTablaProductosEntregarText() { return this.$element_TablaProductosEntregarText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1708 */   public HTMLTableRowElement getElementTarifaPropiaDescripcion() { return this.$element_TarifaPropiaDescripcion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1717 */   public HTMLSelectElement getElementTarifaSalud() { return this.$element_TarifaSalud; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1726 */   public HTMLInputElement getElementTarifaSaludDescripcion() { return this.$element_TarifaSaludDescripcion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1735 */   public HTMLTextAreaElement getElementTarifaSaludDescripcionTxt() { return this.$element_TarifaSaludDescripcionTxt; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1744 */   public HTMLSelectElement getElementTieneAnticipo() { return this.$element_TieneAnticipo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1753 */   public HTMLInputElement getElementTipo() { return this.$element_Tipo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1762 */   public HTMLSelectElement getElementTipoContrato() { return this.$element_TipoContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1771 */   public HTMLInputElement getElementTipoEstudio() { return this.$element_TipoEstudio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1780 */   public HTMLInputElement getElementTipoPlazoTA() { return this.$element_TipoPlazoTA; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1789 */   public HTMLInputElement getElementTipoPlazoTP() { return this.$element_TipoPlazoTP; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1798 */   public HTMLDivElement getElementTrConsulta() { return this.$element_TrConsulta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1807 */   public HTMLDivElement getElementTrDocumentos() { return this.$element_TrDocumentos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1816 */   public HTMLDivElement getElementTrResultados() { return this.$element_TrResultados; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1825 */   public HTMLInputElement getElementUsuarioInsercion() { return this.$element_UsuarioInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1834 */   public HTMLInputElement getElementUsuarioModificacion() { return this.$element_UsuarioModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1843 */   public HTMLInputElement getElementValor() { return this.$element_Valor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1852 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1861 */   public HTMLInputElement getElement_operacionCarga() { return this.$element__operacionCarga; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1870 */   public void setTextActividadesDesarrollarText(String text) { doSetText(this.$element_ActividadesDesarrollarText, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1879 */   public void setTextAlternativasEjecucionText(String text) { doSetText(this.$element_AlternativasEjecucionText, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1888 */   public void setTextDefinicionTecnicaText(String text) { doSetText(this.$element_DefinicionTecnicaText, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1897 */   public void setTextDescripcionNecesidadText(String text) { doSetText(this.$element_DescripcionNecesidadText, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1906 */   public void setTextDescripcionRiesgosText(String text) { doSetText(this.$element_DescripcionRiesgosText, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1915 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1924 */   public void setTextEspecificacionesTecnicasText(String text) { doSetText(this.$element_EspecificacionesTecnicasText, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1933 */   public void setTextFormaPagoText(String text) { doSetText(this.$element_FormaPagoText, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1942 */   public void setTextIdCreacionRegistro(String text) { doSetText(this.$element_IdCreacionRegistro, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1951 */   public void setTextMiForma(String text) { doSetText(this.$element_MiForma, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1960 */   public void setTextMostrarMenu(String text) { doSetText(this.$element_MostrarMenu, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1969 */   public void setTextNroRegistros(String text) { doSetText(this.$element_NroRegistros, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1978 */   public void setTextObjetoContratarText(String text) { doSetText(this.$element_ObjetoContratarText, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1987 */   public void setTextPolizasText(String text) { doSetText(this.$element_PolizasText, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1996 */   public void setTextProductosEntregarText(String text) { doSetText(this.$element_ProductosEntregarText, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2005 */   public void setTextScriptCarga(String text) { doSetText(this.$element_ScriptCarga, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2014 */   public void setTextSoporteEconomicoText(String text) { doSetText(this.$element_SoporteEconomicoText, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2023 */   public void setTextTarifaSaludDescripcionTxt(String text) { doSetText(this.$element_TarifaSaludDescripcionTxt, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2032 */   public void setTextTrConsulta(String text) { doSetText(this.$element_TrConsulta, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2041 */   public void setTextTrDocumentos(String text) { doSetText(this.$element_TrDocumentos, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2050 */   public void setTextTrResultados(String text) { doSetText(this.$element_TrResultados, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void syncWithDocument(Node node) {
/* 2059 */     if (node.getNodeType() != 9)
/*      */     {
/* 2061 */       throw new XMLCRuntimeException("Node must be a document node");
/*      */     }
/*      */ 
/*      */     
/* 2065 */     Document doc = (Document)node;
/*      */ 
/*      */     
/*      */     try {
/* 2069 */       Field[] fs = getClass().getDeclaredFields();
/*      */       
/* 2071 */       int substStart = "$element_".length();
/*      */       
/* 2073 */       for (int i = 0; i < fs.length; i++) {
/*      */         
/* 2075 */         Field f = fs[i];
/*      */         
/* 2077 */         if (f.getName().startsWith("$element_")) {
/*      */           
/* 2079 */           String id = f.getName().substring(substStart);
/*      */           
/* 2081 */           Node idNode = doc.getElementById(id);
/*      */           
/* 2083 */           if (idNode == null) {
/*      */             
/* 2085 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*      */             
/* 2087 */             idNode = doc.getElementById(id);
/*      */           } 
/*      */ 
/*      */           
/* 2091 */           if (idNode != null) f.set(this, idNode);
/*      */         
/*      */         } 
/*      */       } 
/* 2095 */     } catch (Exception e) {
/*      */       
/* 2097 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ContEstudioPrevioSaludActHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */