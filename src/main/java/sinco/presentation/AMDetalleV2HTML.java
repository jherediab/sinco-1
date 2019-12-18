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
/*      */ import org.w3c.dom.html.HTMLTableCellElement;
/*      */ import org.w3c.dom.html.HTMLTableElement;
/*      */ import org.w3c.dom.html.HTMLTextAreaElement;
/*      */ import sinco.presentation.AMDetalleV2HTML;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class AMDetalleV2HTML
/*      */   extends HTMLObjectImpl
/*      */   implements XMLObject, HTMLObject
/*      */ {
/*      */   private HTMLElement $element_Accion;
/*      */   private HTMLElement $element_AccionE;
/*      */   private HTMLElement $element_AccionJ;
/*      */   private HTMLElement $element_AccionR;
/*      */   private HTMLElement $element_AreaImplanta;
/*      */   private HTMLTableElement $element_Areas;
/*      */   private HTMLInputElement $element_BfechaRevision;
/*      */   private HTMLDivElement $element_Botones;
/*      */   private HTMLDivElement $element_Cabecera;
/*      */   private HTMLDivElement $element_CambiarResp;
/*      */   private HTMLElement $element_Cargo1;
/*      */   private HTMLInputElement $element_CausaCacao;
/*      */   private HTMLSelectElement $element_CausaE;
/*      */   private HTMLSelectElement $element_CausaJ;
/*      */   private HTMLSelectElement $element_CausaR;
/*      */   private HTMLElement $element_Codigo_estado;
/*      */   private HTMLElement $element_Cumplio;
/*      */   private HTMLDivElement $element_DArea;
/*      */   private HTMLElement $element_Descripcion;
/*      */   private HTMLDivElement $element_Detalle;
/*      */   private HTMLTableElement $element_DetalleArchivos;
/*      */   private HTMLTableElement $element_DetalleS;
/*      */   private HTMLScriptElement $element_ElMenu;
/*      */   private HTMLElement $element_Empleado_cliente;
/*      */   private HTMLSelectElement $element_Estado;
/*      */   private HTMLElement $element_Fecha1;
/*      */   private HTMLElement $element_FechaEstimada;
/*      */   private HTMLElement $element_FechaGenerada;
/*      */   private HTMLElement $element_FechaReal;
/*      */   private HTMLInputElement $element_FechaRevision;
/*      */   private HTMLElement $element_Fecha_vigencia;
/*      */   private HTMLElement $element_Funcionario1;
/*      */   private HTMLInputElement $element_IdBotonArchivos;
/*      */   private HTMLInputElement $element_IdCausaAr;
/*      */   private HTMLInputElement $element_IdCausaArch;
/*      */   private HTMLInputElement $element_IdNumeroAn;
/*      */   private HTMLInputElement $element_IdNumeroAr;
/*      */   private HTMLInputElement $element_IdNumeroArch;
/*      */   private HTMLInputElement $element_IdNumeroCa;
/*      */   private HTMLInputElement $element_IdNumeroCe;
/*      */   private HTMLInputElement $element_IdNumeroEn;
/*      */   private HTMLInputElement $element_IdNumeroGC;
/*      */   private HTMLInputElement $element_IdNumeroMo;
/*      */   private HTMLInputElement $element_IdNumeroNc;
/*      */   private HTMLTextAreaElement $element_IdObservacionGC;
/*      */   private HTMLDivElement $element_IdObservaciones;
/*      */   private HTMLSelectElement $element_IdResponsable;
/*      */   private HTMLDivElement $element_Idmostrararchivos;
/*      */   private HTMLElement $element_Impacto;
/*      */   private HTMLInputElement $element_JusAnulacion;
/*      */   private HTMLInputElement $element_Llave;
/*      */   private HTMLFormElement $element_MiForma2;
/*      */   private HTMLTableElement $element_MostrarMenu;
/*      */   private HTMLElement $element_Norma;
/*      */   private HTMLElement $element_Numeral;
/*      */   private HTMLElement $element_Numero;
/*      */   private HTMLInputElement $element_NumeroE;
/*      */   private HTMLInputElement $element_NumeroJ;
/*      */   private HTMLInputElement $element_NumeroR;
/*      */   private HTMLElement $element_ObservacionCierre;
/*      */   private HTMLElement $element_Origen;
/*      */   private HTMLElement $element_Proceso;
/*      */   private HTMLElement $element_Subproceso;
/*      */   private HTMLElement $element_Tema;
/*      */   private HTMLTableCellElement $element_TrAgregarArchivo;
/*      */   private HTMLTableElement $element_TrAnular;
/*      */   private HTMLDivElement $element_TrAreas;
/*      */   private HTMLTableCellElement $element_TrBitacora;
/*      */   private HTMLTableCellElement $element_TrCalificar;
/*      */   private HTMLDivElement $element_TrCambioEstado;
/*      */   private HTMLTableCellElement $element_TrCerrar;
/*      */   private HTMLTableCellElement $element_TrEnviar;
/*      */   private HTMLDivElement $element_TrFinaliza;
/*      */   private HTMLTableCellElement $element_TrNuevaCausa;
/*      */   private HTMLDivElement $element_TrObsGerencia;
/*      */   private HTMLDivElement $element_TrPedirCacao;
/*      */   private HTMLTableCellElement $element_TrPreModificar;
/*      */   public static final String CLASS_BOT = "BOT";
/*      */   public static final String CLASS_INP = "INP";
/*      */   public static final String CLASS_ca = "ca";
/*      */   public static final String CLASS_cb = "cb";
/*      */   public static final String CLASS_cf1 = "cf1";
/*      */   public static final String CLASS_cf2 = "cf2";
/*      */   public static final String CLASS_container = "container";
/*      */   public static final String CLASS_dat = "dat";
/*      */   public static final String CLASS_dat2 = "dat2";
/*      */   public static final String CLASS_ne = "ne";
/*      */   public static final String CLASS_tabf = "tabf";
/*      */   public static final String CLASS_tabp = "tabp";
/*      */   public static final String CLASS_tabw = "tabw";
/*      */   public static final String CLASS_tit = "tit";
/*      */   public static final String NAME__operacion = "_operacion";
/*      */   public static final String NAME_causa = "causa";
/*      */   public static final String NAME_estado = "estado";
/*      */   public static final String NAME_fechaRevision = "fechaRevision";
/*      */   public static final String NAME_justificacion = "justificacion";
/*      */   public static final String NAME_llave = "llave";
/*      */   public static final String NAME_miBoton = "miBoton";
/*      */   public static final String NAME_miForma1 = "miForma1";
/*      */   public static final String NAME_miForma10 = "miForma10";
/*      */   public static final String NAME_miForma2 = "miForma2";
/*      */   public static final String NAME_miForma3 = "miForma3";
/*      */   public static final String NAME_miForma4 = "miForma4";
/*      */   public static final String NAME_miForma5 = "miForma5";
/*      */   public static final String NAME_miForma6 = "miForma6";
/*      */   public static final String NAME_miForma7 = "miForma7";
/*      */   public static final String NAME_miForma8 = "miForma8";
/*      */   public static final String NAME_numero = "numero";
/*      */   public static final String NAME_observacion = "observacion";
/*      */   public static final String NAME_observacionGC = "observacionGC";
/*      */   public static final String NAME_pagina = "pagina";
/*      */   public static final String NAME_responsable = "responsable";
/*      */   public static final String NAME_tabla = "tabla";
/*      */   public static final String NAME_txtContObservacionGC = "txtContObservacionGC";
/*  360 */   public static final Class XMLC_GENERATED_CLASS = AMDetalleV2HTML.class;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/AMDetalleV2.html";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  371 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final DocumentLoader fDocumentLoader;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AMDetalleV2HTML() {
/*  382 */     this(StandardDocumentLoader.getInstance());
/*      */     
/*  384 */     buildDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  392 */   public AMDetalleV2HTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AMDetalleV2HTML(AMDetalleV2HTML src) {
/*  400 */     this.fDocumentLoader = src.getDocumentLoader();
/*      */     
/*  402 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*      */     
/*  404 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AMDetalleV2HTML(DocumentLoader loader, boolean buildDOM) {
/*  415 */     this.fDocumentLoader = loader;
/*      */     
/*  417 */     if (buildDOM)
/*      */     {
/*  419 */       buildDocument();
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
/*  431 */   public AMDetalleV2HTML(DocumentLoader loader) { this(loader, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void buildDocument() {
/*  439 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*      */     
/*  441 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node cloneNode(boolean deep) {
/*  449 */     cloneDeepCheck(deep);
/*      */     
/*  451 */     return new AMDetalleV2HTML(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  459 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  467 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  476 */   public HTMLElement getElementAccion() { return this.$element_Accion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  485 */   public HTMLElement getElementAccionE() { return this.$element_AccionE; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  494 */   public HTMLElement getElementAccionJ() { return this.$element_AccionJ; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  503 */   public HTMLElement getElementAccionR() { return this.$element_AccionR; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  512 */   public HTMLElement getElementAreaImplanta() { return this.$element_AreaImplanta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  521 */   public HTMLTableElement getElementAreas() { return this.$element_Areas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  530 */   public HTMLInputElement getElementBfechaRevision() { return this.$element_BfechaRevision; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  539 */   public HTMLDivElement getElementBotones() { return this.$element_Botones; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  548 */   public HTMLDivElement getElementCabecera() { return this.$element_Cabecera; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  557 */   public HTMLDivElement getElementCambiarResp() { return this.$element_CambiarResp; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  566 */   public HTMLElement getElementCargo1() { return this.$element_Cargo1; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  575 */   public HTMLInputElement getElementCausaCacao() { return this.$element_CausaCacao; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  584 */   public HTMLSelectElement getElementCausaE() { return this.$element_CausaE; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  593 */   public HTMLSelectElement getElementCausaJ() { return this.$element_CausaJ; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  602 */   public HTMLSelectElement getElementCausaR() { return this.$element_CausaR; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  611 */   public HTMLElement getElementCodigo_estado() { return this.$element_Codigo_estado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  620 */   public HTMLElement getElementCumplio() { return this.$element_Cumplio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  629 */   public HTMLDivElement getElementDArea() { return this.$element_DArea; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  638 */   public HTMLElement getElementDescripcion() { return this.$element_Descripcion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  647 */   public HTMLDivElement getElementDetalle() { return this.$element_Detalle; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  656 */   public HTMLTableElement getElementDetalleArchivos() { return this.$element_DetalleArchivos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  665 */   public HTMLTableElement getElementDetalleS() { return this.$element_DetalleS; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  674 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  683 */   public HTMLElement getElementEmpleado_cliente() { return this.$element_Empleado_cliente; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  692 */   public HTMLSelectElement getElementEstado() { return this.$element_Estado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  701 */   public HTMLElement getElementFecha1() { return this.$element_Fecha1; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  710 */   public HTMLElement getElementFechaEstimada() { return this.$element_FechaEstimada; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  719 */   public HTMLElement getElementFechaGenerada() { return this.$element_FechaGenerada; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  728 */   public HTMLElement getElementFechaReal() { return this.$element_FechaReal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  737 */   public HTMLInputElement getElementFechaRevision() { return this.$element_FechaRevision; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  746 */   public HTMLElement getElementFecha_vigencia() { return this.$element_Fecha_vigencia; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  755 */   public HTMLElement getElementFuncionario1() { return this.$element_Funcionario1; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  764 */   public HTMLInputElement getElementIdBotonArchivos() { return this.$element_IdBotonArchivos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  773 */   public HTMLInputElement getElementIdCausaAr() { return this.$element_IdCausaAr; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  782 */   public HTMLInputElement getElementIdCausaArch() { return this.$element_IdCausaArch; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  791 */   public HTMLInputElement getElementIdNumeroAn() { return this.$element_IdNumeroAn; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  800 */   public HTMLInputElement getElementIdNumeroAr() { return this.$element_IdNumeroAr; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  809 */   public HTMLInputElement getElementIdNumeroArch() { return this.$element_IdNumeroArch; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  818 */   public HTMLInputElement getElementIdNumeroCa() { return this.$element_IdNumeroCa; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  827 */   public HTMLInputElement getElementIdNumeroCe() { return this.$element_IdNumeroCe; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  836 */   public HTMLInputElement getElementIdNumeroEn() { return this.$element_IdNumeroEn; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  845 */   public HTMLInputElement getElementIdNumeroGC() { return this.$element_IdNumeroGC; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  854 */   public HTMLInputElement getElementIdNumeroMo() { return this.$element_IdNumeroMo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  863 */   public HTMLInputElement getElementIdNumeroNc() { return this.$element_IdNumeroNc; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  872 */   public HTMLTextAreaElement getElementIdObservacionGC() { return this.$element_IdObservacionGC; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  881 */   public HTMLDivElement getElementIdObservaciones() { return this.$element_IdObservaciones; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  890 */   public HTMLSelectElement getElementIdResponsable() { return this.$element_IdResponsable; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  899 */   public HTMLDivElement getElementIdmostrararchivos() { return this.$element_Idmostrararchivos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  908 */   public HTMLElement getElementImpacto() { return this.$element_Impacto; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  917 */   public HTMLInputElement getElementJusAnulacion() { return this.$element_JusAnulacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  926 */   public HTMLInputElement getElementLlave() { return this.$element_Llave; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  935 */   public HTMLFormElement getElementMiForma2() { return this.$element_MiForma2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  944 */   public HTMLTableElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  953 */   public HTMLElement getElementNorma() { return this.$element_Norma; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  962 */   public HTMLElement getElementNumeral() { return this.$element_Numeral; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  971 */   public HTMLElement getElementNumero() { return this.$element_Numero; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  980 */   public HTMLInputElement getElementNumeroE() { return this.$element_NumeroE; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  989 */   public HTMLInputElement getElementNumeroJ() { return this.$element_NumeroJ; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  998 */   public HTMLInputElement getElementNumeroR() { return this.$element_NumeroR; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1007 */   public HTMLElement getElementObservacionCierre() { return this.$element_ObservacionCierre; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1016 */   public HTMLElement getElementOrigen() { return this.$element_Origen; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1025 */   public HTMLElement getElementProceso() { return this.$element_Proceso; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1034 */   public HTMLElement getElementSubproceso() { return this.$element_Subproceso; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1043 */   public HTMLElement getElementTema() { return this.$element_Tema; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1052 */   public HTMLTableCellElement getElementTrAgregarArchivo() { return this.$element_TrAgregarArchivo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1061 */   public HTMLTableElement getElementTrAnular() { return this.$element_TrAnular; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1070 */   public HTMLDivElement getElementTrAreas() { return this.$element_TrAreas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1079 */   public HTMLTableCellElement getElementTrBitacora() { return this.$element_TrBitacora; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1088 */   public HTMLTableCellElement getElementTrCalificar() { return this.$element_TrCalificar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1097 */   public HTMLDivElement getElementTrCambioEstado() { return this.$element_TrCambioEstado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1106 */   public HTMLTableCellElement getElementTrCerrar() { return this.$element_TrCerrar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1115 */   public HTMLTableCellElement getElementTrEnviar() { return this.$element_TrEnviar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1124 */   public HTMLDivElement getElementTrFinaliza() { return this.$element_TrFinaliza; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1133 */   public HTMLTableCellElement getElementTrNuevaCausa() { return this.$element_TrNuevaCausa; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1142 */   public HTMLDivElement getElementTrObsGerencia() { return this.$element_TrObsGerencia; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1151 */   public HTMLDivElement getElementTrPedirCacao() { return this.$element_TrPedirCacao; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1160 */   public HTMLTableCellElement getElementTrPreModificar() { return this.$element_TrPreModificar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1169 */   public void setTextAccion(String text) { doSetText(this.$element_Accion, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1178 */   public void setTextAccionE(String text) { doSetText(this.$element_AccionE, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1187 */   public void setTextAccionJ(String text) { doSetText(this.$element_AccionJ, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1196 */   public void setTextAccionR(String text) { doSetText(this.$element_AccionR, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1205 */   public void setTextAreaImplanta(String text) { doSetText(this.$element_AreaImplanta, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1214 */   public void setTextBotones(String text) { doSetText(this.$element_Botones, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1223 */   public void setTextCabecera(String text) { doSetText(this.$element_Cabecera, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1232 */   public void setTextCambiarResp(String text) { doSetText(this.$element_CambiarResp, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1241 */   public void setTextCargo1(String text) { doSetText(this.$element_Cargo1, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1250 */   public void setTextCodigo_estado(String text) { doSetText(this.$element_Codigo_estado, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1259 */   public void setTextCumplio(String text) { doSetText(this.$element_Cumplio, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1268 */   public void setTextDArea(String text) { doSetText(this.$element_DArea, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1277 */   public void setTextDescripcion(String text) { doSetText(this.$element_Descripcion, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1286 */   public void setTextDetalle(String text) { doSetText(this.$element_Detalle, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1295 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1304 */   public void setTextEmpleado_cliente(String text) { doSetText(this.$element_Empleado_cliente, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1313 */   public void setTextFecha1(String text) { doSetText(this.$element_Fecha1, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1322 */   public void setTextFechaEstimada(String text) { doSetText(this.$element_FechaEstimada, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1331 */   public void setTextFechaGenerada(String text) { doSetText(this.$element_FechaGenerada, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1340 */   public void setTextFechaReal(String text) { doSetText(this.$element_FechaReal, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1349 */   public void setTextFecha_vigencia(String text) { doSetText(this.$element_Fecha_vigencia, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1358 */   public void setTextFuncionario1(String text) { doSetText(this.$element_Funcionario1, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1367 */   public void setTextIdObservacionGC(String text) { doSetText(this.$element_IdObservacionGC, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1376 */   public void setTextIdObservaciones(String text) { doSetText(this.$element_IdObservaciones, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1385 */   public void setTextIdmostrararchivos(String text) { doSetText(this.$element_Idmostrararchivos, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1394 */   public void setTextImpacto(String text) { doSetText(this.$element_Impacto, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1403 */   public void setTextMiForma2(String text) { doSetText(this.$element_MiForma2, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1412 */   public void setTextNorma(String text) { doSetText(this.$element_Norma, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1421 */   public void setTextNumeral(String text) { doSetText(this.$element_Numeral, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1430 */   public void setTextNumero(String text) { doSetText(this.$element_Numero, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1439 */   public void setTextObservacionCierre(String text) { doSetText(this.$element_ObservacionCierre, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1448 */   public void setTextOrigen(String text) { doSetText(this.$element_Origen, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1457 */   public void setTextProceso(String text) { doSetText(this.$element_Proceso, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1466 */   public void setTextSubproceso(String text) { doSetText(this.$element_Subproceso, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1475 */   public void setTextTema(String text) { doSetText(this.$element_Tema, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1484 */   public void setTextTrAgregarArchivo(String text) { doSetText(this.$element_TrAgregarArchivo, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1493 */   public void setTextTrAreas(String text) { doSetText(this.$element_TrAreas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1502 */   public void setTextTrBitacora(String text) { doSetText(this.$element_TrBitacora, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1511 */   public void setTextTrCalificar(String text) { doSetText(this.$element_TrCalificar, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1520 */   public void setTextTrCambioEstado(String text) { doSetText(this.$element_TrCambioEstado, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1529 */   public void setTextTrCerrar(String text) { doSetText(this.$element_TrCerrar, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1538 */   public void setTextTrEnviar(String text) { doSetText(this.$element_TrEnviar, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1547 */   public void setTextTrFinaliza(String text) { doSetText(this.$element_TrFinaliza, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1556 */   public void setTextTrNuevaCausa(String text) { doSetText(this.$element_TrNuevaCausa, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1565 */   public void setTextTrObsGerencia(String text) { doSetText(this.$element_TrObsGerencia, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1574 */   public void setTextTrPedirCacao(String text) { doSetText(this.$element_TrPedirCacao, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1583 */   public void setTextTrPreModificar(String text) { doSetText(this.$element_TrPreModificar, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void syncWithDocument(Node node) {
/* 1592 */     if (node.getNodeType() != 9)
/*      */     {
/* 1594 */       throw new XMLCRuntimeException("Node must be a document node");
/*      */     }
/*      */ 
/*      */     
/* 1598 */     Document doc = (Document)node;
/*      */ 
/*      */     
/*      */     try {
/* 1602 */       Field[] fs = getClass().getDeclaredFields();
/*      */       
/* 1604 */       int substStart = "$element_".length();
/*      */       
/* 1606 */       for (int i = 0; i < fs.length; i++) {
/*      */         
/* 1608 */         Field f = fs[i];
/*      */         
/* 1610 */         if (f.getName().startsWith("$element_")) {
/*      */           
/* 1612 */           String id = f.getName().substring(substStart);
/*      */           
/* 1614 */           Node idNode = doc.getElementById(id);
/*      */           
/* 1616 */           if (idNode == null) {
/*      */             
/* 1618 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*      */             
/* 1620 */             idNode = doc.getElementById(id);
/*      */           } 
/*      */ 
/*      */           
/* 1624 */           if (idNode != null) f.set(this, idNode);
/*      */         
/*      */         } 
/*      */       } 
/* 1628 */     } catch (Exception e) {
/*      */       
/* 1630 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\AMDetalleV2HTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */