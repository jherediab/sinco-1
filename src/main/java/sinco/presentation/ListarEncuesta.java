/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.EncuestaDTO;
/*     */ import sinco.data.EncuestaDAO;
/*     */ import sinco.presentation.ListarEncuesta;
/*     */ import sinco.presentation.ListarEncuestaHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ListarEncuesta
/*     */   implements HttpPresentation
/*     */ {
/*     */   private ListarEncuestaHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  32 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  33 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*  35 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  37 */     this.pagHTML = (ListarEncuestaHTML)comms.xmlcFactory.create(ListarEncuestaHTML.class);
/*  38 */     EncuestaDAO rsEncuesta = new EncuestaDAO();
/*  39 */     rsEncuesta.cargarPendientesEnvio(idNav);
/*  40 */     EncuestaDTO regEncuesta = rsEncuesta.next();
/*  41 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/*  42 */     boolean fondo = true;
/*  43 */     int cuantas = 0;
/*  44 */     while (regEncuesta != null) {
/*  45 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/*  47 */       fondo = !fondo;
/*  48 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  50 */       eltr.appendChild(newtd("" + regEncuesta.getNumero(), false));
/*  51 */       String sPagina = "LlenarEncuesta.po?encuesta=" + regEncuesta.getNumero();
/*  52 */       eltr.appendChild(newtdhref("" + regEncuesta.getNombreServicio(), sPagina));
/*     */       
/*  54 */       hte.appendChild(eltr);
/*  55 */       regEncuesta = rsEncuesta.next();
/*  56 */       cuantas++;
/*     */     } 
/*  58 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/*  59 */     rsEncuesta.close();
/*  60 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  61 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/*  72 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/*  73 */     atrib.setValue(valor);
/*  74 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/*  84 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  85 */     Element enlace = this.pagHTML.createElement("a");
/*  86 */     Node hijo = this.pagHTML.createTextNode(contenido);
/*  87 */     enlace.appendChild(hijo);
/*  88 */     Attr donde = this.pagHTML.createAttribute("href");
/*  89 */     donde.setValue(vinculo);
/*  90 */     enlace.setAttributeNode(donde);
/*  91 */     td.appendChild(enlace);
/*  92 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  93 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido, boolean alinear) {
/* 103 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 104 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 105 */     if (alinear) {
/* 106 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 108 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 109 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\ListarEncuesta.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */