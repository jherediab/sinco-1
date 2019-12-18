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
/*      */ import org.w3c.dom.html.HTMLBodyElement;
/*      */ import org.w3c.dom.html.HTMLDivElement;
/*      */ import org.w3c.dom.html.HTMLElement;
/*      */ import org.w3c.dom.html.HTMLFormElement;
/*      */ import org.w3c.dom.html.HTMLInputElement;
/*      */ import org.w3c.dom.html.HTMLScriptElement;
/*      */ import org.w3c.dom.html.HTMLSelectElement;
/*      */ import org.w3c.dom.html.HTMLTableCellElement;
/*      */ import org.w3c.dom.html.HTMLTableElement;
/*      */ import org.w3c.dom.html.HTMLTableSectionElement;
/*      */ import sinco.presentation.VSPorAtHTML;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class VSPorAtHTML
/*      */   extends HTMLObjectImpl
/*      */   implements XMLObject, HTMLObject
/*      */ {
/*      */   private HTMLInputElement $element_AnexosHechos;
/*      */   private HTMLElement $element_Areacliente;
/*      */   private HTMLElement $element_Areap;
/*      */   private HTMLTableElement $element_Atencion;
/*      */   private HTMLDivElement $element_AuditorCompa;
/*      */   private HTMLInputElement $element_BfechaCacao;
/*      */   private HTMLDivElement $element_CambiarResp;
/*      */   private HTMLDivElement $element_Cambiarestado;
/*      */   private HTMLTableElement $element_Caracteristicas;
/*      */   private HTMLElement $element_Cliente;
/*      */   private HTMLTableSectionElement $element_Compas;
/*      */   private HTMLElement $element_Confiabilidad;
/*      */   private HTMLTableElement $element_DetalleTramite;
/*      */   private HTMLTableCellElement $element_DivSolicitudPadre;
/*      */   private HTMLScriptElement $element_ElMenu;
/*      */   private HTMLElement $element_Escalamiento;
/*      */   private HTMLElement $element_Estado;
/*      */   private HTMLSelectElement $element_Estados;
/*      */   private HTMLDivElement $element_Extender;
/*      */   private HTMLSelectElement $element_Extensiones;
/*      */   private HTMLInputElement $element_FechaCacao;
/*      */   private HTMLElement $element_FechaEscalamientos;
/*      */   private HTMLInputElement $element_FechaEstCacao;
/*      */   private HTMLElement $element_FechaGenerada;
/*      */   private HTMLElement $element_FechaRealTerminacion;
/*      */   private HTMLElement $element_Fechaestimada;
/*      */   private HTMLElement $element_Fechavigencia;
/*      */   private HTMLFormElement $element_FormaPadre;
/*      */   private HTMLFormElement $element_FrmCar;
/*      */   private HTMLTableElement $element_Historia;
/*      */   private HTMLDivElement $element_IdAplazamientos;
/*      */   private HTMLTableElement $element_IdArchivos;
/*      */   private HTMLTableElement $element_IdDetalleAplazamientos;
/*      */   private HTMLTableCellElement $element_IdMasArchivos;
/*      */   private HTMLTableCellElement $element_IdModCaracteristica;
/*      */   private HTMLTableCellElement $element_IdNuevaSolicitud;
/*      */   private HTMLTableCellElement $element_IdRecordarAtencion;
/*      */   private HTMLTableCellElement $element_IdReplicar;
/*      */   private HTMLInputElement $element_IdSolicitudArchivos;
/*      */   private HTMLInputElement $element_IdSolicitudCaracter;
/*      */   private HTMLInputElement $element_IdSolicitudExtender;
/*      */   private HTMLInputElement $element_IdSolicitudMejora;
/*      */   private HTMLInputElement $element_IdSolicitudModificar;
/*      */   private HTMLInputElement $element_IdSolicitudNueva;
/*      */   private HTMLInputElement $element_IdSolicitudPadre;
/*      */   private HTMLInputElement $element_IdSolicitudRecordar;
/*      */   private HTMLInputElement $element_IdSolicitudReplica;
/*      */   private HTMLInputElement $element_IdSolicitudResp;
/*      */   private HTMLDivElement $element_Idmostrararchivos;
/*      */   private HTMLInputElement $element_Idsolicitud;
/*      */   private HTMLInputElement $element_Idsolicitudmail;
/*      */   private HTMLInputElement $element_Idsolicitudped;
/*      */   private HTMLScriptElement $element_JSValfechas;
/*      */   private HTMLElement $element_Mensaje;
/*      */   private HTMLDivElement $element_MensajeR;
/*      */   private HTMLBodyElement $element_MiBody;
/*      */   private HTMLScriptElement $element_MostrarMenu;
/*      */   private HTMLDivElement $element_Mostrarcaracteristicas;
/*      */   private HTMLDivElement $element_Nueva;
/*      */   private HTMLDivElement $element_Nuevaatencion;
/*      */   private HTMLInputElement $element_NumeroAnexos;
/*      */   private HTMLElement $element_Numerosolicitud;
/*      */   private HTMLSelectElement $element_NvoResp;
/*      */   private HTMLElement $element_Oportunidad;
/*      */   private HTMLInputElement $element_Pagina_siguiente;
/*      */   private HTMLInputElement $element_Pagina_siguienteArc;
/*      */   private HTMLInputElement $element_Pagina_siguienteAtencion;
/*      */   private HTMLDivElement $element_Pedircacao;
/*      */   private HTMLElement $element_Proveedor;
/*      */   private HTMLElement $element_Servicio;
/*      */   private HTMLInputElement $element_Solicitudatencion;
/*      */   private HTMLDivElement $element_SolicitudesTramite;
/*      */   private HTMLInputElement $element_TareasPendiente;
/*      */   private HTMLTableCellElement $element_TdAcciones;
/*      */   private HTMLElement $element_TiempoServicio;
/*      */   private HTMLElement $element_Unidad;
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
/*      */   public static final String CLASS_tit = "tit";
/*      */   public static final String NAME_anexosHechos = "anexosHechos";
/*      */   public static final String NAME_extensiones = "extensiones";
/*      */   public static final String NAME_fecha = "fecha";
/*      */   public static final String NAME_fechaEstCacao = "fechaEstCacao";
/*      */   public static final String NAME_formresp = "formresp";
/*      */   public static final String NAME_frmRecordarAtn = "frmRecordarAtn";
/*      */   public static final String NAME_lectura = "lectura";
/*      */   public static final String NAME_miBoton = "miBoton";
/*      */   public static final String NAME_miForma = "miForma";
/*      */   public static final String NAME_miForma2 = "miForma2";
/*      */   public static final String NAME_miForma3 = "miForma3";
/*      */   public static final String NAME_miForma5 = "miForma5";
/*      */   public static final String NAME_miFormaG = "miFormaG";
/*      */   public static final String NAME_myForm = "myForm";
/*      */   public static final String NAME_notificar = "notificar";
/*      */   public static final String NAME_nuevoestado = "nuevoestado";
/*      */   public static final String NAME_nuevoresp = "nuevoresp";
/*      */   public static final String NAME_numeroAnexos = "numeroAnexos";
/*      */   public static final String NAME_observacion = "observacion";
/*      */   public static final String NAME_origen = "origen";
/*      */   public static final String NAME_pagina = "pagina";
/*      */   public static final String NAME_pagina_siguiente = "pagina_siguiente";
/*      */   public static final String NAME_solicitud = "solicitud";
/*      */   public static final String NAME_tareasPendiente = "tareasPendiente";
/*      */   public static final String NAME_theFileName = "theFileName";
/*      */   public static final String NAME_txtContador = "txtContador";
/*      */   public static final String NAME_upload = "upload";
/*  368 */   public static final Class XMLC_GENERATED_CLASS = VSPorAtHTML.class;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/VSPorAt.html";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  379 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final DocumentLoader fDocumentLoader;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public VSPorAtHTML() {
/*  390 */     this(StandardDocumentLoader.getInstance());
/*      */     
/*  392 */     buildDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  400 */   public VSPorAtHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public VSPorAtHTML(VSPorAtHTML src) {
/*  408 */     this.fDocumentLoader = src.getDocumentLoader();
/*      */     
/*  410 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*      */     
/*  412 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public VSPorAtHTML(DocumentLoader loader, boolean buildDOM) {
/*  423 */     this.fDocumentLoader = loader;
/*      */     
/*  425 */     if (buildDOM)
/*      */     {
/*  427 */       buildDocument();
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
/*  439 */   public VSPorAtHTML(DocumentLoader loader) { this(loader, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void buildDocument() {
/*  447 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*      */     
/*  449 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node cloneNode(boolean deep) {
/*  457 */     cloneDeepCheck(deep);
/*      */     
/*  459 */     return new VSPorAtHTML(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  467 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  475 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  484 */   public HTMLInputElement getElementAnexosHechos() { return this.$element_AnexosHechos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  493 */   public HTMLElement getElementAreacliente() { return this.$element_Areacliente; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  502 */   public HTMLElement getElementAreap() { return this.$element_Areap; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  511 */   public HTMLTableElement getElementAtencion() { return this.$element_Atencion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  520 */   public HTMLDivElement getElementAuditorCompa() { return this.$element_AuditorCompa; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  529 */   public HTMLInputElement getElementBfechaCacao() { return this.$element_BfechaCacao; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  538 */   public HTMLDivElement getElementCambiarResp() { return this.$element_CambiarResp; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  547 */   public HTMLDivElement getElementCambiarestado() { return this.$element_Cambiarestado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  556 */   public HTMLTableElement getElementCaracteristicas() { return this.$element_Caracteristicas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  565 */   public HTMLElement getElementCliente() { return this.$element_Cliente; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  574 */   public HTMLTableSectionElement getElementCompas() { return this.$element_Compas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  583 */   public HTMLElement getElementConfiabilidad() { return this.$element_Confiabilidad; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  592 */   public HTMLTableElement getElementDetalleTramite() { return this.$element_DetalleTramite; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  601 */   public HTMLTableCellElement getElementDivSolicitudPadre() { return this.$element_DivSolicitudPadre; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  610 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  619 */   public HTMLElement getElementEscalamiento() { return this.$element_Escalamiento; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  628 */   public HTMLElement getElementEstado() { return this.$element_Estado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  637 */   public HTMLSelectElement getElementEstados() { return this.$element_Estados; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  646 */   public HTMLDivElement getElementExtender() { return this.$element_Extender; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  655 */   public HTMLSelectElement getElementExtensiones() { return this.$element_Extensiones; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  664 */   public HTMLInputElement getElementFechaCacao() { return this.$element_FechaCacao; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  673 */   public HTMLElement getElementFechaEscalamientos() { return this.$element_FechaEscalamientos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  682 */   public HTMLInputElement getElementFechaEstCacao() { return this.$element_FechaEstCacao; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  691 */   public HTMLElement getElementFechaGenerada() { return this.$element_FechaGenerada; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  700 */   public HTMLElement getElementFechaRealTerminacion() { return this.$element_FechaRealTerminacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  709 */   public HTMLElement getElementFechaestimada() { return this.$element_Fechaestimada; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  718 */   public HTMLElement getElementFechavigencia() { return this.$element_Fechavigencia; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  727 */   public HTMLFormElement getElementFormaPadre() { return this.$element_FormaPadre; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  736 */   public HTMLFormElement getElementFrmCar() { return this.$element_FrmCar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  745 */   public HTMLTableElement getElementHistoria() { return this.$element_Historia; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  754 */   public HTMLDivElement getElementIdAplazamientos() { return this.$element_IdAplazamientos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  763 */   public HTMLTableElement getElementIdArchivos() { return this.$element_IdArchivos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  772 */   public HTMLTableElement getElementIdDetalleAplazamientos() { return this.$element_IdDetalleAplazamientos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  781 */   public HTMLTableCellElement getElementIdMasArchivos() { return this.$element_IdMasArchivos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  790 */   public HTMLTableCellElement getElementIdModCaracteristica() { return this.$element_IdModCaracteristica; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  799 */   public HTMLTableCellElement getElementIdNuevaSolicitud() { return this.$element_IdNuevaSolicitud; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  808 */   public HTMLTableCellElement getElementIdRecordarAtencion() { return this.$element_IdRecordarAtencion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  817 */   public HTMLTableCellElement getElementIdReplicar() { return this.$element_IdReplicar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  826 */   public HTMLInputElement getElementIdSolicitudArchivos() { return this.$element_IdSolicitudArchivos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  835 */   public HTMLInputElement getElementIdSolicitudCaracter() { return this.$element_IdSolicitudCaracter; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  844 */   public HTMLInputElement getElementIdSolicitudExtender() { return this.$element_IdSolicitudExtender; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  853 */   public HTMLInputElement getElementIdSolicitudMejora() { return this.$element_IdSolicitudMejora; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  862 */   public HTMLInputElement getElementIdSolicitudModificar() { return this.$element_IdSolicitudModificar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  871 */   public HTMLInputElement getElementIdSolicitudNueva() { return this.$element_IdSolicitudNueva; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  880 */   public HTMLInputElement getElementIdSolicitudPadre() { return this.$element_IdSolicitudPadre; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  889 */   public HTMLInputElement getElementIdSolicitudRecordar() { return this.$element_IdSolicitudRecordar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  898 */   public HTMLInputElement getElementIdSolicitudReplica() { return this.$element_IdSolicitudReplica; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  907 */   public HTMLInputElement getElementIdSolicitudResp() { return this.$element_IdSolicitudResp; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  916 */   public HTMLDivElement getElementIdmostrararchivos() { return this.$element_Idmostrararchivos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  925 */   public HTMLInputElement getElementIdsolicitud() { return this.$element_Idsolicitud; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  934 */   public HTMLInputElement getElementIdsolicitudmail() { return this.$element_Idsolicitudmail; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  943 */   public HTMLInputElement getElementIdsolicitudped() { return this.$element_Idsolicitudped; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  952 */   public HTMLScriptElement getElementJSValfechas() { return this.$element_JSValfechas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  961 */   public HTMLElement getElementMensaje() { return this.$element_Mensaje; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  970 */   public HTMLDivElement getElementMensajeR() { return this.$element_MensajeR; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  979 */   public HTMLBodyElement getElementMiBody() { return this.$element_MiBody; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  988 */   public HTMLScriptElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  997 */   public HTMLDivElement getElementMostrarcaracteristicas() { return this.$element_Mostrarcaracteristicas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1006 */   public HTMLDivElement getElementNueva() { return this.$element_Nueva; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1015 */   public HTMLDivElement getElementNuevaatencion() { return this.$element_Nuevaatencion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1024 */   public HTMLInputElement getElementNumeroAnexos() { return this.$element_NumeroAnexos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1033 */   public HTMLElement getElementNumerosolicitud() { return this.$element_Numerosolicitud; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1042 */   public HTMLSelectElement getElementNvoResp() { return this.$element_NvoResp; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1051 */   public HTMLElement getElementOportunidad() { return this.$element_Oportunidad; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1060 */   public HTMLInputElement getElementPagina_siguiente() { return this.$element_Pagina_siguiente; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1069 */   public HTMLInputElement getElementPagina_siguienteArc() { return this.$element_Pagina_siguienteArc; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1078 */   public HTMLInputElement getElementPagina_siguienteAtencion() { return this.$element_Pagina_siguienteAtencion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1087 */   public HTMLDivElement getElementPedircacao() { return this.$element_Pedircacao; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1096 */   public HTMLElement getElementProveedor() { return this.$element_Proveedor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1105 */   public HTMLElement getElementServicio() { return this.$element_Servicio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1114 */   public HTMLInputElement getElementSolicitudatencion() { return this.$element_Solicitudatencion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1123 */   public HTMLDivElement getElementSolicitudesTramite() { return this.$element_SolicitudesTramite; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1132 */   public HTMLInputElement getElementTareasPendiente() { return this.$element_TareasPendiente; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1141 */   public HTMLTableCellElement getElementTdAcciones() { return this.$element_TdAcciones; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1150 */   public HTMLElement getElementTiempoServicio() { return this.$element_TiempoServicio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1159 */   public HTMLElement getElementUnidad() { return this.$element_Unidad; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1168 */   public void setTextAreacliente(String text) { doSetText(this.$element_Areacliente, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1177 */   public void setTextAreap(String text) { doSetText(this.$element_Areap, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1186 */   public void setTextAuditorCompa(String text) { doSetText(this.$element_AuditorCompa, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1195 */   public void setTextCambiarResp(String text) { doSetText(this.$element_CambiarResp, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1204 */   public void setTextCambiarestado(String text) { doSetText(this.$element_Cambiarestado, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1213 */   public void setTextCliente(String text) { doSetText(this.$element_Cliente, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1222 */   public void setTextConfiabilidad(String text) { doSetText(this.$element_Confiabilidad, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1231 */   public void setTextDivSolicitudPadre(String text) { doSetText(this.$element_DivSolicitudPadre, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1240 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1249 */   public void setTextEscalamiento(String text) { doSetText(this.$element_Escalamiento, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1258 */   public void setTextEstado(String text) { doSetText(this.$element_Estado, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1267 */   public void setTextExtender(String text) { doSetText(this.$element_Extender, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1276 */   public void setTextFechaEscalamientos(String text) { doSetText(this.$element_FechaEscalamientos, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1285 */   public void setTextFechaGenerada(String text) { doSetText(this.$element_FechaGenerada, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1294 */   public void setTextFechaRealTerminacion(String text) { doSetText(this.$element_FechaRealTerminacion, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1303 */   public void setTextFechaestimada(String text) { doSetText(this.$element_Fechaestimada, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1312 */   public void setTextFechavigencia(String text) { doSetText(this.$element_Fechavigencia, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1321 */   public void setTextFormaPadre(String text) { doSetText(this.$element_FormaPadre, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1330 */   public void setTextFrmCar(String text) { doSetText(this.$element_FrmCar, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1339 */   public void setTextIdAplazamientos(String text) { doSetText(this.$element_IdAplazamientos, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1348 */   public void setTextIdMasArchivos(String text) { doSetText(this.$element_IdMasArchivos, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1357 */   public void setTextIdModCaracteristica(String text) { doSetText(this.$element_IdModCaracteristica, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1366 */   public void setTextIdNuevaSolicitud(String text) { doSetText(this.$element_IdNuevaSolicitud, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1375 */   public void setTextIdRecordarAtencion(String text) { doSetText(this.$element_IdRecordarAtencion, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1384 */   public void setTextIdReplicar(String text) { doSetText(this.$element_IdReplicar, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1393 */   public void setTextIdmostrararchivos(String text) { doSetText(this.$element_Idmostrararchivos, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1402 */   public void setTextJSValfechas(String text) { doSetText(this.$element_JSValfechas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1411 */   public void setTextMensaje(String text) { doSetText(this.$element_Mensaje, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1420 */   public void setTextMensajeR(String text) { doSetText(this.$element_MensajeR, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1429 */   public void setTextMiBody(String text) { doSetText(this.$element_MiBody, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1438 */   public void setTextMostrarMenu(String text) { doSetText(this.$element_MostrarMenu, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1447 */   public void setTextMostrarcaracteristicas(String text) { doSetText(this.$element_Mostrarcaracteristicas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1456 */   public void setTextNueva(String text) { doSetText(this.$element_Nueva, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1465 */   public void setTextNuevaatencion(String text) { doSetText(this.$element_Nuevaatencion, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1474 */   public void setTextNumerosolicitud(String text) { doSetText(this.$element_Numerosolicitud, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1483 */   public void setTextOportunidad(String text) { doSetText(this.$element_Oportunidad, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1492 */   public void setTextPedircacao(String text) { doSetText(this.$element_Pedircacao, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1501 */   public void setTextProveedor(String text) { doSetText(this.$element_Proveedor, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1510 */   public void setTextServicio(String text) { doSetText(this.$element_Servicio, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1519 */   public void setTextSolicitudesTramite(String text) { doSetText(this.$element_SolicitudesTramite, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1528 */   public void setTextTdAcciones(String text) { doSetText(this.$element_TdAcciones, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1537 */   public void setTextTiempoServicio(String text) { doSetText(this.$element_TiempoServicio, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1546 */   public void setTextUnidad(String text) { doSetText(this.$element_Unidad, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void syncWithDocument(Node node) {
/* 1555 */     if (node.getNodeType() != 9)
/*      */     {
/* 1557 */       throw new XMLCRuntimeException("Node must be a document node");
/*      */     }
/*      */ 
/*      */     
/* 1561 */     Document doc = (Document)node;
/*      */ 
/*      */     
/*      */     try {
/* 1565 */       Field[] fs = getClass().getDeclaredFields();
/*      */       
/* 1567 */       int substStart = "$element_".length();
/*      */       
/* 1569 */       for (int i = 0; i < fs.length; i++) {
/*      */         
/* 1571 */         Field f = fs[i];
/*      */         
/* 1573 */         if (f.getName().startsWith("$element_")) {
/*      */           
/* 1575 */           String id = f.getName().substring(substStart);
/*      */           
/* 1577 */           Node idNode = doc.getElementById(id);
/*      */           
/* 1579 */           if (idNode == null) {
/*      */             
/* 1581 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*      */             
/* 1583 */             idNode = doc.getElementById(id);
/*      */           } 
/*      */ 
/*      */           
/* 1587 */           if (idNode != null) f.set(this, idNode);
/*      */         
/*      */         } 
/*      */       } 
/* 1591 */     } catch (Exception e) {
/*      */       
/* 1593 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\VSPorAtHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */