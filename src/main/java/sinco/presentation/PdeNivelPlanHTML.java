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
/*      */ import sinco.presentation.PdeNivelPlanHTML;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PdeNivelPlanHTML
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
/*      */   private HTMLInputElement $element_BtnVolver;
/*      */   private HTMLInputElement $element_Cantidad;
/*      */   private HTMLElement $element_CantidadEd;
/*      */   private HTMLInputElement $element_CodigoMeta;
/*      */   private HTMLElement $element_CodigoMetaEd;
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
/*      */   private HTMLSelectElement $element_Criterio1Todo;
/*      */   private HTMLSelectElement $element_Criterio2;
/*      */   private HTMLSelectElement $element_Criterio2Todo;
/*      */   private HTMLSelectElement $element_Criterio3;
/*      */   private HTMLSelectElement $element_Criterio3Todo;
/*      */   private HTMLSelectElement $element_Criterio4;
/*      */   private HTMLSelectElement $element_Criterio4Todo;
/*      */   private HTMLSelectElement $element_Criterio5;
/*      */   private HTMLSelectElement $element_Criterio5Todo;
/*      */   private HTMLSelectElement $element_Criterio6;
/*      */   private HTMLSelectElement $element_Criterio6Todo;
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
/*      */   private HTMLLabelElement $element_LabelNombre;
/*      */   private HTMLInputElement $element_LineaBase;
/*      */   private HTMLElement $element_LineaBaseEd;
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
/*      */   private HTMLTableRowElement $element_Tr3;
/*      */   private HTMLTableRowElement $element_Tr4;
/*      */   private HTMLTableRowElement $element_Tr5;
/*      */   private HTMLTableRowElement $element_Tr6;
/*      */   private HTMLTableRowElement $element_TrProceso;
/*      */   private HTMLTableRowElement $element_TrProcesoEd;
/*      */   private HTMLTableRowElement $element_TrResponsable;
/*      */   private HTMLTableCellElement $element_TrResultNombre;
/*      */   private HTMLTableCellElement $element_TrResultTareas;
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
/*      */   public static final String NAME_criterio1Todo = "criterio1Todo";
/*      */   public static final String NAME_criterio2 = "criterio2";
/*      */   public static final String NAME_criterio2Todo = "criterio2Todo";
/*      */   public static final String NAME_criterio3 = "criterio3";
/*      */   public static final String NAME_criterio3Todo = "criterio3Todo";
/*      */   public static final String NAME_criterio4 = "criterio4";
/*      */   public static final String NAME_criterio4Todo = "criterio4Todo";
/*      */   public static final String NAME_criterio5 = "criterio5";
/*      */   public static final String NAME_criterio5Todo = "criterio5Todo";
/*      */   public static final String NAME_criterio6 = "criterio6";
/*      */   public static final String NAME_criterio6Todo = "criterio6Todo";
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
/*  804 */   public static final Class XMLC_GENERATED_CLASS = PdeNivelPlanHTML.class;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/PdeNivelPlan.html";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  815 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final DocumentLoader fDocumentLoader;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PdeNivelPlanHTML() {
/*  826 */     this(StandardDocumentLoader.getInstance());
/*      */     
/*  828 */     buildDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  836 */   public PdeNivelPlanHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PdeNivelPlanHTML(PdeNivelPlanHTML src) {
/*  844 */     this.fDocumentLoader = src.getDocumentLoader();
/*      */     
/*  846 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*      */     
/*  848 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PdeNivelPlanHTML(DocumentLoader loader, boolean buildDOM) {
/*  859 */     this.fDocumentLoader = loader;
/*      */     
/*  861 */     if (buildDOM)
/*      */     {
/*  863 */       buildDocument();
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
/*  875 */   public PdeNivelPlanHTML(DocumentLoader loader) { this(loader, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void buildDocument() {
/*  883 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*      */     
/*  885 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node cloneNode(boolean deep) {
/*  893 */     cloneDeepCheck(deep);
/*      */     
/*  895 */     return new PdeNivelPlanHTML(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  903 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  911 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  920 */   public HTMLTableCellElement getElementAnio() { return this.$element_Anio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  929 */   public HTMLTableCellElement getElementAnioEd() { return this.$element_AnioEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  938 */   public HTMLTableCellElement getElementBotonConsultar() { return this.$element_BotonConsultar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  947 */   public HTMLInputElement getElementBtnAyuda() { return this.$element_BtnAyuda; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  956 */   public HTMLInputElement getElementBtnConsultar() { return this.$element_BtnConsultar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  965 */   public HTMLInputElement getElementBtnCrear() { return this.$element_BtnCrear; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  974 */   public HTMLInputElement getElementBtnDescargarPlan() { return this.$element_BtnDescargarPlan; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  983 */   public HTMLInputElement getElementBtnDescargarPlanAccion() { return this.$element_BtnDescargarPlanAccion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  992 */   public HTMLInputElement getElementBtnDescargarPlanIndicativo() { return this.$element_BtnDescargarPlanIndicativo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1001 */   public HTMLInputElement getElementBtnDescargarPoa() { return this.$element_BtnDescargarPoa; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1010 */   public HTMLInputElement getElementBtnEliminar() { return this.$element_BtnEliminar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1019 */   public HTMLInputElement getElementBtnGrabar() { return this.$element_BtnGrabar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1028 */   public HTMLInputElement getElementBtnGrabarMetas() { return this.$element_BtnGrabarMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1037 */   public HTMLInputElement getElementBtnGrabarModal() { return this.$element_BtnGrabarModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1046 */   public HTMLInputElement getElementBtnGrabarModalCreacion() { return this.$element_BtnGrabarModalCreacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1055 */   public HTMLInputElement getElementBtnGuardaPlan() { return this.$element_BtnGuardaPlan; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1064 */   public HTMLInputElement getElementBtnModificar() { return this.$element_BtnModificar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1073 */   public HTMLInputElement getElementBtnSalir() { return this.$element_BtnSalir; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1082 */   public HTMLInputElement getElementBtnVolver() { return this.$element_BtnVolver; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1091 */   public HTMLInputElement getElementCantidad() { return this.$element_Cantidad; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1100 */   public HTMLElement getElementCantidadEd() { return this.$element_CantidadEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1109 */   public HTMLInputElement getElementCodigoMeta() { return this.$element_CodigoMeta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1118 */   public HTMLElement getElementCodigoMetaEd() { return this.$element_CodigoMetaEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1127 */   public HTMLInputElement getElementCodigoNivel() { return this.$element_CodigoNivel; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1136 */   public HTMLElement getElementCodigoNivelEd() { return this.$element_CodigoNivelEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1145 */   public HTMLSelectElement getElementCodigoUnidad() { return this.$element_CodigoUnidad; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1154 */   public HTMLElement getElementCodigoUnidadEd() { return this.$element_CodigoUnidadEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1163 */   public HTMLElement getElementCodigoUnidadEdModal() { return this.$element_CodigoUnidadEdModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1172 */   public HTMLInputElement getElementCodigoUnidadModal() { return this.$element_CodigoUnidadModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1181 */   public HTMLInputElement getElementCodigoUnidadModalCreacion() { return this.$element_CodigoUnidadModalCreacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1190 */   public HTMLDivElement getElementConsultaModal() { return this.$element_ConsultaModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1199 */   public HTMLDivElement getElementConsultaModalMetas() { return this.$element_ConsultaModalMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1208 */   public HTMLDivElement getElementCreacionModal() { return this.$element_CreacionModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1217 */   public HTMLDivElement getElementCreacionModalMetas() { return this.$element_CreacionModalMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1226 */   public HTMLSelectElement getElementCriterio1() { return this.$element_Criterio1; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1235 */   public HTMLSelectElement getElementCriterio1Todo() { return this.$element_Criterio1Todo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1244 */   public HTMLSelectElement getElementCriterio2() { return this.$element_Criterio2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1253 */   public HTMLSelectElement getElementCriterio2Todo() { return this.$element_Criterio2Todo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1262 */   public HTMLSelectElement getElementCriterio3() { return this.$element_Criterio3; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1271 */   public HTMLSelectElement getElementCriterio3Todo() { return this.$element_Criterio3Todo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1280 */   public HTMLSelectElement getElementCriterio4() { return this.$element_Criterio4; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1289 */   public HTMLSelectElement getElementCriterio4Todo() { return this.$element_Criterio4Todo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1298 */   public HTMLSelectElement getElementCriterio5() { return this.$element_Criterio5; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1307 */   public HTMLSelectElement getElementCriterio5Todo() { return this.$element_Criterio5Todo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1316 */   public HTMLSelectElement getElementCriterio6() { return this.$element_Criterio6; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1325 */   public HTMLSelectElement getElementCriterio6Todo() { return this.$element_Criterio6Todo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1334 */   public HTMLTableSectionElement getElementDetalle() { return this.$element_Detalle; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1343 */   public HTMLTableSectionElement getElementDetalle2() { return this.$element_Detalle2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1352 */   public HTMLTableSectionElement getElementDetalleEd() { return this.$element_DetalleEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1361 */   public HTMLTableSectionElement getElementDetalleMetas() { return this.$element_DetalleMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1370 */   public HTMLTableSectionElement getElementDetalleNiveles() { return this.$element_DetalleNiveles; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1379 */   public HTMLDivElement getElementDivConsulta() { return this.$element_DivConsulta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1388 */   public HTMLDivElement getElementDivCreacionRegistro() { return this.$element_DivCreacionRegistro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1397 */   public HTMLDivElement getElementDivEdicion() { return this.$element_DivEdicion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1406 */   public HTMLDivElement getElementDivResultados() { return this.$element_DivResultados; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1415 */   public HTMLDivElement getElementDivResultados2() { return this.$element_DivResultados2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1424 */   public HTMLDivElement getElementDivResultadosMetas() { return this.$element_DivResultadosMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1433 */   public HTMLDivElement getElementDivResultadosNiveles() { return this.$element_DivResultadosNiveles; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1442 */   public HTMLDivElement getElementEdicionModal() { return this.$element_EdicionModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1451 */   public HTMLTableCellElement getElementEjecutado() { return this.$element_Ejecutado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1460 */   public HTMLTableCellElement getElementEjecutadoEd() { return this.$element_EjecutadoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1469 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1478 */   public HTMLInputElement getElementFechaInsercion() { return this.$element_FechaInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1487 */   public HTMLElement getElementFechaInsercionEd() { return this.$element_FechaInsercionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1496 */   public HTMLElement getElementFechaInsercionEdMetas() { return this.$element_FechaInsercionEdMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1505 */   public HTMLElement getElementFechaInsercionEdModal() { return this.$element_FechaInsercionEdModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1514 */   public HTMLInputElement getElementFechaInsercionMetas() { return this.$element_FechaInsercionMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1523 */   public HTMLInputElement getElementFechaInsercionModal() { return this.$element_FechaInsercionModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1532 */   public HTMLInputElement getElementFechaInsercionModalCreacion() { return this.$element_FechaInsercionModalCreacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1541 */   public HTMLInputElement getElementFechaModificacion() { return this.$element_FechaModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1550 */   public HTMLElement getElementFechaModificacionEd() { return this.$element_FechaModificacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1559 */   public HTMLElement getElementFechaModificacionEdMetas() { return this.$element_FechaModificacionEdMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1568 */   public HTMLElement getElementFechaModificacionEdModal() { return this.$element_FechaModificacionEdModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1577 */   public HTMLInputElement getElementFechaModificacionMetas() { return this.$element_FechaModificacionMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1586 */   public HTMLInputElement getElementFechaModificacionModal() { return this.$element_FechaModificacionModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1595 */   public HTMLInputElement getElementFechaModificacionModalCreacion() { return this.$element_FechaModificacionModalCreacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1604 */   public HTMLTableSectionElement getElementIdDetalleAnio() { return this.$element_IdDetalleAnio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1613 */   public HTMLInputElement getElementIdMeta() { return this.$element_IdMeta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1622 */   public HTMLInputElement getElementIdNivel() { return this.$element_IdNivel; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1631 */   public HTMLElement getElementIdNivelEd() { return this.$element_IdNivelEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1640 */   public HTMLInputElement getElementIdNivelKey() { return this.$element_IdNivelKey; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1649 */   public HTMLInputElement getElementIdNivelKeyModal() { return this.$element_IdNivelKeyModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1658 */   public HTMLInputElement getElementIdPlanDesarrollo() { return this.$element_IdPlanDesarrollo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1667 */   public HTMLElement getElementIdPlanDesarrolloEd() { return this.$element_IdPlanDesarrolloEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1676 */   public HTMLInputElement getElementIdPlanDesarrolloKey() { return this.$element_IdPlanDesarrolloKey; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1685 */   public HTMLInputElement getElementIdPlanDesarrolloKeyMetas() { return this.$element_IdPlanDesarrolloKeyMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1694 */   public HTMLInputElement getElementIdPlanHidden() { return this.$element_IdPlanHidden; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1703 */   public HTMLInputElement getElementIdUnidadNivelKeyModal() { return this.$element_IdUnidadNivelKeyModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1712 */   public HTMLInputElement getElementIdUnidadNivelModal() { return this.$element_IdUnidadNivelModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1721 */   public HTMLInputElement getElementIdUnidadNivelModalCreacion() { return this.$element_IdUnidadNivelModalCreacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1730 */   public HTMLInputElement getElementIdUnidadSuperiorKeyModal() { return this.$element_IdUnidadSuperiorKeyModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1739 */   public HTMLInputElement getElementIndicador() { return this.$element_Indicador; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1748 */   public HTMLElement getElementIndicadorEd() { return this.$element_IndicadorEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1757 */   public HTMLLabelElement getElementLabelNombre() { return this.$element_LabelNombre; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1766 */   public HTMLInputElement getElementLineaBase() { return this.$element_LineaBase; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1775 */   public HTMLElement getElementLineaBaseEd() { return this.$element_LineaBaseEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1784 */   public HTMLInputElement getElementMetasEspecifico() { return this.$element_MetasEspecifico; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1793 */   public HTMLElement getElementMetasEspecificoEd() { return this.$element_MetasEspecificoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1802 */   public HTMLInputElement getElementMetasGeneral() { return this.$element_MetasGeneral; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1811 */   public HTMLElement getElementMetasGeneralEd() { return this.$element_MetasGeneralEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1820 */   public HTMLInputElement getElementNitEntidadHidden() { return this.$element_NitEntidadHidden; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1829 */   public HTMLInputElement getElementNivelSuperior() { return this.$element_NivelSuperior; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1838 */   public HTMLElement getElementNivelSuperiorEd() { return this.$element_NivelSuperiorEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1847 */   public HTMLInputElement getElementNivelSuperiorKey() { return this.$element_NivelSuperiorKey; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1856 */   public HTMLSelectElement getElementNivelSuperiorModalCreacion() { return this.$element_NivelSuperiorModalCreacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1865 */   public HTMLElement getElementNomUnidad() { return this.$element_NomUnidad; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1874 */   public HTMLElement getElementNomUnidad2() { return this.$element_NomUnidad2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1883 */   public HTMLInputElement getElementNombreMeta() { return this.$element_NombreMeta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1892 */   public HTMLElement getElementNombreMetaEd() { return this.$element_NombreMetaEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1901 */   public HTMLInputElement getElementNombreNivel() { return this.$element_NombreNivel; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1910 */   public HTMLElement getElementNombreNivelConsultaModal() { return this.$element_NombreNivelConsultaModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1919 */   public HTMLElement getElementNombreNivelCreacionModal() { return this.$element_NombreNivelCreacionModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1928 */   public HTMLElement getElementNombreNivelCreacionModalC() { return this.$element_NombreNivelCreacionModalC; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1937 */   public HTMLElement getElementNombreNivelEd() { return this.$element_NombreNivelEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1946 */   public HTMLElement getElementNombrePlanModal() { return this.$element_NombrePlanModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1955 */   public HTMLElement getElementNombrePlanModalEdicion() { return this.$element_NombrePlanModalEdicion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1964 */   public HTMLTableCellElement getElementNombreSiguienteNivel() { return this.$element_NombreSiguienteNivel; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1973 */   public HTMLElement getElementNombreUnidadEdModal() { return this.$element_NombreUnidadEdModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1982 */   public HTMLInputElement getElementNombreUnidadModal() { return this.$element_NombreUnidadModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1991 */   public HTMLInputElement getElementNombreUnidadModalCreacion() { return this.$element_NombreUnidadModalCreacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2000 */   public HTMLElement getElementNroRegistros() { return this.$element_NroRegistros; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2009 */   public HTMLElement getElementNroRegistrosMetas() { return this.$element_NroRegistrosMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2018 */   public HTMLElement getElementNroRegistrosNiveles() { return this.$element_NroRegistrosNiveles; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2027 */   public HTMLTableRowElement getElementObjGeneral() { return this.$element_ObjGeneral; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2036 */   public HTMLTableRowElement getElementObjGeneralCreacion() { return this.$element_ObjGeneralCreacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2045 */   public HTMLTableRowElement getElementObjGeneralEd() { return this.$element_ObjGeneralEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2054 */   public HTMLInputElement getElementObjetivoEspecifico() { return this.$element_ObjetivoEspecifico; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2063 */   public HTMLElement getElementObjetivoEspecificoEd() { return this.$element_ObjetivoEspecificoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2072 */   public HTMLInputElement getElementObjetivoGeneral() { return this.$element_ObjetivoGeneral; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2081 */   public HTMLElement getElementObjetivoGeneralEd() { return this.$element_ObjetivoGeneralEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2090 */   public HTMLElement getElementObjetivoGeneralEdModal() { return this.$element_ObjetivoGeneralEdModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2099 */   public HTMLInputElement getElementObjetivoGeneralModal() { return this.$element_ObjetivoGeneralModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2108 */   public HTMLInputElement getElementObjetivoGeneralModalCreacion() { return this.$element_ObjetivoGeneralModalCreacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2117 */   public HTMLTableCellElement getElementPorcentaje() { return this.$element_Porcentaje; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2126 */   public HTMLTableCellElement getElementPorcentajeEd() { return this.$element_PorcentajeEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2135 */   public HTMLSelectElement getElementProceso() { return this.$element_Proceso; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2144 */   public HTMLElement getElementProcesoEd() { return this.$element_ProcesoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2153 */   public HTMLTableCellElement getElementProgramado() { return this.$element_Programado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2162 */   public HTMLTableCellElement getElementProgramadoEd() { return this.$element_ProgramadoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2171 */   public HTMLScriptElement getElementResponsables() { return this.$element_Responsables; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2180 */   public HTMLScriptElement getElementScriptAreas() { return this.$element_ScriptAreas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2189 */   public HTMLScriptElement getElementScriptModal() { return this.$element_ScriptModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2198 */   public HTMLTableElement getElementTablaResponsables() { return this.$element_TablaResponsables; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2207 */   public HTMLTableElement getElementTblAnios() { return this.$element_TblAnios; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2216 */   public HTMLTableElement getElementTblAniosEd() { return this.$element_TblAniosEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2225 */   public HTMLSelectElement getElementTipoMeta() { return this.$element_TipoMeta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2234 */   public HTMLElement getElementTipoMetaEd() { return this.$element_TipoMetaEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2243 */   public HTMLInputElement getElementTipoNivel() { return this.$element_TipoNivel; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2252 */   public HTMLElement getElementTipoNivelEd() { return this.$element_TipoNivelEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2261 */   public HTMLSelectElement getElementTipoUnidad() { return this.$element_TipoUnidad; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2270 */   public HTMLTableRowElement getElementTr1() { return this.$element_Tr1; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2279 */   public HTMLTableRowElement getElementTr2() { return this.$element_Tr2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2288 */   public HTMLTableRowElement getElementTr3() { return this.$element_Tr3; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2297 */   public HTMLTableRowElement getElementTr4() { return this.$element_Tr4; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2306 */   public HTMLTableRowElement getElementTr5() { return this.$element_Tr5; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2315 */   public HTMLTableRowElement getElementTr6() { return this.$element_Tr6; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2324 */   public HTMLTableRowElement getElementTrProceso() { return this.$element_TrProceso; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2333 */   public HTMLTableRowElement getElementTrProcesoEd() { return this.$element_TrProcesoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2342 */   public HTMLTableRowElement getElementTrResponsable() { return this.$element_TrResponsable; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2351 */   public HTMLTableCellElement getElementTrResultNombre() { return this.$element_TrResultNombre; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2360 */   public HTMLTableCellElement getElementTrResultTareas() { return this.$element_TrResultTareas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2369 */   public HTMLTableRowElement getElementTrSeleccionNivel() { return this.$element_TrSeleccionNivel; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2378 */   public HTMLTableCellElement getElementTrimestre() { return this.$element_Trimestre; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2387 */   public HTMLTableCellElement getElementTrimestreEd() { return this.$element_TrimestreEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2396 */   public HTMLInputElement getElementUsuarioInsercion() { return this.$element_UsuarioInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2405 */   public HTMLElement getElementUsuarioInsercionEd() { return this.$element_UsuarioInsercionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2414 */   public HTMLElement getElementUsuarioInsercionEdMetas() { return this.$element_UsuarioInsercionEdMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2423 */   public HTMLElement getElementUsuarioInsercionEdModal() { return this.$element_UsuarioInsercionEdModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2432 */   public HTMLInputElement getElementUsuarioInsercionMetas() { return this.$element_UsuarioInsercionMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2441 */   public HTMLInputElement getElementUsuarioInsercionModal() { return this.$element_UsuarioInsercionModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2450 */   public HTMLInputElement getElementUsuarioInsercionModalCreacion() { return this.$element_UsuarioInsercionModalCreacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2459 */   public HTMLInputElement getElementUsuarioModificacion() { return this.$element_UsuarioModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2468 */   public HTMLElement getElementUsuarioModificacionEd() { return this.$element_UsuarioModificacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2477 */   public HTMLElement getElementUsuarioModificacionEdMetas() { return this.$element_UsuarioModificacionEdMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2486 */   public HTMLElement getElementUsuarioModificacionEdModal() { return this.$element_UsuarioModificacionEdModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2495 */   public HTMLInputElement getElementUsuarioModificacionMetas() { return this.$element_UsuarioModificacionMetas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2504 */   public HTMLInputElement getElementUsuarioModificacionModal() { return this.$element_UsuarioModificacionModal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2513 */   public HTMLInputElement getElementUsuarioModificacionModalCreacion() { return this.$element_UsuarioModificacionModalCreacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2522 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2531 */   public void setTextAnio(String text) { doSetText(this.$element_Anio, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2540 */   public void setTextAnioEd(String text) { doSetText(this.$element_AnioEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2549 */   public void setTextBotonConsultar(String text) { doSetText(this.$element_BotonConsultar, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2558 */   public void setTextCantidadEd(String text) { doSetText(this.$element_CantidadEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2567 */   public void setTextCodigoMetaEd(String text) { doSetText(this.$element_CodigoMetaEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2576 */   public void setTextCodigoNivelEd(String text) { doSetText(this.$element_CodigoNivelEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2585 */   public void setTextCodigoUnidadEd(String text) { doSetText(this.$element_CodigoUnidadEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2594 */   public void setTextCodigoUnidadEdModal(String text) { doSetText(this.$element_CodigoUnidadEdModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2603 */   public void setTextConsultaModal(String text) { doSetText(this.$element_ConsultaModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2612 */   public void setTextConsultaModalMetas(String text) { doSetText(this.$element_ConsultaModalMetas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2621 */   public void setTextCreacionModal(String text) { doSetText(this.$element_CreacionModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2630 */   public void setTextCreacionModalMetas(String text) { doSetText(this.$element_CreacionModalMetas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2639 */   public void setTextDivConsulta(String text) { doSetText(this.$element_DivConsulta, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2648 */   public void setTextDivCreacionRegistro(String text) { doSetText(this.$element_DivCreacionRegistro, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2657 */   public void setTextDivEdicion(String text) { doSetText(this.$element_DivEdicion, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2666 */   public void setTextDivResultados(String text) { doSetText(this.$element_DivResultados, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2675 */   public void setTextDivResultados2(String text) { doSetText(this.$element_DivResultados2, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2684 */   public void setTextDivResultadosMetas(String text) { doSetText(this.$element_DivResultadosMetas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2693 */   public void setTextDivResultadosNiveles(String text) { doSetText(this.$element_DivResultadosNiveles, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2702 */   public void setTextEdicionModal(String text) { doSetText(this.$element_EdicionModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2711 */   public void setTextEjecutado(String text) { doSetText(this.$element_Ejecutado, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2720 */   public void setTextEjecutadoEd(String text) { doSetText(this.$element_EjecutadoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2729 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2738 */   public void setTextFechaInsercionEd(String text) { doSetText(this.$element_FechaInsercionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2747 */   public void setTextFechaInsercionEdMetas(String text) { doSetText(this.$element_FechaInsercionEdMetas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2756 */   public void setTextFechaInsercionEdModal(String text) { doSetText(this.$element_FechaInsercionEdModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2765 */   public void setTextFechaModificacionEd(String text) { doSetText(this.$element_FechaModificacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2774 */   public void setTextFechaModificacionEdMetas(String text) { doSetText(this.$element_FechaModificacionEdMetas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2783 */   public void setTextFechaModificacionEdModal(String text) { doSetText(this.$element_FechaModificacionEdModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2792 */   public void setTextIdNivelEd(String text) { doSetText(this.$element_IdNivelEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2801 */   public void setTextIdPlanDesarrolloEd(String text) { doSetText(this.$element_IdPlanDesarrolloEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2810 */   public void setTextIndicadorEd(String text) { doSetText(this.$element_IndicadorEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2819 */   public void setTextLabelNombre(String text) { doSetText(this.$element_LabelNombre, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2828 */   public void setTextLineaBaseEd(String text) { doSetText(this.$element_LineaBaseEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2837 */   public void setTextMetasEspecificoEd(String text) { doSetText(this.$element_MetasEspecificoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2846 */   public void setTextMetasGeneralEd(String text) { doSetText(this.$element_MetasGeneralEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2855 */   public void setTextNivelSuperiorEd(String text) { doSetText(this.$element_NivelSuperiorEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2864 */   public void setTextNomUnidad(String text) { doSetText(this.$element_NomUnidad, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2873 */   public void setTextNomUnidad2(String text) { doSetText(this.$element_NomUnidad2, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2882 */   public void setTextNombreMetaEd(String text) { doSetText(this.$element_NombreMetaEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2891 */   public void setTextNombreNivelConsultaModal(String text) { doSetText(this.$element_NombreNivelConsultaModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2900 */   public void setTextNombreNivelCreacionModal(String text) { doSetText(this.$element_NombreNivelCreacionModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2909 */   public void setTextNombreNivelCreacionModalC(String text) { doSetText(this.$element_NombreNivelCreacionModalC, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2918 */   public void setTextNombreNivelEd(String text) { doSetText(this.$element_NombreNivelEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2927 */   public void setTextNombrePlanModal(String text) { doSetText(this.$element_NombrePlanModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2936 */   public void setTextNombrePlanModalEdicion(String text) { doSetText(this.$element_NombrePlanModalEdicion, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2945 */   public void setTextNombreSiguienteNivel(String text) { doSetText(this.$element_NombreSiguienteNivel, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2954 */   public void setTextNombreUnidadEdModal(String text) { doSetText(this.$element_NombreUnidadEdModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2963 */   public void setTextNroRegistros(String text) { doSetText(this.$element_NroRegistros, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2972 */   public void setTextNroRegistrosMetas(String text) { doSetText(this.$element_NroRegistrosMetas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2981 */   public void setTextNroRegistrosNiveles(String text) { doSetText(this.$element_NroRegistrosNiveles, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2990 */   public void setTextObjetivoEspecificoEd(String text) { doSetText(this.$element_ObjetivoEspecificoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2999 */   public void setTextObjetivoGeneralEd(String text) { doSetText(this.$element_ObjetivoGeneralEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3008 */   public void setTextObjetivoGeneralEdModal(String text) { doSetText(this.$element_ObjetivoGeneralEdModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3017 */   public void setTextPorcentaje(String text) { doSetText(this.$element_Porcentaje, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3026 */   public void setTextPorcentajeEd(String text) { doSetText(this.$element_PorcentajeEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3035 */   public void setTextProcesoEd(String text) { doSetText(this.$element_ProcesoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3044 */   public void setTextProgramado(String text) { doSetText(this.$element_Programado, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3053 */   public void setTextProgramadoEd(String text) { doSetText(this.$element_ProgramadoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3062 */   public void setTextResponsables(String text) { doSetText(this.$element_Responsables, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3071 */   public void setTextScriptAreas(String text) { doSetText(this.$element_ScriptAreas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3080 */   public void setTextScriptModal(String text) { doSetText(this.$element_ScriptModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3089 */   public void setTextTipoMetaEd(String text) { doSetText(this.$element_TipoMetaEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3098 */   public void setTextTipoNivelEd(String text) { doSetText(this.$element_TipoNivelEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3107 */   public void setTextTrResultNombre(String text) { doSetText(this.$element_TrResultNombre, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3116 */   public void setTextTrResultTareas(String text) { doSetText(this.$element_TrResultTareas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3125 */   public void setTextTrimestre(String text) { doSetText(this.$element_Trimestre, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3134 */   public void setTextTrimestreEd(String text) { doSetText(this.$element_TrimestreEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3143 */   public void setTextUsuarioInsercionEd(String text) { doSetText(this.$element_UsuarioInsercionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3152 */   public void setTextUsuarioInsercionEdMetas(String text) { doSetText(this.$element_UsuarioInsercionEdMetas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3161 */   public void setTextUsuarioInsercionEdModal(String text) { doSetText(this.$element_UsuarioInsercionEdModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3170 */   public void setTextUsuarioModificacionEd(String text) { doSetText(this.$element_UsuarioModificacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3179 */   public void setTextUsuarioModificacionEdMetas(String text) { doSetText(this.$element_UsuarioModificacionEdMetas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 3188 */   public void setTextUsuarioModificacionEdModal(String text) { doSetText(this.$element_UsuarioModificacionEdModal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void syncWithDocument(Node node) {
/* 3197 */     if (node.getNodeType() != 9)
/*      */     {
/* 3199 */       throw new XMLCRuntimeException("Node must be a document node");
/*      */     }
/*      */ 
/*      */     
/* 3203 */     Document doc = (Document)node;
/*      */ 
/*      */     
/*      */     try {
/* 3207 */       Field[] fs = getClass().getDeclaredFields();
/*      */       
/* 3209 */       int substStart = "$element_".length();
/*      */       
/* 3211 */       for (int i = 0; i < fs.length; i++) {
/*      */         
/* 3213 */         Field f = fs[i];
/*      */         
/* 3215 */         if (f.getName().startsWith("$element_")) {
/*      */           
/* 3217 */           String id = f.getName().substring(substStart);
/*      */           
/* 3219 */           Node idNode = doc.getElementById(id);
/*      */           
/* 3221 */           if (idNode == null) {
/*      */             
/* 3223 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*      */             
/* 3225 */             idNode = doc.getElementById(id);
/*      */           } 
/*      */ 
/*      */           
/* 3229 */           if (idNode != null) f.set(this, idNode);
/*      */         
/*      */         } 
/*      */       } 
/* 3233 */     } catch (Exception e) {
/*      */       
/* 3235 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PdeNivelPlanHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */