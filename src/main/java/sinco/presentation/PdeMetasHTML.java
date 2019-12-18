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
/*      */ import org.w3c.dom.html.HTMLTableCellElement;
/*      */ import org.w3c.dom.html.HTMLTableElement;
/*      */ import org.w3c.dom.html.HTMLTableRowElement;
/*      */ import org.w3c.dom.html.HTMLTableSectionElement;
/*      */ import sinco.presentation.PdeMetasHTML;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PdeMetasHTML
/*      */   extends HTMLObjectImpl
/*      */   implements XMLObject, HTMLObject
/*      */ {
/*      */   private HTMLTableCellElement $element_Anio;
/*      */   private HTMLTableCellElement $element_AnioEd;
/*      */   private HTMLInputElement $element_BtnAyuda;
/*      */   private HTMLInputElement $element_BtnConsultar;
/*      */   private HTMLInputElement $element_BtnCrear;
/*      */   private HTMLInputElement $element_BtnEliminar;
/*      */   private HTMLInputElement $element_BtnGrabar;
/*      */   private HTMLInputElement $element_BtnModificar;
/*      */   private HTMLInputElement $element_BtnSalir;
/*      */   private HTMLInputElement $element_BtnVolver;
/*      */   private HTMLInputElement $element_Cantidad;
/*      */   private HTMLElement $element_CantidadEd;
/*      */   private HTMLInputElement $element_CodigoMeta;
/*      */   private HTMLElement $element_CodigoMetaEd;
/*      */   private HTMLSelectElement $element_CodigoUnidad;
/*      */   private HTMLElement $element_CodigoUnidadEd;
/*      */   private HTMLInputElement $element_CodigoUnidadKey;
/*      */   private HTMLTableSectionElement $element_Detalle;
/*      */   private HTMLTableSectionElement $element_DetalleEd;
/*      */   private HTMLDivElement $element_DivConsulta;
/*      */   private HTMLDivElement $element_DivCreacionRegistro;
/*      */   private HTMLDivElement $element_DivEdicion;
/*      */   private HTMLDivElement $element_DivResultados;
/*      */   private HTMLTableCellElement $element_Ejecutado;
/*      */   private HTMLTableCellElement $element_EjecutadoEd;
/*      */   private HTMLScriptElement $element_ElMenu;
/*      */   private HTMLInputElement $element_FechaInsercion;
/*      */   private HTMLElement $element_FechaInsercionEd;
/*      */   private HTMLInputElement $element_FechaModificacion;
/*      */   private HTMLElement $element_FechaModificacionEd;
/*      */   private HTMLTableSectionElement $element_IdDetalleAnio;
/*      */   private HTMLInputElement $element_IdMeta;
/*      */   private HTMLInputElement $element_IdMetaKey;
/*      */   private HTMLInputElement $element_IdNivelHidden;
/*      */   private HTMLInputElement $element_IdNivelKey;
/*      */   private HTMLInputElement $element_IdObjetivoEspecificoHidden;
/*      */   private HTMLInputElement $element_IdObjetivoEspecificoKey;
/*      */   private HTMLInputElement $element_IdUnidadNivelHidden;
/*      */   private HTMLInputElement $element_IdUnidadNivelKey;
/*      */   private HTMLInputElement $element_Indicador;
/*      */   private HTMLElement $element_IndicadorEd;
/*      */   private HTMLLabelElement $element_LabelNombre;
/*      */   private HTMLInputElement $element_LineaBase;
/*      */   private HTMLElement $element_LineaBaseEd;
/*      */   private HTMLElement $element_NomUnidad;
/*      */   private HTMLElement $element_NomUnidad2;
/*      */   private HTMLElement $element_NombreEntidad;
/*      */   private HTMLInputElement $element_NombreMeta;
/*      */   private HTMLElement $element_NombreMetaEd;
/*      */   private HTMLElement $element_NombreNivel;
/*      */   private HTMLElement $element_NombrePlan;
/*      */   private HTMLElement $element_NroRegistros;
/*      */   private HTMLTableRowElement $element_ObjEspecificoRefe;
/*      */   private HTMLElement $element_ObjetivoEspecificoNivel;
/*      */   private HTMLElement $element_ObjetivoGeneralNivel;
/*      */   private HTMLTableCellElement $element_Porcentaje;
/*      */   private HTMLTableCellElement $element_PorcentajeEd;
/*      */   private HTMLSelectElement $element_Proceso;
/*      */   private HTMLElement $element_ProcesoEd;
/*      */   private HTMLTableCellElement $element_Programado;
/*      */   private HTMLTableCellElement $element_ProgramadoEd;
/*      */   private HTMLScriptElement $element_Responsables;
/*      */   private HTMLScriptElement $element_ScriptAreas;
/*      */   private HTMLTableElement $element_TablaResponsables;
/*      */   private HTMLTableElement $element_TblAnios;
/*      */   private HTMLTableElement $element_TblAniosEd;
/*      */   private HTMLSelectElement $element_TipoMeta;
/*      */   private HTMLElement $element_TipoMetaEd;
/*      */   private HTMLSelectElement $element_TipoUnidad;
/*      */   private HTMLTableRowElement $element_TrProceso;
/*      */   private HTMLTableRowElement $element_TrProcesoEd;
/*      */   private HTMLTableRowElement $element_TrResponsable;
/*      */   private HTMLTableCellElement $element_Trimestre;
/*      */   private HTMLTableCellElement $element_TrimestreEd;
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
/*      */   public static final String CLASS_tabw = "tabw";
/*      */   public static final String CLASS_tit = "tit";
/*      */   public static final String NAME_AgregarLinea = "AgregarLinea";
/*      */   public static final String NAME__operacion = "_operacion";
/*      */   public static final String NAME_cantidad = "cantidad";
/*      */   public static final String NAME_codigoMeta = "codigoMeta";
/*      */   public static final String NAME_codigoUnidad = "codigoUnidad";
/*      */   public static final String NAME_fechaInsercion = "fechaInsercion";
/*      */   public static final String NAME_fechaModificacion = "fechaModificacion";
/*      */   public static final String NAME_ff = "ff";
/*      */   public static final String NAME_filtroResp = "filtroResp";
/*      */   public static final String NAME_idMeta = "idMeta";
/*      */   public static final String NAME_idNivel = "idNivel";
/*      */   public static final String NAME_idObjetivoEspecifico = "idObjetivoEspecifico";
/*      */   public static final String NAME_idUnidadNivel = "idUnidadNivel";
/*      */   public static final String NAME_indicador = "indicador";
/*      */   public static final String NAME_labelNombre = "labelNombre";
/*      */   public static final String NAME_lineaBase = "lineaBase";
/*      */   public static final String NAME_nombreMeta = "nombreMeta";
/*      */   public static final String NAME_proceso = "proceso";
/*      */   public static final String NAME_tipoMeta = "tipoMeta";
/*      */   public static final String NAME_tipoUnidad = "tipoUnidad";
/*      */   public static final String NAME_usuarioInsercion = "usuarioInsercion";
/*      */   public static final String NAME_usuarioModificacion = "usuarioModificacion";
/*  379 */   public static final Class XMLC_GENERATED_CLASS = PdeMetasHTML.class;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/PdeMetas.html";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  390 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final DocumentLoader fDocumentLoader;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PdeMetasHTML() {
/*  401 */     this(StandardDocumentLoader.getInstance());
/*      */     
/*  403 */     buildDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  411 */   public PdeMetasHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PdeMetasHTML(PdeMetasHTML src) {
/*  419 */     this.fDocumentLoader = src.getDocumentLoader();
/*      */     
/*  421 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*      */     
/*  423 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PdeMetasHTML(DocumentLoader loader, boolean buildDOM) {
/*  434 */     this.fDocumentLoader = loader;
/*      */     
/*  436 */     if (buildDOM)
/*      */     {
/*  438 */       buildDocument();
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
/*  450 */   public PdeMetasHTML(DocumentLoader loader) { this(loader, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void buildDocument() {
/*  458 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*      */     
/*  460 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node cloneNode(boolean deep) {
/*  468 */     cloneDeepCheck(deep);
/*      */     
/*  470 */     return new PdeMetasHTML(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  478 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  486 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  495 */   public HTMLTableCellElement getElementAnio() { return this.$element_Anio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  504 */   public HTMLTableCellElement getElementAnioEd() { return this.$element_AnioEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  513 */   public HTMLInputElement getElementBtnAyuda() { return this.$element_BtnAyuda; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  522 */   public HTMLInputElement getElementBtnConsultar() { return this.$element_BtnConsultar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  531 */   public HTMLInputElement getElementBtnCrear() { return this.$element_BtnCrear; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  540 */   public HTMLInputElement getElementBtnEliminar() { return this.$element_BtnEliminar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  549 */   public HTMLInputElement getElementBtnGrabar() { return this.$element_BtnGrabar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  558 */   public HTMLInputElement getElementBtnModificar() { return this.$element_BtnModificar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  567 */   public HTMLInputElement getElementBtnSalir() { return this.$element_BtnSalir; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  576 */   public HTMLInputElement getElementBtnVolver() { return this.$element_BtnVolver; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  585 */   public HTMLInputElement getElementCantidad() { return this.$element_Cantidad; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  594 */   public HTMLElement getElementCantidadEd() { return this.$element_CantidadEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  603 */   public HTMLInputElement getElementCodigoMeta() { return this.$element_CodigoMeta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  612 */   public HTMLElement getElementCodigoMetaEd() { return this.$element_CodigoMetaEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  621 */   public HTMLSelectElement getElementCodigoUnidad() { return this.$element_CodigoUnidad; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  630 */   public HTMLElement getElementCodigoUnidadEd() { return this.$element_CodigoUnidadEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  639 */   public HTMLInputElement getElementCodigoUnidadKey() { return this.$element_CodigoUnidadKey; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  648 */   public HTMLTableSectionElement getElementDetalle() { return this.$element_Detalle; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  657 */   public HTMLTableSectionElement getElementDetalleEd() { return this.$element_DetalleEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  666 */   public HTMLDivElement getElementDivConsulta() { return this.$element_DivConsulta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  675 */   public HTMLDivElement getElementDivCreacionRegistro() { return this.$element_DivCreacionRegistro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  684 */   public HTMLDivElement getElementDivEdicion() { return this.$element_DivEdicion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  693 */   public HTMLDivElement getElementDivResultados() { return this.$element_DivResultados; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  702 */   public HTMLTableCellElement getElementEjecutado() { return this.$element_Ejecutado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  711 */   public HTMLTableCellElement getElementEjecutadoEd() { return this.$element_EjecutadoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  720 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  729 */   public HTMLInputElement getElementFechaInsercion() { return this.$element_FechaInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  738 */   public HTMLElement getElementFechaInsercionEd() { return this.$element_FechaInsercionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  747 */   public HTMLInputElement getElementFechaModificacion() { return this.$element_FechaModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  756 */   public HTMLElement getElementFechaModificacionEd() { return this.$element_FechaModificacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  765 */   public HTMLTableSectionElement getElementIdDetalleAnio() { return this.$element_IdDetalleAnio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  774 */   public HTMLInputElement getElementIdMeta() { return this.$element_IdMeta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  783 */   public HTMLInputElement getElementIdMetaKey() { return this.$element_IdMetaKey; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  792 */   public HTMLInputElement getElementIdNivelHidden() { return this.$element_IdNivelHidden; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  801 */   public HTMLInputElement getElementIdNivelKey() { return this.$element_IdNivelKey; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  810 */   public HTMLInputElement getElementIdObjetivoEspecificoHidden() { return this.$element_IdObjetivoEspecificoHidden; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  819 */   public HTMLInputElement getElementIdObjetivoEspecificoKey() { return this.$element_IdObjetivoEspecificoKey; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  828 */   public HTMLInputElement getElementIdUnidadNivelHidden() { return this.$element_IdUnidadNivelHidden; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  837 */   public HTMLInputElement getElementIdUnidadNivelKey() { return this.$element_IdUnidadNivelKey; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  846 */   public HTMLInputElement getElementIndicador() { return this.$element_Indicador; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  855 */   public HTMLElement getElementIndicadorEd() { return this.$element_IndicadorEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  864 */   public HTMLLabelElement getElementLabelNombre() { return this.$element_LabelNombre; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  873 */   public HTMLInputElement getElementLineaBase() { return this.$element_LineaBase; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  882 */   public HTMLElement getElementLineaBaseEd() { return this.$element_LineaBaseEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  891 */   public HTMLElement getElementNomUnidad() { return this.$element_NomUnidad; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  900 */   public HTMLElement getElementNomUnidad2() { return this.$element_NomUnidad2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  909 */   public HTMLElement getElementNombreEntidad() { return this.$element_NombreEntidad; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  918 */   public HTMLInputElement getElementNombreMeta() { return this.$element_NombreMeta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  927 */   public HTMLElement getElementNombreMetaEd() { return this.$element_NombreMetaEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  936 */   public HTMLElement getElementNombreNivel() { return this.$element_NombreNivel; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  945 */   public HTMLElement getElementNombrePlan() { return this.$element_NombrePlan; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  954 */   public HTMLElement getElementNroRegistros() { return this.$element_NroRegistros; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  963 */   public HTMLTableRowElement getElementObjEspecificoRefe() { return this.$element_ObjEspecificoRefe; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  972 */   public HTMLElement getElementObjetivoEspecificoNivel() { return this.$element_ObjetivoEspecificoNivel; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  981 */   public HTMLElement getElementObjetivoGeneralNivel() { return this.$element_ObjetivoGeneralNivel; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  990 */   public HTMLTableCellElement getElementPorcentaje() { return this.$element_Porcentaje; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  999 */   public HTMLTableCellElement getElementPorcentajeEd() { return this.$element_PorcentajeEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1008 */   public HTMLSelectElement getElementProceso() { return this.$element_Proceso; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1017 */   public HTMLElement getElementProcesoEd() { return this.$element_ProcesoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1026 */   public HTMLTableCellElement getElementProgramado() { return this.$element_Programado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1035 */   public HTMLTableCellElement getElementProgramadoEd() { return this.$element_ProgramadoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1044 */   public HTMLScriptElement getElementResponsables() { return this.$element_Responsables; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1053 */   public HTMLScriptElement getElementScriptAreas() { return this.$element_ScriptAreas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1062 */   public HTMLTableElement getElementTablaResponsables() { return this.$element_TablaResponsables; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1071 */   public HTMLTableElement getElementTblAnios() { return this.$element_TblAnios; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1080 */   public HTMLTableElement getElementTblAniosEd() { return this.$element_TblAniosEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1089 */   public HTMLSelectElement getElementTipoMeta() { return this.$element_TipoMeta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1098 */   public HTMLElement getElementTipoMetaEd() { return this.$element_TipoMetaEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1107 */   public HTMLSelectElement getElementTipoUnidad() { return this.$element_TipoUnidad; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1116 */   public HTMLTableRowElement getElementTrProceso() { return this.$element_TrProceso; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1125 */   public HTMLTableRowElement getElementTrProcesoEd() { return this.$element_TrProcesoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1134 */   public HTMLTableRowElement getElementTrResponsable() { return this.$element_TrResponsable; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1143 */   public HTMLTableCellElement getElementTrimestre() { return this.$element_Trimestre; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1152 */   public HTMLTableCellElement getElementTrimestreEd() { return this.$element_TrimestreEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1161 */   public HTMLInputElement getElementUsuarioInsercion() { return this.$element_UsuarioInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1170 */   public HTMLElement getElementUsuarioInsercionEd() { return this.$element_UsuarioInsercionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1179 */   public HTMLInputElement getElementUsuarioModificacion() { return this.$element_UsuarioModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1188 */   public HTMLElement getElementUsuarioModificacionEd() { return this.$element_UsuarioModificacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1197 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1206 */   public void setTextAnio(String text) { doSetText(this.$element_Anio, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1215 */   public void setTextAnioEd(String text) { doSetText(this.$element_AnioEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1224 */   public void setTextCantidadEd(String text) { doSetText(this.$element_CantidadEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1233 */   public void setTextCodigoMetaEd(String text) { doSetText(this.$element_CodigoMetaEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1242 */   public void setTextCodigoUnidadEd(String text) { doSetText(this.$element_CodigoUnidadEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1251 */   public void setTextDivConsulta(String text) { doSetText(this.$element_DivConsulta, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1260 */   public void setTextDivCreacionRegistro(String text) { doSetText(this.$element_DivCreacionRegistro, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1269 */   public void setTextDivEdicion(String text) { doSetText(this.$element_DivEdicion, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1278 */   public void setTextDivResultados(String text) { doSetText(this.$element_DivResultados, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1287 */   public void setTextEjecutado(String text) { doSetText(this.$element_Ejecutado, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1296 */   public void setTextEjecutadoEd(String text) { doSetText(this.$element_EjecutadoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1305 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1314 */   public void setTextFechaInsercionEd(String text) { doSetText(this.$element_FechaInsercionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1323 */   public void setTextFechaModificacionEd(String text) { doSetText(this.$element_FechaModificacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1332 */   public void setTextIndicadorEd(String text) { doSetText(this.$element_IndicadorEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1341 */   public void setTextLabelNombre(String text) { doSetText(this.$element_LabelNombre, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1350 */   public void setTextLineaBaseEd(String text) { doSetText(this.$element_LineaBaseEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1359 */   public void setTextNomUnidad(String text) { doSetText(this.$element_NomUnidad, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1368 */   public void setTextNomUnidad2(String text) { doSetText(this.$element_NomUnidad2, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1377 */   public void setTextNombreEntidad(String text) { doSetText(this.$element_NombreEntidad, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1386 */   public void setTextNombreMetaEd(String text) { doSetText(this.$element_NombreMetaEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1395 */   public void setTextNombreNivel(String text) { doSetText(this.$element_NombreNivel, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1404 */   public void setTextNombrePlan(String text) { doSetText(this.$element_NombrePlan, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1413 */   public void setTextNroRegistros(String text) { doSetText(this.$element_NroRegistros, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1422 */   public void setTextObjetivoEspecificoNivel(String text) { doSetText(this.$element_ObjetivoEspecificoNivel, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1431 */   public void setTextObjetivoGeneralNivel(String text) { doSetText(this.$element_ObjetivoGeneralNivel, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1440 */   public void setTextPorcentaje(String text) { doSetText(this.$element_Porcentaje, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1449 */   public void setTextPorcentajeEd(String text) { doSetText(this.$element_PorcentajeEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1458 */   public void setTextProcesoEd(String text) { doSetText(this.$element_ProcesoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1467 */   public void setTextProgramado(String text) { doSetText(this.$element_Programado, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1476 */   public void setTextProgramadoEd(String text) { doSetText(this.$element_ProgramadoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1485 */   public void setTextResponsables(String text) { doSetText(this.$element_Responsables, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1494 */   public void setTextScriptAreas(String text) { doSetText(this.$element_ScriptAreas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1503 */   public void setTextTipoMetaEd(String text) { doSetText(this.$element_TipoMetaEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1512 */   public void setTextTrimestre(String text) { doSetText(this.$element_Trimestre, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1521 */   public void setTextTrimestreEd(String text) { doSetText(this.$element_TrimestreEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1530 */   public void setTextUsuarioInsercionEd(String text) { doSetText(this.$element_UsuarioInsercionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1539 */   public void setTextUsuarioModificacionEd(String text) { doSetText(this.$element_UsuarioModificacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void syncWithDocument(Node node) {
/* 1548 */     if (node.getNodeType() != 9)
/*      */     {
/* 1550 */       throw new XMLCRuntimeException("Node must be a document node");
/*      */     }
/*      */ 
/*      */     
/* 1554 */     Document doc = (Document)node;
/*      */ 
/*      */     
/*      */     try {
/* 1558 */       Field[] fs = getClass().getDeclaredFields();
/*      */       
/* 1560 */       int substStart = "$element_".length();
/*      */       
/* 1562 */       for (int i = 0; i < fs.length; i++) {
/*      */         
/* 1564 */         Field f = fs[i];
/*      */         
/* 1566 */         if (f.getName().startsWith("$element_")) {
/*      */           
/* 1568 */           String id = f.getName().substring(substStart);
/*      */           
/* 1570 */           Node idNode = doc.getElementById(id);
/*      */           
/* 1572 */           if (idNode == null) {
/*      */             
/* 1574 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*      */             
/* 1576 */             idNode = doc.getElementById(id);
/*      */           } 
/*      */ 
/*      */           
/* 1580 */           if (idNode != null) f.set(this, idNode);
/*      */         
/*      */         } 
/*      */       } 
/* 1584 */     } catch (Exception e) {
/*      */       
/* 1586 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PdeMetasHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */