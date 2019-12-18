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
/*      */ import org.w3c.dom.html.HTMLInputElement;
/*      */ import org.w3c.dom.html.HTMLScriptElement;
/*      */ import org.w3c.dom.html.HTMLSelectElement;
/*      */ import org.w3c.dom.html.HTMLTableElement;
/*      */ import org.w3c.dom.html.HTMLTableRowElement;
/*      */ import org.w3c.dom.html.HTMLTextAreaElement;
/*      */ import sinco.presentation.ContEstudioPrevioAdminActHTML;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ContEstudioPrevioAdminActHTML
/*      */   extends HTMLObjectImpl
/*      */   implements XMLObject, HTMLObject
/*      */ {
/*      */   private HTMLSelectElement $element_ActividadEspecifica;
/*      */   private HTMLInputElement $element_ActividadesADesarrollar;
/*      */   private HTMLTextAreaElement $element_ActividadesDesarrollarText;
/*      */   private HTMLInputElement $element_AgregarCdp;
/*      */   private HTMLInputElement $element_AlternativasEjecucion;
/*      */   private HTMLInputElement $element_BfechaDesde;
/*      */   private HTMLInputElement $element_BfechaHasta;
/*      */   private HTMLInputElement $element_BotonAgregarActividadesDesarrollar;
/*      */   private HTMLInputElement $element_BotonAgregarDefinicionTecnica;
/*      */   private HTMLInputElement $element_BotonAgregarDescripcionRiesgos;
/*      */   private HTMLInputElement $element_BotonAgregarEspecificacionesTecnicas;
/*      */   private HTMLInputElement $element_BotonAgregarPolizas;
/*      */   private HTMLInputElement $element_BotonAgregarProductoEntregar;
/*      */   private HTMLInputElement $element_BotonFechaPresentacion;
/*      */   private HTMLInputElement $element_BtnFirmas;
/*      */   private HTMLSelectElement $element_ClaseGasto;
/*      */   private HTMLInputElement $element_Copiar;
/*      */   private HTMLInputElement $element_Cuantos;
/*      */   private HTMLInputElement $element_DefinicionTecnica;
/*      */   private HTMLTextAreaElement $element_DefinicionTecnicaText;
/*      */   private HTMLSelectElement $element_Dependencia;
/*      */   private HTMLInputElement $element_DescripcionNecesidad;
/*      */   private HTMLTextAreaElement $element_DescripcionNecesidadText;
/*      */   private HTMLInputElement $element_DescripcionRiesgos;
/*      */   private HTMLTextAreaElement $element_DescripcionRiesgosText;
/*      */   private HTMLTableElement $element_DetalleDocumentos;
/*      */   private HTMLScriptElement $element_ElMenu;
/*      */   private HTMLInputElement $element_EliminarRegistro;
/*      */   private HTMLInputElement $element_EspecificacionesTecnicas;
/*      */   private HTMLTextAreaElement $element_EspecificacionesTecnicasText;
/*      */   private HTMLInputElement $element_FechaDesde;
/*      */   private HTMLInputElement $element_FechaEstudio;
/*      */   private HTMLInputElement $element_FechaHasta;
/*      */   private HTMLInputElement $element_FechaInsercion;
/*      */   private HTMLInputElement $element_FechaModificacion;
/*      */   private HTMLInputElement $element_FechaPresentacion;
/*      */   private HTMLSelectElement $element_FormaContrato;
/*      */   private HTMLSelectElement $element_FormaPago;
/*      */   private HTMLTextAreaElement $element_FormaPagoText;
/*      */   private HTMLTableRowElement $element_IdCreacionRegistro;
/*      */   private HTMLSelectElement $element_Interventor;
/*      */   private HTMLSelectElement $element_Iva;
/*      */   private HTMLInputElement $element_JustificacionValor;
/*      */   private HTMLInputElement $element_LugarEjecucion;
/*      */   private HTMLInputElement $element_MiBotonContrato;
/*      */   private HTMLInputElement $element_MiBotonG;
/*      */   private HTMLInputElement $element_MiBotonM;
/*      */   private HTMLSelectElement $element_NombreImputacionPresupuestal;
/*      */   private HTMLInputElement $element_NumeroEstudio;
/*      */   private HTMLInputElement $element_ObjetoContratar;
/*      */   private HTMLTextAreaElement $element_ObjetoContratarText;
/*      */   private HTMLInputElement $element_Plazo;
/*      */   private HTMLInputElement $element_PlazoD;
/*      */   private HTMLInputElement $element_Polizas;
/*      */   private HTMLTextAreaElement $element_PolizasText;
/*      */   private HTMLInputElement $element_ProductosAEntregar;
/*      */   private HTMLTextAreaElement $element_ProductosEntregarText;
/*      */   private HTMLScriptElement $element_ScriptCarga;
/*      */   private HTMLInputElement $element_SoporteTecnicoEconomico;
/*      */   private HTMLInputElement $element_SoporteTecnicoEconomicoText;
/*      */   private HTMLTableElement $element_TPoliza;
/*      */   private HTMLTableElement $element_TablaActividadesDesarrollarText;
/*      */   private HTMLTableElement $element_TablaDefinicionTecnicaText;
/*      */   private HTMLTableElement $element_TablaDescripcionRiesgosText;
/*      */   private HTMLTableElement $element_TablaEspecificacionesTecnicasText;
/*      */   private HTMLTableElement $element_TablaPolizasText;
/*      */   private HTMLTableElement $element_TablaProductosEntregarText;
/*      */   private HTMLSelectElement $element_TipoContrato;
/*      */   private HTMLInputElement $element_TipoEstudio;
/*      */   private HTMLInputElement $element_TipoPlazoTA;
/*      */   private HTMLInputElement $element_TipoPlazoTP;
/*      */   private HTMLInputElement $element_TipoPlazoTRP;
/*      */   private HTMLTableRowElement $element_TrDocumentos;
/*      */   private HTMLInputElement $element_UsuarioInsercion;
/*      */   private HTMLInputElement $element_UsuarioModificacion;
/*      */   private HTMLInputElement $element_Valor;
/*      */   private HTMLInputElement $element_ValorLetra;
/*      */   private HTMLInputElement $element__operacion;
/*      */   private HTMLInputElement $element__operacionCarga;
/*      */   public static final String CLASS_BOT = "BOT";
/*      */   public static final String CLASS_IND = "IND";
/*      */   public static final String CLASS_INP = "INP";
/*      */   public static final String CLASS_INPD = "INPD";
/*      */   public static final String CLASS_bot = "bot";
/*      */   public static final String CLASS_btnCalendario = "btnCalendario";
/*      */   public static final String CLASS_ca = "ca";
/*      */   public static final String CLASS_cb = "cb";
/*      */   public static final String CLASS_cf1 = "cf1";
/*      */   public static final String CLASS_cf2 = "cf2";
/*      */   public static final String CLASS_container = "container";
/*      */   public static final String CLASS_cp = "cp";
/*      */   public static final String CLASS_dat = "dat";
/*      */   public static final String CLASS_dat2 = "dat2";
/*      */   public static final String CLASS_inp = "inp";
/*      */   public static final String CLASS_tabf = "tabf";
/*      */   public static final String CLASS_tit = "tit";
/*      */   public static final String NAME_TPoliza = "TPoliza";
/*      */   public static final String NAME__operacion = "_operacion";
/*      */   public static final String NAME__operacionCarga = "_operacionCarga";
/*      */   public static final String NAME_actividadEspecifica = "actividadEspecifica";
/*      */   public static final String NAME_actividadesADesarrollar = "actividadesADesarrollar";
/*      */   public static final String NAME_actividadesDesarrollarText = "actividadesDesarrollarText";
/*      */   public static final String NAME_alternativasEjecucion = "alternativasEjecucion";
/*      */   public static final String NAME_botonAgregarActividadesDesarrollar = "botonAgregarActividadesDesarrollar";
/*      */   public static final String NAME_botonAgregarDefinicionTecnica = "botonAgregarDefinicionTecnica";
/*      */   public static final String NAME_botonAgregarDescripcionRiesgos = "botonAgregarDescripcionRiesgos";
/*      */   public static final String NAME_botonAgregarEspecificacionesTecnicas = "botonAgregarEspecificacionesTecnicas";
/*      */   public static final String NAME_botonAgregarProductoEntregar = "botonAgregarProductoEntregar";
/*      */   public static final String NAME_botonAgregarpolizas = "botonAgregarpolizas";
/*      */   public static final String NAME_claseGasto = "claseGasto";
/*      */   public static final String NAME_cuantos = "cuantos";
/*      */   public static final String NAME_definicionTecnica = "definicionTecnica";
/*      */   public static final String NAME_definicionTecnicaText = "definicionTecnicaText";
/*      */   public static final String NAME_dependencia = "dependencia";
/*      */   public static final String NAME_descripcionNecesidad = "descripcionNecesidad";
/*      */   public static final String NAME_descripcionNecesidadText = "descripcionNecesidadText";
/*      */   public static final String NAME_descripcionRiesgos = "descripcionRiesgos";
/*      */   public static final String NAME_descripcionRiesgosText = "descripcionRiesgosText";
/*      */   public static final String NAME_especificacionesTecnicas = "especificacionesTecnicas";
/*      */   public static final String NAME_especificacionesTecnicasText = "especificacionesTecnicasText";
/*      */   public static final String NAME_fechaDesde = "fechaDesde";
/*      */   public static final String NAME_fechaHasta = "fechaHasta";
/*      */   public static final String NAME_fechaInsercion = "fechaInsercion";
/*      */   public static final String NAME_fechaModificacion = "fechaModificacion";
/*      */   public static final String NAME_fechaPresentacion = "fechaPresentacion";
/*      */   public static final String NAME_formaContrato = "formaContrato";
/*      */   public static final String NAME_formaPago = "formaPago";
/*      */   public static final String NAME_formaPagoText = "formaPagoText";
/*      */   public static final String NAME_interventor = "interventor";
/*      */   public static final String NAME_iva = "iva";
/*      */   public static final String NAME_justificacionValor = "justificacionValor";
/*      */   public static final String NAME_lugarEjecucion = "lugarEjecucion";
/*      */   public static final String NAME_miBotonContrato = "miBotonContrato";
/*      */   public static final String NAME_miBotonE = "miBotonE";
/*      */   public static final String NAME_miBotonG = "miBotonG";
/*      */   public static final String NAME_miBotonM = "miBotonM";
/*      */   public static final String NAME_miForma = "miForma";
/*      */   public static final String NAME_miForma2 = "miForma2";
/*      */   public static final String NAME_nombreImputacionPresupuestal = "nombreImputacionPresupuestal";
/*      */   public static final String NAME_numeroEstudio = "numeroEstudio";
/*      */   public static final String NAME_objetoContratar = "objetoContratar";
/*      */   public static final String NAME_objetoContratarText = "objetoContratarText";
/*      */   public static final String NAME_plazo = "plazo";
/*      */   public static final String NAME_plazoD = "plazoD";
/*      */   public static final String NAME_polizas = "polizas";
/*      */   public static final String NAME_polizasText = "polizasText";
/*      */   public static final String NAME_productosAEntregar = "productosAEntregar";
/*      */   public static final String NAME_productosEntregarText = "productosEntregarText";
/*      */   public static final String NAME_soporteTecnicoEconomico = "soporteTecnicoEconomico";
/*      */   public static final String NAME_soporteTecnicoEconomicoText = "soporteTecnicoEconomicoText";
/*      */   public static final String NAME_tipoContrato = "tipoContrato";
/*      */   public static final String NAME_tipoEstudio = "tipoEstudio";
/*      */   public static final String NAME_tipoPlazo = "tipoPlazo";
/*      */   public static final String NAME_txtCont1 = "txtCont1";
/*      */   public static final String NAME_txtCont10 = "txtCont10";
/*      */   public static final String NAME_txtCont11 = "txtCont11";
/*      */   public static final String NAME_txtCont2 = "txtCont2";
/*      */   public static final String NAME_txtCont3 = "txtCont3";
/*      */   public static final String NAME_txtCont4 = "txtCont4";
/*      */   public static final String NAME_txtCont48 = "txtCont48";
/*      */   public static final String NAME_txtCont5 = "txtCont5";
/*      */   public static final String NAME_txtCont7 = "txtCont7";
/*      */   public static final String NAME_usuarioInsercion = "usuarioInsercion";
/*      */   public static final String NAME_usuarioModificacion = "usuarioModificacion";
/*      */   public static final String NAME_valor = "valor";
/*      */   public static final String NAME_valorLetra = "valorLetra";
/*  614 */   public static final Class XMLC_GENERATED_CLASS = ContEstudioPrevioAdminActHTML.class;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/ContEstudioPrevioAdminAct.html";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  625 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final DocumentLoader fDocumentLoader;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ContEstudioPrevioAdminActHTML() {
/*  636 */     this(StandardDocumentLoader.getInstance());
/*      */     
/*  638 */     buildDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  646 */   public ContEstudioPrevioAdminActHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ContEstudioPrevioAdminActHTML(ContEstudioPrevioAdminActHTML src) {
/*  654 */     this.fDocumentLoader = src.getDocumentLoader();
/*      */     
/*  656 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*      */     
/*  658 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ContEstudioPrevioAdminActHTML(DocumentLoader loader, boolean buildDOM) {
/*  669 */     this.fDocumentLoader = loader;
/*      */     
/*  671 */     if (buildDOM)
/*      */     {
/*  673 */       buildDocument();
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
/*  685 */   public ContEstudioPrevioAdminActHTML(DocumentLoader loader) { this(loader, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void buildDocument() {
/*  693 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*      */     
/*  695 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node cloneNode(boolean deep) {
/*  703 */     cloneDeepCheck(deep);
/*      */     
/*  705 */     return new ContEstudioPrevioAdminActHTML(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  713 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  721 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  730 */   public HTMLSelectElement getElementActividadEspecifica() { return this.$element_ActividadEspecifica; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  739 */   public HTMLInputElement getElementActividadesADesarrollar() { return this.$element_ActividadesADesarrollar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  748 */   public HTMLTextAreaElement getElementActividadesDesarrollarText() { return this.$element_ActividadesDesarrollarText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  757 */   public HTMLInputElement getElementAgregarCdp() { return this.$element_AgregarCdp; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  766 */   public HTMLInputElement getElementAlternativasEjecucion() { return this.$element_AlternativasEjecucion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  775 */   public HTMLInputElement getElementBfechaDesde() { return this.$element_BfechaDesde; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  784 */   public HTMLInputElement getElementBfechaHasta() { return this.$element_BfechaHasta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  793 */   public HTMLInputElement getElementBotonAgregarActividadesDesarrollar() { return this.$element_BotonAgregarActividadesDesarrollar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  802 */   public HTMLInputElement getElementBotonAgregarDefinicionTecnica() { return this.$element_BotonAgregarDefinicionTecnica; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  811 */   public HTMLInputElement getElementBotonAgregarDescripcionRiesgos() { return this.$element_BotonAgregarDescripcionRiesgos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  820 */   public HTMLInputElement getElementBotonAgregarEspecificacionesTecnicas() { return this.$element_BotonAgregarEspecificacionesTecnicas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  829 */   public HTMLInputElement getElementBotonAgregarPolizas() { return this.$element_BotonAgregarPolizas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  838 */   public HTMLInputElement getElementBotonAgregarProductoEntregar() { return this.$element_BotonAgregarProductoEntregar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  847 */   public HTMLInputElement getElementBotonFechaPresentacion() { return this.$element_BotonFechaPresentacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  856 */   public HTMLInputElement getElementBtnFirmas() { return this.$element_BtnFirmas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  865 */   public HTMLSelectElement getElementClaseGasto() { return this.$element_ClaseGasto; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  874 */   public HTMLInputElement getElementCopiar() { return this.$element_Copiar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  883 */   public HTMLInputElement getElementCuantos() { return this.$element_Cuantos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  892 */   public HTMLInputElement getElementDefinicionTecnica() { return this.$element_DefinicionTecnica; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  901 */   public HTMLTextAreaElement getElementDefinicionTecnicaText() { return this.$element_DefinicionTecnicaText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  910 */   public HTMLSelectElement getElementDependencia() { return this.$element_Dependencia; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  919 */   public HTMLInputElement getElementDescripcionNecesidad() { return this.$element_DescripcionNecesidad; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  928 */   public HTMLTextAreaElement getElementDescripcionNecesidadText() { return this.$element_DescripcionNecesidadText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  937 */   public HTMLInputElement getElementDescripcionRiesgos() { return this.$element_DescripcionRiesgos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  946 */   public HTMLTextAreaElement getElementDescripcionRiesgosText() { return this.$element_DescripcionRiesgosText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  955 */   public HTMLTableElement getElementDetalleDocumentos() { return this.$element_DetalleDocumentos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  964 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  973 */   public HTMLInputElement getElementEliminarRegistro() { return this.$element_EliminarRegistro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  982 */   public HTMLInputElement getElementEspecificacionesTecnicas() { return this.$element_EspecificacionesTecnicas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  991 */   public HTMLTextAreaElement getElementEspecificacionesTecnicasText() { return this.$element_EspecificacionesTecnicasText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1000 */   public HTMLInputElement getElementFechaDesde() { return this.$element_FechaDesde; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1009 */   public HTMLInputElement getElementFechaEstudio() { return this.$element_FechaEstudio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1018 */   public HTMLInputElement getElementFechaHasta() { return this.$element_FechaHasta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1027 */   public HTMLInputElement getElementFechaInsercion() { return this.$element_FechaInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1036 */   public HTMLInputElement getElementFechaModificacion() { return this.$element_FechaModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1045 */   public HTMLInputElement getElementFechaPresentacion() { return this.$element_FechaPresentacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1054 */   public HTMLSelectElement getElementFormaContrato() { return this.$element_FormaContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1063 */   public HTMLSelectElement getElementFormaPago() { return this.$element_FormaPago; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1072 */   public HTMLTextAreaElement getElementFormaPagoText() { return this.$element_FormaPagoText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1081 */   public HTMLTableRowElement getElementIdCreacionRegistro() { return this.$element_IdCreacionRegistro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1090 */   public HTMLSelectElement getElementInterventor() { return this.$element_Interventor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1099 */   public HTMLSelectElement getElementIva() { return this.$element_Iva; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1108 */   public HTMLInputElement getElementJustificacionValor() { return this.$element_JustificacionValor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1117 */   public HTMLInputElement getElementLugarEjecucion() { return this.$element_LugarEjecucion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1126 */   public HTMLInputElement getElementMiBotonContrato() { return this.$element_MiBotonContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1135 */   public HTMLInputElement getElementMiBotonG() { return this.$element_MiBotonG; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1144 */   public HTMLInputElement getElementMiBotonM() { return this.$element_MiBotonM; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1153 */   public HTMLSelectElement getElementNombreImputacionPresupuestal() { return this.$element_NombreImputacionPresupuestal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1162 */   public HTMLInputElement getElementNumeroEstudio() { return this.$element_NumeroEstudio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1171 */   public HTMLInputElement getElementObjetoContratar() { return this.$element_ObjetoContratar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1180 */   public HTMLTextAreaElement getElementObjetoContratarText() { return this.$element_ObjetoContratarText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1189 */   public HTMLInputElement getElementPlazo() { return this.$element_Plazo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1198 */   public HTMLInputElement getElementPlazoD() { return this.$element_PlazoD; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1207 */   public HTMLInputElement getElementPolizas() { return this.$element_Polizas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1216 */   public HTMLTextAreaElement getElementPolizasText() { return this.$element_PolizasText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1225 */   public HTMLInputElement getElementProductosAEntregar() { return this.$element_ProductosAEntregar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1234 */   public HTMLTextAreaElement getElementProductosEntregarText() { return this.$element_ProductosEntregarText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1243 */   public HTMLScriptElement getElementScriptCarga() { return this.$element_ScriptCarga; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1252 */   public HTMLInputElement getElementSoporteTecnicoEconomico() { return this.$element_SoporteTecnicoEconomico; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1261 */   public HTMLInputElement getElementSoporteTecnicoEconomicoText() { return this.$element_SoporteTecnicoEconomicoText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1270 */   public HTMLTableElement getElementTPoliza() { return this.$element_TPoliza; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1279 */   public HTMLTableElement getElementTablaActividadesDesarrollarText() { return this.$element_TablaActividadesDesarrollarText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1288 */   public HTMLTableElement getElementTablaDefinicionTecnicaText() { return this.$element_TablaDefinicionTecnicaText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1297 */   public HTMLTableElement getElementTablaDescripcionRiesgosText() { return this.$element_TablaDescripcionRiesgosText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1306 */   public HTMLTableElement getElementTablaEspecificacionesTecnicasText() { return this.$element_TablaEspecificacionesTecnicasText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1315 */   public HTMLTableElement getElementTablaPolizasText() { return this.$element_TablaPolizasText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1324 */   public HTMLTableElement getElementTablaProductosEntregarText() { return this.$element_TablaProductosEntregarText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1333 */   public HTMLSelectElement getElementTipoContrato() { return this.$element_TipoContrato; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1342 */   public HTMLInputElement getElementTipoEstudio() { return this.$element_TipoEstudio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1351 */   public HTMLInputElement getElementTipoPlazoTA() { return this.$element_TipoPlazoTA; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1360 */   public HTMLInputElement getElementTipoPlazoTP() { return this.$element_TipoPlazoTP; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1369 */   public HTMLInputElement getElementTipoPlazoTRP() { return this.$element_TipoPlazoTRP; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1378 */   public HTMLTableRowElement getElementTrDocumentos() { return this.$element_TrDocumentos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1387 */   public HTMLInputElement getElementUsuarioInsercion() { return this.$element_UsuarioInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1396 */   public HTMLInputElement getElementUsuarioModificacion() { return this.$element_UsuarioModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1405 */   public HTMLInputElement getElementValor() { return this.$element_Valor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1414 */   public HTMLInputElement getElementValorLetra() { return this.$element_ValorLetra; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1423 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1432 */   public HTMLInputElement getElement_operacionCarga() { return this.$element__operacionCarga; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1441 */   public void setTextActividadesDesarrollarText(String text) { doSetText(this.$element_ActividadesDesarrollarText, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1450 */   public void setTextDefinicionTecnicaText(String text) { doSetText(this.$element_DefinicionTecnicaText, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1459 */   public void setTextDescripcionNecesidadText(String text) { doSetText(this.$element_DescripcionNecesidadText, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1468 */   public void setTextDescripcionRiesgosText(String text) { doSetText(this.$element_DescripcionRiesgosText, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1477 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1486 */   public void setTextEspecificacionesTecnicasText(String text) { doSetText(this.$element_EspecificacionesTecnicasText, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1495 */   public void setTextFormaPagoText(String text) { doSetText(this.$element_FormaPagoText, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1504 */   public void setTextObjetoContratarText(String text) { doSetText(this.$element_ObjetoContratarText, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1513 */   public void setTextPolizasText(String text) { doSetText(this.$element_PolizasText, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1522 */   public void setTextProductosEntregarText(String text) { doSetText(this.$element_ProductosEntregarText, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1531 */   public void setTextScriptCarga(String text) { doSetText(this.$element_ScriptCarga, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void syncWithDocument(Node node) {
/* 1540 */     if (node.getNodeType() != 9)
/*      */     {
/* 1542 */       throw new XMLCRuntimeException("Node must be a document node");
/*      */     }
/*      */ 
/*      */     
/* 1546 */     Document doc = (Document)node;
/*      */ 
/*      */     
/*      */     try {
/* 1550 */       Field[] fs = getClass().getDeclaredFields();
/*      */       
/* 1552 */       int substStart = "$element_".length();
/*      */       
/* 1554 */       for (int i = 0; i < fs.length; i++) {
/*      */         
/* 1556 */         Field f = fs[i];
/*      */         
/* 1558 */         if (f.getName().startsWith("$element_")) {
/*      */           
/* 1560 */           String id = f.getName().substring(substStart);
/*      */           
/* 1562 */           Node idNode = doc.getElementById(id);
/*      */           
/* 1564 */           if (idNode == null) {
/*      */             
/* 1566 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*      */             
/* 1568 */             idNode = doc.getElementById(id);
/*      */           } 
/*      */ 
/*      */           
/* 1572 */           if (idNode != null) f.set(this, idNode);
/*      */         
/*      */         } 
/*      */       } 
/* 1576 */     } catch (Exception e) {
/*      */       
/* 1578 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ContEstudioPrevioAdminActHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */