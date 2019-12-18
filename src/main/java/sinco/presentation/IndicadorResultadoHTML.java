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
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.presentation.IndicadorResultadoHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IndicadorResultadoHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLInputElement $element_BtnAyuda;
/*     */   private HTMLInputElement $element_BtnConsultar;
/*     */   private HTMLInputElement $element_BtnCrear;
/*     */   private HTMLInputElement $element_BtnEliminar;
/*     */   private HTMLInputElement $element_BtnGrabar;
/*     */   private HTMLInputElement $element_BtnModificar;
/*     */   private HTMLInputElement $element_BtnSalir;
/*     */   private HTMLInputElement $element_Ciclo;
/*     */   private HTMLElement $element_CicloEd;
/*     */   private HTMLInputElement $element_CicloKey;
/*     */   private HTMLTableSectionElement $element_Detalle;
/*     */   private HTMLDivElement $element_DivConsulta;
/*     */   private HTMLDivElement $element_DivCreacionRegistro;
/*     */   private HTMLDivElement $element_DivEdicion;
/*     */   private HTMLDivElement $element_DivResultados;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLInputElement $element_Estado;
/*     */   private HTMLElement $element_EstadoEd;
/*     */   private HTMLInputElement $element_Indicador;
/*     */   private HTMLElement $element_IndicadorEd;
/*     */   private HTMLInputElement $element_IndicadorKey;
/*     */   private HTMLLabelElement $element_LabelNombre;
/*     */   private HTMLInputElement $element_Mes;
/*     */   private HTMLElement $element_MesEd;
/*     */   private HTMLInputElement $element_MesKey;
/*     */   private HTMLElement $element_NroRegistros;
/*     */   private HTMLInputElement $element_ValorEjecutado;
/*     */   private HTMLElement $element_ValorEjecutadoEd;
/*     */   private HTMLInputElement $element_ValorProgramado;
/*     */   private HTMLElement $element_ValorProgramadoEd;
/*     */   private HTMLInputElement $element__operacion;
/*     */   public static final String CLASS_BOB = "BOB";
/*     */   public static final String CLASS_BOT = "BOT";
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
/*     */   public static final String NAME__operacion = "_operacion";
/*     */   public static final String NAME_ciclo = "ciclo";
/*     */   public static final String NAME_estado = "estado";
/*     */   public static final String NAME_ff = "ff";
/*     */   public static final String NAME_indicador = "indicador";
/*     */   public static final String NAME_labelNombre = "labelNombre";
/*     */   public static final String NAME_mes = "mes";
/*     */   public static final String NAME_valorEjecutado = "valorEjecutado";
/*     */   public static final String NAME_valorProgramado = "valorProgramado";
/* 208 */   public static final Class XMLC_GENERATED_CLASS = IndicadorResultadoHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/IndicadorResultado.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 219 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadorResultadoHTML() {
/* 230 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 232 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 240 */   public IndicadorResultadoHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadorResultadoHTML(IndicadorResultadoHTML src) {
/* 248 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 250 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 252 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadorResultadoHTML(DocumentLoader loader, boolean buildDOM) {
/* 263 */     this.fDocumentLoader = loader;
/*     */     
/* 265 */     if (buildDOM)
/*     */     {
/* 267 */       buildDocument();
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
/* 279 */   public IndicadorResultadoHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 287 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 289 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 297 */     cloneDeepCheck(deep);
/*     */     
/* 299 */     return new IndicadorResultadoHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 307 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 315 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 324 */   public HTMLInputElement getElementBtnAyuda() { return this.$element_BtnAyuda; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 333 */   public HTMLInputElement getElementBtnConsultar() { return this.$element_BtnConsultar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 342 */   public HTMLInputElement getElementBtnCrear() { return this.$element_BtnCrear; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 351 */   public HTMLInputElement getElementBtnEliminar() { return this.$element_BtnEliminar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 360 */   public HTMLInputElement getElementBtnGrabar() { return this.$element_BtnGrabar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 369 */   public HTMLInputElement getElementBtnModificar() { return this.$element_BtnModificar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 378 */   public HTMLInputElement getElementBtnSalir() { return this.$element_BtnSalir; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 387 */   public HTMLInputElement getElementCiclo() { return this.$element_Ciclo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 396 */   public HTMLElement getElementCicloEd() { return this.$element_CicloEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 405 */   public HTMLInputElement getElementCicloKey() { return this.$element_CicloKey; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 414 */   public HTMLTableSectionElement getElementDetalle() { return this.$element_Detalle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 423 */   public HTMLDivElement getElementDivConsulta() { return this.$element_DivConsulta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 432 */   public HTMLDivElement getElementDivCreacionRegistro() { return this.$element_DivCreacionRegistro; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 441 */   public HTMLDivElement getElementDivEdicion() { return this.$element_DivEdicion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 450 */   public HTMLDivElement getElementDivResultados() { return this.$element_DivResultados; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 459 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 468 */   public HTMLInputElement getElementEstado() { return this.$element_Estado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 477 */   public HTMLElement getElementEstadoEd() { return this.$element_EstadoEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 486 */   public HTMLInputElement getElementIndicador() { return this.$element_Indicador; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 495 */   public HTMLElement getElementIndicadorEd() { return this.$element_IndicadorEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 504 */   public HTMLInputElement getElementIndicadorKey() { return this.$element_IndicadorKey; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 513 */   public HTMLLabelElement getElementLabelNombre() { return this.$element_LabelNombre; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 522 */   public HTMLInputElement getElementMes() { return this.$element_Mes; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 531 */   public HTMLElement getElementMesEd() { return this.$element_MesEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 540 */   public HTMLInputElement getElementMesKey() { return this.$element_MesKey; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 549 */   public HTMLElement getElementNroRegistros() { return this.$element_NroRegistros; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 558 */   public HTMLInputElement getElementValorEjecutado() { return this.$element_ValorEjecutado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 567 */   public HTMLElement getElementValorEjecutadoEd() { return this.$element_ValorEjecutadoEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 576 */   public HTMLInputElement getElementValorProgramado() { return this.$element_ValorProgramado; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 585 */   public HTMLElement getElementValorProgramadoEd() { return this.$element_ValorProgramadoEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 594 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 603 */   public void setTextCicloEd(String text) { doSetText(this.$element_CicloEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 612 */   public void setTextDivConsulta(String text) { doSetText(this.$element_DivConsulta, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 621 */   public void setTextDivCreacionRegistro(String text) { doSetText(this.$element_DivCreacionRegistro, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 630 */   public void setTextDivEdicion(String text) { doSetText(this.$element_DivEdicion, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 639 */   public void setTextDivResultados(String text) { doSetText(this.$element_DivResultados, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 648 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 657 */   public void setTextEstadoEd(String text) { doSetText(this.$element_EstadoEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 666 */   public void setTextIndicadorEd(String text) { doSetText(this.$element_IndicadorEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 675 */   public void setTextLabelNombre(String text) { doSetText(this.$element_LabelNombre, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 684 */   public void setTextMesEd(String text) { doSetText(this.$element_MesEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 693 */   public void setTextNroRegistros(String text) { doSetText(this.$element_NroRegistros, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 702 */   public void setTextValorEjecutadoEd(String text) { doSetText(this.$element_ValorEjecutadoEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 711 */   public void setTextValorProgramadoEd(String text) { doSetText(this.$element_ValorProgramadoEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 720 */     if (node.getNodeType() != 9)
/*     */     {
/* 722 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 726 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 730 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 732 */       int substStart = "$element_".length();
/*     */       
/* 734 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 736 */         Field f = fs[i];
/*     */         
/* 738 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 740 */           String id = f.getName().substring(substStart);
/*     */           
/* 742 */           Node idNode = doc.getElementById(id);
/*     */           
/* 744 */           if (idNode == null) {
/*     */             
/* 746 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 748 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 752 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 756 */     } catch (Exception e) {
/*     */       
/* 758 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\IndicadorResultadoHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */