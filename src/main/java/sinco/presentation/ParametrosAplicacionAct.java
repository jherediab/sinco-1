/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Properties;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.ParametrosDTO;
/*     */ import sinco.presentation.ParametrosAplicacionAct;
/*     */ import sinco.presentation.ParametrosAplicacionActHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ import sinco.spec.Varios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParametrosAplicacionAct
/*     */   implements HttpPresentation
/*     */ {
/*     */   private ParametrosAplicacionActHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  31 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  32 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  35 */     String idNav = (String)comms.session.getSessionData().get("miId");
/*     */     
/*  37 */     String _operacion = comms.request.getParameter("_operacion");
/*  38 */     if (_operacion == null || _operacion.length() == 0) {
/*  39 */       _operacion = "L";
/*     */     }
/*     */     
/*  42 */     if (_operacion.equals("M")) {
/*  43 */       salvar(comms);
/*  44 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("ParametrosAplicacionAct.po"));
/*     */     } 
/*  46 */     if (_operacion.equals("Cargar")) {
/*  47 */       cargar();
/*  48 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("ParametrosAplicacionAct.po"));
/*     */     } 
/*     */     
/*  51 */     this.pagHTML = (ParametrosAplicacionActHTML)comms.xmlcFactory.create(ParametrosAplicacionActHTML.class);
/*  52 */     if (_operacion.equals("L")) {
/*  53 */       listar(comms, _operacion);
/*  54 */       HTMLElement sel = this.pagHTML.getElementTrCreacionRegistro();
/*  55 */       sel.getParentNode().removeChild(sel);
/*     */     }
/*  57 */     else if (_operacion.equals("P")) {
/*  58 */       editar(comms);
/*  59 */       HTMLElement sel = this.pagHTML.getElementTrConsulta();
/*  60 */       sel.getParentNode().removeChild(sel);
/*  61 */       sel = this.pagHTML.getElementTrResultados();
/*  62 */       sel.getParentNode().removeChild(sel);
/*     */     } 
/*     */     
/*  65 */     this.pagHTML.setTextElMenu(MenuDO.getMenu(idNav));
/*  66 */     comms.response.writeDOM(this.pagHTML);
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
/*     */   private void listar(HttpPresentationComms comms, String _operacion) throws HttpPresentationException, KeywordValueException {
/*  78 */     Properties prop = ParametrosDTO.getProp();
/*     */     
/*  80 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/*  81 */     int cuantas = 0;
/*  82 */     for (Enumeration e = prop.keys(); e.hasMoreElements(); ) {
/*     */       
/*  84 */       Object obj = e.nextElement();
/*     */       
/*  86 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*  87 */       String url = "ParametrosAplicacionAct.po?_operacion=P&codigo=" + obj.toString() + "";
/*  88 */       eltr.appendChild(newtdhref("" + obj.toString(), url));
/*  89 */       eltr.appendChild(newtd("" + prop.getProperty(obj.toString())));
/*  90 */       hte.appendChild(eltr);
/*  91 */       cuantas++;
/*     */     } 
/*  93 */     this.pagHTML.setTextNroRegistros("" + cuantas);
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
/*     */   
/*     */   private void salvar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 106 */     String codigo = comms.request.getParameter("codigo");
/* 107 */     String valor = comms.request.getParameter("valor");
/* 108 */     ParametrosDTO.put(codigo, valor);
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
/*     */   
/*     */   private void editar(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/* 121 */     String codigo = comms.request.getParameter("codigo");
/*     */     
/* 123 */     String valor = ParametrosDTO.getString(codigo);
/*     */     
/* 125 */     this.pagHTML.getElementCodigo().setValue("" + codigo);
/* 126 */     this.pagHTML.getElementValor().setValue("" + valor);
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
/*     */   private void cargar() {
/* 138 */     Varios oVarios = new Varios();
/* 139 */     oVarios.cargarParametros();
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
/*     */   private Attr newAttr(String tipo, String valor) {
/* 151 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/* 152 */     atrib.setValue(valor);
/* 153 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido) {
/* 164 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 165 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 166 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 167 */     return td;
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
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/* 180 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 181 */     Element enlace = this.pagHTML.createElement("a");
/* 182 */     Node hijo = this.pagHTML.createTextNode(contenido);
/* 183 */     enlace.appendChild(hijo);
/* 184 */     Attr donde = this.pagHTML.createAttribute("href");
/* 185 */     donde.setValue(vinculo);
/* 186 */     enlace.setAttributeNode(donde);
/* 187 */     td.appendChild(enlace);
/* 188 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 189 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ParametrosAplicacionAct.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */