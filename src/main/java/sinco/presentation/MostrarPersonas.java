/*     */ package sinco.presentation;
/*     */ 
/*     */ import com.lutris.appserver.server.httpPresentation.ClientPageRedirectException;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentation;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationComms;
/*     */ import com.lutris.appserver.server.httpPresentation.HttpPresentationException;
/*     */ import com.lutris.util.KeywordValueException;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.html.HTMLElement;
/*     */ import org.w3c.dom.html.HTMLTableSectionElement;
/*     */ import sinco.business.SisUsuariosDTO;
/*     */ import sinco.data.SisUsuariosDAO;
/*     */ import sinco.presentation.MostrarPersonas;
/*     */ import sinco.presentation.MostrarPersonasHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MostrarPersonas
/*     */   implements HttpPresentation
/*     */ {
/*     */   private MostrarPersonasHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  36 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  37 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  40 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*     */     
/*  42 */     String apellidos = comms.request.getParameter("apellidos");
/*  43 */     String nombres = comms.request.getParameter("nombres");
/*     */     
/*  45 */     if (apellidos.trim().length() == 0 && nombres.trim().length() == 0) {
/*  46 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=DebeApellidosNombres"));
/*     */     }
/*     */     
/*  49 */     this.pagHTML = (MostrarPersonasHTML)comms.xmlcFactory.create(MostrarPersonasHTML.class);
/*  50 */     SisUsuariosDAO rsPersona = new SisUsuariosDAO();
/*  51 */     Collection arr = rsPersona.cargarSimilares(apellidos, nombres);
/*     */     
/*  53 */     HTMLTableSectionElement hte = this.pagHTML.getElementDetalle();
/*  54 */     boolean fondo = true;
/*  55 */     int cuantos = 0;
/*  56 */     Iterator iterator = arr.iterator();
/*  57 */     while (iterator.hasNext()) {
/*  58 */       SisUsuariosDTO regPersona = (SisUsuariosDTO)iterator.next();
/*  59 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/*  61 */       fondo = !fondo;
/*  62 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  64 */       eltr.appendChild(newtd("" + regPersona.getApellidos(), false));
/*  65 */       eltr.appendChild(newtd("" + regPersona.getNombres(), false));
/*     */       
/*  67 */       String sPagina = "ServiciosDeArea.po?area=" + regPersona.getArea();
/*  68 */       eltr.appendChild(newtdhref("" + regPersona.getNombreArea(), sPagina));
/*     */       
/*  70 */       hte.appendChild(eltr);
/*  71 */       cuantos++;
/*     */     } 
/*  73 */     this.pagHTML.setTextNroRegistros("" + cuantos);
/*  74 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  75 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/*  80 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/*  81 */     atrib.setValue(valor);
/*  82 */     return atrib;
/*     */   }
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/*  85 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  86 */     Element enlace = this.pagHTML.createElement("a");
/*  87 */     Node hijo = this.pagHTML.createTextNode(contenido);
/*  88 */     enlace.appendChild(hijo);
/*  89 */     Attr donde = this.pagHTML.createAttribute("href");
/*  90 */     donde.setValue(vinculo);
/*  91 */     enlace.setAttributeNode(donde);
/*  92 */     td.appendChild(enlace);
/*  93 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  94 */     return td;
/*     */   }
/*     */   private HTMLElement newtd(String contenido, boolean alinear) {
/*  97 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  98 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/*  99 */     if (alinear) {
/* 100 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 102 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 103 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\MostrarPersonas.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */