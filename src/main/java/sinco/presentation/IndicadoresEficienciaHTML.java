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
/*     */ import org.w3c.dom.html.HTMLImageElement;
/*     */ import org.w3c.dom.html.HTMLInputElement;
/*     */ import org.w3c.dom.html.HTMLLabelElement;
/*     */ import org.w3c.dom.html.HTMLScriptElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.presentation.IndicadoresEficienciaHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IndicadoresEficienciaHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLElement $element_AcumuladoEstaVigenciaEd;
/*     */   private HTMLElement $element_AcumuladoVigenciasEd;
/*     */   private HTMLElement $element_AreaEd;
/*     */   private HTMLInputElement $element_BtnPrincipal;
/*     */   private HTMLElement $element_CicloEd;
/*     */   private HTMLElement $element_CodigoIndicadorEd;
/*     */   private HTMLDivElement $element_DivConsulta;
/*     */   private HTMLDivElement $element_DivTableroControl;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLSelectElement $element_Findicador;
/*     */   private HTMLElement $element_FormulaEd;
/*     */   private HTMLElement $element_FrecuenciaMedicionEd;
/*     */   private HTMLImageElement $element_Grafica;
/*     */   private HTMLElement $element_IndicadorAcuerdoEd;
/*     */   private HTMLLabelElement $element_LabelNombre;
/*     */   private HTMLElement $element_LogroTotalAcumuladoEd;
/*     */   private HTMLElement $element_LogroTotalEd;
/*     */   private HTMLElement $element_MetaEstaVigenciaEd;
/*     */   private HTMLElement $element_MetaPlanDeDesarrolloEd;
/*     */   private HTMLElement $element_MetaProyectoEd;
/*     */   private HTMLElement $element_MetaTotalProgramadaEd;
/*     */   private HTMLElement $element_NombreIndicadorEd;
/*     */   private HTMLElement $element_ObjetivoEstrategicoEd;
/*     */   private HTMLElement $element_ObjetivoIndicadorEd;
/*     */   private HTMLElement $element_ProcesoEd;
/*     */   private HTMLElement $element_ProyectoInversionEd;
/*     */   private HTMLTableElement $element_TablaDatosVigencia;
/*     */   private HTMLTableSectionElement $element_TablaTablero;
/*     */   private HTMLTableElement $element_TablaVariables;
/*     */   private HTMLElement $element_TipoIndicadorEd;
/*     */   private HTMLElement $element_UnidadMedidaEd;
/*     */   private HTMLInputElement $element__operacion;
/*     */   public static final String CLASS_BOB = "BOB";
/*     */   public static final String CLASS_BOT = "BOT";
/*     */   public static final String CLASS_INP = "INP";
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
/*     */   public static final String NAME_ff = "ff";
/*     */   public static final String NAME_findicador = "findicador";
/*     */   public static final String NAME_labelNombre = "labelNombre";
/* 180 */   public static final Class XMLC_GENERATED_CLASS = IndicadoresEficienciaHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/IndicadoresEficiencia.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 191 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadoresEficienciaHTML() {
/* 202 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 204 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 212 */   public IndicadoresEficienciaHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadoresEficienciaHTML(IndicadoresEficienciaHTML src) {
/* 220 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 222 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 224 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IndicadoresEficienciaHTML(DocumentLoader loader, boolean buildDOM) {
/* 235 */     this.fDocumentLoader = loader;
/*     */     
/* 237 */     if (buildDOM)
/*     */     {
/* 239 */       buildDocument();
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
/* 251 */   public IndicadoresEficienciaHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 259 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 261 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 269 */     cloneDeepCheck(deep);
/*     */     
/* 271 */     return new IndicadoresEficienciaHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 279 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 287 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 296 */   public HTMLElement getElementAcumuladoEstaVigenciaEd() { return this.$element_AcumuladoEstaVigenciaEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 305 */   public HTMLElement getElementAcumuladoVigenciasEd() { return this.$element_AcumuladoVigenciasEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 314 */   public HTMLElement getElementAreaEd() { return this.$element_AreaEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 323 */   public HTMLInputElement getElementBtnPrincipal() { return this.$element_BtnPrincipal; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 332 */   public HTMLElement getElementCicloEd() { return this.$element_CicloEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 341 */   public HTMLElement getElementCodigoIndicadorEd() { return this.$element_CodigoIndicadorEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 350 */   public HTMLDivElement getElementDivConsulta() { return this.$element_DivConsulta; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 359 */   public HTMLDivElement getElementDivTableroControl() { return this.$element_DivTableroControl; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 368 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 377 */   public HTMLSelectElement getElementFindicador() { return this.$element_Findicador; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 386 */   public HTMLElement getElementFormulaEd() { return this.$element_FormulaEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 395 */   public HTMLElement getElementFrecuenciaMedicionEd() { return this.$element_FrecuenciaMedicionEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 404 */   public HTMLImageElement getElementGrafica() { return this.$element_Grafica; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 413 */   public HTMLElement getElementIndicadorAcuerdoEd() { return this.$element_IndicadorAcuerdoEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 422 */   public HTMLLabelElement getElementLabelNombre() { return this.$element_LabelNombre; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 431 */   public HTMLElement getElementLogroTotalAcumuladoEd() { return this.$element_LogroTotalAcumuladoEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 440 */   public HTMLElement getElementLogroTotalEd() { return this.$element_LogroTotalEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 449 */   public HTMLElement getElementMetaEstaVigenciaEd() { return this.$element_MetaEstaVigenciaEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 458 */   public HTMLElement getElementMetaPlanDeDesarrolloEd() { return this.$element_MetaPlanDeDesarrolloEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 467 */   public HTMLElement getElementMetaProyectoEd() { return this.$element_MetaProyectoEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 476 */   public HTMLElement getElementMetaTotalProgramadaEd() { return this.$element_MetaTotalProgramadaEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 485 */   public HTMLElement getElementNombreIndicadorEd() { return this.$element_NombreIndicadorEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 494 */   public HTMLElement getElementObjetivoEstrategicoEd() { return this.$element_ObjetivoEstrategicoEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 503 */   public HTMLElement getElementObjetivoIndicadorEd() { return this.$element_ObjetivoIndicadorEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 512 */   public HTMLElement getElementProcesoEd() { return this.$element_ProcesoEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 521 */   public HTMLElement getElementProyectoInversionEd() { return this.$element_ProyectoInversionEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 530 */   public HTMLTableElement getElementTablaDatosVigencia() { return this.$element_TablaDatosVigencia; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 539 */   public HTMLTableSectionElement getElementTablaTablero() { return this.$element_TablaTablero; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 548 */   public HTMLTableElement getElementTablaVariables() { return this.$element_TablaVariables; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 557 */   public HTMLElement getElementTipoIndicadorEd() { return this.$element_TipoIndicadorEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 566 */   public HTMLElement getElementUnidadMedidaEd() { return this.$element_UnidadMedidaEd; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 575 */   public HTMLInputElement getElement_operacion() { return this.$element__operacion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 584 */   public void setTextAcumuladoEstaVigenciaEd(String text) { doSetText(this.$element_AcumuladoEstaVigenciaEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 593 */   public void setTextAcumuladoVigenciasEd(String text) { doSetText(this.$element_AcumuladoVigenciasEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 602 */   public void setTextAreaEd(String text) { doSetText(this.$element_AreaEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 611 */   public void setTextCicloEd(String text) { doSetText(this.$element_CicloEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 620 */   public void setTextCodigoIndicadorEd(String text) { doSetText(this.$element_CodigoIndicadorEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 629 */   public void setTextDivConsulta(String text) { doSetText(this.$element_DivConsulta, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 638 */   public void setTextDivTableroControl(String text) { doSetText(this.$element_DivTableroControl, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 647 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 656 */   public void setTextFormulaEd(String text) { doSetText(this.$element_FormulaEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 665 */   public void setTextFrecuenciaMedicionEd(String text) { doSetText(this.$element_FrecuenciaMedicionEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 674 */   public void setTextIndicadorAcuerdoEd(String text) { doSetText(this.$element_IndicadorAcuerdoEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 683 */   public void setTextLabelNombre(String text) { doSetText(this.$element_LabelNombre, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 692 */   public void setTextLogroTotalAcumuladoEd(String text) { doSetText(this.$element_LogroTotalAcumuladoEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 701 */   public void setTextLogroTotalEd(String text) { doSetText(this.$element_LogroTotalEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 710 */   public void setTextMetaEstaVigenciaEd(String text) { doSetText(this.$element_MetaEstaVigenciaEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 719 */   public void setTextMetaPlanDeDesarrolloEd(String text) { doSetText(this.$element_MetaPlanDeDesarrolloEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 728 */   public void setTextMetaProyectoEd(String text) { doSetText(this.$element_MetaProyectoEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 737 */   public void setTextMetaTotalProgramadaEd(String text) { doSetText(this.$element_MetaTotalProgramadaEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 746 */   public void setTextNombreIndicadorEd(String text) { doSetText(this.$element_NombreIndicadorEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 755 */   public void setTextObjetivoEstrategicoEd(String text) { doSetText(this.$element_ObjetivoEstrategicoEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 764 */   public void setTextObjetivoIndicadorEd(String text) { doSetText(this.$element_ObjetivoIndicadorEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 773 */   public void setTextProcesoEd(String text) { doSetText(this.$element_ProcesoEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 782 */   public void setTextProyectoInversionEd(String text) { doSetText(this.$element_ProyectoInversionEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 791 */   public void setTextTipoIndicadorEd(String text) { doSetText(this.$element_TipoIndicadorEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 800 */   public void setTextUnidadMedidaEd(String text) { doSetText(this.$element_UnidadMedidaEd, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 809 */     if (node.getNodeType() != 9)
/*     */     {
/* 811 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 815 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 819 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 821 */       int substStart = "$element_".length();
/*     */       
/* 823 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 825 */         Field f = fs[i];
/*     */         
/* 827 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 829 */           String id = f.getName().substring(substStart);
/*     */           
/* 831 */           Node idNode = doc.getElementById(id);
/*     */           
/* 833 */           if (idNode == null) {
/*     */             
/* 835 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 837 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 841 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 845 */     } catch (Exception e) {
/*     */       
/* 847 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\IndicadoresEficienciaHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */