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
/*      */ import org.w3c.dom.html.HTMLTableRowElement;
/*      */ import org.w3c.dom.html.HTMLTableSectionElement;
/*      */ import sinco.presentation.VSEnCursoHTML;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class VSEnCursoHTML
/*      */   extends HTMLObjectImpl
/*      */   implements XMLObject, HTMLObject
/*      */ {
/*      */   private HTMLElement $element_Areacliente;
/*      */   private HTMLInputElement $element_Areacp;
/*      */   private HTMLElement $element_Areap;
/*      */   private HTMLTableElement $element_Atencion;
/*      */   private HTMLDivElement $element_AuditorCompa;
/*      */   private HTMLTableCellElement $element_Calificacion;
/*      */   private HTMLDivElement $element_Calificar;
/*      */   private HTMLDivElement $element_Cambiarestado;
/*      */   private HTMLTableElement $element_Caracteristicas;
/*      */   private HTMLElement $element_Cliente;
/*      */   private HTMLTableElement $element_Compas;
/*      */   private HTMLElement $element_Confiabilidad;
/*      */   private HTMLSelectElement $element_Confiabilidades;
/*      */   private HTMLTableCellElement $element_Copiar;
/*      */   private HTMLTableElement $element_DetalleTramite;
/*      */   private HTMLTableCellElement $element_DivSolicitudPadre;
/*      */   private HTMLScriptElement $element_ElMenu;
/*      */   private HTMLElement $element_Escalamiento;
/*      */   private HTMLElement $element_Estado;
/*      */   private HTMLSelectElement $element_Estados;
/*      */   private HTMLFormElement $element_FFortalezas;
/*      */   private HTMLElement $element_FechaContacto;
/*      */   private HTMLElement $element_FechaEscalamientos;
/*      */   private HTMLElement $element_FechaGenerada;
/*      */   private HTMLElement $element_FechaRealTerminacion;
/*      */   private HTMLElement $element_Fechaestimada;
/*      */   private HTMLElement $element_Fechavigencia;
/*      */   private HTMLFormElement $element_FormaPadre;
/*      */   private HTMLTableElement $element_Historia;
/*      */   private HTMLTableCellElement $element_IdAceptarFortalezas;
/*      */   private HTMLDivElement $element_IdAplazamientos;
/*      */   private HTMLTableElement $element_IdArchivos;
/*      */   private HTMLTableCellElement $element_IdMasArchivos;
/*      */   private HTMLTableCellElement $element_IdModCaracteristica;
/*      */   private HTMLInputElement $element_IdSolicitudArchivos;
/*      */   private HTMLInputElement $element_IdSolicitudFortelezas;
/*      */   private HTMLInputElement $element_IdSolicitudMejora;
/*      */   private HTMLInputElement $element_IdSolicitudModificar;
/*      */   private HTMLInputElement $element_IdSolicitudPadre;
/*      */   private HTMLDivElement $element_Idmostrararchivos;
/*      */   private HTMLInputElement $element_Idsolicitud;
/*      */   private HTMLInputElement $element_Idsolicitudcal;
/*      */   private HTMLInputElement $element_Idsolicitudmail;
/*      */   private HTMLInputElement $element_Llave;
/*      */   private HTMLElement $element_Mensaje;
/*      */   private HTMLBodyElement $element_MiBody;
/*      */   private HTMLScriptElement $element_MostrarMenu;
/*      */   private HTMLDivElement $element_Mostrarcaracteristicas;
/*      */   private HTMLDivElement $element_Mostrarhistoria;
/*      */   private HTMLDivElement $element_Nuevaatencion;
/*      */   private HTMLElement $element_Numerosolicitud;
/*      */   private HTMLElement $element_Oportunidad;
/*      */   private HTMLInputElement $element_Padre;
/*      */   private HTMLInputElement $element_Pagina_siguiente;
/*      */   private HTMLInputElement $element_Pagina_siguienteArc;
/*      */   private HTMLInputElement $element_Pagina_siguienteAtencion;
/*      */   private HTMLInputElement $element_Pagina_siguienteF;
/*      */   private HTMLInputElement $element_PersonaProv;
/*      */   private HTMLElement $element_Proveedor;
/*      */   private HTMLElement $element_Servicio;
/*      */   private HTMLInputElement $element_Serviciocp;
/*      */   private HTMLInputElement $element_SolicitudApla;
/*      */   private HTMLInputElement $element_Solicitudatencion;
/*      */   private HTMLDivElement $element_SolicitudesTramite;
/*      */   private HTMLTableSectionElement $element_TblAplazamientos;
/*      */   private HTMLTableCellElement $element_TdAcciones;
/*      */   private HTMLElement $element_TiempoServicio;
/*      */   private HTMLElement $element_Titulosolicitud;
/*      */   private HTMLTableCellElement $element_TrBitacora;
/*      */   private HTMLTableRowElement $element_TrContacto;
/*      */   private HTMLTableRowElement $element_TrPie;
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
/*      */   public static final String NAME_area = "area";
/*      */   public static final String NAME_confiabilidad = "confiabilidad";
/*      */   public static final String NAME_fCalificar = "fCalificar";
/*      */   public static final String NAME_fcopiar = "fcopiar";
/*      */   public static final String NAME_fforta = "fforta";
/*      */   public static final String NAME_fmodcar = "fmodcar";
/*      */   public static final String NAME_fsolpadre = "fsolpadre";
/*      */   public static final String NAME_lectura = "lectura";
/*      */   public static final String NAME_llave = "llave";
/*      */   public static final String NAME_miBoton = "miBoton";
/*      */   public static final String NAME_miForma = "miForma";
/*      */   public static final String NAME_miForma8 = "miForma8";
/*      */   public static final String NAME_miFormaG = "miFormaG";
/*      */   public static final String NAME_myForm = "myForm";
/*      */   public static final String NAME_nuevoestado = "nuevoestado";
/*      */   public static final String NAME_observacion = "observacion";
/*      */   public static final String NAME_origen = "origen";
/*      */   public static final String NAME_padre = "padre";
/*      */   public static final String NAME_pagina_siguiente = "pagina_siguiente";
/*      */   public static final String NAME_persona = "persona";
/*      */   public static final String NAME_servicio = "servicio";
/*      */   public static final String NAME_solicitud = "solicitud";
/*      */   public static final String NAME_tabla = "tabla";
/*      */   public static final String NAME_theFileName = "theFileName";
/*      */   public static final String NAME_txtContador = "txtContador";
/*      */   public static final String NAME_upload = "upload";
/*  355 */   public static final Class XMLC_GENERATED_CLASS = VSEnCursoHTML.class;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/VSEnCurso.html";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  366 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final DocumentLoader fDocumentLoader;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public VSEnCursoHTML() {
/*  377 */     this(StandardDocumentLoader.getInstance());
/*      */     
/*  379 */     buildDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  387 */   public VSEnCursoHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public VSEnCursoHTML(VSEnCursoHTML src) {
/*  395 */     this.fDocumentLoader = src.getDocumentLoader();
/*      */     
/*  397 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*      */     
/*  399 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public VSEnCursoHTML(DocumentLoader loader, boolean buildDOM) {
/*  410 */     this.fDocumentLoader = loader;
/*      */     
/*  412 */     if (buildDOM)
/*      */     {
/*  414 */       buildDocument();
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
/*  426 */   public VSEnCursoHTML(DocumentLoader loader) { this(loader, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void buildDocument() {
/*  434 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*      */     
/*  436 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node cloneNode(boolean deep) {
/*  444 */     cloneDeepCheck(deep);
/*      */     
/*  446 */     return new VSEnCursoHTML(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  454 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  462 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  471 */   public HTMLElement getElementAreacliente() { return this.$element_Areacliente; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  480 */   public HTMLInputElement getElementAreacp() { return this.$element_Areacp; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  489 */   public HTMLElement getElementAreap() { return this.$element_Areap; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  498 */   public HTMLTableElement getElementAtencion() { return this.$element_Atencion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  507 */   public HTMLDivElement getElementAuditorCompa() { return this.$element_AuditorCompa; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  516 */   public HTMLTableCellElement getElementCalificacion() { return this.$element_Calificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  525 */   public HTMLDivElement getElementCalificar() { return this.$element_Calificar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  534 */   public HTMLDivElement getElementCambiarestado() { return this.$element_Cambiarestado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  543 */   public HTMLTableElement getElementCaracteristicas() { return this.$element_Caracteristicas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  552 */   public HTMLElement getElementCliente() { return this.$element_Cliente; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  561 */   public HTMLTableElement getElementCompas() { return this.$element_Compas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  570 */   public HTMLElement getElementConfiabilidad() { return this.$element_Confiabilidad; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  579 */   public HTMLSelectElement getElementConfiabilidades() { return this.$element_Confiabilidades; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  588 */   public HTMLTableCellElement getElementCopiar() { return this.$element_Copiar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  597 */   public HTMLTableElement getElementDetalleTramite() { return this.$element_DetalleTramite; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  606 */   public HTMLTableCellElement getElementDivSolicitudPadre() { return this.$element_DivSolicitudPadre; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  615 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  624 */   public HTMLElement getElementEscalamiento() { return this.$element_Escalamiento; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  633 */   public HTMLElement getElementEstado() { return this.$element_Estado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  642 */   public HTMLSelectElement getElementEstados() { return this.$element_Estados; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  651 */   public HTMLFormElement getElementFFortalezas() { return this.$element_FFortalezas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  660 */   public HTMLElement getElementFechaContacto() { return this.$element_FechaContacto; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  669 */   public HTMLElement getElementFechaEscalamientos() { return this.$element_FechaEscalamientos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  678 */   public HTMLElement getElementFechaGenerada() { return this.$element_FechaGenerada; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  687 */   public HTMLElement getElementFechaRealTerminacion() { return this.$element_FechaRealTerminacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  696 */   public HTMLElement getElementFechaestimada() { return this.$element_Fechaestimada; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  705 */   public HTMLElement getElementFechavigencia() { return this.$element_Fechavigencia; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  714 */   public HTMLFormElement getElementFormaPadre() { return this.$element_FormaPadre; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  723 */   public HTMLTableElement getElementHistoria() { return this.$element_Historia; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  732 */   public HTMLTableCellElement getElementIdAceptarFortalezas() { return this.$element_IdAceptarFortalezas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  741 */   public HTMLDivElement getElementIdAplazamientos() { return this.$element_IdAplazamientos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  750 */   public HTMLTableElement getElementIdArchivos() { return this.$element_IdArchivos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  759 */   public HTMLTableCellElement getElementIdMasArchivos() { return this.$element_IdMasArchivos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  768 */   public HTMLTableCellElement getElementIdModCaracteristica() { return this.$element_IdModCaracteristica; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  777 */   public HTMLInputElement getElementIdSolicitudArchivos() { return this.$element_IdSolicitudArchivos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  786 */   public HTMLInputElement getElementIdSolicitudFortelezas() { return this.$element_IdSolicitudFortelezas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  795 */   public HTMLInputElement getElementIdSolicitudMejora() { return this.$element_IdSolicitudMejora; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  804 */   public HTMLInputElement getElementIdSolicitudModificar() { return this.$element_IdSolicitudModificar; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  813 */   public HTMLInputElement getElementIdSolicitudPadre() { return this.$element_IdSolicitudPadre; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  822 */   public HTMLDivElement getElementIdmostrararchivos() { return this.$element_Idmostrararchivos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  831 */   public HTMLInputElement getElementIdsolicitud() { return this.$element_Idsolicitud; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  840 */   public HTMLInputElement getElementIdsolicitudcal() { return this.$element_Idsolicitudcal; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  849 */   public HTMLInputElement getElementIdsolicitudmail() { return this.$element_Idsolicitudmail; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  858 */   public HTMLInputElement getElementLlave() { return this.$element_Llave; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  867 */   public HTMLElement getElementMensaje() { return this.$element_Mensaje; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  876 */   public HTMLBodyElement getElementMiBody() { return this.$element_MiBody; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  885 */   public HTMLScriptElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  894 */   public HTMLDivElement getElementMostrarcaracteristicas() { return this.$element_Mostrarcaracteristicas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  903 */   public HTMLDivElement getElementMostrarhistoria() { return this.$element_Mostrarhistoria; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  912 */   public HTMLDivElement getElementNuevaatencion() { return this.$element_Nuevaatencion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  921 */   public HTMLElement getElementNumerosolicitud() { return this.$element_Numerosolicitud; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  930 */   public HTMLElement getElementOportunidad() { return this.$element_Oportunidad; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  939 */   public HTMLInputElement getElementPadre() { return this.$element_Padre; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  948 */   public HTMLInputElement getElementPagina_siguiente() { return this.$element_Pagina_siguiente; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  957 */   public HTMLInputElement getElementPagina_siguienteArc() { return this.$element_Pagina_siguienteArc; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  966 */   public HTMLInputElement getElementPagina_siguienteAtencion() { return this.$element_Pagina_siguienteAtencion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  975 */   public HTMLInputElement getElementPagina_siguienteF() { return this.$element_Pagina_siguienteF; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  984 */   public HTMLInputElement getElementPersonaProv() { return this.$element_PersonaProv; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  993 */   public HTMLElement getElementProveedor() { return this.$element_Proveedor; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1002 */   public HTMLElement getElementServicio() { return this.$element_Servicio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1011 */   public HTMLInputElement getElementServiciocp() { return this.$element_Serviciocp; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1020 */   public HTMLInputElement getElementSolicitudApla() { return this.$element_SolicitudApla; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1029 */   public HTMLInputElement getElementSolicitudatencion() { return this.$element_Solicitudatencion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1038 */   public HTMLDivElement getElementSolicitudesTramite() { return this.$element_SolicitudesTramite; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1047 */   public HTMLTableSectionElement getElementTblAplazamientos() { return this.$element_TblAplazamientos; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1056 */   public HTMLTableCellElement getElementTdAcciones() { return this.$element_TdAcciones; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1065 */   public HTMLElement getElementTiempoServicio() { return this.$element_TiempoServicio; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1074 */   public HTMLElement getElementTitulosolicitud() { return this.$element_Titulosolicitud; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1083 */   public HTMLTableCellElement getElementTrBitacora() { return this.$element_TrBitacora; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1092 */   public HTMLTableRowElement getElementTrContacto() { return this.$element_TrContacto; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1101 */   public HTMLTableRowElement getElementTrPie() { return this.$element_TrPie; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1110 */   public HTMLElement getElementUnidad() { return this.$element_Unidad; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1119 */   public void setTextAreacliente(String text) { doSetText(this.$element_Areacliente, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1128 */   public void setTextAreap(String text) { doSetText(this.$element_Areap, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1137 */   public void setTextAuditorCompa(String text) { doSetText(this.$element_AuditorCompa, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1146 */   public void setTextCalificacion(String text) { doSetText(this.$element_Calificacion, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1155 */   public void setTextCalificar(String text) { doSetText(this.$element_Calificar, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1164 */   public void setTextCambiarestado(String text) { doSetText(this.$element_Cambiarestado, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1173 */   public void setTextCliente(String text) { doSetText(this.$element_Cliente, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1182 */   public void setTextConfiabilidad(String text) { doSetText(this.$element_Confiabilidad, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1191 */   public void setTextCopiar(String text) { doSetText(this.$element_Copiar, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1200 */   public void setTextDivSolicitudPadre(String text) { doSetText(this.$element_DivSolicitudPadre, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1209 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1218 */   public void setTextEscalamiento(String text) { doSetText(this.$element_Escalamiento, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1227 */   public void setTextEstado(String text) { doSetText(this.$element_Estado, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1236 */   public void setTextFFortalezas(String text) { doSetText(this.$element_FFortalezas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1245 */   public void setTextFechaContacto(String text) { doSetText(this.$element_FechaContacto, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1254 */   public void setTextFechaEscalamientos(String text) { doSetText(this.$element_FechaEscalamientos, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1263 */   public void setTextFechaGenerada(String text) { doSetText(this.$element_FechaGenerada, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1272 */   public void setTextFechaRealTerminacion(String text) { doSetText(this.$element_FechaRealTerminacion, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1281 */   public void setTextFechaestimada(String text) { doSetText(this.$element_Fechaestimada, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1290 */   public void setTextFechavigencia(String text) { doSetText(this.$element_Fechavigencia, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1299 */   public void setTextFormaPadre(String text) { doSetText(this.$element_FormaPadre, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1308 */   public void setTextIdAceptarFortalezas(String text) { doSetText(this.$element_IdAceptarFortalezas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1317 */   public void setTextIdAplazamientos(String text) { doSetText(this.$element_IdAplazamientos, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1326 */   public void setTextIdMasArchivos(String text) { doSetText(this.$element_IdMasArchivos, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1335 */   public void setTextIdModCaracteristica(String text) { doSetText(this.$element_IdModCaracteristica, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1344 */   public void setTextIdmostrararchivos(String text) { doSetText(this.$element_Idmostrararchivos, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1353 */   public void setTextMensaje(String text) { doSetText(this.$element_Mensaje, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1362 */   public void setTextMiBody(String text) { doSetText(this.$element_MiBody, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1371 */   public void setTextMostrarMenu(String text) { doSetText(this.$element_MostrarMenu, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1380 */   public void setTextMostrarcaracteristicas(String text) { doSetText(this.$element_Mostrarcaracteristicas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1389 */   public void setTextMostrarhistoria(String text) { doSetText(this.$element_Mostrarhistoria, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1398 */   public void setTextNuevaatencion(String text) { doSetText(this.$element_Nuevaatencion, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1407 */   public void setTextNumerosolicitud(String text) { doSetText(this.$element_Numerosolicitud, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1416 */   public void setTextOportunidad(String text) { doSetText(this.$element_Oportunidad, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1425 */   public void setTextProveedor(String text) { doSetText(this.$element_Proveedor, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1434 */   public void setTextServicio(String text) { doSetText(this.$element_Servicio, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1443 */   public void setTextSolicitudesTramite(String text) { doSetText(this.$element_SolicitudesTramite, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1452 */   public void setTextTdAcciones(String text) { doSetText(this.$element_TdAcciones, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1461 */   public void setTextTiempoServicio(String text) { doSetText(this.$element_TiempoServicio, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1470 */   public void setTextTitulosolicitud(String text) { doSetText(this.$element_Titulosolicitud, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1479 */   public void setTextTrBitacora(String text) { doSetText(this.$element_TrBitacora, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1488 */   public void setTextUnidad(String text) { doSetText(this.$element_Unidad, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void syncWithDocument(Node node) {
/* 1497 */     if (node.getNodeType() != 9)
/*      */     {
/* 1499 */       throw new XMLCRuntimeException("Node must be a document node");
/*      */     }
/*      */ 
/*      */     
/* 1503 */     Document doc = (Document)node;
/*      */ 
/*      */     
/*      */     try {
/* 1507 */       Field[] fs = getClass().getDeclaredFields();
/*      */       
/* 1509 */       int substStart = "$element_".length();
/*      */       
/* 1511 */       for (int i = 0; i < fs.length; i++) {
/*      */         
/* 1513 */         Field f = fs[i];
/*      */         
/* 1515 */         if (f.getName().startsWith("$element_")) {
/*      */           
/* 1517 */           String id = f.getName().substring(substStart);
/*      */           
/* 1519 */           Node idNode = doc.getElementById(id);
/*      */           
/* 1521 */           if (idNode == null) {
/*      */             
/* 1523 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*      */             
/* 1525 */             idNode = doc.getElementById(id);
/*      */           } 
/*      */ 
/*      */           
/* 1529 */           if (idNode != null) f.set(this, idNode);
/*      */         
/*      */         } 
/*      */       } 
/* 1533 */     } catch (Exception e) {
/*      */       
/* 1535 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\VSEnCursoHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */