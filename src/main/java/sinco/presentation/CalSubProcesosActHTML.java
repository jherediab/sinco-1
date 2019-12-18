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
/*     */ import org.w3c.dom.html.HTMLDivElement;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLLabelElement;
/*     */ import org.w3c.dom.html.HTMLScriptElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.presentation.CalSubProcesosActHTML;
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
/*     */ public class CalSubProcesosActHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLInputElement $element_BtnAyuda;
/*     */   private HTMLInputElement $element_BtnConsultar;
/*     */   private HTMLInputElement $element_BtnConsultarDocs;
/*     */   private HTMLInputElement $element_BtnCrear;
/*     */   private HTMLInputElement $element_BtnEditar;
/*     */   private HTMLInputElement $element_BtnEliminar;
/*     */   private HTMLInputElement $element_BtnGrabar;
/*     */   private HTMLInputElement $element_BtnSalir;
/*     */   private HTMLInputElement $element_BtnVolver;
/*     */   private HTMLInputElement $element_Descripcion;
/*     */   private HTMLTableSectionElement $element_Detalle;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLSelectElement $element_Estado;
/*     */   private HTMLInputElement $element_Factor;
/*     */   private HTMLInputElement $element_FechaInsercion;
/*     */   private HTMLInputElement $element_FechaModificacion;
/*     */   private HTMLLabelElement $element_LabelNombre;
/*     */   private HTMLElement $element_MiProceso;
/*     */   private HTMLElement $element_NroRegistros;
/*     */   private HTMLInputElement $element_ObjetivoHidden;
/*     */   private HTMLInputElement $element_ProcesoHidden;
/*     */   private HTMLInputElement $element_Subproceso;
/*     */   private HTMLInputElement $element_Suma;
/*     */   private HTMLElement $element_Sumatoria;
/*     */   private HTMLDivElement $element_TrCreacionRegistro;
/*     */   private HTMLDivElement $element_TrResultados;
/*     */   private HTMLInputElement $element_UsuarioInsercion;
/*     */   private HTMLInputElement $element_UsuarioModificacion;
/*     */   private HTMLInputElement $element__operacion;
/*     */   public static final String CLASS_BOT = "BOT";
/*     */   public static final String CLASS_IND = "IND";
/*     */   public static final String CLASS_INP = "INP";
/*     */   public static final String CLASS_PIE = "PIE";
/*     */   public static final String CLASS_ca = "ca";
/*     */   public static final String CLASS_cb = "cb";
/*     */   public static final String CLASS_cf1 = "cf1";
/*     */   public static final String CLASS_cf2 = "cf2";
/*     */   public static final String CLASS_container = "container";
/*     */   public static final String CLASS_dat = "dat";
/*     */   public static final String CLASS_dat2 = "dat2";
/*     */   public static final String CLASS_datc = "datc";
/*     */   public static final String CLASS_resizable = "resizable";
/*     */   public static final String CLASS_sortable = "sortable";
/*     */   public static final String CLASS_tabf = "tabf";
/*     */   public static final String CLASS_tit = "tit";
/*     */   public static final String CLASS_titulo = "titulo";
/*     */   public static final String NAME__operacion = "_operacion";
/*     */   public static final String NAME_descripcion = "descripcion";
/*     */   public static final String NAME_estado = "estado";
/*     */   public static final String NAME_factor = "factor";
/*     */   public static final String NAME_fechaInsercion = "fechaInsercion";
/*     */   public static final String NAME_fechaModificacion = "fechaModificacion";
/*     */   public static final String NAME_ff = "ff";
/*     */   public static final String NAME_labelNombre = "labelNombre";
/*     */   public static final String NAME_objetivoHidden = "objetivoHidden";
/*     */   public static final String NAME_proceso = "proceso";
/*     */   public static final String NAME_subproceso = "subproceso";
/*     */   public static final String NAME_suma = "suma";
/*     */   public static final String NAME_usuarioInsercion = "usuarioInsercion";
/*     */   public static final String NAME_usuarioModificacion = "usuarioModificacion";
/* 234 */   public static final Class XMLC_GENERATED_CLASS = CalSubProcesosActHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/CalSubProcesosAct.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 245 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalSubProcesosActHTML() {
/* 256 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 258 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 266 */   public CalSubProcesosActHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalSubProcesosActHTML(CalSubProcesosActHTML src) {
/* 274 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 276 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 278 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalSubProcesosActHTML(DocumentLoader loader, boolean buildDOM) {
/* 289 */     this.fDocumentLoader = loader;
/*     */     
/* 291 */     if (buildDOM)
/*     */     {
/* 293 */       buildDocument();
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
/* 305 */   public CalSubProcesosActHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 313 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 315 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 323 */     cloneDeepCheck(deep);
/*     */     
/* 325 */     return new CalSubProcesosActHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 333 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 341 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 350 */   public HTMLInputElement getElementBtnAyuda() { return this.$element_BtnAyuda; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 359 */   public HTMLInputElement getElementBtnConsultar() { return this.$element_BtnConsultar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 368 */   public HTMLInputElement getElementBtnConsultarDocs() { return this.$element_BtnConsultarDocs; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 377 */   public HTMLInputElement getElementBtnCrear() { return this.$element_BtnCrear; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 386 */   public HTMLInputElement getElementBtnEditar() { return this.$element_BtnEditar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 395 */   public HTMLInputElement getElementBtnEliminar() { return this.$element_BtnEliminar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 404 */   public HTMLInputElement getElementBtnGrabar() { return this.$element_BtnGrabar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 413 */   public HTMLInputElement getElementBtnSalir() { return this.$element_BtnSalir; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 422 */   public HTMLInputElement getElementBtnVolver() { return this.$element_BtnVolver; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 431 */   public HTMLInputElement getElementDescripcion() { return this.$element_Descripcion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 440 */   public HTMLTableSectionElement getElementDetalle() { return this.$element_Detalle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 449 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 458 */   public HTMLSelectElement getElementEstado() { return this.$element_Estado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 467 */   public HTMLInputElement getElementFactor() { return this.$element_Factor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 476 */   public HTMLInputElement getElementFechaInsercion() { return this.$element_FechaInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 485 */   public HTMLInputElement getElementFechaModificacion() { return this.$element_FechaModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 494 */   public HTMLLabelElement getElementLabelNombre() { return this.$element_LabelNombre; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 503 */   public HTMLElement getElementMiProceso() { return this.$element_MiProceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 512 */   public HTMLElement getElementNroRegistros() { return this.$element_NroRegistros; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 521 */   public HTMLInputElement getElementObjetivoHidden() { return this.$element_ObjetivoHidden; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 530 */   public HTMLInputElement getElementProcesoHidden() { return this.$element_ProcesoHidden; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 539 */   public HTMLInputElement getElementSubproceso() { return this.$element_Subproceso; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 548 */   public HTMLInputElement getElementSuma() { return this.$element_Suma; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 557 */   public HTMLElement getElementSumatoria() { return this.$element_Sumatoria; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 566 */   public HTMLDivElement getElementTrCreacionRegistro() { return this.$element_TrCreacionRegistro; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 575 */   public HTMLDivElement getElementTrResultados() { return this.$element_TrResultados; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 584 */   public HTMLInputElement getElementUsuarioInsercion() { return this.$element_UsuarioInsercion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 593 */   public HTMLInputElement getElementUsuarioModificacion() { return this.$element_UsuarioModificacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 602 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 611 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 620 */   public void setTextLabelNombre(String text) { doSetText(this.$element_LabelNombre, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 629 */   public void setTextMiProceso(String text) { doSetText(this.$element_MiProceso, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 638 */   public void setTextNroRegistros(String text) { doSetText(this.$element_NroRegistros, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 647 */   public void setTextSumatoria(String text) { doSetText(this.$element_Sumatoria, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 656 */   public void setTextTrCreacionRegistro(String text) { doSetText(this.$element_TrCreacionRegistro, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 665 */   public void setTextTrResultados(String text) { doSetText(this.$element_TrResultados, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 674 */     if (node.getNodeType() != 9)
/*     */     {
/* 676 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 680 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 684 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 686 */       int substStart = "$element_".length();
/*     */       
/* 688 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 690 */         Field f = fs[i];
/*     */         
/* 692 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 694 */           String id = f.getName().substring(substStart);
/*     */           
/* 696 */           Node idNode = doc.getElementById(id);
/*     */           
/* 698 */           if (idNode == null) {
/*     */             
/* 700 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 702 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 706 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 710 */     } catch (Exception e) {
/*     */       
/* 712 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalSubProcesosActHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */