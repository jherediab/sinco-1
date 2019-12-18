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
/*     */ import org.w3c.dom.html.HTMLTableElement;
/*     */ import sinco.business.Utilidades;
/*     */ import sinco.business.VSolicitudesDTO;
/*     */ import sinco.data.VSolicitudesDAO;
/*     */ import sinco.presentation.SolicitudesMeCalifiquen;
/*     */ import sinco.presentation.SolicitudesMeCalifiquenHTML;
/*     */ import sinco.spec.MenuDO;
/*     */ 
/*     */ public class SolicitudesMeCalifiquen
/*     */   implements HttpPresentation {
/*     */   private SolicitudesMeCalifiquenHTML pagHTML;
/*     */   
/*     */   public void run(HttpPresentationComms comms) throws HttpPresentationException, KeywordValueException {
/*  25 */     if (!comms.session.getSessionData().containsKey("miId")) {
/*  26 */       throw new ClientPageRedirectException(comms.request.getAppFileURIPath("Mensaje.po?codigo=NoSesion"));
/*     */     }
/*     */     
/*  29 */     int idNav = Integer.parseInt((String)comms.session.getSessionData().get("miId"));
/*  30 */     this.pagHTML = (SolicitudesMeCalifiquenHTML)comms.xmlcFactory.create(SolicitudesMeCalifiquenHTML.class);
/*  31 */     HTMLTableElement hte = this.pagHTML.getElementSolicitudes();
/*     */     
/*  33 */     VSolicitudesDAO sf = new VSolicitudesDAO();
/*  34 */     sf.cargarTodosPorCalificarA(idNav);
/*  35 */     VSolicitudesDTO regSol = sf.next();
/*     */     
/*  37 */     int cuantas = 0;
/*  38 */     boolean fondo = true;
/*  39 */     while (regSol != null) {
/*  40 */       HTMLElement eltr = (HTMLElement)this.pagHTML.createElement("tr");
/*     */       
/*  42 */       fondo = !fondo;
/*  43 */       eltr.setAttributeNode(newAttr("class", "ct" + (fondo ? "1" : "2")));
/*     */       
/*  45 */       eltr.appendChild(newtd("" + regSol.getNumeroMostrar(), false));
/*  46 */       String sPagina = "VSMeCalifiquen.po?solicitud=" + regSol.getNumero();
/*  47 */       eltr.appendChild(newtdhref("" + regSol.getNombreServicio(), sPagina));
/*  48 */       eltr.appendChild(newtd("" + regSol.getNombreAreaCliente(), false));
/*  49 */       eltr.appendChild(newtd("" + regSol.getNombreCliente(), false));
/*  50 */       eltr.appendChild(newtd("" + Utilidades.darFormatoFecha(regSol.getFechaVigencia()), false));
/*     */       
/*  52 */       hte.appendChild(eltr);
/*  53 */       regSol = sf.next();
/*  54 */       cuantas++;
/*     */     } 
/*  56 */     sf.close();
/*  57 */     this.pagHTML.setTextNroRegistros("" + cuantas);
/*  58 */     this.pagHTML.setTextElMenu(MenuDO.getMenu("" + idNav));
/*  59 */     comms.response.writeDOM(this.pagHTML);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Attr newAttr(String tipo, String valor) {
/*  69 */     Attr atrib = this.pagHTML.createAttribute(tipo);
/*  70 */     atrib.setValue(valor);
/*  71 */     return atrib;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtdhref(String contenido, String vinculo) {
/*  81 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/*  82 */     Element enlace = this.pagHTML.createElement("a");
/*  83 */     Node hijo = this.pagHTML.createTextNode(contenido);
/*  84 */     enlace.appendChild(hijo);
/*  85 */     Attr donde = this.pagHTML.createAttribute("href");
/*  86 */     donde.setValue(vinculo);
/*  87 */     enlace.setAttributeNode(donde);
/*  88 */     td.appendChild(enlace);
/*  89 */     td.setAttributeNode(newAttr("class", "ctd"));
/*  90 */     return td;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HTMLElement newtd(String contenido, boolean alinear) {
/* 100 */     HTMLElement td = (HTMLElement)this.pagHTML.createElement("td");
/* 101 */     td.appendChild(this.pagHTML.createTextNode(contenido));
/* 102 */     if (alinear) {
/* 103 */       td.setAttributeNode(newAttr("align", "right"));
/*     */     }
/* 105 */     td.setAttributeNode(newAttr("class", "ctd"));
/* 106 */     return td;
/*     */   }
/*     */ }


/* Location:              D:\Documents\ardulab\Clinica Chiquinquira\fuente\sinco.war!\WEB-INF\classes\sinco\presentation\SolicitudesMeCalifiquen.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.0.7
 */