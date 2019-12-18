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
/*      */ import org.w3c.dom.html.HTMLAnchorElement;
/*      */ import org.w3c.dom.html.HTMLDivElement;
/*      */ import org.w3c.dom.html.HTMLInputElement;
/*      */ import org.w3c.dom.html.HTMLScriptElement;
/*      */ import org.w3c.dom.html.HTMLSelectElement;
/*      */ import org.w3c.dom.html.HTMLTableCellElement;
/*      */ import org.w3c.dom.html.HTMLTableElement;
/*      */ import org.w3c.dom.html.HTMLTableRowElement;
/*      */ import sinco.presentation.CalDocumentosActHTML;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CalDocumentosActHTML
/*      */   extends HTMLObjectImpl
/*      */   implements XMLObject, HTMLObject
/*      */ {
/*      */   private HTMLScriptElement $element_AreasAsignadas;
/*      */   private HTMLInputElement $element_Codigof;
/*      */   private HTMLInputElement $element_Consecutivo;
/*      */   private HTMLInputElement $element_Descripcionf;
/*      */   private HTMLTableElement $element_Detalle;
/*      */   private HTMLInputElement $element_Doc;
/*      */   private HTMLAnchorElement $element_DocumentoPdf;
/*      */   private HTMLAnchorElement $element_DocumentoWord;
/*      */   private HTMLScriptElement $element_ElMenu;
/*      */   private HTMLSelectElement $element_Estadof;
/*      */   private HTMLInputElement $element_Estadoh;
/*      */   private HTMLInputElement $element_FechaEmision;
/*      */   private HTMLInputElement $element_FechaInsercion;
/*      */   private HTMLInputElement $element_FechaModificacion;
/*      */   private HTMLInputElement $element_Fobservaciones;
/*      */   private HTMLInputElement $element_IdCodigo;
/*      */   private HTMLInputElement $element_IdDescripcion;
/*      */   private HTMLSelectElement $element_IdEstado;
/*      */   private HTMLInputElement $element_IdFechaVersion;
/*      */   private HTMLInputElement $element_IdVersion;
/*      */   private HTMLInputElement $element_MiBotonB;
/*      */   private HTMLInputElement $element_MiBotonD;
/*      */   private HTMLInputElement $element_MiVersion;
/*      */   private HTMLTableElement $element_MostrarMenu;
/*      */   private HTMLInputElement $element_ObjetivoHidden;
/*      */   private HTMLInputElement $element_Observaciones;
/*      */   private HTMLInputElement $element_Operacion;
/*      */   private HTMLInputElement $element_Orden;
/*      */   private HTMLTableCellElement $element_Pro;
/*      */   private HTMLSelectElement $element_Proceso;
/*      */   private HTMLSelectElement $element_Procesof;
/*      */   private HTMLScriptElement $element_Responsables;
/*      */   private HTMLScriptElement $element_ScriptAreas;
/*      */   private HTMLSelectElement $element_SubProcesoC;
/*      */   private HTMLTableCellElement $element_Subpro;
/*      */   private HTMLTableCellElement $element_Subpro2;
/*      */   private HTMLSelectElement $element_Subproceso;
/*      */   private HTMLTableElement $element_TablaLineas;
/*      */   private HTMLTableElement $element_TablaResponsables;
/*      */   private HTMLSelectElement $element_TipoDocumento;
/*      */   private HTMLSelectElement $element_TipoDocumentof;
/*      */   private HTMLTableCellElement $element_Titulo;
/*      */   private HTMLTableRowElement $element_TrAct;
/*      */   private HTMLDivElement $element_TrConsulta;
/*      */   private HTMLDivElement $element_TrCreacionRegistro;
/*      */   private HTMLDivElement $element_TrResultados;
/*      */   private HTMLInputElement $element_UsuarioInsercion;
/*      */   private HTMLInputElement $element_UsuarioModificacion;
/*      */   public static final String CLASS_BOT = "BOT";
/*      */   public static final String CLASS_IND = "IND";
/*      */   public static final String CLASS_INP = "INP";
/*      */   public static final String CLASS_ca = "ca";
/*      */   public static final String CLASS_cb = "cb";
/*      */   public static final String CLASS_cf1 = "cf1";
/*      */   public static final String CLASS_cf2 = "cf2";
/*      */   public static final String CLASS_container = "container";
/*      */   public static final String CLASS_dat = "dat";
/*      */   public static final String CLASS_dat2 = "dat2";
/*      */   public static final String CLASS_tabf = "tabf";
/*      */   public static final String CLASS_tabw = "tabw";
/*      */   public static final String CLASS_tit = "tit";
/*      */   public static final String NAME_AgregarLinea = "AgregarLinea";
/*      */   public static final String NAME_Doc = "Doc";
/*      */   public static final String NAME__operacion = "_operacion";
/*      */   public static final String NAME_codigo = "codigo";
/*      */   public static final String NAME_consecutivo = "consecutivo";
/*      */   public static final String NAME_descripcion = "descripcion";
/*      */   public static final String NAME_descripcionConsulta = "descripcionConsulta";
/*      */   public static final String NAME_estado = "estado";
/*      */   public static final String NAME_estadoh = "estadoh";
/*      */   public static final String NAME_fechaEmision = "fechaEmision";
/*      */   public static final String NAME_fechaInsercion = "fechaInsercion";
/*      */   public static final String NAME_fechaModificacion = "fechaModificacion";
/*      */   public static final String NAME_fechaVersion = "fechaVersion";
/*      */   public static final String NAME_filtroDist = "filtroDist";
/*      */   public static final String NAME_filtroResp = "filtroResp";
/*      */   public static final String NAME_fobservaciones = "fobservaciones";
/*      */   public static final String NAME_miBotonG = "miBotonG";
/*      */   public static final String NAME_miBotonN = "miBotonN";
/*      */   public static final String NAME_miForma = "miForma";
/*      */   public static final String NAME_miForma2 = "miForma2";
/*      */   public static final String NAME_objetivoHidden = "objetivoHidden";
/*      */   public static final String NAME_observaciones = "observaciones";
/*      */   public static final String NAME_orden = "orden";
/*      */   public static final String NAME_proceso = "proceso";
/*      */   public static final String NAME_recarga = "recarga";
/*      */   public static final String NAME_subProcesoC = "subProcesoC";
/*      */   public static final String NAME_subproceso = "subproceso";
/*      */   public static final String NAME_tipoDocumento = "tipoDocumento";
/*      */   public static final String NAME_usuarioInsercion = "usuarioInsercion";
/*      */   public static final String NAME_usuarioModificacion = "usuarioModificacion";
/*      */   public static final String NAME_version = "version";
/*  337 */   public static final Class XMLC_GENERATED_CLASS = CalDocumentosActHTML.class;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/CalDocumentosAct.html";
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  348 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final DocumentLoader fDocumentLoader;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CalDocumentosActHTML() {
/*  359 */     this(StandardDocumentLoader.getInstance());
/*      */     
/*  361 */     buildDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  369 */   public CalDocumentosActHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CalDocumentosActHTML(CalDocumentosActHTML src) {
/*  377 */     this.fDocumentLoader = src.getDocumentLoader();
/*      */     
/*  379 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*      */     
/*  381 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CalDocumentosActHTML(DocumentLoader loader, boolean buildDOM) {
/*  392 */     this.fDocumentLoader = loader;
/*      */     
/*  394 */     if (buildDOM)
/*      */     {
/*  396 */       buildDocument();
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
/*  408 */   public CalDocumentosActHTML(DocumentLoader loader) { this(loader, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void buildDocument() {
/*  416 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*      */     
/*  418 */     syncAccessMethods();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Node cloneNode(boolean deep) {
/*  426 */     cloneDeepCheck(deep);
/*      */     
/*  428 */     return new CalDocumentosActHTML(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  436 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  444 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  453 */   public HTMLScriptElement getElementAreasAsignadas() { return this.$element_AreasAsignadas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  462 */   public HTMLInputElement getElementCodigof() { return this.$element_Codigof; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  471 */   public HTMLInputElement getElementConsecutivo() { return this.$element_Consecutivo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  480 */   public HTMLInputElement getElementDescripcionf() { return this.$element_Descripcionf; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  489 */   public HTMLTableElement getElementDetalle() { return this.$element_Detalle; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  498 */   public HTMLInputElement getElementDoc() { return this.$element_Doc; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  507 */   public HTMLAnchorElement getElementDocumentoPdf() { return this.$element_DocumentoPdf; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  516 */   public HTMLAnchorElement getElementDocumentoWord() { return this.$element_DocumentoWord; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  525 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  534 */   public HTMLSelectElement getElementEstadof() { return this.$element_Estadof; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  543 */   public HTMLInputElement getElementEstadoh() { return this.$element_Estadoh; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  552 */   public HTMLInputElement getElementFechaEmision() { return this.$element_FechaEmision; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  561 */   public HTMLInputElement getElementFechaInsercion() { return this.$element_FechaInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  570 */   public HTMLInputElement getElementFechaModificacion() { return this.$element_FechaModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  579 */   public HTMLInputElement getElementFobservaciones() { return this.$element_Fobservaciones; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  588 */   public HTMLInputElement getElementIdCodigo() { return this.$element_IdCodigo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  597 */   public HTMLInputElement getElementIdDescripcion() { return this.$element_IdDescripcion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  606 */   public HTMLSelectElement getElementIdEstado() { return this.$element_IdEstado; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  615 */   public HTMLInputElement getElementIdFechaVersion() { return this.$element_IdFechaVersion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  624 */   public HTMLInputElement getElementIdVersion() { return this.$element_IdVersion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  633 */   public HTMLInputElement getElementMiBotonB() { return this.$element_MiBotonB; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  642 */   public HTMLInputElement getElementMiBotonD() { return this.$element_MiBotonD; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  651 */   public HTMLInputElement getElementMiVersion() { return this.$element_MiVersion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  660 */   public HTMLTableElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  669 */   public HTMLInputElement getElementObjetivoHidden() { return this.$element_ObjetivoHidden; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  678 */   public HTMLInputElement getElementObservaciones() { return this.$element_Observaciones; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  687 */   public HTMLInputElement getElementOperacion() { return this.$element_Operacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  696 */   public HTMLInputElement getElementOrden() { return this.$element_Orden; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  705 */   public HTMLTableCellElement getElementPro() { return this.$element_Pro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  714 */   public HTMLSelectElement getElementProceso() { return this.$element_Proceso; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  723 */   public HTMLSelectElement getElementProcesof() { return this.$element_Procesof; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  732 */   public HTMLScriptElement getElementResponsables() { return this.$element_Responsables; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  741 */   public HTMLScriptElement getElementScriptAreas() { return this.$element_ScriptAreas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  750 */   public HTMLSelectElement getElementSubProcesoC() { return this.$element_SubProcesoC; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  759 */   public HTMLTableCellElement getElementSubpro() { return this.$element_Subpro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  768 */   public HTMLTableCellElement getElementSubpro2() { return this.$element_Subpro2; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  777 */   public HTMLSelectElement getElementSubproceso() { return this.$element_Subproceso; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  786 */   public HTMLTableElement getElementTablaLineas() { return this.$element_TablaLineas; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  795 */   public HTMLTableElement getElementTablaResponsables() { return this.$element_TablaResponsables; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  804 */   public HTMLSelectElement getElementTipoDocumento() { return this.$element_TipoDocumento; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  813 */   public HTMLSelectElement getElementTipoDocumentof() { return this.$element_TipoDocumentof; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  822 */   public HTMLTableCellElement getElementTitulo() { return this.$element_Titulo; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  831 */   public HTMLTableRowElement getElementTrAct() { return this.$element_TrAct; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  840 */   public HTMLDivElement getElementTrConsulta() { return this.$element_TrConsulta; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  849 */   public HTMLDivElement getElementTrCreacionRegistro() { return this.$element_TrCreacionRegistro; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  858 */   public HTMLDivElement getElementTrResultados() { return this.$element_TrResultados; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  867 */   public HTMLInputElement getElementUsuarioInsercion() { return this.$element_UsuarioInsercion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  876 */   public HTMLInputElement getElementUsuarioModificacion() { return this.$element_UsuarioModificacion; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  885 */   public void setTextAreasAsignadas(String text) { doSetText(this.$element_AreasAsignadas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  894 */   public void setTextDocumentoPdf(String text) { doSetText(this.$element_DocumentoPdf, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  903 */   public void setTextDocumentoWord(String text) { doSetText(this.$element_DocumentoWord, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  912 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  921 */   public void setTextPro(String text) { doSetText(this.$element_Pro, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  930 */   public void setTextResponsables(String text) { doSetText(this.$element_Responsables, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  939 */   public void setTextScriptAreas(String text) { doSetText(this.$element_ScriptAreas, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  948 */   public void setTextSubpro(String text) { doSetText(this.$element_Subpro, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  957 */   public void setTextSubpro2(String text) { doSetText(this.$element_Subpro2, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  966 */   public void setTextTitulo(String text) { doSetText(this.$element_Titulo, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  975 */   public void setTextTrConsulta(String text) { doSetText(this.$element_TrConsulta, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  984 */   public void setTextTrCreacionRegistro(String text) { doSetText(this.$element_TrCreacionRegistro, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  993 */   public void setTextTrResultados(String text) { doSetText(this.$element_TrResultados, text); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void syncWithDocument(Node node) {
/* 1002 */     if (node.getNodeType() != 9)
/*      */     {
/* 1004 */       throw new XMLCRuntimeException("Node must be a document node");
/*      */     }
/*      */ 
/*      */     
/* 1008 */     Document doc = (Document)node;
/*      */ 
/*      */     
/*      */     try {
/* 1012 */       Field[] fs = getClass().getDeclaredFields();
/*      */       
/* 1014 */       int substStart = "$element_".length();
/*      */       
/* 1016 */       for (int i = 0; i < fs.length; i++) {
/*      */         
/* 1018 */         Field f = fs[i];
/*      */         
/* 1020 */         if (f.getName().startsWith("$element_")) {
/*      */           
/* 1022 */           String id = f.getName().substring(substStart);
/*      */           
/* 1024 */           Node idNode = doc.getElementById(id);
/*      */           
/* 1026 */           if (idNode == null) {
/*      */             
/* 1028 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*      */             
/* 1030 */             idNode = doc.getElementById(id);
/*      */           } 
/*      */ 
/*      */           
/* 1034 */           if (idNode != null) f.set(this, idNode);
/*      */         
/*      */         } 
/*      */       } 
/* 1038 */     } catch (Exception e) {
/*      */       
/* 1040 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalDocumentosActHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */