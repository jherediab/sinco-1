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
/*     */ import org.w3c.dom.html.HTMLScriptElement;
/*     */ import org.w3c.dom.html.HTMLSelectElement;
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import org.w3c.dom.html.HTMLTableRowElement;
/*     */ import sinco.presentation.CalPlantillaAreaActHTML;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalPlantillaAreaActHTML
/*     */   extends HTMLObjectImpl
/*     */   implements XMLObject, HTMLObject
/*     */ {
/*     */   private HTMLInputElement $element_BotonAgr;
/*     */   private HTMLInputElement $element_CicloA;
/*     */   private HTMLInputElement $element_CicloE;
/*     */   private HTMLInputElement $element_CicloN;
/*     */   private HTMLTableElement $element_DetE;
/*     */   private HTMLTableElement $element_DetPlantillas;
/*     */   private HTMLTableElement $element_DetalleE;
/*     */   private HTMLTableElement $element_DetalleN;
/*     */   private HTMLScriptElement $element_ElMenu;
/*     */   private HTMLInputElement $element_Filtro;
/*     */   private HTMLSelectElement $element_IdCiclo;
/*     */   private HTMLSelectElement $element_IdPlantilla;
/*     */   private HTMLTableElement $element_MostrarMenu;
/*     */   private HTMLElement $element_NombreCicloA;
/*     */   private HTMLElement $element_NombreCicloN;
/*     */   private HTMLElement $element_NombrePlantillaA;
/*     */   private HTMLElement $element_NombrePlantillaN;
/*     */   private HTMLInputElement $element_PlantillaA;
/*     */   private HTMLInputElement $element_PlantillaE;
/*     */   private HTMLInputElement $element_PlantillaN;
/*     */   private HTMLTableElement $element_TableN;
/*     */   private HTMLDivElement $element_TrAreas;
/*     */   private HTMLTableRowElement $element_TrEli;
/*     */   private HTMLDivElement $element_TrNuevo;
/*     */   private HTMLDivElement $element_TrSeleccionar;
/*     */   public static final String CLASS_BOT = "BOT";
/*     */   public static final String CLASS_CA = "CA";
/*     */   public static final String CLASS_CF2 = "CF2";
/*     */   public static final String CLASS_INP = "INP";
/*     */   public static final String CLASS_ca = "ca";
/*     */   public static final String CLASS_cb = "cb";
/*     */   public static final String CLASS_cf1 = "cf1";
/*     */   public static final String CLASS_cf2 = "cf2";
/*     */   public static final String CLASS_container = "container";
/*     */   public static final String CLASS_dat = "dat";
/*     */   public static final String CLASS_dat2 = "dat2";
/*     */   public static final String CLASS_tabf = "tabf";
/*     */   public static final String CLASS_tabw = "tabw";
/*     */   public static final String CLASS_tit = "tit";
/*     */   public static final String NAME__operacion = "_operacion";
/*     */   public static final String NAME_ciclo = "ciclo";
/*     */   public static final String NAME_clickcontrol1 = "clickcontrol1";
/*     */   public static final String NAME_filtro = "filtro";
/*     */   public static final String NAME_miBoton = "miBoton";
/*     */   public static final String NAME_miForma = "miForma";
/*     */   public static final String NAME_miForma2 = "miForma2";
/*     */   public static final String NAME_miForma3 = "miForma3";
/*     */   public static final String NAME_miForma4 = "miForma4";
/*     */   public static final String NAME_plantilla = "plantilla";
/* 191 */   public static final Class XMLC_GENERATED_CLASS = CalPlantillaAreaActHTML.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String XMLC_SOURCE_FILE = "sinco/presentation/CalPlantillaAreaAct.html";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 202 */   private static final XMLCDomFactory fDOMFactory = XMLCDomFactoryCache.getFactory(org.enhydra.xml.xmlc.dom.lazydom.LazyHTMLDomFactory.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final DocumentLoader fDocumentLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalPlantillaAreaActHTML() {
/* 213 */     this(StandardDocumentLoader.getInstance());
/*     */     
/* 215 */     buildDocument();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 223 */   public CalPlantillaAreaActHTML(boolean buildDOM) { this(StandardDocumentLoader.getInstance(), buildDOM); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalPlantillaAreaActHTML(CalPlantillaAreaActHTML src) {
/* 231 */     this.fDocumentLoader = src.getDocumentLoader();
/*     */     
/* 233 */     setDocument((Document)src.getDocument().cloneNode(true), src.getMIMEType(), src.getEncoding());
/*     */     
/* 235 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CalPlantillaAreaActHTML(DocumentLoader loader, boolean buildDOM) {
/* 246 */     this.fDocumentLoader = loader;
/*     */     
/* 248 */     if (buildDOM)
/*     */     {
/* 250 */       buildDocument();
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
/* 262 */   public CalPlantillaAreaActHTML(DocumentLoader loader) { this(loader, true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildDocument() {
/* 270 */     setDocument(getDocumentLoader().getDocument(getClass()), "text/html", "ISO-8859-1");
/*     */     
/* 272 */     syncAccessMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node cloneNode(boolean deep) {
/* 280 */     cloneDeepCheck(deep);
/*     */     
/* 282 */     return new CalPlantillaAreaActHTML(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 290 */   protected final DocumentLoader getDocumentLoader() { return this.fDocumentLoader; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 298 */   protected final XMLCDomFactory getDomFactory() { return fDOMFactory; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 307 */   public HTMLInputElement getElementBotonAgr() { return this.$element_BotonAgr; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 316 */   public HTMLInputElement getElementCicloA() { return this.$element_CicloA; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 325 */   public HTMLInputElement getElementCicloE() { return this.$element_CicloE; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 334 */   public HTMLInputElement getElementCicloN() { return this.$element_CicloN; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 343 */   public HTMLTableElement getElementDetE() { return this.$element_DetE; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 352 */   public HTMLTableElement getElementDetPlantillas() { return this.$element_DetPlantillas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 361 */   public HTMLTableElement getElementDetalleE() { return this.$element_DetalleE; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 370 */   public HTMLTableElement getElementDetalleN() { return this.$element_DetalleN; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 379 */   public HTMLScriptElement getElementElMenu() { return this.$element_ElMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 388 */   public HTMLInputElement getElementFiltro() { return this.$element_Filtro; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 397 */   public HTMLSelectElement getElementIdCiclo() { return this.$element_IdCiclo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 406 */   public HTMLSelectElement getElementIdPlantilla() { return this.$element_IdPlantilla; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 415 */   public HTMLTableElement getElementMostrarMenu() { return this.$element_MostrarMenu; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 424 */   public HTMLElement getElementNombreCicloA() { return this.$element_NombreCicloA; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 433 */   public HTMLElement getElementNombreCicloN() { return this.$element_NombreCicloN; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 442 */   public HTMLElement getElementNombrePlantillaA() { return this.$element_NombrePlantillaA; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 451 */   public HTMLElement getElementNombrePlantillaN() { return this.$element_NombrePlantillaN; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 460 */   public HTMLInputElement getElementPlantillaA() { return this.$element_PlantillaA; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 469 */   public HTMLInputElement getElementPlantillaE() { return this.$element_PlantillaE; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 478 */   public HTMLInputElement getElementPlantillaN() { return this.$element_PlantillaN; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 487 */   public HTMLTableElement getElementTableN() { return this.$element_TableN; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 496 */   public HTMLDivElement getElementTrAreas() { return this.$element_TrAreas; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 505 */   public HTMLTableRowElement getElementTrEli() { return this.$element_TrEli; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 514 */   public HTMLDivElement getElementTrNuevo() { return this.$element_TrNuevo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 523 */   public HTMLDivElement getElementTrSeleccionar() { return this.$element_TrSeleccionar; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 532 */   public void setTextElMenu(String text) { doSetText(this.$element_ElMenu, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 541 */   public void setTextNombreCicloA(String text) { doSetText(this.$element_NombreCicloA, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 550 */   public void setTextNombreCicloN(String text) { doSetText(this.$element_NombreCicloN, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 559 */   public void setTextNombrePlantillaA(String text) { doSetText(this.$element_NombrePlantillaA, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 568 */   public void setTextNombrePlantillaN(String text) { doSetText(this.$element_NombrePlantillaN, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 577 */   public void setTextTrAreas(String text) { doSetText(this.$element_TrAreas, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 586 */   public void setTextTrNuevo(String text) { doSetText(this.$element_TrNuevo, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 595 */   public void setTextTrSeleccionar(String text) { doSetText(this.$element_TrSeleccionar, text); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void syncWithDocument(Node node) {
/* 604 */     if (node.getNodeType() != 9)
/*     */     {
/* 606 */       throw new XMLCRuntimeException("Node must be a document node");
/*     */     }
/*     */ 
/*     */     
/* 610 */     Document doc = (Document)node;
/*     */ 
/*     */     
/*     */     try {
/* 614 */       Field[] fs = getClass().getDeclaredFields();
/*     */       
/* 616 */       int substStart = "$element_".length();
/*     */       
/* 618 */       for (int i = 0; i < fs.length; i++) {
/*     */         
/* 620 */         Field f = fs[i];
/*     */         
/* 622 */         if (f.getName().startsWith("$element_")) {
/*     */           
/* 624 */           String id = f.getName().substring(substStart);
/*     */           
/* 626 */           Node idNode = doc.getElementById(id);
/*     */           
/* 628 */           if (idNode == null) {
/*     */             
/* 630 */             id = id.substring(0, 1).toLowerCase() + id.substring(1);
/*     */             
/* 632 */             idNode = doc.getElementById(id);
/*     */           } 
/*     */ 
/*     */           
/* 636 */           if (idNode != null) f.set(this, idNode);
/*     */         
/*     */         } 
/*     */       } 
/* 640 */     } catch (Exception e) {
/*     */       
/* 642 */       throw new XMLCRuntimeException("Error reflecting on element access fields", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\CalPlantillaAreaActHTML.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */