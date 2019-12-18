/*     */ package sinco.presentation;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import org.enhydra.xml.xmlc.XMLCRuntimeException;
/*     */ import org.enhydra.xml.xmlc.XMLObject;
/*     */ import org.enhydra.xml.xmlc.deferredparsing.DocumentLoader;
/*     */ import org.enhydra.xml.xmlc.deferredparsing.StandardDocumentLoader;
/*     */ import org.enhydra.xml.xmlc.dom.XMLCDomFactory;
/*     */ import org.enhydra.xml.xmlc.dom.XMLCDomFactoryCache;
/*     */ import org.enhydra.xml.xmlc.html.HTMLObject;
/*     */ import org.enhydra.xml.xmlc.html.HTMLObjectImpl;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLBodyElement;
/*     */ import org.w3c.dom.html.HTMLDivElement;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLFormElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLScriptElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import org.w3c.dom.html.HTMLTableRowElement;
/*     */ import sinco.presentation.VerSolicitudNoEnviadaHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VerSolicitudNoEnviadaHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLInputElement $element_AnexosHechos;
/*     */   private HTMLElement $element_Areacliente;
/*     */   private HTMLElement $element_Areap;
/*     */   private HTMLTableElement $element_Caracteristicas;
/*     */   private HTMLElement $element_Cliente;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLInputElement $element_Estadocancelar;
/*     */   private HTMLDivElement $element_Extender;
/*     */   private HTMLSelectElement $element_Extensiones;
/*     */   private HTMLElement $element_Fecha;
/*     */   private HTMLFormElement $element_FrmCar;
/*     */   private HTMLInputElement $element_HelpDesk;
/*     */   private HTMLInputElement $element_HelpDesk1;
/*     */   private HTMLInputElement $element_HelpDesk2;
/*     */   private HTMLTableElement $element_IdArchivos;
/*     */   private HTMLDivElement $element_IdMasArchivos;
/*     */   private HTMLDivElement $element_IdModCaracteristica;
/*     */   private HTMLInputElement $element_IdSolicitudArchivos;
/*     */   private HTMLInputElement $element_IdSolicitudModificar;
/*     */   private HTMLDivElement $element_Idmostrararchivos;
/*     */   private HTMLInputElement $element_Idsolicitudmail;
/*     */   private HTMLScriptElement $element_JSValfechas;
/*     */   private HTMLElement $element_Mensaje;
/*     */   private HTMLElement $element_MensajeArchivo;
/*     */   private HTMLBodyElement $element_MiBody;
/*     */   private HTMLScriptElement $element_MostrarMenu;
/*     */   private HTMLInputElement $element_NumeroAnexos;
/*     */   private HTMLElement $element_Numerosolicitud;
/*     */   private HTMLInputElement $element_Pagina_siguiente;
/*     */   private HTMLInputElement $element_Pagina_siguienteArc;
/*     */   private HTMLElement $element_Proveedor;
/*     */   private HTMLInputElement $element_Segundasolicitud;
/*     */   private HTMLElement $element_Servicio;
/*     */   private HTMLInputElement $element_Solicitud;
/*     */   private HTMLInputElement $element_Solicitudcancelar;
/*     */   private HTMLInputElement $element_Solicitudenviar;
/*     */   private HTMLTableRowElement $element_TrMensaje;
/*     */   public static final String CLASS_BOT = "BOT";
/*     */   public static final String CLASS_INP = "INP";
/*     */   public static final String CLASS_ca = "ca";
/*     */   public static final String CLASS_ca_rojo = "ca_rojo";
/*     */   public static final String CLASS_cb = "cb";
/*     */   public static final String CLASS_cf1 = "cf1";
/*     */   public static final String CLASS_cf2 = "cf2";
/*     */   public static final String CLASS_container = "container";
/*     */   public static final String CLASS_dat = "dat";
/*     */   public static final String CLASS_dat2 = "dat2";
/*     */   public static final String CLASS_ne = "ne";
/*     */   public static final String CLASS_tabf = "tabf";
/*     */   public static final String CLASS_tit = "tit";
/*     */   public static final String NAME_anexosHechos = "anexosHechos";
/*     */   public static final String NAME_extensiones = "extensiones";
/*     */   public static final String NAME_helpDesk = "helpDesk";
/*     */   public static final String NAME_miBoton = "miBoton";
/*     */   public static final String NAME_miForma = "miForma";
/*     */   public static final String NAME_miForma2 = "miForma2";
/*     */   public static final String NAME_miForma4 = "miForma4";
/*     */   public static final String NAME_myForm = "myForm";
/*     */   public static final String NAME_nuevoestado = "nuevoestado";
/*     */   public static final String NAME_numeroAnexos = "numeroAnexos";
/*     */   public static final String NAME_observacion = "observacion";
/*     */   public static final String NAME_origen = "origen";
/*     */   public static final String NAME_pagina = "pagina";
/*     */   public static final String NAME_pagina_siguiente = "pagina_siguiente";
/*     */   public static final String NAME_solicitud = "solicitud";
/*     */   public static final String NAME_theFileName = "theFileName";
/*     */   public static final String NAME_upload = "upload";
/* 245 */   public static final Class XMLC_GENERATED_CLASS = VerSolicitudNoEnviadaHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/VerSolicitudNoEnviada.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 256 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VerSolicitudNoEnviadaHTML() {
/* 267 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 269 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 277 */   public VerSolicitudNoEnviadaHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VerSolicitudNoEnviadaHTML(VerSolicitudNoEnviadaHTML src) {
/* 285 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 287 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 289 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VerSolicitudNoEnviadaHTML(DocumentLoader loader, boolean buildDOM) {
/* 300 */     this.fDocumentLoader = loader;
/*     */     
/* 302 */     if (buildDOM)
/*     */     {
/* 304 */       buildDocument();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 316 */   public VerSolicitudNoEnviadaHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 324 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 326 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 334 */     cloneDeepCheck(deep);
/*     */     
/* 336 */     return new VerSolicitudNoEnviadaHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 344 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 352 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 361 */   public HTMLInputElement getElementAnexosHechos() { return this.$element_AnexosHechos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 370 */   public HTMLElement getElementAreacliente() { return this.$element_Areacliente; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 379 */   public HTMLElement getElementAreap() { return this.$element_Areap; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 388 */   public HTMLTableElement getElementCaracteristicas() { return this.$element_Caracteristicas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 397 */   public HTMLElement getElementCliente() { return this.$element_Cliente; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 406 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 415 */   public HTMLInputElement getElementEstadocancelar() { return this.$element_Estadocancelar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 424 */   public HTMLDivElement getElementExtender() { return this.$element_Extender; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 433 */   public HTMLSelectElement getElementExtensiones() { return this.$element_Extensiones; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 442 */   public HTMLElement getElementFecha() { return this.$element_Fecha; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 451 */   public HTMLFormElement getElementFrmCar() { return this.$element_FrmCar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 460 */   public HTMLInputElement getElementHelpDesk() { return this.$element_HelpDesk; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 469 */   public HTMLInputElement getElementHelpDesk1() { return this.$element_HelpDesk1; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 478 */   public HTMLInputElement getElementHelpDesk2() { return this.$element_HelpDesk2; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 487 */   public HTMLTableElement getElementIdArchivos() { return this.$element_IdArchivos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 496 */   public HTMLDivElement getElementIdMasArchivos() { return this.$element_IdMasArchivos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 505 */   public HTMLDivElement getElementIdModCaracteristica() { return this.$element_IdModCaracteristica; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 514 */   public HTMLInputElement getElementIdSolicitudArchivos() { return this.$element_IdSolicitudArchivos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 523 */   public HTMLInputElement getElementIdSolicitudModificar() { return this.$element_IdSolicitudModificar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 532 */   public HTMLDivElement getElementIdmostrararchivos() { return this.$element_Idmostrararchivos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 541 */   public HTMLInputElement getElementIdsolicitudmail() { return this.$element_Idsolicitudmail; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 550 */   public HTMLScriptElement getElementJSValfechas() { return this.$element_JSValfechas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 559 */   public HTMLElement getElementMensaje() { return this.$element_Mensaje; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 568 */   public HTMLElement getElementMensajeArchivo() { return this.$element_MensajeArchivo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 577 */   public HTMLBodyElement getElementMiBody() { return this.$element_MiBody; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 586 */   public HTMLScriptElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 595 */   public HTMLInputElement getElementNumeroAnexos() { return this.$element_NumeroAnexos; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 604 */   public HTMLElement getElementNumerosolicitud() { return this.$element_Numerosolicitud; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 613 */   public HTMLInputElement getElementPagina_siguiente() { return this.$element_Pagina_siguiente; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 622 */   public HTMLInputElement getElementPagina_siguienteArc() { return this.$element_Pagina_siguienteArc; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 631 */   public HTMLElement getElementProveedor() { return this.$element_Proveedor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 640 */   public HTMLInputElement getElementSegundasolicitud() { return this.$element_Segundasolicitud; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 649 */   public HTMLElement getElementServicio() { return this.$element_Servicio; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 658 */   public HTMLInputElement getElementSolicitud() { return this.$element_Solicitud; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 667 */   public HTMLInputElement getElementSolicitudcancelar() { return this.$element_Solicitudcancelar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 676 */   public HTMLInputElement getElementSolicitudenviar() { return this.$element_Solicitudenviar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 685 */   public HTMLTableRowElement getElementTrMensaje() { return this.$element_TrMensaje; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 694 */   public void setTextAreacliente(String text) { doSetText(this.$element_Areacliente, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 703 */   public void setTextAreap(String text) { doSetText(this.$element_Areap, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 712 */   public void setTextCliente(String text) { doSetText(this.$element_Cliente, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 721 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 730 */   public void setTextExtender(String text) { doSetText(this.$element_Extender, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 739 */   public void setTextFecha(String text) { doSetText(this.$element_Fecha, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 748 */   public void setTextFrmCar(String text) { doSetText(this.$element_FrmCar, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 757 */   public void setTextIdMasArchivos(String text) { doSetText(this.$element_IdMasArchivos, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 766 */   public void setTextIdModCaracteristica(String text) { doSetText(this.$element_IdModCaracteristica, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 775 */   public void setTextIdmostrararchivos(String text) { doSetText(this.$element_Idmostrararchivos, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 784 */   public void setTextJSValfechas(String text) { doSetText(this.$element_JSValfechas, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 793 */   public void setTextMensaje(String text) { doSetText(this.$element_Mensaje, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 802 */   public void setTextMensajeArchivo(String text) { doSetText(this.$element_MensajeArchivo, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 811 */   public void setTextMiBody(String text) { doSetText(this.$element_MiBody, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 820 */   public void setTextMostrarMenu(String text) { doSetText(this.$element_MostrarMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 829 */   public void setTextNumerosolicitud(String text) { doSetText(this.$element_Numerosolicitud, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 838 */   public void setTextProveedor(String text) { doSetText(this.$element_Proveedor, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 847 */   public void setTextServicio(String text) { doSetText(this.$element_Servicio, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 856 */     if (node.getNodeType() != 9)
/*     */     {
/* 858 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 862 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 866 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 868 */       int substStart = "$element_".length();
/*     */       
/* 870 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 872 */         Field f = fs[i];
/*     */         
/* 874 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 876 */           String id = f.getName().substring(substStart);
/*     */           
/* 878 */           Node idNode = doc.getElementById(id);
/*     */           
/* 880 */           if (idNode == null) {
/*     */             
/* 882 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 884 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 888 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 892 */     } catch (Exception e) {
/*     */       
/* 894 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\VerSolicitudNoEnviadaHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */