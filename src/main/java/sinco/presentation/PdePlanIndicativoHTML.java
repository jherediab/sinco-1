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
/*      */ import sinco.presentation.PdePlanIndicativoHTML;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PdePlanIndicativoHTML
/*      */   extends HTMLObjectImpl
/*      */   implements XMLObject, HTMLObject
/*      */ {
/*      */   private HTMLTableCellElement $element_Anio;
/*      */   private HTMLTableCellElement $element_AnioEd;
/*      */   private HTMLTableCellElement $element_BotonConsultar;
/*      */   private HTMLInputElement $element_BtnAyuda;
/*      */   private HTMLInputElement $element_BtnConsultar;
/*      */   private HTMLInputElement $element_BtnCrear;
/*      */   private HTMLInputElement $element_BtnDescargarPlan;
/*      */   private HTMLInputElement $element_BtnDescargarPlanAccion;
/*      */   private HTMLInputElement $element_BtnDescargarPlanIndicativo;
/*      */   private HTMLInputElement $element_BtnDescargarPoa;
/*      */   private HTMLInputElement $element_BtnEliminar;
/*      */   private HTMLInputElement $element_BtnGrabar;
/*      */   private HTMLInputElement $element_BtnGrabarMetas;
/*      */   private HTMLInputElement $element_BtnGrabarModal;
/*      */   private HTMLInputElement $element_BtnGrabarModalCreacion;
/*      */   private HTMLInputElement $element_BtnGuardaPlan;
/*      */   private HTMLInputElement $element_BtnModificar;
/*      */   private HTMLInputElement $element_BtnSalir;
/*      */   private HTMLInputElement $element_Cantidad;
/*      */   private HTMLElement $element_CantidadEd;
/*      */   private HTMLInputElement $element_CodigoMeta;
/*      */   private HTMLElement $element_CodigoMetaEd;
/*      */   private HTMLTableCellElement $element_CodigoMetaRP;
/*      */   private HTMLInputElement $element_CodigoNivel;
/*      */   private HTMLElement $element_CodigoNivelEd;
/*      */   private HTMLSelectElement $element_CodigoUnidad;
/*      */   private HTMLElement $element_CodigoUnidadEd;
/*      */   private HTMLElement $element_CodigoUnidadEdModal;
/*      */   private HTMLInputElement $element_CodigoUnidadModal;
/*      */   private HTMLInputElement $element_CodigoUnidadModalCreacion;
/*      */   private HTMLDivElement $element_ConsultaModal;
/*      */   private HTMLDivElement $element_ConsultaModalMetas;
/*      */   private HTMLDivElement $element_CreacionModal;
/*      */   private HTMLDivElement $element_CreacionModalMetas;
/*      */   private HTMLSelectElement $element_Criterio1;
/*      */   private HTMLSelectElement $element_Criterio2;
/*      */   private HTMLTableSectionElement $element_Detalle;
/*      */   private HTMLTableSectionElement $element_Detalle2;
/*      */   private HTMLTableSectionElement $element_DetalleEd;
/*      */   private HTMLTableSectionElement $element_DetalleMetas;
/*      */   private HTMLTableSectionElement $element_DetalleNiveles;
/*      */   private HTMLDivElement $element_DivConsulta;
/*      */   private HTMLDivElement $element_DivCreacionRegistro;
/*      */   private HTMLDivElement $element_DivEdicion;
/*      */   private HTMLDivElement $element_DivResultados;
/*      */   private HTMLDivElement $element_DivResultados2;
/*      */   private HTMLDivElement $element_DivResultadosMetas;
/*      */   private HTMLDivElement $element_DivResultadosNiveles;
/*      */   private HTMLDivElement $element_EdicionModal;
/*      */   private HTMLTableCellElement $element_Ejecutado;
/*      */   private HTMLTableCellElement $element_EjecutadoEd;
/*      */   private HTMLScriptElement $element_ElMenu;
/*      */   private HTMLInputElement $element_FechaInsercion;
/*      */   private HTMLElement $element_FechaInsercionEd;
/*      */   private HTMLElement $element_FechaInsercionEdMetas;
/*      */   private HTMLElement $element_FechaInsercionEdModal;
/*      */   private HTMLInputElement $element_FechaInsercionMetas;
/*      */   private HTMLInputElement $element_FechaInsercionModal;
/*      */   private HTMLInputElement $element_FechaInsercionModalCreacion;
/*      */   private HTMLInputElement $element_FechaModificacion;
/*      */   private HTMLElement $element_FechaModificacionEd;
/*      */   private HTMLElement $element_FechaModificacionEdMetas;
/*      */   private HTMLElement $element_FechaModificacionEdModal;
/*      */   private HTMLInputElement $element_FechaModificacionMetas;
/*      */   private HTMLInputElement $element_FechaModificacionModal;
/*      */   private HTMLInputElement $element_FechaModificacionModalCreacion;
/*      */   private HTMLTableSectionElement $element_IdDetalleAnio;
/*      */   private HTMLInputElement $element_IdMeta;
/*      */   private HTMLInputElement $element_IdNivel;
/*      */   private HTMLElement $element_IdNivelEd;
/*      */   private HTMLInputElement $element_IdNivelKey;
/*      */   private HTMLInputElement $element_IdNivelKeyModal;
/*      */   private HTMLInputElement $element_IdPlanDesarrollo;
/*      */   private HTMLElement $element_IdPlanDesarrolloEd;
/*      */   private HTMLInputElement $element_IdPlanDesarrolloKey;
/*      */   private HTMLInputElement $element_IdPlanDesarrolloKeyMetas;
/*      */   private HTMLInputElement $element_IdPlanHidden;
/*      */   private HTMLInputElement $element_IdUnidadNivelKeyModal;
/*      */   private HTMLInputElement $element_IdUnidadNivelModal;
/*      */   private HTMLInputElement $element_IdUnidadNivelModalCreacion;
/*      */   private HTMLInputElement $element_IdUnidadSuperiorKeyModal;
/*      */   private HTMLInputElement $element_Indicador;
/*      */   private HTMLElement $element_IndicadorEd;
/*      */   private HTMLTableCellElement $element_IndicadorRP;
/*      */   private HTMLLabelElement $element_LabelNombre;
/*      */   private HTMLInputElement $element_LineaBase;
/*      */   private HTMLElement $element_LineaBaseEd;
/*      */   private HTMLTableCellElement $element_MetaRP;
/*      */   private HTMLInputElement $element_MetasEspecifico;
/*      */   private HTMLElement $element_MetasEspecificoEd;
/*      */   private HTMLInputElement $element_MetasGeneral;
/*      */   private HTMLElement $element_MetasGeneralEd;
/*      */   private HTMLInputElement $element_NitEntidadHidden;
/*      */   private HTMLInputElement $element_NivelSuperior;
/*      */   private HTMLElement $element_NivelSuperiorEd;
/*      */   private HTMLInputElement $element_NivelSuperiorKey;
/*      */   private HTMLSelectElement $element_NivelSuperiorModalCreacion;
/*      */   private HTMLElement $element_NomUnidad;
/*      */   private HTMLElement $element_NomUnidad2;
/*      */   private HTMLInputElement $element_NombreMeta;
/*      */   private HTMLElement $element_NombreMetaEd;
/*      */   private HTMLInputElement $element_NombreNivel;
/*      */   private HTMLElement $element_NombreNivelConsultaModal;
/*      */   private HTMLElement $element_NombreNivelCreacionModal;
/*      */   private HTMLElement $element_NombreNivelCreacionModalC;
/*      */   private HTMLElement $element_NombreNivelEd;
/*      */   private HTMLTableCellElement $element_NombreNivelRes;
/*      */   private HTMLElement $element_NombrePlanModal;
/*      */   private HTMLElement $element_NombrePlanModalEdicion;
/*      */   private HTMLTableCellElement $element_NombreSiguienteNivel;
/*      */   private HTMLElement $element_NombreUnidadEdModal;
/*      */   private HTMLInputElement $element_NombreUnidadModal;
/*      */   private HTMLInputElement $element_NombreUnidadModalCreacion;
/*      */   private HTMLElement $element_NroRegistros;
/*      */   private HTMLElement $element_NroRegistrosMetas;
/*      */   private HTMLElement $element_NroRegistrosNiveles;
/*      */   private HTMLTableRowElement $element_ObjGeneral;
/*      */   private HTMLTableRowElement $element_ObjGeneralCreacion;
/*      */   private HTMLTableRowElement $element_ObjGeneralEd;
/*      */   private HTMLInputElement $element_ObjetivoEspecifico;
/*      */   private HTMLElement $element_ObjetivoEspecificoEd;
/*      */   private HTMLInputElement $element_ObjetivoGeneral;
/*      */   private HTMLElement $element_ObjetivoGeneralEd;
/*      */   private HTMLElement $element_ObjetivoGeneralEdModal;
/*      */   private HTMLInputElement $element_ObjetivoGeneralModal;
/*      */   private HTMLInputElement $element_ObjetivoGeneralModalCreacion;
/*      */   private HTMLTableCellElement $element_Porcentaje;
/*      */   private HTMLTableCellElement $element_PorcentajeEd;
/*      */   private HTMLSelectElement $element_Proceso;
/*      */   private HTMLElement $element_ProcesoEd;
/*      */   private HTMLTableCellElement $element_Programado;
/*      */   private HTMLTableCellElement $element_ProgramadoEd;
/*      */   private HTMLScriptElement $element_Responsables;
/*      */   private HTMLScriptElement $element_ScriptAreas;
/*      */   private HTMLScriptElement $element_ScriptModal;
/*      */   private HTMLTableElement $element_TablaResponsables;
/*      */   private HTMLTableElement $element_TblAnios;
/*      */   private HTMLTableElement $element_TblAniosEd;
/*      */   private HTMLSelectElement $element_TipoMeta;
/*      */   private HTMLElement $element_TipoMetaEd;
/*      */   private HTMLInputElement $element_TipoNivel;
/*      */   private HTMLElement $element_TipoNivelEd;
/*      */   private HTMLSelectElement $element_TipoUnidad;
/*      */   private HTMLTableRowElement $element_Tr1;
/*      */   private HTMLTableRowElement $element_Tr2;
/*      */   private HTMLTableRowElement $element_TrProceso;
/*      */   private HTMLTableRowElement $element_TrProcesoEd;
/*      */   private HTMLTableRowElement $element_TrResponsable;
/*      */   private HTMLTableRowElement $element_TrSeleccionNivel;
/*      */   private HTMLTableCellElement $element_Trimestre;
/*      */   private HTMLTableCellElement $element_TrimestreEd;
/*      */   private HTMLInputElement $element_UsuarioInsercion;
/*      */   private HTMLElement $element_UsuarioInsercionEd;
/*      */   private HTMLElement $element_UsuarioInsercionEdMetas;
/*      */   private HTMLElement $element_UsuarioInsercionEdModal;
/*      */   private HTMLInputElement $element_UsuarioInsercionMetas;
/*      */   private HTMLInputElement $element_UsuarioInsercionModal;
/*      */   private HTMLInputElement $element_UsuarioInsercionModalCreacion;
/*      */   private HTMLInputElement $element_UsuarioModificacion;
/*      */   private HTMLElement $element_UsuarioModificacionEd;
/*      */   private HTMLElement $element_UsuarioModificacionEdMetas;
/*      */   private HTMLElement $element_UsuarioModificacionEdModal;
/*      */   private HTMLInputElement $element_UsuarioModificacionMetas;
/*      */   private HTMLInputElement $element_UsuarioModificacionModal;
/*      */   private HTMLInputElement $element_UsuarioModificacionModalCreacion;
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
/*      */   public static final String CLASS_modal = "modal";
/*      */   public static final String CLASS_resizable = "resizable";
/*      */   public static final String CLASS_sortable = "sortable";
/*      */   public static final String CLASS_tabf = "tabf";
/*      */   public static final String CLASS_tabw = "tabw";
/*      */   public static final String CLASS_tit = "tit";
/*      */   public static final String NAME_AgregarLinea = "AgregarLinea";
/*      */   public static final String NAME__operacion = "_operacion";
/*      */   public static final String NAME_cantidad = "cantidad";
/*      */   public static final String NAME_codigoMeta = "codigoMeta";
/*      */   public static final String NAME_codigoNivel = "codigoNivel";
/*      */   public static final String NAME_codigoUnidad = "codigoUnidad";
/*      */   public static final String NAME_codigoUnidadModal = "codigoUnidadModal";
/*      */   public static final String NAME_codigoUnidadModalCreacion = "codigoUnidadModalCreacion";
/*      */   public static final String NAME_criterio1 = "criterio1";
/*      */   public static final String NAME_criterio2 = "criterio2";
/*      */   public static final String NAME_fechaInsercion = "fechaInsercion";
/*      */   public static final String NAME_fechaInsercionMetas = "fechaInsercionMetas";
/*      */   public static final String NAME_fechaInsercionModal = "fechaInsercionModal";
/*      */   public static final String NAME_fechaInsercionModalCreacion = "fechaInsercionModalCreacion";
/*      */   public static final String NAME_fechaModificacion = "fechaModificacion";
/*      */   public static final String NAME_fechaModificacionMetas = "fechaModificacionMetas";
/*      */   public static final String NAME_fechaModificacionModal = "fechaModificacionModal";
/*      */   public static final String NAME_fechaModificacionModalCreacion = "fechaModificacionModalCreacion";
/*      */   public static final String NAME_ff = "ff";
/*      */   public static final String NAME_filtroResp = "filtroResp";
/*      */   public static final String NAME_idMeta = "idMeta";
/*      */   public static final String NAME_idNivel = "idNivel";
/*      */   public static final String NAME_idPlan = "idPlan";
/*      */   public static final String NAME_idPlanDesarrollo = "idPlanDesarrollo";
/*      */   public static final String NAME_idUnidadNivel = "idUnidadNivel";
/*      */   public static final String NAME_idUnidadNivelModal = "idUnidadNivelModal";
/*      */   public static final String NAME_idUnidadNivelModalCreacion = "idUnidadNivelModalCreacion";
/*      */   public static final String NAME_idUnidadSuperior = "idUnidadSuperior";
/*      */   public static final String NAME_indicador = "indicador";
/*      */   public static final String NAME_labelNombre = "labelNombre";
/*      */   public static final String NAME_lineaBase = "lineaBase";
/*      */   public static final String NAME_metasEspecifico = "metasEspecifico";
/*      */   public static final String NAME_metasGeneral = "metasGeneral";
/*      */   public static final String NAME_nitEntidad = "nitEntidad";
/*      */   public static final String NAME_nivelSuperior = "nivelSuperior";
/*      */   public static final String NAME_nivelSuperiorModalCreacion = "nivelSuperiorModalCreacion";
/*      */   public static final String NAME_nombreMeta = "nombreMeta";
/*      */   public static final String NAME_nombreNivel = "nombreNivel";
/*      */   public static final String NAME_nombreUnidadModal = "nombreUnidadModal";
/*      */   public static final String NAME_nombreUnidadModalCreacion = "nombreUnidadModalCreacion";
/*      */   public static final String NAME_objetivoEspecifico = "objetivoEspecifico";
/*      */   public static final String NAME_objetivoGeneral = "objetivoGeneral";
/*      */   public static final String NAME_objetivoGeneralModal = "objetivoGeneralModal";
/*      */   public static final String NAME_objetivoGeneralModalCreacion = "objetivoGeneralModalCreacion";
/*      */   public static final String NAME_proceso = "proceso";
/*      */   public static final String NAME_tipoMeta = "tipoMeta";
/*      */   public static final String NAME_tipoNivel = "tipoNivel";
/*      */   public static final String NAME_tipoUnidad = "tipoUnidad";
/*      */   public static final String NAME_usuarioInsercion = "usuarioInsercion";
/*      */   public static final String NAME_usuarioInsercionMetas = "usuarioInsercionMetas";
/*      */   public static final String NAME_usuarioInsercionModal = "usuarioInsercionModal";
/*      */   public static final String NAME_usuarioInsercionModalCreacion = "usuarioInsercionModalCreacion";
/*      */   public static final String NAME_usuarioModificacion = "usuarioModificacion";
/*      */   public static final String NAME_usuarioModificacionMetas = "usuarioModificacionMetas";
/*      */   public static final String NAME_usuarioModificacionModal = "usuarioModificacionModal";
/*      */   public static final String NAME_usuarioModificacionModalCreacion = "usuarioModificacionModalCreacion";
/*  728 */   public static final Class XMLC_GENERATED_CLASS = PdePlanIndicativoHTML.class;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/PdePlanIndicativo.html";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  739 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final DocumentLoader fDocumentLoader;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PdePlanIndicativoHTML() {
/*  750 */     this(StandardDocumentLoader.getInstance());
/*      */     
/*  752 */     buildDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  760 */   public PdePlanIndicativoHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PdePlanIndicativoHTML(PdePlanIndicativoHTML src) {
/*  768 */     this.fDocumentLoader = src.getDocumentLoader();
/*      */     
/*  770 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*      */     
/*  772 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PdePlanIndicativoHTML(DocumentLoader loader, boolean buildDOM) {
/*  783 */     this.fDocumentLoader = loader;
/*      */     
/*  785 */     if (buildDOM)
/*      */     {
/*  787 */       buildDocument();
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
/*  799 */   public PdePlanIndicativoHTML(DocumentLoader loader) { this(loader, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void buildDocument() {
/*  807 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*      */     
/*  809 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node cloneNode(boolean deep) {
/*  817 */     cloneDeepCheck(deep);
/*      */     
/*  819 */     return new PdePlanIndicativoHTML(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  827 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  835 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  844 */   public HTMLTableCellElement getElementAnio() { return this.$element_Anio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  853 */   public HTMLTableCellElement getElementAnioEd() { return this.$element_AnioEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  862 */   public HTMLTableCellElement getElementBotonConsultar() { return this.$element_BotonConsultar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  871 */   public HTMLInputElement getElementBtnAyuda() { return this.$element_BtnAyuda; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  880 */   public HTMLInputElement getElementBtnConsultar() { return this.$element_BtnConsultar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  889 */   public HTMLInputElement getElementBtnCrear() { return this.$element_BtnCrear; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  898 */   public HTMLInputElement getElementBtnDescargarPlan() { return this.$element_BtnDescargarPlan; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  907 */   public HTMLInputElement getElementBtnDescargarPlanAccion() { return this.$element_BtnDescargarPlanAccion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  916 */   public HTMLInputElement getElementBtnDescargarPlanIndicativo() { return this.$element_BtnDescargarPlanIndicativo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  925 */   public HTMLInputElement getElementBtnDescargarPoa() { return this.$element_BtnDescargarPoa; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  934 */   public HTMLInputElement getElementBtnEliminar() { return this.$element_BtnEliminar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  943 */   public HTMLInputElement getElementBtnGrabar() { return this.$element_BtnGrabar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  952 */   public HTMLInputElement getElementBtnGrabarMetas() { return this.$element_BtnGrabarMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  961 */   public HTMLInputElement getElementBtnGrabarModal() { return this.$element_BtnGrabarModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  970 */   public HTMLInputElement getElementBtnGrabarModalCreacion() { return this.$element_BtnGrabarModalCreacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  979 */   public HTMLInputElement getElementBtnGuardaPlan() { return this.$element_BtnGuardaPlan; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  988 */   public HTMLInputElement getElementBtnModificar() { return this.$element_BtnModificar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  997 */   public HTMLInputElement getElementBtnSalir() { return this.$element_BtnSalir; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1006 */   public HTMLInputElement getElementCantidad() { return this.$element_Cantidad; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1015 */   public HTMLElement getElementCantidadEd() { return this.$element_CantidadEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1024 */   public HTMLInputElement getElementCodigoMeta() { return this.$element_CodigoMeta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1033 */   public HTMLElement getElementCodigoMetaEd() { return this.$element_CodigoMetaEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1042 */   public HTMLTableCellElement getElementCodigoMetaRP() { return this.$element_CodigoMetaRP; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1051 */   public HTMLInputElement getElementCodigoNivel() { return this.$element_CodigoNivel; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1060 */   public HTMLElement getElementCodigoNivelEd() { return this.$element_CodigoNivelEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1069 */   public HTMLSelectElement getElementCodigoUnidad() { return this.$element_CodigoUnidad; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1078 */   public HTMLElement getElementCodigoUnidadEd() { return this.$element_CodigoUnidadEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1087 */   public HTMLElement getElementCodigoUnidadEdModal() { return this.$element_CodigoUnidadEdModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1096 */   public HTMLInputElement getElementCodigoUnidadModal() { return this.$element_CodigoUnidadModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1105 */   public HTMLInputElement getElementCodigoUnidadModalCreacion() { return this.$element_CodigoUnidadModalCreacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1114 */   public HTMLDivElement getElementConsultaModal() { return this.$element_ConsultaModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1123 */   public HTMLDivElement getElementConsultaModalMetas() { return this.$element_ConsultaModalMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1132 */   public HTMLDivElement getElementCreacionModal() { return this.$element_CreacionModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1141 */   public HTMLDivElement getElementCreacionModalMetas() { return this.$element_CreacionModalMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1150 */   public HTMLSelectElement getElementCriterio1() { return this.$element_Criterio1; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1159 */   public HTMLSelectElement getElementCriterio2() { return this.$element_Criterio2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1168 */   public HTMLTableSectionElement getElementDetalle() { return this.$element_Detalle; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1177 */   public HTMLTableSectionElement getElementDetalle2() { return this.$element_Detalle2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1186 */   public HTMLTableSectionElement getElementDetalleEd() { return this.$element_DetalleEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1195 */   public HTMLTableSectionElement getElementDetalleMetas() { return this.$element_DetalleMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1204 */   public HTMLTableSectionElement getElementDetalleNiveles() { return this.$element_DetalleNiveles; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1213 */   public HTMLDivElement getElementDivConsulta() { return this.$element_DivConsulta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1222 */   public HTMLDivElement getElementDivCreacionRegistro() { return this.$element_DivCreacionRegistro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1231 */   public HTMLDivElement getElementDivEdicion() { return this.$element_DivEdicion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1240 */   public HTMLDivElement getElementDivResultados() { return this.$element_DivResultados; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1249 */   public HTMLDivElement getElementDivResultados2() { return this.$element_DivResultados2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1258 */   public HTMLDivElement getElementDivResultadosMetas() { return this.$element_DivResultadosMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1267 */   public HTMLDivElement getElementDivResultadosNiveles() { return this.$element_DivResultadosNiveles; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1276 */   public HTMLDivElement getElementEdicionModal() { return this.$element_EdicionModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1285 */   public HTMLTableCellElement getElementEjecutado() { return this.$element_Ejecutado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1294 */   public HTMLTableCellElement getElementEjecutadoEd() { return this.$element_EjecutadoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1303 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1312 */   public HTMLInputElement getElementFechaInsercion() { return this.$element_FechaInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1321 */   public HTMLElement getElementFechaInsercionEd() { return this.$element_FechaInsercionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1330 */   public HTMLElement getElementFechaInsercionEdMetas() { return this.$element_FechaInsercionEdMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1339 */   public HTMLElement getElementFechaInsercionEdModal() { return this.$element_FechaInsercionEdModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1348 */   public HTMLInputElement getElementFechaInsercionMetas() { return this.$element_FechaInsercionMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1357 */   public HTMLInputElement getElementFechaInsercionModal() { return this.$element_FechaInsercionModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1366 */   public HTMLInputElement getElementFechaInsercionModalCreacion() { return this.$element_FechaInsercionModalCreacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1375 */   public HTMLInputElement getElementFechaModificacion() { return this.$element_FechaModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1384 */   public HTMLElement getElementFechaModificacionEd() { return this.$element_FechaModificacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1393 */   public HTMLElement getElementFechaModificacionEdMetas() { return this.$element_FechaModificacionEdMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1402 */   public HTMLElement getElementFechaModificacionEdModal() { return this.$element_FechaModificacionEdModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1411 */   public HTMLInputElement getElementFechaModificacionMetas() { return this.$element_FechaModificacionMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1420 */   public HTMLInputElement getElementFechaModificacionModal() { return this.$element_FechaModificacionModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1429 */   public HTMLInputElement getElementFechaModificacionModalCreacion() { return this.$element_FechaModificacionModalCreacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1438 */   public HTMLTableSectionElement getElementIdDetalleAnio() { return this.$element_IdDetalleAnio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1447 */   public HTMLInputElement getElementIdMeta() { return this.$element_IdMeta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1456 */   public HTMLInputElement getElementIdNivel() { return this.$element_IdNivel; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1465 */   public HTMLElement getElementIdNivelEd() { return this.$element_IdNivelEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1474 */   public HTMLInputElement getElementIdNivelKey() { return this.$element_IdNivelKey; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1483 */   public HTMLInputElement getElementIdNivelKeyModal() { return this.$element_IdNivelKeyModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1492 */   public HTMLInputElement getElementIdPlanDesarrollo() { return this.$element_IdPlanDesarrollo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1501 */   public HTMLElement getElementIdPlanDesarrolloEd() { return this.$element_IdPlanDesarrolloEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1510 */   public HTMLInputElement getElementIdPlanDesarrolloKey() { return this.$element_IdPlanDesarrolloKey; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1519 */   public HTMLInputElement getElementIdPlanDesarrolloKeyMetas() { return this.$element_IdPlanDesarrolloKeyMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1528 */   public HTMLInputElement getElementIdPlanHidden() { return this.$element_IdPlanHidden; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1537 */   public HTMLInputElement getElementIdUnidadNivelKeyModal() { return this.$element_IdUnidadNivelKeyModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1546 */   public HTMLInputElement getElementIdUnidadNivelModal() { return this.$element_IdUnidadNivelModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1555 */   public HTMLInputElement getElementIdUnidadNivelModalCreacion() { return this.$element_IdUnidadNivelModalCreacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1564 */   public HTMLInputElement getElementIdUnidadSuperiorKeyModal() { return this.$element_IdUnidadSuperiorKeyModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1573 */   public HTMLInputElement getElementIndicador() { return this.$element_Indicador; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1582 */   public HTMLElement getElementIndicadorEd() { return this.$element_IndicadorEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1591 */   public HTMLTableCellElement getElementIndicadorRP() { return this.$element_IndicadorRP; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1600 */   public HTMLLabelElement getElementLabelNombre() { return this.$element_LabelNombre; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1609 */   public HTMLInputElement getElementLineaBase() { return this.$element_LineaBase; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1618 */   public HTMLElement getElementLineaBaseEd() { return this.$element_LineaBaseEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1627 */   public HTMLTableCellElement getElementMetaRP() { return this.$element_MetaRP; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1636 */   public HTMLInputElement getElementMetasEspecifico() { return this.$element_MetasEspecifico; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1645 */   public HTMLElement getElementMetasEspecificoEd() { return this.$element_MetasEspecificoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1654 */   public HTMLInputElement getElementMetasGeneral() { return this.$element_MetasGeneral; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1663 */   public HTMLElement getElementMetasGeneralEd() { return this.$element_MetasGeneralEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1672 */   public HTMLInputElement getElementNitEntidadHidden() { return this.$element_NitEntidadHidden; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1681 */   public HTMLInputElement getElementNivelSuperior() { return this.$element_NivelSuperior; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1690 */   public HTMLElement getElementNivelSuperiorEd() { return this.$element_NivelSuperiorEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1699 */   public HTMLInputElement getElementNivelSuperiorKey() { return this.$element_NivelSuperiorKey; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1708 */   public HTMLSelectElement getElementNivelSuperiorModalCreacion() { return this.$element_NivelSuperiorModalCreacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1717 */   public HTMLElement getElementNomUnidad() { return this.$element_NomUnidad; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1726 */   public HTMLElement getElementNomUnidad2() { return this.$element_NomUnidad2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1735 */   public HTMLInputElement getElementNombreMeta() { return this.$element_NombreMeta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1744 */   public HTMLElement getElementNombreMetaEd() { return this.$element_NombreMetaEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1753 */   public HTMLInputElement getElementNombreNivel() { return this.$element_NombreNivel; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1762 */   public HTMLElement getElementNombreNivelConsultaModal() { return this.$element_NombreNivelConsultaModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1771 */   public HTMLElement getElementNombreNivelCreacionModal() { return this.$element_NombreNivelCreacionModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1780 */   public HTMLElement getElementNombreNivelCreacionModalC() { return this.$element_NombreNivelCreacionModalC; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1789 */   public HTMLElement getElementNombreNivelEd() { return this.$element_NombreNivelEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1798 */   public HTMLTableCellElement getElementNombreNivelRes() { return this.$element_NombreNivelRes; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1807 */   public HTMLElement getElementNombrePlanModal() { return this.$element_NombrePlanModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1816 */   public HTMLElement getElementNombrePlanModalEdicion() { return this.$element_NombrePlanModalEdicion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1825 */   public HTMLTableCellElement getElementNombreSiguienteNivel() { return this.$element_NombreSiguienteNivel; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1834 */   public HTMLElement getElementNombreUnidadEdModal() { return this.$element_NombreUnidadEdModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1843 */   public HTMLInputElement getElementNombreUnidadModal() { return this.$element_NombreUnidadModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1852 */   public HTMLInputElement getElementNombreUnidadModalCreacion() { return this.$element_NombreUnidadModalCreacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1861 */   public HTMLElement getElementNroRegistros() { return this.$element_NroRegistros; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1870 */   public HTMLElement getElementNroRegistrosMetas() { return this.$element_NroRegistrosMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1879 */   public HTMLElement getElementNroRegistrosNiveles() { return this.$element_NroRegistrosNiveles; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1888 */   public HTMLTableRowElement getElementObjGeneral() { return this.$element_ObjGeneral; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1897 */   public HTMLTableRowElement getElementObjGeneralCreacion() { return this.$element_ObjGeneralCreacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1906 */   public HTMLTableRowElement getElementObjGeneralEd() { return this.$element_ObjGeneralEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1915 */   public HTMLInputElement getElementObjetivoEspecifico() { return this.$element_ObjetivoEspecifico; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1924 */   public HTMLElement getElementObjetivoEspecificoEd() { return this.$element_ObjetivoEspecificoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1933 */   public HTMLInputElement getElementObjetivoGeneral() { return this.$element_ObjetivoGeneral; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1942 */   public HTMLElement getElementObjetivoGeneralEd() { return this.$element_ObjetivoGeneralEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1951 */   public HTMLElement getElementObjetivoGeneralEdModal() { return this.$element_ObjetivoGeneralEdModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1960 */   public HTMLInputElement getElementObjetivoGeneralModal() { return this.$element_ObjetivoGeneralModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1969 */   public HTMLInputElement getElementObjetivoGeneralModalCreacion() { return this.$element_ObjetivoGeneralModalCreacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1978 */   public HTMLTableCellElement getElementPorcentaje() { return this.$element_Porcentaje; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1987 */   public HTMLTableCellElement getElementPorcentajeEd() { return this.$element_PorcentajeEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1996 */   public HTMLSelectElement getElementProceso() { return this.$element_Proceso; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2005 */   public HTMLElement getElementProcesoEd() { return this.$element_ProcesoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2014 */   public HTMLTableCellElement getElementProgramado() { return this.$element_Programado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2023 */   public HTMLTableCellElement getElementProgramadoEd() { return this.$element_ProgramadoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2032 */   public HTMLScriptElement getElementResponsables() { return this.$element_Responsables; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2041 */   public HTMLScriptElement getElementScriptAreas() { return this.$element_ScriptAreas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2050 */   public HTMLScriptElement getElementScriptModal() { return this.$element_ScriptModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2059 */   public HTMLTableElement getElementTablaResponsables() { return this.$element_TablaResponsables; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2068 */   public HTMLTableElement getElementTblAnios() { return this.$element_TblAnios; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2077 */   public HTMLTableElement getElementTblAniosEd() { return this.$element_TblAniosEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2086 */   public HTMLSelectElement getElementTipoMeta() { return this.$element_TipoMeta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2095 */   public HTMLElement getElementTipoMetaEd() { return this.$element_TipoMetaEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2104 */   public HTMLInputElement getElementTipoNivel() { return this.$element_TipoNivel; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2113 */   public HTMLElement getElementTipoNivelEd() { return this.$element_TipoNivelEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2122 */   public HTMLSelectElement getElementTipoUnidad() { return this.$element_TipoUnidad; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2131 */   public HTMLTableRowElement getElementTr1() { return this.$element_Tr1; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2140 */   public HTMLTableRowElement getElementTr2() { return this.$element_Tr2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2149 */   public HTMLTableRowElement getElementTrProceso() { return this.$element_TrProceso; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2158 */   public HTMLTableRowElement getElementTrProcesoEd() { return this.$element_TrProcesoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2167 */   public HTMLTableRowElement getElementTrResponsable() { return this.$element_TrResponsable; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2176 */   public HTMLTableRowElement getElementTrSeleccionNivel() { return this.$element_TrSeleccionNivel; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2185 */   public HTMLTableCellElement getElementTrimestre() { return this.$element_Trimestre; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2194 */   public HTMLTableCellElement getElementTrimestreEd() { return this.$element_TrimestreEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2203 */   public HTMLInputElement getElementUsuarioInsercion() { return this.$element_UsuarioInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2212 */   public HTMLElement getElementUsuarioInsercionEd() { return this.$element_UsuarioInsercionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2221 */   public HTMLElement getElementUsuarioInsercionEdMetas() { return this.$element_UsuarioInsercionEdMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2230 */   public HTMLElement getElementUsuarioInsercionEdModal() { return this.$element_UsuarioInsercionEdModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2239 */   public HTMLInputElement getElementUsuarioInsercionMetas() { return this.$element_UsuarioInsercionMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2248 */   public HTMLInputElement getElementUsuarioInsercionModal() { return this.$element_UsuarioInsercionModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2257 */   public HTMLInputElement getElementUsuarioInsercionModalCreacion() { return this.$element_UsuarioInsercionModalCreacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2266 */   public HTMLInputElement getElementUsuarioModificacion() { return this.$element_UsuarioModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2275 */   public HTMLElement getElementUsuarioModificacionEd() { return this.$element_UsuarioModificacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2284 */   public HTMLElement getElementUsuarioModificacionEdMetas() { return this.$element_UsuarioModificacionEdMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2293 */   public HTMLElement getElementUsuarioModificacionEdModal() { return this.$element_UsuarioModificacionEdModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2302 */   public HTMLInputElement getElementUsuarioModificacionMetas() { return this.$element_UsuarioModificacionMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2311 */   public HTMLInputElement getElementUsuarioModificacionModal() { return this.$element_UsuarioModificacionModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2320 */   public HTMLInputElement getElementUsuarioModificacionModalCreacion() { return this.$element_UsuarioModificacionModalCreacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2329 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2338 */   public void setTextAnio(String text) { doSetText(this.$element_Anio, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2347 */   public void setTextAnioEd(String text) { doSetText(this.$element_AnioEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2356 */   public void setTextBotonConsultar(String text) { doSetText(this.$element_BotonConsultar, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2365 */   public void setTextCantidadEd(String text) { doSetText(this.$element_CantidadEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2374 */   public void setTextCodigoMetaEd(String text) { doSetText(this.$element_CodigoMetaEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2383 */   public void setTextCodigoMetaRP(String text) { doSetText(this.$element_CodigoMetaRP, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2392 */   public void setTextCodigoNivelEd(String text) { doSetText(this.$element_CodigoNivelEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2401 */   public void setTextCodigoUnidadEd(String text) { doSetText(this.$element_CodigoUnidadEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2410 */   public void setTextCodigoUnidadEdModal(String text) { doSetText(this.$element_CodigoUnidadEdModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2419 */   public void setTextConsultaModal(String text) { doSetText(this.$element_ConsultaModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2428 */   public void setTextConsultaModalMetas(String text) { doSetText(this.$element_ConsultaModalMetas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2437 */   public void setTextCreacionModal(String text) { doSetText(this.$element_CreacionModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2446 */   public void setTextCreacionModalMetas(String text) { doSetText(this.$element_CreacionModalMetas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2455 */   public void setTextDivConsulta(String text) { doSetText(this.$element_DivConsulta, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2464 */   public void setTextDivCreacionRegistro(String text) { doSetText(this.$element_DivCreacionRegistro, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2473 */   public void setTextDivEdicion(String text) { doSetText(this.$element_DivEdicion, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2482 */   public void setTextDivResultados(String text) { doSetText(this.$element_DivResultados, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2491 */   public void setTextDivResultados2(String text) { doSetText(this.$element_DivResultados2, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2500 */   public void setTextDivResultadosMetas(String text) { doSetText(this.$element_DivResultadosMetas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2509 */   public void setTextDivResultadosNiveles(String text) { doSetText(this.$element_DivResultadosNiveles, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2518 */   public void setTextEdicionModal(String text) { doSetText(this.$element_EdicionModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2527 */   public void setTextEjecutado(String text) { doSetText(this.$element_Ejecutado, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2536 */   public void setTextEjecutadoEd(String text) { doSetText(this.$element_EjecutadoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2545 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2554 */   public void setTextFechaInsercionEd(String text) { doSetText(this.$element_FechaInsercionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2563 */   public void setTextFechaInsercionEdMetas(String text) { doSetText(this.$element_FechaInsercionEdMetas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2572 */   public void setTextFechaInsercionEdModal(String text) { doSetText(this.$element_FechaInsercionEdModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2581 */   public void setTextFechaModificacionEd(String text) { doSetText(this.$element_FechaModificacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2590 */   public void setTextFechaModificacionEdMetas(String text) { doSetText(this.$element_FechaModificacionEdMetas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2599 */   public void setTextFechaModificacionEdModal(String text) { doSetText(this.$element_FechaModificacionEdModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2608 */   public void setTextIdNivelEd(String text) { doSetText(this.$element_IdNivelEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2617 */   public void setTextIdPlanDesarrolloEd(String text) { doSetText(this.$element_IdPlanDesarrolloEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2626 */   public void setTextIndicadorEd(String text) { doSetText(this.$element_IndicadorEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2635 */   public void setTextIndicadorRP(String text) { doSetText(this.$element_IndicadorRP, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2644 */   public void setTextLabelNombre(String text) { doSetText(this.$element_LabelNombre, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2653 */   public void setTextLineaBaseEd(String text) { doSetText(this.$element_LineaBaseEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2662 */   public void setTextMetaRP(String text) { doSetText(this.$element_MetaRP, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2671 */   public void setTextMetasEspecificoEd(String text) { doSetText(this.$element_MetasEspecificoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2680 */   public void setTextMetasGeneralEd(String text) { doSetText(this.$element_MetasGeneralEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2689 */   public void setTextNivelSuperiorEd(String text) { doSetText(this.$element_NivelSuperiorEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2698 */   public void setTextNomUnidad(String text) { doSetText(this.$element_NomUnidad, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2707 */   public void setTextNomUnidad2(String text) { doSetText(this.$element_NomUnidad2, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2716 */   public void setTextNombreMetaEd(String text) { doSetText(this.$element_NombreMetaEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2725 */   public void setTextNombreNivelConsultaModal(String text) { doSetText(this.$element_NombreNivelConsultaModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2734 */   public void setTextNombreNivelCreacionModal(String text) { doSetText(this.$element_NombreNivelCreacionModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2743 */   public void setTextNombreNivelCreacionModalC(String text) { doSetText(this.$element_NombreNivelCreacionModalC, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2752 */   public void setTextNombreNivelEd(String text) { doSetText(this.$element_NombreNivelEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2761 */   public void setTextNombreNivelRes(String text) { doSetText(this.$element_NombreNivelRes, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2770 */   public void setTextNombrePlanModal(String text) { doSetText(this.$element_NombrePlanModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2779 */   public void setTextNombrePlanModalEdicion(String text) { doSetText(this.$element_NombrePlanModalEdicion, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2788 */   public void setTextNombreSiguienteNivel(String text) { doSetText(this.$element_NombreSiguienteNivel, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2797 */   public void setTextNombreUnidadEdModal(String text) { doSetText(this.$element_NombreUnidadEdModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2806 */   public void setTextNroRegistros(String text) { doSetText(this.$element_NroRegistros, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2815 */   public void setTextNroRegistrosMetas(String text) { doSetText(this.$element_NroRegistrosMetas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2824 */   public void setTextNroRegistrosNiveles(String text) { doSetText(this.$element_NroRegistrosNiveles, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2833 */   public void setTextObjetivoEspecificoEd(String text) { doSetText(this.$element_ObjetivoEspecificoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2842 */   public void setTextObjetivoGeneralEd(String text) { doSetText(this.$element_ObjetivoGeneralEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2851 */   public void setTextObjetivoGeneralEdModal(String text) { doSetText(this.$element_ObjetivoGeneralEdModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2860 */   public void setTextPorcentaje(String text) { doSetText(this.$element_Porcentaje, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2869 */   public void setTextPorcentajeEd(String text) { doSetText(this.$element_PorcentajeEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2878 */   public void setTextProcesoEd(String text) { doSetText(this.$element_ProcesoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2887 */   public void setTextProgramado(String text) { doSetText(this.$element_Programado, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2896 */   public void setTextProgramadoEd(String text) { doSetText(this.$element_ProgramadoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2905 */   public void setTextResponsables(String text) { doSetText(this.$element_Responsables, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2914 */   public void setTextScriptAreas(String text) { doSetText(this.$element_ScriptAreas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2923 */   public void setTextScriptModal(String text) { doSetText(this.$element_ScriptModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2932 */   public void setTextTipoMetaEd(String text) { doSetText(this.$element_TipoMetaEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2941 */   public void setTextTipoNivelEd(String text) { doSetText(this.$element_TipoNivelEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2950 */   public void setTextTrimestre(String text) { doSetText(this.$element_Trimestre, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2959 */   public void setTextTrimestreEd(String text) { doSetText(this.$element_TrimestreEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2968 */   public void setTextUsuarioInsercionEd(String text) { doSetText(this.$element_UsuarioInsercionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2977 */   public void setTextUsuarioInsercionEdMetas(String text) { doSetText(this.$element_UsuarioInsercionEdMetas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2986 */   public void setTextUsuarioInsercionEdModal(String text) { doSetText(this.$element_UsuarioInsercionEdModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2995 */   public void setTextUsuarioModificacionEd(String text) { doSetText(this.$element_UsuarioModificacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3004 */   public void setTextUsuarioModificacionEdMetas(String text) { doSetText(this.$element_UsuarioModificacionEdMetas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3013 */   public void setTextUsuarioModificacionEdModal(String text) { doSetText(this.$element_UsuarioModificacionEdModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void syncWithDocument(Node node) {
/* 3022 */     if (node.getNodeType() != 9)
/*      */     {
/* 3024 */       throw new XMLCRuntimeException("Node must be a document node");
/*      */     }
/*      */ 
/*      */     
/* 3028 */     Document doc = (Document)node;
/*      */ 
/*      */     
/*      */     try {
/* 3032 */       Field[] fs = getClass().getDeclaredFields();
/*      */       
/* 3034 */       int substStart = "$element_".length();
/*      */       
/* 3036 */       for (int i = 0; i < fs.length; i++) {
/*      */         
/* 3038 */         Field f = fs[i];
/*      */         
/* 3040 */         if (f.getName().startsWith("$element_")) {
/*      */           
/* 3042 */           String id = f.getName().substring(substStart);
/*      */           
/* 3044 */           Node idNode = doc.getElementById(id);
/*      */           
/* 3046 */           if (idNode == null) {
/*      */             
/* 3048 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*      */             
/* 3050 */             idNode = doc.getElementById(id);
/*      */           } 
/*      */ 
/*      */           
/* 3054 */           if (idNode != null) f.set(this, idNode);
/*      */         
/*      */         } 
/*      */       } 
/* 3058 */     } catch (Exception e) {
/*      */       
/* 3060 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PdePlanIndicativoHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */