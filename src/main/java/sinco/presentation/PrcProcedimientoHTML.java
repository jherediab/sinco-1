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
/*      */ import org.w3c.dom.html.HTMLTableSectionElement;
/*      */ import org.w3c.dom.html.HTMLTextAreaElement;
/*      */ import sinco.presentation.PrcProcedimientoHTML;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PrcProcedimientoHTML
/*      */   extends HTMLObjectImpl
/*      */   implements XMLObject, HTMLObject
/*      */ {
/*      */   private HTMLInputElement $element_ActuarCant;
/*      */   private HTMLTextAreaElement $element_Alcance;
/*      */   private HTMLElement $element_AlcanceEd;
/*      */   private HTMLInputElement $element_AnterioresCant;
/*      */   private HTMLSelectElement $element_AnterioresText;
/*      */   private HTMLInputElement $element_BotonAgregarAnteriores;
/*      */   private HTMLInputElement $element_BotonAgregarClientes;
/*      */   private HTMLInputElement $element_BotonAgregarDescAct;
/*      */   private HTMLInputElement $element_BotonAgregarDescHac;
/*      */   private HTMLInputElement $element_BotonAgregarDescPlan;
/*      */   private HTMLInputElement $element_BotonAgregarDescVer;
/*      */   private HTMLInputElement $element_BotonAgregarPolitica;
/*      */   private HTMLInputElement $element_BotonAgregarProveedores;
/*      */   private HTMLInputElement $element_BotonAgregarRecurso;
/*      */   private HTMLInputElement $element_BtnAyuda;
/*      */   private HTMLInputElement $element_BtnConsultar;
/*      */   private HTMLInputElement $element_BtnCrear;
/*      */   private HTMLInputElement $element_BtnEliminar;
/*      */   private HTMLInputElement $element_BtnGrabar;
/*      */   private HTMLInputElement $element_BtnModificar;
/*      */   private HTMLInputElement $element_BtnSalir;
/*      */   private HTMLSelectElement $element_Clientes;
/*      */   private HTMLInputElement $element_ClientesCant;
/*      */   private HTMLSelectElement $element_CodigoEmpleado;
/*      */   private HTMLSelectElement $element_CodigoEmpleadoDescAct;
/*      */   private HTMLSelectElement $element_CodigoEmpleadoDescHac;
/*      */   private HTMLSelectElement $element_CodigoEmpleadoDescPlan;
/*      */   private HTMLSelectElement $element_CodigoEmpleadoDescVer;
/*      */   private HTMLElement $element_CodigoEmpleadoEd;
/*      */   private HTMLTextAreaElement $element_Concepto;
/*      */   private HTMLElement $element_ConceptoEd;
/*      */   private HTMLTextAreaElement $element_Definiciones;
/*      */   private HTMLElement $element_DefinicionesEd;
/*      */   private HTMLInputElement $element_DescRecurso;
/*      */   private HTMLInputElement $element_DescRecursoCant;
/*      */   private HTMLInputElement $element_DescripcionProcAct;
/*      */   private HTMLInputElement $element_DescripcionProcHac;
/*      */   private HTMLInputElement $element_DescripcionProcPlan;
/*      */   private HTMLInputElement $element_DescripcionProcVer;
/*      */   private HTMLTableSectionElement $element_Detalle;
/*      */   private HTMLDivElement $element_DivConsulta;
/*      */   private HTMLDivElement $element_DivCreacionRegistro;
/*      */   private HTMLDivElement $element_DivEdicion;
/*      */   private HTMLDivElement $element_DivResultados;
/*      */   private HTMLScriptElement $element_ElMenu;
/*      */   private HTMLSelectElement $element_Entradas;
/*      */   private HTMLInputElement $element_EntradasCant;
/*      */   private HTMLSelectElement $element_Estado;
/*      */   private HTMLElement $element_EstadoEd;
/*      */   private HTMLTextAreaElement $element_Falcance;
/*      */   private HTMLSelectElement $element_FcodigoEmpleado;
/*      */   private HTMLInputElement $element_FechaInsercion;
/*      */   private HTMLElement $element_FechaInsercionEd;
/*      */   private HTMLInputElement $element_FechaModificacion;
/*      */   private HTMLElement $element_FechaModificacionEd;
/*      */   private HTMLTextAreaElement $element_Fobjetivo;
/*      */   private HTMLInputElement $element_HacerCant;
/*      */   private HTMLInputElement $element_IdProcedimiento;
/*      */   private HTMLElement $element_IdProcedimientoEd;
/*      */   private HTMLInputElement $element_IdProcedimientoKey;
/*      */   private HTMLElement $element_NroRegistros;
/*      */   private HTMLTextAreaElement $element_Objetivo;
/*      */   private HTMLElement $element_ObjetivoEd;
/*      */   private HTMLInputElement $element_PlanearCant;
/*      */   private HTMLTextAreaElement $element_PoliticaText;
/*      */   private HTMLInputElement $element_PoliticasCant;
/*      */   private HTMLInputElement $element_ProcesoId;
/*      */   private HTMLElement $element_ProcesoIdEd;
/*      */   private HTMLSelectElement $element_Proveedores;
/*      */   private HTMLInputElement $element_ProveedoresCant;
/*      */   private HTMLInputElement $element_RecursoCant;
/*      */   private HTMLInputElement $element_RegiActuarCant;
/*      */   private HTMLInputElement $element_RegiHacerCant;
/*      */   private HTMLInputElement $element_RegiPlanearCant;
/*      */   private HTMLInputElement $element_RegiVerificarCant;
/*      */   private HTMLInputElement $element_RegistroAct;
/*      */   private HTMLInputElement $element_RegistroHac;
/*      */   private HTMLInputElement $element_RegistroPlan;
/*      */   private HTMLInputElement $element_RegistroVer;
/*      */   private HTMLInputElement $element_RespActuarCant;
/*      */   private HTMLInputElement $element_RespHacerCant;
/*      */   private HTMLInputElement $element_RespPlanearCant;
/*      */   private HTMLInputElement $element_RespVerificarCant;
/*      */   private HTMLSelectElement $element_Salidas;
/*      */   private HTMLInputElement $element_SalidasCant;
/*      */   private HTMLScriptElement $element_ScriptCarga;
/*      */   private HTMLInputElement $element_ServicioUnidadId;
/*      */   private HTMLElement $element_ServicioUnidadIdEd;
/*      */   private HTMLInputElement $element_SubprocesoId;
/*      */   private HTMLElement $element_SubprocesoIdEd;
/*      */   private HTMLTableElement $element_TablaActualText;
/*      */   private HTMLTableElement $element_TablaAnterioresText;
/*      */   private HTMLTableElement $element_TablaClientesText;
/*      */   private HTMLTableElement $element_TablaHacerText;
/*      */   private HTMLTableElement $element_TablaPlanearText;
/*      */   private HTMLTableElement $element_TablaPoliticaText;
/*      */   private HTMLTableElement $element_TablaProveedorText;
/*      */   private HTMLTableElement $element_TablaRecursosText;
/*      */   private HTMLTableElement $element_TablaVerificarText;
/*      */   private HTMLSelectElement $element_TipoRecurso;
/*      */   private HTMLInputElement $element_UsuarioInsercion;
/*      */   private HTMLElement $element_UsuarioInsercionEd;
/*      */   private HTMLInputElement $element_UsuarioModificacion;
/*      */   private HTMLElement $element_UsuarioModificacionEd;
/*      */   private HTMLInputElement $element_VerificarCant;
/*      */   private HTMLInputElement $element__operacion;
/*      */   public static final String CLASS_BOT = "BOT";
/*      */   public static final String CLASS_PIE = "PIE";
/*      */   public static final String CLASS_btn = "btn";
/*      */   public static final String CLASS_ca = "ca";
/*      */   public static final String CLASS_cb = "cb";
/*      */   public static final String CLASS_cf1 = "cf1";
/*      */   public static final String CLASS_cf2 = "cf2";
/*      */   public static final String CLASS_container = "container";
/*      */   public static final String CLASS_dat = "dat";
/*      */   public static final String CLASS_dat2 = "dat2";
/*      */   public static final String CLASS_panel = "panel";
/*      */   public static final String CLASS_row = "row";
/*      */   public static final String CLASS_tabf = "tabf";
/*      */   public static final String CLASS_table = "table";
/*      */   public static final String CLASS_tabw = "tabw";
/*      */   public static final String CLASS_tit = "tit";
/*      */   public static final String NAME_ActuarCant = "ActuarCant";
/*      */   public static final String NAME_AnterioresCant = "AnterioresCant";
/*      */   public static final String NAME_AnterioresText = "AnterioresText";
/*      */   public static final String NAME_Clientes = "Clientes";
/*      */   public static final String NAME_ClientesCant = "ClientesCant";
/*      */   public static final String NAME_DescRecurso = "DescRecurso";
/*      */   public static final String NAME_DescRecursoCant = "DescRecursoCant";
/*      */   public static final String NAME_DescripcionProcAct = "DescripcionProcAct";
/*      */   public static final String NAME_DescripcionProcHac = "DescripcionProcHac";
/*      */   public static final String NAME_DescripcionProcPlan = "DescripcionProcPlan";
/*      */   public static final String NAME_DescripcionProcVer = "DescripcionProcVer";
/*      */   public static final String NAME_Entradas = "Entradas";
/*      */   public static final String NAME_EntradasCant = "EntradasCant";
/*      */   public static final String NAME_HacerCant = "HacerCant";
/*      */   public static final String NAME_PlanearCant = "PlanearCant";
/*      */   public static final String NAME_PoliticaText = "PoliticaText";
/*      */   public static final String NAME_PoliticasCant = "PoliticasCant";
/*      */   public static final String NAME_Proveedores = "Proveedores";
/*      */   public static final String NAME_ProveedoresCant = "ProveedoresCant";
/*      */   public static final String NAME_RecursoCant = "RecursoCant";
/*      */   public static final String NAME_RegiActuarCant = "RegiActuarCant";
/*      */   public static final String NAME_RegiHacerCant = "RegiHacerCant";
/*      */   public static final String NAME_RegiPlanearCant = "RegiPlanearCant";
/*      */   public static final String NAME_RegiVerificarCant = "RegiVerificarCant";
/*      */   public static final String NAME_RegistroAct = "RegistroAct";
/*      */   public static final String NAME_RegistroHac = "RegistroHac";
/*      */   public static final String NAME_RegistroPlan = "RegistroPlan";
/*      */   public static final String NAME_RegistroVer = "RegistroVer";
/*      */   public static final String NAME_RespActuarCant = "RespActuarCant";
/*      */   public static final String NAME_RespHacerCant = "RespHacerCant";
/*      */   public static final String NAME_RespPlanearCant = "RespPlanearCant";
/*      */   public static final String NAME_RespVerificarCant = "RespVerificarCant";
/*      */   public static final String NAME_Salidas = "Salidas";
/*      */   public static final String NAME_SalidasCant = "SalidasCant";
/*      */   public static final String NAME_VerificarCant = "VerificarCant";
/*      */   public static final String NAME__operacion = "_operacion";
/*      */   public static final String NAME_alcance = "alcance";
/*      */   public static final String NAME_botonAgregarAnteriores = "botonAgregarAnteriores";
/*      */   public static final String NAME_botonAgregarClientes = "botonAgregarClientes";
/*      */   public static final String NAME_botonAgregarDescAct = "botonAgregarDescAct";
/*      */   public static final String NAME_botonAgregarDescHac = "botonAgregarDescHac";
/*      */   public static final String NAME_botonAgregarDescPlan = "botonAgregarDescPlan";
/*      */   public static final String NAME_botonAgregarDescVer = "botonAgregarDescVer";
/*      */   public static final String NAME_botonAgregarPolitica = "botonAgregarPolitica";
/*      */   public static final String NAME_botonAgregarProveedores = "botonAgregarProveedores";
/*      */   public static final String NAME_botonAgregarRecurso = "botonAgregarRecurso";
/*      */   public static final String NAME_codigoEmpleado = "codigoEmpleado";
/*      */   public static final String NAME_codigoEmpleadoDescAct = "codigoEmpleadoDescAct";
/*      */   public static final String NAME_codigoEmpleadoDescHac = "codigoEmpleadoDescHac";
/*      */   public static final String NAME_codigoEmpleadoDescPlan = "codigoEmpleadoDescPlan";
/*      */   public static final String NAME_codigoEmpleadoDescVer = "codigoEmpleadoDescVer";
/*      */   public static final String NAME_concepto = "concepto";
/*      */   public static final String NAME_definiciones = "definiciones";
/*      */   public static final String NAME_estado = "estado";
/*      */   public static final String NAME_fechaInsercion = "fechaInsercion";
/*      */   public static final String NAME_fechaModificacion = "fechaModificacion";
/*      */   public static final String NAME_ff = "ff";
/*      */   public static final String NAME_idProcedimiento = "idProcedimiento";
/*      */   public static final String NAME_objetivo = "objetivo";
/*      */   public static final String NAME_procesoId = "procesoId";
/*      */   public static final String NAME_servicioUnidadId = "servicioUnidadId";
/*      */   public static final String NAME_subprocesoId = "subprocesoId";
/*      */   public static final String NAME_tipoRecurso = "tipoRecurso";
/*      */   public static final String NAME_txtCont10 = "txtCont10";
/*      */   public static final String NAME_txtCont13 = "txtCont13";
/*      */   public static final String NAME_txtCont4 = "txtCont4";
/*      */   public static final String NAME_txtCont8 = "txtCont8";
/*      */   public static final String NAME_txtCont9 = "txtCont9";
/*      */   public static final String NAME_usuarioInsercion = "usuarioInsercion";
/*      */   public static final String NAME_usuarioModificacion = "usuarioModificacion";
/*  663 */   public static final Class XMLC_GENERATED_CLASS = PrcProcedimientoHTML.class;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/PrcProcedimiento.html";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  674 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final DocumentLoader fDocumentLoader;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PrcProcedimientoHTML() {
/*  685 */     this(StandardDocumentLoader.getInstance());
/*      */     
/*  687 */     buildDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  695 */   public PrcProcedimientoHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PrcProcedimientoHTML(PrcProcedimientoHTML src) {
/*  703 */     this.fDocumentLoader = src.getDocumentLoader();
/*      */     
/*  705 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*      */     
/*  707 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PrcProcedimientoHTML(DocumentLoader loader, boolean buildDOM) {
/*  718 */     this.fDocumentLoader = loader;
/*      */     
/*  720 */     if (buildDOM)
/*      */     {
/*  722 */       buildDocument();
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
/*  734 */   public PrcProcedimientoHTML(DocumentLoader loader) { this(loader, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void buildDocument() {
/*  742 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*      */     
/*  744 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node cloneNode(boolean deep) {
/*  752 */     cloneDeepCheck(deep);
/*      */     
/*  754 */     return new PrcProcedimientoHTML(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  762 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  770 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  779 */   public HTMLInputElement getElementActuarCant() { return this.$element_ActuarCant; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  788 */   public HTMLTextAreaElement getElementAlcance() { return this.$element_Alcance; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  797 */   public HTMLElement getElementAlcanceEd() { return this.$element_AlcanceEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  806 */   public HTMLInputElement getElementAnterioresCant() { return this.$element_AnterioresCant; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  815 */   public HTMLSelectElement getElementAnterioresText() { return this.$element_AnterioresText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  824 */   public HTMLInputElement getElementBotonAgregarAnteriores() { return this.$element_BotonAgregarAnteriores; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  833 */   public HTMLInputElement getElementBotonAgregarClientes() { return this.$element_BotonAgregarClientes; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  842 */   public HTMLInputElement getElementBotonAgregarDescAct() { return this.$element_BotonAgregarDescAct; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  851 */   public HTMLInputElement getElementBotonAgregarDescHac() { return this.$element_BotonAgregarDescHac; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  860 */   public HTMLInputElement getElementBotonAgregarDescPlan() { return this.$element_BotonAgregarDescPlan; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  869 */   public HTMLInputElement getElementBotonAgregarDescVer() { return this.$element_BotonAgregarDescVer; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  878 */   public HTMLInputElement getElementBotonAgregarPolitica() { return this.$element_BotonAgregarPolitica; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  887 */   public HTMLInputElement getElementBotonAgregarProveedores() { return this.$element_BotonAgregarProveedores; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  896 */   public HTMLInputElement getElementBotonAgregarRecurso() { return this.$element_BotonAgregarRecurso; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  905 */   public HTMLInputElement getElementBtnAyuda() { return this.$element_BtnAyuda; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  914 */   public HTMLInputElement getElementBtnConsultar() { return this.$element_BtnConsultar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  923 */   public HTMLInputElement getElementBtnCrear() { return this.$element_BtnCrear; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  932 */   public HTMLInputElement getElementBtnEliminar() { return this.$element_BtnEliminar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  941 */   public HTMLInputElement getElementBtnGrabar() { return this.$element_BtnGrabar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  950 */   public HTMLInputElement getElementBtnModificar() { return this.$element_BtnModificar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  959 */   public HTMLInputElement getElementBtnSalir() { return this.$element_BtnSalir; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  968 */   public HTMLSelectElement getElementClientes() { return this.$element_Clientes; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  977 */   public HTMLInputElement getElementClientesCant() { return this.$element_ClientesCant; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  986 */   public HTMLSelectElement getElementCodigoEmpleado() { return this.$element_CodigoEmpleado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  995 */   public HTMLSelectElement getElementCodigoEmpleadoDescAct() { return this.$element_CodigoEmpleadoDescAct; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1004 */   public HTMLSelectElement getElementCodigoEmpleadoDescHac() { return this.$element_CodigoEmpleadoDescHac; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1013 */   public HTMLSelectElement getElementCodigoEmpleadoDescPlan() { return this.$element_CodigoEmpleadoDescPlan; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1022 */   public HTMLSelectElement getElementCodigoEmpleadoDescVer() { return this.$element_CodigoEmpleadoDescVer; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1031 */   public HTMLElement getElementCodigoEmpleadoEd() { return this.$element_CodigoEmpleadoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1040 */   public HTMLTextAreaElement getElementConcepto() { return this.$element_Concepto; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1049 */   public HTMLElement getElementConceptoEd() { return this.$element_ConceptoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1058 */   public HTMLTextAreaElement getElementDefiniciones() { return this.$element_Definiciones; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1067 */   public HTMLElement getElementDefinicionesEd() { return this.$element_DefinicionesEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1076 */   public HTMLInputElement getElementDescRecurso() { return this.$element_DescRecurso; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1085 */   public HTMLInputElement getElementDescRecursoCant() { return this.$element_DescRecursoCant; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1094 */   public HTMLInputElement getElementDescripcionProcAct() { return this.$element_DescripcionProcAct; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1103 */   public HTMLInputElement getElementDescripcionProcHac() { return this.$element_DescripcionProcHac; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1112 */   public HTMLInputElement getElementDescripcionProcPlan() { return this.$element_DescripcionProcPlan; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1121 */   public HTMLInputElement getElementDescripcionProcVer() { return this.$element_DescripcionProcVer; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1130 */   public HTMLTableSectionElement getElementDetalle() { return this.$element_Detalle; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1139 */   public HTMLDivElement getElementDivConsulta() { return this.$element_DivConsulta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1148 */   public HTMLDivElement getElementDivCreacionRegistro() { return this.$element_DivCreacionRegistro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1157 */   public HTMLDivElement getElementDivEdicion() { return this.$element_DivEdicion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1166 */   public HTMLDivElement getElementDivResultados() { return this.$element_DivResultados; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1175 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1184 */   public HTMLSelectElement getElementEntradas() { return this.$element_Entradas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1193 */   public HTMLInputElement getElementEntradasCant() { return this.$element_EntradasCant; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1202 */   public HTMLSelectElement getElementEstado() { return this.$element_Estado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1211 */   public HTMLElement getElementEstadoEd() { return this.$element_EstadoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1220 */   public HTMLTextAreaElement getElementFalcance() { return this.$element_Falcance; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1229 */   public HTMLSelectElement getElementFcodigoEmpleado() { return this.$element_FcodigoEmpleado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1238 */   public HTMLInputElement getElementFechaInsercion() { return this.$element_FechaInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1247 */   public HTMLElement getElementFechaInsercionEd() { return this.$element_FechaInsercionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1256 */   public HTMLInputElement getElementFechaModificacion() { return this.$element_FechaModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1265 */   public HTMLElement getElementFechaModificacionEd() { return this.$element_FechaModificacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1274 */   public HTMLTextAreaElement getElementFobjetivo() { return this.$element_Fobjetivo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1283 */   public HTMLInputElement getElementHacerCant() { return this.$element_HacerCant; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1292 */   public HTMLInputElement getElementIdProcedimiento() { return this.$element_IdProcedimiento; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1301 */   public HTMLElement getElementIdProcedimientoEd() { return this.$element_IdProcedimientoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1310 */   public HTMLInputElement getElementIdProcedimientoKey() { return this.$element_IdProcedimientoKey; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1319 */   public HTMLElement getElementNroRegistros() { return this.$element_NroRegistros; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1328 */   public HTMLTextAreaElement getElementObjetivo() { return this.$element_Objetivo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1337 */   public HTMLElement getElementObjetivoEd() { return this.$element_ObjetivoEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1346 */   public HTMLInputElement getElementPlanearCant() { return this.$element_PlanearCant; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1355 */   public HTMLTextAreaElement getElementPoliticaText() { return this.$element_PoliticaText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1364 */   public HTMLInputElement getElementPoliticasCant() { return this.$element_PoliticasCant; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1373 */   public HTMLInputElement getElementProcesoId() { return this.$element_ProcesoId; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1382 */   public HTMLElement getElementProcesoIdEd() { return this.$element_ProcesoIdEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1391 */   public HTMLSelectElement getElementProveedores() { return this.$element_Proveedores; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1400 */   public HTMLInputElement getElementProveedoresCant() { return this.$element_ProveedoresCant; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1409 */   public HTMLInputElement getElementRecursoCant() { return this.$element_RecursoCant; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1418 */   public HTMLInputElement getElementRegiActuarCant() { return this.$element_RegiActuarCant; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1427 */   public HTMLInputElement getElementRegiHacerCant() { return this.$element_RegiHacerCant; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1436 */   public HTMLInputElement getElementRegiPlanearCant() { return this.$element_RegiPlanearCant; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1445 */   public HTMLInputElement getElementRegiVerificarCant() { return this.$element_RegiVerificarCant; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1454 */   public HTMLInputElement getElementRegistroAct() { return this.$element_RegistroAct; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1463 */   public HTMLInputElement getElementRegistroHac() { return this.$element_RegistroHac; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1472 */   public HTMLInputElement getElementRegistroPlan() { return this.$element_RegistroPlan; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1481 */   public HTMLInputElement getElementRegistroVer() { return this.$element_RegistroVer; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1490 */   public HTMLInputElement getElementRespActuarCant() { return this.$element_RespActuarCant; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1499 */   public HTMLInputElement getElementRespHacerCant() { return this.$element_RespHacerCant; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1508 */   public HTMLInputElement getElementRespPlanearCant() { return this.$element_RespPlanearCant; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1517 */   public HTMLInputElement getElementRespVerificarCant() { return this.$element_RespVerificarCant; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1526 */   public HTMLSelectElement getElementSalidas() { return this.$element_Salidas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1535 */   public HTMLInputElement getElementSalidasCant() { return this.$element_SalidasCant; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1544 */   public HTMLScriptElement getElementScriptCarga() { return this.$element_ScriptCarga; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1553 */   public HTMLInputElement getElementServicioUnidadId() { return this.$element_ServicioUnidadId; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1562 */   public HTMLElement getElementServicioUnidadIdEd() { return this.$element_ServicioUnidadIdEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1571 */   public HTMLInputElement getElementSubprocesoId() { return this.$element_SubprocesoId; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1580 */   public HTMLElement getElementSubprocesoIdEd() { return this.$element_SubprocesoIdEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1589 */   public HTMLTableElement getElementTablaActualText() { return this.$element_TablaActualText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1598 */   public HTMLTableElement getElementTablaAnterioresText() { return this.$element_TablaAnterioresText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1607 */   public HTMLTableElement getElementTablaClientesText() { return this.$element_TablaClientesText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1616 */   public HTMLTableElement getElementTablaHacerText() { return this.$element_TablaHacerText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1625 */   public HTMLTableElement getElementTablaPlanearText() { return this.$element_TablaPlanearText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1634 */   public HTMLTableElement getElementTablaPoliticaText() { return this.$element_TablaPoliticaText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1643 */   public HTMLTableElement getElementTablaProveedorText() { return this.$element_TablaProveedorText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1652 */   public HTMLTableElement getElementTablaRecursosText() { return this.$element_TablaRecursosText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1661 */   public HTMLTableElement getElementTablaVerificarText() { return this.$element_TablaVerificarText; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1670 */   public HTMLSelectElement getElementTipoRecurso() { return this.$element_TipoRecurso; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1679 */   public HTMLInputElement getElementUsuarioInsercion() { return this.$element_UsuarioInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1688 */   public HTMLElement getElementUsuarioInsercionEd() { return this.$element_UsuarioInsercionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1697 */   public HTMLInputElement getElementUsuarioModificacion() { return this.$element_UsuarioModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1706 */   public HTMLElement getElementUsuarioModificacionEd() { return this.$element_UsuarioModificacionEd; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1715 */   public HTMLInputElement getElementVerificarCant() { return this.$element_VerificarCant; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1724 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1733 */   public void setTextAlcance(String text) { doSetText(this.$element_Alcance, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1742 */   public void setTextAlcanceEd(String text) { doSetText(this.$element_AlcanceEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1751 */   public void setTextCodigoEmpleadoEd(String text) { doSetText(this.$element_CodigoEmpleadoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1760 */   public void setTextConcepto(String text) { doSetText(this.$element_Concepto, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1769 */   public void setTextConceptoEd(String text) { doSetText(this.$element_ConceptoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1778 */   public void setTextDefiniciones(String text) { doSetText(this.$element_Definiciones, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1787 */   public void setTextDefinicionesEd(String text) { doSetText(this.$element_DefinicionesEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1796 */   public void setTextDivConsulta(String text) { doSetText(this.$element_DivConsulta, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1805 */   public void setTextDivCreacionRegistro(String text) { doSetText(this.$element_DivCreacionRegistro, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1814 */   public void setTextDivEdicion(String text) { doSetText(this.$element_DivEdicion, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1823 */   public void setTextDivResultados(String text) { doSetText(this.$element_DivResultados, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1832 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1841 */   public void setTextEstadoEd(String text) { doSetText(this.$element_EstadoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1850 */   public void setTextFalcance(String text) { doSetText(this.$element_Falcance, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1859 */   public void setTextFechaInsercionEd(String text) { doSetText(this.$element_FechaInsercionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1868 */   public void setTextFechaModificacionEd(String text) { doSetText(this.$element_FechaModificacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1877 */   public void setTextFobjetivo(String text) { doSetText(this.$element_Fobjetivo, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1886 */   public void setTextIdProcedimientoEd(String text) { doSetText(this.$element_IdProcedimientoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1895 */   public void setTextNroRegistros(String text) { doSetText(this.$element_NroRegistros, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1904 */   public void setTextObjetivo(String text) { doSetText(this.$element_Objetivo, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1913 */   public void setTextObjetivoEd(String text) { doSetText(this.$element_ObjetivoEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1922 */   public void setTextPoliticaText(String text) { doSetText(this.$element_PoliticaText, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1931 */   public void setTextProcesoIdEd(String text) { doSetText(this.$element_ProcesoIdEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1940 */   public void setTextScriptCarga(String text) { doSetText(this.$element_ScriptCarga, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1949 */   public void setTextServicioUnidadIdEd(String text) { doSetText(this.$element_ServicioUnidadIdEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1958 */   public void setTextSubprocesoIdEd(String text) { doSetText(this.$element_SubprocesoIdEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1967 */   public void setTextUsuarioInsercionEd(String text) { doSetText(this.$element_UsuarioInsercionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1976 */   public void setTextUsuarioModificacionEd(String text) { doSetText(this.$element_UsuarioModificacionEd, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void syncWithDocument(Node node) {
/* 1985 */     if (node.getNodeType() != 9)
/*      */     {
/* 1987 */       throw new XMLCRuntimeException("Node must be a document node");
/*      */     }
/*      */ 
/*      */     
/* 1991 */     Document doc = (Document)node;
/*      */ 
/*      */     
/*      */     try {
/* 1995 */       Field[] fs = getClass().getDeclaredFields();
/*      */       
/* 1997 */       int substStart = "$element_".length();
/*      */       
/* 1999 */       for (int i = 0; i < fs.length; i++) {
/*      */         
/* 2001 */         Field f = fs[i];
/*      */         
/* 2003 */         if (f.getName().startsWith("$element_")) {
/*      */           
/* 2005 */           String id = f.getName().substring(substStart);
/*      */           
/* 2007 */           Node idNode = doc.getElementById(id);
/*      */           
/* 2009 */           if (idNode == null) {
/*      */             
/* 2011 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*      */             
/* 2013 */             idNode = doc.getElementById(id);
/*      */           } 
/*      */ 
/*      */           
/* 2017 */           if (idNode != null) f.set(this, idNode);
/*      */         
/*      */         } 
/*      */       } 
/* 2021 */     } catch (Exception e) {
/*      */       
/* 2023 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\PrcProcedimientoHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */